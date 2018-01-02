// 
// Decompiled by Procyon v0.5.30
// 

package jap.forward;

import anon.transport.address.AddressParameter;
import anon.transport.address.IAddress;
import java.awt.Insets;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import logging.LogHolder;
import logging.LogType;
import java.util.Vector;
import jap.JAPModel;
import java.util.Observable;
import java.util.Observer;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import gui.JAPHtmlMultiLineLabel;
import java.text.NumberFormat;
import anon.util.JAPMessages;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import jap.IJAPConfSavePoint;
import jap.MessageSystem;
import jap.AbstractJAPConfModule;

public class JAPConfForwardingState extends AbstractJAPConfModule
{
    private MessageSystem m_messageSystem;
    
    public JAPConfForwardingState() {
        super(null);
    }
    
    public String getHelpContext() {
        return null;
    }
    
    public void recreateRootPanel() {
        synchronized (this) {
            if (this.m_messageSystem == null) {
                this.m_messageSystem = new MessageSystem();
            }
        }
        final JPanel rootPanel = this.getRootPanel();
        synchronized (this) {
            rootPanel.removeAll();
            this.m_messageSystem.sendMessage();
            final JPanel forwardingStatePanel = this.createForwardingStatePanel();
            final GridBagLayout layout = new GridBagLayout();
            rootPanel.setLayout(layout);
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 18;
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            layout.setConstraints(forwardingStatePanel, gridBagConstraints);
            rootPanel.add(forwardingStatePanel);
        }
    }
    
    public String getTabTitle() {
        return JAPMessages.getString("confTreeForwardingStateLeaf");
    }
    
    private JPanel createForwardingServerStatePanel() {
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMinimumFractionDigits(1);
        instance.setMaximumFractionDigits(1);
        instance.setMinimumIntegerDigits(1);
        final NumberFormat instance2 = NumberFormat.getInstance();
        instance.setMinimumIntegerDigits(1);
        final JAPHtmlMultiLineLabel japHtmlMultiLineLabel = new JAPHtmlMultiLineLabel("");
        final JLabel label = new JLabel();
        final JLabel label2 = new JLabel();
        final JLabel label3 = new JLabel();
        final JLabel label4 = new JLabel(JAPMessages.getString("settingsRoutingServerStatusStatisticsConnectionsLabel"));
        final JLabel label5 = new JLabel();
        final JLabel label6 = new JLabel();
        final JLabel label7 = new JLabel();
        final JLabel label8 = new JLabel(JAPMessages.getString("settingsRoutingServerStatusInfoServiceRegistrationsLabel"));
        final JAPRoutingInfoServiceRegistrationTableModel japRoutingInfoServiceRegistrationTableModel = new JAPRoutingInfoServiceRegistrationTableModel();
        final JTable table = new JTable(japRoutingInfoServiceRegistrationTableModel);
        table.getColumnModel().getColumn(1).setMaxWidth(125);
        table.getColumnModel().getColumn(1).setPreferredWidth(125);
        table.setEnabled(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        final JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 50));
        final Observer observer = new Observer() {
            public void update(final Observable observable, final Object o) {
                try {
                    if (observable == JAPModel.getInstance().getRoutingSettings().getServerStatisticsListener() && ((JAPRoutingMessage)o).getMessageCode() == 13) {
                        label2.setText(JAPMessages.getString("settingsRoutingServerStatusStatisticsBandwidthLabelPart1") + " " + instance.format(JAPModel.getInstance().getRoutingSettings().getServerStatisticsListener().getCurrentBandwidthUsage() / 1024.0) + " " + JAPMessages.getString("settingsRoutingServerStatusStatisticsBandwidthLabelPart2"));
                        label3.setText(JAPMessages.getString("settingsRoutingServerStatusStatisticsForwardedBytesLabel") + " " + instance2.format(JAPModel.getInstance().getRoutingSettings().getServerStatisticsListener().getTransferedBytes()));
                        label5.setText(JAPMessages.getString("settingsRoutingServerStatusStatisticsCurrentConnectionsLabel") + " " + instance2.format(JAPModel.getInstance().getRoutingSettings().getServerStatisticsListener().getCurrentlyForwardedConnections()));
                        label6.setText(JAPMessages.getString("settingsRoutingServerStatusStatisticsAcceptedConnectionsLabel") + " " + instance2.format(JAPModel.getInstance().getRoutingSettings().getServerStatisticsListener().getAcceptedConnections()));
                        label7.setText(JAPMessages.getString("settingsRoutingServerStatusStatisticsRejectedConnectionsLabel") + " " + instance2.format(JAPModel.getInstance().getRoutingSettings().getServerStatisticsListener().getRejectedConnections()));
                    }
                    if (observable == JAPModel.getInstance().getRoutingSettings().getRegistrationStatusObserver() && ((JAPRoutingMessage)o).getMessageCode() == 14) {
                        final int currentState = JAPModel.getInstance().getRoutingSettings().getRegistrationStatusObserver().getCurrentState();
                        final int currentErrorCode = JAPModel.getInstance().getRoutingSettings().getRegistrationStatusObserver().getCurrentErrorCode();
                        if (currentState == 0) {
                            japHtmlMultiLineLabel.setText(JAPMessages.getString("settingsRoutingServerStatusLabelStateRegistrationDisabled"));
                        }
                        else if (currentState == 1) {
                            japHtmlMultiLineLabel.setText(JAPMessages.getString("settingsRoutingServerStatusLabelStateRegistrationInitiated"));
                        }
                        else if (currentState == 2) {
                            japHtmlMultiLineLabel.setText(JAPMessages.getString("settingsRoutingServerStatusLabelStateRegistrationFailed"));
                        }
                        else if (currentState == 3) {
                            japHtmlMultiLineLabel.setText(JAPMessages.getString("settingsRoutingServerStatusLabelStateRegistrationSuccessful"));
                        }
                        if (currentErrorCode == 0) {
                            label.setText(" ");
                        }
                        else if (currentErrorCode == 1) {
                            label.setText(JAPMessages.getString("settingsRoutingServerStatusRegistrationErrorLabelNoKnownInfoServices"));
                        }
                        else if (currentErrorCode == 2) {
                            label.setText(JAPMessages.getString("settingsRoutingServerStatusRegistrationErrorLabelConnectionFailed"));
                        }
                        else if (currentErrorCode == 3) {
                            label.setText(JAPMessages.getString("settingsRoutingServerStatusRegistrationErrorLabelVerificationFailed"));
                        }
                        else if (currentErrorCode == 4) {
                            label.setText(JAPMessages.getString("settingsRoutingServerStatusRegistrationErrorLabelUnknownReason"));
                        }
                    }
                    if (observable == JAPModel.getInstance().getRoutingSettings() && ((JAPRoutingMessage)o).getMessageCode() == 2) {
                        japRoutingInfoServiceRegistrationTableModel.updatePropagandaInstancesList((Vector)((JAPRoutingMessage)o).getMessageData());
                    }
                    if (observable == JAPConfForwardingState.this.m_messageSystem) {
                        JAPModel.getInstance().getRoutingSettings().getServerStatisticsListener().deleteObserver(this);
                        JAPModel.getInstance().getRoutingSettings().deleteObserver(this);
                        JAPConfForwardingState.this.m_messageSystem.deleteObserver(this);
                        japRoutingInfoServiceRegistrationTableModel.clearPropagandaInstancesTable();
                    }
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.GUI, ex);
                }
            }
        };
        this.m_messageSystem.addObserver(observer);
        JAPModel.getInstance().getRoutingSettings().getServerStatisticsListener().addObserver(observer);
        JAPModel.getInstance().getRoutingSettings().getRegistrationStatusObserver().addObserver(observer);
        JAPModel.getInstance().getRoutingSettings().addObserver(observer);
        observer.update(JAPModel.getInstance().getRoutingSettings().getServerStatisticsListener(), new JAPRoutingMessage(13));
        observer.update(JAPModel.getInstance().getRoutingSettings().getRegistrationStatusObserver(), new JAPRoutingMessage(14));
        japRoutingInfoServiceRegistrationTableModel.updatePropagandaInstancesList(JAPModel.getInstance().getRoutingSettings().getRunningPropagandaInstances());
        final JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(JAPMessages.getString("settingsRoutingServerStatusBorder")));
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(japHtmlMultiLineLabel, gridBagConstraints);
        panel.add(japHtmlMultiLineLabel);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 5, 20, 5);
        layout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        layout.setConstraints(label8, gridBagConstraints);
        panel.add(label8);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        layout.setConstraints(scrollPane, gridBagConstraints);
        panel.add(scrollPane);
        final JPanel panel2 = new JPanel();
        panel2.setBorder(new TitledBorder(JAPMessages.getString("settingsRoutingServerStatusStatisticsBorder")));
        final GridBagLayout layout2 = new GridBagLayout();
        panel2.setLayout(layout2);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.anchor = 18;
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridwidth = 4;
        gridBagConstraints2.insets = new Insets(5, 5, 10, 5);
        layout2.setConstraints(label2, gridBagConstraints2);
        panel2.add(label2);
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.insets = new Insets(0, 5, 10, 5);
        layout2.setConstraints(label3, gridBagConstraints2);
        panel2.add(label3);
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.insets = new Insets(0, 5, 5, 15);
        layout2.setConstraints(label4, gridBagConstraints2);
        panel2.add(label4);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.insets = new Insets(0, 0, 5, 15);
        layout2.setConstraints(label5, gridBagConstraints2);
        panel2.add(label5);
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.insets = new Insets(0, 0, 5, 15);
        layout2.setConstraints(label6, gridBagConstraints2);
        panel2.add(label6);
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.insets = new Insets(0, 0, 5, 5);
        layout2.setConstraints(label7, gridBagConstraints2);
        panel2.add(label7);
        final JPanel panel3 = new JPanel();
        final GridBagLayout layout3 = new GridBagLayout();
        panel3.setLayout(layout3);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.anchor = 18;
        gridBagConstraints3.fill = 1;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        layout3.setConstraints(panel, gridBagConstraints3);
        panel3.add(panel);
        gridBagConstraints3.weighty = 0.0;
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        layout3.setConstraints(panel2, gridBagConstraints3);
        panel3.add(panel2);
        return panel3;
    }
    
    private JPanel createForwardingClientStatePanel() {
        final JPanel panel = new JPanel();
        final JLabel label = new JLabel(JAPMessages.getString("settingsRoutingClientStatusClientRunningLabel"));
        final JLabel label2 = new JLabel(JAPMessages.getString("settingsRoutingClientStatusConnectedViaLabel"));
        final JLabel label3 = new JLabel();
        final Observer observer = new Observer() {
            public void update(final Observable observable, final Object o) {
                try {
                    if (observable == JAPModel.getInstance().getRoutingSettings() && ((JAPRoutingMessage)o).getMessageCode() == 1 && JAPModel.getInstance().getRoutingSettings().getRoutingMode() == 1) {
                        final IAddress forwarderAddress = JAPModel.getInstance().getRoutingSettings().getForwarderAddress();
                        if (forwarderAddress != null) {
                            final AddressParameter[] allParameters = forwarderAddress.getAllParameters();
                            label3.setText(JAPMessages.getString("settingsRoutingClientStatusForwarderInformationLabelPart1") + " " + allParameters[0].getValue() + "    " + JAPMessages.getString("settingsRoutingClientStatusForwarderInformationLabelPart2") + " " + allParameters[1].getValue());
                        }
                        else {
                            label3.setText(JAPMessages.getString("settingsRoutingClientStatusForwarderInformationLabelInvalid"));
                        }
                    }
                    if (observable == JAPConfForwardingState.this.m_messageSystem) {
                        JAPModel.getInstance().getRoutingSettings().deleteObserver(this);
                        JAPConfForwardingState.this.m_messageSystem.deleteObserver(this);
                    }
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.GUI, ex);
                }
            }
        };
        this.m_messageSystem.addObserver(observer);
        JAPModel.getInstance().getRoutingSettings().addObserver(observer);
        observer.update(JAPModel.getInstance().getRoutingSettings(), new JAPRoutingMessage(1));
        panel.setBorder(new TitledBorder(JAPMessages.getString("settingsRoutingClientStatusBorder")));
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 10, 5);
        layout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 5, 2, 5);
        layout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 15, 5, 5);
        layout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        return panel;
    }
    
    private JPanel createForwardingDisabledStatePanel() {
        final JPanel panel = new JPanel();
        final JLabel label = new JLabel(JAPMessages.getString("settingsRoutingDisabledStatusNothingRunningLabel"));
        panel.setBorder(new TitledBorder(JAPMessages.getString("settingsRoutingDisabledStatusBorder")));
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        return panel;
    }
    
    private JPanel createForwardingStatePanel() {
        final JPanel panel = new JPanel();
        final JPanel forwardingServerStatePanel = this.createForwardingServerStatePanel();
        final JPanel forwardingClientStatePanel = this.createForwardingClientStatePanel();
        final JPanel forwardingDisabledStatePanel = this.createForwardingDisabledStatePanel();
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(forwardingServerStatePanel, gridBagConstraints);
        panel.add(forwardingServerStatePanel);
        layout.setConstraints(forwardingClientStatePanel, gridBagConstraints);
        panel.add(forwardingClientStatePanel);
        layout.setConstraints(forwardingDisabledStatePanel, gridBagConstraints);
        panel.add(forwardingDisabledStatePanel);
        panel.setPreferredSize(panel.getPreferredSize());
        final Observer observer = new Observer() {
            public void update(final Observable observable, final Object o) {
                try {
                    if (observable == JAPModel.getInstance().getRoutingSettings() && ((JAPRoutingMessage)o).getMessageCode() == 1) {
                        final int routingMode = JAPModel.getInstance().getRoutingSettings().getRoutingMode();
                        if (routingMode == 1) {
                            forwardingServerStatePanel.setVisible(false);
                            forwardingDisabledStatePanel.setVisible(false);
                            forwardingClientStatePanel.setVisible(true);
                        }
                        if (routingMode == 2) {
                            forwardingClientStatePanel.setVisible(false);
                            forwardingDisabledStatePanel.setVisible(false);
                            forwardingServerStatePanel.setVisible(true);
                        }
                        if (routingMode == 0) {
                            forwardingServerStatePanel.setVisible(false);
                            forwardingClientStatePanel.setVisible(false);
                            forwardingDisabledStatePanel.setVisible(true);
                        }
                    }
                    if (observable == JAPConfForwardingState.this.m_messageSystem) {
                        JAPModel.getInstance().getRoutingSettings().deleteObserver(this);
                        JAPConfForwardingState.this.m_messageSystem.deleteObserver(this);
                    }
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.GUI, ex);
                }
            }
        };
        this.m_messageSystem.addObserver(observer);
        JAPModel.getInstance().getRoutingSettings().addObserver(observer);
        observer.update(JAPModel.getInstance().getRoutingSettings(), new JAPRoutingMessage(1));
        return panel;
    }
}
