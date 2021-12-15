package com.example.calllogs;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_screen);
        String titleText = getIntent().getStringExtra("key");
        TextView textView = (TextView) findViewById(R.id.call);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(titleText);
        textView.setText(getCallDetails(titleText));
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    private String getCallDetails(String titleText) {
        StringBuffer sb = new StringBuffer();
        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null,
                android.provider.CallLog.Calls.DATE + " DESC",null);
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        sb.append("Call Log Details:\n\n");
        while (cursor.moveToNext()) {
            String phNumber = cursor.getString(number);
            String phType = cursor.getString(type);
            String phDate = cursor.getString(date);
            Date date1 = new Date(Long.valueOf(phDate));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm");
            String dateString = dateFormat.format(date1);
            String callDuration = cursor.getString(duration);
            String dir = null;
            int dirCode = Integer.parseInt(phType);
            switch (dirCode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
                case CallLog.Calls.REJECTED_TYPE:
                    dir = "REJECTED";
                    break;
            }
            switch (titleText){
                case "Dialled Call Logs":
                    if(dir=="OUTGOING"){
                        sb.append("\nPhone Number: " + phNumber + "\nCall Type: " + dir + "\nCall Date: " + dateString + "\nCall Duration:" + callDuration);
                        sb.append("\n---------------------------------------------------------------------");
                    }
                    break;
                case "Received Call Logs":if(dir=="INCOMING"){
                    sb.append("\nPhone Number: " + phNumber + "\nCall Type: " + dir + "\nCall Date: " + dateString + "\nCall Duration:" + callDuration);
                    sb.append("\n---------------------------------------------------------------------");
                }break;
                case "Missed Call Logs":if(dir=="MISSED"){
                    sb.append("\nPhone Number: " + phNumber + "\nCall Type: " + dir + "\nCall Date: " + dateString + "\nCall Duration:" + callDuration);
                    sb.append("\n---------------------------------------------------------------------");
                }break;
                case "Rejected Call Logs":if(dir=="REJECTED"){
                    sb.append("\nPhone Number: " + phNumber + "\nCall Type: " + dir + "\nCall Date: " + dateString + "\nCall Duration:" + callDuration);
                    sb.append("\n---------------------------------------------------------------------");
                }break;
                default:sb.append("\nPhone Number: " + phNumber + "\nCall Type: " + dir + "\nCall Date: " + dateString + "\nCall Duration:" + callDuration);
            sb.append("\n---------------------------------------------------------------------");
            }
        }
        cursor.close();
        return sb.toString();
    }
}