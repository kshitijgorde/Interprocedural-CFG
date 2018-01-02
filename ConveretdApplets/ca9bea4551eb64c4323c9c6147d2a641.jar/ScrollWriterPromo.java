import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.util.Vector;
import java.awt.Point;
import java.awt.Rectangle;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScrollWriterPromo extends Applet implements Runnable
{
    private int topMargin;
    private int pad;
    private int totalLines;
    private int[] sleepTime;
    private int[] pauseTime;
    private int cursorType;
    private int linesPerPage;
    private int scrollSpeed;
    private int spacing;
    private Font[] font;
    private String[] str;
    private String[] lineAlign;
    private String[] message;
    private int currentString;
    private int previousString;
    private Image osi;
    private Graphics osg;
    private Thread runner;
    private Color[] fgColor;
    private Color bgColor;
    private Color mouseOverColor;
    private Color cursorColor;
    private URL[] url;
    private boolean mouseOverPause;
    private boolean scrollType;
    private Rectangle[] rect;
    private Point mouseLoc;
    private int urlPointer;
    private String frame;
    private String sourceFile;
    
    public void init() {
        try {
            final String parameter = this.getParameter("sourceFile");
            if (!parameter.equals("")) {
                this.sourceFile = parameter;
            }
            else {
                this.sourceFile = null;
            }
        }
        catch (Exception ex) {
            this.sourceFile = null;
        }
        if (this.sourceFile == null) {
            try {
                final String parameter2 = this.getParameter("totalLines");
                if (parameter2 != null) {
                    this.totalLines = Integer.parseInt(parameter2);
                }
                else {
                    this.totalLines = 0;
                }
            }
            catch (Exception ex2) {
                this.totalLines = 0;
            }
            this.str = new String[this.totalLines];
            try {
                for (int i = 0; i < this.totalLines; ++i) {
                    this.str[i] = this.getParameter("line" + String.valueOf(i + 1));
                }
            }
            catch (Exception ex3) {}
        }
        else {
            this.loadFile();
        }
        Color color;
        try {
            final String parameter3 = this.getParameter("defaultFontColor");
            if (!parameter3.equals("")) {
                final int index = parameter3.indexOf(",");
                final int index2 = parameter3.indexOf(",", index + 1);
                color = new Color(Integer.parseInt(parameter3.substring(0, index)), Integer.parseInt(parameter3.substring(index + 1, index2)), Integer.parseInt(parameter3.substring(index2 + 1, parameter3.length())));
            }
            else {
                color = new Color(255, 255, 255);
            }
        }
        catch (Exception ex4) {
            color = new Color(255, 255, 255);
        }
        try {
            final String parameter4 = this.getParameter("cursorColor");
            if (!parameter4.equals("")) {
                final int index3 = parameter4.indexOf(",");
                final int index4 = parameter4.indexOf(",", index3 + 1);
                this.cursorColor = new Color(Integer.parseInt(parameter4.substring(0, index3)), Integer.parseInt(parameter4.substring(index3 + 1, index4)), Integer.parseInt(parameter4.substring(index4 + 1, parameter4.length())));
            }
            else {
                this.cursorColor = new Color(255, 255, 255);
            }
        }
        catch (Exception ex5) {
            this.cursorColor = new Color(255, 255, 255);
        }
        try {
            final String parameter5 = this.getParameter("backgroundColor");
            if (!parameter5.equals("")) {
                final int index5 = parameter5.indexOf(",");
                final int index6 = parameter5.indexOf(",", index5 + 1);
                this.bgColor = new Color(Integer.parseInt(parameter5.substring(0, index5)), Integer.parseInt(parameter5.substring(index5 + 1, index6)), Integer.parseInt(parameter5.substring(index6 + 1, parameter5.length())));
            }
            else {
                this.bgColor = new Color(0, 0, 0);
            }
        }
        catch (Exception ex6) {
            this.bgColor = new Color(0, 0, 0);
        }
        try {
            final String parameter6 = this.getParameter("urlMouseOverColor");
            if (!parameter6.equals("")) {
                final int index7 = parameter6.indexOf(",");
                final int index8 = parameter6.indexOf(",", index7 + 1);
                this.mouseOverColor = new Color(Integer.parseInt(parameter6.substring(0, index7)), Integer.parseInt(parameter6.substring(index7 + 1, index8)), Integer.parseInt(parameter6.substring(index8 + 1, parameter6.length())));
            }
            else {
                this.mouseOverColor = new Color(255, 0, 0);
            }
        }
        catch (Exception ex7) {
            this.mouseOverColor = new Color(255, 0, 0);
        }
        this.fgColor = new Color[this.totalLines];
        for (int j = 0; j < this.totalLines; ++j) {
            try {
                final String parameter7 = this.getParameter("fgColor" + String.valueOf(j + 1));
                if (!parameter7.equals("")) {
                    final int index9 = parameter7.indexOf(",");
                    final int index10 = parameter7.indexOf(",", index9 + 1);
                    this.fgColor[j] = new Color(Integer.parseInt(parameter7.substring(0, index9)), Integer.parseInt(parameter7.substring(index9 + 1, index10)), Integer.parseInt(parameter7.substring(index10 + 1, parameter7.length())));
                }
                else {
                    this.fgColor[j] = color;
                }
            }
            catch (Exception ex8) {
                this.fgColor[j] = color;
            }
        }
        this.url = new URL[this.totalLines];
        for (int k = 0; k < this.totalLines; ++k) {
            try {
                final String parameter8 = this.getParameter("url" + String.valueOf(k + 1));
                if (!parameter8.equals("")) {
                    if (parameter8.startsWith("http:")) {
                        this.url[k] = new URL(parameter8);
                    }
                    else {
                        this.url[k] = new URL(this.getDocumentBase(), parameter8);
                    }
                }
                else {
                    this.url[k] = null;
                }
            }
            catch (Exception ex9) {
                this.url[k] = null;
            }
        }
        this.message = new String[this.totalLines];
        for (int l = 0; l < this.totalLines; ++l) {
            try {
                final String parameter9 = this.getParameter("message" + String.valueOf(l + 1));
                if (!parameter9.equals("")) {
                    this.message[l] = parameter9;
                }
                else if (this.url[l] != null) {
                    this.message[l] = this.url[l].toString();
                }
                else {
                    this.message[l] = "Free applets at www.consultcom.com";
                }
            }
            catch (Exception ex10) {
                if (this.url[l] != null) {
                    this.message[l] = this.url[l].toString();
                }
                else {
                    this.message[l] = "Free applets at www.consultcom.com";
                }
            }
        }
        int int1;
        try {
            final String parameter10 = this.getParameter("defaultFontSize");
            if (parameter10 != null) {
                int1 = Integer.parseInt(parameter10);
            }
            else {
                int1 = 12;
            }
        }
        catch (Exception ex11) {
            int1 = 12;
        }
        final int[] array = new int[this.totalLines];
        for (int n = 0; n < this.totalLines; ++n) {
            try {
                final String parameter11 = this.getParameter("fontSize" + String.valueOf(n + 1));
                if (!parameter11.equals("")) {
                    array[n] = Integer.parseInt(parameter11);
                }
                else {
                    array[n] = int1;
                }
            }
            catch (Exception ex12) {
                array[n] = int1;
            }
        }
        String s;
        try {
            final String parameter12 = this.getParameter("defaultFontName");
            if (parameter12 != null) {
                s = parameter12;
            }
            else {
                s = "TimesNewRoman";
            }
        }
        catch (Exception ex13) {
            s = "TimesNewRoman";
        }
        String s2;
        try {
            final String parameter13 = this.getParameter("defaultLineAlignment");
            if (parameter13.equalsIgnoreCase("RIGHT")) {
                s2 = "RIGHT";
            }
            else if (parameter13.equalsIgnoreCase("CENTER")) {
                s2 = "CENTER";
            }
            else {
                s2 = "LEFT";
            }
        }
        catch (Exception ex14) {
            s2 = "LEFT";
        }
        this.lineAlign = new String[this.totalLines];
        for (int n2 = 0; n2 < this.totalLines; ++n2) {
            try {
                final String parameter14 = this.getParameter("alignment" + String.valueOf(n2 + 1));
                if (parameter14.equalsIgnoreCase("RIGHT")) {
                    this.lineAlign[n2] = "RIGHT";
                }
                else if (parameter14.equalsIgnoreCase("CENTER")) {
                    this.lineAlign[n2] = "CENTER";
                }
                else if (parameter14.equalsIgnoreCase("LEFT")) {
                    this.lineAlign[n2] = "LEFT";
                }
                else {
                    this.lineAlign[n2] = s2;
                }
            }
            catch (Exception ex15) {
                this.lineAlign[n2] = s2;
            }
        }
        try {
            if (this.getParameter("onMouseOverPause").equalsIgnoreCase("FALSE")) {
                this.mouseOverPause = false;
            }
            else {
                this.mouseOverPause = true;
            }
        }
        catch (Exception ex16) {
            this.mouseOverPause = true;
        }
        final String[] array2 = new String[this.totalLines];
        for (int n3 = 0; n3 < this.totalLines; ++n3) {
            try {
                final String parameter15 = this.getParameter("fontName" + String.valueOf(n3 + 1));
                if (!parameter15.equalsIgnoreCase("")) {
                    array2[n3] = parameter15;
                }
                else {
                    array2[n3] = s;
                }
            }
            catch (Exception ex17) {
                array2[n3] = s;
            }
        }
        int n4;
        try {
            final String parameter16 = this.getParameter("defaultFontStyle");
            if (parameter16.equalsIgnoreCase("PLAIN")) {
                n4 = 0;
            }
            else if (parameter16.equalsIgnoreCase("BOLD+ITALIC") || parameter16.equalsIgnoreCase("ITALIC+BOLD")) {
                n4 = 3;
            }
            else if (parameter16.equalsIgnoreCase("ITALIC")) {
                n4 = 2;
            }
            else {
                n4 = 1;
            }
        }
        catch (Exception ex18) {
            n4 = 1;
        }
        final int[] array3 = new int[this.totalLines];
        for (int n5 = 0; n5 < this.totalLines; ++n5) {
            try {
                final String parameter17 = this.getParameter("fontStyle" + String.valueOf(n5 + 1));
                if (parameter17.equalsIgnoreCase("PLAIN")) {
                    array3[n5] = 0;
                }
                else if (parameter17.equalsIgnoreCase("BOLD+ITALIC") || parameter17.equalsIgnoreCase("ITALIC+BOLD")) {
                    array3[n5] = 3;
                }
                else if (parameter17.equalsIgnoreCase("ITALIC")) {
                    array3[n5] = 2;
                }
                else if (parameter17.equalsIgnoreCase("BOLD")) {
                    array3[n5] = 1;
                }
                else {
                    array3[n5] = n4;
                }
            }
            catch (Exception ex19) {
                array3[n5] = n4;
            }
        }
        int int2;
        try {
            final String parameter18 = this.getParameter("defaultCharSleep");
            if (parameter18 == null) {
                int2 = 100;
            }
            else {
                int2 = Integer.parseInt(parameter18);
            }
        }
        catch (Exception ex20) {
            int2 = 100;
        }
        this.sleepTime = new int[this.totalLines];
        for (int n6 = 0; n6 < this.totalLines; ++n6) {
            try {
                final String parameter19 = this.getParameter("charSleep" + String.valueOf(n6 + 1));
                if (!parameter19.equals("")) {
                    this.sleepTime[n6] = Integer.parseInt(parameter19);
                }
                else {
                    this.sleepTime[n6] = int2;
                }
            }
            catch (Exception ex21) {
                this.sleepTime[n6] = int2;
            }
        }
        int int3;
        try {
            final String parameter20 = this.getParameter("defaultLinePause");
            if (parameter20 == null) {
                int3 = 100;
            }
            else {
                int3 = Integer.parseInt(parameter20);
            }
        }
        catch (Exception ex22) {
            int3 = 100;
        }
        this.pauseTime = new int[this.totalLines];
        for (int n7 = 0; n7 < this.totalLines; ++n7) {
            try {
                final String parameter21 = this.getParameter("pause" + String.valueOf(n7 + 1));
                if (!parameter21.equals("")) {
                    this.pauseTime[n7] = Integer.parseInt(parameter21);
                }
                else {
                    this.pauseTime[n7] = int3;
                }
            }
            catch (Exception ex23) {
                this.pauseTime[n7] = int3;
            }
        }
        try {
            final String parameter22 = this.getParameter("topMargin");
            if (parameter22 != null) {
                this.topMargin = Integer.parseInt(parameter22);
            }
            else {
                this.topMargin = 0;
            }
        }
        catch (Exception ex24) {
            this.topMargin = 0;
        }
        try {
            final String parameter23 = this.getParameter("horizontalMargin");
            if (parameter23 != null) {
                this.pad = Integer.parseInt(parameter23);
            }
            else {
                this.pad = 0;
            }
        }
        catch (Exception ex25) {
            this.pad = 0;
        }
        try {
            final String parameter24 = this.getParameter("cursorType");
            if (parameter24.equalsIgnoreCase("none")) {
                this.cursorType = 2;
            }
            else if (parameter24.equalsIgnoreCase("underline")) {
                this.cursorType = 1;
            }
            else {
                this.cursorType = 0;
            }
        }
        catch (Exception ex26) {
            this.cursorType = 0;
        }
        try {
            final String parameter25 = this.getParameter("scrollSpeed");
            if (parameter25 != null) {
                this.scrollSpeed = Integer.parseInt(parameter25);
            }
            else {
                this.scrollSpeed = 0;
            }
        }
        catch (Exception ex27) {
            this.scrollSpeed = 0;
        }
        try {
            final String parameter26 = this.getParameter("scrollOffset");
            if (parameter26 != null) {
                this.linesPerPage = Integer.parseInt(parameter26);
            }
            else {
                this.linesPerPage = 2;
            }
        }
        catch (Exception ex28) {
            this.linesPerPage = 2;
        }
        try {
            final String parameter27 = this.getParameter("lineSpacing");
            if (parameter27 != null) {
                this.spacing = Integer.parseInt(parameter27);
            }
            else {
                this.spacing = 0;
            }
        }
        catch (Exception ex29) {
            this.spacing = 0;
        }
        try {
            final String parameter28 = this.getParameter("target");
            if (!parameter28.equals("")) {
                this.frame = parameter28;
            }
            else {
                this.frame = "_top";
            }
        }
        catch (Exception ex30) {
            this.frame = "_top";
        }
        try {
            if (this.getParameter("smoothScroll").equalsIgnoreCase("false")) {
                this.scrollType = false;
            }
            else {
                this.scrollType = true;
            }
        }
        catch (Exception ex31) {
            this.scrollType = true;
        }
        this.font = new Font[this.totalLines];
        for (int n8 = 0; n8 < this.totalLines; ++n8) {
            this.font[n8] = new Font(array2[n8], array3[n8], array[n8]);
        }
        this.osi = this.createImage(this.size().width, this.size().height);
        this.osg = this.osi.getGraphics();
    }
    
    public void loadFile() {
        try {
            final Vector vector = new Vector<String>(1000);
            final DataInputStream dataInputStream = new DataInputStream(new URL(this.getCodeBase(), this.sourceFile).openStream());
            for (String s = dataInputStream.readLine(); s != null; s = dataInputStream.readLine()) {
                vector.addElement(s.trim());
            }
            this.totalLines = vector.size();
            vector.copyInto(this.str = new String[this.totalLines]);
        }
        catch (Exception ex) {
            this.showStatus("Error opening file");
        }
    }
    
    public void start() {
        if (this.runner == null) {
            this.runner = new Thread(this);
        }
        this.runner.start();
    }
    
    public void stop() {
        if (this.runner != null || this.runner.isAlive()) {
            this.runner.stop();
        }
        this.runner = null;
    }
    
    public void run() {
        final int topMargin = this.topMargin;
        try {
            while (this.runner.isAlive()) {
                this.previousString = 0;
                while (this.previousString < this.str.length) {
                    this.topMargin = topMargin;
                    if (this.str[this.previousString].length() > 0) {
                        this.currentString = 1;
                        while (this.currentString <= this.str[this.previousString].length()) {
                            this.repaint();
                            this.threadSleep(this.sleepTime[this.previousString]);
                            if (this.currentString >= this.str[this.previousString].length()) {
                                this.threadSleep(this.pauseTime[this.previousString]);
                                if (this.previousString >= this.linesPerPage - 1 && this.previousString + 1 < this.totalLines && this.scrollType) {
                                    int n = Toolkit.getDefaultToolkit().getFontMetrics(this.font[this.previousString + 1]).getHeight() + this.spacing;
                                    while (n-- > 0) {
                                        --this.topMargin;
                                        this.repaint();
                                        this.threadSleep(this.scrollSpeed);
                                    }
                                }
                            }
                            ++this.currentString;
                        }
                    }
                    else if (this.str[this.previousString].length() == 0 && this.previousString >= this.linesPerPage - 1 && this.previousString + 1 < this.totalLines && this.scrollType) {
                        int n2 = this.previousString + 1;
                        if (this.str[n2].equals("")) {
                            ++n2;
                        }
                        int n3 = Toolkit.getDefaultToolkit().getFontMetrics(this.font[n2]).getHeight() + this.spacing;
                        while (n3-- > 0) {
                            --this.topMargin;
                            this.repaint();
                            this.threadSleep(this.scrollSpeed);
                        }
                    }
                    ++this.previousString;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        try {
            int topMargin = this.topMargin;
            boolean b = false;
            this.osg.setColor(this.bgColor);
            this.osg.fillRect(0, 0, this.size().width, this.size().height);
            this.rect = new Rectangle[this.totalLines];
            if (this.previousString > 0) {
                for (int i = this.previousString - (this.linesPerPage - 1); i < this.previousString; ++i) {
                    if (i >= 0) {
                        this.osg.setFont(this.font[i]);
                        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.osg.getFont());
                        final int stringWidth = fontMetrics.stringWidth(this.str[i]);
                        int pad;
                        if (this.lineAlign[i].equals("LEFT")) {
                            pad = this.pad;
                        }
                        else if (this.lineAlign[i].equals("CENTER")) {
                            pad = (this.size().width - stringWidth) / 2;
                        }
                        else {
                            pad = this.size().width - stringWidth - this.pad;
                        }
                        final int height = fontMetrics.getHeight();
                        this.rect[i] = new Rectangle(pad, topMargin, stringWidth, height);
                        final int n = topMargin + height;
                        if (this.rect[i].inside(this.mouseLoc.x, this.mouseLoc.y)) {
                            if (this.url[i] != null) {
                                this.osg.setColor(this.mouseOverColor);
                                this.urlPointer = i;
                            }
                            else {
                                this.osg.setColor(this.fgColor[i]);
                                this.urlPointer = -1;
                            }
                            this.showStatus(this.message[i]);
                            b = true;
                        }
                        else {
                            this.osg.setColor(this.fgColor[i]);
                            if (!b) {
                                this.urlPointer = -1;
                            }
                        }
                        if (!this.str[i].equals("")) {
                            this.osg.drawString(this.str[i], pad, n);
                        }
                        topMargin = n + this.spacing;
                    }
                }
            }
            this.osg.setFont(this.font[this.previousString]);
            final FontMetrics fontMetrics2 = Toolkit.getDefaultToolkit().getFontMetrics(this.osg.getFont());
            final int stringWidth2 = fontMetrics2.stringWidth(this.str[this.previousString]);
            int pad2;
            if (this.lineAlign[this.previousString].equals("LEFT")) {
                pad2 = this.pad;
            }
            else if (this.lineAlign[this.previousString].equals("CENTER")) {
                pad2 = (this.size().width - stringWidth2) / 2;
            }
            else {
                pad2 = this.size().width - stringWidth2 - this.pad;
            }
            final int height2 = fontMetrics2.getHeight();
            this.rect[this.previousString] = new Rectangle(pad2, topMargin, stringWidth2, height2);
            final int n2 = topMargin + height2;
            if (this.rect[this.previousString].inside(this.mouseLoc.x, this.mouseLoc.y)) {
                if (this.url[this.previousString] != null) {
                    this.osg.setColor(this.mouseOverColor);
                    this.urlPointer = this.previousString;
                }
                else {
                    this.osg.setColor(this.fgColor[this.previousString]);
                }
                this.showStatus(this.message[this.previousString]);
            }
            else {
                this.osg.setColor(this.fgColor[this.previousString]);
                if (!b) {
                    this.showStatus("Free applets at www.consultcom.com");
                    this.urlPointer = -1;
                }
            }
            if (!this.str[this.previousString].equals("")) {
                this.osg.drawString(this.str[this.previousString].substring(0, this.currentString), pad2, n2);
                final int n3 = fontMetrics2.stringWidth(this.str[this.previousString].substring(0, this.currentString)) + pad2;
                this.osg.setColor(this.cursorColor);
                if (this.cursorType == 0) {
                    this.osg.fillRect(n3, n2 - fontMetrics2.getAscent(), 10, fontMetrics2.getAscent());
                }
                else if (this.cursorType == 1) {
                    this.osg.fillRect(n3, n2 - 2, 10, 2);
                }
            }
            graphics.drawImage(this.osi, 0, 0, this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void threadSleep(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        try {
            if (this.mouseOverPause) {
                this.runner.suspend();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        try {
            this.runner.resume();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.mouseLoc = new Point(-1, -1);
        this.urlPointer = -1;
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.mouseLoc = new Point(n, n2);
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            if (this.urlPointer > -1 && this.url[this.urlPointer] != null) {
                this.getAppletContext().showDocument(this.url[this.urlPointer], this.frame);
            }
        }
        catch (Exception ex) {
            this.showStatus(ex.toString());
        }
        return true;
    }
    
    public ScrollWriterPromo() {
        this.mouseLoc = new Point(-1, -1);
        this.urlPointer = -1;
    }
}
