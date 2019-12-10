package com.example.taskmanagementapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.taskmanagementapp.expense_tracking.SiemensDetail;

import java.util.List;

public class SiteActivityList extends ArrayAdapter<SiteActivityDetails> {


    private Activity context;
    private List<SiteActivityDetails> siteActivityList;

    public SiteActivityList(Activity context,List<SiteActivityDetails> siteActivityList){
        super(context, R.layout.list_activty_site_layout,siteActivityList);
        this.context = context;
        this.siteActivityList = siteActivityList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater= context.getLayoutInflater();

        View listViewItemActivity = inflater.inflate(R.layout.list_activty_site_layout,null,true);
        TextView textview_location_spinner = listViewItemActivity.findViewById(R.id.Activity_location_list);
        TextView textView_type_spinner = listViewItemActivity.findViewById(R.id.activity_type_list);
        TextView textView_demo_start_time = listViewItemActivity.findViewById(R.id.activity_demo_start_date_list);
        TextView textView_demo_end_time =listViewItemActivity.findViewById(R.id.activity_demo_end_date_list);
        TextView textView_activity_desc = listViewItemActivity.findViewById(R.id.activity_Description_list);

        SiteActivityDetails siteActivityDetails = siteActivityList.get(position);
        textview_location_spinner.setText(siteActivityDetails.getActivitySiteLocation_De());
        textView_type_spinner.setText(siteActivityDetails.getActivitySiteType_De());
        textView_demo_start_time.setText(siteActivityDetails.getActivity_start_demo_De());
        textView_demo_end_time.setText(siteActivityDetails.getActivity_end_demo_De());
        textView_activity_desc.setText(siteActivityDetails.getActivity_description_De());

        return listViewItemActivity;
    }
}
