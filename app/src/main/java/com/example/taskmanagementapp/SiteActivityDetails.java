package com.example.taskmanagementapp;

public class SiteActivityDetails {

    String activitySiteId;
    String activitySiteLocation_De;
    String activitySiteType_De;
    String activity_start_demo_De ;
    String activity_end_demo_De ;
    String activity_description_De ;

    public  SiteActivityDetails(){

    }

    public SiteActivityDetails(String activitySiteId, String activitySiteLocation_De, String activitySiteType_De, String activity_start_demo_De, String activity_end_demo_De, String activity_description_De) {
        this.activitySiteId = activitySiteId;
        this.activitySiteLocation_De = activitySiteLocation_De;
        this.activitySiteType_De = activitySiteType_De;
        this.activity_start_demo_De = activity_start_demo_De;
        this.activity_end_demo_De = activity_end_demo_De;
        this.activity_description_De = activity_description_De;
    }

    public String getActivitySiteId() {
        return activitySiteId;
    }

    public String getActivitySiteLocation_De() {
        return activitySiteLocation_De;
    }

    public String getActivitySiteType_De() {
        return activitySiteType_De;
    }

    public String getActivity_start_demo_De() {
        return activity_start_demo_De;
    }

    public String getActivity_end_demo_De() {
        return activity_end_demo_De;
    }

    public String getActivity_description_De() {
        return activity_description_De;
    }
}
