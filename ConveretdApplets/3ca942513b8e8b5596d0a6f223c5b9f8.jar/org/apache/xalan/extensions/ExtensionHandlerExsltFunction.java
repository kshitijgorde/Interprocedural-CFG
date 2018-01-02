// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import org.apache.xpath.XPathContext;
import org.apache.xpath.ExpressionNode;
import org.apache.xalan.res.XSLMessages;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.functions.FuncExtFunction;
import java.util.Vector;
import org.apache.xalan.templates.ElemExsltFuncResult;
import org.apache.xalan.templates.ElemTemplate;
import org.apache.xalan.templates.ElemExsltFunction;
import java.io.IOException;
import javax.xml.transform.TransformerException;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xml.utils.QName;
import org.apache.xalan.templates.StylesheetRoot;

public class ExtensionHandlerExsltFunction extends ExtensionHandler
{
    private String m_namespace;
    private StylesheetRoot m_stylesheet;
    private static final QName RESULTQNAME;
    
    public ExtensionHandlerExsltFunction(final String ns, final StylesheetRoot stylesheet) {
        super(ns, "xml");
        this.m_namespace = ns;
        this.m_stylesheet = stylesheet;
    }
    
    public void processElement(final String localPart, final ElemTemplateElement element, final TransformerImpl transformer, final Stylesheet stylesheetTree, final Object methodKey) throws TransformerException, IOException {
    }
    
    public ElemExsltFunction getFunction(final String funcName) {
        final QName qname = new QName(this.m_namespace, funcName);
        final ElemTemplate templ = this.m_stylesheet.getTemplateComposed(qname);
        if (templ != null && templ instanceof ElemExsltFunction) {
            return (ElemExsltFunction)templ;
        }
        return null;
    }
    
    public boolean isFunctionAvailable(final String funcName) {
        return this.getFunction(funcName) != null;
    }
    
    public boolean isElementAvailable(final String elemName) {
        if (!new QName(this.m_namespace, elemName).equals(ExtensionHandlerExsltFunction.RESULTQNAME)) {
            return false;
        }
        ElemTemplateElement nextElem;
        for (ElemTemplateElement elem = this.m_stylesheet.getFirstChildElem(); elem != null && elem != this.m_stylesheet; elem = nextElem) {
            if (elem instanceof ElemExsltFuncResult && this.ancestorIsFunction(elem)) {
                return true;
            }
            nextElem = elem.getFirstChildElem();
            if (nextElem == null) {
                nextElem = elem.getNextSiblingElem();
            }
            if (nextElem == null) {
                nextElem = elem.getParentElem();
            }
        }
        return false;
    }
    
    private boolean ancestorIsFunction(ElemTemplateElement child) {
        while (child.getParentElem() != null && !(child.getParentElem() instanceof StylesheetRoot)) {
            if (child.getParentElem() instanceof ElemExsltFunction) {
                return true;
            }
            child = child.getParentElem();
        }
        return false;
    }
    
    public Object callFunction(final String funcName, final Vector args, final Object methodKey, final ExpressionContext exprContext) throws TransformerException {
        throw new TransformerException("This method should not be called.");
    }
    
    public Object callFunction(final FuncExtFunction extFunction, final Vector args, final ExpressionContext exprContext) throws TransformerException {
        ExpressionNode parent;
        for (parent = extFunction.exprGetParent(); parent != null && !(parent instanceof ElemTemplate); parent = parent.exprGetParent()) {}
        final ElemTemplate callerTemplate = (parent != null) ? ((ElemTemplate)parent) : null;
        final XObject[] methodArgs = new XObject[args.size()];
        try {
            for (int i = 0; i < methodArgs.length; ++i) {
                methodArgs[i] = XObject.create(args.elementAt(i));
            }
            final ElemExsltFunction elemFunc = this.getFunction(extFunction.getFunctionName());
            if (null != elemFunc) {
                final XPathContext context = exprContext.getXPathContext();
                final TransformerImpl transformer = (TransformerImpl)context.getOwnerObject();
                transformer.pushCurrentFuncResult(null);
                elemFunc.execute(transformer, methodArgs);
                final XObject val = (XObject)transformer.popCurrentFuncResult();
                return (val == null) ? new XString("") : val;
            }
            throw new TransformerException(XSLMessages.createMessage("ER_FUNCTION_NOT_FOUND", new Object[] { extFunction.getFunctionName() }));
        }
        catch (TransformerException e) {
            throw e;
        }
        catch (Exception e2) {
            throw new TransformerException(e2);
        }
    }
    
    static {
        RESULTQNAME = new QName("http://exslt.org/functions", "result");
    }
}
