package xmas.callwithsanta.videocallingsanta.Localad_Ads;

import static xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_Splace_Activity.adsdata;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import xmas.callwithsanta.videocallingsanta.R;
import com.squareup.picasso.Picasso;


public class NewYearGIF_AutoQurekaActivity extends AppCompatActivity {

    ImageView iv_main;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.thop_activity_auto_qureka);

        ImageView iv_main = null;
        iv_main = findViewById(R.id.iv_main);

        if (adsdata != null && adsdata.size() > 0) {
            try {
                Picasso.get()
                        .load(adsdata.get(0).getQurekaImageUrl())
                        .into(iv_main);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ((ImageView) findViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NewYearGIF_functions.iplscr_flag = true;
                NewYearGIF_AutoQurekaActivity.this.finish();
            }
        });

        ((ImageView) findViewById(R.id.iv_main)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
                    customTabsIntent.launchUrl(NewYearGIF_AutoQurekaActivity.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        NewYearGIF_functions.iplscr_flag = true;
        finish();
    }
}
