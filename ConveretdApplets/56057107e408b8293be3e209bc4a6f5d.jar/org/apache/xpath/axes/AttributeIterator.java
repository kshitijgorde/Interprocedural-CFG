// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.DOMException;
import org.apache.xpath.VariableStack;
import org.w3c.dom.Node;
import org.apache.xpath.XPathContext;
import org.w3c.dom.traversal.NodeIterator;
import javax.xml.transform.TransformerException;
import org.apache.xpath.compiler.OpMap;
import org.apache.xpath.compiler.Compiler;
import org.w3c.dom.NamedNodeMap;

public class AttributeIterator extends LocPathIterator
{
    private transient NamedNodeMap m_attributeList;
    private transient int m_attrListPos;
    private transient int m_nAttrs;
    
    public AttributeIterator(final Compiler compiler, final int opPos, final int analysis) throws TransformerException {
        super(compiler, opPos, analysis, false);
        this.m_attrListPos = 0;
        this.m_nAttrs = 0;
        final int firstStepPos = OpMap.getFirstChildPos(opPos);
        final int whatToShow = compiler.getWhatToShow(firstStepPos);
        if ((whatToShow & 0x43) == 0x0 || whatToShow == -1) {
            this.initNodeTest(whatToShow);
        }
        else {
            this.initNodeTest(whatToShow, compiler.getStepNS(firstStepPos), compiler.getStepLocalName(firstStepPos));
        }
        this.initPredicateInfo(compiler, firstStepPos);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final LocPathIterator clone = (LocPathIterator)super.clone();
        return clone;
    }
    
    public NodeIterator cloneWithReset() throws CloneNotSupportedException {
        final AttributeIterator clone = (AttributeIterator)super.cloneWithReset();
        clone.m_attrListPos = 0;
        clone.resetProximityPositions();
        return clone;
    }
    
    public int getLastPos(final XPathContext xctxt) {
        return this.m_nAttrs;
    }
    
    private void initAttrList() {
        if (super.m_context.getNodeType() == 1) {
            this.m_attrListPos = 0;
            this.m_attributeList = super.m_context.getAttributes();
            if (this.m_attributeList != null) {
                this.m_nAttrs = this.m_attributeList.getLength();
            }
            else {
                this.m_nAttrs = 0;
            }
            if (this.m_nAttrs == 0) {
                super.m_foundLast = true;
            }
        }
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
        if (this.m_attributeList == null) {
            this.initAttrList();
            this.resetProximityPositions();
        }
        Label_0113: {
            if (super.m_varStackPos != -1) {
                final VariableStack vars = super.m_execContext.getVarStack();
                final int savedStart = vars.getSearchStart();
                vars.setSearchStart(super.m_varStackPos);
                vars.pushContextPosition(super.m_varStackContext);
                break Label_0113;
            }
            final VariableStack vars = null;
            final int savedStart = 0;
            try {
                Node next2;
                do {
                    next2 = (super.m_lastFetched = ((this.m_attributeList == null || this.m_attrListPos >= this.m_nAttrs) ? null : this.m_attributeList.item(this.m_attrListPos++)));
                    if (next2 == null) {
                        break;
                    }
                    if (this.acceptNode(next2) == 1) {
                        break;
                    }
                } while (next2 != null);
                if (next2 != null) {
                    if (super.m_cachedNodes != null) {
                        super.m_cachedNodes.addElement(super.m_lastFetched);
                    }
                    ++super.m_next;
                    return next2;
                }
                super.m_foundLast = true;
                return null;
            }
            finally {
                if (super.m_varStackPos != -1) {
                    vars.setSearchStart(savedStart);
                    vars.popContextPosition();
                }
            }
        }
    }
}
