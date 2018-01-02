import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VoipApplet extends Applet implements ActionListener
{
    public TextArea a;
    private c b;
    private String c;
    private boolean d;
    private JSObject e;
    private b f;
    private long g;
    private String h;
    
    public VoipApplet() {
        this.a = new TextArea(25, 80);
        this.c = "192.168.1.108";
        this.d = true;
        this.g = 0L;
    }
    
    private void a() {
        e.a("STRT", "Init Test");
        this.b = new c(this, this.c);
        this.a("javaLoaded", "1.1");
    }
    
    public void fromJS(final String s, final String s2) {
        e.b("FRJS", "From JS  " + s + "  " + s2);
        if (!this.d && s.equals("start")) {
            final String[] split = s2.split("\\s", 2);
            this.b.g = split[0];
            c c;
            String h;
            if (split.length > 1) {
                c = this.b;
                h = split[1];
            }
            else {
                c = this.b;
                h = null;
            }
            c.h = h;
            e.a("FR-JS", "start test " + s2);
        }
        if (!this.d && s.equals("server")) {
            this.c(s2);
        }
        if (!this.d && s.equals("udpserver")) {
            this.d(s2);
        }
        if (s.equals("unlock")) {
            this.a(s2);
        }
        if (s.equals("unlock2")) {
            this.b(s2);
        }
    }
    
    private void a(final String s) {
        final String[] split = s.split("[|]");
        e.a("UNLOCK", "received " + split.length + "arguments");
        if (split.length >= 1 && split.length >= 2) {
            this.h = split[1];
            e.a("UNLOCK", "parsing settings");
            this.b.e(this.h);
        }
        this.g = Math.round(Math.random() * 1000000.0);
        this.a("unlockReceived", new Long(this.g).toString());
    }
    
    private void b(final String s) {
        if (s.equals(e.a("lol-" + this.g + "-voip").substring(0, 16))) {
            this.d = false;
            this.a("unlock2Received", "bingo");
        }
    }
    
    private static void a(final int a) {
        c.a = a;
    }
    
    private static void b(final int b) {
        c.b = b;
    }
    
    private static void c(final int c) {
        c.c = c;
    }
    
    private static void d(final int d) {
        c.d = d;
    }
    
    private static void e(final int e) {
        c.e = e;
    }
    
    private static void f(final int f) {
        c.f = f;
    }
    
    private void c(final String c) {
        this.c = c;
        this.b.a(c);
        this.a("serverReceived", "orly");
    }
    
    private void d(final String s) {
        final String[] split = s.split(":", 2);
        this.c = split[0];
        this.b.a(split[0]);
        if (split.length > 1) {
            try {
                b(Integer.parseInt(split[1]));
            }
            catch (NumberFormatException ex) {}
        }
        this.a("serverReceived", "" + this.b.c());
    }
    
    public final void a(final String s, final String s2) {
        e.a("JSCPT", s + " - " + s2);
        this.b();
        if (this.e == null) {
            return;
        }
        final Object[] array;
        (array = new Object[2])[0] = s;
        array[1] = s2;
        this.e.call("fromJava", array);
    }
    
    private void b() {
        if (this.e == null) {
            try {
                this.e = JSObject.getWindow((Applet)this);
            }
            catch (JSException ex) {
                e.c("INIT ", "couldnt create java to javascript link");
                e.c("INIT ", ex.toString());
                System.out.println("couldnt create java to javascript link");
            }
        }
    }
    
    public void init() {
        System.out.println("CREATE APPLET");
        System.out.println("md5 of apple: " + e.a("apple"));
        this.b();
        final String parameter;
        if ((parameter = this.getParameter("debug")) != null && parameter.equals("true")) {
            this.a.setEditable(false);
            this.add(this.a);
            e.a(this);
            e.a = true;
            e.b = true;
            e.c = false;
            e.d = true;
            e.e = true;
        }
        else {
            e.a = false;
            e.b = false;
            e.c = false;
            e.e = false;
            e.d = false;
        }
        final String parameter2 = this.getParameter("bgcolor");
        final String parameter3;
        if ((parameter3 = this.getParameter("server")) != null) {
            this.c = parameter3;
        }
        Color background = null;
        Label_0197: {
            Color color = null;
            Label_0195: {
                if (parameter2 != null) {
                    try {
                        background = new Color(Integer.valueOf(parameter2, 16));
                        break Label_0197;
                    }
                    catch (NumberFormatException ex) {
                        color = new Color(14540253);
                        break Label_0195;
                    }
                }
                color = new Color(14540253);
            }
            background = color;
        }
        this.setBackground(background);
        final String parameter4;
        if ((parameter4 = this.getParameter("tcpport")) != null) {
            try {
                a(Integer.parseInt(parameter4));
            }
            catch (NumberFormatException ex2) {}
        }
        final String parameter5;
        if ((parameter5 = this.getParameter("udpport")) != null) {
            try {
                b(Integer.parseInt(parameter5));
            }
            catch (NumberFormatException ex3) {}
        }
        final String parameter6;
        if ((parameter6 = this.getParameter("latencylength")) != null) {
            try {
                c(Integer.parseInt(parameter6));
            }
            catch (NumberFormatException ex4) {}
        }
        final String parameter7;
        if ((parameter7 = this.getParameter("packetlosslength")) != null) {
            try {
                e(Integer.parseInt(parameter7));
            }
            catch (NumberFormatException ex5) {}
        }
        final String parameter8;
        if ((parameter8 = this.getParameter("latencypause")) != null) {
            try {
                d(Integer.parseInt(parameter8));
            }
            catch (NumberFormatException ex6) {}
        }
        final String parameter9;
        if ((parameter9 = this.getParameter("packetlosspause")) != null) {
            try {
                f(Integer.parseInt(parameter9));
            }
            catch (NumberFormatException ex7) {}
        }
        this.a();
        (this.f = new b(this, this.b)).start();
    }
    
    public void destroy() {
        System.out.println("DESTROY APPLET");
        this.removeAll();
        e.a((VoipApplet)null);
        this.f.a();
        this.a = null;
        this.b = null;
        this.e = null;
        this.f = null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Full")) {
            this.b.g = "full";
        }
        if (actionEvent.getActionCommand().equals("Connect")) {
            this.b.g = "connect";
        }
        if (actionEvent.getActionCommand().equals("Ports")) {
            this.b.g = "ports";
        }
        if (actionEvent.getActionCommand().equals("Ping")) {
            this.b.g = "ping";
        }
        if (actionEvent.getActionCommand().equals("Ploss")) {
            this.b.g = "packetloss";
        }
        if (actionEvent.getActionCommand().equals("Sip")) {
            this.b.g = "sip";
        }
        if (actionEvent.getActionCommand().equals("Disconnect")) {
            this.b.g = "disconnect";
        }
        if (actionEvent.getActionCommand().equals("JS")) {
            this.a("lol", "lol");
        }
    }
}
