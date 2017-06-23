package modelmayhem.com.modelmayhem;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

import modelmayhem.com.modelmayhem.homeadapter.ViewPagerAdapter;
import modelmayhem.com.modelmayhem.loginresistration.LoginActivity;
import modelmayhem.com.modelmayhem.loginresistration.SignUpActivity;


public class HomeActivity extends FragmentActivity implements View.OnClickListener{
    int[] image={R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    String[] header_txt_list = {"Start connecting with other modeling-industry professionals.","Review and apply to the thousands of open casting calls near your area.","Browse and connect with those who share your professional interests.","Showcase your portfolio with Model Mayhem’s leading artists."};
    String[] copyright_txt_list = {"Copyright KarenImages","Copyright Ali0x01","Copyright Mason J","Copyright Jackstudios"};
    //private SliderLayout mDemoSlider;
    private Button SignUp, Login_bth;
    private TextView middle_txt,copyright_txt;

    private ViewPager viewPager;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter mAdapter;
    Timer timer;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeeeee);
       // mDemoSlider = (SliderLayout)findViewById(R.id.slider);
       // setImagePager();
        UiInitialisation();
        KeyHAsh();


    }

    private void KeyHAsh() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "modelmayhem.com.modelmayhem",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("Your Tag", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                String hashKey = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                String str ="hi";
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    private void UiInitialisation() {







        SignUp = (Button)findViewById(R.id.btn_signup);
        Login_bth =(Button)findViewById(R.id.btn_login);
        SignUp.setOnClickListener(this);
        Login_bth.setOnClickListener(this);
        middle_txt = (TextView)findViewById(R.id.middle_txt);
        copyright_txt= (TextView)findViewById(R.id.copyright_txt);
        viewPager = (ViewPager)findViewById(R.id.v_pager);


        pager_indicator = (LinearLayout)findViewById(R.id.viewPagerCountDots);
        SetImageInViewPager();









        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable(){

                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%image.length);
                        //viewPager.setPageTransformer(true, new IntroPageTransformer());

                        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
                            @Override
                            public void transformPage(View view, float position) {
                                // Ensures the views overlap each other.
                                view.setTranslationX(view.getWidth() * -position);

                                // Alpha property is based on the view position.
                                if(position <= -1.0F || position >= 1.0F) {
                                    view.setAlpha(0.0F);
                                } else if( position == 0.0F ) {
                                    view.setAlpha(1.0F);
                                } else { // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                                    view.setAlpha(1.0F - Math.abs(position));
                                }


                            }
                        });
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 500, 3000);





    }

    private void SetImageInViewPager() {


        mAdapter = new ViewPagerAdapter(HomeActivity.this, image,copyright_txt_list, header_txt_list);
        mAdapter.notifyDataSetChanged();
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);


       // viewPager.setPageTransformer(true, new IntroPageTransformer());
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                // Ensures the views overlap each other.
                view.setTranslationX(view.getWidth() * -position);

                // Alpha property is based on the view position.
                if(position <= -1.0F || position >= 1.0F) {
                    view.setAlpha(0.0F);
                } else if( position == 0.0F ) {
                    view.setAlpha(1.0F);
                } else { // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                    view.setAlpha(1.0F - Math.abs(position));
                }


            }
        });


        setUiPageViewController();
        viewPager.invalidate();


                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }
                    @Override
                    public void onPageSelected(int position) {
                        for (int i = 0; i < dotsCount; i++) {
                            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
                        }
                        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
                    }
                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });

            }




 /*   private void setImagePager() {
        for(int i=0;i<image.length;i++){
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView.image(image[i]).setScaleType(BaseSliderView.ScaleType.Fit);



            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(1000);



        mDemoSlider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    middle_txt.setText("Start connecting with other modeling-industry professionals.");
                    copyright_txt.setText("Copyright KarenImages.");

                }
                else if(position==1){
                    middle_txt.setText("Review and apply to the thousands of open casting calls near your area.");
                    copyright_txt.setText("Copyright Ali0x01.");
                }
                else if(position==2){
                    middle_txt.setText("Browse and connect with those who share your professional interests. ");
                    copyright_txt.setText("Copyright Mason J.");
                }
                else if(position==3){
                    middle_txt.setText("Showcase your portfolio with Model Mayhem’s leading artists.");
                    copyright_txt.setText("Copyright Jackstudios.");

                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });


    }*/

    private void setUiPageViewController() {
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getApplicationContext());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(4, 0, 4, 0);
            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_signup:
                MoveSignUpPage();
                break;
            case R.id.btn_login:
                MoveLoginPage();
                break;

        }

    }

    private void MoveSignUpPage() {
        Intent intent = new Intent(HomeActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
    private void MoveLoginPage() {
        Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

}
