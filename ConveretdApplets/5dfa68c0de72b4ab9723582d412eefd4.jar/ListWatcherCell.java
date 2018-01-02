import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.font.TextLayout;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.text.AttributedString;
import java.text.AttributedCharacterIterator;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class ListWatcherCell
{
    Color CELL_COLOR;
    String contents;
    AttributedCharacterIterator ci;
    AttributedString as;
    LineBreakMeasurer lineMeasurer;
    FontRenderContext renderContext;
    BufferedImage contentsImage;
    int w;
    int h;
    int x;
    int y;
    
    ListWatcherCell(final String contents, final int w) {
        this.CELL_COLOR = new Color(218, 77, 17);
        this.contents = contents;
        this.w = w;
        this.x = 0;
        this.y = 0;
        (this.as = new AttributedString(contents)).addAttribute(TextAttribute.FONT, ListWatcher.LABEL_FONT);
        this.ci = this.as.getIterator();
        this.renderContext = Skin.bubbleFrame.createGraphics().getFontRenderContext();
        this.h = 4;
        (this.lineMeasurer = new LineBreakMeasurer(this.ci, this.renderContext)).setPosition(this.ci.getBeginIndex());
        while (this.lineMeasurer.getPosition() < this.ci.getEndIndex()) {
            final TextLayout nextLayout = this.lineMeasurer.nextLayout(this.w - 10);
            this.h += (int)nextLayout.getAscent();
            this.h += (int)(nextLayout.getDescent() + nextLayout.getLeading());
        }
        this.contentsImage = new BufferedImage(this.w, this.h, 6);
        final Graphics graphics = this.contentsImage.getGraphics();
        graphics.setColor(Color.WHITE);
        final Graphics2D graphics2D = (Graphics2D)graphics;
        int n = this.y + 2;
        this.lineMeasurer.setPosition(this.ci.getBeginIndex());
        while (this.lineMeasurer.getPosition() < this.ci.getEndIndex()) {
            final TextLayout nextLayout2 = this.lineMeasurer.nextLayout(this.w - 10);
            final int n2 = (int)(n + nextLayout2.getAscent());
            nextLayout2.draw(graphics2D, this.x + 6, n2);
            n = (int)(n2 + (nextLayout2.getDescent() + nextLayout2.getLeading()));
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(this.x + 2, this.y, this.w - 4, 1);
        graphics.fillRect(this.x + 2, this.y + this.h - 1, this.w - 4, 1);
        graphics.fillRect(this.x, this.y + 2, 1, this.h - 4);
        graphics.fillRect(this.x + this.w - 1, this.y + 2, 1, this.h - 4);
        graphics.fillRect(this.x + 1, this.y + 1, 1, 1);
        graphics.fillRect(this.x + this.w - 2, this.y + 1, 1, 1);
        graphics.fillRect(this.x + 1, this.y + this.h - 2, 1, 1);
        graphics.fillRect(this.x + this.w - 2, this.y + this.h - 2, 1, 1);
        graphics.setColor(WatcherReadout.darker(WatcherReadout.darker(this.CELL_COLOR)));
        graphics.fillRect(this.x + 2, this.y + 1, this.w - 4, 1);
        graphics.fillRect(this.x + 1, this.y + 2, 1, this.h - 4);
        graphics.setColor(WatcherReadout.darker(this.CELL_COLOR));
        graphics.fillRect(this.x + 2, this.y + 2, this.w - 4, 1);
        graphics.fillRect(this.x + 2, this.y + this.h - 2, this.w - 4, 1);
        graphics.fillRect(this.x + 2, this.y + 2, 1, this.h - 3);
        graphics.fillRect(this.x + this.w - 2, this.y + 2, 1, this.h - 4);
        graphics.setColor(this.CELL_COLOR);
        graphics.fillRect(this.x + 3, this.y + 3, this.w - 5, this.h - 5);
        graphics.drawImage(this.contentsImage, this.x, this.y, new Color(0, 0, 0, 1), null);
    }
}
