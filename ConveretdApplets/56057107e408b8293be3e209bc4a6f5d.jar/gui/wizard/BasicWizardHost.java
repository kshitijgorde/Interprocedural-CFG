// 
// Decompiled by Procyon v0.5.30
// 

package gui.wizard;

import java.awt.event.ActionEvent;
import java.awt.Container;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JSeparator;
import anon.util.JAPMessages;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import gui.dialog.JAPDialog;
import java.awt.event.ActionListener;

public class BasicWizardHost implements WizardHost, ActionListener
{
    private JAPDialog m_Dialog;
    private JButton m_bttnOk;
    private JButton m_bttnCancel;
    private JButton m_bttnFinish;
    private JButton m_bttnBack;
    private JButton m_bttnNext;
    private JButton m_bttnHelp;
    private JPanel m_panelPages;
    private CardLayout m_cardlayoutPages;
    private Wizard m_Wizard;
    private static final String COMMAND_NEXT = "NEXT";
    private static final String COMMAND_BACK = "BACK";
    private static final String COMMAND_CANCEL = "CANCEL";
    private static final String COMMAND_FINISH = "FINISH";
    private static final String COMMAND_HELP = "HELP";
    
    public BasicWizardHost(final JAPDialog japDialog, final Wizard wizard) {
        this((Object)japDialog, wizard);
    }
    
    public BasicWizardHost(final Component component, final Wizard wizard) {
        this((Object)component, wizard);
    }
    
    private BasicWizardHost(final Object o, final Wizard wizard) {
        this.m_Wizard = wizard;
        if (o instanceof JAPDialog) {
            this.m_Dialog = new JAPDialog((JAPDialog)o, wizard.getWizardTitle(), true);
        }
        else {
            this.m_Dialog = new JAPDialog((Component)o, wizard.getWizardTitle(), true);
        }
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.m_Dialog.setDefaultCloseOperation(0);
        this.m_Dialog.getContentPane().setLayout(layout);
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final JPanel panel = new JPanel();
        panel.setLayout(layout2);
        (this.m_bttnBack = new JButton(JAPMessages.getString("updateM_bttnBack"))).setActionCommand("BACK");
        this.m_bttnBack.addActionListener(this);
        (this.m_bttnNext = new JButton(JAPMessages.getString("updateM_bttnNext"))).setActionCommand("NEXT");
        this.m_bttnNext.addActionListener(this);
        this.m_bttnHelp = new JButton(JAPMessages.getString("updateM_bttnHelp"));
        (this.m_bttnCancel = new JButton(JAPMessages.getString("updateM_bttnCancel"))).setActionCommand("CANCEL");
        this.m_bttnCancel.addActionListener(this);
        (this.m_bttnFinish = new JButton(JAPMessages.getString("updateM_bttnFinish"))).setActionCommand("FINISH");
        this.m_bttnFinish.addActionListener(this);
        final JSeparator separator = new JSeparator();
        separator.setVisible(true);
        this.m_cardlayoutPages = new CardLayout();
        this.m_panelPages = new JPanel(this.m_cardlayoutPages);
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.insets = new Insets(10, 10, 10, 10);
        panel.add(this.m_bttnHelp, gridBagConstraints2);
        final JLabel label = new JLabel("");
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.fill = 2;
        panel.add(label, gridBagConstraints2);
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.insets = new Insets(10, 10, 10, 20);
        panel.add(this.m_bttnCancel, gridBagConstraints2);
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.insets = new Insets(10, 2, 10, 2);
        panel.add(this.m_bttnBack, gridBagConstraints2);
        gridBagConstraints2.gridx = 4;
        panel.add(this.m_bttnNext, gridBagConstraints2);
        gridBagConstraints2.gridx = 5;
        gridBagConstraints2.insets = new Insets(10, 20, 10, 10);
        panel.add(this.m_bttnFinish, gridBagConstraints2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.m_panelPages, gridBagConstraints);
        this.m_Dialog.getContentPane().add(this.m_panelPages);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        layout.setConstraints(separator, gridBagConstraints);
        this.m_Dialog.getContentPane().add(separator);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.fill = 2;
        layout.setConstraints(panel, gridBagConstraints);
        this.m_Dialog.getContentPane().add(panel);
    }
    
    public void addWizardPage(final int n, final WizardPage wizardPage) {
        this.m_panelPages.add(wizardPage.getPageComponent(), Integer.toString(n));
    }
    
    public void showWizardPage(final int n) {
        if (n == 0) {
            this.m_cardlayoutPages.first(this.m_panelPages);
            this.m_Dialog.pack();
            this.m_Dialog.setVisible(true);
        }
        else {
            this.m_cardlayoutPages.show(this.m_panelPages, Integer.toString(n));
            this.m_Dialog.pack();
        }
    }
    
    public JAPDialog getDialogParent() {
        return this.m_Dialog;
    }
    
    public void setHelpEnabled(final boolean enabled) {
        this.m_bttnHelp.setEnabled(enabled);
    }
    
    public void setNextEnabled(final boolean enabled) {
        this.m_bttnNext.setEnabled(enabled);
    }
    
    public void setBackEnabled(final boolean enabled) {
        this.m_bttnBack.setEnabled(enabled);
    }
    
    public void setCancelEnabled(final boolean enabled) {
        this.m_bttnCancel.setEnabled(enabled);
    }
    
    public void setFinishEnabled(final boolean enabled) {
        this.m_bttnFinish.setEnabled(enabled);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("NEXT")) {
            this.m_Wizard.next();
        }
        else if (actionCommand.equals("BACK")) {
            this.m_Wizard.back();
        }
        else if (actionCommand.equals("CANCEL")) {
            this.doCancel();
        }
        else if (actionCommand.equals("FINISH")) {
            this.m_Wizard.finish();
        }
        else if (actionCommand.equals("HELP")) {}
    }
    
    public void lockDialog() {
        this.m_Dialog.setEnabled(false);
    }
    
    public void unlockDialog() {
        this.m_Dialog.setEnabled(true);
    }
    
    public void doCancel() {
        this.m_Wizard.wizardCompleted();
        this.m_Dialog.dispose();
    }
}
