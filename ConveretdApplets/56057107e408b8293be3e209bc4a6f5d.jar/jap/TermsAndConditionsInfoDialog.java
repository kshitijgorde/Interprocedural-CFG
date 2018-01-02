// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.awt.event.ActionEvent;
import gui.TermsAndConditionsDialog;
import anon.terms.TermsAndConditions;
import anon.infoservice.ServiceOperator;
import java.awt.Container;
import javax.swing.JPanel;
import gui.dialog.DialogContentPane;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import anon.util.JAPMessages;
import java.util.Vector;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import gui.dialog.JAPDialog;

public class TermsAndConditionsInfoDialog extends JAPDialog implements TermsAndCondtionsTableController, ActionListener
{
    public static String MSG_DIALOG_TEXT;
    public static String MSG_DIALOG_TITLE;
    private TermsAndConditionsOperatorTable operatorTable;
    private JButton okButtton;
    private JButton cancelButton;
    static /* synthetic */ Class class$jap$TermsAndConditionsInfoDialog;
    
    public TermsAndConditionsInfoDialog(final Component component, final Vector vector, final String s) {
        super(component, JAPMessages.getString(TermsAndConditionsInfoDialog.MSG_DIALOG_TITLE));
        this.operatorTable = null;
        this.okButtton = null;
        this.cancelButton = null;
        this.setDefaultCloseOperation(1);
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        final JTextArea textArea = new JTextArea(JAPMessages.getString(TermsAndConditionsInfoDialog.MSG_DIALOG_TEXT, s));
        textArea.setText(JAPMessages.getString(TermsAndConditionsInfoDialog.MSG_DIALOG_TEXT, s));
        textArea.setEditable(false);
        textArea.setBackground(contentPane.getBackground());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setSelectionColor(contentPane.getBackground());
        textArea.setSelectedTextColor(textArea.getForeground());
        contentPane.add(textArea, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        (this.operatorTable = new TermsAndConditionsOperatorTable(vector)).setController(this);
        final JScrollPane scrollPane = new JScrollPane(this.operatorTable);
        scrollPane.setHorizontalScrollBarPolicy(31);
        scrollPane.setPreferredSize(new Dimension(400, 120));
        this.okButtton = new JButton(JAPMessages.getString(DialogContentPane.MSG_OK));
        this.cancelButton = new JButton(JAPMessages.getString(DialogContentPane.MSG_CANCEL));
        this.okButtton.addActionListener(this);
        this.cancelButton.addActionListener(this);
        final JPanel panel = new JPanel();
        panel.add(this.okButtton);
        panel.add(this.cancelButton);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        contentPane.add(scrollPane, gridBagConstraints);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        contentPane.add(panel, gridBagConstraints);
        this.okButtton.setEnabled(!this.operatorTable.areTermsRejected());
        this.pack();
    }
    
    public boolean areAllAccepted() {
        final Vector operators = this.operatorTable.getOperators();
        for (int i = 0; i < operators.size(); ++i) {
            final TermsAndConditions termsAndConditions = TermsAndConditions.getTermsAndConditions(operators.elementAt(i));
            if (termsAndConditions == null || !termsAndConditions.isAccepted()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean handleOperatorAction(final ServiceOperator serviceOperator, final boolean b) {
        final TermsAndConditionsDialog termsAndConditionsDialog = new TermsAndConditionsDialog(JAPController.getInstance().getCurrentView(), b, TermsAndConditions.getTermsAndConditions(serviceOperator));
        termsAndConditionsDialog.setVisible(true);
        final TermsAndConditionsDialog.TermsAndConditonsDialogReturnValues returnValues = termsAndConditionsDialog.getReturnValues();
        return returnValues.isCancelled() ? b : returnValues.isAccepted();
    }
    
    public void handleSelectLineAction(final ServiceOperator serviceOperator) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.okButtton) {
            this.commitActions();
        }
        this.dispose();
    }
    
    public void commitActions() {
        final Vector[] array = { this.operatorTable.getTermsAccepted(), this.operatorTable.getTermsRejected() };
        for (int i = 0; i < array.length; ++i) {
            final boolean accepted = i == 0;
            if (array[i] != null) {
                for (int j = 0; j < array[i].size(); ++j) {
                    final TermsAndConditions termsAndConditions = array[i].elementAt(j);
                    if (termsAndConditions != null) {
                        termsAndConditions.setAccepted(accepted);
                    }
                }
            }
        }
    }
    
    public void handleAcceptAction(final ServiceOperator serviceOperator, final boolean b) {
        this.okButtton.setEnabled(!this.operatorTable.areTermsRejected());
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
        TermsAndConditionsInfoDialog.MSG_DIALOG_TEXT = ((TermsAndConditionsInfoDialog.class$jap$TermsAndConditionsInfoDialog == null) ? (TermsAndConditionsInfoDialog.class$jap$TermsAndConditionsInfoDialog = class$("jap.TermsAndConditionsInfoDialog")) : TermsAndConditionsInfoDialog.class$jap$TermsAndConditionsInfoDialog).getName() + "_dialogText";
        TermsAndConditionsInfoDialog.MSG_DIALOG_TITLE = ((TermsAndConditionsInfoDialog.class$jap$TermsAndConditionsInfoDialog == null) ? (TermsAndConditionsInfoDialog.class$jap$TermsAndConditionsInfoDialog = class$("jap.TermsAndConditionsInfoDialog")) : TermsAndConditionsInfoDialog.class$jap$TermsAndConditionsInfoDialog).getName() + "_dialogTitle";
    }
}
