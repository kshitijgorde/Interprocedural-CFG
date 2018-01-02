import java.awt.Color;
import java.text.ParseException;
import java.util.Vector;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.awt.Component;
import java.text.Format;

// 
// Decompiled by Procyon v0.5.30
// 

public final class z
{
    public d a;
    public String b;
    public char c;
    public Object d;
    public Object e;
    public Format f;
    public static /* synthetic */ Class g;
    public static /* synthetic */ Class h;
    public static /* synthetic */ Class i;
    
    public z(final d a, final String s, String b, final String b2) {
        this.c = 'T';
        this.a = a;
        this.b = b2;
        if (s != null && s.length() > 0) {
            switch (s.charAt(0)) {
                case 'D':
                case 'I':
                case 'M':
                case 'N':
                case 'T':
                case 'U': {
                    this.c = s.charAt(0);
                    break;
                }
                default: {
                    this.a.c().a((Component)this.a.s, 16, new Object[] { s, "" + this.c });
                    break;
                }
            }
        }
        if (b != null || this.c == 'M' || this.c == 'U') {
            switch (this.c) {
                case 'I':
                case 'N': {
                    b = this.b(b);
                    if (b.startsWith("F")) {
                        this.f = new as(this.a, b);
                        break;
                    }
                    this.f = new DecimalFormat(b);
                    break;
                }
                case 'D': {
                    this.f = new SimpleDateFormat(b);
                    break;
                }
                case 'M': {
                    this.f = new at(this.a, b, false);
                    break;
                }
                case 'U': {
                    this.f = new at(this.a, b, true);
                    break;
                }
            }
        }
    }
    
    private String b(String string) {
        if (string != null) {
            final Vector a = f.a(string, ";");
            if (a.size() > 0) {
                this.d = this.a(a, 0);
                string = a.elementAt(0);
            }
            if (a.size() > 1) {
                this.e = this.a(a, 1);
                string = string + ";" + (Object)a.elementAt(1);
            }
        }
        return string;
    }
    
    private Object a(final Vector vector, final int n) {
        Object f = null;
        final String s = vector.elementAt(n);
        if (s.startsWith("[") && s.indexOf("]") >= 0) {
            final String substring = s.substring(0, s.indexOf("]") + 1);
            vector.setElementAt(s.substring(substring.length()), n);
            final String trim = substring.substring(1, substring.length() - 1).trim();
            if (trim.equals("PosColor") || trim.equals("MidColor") || trim.equals("NegColor")) {
                f = trim;
            }
            else {
                try {
                    f = this.a.d().f(trim);
                }
                catch (ParseException ex) {
                    this.a.c().a((Component)this.a.s, 17, new Object[0]);
                }
            }
        }
        return f;
    }
    
    public final Object a(Object o) {
        switch (this.c) {
            case 'M':
            case 'U': {
                if (o != null) {
                    try {
                        o = this.f.parseObject((String)o);
                    }
                    catch (ParseException ex) {
                        o = null;
                    }
                    break;
                }
                break;
            }
            default: {
                if (o == null) {
                    o = this.b;
                    break;
                }
                if (this.f != null) {
                    try {
                        o = this.f.format(o);
                    }
                    catch (IllegalArgumentException ex2) {
                        o = this.b;
                    }
                    break;
                }
                o = o.toString();
                break;
            }
        }
        return o;
    }
    
    public final Object a(final String s) {
        Object o = null;
        switch (this.c) {
            case 'M':
            case 'U': {
                o = s;
                break;
            }
            case 'I': {
                if (s != null) {
                    try {
                        o = this.a.d().c(s);
                    }
                    catch (Exception ex) {
                        this.a.c().a((Component)this.a.s, 2, new Object[] { s, (z.g == null) ? (z.g = class$("java.lang.Integer")) : z.g, ex.getMessage() });
                    }
                    break;
                }
                break;
            }
            case 'N': {
                if (s != null) {
                    try {
                        o = this.a.d().d(s);
                    }
                    catch (ParseException ex2) {
                        this.a.c().a((Component)this.a.s, 2, new Object[] { s, (z.h == null) ? (z.h = class$("java.lang.Double")) : z.h, ex2.getMessage() });
                    }
                    break;
                }
                break;
            }
            case 'D': {
                if (s != null) {
                    try {
                        o = this.a.d().j(s);
                    }
                    catch (ParseException ex3) {
                        this.a.c().a((Component)this.a.s, 2, new Object[] { s, (z.i == null) ? (z.i = class$("java.util.Date")) : z.i, ex3.getMessage() });
                    }
                    break;
                }
                break;
            }
            default: {
                o = s;
                if (s != null && s.trim().length() == 0) {
                    o = null;
                    break;
                }
                break;
            }
        }
        return o;
    }
    
    public final Color a(final Object o, final l l) {
        Color color = null;
        final Object o2;
        if (o instanceof Number && (o2 = ((((Number)o).doubleValue() >= 0.0) ? this.d : this.e)) != null) {
            if (o2 instanceof Color) {
                color = (Color)o2;
            }
            else if ("PosColor".equals(o2)) {
                color = l.b(2);
            }
            else if ("MidColor".equals(o2)) {
                color = l.b(1);
            }
            else if ("NegColor".equals(o2)) {
                color = l.b(0);
            }
        }
        return color;
    }
    
    public final void a() {
        this.f = null;
        this.e = null;
        this.d = null;
        this.a = null;
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
