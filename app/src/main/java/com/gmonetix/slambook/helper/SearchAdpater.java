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

public class SearchAdpater extends BaseAdapter{

    private Context context;
    private List<SearchFriendsModel> searchedFriends;
    Font font;

    public SearchAdpater(Context context, List<SearchFriendsModel> searchedFriends) {
        this.context = context;
        this.searchedFriends = searchedFriends;
    }

    @Override
    public int getCount() {
        return searchedFriends.size();
    }

    @Override
    public Object getItem(int position) {
        return searchedFriends.get(position);
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
            convertView = mInflater.inflate(R.layout.search_friends_sample_layout, null);

            holder.Name = (TextView) convertView.findViewById(R.id.name_search);
            holder.Email = (TextView) convertView.findViewById(R.id.email_search);
            holder.Description = (TextView) convertView.findViewById(R.id.description_search);
            holder.profilePic = (ImageView) convertView.findViewById(R.id.profile_pic_search);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progress_bar_profile_pic_search);

            font  = new Font();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Name.setText(searchedFriends.get(position).getName());
        holder.Email.setText(searchedFriends.get(position).getEmail());
        holder.Description.setText(searchedFriends.get(position).getDescription());

        final ViewHolder finalHolder = holder;
        ImageLoader.getInstance().displayImage(searchedFriends.get(position).getImageUrl(), holder.profilePic, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                finalHolder.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                finalHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });

        font.setFont(context,holder.Name);
        font.setFont(context,holder.Email);
        font.setFont(context,holder.Description);

        return convertView;
    }
    private class ViewHolder{
        private TextView Name;
        private TextView Email;
        private TextView Description;
        private ImageView profilePic;
        private ProgressBar progressBar;
    }
}
