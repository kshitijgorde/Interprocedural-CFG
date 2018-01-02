// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

public class CDATA extends Text
{
    private static final String CVS_ID = "@(#) $RCSfile: CDATA.java,v $ $Revision: 1.32 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
    
    protected CDATA() {
    }
    
    public CDATA(final String string) {
        this.setText(string);
    }
    
    public Text setText(final String str) {
        if (str == null || "".equals(str)) {
            this.value = "";
            return this;
        }
        final String reason = Verifier.checkCDATASection(str);
        if (reason != null) {
            throw new IllegalDataException(str, "CDATA section", reason);
        }
        this.value = str;
        return this;
    }
    
    public void append(final String str) {
        if (str == null || "".equals(str)) {
            return;
        }
        String tmpValue;
        if (this.value == "") {
            tmpValue = str;
        }
        else {
            tmpValue = this.value + str;
        }
        final String reason = Verifier.checkCDATASection(tmpValue);
        if (reason != null) {
            throw new IllegalDataException(str, "CDATA section", reason);
        }
        this.value = tmpValue;
    }
    
    public void append(final Text text) {
        if (text == null) {
            return;
        }
        this.append(text.getText());
    }
    
    public String toString() {
        return new StringBuffer(64).append("[CDATA: ").append(this.getText()).append("]").toString();
    }
}
