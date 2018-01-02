import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class TBScrollbar
{
    public static int WIDTH;
    private static final Color FILL_COLOR;
    private static final Color FRAME_COLOR;
    private static final int BUBBLE_WIDTH = 13;
    private static final int MIN_BUBBLE_HEIGHT = 4;
    private static final int ARROW_BOX_HEIGHT = 14;
    private static final int[] DOWNARROW_X_PTS;
    private static final int[] DOWNARROW_Y_PTS;
    private static final int[] UPARROW_X_PTS;
    private static final int[] UPARROW_Y_PTS;
    private static final int IDLE = 0;
    private static final int LINE_DOWN = 1;
    private static final int LINE_UP = 2;
    private static final int PAGE_DOWN = 3;
    private static final int PAGE_UP = 4;
    private static final int SCROLL = 5;
    private float amountVisible;
    private float barHeight;
    private float numLines;
    private float numVisibleLines;
    private float topLineNum;
    private int bubbleBottomY;
    private int bubbleHeight;
    private int bubbleTopY;
    private int height;
    private int lastMouseY;
    private int leftX;
    private int mouseAction;
    private int[] downArrowXPts;
    private int[] downArrowYPts;
    private int[] upArrowXPts;
    private int[] upArrowYPts;
    private TextBuffer tb;
    
    public TBScrollbar(final TextBuffer tb, final int leftX, final int height) {
        this.tb = tb;
        this.leftX = leftX;
        this.height = height;
        this.barHeight = height - 32;
        (this.downArrowXPts = new int[3])[0] = TBScrollbar.DOWNARROW_X_PTS[0] + this.leftX;
        this.downArrowXPts[1] = TBScrollbar.DOWNARROW_X_PTS[1] + this.leftX;
        this.downArrowXPts[2] = TBScrollbar.DOWNARROW_X_PTS[2] + this.leftX;
        (this.downArrowYPts = new int[3])[0] = height - 16 + TBScrollbar.DOWNARROW_Y_PTS[0];
        this.downArrowYPts[1] = height - 16 + TBScrollbar.DOWNARROW_Y_PTS[1];
        this.downArrowYPts[2] = height - 16 + TBScrollbar.DOWNARROW_Y_PTS[2];
        (this.upArrowXPts = new int[3])[0] = TBScrollbar.UPARROW_X_PTS[0] + this.leftX;
        this.upArrowXPts[1] = TBScrollbar.UPARROW_X_PTS[1] + this.leftX;
        this.upArrowXPts[2] = TBScrollbar.UPARROW_X_PTS[2] + this.leftX;
        (this.upArrowYPts = new int[3])[0] = TBScrollbar.UPARROW_Y_PTS[0];
        this.upArrowYPts[1] = TBScrollbar.UPARROW_Y_PTS[1];
        this.upArrowYPts[2] = TBScrollbar.UPARROW_Y_PTS[2];
        this.mouseAction = 0;
    }
    
    private void paintBubble(final Graphics graphics) {
        if (this.bubbleTopY > 16) {
            final int n = this.bubbleTopY - 16;
            graphics.setColor(Color.white);
            graphics.fillRect(this.leftX + 2, 16, TBScrollbar.WIDTH - 4, n);
        }
        graphics.setColor(TBScrollbar.FRAME_COLOR);
        graphics.drawRect(this.leftX + 2, this.bubbleTopY, TBScrollbar.WIDTH - 5, this.bubbleHeight - 1);
        graphics.setColor(TBScrollbar.FILL_COLOR);
        graphics.fillRect(this.leftX + 3, this.bubbleTopY + 1, TBScrollbar.WIDTH - 6, this.bubbleHeight - 2);
        final int n2 = this.bubbleBottomY + 1;
        final int n3 = this.height - 16 - n2;
        if (n3 > 0) {
            graphics.setColor(Color.white);
            graphics.fillRect(this.leftX + 2, n2, TBScrollbar.WIDTH - 4, n3);
        }
    }
    
    private void paintDownArrowBox(final Graphics graphics) {
        final int n = this.height - 16;
        graphics.setColor(TBScrollbar.FRAME_COLOR);
        graphics.drawRect(this.leftX + 2, n, TBScrollbar.WIDTH - 5, 13);
        graphics.setColor(TBScrollbar.FILL_COLOR);
        graphics.fillRect(this.leftX + 3, n + 1, TBScrollbar.WIDTH - 6, 12);
        graphics.setColor(Color.black);
        graphics.fillPolygon(this.downArrowXPts, this.downArrowYPts, 3);
    }
    
    private void paintUpArrowBox(final Graphics graphics) {
        final int n = 2;
        graphics.setColor(TBScrollbar.FRAME_COLOR);
        graphics.drawRect(this.leftX + 2, n, TBScrollbar.WIDTH - 5, 13);
        graphics.setColor(TBScrollbar.FILL_COLOR);
        graphics.fillRect(this.leftX + 3, n + 1, TBScrollbar.WIDTH - 6, 12);
        graphics.setColor(Color.black);
        graphics.fillPolygon(this.upArrowXPts, this.upArrowYPts, 3);
        graphics.setColor(TBScrollbar.FILL_COLOR);
        graphics.drawLine(this.upArrowXPts[1] - 3, this.upArrowYPts[1] + 3, this.upArrowXPts[1] + 1, this.upArrowYPts[1] - 1);
    }
    
    private void computeBubbleLoc() {
        if (this.numVisibleLines == 0.0f || this.numLines <= this.numVisibleLines) {
            return;
        }
        this.bubbleTopY = 16 + Math.round(this.barHeight / this.numLines * (this.topLineNum - 1.0f));
        int bubbleHeight = this.height - 32;
        if (bubbleHeight > 4) {
            bubbleHeight = 4;
        }
        this.bubbleHeight = Math.round(this.barHeight * (this.numVisibleLines / this.numLines));
        if (this.bubbleHeight < bubbleHeight) {
            this.bubbleHeight = bubbleHeight;
        }
        this.bubbleBottomY = this.bubbleTopY + (this.bubbleHeight - 1);
        if (this.bubbleBottomY > this.height - 1 - 16) {
            this.bubbleBottomY = this.height - 1 - 16;
            this.bubbleTopY = this.bubbleBottomY - (this.bubbleHeight - 1);
        }
    }
    
    public void mousePressed(final int lastMouseY) {
        this.lastMouseY = lastMouseY;
        if (lastMouseY <= 16) {
            this.mouseAction = 2;
        }
        else if (lastMouseY > this.height - 14 + 2) {
            this.mouseAction = 1;
        }
        else if (lastMouseY < this.bubbleTopY && lastMouseY > 16) {
            this.mouseAction = 4;
        }
        else if (lastMouseY > this.bubbleBottomY && lastMouseY < this.height - 14 + 2) {
            this.mouseAction = 3;
        }
    }
    
    public void mouseReleased(final int n) {
        switch (this.mouseAction) {
            case 1: {
                this.tb.scrollDown();
                break;
            }
            case 2: {
                this.tb.scrollUp();
                break;
            }
            case 3: {
                this.tb.pageDownCmd(false);
                break;
            }
            case 4: {
                this.tb.pageUpCmd(false);
                break;
            }
        }
        this.mouseAction = 0;
    }
    
    public void mouseDragged(final int lastMouseY) {
        final int n = lastMouseY - this.lastMouseY;
        this.bubbleTopY += n;
        this.bubbleBottomY += n;
        if (this.bubbleTopY < 16) {
            this.bubbleTopY = 16;
            this.bubbleBottomY = this.bubbleTopY + (this.bubbleHeight - 1);
        }
        if (this.bubbleBottomY > this.height - 16) {
            this.bubbleBottomY = this.height - 16;
            this.bubbleTopY = this.bubbleBottomY - (this.bubbleHeight - 1);
        }
        this.topLineNum = (this.bubbleTopY - 16) / (this.barHeight / this.numLines);
        this.tb.setTopLineNum(Math.round(this.topLineNum));
        this.lastMouseY = lastMouseY;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(this.leftX, 0, TBScrollbar.WIDTH - 1, this.height - 1);
        graphics.setColor(Color.white);
        graphics.drawRect(this.leftX + 1, 1, TBScrollbar.WIDTH - 3, this.height - 3);
        this.paintDownArrowBox(graphics);
        this.paintUpArrowBox(graphics);
        this.paintBubble(graphics);
    }
    
    public void setBounds(final int leftX, final int height) {
        if (leftX != this.leftX) {
            this.leftX = leftX;
            this.downArrowXPts[0] = TBScrollbar.DOWNARROW_X_PTS[0] + this.leftX;
            this.downArrowXPts[1] = TBScrollbar.DOWNARROW_X_PTS[1] + this.leftX;
            this.downArrowXPts[2] = TBScrollbar.DOWNARROW_X_PTS[2] + this.leftX;
            this.upArrowXPts[0] = TBScrollbar.UPARROW_X_PTS[0] + this.leftX;
            this.upArrowXPts[1] = TBScrollbar.UPARROW_X_PTS[1] + this.leftX;
            this.upArrowXPts[2] = TBScrollbar.UPARROW_X_PTS[2] + this.leftX;
        }
        if (height != this.height) {
            this.setHeight(height);
        }
    }
    
    public void setHeight(final int height) {
        this.height = height;
        this.barHeight = height - 32;
        this.computeBubbleLoc();
        this.downArrowYPts[0] = height - 16 + TBScrollbar.DOWNARROW_Y_PTS[0];
        this.downArrowYPts[1] = height - 16 + TBScrollbar.DOWNARROW_Y_PTS[1];
        this.downArrowYPts[2] = height - 16 + TBScrollbar.DOWNARROW_Y_PTS[2];
    }
    
    public void setNumLines(final int n) {
        this.numLines = n;
        this.computeBubbleLoc();
    }
    
    public void setTopLineNum(final int n) {
        this.topLineNum = n + 1;
        this.computeBubbleLoc();
    }
    
    public void setNumVisibleLines(final int n) {
        this.numVisibleLines = n;
    }
    
    public void setValues(final int topLineNum, final int numVisibleLines, final int numLines) {
        this.setTopLineNum(topLineNum);
        this.setNumVisibleLines(numVisibleLines);
        this.setNumLines(numLines);
    }
    
    static {
        TBScrollbar.WIDTH = 19;
        FILL_COLOR = new Color(150, 200, 250);
        FRAME_COLOR = Color.blue;
        DOWNARROW_X_PTS = new int[] { 4, 9, 15 };
        DOWNARROW_Y_PTS = new int[] { 4, 10, 4 };
        UPARROW_X_PTS = new int[] { 3, 9, 15 };
        UPARROW_Y_PTS = new int[] { 12, 5, 12 };
    }
}
