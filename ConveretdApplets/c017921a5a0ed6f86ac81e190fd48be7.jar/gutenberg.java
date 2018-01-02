import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Component;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.MediaTracker;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Image;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class gutenberg extends Applet implements Runnable
{
    Thread Gt;
    Vector Script;
    Image image;
    boolean isSuspended;
    boolean isRunning;
    boolean pf;
    boolean pfK;
    boolean pfN;
    boolean FreeVersion;
    String hostName;
    boolean fromDisk;
    int aWide;
    int aHigh;
    Color bgColor;
    AudioClip sound;
    AudioClip sound1;
    AudioClip sound2;
    AudioClip sound3;
    AudioClip sound4;
    AudioClip sound5;
    int speed;
    int mouseOverSpeed;
    int priorSpeed;
    int linepause;
    int LeftMargin;
    int TopMargin;
    int BottomMargin;
    int Indent;
    int LineSpacing;
    String[] i_File;
    Image[] i_Array;
    int i_total;
    int whichImage;
    int iX;
    int iY;
    MediaTracker tracker;
    String file;
    FontMetrics fm;
    Color TheFontColor;
    Color defaultFontColor;
    Color TheRectColor;
    String TheFontName;
    String defaultFontName;
    String TheFontStyle;
    String defaultFontStyle;
    int TheFontSize;
    int defaultFontSize;
    int FontStyleNumber;
    int defaultFontStyleNumber;
    int defaultFontH;
    String StatusBarText;
    int fontH;
    int PriorFontH;
    String F;
    boolean SoundState;
    int rX;
    int rY;
    int rW;
    int rH;
    String Target;
    String Link;
    int MouseMode;
    int PriorMode;
    boolean HOLD;
    
    public void init() {
        this.aWide = this.size().width;
        this.aHigh = this.size().height;
        this.BottomMargin = this.aHigh;
        this.DoK();
        this.bgColor = this.parseC(this.getParameter("BGColor"), Color.black);
        final String parameter = this.getParameter("Sound0");
        if (parameter != null) {
            this.F = parameter;
        }
        this.sound = this.getAudioClip(this.getCodeBase(), this.F);
        final String parameter2 = this.getParameter("Sound1");
        if (parameter2 != null) {
            this.F = parameter2;
        }
        this.sound1 = this.getAudioClip(this.getCodeBase(), this.F);
        final String parameter3 = this.getParameter("Sound2");
        if (parameter3 != null) {
            this.F = parameter3;
        }
        this.sound2 = this.getAudioClip(this.getCodeBase(), this.F);
        final String parameter4 = this.getParameter("Sound3");
        if (parameter4 != null) {
            this.F = parameter4;
        }
        this.sound3 = this.getAudioClip(this.getCodeBase(), this.F);
        final String parameter5 = this.getParameter("Sound4");
        if (parameter5 != null) {
            this.F = parameter5;
        }
        this.sound4 = this.getAudioClip(this.getCodeBase(), this.F);
        final String parameter6 = this.getParameter("Sound5");
        if (parameter6 != null) {
            this.F = parameter6;
        }
        this.sound5 = this.getAudioClip(this.getCodeBase(), this.F);
        final String parameter7 = this.getParameter("LeftMargin");
        if (parameter7 != null) {
            this.LeftMargin = Integer.parseInt(parameter7);
        }
        final String parameter8 = this.getParameter("TopMargin");
        if (parameter8 != null) {
            this.TopMargin = Integer.parseInt(parameter8);
        }
        final String parameter9 = this.getParameter("BottomMargin");
        if (parameter9 != null) {
            this.BottomMargin = Integer.parseInt(parameter9);
        }
        for (int i = 0; i < 100; ++i) {
            this.i_File[i] = this.getParameter("Image" + i);
            if (this.i_File[i] == null) {
                break;
            }
            ++this.i_total;
        }
        this.file = this.getParameter("Script");
        if (this.file == null) {
            return;
        }
    }
    
    public void start() {
        if (this.Gt == null) {
            (this.Gt = new Thread(this, "Gt")).start();
            return;
        }
        this.Gt.start();
    }
    
    public void stop() {
        if (this.sound != null) {
            this.sound.stop();
        }
        if (this.sound1 != null) {
            this.sound1.stop();
        }
        if (this.sound2 != null) {
            this.sound2.stop();
        }
        if (this.sound3 != null) {
            this.sound3.stop();
        }
        if (this.sound4 != null) {
            this.sound4.stop();
        }
        if (this.sound5 != null) {
            this.sound5.stop();
        }
        if (this.Gt != null && this.Gt.isAlive()) {
            this.Gt.stop();
            this.Gt = null;
        }
    }
    
    public void destroy() {
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
    
    private int getInt(final String s, final int n) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (Exception ex) {
            int1 = n;
        }
        return int1;
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
    
    public void run() {
        this.HOLD = true;
        this.image = this.createImage(this.aWide, this.aHigh);
        Graphics graphics = this.image.getGraphics();
        graphics.setColor(this.bgColor);
        if (this.FreeVersion && !this.fromDisk) {
            graphics.setColor(Color.black);
        }
        graphics.fillRect(0, 0, this.aWide, this.aHigh);
        this.tracker = new MediaTracker(this);
        for (int i = 0; i < this.i_total; ++i) {
            this.i_Array[i] = this.getImage(this.getCodeBase(), this.i_File[i]);
            this.tracker.addImage(this.i_Array[i], i);
        }
        for (boolean b = true; b; b = false) {
            if (this.tracker.checkAll(true)) {}
        }
        this.whichImage = 100;
        this.repaint();
        if (this.FreeVersion && !this.fromDisk) {
            this.Link = "http://www.codebrain.com";
            this.Target = "_blank";
            this.PriorMode = this.MouseMode;
            this.MouseMode = 99;
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.aWide, this.aHigh);
            graphics.setColor(Color.white);
            graphics.setFont(new Font("Dialog", 0, 10));
            graphics.drawString("Gutenberg Typer", 2, 11);
            graphics.drawString("Free Version 2.0.1", 2, 23);
            graphics.drawString("By CodeBrain.com", 2, 35);
            graphics.setColor(this.bgColor);
            graphics.setFont(new Font(this.TheFontName, this.FontStyleNumber, this.TheFontSize));
            try {
                Thread.sleep(8000L);
            }
            catch (InterruptedException ex) {}
            this.Link = null;
            this.Target = null;
            this.MouseMode = this.PriorMode;
        }
        if (this.Script.isEmpty()) {
            this.getScript();
        }
        FontMetrics fontMetrics = graphics.getFontMetrics();
        fontMetrics.stringWidth("A");
        this.isRunning = true;
        this.HOLD = false;
        int n = 1;
        int topMargin = this.TopMargin;
        while (!this.HOLD) {
            graphics.setColor(this.bgColor);
            graphics.fillRect(0, 0, this.aWide, this.aHigh);
            for (int j = 0; j < this.Script.size(); ++j) {
                final String s = this.Script.elementAt(j);
                if (s.toLowerCase().startsWith("<force")) {
                    this.repaint();
                }
                else if (s.toLowerCase().startsWith("<image ")) {
                    int index = s.indexOf(" ");
                    int n2 = s.indexOf(">");
                    if (n2 < 0) {
                        n2 = s.length();
                    }
                    ++index;
                    this.whichImage = Integer.parseInt(s.substring(index, n2));
                    if (this.whichImage < 0) {
                        this.whichImage = 100;
                    }
                    if (this.whichImage > -1 && this.whichImage < 100) {
                        graphics.drawImage(this.i_Array[this.whichImage], this.iX, this.iY, this.i_Array[this.whichImage].getWidth(this), this.i_Array[this.whichImage].getHeight(this), this);
                    }
                    this.repaint();
                }
                else if (s.toLowerCase().startsWith("<imagexy ")) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
                    stringTokenizer.nextToken();
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(new StringTokenizer(stringTokenizer.nextToken(), ">").nextToken(), ",");
                    this.iX = Integer.parseInt(stringTokenizer2.nextToken());
                    this.iY = Integer.parseInt(stringTokenizer2.nextToken());
                }
                else if (s.toLowerCase().startsWith("<clickmode")) {
                    this.MouseMode = 0;
                }
                else if (s.toLowerCase().startsWith("<deadmode")) {
                    this.MouseMode = 1;
                }
                else if (s.toLowerCase().startsWith("<linkmode")) {
                    this.MouseMode = 2;
                }
                else if (s.toLowerCase().startsWith("<clickwait")) {
                    this.isSuspended = true;
                    this.PriorMode = this.MouseMode;
                    this.MouseMode = 3;
                    this.Gt.suspend();
                }
                else if (s.toLowerCase().startsWith("<link ")) {
                    int n3 = s.indexOf(">");
                    if (n3 < 0) {
                        n3 = s.length();
                    }
                    this.Link = s.substring(6, n3);
                }
                else if (s.toLowerCase().startsWith("<target ")) {
                    int n4 = s.indexOf(">");
                    if (n4 < 0) {
                        n4 = s.length();
                    }
                    this.Target = s.substring(8, n4);
                    if (this.Target.startsWith("_")) {
                        this.Target = this.Target.toLowerCase();
                    }
                }
                else if (s.toLowerCase().startsWith("<nolink")) {
                    this.Link = null;
                    this.Target = null;
                }
                else if (s.toLowerCase().startsWith("<bgcolor ")) {
                    final StringTokenizer stringTokenizer3 = new StringTokenizer(s, " ");
                    stringTokenizer3.nextToken();
                    this.bgColor = this.parseC(stringTokenizer3.nextToken(), Color.black);
                }
                else if (s.toLowerCase().startsWith("<linespacing ")) {
                    int index2 = s.indexOf(" ");
                    int n5 = s.indexOf(">");
                    if (n5 < 0) {
                        n5 = s.length();
                    }
                    ++index2;
                    this.LineSpacing = Integer.parseInt(s.substring(index2, n5));
                }
                else if (s.toLowerCase().startsWith("<vfudge ")) {
                    int index3 = s.indexOf(" ");
                    int n6 = s.indexOf(">");
                    if (n6 < 0) {
                        n6 = s.length();
                    }
                    ++index3;
                    topMargin += Integer.parseInt(s.substring(index3, n6));
                }
                else if (s.toLowerCase().startsWith("<soundo")) {
                    int index4 = s.indexOf(" ");
                    if (s.indexOf(">") < 0) {
                        s.length();
                    }
                    ++index4;
                    if (s.startsWith("<SOUNDOFF")) {
                        this.SoundState = false;
                    }
                    if (s.startsWith("<SOUNDON")) {
                        this.SoundState = true;
                    }
                }
                else if (s.toLowerCase().startsWith("<sound 1")) {
                    if (this.sound1 != null && this.SoundState) {
                        this.sound1.play();
                    }
                }
                else if (s.toLowerCase().startsWith("<sound 2")) {
                    if (this.sound2 != null && this.SoundState) {
                        this.sound2.play();
                    }
                }
                else if (s.toLowerCase().startsWith("<sound 3")) {
                    if (this.sound3 != null && this.SoundState) {
                        this.sound3.play();
                    }
                }
                else if (s.toLowerCase().startsWith("<sound 4")) {
                    if (this.sound4 != null && this.SoundState) {
                        this.sound4.play();
                    }
                }
                else if (s.toLowerCase().startsWith("<sound 5")) {
                    if (this.sound5 != null && this.SoundState) {
                        this.sound5.play();
                    }
                }
                else if (s.toLowerCase().startsWith("<speed ")) {
                    int index5 = s.indexOf(" ");
                    int n7 = s.indexOf(">");
                    if (n7 < 0) {
                        n7 = s.length();
                    }
                    ++index5;
                    this.speed = Integer.parseInt(s.substring(index5, n7));
                    if (this.speed < 1) {
                        this.speed = 1;
                    }
                    this.priorSpeed = this.speed;
                }
                else if (s.toLowerCase().startsWith("<mouseoverspeed ")) {
                    int index6 = s.indexOf(" ");
                    int n8 = s.indexOf(">");
                    if (n8 < 0) {
                        n8 = s.length();
                    }
                    ++index6;
                    this.mouseOverSpeed = Integer.parseInt(s.substring(index6, n8));
                }
                else if (s.toLowerCase().startsWith("<indent ")) {
                    int index7 = s.indexOf(" ");
                    int n9 = s.indexOf(">");
                    if (n9 < 0) {
                        n9 = s.length();
                    }
                    ++index7;
                    this.Indent = Integer.parseInt(s.substring(index7, n9));
                }
                else if (s.toLowerCase().startsWith("<fontcolor ")) {
                    final StringTokenizer stringTokenizer4 = new StringTokenizer(s, " ");
                    stringTokenizer4.nextToken();
                    this.TheFontColor = this.parseC(stringTokenizer4.nextToken(), Color.white);
                }
                else if (s.toLowerCase().startsWith("<fontsize ")) {
                    int index8 = s.indexOf(" ");
                    int n10 = s.indexOf(">");
                    if (n10 < 0) {
                        n10 = s.length();
                    }
                    ++index8;
                    this.TheFontSize = Integer.parseInt(s.substring(index8, n10));
                }
                else if (s.toLowerCase().startsWith("<fontname ")) {
                    int index9 = s.indexOf(" ");
                    int n11 = s.indexOf(">");
                    if (n11 < 0) {
                        n11 = s.length();
                    }
                    ++index9;
                    final String substring = s.substring(index9, n11);
                    if (substring.toUpperCase().startsWith("COURIER")) {
                        this.TheFontName = "Courier";
                    }
                    if (substring.toUpperCase().startsWith("DIALOG")) {
                        this.TheFontName = "Dialog";
                    }
                    if (substring.toUpperCase().startsWith("TIMESROMAN")) {
                        this.TheFontName = "TimesRoman";
                    }
                }
                else if (s.toLowerCase().startsWith("<fontstyle ")) {
                    int index10 = s.indexOf(" ");
                    int n12 = s.indexOf(">");
                    if (n12 < 0) {
                        n12 = s.length();
                    }
                    ++index10;
                    this.TheFontStyle = s.substring(index10, n12);
                    if (this.TheFontStyle.toUpperCase().startsWith("PLAIN")) {
                        this.FontStyleNumber = 0;
                    }
                    if (this.TheFontStyle.toUpperCase().startsWith("BOLD")) {
                        this.FontStyleNumber = 1;
                    }
                    if (this.TheFontStyle.toUpperCase().startsWith("ITALIC")) {
                        this.FontStyleNumber = 2;
                    }
                    if (this.TheFontStyle.toUpperCase().startsWith("BOLDITALIC")) {
                        this.FontStyleNumber = 3;
                    }
                }
                else if (!s.startsWith("<!")) {
                    if (s.startsWith("<#")) {
                        int n13 = 1;
                        int n14 = s.indexOf(">");
                        if (n14 < 0) {
                            n14 = s.length();
                        }
                        ++n13;
                        this.StatusBarText = s.substring(n13, n14);
                        if (s.startsWith("<#####")) {
                            this.StatusBarText = "";
                        }
                        this.showStatus(this.StatusBarText);
                    }
                    else if (s.toLowerCase().startsWith("<page")) {
                        graphics.setColor(this.bgColor);
                        graphics.fillRect(0, 0, this.aWide, this.aHigh);
                        n = 1;
                    }
                    else if (s.toLowerCase().startsWith("<pause ")) {
                        int index11 = s.indexOf(" ");
                        int n15 = s.indexOf(">");
                        if (n15 < 0) {
                            n15 = s.length();
                        }
                        ++index11;
                        try {
                            final int int1 = Integer.parseInt(s.substring(index11, n15));
                            if (int1 == 0) {
                                this.isSuspended = true;
                                this.Gt.suspend();
                            }
                            else {
                                Thread.sleep(int1);
                            }
                        }
                        catch (InterruptedException ex2) {}
                    }
                    else if (s.toLowerCase().startsWith("<linepause ")) {
                        int index12 = s.indexOf(" ");
                        int n16 = s.indexOf(">");
                        if (n16 < 0) {
                            n16 = s.length();
                        }
                        ++index12;
                        this.linepause = Integer.parseInt(s.substring(index12, n16));
                        if (this.linepause < 1) {
                            this.linepause = 1;
                        }
                    }
                    else if (s.toLowerCase().startsWith("<rectcolor ")) {
                        final StringTokenizer stringTokenizer5 = new StringTokenizer(s, " ");
                        stringTokenizer5.nextToken();
                        this.TheRectColor = this.parseC(stringTokenizer5.nextToken(), Color.white);
                    }
                    else if (s.toLowerCase().startsWith("<rect ")) {
                        final StringTokenizer stringTokenizer6 = new StringTokenizer(s, " ");
                        stringTokenizer6.nextToken();
                        final StringTokenizer stringTokenizer7 = new StringTokenizer(new StringTokenizer(stringTokenizer6.nextToken(), ">").nextToken(), ",");
                        this.rX = Integer.parseInt(stringTokenizer7.nextToken());
                        this.rY = Integer.parseInt(stringTokenizer7.nextToken());
                        this.rW = Integer.parseInt(stringTokenizer7.nextToken());
                        this.rH = Integer.parseInt(stringTokenizer7.nextToken());
                        graphics.setColor(this.TheRectColor);
                        graphics.draw3DRect(this.rX, this.rY, this.rW, this.rH, true);
                        this.repaint();
                    }
                    else if (s.toLowerCase().startsWith("<rectf ")) {
                        final StringTokenizer stringTokenizer8 = new StringTokenizer(s, " ");
                        stringTokenizer8.nextToken();
                        final StringTokenizer stringTokenizer9 = new StringTokenizer(new StringTokenizer(stringTokenizer8.nextToken(), ">").nextToken(), ",");
                        this.rX = Integer.parseInt(stringTokenizer9.nextToken());
                        this.rY = Integer.parseInt(stringTokenizer9.nextToken());
                        this.rW = Integer.parseInt(stringTokenizer9.nextToken());
                        this.rH = Integer.parseInt(stringTokenizer9.nextToken());
                        graphics.setColor(this.TheRectColor);
                        graphics.fill3DRect(this.rX, this.rY, this.rW, this.rH, true);
                        this.repaint();
                    }
                    else {
                        graphics.setFont(new Font(this.TheFontName, this.FontStyleNumber, this.TheFontSize));
                        int n17 = 0;
                        final int descent = fontMetrics.getDescent();
                        fontMetrics = graphics.getFontMetrics();
                        this.fontH = fontMetrics.getAscent() + descent + this.LineSpacing;
                        topMargin += this.fontH;
                        if (j == 0) {
                            topMargin = this.TopMargin + fontMetrics.getAscent();
                        }
                        if (n != 0) {
                            topMargin = this.TopMargin + fontMetrics.getAscent();
                            n = 0;
                        }
                        for (int k = 0; k < s.length(); ++k) {
                            if (this.sound != null && this.SoundState) {
                                try {
                                    this.sound.stop();
                                    this.sound.play();
                                }
                                catch (Exception ex3) {}
                            }
                            final int stringWidth = fontMetrics.stringWidth(s.substring(k, k + 1));
                            graphics.setColor(this.TheFontColor);
                            graphics.drawString(s.substring(k, k + 1), this.Indent + this.LeftMargin + n17, topMargin);
                            if (!this.pf) {
                                graphics.setColor(Color.black);
                                graphics.fill3DRect(1, 1, this.aWide - 4, 28, true);
                                graphics.setColor(Color.white);
                                graphics.setFont(new Font("Dialog", 0, 10));
                                if (!this.pfN && this.pfK) {
                                    graphics.drawString("Notice wrong", 4, 13);
                                    graphics.drawString("or missing...", 4, 24);
                                }
                                if (!this.pfK && this.pfN) {
                                    graphics.drawString("Need key for:", 4, 13);
                                    graphics.drawString(this.hostName, 4, 24);
                                }
                                if (!this.pfK && !this.pfN) {
                                    graphics.drawString("Errors:", 4, 13);
                                    graphics.drawString("Key & Notice", 4, 24);
                                }
                                graphics.setColor(Color.red);
                                graphics.draw3DRect(1, 1, this.aWide - 4, 28, true);
                                graphics.setColor(this.bgColor);
                                graphics.setFont(new Font(this.TheFontName, this.FontStyleNumber, this.TheFontSize));
                            }
                            try {
                                Thread.sleep(this.speed);
                            }
                            catch (InterruptedException ex4) {}
                            graphics.setColor(this.bgColor);
                            this.repaint();
                            n17 += stringWidth;
                        }
                        if (this.sound != null && this.SoundState) {
                            try {
                                this.sound.stop();
                            }
                            catch (Exception ex5) {}
                        }
                        try {
                            Thread.sleep(this.linepause);
                        }
                        catch (InterruptedException ex6) {}
                    }
                }
                if (topMargin + fontMetrics.getDescent() + this.fontH > this.BottomMargin) {
                    n = 1;
                }
            }
            this.PriorFontH = 0;
            n = 1;
            this.repaint();
            graphics.dispose();
            graphics = this.image.getGraphics();
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.HOLD) {
            this.speed += this.mouseOverSpeed;
            if (this.speed < 1) {
                this.speed = 1;
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.HOLD) {
            this.speed = this.priorSpeed;
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.MouseMode == 2 || this.MouseMode == 99) {
            URL url = null;
            if (this.Link != null) {
                url = this.getURL(this.Link);
            }
            if (this.Target == null) {
                if (this.Link != null) {
                    this.getAppletContext().showDocument(url);
                }
            }
            else if (this.Link != null) {
                this.getAppletContext().showDocument(url, this.Target);
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.MouseMode == 0 && this.isRunning) {
            if (this.isSuspended) {
                this.Gt.resume();
            }
            else {
                this.Gt.suspend();
            }
            this.isSuspended = !this.isSuspended;
        }
        if (this.MouseMode == 3) {
            if (this.isSuspended) {
                this.Gt.resume();
            }
            this.MouseMode = this.PriorMode;
            this.isSuspended = false;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.image != null) {
            graphics.drawImage(this.image, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    URL getURL(final String s) {
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        return url;
    }
    
    private void DoK() {
        this.pf = false;
        this.pfN = false;
        this.pfK = false;
        final int int1 = Integer.parseInt("716254".substring(0, 1));
        final int int2 = Integer.parseInt("716254".substring(1, 2));
        final int int3 = Integer.parseInt("716254".substring(2, 3));
        final int int4 = Integer.parseInt("716254".substring(3, 4));
        final int int5 = Integer.parseInt("716254".substring(4, 5));
        final int int6 = Integer.parseInt("716254".substring(5, 6));
        final URL documentBase = this.getDocumentBase();
        this.hostName = documentBase.getHost();
        if (documentBase.getProtocol().toUpperCase().indexOf("FILE") > -1) {
            this.pf = true;
            this.pfK = true;
            this.fromDisk = true;
        }
        final String parameter = this.getParameter("Notice");
        if (parameter == null) {
            this.pf = false;
        }
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("Applet by www.CodeBrain.com")) {
                this.pfN = true;
            }
            else {
                this.pfN = false;
                this.pf = false;
            }
        }
        String string = "CodeBrainRocks";
        final String parameter2 = this.getParameter("KeyCode");
        if (parameter2 == null || parameter2.startsWith("FREE")) {
            this.FreeVersion = true;
        }
        if (parameter2 != null && parameter2.length() > 10) {
            string = parameter2;
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
        if (this.pfN && this.pfK) {
            this.pf = true;
        }
    }
    
    public gutenberg() {
        this.Script = new Vector();
        this.pf = false;
        this.pfK = false;
        this.pfN = false;
        this.FreeVersion = false;
        this.hostName = "This URL";
        this.fromDisk = false;
        this.speed = 25;
        this.mouseOverSpeed = 25;
        this.priorSpeed = 25;
        this.linepause = 500;
        this.LineSpacing = 2;
        this.i_File = new String[100];
        this.i_Array = new Image[101];
        this.TheFontColor = Color.green;
        this.defaultFontColor = Color.green;
        this.TheRectColor = Color.white;
        this.TheFontName = "Dialog";
        this.defaultFontName = "Dialog";
        this.TheFontStyle = "Plain";
        this.defaultFontStyle = "Plain";
        this.TheFontSize = 12;
        this.defaultFontSize = 12;
        this.SoundState = true;
        this.HOLD = true;
    }
}
