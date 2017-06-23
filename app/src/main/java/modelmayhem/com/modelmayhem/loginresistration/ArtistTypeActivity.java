package modelmayhem.com.modelmayhem.loginresistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import modelmayhem.com.modelmayhem.R;
import modelmayhem.com.modelmayhem.loginresistration.adapter.RecycleAdapter;


/**
 * Created by Arpan on 30/5/2017.
 */

public class ArtistTypeActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout back_layout;
    private Button btn_continue;
    private RecyclerView list_items;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    String[] items={"Model","Photographer","Makeup Artist","Wardrobe","Hair Stylist","Retoucher","Artist/Painter","Body Painter","Clothing Designer","Digital Artist"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_type);
        back_layout = (RelativeLayout)findViewById(R.id.back_relativeLayout);
        back_layout.setOnClickListener(this);

        btn_continue = (Button)findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(this);
        list_items = (RecyclerView)findViewById(R.id.list_items);

        recylerViewLayoutManager = new LinearLayoutManager(ArtistTypeActivity.this);

        list_items.setLayoutManager(recylerViewLayoutManager);

        recyclerViewAdapter = new RecycleAdapter(ArtistTypeActivity.this, items);

        list_items.setAdapter(recyclerViewAdapter);






    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.back_relativeLayout:

                BackFunction();
                break;
          /*  case R.id.select_gender_type:
            SelectGenderFunction();
            break;
            case R.id.select_art_type:
                SelectArtistFunction();
                break;*/
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
