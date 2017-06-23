package modelmayhem.com.modelmayhem.splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import modelmayhem.com.modelmayhem.HomeActivity;
import modelmayhem.com.modelmayhem.R;
import modelmayhem.com.modelmayhem.utility.CommonUtility;
import modelmayhem.com.modelmayhem.utility.DialogUtility;


/**
 * Created by Arpan on 27/5/2017.
 */

public class SplashActivity extends Activity {
    private int SPLASH_TIME_OUT = 3000;
    public static SharedPreferences pref;
    public static  SharedPreferences.Editor editor;
    public static final String PREFS_NAME = "AOP_PREFS";
    TextView textv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textv = (TextView)findViewById(R.id.textv);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/augie.ttf");
        textv.setTypeface(typeface);

        pref = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = pref.edit();
        if(CommonUtility.checkConnectivity(SplashActivity.this)) {
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent loginintent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(loginintent);
                    finish();
               /*     if(pref.getString("is_login",null)==null ||pref.getString("is_login",null).equals("") ) {
                        System.out.println("chk null");



                        Intent loginintent = new Intent(SplashActivity.this, Login.class);
                        startActivity(loginintent);
                        finish();

                        // }

                    }else{
                        System.out.println("chk  not null"+pref.getString("is_login",null));
                        Intent loginintent = new Intent(SplashActivity.this, Home.class);
                        startActivity(loginintent);
                        finish();
                    }*/

                }
            },SPLASH_TIME_OUT);

        }
        else{
            DialogUtility.showMessegeWithOk("No Internet Connection!,Please Enable Internet", SplashActivity.this);
        }
    }

}
