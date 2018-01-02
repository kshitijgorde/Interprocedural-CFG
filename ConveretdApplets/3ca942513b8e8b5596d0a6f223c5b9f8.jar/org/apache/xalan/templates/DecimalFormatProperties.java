// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xml.utils.QName;
import java.text.DecimalFormatSymbols;

public class DecimalFormatProperties extends ElemTemplateElement
{
    static final long serialVersionUID = -6559409339256269446L;
    DecimalFormatSymbols m_dfs;
    private QName m_qname;
    
    public DecimalFormatProperties(final int docOrderNumber) {
        this.m_qname = null;
        (this.m_dfs = new DecimalFormatSymbols()).setInfinity("Infinity");
        this.m_dfs.setNaN("NaN");
        super.m_docOrderNumber = docOrderNumber;
    }
    
    public DecimalFormatSymbols getDecimalFormatSymbols() {
        return this.m_dfs;
    }
    
    public void setName(final QName qname) {
        this.m_qname = qname;
    }
    
    public QName getName() {
        if (this.m_qname == null) {
            return new QName("");
        }
        return this.m_qname;
    }
    
    public void setDecimalSeparator(final char ds) {
        this.m_dfs.setDecimalSeparator(ds);
    }
    
    public char getDecimalSeparator() {
        return this.m_dfs.getDecimalSeparator();
    }
    
    public void setGroupingSeparator(final char gs) {
        this.m_dfs.setGroupingSeparator(gs);
    }
    
    public char getGroupingSeparator() {
        return this.m_dfs.getGroupingSeparator();
    }
    
    public void setInfinity(final String inf) {
        this.m_dfs.setInfinity(inf);
    }
    
    public String getInfinity() {
        return this.m_dfs.getInfinity();
    }
    
    public void setMinusSign(final char v) {
        this.m_dfs.setMinusSign(v);
    }
    
    public char getMinusSign() {
        return this.m_dfs.getMinusSign();
    }
    
    public void setNaN(final String v) {
        this.m_dfs.setNaN(v);
    }
    
    public String getNaN() {
        return this.m_dfs.getNaN();
    }
    
    public String getNodeName() {
        return "decimal-format";
    }
    
    public void setPercent(final char v) {
        this.m_dfs.setPercent(v);
    }
    
    public char getPercent() {
        return this.m_dfs.getPercent();
    }
    
    public void setPerMille(final char v) {
        this.m_dfs.setPerMill(v);
    }
    
    public char getPerMille() {
        return this.m_dfs.getPerMill();
    }
    
    public int getXSLToken() {
        return 83;
    }
    
    public void setZeroDigit(final char v) {
        this.m_dfs.setZeroDigit(v);
    }
    
    public char getZeroDigit() {
        return this.m_dfs.getZeroDigit();
    }
    
    public void setDigit(final char v) {
        this.m_dfs.setDigit(v);
    }
    
    public char getDigit() {
        return this.m_dfs.getDigit();
    }
    
    public void setPatternSeparator(final char v) {
        this.m_dfs.setPatternSeparator(v);
    }
    
    public char getPatternSeparator() {
        return this.m_dfs.getPatternSeparator();
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeDecimalFormats(this);
    }
}
