package com.example.calllogs;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CallLog;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class Chart extends AppCompatActivity {
 int dialled=0,received=0,missed=0,rejected=0;
    StringBuffer sb = new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        getLogs();
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.addPieSlice(
                new PieModel(
                        "Dialled Calls",
                      dialled,
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Missed Calls",
                        missed,
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Received Calls",
                        received,
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Rejected Calls",
                        rejected,
                        Color.parseColor("#29B6F6")));
        pieChart.startAnimation();
    }

    private void getLogs() {
        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        while (cursor.moveToNext()){
            String phType = cursor.getString(type);
            sb.append(phType);
            sb.append("\n");
        }
        cursor.close();
        getCount();
    }

    private void getCount() {
        String value=sb.toString();
        String[] values = value.split("\n");
        for (int i=0;i<values.length;i++){
            switch (values[i]){
                case "1":received+=1;break;
                case "2":dialled+=1;break;
                case "3": missed+=1;break;
                case "5":rejected+=1;break;
            }
        }

    }
}