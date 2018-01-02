// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.infoservice.ServiceOperator;
import java.util.Observable;
import gui.dialog.JAPDialog;
import logging.LogType;
import anon.terms.TermsAndConditions;
import java.util.Vector;
import anon.infoservice.Database;
import javax.swing.JPanel;
import javax.swing.JViewport;
import gui.UpperLeftStartViewport;
import javax.swing.event.HyperlinkListener;
import gui.JAPHyperlinkAdapter;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import anon.util.JAPMessages;
import anon.terms.TermsAndConditionsResponseHandler;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import java.util.Observer;

public class JAPConfTC extends AbstractJAPConfModule implements Observer, TermsAndCondtionsTableController
{
    private static final String MSG_TAB_TITLE;
    private static final String MSG_ERR_REJECT_IMPOSSIBLE;
    private TermsAndConditionsOperatorTable m_tblOperators;
    private JEditorPane m_termsPane;
    private JScrollPane m_scrollingTerms;
    static /* synthetic */ Class class$jap$JAPConfTC;
    static /* synthetic */ Class class$anon$infoservice$ServiceOperator;
    
    protected JAPConfTC(final IJAPConfSavePoint ijapConfSavePoint) {
        super(null);
    }
    
    protected boolean initObservers() {
        if (super.initObservers()) {
            synchronized (super.LOCK_OBSERVABLE) {
                TermsAndConditionsResponseHandler.get().addObserver(this);
                return true;
            }
        }
        return false;
    }
    
    public String getTabTitle() {
        return JAPMessages.getString(JAPConfTC.MSG_TAB_TITLE);
    }
    
    public void recreateRootPanel() {
        final JPanel rootPanel = this.getRootPanel();
        rootPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 1;
        (this.m_tblOperators = new TermsAndConditionsOperatorTable()).setController(this);
        final JScrollPane scrollPane = new JScrollPane(this.m_tblOperators);
        scrollPane.setHorizontalScrollBarPolicy(31);
        scrollPane.setPreferredSize(this.m_tblOperators.getPreferredSize());
        rootPanel.add(scrollPane, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        gridBagConstraints.weighty = 0.8;
        (this.m_termsPane = new JEditorPane("text/html", "")).addHyperlinkListener(new JAPHyperlinkAdapter());
        this.m_termsPane.setEditable(false);
        (this.m_scrollingTerms = new JScrollPane()).setViewport(new UpperLeftStartViewport());
        this.m_scrollingTerms.getViewport().add(this.m_termsPane);
        rootPanel.add(this.m_scrollingTerms, gridBagConstraints);
        rootPanel.validate();
    }
    
    public String getHelpContext() {
        return "services_tc";
    }
    
    protected void onRootPanelShown() {
        this.m_tblOperators.setOperators(Database.getInstance((JAPConfTC.class$anon$infoservice$ServiceOperator == null) ? (JAPConfTC.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : JAPConfTC.class$anon$infoservice$ServiceOperator).getEntryList());
    }
    
    protected boolean onOkPressed() {
        final Vector[] array = { this.m_tblOperators.getTermsAccepted(), this.m_tblOperators.getTermsRejected() };
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            final boolean accepted = i == 0;
            if (array[i] != null) {
                for (int j = 0; j < array[i].size(); ++j) {
                    final TermsAndConditions termsAndConditions = array[i].elementAt(j);
                    if (termsAndConditions != null) {
                        if (!accepted && !JAPController.getInstance().isOperatorOfConnectedMix(termsAndConditions.getOperator())) {
                            if (n == 0) {
                                JAPDialog.showErrorDialog(JAPConf.getInstance(), JAPMessages.getString(JAPConfTC.MSG_ERR_REJECT_IMPOSSIBLE, termsAndConditions.getOperator().getOrganization()), LogType.MISC);
                                n = 1;
                            }
                        }
                        else {
                            termsAndConditions.setAccepted(accepted);
                        }
                    }
                }
            }
        }
        this.m_tblOperators.setOperators(Database.getInstance((JAPConfTC.class$anon$infoservice$ServiceOperator == null) ? (JAPConfTC.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : JAPConfTC.class$anon$infoservice$ServiceOperator).getEntryList());
        return true;
    }
    
    protected void onCancelPressed() {
        this.m_tblOperators.setOperators(Database.getInstance((JAPConfTC.class$anon$infoservice$ServiceOperator == null) ? (JAPConfTC.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : JAPConfTC.class$anon$infoservice$ServiceOperator).getEntryList());
    }
    
    public void update(final Observable observable, final Object o) {
        this.onUpdateValues();
        this.getRootPanel().revalidate();
    }
    
    protected void onUpdateValues() {
        this.m_tblOperators.setOperators(Database.getInstance((JAPConfTC.class$anon$infoservice$ServiceOperator == null) ? (JAPConfTC.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : JAPConfTC.class$anon$infoservice$ServiceOperator).getEntryList());
    }
    
    public boolean handleOperatorAction(final ServiceOperator serviceOperator, final boolean b) {
        return b;
    }
    
    public void handleSelectLineAction(final ServiceOperator serviceOperator) {
        final TermsAndConditions termsAndConditions = TermsAndConditions.getTermsAndConditions(serviceOperator);
        if (termsAndConditions == null) {
            return;
        }
        this.m_termsPane.setText(termsAndConditions.getHTMLText(JAPMessages.getLocale()));
    }
    
    public void handleAcceptAction(final ServiceOperator serviceOperator, final boolean b) {
        if (!b && !JAPController.getInstance().isOperatorOfConnectedMix(serviceOperator)) {
            JAPDialog.showErrorDialog(JAPConf.getInstance(), JAPMessages.getString(JAPConfTC.MSG_ERR_REJECT_IMPOSSIBLE, serviceOperator.getOrganization()), LogType.MISC);
            throw new IllegalStateException(JAPMessages.getString(JAPConfTC.MSG_ERR_REJECT_IMPOSSIBLE, serviceOperator.getOrganization()));
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
    
    static {
        MSG_TAB_TITLE = ((JAPConfTC.class$jap$JAPConfTC == null) ? (JAPConfTC.class$jap$JAPConfTC = class$("jap.JAPConfTC")) : JAPConfTC.class$jap$JAPConfTC).getName() + "_tabTitle";
        MSG_ERR_REJECT_IMPOSSIBLE = ((JAPConfTC.class$jap$JAPConfTC == null) ? (JAPConfTC.class$jap$JAPConfTC = class$("jap.JAPConfTC")) : JAPConfTC.class$jap$JAPConfTC).getName() + "_errRejectImpossible";
    }
}
