// 
// Decompiled by Procyon v0.5.30
// 

package securitywarningapplet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Component;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.Window;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.GraphicsConfiguration;
import javax.swing.JApplet;

public class Main extends JApplet
{
    private boolean isShapingSupported;
    private boolean isTransparencySupported;
    private GraphicsConfiguration transparencyCapableGC;
    private UntrustedFrame untrustedFrame;
    private ButtonGroup decoratedButtonGroup;
    private JRadioButton decoratedRadioButton;
    private JPanel jPanel1;
    private JButton showHideButton;
    private JRadioButton undecoratedRadioButton;
    
    public void init() {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    Main.this.initComponents();
                    Main.this.isShapingSupported = AWTUtilitiesWrapper.isTranslucencySupported(AWTUtilitiesWrapper.PERPIXEL_TRANSPARENT);
                    Main.this.isTransparencySupported = AWTUtilitiesWrapper.isTranslucencySupported(AWTUtilitiesWrapper.PERPIXEL_TRANSLUCENT);
                    Main.this.transparencyCapableGC = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
                    if (!AWTUtilitiesWrapper.isTranslucencyCapable(Main.this.transparencyCapableGC)) {
                        Main.this.transparencyCapableGC = null;
                        final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                        final GraphicsDevice[] devices = env.getScreenDevices();
                        for (int i = 0; i < devices.length && Main.this.transparencyCapableGC == null; ++i) {
                            final GraphicsConfiguration[] configs = devices[i].getConfigurations();
                            for (int j = 0; j < configs.length && Main.this.transparencyCapableGC == null; ++j) {
                                if (AWTUtilitiesWrapper.isTranslucencyCapable(configs[j])) {
                                    Main.this.transparencyCapableGC = configs[j];
                                }
                            }
                        }
                        if (Main.this.transparencyCapableGC == null) {
                            Main.this.isTransparencySupported = false;
                        }
                    }
                    Main.this.enableControls(true);
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void enableControls(final boolean b) {
        this.undecoratedRadioButton.setEnabled(b && (this.isShapingSupported || this.isTransparencySupported));
        this.decoratedRadioButton.setEnabled(b);
    }
    
    private UntrustedFrame getUntrustedFrame() {
        if (this.untrustedFrame != null) {
            this.untrustedFrame.dispose();
        }
        if (this.isTransparencySupported && this.undecoratedRadioButton.isSelected()) {
            this.untrustedFrame = new UntrustedFrame(this.undecoratedRadioButton.isSelected(), this.transparencyCapableGC);
        }
        else {
            this.untrustedFrame = new UntrustedFrame(this.undecoratedRadioButton.isSelected());
        }
        if (this.undecoratedRadioButton.isSelected()) {
            if (this.isTransparencySupported) {
                AWTUtilitiesWrapper.setWindowOpaque(this.untrustedFrame, false);
            }
            if (this.isShapingSupported) {
                AWTUtilitiesWrapper.setWindowShape(this.untrustedFrame, new Ellipse2D.Float(0.0f, 0.0f, this.untrustedFrame.getWidth(), this.untrustedFrame.getHeight()));
            }
        }
        this.untrustedFrame.setLocationRelativeTo(null);
        this.untrustedFrame.addComponentListener(new ComponentAdapter() {
            public void componentHidden(final ComponentEvent e) {
                Main.this.hideUntrustedFrame();
            }
        });
        return this.untrustedFrame;
    }
    
    private void showUntrustedFrame() {
        this.showHideButton.setText("Hide untrusted window");
        this.enableControls(false);
        this.getUntrustedFrame().setVisible(true);
    }
    
    private void hideUntrustedFrame() {
        this.untrustedFrame.setVisible(false);
        this.enableControls(true);
        this.showHideButton.setText("Show untrusted window");
    }
    
    private void initComponents() {
        this.decoratedButtonGroup = new ButtonGroup();
        this.jPanel1 = new JPanel();
        this.decoratedRadioButton = new JRadioButton();
        this.undecoratedRadioButton = new JRadioButton();
        this.showHideButton = new JButton();
        this.jPanel1.setBorder(BorderFactory.createTitledBorder("Kind of untrusted window to create"));
        this.jPanel1.setLayout(new BoxLayout(this.jPanel1, 3));
        this.decoratedButtonGroup.add(this.decoratedRadioButton);
        this.decoratedRadioButton.setSelected(true);
        this.decoratedRadioButton.setText("Decorated (may be moved with the mouse)");
        this.jPanel1.add(this.decoratedRadioButton);
        this.decoratedButtonGroup.add(this.undecoratedRadioButton);
        this.undecoratedRadioButton.setText("Undecorated (may enable window effects)");
        this.jPanel1.add(this.undecoratedRadioButton);
        this.getContentPane().add(this.jPanel1, "Center");
        this.showHideButton.setText("Show Untrusted Window");
        this.showHideButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Main.this.showHideButtonActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.showHideButton, "Last");
    }
    
    private void showHideButtonActionPerformed(final ActionEvent evt) {
        if (this.untrustedFrame != null && this.untrustedFrame.isVisible()) {
            this.hideUntrustedFrame();
        }
        else {
            this.showUntrustedFrame();
        }
    }
}
