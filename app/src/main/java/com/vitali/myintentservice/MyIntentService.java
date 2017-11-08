package com.vitali.myintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Created by vitalip on 08/11/2017.
 */

public class MyIntentService extends IntentService
{
    private static final long DELAY_TIME = 1000;

    // Defines and instantiates an object for handling status updates.
    private BroadcastNotifier mBroadcastNotifier;

    public MyIntentService()
    {
        super(MyIntentService.class.getSimpleName());
        Logger.logDebug();
    }

    @Override
    public void onCreate()
    {
        Logger.logDebug();
        super.onCreate();
        mBroadcastNotifier = new BroadcastNotifier(this);
    }

    @Override
    public void onDestroy()
    {
        Logger.logDebug();
        mBroadcastNotifier = null;
        super.onDestroy();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId)
    {
        Logger.logDebug();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        Logger.logDebug();
        Bundle extras = intent.getExtras();

        if(null != extras)
        {
            int param = (int) extras.get("NAME");
            Logger.logDebug(AppConstants.DEBUG, param+"");
        }

        doSomeLongWork();
    }

    private void doSomeLongWork()
    {
        Logger.logDebug(AppConstants.DEBUG, "START");


        for (int i = 0; i < 4; i++)
        {
            try
            {
                @AppEnums.BroadcastState String state;
                switch (i)
                {
                    case 0:
                        state = AppEnums.STATE_1;
                        break;
                    case 1:
                        state = AppEnums.STATE_2;
                        break;
                    case 2:
                        state = AppEnums.STATE_3;
                        break;
                    case 3:
                        state = AppEnums.STATE_DEFAULT;
                        break;

                    default:
                        state = AppEnums.STATE_DEFAULT;
                        break;
                }

                mBroadcastNotifier.broadcastIntentWithState(state);
                Thread.sleep(DELAY_TIME);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }


        /*Intent localIntent = new Intent();
        localIntent.setAction(AppConstants.BROADCAST_ACTION);
        localIntent.addCategory(Intent.CATEGORY_DEFAULT);
        localIntent.putExtra("EXTRA_DATA", "Some_data");

        LocalBroadcastManager.getInstance(MyIntentService.this).sendBroadcast(localIntent);*/




        Logger.logDebug(AppConstants.DEBUG, "END");


    }
}
