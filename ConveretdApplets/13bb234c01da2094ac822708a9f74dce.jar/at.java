import java.util.Comparator;
import com.daysofwonder.applet.k;
import ca.odell.glazedlists.SeparatorList$Separator;
import com.daysofwonder.util.UIProperties;
import ca.odell.glazedlists.gui.AdvancedTableFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class at implements AdvancedTableFormat
{
    private static final int[] a;
    private static final String[] b;
    private static int[] c;
    private UIProperties d;
    private String e;
    
    public at(final UIProperties d) {
        this.d = d;
        this.e = " " + this.d.b("lurking");
    }
    
    public int a() {
        return at.a.length;
    }
    
    public String a(final int n) {
        return this.d.b(at.b[n]);
    }
    
    public Object a(final Object o, final int n) {
        if (o == null) {
            return null;
        }
        if (o instanceof SeparatorList$Separator) {
            final SeparatorList$Separator separatorList$Separator = (SeparatorList$Separator)o;
            return "------";
        }
        final k k = (k)o;
        switch (n) {
            case 0: {
                return k;
            }
            case 1: {
                return k.d();
            }
            default: {
                return "";
            }
        }
    }
    
    public Class b(final int n) {
        return (Class)((n == 0) ? k.class : Integer.class);
    }
    
    public Comparator c(final int n) {
        return String.CASE_INSENSITIVE_ORDER;
    }
    
    public int d(final int n) {
        return at.c[n];
    }
    
    public int e(final int n) {
        return at.a[n];
    }
    
    static {
        a = new int[] { 113, 60 };
        b = new String[] { "players.header", "players.header.karma" };
        at.c = new int[] { 10, 0 };
    }
}
