// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.functions.WrongNumberArgsException;
import javax.xml.transform.ErrorListener;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.Source;
import org.apache.xpath.SourceTreeManager;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.xml.utils.WrappedRuntimeException;
import java.io.IOException;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.XMLString;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.NodeSetDTM;
import org.apache.xpath.Expression;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.functions.Function2Args;

public class FuncDocument extends Function2Args
{
    static final long serialVersionUID = 2483304325971281424L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final int context = xctxt.getCurrentNode();
        final DTM dtm = xctxt.getDTM(context);
        final int docContext = dtm.getDocumentRoot(context);
        final XObject arg = this.getArg0().execute(xctxt);
        String base = "";
        final Expression arg1Expr = this.getArg1();
        if (null != arg1Expr) {
            final XObject arg2 = arg1Expr.execute(xctxt);
            if (4 == arg2.getType()) {
                final int baseNode = arg2.iter().nextNode();
                if (baseNode == -1) {
                    this.warn(xctxt, "WG_EMPTY_SECOND_ARG", null);
                    final XNodeSet nodes = new XNodeSet(xctxt.getDTMManager());
                    return nodes;
                }
                final DTM baseDTM = xctxt.getDTM(baseNode);
                base = baseDTM.getDocumentBaseURI();
            }
            else {
                arg2.iter();
            }
        }
        else {
            this.assertion(null != xctxt.getNamespaceContext(), "Namespace context can not be null!");
            base = xctxt.getNamespaceContext().getBaseIdentifier();
        }
        final XNodeSet nodes2 = new XNodeSet(xctxt.getDTMManager());
        final NodeSetDTM mnl = nodes2.mutableNodeset();
        final DTMIterator iterator = (4 == arg.getType()) ? arg.iter() : null;
        int pos = -1;
        while (null == iterator || -1 != (pos = iterator.nextNode())) {
            final XMLString ref = (null != iterator) ? xctxt.getDTM(pos).getStringValue(pos) : arg.xstr();
            if (null == arg1Expr && -1 != pos) {
                final DTM baseDTM2 = xctxt.getDTM(pos);
                base = baseDTM2.getDocumentBaseURI();
            }
            if (null == ref) {
                continue;
            }
            if (-1 == docContext) {
                this.error(xctxt, "ER_NO_CONTEXT_OWNERDOC", null);
            }
            final int indexOfColon = ref.indexOf(58);
            final int indexOfSlash = ref.indexOf(47);
            if (indexOfColon != -1 && indexOfSlash != -1 && indexOfColon < indexOfSlash) {
                base = null;
            }
            final int newDoc = this.getDoc(xctxt, context, ref.toString(), base);
            if (-1 != newDoc && !mnl.contains(newDoc)) {
                mnl.addElement(newDoc);
            }
            if (null == iterator) {
                break;
            }
            if (newDoc == -1) {
                break;
            }
        }
        return nodes2;
    }
    
    int getDoc(final XPathContext xctxt, final int context, String uri, final String base) throws TransformerException {
        final SourceTreeManager treeMgr = xctxt.getSourceTreeManager();
        Source source;
        int newDoc;
        try {
            source = treeMgr.resolveURI(base, uri, xctxt.getSAXLocator());
            newDoc = treeMgr.getNode(source);
        }
        catch (IOException ioe) {
            throw new TransformerException(ioe.getMessage(), xctxt.getSAXLocator(), ioe);
        }
        catch (TransformerException te) {
            throw new TransformerException(te);
        }
        if (-1 != newDoc) {
            return newDoc;
        }
        if (uri.length() == 0) {
            uri = xctxt.getNamespaceContext().getBaseIdentifier();
            try {
                source = treeMgr.resolveURI(base, uri, xctxt.getSAXLocator());
            }
            catch (IOException ioe) {
                throw new TransformerException(ioe.getMessage(), xctxt.getSAXLocator(), ioe);
            }
        }
        String diagnosticsString = null;
        try {
            if (null != uri && uri.toString().length() > 0) {
                newDoc = treeMgr.getSourceTree(source, xctxt.getSAXLocator(), xctxt);
            }
            else {
                this.warn(xctxt, "WG_CANNOT_MAKE_URL_FROM", new Object[] { ((base == null) ? "" : base) + uri });
            }
        }
        catch (Throwable throwable) {
            newDoc = -1;
            while (throwable instanceof WrappedRuntimeException) {
                throwable = ((WrappedRuntimeException)throwable).getException();
            }
            if (throwable instanceof NullPointerException || throwable instanceof ClassCastException) {
                throw new WrappedRuntimeException((Exception)throwable);
            }
            final StringWriter sw = new StringWriter();
            final PrintWriter diagnosticsWriter = new PrintWriter(sw);
            if (throwable instanceof TransformerException) {
                Throwable e;
                final TransformerException spe = (TransformerException)(e = throwable);
                while (null != e) {
                    if (null != e.getMessage()) {
                        diagnosticsWriter.println(" (" + e.getClass().getName() + "): " + e.getMessage());
                    }
                    if (e instanceof TransformerException) {
                        final TransformerException spe2 = (TransformerException)e;
                        final SourceLocator locator = spe2.getLocator();
                        if (null != locator && null != locator.getSystemId()) {
                            diagnosticsWriter.println("   ID: " + locator.getSystemId() + " Line #" + locator.getLineNumber() + " Column #" + locator.getColumnNumber());
                        }
                        e = spe2.getException();
                        if (!(e instanceof WrappedRuntimeException)) {
                            continue;
                        }
                        e = ((WrappedRuntimeException)e).getException();
                    }
                    else {
                        e = null;
                    }
                }
            }
            else {
                diagnosticsWriter.println(" (" + throwable.getClass().getName() + "): " + throwable.getMessage());
            }
            diagnosticsString = throwable.getMessage();
        }
        if (-1 == newDoc) {
            if (null != diagnosticsString) {
                this.warn(xctxt, "WG_CANNOT_LOAD_REQUESTED_DOC", new Object[] { diagnosticsString });
            }
            else {
                this.warn(xctxt, "WG_CANNOT_LOAD_REQUESTED_DOC", new Object[] { (uri == null) ? (((base == null) ? "" : base) + uri) : uri.toString() });
            }
        }
        return newDoc;
    }
    
    public void error(final XPathContext xctxt, final String msg, final Object[] args) throws TransformerException {
        final String formattedMsg = XSLMessages.createMessage(msg, args);
        final ErrorListener errHandler = xctxt.getErrorListener();
        final TransformerException spe = new TransformerException(formattedMsg, xctxt.getSAXLocator());
        if (null != errHandler) {
            errHandler.error(spe);
        }
        else {
            System.out.println(formattedMsg);
        }
    }
    
    public void warn(final XPathContext xctxt, final String msg, final Object[] args) throws TransformerException {
        final String formattedMsg = XSLMessages.createWarning(msg, args);
        final ErrorListener errHandler = xctxt.getErrorListener();
        final TransformerException spe = new TransformerException(formattedMsg, xctxt.getSAXLocator());
        if (null != errHandler) {
            errHandler.warning(spe);
        }
        else {
            System.out.println(formattedMsg);
        }
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum < 1 || argNum > 2) {
            this.reportWrongNumberArgs();
        }
    }
    
    protected void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XSLMessages.createMessage("ER_ONE_OR_TWO", null));
    }
    
    public boolean isNodesetExpr() {
        return true;
    }
}
