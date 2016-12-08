package test.test.com.test;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private Button mB;
    private Button mButton2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


//        高版本自动接听电话方法：
//        try {
//            Method method = Class.forName("android.os.ServiceManager")
//                    .getMethod("getService", String.class);
//
//            IBinder binder = (IBinder) method.invoke(null, new Object[]{TELEPHONY_SERVICE});
//            ITelephony telephony = ITelephony.Stub.asInterface(binder);
//            telephony.answerRingingCall();
//        } catch (NoSuchMethodException e) {
//            Log.d("Sandy", "", e);
//        } catch (ClassNotFoundException e) {
//            Log.d("Sandy", "", e);
//        }catch (Exception e) {
//            Log.d("Sandy", "", e);
//            try{
//                Log.e("Sandy", "for version 4.1 or larger");
//                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
//                KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK);
//                intent.putExtra("android.intent.extra.KEY_EVENT",keyEvent);
//
//                sendOrderedBroadcast(intent,"android.permission.CALL_PRIVILEGED");
//            } catch (Exception e2) {
//                Log.d("Sandy", "", e2);
//                Intent meidaButtonIntent = new Intent(Intent.ACTION_MEDIA_BUTTON);
//                KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK);
//                meidaButtonIntent.putExtra(Intent.EXTRA_KEY_EVENT,keyEvent);
//                sendOrderedBroadcast(meidaButtonIntent, null);
//            }
//        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        mB = (Button) findViewById(R.id.button);
        mB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                release();
            }
        });

        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //锁屏、唤醒相关
    private KeyguardManager km;
    private KeyguardManager.KeyguardLock kl;
    private PowerManager pm;
    private PowerManager.WakeLock wl;
    String Tag = "TEST";


    private void release() {

        Log.d(Tag, "releasereleaserelease");
        //获取电源管理器对象
//        pm=(PowerManager) getSystemService(Context.POWER_SERVICE);
//
//        //获取PowerManager.WakeLock对象，后面的参数|表示同时传入两个值，最后的是调试用的Tag
//        wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
//
////            //点亮屏幕
//            wl.acquire();
//        //释放wakeLock，关灯
//        wl.release();
//        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        pm.goToSleep(SystemClock.uptimeMillis());


//        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);

//        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "TAG");
//        wakeLock.acquire(); //做我们的工作，在这个阶段，我们的屏幕会持续点亮 //释放锁，屏幕熄灭。
//        wl.release();
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);

        ComponentName componentName = new ComponentName(this,MyAdmin.class);

        if(!devicePolicyManager.isAdminActive(componentName)){
            Intent intent =
                    new  Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "----这是一键锁屏激活界面-----");
            startActivityForResult(intent, 0);
        }
        devicePolicyManager.lockNow();



    }

}
