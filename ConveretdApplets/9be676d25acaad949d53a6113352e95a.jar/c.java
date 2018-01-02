import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends Applet implements Runnable
{
    private int goto;
    private String[] byte;
    private Color[] if;
    private String for;
    private int else;
    private int int;
    private Thread long;
    private String null;
    private Image do;
    private int try;
    private Color char;
    public static final Hashtable a;
    private static final Color case;
    private static final Color new;
    
    public c() {
        this.goto = 1;
        this.for = "LED StocksTicker 1.1 by Gigel Chiazna (www.chiazna.has.it)";
        this.try = 3;
        this.char = new Color(70, 70, 70);
        this.a(new String[] { "" }, new Color[] { Color.white });
    }
    
    public void if(final int goto1) {
        this.goto = goto1;
    }
    
    public void a(final Color char1) {
        this.char = char1;
        this.a();
    }
    
    private void a() {
        final Graphics graphics = this.do.getGraphics();
        graphics.setColor(this.char);
        graphics.fillRect(0, 0, this.else, this.int);
        for (int i = 0; i < this.int; i += 2) {
            graphics.setColor(c.case);
            graphics.drawLine(1, i + 1, this.else, i + 1);
        }
        graphics.dispose();
    }
    
    public void a(final String[] array, final Color[] array2) {
        this.byte = new String[array.length];
        this.if = new Color[array2.length];
        for (int i = 0; i < array.length; ++i) {
            this.byte[i] = new String(array[i]);
        }
        for (int j = 0; j < array2.length; ++j) {
            if (array2[j] == null) {
                this.if[j] = new Color(255, 155, 155);
            }
            else {
                this.if[j] = new Color(array2[j].getRed(), array2[j].getGreen(), array2[j].getBlue());
            }
        }
    }
    
    public void init() {
        this.else = this.size().width;
        this.int = this.size().height;
        this.null = "loading ...";
        this.do = this.createImage(this.else, this.int);
        this.a();
    }
    
    public void start() {
        if (this.long == null) {
            (this.long = new Thread(this)).start();
        }
    }
    
    public void stop() {
    }
    
    public void run() {
        final byte[] array = new byte[5];
        try {
            while (true) {
                final String[] array2 = new String[this.byte.length];
                final Color[] array3 = new Color[this.if.length];
                for (int i = 0; i < this.byte.length; ++i) {
                    array2[i] = new String(this.byte[i]);
                }
                for (int j = 0; j < this.if.length; ++j) {
                    array3[j] = new Color(this.if[j].getRed(), this.if[j].getGreen(), this.if[j].getBlue());
                }
                byte[] array4 = new byte[5];
                int n = 4;
                for (int k = 0; k < array2.length; ++k) {
                    final String concat = String.valueOf(String.valueOf(array2[k])).concat(" ");
                    int n2 = 0;
                    for (int l = 0; l < concat.length() * 6; ++l) {
                        final Graphics graphics = this.do.getGraphics();
                        graphics.copyArea(0, 0, this.else, this.int, -2, 0);
                        try {
                            Thread.sleep(this.goto);
                        }
                        catch (Exception ex) {}
                        if (n++ >= 4) {
                            for (int n3 = 0; n3 < 7; ++n3) {
                                graphics.setColor(c.case);
                                graphics.drawLine(this.else - 2, n3 * 2 + this.try, this.else - 2, n3 * 2 + this.try);
                            }
                            n = -1;
                            if (n2 < concat.length()) {
                                array4 = (byte[])c.a.get("".concat(String.valueOf(String.valueOf(concat.charAt(n2)))));
                                if (array4 == null) {
                                    array4 = c.a.get("?");
                                }
                                ++n2;
                            }
                            else {
                                array4 = c.a.get(" ");
                            }
                        }
                        else {
                            for (int n4 = 0; n4 < 7; ++n4) {
                                graphics.setColor(((array4[n] & 1 << n4) == 0x0) ? c.case : array3[k]);
                                graphics.drawLine(this.else - 2, n4 * 2 + this.try, this.else - 2, n4 * 2 + this.try);
                            }
                        }
                        graphics.dispose();
                        final Graphics graphics2 = this.getGraphics();
                        graphics2.drawImage(this.do, 0, 0, this);
                        graphics2.dispose();
                    }
                }
            }
        }
        catch (Exception ex2) {
            try {
                Thread.sleep(3000L);
            }
            catch (Exception ex3) {}
        }
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.do, 0, 0, this);
        graphics.dispose();
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.for);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    static {
        a = new Hashtable();
        case = new Color(70, 70, 70);
        new = new Color(255, 255, 255);
        c.a.put(" ", new byte[] { 0, 0, 0, 0, 0 });
        c.a.put("A", new byte[] { 126, 9, 9, 9, 126 });
        c.a.put("a", new byte[] { 120, 68, 68, 124, 64 });
        c.a.put("B", new byte[] { 127, 73, 73, 73, 62 });
        c.a.put("b", new byte[] { 127, 68, 68, 68, 56 });
        c.a.put("C", new byte[] { 62, 65, 65, 65, 34 });
        c.a.put("D", new byte[] { 65, 127, 65, 65, 62 });
        c.a.put("E", new byte[] { 127, 73, 73, 65, 65 });
        c.a.put("F", new byte[] { 127, 9, 9, 1, 1, 1 });
        c.a.put("G", new byte[] { 62, 65, 65, 73, 58 });
        c.a.put("H", new byte[] { 127, 8, 8, 8, 127 });
        c.a.put("I", new byte[] { 0, 65, 127, 65, 0 });
        c.a.put("J", new byte[] { 32, 64, 65, 63, 1 });
        c.a.put("K", new byte[] { 127, 8, 20, 34, 65 });
        c.a.put("L", new byte[] { 127, 64, 64, 64, 64 });
        c.a.put("M", new byte[] { 127, 2, 12, 2, 127 });
        c.a.put("N", new byte[] { 127, 4, 8, 16, 127 });
        c.a.put("O", new byte[] { 62, 65, 65, 65, 62 });
        c.a.put("P", new byte[] { 127, 9, 9, 9, 6 });
        c.a.put("Q", new byte[] { 62, 65, 81, 33, 94 });
        c.a.put("R", new byte[] { 127, 9, 25, 41, 70 });
        c.a.put("S", new byte[] { 38, 73, 73, 73, 50 });
        c.a.put("T", new byte[] { 1, 1, 127, 1, 1 });
        c.a.put("U", new byte[] { 63, 64, 64, 64, 63 });
        c.a.put("V", new byte[] { 7, 24, 96, 24, 7 });
        c.a.put("W", new byte[] { 127, 32, 24, 32, 127 });
        c.a.put("X", new byte[] { 99, 20, 8, 20, 99 });
        c.a.put("Y", new byte[] { 7, 8, 120, 8, 7 });
        c.a.put("Z", new byte[] { 97, 81, 73, 69, 67 });
        c.a.put("0", new byte[] { 62, 81, 73, 69, 62 });
        c.a.put("1", new byte[] { 0, 66, 127, 64, 0 });
        c.a.put("2", new byte[] { 98, 81, 81, 73, 70 });
        c.a.put("3", new byte[] { 34, 65, 73, 73, 54 });
        c.a.put("4", new byte[] { 24, 20, 18, 127, 16 });
        c.a.put("5", new byte[] { 39, 69, 69, 69, 57 });
        c.a.put("6", new byte[] { 60, 74, 73, 73, 49 });
        c.a.put("7", new byte[] { 1, 113, 9, 5, 3 });
        c.a.put("8", new byte[] { 54, 73, 73, 73, 54 });
        c.a.put("9", new byte[] { 70, 73, 73, 41, 30 });
        c.a.put(".", new byte[] { 0, 96, 96, 0, 0 });
        c.a.put("?", new byte[] { 2, 1, 89, 5, 2 });
        c.a.put("\\", new byte[] { 3, 4, 8, 16, 96 });
        c.a.put("/", new byte[] { 96, 16, 8, 4, 3 });
        c.a.put("-", new byte[] { 8, 8, 8, 8, 8 });
        c.a.put("@", new byte[] { 127, 65, 93, 93, 31 });
        c.a.put(":", new byte[] { 0, 0, 54, 54, 0 });
        c.a.put("^", new byte[] { 4, 2, 1, 2, 4 });
    }
}
