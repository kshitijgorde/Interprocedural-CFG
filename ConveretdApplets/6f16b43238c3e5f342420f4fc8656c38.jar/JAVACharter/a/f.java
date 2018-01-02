// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.a;

import java.util.Enumeration;
import JAVACharter.b.h;
import java.util.Date;
import java.net.URL;
import java.util.Hashtable;
import JAVACharter.StyleManage.StyleCache;
import JAVACharter.c.e;
import java.util.Vector;
import JAVACharter.b.b;
import JAVACharter.b.d;

public class f
{
    private d[] n;
    private d[] g;
    private d[] goto;
    private b void;
    private Vector for;
    private e[] e;
    private int int;
    private int o;
    private int f;
    private int new;
    private boolean if;
    private long p;
    private float null;
    private static final int d = 0;
    private static final int do = 1;
    private static final int char = 0;
    private static final int try = 1;
    private static final int r = 2;
    public String[] i;
    private String[] else;
    private Vector case;
    private boolean m;
    private boolean j;
    private boolean byte;
    private c l;
    private g b;
    private JAVACharter.a.b q;
    private StyleCache c;
    private a h;
    private Hashtable long;
    private String k;
    int a;
    
    public f(final URL url, final e[] e) {
        this.void = null;
        this.for = new Vector();
        this.int = 0;
        this.o = 0;
        this.f = 1;
        this.new = 10;
        this.if = true;
        this.p = Long.MAX_VALUE;
        this.null = 9.223372E18f;
        this.case = new Vector();
        this.m = true;
        this.j = false;
        this.byte = false;
        this.long = new Hashtable();
        this.k = null;
        this.a = 0;
        this.e = e;
        this.n = new d[5];
        this.g = new d[4];
        this.n[0] = new d(1);
        this.n[1] = new d(2);
        this.n[2] = new d(3);
        this.n[3] = new d(4);
        this.n[4] = new d(5);
        this.g[0] = new d(9);
        this.g[1] = new d(6);
        this.g[2] = new d(7);
        this.g[3] = new d(8);
        (this.l = new c(this.n, this.g)).a(true);
        this.goto = this.l.a();
        this.b = new g(this, this.l, url);
        this.q = new JAVACharter.a.b(this, this.l, url);
        this.else = new String[1];
        this.i = new String[1];
        this.h = new a();
    }
    
    public void a(final StyleCache c) {
        this.c = c;
    }
    
    public String void() {
        return this.c.long();
    }
    
    public String long() {
        return this.c.void();
    }
    
    public String k() {
        return this.c.j();
    }
    
    public boolean try() {
        return this.l.for();
    }
    
    public int f() {
        return this.new;
    }
    
    public int goto() {
        return this.f;
    }
    
    public c case() {
        return this.l;
    }
    
    public boolean if(final int f) {
        this.f = f;
        boolean b = false;
        switch (this.f) {
            case 6:
            case 7:
            case 8:
            case 9: {
                if (!this.l.if()) {
                    b = true;
                }
                this.l.a(false);
                this.l.do(false);
                break;
            }
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                if (this.l.if()) {
                    b = true;
                }
                this.l.a(true);
                this.l.do(true);
                break;
            }
        }
        return b;
    }
    
    public boolean a(final String s, final String s2, final int n) {
        return this.j = this.q.a(this.goto, s, s2, this.f);
    }
    
    public String do(final String s) {
        return this.q.for(s);
    }
    
    public boolean h() {
        boolean b = true;
        for (int i = 0; i < this.else.length; ++i) {
            if (this.else[0].equals("")) {
                return false;
            }
            if (this.else[i] != "0") {
                this.j = this.b.a(this.goto, this.else[i], this.f);
                if (this.j) {
                    this.case.addElement(this.else[i]);
                    if (this.else.length == 1) {
                        this.else[0] = this.if();
                    }
                    else {
                        final String[] else1 = new String[this.else.length - 1];
                        int j = 0;
                        int n = 0;
                        while (j < this.else.length) {
                            if (j != i) {
                                else1[n] = this.else[j];
                                ++n;
                            }
                            ++j;
                        }
                        this.else = else1;
                    }
                    b = false;
                }
            }
        }
        for (int k = 0; k < this.i.length; ++k) {
            if (this.i[k] != null) {
                this.j = this.b.if(this.goto, this.i[k], this.f);
                if (this.j) {
                    int n2 = this.i.length - 1;
                    if (n2 == 0) {
                        n2 = 1;
                    }
                    final String[] l = new String[n2];
                    this.new(this.i[k]);
                    this.case.addElement(this.i[k]);
                    if (this.i.length == 1) {
                        this.i[0] = this.if();
                    }
                    else {
                        int n3 = 0;
                        int n4 = 0;
                        while (n3 < this.i.length) {
                            if (n3 != k) {
                                l[n4] = this.i[n3];
                                ++n4;
                            }
                            ++n3;
                        }
                        this.i = l;
                    }
                    b = false;
                }
                else if (null == this.else[0] || "0" == this.else[0]) {
                    this.byte(this.case(this.i[k]));
                }
                else {
                    this.try(this.case(this.i[k]));
                }
            }
        }
        this.i = new String[1];
        return b;
    }
    
    public Vector m() {
        return this.case;
    }
    
    public void g() {
        if (this.case != null) {
            this.case.removeAllElements();
        }
    }
    
    public void if(final String s) {
        if (this.case != null) {
            for (int i = 0; i < this.case.size(); ++i) {
                if (((String)this.case.elementAt(i)).equalsIgnoreCase(s)) {
                    this.case.removeElementAt(i);
                }
            }
        }
    }
    
    public void a(final int int1) {
        if (int1 > this.int) {
            this.int = int1;
        }
    }
    
    public int b() {
        return this.int;
    }
    
    public void a() {
        if (this.new < 15) {
            ++this.new;
        }
        this.do(this.new);
    }
    
    public void char() {
        if (this.new > 1) {
            --this.new;
        }
        this.do(this.new);
    }
    
    public void a(final String s) {
        this.goto = this.l.a();
        int n = -1;
        for (int i = 0; i < this.goto.length; ++i) {
            if (this.goto[i].if() == this.f) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            n = 0;
        }
        this.void = this.goto[n].for(s);
    }
    
    public void i() {
        this.void = null;
    }
    
    public String if() {
        this.goto = this.l.a();
        int n = -1;
        for (int i = 0; i < this.goto.length; ++i) {
            if (this.goto[i].if() == this.f) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            n = 0;
        }
        return this.goto[n].a();
    }
    
    public void new(final String s) {
        this.goto = this.l.a();
        int n = -1;
        for (int i = 0; i < this.goto.length; ++i) {
            if (this.goto[i].if() == this.f) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            n = 0;
        }
        this.goto[n].if(s);
    }
    
    public JAVACharter.b.e else() {
        return this.void.for();
    }
    
    public JAVACharter.b.e goto(final String s) {
        return this.void.a(s);
    }
    
    public Date j() {
        return this.l.int();
    }
    
    public Date e() {
        return this.l.case();
    }
    
    public void if(final Date date, final Date date2) {
        if (null != date) {
            this.l.for(date);
        }
        if (null != date2) {
            this.l.do(date2);
        }
    }
    
    public void for() {
        this.do(this.new = 10);
    }
    
    public void a(final Date date, final Date date2) {
        this.l.a(false);
        this.l.a(JAVACharter.util.b.try(date2, 1));
        this.l.if(date2);
        this.l.a(true);
        this.l.a(date);
        this.l.if(date2);
        this.if(this.l.try(), this.l.char());
    }
    
    public int do() {
        for (int i = 0; i < this.goto.length; ++i) {
            if (this.goto[i].if() == this.f) {
                return i;
            }
        }
        return 0;
    }
    
    public void a(final int n, final int n2) {
        this.goto = this.l.a();
        this.a = this.do();
        final JAVACharter.b.f f = (JAVACharter.b.f)this.goto[this.a].for(this.d()).for();
        this.if(f.do(n), f.do(n2));
    }
    
    public boolean do(final int new1) {
        this.new = new1;
        final Date a = JAVACharter.util.b.a(false);
        boolean b = false;
        switch (new1) {
            case 1: {
                if (!this.l.byte()) {
                    b = true;
                }
                this.l.a(false);
                this.l.if(false);
                this.if(JAVACharter.util.b.goto(JAVACharter.util.b.if(a, 1)), a);
                break;
            }
            case 2: {
                if (!this.l.byte()) {
                    b = true;
                }
                this.l.a(false);
                this.l.if(false);
                this.if(JAVACharter.util.b.goto(JAVACharter.util.b.if(a, 2)), a);
                break;
            }
            case 3: {
                if (!this.l.byte()) {
                    b = true;
                }
                this.l.a(false);
                this.l.if(false);
                this.if(JAVACharter.util.b.goto(JAVACharter.util.b.if(a, 5)), a);
                break;
            }
            case 4: {
                if (!this.l.byte()) {
                    b = true;
                }
                this.l.a(false);
                this.l.if(false);
                this.if(JAVACharter.util.b.goto(JAVACharter.util.b.if(a, 10)), a);
                break;
            }
            case 5: {
                if (this.l.byte()) {
                    b = true;
                }
                this.l.a(true);
                this.l.if(true);
                this.if(JAVACharter.util.b.char(JAVACharter.util.b.a(this.l.char(), 1)), this.l.char());
                break;
            }
            case 6: {
                if (this.l.byte()) {
                    b = true;
                }
                this.l.a(true);
                this.l.if(true);
                this.if(JAVACharter.util.b.char(JAVACharter.util.b.a(this.l.char(), 2)), this.l.char());
                break;
            }
            case 7: {
                if (this.l.byte()) {
                    b = true;
                }
                this.l.a(true);
                this.l.if(true);
                this.if(JAVACharter.util.b.char(JAVACharter.util.b.a(this.l.char(), 3)), this.l.char());
                break;
            }
            case 8: {
                if (this.l.byte()) {
                    b = true;
                }
                this.l.a(true);
                this.l.if(true);
                this.if(JAVACharter.util.b.char(JAVACharter.util.b.a(this.l.char(), 6)), this.l.char());
                break;
            }
            case 9: {
                if (this.l.byte()) {
                    b = true;
                }
                this.l.a(true);
                this.l.if(true);
                this.if(JAVACharter.util.b.if(this.l.char()), this.l.char());
                break;
            }
            case 10: {
                if (this.l.byte()) {
                    b = true;
                }
                this.l.a(true);
                this.l.if(true);
                this.if(JAVACharter.util.b.char(JAVACharter.util.b.do(this.l.char(), 1)), this.l.char());
                break;
            }
            case 11: {
                if (this.l.byte()) {
                    b = true;
                }
                this.l.a(true);
                this.l.if(true);
                this.if(JAVACharter.util.b.char(JAVACharter.util.b.do(this.l.char(), 2)), this.l.char());
                break;
            }
            case 12: {
                if (this.l.byte()) {
                    b = true;
                }
                this.l.a(true);
                this.l.if(true);
                this.if(JAVACharter.util.b.char(JAVACharter.util.b.do(this.l.char(), 3)), this.l.char());
                break;
            }
            case 13: {
                if (this.l.byte()) {
                    b = true;
                }
                this.l.a(true);
                this.l.if(true);
                this.if(JAVACharter.util.b.char(JAVACharter.util.b.do(this.l.char(), 4)), this.l.char());
                break;
            }
            case 14: {
                if (this.l.byte()) {
                    b = true;
                }
                this.l.a(true);
                this.l.if(true);
                this.if(JAVACharter.util.b.char(JAVACharter.util.b.do(this.l.char(), 5)), this.l.char());
                break;
            }
            case 15: {
                if (this.l.byte()) {
                    b = true;
                }
                this.l.a(true);
                this.l.if(true);
                this.if(JAVACharter.util.b.char(JAVACharter.util.b.do(this.l.char(), 10)), this.l.char());
                break;
            }
        }
        return b;
    }
    
    public float a(final Vector vector) {
        float n = -9.9999998E12f;
        for (int i = 0; i < vector.size(); ++i) {
            final float floatValue = Float.valueOf(vector.elementAt(i));
            if (floatValue > n && floatValue != this.null) {
                n = floatValue;
            }
        }
        if (n == -9.9999998E12f) {
            return this.null;
        }
        return n;
    }
    
    public float if(final Vector vector) {
        float n = 9.9999998E12f;
        for (int i = 0; i < vector.size(); ++i) {
            final float floatValue = Float.valueOf(vector.elementAt(i));
            if (floatValue < n && floatValue != this.null) {
                n = floatValue;
            }
        }
        if (n == 9.9999998E12f) {
            return this.null;
        }
        return n;
    }
    
    public float a(final float[] array) {
        float n = -9.9999998E12f;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] > n && array[i] != this.null) {
                n = array[i];
            }
        }
        if (n == -9.9999998E12f) {
            return this.null;
        }
        return n;
    }
    
    public float if(final float[] array) {
        float n = 9.9999998E12f;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < n && array[i] != this.null) {
                n = array[i];
            }
        }
        if (n == 9.9999998E12f) {
            return this.null;
        }
        return n;
    }
    
    public float if(final long[] array) {
        float n = -9.9999998E12f;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] > n && array[i] != this.null) {
                n = array[i];
            }
        }
        if (n == -9.9999998E12f) {
            return this.null;
        }
        return n;
    }
    
    public float a(final long[] array) {
        float n = 9.9999998E12f;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < n && array[i] != this.null) {
                n = array[i];
            }
        }
        if (n == 9.9999998E12f) {
            return this.null;
        }
        return n;
    }
    
    public float if(final float[][] array) {
        float n = -9.9999998E12f;
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++j) {
                if (array[i][j] > n && array[i][j] != this.null) {
                    n = array[i][j];
                }
            }
        }
        if (n == -9.9999998E12f) {
            return this.null;
        }
        return n;
    }
    
    public float a(final float[][] array) {
        float n = 9.9999998E12f;
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array[i].length; ++j) {
                if (array[i][j] < n && array[i][j] != this.null) {
                    n = array[i][j];
                }
            }
        }
        if (n == 9.9999998E12f) {
            return this.null;
        }
        return n;
    }
    
    public String char(final String s) {
        return this.b.a(s);
    }
    
    public String case(final String s) {
        return this.b.if(s);
    }
    
    public String new() {
        return this.char(this.else[0]);
    }
    
    public void for(final String s) {
        final String case1 = this.case(s.toUpperCase());
        if (case1 != "0") {
            this.byte(case1);
        }
        else {
            this.byte("0");
            if (null == this.i) {
                this.i = new String[1];
            }
            this.i[0] = s.toUpperCase();
        }
    }
    
    public void if(final String s, final String k) {
        this.for(s);
        if (this.k == null) {
            this.k = new String();
        }
        this.k = k;
    }
    
    public String null() {
        if (this.k == null) {
            return this.new();
        }
        return this.k;
    }
    
    public void null(final String s) {
        boolean b = false;
        final String case1 = this.case(s);
        if (case1 != "0") {
            for (int i = 0; i < this.else.length; ++i) {
                if (this.else[i].equalsIgnoreCase(case1)) {
                    b = true;
                }
            }
            if (!b) {
                this.try(case1);
            }
        }
        else {
            final int length = this.i.length;
            if (length > 0) {
                if (null != this.i[length - 1] && "" != this.i[length - 1]) {
                    final String[] j = new String[length + 1];
                    System.arraycopy(this.i, 0, j, 0, length);
                    j[length] = s.toUpperCase();
                    this.i = j;
                }
                else {
                    this.i[length - 1] = s.toUpperCase();
                }
            }
            else {
                (this.i = new String[1])[0] = s.toUpperCase();
            }
        }
    }
    
    public String d() {
        return this.else[0];
    }
    
    public void byte(final String s) {
        this.else[0] = s;
    }
    
    public void do(final String s, final String k) {
        this.byte(s);
        if (this.k == null) {
            this.k = new String();
        }
        this.k = k;
    }
    
    public String[] l() {
        return this.else.clone();
    }
    
    public void c() {
        this.else = new String[1];
    }
    
    public int int() {
        return this.else.length;
    }
    
    public void try(final String s) {
        final int length = this.else.length;
        if (length > 0) {
            if (null != this.else[length - 1] && "0" != this.else[length - 1]) {
                final String[] else1 = new String[length + 1];
                System.arraycopy(this.else, 0, else1, 0, length);
                else1[length] = s.toUpperCase();
                this.else = else1;
            }
            else {
                this.else[length - 1] = s.toUpperCase();
            }
        }
        else {
            (this.else = new String[1])[0] = s.toUpperCase();
        }
    }
    
    public void else(final String s) {
        if (this.else.length == 1) {
            this.else[0] = "0";
        }
        else {
            int n = -1;
            for (int i = 0; i < this.else.length; ++i) {
                if (this.else[i].equalsIgnoreCase(s)) {
                    n = i;
                }
            }
            if (n != -1) {
                final String[] else1 = new String[this.else.length - 1];
                int j = 0;
                int n2 = 0;
                while (j < this.else.length) {
                    if (j != n) {
                        else1[n2] = this.else[j];
                        ++n2;
                    }
                    ++j;
                }
                this.else = else1;
            }
        }
    }
    
    public void int(final String s) {
        int n = 1;
        for (int i = 0; i < 4; ++i) {
            final b for1 = this.g[i].for(s);
            if (for1 != null) {
                final JAVACharter.b.f f = (JAVACharter.b.f)for1.a("date");
                final h h = (h)for1.a("open");
                final h h2 = (h)for1.a("high");
                final h h3 = (h)for1.a("low");
                final h h4 = (h)for1.a("close");
                while (n != 0 && f.for() > 1) {
                    if (h.int(h.for() - 1) == this.null && h2.int(h2.for() - 1) == this.null && h3.int(h3.for() - 1) == this.null && h4.int(h4.for() - 1) == this.null) {
                        final Enumeration a = for1.a();
                        while (a.hasMoreElements()) {
                            for1.a(a.nextElement()).do();
                        }
                    }
                    else {
                        n = 0;
                    }
                }
                n = 1;
            }
        }
    }
    
    public a byte() {
        return this.h;
    }
    
    public void a(final String s, final String s2) {
        if (!this.long.containsKey(s)) {
            this.long.put(s, s2);
        }
    }
    
    public String long(final String s) {
        final String s2 = this.long.get(s);
        if (s2 != null) {
            return s2;
        }
        return this.char(s);
    }
}
