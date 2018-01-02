// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xml;

import java.io.OutputStream;
import java.io.File;
import java.util.List;
import java.io.InputStream;
import java.net.URL;
import org.xmodel.IModelObject;
import org.xmodel.IModelObjectFactory;

public interface IXmlIO
{
    void setWhitespace(final Whitespace p0);
    
    void setFactory(final IModelObjectFactory p0);
    
    void setMaxLines(final int p0);
    
    IModelObject read(final String p0) throws XmlException;
    
    IModelObject read(final URL p0) throws XmlException;
    
    IModelObject read(final InputStream p0) throws XmlException;
    
    List<IModelObject> getLineInformation();
    
    String write(final IModelObject p0);
    
    void write(final IModelObject p0, final File p1) throws XmlException;
    
    void write(final IModelObject p0, final OutputStream p1) throws XmlException;
    
    String write(final int p0, final IModelObject p1);
    
    void write(final int p0, final IModelObject p1, final File p2) throws XmlException;
    
    void write(final int p0, final IModelObject p1, final OutputStream p2) throws XmlException;
    
    void skipInputPrefix(final String p0);
    
    void skipOutputPrefix(final String p0);
    
    void setOutputStyle(final Style p0);
    
    public enum Style
    {
        compact("compact", 0), 
        printable("printable", 1);
        
        static {
            A = new Style[] { Style.compact, Style.printable };
        }
        
        private Style(final String s, final int n) {
        }
    }
    
    public enum Whitespace
    {
        keep("keep", 0), 
        trim("trim", 1);
        
        static {
            A = new Whitespace[] { Whitespace.keep, Whitespace.trim };
        }
        
        private Whitespace(final String s, final int n) {
        }
    }
}
