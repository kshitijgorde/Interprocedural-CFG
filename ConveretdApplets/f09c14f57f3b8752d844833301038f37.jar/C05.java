import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class C05 extends Observable implements Runnable
{
    boolean f;
    boolean g;
    long h;
    Thread i;
    
    public void run() {
        while (!this.g) {
            try {
                Thread.sleep(this.h);
            }
            catch (InterruptedException ex) {
                System.out.println("Error 323221");
            }
            if (!this.f) {
                this.setChanged();
                this.notifyObservers(null);
            }
            else {
                System.out.println("Paused Time Thread");
            }
        }
    }
    
    public void a() {
        this.i.start();
        this.g = false;
    }
    
    public void b() {
        this.f = true;
    }
    
    public C05() {
        this.g = false;
        this.f = false;
        this.i = new Thread(this);
    }
    
    public void c(final long h) {
        this.h = h;
    }
    
    public void d() {
        this.i.stop();
        this.g = true;
    }
    
    public void e() {
        this.f = false;
    }
}
