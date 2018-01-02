import netscape.javascript.JSObject;
import java.awt.Event;
import java.awt.Cursor;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryElevator extends Applet implements Runnable
{
    private ryStageFader \u0148;
    private ryStageFader \u0147;
    private ryEVItem[] \u0146;
    private Color \u0145;
    private Color \u0144;
    private Color \u0143;
    private Color \u0142;
    private Color \u0141;
    private Color \u0140;
    private Color \u013f;
    private Color \u013e;
    private Color \u013d;
    private Color \u013c;
    private Color \u013b;
    private Color \u013a;
    private Color \u0139;
    private Color \u0138;
    private Color \u0137;
    private Color \u0136;
    private Graphics \u0135;
    private Image \u0134;
    private Font \u0133;
    private FontMetrics \u0132;
    private int \u0131;
    private int \u0130;
    private int \u012f;
    private String \u012e;
    private Thread \u012d;
    private int \u012c;
    private Rectangle \u012b;
    private Rectangle \u012a;
    private boolean \u0129;
    private boolean \u0128;
    private boolean \u0127;
    private boolean \u0126;
    private boolean \u0125;
    private boolean \u0124;
    private int \u0123;
    private int \u0122;
    private int \u0121;
    private int \u0120;
    private String \u011f;
    private String \u011e;
    private String \u011d;
    private Rectangle[] \u011c;
    private int \u011b;
    private boolean \u011a;
    private int \u0119;
    private boolean \u0118;
    private boolean \u0117;
    private boolean \u0116;
    private int \u0115;
    private boolean \u0114;
    private boolean \u0113;
    private boolean \u0112;
    private Rectangle[] \u0111;
    private boolean \u0110;
    private boolean \u010f;
    String \u010e;
    private boolean \u010d;
    private int \u010c;
    private boolean \u010b;
    private String \u010a;
    private boolean \u0109;
    private int \u0108;
    private boolean \u0107;
    private int \u0106;
    private Image \u0105;
    private ryEVTimer \u0104;
    public String received;
    private boolean \u0103;
    private boolean \u0102;
    private boolean \u0101;
    
    private void \u015f() {
        final String s = "Elevator © 1999 Cool Focus [www.coolfocus.com]";
        final String s2 = "Elevator (c) 1999 Cool Focus [www.coolfocus.com]";
        if (this.getParameter("Copyright") == null || (!this.getParameter("Copyright").equals(s) && !this.getParameter("Copyright").equals(s2))) {
            throw new SecurityException(" Copyright parameter missing or incorrect ");
        }
    }
    
    private void \u015e() {
        final String lowerCase = this.getDocumentBase().toString().toLowerCase();
        boolean b = false;
        if (lowerCase.startsWith("file:") && lowerCase.indexOf("elevator/docs/") >= 0) {
            this.\u0103 = true;
            return;
        }
        final String parameter = this.getParameter("Base");
        if (parameter == null) {
            this.\u0103 = false;
            return;
        }
        String s = "";
        if (parameter.indexOf("|") >= 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, "|");
            final String[] array = new String[stringTokenizer.countTokens()];
            for (int i = 0; i < array.length; ++i) {
                final String nextToken = stringTokenizer.nextToken();
                if (!nextToken.startsWith("http")) {
                    array[i] = "http://" + nextToken;
                }
                else {
                    array[i] = nextToken;
                }
                s = String.valueOf(s) + array[i];
                if (lowerCase.indexOf(nextToken) >= 0 || lowerCase.startsWith("file:")) {
                    b = true;
                }
            }
        }
        else {
            final String s2 = parameter;
            if (!parameter.startsWith("http")) {
                s = "http://" + parameter;
            }
            else {
                s = parameter;
            }
            if (lowerCase.indexOf(s2) >= 0 || lowerCase.startsWith("file:")) {
                b = true;
            }
        }
        if (!b) {
            this.\u0103 = false;
            return;
        }
        try {
            this.\u0103 = this.\u015d(s);
        }
        catch (NoSuchElementException ex) {
            this.\u0103 = false;
        }
    }
    
    private boolean \u015d(final String s) throws NoSuchElementException {
        final String parameter = this.getParameter("Key");
        if (parameter == null) {
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter, "-");
        final String nextToken = stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
        final String nextToken3 = stringTokenizer.nextToken();
        final String nextToken4 = stringTokenizer.nextToken();
        return nextToken.equals(Integer.toString((s.length() * 9565 + 91 - 12766) * 19)) && nextToken2.equals(Integer.toString(this.\u015c(s, 'e') * 403)) && nextToken3.equals(Integer.toString(this.\u015c(s, 'w') * 377)) && nextToken4.equals(Integer.toString(this.\u015c(s, 's') * 519));
    }
    
    private int \u015c(final String s, final char c) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == c) {
                ++n;
            }
        }
        return n;
    }
    
    private int \u015b(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return Integer.valueOf(parameter, 16);
        }
        return color.getRGB();
    }
    
    private String \u015a(final String s, final String s2) {
        String parameter = this.getParameter(s);
        if (parameter == null) {
            parameter = s2;
        }
        return parameter;
    }
    
    public String getAppletInfo() {
        if (!this.\u0103) {
            return "\n\nUNREGISTERED Elevator v1.6 by Rob Young\nCopyright © 1999-2000 Cool Focus [www.coolfocus.com]\n\n";
        }
        return "\n\nElevator v1.6 by Rob Young\nCopyright © 1999-2000 Cool Focus [www.coolfocus.com]\n\n";
    }
    
    public void init() {
        this.\u015e();
        this.\u015f();
        System.out.println(this.getAppletInfo());
        if (!this.\u0103) {
            this.\u0102 = true;
        }
        this.\u0101 = (this.\u0103 || this.getDocumentBase().toString().toLowerCase().startsWith("file:"));
        this.\u0131 = this.size().width;
        this.\u0130 = this.size().height;
        this.\u012f = this.\u0130;
        this.\u0134 = this.createImage(this.\u0131, this.\u0130);
        this.\u0135 = this.\u0134.getGraphics();
        if (this.\u0131 < 133) {
            this.resize(133, this.\u0130);
            this.showStatus("Elevator width must be at least 133.");
            System.out.println("Elevator ERROR: width must be at least 133.\n\n");
        }
        this.\u0145 = new Color(this.\u015b("BgColor", new Color(0, 0, 0)));
        this.\u0144 = new Color(this.\u015b("PanelColor", new Color(64, 64, 64)));
        this.\u0143 = new Color(this.\u015b("LocationColor", this.\u0144));
        this.\u0142 = new Color(this.\u015b("TextColor", Color.white));
        this.\u0141 = new Color(this.\u015b("FocusTextColor", new Color(0, 255, 0)));
        this.\u0140 = new Color(this.\u015b("PressTextColor", new Color(144, 128, 112)));
        this.\u013f = new Color(this.\u015b("SelectTextColor", new Color(204, 187, 170)));
        this.\u013e = new Color(this.\u015b("IconColor", new Color(144, 128, 112)));
        this.\u013d = new Color(this.\u015b("FocusIconColor", Color.white));
        this.\u013c = new Color(this.\u015b("PressIconColor", new Color(118, 88, 58)));
        this.\u013b = new Color(this.\u015b("ArrowColor", new Color(204, 187, 170)));
        this.\u013a = new Color(this.\u015b("LineColor", new Color(204, 187, 170)));
        this.\u0139 = new Color(this.\u015b("LocationTextColor", Color.white));
        this.\u0138 = new Color(this.\u015b("SelectorColor", new Color(64, 48, 32)));
        this.\u0137 = new Color(this.\u015b("VertLineColor", this.\u0143));
        this.\u0136 = this.\u0142;
        this.setBackground(this.\u0145);
    }
    
    public void start() {
        this.\u012e = this.\u015a("DefaultTarget", "_top");
        String parameter = this.getParameter("Font");
        if (parameter == null) {
            parameter = "Dialog,plain,12";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
        final String nextToken = stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
        int n = 0;
        if (nextToken2.equalsIgnoreCase("plain")) {
            n = 0;
        }
        if (nextToken2.equalsIgnoreCase("bold")) {
            n = 1;
        }
        if (nextToken2.equalsIgnoreCase("italic")) {
            n = 2;
        }
        if (nextToken2.equalsIgnoreCase("bolditalic")) {
            n = 3;
        }
        this.\u0133 = new Font(nextToken, n, Integer.parseInt(stringTokenizer.nextToken()));
        this.\u0135.setFont(this.\u0133);
        this.\u0132 = this.getFontMetrics(this.\u0133);
        final String parameter2 = this.getParameter("BgImage");
        if (parameter2 != null) {
            try {
                this.\u0105 = this.getImage(new URL(this.getDocumentBase(), parameter2));
            }
            catch (Exception ex) {
                this.\u0105 = null;
            }
            if (this.\u0105 != null) {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.\u0105, 0);
                try {
                    mediaTracker.waitForID(0);
                }
                catch (Exception ex2) {
                    this.\u0105 = null;
                }
            }
        }
        this.\u0125 = (System.getProperty("java.vendor").startsWith("Microsoft") || System.getProperty("java.vendor").startsWith("Sun Micro"));
        if (this.\u0125) {
            this.\u0125 = (Float.valueOf(System.getProperty("java.version").substring(0, 3)) >= 1.1f);
        }
        if (!this.\u0125) {
            this.\u0125 = System.getProperty("java.vendor").startsWith("Netscape");
            if (this.\u0125) {
                final String property = System.getProperty("java.version");
                if (property.lastIndexOf(".") == property.indexOf(".")) {
                    this.\u0125 = false;
                }
                else {
                    this.\u0125 = (Float.valueOf(System.getProperty("java.version").substring(2)) >= 1.5f);
                }
            }
        }
        final String \u015b = this.\u015a("UseHandCursor", "yes");
        if (\u015b.equalsIgnoreCase("yes")) {
            this.\u0124 = true;
        }
        if (\u015b.equalsIgnoreCase("no")) {
            this.\u0124 = false;
        }
        final String \u015b2 = this.\u015a("ShowLines", "yes");
        if (\u015b2.equalsIgnoreCase("yes")) {
            this.\u0114 = true;
        }
        if (\u015b2.equalsIgnoreCase("no")) {
            this.\u0114 = false;
        }
        final String \u015b3 = this.\u015a("ShowArrows", "yes");
        if (\u015b3.equalsIgnoreCase("yes")) {
            this.\u0113 = true;
        }
        if (\u015b3.equalsIgnoreCase("no")) {
            this.\u0113 = false;
        }
        final String \u015b4 = this.\u015a("ShowSelectors", "yes");
        if (\u015b4.equalsIgnoreCase("yes")) {
            this.\u0112 = true;
        }
        if (\u015b4.equalsIgnoreCase("no")) {
            this.\u0112 = false;
        }
        final String \u015b5 = this.\u015a("3DBorder", "yes");
        if (\u015b5.equalsIgnoreCase("yes")) {
            this.\u0110 = true;
        }
        if (\u015b5.equalsIgnoreCase("no")) {
            this.\u0110 = false;
        }
        this.\u012c = Integer.parseInt(this.\u015a("FadeSpeed", "12"));
        if (this.\u012c <= 0) {
            this.\u012c = 12;
        }
        final String \u015b6 = this.\u015a("Sticky", "yes");
        if (\u015b6.equalsIgnoreCase("yes")) {
            this.\u010b = true;
        }
        if (\u015b6.equalsIgnoreCase("no")) {
            this.\u010b = false;
        }
        int \u010d = -1;
        if (this.\u010b) {
            final String \u015b7 = this.\u015a("HighlightedEntry", "0");
            try {
                \u010d = Integer.parseInt(\u015b7) - 1;
            }
            catch (Exception ex3) {
                \u010d = -1;
            }
        }
        this.\u010c = \u010d;
        this.\u011f = this.\u015a("ContentsText", "Menu Contents");
        this.\u011e = this.\u011f;
        this.\u012b = new Rectangle(this.\u0131 - 18, 5, 13, 12);
        this.\u012a = new Rectangle(this.\u0131 - 30, 5, 12, 12);
        this.\u0148 = new ryStageFader(this.\u0144, this.\u0142, 5, 1);
        this.\u0147 = new ryStageFader(this.\u0143.darker(), this.\u0139, 5, 1);
        final String \u015b8 = this.\u015a("Satellites", "no");
        if (\u015b8.equalsIgnoreCase("yes")) {
            this.\u010d = true;
        }
        if (\u015b8.equalsIgnoreCase("no")) {
            this.\u010d = false;
        }
        this.\u010e = this.getParameter("Name");
        if (this.\u010e == null || this.\u010e.equals("")) {
            this.\u010e = null;
            this.\u010d = false;
        }
        if (this.\u010e != null) {
            if (this.\u0104 != null) {
                this.\u0104.suspend();
                this.\u0104 = null;
            }
            (this.\u0104 = new ryEVTimer(this)).start();
        }
        this.\u0159();
        this.\u0115 = -10;
        this.\u0156();
    }
    
    public void destroy() {
        if (this.\u0104 != null) {
            this.\u0104.suspend();
            this.\u0104 = null;
        }
    }
    
    private void \u0159() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        this.\u0123 = 0;
        for (int n8 = 0; this.getParameter("Item" + (n8 + 1)) != null; ++n8) {
            ++this.\u0123;
        }
        this.\u0146 = new ryEVItem[this.\u0123];
        this.\u011c = new Rectangle[this.\u0123];
        this.\u0111 = new Rectangle[8];
        for (int i = 0; i < 8; ++i) {
            this.\u0111[i] = new Rectangle(5 + i * 12, 5, 10, 10);
        }
        for (int j = 0; j < this.\u0123; ++j) {
            final String parameter = this.getParameter("Item" + (j + 1));
            if (!parameter.startsWith("*")) {
                this.\u0146[j] = new ryEVItem(this, parameter, this.getParameter("URL" + (j + 1)), this.\u015a("Target" + (j + 1), this.\u012e), this.\u015a("Message" + (j + 1), ""), 0, -1);
                n = j;
            }
            else if (parameter.startsWith("*") && !parameter.startsWith("**")) {
                this.\u0146[j] = new ryEVItem(this, parameter.replace('*', ' ').trim(), this.getParameter("URL" + (j + 1)), this.\u015a("Target" + (j + 1), this.\u012e), this.\u015a("Message" + (j + 1), ""), 1, n);
                n2 = j;
            }
            else if (parameter.startsWith("**") && !parameter.startsWith("***")) {
                this.\u0146[j] = new ryEVItem(this, parameter.replace('*', ' ').trim(), this.getParameter("URL" + (j + 1)), this.\u015a("Target" + (j + 1), this.\u012e), this.\u015a("Message" + (j + 1), ""), 2, n2);
                n3 = j;
            }
            else if (parameter.startsWith("***") && !parameter.startsWith("****")) {
                this.\u0146[j] = new ryEVItem(this, parameter.replace('*', ' ').trim(), this.getParameter("URL" + (j + 1)), this.\u015a("Target" + (j + 1), this.\u012e), this.\u015a("Message" + (j + 1), ""), 3, n3);
                n4 = j;
            }
            else if (parameter.startsWith("****") && !parameter.startsWith("*****")) {
                this.\u0146[j] = new ryEVItem(this, parameter.replace('*', ' ').trim(), this.getParameter("URL" + (j + 1)), this.\u015a("Target" + (j + 1), this.\u012e), this.\u015a("Message" + (j + 1), ""), 4, n4);
                n5 = j;
            }
            else if (parameter.startsWith("*****") && !parameter.startsWith("******")) {
                this.\u0146[j] = new ryEVItem(this, parameter.replace('*', ' ').trim(), this.getParameter("URL" + (j + 1)), this.\u015a("Target" + (j + 1), this.\u012e), this.\u015a("Message" + (j + 1), ""), 5, n5);
                n6 = j;
            }
            else if (parameter.startsWith("******") && !parameter.startsWith("*******")) {
                this.\u0146[j] = new ryEVItem(this, parameter.replace('*', ' ').trim(), this.getParameter("URL" + (j + 1)), this.\u015a("Target" + (j + 1), this.\u012e), this.\u015a("Message" + (j + 1), ""), 6, n6);
                n7 = j;
            }
            else if (parameter.startsWith("*******")) {
                this.\u0146[j] = new ryEVItem(this, parameter.replace('*', ' ').trim(), this.getParameter("URL" + (j + 1)), this.\u015a("Target" + (j + 1), this.\u012e), this.\u015a("Message" + (j + 1), ""), 7, n7);
            }
        }
    }
    
    private boolean \u0158(final int n, final int n2, final int n3) {
        return this.\u0146[n].\u0169 == n2 && this.\u0146[n].\u0168 == n3;
    }
    
    private boolean \u0157(final int n, final int n2) {
        boolean b = false;
        for (int i = 0; i < this.\u0146.length; ++i) {
            if (this.\u0146[i].\u0169 == n2 && this.\u0146[i].\u0168 == n) {
                b = true;
                break;
            }
        }
        return b;
    }
    
    private void \u0156() {
        if (this.\u012d != null) {
            this.stop();
        }
        (this.\u012d = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.\u012d != null) {
            this.\u012d = null;
        }
        this.\u0116 = false;
    }
    
    public void run() {
        this.\u0117 = false;
        this.\u0118 = false;
        this.\u0116 = true;
        this.\u014e();
        while (this.\u012d == Thread.currentThread()) {
            final int n = this.\u012c * this.\u0148.fadeDown();
            final boolean fadeCompleted = this.\u0148.getFadeCompleted();
            this.\u0147.fadeDown();
            final boolean fadeCompleted2 = this.\u0147.getFadeCompleted();
            if (!fadeCompleted) {
                this.\u0136 = new Color(this.\u0148.getFadeColor().getRed(), this.\u0148.getFadeColor().getGreen(), this.\u0148.getFadeColor().getBlue());
            }
            if (!fadeCompleted2) {
                this.\u0139 = new Color(this.\u0147.getFadeColor().getRed(), this.\u0147.getFadeColor().getGreen(), this.\u0147.getFadeColor().getBlue());
            }
            this.repaint();
            this.\u010f = true;
            try {
                Thread.sleep(n);
            }
            catch (Exception ex) {}
            if (fadeCompleted && fadeCompleted2) {
                break;
            }
        }
        this.\u014c();
        this.\u0118 = false;
        while (this.\u012d == Thread.currentThread()) {
            final int n2 = this.\u012c * this.\u0148.fadeUp();
            final boolean finished = this.\u0148.getFinished();
            this.\u0147.fadeUp();
            final boolean finished2 = this.\u0147.getFinished();
            if (!finished) {
                this.\u0136 = new Color(this.\u0148.getFadeColor().getRed(), this.\u0148.getFadeColor().getGreen(), this.\u0148.getFadeColor().getBlue());
            }
            if (!finished2) {
                this.\u0139 = new Color(this.\u0147.getFadeColor().getRed(), this.\u0147.getFadeColor().getGreen(), this.\u0147.getFadeColor().getBlue());
            }
            this.repaint();
            try {
                Thread.sleep(n2);
            }
            catch (Exception ex2) {}
            this.\u0117 = true;
            this.\u010f = false;
            if (finished) {
                this.\u0116 = false;
            }
            if (finished && finished2) {
                this.repaint();
                break;
            }
        }
        this.stop();
        this.\u0116 = false;
        this.repaint();
        if (this.\u0115 >= 0) {
            this.\u014b(this.\u0115);
        }
    }
    
    private void \u0155(final Graphics graphics) {
        this.\u0135.setColor(this.\u0145);
        this.\u0135.fillRect(0, 0, this.\u0131, this.\u0130);
        if (this.\u0105 != null) {
            this.\u0150(this.\u0135);
        }
        this.\u0135.setColor(this.\u0144);
        if (this.\u0110) {
            this.\u0135.fill3DRect(0, 0, this.\u0131, this.\u012f, true);
            this.\u0135.fill3DRect(1, 1, this.\u0131 - 2, this.\u012f - 2, true);
            return;
        }
        this.\u0135.fillRect(0, 0, this.\u0131, this.\u012f);
        this.\u0135.fillRect(1, 1, this.\u0131 - 2, this.\u012f - 2);
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u0102) {
            this.\u0135.setColor(Color.black);
            this.\u0135.fillRect(0, 0, this.size().width, this.size().height);
            this.\u0135.setColor(Color.white);
            this.\u0135.setFont(new Font("Dialog", 0, 12));
            final FontMetrics fontMetrics = this.getFontMetrics(new Font("Dialog", 0, 12));
            this.\u0135.drawString("UNREGISTERED", (this.size().width - fontMetrics.stringWidth("UNREGISTERED")) / 2, 20);
            this.\u0135.drawString("Elevator", (this.size().width - fontMetrics.stringWidth("Elevator")) / 2, 35);
            this.\u0135.drawString("by Cool Focus", (this.size().width - fontMetrics.stringWidth("by Cool Focus")) / 2, 50);
            this.\u0135.drawString("www.coolfocus.com", (this.size().width - fontMetrics.stringWidth("www.coolfocus.com")) / 2, 65);
            graphics.drawImage(this.\u0134, 0, 0, this);
            Thread.currentThread();
            try {
                Thread.sleep(4000L);
            }
            catch (InterruptedException ex) {}
            this.\u0102 = false;
        }
        this.\u0135.setFont(this.\u0133);
        this.\u0155(graphics);
        this.\u0135.setColor(this.\u0143);
        int n = this.\u0132.getHeight() + this.\u0132.getDescent();
        if (n % 2 != 0) {
            ++n;
        }
        this.\u0135.fill3DRect(5, 20, this.\u0131 - 10, n, false);
        if (this.\u0118) {
            this.\u0154(graphics);
        }
        if (this.\u0117) {
            this.\u0153(graphics);
        }
        this.\u0152(graphics);
        int n3;
        final int n2 = n3 = 45 + n;
        int \u0121 = 0;
        for (int i = 0; i < this.\u0146.length; ++i) {
            if (this.\u0158(i, this.\u0122, this.\u0121)) {
                int n4 = 0;
                if (i == this.\u011b && this.\u011a) {
                    n4 = 2;
                }
                this.\u0135.setColor(this.\u0142);
                if (i == this.\u011b && i != this.\u010c) {
                    this.\u0135.setColor(this.\u011a ? this.\u0140 : this.\u0141);
                }
                else if (i == this.\u010c) {
                    this.\u0135.setColor(this.\u013f);
                }
                if (this.\u0116) {
                    this.\u0135.setColor(this.\u0136);
                }
                if (!this.\u0146[i].\u016e.equals("-")) {
                    this.\u0135.drawString(this.\u0146[i].\u016e, (this.\u0131 - this.\u0132.stringWidth(this.\u0146[i].\u016e)) / 2, n4 + n3);
                }
                else {
                    this.\u0135.setColor(this.\u0144);
                    final int n5 = n3 - this.\u0132.getMaxAscent() + this.\u0132.getHeight() / 2;
                    if (!this.\u010f) {
                        this.\u0135.fill3DRect(26, n5, this.\u0131 - 53, 2, false);
                    }
                }
                this.\u011c[i] = new Rectangle(18, n3 - this.\u0132.getMaxAscent(), this.\u0131 - 37, this.\u0132.getHeight());
                n3 += this.\u0132.getHeight() + this.\u0132.getDescent();
                ++\u0121;
            }
            else {
                this.\u011c[i] = new Rectangle(-1, -1, 0, 0);
            }
        }
        if (this.\u0120 == -1) {
            this.\u0120 = \u0121;
        }
        final int \u012f = n2 + (this.\u0132.getHeight() + this.\u0132.getDescent()) * this.\u0120 + 2;
        if (\u012f != this.\u012f) {
            this.\u012f = \u012f;
            this.repaint();
        }
        this.\u0135.setColor(this.\u0137);
        this.\u0135.fill3DRect(15, n2 - this.\u0132.getAscent(), 2, (this.\u0132.getHeight() + this.\u0132.getDescent()) * this.\u0120, true);
        this.\u0135.fill3DRect(this.\u0131 - 17, n2 - this.\u0132.getAscent(), 2, (this.\u0132.getHeight() + this.\u0132.getDescent()) * this.\u0120, true);
        this.\u0151(graphics, n);
        graphics.drawImage(this.\u0134, 0, 0, this);
    }
    
    private void \u0154(final Graphics graphics) {
        if (this.\u0113) {
            final int[] array = { this.\u0119, this.\u0119 - 5, this.\u0119 + 5 };
            final int[] array2 = { 13, 8, 8 };
            final int[] array3 = { this.\u0131 - 14, this.\u0131 - 9, this.\u0131 - 9 };
            this.\u0135.setColor(this.\u013b);
            this.\u0135.fillPolygon(array2, array, array2.length);
            this.\u0135.fillPolygon(array3, array, array3.length);
        }
    }
    
    private void \u0153(final Graphics graphics) {
        if (this.\u011b > -1 && this.\u0112) {
            int n = 0;
            int n2 = 0;
            this.\u0135.setColor(this.\u0138);
            if (this.\u011a) {
                n = 2;
                n2 = 1;
            }
            this.\u0135.fillRect(18, this.\u011c[this.\u011b].y + n, this.\u011c[this.\u011b].width + 1, this.\u011c[this.\u011b].height + n2);
        }
        if (this.\u011b > -1 && this.\u0114) {
            int n3 = 0;
            this.\u0135.setColor(this.\u013a);
            if (this.\u011a) {
                this.\u0135.setColor(this.\u013a.darker());
                n3 = 2;
            }
            this.\u0135.drawLine(18, this.\u011c[this.\u011b].y + n3, this.\u011c[this.\u011b].x + this.\u011c[this.\u011b].width, this.\u011c[this.\u011b].y + n3);
            this.\u0135.drawLine(this.\u011c[this.\u011b].x, this.\u011c[this.\u011b].y + this.\u011c[this.\u011b].height + n3, this.\u011c[this.\u011b].x + this.\u011c[this.\u011b].width, this.\u011c[this.\u011b].y + this.\u011c[this.\u011b].height + n3);
        }
    }
    
    private void \u0152(final Graphics graphics) {
        final String[] array = { "1", "2", "3", "4", "5", "6", "7", "8" };
        final Font font = new Font("Helvetica", 1, 10);
        this.getFontMetrics(font);
        this.\u0135.setFont(font);
        for (int i = 0; i < 8; ++i) {
            this.\u0135.setColor(this.\u0143);
            this.\u0135.fill3DRect(this.\u0111[i].x, this.\u0111[i].y, this.\u0111[i].width, this.\u0111[i].height, false);
            this.\u0135.setColor((i <= this.\u0122) ? this.\u013d : this.\u013e);
            this.\u0135.drawString(array[i], 7 + i * 12, 13);
        }
        this.\u0135.setColor(this.\u0143);
        this.\u0135.fill3DRect(this.\u012b.x, this.\u012b.y, this.\u012b.width, this.\u012b.height, !this.\u0126);
        this.\u0135.fill3DRect(this.\u012a.x, this.\u012a.y, this.\u012a.width, this.\u012a.height, !this.\u0127);
        this.\u0135.setColor(this.\u013e);
        if (this.\u0129) {
            this.\u0135.setColor(this.\u0127 ? this.\u013c : this.\u013d);
        }
        this.\u0135.fillRect(this.\u012a.x + 3, this.\u012a.y + 3, this.\u012a.width - 6, this.\u012a.height - 6);
        this.\u0135.setColor(this.\u013e);
        if (this.\u0128) {
            this.\u0135.setColor(this.\u0126 ? this.\u013c : this.\u013d);
        }
        final int[] array2 = { this.\u012b.x + 3, this.\u012b.x + 6, this.\u012b.x + 10 };
        this.\u0135.fillPolygon(array2, new int[] { this.\u012b.y + 4, this.\u012b.y + 8, this.\u012b.y + 4 }, array2.length);
        this.\u0135.setFont(this.\u0133);
    }
    
    private void \u0151(final Graphics graphics, final int n) {
        String s = this.\u011e;
        this.\u0135.setColor(this.\u0139);
        if ((this.\u0129 || this.\u0128) && this.\u0122 > 0) {
            if (this.\u0127 || this.\u0126) {
                this.\u0135.setColor(this.\u013c);
            }
            if (this.\u0129) {
                s = this.\u011f;
            }
            else if (this.\u0128) {
                s = this.\u011d;
            }
            if (this.\u0128) {
                this.\u0135.drawRect(this.\u0111[this.\u0122 - 1].x - 1, this.\u0111[this.\u0122 - 1].y - 1, this.\u0111[this.\u0122 - 1].width, this.\u0111[this.\u0122 - 1].height);
            }
            else if (this.\u0129) {
                this.\u0135.drawRect(this.\u0111[0].x - 1, this.\u0111[0].y - 1, this.\u0111[0].width, this.\u0111[0].height);
            }
            this.\u0135.drawString(s, 8, 20 + this.\u0132.getAscent() + (n - this.\u0132.getHeight()) / 2);
            return;
        }
        if (this.\u0109) {
            if (this.\u0107) {
                this.\u0135.setColor(this.\u013c);
            }
            this.\u0135.drawRect(this.\u0111[this.\u0108].x - 1, this.\u0111[this.\u0108].y - 1, this.\u0111[this.\u0108].width, this.\u0111[this.\u0108].height);
            this.\u0135.drawString(this.\u010a, 8, 20 + this.\u0132.getAscent() + (n - this.\u0132.getHeight()) / 2);
            return;
        }
        this.\u0135.drawString(s, (this.\u0131 - this.\u0132.stringWidth(s)) / 2, 20 + this.\u0132.getAscent() + (n - this.\u0132.getHeight()) / 2);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void \u0150(final Graphics graphics) {
        int i = 0;
        int n = 0;
        while (i < this.\u0131) {
            this.\u0135.drawImage(this.\u0105, i, n, this);
            while (n + this.\u0105.getHeight(this) < this.\u0130) {
                n += this.\u0105.getHeight(this);
                this.\u0135.drawImage(this.\u0105, i, n, this);
            }
            n = 0;
            i += this.\u0105.getWidth(this);
        }
    }
    
    private void \u014f() {
        if (this.\u0125 && this.\u0124) {
            this.setCursor(new Cursor(12));
        }
    }
    
    private void \u014e() {
        if (this.\u0125 && this.\u0124) {
            this.setCursor(new Cursor(0));
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int \u0119) {
        boolean b = false;
        if (this.\u0119 <= this.\u012f && \u0119 > this.\u012f) {
            this.mouseExit(event, n, \u0119);
        }
        this.\u0119 = \u0119;
        final int n2 = 45 + this.\u0132.getHeight() + this.\u0132.getDescent() - this.\u0132.getAscent();
        final boolean \u01192 = this.\u0118;
        if (\u0119 >= n2 && \u0119 <= n2 - this.\u0132.getDescent() + (this.\u0132.getHeight() + this.\u0132.getDescent()) * this.\u0120 && n >= 18 && n <= this.\u0131 - 20) {
            this.\u0118 = true;
        }
        else {
            this.\u0118 = false;
        }
        if ((\u01192 && !this.\u0118) || (!\u01192 && this.\u0118)) {
            b = true;
        }
        if (n > 17 && n < this.\u0131 - 17 && \u0119 >= this.\u011c[0].y && \u0119 < this.\u012f - this.\u0132.getHeight() - this.\u0132.getDescent()) {
            this.repaint(7, 0, 7, this.\u012f);
            this.repaint(this.\u0131 - 14, 0, 7, this.\u012f);
        }
        if (this.\u012a.inside(n, \u0119) && !this.\u0129 && this.\u0122 > 0 && !this.\u0116) {
            this.\u0129 = true;
            b = true;
        }
        if (this.\u0129 && !this.\u012a.inside(n, \u0119)) {
            this.\u0129 = false;
            b = true;
            if (this.\u0101 && this.\u010d) {
                try {
                    ryRadar.transmitName("-^-");
                }
                catch (Exception ex) {}
            }
        }
        if (this.\u012b.inside(n, \u0119) && !this.\u0128 && this.\u0122 > 0 && !this.\u0116) {
            this.\u0128 = true;
            b = true;
        }
        if (this.\u0128 && !this.\u012b.inside(n, \u0119)) {
            this.\u0128 = false;
            b = true;
            if (this.\u0101 && this.\u010d) {
                try {
                    ryRadar.transmitName("-^-");
                }
                catch (Exception ex2) {}
            }
        }
        if (this.\u0120 < 0) {
            this.\u011b = -1;
        }
        else {
            int i = 0;
            while (i < this.\u0146.length) {
                if ((this.\u011b >= 0 && this.\u011b == i && !this.\u011c[i].inside(n, \u0119)) || (this.\u011c[i].inside(n, \u0119) && this.\u0146[i].\u0166)) {
                    this.\u011b = -1;
                    this.\u014e();
                    if (this.\u0103) {
                        this.showStatus("");
                    }
                    b = true;
                    if (this.\u0101 && this.\u010d) {
                        try {
                            ryRadar.transmitName("-^-");
                        }
                        catch (Exception ex3) {}
                        break;
                    }
                    break;
                }
                else if (this.\u011c[i].inside(n, \u0119) && i != this.\u011b) {
                    this.\u011b = i;
                    if (!this.\u0157(this.\u011b, this.\u0122 + 1) && !this.\u014a(this.\u011b)) {
                        this.\u014f();
                    }
                    if (this.\u0103) {
                        this.showStatus(this.\u0146[this.\u011b].\u016c);
                    }
                    b = true;
                    if (this.\u0101 && this.\u010d) {
                        try {
                            ryRadar.transmitName(String.valueOf(this.\u010e) + (this.\u011b + 1));
                        }
                        catch (Exception ex4) {}
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        for (int j = 0; j < 8; ++j) {
            if (this.\u0111[j].inside(n, \u0119) && j < this.\u0122) {
                this.\u0109 = true;
                this.\u014d(this.\u0108 = j);
                b = true;
                break;
            }
            if (this.\u0109 && this.\u0108 == j && !this.\u0111[j].inside(n, \u0119) && (n < this.\u0111[0].x || n > this.\u0111[this.\u0122 - 1].x + this.\u0111[0].width || \u0119 < this.\u0111[0].y || \u0119 > this.\u0111[0].y + this.\u0111[0].height)) {
                this.\u0109 = false;
                b = true;
                this.showStatus("");
                break;
            }
        }
        if (b) {
            this.repaint();
        }
        if (this.\u0101 && this.\u010d && this.\u0128) {
            try {
                ryRadar.transmitName(String.valueOf(this.\u010e) + "_down");
            }
            catch (Exception ex5) {}
        }
        else if (this.\u0101 && this.\u010d && this.\u0129) {
            try {
                ryRadar.transmitName(String.valueOf(this.\u010e) + "_home");
            }
            catch (Exception ex6) {}
        }
        return true;
    }
    
    private void \u014d(final int n) {
        final int n2 = this.\u0122 - n;
        int n3 = this.\u0121;
        ryEVItem ryEVItem = null;
        try {
            for (int i = 0; i < n2; ++i) {
                ryEVItem = this.\u0146[n3];
                n3 = this.\u0146[n3].\u0168;
            }
            this.\u010a = this.\u0146[ryEVItem.\u0168].\u016e;
            this.\u0106 = ryEVItem.\u0168;
        }
        catch (Exception ex) {
            this.\u010a = this.\u011f;
            this.\u0106 = -1;
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.\u012a.inside(n, n2) && this.\u0122 > 0) {
            this.\u0127 = true;
            this.repaint();
        }
        else if (this.\u012b.inside(n, n2) && this.\u0122 > 0) {
            this.\u0126 = true;
            this.repaint();
        }
        if (this.\u0111[this.\u0108].inside(n, n2)) {
            this.\u0107 = true;
            this.repaint();
        }
        if (this.\u0120 < 0 || this.\u011b == -1) {
            this.\u011b = -1;
            return true;
        }
        for (int i = 0; i < this.\u0146.length; ++i) {
            if (this.\u011c[i].inside(n, n2)) {
                this.\u011b = i;
                this.\u011a = true;
                this.repaint();
                break;
            }
        }
        return true;
    }
    
    private void \u014c() {
        if (this.\u0115 == -10) {
            this.\u0122 = 0;
            this.\u0121 = -1;
            this.\u0120 = -1;
            this.\u011e = this.\u011f;
            this.\u011d = "";
            this.\u014e();
            this.repaint();
            if (this.\u0103) {
                this.showStatus("");
            }
            if (this.\u0101 && this.\u010d) {
                try {
                    ryRadar.transmitName("-^-");
                }
                catch (Exception ex) {}
            }
            return;
        }
        if (this.\u0115 == -5) {
            --this.\u0122;
            this.\u0120 = -1;
            this.\u011e = this.\u011d;
            if (this.\u0122 > 1) {
                this.\u011d = this.\u0146[this.\u0146[this.\u0146[this.\u0121].\u0168].\u0168].\u016e;
            }
            else {
                this.\u011d = this.\u011f;
            }
            if (this.\u0122 > 0) {
                this.\u0121 = this.\u0146[this.\u0121].\u0168;
            }
            else {
                this.\u0121 = -1;
            }
            this.repaint();
            if (this.\u0103) {
                this.showStatus("");
            }
            if (this.\u0101 && this.\u010d) {
                try {
                    ryRadar.transmitName("-^-");
                }
                catch (Exception ex2) {}
            }
            return;
        }
        if (this.\u0115 == -2) {
            this.\u0122 = this.\u0108;
            this.\u0120 = -1;
            this.\u011e = this.\u010a;
            if (this.\u0122 > 1) {
                this.\u011d = this.\u0146[this.\u0106].\u016e;
            }
            else {
                this.\u011d = this.\u011f;
            }
            this.\u0121 = this.\u0106;
            this.\u0109 = false;
            this.repaint();
            if (this.\u0103) {
                this.showStatus("");
            }
        }
        if (this.\u0115 >= 0) {
            if (this.\u0122 == 0) {
                this.\u011d = this.\u011f;
            }
            else {
                this.\u011d = this.\u0146[this.\u0146[this.\u0115].\u0168].\u016e;
            }
            ++this.\u0122;
            this.\u0121 = this.\u0115;
            this.\u0120 = -1;
            this.\u011e = this.\u0146[this.\u0115].\u016e;
            this.\u014e();
            if (this.\u0103) {
                this.showStatus("");
            }
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.\u012a.inside(n, n2) && this.\u0127) {
            this.\u0127 = false;
            this.\u0115 = -10;
            this.\u0117 = false;
            this.\u0118 = false;
            this.received = "";
            this.\u0156();
            this.\u010c = -1;
        }
        else if (this.\u012b.inside(n, n2) && this.\u0126) {
            this.\u0126 = false;
            this.\u0115 = -5;
            this.\u0117 = false;
            this.\u0118 = false;
            this.received = "";
            this.\u0156();
            this.\u010c = -1;
        }
        if (this.\u0107) {
            this.\u0107 = false;
            this.repaint();
            if (this.\u0111[this.\u0108].inside(n, n2) && this.\u0108 < this.\u0122) {
                this.\u0115 = -2;
                this.\u0117 = false;
                this.\u0118 = false;
                this.received = "";
                this.\u0156();
                this.\u010c = -1;
            }
        }
        if (this.\u0120 < 0 || this.\u011b == -1) {
            this.\u011b = -1;
            return true;
        }
        if (this.\u011c[this.\u011b].inside(n, n2)) {
            if (this.\u0157(this.\u011b, this.\u0122 + 1)) {
                this.\u011a = false;
                this.\u0115 = this.\u011b;
                this.\u0117 = false;
                this.\u0118 = false;
                this.received = "";
                this.\u0156();
                this.\u014b(this.\u011b);
            }
            else {
                this.\u011a = false;
                if (this.\u010b) {
                    this.\u010c = this.\u011b;
                }
                this.repaint();
                this.\u014b(this.\u011b);
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (n < 0 || n > this.\u0131 || n2 < 0 || n2 > this.\u012f) {
            this.mouseExit(event, n, n2);
        }
        else if (this.\u0127 && !this.\u012a.inside(n, n2)) {
            this.\u0127 = false;
            this.\u0129 = false;
            if (this.\u0103) {
                this.showStatus("");
            }
            this.repaint();
        }
        else if (this.\u0126 && !this.\u012b.inside(n, n2)) {
            this.\u0126 = false;
            this.\u0128 = false;
            if (this.\u0103) {
                this.showStatus("");
            }
            this.repaint();
        }
        else if (this.\u011b > -1 && !this.\u011c[this.\u011b].inside(n, n2)) {
            this.mouseExit(event, n, n2);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.\u0129 = false;
        this.\u0127 = false;
        this.\u0128 = false;
        this.\u0126 = false;
        this.\u011a = false;
        this.\u0107 = false;
        this.\u0109 = false;
        this.\u011b = -1;
        this.\u014e();
        if (this.\u0103) {
            this.showStatus("");
        }
        this.\u0118 = false;
        this.repaint();
        if (this.\u0101 && this.\u010d) {
            try {
                ryRadar.transmitName("-^-");
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.\u0103) {
            this.showStatus("UNREGISTERED Elevator by Cool Focus [www.coolfocus.com]");
        }
        return true;
    }
    
    private void \u014b(final int n) {
        final String \u016b = this.\u0146[n].\u016b;
        if (\u016b == null || \u016b.equals("")) {
            return;
        }
        if (\u016b.equalsIgnoreCase("_down")) {
            this.\u0115 = -5;
            this.\u0117 = false;
            this.\u0118 = false;
            this.\u0156();
            this.\u010c = -1;
            return;
        }
        if (\u016b.equalsIgnoreCase("_home")) {
            this.\u0115 = -10;
            this.\u0117 = false;
            this.\u0118 = false;
            this.\u0156();
            this.\u010c = -1;
            return;
        }
        if (\u016b.startsWith("_") && \u016b.length() == 2) {
            final String substring = \u016b.substring(\u016b.indexOf("_") + 1);
            int \u0109;
            try {
                \u0109 = Integer.parseInt(substring) - 1;
            }
            catch (Exception ex) {
                return;
            }
            if (\u0109 < this.\u0122) {
                this.\u014d(this.\u0108 = \u0109);
                this.\u0115 = -2;
                this.\u0117 = false;
                this.\u0118 = false;
                this.\u0156();
                this.\u010c = -1;
                return;
            }
        }
        if (\u016b.length() > 10 && \u016b.substring(0, 10).equalsIgnoreCase("javascript")) {
            this.\u0149(\u016b);
            return;
        }
        if (this.\u0146[n].\u016a != null) {
            this.getAppletContext().showDocument(this.\u0146[n].\u016a, this.\u0146[n].\u016d);
        }
    }
    
    private boolean \u014a(final int n) {
        String lowerCase;
        try {
            lowerCase = this.\u0146[n].\u016b.toLowerCase();
        }
        catch (Exception ex) {
            return false;
        }
        return lowerCase != null && lowerCase != "" && lowerCase.startsWith("_");
    }
    
    private void \u0149(String substring) {
        if (!this.\u0101) {
            return;
        }
        if (substring == null || substring == "$" || substring.startsWith("$")) {
            return;
        }
        final JSObject window = JSObject.getWindow((Applet)this);
        substring = substring.substring(substring.indexOf(":") + 1);
        window.eval(substring);
    }
    
    public void setItem(final String s, final String s2, final String s3) {
        if (!this.\u0101) {
            return;
        }
        this.received = String.valueOf(s) + s2 + s3;
        final int \u0121 = Integer.parseInt(s) - 1;
        final int \u0123 = Integer.parseInt(s2) - 1;
        int \u0115 = \u0121;
        if (\u0121 == -1 && \u0123 == -1) {
            \u0115 = -10;
        }
        if (\u0115 >= 0 && this.\u0146[\u0115].\u016e.equals(this.\u011e)) {
            return;
        }
        if (\u0115 == -10 && this.\u011e == this.\u011f) {
            return;
        }
        this.\u0121 = \u0121;
        this.\u0122 = \u0123;
        this.\u0115 = \u0115;
        this.\u0156();
    }
    
    public ryElevator() {
        this.\u0121 = -1;
        this.\u0120 = -1;
        this.\u011d = "";
        this.\u011b = -1;
        this.\u011a = false;
        this.\u0118 = false;
        this.\u0117 = false;
        this.\u0116 = false;
        this.\u010f = false;
        this.\u010e = "";
        this.received = "";
        this.\u0103 = false;
        this.\u0102 = false;
    }
}
