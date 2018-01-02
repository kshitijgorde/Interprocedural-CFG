// 
// Decompiled by Procyon v0.5.30
// 

package fan;

import javax.swing.event.ChangeEvent;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import javax.swing.JApplet;

public class Fan extends JApplet
{
    static Spinner spinner;
    private FanPanel fanPanel;
    private BackgroundPanel backgroundPanel;
    private ControlPanel controlPanel;
    private Color black;
    private Color orange;
    
    public void init() {
        Fan.spinner = new Spinner();
        this.fanPanel = new FanPanel(this);
        this.backgroundPanel = new BackgroundPanel(this);
        this.controlPanel = new ControlPanel(this);
        this.black = Color.black;
        this.orange = Color.orange;
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(Color.orange);
        contentPane.setLayout(null);
        contentPane.add((Component)this.backgroundPanel);
        contentPane.add((Component)this.fanPanel);
        contentPane.add((Component)this.controlPanel);
        ((Component)this.backgroundPanel).setBounds(0, 0, 250, 250);
        ((Component)this.fanPanel).setBounds(0, 0, 250, 250);
        ((Component)this.controlPanel).setBounds(0, 250, 250, 25);
        ((Thread)Fan.spinner).start();
    }
    
    public void start() {
        Spinner.isRunning = true;
    }
    
    public void stop() {
        Spinner.isRunning = false;
    }
    
    public void destroy() {
        this.fanPanel = null;
        this.backgroundPanel = null;
        this.controlPanel = null;
        Fan.spinner = null;
        this.black = null;
        this.orange = null;
        System.gc();
    }
    
    private void repaintIt() {
        this.repaint();
    }
    
    class FanPanel extends JPanel
    {
        FanPanel(final Fan this$0) {
            this.this$0 = this$0;
        }
        
        public void paintComponent(final Graphics graphics) {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.fillArc(25, 25, 200, 200, Spinner.angle_1, 60);
            graphics2D.fillArc(25, 25, 200, 200, Spinner.angle_2, 60);
            graphics2D.fillArc(25, 25, 200, 200, Spinner.angle_3, 60);
            Fan.access$000(this.this$0);
        }
    }
    
    class BackgroundPanel extends JPanel
    {
        BackgroundPanel(final Fan this$0) {
            this.this$0 = this$0;
        }
        
        public void paintComponent(final Graphics graphics) {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setColor(this.this$0.black);
            graphics2D.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
            graphics2D.setColor(this.this$0.orange);
            graphics2D.fillOval(100, 100, 50, 50);
            graphics2D.setColor(this.this$0.black);
            graphics2D.fillOval(113, 113, 25, 25);
        }
    }
    
    class ControlPanel extends JPanel implements ChangeListener
    {
        private JLabel label;
        private JSlider slider;
        
        ControlPanel(final Fan this$0) {
            this.this$0 = this$0;
            this.label = new JLabel(" Speed:");
            (this.slider = new JSlider(1, 10, 2)).addChangeListener(this);
            this.add(this.label);
            this.add(this.slider);
            this.setLayout(null);
            this.label.setBounds(1, 1, 50, 20);
            this.slider.setBounds(45, 3, 204, 20);
        }
        
        public void stateChanged(final ChangeEvent changeEvent) {
            if (changeEvent.getSource() == this.slider) {
                Spinner.speed = 10 - this.slider.getValue() + 1;
                if (Spinner.speed == 10) {
                    Spinner.isSuspending = true;
                }
                else if (Spinner.isSuspending) {
                    synchronized (Fan.spinner) {
                        Fan.spinner.notify();
                        Spinner.isSuspending = false;
                    }
                }
            }
        }
    }
}
