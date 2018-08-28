package com.ncs.ivh.flow.test.model;

import java.io.Serializable;


public class Event implements Serializable
{
    private int id;

    private String name;

    private String description;

    public Event()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
}
