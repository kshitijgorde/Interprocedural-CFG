// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.FontMetrics;
import java.awt.Font;

final class dn extends u
{
    private Font b;
    private FontMetrics a;
    private cc a;
    private int a;
    private int b;
    
    dn(final cc a) {
        super(true);
        this.b = new Font(u.a.getName(), 0, 8);
        this.a = a;
    }
    
    public final void b() {
        super.b();
        this.a = this.a(this.b);
        this.a = Math.max(this.a.stringWidth(this.a.a), this.a.stringWidth("Audio Alert")) + 8;
        this.b = this.a.getHeight();
    }
    
    public final void a(final ei ei) {
        this.b(ei);
        ei.a(this.b);
        ei.a(this.a.h);
        ei.a.fillRoundRect(0 + ei.a, 0 + ei.b, this.a, this.b << 1, this.b, this.b);
        ei.a(this.a.i);
        ei.a(this.a.a, 4, this.b - this.a.getDescent());
    }
    
    public final int a() {
        return this.a = Math.max(this.a.stringWidth(this.a.a), this.a.stringWidth("Audio Alert")) + 8;
    }
    
    public final int b() {
        return this.b = this.a.getHeight();
    }
}
