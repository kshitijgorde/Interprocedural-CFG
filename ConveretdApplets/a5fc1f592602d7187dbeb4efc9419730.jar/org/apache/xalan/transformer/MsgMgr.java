// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xalan.res.XSLMessages;
import org.w3c.dom.Node;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;

public class MsgMgr
{
    private TransformerImpl m_transformer;
    
    public MsgMgr(final TransformerImpl transformer) {
        this.m_transformer = transformer;
    }
    
    public void message(final SourceLocator srcLctr, final String msg, final boolean terminate) throws TransformerException {
        final ErrorListener errHandler = this.m_transformer.getErrorListener();
        if (null != errHandler) {
            errHandler.warning(new TransformerException(msg, srcLctr));
        }
        else {
            if (terminate) {
                throw new TransformerException(msg, srcLctr);
            }
            System.out.println(msg);
        }
    }
    
    public void warn(final SourceLocator srcLctr, final String msg) throws TransformerException {
        this.warn(srcLctr, null, null, msg, null);
    }
    
    public void warn(final SourceLocator srcLctr, final String msg, final Object[] args) throws TransformerException {
        this.warn(srcLctr, null, null, msg, args);
    }
    
    public void warn(final SourceLocator srcLctr, final Node styleNode, final Node sourceNode, final String msg) throws TransformerException {
        this.warn(srcLctr, styleNode, sourceNode, msg, null);
    }
    
    public void warn(final SourceLocator srcLctr, final Node styleNode, final Node sourceNode, final String msg, final Object[] args) throws TransformerException {
        final String formattedMsg = XSLMessages.createWarning(msg, args);
        final ErrorListener errHandler = this.m_transformer.getErrorListener();
        if (null != errHandler) {
            errHandler.warning(new TransformerException(formattedMsg, srcLctr));
        }
        else {
            System.out.println(formattedMsg);
        }
    }
    
    public void error(final SourceLocator srcLctr, final String msg) throws TransformerException {
        this.error(srcLctr, null, null, msg, null);
    }
    
    public void error(final SourceLocator srcLctr, final String msg, final Object[] args) throws TransformerException {
        this.error(srcLctr, null, null, msg, args);
    }
    
    public void error(final SourceLocator srcLctr, final String msg, final Exception e) throws TransformerException {
        this.error(srcLctr, msg, null, e);
    }
    
    public void error(final SourceLocator srcLctr, final String msg, final Object[] args, final Exception e) throws TransformerException {
        final String formattedMsg = XSLMessages.createMessage(msg, args);
        final ErrorListener errHandler = this.m_transformer.getErrorListener();
        if (null != errHandler) {
            errHandler.fatalError(new TransformerException(formattedMsg, srcLctr));
            return;
        }
        throw new TransformerException(formattedMsg, srcLctr);
    }
    
    public void error(final SourceLocator srcLctr, final Node styleNode, final Node sourceNode, final String msg) throws TransformerException {
        this.error(srcLctr, styleNode, sourceNode, msg, null);
    }
    
    public void error(final SourceLocator srcLctr, final Node styleNode, final Node sourceNode, final String msg, final Object[] args) throws TransformerException {
        final String formattedMsg = XSLMessages.createMessage(msg, args);
        final ErrorListener errHandler = this.m_transformer.getErrorListener();
        if (null != errHandler) {
            errHandler.fatalError(new TransformerException(formattedMsg, srcLctr));
            return;
        }
        throw new TransformerException(formattedMsg, srcLctr);
    }
}
