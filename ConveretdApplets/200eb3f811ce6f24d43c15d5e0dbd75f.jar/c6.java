// 
// Decompiled by Procyon v0.5.30
// 

public class c6 extends c4
{
    private int a;
    private int[] b;
    
    public c6(final int a, final int[] b) {
        super(256);
        if (n.e()) {
            n.a(a(a), "OperationEvent: op-type incorrect : " + a);
            n.a(b != null, "OperationEvent: null rows passed");
        }
        this.a = a;
        this.b = b;
    }
    
    public final int[] a() {
        return this.b;
    }
    
    public final int b() {
        return this.a;
    }
    
    public String toString() {
        return "Operation{" + b(this.a) + "; " + a(this.b) + "}";
    }
    
    private static final boolean a(final int n) {
        return n >= 100 && n <= 103;
    }
    
    private static final String b(final int n) {
        switch (n) {
            case 101: {
                return "REMOVE";
            }
            case 100: {
                return "INSERT";
            }
            case 102: {
                return "PERMUTE";
            }
            case 103: {
                return "CREATE";
            }
            default: {
                return "UNKNOWN";
            }
        }
    }
    
    private static final String a(final int[] array) {
        if (array == null) {
            return "null";
        }
        final StringBuffer sb = new StringBuffer();
        int i;
        for (i = 0; i < array.length; ++i) {
            sb.append(array[i] + ",");
        }
        String s = sb.toString();
        if (i > 0) {
            s = sb.toString().substring(0, sb.length() - 1);
        }
        return s;
    }
}
