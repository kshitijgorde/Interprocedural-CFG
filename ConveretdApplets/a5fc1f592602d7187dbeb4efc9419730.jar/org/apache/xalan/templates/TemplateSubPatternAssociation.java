// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.QName;
import org.apache.xpath.patterns.StepPattern;
import java.io.Serializable;

class TemplateSubPatternAssociation implements Serializable, Cloneable
{
    StepPattern m_stepPattern;
    private String m_pattern;
    private ElemTemplate m_template;
    private TemplateSubPatternAssociation m_next;
    private boolean m_wild;
    private String m_targetString;
    
    TemplateSubPatternAssociation(final ElemTemplate template, final StepPattern pattern, final String pat) {
        this.m_next = null;
        this.m_pattern = pat;
        this.m_template = template;
        this.m_stepPattern = pattern;
        this.m_targetString = this.m_stepPattern.getTargetString();
        this.m_wild = this.m_targetString.equals("*");
    }
    
    public Object clone() throws CloneNotSupportedException {
        final TemplateSubPatternAssociation tspa = (TemplateSubPatternAssociation)super.clone();
        tspa.m_next = null;
        return tspa;
    }
    
    public final String getTargetString() {
        return this.m_targetString;
    }
    
    public void setTargetString(final String key) {
        this.m_targetString = key;
    }
    
    boolean matchMode(final QName m1) {
        return this.matchModes(m1, this.m_template.getMode());
    }
    
    private boolean matchModes(final QName m1, final QName m2) {
        return (null == m1 && null == m2) || (null != m1 && null != m2 && m1.equals(m2));
    }
    
    public boolean matches(final XPathContext xctxt, final int targetNode, final QName mode) throws TransformerException {
        final double score = this.m_stepPattern.getMatchScore(xctxt, targetNode);
        return Double.NEGATIVE_INFINITY != score && this.matchModes(mode, this.m_template.getMode());
    }
    
    public final boolean isWild() {
        return this.m_wild;
    }
    
    public final StepPattern getStepPattern() {
        return this.m_stepPattern;
    }
    
    public final String getPattern() {
        return this.m_pattern;
    }
    
    public int getDocOrderPos() {
        return this.m_template.getUid();
    }
    
    public final int getImportLevel() {
        return this.m_template.getStylesheetComposed().getImportCountComposed();
    }
    
    public final ElemTemplate getTemplate() {
        return this.m_template;
    }
    
    public final TemplateSubPatternAssociation getNext() {
        return this.m_next;
    }
    
    public void setNext(final TemplateSubPatternAssociation mp) {
        this.m_next = mp;
    }
}
