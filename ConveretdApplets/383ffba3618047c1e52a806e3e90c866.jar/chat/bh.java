// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.Font;
import java.io.IOException;
import java.awt.Color;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.awt.Button;
import java.awt.Component;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.awt.Event;
import java.util.Hashtable;
import java.io.DataInput;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.Vector;
import java.net.Socket;
import java.awt.Image;

public abstract class bh extends aj implements Runnable
{
    protected static final String[] a;
    public static Image b;
    public static Image c;
    public static Image d;
    public static Image e;
    public static Image f;
    public static Image g;
    public static Image[] a;
    public static Image h;
    public boolean d;
    public boolean e;
    public boolean f;
    private int F;
    private long e;
    public int i;
    private long f;
    private boolean o;
    private boolean p;
    public boolean g;
    protected aU a;
    protected aS a;
    protected String b;
    public String d;
    protected ax a;
    protected Thread a;
    protected Socket a;
    public ar a;
    protected W a;
    public W b;
    public W c;
    public W d;
    public W e;
    public W f;
    public W g;
    public W h;
    private W v;
    public W i;
    public W j;
    public W k;
    public W l;
    public W m;
    public W n;
    public W o;
    public W p;
    public W q;
    public W r;
    public W s;
    public W t;
    public Vector a;
    public Vector b;
    public long b;
    public long c;
    private long g;
    protected URL a;
    protected boolean h;
    protected boolean i;
    public MediaTracker a;
    protected boolean j;
    protected G a;
    protected aV a;
    private U a;
    protected int j;
    protected int k;
    protected int l;
    private String h;
    public boolean k;
    public boolean l;
    private MenuItem b;
    private MenuItem c;
    public String e;
    public String f;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;
    public int A;
    public int B;
    public long d;
    public int C;
    public int D;
    public int E;
    public boolean m;
    public boolean n;
    public bm a;
    protected String g;
    public W u;
    private CheckboxMenuItem[] a;
    private MenuItem d;
    public MenuItem a;
    
    public final boolean a() {
        this.a.setSoTimeout(10000);
        m m;
        if ((m = new m(this.a)).a != 71427) {
            m = new m(this.a);
        }
        if (m.a != 71427) {
            return false;
        }
        final m i = m;
        if (0 >= (i.c >> 16) - 1) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + 0);
        }
        this.f = (i.a(0, 0) << 32) + (i.a(0, 1) & 0xFFFFFFFFL);
        this.a.setSoTimeout(0);
        final m j = new m(71427, 1);
        final long n = 504403159339237376L;
        final m k = j;
        if (0 >= (k.c >> 16) - 1) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + 0);
        }
        k.a(0, 0, (int)(n >>> 32));
        k.a(0, 1, (int)n);
        this.m(j);
        this.g = false;
        return true;
    }
    
    public final String a(String s) {
        if (s != null && this.m) {
            try {
                synchronized (this.h) {
                    for (int i = 0; i < this.h.a(); ++i) {
                        s = ((at)this.h.a(i)).a(s);
                    }
                }
            }
            finally {
                throw loadexception(java.lang.Throwable.class);
            }
            try {
                final bh bh;
                synchronized (bh.v) {
                    for (int j = 0; j < bh.v.a(); ++j) {
                        s = ((at)bh.v.a(j)).a(s);
                    }
                }
            }
            finally {
                throw loadexception(java.lang.Throwable.class);
            }
        }
        return s;
    }
    
    public final Object[] a(String a, final int n) {
        Vector<Hashtable<String, String>> vector = null;
        if (a != null && this.m) {
            vector = new Vector<Hashtable<String, String>>();
            try {
                synchronized (this.h) {
                    int int1 = 0;
                    for (int i = 0; i < this.h.a(); ++i) {
                        final at at;
                        if ((at = (at)this.h.a(i)) != null) {
                            if (int1 >= n) {
                                break;
                            }
                            final String s = a;
                            final String[][] a2;
                            a = (a2 = at.a(a, n, int1))[0][0];
                            int1 = Integer.parseInt(a2[0][1]);
                            if (!a.equals(s) && at.a != 0 && at.a != -16777216) {
                                final Hashtable<String, String> hashtable;
                                (hashtable = new Hashtable<String, String>()).put("ID", (String)new Integer(i));
                                hashtable.put("name", new String(at.a));
                                hashtable.put("color", (String)new Integer(at.a));
                                vector.addElement(hashtable);
                            }
                        }
                    }
                }
            }
            finally {
                throw loadexception(java.lang.Throwable.class);
            }
            try {
                final bh bh;
                synchronized (bh.v) {
                    for (int j = 0; j < bh.v.a(); ++j) {
                        a = ((at)bh.v.a(j)).a(a);
                    }
                }
            }
            finally {
                throw loadexception(java.lang.Throwable.class);
            }
        }
        return new Object[] { a, vector };
    }
    
    public abstract void a();
    
    public final void a(final String h, final String s, final String s2, final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.h = h;
        final m m;
        (m = new m(67330, 1)).e = -1;
        m.d = -1;
        m.f = super.g;
        m.a(0, 0, -999);
        m.a(0, h);
        m.a(1, s);
        m.a(0, 5, n);
        m.a(0, 6, n2);
        m.a(0, 7, n3);
        m.a(0, 8, n4);
        if (s2.length() > 0) {
            m.a(new ap(s2));
        }
        if (!b) {
            m.a(0, 58, true);
        }
        this.m(m);
    }
    
    public final void b() {
        new aF(this.a.a(), this).setVisible(true);
    }
    
    public abstract void a(final m p0);
    
    public final void b(final int n) {
        final ai ai;
        if ((ai = (ai)this.d.b(n)) != null) {
            this.a(ai);
            return;
        }
        System.err.println("no such room! " + n);
    }
    
    public final void a(final ai ai) {
        if (ai.b && ai.a != null) {
            new as(this.a.a(), this, ai).setVisible(true);
        }
        if (!ai.b || ai.a == null) {
            final m m;
            (m = new m(66049, 1)).a(0, 0, super.g);
            m.a(0, 1, ai.g);
            m.e = -1;
            m.d = -1;
            this.m(m);
        }
    }
    
    public final long a() {
        return this.e;
    }
    
    public final int b() {
        return this.i;
    }
    
    public final boolean b() {
        return this.o;
    }
    
    public final boolean c() {
        return this.p;
    }
    
    public final int a(final int n) {
        int n2 = 0;
        try {
            synchronized (this.c) {
                for (int i = 0; i < this.c.a(); ++i) {
                    final au au;
                    if ((au = (au)this.c.a(i)).b == n && (!au.a(23) || this.a(24)) && (this.a(52) || !au.a(18) || au.g == super.g)) {
                        ++n2;
                    }
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        return n2;
    }
    
    public boolean a(final Event event) {
        if (event.target == this.b) {
            this.c();
        }
        else if (this.n.a(event.target) != -1) {
            this.a((URL)this.o.a(this.n.a(event.target)), "_blank");
        }
        else if (event.target == this.c) {
            this.d(0);
        }
        if (event.target == this.d) {
            this.a(this.f);
        }
        if (event.target instanceof CheckboxMenuItem) {
            for (int i = 0; i < this.a.length; ++i) {
                if (event.target == this.a[i]) {
                    if (!this.a[i].getState()) {
                        this.a[i].setState(true);
                    }
                    else {
                        this.a[i].setState(true);
                        final int n = 0x1 | i << 4;
                        final m m;
                        (m = new m(67334, 1)).a(0, 0, super.g);
                        m.a(0, 1, this.m);
                        m.a(0, 5, n);
                        m.a(0, super.c);
                        m.e = -1;
                        m.d = -1;
                        this.m(m);
                    }
                }
                else {
                    this.a[i].setState(false);
                }
            }
        }
        return true;
    }
    
    public final void a(final URL a) {
        this.a = a;
    }
    
    public final void a(final String s) {
        final Object[] array = { "window.focus(); setTimeout(\"window.external.AddFavorite('" + s + "', window.document.title);\", 100);" };
        try {
            JSObject.getWindow((Applet)this.a).call("eval", array);
        }
        catch (Exception ex) {}
    }
    
    public final Image a(String a, final boolean b) {
        try {
            MediaTracker a2;
            if (this.a != null) {
                a2 = this.a;
            }
            else {
                final Button button;
                a2 = new MediaTracker(button);
                button = new Button();
            }
            final MediaTracker mediaTracker = a2;
            URL url;
            if (b) {
                url = new URL(this.a, "Resources/" + this.b + "/" + a);
            }
            else {
                url = new URL(this.a, "Resources/" + a);
            }
            if ((a = (String)this.a(url)) != null) {
                final int n = this.F++;
                mediaTracker.addImage((Image)a, n);
                try {
                    mediaTracker.waitForID(n);
                }
                catch (InterruptedException ex) {}
                if (mediaTracker.isErrorID(n)) {
                    a = null;
                }
            }
            return (Image)a;
        }
        catch (MalformedURLException ex2) {
            return null;
        }
    }
    
    public Image a(final String s, final boolean b, final int n) {
        return null;
    }
    
    public abstract Image a(final URL p0);
    
    public abstract void a(final String p0, final String p1, final ap p2, final String p3, final int p4);
    
    public final void c() {
        this.h = true;
        if (this.a != null) {
            final m m;
            (m = new m(65794, 1)).a(0, 0, super.g);
            m.a(0, null);
            m.e = -1;
            m.d = -1;
            this.m(m);
            super.b = -999;
            if (this.a != null) {
                final Frame a = this.a.a();
                this.a.a();
                if (a != null) {
                    a.dispose();
                }
            }
        }
        this.l(new m(66561, 0));
        this.a();
        this.f = false;
    }
    
    public abstract void a(final URL p0, final String p1);
    
    protected final void b(final m m) {
        for (int i = 0; i < m.b; ++i) {
            final au au;
            if ((au = (au)this.c.b(m.a(i, 0))) != null) {
                this.a(au, m.a(i, 1), 0L, 0);
            }
        }
    }
    
    protected abstract void a(final au p0, final int p1, final long p2, final int p3);
    
    protected final void c(final m m) {
        new ad(new Frame(), ak.a(54), m.a(0, 0), this).setVisible(true);
        this.h = true;
        this.l(new m(66561, 0));
        this.a();
    }
    
    protected final void d(final m m) {
        final String[] array = new String[m.b];
        for (int i = 0; i < array.length; ++i) {
            array[i] = m.a(i, 0);
        }
        new ad(this.a.a(), ak.a(55), array, this).setVisible(true);
    }
    
    protected final void e(final m m) {
        String s = null;
        final int a = m.a(0, 1);
        switch (m.a(0, 0)) {
            case 2: {
                s = ak.a(ak.a(56), new String[] { this.a.a });
                break;
            }
            case 4: {
                s = ak.a(ak.a(57), new String[] { this.a.a });
                break;
            }
            case 3: {
                s = ak.a(ak.a(58), new String[] { this.a.a, aK.a(), aK.a(this.f) });
                break;
            }
            case 5: {
                s = ak.a(ak.a(59), new String[] { this.a.a });
                break;
            }
            case 6: {
                s = ak.a(ak.a(60), new String[] { this.a.a });
                break;
            }
            case 8: {
                s = ak.a(ak.a(61), new String[] { this.a.a });
                break;
            }
            case 23: {
                s = ak.a(ak.a(558), new String[] { this.a.a });
                break;
            }
            case 10: {
                s = ak.a(ak.a(62), new String[] { this.a.a });
                break;
            }
            case 9: {
                s = ak.a(ak.a(63), new String[] { this.a.a });
                break;
            }
            case 0: {
                if (a == 65793) {
                    s = ak.a(ak.a(64), new String[] { this.a.a, super.c });
                    break;
                }
                s = ak.a(ak.a(65), new String[] { m.a(0, 0) });
                break;
            }
            case 1:
            case 7: {
                if (a == 65793) {
                    s = ak.a(ak.a(66), new String[] { this.a.a });
                    break;
                }
                break;
            }
        }
        if (s == null) {
            s = ak.a(67);
        }
        if (a == 65793) {
            this.h = true;
            this.l(new m(66561, 0));
            this.a();
        }
        new ad(null, ak.a(1), s, this).setVisible(true);
    }
    
    protected final void f(final m m) {
        if (!this.h) {
            new ad((this.a == null) ? null : this.a.a(), ak.a(68), new String[] { m.a(0, 0), m.a(0, 1) }, this).setVisible(true);
        }
    }
    
    protected final void g(final m m) {
        for (int i = 0; i < m.b; ++i) {
            new ad((this.a == null) ? null : this.a.a(), m.a(i, 0), m.a(i, 1), this, m.a(0, 4) ? new Color(m.a(0, 1)) : null, m.a(0, 5) ? new Color(m.a(0, 2)) : null).setVisible(true);
        }
    }
    
    protected final void h(final m m) {
        new ad(this.a.a(), ak.a(69), ak.a(ak.a(70), new String[] { String.valueOf(m.a(0, 0)) }), this).setVisible(true);
        this.h = true;
        this.l(new m(66561, 0));
        this.a();
    }
    
    protected final void i(final m m) {
        final long a = m.a(-1);
        if (this.c == -999L && m.a(-1, 60)) {
            this.m = true;
        }
        W w;
        if (m.a(-1, 59)) {
            if ("Admin".equals(this.b)) {
                this.c = a;
                w = this.h;
            }
            else {
                w = this.v;
            }
            this.g = a;
        }
        else {
            w = this.h;
            this.c = a;
        }
        if (m.a(this.g | this.c, 62) || m.a(this.g | this.c, 61)) {
            this.m = true;
        }
        try {
            for (int i = 0; i < m.b; ++i) {
                final int a2 = m.a(i, 0);
                at at = (at)w.b(a2);
                if (m.a(i, 63)) {
                    if (at != null) {
                        w.b(a2);
                    }
                }
                else {
                    if (at == null) {
                        at = new at(a2, m.a(i, 0));
                        w.a(at);
                    }
                    else {
                        at.c = m.a(i, 0);
                    }
                    at.a = m.a(i, 1);
                    at.a = m.a(i);
                    at.a = m.a(i, 1);
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    protected final void j(final m m) {
        ai ai = null;
        an an = null;
        try {
            for (int i = 0; i < m.b; ++i) {
                final int a = m.a(i, 0);
                ai ai2 = (ai)this.d.b(a);
                if (m.a(i, 63) || m.a(i, 0) == null) {
                    if (ai2 != null) {
                        this.d.b(a);
                        if (this.a != null) {
                            this.a.a(ai2);
                        }
                    }
                    if (a == super.b) {
                        an = ai2;
                    }
                }
                else {
                    if (ai2 == null) {
                        String a2;
                        if ((a2 = m.a(i, 0)) == null) {
                            System.err.println("null room name received.");
                            a2 = new String("no name");
                        }
                        ai2 = new ai(a, this.a(a2));
                        this.d.a(ai2);
                    }
                    else {
                        ai2.c = this.a(m.a(i, 0));
                    }
                    m.a(i, 1);
                    m.a(i, 2);
                    m.a(i, 3);
                    ai2.a = m.a(i, 4);
                    ai2.b = m.a(i, 5);
                    ai2.c = m.a(i, 6);
                    ai2.d = m.a(i, 7);
                    if (m.a(i, 8) != 0) {
                        ai2.e = m.a(i, 8);
                    }
                    ai2.a = this.a(m.a(i, 1));
                    this.a(m.a(i, 2));
                    m.a(i, 3);
                    final ap a3 = ai2.a;
                    final ai ai3 = ai2;
                    final int n = i;
                    if (n < 0 || n >= m.b) {
                        throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
                    }
                    if (0 >= m.a()) {
                        throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + 0);
                    }
                    ai3.a = m.a[n * m.a()];
                    if (ai2.a != null && !ai2.a.equals(a3)) {
                        ai2.b = true;
                    }
                    ai2.a = m.a(i);
                    if (m.a(i, 62)) {
                        this.j = a;
                    }
                    if (this.a != null) {
                        this.a.b(ai2);
                    }
                    if (this.h != null && ai2.c.equals(this.h)) {
                        this.h = null;
                        (ai = ai2).b = false;
                    }
                }
            }
            if (this.j == -999) {
                this.j = m.a(0, 0);
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        if (ai != null) {
            this.a(ai);
            this.a.e();
            return;
        }
        if (an != null) {
            final O o = (O)this.d.b(this.j);
            this.b(this.j);
            new ad(this.a.a(), this.a.a, ak.a(ak.a(71), new String[] { this.a(an.c), this.a(o.c) }), this).setVisible(true);
        }
    }
    
    protected final void k(final m m) {
        try {
            bm a = null;
            bm a2 = null;
            for (int i = 0; i < m.b; ++i) {
                int n = m.a(i, 0);
                bm bm = (bm)this.l.b(n);
                if (m.a(i, 63)) {
                    this.l.b(n);
                }
                else {
                    if (bm == null) {
                        bm = new bm(n, m.a(i, 0));
                        n = this.l.a(bm, n);
                        m.a(i, 0, n);
                    }
                    else {
                        bm.c = m.a(i, 0);
                    }
                    bm.a = new Color(m.a(i, 1));
                    bm.b = new Color(m.a(i, 2));
                    bm.c = new Color(m.a(i, 3));
                    bm.d = new Color(m.a(i, 4));
                    bm.g = new Color(m.a(i, 5));
                    bm.h = new Color(m.a(i, 6));
                    bm.i = new Color(m.a(i, 7));
                    bm.j = new Color(m.a(i, 7));
                    bm.k = new Color(m.a(i, 8));
                    bm.l = new Color(m.a(i, 9));
                    bm.m = new Color(m.a(i, 10));
                    bm.n = new Color(m.a(i, 11));
                    bm.a = m.a(i, 1);
                    bm.b = m.a(i, 12);
                    bm.a = m.a(i, 13);
                    bm.a();
                    bm.a = m.a(i);
                    bm.a(m.a(i, 2));
                    bm.c = m.a(i, 14);
                    bm.e = new Color(m.a(i, 15));
                    bm.f = new Color(m.a(i, 16));
                    bm.o = new Color(m.a(i, 17));
                    bm.p = new Color(m.a(i, 18));
                    bm.q = new Color(m.a(i, 19));
                    bm.r = new Color(m.a(i, 20));
                    if (bm.a(62)) {
                        a = (bm)bm.clone();
                    }
                    if (n == aV.c) {
                        a2 = (bm)bm.clone();
                    }
                }
            }
            if (a2 != null) {
                this.a = a2;
            }
            else if (a != null) {
                this.a = a;
            }
            if (this.a != null) {
                this.a.d();
            }
            if (this.a != null) {
                for (int j = 0; j < this.a.a(); ++j) {
                    ((aA)this.a.a(j)).a.b();
                }
            }
            chat.j.d = this.a.o;
            chat.j.e = this.a.p;
            chat.j.a = this.a.a(54);
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public final synchronized void l(final m m) {
        this.a.a(m);
        this.notify();
        this.notify();
    }
    
    public abstract void d();
    
    public void run() {
        this.a.a();
        try {
            this.a.start();
            while (!this.h || !this.a.a()) {
                while (this.a.a() && !this.h) {
                    synchronized (this) {
                        this.wait();
                        continue;
                    }
                    break;
                }
                this.a((m)this.a.a());
            }
        }
        catch (InterruptedException ex) {
            if (!this.h) {
                this.h = true;
                this.a();
            }
        }
    }
    
    public final void m(m m) {
        try {
            final m i = m;
            final aS a = this.a;
            m = i;
            synchronized (a) {
                a.writeInt(m.a);
                a.writeInt(m.d);
                a.writeInt(m.e);
                a.writeInt(m.f);
                a.writeShort(m.b);
                a.writeInt(m.c);
                a.writeInt(m.g++);
                if (m.a != null) {
                    a.writeInt(m.a.length);
                    a.write(m.a);
                }
                else {
                    a.writeInt(0);
                }
                for (int j = 0; j < m.a.length; ++j) {
                    a.writeLong(m.a[j]);
                }
                if (m.a != null) {
                    for (int k = 0; k < m.a.length; ++k) {
                        a.writeInt(m.a[k]);
                    }
                }
                if (m.a != null) {
                    for (int l = 0; l < m.a.length; ++l) {
                        if (m.a[l] == null) {
                            a.writeUTF("\u0000");
                        }
                        else {
                            a.writeUTF(m.a[l]);
                        }
                    }
                }
                if (m.a != null) {
                    for (int n = 0; n < m.a.length; ++n) {
                        if (m.a[n] == null) {
                            a.writeInt(0);
                        }
                        else {
                            final ap ap = m.a[n];
                            final aS as = a;
                            final ap ap2 = ap;
                            if (ap.a == null) {
                                as.writeInt(0);
                            }
                            else {
                                as.writeInt(ap2.a.length);
                                as.write(ap2.a);
                            }
                        }
                    }
                }
            }
            this.a.flush();
        }
        catch (IOException ex) {
            if (!this.h) {
                this.a();
            }
        }
    }
    
    public final void a(final String s, final int e, final int d, final int n, final int n2) {
        final m m;
        (m = new m(66305, 1)).a(0, s);
        m.a(0, 0, super.g);
        m.a(0, 1, n);
        m.a(0, 2, n2);
        m.e = e;
        m.d = d;
        this.m(m);
    }
    
    public final void b(final String s) {
        final int b = super.b;
        final m m;
        (m = new m(66305, 1)).a(0, s);
        m.a(0, 0, super.g);
        m.e = -1;
        m.d = b;
        this.m(m);
        this.e = System.currentTimeMillis();
    }
    
    public final void a(final String s, final int n, final int n2) {
        this.a(s, -1, super.b, n, n2);
        this.e = System.currentTimeMillis();
    }
    
    public abstract void a(final ao p0, final aj p1);
    
    public final void a(final String s, final int n, final int n2, final int f) {
        final m m;
        (m = new m(132867, 1)).e = -1;
        if (s == null) {
            m.e = -5;
        }
        else {
            m.a(0, s);
            m.a(1, super.c);
            m.a(0, 0, super.g);
            m.a(0, 1, this.m);
            m.a(0, 2, super.c);
            m.a(0, 3, n);
            m.a(0, 5, n2);
        }
        m.d = -1;
        m.f = f;
        this.m(m);
    }
    
    public final void c(final int n) {
        final m m;
        (m = new m(132866, 1)).e = -1;
        m.d = -1;
        m.a(0, 0, n);
        m.a(0, 1, super.g);
        m.a(0, 1, true);
        this.m(m);
    }
    
    public final void a(final Font font) {
        this.a.a = font.getName();
        this.a.b = font.getStyle();
        this.a.a = font.getSize();
        this.a.a();
        if (this.a != null) {
            this.a.d();
        }
        if (this.a != null) {
            for (int i = 0; i < this.a.a(); ++i) {
                ((aA)this.a.a(i)).a.b();
            }
        }
    }
    
    public static boolean a(final au au, final String s) {
        final String c;
        if ((c = au.c).length() == 0 || s.length() == 0) {
            return true;
        }
        if (s.startsWith("ip=") && s.length() > 2) {
            return false;
        }
        int index = 0;
        final char[] charArray = s.toLowerCase().toCharArray();
        final String lowerCase = c.toLowerCase();
        for (int i = 0; i < charArray.length; ++i) {
            if ((index = lowerCase.indexOf(charArray[i], index)) == -1) {
                return false;
            }
        }
        return true;
    }
    
    public final void a(final boolean i) {
        this.i = i;
        try {
            synchronized (this.c) {
                for (int j = 0; j < this.c.a(); ++j) {
                    final au au = (au)this.c.a(j);
                    if ((this.i || au.b == super.b) && (!this.j || a(au, this.a.a()))) {
                        this.a.a(au, true);
                    }
                    else {
                        this.a.a(au);
                    }
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public final void e() {
        this.o = false;
    }
    
    public final void f() {
        this.p = false;
    }
    
    public final void a(final Frame frame) {
        final Menu menu = new Menu(this.a.a);
        final MenuBar menuBar = new MenuBar();
        menu.add(this.d = new MenuItem(ak.a(559)));
        menu.addSeparator();
        for (int i = 1; i <= this.n.a(); ++i) {
            menu.add((MenuItem)this.n.b(i));
        }
        if (this.n.a() >= 1) {
            menu.addSeparator();
        }
        menu.add(this.c = new MenuItem(ak.a(11)));
        if (this.a(70)) {
            menu.add(this.a);
        }
        menu.addSeparator();
        final Menu menu2;
        (menu2 = new Menu(ak.a(28))).add(this.a[0] = new CheckboxMenuItem(ak.a(549), true));
        menu2.add(this.a[1] = new CheckboxMenuItem(ak.a(550)));
        menu2.add(this.a[2] = new CheckboxMenuItem(ak.a(551), false));
        menu2.add(this.a[3] = new CheckboxMenuItem(ak.a(552), false));
        menu2.add(this.a[4] = new CheckboxMenuItem(ak.a(553), false));
        menu.add(menu2);
        menu.addSeparator();
        if (!this.e) {
            menu.add(this.b = new MenuItem(ak.a(12)));
        }
        menuBar.add(menu);
        frame.setMenuBar(menuBar);
    }
    
    public void d(final int n) {
    }
    
    public final void g() {
        final String a = ak.a(ak.a(72), new String[] { this.a.a });
        final m m;
        (m = new m(50400771, 1)).e = super.g;
        m.a(0, a);
        this.m(m);
    }
    
    public final String b() {
        return this.a.a;
    }
    
    public bh() {
        super(-999, null);
        this.a = new CheckboxMenuItem[5];
        this.e = false;
        this.f = false;
        this.F = 1000;
        this.e = 0L;
        this.i = 250;
        this.o = true;
        this.p = true;
        this.a = null;
        this.a = new W();
        this.b = new W();
        this.c = new W();
        this.d = new W();
        this.e = new W();
        this.f = new W();
        this.g = new W();
        this.h = new W();
        this.v = new W();
        this.i = new W();
        this.j = new W();
        this.k = new W();
        new W();
        this.m = new W();
        this.l = new W();
        this.n = new W();
        this.o = new W();
        this.a = new MenuItem(ak.a(655));
        this.p = new W();
        this.q = new W();
        this.r = new W();
        this.s = new W();
        this.t = new W();
        this.a = new Vector();
        this.b = new Vector();
        this.c = -999L;
        this.h = true;
        this.i = false;
        this.a = new U((byte)0);
        this.j = -999;
        this.k = -999;
        this.l = -999;
        this.k = false;
        this.l = false;
        this.g = false;
        this.m = -999;
        this.n = 2;
        this.o = -999;
        this.p = -999;
        this.q = -999;
        this.r = 7;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 300;
        this.w = 5;
        this.x = 7;
        this.y = 600;
        this.z = 10;
        this.A = 15;
        this.B = 50;
        this.C = 0;
        this.D = 0;
        this.E = 2;
        this.m = false;
        this.n = false;
        this.j = false;
        this.a = bm.a;
        this.u = new W();
        this.a = new G();
    }
    
    static {
        a = new String[] { null, null, null, null, null, null, null, null, null };
        final String[] array = { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
    }
}
