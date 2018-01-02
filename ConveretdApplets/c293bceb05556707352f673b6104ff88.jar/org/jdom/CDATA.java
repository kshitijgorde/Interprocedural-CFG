// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.io.Serializable;

public class CDATA implements Serializable, Cloneable
{
    private static final String CVS_ID = "@(#) $RCSfile: CDATA.java,v $ $Revision: 1.20 $ $Date: 2002/03/12 07:11:39 $ $Name: jdom_1_0_b8 $";
    private static final String EMPTY_STRING = "";
    protected String value;
    protected Object parent;
    
    protected CDATA() {
    }
    
    public CDATA(final String str) {
        this.setText(str);
    }
    
    public String getText() {
        return this.value;
    }
    
    public String getTextTrim() {
        return this.getText().trim();
    }
    
    public String getTextNormalize() {
        return Text.normalizeString(this.getText());
    }
    
    public CDATA setText(final String str) {
        if (str == null) {
            this.value = "";
            return this;
        }
        final String reason;
        if ((reason = Verifier.checkCDATASection(str)) != null) {
            throw new IllegalDataException(str, "CDATA section", reason);
        }
        this.value = str;
        return this;
    }
    
    public void append(final String str) {
        if (str == null) {
            return;
        }
        final String reason;
        if ((reason = Verifier.checkCDATASection(str)) != null) {
            throw new IllegalDataException(str, "CDATA section", reason);
        }
        if (this.value == "") {
            this.value = str;
        }
        else {
            this.value = String.valueOf(this.value) + str;
        }
    }
    
    public void append(final CDATA cdata) {
        if (cdata == null) {
            return;
        }
        this.value = String.valueOf(this.value) + cdata.getText();
    }
    
    public Element getParent() {
        return (Element)this.parent;
    }
    
    public Document getDocument() {
        if (this.parent != null) {
            return ((Element)this.parent).getDocument();
        }
        return null;
    }
    
    protected CDATA setParent(final Element parent) {
        this.parent = parent;
        return this;
    }
    
    public CDATA detach() {
        if (this.parent != null) {
            ((Element)this.parent).removeContent(this);
        }
        this.parent = null;
        return this;
    }
    
    public String toString() {
        return new StringBuffer(64).append("[CDATA: ").append(this.getText()).append("]").toString();
    }
    
    public final int hashCode() {
        return super.hashCode();
    }
    
    public Object clone() {
        CDATA cdata = null;
        try {
            cdata = (CDATA)super.clone();
        }
        catch (CloneNotSupportedException ex) {}
        cdata.parent = null;
        cdata.value = this.value;
        return cdata;
    }
    
    public final boolean equals(final Object ob) {
        return this == ob;
    }
}
