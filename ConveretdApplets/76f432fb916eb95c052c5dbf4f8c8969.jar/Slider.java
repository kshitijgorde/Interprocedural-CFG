import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Slider extends Applet
{
    private String text;
    private String originalText;
    private int pos;
    private Font textfont;
    private int textfontsize;
    private String textfontPBI;
    private Font linkfont;
    private int linkfontsize;
    private String linkfontPBI;
    private Color colorlink;
    private Color forC;
    private Color backC;
    private int h;
    private int hgh;
    private int wdh;
    private int allWdh;
    private int delay;
    private int step;
    private int mouseX;
    private int mouseY;
    private boolean firstInit;
    private String urlBase;
    private LinkClass[] LC;
    Image offImage;
    Graphics offG;
    private Timer myTimer;
    
    public void paint(final Graphics graphics) {
        if (this.firstInit) {
            this.offG.setColor(Color.white);
            this.offG.fillRect(0, 0, this.wdh, this.hgh);
            return;
        }
        this.offG.setColor(this.forC);
        this.offG.setFont(this.textfont);
        this.offG.drawString(this.text, this.pos, this.h);
        int n = 0;
        boolean b = true;
        this.offG.setFont(this.linkfont);
        while (this.LC[n].getLink() != null) {
            final int otherWidth = this.LC[n].getOtherWidth();
            if (this.mouseX > otherWidth + this.pos && this.mouseX < otherWidth + this.pos + this.LC[n].getWidth()) {
                b = false;
            }
            this.offG.setColor(this.backC);
            this.offG.fillRect(this.pos + otherWidth, 0, this.LC[n].getWidth(), this.hgh);
            this.offG.setColor(this.colorlink);
            this.offG.drawString(this.LC[n].getLinkName(), this.pos + otherWidth, this.h);
            this.offG.drawLine(this.pos + otherWidth, this.h + 2, this.pos + otherWidth + this.LC[n].getWidth(), this.h + 2);
            ++n;
        }
        this.pos -= this.step;
        if (this.allWdh < -1 * this.pos) {
            this.pos = this.wdh;
        }
        graphics.drawImage(this.offImage, 0, 0, this.wdh, this.hgh, this);
        if (b) {
            this.setCursor(Cursor.getDefaultCursor());
        }
        if (!b) {
            this.setCursor(new Cursor(12));
            try {
                if (this.mouseX > 0) {
                    Thread.sleep(200L);
                }
            }
            catch (InterruptedException ex) {}
        }
    }
    
    void this_mouseClicked(final MouseEvent mouseEvent) {
        for (int n = 0; this.LC[n].getLink() != null; ++n) {
            final int otherWidth = this.LC[n].getOtherWidth();
            if (this.mouseX > otherWidth + this.pos && this.mouseX < otherWidth + this.pos + this.LC[n].getWidth()) {
                try {
                    this.getAppletContext().showDocument(new URL(this.urlBase + this.LC[n].getLink()));
                    return;
                }
                catch (MalformedURLException ex) {
                    return;
                }
            }
        }
    }
    
    public Slider() {
        this.mouseX = 0;
        this.mouseY = 0;
        this.firstInit = true;
        this.LC = new LinkClass[50];
    }
    
    public void startAnimation() {
        this.myTimer.start();
        this.firstInit = false;
    }
    
    public void update(final Graphics graphics) {
        this.offG.clearRect(0, 0, this.wdh, this.hgh);
        this.offG.setColor(this.backC);
        this.offG.fillRect(0, 0, this.wdh, this.hgh);
        this.paint(graphics);
    }
    
    void this_mouseMoved(final MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX();
    }
    
    private void usePageParams() {
        this.urlBase = this.getParameter("url");
        if (this.urlBase == null) {
            this.urlBase = "";
        }
        this.text = this.getParameter("text");
        if (this.text == null) {
            this.text = "";
        }
        this.originalText = this.text;
        String parameter = this.getParameter("background");
        String parameter2 = this.getParameter("foreground");
        String parameter3 = this.getParameter("colorlink");
        String parameter4 = this.getParameter("textfont");
        String parameter5 = this.getParameter("textfontsize");
        String parameter6 = this.getParameter("textfontPBI");
        String parameter7 = this.getParameter("linkfont");
        String parameter8 = this.getParameter("linkfontsize");
        String parameter9 = this.getParameter("linkfontPBI");
        String parameter10 = this.getParameter("stepdelay");
        String parameter11 = this.getParameter("step");
        if (this.text == null) {
            this.text = "";
        }
        if (parameter == null) {
            parameter = "5080FF";
        }
        if (parameter2 == null) {
            parameter2 = "FFFFFF";
        }
        this.setBackground(this.stringToColor(parameter));
        this.setForeground(this.stringToColor(parameter2));
        if (parameter3 == null) {
            parameter3 = "BBEEFF";
        }
        this.colorlink = this.stringToColor(parameter3);
        if (parameter4 == null) {
            parameter4 = "Arial";
        }
        if (parameter5 == null) {
            parameter5 = "14";
        }
        this.textfontsize = Integer.valueOf(parameter5);
        int n = 0;
        if (parameter6 == null) {
            parameter6 = "";
            n = 0;
        }
        if (parameter6.indexOf("I") > -1) {
            n |= 0x2;
        }
        if (parameter6.indexOf("B") > -1) {
            n |= 0x1;
        }
        this.textfont = new Font(parameter4, n, this.textfontsize);
        this.offG.setFont(this.textfont);
        int n2 = 0;
        if (parameter7 == null) {
            parameter7 = "Arial";
        }
        if (parameter8 == null) {
            parameter8 = "14";
        }
        this.linkfontsize = Integer.valueOf(parameter8);
        if (parameter9 == null) {
            parameter9 = "";
            n2 = 0;
        }
        if (parameter9.indexOf("I") > -1) {
            n2 |= 0x2;
        }
        if (parameter9.indexOf("B") > -1) {
            n2 |= 0x1;
        }
        this.linkfont = new Font(parameter7, n2, this.linkfontsize);
        if (parameter10 == null) {
            parameter10 = "20";
        }
        this.delay = Integer.valueOf(parameter10);
        if (parameter11 == null) {
            parameter11 = "1";
        }
        this.step = Integer.valueOf(parameter11);
    }
    
    public void reinitializeAll() {
        this.pos = this.wdh;
        this.usePageParams();
        this.tokenizeText();
        this.allWdh = this.offG.getFontMetrics().stringWidth(this.text);
        this.repaint();
    }
    
    public void reInit(String text) {
        this.originalText = text;
        if (text == null) {
            text = "";
        }
        this.text = text;
        this.allWdh = this.offG.getFontMetrics().stringWidth(this.text);
        this.pos = this.wdh;
        this.repaint();
        this.text = text;
        this.tokenizeText();
        this.allWdh = this.offG.getFontMetrics().stringWidth(this.text);
        this.pos = this.wdh;
        this.repaint();
    }
    
    public void init() {
        this.hgh = this.getSize().height;
        this.wdh = this.getSize().width;
        this.offImage = this.createImage(this.wdh, this.hgh);
        this.offG = this.offImage.getGraphics();
        this.h = this.offG.getFontMetrics().getHeight() / 4 + this.hgh / 2;
        this.pos = this.wdh;
        this.usePageParams();
        this.backC = this.getBackground();
        this.forC = this.getForeground();
        this.tokenizeText();
        this.allWdh = this.offG.getFontMetrics().stringWidth(this.text);
        this.repaint();
        this.addMouseMotionListener(new MouseMotionAdapter() {
            {
                Slider.this.getClass();
            }
            
            public void mouseMoved(final MouseEvent mouseEvent) {
                Slider.this.this_mouseMoved(mouseEvent);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                Slider.this.this_mouseClicked(mouseEvent);
            }
            
            {
                Slider.this.getClass();
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                Slider.this.this_mouseExited(mouseEvent);
            }
        });
        this.myTimer = new Timer("myTimer", this, this.delay);
    }
    
    void this_mouseExited(final MouseEvent mouseEvent) {
        this.mouseX = -1;
    }
    
    private Color stringToColor(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
    
    public String getText() {
        return this.originalText;
    }
    
    private void tokenizeText() {
        int n = 0;
        int n2 = 0;
        try {
            while (true) {
                final int index = this.text.indexOf("</A>", n2);
                if (index < 0) {
                    break;
                }
                n2 = index + 1;
                ++n;
            }
        }
        catch (StringIndexOutOfBoundsException ex) {}
        for (int i = 0; i <= n; ++i) {
            this.LC[i] = new LinkClass();
        }
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        try {
            while (true) {
                final int index2 = this.text.indexOf("</A>", n4);
                final int index3 = this.text.indexOf("</A>", n5);
                if (index2 < 0 || index3 < 0) {
                    break;
                }
                final String substring = this.text.substring(n4, index2);
                final String substring2 = this.text.substring(n5, index3);
                final String substring3 = substring.substring(substring.lastIndexOf(">") + 1, substring.length());
                final String substring4 = substring2.substring(0, substring2.lastIndexOf("\">"));
                final String substring5 = substring4.substring(substring4.lastIndexOf("<A HREF=\"") + 9, substring4.length());
                this.LC[n3].setLinkName(substring3);
                this.LC[n3].setWidth(this.offG.getFontMetrics().stringWidth(this.LC[n3].getLinkName()));
                this.LC[n3].setLink(substring5);
                n4 = index2 + 1;
                n5 = index3 + 1;
                ++n3;
            }
        }
        catch (StringIndexOutOfBoundsException ex2) {}
        int n6 = 0;
        for (int n7 = 0; this.LC[n7].getLink() != null; ++n7) {
            final String string = "<A HREF=\"" + this.LC[n7].getLink() + "\">";
            final int index4 = this.text.indexOf("<A HREF=\"" + this.LC[n7].getLink() + "\">");
            final String substring6 = this.text.substring(n6, index4);
            this.LC[n7].setPos(index4);
            this.LC[n7].setWidth(this.offG.getFontMetrics().stringWidth(this.LC[n7].getLinkName()));
            this.text = substring6 + this.text.substring(index4 + string.length(), this.text.length());
            this.text = this.text.substring(0, this.text.indexOf("</A>")) + this.text.substring(this.text.indexOf("</A>") + 4, this.text.length());
            n6 = 0;
        }
        for (int n8 = 0; this.LC[n8].getLink() != null; ++n8) {
            this.LC[n8].setOtherWidth(this.offG.getFontMetrics().stringWidth(this.text.substring(0, this.LC[n8].getPos())));
        }
    }
}
