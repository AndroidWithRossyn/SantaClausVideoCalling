package xmas.callwithsanta.videocallingsanta;

import static xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_Splace_Activity.adsdata;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;

import xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_functions;

public class Reject_Video_CallActivity extends AppCompatActivity {
    long k = 0;
    drf l = new drf(this);
    MediaPlayer m;
    String n = "1";

    private Dialog adprogress;

    CardView q_native_banner;
    private InterstitialAd mInterstitialAd;
    private int admobinter;
    private View pos;

    public void onResume() {
        NewYearGIF_functions.callAutoQureka(this);
        super.onResume();
    }

    public void onPause() {
        NewYearGIF_functions.destroyThread();
        super.onPause();
    }

    // TODO: 11-09-2020  Admob Native Ads
    private void admob_native_banner() {
        try {

            MobileAds.initialize(this);
            AdLoader adLoader = new AdLoader.Builder(this, adsdata.get(0).getAdmobNativeid())
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            findViewById(R.id.admobsmallnative).setVisibility(View.VISIBLE);
                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().build();
                            TemplateView template = findViewById(R.id.admobsmallnative);
                            template.setStyles(styles);
                            template.setNativeAd(nativeAd);
                        }
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());

            /*AdLoader adLoader = new AdLoader.Builder(this, adsdata.get(0).getAdmobNativeid())
                    .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                        @Override
                        public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                            TemplateView template = findViewById(R.id.admobsmallnative);
                            template.setVisibility(View.VISIBLE);
                            template.setNativeAd(unifiedNativeAd);
                        }
                    })
                    .build();
            adLoader.loadAd(new AdRequest.Builder().build());*/
        } catch (Exception e) {
        }
    }

    private void Q_Native_banner() {
        q_native_banner.setVisibility(View.VISIBLE);
        q_native_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
                customTabsIntent.launchUrl(Reject_Video_CallActivity.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
            }
        });
    }

    private void admob_inter() {
        try {
            InterstitialAd.load(this, adsdata.get(0).getAdmobInterid(), new AdRequest.Builder().build(),
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            mInterstitialAd = interstitialAd;
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(Reject_Video_CallActivity.this);
                            }

                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    // Called when fullscreen content is dismissed.
                                    Log.d("TAG", "The ad was dismissed.");
                                    adprogress.dismiss();
                                    if (admobinter == 1) {
                                        k();
                                        startActivity(new Intent(pos.getContext(), Play_Video_Call_Activity.class));
                                        finish();
                                    }
                                    if (admobinter == 2) {
                                        a("Call rejected");
                                        k();
                                        finish();
                                    }
                                    if (admobinter == 3) {
                                        if (k + 2000 > System.currentTimeMillis()) {
                                            k();
                                            finish();
                                            startActivity(new Intent(Reject_Video_CallActivity.this, MainActivity.class));
                                        } else {
                                            Toast.makeText(getBaseContext(), "Call will decline if you press again", 0).show();
                                        }
                                        k = System.currentTimeMillis();
                                    }
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(AdError adError) {
                                    // Called when fullscreen content failed to show.
                                    Log.d("TAG", "The ad failed to show.");
                                    adprogress.dismiss();
                                    if (admobinter == 1) {
                                        k();
                                        startActivity(new Intent(pos.getContext(), Play_Video_Call_Activity.class));
                                        finish();
                                    }
                                    if (admobinter == 2) {
                                        a("Call rejected");
                                        k();
                                        finish();
                                    }
                                    if (admobinter == 3) {
                                        if (k + 2000 > System.currentTimeMillis()) {
                                            k();
                                            finish();
                                            startActivity(new Intent(Reject_Video_CallActivity.this, MainActivity.class));
                                        } else {
                                            Toast.makeText(getBaseContext(), "Call will decline if you press again", 0).show();
                                        }
                                        k = System.currentTimeMillis();
                                    }
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    // Called when fullscreen content is shown.
                                    // Make sure to set your reference to null so you don't
                                    // show it a second time.
                                    mInterstitialAd = null;
                                    Log.d("TAG", "The ad was shown.");
                                }
                            });
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
//                            Log.i(TAG, loadAdError.getMessage());
                            adprogress.dismiss();
                            if (admobinter == 1) {
                                k();
                                startActivity(new Intent(pos.getContext(), Play_Video_Call_Activity.class));
                                finish();
                            }
                            if (admobinter == 2) {
                                a("Call rejected");
                                k();
                                finish();
                            }
                            if (admobinter == 3) {
                                if (k + 2000 > System.currentTimeMillis()) {
                                    k();
                                    finish();
                                    startActivity(new Intent(Reject_Video_CallActivity.this, MainActivity.class));
                                } else {
                                    Toast.makeText(getBaseContext(), "Call will decline if you press again", 0).show();
                                }
                                k = System.currentTimeMillis();
                            }

                        }
                    });
        } catch (Exception e) {

        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_reject__video__call);
        getWindow().setFlags(1024, 1024);

        q_native_banner = (CardView) findViewById(R.id.q_native_banner);

        NewYearGIF_functions.iplscr_flag = true;

        //todo Ad Loading Dialog
        adprogress = new Dialog(Reject_Video_CallActivity.this, R.style.Custom);
        adprogress.requestWindowFeature(1);
        adprogress.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        adprogress.setCancelable(false);
        adprogress.setContentView(R.layout.z1_ads_loading_dialog_inter);

        if (adsdata != null && adsdata.size() > 0) {

            // TODO: 11-09-2020  Native Ads
            switch (adsdata.get(0).getCheckAdNativeBanner()) {
                case "admob":
                    admob_native_banner();
                    break;
                case "qureka":
                    Q_Native_banner();
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(this.l.b());
        this.n = sb.toString();
        l();
        this.m = MediaPlayer.create(this, R.raw.fb_messenger_tone);
        this.m.start();
        this.m.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                Reject_Video_CallActivity.this.m.start();
                Reject_Video_CallActivity.this.m();
            }
        });
        m();
    }

    public void k() {
        MediaPlayer mediaPlayer = this.m;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.m.stop();
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        Window window = getWindow();
        window.addFlags(4194304);
        window.addFlags(524288);
        window.addFlags(2097152);
    }

    public void l() {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.santa1);
        Bitmap decodeResource2 = BitmapFactory.decodeResource(getResources(), R.drawable.santa2);
        Bitmap decodeResource3 = BitmapFactory.decodeResource(getResources(), R.drawable.santa3);
        Bitmap decodeResource4 = BitmapFactory.decodeResource(getResources(), R.drawable.santa4);
        Bitmap decodeResource5 = BitmapFactory.decodeResource(getResources(), R.drawable.santa5);
        ImageView imageView = (ImageView) findViewById(R.id.ImageView02);
        TextView textView = (TextView) findViewById(R.id.textView1);
        if (this.n.equalsIgnoreCase("1")) {
            textView.setText("Father Christmas");
            imageView.setImageBitmap(a(decodeResource, 100));
        } else if (this.n.equalsIgnoreCase("2")) {
            textView.setText("Jultomten");
            imageView.setImageBitmap(a(decodeResource2, 100));
        } else if (this.n.equalsIgnoreCase("3")) {
            textView.setText("Kerstman");
            imageView.setImageBitmap(a(decodeResource3, 100));
        } else if (this.n.equalsIgnoreCase("4")) {
            textView.setText("Papai Noel");
            imageView.setImageBitmap(a(decodeResource4, 100));
        } else if (this.n.equalsIgnoreCase("5")) {
            textView.setText("Dun Che Lao Ren");
            imageView.setImageBitmap(a(decodeResource5, 100));
        }
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        if (!(bitmap.getWidth() == i && bitmap.getHeight() == i)) {
            bitmap = Bitmap.createScaledBitmap(bitmap, i, i, false);
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(((float) (bitmap.getWidth() / 2)) + 0.7f, ((float) (bitmap.getHeight() / 2)) + 0.7f, ((float) (bitmap.getWidth() / 2)) + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public void accept_call(View view) {

        pos = view;
        if (adsdata != null && adsdata.size() > 0) {
            admobinter = 1;
            if (adsdata.get(0).getCheckadAcceptcallInter().equals("admob")) {
                adprogress.show();
                admob_inter();
            } else if (adsdata.get(0).getCheckadAcceptcallInter().equals("off")) {
                k();
                startActivity(new Intent(view.getContext(), Play_Video_Call_Activity.class));
                finish();
            } else {
                k();
                startActivity(new Intent(view.getContext(), Play_Video_Call_Activity.class));
                finish();
            }
        } else {
            k();
            startActivity(new Intent(view.getContext(), Play_Video_Call_Activity.class));
            finish();
        }
    }

    public void reject_call(View view) {
        if (adsdata != null && adsdata.size() > 0) {
            admobinter = 2;
            if (adsdata.get(0).getCheckadRejectcallInter().equals("admob")) {
                adprogress.show();
                admob_inter();
            } else if (adsdata.get(0).getCheckadRejectcallInter().equals("off")) {
                a("Call rejected");
                k();
                finish();
            } else {
                a("Call rejected");
                k();
                finish();
            }
        } else {
            a("Call rejected");
            k();
            finish();
        }
    }

    @SuppressLint("WrongConstant")
    public void onBackPressed() {
        if (adsdata != null && adsdata.size() > 0) {
            admobinter = 3;
            if (adsdata.get(0).getCheckadRejectcallbackpressInter().equals("admob")) {
                adprogress.show();
                admob_inter();
            } else if (adsdata.get(0).getCheckadRejectcallbackpressInter().equals("off")) {
                if (this.k + 2000 > System.currentTimeMillis()) {
                    k();
                    finish();
                    startActivity(new Intent(Reject_Video_CallActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(getBaseContext(), "Call will decline if you press again", 0).show();
                }
                this.k = System.currentTimeMillis();
            } else {
                if (this.k + 2000 > System.currentTimeMillis()) {
                    k();
                    finish();
                    super.onBackPressed();
                } else {
                    Toast.makeText(getBaseContext(), "Call will decline if you press again", 0).show();
                }
                this.k = System.currentTimeMillis();
            }
        } else {
            if (this.k + 2000 > System.currentTimeMillis()) {
                k();
                finish();
                super.onBackPressed();
            } else {
                Toast.makeText(getBaseContext(), "Call will decline if you press again", 0).show();
            }
            this.k = System.currentTimeMillis();
        }
    }

    @SuppressLint("WrongConstant")
    public void a(String str) {
        Toast.makeText(getApplicationContext(), str, 1).show();
    }
}
