package modelmayhem.com.modelmayhem.loginresistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import modelmayhem.com.modelmayhem.R;


/**
 * Created by Arpan on 29/5/2017.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout back_relativeLayout;
    private Button btn_sign_up;
    private EditText email_edit,pwd_edit;

    //private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        InitialiseValue();


    }

    private void InitialiseValue() {
        back_relativeLayout =(RelativeLayout)findViewById(R.id.back_relativeLayout);
        back_relativeLayout.setOnClickListener(this);
        btn_sign_up =(Button) findViewById(R.id.btn_sign_upp);
        btn_sign_up.setOnClickListener(this);
        email_edit =(EditText)findViewById(R.id.email_edit);
        pwd_edit =(EditText)findViewById(R.id.pwd_edit);




      /*  toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_relativeLayout:

                BackFunction();
                break;
            case R.id.btn_sign_upp:
                MoveSignUpPage();
                break;

        }


    }

    private void MoveSignUpPage() {
        Intent intent = new Intent(SignUpActivity.this,ChooseTypeofAccountActivity.class);
        startActivity(intent);
    }

    private void BackFunction() {
        finish();
    }
}
