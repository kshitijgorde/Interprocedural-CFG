import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class SigControls extends Panel implements ActionListener
{
    Button rect;
    Button bipulse;
    Button saw;
    Button tri;
    Button exp;
    Button noise;
    Button clear;
    SigPanel panel;
    
    public SigControls(final SigPanel panel) {
        this.rect = new Button("Rectangular Pulse");
        this.bipulse = new Button("Bipolar Pulse");
        this.saw = new Button("Sawtooth");
        this.tri = new Button("Triangle");
        this.exp = new Button("Exponential");
        this.noise = new Button("Noise");
        this.clear = new Button("Clear");
        this.panel = panel;
        this.setLayout(new GridLayout(8, 2, 5, 5));
        this.setBackground(Color.lightGray);
        this.add(this.rect);
        this.add(this.bipulse);
        this.add(this.saw);
        this.add(this.tri);
        this.add(this.exp);
        this.add(this.noise);
        this.add(new Label());
        this.add(this.clear);
        this.rect.addActionListener(this);
        this.bipulse.addActionListener(this);
        this.saw.addActionListener(this);
        this.tri.addActionListener(this);
        this.exp.addActionListener(this);
        this.noise.addActionListener(this);
        this.clear.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Exponential")) {
            this.panel.doDecay();
        }
        else if (actionEvent.getActionCommand().equals("Noise")) {
            this.panel.doNoise();
        }
        else if (actionEvent.getActionCommand().equals("Rectangular Pulse")) {
            this.panel.doPulse();
        }
        else if (actionEvent.getActionCommand().equals("Bipolar Pulse")) {
            this.panel.doDoublePulse();
        }
        else if (actionEvent.getActionCommand().equals("Triangle")) {
            this.panel.doTriangle();
        }
        else if (actionEvent.getActionCommand().equals("Sawtooth")) {
            this.panel.doSawtooth();
        }
        else if (actionEvent.getActionCommand().equals("Clear")) {
            this.panel.clear();
        }
    }
    
    public Insets insets() {
        return new Insets(3, 3, 3, 3);
    }
}
