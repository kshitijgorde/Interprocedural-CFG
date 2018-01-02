// 
// Decompiled by Procyon v0.5.30
// 

package tmcm.xSortLab;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Random;
import java.awt.Color;
import java.awt.Canvas;

final class TimedSort extends Canvas implements Runnable
{
    static final int computeInterval = 400;
    static final int pauseInterval = 100;
    static final int IDLE = 0;
    static final int RUN = 2;
    static final int ABORT = 3;
    static final int ERROR = 4;
    static final int APPLETSTOPPED = 5;
    int state;
    int saveState;
    String errorMessage;
    static final Color backgroundColor;
    static final Color borderColor;
    static final Color labelColor;
    static final Color statsColor;
    static final String[] dataLabel;
    TimedSortPanel owner;
    LogPanel log;
    int[] A;
    int[] B;
    Random rand;
    int sortMethod;
    int arraySize;
    int arrayCt;
    long comparisonCt;
    long copyCt;
    int arraysSorted;
    long comparisonsSinceLastCheck;
    long startTime;
    long lastPauseTime;
    long totalPausedTime;
    long elapsedTimeForDisplay;
    long computeTimeForDisplay;
    long comparisonCtForDisplay;
    long copyCtForDisplay;
    int arraysSortedForDisplay;
    int width;
    int height;
    Font font;
    FontMetrics fm;
    Thread runner;
    
    TimedSort(final TimedSortPanel owner, final LogPanel log) {
        this.state = 0;
        this.saveState = 0;
        this.rand = new Random();
        this.width = -1;
        this.owner = owner;
        this.log = log;
        this.setBackground(TimedSort.backgroundColor);
        this.sortMethod = -1;
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.size().width != this.width || this.size().height != this.height || this.font == null) {
            this.width = this.size().width;
            this.height = this.size().height;
            this.font = new Font("TimesRoman", 0, 18);
            this.fm = graphics.getFontMetrics(this.font);
            if (this.fm.stringWidth("Approximate Compute Time: ") > this.width / 2 || this.fm.getHeight() * 8 > this.height) {
                this.font = new Font("TimesRoman", 0, 14);
                this.fm = graphics.getFontMetrics(this.font);
                if (this.fm.stringWidth("Approximate Compute Time: ") > this.width / 2 || this.fm.getHeight() * 8 > this.height) {
                    this.font = new Font("TimesRoman", 0, 12);
                    this.fm = graphics.getFontMetrics(this.font);
                    if (this.fm.stringWidth("Approximate Compute Time: ") > this.width / 2 || this.fm.getHeight() * 8 > this.height) {
                        this.font = new Font("TimesRoman", 0, 10);
                        this.fm = graphics.getFontMetrics(this.font);
                    }
                }
            }
        }
        graphics.setFont(this.font);
        graphics.setColor(TimedSort.borderColor);
        graphics.drawRect(0, 0, this.width, this.height);
        graphics.drawRect(1, 1, this.width - 2, this.height - 2);
        graphics.drawLine(this.width - 2, 0, this.width - 2, this.height);
        graphics.drawLine(0, this.height - 2, this.width, this.height - 2);
        if (this.state == 3) {
            final int stringWidth = this.fm.stringWidth("OPERATION ABORTED.");
            graphics.setColor(TimedSort.statsColor);
            graphics.drawString("OPERATION ABORTED", (this.width - stringWidth) / 2, this.height / 2);
        }
        else if (this.state == 4) {
            final int stringWidth2 = this.fm.stringWidth(this.errorMessage);
            graphics.setColor(TimedSort.statsColor);
            graphics.drawString(this.errorMessage, (this.width - stringWidth2) / 2, this.height / 2);
        }
        else if (this.sortMethod == -1) {
            graphics.setColor(TimedSort.labelColor);
            final int height = this.fm.getHeight();
            graphics.drawString("Enter the array size and number of arrays.", height, 2 * height);
            graphics.drawString("Select a sort method.", height, 3 * height);
            graphics.drawString("Click \"Start Sorting\" to begin.", height, 4 * height);
        }
        else {
            final int height2 = this.fm.getHeight();
            final int n = (this.height - 7 * height2 + this.fm.getLeading()) / 2 + this.fm.getAscent();
            final Rectangle clipRect = graphics.getClipRect();
            if (clipRect.x < this.width / 2) {
                graphics.setColor(TimedSort.labelColor);
                for (int i = 0; i < 7; ++i) {
                    graphics.drawString(TimedSort.dataLabel[i], this.width / 2 - this.fm.stringWidth(TimedSort.dataLabel[i]), n + height2 * i);
                }
                if (this.getState() == 0) {
                    graphics.setColor(TimedSort.statsColor);
                    graphics.drawString("DONE!", 10, this.fm.getAscent() + 10);
                }
            }
            if (clipRect.x + clipRect.width > this.width / 2) {
                graphics.setColor(TimedSort.statsColor);
                final int n2 = this.width / 2 + 4;
                final TimedSortPanel owner = this.owner;
                graphics.drawString(TimedSortPanel.sortName[this.sortMethod], n2, n);
                graphics.drawString("" + this.arraySize, n2, n + height2);
                graphics.drawString("" + this.arraysSortedForDisplay + " of " + this.arrayCt, n2, n + 2 * height2);
                graphics.drawString("" + this.divideBy1000(this.elapsedTimeForDisplay) + " seconds", n2, n + 3 * height2);
                graphics.drawString("" + this.divideBy1000(this.computeTimeForDisplay) + " seconds", n2, n + 4 * height2);
                graphics.drawString("" + this.comparisonCtForDisplay, n2, n + 5 * height2);
                graphics.drawString("" + this.copyCtForDisplay, n2, n + 6 * height2);
            }
        }
    }
    
    String divideBy1000(final long n) {
        if (n == 0L) {
            return "0";
        }
        final long n2 = n / 1000L;
        final long n3 = n - 1000L * n2;
        if (n3 > 99L) {
            return "" + n2 + '.' + n3;
        }
        if (n3 > 9L) {
            return "" + n2 + ".0" + n3;
        }
        return "" + n2 + ".00" + n3;
    }
    
    synchronized void doAppletStop() {
        this.saveState = this.state;
        this.state = 5;
        if (this.saveState == 2) {
            this.notify();
        }
    }
    
    synchronized void doAppletStart() {
        this.state = this.saveState;
        if (this.state == 2) {
            this.notify();
        }
    }
    
    synchronized void report(final long elapsedTimeForDisplay, final long computeTimeForDisplay, final long comparisonCtForDisplay, final long copyCtForDisplay, final int arraysSortedForDisplay) {
        this.elapsedTimeForDisplay = elapsedTimeForDisplay;
        this.computeTimeForDisplay = computeTimeForDisplay;
        this.comparisonCtForDisplay = comparisonCtForDisplay;
        this.copyCtForDisplay = copyCtForDisplay;
        this.arraysSortedForDisplay = arraysSortedForDisplay;
    }
    
    synchronized void start(final int sortMethod, final int arraySize, final int arrayCt) {
        if (this.state == 2) {
            return;
        }
        this.state = 2;
        this.sortMethod = sortMethod;
        this.arraySize = arraySize;
        this.arrayCt = arrayCt;
        this.report(0L, 0L, 0L, 0L, 0);
        this.repaint();
        if (this.runner == null || !this.runner.isAlive()) {
            (this.runner = new Thread(this)).start();
        }
        this.notify();
    }
    
    synchronized void setState(final int state) {
        this.state = state;
        this.notify();
    }
    
    synchronized int getState() {
        return this.state;
    }
    
    synchronized void setError(final String errorMessage) {
        this.state = 4;
        this.errorMessage = errorMessage;
        this.repaint();
    }
    
    synchronized void doWait(final int n) {
        try {
            this.wait(n);
        }
        catch (InterruptedException ex) {}
    }
    
    boolean compare(final boolean b) {
        ++this.comparisonCt;
        ++this.comparisonsSinceLastCheck;
        if (this.comparisonsSinceLastCheck == 10000L) {
            this.comparisonsSinceLastCheck = 0L;
            final long currentTimeMillis = System.currentTimeMillis();
            final int state = this.getState();
            if (state == 3) {
                throw new SortAbort();
            }
            if (state == 5) {
                synchronized (this) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                final long currentTimeMillis2 = System.currentTimeMillis();
                this.totalPausedTime += currentTimeMillis2 - currentTimeMillis;
                this.lastPauseTime = currentTimeMillis2;
            }
            else if (currentTimeMillis - this.lastPauseTime >= 400L) {
                this.report(currentTimeMillis - this.startTime, currentTimeMillis - this.startTime - this.totalPausedTime, this.comparisonCt, this.copyCt, this.arraysSorted);
                this.repaint(this.size().width / 2 + 1, 4, this.size().width / 2 - 5, this.size().height - 8);
                this.doWait(100);
                if (this.getState() == 3) {
                    throw new SortAbort();
                }
                final long currentTimeMillis3 = System.currentTimeMillis();
                this.totalPausedTime += currentTimeMillis3 - currentTimeMillis;
                this.lastPauseTime = currentTimeMillis3;
            }
        }
        return b;
    }
    
    void swap(final int n, final int n2) {
        final int n3 = this.A[n];
        this.A[n] = this.A[n2];
        this.A[n2] = n3;
        this.copyCt += 3L;
    }
    
    void bubbleSort(final int n, final int n2) {
        for (int i = n2; i > n; --i) {
            for (int j = n; j < i; ++j) {
                if (this.compare(this.A[j] > this.A[j + 1])) {
                    this.swap(j, j + 1);
                }
            }
        }
    }
    
    void selectionSort(final int n, final int n2) {
        for (int i = n2; i > n; --i) {
            int n3 = n;
            for (int j = n + 1; j <= i; ++j) {
                if (this.compare(this.A[j] > this.A[n3])) {
                    n3 = j;
                }
            }
            this.swap(n3, i);
        }
    }
    
    void insertionSort(final int n, final int n2) {
        for (int i = n + 1; i <= n2; ++i) {
            final int n3 = this.A[i];
            ++this.copyCt;
            int n4;
            for (n4 = i - 1; n4 >= n && this.compare(this.A[n4] > n3); --n4) {
                this.A[n4 + 1] = this.A[n4];
                ++this.copyCt;
            }
            this.A[n4 + 1] = n3;
            ++this.copyCt;
        }
    }
    
    void doMerge(int n, final int n2, int n3, final int n4, final int n5, int n6) {
        for (int i = 0; i < n5; ++i) {
            if (n3 > n4) {
                this.B[n6++] = this.A[n++];
            }
            else if (n > n2) {
                this.B[n6++] = this.A[n3++];
            }
            else if (this.compare(this.A[n] < this.A[n3])) {
                this.B[n6++] = this.A[n++];
            }
            else {
                this.B[n6++] = this.A[n3++];
            }
            ++this.copyCt;
        }
    }
    
    void mergeSort(final int n, final int n2) {
        for (int n3 = n2 - n + 1, i = 1; i < n3; i *= 2) {
            int n6;
            for (int j = n; j <= n2; j = n6 + 1) {
                final int n4 = j + i;
                final int n5 = n4 - 1;
                n6 = n4 + i - 1;
                if (n5 >= n2) {
                    this.doMerge(j, n2, 0, -1, n2 - j + 1, j - n);
                }
                else if (n6 >= n2) {
                    this.doMerge(j, n5, n4, n2, n2 - j + 1, j - n);
                }
                else {
                    this.doMerge(j, n5, n4, n6, 2 * i, j - n);
                }
            }
            for (int k = 0; k < n3; ++k) {
                this.A[n + k] = this.B[k];
            }
            this.copyCt += n3;
        }
    }
    
    int quickSortStep(int n, int i) {
        final int n2 = this.A[i];
        ++this.copyCt;
        while (i > n) {
            while (i > n && this.compare(this.A[n] <= n2)) {
                ++n;
            }
            if (i > n) {
                this.A[i] = this.A[n];
                ++this.copyCt;
                --i;
                while (i > n && this.compare(this.A[i] >= n2)) {
                    --i;
                }
                if (i <= n) {
                    continue;
                }
                this.A[n] = this.A[i];
                ++this.copyCt;
                ++n;
            }
        }
        this.A[i] = n2;
        ++this.copyCt;
        return i;
    }
    
    void quickSort(final int n, final int n2) {
        if (n2 > n) {
            final int quickSortStep = this.quickSortStep(n, n2);
            if (quickSortStep - n > n2 - quickSortStep) {
                this.quickSort(quickSortStep + 1, n2);
                this.quickSort(n, quickSortStep - 1);
            }
            else {
                this.quickSort(n, quickSortStep - 1);
                this.quickSort(quickSortStep + 1, n2);
            }
        }
    }
    
    void doSort() {
        final int n = this.arraySize * this.arrayCt;
        this.A = new int[n];
        if (this.sortMethod == 3) {
            this.B = new int[this.arraySize];
        }
        for (int i = 0; i < n; ++i) {
            this.A[i] = Math.abs(this.rand.nextInt());
        }
        System.gc();
        this.owner.readyToStart();
        this.doWait(100);
        if (this.getState() == 3) {
            throw new SortAbort();
        }
        this.comparisonsSinceLastCheck = 0L;
        this.copyCt = 0L;
        this.comparisonCt = 0L;
        this.totalPausedTime = 0L;
        this.startTime = System.currentTimeMillis();
        this.lastPauseTime = this.startTime;
        this.arraysSorted = 0;
        int j = 0;
        int n2 = this.arraySize - 1;
        switch (this.sortMethod) {
            case 0: {
                while (j < n) {
                    this.bubbleSort(j, n2);
                    j = n2 + 1;
                    n2 += this.arraySize;
                    ++this.arraysSorted;
                }
                break;
            }
            case 1: {
                while (j < n) {
                    this.selectionSort(j, n2);
                    j = n2 + 1;
                    n2 += this.arraySize;
                    ++this.arraysSorted;
                }
                break;
            }
            case 2: {
                while (j < n) {
                    this.insertionSort(j, n2);
                    j = n2 + 1;
                    n2 += this.arraySize;
                    ++this.arraysSorted;
                }
                break;
            }
            case 3: {
                while (j < n) {
                    this.mergeSort(j, n2);
                    j = n2 + 1;
                    n2 += this.arraySize;
                    ++this.arraysSorted;
                }
                break;
            }
            case 4: {
                while (j < n) {
                    this.quickSort(j, n2);
                    j = n2 + 1;
                    n2 += this.arraySize;
                    ++this.arraysSorted;
                }
                break;
            }
        }
        final long currentTimeMillis = System.currentTimeMillis();
        this.report(currentTimeMillis - this.startTime, currentTimeMillis - this.startTime - this.totalPausedTime, this.comparisonCt, this.copyCt, this.arraysSorted);
        if (this.arrayCt == 1) {
            final LogPanel log = this.log;
            final StringBuffer sb = new StringBuffer();
            final TimedSortPanel owner = this.owner;
            log.addLine(sb.append(TimedSortPanel.sortName[this.sortMethod]).append(" applied to 1 array containing ").append(this.arraySize).append(" items:").toString());
        }
        else {
            final LogPanel log2 = this.log;
            final StringBuffer sb2 = new StringBuffer();
            final TimedSortPanel owner2 = this.owner;
            log2.addLine(sb2.append(TimedSortPanel.sortName[this.sortMethod]).append(" applied to ").append(this.arrayCt).append(" arrays, each containing ").append(this.arraySize).append(" items:").toString());
        }
        this.log.addLine("   Elapsed Time: " + this.divideBy1000(currentTimeMillis - this.startTime) + " seconds");
        this.log.addLine("   Approximate Compute Time: " + this.divideBy1000(currentTimeMillis - this.startTime - this.totalPausedTime) + " seconds");
        this.log.addLine("   Number of comparisons: " + this.comparisonCt);
        this.log.addLine("   Number of copies: " + this.copyCt);
        this.log.addEoln();
    }
    
    public void run() {
        while (true) {
            synchronized (this) {
                while (this.state != 2) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                try {
                    this.wait(100L);
                }
                catch (InterruptedException ex2) {}
                if (this.state == 3) {
                    this.repaint();
                    this.owner.doneRunning();
                    continue;
                }
            }
            try {
                this.doSort();
                this.setState(0);
                this.repaint();
            }
            catch (SortAbort sortAbort) {
                this.repaint();
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.setError("Not Enough Memory; use smaller or fewer arrays.");
            }
            catch (RuntimeException ex3) {
                this.setError("Sorry, an unexpected error occurred!");
            }
            final int[] array = null;
            this.B = array;
            this.A = array;
            this.owner.doneRunning();
        }
    }
    
    static {
        backgroundColor = new Color(230, 255, 230);
        borderColor = new Color(0, 127, 0);
        labelColor = TimedSort.borderColor;
        statsColor = Color.red;
        dataLabel = new String[] { "Sort Method: ", "Items per Array: ", "Arrays Sorted: ", "Elapsed Time: ", "Approximate Compute Time: ", "Comparisons: ", "Copies: " };
    }
}
