// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.transform.sax;

import javax.xml.transform.stream.StreamSource;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import javax.xml.transform.Source;

public class SAXSource implements Source
{
    public static final String FEATURE = "http://javax.xml.transform.sax.SAXSource/feature";
    private XMLReader reader;
    private InputSource inputSource;
    
    public SAXSource() {
    }
    
    public SAXSource(final XMLReader reader, final InputSource inputSource) {
        this.reader = reader;
        this.inputSource = inputSource;
    }
    
    public SAXSource(final InputSource inputSource) {
        this.inputSource = inputSource;
    }
    
    public void setXMLReader(final XMLReader reader) {
        this.reader = reader;
    }
    
    public XMLReader getXMLReader() {
        return this.reader;
    }
    
    public void setInputSource(final InputSource inputSource) {
        this.inputSource = inputSource;
    }
    
    public InputSource getInputSource() {
        return this.inputSource;
    }
    
    public void setSystemId(final String systemId) {
        if (null == this.inputSource) {
            this.inputSource = new InputSource(systemId);
        }
        else {
            this.inputSource.setSystemId(systemId);
        }
    }
    
    public String getSystemId() {
        return (null != this.inputSource) ? this.inputSource.getSystemId() : null;
    }
    
    public static InputSource sourceToInputSource(final Source source) {
        if (source instanceof SAXSource) {
            return ((SAXSource)source).getInputSource();
        }
        if (source instanceof StreamSource) {
            final StreamSource ss = (StreamSource)source;
            final InputSource isource = new InputSource(ss.getSystemId());
            isource.setByteStream(ss.getInputStream());
            isource.setCharacterStream(ss.getReader());
            isource.setPublicId(ss.getPublicId());
            return isource;
        }
        return null;
    }
}
