// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.SourceLocator;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.TransformerException;
import org.apache.xalan.transformer.TransformerImpl;
import java.util.Vector;
import org.apache.xml.utils.QName;

public class ElemUse extends ElemTemplateElement
{
    static final long serialVersionUID = 5830057200289299736L;
    private QName[] m_attributeSetsNames;
    
    public ElemUse() {
        this.m_attributeSetsNames = null;
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
    
    public QName[] getUseAttributeSets() {
        return this.m_attributeSetsNames;
    }
    
    public void applyAttrSets(final TransformerImpl transformer, final StylesheetRoot stylesheet) throws TransformerException {
        this.applyAttrSets(transformer, stylesheet, this.m_attributeSetsNames);
    }
    
    private void applyAttrSets(final TransformerImpl transformer, final StylesheetRoot stylesheet, final QName[] attributeSetsNames) throws TransformerException {
        if (null != attributeSetsNames) {
            for (final QName qname : attributeSetsNames) {
                final Vector attrSets = stylesheet.getAttributeSetComposed(qname);
                if (null == attrSets) {
                    throw new TransformerException(XSLMessages.createMessage("ER_NO_ATTRIB_SET", new Object[] { qname }), this);
                }
                final int nSets = attrSets.size();
                for (int k = nSets - 1; k >= 0; --k) {
                    final ElemAttributeSet attrSet = attrSets.elementAt(k);
                    attrSet.execute(transformer);
                }
            }
        }
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (null != this.m_attributeSetsNames) {
            this.applyAttrSets(transformer, this.getStylesheetRoot(), this.m_attributeSetsNames);
        }
    }
}
