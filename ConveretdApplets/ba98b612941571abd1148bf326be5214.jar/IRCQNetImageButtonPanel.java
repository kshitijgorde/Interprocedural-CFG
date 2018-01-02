import java.awt.Event;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetImageButtonPanel extends Canvas
{
    private IRCQNet theApp;
    private int Mwidth;
    private int Mheight;
    private Vector ButtonsLabels;
    private Vector ButtonsImages;
    private Vector Images;
    private Vector Rect;
    private int Down;
    private boolean doNormal;
    private boolean doCenter;
    private int ButtonWidth;
    private FontMetrics FMetrics;
    private Font tFont;
    private boolean UseSystemColor;
    private int FontHeight;
    private Image Image0;
    private Image Image1;
    private Image Image2;
    private Image Image3;
    private Image Image4;
    private Image Image5;
    private Image Image6;
    private boolean beenDown;
    public static final int STATUS = 0;
    public static final int CHAT = 1;
    public static final int PRIVATE_CHAT = 2;
    public static final int OK = 3;
    public static final int CANCEL = 4;
    public static final int ROOMS = 5;
    public static final int GO = 6;
    
    public IRCQNetImageButtonPanel(final IRCQNet theApp) {
        this.UseSystemColor = true;
        this.ButtonsLabels = new Vector(10, 10);
        this.ButtonsImages = new Vector(10, 10);
        this.Images = new Vector(3);
        this.Rect = new Vector(10, 10);
        this.doNormal = false;
        this.doCenter = false;
        this.ButtonWidth = 90;
        this.tFont = new Font("Helvetica", 0, 14);
        this.beenDown = false;
        this.theApp = theApp;
        try {
            theApp.getToolkit().prepareImage(this.Image0 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/status.gif"), 16, 16, this);
            theApp.getToolkit().prepareImage(this.Image1 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/chat.gif"), 16, 16, this);
            theApp.getToolkit().prepareImage(this.Image2 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/privatechat.gif"), 16, 16, this);
            theApp.getToolkit().prepareImage(this.Image3 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/ok.gif"), 16, 16, this);
            theApp.getToolkit().prepareImage(this.Image4 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/cancel.gif"), 16, 16, this);
            theApp.getToolkit().prepareImage(this.Image5 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/sm-rooms.gif"), 16, 16, this);
            theApp.getToolkit().prepareImage(this.Image6 = theApp.getImage(theApp.getCodeBase(), "icons/Enable/go.gif"), 16, 16, this);
        }
        catch (NullPointerException ex) {}
        this.Images.addElement(this.Image0);
        this.Images.addElement(this.Image1);
        this.Images.addElement(this.Image2);
        this.Images.addElement(this.Image3);
        this.Images.addElement(this.Image4);
        this.Images.addElement(this.Image5);
        this.Images.addElement(this.Image6);
        this.FMetrics = this.getFontMetrics(this.tFont);
    }
    
    public IRCQNetImageButtonPanel(final IRCQNet ircqNet, final boolean doNormal) {
        this(ircqNet);
        this.doNormal = doNormal;
    }
    
    public void setCenter(final boolean doCenter) {
        this.doCenter = doCenter;
    }
    
    public void DelAllButtons() {
        for (int i = this.ButtonsLabels.size() - 1; i > 0; --i) {
            if (!((String)this.ButtonsLabels.elementAt(i)).equals("!STATUS!")) {
                this.DelButton((String)this.ButtonsLabels.elementAt(i));
            }
        }
        this.Down = 0;
    }
    
    public boolean setButtonsWidth(final int buttonWidth) {
        this.ButtonWidth = buttonWidth;
        return true;
    }
    
    public boolean setFontSize(final int n) {
        this.tFont = new Font("Helvetica", 0, n);
        this.FMetrics = this.getFontMetrics(this.tFont);
        this.FontHeight = this.FMetrics.getHeight();
        this.repaint();
        return true;
    }
    
    public void reshape(final int n, final int n2, final int mwidth, final int mheight) {
        super.reshape(n, n2, mwidth, mheight);
        this.Mwidth = mwidth;
        this.Mheight = mheight;
    }
    
    public boolean DelButton(final String s) {
        for (int size = this.ButtonsLabels.size(), i = 0; i < size; ++i) {
            if (s.equalsIgnoreCase((String)this.ButtonsLabels.elementAt(i))) {
                this.ButtonsLabels.removeElementAt(i);
                this.ButtonsImages.removeElementAt(i);
                this.Down = 0;
                this.repaint();
                return true;
            }
        }
        return false;
    }
    
    public boolean NewButton(final String s, final int n) {
        for (int size = this.ButtonsLabels.size(), i = 0; i < size; ++i) {
            if (s.equalsIgnoreCase((String)this.ButtonsLabels.elementAt(i))) {
                return false;
            }
        }
        this.ButtonsLabels.addElement(s);
        if (s.equalsIgnoreCase("!STATUS!")) {
            this.ButtonsImages.addElement(new Integer(0));
        }
        else {
            this.ButtonsImages.addElement(new Integer(n));
        }
        if (this.isVisible()) {
            this.repaint();
        }
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        Image image;
        try {
            image = this.createImage(this.Mwidth, this.Mheight);
        }
        catch (IllegalArgumentException ex) {
            return;
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setFont(this.tFont);
        if (this.UseSystemColor) {
            graphics.setColor(IRCQNetColors.controlColor);
        }
        else {
            graphics.setColor(this.getBackground());
        }
        graphics.fillRect(0, 0, this.Mwidth, this.Mheight);
        if (this.UseSystemColor) {
            graphics2.setColor(IRCQNetColors.controlColor);
        }
        else {
            graphics2.setColor(this.getBackground());
        }
        graphics2.fillRect(0, 0, this.Mwidth, this.Mheight);
        if (this.ButtonsLabels.size() < 1) {
            return;
        }
        final int n = this.ButtonsLabels.size() - 1;
        int buttonWidth;
        if ((n + 1) * this.ButtonWidth > this.Mwidth) {
            buttonWidth = this.Mwidth / (n + 1);
        }
        else {
            buttonWidth = this.ButtonWidth;
        }
        if (buttonWidth < 30) {
            buttonWidth = 30;
        }
        int n2 = 0;
        if (this.doCenter) {
            if (n % 2 != 0) {
                n2 = this.Mwidth / 2 - this.ButtonWidth * n;
            }
            else {
                n2 = this.Mwidth / 2 - this.ButtonWidth * n - this.ButtonWidth / 2;
            }
        }
        this.Rect.removeAllElements();
        for (int i = 0; i <= n; ++i) {
            Image image2;
            if (i == this.Down && !this.doCenter) {
                image2 = this.DrawButton(this.ButtonsLabels.elementAt(i), buttonWidth, this.Mheight - 5, true, this.ButtonsImages.elementAt(i));
            }
            else {
                image2 = this.DrawButton(this.ButtonsLabels.elementAt(i), buttonWidth, this.Mheight - 5, false, this.ButtonsImages.elementAt(i));
            }
            if (image2 != null) {
                if (this.doCenter) {
                    graphics2.drawImage(image2, i * buttonWidth + n2, 4, this);
                }
                else {
                    graphics2.drawImage(image2, i * buttonWidth, 4, this);
                }
            }
            if (this.doCenter) {
                this.Rect.addElement(new Rectangle(i * buttonWidth + n2, 4, buttonWidth, this.Mheight - 5));
            }
            else {
                this.Rect.addElement(new Rectangle(i * buttonWidth, 4, buttonWidth, this.Mheight - 5));
            }
        }
        if (!this.doCenter) {
            graphics2.setColor(IRCQNetColors.controlColor.darker());
            graphics2.drawLine(0, 0, this.Mwidth, 0);
            graphics2.setColor(Color.white);
            graphics2.drawLine(0, 1, this.Mwidth, 1);
        }
        graphics.drawImage(image, 0, 0, this);
    }
    
    public void SetSystemColors(final boolean useSystemColor) {
        this.UseSystemColor = useSystemColor;
    }
    
    public Image DrawButton(String s, int n, int n2, final boolean b, int n3) {
        this.FMetrics = this.getFontMetrics(this.tFont);
        this.FontHeight = this.FMetrics.getHeight();
        Image image;
        try {
            image = this.createImage(n, n2);
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setFont(this.tFont);
        if (this.UseSystemColor) {
            graphics.setColor(IRCQNetColors.controlColor);
        }
        else {
            graphics.setColor(Color.white);
        }
        graphics.fillRect(0, 0, n, n2);
        graphics.setColor(IRCQNetColors.controlColor);
        graphics.fillRect(2, 2, n - 2, n2 - 2);
        n2 -= 2;
        n -= 2;
        if (b && !this.doNormal) {
            graphics.setColor(Color.black);
            graphics.drawLine(2, 2, n, 2);
            graphics.drawLine(2, 2, 2, n2);
            graphics.setColor(IRCQNetColors.controlColor.brighter());
            graphics.drawLine(2, n2, n, n2);
            graphics.drawLine(n, 2, n, n2);
            graphics.setColor(IRCQNetColors.controlColor.darker());
            graphics.drawLine(3, 3, n - 1, 3);
            graphics.drawLine(3, 3, 3, n2 - 1);
        }
        else {
            graphics.setColor(IRCQNetColors.controlColor.brighter());
            graphics.drawLine(2, 2, n, 2);
            graphics.drawLine(2, 2, 2, n2);
            graphics.setColor(Color.black);
            graphics.drawLine(2, n2, n, n2);
            graphics.drawLine(n, 2, n, n2);
            graphics.setColor(IRCQNetColors.controlColor.darker());
            graphics.drawLine(3, n2 - 1, n - 1, n2 - 1);
            graphics.drawLine(n - 1, 3, n - 1, n2 - 1);
        }
        graphics.setColor(Color.black);
        if (s.equalsIgnoreCase("!STATUS!")) {
            s = "Status";
            n3 = 0;
        }
        if (s.equalsIgnoreCase("!CHANNEL!")) {
            s = "Rooms";
            n3 = 5;
        }
        final Image image2 = this.Images.elementAt(n3);
        if (this.FMetrics.stringWidth(s) > n - 25) {
            if (this.FMetrics.stringWidth(s.substring(0, 3) + "...") <= n - 25) {
                int length;
                for (length = s.length(); length > 0 && this.FMetrics.stringWidth(s.substring(0, length) + "...") > n - 25; --length) {}
                graphics.drawString(s.substring(0, length) + "...", 21, (n2 - 3 + this.FontHeight) / 2);
                graphics.drawImage(image2, 4, (n2 - image2.getHeight(null)) / 2 + 2, this);
            }
            else {
                graphics.drawImage(image2, 4, (n2 - image2.getHeight(null)) / 2 + 2, this);
            }
        }
        else {
            graphics.drawString(s, 21, (n2 - 3 + this.FontHeight) / 2);
            graphics.drawImage(image2, 4, (n2 - image2.getHeight(null)) / 2 + 2, this);
        }
        image.flush();
        return image;
    }
    
    public boolean Press(final String s) {
        if (this.doNormal) {
            return false;
        }
        for (int size = this.ButtonsLabels.size(), i = 0; i < size; ++i) {
            if (s.equalsIgnoreCase((String)this.ButtonsLabels.elementAt(i))) {
                this.Down = i;
            }
        }
        this.repaint();
        return true;
    }
    
    public boolean PaintAt(final int n, final boolean b) {
        if (!this.isVisible()) {
            return false;
        }
        final Graphics graphics = this.getGraphics();
        final int n2 = this.ButtonsLabels.size() - 1;
        int buttonWidth;
        if ((n2 + 1) * this.ButtonWidth > this.Mwidth) {
            buttonWidth = this.Mwidth / (n2 + 1);
        }
        else {
            buttonWidth = this.ButtonWidth;
        }
        int n3 = buttonWidth;
        int n4 = this.Mheight - 5;
        if (n4 <= 0) {
            return false;
        }
        int n5 = 0;
        if (this.doCenter) {
            if (n2 % 2 != 0) {
                n5 = this.Mwidth / 2 - this.ButtonWidth * n2;
            }
            else {
                n5 = this.Mwidth / 2 - this.ButtonWidth * n2 - this.ButtonWidth / 2;
            }
        }
        int n6 = n * buttonWidth;
        if (this.doCenter) {
            n6 += n5;
        }
        Image image;
        try {
            image = this.createImage(n3, n4);
        }
        catch (IllegalArgumentException ex) {
            return false;
        }
        if (image == null) {
            return false;
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setFont(this.tFont);
        graphics2.setColor(IRCQNetColors.controlColor);
        graphics2.fillRect(0, 0, n3, n4);
        n4 -= 2;
        n3 -= 2;
        if (b && !this.doNormal) {
            graphics2.setColor(Color.black);
            graphics2.drawLine(2, 2, n3, 2);
            graphics2.drawLine(2, 2, 2, n4);
            graphics2.setColor(IRCQNetColors.controlColor.brighter());
            graphics2.drawLine(2, n4, n3, n4);
            graphics2.drawLine(n3, 2, n3, n4);
            graphics2.setColor(IRCQNetColors.controlColor.darker());
            graphics2.drawLine(3, 3, n3 - 1, 3);
            graphics2.drawLine(3, 3, 3, n4 - 1);
        }
        else {
            graphics2.setColor(IRCQNetColors.controlColor.brighter());
            graphics2.drawLine(2, 2, n3, 2);
            graphics2.drawLine(2, 2, 2, n4);
            graphics2.setColor(Color.black);
            graphics2.drawLine(2, n4, n3, n4);
            graphics2.drawLine(n3, 2, n3, n4);
            graphics2.setColor(IRCQNetColors.controlColor.darker());
            graphics2.drawLine(3, n4 - 1, n3 - 1, n4 - 1);
            graphics2.drawLine(n3 - 1, 3, n3 - 1, n4 - 1);
        }
        String s = new String(this.ButtonsLabels.elementAt(n));
        final int intValue = this.ButtonsImages.elementAt(n);
        if (s.equalsIgnoreCase("!STATUS!")) {
            s = "Status";
        }
        if (s.equalsIgnoreCase("!CHANNEL!")) {
            s = "Rooms";
        }
        graphics2.setColor(Color.black);
        final Image image2 = this.Images.elementAt(intValue);
        if (this.FMetrics.stringWidth(s) > n3 - 25) {
            if (this.FMetrics.stringWidth(s.substring(0, 3) + "...") <= n3 - 25) {
                int length;
                for (length = s.length(); length > 0 && this.FMetrics.stringWidth(s.substring(0, length) + "...") > n3 - 25; --length) {}
                graphics2.drawString(s.substring(0, length) + "...", 21, (n4 - 3 + this.FontHeight) / 2);
                graphics2.drawImage(image2, 4, (n4 - image2.getHeight(null)) / 2 + 2, this);
            }
            else {
                graphics2.drawImage(image2, 4, (n4 - image2.getHeight(null)) / 2 + 2, this);
            }
        }
        else {
            graphics2.drawString(s, 21, (n4 - 3 + this.FontHeight) / 2);
            graphics2.drawImage(image2, 4, (n4 - image2.getHeight(null)) / 2 + 2, this);
        }
        image.flush();
        graphics.drawImage(image, n6, 4, this);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.doCenter) {
            return super.mouseUp(event, n, n2);
        }
        for (int size = this.Rect.size(), i = 0; i < size; ++i) {
            if (((Rectangle)this.Rect.elementAt(i)).inside(n, n2)) {
                this.PaintAt(this.Down, false);
                this.PaintAt(i, false);
                if (i == this.Down && this.beenDown) {
                    this.postEvent(new Event(this, 1001, this.ButtonsLabels.elementAt(this.Down)));
                }
            }
        }
        this.PaintAt(this.Down, false);
        return super.mouseUp(event, n, n2);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.beenDown = false;
        if (this.doCenter) {
            this.repaint();
        }
        return super.mouseExit(event, n, n2);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        for (int size = this.Rect.size(), i = 0; i < size; ++i) {
            if (((Rectangle)this.Rect.elementAt(i)).inside(n, n2)) {
                this.beenDown = true;
                this.PaintAt(this.Down, false);
                this.PaintAt(this.Down = i, true);
                if (!this.doCenter && this.theApp.appletStart) {
                    this.theApp.MPanel.ShowCard(this.ButtonsLabels.elementAt(this.Down));
                    this.theApp.MPanel.postEvent(new Event(this, 1001, this.ButtonsLabels.elementAt(this.Down)));
                }
            }
        }
        return super.mouseDown(event, n, n2);
    }
}
