// 
// Decompiled by Procyon v0.5.30
// 

public final class b extends Thread
{
    protected c a;
    protected VoipApplet b;
    private boolean c;
    
    public b(final VoipApplet b, final c a) {
        super("WatchThread");
        this.c = true;
        this.a = a;
        this.b = b;
        this.c = true;
    }
    
    public final void a() {
        this.c = false;
    }
    
    public final void run() {
        while (this.c) {
            if (this.a.d != "" && this.a.e == "") {
                this.a.e = this.a.d;
                this.a.d = "";
                this.b.a("testend", this.a.e + " " + this.a.e(this.a.e));
                this.a.e = "";
            }
            else {
                try {
                    Thread.sleep(200L);
                }
                catch (InterruptedException ex) {}
            }
        }
        System.out.println("WatchThread destroyed");
    }
}
