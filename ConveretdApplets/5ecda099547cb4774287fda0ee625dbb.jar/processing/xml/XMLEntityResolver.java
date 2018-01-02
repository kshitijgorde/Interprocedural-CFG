// 
// Decompiled by Procyon v0.5.30
// 

package processing.xml;

import java.io.StringReader;
import java.io.Reader;
import java.util.Hashtable;

public class XMLEntityResolver
{
    private Hashtable<String, Object> entities;
    
    public XMLEntityResolver() {
        (this.entities = new Hashtable<String, Object>()).put("amp", "&#38;");
        this.entities.put("quot", "&#34;");
        this.entities.put("apos", "&#39;");
        this.entities.put("lt", "&#60;");
        this.entities.put("gt", "&#62;");
    }
    
    protected void finalize() throws Throwable {
        this.entities.clear();
        this.entities = null;
        super.finalize();
    }
    
    public void addInternalEntity(final String s, final String s2) {
        if (!this.entities.containsKey(s)) {
            this.entities.put(s, s2);
        }
    }
    
    public void addExternalEntity(final String s, final String s2, final String s3) {
        if (!this.entities.containsKey(s)) {
            this.entities.put(s, new String[] { s2, s3 });
        }
    }
    
    public Reader getEntity(final StdXMLReader stdXMLReader, final String s) throws XMLParseException {
        final String[] value = this.entities.get(s);
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return new StringReader((String)(Object)value);
        }
        final String[] array = value;
        return this.openExternalEntity(stdXMLReader, array[0], array[1]);
    }
    
    public boolean isExternalEntity(final String s) {
        return !(this.entities.get(s) instanceof String);
    }
    
    protected Reader openExternalEntity(final StdXMLReader stdXMLReader, final String s, final String s2) throws XMLParseException {
        final String systemID = stdXMLReader.getSystemID();
        try {
            return stdXMLReader.openStream(s, s2);
        }
        catch (Exception ex) {
            throw new XMLParseException(systemID, stdXMLReader.getLineNr(), "Could not open external entity at system ID: " + s2);
        }
    }
}
