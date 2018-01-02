// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import ABLwidgets.abljem;
import java.awt.Event;

public class EmuRange
{
    public int a;
    public int b;
    public int c;
    public int d;
    public Event e;
    public char f;
    public EmuMenuOption g;
    private int h;
    private int i;
    
    public void a(final EmuRange emuRange) {
        this.a = emuRange.a;
        this.b = emuRange.b;
        this.c = emuRange.c;
        this.d = emuRange.d;
    }
    
    public void a(final int b, final int c) {
        if (this.e == null) {
            abljem.b("EmuRange field ignored - no event");
            return;
        }
        if (this.e.arg == null || !(this.e.arg instanceof StyleEventArg)) {
            abljem.b("EmuRange field ignored - arg not StyleEventArg");
            return;
        }
        if (b > 0 && c > 0) {
            ((StyleEventArg)this.e.arg).b = b;
            ((StyleEventArg)this.e.arg).c = c;
        }
    }
    
    public EmuRange a(final int n, final int n2, final int n3) {
        final EmuRange emuRange = new EmuRange();
        emuRange.a = this.a + n;
        emuRange.b = this.b + n2;
        emuRange.c = (emuRange.a - 1) * n3 + (emuRange.b - 1);
        emuRange.d = this.d;
        emuRange.e = this.e;
        emuRange.f = this.f;
        if (this.h > 0 && this.i > 0) {
            emuRange.a(this.h + n, this.i + n2);
        }
        return emuRange;
    }
}
