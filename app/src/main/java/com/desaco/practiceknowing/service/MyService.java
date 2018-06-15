package com.desaco.practiceknowing.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by desaco on 2018/6/15.
 */

public class MyService extends Service {
    private static final String TAG = "MyService";

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return myBinder;
    }

    Binder myBinder = new Binder() {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {//TODO

//                case RemoteProtocal.CODE_ADD: {
//                    int a = data.readInt();
//                    int b = data.readInt();
//                    int result = add(a, b);
//                    reply.writeInt(result);
//                    return true;
//                }
            }
            return super.onTransact(code, data, reply, flags);
        }
    };

    public int add(int a, int b) {
        return a + b;
    }
}
