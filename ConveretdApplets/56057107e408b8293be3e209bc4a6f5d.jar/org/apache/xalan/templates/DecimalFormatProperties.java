// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xml.utils.QName;
import java.text.DecimalFormatSymbols;

public class DecimalFormatProperties extends ElemTemplateElement
{
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
    
    public char getDecimalSeparator() {
        return this.m_dfs.getDecimalSeparator();
    }
    
    public char getDigit() {
        return this.m_dfs.getDigit();
    }
    
    public char getGroupingSeparator() {
        return this.m_dfs.getGroupingSeparator();
    }
    
    public String getInfinity() {
        return this.m_dfs.getInfinity();
    }
    
    public char getMinusSign() {
        return this.m_dfs.getMinusSign();
    }
    
    public String getNaN() {
        return this.m_dfs.getNaN();
    }
    
    public QName getName() {
        if (this.m_qname == null) {
            return new QName("");
        }
        return this.m_qname;
    }
    
    public char getPatternSeparator() {
        return this.m_dfs.getPatternSeparator();
    }
    
    public char getPerMille() {
        return this.m_dfs.getPerMill();
    }
    
    public char getPercent() {
        return this.m_dfs.getPercent();
    }
    
    public char getZeroDigit() {
        return this.m_dfs.getZeroDigit();
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeDecimalFormats(this);
    }
    
    public void setDecimalSeparator(final char ds) {
        this.m_dfs.setDecimalSeparator(ds);
    }
    
    public void setDigit(final char v) {
        this.m_dfs.setDigit(v);
    }
    
    public void setGroupingSeparator(final char gs) {
        this.m_dfs.setGroupingSeparator(gs);
    }
    
    public void setInfinity(final String inf) {
        this.m_dfs.setInfinity(inf);
    }
    
    public void setMinusSign(final char v) {
        this.m_dfs.setMinusSign(v);
    }
    
    public void setNaN(final String v) {
        this.m_dfs.setNaN(v);
    }
    
    public void setName(final QName qname) {
        this.m_qname = qname;
    }
    
    public void setPatternSeparator(final char v) {
        this.m_dfs.setPatternSeparator(v);
    }
    
    public void setPerMille(final char v) {
        this.m_dfs.setPerMill(v);
    }
    
    public void setPercent(final char v) {
        this.m_dfs.setPercent(v);
    }
    
    public void setZeroDigit(final char v) {
        this.m_dfs.setZeroDigit(v);
    }
}
