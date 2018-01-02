import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class HexColorTool extends Dialog implements ActionListener, WindowListener, KeyListener
{
    private static final char[] digits;
    private ColorField colorField;
    private Brightness brightField;
    private TextField hex;
    private TextField tf;
    private Frame cmf;
    private Button ok;
    private Canvas cc;
    private Button basicColors;
    private BasicColorTable bct;
    private boolean basicVisible;
    private Color savedColor;
    
    public HexColorTool(final Frame cmf, final TextField tf) {
        super(cmf, "HexColorTool", true);
        this.hex = new TextField("808080", 6);
        this.ok = new Button("OK");
        this.cc = new Canvas();
        this.basicColors = new Button("Basic Colors -->");
        this.setResizable(false);
        this.colorField = new ColorField(this);
        this.brightField = new Brightness(this);
        this.setLayout(null);
        this.tf = tf;
        this.cmf = cmf;
        this.setBackground(Color.lightGray);
        this.colorField.setBounds(20, 30, 256, 256);
        this.brightField.setBounds(280, 30, 30, 256);
        this.cc.setBounds(20, 300, 100, 25);
        this.hex.setBounds(130, 300, 50, 25);
        this.ok.setBounds(190, 300, 40, 25);
        this.basicColors.setBounds(240, 300, 100, 25);
        this.add(this.ok);
        this.add(this.basicColors);
        this.ok.addActionListener(this);
        this.basicColors.addActionListener(this);
        this.add(this.colorField);
        this.add(this.brightField);
        this.cc.setBackground(Color.gray);
        this.add(new Label("Hex Code:"));
        this.add(this.hex);
        this.add(this.cc);
        this.bct = new BasicColorTable(this);
        this.addWindowListener(this);
        this.hex.addKeyListener(this);
        this.hex.addActionListener(this);
    }
    
    public HexColorTool(final Frame frame, final Color savedColor) {
        this(frame, new TextField());
        this.savedColor = savedColor;
    }
    
    public void updateColor(final Color background, final boolean b, final boolean b2) {
        this.cc.setBackground(background);
        if (b2) {
            this.hex.setText(this.encodeToHex(background.getRGB() & 0xFFFFFF, 4));
        }
        this.cc.repaint();
        if (b) {
            this.brightField.refresh(background);
        }
        if (b) {
            this.brightField.repaint();
        }
        if (b2) {
            this.colorField.repaint();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.ok) {
            this.tf.setText(this.hex.getText());
            this.dispose();
        }
        else if (actionEvent.getSource() == this.hex) {
            try {
                this.updateColor(this.HexToColor(this.hex.getText()), true, false);
            }
            catch (Exception ex) {}
        }
        else if (actionEvent.getSource() == this.basicColors) {
            if (!this.basicVisible) {
                this.setSize(this.getSize().width + 270, this.getSize().height);
                this.bct.setBounds(this.getSize().width - 270, this.getSize().height / 2 - 125, 250, 250);
                this.add(this.bct);
                this.bct.validate();
                this.basicVisible = true;
            }
            else {
                this.setSize(this.getSize().width - 270, this.getSize().height);
                this.remove(this.bct);
                this.basicVisible = false;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.draw3DRect(19, 299, 101, 26, false);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    private String encodeToHex(int n, final int n2) {
        final char[] array = new char[32];
        int i = 32;
        final int n3 = (1 << n2) - 1;
        do {
            array[--i] = HexColorTool.digits[n & n3];
            n >>>= n2;
        } while (i > 0);
        final String s = new String(array);
        return s.substring(s.length() - 6, s.length()).toUpperCase();
    }
    
    private Color HexToColor(final String s) {
        return new Color(Integer.parseInt(s, 16));
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        try {
            this.updateColor(this.HexToColor(this.hex.getText()), true, false);
        }
        catch (Exception ex) {}
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    static {
        digits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    }
}
