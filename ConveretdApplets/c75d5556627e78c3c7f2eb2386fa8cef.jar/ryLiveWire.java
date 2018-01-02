import netscape.javascript.JSObject;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.applet.AudioClip;
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

public class ryLiveWire extends Applet
{
    protected Color \u0168;
    private Color \u0167;
    protected Color \u0166;
    private Color \u0165;
    protected Color \u0164;
    protected Color \u0163;
    protected Color \u0162;
    protected Color \u0161;
    private Color \u0160;
    protected Color \u015f;
    protected Color \u015e;
    protected Color \u015d;
    protected Color \u015c;
    protected Color \u015b;
    protected Color \u015a;
    protected Color \u0159;
    private Color[] \u0158;
    private Color[] \u0157;
    private Color[] \u0156;
    private Color \u0155;
    private Color \u0154;
    private Color \u0153;
    private Color \u0152;
    protected Graphics \u0151;
    protected Graphics \u0150;
    protected Graphics \u014f;
    protected Graphics \u014e;
    protected Image \u014d;
    protected Image \u014c;
    protected Image \u014b;
    protected Image \u014a;
    protected Font \u0149;
    protected Font \u0148;
    protected Font \u0147;
    protected FontMetrics \u0146;
    protected FontMetrics \u0145;
    protected FontMetrics \u0144;
    protected int \u0143;
    protected int \u0142;
    private String \u0141;
    private boolean \u0140;
    private boolean \u013f;
    private boolean \u013e;
    private ryLWMenu[] \u013d;
    private int \u013c;
    private int \u013b;
    protected int \u013a;
    private int \u0139;
    private int \u0138;
    protected int \u0137;
    private int \u0136;
    private int \u0135;
    private int \u0134;
    protected ryLWRun \u0133;
    private ryLWSRun \u0132;
    private ryLWStatus \u0131;
    protected boolean \u0130;
    protected boolean \u012f;
    protected int \u012e;
    protected int \u012d;
    protected int \u012c;
    protected String \u012b;
    protected int \u012a;
    private boolean \u0129;
    private boolean \u0128;
    private boolean \u0127;
    protected int \u0126;
    protected boolean \u0125;
    protected boolean \u0124;
    protected int \u0123;
    private boolean \u0122;
    private int \u0121;
    private int \u0120;
    protected boolean \u011f;
    protected int[] \u011e;
    protected int[] \u011d;
    protected int[] \u011c;
    protected Rectangle \u011b;
    private boolean \u011a;
    protected String \u0119;
    private boolean \u0118;
    protected int \u0117;
    private AudioClip \u0116;
    private AudioClip \u0115;
    private AudioClip \u0114;
    boolean \u0113;
    private boolean \u0112;
    protected Color \u0111;
    private Color \u0110;
    private boolean \u010f;
    private int \u010e;
    private int \u010d;
    private boolean \u010c;
    private boolean \u010b;
    private boolean \u010a;
    private boolean \u0109;
    private Color \u0108;
    private Color \u0107;
    private String \u0106;
    private String \u0105;
    private Font \u0104;
    private boolean \u0103;
    
    public ryLiveWire() {
        this.\u0136 = -1;
        this.\u0135 = -1;
        this.\u0134 = -1;
        this.\u0133 = null;
        this.\u0132 = null;
        this.\u0131 = null;
        this.\u0130 = false;
        this.\u012f = false;
        this.\u0126 = -1;
        this.\u0121 = 20;
        this.\u0120 = 20;
        this.\u011f = false;
        this.\u0119 = "";
        this.\u0112 = false;
        this.\u010f = false;
        this.\u010e = -1;
        this.\u010d = -1;
        this.\u010c = false;
        this.\u010b = false;
        this.\u0103 = false;
    }
    
    private void \u017d() {
        final String s = "LiveWire © 2001 Cool Focus [www.coolfocus.com]";
        final String s2 = "LiveWire (c) 2001 Cool Focus [www.coolfocus.com]";
        if (this.getParameter("Copyright") == null || (!this.getParameter("Copyright").equals(s) && !this.getParameter("Copyright").equals(s2))) {
            throw new SecurityException(" Copyright parameter missing or incorrect ");
        }
    }
    
    private void \u017c() {
        final String lowerCase = this.getDocumentBase().toString().toLowerCase();
        boolean b = false;
        if (lowerCase != null && lowerCase.startsWith("file:") && lowerCase.indexOf("livewire/docs/") >= 0) {
            this.\u010c = true;
            return;
        }
        final String lowerCase2 = this.getDocumentBase().getHost().toLowerCase();
        if (lowerCase2.indexOf("coolfocus.") > -1 || lowerCase2.indexOf("java-menus.com") > -1) {
            this.\u0103 = true;
            this.\u010c = true;
            return;
        }
        final String parameter = this.getParameter("Base");
        if (parameter == null) {
            this.\u010c = false;
            return;
        }
        if (parameter.indexOf("|") >= 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, "|");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.length() < 3) {
                    continue;
                }
                if (lowerCase.indexOf(nextToken) >= 0 || lowerCase.startsWith("file:")) {
                    b = true;
                    break;
                }
            }
        }
        else if (lowerCase.indexOf(parameter) >= 0 || lowerCase.startsWith("file:")) {
            b = true;
        }
        if (!b) {
            this.\u010c = false;
            return;
        }
        this.\u010c = this.\u017b(parameter);
    }
    
    private boolean \u017b(final String s) {
        final String parameter = this.getParameter("Key");
        if (parameter == null) {
            return false;
        }
        if (!this.\u0140) {
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
            s2 += " ";
        }
        return this.\u017a(s2, "31330139", "17102045").equals(s);
    }
    
    private String \u017a(final String s, final String s2, final String s3) {
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
                    string += (char)new BigInteger(substring, 2).intValue();
                }
            }
        }
        return string;
    }
    
    private int \u0179(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return Integer.valueOf(parameter, 16);
        }
        return color.getRGB();
    }
    
    private Color \u0178(final Color color, final Color color2) {
        return new Color((color.getRed() + color2.getRed()) / 2, (color.getGreen() + color2.getGreen()) / 2, (color.getBlue() + color2.getBlue()) / 2);
    }
    
    private String \u0177(final String s, final String s2) {
        String parameter = this.getParameter(s);
        if (parameter == null) {
            parameter = s2;
        }
        return parameter;
    }
    
    public String getAppletInfo() {
        if (!this.\u010c) {
            return "\n\nUNREGISTERED LiveWire v2.12b by Rob Young\nCopyright © 2001-2005 Cool Focus [www.coolfocus.com]\n\n";
        }
        return "\n\nLiveWire v2.12b by Rob Young\nCopyright © 2001-2005 Cool Focus [www.coolfocus.com]\n\n";
    }
    
    public void init() {
        this.\u0170();
        this.\u017c();
        if (!this.\u0103) {
            this.\u017d();
        }
        if (!this.\u0103) {
            System.out.println(this.getAppletInfo());
        }
        if (!this.\u010c) {
            this.\u010b = true;
        }
        this.\u010a = (this.\u010c || this.getDocumentBase().toString().toLowerCase().startsWith("file:"));
        this.\u0104 = this.\u016f("Tahoma|MS Sans Serif|Dialog,0,9");
        this.\u0143 = this.size().width;
        this.\u0142 = this.size().height;
        this.\u014d = this.createImage(this.\u0143, this.\u0142);
        this.\u0151 = this.\u014d.getGraphics();
        this.\u0168 = new Color(this.\u0179("BgColor", new Color(255, 255, 255)));
        this.\u0167 = new Color(this.\u0179("MenubarColor", new Color(0, 0, 0)));
        this.\u0166 = new Color(this.\u0179("PanelColor", new Color(35, 0, 120)));
        this.\u0165 = new Color(this.\u0179("CaptionColor", new Color(240, 240, 255)));
        this.\u0164 = new Color(this.\u0179("CaptionFocusColor", new Color(224, 0, 224)));
        this.\u0163 = new Color(this.\u0179("ItemColor", new Color(240, 240, 255)));
        this.\u0162 = new Color(this.\u0179("ItemFocusColor", new Color(224, 0, 224)));
        this.\u0111 = new Color(this.\u0179("ItemSelectColor", new Color(0, 255, 255)));
        this.\u0161 = new Color(this.\u0179("SubitemColor", new Color(240, 240, 255)));
        this.\u0160 = new Color(this.\u0179("SubitemFocusColor", new Color(255, 0, 0)));
        this.\u0110 = new Color(this.\u0179("SubitemSelectColor", new Color(0, 255, 255)));
        this.\u015e = new Color(this.\u0179("VerticalLineColor", new Color(224, 0, 224)));
        this.\u015d = new Color(this.\u0179("HorizontalLineColor", new Color(204, 0, 204)));
        this.\u015c = new Color(this.\u0179("MarkerColor", new Color(240, 240, 255)));
        this.\u015b = new Color(this.\u0179("MarkerFocusColor", new Color(255, 0, 0)));
        this.\u015f = new Color(this.\u0179("SubSeparatorColor", new Color(255, 0, 0)));
        this.\u015a = new Color(this.\u0179("StatusBarColor", new Color(0, 0, 0)));
        this.\u0159 = new Color(this.\u0179("StatusTextColor", new Color(240, 240, 255)));
        this.setBackground(this.\u0168);
    }
    
    public void start() {
        this.\u0109 = (this.getParameter("ShowLoad") == null);
        this.\u0108 = new Color(this.\u0179("LoadBgColor", Color.black));
        this.\u0107 = new Color(this.\u0179("LoadTextColor", Color.green));
        this.\u0106 = this.\u0177("LoadText1", "Loading LiveWire...");
        this.\u0105 = this.\u0177("LoadText2", "");
        if (this.\u0109) {
            this.repaint();
        }
        else {
            this.\u0176();
        }
    }
    
    private void \u0176() {
        this.\u0155 = this.\u0178(this.\u0167, this.\u0164);
        this.\u0154 = this.\u0178(this.\u0166, this.\u0162);
        this.\u0153 = this.\u0178(this.\u0166, this.\u0160);
        this.\u0152 = this.\u0178(this.\u0166, this.\u015b);
        this.\u0141 = this.\u0177("DefaultTarget", "_top");
        this.\u0149 = this.\u016f(this.\u0177("CaptionFont", "Dialog,bold,12"));
        this.\u0146 = this.getFontMetrics(this.\u0149);
        this.\u0148 = this.\u016f(this.\u0177("ItemFont", "Dialog,plain,12"));
        this.\u0145 = this.getFontMetrics(this.\u0148);
        this.\u0147 = this.\u016f(this.\u0177("StatusFont", "Dialog,plain,12"));
        this.\u0144 = this.getFontMetrics(this.\u0147);
        this.\u011b = new Rectangle(0, this.\u0142 - this.\u0144.getHeight() - 5, this.\u0143, this.\u0144.getHeight() + 5);
        final String \u0177 = this.\u0177("MenubarIndent", "0");
        try {
            this.\u013c = Integer.parseInt(\u0177);
        }
        catch (Exception ex) {
            this.\u013c = 0;
        }
        if (this.\u013c < 0) {
            this.\u013c = 0;
        }
        this.\u013c += 5;
        final String \u01772 = this.\u0177("CaptionSpacing", "14");
        try {
            this.\u0138 = Integer.parseInt(\u01772);
        }
        catch (Exception ex2) {
            this.\u0138 = 14;
        }
        if (this.\u0138 < 0) {
            this.\u0138 = 0;
        }
        final String \u01773 = this.\u0177("SlideDelay", "15");
        try {
            this.\u012e = Integer.parseInt(\u01773);
        }
        catch (Exception ex3) {
            this.\u012e = 15;
        }
        if (this.\u012e < 0) {
            this.\u012e = 0;
        }
        this.\u012b = " " + this.\u0177("SubSeparatorText", "|") + " ";
        this.\u012a = this.\u0145.stringWidth(this.\u012b);
        final String \u01774 = this.\u0177("ItemExtraSpacing", "0");
        int int1;
        try {
            int1 = Integer.parseInt(\u01774);
        }
        catch (Exception ex4) {
            int1 = 0;
        }
        this.\u0137 = this.\u0145.getHeight() + this.\u0145.getDescent() + int1;
        this.\u0158 = new Color[this.\u0137];
        this.\u0157 = new Color[this.\u0137];
        this.\u0158 = this.\u0171(this.\u0163, this.\u0166);
        this.\u0157 = this.\u0171(this.\u0161, this.\u0166);
        this.\u013a = this.\u0146.getHeight() + 5;
        this.\u0139 = this.\u0146.getAscent() + (this.\u013a - this.\u0146.getHeight()) / 2;
        if (this.\u0177("UseImages", "no").equalsIgnoreCase("yes")) {
            this.\u0122 = true;
        }
        else {
            this.\u0122 = false;
        }
        final String \u01775 = this.\u0177("SubChangeAmount", "2");
        try {
            this.\u0117 = Integer.parseInt(\u01775);
        }
        catch (Exception ex5) {
            this.\u0117 = 1;
        }
        if (this.\u0117 < 1) {
            this.\u0117 = 1;
        }
        if (this.\u0117 > 5) {
            this.\u0117 = 5;
        }
        this.\u0113 = (this.\u0177("Demo", null) != null);
        int n = 1;
        int n2 = 0;
        while (this.\u0177("Menu" + n, null) != null) {
            ++n2;
            ++n;
        }
        this.\u013d = new ryLWMenu[n2];
        int \u013c = this.\u013c;
        for (int i = 0; i < n2; ++i) {
            final String \u01776 = this.\u0177("Menu" + (i + 1), "");
            final String \u01777 = this.\u0177("URL" + (i + 1), "");
            final String \u01778 = this.\u0177("Target" + (i + 1), this.\u0141);
            String s = this.\u0177("Status" + (i + 1), "");
            if (this.\u0113 && s.equals("")) {
                s = "This is " + \u01776;
            }
            this.\u0174(i, \u01776, \u01777, \u01778, s, \u013c);
            \u013c += this.\u0146.stringWidth(\u01776) + this.\u0138;
            for (int j = 0; j < this.\u013d[i].\u0195; ++j) {
                if (this.\u013d[i].\u018e) {
                    this.\u013d[i].\u0196[j].\u0187 = this.\u013d[i].\u0193 - this.\u0145.stringWidth(this.\u013d[i].\u0196[j].\u018a) - 2;
                    this.\u013d[i].\u0196[j].\u0181.x = this.\u013d[i].\u0196[j].\u0187;
                }
                else {
                    this.\u013d[i].\u0196[j].\u0187 = this.\u013d[i].\u0194 + 2;
                }
            }
        }
        this.\u013b = this.\u0143;
        boolean b = false;
        if (this.\u0177("AutosizeMenubar", "no").equalsIgnoreCase("yes")) {
            b = true;
        }
        if (b && !this.\u0122) {
            this.\u013b = this.\u013d[n2 - 1].\u0194 + this.\u0146.stringWidth(this.\u013d[n2 - 1].\u0199) + this.\u013c;
        }
        else if (b && this.\u0122) {
            this.\u013b = this.\u013d[n2 - 1].\u0194 + this.\u0121 + this.\u013c;
        }
        if (this.\u0177("ShowVerticalLine", "yes").equalsIgnoreCase("no")) {
            this.\u0124 = false;
        }
        else {
            this.\u0124 = true;
        }
        final String \u01779 = this.\u0177("ShowMarkers", "both");
        if (\u01779.equalsIgnoreCase("none")) {
            this.\u0123 = 0;
        }
        if (\u01779.equalsIgnoreCase("side")) {
            this.\u0123 = 1;
        }
        if (\u01779.equalsIgnoreCase("top")) {
            this.\u0123 = 2;
        }
        else {
            this.\u0123 = 3;
        }
        if (this.\u0123 > 0) {
            if (this.\u0177("MarkerStyle", "square").equalsIgnoreCase("arrow")) {
                this.\u011f = true;
            }
            else {
                this.\u011f = false;
            }
        }
        if (this.\u0177("ShowStatusbar", "yes").equalsIgnoreCase("no")) {
            this.\u011a = false;
            this.\u011b = new Rectangle(0, this.\u0142 + 5, 0, 0);
        }
        else {
            this.\u011a = true;
        }
        if (this.\u011a) {
            this.\u014a = this.createImage(this.\u011b.width, this.\u011b.height - 1);
            (this.\u014e = this.\u014a.getGraphics()).setColor(this.\u015a);
            this.\u014e.fillRect(this.\u011b.x, 0, this.\u011b.width, this.\u011b.height);
            this.\u0156 = new Color[this.\u0137];
            this.\u0156 = this.\u0171(this.\u0159, this.\u015a);
        }
        try {
            this.\u0116 = this.getAudioClip(new URL(this.getDocumentBase(), this.getParameter("SoundMouseDown")));
        }
        catch (MalformedURLException ex6) {}
        try {
            this.\u0115 = this.getAudioClip(new URL(this.getDocumentBase(), this.getParameter("SoundMouseUp")));
        }
        catch (MalformedURLException ex7) {}
        try {
            this.\u0114 = this.getAudioClip(new URL(this.getDocumentBase(), this.getParameter("SoundMenuSelect")));
        }
        catch (MalformedURLException ex8) {}
        this.\u014c = this.createImage(this.\u0143, this.\u0142 - this.\u013a - this.\u011b.height);
        this.\u0150 = this.\u014c.getGraphics();
        if (this.\u0177("MouseRoam", "yes").equalsIgnoreCase("no")) {
            this.\u0118 = false;
        }
        else {
            this.\u0118 = true;
        }
        if (this.\u0118) {
            if (this.\u0177("Sticky", "no").equalsIgnoreCase("yes")) {
                this.\u0112 = true;
            }
            else {
                this.\u0112 = false;
            }
        }
        this.\u0109 = false;
        this.repaint();
        if (this.\u0112) {
            try {
                this.selectItem(Integer.parseInt(this.\u0177("SelectedMenu", "0")), Integer.parseInt(this.\u0177("SelectedItem", "0")), Integer.parseInt(this.\u0177("SelectedSubitem", "0")));
            }
            catch (Exception ex9) {}
        }
    }
    
    private void \u0175(final ryLWMenu ryLWMenu, final String s, final int n) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        final int countTokens = stringTokenizer.countTokens();
        if (countTokens > 0) {
            try {
                ryLWMenu.\u018d = this.getImage(new URL(this.getDocumentBase(), stringTokenizer.nextToken()));
            }
            catch (Exception ex) {
                ryLWMenu.\u018d = null;
            }
            if (ryLWMenu.\u018d != null) {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(ryLWMenu.\u018d, 0);
                try {
                    if (!mediaTracker.waitForID(0, 10000L)) {
                        ryLWMenu.\u018d = null;
                    }
                }
                catch (Exception ex2) {
                    ryLWMenu.\u018d = null;
                }
            }
            if (n == 0) {
                this.\u0121 = ryLWMenu.\u018d.getWidth(this);
                this.\u0120 = ryLWMenu.\u018d.getHeight(this);
                if (this.\u0121 < 1) {
                    this.\u0121 = 20;
                }
                if (this.\u0120 < 1) {
                    this.\u0120 = 20;
                }
                this.\u013a = this.\u0120 + 5;
            }
        }
        if (countTokens > 1) {
            try {
                ryLWMenu.\u018c = this.getImage(new URL(this.getDocumentBase(), stringTokenizer.nextToken()));
            }
            catch (Exception ex3) {
                ryLWMenu.\u018c = null;
            }
            if (ryLWMenu.\u018c != null) {
                final MediaTracker mediaTracker2 = new MediaTracker(this);
                mediaTracker2.addImage(ryLWMenu.\u018c, 1);
                try {
                    if (!mediaTracker2.waitForID(1, 10000L)) {
                        ryLWMenu.\u018c = null;
                    }
                }
                catch (Exception ex4) {
                    ryLWMenu.\u018c = null;
                }
            }
        }
        else {
            ryLWMenu.\u018c = null;
        }
    }
    
    private void \u0174(final int n, final String s, final String s2, final String s3, final String s4, int \u0263) {
        int n2 = 0;
        int n3 = 0;
        while (this.\u0177(n + 1 + "-Item" + (n2 + 1), null) != null) {
            ++n3;
            ++n2;
        }
        this.\u013d[n] = new ryLWMenu(s, s2, s3, s4, n3, \u0263);
        this.\u013d[n].\u018f = new Rectangle(\u0263, 0, this.\u0146.stringWidth(s), this.\u013a - 1);
        this.\u013d[n].\u0193 = \u0263 + this.\u013d[n].\u018f.width;
        if (this.\u0122) {
            this.\u0175(this.\u013d[n], s, n);
            this.\u013d[n].\u0194 = this.\u013c + (this.\u0121 + this.\u0138) * n;
            this.\u013d[n].\u018f = new Rectangle(this.\u013d[n].\u0194, 0, this.\u0121, this.\u013a);
            this.\u013d[n].\u0193 = this.\u013d[n].\u0194 + this.\u013d[n].\u018f.width;
            this.\u013d[n].\u0191 = this.\u013d[n].\u0194 + 2;
            \u0263 = this.\u013d[n].\u0194;
        }
        int stringWidth = 0;
        int n4 = this.\u013a + this.\u0137;
        for (int i = 0; i < n3; ++i) {
            final String \u0177 = this.\u0177(n + 1 + "-Item" + (i + 1), "");
            final String \u01772 = this.\u0177(n + 1 + "-URL" + (i + 1), "");
            final String \u01773 = this.\u0177(n + 1 + "-Target" + (i + 1), this.\u0141);
            String s5 = this.\u0177(n + 1 + "-Status" + (i + 1), "");
            if (this.\u0113 && s5.equals("")) {
                s5 = "This is " + \u0177;
            }
            this.\u013d[n].\u0196[i] = new ryLWItem(\u0177, \u01772, \u01773, s5, n4);
            this.\u013d[n].\u0196[i].\u0181 = new Rectangle(this.\u013d[n].\u0194, n4 - this.\u0145.getAscent(), this.\u0145.stringWidth(\u0177) + 2, this.\u0145.getHeight());
            if (this.\u0145.stringWidth(\u0177) > stringWidth) {
                stringWidth = this.\u0145.stringWidth(\u0177);
            }
            this.\u0173(this.\u013d[n], this.\u013d[n].\u0196[i], n + 1 + "-" + (i + 1) + "-", n4);
            n4 += this.\u0137;
        }
        this.\u013d[n].\u0192 = stringWidth + 2;
    }
    
    private void \u0173(final ryLWMenu ryLWMenu, final ryLWItem ryLWItem, final String s, final int n) {
        int n2 = 0;
        int \u0183 = 0;
        while (this.\u0177(s + "Item" + (n2 + 1), null) != null) {
            ++\u0183;
            ++n2;
        }
        ryLWItem.\u0182 = \u0183;
        if (ryLWItem.\u0182 == 0) {
            ryLWItem.\u0180 = new Rectangle(20000, 0, 0, 0);
            return;
        }
        ryLWItem.\u0183 = new ryLWSub[ryLWItem.\u0182];
        int n3 = ryLWMenu.\u0191 + this.\u0145.stringWidth(ryLWItem.\u018a);
        for (int i = 0; i < ryLWItem.\u0182; ++i) {
            final String \u0177 = this.\u0177(s + "Item" + (i + 1), "");
            final String \u01772 = this.\u0177(s + "URL" + (i + 1), "");
            final String \u01773 = this.\u0177(s + "Target" + (i + 1), this.\u0141);
            String s2 = this.\u0177(s + "Status" + (i + 1), "");
            if (this.\u0113 && s2.equals("")) {
                s2 = "This is " + \u0177;
            }
            ryLWItem.\u0183[i] = new ryLWSub(\u0177, \u01772, \u01773, s2, n3, n);
            ryLWItem.\u0183[i].\u01ab = new Rectangle(n3 + this.\u012a, n - this.\u0145.getAscent(), this.\u0145.stringWidth(\u0177), this.\u0145.getHeight());
            n3 += this.\u0145.stringWidth(\u0177) + this.\u012a;
        }
        ryLWItem.\u0180 = new Rectangle(ryLWMenu.\u0194, n - this.\u0145.getAscent(), n3 - ryLWMenu.\u0191 + 2, this.\u0145.getHeight());
        if (ryLWMenu.\u0194 + ryLWItem.\u0180.width > this.\u0143) {
            if (this.\u0143 - ryLWMenu.\u018f.x - ryLWMenu.\u018f.width < ryLWMenu.\u0194) {
                ryLWMenu.\u018e = true;
            }
            if (ryLWMenu.\u018e) {
                int n4 = ryLWMenu.\u0193 - this.\u0145.stringWidth(ryLWItem.\u018a) - 2;
                for (int j = 0; j < ryLWItem.\u0182; ++j) {
                    n4 -= this.\u012a + this.\u0145.stringWidth(ryLWItem.\u0183[j].\u01b2);
                    ryLWItem.\u0183[j].\u01ae = n4;
                    ryLWItem.\u0183[j].\u01ab.x = n4;
                }
                ryLWItem.\u0180.x = ryLWMenu.\u0193 - ryLWItem.\u0180.width - 2;
            }
        }
    }
    
    public boolean mouseMove(final Event event, final int \u012d, final int \u012d2) {
        if (this.\u0109 || this.\u010b) {
            return true;
        }
        this.\u012d = -50;
        this.\u012c = -50;
        final boolean \u0119 = this.\u0118;
        this.\u010f = (\u012d2 > this.\u013a + 1 && ((this.\u011a && \u012d2 < this.\u011b.y) || (!this.\u011a && \u012d2 < this.\u0142)));
        if (this.\u011b.inside(\u012d, \u012d2)) {
            this.mouseExit(event, -89, \u012d2);
            return true;
        }
        if (\u012d2 < this.\u013a - 1) {
            int \u0137 = -1;
            for (int i = 0; i < this.\u013d.length; ++i) {
                if (this.\u013d[i].\u018f.inside(\u012d, \u012d2)) {
                    \u0137 = i;
                    break;
                }
            }
            if (\u0137 == this.\u0136) {
                if (\u0137 > -1 && !this.\u0130) {
                    this.\u016a(this.\u013d[this.\u0136].\u018b);
                    this.\u016d(this.\u013d[this.\u0136].\u0190);
                }
                return true;
            }
            if (\u0137 == -1 && this.\u0136 > -1) {
                if (this.\u0130) {
                    this.\u0130 = false;
                    this.stop();
                }
                this.\u0136 = -1;
                this.\u0135 = -1;
                this.\u0134 = -1;
                this.repaint();
                this.\u016a("");
                this.\u016d(false);
                return true;
            }
            this.\u0136 = \u0137;
            this.\u0135 = -1;
            this.\u010e = -1;
            this.\u010d = -1;
            this.\u0150.setColor(this.\u0166);
            this.\u0150.fillRect(0, 0, this.\u0143, this.\u0142);
            this.repaint(0, 0, this.\u0143, this.\u013a);
            this.\u016d(this.\u013d[this.\u0136].\u0190);
            if (this.\u0114 != null) {
                this.\u0114.play();
            }
            if (this.\u013d[this.\u0136].\u0195 > 0) {
                while (this.\u0133 != null) {
                    try {
                        this.\u0133.\u019f();
                    }
                    catch (Exception ex) {}
                }
                this.startupmenu(true);
            }
            else {
                this.repaint(0, this.\u013a, this.\u0143, this.\u0142);
            }
            return true;
        }
        else {
            if (\u012d2 >= this.\u013a && \u012d2 <= this.\u013a + (this.\u0137 - this.\u0145.getAscent())) {
                this.\u016d(false);
            }
            if (this.\u0136 > -1 && \u012d2 > this.\u013a && this.\u013d[this.\u0136].\u0195 == 0) {
                this.\u0136 = -1;
                this.\u0135 = -1;
                this.\u0134 = -1;
                if (this.\u0130) {
                    this.\u0130 = false;
                    this.stop();
                }
                this.repaint();
                this.\u016a("");
                this.\u016d(false);
                return true;
            }
            if (this.\u0136 == -1) {
                this.\u0135 = -1;
                this.\u0134 = -1;
                if (this.\u0130) {
                    this.\u0130 = false;
                    this.stop();
                    this.repaint();
                }
                this.\u016d(false);
                this.\u016a("");
                return true;
            }
            if ((this.\u012c = \u012d) > this.\u013b - 5) {
                this.\u012c = this.\u013b - 5;
            }
            this.repaint(this.\u012c - 20, this.\u013a - 3, this.\u012c + 20, 6);
            if (!this.\u0118 && \u012d2 > this.\u013a + this.\u013d[this.\u0136].\u0196[this.\u013d[this.\u0136].\u0195 - 1].\u0185 + 6) {
                this.\u0136 = -1;
                this.\u0135 = -1;
                this.\u0134 = -1;
                if (this.\u0130 || this.\u012f) {
                    this.\u0130 = false;
                    this.\u012f = false;
                    this.stop();
                }
                this.repaint();
                this.\u016a("");
                this.\u016d(false);
                return true;
            }
            if (\u012d2 > this.\u013a + 1) {
                this.\u012d = \u012d2;
            }
            else {
                this.\u012d = -50;
            }
            if (\u012d2 > this.\u013a + this.\u013d[this.\u0136].\u0196[this.\u013d[this.\u0136].\u0195 - 1].\u0185 + 3) {
                this.\u012d = this.\u013a + this.\u013d[this.\u0136].\u0196[this.\u013d[this.\u0136].\u0195 - 1].\u0185 + 3;
            }
            if (!this.\u013d[this.\u0136].\u018e) {
                this.repaint(this.\u013d[this.\u0136].\u0194 - 5, this.\u013a, 5, this.\u0142);
            }
            else {
                this.repaint(this.\u013d[this.\u0136].\u0193, this.\u013a, 5, this.\u0142);
            }
            int \u0135 = -1;
            final int \u01352 = this.\u0135;
            for (int j = 0; j < this.\u013d[this.\u0136].\u0195; ++j) {
                if (this.\u013d[this.\u0136].\u0196[j].\u0181.inside(\u012d, \u012d2)) {
                    \u0135 = j;
                    break;
                }
            }
            this.\u0118 = false;
            if (\u0135 > -1 && \u0135 == \u01352) {
                this.\u016a(this.\u013d[this.\u0136].\u0196[\u0135].\u017e);
                this.\u016d(this.\u013d[this.\u0136].\u0196[\u0135].\u017f);
                this.\u0118 = \u0119;
                return true;
            }
            if (\u0135 == -1 && this.\u0134 == -1) {
                this.\u016a("");
            }
            if (\u0135 != this.\u0135 && this.\u0135 > -1 && !this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.inside(\u012d, \u012d2) && !\u0119) {
                this.quit();
                this.\u0135 = -1;
                this.\u0134 = -1;
                this.\u016d(false);
            }
            if (\u01352 > -1) {
                if (!this.\u013d[this.\u0136].\u018e) {
                    this.repaint(this.\u013d[this.\u0136].\u0194 - 5, this.\u013a - 3, this.\u013d[this.\u0136].\u0192 + 6, this.\u0142 - this.\u013a - this.\u011b.height + 3);
                }
                else {
                    this.repaint(this.\u013d[this.\u0136].\u0193 - this.\u013d[this.\u0136].\u0192, this.\u013a - 3, this.\u013d[this.\u0136].\u0192 + 6, this.\u0142 - this.\u013a - this.\u011b.height + 3);
                }
                this.repaint(this.\u013d[this.\u0136].\u0196[\u01352].\u0180.x, this.\u013d[this.\u0136].\u0196[\u01352].\u0180.y, this.\u013d[this.\u0136].\u0196[\u01352].\u0180.width + 2, this.\u013d[this.\u0136].\u0196[\u01352].\u0180.height + 2);
                if (this.\u0135 == -1 && this.\u0134 == -1) {
                    this.\u016a("");
                }
            }
            if (!\u0119 && \u0135 > -1 && \u0135 != this.\u0135 && this.\u013d[this.\u0136].\u0196[\u0135].\u0182 > 0) {
                this.\u0135 = \u0135;
                if (this.\u010e > -1) {
                    this.repaint(this.\u013d[this.\u0136].\u0196[this.\u010e].\u0181.x, this.\u013d[this.\u0136].\u0196[this.\u010e].\u0181.y, this.\u013d[this.\u0136].\u0196[this.\u010e].\u0181.width + 6, this.\u013d[this.\u0136].\u0196[this.\u010e].\u0181.height);
                }
                this.\u010d = -1;
                this.\u016a(this.\u013d[this.\u0136].\u0196[this.\u0135].\u017e);
                this.\u0118 = \u0119;
                this.startupitem();
                return true;
            }
            if (\u0119) {
                Label_1712: {
                    if (this.\u012f || this.\u0125) {
                        if (\u0135 == -1) {
                            break Label_1712;
                        }
                        if (\u0135 == this.\u0135) {
                            break Label_1712;
                        }
                    }
                    if (this.\u012f && \u0135 > -1 && this.\u013d[this.\u0136].\u0196[\u0135].\u0181.inside(\u012d, \u012d2)) {
                        this.\u0118 = \u0119;
                    }
                }
                if (\u0135 > -1 && \u0135 != this.\u0135 && this.\u013d[this.\u0136].\u0196[\u0135].\u0182 > 0) {
                    try {
                        while (this.\u0132 != null) {
                            this.\u0132.\u019f();
                        }
                    }
                    catch (Exception ex2) {}
                    this.\u0135 = \u0135;
                    if (this.\u010e > -1) {
                        this.repaint(this.\u013d[this.\u0136].\u0196[this.\u010e].\u0181.x, this.\u013d[this.\u0136].\u0196[this.\u010e].\u0181.y, this.\u013d[this.\u0136].\u0196[this.\u010e].\u0181.width + 6, this.\u013d[this.\u0136].\u0196[this.\u010e].\u0181.height);
                    }
                    this.\u010d = -1;
                    this.\u016a(this.\u013d[this.\u0136].\u0196[this.\u0135].\u017e);
                    this.\u0118 = \u0119;
                    this.startupitem();
                    return true;
                }
            }
            this.\u0118 = \u0119;
            if (\u0135 == -1 && this.\u0135 > -1 && this.\u013d[this.\u0136].\u0196[this.\u0135].\u0182 == 0) {
                this.\u0135 = -1;
                this.\u016d(false);
                return true;
            }
            if (\u0135 > -1) {
                this.\u0135 = \u0135;
                if (this.\u013d[this.\u0136].\u0196[this.\u0135].\u0182 == 0) {
                    this.repaint(this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.x, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.y, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.width + 6, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.height);
                    this.\u016a(this.\u013d[this.\u0136].\u0196[this.\u0135].\u017e);
                    this.\u016d(this.\u013d[this.\u0136].\u0196[\u0135].\u017f);
                    return true;
                }
                this.\u016d(this.\u013d[this.\u0136].\u0196[\u0135].\u017f);
            }
            if (\u0135 == -1 && this.\u0135 > -1) {
                if (this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.inside(\u012d, \u012d2)) {
                    this.\u016a(this.\u013d[this.\u0136].\u0196[this.\u0135].\u017e);
                }
                else {
                    if (this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.inside(\u012d, \u012d2) || (this.\u013d[this.\u0136].\u0196[this.\u0135].\u0182 > 0 && \u0119)) {
                        this.handleSubMouseMove(event, \u012d, \u012d2, this.\u0135);
                        return true;
                    }
                    final int \u01353 = this.\u0135;
                    this.\u0135 = -1;
                    this.\u0134 = -1;
                    this.\u016d(false);
                }
            }
            return true;
        }
    }
    
    public void handleSubMouseMove(final Event event, final int n, final int n2, final int n3) {
        if (n3 == -1) {
            this.\u016a("");
            this.\u016d(false);
            return;
        }
        if (this.\u013d[this.\u0136].\u0196[n3].\u0181.inside(n, n2)) {
            return;
        }
        if (this.\u013d[this.\u0136].\u0196[n3].\u0182 == 0) {
            return;
        }
        int \u0135 = -1;
        final int \u01352 = this.\u0134;
        for (int i = 0; i < this.\u013d[this.\u0136].\u0196[n3].\u0182; ++i) {
            if (this.\u013d[this.\u0136].\u0196[n3].\u0183[i].\u01ab.inside(n, n2)) {
                \u0135 = i;
                break;
            }
        }
        if (\u0135 == this.\u0134) {
            return;
        }
        if (\u0135 == -1) {
            this.\u0134 = -1;
            this.repaint(this.\u013d[this.\u0136].\u0196[n3].\u0180.x, this.\u013d[this.\u0136].\u0196[n3].\u0180.y, this.\u013d[this.\u0136].\u0196[n3].\u0180.width + 4, this.\u013d[this.\u0136].\u0196[n3].\u0180.height);
            this.\u016a("");
            this.\u016d(false);
            return;
        }
        this.\u0134 = \u0135;
        this.\u016a(this.\u013d[this.\u0136].\u0196[n3].\u0183[this.\u0134].\u01a9);
        if (this.\u012f) {
            this.\u016d(this.\u013d[this.\u0136].\u0196[n3].\u0183[this.\u0134].\u01aa);
            return;
        }
        this.repaint(this.\u013d[this.\u0136].\u0196[n3].\u0180.x, this.\u013d[this.\u0136].\u0196[n3].\u0180.y, this.\u013d[this.\u0136].\u0196[n3].\u0180.width + 4, this.\u013d[this.\u0136].\u0196[n3].\u0180.height);
        if (this.\u0134 == -1) {
            this.\u016d(false);
        }
        else {
            this.\u016d(this.\u013d[this.\u0136].\u0196[n3].\u0183[this.\u0134].\u01aa);
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.\u010c) {
            this.showStatus("");
        }
        else {
            this.showStatus("UNREGISTERED LiveWire by Cool Focus [www.coolfocus.com]");
        }
        if (this.\u011a) {
            this.\u010f = (n2 < this.\u011b.y);
        }
        else {
            this.\u010f = (n2 < this.\u0142);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.\u016a("");
        this.\u016d(false);
        if (n == -89 && !this.\u010c) {
            this.showStatus("UNREGISTERED LiveWire by Cool Focus [www.coolfocus.com]");
        }
        else {
            this.showStatus("");
        }
        this.\u010f = false;
        if (this.\u0112) {
            this.repaint();
            return true;
        }
        this.\u0136 = -1;
        this.\u0135 = -1;
        this.\u0134 = -1;
        this.\u0129 = false;
        this.\u0128 = false;
        this.\u0127 = false;
        this.\u0126 = -1;
        if (this.\u0130 || this.\u012f) {
            this.stop();
        }
        this.\u0130 = false;
        this.\u012f = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.\u0134 > -1 && this.\u013d[this.\u0136].\u0196[this.\u0135].\u0183[this.\u0134].\u01ab.inside(n, n2)) {
            if (!this.\u013d[this.\u0136].\u0196[this.\u0135].\u0183[this.\u0134].\u01aa) {
                return true;
            }
            this.\u0127 = true;
            this.repaint(this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.x, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.y, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.width + 2, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.height + 2);
            if (this.\u0116 != null) {
                this.\u0116.play();
            }
            return true;
        }
        else if (this.\u0135 > -1 && this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.inside(n, n2)) {
            if (!this.\u013d[this.\u0136].\u0196[this.\u0135].\u017f) {
                return true;
            }
            this.\u0128 = true;
            this.repaint(this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.x, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.y, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.width + 2, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.height + 2);
            if (this.\u0116 != null) {
                this.\u0116.play();
            }
            return true;
        }
        else {
            if (this.\u0136 <= -1 || !this.\u013d[this.\u0136].\u018f.inside(n, n2)) {
                return true;
            }
            if (!this.\u013d[this.\u0136].\u0190) {
                return true;
            }
            this.\u0129 = true;
            this.repaint(this.\u013d[this.\u0136].\u018f.x - 6, 0, this.\u013d[this.\u0136].\u018f.width + 12, this.\u013a + 2);
            if (this.\u0116 != null) {
                this.\u0116.play();
            }
            return true;
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.\u0127) {
            this.\u0127 = false;
            if (this.\u0134 > -1 && this.\u0112) {
                this.\u010d = this.\u0134;
            }
            if (this.\u010e > -1) {
                this.\u010e = -1;
                this.repaint();
            }
            else {
                this.repaint(this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.x, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.y, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.width + 2, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.height + 2);
            }
            if (this.\u0115 != null) {
                this.\u0115.play();
            }
            if (this.\u0134 > -1) {
                this.\u016c(this.\u013d[this.\u0136].\u0196[this.\u0135].\u0183[this.\u0134].\u01b1, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0183[this.\u0134].\u01b0);
            }
            return true;
        }
        if (this.\u0128) {
            this.\u0128 = false;
            if (this.\u0135 > -1 && this.\u0112) {
                this.\u010e = this.\u0135;
                this.repaint();
            }
            else {
                this.repaint(this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.x, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.y, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.width + 2, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.height + 2);
            }
            if (this.\u0115 != null) {
                this.\u0115.play();
            }
            if (this.\u0135 > -1) {
                this.\u016c(this.\u013d[this.\u0136].\u0196[this.\u0135].\u0189, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0188);
            }
            return true;
        }
        if (this.\u0129) {
            this.\u0129 = false;
            this.repaint(this.\u013d[this.\u0136].\u018f.x - 6, 0, this.\u013d[this.\u0136].\u018f.width + 12, this.\u013a + 2);
            if (this.\u0115 != null) {
                this.\u0115.play();
            }
            if (this.\u0136 > -1) {
                this.\u016c(this.\u013d[this.\u0136].\u0198, this.\u013d[this.\u0136].\u0197);
            }
            return true;
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.\u0127 && !this.\u013d[this.\u0136].\u0196[this.\u0135].\u0183[this.\u0134].\u01ab.inside(n, n2)) {
            this.\u0134 = -1;
            this.\u0127 = false;
            this.\u016a("");
            this.repaint(0, this.\u013a - 2, this.\u0143, 2 + this.\u0142 - this.\u013a - this.\u011b.height);
            this.\u016d(false);
            return true;
        }
        if (this.\u0128 && !this.\u013d[this.\u0136].\u0196[this.\u0135].\u0181.inside(n, n2)) {
            this.\u0135 = -1;
            this.\u0128 = false;
            this.\u016a("");
            this.repaint(0, this.\u013a, this.\u0143, this.\u0142 - this.\u013a - this.\u011b.height);
            this.\u016d(false);
            return true;
        }
        if (this.\u0129 && !this.\u013d[this.\u0136].\u018f.inside(n, n2)) {
            this.\u0136 = -1;
            this.\u0129 = false;
            this.\u016a("");
            this.repaint(0, 0, this.\u0143, this.\u0142 - this.\u011b.height);
            this.\u016d(false);
            return true;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u010b) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            graphics.setColor(Color.green);
            graphics.setFont(this.\u0104);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.drawString("UNREGISTERED", (this.size().width - fontMetrics.stringWidth("UNREGISTERED")) / 2, 20);
            graphics.drawString("LiveWire", (this.size().width - fontMetrics.stringWidth("LiveWire")) / 2, 35);
            graphics.drawString("by Cool Focus", (this.size().width - fontMetrics.stringWidth("by Cool Focus")) / 2, 50);
            graphics.drawString("www.coolfocus.com", (this.size().width - fontMetrics.stringWidth("www.coolfocus.com")) / 2, 65);
            Thread.currentThread();
            try {
                Thread.sleep(4000L);
            }
            catch (InterruptedException ex) {}
            this.\u010b = false;
        }
        if (this.\u0109) {
            graphics.setColor(this.\u0108);
            graphics.fillRect(0, 0, this.\u0143, this.\u0142);
            graphics.setColor(this.\u0107);
            graphics.setFont(this.\u0104);
            graphics.drawString(this.\u0106, 10, 20);
            graphics.drawString(this.\u0105, 10, 35);
            this.\u0176();
            return;
        }
        this.\u0151.setColor(this.\u0168);
        this.\u0151.fillRect(0, 0, this.\u0143, this.\u0142);
        this.\u0151.setColor(this.\u0167);
        this.\u0151.fillRect(0, 0, this.\u013b, this.\u013a);
        this.\u0151.setColor(this.\u0166);
        this.\u0151.fillRect(0, this.\u013a, this.\u0143, this.\u0142);
        this.\u0151.setFont(this.\u0149);
        if (!this.\u0122) {
            for (int i = 0; i < this.\u013d.length; ++i) {
                if (i == this.\u0136) {
                    this.\u0151.setColor(this.\u0129 ? this.\u0155 : this.\u0164);
                }
                else {
                    this.\u0151.setColor(this.\u0165);
                }
                this.\u0151.drawString(this.\u013d[i].\u0199, this.\u013d[i].\u0194, ((this.\u0129 && i == this.\u0136) ? 1 : 0) + this.\u0139);
            }
        }
        else {
            for (int j = 0; j < this.\u013d.length; ++j) {
                if (j == this.\u0136) {
                    this.\u0151.setColor(this.\u0129 ? this.\u0155 : this.\u0164);
                }
                else {
                    this.\u0151.setColor(this.\u0167);
                }
                final int n = (this.\u0129 && j == this.\u0136) ? 1 : 0;
                if (j != this.\u0136 && this.\u013d[j].\u018d != null) {
                    this.\u0151.drawImage(this.\u013d[j].\u018d, this.\u013d[j].\u0194, this.\u013d[j].\u018f.y + 2, this);
                }
                else if (j == this.\u0136) {
                    if (this.\u013d[j].\u018c != null) {
                        this.\u0151.drawImage(this.\u013d[j].\u018c, this.\u013d[j].\u0194, n + this.\u013d[j].\u018f.y + 2, this);
                    }
                    else if (this.\u013d[j].\u018d != null) {
                        this.\u0151.drawImage(this.\u013d[j].\u018d, this.\u013d[j].\u0194, n + this.\u013d[j].\u018f.y + 2, this);
                    }
                }
            }
        }
        if (this.\u0122 && this.\u0136 > -1) {
            final int \u0129 = this.\u0129 ? 1 : 0;
            this.\u0151.setColor(this.\u0129 ? this.\u0155 : this.\u0164);
            if (!this.\u013d[this.\u0136].\u018e) {
                this.\u0151.drawRect(this.\u013d[this.\u0136].\u0194 - 3, \u0129 + this.\u013d[this.\u0136].\u018f.y + 2, this.\u013d[this.\u0136].\u018f.width + 2, \u0129 + this.\u013d[this.\u0136].\u018f.height - 5);
            }
            else {
                this.\u0151.drawRect(this.\u013d[this.\u0136].\u0194 - 2, \u0129 + this.\u013d[this.\u0136].\u018f.y + 2, this.\u013d[this.\u0136].\u018f.width + 2, \u0129 + this.\u013d[this.\u0136].\u018f.height - 5);
            }
        }
        this.\u0151.setColor(this.\u015d);
        this.\u0151.fillRect(0, this.\u013a - 1, this.\u013b, 1);
        this.\u0172();
        if (this.\u0130) {
            this.\u0151.drawImage(this.\u014c, 0, this.\u013a, this);
            graphics.drawImage(this.\u014d, 0, 0, this);
            return;
        }
        this.\u0151.setFont(this.\u0148);
        if (this.\u0136 > -1 && this.\u013d[this.\u0136].\u0195 > 0) {
            for (int k = 0; k < this.\u013d[this.\u0136].\u0195; ++k) {
                this.\u0151.setColor((k == this.\u0135) ? (this.\u0128 ? this.\u0154 : this.\u0162) : this.\u0163);
                if (this.\u0112 && !this.\u0128 && k == this.\u010e) {
                    this.\u0151.setColor(this.\u0111);
                }
                this.\u0151.drawString(this.\u013d[this.\u0136].\u0196[k].\u018a, this.\u013d[this.\u0136].\u0196[k].\u0187, ((this.\u0128 && k == this.\u0135) ? 1 : 0) + this.\u013d[this.\u0136].\u0196[k].\u0186);
            }
            if (this.\u0124 && !this.\u0130) {
                this.\u0151.setColor(this.\u015e);
                if (!this.\u013d[this.\u0136].\u018e) {
                    this.\u0151.fillRect(this.\u013d[this.\u0136].\u0194 - 3, this.\u013a, 1, this.\u013d[this.\u0136].\u0196[this.\u013d[this.\u0136].\u0195 - 1].\u0185 + 1);
                }
                else {
                    this.\u0151.fillRect(this.\u013d[this.\u0136].\u0193 + 2, this.\u013a, 1, this.\u013d[this.\u0136].\u0196[this.\u013d[this.\u0136].\u0195 - 1].\u0185 + 1);
                }
            }
            if (this.\u0123 > 0 && !this.\u0130 && this.\u010f) {
                this.\u0151.setColor((this.\u0134 > -1) ? (this.\u0127 ? this.\u0152 : this.\u015b) : this.\u015c);
                if (!this.\u011f) {
                    if (this.\u0123 == 1 || this.\u0123 == 3) {
                        if (!this.\u013d[this.\u0136].\u018e) {
                            this.\u0151.fillRect(this.\u013d[this.\u0136].\u0194 - 5, this.\u012d - 2, 5, 4);
                        }
                        else {
                            this.\u0151.fillRect(this.\u013d[this.\u0136].\u0193, this.\u012d - 2, 5, 4);
                        }
                    }
                    if (this.\u0123 >= 2) {
                        this.\u0151.fillRect(this.\u012c - 2, this.\u013a - 2, 5, 4);
                    }
                }
                else {
                    if (this.\u0123 == 1 || this.\u0123 == 3) {
                        final int[] array = { this.\u012d, this.\u012d - 4, this.\u012d + 4 };
                        final int[] array2 = { this.\u013d[this.\u0136].\u0194, this.\u013d[this.\u0136].\u0194 - 4, this.\u013d[this.\u0136].\u0194 - 4 };
                        final int[] array3 = { this.\u013d[this.\u0136].\u0193, this.\u013d[this.\u0136].\u0193 + 4, this.\u013d[this.\u0136].\u0193 + 4 };
                        if (!this.\u013d[this.\u0136].\u018e) {
                            this.\u0151.fillPolygon(array2, array, array2.length);
                        }
                        else {
                            this.\u0151.fillPolygon(array3, array, array3.length);
                        }
                    }
                    if (this.\u0123 >= 2) {
                        this.\u0151.drawLine(this.\u012c - 3, this.\u013a - 2, this.\u012c + 3, this.\u013a - 2);
                        this.\u0151.drawLine(this.\u012c - 2, this.\u013a - 1, this.\u012c + 2, this.\u013a - 1);
                        this.\u0151.drawLine(this.\u012c - 1, this.\u013a, this.\u012c + 1, this.\u013a);
                        this.\u0151.drawLine(this.\u012c, this.\u013a + 1, this.\u012c, this.\u013a + 1);
                    }
                }
            }
        }
        if (this.\u0135 == -1) {
            graphics.drawImage(this.\u014d, 0, 0, this);
            return;
        }
        final ryLWItem ryLWItem = this.\u013d[this.\u0136].\u0196[this.\u0135];
        if (this.\u012f && this.\u013d[this.\u0136].\u0196[this.\u0135].\u0182 > 0) {
            if (!this.\u013d[this.\u0136].\u018e) {
                this.\u0151.drawImage(this.\u014b, this.\u013d[this.\u0136].\u0194, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.y, this);
            }
            else {
                this.\u0151.drawImage(this.\u014b, this.\u013d[this.\u0136].\u0193 - ryLWItem.\u0180.width - 2, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.y, this);
            }
            this.\u0172();
            graphics.drawImage(this.\u014d, 0, 0, this);
            return;
        }
        if (!this.\u012f && this.\u0125 && this.\u013d[this.\u0136].\u0196[this.\u0135].\u0182 > 0) {
            for (int l = 0; l < ryLWItem.\u0182; ++l) {
                this.\u0151.setColor(this.\u015f);
                if (!this.\u013d[this.\u0136].\u018e) {
                    this.\u0151.drawString(this.\u012b, ryLWItem.\u0183[l].\u01ae, ryLWItem.\u0183[l].\u01af);
                }
                else {
                    this.\u0151.drawString(this.\u012b, ryLWItem.\u0183[l].\u01ae + this.\u0145.stringWidth(ryLWItem.\u0183[l].\u01b2), ryLWItem.\u0183[l].\u01af);
                }
                this.\u0151.setColor((l == this.\u0134) ? (this.\u0127 ? this.\u0153 : this.\u0160) : this.\u0161);
                if (this.\u0112 && !this.\u0127 && l == this.\u010d) {
                    this.\u0151.setColor(this.\u0110);
                }
                final int n2 = (this.\u0127 && l == this.\u0134) ? 1 : 0;
                if (!this.\u013d[this.\u0136].\u018e) {
                    this.\u0151.drawString(ryLWItem.\u0183[l].\u01b2, ryLWItem.\u0183[l].\u01ae + this.\u012a, n2 + ryLWItem.\u0183[l].\u01af);
                }
                else {
                    this.\u0151.drawString(ryLWItem.\u0183[l].\u01b2, ryLWItem.\u0183[l].\u01ae, n2 + ryLWItem.\u0183[l].\u01af);
                }
            }
        }
        graphics.drawImage(this.\u014d, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void \u0172() {
        if (this.\u011a) {
            this.\u0151.setColor(this.\u015d);
            this.\u0151.fillRect(0, this.\u011b.y, this.\u0143, 1);
            this.\u0151.drawImage(this.\u014a, this.\u011b.x, this.\u011b.y + 1, this);
        }
    }
    
    public void startupmenu(final boolean b) {
        if (this.\u0133 != null || this.\u0130 || this.\u0132 != null || this.\u012f) {
            this.stop();
        }
        this.\u0130 = false;
        this.\u012f = false;
        this.\u0135 = -1;
        (this.\u0133 = new ryLWRun(this, this.\u0158)).setMenu(this.\u013d[this.\u0136], b);
        this.\u0133.start();
    }
    
    public void startupitem() {
        if (this.\u0133 != null) {
            this.\u0126 = this.\u0135;
            return;
        }
        if (this.\u0132 != null) {
            this.\u0132 = null;
            this.\u012f = false;
            this.\u0125 = false;
        }
        if (this.\u0135 == -1) {
            this.\u012f = false;
            this.\u0125 = false;
            return;
        }
        if (this.\u013d[this.\u0136].\u0196[this.\u0135].\u0182 == 0) {
            return;
        }
        if (this.\u012f) {
            this.\u012f = false;
        }
        this.\u0125 = false;
        (this.\u0132 = new ryLWSRun(this, this.\u0157)).setMenu(this.\u013d[this.\u0136], this.\u013d[this.\u0136].\u0196[this.\u0135]);
        if (this.\u013d[this.\u0136].\u0196[this.\u0135].\u0182 == 0) {
            return;
        }
        if (!this.\u013d[this.\u0136].\u018e) {
            this.\u014b = this.createImage(this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.width + 4, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.height);
        }
        else {
            this.\u014b = this.createImage(this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.width + 2, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.height);
        }
        (this.\u014f = this.\u014b.getGraphics()).setColor(this.\u0166);
        this.\u014f.fillRect(0, 0, this.\u0143, this.\u0142);
        this.\u0132.start();
    }
    
    public void quit() {
        if (this.\u012f) {
            this.\u012f = false;
        }
        if (this.\u0132 == null) {
            this.\u012f = false;
            return;
        }
        this.stop();
        this.\u012f = false;
        this.\u0125 = false;
    }
    
    public void stop() {
        if (this.\u0132 != null) {
            this.\u0132 = null;
        }
        if (this.\u0133 != null) {
            try {
                if (this.\u013d[this.\u0136].\u0196[0].\u0182 > 0) {
                    final boolean b = false;
                    this.\u0126 = (b ? 1 : 0);
                    this.\u0135 = (b ? 1 : 0);
                }
            }
            catch (Exception ex) {}
            if (this.\u0126 > -1) {
                this.\u0133 = null;
                this.startupitem();
            }
            else {
                this.\u0133 = null;
            }
        }
    }
    
    private Color[] \u0171(final Color color, Color color2) {
        final Color color3 = color2;
        final Color[] array = new Color[this.\u0158.length];
        boolean b = true;
        boolean b2 = true;
        boolean b3 = true;
        final int n = (color.getRed() > color2.getRed()) ? (color.getRed() - color2.getRed()) : (color2.getRed() - color.getRed());
        final int n2 = (color.getGreen() > color2.getGreen()) ? (color.getGreen() - color2.getGreen()) : (color2.getGreen() - color.getGreen());
        final int n3 = (color.getBlue() > color2.getBlue()) ? (color.getBlue() - color2.getBlue()) : (color2.getBlue() - color.getBlue());
        final double n4 = n * 1.0 / (this.\u0158.length * 1.0);
        final double n5 = n2 * 1.0 / (this.\u0158.length * 1.0);
        final double n6 = n3 * 1.0 / (this.\u0158.length * 1.0);
        if (color2.getRed() > color.getRed()) {
            b = false;
        }
        if (color2.getGreen() > color.getGreen()) {
            b2 = false;
        }
        if (color2.getBlue() > color.getBlue()) {
            b3 = false;
        }
        final int n7 = (int)n4;
        final int n8 = (int)n5;
        final int n9 = (int)n6;
        for (int i = 0; i < this.\u0158.length; ++i) {
            int n10;
            if (b) {
                n10 = ((color2.getRed() + n7 > color.getRed()) ? color2.getRed() : (color2.getRed() + n7));
            }
            else {
                n10 = ((color2.getRed() - n7 < color.getRed()) ? color2.getRed() : (color2.getRed() - n7));
            }
            int n11;
            if (b2) {
                n11 = ((color2.getGreen() + n8 > color.getGreen()) ? color2.getGreen() : (color2.getGreen() + n8));
            }
            else {
                n11 = ((color2.getGreen() - n8 < color.getGreen()) ? color2.getGreen() : (color2.getGreen() - n8));
            }
            int n12;
            if (b3) {
                n12 = ((color2.getBlue() + n9 > color.getBlue()) ? color2.getBlue() : (color2.getBlue() + n9));
            }
            else {
                n12 = ((color2.getBlue() - n9 < color.getBlue()) ? color2.getBlue() : (color2.getBlue() - n9));
            }
            color2 = new Color(n10, n11, n12);
            array[i] = color2;
        }
        array[0] = color3;
        array[this.\u0158.length - 1] = color;
        return array;
    }
    
    private void \u0170() {
        final String property = System.getProperty("java.vendor");
        final String property2 = System.getProperty("java.version");
        this.\u013f = false;
        this.\u0140 = (property.startsWith("Microsoft") || property.startsWith("Sun Micro"));
        if (this.\u0140) {
            final float floatValue = Float.valueOf(property2.substring(0, 3));
            this.\u0140 = (floatValue >= 1.1f);
            if (property.startsWith("Sun Micro") && floatValue >= 1.2f) {
                this.\u013f = true;
            }
        }
        if (!this.\u0140) {
            this.\u0140 = property.startsWith("Netscape");
            if (this.\u0140) {
                if (property2.lastIndexOf(".") == property2.indexOf(".")) {
                    this.\u0140 = false;
                }
                else {
                    this.\u0140 = (Float.valueOf(property2.substring(2)) >= 1.5f);
                }
            }
        }
        final String parameter = this.getParameter("UseHandCursor");
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("yes")) {
                this.\u013e = true;
            }
            if (parameter.equalsIgnoreCase("no")) {
                this.\u013e = false;
            }
        }
        else {
            this.\u013e = true;
        }
    }
    
    private Font \u016f(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        String s2 = stringTokenizer.nextToken().trim();
        boolean b = false;
        if (s2.indexOf("|") > -1) {
            b = true;
            if (this.\u013f) {
                s2 = new ryFont().\u0102(s2);
            }
            else {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(s2, "|");
                while (stringTokenizer2.hasMoreTokens()) {
                    s2 = stringTokenizer2.nextToken();
                }
            }
        }
        if (s2 == "") {
            s2 = "Dialog";
        }
        final String trim = stringTokenizer.nextToken().toLowerCase().trim();
        int n = 0;
        if (trim.equals("plain")) {
            n = 0;
        }
        else if (trim.equals("bold")) {
            n = 1;
        }
        else if (trim.equals("italic")) {
            n = 2;
        }
        else if (trim.equals("bolditalic")) {
            n = 3;
        }
        int int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
        if (b) {
            int1 = (int)Math.rint(int1 * 1.34);
        }
        return new Font(s2, n, int1);
    }
    
    private void \u016e() {
        final String \u0177 = this.\u0177("NetscapeRedirectURL", null);
        final String \u01772 = this.\u0177("NetscapeRedirectTarget", "_self");
        if (\u0177 != null) {
            this.\u016c(\u0177, \u01772);
        }
    }
    
    private void \u016d(final boolean b) {
        if (!this.\u0140 || !this.\u013e) {
            return;
        }
        if (b) {
            this.setCursor(new Cursor(12));
        }
        else {
            this.setCursor(new Cursor(0));
        }
    }
    
    protected void \u016c(final String s, final String s2) {
        if (s == null || s.length() == 0) {
            return;
        }
        if (s.startsWith("$")) {
            return;
        }
        if (s.length() > 10 && s.substring(0, 10).equalsIgnoreCase("javascript")) {
            this.\u016b(s);
        }
        else {
            URL url;
            try {
                url = new URL(this.getDocumentBase(), s);
            }
            catch (Exception ex) {
                url = null;
            }
            if (url != null) {
                this.getAppletContext().showDocument(url, s2);
            }
        }
    }
    
    private void \u016b(String substring) {
        if (!this.\u010a) {
            return;
        }
        if (substring == null || substring == "$" || substring.startsWith("$")) {
            return;
        }
        final JSObject window = JSObject.getWindow((Applet)this);
        substring = substring.substring(substring.indexOf(":") + 1);
        window.eval(substring);
    }
    
    protected void \u016a(final String \u0119) {
        if (!this.\u011a) {
            return;
        }
        if (this.\u0119 == \u0119) {
            return;
        }
        this.\u0119 = \u0119;
        if (this.\u0131 != null) {
            this.\u0131.\u019f();
        }
        (this.\u0131 = new ryLWStatus(this, this.\u0156)).start();
    }
    
    protected void \u0169() {
        if (this.\u0131 != null) {
            this.\u0131 = null;
        }
    }
    
    public void showStatus(final String s) {
        try {
            JSObject.getWindow((Applet)this).eval("window.status=\"" + s + "\";");
        }
        catch (Exception ex) {
            this.getAppletContext().showStatus(s);
        }
    }
    
    public void selectItem(int \u0137, int n, int n2) {
        if (!this.\u010a) {
            return;
        }
        if (!this.\u0112) {
            return;
        }
        if (--\u0137 < 0) {
            this.\u0136 = -1;
            this.\u0135 = -1;
            this.\u0134 = -1;
            this.\u010e = -1;
            this.\u010d = -1;
            this.\u010f = false;
            this.repaint();
            return;
        }
        if (\u0137 != this.\u0136) {
            this.\u0136 = \u0137;
            if (this.\u013d[this.\u0136].\u0195 == 0) {
                this.\u0135 = -1;
                this.\u0134 = -1;
                this.\u010e = -1;
                this.\u010d = -1;
                this.repaint();
                return;
            }
            while (this.\u0133 != null) {
                try {
                    this.\u0133.\u019f();
                }
                catch (Exception ex) {}
            }
            this.startupmenu(false);
        }
        if (this.\u013d[\u0137].\u0195 == 0) {
            return;
        }
        if (--n < 0) {
            return;
        }
        if (n != this.\u0135) {
            final int \u0135 = this.\u0135;
            if (this.\u0135 > -1 || this.\u010e > -1) {
                this.\u0135 = -1;
                this.\u010e = -1;
                this.repaint();
            }
            this.\u0135 = n;
            this.\u0134 = -1;
            this.\u010d = -1;
            if (n2 - 1 <= -1 || this.\u013d[\u0137].\u0196[n].\u0182 <= n2 - 1) {
                this.\u010e = n;
                this.repaint();
                return;
            }
            this.\u010e = -1;
            while (this.\u0133 != null) {}
            this.startupitem();
        }
        if (this.\u013d[\u0137].\u0196[n].\u0182 == 0) {
            return;
        }
        if (--n2 < 0) {
            return;
        }
        if (n2 != this.\u010d) {
            this.\u0134 = n2;
            this.\u010d = n2;
            this.repaint(this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.x, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.y, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.width + 2, this.\u013d[this.\u0136].\u0196[this.\u0135].\u0180.height + 2);
        }
    }
}
