// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.application;

import java.io.IOException;
import java.awt.MenuContainer;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.MenuBar;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.util.Enumeration;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.File;
import java.awt.Container;
import java.util.Hashtable;
import java.awt.Frame;
import java.util.Properties;
import java.applet.Applet;

public class MindTerm extends Applet implements Runnable
{
    public static Properties oz;
    public static Properties oy;
    public static String ox;
    public static String ow;
    public static String ov;
    public static String ou;
    public static String ot;
    public Frame os;
    public h d4;
    public bq e9;
    public bq or;
    public ab km;
    public Thread oq;
    public boolean op;
    public Properties oo;
    public Properties on;
    public String[] om;
    public String lo;
    public String hr;
    public String ol;
    public boolean ok;
    public boolean oj;
    public boolean oi;
    public boolean oh;
    public boolean ko;
    public boolean og;
    public boolean of;
    public boolean g;
    public int oe;
    public boolean hl;
    public boolean hk;
    public boolean hj;
    public int ka;
    public boolean od;
    public boolean oc;
    public boolean ob;
    public static Hashtable oa;
    public boolean n9;
    
    public static synchronized boolean og() {
        return MindTerm.oa.isEmpty();
    }
    
    public static synchronized void of(final MindTerm mindTerm) {
        MindTerm.oa.put(mindTerm, mindTerm);
    }
    
    public static synchronized void oe(final MindTerm mindTerm) {
        MindTerm.oa.remove(mindTerm);
    }
    
    public MindTerm() {
        this.ok = false;
        this.oj = true;
        this.oi = true;
        this.oh = false;
        this.ko = true;
        this.og = false;
        this.of = false;
        this.g = true;
        this.hl = true;
        this.hk = true;
        this.hj = false;
        this.ka = 3;
        this.od = false;
        this.oc = true;
        this.ob = false;
        this.n9 = false;
        this.oo = MindTerm.oy;
        this.on = MindTerm.oz;
        of(this);
    }
    
    public MindTerm(final Properties oo, final Properties on) {
        this.ok = false;
        this.oj = true;
        this.oi = true;
        this.oh = false;
        this.ko = true;
        this.og = false;
        this.of = false;
        this.g = true;
        this.hl = true;
        this.hk = true;
        this.hj = false;
        this.ka = 3;
        this.od = false;
        this.oc = true;
        this.ob = false;
        this.n9 = false;
        this.oo = oo;
        this.on = on;
        of(this);
    }
    
    public static void main(final String[] om) {
        final MindTerm mindTerm = new MindTerm(MindTerm.oy, MindTerm.oz);
        mindTerm.om = om;
        try {
            mindTerm.oc();
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            System.exit(1);
        }
        try {
            mindTerm.run();
        }
        catch (Exception ex2) {
            System.out.println("Error, please mail below stack-trace to mats@mindbright.se");
            ex2.printStackTrace();
        }
    }
    
    public final void init() {
        this.ob = true;
        this.hl = false;
        this.hk = false;
        this.hj = false;
        this.od();
        new Thread(this).start();
    }
    
    public final void run() {
        try {
            if (this.or != null) {
                this.e9 = new bq(this.or);
                this.or = null;
            }
            else {
                if (this.lo != null && this.oo.getProperty("forcpty") == null) {
                    ((Hashtable<String, String>)this.oo).put("forcpty", "false");
                }
                as hl = new as(this.oo);
                if (this.ol != null) {
                    try {
                        hl = as.hl(this.ol, "");
                    }
                    catch (b4 b4) {
                        throw new Exception("Sorry, can only use passwordless settings files for now");
                    }
                    hl.g9(this.oo);
                }
                this.e9 = new bq(this.ko, this.oh, hl);
            }
            this.km = (ab)this.e9.lp();
            if (this.e9.jr().g8() != null) {
                final Properties on = new Properties(this.e9.jr().g8());
                if (this.on != null && !this.on.isEmpty()) {
                    final Enumeration<String> keys = (Enumeration<String>)this.on.keys();
                    while (keys.hasMoreElements()) {
                        final String s = keys.nextElement();
                        ((Hashtable<String, String>)on).put(s, this.on.getProperty(s));
                    }
                    this.op = true;
                }
                this.on = on;
            }
            if (this.oi) {
                this.n8();
                this.km.du(this.d4);
                this.km.e3(this.os);
                this.km.e2("MindTerm v1.2.1");
                this.km.ew();
                try {
                    while (!this.os.isShowing()) {
                        Thread.sleep(50L);
                    }
                }
                catch (InterruptedException ex4) {}
                if (!this.oc) {
                    this.d4.ae();
                }
            }
            this.e9.jq();
            this.e9.jr().hd(this.hr);
            this.e9.jr().hi(this.hk);
            this.e9.jr().hj(this.hl);
            this.e9.jr().hh(this.hj);
            this.e9.cn();
            if (this.lo != null) {
                if (!this.og) {
                    this.e9.jm(this.lo, false, 0L);
                }
                else {
                    if (this.om.length - this.oe < 2) {
                        throw new Exception("scp must have at least two arguments (<source> <destination>)");
                    }
                    final String[] array = new String[this.om.length - this.oe - 1];
                    final String substring = this.lo.substring(0, this.lo.lastIndexOf(32));
                    for (int i = this.oe; i < this.om.length - 1; ++i) {
                        array[i - this.oe] = this.om[i];
                    }
                    final String s2 = this.om[this.om.length - 1];
                    final bz bz = new bz(this.e9.jr().gb(), this.e9.jr().ga(), this.e9.jr(), new File("."), ca.mo, this.of);
                    if (ca.mo) {
                        bz.hk(this.e9);
                        bz.kk(new ac());
                    }
                    if (this.g) {
                        bz.ki(array, s2);
                    }
                    else {
                        bz.kh(s2, substring);
                    }
                }
            }
            else {
                try {
                    (this.oq = new Thread(this.e9)).start();
                    this.oq.join();
                }
                catch (InterruptedException ex5) {}
            }
        }
        catch (IllegalArgumentException ex) {
            if (this.e9 != null) {
                this.e9.h9(ex.getMessage());
            }
            System.out.println(ex.getMessage());
        }
        catch (FileNotFoundException ex2) {
            System.out.println("Settings-file not found: " + ex2.getMessage());
        }
        catch (Exception ex3) {
            if (this.e9 != null) {
                this.e9.h9("Error: " + ex3.getMessage());
            }
            System.out.println("Error: " + ex3.getMessage());
            if (ca.mn) {
                System.out.println("Please send the below stack-trace to mats@mindbright.se");
                ex3.printStackTrace();
            }
        }
        this.n7(null);
        if (og()) {
            this.n6();
        }
    }
    
    public final void od() {
        try {
            this.oc = new Boolean(this.getParameter("sepframe"));
        }
        catch (Exception ex) {
            this.oc = true;
        }
        try {
            ca.mo = new Boolean(this.getParameter("verbose"));
        }
        catch (Exception ex2) {
            ca.mo = false;
        }
        try {
            ca.mn = new Boolean(this.getParameter("debug"));
            ca.mo = ca.mn;
        }
        catch (Exception ex3) {}
        try {
            this.ko = new Boolean(this.getParameter("quiet"));
        }
        catch (Exception ex4) {
            this.ko = true;
        }
        try {
            this.hj = new Boolean(this.getParameter("savepasswords"));
        }
        catch (Exception ex5) {
            this.hj = false;
        }
        try {
            this.oh = new Boolean(this.getParameter("cmdsh"));
        }
        catch (Exception ex6) {
            this.oh = false;
        }
        try {
            new Boolean(this.getParameter("helpinfo"));
        }
        catch (Exception ex7) {}
        this.getParameter("startmsg");
        final String parameter = this.getParameter("menus");
        if (parameter != null) {
            if (parameter.equals("no")) {
                this.oj = false;
            }
            else if (parameter.startsWith("pop")) {
                this.oa(parameter);
                this.ok = true;
            }
        }
        final String parameter2 = this.getParameter("autoprops");
        if (parameter2 != null) {
            if (parameter2.equals("save")) {
                this.hl = true;
                this.hk = false;
            }
            else if (parameter2.equals("load")) {
                this.hl = false;
                this.hk = true;
            }
            else if (parameter2.equals("both")) {
                this.hl = true;
                this.hk = true;
            }
        }
        this.hr = this.getParameter("sshhome");
        this.ol = this.getParameter("propsfile");
        this.lo = this.getParameter("commandline");
        this.n9();
        for (int i = 0; i < as.e4.length; ++i) {
            final String s = as.e4[i][0];
            final String parameter3 = this.getParameter(s);
            if (parameter3 != null) {
                ((Hashtable<String, String>)MindTerm.oy).put(s, parameter3);
            }
        }
        String parameter4;
        for (int n = 0; (parameter4 = this.getParameter("local" + n)) != null; ++n) {
            ((Hashtable<String, String>)MindTerm.oy).put("local" + n, parameter4);
        }
        String parameter5;
        for (int n2 = 0; (parameter5 = this.getParameter("remote" + n2)) != null; ++n2) {
            ((Hashtable<String, String>)MindTerm.oy).put("remote" + n2, parameter5);
        }
        for (int j = 0; j < r.e4.length; ++j) {
            final String s2 = r.e4[j][0];
            final String parameter6 = this.getParameter(s2);
            if (parameter6 != null) {
                ((Hashtable<String, String>)this.on).put(s2, parameter6);
            }
        }
        final String parameter7 = this.getParameter("appletbg");
        if (parameter7 != null) {
            Color background;
            try {
                background = h.cg(parameter7);
            }
            catch (IllegalArgumentException ex8) {
                try {
                    background = h.ch(parameter7);
                }
                catch (Throwable t) {
                    background = null;
                }
            }
            if (background != null) {
                this.setBackground(background);
            }
        }
    }
    
    public final void oc() throws Exception {
        int i;
        try {
            i = 0;
            while (i < this.om.length) {
                final String s = this.om[i];
                if (s.startsWith("--")) {
                    switch (s.charAt(2)) {
                        case 'h': {
                            this.hr = this.om[++i];
                            break;
                        }
                        case 'f': {
                            this.ol = this.om[++i];
                            break;
                        }
                        case 'c': {
                            this.oh = true;
                            break;
                        }
                        case 'd': {
                            this.oi = false;
                            break;
                        }
                        case 'm': {
                            final String s2 = this.om[++i];
                            if (s2.equals("no")) {
                                this.oj = false;
                                break;
                            }
                            if (s2.startsWith("pop")) {
                                this.oa(s2);
                                this.ok = true;
                                break;
                            }
                            throw new Exception("value of '--m' must be 'no', 'pop1', 'pop2', or 'pop3'");
                        }
                        case 'p': {
                            final String s3 = this.om[++i];
                            if (s3.equals("save")) {
                                this.hl = true;
                                break;
                            }
                            if (s3.equals("load")) {
                                this.hk = true;
                                break;
                            }
                            if (s3.equals("both")) {
                                this.hl = true;
                                this.hk = true;
                                break;
                            }
                            if (s3.equals("none")) {
                                this.hl = false;
                                this.hk = false;
                                break;
                            }
                            throw new Exception("value of '--p' must be 'save', 'load', 'both', or 'none'");
                        }
                        case 'q': {
                            final String s4 = this.om[++i];
                            if (s4.equalsIgnoreCase("true") || s4.equalsIgnoreCase("false")) {
                                this.ko = Boolean.valueOf(s4);
                                break;
                            }
                            throw new Exception("value of '--q' must be 'true' or 'false'");
                        }
                        case 'r': {
                            this.of = true;
                            break;
                        }
                        case 's': {
                            this.oi = false;
                            this.og = true;
                            final String s5 = this.om[++i];
                            if (s5.equalsIgnoreCase("toremote")) {
                                this.g = true;
                                break;
                            }
                            if (s5.equalsIgnoreCase("tolocal")) {
                                this.g = false;
                                break;
                            }
                            throw new Exception("value of '--s' must be 'toremote' or 'tolocal'");
                        }
                        case 'v': {
                            System.out.println("verbose mode selected...");
                            ca.mo = true;
                            break;
                        }
                        case 'x': {
                            this.hj = true;
                            break;
                        }
                        case 'V': {
                            System.out.println("MindTerm v1.2.1");
                            System.out.println("SSH protocol version " + 1 + "." + 5);
                            System.exit(0);
                            break;
                        }
                        case 'D': {
                            ca.mo = true;
                            ca.mn = true;
                            break;
                        }
                        case '?': {
                            this.ob();
                            System.exit(0);
                            throw new Exception("unknown parameter '" + s + "'");
                        }
                    }
                    ++i;
                    continue;
                }
                break;
            }
        }
        catch (Exception ex) {
            this.ob();
            throw ex;
        }
        this.n9();
        int j;
        for (j = i; j < this.om.length; j += 2) {
            final String s6 = this.om[j];
            if (s6.charAt(0) != '-') {
                break;
            }
            if (j + 1 == this.om.length) {
                break;
            }
            final String substring = s6.substring(1);
            final String s7 = this.om[j + 1];
            if (as.dv(substring)) {
                ((Hashtable<String, String>)MindTerm.oy).put(substring, s7);
            }
            else if (r.dv(substring)) {
                ((Hashtable<String, String>)MindTerm.oz).put(substring, s7);
            }
            else {
                System.out.println("Unknown property '" + substring + "'");
            }
        }
        if (j < this.om.length) {
            this.oe = j;
            this.lo = "";
            while (j < this.om.length) {
                this.lo = String.valueOf(this.lo) + this.om[j] + " ";
                ++j;
            }
            this.lo = this.lo.trim();
        }
    }
    
    public final void ob() {
        System.out.println("usage: MindTerm [options] [properties] [command]");
        System.out.println("Options:");
        System.out.println("  --c            Enable local command-shell.");
        System.out.println("  --d            No terminal-window, only dumb command-line and port-forwarding.");
        System.out.println("  --f <file>     Use settings from the given file.");
        System.out.println("  --h dir        Name of the MindTerm home-dir (default: ~/mindterm/).");
        System.out.println("  --m <no | pop | popN>");
        System.out.println("                 Use no menus or popup (on mouse-button N) menu instead of menubar.");
        System.out.println("  --p <save | load | both | none>");
        System.out.println("                 Sets automatic save/load flags for property-files.");
        System.out.println("  --q <true | false>");
        System.out.println("                 Quiet; don't query for server/username if given.");
        System.out.println("  --v            Verbose; display verbose messages.");
        System.out.println("  --x            Save passwords in property-files.");
        System.out.println("  --D            Debug; display extra debug info.");
        System.out.println("  --V            Version; display version number only.");
        System.out.println("  --?            Help; display this help.");
    }
    
    public final void oa(final String s) {
        if (s.length() == 4) {
            try {
                this.ka = Integer.valueOf(s.substring(3));
                if (this.ka < 1 || this.ka > 3) {
                    this.ka = 3;
                }
            }
            catch (NumberFormatException ex) {}
        }
    }
    
    public final void n9() {
        try {
            if (this.hr == null) {
                String s = System.getProperty("user.home");
                if (s == null) {
                    s = System.getProperty("user.dir");
                }
                if (s == null) {
                    s = System.getProperty("java.home");
                }
                this.hr = String.valueOf(s) + File.separator + "mindterm" + File.separator;
            }
        }
        catch (Throwable t) {}
        if (this.ob) {
            ((Hashtable<String, String>)MindTerm.oy).put("server", this.getCodeBase().getHost());
        }
        try {
            if (!this.ko) {
                ((Hashtable<String, String>)MindTerm.oy).put("usrname", System.getProperty("user.name", ""));
            }
        }
        catch (Throwable t2) {}
        try {
            MindTerm.ox = System.getProperty("java.version");
            MindTerm.ow = System.getProperty("java.vendor");
            MindTerm.ov = System.getProperty("os.name");
            MindTerm.ou = System.getProperty("os.arch");
            MindTerm.ot = System.getProperty("os.version");
        }
        catch (Throwable t3) {}
    }
    
    public final void n8() {
        MenuBar menuBar = null;
        MenuContainer os;
        if (this.oc) {
            (this.os = new Frame()).addWindowListener(new cp(this));
            os = this.os;
            if (this.oj && !this.ok) {
                menuBar = new MenuBar();
                this.os.setMenuBar(menuBar);
                this.os.addNotify();
                this.os.validate();
            }
        }
        else {
            Container parent = this;
            do {
                parent = parent.getParent();
            } while (!(parent instanceof Frame));
            this.os = (Frame)parent;
            os = this;
        }
        this.d4 = new h(this.os, new q(0), this.on);
        if (this.op) {
            this.d4.cj(true);
        }
        if (this.oj) {
            try {
                final bl bl = (bl)Class.forName("mindbright.ssh.SSHMenuHandlerFull").newInstance();
                bl.i3(this, this.e9, this.os, this.d4);
                final p p = (p)Class.forName("mindbright.terminal.TerminalMenuHandlerFull").newInstance();
                p.c5(this.d4);
                this.d4.cp(p);
                this.e9.cp(bl);
                if (menuBar == null) {
                    bl.iy(this.d4.b9("MindTerm Menu"));
                    bl.ca(this.ka);
                }
                else {
                    bl.iz(menuBar);
                }
                p.c4(bl);
            }
            catch (Throwable t) {
                System.out.println("Full menus can't be enabled since classes are missing");
                this.d4.cp(null);
                this.e9.cp(null);
            }
        }
        ((Container)os).setLayout(new BorderLayout());
        ((Container)os).add(this.d4.b7(), "Center");
        this.os.pack();
        this.os.show();
        this.d4.requestFocus();
    }
    
    public final synchronized void n7(final WindowEvent windowEvent) {
        if (this.od) {
            return;
        }
        this.od = true;
        if (!this.n5()) {
            this.od = false;
            return;
        }
        if (this.oc && this.oi && this.os != null) {
            this.os.dispose();
        }
        if (this.oq != null && this.oq.isAlive()) {
            this.oq.stop();
        }
        oe(this);
    }
    
    public final void n6() {
        System.out.println("Thank you for using MindTerm...");
        if (!this.oc && this.d4 != null) {
            try {
                this.d4.bc(this.d4.b2() - 1, 0, false);
                for (int i = 0; i < this.d4.b2(); ++i) {
                    this.d4.bx("\n\r");
                    Thread.sleep(50L);
                }
                this.d4.bc(0, 0, false);
                for (int j = 0; j < this.d4.b2() - 1; ++j) {
                    this.d4.bx(".\n\r");
                    Thread.sleep(50L);
                }
                this.d4.bx("Thank you for using MindTerm...");
                for (int k = 0; k < this.d4.b2() - 1; ++k) {
                    this.d4.bx("\n\r");
                    Thread.sleep(50L);
                }
                this.d4.bc(2, 0, false);
                this.d4.ap(1, true);
                this.d4.bx("Visit <http://www.mindbright.se/mindterm> for more information.");
                this.d4.bc(this.d4.b2() - 1, this.d4.b1() - 1, false);
            }
            catch (Exception ex) {}
        }
        if (ca.mg != null && ca.mf().mv != null && ca.mf().mv.isAlive()) {
            ca.mf().mv.stop();
        }
        if (!this.ob) {
            System.exit(0);
        }
    }
    
    public final boolean n5() {
        if (this.e9 != null && !this.n9) {
            try {
                this.e9.jr().g_();
            }
            catch (IOException ex) {
                this.e9.h9("Error saving settings: " + ex.getMessage());
            }
            if (this.e9.kp() && !this.e9.jd("Do you really want to disconnect from " + this.e9.jr().ce("server") + "?", false)) {
                this.n9 = false;
            }
            else {
                this.n9 = true;
            }
        }
        return this.n9;
    }
    
    public final void n4(final MindTerm mindTerm) {
        this.hr = mindTerm.hr;
        this.ol = mindTerm.ol;
        this.ok = mindTerm.ok;
        this.oj = mindTerm.oj;
        this.oi = mindTerm.oi;
        this.oh = mindTerm.oh;
        this.ko = mindTerm.ko;
        this.oc = true;
        this.ob = mindTerm.ob;
        this.hk = mindTerm.hk;
        this.ka = mindTerm.ka;
    }
    
    public final void n3() {
        final MindTerm mindTerm = new MindTerm(this.oo, this.on);
        mindTerm.n4(this);
        mindTerm.or = this.e9;
        new Thread(mindTerm).start();
    }
    
    public final void n2() {
        final MindTerm mindTerm = new MindTerm(MindTerm.oy, MindTerm.oz);
        mindTerm.n4(this);
        new Thread(mindTerm).start();
    }
    
    public final void ed() {
        if (!this.n5()) {
            return;
        }
        this.oq.stop();
    }
    
    public final void n1() {
        if (!this.n5()) {
            return;
        }
        final Enumeration<MindTerm> elements = MindTerm.oa.elements();
        while (elements.hasMoreElements()) {
            final MindTerm mindTerm = elements.nextElement();
            if (mindTerm.oq != null) {
                mindTerm.oq.stop();
            }
        }
    }
    
    static {
        MindTerm.oz = new Properties();
        MindTerm.oy = new Properties();
        MindTerm.ox = "<unknown>";
        MindTerm.ow = "<unknown>";
        MindTerm.ov = "<unknown>";
        MindTerm.ou = "<unknown>";
        MindTerm.ot = "<unknown>";
        MindTerm.oa = new Hashtable();
    }
}
