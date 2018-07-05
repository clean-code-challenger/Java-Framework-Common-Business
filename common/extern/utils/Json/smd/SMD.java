package common.extern.utils.Json.smd;

import java.util.Set;
import java.util.TreeSet;

// Referenced classes of package com.googlecode.jsonplugin.smd:
//            SMDMethod

public class SMD
{
    public SMD()
    {
        version = ".1";
        serviceType = "JSON-RPC";
        methods = new TreeSet();
    }

    @SuppressWarnings("unchecked")
	public void addSMDMethod(SMDMethod method)
    {
        methods.add(method);
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getObjectName()
    {
        return objectName;
    }

    public void setObjectName(String objectName)
    {
        this.objectName = objectName;
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public void setServiceType(String serviceType)
    {
        this.serviceType = serviceType;
    }

    public String getServiceUrl()
    {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl)
    {
        this.serviceUrl = serviceUrl;
    }

    public Set getMethods()
    {
        return methods;
    }

    public static final String DEFAULT_VERSION = ".1";
    public static final String DEFAULT_SERVICE_TYPE = "JSON-RPC";
    private String version;
    private String objectName;
    private String serviceType;
    private String serviceUrl;
    private Set methods;
}