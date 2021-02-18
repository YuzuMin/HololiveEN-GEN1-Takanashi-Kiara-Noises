package com.yuzumin.kiaranoise;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class About extends AppCompatActivity {

    Button download_kiara;
    Button download_miko;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        download_kiara=findViewById(R.id.button);
        download_kiara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.yuzumin.kiaranoise");
                //startActivity(launchIntent);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.yuzumin.guranoises"));
                startActivity(intent);
            }
        });

        download_miko=findViewById(R.id.button2);
        download_miko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.yuzumin.kiaranoise");
                //startActivity(launchIntent);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.yuzumin.mikonoises"));
                startActivity(intent);
            }
        });
    }
}