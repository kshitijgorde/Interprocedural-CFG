// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.b;

import java.awt.Dimension;
import flaxchat.e.g;
import java.awt.Graphics;
import flaxchat.d.e;

class c extends e
{
    private final f m;
    
    public c(final f f, final String s, final String s2) {
        super(s, s2);
        this.m = f;
        this.m = f;
    }
    
    public void paint(final Graphics graphics) {
        final int f = flaxchat.b.h.f;
        final Dimension size = this.getSize();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(flaxchat.e.g.a(this.getBackground()));
        if (super.g) {
            graphics.setColor(flaxchat.e.g.a(this.getBackground()));
            int n = 0;
            while (true) {
                Label_0093: {
                    if (f == 0) {
                        break Label_0093;
                    }
                    graphics.drawRect(n, n, size.width - (n + n), size.height - (n + n));
                    ++n;
                }
                if (n < 2) {
                    continue;
                }
                break;
            }
        }
        flaxchat.e.c.a(graphics, this.e(), 4, 0, size.height);
    }
}
