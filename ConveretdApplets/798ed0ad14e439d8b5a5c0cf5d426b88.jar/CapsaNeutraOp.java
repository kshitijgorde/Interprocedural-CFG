import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaNeutraOp extends NeuterBox
{
    private int text_width;
    
    public final void setCommand(final String f) {
        switch (f.charAt(0)) {
            case '-': {
                super.F = "\u2212";
                return;
            }
            case '*': {
                super.F = "·";
                return;
            }
            case '\\': {
                if (f.equals("\\leq")) {
                    super.F = "\u2264";
                    return;
                }
                if (f.equals("\\geq")) {
                    super.F = "\u2265";
                    return;
                }
                if (f.equals("\\ang")) {
                    super.F = "\u2220";
                    return;
                }
                if (f.equals("\\pm")) {
                    super.F = "±";
                    return;
                }
                if (f.equals("\\hat")) {
                    super.F = "^";
                    return;
                }
                if (f.equals("\\utri")) {
                    super.F = "\u25b5";
                    return;
                }
                break;
            }
        }
        super.F = f;
    }
    
    public final void calculRect(final BoxComponent boxComponent) {
        super.width = this.em(0.1f) + this.text_width + this.em(0.1f);
        super.height = this.em(1.0f);
        super.baseline = this.em(0.75f);
        if (super.J) {
            if (super.F.equals("^")) {
                super.height = this.getmy(super.height) + this.getTimes(super.height);
            }
            else if (super.F.equals("\u2212")) {
                super.height = this.getTimes(super.height);
            }
            else {
                super.height = 2 * this.getmy(super.height) + 2 * this.getTimes(super.height);
            }
            super.baseline = super.height;
        }
    }
    
    public final int getmy(final int n) {
        return (this.text_width - this.getTimes(n)) / 2;
    }
    
    public final void onSetFont(final BoxComponent boxComponent, final Font font) {
        String f = super.F;
        if (f.equals("\u2264")) {
            f = "<";
        }
        else if (f.equals("\u2265")) {
            f = ">";
        }
        else if (f.equals("\u2212")) {
            f = "x";
        }
        else if (f.equals("\ufffd")) {
            f = ".";
        }
        else if (f.equals("\\in") || f.equals("\\notin")) {
            f = "e";
        }
        else if (f.equals("\u2220")) {
            f = "<";
        }
        else if (f.equals("±")) {
            f = "+";
        }
        else if (f.equals("^")) {
            f = "X";
        }
        else if (f.equals("\u25b5")) {
            f = "X";
        }
        this.text_width = boxComponent.getFontUtils().width(boxComponent, font, f);
    }
    
    public final void onPaint(final Graphics graphics) {
        final int em = this.em(1.0f);
        final int em2 = this.em(0.1f);
        final int em3 = this.em(0.1f);
        final int width = super.width;
        final int times = this.getTimes(em);
        final int n = (width - em2 - em3 - times) / 2;
        final int n2 = (this.text_width - times) / 2;
        GraphicsUtils.setLineWidth(graphics, 0.5f);
        int n3;
        if (super.J) {
            if (super.F.equals("\u2212")) {
                n3 = super.height / 2;
            }
            else {
                n3 = n2 + times;
            }
        }
        else {
            n3 = 45 * super.height / 100;
        }
        if (super.F.equals("\u2212")) {
            graphics.fillRect(em2, n3 - times / 2, n + times + n, times);
        }
        else if (super.F.equals("+")) {
            graphics.fillRect(em2, n3 - times / 2, n + times + n, times);
            graphics.fillRect(em2 + n, n3 - times / 2 - n2, times, n + times + n2);
        }
        else if (super.F.equals("±")) {
            final int n4 = 10 * em / 100;
            final int n5 = 30 * em / 100;
            graphics.fillRect(em2, n3 - times / 2 - n4, n + times + n, times);
            graphics.fillRect(em2 + n, n3 - times / 2 - n2 - n4, times, n2 + times + n2);
            graphics.fillRect(em2, n3 - times / 2 + n5, n + times + n, times);
        }
        else if (super.F.equals("\ufffd")) {
            graphics.fillRect(width / 2 - times / 2, n3 - times / 2, times, times);
        }
        else if (super.F.equals("<")) {
            final int[] array = { em2, em2 + n2 + times + n, em2 + n + times + n, em2 };
            graphics.fillPolygon(array, new int[] { n3 - times, n3 - times - n2, n3 - n2, n3 }, 4);
            graphics.fillPolygon(array, new int[] { n3 - times, n3 - times + n2, n3 + n2, n3 }, 4);
        }
        else if (super.F.equals(">")) {
            final int[] array2 = { em2 + n + times + n, em2, em2, em2 + n + times + n };
            graphics.fillPolygon(array2, new int[] { n3 - times, n3 - times - n2, n3 - n2, n3 }, 4);
            graphics.fillPolygon(array2, new int[] { n3 - times, n3 - times + n2, n3 + n2, n3 }, 4);
        }
        else if (super.F.equals("\u2264")) {
            final int[] array3 = { em2, em2 + n + times + n, em2 + n + times + n, em2 };
            graphics.fillPolygon(array3, new int[] { n3 - times, n3 - times - n2, n3 - n2, n3 }, 4);
            graphics.fillPolygon(array3, new int[] { n3 - times, n3 - times + n2, n3 + n2, n3 }, 4);
            graphics.fillPolygon(array3, new int[] { n3 - times + 2 * times, n3 - times + n2 + 2 * times, n3 + n2 + 2 * times, n3 + 2 * times }, 4);
        }
        else if (super.F.equals("\u2265")) {
            final int[] array4 = { em2 + n + times + n, em2, em2, em2 + n + times + n };
            graphics.fillPolygon(array4, new int[] { n3 - times, n3 - times - n2, n3 - n2, n3 }, 4);
            graphics.fillPolygon(array4, new int[] { n3 - times, n3 - times + n2, n3 + n2, n3 }, 4);
            graphics.fillPolygon(array4, new int[] { n3 - times + 2 * times, n3 - times + n2 + 2 * times, n3 + n2 + 2 * times, n3 + 2 * times }, 4);
        }
        else if (super.F.equals("\u2220")) {
            final int[] array5 = { em2 + n + times + n, em2, em2, em2 + n + times + n };
            graphics.fillPolygon(array5, new int[] { n3 - n2 - times, n3 + n2, n3 + n2 + times, n3 - n2 }, 4);
            graphics.fillPolygon(array5, new int[] { n3 + n2, n3 + n2, n3 + n2 + times, n3 + n2 + times }, 4);
        }
        else if (super.F.equals("\u25b5")) {
            final int n6 = n2 + n2;
            final int[] array6 = { em2 + n + times / 2 + n, em2 + n + times / 2, em2 + times / 2, em2 + times / 2, em2 + n + times / 2, em2 + n + times / 2 + n };
            final int n7 = super.baseline - 1;
            final int[] array7 = { n7 - times + 1, n7 - n6 - times + 1, n7 - times + 1, n7, n7 - n6, n7 };
            graphics.fillPolygon(array6, array7, 6);
            graphics.drawPolygon(array6, array7, 6);
            graphics.fillRect(em2 + times / 2, n7 - times + 1, 2 * n, times);
        }
        else if (super.F.equals("^")) {
            final int[] array8 = { em2 + n + times / 2 + n, em2 + n + times / 2, em2 + times / 2, em2 + times / 2, em2 + n + times / 2, em2 + n + times / 2 + n };
            final int[] array9 = { n3 - times + 1, n3 - n2 - times + 1, n3 - times + 1, n3, n3 - n2, n3 };
            graphics.fillPolygon(array8, array9, 6);
            graphics.drawPolygon(array8, array9, 6);
        }
        else if (super.F.equals(":")) {
            final int n8 = super.height / 8;
            graphics.fillRect(width / 2, n3 - n8 - 2 * times / 2, times, times);
            graphics.fillRect(width / 2, n3 + n8 + 0 * times / 2, times, times);
        }
        else if (super.F.equals("\\in") || super.F.equals("\\notin")) {
            final int max = Math.max(times / 2, 1);
            final int n9 = (n2 + times + n2 - n2 / 6) / 2;
            final int n10 = n3 - n2 + n2 / 6 + n9;
            int n11 = 72;
            int n12 = 216;
            int n13 = 0;
            if (!GraphicsUtils.isFileGraphics(graphics)) {
                if (OmegaSystem.versio_java >= 2) {
                    n13 = 1;
                }
                else {
                    n11 = 60;
                    n12 = 239;
                }
            }
            for (int i = 0; i < times; ++i) {
                graphics.drawArc(em2, n10 - n9, n + times + n + 2, 2 * n9 + n13, n11, n12);
                graphics.translate(1, 0);
            }
            graphics.translate(-times, 0);
            graphics.fillRect(em2, n10 - max / 2, n + times + n, max);
            if (super.F.equals("\\notin")) {
                final int n14 = 45 * times / 100;
                final int n15 = super.height / 5;
                final int[] array10 = { em2 + 2 * n14 - n14, n + times + n + 2 - n14, n + times + n + 2 + n14, em2 + 2 * n14 + n14 };
                final int[] array11 = { n10 + n9 + n15, n10 - n9 - n15, n10 - n9 - n15, n10 + n9 + n15 };
                graphics.fillPolygon(array10, array11, 4);
                graphics.drawPolygon(array10, array11, 4);
            }
        }
        else {
            graphics.drawString(super.F, this.em(0.1f), super.baseline);
        }
        GraphicsUtils.setLineWidth(graphics, 1.0f);
    }
}
