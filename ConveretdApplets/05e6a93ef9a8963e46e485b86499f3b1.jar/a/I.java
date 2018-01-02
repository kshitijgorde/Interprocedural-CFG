// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;

public class I
{
    public String q;
    public String w;
    public Color q;
    
    public I() {
    }
    
    public static String q(String substring, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = substring.indexOf(s); i >= 0; i = (substring = substring.substring(i + 1)).indexOf(s)) {
            final String substring2 = substring.substring(0, i);
            sb.append(substring2).append((substring2.indexOf(" ") >= 0) ? "" : " ");
        }
        return sb.toString();
    }
    
    public I(final af af, final byte b) {
        this.q = "";
        this.w = "";
        this.q = Color.black;
    }
}
