// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.input;

import org.jdom.EntityRef;
import org.jdom.ProcessingInstruction;
import com.sun.java.util.collections.Map;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.DocType;
import org.jdom.Comment;
import org.jdom.Text;
import org.jdom.CDATA;
import org.jdom.Attribute;
import org.jdom.Namespace;

public interface JDOMFactory
{
    Attribute attribute(final String p0, final String p1, final Namespace p2);
    
    Attribute attribute(final String p0, final String p1, final int p2, final Namespace p3);
    
    Attribute attribute(final String p0, final String p1);
    
    Attribute attribute(final String p0, final String p1, final int p2);
    
    CDATA cdata(final String p0);
    
    Text text(final String p0);
    
    Comment comment(final String p0);
    
    DocType docType(final String p0, final String p1, final String p2);
    
    DocType docType(final String p0, final String p1);
    
    DocType docType(final String p0);
    
    Document document(final Element p0, final DocType p1);
    
    Document document(final Element p0);
    
    Element element(final String p0, final Namespace p1);
    
    Element element(final String p0);
    
    Element element(final String p0, final String p1);
    
    Element element(final String p0, final String p1, final String p2);
    
    ProcessingInstruction processingInstruction(final String p0, final Map p1);
    
    ProcessingInstruction processingInstruction(final String p0, final String p1);
    
    EntityRef entityRef(final String p0);
    
    EntityRef entityRef(final String p0, final String p1, final String p2);
}
