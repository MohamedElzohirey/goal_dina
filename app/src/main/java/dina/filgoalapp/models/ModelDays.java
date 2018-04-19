package dina.filgoalapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDays {


    @SerializedName("matchID")
    @Expose
    private Integer matchID;


    @SerializedName("firstTeam")
    @Expose
    private String firstTeam;


    @SerializedName("secondTeam")
    @Expose
    private String secondTeam;



    @SerializedName("firstTeamFlag")
    @Expose
    private String firstTeamFlag;

    @SerializedName("secondTeamFlag")
    @Expose
    private String secondTeamFlag;


    @SerializedName("result")
    @Expose
    private String result;


    @SerializedName("date")
    @Expose
    private String date;


    @SerializedName("tv")
    @Expose
    private String tv;


    public void setMatchID(Integer matchID) {
        this.matchID = matchID;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public void setFirstTeamFlag(String firstTeamFlag) {
        this.firstTeamFlag = firstTeamFlag;
    }

    public void setSecondTeamFlag(String secondTeamFlag) {
        this.secondTeamFlag = secondTeamFlag;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public Integer getMatchID() {

        return matchID;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public String getFirstTeamFlag() {
        return firstTeamFlag;
    }

    public String getSecondTeamFlag() {
        return secondTeamFlag;
    }

    public String getResult() {
        return result;
    }

    public String getDate() {
        return date;
    }

    public String getTv() {
        return tv;
    }



}
