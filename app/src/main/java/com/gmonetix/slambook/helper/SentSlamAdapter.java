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

public class SentSlamAdapter extends BaseAdapter {

    private Context context;
    private List<SentSlamModel> sentSlams;

    public SentSlamAdapter(Context context, List<SentSlamModel> sentSlams){
        this.context = context;
        this.sentSlams = sentSlams;
    }

    @Override
    public int getCount() {
        return sentSlams.size();
    }

    @Override
    public Object getItem(int position) {
        return sentSlams.get(position);
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
            convertView = mInflater.inflate(R.layout.read_slam_sample_layout, null);

            holder.profilePic = (ImageView) convertView.findViewById(R.id.read_slam_profile_image);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.read_slam_profile_image_progrss_bar);
            holder.name = (TextView) convertView.findViewById(R.id.read_slam_name_tv);
            holder.username = (TextView) convertView.findViewById(R.id.read_slam_username_tv);
            holder.updatedOn = (TextView) convertView.findViewById(R.id.read_slam_updated_on_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ViewHolder finalHolder = holder;
        ImageLoader.getInstance().displayImage(sentSlams.get(position).getImage(), holder.profilePic, new ImageLoadingListener() {
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

        holder.name.setText(sentSlams.get(position).getName());
        holder.username.setText(sentSlams.get(position).getUsername());
        holder.updatedOn.setText(sentSlams.get(position).getUpdatedOn());

        return convertView;
    }
    private class ViewHolder{
        private ImageView profilePic;
        private ProgressBar progressBar;
        private TextView name, username, updatedOn;
    }
    
}
