// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import javax.swing.table.TableModel;
import java.util.Vector;
import anon.mixminion.mmrdescription.MMRDescription;
import gui.dialog.JAPDialog;
import logging.LogType;
import anon.mixminion.mmrdescription.PlainMMRListFetcher;
import anon.mixminion.mmrdescription.MMRListFetcher;
import anon.mixminion.mmrdescription.MMRList;
import anon.mixminion.mmrdescription.InfoServiceMMRListFetcher;
import anon.mixminion.PasswordManager;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.util.Dictionary;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.Icon;
import gui.GUIUtils;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import anon.util.JAPMessages;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;

final class JAPConfMixminion extends AbstractJAPConfModule implements ActionListener
{
    private JCheckBox m_cbxActive;
    private JTable m_tableRouters;
    private JSlider m_sliderPathLen;
    private JButton m_bttnFetchRouters;
    private JButton m_bttnChangePW;
    private JButton m_bttnResetKeyring;
    private JLabel m_labelAvailableRouters;
    private JLabel m_lblPathLen;
    private JLabel m_lblEMail;
    private JLabel m_lblKeyring;
    private JScrollPane m_scrollPane;
    private JPanel m_panelEMail;
    private JPanel m_panelPreferences;
    private TitledBorder m_borderPreferences;
    private TitledBorder m_borderEMail;
    private JTextField m_email;
    long m_lastUpdate;
    
    public JAPConfMixminion() {
        super(null);
    }
    
    public void recreateRootPanel() {
        final JPanel rootPanel = this.getRootPanel();
        rootPanel.removeAll();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = 18;
        rootPanel.setLayout(layout);
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        (this.m_cbxActive = new JCheckBox(JAPMessages.getString(JAPConfTor.MSG_ACTIVATE), true)).addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                JAPConfMixminion.this.m_labelAvailableRouters.setEnabled(JAPConfMixminion.this.m_cbxActive.isSelected());
                JAPConfMixminion.this.m_tableRouters.setEnabled(JAPConfMixminion.this.m_cbxActive.isSelected());
                JAPConfMixminion.this.m_bttnFetchRouters.setEnabled(JAPConfMixminion.this.m_cbxActive.isSelected());
                JAPConfMixminion.this.m_bttnChangePW.setEnabled(JAPConfMixminion.this.m_cbxActive.isSelected());
                JAPConfMixminion.this.m_bttnResetKeyring.setEnabled(JAPConfMixminion.this.m_cbxActive.isSelected());
                JAPConfMixminion.this.m_lblKeyring.setEnabled(JAPConfMixminion.this.m_cbxActive.isSelected());
                JAPConfMixminion.this.m_email.setEnabled(JAPConfMixminion.this.m_cbxActive.isSelected());
                JAPConfMixminion.this.m_sliderPathLen.setEnabled(JAPConfMixminion.this.m_cbxActive.isSelected());
                JAPConfMixminion.this.m_lblPathLen.setEnabled(JAPConfMixminion.this.m_cbxActive.isSelected());
                JAPConfMixminion.this.m_lblEMail.setEnabled(JAPConfMixminion.this.m_cbxActive.isSelected());
                JAPConfMixminion.this.m_scrollPane.setEnabled(JAPConfMixminion.this.m_cbxActive.isSelected());
                JAPConfMixminion.this.m_borderEMail = new TitledBorder(JAPConfMixminion.this.m_borderEMail.getTitle());
                JAPConfMixminion.this.m_borderPreferences = new TitledBorder(JAPConfMixminion.this.m_borderPreferences.getTitle());
                if (JAPConfMixminion.this.m_cbxActive.isSelected()) {
                    JAPConfMixminion.this.m_bttnFetchRouters.setDisabledIcon(GUIUtils.loadImageIcon("reloaddisabled_anim.gif", true, false));
                }
                else {
                    JAPConfMixminion.this.m_borderEMail.setTitleColor(Color.gray);
                    JAPConfMixminion.this.m_borderPreferences.setTitleColor(Color.gray);
                    JAPConfMixminion.this.m_bttnFetchRouters.setDisabledIcon(GUIUtils.loadImageIcon("reloadrollover.gif", true, false));
                }
                JAPConfMixminion.this.m_panelEMail.setBorder(JAPConfMixminion.this.m_borderEMail);
                JAPConfMixminion.this.m_panelPreferences.setBorder(JAPConfMixminion.this.m_borderPreferences);
                final Dictionary labelTable = JAPConfMixminion.this.m_sliderPathLen.getLabelTable();
                for (int i = 2; i <= 10; ++i) {
                    labelTable.get(new Integer(i)).setEnabled(JAPConfMixminion.this.m_sliderPathLen.isEnabled());
                }
            }
        });
        rootPanel.add(this.m_cbxActive, gridBagConstraints);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final JPanel panel = new JPanel(gridBagLayout);
        this.m_labelAvailableRouters = new JLabel(JAPMessages.getString("mixminionBorderAvailableRouters") + ":");
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 0.0;
        panel.add(this.m_labelAvailableRouters, gridBagConstraints2);
        final DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn(JAPMessages.getString("mixminionRouterName"));
        defaultTableModel.addColumn(JAPMessages.getString("mixminionRouterAdr"));
        defaultTableModel.addColumn(JAPMessages.getString("mixminionRouterPort"));
        defaultTableModel.addColumn(JAPMessages.getString("mixminionRouterSoftware"));
        defaultTableModel.setNumRows(10);
        (this.m_tableRouters = new MyJTable(defaultTableModel)).setPreferredScrollableViewportSize(new Dimension(70, this.m_tableRouters.getRowHeight() * 5));
        this.m_tableRouters.setCellSelectionEnabled(false);
        this.m_tableRouters.setColumnSelectionAllowed(false);
        this.m_tableRouters.setRowSelectionAllowed(true);
        this.m_tableRouters.setSelectionMode(0);
        (this.m_scrollPane = new JScrollPane(this.m_tableRouters)).setAutoscrolls(true);
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.gridwidth = 2;
        panel.add(this.m_scrollPane, gridBagConstraints2);
        (this.m_bttnFetchRouters = new JButton(JAPMessages.getString("mixminionBttnFetchRouters"))).setIcon(GUIUtils.loadImageIcon("reload.gif", true, false));
        this.m_bttnFetchRouters.setDisabledIcon(GUIUtils.loadImageIcon("reloaddisabled_anim.gif", true, false));
        this.m_bttnFetchRouters.setPressedIcon(GUIUtils.loadImageIcon("reloadrollover.gif", true, false));
        this.m_bttnFetchRouters.setActionCommand("fetchRouters");
        this.m_bttnFetchRouters.addActionListener(this);
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.anchor = 13;
        gridBagConstraints2.insets = new Insets(5, 5, 5, 0);
        panel.add(this.m_bttnFetchRouters, gridBagConstraints2);
        rootPanel.add(panel, gridBagConstraints);
        final JPanel panelPreferences = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.anchor = 18;
        gridBagConstraints3.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints3.fill = 0;
        panelPreferences.add(this.m_lblPathLen = new JLabel(JAPMessages.getString("mixminionPrefPathLen")), gridBagConstraints3);
        (this.m_sliderPathLen = new JSlider()).setPaintLabels(true);
        this.m_sliderPathLen.setPaintTicks(true);
        this.m_sliderPathLen.setMajorTickSpacing(1);
        this.m_sliderPathLen.setSnapToTicks(true);
        this.m_sliderPathLen.setMinimum(2);
        this.m_sliderPathLen.setMaximum(10);
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.fill = 2;
        panelPreferences.add(this.m_sliderPathLen, gridBagConstraints3);
        panelPreferences.setBorder(this.m_borderPreferences = new TitledBorder(JAPMessages.getString("mixminionBorderPreferences")));
        this.m_panelPreferences = panelPreferences;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        rootPanel.add(panelPreferences, gridBagConstraints);
        final JPanel panelEMail = new JPanel(new GridBagLayout());
        this.m_lblEMail = new JLabel(JAPMessages.getString("mixminionEMail"));
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        panelEMail.add(this.m_lblEMail, gridBagConstraints3);
        this.m_email = new JTextField();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridwidth = 0;
        panelEMail.add(this.m_email, gridBagConstraints3);
        this.m_lblKeyring = new JLabel(JAPMessages.getString("mixminionKeyring") + ":");
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.gridwidth = -1;
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        panelEMail.add(this.m_lblKeyring, gridBagConstraints3);
        (this.m_bttnChangePW = new JButton(JAPMessages.getString("mixminionBttnChangePassword"))).setActionCommand("changePW");
        this.m_bttnChangePW.addActionListener(this);
        gridBagConstraints3.gridx = 1;
        panelEMail.add(this.m_bttnChangePW, gridBagConstraints3);
        (this.m_bttnResetKeyring = new JButton(JAPMessages.getString("mixminionBttnResetKeyring"))).setActionCommand("resetKeyring");
        this.m_bttnResetKeyring.addActionListener(this);
        gridBagConstraints3.gridx = 2;
        panelEMail.add(this.m_bttnResetKeyring, gridBagConstraints3);
        panelEMail.setBorder(this.m_borderEMail = new TitledBorder(JAPMessages.getString("mixminionEMailSettings")));
        this.m_panelEMail = panelEMail;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        rootPanel.add(panelEMail, gridBagConstraints);
        this.m_lastUpdate = 0L;
    }
    
    public String getTabTitle() {
        return "Mixminion";
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("enableMixminion")) {
            this.updateValues(false);
        }
        else if (actionEvent.getActionCommand().equals("fetchRouters")) {
            this.fetchRoutersAsync(true);
        }
        else if (actionEvent.getActionCommand().equals("changePW")) {
            new PasswordManager().changePassword();
            this.m_bttnResetKeyring.setEnabled(true);
        }
        else if (actionEvent.getActionCommand().equals("resetKeyring")) {
            JAPController.resetMixminionPassword();
            this.m_bttnResetKeyring.setEnabled(false);
        }
    }
    
    protected boolean onOkPressed() {
        JAPModel.getInstance().setMixminionRouteLen(this.m_sliderPathLen.getValue());
        JAPModel.getInstance().setMixminionMyEMail(this.m_email.getText());
        JAPModel.getInstance().setMixMinionActivated(this.m_cbxActive.isSelected());
        return true;
    }
    
    protected void onUpdateValues() {
        this.m_sliderPathLen.setValue(JAPModel.getMixminionRouteLen());
        this.m_email.setText(JAPModel.getMixminionMyEMail());
        this.m_cbxActive.setSelected(JAPModel.getInstance().isMixMinionActivated());
    }
    
    public String getHelpContext() {
        return "services_mixminion";
    }
    
    private void fetchRoutersAsync(final boolean b) {
        this.m_bttnFetchRouters.setEnabled(false);
        new Thread(new Runnable() {
            public void run() {
                MMRList list = new MMRList(new InfoServiceMMRListFetcher());
                if (!list.updateList()) {
                    list = new MMRList(new PlainMMRListFetcher());
                }
                if (!list.updateList()) {
                    if (b) {
                        JAPDialog.showErrorDialog(JAPConfMixminion.this.getRootPanel(), JAPMessages.getString("mixminionErrorFetchRouters"), LogType.MISC);
                    }
                    JAPConfMixminion.this.m_bttnFetchRouters.setEnabled(true);
                    return;
                }
                JAPConfMixminion.this.m_lastUpdate = System.currentTimeMillis();
                final DefaultTableModel defaultTableModel = (DefaultTableModel)JAPConfMixminion.this.m_tableRouters.getModel();
                final Vector list2 = list.getList();
                defaultTableModel.setNumRows(list2.size());
                for (int i = 0; i < list2.size(); ++i) {
                    final MMRDescription mmrDescription = list2.elementAt(i);
                    JAPConfMixminion.this.m_tableRouters.setValueAt(mmrDescription.getName(), i, 0);
                    JAPConfMixminion.this.m_tableRouters.setValueAt(mmrDescription.getAddress(), i, 1);
                    JAPConfMixminion.this.m_tableRouters.setValueAt(new Integer(mmrDescription.getPort()), i, 2);
                    JAPConfMixminion.this.m_tableRouters.setValueAt(mmrDescription.getSoftwareVersion(), i, 3);
                    JAPConfMixminion.this.m_tableRouters.invalidate();
                }
                JAPConfMixminion.this.m_labelAvailableRouters.setText(JAPMessages.getString("mixminionBorderAvailableRouters:"));
                JAPConfMixminion.this.m_labelAvailableRouters.invalidate();
                JAPConfMixminion.this.getRootPanel().validate();
                JAPConfMixminion.this.m_bttnFetchRouters.setEnabled(true);
            }
        }).start();
    }
    
    public void onResetToDefaultsPressed() {
        JAPController.resetMixminionPassword();
        this.m_bttnResetKeyring.setEnabled(false);
        this.m_sliderPathLen.setValue(2);
        this.m_email.setText("");
        this.m_cbxActive.setSelected(false);
    }
    
    private class MyJTable extends JTable
    {
        private static final long serialVersionUID = 1L;
        
        public MyJTable(final DefaultTableModel defaultTableModel) {
            super(defaultTableModel);
        }
        
        public boolean isCellEditable(final int n, final int n2) {
            return false;
        }
    }
}
