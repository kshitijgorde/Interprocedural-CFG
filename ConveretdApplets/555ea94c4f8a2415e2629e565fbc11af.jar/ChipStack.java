import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ChipStack extends Panel
{
    final int stackWidth = 38;
    final int maxChips = 20;
    final int maxOffset = 4;
    final int offset = 0;
    final int lineStart = 1;
    final int chipWidth = 28;
    final int chipHeightConst = 6;
    final int topDiff = 0;
    final int leftDiff = 41;
    ChipStack nextHigher;
    int higherRatio;
    int dropLevel;
    int numberOfChips;
    byte[] chipsOffset;
    byte[] chipsLines;
    protected int chipHeight;
    Color lineColor;
    Color chipColor;
    int overflow;
    protected int chipValue;
    String text;
    boolean tempStack;
    
    public ChipStack() {
    }
    
    public ChipStack(final Color chipColor, final Color lineColor, final int dropLevel, final String text, final int n, final int n2) {
        this.setBackground(new Color(0, 128, 0));
        this.nextHigher = null;
        this.higherRatio = 1;
        if (dropLevel < 20) {
            this.dropLevel = dropLevel;
        }
        else {
            this.dropLevel = 20;
        }
        this.chipColor = chipColor;
        this.lineColor = lineColor;
        this.numberOfChips = 0;
        this.chipHeight = 6;
        this.text = text;
        final int n3 = (this.dropLevel - 1) * this.chipHeight + 15;
        this.setLocation(n, n2 - n3);
        this.setSize(38, n3);
        this.tempStack = false;
        this.chipsOffset = new byte[this.dropLevel];
        this.chipsLines = new byte[this.dropLevel];
        for (int i = 1; i < this.dropLevel; ++i) {
            this.chipsOffset[i] = (byte)(4.0 * Math.random() + 1.0);
            this.chipsLines[i] = (byte)(20.0 * Math.random() + 3.0);
        }
    }
    
    private void addChip() {
        ++this.numberOfChips;
        if (this.numberOfChips == this.dropLevel) {
            --this.numberOfChips;
            if (this.nextHigher == null) {
                this.nextHigher = new ChipStack(this.chipColor, this.lineColor, this.dropLevel, "", this.getLeft() + 41, this.getTop() + this.getHeight());
                this.getParent().add(this.nextHigher);
                this.nextHigher.tempStack = true;
            }
            for (int i = 1; i <= this.higherRatio - 1; ++i) {
                this.getChip();
            }
            this.nextHigher.addChip();
            return;
        }
        this.chipsOffset[this.numberOfChips] = (byte)(4.0 * Math.random());
        this.chipsLines[this.numberOfChips] = (byte)(20.0 * Math.random() + 3.0);
        this.drawChip(this.numberOfChips, this.getGraphics());
    }
    
    private int getChip() {
        if (this.overflow != 0) {
            --this.overflow;
            return 1;
        }
        if (this.numberOfChips != 0) {
            --this.numberOfChips;
            this.clearChip(this.numberOfChips + 1, this.getGraphics());
            return 1;
        }
        if (this.nextHigher == null) {
            return 0;
        }
        if (this.nextHigher.removeChips(1) != 1) {
            for (int i = 1; i <= this.higherRatio - 1; ++i) {
                this.addChip();
            }
            return 1;
        }
        return 0;
    }
    
    public void setChipValue(final int chipValue) {
        this.chipValue = chipValue;
        if (this.nextHigher != null) {
            this.nextHigher.setChipValue(chipValue * this.higherRatio);
        }
    }
    
    private void drawChip(final int n, final Graphics graphics) {
        if (n == 1) {
            graphics.setColor(new Color(0, 128, 0));
            graphics.fillRect(0, (this.dropLevel - n - 1) * this.chipHeight, 38, this.chipHeight);
        }
        this.drawChip2(graphics, (this.dropLevel - n - 1) * this.chipHeight, this.chipsOffset[n], this.chipsLines[n]);
    }
    
    private void clearChip(final int n, final Graphics graphics) {
        graphics.setColor(new Color(0, 128, 0));
        graphics.fillRect(0, (this.dropLevel - n - 1) * this.chipHeight, 38, this.chipHeight);
        if (n == 1 && !this.tempStack) {
            this.drawBlank(graphics);
        }
    }
    
    protected void drawChip2(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.setColor(this.chipColor);
        graphics.fillRect(n2, n, 28, this.chipHeight - 1);
        graphics.setColor(Color.black);
        graphics.drawRect(n2, n, 28, this.chipHeight - 1);
        graphics.setColor(this.lineColor);
        graphics.drawLine(n2 + n3, n + 1, n2 + n3, n + this.chipHeight - 2);
        graphics.drawLine(n2 + n3 + 2, n + 1, n2 + n3 + 2, n + this.chipHeight - 2);
        graphics.drawLine(n2 + n3 + 4, n + 1, n2 + n3 + 4, n + this.chipHeight - 2);
    }
    
    private void setText(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.setFont(new Font(graphics.getFont().getName(), 0, 10));
        graphics.drawString(this.text, 3, this.dropLevel * this.chipHeight + 8);
    }
    
    private void growStack() {
        this.drawChip(this.numberOfChips, this.getGraphics());
    }
    
    private void shrinkStack() {
        this.clearChip(this.numberOfChips + 1, this.getGraphics());
    }
    
    private void makeChange() {
        if (this.nextHigher != null && this.nextHigher.removeChips(1) != 1) {
            for (int i = 1; i <= this.higherRatio; ++i) {
                this.addChip();
            }
        }
    }
    
    protected void drawBlank(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(2, (this.dropLevel - 2) * this.chipHeight, 28, this.chipHeight - 1);
    }
    
    public int DrawOnBox(final int n, int drawOnBox, final int n2, final Graphics graphics) {
        int n3;
        if (this.nextHigher != null) {
            n3 = n % this.higherRatio;
        }
        else {
            n3 = n;
        }
        for (int i = 1; i <= n3; ++i) {
            this.drawChip2(graphics, drawOnBox, n2 + this.chipsOffset[(n3 - i) % (this.dropLevel - 1) + 1], this.chipsLines[(n3 - i) % (this.dropLevel - 1) + 1]);
            drawOnBox += this.chipHeight;
        }
        if (this.nextHigher != null) {
            drawOnBox = this.nextHigher.DrawOnBox(n / this.higherRatio, drawOnBox, n2, graphics);
        }
        return drawOnBox;
    }
    
    public int getStackHeight(final int n) {
        int n2;
        if (this.nextHigher != null) {
            n2 = this.nextHigher.getStackHeight(n / this.higherRatio) + n % this.higherRatio * this.chipHeight;
        }
        else {
            n2 = n * this.chipHeight;
        }
        return n2;
    }
    
    public int countChips() {
        if (this.nextHigher != null) {
            return this.numberOfChips + this.nextHigher.countChips() * this.higherRatio;
        }
        return this.numberOfChips + this.overflow;
    }
    
    public void addChips(final int n) {
        if (this.nextHigher != null & this.higherRatio != 1) {
            this.nextHigher.addChips(n / this.higherRatio);
            for (int i = 1; i <= n % this.higherRatio; ++i) {
                this.addChip();
            }
            return;
        }
        for (int j = 1; j <= n; ++j) {
            this.addChip();
        }
    }
    
    public int removeChips(int n) {
        if (this.nextHigher != null) {
            n = this.nextHigher.removeChips(n / this.higherRatio) * this.higherRatio + n % this.higherRatio;
        }
        while (this.countChips() > 0 & n > 0) {
            this.getChip();
            --n;
        }
        return n;
    }
    
    public void paint(final Graphics text) {
        if (text == null) {
            return;
        }
        super.paint(text);
        if (this.numberOfChips == 0) {
            if (this.tempStack) {
                return;
            }
            this.drawBlank(text);
        }
        else {
            for (int i = 1; i <= this.numberOfChips; ++i) {
                this.drawChip(i, text);
            }
        }
        this.setText(text);
    }
    
    public int getChipValue() {
        return this.chipValue;
    }
    
    public int getchipHeight() {
        return this.chipHeight;
    }
    
    public void setHigher(final ChipStack nextHigher, final int higherRatio) {
        this.nextHigher = nextHigher;
        if (this.nextHigher != null) {
            this.higherRatio = higherRatio;
            return;
        }
        this.higherRatio = 1;
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
    
    protected int getLeft() {
        return this.getLocation().x;
    }
    
    protected int getTop() {
        return this.getLocation().y;
    }
    
    protected void setLeft(final int n) {
        this.setLocation(n, this.getTop());
    }
    
    protected void setTop(final int n) {
        this.setLocation(this.getLeft(), n);
    }
    
    protected void setWidth(final int n) {
        this.setSize(n, this.getHeight());
    }
    
    protected void setHeight(final int n) {
        this.setSize(this.getWidth(), n);
    }
}
