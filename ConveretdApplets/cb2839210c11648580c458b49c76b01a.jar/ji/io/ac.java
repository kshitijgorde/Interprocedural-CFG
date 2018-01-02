// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

import ji.util.m;
import java.io.File;
import java.util.StringTokenizer;
import java.util.Enumeration;
import ji.annotate.b8;
import ji.sec.u;
import java.io.EOFException;
import java.io.IOException;
import ji.util.e;
import java.io.OutputStream;
import ji.sec.g;
import java.io.InputStream;
import ji.util.i;
import ji.document.ad;
import ji.util.d;
import ji.res.z;
import ji.awt.c;
import java.util.Hashtable;
import ji.sec.az;
import java.io.BufferedOutputStream;
import ji.sec.aw;
import ji.sec.au;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.Vector;

public class ac
{
    private static boolean a;
    private static Vector b;
    private static long c;
    private static long d;
    private static boolean e;
    private static boolean f;
    private static boolean g;
    private static boolean h;
    private static boolean i;
    private static long j;
    private ByteArrayInputStream k;
    private ByteArrayOutputStream l;
    private DataInputStream m;
    private DataOutputStream n;
    private byte[] o;
    private byte[] p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private long u;
    private boolean v;
    private int w;
    private String x;
    private au y;
    private au z;
    private aw aa;
    private BufferedOutputStream ab;
    private au ac;
    private az ad;
    private static String ae;
    private static String af;
    private String ag;
    private boolean ah;
    private boolean ai;
    private boolean aj;
    private static int ak;
    private static int al;
    private static int am;
    private static int an;
    private static int ao;
    private int ap;
    private static Hashtable aq;
    private static Object ar;
    private static c as;
    public static Object at;
    private static boolean au;
    private static boolean av;
    private static boolean aw;
    private String ax;
    private int ay;
    private boolean[] az;
    private boolean a0;
    private int a1;
    private long a2;
    private boolean a3;
    private String a4;
    private String a5;
    
    public final String toString() {
        if (this.x == null) {
            return "jiFile(No File)";
        }
        if (this.t) {
            return String.valueOf(String.valueOf(new StringBuffer("jiFile(Opened: \"").append(this.x).append("\")")));
        }
        return String.valueOf(String.valueOf(new StringBuffer("jiFile(Closed: \"").append(this.x).append("\")")));
    }
    
    private final boolean b(final String s, final Object o, final String s2) {
        boolean b = false;
        final String c = ji.res.z.c(s);
        if (s2 != null && c != null && ji.util.d.b(s2, "\\", "/").toLowerCase().startsWith(ji.util.d.b(c, "\\", "/").toLowerCase())) {
            b = true;
        }
        return b;
    }
    
    public static final boolean a(final String s, final Object o, final String s2) {
        boolean b = false;
        try {
            String s3 = ji.util.d.c(o, s);
            if (s3 != null) {
                s3 = s3.toLowerCase();
            }
            if (s2 != null && s3 != null) {
                final String s4 = s3;
                final String b2 = ji.util.d.b(s2, "\\", "/");
                final String b3 = ji.util.d.b(s4, "\\", "/");
                final String b4 = ji.util.d.b(b2, "\\", "/");
                if (b4.toLowerCase().startsWith(b3.toLowerCase())) {
                    String s5 = b4.substring(b3.length() + 1);
                    final int lastIndex = s5.lastIndexOf("/");
                    if (lastIndex > 0) {
                        s5 = s5.substring(lastIndex + 1);
                    }
                    if (s5.startsWith(ac.af)) {
                        b = true;
                    }
                    else if (s5.startsWith(ac.ae)) {
                        b = false;
                    }
                }
            }
        }
        catch (Exception ex) {
            ji.io.h.d(s, "Encryption problem!");
            ex.printStackTrace();
        }
        return b;
    }
    
    private final boolean b(final Object o, final String s) {
        return this.a(o, s, false);
    }
    
    private final boolean a(final Object o, final String s, final boolean b) {
        boolean b2 = false;
        try {
            this.d(o, this.ax);
            this.ah = false;
            if (s != null && this.ag != null) {
                final String lowerCase = s.toLowerCase();
                final String ag = this.ag;
                final String lowerCase2 = ji.util.d.b(lowerCase, "\\", "/").toLowerCase();
                final String b3 = ji.util.d.b(ag, "\\", "/");
                if (lowerCase2.startsWith(b3.toLowerCase())) {
                    String s2 = lowerCase2.substring(b3.length() + 1);
                    final int lastIndex = s2.lastIndexOf("/");
                    if (lastIndex > 0) {
                        s2 = s2.substring(lastIndex + 1);
                    }
                    if (!this.aj) {
                        b2 = true;
                        this.ah = false;
                    }
                    else if (s2.startsWith(ji.io.ac.af)) {
                        b2 = true;
                        this.ah = true;
                    }
                    else if (s2.startsWith(ji.io.ac.ae)) {
                        b2 = true;
                        this.ah = false;
                    }
                }
                else if (b && lowerCase2.endsWith("v1") && lowerCase2.startsWith(ji.util.d.b(ji.res.z.c(this.ax), "\\", "/").toLowerCase())) {
                    String substring = lowerCase2;
                    final int lastIndex2 = lowerCase2.lastIndexOf("/");
                    if (lastIndex2 > 0) {
                        substring = substring.substring(lastIndex2 + 1);
                    }
                    if (substring.startsWith(ji.io.ac.af)) {
                        b2 = true;
                        this.ah = true;
                    }
                }
            }
        }
        catch (Exception ex) {
            ji.io.h.d(this.ax, "Encryption problem!");
            ex.printStackTrace();
        }
        return b2;
    }
    
    private final boolean b(final Object o) {
        boolean b = false;
        try {
            this.ah = false;
            if (this.a(o, this.x, true) && this.ah && !this.v) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    private final void c(final Object o, final String ax) {
        h(this.ax = ax);
        ji.io.ac.e = ji.util.d.dx();
        ji.io.ac.d = ji.util.d.d0();
        ji.io.ac.j = ji.io.ac.d;
        ji.io.ac.f = ji.util.d.eg();
        if (ji.io.ac.f) {
            ji.io.ac.g = ji.io.ac.e;
            ji.io.ac.h = ji.io.ac.e;
        }
        else {
            ji.io.ac.g = false;
            ji.io.ac.h = true;
        }
        if (ji.util.d.dw()) {
            ji.io.ac.g = true;
            ji.io.ac.h = true;
        }
        if (ji.util.d.az(ax)) {
            ji.io.ac.g = true;
            ji.util.d.bk(ji.io.ac.h = true);
        }
        if (ji.io.ac.b == null) {
            ji.io.ac.b = new Vector();
        }
        if (ji.io.ac.ae == null) {
            ji.io.ac.ae = "x";
        }
        if (ji.io.ac.af == null) {
            ji.io.ac.af = String.valueOf(String.valueOf(ji.io.ac.ae)).concat("e");
        }
        this.d(o, ax);
    }
    
    private final void d(final Object o, final String s) {
        if (this.ag == null) {
            this.ag = ji.util.d.c(o, s);
            if (this.ag != null) {
                this.ag = this.ag.toLowerCase();
            }
        }
    }
    
    public ac(final Object o, final String s) throws Exception {
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = 0L;
        this.v = false;
        this.w = 0;
        this.x = null;
        this.y = null;
        this.z = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ag = null;
        this.ah = false;
        this.ai = false;
        this.aj = true;
        this.ap = 0;
        this.ax = null;
        this.ay = 0;
        this.az = null;
        this.a0 = false;
        this.a1 = 0;
        this.a2 = 0L;
        this.a3 = true;
        this.a4 = "rw";
        this.a5 = "r";
        ++ji.io.ac.an;
        this.c(o, s);
    }
    
    public final String a() {
        return this.x;
    }
    
    public static final String a(String concat) {
        if (concat != null) {
            final int index = concat.indexOf("/..");
            if (index >= 0) {
                final int lastIndex = concat.substring(0, index).lastIndexOf("/");
                if (lastIndex >= 0) {
                    concat = String.valueOf(String.valueOf(concat.substring(0, lastIndex))).concat(String.valueOf(String.valueOf(concat.substring(index + 3))));
                }
            }
        }
        return concat;
    }
    
    public final void a(final Object o, final String s) {
        if (this.b(o, s)) {
            b(this.ax);
        }
    }
    
    public static final void b(final String s) {
        if (ac.as == null) {
            ac.as = new c("jiFileActiveParents");
        }
        ac.as.a(s, s);
    }
    
    private static final void f(final String s) {
        if (ac.as != null) {
            ac.as.a(s);
        }
    }
    
    public final boolean b() {
        return this.q || this.z != null;
    }
    
    public ac(final String s, final boolean b, final boolean b2, final int n, final Object o, final String s2) throws Exception {
        this(s, b, b2, n, false, o, s2);
    }
    
    public ac(final String s, final boolean b, final boolean b2, final int n, final boolean b3, final Object o, final String s2, final boolean b4) throws Exception {
        this(s, b, b2, n, b3, o, true, s2, false, b4, false);
    }
    
    public ac(final String s, final boolean b, final boolean b2, final int n, final boolean b3, final Object o, final String s2) throws Exception {
        this(s, b, b2, n, b3, o, true, s2);
    }
    
    public ac(final String s, final boolean b, final boolean b2, final int n, final Object o, final String s2, final boolean b3) throws Exception {
        this(s, b, b2, n, false, o, true, s2, b3);
    }
    
    public ac(final String s, final boolean b, final boolean b2, final int n, final boolean b3, final Object o, final boolean b4, final String s2) throws Exception {
        this(s, b, b2, n, b3, o, b4, s2, false);
    }
    
    public ac(final String s, final boolean b, final boolean b2, final int n, final boolean b3, final Object o, final boolean b4, final String s2, final boolean b5) throws Exception {
        this(s, b, b2, n, b3, o, b4, s2, b5, false, false);
    }
    
    public ac(final String s, final boolean b, final boolean b2, final int ay, final boolean b3, final Object o, final boolean b4, final String s2, final boolean v, final boolean b5, final boolean b6) throws Exception {
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = 0L;
        this.v = false;
        this.w = 0;
        this.x = null;
        this.y = null;
        this.z = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ag = null;
        this.ah = false;
        this.ai = false;
        this.aj = true;
        this.ap = 0;
        this.ax = null;
        this.ay = 0;
        this.az = null;
        this.a0 = false;
        this.a1 = 0;
        this.a2 = 0L;
        this.a3 = true;
        this.a4 = "rw";
        this.a5 = "r";
        final String a = a(s);
        final boolean b7 = false;
        this.ay = ay;
        this.v = v;
        final String x = new String(a);
        boolean t = false;
        synchronized (ji.io.ac.ar) {
            if (ji.io.ac.aq == null) {
                ji.io.ac.aq = new Hashtable();
            }
        }
        // monitorexit(ac.ar)
        ++ji.io.ac.an;
        this.r = false;
        this.q = false;
        this.s = false;
        ji.io.ac.aw = false;
        this.ap = ji.io.ac.ao++;
        Label_1112: {
            try {
                this.c(o, s2);
                this.x = x;
                boolean ds = false;
                if (ji.util.d.b() && o != null && o instanceof ad) {
                    ds = ((ad)o).ds();
                }
                this.ai = (this.b(o) || b5 || (ds && !this.b(s2, o, this.x) && !this.b(o, this.x)));
                if (!b) {
                    this.q = false;
                    final so i = i(x);
                    if (i == null) {
                        if (ji.io.ac.e) {
                            final boolean b8 = false;
                            if (!ji.util.d.al && (ji.util.i.c(84) || ji.io.ac.a)) {
                                ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer("File: : secRandomAccessFile r :").append(x).append("....."))));
                            }
                            this.ac = new au(x, this.a5, s2, this.a3);
                            if (!ji.util.d.al && (ji.util.i.c(84) || ji.io.ac.a)) {
                                ji.io.h.d(s2, "File: : secRandomAccessFile r OK");
                            }
                            t = true;
                            ++ji.io.ac.al;
                            try {
                                if (t && !b7) {
                                    ++ji.io.ac.ak;
                                    synchronized (ji.io.ac.ar) {
                                        ji.io.ac.aq.put(this.g(this.x), this);
                                    }
                                    // monitorexit(ac.ar)
                                }
                            }
                            catch (Exception ex2) {}
                            if (!b8) {
                                this.r = true;
                            }
                            if (b8) {
                                this.o = new byte[(int)this.ac.d()];
                                this.ac.a(this.o);
                            }
                        }
                    }
                    else {
                        this.o = i.b();
                    }
                    this.w = 0;
                    if (this.o != null) {
                        this.k = new ByteArrayInputStream(this.o);
                        this.p = this.o;
                        this.m = new DataInputStream(this.k);
                    }
                }
                else {
                    this.q = true;
                    this.w = 0;
                    if (ji.io.ac.h && ji.io.ac.i) {
                        if (b2 && !ji.sec.g.a(s2)) {
                            this.aa = new aw(this.x, s2);
                            t = true;
                            ++ji.io.ac.am;
                            this.ab = new BufferedOutputStream(this.aa, Math.max(ay, 1024));
                            this.s = true;
                        }
                        else {
                            if (!ji.util.d.al && (ji.util.i.c(84) || ji.io.ac.a)) {
                                ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer("File: : secRandomAccessFile rw :").append(this.x).append("....."))));
                            }
                            if (b6) {
                                (this.z = new au(this.x, this.a4, s2, this.a3)).skipBytes((int)this.z.d());
                            }
                            else {
                                this.z = new au(this.x, this.a4, s2, this.a3);
                            }
                            if (!ji.util.d.al && (ji.util.i.c(84) || ji.io.ac.a)) {
                                ji.io.h.d(s2, "File: : secRandomAccessFile rw OK");
                            }
                            t = true;
                            ++ji.io.ac.am;
                            this.a(o, this.x);
                        }
                    }
                    else {
                        this.l = new ByteArrayOutputStream(10240);
                        t = true;
                        ++ji.io.ac.am;
                    }
                    try {
                        if (!t || b7) {
                            break Label_1112;
                        }
                        ++ji.io.ac.ak;
                        synchronized (ji.io.ac.ar) {
                            ji.io.ac.aq.put(this.g(this.x), this);
                        }
                        // monitorexit(ac.ar)
                    }
                    catch (Exception ex3) {}
                }
            }
            catch (Exception ex) {
                if (!ji.util.d.al && (ji.util.i.c(84) || ji.io.ac.a)) {
                    ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer("File: : File access failed : ").append(ex).append(" "))));
                }
                ji.util.d.b(ex);
                throw ex;
            }
        }
        if (ji.io.ac.au) {
            ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer("File: : Open: Files open for read/wite: ").append(ji.io.ac.al).append("/").append(ji.io.ac.am))));
        }
        if (!ji.util.d.al && (ji.util.i.c(84) || ji.io.ac.a)) {
            ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer("File: : Files open  : Tot=").append(ji.io.ac.ak).append("/Read=").append(ji.io.ac.al).append("/Write=").append(ji.io.ac.am))));
            if (ji.io.ac.a) {
                ji.util.d.eu();
            }
            ji.util.e.a(s2);
        }
        this.t = t;
    }
    
    public final void a(final boolean ai) {
        this.ai = ai;
    }
    
    private final String g(final String s) {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(this.ap).append("***V1***").append(this.ax)));
    }
    
    public void c() {
        if (this.az == null) {
            this.az = new boolean[5];
        }
        this.a0 = true;
        if (this.q) {
            try {
                if (this.z != null) {
                    this.a2 = this.z.c();
                    this.z.e();
                    this.az[1] = true;
                }
            }
            catch (IOException ex) {
                ji.io.h.a(this.ax, ex);
            }
            try {
                if (this.l != null) {
                    this.a1 = this.l.size();
                    this.l.close();
                    this.az[0] = true;
                }
            }
            catch (IOException ex2) {
                ji.io.h.a(this.ax, ex2);
            }
            try {
                if (this.n != null) {
                    this.n.close();
                    this.az[3] = true;
                }
            }
            catch (IOException ex3) {
                ji.io.h.a(this.ax, ex3);
            }
            try {
                if (this.aa != null) {
                    this.aa.flush();
                    this.aa.close();
                }
            }
            catch (IOException ex4) {
                ji.io.h.a(this.ax, ex4);
            }
            try {
                if (this.ab != null) {
                    this.ab.flush();
                    this.ab.close();
                    this.az[2] = true;
                }
            }
            catch (IOException ex5) {
                ji.io.h.a(this.ax, ex5);
            }
        }
        else {
            try {
                if (this.ac != null) {
                    this.a2 = this.ac.c();
                    this.ac.e();
                    this.az[1] = true;
                }
            }
            catch (IOException ex6) {
                ji.io.h.a(this.ax, ex6);
            }
            try {
                if (this.k != null) {
                    this.k.close();
                    this.az[0] = true;
                }
            }
            catch (IOException ex7) {
                ji.io.h.a(this.ax, ex7);
            }
            try {
                if (this.m != null) {
                    this.m.close();
                    this.az[3] = true;
                }
            }
            catch (IOException ex8) {
                ji.io.h.a(this.ax, ex8);
            }
            try {
                if (this.ad != null) {
                    this.ad.close();
                    this.az[2] = true;
                }
            }
            catch (IOException ex9) {
                ji.io.h.a(this.ax, ex9);
            }
        }
    }
    
    public void d() {
        if (this.e()) {
            if (this.q) {
                try {
                    if (this.az[1]) {
                        (this.z = new au(this.x, this.a4, this.ax, this.a3)).a(this.a2);
                    }
                }
                catch (Exception ex) {
                    ji.io.h.a(this.ax, ex);
                }
                try {
                    if (this.az[0]) {
                        this.l = new ByteArrayOutputStream(this.a1);
                        if (this.az[3]) {
                            this.n = new DataOutputStream(this.l);
                        }
                    }
                }
                catch (Exception ex2) {
                    ji.io.h.a(this.ax, ex2);
                }
                try {
                    if (this.az[4]) {
                        this.aa = new aw(this.x, this.ax);
                    }
                }
                catch (Exception ex3) {
                    ji.io.h.a(this.ax, ex3);
                }
                try {
                    if (this.az[2] && this.aa != null) {
                        this.ab = new BufferedOutputStream(this.aa, Math.max(this.ay, 1024));
                    }
                }
                catch (Exception ex4) {
                    ji.io.h.a(this.ax, ex4);
                }
            }
            else {
                try {
                    if (this.az[1]) {
                        (this.ac = new au(this.x, this.a5, this.ax, this.a3)).a(this.a2);
                    }
                }
                catch (Exception ex5) {
                    ji.io.h.a(this.ax, ex5);
                }
                try {
                    if (this.az[0]) {
                        this.k = new ByteArrayInputStream(this.o);
                        if (this.az[3]) {
                            this.m = new DataInputStream(this.k);
                        }
                    }
                }
                catch (Exception ex6) {
                    ji.io.h.a(this.ax, ex6);
                }
                try {
                    if (this.az[2]) {
                        this.ad = new az(this.x, this.ax);
                    }
                }
                catch (Exception ex7) {
                    ji.io.h.a(this.ax, ex7);
                }
            }
        }
        this.a0 = false;
    }
    
    public boolean e() {
        return this.a0;
    }
    
    public final int f() throws Exception {
        int n = 0;
        if (this.z != null) {
            ++this.w;
            this.u = Math.max(this.u, this.w);
            n = this.z.b();
        }
        else if (this.r) {
            if (this.ac == null) {
                throw new IOException("readRAS is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            ++this.w;
            this.u = Math.max(this.u, this.w);
            n = this.ac.b();
        }
        else if (!this.q) {
            if (this.m == null) {
                throw new IOException("dataInput is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            n = this.m.read();
            ++this.w;
            this.u = Math.max(this.u, this.w);
        }
        else if (!ji.io.ac.aw) {
            ji.io.ac.aw = true;
            throw new Exception(String.valueOf(String.valueOf(new StringBuffer("read(): ").append(ji.util.d.b(265, this.ax)).append(" (").append(this.x).append(")"))));
        }
        if (this.ai && n != -1) {
            int n2;
            if ((n & 0xFF) != 0x0) {
                n2 = (byte)(255 - (n & 0xFF));
            }
            else {
                n2 = 255;
            }
            return n2 & 0xFF;
        }
        return n;
    }
    
    public final boolean g() throws Exception {
        if (this.r) {
            return this.w >= this.ac.d();
        }
        if (!this.q) {
            return this.w >= this.o.length;
        }
        if (!ji.io.ac.aw) {
            ji.io.ac.aw = true;
            throw new Exception(String.valueOf(String.valueOf(new StringBuffer("eof(): ").append(ji.util.d.b(265, this.ax)).append(" (").append(this.x).append(")"))));
        }
        return true;
    }
    
    public final int h() throws Exception {
        return this.i() & 0xFF;
    }
    
    public final byte i() throws Exception {
        byte b = 0;
        if (this.z != null) {
            ++this.w;
            this.u = Math.max(this.u, this.w);
            b = this.z.readByte();
        }
        else if (this.r) {
            if (this.ac == null) {
                throw new IOException("readRAS is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            ++this.w;
            this.u = Math.max(this.u, this.w);
            b = this.ac.readByte();
        }
        else if (!this.q) {
            if (this.m == null) {
                throw new IOException("dataInput is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            b = this.m.readByte();
            ++this.w;
            this.u = Math.max(this.u, this.w);
        }
        else if (!ji.io.ac.aw) {
            ji.io.ac.aw = true;
            throw new Exception(String.valueOf(String.valueOf(new StringBuffer("readByte(): ").append(ji.util.d.b(265, this.ax)).append(" (").append(this.x).append(")"))));
        }
        if (this.ai) {
            return (byte)(255 - (b & 0xFF));
        }
        return b;
    }
    
    public final int j() throws Exception {
        int n = 0;
        if (this.r) {
            if (this.ac == null) {
                throw new IOException("readRAS is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            ++this.w;
            this.u = Math.max(this.u, this.w);
            n = this.ac.readUnsignedByte();
        }
        else if (!this.q) {
            if (this.m == null) {
                throw new IOException("dataInput is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            n = this.m.readUnsignedByte();
            ++this.w;
            this.u = Math.max(this.u, this.w);
        }
        else if (!ji.io.ac.aw) {
            ji.io.ac.aw = true;
            throw new Exception(String.valueOf(String.valueOf(new StringBuffer("readUnsignedByte(): ").append(ji.util.d.b(265, this.ax)).append(" (").append(this.x).append(")"))));
        }
        if (this.ai && n >= 0) {
            return 255 - n;
        }
        return n;
    }
    
    public final int k() throws Exception {
        return this.l() & 0xFFFF;
    }
    
    public final short l() throws Exception {
        if (this.r) {
            if (this.ac == null) {
                throw new IOException("readRAS is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            if (this.ai) {
                final int n = this.ac.readByte() & 0xFF;
                final int n2 = this.ac.readByte() & 0xFF;
                this.w += 2;
                this.u = Math.max(this.u, this.w);
                return (short)(255 - n << 8 | 255 - n2);
            }
            this.w += 2;
            this.u = Math.max(this.u, this.w);
            return this.ac.readShort();
        }
        else if (!this.q) {
            if (this.m == null) {
                throw new IOException("dataInput is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            if (this.ai) {
                final int n3 = this.m.readByte() & 0xFF;
                final int n4 = this.m.readByte() & 0xFF;
                this.w += 2;
                this.u = Math.max(this.u, this.w);
                return (short)(255 - n3 << 8 | 255 - n4);
            }
            this.w += 2;
            this.u = Math.max(this.u, this.w);
            return this.m.readShort();
        }
        else {
            if (!ji.io.ac.aw) {
                ji.io.ac.aw = true;
                throw new Exception(String.valueOf(String.valueOf(new StringBuffer("readShort(): ").append(ji.util.d.b(265, this.ax)).append(" (").append(this.x).append(")"))));
            }
            return 0;
        }
    }
    
    public final String m() throws Exception {
        final StringBuffer sb = new StringBuffer();
        final byte[] array = new byte[1024];
        try {
            final long r = this.r();
            if (r >= this.v()) {
                return null;
            }
            int n = this.a(array);
            int n2 = 0;
            int n3 = 0;
            int n4 = -1;
            int i = 0;
            if (n < 0) {
                i = 1;
            }
            while (i == 0) {
                n4 = (array[n2++] & 0xFF);
                switch (n4) {
                    case -1:
                    case 10: {
                        i = 1;
                        break;
                    }
                    case 13: {
                        i = 1;
                        final long n5 = r + n2;
                        if (n2 >= n) {
                            break;
                        }
                        n4 = (array[n2++] & 0xFF);
                        if (n4 != 10) {
                            --n2;
                            this.a(n5);
                            break;
                        }
                        break;
                    }
                    default: {
                        sb.append((char)n4);
                        break;
                    }
                }
                if (n2 >= n) {
                    n3 += n2;
                    n2 = 0;
                    n = this.a(array);
                    if (n < 0) {
                        break;
                    }
                    continue;
                }
            }
            final int n6 = n3 + n2;
            this.a(r + n6);
            if (n6 == 0 || (n4 == -1 && sb.length() == 0)) {
                return null;
            }
        }
        catch (Exception ex) {
            if (!(ex instanceof EOFException)) {
                throw ex;
            }
        }
        return sb.toString();
    }
    
    public final String n() throws Exception {
        Object concat = null;
        try {
            byte b = this.i();
            if (concat == null) {
                concat = "";
            }
            while (b != 10) {
                if (b != 13) {
                    concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf((char)b)));
                }
                b = this.i();
            }
        }
        catch (Exception ex) {
            if (!(ex instanceof EOFException)) {
                throw ex;
            }
        }
        return (String)concat;
    }
    
    public final int a(final long[] array) throws Exception {
        return this.a(array, 0, array.length);
    }
    
    public final int a(final long[] array, final int n, final int n2) throws Exception {
        final int[] array2 = new int[n2 * 2];
        final int n3 = this.a(array2) / 2;
        int n4 = 0;
        for (int i = 0; i < n3; ++i) {
            array[i] = (array2[n4] << 32 | array2[n4 + 1]);
            n4 += 2;
        }
        return n3;
    }
    
    public final long o() throws Exception {
        return this.p() << 32 | this.p();
    }
    
    public final int p() throws Exception {
        if (this.z != null) {
            if (this.ai) {
                final int n = this.z.readByte() & 0xFF;
                final int n2 = this.z.readByte() & 0xFF;
                final int n3 = this.z.readByte() & 0xFF;
                final int n4 = this.z.readByte() & 0xFF;
                this.w += 4;
                this.u = Math.max(this.u, this.w);
                return 255 - n << 24 | 255 - n2 << 16 | 255 - n3 << 8 | 255 - n4;
            }
            this.w += 4;
            this.u = Math.max(this.u, this.w);
            return this.z.readInt();
        }
        else if (this.r) {
            if (this.ac == null) {
                throw new IOException("readRAS is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            if (this.ai) {
                final int n5 = this.ac.readByte() & 0xFF;
                final int n6 = this.ac.readByte() & 0xFF;
                final int n7 = this.ac.readByte() & 0xFF;
                final int n8 = this.ac.readByte() & 0xFF;
                this.w += 4;
                this.u = Math.max(this.u, this.w);
                return 255 - n5 << 24 | 255 - n6 << 16 | 255 - n7 << 8 | 255 - n8;
            }
            this.w += 4;
            this.u = Math.max(this.u, this.w);
            return this.ac.readInt();
        }
        else if (!this.q) {
            if (this.m == null) {
                throw new IOException("dataInput is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            if (this.ai) {
                final int n9 = this.m.readByte() & 0xFF;
                final int n10 = this.m.readByte() & 0xFF;
                final int n11 = this.m.readByte() & 0xFF;
                final int n12 = this.m.readByte() & 0xFF;
                this.w += 4;
                this.u = Math.max(this.u, this.w);
                return 255 - n9 << 24 | 255 - n10 << 16 | 255 - n11 << 8 | 255 - n12;
            }
            this.w += 4;
            this.u = Math.max(this.u, this.w);
            return this.m.readInt();
        }
        else {
            if (!ji.io.ac.aw) {
                ji.io.ac.aw = true;
                throw new Exception(String.valueOf(String.valueOf(new StringBuffer("readInt(): ").append(ji.util.d.b(265, this.ax)).append(" (").append(this.x).append(")"))));
            }
            return 0;
        }
    }
    
    public final int a(final byte[] array) throws Exception {
        int n = 0;
        if (this.z != null) {
            n = this.z.a(array);
            this.w += n;
            this.u = Math.max(this.u, this.w);
        }
        else if (this.r) {
            if (this.ac == null) {
                throw new IOException("readRAS is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            n = this.ac.a(array);
            this.w += n;
            this.u = Math.max(this.u, this.w);
        }
        else if (!this.q) {
            if (this.m == null) {
                throw new IOException("dataInput is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            n = this.m.read(array);
            this.w += n;
            this.u = Math.max(this.u, this.w);
        }
        else if (!ji.io.ac.aw) {
            ji.io.ac.aw = true;
            throw new Exception(String.valueOf(String.valueOf(new StringBuffer("read([]): ").append(ji.util.d.b(265, this.ax)).append(" (").append(this.x).append(")"))));
        }
        if (this.ai && n > 0) {
            for (int i = 0; i < n; ++i) {
                array[i] = (byte)(255 - (array[i] & 0xFF));
            }
        }
        return n;
    }
    
    public final void a(final int n) throws Exception {
        this.a(new byte[n]);
    }
    
    public final int a(final byte[] array, final int n, final int n2) throws Exception {
        int n3 = 0;
        if (this.z != null) {
            n3 = this.z.a(array, n, n2);
            if (n3 > 0) {
                this.w += n3;
                this.u = Math.max(this.u, this.w);
            }
        }
        else if (this.r) {
            if (this.ac == null) {
                throw new IOException("readRAS is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            n3 = this.ac.a(array, n, n2);
            this.w += n3;
            this.u = Math.max(this.u, this.w);
        }
        else if (!this.q) {
            if (this.m == null) {
                throw new IOException("dataInput is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
            }
            n3 = this.m.read(array, n, n2);
            this.w += n3;
            this.u = Math.max(this.u, this.w);
        }
        else if (!ji.io.ac.aw) {
            ji.io.ac.aw = true;
            throw new Exception(String.valueOf(String.valueOf(new StringBuffer("read([], x, y): ").append(ji.util.d.b(265, this.ax)).append(" (").append(this.x).append(")"))));
        }
        if (this.ai && n3 > 0) {
            for (int i = 0; i < n3; ++i) {
                array[n + i] = (byte)(255 - (array[n + i] & 0xFF));
            }
        }
        return n3;
    }
    
    public final int a(final int[] array) throws Exception {
        if (array != null) {
            return this.a(array, 0, array.length);
        }
        return 0;
    }
    
    public final int a(final int[] array, final int n, final int n2) throws Exception {
        if (this.r || !this.q || this.z != null) {
            boolean b = true;
            if (this.z != null && this.z.a()) {
                this.z.a(array, n, n2, this.ai);
                b = false;
            }
            if (b) {
                final byte[] array2 = new byte[n2 * 4];
                int n3;
                if (this.z != null) {
                    n3 = this.z.a(array2);
                }
                else if (this.r) {
                    if (this.ac == null) {
                        throw new IOException("readRAS is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
                    }
                    n3 = this.ac.a(array2);
                }
                else {
                    if (this.m == null) {
                        throw new IOException("dataInput is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
                    }
                    n3 = this.m.read(array2);
                }
                if (this.ai && n3 > 0) {
                    for (int i = 0; i < n3; ++i) {
                        array2[i] = (byte)(255 - (array2[i] & 0xFF));
                    }
                }
                int n4 = 0;
                for (int j = 0; j < n2; ++j) {
                    array[n + j] = ((array2[n4] & 0xFF) << 24) + ((array2[n4 + 1] & 0xFF) << 16) + ((array2[n4 + 2] & 0xFF) << 8) + (array2[n4 + 3] & 0xFF);
                    n4 += 4;
                }
            }
            this.w += n2 * 4;
            this.u = Math.max(this.u, this.w);
            return n2;
        }
        if (!ji.io.ac.aw) {
            ji.io.ac.aw = true;
            throw new Exception(String.valueOf(String.valueOf(new StringBuffer("read([]): ").append(ji.util.d.b(265, this.ax)).append(" (").append(this.x).append(")"))));
        }
        return 0;
    }
    
    public final int a(final short[] array, final int n, final int n2) throws Exception {
        final byte[] array2 = new byte[n2 * 2];
        if (this.r || !this.q || this.z != null) {
            int n3;
            if (this.z != null) {
                n3 = this.z.a(array2);
            }
            else if (this.r) {
                if (this.ac == null) {
                    throw new IOException("readRAS is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
                }
                n3 = this.ac.a(array2);
            }
            else {
                if (this.m == null) {
                    throw new IOException("dataInput is null, filename=".concat(String.valueOf(String.valueOf(this.x))));
                }
                n3 = this.m.read(array2);
            }
            if (this.ai && n3 > 0) {
                for (int i = 0; i < n3; ++i) {
                    array2[i] = (byte)(255 - (array2[i] & 0xFF));
                }
            }
            for (int j = 0; j < n2; ++j) {
                final int n4 = j * 2;
                array[n + j] = (short)(((array2[n4] & 0xFF) << 8) + (array2[n4 + 1] & 0xFF) & 0xFFFF);
            }
            this.w += n2 * 2;
            this.u = Math.max(this.u, this.w);
            return n2;
        }
        if (!ji.io.ac.aw) {
            ji.io.ac.aw = true;
            throw new Exception(String.valueOf(String.valueOf(new StringBuffer("read([]): ").append(ji.util.d.b(265, this.ax)).append(" (").append(this.x).append(")"))));
        }
        return 0;
    }
    
    public final void a(long min) throws Exception {
        min = Math.min(min, this.w());
        if (this.r) {
            try {
                this.w = (int)min;
                this.u = Math.max(this.u, this.w);
                if (this.ac.c() != this.w) {
                    this.ac.a(min);
                }
            }
            catch (Exception ex) {}
        }
        else {
            if (this.s) {
                throw new Exception(String.valueOf(String.valueOf(new StringBuffer("seek(): ").append(ji.util.d.b(299, this.ax)).append(" (").append(this.x).append(")"))));
            }
            if (!this.q) {
                try {
                    this.m.reset();
                    this.m.skip(min);
                    this.w = (int)min;
                    this.u = Math.max(this.u, this.w);
                }
                catch (Exception ex2) {}
            }
            else if (ji.io.ac.h && ji.io.ac.i) {
                try {
                    if (this.z.c() != min) {
                        this.z.a(min);
                    }
                }
                catch (Exception ex3) {}
            }
            else if (!ji.io.ac.aw) {
                ji.io.ac.aw = true;
                throw new Exception(String.valueOf(String.valueOf(new StringBuffer("seek(): ").append(ji.util.d.b(265, this.ax)).append(" (").append(this.x).append(")"))));
            }
        }
    }
    
    public final long q() throws Exception {
        return this.u;
    }
    
    public final long r() throws Exception {
        if (this.r) {
            return this.ac.c();
        }
        if (!this.q || this.s) {
            return this.w;
        }
        if (ji.io.ac.h && ji.io.ac.i) {
            return this.z.c();
        }
        return this.w;
    }
    
    public static final long a(final String s, final String s2) throws Exception {
        try {
            return new u(s, s2, true).g();
        }
        catch (Exception ex) {
            return 0L;
        }
    }
    
    public final boolean s() {
        return this.x != null && this.x.toLowerCase().indexOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.io.q.b()))).append(".").append(ji.io.q.a())))) >= 0;
    }
    
    public final int t() throws Exception {
        if (this.s()) {
            final long r = this.r();
            this.a(this.w() - 4);
            final int p = this.p();
            this.a(r);
            if (p > 0 && p < 10240) {
                return p;
            }
        }
        return 0;
    }
    
    public final void u() throws Exception {
        if (this.s()) {
            this.a(this.v());
        }
    }
    
    public final long v() throws Exception {
        if (this.s()) {
            final long n = this.t();
            if (n > 0) {
                return this.w() - 4 - n;
            }
        }
        return this.w();
    }
    
    public final long w() throws Exception {
        if (ji.io.ac.av) {
            return this.w;
        }
        if (this.r) {
            if (this.ac != null) {
                return this.ac.d();
            }
            return 0L;
        }
        else {
            if (this.s) {
                throw new Exception(String.valueOf(String.valueOf(new StringBuffer("length(): ").append(ji.util.d.b(299, this.ax)).append(" (").append(this.x).append(")"))));
            }
            if (!this.q) {
                if (this.o != null) {
                    return this.o.length;
                }
                return 0L;
            }
            else {
                if (ji.io.ac.h && ji.io.ac.i && this.z != null) {
                    return this.z.d();
                }
                return this.w;
            }
        }
    }
    
    public final void x() {
        try {
            if (this.r() < this.w()) {
                this.a(this.w());
            }
        }
        catch (Exception ex) {}
    }
    
    public final void b(final byte[] array, final int n, final int n2) throws Exception {
        this.w += n2;
        this.u = Math.max(this.u, this.w);
        if (ji.io.ac.h && ji.io.ac.i) {
            if (this.ai && n2 > 0) {
                final byte[] array2 = new byte[n2];
                for (int i = 0; i < n2; ++i) {
                    array2[i] = (byte)(255 - (array[i + n] & 0xFF));
                }
                if (this.s) {
                    this.ab.write(array2, 0, n2);
                }
                else {
                    this.z.write(array2, 0, n2);
                }
            }
            else if (this.s) {
                this.ab.write(array, n, n2);
            }
            else {
                this.z.write(array, n, n2);
            }
        }
        else {
            this.l.write(array, n, n2);
        }
    }
    
    public void y() throws Exception {
        if (ji.io.ac.h && ji.io.ac.i) {
            if (this.ai) {
                if (this.s) {
                    this.ab.flush();
                }
            }
            else if (this.s) {
                this.ab.flush();
            }
        }
        else {
            this.l.flush();
        }
    }
    
    public final void a(final byte b) throws Exception {
        ++this.w;
        this.u = Math.max(this.u, this.w);
        if (ji.io.ac.h && ji.io.ac.i) {
            if (this.ai) {
                final byte b2 = (byte)(255 - (b & 0xFF));
                if (this.s) {
                    this.ab.write(b2);
                }
                else {
                    this.z.write(b2);
                }
            }
            else if (this.s) {
                this.ab.write(b);
            }
            else {
                this.z.write(b);
            }
        }
        else {
            this.l.write(b);
        }
    }
    
    public final void b(final byte[] array) throws Exception {
        this.w += array.length;
        this.u = Math.max(this.u, this.w);
        if (ji.io.ac.h && ji.io.ac.i) {
            if (this.ai && array.length > 0) {
                final byte[] array2 = new byte[array.length];
                for (int i = 0; i < array.length; ++i) {
                    array2[i] = (byte)(255 - (array[i] & 0xFF));
                }
                if (this.s) {
                    this.ab.write(array2);
                }
                else {
                    this.z.write(array2);
                }
            }
            else if (this.s) {
                this.ab.write(array);
            }
            else {
                this.z.write(array);
            }
        }
        else if (this.ai && !this.v && array.length > 0) {
            final byte[] array3 = new byte[array.length];
            for (int j = 0; j < array.length; ++j) {
                array3[j] = (byte)(255 - (array[j] & 0xFF));
            }
            this.l.write(array3);
        }
        else {
            this.l.write(array);
        }
    }
    
    public final void b(final long[] array) throws Exception {
        this.b(array, 0, array.length);
    }
    
    public final void b(final long[] array, final int n, final int n2) throws Exception {
        final int[] array2 = new int[n2 * 2];
        int n3 = 0;
        for (final long n4 : array) {
            array2[n3++] = (int)(n4 & 0xFFFF0000) >> 32;
            array2[n3++] = (int)(n4 & 0xFFFF);
        }
        this.b(array2);
    }
    
    public final void b(final long n) throws Exception {
        final int n2 = (int)(n & 0xFFFF0000) >> 32;
        final int n3 = (int)(n & 0xFFFF);
        this.b(n2);
        this.b(n3);
    }
    
    public final void b(final int n) throws Exception {
        int n2 = n;
        if (ji.io.ac.h && ji.io.ac.i) {
            if (this.s) {
                final byte[] array = { (byte)(n2 >> 24 & 0xFF), (byte)(n2 >> 16 & 0xFF), (byte)(n2 >> 8 & 0xFF), (byte)(n2 & 0xFF) };
                if (this.ai) {
                    array[0] = (byte)(255 - array[0] & 0xFF);
                    array[1] = (byte)(255 - array[1] & 0xFF);
                    array[2] = (byte)(255 - array[2] & 0xFF);
                    array[3] = (byte)(255 - array[3] & 0xFF);
                }
                this.ab.write(array);
                this.w += 4;
                this.u = Math.max(this.u, this.w);
            }
            else {
                if (this.ai) {
                    n2 = (255 - (n >>> 24 & 0xFF) << 24 | 255 - (n >>> 16 & 0xFF) << 16 | 255 - (n >>> 8 & 0xFF) << 8 | 255 - (n & 0xFF));
                }
                this.z.writeInt(n2);
                this.w += 4;
                this.u = Math.max(this.u, this.w);
            }
        }
        else {
            final byte[] array2 = { (byte)(n2 >> 24 & 0xFF), (byte)(n2 >> 16 & 0xFF), (byte)(n2 >> 8 & 0xFF), (byte)(n2 & 0xFF) };
            if (this.ai) {
                array2[0] = (byte)(255 - array2[0] & 0xFF);
                array2[1] = (byte)(255 - array2[1] & 0xFF);
                array2[2] = (byte)(255 - array2[2] & 0xFF);
                array2[3] = (byte)(255 - array2[3] & 0xFF);
            }
            this.w += 4;
            this.u = Math.max(this.u, this.w);
            this.l.write(array2);
        }
    }
    
    public final void c(final int n) throws Exception {
        int n2 = n;
        if (this.ai) {
            n2 = (255 - (n >>> 8 & 0xFF) << 8 | 255 - (n & 0xFF));
        }
        if (ji.io.ac.h && ji.io.ac.i) {
            this.w += 2;
            this.u = Math.max(this.u, this.w);
            if (this.s) {
                this.ab.write(new byte[] { (byte)(n2 >> 8 & 0xFF), (byte)(n2 & 0xFF) });
            }
            else {
                this.z.writeShort(n2);
            }
        }
        else {
            final byte[] array = { (byte)(n2 >> 8 & 0xFF), (byte)(n2 & 0xFF) };
            this.w += 2;
            this.u = Math.max(this.u, this.w);
            this.l.write(array);
        }
    }
    
    public void b(final int[] array) throws Exception {
        if (array != null) {
            this.b(array, 0, array.length);
        }
    }
    
    public final boolean z() {
        return ji.io.ac.h && ji.io.ac.i && !this.s && this.z != null && this.z.a();
    }
    
    public void b(final int[] array, final int n, final int n2) throws Exception {
        if (array != null) {
            boolean b = true;
            if (ji.io.ac.h && ji.io.ac.i && !this.s && this.z.a()) {
                this.z.b(array, n, n2, this.ai);
                b = false;
            }
            if (b) {
                final byte[] array2 = new byte[n2 * 4];
                int n3 = 0;
                for (int i = 0; i < n2; ++i) {
                    final int n4 = array[n + i];
                    array2[n3] = (byte)(n4 >> 24);
                    array2[n3 + 1] = (byte)(n4 >> 16 & 0xFF);
                    array2[n3 + 2] = (byte)(n4 >> 8 & 0xFF);
                    array2[n3 + 3] = (byte)(n4 & 0xFF);
                    n3 += 4;
                }
                if (this.ai && n2 > 0) {
                    for (int j = 0; j < array2.length; ++j) {
                        array2[j] = (byte)(255 - (array2[j] & 0xFF));
                    }
                }
                if (ji.io.ac.h && ji.io.ac.i) {
                    if (this.s) {
                        this.ab.write(array2);
                    }
                    else {
                        this.z.write(array2);
                    }
                }
                else {
                    this.l.write(array2);
                }
            }
            this.w += n2 * 4;
            this.u = Math.max(this.u, this.w);
        }
    }
    
    public void b(final short[] array, final int n, final int n2) throws Exception {
        if (array != null) {
            final byte[] array2 = new byte[n2 * 2];
            for (int i = 0; i < n2; ++i) {
                final short n3 = array[n + i];
                final int n4 = i * 2;
                array2[n4] = (byte)(n3 >> 8 & 0xFF);
                array2[n4 + 1] = (byte)(n3 & 0xFF);
            }
            if (this.ai && n2 > 0) {
                for (int j = 0; j < array2.length; ++j) {
                    array2[j] = (byte)(255 - (array2[j] & 0xFF));
                }
            }
            if (ji.io.ac.h && ji.io.ac.i) {
                if (this.s) {
                    this.ab.write(array2);
                }
                else {
                    this.z.write(array2);
                }
            }
            else {
                this.l.write(array2);
            }
            this.w += n2 * 2;
            this.u = Math.max(this.u, this.w);
        }
    }
    
    private static final void h(final String s) {
        try {
            if (s == null) {
                ji.io.h.d(s, "jiFile parentId must be specified!");
                ji.util.d.eu();
            }
        }
        catch (Exception ex) {}
    }
    
    private String ac() {
        return this.ax;
    }
    
    public static final void a(final ad ad, final String s, final boolean b, final Object o) throws Exception {
        synchronized (ac.at) {
            Label_0292: {
                try {
                    h(s);
                    if (s == null) {
                        break Label_0292;
                    }
                    f(s);
                    try {
                        if (ac.aq == null) {
                            break Label_0292;
                        }
                        int size = 0;
                        synchronized (ac.ar) {
                            size = ac.aq.size();
                        }
                        // monitorexit(ac.ar)
                        h(s);
                        if (size > 0) {
                            final c c = new c("jiFilesToClose");
                            Enumeration<String> keys = null;
                            synchronized (ac.ar) {
                                keys = ac.aq.keys();
                            }
                            // monitorexit(ac.ar)
                            q.a(ad, s);
                            ac ac = null;
                            while (keys.hasMoreElements()) {
                                try {
                                    final String s2 = keys.nextElement();
                                    if (!s2.endsWith(s)) {
                                        continue;
                                    }
                                    synchronized (ji.io.ac.ar) {
                                        ac = (ac)ji.io.ac.aq.get(s2);
                                    }
                                    // monitorexit(ac.ar)
                                    if (ac == null || b8.a(ad, o, ac.a())) {
                                        continue;
                                    }
                                    if (ac.ac() != null) {
                                        if (!ac.ac().equals(s)) {
                                            continue;
                                        }
                                        c.c(ac);
                                    }
                                    else {
                                        c.c(ac);
                                    }
                                }
                                catch (Exception ex) {
                                    if (!ji.util.d.cy()) {
                                        continue;
                                    }
                                    ex.printStackTrace();
                                }
                            }
                            while (c.b() > 0) {
                                final ac ac2 = (ac)c.b(0);
                                c.d(0);
                                ac2.a(ad);
                            }
                        }
                        break Label_0292;
                    }
                    catch (Exception ex2) {}
                }
                catch (Exception ex3) {
                }
                // monitorexit(ac.at)
            }
        }
    }
    
    public final boolean aa() {
        return this.t;
    }
    
    public final void a(final Object p0) throws Exception {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iconst_0       
        //     2: putfield        ji/io/ac.t:Z
        //     5: iconst_0       
        //     6: istore_2       
        //     7: aload_0        
        //     8: getfield        ji/io/ac.x:Ljava/lang/String;
        //    11: ifnull          68
        //    14: getstatic       ji/util/d.al:Z
        //    17: ifne            68
        //    20: bipush          84
        //    22: invokestatic    ji/util/i.c:(I)Z
        //    25: ifne            34
        //    28: getstatic       ji/io/ac.a:Z
        //    31: ifeq            68
        //    34: aload_0        
        //    35: getfield        ji/io/ac.ax:Ljava/lang/String;
        //    38: ldc             "File: Close : "
        //    40: nop            
        //    41: aload_0        
        //    42: getfield        ji/io/ac.x:Ljava/lang/String;
        //    45: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    48: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    51: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //    54: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    57: getstatic       ji/io/ac.a:Z
        //    60: ifeq            66
        //    63: invokestatic    ji/util/d.eu:()V
        //    66: iconst_1       
        //    67: istore_2       
        //    68: aload_0        
        //    69: getfield        ji/io/ac.m:Ljava/io/DataInputStream;
        //    72: ifnull          82
        //    75: aload_0        
        //    76: getfield        ji/io/ac.m:Ljava/io/DataInputStream;
        //    79: invokevirtual   java/io/FilterInputStream.close:()V
        //    82: aload_0        
        //    83: getfield        ji/io/ac.k:Ljava/io/ByteArrayInputStream;
        //    86: ifnull          100
        //    89: aload_0        
        //    90: getfield        ji/io/ac.k:Ljava/io/ByteArrayInputStream;
        //    93: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //    96: goto            100
        //    99: astore_3       
        //   100: aload_0        
        //   101: getfield        ji/io/ac.l:Ljava/io/ByteArrayOutputStream;
        //   104: ifnull          118
        //   107: aload_0        
        //   108: getfield        ji/io/ac.l:Ljava/io/ByteArrayOutputStream;
        //   111: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   114: goto            118
        //   117: astore_3       
        //   118: aload_0        
        //   119: getfield        ji/io/ac.q:Z
        //   122: ifeq            196
        //   125: aload_0        
        //   126: getfield        ji/io/ac.l:Ljava/io/ByteArrayOutputStream;
        //   129: ifnull          196
        //   132: aload_0        
        //   133: aload_0        
        //   134: getfield        ji/io/ac.l:Ljava/io/ByteArrayOutputStream;
        //   137: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   140: putfield        ji/io/ac.o:[B
        //   143: aload_0        
        //   144: aconst_null    
        //   145: putfield        ji/io/ac.l:Ljava/io/ByteArrayOutputStream;
        //   148: getstatic       ji/io/ac.h:Z
        //   151: ifeq            166
        //   154: invokestatic    ji/util/d.dx:()Z
        //   157: ifeq            166
        //   160: getstatic       ji/io/ac.i:Z
        //   163: ifne            196
        //   166: getstatic       ji/io/ac.b:Ljava/util/Vector;
        //   169: new             Lji/io/ac$so;
        //   172: dup            
        //   173: aload_0        
        //   174: aload_0        
        //   175: getfield        ji/io/ac.x:Ljava/lang/String;
        //   178: aload_0        
        //   179: getfield        ji/io/ac.o:[B
        //   182: aload_0        
        //   183: getfield        ji/io/ac.ax:Ljava/lang/String;
        //   186: invokespecial   ji/io/ac$so.<init>:(Lji/io/ac;Ljava/lang/String;[BLjava/lang/String;)V
        //   189: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //   192: aload_0        
        //   193: invokespecial   ji/io/ac.ad:()V
        //   196: aload_0        
        //   197: getfield        ji/io/ac.y:Lji/sec/au;
        //   200: ifnull          214
        //   203: aload_0        
        //   204: getfield        ji/io/ac.y:Lji/sec/au;
        //   207: invokevirtual   ji/sec/au.e:()V
        //   210: goto            214
        //   213: astore_3       
        //   214: aload_0        
        //   215: getfield        ji/io/ac.ab:Ljava/io/BufferedOutputStream;
        //   218: ifnull          228
        //   221: aload_0        
        //   222: getfield        ji/io/ac.ab:Ljava/io/BufferedOutputStream;
        //   225: invokevirtual   java/io/FilterOutputStream.close:()V
        //   228: aload_0        
        //   229: getfield        ji/io/ac.aa:Lji/sec/aw;
        //   232: ifnull          242
        //   235: aload_0        
        //   236: getfield        ji/io/ac.aa:Lji/sec/aw;
        //   239: invokevirtual   ji/sec/aw.close:()V
        //   242: aload_0        
        //   243: getfield        ji/io/ac.z:Lji/sec/au;
        //   246: ifnull          256
        //   249: aload_0        
        //   250: getfield        ji/io/ac.z:Lji/sec/au;
        //   253: invokevirtual   ji/sec/au.e:()V
        //   256: aload_0        
        //   257: getfield        ji/io/ac.ac:Lji/sec/au;
        //   260: ifnull          270
        //   263: aload_0        
        //   264: getfield        ji/io/ac.ac:Lji/sec/au;
        //   267: invokevirtual   ji/sec/au.e:()V
        //   270: aload_0        
        //   271: getfield        ji/io/ac.ad:Lji/sec/az;
        //   274: ifnull          284
        //   277: aload_0        
        //   278: getfield        ji/io/ac.ad:Lji/sec/az;
        //   281: invokevirtual   ji/sec/az.close:()V
        //   284: jsr             305
        //   287: goto            520
        //   290: astore_3       
        //   291: jsr             305
        //   294: goto            520
        //   297: astore          4
        //   299: jsr             305
        //   302: aload           4
        //   304: athrow         
        //   305: astore          5
        //   307: aload_0        
        //   308: getfield        ji/io/ac.x:Ljava/lang/String;
        //   311: ifnull          453
        //   314: aload_0        
        //   315: getfield        ji/io/ac.q:Z
        //   318: ifeq            332
        //   321: getstatic       ji/io/ac.am:I
        //   324: iconst_1       
        //   325: isub           
        //   326: putstatic       ji/io/ac.am:I
        //   329: goto            340
        //   332: getstatic       ji/io/ac.al:I
        //   335: iconst_1       
        //   336: isub           
        //   337: putstatic       ji/io/ac.al:I
        //   340: getstatic       ji/io/ac.ar:Ljava/lang/Object;
        //   343: astore          6
        //   345: aload           6
        //   347: monitorenter   
        //   348: getstatic       ji/io/ac.aq:Ljava/util/Hashtable;
        //   351: aload_0        
        //   352: aload_0        
        //   353: getfield        ji/io/ac.x:Ljava/lang/String;
        //   356: invokespecial   ji/io/ac.g:(Ljava/lang/String;)Ljava/lang/String;
        //   359: invokevirtual   java/util/Hashtable.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //   362: pop            
        //   363: aload           6
        //   365: monitorexit    
        //   366: goto            378
        //   369: aload           6
        //   371: monitorexit    
        //   372: athrow         
        //   373: goto            378
        //   376: astore          6
        //   378: getstatic       ji/io/ac.ak:I
        //   381: ifle            392
        //   384: getstatic       ji/io/ac.ak:I
        //   387: iconst_1       
        //   388: isub           
        //   389: putstatic       ji/io/ac.ak:I
        //   392: iload_2        
        //   393: ifeq            453
        //   396: aload_0        
        //   397: getfield        ji/io/ac.ax:Ljava/lang/String;
        //   400: new             Ljava/lang/StringBuffer;
        //   403: dup            
        //   404: ldc             "File: : Files open  : Tot="
        //   406: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   409: getstatic       ji/io/ac.ak:I
        //   412: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   415: ldc             "/Read="
        //   417: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   420: getstatic       ji/io/ac.al:I
        //   423: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   426: ldc             "/Write="
        //   428: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   431: getstatic       ji/io/ac.am:I
        //   434: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   437: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   440: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   443: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   446: aload_0        
        //   447: getfield        ji/io/ac.ax:Ljava/lang/String;
        //   450: invokestatic    ji/util/e.a:(Ljava/lang/String;)V
        //   453: aload_0        
        //   454: aconst_null    
        //   455: putfield        ji/io/ac.o:[B
        //   458: aload_0        
        //   459: aconst_null    
        //   460: putfield        ji/io/ac.p:[B
        //   463: aload_0        
        //   464: aconst_null    
        //   465: putfield        ji/io/ac.m:Ljava/io/DataInputStream;
        //   468: aload_0        
        //   469: aconst_null    
        //   470: putfield        ji/io/ac.k:Ljava/io/ByteArrayInputStream;
        //   473: aload_0        
        //   474: aconst_null    
        //   475: putfield        ji/io/ac.l:Ljava/io/ByteArrayOutputStream;
        //   478: aload_0        
        //   479: aconst_null    
        //   480: putfield        ji/io/ac.y:Lji/sec/au;
        //   483: aload_0        
        //   484: aconst_null    
        //   485: putfield        ji/io/ac.ab:Ljava/io/BufferedOutputStream;
        //   488: aload_0        
        //   489: aconst_null    
        //   490: putfield        ji/io/ac.aa:Lji/sec/aw;
        //   493: aload_0        
        //   494: aconst_null    
        //   495: putfield        ji/io/ac.z:Lji/sec/au;
        //   498: aload_0        
        //   499: aconst_null    
        //   500: putfield        ji/io/ac.ac:Lji/sec/au;
        //   503: aload_0        
        //   504: aconst_null    
        //   505: putfield        ji/io/ac.ad:Lji/sec/az;
        //   508: aload_0        
        //   509: aconst_null    
        //   510: putfield        ji/io/ac.x:Ljava/lang/String;
        //   513: goto            518
        //   516: astore          6
        //   518: ret             5
        //   520: getstatic       ji/io/ac.au:Z
        //   523: ifeq            566
        //   526: aload_0        
        //   527: getfield        ji/io/ac.ax:Ljava/lang/String;
        //   530: new             Ljava/lang/StringBuffer;
        //   533: dup            
        //   534: ldc             "File: : Close: Files open for read/wite: "
        //   536: nop            
        //   537: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   540: getstatic       ji/io/ac.al:I
        //   543: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   546: ldc             "/"
        //   548: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   551: getstatic       ji/io/ac.am:I
        //   554: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   557: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   560: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   563: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   566: return         
        //    Exceptions:
        //  throws java.lang.Exception
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  82     96     99     100    Ljava/io/IOException;
        //  100    114    117    118    Ljava/io/IOException;
        //  118    210    213    214    Ljava/lang/Exception;
        //  7      284    290    297    Ljava/lang/Exception;
        //  7      297    297    305    Any
        //  348    363    369    373    Any
        //  340    373    376    378    Ljava/lang/Exception;
        //  453    513    516    518    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0739:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static final void c(final String s) {
        try {
            if (s != null) {
                final so i = i(s);
                if (i != null) {
                    i.d();
                    ac.b.removeElementAt(i.c());
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static final String b(final String s, final String s2) {
        try {
            return new u(s, s2, true).b();
        }
        catch (Exception ex) {
            return s;
        }
    }
    
    public static final boolean c(final String s, final String s2) throws Exception {
        return a(s, s2, false);
    }
    
    public static final boolean a(final String s, final String s2, final boolean b) throws Exception {
        return a(s, s2, b, true);
    }
    
    public static final boolean a(final String s, final String s2, final boolean b, final boolean b2) throws Exception {
        if (s.startsWith("V!RF")) {
            return true;
        }
        boolean k = false;
        c(s);
        try {
            if (d(s, s2)) {
                if (!ji.util.d.al && (ji.util.i.c(84) || ac.a) && ac.a) {
                    ji.util.d.eu();
                }
                final u u = new u(s, s2, true);
                if (u.e() && b) {
                    ji.util.d.a(s, s2, b);
                }
                k = u.k();
            }
            if (b2) {
                q.a(ji.util.d.w(s2), s2).b(s, true);
            }
        }
        catch (Exception ex) {}
        return k;
    }
    
    public static final boolean d(final String s) {
        return false;
    }
    
    public static final void a(final String s, final String s2, final String s3) throws Exception {
        a(s, s2, s3, false);
    }
    
    public static final void a(final String s, final String s2, final String s3, final boolean b) throws Exception {
        final so i = i(s);
        if (i != null) {
            i.a(s2);
        }
        if (ac.h && ac.i) {
            try {
                e(ji.util.d.i(s2, s3), s3);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            Label_0624: {
                if (b) {
                    final ac ac = new ac(s, false, false, 0, ji.util.d.w(s3), s3);
                    ac ac2 = null;
                    OutputStream outputStream;
                    if (ji.util.d.b()) {
                        ac2 = new ac(s2, true, false, 0, ji.util.d.w(s3), s3);
                        outputStream = new a8(ac2, ji.util.d.w(s3));
                    }
                    else {
                        outputStream = new aw(s2, s3);
                    }
                    try {
                        final byte[] array = new byte[102400];
                        int a;
                        for (int n = (int)ac.w(), j = 0; j < n; j += a) {
                            a = ac.a(array);
                            if (a <= 0) {
                                break;
                            }
                            outputStream.write(array, 0, a);
                        }
                        break Label_0624;
                    }
                    finally {
                        try {
                            if (ac != null) {
                                ac.a(ji.util.d.w(s3));
                            }
                        }
                        catch (Exception ex2) {}
                        try {
                            if (outputStream != null) {
                                outputStream.flush();
                                outputStream.close();
                            }
                        }
                        catch (Exception ex3) {}
                        try {
                            if (ac2 != null) {
                                ac2.a(ji.util.d.w(s3));
                            }
                        }
                        catch (Exception ex4) {}
                    }
                }
                final az az = new az(s, s3);
                final aw aw = new aw(s2, s3);
                try {
                    final byte[] array2 = new byte[102400];
                    int read;
                    for (int available = az.available(), k = 0; k < available; k += read) {
                        read = az.read(array2);
                        if (read <= 0) {
                            break;
                        }
                        aw.write(array2, 0, read);
                    }
                }
                finally {
                    try {
                        if (az != null) {
                            az.close();
                        }
                    }
                    catch (Exception ex5) {}
                    try {
                        if (aw != null) {
                            aw.flush();
                            aw.close();
                        }
                    }
                    catch (Exception ex6) {}
                }
            }
            c(s, s3);
        }
    }
    
    public static final void a(final String s, final String s2, final Object o, final String s3) throws Exception {
        a(s, s2, o, s3, true);
    }
    
    public static final void a(final String s, final String s2, final Object o, final String s3, final boolean b) throws Exception {
        a(s, s2, o, s3, b, false);
    }
    
    public static final void a(final String s, final String s2, final Object o, final String s3, final boolean b, final boolean b2) throws Exception {
        if (ac.h && ac.i) {
            try {
                e(ji.util.d.i(s2, s3), s3);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            final ac ac = new ac(s, false, false, 0, false, o, s3);
            final ac ac2 = new ac(s2, true, false, 0, false, o, s3);
            try {
                if (b2) {
                    ji.io.h.d(s3, String.valueOf(String.valueOf(new StringBuffer("copy - ").append(s).append(": enrypted :").append(ac.b(o)))));
                    ji.io.h.d(s3, String.valueOf(String.valueOf(new StringBuffer("...to - ").append(s2).append(": enrypted :").append(ac2.b(o)))));
                }
                final byte[] array = new byte[102400];
                int n = 0;
                long v = (int)ac.w();
                if (b) {
                    v = ac.v();
                }
                while (n < v) {
                    final int a = ac.a(array, 0, (int)Math.min(array.length, v));
                    if (a <= 0) {
                        break;
                    }
                    ac2.b(array, 0, a);
                    n += a;
                }
            }
            finally {
                try {
                    if (ac != null) {
                        ac.a(o);
                    }
                }
                catch (Exception ex2) {}
                try {
                    if (ac2 != null) {
                        ac2.y();
                        ac2.a(o);
                    }
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public static final boolean d(final String s, final String s2) {
        boolean c = false;
        if (s.startsWith("V!RF")) {
            return true;
        }
        if (i(s) != null) {
            c = true;
        }
        else if (ac.h && ji.util.d.dx()) {
            try {
                c = new u(s, s2, true).c();
            }
            catch (Exception ex) {}
        }
        return c;
    }
    
    public static final boolean e(final String s, final String s2) throws Exception {
        return a(s, false, null, s2);
    }
    
    public static final boolean a(final String s, final boolean b, final ac ac, final String s2) throws Exception {
        if (!ac.h) {
            return true;
        }
        final String b2 = ji.util.d.b(ji.util.d.b(s, "\\", "/"), "//", "/");
        final boolean startsWith = b2.startsWith("/");
        final StringTokenizer stringTokenizer = new StringTokenizer(b2, "/");
        if (stringTokenizer.countTokens() > 2) {
            String s3 = stringTokenizer.nextToken();
            boolean b3 = true;
            while (stringTokenizer.hasMoreTokens()) {
                s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append("/").append(stringTokenizer.nextToken())));
                final int index = s3.indexOf("/..");
                if (index >= 0) {
                    final int lastIndex = s3.substring(0, index).lastIndexOf("/");
                    if (lastIndex >= 0) {
                        s3 = String.valueOf(String.valueOf(s3.substring(0, lastIndex))).concat(String.valueOf(String.valueOf(s3.substring(index + 3))));
                    }
                }
                String concat = s3;
                if (startsWith) {
                    concat = "/".concat(String.valueOf(String.valueOf(concat)));
                }
                final u u = new u(concat, s2, true);
                if (!u.c()) {
                    if (ji.util.d.cy()) {
                        ji.io.h.d(s2, "Creating new dir ".concat(String.valueOf(String.valueOf(concat))));
                    }
                    final boolean h = u.h();
                    b3 = (b3 && h);
                    String value = null;
                    if (b && h) {
                        try {
                            value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("/").append("v1.v1")));
                            final aw aw = new aw(value, s2);
                            aw.write("".concat(String.valueOf(String.valueOf(ji.util.d.cd(true)))).getBytes());
                            aw.close();
                        }
                        catch (IOException ex) {}
                    }
                    if (!h || ac == null) {
                        continue;
                    }
                    if (value != null) {
                        ac.b(String.valueOf(String.valueOf(value)).concat("\r\n").getBytes());
                    }
                    ac.b(String.valueOf(String.valueOf(concat)).concat("\r\n").getBytes());
                }
            }
            return b3;
        }
        return new u(b2, s2, true).i();
    }
    
    public static final boolean f(final String s, final String s2) throws Exception {
        return ac.h && new u(s, s2, true).e();
    }
    
    public static final boolean g(final String s, final String s2) throws Exception {
        if (ac.h) {
            return new u(s, s2, true).d();
        }
        return i(s) != null;
    }
    
    public static final String[] h(final String s, final String s2) {
        try {
            return new u(s, s2, true).j();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static final String b(final String s, final String s2, final String s3) {
        final String s4 = "";
        if (s != null && s.indexOf(";") < 0) {
            return s;
        }
        String s5 = null;
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s4, ";");
            if (stringTokenizer.countTokens() < 1) {
                s5 = s;
            }
            else {
                s5 = stringTokenizer.nextToken();
                final String bu = ji.util.d.bu(s3);
                String s6;
                if (!s5.endsWith(bu)) {
                    s6 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s5))).append(bu).append(s2)));
                }
                else {
                    s6 = String.valueOf(String.valueOf(s5)).concat(String.valueOf(String.valueOf(s2)));
                }
                while (!d(s6, s3) && s5 != null) {
                    s5 = stringTokenizer.nextToken();
                    if (s5 != null) {
                        if (!s5.endsWith(bu)) {
                            s6 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s5))).append(bu).append(s2)));
                        }
                        else {
                            s6 = String.valueOf(String.valueOf(s5)).concat(String.valueOf(String.valueOf(s2)));
                        }
                    }
                }
                if (s5 == null) {
                    s5 = s2;
                }
            }
        }
        catch (Exception ex) {}
        return s5;
    }
    
    public static final void e(final String s) {
        try {
            if (ac.b != null) {
                synchronized (ac.b) {
                    while (ac.b.size() > 0) {
                        final so so = ac.b.elementAt(0);
                        if (so.g().equals(s)) {
                            so.d();
                            ac.b.removeElementAt(0);
                        }
                    }
                }
                // monitorexit(ac.b)
            }
        }
        catch (Exception ex) {}
    }
    
    public final void ab() {
        try {
            if (this.m != null) {
                this.m.close();
                this.m = null;
            }
            if (this.k != null) {
                this.k = null;
            }
            if (this.l != null) {
                this.l = null;
            }
            if (this.y != null) {
                this.y.e();
                this.y = null;
            }
            if (this.ab != null) {
                this.ab.close();
                this.ab = null;
            }
            if (this.aa != null) {
                this.aa.close();
                this.aa = null;
            }
            if (this.z != null) {
                this.z.e();
                this.z = null;
            }
            if (this.ac != null) {
                this.ac.e();
                this.ac = null;
            }
            this.o = null;
            this.p = null;
        }
        catch (Exception ex) {}
    }
    
    public void finalize() {
        --ji.io.ac.an;
        if (ji.io.ac.an <= 0) {
            e(this.ax);
            ji.io.ac.b.removeAllElements();
        }
    }
    
    private final void ad() {
        while (ji.io.ac.c > ji.io.ac.d) {
            synchronized (ji.io.ac.b) {
                final so so = ji.io.ac.b.elementAt(0);
                if (so.g().equals(this.ax)) {
                    so.d();
                    ji.io.ac.b.removeElementAt(0);
                }
                // monitorexit(ac.b)
                continue;
            }
            break;
        }
    }
    
    private static final so i(final String s) {
        so so = null;
        try {
            final String lowerCase = s.toLowerCase();
            if (ac.b != null) {
                synchronized (ac.b) {
                    for (int i = 0; i < ac.b.size(); ++i) {
                        if (lowerCase.equals(((so)ac.b.elementAt(i)).a())) {
                            so = (so)ac.b.elementAt(i);
                            so.a(i);
                            break;
                        }
                    }
                }
                // monitorexit(ac.b)
            }
        }
        catch (Exception ex) {}
        return so;
    }
    
    public static void a(final String s, final long n) {
        final m m = new m(new File(s));
        try {
            m.a("setLastModified", new Long(n));
        }
        catch (Exception ex) {
            if (ji.util.d.em()) {}
        }
    }
    
    static {
        ac.a = false;
        ac.b = new Vector();
        ac.c = 0L;
        ac.d = 0L;
        ac.e = true;
        ac.f = false;
        ac.g = false;
        ac.h = true;
        ac.i = true;
        ac.j = 512000L;
        ac.ae = null;
        ac.af = null;
        ac.ak = 0;
        ac.al = 0;
        ac.am = 0;
        ac.an = 0;
        ac.ao = 0;
        ac.aq = null;
        ac.ar = new Object();
        ac.as = null;
        ac.at = new Object();
        ac.au = false;
        ac.av = false;
        ac.aw = false;
    }
    
    class so extends at
    {
        private String a;
        private byte[] b;
        private int c;
        
        public so(final ac ac, final String s, final byte[] b, final String s2) {
            super(s2);
            this.a = null;
            this.b = null;
            this.c = 0;
            ji.io.ac.c += b.length;
            this.a = s.toLowerCase();
            this.b = b;
        }
        
        private String g() {
            return super.c;
        }
        
        public final String a() {
            return this.a;
        }
        
        public final byte[] b() {
            return this.b;
        }
        
        public final void a(final String a) {
            this.a = a;
        }
        
        public final void a(final int c) {
            this.c = c;
        }
        
        public final int c() {
            return this.c;
        }
        
        public final void d() {
            ji.io.ac.c -= this.b.length;
            this.b = null;
        }
    }
}
