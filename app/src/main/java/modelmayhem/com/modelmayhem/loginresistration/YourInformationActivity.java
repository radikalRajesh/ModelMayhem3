package modelmayhem.com.modelmayhem.loginresistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import modelmayhem.com.modelmayhem.R;

/**
 * Created by Arpan on 21/6/2017.
 */

public class YourInformationActivity extends AppCompatActivity implements View.OnClickListener {

private RelativeLayout back_layout;
private Button btn_continue;



@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_information);
        back_layout = (RelativeLayout)findViewById(R.id.back_relativeLayout);
        back_layout.setOnClickListener(this);

        btn_continue = (Button)findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(this);







        }

@Override
public void onClick(View v) {

        switch (v.getId())
        {
        case R.id.back_relativeLayout:

        BackFunction();
        break;

        case R.id.btn_continue:
        ContinueBtn();
        break;

        }


        }

private void ContinueBtn() {
        Intent intent = new Intent(this,BirthDayActivity.class);
        startActivity(intent);

        }






private void BackFunction() {
        finish();
        }
        }
