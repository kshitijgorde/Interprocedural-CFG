import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Container;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class BaseStack
{
    private static final int textHeight = 10;
    private static final int CHIPSTACKLEFT = 180;
    private static final int CHIPSTACKSEPERATION = 40;
    private static final int CHIPSTACKBOTTOM = 405;
    private HalfPieceStack halfPieceStack;
    private ChipStack silverStack;
    private ChipStack redStack;
    private ChipStack greenStack;
    private ChipStack blackStack;
    private ChipStack blueStack;
    private ChipStack maroonStack;
    private int chipsBought;
    private int addedToBank;
    private Bank bank;
    
    BaseStack() {
        this.silverStack = new ChipStack(Color.lightGray, Color.lightGray, 10, "   1", 220, 405);
        this.redStack = new ChipStack(Color.red, Color.white, 10, "   5", 260, 405);
        this.greenStack = new ChipStack(Color.green, Color.white, 10, "  25", 300, 405);
        this.blackStack = new ChipStack(Color.black, Color.white, 10, " 100", 340, 405);
        this.blueStack = new ChipStack(Color.yellow, Color.red, 10, " 500", 380, 405);
        this.maroonStack = new ChipStack(Color.cyan, Color.white, 11, "2500", 420, 405);
        this.halfPieceStack = new HalfPieceStack(Color.gray, Color.gray, 4, "", 180, 410);
        this.blueStack.setHigher(this.maroonStack, 5);
        this.blackStack.setHigher(this.blueStack, 5);
        this.greenStack.setHigher(this.blackStack, 4);
        this.redStack.setHigher(this.greenStack, 5);
        this.silverStack.setHigher(this.redStack, 5);
        this.halfPieceStack.setHigher(this.silverStack, 2);
        this.silverStack.setChipValue(1);
    }
    
    public void add(final Container container) {
        container.add(this.maroonStack, -1);
        container.add(this.blueStack, -1);
        container.add(this.blackStack, -1);
        container.add(this.greenStack, -1);
        container.add(this.redStack, -1);
        container.add(this.silverStack, -1);
        container.add(this.halfPieceStack, -1);
    }
    
    public int getTopOfBet(final int n, final int n2, final Graphics graphics, final boolean b) {
        if (b) {
            return n2 - this.silverStack.getStackHeight(n) - 10;
        }
        return n2 - this.halfPieceStack.getStackHeight(n) - 10;
    }
    
    public void draw(final int n, final int n2, final Graphics graphics) {
        this.silverStack.DrawOnBox(n, n2 - this.silverStack.getStackHeight(n) - 10, 0, graphics);
    }
    
    public void drawHalf(final int n, final int n2, final Graphics graphics) {
        this.halfPieceStack.DrawOnBox(n, n2 - this.halfPieceStack.getStackHeight(n) - 10, 0, graphics);
    }
    
    public void drawHalfAbsolute(final int n, final int n2, final int n3, final Graphics graphics) {
        this.halfPieceStack.DrawOnBox(n, n2 - this.halfPieceStack.getStackHeight(n), n3, graphics);
    }
    
    public synchronized void addMouseListener(final MouseListener mouseListener) {
        this.silverStack.addMouseListener(mouseListener);
        this.redStack.addMouseListener(mouseListener);
        this.greenStack.addMouseListener(mouseListener);
        this.blackStack.addMouseListener(mouseListener);
        this.blueStack.addMouseListener(mouseListener);
        this.maroonStack.addMouseListener(mouseListener);
    }
    
    public void addChips(final int n) {
        this.addHalf(n * 2);
    }
    
    public void addHalf(final int n) {
        this.halfPieceStack.addChips(n);
        if (this.bank != null) {
            this.bank.addToBank(n);
        }
    }
    
    public int countChips() {
        return this.halfPieceStack.countChips() / 2;
    }
    
    public int countHalfChips() {
        return this.halfPieceStack.countChips();
    }
    
    public void removeChips(final int n) {
        this.removeHalf(n * 2);
    }
    
    public void removeHalf(final int n) {
        this.halfPieceStack.removeChips(n);
        final int n2 = -n;
        if (this.bank != null) {
            this.bank.addToBank(n2);
        }
    }
    
    public int getValue(final Object o) {
        return ((ChipStack)o).chipValue;
    }
    
    public boolean buyChips(int n) {
        n = (n + 99) / 100;
        n *= 100;
        this.chipsBought += n;
        this.addChips(n);
        return true;
    }
    
    public int getChipsBought() {
        return this.chipsBought;
    }
    
    private void addHalfToBank(final int n) {
        if (this.bank != null) {
            this.bank.addToBank(n);
        }
    }
    
    public void setBank(final Bank bank) {
        this.bank = bank;
    }
}
