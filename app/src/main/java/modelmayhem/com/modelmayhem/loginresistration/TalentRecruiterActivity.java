package modelmayhem.com.modelmayhem.loginresistration;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import modelmayhem.com.modelmayhem.R;

/**
 * Created by Arpan on 30/5/2017.
 */

public class TalentRecruiterActivity extends Activity implements View.OnClickListener {


    private RelativeLayout back_layout;
    private LinearLayout select_talent_require;
    private Button btn_continue;
    private TextView talent_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_requiter);
        back_layout = (RelativeLayout) findViewById(R.id.back_relativeLayout);
        back_layout.setOnClickListener(this);
        select_talent_require = (LinearLayout) findViewById(R.id.select_talent_require);
        talent_txt = (TextView) findViewById(R.id.talent_txt);

        btn_continue = (Button) findViewById(R.id.btn_continue);
        select_talent_require.setOnClickListener(this);
        btn_continue.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back_relativeLayout:

                BackFunction();
                break;
            case R.id.select_talent_require:
                SelectTalentRequiredFunction();
                break;

            case R.id.btn_continue:
                ContinueBtn();
                break;

        }


    }

    private void ContinueBtn() {
    }

    private void SelectTalentRequiredFunction() {


        final CharSequence[] items = {"Model", "Photographer", "Makeup Artist", "Wardrobe", "Hair Stylist", "Retoucher", "Artist/Painter", "Body Painter", "Clothing Designer", "Digital Artist"};

        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setTitle("Artist Type").setItems(items, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(), "U clicked " + items[which], Toast.LENGTH_LONG).show();
                talent_txt.setText(items[which]);
            }

        });

        builder3.show();
    }





    private void BackFunction() {
        finish();
    }
}
