package modelmayhem.com.modelmayhem.loginresistration;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import modelmayhem.com.modelmayhem.R;


/**
 * Created by Arpan on 14/6/2017.
 */

public class ForgotPasswordActivity extends Activity implements View.OnClickListener{
    private RelativeLayout relativeLayout;
    private Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);
        InitView();
    }

    private void InitView() {

        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        relativeLayout.setOnClickListener(this);
        submit_btn =(Button)findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.submit_btn:
                SubmitFunction();
                break;
            case R.id.relativeLayout:
                finish();
                break;



        }
    }

    private void SubmitFunction() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(ForgotPasswordActivity.this, android.R.style.Theme_Material_Light_Dialog);
        } else {
            builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
        }
        builder.setCancelable(true);
        builder.setTitle("Email Sent")
                .setMessage("We just sent an email with password reset instructions. Check your spam folder if you do not see the email in your inbox.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                });



        AlertDialog alert11 = builder.create();
        alert11.show();

    }
}
