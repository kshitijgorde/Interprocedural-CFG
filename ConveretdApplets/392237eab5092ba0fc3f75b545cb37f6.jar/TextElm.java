import java.awt.Checkbox;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class TextElm extends CircuitElm
{
    String text;
    Vector lines;
    int size;
    final int FLAG_CENTER = 1;
    final int FLAG_BAR = 2;
    
    public TextElm(final int n, final int n2) {
        super(n, n2);
        this.text = "hello";
        (this.lines = new Vector()).add(this.text);
        this.size = 24;
    }
    
    public TextElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.size = new Integer(stringTokenizer.nextToken());
        this.text = stringTokenizer.nextToken();
        while (stringTokenizer.hasMoreTokens()) {
            this.text = this.text + ' ' + stringTokenizer.nextToken();
        }
        this.split();
    }
    
    void split() {
        this.lines = new Vector();
        final StringBuffer sb = new StringBuffer(this.text);
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '\\') {
                sb.deleteCharAt(i);
                if (sb.charAt(i) == 'n') {
                    this.lines.add(sb.substring(0, i));
                    sb.delete(0, i + 1);
                    i = -1;
                }
            }
        }
        this.lines.add(sb.toString());
    }
    
    String dump() {
        return super.dump() + " " + this.size + " " + this.text;
    }
    
    int getDumpType() {
        return 120;
    }
    
    void drag(final int x, final int n) {
        this.x = x;
        this.y = n;
        this.x2 = x + 16;
        this.y2 = n;
    }
    
    void draw(final Graphics graphics) {
        graphics.setColor(this.needsHighlight() ? TextElm.selectColor : TextElm.lightGrayColor);
        graphics.setFont(new Font("SansSerif", 0, this.size));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n = -1;
        for (int i = 0; i != this.lines.size(); ++i) {
            final int stringWidth = fontMetrics.stringWidth(this.lines.elementAt(i));
            if (stringWidth > n) {
                n = stringWidth;
            }
        }
        int y = this.y;
        this.setBbox(this.x, this.y, this.x, this.y);
        for (int j = 0; j != this.lines.size(); ++j) {
            final String s = this.lines.elementAt(j);
            if ((this.flags & 0x1) != 0x0) {
                this.x = (TextElm.sim.winSize.width - fontMetrics.stringWidth(s)) / 2;
            }
            graphics.drawString(s, this.x, y);
            if ((this.flags & 0x2) != 0x0) {
                final int n2 = y - fontMetrics.getAscent();
                graphics.drawLine(this.x, n2, this.x + fontMetrics.stringWidth(s) - 1, n2);
            }
            this.adjustBbox(this.x, y - fontMetrics.getAscent(), this.x + fontMetrics.stringWidth(s), y + fontMetrics.getDescent());
            y += fontMetrics.getHeight();
        }
        this.x2 = this.boundingBox.x + this.boundingBox.width;
        this.y2 = this.boundingBox.y + this.boundingBox.height;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            final EditInfo editInfo = new EditInfo("Text", 0.0, -1.0, -1.0);
            editInfo.text = this.text;
            return editInfo;
        }
        if (n == 1) {
            return new EditInfo("Size", this.size, 5.0, 100.0);
        }
        if (n == 2) {
            final EditInfo editInfo2 = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo2.checkbox = new Checkbox("Center", (this.flags & 0x1) != 0x0);
            return editInfo2;
        }
        if (n == 3) {
            final EditInfo editInfo3 = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo3.checkbox = new Checkbox("Draw Bar On Top", (this.flags & 0x2) != 0x0);
            return editInfo3;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.text = editInfo.textf.getText();
            this.split();
        }
        if (n == 1) {
            this.size = (int)editInfo.value;
        }
        if (n == 3) {
            if (editInfo.checkbox.getState()) {
                this.flags |= 0x2;
            }
            else {
                this.flags &= 0xFFFFFFFD;
            }
        }
        if (n == 2) {
            if (editInfo.checkbox.getState()) {
                this.flags |= 0x1;
            }
            else {
                this.flags &= 0xFFFFFFFE;
            }
        }
    }
    
    boolean isCenteredText() {
        return (this.flags & 0x1) != 0x0;
    }
    
    void getInfo(final String[] array) {
        array[0] = this.text;
    }
    
    int getPostCount() {
        return 0;
    }
}
