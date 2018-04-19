package dina.filgoalapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MatchesModel implements Serializable {

    @SerializedName("today")
    @Expose
    private List<ModelDays> todayModelList = null;


    public void setTodayModelList(List<ModelDays> todayModelList) {
        this.todayModelList = todayModelList;
    }

    public void setYesterdayModelList(List<ModelDays> yesterdayModelList) {
        this.yesterdayModelList = yesterdayModelList;
    }

    public void setTomorrowModelList(List<ModelDays> tomorrowModelList) {
        this.tomorrowModelList = tomorrowModelList;
    }

    public List<ModelDays> getTodayModelList() {

        return todayModelList;
    }

    public List<ModelDays> getYesterdayModelList() {
        return yesterdayModelList;
    }

    public List<ModelDays> getTomorrowModelList() {
        return tomorrowModelList;
    }

    @SerializedName("yesterday")
    @Expose

    private List<ModelDays> yesterdayModelList = null;



    @SerializedName("tomorrow")
    @Expose
    private List<ModelDays> tomorrowModelList = null;


}
