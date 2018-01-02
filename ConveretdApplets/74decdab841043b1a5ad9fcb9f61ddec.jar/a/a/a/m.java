// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.applet.Applet;
import netscape.javascript.JSObject;
import java.util.Arrays;

final class m implements Runnable
{
    private /* synthetic */ String a;
    private /* synthetic */ boolean b;
    private /* synthetic */ String[] c;
    private /* synthetic */ p d;
    
    m(final p d, final String a, final boolean b, final String[] c) {
        this.d = d;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final void run() {
        if (this.a == null) {
            f.b("Can't call js since func is null.");
            return;
        }
        if (this.d.a == null) {
            final p d = this.d;
            f.b("No window so can't call function.");
            return;
        }
        if (!this.b) {
            f.b("Calling: " + this.a + "(" + Arrays.toString(this.c) + ")");
        }
        try {
            final p d2 = this.d;
            this.d.a.call(this.a, (Object[])this.c);
        }
        catch (Exception ex) {
            f.a("Error calling js function: " + this.a, ex);
        }
    }
}
