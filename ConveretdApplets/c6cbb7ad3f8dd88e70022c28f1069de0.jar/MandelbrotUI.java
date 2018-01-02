import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Button;
import java.awt.event.WindowListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MandelbrotUI extends Applet implements ActionListener, MouseListener, MouseMotionListener, ItemListener, WindowListener
{
    private Button ivjButtonGenerate;
    private Label ivjLabelIterations;
    private Label ivjLabelIterationsValue;
    private Label ivjLabelXMax;
    private Label ivjLabelXMin;
    private Label ivjLabelYMax;
    private Label ivjLabelYMin;
    private Panel ivjPanelBody;
    private Panel ivjPanelMenu;
    private TextField ivjTextFieldXMax;
    private TextField ivjTextFieldXMin;
    private TextField ivjTextFieldYMax;
    private TextField ivjTextFieldYMin;
    private Button ivjButtonHelp;
    private Choice ivjChoiceColorScheme;
    private Choice ivjChoiceSampleRegions;
    private Label ivjLabelColorScheme;
    private Label ivjLabelSampleRegions;
    private static final int maxSize = 4;
    private int colorScheme;
    private int maxCol;
    private int maxRow;
    private int maxIterations;
    private double P;
    private double[] Q;
    private double XMax;
    private double XMin;
    private double YMax;
    private double YMin;
    private double[] arrXMin;
    private double[] arrXMax;
    private double[] arrYMin;
    private double[] arrYMax;
    private double deltaP;
    private double deltaQ;
    public static final String[] colors;
    public static final String[] colors2;
    public static final String[] colors3;
    public static final String[] colors4;
    public static final String[] colors5;
    private Graphics myGraphics;
    private Image image;
    
    public MandelbrotUI() {
        this.ivjButtonGenerate = null;
        this.ivjLabelIterations = null;
        this.ivjLabelIterationsValue = null;
        this.ivjLabelXMax = null;
        this.ivjLabelXMin = null;
        this.ivjLabelYMax = null;
        this.ivjLabelYMin = null;
        this.ivjPanelBody = null;
        this.ivjPanelMenu = null;
        this.ivjTextFieldXMax = null;
        this.ivjTextFieldXMin = null;
        this.ivjTextFieldYMax = null;
        this.ivjTextFieldYMin = null;
        this.ivjButtonHelp = null;
        this.ivjChoiceColorScheme = null;
        this.ivjChoiceSampleRegions = null;
        this.ivjLabelColorScheme = null;
        this.ivjLabelSampleRegions = null;
        this.maxIterations = 512;
        this.XMax = 1.2;
        this.XMin = -2.0;
        this.YMax = 1.2;
        this.YMin = -1.2;
        this.arrXMin = new double[] { -2.0, -0.702973, -0.691594, -0.69106, -0.793114, -0.749337, -0.745465, -0.745464 };
        this.arrXMax = new double[] { 1.2, -0.642879, -0.690089, -0.690906, -0.723005, -0.744948, -0.745387, -0.745388 };
        this.arrYMin = new double[] { -1.2, 0.374785, 0.386608, 0.387103, 0.037822, 0.109349, 0.112896, 0.112967 };
        this.arrYMax = new double[] { 1.2, 0.395415, 0.387494, 0.387228, 0.140974, 0.115851, 0.113034, 0.11303 };
    }
    
    public void buttonGenerate_ActionPerformed(final ActionEvent actionEvent) {
        final Graphics graphics = this.getPanelBody().getGraphics();
        this.colorScheme = this.getChoiceColorScheme().getSelectedIndex();
        graphics.clearRect(0, 0, this.maxCol, this.maxRow);
        this.XMin = Double.parseDouble(this.getTextFieldXMin().getText());
        this.XMax = Double.parseDouble(this.getTextFieldXMax().getText());
        this.YMin = Double.parseDouble(this.getTextFieldYMin().getText());
        this.YMax = Double.parseDouble(this.getTextFieldYMax().getText());
        this.generateImage(graphics);
    }
    
    public void buttonHelp_ActionPerformed(final ActionEvent actionEvent) {
        this.helpOn(this.getPanelBody().getGraphics());
    }
    
    private void connEtoC1(final ActionEvent actionEvent) {
        try {
            this.buttonGenerate_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC2(final ActionEvent actionEvent) {
        try {
            this.buttonHelp_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    public void destroy() {
        super.destroy();
    }
    
    private void generateImage(final Graphics graphics) {
        final int maxIterations = this.maxIterations;
        this.resetPQ();
        for (int i = 0; i < this.maxCol; ++i) {
            for (int j = 0; j < this.maxRow; ++j) {
                double n;
                double n2;
                double n3;
                double n4;
                int n5;
                double n6;
                for (n = 0.0, n2 = 0.0, n3 = 0.0, n4 = 0.0, n5 = 0; n5 < this.maxIterations && n3 + n4 < 4.0; n3 = n * n, n4 = n2 * n2, n6 = n2 * n, n2 = n6 + (n6 + this.Q[j]), n = n3 - n4 + this.P, ++n5) {}
                if (0 == this.colorScheme) {
                    graphics.setColor(new Color(Integer.parseInt(MandelbrotUI.colors[n5 % MandelbrotUI.colors.length], 16)));
                }
                else if (1 == this.colorScheme) {
                    graphics.setColor(new Color(Integer.parseInt(MandelbrotUI.colors3[n5 % MandelbrotUI.colors3.length], 16)));
                }
                else if (2 == this.colorScheme) {
                    graphics.setColor(new Color(Integer.parseInt(MandelbrotUI.colors2[n5 % MandelbrotUI.colors2.length], 16)));
                }
                else if (3 == this.colorScheme) {
                    graphics.setColor(new Color(Integer.parseInt(MandelbrotUI.colors4[n5 % MandelbrotUI.colors4.length], 16)));
                }
                else if (4 == this.colorScheme) {
                    graphics.setColor(new Color(Integer.parseInt(MandelbrotUI.colors5[n5 % MandelbrotUI.colors5.length], 16)));
                }
                graphics.fillOval(i, j, 1, 1);
            }
            this.P += this.deltaP;
        }
    }
    
    public String getAppletInfo() {
        return "MandelbrotUI\n\nGenerates Mandelbrot sets.\nCreation date: (09/10/2002 3:17:08 PM)\n@author: Jose Sandoval\n";
    }
    
    private Button getButtonGenerate() {
        if (this.ivjButtonGenerate == null) {
            try {
                (this.ivjButtonGenerate = new Button()).setName("ButtonGenerate");
                this.ivjButtonGenerate.setLabel("Generate");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButtonGenerate;
    }
    
    private Button getButtonHelp() {
        if (this.ivjButtonHelp == null) {
            try {
                (this.ivjButtonHelp = new Button()).setName("ButtonHelp");
                this.ivjButtonHelp.setLabel("Help");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjButtonHelp;
    }
    
    private Choice getChoiceColorScheme() {
        if (this.ivjChoiceColorScheme == null) {
            try {
                (this.ivjChoiceColorScheme = new Choice()).setName("ChoiceColorScheme");
                this.ivjChoiceColorScheme.setFont(new Font("dialog", 0, 10));
                this.ivjChoiceColorScheme.add("Original");
                this.ivjChoiceColorScheme.add("16 colors");
                this.ivjChoiceColorScheme.add("24 Colors");
                this.ivjChoiceColorScheme.add("Black and White");
                this.ivjChoiceColorScheme.add("Orange Hue");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjChoiceColorScheme;
    }
    
    private Choice getChoiceSampleRegions() {
        if (this.ivjChoiceSampleRegions == null) {
            try {
                (this.ivjChoiceSampleRegions = new Choice()).setName("ChoiceSampleRegions");
                this.ivjChoiceSampleRegions.setFont(new Font("dialog", 0, 10));
                this.ivjChoiceSampleRegions.add("Default");
                this.ivjChoiceSampleRegions.add("Zoom 1");
                this.ivjChoiceSampleRegions.add("Zoom 2");
                this.ivjChoiceSampleRegions.add("Zoom 3");
                this.ivjChoiceSampleRegions.add("Zoom 4");
                this.ivjChoiceSampleRegions.add("Zoom 5");
                this.ivjChoiceSampleRegions.add("Zoom 6");
                this.ivjChoiceSampleRegions.add("Zoom 7");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjChoiceSampleRegions;
    }
    
    private Label getLabelColorScheme() {
        if (this.ivjLabelColorScheme == null) {
            try {
                (this.ivjLabelColorScheme = new Label()).setName("LabelColorScheme");
                this.ivjLabelColorScheme.setAlignment(2);
                this.ivjLabelColorScheme.setFont(new Font("dialog", 0, 10));
                this.ivjLabelColorScheme.setText("Color Scheme:");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelColorScheme;
    }
    
    private Label getLabelIterations() {
        if (this.ivjLabelIterations == null) {
            try {
                (this.ivjLabelIterations = new Label()).setName("LabelIterations");
                this.ivjLabelIterations.setFont(new Font("dialog", 0, 10));
                this.ivjLabelIterations.setText("No. of Iterations:");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelIterations;
    }
    
    private Label getLabelIterationsValue() {
        if (this.ivjLabelIterationsValue == null) {
            try {
                (this.ivjLabelIterationsValue = new Label()).setName("LabelIterationsValue");
                this.ivjLabelIterationsValue.setFont(new Font("dialog", 0, 10));
                this.ivjLabelIterationsValue.setText("512");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelIterationsValue;
    }
    
    private Label getLabelSampleRegions() {
        if (this.ivjLabelSampleRegions == null) {
            try {
                (this.ivjLabelSampleRegions = new Label()).setName("LabelSampleRegions");
                this.ivjLabelSampleRegions.setAlignment(2);
                this.ivjLabelSampleRegions.setFont(new Font("dialog", 0, 10));
                this.ivjLabelSampleRegions.setText("Sample Regions:");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelSampleRegions;
    }
    
    private Label getLabelXMax() {
        if (this.ivjLabelXMax == null) {
            try {
                (this.ivjLabelXMax = new Label()).setName("LabelXMax");
                this.ivjLabelXMax.setAlignment(2);
                this.ivjLabelXMax.setFont(new Font("dialog", 0, 10));
                this.ivjLabelXMax.setText("X Max:");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelXMax;
    }
    
    private Label getLabelXMin() {
        if (this.ivjLabelXMin == null) {
            try {
                (this.ivjLabelXMin = new Label()).setName("LabelXMin");
                this.ivjLabelXMin.setAlignment(2);
                this.ivjLabelXMin.setFont(new Font("dialog", 0, 10));
                this.ivjLabelXMin.setText("X Min:");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelXMin;
    }
    
    private Label getLabelYMax() {
        if (this.ivjLabelYMax == null) {
            try {
                (this.ivjLabelYMax = new Label()).setName("LabelYMax");
                this.ivjLabelYMax.setAlignment(2);
                this.ivjLabelYMax.setFont(new Font("dialog", 0, 10));
                this.ivjLabelYMax.setText("Y Max:");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelYMax;
    }
    
    private Label getLabelYMin() {
        if (this.ivjLabelYMin == null) {
            try {
                (this.ivjLabelYMin = new Label()).setName("LabelYMin");
                this.ivjLabelYMin.setAlignment(2);
                this.ivjLabelYMin.setFont(new Font("dialog", 0, 10));
                this.ivjLabelYMin.setText("Y Min:");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelYMin;
    }
    
    private Panel getPanelBody() {
        if (this.ivjPanelBody == null) {
            try {
                (this.ivjPanelBody = new Panel()).setName("PanelBody");
                this.ivjPanelBody.setLayout(new CardLayout());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelBody;
    }
    
    private Panel getPanelMenu() {
        if (this.ivjPanelMenu == null) {
            try {
                (this.ivjPanelMenu = new Panel()).setName("PanelMenu");
                this.ivjPanelMenu.setLayout(new GridBagLayout());
                final GridBagConstraints gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.fill = 2;
                gridBagConstraints.anchor = 17;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getTextFieldXMin(), gridBagConstraints);
                final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 2;
                gridBagConstraints2.fill = 2;
                gridBagConstraints2.anchor = 17;
                gridBagConstraints2.weightx = 1.0;
                gridBagConstraints2.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getTextFieldXMax(), gridBagConstraints2);
                final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
                gridBagConstraints3.gridx = 3;
                gridBagConstraints3.gridy = 1;
                gridBagConstraints3.fill = 2;
                gridBagConstraints3.anchor = 17;
                gridBagConstraints3.weightx = 1.0;
                gridBagConstraints3.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getTextFieldYMin(), gridBagConstraints3);
                final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
                gridBagConstraints4.gridx = 3;
                gridBagConstraints4.gridy = 2;
                gridBagConstraints4.fill = 2;
                gridBagConstraints4.anchor = 17;
                gridBagConstraints4.weightx = 1.0;
                gridBagConstraints4.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getTextFieldYMax(), gridBagConstraints4);
                final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
                gridBagConstraints5.gridx = 0;
                gridBagConstraints5.gridy = 1;
                gridBagConstraints5.anchor = 13;
                gridBagConstraints5.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getLabelXMin(), gridBagConstraints5);
                final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
                gridBagConstraints6.gridx = 0;
                gridBagConstraints6.gridy = 2;
                gridBagConstraints6.anchor = 13;
                gridBagConstraints6.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getLabelXMax(), gridBagConstraints6);
                final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
                gridBagConstraints7.gridx = 2;
                gridBagConstraints7.gridy = 1;
                gridBagConstraints7.anchor = 13;
                gridBagConstraints7.insets = new Insets(8, 4, 8, 4);
                this.getPanelMenu().add(this.getLabelYMin(), gridBagConstraints7);
                final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
                gridBagConstraints8.gridx = 2;
                gridBagConstraints8.gridy = 2;
                gridBagConstraints8.anchor = 13;
                gridBagConstraints8.insets = new Insets(8, 4, 8, 4);
                this.getPanelMenu().add(this.getLabelYMax(), gridBagConstraints8);
                final GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
                gridBagConstraints9.gridx = 4;
                gridBagConstraints9.gridy = 0;
                gridBagConstraints9.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getLabelIterations(), gridBagConstraints9);
                final GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
                gridBagConstraints10.gridx = 5;
                gridBagConstraints10.gridy = 0;
                gridBagConstraints10.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getLabelIterationsValue(), gridBagConstraints10);
                final GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
                gridBagConstraints11.gridx = 4;
                gridBagConstraints11.gridy = 2;
                gridBagConstraints11.gridwidth = 2;
                gridBagConstraints11.fill = 2;
                gridBagConstraints11.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getButtonGenerate(), gridBagConstraints11);
                final GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
                gridBagConstraints12.gridx = 1;
                gridBagConstraints12.gridy = 0;
                gridBagConstraints12.fill = 2;
                gridBagConstraints12.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getChoiceColorScheme(), gridBagConstraints12);
                final GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
                gridBagConstraints13.gridx = 0;
                gridBagConstraints13.gridy = 0;
                gridBagConstraints13.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getLabelColorScheme(), gridBagConstraints13);
                final GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
                gridBagConstraints14.gridx = 2;
                gridBagConstraints14.gridy = 0;
                gridBagConstraints14.anchor = 13;
                gridBagConstraints14.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getLabelSampleRegions(), gridBagConstraints14);
                final GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
                gridBagConstraints15.gridx = 3;
                gridBagConstraints15.gridy = 0;
                gridBagConstraints15.fill = 2;
                gridBagConstraints15.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getChoiceSampleRegions(), gridBagConstraints15);
                final GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
                gridBagConstraints16.gridx = 4;
                gridBagConstraints16.gridy = 1;
                gridBagConstraints16.gridwidth = 2;
                gridBagConstraints16.fill = 2;
                gridBagConstraints16.insets = new Insets(4, 4, 4, 4);
                this.getPanelMenu().add(this.getButtonHelp(), gridBagConstraints16);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelMenu;
    }
    
    private TextField getTextFieldXMax() {
        if (this.ivjTextFieldXMax == null) {
            try {
                (this.ivjTextFieldXMax = new TextField()).setName("TextFieldXMax");
                this.ivjTextFieldXMax.setFont(new Font("dialog", 0, 10));
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextFieldXMax;
    }
    
    private TextField getTextFieldXMin() {
        if (this.ivjTextFieldXMin == null) {
            try {
                (this.ivjTextFieldXMin = new TextField()).setName("TextFieldXMin");
                this.ivjTextFieldXMin.setFont(new Font("dialog", 0, 10));
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextFieldXMin;
    }
    
    private TextField getTextFieldYMax() {
        if (this.ivjTextFieldYMax == null) {
            try {
                (this.ivjTextFieldYMax = new TextField()).setName("TextFieldYMax");
                this.ivjTextFieldYMax.setFont(new Font("dialog", 0, 10));
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextFieldYMax;
    }
    
    private TextField getTextFieldYMin() {
        if (this.ivjTextFieldYMin == null) {
            try {
                (this.ivjTextFieldYMin = new TextField()).setName("TextFieldYMin");
                this.ivjTextFieldYMin.setFont(new Font("dialog", 0, 10));
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextFieldYMin;
    }
    
    private void handleException(final Throwable t) {
    }
    
    public void helpOn(final Graphics graphics) {
        final int n = 40;
        final int n2 = 20;
        graphics.clearRect(0, 0, this.maxCol, this.maxRow);
        graphics.setColor(Color.gray);
        graphics.drawRect(10, 10, 620, 460);
        graphics.setColor(Color.black);
        graphics.drawString("MandelbrotUI v1.0 - Jose Sandoval - (c) October 2002", n, 60);
        graphics.drawString("The applet generates the Mandelbrot set.", n, 80);
        graphics.setColor(Color.red);
        graphics.drawString("Instructions:", n, n2 + 100);
        graphics.setColor(Color.black);
        graphics.drawString("Color Scheme - Holds values for predefined color schemes", n, n2 + 120);
        graphics.drawString("Sample Regions - Holds values for predefined interesting (I think) regions of the set", n, n2 + 140);
        graphics.drawString("Rectangle co-ordinates: (XMin, YMin), (XMax, YMax)", n, n2 + 160);
        graphics.drawString("Help - Displays this help", n, n2 + 180);
        graphics.drawString("Generate - Generates the Mandelbrot set with parameters entered", n, n2 + 200);
    }
    
    public void init() {
        try {
            super.init();
            this.setName("MandelbrotUI");
            this.setLayout(new BorderLayout());
            this.setSize(640, 600);
            this.add(this.getPanelMenu(), "North");
            this.add(this.getPanelBody(), "Center");
            this.initConnections();
            this.maxCol = 640;
            this.maxRow = 480;
            this.image = this.createImage(this.maxCol, this.maxRow);
            this.myGraphics = this.image.getGraphics();
            this.getLabelIterationsValue().setText("" + this.maxIterations);
            this.getTextFieldXMin().setText("" + this.XMin);
            this.getTextFieldXMax().setText("" + this.XMax);
            this.getTextFieldYMin().setText("" + this.YMin);
            this.getTextFieldYMax().setText("" + this.YMax);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void initConnections() throws Exception {
        this.getChoiceSampleRegions().addItemListener(this);
        this.getButtonGenerate().addActionListener(this);
        this.getButtonHelp().addActionListener(this);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final int selectedIndex = this.getChoiceSampleRegions().getSelectedIndex();
        this.getTextFieldXMin().setText("" + this.arrXMin[selectedIndex]);
        this.getTextFieldXMax().setText("" + this.arrXMax[selectedIndex]);
        this.getTextFieldYMin().setText("" + this.arrYMin[selectedIndex]);
        this.getTextFieldYMax().setText("" + this.arrYMax[selectedIndex]);
    }
    
    public static void main(final String[] array) {
        final MandelbrotUI mandelbrotUI = new MandelbrotUI();
        final Frame frame = new Frame("Applet");
        frame.addWindowListener(mandelbrotUI);
        frame.add("Center", mandelbrotUI);
        frame.setSize(350, 250);
        frame.show();
        mandelbrotUI.init();
        mandelbrotUI.start();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        System.out.println("mouseClicked");
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        System.out.println("mouseDragged");
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        System.out.println("mouseEntered");
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        System.out.println("mouseExited");
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        System.out.println("mouseMoved");
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        System.out.println("mousePressed");
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        System.out.println("mouseReleased");
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
    }
    
    public void resetPQ() {
        this.Q = new double[this.maxRow];
        this.deltaP = (this.XMax - this.XMin) / this.maxCol;
        this.deltaQ = (this.YMax - this.YMin) / this.maxRow;
        this.P = this.XMin;
        this.Q[0] = this.YMax;
        for (int i = 1; i < this.maxRow; ++i) {
            this.Q[i] = this.Q[i - 1] - this.deltaQ;
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        System.exit(0);
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.getButtonGenerate()) {
            this.connEtoC1(actionEvent);
        }
        if (actionEvent.getSource() == this.getButtonHelp()) {
            this.connEtoC2(actionEvent);
        }
    }
    
    static {
        colors = new String[] { "FFFFFF", "0066CC", "0099CC", "66CCCC", "3333CC", "99CCFF", "00CCCC", "6699FF", "336699" };
        colors2 = new String[] { "006600", "009900", "00CC00", "0099CF", "000000", "000066", "000099", "006600", "CC0000", "999900", "99CC33", "990033", "A1DFF8", "FF9900", "669933", "DDDEEE", "ADF3FF", "DFFFEE", "FFFFFF", "FFFF00", "AD00FF", "AFFF00", "660066", "CCFF99" };
        colors3 = new String[] { "000000", "000066", "000099", "006600" };
        colors4 = new String[] { "000000", "FFFFFF" };
        colors5 = new String[] { "FF9900", "FFCC00", "FF6600" };
    }
}
