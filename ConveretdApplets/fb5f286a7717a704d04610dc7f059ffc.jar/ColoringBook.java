import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Button;
import java.awt.Container;
import java.awt.image.PixelGrabber;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Checkbox;
import java.awt.event.MouseListener;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Choice;
import java.util.Vector;
import java.awt.Color;
import java.awt.Image;
import java.awt.Label;
import java.awt.CheckboxGroup;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColoringBook extends Applet implements ItemListener, ActionListener
{
    private static CheckboxGroup cbg;
    public static int currentCanvas;
    Label bookLbl;
    public static boolean tracing;
    public static Image picture;
    public static Image bufImage;
    public static Image palPic;
    public static Image bgImage;
    public static int h;
    public static int w;
    public static bookCanvas book;
    public static Color bgColor;
    public static int mousex;
    public static int mousey;
    public static int lastx;
    public static int lasty;
    boolean m_fAllLoaded;
    public static boolean started;
    public static boolean validP;
    public static Controller con;
    public static Color col;
    public static int penSize;
    public static Vector xyPixel;
    public static Vector pallete;
    private Choice pages;
    
    public String getAppletInfo() {
        return "Name: Coloring Book\r\nAuthor: Jake W. Holmes\r\n";
    }
    
    public void init() {
        this.pages = new Choice();
        int n = 0;
        ColoringBook.bgColor = this.getColorParm(this.getParameter("bgColor"));
        ColoringBook.bgImage = this.getImage(this.getDocumentBase(), this.getParameter("bgImage"));
        String parameter;
        while ((parameter = this.getParameter("image_" + n)) != null) {
            this.pages.add(parameter);
            ++n;
        }
        this.processImages(this.getParameter("image_0"));
        ColoringBook.bufImage = this.createImage(ColoringBook.w, ColoringBook.h);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 1));
        final Panel panel2 = new Panel();
        panel.add(ColoringBook.con);
        final Panel panel3 = new Panel();
        final PalletColors palletColors = new PalletColors();
        final ColorListener colorListener = new ColorListener();
        ColoringBook.palPic = this.getImage(this.getDocumentBase(), "pal.jpg");
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(ColoringBook.palPic, 0);
        mediaTracker.addImage(ColoringBook.bgImage, 1);
        try {
            mediaTracker.waitForAll();
            this.m_fAllLoaded = !mediaTracker.isErrorAny();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < 16; ++i) {
            final Color color = palletColors.getColor(i);
            ColoringBook.pallete.addElement(new ColorCanvas(color, i));
            final ColorCanvas colorCanvas = ColoringBook.pallete.elementAt(i);
            colorCanvas.setSize(15, 15);
            colorCanvas.setBackground(color);
            colorCanvas.addMouseListener(colorListener);
            panel2.add(colorCanvas);
        }
        ColoringBook.col = ColoringBook.pallete.elementAt(0).getColor();
        ColoringBook.book = new bookCanvas();
        final Checkbox checkbox = new Checkbox("Trace", ColoringBook.cbg, false);
        checkbox.addItemListener(this);
        final Checkbox checkbox2 = new Checkbox("Color", ColoringBook.cbg, true);
        checkbox2.addItemListener(this);
        panel3.add(checkbox);
        panel3.add(checkbox2);
        this.pages.addItemListener(this);
        this.setBackground(ColoringBook.bgColor);
        final Label label = new Label("Page:");
        this.pages.setSize(100, 25);
        panel3.add(label);
        panel3.add(this.pages);
        this.add(panel3, "North");
        ColoringBook.book.setSize(ColoringBook.w, ColoringBook.h);
        this.add(panel2, "East");
        this.add(ColoringBook.book, "Center");
        panel2.setSize(ColoringBook.w, 35);
        this.add(panel, "South");
        ColoringBook.book.addMouseListener(new PaintListener());
        ColoringBook.book.addMouseMotionListener(new PaintMoveListener());
        panel2.repaint();
        ColoringBook.con.repaint();
    }
    
    private Color getColorParm(final String s) {
        if (s.equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (s.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (s.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (s.equalsIgnoreCase("darkGray")) {
            return Color.darkGray;
        }
        if (s.equalsIgnoreCase("gray")) {
            return Color.gray;
        }
        if (s.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (s.equalsIgnoreCase("lightGray")) {
            return Color.lightGray;
        }
        if (s.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (s.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        if (s.equalsIgnoreCase("pink")) {
            return Color.pink;
        }
        if (s.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (s.equalsIgnoreCase("white")) {
            return Color.white;
        }
        if (s.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        if (s.length() == 7 && s.charAt(0) == '#') {
            return new Color(Integer.parseInt(s.substring(1, 3), 16), Integer.parseInt(s.substring(3, 5), 16), Integer.parseInt(s.substring(5, 7), 16));
        }
        return Color.black;
    }
    
    public static void setPallet() {
        final ColorCanvas colorCanvas = ColoringBook.pallete.elementAt(ColoringBook.currentCanvas);
        colorCanvas.setBackground(ColoringBook.col);
        colorCanvas.changeColor(ColoringBook.col);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 2) {
            return;
        }
        if (itemEvent.getItem().equals("Color")) {
            ColoringBook.tracing = false;
            return;
        }
        if (itemEvent.getItem().equals("Trace")) {
            ColoringBook.tracing = true;
            return;
        }
        this.processImages(this.pages.getSelectedItem());
        ColoringBook.book.setSize(ColoringBook.w, ColoringBook.h);
        ColoringBook.started = true;
        ColoringBook.book.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        ColoringBook.con.repaint();
    }
    
    public void processImages(final String s) {
        ColoringBook.started = true;
        ColoringBook.xyPixel.removeAllElements();
        ColoringBook.picture = this.getImage(this.getDocumentBase(), String.valueOf(s) + ".gif");
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(ColoringBook.picture, 0);
        try {
            mediaTracker.waitForAll();
            this.m_fAllLoaded = !mediaTracker.isErrorAny();
        }
        catch (InterruptedException ex) {}
        try {
            final PixelGrabber pixelGrabber = new PixelGrabber(ColoringBook.picture, 0, 0, -1, -1, true);
            if (pixelGrabber.grabPixels()) {
                ColoringBook.w = pixelGrabber.getWidth();
                ColoringBook.h = pixelGrabber.getHeight();
                final int[] array = (int[])pixelGrabber.getPixels();
                final int n = array[2];
                for (int i = 0; i < ColoringBook.w; ++i) {
                    for (int j = 0; j < ColoringBook.h; ++j) {
                        if (array[j * ColoringBook.w + i] != n) {
                            ColoringBook.xyPixel.addElement(new Coordinate(i, j));
                        }
                    }
                }
            }
        }
        catch (InterruptedException ex2) {}
        this.repaint();
    }
    
    public void addButton(final Container container, final String s) {
        final Button button = new Button(s);
        container.add(button);
        button.addActionListener(this);
    }
    
    public static void fillBack(final Graphics graphics) {
        for (int i = 0; i < ColoringBook.xyPixel.size(); ++i) {
            final Coordinate coordinate = ColoringBook.xyPixel.elementAt(i);
            if ((coordinate.x > ColoringBook.mousex - (ColoringBook.penSize / 2 + 3) && coordinate.x < ColoringBook.mousex + (ColoringBook.penSize / 2 + 3) && coordinate.y > ColoringBook.mousey - (ColoringBook.penSize / 2 + 3) && coordinate.y < ColoringBook.mousey + (ColoringBook.penSize / 2 + 3)) || ColoringBook.started) {
                graphics.drawOval(coordinate.x, coordinate.y, 1, 1);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        graphics.drawImage(ColoringBook.bgImage, 0, 0, size.width, size.height, this);
    }
    
    public ColoringBook() {
        this.m_fAllLoaded = false;
    }
    
    static {
        ColoringBook.cbg = new CheckboxGroup();
        ColoringBook.mousex = -50;
        ColoringBook.mousey = -50;
        ColoringBook.lastx = -49;
        ColoringBook.lasty = -49;
        ColoringBook.started = true;
        ColoringBook.validP = true;
        ColoringBook.con = new Controller();
        ColoringBook.penSize = 35;
        ColoringBook.xyPixel = new Vector(1, 100);
        ColoringBook.pallete = new Vector(1, 1);
    }
}
