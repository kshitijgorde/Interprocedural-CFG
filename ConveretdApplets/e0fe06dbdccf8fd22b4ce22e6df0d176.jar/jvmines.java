import java.awt.Event;
import java.awt.Insets;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.Panel;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jvmines extends Applet implements Runnable
{
    mines cm;
    int RowsXCols;
    Vector ArrayOfControl;
    Vector ArrayOfImages;
    boolean YouLose;
    boolean started;
    int founded;
    int flaged;
    MediaTracker tracker;
    Image img;
    Image flag;
    Image mina;
    Image ask;
    Image smile;
    Image smile1;
    Image smile2;
    Image strip;
    Panel p;
    custombutton cButt;
    digitaldisplay dd1;
    digitaldisplay dd2;
    Thread clock;
    int clockvalue;
    
    public void init() {
        this.tracker = new MediaTracker(this);
        this.img = this.getImage(this.getCodeBase(), "back.gif");
        this.tracker.addImage(this.img, 0);
        this.smile = this.getImage(this.getCodeBase(), "smile.gif");
        this.tracker.addImage(this.smile, 0);
        this.smile1 = this.getImage(this.getCodeBase(), "smile1.gif");
        this.tracker.addImage(this.smile1, 0);
        this.smile2 = this.getImage(this.getCodeBase(), "smile2.gif");
        this.tracker.addImage(this.smile2, 0);
        this.strip = this.getImage(this.getCodeBase(), "strip.gif");
        this.tracker.addImage(this.strip, 0);
        this.ArrayOfControl = new Vector();
        this.ArrayOfImages = new Vector();
        this.p = new Panel();
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.mina = this.createImage(new FilteredImageSource(this.strip.getSource(), new CropImageFilter(88, 0, 11, 11)));
        this.ask = this.createImage(new FilteredImageSource(this.strip.getSource(), new CropImageFilter(99, 0, 11, 11)));
        this.flag = this.createImage(new FilteredImageSource(this.strip.getSource(), new CropImageFilter(110, 0, 11, 11)));
        this.ArrayOfImages.setSize(17);
        for (int i = 1; i <= 8; ++i) {
            this.ArrayOfImages.setElementAt(this.createImage(new FilteredImageSource(this.strip.getSource(), new CropImageFilter((i - 1) * 11, 0, 11, 11))), i);
        }
        this.ArrayOfImages.setElementAt(this.mina, 16);
        this.setLayout(new FlowLayout(1, 5, 10));
        this.cButt = new custombutton(this.smile, this, 30, 30);
        this.dd1 = new digitaldisplay(false, 2, Color.black, Color.red);
        this.add(this.dd2 = new digitaldisplay(false, 2, Color.black, Color.red));
        this.add(this.cButt);
        this.add(this.dd1);
    }
    
    public void start() {
        if (!this.started) {
            this.started = true;
            this.startGame();
        }
    }
    
    void startGame() {
        this.cButt.setImage(this.smile);
        this.clockvalue = 99;
        this.cm = new mines(8, 8, 10);
        this.RowsXCols = this.cm.Rows() * this.cm.Cols();
        this.p.setLayout(new GridLayout(this.cm.Rows(), this.cm.Cols()));
        for (int i = 0; i < this.RowsXCols; ++i) {
            final cell cell = new cell(null, this, 16, 16);
            this.p.add(cell);
            this.ArrayOfControl.addElement(cell);
        }
        this.dd2.SetValue(Integer.toString(this.cm.Mines()));
        this.add(this.p);
        this.p.validate();
        if (this.clock == null) {
            (this.clock = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.clock = null;
    }
    
    public void run() {
        while (this.clock != null) {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
            --this.clockvalue;
            if (this.clockvalue == 0) {
                this.YouLose = true;
                this.cButt.setImage(this.smile2);
                this.clock = null;
                this.ShowAll();
            }
            else {
                this.dd1.SetValue(Integer.toString(this.clockvalue));
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.tracker.checkAll()) {
            graphics.drawImage(this.img, 0, 0, this);
        }
    }
    
    public Insets insets() {
        return new Insets(28, 0, 0, 0);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (event.target instanceof custombutton) {
            this.started = true;
            this.YouLose = false;
            this.founded = 0;
            this.flaged = 0;
            this.clockvalue = 99;
            this.p.removeAll();
            this.ArrayOfControl.setSize(0);
            this.startGame();
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final cell cell = (cell)event.target;
        final int index = this.ArrayOfControl.indexOf(event.target);
        final int get_play_field = this.cm.get_play_field(index);
        if (this.YouLose) {
            return true;
        }
        if ((get_play_field & 0x20) != 0x0) {
            return true;
        }
        if (event.modifiers != 4) {
            if (get_play_field == 16) {
                this.YouLose = true;
                this.cButt.setImage(this.smile2);
                this.clock = null;
                this.ShowAll();
            }
            this.cm.toShow.setSize(0);
            this.cm.Show(index / this.cm.Rows(), index % this.cm.Rows());
            for (int i = 0; i < this.cm.toShow.size(); ++i) {
                final int intValue = this.cm.toShow.elementAt(i);
                final cell cell2 = this.ArrayOfControl.elementAt(intValue);
                final int get_play_field2 = this.cm.get_play_field(intValue);
                cell2.setImage(this.IntToImage(get_play_field2 & 0x1F));
                cell2.disableCell();
                final int n3 = get_play_field2 | 0x20;
                ++this.founded;
                if (this.founded == this.RowsXCols - this.cm.Mines()) {
                    this.YouLose = true;
                    this.cButt.setImage(this.smile1);
                    this.clock = null;
                }
                this.cm.set_play_field(intValue, n3);
            }
            return true;
        }
        if (cell.image == this.flag) {
            cell.setImage(this.ask);
            --this.flaged;
            this.dd2.SetValue(Integer.toString(this.cm.Mines() - this.flaged));
            return true;
        }
        if (cell.image == this.ask) {
            cell.setImage(null);
            return true;
        }
        cell.setImage(this.flag);
        ++this.flaged;
        this.dd2.SetValue(Integer.toString(this.cm.Mines() - this.flaged));
        return true;
    }
    
    void ShowAll() {
        for (int i = 0; i < this.ArrayOfControl.size(); ++i) {
            final cell cell = this.ArrayOfControl.elementAt(i);
            final int get_play_field = this.cm.get_play_field(i);
            if (get_play_field == 16) {
                cell.setImage(this.IntToImage(get_play_field));
            }
        }
    }
    
    Image IntToImage(final int n) {
        return this.ArrayOfImages.elementAt(n);
    }
    
    public jvmines() {
        this.YouLose = false;
        this.started = false;
        this.clockvalue = 99;
    }
}
