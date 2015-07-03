package com.yongchun.flowimagelayout;

import java.util.List;

/**
 * Created by dee on 15/6/29.
 */
public class Topic {
    private int userImageResId;
    private String userName;
    private String topicContent;
    private List<Integer> imgaes;

    public Topic(int userImageResId, String userName, String topicContent, List<Integer> imgaes) {
        this.userImageResId = userImageResId;
        this.userName = userName;
        this.topicContent = topicContent;
        this.imgaes = imgaes;
    }

    public int getUserImageResId() {
        return userImageResId;
    }

    public String getUserName() {
        return userName;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public List<Integer> getImgaes() {
        return imgaes;
    }

    public void setUserImageResId(int userImageResId) {
        this.userImageResId = userImageResId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public void setImgaes(List<Integer> imgaes) {
        this.imgaes = imgaes;
    }
}
