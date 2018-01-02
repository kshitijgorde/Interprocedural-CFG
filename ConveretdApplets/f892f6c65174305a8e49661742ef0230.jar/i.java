import java.net.URL;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

class i extends Thread
{
    private Component for;
    private URL if;
    private String byte;
    private String a;
    private e do;
    private s new;
    private int int;
    private long try;
    
    public i(final String byte1, final String a, final URL if1, int int1, final e do1, final Component for1) {
        this.int = -1;
        this.try = -1L;
        this.new = do1.s;
        this.do = do1;
        this.byte = byte1;
        this.a = a;
        this.if = if1;
        this.for = for1;
        this.int = int1;
        if (int1 > 0) {
            if (int1 < 5) {
                int1 = 5;
            }
            this.try = System.currentTimeMillis() + int1 * 1000;
        }
        if (this.byte != null && this.do != null && this.for != null) {
            this.start();
        }
    }
    
    public void run() {
        final String[] array = { null };
        this.setPriority(1);
        while (!this.do.k && this.new == this.do.s && this.try > System.currentTimeMillis()) {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
        if (this.do.u || this.do.k || this.new != this.do.s) {
            return;
        }
        this.do.u = true;
        this.do.a(this.new, 80);
        final s s = new s();
        s.void = this.byte;
        s.new = this.a;
        s.for = this.do;
        if (this.if != null) {
            array[0] = h.a(this.if, this.byte);
        }
        else {
            array[0] = h.a((String)null, this.byte);
        }
        if (array[0] == null) {
            this.do.u = false;
            System.out.println("error reading file: " + this.byte);
            return;
        }
        if (!this.do.k) {
            if (this.if != null) {
                h.a(this.if, array, s, this.for);
            }
            else {
                h.a(null, array, s, this.for);
            }
            if (array[0] == null) {
                this.do.u = false;
                System.out.println("error parsing file: " + this.byte);
                return;
            }
            if (!this.do.k && this.new == this.do.s) {
                this.do.a(s);
            }
        }
    }
}
