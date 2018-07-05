package common.extern.utils.Json.annotations;

import java.lang.annotation.Annotation;

public interface JSON extends Annotation
{

    public abstract String name();

    public abstract boolean serialize();

    public abstract boolean deserialize();

    public abstract String format();
}