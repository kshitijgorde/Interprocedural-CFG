import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

public class BitLine extends JComponent implements BitSource, UpdateEvent
{
    private static final int TERM_WIDTH = 6;
    private boolean _boolStartTerm;
    private boolean _boolEndTerm;
    private boolean _boolSelected;
    private boolean _boolUpDown;
    private boolean _boolLeftCorner;
    private boolean _boolRightCorner;
    private BitSource _bitInput;
    private BitSource _bitOutput;
    private UpdateEvent _downStream;
    
    public BitLine(final BitSource bitInput, final BitSource bitOutput, final boolean bStartTerm, final boolean bEndTerm) {
        this._bitInput = bitInput;
        this._bitOutput = bitOutput;
        this._boolStartTerm = bStartTerm;
        this._boolEndTerm = bEndTerm;
        this._boolUpDown = false;
        this._boolLeftCorner = false;
        this._boolRightCorner = false;
        this._downStream = null;
        if (this._bitInput == null || this._bitOutput == null) {
            return;
        }
        final Point pointInput = this._bitInput.getTerminal();
        final Point pointOutput = this._bitOutput.getTerminal();
        this.setBounds(Math.min(pointInput.x, pointOutput.x), Math.min(pointInput.y, pointOutput.y) - 3, Math.max(Math.abs(pointOutput.x - pointInput.x), 6), Math.abs(pointOutput.y - pointInput.y) + 6);
        this._bitInput.setDownStream(this);
    }
    
    public BitLine(final BitSource bitInput, final BitSource bitOutput, final boolean bStartTerm, final boolean bEndTerm, final boolean bUpDown, final boolean bLeftCorner, final boolean bRightCorner) {
        this(bitInput, bitOutput, bStartTerm, bEndTerm);
        this._boolUpDown = bUpDown;
        this._boolLeftCorner = bLeftCorner;
        this._boolRightCorner = bRightCorner;
    }
    
    public boolean isSelected() {
        return this._boolSelected;
    }
    
    public void setSelected(final boolean selected) {
        if (this._boolSelected == selected) {
            return;
        }
        this._boolSelected = selected;
        this.update();
    }
    
    public int getValue() {
        return this.isSelected() ? 1 : 0;
    }
    
    public void setValue(final int value) {
        this.setSelected(value != 0);
    }
    
    public Point getTerminal() {
        return this._bitOutput.getTerminal();
    }
    
    public void setTerminal(final Point point) {
    }
    
    public void setDownStream(final UpdateEvent downStream) {
        this._downStream = downStream;
    }
    
    protected void paintComponent(final Graphics g) {
        if (this._bitInput == null || this._bitOutput == null) {
            return;
        }
        final Point pointInput = this._bitInput.getTerminal();
        final Point pointOutput = this._bitOutput.getTerminal();
        g.setColor(this._boolSelected ? Color.green : Color.black);
        if (this.getLocation().x != Math.min(pointInput.x, pointOutput.x) || this.getLocation().y != Math.min(pointInput.y, pointOutput.y) - 3) {
            this.setLocation(Math.min(pointInput.x, pointOutput.x), Math.min(pointInput.y, pointOutput.y) - 3);
        }
        if (this.getSize().width != Math.max(Math.abs(pointOutput.x - pointInput.x), 6) || this.getSize().height != Math.abs(pointOutput.y - pointInput.y) + 6) {
            this.setSize(Math.max(Math.abs(pointOutput.x - pointInput.x), 6), Math.abs(pointOutput.y - pointInput.y) + 6);
        }
        int x1 = (pointInput.x <= pointOutput.x) ? 0 : (this.getWidth() - 6);
        int y1 = (pointInput.y <= pointOutput.y) ? 0 : (this.getHeight() - 6);
        int x2 = (pointInput.x <= pointOutput.x) ? (this.getWidth() - 6) : 0;
        int y2 = (pointInput.y <= pointOutput.y) ? (this.getHeight() - 6) : 0;
        final int halfx = (this.getWidth() - 6) / 2;
        final int halfy = (this.getHeight() - 6) / 2;
        if (this._boolStartTerm) {
            g.fillOval(x1, y1, 6, 6);
            x1 += 2;
            y1 += 2;
        }
        if (this._boolEndTerm) {
            g.fillOval(x2, y2, 6, 6);
            x2 += 2;
            y2 += 2;
        }
        if (this._boolUpDown) {
            if (!this._boolStartTerm) {
                x1 += 2;
                if (this._boolLeftCorner) {
                    y1 += 2;
                }
                else if (y1 > 0) {
                    y1 += 6;
                }
            }
            if (!this._boolEndTerm) {
                x2 += 2;
                if (this._boolRightCorner) {
                    y2 += 2;
                }
                else if (y2 > 0) {
                    y2 += 6;
                }
            }
            g.drawLine(x1, y1, x1, halfy + 1);
            g.drawLine(x1 + 1, y1, x1 + 1, halfy + 1);
            g.drawLine((x1 > x2) ? x2 : x1, halfy, (x1 > x2) ? (x1 + 1) : (x2 + 1), halfy);
            g.drawLine((x1 > x2) ? x2 : x1, halfy + 1, (x1 > x2) ? (x1 + 1) : (x2 + 1), halfy + 1);
            g.drawLine(x2, halfy, x2, y2);
            g.drawLine(x2 + 1, halfy, x2 + 1, y2);
        }
        else {
            if (!this._boolStartTerm) {
                y1 += 2;
                if (this._boolLeftCorner) {
                    x1 += 2;
                }
                else if (x1 > 0) {
                    x1 += 6;
                }
            }
            if (!this._boolEndTerm) {
                y2 += 2;
                if (this._boolRightCorner) {
                    x2 += 2;
                }
                else if (x2 > 0) {
                    x2 += 6;
                }
            }
            g.drawLine(x1, y1, halfx + 1, y1);
            g.drawLine(x1, y1 + 1, halfx + 1, y1 + 1);
            g.drawLine(halfx, (y1 < y2) ? y1 : y2, halfx, (y1 > y2) ? (y1 + 1) : (y2 + 1));
            g.drawLine(halfx + 1, (y1 < y2) ? y1 : y2, halfx + 1, (y1 > y2) ? (y1 + 1) : (y2 + 1));
            g.drawLine(halfx, y2, x2, y2);
            g.drawLine(halfx, y2 + 1, x2, y2 + 1);
        }
    }
    
    public void update() {
        if (this._bitInput != null && this._bitInput.isSelected() != this._boolSelected) {
            this._boolSelected = this._bitInput.isSelected();
        }
        if (this._bitOutput != null && this._bitOutput.isSelected() != this._boolSelected) {
            this._bitOutput.setSelected(this._boolSelected);
        }
        if (this._downStream != null) {
            this._downStream.update();
        }
        this.repaint();
    }
}
