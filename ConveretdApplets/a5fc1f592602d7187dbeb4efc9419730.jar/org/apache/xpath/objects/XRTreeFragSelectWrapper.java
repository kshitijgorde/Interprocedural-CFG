// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.res.XPATHMessages;
import org.apache.xml.utils.XMLString;
import javax.xml.transform.TransformerException;
import org.apache.xpath.XPathContext;
import java.util.Vector;
import org.apache.xpath.Expression;

public class XRTreeFragSelectWrapper extends XRTreeFrag implements Cloneable
{
    XObject m_selected;
    
    public XRTreeFragSelectWrapper(final Expression expr) {
        super(expr);
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        ((Expression)super.m_obj).fixupVariables(vars, globalsSize);
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        (this.m_selected = ((Expression)super.m_obj).execute(xctxt)).allowDetachToRelease(super.m_allowRelease);
        if (this.m_selected.getType() == 3) {
            return this.m_selected;
        }
        return new XString(this.m_selected.str());
    }
    
    public void detach() {
        if (super.m_allowRelease) {
            this.m_selected.detach();
            this.m_selected = null;
        }
        super.detach();
    }
    
    public double num() throws TransformerException {
        return this.m_selected.num();
    }
    
    public XMLString xstr() {
        return this.m_selected.xstr();
    }
    
    public String str() {
        return this.m_selected.str();
    }
    
    public int getType() {
        return 3;
    }
    
    public int rtf() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_RTF_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
    }
    
    public DTMIterator asNodeIterator() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_RTF_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
    }
}
