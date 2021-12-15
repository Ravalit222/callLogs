package com.example.calllogs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        if (!checkPer()) {
            requestPermission();
        }
        Button allLogs = findViewById(R.id.allLogs);
        Button dialledLogs = findViewById(R.id.dialledCalls);
        Button receivedLogs = findViewById(R.id.receivedCalls);
        Button rejectedLogs = findViewById(R.id.rejectedCalls);
        Button missedLogs = findViewById(R.id.missedCalls);
        Button barChart = findViewById(R.id.chart);
        // clicking all logs buttons
        allLogs.setOnClickListener(v -> {
            if (checkPer()) {
                Intent intent = new Intent(getApplicationContext(), LogScreen.class);
                intent.putExtra("key", "All Logs");
                startActivity(intent);
            } else {
                showToast();
            }
        });
        // clicking all logs buttons
        dialledLogs.setOnClickListener(v -> {
            if (checkPer()) {
                Intent intent = new Intent(getApplicationContext(), LogScreen.class);
                intent.putExtra("key", "Dialled Call Logs");
                startActivity(intent);
            } else {
                showToast();
            }
        });
        // clicking all logs buttons
        receivedLogs.setOnClickListener(v -> {
            if (checkPer()) {
                Intent intent = new Intent(getApplicationContext(), LogScreen.class);
                intent.putExtra("key", "Received Call Logs");
                startActivity(intent);
            } else {
                showToast();
            }
        });
        // clicking all logs buttons
        rejectedLogs.setOnClickListener(v -> {
            if (checkPer()) {
                Intent intent = new Intent(getApplicationContext(), LogScreen.class);
                intent.putExtra("key", "Rejected Call Logs");
                startActivity(intent);
            } else {
                showToast();
            }
        });// clicking all logs buttons
        missedLogs.setOnClickListener(v -> {
            if (checkPer()) {
                Intent intent = new Intent(getApplicationContext(), LogScreen.class);
                intent.putExtra("key", "Missed Call Logs");
                startActivity(intent);
            } else {
                showToast();
            }
        });
        // clicking all logs buttons
        barChart.setOnClickListener(v -> {
            if (checkPer()) {
                Intent intent = new Intent(getApplicationContext(), Chart.class);
                startActivity(intent);
            } else {
                showToast();
            }
        });

    }

    private boolean checkPer() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
    }
    private void showToast(){
         Toast.makeText(getApplicationContext(),"Please enable Call Logs Permission",
                Toast.LENGTH_LONG).show();
    }


}