import java.awt.image.ImageObserver;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextScroller extends Applet implements Runnable
{
    String HillmanSoftware;
    Font hfont;
    FontMetrics hfm;
    Color hColor;
    Thread loopThread;
    Image offImage;
    Graphics offScreen;
    Font font;
    FontMetrics fm;
    Vector vMessage;
    int fontWidth;
    int fontHeight;
    int xpos;
    int ypos;
    int position;
    int step;
    int currentMessage;
    int mx;
    int my;
    Color border;
    Color currentColor;
    String currentString;
    int[] fadeDelay;
    int[] scrollDelay;
    final int LEFT = 1;
    final int RIGHT = 2;
    final int UP = 3;
    final int DOWN = 4;
    final int FADE = 5;
    final int ZOOM = 6;
    int parmMessages;
    Font parmFont;
    String parmFontName;
    int parmFontType;
    int parmFontSize;
    int parmTextSpeed;
    int parmPause;
    String parmMessageText;
    Color parmTextColor;
    Color parmBorderColor;
    int parmBeginPosition;
    int parmEndPosition;
    int parmTextDelay1;
    int parmTextDelay2;
    Color parmBackgroundColor;
    boolean borderColor;
    boolean backGround;
    Color defaultBorderColor;
    Color defaultTextColor;
    Color defaultBackgroundColor;
    Color defaultURLColor;
    int defaultTextSpeed;
    int defaultPause;
    int defaultBeginPosition;
    int defaultEndPosition;
    int defaultDelay;
    String parmURL;
    Color parmURLColor;
    Image backGroundImage;
    String url$;
    boolean bRunApplet;
    boolean bCheckURL;
    boolean bTrialVersion;
    
    public TextScroller() {
        this.HillmanSoftware = "www.HillmanSoftware.com";
        this.hfont = new Font("TimesRoman", 2, 11);
        this.hfm = this.getFontMetrics(this.hfont);
        this.vMessage = new Vector();
        this.fadeDelay = new int[] { 0, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        this.scrollDelay = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 };
        this.defaultBorderColor = new Color(0, 0, 255);
        this.defaultTextColor = new Color(0, 0, 255);
        this.defaultBackgroundColor = new Color(0, 0, 0);
        this.defaultURLColor = new Color(0, 0, 255);
        this.defaultTextSpeed = 5;
        this.defaultPause = 1000;
        this.defaultBeginPosition = 3;
        this.defaultEndPosition = 4;
        this.defaultDelay = this.scrollDelay[5];
        this.parmURLColor = this.defaultURLColor;
        this.bCheckURL = false;
        this.bTrialVersion = false;
    }
    
    public void fadeIn(final int n, final int n2, final int n3, final int n4) {
        int red = this.parmBackgroundColor.getRed();
        int green = this.parmBackgroundColor.getGreen();
        int blue = this.parmBackgroundColor.getBlue();
        final boolean b = red > n;
        final boolean b2 = green > n2;
        final boolean b3 = blue > n3;
        do {
            if (b) {
                red -= n4;
                if (red < n) {
                    red = n;
                }
            }
            else {
                red += n4;
                if (red > n) {
                    red = n;
                }
            }
            if (b2) {
                green -= n4;
                if (green < n2) {
                    green = n2;
                }
            }
            else {
                green += n4;
                if (green > n2) {
                    green = n2;
                }
            }
            if (b3) {
                blue -= n4;
                if (blue < n3) {
                    blue = n3;
                }
            }
            else {
                blue += n4;
                if (blue > n3) {
                    blue = n3;
                }
            }
            this.currentColor = new Color(red, green, blue);
            this.repaint();
            this.pause(20);
        } while (red != n || green != n2 || blue != n3);
    }
    
    public void fadeOut(int n, int n2, int n3, final int n4) {
        final int red = this.parmBackgroundColor.getRed();
        final int green = this.parmBackgroundColor.getGreen();
        final int blue = this.parmBackgroundColor.getBlue();
        final boolean b = n > red;
        final boolean b2 = n2 > green;
        final boolean b3 = n3 > blue;
        do {
            if (b) {
                n -= n4;
                if (n < red) {
                    n = red;
                }
            }
            else {
                n += n4;
                if (n > red) {
                    n = red;
                }
            }
            if (b2) {
                n2 -= n4;
                if (n2 < green) {
                    n2 = green;
                }
            }
            else {
                n2 += n4;
                if (n2 > green) {
                    n2 = green;
                }
            }
            if (b3) {
                n3 -= n4;
                if (n3 < blue) {
                    n3 = blue;
                }
            }
            else {
                n3 += n4;
                if (n3 > blue) {
                    n3 = blue;
                }
            }
            this.currentColor = new Color(n, n2, n3);
            this.repaint();
            this.pause(20);
        } while (n != red || n2 != green || n3 != blue);
    }
    
    Color getColor(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        try {
            return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        catch (Exception ex) {
            return this.defaultBorderColor;
        }
    }
    
    void getFont(final String s, final int n) {
        String nextToken = null;
        String nextToken2 = null;
        if (s != null) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
                this.parmFontName = stringTokenizer.nextToken();
                nextToken = stringTokenizer.nextToken();
                nextToken2 = stringTokenizer.nextToken();
            }
            catch (Exception ex) {
                this.parmFontSize = 12;
                this.parmFontType = 0;
                this.parmFontName = "TimesRoman";
                this.parmFont = new Font(this.parmFontName, this.parmFontType, this.parmFontSize);
                return;
            }
        }
        if (nextToken2 == null || nextToken == null || this.parmFontName == null) {
            if (n == 1) {
                final Message message = this.vMessage.elementAt(0);
                this.parmFontSize = message.fontSize;
                this.parmFontType = message.fontType;
                this.parmFontName = message.fontName;
            }
        }
        else {
            if (nextToken2.equalsIgnoreCase("plain")) {
                this.parmFontType = 0;
            }
            else if (nextToken2.equalsIgnoreCase("italic")) {
                this.parmFontType = 2;
            }
            else {
                this.parmFontType = 1;
            }
            try {
                this.parmFontSize = Integer.parseInt(nextToken);
            }
            catch (Exception ex2) {
                this.parmFontSize = 10;
            }
        }
        try {
            this.parmFont = new Font(this.parmFontName, this.parmFontType, this.parmFontSize);
        }
        catch (Exception ex3) {
            this.parmFontSize = 12;
            this.parmFontType = 0;
            this.parmFontName = "TimesRoman";
            this.parmFont = new Font(this.parmFontName, this.parmFontType, this.parmFontSize);
        }
    }
    
    void getPosition(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final StringTokenizer stringTokenizer2 = new StringTokenizer(s2, ",");
        try {
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer2.nextToken();
            final String nextToken3 = stringTokenizer.nextToken();
            final String nextToken4 = stringTokenizer2.nextToken();
            try {
                this.parmTextDelay1 = Integer.parseInt(nextToken3);
                if (this.parmTextDelay1 < 1 || this.parmTextDelay1 > 30) {
                    this.parmTextDelay1 = 10;
                }
            }
            catch (Exception ex) {
                this.parmTextDelay1 = this.scrollDelay[5];
            }
            try {
                this.parmTextDelay2 = Integer.parseInt(nextToken4);
                if (this.parmTextDelay2 < 1 || this.parmTextDelay2 > 30) {
                    this.parmTextDelay2 = 10;
                }
            }
            catch (Exception ex2) {
                this.parmTextDelay2 = this.scrollDelay[5];
            }
            if (nextToken == null) {
                this.parmBeginPosition = this.defaultBeginPosition;
            }
            else if (nextToken.equalsIgnoreCase("FADE")) {
                this.parmBeginPosition = 5;
            }
            else if (nextToken.equalsIgnoreCase("RIGHT")) {
                this.parmBeginPosition = 2;
            }
            else if (nextToken.equalsIgnoreCase("LEFT")) {
                this.parmBeginPosition = 1;
            }
            else if (nextToken.equalsIgnoreCase("UP")) {
                this.parmBeginPosition = 3;
            }
            else if (nextToken.equalsIgnoreCase("DOWN")) {
                this.parmBeginPosition = 4;
            }
            else {
                this.parmBeginPosition = 6;
            }
            if (nextToken2 == null) {
                this.parmEndPosition = this.defaultEndPosition;
            }
            else if (nextToken2.equalsIgnoreCase("FADE")) {
                this.parmEndPosition = 5;
            }
            else if (nextToken2.equalsIgnoreCase("RIGHT")) {
                this.parmEndPosition = 2;
            }
            else if (nextToken2.equalsIgnoreCase("LEFT")) {
                this.parmEndPosition = 1;
            }
            else if (nextToken2.equalsIgnoreCase("UP")) {
                this.parmEndPosition = 3;
            }
            else if (nextToken2.equalsIgnoreCase("DOWN")) {
                this.parmEndPosition = 4;
            }
            else {
                this.parmEndPosition = 6;
            }
        }
        catch (Exception ex3) {
            this.parmBeginPosition = this.defaultBeginPosition;
            this.parmEndPosition = this.defaultEndPosition;
            this.parmTextDelay1 = 10;
            this.parmTextDelay2 = 10;
        }
    }
    
    public int getRandom(int n, final int n2) {
        n += (int)(new Random().nextDouble() * n2);
        return n;
    }
    
    public void init() {
        this.getGraphics();
        this.size();
        this.offImage = this.createImage(this.size().width, this.size().height);
        this.offScreen = this.offImage.getGraphics();
        if (this.bCheckURL) {
            this.url$ = this.getCodeBase().toString();
            final String s = "~jhillman";
            for (int i = 0; i < this.url$.length(); ++i) {
                if (this.url$.regionMatches(true, i, s, 0, s.length())) {
                    this.bRunApplet = true;
                    break;
                }
            }
            this.bRunApplet = false;
        }
        else {
            this.bRunApplet = true;
        }
        switch (this.getRandom(0, 4)) {
            case 0: {
                this.hColor = Color.red;
                break;
            }
            case 1: {
                this.hColor = Color.green;
                break;
            }
            case 2: {
                this.hColor = Color.blue;
                break;
            }
            case 3: {
                this.hColor = Color.white;
                break;
            }
        }
        final String parameter = this.getParameter("messages");
        if (parameter == null) {
            this.parmMessages = 1;
        }
        else {
            this.parmMessages = Integer.parseInt(parameter);
        }
        for (int j = 1; j <= this.parmMessages; ++j) {
            final String s2 = "background";
            if (this.getParameter(s2) == null) {
                this.parmBackgroundColor = this.defaultBackgroundColor;
            }
            else {
                this.parmBackgroundColor = this.getColor(this.getParameter(s2));
            }
            final String parameter2 = this.getParameter("backgroundimage");
            if (parameter2 == null) {
                this.backGround = false;
            }
            else {
                try {
                    this.backGround = true;
                    this.backGroundImage = this.getImage(new URL(this.getDocumentBase(), parameter2));
                }
                catch (MalformedURLException ex) {
                    this.backGround = false;
                }
            }
            final String s3 = "border";
            if (this.getParameter(s3) == null) {
                this.borderColor = false;
            }
            else {
                this.borderColor = true;
                this.parmBorderColor = this.getColor(this.getParameter(s3));
            }
            this.getFont(this.getParameter("font" + String.valueOf(j)), j);
            final String string = "message" + String.valueOf(j);
            this.parmMessageText = this.getParameter(string);
            if (this.parmMessageText == null) {
                this.parmMessageText = "KEYWORD:  '" + string + "' not defined!!!!";
            }
            final String string2 = "textcolor" + String.valueOf(j);
            if (this.getParameter(string2) == null) {
                if (j == 1) {
                    this.parmTextColor = this.defaultTextColor;
                }
                else {
                    this.parmTextColor = this.vMessage.elementAt(0).textColor;
                }
            }
            else {
                this.parmTextColor = this.getColor(this.getParameter(string2));
            }
            this.parmURL = this.getParameter("url" + String.valueOf(j));
            final String string3 = "urlcolor" + String.valueOf(j);
            if (this.getParameter(string3) == null) {
                this.parmURLColor = this.defaultURLColor;
            }
            else {
                this.parmURLColor = this.getColor(this.getParameter(string3));
            }
            final String parameter3 = this.getParameter("pause" + String.valueOf(j));
            if (parameter3 == null) {
                if (j == 1) {
                    this.parmPause = this.defaultPause;
                }
                else {
                    this.parmPause = this.vMessage.elementAt(0).pause;
                }
            }
            else {
                try {
                    this.parmPause = Integer.parseInt(parameter3);
                }
                catch (Exception ex2) {
                    this.parmPause = 2000;
                }
            }
            this.getPosition(this.getParameter("begin" + String.valueOf(j)), this.getParameter("end" + String.valueOf(j)));
            this.vMessage.addElement(new Message(this.parmBorderColor, this.parmTextColor, this.parmFont, this.parmFontName, this.parmFontType, this.parmFontSize, this.parmMessageText, this.parmTextDelay1, this.parmTextDelay2, this.parmPause, this.parmBeginPosition, this.parmEndPosition, this.parmURL, this.parmURLColor));
        }
    }
    
    public void message() {
        for (int i = 0; i < this.vMessage.size(); ++i) {
            this.currentMessage = i;
            final Message message = this.vMessage.elementAt(i);
            this.border = message.borderColor;
            this.currentString = message.message;
            this.currentColor = message.textColor;
            this.font = message.font;
            this.parmFontSize = message.fontSize;
            this.parmFontType = message.fontType;
            this.parmFontName = message.fontName;
            this.fm = this.getFontMetrics(this.font);
            this.scroll(message.begin, message.end, message.speed1, message.speed2, message.pause);
            message.bSetColor = false;
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final Message message = this.vMessage.elementAt(this.currentMessage);
        if (message.mouseBrowse(n, n2, this.xpos, this.ypos, this.currentString, this.fm)) {
            try {
                this.getAppletContext().showDocument(message.getUrl());
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int mx, final int my) {
        this.mx = mx;
        this.my = my;
        final Message message = this.vMessage.elementAt(this.currentMessage);
        if (message.mouseBrowse(mx, my, this.xpos, this.ypos, this.currentString, this.fm)) {
            message.setHighlight(true);
        }
        else {
            message.setHighlight(false);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mx = 9999;
        this.my = 9999;
        this.vMessage.elementAt(this.currentMessage).setHighlight(false);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int mx, final int my) {
        this.mx = mx;
        this.my = my;
        final Message message = this.vMessage.elementAt(this.currentMessage);
        if (message.mouseBrowse(mx, my, this.xpos, this.ypos, this.currentString, this.fm)) {
            message.setHighlight(true);
        }
        else {
            message.setHighlight(false);
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        long currentTimeMillis = System.currentTimeMillis();
        while (Thread.currentThread() == this.loopThread) {
            this.message();
            try {
                currentTimeMillis += 100L;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {
                break;
            }
        }
    }
    
    public void scroll(final int n, final int n2, final int n3, final int n4, final int n5) {
        final Message message = this.vMessage.elementAt(this.currentMessage);
        final int n6 = (n3 > 5) ? 1 : 2;
        final int n7 = (n4 > 5) ? 1 : 2;
        switch (n) {
            case 1: {
                for (int i = -this.fm.stringWidth(this.currentString); i <= (this.size().width - this.fm.stringWidth(this.currentString)) / 2; i += n6) {
                    this.xpos = i;
                    this.ypos = this.size().height / 2 + this.fm.getHeight() / 3 + 2;
                    this.repaint();
                    this.pause(this.scrollDelay[n3]);
                    if (message.mouseBrowse(this.mx, this.my, this.xpos, this.ypos, this.currentString, this.fm)) {
                        message.setHighlight(true);
                    }
                    else {
                        message.setHighlight(false);
                    }
                }
                break;
            }
            case 2: {
                for (int j = this.size().width; j >= (this.size().width - this.fm.stringWidth(this.currentString)) / 2; j -= n6) {
                    this.xpos = j;
                    this.ypos = this.size().height / 2 + this.fm.getHeight() / 3 + 2;
                    this.repaint();
                    this.pause(this.scrollDelay[n3]);
                    if (message.mouseBrowse(this.mx, this.my, this.xpos, this.ypos, this.currentString, this.fm)) {
                        message.setHighlight(true);
                    }
                    else {
                        message.setHighlight(false);
                    }
                }
                break;
            }
            case 3: {
                for (int k = 0; k <= this.size().height / 2 + this.fm.getHeight() / 3 + 2; k += n6) {
                    this.xpos = (this.size().width - this.fm.stringWidth(this.currentString)) / 2;
                    this.ypos = k;
                    this.repaint();
                    this.pause(this.scrollDelay[n3]);
                    if (message.mouseBrowse(this.mx, this.my, this.xpos, this.ypos, this.currentString, this.fm)) {
                        message.setHighlight(true);
                    }
                    else {
                        message.setHighlight(false);
                    }
                }
                break;
            }
            case 4: {
                for (int l = this.size().height + this.fm.getHeight(); l >= this.size().height / 2 + this.fm.getHeight() / 3 + 2; l -= n6) {
                    this.xpos = (this.size().width - this.fm.stringWidth(this.currentString)) / 2;
                    this.ypos = l;
                    this.repaint();
                    this.pause(this.scrollDelay[n3]);
                    if (message.mouseBrowse(this.mx, this.my, this.xpos, this.ypos, this.currentString, this.fm)) {
                        message.setHighlight(true);
                    }
                    else {
                        message.setHighlight(false);
                    }
                }
                break;
            }
            case 5: {
                this.xpos = (this.size().width - this.fm.stringWidth(this.currentString)) / 2;
                this.ypos = this.size().height / 2 + this.fm.getHeight() / 3 + 2;
                this.fadeIn(this.currentColor.getRed(), this.currentColor.getGreen(), this.currentColor.getBlue(), this.fadeDelay[n3]);
                break;
            }
            case 6: {
                int n8 = 1;
                do {
                    this.parmFont = new Font(this.parmFontName, this.parmFontType, n8++);
                    this.font = this.parmFont;
                    this.fm = this.getFontMetrics(this.font);
                    this.xpos = (this.size().width - this.fm.stringWidth(this.currentString)) / 2;
                    this.ypos = this.size().height / 2 + this.fm.getHeight() / 3 + 2;
                    this.repaint();
                    this.pause(this.scrollDelay[n3]);
                } while (n8 <= this.parmFontSize);
                break;
            }
        }
        this.pause(n5);
        switch (n2) {
            case 1: {
                for (int xpos = (this.size().width - this.fm.stringWidth(this.currentString)) / 2; xpos >= -this.fm.stringWidth(this.currentString); xpos -= n7) {
                    this.xpos = xpos;
                    this.ypos = this.size().height / 2 + this.fm.getHeight() / 3 + 2;
                    this.repaint();
                    this.pause(this.scrollDelay[n4]);
                    if (message.mouseBrowse(this.mx, this.my, this.xpos, this.ypos, this.currentString, this.fm)) {
                        message.setHighlight(true);
                    }
                    else {
                        message.setHighlight(false);
                    }
                }
                break;
            }
            case 2: {
                for (int xpos2 = (this.size().width - this.fm.stringWidth(this.currentString)) / 2; xpos2 < this.size().width + 5; xpos2 += n7) {
                    this.xpos = xpos2;
                    this.ypos = this.size().height / 2 + this.fm.getHeight() / 3 + 2;
                    this.repaint();
                    this.pause(this.scrollDelay[n4]);
                    if (message.mouseBrowse(this.mx, this.my, this.xpos, this.ypos, this.currentString, this.fm)) {
                        message.setHighlight(true);
                    }
                    else {
                        message.setHighlight(false);
                    }
                }
                break;
            }
            case 3: {
                for (int ypos = this.size().height / 2 + this.fm.getHeight() / 3 + 2; ypos >= -this.fm.getHeight(); ypos -= n7) {
                    this.ypos = ypos;
                    this.repaint();
                    this.pause(this.scrollDelay[n4]);
                    if (message.mouseBrowse(this.mx, this.my, this.xpos, this.ypos, this.currentString, this.fm)) {
                        message.setHighlight(true);
                    }
                    else {
                        message.setHighlight(false);
                    }
                }
                break;
            }
            case 4: {
                for (int ypos2 = this.size().height / 2 + this.fm.getHeight() / 3 + 2; ypos2 <= this.size().height + this.fm.getHeight(); ypos2 += n7) {
                    this.ypos = ypos2;
                    this.repaint();
                    this.pause(this.scrollDelay[n4]);
                    if (message.mouseBrowse(this.mx, this.my, this.xpos, this.ypos, this.currentString, this.fm)) {
                        message.setHighlight(true);
                    }
                    else {
                        message.setHighlight(false);
                    }
                }
                break;
            }
            case 5: {
                this.fadeOut(this.currentColor.getRed(), this.currentColor.getGreen(), this.currentColor.getBlue(), this.fadeDelay[n4]);
                break;
            }
            case 6: {
                int parmFontSize = this.parmFontSize;
                do {
                    this.parmFont = new Font(this.parmFontName, this.parmFontType, parmFontSize--);
                    this.font = this.parmFont;
                    this.fm = this.getFontMetrics(this.font);
                    this.xpos = (this.size().width - this.fm.stringWidth(this.currentString)) / 2;
                    this.ypos = this.size().height / 2 + this.fm.getHeight() / 3 + 2;
                    this.repaint();
                    this.pause(this.scrollDelay[n4]);
                } while (parmFontSize > 0);
                break;
            }
        }
    }
    
    public void start() {
        if (this.loopThread == null) {
            (this.loopThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.loopThread != null) {
            this.loopThread.stop();
            this.loopThread = null;
        }
    }
    
    public void update(final Graphics graphics) {
        final Message message = this.vMessage.elementAt(this.currentMessage);
        if (this.bRunApplet) {
            this.offScreen.setFont(this.font);
            this.offScreen.setColor(this.parmBackgroundColor);
            this.offScreen.fillRect(0, 0, this.size().width, this.size().height);
            if (message.bUrlLink) {
                this.offScreen.setColor(message.setUrlColor(this.currentColor));
            }
            else {
                this.offScreen.setColor(this.currentColor);
            }
            if (this.backGround) {
                this.offScreen.drawImage(this.backGroundImage, 0, 0, this);
            }
            this.offScreen.drawString(this.currentString, this.xpos, this.ypos);
            if (this.borderColor) {
                this.offScreen.setColor(this.border);
                for (int i = 0; i <= 5; ++i) {
                    this.offScreen.drawRoundRect(i, i, this.size().width - i - 1, this.size().height - i - 1, 10, 8);
                }
            }
            if (this.bTrialVersion) {
                final int n = this.size().width - this.hfm.stringWidth(this.HillmanSoftware) - 5;
                final int n2 = this.size().height - (this.hfm.getHeight() - 10);
                this.offScreen.setFont(this.hfont);
                this.offScreen.setColor(this.hColor);
                this.offScreen.drawString(this.HillmanSoftware, n, n2);
            }
        }
        else {
            this.currentString = "Please contact www.HillmanSoftware.com";
            this.font = new Font("TimesRoman", 0, 14);
            this.fm = this.getFontMetrics(this.font);
            this.offScreen.setFont(this.font);
            this.offScreen.setColor(Color.red);
            this.xpos = (this.size().width - this.fm.stringWidth(this.currentString)) / 2;
            this.ypos = this.size().height / 2 + this.fm.getHeight() / 3 + 2;
            this.offScreen.drawString(this.currentString, this.xpos, this.ypos);
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
}
