package com.design.mynote;

import java.io.Serializable;

/**
 * Created by 杰‘z on 2016/8/12.
 */
public class Note implements Serializable {

    public String id;
    public String userid;
    public String note;
    public String lastTime;
    public String content;

    public Note(String id, String note) {
        this.id = id;
        this.note = note;
    }

    public Note(String id, String note, String lastTime, String content) {
        this.id = id;
        this.note = note;
        this.lastTime = lastTime;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
