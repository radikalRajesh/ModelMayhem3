package modelmayhem.com.modelmayhem.loginresistration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import modelmayhem.com.modelmayhem.R;


/**
 * Created by Arpan on 10/6/2017.
 */

public class ChooseUsername extends Activity implements View.OnClickListener{

    private TextView user_name_header;
    private Button continue_btn;
    private ProgressBar progressBar;
    private EditText username_input;

    private RelativeLayout back_relativeLayout;
    TextInputLayout input_layer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_user_name);
        InitView();
        EditfieldOntextFunction();
    }

    private void EditfieldOntextFunction() {


        username_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                username_input.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("3");

            }
        });

        username_input.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if(actionId== EditorInfo.IME_ACTION_DONE||actionId== EditorInfo.IME_ACTION_NEXT)
                {
                   CheckingUser();
                }
                return false;
            }
        });

    }

    private void CheckingUser() {
        if(username_input.getText().toString().equalsIgnoreCase("rajesh")) {
            input_layer.setErrorEnabled(false);
            input_layer.setHintTextAppearance(R.style.correct);
            username_input.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_checked, 0);
            progressBar.setVisibility(View.GONE);
            continue_btn.setBackgroundDrawable( getResources().getDrawable( R.drawable.button_background ) );
            continue_btn.setEnabled(true);
            continue_btn.setClickable(true);
        }else{
            input_layer.setErrorEnabled(true);
            input_layer.setHintTextAppearance(R.style.error);
            input_layer.setError("user name already exists");
            username_input.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_error, 0);
            progressBar.setVisibility(View.GONE);
            continue_btn.setBackgroundDrawable( getResources().getDrawable( R.drawable.button_background ) );
            continue_btn.setEnabled(true);
            continue_btn.setClickable(true);

        }


    }

    private void InitView() {

        input_layer = (TextInputLayout)findViewById(R.id.input_layout);
        continue_btn = (Button)findViewById(R.id.continue_btn);
        continue_btn.setOnClickListener(this);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        username_input = (EditText)findViewById(R.id.username_input);
        progressBar.setVisibility(View.GONE);
        back_relativeLayout = (RelativeLayout)findViewById(R.id.back_relativeLayout);
        back_relativeLayout.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.back_relativeLayout:

                BackFunction();
                break;
            case R.id.continue_btn:
                ContinueBtn();
                break;


    }

}

    private void ContinueBtn() {
        Intent intent = new Intent(ChooseUsername.this,AgreetermConditionActivity.class);
        startActivity(intent);

    }

    private void BackFunction() {
        finish();
    }
    }
