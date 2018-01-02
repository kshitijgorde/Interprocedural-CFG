import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.util.StringTokenizer;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Headline
{
    public String headline;
    public String link;
    public int myWidth;
    public int myPosition;
    public int myYpos;
    public int linePos;
    public int pauseFor;
    private Ticker t;
    private JavaTicker a;
    public int ascent;
    public boolean alive;
    public boolean hover;
    
    public Headline(final String headline, final String link, final Ticker t, final JavaTicker a) {
        this.headline = headline;
        this.link = link;
        this.t = t;
        this.a = a;
        this.ascent = 0;
        this.linePos = -1;
        this.alive = false;
        this.hover = false;
        this.pauseFor = -1;
        this.myPosition = 0;
        try {
            this.myWidth = -1;
            this.myWidth = this.draw(t.dummyg, 0);
        }
        catch (Exception ex) {}
        this.alive = true;
    }
    
    public int getEndPosition() {
        return this.myPosition + this.myWidth;
    }
    
    public int getBYEndPosition() {
        return this.myYpos - this.t.bufferSize * 2;
    }
    
    public int getYEndPosition() {
        return this.myYpos + this.ascent;
    }
    
    public int getYPosition() {
        return this.myYpos;
    }
    
    public boolean hasLink() {
        return !this.link.equals("") && !this.link.equals(" ");
    }
    
    private boolean wasYClicked(final int yClick) {
        return yClick >= this.t.y + this.myYpos + this.t.bufferSize && yClick < this.t.y + this.myYpos + this.ascent + this.t.bufferSize;
    }
    
    private boolean wasXClicked(final int xClick) {
        return xClick >= this.t.x + this.myPosition && xClick < this.t.x + this.t.bufferSize + this.getEndPosition();
    }
    
    public boolean wasClicked(final int xClick, final int yClick) {
        if (this.t.vScrollMode()) {
            return this.alive && this.wasXClicked(xClick) && this.wasYClicked(yClick);
        }
        return this.alive && this.wasXClicked(xClick);
    }
    
    public void killOff() {
        try {
            this.headline = null;
            this.link = null;
        }
        catch (Exception ex) {}
    }
    
    protected void finalize() throws Throwable {
        if (this.headline != null) {
            try {
                this.killOff();
            }
            catch (Exception ex) {}
        }
        super.finalize();
    }
    
    public void reverseShift() {
        this.myPosition += this.t.tickBy;
        if (this.pauseFor > 0 && this.myPosition < this.t.width - this.myWidth && this.myPosition + this.t.tickBy >= this.t.width - this.myWidth) {
            for (int i = 0; i < this.pauseFor && !this.t.changingScale; i += 10) {
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void shiftPosition() {
        this.myPosition -= this.t.tickBy;
        if (this.pauseFor > 0 && this.myPosition > 0 && this.myPosition - this.t.tickBy <= 0) {
            for (int i = 0; i < this.pauseFor && !this.t.changingScale; i += 10) {
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void reverseYShift() {
        this.myYpos += this.t.tickBy;
        if (this.pauseFor > 0 && this.myYpos < this.t.height - this.ascent && this.myYpos + this.t.tickBy >= this.t.height - this.ascent) {
            for (int i = 0; i < this.pauseFor && !this.t.changingScale; i += 10) {
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void shiftYPosition() {
        this.myYpos -= this.t.tickBy;
        if (this.pauseFor > 0 && this.myYpos > this.ascent && this.myYpos - this.t.tickBy <= this.ascent) {
            for (int i = 0; i < this.pauseFor && !this.t.changingScale; i += 10) {
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public int draw(final Graphics g, final int y) {
        return this.draw(g, y, this.myPosition);
    }
    
    public int draw(final Graphics g, final int y, final int x) {
        if (this.myWidth > 0) {
            if (this.hover && this.t.showline) {
                g.setColor(this.t.linkColor);
            }
            else {
                g.setColor(this.t.background);
            }
            int theY = y + 2;
            if (this.t.vScrollMode()) {
                theY += this.ascent;
            }
            g.drawLine(x, theY, x + this.myWidth, theY);
        }
        int width = 0;
        String nextTAG = "";
        String nextSTR = "";
        String nextETG = "";
        final StringTokenizer st = new StringTokenizer(this.headline, "<>");
        while (st.hasMoreTokens()) {
            if (st.hasMoreTokens()) {
                nextTAG = st.nextToken();
            }
            else {
                nextTAG = "";
            }
            if (nextTAG.toLowerCase().startsWith("img")) {
                try {
                    width += this.drawSegment(g, x + width, y, nextTAG, "img");
                }
                catch (NoSuchMethodError noSuchMethodError) {
                    this.t.scale = false;
                    width += 100;
                }
                catch (Exception ex) {
                    this.t.scale = false;
                    width += 100;
                }
            }
            else if (nextTAG.toLowerCase().startsWith("pause")) {
                try {
                    this.pauseFor = Integer.parseInt(st.nextToken());
                }
                catch (NoSuchMethodError noSuchMethodError2) {}
                catch (Exception ex2) {}
            }
            else {
                if (st.hasMoreTokens()) {
                    nextSTR = st.nextToken();
                }
                else {
                    nextSTR = "";
                }
                if (st.hasMoreTokens()) {
                    nextETG = st.nextToken();
                }
                else {
                    nextETG = "";
                }
                try {
                    if (nextSTR.startsWith("/font")) {
                        nextSTR = " ";
                    }
                    width += this.drawSegment(g, x + width, y, nextTAG, nextSTR);
                }
                catch (NoSuchMethodError noSuchMethodError3) {
                    this.t.scale = false;
                    width += nextSTR.length() * 6;
                }
                catch (Exception ex3) {
                    this.t.scale = false;
                    width += nextSTR.length() * 6;
                }
            }
        }
        this.myWidth = width;
        this.myPosition -= this.t.tickBy;
        return width;
    }
    
    private int drawSegment(final Graphics g, final int x, int y, String tag, String text) {
        if (text.equals("") && !tag.equals("")) {
            text = tag;
            tag = "";
        }
        if (tag.toLowerCase().startsWith("img")) {
            try {
                String src = "";
                String width = "";
                String height = "";
                final StringTokenizer attributes = new StringTokenizer(tag, " =\"");
                String lToken = "";
                String nToken = "";
                while (attributes.hasMoreTokens()) {
                    lToken = nToken;
                    nToken = attributes.nextToken();
                    if (lToken.toLowerCase().equals("src")) {
                        src = nToken;
                    }
                    else if (lToken.toLowerCase().equals("width")) {
                        width = nToken;
                    }
                    else {
                        if (!lToken.toLowerCase().equals("height")) {
                            continue;
                        }
                        height = nToken;
                    }
                }
                if (src.equals("")) {
                    System.out.println("Parse format error: " + tag + " img src not specified");
                    return 0;
                }
                Image img = this.a.imgtable.get(src);
                if (img == null) {
                    img = this.a.getImage(this.t.base, src);
                    MediaTracker mt = new MediaTracker(this.a);
                    mt.addImage(img, 0);
                    mt.waitForAll();
                    mt = null;
                    this.a.imgtable.put(src, img);
                }
                if (width.equals("")) {
                    System.out.println("Parse format error: " + tag + " img width not specified");
                    return 0;
                }
                final int drawWidth = Integer.parseInt(width);
                if (height.equals("")) {
                    System.out.println("Parse format error: " + tag + " img height not specified");
                    return 0;
                }
                final int drawHeight = Integer.parseInt(height);
                if (this.t.vScrollMode()) {
                    g.drawImage(img, x, (this.ascent - drawHeight) / 2 + y, this.a);
                }
                else if (this.t.tocenter) {
                    g.drawImage(img, x, (this.t.height - drawHeight) / 2, this.a);
                }
                else {
                    g.drawImage(img, x, 0, this.a);
                }
                if (this.t.vScrollMode() && this.ascent < drawHeight) {
                    this.ascent = drawHeight;
                }
                return drawWidth;
            }
            catch (Exception e) {
                System.out.println("Parse format error: " + tag + " " + e.toString());
                return 0;
            }
        }
        String face = "";
        String color = "";
        String size = "";
        String style = "";
        try {
            final StringTokenizer attributes2 = new StringTokenizer(tag, " =\"");
            String lToken2 = "";
            String nToken2 = "";
            while (attributes2.hasMoreTokens()) {
                lToken2 = nToken2;
                nToken2 = attributes2.nextToken();
                if (lToken2.toLowerCase().equals("color")) {
                    color = nToken2;
                }
                else if (lToken2.toLowerCase().equals("face")) {
                    face = nToken2;
                }
                else if (lToken2.toLowerCase().equals("size")) {
                    size = nToken2;
                }
                else {
                    if (!lToken2.toLowerCase().equals("style")) {
                        continue;
                    }
                    style = nToken2;
                }
            }
            face = face.replace('_', ' ');
            if (face.equals("")) {
                face = "Times";
            }
            if (color.indexOf("#") >= 0) {
                color = color.substring(1);
            }
            int blue;
            int red;
            int green;
            if (color.equals("") || color.length() != 6) {
                green = (red = (blue = 0));
            }
            else {
                red = Integer.parseInt(color.substring(0, 2), 16);
                green = Integer.parseInt(color.substring(2, 4), 16);
                blue = Integer.parseInt(color.substring(4, 6), 16);
            }
            int pointSize = 9;
            if (!size.equals("")) {
                pointSize += Integer.parseInt(size);
            }
            int styleCode = 0;
            if (style.toLowerCase().startsWith("sup")) {
                y -= 4;
            }
            else if (style.toLowerCase().startsWith("sub")) {
                y += 4;
            }
            else if (style.toLowerCase().startsWith("bold")) {
                styleCode = 1;
            }
            else if (style.toLowerCase().startsWith("italic")) {
                styleCode = 2;
            }
            else if (style.toLowerCase().startsWith("bolditalic")) {
                styleCode = 3;
            }
            Font hashed = this.a.fonttable.get(String.valueOf(face) + styleCode + pointSize);
            if (hashed == null) {
                hashed = new Font(face, styleCode, pointSize);
                this.a.fonttable.put(String.valueOf(face) + styleCode + pointSize, hashed);
            }
            g.setFont(hashed);
            g.setColor(new Color(red, green, blue));
        }
        catch (Exception e2) {
            System.out.println("Parse format error: " + tag + " " + e2.toString());
        }
        final FontMetrics fontmetrics = g.getFontMetrics(g.getFont());
        if (this.ascent < fontmetrics.getAscent()) {
            this.ascent = fontmetrics.getAscent();
        }
        if (this.hover) {
            g.setColor(this.t.linkColor);
        }
        int ytodraw = y;
        if (this.t.vScrollMode()) {
            ytodraw += fontmetrics.getAscent() + (this.ascent - fontmetrics.getAscent()) / 2;
        }
        this.linePos = ytodraw + 2;
        g.drawString(text, x, ytodraw);
        if (style.toLowerCase().indexOf("underline") >= 0) {
            g.drawLine(x, this.linePos, x + fontmetrics.stringWidth(text), this.linePos);
            if (this.ascent < fontmetrics.getAscent() + 2) {
                this.ascent = fontmetrics.getAscent() + 2;
            }
        }
        return fontmetrics.stringWidth(text);
    }
}
