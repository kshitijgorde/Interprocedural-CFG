// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import ji.util.d;
import ji.io.h;

public class bb extends Thread
{
    private static int a;
    private static int b;
    private int c;
    private String d;
    
    public bb(final String d, final Runnable runnable) {
        super(runnable, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(runnable.getClass().getName()))).append(" ").append(d))));
        this.c = 0;
        this.d = null;
        this.d = d;
        ++bb.a;
        ++bb.b;
        this.c = bb.b;
        try {
            this.setName(String.valueOf(String.valueOf(new StringBuffer("").append(this.c).append(" ").append(runnable.getClass().getName()).append(" ").append(d))));
        }
        catch (Throwable t) {}
    }
    
    public void run() {
        try {
            super.run();
        }
        catch (Throwable t) {
            h.d(this.d, "Thread failed: ".concat(String.valueOf(String.valueOf(this.getName()))));
            ji.util.d.a(t);
        }
    }
    
    public void start() {
        super.start();
    }
    
    public void finalize() {
        --bb.a;
    }
    
    static {
        bb.a = 0;
        bb.b = 0;
    }
}
