import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Font;
import java.awt.Button;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Scrollbar;
import java.awt.Graphics;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Blox2 extends Applet implements MouseListener, MouseMotionListener, ItemListener, ActionListener, WindowListener, AdjustmentListener
{
    public Graphics Blox2G;
    public Scrollbar horizontal;
    public Scrollbar vertical;
    public Choice styleC;
    public Choice colorC;
    public Choice dimensionC;
    public Label deleteL;
    public Choice backgroundC;
    public Choice zoomC;
    public Frame helpF;
    public MenuBar helpMB;
    public TextArea helpT;
    public String[] helpText;
    public Frame saveF;
    public TextArea saveT;
    public Frame loadF;
    public TextField loadT;
    public Frame whoF;
    public Frame eraseF;
    public Cursor hand;
    public Cursor crosshair;
    public Cursor hourglass;
    public boolean title;
    public boolean hold;
    public int[] addToCenter;
    public boolean delete;
    public Color[] colors;
    public int[] zoom;
    public int[] offset;
    public Block[] blocks;
    public int index;
    public int empty;
    
    public final void init() {
        this.title = true;
        this.hold = false;
        this.addToCenter = new int[2];
        this.delete = false;
        (this.colors = new Color[8])[0] = Color.black;
        this.colors[1] = Color.white;
        this.colors[2] = Color.red;
        this.colors[3] = Color.orange;
        this.colors[4] = Color.yellow;
        this.colors[5] = Color.green;
        this.colors[6] = Color.blue;
        this.colors[7] = Color.magenta;
        (this.zoom = new int[8])[0] = 1;
        this.zoom[1] = 6;
        this.zoom[2] = 8;
        this.zoom[3] = 12;
        this.zoom[4] = 25;
        this.zoom[5] = 50;
        this.zoom[6] = 75;
        this.zoom[7] = 100;
        this.offset = new int[2];
        (this.blocks = new Block[50])[0] = new Block();
        this.index = 0;
        this.empty = 0;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setLayout(null);
        (this.horizontal = new Scrollbar(0)).setMinimum(0);
        this.horizontal.setMaximum(12000);
        this.horizontal.setBlockIncrement(500);
        this.horizontal.setUnitIncrement(25);
        this.horizontal.setValue(this.horizontal.getMaximum() / 2);
        this.horizontal.addAdjustmentListener(this);
        this.horizontal.setBounds(100, 480, 480, 20);
        this.add(this.horizontal);
        this.offset[0] = -this.horizontal.getValue();
        (this.vertical = new Scrollbar(1)).setMinimum(0);
        this.vertical.setMaximum(12000);
        this.vertical.setBlockIncrement(500);
        this.vertical.setUnitIncrement(25);
        this.vertical.setValue(this.vertical.getMaximum() / 2);
        this.vertical.addAdjustmentListener(this);
        this.vertical.setBounds(580, 30, 20, 450);
        this.add(this.vertical);
        this.offset[1] = -this.vertical.getValue();
        final Button button = new Button("?");
        button.setForeground(Color.red);
        button.setFont(new Font("Verdana", 1, 12));
        button.setActionCommand("?");
        button.addActionListener(this);
        button.setBounds(580, 480, 20, 20);
        this.add(button);
        final Panel panel = new Panel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 100, 500);
        panel.setBackground(Color.blue);
        panel.setForeground(Color.white);
        panel.setFont(new Font("Verdana", 1, 12));
        final Label label = new Label("Style");
        label.setBounds(10, 10, 80, 20);
        panel.add(label);
        (this.styleC = new Choice()).add("Rectangle");
        this.styleC.add("Oval");
        this.styleC.add("Angle Front");
        this.styleC.add("Angle Right");
        this.styleC.add("Angle Left");
        this.styleC.add("Flat");
        this.styleC.add("Flat&Smooth");
        this.styleC.setForeground(Color.black);
        this.styleC.setBounds(10, 30, 80, 20);
        this.styleC.addItemListener(this);
        panel.add(this.styleC);
        final Label label2 = new Label("Color");
        label2.setBounds(10, 60, 80, 20);
        panel.add(label2);
        (this.colorC = new Choice()).add("Black");
        this.colorC.add("White");
        this.colorC.add("Red");
        this.colorC.add("Orange");
        this.colorC.add("Yellow");
        this.colorC.add("Green");
        this.colorC.add("Blue");
        this.colorC.add("Purple");
        this.colorC.setForeground(Color.black);
        this.colorC.setBounds(10, 80, 80, 20);
        this.colorC.select(2);
        panel.add(this.colorC);
        final Label label3 = new Label("Dimensions");
        label3.setBounds(10, 110, 80, 20);
        panel.add(label3);
        this.dimensionC = new Choice();
        this.resetDimensions();
        this.dimensionC.setForeground(Color.black);
        this.dimensionC.setBounds(10, 130, 80, 20);
        panel.add(this.dimensionC);
        final Button button2 = new Button("Create");
        button2.setFont(new Font("Verdana", 1, 16));
        button2.setForeground(Color.black);
        button2.setBackground(Color.green);
        button2.setBounds(10, 170, 80, 40);
        button2.setActionCommand("C");
        button2.addActionListener(this);
        panel.add(button2);
        final Label label4 = new Label("Layer");
        label4.setBounds(10, 220, 80, 20);
        panel.add(label4);
        final Button button3 = new Button("Forward");
        button3.setForeground(Color.black);
        button3.setBackground(Color.yellow);
        button3.setBounds(10, 240, 80, 20);
        button3.setActionCommand("F");
        button3.addActionListener(this);
        panel.add(button3);
        final Button button4 = new Button("Backward");
        button4.setForeground(Color.black);
        button4.setBackground(Color.yellow);
        button4.setBounds(10, 260, 80, 20);
        button4.setActionCommand("B");
        button4.addActionListener(this);
        panel.add(button4);
        final Button button5 = new Button("To Front");
        button5.setForeground(Color.black);
        button5.setBackground(Color.yellow);
        button5.setBounds(10, 280, 80, 20);
        button5.setActionCommand("T");
        button5.addActionListener(this);
        panel.add(button5);
        final Button button6 = new Button("To Back");
        button6.setForeground(Color.black);
        button6.setBackground(Color.yellow);
        button6.setBounds(10, 300, 80, 20);
        button6.setActionCommand("K");
        button6.addActionListener(this);
        panel.add(button6);
        (this.deleteL = new Label("Destroy Off")).setBounds(10, 330, 80, 20);
        panel.add(this.deleteL);
        final Button button7 = new Button("Destroy");
        button7.setFont(new Font("Verdana", 1, 16));
        button7.setForeground(Color.black);
        button7.setBackground(Color.red);
        button7.setBounds(10, 350, 80, 40);
        button7.setActionCommand("D");
        button7.addActionListener(this);
        panel.add(button7);
        final Label label5 = new Label("Background");
        label5.setBounds(10, 400, 80, 20);
        panel.add(label5);
        (this.backgroundC = new Choice()).add("Black");
        this.backgroundC.add("White");
        this.backgroundC.add("Red");
        this.backgroundC.add("Orange");
        this.backgroundC.add("Yellow");
        this.backgroundC.add("Green");
        this.backgroundC.add("Blue");
        this.backgroundC.add("Purple");
        this.backgroundC.setForeground(Color.black);
        this.backgroundC.setBounds(10, 420, 80, 20);
        this.backgroundC.addItemListener(this);
        panel.add(this.backgroundC);
        final Label label6 = new Label("View/Zoom");
        label6.setBounds(10, 450, 80, 20);
        panel.add(label6);
        (this.zoomC = new Choice()).add("Fit To Screen");
        this.zoomC.add("X1/4");
        this.zoomC.add("X1/3");
        this.zoomC.add("X1/2");
        this.zoomC.add("Normal");
        this.zoomC.add("X2");
        this.zoomC.add("X3");
        this.zoomC.add("X4");
        this.zoomC.setForeground(Color.black);
        this.zoomC.setBounds(10, 470, 80, 20);
        this.zoomC.addItemListener(this);
        this.zoomC.select(4);
        panel.add(this.zoomC);
        this.add(panel);
        final Panel panel2 = new Panel();
        panel2.setLayout(null);
        panel2.setBounds(100, 0, 500, 30);
        panel2.setBackground(Color.blue);
        panel2.setForeground(Color.black);
        panel2.setFont(new Font("Verdana", 1, 12));
        final Button button8 = new Button("Save");
        button8.setActionCommand("S");
        button8.addActionListener(this);
        button8.setBounds(20, 5, 100, 20);
        panel2.add(button8);
        final Button button9 = new Button("Load");
        button9.setActionCommand("L");
        button9.addActionListener(this);
        button9.setBounds(140, 5, 100, 20);
        panel2.add(button9);
        final Button button10 = new Button("Start Over");
        button10.setActionCommand("E");
        button10.addActionListener(this);
        button10.setBounds(260, 5, 100, 20);
        panel2.add(button10);
        final Button button11 = new Button("Help");
        button11.setActionCommand("P");
        button11.addActionListener(this);
        button11.setBounds(380, 5, 100, 20);
        panel2.add(button11);
        this.add(panel2);
        (this.loadF = new Frame("Load")).setResizable(false);
        this.loadF.setFont(new Font("Verdana", 1, 12));
        this.loadF.setBackground(Color.black);
        this.loadF.setForeground(Color.white);
        this.loadF.setLayout(new GridBagLayout());
        this.loadF.setSize(300, 150);
        this.loadF.setLocation(this.getToolkit().getScreenSize().width / 2 - 150, this.getToolkit().getScreenSize().height / 2 - 75);
        this.loadF.addWindowListener(this);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        final Label label7 = new Label("Enter the URL of the \".blx\" file relative to:");
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        this.loadF.add(label7, gridBagConstraints);
        final Label label8 = new Label(String.valueOf(this.getDocumentBase().getProtocol()) + "://" + this.getDocumentBase().getHost() + "/");
        gridBagConstraints.gridy = 1;
        this.loadF.add(label8, gridBagConstraints);
        (this.loadT = new TextField(20)).setBackground(Color.white);
        this.loadT.setForeground(Color.black);
        gridBagConstraints.gridy = 2;
        this.loadF.add(this.loadT, gridBagConstraints);
        final Button button12 = new Button("OK");
        button12.setActionCommand("O");
        button12.addActionListener(this);
        button12.setBackground(Color.blue);
        button12.setForeground(Color.white);
        gridBagConstraints.gridy = 3;
        this.loadF.add(button12, gridBagConstraints);
        (this.saveF = new Frame("Save")).setResizable(false);
        this.saveF.setFont(new Font("Verdana", 1, 12));
        this.saveF.setBackground(Color.black);
        this.saveF.setForeground(Color.white);
        this.saveF.setLayout(new BorderLayout());
        this.saveF.setSize(400, 200);
        this.saveF.setLocation(this.getToolkit().getScreenSize().width / 2 - 200, this.getToolkit().getScreenSize().height / 2 - 100);
        this.saveF.addWindowListener(this);
        final Label label9 = new Label("Copy the text below into a file with the extension \".blx\":");
        gridBagConstraints.gridy = 0;
        this.saveF.add("North", label9);
        (this.saveT = new TextArea()).setBackground(Color.white);
        this.saveT.setForeground(Color.black);
        this.saveT.setEditable(false);
        gridBagConstraints.gridy = 1;
        this.saveF.add("Center", this.saveT);
        (this.whoF = new Frame("Blox 2")).setResizable(false);
        this.whoF.setFont(new Font("Verdana", 1, 12));
        this.whoF.setBackground(Color.black);
        this.whoF.setLayout(new GridBagLayout());
        this.whoF.setSize(300, 150);
        this.whoF.setLocation(this.getToolkit().getScreenSize().width / 2 - 150, this.getToolkit().getScreenSize().height / 2 - 75);
        this.whoF.addWindowListener(this);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final Label label10 = new Label("BLOX 2");
        label10.setFont(new Font("Verdana", 1, 40));
        label10.setForeground(Color.white);
        this.whoF.add(label10, gridBagConstraints2);
        final Label label11 = new Label("John Morris ©1999");
        label11.setForeground(Color.yellow);
        gridBagConstraints2.gridy = 1;
        this.whoF.add(label11, gridBagConstraints2);
        final Button button13 = new Button("www.javaplayground.com");
        button13.setActionCommand("G");
        button13.addActionListener(this);
        button13.setBackground(Color.blue);
        button13.setForeground(Color.white);
        gridBagConstraints2.gridy = 2;
        this.whoF.add(button13, gridBagConstraints2);
        (this.eraseF = new Frame("Start Over")).setResizable(false);
        this.eraseF.setFont(new Font("Verdana", 1, 12));
        this.eraseF.setBackground(Color.black);
        this.eraseF.setLayout(new GridBagLayout());
        this.eraseF.setSize(300, 150);
        this.eraseF.setLocation(this.getToolkit().getScreenSize().width / 2 - 150, this.getToolkit().getScreenSize().height / 2 - 75);
        this.eraseF.addWindowListener(this);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final Label label12 = new Label("Are you sure you want to erase everything?");
        label12.setForeground(Color.white);
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        this.eraseF.add(label12, gridBagConstraints3);
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout());
        final Button button14 = new Button("Yes");
        button14.setActionCommand("Y");
        button14.addActionListener(this);
        panel3.add(button14);
        final Button button15 = new Button("No");
        button15.setActionCommand("N");
        button15.addActionListener(this);
        panel3.add(button15);
        gridBagConstraints3.gridy = 1;
        this.eraseF.add(panel3, gridBagConstraints3);
        (this.helpF = new Frame("Blox 2 Help")).setResizable(false);
        this.helpF.setFont(new Font("Verdana", 1, 12));
        this.helpF.setBackground(Color.black);
        this.helpF.setLayout(new GridLayout(0, 1));
        this.helpF.setSize(400, 200);
        this.helpF.setLocation(this.getToolkit().getScreenSize().width / 2 - 200, this.getToolkit().getScreenSize().height / 2 - 100);
        this.helpF.addWindowListener(this);
        this.helpMB = new MenuBar();
        final Menu menu = new Menu("Help With");
        final MenuItem menuItem = new MenuItem("Creating a Block");
        menuItem.setActionCommand("H1");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        final MenuItem menuItem2 = new MenuItem("Destroying a Block");
        menuItem2.setActionCommand("H2");
        menuItem2.addActionListener(this);
        menu.add(menuItem2);
        final MenuItem menuItem3 = new MenuItem("Moving a Block");
        menuItem3.setActionCommand("H3");
        menuItem3.addActionListener(this);
        menu.add(menuItem3);
        final MenuItem menuItem4 = new MenuItem("Layering a Block");
        menuItem4.setActionCommand("H4");
        menuItem4.addActionListener(this);
        menu.add(menuItem4);
        final MenuItem menuItem5 = new MenuItem("Setting the View");
        menuItem5.setActionCommand("H5");
        menuItem5.addActionListener(this);
        menu.add(menuItem5);
        final MenuItem menuItem6 = new MenuItem("Finding a Block");
        menuItem6.setActionCommand("H6");
        menuItem6.addActionListener(this);
        menu.add(menuItem6);
        final MenuItem menuItem7 = new MenuItem("Saving");
        menuItem7.setActionCommand("H7");
        menuItem7.addActionListener(this);
        menu.add(menuItem7);
        final MenuItem menuItem8 = new MenuItem("Loading");
        menuItem8.setActionCommand("H8");
        menuItem8.addActionListener(this);
        menu.add(menuItem8);
        this.helpMB.add(menu);
        this.helpF.setMenuBar(this.helpMB);
        (this.helpText = new String[9])[0] = new String("Select a help topic from the menu.");
        this.helpText[1] = new String("To create a block:\n  1. Select the style of block you want to create from the\n    \"Style\" list.\n  2. Select the color of the block from the \"Color\" list.\n  3. Select the dimensions(height X width) of the block from the\n    \"Dimensions\" list.\n  4. Press the \"Create\" button");
        this.helpText[2] = new String("To destroy a block:\n  1. Press the \"Destroy\" button to turn the delete function on.\n  2. Click on any block you want to destroy.\n  3. Press the \"Destroy\" button agan to turn the destroy\n    function off.");
        this.helpText[3] = new String("To move a block:\n  1. Click on the block to pick it up.\n  2. Move the block.\n  3. Click again to put the block down.");
        this.helpText[4] = new String("Layering a block:\n  1. Click on a block to pick it up.\n  2. Click again to put the block down (now you have selected\n    the block).\n  3. Press one of the yellow layer buttons to move the block's\n    position.\nPressing \"Forward\" or \"Backward\" will move the block in front\nof or behind one block at a time.\nPressing \"To Front\" or \"To Back\" will move the block in front of\nor behind all the blocks.");
        this.helpText[5] = new String("To Set the View:\n  1. Select the zooming strength from the \"View/Zoom\" list.\nSelecting \"Normal\" will bring the view back to normal.");
        this.helpText[6] = new String("To Find a Block:\n  1. Select \"Fit To Screen\" from the the \"View/Zoom\" list.\n  2. Click the block you're trying to find to zoom in on it.");
        this.helpText[7] = new String("To Save:\n  1. Press the \"Save\" button to open the save window.\n  2. Copy the text from the save window into a file with the\n   extension \".blx\".");
        this.helpText[8] = new String("To Load:\nThere are two(2) ways to load.\n  1. Press the \"Load\" button to open the load window.\n  2. Type in the URL of the \".blx\" file you want to load.\n  3. Press the \"OK\" button.\nOR\n  1. Insert the following tag between the <applet> and\n   </applet> tags in this page's html:\n   <param name=\"load\" value=\"<The File URL>\">\n   replacing <The File URL> with the URL of the \".blx\" file\n   you want to load.");
        this.helpT = new TextArea();
        this.setHelp(0);
        this.helpT.setEditable(false);
        this.helpT.setForeground(Color.white);
        this.helpT.setBackground(Color.black);
        this.helpF.add(this.helpT);
        this.hand = Cursor.getPredefinedCursor(12);
        this.crosshair = Cursor.getPredefinedCursor(1);
        this.hourglass = Cursor.getPredefinedCursor(3);
        this.setCursor(this.hand);
        this.Blox2G = this.getGraphics();
    }
    
    public final Color getBorderColor(final Color color) {
        if (color.equals(Color.black)) {
            return Color.white;
        }
        return Color.black;
    }
    
    public final void drawOvalPanel(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(n, n2, n3, n4);
        graphics.setColor(this.getBorderColor(color));
        graphics.drawOval(n, n2, n3, n4);
    }
    
    public final void drawPanel(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final boolean b, final Color color, final Graphics graphics) {
        final int[] array = new int[5];
        final int[] array2 = new int[5];
        array[0] = n;
        array[1] = n3;
        array[2] = n5;
        array[3] = n7;
        array[4] = array[0];
        array2[0] = n2;
        array2[1] = n4;
        array2[2] = n6;
        array2[3] = n8;
        array2[4] = array2[0];
        graphics.setColor(color);
        graphics.fillPolygon(array, array2, 5);
        if (b) {
            graphics.setColor(this.getBorderColor(color));
            graphics.drawPolygon(array, array2, 5);
        }
    }
    
    public final void drawBump(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(n, n2 - n4 / 2, n3, n4);
        graphics.fillRect(n, n2, n3, n4);
        graphics.setColor(this.getBorderColor(color));
        graphics.drawOval(n, n2 - n4 / 2, n3, n4);
        graphics.drawLine(n, n2, n, n2 + n4);
        graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
    }
    
    public final void paint(final Graphics graphics) {
        graphics.setColor(this.colors[this.backgroundC.getSelectedIndex()]);
        graphics.fillRect(100, 0, 500, 500);
        if (!this.title) {
            for (int i = 0; i < this.empty; ++i) {
                final int n = (int)(this.offset[0] + this.blocks[i].getX() * this.zoom[this.zoomC.getSelectedIndex()] + 100.0);
                final int n2 = (int)(this.offset[1] + this.blocks[i].getY() * this.zoom[this.zoomC.getSelectedIndex()]);
                final int n3 = this.blocks[i].getWidth() * this.zoom[this.zoomC.getSelectedIndex()];
                final int n4 = this.blocks[i].getHeight() * this.zoom[this.zoomC.getSelectedIndex()] / 2;
                final Color color = this.blocks[i].getColor();
                switch (this.blocks[i].getStyle()) {
                    case 1: {
                        this.drawOvalPanel(n, n2 + this.zoom[this.zoomC.getSelectedIndex()], n3 + n4, n4, color, graphics);
                        this.drawPanel(n, n2 + n4 / 2, n + n3 + n4, n2 + n4 / 2, n + n3 + n4, n2 + n4 / 2 + this.zoom[this.zoomC.getSelectedIndex()], n, n2 + n4 / 2 + this.zoom[this.zoomC.getSelectedIndex()], false, color, graphics);
                        this.drawOvalPanel(n, n2, n3 + n4, n4, color, graphics);
                        this.drawBump(n + (n3 + n4) / 2 - n3 / this.blocks[i].getWidth() / 4, n2 + n4 / 2 - n4 / this.blocks[i].getHeight() / 2, n3 / this.blocks[i].getWidth() / 2, n4 / this.blocks[i].getHeight() / 2, color, graphics);
                        break;
                    }
                    case 2: {
                        this.drawPanel(n, n2, n + n3, n2, n + n3 + this.zoom[this.zoomC.getSelectedIndex()] / 2, n2 + this.zoom[this.zoomC.getSelectedIndex()] / 2, n + this.zoom[this.zoomC.getSelectedIndex()] / 2, n2 + this.zoom[this.zoomC.getSelectedIndex()] / 2, true, color, graphics);
                        for (int j = n + n3 / this.blocks[i].getWidth() / 2; j < n + n3; j += n3 / this.blocks[i].getWidth()) {
                            this.drawBump(j, n2, n3 / this.blocks[i].getWidth() / 2, this.zoom[this.zoomC.getSelectedIndex()] / 4, color, graphics);
                        }
                        this.drawPanel(n + this.zoom[this.zoomC.getSelectedIndex()] / 2, n2 + this.zoom[this.zoomC.getSelectedIndex()] / 2, n + this.zoom[this.zoomC.getSelectedIndex()] / 2 + n3, n2 + this.zoom[this.zoomC.getSelectedIndex()] / 2, n + n3 + n4, n2 + this.zoom[this.zoomC.getSelectedIndex()] + n4, n + n4, n2 + this.zoom[this.zoomC.getSelectedIndex()] + n4, true, color, graphics);
                        this.drawPanel(n, n2, n + this.zoom[this.zoomC.getSelectedIndex()] / 2, n2 + this.zoom[this.zoomC.getSelectedIndex()] / 2, n + n4, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], n, n2 + this.zoom[this.zoomC.getSelectedIndex()], true, color, graphics);
                        break;
                    }
                    case 3: {
                        this.drawPanel(n, n2, n + n4, n2 + n4, n + n4, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], n, n2 + this.zoom[this.zoomC.getSelectedIndex()], true, color, graphics);
                        this.drawPanel(n, n2, n + this.zoom[this.zoomC.getSelectedIndex()], n2, n + this.zoom[this.zoomC.getSelectedIndex()] + n4, n2 + n4, n + n4, n2 + n4, true, color, graphics);
                        this.drawPanel(n + n4, n2 + n4, n + this.zoom[this.zoomC.getSelectedIndex()] + n4, n2 + n4, n + n4 + n3, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], n + n4, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], true, color, graphics);
                        this.drawPanel(n + this.zoom[this.zoomC.getSelectedIndex()], n2, n + n3, n2 + this.zoom[this.zoomC.getSelectedIndex()], n + n4 + n3, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], n + n4 + this.zoom[this.zoomC.getSelectedIndex()], n2 + n4, true, color, graphics);
                        for (int k = 0; k < this.blocks[i].getHeight(); ++k) {
                            this.drawBump(n + n3 / this.blocks[i].getWidth() / 2 + k * (n3 / this.blocks[i].getWidth()) / 2, n2 + k * (n4 / this.blocks[i].getHeight()), n3 / this.blocks[i].getWidth() / 2, n4 / this.blocks[i].getHeight() / 2, color, graphics);
                        }
                        break;
                    }
                    case 4: {
                        this.drawPanel(n + n3 - this.zoom[this.zoomC.getSelectedIndex()], n2, n + n3, n2, n + n3 + n4, n2 + n4, n + n3 + n4 - this.zoom[this.zoomC.getSelectedIndex()], n2 + n4, true, color, graphics);
                        this.drawPanel(n, n2 + this.zoom[this.zoomC.getSelectedIndex()], n + n3 - this.zoom[this.zoomC.getSelectedIndex()], n2, n + n3 + n4 - this.zoom[this.zoomC.getSelectedIndex()], n2 + n4, n + n4, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], true, color, graphics);
                        this.drawPanel(n + n4, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], n + n3 + n4 - this.zoom[this.zoomC.getSelectedIndex()], n2 + n4, n + n3 + n4, n2 + n4, n + n3 + n4, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], true, color, graphics);
                        for (int l = 0; l < this.blocks[i].getHeight(); ++l) {
                            this.drawBump(n + n3 / this.blocks[i].getWidth() / 2 + n3 - this.zoom[this.zoomC.getSelectedIndex()] + l * (n3 / this.blocks[i].getWidth()) / 2, n2 + l * (n4 / this.blocks[i].getHeight()), n3 / this.blocks[i].getWidth() / 2, n4 / this.blocks[i].getHeight() / 2, color, graphics);
                        }
                        break;
                    }
                    case 5:
                    case 6: {
                        this.drawPanel(n + n4, n2 + n4 + 4 * this.zoom[this.zoomC.getSelectedIndex()] / 5, n + n4 + n3, n2 + n4 + 4 * this.zoom[this.zoomC.getSelectedIndex()] / 5, n + n4 + n3, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], n + n4, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], true, color, graphics);
                        this.drawPanel(n, n2 + 4 * this.zoom[this.zoomC.getSelectedIndex()] / 5, n + n4, n2 + n4 + 4 * this.zoom[this.zoomC.getSelectedIndex()] / 5, n + n4, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], n, n2 + this.zoom[this.zoomC.getSelectedIndex()], true, color, graphics);
                        this.drawPanel(n, n2 + 4 * this.zoom[this.zoomC.getSelectedIndex()] / 5, n + n3, n2 + 4 * this.zoom[this.zoomC.getSelectedIndex()] / 5, n + n3 + n4, n2 + n4 + 4 * this.zoom[this.zoomC.getSelectedIndex()] / 5, n + n4, n2 + n4 + 4 * this.zoom[this.zoomC.getSelectedIndex()] / 5, true, color, graphics);
                        if (this.blocks[i].getStyle() == 6) {
                            for (int n5 = n + n3 / this.blocks[i].getWidth() / 2; n5 < n + n3; n5 += n3 / this.blocks[i].getWidth()) {
                                for (int n6 = 0; n6 < this.blocks[i].getHeight(); ++n6) {
                                    this.drawBump(n5 + n6 * (n3 / this.blocks[i].getWidth()) / 2, n2 + n6 * (n4 / this.blocks[i].getHeight()) + 4 * this.zoom[this.zoomC.getSelectedIndex()] / 5, n3 / this.blocks[i].getWidth() / 2, n4 / this.blocks[i].getHeight() / 2, color, graphics);
                                }
                            }
                            break;
                        }
                        break;
                    }
                    default: {
                        this.drawPanel(n + n4, n2 + n4, n + n4 + n3, n2 + n4, n + n4 + n3, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], n + n4, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], true, color, graphics);
                        this.drawPanel(n, n2, n + n4, n2 + n4, n + n4, n2 + n4 + this.zoom[this.zoomC.getSelectedIndex()], n, n2 + this.zoom[this.zoomC.getSelectedIndex()], true, color, graphics);
                        this.drawPanel(n, n2, n + n3, n2, n + n3 + n4, n2 + n4, n + n4, n2 + n4, true, color, graphics);
                        for (int n7 = n + n3 / this.blocks[i].getWidth() / 2; n7 < n + n3; n7 += n3 / this.blocks[i].getWidth()) {
                            for (int n8 = 0; n8 < this.blocks[i].getHeight(); ++n8) {
                                this.drawBump(n7 + n8 * (n3 / this.blocks[i].getWidth()) / 2, n2 + n8 * (n4 / this.blocks[i].getHeight()), n3 / this.blocks[i].getWidth() / 2, n4 / this.blocks[i].getHeight() / 2, color, graphics);
                            }
                        }
                        break;
                    }
                }
            }
            return;
        }
        graphics.setFont(new Font("Verdana", 1, 140));
        graphics.setColor(Color.red);
        graphics.drawString("B", 100, 250);
        graphics.setColor(Color.yellow);
        graphics.drawString("L", 200, 250);
        graphics.setColor(Color.green);
        graphics.drawString("O", 300, 250);
        graphics.setColor(Color.blue);
        graphics.drawString("X", 400, 250);
        graphics.setColor(Color.white);
        graphics.drawString("2", 500, 250);
        graphics.setFont(new Font("Verdana", 1, 40));
        graphics.drawString("by John Morris ©1999", 120, 300);
        graphics.setFont(new Font("Verdana", 1, 30));
        graphics.setColor(Color.yellow);
        graphics.drawString("www.javaplayground.com", 130, 340);
    }
    
    public final void update(final Graphics graphics) {
        final Image image = this.createImage(600, 500);
        this.paint(image.getGraphics());
        graphics.drawImage(image, 0, 0, null);
    }
    
    public final void setHelp(final int n) {
        this.helpT.setText(this.helpText[n]);
    }
    
    public final void setSave() {
        this.saveT.setText("");
        if (this.empty == 0) {
            this.saveT.setText("No Blocks Added");
            return;
        }
        for (int i = 0; i < this.empty; ++i) {
            this.saveT.append(String.valueOf(Integer.toString(this.blocks[i].getStyle())) + "," + Integer.toString(this.blocks[i].getColor().getRed()) + "," + Integer.toString(this.blocks[i].getColor().getGreen()) + "," + Integer.toString(this.blocks[i].getColor().getBlue()) + "," + Double.toString(this.blocks[i].getX()) + "," + Double.toString(this.blocks[i].getY()) + "," + Integer.toString(this.blocks[i].getWidth()) + "," + Integer.toString(this.blocks[i].getHeight()) + "\n");
        }
    }
    
    public final void addBlock(final Block block) {
        if (this.empty >= this.blocks.length) {
            final Block[] array = new Block[this.blocks.length];
            for (int i = 0; i < this.blocks.length; ++i) {
                array[i] = this.blocks[i];
            }
            this.blocks = new Block[array.length + 10];
            for (int j = 0; j < array.length; ++j) {
                this.blocks[j] = array[j];
            }
        }
        this.blocks[this.empty] = block;
        ++this.empty;
        this.index = this.empty - 1;
    }
    
    public final void parseLine(final String s) {
        final Block block = new Block();
        final int n = 0;
        final int index = s.indexOf(",");
        try {
            block.setStyle(Integer.parseInt(s.substring(n, index)));
        }
        catch (Exception ex) {
            block.setStyle(0);
        }
        final int n2 = index + 1;
        int n3 = s.indexOf(",", index + 1);
        try {
            final int int1 = Integer.parseInt(s.substring(n2, n3));
            final int n4 = n3 + 1;
            n3 = s.indexOf(",", n3 + 1);
            final int int2 = Integer.parseInt(s.substring(n4, n3));
            final int n5 = n3 + 1;
            n3 = s.indexOf(",", n3 + 1);
            block.setColor(new Color(int1, int2, Integer.parseInt(s.substring(n5, n3))));
        }
        catch (Exception ex2) {
            block.setColor(Color.red);
        }
        final int n6 = n3 + 1;
        final int index2 = s.indexOf(",", n3 + 1);
        try {
            block.setX(Integer.parseInt(s.substring(n6, index2)));
        }
        catch (Exception ex3) {
            block.setX(0.0);
        }
        final int n7 = index2 + 1;
        final int index3 = s.indexOf(",", index2 + 1);
        try {
            block.setY(Integer.parseInt(s.substring(n7, index3)));
        }
        catch (Exception ex4) {
            block.setY(0.0);
        }
        final int n8 = index3 + 1;
        final int index4 = s.indexOf(",", index3 + 1);
        try {
            block.setWidth(Integer.parseInt(s.substring(n8, index4)));
        }
        catch (Exception ex5) {
            block.setWidth(1);
        }
        try {
            block.setHeight(Integer.parseInt(s.substring(index4 + 1)));
        }
        catch (Exception ex6) {
            block.setHeight(1);
        }
        this.addBlock(block);
    }
    
    public final void loadFile(String string) {
        this.setCursor(this.hourglass);
        if (!string.substring(string.length() - 4).equals(".blx")) {
            string = String.valueOf(string) + ".blx";
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(string).openConnection().getInputStream()));
            this.blocks = new Block[100];
            this.index = 0;
            this.empty = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.parseLine(line);
            }
            bufferedReader.close();
        }
        catch (Exception ex) {}
    }
    
    public final int[] stringToDimension(final String s) {
        return new int[] { Integer.parseInt(s.substring(0, s.indexOf("X"))), Integer.parseInt(s.substring(s.indexOf("X") + 1)) };
    }
    
    public final void removeDimensions(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.dimensionC.remove(array[i]);
        }
    }
    
    public final void resetDimensions() {
        this.dimensionC.removeAll();
        this.dimensionC.add("1X1");
        this.dimensionC.add("1X2");
        this.dimensionC.add("1X4");
        this.dimensionC.add("2X1");
        this.dimensionC.add("2X2");
        this.dimensionC.add("2X4");
        this.dimensionC.add("4X1");
        this.dimensionC.add("4X2");
        this.dimensionC.add("4X4");
    }
    
    public final void turnDeleteOff() {
        this.delete = false;
        this.setCursor(this.hand);
        this.deleteL.setForeground(Color.white);
        this.deleteL.setText("Destroy Off");
    }
    
    public final boolean moveBack(final int n, final int n2) {
        if (n >= 0 && n2 >= 0 && n < this.empty && n2 < this.empty) {
            this.setCursor(this.hourglass);
            final Block block = this.blocks[n2];
            for (int i = n2; i > n; --i) {
                this.blocks[i] = this.blocks[i - 1];
            }
            this.blocks[n] = block;
            this.setCursor(this.hand);
            return true;
        }
        return false;
    }
    
    public final boolean moveForward(final int n, final int n2) {
        if (n >= 0 && n2 >= 0 && n < this.empty && n2 < this.empty) {
            this.setCursor(this.hourglass);
            final Block block = this.blocks[n];
            for (int i = n; i < n2; ++i) {
                this.blocks[i] = this.blocks[i + 1];
            }
            this.blocks[n2] = block;
            this.setCursor(this.hand);
            return true;
        }
        return false;
    }
    
    public final int getBlock(int n, int n2) {
        this.setCursor(this.hourglass);
        int n3 = -1;
        n = n + this.horizontal.getValue() - 100;
        n2 += this.vertical.getValue();
        for (int i = 0; i < this.empty; ++i) {
            final int n4 = (int)(this.blocks[i].getX() * this.zoom[this.zoomC.getSelectedIndex()]);
            final int n5 = (int)(this.blocks[i].getY() * this.zoom[this.zoomC.getSelectedIndex()]);
            final int n6 = this.blocks[i].getHeight() * this.zoom[this.zoomC.getSelectedIndex()] / 2 + this.zoom[this.zoomC.getSelectedIndex()];
            final int n7 = this.blocks[i].getWidth() * this.zoom[this.zoomC.getSelectedIndex()] + this.blocks[i].getHeight() * this.zoom[this.zoomC.getSelectedIndex()] / 2;
            if (n > n4 && n2 > n5 && n < n4 + n7 && n2 < n5 + n6) {
                n3 = i;
            }
        }
        return n3;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.hold = false;
        final String s = (String)itemEvent.getItem();
        final Choice choice = (Choice)itemEvent.getItemSelectable();
        if (choice == this.styleC) {
            this.resetDimensions();
            switch (this.styleC.getSelectedIndex()) {
                case 1: {
                    this.removeDimensions(new String[] { "1X2", "1X4", "2X1", "2X4", "4X1", "4X2" });
                    break;
                }
                case 2: {
                    this.removeDimensions(new String[] { "1X1", "2X1", "4X1" });
                    break;
                }
                case 3:
                case 4: {
                    this.removeDimensions(new String[] { "1X1", "1X2", "1X4" });
                    break;
                }
            }
        }
        else if (choice == this.zoomC) {
            final int maximum = this.horizontal.getMaximum();
            final int value = this.horizontal.getValue();
            this.horizontal.setMaximum((this.zoom[this.zoomC.getSelectedIndex()] - 1) * 500);
            this.horizontal.setUnitIncrement(this.zoom[this.zoomC.getSelectedIndex()]);
            this.horizontal.setValue(value * this.horizontal.getMaximum() / maximum);
            this.offset[0] = -this.horizontal.getValue();
            final int maximum2 = this.vertical.getMaximum();
            final int value2 = this.vertical.getValue();
            this.vertical.setMaximum((this.zoom[this.zoomC.getSelectedIndex()] - 1) * 500);
            this.vertical.setUnitIncrement(this.zoom[this.zoomC.getSelectedIndex()]);
            this.vertical.setValue(value2 * this.vertical.getMaximum() / maximum2);
            this.offset[1] = -this.vertical.getValue();
        }
        this.update(this.Blox2G);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.hold = false;
        final String actionCommand = actionEvent.getActionCommand();
        switch (actionCommand.charAt(0)) {
            case 'L': {
                this.loadF.show();
                break;
            }
            case 'O': {
                this.title = false;
                this.loadF.dispose();
                this.loadFile(String.valueOf(this.getDocumentBase().getProtocol()) + "://" + this.getDocumentBase().getHost() + "/" + this.loadT.getText());
                this.delete = false;
                this.setCursor(this.hand);
                break;
            }
            case 'S': {
                this.setSave();
                this.saveF.show();
                break;
            }
            case 'E': {
                this.eraseF.show();
                break;
            }
            case 'Y': {
                this.blocks = new Block[100];
                this.index = 0;
                this.empty = 0;
            }
            case 'N': {
                this.eraseF.dispose();
                break;
            }
            case 'C': {
                this.title = false;
                this.turnDeleteOff();
                final int[] stringToDimension = this.stringToDimension(this.dimensionC.getSelectedItem());
                this.addBlock(new Block(this.styleC.getSelectedIndex(), this.colors[this.colorC.getSelectedIndex()], (this.horizontal.getValue() + 250 - (stringToDimension[0] * this.zoom[this.zoomC.getSelectedIndex()] + stringToDimension[1] * this.zoom[this.zoomC.getSelectedIndex()] / 2) / 2) / this.zoom[this.zoomC.getSelectedIndex()], (this.vertical.getValue() + 250 - (stringToDimension[1] * this.zoom[this.zoomC.getSelectedIndex()] / 2 + this.zoom[this.zoomC.getSelectedIndex()]) / 2) / this.zoom[this.zoomC.getSelectedIndex()], stringToDimension));
                break;
            }
            case 'F': {
                if (this.moveForward(this.index, this.index + 1)) {
                    ++this.index;
                    break;
                }
                break;
            }
            case 'B': {
                if (this.moveBack(this.index - 1, this.index)) {
                    --this.index;
                    break;
                }
                break;
            }
            case 'T': {
                if (this.moveForward(this.index, this.empty - 1)) {
                    this.index = this.empty - 1;
                    break;
                }
                break;
            }
            case 'K': {
                if (this.moveBack(0, this.index)) {
                    this.index = 0;
                    break;
                }
                break;
            }
            case 'D': {
                this.delete = !this.delete;
                if (this.delete) {
                    this.delete = true;
                    this.setCursor(this.crosshair);
                    this.deleteL.setForeground(Color.red);
                    this.deleteL.setText("Destroy On");
                    break;
                }
                this.turnDeleteOff();
                break;
            }
            case 'P': {
                this.helpF.setMenuBar(this.helpMB);
                this.helpF.show();
                break;
            }
            case 'H': {
                this.setHelp(Integer.parseInt(actionCommand.substring(1)));
                break;
            }
            case 'G': {
                try {
                    this.getAppletContext().showDocument(new URL("http://www.javaplayground.com"), "_blank");
                    this.whoF.dispose();
                }
                catch (Exception ex) {}
                break;
            }
            case '?': {
                this.whoF.show();
                break;
            }
        }
        this.update(this.Blox2G);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getAdjustable() == this.horizontal) {
            this.offset[0] = -adjustmentEvent.getValue();
        }
        else {
            this.offset[1] = -adjustmentEvent.getValue();
        }
        this.update(this.Blox2G);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > 100) {
            if (this.zoomC.getSelectedIndex() > 0) {
                if (this.hold) {
                    this.hold = false;
                }
                else if (this.delete) {
                    if (this.moveForward(this.getBlock(x, y), this.empty - 1)) {
                        this.blocks[this.empty - 1] = null;
                        --this.empty;
                        this.index = this.empty - 1;
                    }
                    this.setCursor(this.crosshair);
                }
                else if (!this.hold) {
                    final int block = this.getBlock(x, y);
                    if (block >= 0 && block < this.empty) {
                        this.index = block;
                        this.addToCenter[0] = (this.horizontal.getValue() + x - 100) / this.zoom[this.zoomC.getSelectedIndex()] - (int)this.blocks[this.index].getX();
                        this.addToCenter[1] = (this.vertical.getValue() + y) / this.zoom[this.zoomC.getSelectedIndex()] - (int)this.blocks[this.index].getY();
                        this.hold = true;
                    }
                    this.setCursor(this.hand);
                }
            }
            else {
                this.zoomC.select("Normal");
                this.horizontal.setMaximum((this.zoom[this.zoomC.getSelectedIndex()] - 1) * 500);
                this.horizontal.setUnitIncrement(this.zoom[this.zoomC.getSelectedIndex()]);
                this.horizontal.setValue((x - 100) * this.horizontal.getMaximum() / 500);
                this.offset[0] = -this.horizontal.getValue();
                this.vertical.setMaximum((this.zoom[this.zoomC.getSelectedIndex()] - 1) * 500);
                this.vertical.setUnitIncrement(this.zoom[this.zoomC.getSelectedIndex()]);
                this.vertical.setValue(y * this.vertical.getMaximum() / 500);
                this.offset[1] = -this.vertical.getValue();
            }
            this.update(this.Blox2G);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final double x = (this.horizontal.getValue() + mouseEvent.getX() - 100) / this.zoom[this.zoomC.getSelectedIndex()] - this.addToCenter[0];
        final double y = (this.vertical.getValue() + mouseEvent.getY()) / this.zoom[this.zoomC.getSelectedIndex()] - this.addToCenter[1];
        if (this.hold && (x <= this.blocks[this.index].getX() - 0.0 || x >= this.blocks[this.index].getX() + 0.0 || y <= this.blocks[this.index].getY() - 0.0 || y >= this.blocks[this.index].getY() + 0.0)) {
            this.blocks[this.index].setX(x);
            this.blocks[this.index].setY(y);
            this.update(this.Blox2G);
        }
        if (mouseEvent.getX() >= 100) {
            this.showStatus("X: " + Double.toString(x) + ", Y: " + Double.toString(y));
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        ((Frame)windowEvent.getWindow()).dispose();
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
    
    public void destroy() {
        this.Blox2G = null;
        this.horizontal = null;
        this.vertical = null;
        this.styleC = null;
        this.colorC = null;
        this.dimensionC = null;
        this.deleteL = null;
        this.backgroundC = null;
        this.zoomC = null;
        this.helpF = null;
        this.helpMB = null;
        this.helpT = null;
        this.helpText = null;
        this.saveF = null;
        this.saveT = null;
        this.loadF = null;
        this.loadT = null;
        this.whoF = null;
        this.eraseF = null;
        this.hand = null;
        this.crosshair = null;
        this.hourglass = null;
        this.addToCenter = null;
        this.colors = null;
        this.zoom = null;
        this.offset = null;
        this.blocks = null;
    }
}
