import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SortingPanel extends JPanel
{
    private static final Color BAR_COLOR;
    private int _width;
    private int _height;
    private int _numOfBars;
    private int _w98;
    private int _h98;
    private int _h05;
    private int _h95;
    private JPanel[] _bars;
    private JProgressBar _progress;
    private int _idxRed;
    private int _idxBlue;
    
    public SortingPanel(final int x, final int y, final int width, final int height, final int numOfBars) {
        super(null);
        this.setBounds(x, y, width, height);
        this.setBackground(Main.WINDOW_COLOR);
        this._width = width;
        this._height = height;
        this._numOfBars = numOfBars;
        this._w98 = this._width - 2;
        this._h98 = this._height - 2;
        this._h05 = this._h98 / 20;
        this._h95 = this._h98 - this._h05;
        this._bars = new JPanel[100];
        this._idxRed = 0;
        this._idxBlue = numOfBars - 1;
        for (int i = 0; i < numOfBars; ++i) {
            final int xi = 2 * i * this._w98 / (2 * numOfBars - 1) + 1;
            final int wi = (2 * i + 1) * this._w98 / (2 * numOfBars - 1) + 1 - xi;
            final int hi = (i + 1) * this._h95 / numOfBars + this._h05;
            final int yi = this._height - hi - 1;
            this.add(this._bars[i] = this.getBar(xi, yi, wi, hi));
        }
        this._bars[numOfBars - 1].setSize(this._bars[numOfBars - 1].getWidth() - 1, this._bars[numOfBars - 1].getHeight());
        (this._progress = new JProgressBar(1, 0, 100)).setOpaque(false);
        this._progress.setBorderPainted(false);
        this._progress.setBounds(0, 0, this._width, this._height);
        this._progress.setBackground(Main.WINDOW_COLOR);
        this._progress.setValue(0);
        this.add(this._progress);
        this.pMixBars();
    }
    
    public void setProgress(final int percent) {
        this._progress.setValue(percent);
        this._progress.repaint();
    }
    
    public void setColor(final int index, final Color color) {
        this._bars[index].setBackground(color);
    }
    
    public void setColorRed(final int index) {
        final JPanel[] bars = this._bars;
        this._idxRed = index;
        bars[index].setBackground(Color.RED);
    }
    
    public void setColorBlue(final int index) {
        final JPanel[] bars = this._bars;
        this._idxBlue = index;
        bars[index].setBackground(Color.ORANGE);
    }
    
    public void setColorBlack() {
        this._bars[this._idxRed].setBackground(SortingPanel.BAR_COLOR);
        this._bars[this._idxBlue].setBackground(SortingPanel.BAR_COLOR);
    }
    
    public void setHeight(final int index, final int height) {
        this._bars[index].setBounds(this._bars[index].getX(), this._height - height - 1, this._bars[index].getWidth(), height);
    }
    
    public int getHeight(final int index) {
        return (this._bars[index] == null) ? -1 : this._bars[index].getHeight();
    }
    
    public int getX(final int index) {
        return (this._bars[index] == null) ? -1 : this._bars[index].getX();
    }
    
    public int getWidth(final int index) {
        return (this._bars[index] == null) ? -1 : this._bars[index].getWidth();
    }
    
    public int getNumOfBars() {
        return this._numOfBars;
    }
    
    public void setNumOfBars(final SortingPanel panel) {
        final int numOfBars = panel.getNumOfBars();
        this._progress.setValue(0);
        this.remove(this._progress);
        this._idxRed = 0;
        this._idxBlue = numOfBars - 1;
        if (numOfBars < this._numOfBars) {
            for (int i = this._numOfBars - 1; i >= numOfBars; --i) {
                try {
                    this.remove(this._bars[i]);
                }
                catch (Exception ex) {}
                this._bars[i] = null;
            }
        }
        else if (numOfBars > this._numOfBars) {
            for (int i = this._numOfBars; i < numOfBars; ++i) {
                this.add(this._bars[i] = this.getBar(0, 0, 0, 0));
            }
        }
        this._numOfBars = numOfBars;
        for (int i = 0; i < numOfBars; ++i) {
            final int xi = panel.getX(i);
            final int wi = panel.getWidth(i);
            final int hi = panel.getHeight(i);
            final int yi = this._height - hi - 1;
            this._bars[i].setBackground(SortingPanel.BAR_COLOR);
            this._bars[i].setBounds(xi, yi, wi, hi);
        }
        this.add(this._progress);
    }
    
    public void setNumOfBars(final int numOfBars) {
        this._idxRed = 0;
        this._idxBlue = numOfBars - 1;
        this._progress.setValue(0);
        this.remove(this._progress);
        if (numOfBars < this._numOfBars) {
            for (int i = this._numOfBars - 1; i >= numOfBars; --i) {
                try {
                    this.remove(this._bars[i]);
                }
                catch (Exception ex) {}
                this._bars[i] = null;
            }
        }
        else if (numOfBars > this._numOfBars) {
            for (int i = this._numOfBars; i < numOfBars; ++i) {
                this.add(this._bars[i] = this.getBar(0, 0, 0, 0));
            }
        }
        this._numOfBars = numOfBars;
        for (int i = 0; i < numOfBars; ++i) {
            final int xi = 2 * i * this._w98 / (2 * numOfBars - 1) + 1;
            final int wi = (2 * i + 1) * this._w98 / (2 * numOfBars - 1) + 1 - xi;
            final int hi = (i + 1) * this._h95 / numOfBars + this._h05;
            final int yi = this._height - hi - 1;
            this._bars[i].setBackground(SortingPanel.BAR_COLOR);
            this._bars[i].setBounds(xi, yi, wi, hi);
        }
        this._bars[numOfBars - 1].setSize(this._bars[numOfBars - 1].getWidth() - 1, this._bars[numOfBars - 1].getHeight());
        this.pMixBars();
        this.add(this._progress);
    }
    
    private void pMixBars() {
        for (int i = 0; i < this._numOfBars; ++i) {
            this._bars[i].setBackground(SortingPanel.BAR_COLOR);
            final int swap = Main.RANDOM.nextInt(this._numOfBars - i) + i;
            if (swap != i) {
                final int tmpHeight = this._bars[i].getHeight();
                this._bars[i].setBounds(this._bars[i].getX(), this._height - this._bars[swap].getHeight() - 1, this._bars[i].getWidth(), this._bars[swap].getHeight());
                this._bars[swap].setBounds(this._bars[swap].getX(), this._height - tmpHeight - 1, this._bars[swap].getWidth(), tmpHeight);
            }
        }
        this.repaint(0, 0, this._width, this._height);
    }
    
    private void pOrderBars() {
        for (int i = 0; i < this._numOfBars; ++i) {
            final int xi = this._bars[i].getX();
            final int wi = this._bars[i].getWidth();
            final int hi = (i + 1) * this._h95 / this._numOfBars + this._h05;
            final int yi = this._height - hi - 1;
            this._bars[i].setBounds(xi, yi, wi, hi);
        }
        this.repaint(0, 0, this._width, this._height);
    }
    
    private JPanel getBar(final int x, final int y, final int width, final int height) {
        final JPanel panel = new JPanel(null);
        panel.setBounds(x, y, width, height);
        panel.setBackground(SortingPanel.BAR_COLOR);
        return panel;
    }
    
    static {
        BAR_COLOR = Color.BLACK;
    }
}
