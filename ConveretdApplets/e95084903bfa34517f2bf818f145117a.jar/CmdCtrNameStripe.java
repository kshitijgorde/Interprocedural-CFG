import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class CmdCtrNameStripe
{
    private static final Color BG_COLOR;
    private static final Color BUSY_COLOR;
    private static final Color FG_COLOR;
    private static final Color MSG_COLOR;
    private static final int GAP = 10;
    private static final String BUSY = " BUSY";
    private static final String CMDCTR = "CommandCenter";
    private boolean turtleBusy;
    private int busyLeftX;
    private int busyWidth;
    private int cmdctrNameLeftX;
    private int cmdctrNameWidth;
    private int height;
    private int leftX;
    private int msgLeftX;
    private int textOffset;
    private int topY;
    private int turtleNameLeftX;
    private int width;
    private String message;
    private String turtleName;
    
    public CmdCtrNameStripe(final int n, final int n2) {
        this(n, n2, null);
    }
    
    public CmdCtrNameStripe(final int height, final int width, final String s) {
        this.height = height;
        this.width = width;
        this.turtleName = ((s == null) ? "" : s);
        this.leftX = 0;
        this.topY = 0;
        this.turtleBusy = false;
        this.message = null;
        this.cmdctrNameLeftX = -1;
        this.turtleNameLeftX = -1;
        this.textOffset = -1;
    }
    
    public void clearBusy() {
        this.turtleBusy = false;
    }
    
    public void clearMessage() {
        this.message = null;
        this.cmdctrNameLeftX = -1;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.leftX, this.topY, this.width, this.height);
    }
    
    public Dimension getSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void paint(final Graphics graphics) {
        if (this.cmdctrNameLeftX < 0 || this.turtleNameLeftX < 0) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            if (this.textOffset < 0) {
                final int height = fontMetrics.getHeight();
                this.textOffset = (this.height - height) / 2 + height - fontMetrics.getMaxDescent();
                this.busyWidth = fontMetrics.stringWidth(" BUSY");
                this.cmdctrNameWidth = fontMetrics.stringWidth("CommandCenter");
            }
            this.busyLeftX = this.width - (10 + this.busyWidth);
            this.turtleNameLeftX = this.busyLeftX - fontMetrics.stringWidth(this.turtleName);
            if (this.message == null) {
                this.cmdctrNameLeftX = this.leftX + 10;
            }
            else {
                this.msgLeftX = this.leftX + 10;
                this.cmdctrNameLeftX = (this.width - this.cmdctrNameWidth) / 2;
                final int n = this.msgLeftX + fontMetrics.stringWidth(this.message);
                if (n > this.cmdctrNameLeftX) {
                    this.cmdctrNameLeftX = n + 10;
                }
            }
        }
        graphics.setColor(CmdCtrNameStripe.BG_COLOR);
        graphics.fillRect(this.leftX, this.topY, this.width, this.height);
        graphics.setColor(CmdCtrNameStripe.FG_COLOR);
        final int n2 = this.topY + this.textOffset;
        graphics.drawString("CommandCenter", this.cmdctrNameLeftX, n2);
        graphics.drawString(this.turtleName, this.turtleNameLeftX, n2);
        if (this.message != null) {
            graphics.setColor(CmdCtrNameStripe.MSG_COLOR);
            graphics.drawString(this.message, this.msgLeftX, n2);
        }
        if (this.turtleBusy) {
            graphics.setColor(CmdCtrNameStripe.BUSY_COLOR);
            graphics.drawString(" BUSY", this.busyLeftX, n2);
        }
    }
    
    public void setBounds(final int leftX, final int topY, final int width, final int height) {
        this.leftX = leftX;
        this.topY = topY;
        this.height = height;
        if (this.width != width) {
            this.width = width;
            this.cmdctrNameLeftX = -1;
        }
    }
    
    public void setBusy() {
        this.turtleBusy = true;
    }
    
    public void setLocation(final int leftX, final int topY) {
        this.leftX = leftX;
        this.topY = topY;
    }
    
    public void setMessage(final String message) {
        this.message = message;
        this.cmdctrNameLeftX = -1;
    }
    
    public void setTurtleName(final String s) {
        this.turtleName = ((s == null) ? "" : s);
        this.turtleNameLeftX = -1;
    }
    
    static {
        BG_COLOR = Color.black;
        BUSY_COLOR = Color.yellow;
        FG_COLOR = Color.white;
        MSG_COLOR = Color.magenta;
    }
}
