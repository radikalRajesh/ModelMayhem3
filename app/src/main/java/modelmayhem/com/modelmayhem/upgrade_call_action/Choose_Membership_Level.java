package modelmayhem.com.modelmayhem.upgrade_call_action;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import at.blogc.android.views.ExpandableTextView;
import modelmayhem.com.modelmayhem.R;

/**
 * Created by Arpan on 23/6/2017.
 */

public class Choose_Membership_Level extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout back_layout;
    private Button btn_continue;
    private Button basic_plus,primium_plus,vip_plus;
    private ExpandableTextView basic_view,premium_expnd_view,vip_expnd_view;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_choose_membership);

            InitView();

        }

    private void InitView() {

        back_layout = (RelativeLayout)findViewById(R.id.back_relativeLayout);
        back_layout.setOnClickListener(this);

        // btn_continue = (Button)findViewById(R.id.btn_continue);
        //  btn_continue.setOnClickListener(this);
        basic_plus = (Button)findViewById(R.id.basic_plus);
        basic_plus.setOnClickListener(this);
        basic_view = (ExpandableTextView)findViewById(R.id.basic_view);
        basic_view.setAnimationDuration(1000L);
        basic_view.setInterpolator(new OvershootInterpolator());
        basic_view.setExpandInterpolator(new OvershootInterpolator());
        basic_view.setCollapseInterpolator(new OvershootInterpolator());


        primium_plus = (Button)findViewById(R.id.primium_plus);
        primium_plus.setOnClickListener(this);
        premium_expnd_view = (ExpandableTextView)findViewById(R.id.premium_expnd_view);
        premium_expnd_view.setAnimationDuration(1000L);
        premium_expnd_view.setInterpolator(new OvershootInterpolator());
        premium_expnd_view.setExpandInterpolator(new OvershootInterpolator());
        premium_expnd_view.setCollapseInterpolator(new OvershootInterpolator());


        vip_plus = (Button)findViewById(R.id.vip_plus);
        vip_plus.setOnClickListener(this);
        vip_expnd_view = (ExpandableTextView)findViewById(R.id.vip_expnd_view);
        vip_expnd_view.setAnimationDuration(1000L);
        vip_expnd_view.setInterpolator(new OvershootInterpolator());
        vip_expnd_view.setExpandInterpolator(new OvershootInterpolator());
        vip_expnd_view.setCollapseInterpolator(new OvershootInterpolator());
    }

    @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.back_relativeLayout:

                    BackFunction();
                    break;

                case R.id.basic_plus:
                    BasicContinueBtn();
                    break;
                case R.id.primium_plus:
                    PremiumContinueBtn();
                    break;
                case R.id.vip_plus:
                    VipContinueBtn();
                    break;

            }
        }

    private void BasicContinueBtn() {
        if (basic_view.isExpanded()) {
            basic_view.collapse();
            basic_plus.setText("+");
        }
        else {
            basic_view.expand();
            basic_plus.setText("-");
        }
    }
    private void PremiumContinueBtn() {
        if (premium_expnd_view.isExpanded()) {
            premium_expnd_view.collapse();
            primium_plus.setText("+");
        }
        else {
            premium_expnd_view.expand();
            primium_plus.setText("-");
        }
    }

    private void VipContinueBtn() {
        if (vip_expnd_view.isExpanded()) {
            vip_expnd_view.collapse();
            vip_plus.setText("+");
        }
        else {
            vip_expnd_view.expand();
            vip_plus.setText("-");
        }
    }






    private void BackFunction() {
        finish();
    }
}


