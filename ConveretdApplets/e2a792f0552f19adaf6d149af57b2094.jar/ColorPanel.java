import javax.swing.JToggleButton;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.net.MalformedURLException;
import javax.swing.AbstractButton;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorPanel extends JPanel
{
    private ColorButton redButton;
    private ColorButton orangeButton;
    private ColorButton purpleButton;
    private ColorButton blueButton;
    private ColorButton greenButton;
    private ColorButton yellowButton;
    private ColorButton whiteButton;
    private ColorButton blackButton;
    private ButtonGroup buttonGroup;
    private DrawPanel drawPanel;
    private JPanel quitPan;
    private MiscListener listener;
    private JLabel colorTextLabel;
    private JLabel colorLabel;
    private static final Color myOrange;
    private static final Color myBlue;
    private URL URL_STRING;
    
    public ColorPanel(final DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
        this.URL_STRING = drawPanel.URL_STRING;
        this.listener = new MiscListener(this.drawPanel);
        this.setPreferredSize(new Dimension(500, 50));
        this.setLayout(new BoxLayout(this, 0));
        this.setBackground(ColorPanel.myBlue);
        this.buttonGroup = new ButtonGroup();
        try {
            (this.redButton = new ColorButton(Color.red, new URL(this.URL_STRING + "images/red.gif"))).addActionListener((ActionListener)new ColorListener(this, Color.red));
            this.redButton.setSelected(true);
            this.buttonGroup.add(this.redButton);
            (this.orangeButton = new ColorButton(ColorPanel.myOrange, new URL(this.URL_STRING + "images/orange.gif"))).addActionListener((ActionListener)new ColorListener(this, ColorPanel.myOrange));
            this.buttonGroup.add(this.orangeButton);
            (this.greenButton = new ColorButton(Color.green, new URL(this.URL_STRING + "images/green.gif"))).addActionListener((ActionListener)new ColorListener(this, Color.green));
            this.buttonGroup.add(this.greenButton);
            (this.blueButton = new ColorButton(Color.blue, new URL(this.URL_STRING + "images/blue.gif"))).addActionListener((ActionListener)new ColorListener(this, Color.blue));
            this.buttonGroup.add(this.blueButton);
            (this.yellowButton = new ColorButton(Color.yellow, new URL(this.URL_STRING + "images/yellow.gif"))).addActionListener((ActionListener)new ColorListener(this, Color.yellow));
            this.buttonGroup.add(this.yellowButton);
            (this.blackButton = new ColorButton(Color.black, new URL(this.URL_STRING + "images/black.gif"))).addActionListener((ActionListener)new ColorListener(this, Color.black));
            this.buttonGroup.add(this.blackButton);
            (this.whiteButton = new ColorButton(Color.white, new URL(this.URL_STRING + "images/white.gif"))).addActionListener((ActionListener)new ColorListener(this, Color.white));
            this.buttonGroup.add(this.whiteButton);
            (this.purpleButton = new ColorButton(Color.magenta, new URL(this.URL_STRING + "images/magenta.gif"))).addActionListener((ActionListener)new ColorListener(this, Color.magenta));
            this.buttonGroup.add(this.purpleButton);
        }
        catch (MalformedURLException ex) {}
        try {
            this.colorTextLabel = new JLabel("Colour: ");
            (this.colorLabel = new JLabel()).setIcon(new ImageIcon(new URL(this.URL_STRING + "images/red.gif")));
        }
        catch (MalformedURLException ex2) {}
        this.add(Box.createHorizontalGlue());
        this.add(this.redButton);
        this.add(this.orangeButton);
        this.add(this.yellowButton);
        this.add(this.greenButton);
        this.add(this.blueButton);
        this.add(this.purpleButton);
        this.add(this.blackButton);
        this.add(this.whiteButton);
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        this.add(this.colorTextLabel);
        this.add(this.colorLabel);
        this.add(Box.createRigidArea(new Dimension(28, 0)));
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(Box.createRigidArea(new Dimension(20, 0)));
    }
    
    static {
        ColorPanel.myOrange = new Color(255, 192, 90);
        ColorPanel.myBlue = new Color(192, 192, 255);
    }
    
    class ColorListener implements ActionListener
    {
        Color c;
        
        public ColorListener(final ColorPanel this$0, final Color c) {
            this.this$0 = this$0;
            this.c = c;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.this$0.drawPanel.setColor(this.c);
            this.this$0.colorLabel.setIcon(((JToggleButton)actionEvent.getSource()).getIcon());
        }
    }
}
