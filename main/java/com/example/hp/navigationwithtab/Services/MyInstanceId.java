package com.example.hp.navigationwithtab.Services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Mahesh on 7/17/2017.
 */

public class MyInstanceId extends FirebaseInstanceIdService {
    String InstanceId;
    @Override
    public void onTokenRefresh() {
        InstanceId= FirebaseInstanceId.getInstance().getToken();
        Log.e("TokenGenerated", "onTokenRefresh: "+InstanceId,null);
    }
}
