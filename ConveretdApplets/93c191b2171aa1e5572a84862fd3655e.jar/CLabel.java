import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

// 
// Decompiled by Procyon v0.5.30
// 

public class CLabel extends JLabel
{
    public static final int DEFAULT_SIZE = 14;
    public static final Color DEFAULT_COLOR;
    public static final int ROTATE_NONE = 0;
    public static final int ROTATE_CW = 1;
    public static final int ROTATE_CCW = 2;
    private Color _color;
    private int _rotate;
    
    public CLabel(final String text, final int x, final int y, final int size, final boolean bold, final Color color) {
        super(text);
        this.setBounds(x, y, 1, 1);
        this.setFont(new Font("Dialog", (int)(bold ? 1 : 0), size));
        this.setForeground(this._color = color);
        this._rotate = 0;
    }
    
    public CLabel(final String text, final int x, final int y, final int size, final Color color) {
        this(text, x, y, size, false, color);
    }
    
    public CLabel(final String text, final int x, final int y, final int size) {
        this(text, x, y, size, false, CLabel.DEFAULT_COLOR);
    }
    
    public CLabel(final String text, final int x, final int y) {
        this(text, x, y, 14, false, CLabel.DEFAULT_COLOR);
    }
    
    public CLabel() {
        this("", 0, 0, 14, false, CLabel.DEFAULT_COLOR);
    }
    
    public void setRotation(final int rotate) {
        this._rotate = rotate;
    }
    
    public void paint(final Graphics g) {
        final FontMetrics fm = g.getFontMetrics(this.getFont());
        int height = (fm.getHeight() == 0) ? 1 : fm.getHeight();
        final int width = (fm.stringWidth(this.getText()) == 0) ? 1 : fm.stringWidth(this.getText());
        if (this._rotate != 0) {
            height = width;
        }
        if (this.getWidth() != width || this.getHeight() != height) {
            this.setSize(width, height);
        }
        final Graphics2D g2d = (Graphics2D)g;
        switch (this._rotate) {
            case 1: {
                g2d.rotate(1.5707963267948966);
                g2d.translate(0, -width);
                break;
            }
            case 2: {
                g2d.rotate(-1.5707963267948966);
                g2d.translate(-height, 0);
                break;
            }
        }
        super.paint(g2d);
    }
    
    static {
        DEFAULT_COLOR = Color.BLACK;
    }
}
