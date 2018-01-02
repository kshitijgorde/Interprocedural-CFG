import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.applet.AppletContext;
import java.net.URL;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Polygon;
import java.awt.Font;
import java.applet.AudioClip;
import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NetMenu extends Applet
{
    Image firstLevelImg;
    Image bottomImg;
    Image currExpandedItemImg;
    Image currItemImg;
    Image bbuf;
    Graphics bbufG;
    int width;
    int height;
    Color bgColor;
    Color loStrColor;
    Color hiStrColor;
    Color level0Color;
    Color subBgColor;
    Vector item;
    Vector level;
    Vector msg;
    Vector currItems;
    int selectedIndex;
    int selected;
    int expanded;
    AudioClip ac1;
    AudioClip ac2;
    AudioClip ac3;
    Font f1;
    Font f2;
    int beginX;
    int totalLevel1Num;
    Polygon arrowP;
    Vector firstLevelIdxes;
    Vector bottomImgIdxes;
    
    public NetMenu() {
        this.height = 20;
        this.bgColor = Color.black;
        this.loStrColor = Color.white;
        this.hiStrColor = Color.black;
        this.level0Color = new Color(255, 128, 64);
        this.subBgColor = new Color(255, 176, 138);
        this.item = new Vector();
        this.level = new Vector();
        this.msg = new Vector();
        this.selectedIndex = -1;
        this.selected = -1;
        this.expanded = -1;
    }
    
    public void init() {
        int n = 0;
        try {
            this.bgColor = new Color(Integer.parseInt(this.getParameter("bgColor"), 16));
        }
        catch (Exception ex) {}
        try {
            this.level0Color = new Color(Integer.parseInt(this.getParameter("topLevelColor"), 16));
        }
        catch (Exception ex2) {}
        try {
            this.subBgColor = new Color(Integer.parseInt(this.getParameter("secondLevelColor"), 16));
        }
        catch (Exception ex3) {}
        try {
            this.loStrColor = new Color(Integer.parseInt(this.getParameter("strColor"), 16));
        }
        catch (Exception ex4) {}
        try {
            this.hiStrColor = new Color(Integer.parseInt(this.getParameter("strHiColor"), 16));
        }
        catch (Exception ex5) {}
        try {
            this.height = Integer.parseInt(this.getParameter("itemHeight"));
        }
        catch (Exception ex6) {}
        String parameter = null;
        try {
            parameter = this.getParameter("font");
        }
        catch (Exception ex7) {}
        if (parameter == null) {
            parameter = "Times New Roman";
        }
        this.f1 = new Font(parameter, 3, (int)(this.height * 0.7000000000000001));
        this.f2 = new Font(parameter, 0, (int)(this.height * 0.7000000000000001));
        while (true) {
            final String parameter2 = this.getParameter("menu" + n);
            if (parameter2 == null) {
                break;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, "|", false);
            this.item.addElement(stringTokenizer.nextToken());
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("*")) {
                this.msg.addElement(nextToken);
            }
            else {
                this.msg.addElement(this.getParameter(nextToken));
            }
            this.level.addElement(new Integer(0));
            if (stringTokenizer.hasMoreTokens()) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), ",", false);
                final StringTokenizer stringTokenizer3 = new StringTokenizer(stringTokenizer.nextToken(), ",", false);
                while (stringTokenizer2.hasMoreTokens()) {
                    this.item.addElement(stringTokenizer2.nextToken());
                    this.msg.addElement(this.getParameter(stringTokenizer3.nextToken()));
                    this.level.addElement(new Integer(1));
                }
            }
            ++n;
        }
        this.currItems = new Vector();
        for (int i = 0; i < this.item.size(); ++i) {
            if ((int)this.level.elementAt(i) == 0) {
                this.currItems.addElement(new Integer(i));
            }
        }
        this.setBackground(this.bgColor);
        this.ac1 = this.getAudioClip(this.getCodeBase(), "expand.au");
        this.ac2 = this.getAudioClip(this.getCodeBase(), "shrink.au");
        this.ac3 = this.getAudioClip(this.getCodeBase(), "ding.au");
        this.width = this.size().width;
        this.firstLevelIdxes = this.currItems;
        this.firstLevelImg = this.renderImg(this.firstLevelIdxes, -1, -1);
        this.expanded = this.currItems.size();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n2 >= this.currItems.size() * this.height) {
            return true;
        }
        final int n3 = n2 / this.height;
        final int intValue = this.currItems.elementAt(n3);
        final AppletContext appletContext = this.getAppletContext();
        try {
            final String s = this.msg.elementAt(intValue);
            final String substring = s.substring(s.indexOf(124) + 1);
            URL url;
            if (!substring.startsWith("http://") && !substring.startsWith("mailto:")) {
                url = new URL(String.valueOf(this.getCodeBase()) + substring);
            }
            else {
                url = new URL(substring);
            }
            appletContext.showDocument(url, s.substring(0, s.indexOf(124)));
            this.ac3.play();
        }
        catch (Exception ex) {}
        if (n3 == this.expanded) {
            this.beginX = 0;
            for (int i = 0; i < this.totalLevel1Num; ++i) {
                ++this.beginX;
                this.paint(this.getGraphics());
                this.ac2.play();
                if (i != this.totalLevel1Num - 1) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex2) {
                        break;
                    }
                }
            }
            this.currExpandedItemImg = null;
            this.bottomImg = null;
            this.currItems = this.firstLevelIdxes;
            this.expanded = this.currItems.size();
            this.totalLevel1Num = 0;
            this.bottomImgIdxes = null;
            this.currItemImg = this.renderItemImg(new Integer(intValue), 0, -1);
        }
        else if ((int)this.level.elementAt(intValue) == 1) {
            this.selectedIndex = intValue;
            this.currItemImg = this.renderItemImg(new Integer(intValue), 0, -1);
            this.bottomImg = this.renderImg(this.bottomImgIdxes, -1, -1);
            this.firstLevelImg = this.renderImg(this.firstLevelIdxes, -1, -1);
        }
        else if (intValue == this.item.size() - 1 || (int)this.level.elementAt(intValue + 1) == 0) {
            this.selectedIndex = intValue;
            if (this.bottomImgIdxes != null) {
                this.bottomImg = this.renderImg(this.bottomImgIdxes, -1, -1);
            }
            this.currItemImg = this.renderItemImg(new Integer(intValue), 0, -1);
            this.firstLevelImg = this.renderImg(this.firstLevelIdxes, -1, -1);
        }
        else {
            if (this.selected >= this.expanded) {
                this.currItemImg = null;
            }
            this.beginX = 0;
            for (int j = 0; j < this.totalLevel1Num; ++j) {
                ++this.beginX;
                this.paint(this.getGraphics());
                this.ac2.play();
                if (j != this.totalLevel1Num - 1) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex3) {
                        break;
                    }
                }
            }
            this.currItems = new Vector();
            this.bottomImgIdxes = new Vector();
            int k;
            for (k = 0; k <= intValue; ++k) {
                if ((int)this.level.elementAt(k) == 0) {
                    this.currItems.addElement(new Integer(k));
                }
            }
            final Vector<Object> vector = new Vector<Object>();
            vector.addElement(this.currItems.lastElement());
            this.currExpandedItemImg = this.renderImg(vector, -1, 0);
            this.expanded = this.currItems.size() - 1;
            this.totalLevel1Num = 0;
            while (k < this.item.size()) {
                if ((int)this.level.elementAt(k) != 1) {
                    break;
                }
                this.currItems.addElement(new Integer(k));
                this.bottomImgIdxes.addElement(new Integer(k));
                ++k;
                ++this.totalLevel1Num;
            }
            while (k < this.item.size()) {
                if ((int)this.level.elementAt(k) == 0) {
                    this.currItems.addElement(new Integer(k));
                    this.bottomImgIdxes.addElement(new Integer(k));
                }
                ++k;
            }
            this.bottomImg = this.renderImg(this.bottomImgIdxes, -1, -1);
            this.beginX = this.totalLevel1Num;
            for (int l = 0; l < this.totalLevel1Num; ++l) {
                --this.beginX;
                this.paint(this.getGraphics());
                this.ac1.play();
                if (l != this.totalLevel1Num - 1) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex4) {
                        break;
                    }
                }
            }
            if (n3 < this.currItems.size()) {
                this.currItemImg = this.renderItemImg(new Integer((int)this.currItems.elementAt(n3)), 0, 0);
            }
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.currItemImg = null;
        this.selected = -1;
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (n2 >= this.currItems.size() * this.height) {
            this.currItemImg = null;
            this.selected = -1;
            this.repaint();
            return true;
        }
        if (this.selected == n2 / this.height) {
            return true;
        }
        this.selected = n2 / this.height;
        int n3 = -1;
        if (this.selected == this.expanded) {
            n3 = 0;
        }
        this.currItemImg = this.renderItemImg(this.currItems.elementAt(this.selected), 0, n3);
        this.repaint();
        System.gc();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        if (this.bbuf == null) {
            this.bbuf = this.createImage(width, height);
            this.bbufG = this.bbuf.getGraphics();
        }
        this.bbufG.setClip(0, 0, width, this.expanded * this.height);
        this.bbufG.drawImage(this.firstLevelImg, 0, 0, this);
        if (this.currExpandedItemImg != null) {
            this.bbufG.setClip(0, this.expanded * this.height, width, this.height);
            this.bbufG.drawImage(this.currExpandedItemImg, 0, this.expanded * this.height, this);
        }
        if (this.bottomImg != null) {
            this.bbufG.setClip(0, (this.expanded + 1) * this.height, width, height - (this.expanded + 1) * this.height);
            this.bbufG.drawImage(this.bottomImg, 0, (this.expanded + 1) * this.height - this.beginX * this.height, this);
            this.bbufG.setColor(this.getBackground());
            final int n = (this.expanded + 1) * this.height - this.beginX * this.height + this.bottomImg.getHeight(this);
            this.bbufG.fillRect(0, n, width, height - n);
        }
        if (this.currItemImg != null) {
            this.bbufG.setClip(0, this.selected * this.height, width, this.height);
            this.bbufG.drawImage(this.currItemImg, 0, this.selected * this.height, this);
        }
        graphics.drawImage(this.bbuf, 0, 0, this);
    }
    
    private Image renderImg(final Vector vector, final int n, final int n2) {
        final Image image = this.createImage(this.size().width, vector.size() * this.height);
        final Graphics graphics = image.getGraphics();
        int n3 = 0;
        for (int i = 0; i < vector.size(); ++i) {
            final int intValue = vector.elementAt(i);
            int n4 = 0;
            if ((int)this.level.elementAt(intValue) == 0) {
                graphics.setColor(this.level0Color);
                boolean b = true;
                if (this.selectedIndex == intValue) {
                    b = false;
                }
                graphics.fill3DRect(0, n3, this.width, this.height, b);
                if (intValue + 1 < this.level.size() && (int)this.level.elementAt(intValue + 1) == 1) {
                    graphics.setColor(Color.green);
                    if (this.arrowP == null) {
                        (this.arrowP = new Polygon()).addPoint(0, 0);
                        this.arrowP.addPoint(0, 0);
                        this.arrowP.addPoint(0, 0);
                    }
                    if (n2 != i) {
                        this.arrowP.xpoints[0] = this.width - 8;
                        this.arrowP.ypoints[0] = n3 + 5;
                        this.arrowP.xpoints[1] = this.width - 2;
                        this.arrowP.ypoints[1] = n3 + 5;
                        this.arrowP.xpoints[2] = this.width - 5;
                        this.arrowP.ypoints[2] = n3 + this.height - 5;
                        graphics.fillPolygon(this.arrowP);
                    }
                    else {
                        this.arrowP.xpoints[0] = this.width - 8;
                        this.arrowP.ypoints[0] = n3 + this.height - 5;
                        this.arrowP.xpoints[1] = this.width - 2;
                        this.arrowP.ypoints[1] = n3 + this.height - 5;
                        this.arrowP.xpoints[2] = this.width - 5;
                        this.arrowP.ypoints[2] = n3 + 5;
                        graphics.fillPolygon(this.arrowP);
                    }
                }
            }
            else {
                n4 = 6;
                graphics.setColor(this.subBgColor);
                boolean b2 = true;
                if (this.selectedIndex == vector.elementAt(i)) {
                    b2 = false;
                }
                graphics.fill3DRect(n4, n3, this.size().width - 2 * n4, this.height, b2);
            }
            Font font;
            if (i == n) {
                graphics.setColor(this.hiStrColor);
                font = this.f1;
            }
            else {
                graphics.setColor(this.loStrColor);
                font = this.f2;
            }
            graphics.setFont(font);
            final FontMetrics fontMetrics = this.getFontMetrics(font);
            graphics.drawString(this.item.elementAt(intValue), n4 + 10, n3 + this.height / 2 + fontMetrics.getHeight() / 2 - fontMetrics.getMaxDescent());
            n3 += this.height;
        }
        return image;
    }
    
    private Image renderItemImg(final Integer n, final int n2, final int n3) {
        final Vector<Integer> vector = new Vector<Integer>();
        vector.addElement(n);
        return this.renderImg(vector, n2, n3);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
