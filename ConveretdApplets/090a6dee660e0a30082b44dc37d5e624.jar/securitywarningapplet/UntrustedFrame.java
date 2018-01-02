// 
// Decompiled by Procyon v0.5.30
// 

package securitywarningapplet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.geom.Point2D;
import java.awt.GraphicsConfiguration;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JFrame;

public class UntrustedFrame extends JFrame
{
    private JSlider alignmentXSlider;
    private JSlider alignmentYSlider;
    private JButton closeWindowButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JPanel jPanel10;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JSpinner pointXSpinner;
    private JSpinner pointYSpinner;
    
    private void init(final boolean undecorated) {
        if (undecorated) {
            this.setUndecorated(true);
        }
        this.initComponents();
        final boolean isSecurityWarningControlSupported = SecurityWarningWrapper.isSupported();
        this.alignmentXSlider.setEnabled(isSecurityWarningControlSupported);
        this.alignmentYSlider.setEnabled(isSecurityWarningControlSupported);
        this.pointXSpinner.setEnabled(isSecurityWarningControlSupported);
        this.pointYSpinner.setEnabled(isSecurityWarningControlSupported);
    }
    
    public UntrustedFrame(final boolean undecorated) {
        this.init(undecorated);
    }
    
    public UntrustedFrame(final boolean undecorated, final GraphicsConfiguration transparencyCapableGC) {
        super(transparencyCapableGC);
        this.init(undecorated);
        this.jPanel9.setDoubleBuffered(false);
        this.jPanel5.setDoubleBuffered(false);
        this.jPanel6.setDoubleBuffered(false);
        this.jPanel7.setDoubleBuffered(false);
        this.jPanel8.setDoubleBuffered(false);
    }
    
    private void updateSecurityWarningPosition() {
        if (!SecurityWarningWrapper.isSupported()) {
            return;
        }
        SecurityWarningWrapper.setPosition(this, new Point2D.Float((int)this.pointXSpinner.getValue(), (int)this.pointYSpinner.getValue()), this.alignmentXSlider.getValue() / 100.0f, this.alignmentYSlider.getValue() / 100.0f);
    }
    
    private void initComponents() {
        this.jPanel9 = new JPanel() {
            protected void paintComponent(final Graphics g) {
                if (g instanceof Graphics2D) {
                    final int R = 240;
                    final int G = 240;
                    final int B = 240;
                    final Paint p = new GradientPaint(0.0f, 0.0f, new Color(240, 240, 240, 0), this.getWidth(), this.getHeight(), new Color(240, 240, 240, 255), true);
                    final Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
                }
                else {
                    super.paintComponent(g);
                }
            }
        };
        this.jPanel5 = new JPanel();
        this.jPanel6 = new JPanel();
        this.jPanel7 = new JPanel();
        this.jPanel8 = new JPanel();
        this.jPanel1 = new JPanel();
        this.jPanel2 = new JPanel();
        this.jPanel4 = new JPanel();
        this.jLabel3 = new JLabel();
        this.jPanel10 = new JPanel();
        this.jPanel3 = new JPanel();
        this.jLabel1 = new JLabel();
        this.pointXSpinner = new JSpinner();
        this.jLabel2 = new JLabel();
        this.pointYSpinner = new JSpinner();
        this.closeWindowButton = new JButton();
        this.alignmentXSlider = new JSlider();
        this.alignmentYSlider = new JSlider();
        this.setDefaultCloseOperation(0);
        this.setTitle("Untrusted Frame");
        this.jPanel9.setOpaque(false);
        this.jPanel9.setLayout(new BorderLayout());
        this.jPanel5.setOpaque(false);
        this.jPanel5.setPreferredSize(new Dimension(558, 75));
        this.jPanel5.setLayout(null);
        this.jPanel9.add(this.jPanel5, "North");
        this.jPanel6.setOpaque(false);
        this.jPanel6.setPreferredSize(new Dimension(558, 75));
        this.jPanel6.setLayout(null);
        this.jPanel9.add(this.jPanel6, "South");
        this.jPanel7.setOpaque(false);
        this.jPanel7.setPreferredSize(new Dimension(75, 311));
        this.jPanel7.setLayout(null);
        this.jPanel9.add(this.jPanel7, "East");
        this.jPanel8.setOpaque(false);
        this.jPanel8.setPreferredSize(new Dimension(75, 311));
        this.jPanel8.setLayout(null);
        this.jPanel9.add(this.jPanel8, "West");
        this.jPanel1.setBorder(BorderFactory.createTitledBorder("Security Warning Position"));
        this.jPanel1.setLayout(new BorderLayout());
        this.jPanel2.setLayout(new BorderLayout());
        this.jPanel4.setMinimumSize(new Dimension(350, 146));
        this.jPanel4.setLayout(new BorderLayout());
        this.jLabel3.setHorizontalAlignment(0);
        this.jLabel3.setText("<html> Use the sliders on the left and the top to adjust the alignment of the coordinate system used to position the security warning. <br><br> Change the point coordinates in the fields below to modify the location of the security warning relative to the current coordinate system. If the controls are disabled, this means the Java version you are using does not support changing the position of the security warning. Please upgrade your Java.");
        this.jLabel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.jLabel3.setPreferredSize(new Dimension(292, 75));
        this.jPanel4.add(this.jLabel3, "Center");
        this.jPanel2.add(this.jPanel4, "Center");
        this.jPanel10.setLayout(new BorderLayout());
        this.jPanel3.setBorder(BorderFactory.createTitledBorder("Point"));
        this.jLabel1.setText("X: ");
        this.jPanel3.add(this.jLabel1);
        this.pointXSpinner.setModel(new SpinnerNumberModel(2, null, null, 1));
        this.pointXSpinner.setMinimumSize(new Dimension(64, 20));
        this.pointXSpinner.setPreferredSize(new Dimension(72, 20));
        this.pointXSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent evt) {
                UntrustedFrame.this.controlChange(evt);
            }
        });
        this.jPanel3.add(this.pointXSpinner);
        this.jLabel2.setText("   Y: ");
        this.jPanel3.add(this.jLabel2);
        this.pointYSpinner.setModel(new SpinnerNumberModel());
        this.pointYSpinner.setMinimumSize(new Dimension(64, 20));
        this.pointYSpinner.setPreferredSize(new Dimension(72, 20));
        this.pointYSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent evt) {
                UntrustedFrame.this.controlChange(evt);
            }
        });
        this.jPanel3.add(this.pointYSpinner);
        this.jPanel10.add(this.jPanel3, "Center");
        this.closeWindowButton.setText("Close this window");
        this.closeWindowButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                UntrustedFrame.this.closeWindowButtonActionPerformed(evt);
            }
        });
        this.jPanel10.add(this.closeWindowButton, "South");
        this.jPanel2.add(this.jPanel10, "South");
        this.jPanel1.add(this.jPanel2, "Center");
        this.alignmentXSlider.setValue(100);
        this.alignmentXSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent evt) {
                UntrustedFrame.this.controlChange(evt);
            }
        });
        this.jPanel1.add(this.alignmentXSlider, "First");
        this.alignmentYSlider.setOrientation(1);
        this.alignmentYSlider.setValue(0);
        this.alignmentYSlider.setInverted(true);
        this.alignmentYSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent evt) {
                UntrustedFrame.this.controlChange(evt);
            }
        });
        this.jPanel1.add(this.alignmentYSlider, "Before");
        this.jPanel9.add(this.jPanel1, "Center");
        this.getContentPane().add(this.jPanel9, "Center");
        this.pack();
    }
    
    private void controlChange(final ChangeEvent evt) {
        this.updateSecurityWarningPosition();
    }
    
    private void closeWindowButtonActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
}
