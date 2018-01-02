import java.util.Comparator;
import com.daysofwonder.applet.ar;
import com.daysofwonder.a.d;
import com.daysofwonder.util.UIProperties;
import ca.odell.glazedlists.gui.AdvancedTableFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class P implements AdvancedTableFormat
{
    private static final String[] a;
    private static final int[] b;
    private static final String[] c;
    private static int[] d;
    private UIProperties e;
    private y f;
    
    public P(final y f, final UIProperties e) {
        this.e = e;
        this.f = f;
    }
    
    public int a() {
        return P.b.length;
    }
    
    public String a(final int n) {
        return this.e.b(P.c[n]);
    }
    
    public Object a(final d d, final int n) {
        switch (n) {
            case 0: {
                return d.e;
            }
            case 1: {
                return this.a(this.e.b(P.a[d.d.a()]), d.d.b());
            }
            default: {
                return "";
            }
        }
    }
    
    private String a(final String s, final String s2) {
        if (s != null) {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                if (char1 == '%') {
                    if (i < s.length() - 1) {
                        final char char2 = s.charAt(++i);
                        if (char2 == 's') {
                            if (s2 != null && (s2.equals("TT") || s2.equals("QN") || s2.equals("DS") || s2.equals("GF"))) {
                                sb.append(this.e.b("status.playing_other." + s2));
                            }
                            else if (s2 != null) {
                                final ar b = this.f.b(Integer.valueOf(Integer.parseInt(s2)));
                                if (b != null) {
                                    sb.append(b.a);
                                }
                                else {
                                    final ar a = this.f.a(Integer.valueOf(Integer.parseInt(s2)));
                                    if (a != null) {
                                        sb.append(a.a);
                                    }
                                }
                            }
                        }
                        else {
                            sb.append(char1).append(char2);
                        }
                    }
                    else {
                        sb.append(char1);
                    }
                }
                else {
                    sb.append(char1);
                }
            }
            return sb.toString();
        }
        return "";
    }
    
    public Class b(final int n) {
        return String.class;
    }
    
    public Comparator c(final int n) {
        return String.CASE_INSENSITIVE_ORDER;
    }
    
    public int d(final int n) {
        return P.d[n];
    }
    
    public int e(final int n) {
        return P.b[n];
    }
    
    static {
        a = new String[] { "status.unknown", "status.disconnected", "status.lobby", "status.playing", "status.open", "status.playing_other", "status.browsing", "status.lurking" };
        b = new int[] { 125, 135 };
        c = new String[] { "buddy.header", "buddy.header.action" };
        P.d = new int[] { 10, 0 };
    }
}
