// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xalan.extensions.ExtensionNamespaceSupport;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.xpath.VariableStack;
import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemExsltFunction extends ElemTemplate
{
    static final long serialVersionUID = 272154954793534771L;
    
    public int getXSLToken() {
        return 88;
    }
    
    public String getNodeName() {
        return "function";
    }
    
    public void execute(final TransformerImpl transformer, final XObject[] args) throws TransformerException {
        final XPathContext xctxt = transformer.getXPathContext();
        final VariableStack vars = xctxt.getVarStack();
        final int thisFrame = vars.getStackFrame();
        final int nextFrame = vars.link(super.m_frameSize);
        if (super.m_inArgsSize < args.length) {
            throw new TransformerException("function called with too many args");
        }
        if (super.m_inArgsSize > 0) {
            vars.clearLocalSlots(0, super.m_inArgsSize);
            if (args.length > 0) {
                vars.setStackFrame(thisFrame);
                final NodeList children = this.getChildNodes();
                for (int i = 0; i < args.length; ++i) {
                    final Node child = children.item(i);
                    if (children.item(i) instanceof ElemParam) {
                        final ElemParam param = (ElemParam)children.item(i);
                        vars.setLocalVariable(param.getIndex(), args[i], nextFrame);
                    }
                }
                vars.setStackFrame(nextFrame);
            }
        }
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        vars.setStackFrame(nextFrame);
        transformer.executeChildTemplates(this, true);
        vars.unlink(thisFrame);
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        String namespace = this.getName().getNamespace();
        final String handlerClass = sroot.getExtensionHandlerClass();
        Object[] args = { namespace, sroot };
        ExtensionNamespaceSupport extNsSpt = new ExtensionNamespaceSupport(namespace, handlerClass, args);
        sroot.getExtensionNamespacesManager().registerExtension(extNsSpt);
        if (!namespace.equals("http://exslt.org/functions")) {
            namespace = "http://exslt.org/functions";
            args = new Object[] { namespace, sroot };
            extNsSpt = new ExtensionNamespaceSupport(namespace, handlerClass, args);
            sroot.getExtensionNamespacesManager().registerExtension(extNsSpt);
        }
    }
}
