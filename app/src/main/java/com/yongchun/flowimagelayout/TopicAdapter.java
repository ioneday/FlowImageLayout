package com.yongchun.flowimagelayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dee on 15/6/29.
 */
public class TopicAdapter extends BaseAdapter{
    private List<Topic> list = new ArrayList<Topic>();

    public void addTopic(Topic topic){
        list.add(topic);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Topic getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_topic,parent,false);
            ViewHolder holder = new ViewHolder();
            holder.user_image = (ImageView) convertView.findViewById(R.id.user_image);
            holder.user_name = (TextView) convertView.findViewById(R.id.user_name);
            holder.topic_content = (TextView) convertView.findViewById(R.id.topic_content);
            holder.image_layout = (FlowImageLayout) convertView.findViewById(R.id.image_layout);
            convertView.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        final Topic topic = list.get(position);
        holder.user_image.setImageResource(topic.getUserImageResId());
        holder.user_name.setText(topic.getUserName());
        holder.topic_content.setText(topic.getTopicContent());
        holder.image_layout.setLoadImage(topic.getImgaes().size(), new FlowImageLayout.OnImageLayoutFinishListener() {
            @Override
            public void layoutFinish(List<ImageView> images) {
                for (int i = 0; i < images.size(); i++) {
                    images.get(i).setImageResource(topic.getImgaes().get(i));
                }
            }
        });
        return convertView;
    }
    static class ViewHolder{
        ImageView user_image;
        TextView user_name;
        TextView topic_content;
        FlowImageLayout image_layout;
    }
}
