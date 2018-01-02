// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import java.util.Vector;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xml.utils.QName;

public class ElemUse extends ElemTemplateElement
{
    private QName[] m_attributeSetsNames;
    
    public ElemUse() {
        this.m_attributeSetsNames = null;
    }
    
    private void applyAttrSets(final TransformerImpl transformer, final StylesheetRoot stylesheet, final QName[] attributeSetsNames, final Node sourceNode, final QName mode) throws TransformerException {
        if (attributeSetsNames != null) {
            for (final QName qname : attributeSetsNames) {
                final Vector attrSets = stylesheet.getAttributeSetComposed(qname);
                if (attrSets != null) {
                    for (int nSets = attrSets.size(), k = 0; k < nSets; ++k) {
                        final ElemAttributeSet attrSet = attrSets.elementAt(k);
                        attrSet.execute(transformer, sourceNode, mode);
                    }
                }
            }
        }
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
        }
        if (this.m_attributeSetsNames != null) {
            this.applyAttrSets(transformer, this.getStylesheetRoot(), this.m_attributeSetsNames, sourceNode, mode);
        }
    }
    
    public QName[] getUseAttributeSets() {
        return this.m_attributeSetsNames;
    }
    
    public void setUseAttributeSets(final Vector v) {
        final int n = v.size();
        this.m_attributeSetsNames = new QName[n];
        for (int i = 0; i < n; ++i) {
            this.m_attributeSetsNames[i] = v.elementAt(i);
        }
    }
    
    public void setUseAttributeSets(final QName[] v) {
        this.m_attributeSetsNames = v;
    }
}
