// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import org.apache.xpath.objects.XObject;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.SourceLocator;
import java.io.Serializable;

public abstract class Expression implements Serializable
{
    protected SourceLocator m_slocator;
    
    public void assert(final boolean b, final String msg) throws TransformerException {
        if (!b) {
            final String fMsg = XSLMessages.createXPATHMessage(30, new Object[] { msg });
            throw new RuntimeException(fMsg);
        }
    }
    
    public boolean canTraverseOutsideSubtree() {
        return false;
    }
    
    public void error(final XPathContext xctxt, final int msg, final Object[] args) throws TransformerException {
        final String fmsg = XSLMessages.createXPATHMessage(msg, args);
        if (xctxt != null) {
            final ErrorListener eh = xctxt.getErrorListener();
            final TransformerException te = new TransformerException(fmsg, this.m_slocator);
            eh.fatalError(te);
        }
    }
    
    public abstract XObject execute(final XPathContext p0) throws TransformerException;
    
    public void setSourceLocator(final SourceLocator locator) {
        this.m_slocator = locator;
    }
    
    public void warn(final XPathContext xctxt, final int msg, final Object[] args) throws TransformerException {
        final String fmsg = XSLMessages.createXPATHWarning(msg, args);
        if (xctxt != null) {
            final ErrorListener eh = xctxt.getErrorListener();
            eh.warning(new TransformerException(fmsg, xctxt.getSAXLocator()));
        }
    }
}
