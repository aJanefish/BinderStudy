package com.example.binderservice;

import static com.example.ipc.IPCConstant.APK_TEST_ACTION;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ipc.ApkInfo;
import com.example.ipc.IApkInstallListener;
import com.example.ipc.IApkInstallManager;

import com.zy.zlog.ZLog;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private IApkInstallManager iApkInstallManager;
    private final static String TAG = "MainActivity";
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.service_main_title);
        findViewById(R.id.service_main_button_get_flag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFlag(v);
            }
        });

        findViewById(R.id.service_main_button_set_flag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFlag(v);
            }
        });
    }

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iApkInstallManager = IApkInstallManager.Stub.asInterface(service);
            try {
                //设置死亡代理
                service.linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            ZLog.d(TAG, "onServiceConnected:" + iApkInstallManager + " " + service + " identityHashCode:" + System.identityHashCode(service));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO: 2020/7/31 服务端断开 UI线程中回调
            ZLog.d(TAG, "onServiceDisconnected:" + name + " " + Thread.currentThread());
            iApkInstallManager = null;
            reBindService();
        }
    };

    public void bindDiyService(View view) {
        Intent intent = new Intent(APK_TEST_ACTION);
        //Caused by: java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.test.zy.APK_INSTALL_ACTION }
        //设置服务端的包名
        intent.setPackage("com.example.binderservice");

        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private void reBindService() {
        Intent intent = new Intent(APK_TEST_ACTION);
        //设置服务端的包名
        //Caused by: java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.test.zy.APK_INSTALL_ACTION }
        intent.setPackage("com.example.binderservice");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            // FIXME: 2020/8/1 服务断开 binder线程中调用
            ZLog.d(TAG, "binderDied:" + iApkInstallManager + " " + Thread.currentThread());
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

    public void unbindDiyService(View view) {
        unbindService(serviceConnection);
    }

    public void getFlag(View view) {
        try {
            int flag = iApkInstallManager.getFlag();
            title.append("\n" + flag);
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

    private IApkInstallListener.Stub listener1 = new IApkInstallListener.Stub() {
        @Override
        public void onStatusChanged(int status, String msg) throws RemoteException {
            ZLog.d(TAG, "onStatusChanged 1:" + Thread.currentThread() + " " + ZLog.getStackTraceString(new Throwable()));
            ZLog.d(TAG, "onStatusChanged 1: status=" + status + " msg=" + msg);
        }
    };

    public void registerListener(View view) {
        try {
            iApkInstallManager.registerListener(listener1);
            ZLog.d(TAG, "registerListener: listener1=" + listener1 + " " + listener1.asBinder());
        } catch (Exception e) {
            ZLog.d(TAG, e.toString());
            e.printStackTrace();
        }
    }

    public void unregisterListener(View view) {
        try {
            iApkInstallManager.unregisterListener(listener1);
            ZLog.d(TAG, "unregisterListener: listener1=" + listener1);
        } catch (Exception e) {
            ZLog.d(TAG, e.toString());
            e.printStackTrace();
        }
    }

    public void startSilentInstallInChildThread(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ApkInfo apkInfo = new ApkInfo("com.tentent.ig", "/sdcard/Android/data/com.example.ipcdemo/test.Apk");
                    ZLog.d(TAG, "startSilentInstall:" + apkInfo);
                    iApkInstallManager.startSilentInstall(apkInfo);
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
                    ZLog.d(TAG, "startSilentInstall:" + apkInfo);
                    iApkInstallManager.startCommonInstall(apkInfo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void startSilentInstall(View view) {
        try {
            ApkInfo apkInfo = new ApkInfo("com.tentent.ig", "/sdcard/Android/data/com.example.ipcdemo/test.Apk");
            ZLog.d(TAG, "startSilentInstall:" + apkInfo);
            iApkInstallManager.startSilentInstall(apkInfo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void startCommonInstall(View view) {
        try {
            ApkInfo apkInfo = new ApkInfo("com.tentent.ig", "/sdcard/Android/data/com.example.ipcdemo/test.Apk");
            ZLog.d(TAG, "startCommonInstall:" + apkInfo);
            iApkInstallManager.startCommonInstall(apkInfo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
