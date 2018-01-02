// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.trace;

import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPath;
import org.apache.xalan.templates.ElemTemplateElement;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;

public class EndSelectionEvent extends SelectionEvent
{
    public EndSelectionEvent(final TransformerImpl processor, final Node sourceNode, final ElemTemplateElement styleNode, final String attributeName, final XPath xpath, final XObject selection) {
        super(processor, sourceNode, styleNode, attributeName, xpath, selection);
    }
}
