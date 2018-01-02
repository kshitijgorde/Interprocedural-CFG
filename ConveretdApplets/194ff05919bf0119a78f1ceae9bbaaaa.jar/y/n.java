// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.awt.Color;
import java.io.DataInputStream;

public abstract class n extends aj
{
    boolean a;
    private an b;
    private an c;
    boolean[] a;
    
    public void a() {
        this.b = new an(this.a().a(1716519127));
        this.c = new an(this.a().a(1716519124));
        this.a = new boolean[this.d()];
        this.b.a(true);
        if (this.a().e) {
            this.b(this.b);
            this.b(this.c);
        }
        this.b.c(false);
        super.a();
    }
    
    public void b() {
    }
    
    public synchronized void a(final byte b, final DataInputStream dataInputStream) {
        if (b == 111) {
            final boolean a = dataInputStream.readByte() != 0;
            final eu a2;
            if ((a2 = this.a(dataInputStream)).a.equals(this.a())) {
                this.a = a;
                this.b();
                if (this.e() < 0) {
                    this.o();
                    this.e();
                }
            }
            this.a("*** " + a2.b + (a ? this.a().a(1716519125) : this.a().a(1716519126)), Color.blue);
            return;
        }
        if (b == 55) {
            this.a[dataInputStream.readByte()] = (dataInputStream.readByte() != 0);
            if (this.e() < 0) {
                this.o();
            }
            return;
        }
        super.a(b, dataInputStream);
    }
    
    protected final boolean a() {
        return !this.a;
    }
    
    public void a(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.c) {
                this.a('O', (byte)(((boolean)event.arg) ? 1 : 0));
            }
            else if (event.target == this.b) {
                this.a('&', (byte)(((boolean)event.arg) ? 0 : 1));
            }
        }
        super.a(event);
    }
    
    public void a(final int n) {
        this.c.c(false);
        this.b.c(true);
        this.b.a(true);
        super.a(n);
    }
    
    public void b(final int n) {
        this.c.c(true);
        this.b.c(false);
        super.b(n);
    }
}
