import java.awt.event.ActionEvent;
import netscape.javascript.JSException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Button;
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
    private boolean e;
    private JSObject f;
    private b g;
    private long h;
    private String i;
    
    public VoipApplet() {
        this.a = new TextArea(25, 80);
        new Button("Full");
        new Button("Connect");
        new Button("Ports");
        new Button("Ping");
        new Button("Ploss");
        new Button("Sip");
        new Button("Disconnect");
        new Button("JS");
        this.c = "192.168.1.108";
        this.d = false;
        this.e = true;
        this.h = 0L;
    }
    
    private void a() {
        e.a("STRT", "Init Test");
        this.b = new c(this, this.c);
    }
    
    public void fromJS(final String s, final String d) {
        if (!this.d) {
            return;
        }
        e.b("FRJS", "From JS  " + s + "  " + d);
        if (!this.e && s.equals("start")) {
            this.b.d = d;
            e.a("FR-JS", "start test " + d);
        }
        if (!this.e && s.equals("server")) {
            this.c(d);
        }
        if (s.equals("unlock")) {
            this.a(d);
        }
        if (s.equals("unlock2")) {
            this.b(d);
        }
    }
    
    private void a(final String s) {
        final String[] split = s.split("[|]");
        e.a("UNLOCK", "received " + split.length + "arguments");
        if (split.length >= 1 && split.length >= 2) {
            this.i = split[1];
            e.a("UNLOCK", "parsing settings");
            this.b.f(this.i);
        }
        this.h = Math.round(Math.random() * 1000000.0);
        this.a("unlockReceived", new Long(this.h).toString());
    }
    
    private void b(final String s) {
        if (s.equals(e.a("lol-" + this.h + "-voip").substring(0, 16))) {
            this.e = false;
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
    
    private void c(final String c) {
        this.c = c;
        this.b.a(c);
        this.a("serverReceived", "orly");
    }
    
    public final void a(final String s, final String s2) {
        e.a("JSCPT", s + " - " + s2);
        if (!this.d) {
            return;
        }
        if (this.f == null) {
            return;
        }
        final Object[] array;
        (array = new Object[2])[0] = s;
        array[1] = s2;
        this.f.call("fromJava", array);
    }
    
    public void init() {
        System.out.println("CREATE APPLET");
        System.out.println("md5 of apple: " + e.a("apple"));
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
        Label_0198: {
            Color color = null;
            Label_0196: {
                if (parameter2 != null) {
                    this.d = true;
                    try {
                        background = new Color(Integer.valueOf(parameter2, 16));
                        break Label_0198;
                    }
                    catch (NumberFormatException ex) {
                        color = new Color(14540253);
                        break Label_0196;
                    }
                }
                color = new Color(14540253);
            }
            background = color;
        }
        this.setBackground(background);
        if (this.d) {
            try {
                this.f = JSObject.getWindow((Applet)this);
            }
            catch (JSException ex2) {
                e.c("INIT ", "couldnt create java to javascript link");
                System.out.println("couldnt create java to javascript link");
            }
        }
        else {
            e.a("INIT ", "javascript link disabled");
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("tcpport")) != null) {
            try {
                a(Integer.parseInt(parameter4));
            }
            catch (NumberFormatException ex3) {}
        }
        final String parameter5;
        if ((parameter5 = this.getParameter("udpport")) != null) {
            try {
                b(Integer.parseInt(parameter5));
            }
            catch (NumberFormatException ex4) {}
        }
        final String parameter6;
        if ((parameter6 = this.getParameter("latencylength")) != null) {
            try {
                c(Integer.parseInt(parameter6));
            }
            catch (NumberFormatException ex5) {}
        }
        this.a();
        (this.g = new b(this, this.b)).start();
    }
    
    public void destroy() {
        System.out.println("DESTROY APPLET");
        this.removeAll();
        e.a((VoipApplet)null);
        this.g.a();
        this.a = null;
        this.b = null;
        this.f = null;
        this.g = null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Full")) {
            this.b.d = "full";
        }
        if (actionEvent.getActionCommand().equals("Connect")) {
            this.b.d = "connect";
        }
        if (actionEvent.getActionCommand().equals("Ports")) {
            this.b.d = "ports";
        }
        if (actionEvent.getActionCommand().equals("Ping")) {
            this.b.d = "ping";
        }
        if (actionEvent.getActionCommand().equals("Ploss")) {
            this.b.d = "packetloss";
        }
        if (actionEvent.getActionCommand().equals("Sip")) {
            this.b.d = "sip";
        }
        if (actionEvent.getActionCommand().equals("Disconnect")) {
            this.b.d = "disconnect";
        }
        if (actionEvent.getActionCommand().equals("JS")) {
            this.a("lol", "lol");
        }
    }
}
