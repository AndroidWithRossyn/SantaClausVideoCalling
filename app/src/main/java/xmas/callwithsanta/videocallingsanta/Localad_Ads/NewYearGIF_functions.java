package xmas.callwithsanta.videocallingsanta.Localad_Ads;

import static xmas.callwithsanta.videocallingsanta.Localad_Ads.NewYearGIF_Splace_Activity.adsdata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;


public class NewYearGIF_functions {
    public static boolean iplscr_flag = true;
    private static Handler iplscr_mHandler;
    private static Runnable iplscr_runnable;

    @SuppressLint("WrongConstant")
    public static void autoQureka(Context context) {
        context.startActivity(new Intent(context, NewYearGIF_AutoQurekaActivity.class).addFlags(268435456));
    }

    public static void callAutoQureka(final Context context) {
        iplscr_mHandler = new Handler();
        if (adsdata != null && adsdata.size() > 0 && adsdata.get(0).getCheckQurekaInterval().equals("on")) {
            Handler handler = iplscr_mHandler;
            Runnable r2 = new Runnable() {
                public void run() {
                    if (NewYearGIF_functions.iplscr_flag) {
                        NewYearGIF_functions.iplscr_flag = false;
                        NewYearGIF_functions.autoQureka(context);
                    }
                }
            };
            iplscr_runnable = r2;
            handler.postDelayed(r2, Long.parseLong(adsdata.get(0).getQurekaInterval()));
        }
    }

    public static void destroyThread() {
        try {
            iplscr_mHandler.removeCallbacks(iplscr_runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}