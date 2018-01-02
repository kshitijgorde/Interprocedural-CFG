import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Com2meFWl2 extends Canvas
{
    private int status;
    private int lastStatus;
    private int percent;
    private Image progressImage;
    public static final int LOADING = 1;
    public static final int PIECING = 2;
    public static final int CROPPING = 3;
    public static final int ERROR = 4;
    private String errorMessage;
    private Font errorFont;
    private Font titleFont;
    private Font subTitleFont;
    private Font progressFont;
    private FontMetrics titleMetrix;
    private FontMetrics subTitleMetrix;
    private FontMetrics progressMetrix;
    private FontMetrics errorMetrix;
    private Font textFont;
    private FontMetrics textMetrix;
    private Com2meFWCT textCT;
    
    public Com2meFWl2() {
        this.errorFont = new Font("Helvetica", 0, 20);
        this.titleFont = new Font("Helvetica", 1, 24);
        this.subTitleFont = new Font("Helvetica", 0, 20);
        this.progressFont = new Font("Helvetica", 0, 15);
        this.titleMetrix = this.getFontMetrics(this.titleFont);
        this.subTitleMetrix = this.getFontMetrics(this.subTitleFont);
        this.progressMetrix = this.getFontMetrics(this.progressFont);
        this.errorMetrix = this.getFontMetrics(this.errorFont);
        this.textFont = new Font("Arial", 0, 13);
        this.textMetrix = this.getFontMetrics(this.textFont);
        this.textCT = new Com2meFWCT();
        this.lastStatus = 0;
        this.status = 1;
        this.percent = 0;
    }
    
    public void drawOurText(final Graphics graphics) {
        final Dimension size = this.size();
        graphics.setColor(this.getForeground());
        graphics.setFont(this.textFont);
        final String text1 = this.textCT.getText1();
        final String text2 = this.textCT.getText2();
        final String text3 = this.textCT.getText3();
        final String text4 = this.textCT.getText4();
        graphics.drawString(text1, 3, this.textMetrix.getAscent() + 2);
        graphics.drawString(text2, size.width - this.textMetrix.stringWidth(text2) - 3, this.textMetrix.getAscent() + 2);
        graphics.drawString(text3, 3, size.height - this.textMetrix.getDescent() - 2);
        graphics.drawString(text4, size.width - this.textMetrix.stringWidth(text4) - 3, size.height - this.textMetrix.getDescent() - 2);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(this.getForeground());
        graphics.setFont(this.titleFont);
        if (this.status == 4) {
            graphics.setFont(this.errorFont);
            graphics.drawString(this.errorMessage, (size.width - this.errorMetrix.stringWidth(this.errorMessage)) / 2, 150);
            return;
        }
        this.drawOurText(graphics);
        graphics.setFont(this.subTitleFont);
        final String s = "(c) 99+ by ica-d.de";
        graphics.drawString(s, (size.width - this.subTitleMetrix.stringWidth(s)) / 2, 100);
        this.paintProgressBar(graphics, (size.width - 200) / 2, 150, 200, 25);
    }
    
    public void paintProgressBar(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(Color.gray);
        graphics.drawRect(n, n2, n3 - 1, n4 - 1);
        graphics.setColor(Color.red);
        int n5 = this.percent / 3;
        if (this.status == 1) {
            n5 = this.percent / 3;
        }
        else if (this.status == 2) {
            n5 = 33 + this.percent / 3;
        }
        else if (this.status == 3) {
            n5 = 67 + this.percent / 3;
        }
        final int n6 = (n3 - 2) * n5 / 100;
        graphics.fillRect(n + 1, n2 + 1, n6, n4 - 2);
        graphics.setColor(Color.lightGray);
        graphics.fillRect(n6 + n + 1, n2 + 1, n3 - n6 - 2, n4 - 2);
        graphics.setColor(Color.black);
        graphics.setFont(this.progressFont);
        final String string = String.valueOf(n5) + "%";
        graphics.drawString(string, n + (n3 - this.progressMetrix.stringWidth(string)) / 2, n2 + n4 / 2 + 7);
    }
    
    public void showErrorMessage(final String errorMessage) {
        this.status = 4;
        this.errorMessage = errorMessage;
        this.repaint();
    }
    
    public void showProgress(final int n, final int n2) {
        this.percent = n * 100 / n2;
        this.repaint();
    }
    
    public void showStatus(final int status) {
        this.status = status;
        this.showProgress(0, 1);
    }
    
    public void update(final Graphics graphics) {
        if (this.lastStatus != this.status || this.status == 4) {
            this.paint(graphics);
            this.lastStatus = this.status;
            return;
        }
        if (this.progressImage == null) {
            this.progressImage = this.createImage(200, 25);
        }
        this.paintProgressBar(this.progressImage.getGraphics(), 0, 0, 200, 25);
        graphics.drawImage(this.progressImage, (this.size().width - 200) / 2, 150, this);
    }
}
