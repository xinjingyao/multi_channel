package com.example.multi_channel;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = findViewById(R.id.tv_content);

        setPkgContent();

        String url = BuildConfig.BASE_API;
//        String channel = BuildConfig.CHANNEL;
        boolean logSwitch = BuildConfig.LOG_SWITCH;
    }

    private void setPkgContent() {
        try {
            String packageName = this.getPackageName();
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(packageName, 0);
            String versionName = packageInfo.versionName;
            int versionCode = packageInfo.versionCode;

            ApplicationInfo appInfo = this.getPackageManager()
                    .getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String channel = appInfo.metaData.getString("CHANNEL");

            String content = "packageName:" + packageName + "\n"
                    + "versionName:" + versionName + "\n"
                    + "versionCode:" + versionCode + "\n"
                    + "channel:" + channel;
            tvContent.setText(content);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
