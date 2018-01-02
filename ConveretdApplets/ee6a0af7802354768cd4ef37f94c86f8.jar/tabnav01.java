import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tabnav01 extends Applet implements Runnable
{
    private int z3;
    private Dimension d;
    private Rectangle z8;
    private AudioClip[] z0;
    private tabnav01a z2;
    private Thread z11;
    int q76;
    int q19;
    Color[] q16;
    static final int q7 = 0;
    static final int q8 = 1;
    static final int q13 = 2;
    static final int q14 = 3;
    static final int q15 = 4;
    static final int q11 = 5;
    static final int q12 = 6;
    static final int q9 = 7;
    static final int q10 = 8;
    private static final String[] z5;
    private static final int[] z1;
    private static final String[] z4;
    static final int q21 = 0;
    static final int q22 = 1;
    static final String q20 = "TN";
    static final int q17 = 0;
    static final int q18 = 1;
    static final int q25 = 0;
    static final int q24 = 1;
    static final int q23 = 2;
    static final int q4 = 0;
    static final int q5 = 1;
    static final int q6 = 2;
    static final int q26 = 3;
    private final String z7 = "topmargin";
    private final String z6 = "leftmargin";
    
    public String getAppletInfo() {
        return "tabnav01 beta\r\nweb: www.celticedge.com";
    }
    
    public void init() {
        this.setLayout(null);
        this.d = this.size();
        this.q16 = new Color[9];
        this.q76 = this.q0(this.q1("topmargin"), 0, this.d.height / 2, 0);
        this.q19 = this.q0(this.q1("leftmargin"), 0, this.d.width / 2, 0);
        this.z8 = new Rectangle(0, 0, this.d.width, this.d.height);
        for (int i = 0; i <= 8; ++i) {
            this.q16[i] = this.z12(this.q1(tabnav01.z5[i] + "color"), tabnav01.z1[i]);
        }
        this.setBackground(this.q16[0]);
        for (int j = 0; j <= 2; ++j) {
            final String q1 = this.q1("audio" + tabnav01.z4[j]);
            if (q1 != null && q1.length() > 1) {
                this.z0[j] = this.getAudioClip(this.getDocumentBase(), q1);
            }
        }
        this.addNotify();
    }
    
    boolean q3(final Graphics graphics, final Image image, final Rectangle rectangle) {
        if (image != null) {
            for (int i = rectangle.y; i < rectangle.height; i += image.getHeight(null)) {
                for (int j = rectangle.x; j < rectangle.width; j += image.getWidth(null)) {
                    graphics.drawImage(image, j, i, null);
                }
            }
        }
        return image != null;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.q16[0]);
        if (this.z3 < 1) {
            graphics.fillRect(0, 0, this.d.width, this.d.height);
            graphics.setColor(this.q16[1]);
            graphics.drawRect(0, 0, this.d.width - 1, this.d.height - 1);
            graphics.drawString("applet " + ((this.z3 < 0) ? "error" : "starting"), 8, 16);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private Color z12(final String s, final int n) {
        int n2;
        if (s != null) {
            try {
                if (s.startsWith("0x")) {
                    n2 = Integer.parseInt(s.substring(2), 16);
                }
                else if (s.startsWith("#")) {
                    n2 = Integer.parseInt(s.substring(1), 16);
                }
                else {
                    n2 = Integer.parseInt(s, 10);
                }
            }
            catch (NumberFormatException ex) {
                n2 = n;
            }
        }
        else {
            n2 = n;
        }
        if (n2 > -1) {
            return new Color(n2);
        }
        return null;
    }
    
    int q0(final String s, final int n, final int n2, final int n3) {
        int int1 = 0;
        if (s != null) {
            try {
                int1 = Integer.parseInt(s);
            }
            catch (NumberFormatException ex) {}
            if (int1 < n) {
                int1 = n;
            }
            else if (int1 > n2) {
                int1 = n2;
            }
        }
        else {
            int1 = n3;
        }
        return int1;
    }
    
    String q1(final String s) {
        final String parameter;
        if ((parameter = this.getParameter(s)) != null) {
            return parameter.trim();
        }
        return null;
    }
    
    void q2(final int n) {
        if (this.z0[n] != null) {
            this.z0[n].play();
        }
    }
    
    public void start() {
        if (this.z2 == null) {
            (this.z2 = new tabnav01a(this, this.z8)).start();
        }
        if (this.z11 == null) {
            (this.z11 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.z11 != null) {
            this.z11.stop();
            this.z11 = null;
        }
    }
    
    public void run() {
        this.z11.setPriority(1);
    Label_0008_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        Thread.sleep(400L);
                        if (this.z3 > 0) {
                            this.z2.q28();
                        }
                    }
                }
                catch (InterruptedException ex) {
                    continue Label_0008_Outer;
                }
                continue;
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.arg != null && event.arg.equals("TN")) {
            if (event.id == 0) {
                --this.z3;
            }
            else {
                this.z2.q27();
                ++this.z3;
            }
            this.repaint();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public tabnav01() {
        this.z0 = new AudioClip[] { null, null, null };
    }
    
    static {
        z5 = new String[] { "back", "border", "scroll", "tab", "tabtext", "menuback", "menutext", "hintback", "hinttext" };
        z1 = new int[] { 16777215, 0, 13421772, 13421772, 0, 16777215, 0, 16777215, 0 };
        z4 = new String[] { "tab", "menu", "item" };
    }
}
