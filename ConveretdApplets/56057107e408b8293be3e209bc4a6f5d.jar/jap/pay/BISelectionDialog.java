// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import logging.LogHolder;
import logging.LogType;
import anon.pay.PayAccountsFile;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import anon.util.JAPMessages;
import java.awt.Component;
import anon.pay.PaymentInstanceDBEntry;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import gui.dialog.JAPDialog;

public class BISelectionDialog extends JAPDialog implements ActionListener, ListSelectionListener
{
    private JList m_biList;
    private JButton m_okButton;
    private JButton m_cancelButton;
    private JLabel m_biHost;
    private JLabel m_biPort;
    private PaymentInstanceDBEntry m_selectedBI;
    
    public BISelectionDialog(final Component component) {
        super(component, JAPMessages.getString("biSelectionDialog"), true);
        this.setDefaultCloseOperation(2);
        this.jbInit();
        this.setSize(500, 400);
        this.setVisible(true);
    }
    
    private void jbInit() {
        final JPanel panel = new JPanel(new GridBagLayout());
        final JPanel panel2 = new JPanel(new FlowLayout(0));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        (this.m_biList = new JList()).addListSelectionListener(this);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        panel.add(this.m_biList, gridBagConstraints);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridx = 3;
        panel.add(new JLabel(JAPMessages.getString("infoAboutBI")), gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        panel.add(new JLabel(JAPMessages.getString("biInfoHost")), gridBagConstraints);
        this.m_biHost = new JLabel();
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridx;
        panel.add(this.m_biHost, gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        --gridBagConstraints4.gridx;
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        ++gridBagConstraints5.gridy;
        panel.add(new JLabel(JAPMessages.getString("biInfoPort")), gridBagConstraints);
        this.m_biPort = new JLabel();
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
        ++gridBagConstraints6.gridx;
        panel.add(this.m_biPort, gridBagConstraints);
        (this.m_cancelButton = new JButton(JAPMessages.getString("bttnCancel"))).addActionListener(this);
        panel2.add(this.m_cancelButton);
        (this.m_okButton = new JButton(JAPMessages.getString("bttnOk"))).addActionListener(this);
        panel2.add(this.m_okButton);
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = 14;
        panel.add(panel2, gridBagConstraints);
        this.getContentPane().add(panel);
        final DefaultListModel<String> model = new DefaultListModel<String>();
        model.addElement(JAPMessages.getString("loadingBIInfo1"));
        model.addElement(JAPMessages.getString("loadingBIInfo2"));
        this.m_biList.setModel(model);
        this.m_biList.setEnabled(false);
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                final DefaultListModel<PaymentInstanceDBEntry> model = new DefaultListModel<PaymentInstanceDBEntry>();
                try {
                    final Enumeration<PaymentInstanceDBEntry> elements = PayAccountsFile.getInstance().getPaymentInstances().elements();
                    while (elements.hasMoreElements()) {
                        model.addElement(elements.nextElement());
                    }
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.PAY, ex.getMessage());
                }
                BISelectionDialog.this.m_biList.setEnabled(true);
                BISelectionDialog.this.m_biList.setModel(model);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    
    public PaymentInstanceDBEntry getSelectedBI() {
        return this.m_selectedBI;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.m_okButton) {
            this.m_selectedBI = this.m_biList.getSelectedValue();
            this.dispose();
        }
        else if (actionEvent.getSource() == this.m_cancelButton) {
            this.dispose();
        }
    }
    
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent.getSource() == this.m_biList) {}
    }
}
