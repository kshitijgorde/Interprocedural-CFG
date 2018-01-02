import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class TickerText extends TickerData
{
    private String text;
    private Hashtable chars;
    private Font normal;
    private FontMetrics fontmetrics;
    private Color color;
    private Color color1;
    private int style;
    
    public TickerText(final TickerInfo ticker, final String s) {
        this.text = null;
        this.normal = null;
        this.fontmetrics = null;
        this.color = null;
        this.color1 = null;
        this.style = 0;
        this.ticker = ticker;
        this.parse(s);
        this.createCharTable();
        final Graphics graphics = ticker.app.createImage(1, 1).getGraphics();
        this.fontname = this.getField("FONT", ticker.fontname);
        try {
            this.fontsize = Integer.parseInt(this.getField("SIZE"));
        }
        catch (NumberFormatException ex) {
            this.fontsize = ticker.fontsize;
        }
        graphics.setFont(this.normal = new Font(this.fontname, 1, this.fontsize));
        this.fontmetrics = graphics.getFontMetrics();
        graphics.dispose();
        this.descent = this.fontmetrics.getDescent();
        try {
            this.color = new Color(Integer.parseInt(this.getField("COLOR"), 16));
        }
        catch (NumberFormatException ex2) {
            this.color = ticker.fgColor;
        }
        try {
            this.color1 = new Color(Integer.parseInt(this.getField("COLOR1"), 16));
        }
        catch (NumberFormatException ex3) {
            this.color1 = ticker.color1;
        }
        try {
            this.style = Integer.parseInt(this.getField("STYLE"));
        }
        catch (NumberFormatException ex4) {}
        this.text = this.getField("TEXT", "no text?");
        this.text = this.unHtml(this.text);
        this.link = this.getField("LINK", "no link?");
        this.target = this.getField("TARGET", "_blank");
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
            default: {
                System.out.println("Unknown style: " + this.style);
                return 0;
            }
        }
    }
    
    protected int drawStyle0(final Graphics graphics, final int n, final int n2, final boolean b) {
        if (this.text != null) {
            graphics.setColor(this.color);
            graphics.setFont(this.normal);
            graphics.drawString(this.text, n, n2 + this.fontmetrics.getHeight());
            if (this.width == 0) {
                this.width = this.fontmetrics.stringWidth(this.text) + this.ticker.space;
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
    
    protected int drawStyle1(final Graphics graphics, final int n, final int n2, final boolean b) {
        if (this.text != null) {
            int index = this.text.indexOf(" ");
            if (index < 0) {
                index = 0;
            }
            graphics.setColor(this.color);
            graphics.setFont(this.normal);
            final int height = this.fontmetrics.getHeight();
            graphics.drawString(this.text.substring(0, index), n, n2 + height);
            graphics.setColor(this.color1);
            graphics.drawString(this.text.substring(index), n + this.fontmetrics.stringWidth(this.text.substring(0, index)), n2 + height);
            if (this.width == 0) {
                this.width = this.fontmetrics.stringWidth(this.text) + this.ticker.space;
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
    
    protected String unHtml(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case '&': {
                    int n;
                    for (n = ++i; s.charAt(i) != ';'; ++i) {}
                    sb.append(this.parseSpecial(s.substring(n, i)));
                    break;
                }
                default: {
                    sb.append(s.charAt(i));
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    protected String parseSpecial(final String s) {
        if (s.substring(0, 1).equals("#")) {
            final String substring = s.substring(1, s.length());
            int int1;
            try {
                int1 = Integer.parseInt(substring);
                if (int1 > 65535) {
                    throw new NumberFormatException();
                }
            }
            catch (NumberFormatException ex) {
                return "&" + s + ";";
            }
            return new Character((char)int1).toString();
        }
        final String s2 = this.chars.get(s);
        if (s2 == null) {
            return "&" + s + ";";
        }
        return s2;
    }
    
    protected void createCharTable() {
        (this.chars = new Hashtable()).put("nbsp", " ");
        this.chars.put("quot", "\"");
        this.chars.put("amp", "&");
        this.chars.put("lt", "<");
        this.chars.put("gt", ">");
        this.chars.put("Auml", "\u00c4");
        this.chars.put("auml", "\u00e4");
        this.chars.put("Ouml", "\u00d6");
        this.chars.put("ouml", "\u00f6");
        this.chars.put("Uuml", "\u00dc");
        this.chars.put("uuml", "\u00fc");
        this.chars.put("szlig", "\u00df");
        this.chars.put("reg", "®");
        this.chars.put("copy", "©");
    }
}
