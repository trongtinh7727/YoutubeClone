package com.iiex.videocommunity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iiex.videocommunity.databinding.ItemVideoBinding;
import com.iiex.videocommunity.model.Video;


import java.util.List;



public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private List<Video> videos;


    public VideoAdapter(List<Video> videos) {
        this.videos = videos;
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Video video);
    }
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemVideoBinding binding = ItemVideoBinding.inflate(layoutInflater, parent, false);
        return new VideoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.bind(videos.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(video);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
        notifyDataSetChanged();
    }

    public void clearVideos() {
        videos.clear();
        notifyDataSetChanged();
    }


    public class VideoViewHolder extends RecyclerView.ViewHolder {
        private ItemVideoBinding binding;

        public VideoViewHolder(@NonNull ItemVideoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Video video) {
            binding.setVideo(video);
            Glide.with(itemView.getContext())
                    .load(video.getThumbnail())
                    .fitCenter()
                    .into(binding.videoThumbnail);

            Glide.with(itemView.getContext())
                    .load(video.getChannelPicture())
                    .fitCenter()
                    .into(binding.channelPicture);
            binding.executePendingBindings();
        }
    }
}

