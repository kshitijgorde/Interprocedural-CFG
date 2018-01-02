// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.io.IOException;
import logging.LogType;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JViewport;
import anon.terms.TermsAndConditionsTranslation;
import anon.util.JAPMessages;
import anon.terms.TermsAndConditions;
import java.awt.Component;
import gui.dialog.TermsAndConditionsPane;
import gui.dialog.JAPDialog;

public class TermsAndConditionsDialog extends JAPDialog
{
    TermsAndConditionsPane m_panel;
    TermsAndConditonsDialogReturnValues m_ret;
    boolean acceptInitialValue;
    public static final String HTML_EXPORT_ENCODING = "ISO-8859-1";
    public static final String MSG_DIALOG_TITLE;
    static /* synthetic */ Class class$gui$TermsAndConditionsDialog;
    
    public TermsAndConditionsDialog(final Component component, final boolean b, final TermsAndConditions termsAndConditions) {
        this(component, b, termsAndConditions, JAPMessages.getLocale().getLanguage());
    }
    
    public TermsAndConditionsDialog(final Component component, final boolean b, final TermsAndConditions termsAndConditions, final String s) {
        super(component, JAPMessages.getString(TermsAndConditionsDialog.MSG_DIALOG_TITLE, termsAndConditions.getOperator().getOrganization()));
        this.acceptInitialValue = false;
        this.m_ret = new TermsAndConditonsDialogReturnValues();
        this.setResizable(false);
        (this.m_panel = new TermsAndConditionsPane(this, b, new TermsAndConditionsPane.TermsAndConditionsMessages())).setText(termsAndConditions.getHTMLText(s));
        this.m_panel.updateDialog();
        this.pack();
    }
    
    public static void previewTranslation(final Component component, final TermsAndConditionsTranslation termsAndConditionsTranslation) {
        final String htmlText = TermsAndConditions.getHTMLText(termsAndConditionsTranslation);
        final JapHtmlPane japHtmlPane = new JapHtmlPane(htmlText, new UpperLeftStartViewport());
        japHtmlPane.setPreferredSize(new Dimension(800, 600));
        final JAPDialog japDialog = new JAPDialog(component, "Translation preview [" + termsAndConditionsTranslation + "]");
        final Container contentPane = japDialog.getContentPane();
        contentPane.setLayout(new BorderLayout());
        final JPanel panel = new JPanel();
        final JButton button = new JButton(JAPMessages.getString("bttnSaveAs"));
        final JButton button2 = new JButton(JAPMessages.getString("bttnClose"));
        final ActionListener actionListener = new ActionListener() {
            private final /* synthetic */ String val$suggestedFileName = "Terms_" + ((termsAndConditionsTranslation.getOperator() != null) ? ((termsAndConditionsTranslation.getOperator().getOrganization() != null) ? termsAndConditionsTranslation.getOperator().getOrganization() : "???") : "???") + "_" + termsAndConditionsTranslation.getLocale() + ".html";
            
            public void actionPerformed(final ActionEvent actionEvent) {
                if (actionEvent.getSource() == button) {
                    actionExportHTMLToFile(japDialog.getContentPane(), htmlText, this.val$suggestedFileName);
                }
                else if (actionEvent.getSource() == button2) {
                    japDialog.dispose();
                }
            }
        };
        button.addActionListener(actionListener);
        button2.addActionListener(actionListener);
        panel.add(button);
        panel.add(button2);
        contentPane.add(panel, "North");
        contentPane.add(japHtmlPane, "South");
        contentPane.add(japHtmlPane);
        japDialog.setDefaultCloseOperation(2);
        japDialog.pack();
        japDialog.setVisible(true);
    }
    
    public TermsAndConditonsDialogReturnValues getReturnValues() {
        this.m_ret.setCancelled(this.m_panel.getButtonValue() != 0);
        this.m_ret.setAccepted(this.m_panel.isTermsAccepted());
        return this.m_ret;
    }
    
    private static void actionExportHTMLToFile(final Component component, final String s, final String s2) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(fileChooser.getCurrentDirectory() + File.separator + s2));
        switch (fileChooser.showSaveDialog(component)) {
            case 0: {
                final File selectedFile = fileChooser.getSelectedFile();
                int n = 1;
                if (selectedFile.exists()) {
                    n = ((JAPDialog.showConfirmDialog(component, "File already " + selectedFile.getName() + " already exists. Do you want to replace it?", 0, 3) == 0) ? 1 : 0);
                }
                if (n != 0) {
                    try {
                        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(selectedFile), "ISO-8859-1");
                        outputStreamWriter.write(s);
                        outputStreamWriter.flush();
                        outputStreamWriter.close();
                    }
                    catch (IOException ex) {
                        JAPDialog.showErrorDialog(component, "Could not export to " + selectedFile.getName(), LogType.MISC, ex);
                    }
                    break;
                }
                break;
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
    
    static {
        MSG_DIALOG_TITLE = ((TermsAndConditionsDialog.class$gui$TermsAndConditionsDialog == null) ? (TermsAndConditionsDialog.class$gui$TermsAndConditionsDialog = class$("gui.TermsAndConditionsDialog")) : TermsAndConditionsDialog.class$gui$TermsAndConditionsDialog).getName() + "_dialogTitle";
    }
    
    public class TermsAndConditonsDialogReturnValues
    {
        private boolean cancelled;
        private boolean accepted;
        
        public TermsAndConditonsDialogReturnValues() {
            this.cancelled = false;
            this.accepted = false;
        }
        
        public boolean isCancelled() {
            return this.cancelled;
        }
        
        public void setCancelled(final boolean cancelled) {
            this.cancelled = cancelled;
        }
        
        public boolean isAccepted() {
            return this.accepted;
        }
        
        public void setAccepted(final boolean accepted) {
            this.accepted = accepted;
        }
    }
}
