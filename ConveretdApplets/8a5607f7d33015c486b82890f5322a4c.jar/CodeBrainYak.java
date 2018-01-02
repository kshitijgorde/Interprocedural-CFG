import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.net.URL;
import java.applet.AudioClip;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CodeBrainYak extends Applet implements Runnable
{
    private Thread Y;
    private Color backC;
    boolean pf;
    private AudioClip A;
    private URL U;
    private String T;
    private int W;
    private Image[] image;
    private MediaTracker MT;
    private int F;
    private int nF;
    private boolean mHot;
    private Image offI;
    private Graphics offG;
    private int LM;
    private String L;
    private int tX;
    private int tY;
    private int tSX;
    private int tSY;
    private Color tC;
    private Color tHotC;
    private int bMode;
    private int bStyle;
    private int bIn;
    private int bThick;
    private Color bC;
    private Color bHotC;
    private Font fFont;
    private int fSize;
    private String fName;
    private String H;
    
    public void init() {
        this.tX = 5;
        this.tY = this.size().height - 5;
        final String parameter = this.getParameter("Notice");
        if (parameter == null) {
            this.pf = false;
        }
        if (parameter != null && parameter.equalsIgnoreCase("Applet by www.CodeBrain.com")) {
            this.pf = true;
        }
        final String parameter2 = this.getParameter("BackgroundColor");
        if (parameter2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, ",");
            this.backC = new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        this.setBackground(this.backC);
        final String parameter3 = this.getParameter("Text");
        if (parameter3 != null) {
            this.L = parameter3;
        }
        final String parameter4 = this.getParameter("TextXY");
        if (parameter4 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter4, ",");
            this.tX = Integer.parseInt(stringTokenizer2.nextToken());
            this.tY = Integer.parseInt(stringTokenizer2.nextToken());
        }
        final String parameter5 = this.getParameter("TextShiftXY");
        if (parameter5 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter5, ",");
            this.tSX = Integer.parseInt(stringTokenizer3.nextToken());
            this.tSY = Integer.parseInt(stringTokenizer3.nextToken());
        }
        final String parameter6 = this.getParameter("FontSize");
        if (parameter6 != null) {
            this.fSize = Integer.parseInt(parameter6);
        }
        final String parameter7 = this.getParameter("FontName");
        if (parameter7 != null) {
            this.fName = parameter7;
        }
        final String parameter8 = this.getParameter("FontStyle");
        if (parameter8 != null) {
            if (parameter8.equalsIgnoreCase("PLAIN")) {
                this.fFont = new Font(this.fName, 0, this.fSize);
            }
            else if (parameter8.equalsIgnoreCase("BOLD")) {
                this.fFont = new Font(this.fName, 1, this.fSize);
            }
            else if (parameter8.equalsIgnoreCase("ITALIC")) {
                this.fFont = new Font(this.fName, 2, this.fSize);
            }
            else if (parameter8.equalsIgnoreCase("BOLDITALIC")) {
                this.fFont = new Font(this.fName, 3, this.fSize);
            }
            else {
                this.fFont = new Font(this.fName, 0, this.fSize);
            }
        }
        else {
            this.fFont = new Font(this.fName, 0, this.fSize);
        }
        final String parameter9 = this.getParameter("TextColor");
        if (parameter9 != null) {
            final StringTokenizer stringTokenizer4 = new StringTokenizer(parameter9, ",");
            this.tC = new Color(Integer.parseInt(stringTokenizer4.nextToken()), Integer.parseInt(stringTokenizer4.nextToken()), Integer.parseInt(stringTokenizer4.nextToken()));
        }
        final String parameter10 = this.getParameter("TextHotColor");
        if (parameter10 != null) {
            final StringTokenizer stringTokenizer5 = new StringTokenizer(parameter10, ",");
            this.tHotC = new Color(Integer.parseInt(stringTokenizer5.nextToken()), Integer.parseInt(stringTokenizer5.nextToken()), Integer.parseInt(stringTokenizer5.nextToken()));
        }
        this.nF = 0;
        this.image = new Image[101];
        this.MT = new MediaTracker(this);
        int n = 1;
        String parameter11;
        do {
            parameter11 = this.getParameter("Image" + n);
            if (parameter11 != null) {
                this.image[n] = this.getImage(this.getDocumentBase(), parameter11);
                this.MT.addImage(this.image[n], n);
            }
            ++n;
            ++this.nF;
        } while (parameter11 != null);
        --this.nF;
        this.F = 1;
        try {
            this.MT.waitForAll();
        }
        catch (InterruptedException ex) {}
        final String parameter12 = this.getParameter("BoxMode");
        if (parameter12 != null) {
            this.bMode = Integer.parseInt(parameter12);
        }
        if (this.bMode > 3) {
            this.bMode = 0;
        }
        final String parameter13 = this.getParameter("BoxStyle");
        if (parameter13 != null) {
            this.bStyle = Integer.parseInt(parameter13);
        }
        if (this.bStyle > 3) {
            this.bStyle = 0;
        }
        final String parameter14 = this.getParameter("BoxInset");
        if (parameter14 != null) {
            this.bIn = Integer.parseInt(parameter14);
        }
        final String parameter15 = this.getParameter("BoxThick");
        if (parameter15 != null) {
            this.bThick = Integer.parseInt(parameter15);
        }
        final String parameter16 = this.getParameter("BoxColor");
        if (parameter16 != null) {
            final StringTokenizer stringTokenizer6 = new StringTokenizer(parameter16, ",");
            this.bHotC = new Color(Integer.parseInt(stringTokenizer6.nextToken()), Integer.parseInt(stringTokenizer6.nextToken()), Integer.parseInt(stringTokenizer6.nextToken()));
        }
        final String parameter17 = this.getParameter("BoxHotColor");
        if (parameter17 != null) {
            final StringTokenizer stringTokenizer7 = new StringTokenizer(parameter17, ",");
            this.bC = new Color(Integer.parseInt(stringTokenizer7.nextToken()), Integer.parseInt(stringTokenizer7.nextToken()), Integer.parseInt(stringTokenizer7.nextToken()));
        }
        final String parameter18 = this.getParameter("AnimateMode");
        if (parameter18 != null) {
            this.LM = Integer.parseInt(parameter18);
        }
        final String parameter19 = this.getParameter("Sound");
        if (parameter19 != null) {
            this.A = this.getAudioClip(this.getDocumentBase(), parameter19);
        }
        final String parameter20 = this.getParameter("Link");
        if (parameter20 != null) {
            try {
                this.U = new URL(parameter20);
            }
            catch (MalformedURLException ex2) {}
        }
        this.T = this.getParameter("Target");
        final String parameter21 = this.getParameter("Dwell");
        if (parameter21 == null) {
            this.W = 1000;
        }
        else {
            this.W = Integer.parseInt(parameter21);
        }
        final String parameter22 = this.getParameter("StatusBarText");
        if (parameter22 != null) {
            this.H = parameter22;
        }
        if (!this.pf) {
            this.L = "Notice?";
        }
    }
    
    public void start() {
        (this.Y = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            this.cycle();
            this.repaint();
            try {
                Thread.sleep(this.W);
            }
            catch (Exception ex) {}
        }
    }
    
    public void cycle() {
        if ((this.mHot && this.LM == 1) || (!this.mHot && this.LM == 2) || this.LM == 3) {
            ++this.F;
            if (this.F > this.nF) {
                this.F = 1;
            }
        }
        else {
            this.F = 1;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.LM == 4 && this.mHot) {
            this.F = 2;
        }
        if (this.LM == 4 && !this.mHot) {
            this.F = 1;
        }
        if (this.image[this.F] != null && this.pf) {
            graphics.drawImage(this.image[this.F], 0, 0, null);
        }
        if (this.mHot && this.H != null) {
            this.getAppletContext().showStatus(this.H);
        }
    }
    
    public final synchronized void update(final Graphics graphics) {
        if (this.offI == null) {
            this.offI = this.createImage(this.size().width, this.size().height);
            this.offG = this.offI.getGraphics();
        }
        this.offG.setColor(this.getBackground());
        this.offG.fillRect(0, 0, this.size().width, this.size().height);
        this.paint(this.offG);
        if (this.bMode > 0) {
            if (this.mHot) {
                this.offG.setColor(this.bC);
            }
            if (!this.mHot) {
                this.offG.setColor(this.bHotC);
            }
            if ((this.bMode == 1 && this.mHot) || (this.bMode == 2 && !this.mHot) || this.bMode == 3) {
                for (int i = 0; i < this.bThick; ++i) {
                    if (this.bStyle == 0) {
                        this.offG.drawRect(this.bIn + i, this.bIn + i, this.size().width - 1 - this.bIn * 2 - i * 2, this.size().height - 1 - this.bIn * 2 - i * 2);
                    }
                    if (this.bStyle == 1) {
                        this.offG.drawRoundRect(this.bIn + i, this.bIn + i, this.size().width - 1 - this.bIn * 2 - i * 2, this.size().height - 1 - this.bIn * 2 - i * 2, 12 - i * 3, 12 - i * 3);
                    }
                    if (this.bStyle == 2) {
                        this.offG.drawOval(this.bIn + i, this.bIn + i, this.size().height - 1 - this.bIn * 2 - i * 2, this.size().height - 1 - this.bIn * 2 - i * 2);
                    }
                    if (this.bStyle == 3) {
                        this.offG.drawOval(this.bIn + i, this.bIn + i, this.size().width - 1 - this.bIn * 2 - i * 2, this.size().height - 1 - this.bIn * 2 - i * 2);
                    }
                }
            }
        }
        if (this.mHot) {
            this.offG.setColor(this.tHotC);
        }
        if (!this.mHot) {
            this.offG.setColor(this.tC);
        }
        this.offG.setFont(this.fFont);
        if (this.L != null) {
            this.offG.drawString(this.L, this.tX, this.tY);
        }
        graphics.drawImage(this.offI, 0, 0, null);
    }
    
    public void stop() {
        this.destroy();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.mHot = false;
        if (this.A != null && this.pf) {
            this.A.play();
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.mHot = false;
        if (this.U != null) {
            if (this.T != null) {
                this.getAppletContext().showDocument(this.U, this.T);
            }
            else {
                this.getAppletContext().showDocument(this.U);
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.tX += this.tSX;
        this.tY += this.tSY;
        this.mHot = true;
        this.cycle();
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.tX -= this.tSX;
        this.tY -= this.tSY;
        this.mHot = false;
        if (this.H != null) {
            this.getAppletContext().showStatus(null);
        }
        this.cycle();
        this.repaint();
        return true;
    }
    
    public CodeBrainYak() {
        this.backC = Color.black;
        this.pf = false;
        this.W = 500;
        this.mHot = false;
        this.tX = 15;
        this.tY = 15;
        this.tC = Color.red;
        this.tHotC = Color.green;
        this.bThick = 1;
        this.bC = Color.red;
        this.bHotC = Color.green;
        this.fSize = 11;
        this.fName = "Dialog";
    }
}
