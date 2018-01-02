import java.awt.Font;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaNeutraIgual extends NeuterBox
{
    boolean drawPolygon;
    boolean em;
    boolean equals;
    
    public final void setCommand(final String command) {
        super.setCommand(command);
        this.drawPolygon = !command.equals("=");
        this.em = (super.F.indexOf("neq") != -1);
        this.equals = (super.F.indexOf("assign") != -1);
    }
    
    public final void calculRect(final BoxComponent boxComponent) {
        if (this.drawPolygon) {
            super.width = this.em(0.9f);
        }
        else {
            super.width = this.em(0.7f);
        }
        super.height = this.em(1.0f);
        super.baseline = this.em(0.75f);
    }
    
    public final void onPaint(final Graphics graphics) {
        final int em = this.em(0.1f);
        final int n = super.height / 8;
        final int n2 = super.height / 3;
        final int width = super.width;
        final int n3 = 45 * super.height / 100;
        final int n4 = width / 8;
        final int style = super.B.getStyle();
        final Font b = super.B;
        int n5 = (int)(((style == 1) ? 1.3f : 1.0f) * super.height / 11.0f);
        if (n5 == 0) {
            n5 = 1;
        }
        if (this.equals) {
            final int n6 = width / 8;
            graphics.fillRect(em, n3 - n - 2 * n5 / 2, n6, n5);
            graphics.fillRect(em, n3 + n - 0 * n5 / 2, n6, n5);
            graphics.fillRect(em + 2 * n6, n3 - n - n5 / 2, width - 2 * em - 2 * n6, n5);
            graphics.fillRect(em + 2 * n6, n3 + n - n5 / 2, width - 2 * em - 2 * n6, n5);
        }
        else {
            graphics.fillRect(em, n3 - n - n5 / 2, width - 2 * em, n5);
            graphics.fillRect(em, n3 + n - n5 / 2, width - 2 * em, n5);
            if (this.em) {
                final int n7 = 45 * n5 / 100;
                final int[] array = { width / 2 - n4 - n7, width / 2 + n4 - n7, width / 2 + n4 + n7, width / 2 - n4 + n7 };
                final int[] array2 = { n3 + n2, n3 - n2, n3 - n2, n3 + n2 };
                graphics.fillPolygon(array, array2, 4);
                graphics.drawPolygon(array, array2, 4);
            }
        }
    }
}
