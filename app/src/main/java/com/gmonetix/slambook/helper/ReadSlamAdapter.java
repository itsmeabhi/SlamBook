package com.gmonetix.slambook.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
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

public class ReadSlamAdapter extends BaseAdapter {

    private Context context;
    private List<ReadSlamModel> Slams;
    Utils utils;

    public ReadSlamAdapter(Context context, List<ReadSlamModel> Slams) {
        this.context = context;
        this.Slams = Slams;
    }

    @Override
    public int getCount() {
        return Slams.size();
    }

    @Override
    public Object getItem(int position) {
        return Slams.get(position);
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

            holder.Name = (TextView) convertView.findViewById(R.id.read_slam_name_tv);
            holder.fromUsername = (TextView) convertView.findViewById(R.id.read_slam_username_tv);
            holder.profilePic = (ImageView) convertView.findViewById(R.id.read_slam_profile_image);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.read_slam_profile_image_progrss_bar);

            utils = new Utils();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Name.setText(Slams.get(position).getName());
        holder.fromUsername.setText(Slams.get(position).getFromUsername());

        final ViewHolder finalHolder = holder;
        Log.d("image url",Slams.get(position).getImage());
        ImageLoader.getInstance().displayImage(Slams.get(position).getImage(), holder.profilePic, new ImageLoadingListener() {
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

        utils.setFont(context,holder.Name);
        utils.setFont(context,holder.fromUsername);

        return convertView;
    }
    private class ViewHolder{
        private TextView Name;
        private TextView fromUsername;
        private ImageView profilePic;
        private ProgressBar progressBar;
    }
}
