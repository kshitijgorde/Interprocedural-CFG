// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

class CompounderPanel extends JPanel
{
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private BasicCompoundForm vBaseForm;
    private BasicCompoundForm vAlternateForm;
    private DiffCompoundForm vDiffForm;
    private JButton vBaseResetButton;
    private JButton vAlternateResetButton;
    private JButton vBaseSyncButton;
    private JButton vAlternateSyncButton;
    private String vMode;
    
    public CompounderPanel() {
        this.vMode = "Comparison";
        this.initialise();
    }
    
    private void initialise() {
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.setLayout(this.gbl);
        this.buildScreen();
        this.setMode("Basic");
        this.setBorder(BorderFactory.createEmptyBorder(11, 11, 11, 11));
        this.start();
    }
    
    public void setMode(final String s) {
        if (this.vMode.equals(s)) {
            return;
        }
        if (s.equals("Basic")) {
            this.destroyComparisonScreen();
            this.vMode = s;
        }
        if (s.equals("Comparison")) {
            this.buildComparisonScreen();
            this.vMode = s;
        }
    }
    
    public String getMode() {
        return this.vMode;
    }
    
    public void configureBaseScenario() {
        this.vBaseForm.configure();
    }
    
    public void configureAlternateScenario() {
        this.vAlternateForm.configure();
    }
    
    private void buildScreen() {
        this.vBaseForm = CompoundFormFactory.getFirstForm();
        this.vAlternateForm = CompoundFormFactory.getSecondForm();
        this.vDiffForm = CompoundFormFactory.getDiffForm(this.vBaseForm, this.vAlternateForm);
        (this.vBaseResetButton = new JButton("Reset Base")).setMnemonic(82);
        this.vBaseResetButton.setToolTipText(" Resets Base Scenario to its initial state. ");
        this.vBaseResetButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                CompounderPanel.this.vBaseForm.reset();
            }
        });
        this.vBaseForm.subscribeState(new ResetListener(this.vBaseResetButton));
        (this.vBaseSyncButton = new JButton("Sync to Alternate")).setMnemonic(83);
        this.vBaseSyncButton.setToolTipText(" Synchronises Base scenario to Alternate. ");
        this.vBaseSyncButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                CompounderPanel.this.vBaseForm.synchroniseWith(CompounderPanel.this.vAlternateForm);
            }
        });
        this.vDiffForm.subscribeState(new SyncListener(this.vBaseSyncButton));
        (this.vAlternateResetButton = new JButton("Reset Alternate")).setMnemonic(65);
        this.vAlternateResetButton.setToolTipText(" Resets Alternate Scenario to its initial state. ");
        this.vAlternateResetButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                CompounderPanel.this.vAlternateForm.reset();
            }
        });
        this.vAlternateForm.subscribeState(new ResetListener(this.vAlternateResetButton));
        (this.vAlternateSyncButton = new JButton("Sync to Base")).setMnemonic(66);
        this.vAlternateSyncButton.setToolTipText(" Synchronises Alternate scenario to Base. ");
        this.vAlternateSyncButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                CompounderPanel.this.vAlternateForm.synchroniseWith(CompounderPanel.this.vBaseForm);
            }
        });
        this.vDiffForm.subscribeState(new SyncListener(this.vAlternateSyncButton));
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.gbc.gridwidth = 2;
        this.gbc.gridheight = 1;
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.weighty = 1.0;
        this.gbc.anchor = 10;
        this.add(this.vBaseForm, this.gbc);
        this.gbc.insets = new Insets(0, 11, 0, 11);
        this.gbc.gridwidth = 2;
        this.gbc.gridheight = 1;
        this.gbc.gridx = 2;
        this.gbc.gridy = 0;
        this.gbc.weighty = 1.0;
        this.gbc.anchor = 10;
        this.add(this.vAlternateForm, this.gbc);
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.gridx = 4;
        this.gbc.gridy = 0;
        this.gbc.weighty = 1.0;
        this.gbc.anchor = 10;
        this.add(this.vDiffForm, this.gbc);
        this.gbc.insets = new Insets(17, 0, 0, 0);
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.gbc.ipadx = 12;
        this.gbc.ipady = 3;
        this.gbc.weightx = 1.0;
        this.gbc.weighty = 1.0;
        this.gbc.anchor = 10;
        this.add(this.vBaseResetButton, this.gbc);
        this.gbc.insets = new Insets(17, 0, 0, 0);
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.gridx = 1;
        this.gbc.gridy = 1;
        this.gbc.ipadx = 12;
        this.gbc.weightx = 1.0;
        this.gbc.weighty = 1.0;
        this.gbc.anchor = 10;
        this.add(this.vBaseSyncButton, this.gbc);
        this.gbc.insets = new Insets(17, 11, 0, 0);
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.gridx = 2;
        this.gbc.gridy = 1;
        this.gbc.ipadx = 12;
        this.gbc.ipady = 3;
        this.gbc.weightx = 1.0;
        this.gbc.weighty = 1.0;
        this.gbc.anchor = 10;
        this.add(this.vAlternateResetButton, this.gbc);
        this.gbc.insets = new Insets(17, 0, 0, 11);
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.gridx = 3;
        this.gbc.gridy = 1;
        this.gbc.ipadx = 12;
        this.gbc.weightx = 1.0;
        this.gbc.weighty = 1.0;
        this.gbc.anchor = 10;
        this.add(this.vAlternateSyncButton, this.gbc);
    }
    
    private void buildComparisonScreen() {
        this.vDiffForm.setVisible(true);
        this.vAlternateForm.setVisible(true);
        this.vAlternateResetButton.setVisible(true);
        this.vAlternateSyncButton.setVisible(true);
        this.repack();
    }
    
    private void destroyComparisonScreen() {
        this.vDiffForm.setVisible(false);
        this.vAlternateForm.setVisible(false);
        this.vAlternateResetButton.setVisible(false);
        this.vAlternateSyncButton.setVisible(false);
        this.repack();
    }
    
    private void start() {
        this.vBaseForm.start();
        this.vAlternateForm.start();
        this.vDiffForm.start();
    }
    
    private void repack() {
        final Window window = (Window)this.getTopLevelAncestor();
        if (window != null) {
            window.pack();
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final Dimension size = window.getSize();
            final Point location = window.getLocation();
            if (size.getWidth() + location.getX() > screenSize.getWidth()) {
                window.setLocation(screenSize.width / 2 - size.width / 2, (int)location.getY());
            }
        }
    }
}
