import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class TickerPrice extends TickerData
{
    private int style;
    private Font normal;
    private Font small;
    private Font tiny;
    private FontMetrics fontmetrics;
    private Color color;
    
    public TickerPrice(final TickerInfo ticker, final String s) {
        this.style = 0;
        this.normal = null;
        this.small = null;
        this.tiny = null;
        this.fontmetrics = null;
        this.color = null;
        this.ticker = ticker;
        this.parse(s);
        this.image = ticker.app.createImage(1, 1);
        final Graphics graphics = this.image.getGraphics();
        this.fontname = this.getField("FONT", ticker.fontname);
        this.link = this.getField("LINK", "no link?");
        this.target = this.getField("TARGET", "_blank");
        try {
            this.fontsize = Integer.parseInt(this.getField("SIZE"));
        }
        catch (NumberFormatException ex) {
            this.fontsize = ticker.fontsize;
        }
        try {
            this.fontsize = Integer.parseInt(this.getField("SMALLSIZE"));
        }
        catch (NumberFormatException ex2) {
            this.smallFontsize = ticker.smallFontsize;
        }
        try {
            this.fontsize = Integer.parseInt(this.getField("TINYSIZE"));
        }
        catch (NumberFormatException ex3) {
            this.tinyFontsize = ticker.tinyFontsize;
        }
        this.normal = new Font(this.fontname, 1, this.fontsize);
        this.small = new Font(this.fontname, 1, this.smallFontsize);
        this.tiny = new Font(this.fontname, 0, this.tinyFontsize);
        graphics.setFont(this.normal);
        this.fontmetrics = graphics.getFontMetrics();
        graphics.dispose();
        this.descent = this.fontmetrics.getDescent();
        try {
            this.color = new Color(Integer.parseInt(this.getField("COLOR"), 16));
        }
        catch (NumberFormatException ex4) {
            this.color = ticker.fgColor;
        }
        try {
            this.style = Integer.parseInt(this.getField("STYLE"));
        }
        catch (NumberFormatException ex5) {}
    }
    
    @Override
    protected int redraw(final Graphics graphics, final int n, final int n2, final boolean b) {
        switch (this.style) {
            case 0: {
                return this.drawStyle0(graphics, n, n2, b);
            }
            case 1: {
                return this.drawStyle1(graphics, n, n2, b);
            }
            case 2: {
                return this.drawStyle2(graphics, n, n2, b);
            }
            case 3: {
                return this.drawStyle3(graphics, n, n2, b);
            }
            case 4: {
                return this.drawStyle4(graphics, n, n2, b);
            }
            case 5: {
                return this.drawStyle5(graphics, n, n2, b);
            }
            case 6: {
                return this.drawStyle6(graphics, n, n2, b);
            }
            case 7: {
                return this.drawStyle7(graphics, n, n2, b);
            }
            case 8: {
                return this.drawStyle8(graphics, n, n2, b);
            }
            case 9: {
                return this.drawStyle9(graphics, n, n2, b);
            }
            case 10: {
                return this.drawStyle10(graphics, n, n2, b);
            }
            default: {
                System.out.println("Unknown style: " + this.style);
                return 0;
            }
        }
    }
    
    private int drawStyle0(final Graphics graphics, final int n, final int n2, final boolean b) {
        final String field = this.getField("SYMB");
        final String field2 = this.getField("LAST", "-");
        final String field3 = this.getField("LTIME", "--:--");
        if (field != null) {
            graphics.setFont(this.normal);
            graphics.setColor(this.color);
            graphics.drawString(field + " " + field2 + " " + field3, n, n2 + graphics.getFontMetrics().getHeight());
            if (this.width == 0) {
                this.width = this.fontmetrics.stringWidth(field + " " + field2 + " " + field3) + this.ticker.space;
            }
            if (this.height == 0) {
                this.height = this.fontmetrics.getHeight() + this.ticker.space;
            }
        }
        else {
            this.width = 0;
            this.height = 0;
        }
        if (b) {
            return this.width;
        }
        return this.height;
    }
    
    private int drawStyle1(final Graphics graphics, final int n, final int n2, final boolean b) {
        final String field = this.getField("NAME");
        final String field2 = this.getField("PREV", "-");
        final String field3 = this.getField("LAST", "-");
        final String field4 = this.getField("TREND", " ");
        final String field5 = this.getField("LTIME", "--:--");
        final String string = field2 + " / " + field3;
        if (field != null) {
            graphics.setColor(this.color);
            graphics.setFont(this.normal);
            final int height = graphics.getFontMetrics().getHeight();
            graphics.drawString(field, n, n2 + height);
            switch (field4.charAt(0)) {
                case '+': {
                    graphics.setColor(this.ticker.more);
                    break;
                }
                case '-': {
                    graphics.setColor(this.ticker.less);
                    break;
                }
                case '=': {
                    graphics.setColor(this.ticker.same);
                    break;
                }
                default: {
                    graphics.setColor(this.color);
                    break;
                }
            }
            graphics.setFont(this.small);
            final int n3 = height + graphics.getFontMetrics().getHeight();
            graphics.drawString(string, n, n2 + n3);
            graphics.setColor(this.color);
            graphics.setFont(this.tiny);
            final int n4 = n3 + graphics.getFontMetrics().getHeight();
            graphics.drawString(field5, n, n2 + n4);
            if (this.width == 0) {
                final int stringWidth = this.fontmetrics.stringWidth(field);
                final int n5 = (int)Math.round(this.fontmetrics.stringWidth(string) * 0.9);
                this.width = ((stringWidth > n5) ? stringWidth : n5) + this.ticker.space;
            }
            if (this.height == 0) {
                this.height = n4 + this.ticker.space;
            }
        }
        else {
            this.width = 0;
            this.height = 0;
        }
        if (b) {
            return this.width;
        }
        return this.height;
    }
    
    private int drawStyle2(final Graphics graphics, final int n, final int n2, final boolean b) {
        final String field = this.getField("NAME");
        final String field2 = this.getField("LAST", "-");
        final String field3 = this.getField("TREND", " ");
        final String field4 = this.getField("LTIME", "--:--");
        final String string = field2 + " / " + this.getField("CHGABS", "-");
        if (field != null) {
            graphics.setColor(this.color);
            graphics.setFont(this.normal);
            final int height = graphics.getFontMetrics().getHeight();
            graphics.drawString(field, n, n2 + height);
            switch (field3.charAt(0)) {
                case '+': {
                    graphics.setColor(this.ticker.more);
                    break;
                }
                case '-': {
                    graphics.setColor(this.ticker.less);
                    break;
                }
                case '=': {
                    graphics.setColor(this.ticker.same);
                    break;
                }
                default: {
                    graphics.setColor(this.color);
                    break;
                }
            }
            graphics.setFont(this.small);
            final int n3 = height + graphics.getFontMetrics().getHeight();
            graphics.drawString(string, n, n2 + n3);
            graphics.setColor(this.color);
            graphics.setFont(this.tiny);
            final int n4 = n3 + graphics.getFontMetrics().getHeight();
            graphics.drawString(field4, n, n2 + n4);
            if (this.width == 0) {
                final int stringWidth = this.fontmetrics.stringWidth(field);
                final int n5 = (int)Math.round(this.fontmetrics.stringWidth(string) * 0.9);
                this.width = ((stringWidth > n5) ? stringWidth : n5) + this.ticker.space;
            }
            if (this.height == 0) {
                this.height = n4 + this.ticker.space;
            }
        }
        else {
            this.width = 0;
            this.height = 0;
        }
        if (b) {
            return this.width;
        }
        return this.height;
    }
    
    private int drawStyle3(final Graphics graphics, final int n, final int n2, final boolean b) {
        final String field = this.getField("NAME");
        final String field2 = this.getField("PREV", "-");
        final String field3 = this.getField("LAST", "-");
        final String field4 = this.getField("TREND", " ");
        final String field5 = this.getField("LTIME", "--:--");
        final String string = field3 + " (" + field2 + ")";
        if (field != null) {
            graphics.setColor(this.color);
            graphics.setFont(this.normal);
            final int height = graphics.getFontMetrics().getHeight();
            graphics.drawString(field, n, n2 + height);
            switch (field4.charAt(0)) {
                case '+': {
                    graphics.setColor(this.ticker.more);
                    break;
                }
                case '-': {
                    graphics.setColor(this.ticker.less);
                    break;
                }
                case '=': {
                    graphics.setColor(this.ticker.same);
                    break;
                }
                default: {
                    graphics.setColor(this.color);
                    break;
                }
            }
            graphics.setFont(this.small);
            final int n3 = height + graphics.getFontMetrics().getHeight();
            graphics.drawString(string, n, n2 + n3);
            graphics.setColor(this.color);
            graphics.setFont(this.tiny);
            final int n4 = n3 + graphics.getFontMetrics().getHeight();
            graphics.drawString(field5, n, n2 + n4);
            if (this.width == 0) {
                final int stringWidth = this.fontmetrics.stringWidth(field);
                final int n5 = (int)Math.round(this.fontmetrics.stringWidth(string) * 0.9);
                this.width = ((stringWidth > n5) ? stringWidth : n5) + this.ticker.space;
            }
            if (this.height == 0) {
                this.height = n4 + this.ticker.space;
            }
        }
        else {
            this.width = 0;
            this.height = 0;
        }
        if (b) {
            return this.width;
        }
        return this.height;
    }
    
    private int drawStyle4(final Graphics graphics, final int n, final int n2, final boolean b) {
        final String field = this.getField("SYMB");
        final String field2 = this.getField("LAST", "-");
        final String field3 = this.getField("LTIME", "--:--");
        final String field4 = this.getField("CHGABS", "+/-");
        if (field != null) {
            graphics.setColor(this.color);
            graphics.setFont(this.normal);
            graphics.drawString(field + " " + field2 + " " + field3 + " " + field4, n, n2 + graphics.getFontMetrics().getHeight());
            if (this.width == 0) {
                this.width = this.fontmetrics.stringWidth(field + " " + field2 + " " + field3 + " " + field4) + this.ticker.space;
            }
            if (this.height == 0) {
                this.height = this.fontmetrics.getHeight() + this.ticker.space;
            }
        }
        else {
            this.width = 0;
            this.height = 0;
        }
        if (b) {
            return this.width;
        }
        return this.height;
    }
    
    private int drawStyle5(final Graphics graphics, final int n, final int n2, final boolean b) {
        final String field = this.getField("NAME");
        final String field2 = this.getField("LAST", "-");
        final String field3 = this.getField("TREND", " ");
        final String field4 = this.getField("LTIME", "--:--");
        final String string = field2 + " / " + this.getField("CHGREL", "-");
        if (field != null) {
            graphics.setColor(this.color);
            graphics.setFont(this.normal);
            final int height = graphics.getFontMetrics().getHeight();
            graphics.drawString(field, n, n2 + height);
            switch (field3.charAt(0)) {
                case '+': {
                    graphics.setColor(this.ticker.more);
                    break;
                }
                case '-': {
                    graphics.setColor(this.ticker.less);
                    break;
                }
                case '=': {
                    graphics.setColor(this.ticker.same);
                    break;
                }
                default: {
                    graphics.setColor(this.color);
                    break;
                }
            }
            graphics.setFont(this.small);
            final int n3 = height + graphics.getFontMetrics().getHeight();
            graphics.drawString(string, n, n2 + n3);
            graphics.setColor(this.color);
            graphics.setFont(this.tiny);
            final int n4 = n3 + graphics.getFontMetrics().getHeight();
            graphics.drawString(field4, n, n2 + n4);
            if (this.width == 0) {
                final int stringWidth = this.fontmetrics.stringWidth(field);
                final int n5 = (int)Math.round(this.fontmetrics.stringWidth(string) * 0.9);
                this.width = ((stringWidth > n5) ? stringWidth : n5) + this.ticker.space;
            }
            if (this.height == 0) {
                this.height = n4 + this.ticker.space;
            }
        }
        else {
            this.width = 0;
            this.height = 0;
        }
        if (b) {
            return this.width;
        }
        return this.height;
    }
    
    private int drawStyle6(final Graphics graphics, final int n, final int n2, final boolean b) {
        final String field = this.getField("NAME");
        final String field2 = this.getField("LAST", "-");
        final String field3 = this.getField("CHGREL", "%");
        if (field != null) {
            final int stringWidth = this.fontmetrics.stringWidth(field + " ");
            graphics.setColor(this.color);
            graphics.setFont(this.normal);
            final int height = graphics.getFontMetrics().getHeight();
            graphics.drawString(field + " ", n, n2 + height);
            switch (field3.charAt(0)) {
                case '+': {
                    graphics.setColor(this.ticker.more);
                    break;
                }
                case '-': {
                    graphics.setColor(this.ticker.less);
                    break;
                }
                case '=': {
                    graphics.setColor(this.ticker.same);
                    break;
                }
                default: {
                    graphics.setColor(this.color);
                    break;
                }
            }
            graphics.drawString(field2 + " / " + field3, n + stringWidth, n2 + height);
            if (this.width == 0) {
                this.width = stringWidth + this.fontmetrics.stringWidth(field2 + " / " + field3) + this.ticker.space;
            }
            if (this.height == 0) {
                this.height = this.fontmetrics.getHeight() + this.ticker.space;
            }
        }
        else {
            this.width = 0;
            this.height = 0;
        }
        if (b) {
            return this.width;
        }
        return this.height;
    }
    
    private int drawStyle7(final Graphics graphics, final int n, final int n2, final boolean b) {
        final String field = this.getField("NAME");
        final String field2 = this.getField("VAL", "-");
        final String field3 = this.getField("PVAL", "-");
        final String field4 = this.getField("TREND", " ");
        final String field5 = this.getField("VALTIME", "--:--");
        final String string = field2 + " (" + field3 + ")";
        if (field != null) {
            graphics.setColor(this.color);
            graphics.setFont(this.normal);
            final int height = graphics.getFontMetrics().getHeight();
            graphics.drawString(field, n, n2 + height);
            switch (field4.charAt(0)) {
                case '+': {
                    graphics.setColor(this.ticker.more);
                    break;
                }
                case '-': {
                    graphics.setColor(this.ticker.less);
                    break;
                }
                case '=': {
                    graphics.setColor(this.ticker.same);
                    break;
                }
                default: {
                    graphics.setColor(this.color);
                    break;
                }
            }
            graphics.setFont(this.small);
            final int n3 = height + graphics.getFontMetrics().getHeight();
            graphics.drawString(string, n, n2 + n3);
            graphics.setColor(this.color);
            graphics.setFont(this.tiny);
            final int n4 = n3 + graphics.getFontMetrics().getHeight();
            graphics.drawString(field5, n, n2 + n4);
            if (this.width == 0) {
                final int stringWidth = this.fontmetrics.stringWidth(field);
                final int n5 = (int)Math.round(this.fontmetrics.stringWidth(string) * 0.9);
                this.width = ((stringWidth > n5) ? stringWidth : n5) + this.ticker.space;
            }
            if (this.height == 0) {
                this.height = n4 + this.ticker.space;
            }
        }
        else {
            this.width = 0;
            this.height = 0;
        }
        if (b) {
            return this.width;
        }
        return this.height;
    }
    
    private int drawStyle8(final Graphics graphics, final int n, final int n2, final boolean b) {
        final String field = this.getField("NAME");
        final String field2 = this.getField("TREND", " ");
        final String field3 = this.getField("VALUE3", "-");
        final String field4 = this.getField("VALTM3", "--;--");
        final String string = field3 + " / " + this.getField("CHGABS", "-") + " / " + this.getField("CHGREL", "-");
        if (field != null) {
            graphics.setColor(this.color);
            graphics.setFont(this.normal);
            final int height = graphics.getFontMetrics().getHeight();
            graphics.drawString(field, n, n2 + height);
            switch (field2.charAt(0)) {
                case '+': {
                    graphics.setColor(this.ticker.more);
                    break;
                }
                case '-': {
                    graphics.setColor(this.ticker.less);
                    break;
                }
                case '=': {
                    graphics.setColor(this.ticker.same);
                    break;
                }
                default: {
                    graphics.setColor(this.color);
                    break;
                }
            }
            graphics.setFont(this.small);
            final int n3 = height + graphics.getFontMetrics().getHeight();
            graphics.drawString(string, n, n2 + n3);
            graphics.setColor(this.color);
            graphics.setFont(this.tiny);
            final int n4 = n3 + graphics.getFontMetrics().getHeight();
            graphics.drawString(field4, n, n2 + n4);
            if (this.width == 0) {
                final int stringWidth = this.fontmetrics.stringWidth(field);
                final int n5 = (int)Math.round(this.fontmetrics.stringWidth(string) * 0.9);
                this.width = ((stringWidth > n5) ? stringWidth : n5) + this.ticker.space;
            }
            if (this.height == 0) {
                this.height = n4 + this.ticker.space;
            }
        }
        else {
            this.width = 0;
            this.height = 0;
        }
        if (b) {
            return this.width;
        }
        return this.height;
    }
    
    private int drawStyle9(final Graphics graphics, final int n, final int n2, final boolean b) {
        final String field = this.getField("NAME");
        final String field2 = this.getField("TREND", " ");
        final String field3 = this.getField("MID", "-");
        final String field4 = this.getField("MTIME", "--;--");
        final String string = field3 + " / " + this.getField("CHGABS", "-") + " / " + this.getField("CHGREL", "-");
        if (field != null) {
            graphics.setColor(this.color);
            graphics.setFont(this.normal);
            final int height = graphics.getFontMetrics().getHeight();
            graphics.drawString(field, n, n2 + height);
            switch (field2.charAt(0)) {
                case '+': {
                    graphics.setColor(this.ticker.more);
                    break;
                }
                case '-': {
                    graphics.setColor(this.ticker.less);
                    break;
                }
                case '=': {
                    graphics.setColor(this.ticker.same);
                    break;
                }
                default: {
                    graphics.setColor(this.color);
                    break;
                }
            }
            graphics.setFont(this.small);
            final int n3 = height + graphics.getFontMetrics().getHeight();
            graphics.drawString(string, n, n2 + n3);
            graphics.setColor(this.color);
            graphics.setFont(this.tiny);
            final int n4 = n3 + graphics.getFontMetrics().getHeight();
            graphics.drawString(field4, n, n2 + n4);
            if (this.width == 0) {
                final int stringWidth = this.fontmetrics.stringWidth(field);
                final int n5 = (int)Math.round(this.fontmetrics.stringWidth(string) * 0.9);
                this.width = ((stringWidth > n5) ? stringWidth : n5) + this.ticker.space;
            }
            if (this.height == 0) {
                this.height = n4 + this.ticker.space;
            }
        }
        else {
            this.width = 0;
            this.height = 0;
        }
        if (b) {
            return this.width;
        }
        return this.height;
    }
    
    private int drawStyle10(final Graphics graphics, final int n, final int n2, final boolean b) {
        final String field = this.getField("NAME");
        final String field2 = this.getField("SYMB", " ");
        final String field3 = this.getField("VALUE3", "-");
        final String field4 = this.getField("VALTM3", "--;--");
        final String field5 = this.getField("CHGABS", "-");
        final String field6 = this.getField("CHGREL", "-");
        final String field7 = this.getField("TREND", " ");
        final String string = field + " (" + field2 + ") ";
        final String string2 = "A: " + field3 + " / " + field5 + " / " + field6;
        if (field != null) {
            graphics.setColor(this.color);
            graphics.setFont(this.normal);
            final int height = graphics.getFontMetrics().getHeight();
            graphics.drawString(string, n, n2 + height);
            switch (field7.charAt(0)) {
                case '+': {
                    graphics.setColor(this.ticker.more);
                    break;
                }
                case '-': {
                    graphics.setColor(this.ticker.less);
                    break;
                }
                case '=': {
                    graphics.setColor(this.ticker.same);
                    break;
                }
                default: {
                    graphics.setColor(this.color);
                    break;
                }
            }
            graphics.setFont(this.small);
            final int n3 = height + graphics.getFontMetrics().getHeight();
            graphics.drawString(string2, n, n2 + n3);
            graphics.setColor(this.color);
            graphics.setFont(this.tiny);
            final int n4 = n3 + graphics.getFontMetrics().getHeight();
            graphics.drawString(field4, n, n2 + n4);
            if (this.width == 0) {
                final int stringWidth = this.fontmetrics.stringWidth(field);
                final int n5 = (int)Math.round(this.fontmetrics.stringWidth(string2) * 0.9);
                this.width = ((stringWidth > n5) ? stringWidth : n5) + this.ticker.space;
            }
            if (this.height == 0) {
                this.height = n4 + this.ticker.space;
            }
        }
        else {
            this.width = 0;
            this.height = 0;
        }
        if (b) {
            return this.width;
        }
        return this.height;
    }
}
