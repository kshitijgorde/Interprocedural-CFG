import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class a extends Canvas implements MouseListener
{
    private final esChat a;
    protected final Color[] b;
    TextArea c;
    
    a(final esChat a, final TextArea c) {
        this.a = a;
        this.c = c;
        this.b = new Color[] { Color.white, Color.black, new Color(0, 0, 127), new Color(0, 143, 0), new Color(255, 0, 0), new Color(127, 0, 0), new Color(158, 0, 158), new Color(255, 127, 0), new Color(255, 255, 0), new Color(0, 255, 0), new Color(0, 143, 143), new Color(0, 255, 255), new Color(0, 0, 255), new Color(255, 0, 255), new Color(127, 127, 127), new Color(206, 206, 206) };
        this.addMouseListener(this);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(400, 9);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(400, 9);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int m = fb.m;
        final String text = this.c.getText();
        char c = '\u0003';
        if (m == 0) {
            if (text.length() > 1) {
                final char char1 = text.charAt(text.length() - 1);
                if (m == 0 && char1 != '\u0003') {
                    final char char2 = text.charAt(text.length() - 2);
                    if (m == 0 && char2 == '\u0003') {
                        goto Label_0066;
                    }
                    c = char2;
                }
                else {
                    c = char1;
                    if (m != 0) {
                        goto Label_0074;
                    }
                }
            }
            this.c.append(new StringBuffer().append(c).append(mouseEvent.getX() / (this.getSize().width / 16)).toString());
            this.c.setCaretPosition(this.c.getText().length());
            this.c.requestFocus();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        final int m = fb.m;
        final Dimension size = this.getSize();
        final int n = size.width / 16;
        final int height = size.height;
        int i = 0;
    Block_2:
        while (true) {
            graphics.setColor(this.b[i]);
            graphics.fill3DRect(i * n, 0, n, height, true);
            ++i;
            while (i >= 16) {
                graphics.setColor(Color.white);
                graphics.draw3DRect(0, 0, size.width, size.height, false);
                if (m == 0) {
                    break Block_2;
                }
            }
        }
    }
}
