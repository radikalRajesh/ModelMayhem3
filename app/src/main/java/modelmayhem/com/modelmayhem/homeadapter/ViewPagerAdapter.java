package modelmayhem.com.modelmayhem.homeadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import modelmayhem.com.modelmayhem.R;

/**
 * Created by Arpan on 20/6/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {

        private Context mContext;




    String[] copy_right_txt,body_txtt;
    int[] image_id;



    public ViewPagerAdapter(Context mContext, int[] image_id, String[] copy_right_txt, String[] body_txt) {
        this.mContext = mContext;
        this.image_id = image_id;
        this.copy_right_txt = copy_right_txt;
        this.body_txtt = body_txt;




    }

        @Override
        public int getCount() {
        return image_id.length;
    }

        @Override
        public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.iamge_pager_items, container, false);

        final ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
            imageView.setImageResource(image_id[position]);

            final TextView body_txt = (TextView)itemView.findViewById(R.id.center_txt);
            body_txt.setText(body_txtt[position]);
            final TextView copyright_txt = (TextView)itemView.findViewById(R.id.copyright_txt);
            copyright_txt.setText(copy_right_txt[position]);








        container.addView(itemView);

        return itemView;
    }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    }
