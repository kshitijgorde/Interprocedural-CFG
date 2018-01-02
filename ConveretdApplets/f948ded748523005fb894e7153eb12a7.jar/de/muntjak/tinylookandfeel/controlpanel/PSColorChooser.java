// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.Graphics;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.GraphicsEnvironment;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.ImageIcon;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JDialog;

public class PSColorChooser extends JDialog
{
    private Color inColor;
    private Color outColor;
    private static PSColorChooser myInstance;
    private ColorSelector colorSelector;
    private HueSelector hueSelector;
    private TwoColorField twoColorField;
    private NumericTextField redField;
    private NumericTextField greenField;
    private NumericTextField blueField;
    private NumericTextField satField;
    private NumericTextField briField;
    private NumericTextField hueField;
    private JButton ok;
    private boolean spinnerUpdate;
    private static Cursor cs_cursor;
    private static BufferedImage brightmask;
    private static GraphicsConfiguration conf;
    static /* synthetic */ Class class$de$muntjak$tinylookandfeel$controlpanel$PSColorChooser;
    
    private static Cursor loadCursor() {
        Cursor cursor = null;
        final Dimension bestCursorSize = Toolkit.getDefaultToolkit().getBestCursorSize(16, 16);
        if (bestCursorSize.width == 32) {
            cursor = Toolkit.getDefaultToolkit().createCustomCursor(loadImageIcon("/de/muntjak/tinylookandfeel/icons/cs32.gif").getImage(), new Point(15, 15), "cs_cursor");
        }
        else if (bestCursorSize.width == 16) {
            cursor = Toolkit.getDefaultToolkit().createCustomCursor(loadImageIcon("/de/muntjak/tinylookandfeel/icons/cs16.gif").getImage(), new Point(7, 7), "cs_cursor");
        }
        return cursor;
    }
    
    private static BufferedImage loadBrightmask() {
        final ImageIcon loadImageIcon = loadImageIcon("/de/muntjak/tinylookandfeel/icons/brightmask.png");
        final int iconWidth = loadImageIcon.getIconWidth();
        final int iconHeight = loadImageIcon.getIconHeight();
        final BufferedImage compatibleImage = PSColorChooser.conf.createCompatibleImage(iconWidth, iconHeight, 3);
        compatibleImage.getGraphics().drawImage(loadImageIcon.getImage(), 0, 0, iconWidth, iconHeight, 0, 0, iconWidth, iconHeight, null);
        return compatibleImage;
    }
    
    protected static ImageIcon loadImageIcon(final String s) {
        return new ImageIcon(((PSColorChooser.class$de$muntjak$tinylookandfeel$controlpanel$PSColorChooser == null) ? (PSColorChooser.class$de$muntjak$tinylookandfeel$controlpanel$PSColorChooser = class$("de.muntjak.tinylookandfeel.controlpanel.PSColorChooser")) : PSColorChooser.class$de$muntjak$tinylookandfeel$controlpanel$PSColorChooser).getResource(s));
    }
    
    private PSColorChooser(final Frame frame, final Color color) {
        super(frame, "PSColorChooser", true);
        this.spinnerUpdate = false;
        this.setDefaultCloseOperation(1);
        this.inColor = color;
        this.setupUI(frame, this.outColor = color);
    }
    
    public static Color showColorChooser(final Frame frame, final Color color) {
        if (PSColorChooser.myInstance == null) {
            PSColorChooser.myInstance = new PSColorChooser(frame, color);
        }
        PSColorChooser.myInstance.setColor(color);
        PSColorChooser.myInstance.setVisible(true);
        return PSColorChooser.myInstance.outColor;
    }
    
    public static void deleteInstance() {
        PSColorChooser.myInstance = null;
    }
    
    public void setColor(final Color color) {
        this.spinnerUpdate = true;
        this.inColor = color;
        this.outColor = this.inColor;
        final int hue = ColorRoutines.getHue(color);
        final int saturation = ColorRoutines.getSaturation(color);
        final int brightness = ColorRoutines.getBrightness(color);
        this.satField.setValue(saturation);
        this.briField.setValue(brightness);
        this.hueField.setValue(hue);
        this.redField.setValue(color.getRed());
        this.greenField.setValue(color.getGreen());
        this.blueField.setValue(color.getBlue());
        this.colorSelector.setColor(color);
        this.hueSelector.setHue(hue);
        this.twoColorField.setUpperColor(color);
        this.twoColorField.setLowerColor(color);
        this.spinnerUpdate = false;
    }
    
    private void colorChanged(final Color upperColor) {
        this.spinnerUpdate = true;
        final int hue = ColorRoutines.getHue(upperColor);
        final int saturation = ColorRoutines.getSaturation(upperColor);
        final int brightness = ColorRoutines.getBrightness(upperColor);
        this.satField.setValue(saturation);
        this.briField.setValue(brightness);
        this.hueField.setValue(hue);
        this.redField.setValue(upperColor.getRed());
        this.greenField.setValue(upperColor.getGreen());
        this.blueField.setValue(upperColor.getBlue());
        this.twoColorField.setUpperColor(upperColor);
        this.spinnerUpdate = false;
    }
    
    private void hueChanged(final int value) {
        this.spinnerUpdate = true;
        final int value2 = this.satField.getValue();
        final int value3 = this.briField.getValue();
        this.hueField.setValue(value);
        final Color hsbColor = Color.getHSBColor(value / 360.0f, value2 / 100.0f, value3 / 100.0f);
        this.redField.setValue(hsbColor.getRed());
        this.greenField.setValue(hsbColor.getGreen());
        this.blueField.setValue(hsbColor.getBlue());
        this.twoColorField.setUpperColor(hsbColor);
        this.colorSelector.setColor(hsbColor);
        this.spinnerUpdate = false;
    }
    
    private void setupUI(final Frame frame, final Color color) {
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel panel2 = new JPanel(new BorderLayout());
        final JPanel panel3 = new JPanel(new FlowLayout(0, 6, 7));
        final int hue = ColorRoutines.getHue(color);
        panel3.add(this.colorSelector = new ColorSelector(color));
        panel.add(panel3, "West");
        final JPanel panel4 = new JPanel(new FlowLayout(0, 6, 3));
        panel4.add(this.hueSelector = new HueSelector(hue));
        panel.add(panel4, "Center");
        panel2.add(panel, "Center");
        final JPanel panel5 = new JPanel(new BorderLayout());
        final JPanel panel6 = new JPanel(new FlowLayout(0, 6, 7));
        panel6.add(this.twoColorField = new TwoColorField(color));
        panel5.add(panel6, "North");
        final JPanel panel7 = new JPanel(new FlowLayout(0, 6, 7));
        panel7.add(this.createNumericTextFields());
        panel5.add(panel7, "Center");
        panel2.add(panel5, "East");
        this.getContentPane().add(panel2, "North");
        final JPanel panel8 = new JPanel(new FlowLayout(2, 12, 4));
        panel8.setBorder(new EtchedBorder());
        final JButton button = new JButton("Cancel");
        button.addActionListener(new CancelAction());
        panel8.add(button);
        final JButton defaultButton = new JButton("OK");
        defaultButton.addActionListener(new OKAction());
        this.getRootPane().setDefaultButton(defaultButton);
        panel8.add(defaultButton);
        this.getContentPane().add(panel8, "South");
        this.pack();
        this.getSize();
        this.setLocation(frame.getLocationOnScreen().x + (frame.getWidth() - this.getSize().width) / 2, frame.getLocationOnScreen().y + (frame.getHeight() - this.getSize().height) / 2);
    }
    
    private JPanel createNumericTextFields() {
        final RGBAction rgbAction = new RGBAction();
        final HSBAction hsbAction = new HSBAction();
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 0, 4, 2);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel.add(new JLabel("H:"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridx;
        (this.hueField = new NumericTextField(3, 0, 0, 360)).addActionListener(hsbAction);
        panel.add(this.hueField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        panel.add(new JLabel("S:"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridx;
        (this.satField = new NumericTextField(3, 0, 0, 100)).addActionListener(hsbAction);
        panel.add(this.satField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        ++gridBagConstraints5.gridy;
        panel.add(new JLabel("B:"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
        ++gridBagConstraints6.gridx;
        (this.briField = new NumericTextField(3, 0, 0, 100)).addActionListener(hsbAction);
        panel.add(this.briField, gridBagConstraints);
        gridBagConstraints.insets = new Insets(8, 0, 4, 2);
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
        ++gridBagConstraints7.gridy;
        panel.add(new JLabel("R:"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
        ++gridBagConstraints8.gridx;
        (this.redField = new NumericTextField(3, 0, 0, 255)).addActionListener(rgbAction);
        panel.add(this.redField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints9 = gridBagConstraints;
        ++gridBagConstraints9.gridy;
        gridBagConstraints.insets = new Insets(0, 0, 4, 2);
        panel.add(new JLabel("G:"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints10 = gridBagConstraints;
        ++gridBagConstraints10.gridx;
        (this.greenField = new NumericTextField(3, 0, 0, 255)).addActionListener(rgbAction);
        panel.add(this.greenField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints11 = gridBagConstraints;
        ++gridBagConstraints11.gridy;
        panel.add(new JLabel("B:"), gridBagConstraints);
        final GridBagConstraints gridBagConstraints12 = gridBagConstraints;
        ++gridBagConstraints12.gridx;
        (this.blueField = new NumericTextField(3, 0, 0, 255)).addActionListener(rgbAction);
        panel.add(this.blueField, gridBagConstraints);
        return panel;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        PSColorChooser.conf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        PSColorChooser.brightmask = loadBrightmask();
        PSColorChooser.cs_cursor = loadCursor();
    }
    
    class CancelAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            PSColorChooser.this.outColor = null;
            PSColorChooser.this.setVisible(false);
        }
    }
    
    class OKAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            PSColorChooser.this.setVisible(false);
        }
    }
    
    class HSBAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (PSColorChooser.this.spinnerUpdate) {
                return;
            }
            final int value = PSColorChooser.this.hueField.getValue();
            final Color hsbColor = Color.getHSBColor(value / 360.0f, PSColorChooser.this.satField.getValue() / 100.0f, PSColorChooser.this.briField.getValue() / 100.0f);
            PSColorChooser.this.twoColorField.setUpperColor(hsbColor);
            PSColorChooser.this.spinnerUpdate = true;
            PSColorChooser.this.redField.setValue(hsbColor.getRed());
            PSColorChooser.this.greenField.setValue(hsbColor.getGreen());
            PSColorChooser.this.blueField.setValue(hsbColor.getBlue());
            PSColorChooser.this.spinnerUpdate = false;
            PSColorChooser.this.hueSelector.setHue(value);
            PSColorChooser.this.colorSelector.setColor(hsbColor);
        }
    }
    
    class TwoColorField extends JPanel
    {
        private Dimension size;
        private Color upperColor;
        private Color lowerColor;
        
        TwoColorField(final Color color) {
            this.size = new Dimension(60, 68);
            this.setBorder(new LineBorder(Color.BLACK, 1));
            this.upperColor = color;
            this.lowerColor = color;
        }
        
        public Dimension getPreferredSize() {
            return this.size;
        }
        
        void setUpperColor(final Color upperColor) {
            this.upperColor = upperColor;
            PSColorChooser.this.outColor = upperColor;
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
    
    class HueSelector extends JPanel
    {
        private Color darkColor;
        private Dimension size;
        private float hue;
        private int arrowY;
        private Vector listeners;
        
        HueSelector(final int n) {
            this.darkColor = new Color(128, 128, 128);
            this.size = new Dimension(35, 266);
            this.hue = (float)(n / 360.0);
            this.addMouseListener(new Mousey());
            this.addMouseMotionListener(new MouseyDrag());
        }
        
        void setHue(final int n) {
            this.hue = (float)(n / 360.0);
            this.repaint(0L);
        }
        
        public Dimension getPreferredSize() {
            return this.size;
        }
        
        public void paint(final Graphics graphics) {
            graphics.setColor(Theme.backColor[Theme.style].getColor());
            graphics.fillRect(0, 0, 35, 266);
            this.drawArrows(graphics);
            graphics.setColor(this.darkColor);
            graphics.drawLine(6, 3, 27, 3);
            graphics.drawLine(6, 3, 6, 261);
            graphics.setColor(Color.WHITE);
            graphics.drawLine(6, 262, 28, 262);
            graphics.drawLine(28, 3, 28, 261);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(7, 4, 20, 257);
            final int n = 8;
            final int n2 = 26;
            final int n3 = 5;
            for (int i = 0; i < 256; ++i) {
                graphics.setColor(Color.getHSBColor((float)((255 - i) / 255.0), 1.0f, 1.0f));
                graphics.drawLine(n, i + n3, n2, i + n3);
            }
        }
        
        private void drawArrows(final Graphics graphics) {
            this.arrowY = 260 - (int)(this.hue * 255.0);
            graphics.setColor(Color.BLACK);
            graphics.drawLine(0, this.arrowY - 5, 0, this.arrowY + 5);
            graphics.drawLine(1, this.arrowY - 4, 1, this.arrowY - 4);
            graphics.drawLine(2, this.arrowY - 3, 2, this.arrowY - 3);
            graphics.drawLine(3, this.arrowY - 2, 3, this.arrowY - 2);
            graphics.drawLine(4, this.arrowY - 1, 4, this.arrowY - 1);
            graphics.drawLine(5, this.arrowY, 5, this.arrowY);
            graphics.drawLine(0, this.arrowY + 5, 0, this.arrowY + 5);
            graphics.drawLine(1, this.arrowY + 4, 1, this.arrowY + 4);
            graphics.drawLine(2, this.arrowY + 3, 2, this.arrowY + 3);
            graphics.drawLine(3, this.arrowY + 2, 3, this.arrowY + 2);
            graphics.drawLine(4, this.arrowY + 1, 4, this.arrowY + 1);
            graphics.drawLine(34, this.arrowY - 5, 34, this.arrowY + 5);
            graphics.drawLine(33, this.arrowY - 4, 33, this.arrowY - 4);
            graphics.drawLine(32, this.arrowY - 3, 32, this.arrowY - 3);
            graphics.drawLine(31, this.arrowY - 2, 31, this.arrowY - 2);
            graphics.drawLine(30, this.arrowY - 1, 30, this.arrowY - 1);
            graphics.drawLine(29, this.arrowY, 29, this.arrowY);
            graphics.drawLine(34, this.arrowY + 5, 34, this.arrowY + 5);
            graphics.drawLine(33, this.arrowY + 4, 33, this.arrowY + 4);
            graphics.drawLine(32, this.arrowY + 3, 32, this.arrowY + 3);
            graphics.drawLine(31, this.arrowY + 2, 31, this.arrowY + 2);
            graphics.drawLine(30, this.arrowY + 1, 30, this.arrowY + 1);
        }
        
        class MouseyDrag extends MouseMotionAdapter
        {
            public void mouseDragged(final MouseEvent mouseEvent) {
                int n = mouseEvent.getY() - 5;
                if (n < 0) {
                    n = 0;
                }
                else if (n > 255) {
                    n = 255;
                }
                HueSelector.this.hue = (float)((255 - n) / 255.0);
                HueSelector.this.repaint();
                PSColorChooser.this.hueChanged((int)(HueSelector.this.hue * 360.0));
            }
        }
        
        class Mousey extends MouseAdapter
        {
            public void mousePressed(final MouseEvent mouseEvent) {
                if (mouseEvent.getY() < 5 || mouseEvent.getY() > 260) {
                    return;
                }
                HueSelector.this.hue = (float)((255 - (mouseEvent.getY() - 5)) / 255.0);
                HueSelector.this.repaint();
                PSColorChooser.this.hueChanged((int)(HueSelector.this.hue * 360.0));
            }
        }
    }
    
    class ColorSelector extends JPanel
    {
        private Dimension size;
        private float hue;
        private int h;
        private int s;
        private int b;
        private int circleX;
        private int circleY;
        private Vector listeners;
        private Color theColor;
        private boolean mousePressed;
        
        ColorSelector(final Color theColor) {
            this.size = new Dimension(258, 258);
            this.mousePressed = false;
            this.h = ColorRoutines.getHue(theColor);
            this.s = ColorRoutines.getSaturation(theColor) * 255 / 100;
            this.b = ColorRoutines.getBrightness(theColor) * 255 / 100;
            this.hue = (float)(this.h / 360.0);
            this.theColor = theColor;
            this.setBorder(new LineBorder(Color.BLACK, 1));
            this.addMouseListener(new Mousey());
            this.addMouseMotionListener(new MouseyDrag());
            this.setCursor(PSColorChooser.cs_cursor);
        }
        
        void setColor(final Color color) {
            this.h = PSColorChooser.this.hueField.getValue();
            this.s = PSColorChooser.this.satField.getValue() * 255 / 100;
            this.b = PSColorChooser.this.briField.getValue() * 255 / 100;
            this.hue = (float)(this.h / 360.0);
            this.repaint(0L);
        }
        
        public Dimension getPreferredSize() {
            return this.size;
        }
        
        public void paint(final Graphics graphics) {
            super.paintBorder(graphics);
            if (this.mousePressed) {
                graphics.setClip(this.circleX, this.circleY, 11, 11);
            }
            for (int i = 0; i < 256; ++i) {
                graphics.setColor(Color.getHSBColor(this.hue, (float)(i / 255.0), 1.0f));
                graphics.drawLine(i + 1, 1, i + 1, 256);
            }
            graphics.drawImage(PSColorChooser.brightmask, 1, 1, this);
            this.circleX = this.s - 4;
            this.circleY = 255 - this.b - 4;
            if (this.mousePressed) {
                graphics.setClip(this.circleX, this.circleY, 11, 11);
            }
            if (this.b < 160) {
                graphics.setColor(Color.WHITE);
            }
            else {
                graphics.setColor(Color.BLACK);
            }
            graphics.drawLine(this.circleX + 3, this.circleY, this.circleX + 7, this.circleY);
            graphics.drawLine(this.circleX + 3, this.circleY + 10, this.circleX + 7, this.circleY + 10);
            graphics.drawLine(this.circleX, this.circleY + 3, this.circleX, this.circleY + 7);
            graphics.drawLine(this.circleX + 10, this.circleY + 3, this.circleX + 10, this.circleY + 7);
            graphics.drawLine(this.circleX + 2, this.circleY + 1, this.circleX + 2, this.circleY + 1);
            graphics.drawLine(this.circleX + 8, this.circleY + 1, this.circleX + 8, this.circleY + 1);
            graphics.drawLine(this.circleX + 1, this.circleY + 2, this.circleX + 1, this.circleY + 2);
            graphics.drawLine(this.circleX + 9, this.circleY + 2, this.circleX + 9, this.circleY + 2);
            graphics.drawLine(this.circleX + 1, this.circleY + 8, this.circleX + 1, this.circleY + 8);
            graphics.drawLine(this.circleX + 9, this.circleY + 8, this.circleX + 9, this.circleY + 8);
            graphics.drawLine(this.circleX + 2, this.circleY + 9, this.circleX + 2, this.circleY + 9);
            graphics.drawLine(this.circleX + 8, this.circleY + 9, this.circleX + 8, this.circleY + 9);
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
        
        class MouseyDrag extends MouseMotionAdapter
        {
            public void mouseDragged(final MouseEvent mouseEvent) {
                ColorSelector.this.s = mouseEvent.getX() - 1;
                if (ColorSelector.this.s < 0) {
                    ColorSelector.this.s = 0;
                }
                else if (ColorSelector.this.s > 255) {
                    ColorSelector.this.s = 255;
                }
                int n = mouseEvent.getY() - 1;
                if (n < 0) {
                    n = 0;
                }
                else if (n > 255) {
                    n = 255;
                }
                ColorSelector.this.b = 255 - n;
                ColorSelector.this.paint(ColorSelector.this.getGraphics());
                ColorSelector.this.theColor = Color.getHSBColor(ColorSelector.this.hue, (float)(ColorSelector.this.s / 255.0), (float)(ColorSelector.this.b / 255.0));
                PSColorChooser.this.colorChanged(ColorSelector.this.theColor);
            }
        }
        
        class Mousey extends MouseAdapter
        {
            public void mousePressed(final MouseEvent mouseEvent) {
                if (mouseEvent.getX() < 1 || mouseEvent.getX() > 256) {
                    return;
                }
                if (mouseEvent.getY() < 1 || mouseEvent.getY() > 256) {
                    return;
                }
                ColorSelector.this.s = mouseEvent.getX() - 1;
                if (ColorSelector.this.s < 0) {
                    ColorSelector.this.s = 0;
                }
                else if (ColorSelector.this.s > 255) {
                    ColorSelector.this.s = 255;
                }
                int n = mouseEvent.getY() - 1;
                if (n < 0) {
                    n = 0;
                }
                else if (n > 255) {
                    n = 255;
                }
                ColorSelector.this.mousePressed = true;
                ColorSelector.this.b = 255 - n;
                ColorSelector.this.paint(ColorSelector.this.getGraphics());
                ColorSelector.this.theColor = Color.getHSBColor(ColorSelector.this.hue, (float)(ColorSelector.this.s / 255.0), (float)(ColorSelector.this.b / 255.0));
                PSColorChooser.this.colorChanged(ColorSelector.this.theColor);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                ColorSelector.this.mousePressed = false;
            }
        }
    }
    
    class RGBAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (PSColorChooser.this.spinnerUpdate) {
                return;
            }
            final Color color = new Color(PSColorChooser.this.redField.getValue(), PSColorChooser.this.greenField.getValue(), PSColorChooser.this.blueField.getValue());
            PSColorChooser.this.twoColorField.setUpperColor(color);
            PSColorChooser.this.spinnerUpdate = true;
            final int hue = ColorRoutines.getHue(color);
            PSColorChooser.this.hueField.setValue(hue);
            PSColorChooser.this.satField.setValue(ColorRoutines.getSaturation(color));
            PSColorChooser.this.briField.setValue(ColorRoutines.getBrightness(color));
            PSColorChooser.this.spinnerUpdate = false;
            PSColorChooser.this.hueSelector.setHue(hue);
            PSColorChooser.this.colorSelector.setColor(color);
        }
    }
}
