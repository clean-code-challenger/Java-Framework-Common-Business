package common.extern.utils.Json.smd;

import java.util.Set;
import java.util.TreeSet;

// Referenced classes of package com.googlecode.jsonplugin.smd:
//            SMDMethodParameter

public class SMDMethod implements Comparable
{
    private String name;
    private Set parameters;

    public SMDMethod(String name)
    {
        parameters = new TreeSet();
        this.name = name;
    }

    @SuppressWarnings("unchecked")
	public void addSMDMethodParameter(SMDMethodParameter parameter)
    {
        parameters.add(parameter);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set getParameters()
    {
        return parameters;
    }

    public int compareTo(Object o)
    {
        if(!(o instanceof SMDMethod))
            return 1;
        if(o == null)
            return 1;
        if(name == null && ((SMDMethod)o).name == null)
            return 0;
        if(name == null)
            return -1;
        else
            return name.compareTo(((SMDMethod)o).name);
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof SMDMethod))
            return false;
        SMDMethod toCompare = (SMDMethod)obj;
        if(name == null && toCompare.name == null)
            return true;
        return name != null && name.equals(toCompare.name) && parameters.size() == toCompare.parameters.size();
    }

}