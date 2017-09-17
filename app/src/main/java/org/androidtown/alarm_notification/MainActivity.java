package org.androidtown.alarm_notification;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    /*
    AlarmReceiver alarmReceiver;
    Button AccessTime;
    TextView DisplayTime;
    private int CalendarHour, CalendarMinute;
    String format;
    Calendar cal;
    TimePickerDialog timepickdialog;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getToken();
      /*
            AccessTime = (Button)findViewById(R.id.button1);
            DisplayTime = (TextView)findViewById(R.id.textView1);

            AccessTime.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    cal = Calendar.getInstance();
                    CalendarHour = cal.get(Calendar.HOUR_OF_DAY);
                    CalendarMinute = cal.get(Calendar.MINUTE);

                    timepickdialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            if (hourOfDay == 0) {
                                hourOfDay += 12;
                                format = "AM";
                            } else if (hourOfDay == 12) {
                                format = "PM";
                            } else if (hourOfDay > 12) {
                                hourOfDay -= 12;
                                format = "PM";
                            } else {
                                format = "AM";
                            }

                            DisplayTime.setText(hourOfDay + ":" + minute + format);
                            setAlarm(getBaseContext(), cal.getTimeInMillis());
                        }
                    }, CalendarHour, CalendarMinute, false);
                    timepickdialog.show();
                }
            });
        }
*/

        final Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        //int second = 5000;

        TimePickerDialog dlg = new TimePickerDialog(this,  new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.d("", "hourOfDay: " + hourOfDay + ", minute: " + minute);
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND, now.get(Calendar.SECOND));
                setAlarm(getBaseContext(), cal.getTimeInMillis());
            }
        }, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false);
    }

    public void setAlarm(Context context, long alarmAt) {

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        //textAlarmPrompt.setText("\n" + "Alarm is set " + targetCal.getTime() + "\n");
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent, 0);
        //AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmAt, pendingIntent);
    }

        /*
        WebView webView = (WebView)findViewById(R.id.web_view);
        webView.loadUrl("172.16.0.87:3000");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebViewJavaScriptInterface(this), "app");


        datePicker = (DatePicker) findViewById(R.id.datepicker);

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dataOfMonth) {
                String msg = String.format("%d / %d / %d", year, monthOfYear + 1, dataOfMonth);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        */
    //}
        /*
    public class WebViewJavaScriptInterface
    {
        private Context context;

        public WebViewJavaScriptInterface(Context context)
        {
            this.context = context;
        }
    }
    */

}

