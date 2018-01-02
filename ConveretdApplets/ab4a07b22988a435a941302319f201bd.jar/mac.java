import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class mac extends Applet
{
    Image wallpaper;
    String frame_target;
    int iconWH;
    int iconCount;
    Vector iconText;
    Vector icons;
    Vector iconX;
    Vector iconY;
    Vector iconLink;
    String applebtn;
    int applebtnEX;
    FontMetrics f;
    Image applelogo;
    Image buffer;
    Vector mpops;
    Vector mlinks;
    Vector macts;
    Vector mitems;
    boolean applepop;
    boolean mclick;
    int pop;
    int mix;
    int miy;
    int popselect;
    int mitemCount;
    Dimension d;
    int fwidth;
    int xmenu;
    int mouseX;
    int mouseY;
    int fheight;
    String message;
    boolean message_show;
    
    public void addIcon(final String s, final Image image, final String s2, final String s3, final String s4) {
        this.iconText.addElement(s);
        this.icons.addElement(image);
        this.iconLink.addElement(s2);
        this.iconX.addElement(s3);
        this.iconY.addElement(s4);
        ++this.iconCount;
    }
    
    public void addMItem(final String s) {
        this.mitems.addElement(s);
        this.mpops.addElement(new Vector<Vector>());
        this.mlinks.addElement(new Vector<Vector>());
        this.macts.addElement("none");
        ++this.mitemCount;
    }
    
    public void message_box(final String message) {
        this.message = message;
        this.message_show = true;
        this.repaint();
    }
    
    public boolean mouseUp(final Event event, final int mouseX, final int mouseY) {
        int stringWidth = 0;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        if (this.mouseY <= this.fheight) {
            this.mclick = true;
        }
        this.message_show = false;
        if (this.pop > -1 && !this.mpops.elementAt(this.pop).isEmpty()) {
            final Vector<String> vector = this.mpops.elementAt(this.pop);
            final Vector<String> vector2 = this.mlinks.elementAt(this.pop);
            for (int i = 0; i < vector.size(); ++i) {
                if (stringWidth < this.f.stringWidth(vector.elementAt(i))) {
                    stringWidth = this.f.stringWidth(vector.elementAt(i));
                }
            }
            final int n = stringWidth + 32;
            for (int j = 0; j < vector.size(); ++j) {
                if (this.mouseY > (j + 1) * this.fheight && this.mouseY < this.fheight * (j + 2) && this.mouseX >= this.mix && this.mouseX <= n + this.mix) {
                    this.popselect = j;
                    this.do_link(vector2.elementAt(j));
                }
                else {
                    this.popselect = -1;
                    this.pop = -1;
                }
            }
        }
        if (this.mouseY < this.fheight && this.mouseX <= 16 && this.applepop) {
            this.pop = 0;
            this.mix = 0;
            this.mclick = false;
        }
        if (this.mouseY < this.fheight && this.mouseX > this.d.width - 16) {
            switch (++this.xmenu) {
                case 1: {
                    this.message_box("RDESKTOP II (C) Raymond Anthony Samalo June 1998 ");
                    break;
                }
                case 2: {
                    this.message_box("This is a FREE ware, Desktop like Site navigation tool.");
                    break;
                }
                case 3: {
                    this.message_box("Any support moral, financial :), critics are welcome.");
                    break;
                }
                case 4: {
                    this.message_box("Contact me at eng60728@leonis.nus.edu.sg");
                    break;
                }
                case 5: {
                    this.message_box("Visit http://members.tripod.com/~yarsa");
                    this.xmenu = 0;
                    break;
                }
                default: {
                    this.message_box("Click on the logo, menu or icon, to navigate ");
                    break;
                }
            }
            this.mclick = false;
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
        int stringWidth = 0;
        int mix = 0;
        this.d = this.size();
        graphics.setColor(this.getColor("bgcolor", Color.lightGray));
        graphics.fill3DRect(0, 0, this.d.width, this.d.height, true);
        this.f = graphics.getFontMetrics();
        this.fheight = this.f.getHeight() + 3;
        if (this.wallpaper != null) {
            graphics.drawImage(this.wallpaper, 0, 0, this.d.width, this.d.height - 8, this);
        }
        graphics.setColor(this.getColor("mnu0color", Color.white));
        graphics.fill3DRect(0, 0, this.d.width, this.fheight, true);
        graphics.setColor(this.getColor("mnu1color", Color.black));
        graphics.drawRect(0, 0, this.d.width, this.fheight);
        if (this.iconCount > 0) {
            for (int i = this.iconCount - 1; i >= 0; --i) {
                if (this.icons.elementAt(i) != null) {
                    graphics.setColor(Color.lightGray);
                    final int stringWidth2 = graphics.getFontMetrics().stringWidth(this.iconText.elementAt(i));
                    final int n = Integer.parseInt(this.iconX.elementAt(i)) - stringWidth2 / 2;
                    final int n2 = Integer.parseInt(this.iconY.elementAt(i)) + this.iconWH;
                    if (n <= this.mouseX && n2 - this.iconWH <= this.mouseY && n + stringWidth2 >= this.mouseX && n2 + this.fheight - 8 >= this.mouseY) {
                        graphics.draw3DRect(n - 4, n2 - this.iconWH - 4, stringWidth2 + 8, this.fheight + this.iconWH, true);
                    }
                    graphics.setColor(Color.black);
                    graphics.fillRect(n, n2 + 2, graphics.getFontMetrics().stringWidth(this.iconText.elementAt(i)), this.fheight - 8);
                    graphics.setColor(Color.white);
                    graphics.drawString(this.iconText.elementAt(i), n, n2 + this.fheight - 8);
                    graphics.drawImage(this.icons.elementAt(i), n + this.iconWH / 2, n2 - this.iconWH, this.iconWH, this.iconWH, this);
                }
            }
        }
        if (this.message_show) {
            final int n3 = graphics.getFontMetrics().stringWidth(this.message) + 64;
            final int n4 = (this.d.width - n3) / 2;
            final int n5 = this.d.height / 2 - this.fheight * 2;
            graphics.setColor(this.getColor("msg0color", Color.gray));
            graphics.fill3DRect(n4, n5, n3, this.fheight * 4, true);
            graphics.setColor(this.getColor("msg1color", Color.white));
            graphics.drawString(this.message, n4 + 32, n5 + this.fheight * 2);
            if (this.applelogo != null) {
                graphics.drawImage(this.applelogo, n4 + 2, n5 + this.fheight, 28, 28, this);
            }
        }
        this.applebtnEX = 16;
        int j = 0;
        if (this.applelogo != null) {
            graphics.drawImage(this.applelogo, 0, 0, 16, this.fheight, this);
            if (this.mouseY < this.fheight && this.mouseX >= 0 && this.mouseX <= 16) {
                graphics.draw3DRect(0, 0, 16, this.fheight, true);
            }
            j = 1;
        }
        while (j < this.mitemCount) {
            mix += this.applebtnEX;
            mix += 16;
            if (j == this.mitemCount - 1) {
                mix = this.d.width - 16;
            }
            final String s = this.mitems.elementAt(j);
            this.applebtnEX = graphics.getFontMetrics().stringWidth(s);
            if (this.mouseY < this.fheight && this.mouseX >= mix && this.mouseX <= mix + this.applebtnEX) {
                if (this.mclick) {
                    this.pop = j;
                    this.mix = mix;
                    if (!this.macts.elementAt(j).equals("none")) {
                        this.do_link((String)this.macts.elementAt(j));
                    }
                }
                graphics.setColor(this.getColor("mnu1color", Color.black));
                graphics.fillRect(mix, 1, this.applebtnEX, this.fheight - 2);
                graphics.setColor(this.getColor("mnu0color", Color.white));
                graphics.drawString(s, mix, this.fheight - 3);
            }
            else {
                graphics.setColor(this.getColor("mnu0color", Color.white));
                graphics.fillRect(mix, 1, this.applebtnEX, this.fheight - 2);
                graphics.setColor(this.getColor("mnu1color", Color.black));
                graphics.drawString(s, mix, this.fheight - 3);
            }
            ++j;
        }
        this.mclick = false;
        final int mix2 = this.mix;
        if (this.pop > -1 && !this.mpops.elementAt(this.pop).isEmpty()) {
            final Vector<String> vector = this.mpops.elementAt(this.pop);
            for (int k = 0; k < vector.size(); ++k) {
                if (stringWidth < graphics.getFontMetrics().stringWidth(vector.elementAt(k))) {
                    stringWidth = graphics.getFontMetrics().stringWidth(vector.elementAt(k));
                }
            }
            final int n6 = stringWidth + 32;
            graphics.setColor(this.getColor("mnu0color", Color.white));
            graphics.fillRect(mix2, this.fheight, n6, vector.size() * this.fheight);
            graphics.setColor(this.getColor("mnu1color", Color.black));
            graphics.drawRect(mix2, this.fheight, n6, vector.size() * this.fheight);
            for (int l = 0; l < vector.size(); ++l) {
                graphics.setColor(this.getColor("mnu1color", Color.black));
                if (this.mouseY > this.fheight * (l + 1) && this.mouseY < this.fheight * (l + 2) && this.mouseX > mix2 && this.mouseX < n6 + mix2) {
                    graphics.fill3DRect(mix2, (l + 1) * this.fheight, n6, this.fheight, true);
                    graphics.setColor(this.getColor("mnu0color", Color.white));
                }
                graphics.drawString(vector.elementAt(l), mix2 + 16, (l + 2) * this.fheight);
            }
        }
        graphics2.drawImage(this.buffer, 0, 0, null);
    }
    
    public void do_icon(final int n) {
        this.do_link(this.iconLink.elementAt(n));
    }
    
    public void do_link(final String s) {
        if (!s.equals("none")) {
            if (s.startsWith("say:")) {
                this.message_box(s.substring(4));
                return;
            }
            try {
                if (this.frame_target == null) {
                    this.getAppletContext().showDocument(new URL(s));
                    return;
                }
                this.getAppletContext().showDocument(new URL(s), this.frame_target);
            }
            catch (MalformedURLException ex) {
                this.message_box("Malformed URL error at " + s);
            }
        }
    }
    
    public void init() {
        this.d = this.size();
        this.buffer = this.createImage(this.d.width, this.d.height);
        this.frame_target = this.getParameter("target");
        if (this.getParameter("img") != null) {
            this.wallpaper = this.getImage(this.getDocumentBase(), this.getParameter("img"));
        }
        int n = 0;
        if (this.getParameter("logo") != null) {
            this.addMItem("@");
            this.applelogo = this.getImage(this.getDocumentBase(), this.getParameter("logo"));
            final String parameter;
            if ((parameter = this.getParameter("poplogo")) != null) {
                this.applepop = true;
                final Vector<String> vector = this.mpops.elementAt(0);
                final Vector<String> vector2 = this.mlinks.elementAt(0);
                for (int n2 = 1; this.getParameter(String.valueOf(parameter) + n2) != null; ++n2) {
                    vector.addElement(this.getParameter(String.valueOf(parameter) + n2));
                    vector2.addElement((this.getParameter(String.valueOf(parameter) + "link" + n2) == null) ? "none" : this.getParameter(String.valueOf(parameter) + "link" + n2));
                }
                n = 1;
            }
        }
        for (int n3 = 1; this.getParameter("menu" + n3) != null; ++n3) {
            this.addMItem(this.getParameter("menu" + n3));
            final String parameter2;
            if ((parameter2 = this.getParameter("pop" + n3)) != null) {
                final Vector<String> vector3 = this.mpops.elementAt(n + n3 - 1);
                final Vector<String> vector4 = this.mlinks.elementAt(n + n3 - 1);
                for (int n4 = 1; this.getParameter(String.valueOf(parameter2) + n4) != null; ++n4) {
                    vector3.addElement(this.getParameter(String.valueOf(parameter2) + n4));
                    vector4.addElement((this.getParameter(String.valueOf(parameter2) + "link" + n4) == null) ? "none" : this.getParameter(String.valueOf(parameter2) + "link" + n4));
                }
            }
            else {
                final String parameter3;
                if ((parameter3 = this.getParameter("link" + n3)) != null) {
                    this.macts.setElementAt(parameter3, n + n3 - 1);
                }
            }
        }
        this.addMItem("?");
        for (int n5 = 1; this.getParameter("icon" + n5) != null; ++n5) {
            this.addIcon((this.getParameter("itext" + n5) != null) ? this.getParameter("itext" + n5) : "", this.getImage(this.getDocumentBase(), this.getParameter("icon" + n5)), (this.getParameter("ilink" + n5) != null) ? this.getParameter("ilink" + n5) : "none", this.getParameter("ix" + n5), this.getParameter("iy" + n5));
        }
        this.message_box((this.getParameter("msg") != null) ? this.getParameter("msg") : "Welcome To (C)RDesktop II");
    }
    
    Color getColor(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return color;
        }
        if (parameter.charAt(0) == '#') {
            return new Color(Integer.parseInt(parameter.substring(1), 16));
        }
        if (parameter.equals("black")) {
            return Color.black;
        }
        if (parameter.equals("white")) {
            return Color.white;
        }
        if (parameter.equals("lightGray")) {
            return Color.lightGray;
        }
        if (parameter.equals("gray")) {
            return Color.gray;
        }
        if (parameter.equals("darkGray")) {
            return Color.darkGray;
        }
        if (parameter.equals("red")) {
            return Color.red;
        }
        if (parameter.equals("pink")) {
            return Color.pink;
        }
        if (parameter.equals("magenta")) {
            return Color.magenta;
        }
        if (parameter.equals("yellow")) {
            return Color.yellow;
        }
        if (parameter.equals("green")) {
            return Color.green;
        }
        if (parameter.equals("cyan")) {
            return Color.cyan;
        }
        if (parameter.equals("blue")) {
            return Color.blue;
        }
        return color;
    }
    
    public mac() {
        this.iconWH = 32;
        this.iconText = new Vector();
        this.icons = new Vector();
        this.iconX = new Vector();
        this.iconY = new Vector();
        this.iconLink = new Vector();
        this.applebtn = "Start";
        this.mpops = new Vector();
        this.mlinks = new Vector();
        this.macts = new Vector();
        this.mitems = new Vector();
        this.applepop = false;
        this.mclick = false;
        this.pop = -1;
        this.popselect = -1;
        this.d = this.size();
        this.fwidth = 100;
        this.message_show = false;
    }
}
