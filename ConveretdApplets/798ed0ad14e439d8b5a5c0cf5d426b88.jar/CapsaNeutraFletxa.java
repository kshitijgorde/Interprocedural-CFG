import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaNeutraFletxa extends NeuterBox
{
    boolean drawLine;
    boolean em;
    boolean fillRect;
    boolean getArrowSize;
    boolean getTimes;
    
    public final void setCommand(final String command) {
        super.setCommand(command);
        this.getTimes = (super.F.indexOf("delayed") != -1);
        this.getArrowSize = (super.F.indexOf("mapsto") != -1);
        this.drawLine = (super.F.indexOf("ong") != -1);
        this.em = (super.F.indexOf("L") != -1 || super.F.indexOf("R") != -1 || this.getTimes);
        this.fillRect = (super.F.indexOf("ight") != -1 || this.getArrowSize || this.getTimes);
    }
    
    public final void calculRect(final BoxComponent boxComponent) {
        if (this.getTimes) {
            super.width = this.em(0.9f);
        }
        else if (this.drawLine) {
            super.width = this.em(1.0f);
        }
        else {
            super.width = this.em(0.85f);
        }
        super.height = this.em(1.0f);
        super.baseline = this.em(0.75f);
        if (super.J) {
            super.height = 2 * this.getArrowSize(super.height) + this.getTimes(super.height);
            super.baseline = super.height;
        }
    }
    
    public final int getArrowSize(final int n) {
        return this.em ? (n / 5) : (n / 7);
    }
    
    public final void onPaint(final Graphics graphics) {
        final int em = this.em(1.0f);
        final int max = Math.max(this.em(0.1f), 1);
        final int arrowSize = this.getArrowSize(em);
        final int n = super.width - 2 * max;
        final int times = this.getTimes(em);
        int n2;
        if (super.J) {
            n2 = (2 * arrowSize + times) / 2;
        }
        else {
            n2 = 45 * super.height / 100;
        }
        final int n3 = 40 * arrowSize / 100 + 50 * times / 100;
        graphics.translate(0, times / 2);
        for (int i = 0; i < times; ++i) {
            if (this.fillRect) {
                graphics.drawLine(n - arrowSize, n2 - arrowSize, n, n2);
                graphics.drawLine(n - arrowSize, n2 + arrowSize, n, n2);
            }
            else {
                graphics.drawLine(max, n2, max + arrowSize, n2 - arrowSize);
                graphics.drawLine(max, n2, max + arrowSize, n2 + arrowSize);
            }
            if (this.em && !this.getTimes) {
                if (this.fillRect) {
                    graphics.drawLine(max, n2 - n3, n - n3, n2 - n3);
                    graphics.drawLine(max, n2 + n3, n - n3, n2 + n3);
                }
                else {
                    graphics.drawLine(max + n3, n2 - n3, n, n2 - n3);
                    graphics.drawLine(max + n3, n2 + n3, n, n2 + n3);
                }
            }
            if (!this.em) {
                if (this.fillRect) {
                    graphics.drawLine(max, n2, n, n2);
                }
                else {
                    graphics.drawLine(max, n2, n, n2);
                }
            }
            if (this.getTimes) {
                final int n4 = 90 * arrowSize / 100;
                graphics.drawLine(max + 10 * times / 4, n2 - n3, n - n3, n2 - n3);
                graphics.drawLine(max + 10 * times / 4, n2 + n3, n - n3, n2 + n3);
                graphics.drawLine(max, n2 - n4, max + times, n2 - n4);
                graphics.drawLine(max, n2 + n4, max + times, n2 + n4);
            }
            if (this.getArrowSize) {
                final int n5 = arrowSize;
                graphics.fillRect(max, n2 - n5, times, 2 * n5 + 1);
            }
            graphics.translate(0, -1);
        }
        graphics.translate(0, times - times / 2);
    }
}
