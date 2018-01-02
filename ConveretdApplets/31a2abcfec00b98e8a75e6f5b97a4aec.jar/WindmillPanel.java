import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.Scrollbar;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class WindmillPanel extends Panel
{
    Button turnLeft;
    Button turnRight;
    Button reset;
    Button startScramble;
    Button stopScramble;
    Choice cChoice;
    Button label;
    Scrollbar slider;
    DrawPanel drawPanel;
    WindmillControls controls;
    
    WindmillPanel() {
        this.turnLeft = new Button("<-----  Turn Left");
        this.turnRight = new Button("Turn Right  ----->");
        this.reset = new Button("Reset");
        this.startScramble = new Button("Start Scramble");
        this.stopScramble = new Button("Stop Scramble");
        this.cChoice = new Choice();
        this.label = new Button("Animation speed");
        this.slider = new Scrollbar(0);
        this.drawPanel = new DrawPanel(this);
        for (int n = 7, i = 1; i < n + 1; ++i) {
            this.cChoice.add(i + " disks");
        }
        this.cChoice.select(4);
        this.slider.setValues(3, 1, 0, 7);
        this.slider.setBlockIncrement(1);
        this.controls = new WindmillControls(this);
        this.cChoice.addItemListener(this.controls);
        this.turnLeft.addActionListener(this.controls);
        this.turnRight.addActionListener(this.controls);
        this.reset.addActionListener(this.controls);
        this.startScramble.addActionListener(this.controls);
        this.stopScramble.addActionListener(this.controls);
        this.slider.addAdjustmentListener(this.controls);
        this.turnLeft.addKeyListener(this.controls);
        this.turnRight.addKeyListener(this.controls);
        this.reset.addKeyListener(this.controls);
        this.startScramble.addKeyListener(this.controls);
        this.stopScramble.addKeyListener(this.controls);
        this.label.addKeyListener(this.controls);
        this.slider.addKeyListener(this.controls);
        this.drawPanel.addKeyListener(this.controls);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(2, 4));
        panel.add(this.startScramble);
        panel.add(this.turnLeft);
        panel.add(this.turnRight);
        panel.add(this.label);
        panel.add(this.stopScramble);
        panel.add(this.reset);
        panel.add(this.cChoice);
        panel.add(this.slider);
        this.setLayout(new BorderLayout());
        this.add("Center", this.drawPanel);
        this.add("South", panel);
    }
    
    public void repaint() {
        super.repaint();
        this.drawPanel.repaint();
    }
}
