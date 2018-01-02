// 
// Decompiled by Procyon v0.5.30
// 

package weightapplet;

import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JApplet;

public class WeightApplet extends JApplet
{
    private boolean isStandalone;
    private MainPanel mpanel;
    private WeightApplet applet;
    private JButton startButton;
    private JLabel statusLabel;
    
    public WeightApplet() {
        this.isStandalone = false;
    }
    
    public static void main(final String[] array) {
    }
    
    @Override
    public void init() {
        try {
            (this.applet = this).initComponents();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void initComponents() {
        this.startButton = new JButton();
        this.statusLabel = new JLabel();
        this.getContentPane().setLayout(new GridBagLayout());
        this.startButton.setText("Start Simulator");
        this.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                WeightApplet.this.startButtonActionPerformed(actionEvent);
            }
        });
        this.getContentPane().add(this.startButton, new GridBagConstraints());
        this.statusLabel.setText("Status");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.getContentPane().add(this.statusLabel, gridBagConstraints);
    }
    
    private void startButtonActionPerformed(final ActionEvent actionEvent) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WeightApplet.this.getAppletContext();
                WeightApplet.this.statusLabel.setText("Loading");
                WeightApplet.this.update(WeightApplet.this.getGraphics());
                WeightApplet.this.mpanel = new MainPanel(WeightApplet.this.applet);
                if (WeightApplet.this.mpanel != null) {
                    WeightApplet.this.statusLabel.setText("Loaded");
                }
            }
        });
    }
}
