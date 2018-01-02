import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SearchItem extends JPanel implements Runnable
{
    private Thread kicker;
    private double[] arr;
    public int h1;
    public int h2;
    public int h3;
    public int numsIndex;
    public int comparisons;
    public int animSpeed;
    public double numToFind;
    private int lineWidth;
    private String algName;
    private Color bgcolor;
    private SearchAlgorithm algorithm;
    private boolean haveASort;
    private SearchApplet parent;
    
    void pause() {
        this.h1 = -1;
        this.h2 = -1;
        this.h3 = -1;
        if (this.kicker != null) {
            this.repaint();
        }
        try {
            Thread.sleep(this.animSpeed);
        }
        catch (InterruptedException ex) {}
    }
    
    void pause(final int H1) {
        this.h1 = H1;
        this.h2 = -1;
        this.h3 = -1;
        if (this.kicker != null) {
            this.repaint();
        }
        try {
            Thread.sleep(this.animSpeed);
        }
        catch (InterruptedException ex) {}
    }
    
    void pause(final int H1, final int H2) {
        this.h1 = H1;
        this.h2 = H2;
        this.h3 = -1;
        if (this.kicker != null) {
            this.repaint();
        }
        try {
            Thread.sleep(this.animSpeed);
        }
        catch (InterruptedException ex) {}
    }
    
    void pause(final int H1, final int H2, final int H3) {
        this.h1 = H1;
        this.h2 = H2;
        this.h3 = H3;
        if (this.kicker != null) {
            this.repaint();
        }
        try {
            Thread.sleep(this.animSpeed);
        }
        catch (InterruptedException ex) {}
    }
    
    public SearchItem(final String at, final double[] a, final Color _bgcolor, final SearchApplet _parent, final double _numToFind) {
        this.h1 = -1;
        this.h2 = -1;
        this.h3 = -1;
        this.numsIndex = -1;
        this.comparisons = 0;
        this.animSpeed = 10;
        this.numToFind = -1.0;
        this.lineWidth = 1;
        this.bgcolor = Color.yellow;
        this.algName = at + "Algorithm";
        this.arr = a;
        this.bgcolor = _bgcolor;
        this.parent = _parent;
        this.numToFind = _numToFind;
        this.h2 = 0;
        this.h3 = this.arr.length - 1;
        for (int i = 0; i < this.arr.length; ++i) {
            if (this.arr[i] == this.numToFind) {
                this.numsIndex = i;
            }
        }
    }
    
    public void setSize(final int width, final int height) {
        super.setSize(width, height);
        double temp = this.getWidth();
        int ctr = 0;
        while (true) {
            temp -= this.arr.length * 2;
            if (temp < 0.0) {
                break;
            }
            ++ctr;
        }
        this.lineWidth = ctr;
    }
    
    public void setAnimationSpeed(final int s) {
        this.animSpeed = s;
    }
    
    public void paint(final Graphics g) {
        final int height = this.getHeight();
        final double[] a = this.arr;
        final double e = this.parent.getNumOfSelections() / this.getHeight();
        int y = this.getWidth() - 1;
        g.setColor(this.bgcolor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(this.bgcolor);
        int i = a.length;
        while (--i >= 0) {
            for (int count = 0; count < this.lineWidth; ++count) {
                g.drawLine(y - count, height - (int)Math.ceil(this.arr[i] / e), y - count, 0);
            }
            y -= 2 * this.lineWidth;
        }
        g.setColor(Color.black);
        y = this.getWidth() - 1;
        i = a.length;
        while (--i > this.h3) {
            for (int count = 0; count < this.lineWidth; ++count) {
                g.drawLine(y - count, height, y - count, height - (int)Math.ceil(this.arr[i] / e));
            }
            y -= 2 * this.lineWidth;
        }
        g.setColor(Color.blue);
        while (--i >= this.h2) {
            for (int count = 0; count < this.lineWidth; ++count) {
                g.drawLine(y - count, height, y - count, height - (int)Math.ceil(this.arr[i] / e));
            }
            y -= 2 * this.lineWidth;
        }
        g.setColor(Color.black);
        while (--i >= 0) {
            for (int count = 0; count < this.lineWidth; ++count) {
                g.drawLine(y - count, height, y - count, height - (int)Math.ceil(this.arr[i] / e));
            }
            y -= 2 * this.lineWidth;
        }
        final int offset = this.getWidth() - (a.length - 1) * this.lineWidth * 2;
        if (this.h1 >= 0) {
            g.setColor(Color.red);
            y = this.h1 * 2 * this.lineWidth + this.lineWidth + offset;
            for (int count2 = 0; count2 < this.lineWidth; ++count2) {
                g.drawLine(y + count2, height, y + count2, 0);
            }
        }
        g.setColor(Color.orange);
        y = this.numsIndex * 2 * this.lineWidth + this.lineWidth + offset;
        for (int count2 = 0; count2 < this.lineWidth; ++count2) {
            g.drawLine(y + count2, height, y + count2, height - (int)Math.ceil(this.arr[this.numsIndex] / e));
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void run() {
        try {
            if (this.algorithm == null) {
                (this.algorithm = (SearchAlgorithm)Class.forName(this.algName).newInstance()).setParent(this);
            }
            this.algorithm.init();
            this.comparisons = this.algorithm.search(this.arr, this.numToFind);
            this.parent.stopSearch(this);
        }
        catch (Exception e) {
            System.out.println("Sorting Algorithm Class " + this.algName + " Not found");
            e.printStackTrace();
        }
    }
    
    public synchronized void stop() {
        if (this.algorithm != null) {
            try {
                this.algorithm.stop();
            }
            catch (IllegalThreadStateException ex) {}
        }
    }
    
    public synchronized void startSearch() {
        if (this.kicker == null || !this.kicker.isAlive()) {
            this.repaint();
            (this.kicker = new Thread(this)).start();
        }
    }
}
