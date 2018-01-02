// 
// Decompiled by Procyon v0.5.30
// 

package jap.forward;

import gui.dialog.WorkerContentPane;
import anon.infoservice.Database;
import java.util.Hashtable;
import anon.infoservice.InfoServiceHolder;
import anon.infoservice.InfoServiceDBEntry;
import anon.infoservice.MixCascade;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import jap.JAPController;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import gui.GUIUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.FocusListener;
import gui.dialog.JAPDialog;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.Document;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.util.Enumeration;
import javax.swing.ListModel;
import anon.util.JAPMessages;
import logging.LogHolder;
import logging.LogType;
import jap.JAPModel;
import java.util.Observable;
import java.util.Observer;
import jap.IJAPConfSavePoint;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.DefaultListModel;
import jap.AbstractJAPConfModule;

public class JAPConfForwardingServer extends AbstractJAPConfModule
{
    private DefaultListModel m_knownCascadesListModel;
    private DefaultListModel m_knownInfoServicesListModel;
    private DefaultListModel m_allowedCascadesListModel;
    private DefaultListModel m_registrationInfoServicesListModel;
    private JCheckBox m_startServerBox;
    private JTextField serverPortField;
    private JTextField uploadBandwidthField;
    private JComboBox connectionClassesComboBox;
    private JLabel settingsForwardingServerConfigCurrentBandwidthLabel;
    private JTextField relativeBandwidthField;
    private JButton increaseRelativeBandwidthButton;
    private JButton decreaseRelativeBandwidthButton;
    private JLabel settingsForwardingServerConfigAllowedCascadesKnownCascadesLabel;
    private JLabel settingsForwardingServerConfigAllowedCascadesAllowedCascadesLabel;
    private JList knownCascadesList;
    private JList allowedCascadesList;
    private JButton settingsForwardingServerConfigAllowedCascadesReloadButton;
    private JButton settingsForwardingServerConfigAllowedCascadesAddButton;
    private JButton settingsForwardingServerConfigAllowedCascadesRemoveButton;
    private JCheckBox settingsForwardingServerConfigAllowedCascadesAllowAllBox;
    private JLabel settingsForwardingServerConfigRegistrationInfoServicesKnownInfoServicesLabel;
    private JLabel settingsForwardingServerConfigRegistrationInfoServicesSelectedInfoServicesLabel;
    private JList knownInfoServicesList;
    private JList registrationInfoServicesList;
    private JButton settingsForwardingServerConfigRegistrationInfoServicesReloadButton;
    private JButton settingsForwardingServerConfigRegistrationInfoServicesAddButton;
    private JButton settingsForwardingServerConfigRegistrationInfoServicesRemoveButton;
    private JCheckBox settingsForwardingServerConfigRegistrationInfoServicesRegisterAtAllBox;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    
    public JAPConfForwardingServer() {
        super(new JAPConfForwardingServerSavePoint());
    }
    
    protected boolean initObservers() {
        if (super.initObservers()) {
            synchronized (super.LOCK_OBSERVABLE) {
                final Observer observer = new Observer() {
                    public void update(final Observable observable, final Object o) {
                        try {
                            if (observable == JAPModel.getInstance().getRoutingSettings() && ((JAPRoutingMessage)o).getMessageCode() == 15) {
                                JAPConfForwardingServer.this.serverPortField.setText(Integer.toString(JAPModel.getInstance().getRoutingSettings().getServerPort()));
                            }
                        }
                        catch (Exception ex) {
                            LogHolder.log(2, LogType.GUI, ex);
                        }
                    }
                };
                JAPModel.getInstance().getRoutingSettings().addObserver(observer);
                observer.update(JAPModel.getInstance().getRoutingSettings(), new JAPRoutingMessage(15));
                final Observer observer2 = new Observer() {
                    public void update(final Observable observable, final Object o) {
                        try {
                            if (observable == JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector() && ((JAPRoutingMessage)o).getMessageCode() == 6) {
                                final JAPRoutingConnectionClass currentConnectionClass = JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().getCurrentConnectionClass();
                                JAPConfForwardingServer.this.connectionClassesComboBox.setSelectedItem(currentConnectionClass);
                                if (currentConnectionClass.getIdentifier() == 8) {
                                    JAPConfForwardingServer.this.uploadBandwidthField.setEnabled(true);
                                }
                                else {
                                    JAPConfForwardingServer.this.uploadBandwidthField.setEnabled(false);
                                }
                                JAPConfForwardingServer.this.uploadBandwidthField.setText(Integer.toString(currentConnectionClass.getMaximumBandwidth() * 8 / 1000));
                            }
                        }
                        catch (Exception ex) {
                            LogHolder.log(2, LogType.GUI, ex);
                        }
                    }
                };
                JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().addObserver(observer2);
                observer2.update(JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector(), new JAPRoutingMessage(6));
                final Observer observer3 = new Observer() {
                    public void update(final Observable observable, final Object o) {
                        try {
                            if (observable == JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector()) {
                                if (((JAPRoutingMessage)o).getMessageCode() == 6 || ((JAPRoutingMessage)o).getMessageCode() == 7) {
                                    final JAPRoutingConnectionClass currentConnectionClass = JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().getCurrentConnectionClass();
                                    JAPConfForwardingServer.this.relativeBandwidthField.setText(Integer.toString(currentConnectionClass.getRelativeBandwidth()) + "%");
                                    if (currentConnectionClass.getRelativeBandwidth() < 100) {
                                        JAPConfForwardingServer.this.increaseRelativeBandwidthButton.setEnabled(true);
                                    }
                                    else {
                                        JAPConfForwardingServer.this.increaseRelativeBandwidthButton.setEnabled(false);
                                    }
                                    if (currentConnectionClass.getRelativeBandwidth() > (currentConnectionClass.getMinimumRelativeBandwidth() + 9) / 10 * 10) {
                                        JAPConfForwardingServer.this.decreaseRelativeBandwidthButton.setEnabled(true);
                                    }
                                    else {
                                        JAPConfForwardingServer.this.decreaseRelativeBandwidthButton.setEnabled(false);
                                    }
                                }
                                if (((JAPRoutingMessage)o).getMessageCode() == 7) {
                                    JAPConfForwardingServer.this.settingsForwardingServerConfigCurrentBandwidthLabel.setText(JAPMessages.getString("settingsForwardingServerConfigCurrentBandwidthLabelPart1") + " " + Integer.toString(JAPModel.getInstance().getRoutingSettings().getBandwidth() * 8 / 1000) + " " + JAPMessages.getString("settingsForwardingServerConfigCurrentBandwidthLabelPart2") + " " + Integer.toString(JAPModel.getInstance().getRoutingSettings().getAllowedConnections()) + " " + JAPMessages.getString("settingsForwardingServerConfigCurrentBandwidthLabelPart3"));
                                }
                            }
                        }
                        catch (Exception ex) {
                            LogHolder.log(2, LogType.GUI, ex);
                        }
                    }
                };
                JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().addObserver(observer3);
                observer3.update(JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector(), new JAPRoutingMessage(7));
                final Observer observer4 = new Observer() {
                    public void update(final Observable observable, final Object o) {
                        try {
                            if (observable == JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore()) {
                                final int messageCode = ((JAPRoutingMessage)o).getMessageCode();
                                if (messageCode == 9) {
                                    if (JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore().getAllowAllAvailableMixCascades()) {
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesKnownCascadesLabel.setEnabled(false);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesAllowedCascadesLabel.setEnabled(false);
                                        JAPConfForwardingServer.this.knownCascadesList.setEnabled(false);
                                        JAPConfForwardingServer.this.allowedCascadesList.setEnabled(false);
                                        JAPConfForwardingServer.this.knownCascadesList.setModel(new DefaultListModel());
                                        JAPConfForwardingServer.this.allowedCascadesList.setModel(new DefaultListModel());
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesReloadButton.setEnabled(false);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesAddButton.setEnabled(false);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesRemoveButton.setEnabled(false);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesAllowAllBox.setSelected(true);
                                    }
                                    else {
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesKnownCascadesLabel.setEnabled(true);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesAllowedCascadesLabel.setEnabled(true);
                                        JAPConfForwardingServer.this.knownCascadesList.setModel(JAPConfForwardingServer.this.m_knownCascadesListModel);
                                        JAPConfForwardingServer.this.allowedCascadesList.setModel(JAPConfForwardingServer.this.m_allowedCascadesListModel);
                                        JAPConfForwardingServer.this.knownCascadesList.setEnabled(true);
                                        JAPConfForwardingServer.this.allowedCascadesList.setEnabled(true);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesReloadButton.setEnabled(true);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesAddButton.setEnabled(true);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesRemoveButton.setEnabled(true);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesAllowAllBox.setSelected(false);
                                    }
                                }
                                if (messageCode == 10) {
                                    synchronized (JAPConfForwardingServer.this.m_allowedCascadesListModel) {
                                        JAPConfForwardingServer.this.m_allowedCascadesListModel.clear();
                                        final Enumeration<Object> elements = JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore().getAllowedMixCascades().elements();
                                        while (elements.hasMoreElements()) {
                                            JAPConfForwardingServer.this.m_allowedCascadesListModel.addElement(elements.nextElement());
                                        }
                                    }
                                }
                            }
                        }
                        catch (Exception ex) {
                            LogHolder.log(2, LogType.GUI, ex);
                        }
                    }
                };
                JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore().addObserver(observer4);
                observer4.update(JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore(), new JAPRoutingMessage(10));
                observer4.update(JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore(), new JAPRoutingMessage(9));
                final Observer observer5 = new Observer() {
                    public void update(final Observable observable, final Object o) {
                        try {
                            if (observable == JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore()) {
                                final int messageCode = ((JAPRoutingMessage)o).getMessageCode();
                                if (messageCode == 11) {
                                    if (JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore().getRegisterAtAllAvailableInfoServices()) {
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesKnownInfoServicesLabel.setEnabled(false);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesSelectedInfoServicesLabel.setEnabled(false);
                                        JAPConfForwardingServer.this.knownInfoServicesList.setEnabled(false);
                                        JAPConfForwardingServer.this.registrationInfoServicesList.setEnabled(false);
                                        JAPConfForwardingServer.this.knownInfoServicesList.setModel(new DefaultListModel());
                                        JAPConfForwardingServer.this.registrationInfoServicesList.setModel(new DefaultListModel());
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesReloadButton.setEnabled(false);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesAddButton.setEnabled(false);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesRemoveButton.setEnabled(false);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesRegisterAtAllBox.setSelected(true);
                                    }
                                    else {
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesKnownInfoServicesLabel.setEnabled(true);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesSelectedInfoServicesLabel.setEnabled(true);
                                        JAPConfForwardingServer.this.knownInfoServicesList.setModel(JAPConfForwardingServer.this.m_knownInfoServicesListModel);
                                        JAPConfForwardingServer.this.registrationInfoServicesList.setModel(JAPConfForwardingServer.this.m_registrationInfoServicesListModel);
                                        JAPConfForwardingServer.this.knownInfoServicesList.setEnabled(true);
                                        JAPConfForwardingServer.this.registrationInfoServicesList.setEnabled(true);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesReloadButton.setEnabled(true);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesAddButton.setEnabled(true);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesRemoveButton.setEnabled(true);
                                        JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesRegisterAtAllBox.setSelected(false);
                                    }
                                }
                                if (messageCode == 12) {
                                    synchronized (JAPConfForwardingServer.this.m_registrationInfoServicesListModel) {
                                        JAPConfForwardingServer.this.m_registrationInfoServicesListModel.clear();
                                        final Enumeration<Object> elements = JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore().getRegistrationInfoServices().elements();
                                        while (elements.hasMoreElements()) {
                                            JAPConfForwardingServer.this.m_registrationInfoServicesListModel.addElement(elements.nextElement());
                                        }
                                    }
                                }
                            }
                        }
                        catch (Exception ex) {
                            LogHolder.log(2, LogType.GUI, ex);
                        }
                    }
                };
                JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore().addObserver(observer5);
                observer5.update(JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore(), new JAPRoutingMessage(12));
                observer5.update(JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore(), new JAPRoutingMessage(11));
                return true;
            }
        }
        return false;
    }
    
    public void recreateRootPanel() {
        final JPanel rootPanel = this.getRootPanel();
        synchronized (this) {
            rootPanel.removeAll();
            final JPanel forwardingServerConfigPanel = this.createForwardingServerConfigPanel();
            final GridBagLayout layout = new GridBagLayout();
            rootPanel.setLayout(layout);
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            layout.setConstraints(forwardingServerConfigPanel, gridBagConstraints);
            rootPanel.add(forwardingServerConfigPanel);
        }
    }
    
    public String getTabTitle() {
        return JAPMessages.getString("confTreeForwardingServerLeaf");
    }
    
    private JPanel createForwardingServerConfigPanel() {
        final JPanel panel = new JPanel();
        final JLabel label = new JLabel(JAPMessages.getString("settingsForwardingServerConfigPortLabel"));
        (this.serverPortField = new JTextField(5) {
            private static final long serialVersionUID = 1L;
            
            protected Document createDefaultModel() {
                return new PlainDocument() {
                    private static final long serialVersionUID = 1L;
                    
                    public void insertString(final int n, final String s, final AttributeSet set) throws BadLocationException {
                        try {
                            final int int1 = Integer.parseInt(this.getText(0, this.getLength()) + s);
                            if (int1 >= 1 && int1 <= 65535) {
                                super.insertString(n, s, set);
                            }
                        }
                        catch (NumberFormatException ex) {}
                    }
                };
            }
        }).addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent focusEvent) {
                try {
                    if (!JAPModel.getInstance().getRoutingSettings().setServerPort(Integer.parseInt(JAPConfForwardingServer.this.serverPortField.getText()))) {
                        throw new Exception("Error while changing server port.");
                    }
                }
                catch (Exception ex) {
                    JAPDialog.showErrorDialog(panel, JAPMessages.getString("settingsForwardingServerConfigChangeServerPortError"), LogType.MISC);
                    JAPConfForwardingServer.this.serverPortField.setText(Integer.toString(JAPModel.getInstance().getRoutingSettings().getServerPort()));
                }
            }
        });
        final JLabel label2 = new JLabel(JAPMessages.getString("settingsForwardingServerConfigMyConnectionLabel"));
        (this.connectionClassesComboBox = new JComboBox(JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().getConnectionClasses())).setEditable(false);
        this.connectionClassesComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().setCurrentConnectionClass(((JAPRoutingConnectionClass)JAPConfForwardingServer.this.connectionClassesComboBox.getSelectedItem()).getIdentifier());
            }
        });
        final JLabel label3 = new JLabel(JAPMessages.getString("settingsForwardingServerConfigMaxUploadBandwidthLabel"));
        (this.uploadBandwidthField = new JTextField() {
            private static final long serialVersionUID = 1L;
            
            protected Document createDefaultModel() {
                return new PlainDocument() {
                    private static final long serialVersionUID = 1L;
                    
                    public void insertString(final int n, final String s, final AttributeSet set) throws BadLocationException {
                        try {
                            if (Integer.parseInt(this.getText(0, this.getLength()) + s) >= 1) {
                                super.insertString(n, s, set);
                            }
                        }
                        catch (NumberFormatException ex) {}
                    }
                };
            }
        }).addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent focusEvent) {
                try {
                    final int maximumBandwidth = Integer.parseInt(JAPConfForwardingServer.this.uploadBandwidthField.getText()) * 1000 / 8;
                    if (maximumBandwidth < 4000) {
                        throw new Exception("JAPConfForwardingServer: Error while changing maximum upload bandwidth.");
                    }
                    final JAPRoutingConnectionClass currentConnectionClass = JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().getCurrentConnectionClass();
                    currentConnectionClass.setMaximumBandwidth(maximumBandwidth);
                    JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().setCurrentConnectionClass(currentConnectionClass.getIdentifier());
                }
                catch (Exception ex) {
                    JAPDialog.showErrorDialog(panel, JAPMessages.getString("settingsForwardingServerConfigChangeMaximumUploadBandwidthErrorPart1") + " " + Integer.toString(32) + " " + JAPMessages.getString("settingsForwardingServerConfigChangeMaximumUploadBandwidthErrorPart2"), LogType.MISC);
                    JAPConfForwardingServer.this.uploadBandwidthField.setText(Integer.toString(JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().getCurrentConnectionClass().getMaximumBandwidth() * 8 / 1000));
                }
            }
        });
        this.uploadBandwidthField.setColumns(7);
        final JLabel label4 = new JLabel(JAPMessages.getString("settingsForwardingServerConfigForwardingPercentageLabel"));
        (this.relativeBandwidthField = new JTextField()).setColumns(4);
        this.relativeBandwidthField.setHorizontalAlignment(4);
        this.relativeBandwidthField.setDisabledTextColor(this.relativeBandwidthField.getForeground());
        this.relativeBandwidthField.setEnabled(false);
        (this.increaseRelativeBandwidthButton = new JButton(GUIUtils.loadImageIcon("arrowUp.gif", true))).setBorder(new EmptyBorder(0, 1, 0, 1));
        this.increaseRelativeBandwidthButton.setFocusPainted(false);
        this.increaseRelativeBandwidthButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final JAPRoutingConnectionClass currentConnectionClass = JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().getCurrentConnectionClass();
                currentConnectionClass.setRelativeBandwidth(Math.min(100, ((currentConnectionClass.getRelativeBandwidth() + 9) / 10 + 1) * 10));
                JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().setCurrentConnectionClass(currentConnectionClass.getIdentifier());
            }
        });
        (this.decreaseRelativeBandwidthButton = new JButton(GUIUtils.loadImageIcon("arrowDown.gif", true))).setBorder(new EmptyBorder(0, 1, 0, 1));
        this.decreaseRelativeBandwidthButton.setFocusPainted(false);
        this.decreaseRelativeBandwidthButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final JAPRoutingConnectionClass currentConnectionClass = JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().getCurrentConnectionClass();
                currentConnectionClass.setRelativeBandwidth((Math.max(currentConnectionClass.getMinimumRelativeBandwidth(), currentConnectionClass.getRelativeBandwidth() - 10) + 9) / 10 * 10);
                JAPModel.getInstance().getRoutingSettings().getConnectionClassSelector().setCurrentConnectionClass(currentConnectionClass.getIdentifier());
            }
        });
        this.settingsForwardingServerConfigCurrentBandwidthLabel = new JLabel();
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.insertTab(JAPMessages.getString("settingsForwardingServerConfigAllowedCascadesTabTitle"), null, this.createForwardingServerConfigAllowedCascadesPanel(), null, 0);
        tabbedPane.insertTab(JAPMessages.getString("settingsForwardingServerConfigRegistrationInfoServicesTabTitle"), null, this.createForwardingServerConfigRegistrationInfoServicesPanel(), null, 1);
        final JPanel panel2 = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        panel2.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 17;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 20, 5);
        layout.setConstraints(label, gridBagConstraints);
        panel2.add(label);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(5, 0, 20, 5);
        layout.setConstraints(this.serverPortField, gridBagConstraints);
        panel2.add(this.serverPortField);
        this.m_startServerBox = new JCheckBox(JAPMessages.getString("forwardingServerStart"), JAPModel.getInstance().getRoutingSettings().getRoutingMode() == 2);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.anchor = 12;
        this.m_startServerBox.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPController.getInstance().enableForwardingServer(JAPConfForwardingServer.this.m_startServerBox.isSelected());
            }
        });
        gridBagConstraints.anchor = 18;
        panel.setBorder(new TitledBorder(JAPMessages.getString("settingsForwardingServerConfigBorder")));
        final GridBagLayout layout2 = new GridBagLayout();
        panel.setLayout(layout2);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.anchor = 18;
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridwidth = 4;
        layout2.setConstraints(panel2, gridBagConstraints2);
        panel.add(panel2);
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.insets = new Insets(0, 5, 0, 10);
        layout2.setConstraints(label2, gridBagConstraints2);
        panel.add(label2);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.insets = new Insets(0, 10, 0, 10);
        layout2.setConstraints(label3, gridBagConstraints2);
        panel.add(label3);
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.insets = new Insets(0, 10, 0, 5);
        layout2.setConstraints(label4, gridBagConstraints2);
        panel.add(label4);
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.fill = 3;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.insets = new Insets(0, 5, 10, 10);
        layout2.setConstraints(this.connectionClassesComboBox, gridBagConstraints2);
        panel.add(this.connectionClassesComboBox);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.insets = new Insets(0, 10, 10, 10);
        layout2.setConstraints(this.uploadBandwidthField, gridBagConstraints2);
        panel.add(this.uploadBandwidthField);
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.insets = new Insets(0, 10, 10, 0);
        layout2.setConstraints(this.relativeBandwidthField, gridBagConstraints2);
        panel.add(this.relativeBandwidthField);
        final JPanel panel3 = new JPanel(new GridLayout(2, 1, 0, 0));
        panel3.add(this.increaseRelativeBandwidthButton);
        panel3.add(this.decreaseRelativeBandwidthButton);
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 3;
        gridBagConstraints2.anchor = 16;
        gridBagConstraints2.insets = new Insets(0, 0, 10, 5);
        panel.add(panel3, gridBagConstraints2);
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 4;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.gridwidth = 4;
        gridBagConstraints2.insets = new Insets(0, 5, 20, 5);
        layout2.setConstraints(this.settingsForwardingServerConfigCurrentBandwidthLabel, gridBagConstraints2);
        panel.add(this.settingsForwardingServerConfigCurrentBandwidthLabel);
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 5;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.insets = new Insets(0, 5, 5, 5);
        layout2.setConstraints(tabbedPane, gridBagConstraints2);
        panel.add(tabbedPane);
        return panel;
    }
    
    private JPanel createForwardingServerConfigAllowedCascadesPanel() {
        final JPanel panel = new JPanel();
        this.settingsForwardingServerConfigAllowedCascadesKnownCascadesLabel = new JLabel(JAPMessages.getString("settingsForwardingServerConfigAllowedCascadesKnownCascadesLabel"));
        this.settingsForwardingServerConfigAllowedCascadesAllowedCascadesLabel = new JLabel(JAPMessages.getString("settingsForwardingServerConfigAllowedCascadesAllowedCascadesLabel"));
        this.m_knownCascadesListModel = new DefaultListModel();
        this.m_knownInfoServicesListModel = new DefaultListModel();
        (this.knownCascadesList = new JList(this.m_knownCascadesListModel)).setSelectionMode(0);
        final JScrollPane scrollPane = new JScrollPane(this.knownCascadesList);
        scrollPane.setPreferredSize(new JTextArea(4, 20).getPreferredSize());
        this.m_allowedCascadesListModel = new DefaultListModel();
        (this.allowedCascadesList = new JList(this.m_allowedCascadesListModel)).setSelectionMode(0);
        final JScrollPane scrollPane2 = new JScrollPane(this.allowedCascadesList);
        scrollPane2.setPreferredSize(new JTextArea(4, 20).getPreferredSize());
        (this.settingsForwardingServerConfigAllowedCascadesReloadButton = new JButton(JAPMessages.getString("settingsForwardingServerConfigAllowedCascadesReloadButton"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                synchronized (JAPConfForwardingServer.this.m_knownCascadesListModel) {
                    JAPConfForwardingServer.this.m_knownCascadesListModel.clear();
                    final Enumeration<Object> elements = JAPConfForwardingServer.this.showFetchMixCascadesDialog(panel).elements();
                    while (elements.hasMoreElements()) {
                        JAPConfForwardingServer.this.m_knownCascadesListModel.addElement(elements.nextElement());
                    }
                }
            }
        });
        (this.settingsForwardingServerConfigAllowedCascadesAddButton = new JButton(JAPMessages.getString("settingsForwardingServerConfigAllowedCascadesAddButton"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final MixCascade mixCascade = JAPConfForwardingServer.this.knownCascadesList.getSelectedValue();
                if (mixCascade != null) {
                    JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore().addToAllowedMixCascades(mixCascade);
                    JAPConfForwardingServer.this.m_knownCascadesListModel.removeElement(mixCascade);
                }
            }
        });
        (this.settingsForwardingServerConfigAllowedCascadesRemoveButton = new JButton(JAPMessages.getString("settingsForwardingServerConfigAllowedCascadesRemoveButton"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final MixCascade mixCascade = JAPConfForwardingServer.this.allowedCascadesList.getSelectedValue();
                if (mixCascade != null) {
                    JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore().removeFromAllowedMixCascades(mixCascade.getId());
                    JAPConfForwardingServer.this.m_knownCascadesListModel.addElement(mixCascade);
                }
            }
        });
        (this.settingsForwardingServerConfigAllowedCascadesAllowAllBox = new JCheckBox(JAPMessages.getString("settingsForwardingServerConfigAllowedCascadesAllowAllBox"), JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore().getAllowAllAvailableMixCascades())).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPModel.getInstance().getRoutingSettings().getUseableMixCascadesStore().setAllowAllAvailableMixCascades(JAPConfForwardingServer.this.settingsForwardingServerConfigAllowedCascadesAllowAllBox.isSelected());
            }
        });
        panel.setBorder(new TitledBorder(JAPMessages.getString("settingsForwardingServerConfigAllowedCascadesBorder")));
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(0, 5, 10, 5);
        layout.setConstraints(this.settingsForwardingServerConfigAllowedCascadesAllowAllBox, gridBagConstraints);
        panel.add(this.settingsForwardingServerConfigAllowedCascadesAllowAllBox);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(this.settingsForwardingServerConfigAllowedCascadesKnownCascadesLabel, gridBagConstraints);
        panel.add(this.settingsForwardingServerConfigAllowedCascadesKnownCascadesLabel);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(this.settingsForwardingServerConfigAllowedCascadesAllowedCascadesLabel, gridBagConstraints);
        panel.add(this.settingsForwardingServerConfigAllowedCascadesAllowedCascadesLabel);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(this.settingsForwardingServerConfigAllowedCascadesReloadButton, gridBagConstraints);
        panel.add(this.settingsForwardingServerConfigAllowedCascadesReloadButton);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(scrollPane, gridBagConstraints);
        panel.add(scrollPane);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(scrollPane2, gridBagConstraints);
        panel.add(scrollPane2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.settingsForwardingServerConfigAllowedCascadesAddButton, gridBagConstraints);
        panel.add(this.settingsForwardingServerConfigAllowedCascadesAddButton);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.settingsForwardingServerConfigAllowedCascadesRemoveButton, gridBagConstraints);
        panel.add(this.settingsForwardingServerConfigAllowedCascadesRemoveButton);
        return panel;
    }
    
    private JPanel createForwardingServerConfigRegistrationInfoServicesPanel() {
        final JPanel panel = new JPanel();
        this.settingsForwardingServerConfigRegistrationInfoServicesKnownInfoServicesLabel = new JLabel(JAPMessages.getString("settingsForwardingServerConfigRegistrationInfoServicesKnownInfoServicesLabel"));
        this.settingsForwardingServerConfigRegistrationInfoServicesSelectedInfoServicesLabel = new JLabel(JAPMessages.getString("settingsForwardingServerConfigRegistrationInfoServicesSelectedInfoServicesLabel"));
        (this.knownInfoServicesList = new JList(this.m_knownInfoServicesListModel)).setSelectionMode(0);
        final JScrollPane scrollPane = new JScrollPane(this.knownInfoServicesList);
        scrollPane.setPreferredSize(new JTextArea(4, 20).getPreferredSize());
        this.m_registrationInfoServicesListModel = new DefaultListModel();
        (this.registrationInfoServicesList = new JList(this.m_registrationInfoServicesListModel)).setSelectionMode(0);
        final JScrollPane scrollPane2 = new JScrollPane(this.registrationInfoServicesList);
        scrollPane2.setPreferredSize(new JTextArea(4, 20).getPreferredSize());
        (this.settingsForwardingServerConfigRegistrationInfoServicesReloadButton = new JButton(JAPMessages.getString("settingsForwardingServerConfigRegistrationInfoServicesReloadButton"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPConfForwardingServer.this.startLoadInfoServicesThread();
            }
        });
        (this.settingsForwardingServerConfigRegistrationInfoServicesAddButton = new JButton(JAPMessages.getString("settingsForwardingServerConfigRegistrationInfoServicesAddButton"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final InfoServiceDBEntry infoServiceDBEntry = JAPConfForwardingServer.this.knownInfoServicesList.getSelectedValue();
                if (infoServiceDBEntry != null) {
                    JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore().addToRegistrationInfoServices(infoServiceDBEntry);
                    JAPConfForwardingServer.this.m_knownInfoServicesListModel.removeElement(infoServiceDBEntry);
                }
            }
        });
        (this.settingsForwardingServerConfigRegistrationInfoServicesRemoveButton = new JButton(JAPMessages.getString("settingsForwardingServerConfigRegistrationInfoServicesRemoveButton"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final InfoServiceDBEntry infoServiceDBEntry = JAPConfForwardingServer.this.registrationInfoServicesList.getSelectedValue();
                if (infoServiceDBEntry != null) {
                    JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore().removeFromRegistrationInfoServices(infoServiceDBEntry.getId());
                    JAPConfForwardingServer.this.m_knownInfoServicesListModel.addElement(infoServiceDBEntry);
                }
            }
        });
        (this.settingsForwardingServerConfigRegistrationInfoServicesRegisterAtAllBox = new JCheckBox(JAPMessages.getString("settingsForwardingServerConfigRegistrationInfoServicesRegisterAtAllBox"), JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore().getRegisterAtAllAvailableInfoServices())).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPModel.getInstance().getRoutingSettings().getRegistrationInfoServicesStore().setRegisterAtAllAvailableInfoServices(JAPConfForwardingServer.this.settingsForwardingServerConfigRegistrationInfoServicesRegisterAtAllBox.isSelected());
            }
        });
        panel.setBorder(new TitledBorder(JAPMessages.getString("settingsForwardingServerConfigRegistrationInfoServicesBorder")));
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(0, 5, 10, 5);
        layout.setConstraints(this.settingsForwardingServerConfigRegistrationInfoServicesRegisterAtAllBox, gridBagConstraints);
        panel.add(this.settingsForwardingServerConfigRegistrationInfoServicesRegisterAtAllBox);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(this.settingsForwardingServerConfigRegistrationInfoServicesKnownInfoServicesLabel, gridBagConstraints);
        panel.add(this.settingsForwardingServerConfigRegistrationInfoServicesKnownInfoServicesLabel);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(this.settingsForwardingServerConfigRegistrationInfoServicesSelectedInfoServicesLabel, gridBagConstraints);
        panel.add(this.settingsForwardingServerConfigRegistrationInfoServicesSelectedInfoServicesLabel);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(this.settingsForwardingServerConfigRegistrationInfoServicesReloadButton, gridBagConstraints);
        panel.add(this.settingsForwardingServerConfigRegistrationInfoServicesReloadButton);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(scrollPane, gridBagConstraints);
        panel.add(scrollPane);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(scrollPane2, gridBagConstraints);
        panel.add(scrollPane2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.settingsForwardingServerConfigRegistrationInfoServicesAddButton, gridBagConstraints);
        panel.add(this.settingsForwardingServerConfigRegistrationInfoServicesAddButton);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(this.settingsForwardingServerConfigRegistrationInfoServicesRemoveButton, gridBagConstraints);
        panel.add(this.settingsForwardingServerConfigRegistrationInfoServicesRemoveButton);
        return panel;
    }
    
    private Vector showFetchMixCascadesDialog(final JComponent component) {
        final JAPDialog japDialog = new JAPDialog(component, JAPMessages.getString("settingsForwardingServerConfigAllowedCascadesFetchMixCascadesDialogTitle"));
        japDialog.setResizable(false);
        japDialog.setDefaultCloseOperation(2);
        final Vector vector = new Vector();
        final Vector vector2 = new Vector();
        final WorkerContentPane workerContentPane = new WorkerContentPane(japDialog, JAPMessages.getString("settingsForwardingServerConfigAllowedCascadesFetchMixCascadesDialogFetchLabel"), new Runnable() {
            static /* synthetic */ Class class$anon$infoservice$MixCascade;
            
            public void run() {
                Hashtable<Object, MixCascade> mixCascades = (Hashtable<Object, MixCascade>)InfoServiceHolder.getInstance().getMixCascades();
                Thread.interrupted();
                if (mixCascades == null) {
                    vector2.addElement(new NullPointerException());
                    mixCascades = new Hashtable<Object, MixCascade>();
                }
                final Enumeration<MixCascade> elements = mixCascades.elements();
                while (elements.hasMoreElements()) {
                    final MixCascade mixCascade = elements.nextElement();
                    if (JAPConfForwardingServer.this.m_allowedCascadesListModel != null && !JAPConfForwardingServer.this.m_allowedCascadesListModel.contains(mixCascade)) {
                        vector.addElement(mixCascade);
                    }
                }
                final Enumeration entrySnapshotAsEnumeration = Database.getInstance((JAPConfForwardingServer$24.class$anon$infoservice$MixCascade == null) ? (JAPConfForwardingServer$24.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPConfForwardingServer$24.class$anon$infoservice$MixCascade).getEntrySnapshotAsEnumeration();
                while (entrySnapshotAsEnumeration.hasMoreElements()) {
                    final MixCascade mixCascade2 = entrySnapshotAsEnumeration.nextElement();
                    if (mixCascade2.isUserDefined()) {
                        vector.addElement(mixCascade2);
                    }
                }
            }
            
            static /* synthetic */ Class class$(final String s) {
                try {
                    return Class.forName(s);
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
        });
        workerContentPane.setInterruptThreadSafe(false);
        workerContentPane.updateDialog();
        japDialog.pack();
        japDialog.setVisible(true);
        if (vector2.size() > 0) {
            JAPDialog.showErrorDialog(component, JAPMessages.getString("settingsForwardingServerConfigAllowedCascadesFetchMixCascadesDialogFetchCascadesError"), LogType.NET);
        }
        return vector;
    }
    
    public String getHelpContext() {
        return "forwarding_server";
    }
    
    protected void onRootPanelShown() {
        if (!JAPModel.isInfoServiceDisabled()) {
            this.fillLists();
        }
        this.m_startServerBox.setSelected(JAPModel.getInstance().getRoutingSettings().getRoutingMode() == 2);
    }
    
    private void fillLists() {
        this.m_knownCascadesListModel.clear();
        this.m_knownInfoServicesListModel.clear();
        final Enumeration entrySnapshotAsEnumeration = Database.getInstance((JAPConfForwardingServer.class$anon$infoservice$MixCascade == null) ? (JAPConfForwardingServer.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : JAPConfForwardingServer.class$anon$infoservice$MixCascade).getEntrySnapshotAsEnumeration();
        final MixCascade currentMixCascade = JAPController.getInstance().getCurrentMixCascade();
        boolean b = false;
        while (entrySnapshotAsEnumeration.hasMoreElements()) {
            final MixCascade mixCascade = entrySnapshotAsEnumeration.nextElement();
            if (this.m_allowedCascadesListModel != null && !this.m_allowedCascadesListModel.contains(mixCascade)) {
                this.m_knownCascadesListModel.addElement(mixCascade);
            }
            if (mixCascade.equals(currentMixCascade)) {
                b = true;
            }
        }
        if (!b) {
            this.m_knownCascadesListModel.addElement(currentMixCascade);
        }
        this.startLoadInfoServicesThread();
    }
    
    private void startLoadInfoServicesThread() {
        new Thread(new Runnable() {
            public void run() {
                JAPConfForwardingServer.this.loadInfoServices();
            }
        }).start();
    }
    
    private synchronized void loadInfoServices() {
        synchronized (InfoServiceHolder.getInstance()) {
            this.m_knownInfoServicesListModel.clear();
            final Vector infoservicesWithForwarderList = InfoServiceHolder.getInstance().getInfoservicesWithForwarderList();
            if (infoservicesWithForwarderList != null) {
                final Enumeration<InfoServiceDBEntry> elements = infoservicesWithForwarderList.elements();
                while (elements.hasMoreElements()) {
                    final InfoServiceDBEntry infoServiceDBEntry = elements.nextElement();
                    if (this.m_registrationInfoServicesListModel != null && !this.m_registrationInfoServicesListModel.contains(infoServiceDBEntry)) {
                        this.m_knownInfoServicesListModel.addElement(infoServiceDBEntry);
                    }
                }
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
