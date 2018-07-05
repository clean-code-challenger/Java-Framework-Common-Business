package common.extern.utils.xml.writer.xml;

import java.io.*;
import org.xml.sax.ContentHandler;
import org.xml.sax.DocumentHandler;

public interface Serializer
{

    public abstract void setOutputByteStream(OutputStream outputstream);

    public abstract void setOutputCharStream(Writer writer);

    public abstract void setOutputFormat(OutputFormat outputformat);

    @SuppressWarnings("deprecation")
	public abstract DocumentHandler asDocumentHandler()
        throws IOException;

    public abstract ContentHandler asContentHandler()
        throws IOException;

    public abstract DOMSerializer asDOMSerializer()
        throws IOException;
}