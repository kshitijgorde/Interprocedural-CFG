import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Marquee102V05 extends Applet implements Runnable
{
    public static final int HORIZONTAL_LINKS = 1;
    public static final int HORIZONTAL_RECHTS = 2;
    String text;
    String tmptext;
    String schriftart;
    int schriftgrad;
    int rgbVordergrund;
    int rgbHintergrund;
    int geschwindigkeit;
    int schriftstil;
    int pixelscroll;
    int anzahlText;
    int position;
    Font font;
    FontMetrics metrics;
    int lg;
    StringBuffer schrift;
    static Thread th;
    Image offScreen;
    Dimension offScreenDimension;
    String[] buchstaben;
    int[] yValues;
    int[] xValues;
    int numberOfBuchstaben;
    int funktion;
    int frame;
    String stil;
    String funkStr;
    String fontsizePar;
    String rgbtextPar;
    String rgbbackPar;
    String delayPar;
    String pixelscrollPar;
    
    public Marquee102V05() {
    }
    
    public Marquee102V05(final String tmptext, final String schriftart, final String stil, final String funkStr, final String fontsizePar, final String rgbtextPar, final String rgbbackPar, final String delayPar, final String pixelscrollPar) {
        this.tmptext = tmptext;
        this.schriftart = schriftart;
        this.stil = stil;
        this.funkStr = funkStr;
        this.fontsizePar = fontsizePar;
        this.rgbtextPar = rgbtextPar;
        this.rgbbackPar = rgbbackPar;
        this.delayPar = delayPar;
        this.pixelscrollPar = pixelscrollPar;
        this.parseParams();
    }
    
    public void init() {
        this.tmptext = this.getParameter("text");
        this.schriftart = this.getParameter("font");
        this.stil = this.getParameter("font style");
        this.funkStr = this.getParameter("function");
        this.fontsizePar = this.getParameter("font size");
        this.rgbtextPar = this.getParameter("rgbText");
        this.rgbbackPar = this.getParameter("rgbBackground");
        this.delayPar = this.getParameter("delay");
        this.pixelscrollPar = this.getParameter("pixelscroll");
        this.parseParams();
    }
    
    public void parseParams() {
        if (this.stil != null) {
            if (this.stil.equals("bold")) {
                this.schriftstil = 1;
            }
            else if (this.stil.equals("italic")) {
                this.schriftstil = 2;
            }
            else if (this.stil.equals("bold+italic")) {
                this.schriftstil = 3;
            }
            else {
                this.schriftstil = 0;
            }
        }
        else {
            this.schriftstil = 0;
        }
        if (this.funkStr != null) {
            if (this.funkStr.equalsIgnoreCase("horizontal right")) {
                this.funktion = 2;
            }
            else if (this.funkStr.equalsIgnoreCase("horizontal left")) {
                this.funktion = 1;
            }
        }
        else {
            this.funktion = 1;
        }
        if (this.tmptext == null) {
            this.tmptext = "Please enter your text!";
        }
        if (this.schriftart == null) {
            this.schriftart = "Helvetica";
        }
        try {
            if (this.fontsizePar != null) {
                this.schriftgrad = Integer.parseInt(this.fontsizePar);
            }
            else {
                this.schriftgrad = 12;
            }
            if (this.rgbtextPar != null) {
                this.rgbVordergrund = Integer.parseInt(this.rgbtextPar, 16);
            }
            else {
                this.rgbVordergrund = 65536;
            }
            if (this.rgbbackPar != null) {
                this.rgbHintergrund = Integer.parseInt(this.rgbbackPar, 16);
            }
            else {
                this.rgbHintergrund = 65535;
            }
            if (this.delayPar != null) {
                this.geschwindigkeit = Integer.parseInt(this.delayPar);
            }
            else {
                this.geschwindigkeit = 10;
            }
            if (this.pixelscrollPar != null) {
                this.pixelscroll = Integer.parseInt(this.pixelscrollPar);
            }
            else {
                this.pixelscroll = 2;
            }
            if (this.pixelscroll < 0) {
                this.pixelscroll *= -1;
            }
        }
        catch (NumberFormatException ex) {
            this.schriftgrad = 12;
            this.rgbVordergrund = 65536;
            this.rgbHintergrund = 65535;
            this.geschwindigkeit = 10;
            this.pixelscroll = 2;
            this.tmptext = "Error in parameters!";
            System.out.println("Parameterfehler: Zahlen falsch zugewiesen!!!" + ex);
        }
    }
    
    public void start() {
        this.position = 10;
        this.setBackground(new Color(this.rgbHintergrund));
        this.setFont(this.font = new Font(this.schriftart, this.schriftstil, this.schriftgrad));
        this.metrics = this.getFontMetrics(this.font);
        this.anzahlText = this.size().width / this.metrics.stringWidth(this.tmptext) + 3;
        this.text = new String("");
        for (int i = 0; i < this.anzahlText; ++i) {
            this.text = this.text + this.tmptext + "   ";
        }
        int n = 0;
        this.numberOfBuchstaben = this.text.length();
        this.buchstaben = new String[this.numberOfBuchstaben];
        this.yValues = new int[this.numberOfBuchstaben];
        this.xValues = new int[this.numberOfBuchstaben];
        for (int j = 0; j < this.numberOfBuchstaben; ++j) {
            this.buchstaben[j] = this.text.substring(j, j + 1);
            this.xValues[j] = n;
            n += this.metrics.charWidth(this.buchstaben[j].toCharArray()[0]);
            this.yValues[j] = this.size().height / 2;
        }
        this.frame = 0;
        (Marquee102V05.th = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            switch (this.funktion) {
                case 1: {
                    this.position -= this.pixelscroll;
                    if (-this.position > this.metrics.stringWidth(this.tmptext + "   ")) {
                        this.position += this.metrics.stringWidth(this.tmptext + "   ");
                        break;
                    }
                    break;
                }
                case 2: {
                    this.position += this.pixelscroll;
                    if (this.position > 0) {
                        this.position -= this.metrics.stringWidth(this.tmptext + "   ");
                        break;
                    }
                    break;
                }
            }
            this.repaint();
            try {
                Thread.sleep(this.geschwindigkeit);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.offScreen == null || this.offScreenDimension.width != this.size().width || this.offScreenDimension.height != this.size().height) {
            this.offScreen = this.createImage(this.size().width, this.size().height);
            this.offScreenDimension = this.size();
        }
        final Graphics graphics2 = this.offScreen.getGraphics();
        graphics2.setColor(new Color(this.rgbHintergrund));
        graphics2.fillRect(0, 0, this.size().width, this.size().height);
        graphics2.setColor(new Color(this.rgbVordergrund));
        this.paintMarquee(graphics2);
        graphics.drawImage(this.offScreen, 0, 0, this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offScreen != null) {
            graphics.drawImage(this.offScreen, 0, 0, this);
        }
    }
    
    private void paintMarquee(final Graphics graphics) {
        this.setFont(this.font);
        for (int i = 0; i < this.numberOfBuchstaben; ++i) {
            graphics.drawString(this.buchstaben[i], this.xValues[i] + this.position, this.yValues[i]);
        }
    }
    
    public void stop() {
        Marquee102V05.th.stop();
    }
    
    public String getAppletInfo() {
        return "Marquee Version 1.04. Copyright (c) 1998-2000 by Matthias Burg.\nInternet: www.burgsoft.de\neMail: Matthias@burgsoft.de";
    }
}
