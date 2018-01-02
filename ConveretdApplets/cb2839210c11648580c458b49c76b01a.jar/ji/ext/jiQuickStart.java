// 
// Decompiled by Procyon v0.5.30
// 

package ji.ext;

import ji.io.p;
import java.io.FileOutputStream;
import ji.v1event.af;
import java.io.FileNotFoundException;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import ji.res.s;
import ji.awt.bb;
import java.awt.Component;
import ji.res.ay;
import java.net.URL;
import ji.io.h;
import ji.util.i;
import ji.util.e;
import ji.util.d;
import java.util.Vector;
import ji.util.m;
import ji.applet.jiApplet;

public class jiQuickStart implements n
{
    private String a;
    private String b;
    private String c;
    private boolean d;
    private static boolean e;
    private static boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private int j;
    private jiJNIUtil k;
    private jiApplet l;
    private m m;
    private m n;
    private String o;
    static boolean p;
    private static boolean q;
    private static boolean r;
    private tr s;
    private boolean t;
    private static boolean u;
    private static int v;
    private static Vector w;
    private static Object x;
    static String y;
    private static v z;
    private static boolean aa;
    private static boolean ab;
    
    public jiQuickStart() {
        this.d = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = 0;
        this.o = null;
        this.s = null;
        this.t = false;
    }
    
    public static final void a(final String y) {
        jiQuickStart.y = y;
    }
    
    public static final boolean b(final String s) {
        synchronized (jiQuickStart.x) {
            if (jiQuickStart.y != null && s.equals(jiQuickStart.y)) {
                // monitorexit(jiQuickStart.x)
                return false;
            }
            if (jiQuickStart.v > 0) {
                if (jiQuickStart.w != null) {
                    for (int i = 0; i < jiQuickStart.w.size(); ++i) {
                        if (((String)jiQuickStart.w.elementAt(i)).equals(s)) {
                            // monitorexit(jiQuickStart.x)
                            return false;
                        }
                    }
                }
                else {
                    jiQuickStart.w = new Vector(jiQuickStart.v);
                }
                if (jiQuickStart.v > 1) {
                    jiQuickStart.w.addElement(s);
                    --jiQuickStart.v;
                }
                else {
                    while (jiQuickStart.w.size() > 0) {
                        jiQuickStart.w.removeElementAt(0);
                    }
                    jiQuickStart.w = null;
                    jiQuickStart.v = 0;
                }
                // monitorexit(jiQuickStart.x)
                return true;
            }
        }
        // monitorexit(jiQuickStart.x)
        return false;
    }
    
    public static void c(final String s) {
        synchronized (jiQuickStart.x) {
            jiQuickStart.v = d.b2();
            if (jiQuickStart.w != null) {
                while (jiQuickStart.w.size() > 0) {
                    jiQuickStart.w.removeElementAt(0);
                }
                jiQuickStart.w = null;
            }
            b(s);
        }
        // monitorexit(jiQuickStart.x)
    }
    
    public static final boolean d(final String s) {
        boolean b = jiQuickStart.q && ji.util.e.u(s) && !ji.util.e.t(s) && !ji.util.e.v(s) && !d.av(s);
        if (b) {
            final URL am = ji.util.e.am();
            if (am != null) {
                if (d.a(am)) {
                    if (i.c(105) && !jiQuickStart.r) {
                        jiQuickStart.r = true;
                        h.d(s, "Quick-Start not available for Local use");
                    }
                    b = false;
                }
                if (b) {
                    final String a = a(am);
                    if (a != null) {
                        final String c = d.c(s);
                        if (c != null && a.toLowerCase().indexOf(c.toLowerCase()) < 0) {
                            if (i.c(105) && !jiQuickStart.r) {
                                jiQuickStart.r = true;
                                h.d(s, String.valueOf(String.valueOf(new StringBuffer("Quick-Start not available for mismatched domains (").append(am).append(" vs. ").append(c).append(")"))));
                            }
                            b = false;
                        }
                    }
                }
            }
        }
        return b;
    }
    
    private static final String a(final URL url) {
        String substring = null;
        if (url != null) {
            final String string = url.toString();
            final int index = string.indexOf("//");
            if (index >= 0) {
                final int index2 = string.indexOf("/", index + 2);
                if (index2 >= 0) {
                    final String substring2 = string.substring(index + 2, index2);
                    final int index3 = substring2.indexOf(".");
                    if (index3 >= 0) {
                        substring = substring2.substring(index3 + 1);
                    }
                }
            }
        }
        return substring;
    }
    
    public static final boolean a() {
        return jiQuickStart.p;
    }
    
    private final void i() {
        this.a = ay.d();
        this.b = ay.a();
        final jiApplet l = this.l;
        if (l != null) {
            this.c = l.getParameterWithDefault("filenetSystem", "");
        }
        else {
            this.c = "";
        }
    }
    
    public jiQuickStart(final jiApplet l, final String o, final boolean b, final boolean b2) throws Exception {
        this.d = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = 0;
        this.o = null;
        this.s = null;
        this.t = false;
        this.l = l;
        this.o = o;
        if (!l.isMainQuickstartWindow() && !b) {
            this.d(false);
        }
        this.m();
        a(o, "QSHelper: Launcher = ".concat(String.valueOf(String.valueOf(b2))));
        if (!b2) {
            this.n();
        }
    }
    
    public final void a(final boolean t) {
        this.t = t;
    }
    
    public boolean a(final boolean b, final boolean b2) {
        final jiApplet l = this.l;
        boolean b3 = false;
        boolean b4 = false;
        try {
            if (!this.b(!b)) {
                if (this.c()) {
                    final int a = a(this.o, l);
                    if (a > 0) {
                        this.k = new jiJNIUtil(this.o, l);
                        try {
                            if (ji.util.i.c(105)) {
                                this.c(true);
                            }
                            a(this.o, "StartQS: Launcher = ".concat(String.valueOf(String.valueOf(b))));
                            if (b) {
                                if (this.m()) {
                                    a(this.o, "launching: getting URL...");
                                    final String d = this.d(true);
                                    a(this.o, "launching: URL: ".concat(String.valueOf(String.valueOf(d))));
                                    if (d != null) {
                                        a(this.o, "launching:QS...");
                                        final Object a2 = this.n.a("launchQuickstartWindow", new Integer(a), this, d);
                                        a(this.o, "launch result: ".concat(String.valueOf(String.valueOf(a2))));
                                        if (a2 != null) {
                                            boolean b5 = false;
                                            if (a2 instanceof Boolean && (boolean)a2) {
                                                b5 = true;
                                            }
                                            if (b5) {
                                                if (!b2) {
                                                    int n = 30000;
                                                    final int n2 = 1000;
                                                    boolean b6 = false;
                                                    while (n > 0 && !(b6 = e(this.o)) && !this.t && !ji.util.d.c5()) {
                                                        ji.util.d.b(n2, 4397, this.o);
                                                        n -= n2;
                                                    }
                                                    if (!this.t) {
                                                        if (!b6) {
                                                            for (int n3 = 5000; n3 > 0 && !(b6 = e(this.o)) && !this.t && !ji.util.d.c5(); n3 -= n2) {
                                                                ji.util.d.b(n2, 4398, this.o);
                                                            }
                                                        }
                                                        if (b6) {
                                                            this.j = a;
                                                            b3 = true;
                                                        }
                                                        else {
                                                            ji.io.h.d(this.o, "Quick-start: time-out during start-up");
                                                        }
                                                    }
                                                }
                                                else {
                                                    this.j = a;
                                                    b3 = true;
                                                }
                                            }
                                            else {
                                                ji.io.h.d(this.o, "Quick-start: unable to launch quickstart window");
                                            }
                                        }
                                        else {
                                            ji.io.h.d(this.o, "Quick-start: unable to launch quickstart window");
                                        }
                                    }
                                    else {
                                        ji.io.h.d(this.o, "Quick-start: unable to retrieve quickstart URL");
                                    }
                                }
                                else {
                                    ji.io.h.d(this.o, "Quick-start: Unable to load Ext library!");
                                }
                            }
                            else if (this.n()) {
                                a(this.o, "starting QS");
                                final int quickstartLaunchHandle = l.getQuickstartLaunchHandle();
                                a(this.o, "using launchwindowhandle of ".concat(String.valueOf(String.valueOf(quickstartLaunchHandle))));
                                final Object a3 = this.m.a("lockWindow", l, new Integer(a), new Integer(quickstartLaunchHandle), this, this.k);
                                if (a3 != null) {
                                    if (a3 instanceof Boolean) {
                                        if (a3) {
                                            this.j = a;
                                            b3 = true;
                                        }
                                        else {
                                            ji.io.h.d(this.o, "Quick-start: lock window returned false");
                                        }
                                    }
                                    else {
                                        ji.io.h.d(this.o, "Quick-start: unable to lock window (no boolean)");
                                        if (this.m != null) {
                                            this.m.a();
                                        }
                                    }
                                }
                                else {
                                    ji.io.h.d(this.o, "Quick-start: unable to lock window (null result)");
                                    if (this.m != null) {
                                        this.m.a();
                                    }
                                }
                            }
                            else {
                                ji.io.h.d(this.o, "Quick-start: Unable to load QS library!");
                                if (this.m != null) {
                                    this.m.a();
                                }
                            }
                        }
                        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                            ji.io.h.d(this.o, "Quick-start: UnsatisfiedLinkError when trying to use method.");
                            if (this.m != null) {
                                this.m.a();
                            }
                        }
                    }
                    else {
                        ji.io.h.d(this.o, "Quick-start: Could not obtain parent window handle, not installing shutdown callback");
                        if (this.m != null) {
                            this.m.a();
                        }
                    }
                }
                else {
                    a(this.o, "quickstart dependency check failed");
                    b3 = false;
                }
            }
            else {
                a(this.o, "quickstart is already running");
                b3 = true;
                b4 = true;
            }
            this.d = false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if ((!b3 || b4) && l.isMainQuickstartWindow()) {
                if (!b3) {
                    a(this.o, "due to a problem starting quick start, we are closing the quick start browser window");
                }
                else {
                    a(this.o, "already running, closing the quick start browser window");
                }
                try {
                    if (this.n != null) {
                        final int a4 = a(this.o, l);
                        final int quickstartLaunchHandle2 = l.getQuickstartLaunchHandle();
                        a(this.o, String.valueOf(String.valueOf(new StringBuffer("close decision local=").append(a4).append(", launchingWindowHandle=").append(quickstartLaunchHandle2))));
                        if (a4 > 0) {
                            this.n.a("closeWindow", new Integer(a4), new Integer(quickstartLaunchHandle2));
                        }
                        else {
                            a(this.o, "the launch window is the same as the qs window, not closing browser window");
                        }
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
            else if (b3 && b2) {
                this.j();
            }
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        return b3;
    }
    
    public boolean openWindow(final String s) {
        boolean b = false;
        try {
            this.l.getAppletContext().showDocument(new URL(s), "_blank");
            b = true;
        }
        catch (Exception ex) {}
        return b;
    }
    
    private final void j() {
        if (this.s == null && !this.t) {
            try {
                this.s = new tr("\\\\.\\pipe\\v1trayqueue", this.o);
                new bb(this.o, this.s).start();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void b() {
        try {
            if (this.s != null) {
                this.s.a();
                this.s = null;
            }
        }
        catch (Exception ex) {}
    }
    
    private String d(final boolean b) {
        try {
            if (this.l != null) {
                if (!b) {
                    a(this.o, "retrieving URL without HTML test");
                }
                a(this.o, "URL = ".concat(String.valueOf(String.valueOf(this.l.getQuickstartURL()))));
                if (this.l.getQuickstartURL() != null) {
                    final URL url = new URL(this.l.getQuickstartURL());
                    if (b) {
                        return this.j(url.toString());
                    }
                    return url.toString();
                }
                else {
                    final Object b2 = ji.util.d.b(this.l.getCodeBase(), "quickstart.htm", this.o);
                    if (b2 != null) {
                        if (b2 instanceof URL) {
                            if (b) {
                                return this.j(((URL)b2).toString());
                            }
                            return ((URL)b2).toString();
                        }
                        else {
                            this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1106))).concat("\n"))).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(b2)))))));
                        }
                    }
                    else {
                        this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1106))).concat("\n"))).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(b2)))))));
                    }
                }
            }
            return null;
        }
        catch (Exception ex) {
            this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1108))).concat("\n"))).concat(String.valueOf(String.valueOf(ex.toString()))));
            return null;
        }
    }
    
    private final String a(final int n) {
        return ji.res.s.a(n, this.o);
    }
    
    private final String j(String s) {
        try {
            a(this.o, "QuickStart testing URL: ".concat(String.valueOf(String.valueOf(s))));
            if (ji.util.d.bj(s)) {
                final URLConnection a = ji.util.d.a(new URL(s), true, this.o);
                if (a != null) {
                    final StringBuffer a2 = this.a(a);
                    if (a2 != null) {
                        final String string = a2.toString();
                        if (string != null) {
                            int n = 1;
                            if (string.toLowerCase().indexOf("applet") < 0) {
                                n = 0;
                            }
                            if (n != 0) {
                                a(this.o, "Applet tag found in HTML");
                                if (string.toLowerCase().indexOf("mainquickstartwindow") < 0) {
                                    n = 0;
                                }
                                if (n != 0) {
                                    try {
                                        a(this.o, "mainQuickstartWindow parameter found in HTML");
                                        String value;
                                        if (ji.util.d.em()) {
                                            value = "cabbase";
                                        }
                                        else {
                                            value = "archive";
                                        }
                                        int n2 = string.toLowerCase().indexOf(String.valueOf(String.valueOf(new StringBuffer("\"").append(value).append("\""))));
                                        if (n2 < 0) {
                                            n2 = string.toLowerCase().indexOf(value);
                                        }
                                        else {
                                            value = String.valueOf(String.valueOf(new StringBuffer("\"").append(value).append("\"")));
                                        }
                                        if (n2 >= 0) {
                                            String b = null;
                                            final int index = string.toLowerCase().indexOf("\"", n2 + value.length());
                                            if (index >= 0) {
                                                final int index2 = string.toLowerCase().indexOf("\"", index + 1);
                                                if (index2 >= 0) {
                                                    final String substring = string.substring(index + 1, index2);
                                                    final String el = ji.util.d.el();
                                                    final String c = ji.util.d.c(substring, true);
                                                    final String c2 = ji.util.d.c(el, true);
                                                    final String s2 = c;
                                                    final String bc = ji.util.d.bc(c);
                                                    final String bc2 = ji.util.d.bc(c2);
                                                    b = ji.util.d.b(bc, " ", "");
                                                    final String b2 = ji.util.d.b(bc2, " ", "");
                                                    boolean b3 = false;
                                                    if (!ji.util.d.by(b) && !ji.util.d.by(b2) && b.toLowerCase().equals(b2.toLowerCase())) {
                                                        b3 = true;
                                                    }
                                                    if (!b3) {
                                                        n = 0;
                                                        ji.io.h.d(this.o, "Quick-start: Different archive values for Quick-start and this viewer instance!");
                                                        ji.io.h.d(this.o, String.valueOf(String.valueOf(new StringBuffer("Quick-start: Quick-start archive = \"").append(b).append("\""))));
                                                        ji.io.h.d(this.o, String.valueOf(String.valueOf(new StringBuffer("Quick-start: Viewer archive = \"").append(b2).append("\""))));
                                                        a(this.o, "HTML = ".concat(String.valueOf(String.valueOf(string))));
                                                        this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1109))).concat("\n"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(1142)))).append(" \"").append(c2).append("\"\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(1143)))).append(" \"").append(s2).append("\"\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1144))).concat("\n")))))).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(s)))))));
                                                    }
                                                }
                                            }
                                            if (b == null) {
                                                n = 0;
                                                ji.io.h.d(this.o, "Quick-start: No archive value!");
                                                a(this.o, "HTML = ".concat(String.valueOf(String.valueOf(string))));
                                                final String concat = String.valueOf(String.valueOf(this.a(1109))).concat("\n");
                                                String s3;
                                                if (ji.util.d.em()) {
                                                    s3 = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1141))).concat("\n"))));
                                                }
                                                else {
                                                    s3 = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1140))).concat("\n"))));
                                                }
                                                this.k(String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(s)))))));
                                            }
                                        }
                                        else {
                                            n = 0;
                                            ji.io.h.d(this.o, "Quick-start: Archive paramater NOT found in HTML!");
                                            a(this.o, "HTML = ".concat(String.valueOf(String.valueOf(string))));
                                            final String concat2 = String.valueOf(String.valueOf(this.a(1109))).concat("\n");
                                            String s4;
                                            if (ji.util.d.em()) {
                                                s4 = String.valueOf(String.valueOf(concat2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1141))).concat("\n"))));
                                            }
                                            else {
                                                s4 = String.valueOf(String.valueOf(concat2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1140))).concat("\n"))));
                                            }
                                            this.k(String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(s)))))));
                                        }
                                    }
                                    catch (Exception ex) {
                                        n = 0;
                                        ji.io.h.d(this.o, "Quick-start: ".concat(String.valueOf(String.valueOf(ex))));
                                        a(this.o, "HTML = ".concat(String.valueOf(String.valueOf(string))));
                                        this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1109))).concat("\n"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("(").append(ex).append(")\n")))))))).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(s)))))));
                                    }
                                }
                                else {
                                    n = 0;
                                    ji.io.h.d(this.o, "Quick-start: mainQuickstartWindow paramater NOT found in HTML!");
                                    a(this.o, "HTML = ".concat(String.valueOf(String.valueOf(string))));
                                    this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1109))).concat("\n"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1110))).concat("\n")))))).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(s)))))));
                                }
                            }
                            else {
                                n = 0;
                                ji.io.h.d(this.o, "Quick-start: Applet tag NOT found in HTML!");
                                a(this.o, "HTML = ".concat(String.valueOf(String.valueOf(string))));
                                this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1109))).concat("\n"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1111))).concat("\n")))))).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(s)))))));
                            }
                            if (n == 0) {
                                s = null;
                            }
                            else {
                                a(this.o, "QuickStart URL and HTML file is OK.");
                            }
                        }
                        else {
                            this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(1106)))).append("\n").append(this.a(1107)).append("\n"))))).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(s)))))));
                            s = null;
                        }
                    }
                    else {
                        this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(1106)))).append("\n").append(this.a(1107)).append("\n"))))).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(s)))))));
                        s = null;
                    }
                }
                else {
                    this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(1106)))).append("\n").append(this.a(1107)).append("\n"))))).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(s)))))));
                    s = null;
                }
            }
            else {
                this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1106))).concat("\n"))).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(s)))))));
                s = null;
            }
        }
        catch (Exception ex2) {
            this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1106))).concat("\n"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("Error: ").append(ex2.toString()).append("\n")))))))).concat(String.valueOf(String.valueOf("URL = ".concat(String.valueOf(String.valueOf(s)))))));
            s = null;
            ex2.printStackTrace();
        }
        return s;
    }
    
    private final StringBuffer a(final URLConnection urlConnection) {
        StringBuffer sb = null;
        try {
            final InputStream inputStream = urlConnection.getInputStream();
            final byte[] array = new byte[2048];
            for (int i = inputStream.read(array); i >= 0; i = inputStream.read(array)) {
                if (sb == null) {
                    sb = new StringBuffer(new String(array, 0, i));
                }
                else {
                    sb.append(new String(array, 0, i));
                }
            }
        }
        catch (Exception ex) {}
        return sb;
    }
    
    private boolean k() {
        try {
            boolean b = false;
            final String l = this.l();
            if (l != null) {
                if (this.l(l) != null) {
                    a(this.o, "querying whether we're in startup folder, exe=".concat(String.valueOf(String.valueOf(l))));
                    this.i();
                    final Object a = this.n.a("installedInStartup", this.a, this.b, l);
                    if (a != null && a instanceof Boolean && (boolean)a) {
                        b = true;
                    }
                }
                else {
                    a(this.o, "problem resolving browser location");
                }
            }
            else {
                a(this.o, "couldn't determine browser exe");
            }
            a(this.o, "Ex tells us installed =".concat(String.valueOf(String.valueOf(b))));
            return b;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean b(final boolean b) {
        boolean e = false;
        try {
            if (!b) {
                e = e(this.o);
            }
            else if (this.m != null) {
                final Object c = this.m.c("isRunning");
                if (c != null && c instanceof Boolean && (boolean)c) {
                    e = true;
                }
            }
            a(this.o, "QS running: ".concat(String.valueOf(String.valueOf(e))));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return jiQuickStart.u = e;
    }
    
    public static final boolean e(final String s) {
        boolean b = false;
        try {
            if (!i(s)) {
                try {
                    final FileInputStream fileInputStream = new FileInputStream("\\\\.\\PIPE\\v1runpipe");
                    if (fileInputStream != null) {
                        final DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                        b = true;
                        dataInputStream.close();
                        fileInputStream.close();
                        a(s, "QS is running...");
                    }
                }
                catch (Exception ex) {
                    if (ex instanceof FileNotFoundException) {
                        a(s, "QS is not running...");
                    }
                    else {
                        ex.printStackTrace();
                    }
                }
            }
            a(s, "QS running: ".concat(String.valueOf(String.valueOf(b))));
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return b;
    }
    
    public boolean c() {
        boolean b = false;
        try {
            if (this.n != null) {
                final Object c = this.n.c("checkOLEACCDependency");
                if (c != null && c instanceof Boolean && (boolean)c) {
                    b = true;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    public boolean a(final Object[] array) throws Exception {
        if (this.i) {
            return true;
        }
        boolean b = false;
        if (this.m != null) {
            final Object c = this.m.c("checkLibrary");
            boolean b2 = false;
            if (c != null) {
                if (c instanceof Boolean && (boolean)c) {
                    b = true;
                    b2 = true;
                    this.g = true;
                }
            }
            else {
                ji.io.h.d(this.o, "Quick-start: Library failed to load!");
            }
            a(this.o, "Library test complete, result=".concat(String.valueOf(String.valueOf(b2))));
        }
        else {
            b = true;
        }
        this.h = true;
        return b;
    }
    
    public void c(final boolean b) {
        try {
            if (this.m != null) {
                this.m.a("setDebugging", new Boolean(b));
            }
            else {
                a(this.o, "jiqsInvoker object is null");
            }
            if (this.n != null) {
                this.n.a("setDebugging", new Boolean(b));
            }
            else {
                a(this.o, "jiex1Invoker object is null");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean f(final String s) {
        boolean b = false;
        if (ji.util.i.c(105)) {
            ji.io.h.d(s, "Quick-start: installStartup");
        }
        try {
            a(s, "ready to install");
            if (this.k()) {
                a(s, "already installed in startup");
                b = true;
            }
            else {
                a(s, "not currently installed, attempting to install");
                if (!this.f()) {
                    a(s, "couldn't install in startup");
                }
                else {
                    a(s, "installed in startup");
                    b = true;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            b = false;
        }
        if (ji.util.i.c(105)) {
            ji.io.h.d(s, "Quick-start: installStartup success = ".concat(String.valueOf(String.valueOf(b))));
        }
        return b;
    }
    
    public boolean d() {
        if (!this.h) {
            try {
                this.g = this.a((Object[])null);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.h = true;
        }
        a(this.o, "asked if we're OK, returning ".concat(String.valueOf(String.valueOf(this.g))));
        return this.g;
    }
    
    public boolean e() {
        final String l = this.l();
        if (l != null) {
            final String substring = l.substring(l.lastIndexOf(92) + 1, l.length());
            if (substring.equalsIgnoreCase("iexplore.exe") || substring.equalsIgnoreCase("iexplorer.exe") || substring.equalsIgnoreCase("explorer.exe")) {
                return true;
            }
            if (substring.equalsIgnoreCase("firefox.exe")) {
                return true;
            }
            if (substring.equalsIgnoreCase("netscp.exe") || substring.equalsIgnoreCase("netscape.exe")) {
                return true;
            }
            if (substring.equalsIgnoreCase("mozilla.exe")) {
                return true;
            }
            if (substring.equalsIgnoreCase("opera.exe")) {
                return false;
            }
        }
        else {
            a(this.o, "couldn't determine browser exe");
        }
        return false;
    }
    
    private final void k(final String s) {
        try {
            if (!jiQuickStart.p) {
                ji.io.h.d(this.o, "Quick-start: user message: ".concat(String.valueOf(String.valueOf(s))));
                jiQuickStart.p = true;
                if (this.l.isMainQuickstartWindow()) {
                    ji.util.d.a(s, (af)null, this.o);
                    jiQuickStart.p = false;
                }
                else {
                    ji.util.d.a(ay.a(), s, this.l, 120, null, null, this.o);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected boolean f() {
        try {
            final String d = this.d(false);
            if (d == null) {
                ji.io.h.d(this.o, "Quick-start: Empty URL?");
                this.k(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a(1112))).concat("\n"))).concat(String.valueOf(String.valueOf(this.a(1113)))));
                return false;
            }
            String s = this.j(d);
            if (s == null) {
                if (!a()) {
                    ji.io.h.d(this.o, "Quick-start: Unable to determine browser");
                }
                return false;
            }
            final String l = this.l();
            if (l == null) {
                ji.io.h.d(this.o, "Quick-start: Unable to setup start-folder shortcut due to invalue URL");
                return false;
            }
            final String i = this.l(l);
            if (i != null) {
                this.i();
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.b))).append(" ").append(this.a(1118)).append(" ").append(i)));
                a(this.o, String.valueOf(String.valueOf(new StringBuffer("installing shortcut in startup folder, name=").append(value).append(", exe=").append(l).append(", url=").append(s))));
                if (this.a.toLowerCase().indexOf("daeja") == -1) {
                    s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("?resCompany=").append(this.a.replace(' ', '+')).append("&resProduct=").append(this.b.replace(' ', '+')).append("&resProductId=").append(this.c.replace(' ', '+'))));
                }
                final Object a = this.n.a("addToStartup", this.a, this.b, value, l, s);
                return a != null && a instanceof Boolean && (boolean)a;
            }
            ji.io.h.d(this.o, "Quick-start: Unable to resolve browser location");
            return false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private String l(final String s) {
        try {
            String s2 = s.substring(s.lastIndexOf(92) + 1, s.length());
            if (s2.equalsIgnoreCase("iexplore.exe") || s2.equalsIgnoreCase("iexplorer.exe") || s2.equalsIgnoreCase("explorer.exe")) {
                s2 = this.a(1133);
            }
            else if (s2.equalsIgnoreCase("firefox.exe")) {
                s2 = this.a(1134);
            }
            else if (s2.indexOf(46) > -1) {
                s2 = s2.substring(0, s2.lastIndexOf(46));
            }
            return s2;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private String l() {
        String s = null;
        try {
            if (!ji.util.d.av(this.o) && this.n != null) {
                final Object c = this.n.c("getBrowserExe");
                "test ".concat(String.valueOf(String.valueOf(c)));
                if (c != null && c instanceof String) {
                    s = (String)c;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return s;
    }
    
    public boolean g() {
        return this.k();
    }
    
    public boolean g(final String s) {
        if (ji.util.i.c(105)) {
            ji.io.h.d(s, "Quick-start: removeStartup");
        }
        boolean b = false;
        if (this.h()) {
            b = true;
        }
        if (ji.util.i.c(105)) {
            ji.io.h.d(s, "Quick-start: removeStartup success = ".concat(String.valueOf(String.valueOf(b))));
        }
        return b;
    }
    
    protected boolean h() {
        boolean b = false;
        a(this.o, "removing shortcuts in startup folder");
        try {
            this.i();
            final Object a = this.n.a("removeFromStartup", this.a, this.b);
            if (a != null && a instanceof Boolean && (boolean)a) {
                b = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    public boolean h(final String s) {
        a(s, "releaseResources(String parentId) called");
        try {
            this.o();
            return true;
        }
        catch (Exception ex) {
            a(s, "An exception occurred when releasing resources: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
            return false;
        }
    }
    
    private boolean m() throws Exception {
        if (!jiQuickStart.e) {
            try {
                a(this.o, "Loading Ex library....");
                if (ji.util.e.u(this.o) && ji.util.i.c(7) && ji.util.e.t()) {
                    jiQuickStart.e = a(this.l, null, this.o);
                }
                a(this.o, "Ex loaded =".concat(String.valueOf(String.valueOf(jiQuickStart.e))));
            }
            catch (Exception ex) {
                a(this.o, "installation of Ext failed: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                jiQuickStart.e = false;
            }
        }
        else {
            a(this.o, "Ext is already loaded");
        }
        a(this.o, "initExtLibrary() returning ".concat(String.valueOf(String.valueOf(jiQuickStart.e))));
        if (jiQuickStart.e) {
            this.n = new m(ji.util.d.a2("daeja.ji.jiex1"));
            if (this.n != null) {
                if (ji.util.i.c(105)) {
                    this.n.a("setDebugging", new Boolean(true));
                }
            }
            else {
                jiQuickStart.e = false;
                ji.io.h.d(this.o, "Quick-start: Unable to load EX wrapper");
            }
        }
        return jiQuickStart.e;
    }
    
    private boolean n() throws Exception {
        Label_0141: {
            if (!jiQuickStart.f) {
                try {
                    try {
                        if (ji.util.d.lx && !jiQuickStart.f) {
                            a(this.o, "Loading jiqs....");
                            this.i = true;
                            jiQuickStart.f = ji.ext.w.a(this.l, this.o, null, this, "jiqs", 767, 1, false);
                            this.i = false;
                            a(this.o, "jiqs loaded =".concat(String.valueOf(String.valueOf(jiQuickStart.f))));
                        }
                        break Label_0141;
                    }
                    catch (Exception ex2) {
                        this.i = false;
                        jiQuickStart.f = false;
                    }
                }
                catch (Exception ex) {
                    a(this.o, "installation of library failed: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                    jiQuickStart.f = false;
                    break Label_0141;
                }
            }
            a(this.o, "lib is already loaded");
        }
        a(this.o, "initQSLibrary() returning ".concat(String.valueOf(String.valueOf(jiQuickStart.f))));
        if (jiQuickStart.f) {
            this.m = new m(ji.util.d.a2("daeja.ji.jiqs"));
            if (this.m != null) {
                if (ji.util.i.c(105)) {
                    this.m.a("setDebugging", new Boolean(true));
                }
                this.m.a(true);
            }
            else {
                jiQuickStart.f = false;
                ji.io.h.d(this.o, "Quick-start: Unable to load QS wrapper");
            }
        }
        if (jiQuickStart.f) {
            if (!this.a((Object[])null)) {
                ji.io.h.d(this.o, "Quick-start: Library failed start-up test");
                jiQuickStart.f = false;
            }
            else {
                a(this.o, "QS library passed start-up test");
            }
        }
        return jiQuickStart.f;
    }
    
    public static final int a(final String s, final Component component) {
        if (jiQuickStart.z == null) {
            jiQuickStart.z = new v();
            jiQuickStart.ab = jiQuickStart.z.b(component, s, null);
        }
        if (jiQuickStart.z != null && jiQuickStart.ab) {
            final boolean a = jiQuickStart.z.a(i.c(105) || i.c(124));
            final String g = jiQuickStart.z.g("Quick-start");
            try {
                return jiQuickStart.z.a(s, component);
            }
            finally {
                jiQuickStart.z.a(a);
                jiQuickStart.z.g(g);
            }
        }
        return 0;
    }
    
    public static final boolean i(final String s) {
        return jiQuickStart.y != null && s != null && s.equals(jiQuickStart.y);
    }
    
    private static final void m(final String s) {
        try {
            if (i.c(105)) {
                h.d(s, "Waiting for QS to shutdown...");
            }
            int n = 5000;
            for (int n2 = 100; e(s) && n > 0; n -= n2) {
                d.b(n2, 4213, s);
            }
            if (i.c(105)) {
                if (n <= 0) {
                    h.d(s, "QS shutdown - TIMEOUT, continuing anyway...");
                }
                else {
                    h.d(s, "QS shutdown, continuing...");
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static final boolean a(final String s, final boolean b) {
        boolean b2 = false;
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream("\\\\.\\PIPE\\v1quitpipe");
            if (fileOutputStream != null) {
                a(s, "found pipe");
                a(s, "writing to pipe...");
                fileOutputStream.write("quit\u0000".getBytes());
                a(s, "finished writing to pipe");
                fileOutputStream.flush();
                a(s, "flushed pipe");
                fileOutputStream.close();
                a(s, "closed pipe");
                b2 = true;
                if (b) {
                    m(s);
                }
            }
        }
        catch (Exception ex) {}
        return b2;
    }
    
    private void o() {
        a(this.o, "releaseResources() called");
        this.b();
        if (this.k != null) {
            this.k.releaseResources();
            this.k = null;
        }
        if (this.l != null) {
            this.l = null;
        }
    }
    
    private static final void a(final String s, final String s2) {
        if (i.c(105) || i.c(124)) {
            h.d(s, "Quick-start: ".concat(String.valueOf(String.valueOf(s2))));
        }
    }
    
    private static boolean a(final Component component, final af af, final String s) {
        if (!jiQuickStart.aa) {
            jiQuickStart.aa = true;
            if (jiQuickStart.z == null) {
                jiQuickStart.z = new v();
                jiQuickStart.ab = jiQuickStart.z.b(component, s, af);
            }
        }
        return jiQuickStart.ab;
    }
    
    static {
        jiQuickStart.e = false;
        jiQuickStart.f = false;
        jiQuickStart.p = false;
        jiQuickStart.q = true;
        jiQuickStart.r = false;
        jiQuickStart.u = false;
        jiQuickStart.v = 0;
        jiQuickStart.w = null;
        jiQuickStart.x = new Object();
        jiQuickStart.y = null;
        jiQuickStart.z = null;
        jiQuickStart.aa = false;
        jiQuickStart.ab = false;
    }
    
    class zz implements Runnable
    {
        String a;
        
        public zz(final String a) {
            this.a = null;
            this.a = a;
        }
        
        public void run() {
            try {
                new p(this.a).ao(false, this);
                jiQuickStart.c(this.a);
                jiQuickStart.this.g(this.a);
                jiQuickStart.a(this.a, false);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    class tr implements Runnable
    {
        String a;
        String b;
        boolean c;
        FileInputStream d;
        DataInputStream e;
        
        public tr(final String a, final String b) {
            this.a = null;
            this.b = null;
            this.c = false;
            this.d = null;
            this.e = null;
            this.a = a;
            this.b = b;
        }
        
        public void a() {
            this.c = true;
            if (this.e != null) {
                this.b();
            }
        }
        
        private final void b() {
            try {
                if (this.e != null) {
                    this.e.close();
                    this.e = null;
                }
            }
            catch (Exception ex) {}
            try {
                if (this.d != null) {
                    this.d.close();
                    this.d = null;
                }
            }
            catch (Exception ex2) {}
        }
        
        public void run() {
            boolean b = false;
            try {
                this.d = new FileInputStream(this.a);
                if (this.d != null) {
                    if (ji.util.i.c(105)) {
                        ji.io.h.d(jiQuickStart.this.o, "Quick-start: Tray listener opened.");
                    }
                    this.e = new DataInputStream(this.d);
                    if (ji.util.i.c(105)) {
                        ji.io.h.d(jiQuickStart.this.o, "Quick-start: Waiting on Tray listener....");
                    }
                    while (!this.c && !jiQuickStart.this.t) {
                        final String line = this.e.readLine();
                        if (ji.util.i.c(105)) {
                            ji.io.h.d(jiQuickStart.this.o, String.valueOf(String.valueOf(new StringBuffer("Quick-start: Listener received: <").append(line).append(">"))));
                        }
                        if (line == null) {
                            break;
                        }
                        if (line.toLowerCase().equals("stop")) {
                            this.c = true;
                            b = true;
                            break;
                        }
                    }
                    if (ji.util.i.c(105)) {
                        ji.io.h.d(jiQuickStart.this.o, "Quick-start: Tray listener stopped.");
                    }
                    this.b();
                }
            }
            catch (Exception ex) {
                ji.io.h.d(jiQuickStart.this.o, "Quick-start: Tray listener failed:");
                ex.printStackTrace();
            }
            finally {
                jiQuickStart.this.s = null;
            }
            try {
                if (b) {
                    new bb(jiQuickStart.this.o, new zz(jiQuickStart.this.o)).run();
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
    }
}
