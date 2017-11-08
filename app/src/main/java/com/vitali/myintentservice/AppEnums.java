package com.vitali.myintentservice;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by vitalip on 08/11/2017.
 */

public abstract class AppEnums
{
    final static String STATE_1 = "STATE_1";
    final static String STATE_2 = "STATE_2";
    final static String STATE_3 = "STATE_3";
    final static String STATE_DEFAULT = "STATE_DEFAULT";

    @Retention(RetentionPolicy.CLASS)
    @StringDef({STATE_1, STATE_2, STATE_3, STATE_DEFAULT})
    @interface BroadcastState{}


    final static int STATE_INT_1 = 1;
    final static int STATE_INT_2 = 2;
    final static int STATE_INT_3 = 3;

    @IntDef({STATE_INT_1, STATE_INT_2, STATE_INT_3})
    @interface BroadcastIntState{}
}
