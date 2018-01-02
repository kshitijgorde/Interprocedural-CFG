import netscape.javascript.JSObject;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
import java.net.MalformedURLException;
import java.awt.Component;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.awt.MediaTracker;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryCoolStrip extends Applet
{
    private Color \u0154;
    private Color \u0153;
    private Color \u0152;
    private Color \u0151;
    private Color \u0150;
    private Color \u014f;
    private Color \u014e;
    private Color \u014d;
    private Color \u014c;
    private Color \u014b;
    private Color \u014a;
    private Color \u0149;
    private Color \u0148;
    private Color \u0147;
    private Color \u0146;
    private Color \u0145;
    private Color \u0144;
    private Color \u0143;
    private Color \u0142;
    private Graphics \u0141;
    private Image \u0140;
    private int \u013f;
    private int \u013e;
    private Font \u013d;
    private FontMetrics \u013c;
    private int \u013b;
    private String[] \u013a;
    private URL[] \u0139;
    private URL[] \u0138;
    private URL[] \u0137;
    private URL[] \u0136;
    private String[] \u0135;
    private String[] \u0134;
    private String[] \u0133;
    private String[] \u0132;
    private String[] \u0131;
    private String[] \u0130;
    private String[] \u012f;
    private String[] \u012e;
    private String[] \u012d;
    private int[] \u012c;
    private boolean[] \u012b;
    private Image[] \u012a;
    private String \u0129;
    private boolean \u0128;
    private boolean \u0127;
    private boolean \u0126;
    private boolean \u0125;
    private int \u0124;
    private int \u0123;
    private int \u0122;
    private int \u0121;
    private int \u0120;
    private boolean \u011f;
    private boolean \u011e;
    private boolean \u011d;
    private boolean \u011c;
    private AudioClip \u011b;
    private AudioClip \u011a;
    private boolean \u0119;
    private MediaTracker \u0118;
    private int \u0117;
    private int \u0116;
    private boolean \u0115;
    private String \u0114;
    private String \u0113;
    private Image \u0112;
    private boolean \u0111;
    private int \u0110;
    private boolean \u010f;
    String \u010e;
    private boolean \u010d;
    private boolean \u010c;
    private boolean \u010b;
    private boolean \u010a;
    private boolean \u0109;
    private boolean \u0108;
    private boolean \u0107;
    private boolean \u0106;
    private boolean \u0105;
    private Color \u0104;
    private Color \u0103;
    private String \u0102;
    private String \u0101;
    public boolean ready;
    
    private void \u0160() {
        final String s = "CoolStrip © 1998 Cool Focus [www.coolfocus.com]";
        final String s2 = "CoolStrip (c) 1998 Cool Focus [www.coolfocus.com]";
        if (this.getParameter("Copyright") == null || (!this.getParameter("Copyright").equals(s) && !this.getParameter("Copyright").equals(s2))) {
            throw new SecurityException(" Copyright parameter missing or incorrect ");
        }
    }
    
    private void \u015f() {
        final String lowerCase = this.getDocumentBase().toString().toLowerCase();
        boolean b = false;
        if (lowerCase != null && lowerCase.startsWith("file:") && lowerCase.indexOf("coolstrip/docs/") >= 0) {
            this.\u0108 = true;
            return;
        }
        final String parameter = this.getParameter("Base");
        if (parameter == null) {
            this.\u0108 = false;
            return;
        }
        if (parameter.indexOf("|") >= 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, "|");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.length() >= 3 && (lowerCase.indexOf(nextToken) >= 0 || lowerCase.startsWith("file:"))) {
                    b = true;
                    break;
                }
            }
        }
        else if (lowerCase.indexOf(parameter) >= 0 || lowerCase.startsWith("file:")) {
            b = true;
        }
        if (!b) {
            this.\u0108 = false;
            return;
        }
        this.\u0108 = this.\u015e(parameter);
    }
    
    private boolean \u015e(final String s) {
        final String parameter = this.getParameter("Key");
        if (parameter == null) {
            return false;
        }
        if (!this.\u010d) {
            return true;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < parameter.length(); ++i) {
            if (parameter.charAt(i) != '\r' && parameter.charAt(i) != '\n' && parameter.charAt(i) != '-') {
                sb.append(parameter.charAt(i));
            }
            if (parameter.charAt(i) == '-') {
                sb.append(" ");
            }
        }
        String s2 = sb.toString();
        if (s2.charAt(s2.length() - 1) != ' ') {
            s2 = String.valueOf(s2) + " ";
        }
        return this.\u015d(s2, "27144343", "21078301").equals(s);
    }
    
    private String \u015d(final String s, final String s2, final String s3) {
        final BigInteger bigInteger = new BigInteger(s3);
        final BigInteger bigInteger2 = new BigInteger(s2);
        String string = "";
        int index;
        for (int i = 0; i < s.length(); i = index + 1) {
            final int n = i;
            index = s.indexOf(32, i);
            String s4 = Integer.toBinaryString(new BigInteger(s.substring(n, index), 10).modPow(bigInteger, bigInteger2).intValue());
            for (int j = s4.length(); j < 16; ++j) {
                s4 = "0" + s4;
            }
            for (int k = 1; k <= 2; ++k) {
                final String substring = s4.substring((k - 1) * 8, k * 8);
                if (!substring.equals("00000000")) {
                    string = String.valueOf(string) + (char)new BigInteger(substring, 2).intValue();
                }
            }
        }
        return string;
    }
    
    private int \u015c(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return Integer.valueOf(parameter, 16);
        }
        return color.getRGB();
    }
    
    private String \u015b(final String s, final String s2) {
        String parameter = this.getParameter(s);
        if (parameter == null) {
            parameter = s2;
        }
        return parameter;
    }
    
    public void init() {
        this.\u0155();
        this.\u015f();
        this.\u0160();
        System.out.println(this.getAppletInfo());
        if (!this.\u0108) {
            this.\u0107 = true;
        }
        this.\u0106 = (this.\u0108 || this.getDocumentBase().toString().toLowerCase().startsWith("file:"));
        this.\u013f = this.size().width;
        this.\u013e = this.size().height;
        this.\u0140 = this.createImage(this.\u013f, this.\u013e);
        this.\u0141 = this.\u0140.getGraphics();
        this.\u0154 = new Color(this.\u015c("BgColor", this.\u0153));
        this.\u0152 = new Color(this.\u015c("TextColor", this.\u0151));
        this.\u0150 = new Color(this.\u015c("TextFocusColor", this.\u014f));
        this.\u014e = new Color(this.\u015c("TextPressedColor", this.\u014d));
        this.\u0143 = new Color(this.\u015c("TextSelectColor", this.\u0142));
        this.\u014c = new Color(this.\u015c("StripColor", this.\u014b));
        this.\u0148 = new Color(this.\u015c("ArrowColor", this.\u0147));
        this.\u0146 = new Color(this.\u015c("ArrowPressedColor", this.\u014e));
        this.\u0145 = new Color(this.\u015c("SelectorColor", this.\u0144));
        this.\u014a = new Color(this.\u015c("LineColor", this.\u0149));
        this.setBackground(this.\u0154);
        int n = 0;
        String parameter = this.getParameter("Font");
        if (parameter == null) {
            parameter = "Helvetica,plain,12";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
        final String nextToken = stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
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
        this.\u013d = new Font(nextToken, n, Integer.parseInt(stringTokenizer.nextToken()));
        this.\u013c = this.getFontMetrics(this.\u013d);
    }
    
    public void start() {
        this.\u0105 = (this.getParameter("ShowLoad") == null);
        this.\u0104 = new Color(this.\u015c("LoadBgColor", Color.black));
        this.\u0103 = new Color(this.\u015c("LoadTextColor", Color.green));
        this.\u0102 = this.\u015b("LoadText1", "Loading CoolStrip...");
        this.\u0101 = this.\u015b("LoadText2", "");
        if (this.\u0105) {
            this.repaint();
            return;
        }
        this.\u015a();
    }
    
    private void \u015a() {
        this.\u0129 = this.\u015b("DefaultTarget", "_self");
        int n = 0;
        this.\u013b = 0;
        this.\u0122 = 0;
        while (true) {
            final String parameter = this.getParameter("Entry" + (n + 1));
            if (parameter == null) {
                break;
            }
            final int stringWidth = this.\u013c.stringWidth(parameter);
            if (stringWidth > this.\u0122) {
                this.\u0122 = stringWidth;
            }
            ++this.\u013b;
            ++n;
        }
        this.\u013a = new String[this.\u013b];
        this.\u0139 = new URL[this.\u013b];
        this.\u0138 = new URL[this.\u013b];
        this.\u0137 = new URL[this.\u013b];
        this.\u0136 = new URL[this.\u013b];
        this.\u0135 = new String[this.\u013b];
        this.\u0134 = new String[this.\u013b];
        this.\u0133 = new String[this.\u013b];
        this.\u0132 = new String[this.\u013b];
        this.\u0131 = new String[this.\u013b];
        this.\u0130 = new String[this.\u013b];
        this.\u012f = new String[this.\u013b];
        this.\u012e = new String[this.\u013b];
        this.\u012d = new String[this.\u013b];
        this.\u012c = new int[this.\u013b];
        this.\u012b = new boolean[this.\u013b];
        this.\u012a = new Image[this.\u013b];
        this.\u0118 = new MediaTracker(this);
        for (int i = 0; i < this.\u013b; ++i) {
            final int n2 = i + 1;
            this.\u013a[i] = this.getParameter("Entry" + n2);
            this.\u0131[i] = this.\u015b("Target" + n2 + "a", this.\u0129);
            this.\u0130[i] = this.\u015b("Target" + n2 + "b", this.\u0129);
            this.\u012f[i] = this.\u015b("Target" + n2 + "c", this.\u0129);
            this.\u012e[i] = this.\u015b("Target" + n2 + "d", this.\u0129);
            this.\u012d[i] = this.\u015b("Message" + n2, "");
            if (!this.\u0108) {
                this.\u012d[i] = "UNREGISTERED CoolStrip © Cool Focus [www.coolfocus.com]";
            }
            final String parameter2 = this.getParameter("URL" + n2 + "a");
            this.\u0135[i] = parameter2;
            if (parameter2 != null && !parameter2.equals("") && !parameter2.startsWith("$") && !parameter2.equals("$")) {
                try {
                    this.\u0139[i] = new URL(this.getDocumentBase(), parameter2);
                }
                catch (MalformedURLException ex) {}
            }
            final String parameter3 = this.getParameter("URL" + n2 + "b");
            if ((this.\u0134[i] = parameter3) != null && !parameter3.equals("") && !parameter3.startsWith("$") && !parameter3.equals("$")) {
                try {
                    this.\u0138[i] = new URL(this.getDocumentBase(), parameter3);
                }
                catch (MalformedURLException ex2) {}
            }
            final String parameter4 = this.getParameter("URL" + n2 + "c");
            if ((this.\u0133[i] = parameter4) != null && !parameter4.equals("") && !parameter4.startsWith("$") && !parameter4.equals("$")) {
                try {
                    this.\u0137[i] = new URL(this.getDocumentBase(), parameter4);
                }
                catch (MalformedURLException ex3) {}
            }
            final String parameter5 = this.getParameter("URL" + n2 + "d");
            if ((this.\u0132[i] = parameter5) != null && !parameter5.equals("") && !parameter5.startsWith("$") && !parameter5.equals("$")) {
                try {
                    this.\u0136[i] = new URL(this.getDocumentBase(), parameter5);
                }
                catch (MalformedURLException ex4) {}
            }
            if (this.getParameter("Image" + n2) != null) {
                this.\u012b[i] = true;
                try {
                    this.\u012a[i] = this.getImage(new URL(this.getDocumentBase(), this.getParameter("Image" + n2)));
                }
                catch (Exception ex5) {
                    this.\u012a[i] = null;
                    this.\u012b[i] = false;
                }
                if (this.\u012a[i] != null) {
                    this.\u0118.addImage(this.\u012a[i], i);
                }
                try {
                    if (!this.\u0118.waitForID(i, 10000L)) {
                        this.\u012a[i] = null;
                        this.\u012b[i] = false;
                    }
                }
                catch (Exception ex6) {
                    this.\u012a[i] = null;
                    this.\u012b[i] = false;
                }
            }
            else {
                this.\u012b[i] = false;
            }
        }
        if (this.\u015b("Satellites", "no").equalsIgnoreCase("yes")) {
            this.\u010b = true;
        }
        else {
            this.\u010b = false;
        }
        if (this.\u015b("UseArrows", "yes").equalsIgnoreCase("no")) {
            this.\u0128 = false;
        }
        else {
            this.\u0128 = true;
        }
        if (this.\u015b("SmallArrows", "no").equalsIgnoreCase("yes")) {
            this.\u0109 = true;
        }
        else {
            this.\u0109 = false;
        }
        if (this.\u015b("UseSelectors", "yes").equalsIgnoreCase("no")) {
            this.\u0127 = false;
        }
        else {
            this.\u0127 = true;
        }
        if (this.\u015b("UnlockOnExit", "no").equalsIgnoreCase("yes")) {
            this.\u0111 = false;
        }
        else {
            this.\u0111 = true;
        }
        if (this.\u0111) {
            int int1 = Integer.parseInt(this.\u015b("HighlightedEntry", "0"));
            if (int1 < 0) {
                int1 = 0;
            }
            if (int1 == 0) {
                this.\u0121 = 500;
                this.\u0110 = 500;
            }
            else {
                this.\u0121 = int1 - 1;
                this.\u0110 = int1 - 1;
            }
        }
        this.\u0124 = Integer.parseInt(this.\u015b("EntrySpacing", "10"));
        final String \u015b = this.\u015b("Align", "right");
        if (\u015b.equalsIgnoreCase("left")) {
            this.\u0123 = 0;
        }
        if (\u015b.equalsIgnoreCase("center")) {
            this.\u0123 = 1;
        }
        if (\u015b.equalsIgnoreCase("right")) {
            this.\u0123 = 2;
        }
        final String \u015b2 = this.\u015b("Location", "left");
        if (\u015b2.equalsIgnoreCase("left")) {
            this.\u011d = true;
        }
        if (\u015b2.equalsIgnoreCase("right")) {
            this.\u011d = false;
        }
        final String \u015b3 = this.\u015b("Testmode", "no");
        if (\u015b3.equalsIgnoreCase("yes")) {
            this.\u0115 = true;
        }
        if (\u015b3.equalsIgnoreCase("no")) {
            this.\u0115 = false;
        }
        final String \u015b4 = this.\u015b("Sound", "yes");
        if (\u015b4.equalsIgnoreCase("yes")) {
            this.\u0119 = true;
        }
        if (\u015b4.equalsIgnoreCase("no")) {
            this.\u0119 = false;
        }
        this.\u0117 = Integer.parseInt(this.\u015b("ImageHeight", "0"));
        if (this.\u0119) {
            try {
                this.\u011b = this.getAudioClip(new URL(this.getDocumentBase(), this.getParameter("DownSound")));
            }
            catch (Exception ex7) {}
            try {
                this.\u011a = this.getAudioClip(new URL(this.getDocumentBase(), this.getParameter("UpSound")));
            }
            catch (Exception ex8) {}
        }
        this.\u0122 += 12;
        this.\u0113 = this.getParameter("BgImage");
        if (this.\u0113 != null) {
            try {
                this.\u0112 = this.getImage(new URL(this.getDocumentBase(), this.\u0113));
            }
            catch (Exception ex9) {
                System.out.println("Error loading background image");
            }
            try {
                this.\u0118.addImage(this.\u0112, 50);
            }
            catch (Exception ex10) {}
            final String parameter6 = this.getParameter("TileBgImage");
            if (parameter6 != null && parameter6.equalsIgnoreCase("yes")) {
                this.\u010a = true;
            }
            else {
                this.\u010a = false;
            }
            try {
                if (!this.\u0118.waitForID(50, 10000L)) {
                    this.\u0113 = null;
                    this.\u010a = false;
                }
            }
            catch (Exception ex11) {
                this.\u0113 = null;
                this.\u010a = false;
            }
        }
        this.\u0159();
        if (this.\u011d) {
            this.\u0116 = (this.\u013f - this.\u0122 - 1) / 2;
        }
        else {
            this.\u0116 = (this.\u013f - this.\u0122 + 1) / 2;
        }
        if (this.\u0115) {
            this.\u0157();
        }
        if (this.getParameter("Name") != null) {
            this.\u010e = this.getParameter("Name");
        }
        else {
            this.\u010e = null;
        }
        this.\u0105 = false;
        this.repaint();
        this.ready = true;
    }
    
    private void \u0159() {
        final int n = this.\u013c.getHeight() + this.\u013c.getLeading();
        for (int i = 0; i < this.\u013b; ++i) {
            this.\u012c[i] = (n + this.\u0124) * (i + 1);
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int \u0121) {
        if (this.\u0105 || this.\u0107) {
            return true;
        }
        this.\u011f = (n >= this.\u0116 + 2 && n <= this.\u0116 + this.\u0122 - 4);
        if (this.\u0128) {
            this.\u0120 = \u0121;
            if (this.\u011f && \u0121 > this.\u0117 && \u0121 < this.\u0117 + this.\u012c[this.\u013b - 1] + this.\u013c.getHeight() / 2 + this.\u0124) {
                this.\u011e = true;
                this.\u011c = false;
            }
            else {
                this.\u011e = false;
            }
        }
        if (!this.\u011f || \u0121 < this.\u0117 + this.\u012c[0] || \u0121 > this.\u0117 + this.\u012c[this.\u013b - 1]) {
            this.\u0126 = false;
            if (this.\u010d && this.\u010c) {
                this.setCursor(new Cursor(0));
            }
            if (this.\u0106 && this.\u010b) {
                try {
                    ryRadar.transmitName("-^-");
                }
                catch (Exception ex) {}
            }
            if (!this.\u0115 && this.\u0108) {
                this.showStatus("");
            }
            if (this.\u0121 != 500) {
                this.\u0121 = 500;
                this.repaint();
            }
        }
        for (int i = 0; i < this.\u013b; ++i) {
            if (this.\u011f && \u0121 <= this.\u0117 + this.\u012c[i] + this.\u013c.getDescent() && \u0121 >= this.\u0117 + this.\u012c[i] - this.\u013c.getHeight()) {
                this.\u0126 = true;
                if (this.\u0121 != i) {
                    this.\u0121 = i;
                    if (this.\u010d && this.\u010c) {
                        this.setCursor(new Cursor(12));
                    }
                    this.repaint();
                    if (this.\u0106 && this.\u010b) {
                        try {
                            ryRadar.transmitName(String.valueOf(this.\u010e) + Integer.toString(i + 1));
                        }
                        catch (Exception ex2) {}
                    }
                    if (this.\u0106) {
                        this.showStatus(this.\u012d[i]);
                    }
                    else {
                        this.showStatus("UNREGISTERED CoolStrip by Cool Focus [www.coolfocus.com]");
                    }
                }
            }
            else if (this.\u011f && this.\u0128 && this.\u011d && !this.\u011c) {
                this.repaint(this.\u0116 + this.\u0122 + 3, this.\u0117, 10, this.\u0117 + this.\u012c[this.\u013b - 1] + this.\u013c.getHeight() / 2 + this.\u0124);
            }
            else if (this.\u011f && this.\u0128 && !this.\u011d && !this.\u011c) {
                this.repaint(this.\u0116 - 13, this.\u0117, 10, this.\u0117 + this.\u012c[this.\u013b - 1] + this.\u013c.getHeight() / 2 + this.\u0124);
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.\u0126 = false;
        this.\u0125 = false;
        this.\u011e = false;
        this.repaint();
        if (this.\u0106 && this.\u010b) {
            try {
                ryRadar.transmitName("-^-");
            }
            catch (Exception ex) {}
        }
        this.\u0121 = 500;
        if (this.\u010d && this.\u010c) {
            this.setCursor(new Cursor(0));
        }
        if (!this.\u0115 || !this.\u0108) {
            this.showStatus("");
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.\u0126) {
            this.\u0125 = true;
            this.repaint();
            if (this.\u0119 && this.\u011b != null) {
                this.\u011b.play();
            }
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.\u0126 && this.\u0125) {
            this.\u0125 = false;
            if (this.\u0111) {
                this.\u0110 = this.\u0121;
            }
            this.\u010f = true;
            this.repaint();
            if (this.\u0119 && this.\u011a != null) {
                this.\u011a.play();
            }
            if (this.\u0135[this.\u0121] != null && this.\u0135[this.\u0121].length() > 10 && this.\u0135[this.\u0121].substring(0, 10).equalsIgnoreCase("javascript")) {
                this.\u0156(this.\u0135[this.\u0121]);
            }
            else if (this.\u0139[this.\u0121] != null) {
                this.getAppletContext().showDocument(this.\u0139[this.\u0121], this.\u0131[this.\u0121]);
            }
            if (this.\u0134[this.\u0121] != null && this.\u0134[this.\u0121].length() > 10 && this.\u0134[this.\u0121].substring(0, 10).equalsIgnoreCase("javascript")) {
                this.\u0156(this.\u0134[this.\u0121]);
            }
            else if (this.\u0138[this.\u0121] != null) {
                this.getAppletContext().showDocument(this.\u0138[this.\u0121], this.\u0130[this.\u0121]);
            }
            if (this.\u0133[this.\u0121] != null && this.\u0133[this.\u0121].length() > 10 && this.\u0133[this.\u0121].substring(0, 10).equalsIgnoreCase("javascript")) {
                this.\u0156(this.\u0133[this.\u0121]);
            }
            else if (this.\u0137[this.\u0121] != null) {
                this.getAppletContext().showDocument(this.\u0137[this.\u0121], this.\u012f[this.\u0121]);
            }
            if (this.\u0132[this.\u0121] != null && this.\u0132[this.\u0121].length() > 10 && this.\u0132[this.\u0121].substring(0, 10).equalsIgnoreCase("javascript")) {
                this.\u0156(this.\u0132[this.\u0121]);
            }
            else if (this.\u0136[this.\u0121] != null) {
                this.getAppletContext().showDocument(this.\u0136[this.\u0121], this.\u012e[this.\u0121]);
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.\u011f = (n >= this.\u0116 + 2 && n <= this.\u0116 + this.\u0122 - 4);
        if (this.\u0126 && ((n2 > this.\u0117 + this.\u012c[this.\u0121] + this.\u013c.getDescent() && n2 < this.\u0117 + this.\u012c[this.\u0121] + this.\u0124 + this.\u013c.getLeading()) || n2 < this.\u0117 + this.\u012c[0] - this.\u013c.getHeight() || !this.\u011f)) {
            this.\u0126 = false;
            this.\u0125 = false;
            this.repaint();
            this.\u0121 = 500;
            if (this.\u010d && this.\u010c) {
                this.setCursor(new Cursor(0));
            }
            if (!this.\u0115) {
                this.showStatus("");
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (this.\u0107) {
            this.\u0141.setColor(Color.black);
            this.\u0141.fillRect(0, 0, this.size().width, this.size().height);
            this.\u0141.setColor(Color.green);
            this.\u0141.setFont(new Font("Dialog", 0, 12));
            final FontMetrics fontMetrics = this.getFontMetrics(new Font("Dialog", 0, 12));
            this.\u0141.drawString("UNREGISTERED", (this.size().width - fontMetrics.stringWidth("UNREGISTERED")) / 2, 20);
            this.\u0141.drawString("CoolStrip", (this.size().width - fontMetrics.stringWidth("CoolStrip")) / 2, 35);
            this.\u0141.drawString("by Cool Focus", (this.size().width - fontMetrics.stringWidth("by Cool Focus")) / 2, 50);
            this.\u0141.drawString("www.coolfocus.com", (this.size().width - fontMetrics.stringWidth("www.coolfocus.com")) / 2, 65);
            graphics.drawImage(this.\u0140, 0, 0, this);
            Thread.currentThread();
            try {
                Thread.sleep(4000L);
            }
            catch (InterruptedException ex) {}
            this.\u0107 = false;
        }
        if (this.\u0105) {
            this.\u0141.setColor(this.\u0104);
            this.\u0141.fillRect(0, 0, this.\u013f, this.\u013e);
            this.\u0141.setColor(this.\u0103);
            this.\u0141.setFont(new Font("Dialog", 0, 12));
            this.\u0141.drawString(this.\u0102, 10, 20);
            this.\u0141.drawString(this.\u0101, 10, 35);
            graphics.drawImage(this.\u0140, 0, 0, this);
            this.\u015a();
            return;
        }
        this.\u0141.setColor(this.\u0154);
        this.\u0141.fillRect(0, 0, this.\u013f, this.\u013e);
        if (this.\u0113 != null) {
            if (this.\u010a) {
                this.\u0158(this.\u0141);
            }
            else {
                this.\u0141.drawImage(this.\u0112, 0, 0, this.\u013f, this.\u013e, this);
            }
        }
        this.\u0141.setFont(this.\u013d);
        if (this.\u0128) {
            final int n = this.\u0109 ? 7 : 9;
            int n2;
            int n4;
            int n3;
            if (this.\u011d) {
                n2 = this.\u0116 + this.\u0122 + 4;
                n3 = (n4 = n2 + n);
            }
            else {
                n2 = this.\u0116 - 4;
                n3 = (n4 = n2 - n);
            }
            if (this.\u0120 < this.\u0117 + n) {
                this.\u0120 = this.\u0117 + n;
            }
            final int \u0121 = this.\u0120;
            final int n5 = \u0121 - n;
            final int n6 = \u0121 + n;
            if (this.\u011e) {
                if (this.\u0126 && this.\u0125) {
                    this.\u0141.setColor(this.\u0146);
                }
                else {
                    this.\u0141.setColor(this.\u0148);
                }
                final int[] array = { n2, n3, n4 };
                this.\u0141.fillPolygon(array, new int[] { \u0121, n5, n6 }, array.length);
                if (!this.\u011e) {
                    this.\u011c = true;
                }
            }
        }
        if (!this.\u014c.equals(this.\u0154)) {
            this.\u0141.setColor(this.\u014c);
            this.\u0141.fillRect(this.\u0116, this.\u0117, this.\u0122, this.\u012c[this.\u013b - 1] + this.\u013c.getHeight() / 2 + this.\u0124);
        }
        if (!this.\u014a.equals(this.\u0154)) {
            this.\u0141.setColor(this.\u014a);
            if (this.\u011d) {
                this.\u0141.fillRect(this.\u0116 + this.\u0122, this.\u0117, 2, this.\u012c[this.\u013b - 1] + this.\u013c.getHeight() / 2 + this.\u0124);
            }
            else {
                this.\u0141.fillRect(this.\u0116 - 2, this.\u0117, 2, this.\u012c[this.\u013b - 1] + this.\u013c.getHeight() / 2 + this.\u0124);
            }
        }
        if (this.\u0126 && this.\u0127) {
            this.\u0141.setColor(this.\u0145);
            this.\u0141.fillRect(this.\u0116 + 2, this.\u0117 + this.\u012c[this.\u0121] - this.\u013c.getHeight() + 2, this.\u0122 - 4, this.\u013c.getHeight() + this.\u013c.getDescent() * 2 - 4);
        }
        for (int i = 0; i < this.\u013b; ++i) {
            if (this.\u0126 && !this.\u0125 && i == this.\u0121) {
                this.\u0141.setColor(this.\u0150);
            }
            else if (this.\u0126 && this.\u0125 && i == this.\u0121) {
                this.\u0141.setColor(this.\u014e);
            }
            else {
                this.\u0141.setColor(this.\u0152);
            }
            if (this.\u0111 && this.\u0110 != 500 && i == this.\u0110 && !this.\u0125) {
                this.\u0141.setColor(this.\u0143);
            }
            if (this.\u0123 == 0) {
                this.\u0141.drawString(this.\u013a[i], this.\u0116 + 4, this.\u0117 + this.\u012c[i]);
            }
            else if (this.\u0123 == 1) {
                this.\u0141.drawString(this.\u013a[i], this.\u0116 + (this.\u0122 - this.\u013c.stringWidth(this.\u013a[i])) / 2, this.\u0117 + this.\u012c[i]);
            }
            else if (this.\u0123 == 2) {
                this.\u0141.drawString(this.\u013a[i], this.\u0116 + this.\u0122 - 6 - this.\u013c.stringWidth(this.\u013a[i]), this.\u0117 + this.\u012c[i]);
            }
        }
        if (this.\u0111 && this.\u0110 != 500 && this.\u012b[this.\u0110] && (!this.\u0126 || !this.\u012b[this.\u0121])) {
            this.\u0141.drawImage(this.\u012a[this.\u0110], (this.\u013f - this.\u012a[this.\u0110].getWidth(this)) / 2, 0, this);
        }
        if (this.\u0126 && this.\u012b[this.\u0121]) {
            this.\u0141.drawImage(this.\u012a[this.\u0121], (this.\u013f - this.\u012a[this.\u0121].getWidth(this)) / 2, 0, this);
        }
        graphics.drawImage(this.\u0140, 0, 0, this);
        this.\u010f = false;
    }
    
    private void \u0158(final Graphics graphics) {
        int i = 0;
        int n = 0;
        while (i < this.\u013f) {
            this.\u0141.drawImage(this.\u0112, i, n, this);
            while (n + this.\u0112.getHeight(this) < this.\u013e) {
                n += this.\u0112.getHeight(this);
                this.\u0141.drawImage(this.\u0112, i, n, this);
            }
            n = 0;
            i += this.\u0112.getWidth(this);
        }
    }
    
    private void \u0157() {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.\u013b; ++i) {
            if (this.\u012b[i]) {
                final int height = this.\u012a[i].getHeight(this);
                if (height > n) {
                    n = height;
                }
                final int width = this.\u012a[i].getWidth(this);
                if (width > n2) {
                    n2 = width;
                }
            }
        }
        final int n3 = n + this.\u012c[this.\u013b - 1] + this.\u013c.getHeight() / 2 + this.\u0124;
        int n4 = this.\u0122 + 18 + 2;
        if (this.\u0128) {
            n4 += 11;
        }
        this.\u0114 = "ImageHeight: " + n + ";  Width: " + ((n4 >= n2) ? n4 : n2) + ";  Height: " + n3;
        for (int j = 0; j < this.\u013b; ++j) {
            if (this.\u0108) {
                this.\u012d[j] = this.\u0114;
            }
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.\u0108) {
            this.showStatus("UNREGISTERED CoolStrip © Cool Focus [www.coolfocus.com]");
            return true;
        }
        if (this.\u0115) {
            this.showStatus(this.\u0114);
        }
        return true;
    }
    
    public String getAppletInfo() {
        if (!this.\u0108) {
            return "\n\nUNREGISTERED CoolStrip v2.8a by Rob Young\nCopyright © 1998-2002 Cool Focus [www.coolfocus.com]\n\n";
        }
        return "\n\nCoolStrip v2.8a by Rob Young\nCopyright © 1998-2002 Cool Focus [www.coolfocus.com]\n\n";
    }
    
    private void \u0156(String substring) {
        if (!this.\u0106) {
            return;
        }
        if (substring == null || substring == "$" || substring.startsWith("$")) {
            return;
        }
        final JSObject window = JSObject.getWindow((Applet)this);
        substring = substring.substring(substring.indexOf(":") + 1);
        window.eval(substring);
    }
    
    private void \u0155() {
        this.\u010d = (System.getProperty("java.vendor").startsWith("Microsoft") || System.getProperty("java.vendor").startsWith("Sun Micro"));
        if (this.\u010d) {
            this.\u010d = (Float.valueOf(System.getProperty("java.version").substring(0, 3)) >= 1.1f);
        }
        if (!this.\u010d) {
            this.\u010d = System.getProperty("java.vendor").startsWith("Netscape");
            if (this.\u010d) {
                final String property = System.getProperty("java.version");
                if (property.lastIndexOf(".") == property.indexOf(".")) {
                    this.\u010d = false;
                }
                else {
                    this.\u010d = (Float.valueOf(System.getProperty("java.version").substring(2)) >= 1.5f);
                }
            }
        }
        final String parameter = this.getParameter("UseHandCursor");
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("yes")) {
                this.\u010c = true;
            }
            if (parameter.equalsIgnoreCase("no")) {
                this.\u010c = false;
            }
        }
        else {
            this.\u010c = true;
        }
    }
    
    public final void selecttItem(final String s) {
        if (!this.\u0106) {
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (Exception ex) {
            return;
        }
        if (int1 > 0) {
            this.selectItem(int1);
        }
    }
    
    public final void selectItem(int \u0111) {
        if (!this.\u0106) {
            return;
        }
        if (--\u0111 < -1) {
            return;
        }
        if (\u0111 == -1) {
            \u0111 = 500;
        }
        if (this.\u0110 != \u0111 && !this.\u010f) {
            this.\u0125 = false;
            if (this.\u0111) {
                this.\u0110 = \u0111;
            }
            this.repaint();
            this.\u0121 = this.\u0110;
        }
    }
    
    public void showStatus(final String s) {
        try {
            JSObject.getWindow((Applet)this).eval("window.status='" + s + "';");
        }
        catch (Exception ex) {
            this.getAppletContext().showStatus(s);
        }
    }
    
    public ryCoolStrip() {
        this.\u0153 = new Color(255, 255, 255);
        this.\u0151 = new Color(0, 0, 0);
        this.\u014f = new Color(0, 0, 255);
        this.\u014d = new Color(255, 0, 0);
        this.\u014b = new Color(128, 255, 128);
        this.\u0149 = new Color(0, 128, 0);
        this.\u0147 = new Color(0, 0, 255);
        this.\u0144 = new Color(0, 0, 0);
        this.\u0142 = new Color(0, 255, 0);
        this.\u0121 = 500;
        this.\u0110 = 500;
        this.\u010f = false;
        this.\u010a = false;
        this.\u0108 = false;
        this.\u0107 = false;
        this.ready = false;
    }
}
