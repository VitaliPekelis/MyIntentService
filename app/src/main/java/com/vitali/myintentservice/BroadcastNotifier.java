package com.vitali.myintentservice;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by vitalip on 08/11/2017.
 */

public class BroadcastNotifier
{
    private LocalBroadcastManager mManager;

    /**
     * Creates a BroadcastNotifier containing an instance of LocalBroadcastManager.
     * LocalBroadcastManager is more efficient than BroadcastManager; because it only
     * broadcasts to components within the app, it doesn't have to do parceling and so forth.
     *
     * @param context a Context from which to get the LocalBroadcastManager
     */
    public BroadcastNotifier(Context context)
    {
        this.mManager = LocalBroadcastManager.getInstance(context);
    }

    /**
     *
     * Uses LocalBroadcastManager to send an {@link Intent} containing {@code status}. The
     * {@link Intent} has the action {@code BROADCAST_ACTION} and the category {@code DEFAULT}.
     *
     * @param status {@link AppEnums.BroadcastState} denoting a work request status
     */
    public void broadcastIntentWithState(@AppEnums.BroadcastState String status) {

        Intent localIntent = new Intent();

        // The Intent contains the custom broadcast action for this app
        localIntent.setAction(AppConstants.BROADCAST_ACTION);

        // Puts the status into the String
        localIntent.putExtra(AppConstants.EXTENDED_DATA_STATUS, status);
        localIntent.addCategory(Intent.CATEGORY_DEFAULT);

        // Broadcasts the Intent
        mManager.sendBroadcast(localIntent);

    }


}
