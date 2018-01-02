import java.awt.Event;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.io.DataInputStream;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.util.Vector;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NewsMaker extends Applet implements Runnable
{
    Thread News;
    boolean pfK;
    boolean pfN;
    boolean FreeVersion;
    String hostName;
    boolean fromDisk;
    boolean FirstPass;
    int Dwell;
    int Speed;
    boolean doDwell;
    boolean first;
    AudioClip A;
    String sFile;
    Vector v_Line;
    int v_current;
    int v_lineCount;
    Vector v_URL;
    Vector v_Targ;
    Image i_Buff;
    Image i_Back;
    String i_File;
    boolean m_Good;
    int m_Y;
    int leftMarg;
    int rightMarg;
    int topMarg;
    int botMarg;
    int sWide;
    int sHigh;
    int scrollPosY;
    Color textColor;
    Color linkColor;
    Color headColor;
    Color hotColor;
    Color backColor;
    Font theFont;
    int fontHigh;
    int fontSize;
    String fontName;
    FontMetrics FM;
    String howAlign;
    boolean HeadLines;
    int URLcheck;
    int aWide;
    int aHigh;
    Vector Script;
    String file;
    int oldScrollY;
    
    public void init() {
        this.aWide = this.size().width;
        this.aHigh = this.size().height;
        this.DoC();
        final String parameter = this.getParameter("SoundFile");
        if (parameter != null) {
            this.sFile = parameter;
            this.A = this.getAudioClip(this.getCodeBase(), this.sFile);
        }
        final String parameter2 = this.getParameter("Speed");
        if (parameter2 != null) {
            this.Speed = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("HeadLines");
        if (parameter3 != null && parameter3.equalsIgnoreCase("YES")) {
            this.HeadLines = true;
        }
        final String parameter4 = this.getParameter("Justification");
        if (parameter4 != null) {
            if (parameter4.equalsIgnoreCase("left")) {
                this.howAlign = "left";
            }
            else if (parameter4.equalsIgnoreCase("center")) {
                this.howAlign = "center";
            }
            else if (parameter4.equalsIgnoreCase("right")) {
                this.howAlign = "right";
            }
            else {
                this.howAlign = "left";
            }
        }
        final String parameter5 = this.getParameter("FontSize");
        if (parameter5 != null) {
            this.fontSize = Integer.parseInt(parameter5);
        }
        final String parameter6 = this.getParameter("FontName");
        if (parameter6 != null) {
            this.fontName = parameter6;
        }
        final String parameter7 = this.getParameter("FontStyle");
        if (parameter7 != null) {
            if (parameter7.equalsIgnoreCase("PLAIN")) {
                this.theFont = new Font(this.fontName, 0, this.fontSize);
            }
            else if (parameter7.equalsIgnoreCase("BOLD")) {
                this.theFont = new Font(this.fontName, 1, this.fontSize);
            }
            else if (parameter7.equalsIgnoreCase("ITALIC")) {
                this.theFont = new Font(this.fontName, 2, this.fontSize);
            }
            else if (parameter7.equalsIgnoreCase("BOLDITALIC")) {
                this.theFont = new Font(this.fontName, 3, this.fontSize);
            }
            else {
                this.theFont = new Font(this.fontName, 0, this.fontSize);
            }
        }
        else {
            this.theFont = new Font(this.fontName, 0, this.fontSize);
        }
        this.FM = this.getFontMetrics(this.theFont);
        this.fontHigh = this.FM.getMaxAscent() + this.FM.getMaxDescent() + this.FM.getLeading();
        this.scrollPosY = this.fontHigh;
        final String parameter8 = this.getParameter("TextColor");
        if (parameter8 != null) {
            this.textColor = this.parseC(parameter8, this.textColor);
        }
        final String parameter9 = this.getParameter("TextHotColor");
        if (parameter9 != null) {
            this.linkColor = this.parseC(parameter9, this.linkColor);
        }
        final String parameter10 = this.getParameter("HeadLineColor");
        if (parameter10 != null) {
            this.headColor = this.parseC(parameter10, this.headColor);
        }
        final String parameter11 = this.getParameter("HeadLineHotColor");
        if (parameter11 != null) {
            this.hotColor = this.parseC(parameter11, this.hotColor);
        }
        final String parameter12 = this.getParameter("LeftMargin");
        if (parameter12 != null) {
            this.leftMarg = Integer.parseInt(parameter12);
        }
        final String parameter13 = this.getParameter("RightMargin");
        if (parameter13 != null) {
            this.rightMarg = Integer.parseInt(parameter13);
        }
        final String parameter14 = this.getParameter("TopMargin");
        if (parameter14 != null) {
            this.topMarg = Integer.parseInt(parameter14);
        }
        final String parameter15 = this.getParameter("BottomMargin");
        if (parameter15 != null) {
            this.botMarg = Integer.parseInt(parameter15);
        }
        this.sWide = this.aWide - this.leftMarg - this.rightMarg;
        this.sHigh = this.aHigh - this.topMarg - this.botMarg;
        final String parameter16 = this.getParameter("Pause");
        if (parameter16 != null) {
            this.Dwell = Integer.parseInt(parameter16);
        }
        final String parameter17 = this.getParameter("BackImage");
        if (parameter17 != null) {
            this.i_File = parameter17;
        }
        final String parameter18 = this.getParameter("BackgroundColor");
        if (parameter18 != null) {
            this.backColor = this.parseC(parameter18, this.backColor);
        }
        if (this.i_File != null) {
            this.prepareImage(this.i_Back = this.getImage(this.getDocumentBase(), this.i_File), this);
        }
        if (this.FreeVersion && !this.fromDisk) {
            this.doParse("###http://www.microticker.com");
            this.doParse("This is NewsMaker by MicroTicker.com... Free for non-profit, non-commercial use. Click here for more information.");
        }
        if (!this.pfN) {
            this.doParse("###http://www.codebrain.com");
            this.doParse("NOTICE WRONG OR MISSING NOTICE WRONG OR MISSING NOTICE WRONG OR MISSING NOTICE WRONG OR MISSING NOTICE WRONG OR MISSING NOTICE WRONG OR MISSING");
        }
        if (!this.pfK) {
            this.doParse("###");
            this.doParse("KEYCODE WRONG OR MISSING KEYCODE WRONG OR MISSING KEYCODE WRONG OR MISSING KEYCODE WRONG OR MISSING KEYCODE WRONG OR MISSING KEYCODE WRONG OR MISSING");
        }
        this.file = "NewsMaker.txt";
        final String parameter19 = this.getParameter("Script");
        if (parameter19 != null) {
            this.file = parameter19;
        }
        if (this.Script.isEmpty()) {
            this.getScript();
        }
        for (int i = 0; i < this.Script.size(); ++i) {
            String s = this.Script.elementAt(i).trim();
            if (s.length() < 3 && s.length() > 0) {
                s += "^^^^^";
            }
            if (s.length() > 0) {
                this.doParse(s);
            }
        }
    }
    
    private void doParse(String s) {
        final int n = this.aWide - this.rightMarg - this.leftMarg;
        boolean b = true;
        String string = "";
        String s2 = "";
        String string2 = "";
        int n2 = 0;
        if (s.substring(0, 3).equals("###")) {
            s = s.substring(3, s.length());
            s = s.trim();
            if (s.length() < 3) {
                s = "@@@";
            }
            if (s.substring(0, 3).equals("@@@")) {
                s = "@@@";
            }
            if (s.substring(s.length() - 3, s.length()).equals("@@@")) {
                s = s.substring(0, s.length() - 3);
            }
            String nextToken;
            String nextToken2;
            if (s.indexOf("@@@") > 0 && s.length() > s.indexOf("@@@") + 3) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, "@@@");
                nextToken = stringTokenizer.nextToken();
                nextToken2 = stringTokenizer.nextToken();
            }
            else {
                nextToken = s;
                if (s.equals("") || s.equals("@@@")) {
                    nextToken = null;
                }
                nextToken2 = null;
            }
            this.v_URL.addElement(nextToken);
            this.v_Targ.addElement(nextToken2);
            return;
        }
        if (s.equalsIgnoreCase("<linebreak>")) {
            s = "|    ";
            final int n3 = this.v_lineCount - 1;
            if (n3 > -1) {
                this.v_Line.setElementAt((String)this.v_Line.elementAt(n3) + s, n3);
            }
            return;
        }
        if (this.HeadLines) {
            s = "<b>" + s;
        }
        while (b) {
            if (n2 > s.length() - 2) {
                b = false;
            }
            string += s.substring(n2, n2 + 1);
            ++n2;
            if (this.FM.stringWidth(string) >= n) {
                boolean b2 = false;
                for (int i = string.length() - 1; i > 0; --i) {
                    if (string.substring(i, i + 1).equals(" ")) {
                        string2 += (string.substring(0, i) + "|").trim();
                        string = "";
                        s = s.substring(i, s.length());
                        if (this.FM.stringWidth(s) < n) {
                            s2 = s.trim();
                        }
                        n2 = 0;
                        b2 = true;
                    }
                    if (b2) {
                        break;
                    }
                }
            }
            else {
                s2 = s.trim();
            }
        }
        this.v_Line.addElement(string2 + s2);
        ++this.v_lineCount;
    }
    
    private void getScript() {
        try {
            String line;
            while ((line = new DataInputStream(new URL(this.getCodeBase(), this.file).openConnection().getInputStream()).readLine()) != null) {
                this.Script.addElement(line.trim());
            }
        }
        catch (Exception ex) {
            this.Script.removeAllElements();
            this.Script.addElement("Could not get script...");
        }
    }
    
    URL getURL(final String s) {
        URL url = null;
        try {
            if (s.indexOf("http://") >= 0 || s.indexOf("https://") >= 0) {
                url = new URL(s);
            }
            else {
                url = new URL(this.getDocumentBase(), s);
            }
        }
        catch (MalformedURLException ex) {}
        return url;
    }
    
    private Color parseC(String trim, final Color color) {
        Color color2;
        try {
            trim = trim.replace('#', ' ').trim();
            color2 = new Color(Integer.valueOf(trim.substring(0, 2), 16), Integer.valueOf(trim.substring(2, 4), 16), Integer.valueOf(trim.substring(4, 6), 16));
        }
        catch (Exception ex) {
            color2 = color;
        }
        return color2;
    }
    
    public void render() {
        if (this.i_Buff != null) {
            final Graphics graphics = this.i_Buff.getGraphics();
            graphics.setColor(this.backColor);
            graphics.fillRect(0, 0, this.aWide, this.aHigh);
            if (this.i_Back != null) {
                graphics.drawImage(this.i_Back, 0, 0, this);
            }
            graphics.clipRect(this.leftMarg, this.topMarg, this.sWide, this.sHigh);
            graphics.translate(0, this.scrollPosY);
            graphics.setColor(this.textColor);
            graphics.setFont(this.theFont);
            int topMarg = this.topMarg;
            this.doDwell = false;
            boolean b = false;
            this.v_current = -1;
            Color color = graphics.getColor();
            while (topMarg + this.scrollPosY < this.topMarg + this.sHigh + this.fontHigh + this.fontHigh) {
                for (int i = 0; i < this.v_lineCount; ++i) {
                    if (this.m_Good) {
                        final String s = this.v_Line.elementAt(i);
                        int n = 0;
                        for (int n2 = 0; s.indexOf(124, n2) > 0; n2 = s.indexOf(124, n2) + 1) {
                            n += this.fontHigh;
                        }
                        if (this.m_Y + this.fontHigh > topMarg + this.scrollPosY && this.m_Y < topMarg + n + this.scrollPosY) {
                            this.v_current = i;
                            graphics.setColor(this.linkColor);
                        }
                    }
                    final StringTokenizer stringTokenizer = new StringTokenizer(this.v_Line.elementAt(i), "|");
                    if (topMarg + this.scrollPosY == this.topMarg + this.fontHigh) {
                        this.doDwell = true;
                        if (this.A != null && this.oldScrollY != this.scrollPosY && !this.m_Good) {
                            this.A.play();
                        }
                        this.oldScrollY = this.scrollPosY;
                    }
                    while (stringTokenizer.hasMoreTokens()) {
                        String s2 = stringTokenizer.nextToken();
                        if (s2.indexOf("^^^^^") > -1) {
                            s2 = s2.substring(0, s2.indexOf("^^^^^")) + "     ";
                        }
                        if (this.HeadLines) {
                            if (s2.substring(0, 3).equalsIgnoreCase("<b>")) {
                                s2 = s2.substring(3);
                                color = graphics.getColor();
                                graphics.setColor(this.headColor);
                                if (this.m_Good && this.v_current == i) {
                                    graphics.setColor(this.hotColor);
                                }
                            }
                            else {
                                graphics.setColor(color);
                            }
                        }
                        final int stringWidth = this.FM.stringWidth(s2);
                        final int n3 = this.leftMarg + (this.aWide - this.rightMarg - this.leftMarg) / 2 - stringWidth / 2;
                        if (this.howAlign == "left") {
                            graphics.drawString(s2, this.leftMarg, topMarg);
                        }
                        if (this.howAlign == "right") {
                            graphics.drawString(s2, this.aWide - this.rightMarg - stringWidth, topMarg);
                        }
                        if (this.howAlign == "center") {
                            graphics.drawString(s2, n3, topMarg);
                        }
                        topMarg += this.fontHigh;
                    }
                    topMarg += this.fontHigh;
                    graphics.setColor(this.textColor);
                }
                if (topMarg + this.scrollPosY < this.topMarg) {
                    b = true;
                }
            }
            if (b) {
                this.scrollPosY = 0;
            }
        }
    }
    
    public void run() {
        try {
            while (true) {
                if (this.first) {
                    this.render();
                    this.repaint();
                }
                if ((this.doDwell || this.first) && !this.m_Good) {
                    for (int n = 0; !this.m_Good && n < this.Dwell && this.doDwell; n += 10) {
                        Thread.sleep(10L);
                    }
                    this.doDwell = false;
                }
                else {
                    Thread.sleep(this.Speed);
                }
                this.first = false;
                this.doDwell = false;
                if (!this.m_Good) {
                    --this.scrollPosY;
                }
                this.render();
                this.repaint();
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void update(final Graphics graphics) {
        this.render();
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.i_Buff == null) {
            this.i_Buff = this.createImage(this.aWide, this.aHigh);
        }
        graphics.drawImage(this.i_Buff, 0, 0, null);
    }
    
    public void start() {
        if (this.News == null) {
            this.News = new Thread(this);
        }
        this.News.start();
    }
    
    public void stop() {
        if (this.A != null) {
            this.A.stop();
        }
        if (this.News != null) {
            this.News.stop();
            this.News = null;
        }
    }
    
    public void destroy() {
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.v_current > -1) {
            this.URLcheck = -1;
            URL url = null;
            String string = null;
            if (this.v_URL.elementAt(this.v_current) != null) {
                url = this.getURL(this.v_URL.elementAt(this.v_current).toString());
            }
            if (this.v_Targ.elementAt(this.v_current) != null) {
                string = this.v_Targ.elementAt(this.v_current).toString();
            }
            if (this.URLcheck < 0) {
                if (this.v_Targ.elementAt(this.v_current) == null) {
                    if (this.v_URL.elementAt(this.v_current) != null) {
                        this.getAppletContext().showDocument(url);
                    }
                }
                else if (this.v_URL.elementAt(this.v_current) != null) {
                    this.getAppletContext().showDocument(url, string);
                }
            }
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int y) {
        this.m_Good = true;
        this.m_Y = y;
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int y) {
        this.m_Good = false;
        this.doDwell = false;
        if (this.first) {
            this.first = false;
        }
        this.m_Y = y;
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int y) {
        this.m_Y = y;
        this.repaint();
        return true;
    }
    
    private void DoC() {
        this.pfN = false;
        this.pfK = false;
        final int int1 = Integer.parseInt("452617".substring(0, 1));
        final int int2 = Integer.parseInt("452617".substring(1, 2));
        final int int3 = Integer.parseInt("452617".substring(2, 3));
        final int int4 = Integer.parseInt("452617".substring(3, 4));
        final int int5 = Integer.parseInt("452617".substring(4, 5));
        final int int6 = Integer.parseInt("452617".substring(5, 6));
        final URL documentBase = this.getDocumentBase();
        this.hostName = documentBase.getHost();
        if (documentBase.getProtocol().toUpperCase().indexOf("FILE") > -1) {
            this.pfK = true;
            this.fromDisk = true;
        }
        final String parameter = this.getParameter("Notice");
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("Applet by www.MicroTicker.com")) {
                this.pfN = true;
            }
            else {
                this.pfN = false;
            }
        }
        if (parameter == null) {
            this.pfN = false;
        }
        String string = "CodeBrainRocks";
        final String parameter2 = this.getParameter("KeyCode");
        if (parameter2 == null) {
            this.pfK = false;
        }
        if (parameter2 != null) {
            if (parameter2.startsWith("FREE")) {
                this.FreeVersion = true;
            }
            if (parameter2.length() > 10) {
                string = parameter2;
            }
        }
        int n = 0;
        for (int i = 0; i < string.length(); ++i) {
            if (string.substring(i, i + 1).indexOf("|") > -1) {
                ++n;
            }
        }
        if (n == 0) {
            string += "|CodeBrainRocks";
            n = 1;
        }
        final String[] array = new String[13];
        final StringTokenizer stringTokenizer = new StringTokenizer(string, "|");
        final int n2 = n + 1;
        for (int j = 0; j < n2; ++j) {
            array[j] = stringTokenizer.nextToken();
        }
        final String[] array2 = new String[13];
        for (int k = 0; k < n2; ++k) {
            final String substring = array[k].substring(3);
            final String substring2 = substring.substring(0, substring.length() - 3);
            String string2 = "";
            int n3 = 0;
            for (int l = 0; l < substring2.length(); ++l) {
                int char1 = substring2.charAt(l);
                if (n3 == 0) {
                    char1 += int1;
                }
                if (n3 == 1) {
                    char1 += int2;
                }
                if (n3 == 2) {
                    char1 += int3;
                }
                if (n3 == 3) {
                    char1 += int4;
                }
                if (n3 == 4) {
                    char1 += int5;
                }
                if (n3 == 5) {
                    char1 += int6;
                }
                string2 += String.valueOf((char)char1);
                if (++n3 > 5) {
                    n3 = 0;
                }
            }
            String string3 = "";
            int n4 = 0;
            for (int n5 = 0; n5 < string2.length(); ++n5) {
                int n6 = string2.charAt(n5) - '\u0003';
                if (n6 == 118) {
                    n6 = 126;
                }
                string3 += String.valueOf((char)n6);
                if (++n4 > 5) {
                    n4 = 0;
                }
            }
            array2[k] = string3;
        }
        final String upperCase = String.valueOf(this.getDocumentBase()).toUpperCase();
        for (int n7 = 0; n7 < n2; ++n7) {
            if (upperCase.indexOf(array2[n7]) > -1) {
                this.pfK = true;
            }
        }
        if (this.FreeVersion) {
            this.pfK = true;
        }
    }
    
    public NewsMaker() {
        this.pfK = false;
        this.pfN = false;
        this.FreeVersion = false;
        this.hostName = "This URL";
        this.fromDisk = false;
        this.FirstPass = true;
        this.Dwell = 3000;
        this.Speed = 10;
        this.doDwell = true;
        this.first = true;
        this.v_Line = new Vector();
        this.v_current = -1;
        this.v_URL = new Vector();
        this.v_Targ = new Vector();
        this.i_File = "";
        this.m_Good = false;
        this.leftMarg = 5;
        this.rightMarg = 5;
        this.topMarg = 5;
        this.botMarg = 5;
        this.sWide = 100;
        this.sHigh = 100;
        this.scrollPosY = 14;
        this.textColor = Color.black;
        this.linkColor = new Color(0, 128, 0);
        this.headColor = Color.blue;
        this.hotColor = Color.red;
        this.backColor = Color.white;
        this.fontHigh = 14;
        this.fontSize = 12;
        this.fontName = "Dialog";
        this.howAlign = "left";
        this.HeadLines = false;
        this.URLcheck = -1;
        this.Script = new Vector();
        this.oldScrollY = 12345;
    }
}
