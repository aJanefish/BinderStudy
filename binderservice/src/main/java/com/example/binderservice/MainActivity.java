package com.example.binderservice;

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

import com.example.ipc.IApkInstallManager;
import com.example.ipc.util.Constant;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private IApkInstallManager iApkInstallManager;
    private final static String TAG = Constant.PRE_TAG + "MainActivity";
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
            Log.d(TAG, "onServiceConnected:" + iApkInstallManager + " " + service + " identityHashCode:" + System.identityHashCode(service));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO: 2020/7/31 服务端断开 UI线程中回调
            Log.d(TAG, "onServiceDisconnected:" + name + " " + Thread.currentThread());
            iApkInstallManager = null;
            reBindService();
        }
    };

    public void bindDiyService(View view) {
        Intent intent = new Intent(Constant.APK_TEST_ACTION);
        //Caused by: java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.test.zy.APK_INSTALL_ACTION }
        //设置服务端的包名
        intent.setPackage("com.example.binderservice");

        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

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

}
