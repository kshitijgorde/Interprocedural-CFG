import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class DrawableNode extends HiddenNode
{
    public static final int MAX_NODES = 32;
    private static final int TREE_FULLNESS = 2;
    private static final int X_CHECK = 15;
    private static final int Y_CHECK = 20;
    private static final int CHECK_WIDTH = 10;
    protected static final Color TREE_COLOR_1;
    protected static final Color TREE_COLOR_2;
    protected static final int X_DIFF = 275;
    protected static final int Y_DIFF = 50;
    protected static final int X_OFFSET = 7;
    protected static final int Y_OFFSET = 5;
    protected static final int X_STRING = 18;
    protected static final int Y_STRING = 25;
    protected static final int MAX_LEVELS = 6;
    protected static Random random;
    protected int value;
    protected int numberChecked;
    protected Color nodeColor;
    protected boolean nullNode;
    protected boolean movingTreeNode;
    protected boolean stringValueNode;
    
    static {
        TREE_COLOR_1 = Screen.DARK_BLUE;
        TREE_COLOR_2 = Screen.DARK_RED;
        DrawableNode.random = new Random();
    }
    
    DrawableNode() {
        this.numberChecked = 0;
        this.nodeColor = DrawableNode.TREE_COLOR_1;
        this.nullNode = false;
        this.movingTreeNode = false;
        this.stringValueNode = false;
    }
    
    protected Position NodeStringPosition(final int x, final int y) {
        return new Position(x - 7, y + 5);
    }
    
    public void changeColor() {
        if (this.nodeColor == DrawableNode.TREE_COLOR_1) {
            this.nodeColor = DrawableNode.TREE_COLOR_2;
        }
        else {
            this.nodeColor = DrawableNode.TREE_COLOR_1;
        }
    }
    
    public void draw(final Graphics g) {
        if (this.nullNode) {
            return;
        }
        if (this.left() != null && !this.left().nullNode) {
            g.setColor(DrawableNode.TREE_COLOR_1);
            g.drawLine(super.x, super.y, this.left().x, this.left().y);
            g.setColor(this.nodeColor);
            if (!this.movingTreeNode) {
                this.left().draw(g);
            }
        }
        if (this.right() != null && !this.right().nullNode) {
            g.setColor(DrawableNode.TREE_COLOR_1);
            g.drawLine(super.x, super.y, this.right().x, this.right().y);
            g.setColor(this.nodeColor);
            if (!this.movingTreeNode) {
                this.right().draw(g);
            }
        }
        g.setColor(this.nodeColor);
        g.fillOval(super.x - 15, super.y - 15, 30, 30);
        g.setFont(Screen.screen.getFont());
        if (this.stringValueNode) {
            g.setColor(Color.black);
            g.drawString(this.getString(), super.x - 18, super.y + 25);
        }
        else if (this.value >= 0) {
            g.setColor(Color.white);
            g.drawString(String.valueOf(this.value), super.x - 7, super.y + 5);
        }
        if (!Screen.panel.isInteractive()) {
            for (int check = 0; check < this.numberChecked; ++check) {
                final int x1 = super.x - 15 + check * 10;
                final int y1 = super.y + 20;
                final int x2 = x1 + 2;
                final int y2 = y1 + 4;
                final int x3 = x2 + 4;
                final int y3 = y2 - 8;
                g.setColor(Color.black);
                g.drawLine(x1, y1, x2, y2);
                g.drawLine(x2, y2, x3, y3);
            }
        }
    }
    
    public String getString() {
        return "";
    }
    
    public boolean isHidden() {
        return this.nullNode;
    }
    
    protected boolean isNullNode() {
        return super.level == 6 || Math.abs(DrawableNode.random.nextInt() % 6) + 2 < super.level;
    }
    
    public abstract DrawableNode left();
    
    protected Position leftPosition() {
        return new Position(super.x - 275 / (int)Math.pow(2.0, super.level), super.y + 50);
    }
    
    public void move(final String prompt, final boolean visitNode, final DrawableCircle tracer) {
        if (!Screen.panel.isInteractive()) {
            final Position[] positions = { new Position(super.x, super.y) };
            tracer.moveTo(positions);
            Screen.canvas.awaitMovingCompletion();
        }
        tracer.reposition(new Position(super.x, super.y));
        if (visitNode && Screen.panel.isInteractive()) {
            Screen.canvas.acceptClick(this);
            Screen.canvas.awaitClick(prompt, true, false);
        }
    }
    
    public void moveValue(final DrawableNode to, final DrawableString nodeValue) {
        this.value = -1;
        final Position[] positions = { this.NodeStringPosition(to.x, to.y) };
        nodeValue.moveTo(positions);
        nodeValue.show();
    }
    
    public void resetColor() {
        this.nodeColor = DrawableNode.TREE_COLOR_1;
        if (this.left() != null && !this.left().nullNode) {
            this.left().resetColor();
        }
        if (this.right() != null && !this.right().nullNode) {
            this.right().resetColor();
        }
    }
    
    public abstract DrawableNode right();
    
    protected Position rightPosition() {
        return new Position(super.x + 275 / (int)Math.pow(2.0, super.level), super.y + 50);
    }
}
