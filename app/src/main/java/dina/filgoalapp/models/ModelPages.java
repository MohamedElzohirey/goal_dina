package dina.filgoalapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelPages {


    @SerializedName("itemID")
    @Expose
    private Integer itemID;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("imageURL")
    @Expose
    private String imageURL;

    @SerializedName("video")
    @Expose
    private String video;

    @SerializedName("date")
    @Expose
    private String date;


    @SerializedName("details")
    @Expose
    private String details;

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getItemID() {

        return itemID;
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getVideo() {
        return video;
    }

    public String getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }



}
