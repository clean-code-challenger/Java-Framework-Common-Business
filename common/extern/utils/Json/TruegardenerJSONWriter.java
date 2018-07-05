package common.extern.utils.Json;

import java.util.*;

public class TruegardenerJSONWriter
{
	public Object JSONWriter = null;
	private static boolean m_bExternalComGJAJExist = true;
	static{		
		try {
    		if(Class.forName("com.googlecode.jsonplugin.annotations.JSON")!=null) {
    			m_bExternalComGJAJExist = true;
    		}
    		else {
    			m_bExternalComGJAJExist = false;
    		}
    	}
    	catch (ClassNotFoundException e) {
    		m_bExternalComGJAJExist = false;
		}
	}
	
    public TruegardenerJSONWriter()
    {
    	if(m_bExternalComGJAJExist) {
    		JSONWriter = new common.extern.utils.Json.writer.JSONGoogleWriter();
    	}
    	else {
    		JSONWriter = new common.extern.utils.Json.writer.JSONWriter();
    	}
    }

    public String write(Object object) throws Exception
    {
    	if(m_bExternalComGJAJExist) {
    		return ((common.extern.utils.Json.writer.JSONGoogleWriter)JSONWriter).write(object);
    	}
    	else {
    		return ((common.extern.utils.Json.writer.JSONWriter)JSONWriter).write(object);
    	}
    }

    public String write(Object object, Collection excludeProperties, Collection includeProperties) throws Exception
    {
    	if(m_bExternalComGJAJExist) {
    		return ((common.extern.utils.Json.writer.JSONGoogleWriter)JSONWriter).write(object, excludeProperties, includeProperties);
    	}
    	else {
    		return ((common.extern.utils.Json.writer.JSONWriter)JSONWriter).write(object, excludeProperties, includeProperties);
    	}
    }


    public void setIgnoreHierarchy(boolean ignoreHierarchy)
    {
    	if(m_bExternalComGJAJExist) {
    		((common.extern.utils.Json.writer.JSONGoogleWriter)JSONWriter).setIgnoreHierarchy(ignoreHierarchy);
    	}
    	else {
    		((common.extern.utils.Json.writer.JSONWriter)JSONWriter).setIgnoreHierarchy(ignoreHierarchy);
    	}
    }

    public void setEnumAsBean(boolean enumAsBean)
    {
    	if(m_bExternalComGJAJExist) {
    		((common.extern.utils.Json.writer.JSONGoogleWriter)JSONWriter).setEnumAsBean(enumAsBean);
    	}
    	else {
    		((common.extern.utils.Json.writer.JSONWriter)JSONWriter).setEnumAsBean(enumAsBean);
    	}
    }
}