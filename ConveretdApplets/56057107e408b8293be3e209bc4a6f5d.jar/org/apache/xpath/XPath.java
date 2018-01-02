// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import org.apache.xpath.compiler.FunctionTable;
import org.apache.xpath.functions.Function;
import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xpath.objects.XObject;
import org.apache.xml.utils.SAXSourceLocator;
import org.w3c.dom.Node;
import org.apache.xalan.res.XSLMessages;
import org.apache.xpath.compiler.Compiler;
import org.apache.xpath.compiler.XPathParser;
import org.apache.xml.utils.DefaultErrorHandler;
import javax.xml.transform.TransformerException;
import javax.xml.transform.ErrorListener;
import org.apache.xml.utils.PrefixResolver;
import javax.xml.transform.SourceLocator;
import java.io.Serializable;

public class XPath implements Serializable
{
    private Expression m_mainExp;
    String m_patternString;
    public static final int SELECT = 0;
    public static final int MATCH = 1;
    private static final boolean DEBUG_MATCHES = false;
    public static final double MATCH_SCORE_NONE = Double.NEGATIVE_INFINITY;
    public static final double MATCH_SCORE_QNAME = 0.0;
    public static final double MATCH_SCORE_NSWILD = -0.25;
    public static final double MATCH_SCORE_NODETEST = -0.5;
    public static final double MATCH_SCORE_OTHER = 0.5;
    
    public XPath(final String exprString, final SourceLocator locator, final PrefixResolver prefixResolver, final int type) throws TransformerException {
        this(exprString, locator, prefixResolver, type, null);
    }
    
    public XPath(final String exprString, final SourceLocator locator, final PrefixResolver prefixResolver, final int type, ErrorListener errorListener) throws TransformerException {
        if (errorListener == null) {
            errorListener = new DefaultErrorHandler();
        }
        this.m_patternString = exprString;
        final XPathParser parser = new XPathParser(errorListener, locator);
        final Compiler compiler = new Compiler(errorListener, locator);
        if (type == 0) {
            parser.initXPath(compiler, exprString, prefixResolver);
        }
        else {
            if (type != 1) {
                throw new RuntimeException("Can not deal with XPath type: " + type);
            }
            parser.initMatchPattern(compiler, exprString, prefixResolver);
        }
        final Expression expr = compiler.compile(0);
        this.setExpression(expr);
    }
    
    public void assert(final boolean b, final String msg) {
        if (!b) {
            final String fMsg = XSLMessages.createXPATHMessage(30, new Object[] { msg });
            throw new RuntimeException(fMsg);
        }
    }
    
    public void error(final XPathContext xctxt, final Node sourceNode, final int msg, final Object[] args) throws TransformerException {
        final String fmsg = XSLMessages.createXPATHMessage(msg, args);
        final ErrorListener ehandler = xctxt.getErrorListener();
        if (ehandler != null) {
            ehandler.fatalError(new TransformerException(fmsg, xctxt.getSAXLocator()));
        }
        else {
            final SourceLocator slocator = xctxt.getSAXLocator();
            System.out.println(String.valueOf(fmsg) + "; file " + slocator.getSystemId() + "; line " + slocator.getLineNumber() + "; column " + slocator.getColumnNumber());
        }
    }
    
    public XObject execute(final XPathContext xctxt, final Node contextNode, final PrefixResolver namespaceContext) throws TransformerException {
        final PrefixResolver savedPrefixResolver = xctxt.getNamespaceContext();
        xctxt.m_currentPrefixResolver = namespaceContext;
        xctxt.pushCurrentNodeAndExpression(contextNode, contextNode);
        XObject xobj = null;
        try {
            xobj = this.m_mainExp.execute(xctxt);
        }
        catch (TransformerException te) {
            te.setLocator(this.getLocator());
            final ErrorListener el = xctxt.getErrorListener();
            if (el == null) {
                throw te;
            }
            el.error(te);
        }
        catch (Exception e) {
            while (e instanceof WrappedRuntimeException) {
                e = ((WrappedRuntimeException)e).getException();
            }
            String msg = e.getMessage();
            msg = ((msg == null || msg.length() == 0) ? "Unknown error in XPath" : msg);
            final TransformerException te2 = new TransformerException(msg, this.getLocator(), e);
            final ErrorListener el2 = xctxt.getErrorListener();
            if (el2 == null) {
                throw te2;
            }
            el2.fatalError(te2);
        }
        finally {
            xctxt.m_currentPrefixResolver = savedPrefixResolver;
            xctxt.popCurrentNodeAndExpression();
        }
        return xobj;
    }
    
    public Expression getExpression() {
        return this.m_mainExp;
    }
    
    public SourceLocator getLocator() {
        return this.m_mainExp.m_slocator;
    }
    
    public double getMatchScore(final XPathContext xctxt, final Node context) throws TransformerException {
        xctxt.pushCurrentNode(context);
        xctxt.pushCurrentExpressionNode(context);
        try {
            final XObject score = this.m_mainExp.execute(xctxt);
            return score.num();
        }
        finally {
            xctxt.popCurrentNode();
            xctxt.popCurrentExpressionNode();
        }
    }
    
    public String getPatternString() {
        return this.m_patternString;
    }
    
    public void installFunction(final String name, final int funcIndex, final Function func) {
        FunctionTable.installFunction(func, funcIndex);
    }
    
    public void setExpression(final Expression exp) {
        this.m_mainExp = exp;
    }
    
    public void setLocator(final SourceLocator l) {
        this.m_mainExp.setSourceLocator(l);
    }
    
    public void warn(final XPathContext xctxt, final Node sourceNode, final int msg, final Object[] args) throws TransformerException {
        final String fmsg = XSLMessages.createXPATHWarning(msg, args);
        final ErrorListener ehandler = xctxt.getErrorListener();
        if (ehandler != null) {
            ehandler.warning(new TransformerException(fmsg, xctxt.getSAXLocator()));
        }
    }
}
