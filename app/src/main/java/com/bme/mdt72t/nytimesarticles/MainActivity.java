package com.bme.mdt72t.nytimesarticles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //UI vars
    @BindView(R.id.tv)
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getArticles();
    }

    private void getArticles() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NYTimesArticleService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NYTimesArticleService service = retrofit.create(NYTimesArticleService.class);
        Call<ArticlePOJO> call = service.loadCards("all-sections", "7");
        call.enqueue(new Callback<ArticlePOJO>() {

            @Override
            public void onResponse(Call<ArticlePOJO> call, Response<ArticlePOJO> response) {
                ArticlePOJO articlePOJO = response.body();

                textView.setText("num_result: " + articlePOJO.getNum_results() + "\r\n"
                        + "copyright: " + articlePOJO.getCopyright() + "\r\n"
                        + "status: " + articlePOJO.getStatus() + "\r\n" + "\r\n" + "\r\n"
                        + articlePOJO.getResults().get(0).getByline() + "\r\n"
                        + articlePOJO.getResults().get(1).getByline() + "\r\n");
            }

            @Override
            public void onFailure(Call<ArticlePOJO> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Error during query!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
