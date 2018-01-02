// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import javax.swing.event.ChangeEvent;
import javax.swing.text.Document;
import javax.swing.text.BadLocationException;
import javax.swing.event.DocumentEvent;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JDialog;

public class HSBChooser extends JDialog
{
    private static HSBChooser myInstance;
    private static int hue;
    private static int sat;
    private static int bri;
    private boolean result;
    private ControlPanel.HSBField hsbField;
    private JSlider hueSlider;
    private JSlider satSlider;
    private JSlider briSlider;
    private JTextField hueField;
    private JTextField satField;
    private JTextField briField;
    private JCheckBox preserveGrey;
    private boolean keyInput;
    private boolean valueIsAdjusting;
    
    public HSBChooser(final Frame frame) {
        super(frame, "Hue/Saturation/Lightness", true);
        this.result = false;
        this.keyInput = false;
        this.valueIsAdjusting = false;
        this.setDefaultCloseOperation(1);
        this.setupUI(frame);
    }
    
    private void setupUI(final Frame frame) {
        final SliderAction sliderAction = new SliderAction();
        final JPanel panel = new JPanel(new BorderLayout(12, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        final JPanel panel2 = new JPanel(new GridLayout(3, 1, 0, 8));
        final JPanel panel3 = new JPanel(new FlowLayout(0, 2, 4));
        final JPanel panel4 = new JPanel(new BorderLayout(4, 0));
        final JPanel panel5 = new JPanel(new FlowLayout(0, 0, 0));
        panel4.add(new JLabel("Hue"), "North");
        (this.hueSlider = new JSlider(0, 360, HSBChooser.hue)).addChangeListener(sliderAction);
        this.hueSlider.setMajorTickSpacing(180);
        this.hueSlider.setPaintTicks(true);
        panel4.add(this.hueSlider, "Center");
        this.hueField = new JTextField("" + this.hueSlider.getValue(), 4);
        this.hueField.getDocument().addDocumentListener(new HueInputListener());
        this.hueField.addKeyListener(new ArrowKeyAction(this.hueField, 0, 360));
        this.hueField.setHorizontalAlignment(0);
        panel5.add(this.hueField);
        panel4.add(panel5, "East");
        panel2.add(panel4);
        final JPanel panel6 = new JPanel(new FlowLayout(0, 0, 0));
        final JPanel panel7 = new JPanel(new BorderLayout(4, 0));
        panel7.add(new JLabel("Saturation"), "North");
        (this.satSlider = new JSlider(0, 100, HSBChooser.sat)).addChangeListener(sliderAction);
        this.satSlider.setMajorTickSpacing(50);
        this.satSlider.setPaintTicks(true);
        panel7.add(this.satSlider, "Center");
        this.satField = new JTextField("" + this.satSlider.getValue(), 4);
        this.satField.getDocument().addDocumentListener(new SatInputListener());
        this.satField.addKeyListener(new ArrowKeyAction(this.satField, 0, 100));
        this.satField.setHorizontalAlignment(0);
        panel6.add(this.satField);
        panel7.add(panel6, "East");
        panel2.add(panel7);
        final JPanel panel8 = new JPanel(new FlowLayout(0, 0, 0));
        final JPanel panel9 = new JPanel(new BorderLayout(4, 0));
        panel9.add(new JLabel("Lightness"), "North");
        (this.briSlider = new JSlider(-100, 100, HSBChooser.bri)).addChangeListener(sliderAction);
        this.briSlider.setMajorTickSpacing(100);
        this.briSlider.setPaintTicks(true);
        panel9.add(this.briSlider, "Center");
        this.briField = new JTextField("" + this.briSlider.getValue(), 4);
        this.briField.getDocument().addDocumentListener(new BriInputListener());
        this.briField.addKeyListener(new ArrowKeyAction(this.briField, -100, 100));
        this.briField.setHorizontalAlignment(0);
        panel8.add(this.briField);
        panel9.add(panel8, "East");
        panel2.add(panel9);
        panel3.add(panel2);
        panel.add(panel3, "Center");
        final JPanel panel10 = new JPanel(new FlowLayout(0, 2, 6));
        (this.preserveGrey = new JCheckBox("Preserve grey values", true)).addActionListener(new PreserveAction());
        panel10.add(this.preserveGrey);
        panel.add(panel10, "South");
        this.getContentPane().add(panel, "Center");
        final JPanel panel11 = new JPanel(new FlowLayout(2, 8, 4));
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
    
    public static boolean showColorizeDialog(final Frame frame, final ControlPanel.HSBField hsbField) {
        if (HSBChooser.myInstance == null) {
            HSBChooser.myInstance = new HSBChooser(frame);
        }
        HSBChooser.myInstance.hsbField = hsbField;
        HSBChooser.myInstance.result = false;
        HSBChooser.myInstance.setParams(hsbField);
        HSBChooser.myInstance.setVisible(true);
        return HSBChooser.myInstance.result;
    }
    
    public static void deleteInstance() {
        HSBChooser.myInstance = null;
    }
    
    public static int getHue() {
        return HSBChooser.hue;
    }
    
    public static int getSaturation() {
        return HSBChooser.sat;
    }
    
    public static int getBrightness() {
        return HSBChooser.bri;
    }
    
    public static boolean isPreserveGrey() {
        return HSBChooser.myInstance.preserveGrey.isSelected();
    }
    
    private void setParams(final ControlPanel.HSBField hsbField) {
        HSBChooser.hue = hsbField.getHue();
        HSBChooser.sat = hsbField.getSaturation();
        HSBChooser.bri = hsbField.getBrightness();
        this.valueIsAdjusting = true;
        this.hueSlider.setValue(HSBChooser.hue);
        this.satSlider.setValue(HSBChooser.sat);
        this.briSlider.setValue(HSBChooser.bri);
        this.preserveGrey.setSelected(hsbField.isPreserveGrey());
        this.valueIsAdjusting = false;
        this.performAction();
    }
    
    private void performAction() {
        if (this.valueIsAdjusting) {
            return;
        }
        this.hsbField.setHue(HSBChooser.hue);
        this.hsbField.setSaturation(HSBChooser.sat);
        this.hsbField.setBrightness(HSBChooser.bri);
        this.hsbField.setPreserveGrey(this.preserveGrey.isSelected());
        this.hsbField.getAction().actionPerformed(new ActionEvent(this.hsbField, 1001, ""));
    }
    
    class CancelAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            HSBChooser.this.result = false;
            HSBChooser.this.setVisible(false);
        }
    }
    
    class OKAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            HSBChooser.this.result = true;
            HSBChooser.this.setVisible(false);
        }
    }
    
    class PreserveAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            HSBChooser.this.performAction();
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
                    HSBChooser.this.keyInput = true;
                    HSBChooser.bri = int1;
                    HSBChooser.this.briSlider.setValue(int1);
                    HSBChooser.this.keyInput = false;
                    HSBChooser.this.performAction();
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
                    HSBChooser.this.keyInput = true;
                    HSBChooser.sat = int1;
                    HSBChooser.this.satSlider.setValue(int1);
                    HSBChooser.this.keyInput = false;
                    HSBChooser.this.performAction();
                }
                catch (NumberFormatException ex) {}
            }
            catch (BadLocationException ex2) {}
        }
    }
    
    class HueInputListener implements DocumentListener
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
                    HSBChooser.this.keyInput = true;
                    HSBChooser.hue = int1;
                    HSBChooser.this.hueSlider.setValue(int1);
                    HSBChooser.this.keyInput = false;
                    HSBChooser.this.performAction();
                }
                catch (NumberFormatException ex) {}
            }
            catch (BadLocationException ex2) {}
        }
    }
    
    class SliderAction implements ChangeListener
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            if (!HSBChooser.this.keyInput) {
                if (changeEvent.getSource().equals(HSBChooser.this.hueSlider)) {
                    HSBChooser.hue = HSBChooser.this.hueSlider.getValue();
                    HSBChooser.this.hueField.setText("" + HSBChooser.hue);
                }
                else if (changeEvent.getSource().equals(HSBChooser.this.briSlider)) {
                    HSBChooser.bri = HSBChooser.this.briSlider.getValue();
                    HSBChooser.this.briField.setText("" + HSBChooser.bri);
                }
                else if (changeEvent.getSource().equals(HSBChooser.this.satSlider)) {
                    HSBChooser.sat = HSBChooser.this.satSlider.getValue();
                    HSBChooser.this.satField.setText("" + HSBChooser.sat);
                }
                HSBChooser.this.performAction();
            }
        }
    }
}
