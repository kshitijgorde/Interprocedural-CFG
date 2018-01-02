import netscape.javascript.JSObject;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
import java.util.Date;
import java.io.DataInputStream;
import java.util.Vector;
import java.math.BigInteger;
import java.util.StringTokenizer;
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

public class ryHeadliner extends Applet
{
    private Color \u013a;
    private Color \u0139;
    private Color \u0138;
    private Color \u0137;
    private Color \u0136;
    private Color \u0135;
    private Color \u0134;
    private Color \u0133;
    private Color \u0132;
    private Color \u0131;
    private Color \u0130;
    private Color \u012f;
    private Color \u012e;
    private Color \u012d;
    private Graphics \u012c;
    private Image \u012b;
    private int \u012a;
    int \u0129;
    private Font \u0128;
    private Font \u0127;
    private FontMetrics \u0126;
    private FontMetrics \u0125;
    private String \u0124;
    String[] \u0123;
    private String[] \u0122;
    private URL[] \u0121;
    private String[] \u0120;
    private String \u011f;
    private boolean \u011e;
    int \u011d;
    private int \u011c;
    int \u011b;
    int \u011a;
    int \u0119;
    int \u0118;
    private boolean \u0117;
    private ryHeadThread \u0116;
    private String \u0115;
    String \u0114;
    private Color \u0113;
    private String \u0112;
    boolean \u0111;
    long \u0110;
    int \u010f;
    private String \u010e;
    int \u010d;
    int \u010c;
    private boolean \u010b;
    private boolean \u010a;
    private boolean \u0109;
    private boolean \u0108;
    private boolean \u0107;
    private boolean \u0106;
    private boolean \u0105;
    private Font \u0104;
    private boolean \u0103;
    
    public ryHeadliner() {
        this.\u0139 = new Color(255, 255, 255);
        this.\u0137 = new Color(0, 255, 0);
        this.\u0135 = new Color(0, 0, 0);
        this.\u0133 = new Color(0, 0, 0);
        this.\u0131 = new Color(0, 0, 255);
        this.\u012f = new Color(255, 0, 0);
        this.\u011d = 0;
        this.\u011c = 0;
        this.\u011b = 1000;
        this.\u011a = 10;
        this.\u0115 = "";
        this.\u0114 = "";
        this.\u010e = "";
        this.\u0107 = false;
        this.\u0106 = false;
        this.\u0103 = false;
    }
    
    private void \u0147() {
        final String s = "Headliner © 1998 Cool Focus [www.coolfocus.com]";
        final String s2 = "Headliner (c) 1998 Cool Focus [www.coolfocus.com]";
        if (this.getParameter("Copyright") == null || (!this.getParameter("Copyright").equals(s) && !this.getParameter("Copyright").equals(s2))) {
            throw new SecurityException(" Copyright parameter missing or incorrect ");
        }
    }
    
    private void \u0146() {
        final String lowerCase = this.getDocumentBase().toString().toLowerCase();
        boolean b = false;
        if (lowerCase != null && lowerCase.startsWith("file:") && lowerCase.indexOf("headliner/docs/") >= 0) {
            this.\u0107 = true;
            return;
        }
        final String lowerCase2 = this.getDocumentBase().getHost().toLowerCase();
        if (lowerCase2.indexOf("coolfocus.net") > -1 || lowerCase2.indexOf("java-menus.com") > -1) {
            this.\u0103 = true;
            this.\u0107 = true;
            return;
        }
        final String parameter = this.getParameter("Base");
        if (parameter == null) {
            this.\u0107 = false;
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
            this.\u0107 = false;
            return;
        }
        this.\u0107 = this.\u0145(parameter);
    }
    
    private boolean \u0145(final String s) {
        final String parameter = this.getParameter("Key");
        if (parameter == null) {
            return false;
        }
        if (!this.\u010b) {
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
        return this.\u0144(s2, "38778007", "33133613").equals(s);
    }
    
    private String \u0144(final String s, final String s2, final String s3) {
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
    
    private int \u0143(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return Integer.valueOf(parameter, 16);
        }
        return color.getRGB();
    }
    
    private String \u0142(final String s, final String s2) {
        String parameter = this.getParameter(s);
        if (parameter == null) {
            parameter = s2;
        }
        return parameter;
    }
    
    public String getAppletInfo() {
        if (!this.\u0107) {
            return "\n\nUNREGISTERED Headliner v1.72a by Rob Young\nCopyright © 1998-2003 Cool Focus [www.coolfocus.com]\n\n";
        }
        return "\n\nHeadliner v1.72a by Rob Young\nCopyright © 1998-2003 Cool Focus [www.coolfocus.com]\n\n";
    }
    
    public void init() {
        this.\u013c();
        this.\u0146();
        if (!this.\u0103) {
            this.\u0147();
        }
        if (!this.\u0103) {
            System.out.println(this.getAppletInfo());
        }
        if (!this.\u0107) {
            this.\u0106 = true;
        }
        this.\u0105 = (this.\u0107 || this.getDocumentBase().toString().toLowerCase().startsWith("file:"));
        this.\u0104 = this.\u013b("Tahoma|MS Sans Serif|Dialog,0,9");
        this.\u012a = this.size().width;
        this.\u0129 = this.size().height;
        this.\u012b = this.createImage(this.\u012a, this.\u0129);
        this.\u012c = this.\u012b.getGraphics();
        this.\u013a = new Color(this.\u0143("BgColor", this.\u0139));
        this.\u0138 = new Color(this.\u0143("HeadingTextColor", this.\u0137));
        this.\u0136 = new Color(this.\u0143("BorderColor", this.\u0135));
        this.\u0134 = new Color(this.\u0143("TextColor", this.\u0133));
        this.\u0132 = new Color(this.\u0143("FocusTextColor", this.\u0131));
        this.\u0130 = new Color(this.\u0143("PressTextColor", this.\u012f));
        this.\u012e = new Color(this.\u0143("FillColor", this.\u013a));
        this.\u012d = new Color(this.\u0143("HeadingFillColor", this.\u0136));
        this.setBackground(this.\u013a);
        this.\u0113 = this.\u0134;
    }
    
    public void start() {
        this.\u011f = this.\u0142("DefaultTarget", "_top");
        final String \u0142 = this.\u0142("Testmode", "no");
        if (\u0142.equalsIgnoreCase("yes")) {
            this.\u011e = true;
        }
        if (\u0142.equalsIgnoreCase("no")) {
            this.\u011e = false;
        }
        if (!this.\u0107) {
            this.\u011e = false;
        }
        String parameter = this.getParameter("EntryFont");
        if (parameter == null) {
            parameter = "Dialog,plain,12";
        }
        this.\u0128 = this.\u013b(parameter);
        this.\u0126 = this.getFontMetrics(this.\u0128);
        final String parameter2 = this.getParameter("Satellites");
        if (parameter2 != null) {
            if (parameter2.equalsIgnoreCase("yes")) {
                this.\u0108 = true;
            }
            if (parameter2.equalsIgnoreCase("no")) {
                this.\u0108 = false;
            }
        }
        else {
            this.\u0108 = false;
        }
        final String \u01422 = this.\u0142("UseHeading", "yes");
        if (\u01422.equalsIgnoreCase("yes")) {
            this.\u0117 = true;
        }
        if (\u01422.equalsIgnoreCase("no")) {
            this.\u0117 = false;
        }
        if (this.\u0117) {
            String parameter3 = this.getParameter("HeadingFont");
            if (parameter3 == null) {
                parameter3 = "Dialog,bold,12";
            }
            this.\u0127 = this.\u013b(parameter3);
            this.\u0125 = this.getFontMetrics(this.\u0127);
            this.\u0124 = this.\u0142("Heading", "Headlines:");
        }
        final String \u01423 = this.\u0142("Hold", "1");
        try {
            this.\u011b = Integer.parseInt(\u01423) * 1000;
        }
        catch (Exception ex) {
            this.\u011b = 1000;
        }
        final String \u01424 = this.\u0142("CPS", "10");
        try {
            this.\u011a = 1000 / Integer.parseInt(\u01424);
        }
        catch (Exception ex2) {
            this.\u011a = 10;
        }
        this.\u0112 = this.getParameter("ScriptFile");
        if (this.\u0112 == null || this.\u0112.equals("")) {
            this.\u0111 = false;
        }
        else {
            this.\u0111 = true;
        }
        if (!this.\u0107) {
            this.repaint();
        }
        else {
            this.\u0141();
        }
    }
    
    private void \u0141() {
        if (this.\u0111) {
            final String \u0142 = this.\u0142("Update", "10");
            try {
                this.\u010f = Integer.parseInt(\u0142);
            }
            catch (Exception ex) {
                this.\u010f = 10;
            }
        }
        if (this.\u0111) {
            this.\u0111 = this.readFile();
        }
        if (!this.\u0111) {
            this.\u0140();
        }
        if (this.\u0111 && this.\u010f < 1) {
            this.\u0111 = false;
        }
        if (this.\u0116 != null) {
            this.\u0116 = null;
        }
        (this.\u0116 = new ryHeadThread(this)).start();
    }
    
    public void stop() {
        if (this.\u0116 != null) {
            this.\u0116.stop();
            this.\u0116 = null;
        }
    }
    
    private void \u0140() {
        this.\u0114 = "Reading local news...";
        this.repaint(this.\u0119, 0, this.\u010d, this.\u0129);
        int n = 0;
        this.\u011d = 0;
        if (this.getParameter("Entry1") != null) {
            while (true) {
                final String parameter = this.getParameter("Entry" + (n + 1));
                if (parameter == null) {
                    break;
                }
                final int stringWidth = this.\u0126.stringWidth(parameter);
                if (stringWidth > this.\u011c) {
                    this.\u011c = stringWidth;
                }
                ++this.\u011d;
                ++n;
            }
        }
        if (this.\u011d == 0) {
            this.\u013f();
            return;
        }
        this.\u0123 = new String[this.\u011d];
        this.\u0121 = new URL[this.\u011d];
        this.\u0120 = new String[this.\u011d];
        this.\u0122 = new String[this.\u011d];
        this.\u0115 = "Headliner: " + this.\u011d + " headlines";
        this.\u011c += 4;
        if (this.\u0117) {
            this.\u0119 = this.\u0125.stringWidth(this.\u0124) + 12;
        }
        else {
            this.\u0119 = 3;
        }
        this.\u013e();
        for (int i = 0; i < this.\u011d; ++i) {
            final int n2 = i + 1;
            this.\u0123[i] = this.getParameter("Entry" + n2);
            this.\u0122[i] = this.\u0142("Target" + n2, this.\u011f);
            final String parameter2 = this.getParameter("URL" + n2);
            if (parameter2 != null && !parameter2.equals("") && !parameter2.startsWith("$") && !parameter2.equals("$")) {
                this.\u0120[i] = parameter2;
                try {
                    this.\u0121[i] = new URL(this.getDocumentBase(), parameter2);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public final boolean readFile() {
        this.\u0114 = "Reading remote news...";
        this.repaint(this.\u0119, 0, this.\u010d, this.\u0129);
        final Vector vector = new Vector<String>(0, 1);
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), this.\u0112).openStream());
            boolean b = true;
            while (b) {
                final String line = dataInputStream.readLine();
                if (line == null || line.equals("") || line.equals(" ") || line.equals("  ")) {
                    b = false;
                }
                else {
                    vector.addElement(line);
                }
            }
            dataInputStream.close();
            this.\u0110 = new Long(new Date().getTime());
            final Date date = new Date(this.\u0110);
            String s;
            if (date.getHours() > 11) {
                s = "pm";
            }
            else {
                s = "am";
            }
            String s2;
            if (date.getHours() > 12) {
                s2 = Integer.toString(date.getHours() - 12);
            }
            else {
                s2 = Integer.toString(date.getHours());
            }
            String s3;
            if (date.getMinutes() < 10) {
                s3 = "0" + Integer.toString(date.getMinutes());
            }
            else {
                s3 = Integer.toString(date.getMinutes());
            }
            this.\u010e = "" + s2 + ":" + s3 + s;
        }
        catch (Exception ex) {
            this.\u0114 = "Remote news not available";
            this.repaint(this.\u0119, 0, this.\u010d, this.\u0129);
            return false;
        }
        this.\u011d = vector.size();
        this.\u0123 = new String[this.\u011d];
        this.\u0121 = new URL[this.\u011d];
        this.\u0120 = new String[this.\u011d];
        this.\u0122 = new String[this.\u011d];
        this.\u011c = 0;
        for (int i = 0; i < this.\u011d; ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(vector.elementAt(i), "|");
            try {
                this.\u0123[i] = stringTokenizer.nextToken();
            }
            catch (Exception ex2) {
                this.\u0123[i] = "";
            }
            final int stringWidth = this.\u0126.stringWidth(this.\u0123[i]);
            if (stringWidth > this.\u011c) {
                this.\u011c = stringWidth;
            }
            try {
                this.\u0120[i] = stringTokenizer.nextToken();
            }
            catch (Exception ex3) {
                this.\u0120[i] = "";
            }
            if (this.\u0120[i] != "") {
                try {
                    this.\u0121[i] = new URL(this.getDocumentBase(), this.\u0120[i]);
                }
                catch (Exception ex4) {}
            }
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    this.\u0122[i] = stringTokenizer.nextToken();
                    if (this.\u0122[i] == null) {
                        this.\u0122[i] = this.\u011f;
                    }
                }
                catch (Exception ex5) {
                    this.\u0122[i] = this.\u011f;
                }
            }
            else {
                this.\u0122[i] = this.\u011f;
            }
        }
        this.\u0115 = "Headliner: " + this.\u011d + " headlines, updated at " + this.\u010e;
        this.\u011c += 4;
        if (this.\u0117) {
            this.\u0119 = this.\u0125.stringWidth(this.\u0124) + 12;
        }
        else {
            this.\u0119 = 3;
        }
        this.\u013e();
        return true;
    }
    
    public void destroy() {
        if (this.\u0116 != null) {
            this.\u0116 = null;
        }
        System.gc();
    }
    
    private void \u013f() {
        (this.\u0123 = new String[1])[0] = "No headlines available";
        this.\u011c = this.\u0126.stringWidth(this.\u0123[0]) + 4;
        if (this.\u0117) {
            this.\u0119 = this.\u0125.stringWidth(this.\u0124) + 12;
        }
        else {
            this.\u0119 = 3;
        }
        this.\u013e();
        this.repaint(this.\u0119, 0, this.\u010d, this.\u0129);
    }
    
    private void \u013e() {
        if (this.\u0117) {
            this.\u010c = this.\u0126.getHeight() + this.\u0126.getDescent() + 4;
            this.\u010d = this.\u0119 + 4 + this.\u011c;
        }
        else {
            this.\u010c = this.\u0126.getHeight() + this.\u0126.getDescent() + 4;
            this.\u010d = this.\u011c + 5;
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.\u011e && this.\u0107) {
            this.showStatus(" Height: " + this.\u010c + ",  Minimum Width: " + this.\u010d);
        }
        if (!this.\u0107) {
            this.showStatus("UNREGISTERED Headliner by Cool Focus [www.coolfocus.com]");
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (!this.\u0116.\u014c && n >= this.\u0119 && n <= this.\u010d) {
            if (this.\u0105 && this.\u0108) {
                try {
                    ryRadar.transmitName(this.\u0123[this.\u0118]);
                }
                catch (Exception ex) {}
            }
            this.\u0113 = this.\u0132;
            if (this.\u010b && this.\u0109) {
                this.setCursor(new Cursor(12));
            }
            this.repaint(this.\u0119, 0, this.\u010d, this.\u0129);
            this.\u0116.\u0150(true);
            if (this.\u0107 && !this.\u011e) {
                this.showStatus(this.\u0115);
            }
            else if (!this.\u0105) {
                this.showStatus("UNREGISTERED Headliner by Cool Focus [www.coolfocus.com]");
            }
        }
        if (this.\u0116.\u014c && (n < this.\u0119 || n >= this.\u010d || n2 < 0 || n2 > this.\u0129)) {
            if (this.\u0105 && this.\u0108) {
                try {
                    ryRadar.transmitName("-^-");
                }
                catch (Exception ex2) {}
            }
            this.\u0113 = this.\u0134;
            if (this.\u010b && this.\u0109) {
                this.setCursor(new Cursor(0));
            }
            this.repaint(this.\u0119, 0, this.\u010d, this.\u0129);
            this.\u0116.\u0150(false);
            if (this.\u0107 && !this.\u011e) {
                this.showStatus("");
            }
            else if (!this.\u0105) {
                this.showStatus("UNREGISTERED Headliner by Cool Focus [www.coolfocus.com]");
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.\u0116.\u014c && n >= this.\u0119 && n <= this.\u010d) {
            this.\u0113 = this.\u0130;
            this.repaint(this.\u0119, 0, this.\u010d, this.\u0129);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.\u0116.\u014c && n >= this.\u0119 && n <= this.\u010d) {
            this.\u0113 = this.\u0132;
            this.repaint(this.\u0119, 0, this.\u010d, this.\u0129);
            if (this.\u0120[this.\u0118] != null) {
                if (this.\u0120[this.\u0118].length() > 10 && this.\u0120[this.\u0118].substring(0, 10).equalsIgnoreCase("javascript")) {
                    this.\u013d(this.\u0120[this.\u0118]);
                }
                else if (this.\u0121[this.\u0118] != null) {
                    this.getAppletContext().showDocument(this.\u0121[this.\u0118], this.\u0122[this.\u0118]);
                }
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (n < this.\u0119 || n > this.\u010d || n2 < 0 || n2 > this.\u0129) {
            this.\u0113 = this.\u0134;
            this.repaint(this.\u0119, 0, this.\u010d, this.\u0129);
            this.\u0116.\u0150(false);
            if (this.\u010b && this.\u0109) {
                this.setCursor(new Cursor(0));
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.\u0105 && this.\u0108) {
            try {
                ryRadar.transmitName("-^-");
            }
            catch (Exception ex) {}
        }
        this.\u0113 = this.\u0134;
        this.repaint(this.\u0119, 0, this.\u010d, this.\u0129);
        this.\u0116.\u0150(false);
        if (this.\u010b && this.\u0109) {
            this.setCursor(new Cursor(0));
        }
        this.showStatus("");
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u0106) {
            this.\u012c.setColor(Color.black);
            this.\u012c.fillRect(0, 0, this.\u012a, this.\u0129);
            this.\u012c.setColor(Color.green);
            final Font \u0105 = this.\u0104;
            this.\u012c.setFont(\u0105);
            final FontMetrics fontMetrics = this.getFontMetrics(\u0105);
            final String s = "UNREGISTERED";
            final String s2 = "Headliner";
            final String s3 = "by Cool Focus";
            final String s4 = "www.coolfocus.com";
            this.\u012c.drawString(s, (this.\u012a - fontMetrics.stringWidth(s)) / 2, fontMetrics.getAscent() + (this.\u0129 - fontMetrics.getHeight()) / 2);
            graphics.drawImage(this.\u012b, 0, 0, this);
            Thread.currentThread();
            try {
                Thread.sleep(1200L);
            }
            catch (InterruptedException ex) {}
            this.\u012c.setColor(Color.black);
            this.\u012c.fillRect(0, 0, this.\u012a, this.\u0129);
            this.\u012c.setColor(Color.green);
            this.\u012c.drawString(s2, (this.\u012a - fontMetrics.stringWidth(s2)) / 2, fontMetrics.getAscent() + (this.\u0129 - fontMetrics.getHeight()) / 2);
            graphics.drawImage(this.\u012b, 0, 0, this);
            try {
                Thread.sleep(1200L);
            }
            catch (InterruptedException ex2) {}
            this.\u012c.setColor(Color.black);
            this.\u012c.fillRect(0, 0, this.\u012a, this.\u0129);
            this.\u012c.setColor(Color.green);
            this.\u012c.drawString(s3, (this.\u012a - fontMetrics.stringWidth(s3)) / 2, fontMetrics.getAscent() + (this.\u0129 - fontMetrics.getHeight()) / 2);
            graphics.drawImage(this.\u012b, 0, 0, this);
            try {
                Thread.sleep(1200L);
            }
            catch (InterruptedException ex3) {}
            this.\u012c.setColor(Color.black);
            this.\u012c.fillRect(0, 0, this.\u012a, this.\u0129);
            this.\u012c.setColor(Color.green);
            this.\u012c.drawString(s4, (this.\u012a - fontMetrics.stringWidth(s4)) / 2, fontMetrics.getAscent() + (this.\u0129 - fontMetrics.getHeight()) / 2);
            graphics.drawImage(this.\u012b, 0, 0, this);
            try {
                Thread.sleep(1200L);
            }
            catch (InterruptedException ex4) {}
            this.\u0106 = false;
            this.\u0141();
        }
        this.\u012c.setColor(this.\u013a);
        this.\u012c.fillRect(0, 0, this.\u012a, this.\u0129);
        this.\u012c.setColor(this.\u012e);
        this.\u012c.fillRect(0, 0, this.\u010d, this.\u0129);
        if (this.\u0117) {
            this.\u012c.setColor(this.\u012d);
            this.\u012c.fillRect(0, 0, this.\u0119 - 2, this.\u0129);
            this.\u012c.setColor(this.\u0136);
            this.\u012c.drawRect(this.\u0119 - 2, 0, this.\u010d - this.\u0119 + 1, this.\u0129 - 1);
            this.\u012c.setFont(this.\u0127);
            this.\u012c.setColor(this.\u0138);
            this.\u012c.drawString(this.\u0124, (this.\u0119 - 2 - this.\u0125.stringWidth(this.\u0124)) / 2, this.\u0125.getAscent() + (this.\u0129 - this.\u0125.getHeight()) / 2);
            this.\u012c.setFont(this.\u0128);
            this.\u012c.setColor(this.\u0113);
            this.\u012c.drawString(this.\u0114, this.\u0119 + 2, this.\u0126.getAscent() + (this.\u0129 - this.\u0126.getHeight()) / 2);
        }
        else {
            this.\u012c.setColor(this.\u0136);
            this.\u012c.drawRect(0, 0, this.\u010d - 1, this.\u0129 - 1);
            this.\u012c.setFont(this.\u0128);
            this.\u012c.setColor(this.\u0113);
            this.\u012c.drawString(this.\u0114, this.\u0119, this.\u0126.getAscent() + (this.\u0129 - this.\u0126.getHeight()) / 2);
        }
        graphics.drawImage(this.\u012b, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void \u013d(String substring) {
        if (!this.\u0105) {
            return;
        }
        if (substring == null || substring == "$" || substring.startsWith("$")) {
            return;
        }
        final JSObject window = JSObject.getWindow((Applet)this);
        substring = substring.substring(substring.indexOf(":") + 1);
        window.eval(substring);
    }
    
    private void \u013c() {
        final String property = System.getProperty("java.vendor");
        final String property2 = System.getProperty("java.version");
        this.\u010a = false;
        this.\u010b = (property.startsWith("Microsoft") || property.startsWith("Sun Micro"));
        if (this.\u010b) {
            final float floatValue = Float.valueOf(property2.substring(0, 3));
            this.\u010b = (floatValue >= 1.1f);
            if (property.startsWith("Sun Micro") && floatValue >= 1.2f) {
                this.\u010a = true;
            }
        }
        if (!this.\u010b) {
            this.\u010b = property.startsWith("Netscape");
            if (this.\u010b) {
                if (property2.lastIndexOf(".") == property2.indexOf(".")) {
                    this.\u010b = false;
                }
                else {
                    this.\u010b = (Float.valueOf(property2.substring(2)) >= 1.5f);
                }
            }
        }
        final String parameter = this.getParameter("UseHandCursor");
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("yes")) {
                this.\u0109 = true;
            }
            if (parameter.equalsIgnoreCase("no")) {
                this.\u0109 = false;
            }
        }
        else {
            this.\u0109 = true;
        }
    }
    
    private Font \u013b(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        String s2 = stringTokenizer.nextToken().trim();
        boolean b = false;
        if (s2.indexOf("|") > -1) {
            b = true;
            if (this.\u010a) {
                s2 = new ryFont().getName(s2);
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
    
    public void showStatus(final String s) {
        try {
            JSObject.getWindow((Applet)this).eval("window.status=\"" + s + "\";");
        }
        catch (Exception ex) {
            this.getAppletContext().showStatus(s);
        }
    }
}
