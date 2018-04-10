package dina.filgoalapp.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Model implements Serializable{

    @SerializedName("pages")
    @Expose
    private List<ModelPages> pages = null;

    public void setPages(List<ModelPages> pages) {
        this.pages = pages;
    }

    public List<ModelPages> getPages() {

        return pages;
    }
}
