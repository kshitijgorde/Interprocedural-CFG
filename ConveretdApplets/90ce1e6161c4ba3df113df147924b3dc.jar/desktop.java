import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Image;
import java.awt.Frame;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class desktop extends Applet
{
    Frame f;
    Image wallpaper;
    int iconWH;
    int iconCount;
    Vector iconText;
    Vector icons;
    Vector iconX;
    Vector iconY;
    Vector iconLink;
    String startbtn;
    int startbtnEX;
    Image startlogo;
    Image buffer;
    Vector items;
    Vector hrefs;
    Dimension d;
    boolean pop;
    int maxLength;
    int fwidth;
    int itemCount;
    int mouseX;
    int mouseY;
    int menuY;
    int menuEY;
    int fheight;
    
    public void addIcon(final String s, final Image image, final String s2, final String s3, final String s4) {
        this.iconText.addElement(s);
        this.icons.addElement(image);
        this.iconLink.addElement(s2);
        this.iconX.addElement(s3);
        this.iconY.addElement(s4);
        ++this.iconCount;
    }
    
    public void addItem(final String s, final String s2) {
        this.items.addElement(s);
        this.hrefs.addElement(s2);
        if (s.length() > this.maxLength) {
            this.maxLength = s.length();
        }
        ++this.itemCount;
    }
    
    public boolean mouseUp(final Event event, final int mouseX, final int mouseY) {
        int n = 0;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        if (this.pop && this.mouseX >= 0 && this.menuY < this.mouseY && this.fwidth >= this.mouseX && this.menuEY > this.mouseY) {
            for (int i = this.menuY; i < this.menuEY; i += this.fheight, ++n) {
                if (i + 8 < this.mouseY && i + this.fheight + 8 > this.mouseY) {
                    this.repaint(0, i + 8, this.fwidth, i + this.fheight + 8);
                    this.showStatus("Select " + n + " is " + this.items.elementAt(n) + " Link To " + this.hrefs.elementAt(n));
                    break;
                }
            }
            this.do_menu(n);
        }
        else if (this.mouseX >= 0 && this.d.height - this.fheight < this.mouseY && this.startbtnEX >= this.mouseX && this.d.height > this.mouseY) {
            this.pop = !this.pop;
            this.repaint();
        }
        else {
            this.repaint();
            this.pop = false;
        }
        if (this.iconCount > 0) {
            for (int n2 = this.iconCount - 1; n2 >= 0 && this.icons.elementAt(n2) != null; --n2) {
                final int n3 = Integer.parseInt(this.iconX.elementAt(n2)) - this.iconWH / 2;
                final int iconWH = this.iconWH;
                final int n4 = Integer.parseInt(this.iconY.elementAt(n2)) + this.iconWH;
                if (n3 <= this.mouseX && n4 - this.iconWH <= this.mouseY && n3 + iconWH >= this.mouseX && n4 + this.fheight - 8 >= this.mouseY) {
                    this.do_icon(n2);
                    break;
                }
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.repaint();
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x8) != 0x0) {
            this.repaint(n2, n3, n4, n5);
        }
        else if ((n & 0x80) != 0x0) {
            this.repaint();
        }
        return (n & 0xA0) == 0x0;
    }
    
    public void paint(Graphics graphics) {
        final Graphics graphics2 = graphics;
        graphics = this.buffer.getGraphics();
        this.d = this.size();
        graphics.setColor(Color.cyan);
        graphics.fillRect(0, 0, this.d.width, this.d.height);
        this.fheight = graphics.getFontMetrics().getHeight() + 8;
        if (this.wallpaper != null) {
            graphics.drawImage(this.wallpaper, 0, 0, this.d.width, this.d.height - 8, this);
        }
        graphics.setColor(Color.lightGray);
        graphics.fillRect(0, this.d.height - this.fheight, this.d.width, this.fheight + 8);
        this.startbtnEX = graphics.getFontMetrics().stringWidth(this.startbtn) + 20;
        graphics.fill3DRect(0, this.d.height - this.fheight, this.startbtnEX, this.fheight + 8, !this.pop);
        if (this.startlogo != null) {
            graphics.drawImage(this.startlogo, 0, this.d.height - this.fheight + 2, 16, this.fheight + 8, this);
        }
        if (!this.pop) {
            graphics.setColor(Color.black);
        }
        else {
            graphics.setColor(Color.white);
        }
        graphics.drawString(this.startbtn, 17, this.d.height - 3);
        if (this.iconCount > 0) {
            for (int i = this.iconCount - 1; i >= 0; --i) {
                if (this.icons.elementAt(i) != null) {
                    graphics.setColor(Color.lightGray);
                    final int stringWidth = graphics.getFontMetrics().stringWidth(this.iconText.elementAt(i));
                    final int n = Integer.parseInt(this.iconX.elementAt(i)) - stringWidth / 2;
                    final int n2 = Integer.parseInt(this.iconY.elementAt(i)) + this.iconWH;
                    if (n <= this.mouseX && n2 - this.iconWH <= this.mouseY && n + stringWidth >= this.mouseX && n2 + this.fheight - 8 >= this.mouseY) {
                        graphics.draw3DRect(n - 4, n2 - this.iconWH - 4, stringWidth + 8, n2 + this.fheight, true);
                    }
                    graphics.setColor(Color.black);
                    graphics.fillRect(n, n2 + 2, graphics.getFontMetrics().stringWidth(this.iconText.elementAt(i)), this.fheight - 8);
                    graphics.setColor(Color.white);
                    graphics.drawString(this.iconText.elementAt(i), n, n2 + this.fheight - 8);
                    graphics.drawImage(this.icons.elementAt(i), n + this.iconWH / 2, n2 - this.iconWH, this.iconWH, this.iconWH, this);
                }
            }
        }
        if (this.pop && this.itemCount > 0) {
            int n3 = this.d.height - this.fheight - 8;
            this.menuEY = n3;
            for (int j = this.itemCount - 1; j >= 0; --j) {
                final String s = this.items.elementAt(j);
                graphics.setColor(Color.lightGray);
                if (this.mouseX >= 0 && n3 - this.fheight + 8 <= this.mouseY && this.fwidth >= this.mouseX && n3 + 8 >= this.mouseY) {
                    graphics.fill3DRect(0, n3 - this.fheight + 8, this.fwidth, this.fheight, false);
                }
                else {
                    graphics.fill3DRect(0, n3 - this.fheight + 8, this.fwidth, this.fheight, true);
                }
                graphics.setColor(Color.black);
                graphics.drawString(s, 5, n3);
                n3 -= this.fheight;
            }
            this.menuY = n3;
        }
        graphics2.drawImage(this.buffer, 0, 0, null);
    }
    
    public void do_menu(final int n) {
        switch (n) {
            case 0: {
                (this.f = new msgbox("About RDesktop", "Created by: Raymond Anthony Samalo\nE-mail    : eng60728@leonis.nus.edu.sg\nMade with : jdk 1.0,1998\nWhat is it: a Desktop like interface for\n            website navigational purpose\nParameters:\n           msg            is the message of the day\n           img            is the background image\n           cmd            is the menu button text\n           logo           is the menu button image\n\n           menu1  .. menuN  are the menu items\n           link1  .. linkN  are the URLs      \n\n           icon1  .. iconN  are the icon image files\n           ilink1 .. ilinkN are the URLs      \n           ix1    .. ixN    are the x-center of icons\n           iy1    .. iyN    are the top-y    of icons\n           itext1 .. itextN are the icons' text\n\nNote      :\n link can be started with pop: for making messagebox\n           I will be trying to allow dragging icon or a cool Mac Desktop\nSuper Note:\n this is a freeware but if you want to support me...\nBTW       :\n THANK YOU FOR TRYING RDesktop \n")).show();
            }
            default: {
                this.do_link(this.hrefs.elementAt(n));
            }
        }
    }
    
    public void do_icon(final int n) {
        this.showStatus("Select icon" + n + " is " + this.iconText.elementAt(n));
        this.do_link(this.iconLink.elementAt(n));
    }
    
    public void do_link(final String s) {
        if (s.startsWith("pop:")) {
            (this.f = new msgbox("RDesktop message", s.substring(4))).show();
            return;
        }
        try {
            this.getAppletContext().showDocument(new URL(s));
        }
        catch (MalformedURLException ex) {
            (this.f = new msgbox("RDesktop ERROR", "PAGE NOT FOUND:\n" + s)).show();
        }
    }
    
    public void init() {
        this.setBackground(Color.cyan);
        this.d = this.size();
        this.buffer = this.createImage(this.d.width, this.d.height);
        if (this.getParameter("msg") != null) {
            (this.f = new msgbox("Message of the day..!", this.getParameter("msg"))).show();
        }
        if (this.getParameter("img") != null) {
            this.wallpaper = this.getImage(this.getDocumentBase(), this.getParameter("img"));
        }
        if (this.getParameter("logo") != null) {
            this.startlogo = this.getImage(this.getDocumentBase(), this.getParameter("logo"));
        }
        if (this.getParameter("cmd") != null) {
            this.startbtn = this.getParameter("cmd");
        }
        this.addItem("(C)RDesktop", "about");
        for (int n = 1; this.getParameter("menu" + n) != null; ++n) {
            this.addItem(this.getParameter("menu" + n), (this.getParameter("link" + n) != null) ? this.getParameter("link" + n) : "none");
        }
        for (int n2 = 1; this.getParameter("icon" + n2) != null; ++n2) {
            this.addIcon((this.getParameter("itext" + n2) != null) ? this.getParameter("itext" + n2) : "", this.getImage(this.getDocumentBase(), this.getParameter("icon" + n2)), (this.getParameter("ilink" + n2) != null) ? this.getParameter("ilink" + n2) : "none", this.getParameter("ix" + n2), this.getParameter("iy" + n2));
        }
    }
    
    public desktop() {
        this.iconWH = 32;
        this.iconText = new Vector();
        this.icons = new Vector();
        this.iconX = new Vector();
        this.iconY = new Vector();
        this.iconLink = new Vector();
        this.startbtn = "Start";
        this.items = new Vector();
        this.hrefs = new Vector();
        this.d = this.size();
        this.pop = false;
        this.fwidth = 100;
    }
}
