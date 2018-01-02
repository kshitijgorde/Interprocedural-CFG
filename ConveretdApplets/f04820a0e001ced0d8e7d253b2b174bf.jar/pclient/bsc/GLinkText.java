// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import com.pchat.sc.WindowUtil;
import java.awt.Graphics;
import java.awt.Color;
import pclient.shd.Config;
import java.awt.Font;

public class GLinkText extends GBasicItem implements ActionCommand
{
    public static final int REGULAR = 0;
    public int type;
    public String string;
    public Font font;
    private String link;
    private boolean drawUnderline;
    private Config paraConf;
    
    public GLinkText(final Config paraConf, final int type, final String string, final Color color, final Font font, final String s) {
        super(color);
        this.link = null;
        this.drawUnderline = true;
        this.paraConf = paraConf;
        this.type = type;
        this.string = string;
        this.font = font;
        this.link = this.fixLink(s);
        super.actionCom = this;
    }
    
    public String toString() {
        return this.getBounds() + "string=" + this.string + "," + "link=" + this.link;
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        graphics.setFont(this.font);
        graphics.setColor(super.pcolor);
        graphics.drawString(this.string, n, n2 + this.getBaseline(this.font));
        String s = this.string;
        if (this.isLastExtra(this.string)) {
            s = this.string.substring(0, this.string.length() - 1);
        }
        if (this.drawUnderline) {
            final int calculateLength = this.calculateLength(this.font, s);
            final int n3 = n2 + this.calculateFontHeight(this.font) - 1;
            graphics.drawLine(n, n3, n + calculateLength, n3);
        }
    }
    
    public void performAction() {
        if (this.link == null) {
            return;
        }
        WindowUtil.loadURL(this.paraConf.getApplet(), this.link);
    }
    
    private String fixLink(final String s) {
        String s2 = s.trim();
        if (this.isLastExtra(s2)) {
            s2 = s2.substring(0, s2.length() - 1);
        }
        return s2;
    }
    
    private boolean isLastExtra(final String s) {
        return s.endsWith(".") || s.endsWith(",") || s.endsWith("?") || s.endsWith(" ") || s.endsWith(";") || s.endsWith("!");
    }
}
