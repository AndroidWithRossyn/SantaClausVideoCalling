package xmas.callwithsanta.videocallingsanta;

import static xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_Splace_Activity.adsdata;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
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
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_Splace_Activity;
import xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_functions;

public class StratestActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public TextView G;
    /* access modifiers changed from: private */
    public TextView H;
    int I = 2;
    private ImageView ImageView1;
    private ImageView ImageView2;
    private ImageView ImageView3;
    private ImageView ImageView4;
    private ImageView ImageView5;
    drg J = drg.a();
    String K = "not_select";
    drf L = new drf(this);
    int M = 1;
    protected String N = "nothing";
    private CountDownTimer O;
    int P = 13;
    int Q = 32;
    int R123 = 33;
    private TextView TextView01;
    private TextView TextView02;
    private TextView TextView03;
    private TextView TextView04;
    private TextView TextView05;
    private RelativeLayout count_down_layout;

    private TextView makevideocall;
    private RelativeLayout relmin1;
    private RelativeLayout relmin10;
    private RelativeLayout relmin10s;
    private RelativeLayout relmin15;
    private RelativeLayout relmin20;
    private RelativeLayout relmin30;
    private RelativeLayout relmin30s;
    private RelativeLayout relmin5;
    private RelativeLayout relminclock;
    private RelativeLayout relminnow;
    private View tempview;

    private Dialog adprogress;

    CardView q_native, q_native_banner;
    private InterstitialAd mInterstitialAd;
    private int admobinter;

    private void admob_inter() {
        try {
            InterstitialAd.load(this, adsdata.get(0).getAdmobInterid(), new AdRequest.Builder().build(),
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            mInterstitialAd = interstitialAd;
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(StratestActivity.this);
                            }
                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    // Called when fullscreen content is dismissed.
                                    Log.d("TAG", "The ad was dismissed.");
                                    adprogress.dismiss();
                                    if (admobinter == 1) {
                                        tempview.startAnimation(AnimationUtils.loadAnimation(StratestActivity.this.getApplicationContext(), R.anim.viewpush));
                                        StratestActivity.this.k();
                                        StratestActivity.this.m();
                                    }
                                    if (admobinter == 2) {
                                        startActivity(new Intent(StratestActivity.this, MainActivity.class));
                                    }
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(AdError adError) {
                                    // Called when fullscreen content failed to show.
                                    Log.d("TAG", "The ad failed to show.");
                                    adprogress.dismiss();
                                    if (admobinter == 1) {
                                        tempview.startAnimation(AnimationUtils.loadAnimation(StratestActivity.this.getApplicationContext(), R.anim.viewpush));
                                        StratestActivity.this.k();
                                        StratestActivity.this.m();
                                    }
                                    if (admobinter == 2) {
                                        startActivity(new Intent(StratestActivity.this, MainActivity.class));
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
                                tempview.startAnimation(AnimationUtils.loadAnimation(StratestActivity.this.getApplicationContext(), R.anim.viewpush));
                                StratestActivity.this.k();
                                StratestActivity.this.m();
                            }
                            if (admobinter == 2) {
                                startActivity(new Intent(StratestActivity.this, MainActivity.class));
                            }

                        }
                    });
        } catch (Exception e) {

        }
    }

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
                customTabsIntent.launchUrl(StratestActivity.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
            }
        });
    }


    // TODO: 08-Oct-20  admob Native
    private void admob_native() {
        try {
            MobileAds.initialize(this);
            AdLoader adLoader = new AdLoader.Builder(this, NewYearGIF_Splace_Activity.adsdata.get(0).getAdmobNativeid())
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            findViewById(R.id.admobmediumnative).setVisibility(View.VISIBLE);
                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().build();
                            TemplateView template = findViewById(R.id.admobmediumnative);
                            template.setStyles(styles);
                            template.setNativeAd(nativeAd);
                        }
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
        }
    }

    private void Q_Native() {
        q_native.setVisibility(View.VISIBLE);
        q_native.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
                customTabsIntent.launchUrl(StratestActivity.this, Uri.parse(adsdata.get(0).getQurekaUrl()));
            }
        });
    }

    public void onBackPressed() {
        if (adsdata != null && adsdata.size() > 0) {
            admobinter = 2;
            if (adsdata.get(0).getCheckadStratestbackpressInter().equals("admob")) {
                adprogress.show();
                admob_inter();
            } else if (adsdata.get(0).getCheckadStratestbackpressInter().equals("off")) {
                startActivity(new Intent(StratestActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(StratestActivity.this, MainActivity.class));
            }
        } else {
            startActivity(new Intent(StratestActivity.this, MainActivity.class));
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_stratest);

        q_native = (CardView) findViewById(R.id.q_native);
        q_native_banner = (CardView) findViewById(R.id.q_native_banner);

        NewYearGIF_functions.iplscr_flag = true;

        //todo Ad Loading Dialog
        adprogress = new Dialog(StratestActivity.this, R.style.Custom);
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


            // TODO: 11-09-2020  Native Ads
            switch (adsdata.get(0).getCheckAdNative()) {
                case "admob":
                    admob_native();
                    break;
                case "qureka":
                    Q_Native();
                    break;
            }
        }

        bind();
    }

    private void bind() {
        this.ImageView1 = (ImageView) findViewById(R.id.ImageView1);
        this.ImageView2 = (ImageView) findViewById(R.id.ImageView2);
        this.ImageView3 = (ImageView) findViewById(R.id.ImageView3);
        this.ImageView4 = (ImageView) findViewById(R.id.ImageView4);
        this.ImageView5 = (ImageView) findViewById(R.id.ImageView5);
        this.TextView01 = (TextView) findViewById(R.id.TextView01);
        this.TextView02 = (TextView) findViewById(R.id.TextView02);
        this.TextView03 = (TextView) findViewById(R.id.TextView03);
        this.TextView04 = (TextView) findViewById(R.id.TextView04);
        this.TextView05 = (TextView) findViewById(R.id.TextView05);
        this.makevideocall = (TextView) findViewById(R.id.makevideocall);
        this.relminnow = (RelativeLayout) findViewById(R.id.relminnow);
        this.relmin10s = (RelativeLayout) findViewById(R.id.relmin10s);
        this.relmin30s = (RelativeLayout) findViewById(R.id.relmin30s);
        this.relmin1 = (RelativeLayout) findViewById(R.id.relmin1);
        this.relmin5 = (RelativeLayout) findViewById(R.id.relmin5);
        this.relmin10 = (RelativeLayout) findViewById(R.id.relmin10);
        this.relmin15 = (RelativeLayout) findViewById(R.id.relmin15);
        this.relmin20 = (RelativeLayout) findViewById(R.id.relmin20);
        this.relmin30 = (RelativeLayout) findViewById(R.id.relmin30);
        this.relminclock = (RelativeLayout) findViewById(R.id.relminclock);
        this.count_down_layout = (RelativeLayout) findViewById(R.id.count_down_layout);
        this.G = (TextView) findViewById(R.id.textView7);
        this.H = (TextView) findViewById(R.id.textView6);
        String str = "";
        this.G.setText(str);
        this.H.setText(str);
        o();
        this.J.d(RingtoneManager.getActualDefaultRingtoneUri(this, 1).toString());
        this.J.e("off");
        this.J.c("no_audio");
        this.J.b("lolipop");
        this.makevideocall.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                tempview = view;
                if (adsdata != null && adsdata.size() > 0) {
                    admobinter = 1;
                    if (adsdata.get(0).getCheckadVcallbtnInter().equals("admob")) {
                        adprogress.show();
                        admob_inter();
                    } else if (adsdata.get(0).getCheckadVcallbtnInter().equals("off")) {
                        view.startAnimation(AnimationUtils.loadAnimation(StratestActivity.this.getApplicationContext(), R.anim.viewpush));
                        StratestActivity.this.k();
                        StratestActivity.this.m();
                    } else {
                        view.startAnimation(AnimationUtils.loadAnimation(StratestActivity.this.getApplicationContext(), R.anim.viewpush));
                        StratestActivity.this.k();
                        StratestActivity.this.m();
                    }
                } else {
                    view.startAnimation(AnimationUtils.loadAnimation(StratestActivity.this.getApplicationContext(), R.anim.viewpush));
                    StratestActivity.this.k();
                    StratestActivity.this.m();
                }

            }
        });
        this.relminnow.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.a("now");
                StratestActivity.this.I = 2;
            }
        });
        this.relmin10s.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.a("sec10");
                StratestActivity.this.I = 10;
            }
        });
        this.relmin30s.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.a("sec30");
                StratestActivity.this.I = 30;
            }
        });
        this.relmin1.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.a("min1");
                StratestActivity.this.I = 60;
            }
        });
        this.relmin5.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.a("min5");
                StratestActivity.this.I = 300;
            }
        });
        this.relmin10.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.a("min10");
                StratestActivity.this.I = 600;
            }
        });
        this.relmin15.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.a("min15");
                StratestActivity.this.I = 900;
            }
        });
        this.relmin20.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.a("min20");
                StratestActivity.this.I = 1200;
            }
        });
        this.relmin30.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.a("min30");
                StratestActivity.this.I = 1800;
            }
        });
        this.relminclock.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.a("clock");
                Calendar instance = Calendar.getInstance();
                @SuppressLint("WrongConstant") TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), new OnTimeSetListener() {
                    public void onTimeSet(TimePicker timePicker, int i, int i2) {
                        int i3;
                        StratestActivity.this.P = i;
                        StratestActivity.this.Q = i2;
                        StratestActivity.this.R123 = new Date().getSeconds() + 3;
                        int i4 = 0;
                        if (StratestActivity.this.P > i) {
                            i4 = StratestActivity.this.P - i;
                            i3 = StratestActivity.this.Q;
                        } else {
                            i3 = StratestActivity.this.Q > 0 ? StratestActivity.this.Q - 0 : 0;
                        }
                        StratestActivity.this.I = (i4 * 3600) + (i3 * 60) + StratestActivity.this.R123;
                    }
                }, instance.get(11), instance.get(12), true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
        l();
        this.ImageView1.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.c(1);
            }
        });
        this.ImageView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.c(2);
            }
        });
        this.ImageView3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.c(3);
            }
        });
        this.ImageView4.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.c(4);
            }
        });
        this.ImageView5.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.c(5);
            }
        });
    }

    private void o() {
        this.ImageView1.setImageBitmap(a(BitmapFactory.decodeResource(getResources(), R.drawable.santa1), 100));
        this.ImageView2.setImageBitmap(a(BitmapFactory.decodeResource(getResources(), R.drawable.santa2), 100));
        this.ImageView3.setImageBitmap(a(BitmapFactory.decodeResource(getResources(), R.drawable.santa3), 100));
        this.ImageView4.setImageBitmap(a(BitmapFactory.decodeResource(getResources(), R.drawable.santa4), 100));
        this.ImageView5.setImageBitmap(a(BitmapFactory.decodeResource(getResources(), R.drawable.santa5), 100));
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

    @SuppressLint("ResourceType")
    public void c(final int i) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_show_and_set);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawableResource(17170445);
        TextView textView = (TextView) dialog.findViewById(R.id.textView1);
        TextView textView2 = (TextView) dialog.findViewById(R.id.TextView01);
        TextView textView3 = (TextView) dialog.findViewById(R.id.textView5);
        TextView textView4 = (TextView) dialog.findViewById(R.id.TextView02);
        textView4.setVisibility(8);
        textView4.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PlayVideoActivity.class);
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(i);
                intent.putExtra("video_number", sb.toString());
                StratestActivity.this.startActivity(intent);
                dialog.dismiss();
            }
        });
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StratestActivity.this.d(i);
                dialog.dismiss();
            }
        });
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void d(int i) {
        this.M = i;
        String str = "#000000";
        this.TextView01.setTextColor(Color.parseColor(str));
        this.TextView02.setTextColor(Color.parseColor(str));
        this.TextView03.setTextColor(Color.parseColor(str));
        this.TextView04.setTextColor(Color.parseColor(str));
        this.TextView05.setTextColor(Color.parseColor(str));
        String str2 = "#468ceb";
        if (i == 1) {
            this.TextView01.setTextColor(Color.parseColor(str2));
        } else if (i == 2) {
            this.TextView02.setTextColor(Color.parseColor(str2));
        } else if (i == 3) {
            this.TextView03.setTextColor(Color.parseColor(str2));
        } else if (i == 4) {
            this.TextView04.setTextColor(Color.parseColor(str2));
        } else if (i == 5) {
            this.TextView05.setTextColor(Color.parseColor(str2));
        }
        b("Video selected for call successfuly");
    }

    @SuppressLint("WrongConstant")
    public void b(String str) {
        Toast.makeText(getApplicationContext(), str, 1).show();
    }

    @SuppressLint("WrongConstant")
    public void k() {
        Calendar instance = Calendar.getInstance();
        instance.add(13, this.I);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        drg drg = this.J;
        StringBuilder sb = new StringBuilder();
        String str = "";
        sb.append(str);
        sb.append(this.K);
        drg.a(sb.toString());
        try {
            this.L.e();
            drf drf = this.L;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(this.M);
            drf.a(format, sb2.toString());
        } catch (SQLiteConstraintException e) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(e.getMessage());
            Log.e("CALL-DATE insertion ", sb3.toString());
        }
        this.N = NotificationCompat.CATEGORY_CALL;
        ((AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, instance.getTimeInMillis(), PendingIntent.getActivity(this, 12345, new Intent(this, Reject_Video_CallActivity.class), 268435456));
    }

    @SuppressLint("LongLogTag")
    public void l() {
        try {
            List a = this.L.a();
            if (this.J.b()) {
                a = this.L.b(this.J.c());
                this.J.a(false);
            }
            this.K = (String) a.get(7);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(e.getMessage());
            Log.e("Error loading data base ", sb.toString());
            this.K = "not_select";
        }
    }

    public void m() {
        int i = this.I * 1000;
        CountDownTimer countDownTimer = this.O;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer r2 = new CountDownTimer((long) i, 1000) {
            public void onTick(long j) {
                TextView access$000 = StratestActivity.this.H;
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(StratestActivity.this.a(j / 1000));
                access$000.setText(sb.toString());
                StratestActivity.this.G.setText("Getting Call After: ");
                if (StratestActivity.this.M == 1) {
                    StratestActivity.this.G.setText("Father Christmas will call you after: ");
                } else if (StratestActivity.this.M == 2) {
                    StratestActivity.this.G.setText("Jultomten will call you after: ");
                } else if (StratestActivity.this.M == 3) {
                    StratestActivity.this.G.setText("Kerstman will call you after: ");
                } else if (StratestActivity.this.M == 4) {
                    StratestActivity.this.G.setText("Papai Noel will call you after: ");
                } else if (StratestActivity.this.M == 5) {
                    StratestActivity.this.G.setText("Dun Che Lao Ren will call you after: ");
                }
            }

            public void onFinish() {
                String str = "";
                StratestActivity.this.H.setText(str);
                StratestActivity.this.G.setText(str);
            }
        };
        this.O = r2;
        this.O.start();
    }

    public String a(long j) {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(new Date(j * 1000));
    }

    public void a(String str) {
        char c;
        this.relminnow.setBackgroundResource(R.drawable.circle_shape);
        this.relmin10s.setBackgroundResource(R.drawable.circle_shape);
        this.relmin30s.setBackgroundResource(R.drawable.circle_shape);
        this.relmin1.setBackgroundResource(R.drawable.circle_shape);
        this.relmin5.setBackgroundResource(R.drawable.circle_shape);
        this.relmin10.setBackgroundResource(R.drawable.circle_shape);
        this.relmin15.setBackgroundResource(R.drawable.circle_shape);
        this.relmin20.setBackgroundResource(R.drawable.circle_shape);
        this.relmin30.setBackgroundResource(R.drawable.circle_shape);
        this.relminclock.setBackgroundResource(R.drawable.circle_shape);
        switch (str.hashCode()) {
            case 109270:
                if (str.equals("now")) {
                    c = 0;
                    break;
                }
            case 3351583:
                if (str.equals("min1")) {
                    c = 3;
                    break;
                }
            case 3351587:
                if (str.equals("min5")) {
                    c = 4;
                    break;
                }
            case 94755854:
                if (str.equals("clock")) {
                    c = 9;
                    break;
                }
            case 103899121:
                if (str.equals("min10")) {
                    c = 5;
                    break;
                }
            case 103899126:
                if (str.equals("min15")) {
                    c = 6;
                    break;
                }
            case 103899152:
                if (str.equals("min20")) {
                    c = 7;
                    break;
                }
            case 103899183:
                if (str.equals("min30")) {
                    c = 8;
                    break;
                }
            case 109310512:
                if (str.equals("sec10")) {
                    c = 1;
                    break;
                }
            case 109310574:
                if (str.equals("sec30")) {
                    c = 2;
                    break;
                }
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.relminnow.setBackgroundResource(R.drawable.circle_shape_green_color);
                return;
            case 1:
                this.relmin10s.setBackgroundResource(R.drawable.circle_shape_green_color);
                return;
            case 2:
                this.relmin30s.setBackgroundResource(R.drawable.circle_shape_green_color);
                return;
            case 3:
                this.relmin1.setBackgroundResource(R.drawable.circle_shape_green_color);
                return;
            case 4:
                this.relmin5.setBackgroundResource(R.drawable.circle_shape_green_color);
                return;
            case 5:
                this.relmin10.setBackgroundResource(R.drawable.circle_shape_green_color);
                return;
            case 6:
                this.relmin15.setBackgroundResource(R.drawable.circle_shape_green_color);
                return;
            case 7:
                this.relmin20.setBackgroundResource(R.drawable.circle_shape_green_color);
                return;
            case 8:
                this.relmin30.setBackgroundResource(R.drawable.circle_shape_green_color);
                return;
            case 9:
                this.relminclock.setBackgroundResource(R.drawable.circle_shape_green_color);
                return;
            default:
                return;
        }
    }
}
