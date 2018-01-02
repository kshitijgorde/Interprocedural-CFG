import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class MessageMenu extends KLMenu
{
    static final int LEFT = -1;
    static final int a343 = 0;
    static final int RIGHT = 1;
    int a344;
    Rectangle a345;
    int a346;
    Font a347;
    Font a348;
    String a349;
    Color a350;
    Color[] a351;
    Color[] a352;
    
    public void init() {
        super.init("Message Menu");
        super.a168 = false;
        super.a110 = "bar";
        if (super.a110.equals("bar")) {
            super.height /= 2;
            this.a345 = new Rectangle(0, super.height, super.width, this.size().height - super.height);
        }
        else {
            super.height *= 0;
        }
        this.a347 = super.a20.getFont();
        final Color a50 = this.a50("messageFgcolor", this.a50("fgcolor", Color.black));
        final Color a51 = this.a50("messageMouse", this.a50("mouse", Color.blue));
        this.a350 = this.a50("messageHighlight", this.a50("highlight", super.a211));
        this.a351 = this.a225(a50, a51, super.a180);
        this.a352 = this.a225(super.a211, this.a350, super.a180);
        final String s = (this.getParameter("messageFont") != null) ? this.getParameter("messageFont") : this.a347.getName();
        final String lowerCase = this.a66("messageFontstyle", this.a66("fontstyle", "")).toLowerCase();
        this.a348 = new Font(s, ((lowerCase.indexOf(105) != -1) ? 2 : 0) | ((lowerCase.indexOf(98) != -1) ? 1 : 0), this.a47("messageFontsize", this.a347.getSize()));
        final String a52 = this.a66("messageJustify", super.a217);
        if (a52.equalsIgnoreCase("left")) {
            this.a344 = -1;
        }
        else if (a52.equalsIgnoreCase("right")) {
            this.a344 = 1;
        }
        else {
            this.a344 = 0;
        }
        this.a349 = this.a66("messageHighlightFade", "left");
        this.a239("menuMain");
        super.a168 = true;
    }
    
    protected synchronized void a148(final int n) {
        this.a346 = ((super.a5 == -1) ? this.a346 : super.a5);
        if (this.a346 != n) {
            super.a148(n);
            return;
        }
        super.a148(n);
        super.a20.setFont(this.a348);
        final Rectangle rectangle = new Rectangle(this.a345.x, this.a345.y, this.a345.width, this.a345.height);
        if (super.a207 == 2) {
            this.a93(super.a215, rectangle, super.a208, super.a204, n);
        }
        else if (super.a207 == 1) {
            this.a93(super.a216, rectangle, super.a208, super.a204, n);
        }
        final Graphics create = super.a20.create(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        create.clipRect(this.a345.x - rectangle.x, this.a345.y - rectangle.y, this.a345.width, this.a345.height);
        final int n2 = rectangle.y + (rectangle.height - create.getFontMetrics().getHeight()) / 2 + create.getFontMetrics().getAscent();
        int x;
        if (this.a344 == -1) {
            x = rectangle.x;
        }
        else if (this.a344 == 1) {
            x = rectangle.x + rectangle.width - create.getFontMetrics().stringWidth(this.a236(n, 3));
        }
        else {
            x = rectangle.x + (rectangle.width - create.getFontMetrics().stringWidth(this.a236(n, 3))) / 2;
        }
        if (super.a35 == null) {
            create.setColor(super.a211);
            create.fillRect(0, 0, this.a345.width, this.a345.height);
        }
        else {
            create.drawImage(super.a35, -rectangle.x, -rectangle.y, this);
        }
        if (this.a236(n, 3).length() > 0 && this.a236(n, 1).length() > 0) {
            final Rectangle rectangle2 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            if (!this.a349.equals("none")) {
                this.a93(this.a349, rectangle2, super.a91[n], super.a180, n);
                this.a359(rectangle2.intersection(rectangle).intersection(this.a345), n);
            }
        }
        create.setColor((this.a236(n, 1).length() > 0) ? this.a351[super.a91[n]] : this.a351[0]);
        if (super.a5 == n || super.a91[n] == super.a180 - 1) {
            create.drawString(this.a236(n, 3), x - rectangle.x + ((super.a6 == n) ? 2 : 0), n2 - rectangle.y + ((super.a6 == n) ? 2 : 0));
        }
        super.a20.setFont(this.a347);
    }
    
    protected void a359(final Rectangle rectangle, final int n) {
        if (super.a91[n] != 0) {
            super.a20.setColor(this.a352[super.a91[n]]);
            super.a20.fillRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, super.a15, super.a15);
        }
    }
    
    public MessageMenu() {
        this.a346 = -1;
    }
}
