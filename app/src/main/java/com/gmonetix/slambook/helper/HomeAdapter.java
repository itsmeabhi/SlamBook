package com.gmonetix.slambook.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gmonetix.slambook.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

public class HomeAdapter extends BaseAdapter {

    private Context context;
    private List<HomeModel> fromData;

    public HomeAdapter(Context context, List<HomeModel> fromData){
        this.context = context;
        this.fromData = fromData;
    }

    @Override
    public int getCount() {
        return fromData.size();
    }

    @Override
    public Object getItem(int position) {
        return fromData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.user_home_list_view_sample_layout, null);

            holder.profilePic = (ImageView) convertView.findViewById(R.id.user_home_listview_image_view_sample_layout);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.user_home_listview_progress_bar_sample_layout);
            holder.name = (TextView) convertView.findViewById(R.id.user_home_listview_name_sample);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ViewHolder finalHolder = holder;
        holder.name.setText(fromData.get(position).getName());
        ImageLoader.getInstance().displayImage(fromData.get(position).getImage(), holder.profilePic, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                finalHolder.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                finalHolder.profilePic.setImageResource(R.drawable.profile);
                finalHolder.progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                finalHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });

        return convertView;
    }
    private class ViewHolder{
        private ImageView profilePic;
        private ProgressBar progressBar;
        private TextView name;
    }
}
