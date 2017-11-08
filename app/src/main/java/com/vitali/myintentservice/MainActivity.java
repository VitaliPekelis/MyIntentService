package com.vitali.myintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private static final long DELAY_TIME = 3000;
    private MyBroadcastRecevier mRecevier;
    private TextView            mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initReceiver();
        initUi();
        doSomeWork();
    }

    private void initUi()
    {
        mTextView = findViewById(R.id.textView);
    }

    @Override
    protected void onDestroy()
    {
        if(mRecevier!=null)
        {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mRecevier);
            mRecevier = null;
        }
        super.onDestroy();
    }

    private void initReceiver()
    {
        IntentFilter intentFilter = new IntentFilter(AppConstants.BROADCAST_ACTION);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        mRecevier = new MyBroadcastRecevier();

        LocalBroadcastManager.getInstance(this).registerReceiver(mRecevier, intentFilter);

    }

    private void doSomeWork()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Logger.logDebug();
                startServiceIntent();
            }

        },DELAY_TIME);
    }

    private void startServiceIntent()
    {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("NAME", 123);
        startService(intent);
    }

    //------------------------------------------------------------------------------
    // class
    //------------------------------------------------------------------------------
    private class MyBroadcastRecevier extends BroadcastReceiver
    {

        /**
         *
         * This method is called by the system when a broadcast Intent is matched by this class'
         * intent filters
         *
         * @param context An Android context
         * @param intent The incoming broadcast Intent
         */
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Logger.logDebug();
            if(intent!=null)
            {
                Bundle extras = intent.getExtras();
                if(extras!=null)
                {
                    /*String data = (String) extras.get("EXTRA_DATA");*/

                    switch (extras.getString(AppConstants.EXTENDED_DATA_STATUS, AppEnums.STATE_DEFAULT))
                    {
                        case AppEnums.STATE_1:
                            Logger.logDebug(AppConstants.DEBUG, AppEnums.STATE_1);
                            mTextView.setText("1");
                            break;
                        case AppEnums.STATE_2:
                            Logger.logDebug(AppConstants.DEBUG, AppEnums.STATE_2);
                            mTextView.setText("2");
                            break;
                        case AppEnums.STATE_3:
                            mTextView.setText("3");
                            Logger.logDebug(AppConstants.DEBUG, AppEnums.STATE_3);
                            break;
                        case AppEnums.STATE_DEFAULT:
                            mTextView.setText("4");
                            Logger.logDebug(AppConstants.DEBUG, AppEnums.STATE_DEFAULT);
                            break;
                        default:
                            mTextView.setText("-1");
                            break;
                    }

                }
            }

        }
    }
}
