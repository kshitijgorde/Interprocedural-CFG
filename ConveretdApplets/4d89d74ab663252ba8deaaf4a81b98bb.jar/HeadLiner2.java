import java.awt.Event;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.awt.Component;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class HeadLiner2 extends Applet implements Runnable
{
    StringBuffer[] myStrings;
    boolean[] bHasEvents;
    Font[][][] myFonts;
    public final int HELVETICA = 0;
    public final int COURIER = 1;
    public final int TIMESROMAN = 2;
    public final int PLAIN = 0;
    public final int ITALIC = 1;
    public final int BOLD = 2;
    public final int BOLDITALIC = 3;
    public String[] strFontNames;
    public int[] nFontStyles;
    StringBuffer sponsorString;
    int nCurrentFontStyle;
    int nCurrentFontSize;
    int nCurrentFontName;
    Rectangle myRect;
    int nCharHeight;
    int nOffset;
    int nVisibleLines;
    Font myFont;
    int nNumStrings;
    int nDelay;
    StringBuffer bgColorParam;
    StringBuffer fgColorParam;
    StringBuffer sleepParam;
    StringBuffer borderParam;
    StringBuffer fileParam;
    StringBuffer fontNameParam;
    StringBuffer fontStyleParam;
    StringBuffer fontSizeParam;
    StringBuffer visibleLinesParam;
    int nFontSize;
    int nFontStyle;
    String strFontName;
    boolean fileError;
    boolean bBorder;
    boolean bRegistered;
    boolean bSoundIsReset;
    StringBuffer authorParam;
    StringBuffer originParam;
    String strURL;
    String strStatus;
    Color bgColor;
    Color fgColor;
    Color flashColor;
    boolean bRunning;
    Thread scrollThread;
    Image offscreen;
    boolean bUsebgImage;
    Image bgImage;
    Image[] images;
    int nNumImages;
    MediaTracker tracker;
    Graphics og;
    
    public void setFontName(final String s) {
        this.myFont = this.getFont();
    }
    
    public void setBold(final Graphics myFont) {
        if (this.nCurrentFontStyle == 2 || this.nCurrentFontStyle == 3) {
            return;
        }
        this.nCurrentFontStyle += 2;
        this.setMyFont(myFont);
    }
    
    public void unSetBold(final Graphics myFont) {
        if (this.nCurrentFontStyle == 2 || this.nCurrentFontStyle == 3) {
            this.nCurrentFontStyle -= 2;
            this.setMyFont(myFont);
        }
    }
    
    public void setItalic(final Graphics myFont) {
        if (this.nCurrentFontStyle == 1 || this.nCurrentFontStyle == 3) {
            return;
        }
        ++this.nCurrentFontStyle;
        this.setMyFont(myFont);
    }
    
    public void unSetItalic(final Graphics myFont) {
        if (this.nCurrentFontStyle == 1 || this.nCurrentFontStyle == 3) {
            --this.nCurrentFontStyle;
            this.setMyFont(myFont);
        }
    }
    
    public void setCourier(final Graphics myFont) {
        if (this.nCurrentFontName != 1) {
            this.nCurrentFontName = 1;
            this.setMyFont(myFont);
        }
    }
    
    public void setHelvetica(final Graphics myFont) {
        if (this.nCurrentFontName != 0) {
            this.nCurrentFontName = 0;
            this.setMyFont(myFont);
        }
    }
    
    public void setTimesRoman(final Graphics myFont) {
        if (this.nCurrentFontName != 2) {
            this.nCurrentFontName = 2;
            this.setMyFont(myFont);
        }
    }
    
    public void setMyFont() {
        if (this.myFonts[this.nCurrentFontName][this.nCurrentFontStyle][this.nCurrentFontSize] == null) {
            this.myFonts[this.nCurrentFontName][this.nCurrentFontStyle][this.nCurrentFontSize] = new Font(this.strFontNames[this.nCurrentFontName], this.nFontStyles[this.nCurrentFontStyle], this.nCurrentFontSize);
        }
        this.setFont(this.myFont = this.myFonts[this.nCurrentFontName][this.nCurrentFontStyle][this.nCurrentFontSize]);
        this.nCharHeight = this.getToolkit().getFontMetrics(this.myFont).getAscent() + this.getToolkit().getFontMetrics(this.myFont).getDescent();
    }
    
    public void setMyFont(final Graphics graphics) {
        if (this.myFonts[this.nCurrentFontName][this.nCurrentFontStyle][this.nCurrentFontSize] == null) {
            this.myFonts[this.nCurrentFontName][this.nCurrentFontStyle][this.nCurrentFontSize] = new Font(this.strFontNames[this.nCurrentFontName], this.nFontStyles[this.nCurrentFontStyle], this.nCurrentFontSize);
        }
        graphics.setFont(this.myFont = this.myFonts[this.nCurrentFontName][this.nCurrentFontStyle][this.nCurrentFontSize]);
    }
    
    public void update(final Graphics graphics) {
        boolean b = false;
        if (this.nOffset-- <= -this.nCharHeight) {
            this.nOffset = 0;
            b = true;
        }
        if (b) {
            final StringBuffer sb = this.myStrings[0];
            final boolean b2 = this.bHasEvents[0];
            for (int i = 0; i < this.nNumStrings; ++i) {
                this.myStrings[i] = this.myStrings[i + 1];
                this.bHasEvents[i] = this.bHasEvents[i + 1];
            }
            this.myStrings[this.nNumStrings] = sb;
            this.bHasEvents[this.nNumStrings] = b2;
        }
        if (this.offscreen == null) {
            this.offscreen = this.createImage(this.myRect.width, this.myRect.height);
            this.og = this.offscreen.getGraphics();
        }
        if (!this.bUsebgImage || this.bgImage == null) {
            this.og.setColor(this.bgColor);
            this.og.fillRect(0, 0, this.myRect.width, this.myRect.height);
        }
        else {
            this.og.setColor(this.bgColor);
            this.og.fillRect(0, 0, this.myRect.width, this.myRect.height);
            this.og.drawImage(this.bgImage, 0, 0, null);
        }
        this.og.setColor(this.fgColor);
        for (int n = 0; n < this.nVisibleLines && n < this.nNumStrings; ++n) {
            if (this.bHasEvents[n]) {
                final String string = this.myStrings[n].toString();
                int n2 = 2;
                for (int j = 0; j < string.length(); ++j) {
                    if (string.charAt(j) == '<' && string.charAt(j + 1) != '<') {
                        final int index = string.indexOf(62, j);
                        final String substring = string.substring(j, index + 1);
                        if (substring.equalsIgnoreCase("<b>")) {
                            this.setBold(this.og);
                        }
                        else if (substring.equalsIgnoreCase("</b>")) {
                            this.unSetBold(this.og);
                        }
                        else if (substring.equalsIgnoreCase("<i>")) {
                            this.setItalic(this.og);
                        }
                        else if (substring.equalsIgnoreCase("</i>")) {
                            this.unSetItalic(this.og);
                        }
                        else if (substring.equalsIgnoreCase("<courier>")) {
                            this.setCourier(this.og);
                        }
                        else if (substring.equalsIgnoreCase("<helvetica>")) {
                            this.setHelvetica(this.og);
                        }
                        else if (substring.equalsIgnoreCase("<timesroman>")) {
                            this.setTimesRoman(this.og);
                        }
                        else if (substring.toLowerCase().indexOf("<link=") != -1) {
                            this.strURL = substring.substring(6, substring.length() - 1);
                        }
                        else if (substring.toLowerCase().indexOf("<status=") != -1) {
                            this.strStatus = substring.substring(8, substring.length() - 1);
                        }
                        else if (substring.toLowerCase().indexOf("<image=") != -1) {
                            final String substring2 = substring.substring(7, substring.length() - 1);
                            int intValue = 0;
                            try {
                                intValue = Integer.valueOf(substring2, 10);
                                if (intValue > 9) {
                                    intValue = 9;
                                }
                                else if (intValue < 0) {
                                    intValue = 0;
                                }
                            }
                            catch (NumberFormatException ex) {
                                this.showStatus("Invalid image argument: " + ex.toString());
                            }
                            if (this.images[intValue] != null) {
                                this.og.drawImage(this.images[intValue], n2, 2 + this.nOffset + this.myRect.y + this.nCharHeight * (n + 1), null);
                            }
                        }
                        else if (substring.toLowerCase().indexOf("<play=") != -1 && this.bSoundIsReset) {
                            final String substring3 = substring.substring(6, substring.length() - 1);
                            try {
                                this.getAudioClip(new URL(this.getCodeBase(), substring3)).play();
                            }
                            catch (MalformedURLException ex2) {
                                this.showStatus(ex2.toString());
                                System.out.println(ex2.toString());
                            }
                            this.bSoundIsReset = false;
                        }
                        else if (substring.toLowerCase().indexOf("<loop=") != -1 && this.bSoundIsReset) {
                            final String substring4 = substring.substring(6, substring.length() - 1);
                            try {
                                this.getAudioClip(new URL(this.getCodeBase(), substring4)).loop();
                            }
                            catch (MalformedURLException ex3) {
                                this.showStatus(ex3.toString());
                                System.out.println(ex3.toString());
                            }
                            this.bSoundIsReset = false;
                        }
                        else if (substring.toLowerCase().indexOf("<stop=") != -1 && this.bSoundIsReset) {
                            final String substring5 = substring.substring(6, substring.length() - 1);
                            try {
                                this.getAudioClip(new URL(this.getCodeBase(), substring5)).stop();
                            }
                            catch (MalformedURLException ex4) {
                                this.showStatus(ex4.toString());
                                System.out.println(ex4.toString());
                            }
                            this.bSoundIsReset = true;
                        }
                        else if (substring.toLowerCase().indexOf("<soundreset>") != -1) {
                            this.bSoundIsReset = true;
                        }
                        else if (substring.toLowerCase().indexOf("<setbgimage=") != -1) {
                            final String substring6 = substring.substring(12, substring.length() - 1);
                            int intValue2 = 0;
                            try {
                                intValue2 = Integer.valueOf(substring6, 10);
                                if (intValue2 == 10) {
                                    this.bUsebgImage = false;
                                }
                                if (intValue2 > 9) {
                                    intValue2 = 9;
                                }
                                else if (intValue2 < 0) {
                                    intValue2 = 0;
                                }
                            }
                            catch (NumberFormatException ex5) {
                                this.showStatus("Invalid image argument: " + ex5.toString());
                            }
                            if (this.images[intValue2] != null) {
                                this.bgImage = this.images[intValue2];
                                this.bUsebgImage = true;
                            }
                        }
                        else if (substring.toLowerCase().indexOf("<size=") != -1) {
                            final String substring7 = substring.substring(6, substring.length() - 1);
                            try {
                                this.nCurrentFontSize = Integer.valueOf(substring7, 10);
                                if (this.nCurrentFontSize >= 128) {
                                    this.nCurrentFontSize = 127;
                                }
                                else if (this.nCurrentFontSize <= 0) {
                                    this.nCurrentFontSize = 1;
                                }
                                this.setMyFont(this.og);
                            }
                            catch (NumberFormatException ex6) {
                                this.showStatus("Invalid Font Size: " + ex6.toString());
                            }
                        }
                        else if (substring.toLowerCase().indexOf("<bgcolor=") != -1) {
                            final String substring8 = substring.substring(9, substring.length() - 1);
                            try {
                                this.bgColor = new Color(Integer.valueOf(substring8, 16));
                            }
                            catch (NumberFormatException ex7) {
                                this.showStatus("Invalid Color: " + ex7.toString());
                            }
                        }
                        else if (substring.toLowerCase().indexOf("<color=") != -1) {
                            final String substring9 = substring.substring(7, substring.length() - 1);
                            try {
                                this.fgColor = new Color(Integer.valueOf(substring9, 16));
                                this.og.setColor(this.fgColor);
                            }
                            catch (NumberFormatException ex8) {
                                this.showStatus("Invalid Color: " + ex8.toString());
                            }
                        }
                        j = index;
                    }
                    else {
                        if (string.charAt(j) == '<' && string.charAt(j + 1) == '<') {
                            ++j;
                        }
                        if (this.og != null) {
                            this.og.drawString(String.valueOf(string.charAt(j)), n2, 2 + this.nOffset + this.myRect.y + this.nCharHeight * (n + 1));
                            n2 += this.og.getFontMetrics().charWidth(string.charAt(j));
                        }
                    }
                }
            }
            else {
                this.og.drawString(this.myStrings[n].toString(), 2, 2 + this.nOffset + this.myRect.y + this.nCharHeight * (n + 1));
            }
        }
        if (this.bBorder) {
            final Dimension dimension = new Dimension();
            dimension.width = this.myRect.width;
            dimension.height = this.myRect.height;
            this.og.drawLine(0, 0, dimension.width - 1, 0);
            this.og.drawLine(dimension.width - 1, 0, dimension.width - 1, dimension.height - 1);
            this.og.drawLine(dimension.width - 1, dimension.height - 1, 0, dimension.height - 1);
            this.og.drawLine(0, dimension.height - 1, 0, 0);
        }
        graphics.drawImage(this.offscreen, 0, 0, null);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void init() {
        this.authorParam = new StringBuffer(0);
        this.originParam = new StringBuffer(0);
        try {
            this.authorParam.append(this.getParameter("Author"));
            this.originParam.append(this.getParameter("Origin"));
            if (this.authorParam.toString().equalsIgnoreCase("mark ganson") && this.originParam.toString().equalsIgnoreCase("http://www.bigfoot.com/~java4free")) {
                this.bRegistered = true;
            }
            else {
                this.bRegistered = false;
            }
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
            this.bRegistered = false;
        }
        this.tracker = new MediaTracker(this);
        this.bgImage = null;
        for (int i = 0; i < 1000; ++i) {
            this.myStrings[i] = new StringBuffer(0);
            this.bHasEvents[i] = false;
        }
        this.strFontNames[0] = "Helvetica";
        this.strFontNames[1] = "Courier";
        this.strFontNames[2] = "TimesRoman";
        this.nFontStyles[0] = 0;
        this.nFontStyles[1] = 2;
        this.nFontStyles[2] = 1;
        this.nFontStyles[3] = 3;
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 4; ++k) {
                for (int l = 0; l < 128; ++l) {
                    this.myFonts[j][k][l] = null;
                }
            }
        }
        this.visibleLinesParam = new StringBuffer();
        try {
            this.visibleLinesParam.append(this.getParameter("VisibleLines"));
        }
        catch (Exception ex2) {
            this.visibleLinesParam.setLength(0);
            this.visibleLinesParam.append("15");
        }
        try {
            this.nVisibleLines = Integer.parseInt(this.visibleLinesParam.toString());
        }
        catch (NumberFormatException ex3) {
            this.nVisibleLines = 15;
        }
        this.fontNameParam = new StringBuffer();
        try {
            this.fontNameParam.append(this.getParameter("FontName"));
        }
        catch (Exception ex4) {
            this.fontNameParam.setLength(0);
            this.fontNameParam.append("TimesRoman");
        }
        this.strFontName = this.fontNameParam.toString();
        if (this.strFontName.equalsIgnoreCase("Helvetica")) {
            this.nCurrentFontName = 0;
        }
        else if (this.strFontName.equalsIgnoreCase("Courier")) {
            this.nCurrentFontName = 1;
        }
        else if (this.strFontName.equalsIgnoreCase("TimesRoman")) {
            this.nCurrentFontName = 2;
        }
        else {
            this.nCurrentFontName = 2;
        }
        this.fontStyleParam = new StringBuffer();
        try {
            this.fontStyleParam.append(this.getParameter("FontStyle"));
        }
        catch (Exception ex5) {
            this.fontStyleParam.setLength(0);
            this.fontStyleParam.append("Plain");
        }
        this.nFontStyle = 0;
        this.nCurrentFontStyle = 0;
        if (this.fontStyleParam.toString().toLowerCase().indexOf("plain") != -1) {
            this.nFontStyle += 0;
            this.nCurrentFontStyle = 0;
        }
        if (this.fontStyleParam.toString().toLowerCase().indexOf("bold") != -1) {
            ++this.nFontStyle;
            this.nCurrentFontStyle += 2;
        }
        if (this.fontStyleParam.toString().toLowerCase().indexOf("italic") != -1) {
            this.nFontStyle += 2;
            ++this.nCurrentFontStyle;
        }
        this.fontSizeParam = new StringBuffer();
        try {
            this.fontSizeParam.append(this.getParameter("FontSize"));
        }
        catch (Exception ex6) {
            this.fontSizeParam.setLength(0);
            this.fontSizeParam.append("12");
        }
        try {
            this.nFontSize = Integer.parseInt(this.fontSizeParam.toString());
        }
        catch (NumberFormatException ex7) {
            this.nFontSize = 12;
        }
        this.myRect = new Rectangle(0, 0, this.size().width, this.size().height);
        this.nCurrentFontSize = this.nFontSize;
        this.setMyFont();
        this.sponsorString = new StringBuffer("bigfoot.com/~Java4Free");
        this.nNumStrings = 0;
        this.fileError = false;
        try {
            (this.fileParam = new StringBuffer()).append(this.getParameter("FileName"));
        }
        catch (Exception ex8) {
            this.fileParam.setLength(0);
            this.fileParam.append("headlines.txt");
        }
        this.readFile();
        try {
            (this.borderParam = new StringBuffer(0)).append(this.getParameter("Border"));
            if (this.borderParam.length() == 1) {
                this.bBorder = (Integer.valueOf(this.borderParam.toString(), 10) == 1);
            }
            else {
                this.bBorder = false;
            }
        }
        catch (Exception ex9) {
            this.bBorder = false;
        }
        try {
            (this.sleepParam = new StringBuffer(0)).append(this.getParameter("ScrollDelay"));
            if (this.sleepParam.length() == 6) {
                this.nDelay = Integer.valueOf(this.sleepParam.toString(), 10);
            }
            else {
                this.nDelay = 10;
            }
        }
        catch (Exception ex10) {
            this.nDelay = 10;
        }
        try {
            (this.bgColorParam = new StringBuffer(0)).append(this.getParameter("BGColor"));
            if (this.bgColorParam.length() == 6) {
                this.bgColor = new Color(Integer.valueOf(this.bgColorParam.toString(), 16));
            }
            else {
                this.bgColor = Color.white;
            }
            this.setBackground(this.bgColor);
        }
        catch (Exception ex11) {
            this.bgColor = Color.white;
        }
        try {
            (this.fgColorParam = new StringBuffer(0)).append(this.getParameter("FGColor"));
            if (this.fgColorParam.length() == 6) {
                this.fgColor = new Color(Integer.valueOf(this.fgColorParam.toString(), 16));
            }
            else {
                this.fgColor = Color.black;
            }
        }
        catch (Exception ex12) {
            this.fgColor = Color.black;
        }
        this.setForeground(this.fgColor);
        if (this.fgColor == Color.red) {
            this.flashColor = Color.blue;
        }
        else {
            this.flashColor = Color.red;
        }
        if (this.scrollThread == null) {
            this.scrollThread = new Thread(this);
            this.bRunning = true;
            this.scrollThread.start();
        }
    }
    
    public void setBorder(final String s) {
        try {
            this.borderParam.setLength(0);
            this.borderParam.append(s);
            if (this.borderParam.length() == 1) {
                this.bBorder = (Integer.valueOf(this.borderParam.toString(), 10) == 1);
                return;
            }
            this.bBorder = false;
        }
        catch (Exception ex) {
            this.bBorder = false;
        }
    }
    
    public void setFileName(final String s) {
        this.fileError = false;
        try {
            this.fileParam.setLength(0);
            this.fileParam.append(s);
        }
        catch (Exception ex) {
            this.fileParam.setLength(0);
            this.fileParam.append("headlines.txt");
            this.fileError = true;
        }
        this.readFile();
    }
    
    public void setScrollDelay(final String s) {
        try {
            this.sleepParam.setLength(0);
            this.sleepParam.append(s);
            if (this.sleepParam.length() == 6) {
                this.nDelay = Integer.valueOf(this.sleepParam.toString(), 10);
                return;
            }
            this.nDelay = 10;
        }
        catch (Exception ex) {
            this.nDelay = 10;
        }
    }
    
    public void setbgColor(final String s) {
        this.bgColorParam.setLength(0);
        this.bgColorParam.append(s);
        try {
            if (this.bgColorParam.length() == 6) {
                this.bgColor = new Color(Integer.valueOf(this.bgColorParam.toString(), 16));
            }
            else {
                this.bgColor = Color.white;
            }
            this.setBackground(this.bgColor);
        }
        catch (Exception ex) {
            this.bgColor = Color.white;
        }
    }
    
    public void setfgColor(final String s) {
        this.fgColorParam.setLength(0);
        this.fgColorParam.append(s);
        try {
            if (this.fgColorParam.length() == 6) {
                this.fgColor = new Color(Integer.valueOf(this.fgColorParam.toString(), 16));
            }
            else {
                this.fgColor = Color.black;
            }
            this.setBackground(this.fgColor);
        }
        catch (Exception ex) {
            this.fgColor = Color.black;
        }
    }
    
    void readFile() {
        this.nNumImages = 0;
        for (int i = 0; i < 10; ++i) {
            this.images[i] = null;
        }
        for (int j = 0; j < this.nNumStrings; ++j) {
            this.myStrings[j].setLength(0);
            this.bHasEvents[j] = false;
        }
        this.fileError = false;
        try {
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new URL(this.getCodeBase(), this.fileParam.toString()).openConnection().getInputStream()));
            final StringBuffer sb = new StringBuffer();
            this.nNumStrings = 0;
            while (!sb.append(dataInputStream.readLine()).toString().equals("null")) {
                if (this.nNumStrings >= 995) {
                    return;
                }
                this.myStrings[this.nNumStrings].append(sb.toString());
                if (sb.toString().indexOf("<") != -1 && sb.toString().indexOf(">") != -1) {
                    this.bHasEvents[this.nNumStrings] = true;
                    for (int k = 0; k < sb.length(); ++k) {
                        if (sb.charAt(k) == '<' && sb.charAt(k + 1) != '<') {
                            final String substring = sb.toString().substring(k, sb.toString().indexOf(62, k) + 1);
                            if (substring.toLowerCase().indexOf("<loadimage=") != -1) {
                                final String substring2 = substring.substring(11, substring.length() - 1);
                                try {
                                    this.images[this.nNumImages] = this.getImage(new URL(this.getCodeBase(), substring2));
                                    this.tracker.addImage(this.images[this.nNumImages], 1);
                                    ++this.nNumImages;
                                }
                                catch (MalformedURLException ex) {}
                            }
                            else if (substring.toLowerCase().indexOf("<bgimage=") != -1) {
                                final String substring3 = substring.substring(9, substring.length() - 1);
                                try {
                                    this.bgImage = this.getImage(new URL(this.getCodeBase(), substring3));
                                    this.tracker.addImage(this.bgImage, 1);
                                    this.bUsebgImage = true;
                                }
                                catch (MalformedURLException ex2) {}
                            }
                        }
                    }
                }
                ++this.nNumStrings;
                sb.setLength(0);
            }
            dataInputStream.close();
            try {
                this.tracker.waitForAll();
            }
            catch (InterruptedException ex3) {}
        }
        catch (MalformedURLException ex4) {
            this.fileError = true;
        }
        catch (FileNotFoundException ex5) {
            this.fileError = true;
        }
        catch (IOException ex6) {
            this.fileError = true;
        }
        catch (SecurityException ex7) {
            this.fileError = true;
        }
    }
    
    public void start() {
        this.bRunning = true;
        (this.scrollThread = new Thread(this)).start();
    }
    
    public void stop() {
        this.bRunning = false;
        this.scrollThread = null;
        this.og = null;
        this.offscreen = null;
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        long currentTimeMillis = System.currentTimeMillis();
        while (Thread.currentThread() == this.scrollThread) {
            this.repaint();
            try {
                currentTimeMillis += this.nDelay;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.bRegistered) {
            this.showStatus("HeadLiner is FREE!  Click Now!  bigfoot.com/~Java4Free");
        }
        else {
            this.showStatus(this.strStatus);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            if (!this.bRegistered) {
                this.getAppletContext().showDocument(new URL("http://www.bigfoot.com/~Java4Free"), "_blank");
            }
            else {
                this.getAppletContext().showDocument(new URL(this.strURL), "_blank");
            }
        }
        catch (MalformedURLException ex) {}
        return true;
    }
    
    public String getAppletInfo() {
        return "HeadLiner Applet (C) Copyright 1999, Mark Ganson.  bigfoot.com/~java4free";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "BGColor", "int", "background color (6 hex digits rrggbb)" }, { "FGColor", "int", "foreground color (6 hex digits rrggbb)" }, { "ScrollDelay", "int", "scroll delay factor (6 base ten digits)" }, { "Border", "boolean", "1=yes, 0=no" }, { "FileName", "URL", "file containing text to scroll (must be relative URL)" }, { "FontName", "String", "Name of font (courier, timesroman, helvetica)" }, { "FontStyle", "String", "Plain, Bold, Italic, BoldItalic" }, { "FontSize", "int", "Point size of font" }, { "VisibleLines", "int", "Number of visible lines in the scroll box" }, { "Author", "String", "Must be equal to \"Mark Ganson\" to enable embedded links" }, { "Origin", "String", "Must be equal to \"http://www.bigfoot.com/~java4free\" to enable links" } };
    }
    
    public HeadLiner2() {
        this.myStrings = new StringBuffer[1000];
        this.bHasEvents = new boolean[1000];
        this.myFonts = new Font[3][4][128];
        this.strFontNames = new String[3];
        this.nFontStyles = new int[4];
        this.bRegistered = false;
        this.bSoundIsReset = true;
        this.strURL = "http://www.bigfoot.com/~Java4Free";
        this.strStatus = "HeadLiner is FREE!  Click Now!  bigfoot.com/~Java4Free";
        this.bRunning = true;
        this.bUsebgImage = false;
        this.images = new Image[10];
    }
}
