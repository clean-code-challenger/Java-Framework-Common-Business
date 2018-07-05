package common.extern.utils.xml.writer.xerces.util;

public class SymbolTable
{
    protected static final class Entry
    {

        public String symbol;
        public char characters[];
        public Entry next;

        public Entry(String s, Entry entry)
        {
            symbol = s.intern();
            characters = new char[s.length()];
            s.getChars(0, characters.length, characters, 0);
            next = entry;
        }

        public Entry(char ac[], int i, int j, Entry entry)
        {
            characters = new char[j];
            System.arraycopy(ac, i, characters, 0, j);
            symbol = (new String(characters)).intern();
            next = entry;
        }
    }


    public SymbolTable()
    {
        this(101);
    }

    public SymbolTable(int i)
    {
        fBuckets = null;
        fTableSize = i;
        fBuckets = new Entry[fTableSize];
    }

    public String addSymbol(String s)
    {
        int i = hash(s) % fTableSize;
        int j = s.length();
label0:
        for(Entry entry = fBuckets[i]; entry != null; entry = entry.next)
        {
            if(j != entry.characters.length)
                continue;
            for(int k = 0; k < j; k++)
                if(s.charAt(k) != entry.characters[k])
                    continue label0;

            return entry.symbol;
        }

        Entry entry1 = new Entry(s, fBuckets[i]);
        fBuckets[i] = entry1;
        return entry1.symbol;
    }

    public String addSymbol(char ac[], int i, int j)
    {
        int k = hash(ac, i, j) % fTableSize;
label0:
        for(Entry entry = fBuckets[k]; entry != null; entry = entry.next)
        {
            if(j != entry.characters.length)
                continue;
            for(int l = 0; l < j; l++)
                if(ac[i + l] != entry.characters[l])
                    continue label0;

            return entry.symbol;
        }

        Entry entry1 = new Entry(ac, i, j, fBuckets[k]);
        fBuckets[k] = entry1;
        return entry1.symbol;
    }

    public int hash(String s)
    {
        int i = 0;
        int j = s.length();
        for(int k = 0; k < j; k++)
            i = i * 37 + s.charAt(k);

        return i & 0x7ffffff;
    }

    public int hash(char ac[], int i, int j)
    {
        int k = 0;
        for(int l = 0; l < j; l++)
            k = k * 37 + ac[i + l];

        return k & 0x7ffffff;
    }

    public boolean containsSymbol(String s)
    {
        int i = hash(s) % fTableSize;
        int j = s.length();
label0:
        for(Entry entry = fBuckets[i]; entry != null; entry = entry.next)
        {
            if(j != entry.characters.length)
                continue;
            for(int k = 0; k < j; k++)
                if(s.charAt(k) != entry.characters[k])
                    continue label0;

            return true;
        }

        return false;
    }

    public boolean containsSymbol(char ac[], int i, int j)
    {
        int k = hash(ac, i, j) % fTableSize;
label0:
        for(Entry entry = fBuckets[k]; entry != null; entry = entry.next)
        {
            if(j != entry.characters.length)
                continue;
            for(int l = 0; l < j; l++)
                if(ac[i + l] != entry.characters[l])
                    continue label0;
            return true;
        }

        return false;
    }

    protected static final int TABLE_SIZE = 101;
    protected Entry fBuckets[];
    protected int fTableSize;
}