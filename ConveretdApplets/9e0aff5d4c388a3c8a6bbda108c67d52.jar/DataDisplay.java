import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class DataDisplay extends JPanel
{
    private boolean asource;
    private boolean bsource;
    private boolean csource;
    private int mode;
    private int dataLeft;
    final Color green;
    final Color yellow;
    final Color orange;
    private Color a;
    private Color b;
    private Color c;
    private String[] data;
    private String dataToTransfer;
    private double tolerance;
    private double fadeInc;
    
    public DataDisplay() {
        this.green = Color.green;
        this.yellow = Color.yellow;
        this.orange = Color.orange;
        this.dataToTransfer = null;
        this.tolerance = 0.2;
        this.fadeInc = 0.15;
        this.setPreferredSize(new Dimension(480, 40));
        this.setStartState();
    }
    
    public boolean isMoreData() {
        return this.dataLeft > 0;
    }
    
    public void setStartState() {
        this.asource = true;
        this.bsource = true;
        this.csource = true;
        this.a = this.orange;
        this.b = this.green;
        this.c = this.yellow;
        this.mode = 0;
        this.dataLeft = 3;
        (this.data = new String[3])[0] = "A";
        this.data[1] = "B";
        this.data[2] = "C";
    }
    
    public void setDataTransfered() {
        if (this.dataToTransfer.equals("A")) {
            this.setMode(4);
        }
        else if (this.dataToTransfer.equals("B")) {
            this.setMode(5);
        }
        else if (this.dataToTransfer.equals("C")) {
            this.setMode(6);
        }
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public String getData() {
        if (this.dataLeft == 0) {
            return null;
        }
        int ind;
        do {
            ind = (int)Math.floor(Math.random() * 3.0);
            if (ind == 3) {
                ind = 2;
            }
        } while (this.data[ind] == null);
        this.dataToTransfer = this.data[ind];
        this.data[ind] = null;
        --this.dataLeft;
        this.mode = ind + 1;
        return this.dataToTransfer;
    }
    
    public Color getColorOfData() {
        if (this.dataToTransfer.equals("A")) {
            return this.orange;
        }
        if (this.dataToTransfer.equals("B")) {
            return this.green;
        }
        if (this.dataToTransfer.equals("C")) {
            return this.yellow;
        }
        return null;
    }
    
    public void setMode(final int m) {
        if (m < 1 || m > 6) {
            return;
        }
        if (m == 4) {
            this.asource = false;
        }
        else if (m == 5) {
            this.bsource = false;
        }
        else if (m == 6) {
            this.csource = false;
        }
        this.mode = m;
    }
    
    private boolean fadeAToColor(final Color fadeTo) {
        this.a = Toolbox.getNextColor(this.a, fadeTo, this.tolerance, this.fadeInc);
        return this.a.equals(fadeTo);
    }
    
    private boolean fadeBToColor(final Color fadeTo) {
        this.b = Toolbox.getNextColor(this.b, fadeTo, this.tolerance, this.fadeInc);
        return this.b.equals(fadeTo);
    }
    
    private boolean fadeCToColor(final Color fadeTo) {
        this.c = Toolbox.getNextColor(this.c, fadeTo, this.tolerance, this.fadeInc);
        return this.c.equals(fadeTo);
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.drawString("Source Data", 12, 10);
        g.drawString("Sent Data", 397, 40);
        g.drawLine(100, 10, 330, 10);
        g.drawLine(330, 10, 330, 0);
        g.drawLine(330, 0, 380, 20);
        g.drawLine(100, 30, 330, 30);
        g.drawLine(330, 30, 330, 40);
        g.drawLine(330, 40, 380, 20);
        if (this.mode == 1) {
            if (this.fadeAToColor(this.getBackground())) {
                this.mode = 0;
            }
        }
        else if (this.mode == 2) {
            if (this.fadeBToColor(this.getBackground())) {
                this.mode = 0;
            }
        }
        else if (this.mode == 3) {
            if (this.fadeCToColor(this.getBackground())) {
                this.mode = 0;
            }
        }
        else if (this.mode == 4) {
            if (this.fadeAToColor(this.orange)) {
                this.mode = 0;
            }
        }
        else if (this.mode == 5) {
            if (this.fadeBToColor(this.green)) {
                this.mode = 0;
            }
        }
        else if (this.mode == 6 && this.fadeCToColor(this.yellow)) {
            this.mode = 0;
        }
        g.setColor(this.a);
        if (this.asource) {
            g.drawString("A", 30, 30);
        }
        else {
            g.drawString("A", 405, 25);
        }
        g.setColor(this.b);
        if (this.bsource) {
            g.drawString("B", 46, 30);
        }
        else {
            g.drawString("B", 421, 25);
        }
        g.setColor(this.c);
        if (this.csource) {
            g.drawString("C", 62, 30);
        }
        else {
            g.drawString("C", 437, 25);
        }
    }
}
