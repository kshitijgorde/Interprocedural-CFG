import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.net.URL;
import java.applet.AudioClip;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SIRtypeFree extends Applet implements MouseListener, Runnable
{
    Thread thread;
    AudioClip[] type;
    URL newURL;
    Graphics pad;
    Graphics textPAD;
    Image buffer;
    Image textDISPLAY;
    Color[] textCOLOR;
    Color fadeCOLOR;
    Color backgroundCOLOR;
    Font font;
    FontMetrics fontMETRICS;
    int fontTYPE;
    int[] fontSIZE;
    int[] textEFFECT;
    int textLENGTH;
    int textNUMBER;
    int textCOUNT;
    int[] textALIGNMENT;
    int[] textHOLD;
    int[] typeWAIT;
    int textX;
    int textY;
    int bufferX;
    int bufferY;
    int bufferWIDTH;
    int bufferHEIGHT;
    int[] typeSPEED;
    int fRed;
    int fGreen;
    int fBlue;
    int bRed;
    int bGreen;
    int bBlue;
    int tokenNUMBER;
    int[] aeSPEED;
    boolean flag;
    boolean[] soundFLAG;
    boolean fadeout;
    boolean rollup;
    boolean shrinkout;
    boolean textBUFFER;
    boolean noURL;
    String[] text;
    String subText;
    String parmsoundNAME;
    String[] soundNAME;
    String parmfontSIZE;
    String parmfontNAME;
    String[] fontNAME;
    String parmfontITALIC;
    String[] fontITALIC;
    String parmfontBOLD;
    String[] fontBOLD;
    String message;
    String parmAfterEffect;
    String[] AfterEffect;
    
    public SIRtypeFree() {
        this.newURL = null;
        this.fontTYPE = 0;
        this.textLENGTH = 0;
        this.textNUMBER = 0;
        this.textCOUNT = 1;
        this.bufferX = 0;
        this.bufferY = 0;
        this.tokenNUMBER = 0;
        this.fadeout = false;
        this.rollup = false;
        this.shrinkout = false;
        this.textBUFFER = false;
    }
    
    public void init() {
        final String parmgetstr = this.parmgetstr("BgColor", "255,255,255");
        final String parmgetstr2 = this.parmgetstr("FontColor", "0,0,0");
        this.message = this.parmgetstr("Text", "No Messages Have Been Put in the Parameters");
        this.parmAfterEffect = this.parmgetstr("AfterEffect", "");
        final String parmgetstr3 = this.parmgetstr("AfterEffectSpeed", "30");
        this.parmfontNAME = this.parmgetstr("Font", "Helvetica");
        this.parmfontSIZE = this.parmgetstr("FontSize", "12");
        final String parmgetstr4 = this.parmgetstr("TextAlignment", "LEFT");
        final String parmgetstr5 = this.parmgetstr("TypeWait", "0");
        final String parmgetstr6 = this.parmgetstr("TypeSpeed", "60");
        final String parmgetstr7 = this.parmgetstr("TextHold", "2000");
        this.parmfontITALIC = new String(this.parmgetstr("FontItalic", "No"));
        this.parmfontBOLD = new String(this.parmgetstr("FontBold", "No"));
        this.parmsoundNAME = this.parmgetstr("Sound", "none");
        try {
            this.newURL = new URL("http://www.net800.co.uk/netstart/sirius");
            this.noURL = false;
        }
        catch (MalformedURLException ex) {
            this.noURL = true;
        }
        if (this.newURL.toString().length() != 39) {
            throw new SecurityException("Unauthorized Modification Please Contact chris@applets.freeserve.co.uk");
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.message, "|");
        this.text = new String[stringTokenizer.countTokens() + 1];
        this.textEFFECT = new int[stringTokenizer.countTokens() + 1];
        while (stringTokenizer.hasMoreTokens()) {
            this.text[++this.textNUMBER] = stringTokenizer.nextToken();
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(parmgetstr, ",");
        this.setBackground(this.backgroundCOLOR = new Color(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken())));
        final StringTokenizer stringTokenizer3 = new StringTokenizer(parmgetstr2, "|");
        (this.textCOLOR = new Color[this.textNUMBER + 1])[1] = new Color(0, 0, 0);
        while (stringTokenizer3.hasMoreTokens() || this.tokenNUMBER <= this.textNUMBER - 1) {
            if (this.tokenNUMBER <= this.textNUMBER - 1) {
                if (stringTokenizer3.hasMoreTokens()) {
                    final StringTokenizer stringTokenizer4 = new StringTokenizer(stringTokenizer3.nextToken(), ",");
                    this.textCOLOR[++this.tokenNUMBER] = new Color(Integer.parseInt(stringTokenizer4.nextToken()), Integer.parseInt(stringTokenizer4.nextToken()), Integer.parseInt(stringTokenizer4.nextToken()));
                }
                else {
                    this.textCOLOR[++this.tokenNUMBER] = this.textCOLOR[1];
                }
            }
            else {
                final String s = new String(stringTokenizer3.nextToken());
            }
        }
        this.tokenNUMBER = 0;
        this.fadeCOLOR = this.textCOLOR[1];
        final StringTokenizer stringTokenizer5 = new StringTokenizer(this.parmAfterEffect, "|");
        (this.AfterEffect = new String[this.textNUMBER + 1])[1] = " ";
        while (stringTokenizer5.hasMoreTokens() || this.tokenNUMBER <= this.textNUMBER - 1) {
            if (this.tokenNUMBER <= this.textNUMBER - 1) {
                if (stringTokenizer5.hasMoreTokens()) {
                    this.AfterEffect[++this.tokenNUMBER] = stringTokenizer5.nextToken();
                }
                else {
                    this.AfterEffect[++this.tokenNUMBER] = this.AfterEffect[1];
                }
                this.textEFFECT[this.tokenNUMBER] = 0;
                if (this.AfterEffect[this.tokenNUMBER].equalsIgnoreCase("shrink")) {
                    this.textEFFECT[this.tokenNUMBER] = 1;
                }
                if (this.AfterEffect[this.tokenNUMBER].equalsIgnoreCase("scrollup")) {
                    this.textEFFECT[this.tokenNUMBER] = 2;
                }
                if (!this.AfterEffect[this.tokenNUMBER].equalsIgnoreCase("fadeout")) {
                    continue;
                }
                this.textEFFECT[this.tokenNUMBER] = 3;
            }
            else {
                final String s2 = new String(stringTokenizer5.nextToken());
            }
        }
        this.tokenNUMBER = 0;
        final StringTokenizer stringTokenizer6 = new StringTokenizer(parmgetstr3, "|");
        (this.aeSPEED = new int[this.textNUMBER + 1])[1] = 30;
        while (stringTokenizer6.hasMoreTokens() || this.tokenNUMBER <= this.textNUMBER - 1) {
            if (this.tokenNUMBER <= this.textNUMBER - 1) {
                if (stringTokenizer6.hasMoreTokens()) {
                    this.aeSPEED[++this.tokenNUMBER] = Integer.parseInt(stringTokenizer6.nextToken());
                }
                else {
                    this.aeSPEED[++this.tokenNUMBER] = this.aeSPEED[1];
                }
            }
            else {
                final String s3 = new String(stringTokenizer6.nextToken());
            }
        }
        this.tokenNUMBER = 0;
        final StringTokenizer stringTokenizer7 = new StringTokenizer(this.parmfontNAME, "|");
        (this.fontNAME = new String[this.textNUMBER + 1])[1] = "Helvetica";
        while (stringTokenizer7.hasMoreTokens() || this.tokenNUMBER <= this.textNUMBER - 1) {
            if (this.tokenNUMBER <= this.textNUMBER - 1) {
                if (stringTokenizer7.hasMoreTokens()) {
                    this.fontNAME[++this.tokenNUMBER] = stringTokenizer7.nextToken();
                }
                else {
                    this.fontNAME[++this.tokenNUMBER] = this.fontNAME[1];
                }
            }
            else {
                final String s4 = new String(stringTokenizer7.nextToken());
            }
        }
        this.tokenNUMBER = 0;
        final StringTokenizer stringTokenizer8 = new StringTokenizer(this.parmfontITALIC, "|");
        (this.fontITALIC = new String[this.textNUMBER + 1])[1] = "No";
        while (stringTokenizer8.hasMoreTokens() || this.tokenNUMBER <= this.textNUMBER - 1) {
            if (this.tokenNUMBER <= this.textNUMBER - 1) {
                if (stringTokenizer8.hasMoreTokens()) {
                    this.fontITALIC[++this.tokenNUMBER] = stringTokenizer8.nextToken();
                }
                else {
                    this.fontITALIC[++this.tokenNUMBER] = this.fontITALIC[1];
                }
            }
            else {
                final String s5 = new String(stringTokenizer8.nextToken());
            }
        }
        this.tokenNUMBER = 0;
        final StringTokenizer stringTokenizer9 = new StringTokenizer(this.parmfontBOLD, "|");
        (this.fontBOLD = new String[this.textNUMBER + 1])[1] = "No";
        while (stringTokenizer9.hasMoreTokens() || this.tokenNUMBER <= this.textNUMBER - 1) {
            if (this.tokenNUMBER <= this.textNUMBER - 1) {
                if (stringTokenizer9.hasMoreTokens()) {
                    this.fontBOLD[++this.tokenNUMBER] = stringTokenizer9.nextToken();
                }
                else {
                    this.fontBOLD[++this.tokenNUMBER] = this.fontBOLD[1];
                }
            }
            else {
                final String s6 = new String(stringTokenizer9.nextToken());
            }
        }
        this.tokenNUMBER = 0;
        final StringTokenizer stringTokenizer10 = new StringTokenizer(this.parmfontSIZE, "|");
        (this.fontSIZE = new int[this.textNUMBER + 1])[1] = 12;
        while (stringTokenizer10.hasMoreTokens() || this.tokenNUMBER <= this.textNUMBER - 1) {
            if (this.tokenNUMBER <= this.textNUMBER - 1) {
                if (stringTokenizer10.hasMoreTokens()) {
                    this.fontSIZE[++this.tokenNUMBER] = Integer.parseInt(stringTokenizer10.nextToken());
                }
                else {
                    this.fontSIZE[++this.tokenNUMBER] = this.fontSIZE[1];
                }
            }
            else {
                final String s7 = new String(stringTokenizer10.nextToken());
            }
        }
        this.tokenNUMBER = 0;
        if (this.fontITALIC[1].equalsIgnoreCase("Yes")) {
            this.fontTYPE = 2;
        }
        if (this.fontBOLD[1].equalsIgnoreCase("Yes")) {
            this.fontTYPE = 1;
        }
        if (this.fontBOLD[1].equalsIgnoreCase("Yes") && this.fontITALIC[1].equalsIgnoreCase("Yes")) {
            this.fontTYPE = 3;
        }
        this.font = new Font(this.fontNAME[1], this.fontTYPE, this.fontSIZE[1]);
        this.fontMETRICS = this.getFontMetrics(this.font);
        final StringTokenizer stringTokenizer11 = new StringTokenizer(parmgetstr4, "|");
        (this.textALIGNMENT = new int[this.textNUMBER + 1])[1] = 1;
        while (stringTokenizer11.hasMoreTokens() || this.tokenNUMBER <= this.textNUMBER - 1) {
            if (this.tokenNUMBER <= this.textNUMBER - 1) {
                if (stringTokenizer11.hasMoreTokens()) {
                    this.textALIGNMENT[++this.tokenNUMBER] = 1;
                    final String nextToken = stringTokenizer11.nextToken();
                    if (nextToken.equalsIgnoreCase("CENTRE")) {
                        this.textALIGNMENT[this.tokenNUMBER] = 2;
                    }
                    else {
                        if (!nextToken.equalsIgnoreCase("RIGHT")) {
                            continue;
                        }
                        this.textALIGNMENT[this.tokenNUMBER] = 3;
                    }
                }
                else {
                    this.textALIGNMENT[++this.tokenNUMBER] = this.textALIGNMENT[1];
                }
            }
            else {
                final String s8 = new String(stringTokenizer11.nextToken());
            }
        }
        this.tokenNUMBER = 0;
        switch (this.textALIGNMENT[1]) {
            case 1: {
                this.textX = 10;
                break;
            }
            case 2: {
                this.textX = (this.size().width - this.fontMETRICS.stringWidth(this.text[this.textCOUNT])) / 2;
                if (this.textX <= 9) {
                    this.textX = 10;
                    break;
                }
                break;
            }
            case 3: {
                this.textX = this.size().width - this.fontMETRICS.stringWidth(this.text[this.textCOUNT]) - 10;
                if (this.textX <= 9) {
                    this.textX = 10;
                    break;
                }
                break;
            }
        }
        this.textY = (this.size().height + this.fontMETRICS.getHeight()) / 2;
        this.bufferWIDTH = this.size().width;
        this.bufferHEIGHT = this.size().height;
        final StringTokenizer stringTokenizer12 = new StringTokenizer(parmgetstr7, "|");
        (this.textHOLD = new int[this.textNUMBER + 1])[1] = 2000;
        while (stringTokenizer12.hasMoreTokens() || this.tokenNUMBER <= this.textNUMBER - 1) {
            if (this.tokenNUMBER <= this.textNUMBER - 1) {
                if (stringTokenizer12.hasMoreTokens()) {
                    this.textHOLD[++this.tokenNUMBER] = Integer.parseInt(stringTokenizer12.nextToken());
                }
                else {
                    this.textHOLD[++this.tokenNUMBER] = this.textHOLD[1];
                }
            }
            else {
                final String s9 = new String(stringTokenizer12.nextToken());
            }
        }
        this.tokenNUMBER = 0;
        final StringTokenizer stringTokenizer13 = new StringTokenizer(parmgetstr6, "|");
        (this.typeSPEED = new int[this.textNUMBER + 1])[1] = 60;
        while (stringTokenizer13.hasMoreTokens() || this.tokenNUMBER <= this.textNUMBER - 1) {
            if (this.tokenNUMBER <= this.textNUMBER - 1) {
                if (stringTokenizer13.hasMoreTokens()) {
                    this.typeSPEED[++this.tokenNUMBER] = Integer.parseInt(stringTokenizer13.nextToken());
                }
                else {
                    this.typeSPEED[++this.tokenNUMBER] = this.typeSPEED[1];
                }
            }
            else {
                final String s10 = new String(stringTokenizer13.nextToken());
            }
        }
        this.tokenNUMBER = 0;
        final StringTokenizer stringTokenizer14 = new StringTokenizer(parmgetstr5, "|");
        (this.typeWAIT = new int[this.textNUMBER + 1])[1] = 0;
        while (stringTokenizer14.hasMoreTokens() || this.tokenNUMBER <= this.textNUMBER - 1) {
            if (this.tokenNUMBER <= this.textNUMBER - 1) {
                if (stringTokenizer14.hasMoreTokens()) {
                    this.typeWAIT[++this.tokenNUMBER] = Integer.parseInt(stringTokenizer14.nextToken());
                }
                else {
                    this.typeWAIT[++this.tokenNUMBER] = this.typeWAIT[1];
                }
            }
            else {
                final String s11 = new String(stringTokenizer14.nextToken());
            }
        }
        this.tokenNUMBER = 0;
        final StringTokenizer stringTokenizer15 = new StringTokenizer(this.parmsoundNAME, "|");
        this.soundNAME = new String[this.textNUMBER + 1];
        this.soundFLAG = new boolean[this.textNUMBER + 1];
        this.type = new AudioClip[this.textNUMBER + 1];
        this.soundNAME[1] = "none";
        while (stringTokenizer15.hasMoreTokens() || this.tokenNUMBER <= this.textNUMBER - 1) {
            if (this.tokenNUMBER <= this.textNUMBER - 1) {
                if (stringTokenizer15.hasMoreTokens()) {
                    this.soundNAME[++this.tokenNUMBER] = stringTokenizer15.nextToken();
                    if (!this.soundNAME[this.tokenNUMBER].endsWith(".au")) {
                        continue;
                    }
                    this.soundFLAG[this.tokenNUMBER] = true;
                    this.type[this.tokenNUMBER] = this.getAudioClip(this.getDocumentBase(), this.soundNAME[this.tokenNUMBER]);
                }
                else {
                    this.soundNAME[++this.tokenNUMBER] = this.soundNAME[1];
                    if (!this.soundNAME[this.tokenNUMBER].endsWith(".au")) {
                        continue;
                    }
                    this.soundFLAG[this.tokenNUMBER] = true;
                    this.type[this.tokenNUMBER] = this.getAudioClip(this.getDocumentBase(), this.soundNAME[this.tokenNUMBER]);
                }
            }
            else {
                final String s12 = new String(stringTokenizer15.nextToken());
            }
        }
        this.tokenNUMBER = 0;
        this.subText = new String(" ");
        this.addMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.noURL) {
            this.getAppletContext().showDocument(this.newURL);
        }
    }
    
    public synchronized void mouseEntered(final MouseEvent mouseEvent) {
        if (!this.noURL) {
            this.setCursor(new Cursor(12));
            this.showStatus(this.newURL.toString());
        }
    }
    
    public synchronized void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
        this.showStatus("");
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        this.buffer = this.createImage(this.size().width, this.size().height);
        this.pad = this.buffer.getGraphics();
        if (this.textBUFFER) {
            this.textDISPLAY = this.createImage(this.size().width, this.size().height);
            (this.textPAD = this.textDISPLAY.getGraphics()).setFont(this.font);
            this.textPAD.setColor(this.fadeCOLOR);
            this.textPAD.drawString(this.subText, this.textX, this.textY);
            this.pad.drawImage(this.textDISPLAY, this.bufferX, this.bufferY, this.bufferWIDTH, this.bufferHEIGHT, this);
        }
        else {
            this.pad.setFont(this.font);
            this.pad.setColor(this.fadeCOLOR);
            this.pad.drawString(this.subText, this.textX, this.textY);
        }
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public int parmgetint(final String s, final int n, final int n2, final int n3) {
        final String parameter = this.getParameter(s);
        int int1;
        if (parameter == null) {
            int1 = n;
        }
        else {
            int1 = Integer.parseInt(parameter);
        }
        if (int1 > n3) {
            int1 = n3;
        }
        if (int1 < n2) {
            int1 = n2;
        }
        return int1;
    }
    
    public String parmgetstr(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        String s3;
        if (parameter == null) {
            s3 = s2;
        }
        else {
            s3 = parameter;
        }
        return s3;
    }
    
    public void run() {
        while (this.flag) {
            if (this.textLENGTH >= this.text[this.textCOUNT].length()) {
                this.textLENGTH = 0;
                try {
                    Thread.sleep(this.textHOLD[this.textCOUNT]);
                }
                catch (InterruptedException ex) {
                    System.out.println("The thread failed to sleep");
                }
                switch (this.textEFFECT[this.textCOUNT]) {
                    case 1: {
                        this.shrinkout = true;
                        break;
                    }
                    case 2: {
                        this.rollup = true;
                        break;
                    }
                    case 3: {
                        this.fadeout = true;
                        break;
                    }
                }
                if (this.shrinkout) {
                    this.textBUFFER = true;
                    while (this.bufferHEIGHT >= 1 || this.bufferWIDTH >= 1) {
                        this.bufferHEIGHT -= this.size().height / 20;
                        this.bufferWIDTH -= this.size().width / 20;
                        if (this.bufferHEIGHT <= -1) {
                            this.bufferHEIGHT = 0;
                        }
                        if (this.bufferWIDTH <= -1) {
                            this.bufferWIDTH = 0;
                        }
                        this.bufferX = (this.size().width - this.bufferWIDTH) / 2;
                        this.bufferY = (this.size().height - this.bufferHEIGHT) / 2;
                        this.repaint();
                        try {
                            Thread.sleep(this.aeSPEED[this.textCOUNT]);
                        }
                        catch (InterruptedException ex2) {
                            System.out.println("The thread failed to sleep");
                        }
                    }
                    this.textBUFFER = false;
                    this.bufferHEIGHT = this.size().height;
                    this.bufferWIDTH = this.size().width;
                    this.bufferX = 0;
                    this.bufferY = 0;
                }
                if (this.rollup) {
                    this.textBUFFER = true;
                    while (this.bufferHEIGHT >= 1) {
                        this.bufferHEIGHT -= this.size().height / 20;
                        if (this.bufferHEIGHT <= -1) {
                            this.bufferHEIGHT = 0;
                        }
                        this.repaint();
                        try {
                            Thread.sleep(this.aeSPEED[this.textCOUNT]);
                        }
                        catch (InterruptedException ex3) {
                            System.out.println("The thread failed to sleep");
                        }
                    }
                    this.textBUFFER = false;
                    this.bufferHEIGHT = this.size().height;
                }
                if (this.fadeout) {
                    int red = this.textCOLOR[this.textCOUNT].getRed();
                    int green = this.textCOLOR[this.textCOUNT].getGreen();
                    int blue = this.textCOLOR[this.textCOUNT].getBlue();
                    final int red2 = this.backgroundCOLOR.getRed();
                    final int green2 = this.backgroundCOLOR.getGreen();
                    final int blue2 = this.backgroundCOLOR.getBlue();
                    while (red != red2 || green != green2 || blue != blue2) {
                        if (red != red2) {
                            if (red >= red2) {
                                red -= 10;
                            }
                            else {
                                red += 10;
                            }
                            if (red - red2 <= 9 && red - red2 >= -9) {
                                red = red2;
                            }
                        }
                        if (green != green2) {
                            if (green >= green2) {
                                green -= 10;
                            }
                            else {
                                green += 10;
                            }
                            if (green - green2 <= 9 && green - green2 >= -9) {
                                green = green2;
                            }
                        }
                        if (blue != blue2) {
                            if (blue >= blue2) {
                                blue -= 10;
                            }
                            else {
                                blue += 10;
                            }
                            if (blue - blue2 <= 9 && blue - blue2 >= -9) {
                                blue = blue2;
                            }
                        }
                        this.fadeCOLOR = new Color(red, green, blue);
                        this.repaint();
                        try {
                            Thread.sleep(this.aeSPEED[this.textCOUNT]);
                        }
                        catch (InterruptedException ex4) {
                            System.out.println("The thread failed to sleep");
                        }
                    }
                    this.fadeCOLOR = this.textCOLOR[this.textCOUNT];
                }
                this.shrinkout = false;
                this.rollup = false;
                this.fadeout = false;
                this.subText = new String("");
                this.repaint();
                if (this.textCOUNT >= this.textNUMBER) {
                    this.textCOUNT = 0;
                }
                ++this.textCOUNT;
                this.fadeCOLOR = this.textCOLOR[this.textCOUNT];
                this.fontTYPE = 0;
                if (this.fontITALIC[this.textCOUNT].equalsIgnoreCase("Yes")) {
                    this.fontTYPE = 2;
                }
                if (this.fontBOLD[this.textCOUNT].equalsIgnoreCase("Yes")) {
                    this.fontTYPE = 1;
                }
                if (this.fontBOLD[this.textCOUNT].equalsIgnoreCase("Yes") && this.fontITALIC[this.textCOUNT].equalsIgnoreCase("Yes")) {
                    this.fontTYPE = 3;
                }
                this.font = new Font(this.fontNAME[this.textCOUNT], this.fontTYPE, this.fontSIZE[this.textCOUNT]);
                this.fontMETRICS = this.getFontMetrics(this.font);
                switch (this.textALIGNMENT[this.textCOUNT]) {
                    case 1: {
                        this.textX = 10;
                        break;
                    }
                    case 2: {
                        this.textX = (this.size().width - this.fontMETRICS.stringWidth(this.text[this.textCOUNT])) / 2;
                        if (this.textX <= 9) {
                            this.textX = 10;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        this.textX = this.size().width - this.fontMETRICS.stringWidth(this.text[this.textCOUNT]) - 10;
                        if (this.textX <= 9) {
                            this.textX = 10;
                            break;
                        }
                        break;
                    }
                }
                try {
                    Thread.sleep(this.typeWAIT[this.textCOUNT]);
                }
                catch (InterruptedException ex5) {
                    System.out.println("The thread failed to sleep");
                }
            }
            ++this.textLENGTH;
            this.subText = new String(this.text[this.textCOUNT].substring(0, this.textLENGTH));
            if (this.textX + this.fontMETRICS.stringWidth(this.subText) >= this.size().width - 9) {
                this.textX = this.size().width - this.fontMETRICS.stringWidth(this.subText) - 10;
            }
            if (this.soundFLAG[this.textCOUNT]) {
                this.type[this.textCOUNT].play();
            }
            this.repaint();
            try {
                Thread.sleep(this.typeSPEED[this.textCOUNT]);
            }
            catch (InterruptedException ex6) {
                System.out.println("The thread failed to sleep");
            }
        }
    }
    
    public void start() {
        this.thread = new Thread(this);
        this.flag = true;
        this.thread.start();
    }
    
    public void stop() {
        this.flag = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
