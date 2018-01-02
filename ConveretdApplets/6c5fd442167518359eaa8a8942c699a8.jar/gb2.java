import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.Component;
import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class gb2 extends Applet
{
    static final String COPYRIGHT = "(C) 1996 by W.Giel";
    static final String IMAGE_LOAD = "Loading Images...wait.";
    static final String VERSION = "Guest Book II v2.0a (4 July 1996)";
    static final String TITLE = "Guest Book II";
    int width;
    int height;
    tFrame window;
    smtpSend smtp;
    String szButton;
    String szReceiver;
    String szTitle;
    String szAppletImage;
    String szLogoImage;
    int imageCount;
    imageLoader il;
    Image[] images;
    boolean threeD;
    Color bgColor;
    Color fgColor;
    Button button;
    
    public void init() {
        this.szReceiver = this.getParameter("receiver");
        final String parameter = this.getParameter("title");
        this.szTitle = parameter;
        if (parameter == null) {
            this.szTitle = "Guest Book II";
        }
        if ((this.szButton = this.getParameter("button")) == null) {
            this.szButton = "Guest Book II";
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("bgcolor")) == null) {
            this.bgColor = Color.lightGray;
        }
        else {
            this.bgColor = this.parseColorString(parameter2);
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("fgcolor")) == null) {
            this.fgColor = Color.black;
        }
        else {
            this.fgColor = this.parseColorString(parameter3);
        }
        final String parameter4 = this.getParameter("3d_logo");
        if (parameter4 != null && Integer.valueOf(parameter4) > 0) {
            this.threeD = true;
        }
        else {
            this.threeD = false;
        }
        this.add(this.button = new Button(this.szButton));
        this.width = this.size().width;
        this.height = this.size().height;
        this.button.move((this.width - this.button.size().width) / 2, (this.width - this.button.size().width) / 2);
        final String parameter5 = this.getParameter("applet_image");
        this.szAppletImage = parameter5;
        if (parameter5 != null) {
            ++this.imageCount;
        }
        if ((this.szLogoImage = this.getParameter("logo_image")) != null) {
            ++this.imageCount;
        }
        if (this.imageCount > 0) {
            int n = 0;
            final String[] array = new String[this.imageCount];
            if (this.szAppletImage != null) {
                array[n++] = this.szAppletImage;
            }
            if (this.szLogoImage != null) {
                array[n] = this.szLogoImage;
            }
            (this.il = new imageLoader(this, array, this.imageCount, null)).start();
            this.images = new Image[this.imageCount];
        }
    }
    
    private Color parseColorString(final String s) {
        if (s.length() == 6) {
            return new Color(Integer.valueOf(s.substring(0, 2), 16), Integer.valueOf(s.substring(2, 4), 16), Integer.valueOf(s.substring(4, 6), 16));
        }
        return Color.lightGray;
    }
    
    public void paint(final Graphics graphics) {
        final Color color = graphics.getColor();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        if (this.il != null) {
            graphics.drawString("Loading Images...wait.", (this.size().width - fontMetrics.stringWidth("Loading Images...wait.")) / 2, this.size().height - fontMetrics.getMaxDescent() - 3);
            while ((this.images = this.il.retrieveImages()) == null) {}
            this.il.stop();
            this.il = null;
        }
        graphics.setColor(this.bgColor);
        graphics.fill3DRect(0, 0, this.size().width, this.size().height, true);
        graphics.setColor(this.fgColor);
        if (this.szAppletImage != null && this.images != null) {
            graphics.drawImage(this.images[0], (this.size().width - this.images[0].getWidth(this)) / 2, this.button.size().height + (this.size().height - this.images[0].getHeight(this) - this.button.size().height) / 3, this);
        }
        graphics.drawString("(C) 1996 by W.Giel", (this.size().width - fontMetrics.stringWidth("(C) 1996 by W.Giel")) / 2, this.size().height - fontMetrics.getMaxDescent() - 3);
        graphics.setColor(color);
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals(this.szButton)) {
            if (this.smtp == null) {
                this.smtp = new smtpSend(this.getCodeBase().getHost(), this.szReceiver);
            }
            if (this.window == null) {
                this.window = new gbFrame(this.szTitle, this.smtp, (this.szLogoImage != null && this.images != null) ? this.images[this.imageCount - 1] : null, this.threeD);
            }
            this.window.show();
            return true;
        }
        return false;
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "width", "int", "width of the applet, in pixels" }, { "height", "int", "height of the applet, in pixels" }, { "receiver", "string", "SMTP 'RCPT TO:' parameter <null>" }, { "applet_image", "string", "GIF file to display on applet panel <null>" }, { "logo_image", "string", "GIF file to display on message dialog <null>" }, { "3d_logo", "int", "Non-zero produces an inletted logo image <0>" }, { "bgcolor", "String", "RGB hex triplet for applet panel background <lightGray>" }, { "fgcolor", "String", "RGB hex triplet for applet panel foreground <black>" }, { "title", "string", "title for popup window <Guest Book II>" }, { "button", "string", "Label to appear in applet's button <Guest Book II>" } };
    }
    
    public String getAppletInfo() {
        return "Guest Book II v2.0a (4 July 1996) - simulates a guest log\nby E-mailing guest data to page owner, by Bill Giel\nhttp://www.nai.net/~rvdi/home.htm  or  rvdi@usa.nai.net\nCopyright 1996 by William Giel.";
    }
}
