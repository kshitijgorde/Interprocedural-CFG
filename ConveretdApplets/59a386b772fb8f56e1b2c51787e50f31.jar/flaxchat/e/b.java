// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

final class b extends WindowAdapter
{
    private final String a;
    private final e b;
    
    public void windowOpened(final WindowEvent windowEvent) {
        final boolean a = g.a;
        final int componentCount = this.b.getComponentCount();
        int n = 0;
        while (true) {
            Label_0056: {
                if (!a) {
                    break Label_0056;
                }
                final flaxchat.d.e e = (flaxchat.d.e)this.b.getComponent(n);
                if (e.g().equals(this.a)) {
                    e.requestFocus();
                    return;
                }
                ++n;
            }
            if (n >= componentCount) {
                return;
            }
            continue;
        }
    }
    
    b(final e b, final String a) {
        this.b = b;
        this.a = a;
    }
}
