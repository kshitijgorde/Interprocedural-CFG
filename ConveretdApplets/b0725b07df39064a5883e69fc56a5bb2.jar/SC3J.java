import java.awt.event.KeyEvent;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import a.a.a.n;
import java.awt.event.KeyListener;
import a.a.a.g;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SC3J extends Applet implements g, KeyListener, Runnable
{
    private Thread a;
    private n b;
    private boolean c;
    private boolean d;
    private String e;
    private String f;
    private String g;
    private int h;
    private int i;
    private int j;
    
    public String getAppletInfo() {
        return "Name: SC3K - SEGA SC-3000 Emulator for Java\r\nAuthor: Saverio Russo";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "ROMImage", "String", "URL of cartridge ROM to play" }, { "DiskImage", "String", "URL of disk to load" }, { "DiskImage2", "String", "URL of disk #2 to load" }, { "Mapper", "int", "Mapper mode" }, { "ScreenMultX", "int", "Screen Width multiplier" }, { "ScreenMultY", "int", "Screen Height multiplier" } };
    }
    
    private String a(String string, final String[] array) {
        if (array == null) {
            return this.getParameter(string);
        }
        string += "=";
        String s = null;
        final int length = string.length();
        try {
            int i = 0;
            while (i < array.length) {
                if (string.equalsIgnoreCase("" + array[i].substring(0, length))) {
                    if ((s = "" + array[i].substring(length)).startsWith("\"") && (s = "" + s.substring(1)).endsWith("\"")) {
                        s = "" + s.substring(0, s.length() - 1);
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        catch (Exception ex) {}
        return s;
    }
    
    private void a(final String[] array) {
        final String a;
        if ((a = this.a("ROMImage", array)) != null) {
            this.e = a;
        }
        final String a2;
        if ((a2 = this.a("DiskImage", array)) != null) {
            this.f = a2;
        }
        final String a3;
        if ((a3 = this.a("DiskImage2", array)) != null) {
            this.g = a3;
        }
        final String a4;
        if ((a4 = this.a("Mapper", array)) != null) {
            this.h = Integer.parseInt(a4);
        }
        final String a5;
        if ((a5 = this.a("ScreenMultX", array)) != null) {
            this.i = Integer.parseInt(a5);
        }
        final String a6;
        if ((a6 = this.a("ScreenMultY", array)) != null) {
            this.j = Integer.parseInt(a6);
        }
    }
    
    public static void main(final String[] array) {
        final a a;
        (a = new a("SC3J SEGA SC-3000 Java Emulator (JavaVM)")).show();
        a.hide();
        a.resize(a.insets().left + a.insets().right + 512, a.insets().top + a.insets().bottom + 384);
        a.setResizable(false);
        final SC3J sc3J = new SC3J();
        a.add("Center", sc3J);
        sc3J.c = true;
        final String[] array2;
        (array2 = new String[3])[0] = "ROMImage=roms/sf7000_rom.sc";
        array2[1] = "DiskImage=disks/Carts02.sf7";
        sc3J.a(array2);
        sc3J.init();
        sc3J.start();
        a.show();
    }
    
    public SC3J() {
        this.c = false;
        this.d = false;
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = 0;
        this.i = 2;
        this.j = 2;
    }
    
    public void init() {
        if (!this.c) {
            this.a(null);
        }
        this.resize(512, 384);
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.addKeyListener(this);
    }
    
    public void destroy() {
    }
    
    public final void a() {
        final Graphics graphics;
        if ((graphics = this.getGraphics()) != null) {
            this.b.a(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.d) {
            this.b.a(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.d) {
            this.paint(graphics);
            return;
        }
        super.update(graphics);
    }
    
    public void run() {
        this.b = new n();
        this.d = this.b.a(this, this, this.i, this.j);
        URL codeBase = null;
        if (!this.c) {
            codeBase = this.getCodeBase();
        }
        try {
            this.b.a(codeBase);
            this.b.a(this.h);
            this.b.d();
            if (this.e.length() > 0) {
                this.b.a(this.e);
            }
            this.b.b();
            if (this.f.length() > 0) {
                this.b.a(true);
                this.b.b(this.f);
                this.b.e();
            }
            if (this.g.length() > 0) {
                this.b.c(this.g);
            }
        }
        catch (Exception ex) {}
        this.b.c();
    }
    
    public void start() {
        if (this.a == null) {
            (this.a = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.a != null) {
            this.a = null;
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (this.b != null && keyEvent.getKeyCode() != 121) {
            this.b.a(keyEvent);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (this.b != null) {
            if (keyEvent.getKeyCode() == 121) {
                this.b.a();
                return;
            }
            this.b.b(keyEvent);
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
}
