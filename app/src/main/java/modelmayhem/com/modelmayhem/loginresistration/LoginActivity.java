package modelmayhem.com.modelmayhem.loginresistration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import modelmayhem.com.modelmayhem.R;
import modelmayhem.com.modelmayhem.upgrade_call_action.Choose_Membership_Level;


/**
 * Created by Arpan on 29/5/2017.
 */

public class LoginActivity extends Activity implements View.OnClickListener{

    private TextInputLayout email_input_layout,pwd_input_layout;
    private EditText email_edit,password_edit;
    private Button login_btn;
    private RelativeLayout relativeLayout;
    private TextView forgot_pwd_txt;
    private CallbackManager callbackManager;
    LoginButton login_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);

        InitView();
    }

    private void InitView() {
        email_input_layout = (TextInputLayout)findViewById(R.id.input_layout_email);
        pwd_input_layout = (TextInputLayout)findViewById(R.id.input_layout_pwd);
        email_edit = (EditText)findViewById(R.id.email_edit);
        password_edit = (EditText)findViewById(R.id.password_edit);
        login_btn = (Button)findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        relativeLayout.setOnClickListener(this);
        forgot_pwd_txt = (TextView)findViewById(R.id.forgot_pwd_txt);
        forgot_pwd_txt.setOnClickListener(this);

        login_button = (LoginButton)findViewById(R.id.login_button);

        email_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                email_input_layout.setErrorEnabled(false);
                email_input_layout.setHintTextAppearance(R.style.correct);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String entered_txt = s.toString();
                System.out.println(entered_txt);
                if(entered_txt.equalsIgnoreCase("1234")){
                    login_btn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white_color));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        login_button.setReadPermissions(Arrays.asList("public_profile","email"));
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                login_button.setVisibility(View.GONE);

                GraphRequest graphRequest   =   GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback()
                {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response)
                    {
                        Log.d("JSON", ""+response.getJSONObject().toString());

                        try
                        {
                            String  email = object.getString("email");
                            String name =  object.getString("name");
                            String  first_name =  object.optString("first_name");
                            String  last_name = object.optString("last_name");
                            LoginManager.getInstance().logOut();
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,first_name,last_name,email");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel()
            {

            }

            @Override
            public void onError(FacebookException exception)
            {

            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_btn:
                LoginFunction();
                DemoFunction();
                break;
            case R.id.relativeLayout:
                finish();
                break;
            case R.id.forgot_pwd_txt:
                forgotPasswordFunction();


        }
    }

    private void DemoFunction() {

        Intent intent = new Intent(LoginActivity.this, Choose_Membership_Level.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void forgotPasswordFunction() {

        Intent intent = new Intent (LoginActivity.this,ForgotPasswordActivity.class);
        startActivity(intent);
    }

    private void LoginFunction() {
        System.out.println("hi");
        if(!email_edit.getText().toString().equalsIgnoreCase("aa@gmail.com")){
            email_input_layout.setErrorEnabled(true);
            email_edit.setError(null);
            email_input_layout.setError("not valid");
            email_input_layout.setHintTextAppearance(R.style.error);
        }
        else if(!password_edit.getText().toString().equalsIgnoreCase("1234")){
            email_input_layout.setErrorEnabled(false);
            pwd_input_layout.setErrorEnabled(true);
            email_input_layout.setHintTextAppearance(R.style.correct);

            password_edit.setError(null);
            pwd_input_layout.setError("not valid");
            pwd_input_layout.setHintTextAppearance(R.style.error);

        }
        else{
            pwd_input_layout.setErrorEnabled(false);
            pwd_input_layout.setHintTextAppearance(R.style.correct);
            login_btn.setEnabled(true);
            login_btn.setFocusable(true);
            login_btn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white_color));
            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();

        }

    }
}
