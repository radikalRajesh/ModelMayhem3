package modelmayhem.com.modelmayhem.loginresistration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import modelmayhem.com.modelmayhem.R;


/**
 * Created by Arpan on 31/5/2017.
 */

public class BirthDayActivity extends Activity {

    private DatePicker datePicker;
    private Button btn_continue;
    private RelativeLayout back_relativeLayout;
    String date_picker_selected_date="";
    Date before_date,todsy_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);
        InitView();



    }

    private void InitView() {

        datePicker = (DatePicker) this.findViewById(R.id.datePicker);
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                System.out.println("******"+arg1+"....."+arg2+"....."+arg3);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                before_date = new Date(arg3, arg2, arg1);
                date_picker_selected_date = dateFormatter.format(before_date);
                System.out.println("AGE......"+date_picker_selected_date);

            }
        } );
        btn_continue = (Button)findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SimpleDateFormat formattt = new SimpleDateFormat("dd/MM/yyyy");

                Date date = new Date();


                Date d1 = null;
                Date d2 = null;

                int age = 0;
                try {
                    d1 = formattt.parse(date_picker_selected_date);
                    d2 = formattt.parse(formattt.format(date));
                    System.out.println("AGE"+d1);
                    System.out.println("AGE"+d2);



                    //in milliseconds
                    double diff = d2.getTime() - d1.getTime();

                    double diffYears = diff / (365 * 24 * 60 * 60 * 1000);

                    age = (int) Math.round(diffYears);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("AGE"+String.valueOf(age));

                Intent intent = new Intent(BirthDayActivity.this,GenderActivity.class);
                startActivity(intent);




              /*  if(yearr>=18){
                    Toast.makeText(getApplicationContext(),"You are "+String.valueOf(yearr)+" yeas old",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BirthDayActivity.this,ChooseUsername.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(),"You are "+String.valueOf(yearr)+" yeas old",Toast.LENGTH_SHORT).show();

                }*/




            }
        });
        back_relativeLayout = (RelativeLayout)findViewById(R.id.back_relativeLayout);
        back_relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
