import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends Rectangle
{
    private String[] case;
    private Image for;
    private Graphics new;
    private String byte;
    private int a;
    private Font do;
    private int int;
    private int try;
    Component if;
    
    public c(final Component if1) {
        this.case = new String[20];
        this.if = if1;
    }
    
    public int a(final Graphics graphics, final String s, final Font font, final int n) {
        this.if(graphics, s, font, n);
        return this.try;
    }
    
    private int if(final Graphics graphics, final String s, final Font do1, final int int1) {
        this.byte = s.trim();
        this.do = do1;
        this.int = int1;
        final FontMetrics fontMetrics = graphics.getFontMetrics(do1);
        this.a = 0;
        this.case[0] = "";
        for (int i = 0; i <= s.length(); ++i) {
            String substring = "";
            try {
                substring = s.substring(i, i + 1);
            }
            catch (StringIndexOutOfBoundsException ex) {}
            if (substring != "|") {
                final String[] case1 = this.case;
                final int a = this.a;
                case1[a] = String.valueOf(String.valueOf(case1[a])).concat(String.valueOf(String.valueOf(substring)));
            }
            if (fontMetrics.stringWidth(this.case[this.a]) >= int1 - 10) {
                final int lastIndex = this.case[this.a].lastIndexOf(" ");
                String substring2 = "";
                if (lastIndex != -1) {
                    try {
                        substring2 = this.case[this.a].substring(lastIndex + 1);
                        this.case[this.a] = this.case[this.a].substring(0, lastIndex);
                        substring2.trim();
                    }
                    catch (StringIndexOutOfBoundsException ex2) {}
                }
                ++this.a;
                this.case[this.a] = substring2;
            }
            this.try = fontMetrics.getHeight() * (this.a + 2) + 6;
        }
        return this.a;
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final String s, final Font font, final int n3, final Color color, final Color color2) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(font);
        this.a = this.if(graphics, s, font, n3);
        if (this.for != null) {
            graphics.drawImage(this.for, 0, 0, this.if);
        }
        graphics.setColor(Color.darkGray);
        graphics.fillRoundRect(n + 3, n2 + 3, this.int, this.try, 10, 10);
        graphics.setColor(color);
        graphics.fillRoundRect(n, n2, this.int, this.try, 10, 10);
        graphics.setColor(color2);
        graphics.setFont(font);
        for (int i = 0; i <= this.a; ++i) {
            graphics.drawString(this.case[i], n + 7, n2 + ((i + 1) * fontMetrics.getHeight() + 5));
        }
    }
}
