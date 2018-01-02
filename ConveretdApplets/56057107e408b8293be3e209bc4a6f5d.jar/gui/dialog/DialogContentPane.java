// 
// Decompiled by Procyon v0.5.30
// 

package gui.dialog;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.Box;
import gui.help.JAPHelp;
import platform.AbstractOS;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import logging.LogHolder;
import logging.LogType;
import anon.util.JAPMessages;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import gui.GUIUtils;
import javax.swing.JOptionPane;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Insets;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.event.WindowListener;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.URL;
import java.awt.Component;
import javax.swing.JDialog;
import java.awt.event.ComponentListener;
import java.util.Vector;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import gui.JAPHtmlMultiLineLabel;
import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.RootPaneContainer;
import javax.swing.Icon;
import gui.JAPHelpContext;

public class DialogContentPane implements JAPHelpContext.IHelpContext, IDialogOptions
{
    private static final int[] MESSAGE_TYPES;
    private static final Icon[] MESSAGE_ICONS;
    public static final int ON_CLICK_DO_NOTHING = 0;
    public static final int ON_CLICK_HIDE_DIALOG = 1;
    public static final int ON_CLICK_DISPOSE_DIALOG = 2;
    public static final int ON_CLICK_SHOW_NEXT_CONTENT = 4;
    public static final int ON_YESOK_SHOW_NEXT_CONTENT = 8;
    public static final int ON_NO_SHOW_NEXT_CONTENT = 16;
    public static final int ON_CANCEL_SHOW_NEXT_CONTENT = 32;
    public static final int ON_CLICK_SHOW_PREVIOUS_CONTENT = 64;
    public static final int ON_YESOK_SHOW_PREVIOUS_CONTENT = 128;
    public static final int ON_NO_SHOW_PREVIOUS_CONTENT = 256;
    public static final int ON_CANCEL_SHOW_PREVIOUS_CONTENT = 512;
    public static final int ON_YESOK_HIDE_DIALOG = 1024;
    public static final int ON_NO_HIDE_DIALOG = 2048;
    public static final int ON_CANCEL_HIDE_DIALOG = 4096;
    public static final int ON_YESOK_DISPOSE_DIALOG = 8192;
    public static final int ON_NO_DISPOSE_DIALOG = 16384;
    public static final int ON_CANCEL_DISPOSE_DIALOG = 32768;
    public static final int BUTTON_OPERATION_WIZARD = 266;
    public static final String MSG_OK;
    public static final String MSG_YES;
    public static final String MSG_NO;
    public static final String MSG_NEXT;
    public static final String MSG_PREVIOUS;
    public static final String MSG_FINISH;
    public static final String MSG_CANCEL;
    public static final String MSG_OPERATION_FAILED;
    public static final String MSG_SEE_FULL_MESSAGE;
    public static final int DEFAULT_BUTTON_EMPTY = 0;
    public static final int DEFAULT_BUTTON_CANCEL = 1;
    public static final int DEFAULT_BUTTON_YES = 2;
    public static final int DEFAULT_BUTTON_OK = 2;
    public static final int DEFAULT_BUTTON_NO = 3;
    public static final int DEFAULT_BUTTON_HELP = 4;
    public static final int DEFAULT_BUTTON_KEEP = 5;
    private static final int MIN_TEXT_WIDTH = 100;
    private static final int UNLIMITED_SIZE = 2500;
    private static final int SPACE_AROUND_TEXT = 5;
    private static final String MORE_POINTS = "...";
    private static final int NUMBER_OF_HEURISTIC_ITERATIONS = 6;
    private DialogContentPane m_nextContentPane;
    private DialogContentPane m_previousContentPane;
    private RootPaneContainer m_parentDialog;
    private JComponent m_contentPane;
    private JPanel m_titlePane;
    private JPanel m_rootPane;
    private Container m_panelOptions;
    private JAPHtmlMultiLineLabel m_lblMessage;
    private LinkedDialog m_linkedDialog;
    private JAPHtmlMultiLineLabel m_lblText;
    private JAPHtmlMultiLineLabel m_lblSeeFullText;
    private int m_defaultButtonOperation;
    private int m_value;
    private JAPHelpContext.IHelpContext m_helpContext;
    private JButton m_btnHelp;
    private JButton m_btnYesOK;
    private JButton m_btnNo;
    private JButton m_btnCancel;
    private ButtonListener m_buttonListener;
    private Icon m_icon;
    private boolean m_bHasHadWizardLayout;
    private GridBagConstraints m_textConstraints;
    private Vector m_rememberedErrors;
    private Vector m_rememberedUpdateErrors;
    private Container m_currentlyActiveContentPane;
    private Vector m_componentListeners;
    private ComponentListener m_currentlyActiveContentPaneComponentListener;
    private int m_defaultButton;
    private String m_strText;
    private JDialog m_tempDialog;
    private boolean m_bDisposed;
    private DialogContentPaneOptions m_options;
    private Layout m_layout;
    private int m_idStatusMessage;
    static /* synthetic */ Class class$gui$dialog$DialogContentPane;
    static /* synthetic */ Class class$gui$dialog$DialogContentPane$CheckError;
    
    public DialogContentPane(final JDialog dialog, final String s) {
        this((RootPaneContainer)dialog, s, new Layout(""), null);
    }
    
    public DialogContentPane(final JAPDialog japDialog, final String s) {
        this((RootPaneContainer)japDialog, s, new Layout(""), null);
    }
    
    public DialogContentPane(final JDialog dialog, final String s, final Layout layout) {
        this((RootPaneContainer)dialog, s, layout, null);
    }
    
    public DialogContentPane(final JAPDialog japDialog, final String s, final Layout layout) {
        this((RootPaneContainer)japDialog, s, layout, null);
    }
    
    public DialogContentPane(final JDialog dialog, final String s, final DialogContentPaneOptions dialogContentPaneOptions) {
        this((RootPaneContainer)dialog, s, new Layout(""), dialogContentPaneOptions);
    }
    
    public DialogContentPane(final JAPDialog japDialog, final String s, final DialogContentPaneOptions dialogContentPaneOptions) {
        this((RootPaneContainer)japDialog, s, new Layout(""), dialogContentPaneOptions);
    }
    
    public DialogContentPane(final JDialog dialog, final String s, final Layout layout, final DialogContentPaneOptions dialogContentPaneOptions) {
        this((RootPaneContainer)dialog, s, layout, dialogContentPaneOptions);
    }
    
    public DialogContentPane(final JAPDialog japDialog, final String s, final Layout layout, final DialogContentPaneOptions dialogContentPaneOptions) {
        this((RootPaneContainer)japDialog, s, layout, dialogContentPaneOptions);
    }
    
    public DialogContentPane(final JDialog dialog) {
        this((RootPaneContainer)dialog, null, new Layout(""), null);
    }
    
    public DialogContentPane(final JAPDialog japDialog) {
        this((RootPaneContainer)japDialog, null, new Layout(""), null);
    }
    
    public DialogContentPane(final JDialog dialog, final Layout layout) {
        this((RootPaneContainer)dialog, null, layout, null);
    }
    
    public DialogContentPane(final JAPDialog japDialog, final Layout layout) {
        this((RootPaneContainer)japDialog, null, layout, null);
    }
    
    public DialogContentPane(final JDialog dialog, final DialogContentPaneOptions dialogContentPaneOptions) {
        this((RootPaneContainer)dialog, null, new Layout(""), dialogContentPaneOptions);
    }
    
    public DialogContentPane(final JAPDialog japDialog, final DialogContentPaneOptions dialogContentPaneOptions) {
        this((RootPaneContainer)japDialog, null, new Layout(""), dialogContentPaneOptions);
    }
    
    public DialogContentPane(final JDialog dialog, final Layout layout, final DialogContentPaneOptions dialogContentPaneOptions) {
        this((RootPaneContainer)dialog, null, layout, dialogContentPaneOptions);
    }
    
    public DialogContentPane(final JAPDialog japDialog, final Layout layout, final DialogContentPaneOptions dialogContentPaneOptions) {
        this((RootPaneContainer)japDialog, null, layout, dialogContentPaneOptions);
    }
    
    private DialogContentPane(final RootPaneContainer parentDialog, final String s, Layout layout, DialogContentPaneOptions options) {
        this.m_rememberedErrors = new Vector();
        this.m_rememberedUpdateErrors = new Vector();
        this.m_componentListeners = new Vector();
        this.m_bDisposed = false;
        this.m_idStatusMessage = 0;
        if (layout == null) {
            layout = new Layout((String)null);
        }
        this.m_layout = layout;
        if (options == null) {
            options = new DialogContentPaneOptions((JAPHelpContext.IHelpContext)null);
        }
        this.m_options = options;
        final boolean b = false;
        if (parentDialog == null) {
            throw new IllegalArgumentException("The parent dialog must not be null!");
        }
        if (this.m_options.getPreviousContentPane() != null && this.m_options.getPreviousContentPane().m_parentDialog != parentDialog) {
            throw new IllegalArgumentException("Chained content panes must refer to the same dialog!");
        }
        if (this.m_options.getOptionType() != Integer.MIN_VALUE && this.m_options.getOptionType() != -1 && this.m_options.getOptionType() != -2147483647 && this.m_options.getOptionType() != 2 && this.m_options.getOptionType() != 1 && this.m_options.getOptionType() != 0) {
            throw new IllegalArgumentException("Unknown option type!");
        }
        if (this.m_layout.getMessageType() != -1 && this.m_layout.getMessageType() != 3 && this.m_layout.getMessageType() != 0 && this.m_layout.getMessageType() != 2 && this.m_layout.getMessageType() != 1) {
            throw new IllegalArgumentException("Unknown message type!");
        }
        if (this instanceof IWizardSuitable) {
            this.m_defaultButtonOperation = 266;
        }
        else {
            this.m_defaultButtonOperation = 0;
        }
        this.m_parentDialog = parentDialog;
        this.m_previousContentPane = this.m_options.getPreviousContentPane();
        this.m_icon = this.m_layout.getIcon();
        if (this.m_icon == null) {
            this.m_icon = getMessageIcon(this.m_layout.getMessageType());
        }
        if (this.m_options.getHelpContext() != null) {
            if (this.m_options.getHelpContext() instanceof JAPHelpContext.IURLHelpContext) {
                this.m_helpContext = new JAPHelpContext.IURLHelpContext() {
                    public Component getHelpExtractionDisplayContext() {
                        return DialogContentPane.this.getContentPane();
                    }
                    
                    public String getHelpContext() {
                        return DialogContentPane.this.m_options.getHelpContext().getHelpContext();
                    }
                    
                    public String getURLMessage() {
                        return ((JAPHelpContext.IURLHelpContext)DialogContentPane.this.m_options.getHelpContext()).getURLMessage();
                    }
                    
                    public URL getHelpURL() {
                        return ((JAPHelpContext.IURLHelpContext)DialogContentPane.this.m_options.getHelpContext()).getHelpURL();
                    }
                };
            }
            else {
                this.m_helpContext = new JAPHelpContext.IHelpContext() {
                    public Component getHelpExtractionDisplayContext() {
                        return DialogContentPane.this.getContentPane();
                    }
                    
                    public String getHelpContext() {
                        return DialogContentPane.this.m_options.getHelpContext().getHelpContext();
                    }
                };
            }
        }
        else {
            this.m_helpContext = null;
        }
        this.m_rootPane = new JPanel(new BorderLayout());
        this.m_titlePane = new JPanel(new GridBagLayout());
        this.m_rootPane.add(this.m_titlePane, "Center");
        this.addDialogComponentListener(new DialogComponentListener());
        this.addDialogWindowListener(new DialogWindowListener());
        this.setContentPane(new JPanel());
        if (this.m_layout.getTitle() != null) {
            if (this.m_layout.getTitle().trim().length() > 0) {
                this.m_titlePane.setBorder(new TitledBorder(this.m_layout.getTitle()));
            }
            (this.m_lblMessage = new JAPHtmlMultiLineLabel()).setFontStyle(1);
            this.clearStatusMessage();
            this.m_rootPane.add(this.m_lblMessage, "South");
        }
        if (s != null && s.trim().length() > 0) {
            this.m_strText = JAPHtmlMultiLineLabel.removeHTMLHEADAndBODYTags(s);
            (this.m_lblText = new JAPHtmlMultiLineLabel("<font color=#000000>" + this.m_strText + "</font>", b ? 1 : 0)).setFontStyle(0);
        }
        this.m_textConstraints = new GridBagConstraints();
        this.m_textConstraints.gridx = 0;
        this.m_textConstraints.gridy = 1;
        this.m_textConstraints.weightx = 1.0;
        this.m_textConstraints.weighty = 0.0;
        this.m_textConstraints.anchor = 11;
        this.m_textConstraints.fill = 2;
        this.m_textConstraints.insets = new Insets(5, 5, 5, 5);
        if (this.m_layout.isCentered()) {
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 10.0;
            gridBagConstraints.anchor = 11;
            gridBagConstraints.fill = 3;
            this.m_titlePane.add(new JPanel(), gridBagConstraints);
            gridBagConstraints.gridy = 4;
            this.m_titlePane.add(new JPanel(), gridBagConstraints);
        }
        if (this.m_previousContentPane != null) {
            this.m_previousContentPane.setNextContentPane(this);
        }
        this.m_bHasHadWizardLayout = false;
        this.setButtonValue(Integer.MIN_VALUE);
        this.createOptions();
        if (this.m_options.getOptionType() == -1 || this.m_options.getOptionType() == 0 || this.m_options.getOptionType() == 2 || this.m_options.getOptionType() == 1) {
            this.setDefaultButton(2);
        }
        else if (this.m_options.getOptionType() == -2147483647) {
            this.setDefaultButton(1);
        }
        else if (this.getButtonHelp() != null) {
            this.setDefaultButton(4);
        }
        else {
            this.setDefaultButton(5);
        }
        this.addComponentListener(new ComponentAdapter() {
            public void componentShown(final ComponentEvent componentEvent) {
                if (DialogContentPane.this.hasWizardLayout()) {
                    DialogContentPane.this.setTextOfWizardNextButton();
                }
                DialogContentPane.this.validateDialog();
            }
        });
    }
    
    public static Icon getMessageIcon(final int n) {
        int n2;
        if (n == 1) {
            n2 = 1;
        }
        else if (n == 3) {
            n2 = 2;
        }
        else if (n == 2) {
            n2 = 3;
        }
        else {
            if (n != 0) {
                return DialogContentPane.MESSAGE_ICONS[0];
            }
            n2 = 4;
        }
        Icon messageIcon = DialogContentPane.MESSAGE_ICONS[n2];
        if (messageIcon == null) {
            messageIcon = findMessageIcon(new JOptionPane("", n));
            DialogContentPane.MESSAGE_ICONS[n2] = messageIcon;
        }
        return GUIUtils.createScaledIcon(messageIcon, GUIUtils.getIconResizer());
    }
    
    public void updateDialogOptimalSized() {
        updateDialogOptimalSized(this);
    }
    
    public static void updateDialogOptimalSized(final DialogContentPane dialogContentPane) {
        if (dialogContentPane == null) {
            return;
        }
        int max = 100;
        DialogContentPane nextContentPane = dialogContentPane;
        do {
            max = Math.max(max, nextContentPane.getContentPane().getPreferredSize().width);
            nextContentPane = nextContentPane.getNextContentPane();
        } while (nextContentPane != null);
        int n = 0;
        int n2 = 0;
        DialogContentPane dialogContentPane2 = dialogContentPane;
        Label_0261: {
            if (dialogContentPane.m_parentDialog instanceof JDialog) {
                final JDialog dialog = (JDialog)dialogContentPane.m_parentDialog;
                while (!dialogContentPane2.isDialogVisible()) {
                    dialogContentPane2.updateDialog(max, false);
                    dialogContentPane2.m_rootPane.setPreferredSize(null);
                    dialog.pack();
                    n = Math.max(n, dialog.getSize().width);
                    n2 = Math.max(n2, dialog.getSize().height);
                    dialogContentPane2 = dialogContentPane2.getNextContentPane();
                    if (dialogContentPane2 == null) {
                        dialog.setSize(new Dimension(n, n2));
                        break Label_0261;
                    }
                }
                throw new IllegalStateException("You may not optimise the dialog size while it is visible!");
            }
            final JAPDialog japDialog = (JAPDialog)dialogContentPane.m_parentDialog;
            while (!dialogContentPane2.isDialogVisible()) {
                dialogContentPane2.updateDialog(max, false);
                dialogContentPane2.m_rootPane.setPreferredSize(null);
                japDialog.pack();
                n = Math.max(n, japDialog.getSize().width);
                n2 = Math.max(n2, japDialog.getSize().height);
                dialogContentPane2 = dialogContentPane2.getNextContentPane();
                if (dialogContentPane2 == null) {
                    japDialog.setSize(new Dimension(n, n2));
                    break Label_0261;
                }
            }
            throw new IllegalStateException("You may not optimise the dialog size while it is visible!");
        }
        DialogContentPane nextContentPane2 = dialogContentPane;
        do {
            nextContentPane2.m_rootPane.setPreferredSize(new Dimension(2500, 2500));
            nextContentPane2 = nextContentPane2.getNextContentPane();
        } while (nextContentPane2 != null);
        dialogContentPane.updateDialog();
    }
    
    public final boolean hasPreviousContentPane() {
        DialogContentPane previousContentPane = this;
        while ((previousContentPane = previousContentPane.getPreviousContentPane()) != null) {
            if (!previousContentPane.isMoveBackAllowed()) {
                return false;
            }
            try {
                if (!previousContentPane.isSkippedAsPreviousContentPane()) {
                    return true;
                }
                continue;
            }
            catch (Exception ex) {
                return false;
            }
        }
        return false;
    }
    
    public final boolean hasNextContentPane() {
        DialogContentPane nextContentPane = this;
        while ((nextContentPane = nextContentPane.getNextContentPane()) != null) {
            if (!nextContentPane.isMoveForwardAllowed()) {
                return false;
            }
            try {
                if (!nextContentPane.isSkippedAsNextContentPane()) {
                    return true;
                }
                continue;
            }
            catch (Exception ex) {
                return false;
            }
        }
        return false;
    }
    
    public final boolean hasWizardLayout() {
        DialogContentPane previousContentPane = this;
        if (!(previousContentPane instanceof IWizardSuitable) || previousContentPane instanceof IWizardSuitableNoWizardButtons || (this.getNextContentPane() == null && this.getPreviousContentPane() == null)) {
            return false;
        }
        while ((previousContentPane = previousContentPane.getPreviousContentPane()) != null) {
            if (!(previousContentPane instanceof IWizardSuitable)) {
                return false;
            }
        }
        DialogContentPane nextContentPane = this;
        while ((nextContentPane = nextContentPane.getNextContentPane()) != null) {
            if (!(nextContentPane instanceof IWizardSuitable)) {
                return false;
            }
        }
        return true;
    }
    
    public final DialogContentPane getNextContentPane() {
        return this.m_nextContentPane;
    }
    
    public final DialogContentPane getPreviousContentPane() {
        return this.m_previousContentPane;
    }
    
    public final boolean moveToPreviousContentPane() {
        return this.moveToContentPane(false);
    }
    
    public CheckError[] checkYesOK() {
        return null;
    }
    
    public CheckError[] checkNo() {
        return null;
    }
    
    public CheckError[] checkCancel() {
        return null;
    }
    
    public CheckError[] checkUpdate() {
        return null;
    }
    
    public boolean isSkippedAsNextContentPane() {
        return false;
    }
    
    public boolean isMoveBackAllowed() {
        return true;
    }
    
    public boolean isMoveForwardAllowed() {
        return true;
    }
    
    public boolean isSkippedAsPreviousContentPane() {
        return false;
    }
    
    public final boolean moveToNextContentPane() {
        return this.moveToContentPane(true);
    }
    
    public final JComponent getContentPane() {
        return this.m_contentPane;
    }
    
    public final void showDialog() {
        if (this.m_parentDialog instanceof JDialog) {
            ((JDialog)this.m_parentDialog).setVisible(true);
        }
        else {
            ((JAPDialog)this.m_parentDialog).setVisible(true);
        }
    }
    
    public final void setContentPane(final JComponent contentPane) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 11;
        gridBagConstraints.fill = 1;
        if (this.m_contentPane != null) {
            this.m_titlePane.remove(this.m_contentPane);
        }
        this.m_titlePane.add(contentPane, gridBagConstraints);
        this.m_contentPane = contentPane;
    }
    
    public final String getHelpContext() {
        if (this.m_helpContext == null) {
            return null;
        }
        return this.m_helpContext.getHelpContext();
    }
    
    public Component getHelpExtractionDisplayContext() {
        if (this.m_helpContext == null) {
            return null;
        }
        return this.m_helpContext.getHelpExtractionDisplayContext();
    }
    
    public final void clearStatusMessage(final int n) {
        if (this.m_idStatusMessage == n) {
            this.clearStatusMessage();
        }
    }
    
    public final void clearStatusMessage() {
        if (this.m_lblMessage != null) {
            this.m_lblMessage.setText("T");
            this.m_lblMessage.setPreferredSize(this.m_lblMessage.getPreferredSize());
            this.m_lblMessage.removeMouseListener(this.m_linkedDialog);
            this.m_linkedDialog = null;
            this.m_lblMessage.setText("");
            this.m_lblMessage.setToolTipText(null);
        }
    }
    
    public final void printStatusMessage(final String s) {
        this.printStatusMessage(s, 1);
    }
    
    public final void printStatusMessage(final String s, final int n) {
        if (this.m_lblMessage != null) {
            this.printStatusMessageInternal(s, n);
        }
        else {
            JAPDialog.showConfirmDialog(this.getContentPane(), s, -1, n);
        }
    }
    
    public final int printErrorStatusMessage(final int n, final Throwable t) {
        return this.printErrorStatusMessage(null, n, t);
    }
    
    public final int printErrorStatusMessage(final String s, final int n) {
        return this.printErrorStatusMessage(s, n, null);
    }
    
    public final int printErrorStatusMessage(final String s, final int n, final Throwable t) {
        return this.printErrorStatusMessage(s, n, t, true);
    }
    
    public final void validateDialog() {
        if (this.getDialog() instanceof JDialog) {
            ((JDialog)this.getDialog()).validate();
        }
        else {
            ((JAPDialog)this.getDialog()).validate();
        }
    }
    
    public final synchronized CheckError[] updateDialog() {
        return this.updateDialog(100, true);
    }
    
    private final synchronized CheckError[] updateDialog(final boolean b) {
        return this.updateDialog(100, b);
    }
    
    private int printErrorStatusMessage(String s, int gui, final Throwable t, final boolean b) {
        boolean b2 = false;
        int printStatusMessageInternal = 0;
        try {
            s = JAPDialog.retrieveErrorMessage(s, t);
            if (s == null) {
                s = JAPMessages.getString(JAPDialog.MSG_ERROR_UNKNOWN);
                b2 = true;
            }
            if (!LogType.isValidLogType(gui)) {
                gui = LogType.GUI;
            }
            if (this.m_lblMessage != null) {
                if (b) {
                    printStatusMessageInternal = this.printStatusMessageInternal(s, 0);
                }
                LogHolder.log(3, gui, s, true);
                if (t != null) {
                    if (b2) {
                        LogHolder.log(3, gui, t);
                    }
                    else {
                        LogHolder.log(7, gui, t);
                    }
                }
            }
            else if (b) {
                JAPDialog.showErrorDialog(this.getContentPane(), s, gui, t);
            }
        }
        catch (Throwable t2) {
            JAPDialog.showErrorDialog(this.getContentPane(), LogType.GUI, t2);
        }
        return printStatusMessageInternal;
    }
    
    private final synchronized CheckError[] updateDialog(final int n, final boolean b) {
        if (this.isDisposed()) {
            return null;
        }
        CheckError[] checkUpdate;
        if (b) {
            checkUpdate = this.checkUpdate();
        }
        else {
            checkUpdate = null;
        }
        if (checkUpdate != null && checkUpdate.length > 0) {
            return checkUpdate;
        }
        this.createOptions();
        final Object[] array = { this.m_panelOptions };
        if (this.m_lblText != null) {
            this.m_titlePane.remove(this.m_lblText);
        }
        final JOptionPane optionPane = new JOptionPane(this.m_rootPane, this.m_layout.getMessageType(), 0, this.m_icon, array);
        if (this.m_tempDialog != null) {
            this.m_tempDialog.dispose();
        }
        (this.m_tempDialog = optionPane.createDialog(null, "")).pack();
        if (this.m_lblText != null) {
            if (this.isDialogVisible()) {
                this.m_lblText = new JAPHtmlMultiLineLabel(this.m_lblText.getText(), this.m_lblText.getFont(), 0);
            }
            else if (this.m_lblText.getPreferredSize().width > this.m_contentPane.getWidth() - 10) {
                this.m_lblText.setPreferredWidth(Math.max(this.m_lblText.getMinimumSize().width, Math.max(this.m_contentPane.getWidth() - 10, n)));
            }
            this.m_titlePane.add(this.m_lblText, this.m_textConstraints);
        }
        this.clearStatusMessage();
        if (this.m_currentlyActiveContentPane != null) {
            this.m_currentlyActiveContentPane.removeComponentListener(this.m_currentlyActiveContentPaneComponentListener);
        }
        this.m_currentlyActiveContentPane = this.m_tempDialog.getContentPane();
        this.m_currentlyActiveContentPaneComponentListener = new ContentPaneComponentListener();
        this.m_currentlyActiveContentPane.addComponentListener(this.m_currentlyActiveContentPaneComponentListener);
        this.m_parentDialog.setContentPane(this.m_currentlyActiveContentPane);
        if (this.isDialogVisible()) {
            final Vector vector = (Vector)this.m_componentListeners.clone();
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).componentShown(new ComponentEvent(this.m_currentlyActiveContentPane, 102));
            }
        }
        if (this.m_defaultButton == 2) {
            this.getDialog().getRootPane().setDefaultButton(this.getButtonYesOK());
        }
        else if (this.m_defaultButton == 1) {
            this.getDialog().getRootPane().setDefaultButton(this.getButtonCancel());
        }
        else if (this.m_defaultButton == 3) {
            this.getDialog().getRootPane().setDefaultButton(this.getButtonNo());
        }
        else if (this.m_defaultButton == 4) {
            this.getDialog().getRootPane().setDefaultButton(this.getButtonHelp());
        }
        else if (this.m_defaultButton != 5) {
            this.getDialog().getRootPane().setDefaultButton(null);
        }
        this.m_titlePane.invalidate();
        if (this.m_lblText != null) {
            this.m_lblText.invalidate();
        }
        this.m_rootPane.invalidate();
        this.m_contentPane.invalidate();
        if (this.m_parentDialog instanceof JAPDialog) {
            ((JAPDialog)this.m_parentDialog).validate();
        }
        else {
            ((JDialog)this.m_parentDialog).validate();
        }
        return null;
    }
    
    public final JButton getButtonHelp() {
        return this.m_btnHelp;
    }
    
    public final JButton getButtonCancel() {
        return this.m_btnCancel;
    }
    
    public final JButton getButtonYesOK() {
        return this.m_btnYesOK;
    }
    
    public final JButton getButtonNo() {
        return this.m_btnNo;
    }
    
    public final void setDefaultButton(final int defaultButton) {
        if (defaultButton < 0 || defaultButton > 5) {
            this.m_defaultButton = 0;
        }
        else {
            this.m_defaultButton = defaultButton;
        }
    }
    
    public final int getDefaultButton() {
        return this.m_defaultButton;
    }
    
    public final int getDefaultButtonOperation() {
        return this.m_defaultButtonOperation;
    }
    
    public final void setDefaultButtonOperation(final int defaultButtonOperation) throws IllegalArgumentException {
        this.m_defaultButtonOperation = defaultButtonOperation;
    }
    
    public Object getValue() {
        return null;
    }
    
    public final int getButtonValue() {
        return this.m_value;
    }
    
    public final void setButtonValue(final int value) {
        if (2 == value || 0 == value || -1 == value || 0 == value || 1 == value) {
            this.m_value = value;
        }
        else {
            this.m_value = Integer.MIN_VALUE;
        }
    }
    
    public final boolean hasValidValue() {
        return this.getButtonValue() != 2 && this.getButtonValue() != -1 && this.getButtonValue() != Integer.MIN_VALUE;
    }
    
    public boolean isAutomaticFocusSettingEnabled() {
        return true;
    }
    
    public final boolean isActive() {
        return this.m_currentlyActiveContentPane != null && this.m_parentDialog.getContentPane() == this.m_currentlyActiveContentPane;
    }
    
    public final boolean isVisible() {
        return this.isActive() && this.isDialogVisible();
    }
    
    public String getText() {
        return this.m_strText;
    }
    
    public synchronized void setText(final String s) {
        if (this.m_strText == null) {
            throw new IllegalStateException("This content pane does not contain a text field!");
        }
        synchronized (this.getDialog()) {
            if (this.isVisible()) {
                this.m_lblText.setText(JAPHtmlMultiLineLabel.removeHTMLHEADAndBODYTags(s));
                this.getDialog().notifyAll();
                return;
            }
            final JAPDialog parentDialog = new JAPDialog((JAPDialog)null, "");
            final RootPaneContainer dialog = this.getDialog();
            final boolean active = this.isActive();
            boolean b = false;
            int n = 0;
            Dimension size;
            if (dialog instanceof JDialog) {
                size = ((JDialog)dialog).getSize();
            }
            else {
                size = ((JAPDialog)dialog).getSize();
            }
            if (size.width == 0 || size.height == 0) {
                throw new IllegalStateException("The parent dialog has a size <=0! This is not allowed when changing the text.");
            }
            parentDialog.setSize(size);
            if (this.m_lblText != null) {
                this.m_titlePane.remove(this.m_lblText);
            }
            this.m_strText = JAPHtmlMultiLineLabel.removeHTMLHEADAndBODYTags(s);
            if (this.m_strText == null || this.m_strText.trim().length() == 0) {
                this.m_strText = "";
                return;
            }
            (this.m_lblText = new JAPHtmlMultiLineLabel(this.m_strText, 0)).setFontStyle(0);
            this.m_titlePane.add(this.m_lblText, this.m_textConstraints);
            this.m_parentDialog = parentDialog;
            if (this.m_rootPane.getPreferredSize().equals(new Dimension(2500, 2500))) {
                b = true;
            }
            this.m_rootPane.setPreferredSize(null);
            if (this.m_lblSeeFullText != null) {
                this.m_titlePane.remove(this.m_lblSeeFullText);
                this.m_lblSeeFullText = null;
            }
            this.updateDialog(false);
            this.m_lblText.setText(this.m_strText);
            this.m_lblText.setPreferredWidth(this.getContentPane().getSize().width);
            final GridBagConstraints gridBagConstraints = (GridBagConstraints)this.m_textConstraints.clone();
            gridBagConstraints.gridy = 2;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            (this.m_lblSeeFullText = new JAPHtmlMultiLineLabel()).setPreferredSize(new Dimension(this.getContentPane().getSize().width, 0));
            this.m_titlePane.add(this.m_lblSeeFullText, gridBagConstraints);
            this.updateDialog(false);
            this.m_titlePane.remove(this.m_lblSeeFullText);
            if (parentDialog.getContentPane().getSize().height < parentDialog.getContentPane().getPreferredSize().height) {
                final int height = parentDialog.getSize().height;
                final int width = parentDialog.getSize().width;
                parentDialog.pack();
                if (parentDialog.getSize().height > size.height * 1.2 || parentDialog.getSize().width > size.width * 1.2) {
                    parentDialog.setSize(width, height);
                }
                else if (dialog instanceof JDialog) {
                    ((JDialog)dialog).setSize(parentDialog.getSize());
                }
                else {
                    ((JAPDialog)dialog).setSize(parentDialog.getSize());
                }
            }
            if (parentDialog.getContentPane().getSize().height < parentDialog.getContentPane().getPreferredSize().height) {
                (this.m_lblSeeFullText = new JAPHtmlMultiLineLabel("<A href=''>(" + JAPMessages.getString(DialogContentPane.MSG_SEE_FULL_MESSAGE) + ")</A>", this.m_lblText.getFont(), 0)).setCursor(Cursor.getPredefinedCursor(12));
                this.m_lblSeeFullText.setPreferredSize(new Dimension(this.getContentPane().getSize().width, this.m_lblSeeFullText.getPreferredSize().height));
                this.m_lblSeeFullText.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(final MouseEvent mouseEvent) {
                        if (DialogContentPane.this.m_layout.getTitle() != null) {
                            JAPDialog.showMessageDialog(DialogContentPane.this.m_lblSeeFullText, DialogContentPane.this.m_strText, DialogContentPane.this.m_layout.getTitle());
                        }
                        else {
                            JAPDialog.showMessageDialog(DialogContentPane.this.m_lblSeeFullText, DialogContentPane.this.m_strText);
                        }
                    }
                });
                this.m_titlePane.add(this.m_lblSeeFullText, gridBagConstraints);
                final int htmlDocumentLength = this.m_lblText.getHTMLDocumentLength();
                int n2 = htmlDocumentLength / 2;
                int n3 = 0;
                for (int i = 0; i < 6; ++i) {
                    if (i == 5) {
                        if (n == 0) {
                            this.m_titlePane.remove(this.m_lblText);
                            this.m_lblText = null;
                            this.updateDialog(false);
                            break;
                        }
                        n2 = n3;
                    }
                    this.m_lblText.setText(this.m_strText);
                    this.m_lblText.cutHTMLDocument(n2);
                    this.m_lblText.setText(JAPHtmlMultiLineLabel.removeHTMLHEADAndBODYTags(this.m_lblText.getText()) + "...");
                    this.updateDialog(false);
                    if (parentDialog.getContentPane().getSize().height < parentDialog.getContentPane().getPreferredSize().height) {
                        if (n != 0) {
                            n2 = n3 + n3 / (i + 2);
                        }
                        else {
                            n2 /= 2;
                        }
                    }
                    else {
                        n = 1;
                        if (n3 < n2) {
                            n3 = n2;
                        }
                        n2 += n2 / 2;
                    }
                }
                if (n != 0 && n3 >= htmlDocumentLength) {
                    this.m_lblText.setText(this.m_strText);
                    this.m_titlePane.remove(this.m_lblSeeFullText);
                }
            }
            if (this.m_lblText != null) {
                this.m_lblText.setText("<font color=#000000>" + JAPHtmlMultiLineLabel.removeHTMLHEADAndBODYTags(this.m_lblText.getText()) + "</font>");
            }
            this.m_parentDialog = dialog;
            if (b) {
                this.m_rootPane.setPreferredSize(new Dimension(2500, 2500));
            }
            if (active) {
                this.updateDialog(false);
            }
            this.getDialog().notifyAll();
        }
    }
    
    public RootPaneContainer getDialog() {
        return this.m_parentDialog;
    }
    
    private Container getDialogContentPane() {
        if (this.m_parentDialog instanceof JAPDialog) {
            return ((JAPDialog)this.m_parentDialog).getContentPane();
        }
        return ((JDialog)this.m_parentDialog).getContentPane();
    }
    
    public final boolean isDialogVisible() {
        return (this.m_parentDialog instanceof JAPDialog && ((JAPDialog)this.m_parentDialog).isVisible()) || (this.m_parentDialog instanceof JDialog && ((JDialog)this.m_parentDialog).isVisible());
    }
    
    public void addDialogWindowListener(final WindowListener windowListener) {
        if (this.m_parentDialog instanceof JDialog) {
            ((JDialog)this.m_parentDialog).addWindowListener(windowListener);
        }
        else {
            ((JAPDialog)this.m_parentDialog).addWindowListener(windowListener);
        }
    }
    
    public synchronized void addComponentListener(final ComponentListener componentListener) {
        if (componentListener != null) {
            this.m_componentListeners.addElement(componentListener);
        }
    }
    
    public synchronized void removeComponentListener(final ComponentListener componentListener) {
        this.m_componentListeners.removeElement(componentListener);
    }
    
    public void addDialogComponentListener(final ComponentListener componentListener) {
        if (this.m_parentDialog instanceof JDialog) {
            ((JDialog)this.m_parentDialog).addComponentListener(componentListener);
        }
        else {
            ((JAPDialog)this.m_parentDialog).addComponentListener(componentListener);
        }
    }
    
    public void removeDialogComponentListener(final ComponentListener componentListener) {
        if (this.m_parentDialog instanceof JDialog) {
            ((JDialog)this.m_parentDialog).removeComponentListener(componentListener);
        }
        else {
            ((JAPDialog)this.m_parentDialog).removeComponentListener(componentListener);
        }
    }
    
    public void removeDialogWindowListener(final WindowListener windowListener) {
        if (this.m_parentDialog instanceof JDialog) {
            ((JDialog)this.m_parentDialog).removeWindowListener(windowListener);
        }
        else {
            ((JAPDialog)this.m_parentDialog).removeWindowListener(windowListener);
        }
    }
    
    public boolean isDisposed() {
        return this.m_bDisposed;
    }
    
    public synchronized void dispose() {
        this.m_bDisposed = true;
        if (this.m_tempDialog != null) {
            this.m_tempDialog.dispose();
        }
        if (this.m_titlePane != null) {
            this.m_titlePane.removeAll();
        }
        this.m_titlePane = null;
        if (this.m_rootPane != null) {
            this.m_rootPane.removeAll();
        }
        this.m_rootPane = null;
        if (this.m_contentPane != null) {
            this.m_contentPane.removeAll();
        }
        this.m_contentPane = null;
        if (this.m_panelOptions != null) {
            this.m_panelOptions.removeAll();
        }
        this.m_panelOptions = null;
        this.m_parentDialog = null;
        this.m_lblText = null;
        this.m_componentListeners.removeAllElements();
        if (this.m_btnCancel != null) {
            this.m_btnCancel.removeActionListener(this.m_buttonListener);
        }
        if (this.m_btnYesOK != null) {
            this.m_btnYesOK.removeActionListener(this.m_buttonListener);
        }
        if (this.m_btnNo != null) {
            this.m_btnNo.removeActionListener(this.m_buttonListener);
        }
        if (this.m_btnHelp != null) {
            this.m_btnHelp.removeActionListener(this.m_buttonListener);
        }
        this.m_buttonListener = null;
        if (this.m_currentlyActiveContentPane != null) {
            this.m_currentlyActiveContentPane.removeComponentListener(this.m_currentlyActiveContentPaneComponentListener);
        }
        this.m_currentlyActiveContentPaneComponentListener = null;
        this.m_currentlyActiveContentPane = null;
        this.m_nextContentPane = null;
        this.m_previousContentPane = null;
    }
    
    public final void closeDialog(final boolean b) {
        try {
            if (b) {
                if (this.m_parentDialog instanceof JDialog) {
                    ((JDialog)this.m_parentDialog).dispose();
                }
                else {
                    try {
                        ((JAPDialog)this.m_parentDialog).dispose();
                    }
                    catch (IllegalMonitorStateException ex) {
                        LogHolder.log(7, LogType.GUI, ex);
                    }
                }
            }
            else if (this.m_parentDialog instanceof JDialog) {
                ((JDialog)this.m_parentDialog).setVisible(false);
            }
            else {
                ((JAPDialog)this.m_parentDialog).setVisible(false);
            }
        }
        catch (NullPointerException ex2) {
            if (!this.isDisposed()) {
                throw ex2;
            }
        }
    }
    
    private JAPDialog getJAPDialog() {
        if (this.m_parentDialog instanceof JAPDialog) {
            return (JAPDialog)this.m_parentDialog;
        }
        return null;
    }
    
    private static Icon findMessageIcon(final JOptionPane optionPane) {
        Icon icon = null;
        for (int i = 0; i < optionPane.getComponentCount(); ++i) {
            if (optionPane.getComponent(i) instanceof Container) {
                final Container container = (Container)optionPane.getComponent(i);
                for (int j = 0; j < container.getComponentCount(); ++j) {
                    if (container.getComponent(j) instanceof JLabel) {
                        icon = ((JLabel)container.getComponent(j)).getIcon();
                        break;
                    }
                }
            }
        }
        return icon;
    }
    
    private final synchronized int printStatusMessageInternal(final String s, final int n) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        String s2 = JAPHtmlMultiLineLabel.removeTagsAndNewLines(s);
        String s3;
        if (0 == n || 2 == n) {
            s3 = "red";
        }
        else {
            s3 = "black";
        }
        final JAPHtmlMultiLineLabel japHtmlMultiLineLabel = new JAPHtmlMultiLineLabel(s2, this.m_lblMessage.getFont());
        String s5;
        if (japHtmlMultiLineLabel.getPreferredSize().width > this.m_lblMessage.getSize().width) {
            String s4;
            if (0 == n) {
                s4 = JAPMessages.getString(JAPDialog.MSG_TITLE_ERROR);
            }
            else if (2 == n) {
                s4 = JAPMessages.getString(JAPDialog.MSG_TITLE_WARNING);
            }
            else {
                s4 = JAPMessages.getString(JAPDialog.MSG_TITLE_INFO);
            }
            this.clearStatusMessage();
            int n2 = 0;
            for (int n3 = s2.length() / 2, n4 = 0; n3 > 1 && (n4 < 6 || n2 >= s2.length()); ++n4) {
                japHtmlMultiLineLabel.setText(s2.substring(0, n3) + "...");
                if (japHtmlMultiLineLabel.getPreferredSize().width <= this.m_lblMessage.getSize().width) {
                    n2 = Math.max(n2, n3) - 2;
                    n3 += n3 / (n4 + 2);
                }
                else {
                    n3 /= 2;
                }
            }
            if (n2 <= 5) {
                s2 = "...";
            }
            else {
                s2 = s2.substring(0, n2) + "...";
            }
            s5 = " href=\"\"";
            this.m_lblMessage.setToolTipText(JAPMessages.getString(DialogContentPane.MSG_SEE_FULL_MESSAGE));
            this.m_linkedDialog = new LinkedDialog(s, s4, -1, n);
            this.m_lblMessage.addMouseListener(this.m_linkedDialog);
            this.m_lblMessage.setCursor(Cursor.getPredefinedCursor(12));
        }
        else {
            this.clearStatusMessage();
            s5 = "";
        }
        this.m_lblMessage.setText("<A style=\"color:" + s3 + "\"" + s5 + "> " + s2 + " </A>");
        this.m_lblMessage.revalidate();
        return ++this.m_idStatusMessage;
    }
    
    private void setNextContentPane(final DialogContentPane nextContentPane) {
        if (this.m_nextContentPane != null) {
            this.m_nextContentPane.m_previousContentPane = null;
        }
        this.m_nextContentPane = nextContentPane;
    }
    
    private boolean moveToContentPane(final boolean b) {
        DialogContentPane dialogContentPane = this;
        if (b) {
            while ((dialogContentPane = dialogContentPane.getNextContentPane()) != null) {
                if (!dialogContentPane.isSkippedAsNextContentPane()) {
                    break;
                }
            }
        }
        else {
            while ((dialogContentPane = dialogContentPane.getPreviousContentPane()) != null && dialogContentPane.isSkippedAsPreviousContentPane()) {}
        }
        if (dialogContentPane == null) {
            if ((this.getDefaultButtonOperation() & 0x2) > 0) {
                this.closeDialog(true);
            }
            else if ((this.getDefaultButtonOperation() & 0x1) > 0) {
                this.closeDialog(false);
            }
            return false;
        }
        final CheckError[] updateDialog = dialogContentPane.updateDialog();
        boolean b2 = false;
        if (this.checkErrors(updateDialog, this.m_rememberedUpdateErrors)) {
            if (dialogContentPane.isVisible()) {
                if (b) {
                    if (dialogContentPane.getButtonYesOK() != null && dialogContentPane.getButtonYesOK().isEnabled()) {
                        if (dialogContentPane.isAutomaticFocusSettingEnabled()) {
                            dialogContentPane.getButtonYesOK().requestFocus();
                        }
                        this.getDialog().getRootPane().setDefaultButton(dialogContentPane.getButtonYesOK());
                        b2 = true;
                    }
                }
                else if (dialogContentPane.getButtonNo() != null && dialogContentPane.getButtonNo().isEnabled()) {
                    if (dialogContentPane.isAutomaticFocusSettingEnabled()) {
                        dialogContentPane.getButtonYesOK().requestFocus();
                    }
                    this.getDialog().getRootPane().setDefaultButton(dialogContentPane.getButtonYesOK());
                    b2 = true;
                }
                else if (dialogContentPane.getButtonYesOK() != null && dialogContentPane.getButtonYesOK().isEnabled()) {
                    if (dialogContentPane.isAutomaticFocusSettingEnabled()) {
                        dialogContentPane.getButtonYesOK().requestFocus();
                    }
                    this.getDialog().getRootPane().setDefaultButton(dialogContentPane.getButtonYesOK());
                    b2 = true;
                }
                if (!b2 && dialogContentPane.getButtonCancel() != null && dialogContentPane.getButtonCancel().isEnabled()) {
                    if (dialogContentPane.isAutomaticFocusSettingEnabled()) {
                        dialogContentPane.getButtonCancel().requestFocus();
                    }
                    this.getDialog().getRootPane().setDefaultButton(dialogContentPane.getButtonCancel());
                }
            }
            return true;
        }
        return false;
    }
    
    private boolean checkErrors(final CheckError[] array, final Vector vector) {
        for (int i = vector.size() - 1; i >= 0; --i) {
            vector.elementAt(i).undoErrorAction();
            vector.removeElementAt(i);
        }
        if (array != null && array.length > 0) {
            CheckError checkError = null;
            for (int j = 0; j < array.length; ++j) {
                if (array[j] == null) {
                    LogHolder.log(3, LogType.GUI, "Found a " + ((DialogContentPane.class$gui$dialog$DialogContentPane$CheckError == null) ? (DialogContentPane.class$gui$dialog$DialogContentPane$CheckError = class$("gui.dialog.DialogContentPane$CheckError")) : DialogContentPane.class$gui$dialog$DialogContentPane$CheckError).getName() + " " + "that is null! Ignoring it.");
                }
                else {
                    if (checkError == null || (array[j].hasDisplayableErrorMessage() && (checkError.getMessage() == null || checkError.getMessage().trim().length() == 0))) {
                        checkError = array[j];
                    }
                    if (JAPDialog.retrieveErrorMessage(array[j].getMessage(), array[j].getThrowable()) != null) {
                        this.printErrorStatusMessage(array[j].getMessage(), array[j].getLogType(), array[j].getThrowable(), false);
                    }
                    vector.addElement(array[j]);
                    array[j].doErrorAction();
                }
            }
            if (checkError == null) {
                this.printStatusMessage(JAPMessages.getString(DialogContentPane.MSG_OPERATION_FAILED), 0);
            }
            else {
                this.printStatusMessage(JAPDialog.retrieveErrorMessage(checkError.getMessage(), checkError.getThrowable()), 0);
            }
            return false;
        }
        return true;
    }
    
    private void createDefaultOptions() {
        this.m_panelOptions = new JPanel();
        if (this.m_btnCancel == null) {
            (this.m_btnCancel = new JButton()).addActionListener(this.m_buttonListener);
        }
        this.m_btnCancel.setText(JAPMessages.getString(DialogContentPane.MSG_CANCEL));
        this.m_panelOptions.add(this.m_btnCancel);
        this.m_btnCancel.setVisible(false);
        if (1 == this.m_options.getOptionType() || 2 == this.m_options.getOptionType() || -2147483647 == this.m_options.getOptionType()) {
            this.m_btnCancel.setVisible(true);
        }
        if (this.m_btnNo == null) {
            (this.m_btnNo = new JButton()).addActionListener(this.m_buttonListener);
        }
        this.m_btnNo.setText(JAPMessages.getString(DialogContentPane.MSG_NO));
        this.m_panelOptions.add(this.m_btnNo);
        this.m_btnNo.setVisible(false);
        if (0 == this.m_options.getOptionType() || 1 == this.m_options.getOptionType()) {
            this.m_btnNo.setVisible(true);
            if (this.m_btnYesOK == null) {
                (this.m_btnYesOK = new JButton()).addActionListener(this.m_buttonListener);
            }
            this.m_btnYesOK.setText(JAPMessages.getString(DialogContentPane.MSG_YES));
            this.m_panelOptions.add(this.m_btnYesOK);
        }
        else if (2 == this.m_options.getOptionType() || -1 == this.m_options.getOptionType()) {
            if (this.m_btnYesOK == null) {
                (this.m_btnYesOK = new JButton()).addActionListener(this.m_buttonListener);
            }
            this.m_btnYesOK.setText(JAPMessages.getString(DialogContentPane.MSG_OK));
            this.m_panelOptions.add(this.m_btnYesOK);
        }
        this.addHelpButton();
    }
    
    private JButton addHelpButton() {
        if (this.getHelpContext() != null) {
            if (this.m_btnHelp == null) {
                if (this.m_helpContext instanceof JAPHelpContext.IURLHelpContext) {
                    (this.m_btnHelp = new JButton(((JAPHelpContext.IURLHelpContext)this.m_helpContext).getURLMessage())).addActionListener(new ActionListener() {
                        public void actionPerformed(final ActionEvent actionEvent) {
                            AbstractOS.getInstance().openURL(((JAPHelpContext.IURLHelpContext)DialogContentPane.this.m_helpContext).getHelpURL());
                        }
                    });
                }
                else {
                    this.m_btnHelp = JAPHelp.createHelpButton(this);
                }
            }
            this.m_panelOptions.add(this.m_btnHelp);
        }
        return this.m_btnHelp;
    }
    
    private void createWizardOptions() {
        this.m_panelOptions = Box.createHorizontalBox();
        if (this.addHelpButton() != null) {
            this.m_panelOptions.add(Box.createHorizontalStrut(5));
        }
        if (this.hasPreviousContentPane()) {
            if (this.m_btnNo == null) {
                (this.m_btnNo = new JButton()).addActionListener(this.m_buttonListener);
            }
            this.m_btnNo.setText("< " + JAPMessages.getString(DialogContentPane.MSG_PREVIOUS));
            if (this.getPreviousContentPane() == null) {
                this.m_btnNo.setEnabled(false);
            }
            this.m_panelOptions.add(this.m_btnNo);
        }
        if (this.m_btnYesOK == null) {
            (this.m_btnYesOK = new JButton()).addActionListener(this.m_buttonListener);
        }
        this.setTextOfWizardNextButton();
        this.m_panelOptions.add(this.m_btnYesOK);
        this.m_panelOptions.add(Box.createHorizontalStrut(5));
        if (this.m_btnCancel == null) {
            (this.m_btnCancel = new JButton()).addActionListener(this.m_buttonListener);
        }
        this.m_btnCancel.setText(JAPMessages.getString(DialogContentPane.MSG_CANCEL));
        this.m_panelOptions.add(this.m_btnCancel);
    }
    
    private void setTextOfWizardNextButton() {
        if (this.hasNextContentPane()) {
            this.m_btnYesOK.setText(JAPMessages.getString(DialogContentPane.MSG_NEXT) + " >");
        }
        else {
            this.m_btnYesOK.setText(JAPMessages.getString(DialogContentPane.MSG_FINISH));
        }
        this.m_btnYesOK.invalidate();
    }
    
    public void setMouseListener(final MouseListener mouseListener) {
        if (this.m_strText == null) {
            throw new IllegalStateException("This content pane does not contain a text field!");
        }
        this.m_lblText.addMouseListener(mouseListener);
        this.m_lblSeeFullText.addMouseListener(mouseListener);
    }
    
    private void createOptions() {
        final boolean hasWizardLayout = this.hasWizardLayout();
        if (this.m_panelOptions != null && ((!hasWizardLayout && !this.m_bHasHadWizardLayout) || (hasWizardLayout && this.m_bHasHadWizardLayout))) {
            this.m_bHasHadWizardLayout = hasWizardLayout;
            return;
        }
        this.m_bHasHadWizardLayout = hasWizardLayout;
        if (this.m_buttonListener == null) {
            this.m_buttonListener = new ButtonListener();
        }
        if (hasWizardLayout) {
            this.createWizardOptions();
        }
        else {
            this.createDefaultOptions();
        }
    }
    
    private boolean isSomethingDoneOnClick(final CheckError[] array, final int n, final int n2, final int n3, final int n4) {
        return (array == null || array.length == 0) && (this.getDefaultButtonOperation() & (0x47 | n | n2 | n3 | n4)) > 0;
    }
    
    private boolean doDefaultButtonOperation(final CheckError[] array, final int n, final int n2, final int n3, final int n4) {
        if (!this.checkErrors(array, this.m_rememberedErrors)) {
            return false;
        }
        if (this.m_nextContentPane != null && (this.getDefaultButtonOperation() & n) > 0 && this.m_nextContentPane.isMoveForwardAllowed()) {
            return this.moveToNextContentPane();
        }
        if (this.m_previousContentPane != null && (this.getDefaultButtonOperation() & n2) > 0) {
            return this.moveToPreviousContentPane();
        }
        if ((this.getDefaultButtonOperation() & n4) > 0) {
            this.closeDialog(true);
            return true;
        }
        if ((this.getDefaultButtonOperation() & n3) > 0) {
            this.closeDialog(false);
            return true;
        }
        return false;
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
        MESSAGE_TYPES = new int[] { -1, 1, 3, 2, 0 };
        MESSAGE_ICONS = new Icon[DialogContentPane.MESSAGE_TYPES.length];
        if (!JAPDialog.isConsoleOnly()) {
            for (int i = 0; i < DialogContentPane.MESSAGE_TYPES.length; ++i) {
                final JOptionPane optionPane = new JOptionPane("", DialogContentPane.MESSAGE_TYPES[i]);
                optionPane.createDialog(null, "");
                DialogContentPane.MESSAGE_ICONS[i] = findMessageIcon(optionPane);
            }
        }
        MSG_OK = ((DialogContentPane.class$gui$dialog$DialogContentPane == null) ? (DialogContentPane.class$gui$dialog$DialogContentPane = class$("gui.dialog.DialogContentPane")) : DialogContentPane.class$gui$dialog$DialogContentPane).getName() + "_OK";
        MSG_YES = ((DialogContentPane.class$gui$dialog$DialogContentPane == null) ? (DialogContentPane.class$gui$dialog$DialogContentPane = class$("gui.dialog.DialogContentPane")) : DialogContentPane.class$gui$dialog$DialogContentPane).getName() + "_yes";
        MSG_NO = ((DialogContentPane.class$gui$dialog$DialogContentPane == null) ? (DialogContentPane.class$gui$dialog$DialogContentPane = class$("gui.dialog.DialogContentPane")) : DialogContentPane.class$gui$dialog$DialogContentPane).getName() + "_no";
        MSG_NEXT = ((DialogContentPane.class$gui$dialog$DialogContentPane == null) ? (DialogContentPane.class$gui$dialog$DialogContentPane = class$("gui.dialog.DialogContentPane")) : DialogContentPane.class$gui$dialog$DialogContentPane).getName() + "_next";
        MSG_PREVIOUS = ((DialogContentPane.class$gui$dialog$DialogContentPane == null) ? (DialogContentPane.class$gui$dialog$DialogContentPane = class$("gui.dialog.DialogContentPane")) : DialogContentPane.class$gui$dialog$DialogContentPane).getName() + "_previous";
        MSG_FINISH = ((DialogContentPane.class$gui$dialog$DialogContentPane == null) ? (DialogContentPane.class$gui$dialog$DialogContentPane = class$("gui.dialog.DialogContentPane")) : DialogContentPane.class$gui$dialog$DialogContentPane).getName() + "_finish";
        MSG_CANCEL = ((DialogContentPane.class$gui$dialog$DialogContentPane == null) ? (DialogContentPane.class$gui$dialog$DialogContentPane = class$("gui.dialog.DialogContentPane")) : DialogContentPane.class$gui$dialog$DialogContentPane).getName() + "_cancel";
        MSG_OPERATION_FAILED = ((DialogContentPane.class$gui$dialog$DialogContentPane == null) ? (DialogContentPane.class$gui$dialog$DialogContentPane = class$("gui.dialog.DialogContentPane")) : DialogContentPane.class$gui$dialog$DialogContentPane).getName() + "_operationFailed";
        MSG_SEE_FULL_MESSAGE = ((DialogContentPane.class$gui$dialog$DialogContentPane == null) ? (DialogContentPane.class$gui$dialog$DialogContentPane = class$("gui.dialog.DialogContentPane")) : DialogContentPane.class$gui$dialog$DialogContentPane).getName() + "_seeFullMessage";
    }
    
    public static class CheckError
    {
        private String m_strMessage;
        private int m_logType;
        private Throwable m_throwable;
        
        public CheckError() {
            this("", LogType.NUL, null);
        }
        
        public CheckError(final String s, final int n) {
            this(s, n, null);
        }
        
        public CheckError(final String strMessage, final int logType, final Throwable throwable) {
            this.m_strMessage = strMessage;
            this.m_logType = logType;
            this.m_throwable = throwable;
        }
        
        public void doErrorAction() {
        }
        
        public void undoErrorAction() {
        }
        
        public final int getLogType() {
            return this.m_logType;
        }
        
        public final Throwable getThrowable() {
            return this.m_throwable;
        }
        
        public final String getMessage() {
            return this.m_strMessage;
        }
        
        public final boolean hasDisplayableErrorMessage() {
            return JAPDialog.retrieveErrorMessage(this.m_strMessage, this.m_throwable) != null;
        }
    }
    
    public static class Layout
    {
        private String m_strTitle;
        private int m_messageType;
        private Icon m_icon;
        private boolean m_bCentered;
        
        public Layout() {
            this("", -1, null);
        }
        
        public Layout(final int n) {
            this("", n, null);
        }
        
        public Layout(final String s) {
            this(s, -1, null);
        }
        
        public Layout(final Icon icon) {
            this("", -1, icon);
        }
        
        public Layout(final int n, final Icon icon) {
            this("", n, icon);
        }
        
        public Layout(final String s, final int n) {
            this(s, n, null);
        }
        
        public Layout(final String s, final Icon icon) {
            this(s, -1, icon);
        }
        
        public Layout(final String strTitle, final int messageType, final Icon icon) {
            this.m_strTitle = strTitle;
            this.m_messageType = messageType;
            this.m_icon = icon;
            this.m_bCentered = true;
        }
        
        public boolean isCentered() {
            return this.m_bCentered;
        }
        
        public final String getTitle() {
            return this.m_strTitle;
        }
        
        public final int getMessageType() {
            return this.m_messageType;
        }
        
        public final Icon getIcon() {
            return this.m_icon;
        }
    }
    
    private class LinkedDialog extends MouseAdapter
    {
        private String m_strMessage;
        private String m_strTitle;
        private int m_optionType;
        private int m_messageType;
        
        public LinkedDialog(final String strMessage, final String strTitle, final int optionType, final int messageType) {
            this.m_strMessage = strMessage;
            this.m_strTitle = strTitle;
            this.m_optionType = optionType;
            this.m_messageType = messageType;
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            JAPDialog.showConfirmDialog(DialogContentPane.this.m_lblMessage, this.m_strMessage, this.m_strTitle, this.m_optionType, this.m_messageType);
        }
    }
    
    private class DialogWindowListener extends WindowAdapter
    {
        public void windowClosed(final WindowEvent windowEvent) {
            if (DialogContentPane.this.getButtonValue() == Integer.MIN_VALUE) {
                DialogContentPane.this.setButtonValue(-1);
            }
            final ComponentListener access$1100 = DialogContentPane.this.m_currentlyActiveContentPaneComponentListener;
            if (access$1100 != null) {
                access$1100.componentHidden(new ComponentEvent(DialogContentPane.this.m_currentlyActiveContentPane, 103));
            }
            if (!DialogContentPane.this.isDisposed()) {
                DialogContentPane.this.dispose();
            }
        }
        
        public void windowOpened(final WindowEvent windowEvent) {
            if (DialogContentPane.this.isVisible() && DialogContentPane.this.hasWizardLayout() && DialogContentPane.this.getButtonYesOK() != null && DialogContentPane.this.getButtonYesOK().isEnabled()) {
                if (DialogContentPane.this.isAutomaticFocusSettingEnabled()) {
                    DialogContentPane.this.getButtonYesOK().requestFocus();
                }
                DialogContentPane.this.getDialog().getRootPane().setDefaultButton(DialogContentPane.this.getButtonYesOK());
            }
            if (DialogContentPane.this.getDialog() instanceof JDialog) {
                final ComponentListener access$1100 = DialogContentPane.this.m_currentlyActiveContentPaneComponentListener;
                if (access$1100 != null) {
                    access$1100.componentShown(new ComponentEvent(DialogContentPane.this.m_currentlyActiveContentPane, 102));
                }
            }
        }
    }
    
    private class DialogComponentListener extends ComponentAdapter
    {
        public void componentHidden(final ComponentEvent componentEvent) {
            if (DialogContentPane.this.getButtonValue() == Integer.MIN_VALUE) {
                DialogContentPane.this.setButtonValue(-1);
            }
            final ComponentListener access$1100 = DialogContentPane.this.m_currentlyActiveContentPaneComponentListener;
            if (access$1100 != null) {
                access$1100.componentHidden(new ComponentEvent(DialogContentPane.this.m_currentlyActiveContentPane, 103));
            }
        }
        
        public void componentShown(final ComponentEvent componentEvent) {
        }
    }
    
    private class ContentPaneComponentListener extends ComponentAdapter
    {
        public void componentHidden(final ComponentEvent componentEvent) {
            final Vector vector = (Vector)DialogContentPane.this.m_componentListeners.clone();
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).componentHidden(componentEvent);
            }
        }
        
        public void componentShown(final ComponentEvent componentEvent) {
            if (DialogContentPane.this.isVisible()) {
                if (DialogContentPane.this.m_lblText != null) {
                    DialogContentPane.this.m_titlePane.remove(DialogContentPane.this.m_lblText);
                    DialogContentPane.this.m_lblText = new JAPHtmlMultiLineLabel(DialogContentPane.this.m_lblText.getText(), DialogContentPane.this.m_lblText.getFont(), 0);
                    DialogContentPane.this.m_titlePane.add(DialogContentPane.this.m_lblText, DialogContentPane.this.m_textConstraints);
                    DialogContentPane.this.m_titlePane.revalidate();
                }
                final Vector vector = (Vector)DialogContentPane.this.m_componentListeners.clone();
                for (int i = 0; i < vector.size(); ++i) {
                    vector.elementAt(i).componentShown(componentEvent);
                }
            }
        }
        
        public void componentResized(final ComponentEvent componentEvent) {
            final Vector vector = (Vector)DialogContentPane.this.m_componentListeners.clone();
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).componentResized(componentEvent);
            }
        }
        
        public void componentMoved(final ComponentEvent componentEvent) {
            final Vector vector = (Vector)DialogContentPane.this.m_componentListeners.clone();
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).componentMoved(componentEvent);
            }
        }
    }
    
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent == null || actionEvent.getSource() == null) {
                return;
            }
            CheckError[] array;
            boolean b;
            if (actionEvent.getSource() == DialogContentPane.this.m_btnCancel) {
                array = DialogContentPane.this.checkCancel();
                if (DialogContentPane.this.isSomethingDoneOnClick(array, 32, 512, 4096, 32768) || (DialogContentPane.this.getJAPDialog() != null && DialogContentPane.this.getJAPDialog().isClosingOnContentPaneCancel())) {
                    DialogContentPane.this.setButtonValue(2);
                }
                if (DialogContentPane.this.getJAPDialog() == null || !DialogContentPane.this.getJAPDialog().isClosingOnContentPaneCancel()) {
                    b = DialogContentPane.this.doDefaultButtonOperation(array, 32, 512, 4096, 32768);
                }
                else {
                    b = true;
                    DialogContentPane.this.getJAPDialog().doWindowClosing();
                }
            }
            else if (actionEvent.getSource() == DialogContentPane.this.m_btnYesOK) {
                array = DialogContentPane.this.checkYesOK();
                if (DialogContentPane.this.isSomethingDoneOnClick(array, 8, 128, 1024, 8192)) {
                    if (0 == DialogContentPane.this.m_options.getOptionType() || 1 == DialogContentPane.this.m_options.getOptionType()) {
                        DialogContentPane.this.setButtonValue(0);
                    }
                    else {
                        DialogContentPane.this.setButtonValue(0);
                    }
                }
                b = DialogContentPane.this.doDefaultButtonOperation(array, 8, 128, 1024, 8192);
            }
            else {
                array = DialogContentPane.this.checkNo();
                if (DialogContentPane.this.isSomethingDoneOnClick(array, 16, 256, 2048, 16384)) {
                    DialogContentPane.this.setButtonValue(1);
                }
                b = DialogContentPane.this.doDefaultButtonOperation(array, 16, 256, 2048, 16384);
            }
            if (!b && (array == null || array.length == 0)) {
                DialogContentPane.this.doDefaultButtonOperation(array, 4, 64, 1, 2);
            }
        }
    }
    
    public interface IWizardSuitableNoWizardButtons extends IWizardSuitable
    {
    }
    
    public interface IWizardSuitable
    {
    }
}
