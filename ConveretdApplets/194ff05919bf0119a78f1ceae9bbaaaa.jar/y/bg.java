// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Image;

public final class bg
{
    public l a;
    int a;
    int b;
    String a;
    public String b;
    public Image a;
    String c;
    
    bg(final l a) {
        this.a = a;
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer("AdData:m_adLocation=")).append(this.a.toString());
        sb.append(",width=");
        sb.append(this.a);
        sb.append(",height=");
        sb.append(this.b);
        sb.append(",cscUrl=");
        sb.append(this.a);
        sb.append(",onClickUrl=");
        sb.append(this.b);
        sb.append(",m_adImageUrl=");
        sb.append(this.c);
        return sb.toString();
    }
}
