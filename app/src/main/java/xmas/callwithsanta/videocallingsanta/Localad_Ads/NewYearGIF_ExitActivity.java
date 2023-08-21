package xmas.callwithsanta.videocallingsanta.Localad_Ads;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import xmas.callwithsanta.videocallingsanta.R;

public class NewYearGIF_ExitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thop_activity_exit);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finishAffinity();
                System.exit(0);
            }
        }, 1000);
    }
}