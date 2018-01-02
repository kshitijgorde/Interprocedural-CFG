// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay.wizardnew;

import java.util.Enumeration;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import logging.LogType;
import java.awt.Component;
import javax.swing.AbstractButton;
import anon.infoservice.ListenerInterface;
import java.awt.Insets;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import gui.dialog.DialogContentPaneOptions;
import anon.util.JAPMessages;
import gui.dialog.JAPDialog;
import java.awt.Container;
import java.awt.GridBagConstraints;
import javax.swing.ButtonGroup;
import java.util.Hashtable;
import java.util.Vector;
import anon.pay.PaymentInstanceDBEntry;
import gui.dialog.WorkerContentPane;
import java.awt.event.ActionListener;
import gui.dialog.DialogContentPane;

public class JpiSelectionPane extends DialogContentPane implements ActionListener, IWizardSuitable
{
    private static final String MSG_CHOOSEAJPI;
    private static final String MSG_CHOOSEAJPI_TITLE;
    private static final String MSG_HAVE_TO_CHOOSE;
    private WorkerContentPane m_fetchJPIPane;
    private PaymentInstanceDBEntry m_selectedJpi;
    private Vector m_allJpis;
    private Hashtable m_Jpis;
    private ButtonGroup m_rbGroup;
    private GridBagConstraints m_c;
    private Container m_rootPanel;
    static /* synthetic */ Class class$jap$pay$wizardnew$JpiSelectionPane;
    
    public JpiSelectionPane(final JAPDialog japDialog, final WorkerContentPane fetchJPIPane, final String s) {
        super(japDialog, JAPMessages.getString(JpiSelectionPane.MSG_CHOOSEAJPI), new Layout(JAPMessages.getString(JpiSelectionPane.MSG_CHOOSEAJPI_TITLE), -1), new DialogContentPaneOptions(2, fetchJPIPane));
        this.m_c = new GridBagConstraints();
        this.setDefaultButtonOperation(266);
        this.m_fetchJPIPane = fetchJPIPane;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof JRadioButton) {
            this.m_selectedJpi = this.m_Jpis.get(((JRadioButton)actionEvent.getSource()).getName());
        }
    }
    
    private void addJpi(final PaymentInstanceDBEntry paymentInstanceDBEntry) {
        this.m_c.insets = new Insets(0, 5, 0, 5);
        final GridBagConstraints c = this.m_c;
        ++c.gridy;
        final String id = paymentInstanceDBEntry.getId();
        final String name = paymentInstanceDBEntry.getName();
        final ListenerInterface listenerInterface = paymentInstanceDBEntry.getListenerInterfaces().nextElement();
        final String string = listenerInterface.getHost() + " : " + listenerInterface.getPort();
        this.m_c.gridx = 0;
        final JRadioButton radioButton = new JRadioButton(name + " , " + string);
        radioButton.setName(id);
        radioButton.addActionListener(this);
        this.m_rbGroup.add(radioButton);
        this.m_rootPanel.add(radioButton, this.m_c);
    }
    
    public CheckError[] checkYesOK() {
        CheckError[] checkYesOK = super.checkYesOK();
        if ((checkYesOK == null || checkYesOK.length == 0) && this.m_rbGroup.getSelection() == null) {
            checkYesOK = new CheckError[] { new CheckError(JAPMessages.getString(JpiSelectionPane.MSG_HAVE_TO_CHOOSE), LogType.GUI) };
        }
        return checkYesOK;
    }
    
    public CheckError[] checkUpdate() {
        this.m_rbGroup = new ButtonGroup();
        this.m_rootPanel = this.getContentPane();
        this.m_c = new GridBagConstraints();
        this.m_rootPanel.setLayout(new GridBagLayout());
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weightx = 0.0;
        this.m_c.weightx = 0.0;
        this.m_c.insets = new Insets(5, 5, 5, 5);
        this.m_c.anchor = 18;
        this.m_c.fill = 0;
        this.m_allJpis = (Vector)this.m_fetchJPIPane.getValue();
        this.showPaymentInstances();
        this.m_rootPanel.setVisible(true);
        this.resetSelection();
        return null;
    }
    
    public void showPaymentInstances() {
        this.m_Jpis = new Hashtable();
        final Enumeration<PaymentInstanceDBEntry> elements = (Enumeration<PaymentInstanceDBEntry>)this.m_allJpis.elements();
        while (elements.hasMoreElements()) {
            final PaymentInstanceDBEntry paymentInstanceDBEntry = elements.nextElement();
            this.m_Jpis.put(paymentInstanceDBEntry.getId(), paymentInstanceDBEntry);
        }
        this.m_rootPanel.removeAll();
        this.m_c = new GridBagConstraints();
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weightx = 0.0;
        this.m_c.weightx = 0.0;
        this.m_c.insets = new Insets(5, 5, 5, 5);
        this.m_c.anchor = 18;
        this.m_c.fill = 0;
        final GridBagConstraints c = this.m_c;
        ++c.gridy;
        final Enumeration<PaymentInstanceDBEntry> elements2 = (Enumeration<PaymentInstanceDBEntry>)this.m_allJpis.elements();
        while (elements2.hasMoreElements()) {
            this.addJpi(elements2.nextElement());
        }
    }
    
    public void resetSelection() {
        this.m_selectedJpi = null;
    }
    
    public PaymentInstanceDBEntry getSelectedPaymentInstance() {
        this.m_allJpis = (Vector)this.m_fetchJPIPane.getValue();
        if (this.m_allJpis == null || this.m_allJpis.size() < 1) {
            this.m_selectedJpi = null;
        }
        else if (this.m_allJpis.size() == 1) {
            this.m_selectedJpi = this.m_allJpis.elementAt(0);
        }
        return this.m_selectedJpi;
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
        MSG_CHOOSEAJPI = ((JpiSelectionPane.class$jap$pay$wizardnew$JpiSelectionPane == null) ? (JpiSelectionPane.class$jap$pay$wizardnew$JpiSelectionPane = class$("jap.pay.wizardnew.JpiSelectionPane")) : JpiSelectionPane.class$jap$pay$wizardnew$JpiSelectionPane).getName() + "_chooseajpi";
        MSG_CHOOSEAJPI_TITLE = ((JpiSelectionPane.class$jap$pay$wizardnew$JpiSelectionPane == null) ? (JpiSelectionPane.class$jap$pay$wizardnew$JpiSelectionPane = class$("jap.pay.wizardnew.JpiSelectionPane")) : JpiSelectionPane.class$jap$pay$wizardnew$JpiSelectionPane).getName() + "_titleChooseajpi";
        MSG_HAVE_TO_CHOOSE = ((JpiSelectionPane.class$jap$pay$wizardnew$JpiSelectionPane == null) ? (JpiSelectionPane.class$jap$pay$wizardnew$JpiSelectionPane = class$("jap.pay.wizardnew.JpiSelectionPane")) : JpiSelectionPane.class$jap$pay$wizardnew$JpiSelectionPane).getName() + "_havetochoose";
    }
}
