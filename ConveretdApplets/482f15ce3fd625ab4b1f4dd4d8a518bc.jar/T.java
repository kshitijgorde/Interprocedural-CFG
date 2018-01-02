// 
// Decompiled by Procyon v0.5.30
// 

public final class T extends Thread
{
    private aE a;
    private String a;
    private int b;
    public int a;
    private boolean a;
    private boolean b;
    
    public T(final aE a, final String a2) {
        this.a = "";
        this.b = 6;
        this.a = 0;
        this.a = false;
        this.b = false;
        this.a = a2;
        this.a = a;
        this.b = 6;
    }
    
    public T(final aE a, final String a2, final boolean a3) {
        this.a = "";
        this.b = 6;
        this.a = 0;
        this.a = false;
        this.b = false;
        this.a = a2;
        this.a = a;
        this.a = a3;
        this.b = 8;
    }
    
    public T(final aE a, final String a2, final int b) {
        this.a = "";
        this.b = 6;
        this.a = 0;
        this.a = false;
        this.b = false;
        this.a = a2;
        this.a = a;
        this.a = false;
        this.b = b;
    }
    
    public final void run() {
        if (this.a.a != null) {
            this.a.a.a(this.a, this.a, this.a);
        }
        while (!this.b && this.b > 0) {
            try {
                if (this.a.c()) {
                    --this.b;
                }
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
        }
        if (this.a.a != null && this.a.a.a() != null && this.a.a.a().equals(this.a)) {
            this.a.a.a("", false, 0);
        }
    }
    
    public final void a() {
        this.b = true;
    }
}
