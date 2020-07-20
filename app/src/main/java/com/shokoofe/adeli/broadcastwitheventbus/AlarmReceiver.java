package com.shokoofe.adeli.broadcastwitheventbus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;

public class AlarmReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    EventBus.getDefault().post(new MessageEvent("Good Morning,Wake up!"));
  }
}
