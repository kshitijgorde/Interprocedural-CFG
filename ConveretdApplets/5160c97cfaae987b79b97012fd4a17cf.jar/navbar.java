import java.awt.Panel;
import java.awt.Cursor;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.awt.Component;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class navbar extends Applet implements Runnable
{
    private int size;
    private int spacing;
    private int fontstyle;
    private Image bgImage;
    private Font ft;
    String target;
    private Color BgColor;
    Color HBarColor;
    Color BarColor;
    Color BarHFontColor;
    Color BarNFontColor;
    Color HMenuColor;
    Color MenuColor;
    Color HFontColor;
    Color NFontColor;
    private boolean check;
    private Vector mt;
    private Vector mp;
    private Vector mlb;
    boolean start;
    
    public void stop() {
    }
    
    public void paint(final Graphics graphics) {
        if (this.check) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.ft);
            final int n = fontMetrics.getAscent() + fontMetrics.getDescent() + fontMetrics.getLeading();
            if (this.bgImage != null) {
                final int n2 = 0;
                int i = n;
                if (graphics.drawImage(this.bgImage, n2, i, this)) {
                    while (i < this.getSize().height) {
                        for (int j = 0; j < this.getSize().width; j += this.bgImage.getWidth(null)) {
                            graphics.drawImage(this.bgImage, j, i, this);
                        }
                        i += this.bgImage.getHeight(null);
                    }
                }
            }
            graphics.setColor(this.BarColor);
            graphics.fillRect(0, 0, this.getSize().width, n);
            if (this.start) {
                this.showItems(true);
                this.start = false;
            }
        }
        else {
            graphics.setColor(Color.black);
            graphics.drawString("Please do not remove the credits!", 15, 15);
        }
    }
    
    public navbar() {
        this.size = 10;
        this.spacing = 75;
        this.fontstyle = 1;
        this.bgImage = null;
        this.ft = new Font("TimesRoman", this.fontstyle, this.size);
        this.target = "_blank";
        this.BgColor = new Color(16777215);
        this.HBarColor = new Color(0);
        this.BarColor = new Color(0);
        this.BarHFontColor = new Color(16776960);
        this.BarNFontColor = new Color(16777215);
        this.HMenuColor = new Color(0);
        this.MenuColor = new Color(0);
        this.HFontColor = new Color(16776960);
        this.NFontColor = new Color(16777215);
        this.check = false;
        this.mt = new Vector();
        this.mp = new Vector();
        this.mlb = new Vector();
        this.start = true;
    }
    
    void showItems(final boolean b) {
        for (int i = 0; i < this.mlb.size(); ++i) {
            ((MenuLabel)this.mlb.elementAt(i)).setVisible(b);
        }
        for (int j = 0; j < this.mt.size(); ++j) {
            ((MenuTitle)this.mt.elementAt(j)).setVisible(b);
        }
    }
    
    void showMenu(final boolean visible) {
        for (int i = 0; i < this.mp.size(); ++i) {
            ((MenuPanel)this.mp.elementAt(i)).setVisible(visible);
        }
    }
    
    public void start() {
        this.showItems(false);
        this.showMenu(false);
        this.start = true;
    }
    
    public void run() {
    }
    
    public void init() {
        final String parameter = this.getParameter("size");
        if (parameter != null) {
            this.size = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("fontstyle");
        if (parameter2 != null) {
            this.fontstyle = Integer.parseInt(parameter2);
        }
        if (this.fontstyle > 3) {
            this.fontstyle = 3;
        }
        final String parameter3 = this.getParameter("font");
        if (parameter3 != null) {
            this.ft = new Font(parameter3, this.fontstyle, this.size);
        }
        final String parameter4 = this.getParameter("spacing");
        if (parameter4 != null) {
            this.spacing = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("target");
        if (parameter5 != null) {
            this.target = parameter5;
        }
        final String parameter6 = this.getParameter("BgColor");
        if (parameter6 != null) {
            this.BgColor = new Color(Integer.parseInt(parameter6, 16));
        }
        final String parameter7 = this.getParameter("HBarColor");
        if (parameter7 != null) {
            this.HBarColor = new Color(Integer.parseInt(parameter7, 16));
        }
        final String parameter8 = this.getParameter("BarColor");
        if (parameter8 != null) {
            this.BarColor = new Color(Integer.parseInt(parameter8, 16));
        }
        final String parameter9 = this.getParameter("BarHFontColor");
        if (parameter9 != null) {
            this.BarHFontColor = new Color(Integer.parseInt(parameter9, 16));
        }
        final String parameter10 = this.getParameter("BarNFontColor");
        if (parameter10 != null) {
            this.BarNFontColor = new Color(Integer.parseInt(parameter10, 16));
        }
        final String parameter11 = this.getParameter("HMenuColor");
        if (parameter11 != null) {
            this.HMenuColor = new Color(Integer.parseInt(parameter11, 16));
        }
        final String parameter12 = this.getParameter("MenuColor");
        if (parameter12 != null) {
            this.MenuColor = new Color(Integer.parseInt(parameter12, 16));
        }
        final String parameter13 = this.getParameter("HFontColor");
        if (parameter13 != null) {
            this.HFontColor = new Color(Integer.parseInt(parameter13, 16));
        }
        final String parameter14 = this.getParameter("NFontColor");
        if (parameter14 != null) {
            this.NFontColor = new Color(Integer.parseInt(parameter14, 16));
        }
        final String parameter15 = this.getParameter("bgImage");
        if (parameter15 != null) {
            this.bgImage = this.getImage(this.getDocumentBase(), parameter15);
        }
        if (this.getParameter("NavBar") != null && this.getParameter("NavBar").equals("Free for non-commercial use") && this.getParameter("http://lktoh.tripod.com") != null) {
            this.check = true;
        }
        if (this.HBarColor.equals(this.BarColor)) {
            if (this.HBarColor.getRGB() != 0) {
                this.HBarColor = new Color(this.HBarColor.getRGB() - 1);
            }
            else {
                this.HBarColor = new Color(this.HBarColor.getRGB() + 1);
            }
        }
        if (this.HMenuColor.equals(this.MenuColor)) {
            if (this.HMenuColor.getRGB() != 0) {
                this.HMenuColor = new Color(this.HMenuColor.getRGB() - 1);
            }
            else {
                this.HMenuColor = new Color(this.HMenuColor.getRGB() + 1);
            }
        }
        this.setBackground(this.BgColor);
        this.setFont(this.ft);
        final FontMetrics fontMetrics = this.getFontMetrics(this.ft);
        final int n = fontMetrics.getAscent() + fontMetrics.getDescent() + fontMetrics.getLeading();
        int n2 = 0;
        int int1 = 0;
        while (this.getParameter("Menu" + n2) != null) {
            try {
                int stringWidth = 0;
                if (this.getParameter("Menu" + n2 + "Position") != null) {
                    int1 = Integer.parseInt(this.getParameter("Menu" + n2 + "Position"));
                }
                MenuTitle menuTitle;
                if (this.getParameter("Menu" + n2 + "URL") != null) {
                    menuTitle = new MenuTitle(this.getParameter("Menu" + n2), new URL(this.getDocumentBase(), this.getParameter("Menu" + n2 + "URL")), n2);
                }
                else {
                    for (int n3 = 0; this.getParameter("Menu" + n2 + "Item" + n3) != null; ++n3) {
                        final String string = "      " + this.getParameter("Menu" + n2 + "Item" + n3);
                        if (fontMetrics.stringWidth(string) > stringWidth) {
                            stringWidth = fontMetrics.stringWidth(string);
                        }
                    }
                    int n4 = 0;
                    final MenuPanel menuPanel = new MenuPanel();
                    menuPanel.setBackground(this.MenuColor);
                    menuPanel.setVisible(false);
                    int n5 = 0;
                    int n6 = 0;
                    while (this.getParameter("Menu" + n2 + "Item" + n4) != null) {
                        MenuLabel menuLabel;
                        if (this.getParameter("Menu" + n2 + "Target" + n4) != null) {
                            menuLabel = new MenuLabel(this.getParameter("Menu" + n2 + "Item" + n4), new URL(this.getDocumentBase(), this.getParameter("Menu" + n2 + "URL" + n4)), this.getParameter("Menu" + n2 + "Target" + n4));
                        }
                        else {
                            menuLabel = new MenuLabel(this.getParameter("Menu" + n2 + "Item" + n4), new URL(this.getDocumentBase(), this.getParameter("Menu" + n2 + "URL" + n4)), this.target);
                        }
                        if (n * (n4 + 2) > this.getSize().height) {
                            menuLabel.setBounds(stringWidth + 2, n * n5 + 2, stringWidth, n);
                            ++n5;
                        }
                        else {
                            menuLabel.setBounds(2, n * n6 + 2, stringWidth, n);
                            ++n6;
                        }
                        this.mlb.addElement(menuLabel);
                        menuPanel.add(menuLabel);
                        ++n4;
                    }
                    if (n5 != 0) {
                        menuPanel.setBounds(int1, n, stringWidth * 2 + 4, n * n6 + 4);
                    }
                    else {
                        menuPanel.setBounds(int1, n, stringWidth + 4, n * n6 + 4);
                    }
                    this.mp.addElement(menuPanel);
                    this.add(menuPanel);
                    menuTitle = new MenuTitle(this.getParameter("Menu" + n2), menuPanel);
                }
                menuTitle.setBounds(int1, 0, fontMetrics.stringWidth("    " + this.getParameter("Menu" + n2)), n);
                this.mt.addElement(menuTitle);
                this.add(menuTitle);
                int1 += fontMetrics.stringWidth(this.getParameter("Menu" + n2)) + this.spacing;
            }
            catch (MalformedURLException ex) {}
            ++n2;
        }
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(final MouseEvent mouseEvent) {
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
            }
            
            {
                navbar.this.getClass();
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                navbar.this.showMenu(false);
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
            }
        });
    }
    
    public class MenuLabel extends Label
    {
        MenuLabel(final String s, final URL url, final String s2) {
            navbar.this.getClass();
            super.setVisible(false);
            super.setForeground(navbar.this.NFontColor);
            super.setBackground(navbar.this.MenuColor);
            super.setText("   " + s + "   ");
            super.addMouseListener(new MouseListener() {
                public void mouseClicked(final MouseEvent mouseEvent) {
                    navbar.this.getAppletContext().showDocument(url, s2);
                }
                
                public void mousePressed(final MouseEvent mouseEvent) {
                }
                
                {
                    MenuLabel.this.getClass();
                }
                
                public void mouseReleased(final MouseEvent mouseEvent) {
                }
                
                public void mouseEntered(final MouseEvent mouseEvent) {
                    mouseEvent.getComponent().setCursor(new Cursor(12));
                    mouseEvent.getComponent().setBackground(navbar.this.HMenuColor);
                    mouseEvent.getComponent().setForeground(navbar.this.HFontColor);
                }
                
                public void mouseExited(final MouseEvent mouseEvent) {
                    mouseEvent.getComponent().setBackground(navbar.this.MenuColor);
                    mouseEvent.getComponent().setForeground(navbar.this.NFontColor);
                }
            });
        }
    }
    
    public class MenuTitle extends Label
    {
        boolean raised;
        boolean current;
        
        MenuTitle(final String s, final URL url, final int n) {
            navbar.this.getClass();
            super.setVisible(false);
            super.setBackground(navbar.this.BarColor);
            super.setForeground(navbar.this.BarNFontColor);
            super.setText("  " + s + "  ");
            super.addMouseListener(new MouseListener() {
                public void mouseClicked(final MouseEvent mouseEvent) {
                    MenuTitle.this.current = true;
                    MenuTitle.this.raised = false;
                    mouseEvent.getComponent().setForeground(navbar.this.BarNFontColor);
                    mouseEvent.getComponent().setBackground(navbar.this.HBarColor);
                    mouseEvent.getComponent().setForeground(navbar.this.BarHFontColor);
                    if (navbar.this.getParameter("Menu" + n + "Target") != null) {
                        navbar.this.getAppletContext().showDocument(url, navbar.this.getParameter("Menu" + n + "Target"));
                    }
                    else {
                        navbar.this.getAppletContext().showDocument(url, navbar.this.target);
                    }
                }
                
                public void mousePressed(final MouseEvent mouseEvent) {
                }
                
                {
                    MenuTitle.this.getClass();
                }
                
                public void mouseReleased(final MouseEvent mouseEvent) {
                }
                
                public void mouseEntered(final MouseEvent mouseEvent) {
                    MenuTitle.this.current = true;
                    MenuTitle.this.raised = true;
                    navbar.this.showMenu(false);
                    mouseEvent.getComponent().setCursor(new Cursor(12));
                    mouseEvent.getComponent().setBackground(navbar.this.HBarColor);
                    mouseEvent.getComponent().setForeground(navbar.this.BarHFontColor);
                }
                
                public void mouseExited(final MouseEvent mouseEvent) {
                    MenuTitle.this.current = false;
                    mouseEvent.getComponent().setBackground(navbar.this.BarColor);
                    mouseEvent.getComponent().setForeground(navbar.this.BarNFontColor);
                }
            });
        }
        
        MenuTitle(final String s, final MenuPanel menuPanel) {
            navbar.this.getClass();
            super.setVisible(this.current = false);
            super.setBackground(navbar.this.BarColor);
            super.setForeground(navbar.this.BarNFontColor);
            super.setText("  " + s + "  ");
            super.addMouseListener(new MouseListener() {
                public void mouseClicked(final MouseEvent mouseEvent) {
                    MenuTitle.this.current = true;
                    if (menuPanel.isVisible()) {
                        mouseEvent.getComponent().setBackground(navbar.this.BarColor);
                        mouseEvent.getComponent().setForeground(navbar.this.BarNFontColor);
                        menuPanel.setVisible(false);
                        MenuTitle.this.raised = true;
                    }
                    else {
                        mouseEvent.getComponent().setBackground(navbar.this.HBarColor);
                        mouseEvent.getComponent().setForeground(navbar.this.BarHFontColor);
                        menuPanel.setVisible(true);
                        MenuTitle.this.raised = false;
                    }
                }
                
                public void mousePressed(final MouseEvent mouseEvent) {
                }
                
                {
                    MenuTitle.this.getClass();
                }
                
                public void mouseReleased(final MouseEvent mouseEvent) {
                }
                
                public void mouseEntered(final MouseEvent mouseEvent) {
                    MenuTitle.this.current = true;
                    MenuTitle.this.raised = true;
                    mouseEvent.getComponent().setCursor(new Cursor(12));
                    mouseEvent.getComponent().setBackground(navbar.this.HBarColor);
                    mouseEvent.getComponent().setForeground(navbar.this.BarHFontColor);
                    String parameter = "no";
                    if (navbar.this.getParameter("hover") != null) {
                        parameter = navbar.this.getParameter("hover");
                    }
                    if (parameter.equals("yes")) {
                        MenuTitle.this.raised = false;
                        navbar.this.showMenu(false);
                        menuPanel.setVisible(true);
                    }
                }
                
                public void mouseExited(final MouseEvent mouseEvent) {
                    MenuTitle.this.current = false;
                    mouseEvent.getComponent().setBackground(navbar.this.HBarColor);
                    mouseEvent.getComponent().setForeground(navbar.this.BarHFontColor);
                    mouseEvent.getComponent().setBackground(navbar.this.BarColor);
                    mouseEvent.getComponent().setForeground(navbar.this.BarNFontColor);
                }
            });
        }
        
        public void paint(final Graphics graphics) {
            if (this.current) {
                Color brighter = navbar.this.BarColor.brighter();
                if (navbar.this.getParameter("BarButton") != null) {
                    brighter = new Color(Integer.parseInt(navbar.this.getParameter("BarButton"), 16));
                }
                graphics.setColor(brighter);
                graphics.draw3DRect(0, 0, super.getSize().width - 1, super.getSize().height - 1, this.raised);
            }
        }
    }
    
    public class MenuPanel extends Panel
    {
        public void paint(final Graphics graphics) {
            graphics.setColor(navbar.this.MenuColor.darker());
            graphics.draw3DRect(0, 0, super.getSize().width - 1, super.getSize().height - 1, true);
        }
        
        public MenuPanel() {
            navbar.this.getClass();
        }
    }
}
