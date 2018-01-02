// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import java.awt.Graphics;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import javax.swing.event.ChangeEvent;
import javax.swing.text.Document;
import javax.swing.text.BadLocationException;
import javax.swing.event.DocumentEvent;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JTextField;
import javax.swing.JSlider;
import java.awt.Color;
import javax.swing.JDialog;

public class SBChooser extends JDialog
{
    private static SBChooser myInstance;
    private static int sat;
    private static int bri;
    private Color reference;
    private Color outColor;
    private JSlider satSlider;
    private JSlider briSlider;
    private JTextField satField;
    private JTextField briField;
    private JTextField redField;
    private JTextField greenField;
    private JTextField blueField;
    private TwoColorField twoColorField;
    private ColorField referenceField;
    private boolean keyInput;
    private boolean valueIsAdjusting;
    
    public SBChooser(final Frame frame) {
        super(frame, "Saturation/Lightness", true);
        this.keyInput = false;
        this.valueIsAdjusting = false;
        this.setDefaultCloseOperation(1);
        this.setupUI(frame);
    }
    
    private void setupUI(final Frame frame) {
        final SliderAction sliderAction = new SliderAction();
        final JPanel panel = new JPanel(new BorderLayout(12, 0));
        final JPanel panel2 = new JPanel(new GridLayout(2, 1, 0, 8));
        final JPanel panel3 = new JPanel(new FlowLayout(0, 2, 4));
        final JPanel panel4 = new JPanel(new BorderLayout(4, 0));
        final JPanel panel5 = new JPanel(new FlowLayout(0, 0, 0));
        panel4.add(new JLabel("Saturation"), "North");
        (this.satSlider = new JSlider(-100, 100, SBChooser.sat)).addChangeListener(sliderAction);
        this.satSlider.setMajorTickSpacing(100);
        this.satSlider.setPaintTicks(true);
        panel4.add(this.satSlider, "Center");
        this.satField = new JTextField("" + this.satSlider.getValue(), 4);
        this.satField.getDocument().addDocumentListener(new SatInputListener());
        this.satField.addKeyListener(new ArrowKeyAction(this.satField, -100, 100));
        this.satField.setHorizontalAlignment(0);
        panel5.add(this.satField);
        panel4.add(panel5, "East");
        panel2.add(panel4);
        final JPanel panel6 = new JPanel(new FlowLayout(0, 0, 0));
        final JPanel panel7 = new JPanel(new BorderLayout(4, 0));
        panel7.add(new JLabel("Lightness"), "North");
        (this.briSlider = new JSlider(-100, 100, SBChooser.bri)).addChangeListener(sliderAction);
        this.briSlider.setMajorTickSpacing(100);
        this.briSlider.setPaintTicks(true);
        panel7.add(this.briSlider, "Center");
        this.briField = new JTextField("" + this.briSlider.getValue(), 4);
        this.briField.getDocument().addDocumentListener(new BriInputListener());
        this.briField.addKeyListener(new ArrowKeyAction(this.briField, -100, 100));
        this.briField.setHorizontalAlignment(0);
        panel6.add(this.briField);
        panel7.add(panel6, "East");
        panel2.add(panel7);
        panel3.add(panel2);
        panel.add(panel3, "Center");
        final JPanel panel8 = new JPanel(new BorderLayout(0, 6));
        final JPanel panel9 = new JPanel(new FlowLayout(0, 4, 4));
        panel8.add(this.twoColorField = new TwoColorField(this.reference), "North");
        panel8.add(this.referenceField = new ColorField(this.reference), "Center");
        panel9.add(panel8);
        panel.add(panel9, "East");
        final JPanel panel10 = new JPanel(new FlowLayout(0, 3, 8));
        panel10.add(new JLabel("R:"));
        (this.redField = new JTextField(4)).setHorizontalAlignment(0);
        this.redField.setEditable(false);
        panel10.add(this.redField);
        panel10.add(new JLabel("  G:"));
        (this.greenField = new JTextField(4)).setHorizontalAlignment(0);
        this.greenField.setEditable(false);
        panel10.add(this.greenField);
        panel10.add(new JLabel("  B:"));
        (this.blueField = new JTextField(4)).setHorizontalAlignment(0);
        this.blueField.setEditable(false);
        panel10.add(this.blueField);
        panel.add(panel10, "South");
        this.getContentPane().add(panel, "Center");
        final JPanel panel11 = new JPanel(new FlowLayout(2, 12, 4));
        panel11.setBorder(new EtchedBorder());
        final JButton button = new JButton("Cancel");
        button.addActionListener(new CancelAction());
        panel11.add(button);
        final JButton defaultButton = new JButton("OK");
        this.getRootPane().setDefaultButton(defaultButton);
        defaultButton.addActionListener(new OKAction());
        panel11.add(defaultButton);
        this.getContentPane().add(panel11, "South");
        this.pack();
        this.getSize();
        this.setLocation(frame.getLocationOnScreen().x + (frame.getWidth() - this.getSize().width) / 2, frame.getLocationOnScreen().y + (frame.getHeight() - this.getSize().height) / 2);
    }
    
    public static Color showSBChooser(final Frame frame, final Color color, final Color color2, final int n, final int n2) {
        if (SBChooser.myInstance == null) {
            SBChooser.myInstance = new SBChooser(frame);
        }
        SBChooser.myInstance.setColor(color, color2, n, n2);
        SBChooser.myInstance.setVisible(true);
        return SBChooser.myInstance.outColor;
    }
    
    public static Color showSBChooser(final Frame frame, final ControlPanel.SBField color) {
        if (SBChooser.myInstance == null) {
            SBChooser.myInstance = new SBChooser(frame);
        }
        SBChooser.myInstance.setColor(color);
        SBChooser.myInstance.setVisible(true);
        return SBChooser.myInstance.outColor;
    }
    
    public static void deleteInstance() {
        SBChooser.myInstance = null;
    }
    
    public void setColor(final ControlPanel.SBField sbField) {
        this.reference = sbField.getColorReference().getReferenceColor();
        this.outColor = sbField.getBackground();
        SBChooser.sat = sbField.getColorReference().getSaturation();
        SBChooser.bri = sbField.getColorReference().getBrightness();
        this.valueIsAdjusting = true;
        this.satSlider.setValue(SBChooser.sat);
        this.briSlider.setValue(SBChooser.bri);
        this.valueIsAdjusting = false;
        this.referenceField.setBackground(this.reference);
        this.twoColorField.setLowerColor(this.outColor);
        this.adjustColor();
    }
    
    public void setColor(final Color reference, final Color color, final int sat, final int bri) {
        this.reference = reference;
        this.outColor = color;
        SBChooser.sat = sat;
        SBChooser.bri = bri;
        this.valueIsAdjusting = true;
        this.satSlider.setValue(SBChooser.sat);
        this.briSlider.setValue(SBChooser.bri);
        this.valueIsAdjusting = false;
        this.referenceField.setBackground(this.reference);
        this.twoColorField.setLowerColor(color);
        this.adjustColor();
    }
    
    private void showColor(final int sat, final int bri) {
        SBChooser.sat = sat;
        SBChooser.bri = bri;
        this.adjustColor();
    }
    
    private void adjustColor() {
        this.outColor = getAdjustedColor(this.reference, SBChooser.sat, SBChooser.bri);
        this.twoColorField.setUpperColor(this.outColor);
    }
    
    public static Color getAdjustedColor(final Color color, final int n, final int n2) {
        Color color2 = color;
        if (n2 < 0) {
            color2 = ColorRoutines.darken(color, -n2);
        }
        else if (n2 > 0) {
            color2 = ColorRoutines.lighten(color, n2);
        }
        final Color maxSaturation = ColorRoutines.getMaxSaturation(color2, ColorRoutines.getHue(color));
        int n6;
        int n7;
        int n8;
        if (n >= 0) {
            final int n3 = color2.getRed() - maxSaturation.getRed();
            final int n4 = color2.getGreen() - maxSaturation.getGreen();
            final int n5 = color2.getBlue() - maxSaturation.getBlue();
            n6 = color2.getRed() - (int)Math.round(n3 * n / 100.0);
            n7 = color2.getGreen() - (int)Math.round(n4 * n / 100.0);
            n8 = color2.getBlue() - (int)Math.round(n5 * n / 100.0);
        }
        else {
            final float greyValue = ColorRoutines.getGreyValue(color2);
            final float n9 = color2.getRed() - greyValue;
            final float n10 = color2.getGreen() - greyValue;
            final float n11 = color2.getBlue() - greyValue;
            n6 = (int)Math.round(color2.getRed() + n9 * n / 100.0);
            n7 = (int)Math.round(color2.getGreen() + n10 * n / 100.0);
            n8 = (int)Math.round(color2.getBlue() + n11 * n / 100.0);
        }
        return new Color(n6, n7, n8);
    }
    
    public static int getSat() {
        return SBChooser.sat;
    }
    
    public static int getBri() {
        return SBChooser.bri;
    }
    
    class CancelAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            SBChooser.this.outColor = null;
            SBChooser.this.setVisible(false);
        }
    }
    
    class OKAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            SBChooser.this.setVisible(false);
        }
    }
    
    class ArrowKeyAction extends KeyAdapter implements ActionListener
    {
        private JTextField theField;
        private Timer keyTimer;
        private int step;
        private int min;
        private int max;
        
        ArrowKeyAction(final JTextField theField, final int min, final int max) {
            this.theField = theField;
            this.min = min;
            this.max = max;
            this.keyTimer = new Timer(20, this);
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 38) {
                this.step = 1;
                if (keyEvent.getModifiers() == 1) {
                    this.step = 10;
                }
                this.changeVal();
                this.keyTimer.setInitialDelay(300);
                this.keyTimer.start();
            }
            else if (keyEvent.getKeyCode() == 40) {
                this.step = -1;
                if (keyEvent.getModifiers() == 1) {
                    this.step = -10;
                }
                this.changeVal();
                this.keyTimer.setInitialDelay(300);
                this.keyTimer.start();
            }
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
            this.keyTimer.stop();
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.changeVal();
        }
        
        private void changeVal() {
            int n = Integer.parseInt(this.theField.getText()) + this.step;
            if (n > this.max) {
                n = this.max;
            }
            else if (n < this.min) {
                n = this.min;
            }
            this.theField.setText("" + n);
        }
    }
    
    class BriInputListener implements DocumentListener
    {
        public void changedUpdate(final DocumentEvent documentEvent) {
        }
        
        public void insertUpdate(final DocumentEvent documentEvent) {
            this.update(documentEvent);
        }
        
        public void removeUpdate(final DocumentEvent documentEvent) {
            this.update(documentEvent);
        }
        
        private void update(final DocumentEvent documentEvent) {
            final Document document = documentEvent.getDocument();
            try {
                final String text = document.getText(0, document.getLength());
                try {
                    final int int1 = Integer.parseInt(text);
                    SBChooser.this.keyInput = true;
                    SBChooser.this.briSlider.setValue(int1);
                    SBChooser.this.keyInput = false;
                }
                catch (NumberFormatException ex) {}
            }
            catch (BadLocationException ex2) {}
        }
    }
    
    class SatInputListener implements DocumentListener
    {
        public void changedUpdate(final DocumentEvent documentEvent) {
        }
        
        public void insertUpdate(final DocumentEvent documentEvent) {
            this.update(documentEvent);
        }
        
        public void removeUpdate(final DocumentEvent documentEvent) {
            this.update(documentEvent);
        }
        
        private void update(final DocumentEvent documentEvent) {
            final Document document = documentEvent.getDocument();
            try {
                final String text = document.getText(0, document.getLength());
                try {
                    final int int1 = Integer.parseInt(text);
                    SBChooser.this.keyInput = true;
                    SBChooser.this.satSlider.setValue(int1);
                    SBChooser.this.keyInput = false;
                }
                catch (NumberFormatException ex) {}
            }
            catch (BadLocationException ex2) {}
        }
    }
    
    class SliderAction implements ChangeListener
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            if (!SBChooser.this.keyInput) {
                if (changeEvent.getSource().equals(SBChooser.this.satSlider)) {
                    SBChooser.this.satField.setText("" + SBChooser.this.satSlider.getValue());
                }
                else {
                    SBChooser.this.briField.setText("" + SBChooser.this.briSlider.getValue());
                }
            }
            if (SBChooser.this.valueIsAdjusting) {
                return;
            }
            SBChooser.this.showColor(SBChooser.this.satSlider.getValue(), SBChooser.this.briSlider.getValue());
        }
    }
    
    class ColorField extends JPanel
    {
        private Dimension size;
        
        ColorField(final Color background) {
            this.size = new Dimension(60, 38);
            this.setBorder(new LineBorder(Color.GRAY, 1));
            this.setBackground(background);
        }
        
        public Dimension getPreferredSize() {
            return this.size;
        }
    }
    
    class TwoColorField extends JPanel
    {
        private Dimension size;
        private Color upperColor;
        private Color lowerColor;
        
        TwoColorField(final Color lowerColor) {
            this.size = new Dimension(60, 68);
            this.setBorder(new LineBorder(Color.BLACK, 1));
            this.upperColor = SBChooser.this.outColor;
            this.lowerColor = lowerColor;
        }
        
        public Dimension getPreferredSize() {
            return this.size;
        }
        
        void setUpperColor(final Color upperColor) {
            this.upperColor = upperColor;
            SBChooser.this.redField.setText("" + upperColor.getRed());
            SBChooser.this.greenField.setText("" + upperColor.getGreen());
            SBChooser.this.blueField.setText("" + upperColor.getBlue());
            this.repaint(0L);
        }
        
        void setLowerColor(final Color lowerColor) {
            this.lowerColor = lowerColor;
            this.repaint(0L);
        }
        
        public void paint(final Graphics graphics) {
            super.paintBorder(graphics);
            graphics.setColor(this.upperColor);
            graphics.fillRect(1, 1, 58, 33);
            graphics.setColor(this.lowerColor);
            graphics.fillRect(1, 34, 58, 33);
        }
    }
}
