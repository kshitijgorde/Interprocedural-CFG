import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.TextEvent;
import java.applet.AppletContext;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Dialog;
import java.util.Observable;
import java.util.Enumeration;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.TextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.applet.Applet;
import java.awt.Frame;
import java.awt.Choice;
import java.util.Hashtable;
import java.util.Observer;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowListener;
import java.awt.event.ItemListener;
import java.awt.event.TextListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ControlPanel extends Panel implements ActionListener, TextListener, ItemListener, WindowListener, MouseMotionListener, MouseListener, Observer
{
    public static final double BASE_CSW = 10.0;
    public static final String CENTERX = "centerx";
    public static final String CENTERY = "centery";
    public static final String STABILETYPE = "stabiletype";
    public static final String STABILESIZE = "stabilesize";
    public static final String MOBILESIZE = "mobilesize";
    public static final String PENSIZE = "pensize";
    public static final String PRESPIN = "prespin";
    public static final String REVOLUTIONS = "revolutions";
    public static final String SIDEMODE = "sidemode";
    public static final String PENSOLIDDIAMETER = "pensoliddiameter";
    public static final String PENFADEDIAMETER = "penfadediameter";
    public static final String PENCOLOR = "pencolor";
    public static final String PENCOLORPICK = "pencolorpick";
    public static final String DOTSPER = "dotsper";
    public static final String ADAPTIVE = "adaptive";
    public static final String RENDER = "render";
    public static final String HELP = "help";
    public static final String CLOSE = "close";
    Panel innerPanel;
    Hashtable components;
    Hashtable componentsByName;
    Choice presets;
    DesignPanel myDesignPanel;
    ColorPicker penColorPicker;
    double canvasSizeWorld;
    Frame parentFrame;
    Applet parentApplet;
    Font smallFont;
    Font smallBold;
    Font mediumFont;
    Dimension canvasSize;
    Color penColor;
    boolean listForPresets;
    protected static String[][] rawpresets;
    protected double startDx;
    protected double startDy;
    
    ControlItem getItemByName(final String s) {
        return this.componentsByName.get(s);
    }
    
    ControlItem getItem(final Component component) {
        if (component == null) {
            return null;
        }
        return this.components.get(component);
    }
    
    boolean validateItem(final Component component) {
        if (component == null) {
            return true;
        }
        final ControlItem item = this.getItem(component);
        return item == null || item.validate();
    }
    
    public ControlPanel(final DesignPanel myDesignPanel, final double canvasSizeWorld, final Frame parentFrame, final Applet parentApplet, final boolean listForPresets) {
        this.startDx = 0.0;
        this.startDy = 0.0;
        int n = 12;
        int n2 = 14;
        this.parentFrame = parentFrame;
        this.parentApplet = parentApplet;
        this.canvasSizeWorld = canvasSizeWorld;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        this.innerPanel = new Panel();
        this.components = new Hashtable();
        this.componentsByName = new Hashtable();
        this.add("Center", new Label("  "));
        final Label label = new Label("Spiro v0.99 (c) 2000 by Neal Ziring", 0);
        label.setFont(new Font("Serif", 1, 10));
        this.add("South", label);
        this.myDesignPanel = myDesignPanel;
        this.listForPresets = listForPresets;
        final DesignCanvas canvas = myDesignPanel.getCanvas();
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        this.canvasSize = canvas.getSize();
        if (this.canvasSize.width < 600) {
            n = 10;
            n2 = 12;
        }
        this.innerPanel.setLayout(new GridLayout(0, 1, 1, 2));
        this.innerPanel.setBackground(Color.lightGray);
        this.smallFont = new Font("SansSerif", 0, n);
        this.smallBold = new Font("SansSerif", 1, n);
        this.mediumFont = new Font("SansSerif", 0, n2);
        this.innerPanel.setFont(this.smallFont);
        this.initializePresets();
        final Label label2 = new Label("Design center point:", 0);
        label2.setFont(this.smallBold);
        this.innerPanel.add(label2);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(0, 1, 1));
        panel.add(new Label("  X:", 2));
        final TextField textField = new TextField("0.0", 6);
        panel.add(textField);
        new ControlItem("centerx", textField, true, -this.canvasSizeWorld, this.canvasSizeWorld, true);
        panel.add(new Label("  Y:", 2));
        final TextField textField2 = new TextField("0.0", 6);
        panel.add(textField2);
        new ControlItem("centery", textField2, true, -this.canvasSizeWorld, this.canvasSizeWorld, true);
        this.innerPanel.add(panel);
        final Label label3 = new Label("Parameters for Stabile:", 0);
        label3.setFont(this.smallBold);
        this.innerPanel.add(label3);
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout(0, 1, 1));
        panel2.add(new Label("  "));
        final Choice choice = new Choice();
        choice.add("Circle");
        choice.add("Horiz. Bar");
        choice.add("Vert. Bar");
        panel2.add(choice);
        new ControlItem("stabiletype", choice, false, 0.0, 1.0, false);
        panel2.add(new Label("of size:", 1));
        final TextField textField3 = new TextField("0.0", 7);
        panel2.add(textField3);
        new ControlItem("stabilesize", textField3, true, this.canvasSizeWorld / 70.0, this.canvasSizeWorld, true);
        this.innerPanel.add(panel2);
        final Label label4 = new Label("Parameters for Mobile:", 0);
        label4.setFont(this.smallBold);
        this.innerPanel.add(label4);
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout(0, 1, 1));
        panel3.add(new Label("  Size:", 2));
        final TextField textField4 = new TextField("0.0", 5);
        panel3.add(textField4);
        new ControlItem("mobilesize", textField4, true, this.canvasSizeWorld / 70.0, this.canvasSizeWorld / 1.5, true);
        panel3.add(new Label("  Pen:", 2));
        final TextField textField5 = new TextField("0.0", 5);
        panel3.add(textField5);
        new ControlItem("pensize", textField5, true, 0.0, this.canvasSizeWorld / 1.5, true);
        this.innerPanel.add(panel3);
        final Panel panel4 = new Panel();
        panel4.setLayout(new FlowLayout(0, 1, 1));
        panel4.add(new Label("  Rotations:", 2));
        final TextField textField6 = new TextField("40", 2);
        panel4.add(textField6);
        new ControlItem("revolutions", textField6, true, 1.0, 400.0, false);
        panel4.add(new Label(" (Prerotate:", 2));
        final TextField textField7 = new TextField("0", 2);
        panel4.add(textField7);
        new ControlItem("prespin", textField7, true, 0.0, 399.0, false);
        panel4.add(new Label(")"));
        this.innerPanel.add(panel4);
        final Panel panel5 = new Panel();
        panel5.setLayout(new FlowLayout(0, 1, 1));
        panel5.add(new Label("  Spin on the", 2));
        final Choice choice2 = new Choice();
        choice2.addItem("Outside");
        choice2.addItem("~Inside");
        choice2.addItem("Inside");
        new ControlItem("sidemode", choice2, false, 0.0, 0.0, false);
        panel5.add(choice2);
        this.innerPanel.add(panel5);
        final Label label5 = new Label("Pen size and color:", 0);
        label5.setFont(this.smallBold);
        this.innerPanel.add(label5);
        final Panel panel6 = new Panel();
        panel6.setLayout(new FlowLayout(0, 1, 1));
        panel6.add(new Label("  Solid:", 2));
        final TextField textField8 = new TextField("1.0", 5);
        panel6.add(textField8);
        new ControlItem("pensoliddiameter", textField8, true, 0.0, 8.0, false);
        panel6.add(new Label("  Fade:", 2));
        final TextField textField9 = new TextField("1.33", 5);
        panel6.add(textField9);
        new ControlItem("penfadediameter", textField9, true, 0.1, 16.0, false);
        this.innerPanel.add(panel6);
        final Panel panel7 = new Panel();
        panel7.setLayout(new FlowLayout(0, 1, 1));
        panel7.add(new Label("  Pen color: ", 2));
        final Label label6 = new Label("      ");
        label6.setBackground(Color.blue);
        panel7.add(label6);
        panel7.add(new Label(" "));
        new ControlItem("pencolor", label6, false, 0.0, 0.0, false);
        (this.penColorPicker = new ColorPicker(parentFrame, label6)).setLocation(120, 120);
        this.penColorPicker.setTitle("Set pen color");
        final Button button = new Button("Set pen..");
        panel7.add(button);
        button.setActionCommand("pencolor");
        button.addActionListener(this);
        this.innerPanel.add(panel7);
        final Label label7 = new Label("Advanced Parameters:", 0);
        label7.setFont(this.smallBold);
        this.innerPanel.add(label7);
        final Panel panel8 = new Panel();
        panel8.setLayout(new FlowLayout(0, 1, 1));
        panel8.add(new Label("  Dots/cycle:", 2));
        final TextField textField10 = new TextField("280", 4);
        panel8.add(textField10);
        new ControlItem("dotsper", textField10, true, 8.0, 1440.0, false);
        panel8.add(new Label(" "));
        final Checkbox checkbox = new Checkbox("adaptive", false);
        panel8.add(checkbox);
        new ControlItem("adaptive", checkbox, false, 0.0, 0.0, false);
        this.innerPanel.add(panel8);
        final Label label8 = new Label("Preset designs:", 0);
        label8.setFont(this.smallBold);
        this.innerPanel.add(label8);
        final Panel panel9 = new Panel();
        panel9.setLayout(new FlowLayout(0, 1, 1));
        panel9.add(new Label("  "));
        this.presets = new Choice();
        for (int i = 0; i < ControlPanel.rawpresets.length; i += 2) {
            this.presets.addItem(ControlPanel.rawpresets[i][0]);
        }
        panel9.add(this.presets);
        this.presets.addItemListener(this);
        this.innerPanel.add(panel9);
        this.innerPanel.add(new Label("-------------------", 1));
        final Button button2 = new Button("Draw this Design!");
        button2.setFont(this.mediumFont);
        button2.setActionCommand("render");
        this.innerPanel.add(button2);
        final Panel panel10 = new Panel();
        panel10.setLayout(new GridLayout(1, 2));
        panel10.setFont(this.mediumFont);
        final Button button3 = new Button("Help");
        button3.setActionCommand("help");
        panel10.add(button3);
        final Button button4 = new Button("Close");
        button4.setActionCommand("close");
        panel10.add(button4);
        this.innerPanel.add(panel10);
        this.add("North", this.innerPanel);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        final Enumeration<Component> keys = this.components.keys();
        while (keys.hasMoreElements()) {
            final Component component = keys.nextElement();
            if (component instanceof TextField) {
                ((TextField)component).addTextListener(this);
            }
        }
        this.applyPreset(0);
        parentFrame.addWindowListener(this);
    }
    
    public void initializePresets() {
    }
    
    public void applyPreset(final int n) {
        final String s = ControlPanel.rawpresets[n * 2][0];
        final String[] array = ControlPanel.rawpresets[n * 2 + 1];
        this.myDesignPanel.setStatus("Using preset " + s);
        for (int i = 0; i < array.length; i += 2) {
            final String s2 = array[i];
            final String s3 = array[i + 1];
            final ControlItem itemByName = this.getItemByName(s2);
            if (itemByName != null) {
                if (itemByName.comp instanceof TextField) {
                    try {
                        double doubleValue = Double.valueOf(s3);
                        if (itemByName.needsScale) {
                            doubleValue = doubleValue / 10.0 * (this.canvasSizeWorld / 2.0);
                        }
                        ((TextField)itemByName.comp).setText("" + doubleValue);
                    }
                    catch (NumberFormatException ex) {}
                }
                else if (itemByName.comp instanceof Label) {
                    int n2 = 0;
                    try {
                        if (s3.startsWith("0x")) {
                            n2 = Integer.parseInt(s3.substring(2), 16);
                        }
                        else {
                            n2 = Integer.parseInt(s3);
                        }
                    }
                    catch (NumberFormatException ex2) {}
                    itemByName.comp.setBackground(new Color(n2));
                }
                else if (itemByName.comp instanceof Choice) {
                    try {
                        ((Choice)itemByName.comp).select(Integer.parseInt(s3));
                    }
                    catch (Exception ex3) {}
                }
                else if (itemByName.comp instanceof Checkbox) {
                    boolean booleanValue = false;
                    try {
                        if (s3.length() >= 4) {
                            booleanValue = Boolean.valueOf(s3);
                        }
                        else {
                            booleanValue = (Integer.parseInt(s3) != 0);
                        }
                    }
                    catch (Exception ex4) {}
                    ((Checkbox)itemByName.comp).setState(booleanValue);
                }
            }
        }
    }
    
    public void doDesign() {
        final double doubleValue = this.getItemByName("centerx").getDoubleValue();
        final double doubleValue2 = this.getItemByName("centery").getDoubleValue();
        final int selectedIndex = ((Choice)this.getItemByName("stabiletype").comp).getSelectedIndex();
        final double doubleValue3 = this.getItemByName("stabilesize").getDoubleValue();
        final double doubleValue4 = this.getItemByName("mobilesize").getDoubleValue();
        final double doubleValue5 = this.getItemByName("pensize").getDoubleValue();
        final double doubleValue6 = this.getItemByName("revolutions").getDoubleValue();
        final double doubleValue7 = this.getItemByName("prespin").getDoubleValue();
        final int selectedIndex2 = ((Choice)this.getItemByName("sidemode").comp).getSelectedIndex();
        final double doubleValue8 = this.getItemByName("pensoliddiameter").getDoubleValue();
        final double doubleValue9 = this.getItemByName("penfadediameter").getDoubleValue();
        this.penColor = this.getItemByName("pencolor").comp.getBackground();
        final double doubleValue10 = this.getItemByName("dotsper").getDoubleValue();
        final int state = ((Checkbox)this.getItemByName("adaptive").comp).getState() ? 1 : 0;
        if (this.listForPresets) {
            final double n = 10.0 / (this.canvasSizeWorld / 2.0);
            System.out.println("  { \"SomeName\" },");
            System.out.println("  { CENTERX, \"" + doubleValue * n + "\",");
            System.out.println("    CENTERY, \"" + doubleValue2 * n + "\",");
            System.out.println("    STABILETYPE, \"" + selectedIndex + "\",");
            System.out.println("    STABILESIZE, \"" + doubleValue3 * n + "\",");
            System.out.println("    MOBILESIZE, \"" + doubleValue4 * n + "\",");
            System.out.println("    PENSIZE, \"" + doubleValue5 * n + "\",");
            System.out.println("    REVOLUTIONS, \"" + doubleValue6 + "\",");
            System.out.println("    PRESPIN, \"" + doubleValue7 + "\",");
            System.out.println("    SIDEMODE, \"" + selectedIndex2 + "\",");
            System.out.println("    PENSOLIDDIAMETER, \"" + doubleValue8 + "\",");
            System.out.println("    PENFADEDIAMETER, \"" + doubleValue9 + "\",");
            System.out.println("    PENCOLOR, \"" + ((this.penColor.getRed() << 8 | this.penColor.getGreen()) << 8 | this.penColor.getBlue()) + "\",");
            System.out.println("    DOTSPER, \"" + doubleValue10 + "\",");
            System.out.println("    ADAPTIVE, \"" + state + "\" },");
        }
        Stabile stabile = null;
        switch (selectedIndex) {
            case 0: {
                stabile = new CircleStabile(new PPoint(doubleValue, doubleValue2), doubleValue3);
                break;
            }
            case 1: {
                stabile = new BarStabile(new PPoint(doubleValue, doubleValue2), doubleValue3);
                break;
            }
            case 2: {
                stabile = new PylonStabile(new PPoint(doubleValue, doubleValue2), doubleValue3);
                break;
            }
            default: {
                stabile = new CircleStabile(new PPoint(doubleValue, doubleValue2), doubleValue3);
                break;
            }
        }
        final Design design = new Design(new CircleMobile(doubleValue4, doubleValue5), stabile, doubleValue7 * 3.141592653589793 * 2.0, doubleValue6, this.canvasSizeWorld, this.canvasSize.width, doubleValue8, doubleValue9, (int)doubleValue10, selectedIndex2);
        design.addObserver(this.myDesignPanel);
        design.setAdaptive(state != 0);
        design.addObserver(this);
        new Thread(design).start();
    }
    
    public void update(final Observable observable, final Object o) {
        if (observable instanceof Design && o instanceof String) {
            this.myDesignPanel.getCanvas().addImage(((Design)observable).getImage(this.myDesignPanel.getCanvas(), this.penColor, Color.white, true), (Design)observable);
            ((Design)observable).release();
            ((Design)observable).deleteObserver(this);
        }
    }
    
    public void doColorPicker(final Dialog dialog) {
        dialog.setModal(true);
        dialog.show();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("pencolor") || actionCommand.equals("pencolorpick")) {
            this.penColorPicker.setModal(true);
            this.penColorPicker.show();
        }
        else if (actionCommand.equals("close")) {
            this.closeUpShop();
        }
        else if (actionCommand.equals("help")) {
            if (this.parentApplet != null) {
                final AppletContext appletContext = this.parentApplet.getAppletContext();
                String s = this.parentApplet.getParameter("help");
                if (s == null) {
                    s = this.parentApplet.getParameter("helpURL");
                }
                if (s == null) {
                    s = this.parentApplet.getParameter("helpPage");
                }
                if (s != null) {
                    final URL documentBase = this.parentApplet.getDocumentBase();
                    try {
                        appletContext.showDocument(new URL(documentBase, s), "help");
                    }
                    catch (Exception ex) {
                        this.myDesignPanel.setStatus("Could not load help page: " + s);
                    }
                }
            }
        }
        else if (actionCommand.equals("render")) {
            final String validateAllItems = this.validateAllItems();
            if (validateAllItems != null) {
                this.myDesignPanel.setStatus("Item(s) with invalid values: " + validateAllItems);
            }
            else {
                this.doDesign();
            }
        }
    }
    
    public void closeUpShop() {
        this.parentFrame.setVisible(false);
        this.parentFrame.dispose();
        if (this.parentApplet == null) {
            System.exit(0);
        }
        else if (this.parentApplet instanceof SpiroApplet) {
            final SpiroApplet spiroApplet = (SpiroApplet)this.parentApplet;
            --spiroApplet.canvasesUp;
        }
    }
    
    public String validateAllItems() {
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        final Enumeration<Component> keys = this.components.keys();
        while (keys.hasMoreElements()) {
            final Component component = keys.nextElement();
            if (!this.validateItem(component)) {
                if (n > 0) {
                    sb.append(", ");
                }
                sb.append(this.getItem(component).name);
                ++n;
            }
        }
        if (n == 0) {
            return null;
        }
        return sb.toString();
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        this.validateItem((Component)textEvent.getSource());
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.presets) {
            this.applyPreset(this.presets.getSelectedIndex());
        }
    }
    
    public void setCenter(final int n, final int n2, final boolean b) {
        final double startDx = n / (1.0 * this.canvasSize.width) * this.canvasSizeWorld - this.canvasSizeWorld / 2.0;
        final double startDy = n2 / (1.0 * this.canvasSize.height) * this.canvasSizeWorld - this.canvasSizeWorld / 2.0;
        if (b) {
            this.startDx = startDx;
            this.startDy = startDy;
        }
        final ControlItem itemByName = this.getItemByName("centerx");
        if (itemByName != null) {
            ((TextField)itemByName.comp).setText("" + Math.rint(startDx));
        }
        final ControlItem itemByName2 = this.getItemByName("centery");
        if (itemByName2 != null) {
            ((TextField)itemByName2.comp).setText("" + Math.rint(startDy));
        }
    }
    
    public void setSize(final int n, final int n2) {
        final double n3 = n / (1.0 * this.canvasSize.width) * this.canvasSizeWorld - this.canvasSizeWorld / 2.0;
        final double n4 = n2 / (1.0 * this.canvasSize.height) * this.canvasSizeWorld - this.canvasSizeWorld / 2.0;
        final double n5 = this.startDx - n3;
        final double n6 = this.startDy - n4;
        final double rint = Math.rint(Math.sqrt(n5 * n5 + n6 * n6));
        final ControlItem itemByName = this.getItemByName("stabilesize");
        if (itemByName != null) {
            ((TextField)itemByName.comp).setText("" + rint);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (mouseEvent.isShiftDown()) {
            this.setSize(mouseEvent.getX(), mouseEvent.getY());
        }
        else {
            this.setCenter(mouseEvent.getX(), mouseEvent.getY(), true);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.setCenter(mouseEvent.getX(), mouseEvent.getY(), true);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.isShiftDown()) {
            this.setSize(mouseEvent.getX(), mouseEvent.getY());
        }
        else {
            this.setCenter(mouseEvent.getX(), mouseEvent.getY(), false);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.closeUpShop();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    static {
        ControlPanel.rawpresets = new String[][] { { "Classic" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "7.8125", "mobilesize", "3.4375", "pensize", "2.15625", "revolutions", "40.0", "sidemode", "2", "pensoliddiameter", "1.0", "penfadediameter", "1.5", "dotsper", "330.0", "pencolor", "0x06600CC", "adaptive", "1" }, { "Simple 1" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "4.0", "mobilesize", "1.1111", "pensize", "1.0", "revolutions", "50", "sidemode", "0", "pensoliddiameter", "1.0", "penfadediameter", "1.5", "pencolor", "3203104", "dotsper", "400" }, { "Fancy 1" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "6.95", "mobilesize", "2.01", "pensize", "2.5", "revolutions", "90", "sidemode", "2", "adaptive", "true", "pensoliddiameter", "0.8", "penfadediameter", "1.3", "pencolor", "0x0033FF", "dotsper", "330" }, { "Test 2" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "4", "mobilesize", "1.0", "pensize", "1.0", "revolutions", "4", "sidemode", "0", "adaptive", "true", "pensoliddiameter", "2", "penfadediameter", "3", "pencolor", "0x00333", "dotsper", "15" }, { "Nicer ?" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "5.0", "mobilesize", "1.1", "pensize", "1.3", "revolutions", "50.0", "sidemode", "2", "pensoliddiameter", "2.0", "penfadediameter", "3.5", "dotsper", "400.0", "adaptive", "1" }, { "Lacy 1" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "4.88", "mobilesize", "0.84", "pensize", "0.96", "revolutions", "144.0", "sidemode", "0", "pensoliddiameter", "0.3", "penfadediameter", "2.0", "pencolor", "51", "dotsper", "360.0", "adaptive", "false" }, { "Lacy 2" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "8.4", "mobilesize", "1.155", "pensize", "1.0", "revolutions", "80.0", "sidemode", "2", "pensoliddiameter", "1.0", "penfadediameter", "2.5", "dotsper", "400.0", "adaptive", "1" }, { "PowderPuff 1" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "6.9375", "mobilesize", "2.234375", "pensize", "2.53125", "revolutions", "82.0", "sidemode", "1", "pensoliddiameter", "1.0", "penfadediameter", "1.5", "dotsper", "400.0", "adaptive", "0" }, { "Basket?" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "1.5625", "mobilesize", "3.046875", "pensize", "3.125", "revolutions", "9.0", "sidemode", "0", "pensoliddiameter", "0.55", "penfadediameter", "1.9", "dotsper", "333.0", "adaptive", "1" }, { "Star 1" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "10.3125", "mobilesize", "4.140625", "pensize", "1.53125", "revolutions", "30.0", "sidemode", "2", "pensoliddiameter", "0.5", "penfadediameter", "3.5", "dotsper", "500.0", "pencolor", "0x0CCffff", "adaptive", "1" }, { "String Border" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "16.25", "mobilesize", "4.265625", "pensize", "3.75", "revolutions", "90.0", "sidemode", "2", "pensoliddiameter", "0.5", "penfadediameter", "1.5", "pencolor", "0x0663300", "dotsper", "450.0", "adaptive", "1" }, { "Delicate3" }, { "centerx", "0.0", "centery", "0.09375", "stabiletype", "0", "stabilesize", "8.125", "mobilesize", "1.125", "pensize", "1.0", "revolutions", "90.0", "sidemode", "2", "pensoliddiameter", "1.0", "penfadediameter", "1.25", "dotsper", "90.0", "pencolor", "0x0CC33FF", "adaptive", "0" }, { "Border Bottom" }, { "centerx", "0.0", "centery", "9.95", "stabiletype", "1", "stabilesize", "8.125", "mobilesize", "1.125", "pensize", "1.0", "revolutions", "90.0", "sidemode", "2", "pensoliddiameter", "1.0", "penfadediameter", "1.25", "dotsper", "90.0", "adaptive", "1" }, { "Border Left" }, { "centerx", "-9.95", "centery", "0.0", "stabiletype", "2", "stabilesize", "8.125", "mobilesize", "1.125", "pensize", "1.0", "revolutions", "90.0", "sidemode", "2", "pensoliddiameter", "1.0", "penfadediameter", "1.25", "dotsper", "90.0", "adaptive", "1" }, { "Border Right" }, { "centerx", "10.0", "centery", "0.0", "stabiletype", "2", "stabilesize", "8.125", "mobilesize", "1.125", "pensize", "1.0", "revolutions", "90.0", "sidemode", "2", "pensoliddiameter", "1.0", "penfadediameter", "1.25", "dotsper", "90.0", "adaptive", "1" }, { "Border Top" }, { "centerx", "0.0", "centery", "-9.95", "stabiletype", "1", "stabilesize", "8.125", "mobilesize", "1.125", "pensize", "1.0", "revolutions", "90.0", "sidemode", "2", "pensoliddiameter", "1.0", "penfadediameter", "1.25", "dotsper", "90.0", "adaptive", "1" }, { "Puffy Cloud" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "9.375", "mobilesize", "0.9375", "pensize", "0.9375", "revolutions", "51.0", "sidemode", "1", "pensoliddiameter", "0.15", "penfadediameter", "8.9", "dotsper", "900.0", "adaptive", "1" }, { "Wirey Pt1" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "8.75", "mobilesize", "2.40625", "pensize", "1.84375", "revolutions", "40.0", "sidemode", "2", "pensoliddiameter", "1.0", "penfadediameter", "1.5", "pencolor", "10066278", "dotsper", "330.0", "adaptive", "1" }, { "Wirey Pt 2" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "4.375", "mobilesize", "1.203125", "pensize", "0.921875", "revolutions", "40.0", "sidemode", "1", "pensoliddiameter", "1.0", "penfadediameter", "1.5", "pencolor", "10066278", "dotsper", "330.0", "adaptive", "1" }, { "Seventeen" }, { "centerx", "0.0", "centery", "0.0", "stabiletype", "0", "stabilesize", "3.1875", "mobilesize", "2.25", "pensize", "3.1875", "revolutions", "17.1", "sidemode", "0", "pensoliddiameter", "1.25", "penfadediameter", "2.95", "pencolor", "10092492", "dotsper", "330.0", "adaptive", "1" } };
    }
    
    class ControlItem
    {
        String name;
        Component comp;
        boolean checkNumericValue;
        boolean needsScale;
        double min;
        double max;
        
        ControlItem(final String name, final Component comp, final boolean checkNumericValue, final double min, final double max, final boolean needsScale) {
            this.name = name;
            this.comp = comp;
            this.checkNumericValue = checkNumericValue;
            this.min = min;
            this.max = max;
            this.needsScale = needsScale;
            ControlPanel.this.components.put(comp, this);
            ControlPanel.this.componentsByName.put(name, this);
        }
        
        Color getBackground() {
            return this.comp.getBackground();
        }
        
        double getDoubleValue() {
            if (this.comp instanceof TextField) {
                double doubleValue = 0.0;
                try {
                    doubleValue = Double.valueOf(((TextField)this.comp).getText());
                }
                catch (NumberFormatException ex) {}
                return doubleValue;
            }
            return 0.0;
        }
        
        boolean validate() {
            boolean b = true;
            if (this.checkNumericValue) {
                double doubleValue = 0.0;
                if (this.comp instanceof TextField) {
                    try {
                        doubleValue = Double.valueOf(((TextField)this.comp).getText());
                    }
                    catch (NumberFormatException ex) {
                        b = false;
                    }
                    if (doubleValue < this.min || doubleValue > this.max) {
                        b = false;
                    }
                    if (b) {
                        ((TextField)this.comp).setForeground(Color.black);
                    }
                    else {
                        ((TextField)this.comp).setForeground(Color.red);
                    }
                }
            }
            return b;
        }
    }
}
