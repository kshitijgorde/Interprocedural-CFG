// 
// Decompiled by Procyon v0.5.30
// 

package ChatRoom;

import java.awt.Container;
import java.awt.TextComponent;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Component;
import java.net.Socket;
import java.net.SocketException;
import java.awt.TextArea;
import java.io.IOException;
import java.awt.FontMetrics;
import java.util.Random;
import java.awt.Button;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

public class ChatRoomApplet extends Applet implements Runnable
{
    public int a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public Color h;
    public Color i;
    public Color j;
    public Color k;
    public String fstrTitle;
    public static final Font FTITLEFONT;
    public static final Font FTEXTFONT;
    public Rectangle l;
    public e m;
    public Button n;
    public Button o;
    public f fTextInputArea;
    public d p;
    public g q;
    public boolean r;
    public boolean s;
    public static final int DEFAULT_PORT = 6667;
    public static final String DEFAULT_HOST = "chat.homestead.com";
    public static final String DEFAULT_USERNAME = "Anonymous";
    public static final int MAX_CHANNEL = 200;
    public static final int MAX_TEXT = 200;
    public static final int MAX_USERNAME = 20;
    public static Random idGen;
    public h t;
    public int u;
    public String v;
    public b w;
    public int x;
    public int y;
    public int z;
    public int aa;
    public int ab;
    public int ac;
    public int ad;
    public int ae;
    public int af;
    public int ag;
    public int ah;
    public int ai;
    public int aj;
    public static final Font ak;
    public static final Font al;
    public static final Font am;
    public static final Font an;
    public static FontMetrics ao;
    
    private static String a(String s, final int n) {
        s = s.replace(' ', '_').replace(',', '^').replace('\\', '/');
        if (s.startsWith("http://www")) {
            s = "http://" + s.substring(11);
        }
        if (s.startsWith("http://homestead.com/")) {
            s = b(s);
        }
        if (s.startsWith("http://")) {
            s = s.substring(7);
        }
        String s2 = c(s) + "#" + n;
        final int length = s2.length();
        if (length > 200) {
            s2 = s2.substring(length - 200, length);
        }
        return s2;
    }
    
    public final Color GetColorParameter(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            final int index = parameter.indexOf(58);
            final int lastIndex = parameter.lastIndexOf(58);
            if (index > 0 && lastIndex != index) {
                try {
                    return new Color(Integer.parseInt(parameter.substring(0, index)), Integer.parseInt(parameter.substring(index + 1, lastIndex)), Integer.parseInt(parameter.substring(lastIndex + 1)));
                }
                catch (NumberFormatException ex) {}
            }
        }
        return color;
    }
    
    public synchronized void stop() {
        try {
            if (this.w != null) {
                this.w.b();
            }
        }
        catch (IOException ex) {
            System.err.println("IO Error: quitting: " + ex);
        }
        this.CleanUp();
        this.DeleteComponents();
        super.stop();
    }
    
    public void a(final String b) {
        ((TextComponent)this.q).setText(this.b);
        ((Component)this.q).requestFocus();
        ((TextComponent)this.q).selectAll();
        this.b = b;
        this.f = ChatRoom.h.c(b);
    }
    
    public void a() {
        this.b += this.RandomId();
        ((TextComponent)this.q).setText(this.b);
        this.LoginAndJoin();
    }
    
    private void b() {
        this.showStatus("Connecting to chat server...");
        if (!this.CreateClient()) {
            return;
        }
        ((Thread)(this.t = new h(this.w, (TextArea)this.p, this))).start();
        if (!this.LoginAndJoin()) {
            return;
        }
        this.AppendToMainWindow("Connected and signed in.");
        this.showStatus("Connected to chat server.");
    }
    
    public boolean CreateClient() {
        try {
            this.w = new b(this.v, this.u);
            final Socket a = this.w.a;
            System.err.println("Connected to server at " + (a.getInetAddress() + ":" + a.getPort()));
        }
        catch (SocketException ex3) {
            this.AppendToMainWindow("Connection refused.\nYou could be behind a firewall or our server is down.\nPlease try again by pressing the reload button on your browser.\n");
            this.CleanUp();
            return false;
        }
        catch (IOException ex) {
            this.Error("connect", ex);
            return false;
        }
        catch (SecurityException ex2) {
            this.Error("security", ex2);
            return false;
        }
        return true;
    }
    
    private void c() {
        this.a(this.size(), this.fstrTitle);
        ((Component)this.fTextInputArea).reshape(67, this.ae, this.af, 20);
        ((Component)this.fTextInputArea).setBackground(this.h);
        ((Component)this.fTextInputArea).setForeground(this.i);
        ((Component)this.p).reshape(3, 23, this.y, this.z);
        ((Component)this.p).setBackground(this.h);
        ((Component)this.p).setForeground(this.i);
        ((Component)this.q).reshape(67, this.ac, this.ad, 20);
        ((Component)this.q).setBackground(this.h);
        ((Component)this.q).setForeground(this.i);
        this.n.reshape(this.ag, this.ah, 45, 20);
        this.n.setBackground(this.j);
        this.n.setForeground(this.k);
        this.n.setFont(ChatRoomApplet.am);
        this.o.reshape(this.ai, this.aj, 50, 20);
        this.o.setBackground(this.j);
        this.o.setForeground(this.k);
        this.o.setFont(ChatRoomApplet.an);
    }
    
    public void StartNounRuntime() {
        this.l = this.bounds();
        if (this.m == null) {
            this.add((Component)(this.m = this.e()));
        }
        if (this.r) {
            this.c();
            this.r = false;
        }
        ((Component)this.m).show();
        ((Container)this.m).validate();
        ((Component)this.m).repaint();
        ((TextComponent)this.p).setText("");
        this.p.a("Connecting to chat server...\n");
        ((TextComponent)this.q).setEditable(false);
        ((TextComponent)this.fTextInputArea).setEditable(false);
        this.n.disable();
        this.o.disable();
        ((Component)this.fTextInputArea).requestFocus();
    }
    
    public void start() {
        this.invalidate();
        this.repaint();
        this.StartNounRuntime();
        this.l = this.bounds();
        this.c();
        if (this.s) {
            new Thread(this).start();
        }
    }
    
    public void ChangeUserName() {
        ((TextComponent)this.q).setEditable(false);
        ((TextComponent)this.fTextInputArea).setEditable(false);
        this.n.disable();
        this.o.disable();
        String trim = ((TextComponent)this.q).getText().trim();
        if (trim.length() == 0) {
            trim = " ";
        }
        if (!this.b.equalsIgnoreCase(trim)) {
            try {
                this.g = this.b;
                this.b = trim;
                this.f = ChatRoom.h.c(trim);
                if (this.f.length() > 20) {
                    this.f = this.f.substring(0, 21);
                    this.b = ChatRoom.h.a(this.f);
                }
                ((TextComponent)this.q).setText(this.b);
                System.err.println("Changing username to " + this.b);
                this.w.a(this.f);
            }
            catch (IOException ex) {
                this.Error("nick", ex);
                return;
            }
        }
        ((TextComponent)this.q).setEditable(true);
        ((TextComponent)this.fTextInputArea).setEditable(true);
        this.n.enable();
        this.o.enable();
    }
    
    private boolean d() {
        System.err.println("The document base is " + this.getDocumentBase());
        this.getDocumentBase().toString().toLowerCase();
        return true;
    }
    
    public String RandomId() {
        return Integer.toString(Math.abs(ChatRoomApplet.idGen.nextInt()) % 100);
    }
    
    private static String b(final String s) {
        String s2 = s.substring(7);
        if (s2.startsWith("www.")) {
            s2 = s2.substring(4);
        }
        final int index = s2.indexOf(47, 14);
        return s2.substring(14, index) + ".homestead.com" + s2.substring(index, s2.length());
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        if (this.fTextInputArea != null) {
            this.l = this.bounds();
            this.c();
        }
    }
    
    private final void a(final Dimension dimension, final String s) {
        if (ChatRoomApplet.ao == null) {
            ChatRoomApplet.ao = Toolkit.getDefaultToolkit().getFontMetrics(ChatRoomApplet.ak);
        }
        this.x = (dimension.width - ChatRoomApplet.ao.stringWidth(s)) / 2;
        this.y = dimension.width - 3 - 3;
        this.z = dimension.height - 23 - 49;
        final int n = dimension.height - 49;
        this.aa = n + 19;
        this.ab = n + 41;
        this.ac = n + 3;
        this.ae = n + 26;
        this.ad = Math.min(180, dimension.width - 67 - 50 - 3 - 3);
        this.af = dimension.width - 67 - 45 - 3 - 5;
        this.ag = dimension.width - 45 - 3;
        this.ah = this.ae;
        this.ai = dimension.width - 50 - 3;
        this.aj = this.ac;
    }
    
    public void SubmitPost() {
        ((TextComponent)this.q).setEditable(false);
        ((TextComponent)this.fTextInputArea).setEditable(false);
        this.n.disable();
        this.o.disable();
        String s = ((TextComponent)this.fTextInputArea).getText();
        if (s.length() > 200) {
            s = s.substring(0, 200);
        }
        if (s.length() == 0) {
            s = " ";
        }
        if (!this.t.f) {
            return;
        }
        try {
            this.w.a(this.d, s);
        }
        catch (IOException ex) {
            this.Error("notice", ex);
            return;
        }
        this.AppendToMainWindow("<" + this.b + "> " + s + "\n");
        ((TextComponent)this.fTextInputArea).setText("");
    }
    
    public boolean LoginAndJoin() {
        this.f = ChatRoom.h.c(this.b);
        try {
            this.w.c("IRCX");
            this.w.a(this.f, "chatclient", this.e, this.c, "tneilctahc");
            System.err.println("Logged on as " + this.b + " from " + this.c + "@" + this.e);
            this.w.b(this.d);
            System.err.println("Joined channel " + this.d);
        }
        catch (IOException ex) {
            this.Error("logon", ex);
            return false;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.s) {
            graphics.setColor(Color.white);
            graphics.fillRect(this.l.x, this.l.y, this.l.width, this.l.height);
            graphics.setColor(Color.black);
            graphics.setFont(new Font("Helvetica", 1, 18));
            graphics.drawString("This applet can only be run ", 20, 70);
            graphics.drawString("from www.homestead.com...  ", 20, 100);
            return;
        }
        this.PaintAtRunTime(graphics, null);
    }
    
    public ChatRoomApplet() {
        this.fstrTitle = "Chat Room";
        this.r = true;
        this.s = true;
        this.t = null;
        this.x = -1;
        this.y = -1;
        this.z = -1;
        this.aa = -1;
        this.ab = -1;
        this.ac = -1;
        this.ad = -1;
        this.ae = -1;
        this.af = -1;
        this.ag = -1;
        this.ah = -1;
        this.ai = -1;
        this.aj = -1;
    }
    
    public synchronized void CleanUp() {
        System.err.println("Clean up");
        if (this.t != null && this.t.b) {
            this.t.b = false;
            ((Thread)this.t).stop();
        }
        this.t = null;
        System.err.println("Listener thread stopped");
        try {
            if (this.w != null) {
                this.w.c();
                System.err.println("Connection closed");
            }
        }
        catch (IOException ex) {
            System.err.println("Couldn't close connection: " + ex);
        }
        this.w = null;
        if (this.m != null) {
            ((TextComponent)this.q).setEditable(false);
            ((TextComponent)this.fTextInputArea).setEditable(false);
            this.n.disable();
            this.o.disable();
        }
        this.repaint();
    }
    
    public void PaintChatLabels(final Graphics graphics) {
        graphics.setColor(this.k);
        graphics.setFont(ChatRoomApplet.ak);
        graphics.drawString(this.fstrTitle, this.x, 18);
        graphics.setFont(ChatRoomApplet.al);
        graphics.drawString("Name:", 7, this.aa);
        graphics.drawString("Message:", 7, this.ab);
    }
    
    static {
        FTITLEFONT = new Font("TimesRoman", 1, 16);
        FTEXTFONT = new Font("Helvetica", 0, 12);
        ChatRoomApplet.idGen = new Random();
        ak = new Font("", 1, 14);
        al = new Font("", 1, 12);
        am = new Font("", 0, 12);
        an = new Font("", 0, 12);
        ChatRoomApplet.ao = null;
    }
    
    public void Error(final String s, final Exception ex) {
        this.AppendToMainWindow("Fatal Error: " + s + ".\nPress reload button on browser to restart\n");
        System.err.println("Error: " + s + ": " + ex);
        this.CleanUp();
    }
    
    public final String GetStringParameter(final String s, final String s2) {
        String parameter = this.getParameter(s);
        if (parameter == null || parameter.equals("")) {
            parameter = s2;
        }
        return parameter;
    }
    
    public final int GetIntParameter(final String s, final int n) {
        final String parameter = this.getParameter(s);
        int int1 = n;
        if (parameter != null) {
            try {
                int1 = Integer.parseInt(parameter);
            }
            catch (Exception ex) {}
        }
        return int1;
    }
    
    public void DeleteComponents() {
        if (this.m != null) {
            ((Container)this.m).removeAll();
        }
        this.removeAll();
        this.m = null;
        this.p = null;
        this.q = null;
        this.fTextInputArea = null;
        this.n = null;
        this.o = null;
        this.r = true;
    }
    
    public synchronized void AppendToMainWindow(final String s) {
        try {
            this.p.a(s);
            ((TextComponent)this.q).setEditable(true);
            ((TextComponent)this.fTextInputArea).setEditable(true);
            this.n.enable();
            this.o.enable();
        }
        catch (NullPointerException ex) {}
    }
    
    private static String c(final String s) {
        String substring = s;
        int n;
        if ((n = s.lastIndexOf("_ns3")) != -1 || (n = s.lastIndexOf("_ns4")) != -1 || (n = s.lastIndexOf("~")) != -1) {
            substring = s.substring(0, n);
        }
        return substring;
    }
    
    public void run() {
        System.err.println("Starting connection thread.");
        this.b();
        this.invalidate();
        this.repaint();
        System.err.println("Connection thread finished");
    }
    
    public void init() {
        this.s = this.d();
        if (this.s) {
            this.u = this.GetIntParameter("PORT", 6667);
            this.v = this.GetStringParameter("HOST", "chat.homestead.com");
            this.b = this.GetStringParameter("USERNAME", "Anonymous");
            this.b += this.RandomId();
            this.g = this.b;
            this.a = this.GetIntParameter("NOUNID", 0);
            System.err.println("The nounid is " + this.a);
            this.h = this.GetColorParameter("BACKCOLOR", Color.white);
            this.i = this.GetColorParameter("TEXTCOLOR", Color.black);
            this.j = this.GetColorParameter("TITLEBACKCOLOR", new Color(206, 207, 214));
            this.k = this.GetColorParameter("TITLETEXTCOLOR", Color.black);
            this.fstrTitle = this.GetStringParameter("TITLE", "Chatroom");
            System.err.println("The title is " + this.fstrTitle);
            String s = this.getDocumentBase().toString().toLowerCase();
            if (!s.endsWith(".html") && !s.endsWith(".asp") && !s.endsWith(".pwd")) {
                if (!s.endsWith("/")) {
                    s += "/";
                }
                s += "index.html";
            }
            this.d = "#" + this.GetStringParameter("CHANNEL", a(s, this.a));
            System.err.println("The channel is " + this.d);
            this.c = "HomesteadUser";
            this.e = "unknown";
        }
    }
    
    private e e() {
        final e e = new e(this);
        ((Container)e).setLayout(null);
        ((TextComponent)(this.fTextInputArea = new f(this))).setEditable(true);
        ((TextComponent)(this.q = new g(this, this.b))).setEditable(true);
        ((Component)this.q).setBackground(Color.white);
        this.n = new Button("Submit");
        this.o = new Button("Change");
        ((TextComponent)(this.p = new d(this))).setEditable(false);
        ((Component)this.p).setBackground(Color.white);
        ((Component)e).setBackground(this.j);
        ((Container)e).add((Component)this.fTextInputArea);
        ((Container)e).add((Component)this.p);
        ((Container)e).add((Component)this.q);
        ((Container)e).add(this.n);
        ((Container)e).add(this.o);
        return e;
    }
    
    public final void PaintAtRunTime(final Graphics graphics, final Rectangle rectangle) {
        final Rectangle bounds = this.bounds();
        if (this.m != null) {
            ((Component)this.m).setBackground(this.j);
            ((Component)this.m).reshape(bounds.x, bounds.y, bounds.width, bounds.height);
            ((Container)this.m).validate();
            ((Component)this.m).repaint();
        }
    }
}
