// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

public class Text extends Content
{
    private static final String CVS_ID = "@(#) $RCSfile: Text.java,v $ $Revision: 1.25 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
    static final String EMPTY_STRING = "";
    protected String value;
    
    protected Text() {
    }
    
    public Text(final String str) {
        this.setText(str);
    }
    
    public String getText() {
        return this.value;
    }
    
    public String getTextTrim() {
        return this.getText().trim();
    }
    
    public String getTextNormalize() {
        return normalizeString(this.getText());
    }
    
    public static String normalizeString(final String str) {
        if (str == null) {
            return "";
        }
        final char[] c = str.toCharArray();
        final char[] n = new char[c.length];
        boolean white = true;
        int pos = 0;
        for (int i = 0; i < c.length; ++i) {
            if (" \t\n\r".indexOf(c[i]) != -1) {
                if (!white) {
                    n[pos++] = ' ';
                    white = true;
                }
            }
            else {
                n[pos++] = c[i];
                white = false;
            }
        }
        if (white && pos > 0) {
            --pos;
        }
        return new String(n, 0, pos);
    }
    
    public Text setText(final String str) {
        if (str == null) {
            this.value = "";
            return this;
        }
        final String reason;
        if ((reason = Verifier.checkCharacterData(str)) != null) {
            throw new IllegalDataException(str, "character content", reason);
        }
        this.value = str;
        return this;
    }
    
    public void append(final String str) {
        if (str == null) {
            return;
        }
        final String reason;
        if ((reason = Verifier.checkCharacterData(str)) != null) {
            throw new IllegalDataException(str, "character content", reason);
        }
        if (str == "") {
            this.value = str;
        }
        else {
            this.value += str;
        }
    }
    
    public void append(final Text text) {
        if (text == null) {
            return;
        }
        this.value += text.getText();
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String toString() {
        return new StringBuffer(64).append("[Text: ").append(this.getText()).append("]").toString();
    }
    
    public Object clone() {
        final Text text = (Text)super.clone();
        text.value = this.value;
        return text;
    }
}
