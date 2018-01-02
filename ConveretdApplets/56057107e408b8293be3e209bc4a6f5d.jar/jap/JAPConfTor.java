// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import javax.swing.table.TableModel;
import java.util.Date;
import java.util.Vector;
import anon.tor.ordescription.ORDescriptor;
import gui.dialog.JAPDialog;
import logging.LogType;
import anon.tor.ordescription.InfoServiceORListFetcher;
import anon.tor.ordescription.ORListFetcher;
import anon.tor.ordescription.ORList;
import anon.tor.ordescription.PlainORListFetcher;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
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
import java.text.DateFormat;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import gui.JAPJIntField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;

final class JAPConfTor extends AbstractJAPConfModule implements ActionListener
{
    public static final String MSG_ACTIVATE;
    private static final int MIN_CON_PER_PATH = 1;
    private static final int MAX_CON_PER_PATH = 5;
    private JCheckBox m_cbxActive;
    private JTable m_tableRouters;
    private JSlider m_sliderMaxPathLen;
    private JSlider m_sliderMinPathLen;
    private JSlider m_sliderConnectionsPerPath;
    private JButton m_bttnFetchRouters;
    private JLabel m_labelAvailableRouters;
    private JCheckBox m_cbPreCreateRoutes;
    private JCheckBox m_cbNoDefaultTorServer;
    private JTextField m_tfTorDirServerHostName;
    private JAPJIntField m_jintfieldTorDirServerPort;
    private JLabel m_lblMaxPathLen;
    private JLabel m_lblMinPathLen;
    private JLabel m_lblPathSwitchTime;
    private JScrollPane m_scrollPane;
    private JPanel m_panelSlider;
    private TitledBorder m_border;
    private DateFormat ms_dateFormat;
    static /* synthetic */ Class class$jap$JAPConfTor;
    
    public JAPConfTor() {
        super(null);
        this.ms_dateFormat = DateFormat.getDateTimeInstance(2, 3);
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
                JAPConfTor.this.m_labelAvailableRouters.setEnabled(JAPConfTor.this.m_cbxActive.isSelected());
                JAPConfTor.this.m_tableRouters.setEnabled(JAPConfTor.this.m_cbxActive.isSelected());
                JAPConfTor.this.m_bttnFetchRouters.setEnabled(JAPConfTor.this.m_cbxActive.isSelected());
                JAPConfTor.this.m_sliderMinPathLen.setEnabled(JAPConfTor.this.m_cbxActive.isSelected());
                JAPConfTor.this.m_sliderMaxPathLen.setEnabled(JAPConfTor.this.m_cbxActive.isSelected());
                JAPConfTor.this.m_sliderConnectionsPerPath.setEnabled(JAPConfTor.this.m_cbxActive.isSelected());
                JAPConfTor.this.m_cbPreCreateRoutes.setEnabled(JAPConfTor.this.m_cbxActive.isSelected());
                JAPConfTor.this.m_lblMinPathLen.setEnabled(JAPConfTor.this.m_cbxActive.isSelected());
                JAPConfTor.this.m_lblMaxPathLen.setEnabled(JAPConfTor.this.m_cbxActive.isSelected());
                JAPConfTor.this.m_scrollPane.setEnabled(JAPConfTor.this.m_cbxActive.isSelected());
                JAPConfTor.this.m_lblPathSwitchTime.setEnabled(JAPConfTor.this.m_cbxActive.isSelected());
                JAPConfTor.this.m_border = new TitledBorder(JAPConfTor.this.m_border.getTitle());
                if (JAPConfTor.this.m_cbxActive.isSelected()) {
                    JAPConfTor.this.m_bttnFetchRouters.setDisabledIcon(GUIUtils.loadImageIcon("reloaddisabled_anim.gif", true, false));
                }
                else {
                    JAPConfTor.this.m_border.setTitleColor(Color.gray);
                    JAPConfTor.this.m_bttnFetchRouters.setDisabledIcon(GUIUtils.loadImageIcon("reloadrollover.gif", true, false));
                }
                JAPConfTor.this.m_panelSlider.setBorder(JAPConfTor.this.m_border);
                final Dictionary labelTable = JAPConfTor.this.m_sliderMaxPathLen.getLabelTable();
                for (int i = 2; i <= 5; ++i) {
                    labelTable.get(new Integer(i)).setEnabled(JAPConfTor.this.m_sliderMaxPathLen.isEnabled());
                }
                final Dictionary labelTable2 = JAPConfTor.this.m_sliderMinPathLen.getLabelTable();
                for (int j = 2; j <= 5; ++j) {
                    labelTable2.get(new Integer(j)).setEnabled(JAPConfTor.this.m_sliderMinPathLen.isEnabled());
                }
                final Dictionary labelTable3 = JAPConfTor.this.m_sliderConnectionsPerPath.getLabelTable();
                for (int k = 1; k <= 5; ++k) {
                    labelTable3.get(new Integer(k)).setEnabled(JAPConfTor.this.m_sliderConnectionsPerPath.isEnabled());
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
        this.m_labelAvailableRouters = new JLabel(JAPMessages.getString("torBorderAvailableRouters") + ":");
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 0.0;
        panel.add(this.m_labelAvailableRouters, gridBagConstraints2);
        final DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn(JAPMessages.getString("torRouterName"));
        defaultTableModel.addColumn(JAPMessages.getString("torRouterAdr"));
        defaultTableModel.addColumn(JAPMessages.getString("torRouterPort"));
        defaultTableModel.addColumn(JAPMessages.getString("torRouterSoftware"));
        defaultTableModel.setNumRows(3);
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
        (this.m_bttnFetchRouters = new JButton(JAPMessages.getString("torBttnFetchRouters"))).setIcon(GUIUtils.loadImageIcon("reload.gif", true, false));
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
        final JPanel panel2 = new JPanel(new GridBagLayout());
        panel2.setBorder(new TitledBorder(JAPMessages.getString("torBorderTorDirServer")));
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        panel2.add(this.m_cbNoDefaultTorServer = new JCheckBox(JAPMessages.getString("torCheckBoxNoDefaultDirServer")), gridBagConstraints3);
        gridBagConstraints3.gridx = 1;
        panel2.add(new JLabel(JAPMessages.getString("torDirServerHostName")), gridBagConstraints3);
        this.m_tfTorDirServerHostName = new JTextField();
        gridBagConstraints3.gridx = 2;
        panel2.add(this.m_tfTorDirServerHostName, gridBagConstraints3);
        this.m_jintfieldTorDirServerPort = new JAPJIntField(65535);
        gridBagConstraints3.gridx = 3;
        panel2.add(this.m_jintfieldTorDirServerPort, gridBagConstraints3);
        gridBagConstraints3.gridx = 4;
        panel2.add(new JLabel(JAPMessages.getString("torDirServerPort")), gridBagConstraints3);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        rootPanel.add(panel2, gridBagConstraints);
        final JPanel panelSlider = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.anchor = 18;
        gridBagConstraints4.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints4.fill = 0;
        panelSlider.add(this.m_lblMinPathLen = new JLabel(JAPMessages.getString("torPrefMinPathLen")), gridBagConstraints4);
        (this.m_sliderMinPathLen = new JSlider()).setPaintLabels(true);
        this.m_sliderMinPathLen.setPaintTicks(true);
        this.m_sliderMinPathLen.setMajorTickSpacing(1);
        this.m_sliderMinPathLen.setSnapToTicks(true);
        this.m_sliderMinPathLen.setMinimum(2);
        this.m_sliderMinPathLen.setMaximum(5);
        this.m_sliderMinPathLen.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                if (JAPConfTor.this.m_sliderMaxPathLen.getValue() < JAPConfTor.this.m_sliderMinPathLen.getValue()) {
                    JAPConfTor.this.m_sliderMaxPathLen.setValue(JAPConfTor.this.m_sliderMinPathLen.getValue());
                }
            }
        });
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.fill = 2;
        panelSlider.add(this.m_sliderMinPathLen, gridBagConstraints4);
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.fill = 0;
        panelSlider.add(this.m_lblMaxPathLen = new JLabel(JAPMessages.getString("torPrefMaxPathLen")), gridBagConstraints4);
        (this.m_sliderMaxPathLen = new JSlider()).setMinimum(2);
        this.m_sliderMaxPathLen.setMaximum(5);
        this.m_sliderMaxPathLen.setPaintLabels(true);
        this.m_sliderMaxPathLen.setPaintTicks(true);
        this.m_sliderMaxPathLen.setMajorTickSpacing(1);
        this.m_sliderMaxPathLen.setMinorTickSpacing(1);
        this.m_sliderMaxPathLen.setSnapToTicks(true);
        this.m_sliderMaxPathLen.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                if (JAPConfTor.this.m_sliderMaxPathLen.getValue() < JAPConfTor.this.m_sliderMinPathLen.getValue()) {
                    JAPConfTor.this.m_sliderMinPathLen.setValue(JAPConfTor.this.m_sliderMaxPathLen.getValue());
                }
            }
        });
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.fill = 2;
        panelSlider.add(this.m_sliderMaxPathLen, gridBagConstraints4);
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 2;
        gridBagConstraints4.fill = 0;
        panelSlider.add(this.m_lblPathSwitchTime = new JLabel(JAPMessages.getString("torPrefPathSwitchTime")), gridBagConstraints4);
        this.m_sliderConnectionsPerPath = new JSlider();
        final Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put(new Integer(1), new JLabel("10"));
        labelTable.put(new Integer(2), new JLabel("50"));
        labelTable.put(new Integer(3), new JLabel("100"));
        labelTable.put(new Integer(4), new JLabel("500"));
        labelTable.put(new Integer(5), new JLabel("1000"));
        this.m_sliderConnectionsPerPath.setLabelTable(labelTable);
        this.m_sliderConnectionsPerPath.setMinimum(1);
        this.m_sliderConnectionsPerPath.setMaximum(5);
        this.m_sliderConnectionsPerPath.setMajorTickSpacing(1);
        this.m_sliderConnectionsPerPath.setMinorTickSpacing(1);
        this.m_sliderConnectionsPerPath.setSnapToTicks(true);
        this.m_sliderConnectionsPerPath.setPaintLabels(true);
        this.m_sliderConnectionsPerPath.setPaintTicks(true);
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.weightx = 1.0;
        gridBagConstraints4.fill = 2;
        panelSlider.add(this.m_sliderConnectionsPerPath, gridBagConstraints4);
        this.m_cbPreCreateRoutes = new JCheckBox(JAPMessages.getString("ngConfAnonGeneralPreCreateRoutes"));
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints4;
        ++gridBagConstraints5.gridy;
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridwidth = 2;
        panelSlider.add(this.m_cbPreCreateRoutes, gridBagConstraints4);
        panelSlider.setBorder(this.m_border = new TitledBorder(JAPMessages.getString("torBorderPreferences")));
        this.m_panelSlider = panelSlider;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        rootPanel.add(panelSlider, gridBagConstraints);
    }
    
    public String getTabTitle() {
        return "Tor";
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("enableTor")) {
            this.updateGuiOutput();
        }
        else if (actionEvent.getActionCommand().equals("fetchRouters")) {
            this.fetchRoutersAsync(true);
        }
    }
    
    protected boolean onOkPressed() {
        JAPModel.getInstance().setTorActivated(this.m_cbxActive.isSelected());
        JAPController.setTorMaxConnectionsPerRoute((new int[] { 10, 50, 100, 500, 1000 })[this.m_sliderConnectionsPerPath.getValue() - 1]);
        JAPController.setTorRouteLen(this.m_sliderMinPathLen.getValue(), this.m_sliderMaxPathLen.getValue());
        JAPController.setPreCreateAnonRoutes(this.m_cbPreCreateRoutes.isSelected());
        JAPController.setTorUseNoneDefaultDirServer(this.m_cbNoDefaultTorServer.isSelected());
        return true;
    }
    
    protected void onUpdateValues() {
        this.updateGuiOutput();
    }
    
    public String getHelpContext() {
        return "services_tor";
    }
    
    private void updateGuiOutput() {
        final int torMaxConnectionsPerRoute = JAPModel.getTorMaxConnectionsPerRoute();
        int value;
        if (torMaxConnectionsPerRoute < 25) {
            value = 1;
        }
        else if (torMaxConnectionsPerRoute < 75) {
            value = 2;
        }
        else if (torMaxConnectionsPerRoute < 250) {
            value = 3;
        }
        else if (torMaxConnectionsPerRoute < 750) {
            value = 4;
        }
        else {
            value = 5;
        }
        this.m_sliderConnectionsPerPath.setValue(value);
        this.m_sliderMaxPathLen.setValue(JAPModel.getTorMaxRouteLen());
        this.m_sliderMinPathLen.setValue(JAPModel.getTorMinRouteLen());
        this.m_cbPreCreateRoutes.setSelected(JAPModel.isPreCreateAnonRoutesEnabled());
        this.m_cbxActive.setSelected(JAPModel.getInstance().isTorActivated());
        this.m_cbNoDefaultTorServer.setSelected(JAPModel.isTorNoneDefaultDirServerEnabled());
    }
    
    private void fetchRoutersAsync(final boolean b) {
        this.m_bttnFetchRouters.setEnabled(false);
        new Thread(new Runnable() {
            public void run() {
                ORList list;
                if (JAPModel.isTorNoneDefaultDirServerEnabled()) {
                    list = new ORList(new PlainORListFetcher("141.76.45.45", 9030));
                }
                else {
                    list = new ORList(new InfoServiceORListFetcher());
                }
                if (!list.updateList()) {
                    if (b) {
                        JAPDialog.showErrorDialog(JAPConfTor.this.getRootPanel(), JAPMessages.getString("torErrorFetchRouters"), LogType.MISC);
                    }
                    JAPConfTor.this.m_bttnFetchRouters.setEnabled(true);
                    return;
                }
                final DefaultTableModel defaultTableModel = (DefaultTableModel)JAPConfTor.this.m_tableRouters.getModel();
                final Vector list2 = list.getList();
                defaultTableModel.setNumRows(list2.size());
                for (int i = 0; i < list2.size(); ++i) {
                    final ORDescriptor orDescriptor = list2.elementAt(i);
                    JAPConfTor.this.m_tableRouters.setValueAt(orDescriptor.getName(), i, 0);
                    JAPConfTor.this.m_tableRouters.setValueAt(orDescriptor.getAddress(), i, 1);
                    JAPConfTor.this.m_tableRouters.setValueAt(new Integer(orDescriptor.getPort()), i, 2);
                    JAPConfTor.this.m_tableRouters.setValueAt(orDescriptor.getSoftware(), i, 3);
                    JAPConfTor.this.m_tableRouters.invalidate();
                }
                final Date published = list.getPublished();
                String s = JAPMessages.getString("unknown");
                if (published != null) {
                    s = JAPConfTor.this.ms_dateFormat.format(published);
                }
                JAPConfTor.this.m_labelAvailableRouters.setText(JAPMessages.getString("torBorderAvailableRouters") + " (" + s + "):");
                JAPConfTor.this.m_labelAvailableRouters.invalidate();
                JAPConfTor.this.getRootPanel().validate();
                JAPConfTor.this.m_bttnFetchRouters.setEnabled(true);
            }
        }).start();
    }
    
    public void onResetToDefaultsPressed() {
        this.m_cbPreCreateRoutes.setSelected(false);
        this.m_sliderMaxPathLen.setValue(3);
        this.m_sliderMinPathLen.setValue(2);
        this.m_sliderConnectionsPerPath.setValue(1000);
        this.m_cbxActive.setSelected(false);
        this.m_cbNoDefaultTorServer.setSelected(false);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_ACTIVATE = ((JAPConfTor.class$jap$JAPConfTor == null) ? (JAPConfTor.class$jap$JAPConfTor = class$("jap.JAPConfTor")) : JAPConfTor.class$jap$JAPConfTor).getName() + "_activate";
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
