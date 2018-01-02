import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.AbstractButton;
import java.awt.Component;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ButtonsPanel extends JPanel
{
    private JToggleButton pen;
    private JToggleButton penThick;
    private JToggleButton spray;
    private JToggleButton rubber;
    private JToggleButton stamp1;
    private JToggleButton stamp2;
    private JToggleButton stamp3;
    private JToggleButton stamp4;
    private JToggleButton stamp5;
    private JToggleButton stamp6;
    private JToggleButton fill;
    private ButtonGroup buttonGroup;
    private JButton clear;
    private JButton undo;
    private JButton backG;
    private DrawPanel drawPanel;
    private MiscListener listener;
    private JPanel undoClearPanel;
    private JPanel drawButtonsPanel;
    private JPanel stampPanel;
    private static final Color myBlue;
    private URL URL_STRING;
    
    public ButtonsPanel(final DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
        this.URL_STRING = drawPanel.URL_STRING;
        this.listener = new MiscListener(this.drawPanel);
        this.setBackground(ButtonsPanel.myBlue);
        this.setPreferredSize(new Dimension(120, 550));
        this.setLayout(new BoxLayout(this, 1));
        this.setAlignmentX(0.0f);
        try {
            (this.pen = (JToggleButton)new DrawActionButton(new URL(this.URL_STRING + "images/scribble.gif"))).addActionListener((ActionListener)new ButtonListener(this, 1));
            this.pen.setToolTipText("Pencil");
            (this.penThick = (JToggleButton)new DrawActionButton(new URL(this.URL_STRING + "images/stroke.gif"))).addActionListener((ActionListener)new ButtonListener(this, 8));
            this.penThick.setToolTipText("Paint Brush");
            (this.spray = (JToggleButton)new DrawActionButton(new URL(this.URL_STRING + "images/spraypaint2.gif"))).addActionListener((ActionListener)new ButtonListener(this, 2));
            this.spray.setToolTipText("Spray Can");
            (this.rubber = (JToggleButton)new DrawActionButton(new URL(this.URL_STRING + "images/rubber.gif"))).addActionListener((ActionListener)new ButtonListener(this, 3));
            this.rubber.setToolTipText("Rubber");
            (this.fill = (JToggleButton)new DrawActionButton(new URL(this.URL_STRING + "images/fill.gif"))).addActionListener((ActionListener)new ButtonListener(this, 7));
            this.fill.setToolTipText("Fill An Area");
            (this.backG = new JButton(new ImageIcon(new URL(this.URL_STRING + "images/picIcon.gif")))).addActionListener((ActionListener)this.listener);
            this.backG.setActionCommand("Background");
            this.backG.setToolTipText("Create a random picture to colour in");
            this.backG.setBackground(ButtonsPanel.myBlue);
            (this.stamp1 = (JToggleButton)new DrawActionButton(new URL(this.URL_STRING + "images/treeIcon.gif"))).addActionListener((ActionListener)new ButtonListener(this, 4));
            (this.stamp2 = (JToggleButton)new DrawActionButton(new URL(this.URL_STRING + "images/carIcon.gif"))).addActionListener((ActionListener)new ButtonListener(this, 5));
            (this.stamp3 = (JToggleButton)new DrawActionButton(new URL(this.URL_STRING + "images/houseIcon.gif"))).addActionListener((ActionListener)new ButtonListener(this, 6));
            (this.stamp4 = (JToggleButton)new DrawActionButton(new URL(this.URL_STRING + "images/dogIcon.gif"))).addActionListener((ActionListener)new ButtonListener(this, 9));
            (this.stamp5 = (JToggleButton)new DrawActionButton(new URL(this.URL_STRING + "images/sunIcon.gif"))).addActionListener((ActionListener)new ButtonListener(this, 10));
            (this.stamp6 = (JToggleButton)new DrawActionButton(new URL(this.URL_STRING + "images/splatIcon.gif"))).addActionListener((ActionListener)new ButtonListener(this, 11));
        }
        catch (MalformedURLException ex) {}
        (this.drawButtonsPanel = new JPanel()).setLayout(new GridLayout(3, 2));
        this.drawButtonsPanel.setMaximumSize(new Dimension(110, 160));
        this.drawButtonsPanel.add(this.pen);
        this.drawButtonsPanel.add(this.penThick);
        this.drawButtonsPanel.add(this.spray);
        this.drawButtonsPanel.add(this.fill);
        this.drawButtonsPanel.add(this.rubber);
        this.drawButtonsPanel.add(this.backG);
        (this.stampPanel = new JPanel()).setLayout(new GridLayout(3, 2));
        this.stampPanel.setMaximumSize(new Dimension(110, 160));
        this.stampPanel.add(this.stamp1);
        this.stampPanel.add(this.stamp2);
        this.stampPanel.add(this.stamp3);
        this.stampPanel.add(this.stamp4);
        this.stampPanel.add(this.stamp5);
        this.stampPanel.add(this.stamp6);
        (this.buttonGroup = new ButtonGroup()).add(this.pen);
        this.buttonGroup.add(this.penThick);
        this.buttonGroup.add(this.spray);
        this.buttonGroup.add(this.fill);
        this.buttonGroup.add(this.rubber);
        this.buttonGroup.add(this.stamp1);
        this.buttonGroup.add(this.stamp2);
        this.buttonGroup.add(this.stamp3);
        this.buttonGroup.add(this.stamp4);
        this.buttonGroup.add(this.stamp5);
        this.buttonGroup.add(this.stamp6);
        (this.undoClearPanel = new JPanel()).setAlignmentY(0.5f);
        this.undoClearPanel.setPreferredSize(new Dimension(100, 70));
        this.undoClearPanel.setMinimumSize(new Dimension(70, 70));
        this.undoClearPanel.setMaximumSize(new Dimension(100, 70));
        this.undoClearPanel.setBackground(ButtonsPanel.myBlue);
        try {
            (this.clear = new JButton(new ImageIcon(new URL(this.URL_STRING + "images/clear.gif")))).setPreferredSize(new Dimension(45, 45));
            this.clear.addActionListener((ActionListener)this.listener);
            this.clear.setActionCommand("Clear");
            this.clear.setToolTipText("Start Again");
            this.clear.setBackground(ButtonsPanel.myBlue);
            (this.undo = new JButton(new ImageIcon(new URL(this.URL_STRING + "images/undo.gif")))).setPreferredSize(new Dimension(45, 45));
            this.undo.addActionListener((ActionListener)this.listener);
            this.undo.setActionCommand("Undo");
            this.undo.setToolTipText("Undo what you just did");
            this.undo.setBackground(ButtonsPanel.myBlue);
        }
        catch (MalformedURLException ex2) {}
        this.undoClearPanel.add(this.clear);
        this.undoClearPanel.add(this.undo);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(this.drawButtonsPanel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(this.stampPanel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(this.undoClearPanel);
    }
    
    public void setUndo(final String text) {
        this.undo.setText(text);
    }
    
    public void undoRedo() {
        if (this.undo.getText() == "Undo") {
            this.undo.setText("Redo");
        }
        else {
            this.undo.setText("Undo");
        }
    }
    
    static {
        ButtonsPanel.myBlue = new Color(192, 192, 255);
    }
    
    class ButtonListener implements ActionListener
    {
        int modeSet;
        
        public ButtonListener(final ButtonsPanel this$0, final int modeSet) {
            this.this$0 = this$0;
            this.modeSet = modeSet;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.this$0.drawPanel.setMode(this.modeSet);
        }
    }
}
