package com.example.ipcdemo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ipc.ApkInfo;
import com.example.ipc.IApkInstallListener;
import com.example.ipc.IApkInstallManager;
import com.example.ipc.util.Constant;
import com.example.ipcdemo.R;
import com.example.ipcdemo.manager.DemoManager;
import com.example.ipcdemo.util.Utils;

import java.util.Random;

public class IPCDemoActivity extends AppCompatActivity {

    private static final String TAG = Constant.PRE_TAG + "MainActivity";
    private TextView title;
    IApkInstallManager iApkInstallManager = null;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iApkInstallManager = IApkInstallManager.Stub.asInterface(service);
            try {
                //设置死亡代理
                service.linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "onServiceConnected:" + iApkInstallManager + " " + service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO: 2020/7/31 服务端断开 UI线程中回调
            Log.d(TAG, "onServiceDisconnected:" + name + " " + Thread.currentThread());
            iApkInstallManager = null;
            reBindService();
        }
    };

    private void reBindService() {
        Intent intent = new Intent(Constant.APK_TEST_ACTION);
        //设置服务端的包名
        //Caused by: java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.test.zy.APK_INSTALL_ACTION }
        intent.setPackage("com.example.binderservice");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            // FIXME: 2020/8/1 服务断开 binder线程中调用
            Log.d(TAG, "binderDied:" + iApkInstallManager + " " + Thread.currentThread());
            if (iApkInstallManager == null) {
                return;
            }
            try {
                iApkInstallManager.asBinder().linkToDeath(deathRecipient, 0);
                iApkInstallManager = null;
                // TODO: 2020/7/31 重新绑定远程服务
                reBindService();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_demo);
        title = findViewById(R.id.main_title);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DemoManager.setValues(2);
        title.setText("sss:" + DemoManager.getValues());
    }

    public void gotoOne(View view) {
        startActivity(new Intent(this, OneActivity.class));
    }

    public void gotoTwo(View view) {
        startActivity(new Intent(this, TwoActivity.class));
    }

    public void showTips(View view) {
        Utils.showProcess();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Utils.showProcess();
            }
        }).start();
    }

    public void getFlag(View view) {
        try {
            int flag = iApkInstallManager.getFlag();
            title.append("" + flag + "\n");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setFlag(View view) {
        try {
            iApkInstallManager.setFlag(new Random().nextInt(100));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void bindDiyService(View view) {
        Intent intent = new Intent(Constant.APK_TEST_ACTION);
        //Caused by: java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.test.zy.APK_INSTALL_ACTION }
        //设置服务端的包名
        intent.setPackage("com.example.binderservice");

        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void unbindDiyService(View view) {
        unbindService(serviceConnection);
    }

    public void checkPermission(View view) {

        try {
            boolean checkPermission = iApkInstallManager.checkPermission();
            title.append("检查apk安装权限:" + checkPermission + "\n");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void startSilentInstall(View view) {
        try {
            ApkInfo apkInfo = new ApkInfo("com.tentent.ig", "/sdcard/Android/data/com.example.ipcdemo/test.Apk");
            Log.d(TAG, "startSilentInstall:" + apkInfo);
            iApkInstallManager.startSilentInstall(apkInfo);
            titleAppend("startSilentInstall");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void startCommonInstall(View view) {
        try {
            ApkInfo apkInfo = new ApkInfo("com.tentent.ig", "/sdcard/Android/data/com.example.ipcdemo/test.Apk");
            Log.d(TAG, "startCommonInstall:" + apkInfo);
            iApkInstallManager.startCommonInstall(apkInfo);
            titleAppend("startCommonInstall");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

//    private IApkInstallListener iApkInstallListener = new IApkInstallListener() {
//        @Override
//        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
//
//        }
//
//        @Override
//        public void onStatusChanged(int status, String msg) throws RemoteException {
//
//        }
//
//        @Override
//        public IBinder asBinder() {
//            return null;
//        }
//    }

    private IApkInstallListener.Stub listener2 = new IApkInstallListener.Stub() {
        @Override
        public void onStatusChanged(int status, String msg) throws RemoteException {
            Log.d(TAG, "onStatusChanged 1:" + Thread.currentThread());
            Log.d(TAG, "onStatusChanged 1: status=" + status + " msg=" + msg);
        }
    };

    private IApkInstallListener.Stub listener1 = new IApkInstallListener.Stub() {
        @Override
        public void onStatusChanged(int status, String msg) throws RemoteException {
            Log.d(TAG, "onStatusChanged 2:" + Thread.currentThread() + " " + Log.getStackTraceString(new Throwable()));
            Log.d(TAG, "onStatusChanged 2: status=" + status + " msg=" + msg);
        }
    };

    public void registerListener(View view) {
        try {
            iApkInstallManager.registerListener(listener1);
            //iApkInstallManager.registerListener(listener2);
            Log.d(TAG, "registerListener: listener1=" + listener1 + " " + listener1.asBinder());
            titleAppend("registerListener");
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            e.printStackTrace();
        }
    }

    public void unregisterListener(View view) {
        try {
            iApkInstallManager.unregisterListener(listener1);
            //iApkInstallManager.unregisterListener(listener2);
            Log.d(TAG, "unregisterListener: listener1=" + listener1);

            titleAppend("unregisterListener");
        } catch (Exception e) {
            Log.d(TAG, e.toString());
            e.printStackTrace();
        }
    }

    public void startSilentInstallInChildThread(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ApkInfo apkInfo = new ApkInfo("com.tentent.ig", "/sdcard/Android/data/com.example.ipcdemo/test.Apk");
                    Log.d(TAG, "startSilentInstall:" + apkInfo);
                    iApkInstallManager.startSilentInstall(apkInfo);
                    titleAppend("startSilentInstallInChildThread");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void startCommonInstallInChildThread(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ApkInfo apkInfo = new ApkInfo("com.tentent.ig", "/sdcard/Android/data/com.example.ipcdemo/test.Apk");
                    Log.d(TAG, "startSilentInstall:" + apkInfo);
                    iApkInstallManager.startCommonInstall(apkInfo);
                    titleAppend("startCommonInstallInChildThread");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void titleAppend(final String values) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                title.append(values + "\n");
            }
        });
    }
}