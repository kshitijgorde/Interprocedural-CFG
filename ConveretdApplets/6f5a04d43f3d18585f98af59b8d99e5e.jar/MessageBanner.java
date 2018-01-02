import java.awt.Container;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Hashtable;
import java.awt.Color;
import java.util.Vector;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MessageBanner extends Applet implements Runnable
{
    Image offScrImage;
    Graphics offScrGC;
    String target;
    String titleFont;
    String descFont;
    URL fButton;
    URL bButton;
    URL gImage;
    URL otherLink;
    int fButtonX;
    int fButtonY;
    int bButtonX;
    int bButtonY;
    int gImageX;
    int gImageY;
    int titleX;
    int titleY;
    int titleWidth;
    int titleHeight;
    int descX;
    int descY;
    int descWidth;
    int descHeight;
    int cImageX;
    int cImageY;
    int cImageX2;
    int cImageY2;
    int titleSize;
    int descSize;
    Vector Vect;
    Vector Vect2;
    Image fButtonImage;
    Image bButtonImage;
    Image gImageImage;
    Image[] imageSpace;
    Image[] imageSpace2;
    Vector titleSpace;
    Vector descSpace;
    Thread killme;
    int infoPos;
    int oldInfoPos;
    int counter;
    Image titleImage;
    Image descImage;
    Vector href;
    boolean autoRun;
    boolean frameOn;
    Color titleColor;
    Color descColor;
    Color colorT;
    Color colorD;
    Hashtable colorValue;
    Hashtable fontStyle;
    int titleFontHeight;
    int descFontHeight;
    int changeText;
    int speed;
    int titleStyle;
    int descStyle;
    FontMetrics tfm;
    FontMetrics dfm;
    Font tFont;
    Font dFont;
    Font normalFont;
    
    public MessageBanner() {
        this.frameOn = false;
        this.speed = 100;
    }
    
    public void init() {
        this.href = new Vector();
        this.autoRun = true;
        this.setBackground(Color.white);
        (this.colorValue = new Hashtable()).put("black", Color.black);
        this.colorValue.put("blue", Color.blue);
        this.colorValue.put("cyan", Color.cyan);
        this.colorValue.put("darkGray", Color.darkGray);
        this.colorValue.put("gray", Color.gray);
        this.colorValue.put("green", Color.green);
        this.colorValue.put("lightGray", Color.lightGray);
        this.colorValue.put("orange", Color.orange);
        this.colorValue.put("pink", Color.pink);
        this.colorValue.put("red", Color.red);
        this.colorValue.put("yellow", Color.yellow);
        this.colorValue.put("white", Color.white);
        this.colorValue.put("magenta", Color.magenta);
        (this.fontStyle = new Hashtable()).put("0", new Integer(0));
        this.fontStyle.put("1", new Integer(1));
        this.fontStyle.put("2", new Integer(2));
        this.fontStyle.put("3", new Integer(3));
        this.offScrImage = this.createImage(this.size().width, this.size().height);
        this.offScrGC = this.offScrImage.getGraphics();
        final String parameter = this.getParameter("target");
        this.target = parameter;
        if (parameter == null) {
            this.target = "_self";
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("SPEED")) != null) {
            this.speed = Math.round(1000 / new Integer(parameter2));
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("FRAME")) != null && parameter3.toUpperCase().equals("ON")) {
            this.frameOn = true;
        }
        try {
            final String parameter4;
            if ((parameter4 = this.getParameter("FBUTTON")) == null) {
                this.fButton = null;
            }
            else {
                this.fButton = (parameter4.toLowerCase().startsWith("http") ? new URL(parameter4) : new URL(this.getDocumentBase(), parameter4));
            }
            final String parameter5;
            if ((parameter5 = this.getParameter("BBUTTON")) == null) {
                this.bButton = null;
            }
            else {
                this.bButton = (parameter5.toLowerCase().startsWith("http") ? new URL(parameter5) : new URL(this.getDocumentBase(), parameter5));
            }
            final String parameter6;
            if ((parameter6 = this.getParameter("GImage")) == null) {
                this.gImage = null;
            }
            else {
                this.gImage = (parameter6.toLowerCase().startsWith("http") ? new URL(parameter6) : new URL(this.getDocumentBase(), parameter6));
            }
            final String parameter7;
            if ((parameter7 = this.getParameter("otherLink")) != null) {
                this.otherLink = new URL(parameter7);
            }
        }
        catch (MalformedURLException ex) {}
        final String parameter8;
        if ((parameter8 = this.getParameter("Title.color")) == null) {
            this.titleColor = new Color(0, 0, 0);
        }
        else {
            this.titleColor = (Color)this.colorValue.get(parameter8.toLowerCase());
        }
        final String parameter9;
        if ((parameter9 = this.getParameter("desc.color")) == null) {
            this.descColor = new Color(0, 0, 0);
        }
        else {
            this.descColor = (Color)this.colorValue.get(parameter9.toLowerCase());
        }
        final String parameter10 = this.getParameter("desc.font");
        this.descFont = parameter10;
        if (parameter10 == null) {
            this.descFont = "TimesRoman";
        }
        final String parameter11;
        if ((parameter11 = this.getParameter("desc.size")) == null) {
            this.descSize = 12;
        }
        else {
            this.descSize = Integer.parseInt(parameter11);
        }
        final String parameter12;
        if ((parameter12 = this.getParameter("desc.style")) == null) {
            this.descStyle = 0;
        }
        else {
            this.descStyle = (int)this.fontStyle.get(parameter12);
        }
        final String parameter13;
        if ((parameter13 = this.getParameter("desc.X")) == null) {
            this.descX = 0;
        }
        else {
            this.descX = Integer.parseInt(parameter13);
        }
        final String parameter14;
        if ((parameter14 = this.getParameter("desc.Y")) == null) {
            this.descY = 0;
        }
        else {
            this.descY = Integer.parseInt(parameter14);
        }
        final String parameter15;
        if ((parameter15 = this.getParameter("GIMAGE.X")) == null) {
            this.gImageX = 0;
        }
        else {
            this.gImageX = Integer.parseInt(parameter15);
        }
        final String parameter16;
        if ((parameter16 = this.getParameter("GIMAGE.Y")) == null) {
            this.gImageY = 0;
        }
        else {
            this.gImageY = Integer.parseInt(parameter16);
        }
        final String parameter17;
        if ((parameter17 = this.getParameter("BBUTTON.X")) == null) {
            this.bButtonX = 0;
        }
        else {
            this.bButtonX = Integer.parseInt(parameter17);
        }
        final String parameter18;
        if ((parameter18 = this.getParameter("BBUTTON.Y")) == null) {
            this.bButtonY = 0;
        }
        else {
            this.bButtonY = Integer.parseInt(parameter18);
        }
        final String parameter19;
        if ((parameter19 = this.getParameter("FBUTTON.X")) == null) {
            this.fButtonX = 50;
        }
        else {
            this.fButtonX = Integer.parseInt(parameter19);
        }
        final String parameter20;
        if ((parameter20 = this.getParameter("FBUTTON.y")) == null) {
            this.fButtonY = 50;
        }
        else {
            this.fButtonY = Integer.parseInt(parameter20);
        }
        final String parameter21 = this.getParameter("title.font");
        this.titleFont = parameter21;
        if (parameter21 == null) {
            this.titleFont = "TimesRoman";
        }
        final String parameter22;
        if ((parameter22 = this.getParameter("title.size")) == null) {
            this.titleSize = 12;
        }
        else {
            this.titleSize = Integer.parseInt(parameter22);
        }
        final String parameter23;
        if ((parameter23 = this.getParameter("title.style")) == null) {
            this.titleStyle = 0;
        }
        else {
            this.titleStyle = (int)this.fontStyle.get(parameter23);
        }
        final String parameter24;
        if ((parameter24 = this.getParameter("title.x")) == null) {
            this.titleX = 0;
        }
        else {
            this.titleX = Integer.parseInt(parameter24);
        }
        final String parameter25;
        if ((parameter25 = this.getParameter("title.y")) == null) {
            this.titleY = 0;
        }
        else {
            this.titleY = Integer.parseInt(parameter25);
        }
        final MediaTracker mediaTracker = new MediaTracker(this);
        if (this.fButton != null) {
            mediaTracker.addImage(this.fButtonImage = this.getImage(this.fButton), 0);
        }
        if (this.bButton != null) {
            mediaTracker.addImage(this.bButtonImage = this.getImage(this.bButton), 1);
        }
        if (this.gImage != null) {
            mediaTracker.addImage(this.gImageImage = this.getImage(this.gImage), 2);
        }
        this.tFont = new Font(this.titleFont, this.titleStyle, this.titleSize);
        this.dFont = new Font(this.descFont, this.descStyle, this.descSize);
        this.normalFont = new Font("TimesRoman", 0, 10);
        this.tfm = this.offScrGC.getFontMetrics(this.tFont);
        this.dfm = this.offScrGC.getFontMetrics(this.dFont);
        this.titleFontHeight = this.tfm.getHeight();
        this.descFontHeight = this.dfm.getHeight();
        this.titleWidth = 0;
        this.titleHeight = this.descFontHeight;
        this.descWidth = 0;
        this.descHeight = 0;
        this.titleSpace = new Vector();
        this.descSpace = new Vector();
        final Vector vector = new Vector<String>();
        int n = 1;
        boolean b = false;
        try {
            while (!b) {
                final String parameter26 = this.getParameter("href" + n);
                if (parameter26 == null && n == 1) {
                    if (n == 1) {
                        this.href.addElement(new URL("http://www.vivaorange.com/"));
                    }
                    b = true;
                }
                else if (parameter26 == null) {
                    b = true;
                }
                else {
                    this.href.addElement(new URL(parameter26));
                    ++n;
                }
            }
        }
        catch (MalformedURLException ex2) {}
        int n2 = 1;
        int n3 = 1;
        boolean b2 = false;
        while (!b2) {
            final String parameter27 = this.getParameter("desc" + n2 + "." + n3);
            if (parameter27 == null && n3 == 1) {
                b2 = true;
            }
            else if (parameter27 == null) {
                this.descSpace.addElement(vector.clone());
                vector.removeAllElements();
                if (this.descHeight < n3 * (this.descFontHeight + this.descFontHeight / 3) + 14) {
                    this.descHeight = n3 * (this.descFontHeight + this.descFontHeight / 3) + 14;
                }
                n3 = 1;
                ++n2;
            }
            else {
                vector.addElement(parameter27);
                if (this.descWidth < this.dfm.stringWidth(parameter27)) {
                    this.descWidth = this.dfm.stringWidth(parameter27);
                }
                ++n3;
            }
        }
        int n4 = 1;
        int n5 = 1;
        boolean b3 = false;
        while (!b3) {
            final String parameter28 = this.getParameter("Title" + n4 + "." + n5);
            if (parameter28 == null && n5 == 1) {
                if (n4 == 1) {
                    vector.addElement("");
                    this.titleSpace.addElement(vector);
                }
                b3 = true;
                this.killme = new Thread(this);
            }
            else if (parameter28 == null) {
                this.titleSpace.addElement(vector.clone());
                vector.removeAllElements();
                if (this.titleHeight < n5 * (this.titleFontHeight + this.titleFontHeight / 3) + 14) {
                    this.titleHeight = n5 * (this.titleFontHeight + this.titleFontHeight / 3) + 14;
                }
                n5 = 1;
                ++n4;
            }
            else {
                vector.addElement(parameter28);
                if (this.titleWidth < this.tfm.stringWidth(parameter28)) {
                    this.titleWidth = this.tfm.stringWidth(parameter28);
                }
                ++n5;
            }
        }
        final String parameter29;
        if ((parameter29 = this.getParameter("cImage.X")) == null) {
            this.cImageX = -100;
        }
        else {
            this.cImageX = Integer.parseInt(parameter29);
        }
        final String parameter30;
        if ((parameter30 = this.getParameter("cImage.Y")) == null) {
            this.cImageY = -100;
        }
        else {
            this.cImageY = Integer.parseInt(parameter30);
        }
        final String parameter31;
        if ((parameter31 = this.getParameter("c2Image.X")) == null) {
            this.cImageX2 = -100;
        }
        else {
            this.cImageX2 = Integer.parseInt(parameter31);
        }
        final String parameter32;
        if ((parameter32 = this.getParameter("c2Image.Y")) == null) {
            this.cImageY2 = -100;
        }
        else {
            this.cImageY2 = Integer.parseInt(parameter32);
        }
        this.imageSpace = new Image[this.titleSpace.size()];
        this.imageSpace2 = new Image[this.titleSpace.size()];
        if (this.cImageX >= 0 && this.cImageY >= 0) {
            for (int i = 0; i < this.imageSpace.length; ++i) {
                final String parameter33;
                if ((parameter33 = this.getParameter("cImage" + (i + 1))) == null) {
                    this.imageSpace[i] = null;
                }
                else {
                    try {
                        mediaTracker.addImage(this.imageSpace[i] = this.getImage(parameter33.toLowerCase().startsWith("http") ? new URL(parameter33) : new URL(this.getDocumentBase(), parameter33)), 3);
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (this.cImageX2 >= 0 && this.cImageY2 >= 0) {
            for (int j = 0; j < this.imageSpace.length; ++j) {
                final String parameter34;
                if ((parameter34 = this.getParameter("c2Image" + (j + 1))) == null) {
                    this.imageSpace2[j] = null;
                }
                else {
                    try {
                        mediaTracker.addImage(this.imageSpace2[j] = this.getImage(parameter34.toLowerCase().startsWith("http") ? new URL(parameter34) : new URL(this.getDocumentBase(), parameter34)), 4);
                    }
                    catch (Exception ex4) {}
                }
            }
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex5) {}
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        Container container;
        for (container = this.getParent(); !(container instanceof Frame) && container != null; container = ((Frame)container).getParent()) {}
        if (this.bButton != null && n >= this.bButtonX && n <= this.bButtonX + this.bButtonImage.getWidth(this) && n2 >= this.bButtonY && n2 <= this.bButtonY + this.bButtonImage.getHeight(this)) {
            if (container != null) {
                ((Frame)container).setCursor(12);
            }
            this.repaint();
        }
        else if (this.fButton != null && n >= this.fButtonX && n <= this.fButtonX + this.fButtonImage.getWidth(this) && n2 >= this.fButtonY && n2 <= this.fButtonY + this.fButtonImage.getHeight(this)) {
            if (container != null) {
                ((Frame)container).setCursor(12);
            }
            this.repaint();
        }
        else if ((n >= this.titleX && n <= this.titleX + this.titleWidth && n2 >= this.titleY && n2 <= this.titleY + this.titleHeight) || (n >= this.descX && n <= this.descX + this.descWidth && n2 >= this.descY && n2 <= this.descY + this.descHeight)) {
            if (container != null) {
                ((Frame)container).setCursor(12);
            }
            this.repaint();
        }
        else {
            if (container != null) {
                ((Frame)container).setCursor(0);
            }
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        Label_0341: {
            if (n < this.titleX || n > this.titleX + this.titleWidth || n2 < this.titleY || n2 > this.titleY + this.titleHeight) {
                if (n < this.descX || n > this.descX + this.descWidth || n2 < this.descY || n2 > this.descY + this.descHeight) {
                    if (this.bButton != null && n >= this.bButtonX && n <= this.bButtonX + this.bButtonImage.getWidth(this) && n2 >= this.bButtonY && n2 <= this.bButtonY + this.bButtonImage.getHeight(this)) {
                        --this.infoPos;
                        if (this.infoPos < 0) {
                            this.infoPos = this.titleSpace.size() - 1;
                        }
                        this.repaint();
                        break Label_0341;
                    }
                    if (this.fButton != null && n >= this.fButtonX && n <= this.fButtonX + this.fButtonImage.getWidth(this) && n2 >= this.fButtonY && n2 <= this.fButtonY + this.fButtonImage.getHeight(this)) {
                        if (this.infoPos >= this.titleSpace.size() - 1) {
                            this.infoPos = 0;
                        }
                        else {
                            ++this.infoPos;
                        }
                        this.repaint();
                        break Label_0341;
                    }
                    if (this.otherLink != null) {
                        try {
                            this.getAppletContext().showDocument(this.otherLink, this.target);
                        }
                        catch (ArrayIndexOutOfBoundsException ex) {}
                    }
                    break Label_0341;
                }
            }
            try {
                this.getAppletContext().showDocument(this.href.elementAt(this.infoPos), this.target);
            }
            catch (ArrayIndexOutOfBoundsException ex2) {}
        }
        this.autoRun = false;
        return true;
    }
    
    public void paint(final Graphics graphics) {
        (this.offScrGC = this.offScrImage.getGraphics()).setColor(Color.white);
        this.offScrGC.fillRect(0, 0, this.size().width, this.size().height);
        if (this.gImage != null) {
            this.offScrGC.drawImage(this.gImageImage, this.gImageX, this.gImageY, this);
        }
        if (this.imageSpace[this.infoPos] != null) {
            this.offScrGC.drawImage(this.imageSpace[this.infoPos], this.cImageX, this.cImageY, this);
        }
        if (this.imageSpace2[this.infoPos] != null) {
            this.offScrGC.drawImage(this.imageSpace2[this.infoPos], this.cImageX2, this.cImageY2, this);
        }
        if (this.fButton != null) {
            this.offScrGC.drawImage(this.fButtonImage, this.fButtonX, this.fButtonY, this);
        }
        if (this.bButton != null) {
            this.offScrGC.drawImage(this.bButtonImage, this.bButtonX, this.bButtonY, this);
        }
        if (this.counter > 30 && this.autoRun) {
            if (this.infoPos >= this.titleSpace.size() - 1) {
                this.infoPos = 0;
            }
            else {
                ++this.infoPos;
            }
            this.counter = 0;
        }
        if (this.infoPos != this.oldInfoPos) {
            this.oldInfoPos = this.infoPos;
            this.counter = 0;
        }
        this.Vect = this.titleSpace.elementAt(this.infoPos);
        try {
            this.Vect2 = this.descSpace.elementAt(this.infoPos);
        }
        catch (NullPointerException ex) {}
        catch (ArrayIndexOutOfBoundsException ex2) {}
        if (this.autoRun) {
            if (this.counter <= 15) {
                this.colorT = new Color(this.titleColor.getRed() + (255 - this.titleColor.getRed()) / 15 * (15 - this.counter), this.titleColor.getGreen() + (255 - this.titleColor.getGreen()) / 15 * (15 - this.counter), this.titleColor.getGreen() + (255 - this.titleColor.getBlue()) / 15 * (15 - this.counter));
                this.colorD = new Color(this.descColor.getRed() + (255 - this.descColor.getRed()) / 15 * (15 - this.counter), this.descColor.getGreen() + (255 - this.descColor.getGreen()) / 15 * (15 - this.counter), this.descColor.getGreen() + (255 - this.descColor.getBlue()) / 15 * (15 - this.counter));
            }
            else {
                if (this.counter == 16) {
                    try {
                        Thread.sleep(600L);
                    }
                    catch (InterruptedException ex3) {}
                }
                this.colorT = new Color(this.titleColor.getRed() + (255 - this.titleColor.getRed()) / 15 * (this.counter - 15), this.titleColor.getGreen() + (255 - this.titleColor.getGreen()) / 15 * (this.counter - 15), this.titleColor.getBlue() + (255 - this.titleColor.getBlue()) / 15 * (this.counter - 15));
                this.colorT = new Color(this.descColor.getRed() + (255 - this.descColor.getRed()) / 15 * (this.counter - 15), this.descColor.getGreen() + (255 - this.descColor.getGreen()) / 15 * (this.counter - 15), this.descColor.getBlue() + (255 - this.descColor.getBlue()) / 15 * (this.counter - 15));
            }
            ++this.counter;
        }
        else {
            if (this.changeText++ > 300) {
                this.changeText = 0;
                this.autoRun = true;
            }
            this.colorT = this.titleColor;
            this.colorD = this.descColor;
        }
        for (int i = 0; i < this.Vect.size(); ++i) {
            this.offScrGC.setColor(this.colorT);
            this.offScrGC.setFont(this.tFont);
            this.offScrGC.drawString((String)this.Vect.elementAt(i), this.titleX, this.titleY + i * (this.titleFontHeight + this.titleFontHeight / 3) + 14);
        }
        try {
            for (int j = 0; j < this.Vect2.size(); ++j) {
                this.offScrGC.setColor(this.colorD);
                this.offScrGC.setFont(this.dFont);
                this.offScrGC.drawString((String)this.Vect2.elementAt(j), this.descX, this.descY + j * (this.descFontHeight + this.descFontHeight / 3) + 14);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex4) {}
        catch (NullPointerException ex5) {}
        this.offScrGC.setColor(Color.blue);
        this.offScrGC.setFont(this.normalFont);
        this.offScrGC.drawString("www.vivaorange.com", this.size().width - 120, this.size().height - 9);
        graphics.drawImage(this.offScrImage, 0, 0, this);
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.speed);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void start() {
        if (this.killme != null) {
            this.killme.start();
        }
    }
    
    public void stop() {
        if (this.killme != null) {
            this.killme.stop();
        }
        this.killme = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
