// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.pay.PayAccount;
import anon.infoservice.MixCascade;
import java.util.Hashtable;
import java.util.Dictionary;
import logging.LogLevel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import gui.GUIUtils;
import platform.AbstractOS;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import gui.JAPHelpContext;
import java.awt.Dimension;
import logging.LogHolder;
import logging.LogType;
import java.awt.event.WindowEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.SwingUtilities;
import java.util.Observable;
import java.util.Observer;
import java.awt.Container;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import gui.help.JAPHelp;
import java.awt.FlowLayout;
import javax.swing.tree.TreePath;
import jap.forward.JAPConfForwardingState;
import jap.forward.JAPConfForwardingServer;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Component;
import anon.util.JAPMessages;
import jap.pay.AccountSettingsPanel;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JSlider;
import gui.JAPMultilineLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import gui.dialog.JAPDialog;

public final class JAPConf extends JAPDialog implements ActionListener, WindowListener
{
    public static final String MSG_READ_PANEL_HELP;
    private static final String MSG_DETAILLEVEL;
    private static final String MSG_BTN_SAVE;
    private static final String MSG_ASK_RESET_DEFAULTS;
    private static final String MSG_NEED_RESTART;
    public static final String NETWORK_TAB = "NETWORK_TAB";
    public static final String UI_TAB = "UI_TAB";
    public static final String UPDATE_TAB = "UPDATE_TAB";
    public static final String PROXY_TAB = "PROXY_TAB";
    public static final String INFOSERVICE_TAB = "INFOSERVICE_TAB";
    public static final String ANON_TAB = "ANON_TAB";
    public static final String ANON_SERVICES_TAB = "SERVICES_TAB";
    public static final String ANON_TRUST_TAB = "ANON_TRUST_TAB";
    public static final String CERT_TAB = "CERT_TAB";
    public static final String TOR_TAB = "TOR_TAB";
    public static final String DEBUG_TAB = "DEBUG_TAB";
    public static final String PAYMENT_TAB = "PAYMENT_TAB";
    public static final String HTTP_FILTER_TAB = "HTTP_FILTER_TAB";
    public static final String FORWARDING_CLIENT_TAB = "FORWARDING_CLIENT_TAB";
    public static final String FORWARDING_SERVER_TAB = "FORWARDING_SERVER_TAB";
    public static final String FORWARDING_STATE_TAB = "FORWARDING_STATE_TAB";
    private static JAPConf ms_JapConfInstance;
    private JAPController m_Controller;
    private JCheckBox[] m_cbLogTypes;
    private JCheckBox m_cbShowDebugConsole;
    private JCheckBox m_cbDebugToFile;
    private JTextField m_tfDebugFileName;
    private JButton m_bttnDebugFileNameSearch;
    private JAPMultilineLabel m_labelConfDebugLevel;
    private JAPMultilineLabel m_labelConfDebugTypes;
    private JSlider m_sliderDebugLevel;
    private JSlider m_sliderDebugDetailLevel;
    private JPanel m_pMisc;
    private JButton m_bttnDefaultConfig;
    private JButton m_bttnCancel;
    private JButton m_bttnHelp;
    private boolean m_bWithPayment;
    private boolean m_bIsSimpleView;
    private Vector m_vecConfigChangesNeedRestart;
    private JAPConfModuleSystem m_moduleSystem;
    private JAPConfServices m_confServices;
    private AbstractJAPMainView m_parentView;
    private AccountSettingsPanel m_accountSettings;
    private JAPConfUI m_confUI;
    static /* synthetic */ Class class$jap$JAPConf;
    
    public static JAPConf getInstance() {
        return JAPConf.ms_JapConfInstance;
    }
    
    public JAPConf(final AbstractJAPMainView parentView, final boolean bWithPayment) {
        super(parentView, JAPMessages.getString("settingsDialog"), true);
        this.m_bWithPayment = false;
        this.m_vecConfigChangesNeedRestart = new Vector();
        this.m_parentView = parentView;
        this.setDefaultCloseOperation(1);
        this.m_bWithPayment = bWithPayment;
        this.m_bIsSimpleView = (JAPModel.getDefaultView() == 2);
        JAPConf.ms_JapConfInstance = this;
        this.m_Controller = JAPController.getInstance();
        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        this.m_pMisc = this.buildMiscPanel();
        this.m_moduleSystem = new JAPConfModuleSystem();
        final DefaultMutableTreeNode configurationTreeRootNode = this.m_moduleSystem.getConfigurationTreeRootNode();
        this.m_confUI = new JAPConfUI();
        this.m_moduleSystem.addConfigurationModule(configurationTreeRootNode, this.m_confUI, "UI_TAB");
        if (this.m_bWithPayment) {
            this.m_accountSettings = new AccountSettingsPanel();
            this.m_moduleSystem.addConfigurationModule(configurationTreeRootNode, this.m_accountSettings, "PAYMENT_TAB");
        }
        if (!this.m_bIsSimpleView) {
            this.m_moduleSystem.addConfigurationModule(configurationTreeRootNode, new JAPConfUpdate(), "UPDATE_TAB");
        }
        this.m_moduleSystem.addConfigurationModule(configurationTreeRootNode, new JAPConfNetwork(), "NETWORK_TAB");
        this.m_confServices = new JAPConfServices();
        final DefaultMutableTreeNode addComponent = this.m_moduleSystem.addComponent(configurationTreeRootNode, null, "ngTreeAnonService", null, null);
        if (!this.m_bIsSimpleView) {
            this.m_moduleSystem.addConfigurationModule(addComponent, this.m_confServices, "SERVICES_TAB");
            this.m_moduleSystem.addConfigurationModule(addComponent, new JAPConfInfoService(), "INFOSERVICE_TAB");
            this.m_moduleSystem.addConfigurationModule(addComponent, new JAPConfForwardingServer(), "FORWARDING_SERVER_TAB");
            this.m_moduleSystem.addConfigurationModule(addComponent, new JAPConfCert(), "CERT_TAB");
            final DefaultMutableTreeNode addComponent2 = this.m_moduleSystem.addComponent(configurationTreeRootNode, this.m_pMisc, "ngTreeDebugging", "DEBUG_TAB", "debugging");
            if (JAPModel.getInstance().isForwardingStateModuleVisible()) {
                this.m_moduleSystem.addConfigurationModule(addComponent2, new JAPConfForwardingState(), "FORWARDING_STATE_TAB");
            }
        }
        else {
            this.m_moduleSystem.addConfigurationModule(addComponent, this.m_confServices, "SERVICES_TAB");
        }
        this.m_moduleSystem.getConfigurationTree().expandPath(new TreePath(addComponent.getPath()));
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(2));
        panel.add(this.m_bttnHelp = new JButton(JAPMessages.getString(JAPHelp.MSG_HELP_BUTTON)));
        this.m_bttnHelp.addActionListener(this);
        (this.m_bttnDefaultConfig = new JButton(JAPMessages.getString("bttnDefaultConfig"))).addActionListener(new ActionListener() {
            private final /* synthetic */ JAPDialog val$view = this;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                if (JAPDialog.showConfirmDialog(this.val$view, JAPMessages.getString(JAPConf.MSG_ASK_RESET_DEFAULTS), 2, 2) == 0) {
                    JAPConf.this.resetToDefault();
                }
            }
        });
        if (!JAPModel.isSmallDisplay()) {
            panel.add(this.m_bttnDefaultConfig);
        }
        (this.m_bttnCancel = new JButton(JAPMessages.getString("cancelButton"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPConf.this.cancelPressed();
            }
        });
        panel.add(this.m_bttnCancel);
        final JButton button = new JButton(JAPMessages.getString(JAPConf.MSG_BTN_SAVE));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPConf.this.okPressed(false);
            }
        });
        panel.add(button);
        final JButton defaultButton = new JButton(JAPMessages.getString("okButton"));
        defaultButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPConf.this.okPressed(true);
            }
        });
        panel.add(defaultButton);
        panel.add(new JLabel("   "));
        this.getRootPane().setDefaultButton(defaultButton);
        final JPanel rootPanel = this.m_moduleSystem.getRootPanel();
        final GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(rootPanel, gridBagConstraints);
        contentPane.add(rootPanel);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        layout.setConstraints(panel, gridBagConstraints);
        contentPane.add(panel);
        this.setContentPane(contentPane);
        this.updateValues();
        if (JAPModel.getDefaultView() == 2) {
            this.m_moduleSystem.selectNode("SERVICES_TAB");
        }
        else {
            this.m_moduleSystem.selectNode("DEBUG_TAB");
        }
        if (JAPModel.isSmallDisplay()) {
            this.setSize(240, 300);
            this.setLocation(0, 0);
        }
        else if (JAPModel.getInstance().isConfigWindowSizeSaved() && JAPModel.getInstance().getConfigSize() != null) {
            this.setSize(JAPModel.getInstance().getConfigSize());
        }
        else {
            this.doPack();
        }
        this.m_confUI.afterPack();
        this.m_moduleSystem.getConfigurationTree().setMinimumSize(this.m_moduleSystem.getConfigurationTree().getPreferredSize());
        this.m_moduleSystem.selectNode("UI_TAB");
        this.restoreLocation(JAPModel.getInstance().getConfigWindowLocation());
        this.addWindowListener(this);
        this.m_moduleSystem.initObservers();
        JAPModel.getInstance().addObserver(new Observer() {
            private final /* synthetic */ JAPConf this$0;
            
            public void update(final Observable observable, final Object o) {
                if (o instanceof JAPModel.FontResize) {
                    final Runnable runnable = new Runnable() {
                        private final /* synthetic */ JAPConf$5 this$1 = this$1;
                        
                        public void run() {
                            SwingUtilities.updateComponentTreeUI(this.this$1.this$0.getContentPane());
                        }
                    };
                    if (SwingUtilities.isEventDispatchThread()) {
                        runnable.run();
                    }
                    else {
                        SwingUtilities.invokeLater(runnable);
                    }
                }
            }
        });
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.cancelPressed();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    protected synchronized void doPack() {
        int i = 0;
        int n = 0;
        try {
            if (SwingUtilities.isEventDispatchThread()) {
                this.m_moduleSystem.revalidate();
            }
            else {
                SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        JAPConf.this.m_moduleSystem.revalidate();
                    }
                });
            }
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.GUI, ex);
        }
        while (i == 0) {
            this.pack();
            if (this.getSize().width < this.getSize().height) {
                LogHolder.log(3, LogType.GUI, "Could not pack config properly. Width is smaller than height! Width:" + this.getSize().width + " Height:" + this.getSize().height);
                i = 1;
            }
            else if (this.getSize().width > this.getScreenBounds().width || this.getSize().height > this.getScreenBounds().height) {
                LogHolder.log(3, LogType.GUI, "Packed config view with illegal size! " + this.getSize());
                i = 1;
            }
            else {
                JAPModel.getInstance().setConfigSize(this.getSize());
            }
            if (i == 0) {
                break;
            }
            this.m_moduleSystem.revalidate();
            if (n == 0) {
                if (JAPModel.getInstance().getConfigSize() != null && JAPModel.getInstance().getConfigSize().width > 0 && JAPModel.getInstance().getConfigSize().height > 0) {
                    this.setSize(JAPModel.getInstance().getConfigSize());
                }
                else {
                    this.setSize(new Dimension(786, 545));
                }
                LogHolder.log(3, LogType.GUI, "Setting default config size to " + this.getSize());
                break;
            }
            i = 0;
            n = 0;
        }
    }
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.m_parentView.getViewIconified().switchBackToMainView();
            this.m_moduleSystem.createSavePoints();
        }
        super.setVisible(visible);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.m_bttnHelp) {
            JAPHelp.getInstance().setContext(this.m_moduleSystem);
            JAPHelp.getInstance().loadCurrentContext();
        }
    }
    
    private JPanel buildMiscPanel() {
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Debugging"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final JPanel panel2 = new JPanel(new GridLayout(0, 1));
        this.m_cbLogTypes = new JCheckBox[LogType.getNumberOfLogTypes()];
        final int[] availableLogTypes = LogType.getAvailableLogTypes();
        for (int i = 0; i < this.m_cbLogTypes.length; ++i) {
            this.m_cbLogTypes[i] = new JCheckBox(LogType.getLogTypeName(availableLogTypes[i]));
            if (i > 0) {
                panel2.add(this.m_cbLogTypes[i]);
            }
        }
        panel.add(this.m_labelConfDebugTypes = new JAPMultilineLabel(JAPMessages.getString("ConfDebugTypes")), gridBagConstraints);
        gridBagConstraints.gridy = 1;
        panel.add(panel2, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        panel.add(new JSeparator(), gridBagConstraints);
        (this.m_cbShowDebugConsole = new JCheckBox(JAPMessages.getString("ConfDebugShowConsole"))).setSelected(JAPDebug.isShowConsole());
        JAPDebug.getInstance().addObserver(new Observer() {
            public void update(final Observable observable, final Object o) {
                JAPConf.this.m_cbShowDebugConsole.setSelected(false);
            }
        });
        this.m_cbShowDebugConsole.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                JAPDebug.showConsole(itemEvent.getStateChange() == 1, JAPController.getInstance().getViewWindow());
            }
        });
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(this.m_cbShowDebugConsole, gridBagConstraints);
        (this.m_cbDebugToFile = new JCheckBox(JAPMessages.getString("ConfDebugFile"))).addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                final boolean selected = JAPConf.this.m_cbDebugToFile.isSelected();
                JAPConf.this.m_bttnDebugFileNameSearch.setEnabled(selected);
                JAPConf.this.m_tfDebugFileName.setEnabled(selected);
            }
        });
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 0.0;
        panel.add(this.m_cbDebugToFile, gridBagConstraints);
        final JPanel panel3 = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        this.m_tfDebugFileName = new JTextField(20);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.insets = new Insets(0, 5, 0, 5);
        gridBagConstraints2.fill = 2;
        panel3.add(this.m_tfDebugFileName, gridBagConstraints2);
        (this.m_bttnDebugFileNameSearch = new JButton(JAPMessages.getString("ConfDebugFileNameSearch"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final JFileChooser fileChooser = new JFileChooser();
                String trim = JAPConf.ms_JapConfInstance.m_tfDebugFileName.getText().trim();
                if (!trim.equals("")) {
                    try {
                        fileChooser.setCurrentDirectory(new File(new File(trim).getParent()));
                    }
                    catch (Exception ex) {
                        trim = "";
                    }
                }
                if (JAPController.getInstance().isPortableMode() && trim.equals("")) {
                    final String property = AbstractOS.getInstance().getProperty("user.dir");
                    if (property != null) {
                        fileChooser.setCurrentDirectory(new File(property));
                    }
                }
                if (GUIUtils.showMonitoredFileChooser(fileChooser, JAPConf.ms_JapConfInstance.getContentPane()) == 0) {
                    try {
                        if (JAPController.getInstance().isPortableMode()) {
                            JAPConf.this.m_tfDebugFileName.setText(AbstractOS.toRelativePath(fileChooser.getSelectedFile().getCanonicalPath()));
                        }
                        else {
                            JAPConf.this.m_tfDebugFileName.setText(fileChooser.getSelectedFile().getCanonicalPath());
                        }
                    }
                    catch (IOException ex2) {}
                }
            }
        });
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.weightx = 0.0;
        panel3.add(this.m_bttnDebugFileNameSearch, gridBagConstraints2);
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 2;
        panel.add(panel3, gridBagConstraints);
        final JPanel panel4 = new JPanel();
        (this.m_sliderDebugLevel = new JSlider(1, 0, 7, 0)).addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                final Dictionary labelTable = JAPConf.this.m_sliderDebugLevel.getLabelTable();
                for (int i = 0; i < LogLevel.getLevelCount(); ++i) {
                    labelTable.get(new Integer(i)).setEnabled(i <= JAPConf.this.m_sliderDebugLevel.getValue());
                }
            }
        });
        final Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>(LogLevel.getLevelCount(), 1.0f);
        for (int j = 0; j < LogLevel.getLevelCount(); ++j) {
            labelTable.put(new Integer(j), new JLabel(" " + LogLevel.getLevelName(j)));
        }
        this.m_sliderDebugLevel.setLabelTable(labelTable);
        this.m_sliderDebugLevel.setPaintLabels(true);
        this.m_sliderDebugLevel.setMajorTickSpacing(1);
        this.m_sliderDebugLevel.setMinorTickSpacing(1);
        this.m_sliderDebugLevel.setSnapToTicks(true);
        this.m_sliderDebugLevel.setPaintTrack(true);
        this.m_sliderDebugLevel.setPaintTicks(false);
        panel4.add(this.m_sliderDebugLevel);
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 3;
        panel.add(new JSeparator(1), gridBagConstraints);
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(this.m_labelConfDebugLevel = new JAPMultilineLabel(JAPMessages.getString("ConfDebugLevels")), gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        panel.add(panel4, gridBagConstraints);
        final JPanel panel5 = new JPanel();
        (this.m_sliderDebugDetailLevel = new JSlider(1, 0, 3, LogHolder.getDetailLevel())).setPaintTicks(false);
        this.m_sliderDebugDetailLevel.setPaintLabels(true);
        this.m_sliderDebugDetailLevel.setMajorTickSpacing(1);
        this.m_sliderDebugDetailLevel.setMinorTickSpacing(1);
        this.m_sliderDebugDetailLevel.setSnapToTicks(true);
        this.m_sliderDebugDetailLevel.setPaintTrack(true);
        panel5.add(this.m_sliderDebugDetailLevel);
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 3;
        panel.add(new JSeparator(1), gridBagConstraints);
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panel.add(this.m_labelConfDebugLevel = new JAPMultilineLabel(JAPMessages.getString(JAPConf.MSG_DETAILLEVEL)), gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        panel.add(panel5, gridBagConstraints);
        return panel;
    }
    
    void cancelPressed() {
        this.m_vecConfigChangesNeedRestart.removeAllElements();
        this.m_moduleSystem.processCancelPressedEvent();
        this.setVisible(false);
    }
    
    private boolean checkValues() {
        return true;
    }
    
    private void resetToDefault() {
        this.m_vecConfigChangesNeedRestart.removeAllElements();
        this.m_moduleSystem.processResetToDefaultsPressedEvent();
        this.m_cbShowDebugConsole.setSelected(false);
        this.m_sliderDebugLevel.setValue(4);
        for (int i = 0; i < this.m_cbLogTypes.length; ++i) {
            this.m_cbLogTypes[i].setSelected(true);
        }
        this.m_sliderDebugDetailLevel.setValue(3);
        this.m_cbDebugToFile.setSelected(false);
    }
    
    private void onOkPressed() {
        final int[] availableLogTypes = LogType.getAvailableLogTypes();
        int nul = LogType.NUL;
        for (int i = 0; i < this.m_cbLogTypes.length; ++i) {
            nul |= (this.m_cbLogTypes[i].isSelected() ? availableLogTypes[i] : LogType.NUL);
        }
        JAPDebug.getInstance().setLogType(nul);
        JAPDebug.getInstance().setLogLevel(this.m_sliderDebugLevel.getValue());
        LogHolder.setDetailLevel(this.m_sliderDebugDetailLevel.getValue());
        String trim = this.m_tfDebugFileName.getText().trim();
        if (!this.m_cbDebugToFile.isSelected()) {
            trim = null;
        }
        JAPDebug.setLogToFile(trim);
    }
    
    private void okPressed(final boolean b) {
        if (!this.checkValues()) {
            return;
        }
        this.m_vecConfigChangesNeedRestart.removeAllElements();
        if (!this.m_moduleSystem.processOkPressedEvent()) {
            this.m_vecConfigChangesNeedRestart.removeAllElements();
            return;
        }
        this.onOkPressed();
        this.resetAutomaticLocation(JAPModel.getInstance().isConfigWindowLocationSaved());
        if (this.m_vecConfigChangesNeedRestart.size() > 0) {
            String string = "<ul>";
            for (int i = 0; i < this.m_vecConfigChangesNeedRestart.size(); ++i) {
                final AbstractRestartNeedingConfigChange abstractRestartNeedingConfigChange = this.m_vecConfigChangesNeedRestart.elementAt(i);
                String s = string + "<li>" + abstractRestartNeedingConfigChange.getName();
                if (abstractRestartNeedingConfigChange.getMessage() != null && abstractRestartNeedingConfigChange.getMessage().trim().length() > 0) {
                    s = s + "<br>" + abstractRestartNeedingConfigChange.getMessage();
                }
                string = s + "</li>";
            }
            if (!JAPDialog.showYesNoDialog(this, JAPMessages.getString(JAPConf.MSG_NEED_RESTART, string + "</ul>"))) {
                for (int j = 0; j < this.m_vecConfigChangesNeedRestart.size(); ++j) {
                    ((AbstractRestartNeedingConfigChange)this.m_vecConfigChangesNeedRestart.elementAt(j)).doCancel();
                }
                this.m_vecConfigChangesNeedRestart.removeAllElements();
                return;
            }
            for (int k = 0; k < this.m_vecConfigChangesNeedRestart.size(); ++k) {
                ((AbstractRestartNeedingConfigChange)this.m_vecConfigChangesNeedRestart.elementAt(k)).doChange();
            }
        }
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                JAPConf.this.m_Controller.saveConfigFile();
                if (b && !JAPConf.this.isRestartNeeded()) {
                    JAPConf.this.setVisible(false);
                }
                if (JAPConf.this.isRestartNeeded()) {
                    JAPController.goodBye(false);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    
    public void selectCard(final String s, final Object o) {
        if (s != null) {
            if (s.equals("UI_TAB")) {
                this.m_moduleSystem.selectNode("UI_TAB");
                new Thread(new Runnable() {
                    public void run() {
                        JAPConf.this.m_confUI.chooseBrowserPath();
                    }
                }).start();
            }
            else if (s.equals("ANON_TAB")) {
                this.m_moduleSystem.selectNode("SERVICES_TAB");
                if (o instanceof MixCascade) {
                    this.m_confServices.selectAnonTab((MixCascade)o, false);
                }
                else if (o instanceof Boolean) {
                    this.m_confServices.selectAnonTab(null, (boolean)o);
                }
                else {
                    this.m_confServices.selectAnonTab(null, false);
                }
            }
            else if (s.equals("PAYMENT_TAB")) {
                this.m_moduleSystem.selectNode("PAYMENT_TAB");
                if (o != null) {
                    new Thread(new Runnable() {
                        public void run() {
                            if (o instanceof Boolean && (boolean)o) {
                                JAPConf.this.m_accountSettings.doCreateAccount(null);
                            }
                            else if (o instanceof String) {
                                JAPConf.this.m_accountSettings.doCreateAccount((String)o);
                            }
                            else if (o instanceof PayAccount) {
                                JAPConf.this.m_accountSettings.showOpenTransaction((PayAccount)o);
                            }
                            else if (o instanceof Boolean && !(boolean)o) {
                                JAPConf.this.m_accountSettings.backupAccount();
                            }
                        }
                    }).start();
                }
            }
            else {
                this.m_moduleSystem.selectNode(s);
            }
        }
    }
    
    private synchronized void updateValues() {
        this.m_moduleSystem.processUpdateValuesEvent(true);
        this.m_cbShowDebugConsole.setSelected(JAPDebug.isShowConsole());
        final int[] availableLogTypes = LogType.getAvailableLogTypes();
        for (int i = 0; i < this.m_cbLogTypes.length; ++i) {
            this.m_cbLogTypes[i].setSelected((JAPDebug.getInstance().getLogType() & availableLogTypes[i]) != 0x0);
        }
        this.m_sliderDebugLevel.setValue(JAPDebug.getInstance().getLogLevel());
        this.m_sliderDebugDetailLevel.setValue(LogHolder.getDetailLevel());
        final boolean logToFile = JAPDebug.isLogToFile();
        this.m_tfDebugFileName.setEnabled(logToFile);
        this.m_bttnDebugFileNameSearch.setEnabled(logToFile);
        this.m_cbDebugToFile.setSelected(logToFile);
        if (logToFile) {
            this.m_tfDebugFileName.setText(JAPDebug.getLogFilename());
        }
    }
    
    protected void addNeedRestart(final AbstractRestartNeedingConfigChange abstractRestartNeedingConfigChange) {
        if (abstractRestartNeedingConfigChange != null) {
            this.m_vecConfigChangesNeedRestart.addElement(abstractRestartNeedingConfigChange);
        }
    }
    
    private boolean isRestartNeeded() {
        return this.m_vecConfigChangesNeedRestart.size() > 0;
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
        MSG_READ_PANEL_HELP = ((JAPConf.class$jap$JAPConf == null) ? (JAPConf.class$jap$JAPConf = class$("jap.JAPConf")) : JAPConf.class$jap$JAPConf).getName() + "_readPanelHelp";
        MSG_DETAILLEVEL = ((JAPConf.class$jap$JAPConf == null) ? (JAPConf.class$jap$JAPConf = class$("jap.JAPConf")) : JAPConf.class$jap$JAPConf).getName() + "_detaillevel";
        MSG_BTN_SAVE = ((JAPConf.class$jap$JAPConf == null) ? (JAPConf.class$jap$JAPConf = class$("jap.JAPConf")) : JAPConf.class$jap$JAPConf).getName() + "_btnSave";
        MSG_ASK_RESET_DEFAULTS = ((JAPConf.class$jap$JAPConf == null) ? (JAPConf.class$jap$JAPConf = class$("jap.JAPConf")) : JAPConf.class$jap$JAPConf).getName() + "_askResetDefaults";
        MSG_NEED_RESTART = ((JAPConf.class$jap$JAPConf == null) ? (JAPConf.class$jap$JAPConf = class$("jap.JAPConf")) : JAPConf.class$jap$JAPConf).getName() + "_needRestart";
        JAPConf.ms_JapConfInstance = null;
    }
    
    public abstract static class AbstractRestartNeedingConfigChange
    {
        public abstract String getName();
        
        public abstract void doChange();
        
        public void doCancel() {
        }
        
        public String getMessage() {
            return "";
        }
    }
}
