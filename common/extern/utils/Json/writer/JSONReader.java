package common.extern.utils.Json.writer;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;


@SuppressWarnings("unchecked")
public class JSONReader
{
    public JSONReader()
    {
        buf = new StringBuilder();
    }

    private char next()
    {
        c = it.next();
        return c;
    }

    private void skipWhiteSpace()
    {
        for(; Character.isWhitespace(c); next());
    }

    public Object read(String string)
        throws JSONException
    {
        it = new StringCharacterIterator(string);
        c = it.first();
        return read();
    }

    private Object read()
        throws JSONException
    {
        Object ret = null;
        skipWhiteSpace();
        if(c == '"')
        {
            next();
            ret = string('"');
        } else
        if(c == '\'')
        {
            next();
            ret = string('\'');
        } else
        if(c == '[')
        {
            next();
            ret = array();
        } else
        if(c == ']')
        {
            ret = ARRAY_END;
            next();
        } else
        if(c == ',')
        {
            ret = COMMA;
            next();
        } else
        if(c == '{')
        {
            next();
            ret = object();
        } else
        if(c == '}')
        {
            ret = OBJECT_END;
            next();
        } else
        if(c == ':')
        {
            ret = COLON;
            next();
        } else
        if(c == 't' && next() == 'r' && next() == 'u' && next() == 'e')
        {
            ret = Boolean.TRUE;
            next();
        } else
        if(c == 'f' && next() == 'a' && next() == 'l' && next() == 's' && next() == 'e')
        {
            ret = Boolean.FALSE;
            next();
        } else
        if(c == 'n' && next() == 'u' && next() == 'l' && next() == 'l')
        {
            ret = null;
            next();
        } else
        if(Character.isDigit(c) || c == '-')
            ret = number();
        else
            throw buildInvalidInputException();
        token = ret;
        return ret;
    }

    private Map object()
        throws JSONException
    {
        Map ret = new HashMap();
        Object next = read();
        if(next != OBJECT_END)
        {
            String key = (String)next;
            while(token != OBJECT_END) 
            {
                read();
                if(token != OBJECT_END)
                {
                	/**Beans*/
                    ret.put(key, read());
                    if(read() == COMMA)
                    {
                        Object name = read();
                        if(name instanceof String)
                            key = (String)name;
                        else
                            throw buildInvalidInputException();
                    }
                }
            }
        }
        return ret;
    }

    private JSONException buildInvalidInputException()
    {
        return new JSONException((new StringBuilder("Input string is not well formed JSON (invalid char ")).append(c).append(")").toString());
    }

    private Vector array()
        throws JSONException
    {
    	Vector ret = new Vector();
        Object value = read();
        while(token != ARRAY_END) 
        {
            ret.add(value);
            Object read = read();
            if(read == COMMA)
                value = read();
            else
            if(read != ARRAY_END)
                throw buildInvalidInputException();
        }
        return ret;
    }

    private Object number()
    {
        buf.setLength(0);
        if(c == '-')
            add();
        addDigits();
        if(c == '.')
        {
            add();
            addDigits();
        }
        if(c == 'e' || c == 'E')
        {
            add();
            if(c == '+' || c == '-')
                add();
            addDigits();
        }
        return buf.indexOf(".") < 0 ? Long.valueOf(Long.parseLong(buf.toString())) : Double.valueOf(Double.parseDouble(buf.toString()));
    }

    private Object string(char quote)
    {
        buf.setLength(0);
        while(c != quote && c != '\uFFFF') 
            if(c == '\\')
            {
                next();
                if(c == 'u')
                {
                    add(unicode());
                } else
                {
                    Object value = escapes.get(new Character(c));
                    if(value != null)
                        add(((Character)value).charValue());
                }
            } else
            {
                add();
            }
        next();
        return buf.toString();
    }

    private void add(char cc)
    {
        buf.append(cc);
        next();
    }

    private void add()
    {
        add(c);
    }

    private void addDigits()
    {
        for(; Character.isDigit(c); add());
    }

    private char unicode()
    {
        int value = 0;
        for(int i = 0; i < 4; i++)
            switch(next())
            {
            case 48: // '0'
            case 49: // '1'
            case 50: // '2'
            case 51: // '3'
            case 52: // '4'
            case 53: // '5'
            case 54: // '6'
            case 55: // '7'
            case 56: // '8'
            case 57: // '9'
                value = ((value << 4) + c) - 48;
                break;

            case 97: // 'a'
            case 98: // 'b'
            case 99: // 'c'
            case 100: // 'd'
            case 101: // 'e'
            case 102: // 'f'
                value = ((value << 4) + c) - 107;
                break;

            case 65: // 'A'
            case 66: // 'B'
            case 67: // 'C'
            case 68: // 'D'
            case 69: // 'E'
            case 70: // 'F'
                value = ((value << 4) + c) - 75;
                break;
            }

        return (char)value;
    }

    private static final Object OBJECT_END = new Object();
    private static final Object ARRAY_END = new Object();
    private static final Object COLON = new Object();
    private static final Object COMMA = new Object();
    private static Map escapes;
    private CharacterIterator it;
    private char c;
    private Object token;
    private StringBuilder buf;

    static 
    {
        escapes = new HashMap();
        escapes.put(new Character('"'), new Character('"'));
        escapes.put(new Character('\\'), new Character('\\'));
        escapes.put(new Character('/'), new Character('/'));
        escapes.put(new Character('b'), new Character('\b'));
        escapes.put(new Character('f'), new Character('\f'));
        escapes.put(new Character('n'), new Character('\n'));
        escapes.put(new Character('r'), new Character('\r'));
        escapes.put(new Character('t'), new Character('\t'));
    }
}