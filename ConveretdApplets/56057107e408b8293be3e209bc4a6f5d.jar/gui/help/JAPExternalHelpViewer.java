// 
// Decompiled by Procyon v0.5.30
// 

package gui.help;

import gui.dialog.SimpleWizardContentPane;
import java.util.Observable;
import gui.dialog.WorkerContentPane;
import gui.dialog.DialogContentPane;
import gui.dialog.FileChooserContentPane;
import gui.dialog.JAPDialog;
import anon.util.JAPMessages;
import java.net.URL;
import java.awt.Component;
import platform.AbstractOS;
import logging.LogHolder;
import logging.LogType;
import gui.JAPHelpContext;
import java.awt.Frame;

public final class JAPExternalHelpViewer extends JAPHelp
{
    public static final String MSG_HELP_INSTALL;
    public static final String MSG_HELP_INSTALL_PROGRESS = "helpInstallProgress";
    public static final String MSG_HELP_INSTALL_FAILED;
    private static final String MSG_HELP_PATH_CHOICE;
    private static final String MSG_HELP_INTERNAL;
    private static final String MSG_HELP_INSTALL_SUCCESS;
    private Object SYNC_INSTALL;
    private boolean m_bInstallationDialogShown;
    private JAPHelp m_alternativeHelp;
    private IHelpModel m_helpModel;
    private long m_timeLastSetVisible;
    static /* synthetic */ Class class$gui$help$JAPExternalHelpViewer;
    
    JAPExternalHelpViewer(final Frame frame, final IHelpModel helpModel) {
        this.SYNC_INSTALL = new Object();
        this.m_bInstallationDialogShown = false;
        this.m_alternativeHelp = null;
        this.m_timeLastSetVisible = 0L;
        this.m_helpModel = helpModel;
        this.m_alternativeHelp = new JAPInternalHelpViewer(frame).getHelp();
    }
    
    public void setVisible(final boolean visible) {
        if (System.currentTimeMillis() - this.m_timeLastSetVisible < 1000L) {
            this.m_alternativeHelp.setContext(JAPHelpContext.INDEX_CONTEXT);
            this.m_alternativeHelp.setVisible(visible);
            return;
        }
        this.m_timeLastSetVisible = System.currentTimeMillis();
        final JAPHelpContext.IHelpContext helpContext = this.getHelpContext();
        if (this.getHelpContext() == null) {
            LogHolder.log(3, LogType.GUI, "Cannot show help externally: No help context specified");
            this.m_alternativeHelp.setContext(JAPHelpContext.INDEX_CONTEXT);
            this.m_alternativeHelp.setVisible(visible);
            return;
        }
        final Component helpExtractionDisplayContext = helpContext.getHelpExtractionDisplayContext();
        if (!this.m_helpModel.isHelpPathDefined()) {
            if (helpExtractionDisplayContext == null) {
                LogHolder.log(3, LogType.GUI, "Cannot show help externally: No display context specified");
                this.m_alternativeHelp.setContext(this.getHelpContext());
                this.m_alternativeHelp.setVisible(visible);
                return;
            }
            if (this.m_bInstallationDialogShown) {
                LogHolder.log(4, LogType.GUI, "Help installation dialog is already being shown. Cannot display help files!");
                return;
            }
            synchronized (this.SYNC_INSTALL) {
                this.m_bInstallationDialogShown = true;
                boolean showInstallDialog = false;
                if (!this.m_helpModel.isHelpPathDefined() && (!this.m_helpModel.isHelpPathChangeable() || !(showInstallDialog = this.showInstallDialog(helpExtractionDisplayContext)))) {
                    this.m_bInstallationDialogShown = false;
                    LogHolder.log(3, LogType.GUI, "Cannot show help externally: Help installation failed (changeable: " + this.m_helpModel.isHelpPathChangeable() + " showDialog: " + showInstallDialog + ")");
                    this.m_alternativeHelp.setContext(this.getHelpContext());
                    this.m_alternativeHelp.setVisible(visible);
                    return;
                }
                this.m_bInstallationDialogShown = false;
            }
        }
        final URL helpURL = this.m_helpModel.getHelpURL(helpContext.getHelpContext() + ".html");
        boolean openURL = true;
        if (helpURL == null || !(openURL = AbstractOS.getInstance().openURL(helpURL))) {
            final URL helpURL2;
            if (helpExtractionDisplayContext != null && this.showInstallDialog(helpExtractionDisplayContext) && (helpURL2 = this.m_helpModel.getHelpURL(helpContext.getHelpContext() + ".html")) != null) {
                AbstractOS.getInstance().openURL(helpURL2);
            }
            else {
                openURL = false;
            }
        }
        if (!openURL) {
            LogHolder.log(3, LogType.GUI, "Error while trying to show context '" + helpContext.getHelpContext() + "' in external help!");
            this.m_alternativeHelp.setContext(this.getHelpContext());
            this.m_alternativeHelp.setVisible(visible);
        }
    }
    
    private boolean showInstallDialog(final Component component) {
        if (this.m_helpModel.getHelpPath() == null || !this.m_helpModel.isHelpPathChangeable()) {
            return false;
        }
        final JAPDialog japDialog = new JAPDialog(component, JAPMessages.getString(JAPExternalHelpViewer.MSG_HELP_INSTALL));
        final FileChooserContentPane fileChooserContentPane = new FileChooserContentPane(japDialog, JAPMessages.getString(JAPExternalHelpViewer.MSG_HELP_PATH_CHOICE), this.m_helpModel.getHelpPath(), 1) {
            public CheckError[] checkYesOK() {
                CheckError[] checkYesOK = super.checkYesOK();
                if (checkYesOK != null && checkYesOK.length > 0) {
                    return checkYesOK;
                }
                final String helpPathValidityCheck = JAPExternalHelpViewer.this.m_helpModel.helpPathValidityCheck(this.getFile());
                if (!helpPathValidityCheck.equals("HELP_IS_VALID") && !helpPathValidityCheck.equals("helpJonDoExists")) {
                    checkYesOK = new CheckError[] { new CheckError(JAPMessages.getString(helpPathValidityCheck), LogType.GUI) };
                }
                return checkYesOK;
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return true;
            }
        };
        final WorkerContentPane workerContentPane = new WorkerContentPane(JAPMessages.getString("helpInstallProgress"), (DialogContentPane)fileChooserContentPane, (Runnable)new Runnable() {
            private final /* synthetic */ FileChooserContentPane val$fileChooser = fileChooserContentPane;
            
            public void run() {
                JAPExternalHelpViewer.this.m_helpModel.setHelpPath(this.val$fileChooser.getFile());
            }
        }, this.m_helpModel.getHelpFileStorageObservable()) {
            private final /* synthetic */ FileChooserContentPane val$fileChooser = fileChooserContentPane;
            
            public boolean isSkippedAsNextContentPane() {
                return JAPExternalHelpViewer.this.m_helpModel.isHelpPathDefined() && this.val$fileChooser.getFile().getPath().equals(JAPExternalHelpViewer.this.m_helpModel.getHelpPath());
            }
        };
        new SimpleWizardContentPane(japDialog) {
            private final /* synthetic */ WorkerContentPane val$workerPane = workerContentPane;
            
            public CheckError[] checkUpdate() {
                if (this.val$workerPane.getProgressStatus() != 0) {
                    japDialog.setTitle(JAPMessages.getString(JAPDialog.MSG_TITLE_ERROR));
                    this.setText("<font color='red'>" + JAPMessages.getString(JAPExternalHelpViewer.MSG_HELP_INSTALL_FAILED) + " " + JAPMessages.getString(JAPExternalHelpViewer.MSG_HELP_INTERNAL) + "</font>");
                }
                return null;
            }
        }.getButtonCancel().setVisible(false);
        fileChooserContentPane.updateDialogOptimalSized();
        japDialog.setResizable(false);
        japDialog.setVisible(true);
        return workerContentPane.getProgressStatus() == 0;
    }
    
    protected JAPDialog getOwnDialog() {
        return null;
    }
    
    public void loadCurrentContext() {
        if (this.getHelpContext() != null) {
            if (this.getHelpContext().getHelpExtractionDisplayContext() != null) {
                this.setVisible(true);
            }
            else {
                LogHolder.log(3, LogType.GUI, "Cannot show help externally: No display context specified");
                this.m_alternativeHelp.setContext(this.getHelpContext());
                this.m_alternativeHelp.loadCurrentContext();
            }
        }
        else {
            LogHolder.log(3, LogType.GUI, "Cannot show help externally: No help context specified");
            this.m_alternativeHelp.setContext(JAPHelpContext.INDEX_CONTEXT);
            this.m_alternativeHelp.loadCurrentContext();
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
        MSG_HELP_INSTALL = ((JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer == null) ? (JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer = class$("gui.help.JAPExternalHelpViewer")) : JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer).getName() + "_helpInstall";
        MSG_HELP_INSTALL_FAILED = ((JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer == null) ? (JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer = class$("gui.help.JAPExternalHelpViewer")) : JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer).getName() + "_helpInstallFailed";
        MSG_HELP_PATH_CHOICE = ((JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer == null) ? (JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer = class$("gui.help.JAPExternalHelpViewer")) : JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer).getName() + "_helpPathChoice";
        MSG_HELP_INTERNAL = ((JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer == null) ? (JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer = class$("gui.help.JAPExternalHelpViewer")) : JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer).getName() + "_helpInstallOpenInternal";
        MSG_HELP_INSTALL_SUCCESS = ((JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer == null) ? (JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer = class$("gui.help.JAPExternalHelpViewer")) : JAPExternalHelpViewer.class$gui$help$JAPExternalHelpViewer).getName() + "_helpInstallSucceded";
    }
}
