import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Font;
import java.awt.Label;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.MemoryImageSource;
import java.awt.List;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Scrollbar;
import java.awt.Image;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ItemListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Play3 extends Applet implements Runnable, ActionListener, AdjustmentListener, MouseMotionListener, ItemListener
{
    double redrawht;
    double redrawwt;
    Enlarge getEnlarge;
    RgbShiftImage rgb;
    SetContrast sc;
    Graphics gb;
    Button reset;
    Button invert;
    Button startThread;
    static Image img;
    static Image img2;
    int iw;
    int ih;
    int keepx;
    int keepy;
    int enlarge;
    Scrollbar red;
    Scrollbar green;
    Scrollbar blue;
    Scrollbar contrast;
    Scrollbar mag;
    Scrollbar nextImage;
    Scrollbar playtest;
    Scrollbar bright;
    Checkbox on;
    Checkbox off;
    CheckboxGroup onoffcheck;
    List picSelect;
    boolean move;
    boolean inside;
    Thread runner;
    int k;
    protected MemoryImageSource buffer;
    
    public Play3() {
        this.enlarge = 100;
        this.move = true;
        this.inside = false;
    }
    
    public void init() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                if (Play3.this.inside) {
                    if (Play3.this.move) {
                        Play3.this.move = false;
                    }
                    else {
                        Play3.this.move = true;
                    }
                }
            }
        });
        this.addMouseMotionListener(this);
        this.picSelect = new List(20, false);
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("Pics"), "+");
        this.k = 1;
        while (stringTokenizer.hasMoreTokens()) {
            ++this.k;
            this.picSelect.add(stringTokenizer.nextToken());
        }
        this.showStatus(this.k + " Total Images");
        (this.nextImage = new Scrollbar(0, 0, 1, 0, this.picSelect.getItemCount())).setBackground(Color.BLUE);
        this.nextImage.addAdjustmentListener(this);
        this.picSelect.addActionListener(this);
        this.onoffcheck = new CheckboxGroup();
        this.on = new Checkbox("ON", this.onoffcheck, false);
        this.off = new Checkbox("OFF", this.onoffcheck, true);
        this.on.addItemListener(this);
        this.off.addItemListener(this);
        this.reset = new Button("Reset");
        this.invert = new Button("Invert");
        this.startThread = new Button("Start");
        this.contrast = new Scrollbar(0, 0, 1, -255, 255);
        this.red = new Scrollbar(1, 0, 1, -255, 255);
        this.green = new Scrollbar(1, 0, 1, -255, 255);
        this.blue = new Scrollbar(1, 0, 1, -255, 255);
        this.mag = new Scrollbar(0, -50, 1, -200, 100);
        this.bright = new Scrollbar(0, 0, 1, -255, 255);
        this.mag.setUnitIncrement(2);
        this.red.setBackground(Color.RED);
        this.green.setBackground(Color.GREEN);
        this.blue.setBackground(Color.BLUE);
        this.bright.addAdjustmentListener(this);
        this.red.addAdjustmentListener(this);
        this.green.addAdjustmentListener(this);
        this.blue.addAdjustmentListener(this);
        this.contrast.addAdjustmentListener(this);
        this.mag.addAdjustmentListener(this);
        this.nextImage.setCursor(Cursor.getPredefinedCursor(12));
        this.bright.setCursor(Cursor.getPredefinedCursor(12));
        this.red.setCursor(Cursor.getPredefinedCursor(12));
        this.green.setCursor(Cursor.getPredefinedCursor(12));
        this.blue.setCursor(Cursor.getPredefinedCursor(12));
        this.mag.setCursor(Cursor.getPredefinedCursor(12));
        this.contrast.setCursor(Cursor.getPredefinedCursor(12));
        this.startThread.setCursor(Cursor.getPredefinedCursor(12));
        this.invert.setCursor(Cursor.getPredefinedCursor(12));
        this.reset.setCursor(Cursor.getPredefinedCursor(12));
        this.picSelect.setCursor(Cursor.getPredefinedCursor(12));
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        final Panel panel4 = new Panel();
        final Panel panel5 = new Panel();
        final Panel panel6 = new Panel();
        final Panel panel7 = new Panel();
        final Panel panel8 = new Panel();
        panel8.setLayout(new GridLayout(3, 0));
        panel5.add(this.picSelect);
        panel.setBackground(Color.gray);
        panel6.setBackground(Color.lightGray);
        final Panel panel9 = new Panel();
        panel2.setLayout(new GridLayout(3, 0));
        panel3.setLayout(new GridLayout(3, 0));
        panel4.setLayout(new GridLayout(4, 0));
        panel6.setLayout(new GridLayout(3, 0));
        panel7.add(this.on);
        panel7.add(this.off);
        panel2.add(new Label("R"));
        panel2.add(new Label("G"));
        panel2.add(new Label("B"));
        panel3.add(this.reset);
        panel3.add(this.invert);
        panel3.add(this.startThread);
        panel4.add(new Label(" Brightness"));
        panel4.add(this.bright);
        panel4.add(new Label(" Contrast"));
        panel4.add(this.contrast);
        this.setFont(new Font("Serif", 1, 15));
        panel6.add(new Label("  MAG Control "));
        panel6.add(panel7);
        panel6.add(this.mag);
        panel.add(panel3);
        panel9.setBackground(Color.lightGray);
        panel9.add(panel2);
        panel9.add(this.red);
        panel9.add(this.green);
        panel9.add(this.blue);
        panel.add(panel9);
        panel.add(new Label(" "));
        panel.add(panel4);
        panel.add(new Label(" "));
        panel.add(panel6);
        this.reset.addActionListener(this);
        this.invert.addActionListener(this);
        this.startThread.addActionListener(this);
        this.add("North", this.nextImage);
        this.add("South", panel);
        this.add("West", panel5);
        this.add("East", panel8);
        this.loadImage("1940martha.jpg");
        this.startThread.setBackground(Color.GREEN);
    }
    
    public void loadImage(String s) {
        try {
            this.setCursor(Cursor.getPredefinedCursor(3));
            s = s;
            Play3.img = this.getImage(this.getDocumentBase(), s);
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(Play3.img, 0);
            mediaTracker.waitForID(0);
            this.iw = Play3.img.getWidth(null);
            this.ih = Play3.img.getHeight(null);
            Play3.img2 = Play3.img;
            this.setCursor(Cursor.getPredefinedCursor(0));
            this.red.setValue(0);
            this.green.setValue(0);
            this.blue.setValue(0);
            this.contrast.setValue(0);
            this.bright.setValue(0);
            this.mag.setValue(-50);
            this.onoffcheck.setSelectedCheckbox(this.off);
            this.repaint();
        }
        catch (Exception ex) {}
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final Object source = adjustmentEvent.getSource();
        if (source == this.bright) {
            if (this.contrast.getValue() == 0) {
                this.rgb = new RgbShiftImage(Play3.img, this.bright.getValue(), this.bright.getValue(), this.bright.getValue());
            }
            else {
                this.sc = new SetContrast(Play3.img, this.contrast.getValue());
                this.rgb = new RgbShiftImage(this.sc.returnImg(), this.bright.getValue(), this.bright.getValue(), this.bright.getValue());
            }
            Play3.img2 = this.rgb.returnImg();
            this.repaint();
        }
        if (source == this.red || source == this.green || source == this.blue) {
            this.rgb = new RgbShiftImage(Play3.img, this.red.getValue(), this.green.getValue(), this.blue.getValue());
            this.contrast.setValue(0);
            this.bright.setValue(0);
            Play3.img2 = this.rgb.returnImg();
            this.repaint();
        }
        if (source == this.contrast) {
            if (this.bright.getValue() == 0) {
                this.sc = new SetContrast(Play3.img, this.contrast.getValue());
            }
            else {
                this.sc = new SetContrast(Play3.img, this.contrast.getValue());
                this.rgb = new RgbShiftImage(this.sc.returnImg(), this.bright.getValue(), this.bright.getValue(), this.bright.getValue());
            }
            Play3.img2 = this.rgb.returnImg();
            this.repaint();
            Play3.img2.flush();
        }
        if (source == this.mag) {
            this.enlarge = 100 - this.mag.getValue();
            this.gb = this.getGraphics();
            if (this.iw <= 590 & this.ih <= 590) {
                this.getEnlarge = new Enlarge(Play3.img, this.keepx, this.keepy, this.enlarge, this.nextImage.getSize().height, this.picSelect.getSize().width);
                this.gb.drawImage(this.getEnlarge.returnImg(), this.getSize().width - 250, 50, 200, 200, this);
            }
            this.gb.dispose();
        }
        if (source == this.nextImage) {
            this.picSelect.select(this.nextImage.getValue());
            Play3.img.flush();
            Play3.img2.flush();
            this.loadImage(this.picSelect.getItem(this.nextImage.getValue()));
            this.repaint();
        }
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        int value = this.nextImage.getValue();
        this.startThread.setBackground(Color.RED);
        this.startThread.setLabel("Stop");
        while (this.runner == currentThread) {
            this.nextImage.setValue(value);
            this.picSelect.select(this.nextImage.getValue());
            this.loadImage(this.picSelect.getItem(value++));
            if (value == 2157) {
                value = 0;
            }
            try {
                Thread.sleep(3000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.reset) {
            Play3.img2 = Play3.img;
            this.red.setValue(0);
            this.green.setValue(0);
            this.blue.setValue(0);
            this.contrast.setValue(0);
            this.bright.setValue(0);
            this.repaint();
        }
        if (source == this.invert) {
            Play3.img2 = new Invert(Play3.img).returnImg();
            this.repaint();
        }
        if (source == this.picSelect) {
            this.nextImage.setValue(this.picSelect.getSelectedIndex());
            Play3.img.flush();
            Play3.img2.flush();
            this.loadImage(this.picSelect.getSelectedItem());
            this.repaint();
        }
        if (source == this.startThread) {
            if (this.startThread.getLabel().equals("Start")) {
                (this.runner = new Thread(this)).start();
            }
            if (this.startThread.getLabel().equals("Stop") && this.runner != null) {
                this.runner = null;
                this.startThread.setBackground(Color.GREEN);
                this.startThread.setLabel("Start");
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.move && this.onoffcheck.getSelectedCheckbox().getLabel().equals("ON")) {
            if (mouseEvent.getX() > this.picSelect.getSize().width + this.enlarge / 2 & mouseEvent.getX() < this.picSelect.getSize().width + Play3.img.getWidth(null) - this.enlarge / 2 & mouseEvent.getY() > 20 + this.enlarge / 2 & mouseEvent.getY() < this.ih - 20 - this.enlarge / 2) {
                this.inside = true;
                this.gb = this.getGraphics();
                this.keepx = mouseEvent.getX();
                this.keepy = mouseEvent.getY();
                if (this.iw <= 590 & this.ih <= 590) {
                    this.getEnlarge = new Enlarge(Play3.img, this.keepx, this.keepy, this.enlarge, this.nextImage.getSize().height, this.picSelect.getSize().width);
                    this.gb.drawImage(this.getEnlarge.returnImg(), this.getSize().width - 250, 50, 200, 200, this);
                }
                this.gb.dispose();
            }
            else {
                this.inside = false;
            }
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (Play3.img2 != null) {
            this.iw = Play3.img2.getWidth(null);
            this.ih = Play3.img2.getHeight(null);
            if (this.ih <= 590 & this.iw <= 480) {
                this.on.setEnabled(true);
                this.mag.setEnabled(true);
                this.showStatus(this.picSelect.getItem(this.nextImage.getValue()) + " Contains: " + this.iw * this.ih + " Pixels");
                graphics.drawImage(Play3.img2, this.picSelect.getSize().width, this.nextImage.getSize().height + 5, this.iw, this.ih, this);
            }
            if (this.ih > 590 || this.iw > 480) {
                this.on.setEnabled(false);
                this.mag.setEnabled(false);
                this.showStatus(this.picSelect.getItem(this.nextImage.getValue()) + " Contains: " + this.iw * this.ih + " Pixels. Mag has been turned off to prevent lockup..");
                this.redrawht = this.ih;
                this.redrawwt = this.iw;
                while (this.redrawht > 590.0 || this.redrawwt > 480.0) {
                    this.redrawht *= 0.9;
                    this.redrawwt *= 0.9;
                }
                graphics.drawImage(Play3.img2, this.picSelect.getSize().width, this.nextImage.getSize().height + 5, (int)this.redrawwt, (int)this.redrawht, this);
            }
        }
    }
}
