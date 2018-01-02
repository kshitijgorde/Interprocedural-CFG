import java.util.Comparator;
import com.daysofwonder.tt.m;
import com.daysofwonder.applet.ar;
import ca.odell.glazedlists.SeparatorList$Separator;
import com.daysofwonder.util.UIProperties;
import ca.odell.glazedlists.gui.AdvancedTableFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class r implements AdvancedTableFormat
{
    private static final int[] a;
    private static final String[] b;
    private static int[] c;
    private boolean d;
    private UIProperties e;
    
    public r(final UIProperties e, final boolean d) {
        this.d = d;
        this.e = e;
    }
    
    public int a() {
        return r.a.length;
    }
    
    public String a(final int n) {
        return this.e.b(r.b[n]);
    }
    
    public Object a(final Object o, final int n) {
        if (o == null) {
            return null;
        }
        if (o instanceof SeparatorList$Separator) {
            final SeparatorList$Separator separatorList$Separator = (SeparatorList$Separator)o;
            return "------";
        }
        final ar ar = (ar)o;
        switch (n) {
            case 0: {
                return ar.i ? this.e.b("newbies_game") : ar.a;
            }
            case 1: {
                final int a = y.a((m)ar.q);
                return (a >= 0) ? this.e.b("map." + a) : "";
            }
            case 2: {
                return this.b(ar);
            }
            case 3: {
                return this.a(ar);
            }
            default: {
                return "";
            }
        }
    }
    
    private String a(final ar ar) {
        final StringBuffer sb = new StringBuffer();
        if (ar.l) {
            if (ar.j) {
                sb.append(this.e.b("private"));
            }
            if (ar.h) {
                if (ar.j) {
                    sb.append(" - ");
                }
                sb.append(this.e.b("ranking"));
            }
            if (!ar.m) {
                if (ar.j || ar.h) {
                    sb.append(" - ");
                }
                sb.append(this.e.b("notlurkable"));
            }
            if (((m)ar.q).c()) {
                if (ar.j || ar.h || !ar.m) {
                    sb.append(" - ");
                }
                sb.append(this.e.b("notfirst"));
            }
            if (ar.o >= 2) {
                if (ar.j || ar.h || !ar.m || ((m)ar.q).c()) {
                    sb.append(" - ");
                }
                sb.append(this.e.b("maxplayers")).append('[').append(((m)ar.q).e()).append('-').append(ar.o).append(']');
            }
            if (ar.p >= 0) {
                if (ar.j || ar.h || ar.o >= 2) {
                    sb.append(" - ");
                }
                sb.append(this.e.b("minkarma")).append(' ').append(y.c(y.b(ar.p)));
            }
        }
        else {
            sb.append(ar.k);
            sb.append("% ");
            if (!ar.m) {
                sb.append(" - ").append(this.e.b("notlurkable"));
            }
            if (!ar.h) {
                sb.append(" - ").append(this.e.b("ranking"));
            }
        }
        return sb.toString();
    }
    
    private String b(final ar ar) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ar.b.length; ++i) {
            sb.append(ar.b[i]);
            if (!this.d || (this.d && ar.e[i] != -1)) {
                sb.append(" (").append(this.d ? ar.e[i] : ar.d[i]);
                sb.append(")");
            }
            if (i < ar.b.length - 1) {
                sb.append(" - ");
            }
        }
        return sb.toString();
    }
    
    public Class b(final int n) {
        return String.class;
    }
    
    public Comparator c(final int n) {
        return String.CASE_INSENSITIVE_ORDER;
    }
    
    public int d(final int n) {
        return r.c[n];
    }
    
    public int e(final int n) {
        return r.a[n];
    }
    
    static {
        a = new int[] { 100, 75, 386, 170 };
        b = new String[] { "game.header.text.1", "game.header.text.2", "game.header.text.3", "game.header.text.4" };
        r.c = new int[] { 2, 0, 2, 4 };
    }
}
