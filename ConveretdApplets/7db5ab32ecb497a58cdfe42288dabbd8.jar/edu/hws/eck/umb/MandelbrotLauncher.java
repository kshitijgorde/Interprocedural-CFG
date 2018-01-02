// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import edu.hws.eck.umb.util.I18n;
import javax.swing.JButton;
import javax.swing.JApplet;

public class MandelbrotLauncher extends JApplet
{
    private JButton launchButton;
    private MandelbrotFrame window;
    
    public void init() {
        (this.launchButton = new JButton(I18n.tr("mandelbrotLauncher.buttonName.LaunchMandelbrotViewer", new Object[0]))).addActionListener(new ButtonListener());
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.launchButton, "Center");
    }
    
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            MandelbrotLauncher.this.launchButton.setEnabled(false);
            if (MandelbrotLauncher.this.window == null) {
                MandelbrotLauncher.this.window = new MandelbrotFrame(true);
                MandelbrotLauncher.this.window.addWindowListener(new WindowAdapter() {
                    public void windowOpened(final WindowEvent windowEvent) {
                        MandelbrotLauncher.this.launchButton.setText(I18n.tr("mandelbrotLauncher.buttonName.CloseMandelbrotViewer", new Object[0]));
                        MandelbrotLauncher.this.launchButton.setEnabled(true);
                    }
                    
                    public void windowClosed(final WindowEvent windowEvent) {
                        MandelbrotLauncher.this.launchButton.setText(I18n.tr("mandelbrotLauncher.buttonName.LaunchMandelbrotViewer", new Object[0]));
                        MandelbrotLauncher.this.launchButton.setEnabled(true);
                        MandelbrotLauncher.this.window = null;
                    }
                });
                MandelbrotLauncher.this.window.setDefaultCloseOperation(2);
                MandelbrotLauncher.this.window.setVisible(true);
            }
            else {
                MandelbrotLauncher.this.window.dispose();
            }
        }
    }
}
