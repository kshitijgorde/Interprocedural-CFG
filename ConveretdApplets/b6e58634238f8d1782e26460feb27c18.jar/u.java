import java.util.Enumeration;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.awt.Font;
import java.util.Properties;
import java.awt.Component;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class u
{
    public static final a2[] a;
    public d b;
    public s c;
    public Hashtable d;
    public Vector e;
    public boolean f;
    
    private boolean a(final String s, final String s2, final int[] array) {
        try {
            array[0] = this.b.d().c(s);
            if (array[0] < 0) {
                throw new Exception(s + " negative");
            }
            return true;
        }
        catch (Exception ex) {
            this.b.c().a((Component)this.b.s, 4, new Object[] { s, s2, ex.getMessage() });
            return false;
        }
    }
    
    private boolean a(final Properties properties, final String s, final int[] array) {
        final String property = properties.getProperty(s);
        return property != null && this.a(property, s, array);
    }
    
    private boolean b(final Properties properties, final String s, final int[] array) {
        if (!this.a(properties, s, array)) {
            return false;
        }
        if (array[0] > 0) {
            return true;
        }
        this.b.c().a((Component)this.b.s, 4, new Object[] { properties.getProperty(s), s, "nonpositive" });
        return false;
    }
    
    private boolean a(final Properties properties, final String s, final boolean[] array) {
        final String property = properties.getProperty(s);
        return property != null && this.a(property, s, array);
    }
    
    private boolean a(final String s, final String s2, final boolean[] array) {
        try {
            array[0] = this.b.d().e(s);
            return true;
        }
        catch (Exception ex) {
            this.b.c().a((Component)this.b.s, 4, new Object[] { s, s2, ex.getMessage() });
            return false;
        }
    }
    
    private boolean a(final Properties properties, final String s, final String[] array) {
        array[0] = properties.getProperty(s);
        return array[0] != null && this.a(array[0], s, new Font[1]);
    }
    
    private boolean a(final String s, final String s2, final Font[] array) {
        try {
            array[0] = this.b.d().g(s);
            return true;
        }
        catch (ParseException ex) {
            this.b.c().a((Component)this.b.s, 4, new Object[] { s, s2, ex.getMessage() });
            return false;
        }
    }
    
    private boolean a(final Properties properties, final String s, final URL[] array) {
        final String property = properties.getProperty(s);
        return property != null && this.a(property, s, array);
    }
    
    private boolean a(final String s, final String s2, final URL[] array) {
        try {
            array[0] = this.b.d().a(s);
            return true;
        }
        catch (MalformedURLException ex) {
            this.b.c().a((Component)this.b.s, 4, new Object[] { s, s2, ex.getMessage() });
            return false;
        }
    }
    
    private boolean a(final Properties properties, final String s, final Color[] array) {
        final String property = properties.getProperty(s);
        return property != null && this.a(property, s, array);
    }
    
    private boolean a(final String s, final String s2, final Color[] array) {
        try {
            array[0] = this.b.d().f(s);
            return true;
        }
        catch (ParseException ex) {
            this.b.c().a((Component)this.b.s, 4, new Object[] { s, s2, ex.getMessage() });
            return false;
        }
    }
    
    private boolean a(final String s, final String s2, final Double[] array) {
        try {
            array[0] = this.b.d().d(s);
            return true;
        }
        catch (ParseException ex) {
            this.b.c().a((Component)this.b.s, 7, new Object[] { s, s2, ex.getMessage() });
            return false;
        }
    }
    
    private void a(final Properties properties, final a2 object, final v v) {
        if (object.a.equals("C")) {
            this.c(properties, object, v);
            return;
        }
        final int[] array = { 0 };
        String s = null;
        switch (object.c) {
            case 0: {
                if (!this.a(properties, object.d, array)) {
                    return;
                }
                s = Integer.toString(array[0]);
                break;
            }
            case 1: {
                if (!this.b(properties, object.d, array)) {
                    return;
                }
                s = Integer.toString(array[0]);
                break;
            }
            case 2: {
                final boolean[] array2 = { false };
                if (!this.a(properties, object.d, array2)) {
                    return;
                }
                s = (array2[0] ? "true" : "false");
                break;
            }
            case 100: {
                this.b(properties, object, v);
                return;
            }
            case 3: {
                final URL[] array3 = { null };
                if (!this.a(properties, object.d, array3)) {
                    return;
                }
                s = array3[0].toString();
                break;
            }
            case 4:
            case 5: {
                s = properties.getProperty(object.d);
                if (s == null) {
                    return;
                }
                if (object.c == 5) {
                    s = s.trim();
                    if (s.length() != 0) {
                        final String s2 = s;
                        this.b.d();
                        if (!s2.equalsIgnoreCase("None")) {
                            break;
                        }
                    }
                    s = null;
                    break;
                }
                break;
            }
            case 6: {
                final Color[] array4 = { null };
                if (!this.a(properties, object.d, array4)) {
                    return;
                }
                s = Integer.toString(array4[0].getRed()) + "," + Integer.toString(array4[0].getGreen()) + "," + Integer.toString(array4[0].getBlue());
                break;
            }
            default: {
                return;
            }
        }
        v.a(object.a, 0, object.b, 0, 0, s);
    }
    
    private void b(final Properties properties, final a2 object, final v v) {
        final String[] array = { null };
        if (!this.a(properties, object.d, array)) {
            return;
        }
        this.b.d();
        final Vector a = f.a(array[0], ",");
        if (a.size() > 0) {
            v.a(object.a, 0, object.b, 0, 0, a.elementAt(0));
        }
        if (a.size() > 1) {
            v.a(object.a, 0, object.b, 0, 2, a.elementAt(1));
        }
        if (a.size() > 2) {
            v.a(object.a, 0, object.b, 0, 1, a.elementAt(2));
        }
    }
    
    private void c(final Properties properties, final a2 object, final v v) {
        if (object.b.equals("W")) {
            this.d(properties, object, v);
            return;
        }
        String s = null;
        switch (object.c) {
            case 1: {
                final int[] array = { 0 };
                if (!this.b(properties, object.d, array)) {
                    return;
                }
                s = Integer.toString(array[0]);
                break;
            }
            case 4:
            case 5: {
                s = properties.getProperty(object.d);
                if (s == null) {
                    return;
                }
                if (object.c == 5) {
                    s = s.trim();
                    if (s.length() != 0) {
                        final String s2 = s;
                        this.b.d();
                        if (!s2.equalsIgnoreCase("None")) {
                            break;
                        }
                    }
                    s = null;
                    break;
                }
                break;
            }
            default: {
                s = null;
                break;
            }
        }
        String string = null;
        if (object.b.equals("E") && s != null) {
            this.b.d();
            final Vector a = f.a(s, ",");
            if (a.size() > 0) {
                s = a.elementAt(0);
            }
            if (a.size() > 1) {
                try {
                    string = this.b.d().e(a.elementAt(1)).toString();
                }
                catch (Exception ex) {
                    this.b.c().a((Component)this.b.s, 4, new Object[] { string, object.b, ex.getMessage() });
                }
            }
        }
        for (int b = v.a(object.a).b(), i = 0; i < b; ++i) {
            v.a(object.a, i, object.b, 0, 0, s);
            if (object.b.equals("E")) {
                v.a(object.a, i, object.b, 0, 1, string);
            }
        }
    }
    
    private void d(final Properties properties, final a2 object, final v v) {
        final String property = properties.getProperty(object.d);
        if (property == null) {
            return;
        }
        this.b.d();
        final Vector a = f.a(property, ",");
        final int b = v.a(object.a).b();
        int int1;
        try {
            int1 = Integer.parseInt(v.a("Su", 0, "N", 0, 0));
        }
        catch (NumberFormatException ex) {
            this.b.c().a((Component)this.b.s, 16, new Object[] { "N", ex.getMessage() });
            return;
        }
        for (int n = 0; n < b && n < a.size(); ++n) {
            try {
                final int min = Math.min(this.b.d().c(a.elementAt(n)), int1);
                if (min < 0) {
                    throw new Exception(Integer.toString(min) + " nonpositive");
                }
                v.a(object.a, n, object.b, 0, 0, Integer.toString(min));
            }
            catch (Exception ex2) {
                this.b.c().a((Component)this.b.s, 4, new Object[] { property, object.d + " " + n, ex2.getMessage() });
            }
        }
    }
    
    public void a(final v v, final Properties properties) {
        for (int i = 0; i < u.a.length; ++i) {
            this.a(properties, u.a[i], v);
        }
        final String[] array = { "FlyoverDOPList1", "ClickThroughLabel1" };
        for (int j = 0; j < array.length; ++j) {
            final String substring = array[j].substring(0, array[j].length() - 1);
            int n = 1;
            while (true) {
                final String property = properties.getProperty(substring + n);
                if (property == null) {
                    break;
                }
                if (array[j].equals("FlyoverDOPList1")) {
                    this.a(v, n - 1, property);
                }
                else if (array[j].equals("ClickThroughLabel1")) {
                    this.b(v, n - 1, property);
                }
                ++n;
            }
        }
    }
    
    private void a(final v v, final int n, final String s) {
        if (s.length() == 0) {
            return;
        }
        final v a = v.a("P");
        for (int i = 0; i < a.b(); ++i) {
            final String b = a.a(i).a((String)null).a(0).b(0);
            if (b != null) {
                int intValue;
                try {
                    intValue = this.b.d().c(b);
                }
                catch (Exception ex) {
                    continue;
                }
                if (intValue - 1 == n) {
                    final Vector<Integer> vector = new Vector<Integer>(s.length());
                    vector.setSize(s.length());
                    for (int j = 0; j < s.length(); ++j) {
                        try {
                            vector.setElementAt(Integer.valueOf(s.substring(j, 1)), j);
                        }
                        catch (NumberFormatException ex2) {}
                    }
                    if (v.a("P").a(i).b("H") && vector.elementAt(0) != null) {
                        v.a("P", i, "H", 0, 2, vector.elementAt(0).toString());
                        vector.removeElementAt(0);
                    }
                    if (v.a("P").a(i).a("L").b() == vector.size()) {
                        for (int k = 0; k < vector.size(); ++k) {
                            if (vector.elementAt(k) != null) {
                                v.a("P", i, "L", k, 2, vector.elementAt(k).toString());
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void b(final v v, final int n, final String s) {
        if (s.trim().length() == 0) {
            return;
        }
        final v a = v.a("L");
        for (int i = 0; i < a.b(); ++i) {
            final String b = a.a(i).a((String)null).a(0).b(0);
            if (b != null) {
                try {
                    if (this.b.d().c(b) - 1 == n) {
                        v.a("L", i, "L", 0, 0, s);
                    }
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public u(final d b, final s c, final String s, final boolean f) {
        this.b = b;
        this.c = c;
        this.d = new Hashtable();
        if (s != null) {
            this.b.d();
            this.e = f.a(s, ";");
            for (int i = 0; i < this.e.size(); ++i) {
                final Vector e = this.e;
                this.b.d();
                e.setElementAt(f.a(this.e.elementAt(i), ","), i);
            }
        }
        this.f = f;
    }
    
    public void a(final v v) {
        boolean b = false;
        String a = v.a("B", 0, null, 0, 0);
        if (a == null) {
            return;
        }
        if (a.equals("init")) {
            if (this.f) {
                this.a(v, this.b.k());
            }
            this.b(v);
            b = true;
            a = "update";
        }
        else if (a.equals("reinit")) {
            for (int i = 0; i < this.c.f(); ++i) {
                this.c.c(true);
            }
            a = "update";
            b = true;
        }
        if (a.equals("update")) {
            this.a(v, b);
            this.c.t();
        }
    }
    
    public void b(final v v) {
        this.c.w();
        this.d.clear();
        final Enumeration a = v.a("FN").a(0).a();
        while (a.hasMoreElements()) {
            final String s = a.nextElement();
            String a2 = v.a("FN", 0, s, 0, 0);
            if (a2 == null) {
                a2 = s;
            }
            this.d.put(s, a2);
        }
        final String a3 = v.a("Su", 0, "I", 0, 0);
        if (a3 != null) {
            this.c.a(!this.c.a().equals(a3));
        }
        final String a4 = v.a("Su", 0, "M", 0, 0);
        final int[] array = { 0 };
        if (a4 != null && this.a(a4, "M", array)) {
            this.c.a(array[0]);
        }
        final String a5 = v.a("Su", 0, "N", 0, 0);
        if (a5 != null && this.a(a5, "N", array)) {
            this.c.c().a(new Integer(array[0]));
        }
        final String a6 = v.a("Su", 0, "D", 0, 0);
        if (a6 != null && this.a(a6, "D", array)) {
            this.c.c().af = array[0];
        }
        final String a7 = v.a("Su", 0, "G", 0, 0);
        if (a7 != null && this.a(a7, "G", array)) {
            this.c.c().ae = array[0];
        }
        final boolean[] array2 = { false };
        final String a8 = v.a("Su", 0, "H", 0, 0);
        if (a8 != null && this.a(a8, "H", array2)) {
            this.c.c().ab = array2[0];
        }
        final String a9 = v.a("Su", 0, "P", 0, 0);
        if (a9 != null && this.a(a9, "P", array2)) {
            this.c.c().ac = array2[0];
        }
        String a10 = v.a("Su", 0, "F", 0, 0);
        if (a10 == null) {
            a10 = "";
        }
        String a11 = v.a("Su", 0, "F", 0, 2);
        if (a11 == null) {
            a11 = "";
        }
        String a12 = v.a("Su", 0, "F", 0, 1);
        if (a12 == null) {
            a12 = "";
        }
        final Font[] array3 = { null };
        if (this.a(a10 + "," + a11 + "," + a12, "F", array3)) {
            this.c.c().s = array3[0];
        }
        final String a13 = v.a("Su", 0, "R", 0, 0);
        if (a13 != null && this.a(a13, "R", array2)) {
            this.c.c().z = array2[0];
        }
        final String[] array4 = { "ZZ", "Z" };
        final URL[] array5 = { null };
        for (int i = 0; i < 2; ++i) {
            final String a14 = v.a("Su", 0, array4[i], 0, 0);
            if (a14 != null && this.a(a14, array4[i], array5)) {
                if (i == 0) {
                    this.c.a(array5[0]);
                }
                else {
                    this.c.b(array5[0]);
                }
            }
        }
        final String a15 = v.a("Su", 0, "L", 0, 0);
        if (a15 != null) {
            this.c.b(a15);
        }
        final String a16 = v.a("Su", 0, "X", 0, 0);
        if (a16 != null) {
            this.c.c().a = a16;
        }
        for (int j = 0; j < this.c.f(); ++j) {
            this.a(j, v);
        }
        final String a17 = v.a("Co", 0, "S", 0, 0);
        final int[] array6 = { 0 };
        if (a17 != null && this.a(a17, "S", array6)) {
            this.c.f.a(array6[0]);
        }
        final int[] array7 = { 0, 1, 2 };
        final String[] array8 = { "N", "M", "P" };
        final Color[] array9 = { null };
        for (int k = 0; k < array7.length; ++k) {
            final String a18 = v.a("Co", 0, array8[k], 0, 0);
            if (a18 != null && this.a(a18, array8[k], array9)) {
                this.c.f.a(array7[k], array9[0]);
            }
        }
        String a19 = v.a("Co", 0, "L", 0, 0);
        if (a19 != null) {
            this.b.d();
            final Vector a20 = f.a(a19, ",");
            for (int l = 0; l < a20.size(); ++l) {
                try {
                    a19 = (String)this.d.get(a20.elementAt(l));
                    if (a19 == null) {
                        throw new Exception("no mapping");
                    }
                    final y c = this.c.c(a19);
                    if (c == null) {
                        throw new Exception("undefined");
                    }
                    this.c.f.a(c);
                }
                catch (Exception ex) {
                    this.b.c().a((Component)this.b.s, 7, new Object[] { a19, "Co", "0", "L", "0", "0", ex.getMessage() });
                }
            }
        }
        String a21 = v.a("Co", 0, "F", 0, 0);
        if (a21 != null) {
            final String s2 = this.d.get(a21);
            if (s2 != null) {
                a21 = s2;
            }
            final y c2 = this.c.c(a21);
            if (c2 != null) {
                this.c.f.a(c2);
                this.c.f.b(c2);
            }
            else {
                this.b.c().a((Component)this.b.s, 7, new Object[] { a21, "Co", "0", "F", "0", "0", "undefined" });
            }
        }
        final String a22 = v.a("Co", 0, "E", 0, 0);
        if (a22 != null) {
            final String s3 = "b";
            final String s4 = "a";
            final String s5 = "f";
            final String lowerCase = a22.toLowerCase();
            try {
                if (lowerCase.equals(s3)) {
                    this.c.f.a(new Integer(0));
                }
                else if (lowerCase.equals(s4)) {
                    this.c.f.a(new Integer(1));
                }
                else {
                    if (!lowerCase.equals(s5)) {
                        throw new ParseException(lowerCase, 0);
                    }
                    this.c.f.a(new Integer(2));
                }
                this.c.f.b(new Integer(this.c.f.j()));
            }
            catch (ParseException ex2) {
                this.b.c().a((Component)this.b.s, 7, new Object[] { lowerCase, "Co", "0", "E", "0", "0", ex2.getMessage() });
            }
        }
        this.c(v);
    }
    
    private void c(final v v) {
        final v a = v.a("Co").a(0).a("R");
        for (int i = 0; i < a.b(); ++i) {
            String a2 = v.a("Co", 0, "R", i, 0);
            if (a2 != null) {
                final String s = this.d.get(a2);
                if (s != null) {
                    a2 = s;
                }
                final y c = this.c.c(a2);
                if (c != null) {
                    final l l = new l(this.c.f);
                    final int[] array = { -1, 2, 1, 0 };
                    final Color[] array2 = { null };
                    for (int j = 1; j < array.length; ++j) {
                        final String a3 = v.a("Co", 0, "R", i, j);
                        if (a3 != null && this.a(a3, "R" + j, array2)) {
                            l.a(array[j], array2[0]);
                        }
                    }
                    final String a4 = v.a("Co", 0, "R", i, 4);
                    final int[] array3 = { 0 };
                    if (a4 != null && this.a(a4, "R4", array3)) {
                        l.a(array3[0]);
                    }
                    final String a5 = v.a("Co", 0, "R", i, 5);
                    if (a5 != null) {
                        if (a5.equalsIgnoreCase("m")) {
                            l.a(Boolean.FALSE);
                        }
                        else if (a5.equalsIgnoreCase("e")) {
                            l.a(Boolean.TRUE);
                        }
                        else {
                            this.b.c().a((Component)this.b.s, 7, new Object[] { a5, "Co", "0", "R", "" + i, "5", "not m or e" });
                        }
                    }
                    final String a6 = v.a("Co", 0, "R", i, 6);
                    final Double[] array4 = { null };
                    if (a6 != null && this.a(a6, "R6", array4)) {
                        l.d(array4[0]);
                    }
                    final String a7 = v.a("Co", 0, "R", i, 7);
                    if (a7 != null && this.a(a7, "R7", array4)) {
                        l.c(array4[0]);
                    }
                    final String a8 = v.a("Co", 0, "R", i, 8);
                    if (a8 != null && this.a(a8, "R8", array4)) {
                        l.e(array4[0]);
                    }
                    this.c.f.a(c, l);
                }
            }
        }
    }
    
    private void a(final int n, final v v) {
        final v a = v.a("C");
        for (int i = 0; i < a.b(); ++i) {
            final String b;
            if ((b = a.a(i).a((String)null).a(0).b(0)) != null) {
                Integer c;
                try {
                    c = this.b.d().c(b);
                }
                catch (Exception ex6) {
                    c = null;
                }
                if (c != null && c - 1 == n) {
                    final String a2;
                    if ((a2 = v.a("C", i, "T", 0, 0)) != null) {
                        try {
                            this.c.b(n).e(this.b.d().e(a2));
                        }
                        catch (Exception ex) {
                            this.b.c().a((Component)this.b.s, 7, new Object[] { a2, "C", "" + i, "T", "0", "0", ex.getMessage() });
                        }
                    }
                    final String a3;
                    if ((a3 = v.a("C", i, "U", 0, 0)) != null) {
                        try {
                            this.c.b(n).d(this.b.d().e(a3));
                        }
                        catch (Exception ex2) {
                            this.b.c().a((Component)this.b.s, 7, new Object[] { a3, "C", "" + i, "U", "0", "0", ex2.getMessage() });
                        }
                    }
                    final String a4;
                    if ((a4 = v.a("C", i, "L", 0, 0)) != null) {
                        this.c.b(n).b(a4);
                    }
                    String a5;
                    if ((a5 = v.a("C", i, "W", 0, 0)) != null) {
                        try {
                            final Integer c2 = this.b.d().c(a5);
                            if (c2 > 0) {
                                this.c.b(n).c(c2);
                            }
                            else {
                                this.b.c().a((Component)this.b.s, 7, new Object[] { a5, "C", "" + i, "W", "0", "0", "nonpositive" });
                            }
                        }
                        catch (Exception ex3) {
                            this.b.c().a((Component)this.b.s, 7, new Object[] { a5, "C", "" + i, "W", "0", "0", ex3.getMessage() });
                        }
                    }
                    final v a6 = v.a("C").a(i).a("I");
                    for (int j = 0; j < a6.b(); ++j) {
                        final String a7;
                        if ((a7 = v.a("C", i, "I", j, 0)) != null) {
                            a5 = (String)this.d.get(a7);
                        }
                        if (a5 != null) {
                            this.c.b(n).a(new y(this.b, a5, v.a("FN", 0, a7, 0, 1), new z(this.b, v.a("C", i, "I", j, 1), v.a("C", i, "I", j, 2), this.c.c().a), v.a("C", i, "I", j, 3)));
                        }
                        else {
                            this.b.c().a((Component)this.b.s, 7, new Object[] { a7, "C", "" + i, "I", "" + j, "0", "empty" });
                        }
                    }
                    final String a8;
                    if ((a8 = v.a("C", i, "S", 0, 0)) != null) {
                        this.b.d();
                        final Vector a9 = f.a(a8, ",");
                        for (int k = 0; k < a9.size(); ++k) {
                            final String s;
                            if ((s = this.d.get(a9.elementAt(k))) != null) {
                                final y c3;
                                if ((c3 = this.c.b(n).c(s)) != null) {
                                    this.c.b(n).f.e(c3);
                                }
                                else {
                                    this.b.c().a((Component)this.b.s, 7, new Object[] { s, "C", "" + i, "S", "0", "0", "undefined" });
                                }
                            }
                            else {
                                this.b.c().a((Component)this.b.s, 7, new Object[] { a9.elementAt(k), "C", "" + i, "S", "0", "0", "no mapping" });
                            }
                        }
                    }
                    String a10;
                    if ((a10 = v.a("C", i, "E", 0, 0)) != null) {
                        final String s2;
                        if ((s2 = this.d.get(a10)) != null) {
                            a10 = s2;
                        }
                        final y c4;
                        if ((c4 = this.c.b(n).c(a10)) != null) {
                            this.c.b(n).f.e(c4);
                            this.c.b(n).f.f(c4);
                        }
                        else {
                            this.b.c().a((Component)this.b.s, 7, new Object[] { a10, "C", "" + i, "E", "0", "0", "undefined" });
                        }
                    }
                    final String a11;
                    if ((a11 = v.a("C", i, "E", 0, 1)) != null) {
                        try {
                            this.c.b(n).f.a((boolean)this.b.d().e(a11));
                        }
                        catch (Exception ex4) {
                            this.b.c().a((Component)this.b.s, 7, new Object[] { a11, "C", "" + i, "E", "0", "1", ex4.getMessage() });
                        }
                    }
                    final String a12;
                    if ((a12 = v.a("C", i, "G", 0, 0)) != null) {
                        this.b.d();
                        final Vector a13 = f.a(a12, ",");
                        for (int l = 0; l < a13.size(); ++l) {
                            final String s3;
                            if ((s3 = this.d.get(a13.elementAt(l))) != null) {
                                final y c5;
                                if ((c5 = this.c.b(n).c(s3)) != null) {
                                    this.c.b(n).f.c(c5);
                                }
                                else {
                                    this.b.c().a((Component)this.b.s, 7, new Object[] { s3, "C", "" + i, "G", "0", "0", "undefined" });
                                }
                            }
                            else {
                                this.b.c().a((Component)this.b.s, 7, new Object[] { a13.elementAt(l), "C", "0", "G", "0", "0", "no mapping" });
                            }
                        }
                    }
                    String a14;
                    if ((a14 = v.a("C", i, "D", 0, 0)) != null) {
                        final String s4;
                        if ((s4 = this.d.get(a14)) != null) {
                            a14 = s4;
                        }
                        final y c6;
                        if ((c6 = this.c.b(n).c(a14)) != null) {
                            this.c.b(n).f.c(c6);
                            this.c.b(n).f.d(c6);
                        }
                        else {
                            this.b.c().a((Component)this.b.s, 7, new Object[] { a14, "C", "" + i, "D", "0", "0", "undefined" });
                        }
                    }
                    final v a15 = v.a("C").a(i).a("C");
                    for (int n2 = 0; n2 < a15.b(); ++n2) {
                        String a16;
                        if ((a16 = v.a("C", i, "C", n2, 1)) == null) {
                            a16 = "";
                        }
                        String s5 = a16 + ",";
                        final String a17;
                        if ((a17 = v.a("C", i, "C", n2, 3)) != null) {
                            s5 += a17;
                        }
                        String s6 = s5 + ",";
                        final String a18;
                        if ((a18 = v.a("C", i, "C", n2, 2)) != null) {
                            s6 += a18;
                        }
                        Font g;
                        try {
                            g = this.b.d().g(s6);
                        }
                        catch (ParseException ex5) {
                            this.b.c().a((Component)this.b.s, 7, new Object[] { s6, "C", "" + i, "C", "" + n2, "1,3,2", ex5.getMessage() });
                            g = null;
                        }
                        this.c.b(n).a(v.a("C", i, "C", n2, 0), g);
                    }
                    break;
                }
            }
        }
        final v a19 = v.a("P");
        for (int n3 = 0; n3 < a19.b(); ++n3) {
            final String b2;
            if ((b2 = a19.a(n3).a((String)null).a(0).b(0)) != null) {
                Integer c7;
                try {
                    c7 = this.b.d().c(b2);
                }
                catch (Exception ex7) {
                    c7 = null;
                }
                if (c7 != null && c7 - 1 == n) {
                    final String a20;
                    if ((a20 = v.a("P", n3, "H", 0, 0)) != null) {
                        Integer c8 = null;
                        final String a21;
                        if ((a21 = v.a("P", n3, "H", 0, 2)) != null) {
                            try {
                                c8 = this.b.d().c(a21);
                                if (c8 < 0) {
                                    c8 = null;
                                }
                            }
                            catch (Exception ex8) {}
                        }
                        if (c8 == null) {
                            c8 = new Integer(0);
                        }
                        this.c.b(n).a(a20, c8);
                    }
                    final v a22 = v.a("P").a(n3).a("L");
                    for (int n4 = 0; n4 < a22.b(); ++n4) {
                        final String a23 = v.a("P", n3, "L", n4, 0);
                        Integer c9 = null;
                        final String a24;
                        if ((a24 = v.a("P", n3, "L", n4, 2)) != null) {
                            try {
                                c9 = this.b.d().c(a24);
                                if (c9 < 0) {
                                    c9 = null;
                                }
                            }
                            catch (Exception ex9) {}
                        }
                        if (c9 == null) {
                            c9 = new Integer(n4 + 1);
                        }
                        this.c.b(n).b(a23, c9);
                    }
                    break;
                }
            }
        }
        final v a25 = v.a("L");
        for (int n5 = 0; n5 < a25.b(); ++n5) {
            final String b3;
            if ((b3 = a25.a(n5).a((String)null).a(0).b(0)) != null) {
                Integer c10;
                try {
                    c10 = this.b.d().c(b3);
                }
                catch (Exception ex10) {
                    c10 = null;
                }
                if (c10 != null && c10 - 1 == n) {
                    final String a26;
                    if ((a26 = v.a("L", n5, "C", 0, 0)) != null) {
                        this.c.b(n).a(v.a("L", n5, "L", 0, 0), a26, v.a("L", n5, "T", 0, 0));
                        break;
                    }
                    break;
                }
            }
        }
        final v a27 = v.a("M");
        for (int n6 = 0; n6 < a27.b(); ++n6) {
            final String b4;
            if ((b4 = a27.a(n6).a((String)null).a(0).b(0)) != null) {
                Integer c11;
                try {
                    c11 = this.b.d().c(b4);
                }
                catch (Exception ex11) {
                    c11 = null;
                }
                if (c11 != null && c11 - 1 == n) {
                    this.c.b(n).b(v.a("M", n6, "L", 0, 0), v.a("M", n6, "A", 0, 0), v.a("M", n6, "T", 0, 0));
                }
            }
        }
    }
    
    public void a(final v v, final boolean b) {
        this.c.a(v.a("Su", 0, "S", 0, 0));
        if (this.c.d()) {
            final v a = v.a("R");
            for (int i = 0; i < a.b(); ++i) {
                final String a2;
                if ((a2 = v.a("R", i, null, 0, 0)) != null) {
                    Integer c;
                    try {
                        c = this.b.d().c(a2);
                    }
                    catch (Exception ex) {
                        c = null;
                    }
                    if (c != null && 0 < c) {
                        if (c <= this.c.f()) {
                            final int n = c - 1;
                            final s b2 = this.c.b(n);
                            final String a3;
                            if ((a3 = v.a("R", i, null, 0, 1)) != null) {
                                if (this.a(n, a3)) {
                                    if (b) {
                                        b2.d(a3);
                                    }
                                    final aa e;
                                    if ((e = b2.e(a3)) != null) {
                                        final Enumeration a4 = v.a("R").a(i).a();
                                        while (a4.hasMoreElements()) {
                                            final String s = a4.nextElement();
                                            final String s2;
                                            if ((s2 = this.d.get(s)) == null) {
                                                continue;
                                            }
                                            final y c2;
                                            if ((c2 = b2.c(s2)) == null) {
                                                continue;
                                            }
                                            if (c2.b()) {
                                                continue;
                                            }
                                            e.a(s2, c2.h.a(v.a("R", i, s, 0, 0)));
                                        }
                                        for (int j = 0; j < b2.h(); ++j) {
                                            final y d = b2.d(j);
                                            if (d.b()) {
                                                e.a(d.f, d.h.a(d.a(e)));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private boolean a(final int n, final String s) {
        boolean contains = true;
        if (this.e != null && n < this.e.size()) {
            final Vector vector = this.e.elementAt(n);
            if (vector != null && vector.size() > 0) {
                contains = vector.contains(s);
            }
        }
        return contains;
    }
    
    public void a() {
        if (this.e != null) {
            for (int i = 0; i < this.e.size(); ++i) {
                final Object element = this.e.elementAt(i);
                if (element instanceof Vector) {
                    ((Vector)element).removeAllElements();
                }
            }
            this.e.removeAllElements();
            this.e = null;
        }
        this.c = null;
        if (this.d != null) {
            this.d.clear();
            this.d = null;
        }
        this.b = null;
    }
    
    static {
        a = new a2[] { new Object("Su", "N", 1, "NumCols") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Su", "D", 0, "SubmapSeparator") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Su", "G", 0, "GroupSeparator") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Su", "H", 2, "ShowLabels") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Su", "P", 2, "PackOption") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Su", "F", 100, "LabelFont") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Su", "R", 2, "ShowGridLines") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Su", "ZZ", 3, "HelpURL") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Su", "Z", 3, "AboutURL") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Su", "L", 4, "Title") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Su", "X", 4, "UndefinedString") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Co", "N", 6, "NegColor") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Co", "M", 6, "MidColor") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Co", "P", 6, "PosColor") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("Co", "F", 5, "DefaultColorField") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("C", "D", 5, "DefaultGroupField") {
                public String a;
                public String b;
                public int c;
                public String d;
                
                {
                    this.a = a;
                    this.b = b;
                    this.c = c;
                    this.d = d;
                }
            }, new Object("C", "W", 1, "CellWidth") {
                public String a = a;
                public String b = b;
                public int c = c;
                public String d = d;
            } };
    }
}
