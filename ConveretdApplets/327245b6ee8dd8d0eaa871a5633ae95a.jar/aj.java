import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class aj extends Thread
{
    private Vector a;
    ak b;
    
    public aj() {
        this.a = new Vector(2, 10);
        this.b = new ak(false);
    }
    
    public void run() {
        while (true) {
            Object o;
            if (this.a.isEmpty()) {
                final ak ak = (ak)(o = this.b);
                if (!c.l) {
                    if (!ak.c()) {
                        try {
                            this.b.a();
                        }
                        catch (InterruptedException ex) {}
                        continue;
                    }
                    continue;
                }
            }
            else {
                o = this.a.firstElement();
            }
            final t t = (t)o;
            t.b();
            this.a.removeElement(t);
        }
    }
    
    void a(final t t) {
        if (this.a.indexOf(t) == -1) {
            this.a.addElement(t);
            this.b.b();
        }
    }
}
