package com.bme.mdt72t.nytimesarticles.model.original;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Media
{
    @SerializedName("subtype")
    @Expose
    private String subtype;

    @SerializedName("approved_for_syndication")
    @Expose
    private String approved_for_syndication;

    @SerializedName("caption")
    @Expose
    private String caption;

    @SerializedName("media-metadata")
    @Expose
    private List<Thumbnail> metadata;

    private String copyright;

    private String type;

    public String getSubtype ()
    {
        return subtype;
    }

    public void setSubtype (String subtype)
    {
        this.subtype = subtype;
    }

    public String getApproved_for_syndication ()
    {
        return approved_for_syndication;
    }

    public void setApproved_for_syndication (String approved_for_syndication)
    {
        this.approved_for_syndication = approved_for_syndication;
    }

    public String getCaption ()
    {
        return caption;
    }

    public void setCaption (String caption)
    {
        this.caption = caption;
    }

    public List<Thumbnail> getMetadata ()
    {
        return metadata;
    }

    public void setMetadata (List<Thumbnail> metadata)
    {
        this.metadata = metadata;
    }

    public String getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (String copyright)
    {
        this.copyright = copyright;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [subtype = "+subtype+", approved_for_syndication = "+approved_for_syndication+", caption = "+caption+", media-metadata = "+metadata+", copyright = "+copyright+", type = "+type+"]";
    }
}