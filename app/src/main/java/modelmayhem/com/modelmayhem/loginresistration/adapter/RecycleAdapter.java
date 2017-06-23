package modelmayhem.com.modelmayhem.loginresistration.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import modelmayhem.com.modelmayhem.R;


/**
 * Created by Arpan on 14/6/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    String[] listValues;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView row_btn;
    int global_position=500;

    public RecycleAdapter(Context context1,String[] listValuess){

        listValues = listValuess;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView row_brn;

        public ViewHolder(View v){

            super(v);

            row_brn = (TextView) v.findViewById(R.id.row_brn);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        view1 = LayoutInflater.from(context).inflate(R.layout.artist_row,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position){

        holder.row_brn.setText(listValues[position]);
        holder.row_brn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.row_brn.setBackgroundColor(holder.row_brn.getContext().getResources().getColor(R.color.selected_color));

            }
        });
    }

    @Override
    public int getItemCount(){

        return listValues.length;
    }
}