// 
// Decompiled by Procyon v0.5.30
// 

package tmcm.xSortLab;

import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Label;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

class SortCanvas extends Canvas implements Runnable
{
    Thread runner;
    static final int APPLETSTOPPED = -1;
    static final int IDLE = 0;
    static final int STARTING = 1;
    static final int STOPPED = 2;
    static final int STEP = 3;
    static final int RUN = 4;
    static final int STOPPING = 5;
    private int state;
    private int oldState;
    Image OSC;
    Graphics OSG;
    boolean changed;
    int changed_x;
    int changed_y;
    int changed_width;
    int changed_height;
    int width;
    int height;
    static final Color backgroundColor;
    static final Color borderColor;
    static final Color barColor;
    static final Color finishedBarColor;
    static final Color movingBarColor;
    static final Color movingBarOutlineColor;
    static final Color boxColor;
    static final Color multiBoxColor;
    static final Color maxColor;
    int[] item;
    boolean tempOn;
    int[] mergeBox;
    Point multiBoxLoc;
    Point movingItemLoc;
    int maxLoc;
    int hiLoc;
    int loLoc;
    int box1Loc;
    int box2Loc;
    int movingItem;
    int copyCt;
    int comparisonCt;
    Label comparisons;
    Label copies;
    MessageCanvas comment1;
    MessageCanvas comment2;
    VisualSortPanel owner;
    static final int barGap = 8;
    int barWidth;
    int barHeight;
    int minBarHeight;
    int barIncrement;
    int leftOffset;
    int firstRow_y;
    int secondRow_y;
    int textAscent;
    Font font;
    FontMetrics fm;
    boolean fast;
    int method;
    boolean done;
    int i;
    int j;
    int k;
    int hi;
    int lo;
    int[] stack;
    int top;
    int sortLength;
    int end_i;
    int end_j;
    boolean valid;
    
    SortCanvas(final VisualSortPanel owner, final Label comparisons, final Label copies, final MessageCanvas comment1, final MessageCanvas comment2) {
        this.state = 1;
        this.oldState = 1;
        this.width = -1;
        this.item = new int[33];
        this.mergeBox = new int[3];
        this.multiBoxLoc = new Point(-1, -1);
        this.movingItemLoc = new Point(-1, -1);
        this.method = 1;
        this.stack = new int[33];
        this.valid = false;
        this.owner = owner;
        this.comparisons = comparisons;
        this.copies = copies;
        this.comment1 = comment1;
        this.comment2 = comment2;
        this.setUpSortData();
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        if (n3 != this.width || n4 != this.height) {
            this.OSC = null;
            this.OSG = null;
        }
    }
    
    synchronized int getState() {
        return this.state;
    }
    
    synchronized void setState(final int state) {
        this.state = state;
        this.notify();
    }
    
    void setUpSortData() {
        this.maxLoc = -1;
        this.hiLoc = -1;
        this.loLoc = -1;
        this.box1Loc = -1;
        this.box2Loc = -1;
        this.multiBoxLoc.x = -1;
        this.mergeBox[0] = -1;
        this.movingItem = -1;
        this.tempOn = false;
        for (int i = 1; i <= 16; ++i) {
            this.item[i] = i;
        }
        for (int j = 16; j >= 2; --j) {
            final int n = 1 + (int)(Math.random() * j);
            final int n2 = this.item[j];
            this.item[j] = this.item[n];
            this.item[n] = n2;
        }
        this.item[0] = -1;
        for (int k = 17; k < 33; ++k) {
            this.item[k] = -1;
        }
    }
    
    synchronized void newSort(final int method) {
        if (this.state == 4) {
            this.stopRunning();
        }
        this.state = 1;
        this.setUpSortData();
        this.comparisons.setText("0");
        this.copies.setText("0");
        this.comparisonCt = 0;
        this.copyCt = 0;
        this.method = method;
        this.valid = false;
        this.comment1.changeMessage("Click \"Go\" or \"Step\" to begin sorting.");
        this.comment2.changeMessage("");
        this.setChangedAll();
        this.repaint();
    }
    
    public synchronized void paint(final Graphics graphics) {
        Label_0171: {
            if (this.OSC != null && this.size().width == this.width) {
                if (this.size().height == this.height) {
                    break Label_0171;
                }
            }
            try {
                this.OSC = this.createImage(this.size().width, this.size().height);
                this.OSG = this.OSC.getGraphics();
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.OSC = null;
                this.OSG = null;
            }
            this.font = new Font("Helvetica", 0, 10);
            this.fm = graphics.getFontMetrics(this.font);
            if (this.OSG != null) {
                this.OSG.setFont(this.font);
            }
            this.textAscent = this.fm.getAscent();
            this.setSizeData(this.size().width, this.size().height);
            this.setChanged(0, 0, this.width, this.height);
        }
        if (this.OSC == null) {
            graphics.setFont(this.font);
            this.draw(graphics, 0, 0, this.width, this.height);
        }
        else {
            if (this.changed) {
                this.draw(this.OSG, this.changed_x, this.changed_y, this.changed_width, this.changed_height);
            }
            graphics.drawImage(this.OSC, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void setSizeData(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.barWidth = (this.width - 20 + 8) / 16 - 8;
        this.leftOffset = (this.width - 16 * this.barWidth - 120) / 2;
        this.barHeight = (this.height - 40 - 2 * this.textAscent) / 2;
        this.barIncrement = (this.barHeight - 3) / 17;
        this.minBarHeight = this.barHeight - 17 * this.barIncrement;
        this.firstRow_y = this.barHeight + 10;
        this.secondRow_y = 2 * this.barHeight + 25 + this.textAscent;
    }
    
    synchronized void setChanged(final int changed_x, final int changed_y, final int changed_width, final int changed_height) {
        if (this.changed) {
            final int min = Math.min(changed_x, this.changed_x);
            final int min2 = Math.min(changed_y, this.changed_y);
            final int max = Math.max(changed_x + changed_width, this.changed_x + this.changed_width);
            final int max2 = Math.max(changed_y + changed_height, this.changed_y + this.changed_height);
            this.changed_x = min;
            this.changed_y = min2;
            this.changed_width = max - min;
            this.changed_height = max2 - min2;
        }
        else {
            this.changed_x = changed_x;
            this.changed_y = changed_y;
            this.changed_width = changed_width;
            this.changed_height = changed_height;
        }
        this.changed = true;
    }
    
    void setChangedAll() {
        this.setChanged(0, 0, this.size().width, this.size().height);
    }
    
    void putItem(final Graphics graphics, final int n) {
        final int n2 = this.item[n];
        if (n2 == -1) {
            return;
        }
        int n3;
        if (n2 > 16) {
            n3 = (n2 - 100) * this.barIncrement + this.minBarHeight;
            graphics.setColor(SortCanvas.finishedBarColor);
        }
        else {
            n3 = n2 * this.barIncrement + this.minBarHeight;
            graphics.setColor(SortCanvas.barColor);
        }
        int n4;
        int n5;
        if (n == 0) {
            n4 = this.leftOffset + (this.barWidth + 8) * 15 / 2;
            n5 = this.secondRow_y - n3;
        }
        else if (n < 17) {
            n4 = this.leftOffset + (n - 1) * (this.barWidth + 8);
            n5 = this.firstRow_y - n3;
        }
        else {
            n4 = this.leftOffset + (n - 17) * (this.barWidth + 8);
            n5 = this.secondRow_y - n3;
        }
        graphics.fillRect(n4, n5, this.barWidth, n3);
        graphics.setColor(SortCanvas.finishedBarColor);
        graphics.drawRect(n4, n5, this.barWidth, n3);
    }
    
    void drawMovingItem(final Graphics graphics) {
        final int n = this.movingItem * this.barIncrement + this.minBarHeight;
        graphics.setColor(SortCanvas.movingBarColor);
        graphics.fillRect(this.movingItemLoc.x, this.movingItemLoc.y - n, this.barWidth, n);
        graphics.setColor(SortCanvas.movingBarOutlineColor);
        graphics.drawRect(this.movingItemLoc.x, this.movingItemLoc.y - n, this.barWidth, n);
    }
    
    void drawMax(final Graphics graphics) {
        final int stringWidth = this.fm.stringWidth("Max");
        final int n = this.leftOffset + (this.maxLoc - 1) * (this.barWidth + 8) + this.barWidth / 2;
        final int n2 = this.firstRow_y + 38 + this.textAscent;
        graphics.setColor(SortCanvas.maxColor);
        graphics.drawString("Max", n - stringWidth / 2, n2 + this.textAscent);
        graphics.drawLine(n, n2, n, n2 - 30);
        graphics.drawLine(n, n2 - 30, n + 6, n2 - 24);
        graphics.drawLine(n, n2 - 30, n - 6, n2 - 24);
    }
    
    void drawBox(final Graphics graphics, final int n) {
        int n2;
        int n3;
        if (n == 0) {
            n2 = this.leftOffset + (this.barWidth + 8) * 15 / 2;
            n3 = this.secondRow_y;
        }
        else if (n < 17) {
            n2 = this.leftOffset + (n - 1) * (this.barWidth + 8);
            n3 = this.firstRow_y;
        }
        else {
            n2 = this.leftOffset + (n - 17) * (this.barWidth + 8);
            n3 = this.secondRow_y;
        }
        graphics.setColor(SortCanvas.boxColor);
        graphics.drawRect(n2 - 2, n3 - this.barHeight - 2, this.barWidth + 4, this.barHeight + 4);
    }
    
    void drawMultiBox(final Graphics graphics) {
        int n;
        int n2;
        if (this.multiBoxLoc.x < 17) {
            n = this.firstRow_y;
            n2 = this.leftOffset + (this.multiBoxLoc.x - 1) * (this.barWidth + 8);
        }
        else {
            n = this.secondRow_y;
            n2 = this.leftOffset + (this.multiBoxLoc.x - 17) * (this.barWidth + 8);
        }
        final int n3 = (this.multiBoxLoc.y - this.multiBoxLoc.x) * (8 + this.barWidth) + this.barWidth;
        graphics.setColor(SortCanvas.multiBoxColor);
        graphics.drawRect(n2 - 4, n - this.barHeight - 4, n3 + 8, this.barHeight + 8);
    }
    
    void drawMergeListBoxes(final Graphics graphics) {
        final int firstRow_y = this.firstRow_y;
        final int n = this.leftOffset + (this.mergeBox[0] - 1) * (this.barWidth + 8);
        final int n2 = (this.mergeBox[1] - this.mergeBox[0]) * (8 + this.barWidth) + this.barWidth;
        final int n3 = (this.mergeBox[2] - this.mergeBox[0]) * (8 + this.barWidth) + this.barWidth;
        graphics.setColor(SortCanvas.multiBoxColor);
        graphics.drawRect(n - 4, firstRow_y - this.barHeight - 4, n2 + 8, this.barHeight + 8);
        graphics.drawRect(n - 4, firstRow_y - this.barHeight - 4, n3 + 8, this.barHeight + 8);
    }
    
    synchronized void draw(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(SortCanvas.backgroundColor);
        graphics.fillRect(n, n2, n3, n4);
        graphics.setColor(SortCanvas.borderColor);
        graphics.drawRect(0, 0, this.width, this.height);
        graphics.drawRect(1, 1, this.width - 2, this.height - 2);
        graphics.drawLine(0, this.height - 2, this.width, this.height - 2);
        graphics.drawLine(this.width - 2, 0, this.width - 2, this.height);
        int n5 = (n - 10) / (this.barWidth + 8) + 1;
        if (n5 < 1) {
            n5 = 1;
        }
        int n6 = (n + n3 - 10) / (this.barWidth + 8) + 1;
        if (n6 > 16) {
            n6 = 16;
        }
        if (n2 <= this.firstRow_y) {
            for (int i = n5; i <= n6; ++i) {
                this.putItem(graphics, i);
            }
        }
        if (n2 <= this.firstRow_y + 10 + this.textAscent && n2 + n4 > this.firstRow_y) {
            graphics.setColor(SortCanvas.borderColor);
            for (int j = n5; j <= n6; ++j) {
                final String value = String.valueOf(j);
                graphics.drawString(value, this.leftOffset + (j - 1) * (this.barWidth + 8) + (this.barWidth - this.fm.stringWidth(value)) / 2, this.firstRow_y + 6 + this.textAscent);
            }
        }
        if (n2 <= this.secondRow_y && n2 + n4 >= this.secondRow_y - this.barHeight) {
            for (int k = 16 + n5; k <= 16 + n6; ++k) {
                this.putItem(graphics, k);
            }
        }
        if (this.tempOn) {
            graphics.setColor(SortCanvas.borderColor);
            graphics.drawString("Temp", this.leftOffset + (16 * this.barWidth + 120 - this.fm.stringWidth("Temp")) / 2, this.secondRow_y + 5 + this.textAscent);
            this.putItem(graphics, 0);
        }
        if (this.maxLoc >= 0) {
            this.drawMax(graphics);
        }
        if (this.box1Loc >= 0) {
            this.drawBox(graphics, this.box1Loc);
        }
        if (this.box2Loc >= 0) {
            this.drawBox(graphics, this.box2Loc);
        }
        if (this.multiBoxLoc.x > 0) {
            this.drawMultiBox(graphics);
        }
        if (this.mergeBox[0] > 0) {
            this.drawMergeListBoxes(graphics);
        }
        if (this.movingItem >= 0) {
            this.drawMovingItem(graphics);
        }
        this.changed = false;
    }
    
    synchronized void startRunning() {
        if (this.state == 0) {
            this.newSort(this.method);
        }
        this.state = 4;
        if (this.fast) {
            this.comment2.changeMessage("");
        }
        if (this.runner == null || !this.runner.isAlive()) {
            (this.runner = new Thread(this)).start();
        }
        else {
            this.notify();
        }
    }
    
    synchronized void stopRunning() {
        if (this.runner != null && this.runner.isAlive() && this.state == 4) {
            this.state = 5;
            this.notify();
            while (this.state == 5) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    synchronized void setFast(final boolean fast) {
        this.fast = fast;
        if (this.state == 4 && fast) {
            this.comment2.changeMessage("");
        }
        this.notify();
    }
    
    synchronized void doStep() {
        if (this.state == 4 || this.state == 0) {
            return;
        }
        this.state = 3;
        if (this.runner == null || !this.runner.isAlive()) {
            (this.runner = new Thread(this)).start();
        }
        else {
            this.notify();
        }
    }
    
    synchronized void doAppletStop() {
        this.oldState = this.state;
        this.stopRunning();
        this.state = -1;
        this.OSC = null;
        this.OSG = null;
    }
    
    synchronized void doAppletStart() {
        if (this.state != -1) {
            return;
        }
        this.state = this.oldState;
        if (this.state == 4 || this.state == 3) {
            this.notify();
        }
    }
    
    synchronized void doWait(final int n) {
        try {
            this.wait(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        while (true) {
            final int state;
            synchronized (this) {
                while (this.state <= 2) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                state = this.state;
            }
            if (state == 5) {
                this.setState(2);
                this.owner.runnerStopped();
            }
            else {
                this.scriptStep();
                this.repaint();
                if (this.done) {
                    this.owner.doneRunning(this.method, this.comparisonCt, this.copyCt);
                    this.setState(0);
                    this.repaint();
                }
                else if (this.getState() == 5) {
                    this.setState(2);
                    this.owner.runnerStopped();
                }
                else if (state == 3 && this.getState() != 4) {
                    this.setState(2);
                    this.owner.runnerStopped();
                }
                else if (this.fast) {
                    this.doWait(100);
                }
                else {
                    this.doWait(1000);
                }
            }
        }
    }
    
    void say1(final String s) {
        this.comment1.changeMessage(s);
    }
    
    void say2(final String s) {
        if (!this.fast || this.getState() != 4) {
            this.comment2.changeMessage(s);
        }
    }
    
    void invalidate(final int n, final int n2) {
        if (n < 0) {
            return;
        }
        int n3;
        int n4;
        if (n == 0) {
            n3 = this.leftOffset + (this.barWidth + 8) * 15 / 2;
            n4 = this.secondRow_y - this.barHeight;
        }
        else if (n < 17) {
            n3 = this.leftOffset + (n - 1) * (this.barWidth + 8);
            n4 = this.firstRow_y - this.barHeight;
        }
        else {
            n3 = this.leftOffset + (n - 17) * (this.barWidth + 8);
            n4 = this.secondRow_y - this.barHeight;
        }
        this.setChanged(n3 - n2, n4 - n2, this.barWidth + 1 + 2 * n2, this.barHeight + 1 + 2 * n2);
    }
    
    void putTemp(final boolean tempOn) {
        if (this.tempOn == tempOn) {
            return;
        }
        this.tempOn = tempOn;
        this.setChanged(0, this.secondRow_y + 1, this.width, this.height - this.secondRow_y);
    }
    
    void putMax(final int maxLoc) {
        final int n = this.fm.stringWidth("Max") + 4;
        if (this.maxLoc != -1) {
            this.setChanged(this.leftOffset + (this.maxLoc - 1) * (this.barWidth + 8) + (this.barWidth - n) / 2, this.firstRow_y + 1, n, 50 + this.textAscent);
        }
        this.maxLoc = maxLoc;
        if (this.maxLoc != -1) {
            this.setChanged(this.leftOffset + (this.maxLoc - 1) * (this.barWidth + 8) + (this.barWidth - n) / 2, this.firstRow_y + 1, n, 50 + this.textAscent);
        }
    }
    
    void putMergeListBoxes(final int n, final int n2, final int n3) {
        if (this.mergeBox[0] != -1) {
            this.invalidate(this.mergeBox[0], 5);
            this.invalidate(this.mergeBox[2], 5);
        }
        this.mergeBox[0] = n;
        this.mergeBox[1] = n2;
        this.mergeBox[2] = n3;
        if (this.mergeBox[0] != -1) {
            this.invalidate(this.mergeBox[0], 5);
            this.invalidate(this.mergeBox[2], 5);
        }
    }
    
    void putMultiBox(final int x, final int y) {
        if (this.multiBoxLoc.x != -1) {
            this.invalidate(this.multiBoxLoc.x, 5);
            this.invalidate(this.multiBoxLoc.y, 5);
        }
        this.multiBoxLoc.x = x;
        this.multiBoxLoc.y = y;
        if (this.multiBoxLoc.x != -1) {
            this.invalidate(this.multiBoxLoc.x, 5);
            this.invalidate(this.multiBoxLoc.y, 5);
        }
    }
    
    void putBoxes(final int box1Loc, final int box2Loc) {
        if (this.box1Loc != -1) {
            this.invalidate(this.box1Loc, 3);
        }
        this.box1Loc = box1Loc;
        if (this.box1Loc != -1) {
            this.invalidate(this.box1Loc, 3);
        }
        if (this.box2Loc != -1) {
            this.invalidate(this.box2Loc, 3);
        }
        this.box2Loc = box2Loc;
        if (this.box2Loc != -1) {
            this.invalidate(this.box2Loc, 3);
            this.repaint();
            if (this.fast) {
                this.doWait(100);
            }
            else {
                this.doWait(200);
            }
        }
    }
    
    void itemChanged(final int n) {
        this.invalidate(n, 0);
        this.repaint();
    }
    
    void copyFast(final int n, final int n2) {
        this.item[n] = this.item[n2];
        this.item[n2] = -1;
        this.invalidate(n, 0);
        this.invalidate(n2, 0);
        this.repaint();
        ++this.copyCt;
        this.copies.setText(String.valueOf(this.copyCt));
        this.doWait(100);
    }
    
    void copyItem(final int n, final int n2) {
        if (this.fast) {
            this.copyFast(n, n2);
        }
        else {
            this.movingItem = this.item[n2];
            this.item[n2] = -1;
            this.invalidate(n2, 0);
            int n3;
            int n4;
            if (n == 0) {
                n3 = this.leftOffset + (this.barWidth + 8) * 15 / 2;
                n4 = this.secondRow_y;
            }
            else if (n < 17) {
                n3 = this.leftOffset + (n - 1) * (this.barWidth + 8);
                n4 = this.firstRow_y;
            }
            else {
                n3 = this.leftOffset + (n - 17) * (this.barWidth + 8);
                n4 = this.secondRow_y;
            }
            int n5;
            int n6;
            if (n2 == 0) {
                n5 = this.leftOffset + (this.barWidth + 8) * 15 / 2;
                n6 = this.secondRow_y;
            }
            else if (n2 < 17) {
                n5 = this.leftOffset + (n2 - 1) * (this.barWidth + 8);
                n6 = this.firstRow_y;
            }
            else {
                n5 = this.leftOffset + (n2 - 17) * (this.barWidth + 8);
                n6 = this.secondRow_y;
            }
            int n7 = (int)Math.sqrt((n5 - n3) * (n5 - n3) + (n6 - n4) * (n6 - n4)) / 5;
            if (n7 > 15) {
                n7 = 15;
            }
            else if (n7 < 5) {
                n7 = 5;
            }
            final int n8 = this.minBarHeight + this.movingItem * this.barIncrement;
            for (int i = 0; i <= n7; ++i) {
                if (i > 0) {
                    this.setChanged(this.movingItemLoc.x, this.movingItemLoc.y - n8, this.barWidth + 1, n8 + 1);
                }
                this.movingItemLoc.x = n5 + (n3 - n5) * i / n7;
                this.movingItemLoc.y = n6 + (n4 - n6) * i / n7;
                this.setChanged(this.movingItemLoc.x, this.movingItemLoc.y - n8, this.barWidth + 1, n8 + 1);
                this.repaint();
                this.doWait(50);
            }
            this.item[n] = this.movingItem;
            this.movingItem = -1;
            this.invalidate(n, 0);
            this.repaint();
            this.doWait(50);
            ++this.copyCt;
            this.copies.setText(String.valueOf(this.copyCt));
        }
    }
    
    boolean greaterThan(final int n, final int n2) {
        this.putBoxes(n, n2);
        ++this.comparisonCt;
        this.comparisons.setText(String.valueOf(this.comparisonCt));
        return this.item[n] > this.item[n2];
    }
    
    void swapItems(final int n, final int n2) {
        this.copyItem(0, n);
        if (this.getState() == 1) {
            return;
        }
        this.copyItem(n, n2);
        if (this.getState() == 1) {
            return;
        }
        this.copyItem(n2, 0);
    }
    
    synchronized void scriptSetup() {
        this.comment2.changeMessage("");
        switch (this.method) {
            case 1: {
                this.j = 16;
                this.i = 1;
                this.say1("Phase 1:  largest item \"bubbles\" up to position 16");
                this.putTemp(true);
                break;
            }
            case 2: {
                this.j = 16;
                this.i = 2;
                this.say1("Phase 1:  Find the largest item and swap it with item 16");
                this.say2("Item 1 is the largest item seen so far during this phase");
                this.putMax(1);
                this.putTemp(true);
                break;
            }
            case 3: {
                this.j = 0;
                this.putMultiBox(1, 1);
                this.say1("The sublist in the box -- just item 1 for now -- is correctly sorted");
                break;
            }
            case 4: {
                this.sortLength = 1;
                this.i = 1;
                this.end_i = 1;
                this.j = 2;
                this.end_j = 2;
                this.k = 17;
                this.lo = 0;
                this.hi = 1;
                this.say1("Phase 1:  Merge lists of length 1 into lists of length 2");
                this.say2("First, merge item 1 with item 2.");
                this.putMultiBox(17, 18);
                this.putMergeListBoxes(1, 1, 2);
                break;
            }
            case 5: {
                this.top = 0;
                this.hi = 16;
                this.lo = 1;
                this.k = 0;
                this.i = 1;
                this.j = 16;
                this.say1("Apply \"QuickSortStep\" to items 1 through 16.");
                this.say2("The range of possible final positions for item 1 is boxed.");
                this.putMultiBox(1, 16);
                this.putTemp(true);
                break;
            }
        }
    }
    
    synchronized void scriptStep() {
        if (!this.valid) {
            this.scriptSetup();
            this.valid = true;
            this.done = false;
            return;
        }
        switch (this.method) {
            case 1: {
                if (this.i == this.j) {
                    this.comment2.changeMessage("");
                    this.putBoxes(-1, -1);
                    if (this.j == 2) {
                        this.say1("The sort is finished.");
                        this.done = true;
                        this.putTemp(false);
                        this.item[1] += 100;
                        this.itemChanged(1);
                        break;
                    }
                    --this.j;
                    this.i = 1;
                    this.say1("Phase " + (17 - this.j) + ":  next largest item bubbles up to position " + this.j);
                    break;
                }
                else {
                    if (this.greaterThan(this.i, this.i + 1)) {
                        this.say2("Is item " + this.i + " bigger than item " + (this.i + 1) + "?  Yes, so swap them.");
                        this.swapItems(this.i, this.i + 1);
                    }
                    else {
                        this.say2("Is item " + this.i + " bigger than item " + (this.i + 1) + "?  No, so don't swap them.");
                    }
                    ++this.i;
                    if (this.i == this.j) {
                        this.item[this.j] += 100;
                        this.itemChanged(this.j);
                        break;
                    }
                    break;
                }
                break;
            }
            case 2: {
                if (this.j == 1) {
                    this.say1("The sort is finished.");
                    this.comment2.changeMessage("");
                    this.done = true;
                    this.item[1] += 100;
                    this.itemChanged(1);
                    this.putTemp(false);
                    break;
                }
                if (this.i == -1) {
                    this.say1("Phase " + (17 - this.j) + ":   Find the next largest item and move it to position " + this.j);
                    this.say2("Item 1 is the largest item seen so far during this phase");
                    this.i = 2;
                    this.putMax(1);
                    break;
                }
                if (this.i > this.j) {
                    this.putBoxes(-1, -1);
                    this.k = this.maxLoc;
                    this.putMax(-1);
                    if (this.k == this.j) {
                        this.say2("Item " + this.j + " is already in its correct location.");
                    }
                    else {
                        if (this.j == 2) {
                            this.say2("Swap item 2 with item 1");
                        }
                        else {
                            this.say2("Swap item " + this.j + " with maximum among items 1 through " + (this.j - 1));
                        }
                        this.swapItems(this.k, this.j);
                    }
                    this.item[this.j] += 100;
                    this.itemChanged(this.j);
                    --this.j;
                    this.i = -1;
                    break;
                }
                if (this.greaterThan(this.i, this.maxLoc)) {
                    this.say2("Item " + this.i + " is bigger than item " + this.maxLoc + ", so item " + this.i + " is now the max seen.");
                    this.putMax(this.i);
                    ++this.i;
                    break;
                }
                this.say2("Item " + this.i + " is smaller than item " + this.maxLoc + ", so item " + this.maxLoc + " is still the max seen.");
                ++this.i;
                break;
            }
            case 3: {
                if (this.j == 0) {
                    this.say1("Phase 1: Insert item 2 into its correct position in the sorted list.");
                    this.say2("Copy item 2 to Temp.");
                    this.copyItem(0, 2);
                    this.j = 2;
                    this.i = 1;
                    this.putTemp(true);
                    break;
                }
                if (this.j == 17) {
                    this.putMultiBox(-1, -1);
                    for (int i = 1; i <= 16; ++i) {
                        final int[] item = this.item;
                        final int n = i;
                        item[n] += 100;
                    }
                    this.setChangedAll();
                    this.say1("The sort is finished.");
                    this.done = true;
                    this.comment2.changeMessage("");
                    this.putTemp(false);
                    break;
                }
                if (this.i == 0) {
                    this.say2("Temp is smaller than all items in the sorted list; copy it to position 1.");
                    this.copyItem(1, 0);
                    this.i = -1;
                    break;
                }
                if (this.i == -1) {
                    this.putBoxes(-1, -1);
                    this.say1("Items 1 through " + this.j + " now form a sorted list.");
                    this.comment2.changeMessage("");
                    this.putMultiBox(1, this.j);
                    ++this.j;
                    this.i = -2;
                    break;
                }
                if (this.i == -2) {
                    this.say1("Phase " + (this.j - 1) + ": Insert item " + this.j + "  into its correct position in the sorted list.");
                    this.say2("Copy item " + this.j + " to Temp.");
                    this.copyItem(0, this.j);
                    this.i = this.j - 1;
                    break;
                }
                if (this.greaterThan(this.i, 0)) {
                    this.say2("Is item " + this.i + " bigger than Temp?  Yes, so move it up to position " + (this.i + 1));
                    this.copyItem(this.i + 1, this.i);
                    --this.i;
                    break;
                }
                this.say2("Is item " + this.i + " bigger than Temp?  No, so Temp belongs in position " + (this.i + 1));
                this.copyItem(this.i + 1, 0);
                this.i = -1;
                break;
            }
            case 4: {
                if (this.lo == 1 && this.sortLength == 8) {
                    for (int j = 1; j <= 16; ++j) {
                        final int[] item2 = this.item;
                        final int n2 = j;
                        item2[n2] += 100;
                    }
                    this.setChangedAll();
                    this.say1("The sort is finished.");
                    this.comment2.changeMessage("");
                    this.done = true;
                    break;
                }
                if (this.lo == 1) {
                    ++this.hi;
                    this.sortLength *= 2;
                    this.say1("Phase " + this.hi + ":  Merge lists of length " + this.sortLength + " into lists of length " + this.sortLength * 2);
                    this.k = 17;
                    this.i = 1;
                    this.j = this.sortLength + 1;
                    this.end_i = this.i + this.sortLength - 1;
                    this.end_j = this.j + this.sortLength - 1;
                    this.say2("First, merge items " + this.i + " through " + this.end_i + " with items " + this.j + " through " + this.end_j);
                    this.putMultiBox(this.i + 16, this.end_j + 16);
                    this.putMergeListBoxes(this.i, this.end_i, this.end_j);
                    this.lo = 0;
                    break;
                }
                if (this.end_i < this.i && this.end_j < this.j) {
                    if (this.k == 33) {
                        this.putMultiBox(-1, -1);
                        this.putMergeListBoxes(-1, -1, -1);
                        this.say2("Copy merged items back to original list.");
                        for (int k = 1; k < 17; ++k) {
                            this.copyFast(k, k + 16);
                            if (this.getState() == 1) {
                                return;
                            }
                        }
                        this.lo = 1;
                        break;
                    }
                    this.end_i += 2 * this.sortLength;
                    this.end_j += 2 * this.sortLength;
                    this.j = this.end_i + 1;
                    this.i = this.j - this.sortLength;
                    if (this.sortLength == 1) {
                        this.say2("Next, merge item " + this.i + " with item " + this.j);
                    }
                    else {
                        this.say2("Next, merge items " + this.i + " through " + this.end_i + " with items " + this.j + " through " + this.end_j);
                    }
                    this.putMultiBox(this.i + 16, this.end_j + 16);
                    this.putMergeListBoxes(this.i, this.end_i, this.end_j);
                    break;
                }
                else {
                    if (this.end_i < this.i) {
                        this.putBoxes(-1, -1);
                        this.say2("List 1 is empty; move item " + this.j + " to the merged list.");
                        this.copyItem(this.k, this.j);
                        ++this.j;
                        ++this.k;
                        break;
                    }
                    if (this.end_j < this.j) {
                        this.putBoxes(-1, -1);
                        this.say2("List 2 is empty; move item " + this.i + " to the merged list.");
                        this.copyItem(this.k, this.i);
                        ++this.i;
                        ++this.k;
                        break;
                    }
                    if (this.greaterThan(this.i, this.j)) {
                        this.say2("Is item " + this.j + " smaller than item " + this.i + "?  Yes, so move item " + this.j + " to merged list");
                        this.copyItem(this.k, this.j);
                        ++this.j;
                        ++this.k;
                        break;
                    }
                    this.say2("Is item " + this.j + " smaller than item " + this.i + "?  No, so move item " + this.i + " to merged list");
                    this.copyItem(this.k, this.i);
                    ++this.i;
                    ++this.k;
                    break;
                }
                break;
            }
            case 5: {
                if (this.k == 0) {
                    if (this.hi == this.lo) {
                        this.say2("There is only one item in the range; it is already in its final position.");
                        this.item[this.hi] += 100;
                        this.itemChanged(this.hi);
                        this.putMultiBox(-1, -1);
                        this.k = 1;
                        break;
                    }
                    this.say2("Copy item " + this.lo + " to Temp");
                    this.copyItem(0, this.lo);
                    this.k = -1;
                    break;
                }
                else if (this.k == 1) {
                    if (this.top == 0) {
                        this.say1("The sort is finished.");
                        this.comment2.changeMessage("");
                        this.putTemp(false);
                        this.done = true;
                        break;
                    }
                    this.hi = this.stack[this.top];
                    this.lo = this.stack[this.top - 1];
                    this.j = this.hi;
                    this.i = this.lo;
                    this.top -= 2;
                    this.say1("Apply \"QuickSortStep\" to items " + this.lo + " through " + this.hi);
                    this.say2("The range of possible final positions for item " + this.lo + " is boxed");
                    this.putMultiBox(this.lo, this.hi);
                    this.k = 0;
                    break;
                }
                else {
                    if (this.k == 2) {
                        this.say2("Item " + this.hi + " is in final position; smaller items below and bigger items above");
                        this.putMultiBox(-1, -1);
                        this.item[this.hi] += 100;
                        this.itemChanged(this.hi);
                        if (this.hi < this.j) {
                            this.stack[this.top + 1] = this.hi + 1;
                            this.stack[this.top + 2] = this.j;
                            this.top += 2;
                        }
                        if (this.hi > this.i) {
                            this.stack[this.top + 1] = this.i;
                            this.stack[this.top + 2] = this.hi - 1;
                            this.top += 2;
                        }
                        this.k = 1;
                        break;
                    }
                    if (this.hi == this.lo) {
                        this.putBoxes(-1, -1);
                        this.say2("Only one possible position left for Temp; copy Temp to position " + this.hi);
                        this.copyItem(this.hi, 0);
                        this.k = 2;
                        break;
                    }
                    if (this.item[this.lo] == -1) {
                        if (this.greaterThan(0, this.hi)) {
                            this.say2("Item " + this.hi + " is smaller than Temp, so move it; Temp will end up above it");
                            this.copyItem(this.lo, this.hi);
                            this.putMultiBox(++this.lo, this.hi);
                            break;
                        }
                        this.say2("Item " + this.hi + " is bigger than Temp, so Temp will end up below it");
                        --this.hi;
                        this.putMultiBox(this.lo, this.hi);
                        break;
                    }
                    else {
                        if (this.item[this.hi] != -1) {
                            break;
                        }
                        if (this.greaterThan(this.lo, 0)) {
                            this.say2("Item " + this.lo + " is bigger than Temp, so move it; Temp will end up below it");
                            this.copyItem(this.hi, this.lo);
                            --this.hi;
                            this.putMultiBox(this.lo, this.hi);
                            break;
                        }
                        this.say2("Item " + this.lo + " is smaller than Temp, so Temp will end up above it");
                        this.putMultiBox(++this.lo, this.hi);
                        break;
                    }
                }
                break;
            }
        }
    }
    
    static {
        backgroundColor = new Color(230, 230, 255);
        borderColor = Color.blue;
        barColor = Color.gray;
        finishedBarColor = Color.black;
        movingBarColor = Color.lightGray;
        movingBarOutlineColor = Color.gray;
        boxColor = Color.magenta;
        multiBoxColor = new Color(0, 200, 0);
        maxColor = Color.red;
    }
}
