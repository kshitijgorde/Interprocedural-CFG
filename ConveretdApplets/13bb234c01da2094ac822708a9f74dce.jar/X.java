import java.util.Comparator;
import com.daysofwonder.a.d;
import com.daysofwonder.util.UIProperties;
import ca.odell.glazedlists.gui.AdvancedTableFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class X implements AdvancedTableFormat
{
    private static final int[] a;
    private static final String[] b;
    private static int[] c;
    private UIProperties d;
    
    public X(final UIProperties d) {
        this.d = d;
    }
    
    public int a() {
        return X.a.length;
    }
    
    public String a(final int n) {
        return this.d.b(X.b[n]);
    }
    
    public Object a(final d d, final int n) {
        switch (n) {
            case 0: {
                return d.e;
            }
            default: {
                return "";
            }
        }
    }
    
    public Class b(final int n) {
        return String.class;
    }
    
    public Comparator c(final int n) {
        return String.CASE_INSENSITIVE_ORDER;
    }
    
    public int d(final int n) {
        return X.c[n];
    }
    
    public int e(final int n) {
        return X.a[n];
    }
    
    static {
        a = new int[] { 350 };
        b = new String[] { "ignorelist" };
        X.c = new int[] { 10 };
    }
}
