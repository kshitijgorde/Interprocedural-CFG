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
import java.awt.Event;
import java.util.Hashtable;
import java.io.DataInput;
import java.awt.MenuItem;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.Vector;
import java.net.Socket;
import java.awt.Image;

public abstract class cs extends ai implements Runnable
{
    protected static final String[] a;
    public static Image b;
    public static Image c;
    public static Image d;
    public static Image e;
    public static Image f;
    public static Image g;
    public static Image h;
    public static Image i;
    public static Image[] a;
    public static Image j;
    public boolean f;
    public boolean g;
    public boolean h;
    private int g;
    private long a;
    public int p;
    private long c;
    private boolean e;
    private boolean v;
    public boolean i;
    public boolean j;
    public boolean k;
    protected bR a;
    protected bN a;
    public String f;
    public String g;
    protected bj a;
    protected Thread a;
    protected Socket a;
    public aX a;
    protected av a;
    public av b;
    public av c;
    public av d;
    public av e;
    public av f;
    public av g;
    public av h;
    private av w;
    public av i;
    public av j;
    public av k;
    public av l;
    public av m;
    public av n;
    public av o;
    public av p;
    public av q;
    public av r;
    public av s;
    public av t;
    public av u;
    public Vector a;
    public Vector b;
    public long d;
    public long e;
    public long f;
    public long g;
    public URL a;
    protected boolean l;
    protected boolean m;
    public MediaTracker a;
    protected boolean n;
    public aa a;
    public bU a;
    private as a;
    protected int q;
    protected int r;
    public int s;
    private String e;
    public boolean o;
    public boolean p;
    private MenuItem a;
    private MenuItem b;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public URL b;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public long h;
    public int O;
    public int P;
    public int Q;
    public String o;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public cz a;
    protected String p;
    public av v;
    public boolean u;
    
    public final boolean a() {
        this.a.setSoTimeout(10000);
        r r;
        if ((r = new r(this.a)).a != 71427) {
            r = new r(this.a);
        }
        if (r.a != 71427) {
            return false;
        }
        this.c = r.a(0, 0);
        this.a.setSoTimeout(0);
        final r r2 = new r(71427, 1);
        final long n = 504403159339237376L;
        final r r3 = r2;
        if (0 >= (r3.c >> 16) - 1) {
            throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + 0);
        }
        r3.a(0, 0, (int)(n >>> 32));
        r3.a(0, 1, (int)n);
        this.o(r2);
        this.i = false;
        this.j = false;
        this.k = false;
        return true;
    }
    
    public final String a(String s) {
        if (s != null && this.r) {
            try {
                synchronized (this.h) {
                    for (int i = 0; i < this.h.a(); ++i) {
                        s = ((ba)this.h.a(i)).a(s);
                    }
                }
            }
            finally {
                throw loadexception(java.lang.Throwable.class);
            }
            try {
                final cs cs;
                synchronized (cs.w) {
                    for (int j = 0; j < cs.w.a(); ++j) {
                        s = ((ba)cs.w.a(j)).a(s);
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
        if (a != null && this.r) {
            vector = new Vector<Hashtable<String, String>>();
            try {
                synchronized (this.h) {
                    int int1 = 0;
                    for (int i = 0; i < this.h.a(); ++i) {
                        final ba ba;
                        if ((ba = (ba)this.h.a(i)) != null) {
                            if (int1 >= n) {
                                break;
                            }
                            final String s = a;
                            final String[][] a2;
                            a = (a2 = ba.a(a, n, int1))[0][0];
                            int1 = Integer.parseInt(a2[0][1]);
                            if (!a.equals(s) && ba.a != 0 && ba.a != -16777216) {
                                final Hashtable<String, String> hashtable;
                                (hashtable = new Hashtable<String, String>()).put("ID", (String)new Integer(i));
                                hashtable.put("name", new String(ba.a));
                                hashtable.put("color", (String)new Integer(ba.a));
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
                final cs cs;
                synchronized (cs.w) {
                    for (int j = 0; j < cs.w.a(); ++j) {
                        a = ((ba)cs.w.a(j)).a(a);
                    }
                }
            }
            finally {
                throw loadexception(java.lang.Throwable.class);
            }
        }
        return new Object[] { a, vector };
    }
    
    public abstract void c();
    
    public final void a(final String e, final String s, final String s2, final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.e = e;
        final r r;
        (r = new r(67330, 1)).e = -1;
        r.d = -1;
        r.f = super.i;
        r.a(0, 0, -999);
        r.a(0, 0, e);
        r.a(0, 1, s);
        r.a(0, 5, n);
        r.a(0, 6, n2);
        r.a(0, 7, n3);
        r.a(0, 8, n4);
        if (s2.length() > 0) {
            r.a(0, 0, new aV(s2));
        }
        if (!b) {
            r.a(0, 58, true);
        }
        this.o(r);
    }
    
    public final void i() {
        new bt(this.a.a(), this).setVisible(true);
    }
    
    public abstract void a(final r p0);
    
    public final void d(final int n) {
        final aP ap;
        if ((ap = (aP)this.d.b(n)) != null) {
            this.a(ap);
            return;
        }
        System.err.println("no such room! " + n);
    }
    
    public final void a(final aP ap) {
        if (ap.d && ap.a != null) {
            new aY(this.a.a(), this, ap).setVisible(true);
        }
        if (!ap.d || ap.a == null) {
            final r r;
            (r = new r(66049, 1)).a(0, 0, super.i);
            r.a(0, 1, ap.i);
            r.e = -1;
            r.d = -1;
            this.o(r);
        }
    }
    
    public final long a() {
        return this.a;
    }
    
    public final int b() {
        return this.p;
    }
    
    public final boolean b() {
        return this.e;
    }
    
    public final boolean c() {
        return this.v;
    }
    
    public final int a(final int n) {
        int n2 = 0;
        try {
            synchronized (this.c) {
                for (int i = 0; i < this.c.a(); ++i) {
                    final aZ az;
                    if ((az = (aZ)this.c.a(i)).b == n && (!az.a(23) || this.a(24)) && (this.a(52) || !az.a(18) || az.i == super.i)) {
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
        if (event.target == this.a) {
            this.j();
        }
        else if (this.n.a(event.target)) {
            this.a((URL)this.o.a(this.n.a(event.target)), "_blank");
        }
        else if (event.target == this.b) {
            this.f(0);
        }
        return true;
    }
    
    public final void a(final URL a) {
        this.a = a;
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
                url = new URL(this.a, "Resources/" + this.f + "/" + a);
            }
            else {
                url = new URL(this.a, "Resources/" + a);
            }
            if ((a = (String)this.a(url)) != null) {
                final int n = this.g++;
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
    
    public abstract void a(final String p0, final String p1, final aV p2, final String p3, final int p4);
    
    public final void j() {
        this.l = true;
        if (this.a != null) {
            final r r;
            (r = new r(65794, 1)).a(0, 0, super.i);
            r.a(0, 0, this.k);
            r.e = -1;
            r.d = -1;
            this.o(r);
            super.b = -999;
            if (this.a != null) {
                final Frame a = this.a.a();
                this.a.a();
                if (a != null) {
                    a.dispose();
                }
            }
        }
        this.n(new r(66561, 0));
        this.c();
        this.h = false;
    }
    
    public void a(final aU au, final aZ az) {
    }
    
    public void a(final aU au) {
    }
    
    public void a(final byte[] array) {
    }
    
    public abstract void a(final URL p0, final String p1);
    
    public void e(final r r) {
        for (int i = 0; i < r.b; ++i) {
            final aZ az;
            if ((az = (aZ)this.c.b(r.a(i, 0))) != null) {
                this.a(az, r.a(i, 1), 0L, 0);
            }
        }
    }
    
    protected abstract void a(final aZ p0, final int p1, final long p2, final int p3);
    
    protected final void f(final r r) {
        new bD(new Frame(), aS.a(54), r.a(0, 0), this).setVisible(true);
        this.l = true;
        this.n(new r(66561, 0));
        this.c();
    }
    
    protected final void g(final r r) {
        final String[] array = new String[r.b];
        for (int i = 0; i < array.length; ++i) {
            array[i] = r.a(i, 0);
        }
        new bD(this.a.a(), aS.a(55), array, this).setVisible(true);
    }
    
    public void b(final r r) {
        String s = null;
        final int a = r.a(0, 1);
        switch (r.a(0, 0)) {
            case 2: {
                s = bm.a(aS.a(56), new String[] { this.a.a });
                break;
            }
            case 4: {
                s = bm.a(aS.a(57), new String[] { this.a.a });
                break;
            }
            case 3: {
                s = bm.a(aS.a(58), new String[] { this.a.a, bB.a(), bB.a(this.c) });
                break;
            }
            case 5: {
                s = bm.a(aS.a(59), new String[] { this.a.a });
                break;
            }
            case 6: {
                s = bm.a(aS.a(60), new String[] { this.a.a });
                break;
            }
            case 8: {
                s = bm.a(aS.a(61), new String[] { this.a.a });
                break;
            }
            case 23: {
                s = bm.a(aS.a(558), new String[] { this.a.a });
                break;
            }
            case 24: {
                s = bm.a(aS.a(685), new String[] { this.a.a });
                break;
            }
            case 10: {
                s = bm.a(aS.a(62), new String[] { this.a.a });
                break;
            }
            case 9: {
                s = bm.a(aS.a(63), new String[] { this.a.a });
                break;
            }
            case 0: {
                if (a == 65793) {
                    s = bm.a(aS.a(64), new String[] { this.a.a, super.d });
                    break;
                }
                s = bm.a(aS.a(65), new String[] { r.a(0, 0) });
                break;
            }
            case 1:
            case 7: {
                if (a == 65793) {
                    s = bm.a(aS.a(66), new String[] { this.a.a });
                    break;
                }
                break;
            }
        }
        if (s == null) {
            s = aS.a(67);
        }
        if (a == 65793) {
            this.l = true;
            this.n(new r(66561, 0));
            this.c();
        }
        new bD(null, aS.a(1), s, this).setVisible(true);
    }
    
    protected final void h(final r r) {
        if (!this.l) {
            new bD((this.a == null) ? null : this.a.a(), aS.a(68), new String[] { r.a(0, 0), r.a(0, 1) }, this).setVisible(true);
        }
    }
    
    protected final void i(final r r) {
        for (int i = 0; i < r.b; ++i) {
            final bD bd;
            (bd = new bD((this.a == null) ? null : this.a.a(), r.a(i, 0), r.a(i, 1), this, r.a(0, 4) ? new Color(r.a(0, 1)) : null, r.a(0, 5) ? new Color(r.a(0, 2)) : null)).setModal(false);
            bd.setVisible(true);
        }
    }
    
    protected final void j(final r r) {
        new bD(this.a.a(), aS.a(69), bm.a(aS.a(70), new String[] { String.valueOf(r.a(0, 0)) }), this).setVisible(true);
        this.l = true;
        this.n(new r(66561, 0));
        this.c();
    }
    
    protected final void k(final r r) {
        final long a = r.a(-1);
        if (this.e == -999L && r.a(-1, 60)) {
            this.r = true;
        }
        av av;
        if (r.a(-1, 59)) {
            if ("Admin".equals(this.f)) {
                this.e = a;
                av = this.h;
            }
            else {
                av = this.w;
            }
            this.f = a;
        }
        else {
            av = this.h;
            this.e = a;
        }
        if (r.a(this.f | this.e, 62) || r.a(this.f | this.e, 61)) {
            this.r = true;
        }
        try {
            for (int i = 0; i < r.b; ++i) {
                final int a2 = r.a(i, 0);
                ba ba = (ba)av.b(a2);
                if (r.a(i, 63)) {
                    if (ba != null) {
                        av.b(a2);
                    }
                }
                else {
                    if (ba == null) {
                        ba = new ba(a2, r.a(i, 0));
                        av.a(ba);
                    }
                    else {
                        ba.d = r.a(i, 0);
                    }
                    ba.a = r.a(i, 1);
                    ba.b = r.a(i);
                    ba.a = r.a(i, 1);
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public void a() {
    }
    
    public void b() {
    }
    
    protected final void l(final r r) {
        aP ap = null;
        U u = null;
        try {
            for (int i = 0; i < r.b; ++i) {
                final int a = r.a(i, 0);
                aP ap2 = (aP)this.d.b(a);
                if (r.a(i, 63) || r.a(i, 0) == null) {
                    if (ap2 != null) {
                        this.d.b(a);
                        if (this.a != null) {
                            this.a.a(ap2);
                        }
                    }
                    if (a == super.b) {
                        u = ap2;
                    }
                }
                else {
                    if (ap2 == null) {
                        String a2;
                        if ((a2 = r.a(i, 0)) == null) {
                            System.err.println("null room name received.");
                            a2 = new String("no name");
                        }
                        ap2 = new aP(a, a2);
                        this.d.a(ap2);
                    }
                    else {
                        ap2.d = r.a(i, 0);
                    }
                    ap2.b = r.a(i, 1);
                    ap2.c = r.a(i, 2);
                    ap2.d = r.a(i, 3);
                    ap2.a = r.a(i, 4);
                    ap2.e = r.a(i, 5);
                    ap2.f = r.a(i, 6);
                    ap2.g = r.a(i, 7);
                    if (r.a(i, 8) != 0) {
                        ap2.h = r.a(i, 8);
                    }
                    ap2.a = this.a(r.a(i, 1));
                    ap2.b = this.a(r.a(i, 2));
                    r.a(i, 3);
                    final aV a3 = ap2.a;
                    ap2.a = r.a(i);
                    if (ap2.a != null && !ap2.a.equals(a3)) {
                        ap2.d = true;
                    }
                    ap2.b = r.a(i);
                    if (r.a(i, 62)) {
                        this.q = a;
                    }
                    if (this.a != null) {
                        this.a.b(ap2);
                    }
                    if (this.e != null && ap2.d.equals(this.e)) {
                        this.e = null;
                        (ap = ap2).d = false;
                    }
                }
            }
            if (this.q == -999) {
                this.q = r.a(0, 0);
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        if (ap != null) {
            this.a(ap);
            this.a.e();
            return;
        }
        if (u != null) {
            final ak ak = (ak)this.d.b(this.q);
            this.d(this.q);
            new bD(this.a.a(), this.a.a, bm.a(aS.a(71), new String[] { u.d, ak.d }), this).setVisible(true);
        }
    }
    
    protected final void m(final r r) {
        try {
            cz a = null;
            cz a2 = null;
            for (int i = 0; i < r.b; ++i) {
                int n = r.a(i, 0);
                cz cz = (cz)this.l.b(n);
                if (r.a(i, 63)) {
                    this.l.b(n);
                }
                else {
                    if (cz == null) {
                        cz = new cz(n, r.a(i, 0));
                        n = this.l.a(cz, n);
                        r.a(i, 0, n);
                    }
                    else {
                        cz.d = r.a(i, 0);
                    }
                    cz.a = new Color(r.a(i, 1));
                    cz.b = new Color(r.a(i, 2));
                    cz.c = new Color(r.a(i, 3));
                    cz.d = new Color(r.a(i, 4));
                    cz.g = new Color(r.a(i, 5));
                    cz.h = new Color(r.a(i, 6));
                    cz.i = new Color(r.a(i, 7));
                    cz.j = new Color(r.a(i, 7));
                    cz.k = new Color(r.a(i, 8));
                    cz.l = new Color(r.a(i, 9));
                    cz.m = new Color(r.a(i, 10));
                    cz.n = new Color(r.a(i, 11));
                    cz.a = r.a(i, 1);
                    cz.b = r.a(i, 12);
                    cz.a = r.a(i, 13);
                    cz.a();
                    cz.b = r.a(i);
                    cz.a(r.a(i, 2));
                    cz.c = r.a(i, 14);
                    cz.e = new Color(r.a(i, 15));
                    cz.f = new Color(r.a(i, 16));
                    cz.o = new Color(r.a(i, 17));
                    cz.p = new Color(r.a(i, 18));
                    cz.q = new Color(r.a(i, 19));
                    cz.r = new Color(r.a(i, 20));
                    if (cz.a(62)) {
                        a = (cz)cz.clone();
                    }
                    if (n == bU.c) {
                        a2 = (cz)cz.clone();
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
                    ((bo)this.a.a(j)).a.c();
                }
            }
            chat.o.d = this.a.o;
            chat.o.e = this.a.p;
            chat.o.a = this.a.a(54);
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public final synchronized void n(final r r) {
        this.a.a(r);
        this.notify();
        this.notify();
    }
    
    public void g() {
        this.j();
    }
    
    public abstract void h();
    
    public void run() {
        this.a.a();
        try {
            this.a.start();
            while (!this.l || !this.a.a()) {
                while (this.a.a() && !this.l) {
                    synchronized (this) {
                        this.wait();
                        continue;
                    }
                    break;
                }
                this.a((r)this.a.a());
            }
        }
        catch (InterruptedException ex) {
            if (!this.l) {
                this.l = true;
                this.c();
            }
        }
    }
    
    public final void o(r r) {
        try {
            final r r2 = r;
            final bN a = this.a;
            r = r2;
            synchronized (a) {
                a.writeInt(r.a);
                a.writeInt(r.d);
                a.writeInt(r.e);
                a.writeInt(r.f);
                a.writeShort(r.b);
                a.writeInt(r.c);
                a.writeInt(r.g++);
                if (r.a != null) {
                    a.writeInt(r.a.length);
                    a.write(r.a);
                }
                else {
                    a.writeInt(0);
                }
                for (int i = 0; i < r.a.length; ++i) {
                    a.writeLong(r.a[i]);
                }
                if (r.a != null) {
                    for (int j = 0; j < r.a.length; ++j) {
                        a.writeInt(r.a[j]);
                    }
                }
                if (r.a != null) {
                    for (int k = 0; k < r.a.length; ++k) {
                        if (r.a[k] == null) {
                            a.writeUTF("\u0000");
                        }
                        else {
                            a.writeUTF(r.a[k]);
                        }
                    }
                }
                if (r.a != null) {
                    for (int l = 0; l < r.a.length; ++l) {
                        if (r.a[l] == null) {
                            a.writeInt(0);
                        }
                        else {
                            final aV av = r.a[l];
                            final bN bn = a;
                            final aV av2 = av;
                            if (av.a == null) {
                                bn.writeInt(0);
                            }
                            else {
                                bn.writeInt(av2.a.length);
                                bn.write(av2.a);
                            }
                        }
                    }
                }
            }
            this.a.flush();
        }
        catch (IOException ex) {
            if (!this.l) {
                this.c();
            }
        }
    }
    
    public final void a(final String s, final int e, final int d, final int n, final int n2) {
        final r r;
        (r = new r(66305, 1)).a(0, 0, s);
        r.a(0, 0, super.i);
        r.a(0, 1, n);
        r.a(0, 2, n2);
        r.e = e;
        r.d = d;
        this.o(r);
    }
    
    public final void a(final String s) {
        final int b = super.b;
        final r r;
        (r = new r(66305, 1)).a(0, 0, s);
        r.a(0, 0, super.i);
        r.e = -1;
        r.d = b;
        this.o(r);
        this.a = System.currentTimeMillis();
    }
    
    public final void a(final String s, final int n, final int n2) {
        this.a(s, -1, super.b, n, n2);
        this.a = System.currentTimeMillis();
    }
    
    public abstract void a(final aU p0, final ai p1);
    
    public final void a(final String s, final int n, final int n2, final int f) {
        final r r;
        (r = new r(132867, 1)).e = -1;
        if (s == null) {
            r.e = -5;
        }
        else {
            r.a(0, 0, s);
            r.a(0, 1, super.d);
            r.a(0, 0, super.i);
            r.a(0, 1, this.t);
            r.a(0, 2, super.c);
            r.a(0, 3, n);
            r.a(0, 5, n2);
        }
        r.d = -1;
        r.f = f;
        this.o(r);
    }
    
    public final void e(final int n) {
        final r r;
        (r = new r(132866, 1)).e = -1;
        r.d = -1;
        r.a(0, 0, n);
        r.a(0, 1, super.i);
        r.a(0, 1, true);
        this.o(r);
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
                ((bo)this.a.a(i)).a.c();
            }
        }
    }
    
    public static boolean a(final aZ az, String substring) {
        final String d;
        if ((d = az.d).length() == 0 || substring.length() == 0) {
            return true;
        }
        if (substring.startsWith("ip=") && substring.length() > 2) {
            substring = substring.substring(3);
            return az.a != null && az.a.startsWith(substring);
        }
        int index = 0;
        final char[] charArray = substring.toLowerCase().toCharArray();
        final String lowerCase = d.toLowerCase();
        for (int i = 0; i < charArray.length; ++i) {
            if ((index = lowerCase.indexOf(charArray[i], index)) == -1) {
                return false;
            }
        }
        return true;
    }
    
    public final void b(final boolean m) {
        this.m = m;
        try {
            synchronized (this.c) {
                for (int i = 0; i < this.c.a(); ++i) {
                    final aZ az = (aZ)this.c.a(i);
                    if (this.u) {
                        if (az.g > 0 && (this.m || az.b == super.b) && (!this.n || a(az, this.a.a()))) {
                            this.a.a(az, true);
                        }
                        else {
                            this.a.a(az);
                        }
                    }
                    else if ((this.m || az.b == super.b) && (!this.n || a(az, this.a.a()))) {
                        this.a.a(az, true);
                    }
                    else {
                        this.a.a(az);
                    }
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public final void k() {
        this.e = false;
    }
    
    public final void l() {
        this.v = false;
    }
    
    public void a(final Frame frame) {
        final Menu menu = new Menu(this.a.a);
        final MenuBar menuBar = new MenuBar();
        for (int i = 1; i <= this.n.a(); ++i) {
            menu.add((MenuItem)this.n.b(i));
        }
        if (this.n.a() >= 1) {
            menu.addSeparator();
        }
        menu.add(this.b = new MenuItem(aS.a(11)));
        if (!this.g) {
            menu.add(this.a = new MenuItem(aS.a(12)));
        }
        menuBar.add(menu);
        frame.setMenuBar(menuBar);
    }
    
    public void f(final int n) {
    }
    
    public final String b() {
        return this.a.a;
    }
    
    public final String c() {
        return this.f;
    }
    
    public cs() {
        super(-999, null);
        this.g = false;
        this.h = false;
        this.g = 1000;
        this.a = 0L;
        this.p = 250;
        this.e = true;
        this.v = true;
        this.a = null;
        this.a = new av();
        this.b = new av();
        this.c = new av();
        this.d = new av();
        this.e = new av();
        this.f = new av();
        this.g = new av();
        this.h = new av();
        this.w = new av();
        this.i = new av();
        this.j = new av();
        this.k = new av();
        new av();
        this.m = new av();
        this.l = new av();
        this.n = new av();
        this.o = new av();
        this.p = new av();
        this.q = new av();
        this.r = new av();
        this.s = new av();
        this.t = new av();
        this.u = new av();
        this.a = new Vector();
        this.b = new Vector();
        this.e = -999L;
        this.l = true;
        this.m = false;
        this.a = new as((byte)0);
        this.q = -999;
        this.r = -999;
        this.s = -999;
        this.o = false;
        this.p = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.t = -999;
        this.u = 2;
        this.v = -999;
        this.w = -999;
        this.x = -999;
        this.y = 0;
        this.z = 7;
        this.A = 2;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.E = 300;
        this.F = 5;
        this.G = 10;
        this.H = 7;
        this.I = 600;
        this.J = 10;
        this.K = 15;
        this.L = 6;
        this.M = 3;
        this.N = 50;
        this.O = 0;
        this.P = 0;
        this.Q = 2;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = false;
        this.n = false;
        this.a = cz.a;
        this.v = new av();
        this.a = new aa();
    }
    
    static {
        a = new String[] { null, null, null, null, null, null, null, null, null };
        final String[] array = { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
    }
}
