import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorPicker extends Dialog implements MouseListener, ActionListener, WindowListener
{
    int[] colorValues;
    Color selectedColor;
    Component setComponent;
    static Color[] colors;
    ColorCanvas myCanvas;
    TextField rtext;
    TextField gtext;
    TextField btext;
    Label sample;
    Label samphex;
    
    public Color getSelectedColor() {
        return this.selectedColor;
    }
    
    static final Frame getFrameParent(final Component component) {
        if (component == null) {
            return null;
        }
        if (component instanceof Frame) {
            return (Frame)component;
        }
        return getFrameParent(component.getParent());
    }
    
    public ColorPicker(final Component component, final Component setComponent) {
        super(getFrameParent(component));
        this.colorValues = new int[] { 255, 204, 153, 102, 51, 0 };
        this.setComponent = setComponent;
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(3, 3));
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(2, 2));
        panel.setFont(new Font("SansSerif", 0, 10));
        panel.add("Center", this.myCanvas = new ColorCanvas(13));
        final Panel panel2 = new Panel();
        panel.add("North", new Label("Please select a Color"));
        final Label label = new Label("Red:", 2);
        this.rtext = new TextField(3);
        panel2.add(label);
        panel2.add(this.rtext);
        final Label label2 = new Label(" Green:", 2);
        this.gtext = new TextField(3);
        panel2.add(label2);
        panel2.add(this.gtext);
        final Label label3 = new Label(" Blue:", 2);
        this.btext = new TextField(3);
        panel2.add(label3);
        panel2.add(this.btext);
        final Label label4 = new Label(" Sample:", 2);
        this.sample = new Label("     ");
        panel2.add(label4);
        panel2.add(this.sample);
        this.sample.setBackground(Color.black);
        panel2.add(this.samphex = new Label("(000000)    "));
        panel.add("South", panel2);
        this.add("Center", panel);
        final Panel panel3 = new Panel();
        panel3.setLayout(new GridLayout(1, 5));
        final Label label5 = new Label("   ");
        final Button button = new Button("Okay");
        panel3.add(label5);
        panel3.add(button);
        button.setActionCommand("ok");
        final Label label6 = new Label("   ");
        final Button button2 = new Button("Cancel");
        button2.setActionCommand("cancel");
        panel3.add(label6);
        panel3.add(button2);
        panel3.add(new Label("   "));
        this.add("South", panel3);
        button.addActionListener(this);
        button2.addActionListener(this);
        this.myCanvas.addMouseListener(this);
        this.rtext.addActionListener(this);
        this.gtext.addActionListener(this);
        this.btext.addActionListener(this);
        this.addWindowListener(this);
        this.pack();
        this.setLocation(120, 120);
    }
    
    public String getHexString(final Color color) {
        final StringBuffer sb = new StringBuffer();
        sb.append("(");
        final int red = color.getRed();
        if (red < 16) {
            sb.append('0');
        }
        sb.append(Integer.toString(red, 16));
        final int green = color.getGreen();
        if (green < 16) {
            sb.append('0');
        }
        sb.append(Integer.toString(green, 16));
        final int blue = color.getBlue();
        if (blue < 16) {
            sb.append('0');
        }
        sb.append(Integer.toString(blue, 16));
        sb.append(")    ");
        return sb.toString();
    }
    
    public void show() {
        if (this.setComponent != null) {
            final Color background = this.setComponent.getBackground();
            this.sample.setBackground(background);
            this.rtext.setText("" + background.getRed());
            this.gtext.setText("" + background.getGreen());
            this.btext.setText("" + background.getBlue());
            this.samphex.setText(this.getHexString(background));
        }
        super.show();
    }
    
    public int getLimitedInt(final String s) {
        int int1 = 0;
        try {
            int1 = Integer.parseInt(s);
            if (int1 < 0) {
                int1 = 0;
            }
            if (int1 > 255) {
                int1 = 255;
            }
        }
        catch (NumberFormatException ex) {}
        return int1;
    }
    
    public void grabValuesAndSet() {
        final String trim = this.rtext.getText().trim();
        final String trim2 = this.gtext.getText().trim();
        final String trim3 = this.btext.getText().trim();
        final int limitedInt = this.getLimitedInt(trim);
        final int limitedInt2 = this.getLimitedInt(trim2);
        final int limitedInt3 = this.getLimitedInt(trim3);
        this.rtext.setText(limitedInt + "");
        this.gtext.setText(limitedInt2 + "");
        this.btext.setText(limitedInt3 + "");
        final Color background = new Color(limitedInt << 16 | limitedInt2 << 8 | limitedInt3);
        this.sample.setBackground(background);
        this.samphex.setText(this.getHexString(background));
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (((Component)actionEvent.getSource()) instanceof TextField) {
            this.grabValuesAndSet();
        }
        else if (actionCommand.equals("ok")) {
            this.grabValuesAndSet();
            this.selectedColor = this.sample.getBackground();
            if (this.setComponent != null) {
                this.setComponent.setBackground(this.selectedColor);
            }
            this.setVisible(false);
        }
        else if (actionCommand.equals("cancel")) {
            this.selectedColor = null;
            this.setVisible(false);
        }
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.selectedColor = null;
        this.setVisible(false);
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
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final int length = this.colorValues.length;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (mouseEvent.getComponent() != this.myCanvas) {
            return;
        }
        final int n = y / this.myCanvas.chgt;
        final int n2 = x / this.myCanvas.cwid;
        if (n < 0 || n >= length) {
            return;
        }
        if (n2 < 0 || n2 >= length * length) {
            return;
        }
        final int n3 = this.colorValues[n];
        final int n4 = this.colorValues[n2 / length];
        final int n5 = this.colorValues[n2 % length];
        this.rtext.setText("" + n3);
        this.gtext.setText("" + n4);
        this.btext.setText("" + n5);
        this.sample.setBackground(ColorPicker.colors[n * length * length + n2]);
        this.samphex.setText(this.getHexString(ColorPicker.colors[n * length * length + n2]));
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    static {
        ColorPicker.colors = null;
    }
    
    class ColorCanvas extends Canvas
    {
        int cwid;
        int chgt;
        Dimension prefsize;
        
        ColorCanvas(final int n) {
            final int length = ColorPicker.this.colorValues.length;
            this.setSize(this.prefsize = new Dimension(length * length * n, length * n));
            if (ColorPicker.colors == null) {
                ColorPicker.colors = new Color[length * length * length];
                int n2 = 0;
                for (int i = 0; i < length; ++i) {
                    for (int j = 0; j < length; ++j) {
                        for (int k = 0; k < length; ++k) {
                            ColorPicker.colors[n2] = new Color(ColorPicker.this.colorValues[i] << 16 | ColorPicker.this.colorValues[j] << 8 | ColorPicker.this.colorValues[k]);
                            ++n2;
                        }
                    }
                }
            }
        }
        
        public Dimension getPreferredSize() {
            return this.prefsize;
        }
        
        public void paint(final Graphics graphics) {
            final int length = ColorPicker.this.colorValues.length;
            final int width = this.getSize().width;
            final int height = this.getSize().height;
            this.cwid = width / (length * length);
            this.chgt = height / length;
            graphics.setColor(Color.lightGray);
            graphics.fillRect(0, 0, width, height);
            for (int i = 0; i < length; ++i) {
                for (int j = 0; j < length * length; ++j) {
                    graphics.setColor(ColorPicker.colors[i * length * length + j]);
                    graphics.fillRect(j * this.cwid + 2, i * this.chgt + 2, this.cwid - 2, this.chgt - 2);
                }
            }
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, width - 1, height - 1);
        }
    }
}
