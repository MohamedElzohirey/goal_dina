package dina.filgoalapp.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Model {

    @SerializedName("pages")
    @Expose
    private List<ModelPages> results = null;

    public void setResults(List<ModelPages> results) {
        this.results = results;
    }

    public List<ModelPages> getResults() {

        return results;
    }
}
