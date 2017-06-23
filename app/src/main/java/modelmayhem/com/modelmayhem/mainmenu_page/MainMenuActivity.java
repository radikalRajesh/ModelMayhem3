package modelmayhem.com.modelmayhem.mainmenu_page;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import modelmayhem.com.modelmayhem.R;
import modelmayhem.com.modelmayhem.mainmenu_page.menu_fragment.HomeFragment;
import modelmayhem.com.modelmayhem.mainmenu_page.menu_fragment.NotificationFragment;
import modelmayhem.com.modelmayhem.mainmenu_page.menu_fragment.SearchFragment;
import modelmayhem.com.modelmayhem.mainmenu_page.menu_fragment.UserFragment;
import modelmayhem.com.modelmayhem.mainmenu_page.menu_fragment.WorkFragment;
import modelmayhem.com.modelmayhem.utility.BottomNavigationViewHelper;

/**
 * Created by Arpan on 22/6/2017.
 */

public class MainMenuActivity extends AppCompatActivity {

    private TextView header_txt;
    private  LinearLayout dialog_layout;
    private Button btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        InitView();
       //OpenFullScreenDialog();
    }

    private void OpenFullScreenDialog() {


        final Dialog dialog = new Dialog(MainMenuActivity.this,android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fullscreen_main_menudialog);
        dialog.show();

        Button declineButton = (Button) dialog.findViewById(R.id.btn_continue);
        // if decline button is clicked, close the custom dialog
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });
    }

    private void InitView() {

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        if (bottomNavigationView != null) {

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottomNavigationView.getMenu();
            selectFragment(menu.getItem(0));

            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item);
                            return false;
                        }
                    });

        }
        header_txt = (TextView)findViewById(R.id.header_txt);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/augie.ttf");
        header_txt.setTypeface(typeface);
        dialog_layout = (LinearLayout)findViewById(R.id.dialog_layout);
        btn_continue = (Button)findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_layout.setVisibility(View.GONE);
            }
        });


    }
    protected void selectFragment(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.navigation_home:

                pushSearchFragment(new HomeFragment());

                break;
            case R.id.navigation_search:

                pushSearchFragment(new SearchFragment());

                break;
            case R.id.navigation_notification:

                pushSearchFragment(new NotificationFragment());
                break;
            case R.id.navigation_work:

                pushSearchFragment(new WorkFragment());
                break;
            case R.id.navigation_user:

                pushSearchFragment(new UserFragment());
                break;
        }
    }

    protected void pushSearchFragment(Fragment fragment) {
        if (fragment == null)
            return;
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.content, fragment);
                ft.commit();
            }
        }
    }
}
