package com.example.collegehelper.ui.event;

import java.util.Date;

public class EventConstructor {
    private String event_name; // название
    private String event_desc;
    private String event_date;
    private String group_name;
    private String course_name;

    public EventConstructor(String event_name, String event_desc, String event_date, String group_name, String course_name){
        this.event_name = event_name;
        this.event_desc = event_desc;
        this.event_date = event_date;
        this.group_name = group_name;
        this.course_name = course_name;
    }

    public String getName() {
        return this.event_name;
    }
    public void setName(String event_name) {
        this.event_name = event_name;
    }

    public String getDesc() {
        return this.event_desc;
    }
    public void setDesc(String event_desc) {
        this.event_desc = event_desc;
    }

    public String getDate() {
        return this.event_date;
    }
    public void setDate(String event_date) {
        this.event_date = event_date;
    }

    public String getGroup() {
        return this.group_name;
    }
    public void setGroup(String group_name) {
        this.group_name = group_name;
    }

    public String getCourse() {
        return this.course_name;
    }
    public void setCourse(String course_name) {
        this.course_name = course_name;
    }
}
