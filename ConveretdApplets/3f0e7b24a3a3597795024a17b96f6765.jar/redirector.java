import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class redirector extends Applet implements Runnable
{
    Thread t;
    Image back;
    Image imgBuffer;
    Graphics grBuffer;
    int height;
    int width;
    String paramBack;
    String paramUrl;
    String paramDelay;
    String paramFont;
    String paramFontSize;
    String paramText;
    String paramBackCol;
    String paramTextCol;
    String paramStretch;
    String paramPage;
    FontMetrics fontInfo;
    Font font;
    int delay;
    int lineWidth;
    int lineHeight;
    Color foreCol;
    Color backCol;
    
    public redirector() {
        this.paramBack = "Error";
        this.paramUrl = "Error";
        this.paramDelay = "Error";
        this.paramFont = "Error";
        this.paramFontSize = "Error";
        this.paramText = "Error";
        this.paramBackCol = "Error";
        this.paramTextCol = "Error";
        this.paramStretch = "Error";
        this.paramPage = "Error";
        this.delay = 0;
    }
    
    public void init() {
        this.paramBack = "Error";
        this.paramUrl = "Error";
        this.paramDelay = "Error";
        this.paramFont = "Error";
        this.paramFontSize = "Error";
        this.paramText = "Error";
        this.paramBackCol = "Error";
        this.paramTextCol = "Error";
        this.paramStretch = "Error";
        this.paramPage = "Error";
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        try {
            this.paramUrl = this.getParameter("URL");
        }
        catch (Exception ex) {
            this.paramUrl = "Error";
        }
        if (this.paramUrl == null) {
            this.paramUrl = "Error";
        }
        try {
            this.paramPage = this.getParameter("PAGE");
        }
        catch (Exception ex2) {
            this.paramPage = "Error";
        }
        if (this.paramPage == null) {
            this.paramPage = "Error";
        }
        try {
            this.paramBackCol = this.getParameter("BACKCOLOR");
        }
        catch (Exception ex3) {
            this.paramBackCol = "Error";
        }
        if (this.paramBackCol == null) {
            this.paramBackCol = "Error";
        }
        try {
            this.paramStretch = this.getParameter("STRETCHIMAGE");
        }
        catch (Exception ex4) {
            this.paramStretch = "Error";
        }
        if (this.paramStretch == null) {
            this.paramStretch = "Error";
        }
        try {
            this.paramTextCol = this.getParameter("TEXTCOLOR");
        }
        catch (Exception ex5) {
            this.paramTextCol = "Error";
        }
        if (this.paramTextCol == null) {
            this.paramTextCol = "Error";
        }
        try {
            this.paramFont = this.getParameter("FONT");
        }
        catch (Exception ex6) {
            this.paramFont = "Error";
        }
        if (this.paramFont == null) {
            this.paramFont = "Error";
        }
        try {
            this.paramText = this.getParameter("TEXT");
        }
        catch (Exception ex7) {
            this.paramText = "Error";
        }
        if (this.paramText == null) {
            this.paramText = "Error";
        }
        try {
            this.paramFontSize = this.getParameter("FONTSIZE");
        }
        catch (Exception ex8) {
            this.paramFontSize = "Error";
        }
        if (this.paramFontSize == null) {
            this.paramFontSize = "Error";
        }
        try {
            this.paramBack = this.getParameter("IMAGE");
        }
        catch (Exception ex9) {
            this.paramBack = "Error";
        }
        if (this.paramBack == null) {
            this.paramBack = "Error";
        }
        try {
            this.paramDelay = this.getParameter("DELAY");
        }
        catch (Exception ex10) {
            this.paramDelay = "Error";
        }
        if (this.paramDelay == null) {
            this.paramDelay = "Error";
        }
        if (this.paramUrl.equals("Error")) {
            try {
                this.paramUrl = this.getParameter("url");
            }
            catch (Exception ex11) {
                this.paramUrl = "Error";
            }
            if (this.paramUrl == null) {
                this.paramUrl = "Error";
            }
        }
        if (this.paramUrl.equals("Error")) {
            try {
                this.paramPage = this.getParameter("page");
            }
            catch (Exception ex12) {
                this.paramPage = "Error";
            }
            if (this.paramPage == null) {
                this.paramPage = "Error";
            }
        }
        if (this.paramFont.equals("Error")) {
            try {
                this.paramFont = this.getParameter("font");
            }
            catch (Exception ex13) {
                this.paramFont = "Error";
            }
            if (this.paramFont == null) {
                this.paramFont = "Error";
            }
        }
        if (this.paramFontSize.equals("Error")) {
            try {
                this.paramFontSize = this.getParameter("fontsize");
            }
            catch (Exception ex14) {
                this.paramFontSize = "Error";
            }
            if (this.paramFontSize == null) {
                this.paramFontSize = "Error";
            }
        }
        if (this.paramBack.equals("Error")) {
            try {
                this.paramBack = this.getParameter("image");
            }
            catch (Exception ex15) {
                this.paramBack = "Error";
            }
            if (this.paramBack == null) {
                this.paramBack = "Error";
            }
        }
        if (this.paramBackCol.equals("Error")) {
            try {
                this.paramBackCol = this.getParameter("backcolor");
            }
            catch (Exception ex16) {
                this.paramBackCol = "Error";
            }
            if (this.paramBackCol == null) {
                this.paramBackCol = "Error";
            }
        }
        if (this.paramTextCol.equals("Error")) {
            try {
                this.paramTextCol = this.getParameter("textcolor");
            }
            catch (Exception ex17) {
                this.paramTextCol = "Error";
            }
            if (this.paramTextCol == null) {
                this.paramTextCol = "Error";
            }
        }
        if (this.paramStretch.equals("Error")) {
            try {
                this.paramStretch = this.getParameter("stretchimage");
            }
            catch (Exception ex18) {
                this.paramStretch = "Error";
            }
            if (this.paramStretch == null) {
                this.paramStretch = "Error";
            }
        }
        if (this.paramDelay.equals("Error")) {
            try {
                this.paramDelay = this.getParameter("delay");
            }
            catch (Exception ex19) {
                this.paramDelay = "Error";
                if (this.paramDelay == null) {
                    this.paramDelay = "Error";
                }
            }
        }
        if (this.paramText.equals("Error")) {
            try {
                this.paramText = this.getParameter("text");
            }
            catch (Exception ex20) {
                this.paramText = "Error";
                if (this.paramText == null) {
                    this.paramText = "Error";
                }
            }
        }
        this.delay = 5;
        try {
            this.delay = Integer.parseInt(this.paramDelay);
        }
        catch (Exception ex21) {
            this.delay = 5;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.paramFontSize);
        }
        catch (Exception ex22) {
            int1 = 12;
        }
        if (!this.paramBack.equals("Error") && !this.paramBack.equals("none")) {
            this.back = this.getImage(this.getCodeBase(), this.paramBack);
            if (this.paramStretch.equals("YES") || this.paramStretch.equals("yes")) {
                final Image back = this.back;
                final int width = this.width;
                final int height = this.height;
                final Image back2 = this.back;
                this.back = back.getScaledInstance(width, height, 16);
            }
        }
        this.imgBuffer = this.createImage(this.width, this.height);
        this.grBuffer = this.imgBuffer.getGraphics();
        if (!this.paramPage.equals("new") && !this.paramPage.equals("NEW") && !this.paramPage.equals("same") && !this.paramPage.equals("SAME")) {
            this.paramPage = "SAME";
        }
        if (!this.paramFont.equals("Error")) {
            try {
                this.font = new Font(this.paramFont, 0, int1);
                this.grBuffer.setFont(this.font);
            }
            catch (Exception ex23) {
                this.paramFont = "Courier";
                this.font = new Font(this.paramFont, 0, 12);
                this.grBuffer.setFont(this.font);
            }
        }
        else {
            this.paramFont = "Courier";
            this.font = new Font(this.paramFont, 0, 12);
            this.grBuffer.setFont(this.font);
        }
        this.fontInfo = this.getFontMetrics(this.font);
        this.lineWidth = this.fontInfo.stringWidth(this.paramText);
        this.lineHeight = int1;
        if (!this.paramBackCol.equals("Error") && !this.paramBackCol.equals("none")) {
            this.backCol = convStrCol(this.paramBackCol);
        }
        else {
            this.backCol = Color.white;
        }
        if (!this.paramTextCol.equals("Error") && !this.paramTextCol.equals("none")) {
            this.foreCol = convStrCol(this.paramTextCol);
        }
        else {
            this.foreCol = Color.black;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.grBuffer.setColor(this.backCol);
        this.grBuffer.fillRect(0, 0, this.width, this.height);
        this.grBuffer.setColor(this.foreCol);
        if (!this.paramBack.equals("Error") && !this.paramBack.equals("none")) {
            if (this.back.getWidth(this) != this.width || this.back.getHeight(this) != this.height) {
                this.grBuffer.drawImage(this.back, 0 + (this.width - this.back.getWidth(this)) / 2, 0 + (this.height - this.back.getHeight(this)) / 2, this);
            }
            else {
                this.grBuffer.drawImage(this.back, 0, 0, this);
            }
        }
        if (!this.paramText.equals("Error") && !this.paramText.equals("none")) {
            final int n = (this.height - this.lineHeight) / 2 + this.lineHeight;
            int n2;
            if (this.lineWidth < this.width) {
                n2 = (this.width - this.lineWidth) / 2;
            }
            else {
                n2 = 0;
            }
            this.grBuffer.drawString(this.paramText, n2, n);
        }
        graphics.drawImage(this.imgBuffer, 0, 0, this);
    }
    
    public void run() {
        final long time = this.getTime();
        int i = 0;
        while (i == 0) {
            this.repaint();
            if ((int)((this.getTime() - time) / 1000L) >= this.delay && !this.paramUrl.equals("Error")) {
                try {
                    final URL url = new URL(this.paramUrl);
                    if (this.paramPage.equals("same") || this.paramPage.equals("SAME")) {
                        this.getAppletContext().showDocument(url, "_self");
                    }
                    else {
                        this.getAppletContext().showDocument(url, "_new");
                    }
                }
                catch (MalformedURLException ex) {}
                i = 1;
            }
            try {
                final Thread t = this.t;
                Thread.sleep(100L);
                continue;
            }
            catch (InterruptedException ex2) {
                return;
            }
            break;
        }
    }
    
    public void start() {
        (this.t = new Thread(this)).start();
    }
    
    public void stop() {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static Color convStrCol(final String s) {
        if (s.equals("green")) {
            return Color.green;
        }
        if (s.equals("black")) {
            return Color.black;
        }
        if (s.equals("white")) {
            return Color.white;
        }
        if (s.equals("blue")) {
            return Color.blue;
        }
        if (s.equals("gray")) {
            return Color.gray;
        }
        if (s.equals("red")) {
            return Color.red;
        }
        if (s.equals("yellow")) {
            return Color.yellow;
        }
        if (s.equals("pink")) {
            return Color.pink;
        }
        if (s.equals("orange")) {
            return Color.orange;
        }
        if (s.equals("magenta")) {
            return Color.magenta;
        }
        if (s.equals("cyan")) {
            return Color.cyan;
        }
        return Color.white;
    }
    
    public long getTime() {
        return System.currentTimeMillis();
    }
}
