package com.github.tianma8023.smscode.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.github.tianma8023.smscode.entity.SmsMessageData;
import com.github.tianma8023.smscode.utils.XLog;

/**
 * 观察监测短信的Service
 */
public class SmsObserveService extends Service {

    private SmsObserver mSmsObserver;

    private int lastId = 0;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerObserver();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterObserver();
    }

    private void registerObserver() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            if (mSmsObserver == null) {
                mSmsObserver = new SmsObserver(new Handler(Looper.getMainLooper()));
            }
            // register SMS content observer
            getContentResolver().registerContentObserver(Telephony.Sms.CONTENT_URI,
                    true, mSmsObserver);
        } else {
            XLog.d("RECEIVE_SMS permission denied");
        }
    }

    private void unregisterObserver() {
        if (mSmsObserver == null) {
            return;
        }

        // unregister content observer
        getContentResolver().unregisterContentObserver(mSmsObserver);
    }

    private class SmsObserver extends ContentObserver {

        private SmsObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            XLog.d("SmsObserver#onChange()");
            parseNewSms();
        }
    }

    private void parseNewSms() {
        final String[] projection = new String[]{
                Telephony.Sms._ID,
                Telephony.Sms.ADDRESS,
                Telephony.Sms.BODY,
                Telephony.Sms.DATE
        };
        final String sortOrder = Telephony.Sms.DATE + " desc limit 1";
        Cursor cursor = getContentResolver().query(Telephony.Sms.Inbox.CONTENT_URI, projection,
                null, null, sortOrder);
        if (cursor == null) {
            return;
        }
        if(cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(Telephony.Sms._ID));
            if (id != lastId) { // same with the last id
                lastId = id;

                String sender = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
                String body = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
                long date = cursor.getLong(cursor.getColumnIndex(Telephony.Sms.DATE));

                SmsMessageData smsMessageData = new SmsMessageData();
                smsMessageData.setSender(sender);
                smsMessageData.setBody(body);
                smsMessageData.setDate(date);

                Intent smsCodeHandleSvc = new Intent(this, SmsCodeHandleService.class);
                smsCodeHandleSvc.putExtra(SmsCodeHandleService.EXTRA_KEY_SMS_MESSAGE_DATA, smsMessageData);
                ContextCompat.startForegroundService(this, smsCodeHandleSvc);
            }
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
    }

    public static void startMe(Context context) {
        Intent service = new Intent(context, SmsObserveService.class);
        context.startService(service);
    }

    public static void stopMe(Context context) {
        Intent service = new Intent(context, SmsObserveService.class);
        context.stopService(service);
    }

}
