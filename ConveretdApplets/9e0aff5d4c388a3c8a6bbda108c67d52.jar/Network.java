import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Network extends JApplet implements ActionListener
{
    static Datagram dg;
    static ComputersPanel cp;
    static DataDisplay dd;
    private Timer timer;
    final String[] names;
    static /* synthetic */ Class class$Network;
    
    public Network() {
        this.names = new String[] { "128.9.2.34", "128.6.9.15", "128.33.12.1", "128.23.1.1", "128.1.2.23", "128.4.4.102", "128.108.110.4", "128.104.3.3" };
    }
    
    public void init() {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Color bgcolor;
        try {
            final int colorValue = Integer.decode(this.getParameter("backGroundColor"));
            bgcolor = new Color(colorValue);
        }
        catch (Exception e2) {
            bgcolor = Color.blue;
            e2.printStackTrace();
        }
        this.getContentPane().setBackground(bgcolor);
        final Image img = this.getImage(((Network.class$Network == null) ? (Network.class$Network = class$("Network")) : Network.class$Network).getResource("images/computer.gif"));
        Network.cp = new ComputersPanel(img, this.getContentPane().getGraphics(), this.names);
        Network.dg = new Datagram(this.getContentPane().getGraphics(), this.names, bgcolor);
        final NetworkControl control = new NetworkControl();
        final JLabel titleLabel = new JLabel("Networks");
        Network.dd = new DataDisplay();
        titleLabel.setHorizontalAlignment(0);
        titleLabel.setFont(new Font("Dialog", 1, 32));
        titleLabel.setForeground(Color.orange);
        Network.cp.setBackground(bgcolor);
        Network.dd.setBackground(bgcolor);
        control.setBackground(bgcolor);
        this.getContentPane().setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        this.getContentPane().add(titleLabel, c);
        c.gridy = 1;
        c.anchor = 11;
        this.getContentPane().add(Network.cp, c);
        c.gridy = 2;
        c.insets = new Insets(0, 0, 10, 0);
        this.getContentPane().add(Network.dd, c);
        c.insets = new Insets(0, 0, 20, 0);
        c.gridy = 3;
        Network.dg.setMode(0);
        this.getContentPane().add(Network.dg, c);
        c.gridy = 4;
        c.weighty = 1.0;
        c.insets = new Insets(0, 0, 0, 0);
        this.getContentPane().add(control, c);
        this.startTimer();
    }
    
    public synchronized void startTimer() {
        (this.timer = new Timer(20, this)).start();
    }
    
    public synchronized void stopTimer() {
        this.timer.stop();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.repaint();
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
