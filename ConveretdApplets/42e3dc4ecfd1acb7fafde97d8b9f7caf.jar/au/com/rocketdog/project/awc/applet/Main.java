// 
// Decompiled by Procyon v0.5.30
// 

package au.com.rocketdog.project.awc.applet;

import java.util.HashMap;
import java.awt.Dimension;
import javax.swing.UIManager;
import java.io.LineNumberReader;
import java.util.Iterator;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Set;
import java.util.Locale;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.TreeSet;
import java.awt.Container;
import java.awt.LayoutManager;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.Component;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Hashtable;
import java.awt.MediaTracker;
import java.awt.Frame;
import java.applet.AppletContext;
import java.util.SortedSet;
import java.util.Map;
import javax.swing.JApplet;

public class Main extends JApplet
{
    public static String a;
    public static String b;
    public static final Map c;
    public static SortedSet d;
    public static SortedSet e;
    public static SortedSet f;
    private static SortedSet g;
    public static String h;
    public static String i;
    private e j;
    private c k;
    private f l;
    private static AppletContext m;
    private static Frame n;
    private static MediaTracker o;
    public static u p;
    private static Hashtable q;
    public static int r;
    public static Hashtable s;
    public static String[] t;
    public static String[] u;
    private boolean v;
    
    public Main() {
        this.v = false;
    }
    
    private static void i() {
        Main.t = new String[7];
        (Main.u = new String[7])[0] = Main.p.a("ban.time.onehour");
        Main.u[1] = Main.p.a("ban.time.oneday");
        Main.u[2] = Main.p.a("ban.time.oneweek");
        Main.u[3] = Main.p.a("ban.time.twoweeks");
        Main.u[4] = Main.p.a("ban.time.onemonth");
        Main.u[5] = Main.p.a("ban.time.staff");
        Main.u[6] = Main.p.a("ban.time.twodays");
        Main.t[0] = Main.p.a("gender.male");
        Main.t[1] = Main.p.a("gender.female");
        Main.t[2] = Main.p.a("gender.group");
        Main.t[3] = Main.p.a("gender.group");
        Main.t[4] = Main.p.a("gender.group");
        Main.t[5] = Main.p.a("gender.group");
        Main.t[6] = Main.p.a("gender.other");
    }
    
    public static void a(final int n, final int n2) {
        if (n2 == n.b().s()) {
            b("http://" + Main.b + "/awc/servlet/dispatch?CMD=cmd.user.viewuser&id=" + n, "frameContent");
        }
        else {
            if (n2 == 0) {
                b("http://www.anywebcam.com", "_blank");
                return;
            }
            if (Main.q == null) {
                j();
            }
            b("http://my." + Main.q.get(Integer.toString(n2)).toString() + "/" + b(n, n2), "_blank");
        }
    }
    
    private static String b(final int n, final int n2) {
        final String string = Main.q.get(Integer.toString(n2)).toString();
        try {
            return new StringTokenizer(new BufferedReader(new InputStreamReader(new BufferedInputStream(new URL("http://www." + string + "/awc/servlet/id?id=" + n).openStream()))).readLine(), "|").nextToken();
        }
        catch (Exception ex) {
            b.a(ex, 3);
            return null;
        }
    }
    
    private static void j() {
        try {
            Main.q = new Hashtable();
            String line;
            while ((line = new BufferedReader(new InputStreamReader(new BufferedInputStream(new URL("http://www.anywebcam.com/awc/servlet/dispatch?CMD=cmd.domains.list").openStream()))).readLine()) != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                if (stringTokenizer.hasMoreTokens()) {
                    stringTokenizer.nextToken();
                    final String nextToken = stringTokenizer.nextToken();
                    stringTokenizer.nextToken();
                    stringTokenizer.nextToken();
                    Main.q.put(stringTokenizer.nextToken(), nextToken);
                }
            }
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
    
    public String a(final String s, final String s2) {
        return (this.getParameter(s) == null) ? s2 : this.getParameter(s);
    }
    
    public void a() {
        ImageRes.a(Main.o = new MediaTracker(this));
        this.setBackground(dj.w);
        Container parent;
        for (parent = this; !(parent instanceof Frame) && parent != null; parent = ((Frame)parent).getParent()) {}
        Main.n = (Frame)parent;
        this.getContentPane().setLayout(null);
        final h f = h.f();
        this.getContentPane().add(f);
        Main.m = this.getAppletContext();
        f.a(0);
    }
    
    public void b() {
        Main.r = Integer.parseInt(this.a("cp", "6667"));
        final n b = n.b();
        b.b(this.a("u", "guest"));
        b.b(Integer.parseInt(this.a("i", "0")));
        b.c(Integer.parseInt(this.a("si", "0")));
        b.a(this.a("s", ""));
        b.a(Integer.parseInt(this.a("x", "0")));
        b.d(Integer.parseInt(this.a("y", "0")));
        b.e(Integer.parseInt(this.a("bcs", "4000")));
        if ("Y".equals(this.a("bcb", "N"))) {
            b.g(true);
        }
        if ("Y".equals(this.a("sp", "N"))) {
            b.b(true);
        }
        if ("Y".equals(this.a("f", "N"))) {
            b.e(true);
        }
        if ("Y".equals(this.a("t", "N"))) {
            b.c(true);
        }
        if ("Y".equals(this.a("m", "N"))) {
            b.f(true);
        }
        if ("Y".equals(this.a("ap", "N"))) {
            b.d(true);
        }
        if ("1".equals(this.a("CF", "0"))) {
            b.m();
        }
        if (this.a("op", "").equals("TRUE")) {
            b.a(true);
        }
        b.q();
        final String a = this.a("sites", "");
        Main.a = this.a("chat", "chat.anywebcam.com");
        final StringTokenizer stringTokenizer = new StringTokenizer(a, ",");
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
            final String nextToken = stringTokenizer.nextToken();
            final int index = nextToken.indexOf(" ");
            Main.s.put(nextToken.substring(index, nextToken.length()).trim(), nextToken.substring(0, index).trim());
        }
    }
    
    public void c() {
        try {
            Main.p = new u(false);
            final StringTokenizer stringTokenizer = new StringTokenizer("4.1.12", ".");
            stringTokenizer.nextToken();
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final StringTokenizer stringTokenizer2 = new StringTokenizer(Main.p.a("lang.version"), ".");
            stringTokenizer2.nextToken();
            if (Integer.parseInt(stringTokenizer2.nextToken()) != int1) {
                Main.p.a();
            }
            if (n.b().o()) {
                i();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public SortedSet a(final String s) {
        final TreeSet<String> set = new TreeSet<String>();
        try {
            final File file = new File(Main.i + System.getProperty("file.separator") + s);
            if (!file.exists()) {
                new FileOutputStream(file).close();
                return set;
            }
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                set.add(line);
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return set;
    }
    
    public void d() {
        final Locale default1 = Locale.getDefault();
        Main.i = n.b().ab();
        new File(Main.i).mkdirs();
        t.a(Main.i + System.getProperty("file.separator") + "settings_" + default1.getLanguage() + "_" + default1.getCountry() + ".properties", this.a("l", "EN"));
        final File file = new File(Main.i + System.getProperty("file.separator") + "log.txt");
        if (file.exists()) {
            file.delete();
        }
        try {
            new FileOutputStream(Main.i + System.getProperty("file.separator") + "log.txt").close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        b.a(true, Integer.parseInt(this.a("debug", "0")), "FILE", Main.i + System.getProperty("file.separator") + "log.txt");
        b.a("APPLET VERSION : 4.1.12", 99);
        b.a("LANG VERSION : 4", 99);
        b.a("JAVA DETAILS : " + System.getProperty("java.vendor", "") + " " + System.getProperty("java.version", "") + " " + System.getProperty("java.class.version", ""), 99);
        b.a("OS : " + System.getProperty("os.name", "") + " " + System.getProperty("os.version", "") + " " + System.getProperty("os.arch", ""), 99);
        b.a("BROWSER : " + this.a("browser", "error"), 99);
        Main.d = this.a("ban.txt");
        Main.f = this.a("allow.txt");
        if (n.b().h() >= 1) {
            Main.e = this.a("favchat.txt");
            Main.g = this.a("favcam.txt");
        }
        try {
            final File file2 = new File(Main.i + System.getProperty("file.separator") + "shortcuts.txt");
            if (!file2.exists()) {
                new FileOutputStream(file2).close();
            }
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
            int n = 1;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Main.c.put("F" + n, line);
                ++n;
            }
        }
        catch (Exception ex2) {
            b.a(ex2, 3);
        }
    }
    
    public void e() {
        (this.j = new e()).start();
        (this.k = new c()).start();
        (this.l = new f()).start();
        Main.h = "f7LA5r5etl3xoA";
        p.a();
        if (new a9().b()) {
            Main.m = this.getAppletContext();
            b("http://" + Main.b + "/awc/html/pirate.html", "_top");
            this.destroy();
        }
    }
    
    public static boolean b(final String s) {
        return Main.d.contains(s);
    }
    
    public static boolean c(final String s) {
        return Main.f.contains(s);
    }
    
    public static boolean d(final String s) {
        return Main.g.contains(s);
    }
    
    public static boolean e(final String s) {
        return Main.e.contains(s);
    }
    
    public static void f(final String s) {
        b(Main.d, s, "ban.txt");
    }
    
    public static void g(final String s) {
        b(Main.f, s, "allow.txt");
    }
    
    public static void h(final String s) {
        a(Main.d, s, "ban.txt");
    }
    
    public static void i(final String s) {
        a(Main.f, s, "allow.txt");
    }
    
    public static void j(final String s) {
        b(Main.g, s, "favcam.txt");
    }
    
    public static void k(final String s) {
        a(Main.g, s, "favcam.txt");
    }
    
    public static void l(final String s) {
        b(Main.e, s, "favchat.txt");
    }
    
    public static void m(final String s) {
        a(Main.e, s, "favchat.txt");
    }
    
    private static void a(final Set set, final String s, final String s2) {
        set.remove(s);
        a(set, s2);
    }
    
    private static void b(final Set set, final String s, final String s2) {
        set.add(s);
        a(set, s2);
    }
    
    private static void a(final Set set, final String s) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(Main.i + System.getProperty("file.separator") + s);
            final PrintStream printStream = new PrintStream(fileOutputStream);
            final Iterator<Object> iterator = set.iterator();
            while (iterator.hasNext()) {
                printStream.println(iterator.next().toString());
            }
            printStream.flush();
            printStream.close();
            fileOutputStream.close();
        }
        catch (IOException ex) {
            b.a(ex, 3);
        }
    }
    
    public static void f() {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(Main.i + System.getProperty("file.separator") + "shortcuts.txt");
            final PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(Main.c.get("F1").toString());
            printStream.println(Main.c.get("F2").toString());
            printStream.println(Main.c.get("F3").toString());
            printStream.println(Main.c.get("F4").toString());
            printStream.println(Main.c.get("F5").toString());
            printStream.println(Main.c.get("F6").toString());
            printStream.println(Main.c.get("F7").toString());
            printStream.println(Main.c.get("F8").toString());
            printStream.println(Main.c.get("F9").toString());
            printStream.flush();
            printStream.close();
            fileOutputStream.close();
        }
        catch (IOException ex) {
            b.a(ex, 3);
        }
    }
    
    public static void g() {
        try {
            final Runtime runtime = Runtime.getRuntime();
            final File file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "BCPath.txt");
            if (file.exists()) {
                runtime.exec(new LineNumberReader(new InputStreamReader(new FileInputStream(file))).readLine() + "Broadcaster.exe");
            }
            else if (System.getProperty("user.name") != null) {
                final File file2 = new File("C:\\\\Documents and Settings\\" + System.getProperty("user.name") + "\\BCPath.txt");
                if (!file2.exists()) {
                    runtime.exec("C:\\Program Files\\Anywebcam\\Broadcaster.exe");
                }
                else {
                    runtime.exec(new LineNumberReader(new InputStreamReader(new FileInputStream(file2))).readLine() + "Broadcaster.exe");
                }
            }
        }
        catch (Exception ex) {
            b("http://" + Main.b + "/awc/servlet/dispatch?CMD=cmd.xsl&XSL=xsl.broadcaster.downloadmain", "frameContent");
        }
    }
    
    public static Frame h() {
        return Main.n;
    }
    
    public static boolean b(final String s, final String s2) {
        try {
            Main.m.showDocument(new URL(s), s2);
            return true;
        }
        catch (Exception ex) {
            b.a(ex, 3);
            return false;
        }
    }
    
    public void init() {
        System.out.println("ANYwebcam.com Applet 4.1.12");
        System.out.println("ANYwebcam.com Cam 1.22-beta new");
        System.out.println("ANYwebcam.com Lang 4");
        try {
            final String host = this.getDocumentBase().getHost();
            if (-1 == host.indexOf("anywebcam.com") && -1 == host.indexOf("popular.com.br") && -1 == host.indexOf("anywebcam.de") && -1 == host.indexOf("anywebcam.at") && -1 == host.indexOf("anywebcam.ch")) {
                Main.m = this.getAppletContext();
                b("http://www.anywebcam.com/awc/html/pirate.html", "_top");
                this.destroy();
                return;
            }
            Main.b = host;
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            this.b();
            this.d();
            this.c();
            this.a();
            this.e();
        }
        catch (Exception ex) {
            b.a(ex, 3);
            this.destroy();
        }
    }
    
    public void setSize(final Dimension dimension) {
        super.setSize(dimension);
        if (this.v) {
            h.f().setSize(dimension);
        }
    }
    
    public void start() {
        this.v = true;
        h.f().setSize(this.getSize());
    }
    
    public void destroy() {
        System.out.println("DESTROYING ANYwebcam.com Applet 4.1.12");
        try {
            if (this.k != null) {
                this.k.d();
            }
            if (this.j != null) {
                this.j.d();
            }
            if (this.l != null) {
                this.l.d();
            }
            h.g();
            i.a();
            l.d();
            n.c();
            p.d();
        }
        catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        System.out.println("DESTROYED ANYwebcam.com Applet 4.1.12");
    }
    
    public String getAppletInfo() {
        return "ANYwebcam.com Applet 4.1.12";
    }
    
    static {
        Main.a = "chat.anywebcam.com";
        Main.b = "";
        c = new HashMap(10);
        Main.d = new TreeSet();
        Main.e = new TreeSet();
        Main.f = new TreeSet();
        Main.g = new TreeSet();
        Main.m = null;
        Main.r = 8080;
        Main.s = new Hashtable(3);
    }
}
