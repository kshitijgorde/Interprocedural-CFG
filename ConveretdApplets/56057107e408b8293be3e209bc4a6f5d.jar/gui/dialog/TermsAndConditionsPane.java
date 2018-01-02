// 
// Decompiled by Procyon v0.5.30
// 

package gui.dialog;

import logging.LogType;
import anon.pay.xml.XMLGenericText;
import java.awt.event.ComponentListener;
import javax.swing.SwingUtilities;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.event.HyperlinkListener;
import gui.JAPHyperlinkAdapter;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import anon.util.JAPMessages;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JCheckBox;
import java.awt.Container;
import java.awt.GridBagConstraints;

public class TermsAndConditionsPane extends DialogContentPane implements IWizardSuitable
{
    public static final String MSG_HEADING;
    private static final String MSG_ERROR_HAVE_TO_ACCEPT;
    private static final String MSG_NO_TERMS_FOUND;
    private static final String MSG_I_ACCEPT;
    private static final String MSG_CANCEL_HEADING;
    private static final String MSG_CANCEL_ERROR_HAVE_TO_ACCEPT;
    private static final String MSG_CANCEL_NO_POLICY_FOUND;
    private static final String MSG_CANCEL_I_ACCEPT;
    private WorkerContentPane m_fetchTermsPane;
    private GridBagConstraints m_c;
    private Container m_rootPanel;
    private JCheckBox m_accepted;
    private JEditorPane m_termsPane;
    private JScrollPane m_scrollingTerms;
    private IMessages m_messages;
    static /* synthetic */ Class class$gui$dialog$TermsAndConditionsPane;
    
    public TermsAndConditionsPane(final JAPDialog japDialog, final WorkerContentPane fetchTermsPane, final boolean b, final IMessages messages) {
        super(japDialog, new Layout(-1) {
            public boolean isCentered() {
                return false;
            }
        }, new DialogContentPaneOptions((DialogContentPane)fetchTermsPane) {
            public int countExtraButtons() {
                return 1;
            }
            
            public AbstractDialogExtraButton getExtraButtonInternal(final int n) {
                return new AbstractDialogExtraButton() {
                    public String getText() {
                        return "Drucken";
                    }
                    
                    public void doAction() {
                        System.out.println("print");
                    }
                };
            }
        });
        this.m_c = new GridBagConstraints();
        this.m_messages = messages;
        this.m_fetchTermsPane = fetchTermsPane;
        this.init(b);
        if (b) {
            this.m_accepted.setSelected(false);
        }
    }
    
    public TermsAndConditionsPane(final JAPDialog japDialog, final boolean selected, final IMessages messages) {
        super(japDialog, new Layout(-1) {
            public boolean isCentered() {
                return false;
            }
        }, new DialogContentPaneOptions() {
            public int countExtraButtons() {
                return 1;
            }
            
            public AbstractDialogExtraButton getExtraButtonInternal(final int n) {
                return new AbstractDialogExtraButton() {
                    public String getText() {
                        return "Drucken";
                    }
                    
                    public void doAction() {
                        System.out.println("print");
                    }
                };
            }
        });
        this.m_c = new GridBagConstraints();
        this.m_messages = messages;
        this.init(true);
        this.m_accepted.setSelected(selected);
    }
    
    private void init(final boolean b) {
        this.setDefaultButtonOperation(266);
        this.m_rootPanel = this.getContentPane();
        this.m_c = new GridBagConstraints();
        this.m_rootPanel.setLayout(new GridBagLayout());
        this.m_c.gridx = 0;
        this.m_c.gridy = 0;
        this.m_c.weightx = 1.0;
        this.m_c.weighty = 1.0;
        this.m_c.insets = new Insets(5, 5, 5, 5);
        this.m_c.anchor = 18;
        this.m_c.fill = 1;
        (this.m_termsPane = new JEditorPane("text/html", JAPMessages.getString(this.m_messages.getNotFound()))).setEditable(false);
        this.m_termsPane.addHyperlinkListener(new JAPHyperlinkAdapter());
        (this.m_scrollingTerms = new JScrollPane(this.m_termsPane)).setHorizontalScrollBarPolicy(31);
        this.m_scrollingTerms.setPreferredSize(new Dimension(400, 200));
        this.m_rootPanel.add(this.m_scrollingTerms, this.m_c);
        if (b) {
            this.m_accepted = new JCheckBox(JAPMessages.getString(this.m_messages.getIAccept()));
            this.m_c.weightx = 0.0;
            this.m_c.weighty = 0.0;
            final GridBagConstraints c = this.m_c;
            ++c.gridy;
            this.m_rootPanel.add(this.m_accepted, this.m_c);
        }
        this.addComponentListener(new ComponentAdapter() {
            private final /* synthetic */ TermsAndConditionsPane this$0;
            
            public void componentShown(final ComponentEvent componentEvent) {
                SwingUtilities.invokeLater(new Runnable() {
                    private final /* synthetic */ TermsAndConditionsPane$7 this$1 = this$1;
                    
                    public void run() {
                        this.this$1.this$0.m_scrollingTerms.getVerticalScrollBar().setValue(0);
                    }
                });
            }
        });
    }
    
    public boolean isTermsAccepted() {
        return this.m_accepted == null || this.m_accepted.isSelected();
    }
    
    private void showTerms() {
        final Object value = this.m_fetchTermsPane.getValue();
        String text;
        if (value == null) {
            text = JAPMessages.getString(this.m_messages.getNotFound());
        }
        else {
            text = ((XMLGenericText)value).getText();
        }
        this.m_termsPane.setText(text);
    }
    
    public void setText(final String text) {
        this.m_termsPane.setText(text);
    }
    
    public CheckError[] checkYesOK() {
        CheckError[] checkYesOK = super.checkYesOK();
        if ((checkYesOK == null || checkYesOK.length == 0) && !this.isTermsAccepted()) {
            checkYesOK = new CheckError[] { new CheckError(JAPMessages.getString(this.m_messages.getErrorHaveToAccept()), LogType.GUI) };
        }
        return checkYesOK;
    }
    
    public CheckError[] checkUpdate() {
        if (this.m_fetchTermsPane != null) {
            this.showTerms();
        }
        return null;
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
        MSG_HEADING = ((TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane == null) ? (TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane = class$("gui.dialog.TermsAndConditionsPane")) : TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane).getName() + "_heading";
        MSG_ERROR_HAVE_TO_ACCEPT = ((TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane == null) ? (TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane = class$("gui.dialog.TermsAndConditionsPane")) : TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane).getName() + "_havetoaccept";
        MSG_NO_TERMS_FOUND = ((TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane == null) ? (TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane = class$("gui.dialog.TermsAndConditionsPane")) : TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane).getName() + "_notermsfound";
        MSG_I_ACCEPT = ((TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane == null) ? (TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane = class$("gui.dialog.TermsAndConditionsPane")) : TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane).getName() + "_iaccept";
        MSG_CANCEL_HEADING = ((TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane == null) ? (TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane = class$("gui.dialog.TermsAndConditionsPane")) : TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane).getName() + "_cancellation_heading";
        MSG_CANCEL_ERROR_HAVE_TO_ACCEPT = ((TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane == null) ? (TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane = class$("gui.dialog.TermsAndConditionsPane")) : TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane).getName() + "_cancellation_havetoaccept";
        MSG_CANCEL_NO_POLICY_FOUND = ((TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane == null) ? (TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane = class$("gui.dialog.TermsAndConditionsPane")) : TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane).getName() + "_cancellation_nopolicyfound";
        MSG_CANCEL_I_ACCEPT = ((TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane == null) ? (TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane = class$("gui.dialog.TermsAndConditionsPane")) : TermsAndConditionsPane.class$gui$dialog$TermsAndConditionsPane).getName() + "_cancellation_iaccept";
    }
    
    public static final class TermsAndConditionsMessages implements IMessages
    {
        public String getHeading() {
            return TermsAndConditionsPane.MSG_HEADING;
        }
        
        public String getErrorHaveToAccept() {
            return TermsAndConditionsPane.MSG_ERROR_HAVE_TO_ACCEPT;
        }
        
        public String getNotFound() {
            return TermsAndConditionsPane.MSG_NO_TERMS_FOUND;
        }
        
        public String getIAccept() {
            return TermsAndConditionsPane.MSG_I_ACCEPT;
        }
    }
    
    public static final class CancellationPolicyMessages implements IMessages
    {
        public String getHeading() {
            return TermsAndConditionsPane.MSG_CANCEL_HEADING;
        }
        
        public String getErrorHaveToAccept() {
            return TermsAndConditionsPane.MSG_CANCEL_ERROR_HAVE_TO_ACCEPT;
        }
        
        public String getNotFound() {
            return TermsAndConditionsPane.MSG_CANCEL_NO_POLICY_FOUND;
        }
        
        public String getIAccept() {
            return TermsAndConditionsPane.MSG_CANCEL_I_ACCEPT;
        }
    }
    
    public interface IMessages
    {
        String getHeading();
        
        String getErrorHaveToAccept();
        
        String getNotFound();
        
        String getIAccept();
    }
}
