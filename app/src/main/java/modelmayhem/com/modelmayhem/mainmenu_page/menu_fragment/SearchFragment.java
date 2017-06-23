package modelmayhem.com.modelmayhem.mainmenu_page.menu_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import modelmayhem.com.modelmayhem.R;

/**
 * Created by Arpan on 22/6/2017.
 */

public class SearchFragment extends Fragment {
    public SearchFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}