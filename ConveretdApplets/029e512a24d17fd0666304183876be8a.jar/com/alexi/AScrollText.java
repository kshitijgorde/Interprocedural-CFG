// 
// Decompiled by Procyon v0.5.30
// 

package com.alexi;

import java.io.IOException;
import java.io.DataInputStream;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

public class AScrollText extends Applet implements MouseMotionListener, MouseListener, Runnable
{
    Font m_font;
    String m_str;
    String m_strSpecialChars;
    Thread thread;
    int xPos;
    private boolean bStartFromBottom;
    public Image m_offImage;
    public Graphics m_offGraphics;
    private int yStart;
    private int savedY;
    private boolean motionStopped;
    protected String bodyTag;
    protected String underlineTag;
    protected String boldTag;
    protected String italicTag;
    protected String fontTag;
    protected String paragraphTag;
    protected String lineBreakTag;
    protected String imageTag;
    protected String urlTag;
    protected String titleTag;
    protected String headTag;
    protected String metaTag;
    protected String centerTag;
    protected Font currentFont;
    protected AScrollTextDisplayer theScrollTextDisplayer;
    protected int m_currentIndex;
    protected int m_lastMouseXPos;
    protected int m_lastMouseYPos;
    protected Color backColor;
    protected Color foreColor;
    protected Color m_linkColor;
    public static Color m_linkFocusColor;
    public static boolean m_bUnderlineLinks;
    protected int m_linkIndex;
    protected int m_height;
    protected Cursor m_handCursor;
    protected Cursor m_normalCursor;
    protected int m_initialDelay;
    protected int m_smallSleep;
    protected int m_scrollDelta;
    private String m_userName;
    private int m_userKey;
    protected String m_strHTMLFile;
    protected String m_strLinkTarget;
    protected String m_strSpecialsURL;
    protected boolean m_bAddLinesAfter;
    protected int m_nParagraphSleep;
    private String m_strLoadText;
    protected boolean m_bTextLoaded;
    protected int m_nReloadCycle;
    protected long m_lReloadTime;
    protected long m_lLastReloadTime;
    
    static {
        AScrollText.m_linkFocusColor = null;
        AScrollText.m_bUnderlineLinks = true;
    }
    
    protected AHTMLTag extractHTMLTag() {
        int n = 0;
        String substring = null;
        final AHTMLTag ahtmlTag = new AHTMLTag();
        ++this.m_currentIndex;
        final int currentIndex = this.m_currentIndex;
        while (this.m_str.charAt(this.m_currentIndex) != ' ' && this.m_str.charAt(this.m_currentIndex) != '>') {
            ++this.m_currentIndex;
        }
        final int currentIndex2 = this.m_currentIndex;
        ahtmlTag.setName(this.m_str.substring(currentIndex, currentIndex2));
        int n2 = currentIndex2;
        while (this.m_str.charAt(this.m_currentIndex) != '>') {
            if (n == 0) {
                if (this.m_str.charAt(this.m_currentIndex) == ' ') {
                    ++n2;
                }
                if (this.m_str.charAt(this.m_currentIndex) == '=') {
                    substring = this.m_str.substring(n2, this.m_currentIndex);
                    n = 1;
                    n2 = this.m_currentIndex + 1;
                }
            }
            else if (this.m_str.charAt(this.m_currentIndex) == ' ') {
                ahtmlTag.addAttribute(substring, this.m_str.substring(n2, this.m_currentIndex));
                n = 0;
                n2 = this.m_currentIndex + 1;
            }
            ++this.m_currentIndex;
        }
        if (n == 1) {
            ahtmlTag.addAttribute(substring, this.m_str.substring(n2, this.m_currentIndex));
        }
        ++this.m_currentIndex;
        return ahtmlTag;
    }
    
    public String getAppletInfo() {
        return "AXScroller\n\nHigh performance vertical scroller.\nCreation date: (2/14/00 3:31:51 PM)\n@author: \nAlexandru Indrei";
    }
    
    protected Color getColorFromString(final String s) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        if (s.charAt(0) == '#') {
            return Color.decode(s);
        }
        if (s.equalsIgnoreCase("red")) {
            n = 255;
        }
        if (s.equalsIgnoreCase("green")) {
            n2 = 255;
        }
        if (s.equalsIgnoreCase("blue")) {
            n3 = 255;
        }
        s.equalsIgnoreCase("black");
        if (s.equalsIgnoreCase("white")) {
            n3 = 255;
            n = 255;
            n2 = 255;
        }
        if (s.equalsIgnoreCase("gray")) {
            n3 = 127;
            n = 127;
            n2 = 127;
        }
        if (s.equalsIgnoreCase("yellow")) {
            n = 255;
            n2 = 255;
        }
        if (s.equalsIgnoreCase("magenta")) {
            n = 255;
            n3 = 255;
        }
        return new Color(n, n2, n3);
    }
    
    protected AScrollTextDisplayer getDisplayer() {
        if (this.theScrollTextDisplayer == null) {
            this.theScrollTextDisplayer = new AScrollTextDisplayer(this.getGraphics(), this.getSize().width);
        }
        return this.theScrollTextDisplayer;
    }
    
    protected int getFontExactSize(final int n) {
        switch (n) {
            case 1: {
                return 8;
            }
            case 2: {
                return 10;
            }
            case 3: {
                return 12;
            }
            case 4: {
                return 14;
            }
            case 5: {
                return 18;
            }
            case 6: {
                return 24;
            }
            case 7: {
                return 36;
            }
            default: {
                return 12;
            }
        }
    }
    
    protected String getFontFromHTML(final String s) {
        String s2;
        if (s.indexOf("arial") != -1 || s.indexOf("helvetica") != -1) {
            s2 = new String("SansSerif");
        }
        else if (s.indexOf("times") != -1) {
            s2 = new String("Serif");
        }
        else if (s.indexOf("courier") != -1) {
            s2 = new String("Monospaced");
        }
        else if (s.equalsIgnoreCase("Serif")) {
            s2 = new String("Serif");
        }
        else {
            s2 = new String(s);
        }
        return s2;
    }
    
    protected int getIntFromString(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            int1 = 0;
        }
        return int1;
    }
    
    protected char getSpecialChar(final String s) {
        if (this.m_strSpecialChars == null) {
            this.m_strSpecialChars = this.readFileInString(this.m_strSpecialsURL, false);
        }
        final int index = this.m_strSpecialChars.indexOf(s);
        if (index == -1) {
            return 'a';
        }
        return this.m_strSpecialChars.charAt(index + s.length() + 1);
    }
    
    public void init() {
        super.init();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.loadFromAppletParams();
    }
    
    protected void loadApplet() {
        this.m_currentIndex = 0;
        this.yStart = 0;
        this.theScrollTextDisplayer = null;
        this.m_str = this.readFileInString(this.m_strHTMLFile, true);
        this.parseHTMLTag(this.m_font, null, null, null, 0);
        if (this.verifyLicense() == 0) {
            this.getDisplayer().goToNextLine();
            this.getDisplayer().addFormattedText("Visit AXScroll 3.0 homepage", this.getFont(), new Color(255, 255, 255), "http://alexi.dreamhost.com", null, 0);
        }
        this.m_height = this.getDisplayer().getHeight();
        if (this.m_height < this.getSize().height || this.m_bAddLinesAfter) {
            this.m_height += this.getSize().height - this.m_height % this.getSize().height;
        }
        this.m_offImage = this.createImage(this.getSize().width, this.m_height);
        this.m_offGraphics = this.m_offImage.getGraphics();
        this.getDisplayer().backColor = this.backColor;
        this.getDisplayer().drawAll(this.m_offGraphics, this.getSize().width, this.m_height);
        this.m_bTextLoaded = true;
        System.gc();
    }
    
    protected void loadFromAppletParams() {
        final String parameter = this.getParameter("htmlSourceFile");
        if (parameter != null) {
            this.m_strHTMLFile = parameter;
        }
        final String parameter2 = this.getParameter("initialDelay");
        if (parameter2 != null) {
            this.m_initialDelay = this.getIntFromString(parameter2);
        }
        final String parameter3 = this.getParameter("normalDelay");
        if (parameter3 != null && this.getIntFromString(parameter3) >= 0) {
            this.m_smallSleep = this.getIntFromString(parameter3);
        }
        final String parameter4 = this.getParameter("scrollStep");
        if (parameter4 != null && this.getIntFromString(parameter4) >= 0) {
            this.m_scrollDelta = this.getIntFromString(parameter4);
        }
        final String parameter5 = this.getParameter("userName");
        if (parameter5 != null) {
            this.m_userName = new String(parameter5);
        }
        final String parameter6 = this.getParameter("userKey");
        if (parameter6 != null && this.getIntFromString(parameter6) >= 0) {
            this.m_userKey = this.getIntFromString(parameter6);
        }
        String parameter7 = this.getParameter("linkColor");
        if (parameter7 == null) {
            parameter7 = new String("blue");
        }
        this.m_linkColor = this.getColorFromString(parameter7);
        String parameter8 = this.getParameter("linkFocusColor");
        if (parameter8 == null) {
            parameter8 = new String("red");
        }
        AScrollText.m_linkFocusColor = this.getColorFromString(parameter8);
        final String parameter9 = this.getParameter("underlineLinks");
        if (parameter9 != null && this.getIntFromString(parameter9) >= 0) {
            AScrollText.m_bUnderlineLinks = (this.getIntFromString(parameter9) == 1);
        }
        final String parameter10 = this.getParameter("linkTarget");
        if (parameter10 != null) {
            this.m_strLinkTarget = parameter10;
        }
        else {
            this.m_strLinkTarget = "_parent";
        }
        final String parameter11 = this.getParameter("addLinesAfter");
        if (parameter11 != null && this.getIntFromString(parameter11) >= 0) {
            this.m_bAddLinesAfter = (this.getIntFromString(parameter11) == 1);
        }
        final String parameter12 = this.getParameter("specialCharsURL");
        if (parameter12 != null && parameter12.length() > 0) {
            this.m_strSpecialsURL = new String(parameter12);
        }
        final String parameter13 = this.getParameter("paragraphDelay");
        if (parameter13 != null && this.getIntFromString(parameter13) >= 0) {
            this.m_nParagraphSleep = this.getIntFromString(parameter13);
        }
        final String parameter14 = this.getParameter("loadingTextDisplay");
        if (parameter14 != null && parameter14.length() > 0) {
            this.m_strLoadText = new String(parameter14);
        }
        final String parameter15 = this.getParameter("reloadCycle");
        if (parameter15 != null && this.getIntFromString(parameter15) >= 0) {
            this.m_nReloadCycle = this.getIntFromString(parameter15);
        }
        final String parameter16 = this.getParameter("reloadTime");
        if (parameter16 != null && this.getIntFromString(parameter16) >= 0) {
            this.m_lReloadTime = this.getIntFromString(parameter16);
        }
    }
    
    protected Image loadImage(final String s) {
        try {
            this.showStatus(String.valueOf(new String(" Loading image ")) + s);
            final Image image = this.getImage(this.getCodeBase(), s);
            if (image != null) {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(image, 0);
                try {
                    mediaTracker.waitForID(0);
                }
                catch (InterruptedException ex2) {}
            }
            return image;
        }
        catch (Exception ex) {
            System.err.println("Exception " + ex + " occurred when trying to load image " + s);
            return null;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        mouseEvent.getX();
        mouseEvent.getY();
        String url = null;
        if (this.m_linkIndex != -1) {
            url = this.getDisplayer().getURL(this.m_linkIndex);
        }
        String s = this.getDisplayer().getURLTarget(this.m_linkIndex);
        if (s == null) {
            s = this.m_strLinkTarget;
        }
        if (url != null) {
            this.navigateURL(url, s);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int y = mouseEvent.getY();
        this.yStart += y - this.savedY;
        if (this.yStart > 0 || this.yStart + this.m_height <= 0) {
            this.yStart = 0;
        }
        this.savedY = y;
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.motionStopped = true;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.m_lastMouseXPos = -1;
        this.m_lastMouseYPos = -1;
        this.motionStopped = false;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.m_lastMouseXPos = mouseEvent.getX();
        this.m_lastMouseYPos = mouseEvent.getY();
        this.motionStopped = true;
        if (this.m_offGraphics == null) {
            return;
        }
        int linkIndex;
        if (this.yStart + this.m_height > this.m_lastMouseYPos) {
            linkIndex = this.getDisplayer().updateLinks(this.m_offGraphics, this.m_lastMouseXPos, this.m_lastMouseYPos - this.yStart, this.m_linkIndex);
        }
        else {
            linkIndex = this.getDisplayer().updateLinks(this.m_offGraphics, this.m_lastMouseXPos, this.m_lastMouseYPos - this.yStart - this.m_height, this.m_linkIndex);
        }
        if (this.m_linkIndex != linkIndex) {
            this.m_linkIndex = linkIndex;
            if (this.m_linkIndex != -1) {
                this.setCursor(this.m_handCursor);
                this.showStatus(this.getDisplayer().getURL(this.m_linkIndex));
            }
            else {
                this.setCursor(this.m_normalCursor);
                this.showStatus(null);
            }
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.savedY = mouseEvent.getY();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    protected void navigateURL(final String s, final String s2) {
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            try {
                url = new URL(this.getCodeBase(), s);
            }
            catch (MalformedURLException ex2) {
                System.err.println("Bad url: " + s);
            }
        }
        if (url != null) {
            this.getAppletContext().showDocument(url, s2);
        }
    }
    
    protected void normalSleep(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_bTextLoaded) {
            graphics.drawImage(this.m_offImage, 0, this.yStart, this);
            if (this.yStart + this.m_height < this.getSize().height) {
                graphics.drawImage(this.m_offImage, 0, this.yStart + this.m_height, this);
            }
        }
        else if (this.m_strLoadText != null) {
            graphics.drawString(this.m_strLoadText, 10, 20);
        }
    }
    
    protected void parseHTMLTag(final Font font, final Color color, final String s, final String s2, final int n) {
        Font font2 = null;
        Color color2 = null;
        String attributeValue = null;
        if (s != null) {
            attributeValue = new String(s);
        }
        String attributeValue2 = null;
        if (s2 != null) {
            attributeValue2 = new String(s2);
        }
        boolean b = true;
        int n2 = n;
        final String name = font.getName();
        final int size = font.getSize();
        final int style = font.getStyle();
        final int length = this.m_str.length();
        if (color != null) {
            color2 = new Color(color.getRGB());
        }
        this.m_str.substring(this.m_currentIndex);
        final AHTMLTag htmlTag = this.extractHTMLTag();
        if (htmlTag != null) {
            if (htmlTag.is(this.paragraphTag) || htmlTag.is(this.lineBreakTag)) {
                this.getDisplayer().goToNextLine();
                if (htmlTag.is(this.paragraphTag)) {
                    this.getDisplayer().addParagraphMark();
                }
                return;
            }
            if (htmlTag.is(this.titleTag) || htmlTag.is(this.headTag)) {
                b = false;
            }
            if (htmlTag.is(this.centerTag)) {
                n2 = 1;
            }
            if (htmlTag.is(this.bodyTag)) {
                for (int i = 0; i < htmlTag.getNoOfAttributes(); ++i) {
                    if (htmlTag.getAttributeName(i).equalsIgnoreCase("bgcolor")) {
                        this.backColor = this.getColorFromString(htmlTag.getAttributeValue(i));
                    }
                    if (htmlTag.getAttributeName(i).equalsIgnoreCase("text")) {
                        color2 = this.getColorFromString(htmlTag.getAttributeValue(i));
                    }
                }
                font2 = new Font(name, style, size);
            }
            else if (htmlTag.is(this.boldTag)) {
                font2 = new Font(name, style | 0x1, size);
            }
            else if (htmlTag.is(this.underlineTag)) {
                font2 = new Font(name, style, size);
            }
            else if (htmlTag.is(this.italicTag)) {
                font2 = new Font(name, style | 0x2, size);
            }
            else if (htmlTag.is(this.fontTag)) {
                String fontFromHTML = name;
                int fontExactSize = size;
                for (int j = 0; j < htmlTag.getNoOfAttributes(); ++j) {
                    if (htmlTag.getAttributeName(j).equalsIgnoreCase("face")) {
                        fontFromHTML = this.getFontFromHTML(htmlTag.getAttributeValue(j));
                    }
                    if (htmlTag.getAttributeName(j).equalsIgnoreCase("size")) {
                        final String attributeValue3 = htmlTag.getAttributeValue(j);
                        int n3 = 0;
                        final char char1 = attributeValue3.charAt(n3++);
                        char c = '\u0003';
                        int n4 = 1;
                        if (char1 == '-') {
                            n4 = -1;
                        }
                        else if (char1 == '+') {
                            n4 = 1;
                        }
                        else {
                            c = '\0';
                            --n3;
                        }
                        fontExactSize = this.getFontExactSize(c + n4 * (attributeValue3.charAt(n3) - '0'));
                    }
                    if (htmlTag.getAttributeName(j).equalsIgnoreCase("color")) {
                        color2 = this.getColorFromString(htmlTag.getAttributeValue(j));
                    }
                }
                font2 = new Font(fontFromHTML, style, fontExactSize);
            }
            else {
                if (htmlTag.is(this.imageTag)) {
                    String attributeValue4 = null;
                    int intValue = 0;
                    int intValue2 = 0;
                    for (int k = 0; k < htmlTag.getNoOfAttributes(); ++k) {
                        if (htmlTag.getAttributeName(k).equalsIgnoreCase("src")) {
                            attributeValue4 = htmlTag.getAttributeValue(k);
                        }
                        if (htmlTag.getAttributeName(k).equalsIgnoreCase("width")) {
                            intValue = new Integer(htmlTag.getAttributeValue(k));
                        }
                        if (htmlTag.getAttributeName(k).equalsIgnoreCase("height")) {
                            intValue2 = new Integer(htmlTag.getAttributeValue(k));
                        }
                    }
                    if (attributeValue4 != null) {
                        this.getDisplayer().addImage(this.loadImage(attributeValue4), intValue, intValue2, attributeValue, attributeValue2, n2);
                    }
                    return;
                }
                if (htmlTag.is(this.metaTag)) {
                    return;
                }
                if (htmlTag.is(this.urlTag)) {
                    for (int l = 0; l < htmlTag.getNoOfAttributes(); ++l) {
                        if (htmlTag.getAttributeName(l).equalsIgnoreCase("href")) {
                            attributeValue = htmlTag.getAttributeValue(l);
                        }
                        if (htmlTag.getAttributeName(l).equalsIgnoreCase("target")) {
                            attributeValue2 = htmlTag.getAttributeValue(l);
                        }
                    }
                    font2 = new Font(name, style, size);
                    if (this.m_linkColor != null) {
                        color2 = this.m_linkColor;
                    }
                }
                else {
                    font2 = new Font(name, style, size);
                }
            }
        }
        String string = "";
        while (this.m_currentIndex < length) {
            boolean b2 = false;
            char c2 = this.m_str.charAt(this.m_currentIndex);
            if (c2 == '&') {
                final int currentIndex = this.m_currentIndex;
                while (this.m_str.charAt(++this.m_currentIndex) != ';') {}
                c2 = this.getSpecialChar(this.m_str.substring(currentIndex, this.m_currentIndex + 1));
                if (c2 == 'a') {
                    ++this.m_currentIndex;
                    continue;
                }
                b2 = true;
            }
            if (c2 == '<' && !b2) {
                if (string.length() > 0 && b) {
                    this.getDisplayer().addFormattedText(string, font2, color2, attributeValue, attributeValue2, n2);
                }
                if (this.m_str.charAt(this.m_currentIndex + 1) == '/') {
                    ++this.m_currentIndex;
                    for (char c3 = this.m_str.charAt(this.m_currentIndex); this.m_currentIndex < length && c3 != '>'; c3 = this.m_str.charAt(this.m_currentIndex)) {
                        ++this.m_currentIndex;
                    }
                    if (this.m_currentIndex < length) {
                        ++this.m_currentIndex;
                    }
                    return;
                }
                this.parseHTMLTag(font2, color2, attributeValue, attributeValue2, n2);
                string = "";
            }
            else {
                string = String.valueOf(string) + c2;
                ++this.m_currentIndex;
            }
        }
        if (string.length() > 0 && b) {
            this.getDisplayer().addFormattedText(string, font2, color2, attributeValue, attributeValue2, n2);
        }
    }
    
    protected String readFileInString(final String s, final boolean b) {
        URL url = null;
        String s2 = new String();
        try {
            url = new URL(s);
        }
        catch (Exception ex) {
            try {
                url = new URL(this.getDocumentBase(), s);
            }
            catch (Exception ex2) {
                System.out.println("Error Reading file");
            }
        }
        try {
            final DataInputStream dataInputStream = new DataInputStream(url.openStream());
            int char1 = 62;
            String s3;
            while ((s3 = dataInputStream.readLine()) != null) {
                if (b) {
                    s3 = s3.trim();
                }
                if (char1 != 62 || s3.length() == 0 || s3.charAt(0) != '<') {
                    s2 = String.valueOf(s2) + " ";
                }
                s2 = String.valueOf(s2) + s3;
                if (s3.length() > 0) {
                    char1 = s3.charAt(s3.length() - 1);
                }
            }
            dataInputStream.close();
        }
        catch (IOException ex3) {
            System.out.println("Error Reading Special chars file");
        }
        return s2;
    }
    
    public void run() {
        this.repaint();
        if (!this.m_bTextLoaded) {
            this.loadApplet();
            this.repaint();
            this.m_lLastReloadTime = System.currentTimeMillis();
        }
        int n = 0;
        int n2 = this.m_initialDelay;
        while (true) {
            if (!this.motionStopped) {
                this.yStart -= this.m_scrollDelta;
                if (this.yStart + this.m_height <= 0) {
                    this.yStart = 0;
                    ++n;
                    if (this.m_nReloadCycle > 0 && n >= this.m_nReloadCycle) {
                        this.loadApplet();
                        n = 0;
                    }
                }
                if (this.m_nParagraphSleep > 0 && this.getDisplayer().isParagraphStarted(-this.yStart)) {
                    this.smartSleep(this.m_nParagraphSleep);
                }
                if (this.m_lReloadTime > 0L) {
                    final long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.m_lLastReloadTime >= this.m_lReloadTime) {
                        this.loadApplet();
                        this.m_lLastReloadTime = currentTimeMillis;
                    }
                }
                this.repaint();
            }
            this.smartSleep(n2);
            n2 = this.m_smallSleep;
        }
    }
    
    protected void smartSleep(final int n) {
        if (n < 10) {
            this.normalSleep(n);
            return;
        }
        for (long currentTimeMillis = System.currentTimeMillis(), n2 = currentTimeMillis + n; n2 > currentTimeMillis; n2 -= 10L) {
            this.normalSleep(10);
        }
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private int verifyLicense() {
        if (this.m_userName == null) {
            return 0;
        }
        char c = '\0';
        for (int i = 0; i < this.m_userName.length(); ++i) {
            c += this.m_userName.charAt(i);
        }
        if (c % '\u014d' * '\u0002' - '\u0001' == this.m_userKey) {
            return 1;
        }
        return 0;
    }
    
    public AScrollText() {
        this.m_font = new Font("Serif", 0, 14);
        this.m_str = "";
        this.m_strSpecialChars = null;
        this.xPos = 5;
        this.bStartFromBottom = true;
        this.m_offImage = null;
        this.m_offGraphics = null;
        this.yStart = 0;
        this.savedY = 0;
        this.motionStopped = false;
        this.bodyTag = "body";
        this.underlineTag = "u";
        this.boldTag = "b";
        this.italicTag = "i";
        this.fontTag = "font";
        this.paragraphTag = "p";
        this.lineBreakTag = "br";
        this.imageTag = "img";
        this.urlTag = "a";
        this.titleTag = "title";
        this.headTag = "head";
        this.metaTag = "meta";
        this.centerTag = "center";
        this.currentFont = null;
        this.theScrollTextDisplayer = null;
        this.m_currentIndex = 0;
        this.m_lastMouseXPos = -1;
        this.m_lastMouseYPos = -1;
        this.backColor = null;
        this.foreColor = null;
        this.m_linkColor = null;
        this.m_linkIndex = -1;
        this.m_height = 0;
        this.m_handCursor = new Cursor(12);
        this.m_normalCursor = new Cursor(0);
        this.m_initialDelay = 500;
        this.m_smallSleep = 20;
        this.m_scrollDelta = 1;
        this.m_userName = null;
        this.m_userKey = 0;
        this.m_strHTMLFile = null;
        this.m_strLinkTarget = null;
        this.m_strSpecialsURL = "Specials.txt";
        this.m_bAddLinesAfter = true;
        this.m_nParagraphSleep = 0;
        this.m_strLoadText = null;
        this.m_bTextLoaded = false;
        this.m_nReloadCycle = 0;
        this.m_lReloadTime = 0L;
        this.m_lLastReloadTime = 0L;
    }
}
