import java.awt.Frame;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NF4 extends Applet
{
    NFControls controls;
    NFCanvas canvas;
    PlainData[] data;
    int num;
    int c;
    Font dataFont;
    int columns;
    int[][] values;
    String[] name;
    
    public void init() {
        this.dataFont = new Font("Courier", 1, 16);
        final String parameter = this.getParameter("columns");
        if (parameter == null) {
            this.columns = 5;
        }
        else {
            this.columns = Integer.parseInt(parameter);
        }
        final Vector vector = new Vector<PlainData>();
        while (true) {
            final PlainData plainData = new PlainData();
            final String parameter2 = this.getParameter("a" + this.num);
            if (parameter2 == null) {
                break;
            }
            plainData.init(parameter2);
            vector.addElement(plainData);
            ++this.num;
        }
        vector.copyInto(this.data = new PlainData[vector.size()]);
        --this.num;
        this.values = new int[this.num][this.columns];
        for (int i = 0; i < this.num; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                this.values[i][j] = this.data[i].item[j];
            }
        }
        this.name = new String[this.num];
        for (int k = 0; k < this.num; ++k) {
            this.name[k] = this.data[k].name;
        }
        this.setLayout(new BorderLayout());
        final NFCanvas nfCanvas = new NFCanvas();
        this.add("Center", nfCanvas);
        this.add("North", this.controls = new NFControls(nfCanvas));
        nfCanvas.add(this, this.values, this.columns, this.num, this.name);
    }
    
    public void start() {
        this.controls.start();
    }
    
    public void stop() {
        this.controls.stop();
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("NF4");
        final NF4 nf4 = new NF4();
        nf4.init();
        nf4.start();
        frame.add("Center", nf4);
        frame.resize(550, 600);
        frame.show();
    }
    
    public NF4() {
        this.num = 1;
    }
}
