package common.extern.utils.Json.writer;

import java.beans.*;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.*;
import java.util.*;
import java.util.regex.Pattern;

import common.extern.utils.Json.annotations.JSON;

public class JSONWriter
{
    public JSONWriter()
    {
        buf = new StringBuilder();
        stack = new Stack();
        ignoreHierarchy = true;
        buildExpr = true;
        exprStack = "";
        enumAsBean = false;
    }

    public String write(Object object)
        throws JSONException
    {
        return write(object, null, null);
    }

    public String write(Object object, Collection excludeProperties, Collection includeProperties)
        throws JSONException
    {
        buf.setLength(0);
        root = object;
        exprStack = "";
        buildExpr = excludeProperties != null && !excludeProperties.isEmpty() || includeProperties != null && !includeProperties.isEmpty();
        this.excludeProperties = excludeProperties;
        this.includeProperties = includeProperties;
        value(object, null);
        return buf.toString();
    }

    private void value(Object object, Method method)
        throws JSONException
    {
        if(object == null)
        {
            add("null");
            return;
        }
        if(stack.contains(object))
        {
            Class clazz = object.getClass();
            if(clazz.isPrimitive() || clazz.equals(java.lang.String.class))
            {
                process(object, method);
            }
            else
            {
                add("null");
            }
            return;
        }
        else
        {
            process(object, method);
            return;
        }
    }

    @SuppressWarnings("unchecked")
	private void process(Object object, Method method)
        throws JSONException
    {
        stack.push(object);
        if(object instanceof Class)
            string(object);
        else
        if(object instanceof Boolean)
            bool(((Boolean)object).booleanValue());
        else
        if(object instanceof Number)
            add(object);
        else
        if(object instanceof String)
            string(object);
        else
        if(object instanceof Character)
            string(object);
        else
        if(object instanceof Map)
            map((Map)object, method);
        else
        if(object.getClass().isArray())
            array(object, method);
        else
        if(object instanceof Iterable)
            array(((Iterable)object).iterator(), method);
        else
        if(object instanceof Date)
            date((Date)object, method);
        else
        if(object instanceof Calendar)
            date(((Calendar)object).getTime(), method);
        else
        if(object instanceof Locale)
            string(object);
        else
        if(object instanceof Enum)
            enumeration((Enum)object);
        else
            bean(object);
        stack.pop();
    }

    private void bean(Object object)
        throws JSONException
    {
        add("{");
        try
        {
            Class clazz = object.getClass();
            BeanInfo info = object != root || !ignoreHierarchy ? Introspector.getBeanInfo(clazz) : Introspector.getBeanInfo(clazz, clazz.getSuperclass());
            PropertyDescriptor props[] = info.getPropertyDescriptors();
            boolean hasData = false;
            for(int i = 0; i < props.length; i++)
            {
                PropertyDescriptor prop = props[i];
                String name = prop.getName();
                Method accessor = prop.getReadMethod();
                Method baseAccessor = null;
                if(clazz.getName().indexOf("$$EnhancerByCGLIB$$") > -1)
                {
                	try
                	{
                		baseAccessor = Class.forName(clazz.getName().substring(0, clazz.getName().indexOf("$$"))).getMethod(accessor.getName(), accessor.getParameterTypes());
                	}
                	catch(Exception ex)
                	{
                		throw ex;
                	}
                }
                else
                    baseAccessor = accessor;
                if(baseAccessor == null)
                    continue;
                JSON json = (JSON)baseAccessor.getAnnotation(common.extern.utils.Json.annotations.JSON.class);
                if(json != null)
                {
                    if(!json.serialize())
                        continue;
                    if(json.name().length() > 0)
                        name = json.name();
                }
                if(shouldExcludeProperty(clazz, prop))
                    continue;
                String expr = null;
                if(buildExpr)
                {
                    expr = expandExpr(name);
                    if(shouldExcludeProperty(expr))
                        continue;
                    expr = setExprStack(expr);
                }
                if(hasData)
                    add(',');
                hasData = true;
                Object value = accessor.invoke(object, new Object[0]);
                add(name, value, accessor);
                if(buildExpr)
                    setExprStack(expr);
            }

            if(object instanceof Enum)
            {
                if(hasData)
                    add(',');
                Object value = ((Enum)object).name();
                add("_name", value, object.getClass().getMethod("name", new Class[0]));
            }
        }
        catch(Exception e)
        {
            throw new JSONException(e);
        }
        add("}");
    }

    private void enumeration(Enum enumeration)
        throws JSONException
    {
        if(enumAsBean)
            bean(enumeration);
        else
            string(enumeration.name());
    }

    private boolean shouldExcludeProperty(Class clazz, PropertyDescriptor prop)
        throws SecurityException, NoSuchFieldException
    {
        String name = prop.getName();
        return name.equals("class") || name.equals("declaringClass");
    }

    private String expandExpr(int i)
    {
        return (new StringBuilder()).append(exprStack).append("[").append(i).append("]").toString();
    }

    private String expandExpr(String property)
    {
        if(exprStack.length() == 0)
            return property;
        else
            return (new StringBuilder()).append(exprStack).append(".").append(property).toString();
    }

    private String setExprStack(String expr)
    {
        String s = exprStack;
        exprStack = expr;
        return s;
    }

    private boolean shouldExcludeProperty(String expr)
    {
    	label0:
        {
            if(excludeProperties == null)
                break label0;
            Iterator i$ = excludeProperties.iterator();
            Pattern pattern;
            do
            {
                if(!i$.hasNext())
                    break label0;
                pattern = (Pattern)i$.next();
            } while(!pattern.matcher(expr).matches());
            return true;
        }
        if(includeProperties != null)
        {
            for(Iterator i$ = includeProperties.iterator(); i$.hasNext();)
            {
                Pattern pattern = (Pattern)i$.next();
                if(pattern.matcher(expr).matches())
                    return false;
            }

            return true;
        } else
        {
            return false;
        }
    }

    private void add(String name, Object value, Method method)
        throws JSONException
    {
        add('"');
        add(name);
        add("\":");
        value(value, method);
    }

    private void map(Map map, Method method)
        throws JSONException
    {
        add("{");
        Iterator it = map.entrySet().iterator();
        boolean hasData = false;
        do
        {
            if(!it.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
            Object key = entry.getKey();
            String expr = null;
            if(buildExpr)
            {
                if(key == null)
                {
                    continue;
                }
                expr = expandExpr(key.toString());
                if(shouldExcludeProperty(expr))
                    continue;
                expr = setExprStack(expr);
            }
            if(hasData)
                add(',');
            hasData = true;
            value(key, method);
            add(":");
            value(entry.getValue(), method);
            if(buildExpr)
                setExprStack(expr);
        } while(true);
        add("}");
    }

    private void date(Date date, Method method)
    {
        JSON json = (JSON)method.getAnnotation(common.extern.utils.Json.annotations.JSON.class);
        if(this.formatter == null)
            this.formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DateFormat formatter = ((DateFormat) (json == null || json.format().length() <= 0 ? this.formatter : ((DateFormat) (new SimpleDateFormat(json.format())))));
        string(formatter.format(date));
    }

    private void array(Iterator it, Method method)
        throws JSONException
    {
        add("[");
        boolean hasData = false;
        for(int i = 0; it.hasNext(); i++)
        {
            String expr = null;
            if(buildExpr)
            {
                expr = expandExpr(i);
                if(shouldExcludeProperty(expr))
                    continue;
                expr = setExprStack(expr);
            }
            if(hasData)
                add(',');
            hasData = true;
            value(it.next(), method);
            if(buildExpr)
                setExprStack(expr);
        }

        add("]");
    }

    private void array(Object object, Method method)
        throws JSONException
    {
        add("[");
        int length = Array.getLength(object);
        boolean hasData = false;
        for(int i = 0; i < length; i++)
        {
            String expr = null;
            if(buildExpr)
            {
                expr = expandExpr(i);
                if(shouldExcludeProperty(expr))
                    continue;
                expr = setExprStack(expr);
            }
            if(hasData)
                add(',');
            hasData = true;
            value(Array.get(object, i), method);
            if(buildExpr)
                setExprStack(expr);
        }

        add("]");
    }

    private void bool(boolean b)
    {
        add(b ? "true" : "false");
    }

    private void string(Object obj)
    {
        add('"');
        CharacterIterator it = new StringCharacterIterator(obj.toString());
        for(char c = it.first(); c != '\uFFFF'; c = it.next())
        {
            if(c == '"')
            {
                add("\\\"");
                continue;
            }
            if(c == '\\')
            {
                add("\\\\");
                continue;
            }
            if(c == '/')
            {
                add("\\/");
                continue;
            }
            if(c == '\b')
            {
                add("\\b");
                continue;
            }
            if(c == '\f')
            {
                add("\\f");
                continue;
            }
            if(c == '\n')
            {
                add("\\n");
                continue;
            }
            if(c == '\r')
            {
                add("\\r");
                continue;
            }
            if(c == '\t')
            {
                add("\\t");
                continue;
            }
            if(Character.isISOControl(c))
                unicode(c);
            else
                add(c);
        }

        add('"');
    }

    private void add(Object obj)
    {
        buf.append(obj);
    }

    private void add(char c)
    {
        buf.append(c);
    }

    private void unicode(char c)
    {
        add("\\u");
        int n = c;
        for(int i = 0; i < 4; i++)
        {
            int digit = (n & 0xf000) >> 12;
            add(hex[digit]);
            n <<= 4;
        }

    }

    public void setIgnoreHierarchy(boolean ignoreHierarchy)
    {
        this.ignoreHierarchy = ignoreHierarchy;
    }

    public void setEnumAsBean(boolean enumAsBean)
    {
        this.enumAsBean = enumAsBean;
    }

    public static final boolean ENUM_AS_BEAN_DEFAULT = false;
    static char hex[] = "0123456789ABCDEF".toCharArray();
    private StringBuilder buf;
    private Stack stack;
    private boolean ignoreHierarchy;
    private Object root;
    private boolean buildExpr;
    private String exprStack;
    private Collection excludeProperties;
    private Collection includeProperties;
    private DateFormat formatter;
    private boolean enumAsBean;

}