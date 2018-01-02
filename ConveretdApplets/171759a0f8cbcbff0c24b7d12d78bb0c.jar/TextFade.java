import java.awt.Event;
import java.util.Date;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextFade extends Applet implements Runnable
{
    public static final int SLEEPTIME = 80;
    private String[] paramStrings;
    private String[] paramUrls;
    private String[] paramTargets;
    private Color[] barColorsArray;
    private TextLink[] textlinkArray;
    private int currentLink;
    private long tSeconds;
    private ParameterCollector pc;
    private Image imageBuffer;
    private Graphics bGraphics;
    private Thread m_ScrollerApplet;
    private Color textColor;
    private Color rollColor;
    private Color backColor;
    private Font f;
    private int linkDelay;
    private int fadeSpeed;
    private int fSize;
    private int xStart;
    private int yStart;
    private int blockWidth;
    private int leading;
    private boolean paramsCollected;
    private String errorMessage;
    
    public void init() {
        this.imageBuffer = this.createImage(this.size().width, this.size().height);
        this.bGraphics = this.imageBuffer.getGraphics();
        this.currentLink = 0;
        this.errorMessage = "";
        try {
            this.linkDelay = Integer.parseInt(this.getParameter("linkdelay"));
            this.fadeSpeed = Integer.parseInt(this.getParameter("fadespeed"));
            this.fSize = Integer.parseInt(this.getParameter("fontsize"));
            this.xStart = Integer.parseInt(this.getParameter("xstart"));
            this.yStart = Integer.parseInt(this.getParameter("ystart"));
            this.blockWidth = Integer.parseInt(this.getParameter("textblockwidth"));
            this.leading = Integer.parseInt(this.getParameter("leading"));
            this.paramsCollected = true;
        }
        catch (NumberFormatException ex) {
            this.paramsCollected = false;
        }
        this.pc = new ParameterCollector("string_", this);
        this.paramStrings = this.pc.get("string_");
        this.paramUrls = this.pc.get("url_");
        this.paramTargets = this.pc.get("target_");
        this.textColor = this.pc.hexConvert(this.getParameter("textcolor"));
        this.rollColor = this.pc.hexConvert(this.getParameter("rollcolor"));
        this.backColor = this.pc.hexConvert(this.getParameter("backcolor"));
        this.f = new Font(this.getParameter("fontface"), this.pc.getFontStyle(this.getParameter("fontstyle")), this.fSize);
        if (this.paramsCollected) {
            this.buildTextLinks();
            return;
        }
        this.errorMessage = "";
    }
    
    public void paint(final Graphics graphics) {
        this.bGraphics.setColor(this.backColor);
        this.bGraphics.fillRect(0, 0, this.size().width, this.size().height);
        this.drawParagraph(this.textlinkArray[this.currentLink]);
        graphics.drawImage(this.imageBuffer, 0, 0, this);
    }
    
    private void drawParagraph(final TextLink textLink) {
        this.bGraphics.setColor(textLink.getTextColor());
        this.bGraphics.setFont(this.f);
        for (int i = 0; i < textLink.getNumStrings(); ++i) {
            this.bGraphics.drawString(textLink.getString(i), textLink.getPos().x, textLink.getLeading(i));
        }
    }
    
    private void buildTextLinks() {
        final FontMetrics fontMetrics = this.bGraphics.getFontMetrics(this.f);
        this.textlinkArray = new TextLink[this.paramStrings.length];
        for (int i = 0; i < this.paramStrings.length; ++i) {
            (this.textlinkArray[i] = new TextLink(this.paramStrings[i], this.paramUrls[i], this.paramTargets[i], this.xStart, this.yStart, this.blockWidth, this.leading, this.textColor, this.rollColor, fontMetrics)).setTextColor(this.backColor);
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            if (!this.textlinkArray[this.currentLink].getHighlight()) {
                this.cycleLinks(this.linkDelay);
            }
            try {
                Thread.sleep(80L);
            }
            catch (Exception ex) {}
        }
    }
    
    private void cycleLinks(final int n) {
        final long time = new Date().getTime();
        if (time - this.tSeconds > n * 1000) {
            if (this.textlinkArray[this.currentLink].fadeTo(this.backColor, this.fadeSpeed)) {
                this.currentLink = ((this.currentLink >= this.textlinkArray.length - 1) ? 0 : (this.currentLink + 1));
                this.tSeconds = time;
            }
        }
        else {
            this.textlinkArray[this.currentLink].fadeTo(this.textColor, this.fadeSpeed);
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showStatus(this.errorMessage);
        if (this.textlinkArray[this.currentLink].mouseWithin(n, n2)) {
            this.textlinkArray[this.currentLink].setHighlight(true);
        }
        else {
            this.textlinkArray[this.currentLink].setHighlight(false);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.textlinkArray.length; ++i) {
            this.textlinkArray[i].setHighlight(false);
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.textlinkArray[this.currentLink].mouseWithin(n, n2)) {
            try {
                this.getAppletContext().showDocument(this.textlinkArray[this.currentLink].getUrl(), this.textlinkArray[this.currentLink].getTarget());
            }
            catch (Exception ex) {
                this.errorMessage = "Warning: one or more url parameters are set incorrectly!";
            }
        }
        return true;
    }
    
    public void start() {
        if (this.m_ScrollerApplet == null) {
            this.m_ScrollerApplet = new Thread(this);
            this.textlinkArray[this.currentLink].setHighlight(false);
            this.tSeconds = new Date().getTime();
            this.m_ScrollerApplet.start();
        }
    }
    
    public void stop() {
        if (this.m_ScrollerApplet != null) {
            this.m_ScrollerApplet.stop();
            this.m_ScrollerApplet = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
