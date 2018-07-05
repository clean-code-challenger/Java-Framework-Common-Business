package common.extern.utils.Json.writer;

import java.util.*;

// Referenced classes of package com.googlecode.jsonplugin:
//            JSONException

public abstract class JSONCleaner
{

    public JSONCleaner()
    {
    }

    public Object clean(String ognlPrefix, Object data)
        throws JSONException
    {
        if(data == null)
            return null;
        if(data instanceof List)
            return cleanList(ognlPrefix, data);
        if(data instanceof Map)
            return cleanMap(ognlPrefix, data);
        else
            return cleanValue(ognlPrefix, data);
    }

    @SuppressWarnings("unchecked")
	protected Object cleanList(String ognlPrefix, Object data)
        throws JSONException
    {
        List list = (List)data;
        int count = list.size();
        for(int i = 0; i < count; i++)
            list.set(i, clean((new StringBuilder(String.valueOf(ognlPrefix))).append("[").append(i).append("]").toString(), list.get(i)));

        return list;
    }

    @SuppressWarnings("unchecked")
	protected Object cleanMap(String ognlPrefix, Object data)
        throws JSONException
    {
        Map map = (Map)data;
        java.util.Map.Entry e;
        for(Iterator iter = map.entrySet().iterator(); iter.hasNext(); e.setValue(clean((new StringBuilder(String.valueOf(ognlPrefix))).append(".").append(e.getKey()).toString(), e.getValue())))
            e = (java.util.Map.Entry)iter.next();

        return map;
    }

    protected abstract Object cleanValue(String s, Object obj)
        throws JSONException;
}