// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.DOMException;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.WrappedRuntimeException;
import org.w3c.dom.Element;
import org.apache.xpath.WhitespaceStrippingElementMatcher;
import org.apache.xml.utils.XMLCharacterRecognizer;
import org.w3c.dom.Node;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.Compiler;

public class ChildIterator extends LocPathIterator
{
    public ChildIterator(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        super(compiler, opPos, analysis, false);
    }
    
    public Node nextNode() throws DOMException {
        if (super.m_cachedNodes != null && super.m_cachedNodes.getCurrentPos() < super.m_cachedNodes.size()) {
            final Node next = super.m_cachedNodes.nextNode();
            this.setCurrentPos(super.m_cachedNodes.getCurrentPos());
            return next;
        }
        if (super.m_foundLast) {
            return null;
        }
        Node next;
        while (true) {
            next = (super.m_lastFetched = ((super.m_lastFetched == null) ? super.m_context.getFirstChild() : super.m_lastFetched.getNextSibling()));
            if (next == null) {
                break;
            }
            final int nt = next.getNodeType();
            if (nt == 10) {
                continue;
            }
            if (nt != 3 || next.isSupported("http://xml.apache.org/xpath/features/whitespace-pre-stripping", null)) {
                break;
            }
            final Node parent = next.getParentNode();
            if (parent == null || parent.getNodeType() != 1) {
                break;
            }
            final String data = next.getNodeValue();
            if (!XMLCharacterRecognizer.isWhiteSpace(data)) {
                break;
            }
            final PrefixResolver resolver = this.getXPathContext().getNamespaceContext();
            if (resolver instanceof WhitespaceStrippingElementMatcher) {
                final WhitespaceStrippingElementMatcher wsem = (WhitespaceStrippingElementMatcher)resolver;
                try {
                    if (wsem.shouldStripWhiteSpace(this.getXPathContext(), (Element)parent)) {
                        continue;
                    }
                }
                catch (TransformerException te) {
                    throw new WrappedRuntimeException(te);
                }
                break;
            }
            break;
        }
        if (next != null) {
            if (super.m_cachedNodes != null) {
                super.m_cachedNodes.addElement(super.m_lastFetched);
            }
            ++super.m_next;
            return next;
        }
        super.m_foundLast = true;
        return null;
    }
}
