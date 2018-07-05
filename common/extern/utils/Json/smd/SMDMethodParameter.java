package common.extern.utils.Json.smd;

public class SMDMethodParameter
    implements Comparable
{

    public SMDMethodParameter(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int compareTo(Object o)
    {
        if(!(o instanceof SMDMethodParameter))
            return 1;
        if(o == null)
            return 1;
        if(name == null && ((SMDMethodParameter)o).name == null)
            return 0;
        if(name == null)
            return -1;
        else
            return name.compareTo(((SMDMethodParameter)o).name);
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof SMDMethodParameter))
            return false;
        if(name == null && ((SMDMethodParameter)o).name == null)
            return true;
        return name != null && name.equals(((SMDMethodParameter)o).name);
    }

    private String name;
}