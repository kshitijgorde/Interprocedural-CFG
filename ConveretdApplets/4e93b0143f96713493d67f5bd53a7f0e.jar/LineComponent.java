import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Point;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

public class LineComponent extends JComponent
{
    private static final int MIN_SIZE = 4;
    private static final int STATE_NIL = 0;
    private static final int STATE_BEG = 1;
    private static final int STATE_MID = 2;
    private static final int STATE_END = 3;
    private static final int STATE_DONE = 4;
    private Point _start;
    private Point _end;
    private Timer _timerTrans;
    private boolean _boolLeftToRight;
    private int _intState;
    private int _width;
    private int _height;
    private char _cLeft;
    private char _cRight;
    private int _x1;
    private int _y1;
    private int _x2;
    private int _y2;
    private int _h13;
    private int _h23;
    private int _x13;
    private int _y13;
    private int _x23;
    private int _y23;
    private JLabel _label;
    private static int LABEL_WIDTH;
    private static int LABEL_HEIGHT;
    private LinkedList _que;
    
    public LineComponent(final Point start, final Point end, final JLabel label) {
        this._start = start;
        this._end = end;
        this._que = new LinkedList();
        this._boolLeftToRight = false;
        this._intState = 0;
        if (label.getText().length() == 3) {
            this._cLeft = label.getText().charAt(0);
            this._cRight = label.getText().charAt(2);
        }
        else {
            this._cLeft = NetManager.LOST_PACKET;
            this._cRight = NetManager.LOST_PACKET;
        }
        this._width = 4 + ((this._end.x - this._start.x < 4) ? 0 : (this._end.x - this._start.x));
        this._height = 4 + ((Math.abs(this._end.y - this._start.y) < 4) ? 0 : Math.abs(this._end.y - this._start.y));
        this.setBounds(this._start.x, Math.min(this._start.y, this._end.y), this._width, this._height);
        this.setOpaque(false);
        this._timerTrans = new Timer(200, new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                LineComponent.this.timerTrans_Timer();
            }
        });
        final int labelX = (this._start.x + this._end.x - LineComponent.LABEL_WIDTH) / 2 - ((this._start.x == this._end.x) ? 20 : 0);
        final int labelY = (this._start.y + this._end.y - LineComponent.LABEL_HEIGHT) / 2 - ((this._start.x == this._end.x) ? 0 : 20);
        (this._label = label).setBounds(labelX, labelY, LineComponent.LABEL_WIDTH, LineComponent.LABEL_HEIGHT);
        this._label.setHorizontalAlignment(0);
        this._label.setHorizontalTextPosition(0);
        this._label.setVerticalAlignment(0);
        this._label.setVerticalTextPosition(0);
        this._label.setForeground(Main.WINDOW_COLOR);
        this._label.setText("");
        this._x1 = ((this._end.x > this._start.x) ? 0 : (this._width - 4));
        this._y1 = ((this._end.y > this._start.y) ? 0 : (this._height - 4));
        this._x2 = ((this._end.x > this._start.x) ? (this._width - 4) : 0);
        this._y2 = ((this._end.y > this._start.y) ? (this._height - 4) : 0);
        this._h13 = this._y1 + (this._y2 - this._y1) * 1 / 3;
        this._h23 = this._y1 + (this._y2 - this._y1) * 2 / 3;
        this._x13 = (this._width - 4) / 3;
        this._y13 = (this._height - 4) / 3;
        this._x23 = (this._width - 4) * 2 / 3;
        this._y23 = (this._height - 4) * 2 / 3;
        if (this._x1 != this._x2) {
            final double m = (this._y2 - this._y1) / (this._x2 - this._x1);
            final double b = (this._y1 - m * this._x1 + (this._y2 - m * this._x2)) / 2.0;
            this._x13 = this._x1 + (this._x2 - this._x1) * 1 / 3;
            this._y13 = (int)(m * this._x13 + b);
            this._x23 = this._x1 + (this._x2 - this._x1) * 2 / 3;
            this._y23 = (int)(m * this._x23 + b);
        }
    }
    
    private void timerTrans_Timer() {
        switch (this._intState++) {
            case 0: {
                break;
            }
            case 1: {
                this._label.setForeground(Color.BLACK);
                break;
            }
            case 2: {
                this._label.setForeground(Color.BLACK);
                break;
            }
            case 3: {
                this._label.setForeground(Color.GREEN);
                break;
            }
            case 4: {
                this._label.setForeground(Color.LIGHT_GRAY);
                break;
            }
            default: {
                if (this._que.size() > 0) {
                    String display = this._que.removeFirst();
                    final char dir = display.charAt(0);
                    display = display.substring(1);
                    this._label.setText(display);
                    this._intState = 1;
                    this._boolLeftToRight = (dir == '>');
                }
                else {
                    this._intState = 0;
                    this._label.setForeground(Main.WINDOW_COLOR);
                    this._timerTrans.stop();
                }
                this.forwardNextPacket();
                break;
            }
        }
        this.repaint();
    }
    
    public void forwardNextPacket() {
        Network net = null;
        if (this._label.getText().equalsIgnoreCase(this.toString()) || this._label.getText().equalsIgnoreCase(this.toRevString())) {
            this._label.setText("");
            return;
        }
        switch (this._boolLeftToRight ? this._cRight : this._cLeft) {
            case 'A': {
                net = (Network)NetManager.getNetworkA();
                break;
            }
            case 'B': {
                net = (Network)NetManager.getNetworkB();
                break;
            }
            case 'C': {
                net = (Network)NetManager.getNetworkC();
                break;
            }
            case 'D': {
                net = (Network)NetManager.getNetworkD();
                break;
            }
            case 'E': {
                net = (Network)NetManager.getNetworkE();
                break;
            }
        }
        if (net == null) {
            return;
        }
        final char cNext = net.forwardPacket(this._label.getText());
        final char cThis = this._boolLeftToRight ? this._cRight : this._cLeft;
        final String sLine = cThis + "." + cNext;
        final LineComponent lineLeftToRight = NetManager.getLine(sLine);
        if (lineLeftToRight != null) {
            lineLeftToRight.setLeftToRight(this._label.getText(), 1);
            return;
        }
        final String sRevLine = "" + cNext + "." + cThis;
        final LineComponent lineRightToLeft = NetManager.getLine(sRevLine);
        if (lineRightToLeft != null) {
            lineRightToLeft.setRightToLeft(this._label.getText(), 1);
        }
    }
    
    public void setRightToLeft(final String display, final int count) {
        if (display.length() < 3) {
            return;
        }
        if (count < 1) {
            return;
        }
        if (this._intState != 0) {
            for (int i = 0; i < count; ++i) {
                this._que.addLast("<" + display);
            }
        }
        else {
            this._label.setText(display);
            this._intState = 1;
            this._boolLeftToRight = false;
            this._timerTrans.start();
            for (int i = 0; i < count - 1; ++i) {
                this._que.addLast("<" + display);
            }
        }
    }
    
    public void setLeftToRight(final String display, final int count) {
        if (display.length() < 3) {
            return;
        }
        if (count < 1) {
            return;
        }
        if (this._intState != 0) {
            for (int i = 0; i < count; ++i) {
                this._que.addLast(">" + display);
            }
        }
        else {
            this._label.setText(display);
            this._intState = 1;
            this._boolLeftToRight = true;
            this._timerTrans.start();
            for (int i = 0; i < count - 1; ++i) {
                this._que.addLast(">" + display);
            }
        }
    }
    
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this._start == null || this._end == null) {
            return;
        }
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke((this._x1 == this._x2 || this._y1 == this._y2) ? 5.0f : 3.0f));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this._x1 == this._x2) {
            g2d.setColor((this._intState > 1 && this._boolLeftToRight) ? Color.GREEN : ((this._intState > 3 && !this._boolLeftToRight) ? Color.GREEN : Color.BLACK));
            g2d.drawLine(this._x1, this._y1, this._x2, this._h13);
            g2d.setColor((this._intState > 2) ? Color.GREEN : Color.BLACK);
            g2d.drawLine(this._x1, this._h13, this._x2, this._h23);
            g2d.setColor((this._intState > 3 && this._boolLeftToRight) ? Color.GREEN : ((this._intState > 1 && !this._boolLeftToRight) ? Color.GREEN : Color.BLACK));
            g2d.drawLine(this._x1, this._h23, this._x2, this._y2);
        }
        else {
            g2d.setColor((this._intState > 1 && this._boolLeftToRight) ? Color.GREEN : ((this._intState > 3 && !this._boolLeftToRight) ? Color.GREEN : Color.BLACK));
            g2d.drawLine(this._x1, this._y1, this._x13, this._y13);
            g2d.setColor((this._intState > 2) ? Color.GREEN : Color.BLACK);
            g2d.drawLine(this._x13, this._y13, this._x23, this._y23);
            g2d.setColor((this._intState > 3 && this._boolLeftToRight) ? Color.GREEN : ((this._intState > 1 && !this._boolLeftToRight) ? Color.GREEN : Color.BLACK));
            g2d.drawLine(this._x23, this._y23, this._x2, this._y2);
        }
    }
    
    public boolean isDone() {
        return this._intState == 0;
    }
    
    public String toString() {
        return "" + this._cLeft + "." + this._cRight;
    }
    
    public String toRevString() {
        return "" + this._cRight + "." + this._cLeft;
    }
    
    static {
        LineComponent.LABEL_WIDTH = 30;
        LineComponent.LABEL_HEIGHT = 20;
    }
}
