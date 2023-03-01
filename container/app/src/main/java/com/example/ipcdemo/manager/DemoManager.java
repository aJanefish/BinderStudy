package com.example.ipcdemo.manager;

import static com.example.ipc.IPCConstant.APK_TEST_ACTION;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.ipc.IApkInstallManager;
import com.zy.zlog.ZLog;


/**
 * 通过Application连接服务
 */
public class DemoManager {

    private static final String TAG = "DemoManager";
    private static DemoManager demoManager = new DemoManager();
    private static int values = 1;
    private Context mContext;

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
            ZLog.d(TAG, "onServiceConnected:" + iApkInstallManager + " " + service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO: 2020/7/31 服务端断开 UI线程中回调
            ZLog.d(TAG, "onServiceDisconnected:" + name + " " + Thread.currentThread());
            iApkInstallManager = null;
            reBindService();
        }
    };

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

    private DemoManager() {
    }

    public static DemoManager getDemoManager() {
        return demoManager;
    }

    public void init(Context context) {
        mContext = context;
    }

    public void bindDiyService() {
        Intent intent = new Intent(APK_TEST_ACTION);
        //Caused by: java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.test.zy.APK_INSTALL_ACTION }
        //设置服务端的包名
        intent.setPackage("com.example.binderservice");
        mContext.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void reBindService() {
        bindDiyService();
    }

    public static int getValues() {
        return values;
    }

    public static void setValues(int values) {
        DemoManager.values = values;
    }
}
