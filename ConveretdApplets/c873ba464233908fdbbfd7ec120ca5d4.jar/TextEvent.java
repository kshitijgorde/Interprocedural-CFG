import java.awt.Rectangle;
import java.awt.Component;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public final class TextEvent extends AnimationEvent
{
    private int m_textWidth;
    private int m_textHeight;
    private int m_textDescent;
    private int m_textAscent;
    private Font m_font;
    private String m_text;
    private int m_position;
    private int m_direction;
    private int m_yPos;
    
    public void drawFrame(final Graphics graphics) {
        int n;
        if (this.m_direction == 0) {
            n = (int)(-this.m_textWidth + super.m_xOffset / 16L);
        }
        else {
            n = (int)(super.m_animation.m_size.width - super.m_xOffset / 16L);
        }
        graphics.setColor(super.m_colour);
        graphics.setFont(this.m_font);
        graphics.drawString(this.m_text, n, this.m_yPos);
    }
    
    public void initFromParams(final String s) {
        super.initFromParams(s);
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        final String nextToken = stringTokenizer.nextToken();
        final int index = nextToken.indexOf(58);
        String s2 = "ARIAL";
        int intValue = 72;
        int n = 0;
        if (index != -1) {
            final String upperCase = nextToken.substring(0, index).toUpperCase();
            final String substring = nextToken.substring(index + 1);
            if (!upperCase.equals("TYPE") || !substring.equals("TEXT")) {
                return;
            }
        }
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken2 = stringTokenizer.nextToken();
            final int index2 = nextToken2.indexOf(58);
            if (index2 != -1) {
                final String upperCase2 = nextToken2.substring(0, index2).toUpperCase();
                final String substring2 = nextToken2.substring(index2 + 1);
                if (upperCase2.equals("TEXT")) {
                    this.m_text = substring2;
                    this.m_text = this.m_text.replaceAll("&#semi", ";");
                }
                else if (upperCase2.equals("FONT")) {
                    s2 = substring2;
                }
                else if (upperCase2.equals("STYLE")) {
                    if (substring2.equals("BOLD")) {
                        ++n;
                    }
                    else if (substring2.equals("ITALIC")) {
                        n += 2;
                    }
                    else {
                        if (!substring2.equals("BOLDITALIC")) {
                            continue;
                        }
                        n += 3;
                    }
                }
                else if (upperCase2.equals("SIZE")) {
                    intValue = Integer.valueOf(substring2);
                }
                else if (upperCase2.equals("POSITION")) {
                    if (substring2.equals("TOP")) {
                        this.m_position = 0;
                    }
                    else if (substring2.equals("MIDDLE")) {
                        this.m_position = 1;
                    }
                    else {
                        if (!substring2.equals("BOTTOM")) {
                            continue;
                        }
                        this.m_position = 2;
                    }
                }
                else {
                    if (!upperCase2.equals("DIRECTION")) {
                        continue;
                    }
                    if (substring2.equals("RIGHTWARDS")) {
                        this.m_direction = 0;
                    }
                    else {
                        if (!substring2.equals("LEFTWARDS")) {
                            continue;
                        }
                        this.m_direction = 1;
                    }
                }
            }
        }
        this.m_font = new Font(s2, n, intValue);
        final Graphics graphics = super.m_component.createImage(1, 1).getGraphics();
        graphics.setFont(this.m_font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.m_textWidth = fontMetrics.stringWidth(this.m_text);
        this.m_textAscent = fontMetrics.getMaxAscent();
        this.m_textDescent = fontMetrics.getMaxDescent();
        if (this.m_position == 0) {
            this.m_yPos = this.m_textAscent;
        }
        else if (this.m_position == 1) {
            this.m_yPos = this.m_textAscent + (super.m_animation.m_size.height - this.m_textAscent - this.m_textDescent) / 2;
        }
        else if (this.m_position == 2) {
            this.m_yPos = super.m_animation.m_size.height - this.m_textDescent;
        }
        graphics.dispose();
    }
    
    public TextEvent(final Component component, final Animation animation) {
        super(component, animation);
        this.m_position = 0;
        this.m_direction = 0;
    }
    
    public String getUrl(final int n, final int n2) {
        int n3 = 0;
        int n4;
        if (this.m_direction == 0) {
            n4 = (int)(-this.m_textWidth + super.m_xOffset / 16L);
        }
        else {
            n4 = (int)(super.m_animation.m_size.width - super.m_xOffset / 16L);
        }
        if (this.m_position == 0) {
            n3 = 0;
        }
        else if (this.m_position == 1) {
            n3 = (super.m_animation.m_size.height - this.m_textAscent - this.m_textDescent) / 2;
        }
        else if (this.m_position == 2) {
            n3 = super.m_animation.m_size.height - this.m_textAscent - this.m_textDescent;
        }
        if (new Rectangle(n4, n3, this.m_textWidth, this.m_textAscent + this.m_textDescent).inside(n, n2)) {
            return super.m_url;
        }
        return null;
    }
    
    public void incFrame() {
        super.incFrame();
        if (this.m_direction == 0) {
            if ((int)(-this.m_textWidth + super.m_xOffset / 16L) > super.m_animation.m_size.width) {
                super.m_xOffset = 0L;
            }
        }
        else if ((int)(super.m_animation.m_size.width - super.m_xOffset / 16L) < -this.m_textWidth) {
            super.m_xOffset = 0L;
        }
    }
}
