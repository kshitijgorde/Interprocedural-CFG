// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.w3c.dom.Element;
import org.w3c.dom.Text;
import javax.xml.transform.TransformerException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.xalan.templates.ElemTemplateElement;
import java.util.Stack;
import org.w3c.dom.Node;

public class StackGuard
{
    public static int m_recursionLimit;
    Node m_xslRule;
    Node m_sourceXML;
    Stack stack;
    
    static {
        StackGuard.m_recursionLimit = -1;
    }
    
    public StackGuard() {
        this.stack = new Stack();
    }
    
    public StackGuard(final ElemTemplateElement xslTemplate, final Node sourceXML) {
        this.stack = new Stack();
        this.m_xslRule = xslTemplate;
        this.m_sourceXML = sourceXML;
    }
    
    public void checkForInfinateLoop(final StackGuard guard) throws TransformerException {
        final int nRules = this.stack.size();
        int loopCount = 0;
        for (int i = nRules - 1; i >= 0; --i) {
            if (this.stack.elementAt(i).equals(guard)) {
                ++loopCount;
            }
            if (loopCount >= StackGuard.m_recursionLimit) {
                final StringWriter sw = new StringWriter();
                final PrintWriter pw = new PrintWriter(sw);
                pw.println("Infinite loop diagnosed!  Stack trace:");
                int k;
                for (k = 0; k < nRules; ++k) {
                    pw.println("Source Elem #" + k + " ");
                    final StackGuard guardOnStack = (StackGuard)this.stack.elementAt(i);
                    guardOnStack.print(pw);
                }
                pw.println("Source Elem #" + k + " ");
                guard.print(pw);
                pw.println("End of infinite loop diagnosis.");
                throw new TransformerException(sw.getBuffer().toString());
            }
        }
    }
    
    public boolean equals(final Object obj) {
        return ((StackGuard)obj).m_xslRule.equals(this.m_xslRule) && ((StackGuard)obj).m_sourceXML.equals(this.m_sourceXML);
    }
    
    public int getRecursionLimit() {
        return StackGuard.m_recursionLimit;
    }
    
    public void pop() {
        this.stack.pop();
    }
    
    public void print(final PrintWriter pw) {
        if (this.m_sourceXML instanceof Text) {
            final Text tx = (Text)this.m_sourceXML;
            pw.println(tx.getData());
        }
        else if (this.m_sourceXML instanceof Element) {
            final Element elem = (Element)this.m_sourceXML;
            pw.println(elem.getNodeName());
        }
    }
    
    public void push(final ElemTemplateElement xslTemplate, final Node sourceXML) throws TransformerException {
        final StackGuard guard = new StackGuard(xslTemplate, sourceXML);
        this.checkForInfinateLoop(guard);
        this.stack.push(guard);
    }
    
    public void setRecursionLimit(final int limit) {
        StackGuard.m_recursionLimit = limit;
    }
}
