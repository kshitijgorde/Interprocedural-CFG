// 
// Decompiled by Procyon v0.5.30
// 

public class aw extends an
{
    private int a;
    
    public aw(final String s) {
        super(s);
    }
    
    public aw(final String s, final Throwable t) {
        super(s, t);
    }
    
    public aw(final String s, final Throwable t, final int n) {
        this(s, t);
        this.b(n);
    }
    
    public String toString() {
        return "MDGConfigException: " + a(this.c());
    }
    
    private void b(final int a) {
        this.a = a;
    }
    
    public static final String a(final int n) {
        switch (n) {
            case 1: {
                return "LOGIN_TIMEOUT";
            }
            case 2: {
                return "LOGIN_HTTP_ERROR";
            }
            case 3: {
                return "LOGIN_NETWORK_ERROR";
            }
            case 4: {
                return "LOGIN_COMMON_ERROR";
            }
            case 5: {
                return "LOGIN_WRONG_VALUE";
            }
            case 6: {
                return "PUSH_ESTABLISH_TIMEOUT";
            }
            case 7: {
                return "PUSH_HTTP_ERROR";
            }
            case 8: {
                return "PUSH_NETWORK_ERROR";
            }
            case 9: {
                return "PUSH_NO_PERMISSION";
            }
            case 10: {
                return "PUSH_INVALID_OBJECT";
            }
            case 11: {
                return "CONFIGURATION_ERROR";
            }
            default: {
                return "UnmatchedError";
            }
        }
    }
    
    public final int c() {
        return this.a;
    }
}
