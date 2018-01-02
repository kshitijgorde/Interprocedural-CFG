// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xml.dtm.ref.DTMNodeIterator;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xml.dtm.DTM;
import javax.xml.transform.Transformer;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xalan.templates.ElemTemplate;
import org.apache.xalan.templates.ElemTemplateElement;
import org.w3c.dom.Node;

public class XalanTransformState implements TransformState
{
    Node m_node;
    ElemTemplateElement m_currentElement;
    ElemTemplate m_currentTemplate;
    ElemTemplate m_matchedTemplate;
    int m_currentNodeHandle;
    Node m_currentNode;
    int m_matchedNode;
    DTMIterator m_contextNodeList;
    boolean m_elemPending;
    TransformerImpl m_transformer;
    
    public XalanTransformState() {
        this.m_node = null;
        this.m_currentElement = null;
        this.m_currentTemplate = null;
        this.m_matchedTemplate = null;
        this.m_currentNodeHandle = -1;
        this.m_currentNode = null;
        this.m_matchedNode = -1;
        this.m_contextNodeList = null;
        this.m_elemPending = false;
        this.m_transformer = null;
    }
    
    public void setCurrentNode(final Node n) {
        this.m_node = n;
    }
    
    public void resetState(final Transformer transformer) {
        if (transformer != null && transformer instanceof TransformerImpl) {
            this.m_transformer = (TransformerImpl)transformer;
            this.m_currentElement = this.m_transformer.getCurrentElement();
            this.m_currentTemplate = this.m_transformer.getCurrentTemplate();
            this.m_matchedTemplate = this.m_transformer.getMatchedTemplate();
            final int currentNodeHandle = this.m_transformer.getCurrentNode();
            final DTM dtm = this.m_transformer.getXPathContext().getDTM(currentNodeHandle);
            this.m_currentNode = dtm.getNode(currentNodeHandle);
            this.m_matchedNode = this.m_transformer.getMatchedNode();
            this.m_contextNodeList = this.m_transformer.getContextNodeList();
        }
    }
    
    public ElemTemplateElement getCurrentElement() {
        if (this.m_elemPending) {
            return this.m_currentElement;
        }
        return this.m_transformer.getCurrentElement();
    }
    
    public Node getCurrentNode() {
        if (this.m_currentNode != null) {
            return this.m_currentNode;
        }
        final DTM dtm = this.m_transformer.getXPathContext().getDTM(this.m_transformer.getCurrentNode());
        return dtm.getNode(this.m_transformer.getCurrentNode());
    }
    
    public ElemTemplate getCurrentTemplate() {
        if (this.m_elemPending) {
            return this.m_currentTemplate;
        }
        return this.m_transformer.getCurrentTemplate();
    }
    
    public ElemTemplate getMatchedTemplate() {
        if (this.m_elemPending) {
            return this.m_matchedTemplate;
        }
        return this.m_transformer.getMatchedTemplate();
    }
    
    public Node getMatchedNode() {
        if (this.m_elemPending) {
            final DTM dtm = this.m_transformer.getXPathContext().getDTM(this.m_matchedNode);
            return dtm.getNode(this.m_matchedNode);
        }
        final DTM dtm = this.m_transformer.getXPathContext().getDTM(this.m_transformer.getMatchedNode());
        return dtm.getNode(this.m_transformer.getMatchedNode());
    }
    
    public NodeIterator getContextNodeList() {
        if (this.m_elemPending) {
            return new DTMNodeIterator(this.m_contextNodeList);
        }
        return new DTMNodeIterator(this.m_transformer.getContextNodeList());
    }
    
    public Transformer getTransformer() {
        return this.m_transformer;
    }
}
