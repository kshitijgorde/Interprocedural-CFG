import java.awt.Frame;
import java.awt.Event;
import java.io.IOException;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.util.Date;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Marquee extends Applet implements Runnable
{
    private static final String version = "2.4";
    private boolean demoMode;
    private boolean pirated;
    private String host;
    private Vector regEntry;
    private static final boolean debugMode = false;
    private static final boolean expires = false;
    private static final int monthCompiled = 2;
    private static final int dayCompiled = 2;
    private static final int yearCompiled = 100;
    private static final int daysToRun = 30;
    private static final Date dateCompiled;
    private static final Date dateExpires;
    private static final int arrayLength = 20;
    private Graphics g;
    private Graphics offScrGC;
    private Image background;
    private Image offScrImage;
    private Image[] images;
    private int messageIndex;
    private int messageCount;
    private String[] messages;
    private String[] default_messages;
    private String[][] multiMessages;
    private int[] multiCount;
    private String[] theHost;
    private boolean[] multiFlag;
    private String[] font_type;
    private int[] font_style;
    private char[] align;
    private int[] mode;
    private int mode0;
    private int mode1;
    private int mode2;
    private int mode3;
    private int[] font_size;
    private int[] y_pos;
    private int[] default_y_pos;
    private int[] default_x_pos;
    private int[] msgWidth;
    private int[] longLineWidth;
    private int[] msgHeight;
    private int[] lineHeight;
    private int[] imageW;
    private int[] imageH;
    private int[] textW;
    private int[] textH;
    private Font[] myFont;
    private Font[] default_myFont;
    private int[] textColorRed;
    private int[] textColorGreen;
    private int[] textColorBlue;
    private int[] scrollIn;
    private int[] scrollPause;
    private int[] scrollOut;
    private Color[] textColor;
    private Color[] default_textColor;
    private int[] deltaRed;
    private int[] deltaGreen;
    private int[] deltaBlue;
    private int fadeCount;
    private int[] delay;
    private int[] typewriterDelay;
    private int[] fadeFrames;
    private boolean[] dropShadow;
    private int[] shadowXOffset;
    private int[] shadowYOffset;
    private Color[] shadowColor;
    private boolean[] bannermode;
    private String[] myURL;
    private URL[] theURL;
    private String target;
    private AudioClip[] soundClip;
    private boolean loadingURL;
    private int appletWidth;
    private int appletHeight;
    private int winColorRed;
    private int winColorGreen;
    private int winColorBlue;
    private int x_pos;
    private static final int offset = 50;
    private static final int spacer = 10;
    private Thread myThread;
    private Color winColor;
    private boolean started;
    private boolean image_toggle;
    private String backgroundImage;
    private int scrollStyle;
    private long time;
    
    public void init() {
        this.host = this.getDocumentBase().getHost().toLowerCase();
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.appletWidth = this.size().width;
        this.appletHeight = this.size().height;
        if (this.appletWidth == 0) {
            final String parameter = this.getParameter("WIDTH");
            if (parameter != null) {
                this.appletWidth = Integer.parseInt(parameter);
            }
        }
        if (this.appletHeight == 0) {
            final String parameter2 = this.getParameter("HEIGHT");
            if (parameter2 != null) {
                this.appletHeight = Integer.parseInt(parameter2);
            }
        }
        this.g = this.getGraphics();
        this.offScrImage = this.createImage(this.appletWidth, this.appletHeight);
        this.offScrGC = this.offScrImage.getGraphics();
        final String parameter3 = this.getParameter("REGISTRATION");
        if (parameter3 != null) {
            this.demoMode = false;
            final StringTokenizer stringTokenizer = new StringTokenizer(this.loadFile(parameter3), ";");
            while (stringTokenizer.hasMoreTokens()) {
                this.regEntry.addElement(stringTokenizer.nextToken());
            }
        }
        else {
            this.demoMode = true;
        }
        if (!this.demoMode) {
            this.pirated = true;
            final String hashValue = this.hashValue(this.host);
            if (this.host.equals("") || this.host.equals("localhost") || this.host.equals("127.0.0.1")) {
                this.pirated = false;
            }
            else {
                for (int i = 0; i <= this.regEntry.size() - 1; ++i) {
                    if (((String)this.regEntry.elementAt(i)).equals(hashValue)) {
                        this.pirated = false;
                    }
                }
            }
            if (this.pirated) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(this.host, ".");
                int n = 0;
                while (stringTokenizer2.hasMoreTokens()) {
                    this.theHost[n] = stringTokenizer2.nextToken();
                    ++n;
                }
                if (n >= 2) {
                    final String hashValue2 = this.hashValue(this.theHost[n - 2] + "." + this.theHost[n - 1]);
                    for (int j = 0; j <= this.regEntry.size() - 1; ++j) {
                        if (((String)this.regEntry.elementAt(j)).equals(hashValue2)) {
                            this.pirated = false;
                        }
                    }
                }
                else {
                    final String hashValue3 = this.hashValue(this.theHost[0]);
                    for (int k = 0; k <= this.regEntry.size() - 1; ++k) {
                        if (((String)this.regEntry.elementAt(k)).equals(hashValue3)) {
                            this.pirated = false;
                        }
                    }
                }
            }
        }
        System.out.println(this.getAppletInfo());
        if (this.pirated) {
            this.messages[0] = "Security Violation! Click on applet for product information.";
            this.messageCount = 0;
            this.messageIndex = 0;
            this.winColor = new Color(0, 0, 255);
            this.textColor[0] = new Color(255, 255, 0);
            this.default_textColor[0] = this.textColor[0];
            this.dropShadow[0] = true;
            this.shadowXOffset[0] = 2;
            this.shadowYOffset[0] = 2;
            this.shadowColor[0] = Color.black;
            this.mode[0] = 1;
            this.mode1 = 1;
            this.delay[0] = 20;
            this.soundClip[0] = null;
            this.images[0] = null;
            this.default_messages[0] = "";
            this.font_type[0] = "TimesRoman";
            this.font_style[0] = 1;
            this.font_size[0] = 18;
            this.myFont[0] = new Font("TimesRoman", this.font_style[0], this.font_size[0]);
            this.default_myFont[0] = this.myFont[0];
            final FontMetrics fontMetrics = this.g.getFontMetrics(this.myFont[0]);
            this.msgWidth[0] = fontMetrics.stringWidth(this.messages[0]) + 4;
            this.longLineWidth[0] = this.msgWidth[0];
            this.scrollIn[0] = 1;
            this.scrollPause[0] = 0;
            this.scrollOut[0] = 1;
            this.default_x_pos[0] = (this.appletWidth - this.msgWidth[0]) / 2;
            this.x_pos = this.appletWidth + 50;
            this.y_pos[0] = (this.appletHeight - fontMetrics.getAscent() - fontMetrics.getDescent()) / 2 + fontMetrics.getAscent();
            this.default_y_pos[0] = this.y_pos[0];
            this.target = null;
            this.myURL[0] = "http://www.triview.com/marquee-link.html";
            try {
                this.theURL[0] = new URL(this.myURL[0]);
                return;
            }
            catch (MalformedURLException ex) {
                this.theURL[0] = null;
                return;
            }
        }
        final String parameter4 = this.getParameter("MESSAGEFILE");
        if (parameter4 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(this.loadFile(parameter4), ";");
            int n2 = 0;
            while (stringTokenizer3.hasMoreTokens()) {
                this.messages[n2] = stringTokenizer3.nextToken();
                final StringTokenizer stringTokenizer4 = new StringTokenizer(this.messages[n2], "|");
                int n3 = 0;
                while (stringTokenizer4.hasMoreTokens()) {
                    this.multiMessages[n2][n3] = stringTokenizer4.nextToken();
                    ++n3;
                    this.multiCount[n2] = n3;
                }
                if (this.multiMessages[n2][1] != null) {
                    this.multiFlag[n2] = true;
                }
                else {
                    this.multiFlag[n2] = false;
                }
                this.default_messages[n2] = this.messages[n2];
                this.textColor[n2] = new Color(0, 0, 0);
                this.default_textColor[n2] = new Color(0, 0, 0);
                this.delay[n2] = 20;
                this.typewriterDelay[n2] = 300;
                this.fadeFrames[n2] = 50;
                this.shadowXOffset[n2] = 2;
                this.shadowYOffset[n2] = 2;
                this.shadowColor[n2] = Color.black;
                ++n2;
            }
            this.messageCount = n2 - 1;
        }
        else {
            final String parameter5 = this.getParameter("MESSAGE");
            if (parameter5 != null) {
                final StringTokenizer stringTokenizer5 = new StringTokenizer(parameter5, ";");
                int n4 = 0;
                while (stringTokenizer5.hasMoreTokens()) {
                    this.messages[n4] = stringTokenizer5.nextToken();
                    final StringTokenizer stringTokenizer6 = new StringTokenizer(this.messages[n4], "|");
                    int n5 = 0;
                    while (stringTokenizer6.hasMoreTokens()) {
                        this.multiMessages[n4][n5] = stringTokenizer6.nextToken();
                        ++n5;
                        this.multiCount[n4] = n5;
                    }
                    if (this.multiMessages[n4][1] != null) {
                        this.multiFlag[n4] = true;
                    }
                    else {
                        this.multiFlag[n4] = false;
                    }
                    this.default_messages[n4] = this.messages[n4];
                    this.textColor[n4] = new Color(0, 0, 0);
                    this.default_textColor[n4] = new Color(0, 0, 0);
                    this.delay[n4] = 20;
                    this.typewriterDelay[n4] = 300;
                    this.fadeFrames[n4] = 50;
                    this.shadowXOffset[n4] = 2;
                    this.shadowYOffset[n4] = 2;
                    this.shadowColor[n4] = Color.black;
                    ++n4;
                }
                this.messageCount = n4 - 1;
            }
        }
        if (this.demoMode) {
            for (int l = 0; l <= 19; ++l) {
                this.myURL[l] = "http://www.triview.com/cgibin/applet-link.pl?source=" + this.getDocumentBase() + "&applet=marquee&version=" + "2.4";
                try {
                    this.theURL[l] = new URL(this.myURL[l]);
                }
                catch (MalformedURLException ex2) {
                    this.theURL[l] = null;
                }
            }
        }
        else {
            final String parameter6 = this.getParameter("URL");
            if (parameter6 != null) {
                final StringTokenizer stringTokenizer7 = new StringTokenizer(parameter6, ";");
                int n6 = 0;
                while (stringTokenizer7.hasMoreTokens()) {
                    this.myURL[n6] = stringTokenizer7.nextToken();
                    if (this.myURL[n6] != "?") {
                        try {
                            this.theURL[n6] = new URL(this.myURL[n6]);
                        }
                        catch (MalformedURLException ex3) {
                            System.out.println("Incorrect URL Syntax for URL: " + this.myURL[n6] + "  (Absolute URL's are required.)");
                            this.theURL[n6] = null;
                        }
                    }
                    else {
                        this.theURL[n6] = null;
                    }
                    ++n6;
                }
            }
        }
        this.target = this.getParameter("target");
        final String parameter7 = this.getParameter("FONT_TYPE");
        if (parameter7 != null) {
            final StringTokenizer stringTokenizer8 = new StringTokenizer(parameter7, ";");
            int n7 = 0;
            while (stringTokenizer8.hasMoreTokens()) {
                this.font_type[n7] = stringTokenizer8.nextToken();
                ++n7;
            }
        }
        final String parameter8 = this.getParameter("FONT_STYLE");
        if (parameter8 != null) {
            final StringTokenizer stringTokenizer9 = new StringTokenizer(parameter8, ";");
            int n8 = 0;
            while (stringTokenizer9.hasMoreTokens()) {
                final String lowerCase = stringTokenizer9.nextToken().toLowerCase();
                if (lowerCase.equals("plain")) {
                    this.font_style[n8] = 0;
                }
                else if (lowerCase.equals("bold")) {
                    this.font_style[n8] = 1;
                }
                else if (lowerCase.equals("italic")) {
                    this.font_style[n8] = 2;
                }
                else if (lowerCase.equals("bold+italic")) {
                    this.font_style[n8] = 3;
                }
                else {
                    this.font_style[n8] = 0;
                }
                ++n8;
            }
        }
        final String parameter9 = this.getParameter("FONT_SIZE");
        if (parameter9 != null) {
            final StringTokenizer stringTokenizer10 = new StringTokenizer(parameter9, ";");
            int n9 = 0;
            while (stringTokenizer10.hasMoreTokens()) {
                this.font_size[n9] = Integer.parseInt(stringTokenizer10.nextToken());
                ++n9;
            }
        }
        final String parameter10 = this.getParameter("DELAY");
        if (parameter10 != null) {
            final StringTokenizer stringTokenizer11 = new StringTokenizer(parameter10, ";");
            int n10 = 0;
            while (stringTokenizer11.hasMoreTokens()) {
                this.delay[n10] = Integer.parseInt(stringTokenizer11.nextToken());
                ++n10;
            }
        }
        final String parameter11 = this.getParameter("TYPEWRITERDELAY");
        if (parameter11 != null) {
            final StringTokenizer stringTokenizer12 = new StringTokenizer(parameter11, ";");
            int n11 = 0;
            while (stringTokenizer12.hasMoreTokens()) {
                this.typewriterDelay[n11] = Integer.parseInt(stringTokenizer12.nextToken());
                ++n11;
            }
        }
        final String parameter12 = this.getParameter("FADEFRAMES");
        if (parameter12 != null) {
            final StringTokenizer stringTokenizer13 = new StringTokenizer(parameter12, ";");
            int n12 = 0;
            while (stringTokenizer13.hasMoreTokens()) {
                this.fadeFrames[n12] = Integer.parseInt(stringTokenizer13.nextToken());
                ++n12;
            }
        }
        final String parameter13 = this.getParameter("SOUND");
        if (parameter13 != null) {
            final StringTokenizer stringTokenizer14 = new StringTokenizer(parameter13, ";");
            int n13 = 0;
            while (stringTokenizer14.hasMoreTokens()) {
                this.soundClip[n13] = this.getAudioClip(this.getDocumentBase(), stringTokenizer14.nextToken());
                ++n13;
            }
        }
        final String parameter14 = this.getParameter("IMAGES");
        if (parameter14 != null) {
            final StringTokenizer stringTokenizer15 = new StringTokenizer(parameter14, ";");
            int n14 = 0;
            while (stringTokenizer15.hasMoreTokens()) {
                final String nextToken = stringTokenizer15.nextToken();
                if (nextToken.charAt(0) != '?') {
                    mediaTracker.addImage(this.images[n14] = this.getImage(this.getDocumentBase(), nextToken), 0);
                }
                else {
                    this.images[n14] = null;
                }
                ++n14;
            }
        }
        final String parameter15 = this.getParameter("ALIGNTEXT");
        if (parameter15 != null) {
            final StringTokenizer stringTokenizer16 = new StringTokenizer(parameter15, ";");
            int n15 = 0;
            while (stringTokenizer16.hasMoreTokens()) {
                this.align[n15] = stringTokenizer16.nextToken().charAt(0);
                ++n15;
            }
        }
        final String parameter16 = this.getParameter("shadow_toggle");
        if (parameter16 != null) {
            final StringTokenizer stringTokenizer17 = new StringTokenizer(parameter16, ";");
            int n16 = 0;
            while (stringTokenizer17.hasMoreTokens()) {
                this.dropShadow[n16] = Boolean.valueOf(stringTokenizer17.nextToken());
                ++n16;
            }
            final String parameter17 = this.getParameter("shadowXoffset");
            if (parameter17 != null) {
                final StringTokenizer stringTokenizer18 = new StringTokenizer(parameter17, ";");
                int n17 = 0;
                while (stringTokenizer18.hasMoreTokens()) {
                    this.shadowXOffset[n17] = Integer.parseInt(stringTokenizer18.nextToken());
                    ++n17;
                }
            }
            final String parameter18 = this.getParameter("shadowYoffset");
            if (parameter18 != null) {
                final StringTokenizer stringTokenizer19 = new StringTokenizer(parameter18, ";");
                int n18 = 0;
                while (stringTokenizer19.hasMoreTokens()) {
                    this.shadowYOffset[n18] = Integer.parseInt(stringTokenizer19.nextToken());
                    ++n18;
                }
            }
            final String parameter19 = this.getParameter("shadowColor");
            if (parameter19 != null) {
                final StringTokenizer stringTokenizer20 = new StringTokenizer(parameter19, ";");
                int n19 = 0;
                while (stringTokenizer20.hasMoreTokens()) {
                    final StringTokenizer stringTokenizer21 = new StringTokenizer(stringTokenizer20.nextToken());
                    this.shadowColor[n19] = new Color(Integer.parseInt(stringTokenizer21.nextToken()), Integer.parseInt(stringTokenizer21.nextToken()), Integer.parseInt(stringTokenizer21.nextToken()));
                    ++n19;
                }
            }
        }
        final String parameter20 = this.getParameter("WINCOLOR");
        if (parameter20 != null) {
            final StringTokenizer stringTokenizer22 = new StringTokenizer(parameter20);
            this.winColorRed = Integer.parseInt(stringTokenizer22.nextToken());
            this.winColorGreen = Integer.parseInt(stringTokenizer22.nextToken());
            this.winColorBlue = Integer.parseInt(stringTokenizer22.nextToken());
        }
        if ((this.backgroundImage = this.getParameter("BACKGROUNDIMAGE")) != null) {
            this.image_toggle = true;
            mediaTracker.addImage(this.background = this.getImage(this.getDocumentBase(), this.backgroundImage), 0);
        }
        this.winColor = new Color(this.winColorRed, this.winColorGreen, this.winColorBlue);
        final String[] array = new String[20];
        final String parameter21 = this.getParameter("TEXTCOLOR");
        if (parameter21 != null) {
            final StringTokenizer stringTokenizer23 = new StringTokenizer(parameter21, ";");
            int n20 = 0;
            while (stringTokenizer23.hasMoreTokens()) {
                array[n20] = stringTokenizer23.nextToken();
                final StringTokenizer stringTokenizer24 = new StringTokenizer(array[n20]);
                this.textColorRed[n20] = Integer.parseInt(stringTokenizer24.nextToken());
                this.textColorGreen[n20] = Integer.parseInt(stringTokenizer24.nextToken());
                this.textColorBlue[n20] = Integer.parseInt(stringTokenizer24.nextToken());
                this.textColor[n20] = new Color(this.textColorRed[n20], this.textColorGreen[n20], this.textColorBlue[n20]);
                this.default_textColor[n20] = this.textColor[n20];
                this.deltaRed[n20] = this.winColorRed - this.textColorRed[n20];
                this.deltaGreen[n20] = this.winColorGreen - this.textColorGreen[n20];
                this.deltaBlue[n20] = this.winColorBlue - this.textColorBlue[n20];
                ++n20;
            }
        }
        final String[] array2 = new String[20];
        final String parameter22 = this.getParameter("ANIMATION");
        if (parameter22 != null) {
            final StringTokenizer stringTokenizer25 = new StringTokenizer(parameter22, ";");
            int n21 = 0;
            while (stringTokenizer25.hasMoreTokens()) {
                array2[n21] = stringTokenizer25.nextToken();
                final StringTokenizer stringTokenizer26 = new StringTokenizer(array2[n21]);
                this.scrollIn[n21] = Integer.parseInt(stringTokenizer26.nextToken());
                this.scrollPause[n21] = Integer.parseInt(stringTokenizer26.nextToken());
                this.scrollOut[n21] = Integer.parseInt(stringTokenizer26.nextToken());
                ++n21;
            }
            this.scrollStyle = this.scrollIn[0];
        }
        else {
            for (int n22 = 0; n22 <= this.messageCount; ++n22) {
                this.scrollIn[n22] = 1;
                this.scrollPause[n22] = 0;
                this.scrollOut[n22] = 1;
            }
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex4) {
            this.showStatus("The loading process was interrupted.");
        }
        if (mediaTracker.isErrorAny()) {
            this.messages[0] = "A required file could not be loaded!";
            this.default_messages[0] = this.messages[0];
            this.messageCount = 0;
            this.textColor[0] = new Color(0, 0, 0);
            this.default_textColor[0] = this.textColor[0];
            this.dropShadow[0] = false;
            this.image_toggle = false;
        }
        for (int n23 = 0; n23 <= this.messageCount; ++n23) {
            if (this.messages[n23].equals("?")) {
                if (this.images[n23] == null) {
                    this.mode[n23] = 0;
                    ++this.mode0;
                    System.out.println("Error: Message " + n23 + " does not have any text or graphics defined.");
                }
                else {
                    this.mode[n23] = 2;
                    ++this.mode2;
                    this.msgWidth[n23] = this.images[n23].getWidth(this);
                    this.msgHeight[n23] = this.images[n23].getHeight(this);
                    this.lineHeight[n23] = this.msgHeight[n23];
                    this.default_x_pos[n23] = (this.appletWidth - this.msgWidth[n23]) / 2;
                    this.default_y_pos[n23] = (this.appletHeight - this.msgHeight[n23]) / 2;
                    this.y_pos[n23] = this.default_y_pos[n23];
                    this.longLineWidth[n23] = this.msgWidth[n23];
                }
            }
            else if (this.images[n23] == null) {
                this.mode[n23] = 1;
                ++this.mode1;
                if (this.font_type[n23] == null) {
                    this.font_type[n23] = "TimesRoman";
                }
                this.default_myFont[n23] = new Font(this.font_type[n23], this.font_style[n23], this.font_size[n23]);
                this.myFont[n23] = this.default_myFont[n23];
                final FontMetrics fontMetrics2 = this.g.getFontMetrics(this.myFont[n23]);
                if (this.multiFlag[n23]) {
                    this.msgWidth[n23] = fontMetrics2.stringWidth(this.multiMessages[n23][0]) + 4;
                    this.msgHeight[n23] = (fontMetrics2.getAscent() + fontMetrics2.getDescent()) * this.multiCount[n23];
                    this.lineHeight[n23] = fontMetrics2.getAscent() + fontMetrics2.getDescent();
                    this.longLineWidth[n23] = 0;
                    for (int n24 = 0; n24 <= this.multiCount[n23] - 1; ++n24) {
                        if (fontMetrics2.stringWidth(this.multiMessages[n23][n24]) > this.longLineWidth[n23]) {
                            this.longLineWidth[n23] = fontMetrics2.stringWidth(this.multiMessages[n23][n24]);
                        }
                    }
                    this.longLineWidth[n23] += 4;
                }
                else {
                    this.msgWidth[n23] = fontMetrics2.stringWidth(this.messages[n23]) + 4;
                    this.msgHeight[n23] = fontMetrics2.getAscent() + fontMetrics2.getDescent();
                    this.lineHeight[n23] = this.msgHeight[n23];
                    this.longLineWidth[n23] = this.msgWidth[n23];
                }
                this.default_x_pos[n23] = (this.appletWidth - this.msgWidth[n23]) / 2;
                this.default_y_pos[n23] = (this.appletHeight - this.msgHeight[n23]) / 2 + fontMetrics2.getAscent();
                this.y_pos[n23] = this.default_y_pos[n23];
            }
            else {
                this.mode[n23] = 3;
                ++this.mode3;
                if (this.font_type[n23] == null) {
                    this.font_type[n23] = "TimesRoman";
                }
                this.default_myFont[n23] = new Font(this.font_type[n23], this.font_style[n23], this.font_size[n23]);
                this.myFont[n23] = this.default_myFont[n23];
                final FontMetrics fontMetrics3 = this.g.getFontMetrics(this.myFont[n23]);
                this.imageW[n23] = this.images[n23].getWidth(this);
                this.imageH[n23] = this.images[n23].getHeight(this);
                if (this.multiFlag[n23]) {
                    this.textW[n23] = fontMetrics3.stringWidth(this.multiMessages[n23][0]) + 4;
                    this.textH[n23] = fontMetrics3.getAscent() - fontMetrics3.getDescent();
                    this.msgWidth[n23] = this.imageW[n23] + this.textW[n23] + 10;
                    this.msgHeight[n23] = this.imageH[n23];
                    this.lineHeight[n23] = fontMetrics3.getHeight();
                    this.longLineWidth[n23] = 0;
                    for (int n25 = 0; n25 <= this.multiCount[n23] - 1; ++n25) {
                        if (fontMetrics3.stringWidth(this.multiMessages[n23][n25]) > this.longLineWidth[n23]) {
                            this.longLineWidth[n23] = fontMetrics3.stringWidth(this.multiMessages[n23][n25]);
                        }
                    }
                    this.longLineWidth[n23] += 4;
                }
                else {
                    this.textW[n23] = fontMetrics3.stringWidth(this.messages[n23]) + 4;
                    this.textH[n23] = fontMetrics3.getAscent() - fontMetrics3.getDescent();
                    this.msgWidth[n23] = this.imageW[n23] + this.textW[n23] + 10;
                    this.msgHeight[n23] = this.imageH[n23];
                    this.lineHeight[n23] = this.msgHeight[n23];
                    this.longLineWidth[n23] = this.msgWidth[n23];
                }
                this.default_y_pos[n23] = (this.appletHeight - this.msgHeight[n23]) / 2;
                this.default_x_pos[n23] = (this.appletWidth - this.msgWidth[n23]) / 2;
                this.y_pos[n23] = this.default_y_pos[n23];
            }
        }
        if (this.mode2 > 0 && this.mode0 == 0 && this.mode1 == 0 && this.mode3 == 0 && this.image_toggle) {
            final String parameter23 = this.getParameter("BANNERMODE");
            if (parameter23 != null) {
                final StringTokenizer stringTokenizer27 = new StringTokenizer(parameter23, ";");
                int n26 = 0;
                while (stringTokenizer27.hasMoreTokens()) {
                    this.bannermode[n26] = Boolean.valueOf(stringTokenizer27.nextToken());
                    ++n26;
                }
            }
        }
    }
    
    public void start() {
        if (!this.started) {
            this.started = true;
            this.myThread = new Thread(this);
            this.setPositions();
            this.loadingURL = false;
            this.setCursor(0);
            this.getAppletContext().showStatus("");
            this.myThread.start();
            return;
        }
        this.myThread.resume();
    }
    
    public void run() {
        this.time = System.currentTimeMillis();
        while (true) {
            this.animate();
            this.repaint();
            if (!this.scrolledOffScreen() && this.centered()) {
                this.time += this.scrollPause[this.messageIndex];
                if (this.bannermode[0]) {
                    this.background = this.images[this.messageIndex];
                    this.refreshMessage();
                }
                else {
                    this.scrollStyle = this.scrollOut[this.messageIndex];
                }
            }
            try {
                this.time += this.delay[this.messageIndex];
                Thread.sleep(Math.max(0L, this.time - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.image_toggle) {
            this.offScrGC.drawImage(this.background, 0, 0, this);
        }
        else {
            this.offScrGC.setColor(this.winColor);
            this.offScrGC.fillRect(0, 0, this.appletWidth, this.appletHeight);
        }
        switch (this.mode[this.messageIndex]) {
            case 1: {
                this.offScrGC.setFont(this.myFont[this.messageIndex]);
                if (this.dropShadow[this.messageIndex]) {
                    this.offScrGC.setColor(this.shadowColor[this.messageIndex]);
                    if (this.multiFlag[this.messageIndex]) {
                        for (int i = 0; i <= this.multiCount[this.messageIndex] - 1; ++i) {
                            this.offScrGC.drawString(this.multiMessages[this.messageIndex][i], this.x_pos + this.shadowXOffset[this.messageIndex], this.y_pos[this.messageIndex] + this.shadowYOffset[this.messageIndex] + this.lineHeight[this.messageIndex] * i);
                        }
                    }
                    else {
                        this.offScrGC.drawString(this.messages[this.messageIndex], this.x_pos + this.shadowXOffset[this.messageIndex], this.y_pos[this.messageIndex] + this.shadowYOffset[this.messageIndex]);
                    }
                }
                this.offScrGC.setColor(this.textColor[this.messageIndex]);
                if (this.multiFlag[this.messageIndex]) {
                    for (int j = 0; j <= this.multiCount[this.messageIndex] - 1; ++j) {
                        this.offScrGC.drawString(this.multiMessages[this.messageIndex][j], this.x_pos, this.y_pos[this.messageIndex] + this.lineHeight[this.messageIndex] * j);
                    }
                    break;
                }
                this.offScrGC.drawString(this.messages[this.messageIndex], this.x_pos, this.y_pos[this.messageIndex]);
                break;
            }
            case 2: {
                this.offScrGC.drawImage(this.images[this.messageIndex], this.x_pos, this.y_pos[this.messageIndex], this);
                break;
            }
            case 3: {
                this.offScrGC.drawImage(this.images[this.messageIndex], this.x_pos, this.y_pos[this.messageIndex], this);
                this.offScrGC.setFont(this.myFont[this.messageIndex]);
                final int n = this.x_pos + this.imageW[this.messageIndex] + 10;
                int n2 = 0;
                switch (this.align[this.messageIndex]) {
                    case 't': {
                        n2 = this.y_pos[this.messageIndex] + this.textH[this.messageIndex];
                        break;
                    }
                    case 'm': {
                        n2 = this.y_pos[this.messageIndex] + (this.imageH[this.messageIndex] + this.textH[this.messageIndex]) / 2;
                        break;
                    }
                    case 'b': {
                        n2 = this.y_pos[this.messageIndex] + this.imageH[this.messageIndex];
                        break;
                    }
                    default: {
                        n2 = this.y_pos[this.messageIndex] + (this.imageH[this.messageIndex] + this.textH[this.messageIndex]) / 2;
                        break;
                    }
                }
                if (this.dropShadow[this.messageIndex]) {
                    this.offScrGC.setColor(this.shadowColor[this.messageIndex]);
                    if (this.multiFlag[this.messageIndex]) {
                        for (int k = 0; k <= this.multiCount[this.messageIndex] - 1; ++k) {
                            this.offScrGC.drawString(this.multiMessages[this.messageIndex][k], n + this.shadowXOffset[this.messageIndex], n2 + this.shadowYOffset[this.messageIndex] + this.lineHeight[this.messageIndex] * k);
                        }
                    }
                    else {
                        this.offScrGC.drawString(this.messages[this.messageIndex], n + this.shadowXOffset[this.messageIndex], n2 + this.shadowYOffset[this.messageIndex]);
                    }
                }
                this.offScrGC.setColor(this.textColor[this.messageIndex]);
                if (this.multiFlag[this.messageIndex]) {
                    for (int l = 0; l <= this.multiCount[this.messageIndex] - 1; ++l) {
                        this.offScrGC.drawString(this.multiMessages[this.messageIndex][l], n, n2 + this.lineHeight[this.messageIndex] * l);
                    }
                    break;
                }
                this.offScrGC.drawString(this.messages[this.messageIndex], n, n2);
                break;
            }
        }
        graphics.drawImage(this.offScrImage, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void animate() {
        switch (this.scrollStyle) {
            case 1: {
                --this.x_pos;
            }
            case 2: {
                ++this.x_pos;
            }
            case 3: {
                final int[] y_pos = this.y_pos;
                final int messageIndex = this.messageIndex;
                --y_pos[messageIndex];
            }
            case 4: {
                final int[] y_pos2 = this.y_pos;
                final int messageIndex2 = this.messageIndex;
                ++y_pos2[messageIndex2];
            }
            case 5: {
                this.myFont[this.messageIndex] = new Font(this.font_type[this.messageIndex], this.font_style[this.messageIndex], this.myFont[this.messageIndex].getSize() + 1);
                this.x_pos = (this.appletWidth - this.g.getFontMetrics(this.myFont[this.messageIndex]).stringWidth(this.messages[this.messageIndex]) + 4) / 2;
                this.y_pos[this.messageIndex] = (this.appletHeight - this.g.getFontMetrics(this.myFont[this.messageIndex]).getAscent() - this.g.getFontMetrics(this.myFont[this.messageIndex]).getDescent()) / 2 + this.g.getFontMetrics(this.myFont[this.messageIndex]).getAscent();
            }
            case 6: {
                this.myFont[this.messageIndex] = new Font(this.font_type[this.messageIndex], this.font_style[this.messageIndex], this.myFont[this.messageIndex].getSize() - 1);
                this.x_pos = (this.appletWidth - this.g.getFontMetrics(this.myFont[this.messageIndex]).stringWidth(this.messages[this.messageIndex]) + 4) / 2;
                this.y_pos[this.messageIndex] = (this.appletHeight - this.g.getFontMetrics(this.myFont[this.messageIndex]).getAscent() - this.g.getFontMetrics(this.myFont[this.messageIndex]).getDescent()) / 2 + this.g.getFontMetrics(this.myFont[this.messageIndex]).getAscent();
            }
            case 7: {
                ++this.fadeCount;
                this.textColor[this.messageIndex] = new Color(this.winColorRed - this.fadeCount * this.deltaRed[this.messageIndex] / this.fadeFrames[this.messageIndex], this.winColorGreen - this.fadeCount * this.deltaGreen[this.messageIndex] / this.fadeFrames[this.messageIndex], this.winColorBlue - this.fadeCount * this.deltaBlue[this.messageIndex] / this.fadeFrames[this.messageIndex]);
            }
            case 8: {
                ++this.fadeCount;
                this.textColor[this.messageIndex] = new Color(this.textColorRed[this.messageIndex] + this.fadeCount * this.deltaRed[this.messageIndex] / this.fadeFrames[this.messageIndex], this.textColorGreen[this.messageIndex] + this.fadeCount * this.deltaGreen[this.messageIndex] / this.fadeFrames[this.messageIndex], this.textColorBlue[this.messageIndex] + this.fadeCount * this.deltaBlue[this.messageIndex] / this.fadeFrames[this.messageIndex]);
            }
            case 9: {
                this.time += this.typewriterDelay[this.messageIndex];
                if (this.messages[this.messageIndex].length() != this.default_messages[this.messageIndex].length()) {
                    this.messages[this.messageIndex] = this.default_messages[this.messageIndex].substring(0, this.messages[this.messageIndex].length() + 1);
                    return;
                }
                break;
            }
        }
    }
    
    private boolean scrolledOffScreen() {
        switch (this.scrollStyle) {
            case 0: {
                return false;
            }
            case 1: {
                if (this.x_pos < -1 * (this.longLineWidth[this.messageIndex] + this.imageW[this.messageIndex] + 10)) {
                    this.refreshMessage();
                    return true;
                }
                return false;
            }
            case 2: {
                if (this.x_pos > this.appletWidth) {
                    this.refreshMessage();
                    return true;
                }
                return false;
            }
            case 3: {
                switch (this.mode[this.messageIndex]) {
                    case 1: {
                        if (this.y_pos[this.messageIndex] < -this.msgHeight[this.messageIndex]) {
                            this.refreshMessage();
                            return true;
                        }
                        return false;
                    }
                    case 2: {
                        if (this.y_pos[this.messageIndex] < -this.msgHeight[this.messageIndex]) {
                            this.refreshMessage();
                            return true;
                        }
                        return false;
                    }
                    case 3: {
                        if (this.y_pos[this.messageIndex] < -1 * this.imageH[this.messageIndex] && this.y_pos[this.messageIndex] < -1 * (this.lineHeight[this.messageIndex] * (this.multiCount[this.messageIndex] + 1))) {
                            this.refreshMessage();
                            return true;
                        }
                        return false;
                    }
                }
                break;
            }
            case 4: {
                switch (this.mode[this.messageIndex]) {
                    case 1: {
                        if (this.y_pos[this.messageIndex] > this.appletHeight + this.msgHeight[this.messageIndex]) {
                            this.refreshMessage();
                            return true;
                        }
                        return false;
                    }
                    case 2: {
                        if (this.y_pos[this.messageIndex] > this.appletHeight) {
                            this.refreshMessage();
                            return true;
                        }
                        return false;
                    }
                    case 3: {
                        if (this.y_pos[this.messageIndex] > this.appletHeight + Math.abs(this.textH[this.messageIndex] - this.imageH[this.messageIndex])) {
                            this.refreshMessage();
                            return true;
                        }
                        return false;
                    }
                }
                break;
            }
            case 5: {
                if (this.myFont[this.messageIndex].getSize() >= 96) {
                    this.refreshMessage();
                    return true;
                }
                return false;
            }
            case 6: {
                if (this.myFont[this.messageIndex].getSize() <= 1) {
                    this.refreshMessage();
                    return true;
                }
                return false;
            }
            case 7: {
                return false;
            }
            case 8: {
                if (this.textColor[this.messageIndex].equals(this.winColor)) {
                    this.time += 500L;
                    this.refreshMessage();
                    return true;
                }
                return false;
            }
            default: {
                return false;
            }
            case 9: {
                return false;
            }
        }
    }
    
    private boolean centered() {
        switch (this.scrollStyle) {
            case 1: {
                return this.x_pos == this.default_x_pos[this.messageIndex];
            }
            case 2: {
                return this.x_pos == this.default_x_pos[this.messageIndex];
            }
            case 3: {
                return this.y_pos[this.messageIndex] == this.default_y_pos[this.messageIndex];
            }
            case 4: {
                return this.y_pos[this.messageIndex] == this.default_y_pos[this.messageIndex];
            }
            case 5: {
                return this.myFont[this.messageIndex].getSize() == this.default_myFont[this.messageIndex].getSize();
            }
            case 6: {
                return this.myFont[this.messageIndex].getSize() == this.default_myFont[this.messageIndex].getSize();
            }
            case 7: {
                if (this.textColor[this.messageIndex].equals(this.default_textColor[this.messageIndex])) {
                    this.fadeCount = 0;
                    return true;
                }
                return false;
            }
            case 8: {
                return false;
            }
            case 9: {
                return this.messages[this.messageIndex].length() == this.default_messages[this.messageIndex].length();
            }
            default: {
                return false;
            }
        }
    }
    
    private void setPositions() {
        this.scrollStyle = this.scrollIn[this.messageIndex];
        if (this.soundClip[this.messageIndex] != null) {
            this.soundClip[this.messageIndex].play();
        }
        Label_0816: {
            switch (this.scrollIn[this.messageIndex]) {
                case 1: {
                    this.x_pos = this.appletWidth + 50;
                    this.y_pos[this.messageIndex] = this.default_y_pos[this.messageIndex];
                    return;
                }
                case 2: {
                    this.x_pos = -1 * this.msgWidth[this.messageIndex] - 50;
                    this.y_pos[this.messageIndex] = this.default_y_pos[this.messageIndex];
                    return;
                }
                case 3: {
                    this.x_pos = this.default_x_pos[this.messageIndex];
                    switch (this.mode[this.messageIndex]) {
                        case 1: {
                            this.y_pos[this.messageIndex] = this.appletHeight + 50 + this.msgHeight[this.messageIndex];
                            return;
                        }
                        case 2: {
                            this.y_pos[this.messageIndex] = this.appletHeight + 50;
                            return;
                        }
                        case 3: {
                            this.y_pos[this.messageIndex] = this.appletHeight + 50;
                            return;
                        }
                        default: {
                            break Label_0816;
                        }
                    }
                    break;
                }
                case 4: {
                    this.x_pos = this.default_x_pos[this.messageIndex];
                    switch (this.mode[this.messageIndex]) {
                        case 1: {
                            this.y_pos[this.messageIndex] = -50;
                            return;
                        }
                        case 2: {
                            this.y_pos[this.messageIndex] = -1 * (50 + this.msgHeight[this.messageIndex]);
                            return;
                        }
                        case 3: {
                            this.y_pos[this.messageIndex] = -50;
                            return;
                        }
                        default: {
                            break Label_0816;
                        }
                    }
                    break;
                }
                case 5: {
                    this.myFont[this.messageIndex] = new Font(this.font_type[this.messageIndex], this.font_style[this.messageIndex], 1);
                    this.x_pos = (this.appletWidth - this.g.getFontMetrics(this.myFont[this.messageIndex]).stringWidth(this.messages[this.messageIndex]) + 4) / 2;
                    this.y_pos[this.messageIndex] = (this.appletHeight - this.g.getFontMetrics(this.myFont[this.messageIndex]).getAscent() - this.g.getFontMetrics(this.myFont[this.messageIndex]).getDescent()) / 2 + this.g.getFontMetrics(this.myFont[this.messageIndex]).getAscent();
                    return;
                }
                case 6: {
                    this.myFont[this.messageIndex] = new Font(this.font_type[this.messageIndex], this.font_style[this.messageIndex], 96);
                    this.x_pos = (this.appletWidth - this.g.getFontMetrics(this.myFont[this.messageIndex]).stringWidth(this.messages[this.messageIndex]) + 4) / 2;
                    this.y_pos[this.messageIndex] = (this.appletHeight - this.g.getFontMetrics(this.myFont[this.messageIndex]).getAscent() - this.g.getFontMetrics(this.myFont[this.messageIndex]).getDescent()) / 2 + this.g.getFontMetrics(this.myFont[this.messageIndex]).getAscent();
                    return;
                }
                case 7: {
                    this.textColor[this.messageIndex] = new Color(this.winColorRed, this.winColorGreen, this.winColorBlue);
                    this.x_pos = this.default_x_pos[this.messageIndex];
                    this.y_pos[this.messageIndex] = this.default_y_pos[this.messageIndex];
                    return;
                }
                case 8: {
                    this.x_pos = this.default_x_pos[this.messageIndex];
                    this.y_pos[this.messageIndex] = this.default_y_pos[this.messageIndex];
                    return;
                }
                case 9: {
                    this.messages[this.messageIndex] = "";
                    this.x_pos = this.default_x_pos[this.messageIndex];
                    this.y_pos[this.messageIndex] = this.default_y_pos[this.messageIndex];
                    return;
                }
            }
        }
    }
    
    public void setScrollStyle(final int scrollStyle) {
        this.scrollStyle = scrollStyle;
    }
    
    private void refreshMessage() {
        this.y_pos[this.messageIndex] = this.default_y_pos[this.messageIndex];
        this.myFont[this.messageIndex] = this.default_myFont[this.messageIndex];
        this.textColor[this.messageIndex] = this.default_textColor[this.messageIndex];
        this.fadeCount = 0;
        ++this.messageIndex;
        if (this.messageIndex > this.messageCount) {
            this.messageIndex = 0;
            this.time = System.currentTimeMillis();
        }
        this.setPositions();
    }
    
    public void stop() {
        if (this.soundClip[this.messageIndex] != null) {
            this.soundClip[this.messageIndex].stop();
        }
        this.myThread.stop();
        this.started = false;
    }
    
    public void destroy() {
        if (this.soundClip[this.messageIndex] != null) {
            this.soundClip[this.messageIndex].stop();
        }
        this.myThread.stop();
    }
    
    private void playSound() {
        if (this.soundClip[this.messageIndex] != null) {
            this.soundClip[this.messageIndex].play();
        }
    }
    
    private void stopSound() {
        if (this.soundClip[this.messageIndex] != null) {
            this.soundClip[this.messageIndex].stop();
        }
    }
    
    private String loadFile(final String s) {
        URL url;
        try {
            url = new URL(this.getCodeBase(), s);
        }
        catch (MalformedURLException ex) {
            return "Can't access " + s;
        }
        String s2;
        try {
            final DataInputStream dataInputStream = new DataInputStream(url.openStream());
            s2 = dataInputStream.readLine();
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                s2 = s2 + ";" + line;
            }
        }
        catch (IOException ex2) {
            return "IO Error during read";
        }
        return s2;
    }
    
    private int hash(final String s) {
        long n = 0L;
        for (int i = 0; i < s.length(); ++i) {
            n = (n * 27L + s.charAt(i)) % 1000000000L;
        }
        return (int)n;
    }
    
    private String hashValue(final String s) {
        final long n = (Math.abs(this.hash(s)) + 1987L) * 739875L;
        final String string = new Long(n).toString();
        return string + (long)Math.abs(this.hash(string) * 654237) + Math.abs(n * 5470153L);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                return true;
            }
            case 502: {
                if (this.theURL[this.messageIndex] != null && !this.loadingURL) {
                    if (this.demoMode) {
                        this.getAppletContext().showStatus("Loading...");
                    }
                    else {
                        this.getAppletContext().showStatus("Contacting " + this.myURL[this.messageIndex]);
                    }
                    if (this.target != null) {
                        this.getAppletContext().showDocument(this.theURL[this.messageIndex], this.target);
                    }
                    else {
                        this.loadingURL = true;
                        this.getAppletContext().showDocument(this.theURL[this.messageIndex]);
                    }
                }
                return true;
            }
            case 503: {
                return false;
            }
            case 506: {
                return false;
            }
            case 504: {
                if (this.theURL[this.messageIndex] != null) {
                    if (this.loadingURL) {
                        this.setCursor(3);
                        if (this.demoMode) {
                            this.getAppletContext().showStatus("Loading...");
                        }
                        else {
                            this.getAppletContext().showStatus("Contacting " + this.myURL[this.messageIndex]);
                        }
                    }
                    else {
                        this.setCursor(12);
                        if (this.demoMode) {
                            this.getAppletContext().showStatus("Click Me!");
                        }
                        else {
                            this.getAppletContext().showStatus(this.myURL[this.messageIndex]);
                        }
                    }
                    return true;
                }
            }
            case 505: {
                this.setCursor(0);
                this.getAppletContext().showStatus("");
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public void setCursor(final int cursor) {
        try {
            ((Frame)this.getParent()).setCursor(cursor);
        }
        catch (Exception ex) {}
    }
    
    public int getCursor() {
        try {
            ((Frame)this.getParent()).getCursorType();
        }
        catch (Exception ex) {}
        return ((Frame)this.getParent()).getCursorType();
    }
    
    public String getAppletInfo() {
        final String s = "\nMarquee: Version 2.4\nCopyright 1997-2001 Triview Software \nhttp://www.triview.com\n\n";
        String s2;
        if (this.demoMode) {
            s2 = s + "Demo Version\n";
        }
        else if (this.pirated) {
            s2 = s + "Marquee not registered for host: " + this.host + "\n";
        }
        else {
            s2 = s + "Registered Version\n";
        }
        return s2 + "Marquee configured for up to " + 20 + " messages.\n";
    }
    
    public Marquee() {
        this.demoMode = false;
        this.pirated = false;
        this.regEntry = new Vector();
        this.images = new Image[20];
        this.messages = new String[20];
        this.default_messages = new String[20];
        this.multiMessages = new String[20][20];
        this.multiCount = new int[20];
        this.theHost = new String[5];
        this.multiFlag = new boolean[20];
        this.font_type = new String[20];
        this.font_style = new int[20];
        this.align = new char[20];
        this.mode = new int[20];
        this.font_size = new int[20];
        this.y_pos = new int[20];
        this.default_y_pos = new int[20];
        this.default_x_pos = new int[20];
        this.msgWidth = new int[20];
        this.longLineWidth = new int[20];
        this.msgHeight = new int[20];
        this.lineHeight = new int[20];
        this.imageW = new int[20];
        this.imageH = new int[20];
        this.textW = new int[20];
        this.textH = new int[20];
        this.myFont = new Font[20];
        this.default_myFont = new Font[20];
        this.textColorRed = new int[20];
        this.textColorGreen = new int[20];
        this.textColorBlue = new int[20];
        this.scrollIn = new int[20];
        this.scrollPause = new int[20];
        this.scrollOut = new int[20];
        this.textColor = new Color[20];
        this.default_textColor = new Color[20];
        this.deltaRed = new int[20];
        this.deltaGreen = new int[20];
        this.deltaBlue = new int[20];
        this.delay = new int[20];
        this.typewriterDelay = new int[20];
        this.fadeFrames = new int[20];
        this.dropShadow = new boolean[20];
        this.shadowXOffset = new int[20];
        this.shadowYOffset = new int[20];
        this.shadowColor = new Color[20];
        this.bannermode = new boolean[20];
        this.myURL = new String[20];
        this.theURL = new URL[20];
        this.soundClip = new AudioClip[20];
        this.loadingURL = false;
        this.winColorRed = 255;
        this.winColorGreen = 255;
        this.winColorBlue = 255;
        this.started = false;
        this.image_toggle = false;
        this.scrollStyle = 1;
    }
    
    static {
        dateCompiled = new Date(100, 1, 2);
        dateExpires = new Date(100, 1, 32);
    }
}
