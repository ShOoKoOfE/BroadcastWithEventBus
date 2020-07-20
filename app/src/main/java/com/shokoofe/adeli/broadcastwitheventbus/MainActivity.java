package com.shokoofe.adeli.broadcastwitheventbus;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
    PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
    alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000,alarmIntent);

  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onMessageEvent(MessageEvent event) {
    Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override
  public void onStop() {
    EventBus.getDefault().unregister(this);
    super.onStop();
  }
}
