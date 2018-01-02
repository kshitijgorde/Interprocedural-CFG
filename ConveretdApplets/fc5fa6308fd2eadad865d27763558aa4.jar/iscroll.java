import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class iscroll extends Applet implements Runnable
{
    private Thread JSS;
    boolean pf;
    boolean FirstPass;
    private String L;
    private String Tg;
    private String Text;
    private int S;
    private Color bgC;
    private Color textC;
    private Color textHotC;
    private Color tempC;
    private boolean MouseMode;
    private String statText;
    private int xPos;
    private int yPos;
    private int textWidth;
    private int aWide;
    private int aHigh;
    private String fontName;
    private int FontStyle;
    private Font F;
    private Image iBuff;
    private Graphics gBuff;
    private Image iTheText;
    private Graphics gTheText;
    private boolean LoadPause;
    private int RP;
    private int VB;
    
    public iscroll() {
        this.JSS = null;
        this.pf = false;
        this.FirstPass = true;
        this.Tg = "_top";
        this.Text = "... ";
        this.S = 25;
        this.bgC = Color.black;
        this.textC = Color.red;
        this.textHotC = Color.green;
        this.tempC = Color.red;
        this.MouseMode = true;
        this.xPos = 1;
        this.yPos = 0;
        this.textWidth = 0;
        this.aWide = 0;
        this.aHigh = 0;
        this.fontName = null;
        this.FontStyle = 0;
        this.iBuff = null;
        this.iTheText = null;
        this.LoadPause = true;
        this.RP = 1000;
        this.VB = 0;
    }
    
    public void init() {
        this.aWide = this.size().width;
        this.aHigh = this.size().height;
        final String parameter = this.getParameter("Notice");
        if (parameter == null) {
            this.pf = false;
        }
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("Applet by www.CodeBrain.com")) {
                this.pf = true;
            }
            else {
                this.pf = false;
            }
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("Link")) != null) {
            this.L = parameter2;
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("Target")) != null) {
            this.Tg = parameter3;
        }
        String s;
        if ((s = this.getParameter("Text")) != null) {
            s = s.trim();
        }
        this.Text = String.valueOf(String.valueOf(" ").concat(String.valueOf(s))).concat(String.valueOf(" "));
        if (!this.pf) {
            this.Text = " Notice wrong or missing... ";
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("Speed")) != null && (this.S = Integer.parseInt(parameter4)) < 10) {
            this.S = 10;
        }
        final String parameter5;
        if ((parameter5 = this.getParameter("Pause")) != null && (this.RP = Integer.parseInt(parameter5)) < 100) {
            this.RP = 100;
        }
        final String parameter6;
        if ((parameter6 = this.getParameter("BackgroundColor")) != null) {
            this.bgC = this.parseC(parameter6, Color.black);
        }
        final String parameter7;
        if ((parameter7 = this.getParameter("VerticalBias")) != null) {
            this.VB = Integer.parseInt(parameter7);
        }
        final String parameter8;
        if ((parameter8 = this.getParameter("TextColor")) != null) {
            this.textC = this.parseC(parameter8, Color.red);
        }
        this.tempC = this.textC;
        this.textHotC = this.textC;
        final String parameter9;
        if ((parameter9 = this.getParameter("TextLinkColor")) != null) {
            this.textHotC = this.parseC(parameter9, Color.green);
        }
        final String parameter10;
        if ((parameter10 = this.getParameter("MouseMode")) != null) {
            this.MouseMode = (Integer.parseInt(parameter10) != 0);
        }
        final String parameter11;
        if ((parameter11 = this.getParameter("StatusBarText")) != null) {
            this.statText = parameter11;
        }
        final String parameter12 = this.getParameter("FontStyle");
        if (parameter12 != null) {
            this.FontStyle = Integer.parseInt(parameter12);
        }
        if (this.FontStyle > 3 || this.FontStyle < 0) {
            this.FontStyle = 0;
        }
        final String parameter13;
        if ((parameter13 = this.getParameter("FontName")) != null) {
            this.fontName = parameter13;
        }
        else {
            this.fontName = this.getFont().getName();
        }
        this.prepMessage();
        this.makeText();
        if (this.iBuff == null) {
            this.iBuff = this.createImage(this.aWide, this.aHigh);
        }
        this.gBuff = this.iBuff.getGraphics();
    }
    
    public void prepMessage() {
        this.F = this.getFont();
        int size = this.getFont().getSize();
        do {
            this.F = new Font(this.fontName, this.FontStyle, ++size);
        } while (this.doVsize());
        while (!this.doVsize() && size > 2) {
            this.F = new Font(this.fontName, this.FontStyle, --size);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.F);
        this.textWidth = fontMetrics.stringWidth(this.Text);
        fontMetrics.getAscent();
        this.yPos = this.aHigh - fontMetrics.getMaxDescent() - fontMetrics.getMaxDescent() / 3;
        this.yPos += this.VB;
    }
    
    public boolean doVsize() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.F);
        return this.aHigh > fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
    }
    
    public void makeText() {
        this.iTheText = this.createImage(this.textWidth, this.aHigh);
        (this.gTheText = this.iTheText.getGraphics()).setColor(this.bgC);
        this.gTheText.fillRect(0, 0, this.textWidth, this.aHigh);
        this.gTheText.setColor(this.tempC);
        this.gTheText.setFont(this.F);
        if (!this.FirstPass) {
            this.gTheText.drawString(this.Text, 0, this.yPos);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (++this.xPos > this.textWidth) {
            this.xPos = 1;
        }
        int i = this.xPos * -1;
        do {
            if (!this.FirstPass) {
                this.gBuff.drawImage(this.iTheText, i, 0, this);
            }
            if (this.FirstPass) {
                this.gBuff.drawImage(this.iTheText, 0, 0, this);
                i = 0;
            }
            i += this.textWidth;
        } while (i < this.aWide);
        graphics.drawImage(this.iBuff, 0, 0, this);
    }
    
    public void start() {
        if (this.JSS == null) {
            (this.JSS = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.JSS != null) {
            this.JSS.stop();
            this.JSS = null;
        }
    }
    
    public void run() {
        if (this.FirstPass) {
            this.FirstPass = false;
            this.makeText();
        }
        if (this.FirstPass) {
            return;
        }
        while (true) {
            try {
                this.repaint();
                if (this.LoadPause) {
                    try {
                        Thread.sleep(this.RP);
                    }
                    catch (InterruptedException ex) {}
                    this.LoadPause = false;
                }
                Thread.sleep(this.S);
            }
            catch (InterruptedException ex2) {
                this.stop();
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.MouseMode) {
            try {
                this.getAppletContext().showDocument(new URL(this.L), this.Tg);
            }
            catch (MalformedURLException ex) {}
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.tempC = this.textHotC;
        this.makeText();
        if (this.statText != null) {
            this.getAppletContext().showStatus(this.statText);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.tempC = this.textC;
        this.makeText();
        if (this.statText != null) {
            this.getAppletContext().showStatus(" ");
        }
        return true;
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
}
