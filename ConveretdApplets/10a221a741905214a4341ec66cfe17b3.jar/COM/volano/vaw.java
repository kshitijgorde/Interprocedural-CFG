// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.TextArea;

public class vaw extends TextArea
{
    private int a;
    private int b;
    private int c;
    private FontMetrics d;
    
    public vaw(final int a) {
        super(10, 40);
        this.c = 30;
        this.a = a;
    }
    
    public void addNotify() {
        super.addNotify();
        this.d = this.getFontMetrics(this.getFont());
    }
    
    public void reshape(final int n, final int n2, final int c, final int n3) {
        super.reshape(n, n2, c, n3);
        if (c > 0) {
            this.c = c;
        }
        super.appendText("");
    }
    
    public synchronized void setFont(final Font font) {
        super.setFont(font);
        if (font != null) {
            this.d = this.getFontMetrics(font);
        }
    }
    
    public void appendText(final String s) {
        this.a(s, 0);
    }
    
    public void a(final String s, final String s2) {
        this.a(s, this.d.stringWidth(s2));
    }
    
    public synchronized void a(final String s, final int n) {
        final StringBuffer sb = new StringBuffer();
        final int width = this.size().width;
        final int n2 = (width > 30) ? (width - 30) : (this.c - 30);
        if (this.d.stringWidth(s) <= n2) {
            sb.append(s);
        }
        else {
            final String s2 = (n > 0) ? this.a(n) : "";
            final StringTokenizer a = this.a(new StringTokenizer(s), s2, n2);
            final StringBuffer sb2 = new StringBuffer();
            while (a.hasMoreTokens()) {
                final String nextToken = a.nextToken();
                if (this.d.stringWidth((Object)sb2 + nextToken + " ") <= n2) {
                    sb2.append(nextToken);
                    if (!a.hasMoreTokens()) {
                        continue;
                    }
                    sb2.append(" ");
                }
                else {
                    sb2.append("\n");
                    sb.append(sb2.toString());
                    sb2.setLength(0);
                    sb2.append(s2);
                    sb2.append(nextToken);
                    if (!a.hasMoreTokens()) {
                        continue;
                    }
                    sb2.append(" ");
                }
            }
            sb.append(sb2.toString());
        }
        final int length = sb.length();
        if (this.b == 0) {
            super.appendText(sb.toString());
            this.b += length;
            return;
        }
        if (this.b + length < this.a) {
            super.appendText("\n" + sb.toString());
            this.b += length;
            return;
        }
        super.setText(sb.toString());
        this.b = length;
    }
    
    private StringTokenizer a(final StringTokenizer stringTokenizer, final String s, final int n) {
        final String string = s + " ";
        final StringBuffer sb = new StringBuffer();
        while (stringTokenizer.hasMoreTokens()) {
            String s2;
            String substring;
            for (s2 = stringTokenizer.nextToken(); this.d.stringWidth(s2 + string) > n; s2 = s2.substring(substring.length(), s2.length())) {
                int n2;
                for (n2 = 1; n2 <= s2.length() && this.d.stringWidth(s2.substring(0, n2) + string) <= n; ++n2) {}
                substring = s2.substring(0, n2 - 1);
                sb.append(substring + " ");
            }
            sb.append(s2 + " ");
        }
        return new StringTokenizer(sb.toString());
    }
    
    public String a(final int n) {
        final StringBuffer sb = new StringBuffer();
        while (this.d.stringWidth(sb.toString()) < n) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
