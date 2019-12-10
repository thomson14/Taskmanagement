package com.example.taskmanagementapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.taskmanagementapp.expense_tracking.SiemensDetail;
import com.example.taskmanagementapp.expense_tracking.SiemensSite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;

public class SiteActivitySecond extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

    FloatingActionButton floatingActionButtonActivity;

    Spinner location_spinner,Activity_type_spinner;
    Button btn_start,btn_end,btn_activity_cancel,btn_activity_update;
    TextView demo_btn_start_Text,demo_btn_end_text;
    EditText activity_description;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String[] country3 = { "India", "USA", "China", "Japan", "Other"};
    String[] country4 = { "In", "U", "Ch", "Ja", "Ot"};

    DatabaseReference activtiyReference;
    ListView listViewActivity;
    List<SiteActivityDetails>  activityList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_second);



        floatingActionButtonActivity = findViewById(R.id.floating_site);

        floatingActionButtonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SiteActivitySecond.this);
                dialog.setContentView(R.layout.dialog_activity_site);
                location_spinner = dialog.findViewById(R.id.spinner_location);
                Activity_type_spinner = dialog.findViewById(R.id.activity_site_type);
                btn_start = dialog.findViewById(R.id.demo_start_btn);
                btn_end = dialog.findViewById(R.id.demo_end_btn);
                btn_activity_cancel = dialog.findViewById(R.id.btn_activity_cancel);
                btn_activity_update = dialog.findViewById(R.id.btn_activity_update);
                demo_btn_start_Text = dialog.findViewById(R.id.btn_start);
                demo_btn_end_text = dialog.findViewById(R.id.end_btn);
                activity_description = dialog.findViewById(R.id.activity_site_description);


                ArrayAdapter aa = new ArrayAdapter(SiteActivitySecond.this,android.R.layout.simple_spinner_item,country3);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                location_spinner.setAdapter(aa);
                location_spinner.setOnItemSelectedListener(SiteActivitySecond.this);


                ArrayAdapter aa1 = new ArrayAdapter(SiteActivitySecond.this,android.R.layout.simple_spinner_item,country4);
                aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Activity_type_spinner.setAdapter(aa1);
                Activity_type_spinner.setOnItemSelectedListener(SiteActivitySecond.this);

                btn_start.setOnClickListener(this);
                btn_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        // Launch Time Picker Dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(SiteActivitySecond.this,
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {

                                        demo_btn_start_Text.setText(hourOfDay + ":" + minute);
                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.show();
                    }
                });

                btn_end.setOnClickListener(this);
                btn_end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        // Launch Time Picker Dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(SiteActivitySecond.this,
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {

                                        demo_btn_end_text.setText(hourOfDay + ":" + minute);
                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.show();
                    }
                });

                btn_activity_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SiteActivity();

                    }
                });

                btn_activity_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                dialog.show();






            }
        });


    }


    public void SiteActivity(){

        String activityLocationSpinner = location_spinner.getSelectedItem().toString();
        String activityTypeSpinner =Activity_type_spinner.getSelectedItem().toString();
        String demostartTime = demo_btn_start_Text.getText().toString();
        String demoendTime = demo_btn_end_text.getText().toString();
        String activitydescription = activity_description.getText().toString();

        if(!TextUtils.isEmpty(activityLocationSpinner)) {

            SiteActivityDetails siteActivityDetails = new SiteActivityDetails(String.valueOf(2),activityLocationSpinner,activityTypeSpinner,demostartTime,demoendTime,activitydescription);

            activtiyReference.child("Details2").push().setValue(siteActivityDetails, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                    Log.d("TAG2", "onComplete: " + databaseReference.getKey());
                }
            });

        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference referenceA = FirebaseDatabase.getInstance().getReference("Details2");
        referenceA.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                activityList.clear();

                for (DataSnapshot activitySnapshot: dataSnapshot.getChildren()){
                    SiteActivityDetails siteActivityDetails = activitySnapshot.getValue(SiteActivityDetails.class);
                    activityList.add(siteActivityDetails);

                }
                SiteActivityList adapter2 = new SiteActivityList(SiteActivitySecond.this,activityList);

                listViewActivity.setAdapter(adapter2);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
