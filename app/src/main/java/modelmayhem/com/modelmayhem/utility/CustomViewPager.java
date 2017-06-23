package modelmayhem.com.modelmayhem.utility;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Arpan on 22/6/2017.
 */

public class CustomViewPager extends ViewPager {


        private int mVelocity = 1;

    public CustomViewPager(Context context) {
            super(context);
        }

    public CustomViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }


}
