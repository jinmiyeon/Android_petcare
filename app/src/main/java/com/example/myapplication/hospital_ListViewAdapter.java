package com.example.myapplication;
// 검색
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class hospital_ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<hospital_search_ArrayList> hospital_search_list = null;
    private ArrayList<hospital_search_ArrayList> arraylist;

    public hospital_ListViewAdapter(Context context, List<hospital_search_ArrayList> worldpopulationlist) {
        mContext = context;
        this.hospital_search_list = worldpopulationlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<hospital_search_ArrayList>();
        this.arraylist.addAll(worldpopulationlist);
    }

    public class hospital_ViewHolder {
        TextView hospital;
    }

    @Override
    public int getCount() {
        return hospital_search_list.size();
    }

    @Override
    public hospital_search_ArrayList getItem(int position) {
        return hospital_search_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final hospital_ViewHolder hospital_holder;
        if (view == null) {
            hospital_holder = new hospital_ViewHolder();
            view = inflater.inflate(R.layout.hospital_search_item, null);
            // Locate the TextViews in listview_item.xml
            hospital_holder.hospital = (TextView) view.findViewById(R.id.hospital);
            view.setTag(hospital_holder);
        } else {
            hospital_holder = (hospital_ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        hospital_holder.hospital.setText(hospital_search_list.get(position).getHospital());

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, hospital_SingleItemView.class);
                // Pass all data rank
                intent.putExtra("hospital", (hospital_search_list.get(position).getHospital()));

                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        hospital_search_list.clear();
        if (charText.length() == 0) {
            hospital_search_list.addAll(arraylist);
        } else {
            for (hospital_search_ArrayList wp : arraylist) {
                if (wp.getHospital().toLowerCase(Locale.getDefault()).contains(charText)) {
                    hospital_search_list.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}