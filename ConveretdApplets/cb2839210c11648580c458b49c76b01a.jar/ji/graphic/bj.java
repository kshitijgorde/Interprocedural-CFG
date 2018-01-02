// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import ji.ext.v;
import java.io.File;
import ji.res.ay;
import ji.res.s;
import ji.document.b2;
import ji.document.gm;
import ji.document.ad;
import ji.io.h;
import ji.util.i;
import ji.awt.bb;
import ji.util.e;
import ji.v1event.af;
import java.awt.Window;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Dialog;
import ji.util.d;
import java.awt.Color;
import ji.awt.c;

public class bj
{
    private String a;
    private String b;
    private ec c;
    private ec d;
    private static bx e;
    private c f;
    private static bt g;
    private static bv h;
    private static bk i;
    private static b1 j;
    private static String k;
    private static Color l;
    private static boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private Color t;
    private int u;
    private String v;
    
    public bj(final String v) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.f = null;
        this.n = false;
        this.o = false;
        this.p = true;
        this.q = true;
        this.r = false;
        this.s = false;
        this.t = null;
        this.u = 0;
        this.v = v;
    }
    
    public boolean a() {
        return this.s;
    }
    
    public void b() {
        try {
            if (bj.g != null) {
                bj.g.a();
            }
            if (bj.e != null) {
                bj.e.b();
            }
            if (bj.h != null) {
                bj.h.c();
            }
            if (this.f != null) {
                while (this.f.b() > 0) {
                    final bw bw = (bw)this.f.b(0);
                    this.f.d(0);
                    try {
                        bw.b();
                    }
                    catch (Exception ex) {}
                }
            }
            if (bj.j != null) {
                bj.j.a();
            }
            ji.util.d.bi(false);
        }
        catch (Exception ex2) {}
    }
    
    public void a(final Dialog dialog, final String k) throws Exception {
        this.q = false;
        ji.util.d.df();
        if (bj.i != null && dialog.equals(bj.i)) {
            this.p = bj.i.c();
            bj.i = null;
        }
        if (bj.h != null && dialog.equals(bj.h)) {
            if (!((bv)dialog).a()) {
                if (k != null) {
                    if (ji.util.d.by(k)) {
                        bj.l = null;
                    }
                    else {
                        bj.l = ji.util.d.b1(k);
                    }
                }
                else {
                    bj.l = this.t;
                }
            }
            else {
                this.s = true;
                bj.l = this.t;
            }
            bj.h = null;
        }
        if (this.f != null) {
            for (int i = 0; i < this.f.b(); ++i) {
                if (dialog.equals(this.f.b(i))) {
                    if (!((bw)dialog).c()) {
                        bj.k = k;
                    }
                    else {
                        this.s = true;
                        bj.k = null;
                    }
                    this.f.d(i);
                    break;
                }
            }
        }
        if (bj.g != null && dialog.equals(bj.g)) {
            if (!((bt)dialog).b()) {
                bj.k = k;
            }
            else {
                this.s = true;
                bj.k = null;
            }
            bj.g = null;
        }
        if (bj.e != null && dialog.equals(bj.e)) {
            if (!((bx)dialog).a()) {
                bj.k = k;
            }
            else {
                this.s = true;
                bj.k = null;
            }
            bj.e = null;
        }
        ji.util.d.bi(false);
    }
    
    public static gt a(final Component component, final String s) throws Exception {
        final Window b = d.b(component, s);
        gt gt = null;
        if (b instanceof Frame) {
            gt = new gt(component, (Frame)b, null, d.b(1159, s), s);
        }
        else if (b instanceof Dialog) {
            gt = new gt(component, (Dialog)b, null, d.b(1159, s), s);
        }
        return gt;
    }
    
    public Color a(final String s, final Component component, final af af, final Color color, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s2) {
        try {
            this.s = false;
            bj.l = color;
            ji.util.e.d(s2, component);
            this.t = color;
            if (bj.h != null) {
                bj.h.setVisible(false);
                ji.util.d.ew();
                try {
                    bj.h.dispose();
                }
                catch (Exception ex) {}
                ji.util.d.ew();
                bj.h = null;
            }
            boolean b5 = false;
            if (component == null) {
                b5 = true;
            }
            final Window b6 = ji.util.d.b(component, s2);
            if (b6 instanceof Frame) {
                bj.h = new bv((Frame)b6, this, s, component, af, color, b, b2, b3, b4, s2);
            }
            else if (b6 instanceof Dialog) {
                bj.h = new bv((Dialog)b6, this, s, component, af, color, b, b2, b3, b4, s2);
            }
            ji.util.d.bi(true);
            final bb bb = new bb(s2, new abz(b5));
            bj.m = false;
            bb.setPriority(1);
            bb.start();
            while (bj.h != null) {
                ji.util.d.b(100, 52, s2);
            }
            bj.m = true;
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            ji.util.e.au();
            ji.util.d.bi(false);
        }
        return bj.l;
    }
    
    public boolean a(final String s, final String s2, final Component component, final boolean o, final String s3, final String s4, final String s5, final boolean b, final boolean b2) {
        if (ji.util.i.c(124)) {
            ji.io.h.d(s4, "Shutdown dialog: showMessage A");
        }
        if (ji.util.d.ck(s4) && !ji.util.d.cl(s4)) {
            return false;
        }
        try {
            if (!ji.util.d.d5()) {
                return false;
            }
            this.s = false;
            this.p = false;
            this.o = o;
            ji.util.e.d(s4, component);
            if (bj.i != null) {
                bj.i.setVisible(false);
                ji.util.d.ew();
                try {
                    bj.i.dispose();
                }
                catch (Exception ex) {}
                ji.util.d.ew();
                bj.i = null;
            }
            if (component == null) {}
            final Window b3 = ji.util.d.b(component, s4);
            boolean b4 = !b;
            if (o) {
                b4 = true;
            }
            if (b3 instanceof Frame) {
                bj.i = new bk(component, (Frame)b3, this, s, s2, b4, o, s3, s4, s5, b, b2);
            }
            else if (b3 instanceof Dialog) {
                bj.i = new bk(component, (Dialog)b3, this, s, s2, b4, o, s3, s4, s5, b, b2);
            }
            if (s2 != null) {
                final boolean b5 = !this.n && ji.util.d.eg() && !ji.util.d.do() && ji.util.d.b5() && ji.util.d.an();
                ji.util.d.bi(true);
                if (ji.util.i.c(124)) {
                    ji.io.h.d(s4, "Shutdown dialog: showMessage");
                }
                bj.i.show();
                ji.util.d.de();
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            if (!ji.util.d.ck(s4)) {
                ji.util.e.au();
            }
            ji.util.d.bi(false);
        }
        ji.util.d.ao(false);
        return this.p;
    }
    
    public void a(final String s, final String s2, final Component component, final int n, final String s3, final String s4, final String s5, final boolean b, final boolean b2) throws Exception {
        if (ji.util.i.c(124)) {
            ji.io.h.d(s4, "Shutdown dialog: showMessage B");
        }
        if (ji.util.d.ck(s4) && !ji.util.d.cl(s4)) {
            return;
        }
        if (component instanceof ad && ((ad)component).cz()) {
            throw new Exception(s2);
        }
        new to(this, s, s2, component, n, s3, s4, s5, b, b2).run();
    }
    
    public final void a(final boolean r) {
        this.r = r;
    }
    
    public final boolean c() {
        return this.r;
    }
    
    public final boolean d() {
        try {
            if (bj.i != null) {
                return bj.i.isVisible();
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public final void e() {
        try {
            bj.m = true;
            if (bj.i != null && !bj.i.a()) {
                bj.i.setVisible(false);
                try {
                    bj.i.dispose();
                }
                catch (Exception ex) {}
                bj.i = null;
            }
        }
        catch (Exception ex2) {}
    }
    
    public String a(final String s, final String s2, final String s3, final Component component, final boolean b, final boolean b2, final String s4, final int n, final char c, final boolean b3, final boolean b4, final boolean b5, final b0 b6, final boolean b7, final int n2, final int n3) {
        return this.a(s, s2, null, s3, component, b, b2, s4, n, c, b3, b4, b5, b6, b7, n2, n3);
    }
    
    public String a(final String s, final String s2, final String s3, final String s4, final Component component, final boolean b, final boolean b2, final String s5, final int n, final char c, final boolean b3, final boolean b4, final boolean b5, final b0 b6, final boolean b7, final int n2, final int n3) {
        if (ji.util.d.b()) {
            return bj.k = null;
        }
        this.e();
        try {
            this.s = false;
            ji.util.e.d(s5, component);
            final Window b8 = ji.util.d.b(component, s5);
            bw bw = null;
            if (b8 instanceof Frame) {
                bw = new bw((Frame)b8, this, s, s2, s3, s4, n, b, b2, s5, c, b3, b5, b4, b6, b7, n2, n3);
            }
            else if (b8 instanceof Dialog) {
                bw = new bw((Dialog)b8, this, s, s2, s3, s4, n, b, b2, s5, c, b3, b5, b4, b6, b7, n2, n3);
            }
            if (this.f == null) {
                this.f = new c("jiDialogInputDialogs");
            }
            this.f.c(bw);
            bw.setBackground(ji.util.e.ao());
            ji.util.d.bi(true);
            bw.show(b4);
            this.u = bw.a();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            if (b4) {
                ji.util.e.au();
            }
        }
        return bj.k;
    }
    
    public final int f() {
        return this.u;
    }
    
    public String a(final String s, final Component component, final int n, final boolean[] array, final String s2) {
        this.e();
        try {
            this.s = false;
            ji.util.e.d(s2, component);
            if (bj.g != null) {
                bj.g.setVisible(false);
                ji.util.d.ew();
                try {
                    bj.g.dispose();
                }
                catch (Exception ex) {}
                ji.util.d.ew();
                bj.g = null;
            }
            final Window b = ji.util.d.b(component, s2);
            if (b instanceof Frame) {
                bj.g = new bt((Frame)b, this, s, n, array, s2);
            }
            else if (b instanceof Dialog) {
                bj.g = new bt((Dialog)b, this, s, n, array, s2);
            }
            bj.g.setBackground(ji.util.e.ao());
            ji.util.d.bi(true);
            bj.g.show();
        }
        catch (Throwable t) {}
        finally {
            ji.util.e.au();
        }
        return bj.k;
    }
    
    public b2 a(final gm[] array, final String s, final String s2, final Component component, final String s3) {
        this.e();
        b2 a = new b2();
        b1 b1 = null;
        final Window b2 = ji.util.d.b(component, s3);
        if (b2 instanceof Frame) {
            b1 = new b1((Frame)b2, s2, true);
        }
        else if (b2 instanceof Dialog) {
            b1 = new b1((Dialog)b2, s2, true);
        }
        b1.a(this, s, s3);
        String m = this.m();
        if (m == null) {
            m = s;
        }
        b1.a(this, m, s3);
        try {
            ji.util.d.b7(false);
            a = b1.a(array, m);
        }
        catch (Exception ex) {
            ji.io.h.a(s3, ex);
        }
        finally {
            ji.util.d.b7(true);
        }
        return a;
    }
    
    public String a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final Component component, final String s7) {
        this.e();
        try {
            this.s = false;
            ji.util.e.d(s7, component);
            if (bj.e != null) {
                bj.e.setVisible(false);
                ji.util.d.ew();
                try {
                    bj.e.dispose();
                }
                catch (Exception ex) {}
                ji.util.d.ew();
                bj.e = null;
            }
            final Window b = ji.util.d.b(component, s7);
            if (b instanceof Frame) {
                bj.e = new bx((Frame)b, this, s, s2, s3, s4, s5, s6, 5, s7);
            }
            else if (b instanceof Dialog) {
                bj.e = new bx((Dialog)b, this, s, s2, s3, s4, s5, s6, 5, s7);
            }
            ji.util.d.bi(true);
            bj.e.show();
        }
        catch (Throwable t) {}
        finally {
            ji.util.e.au();
        }
        return bj.k;
    }
    
    public String a(final String title, final String directory, final String s, final Component component, final String s2) {
        String concat = null;
        this.e();
        try {
            this.s = false;
            ji.util.e.d(s2, component);
            if (this.c == null) {
                this.c = this.a(component, title, 1);
            }
            this.c.intialise();
            this.c.setBackground(ji.util.e.aq());
            if (title != null) {
                this.c.setTitle(title);
            }
            if (directory != null) {
                this.c.setDirectory(directory);
            }
            ji.util.d.bj(false);
            this.c.show();
            ji.util.d.bj(true);
            final String directory2 = this.c.getDirectory();
            final String file = this.c.getFile();
            if (directory2 != null && file != null) {
                this.a = directory2;
                this.b = file;
                concat = String.valueOf(String.valueOf(directory2)).concat(String.valueOf(String.valueOf(file)));
            }
            ji.util.d.ew();
        }
        catch (Exception ex) {
            ji.util.d.a(ex, null, null, 5, null, s2);
        }
        finally {
            ji.util.e.au();
        }
        return concat;
    }
    
    public final String a(final int n, final String a, final String b, final boolean b2, final Component component, final String s) throws Exception {
        String s2 = null;
        String s3 = null;
        switch (n) {
            case 1: {
                s2 = String.valueOf(String.valueOf(s.a(547, s))).concat("...");
                break;
            }
            case 4: {
                s2 = s.a(225, s);
                s3 = s.a(1239, s);
                break;
            }
            case 3: {
                s2 = s.a(224, s);
                s3 = s.a(1241, s);
                break;
            }
            case 2: {
                s2 = s.a(223, s);
                s3 = s.a(1240, s);
                break;
            }
        }
        final String m = this.m();
        if (n == 1) {
            if (m != null) {
                this.a = a;
                this.b = b;
                return a;
            }
            return this.b(s2, a, b, component, s);
        }
        else {
            String s4;
            if (m != null) {
                s4 = this.a(s2, s3, a, b, component, s);
            }
            else {
                s4 = this.b(s2, a, b, component, s);
            }
            if (!ji.util.d.by(s4) && b2 && !ji.util.d.cy(ji.util.d.bh(s4)) && !ji.util.d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.a(1255, s)))).append("\n").append(s.a(1256, s)))), component, null, s)) {
                return null;
            }
            return s4;
        }
    }
    
    public final String a(final String s, final Component component, final String s2) throws Exception {
        final String concat = String.valueOf(String.valueOf(s.a(547, s2))).concat("...");
        s.a(1239, s2);
        final String m = this.m();
        this.b = ".";
        if (m == null) {
            return this.a(concat, s, component, s2);
        }
        this.a = m;
        return s;
    }
    
    private String m() {
        String s = ji.util.i.e(2);
        if (s != null) {
            final File file = new File(s);
            if (file.exists() && file.isDirectory()) {
                s = file.getAbsolutePath();
            }
            else {
                ji.util.d.a(String.valueOf(String.valueOf(ji.res.s.a(1243, this.v))).concat(String.valueOf(String.valueOf(s))), (af)null, this.v);
                ji.io.h.d(this.v, String.valueOf(String.valueOf(new StringBuffer("Could not select directory as ").append(s).append(" is an invalid file path."))));
                s = null;
            }
        }
        return s;
    }
    
    private String a(final String s, final String s2, final String a, final String s3, final Component component, final String s4) {
        this.a = null;
        this.b = null;
        final String a2 = this.a(s, s.a(1242, s4), String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(a))), s3, component, false, false, s4, 48, '\0', true, true, false, null, false, 0, 3);
        if (a2 == null) {
            return null;
        }
        final String name = new File(a2).getName();
        this.a = a;
        this.b = name;
        return new File(a, this.b).getAbsolutePath();
    }
    
    private String a(final String title, final String directory, final Component component, final String s) {
        String directory2 = null;
        this.e();
        try {
            this.s = false;
            ji.util.e.d(s, component);
            if (this.d == null) {
                this.d = this.a(component, title, 3);
            }
            else {
                this.d.setMode(3);
            }
            this.d.intialise();
            this.d.setFile("[directory]");
            this.d.setBackground(ji.util.e.aq());
            try {
                if (title != null) {
                    this.d.setTitle(title);
                }
            }
            catch (Exception ex) {}
            try {
                if (directory != null) {
                    this.d.setDirectory(directory);
                }
            }
            catch (Exception ex2) {}
            ji.util.d.bj(false);
            this.d.show();
            ji.util.d.bj(true);
            if (this.d.getDirectory() != null) {
                this.a = this.d.getDirectory();
                directory2 = this.d.getDirectory();
            }
            ji.util.d.ew();
        }
        catch (Exception ex3) {}
        finally {
            ji.util.e.au();
        }
        return directory2;
    }
    
    private String b(final String title, final String directory, final String file, final Component component, final String s) {
        String concat = null;
        this.e();
        try {
            this.s = false;
            ji.util.e.d(s, component);
            if (this.d == null) {
                this.d = this.a(component, title, 2);
            }
            this.d.intialise();
            this.d.setBackground(ji.util.e.aq());
            try {
                if (title != null) {
                    this.d.setTitle(title);
                }
            }
            catch (Exception ex) {}
            try {
                if (directory != null) {
                    this.d.setDirectory(directory);
                }
            }
            catch (Exception ex2) {}
            try {
                if (file != null) {
                    this.d.setFile(file);
                }
            }
            catch (Exception ex3) {}
            ji.util.d.bj(false);
            this.d.show();
            ji.util.d.bj(true);
            if (this.d.getDirectory() != null && this.d.getFile() != null) {
                this.a = this.d.getDirectory();
                this.b = this.d.getFile();
                concat = String.valueOf(String.valueOf(this.d.getDirectory())).concat(String.valueOf(String.valueOf(this.d.getFile())));
            }
            ji.util.d.ew();
        }
        catch (Exception ex4) {}
        finally {
            ji.util.e.au();
        }
        return concat;
    }
    
    public String g() {
        return this.a;
    }
    
    public String h() {
        return this.b;
    }
    
    public void i() {
        try {
            this.b();
            if (this.c != null) {
                try {
                    this.c.dispose();
                }
                catch (Exception ex) {}
                this.c = null;
            }
            if (this.d != null) {
                try {
                    this.d.dispose();
                }
                catch (Exception ex2) {}
                this.d = null;
            }
            if (bj.i != null) {
                try {
                    bj.i.dispose();
                }
                catch (Exception ex3) {}
                bj.i = null;
            }
            if (this.f != null) {
                while (this.f.b() > 0) {
                    final bw bw = (bw)this.f.b(0);
                    this.f.d(0);
                    try {
                        bw.dispose();
                    }
                    catch (Exception ex4) {}
                }
            }
            if (bj.e != null) {
                try {
                    bj.e.dispose();
                }
                catch (Exception ex5) {}
                bj.e = null;
            }
            if (bj.h != null) {
                try {
                    bj.h.dispose();
                }
                catch (Exception ex6) {}
                bj.h = null;
            }
        }
        catch (Exception ex7) {}
    }
    
    public void finalize() {
    }
    
    private Frame a(final Component component) {
        Frame e = ji.util.d.e(component);
        if (e == null) {
            e = new Frame();
        }
        return e;
    }
    
    private ec a(final Component component, final String title, final int mode) throws Exception {
        ec ec;
        if (this.b(component)) {
            ec = (ec)ji.util.d.a2("ji.graphic.jiJava2FileDialog");
            ec.setParent(component, this.v);
        }
        else {
            ec = new f7(this.a(component));
        }
        if (title != null) {
            ec.setTitle(title);
        }
        ec.setMode(mode);
        return ec;
    }
    
    private boolean b(final Component component) {
        if (!ji.util.e.av()) {
            return false;
        }
        if (ji.util.i.c(172)) {
            ji.io.h.f(this.v, "User Preference for Java2 File Dialogs");
            return true;
        }
        if (ji.util.d.a(1, 6, 0, 0, this.v) && ji.util.e.y(this.v)) {
            ji.io.h.f(this.v, "Java 1.6 or Later on Vista so use Java2 File Dialogs");
            return true;
        }
        if (ji.util.d.a(1, 5, 0, 10, this.v)) {
            ji.io.h.f(this.v, "Java 1.5.0_10 or Later on a non_Vista Windows Platform so use Java1 File Dialogs");
            return false;
        }
        if (ji.util.i.c(176) && ji.util.e.u(this.v)) {
            final boolean c = new v().c(this.v, component);
            ji.io.h.f(this.v, "Checked for IE7 to work out if to use Java1 File Dialog: isIE7Plus = ".concat(String.valueOf(String.valueOf(c))));
            return c;
        }
        return false;
    }
    
    static {
        bj.e = null;
        bj.g = null;
        bj.h = null;
        bj.i = null;
        bj.j = null;
        bj.k = null;
        bj.l = null;
        bj.m = false;
    }
    
    class abz implements Runnable
    {
        boolean a;
        
        public abz(final boolean a) {
            this.a = a;
        }
        
        public final void run() {
            try {
                bj.this.n = true;
                if (bj.h != null) {
                    bj.h.setVisible(true);
                    while (bj.h != null && !bj.m) {
                        ji.util.d.b(1000, 156, bj.this.v);
                        if (this.a && ji.util.d.dh() && ji.util.e.at()) {
                            ji.util.e.b(bj.h);
                            bj.h.toFront();
                        }
                    }
                    bj.m = false;
                    if (bj.h != null) {
                        try {
                            bj.h.dispose();
                        }
                        catch (Exception ex) {}
                        bj.h = null;
                    }
                }
            }
            catch (Exception ex2) {}
            finally {
                ji.util.d.bi(false);
                bj.this.n = false;
            }
        }
    }
    
    class tp implements Runnable
    {
        int a;
        boolean b;
        String c;
        
        public tp(final String c, final boolean b, final int a) {
            this.a = 0;
            this.c = c;
            this.a = a;
            this.b = b;
        }
        
        public final void run() {
            try {
                bj.this.n = true;
                if (bj.i != null) {
                    if (ji.util.i.c(124)) {
                        ji.io.h.d(this.c, "Shutdown dialog: about to set vis");
                    }
                    bj.i.setVisible(true);
                    if (ji.util.i.c(124)) {
                        ji.io.h.d(this.c, "Shutdown dialog: set vis");
                    }
                    int n = this.a * 1000;
                    int n2 = 250;
                    while (bj.i != null && n > 0 && !bj.m) {
                        if (ji.util.i.c(124)) {
                            ji.io.h.d(this.c, "Shutdown dialog: waiting ".concat(String.valueOf(String.valueOf(n))));
                        }
                        ji.util.d.b(n2, 145, this.c);
                        n -= n2;
                        n2 = 2000;
                        if ((this.b || ji.util.d.ck(this.c)) && (ji.util.d.dh() || ji.util.d.ck(this.c)) && ji.util.e.at()) {
                            ji.util.e.b(bj.i);
                            bj.i.toFront();
                        }
                    }
                    bj.m = false;
                    if (bj.i != null) {
                        try {
                            if (ji.util.i.c(124)) {
                                ji.io.h.d(this.c, "Shutdown dialog: disposing");
                            }
                            bj.i.dispose();
                        }
                        catch (Exception ex) {}
                        bj.i = null;
                    }
                }
            }
            catch (Exception ex2) {}
            finally {
                ji.util.d.bi(false);
                bj.this.n = false;
            }
            ji.util.d.ao(false);
        }
    }
    
    private class to implements Runnable
    {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private Component f;
        private int g;
        private bj h;
        private boolean i;
        private boolean j;
        
        public to(final bj h, final String a, final String b, final Component f, final int g, final String c, final String d, final String e, final boolean i, final boolean j) {
            this.i = false;
            this.j = false;
            this.h = h;
            this.a = a;
            this.b = b;
            this.f = f;
            this.g = g;
            this.c = c;
            this.d = d;
            this.e = e;
            this.i = i;
            this.j = j;
        }
        
        public void run() {
            try {
                if (!ji.util.d.d5()) {
                    return;
                }
                bj.this.s = false;
                ji.util.d.bi(true);
                if (this.g > 0 && ji.util.d.an()) {
                    ji.util.e.d(this.d, this.f);
                    if (bj.i != null) {
                        if (bj.i.isVisible()) {
                            bj.i.setVisible(false);
                            ji.util.d.ew();
                        }
                        try {
                            bj.i.dispose();
                        }
                        catch (Exception ex) {}
                        ji.util.d.ew();
                        bj.i = null;
                    }
                    boolean b = false;
                    if (this.f == null) {
                        b = true;
                    }
                    if (ji.util.i.c(124)) {
                        ji.io.h.d(this.d, "Shutdown dialog: new frame ".concat(String.valueOf(String.valueOf(b))));
                    }
                    final Window b2 = ji.util.d.b(this.f, this.d);
                    if (ji.util.i.c(124)) {
                        ji.io.h.d(this.d, "Shutdown dialog: parent ".concat(String.valueOf(String.valueOf(b2))));
                    }
                    if (b2 instanceof Frame) {
                        bj.i = new bk(this.f, (Frame)b2, this.h, this.a, this.b, !this.i, false, this.c, this.d, this.e, this.i, this.j);
                    }
                    else if (b2 instanceof Dialog) {
                        bj.i = new bk(this.f, (Dialog)b2, this.h, this.a, this.b, !this.i, false, this.c, this.d, this.e, this.i, this.j);
                    }
                    final bb bb = new bb(this.d, new tp(this.d, b, this.g));
                    bj.m = false;
                    bb.start();
                }
                else {
                    bj.this.a(this.a, this.b, this.f, false, this.c, this.d, this.e, this.i, this.j);
                    ji.util.d.ao(false);
                }
            }
            catch (Throwable t) {}
            finally {
                this.h = null;
                this.f = null;
                ji.util.e.au();
            }
        }
    }
}
