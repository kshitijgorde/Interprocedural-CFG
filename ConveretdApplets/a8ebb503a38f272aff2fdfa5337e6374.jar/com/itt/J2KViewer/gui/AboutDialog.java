// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Canvas;
import com.itt.J2KViewer.controller.PropertyManager;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import com.itt.J2KViewer.util.ViewerConst;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import com.itt.J2KViewer.controller.ViewCentral;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class AboutDialog extends JDialog implements ActionListener
{
    private JPanel m_mainPanel;
    private JPanel m_buttonPanel;
    private JButton m_okButton;
    private JButton detailsButton;
    private ImagePanel m_imagePanel;
    private JPanel m_infoPanel;
    private ViewCentral viewCentral;
    
    public AboutDialog(final JFrame locationRelativeTo, final boolean b, final ViewCentral viewCentral) {
        super(locationRelativeTo, b);
        this.m_mainPanel = null;
        this.m_buttonPanel = new JPanel();
        this.m_okButton = null;
        this.detailsButton = null;
        this.m_imagePanel = new ImagePanel("/IASViewerHelpAboutScreen.gif");
        this.viewCentral = viewCentral;
        (this.m_mainPanel = new JPanel()).setLayout(new BorderLayout());
        this.getContentPane().add(this.m_mainPanel);
        this.m_mainPanel.add(this.m_imagePanel, "Before");
        this.createInfoPanel();
        this.m_mainPanel.add(this.m_infoPanel, "After");
        (this.m_okButton = new JButton("OK")).addActionListener(this);
        this.m_buttonPanel.setLayout(new BoxLayout(this.m_buttonPanel, 2));
        (this.detailsButton = new JButton("Details...")).addActionListener(this);
        this.m_buttonPanel.add(this.detailsButton);
        this.m_buttonPanel.add(Box.createHorizontalGlue());
        this.m_buttonPanel.add(this.m_okButton);
        this.m_buttonPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        this.m_mainPanel.add(this.m_buttonPanel, "South");
        this.pack();
        this.setResizable(false);
        this.setTitle("Enterprise Viewer 1.7");
        this.setLocationRelativeTo(locationRelativeTo);
    }
    
    public void createInfoPanel() {
        (this.m_infoPanel = new JPanel()).setSize(266, this.m_imagePanel.getHeight());
        this.m_infoPanel.setBackground(Color.WHITE);
        this.m_infoPanel.setLayout(new BoxLayout(this.m_infoPanel, 3));
        final JLabel label = new JLabel("<html><B>Image Access Solutions</B><br> Enterprise Viewer 1.7<html>");
        label.setBorder(new EmptyBorder(7, 7, 0, 0));
        this.m_infoPanel.add(label);
        final JLabel label2 = new JLabel("<html><b>ITT Visual Information Solutions</b><br>4990 Pearl East Circle<br>Boulder, CO 80301<br>303-786-9900 <br>iasinfo@ittvis.com <br>http://www.ittvis.com<br><br>&copy;2010 ITT Visual Information Solutions");
        label2.setBorder(new EmptyBorder(0, 7, 7, 40));
        this.m_infoPanel.add(Box.createVerticalGlue());
        this.m_infoPanel.add(label2);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.m_okButton == actionEvent.getSource()) {
            this.setVisible(false);
        }
        if (this.detailsButton == actionEvent.getSource()) {
            this.showDetails();
        }
    }
    
    private void showDetails() {
        final JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Sans Serif", 0, 11));
        textArea.setEditable(false);
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        textArea.append("Allow Zoom \t\t " + propertyManager.isAllowZoom() + "\n");
        textArea.append("Change Quality \t " + propertyManager.isAllowChangeQuality() + "\n");
        textArea.append("Allow Pan \t\t " + propertyManager.isAllowPan() + "\n");
        textArea.append("Auto DRA On Load \t " + propertyManager.isUponLoadDoAutoDRA() + "\n");
        textArea.append("Default DRA Type \t " + ViewerConst.stretchTypes[propertyManager.getDefaultDRAType()] + "\n");
        textArea.append("DRA Ignore Value \t " + propertyManager.getIgnoreValueDRA() + "\n");
        textArea.append("Default Percent Stretch \t " + propertyManager.getIgnoreValueDRA() + "\n");
        textArea.append("Show Tool Bar \t " + propertyManager.isShowToolBar() + "\n");
        textArea.append("Show Context Menu \t " + propertyManager.isShowContextMenu() + "\n");
        textArea.append("Show Split Pane \t " + propertyManager.isShowSplitPane() + "\n");
        textArea.append("Show Context Menu \t " + propertyManager.isShowContextMenu() + "\n");
        textArea.append("Show Security Banners \t " + propertyManager.isShowSecurityBanner() + "\n");
        textArea.append("Security Segment \t " + propertyManager.getSecuritySegment() + "\n");
        textArea.append("Show Copyright Banner \t " + propertyManager.isShowCopyrightBanner() + "\n");
        textArea.append("Chipping Enabled\t " + propertyManager.isAllowChipping() + "\n");
        textArea.append("Chipping Service\t " + propertyManager.getChippingServiceType() + "\n");
        textArea.append("Chipping Service URL\t " + propertyManager.getChippingServiceURL() + "\n");
        textArea.append("ImageIO \t\t " + this.viewCentral.getImageStream().getStream().getInputStreamType() + "\n");
        JOptionPane.showMessageDialog(null, textArea, "Configuration Details", -1, null);
    }
    
    class ImagePanel extends Canvas
    {
        Image m_image;
        
        public ImagePanel(final String s) {
            this.m_image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(s));
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.m_image, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {}
            this.setSize(this.m_image.getWidth(null), this.m_image.getHeight(null));
        }
        
        public void paint(final Graphics graphics) {
            graphics.drawImage(this.m_image, 0, 0, this);
        }
    }
}
