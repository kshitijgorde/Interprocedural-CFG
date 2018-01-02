// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.TextArea;

public class aa extends TextArea
{
    private static final int jd = 30;
    private static final String kd;
    private int ld;
    private int md;
    private int nd;
    private int od;
    private FontMetrics pd;
    
    public aa(final int ld) {
        this.od = 30;
        this.ld = ld;
    }
    
    public void addNotify() {
        super.addNotify();
        this.pd = this.getFontMetrics(this.getFont());
    }
    
    public void reshape(final int n, final int n2, final int od, final int n3) {
        super.reshape(n, n2, od, n3);
        if (od > 0) {
            this.od = od;
        }
        super.appendText("");
    }
    
    public synchronized void setFont(final Font font) {
        super.setFont(font);
        this.pd = this.getFontMetrics(font);
    }
    
    public void appendText(final String s) {
        this.kb(s, 0);
    }
    
    public void kb(final String s, final String s2) {
        this.kb(s, this.pd.stringWidth(s2));
    }
    
    protected synchronized void kb(final String s, final int n) {
        final StringBuffer sb = new StringBuffer();
        final int width = this.size().width;
        final int n2 = (width > 30) ? (width - 30) : (this.od - 30);
        if (this.pd.stringWidth(s) <= n2) {
            sb.append(s);
        }
        else {
            String lb = "";
            if (n > 0) {
                lb = this.lb(n);
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(s, " \t", false);
            final StringBuffer sb2 = new StringBuffer();
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (this.pd.stringWidth((Object)sb2 + nextToken) <= n2) {
                    sb2.append(nextToken);
                    if (!stringTokenizer.hasMoreTokens()) {
                        continue;
                    }
                    sb2.append(' ');
                }
                else if (sb2.length() == 0) {
                    sb2.append(nextToken);
                    sb2.append(aa.kd);
                    sb.append(sb2.toString());
                    sb2.setLength(0);
                    sb2.append(lb);
                }
                else {
                    sb2.append(aa.kd);
                    sb.append(sb2.toString());
                    sb2.setLength(0);
                    sb2.append(lb);
                    sb2.append(nextToken);
                    if (!stringTokenizer.hasMoreTokens()) {
                        continue;
                    }
                    sb2.append(' ');
                }
            }
            sb.append(sb2.toString());
        }
        final int length = sb.length();
        if (this.nd == 0) {
            super.appendText(sb.toString());
            this.nd += length;
            return;
        }
        if (this.nd + length < this.ld) {
            super.appendText(aa.kd + sb.toString());
            this.nd += length;
            return;
        }
        super.setText(sb.toString());
        this.nd = length;
    }
    
    protected String lb(final int n) {
        final StringBuffer sb = new StringBuffer();
        while (this.pd.stringWidth(sb.toString()) < n) {
            sb.append(' ');
        }
        return sb.toString();
    }
    
    static {
        kd = System.getProperty("line.separator", "\n");
    }
}
