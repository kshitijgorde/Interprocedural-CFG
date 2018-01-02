import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import java.awt.FontMetrics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VertTextScroller extends Applet implements Runnable
{
    String stringFont1;
    int stringStyle;
    int stringSize;
    int offset;
    int w;
    FontMetrics fontMetrics;
    boolean wrap;
    String unregistered;
    String slogan;
    boolean allowed;
    String status;
    String fileName;
    Vector v;
    Vector v2;
    int z;
    boolean clicked;
    Color color;
    long downTime;
    int manualPause;
    int linkClick;
    long startTime;
    boolean link;
    boolean paused;
    Font[] fontArray3;
    boolean mouseexit;
    int mousex;
    int mousey;
    int lastlined;
    boolean once;
    String sColor;
    int tempInt;
    boolean checkError;
    String error;
    int lines;
    String[] lineArray;
    int[] sizeArray;
    Color[] colorArray;
    String[] linkArray;
    int[] pauseLineArray;
    String temp;
    String[] fontArray;
    int[] styleArray;
    int[] lineOffsetArray;
    int lineDown;
    Thread thread;
    Image offscreen;
    Graphics offscreeng;
    int height;
    Font f;
    FontMetrics fm;
    int fs;
    int ypos;
    int lineSpace;
    int diff;
    int startLines;
    int pauseValue;
    Color background;
    int totLines;
    int adjYpos;
    boolean run;
    String linkFrame;
    int lineOffsetDefault;
    Color linkColor;
    String[] SColorArray;
    Color[] ColorMap;
    String[] SStyleArray;
    int[] StyleMap;
    String[] fontArray2;
    int lineSelect;
    int lastLink;
    int lined;
    long diffTime;
    long displayStatus;
    int nextDispl;
    Vector lineVector;
    Vector mapVector;
    int vectorLineNumber;
    int passLine;
    String path;
    boolean stopPause;
    int textStart;
    int textEnd;
    boolean wrapLine;
    
    public VertTextScroller() {
        this.wrap = false;
        this.unregistered = "Unregistered: Contact support@consultcom.com";
        this.slogan = "FREE Vertical Scroller at www.consultcom.com";
        this.allowed = true;
        this.status = "";
        this.fileName = null;
        this.v = new Vector(10);
        this.v2 = new Vector(10);
        this.clicked = false;
        this.link = false;
        this.paused = false;
        this.mouseexit = true;
        this.mousex = 0;
        this.mousey = 0;
        this.lastlined = 0;
        this.once = true;
        this.sColor = "";
        this.checkError = true;
        this.error = "";
        this.lineDown = 0;
        this.offscreen = null;
        this.ypos = 0;
        this.startLines = 0;
        this.pauseValue = 15;
        this.run = true;
        this.linkFrame = "";
        this.linkColor = Color.blue;
        this.SColorArray = new String[] { "black", "blue", "cyan", "darkGray", "gray", "green", "lightGray", "magenta", "orange", "pink", "red", "white", "yellow" };
        this.ColorMap = new Color[] { Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow };
        this.SStyleArray = new String[] { "PLAIN", "ITALIC", "BOLD" };
        this.StyleMap = new int[] { 0, 2, 1 };
        this.fontArray2 = new String[] { "Dialog", "Helvetica", "TimesRoman", "Courier" };
        this.lineSelect = -1;
        this.lastLink = -1;
        this.lined = 0;
        this.displayStatus = 0L;
        this.nextDispl = 5000;
        this.lineVector = new Vector(10);
        this.mapVector = new Vector(10);
        this.vectorLineNumber = 0;
        this.stopPause = false;
        this.textStart = -1;
        this.textEnd = -1;
        this.wrapLine = true;
    }
    
    void checkPause() {
        for (int i = 0; i < this.lineVector.size(); ++i) {
            final Enumeration<Integer> elements = this.mapVector.elementAt(i).elements();
            final int intValue = elements.nextElement();
            final int intValue2 = elements.nextElement();
            if (this.pauseLineArray[intValue] > 0 && intValue2 == i && this.ypos == -(this.startLines + this.lineSpace * i + this.sizeArray[intValue] / 3 - (this.sizeArray[intValue] - this.lineSpace))) {
                ++this.ypos;
                this.paused = true;
                this.pauseit(this.pauseLineArray[intValue]);
                this.paused = false;
                --this.ypos;
                return;
            }
        }
    }
    
    boolean checkSecurity() {
        final String parameter = this.getParameter("copyright");
        if (parameter == null) {
            this.error = this.unregistered;
            this.showStatus(this.status = this.unregistered);
            return false;
        }
        if (parameter.compareTo("ConsultScroll Vertical Text Scroller v2.0 Copyright (c) 1998, consulting.com Inc.") == 0) {
            return true;
        }
        this.error = this.unregistered;
        this.showStatus(this.status = this.unregistered);
        return false;
    }
    
    Color getColor(final String s) {
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        final int index = this.temp.indexOf(",");
        if (index <= 0) {
            for (int i = 0; i < this.SColorArray.length; ++i) {
                if (this.temp.compareTo(this.SColorArray[i]) == 0) {
                    return this.ColorMap[i];
                }
            }
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        final String substring = this.temp.substring(0, index);
        final int index2 = this.temp.indexOf(",", index + 1);
        if (index2 < 0) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        final String substring2 = this.temp.substring(index + 1, index2);
        final String substring3 = this.temp.substring(index2 + 1, this.temp.length());
        int intValue;
        try {
            intValue = Integer.valueOf(substring);
        }
        catch (NumberFormatException ex) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        int intValue2;
        try {
            intValue2 = Integer.valueOf(substring2);
        }
        catch (NumberFormatException ex2) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        int intValue3;
        try {
            intValue3 = Integer.valueOf(substring3);
        }
        catch (NumberFormatException ex3) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        if (intValue > 255 || intValue2 > 255 || intValue3 > 255) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return null;
        }
        return new Color(intValue, intValue2, intValue3);
    }
    
    int getFile() {
        String s = "";
        this.fileName = this.getParameter("textFile");
        if (this.fileName == null) {
            return 0;
        }
        if (this.fileName.trim().equals("")) {
            this.error = "error: textFile value=\"" + this.fileName + "\"";
            return -1;
        }
        URL url;
        try {
            url = new URL(String.valueOf(String.valueOf(this.getCodeBase())) + this.fileName);
        }
        catch (MalformedURLException ex) {
            this.error = "error: textFile value=\"" + this.fileName + "\"";
            return -1;
        }
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(url.openStream()));
            if (!this.wrap) {
                String line;
                while ((line = dataInputStream.readLine()) != null) {
                    if (!line.startsWith("/*")) {
                        this.v.addElement(line);
                    }
                }
                try {
                    dataInputStream.close();
                }
                catch (IOException ex2) {
                    this.error = "error: textFile value=\"" + this.fileName + "\"";
                    return -1;
                }
                return 1;
            }
            String line2;
            while ((line2 = dataInputStream.readLine()) != null) {
                if (!line2.startsWith("/*")) {
                    if (line2.startsWith("<br>") || line2.startsWith("<BR>")) {
                        this.v.addElement(s);
                        if (line2.length() > 4) {
                            s = line2.substring(4, line2.length());
                        }
                        else {
                            s = "";
                        }
                    }
                    else if (s.equals("")) {
                        s = line2;
                    }
                    else {
                        s = String.valueOf(s) + " " + line2;
                    }
                }
            }
        }
        catch (IOException ex3) {
            this.error = "error: textFile value=\"" + this.fileName + "\"";
            return -1;
        }
        try {
            dataInputStream.close();
        }
        catch (IOException ex4) {
            this.error = "error: textFile value=\"" + this.fileName + "\"";
            return -1;
        }
        this.v.addElement(s);
        this.w = this.size().width;
        this.tempInt = this.getFont("fontDefault");
        if (this.tempInt < 0) {
            return 1;
        }
        this.stringFont1 = this.fontArray2[this.tempInt];
        this.tempInt = this.getStyle("styleDefault");
        if (this.tempInt < 0) {
            return 1;
        }
        this.stringStyle = this.StyleMap[this.tempInt];
        this.stringSize = this.getNumber("sizeDefault");
        if (this.stringSize < 0) {
            return 1;
        }
        this.offset = this.getNumber("lineOffsetDefault");
        if (this.offset < 0) {
            this.offset = 0;
        }
        this.fontMetrics = this.getFontMetrics(new Font(this.stringFont1, this.stringStyle, this.stringSize));
        final Enumeration<String> elements = this.v.elements();
        while (elements.hasMoreElements()) {
            this.wordWrap(elements.nextElement(), this.v2);
        }
        this.v = this.v2;
        this.v2 = null;
        return 1;
    }
    
    int getFont(final String s) {
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return -1;
        }
        for (int i = 0; i < this.fontArray2.length; ++i) {
            if (this.temp.compareTo(this.fontArray2[i]) == 0) {
                return i;
            }
        }
        this.error = "error: " + s + " value=\"" + this.temp + "\"";
        return -2;
    }
    
    int getNumber(final String s) {
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return -1;
        }
        int intValue;
        try {
            intValue = Integer.valueOf(this.temp);
        }
        catch (NumberFormatException ex) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return -2;
        }
        return intValue;
    }
    
    int getStyle(final String s) {
        this.temp = this.getParameter(s);
        if (this.temp == null) {
            this.error = "error: " + s + " value=\"" + this.temp + "\"";
            return -1;
        }
        for (int i = 0; i < this.SStyleArray.length; ++i) {
            if (this.temp.compareTo(this.SStyleArray[i]) == 0) {
                return i;
            }
        }
        this.error = "error: " + s + " value=\"" + this.temp + "\"";
        return -2;
    }
    
    public boolean handleEvent(final Event event) {
        this.highlight(event.x, event.y);
        this.mousex = event.x;
        this.mousey = event.y;
        return super.handleEvent(event);
    }
    
    synchronized void highlight(final int n, final int n2) {
        if (!this.link || this.mouseexit || this.linkArray == null || !this.run) {
            return;
        }
        this.adjYpos = this.startLines + this.ypos;
        this.diff = n2 - this.adjYpos;
        this.lineSelect = this.diff / this.lineSpace;
        if (this.lastLink == this.lineSelect) {
            return;
        }
        if (this.lastLink >= 0) {
            this.highliteLines(this.passLine = this.lastLink, 1);
        }
        this.adjYpos = this.startLines + this.ypos;
        if (n2 < this.adjYpos || n2 > this.adjYpos + this.totLines - 1) {
            if (this.paused) {
                this.repaint();
            }
            this.lastLink = -1;
            this.showStatus(this.status = "");
            return;
        }
        final int intValue = this.mapVector.elementAt(this.lineSelect).elements().nextElement();
        if (this.linkArray[intValue].equals("")) {
            if (this.paused) {
                this.repaint();
            }
            this.lastLink = -1;
            if (!this.status.equals(this.slogan)) {
                this.showStatus(this.status = "");
            }
            return;
        }
        this.highliteLines(this.passLine = this.lineSelect, 0);
        if (this.paused) {
            this.repaint();
        }
        this.showStatus(this.status = this.linkArray[intValue]);
        this.lastLink = this.lineSelect;
        this.lastlined = this.lined;
    }
    
    public void highliteLines(final int n, final int n2) {
        Color color = Color.white;
        final Enumeration<Integer> elements = this.mapVector.elementAt(n).elements();
        final int intValue = elements.nextElement();
        this.offscreeng.setFont(this.fontArray3[intValue]);
        final FontMetrics fontMetrics = this.getFontMetrics(this.fontArray3[intValue]);
        fontMetrics.getHeight();
        final int descent = fontMetrics.getDescent();
        final int ascent = fontMetrics.getAscent();
        if (n2 == 0) {
            color = this.linkColor;
        }
        if (n2 == 1) {
            color = this.colorArray[intValue];
        }
        if (n2 == 2) {
            color = Color.red;
        }
        while (elements.hasMoreElements()) {
            final int intValue2 = elements.nextElement();
            this.lined = this.lineSpace * intValue2 + this.startLines + this.lineSpace;
            this.offscreeng.setColor(this.background);
            this.offscreeng.fillRect(0, this.lined + descent - ascent, this.size().width, ascent);
            this.offscreeng.setColor(color);
            this.offscreeng.drawString((String)this.lineVector.elementAt(intValue2), this.lineOffsetArray[intValue], this.lined);
        }
    }
    
    public void init() {
        if (!(this.allowed = this.checkSecurity())) {
            return;
        }
        this.path = this.getCodeBase().toString();
        this.wrap = this.setBoolean("textFileWrap");
        this.showStatus(this.status = this.slogan);
        this.displayStatus = System.currentTimeMillis() + this.nextDispl;
        this.tempInt = this.getFile();
        if (this.tempInt < 0) {
            return;
        }
        if (this.tempInt == 0) {
            this.lines = this.getNumber("numberOflines");
            if (this.lines < 0) {
                return;
            }
        }
        else {
            this.temp = this.getParameter("insertTextFile");
            if (this.temp != null && this.temp.indexOf("-") >= 0) {
                this.lines = this.getNumber("numberOflines");
                if (this.lines < 0) {
                    return;
                }
            }
            else {
                this.lines = this.getNumber("insertTextFile");
                --this.lines;
                if (this.lines < 0) {
                    this.lines = 0;
                }
                this.lines += this.v.size();
            }
        }
        this.lineArray = new String[this.lines];
        this.sizeArray = new int[this.lines];
        this.colorArray = new Color[this.lines];
        this.fontArray = new String[this.lines];
        this.styleArray = new int[this.lines];
        this.linkArray = new String[this.lines];
        this.pauseLineArray = new int[this.lines];
        this.lineOffsetArray = new int[this.lines];
        this.fontArray3 = new Font[this.lines];
        this.linkFrame = this.getParameter("linkFrame");
        if (this.linkFrame == null || this.linkFrame.equals("")) {
            this.error = "error: linkFrame value=\"" + this.linkFrame + "\"";
            return;
        }
        this.manualPause = this.getNumber("manualPause");
        if (this.manualPause < 0) {
            this.manualPause = 0;
        }
        this.linkClick = this.getNumber("linkClick");
        if (this.linkClick < 0) {
            this.linkClick = 1;
        }
        if (this.linkClick > 2) {
            this.linkClick = 2;
        }
        this.color = this.getColor("linkColor");
        if (this.color == null) {
            return;
        }
        this.linkColor = this.color;
        this.temp = this.getParameter("background");
        if (this.temp == null) {
            this.error = "error: background value=\"null\"";
            return;
        }
        this.color = this.getColor("background");
        if (this.color == null) {
            return;
        }
        this.setBackground(this.background = this.color);
        this.lineSpace = this.getNumber("lineSpace");
        if (this.lineSpace < 0) {
            return;
        }
        this.pauseValue = this.getNumber("scrollDelay");
        if (this.pauseValue < 0) {
            return;
        }
        for (int i = 0; i < this.lines; ++i) {
            this.pauseLineArray[i] = this.getNumber("pauseValueDefault");
            if (this.pauseLineArray[i] < 0) {
                return;
            }
            this.lineArray[i] = this.getParameter("lineDefault");
            if (this.lineArray[i] == null) {
                this.lineArray[i] = "";
            }
            this.sizeArray[i] = this.getNumber("sizeDefault");
            if (this.sizeArray[i] < 0) {
                return;
            }
            this.color = this.getColor("colorDefault");
            if (this.color == null) {
                return;
            }
            this.colorArray[i] = this.color;
            this.tempInt = this.getFont("fontDefault");
            if (this.tempInt < 0) {
                return;
            }
            this.fontArray[i] = this.fontArray2[this.tempInt];
            this.tempInt = this.getStyle("styleDefault");
            if (this.tempInt < 0) {
                return;
            }
            this.styleArray[i] = this.StyleMap[this.tempInt];
            this.linkArray[i] = this.getParameter("linkDefault");
            if (this.linkArray[i] == null) {
                this.linkArray[i] = "";
            }
            else {
                this.linkArray[i] = this.linkArray[i].trim();
            }
            if (this.linkArray[i].compareTo("") != 0) {
                this.link = true;
            }
            this.lineOffsetArray[i] = this.getNumber("lineOffsetDefault");
            if (this.lineOffsetArray[i] < 0) {
                this.lineOffsetArray[i] = 0;
            }
        }
        if (this.insertFile() < 0) {
            return;
        }
        for (int j = 0; j < this.lines; ++j) {
            this.tempInt = this.getNumber("lineOffset" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.lineOffsetArray[j] = this.tempInt;
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.tempInt = this.getNumber("pauseValue" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.pauseLineArray[j] = this.tempInt;
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.tempInt = this.getNumber("size" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.sizeArray[j] = this.tempInt;
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.color = this.getColor("color" + Integer.toString(j + 1));
            if (this.color != null) {
                this.colorArray[j] = this.color;
            }
            this.tempInt = this.getFont("font" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.fontArray[j] = this.fontArray2[this.tempInt];
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.tempInt = this.getStyle("style" + Integer.toString(j + 1));
            if (this.tempInt >= 0) {
                this.styleArray[j] = this.StyleMap[this.tempInt];
            }
            else if (this.tempInt < -1) {
                return;
            }
            this.temp = this.getParameter("link" + Integer.toString(j + 1));
            if (this.temp != null) {
                this.linkArray[j] = this.temp;
                this.link = true;
            }
            this.fontArray3[j] = new Font(this.fontArray[j], this.styleArray[j], this.sizeArray[j]);
            this.wrapLine = true;
            this.temp = this.getParameter("line" + Integer.toString(j + 1));
            if (this.temp != null) {
                this.lineArray[j] = this.temp;
            }
            else if (this.textStart > -1 && j >= this.textStart && j <= this.textEnd) {
                this.wrapLine = false;
            }
            this.insertLineIntoVector(this.lineArray[j], j, this.wrapLine);
        }
        this.startLines = this.size().height;
        this.totLines = this.lineSpace * this.lineVector.size();
        this.height = this.size().height * 2 + this.totLines;
        this.offscreen = this.createImage(this.size().width, this.height);
        (this.offscreeng = this.offscreen.getGraphics()).setColor(this.getBackground());
        this.offscreeng.fillRect(0, 0, this.size().width, this.height);
        this.lines = this.lineVector.size();
        for (int k = 0; k < this.lineVector.size(); ++k) {
            final int intValue = this.mapVector.elementAt(k).elementAt(0);
            this.fm = this.offscreeng.getFontMetrics();
            this.offscreeng.setColor(this.colorArray[intValue]);
            this.fontArray3[intValue] = new Font(this.fontArray[intValue], this.styleArray[intValue], this.sizeArray[intValue]);
            this.offscreeng.setFont(this.fontArray3[intValue]);
            String parameter = this.lineVector.elementAt(k);
            if (parameter == null) {
                parameter = this.getParameter("lineDefault");
                if (parameter == null) {
                    parameter = "";
                }
            }
            this.offscreeng.drawString(parameter, this.lineOffsetArray[intValue], this.startLines + this.lineSpace * (k + 1));
        }
        this.checkError = false;
        this.startTime = System.currentTimeMillis();
    }
    
    int insertFile() {
        final int n = this.lines - 1;
        this.temp = this.getParameter("insertTextFile");
        if (this.temp == null || this.fileName == null) {
            return 0;
        }
        final int index = this.temp.indexOf("-");
        if (index >= 0) {
            final String substring = this.temp.substring(0, index);
            int textStart;
            try {
                textStart = Integer.valueOf(substring) - 1;
            }
            catch (NumberFormatException ex) {
                this.error = "error: insertTextFile value=\"" + this.temp + "\"";
                return -1;
            }
            final String substring2 = this.temp.substring(index + 1, this.temp.length());
            int n2;
            try {
                n2 = Integer.valueOf(substring2) - 1;
            }
            catch (NumberFormatException ex2) {
                this.error = "error: insertTextFile value=\"" + this.temp + "\"";
                return -1;
            }
            if (textStart > n || n2 > n || textStart > n2) {
                this.error = "error: insertTextFile value=\"" + this.temp + "\"";
                return -1;
            }
            final Enumeration elements = this.v.elements();
            this.textStart = textStart;
            while (elements.hasMoreElements() && textStart <= n2) {
                this.lineArray[textStart] = elements.nextElement();
                ++textStart;
            }
            this.textEnd = textStart - 1;
        }
        else {
            int textStart2;
            try {
                textStart2 = Integer.valueOf(this.temp) - 1;
            }
            catch (NumberFormatException ex3) {
                this.error = "error: insertTextFile value=\"" + this.temp + "\"";
                return -1;
            }
            final Enumeration<String> elements2 = this.v.elements();
            this.textStart = textStart2;
            while (elements2.hasMoreElements()) {
                if (textStart2 > n || textStart2 < 0) {
                    this.error = "error: insertTextFile value=\"" + this.getNumber("insertTextFile") + "\"";
                    return -1;
                }
                this.lineArray[textStart2] = elements2.nextElement();
                ++textStart2;
            }
            this.textEnd = textStart2 - 1;
        }
        return 0;
    }
    
    void insertLineIntoVector(final String s, final int n, final boolean b) {
        final Vector<String> vector = new Vector<String>(10);
        int vectorLineNumber = this.vectorLineNumber;
        this.w = this.size().width;
        this.fontMetrics = this.getFontMetrics(this.fontArray3[n]);
        final Vector<Integer> vector2 = new Vector<Integer>(10);
        this.offset = this.lineOffsetArray[n];
        if (b) {
            this.wordWrap(s, vector);
        }
        else {
            vector.addElement(s);
        }
        vector2.addElement(Integer.valueOf(Integer.toString(n)));
        for (int i = 0; i < vector.size(); ++i) {
            vector2.addElement(Integer.valueOf(Integer.toString(vectorLineNumber++)));
        }
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            this.lineVector.addElement(elements.nextElement());
            this.mapVector.addElement(vector2);
            ++this.vectorLineNumber;
        }
    }
    
    void jumpToUrl(String string) {
        try {
            if (!string.startsWith("http") && !string.startsWith("ftp") && !string.startsWith("mailto:")) {
                string = String.valueOf(this.path) + string;
            }
            this.getAppletContext().showDocument(new URL(string), this.linkFrame);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
    
    void manualPause() {
        if (this.downTime == 0L) {
            return;
        }
        this.diffTime = System.currentTimeMillis() - this.downTime;
        if (this.diffTime < this.manualPause) {
            ++this.ypos;
        }
        else {
            this.downTime = 0L;
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.stopPause = true;
        if (this.downTime == 0L) {
            this.downTime = System.currentTimeMillis();
        }
        else {
            this.downTime = 0L;
        }
        this.run = false;
        this.adjYpos = this.startLines + this.ypos;
        this.diff = n2 - this.adjYpos;
        if (n2 < this.adjYpos || n2 > this.adjYpos + this.totLines - 1) {
            return true;
        }
        final int passLine = this.diff / this.lineSpace;
        this.lineDown = this.lineSpace * passLine;
        this.lineDown += this.startLines;
        final int intValue = this.mapVector.elementAt(passLine).elements().nextElement();
        if (intValue >= this.linkArray.length || this.linkArray[intValue].equals("")) {
            this.lineDown = 0;
            return true;
        }
        this.showStatus(this.status = this.linkArray[intValue]);
        if (event.clickCount == this.linkClick) {
            this.clicked = true;
            this.highliteLines(this.passLine = passLine, 2);
            this.getGraphics().drawImage(this.offscreen, 0, this.ypos, this);
            this.jumpToUrl(this.linkArray[intValue]);
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.stopPause = true;
        final int n3 = this.startLines + this.ypos;
        this.ypos = n2 - this.startLines - this.diff;
        if (this.ypos < -(this.startLines + this.lineSpace * this.lines)) {
            this.ypos = this.size().height - this.startLines;
        }
        if (this.ypos > this.size().height - this.startLines) {
            this.ypos = this.size().height - this.startLines;
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.mouseexit = false;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseexit = true;
        if (!this.run) {
            return true;
        }
        if (this.lastLink >= 0) {
            this.highliteLines(this.passLine = this.lastLink, 1);
            this.lastLink = -1;
            this.repaint();
            this.showStatus(this.status = "");
        }
        this.lineSelect = -1;
        this.lastLink = -1;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.stopPause = false;
        if (this.clicked) {
            this.highliteLines(this.passLine = this.lineSelect, 0);
            this.repaint();
            this.clicked = false;
        }
        this.lineDown = 0;
        this.pause(200);
        return this.run = true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.checkError) {
            if (this.once) {
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, this.size().width, this.size().height);
                this.once = false;
            }
            graphics.setFont(this.f = new Font("Dialog", 0, 12));
            graphics.setColor(Color.black);
            graphics.drawString(this.error, 0, 20);
            if (!this.allowed) {
                this.showStatus(this.status = "Unregistered: Contact support@consultcom.com");
            }
        }
        else {
            graphics.drawImage(this.offscreen, 0, this.ypos, this);
        }
    }
    
    void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    void pauseit(final int n) {
        final long currentTimeMillis = System.currentTimeMillis();
        long n2 = currentTimeMillis + n;
        while (n2 > currentTimeMillis) {
            this.pause(10);
            n2 -= 10L;
            if (this.stopPause) {
                this.stopPause = false;
                break;
            }
        }
    }
    
    public void run() {
        while (this.thread.isAlive()) {
            this.startTime = System.currentTimeMillis();
            if (this.run) {
                if (this.ypos < -(this.startLines + this.lineSpace * this.lines)) {
                    this.ypos = this.size().height - this.startLines;
                }
                else {
                    --this.ypos;
                    this.manualPause();
                }
            }
            this.checkPause();
            this.repaint();
            this.highlight(this.mousex, this.mousey);
            if (System.currentTimeMillis() > this.displayStatus && this.allowed) {
                this.showStatus(this.status = this.slogan);
                this.displayStatus = System.currentTimeMillis() + this.nextDispl;
            }
            this.diffTime = System.currentTimeMillis() - this.startTime;
            if (this.diffTime < this.pauseValue) {
                this.pause(this.pauseValue - (int)this.diffTime);
            }
        }
    }
    
    boolean setBoolean(final String s) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return false;
        }
        this.z = parameter.compareTo("yes");
        return this.z == 0;
    }
    
    public void start() {
        if (this.thread == null) {
            this.thread = new Thread(this);
        }
        this.thread.start();
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
        }
        this.thread = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void wordWrap(final String s, final Vector vector) {
        String string = "";
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (string.equals("")) {
                string = nextToken;
            }
            else if (this.fontMetrics.stringWidth(String.valueOf(string) + " " + nextToken) < this.w - this.offset) {
                string = String.valueOf(string) + " " + nextToken;
            }
            else {
                vector.addElement(string);
                string = nextToken;
            }
        }
        vector.addElement(string);
    }
}
