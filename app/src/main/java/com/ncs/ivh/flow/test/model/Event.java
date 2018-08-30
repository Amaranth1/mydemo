package com.ncs.ivh.flow.test.model;

import java.util.Date;

public class Event
{
    private String name;

    private String description;

    private Date time;

    private String location;

    public Event()
    {
    }

    public Event(String name, String description, Date time, String location)
    {
        this.name = name;
        this.description = description;
        this.time = time;
        this.location = location;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
}
