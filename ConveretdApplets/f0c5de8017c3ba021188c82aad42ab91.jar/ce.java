// 
// Decompiled by Procyon v0.5.30
// 

public class ce
{
    private int a;
    private int b;
    private long c;
    private Exception d;
    private String e;
    
    public ce(final int n, final Exception d, final String e) {
        this.a = 0;
        this.b = 0;
        this.b(n);
        this.c = System.currentTimeMillis();
        this.d = d;
        this.e = e;
    }
    
    public final int a() {
        return this.a;
    }
    
    public final String b() {
        return a(this.a);
    }
    
    public static final String a(final int n) {
        switch (n) {
            case 0: {
                return "STATUS_UNDEFINED";
            }
            case 1: {
                return "STATUS_INIT";
            }
            case 2: {
                return "STATUS_OK";
            }
            case 3: {
                return "STATUS_SLEEPING";
            }
            case 4: {
                return "STATUS_RECONNECTING";
            }
            case 5: {
                return "STATUS_LOST_CONNECTION";
            }
            case 6: {
                return "STATUS_SESSION_MDG_ERROR";
            }
            case 7: {
                return "STATUS_BYE";
            }
            case 8: {
                return "STATUS_SERVER_CLOSE";
            }
            default: {
                return "STATUS_UNKNOWN";
            }
        }
    }
    
    public final String c() {
        return this.e;
    }
    
    public final String toString() {
        return this.b() + ":" + this.e;
    }
    
    private void b(final int n) {
        if (n >= 1 && n <= 8) {
            this.a = n;
            this.b = 0;
        }
        else if (n == 11 || n == 12) {
            this.a = 2;
            this.b = n;
        }
        else {
            this.a = 0;
            this.b = 0;
        }
    }
}
