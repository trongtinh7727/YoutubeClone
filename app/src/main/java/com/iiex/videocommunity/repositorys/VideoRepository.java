package com.iiex.videocommunity.repositorys;

import com.iiex.videocommunity.model.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoRepository {
    public List<Video> getSuggestedVideos() {
       Video v1 = new Video("Tinh nè", "https://firebasestorage.googleapis.com/v0/b/clone-658cb.appspot.com/o/VIETNAM%20%20My%20Home%20%20Masew%20MyoMouse%20Nguyen%20Loi%20Version%202%20Sáo_1080p.mp4?alt=media&token=f760f792-dbc8-4fde-b651-d2b2688b85b1");
       List<Video> list = new ArrayList<>();
       list.add(v1);
       return list;
    }
}

