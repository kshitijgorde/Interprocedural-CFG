// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import javax.xml.transform.Transformer;
import org.apache.xalan.templates.ElemTemplate;
import org.w3c.dom.Node;
import org.apache.xalan.templates.ElemTemplateElement;
import org.w3c.dom.traversal.NodeIterator;

public interface TransformState
{
    NodeIterator getContextNodeList();
    
    ElemTemplateElement getCurrentElement();
    
    Node getCurrentNode();
    
    ElemTemplate getCurrentTemplate();
    
    Node getMatchedNode();
    
    ElemTemplate getMatchedTemplate();
    
    Transformer getTransformer();
}
