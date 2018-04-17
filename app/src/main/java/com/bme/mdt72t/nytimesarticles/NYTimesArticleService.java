package com.bme.mdt72t.nytimesarticles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NYTimesArticleService {

    String BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/";
    String ApiKey = "b57a5a5f1d79412fa7ff73bcf5b555c7";

    //section = all-sections
    //period = [1,7,30]

    @GET("{Section}/{Period}.json?api-key=" + ApiKey)
    Call<ArticlePOJO> loadCards(@Path("Section") String section,
                                @Path("Period") String period);

}
