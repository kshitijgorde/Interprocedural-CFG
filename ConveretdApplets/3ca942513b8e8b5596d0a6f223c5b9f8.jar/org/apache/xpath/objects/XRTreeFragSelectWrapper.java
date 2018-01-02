// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.apache.xml.dtm.DTMIterator;
import org.apache.xml.utils.XMLString;
import org.apache.xpath.res.XPATHMessages;
import javax.xml.transform.TransformerException;
import org.apache.xpath.XPathContext;
import java.util.Vector;
import org.apache.xpath.Expression;

public class XRTreeFragSelectWrapper extends XRTreeFrag implements Cloneable
{
    static final long serialVersionUID = -6526177905590461251L;
    
    public XRTreeFragSelectWrapper(final Expression expr) {
        super(expr);
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        ((Expression)super.m_obj).fixupVariables(vars, globalsSize);
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final XObject m_selected = ((Expression)super.m_obj).execute(xctxt);
        m_selected.allowDetachToRelease(super.m_allowRelease);
        if (m_selected.getType() == 3) {
            return m_selected;
        }
        return new XString(m_selected.str());
    }
    
    public void detach() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_DETACH_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
    }
    
    public double num() throws TransformerException {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NUM_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
    }
    
    public XMLString xstr() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_XSTR_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
    }
    
    public String str() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_STR_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
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
