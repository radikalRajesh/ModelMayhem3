package modelmayhem.com.modelmayhem.loginresistration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import modelmayhem.com.modelmayhem.R;


/**
 * Created by Arpan on 30/5/2017.
 */

public class ChooseTypeofAccountActivity extends Activity implements View.OnClickListener {

    private RelativeLayout back_layout;
    private LinearLayout indivisual_artist,talent_requiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account_type);
        back_layout = (RelativeLayout)findViewById(R.id.back_relativeLayout);
        back_layout.setOnClickListener(this);
        indivisual_artist = (LinearLayout) findViewById(R.id.indivisual_artist);
        indivisual_artist.setOnClickListener(this);
        talent_requiter  =(LinearLayout)findViewById(R.id.talent_requiter);
        talent_requiter.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_relativeLayout:

                BackFunction();
                break;
            case R.id.indivisual_artist:
                MoveProfilepage();
                break;
            case R.id.talent_requiter:
               MoveTalentRecruiter();
                break;

        }
    }

    private void MoveTalentRecruiter() {
        Intent intent = new Intent(this,TalentRecruiterActivity.class);
        startActivity(intent);
    }

    private void MoveProfilepage() {
        Intent intent = new Intent(this,ArtistTypeActivity.class);
        startActivity(intent);
    }

    private void BackFunction() {
        finish();
    }
}
