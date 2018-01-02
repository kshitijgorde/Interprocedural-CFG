// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.SourceLocator;
import javax.xml.transform.Source;
import org.apache.xpath.SourceTreeManager;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.xml.utils.WrappedRuntimeException;
import java.io.IOException;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.NodeSet;
import org.apache.xpath.Expression;
import org.w3c.dom.Node;
import org.apache.xpath.DOMHelper;
import org.apache.xpath.objects.XNodeSet;
import org.w3c.dom.Document;
import org.apache.xpath.objects.XObject;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;
import org.apache.xalan.res.XSLMessages;
import org.apache.xpath.XPathContext;
import org.apache.xpath.functions.WrongNumberArgsException;
import org.apache.xpath.functions.Function2Args;

public class FuncDocument extends Function2Args
{
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum < 1 || argNum > 2) {
            throw new WrongNumberArgsException("2");
        }
    }
    
    public void error(final XPathContext xctxt, final int msg, final Object[] args) throws TransformerException {
        final String formattedMsg = XSLMessages.createMessage(msg, args);
        final ErrorListener errHandler = xctxt.getErrorListener();
        final TransformerException spe = new TransformerException(formattedMsg, xctxt.getSAXLocator());
        if (errHandler != null) {
            errHandler.error(spe);
        }
        else {
            System.out.println(formattedMsg);
        }
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final Node context = xctxt.getCurrentNode();
        final Document docContext = (Document)((context.getNodeType() == 9) ? context : context.getOwnerDocument());
        final XObject arg = this.getArg0().execute(xctxt);
        String base = "";
        final Expression arg1Expr = this.getArg1();
        if (arg1Expr != null) {
            final XObject arg2 = arg1Expr.execute(xctxt);
            if (arg2.getType() == 4) {
                final Node baseNode = arg2.nodeset().nextNode();
                if (baseNode == null) {
                    this.warn(xctxt, 26, null);
                }
                final Document baseDoc = (Document)((baseNode == null) ? null : ((baseNode.getNodeType() == 9) ? baseNode : baseNode.getOwnerDocument()));
                if (baseDoc == null || baseDoc instanceof Stylesheet) {
                    base = xctxt.getNamespaceContext().getBaseIdentifier();
                }
                else {
                    base = xctxt.getSourceTreeManager().findURIFromDoc(baseDoc);
                }
            }
            else {
                base = arg2.str();
            }
        }
        else {
            base = xctxt.getNamespaceContext().getBaseIdentifier();
        }
        final XNodeSet nodes = new XNodeSet();
        final NodeSet mnl = nodes.mutableNodeset();
        final NodeIterator iterator = (arg.getType() == 4) ? arg.nodeset() : null;
        Node pos = null;
        while (iterator == null || (pos = iterator.nextNode()) != null) {
            final String ref = (iterator != null) ? DOMHelper.getNodeData(pos) : arg.str();
            if (ref != null) {
                if (docContext == null) {
                    this.error(xctxt, 24, null);
                }
                final int indexOfColon = ref.indexOf(58);
                final int indexOfSlash = ref.indexOf(47);
                if (indexOfColon != -1 && indexOfSlash != -1 && indexOfColon < indexOfSlash) {
                    base = null;
                }
                final Node newDoc = this.getDoc(xctxt, context, ref, base);
                if (newDoc != null && !mnl.contains(newDoc)) {
                    mnl.addElement(newDoc);
                }
                if (iterator == null) {
                    break;
                }
                if (newDoc == null) {
                    break;
                }
                continue;
            }
        }
        return nodes;
    }
    
    Node getDoc(final XPathContext xctxt, final Node context, String uri, final String base) throws TransformerException {
        final SourceTreeManager treeMgr = xctxt.getSourceTreeManager();
        Node newDoc;
        try {
            final Source source = treeMgr.resolveURI(base, uri, xctxt.getSAXLocator());
            newDoc = treeMgr.getNode(source);
        }
        catch (IOException ioe) {
            throw new TransformerException(ioe.getMessage(), xctxt.getSAXLocator(), ioe);
        }
        catch (TransformerException e2) {
            throw new TransformerException(e2);
        }
        if (newDoc != null) {
            return newDoc;
        }
        if (uri.length() == 0) {
            uri = xctxt.getNamespaceContext().getBaseIdentifier();
        }
        String diagnosticsString = null;
        try {
            if (uri != null && uri.toString().length() > 0) {
                newDoc = treeMgr.getSourceTree(base, uri, xctxt.getSAXLocator());
            }
            else {
                this.warn(xctxt, 6, new Object[] { String.valueOf((base == null) ? "" : base) + uri });
            }
        }
        catch (Throwable throwable) {
            newDoc = null;
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
                while (e != null) {
                    if (e.getMessage() != null) {
                        diagnosticsWriter.println(" (" + e.getClass().getName() + "): " + e.getMessage());
                    }
                    if (e instanceof TransformerException) {
                        final TransformerException spe2 = (TransformerException)e;
                        final SourceLocator locator = spe2.getLocator();
                        if (locator != null && locator.getSystemId() != null) {
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
        if (newDoc == null) {
            if (diagnosticsString != null) {
                this.warn(xctxt, 7, new Object[] { diagnosticsString });
            }
            else {
                this.warn(xctxt, 7, new Object[] { (uri == null) ? (String.valueOf((base == null) ? "" : base) + uri) : uri.toString() });
            }
        }
        return newDoc;
    }
    
    public void warn(final XPathContext xctxt, final int msg, final Object[] args) throws TransformerException {
        final String formattedMsg = XSLMessages.createWarning(msg, args);
        final ErrorListener errHandler = xctxt.getErrorListener();
        final TransformerException spe = new TransformerException(formattedMsg, xctxt.getSAXLocator());
        if (errHandler != null) {
            errHandler.warning(spe);
        }
        else {
            System.out.println(formattedMsg);
        }
    }
}
