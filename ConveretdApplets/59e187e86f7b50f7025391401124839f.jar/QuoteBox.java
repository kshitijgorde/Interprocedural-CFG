import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import complabel.ParserNode;
import complabel.ParserElement;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Point;
import complabel.CompLabel;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class QuoteBox extends Canvas
{
    QuoteApplet applet;
    Quote quote;
    Vector compLabels;
    CompLabel sourceLabel;
    Point textOffset;
    Point sourceOffset;
    float textColorFactor;
    float sourceColorFactor;
    int textLineOffset;
    Color textLineColor;
    ThingPainter thingPainter;
    Image offImg;
    Graphics offG;
    Dimension sz;
    
    private ThingPainter getRandomPainter() {
        final int n = Math.abs(new Random().nextInt()) % 5;
        ThingPainter thingPainter;
        if (n <= 1) {
            thingPainter = new RectPainter();
        }
        else if (n == 2) {
            thingPainter = new CrossPainter();
        }
        else {
            thingPainter = new LinePainter();
        }
        return thingPainter;
    }
    
    public QuoteBox(final QuoteApplet applet) {
        this.compLabels = new Vector();
        this.textOffset = new Point(0, 0);
        this.sourceOffset = new Point(0, 0);
        this.textColorFactor = 1.0f;
        this.sourceColorFactor = 1.0f;
        this.textLineColor = Color.red;
        this.applet = applet;
        this.quote = new Quote();
    }
    
    void makeOffscreenBuffer() {
        this.sz = this.getSize();
        this.offImg = this.createImage(this.sz.width, this.sz.height);
        this.offG = this.offImg.getGraphics();
    }
    
    public void zapQuote() {
        if (this.offG == null) {
            return;
        }
        final int n = Math.abs(new Random().nextInt()) % 5;
        this.thingPainter = this.getRandomPainter();
        if (n <= 1) {
            this.textColorFactor = 1.0f;
            this.sourceColorFactor = 1.0f;
            for (int n2 = this.sz.width * 2, i = 0; i <= n2; i += 20) {
                final float pct = i / n2;
                this.textOffset.x = i;
                this.sourceOffset.x = -i;
                this.thingPainter.setPct(pct);
                this.repaint();
                try {
                    Thread.sleep(this.applet.animSpeed);
                }
                catch (InterruptedException ex) {}
            }
        }
        else if (n <= 3) {
            this.textColorFactor = 1.0f;
            this.sourceColorFactor = 1.0f;
            for (int n3 = this.sz.height * 2, j = 0; j <= n3; j += 7) {
                final float pct2 = j / n3;
                this.textOffset.y = j;
                this.sourceOffset.y = -j;
                this.thingPainter.setPct(pct2);
                this.repaint();
                try {
                    Thread.sleep(this.applet.animSpeed);
                }
                catch (InterruptedException ex2) {}
            }
        }
        else {
            this.textOffset = new Point(0, 0);
            this.sourceOffset = new Point(0, 0);
            this.textLineOffset = 0;
            for (float pct3 = 1.0f; pct3 >= 0.0f; pct3 -= 0.06) {
                if (this.applet.reverseColors) {
                    this.textColorFactor = 1.0f - pct3;
                    this.sourceColorFactor = 1.0f - pct3;
                }
                else {
                    this.textColorFactor = pct3;
                    this.sourceColorFactor = pct3;
                }
                this.thingPainter.setPct(pct3);
                this.repaint();
                try {
                    Thread.sleep(this.applet.animSpeed);
                }
                catch (InterruptedException ex3) {}
            }
        }
    }
    
    public void newQuote(final Quote quote) {
        if (this.quote.text.size() > 0) {
            this.zapQuote();
        }
        this.quote = quote;
        final String[] array = { "b" };
        this.compLabels = new Vector();
        final ParserElement element = new ParserElement();
        if (this.quote.getFlag(11)) {
            element.font = "serif";
        }
        else if (this.quote.getFlag(12)) {
            element.font = "Courier";
        }
        else {
            element.font = "sans";
        }
        if (this.quote.getFlag(2)) {
            element.size = "14";
        }
        else if (this.quote.getFlag(3)) {
            element.size = "16";
        }
        else if (this.quote.getFlag(4)) {
            element.size = "20";
        }
        else if (this.quote.getFlag(1)) {
            element.size = "11";
        }
        else {
            element.size = "13";
        }
        for (int i = 0; i < array.length; ++i) {
            element.flags.addElement(array[i]);
        }
        element.fgString = this.applet.defaultQuoteColorStr;
        element.bgString = this.applet.bgColorStr;
        ParserNode tagTree = new ParserNode();
        tagTree.element = element;
        for (int j = 0; j < this.quote.text.size(); ++j) {
            final CompLabel compLabel = new CompLabel(this.quote.text.elementAt(j), tagTree);
            this.compLabels.addElement(compLabel);
            tagTree = compLabel.tagTree;
        }
        final String[] array2 = { "b", "i" };
        if (this.quote.source != null) {
            this.sourceLabel = new CompLabel(this.quote.source, "sans", "13", array2, this.applet.defaultSourceColorStr, "black");
        }
        if (this.offG == null) {
            return;
        }
        final int n = Math.abs(new Random().nextInt()) % 5;
        this.thingPainter = this.getRandomPainter();
        if (n <= 1) {
            this.textColorFactor = 1.0f;
            this.sourceColorFactor = 1.0f;
            final int n2 = this.sz.height * 2;
            this.textOffset.y = 0;
            this.sourceOffset.y = 0;
            for (int k = n2; k > 0; k -= 20) {
                final float pct = k / n2;
                this.textOffset.x = -k;
                this.sourceOffset.x = k;
                this.thingPainter.setPct(pct);
                this.repaint();
                try {
                    Thread.sleep(this.applet.animSpeed);
                }
                catch (InterruptedException ex) {}
            }
            this.thingPainter.setPct(0.0f);
            this.textOffset.x = 0;
            this.sourceOffset.x = 0;
            if (this.quote.getFlag(16)) {
                this.doFlash();
            }
            else {
                try {
                    Thread.sleep(40L);
                }
                catch (InterruptedException ex2) {}
                this.textColorFactor = this.applet.flashBrightness;
                this.sourceColorFactor = this.applet.flashBrightness;
                this.repaint();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex3) {}
            }
            this.textColorFactor = 1.0f;
            this.sourceColorFactor = 1.0f;
            this.repaint();
        }
        else if (n <= 3) {
            this.textColorFactor = 1.0f;
            this.sourceColorFactor = 1.0f;
            final int n3 = this.sz.height * 2;
            this.textOffset.x = 0;
            this.sourceOffset.x = 0;
            for (int l = n3; l > 0; l -= 7) {
                final float pct2 = l / n3;
                this.textOffset.y = -l;
                this.sourceOffset.y = l;
                this.thingPainter.setPct(pct2);
                this.repaint();
                try {
                    Thread.sleep(this.applet.animSpeed);
                }
                catch (InterruptedException ex4) {}
            }
            this.thingPainter.setPct(0.0f);
            this.textOffset.y = 0;
            this.sourceOffset.y = 0;
            if (this.quote.getFlag(16)) {
                this.doFlash();
            }
            else {
                try {
                    Thread.sleep(40L);
                }
                catch (InterruptedException ex5) {}
                this.textColorFactor = this.applet.flashBrightness;
                this.sourceColorFactor = this.applet.flashBrightness;
                this.repaint();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex6) {}
            }
            this.textColorFactor = 1.0f;
            this.sourceColorFactor = 1.0f;
            this.repaint();
        }
        else {
            final Point textOffset = this.textOffset;
            final Point textOffset2 = this.textOffset;
            final boolean b = false;
            textOffset2.y = (b ? 1 : 0);
            textOffset.x = (b ? 1 : 0);
            final Point sourceOffset = this.sourceOffset;
            final Point sourceOffset2 = this.sourceOffset;
            final boolean b2 = false;
            sourceOffset2.y = (b2 ? 1 : 0);
            sourceOffset.x = (b2 ? 1 : 0);
            this.textLineOffset = 0;
            for (float pct3 = 0.0f; pct3 < 1.0f; pct3 += 0.085) {
                if (this.applet.reverseColors) {
                    this.textColorFactor = 1.0f - pct3;
                    this.sourceColorFactor = 1.0f - pct3;
                }
                else {
                    this.textColorFactor = pct3;
                    this.sourceColorFactor = pct3;
                }
                this.thingPainter.setPct(pct3);
                this.repaint();
                try {
                    Thread.sleep(this.applet.animSpeed);
                }
                catch (InterruptedException ex7) {}
            }
            this.thingPainter.setPct(0.0f);
            if (this.quote.getFlag(16)) {
                this.doFlash();
            }
            else {
                this.textColorFactor = this.applet.flashBrightness;
                this.sourceColorFactor = this.applet.flashBrightness;
                this.repaint();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex8) {}
            }
            this.textColorFactor = 1.0f;
            this.sourceColorFactor = 1.0f;
            this.repaint();
        }
    }
    
    void doFlash() {
        for (int i = 0; i < 18; ++i) {
            this.textColorFactor = this.applet.flashBrightness;
            this.sourceColorFactor = this.applet.flashBrightness;
            this.repaint();
            try {
                Thread.sleep(5L);
            }
            catch (InterruptedException ex) {}
            this.textColorFactor = 1.0f;
            this.sourceColorFactor = 1.0f;
            this.repaint();
            try {
                Thread.sleep(5L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.offImg == null || this.offG == null) {
            this.makeOffscreenBuffer();
        }
        this.offG.setColor(this.applet.bgColor);
        this.offG.fillRect(0, 0, this.sz.width, this.sz.height);
        int n = 0;
        int n2 = 0;
        if (this.applet.stopped) {
            final Font font = new Font("SansSerif", 1, 62);
            final FontMetrics fontMetrics = this.offG.getFontMetrics(font);
            final String s = "paused";
            n2 = fontMetrics.stringWidth(s);
            final int n3 = (this.sz.width - n2) / 2;
            this.offG.setFont(font);
            if (this.applet.reverseColors) {
                this.offG.setColor(new Color(180, 180, 180));
            }
            else {
                this.offG.setColor(new Color(70, 70, 70));
            }
            this.offG.drawString(s, n3, this.sz.height / 2 + fontMetrics.getAscent() / 4);
        }
        boolean b = true;
        if (this.quote.source.length() < 2) {
            b = false;
        }
        boolean b2 = false;
        if (this.compLabels != null) {
            for (int i = 0; i < this.compLabels.size(); ++i) {
                b2 = true;
                final CompLabel compLabel = this.compLabels.elementAt(i);
                n2 = compLabel.getWidth(this.offG);
                final int n4 = (this.sz.width - n2) / 2;
                n += compLabel.maxAscent;
                compLabel.paintAt(this.offG, this.textOffset.x + n4, this.textOffset.y + n, this.textColorFactor);
            }
        }
        final int n5 = n + 4;
        final int n6 = n2;
        if (b) {
            this.sourceLabel.paintAt(this.offG, this.sourceOffset.x + (this.sz.width - this.sourceLabel.getWidth(this.offG)) / 2, this.sourceOffset.y + (n + (this.sourceLabel.maxHeight + 5)), this.sourceColorFactor);
        }
        if (b2 && b && this.thingPainter != null) {
            this.thingPainter.paint(this.offG, this.sz, new Point(this.sz.width / 2, n5), n6);
        }
        graphics.drawImage(this.offImg, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    Color colorMul(final Color color, final float n) {
        return new Color((int)(color.getRed() * n), (int)(color.getGreen() * n), (int)(color.getBlue() * n));
    }
    
    private class ThingPainter
    {
        float pct;
        Color color;
        
        public void paint(final Graphics graphics, final Dimension dimension, final Point point, final int n) {
        }
        
        public void setPct(final float pct) {
            this.pct = pct;
        }
        
        public void setColor(final Color color) {
            this.color = color;
        }
    }
    
    private class LinePainter extends ThingPainter
    {
        public void paint(final Graphics graphics, final Dimension dimension, final Point point, final int n) {
            if (QuoteBox.this.applet.reverseColors) {
                super.color = new Color(super.pct, super.pct, 1.0f);
            }
            else {
                super.color = new Color(1.0f - super.pct, 0.0f, 0.0f);
            }
            final int n2 = (int)(super.pct * 50.0f);
            graphics.setColor(super.color);
            graphics.drawLine(point.x - n / 2 - 5, point.y + n2, point.x + n / 2 + 5, point.y + n2);
        }
    }
    
    private class RectPainter extends ThingPainter
    {
        public void paint(final Graphics graphics, final Dimension dimension, final Point point, final int n) {
            if (QuoteBox.this.applet.reverseColors) {
                super.color = new Color(super.pct, super.pct, 1.0f);
            }
            else {
                super.color = new Color(1.0f - super.pct, 0.0f, 0.0f);
            }
            final int n2 = (int)(super.pct * dimension.height);
            graphics.setColor(super.color);
            final int n4;
            final int n3 = dimension.width - (n4 = (int)((dimension.width / 2 - n / 2 - 5) * (1.0 - super.pct)));
            if (super.pct != 0.0f) {
                this.funRect(graphics, n4, point.y - n2, n3 - n4, point.y + n2);
            }
            else {
                graphics.drawLine(point.x - n / 2 - 5, point.y, point.x + n / 2 + 5, point.y);
            }
        }
        
        private void funRect(final Graphics graphics, int n, int n2, int n3, int n4) {
            graphics.drawRect(n, n2, n3, n4);
            for (int n5 = 8; n3 > 2 && n4 > 2; n3 -= n5 * 2, n4 -= n5 * 2, n += n5, n2 += n5) {
                if (QuoteBox.this.applet.reverseColors) {
                    super.color = new Color(super.pct, super.pct, 1.0f);
                }
                else {
                    super.color = new Color(1.0f - super.pct, 1.0f - super.pct, 0.0f);
                }
                graphics.setColor(super.color);
                graphics.drawRect(n, n2, n3, n4);
            }
        }
    }
    
    private class CrossPainter extends ThingPainter
    {
        private LinePainter linePainter;
        private Color color1;
        private Color color2;
        private Color color3;
        private Color color4;
        
        CrossPainter() {
            this.linePainter = new LinePainter();
        }
        
        public void paint(final Graphics graphics, final Dimension dimension, final Point point, final int n) {
            this.linePainter.setPct(super.pct);
            this.linePainter.paint(graphics, dimension, point, n);
            if (QuoteBox.this.applet.reverseColors) {
                this.color1 = new Color(super.pct, super.pct, 1.0f);
                this.color2 = new Color(super.pct, 1.0f, super.pct);
                this.color3 = new Color(1.0f, super.pct, super.pct);
                this.color4 = new Color(1.0f, 1.0f, super.pct);
            }
            else {
                this.color1 = new Color(1.0f - super.pct, 0.0f, super.pct);
                this.color2 = new Color(1.0f, 1.0f - super.pct, super.pct);
                this.color3 = new Color(0.0f, 0.0f, 1.0f - super.pct);
                this.color4 = new Color(1.0f - super.pct, super.pct, 1.0f - super.pct);
            }
            final int n2 = (int)(dimension.width * super.pct);
            final int n3 = (int)(dimension.height * super.pct);
            final int n4 = dimension.width - n2;
            final int n5 = dimension.height - n3;
            if (super.pct != 0.0f) {
                this.funCrossLines(graphics, n2, n4, n3, n5, dimension);
            }
        }
        
        private void funCrossLines(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Dimension dimension) {
            graphics.setColor(this.color1);
            graphics.drawLine(n, 0, n2, dimension.height);
            graphics.setColor(this.color2);
            graphics.drawLine(n2, 0, n, dimension.height);
            graphics.setColor(this.color3);
            graphics.drawLine(n3, 0, n4, dimension.width);
            graphics.setColor(this.color4);
            graphics.drawLine(n2, 0, n, dimension.height);
        }
    }
}
