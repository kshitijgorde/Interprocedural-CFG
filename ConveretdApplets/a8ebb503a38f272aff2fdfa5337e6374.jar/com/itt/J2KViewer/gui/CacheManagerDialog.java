// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import javax.swing.JOptionPane;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.Container;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.Frame;
import javax.swing.JFrame;
import com.itt.J2KViewer.controller.ViewCentral;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class CacheManagerDialog extends JDialog implements ActionListener, ComponentListener
{
    private static Font PANEL_TITLE_FONT;
    private JPanel m_mainPanel;
    private JPanel m_directoryPanel;
    private JPanel m_buttonPanel;
    private JButton m_okButton;
    private JTextField m_textFieldCacheDir;
    private JCheckBox m_checkBoxEnableCache;
    private JCheckBox m_checkBoxClearCacheOnAppExit;
    private ViewCentral viewCentral;
    
    public CacheManagerDialog(final JFrame locationRelativeTo, final boolean b, final ViewCentral viewCentral) {
        super(locationRelativeTo, b);
        this.m_mainPanel = new JPanel();
        this.m_directoryPanel = null;
        this.m_buttonPanel = new JPanel();
        this.m_okButton = new JButton("OK");
        this.m_textFieldCacheDir = null;
        this.m_checkBoxEnableCache = new JCheckBox("Enable Cache");
        this.m_checkBoxClearCacheOnAppExit = new JCheckBox("Clear Cache on Application Exit");
        this.viewCentral = null;
        this.viewCentral = viewCentral;
        this.loadCacheProps();
        final RiverLayout layout = new RiverLayout();
        this.m_buttonPanel.setLayout(layout);
        this.m_mainPanel.setLayout(new BorderLayout());
        this.getContentPane().add(this.m_mainPanel);
        this.m_mainPanel.add(this.getCenterPanel(), "Center");
        this.m_okButton.addActionListener(this);
        this.m_buttonPanel.add("right", this.m_okButton);
        this.m_mainPanel.add(this.m_buttonPanel, "South");
        this.setTitle("JPIP Cache Manager");
        this.pack();
        this.getLayeredPane().setMinimumSize(layout.preferredLayoutSize(this.getLayeredPane()));
        this.setLocationRelativeTo(locationRelativeTo);
        this.addComponentListener(this);
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        final Component component = componentEvent.getComponent();
        final Dimension size = component.getSize();
        final Dimension minimumSize = component.getMinimumSize();
        final Dimension size2 = new Dimension(size);
        boolean b = false;
        if (size.width < minimumSize.width) {
            b = true;
            size2.width = minimumSize.width;
        }
        if (size.height < minimumSize.height) {
            b = true;
            size2.height = minimumSize.height;
        }
        if (b) {
            component.setSize(size2);
        }
    }
    
    public Dimension getMinimumSize() {
        final Dimension preferredSize;
        final Dimension dimension = preferredSize = this.getContentPane().getPreferredSize();
        preferredSize.height += 40;
        return dimension;
    }
    
    public String getCacheDir() {
        if (this.viewCentral.getPropertyManager().getCachingIsEnabled()) {
            return this.viewCentral.getPropertyManager().getJpipCacheDirectory();
        }
        return null;
    }
    
    protected void loadCacheProps() {
    }
    
    protected void saveCacheProps() {
    }
    
    protected JPanel getCenterPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new RiverLayout());
        panel.add("left", this.getOptionsPanel());
        panel.add("br left", this.getDirectoryPanel());
        final JLabel label = new JLabel();
        label.setText("NOTE:");
        panel.add("br left", label);
        final JLabel label2 = new JLabel();
        label2.setText("  Changes to cache configuration do not apply to any open images. ");
        panel.add("br left", label2);
        final JLabel label3 = new JLabel();
        label3.setText("  Close and re-open the images for the changes to take effect. ");
        panel.add("br left", label3);
        return panel;
    }
    
    protected JPanel getDirectoryPanel() {
        final JPanel directoryPanel = new JPanel();
        directoryPanel.setLayout(new RiverLayout());
        final TitledBorder titledBorder = BorderFactory.createTitledBorder("Cache Directory");
        titledBorder.setTitleFont(CacheManagerDialog.PANEL_TITLE_FONT);
        directoryPanel.setBorder(new CompoundBorder(titledBorder, new EmptyBorder(2, 2, 2, 2)));
        (this.m_textFieldCacheDir = new JTextField(30)).setEditable(true);
        this.setTextFieldCacheDir();
        if (this.viewCentral.getPropertyManager().getCachingIsEnabled()) {
            this.m_textFieldCacheDir.setEnabled(true);
        }
        else {
            this.m_textFieldCacheDir.setEnabled(false);
        }
        directoryPanel.add("left", this.m_textFieldCacheDir);
        final JButton button = new JButton("...");
        directoryPanel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                CacheManagerDialog.this.showDirectoryChooserDialog();
            }
        });
        this.m_directoryPanel = directoryPanel;
        if (this.viewCentral.getPropertyManager().getCachingIsEnabled()) {
            this.EnableDirectoryPanel(true);
        }
        else {
            this.EnableDirectoryPanel(false);
        }
        return directoryPanel;
    }
    
    protected void EnableDirectoryPanel(final boolean enabled) {
        final Component[] components = this.m_directoryPanel.getComponents();
        for (int i = 0; i < components.length; ++i) {
            components[i].setEnabled(enabled);
        }
    }
    
    protected JPanel getOptionsPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new RiverLayout());
        final TitledBorder titledBorder = BorderFactory.createTitledBorder("Cache Options");
        titledBorder.setTitleFont(CacheManagerDialog.PANEL_TITLE_FONT);
        panel.setBorder(new CompoundBorder(titledBorder, new EmptyBorder(2, 2, 2, 2)));
        this.m_checkBoxEnableCache.setToolTipText("Enable Disk Cache for the JPIP Wavelets.");
        this.m_checkBoxClearCacheOnAppExit.setToolTipText("Turn on if desire cache to clear on application exit.");
        panel.add("left", this.m_checkBoxEnableCache);
        panel.add("left", this.m_checkBoxClearCacheOnAppExit);
        if (this.viewCentral.getPropertyManager().getCachingIsEnabled()) {
            this.m_checkBoxEnableCache.setSelected(true);
        }
        else {
            this.m_checkBoxEnableCache.setSelected(false);
        }
        if (this.viewCentral.getPropertyManager().getCacheClearOnAppExit()) {
            this.m_checkBoxClearCacheOnAppExit.setSelected(true);
        }
        else {
            this.m_checkBoxClearCacheOnAppExit.setSelected(false);
        }
        this.m_checkBoxEnableCache.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                CacheManagerDialog.this.recordCheckBoxEnableCacheChange();
            }
        });
        this.m_checkBoxClearCacheOnAppExit.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                CacheManagerDialog.this.recordCheckBoxClearCacheOnAppExitChange();
            }
        });
        return panel;
    }
    
    protected void recordCheckBoxEnableCacheChange() {
        if (this.m_checkBoxEnableCache.isSelected()) {
            this.viewCentral.getPropertyManager().setCachingIsEnabled(true);
            this.EnableDirectoryPanel(true);
        }
        else {
            this.viewCentral.getPropertyManager().setCachingIsEnabled(false);
            this.EnableDirectoryPanel(false);
        }
        this.saveCacheProps();
    }
    
    protected void recordCheckBoxClearCacheOnAppExitChange() {
        if (this.m_checkBoxClearCacheOnAppExit.isSelected()) {
            this.viewCentral.getPropertyManager().setCacheClearOnAppExit(true);
        }
        else {
            this.viewCentral.getPropertyManager().setCacheClearOnAppExit(false);
        }
        this.saveCacheProps();
    }
    
    protected void setTextFieldCacheDir() {
        this.m_textFieldCacheDir.setText(this.viewCentral.getPropertyManager().getJpipCacheDirectory());
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.m_okButton == actionEvent.getSource() && this.checkCacheDir()) {
            this.setVisible(false);
        }
    }
    
    protected void showDirectoryChooserDialog() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(1);
        fileChooser.setDialogTitle("Choose a directory for caching JPIP wavelets.");
        fileChooser.setCurrentDirectory(new File(this.viewCentral.getPropertyManager().getDefaultJpipCacheDirectory()));
        if (fileChooser.showOpenDialog(this) == 0) {
            final String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (new File(absolutePath).canWrite()) {
                this.viewCentral.getPropertyManager().setJpipCacheDirectory(absolutePath);
                this.saveCacheProps();
                this.setTextFieldCacheDir();
            }
            else {
                JOptionPane.showMessageDialog(this, "The directory does not exist or does not have write permissions. Choose another directory.", "Cache Directory Error", 0);
            }
        }
    }
    
    private boolean checkCacheDir() {
        if (new File(this.m_textFieldCacheDir.getText()).canWrite()) {
            this.viewCentral.getPropertyManager().setJpipCacheDirectory(this.m_textFieldCacheDir.getText());
            return true;
        }
        JOptionPane.showMessageDialog(this, "The directory does not exist or does not have write permissions. Choose another directory.", "Cache Directory Error", 0);
        return false;
    }
    
    static {
        CacheManagerDialog.PANEL_TITLE_FONT = new Font("SansSerif", 1, 12);
    }
}
