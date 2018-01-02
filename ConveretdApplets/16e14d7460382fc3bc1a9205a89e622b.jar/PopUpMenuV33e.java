import java.util.Date;
import java.awt.Event;
import java.awt.Polygon;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PopUpMenuV33e extends Applet
{
    Image mapImage;
    int areas;
    Color fgColor;
    Color darkerFgColor;
    Color bgDarker;
    Color bgBrighter;
    Color bgColor;
    int bdSize;
    Color bdColor;
    boolean hlFlag;
    boolean invertFlag;
    boolean overAd;
    Color hlColor;
    String fontName;
    int fontStyle;
    int fontSize;
    int hrznMargin;
    int vertMargin;
    int adMargin;
    int pop;
    int mouseX;
    int mouseY;
    String target;
    String code;
    boolean registered;
    char c;
    Area[] area;
    int activeArea;
    int activeItem;
    Font font;
    int fontAdvance;
    int fontAscent;
    int fontHeight;
    Point center;
    Dimension offDimension;
    Dimension size;
    Image offImage;
    Graphics offGraphics;
    
    public String getAppletInfo() {
        return "PopUpMenu version 3.3e\r\n\r\nCopyright 1997-98 dZiners.com\r\nWritten by Igal Sapir, www.dZiners.com";
    }
    
    public void init() {
        this.size = this.size();
        this.center = new Point(this.size.width / 2, this.size.height / 2);
        this.registered = this.isRegistered();
        if (this.registered) {
            this.adMargin = 0;
        }
        else {
            this.adMargin = 10;
        }
        try {
            final String parameter = this.getParameter("mapimage");
            if (parameter != null) {
                this.prepareImage(this.mapImage = this.getImage(new URL(this.getDocumentBase(), parameter)), this);
            }
        }
        catch (Exception ex) {}
        final String parameter2;
        if ((parameter2 = this.getParameter("paper")) != null) {
            this.bgColor = this.parseColor(parameter2);
        }
        this.bgDarker = this.bgColor.darker();
        this.bgDarker = this.bgDarker.darker();
        this.bgBrighter = this.bgColor.brighter();
        this.bgBrighter = this.bgBrighter.brighter();
        final String parameter3;
        if ((parameter3 = this.getParameter("ink")) != null) {
            this.fgColor = this.parseColor(parameter3);
        }
        this.darkerFgColor = this.fgColor.darker();
        this.darkerFgColor = this.darkerFgColor.darker();
        final String parameter4;
        if ((parameter4 = this.getParameter("highlight")) != null) {
            this.hlColor = this.parseColor(parameter4);
        }
        this.hlFlag = true;
        final String parameter5;
        if ((parameter5 = this.getParameter("mark")) != null && parameter5.equalsIgnoreCase("false")) {
            this.hlFlag = false;
        }
        try {
            final String parameter6 = this.getParameter("border");
            if (parameter6 != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter6, ",");
                final int int1;
                if ((int1 = Integer.parseInt(stringTokenizer.nextToken())) > 0) {
                    this.bdSize = int1;
                }
                this.bdColor = this.parseColor(stringTokenizer.nextToken());
            }
        }
        catch (Exception ex2) {}
        while (this.getParameter("area#" + (this.areas + 1)) != null) {
            ++this.areas;
        }
        this.area = new Area[this.areas];
        final String parameter7;
        if ((parameter7 = this.getParameter("target")) != null) {
            this.target = parameter7;
        }
        for (int i = 0; i < this.areas; ++i) {
            this.getShape(i, this.getParameter("area#" + (i + 1)));
            this.area[i].targetFrame = this.target;
            this.area[i].defaultUrl = this.getParameter("url#" + (i + 1));
            this.area[i].defaultStatus = this.getParameter("status#" + (i + 1));
            final String parameter8 = this.getParameter("target#" + (i + 1));
            if (parameter8 != null) {
                this.area[i].targetFrame = parameter8;
            }
            this.area[i].alignment = 1;
            final String parameter9;
            if ((parameter9 = this.getParameter("align")) != null) {
                if (parameter9.equalsIgnoreCase("right")) {
                    this.area[i].alignment = 2;
                }
                else if (parameter9.equalsIgnoreCase("center")) {
                    this.area[i].alignment = 0;
                }
            }
            this.area[i].style = 0;
            final String parameter10;
            if ((parameter10 = this.getParameter("style")) != null) {
                if (parameter10.equalsIgnoreCase("rect")) {
                    this.area[i].style = 1;
                }
                else if (parameter10.equalsIgnoreCase("round")) {
                    this.area[i].style = 2;
                }
            }
            for (int j = 0; j < this.area[i].items; ++j) {
                if (this.area[i].defaultUrl != null) {
                    this.area[i].url[j] = this.area[i].defaultUrl;
                }
                if (this.area[i].defaultStatus != null) {
                    this.area[i].status[j] = this.area[i].defaultStatus;
                }
            }
        }
        for (int k = 0; k < this.areas; ++k) {
            for (int l = 0; l < this.area[k].items; ++l) {
                final String parameter11;
                if ((parameter11 = this.getParameter("item#" + (k + 1) + "-" + (l + 1))) != null) {
                    this.area[k].item[l] = parameter11;
                }
                final String parameter12;
                if ((parameter12 = this.getParameter("url#" + (k + 1) + "-" + (l + 1))) != null) {
                    this.area[k].url[l] = parameter12;
                }
                final String parameter13;
                if ((parameter13 = this.getParameter("status#" + (k + 1) + "-" + (l + 1))) != null) {
                    this.area[k].status[l] = parameter13;
                }
            }
        }
        try {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("font"), ",");
            final String nextToken = stringTokenizer2.nextToken();
            if (nextToken.equalsIgnoreCase("Courier")) {
                this.fontName = "Courier";
            }
            else if (nextToken.equalsIgnoreCase("Dialog")) {
                this.fontName = "Dialog";
            }
            else if (nextToken.equalsIgnoreCase("Helvetica")) {
                this.fontName = "Helvetica";
            }
            else if (nextToken.equalsIgnoreCase("Symbol")) {
                this.fontName = "Symbol";
            }
            else if (nextToken.equalsIgnoreCase("TimesRoman")) {
                this.fontName = "TimesRoman";
            }
            else {
                this.fontName = nextToken;
            }
            final String nextToken2 = stringTokenizer2.nextToken();
            if (nextToken2.equalsIgnoreCase("plain")) {
                this.fontStyle = 0;
            }
            else if (nextToken2.equalsIgnoreCase("bold")) {
                this.fontStyle = 1;
            }
            else if (nextToken2.equalsIgnoreCase("italic")) {
                this.fontStyle = 2;
            }
            else if (nextToken2.equalsIgnoreCase("boldItalic")) {
                this.fontStyle = 3;
            }
            else {
                this.fontStyle = 0;
            }
            final int int2;
            if ((int2 = Integer.parseInt(stringTokenizer2.nextToken())) > 0) {
                this.fontSize = int2;
            }
            else {
                this.fontSize = 11;
            }
        }
        catch (Exception ex3) {}
        final Graphics graphics = this.getGraphics();
        this.font = graphics.getFont();
        graphics.setFont(this.font = new Font(this.fontName, this.fontStyle, this.fontSize));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.fontAdvance = fontMetrics.getMaxAdvance();
        this.fontAscent = fontMetrics.getMaxAscent();
        this.fontHeight = fontMetrics.getHeight();
        for (int n = 0; n < this.areas; ++n) {
            int n2 = 0;
            for (int n3 = 0; n3 < this.area[n].items; ++n3) {
                final int stringWidth = fontMetrics.stringWidth(this.area[n].item[n3]);
                if (stringWidth > n2) {
                    n2 = stringWidth;
                }
            }
            final Rectangle boundingBox = this.area[n].getBoundingBox();
            final int n4 = n2 + 2 * this.hrznMargin;
            final int n5 = this.area[n].items * this.fontHeight + 2 * this.vertMargin;
            int n6;
            if (boundingBox.x + boundingBox.width / 2 < this.center.x) {
                n6 = boundingBox.x + boundingBox.width - this.fontAdvance;
                if (n6 + n4 > this.size.width) {
                    n6 = Math.max(0, this.size.width - n4);
                }
            }
            else {
                n6 = Math.max(0, boundingBox.x + this.fontAdvance - n4);
                if (n6 + n4 > this.size.width) {
                    n6 = Math.max(0, this.size.width - n4);
                }
            }
            int n7;
            if (boundingBox.y + boundingBox.height / 2 < this.center.y) {
                n7 = boundingBox.y + boundingBox.height / 2;
                if (n7 + n5 > this.size.height - this.adMargin) {
                    n7 = Math.max(0, this.size.height - n5 - this.adMargin);
                }
            }
            else {
                n7 = Math.max(0, boundingBox.y + boundingBox.height / 2 - n5);
                if (n7 + n5 > this.size.height - this.adMargin) {
                    n7 = Math.max(0, this.size.height - n5 - this.adMargin);
                }
            }
            final String parameter14;
            if ((parameter14 = this.getParameter("box#" + (n + 1))) != null) {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter14, ",");
                final int int3;
                if ((int3 = Integer.parseInt(stringTokenizer3.nextToken())) > 0) {
                    n6 = int3;
                }
                final int int4;
                if ((int4 = Integer.parseInt(stringTokenizer3.nextToken())) > 0) {
                    n7 = int4;
                }
            }
            this.area[n].popBox = new Rectangle(n6, n7, n4, n5);
        }
    }
    
    private void getShape(final int n, String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final int int1 = Integer.parseInt(stringTokenizer.nextToken());
        final String nextToken = stringTokenizer.nextToken();
        if (nextToken.equalsIgnoreCase("rect")) {
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            final int int3 = Integer.parseInt(stringTokenizer.nextToken());
            this.area[n] = new Area(int1, new Rectangle(int2, int3, Integer.parseInt(stringTokenizer.nextToken()) - int2, Integer.parseInt(stringTokenizer.nextToken()) - int3));
        }
        if (nextToken.equalsIgnoreCase("circ")) {
            this.area[n] = new Area(int1, Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        if (nextToken.equalsIgnoreCase("poly")) {
            final Polygon polygon = new Polygon();
            while (stringTokenizer.hasMoreTokens()) {
                polygon.addPoint(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
            }
            this.area[n] = new Area(int1, polygon);
        }
        if ((s = this.getParameter("image#" + (n + 1))) != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(s, ",");
            final String nextToken2 = stringTokenizer2.nextToken();
            final int int4 = Integer.parseInt(stringTokenizer2.nextToken());
            final int int5 = Integer.parseInt(stringTokenizer2.nextToken());
            try {
                if (nextToken2 != null) {
                    this.prepareImage(this.area[n].image = this.getImage(new URL(this.getDocumentBase(), nextToken2)), this);
                }
            }
            catch (Exception ex) {}
            if (this.area[n].image != null) {
                this.area[n].hasImage = true;
                this.area[n].imageX = int4;
                this.area[n].imageY = int5;
            }
        }
        if ((s = this.getParameter("audio#" + (n + 1))) != null) {
            try {
                this.area[n].audio = this.getAudioClip(new URL(this.getDocumentBase(), s));
            }
            catch (Exception ex2) {}
            if (this.area[n].audio != null) {
                this.area[n].hasAudio = true;
            }
        }
    }
    
    public Color parseColor(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        if (s.equalsIgnoreCase("white")) {
            return Color.white;
        }
        if (s.equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (s.equalsIgnoreCase("lightGray")) {
            return Color.lightGray;
        }
        if (s.equalsIgnoreCase("gray")) {
            return Color.gray;
        }
        if (s.equalsIgnoreCase("darkGray")) {
            return Color.darkGray;
        }
        if (s.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (s.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (s.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (s.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        if (s.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (s.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (s.equalsIgnoreCase("pink")) {
            return Color.pink;
        }
        if (s.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        if (s.equalsIgnoreCase("gold")) {
            return new Color(231, 231, 107);
        }
        Color color;
        if (stringTokenizer.countTokens() == 3) {
            color = new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        else {
            color = new Color(231, 231, 107);
        }
        if (s.length() == 7 && s.charAt(0) == '#') {
            color = new Color(Integer.parseInt(s.substring(1, 3), 16), Integer.parseInt(s.substring(3, 5), 16), Integer.parseInt(s.substring(5, 7), 16));
        }
        return color;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseX = -1;
        this.mouseY = -1;
        this.activeArea = -1;
        this.getAppletContext().showStatus("");
        this.overAd = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        String targetFrame = "";
        if (this.activeArea >= 0) {
            targetFrame = this.area[this.activeArea].targetFrame;
        }
        if (this.mouseY > this.size.height - this.adMargin) {
            URL url;
            try {
                url = new URL(this.getCodeBase(), "http://www.dziners.com/");
            }
            catch (Exception ex) {
                url = null;
            }
            if (url != null) {
                this.getAppletContext().showDocument(url, "_blank");
            }
        }
        if (this.activeArea >= 0) {
            URL url2;
            if (this.activeItem >= 0) {
                try {
                    url2 = new URL(this.getCodeBase(), this.area[this.activeArea].url[this.activeItem]);
                }
                catch (Exception ex2) {
                    url2 = null;
                }
            }
            else {
                try {
                    url2 = new URL(this.getCodeBase(), this.area[this.activeArea].defaultUrl);
                }
                catch (Exception ex3) {
                    url2 = null;
                }
            }
            if (url2 != null) {
                this.getAppletContext().showDocument(url2, targetFrame);
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int mouseX, final int mouseY) {
        boolean b = false;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        final int activeArea = this.activeArea;
        final int activeItem = this.activeItem;
        this.activeArea = -1;
        this.activeItem = -1;
        if (this.mouseY > this.size.height - this.adMargin) {
            this.overAd = true;
        }
        else {
            this.overAd = false;
        }
        if (activeArea >= 0 && this.area[activeArea].popBox.inside(mouseX, mouseY)) {
            b = true;
        }
        if (!b) {
            for (int i = this.areas - 1; i >= 0; --i) {
                if (this.area[i].inside(mouseX, mouseY)) {
                    this.area[i].poped = true;
                    this.activeArea = i;
                    this.activeItem = this.area[i].overItem(mouseX, mouseY, this.fontHeight);
                }
                else {
                    this.area[i].poped = false;
                }
            }
        }
        if (b) {
            this.activeArea = activeArea;
            this.activeItem = this.area[this.activeArea].overItem(mouseX, mouseY, this.fontHeight);
        }
        if (this.activeArea >= 0) {
            if (this.activeArea != activeArea && this.area[this.activeArea].hasAudio) {
                this.area[this.activeArea].audio.play();
            }
            if (this.activeArea != activeArea || this.activeItem != activeItem) {
                if (this.activeItem == -1) {
                    if (this.area[this.activeArea].defaultStatus != null) {
                        this.showStatus(this.area[this.activeArea].defaultStatus);
                    }
                    else if (this.area[this.activeArea].defaultUrl != null) {
                        this.showStatus(this.area[this.activeArea].defaultUrl);
                    }
                }
                else if (this.area[this.activeArea].status[this.activeItem] != null) {
                    this.showStatus(this.area[this.activeArea].status[this.activeItem]);
                }
                else {
                    this.showStatus(this.area[this.activeArea].url[this.activeItem]);
                }
            }
        }
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (this.offGraphics == null || this.size.width != this.offDimension.width || this.size.height != this.offDimension.height) {
            this.offDimension = this.size;
            this.offImage = this.createImage(this.size.width, this.size.height);
            this.offGraphics = this.offImage.getGraphics();
        }
        this.offGraphics.setColor(this.bgColor);
        this.offGraphics.fillRect(0, 0, this.size.width, this.size.height);
        if ((this.checkImage(this.mapImage, this) & 0x20) == 0x20) {
            this.offGraphics.drawImage(this.mapImage, 0, 0, this);
            if (this.activeArea >= 0) {
                if (this.hlFlag) {
                    this.offGraphics.setColor(this.hlColor);
                    if (this.area[this.activeArea].shape == 1) {
                        final Rectangle rect = this.area[this.activeArea].getRect();
                        this.offGraphics.drawRect(rect.x, rect.y, rect.width, rect.height);
                    }
                    else if (this.area[this.activeArea].shape == 2) {
                        final Rectangle circ = this.area[this.activeArea].getCirc();
                        this.offGraphics.drawOval(circ.x, circ.y, circ.width, circ.height);
                    }
                    else if (this.area[this.activeArea].shape == 3) {
                        final Polygon poly = this.area[this.activeArea].getPoly();
                        this.offGraphics.drawPolygon(poly);
                        final int n = poly.npoints - 1;
                        this.offGraphics.drawLine(poly.xpoints[n], poly.ypoints[n], poly.xpoints[0], poly.ypoints[0]);
                    }
                }
                if (this.area[this.activeArea].hasImage) {
                    this.offGraphics.drawImage(this.area[this.activeArea].image, this.area[this.activeArea].imageX, this.area[this.activeArea].imageY, this);
                }
                if (this.area[this.activeArea].items > 0) {
                    if (this.area[this.activeArea].style == 0) {
                        this.offGraphics.setColor(this.bgDarker);
                        this.offGraphics.fillRect(this.area[this.activeArea].popBox.x, this.area[this.activeArea].popBox.y, this.area[this.activeArea].popBox.width, this.area[this.activeArea].popBox.height);
                        this.offGraphics.setColor(this.bgColor);
                        this.offGraphics.fillRect(this.area[this.activeArea].popBox.x, this.area[this.activeArea].popBox.y, this.area[this.activeArea].popBox.width - 2, this.area[this.activeArea].popBox.height - 2);
                        this.offGraphics.setColor(this.bgBrighter);
                        this.offGraphics.drawLine(this.area[this.activeArea].popBox.x, this.area[this.activeArea].popBox.y, this.area[this.activeArea].popBox.x + this.area[this.activeArea].popBox.width, this.area[this.activeArea].popBox.y);
                        this.offGraphics.drawLine(this.area[this.activeArea].popBox.x + 1, this.area[this.activeArea].popBox.y + 1, this.area[this.activeArea].popBox.x + this.area[this.activeArea].popBox.width - 1, this.area[this.activeArea].popBox.y + 1);
                        this.offGraphics.drawLine(this.area[this.activeArea].popBox.x, this.area[this.activeArea].popBox.y, this.area[this.activeArea].popBox.x, this.area[this.activeArea].popBox.y + this.area[this.activeArea].popBox.height);
                        this.offGraphics.drawLine(this.area[this.activeArea].popBox.x + 1, this.area[this.activeArea].popBox.y + 1, this.area[this.activeArea].popBox.x + 1, this.area[this.activeArea].popBox.y + this.area[this.activeArea].popBox.height - 1);
                    }
                    else if (this.area[this.activeArea].style == 1) {
                        this.offGraphics.setColor(this.bdColor);
                        this.offGraphics.fillRect(this.area[this.activeArea].popBox.x, this.area[this.activeArea].popBox.y, this.area[this.activeArea].popBox.width, this.area[this.activeArea].popBox.height);
                        this.offGraphics.setColor(this.bgColor);
                        this.offGraphics.fillRect(this.area[this.activeArea].popBox.x + this.bdSize, this.area[this.activeArea].popBox.y + this.bdSize, this.area[this.activeArea].popBox.width - 2 * this.bdSize, this.area[this.activeArea].popBox.height - 2 * this.bdSize);
                    }
                    else {
                        this.offGraphics.setColor(this.bdColor);
                        this.offGraphics.fillRoundRect(this.area[this.activeArea].popBox.x, this.area[this.activeArea].popBox.y, this.area[this.activeArea].popBox.width, this.area[this.activeArea].popBox.height, this.hrznMargin, this.vertMargin);
                        this.offGraphics.setColor(this.bgColor);
                        this.offGraphics.fillRoundRect(this.area[this.activeArea].popBox.x + this.bdSize, this.area[this.activeArea].popBox.y + this.bdSize, this.area[this.activeArea].popBox.width - 2 * this.bdSize, this.area[this.activeArea].popBox.height - 2 * this.bdSize, this.hrznMargin, this.vertMargin);
                    }
                    this.offGraphics.setFont(this.font);
                    for (int i = 0; i < this.area[this.activeArea].items; ++i) {
                        if (i == this.activeItem) {
                            final int n2 = this.area[this.activeArea].popBox.x + this.bdSize * 2;
                            final int n3 = this.area[this.activeArea].popBox.y + this.vertMargin + (i - 1) * this.fontHeight + this.fontAscent + (this.fontHeight - this.fontAscent);
                            final int n4 = this.area[this.activeArea].popBox.width - this.bdSize * 4;
                            final int fontHeight = this.fontHeight;
                            this.offGraphics.setColor(this.hlColor);
                            this.offGraphics.fillRect(n2, n3, n4, fontHeight);
                            this.offGraphics.setColor(this.bgColor);
                        }
                        else {
                            this.offGraphics.setColor(this.fgColor);
                        }
                        final FontMetrics fontMetrics = graphics.getFontMetrics();
                        final String s = this.area[this.activeArea].item[i];
                        final int stringWidth = fontMetrics.stringWidth(s);
                        int n5 = this.area[this.activeArea].popBox.width - stringWidth - this.hrznMargin / 2;
                        if (n5 >= this.area[this.activeArea].popBox.width - this.bdSize * 2 - this.hrznMargin * 2) {
                            n5 = this.area[this.activeArea].popBox.width - this.hrznMargin * 2 - this.bdSize * 2 + 2;
                        }
                        if (this.area[this.activeArea].alignment == 0) {
                            this.offGraphics.drawString(s, this.area[this.activeArea].popBox.x + (this.hrznMargin / 4 + this.area[this.activeArea].popBox.width / 2 - stringWidth / 2), this.area[this.activeArea].popBox.y + this.vertMargin + i * this.fontHeight + this.fontAscent);
                        }
                        else if (this.area[this.activeArea].alignment == 2) {
                            this.offGraphics.drawString(s, this.area[this.activeArea].popBox.x + n5, this.area[this.activeArea].popBox.y + this.vertMargin + i * this.fontHeight + this.fontAscent);
                        }
                        else {
                            this.offGraphics.drawString(s, this.area[this.activeArea].popBox.x + this.hrznMargin, this.area[this.activeArea].popBox.y + this.vertMargin + i * this.fontHeight + this.fontAscent);
                        }
                    }
                }
            }
            if (!this.registered) {
                this.offGraphics.setFont(new Font("Helvetica", 1, this.adMargin - 1));
                if (this.overAd) {
                    this.offGraphics.setColor(this.bgColor);
                }
                else {
                    this.offGraphics.setColor(this.fgColor);
                }
                this.offGraphics.fillRect(0, this.size.height - this.adMargin, this.size.width, this.adMargin);
                if (!this.overAd) {
                    this.offGraphics.setColor(this.bgColor);
                }
                else {
                    this.offGraphics.setColor(this.fgColor);
                }
                this.offGraphics.drawString("Java applet by dZiners.com", this.center.x - 60, this.size.height - 2);
            }
            graphics.drawImage(this.offImage, 0, 0, this);
            return;
        }
        this.offGraphics.setFont(this.font);
        this.offGraphics.setColor(this.fgColor);
        this.offGraphics.drawString("Downloading image...", this.fontAdvance, this.fontHeight + this.fontAscent);
        if (this.registered) {
            this.offGraphics.drawString("This applet is registered to this site", this.fontAdvance, 2 * this.fontHeight + this.fontAscent);
            return;
        }
        this.offGraphics.drawString("This applet is being distributed for free", this.fontAdvance, 2 * this.fontHeight + this.fontAscent);
        this.offGraphics.drawString("The PopUpMenuV3.3 Java Applet,", this.fontAdvance, 3 * this.fontHeight + this.fontAscent);
        this.offGraphics.drawString("© 1997-98, dZiners.com", this.fontAdvance, 4 * this.fontHeight + this.fontAscent);
        this.offGraphics.drawString("http://www.dZiners.com", this.fontAdvance, 5 * this.fontHeight + this.fontAscent);
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public boolean isRegistered() {
        final Date date = new Date();
        final Date date2 = new Date(99, 5, 31);
        if (!this.getParameter("author").equalsIgnoreCase("Igal Sapir")) {
            return false;
        }
        if (!this.getParameter("copyright").equalsIgnoreCase("dZiners.com")) {
            return false;
        }
        URL url;
        try {
            url = new URL(this.getCodeBase(), "");
        }
        catch (Exception ex) {
            url = null;
        }
        if (url != null) {
            final String upperCase = url.toString().toUpperCase();
            upperCase.charAt(11);
            upperCase.charAt(12);
            upperCase.charAt(13);
            final String parameter = this.getParameter("registration");
            int int1;
            if (parameter != null) {
                int1 = Integer.parseInt(parameter);
            }
            else {
                int1 = 0;
            }
            if (int1 == 505050 && date.before(date2)) {
                return true;
            }
            if (int1 == upperCase.length() * 19999) {
                return true;
            }
            if (upperCase.equalsIgnoreCase("HTTP://WWW.DZINERS.COM/")) {
                return true;
            }
        }
        return false;
    }
    
    public PopUpMenuV33e() {
        this.areas = 1;
        this.fgColor = Color.blue;
        this.bgColor = Color.cyan;
        this.bdSize = 3;
        this.bdColor = Color.blue;
        this.hlFlag = true;
        this.invertFlag = true;
        this.overAd = false;
        this.hlColor = Color.red;
        this.fontName = "Helvetica";
        this.fontStyle = 1;
        this.fontSize = 11;
        this.hrznMargin = 10;
        this.vertMargin = 10;
        this.pop = -1;
        this.mouseX = -1;
        this.mouseY = -1;
        this.target = "_self";
        this.registered = false;
        this.activeArea = -1;
        this.activeItem = -1;
    }
}
