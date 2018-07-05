package common.extern.utils.Json.annotations;

import java.lang.annotation.Annotation;

public interface SMD extends Annotation
{

    public abstract String version();

    public abstract String objectName();

    public abstract String serviceType();
}