// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import javax.xml.transform.Transformer;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xalan.templates.ElemTemplate;
import org.w3c.dom.Node;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xml.serializer.TransformStateSetter;

public interface TransformState extends TransformStateSetter
{
    ElemTemplateElement getCurrentElement();
    
    Node getCurrentNode();
    
    ElemTemplate getCurrentTemplate();
    
    ElemTemplate getMatchedTemplate();
    
    Node getMatchedNode();
    
    NodeIterator getContextNodeList();
    
    Transformer getTransformer();
}
