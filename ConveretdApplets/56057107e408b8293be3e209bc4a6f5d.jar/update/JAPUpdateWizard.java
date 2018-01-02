// 
// Decompiled by Procyon v0.5.30
// 

package update;

import anon.infoservice.IProxyInterfaceGetter;
import anon.infoservice.IMutableProxyInterface;
import HTTPClient.HTTPResponse;
import anon.infoservice.HTTPConnectionFactory;
import anon.infoservice.ListenerInterface;
import anon.util.ClassUtil;
import anon.util.RecursiveFileTool;
import java.io.IOException;
import gui.GUIUtils;
import jarify.JarVerifier;
import jap.JAPUtil;
import java.net.URL;
import platform.AbstractOS;
import java.io.OutputStream;
import java.io.InputStream;
import anon.util.Util;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import javax.swing.Icon;
import java.awt.Window;
import jap.AbstractJAPMainView;
import jap.JAPController;
import jap.JAPModel;
import logging.LogHolder;
import gui.wizard.WizardPage;
import gui.wizard.WizardHost;
import gui.wizard.Wizard;
import logging.LogType;
import anon.util.JAPMessages;
import anon.infoservice.JavaVersionDBEntry;
import java.awt.Component;
import gui.dialog.JAPDialog;
import anon.infoservice.JAPVersionInfo;
import java.io.File;
import gui.wizard.BasicWizardHost;
import gui.wizard.BasicWizard;

public final class JAPUpdateWizard extends BasicWizard implements Runnable
{
    public JAPWelcomeWizardPage welcomePage;
    public JAPDownloadWizardPage downloadPage;
    public JAPFinishWizardPage finishPage;
    private BasicWizardHost host;
    private String m_strTempDirectory;
    public static final String MSG_JAVA_TOO_OLD;
    private static final String MSG_ADMIN_RIGHTS_NEEDED;
    private static final String MSG_ENTER_ADMIN_PASSWORD;
    private String m_strAktJapJarFileName;
    private String m_strAktJapJarExtension;
    private String m_strAktJapJarPath;
    private static final String EXTENSION_BACKUP = ".backup";
    private static final String EXTENSION_NEW = ".new";
    private static final File CLASSFILE;
    private boolean updateAborted;
    private String m_strNewJapVersion;
    private JAPVersionInfo japVersionInfo;
    private File m_fileAktJapJar;
    private File m_fileJapJarCopy;
    private File m_fileNewJapJar;
    private File updJapJar;
    private byte[] m_arBufferNewJapJar;
    private Thread updateThread;
    private int m_Status;
    public static final int UPDATESTATUS_SUCCESS = 0;
    public static final int UPDATESTATUS_ABORTED = 1;
    public static final int UPDATESTATUS_ERROR = -1;
    static /* synthetic */ Class class$update$JAPUpdateWizard;
    static /* synthetic */ Class class$anon$util$ClassUtil;
    
    public JAPUpdateWizard(final JAPVersionInfo japVersionInfo, final JAPDialog japDialog) {
        this(japVersionInfo, (Object)japDialog);
    }
    
    public JAPUpdateWizard(final JAPVersionInfo japVersionInfo, final Component component) {
        this(japVersionInfo, (Object)component);
    }
    
    private JAPUpdateWizard(final JAPVersionInfo japVersionInfo, final Object o) {
        this.updateAborted = false;
        this.m_arBufferNewJapJar = null;
        this.setWizardTitle("JAP Update Wizard");
        if (o instanceof JAPDialog) {
            if (!japVersionInfo.isJavaVersionStillSupported()) {
                JAPDialog.showErrorDialog((JAPDialog)o, JAPMessages.getString(JAPUpdateWizard.MSG_JAVA_TOO_OLD, new Object[] { JavaVersionDBEntry.CURRENT_JAVA_VERSION, japVersionInfo.getSupportedJavaVersion() }), LogType.MISC, new JAPDialog.LinkedHelpContext("updateJava"));
                return;
            }
            this.host = new BasicWizardHost((JAPDialog)o, this);
        }
        else {
            if (!japVersionInfo.isJavaVersionStillSupported()) {
                JAPDialog.showErrorDialog((Component)o, JAPMessages.getString(JAPUpdateWizard.MSG_JAVA_TOO_OLD, new Object[] { JavaVersionDBEntry.CURRENT_JAVA_VERSION, japVersionInfo.getSupportedJavaVersion() }), LogType.MISC, new JAPDialog.LinkedHelpContext("updateJava"));
                return;
            }
            this.host = new BasicWizardHost((Component)o, this);
        }
        this.host.setHelpEnabled(false);
        this.setHost(this.host);
        this.m_Status = 1;
        this.japVersionInfo = japVersionInfo;
        this.m_strNewJapVersion = japVersionInfo.getJapVersion();
        this.welcomePage = new JAPWelcomeWizardPage(japVersionInfo);
        this.downloadPage = new JAPDownloadWizardPage();
        this.finishPage = new JAPFinishWizardPage();
        this.addWizardPage(0, this.welcomePage);
        this.addWizardPage(1, this.downloadPage);
        this.addWizardPage(2, this.finishPage);
        this.invokeWizard();
    }
    
    public int getStatus() {
        return this.m_Status;
    }
    
    private void startUpdateThread() {
        LogHolder.log(7, LogType.MISC, "Start update...");
        (this.updateThread = new Thread(this, "JAPUpdateWizard")).setDaemon(true);
        this.updateThread.start();
    }
    
    public void run() {
        this.m_Status = 0;
        if (this.renameJapJar() != 0) {
            this.downloadPage.showInformationDialog(JAPMessages.getString("updateInformationMsgStep1") + " " + JAPMessages.getString(JAPUpdateWizard.MSG_ADMIN_RIGHTS_NEEDED));
            this.resetChanges();
            return;
        }
        if (this.downloadUpdate() != 0) {
            if (!this.updateAborted) {
                if (JAPModel.getInstance().getUpdateAnonymousConnectionSetting() == 1 && !JAPController.getInstance().isAnonConnected()) {
                    if (JAPDialog.showConfirmDialog(this.downloadPage, JAPMessages.getString("updateInformationMsgStep2") + JAPMessages.getString("updateInformationMsgStep2_noDirectConn"), 0, 0) == 0) {
                        JAPModel.getInstance().setUpdateAnonymousConnectionSetting(0);
                    }
                }
                else if (JAPModel.getInstance().getUpdateAnonymousConnectionSetting() == 2 && JAPController.getInstance().isAnonConnected()) {
                    if (JAPDialog.showConfirmDialog(this.downloadPage, JAPMessages.getString("updateInformationMsgStep2") + JAPMessages.getString("updateInformationMsgStep2_noAnonConn"), 0, 0) == 0) {
                        JAPModel.getInstance().setUpdateAnonymousConnectionSetting(0);
                    }
                }
                else {
                    this.downloadPage.showInformationDialog(JAPMessages.getString("updateInformationMsgStep2"));
                }
            }
            this.resetChanges();
            return;
        }
        if (this.welcomePage.isIncrementalUpdate()) {
            if (this.applyJARDiffJAPJar() != 0) {
                this.downloadPage.showInformationDialog(JAPMessages.getString("updateInformationMsgStep3") + " " + JAPMessages.getString(JAPUpdateWizard.MSG_ADMIN_RIGHTS_NEEDED));
                this.resetChanges();
                return;
            }
        }
        else if (this.createNewJAPJar() != 0) {
            this.downloadPage.showInformationDialog(JAPMessages.getString("updateInformationMsgStep3"));
            this.resetChanges();
            return;
        }
        if (!this.checkSignature()) {
            this.downloadPage.showInformationDialog(JAPMessages.getString("updateInformationMsgStep4"));
            this.resetChanges();
            return;
        }
        if (this.overwriteJapJar() != 0) {
            this.downloadPage.showInformationDialog(JAPMessages.getString("updateInformationMsgStep5") + " " + JAPMessages.getString(JAPUpdateWizard.MSG_ADMIN_RIGHTS_NEEDED));
            this.host.doCancel();
            return;
        }
        try {
            if (!this.m_fileNewJapJar.delete()) {
                this.downloadPage.showInformationDialog(JAPMessages.getString("updateM_DeletingofJAP_new.jarfailed"));
                return;
            }
            this.host.setNextEnabled(true);
            this.host.setFinishEnabled(false);
            this.host.setCancelEnabled(false);
        }
        catch (Exception ex) {
            this.downloadPage.showInformationDialog(ex.toString());
        }
    }
    
    private void setJapJarFile(final File fileAktJapJar) {
        this.m_fileAktJapJar = fileAktJapJar;
        this.parsePathToJapJar();
        final String string = this.m_strAktJapJarPath + this.m_strAktJapJarFileName + "00.12.005" + ".backup" + this.m_strAktJapJarExtension;
        this.downloadPage.m_labelSaveFrom.setText(this.m_fileAktJapJar.getAbsolutePath());
        this.downloadPage.m_labelSaveTo.setText(string);
        this.downloadPage.m_labelStep3.setText(JAPMessages.getString("updateM_labelStep3Part1") + " " + this.m_strAktJapJarFileName + this.m_strNewJapVersion + ".new" + this.m_strAktJapJarExtension);
        this.finishPage.m_labelBackupOfJapJar.setText(string);
    }
    
    public WizardPage next() {
        if (!super.m_Pages.elementAt(super.m_PageIndex).checkPage()) {
            return null;
        }
        ++super.m_PageIndex;
        this.host.setBackEnabled(true);
        if (super.m_PageIndex == super.m_Pages.size() - 1) {
            this.host.setFinishEnabled(true);
            this.host.setNextEnabled(false);
            try {
                this.updateThread.join();
            }
            catch (InterruptedException ex) {
                LogHolder.log(3, LogType.THREAD, ex);
            }
        }
        if (super.m_PageIndex == 1) {
            this.host.setBackEnabled(false);
            this.host.setFinishEnabled(false);
            this.host.setNextEnabled(false);
            this.setJapJarFile(this.welcomePage.getJapJarFile());
            this.host.showWizardPage(super.m_PageIndex);
            this.startUpdateThread();
        }
        else {
            this.host.showWizardPage(super.m_PageIndex);
        }
        return null;
    }
    
    public WizardPage finish() {
        final Window owner = this.host.getDialogParent().getOwner();
        this.host.getDialogParent().dispose();
        if (this.m_fileAktJapJar != null && this.m_fileAktJapJar.equals(JAPUpdateWizard.CLASSFILE)) {
            if (!(owner instanceof AbstractJAPMainView)) {
                owner.setVisible(false);
            }
            JAPController.goodBye(false);
        }
        return null;
    }
    
    public WizardPage back() {
        if (super.m_PageIndex == super.m_Pages.size() - 1) {
            this.host.setBackEnabled(false);
        }
        super.back();
        return null;
    }
    
    public void wizardCompleted() {
        this.updateAborted = true;
    }
    
    private void parsePathToJapJar() {
        try {
            this.m_strAktJapJarFileName = this.m_fileAktJapJar.getName();
            this.m_strAktJapJarPath = this.m_fileAktJapJar.getCanonicalPath();
            this.m_strAktJapJarPath = this.m_strAktJapJarPath.substring(0, this.m_strAktJapJarPath.length() - this.m_strAktJapJarFileName.length());
            this.m_strAktJapJarExtension = this.m_fileAktJapJar.getName();
            final int lastIndex = this.m_strAktJapJarExtension.lastIndexOf(46);
            this.m_strAktJapJarExtension = this.m_strAktJapJarExtension.substring(lastIndex);
            this.m_strAktJapJarFileName = this.m_strAktJapJarFileName.substring(0, lastIndex);
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.MISC, ex);
        }
    }
    
    private int renameJapJar() {
        LogHolder.log(7, LogType.MISC, "Start to make a copy of old jar-File!");
        this.downloadPage.m_labelIconStep1.setIcon(this.downloadPage.arrow);
        try {
            try {
                this.m_fileJapJarCopy = new File(this.m_strAktJapJarPath + this.m_strAktJapJarFileName + "00.12.005" + ".backup" + this.m_strAktJapJarExtension);
                Util.copyStream(new FileInputStream(this.m_fileAktJapJar), new FileOutputStream(this.m_fileJapJarCopy));
            }
            catch (Throwable t) {
                this.m_strTempDirectory = AbstractOS.getInstance().getTempPath();
                if (this.m_strTempDirectory == null) {
                    throw t;
                }
                this.m_fileJapJarCopy = new File(this.m_strTempDirectory + this.m_strAktJapJarFileName + "00.12.005" + ".backup" + this.m_strAktJapJarExtension);
                Util.copyStream(new FileInputStream(this.m_fileAktJapJar), new FileOutputStream(this.m_fileJapJarCopy));
                this.finishPage.m_labelBackupOfJapJar.setText(this.m_fileJapJarCopy.getAbsolutePath());
                this.downloadPage.m_labelSaveTo.setText(this.m_fileJapJarCopy.getAbsolutePath());
            }
            this.downloadPage.progressBar.setValue(5);
            this.downloadPage.progressBar.repaint();
            this.downloadPage.m_labelIconStep1.setIcon(this.downloadPage.stepfinished);
            return 0;
        }
        catch (Throwable t2) {
            LogHolder.log(7, LogType.MISC, "Could not make a copy of old JAP.jar: " + t2.getMessage());
            return -1;
        }
    }
    
    private int downloadUpdate() {
        final URL codeBase = this.japVersionInfo.getCodeBase();
        URL url;
        try {
            if (this.welcomePage.isIncrementalUpdate()) {
                url = new URL(codeBase, this.japVersionInfo.getJAPJarFileName() + "?version-id=" + this.japVersionInfo.getJapVersion() + "&current-version-id=" + "00.12.005");
            }
            else {
                url = new URL(codeBase, this.japVersionInfo.getJAPJarFileName() + "?version-id=" + this.japVersionInfo.getJapVersion());
            }
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, ex);
            return -1;
        }
        this.downloadPage.m_labelIconStep2.setIcon(this.downloadPage.arrow);
        try {
            final JapDownloadManager japDownloadManager = new JapDownloadManager(url);
            synchronized (japDownloadManager) {
                japDownloadManager.startDownload();
                japDownloadManager.wait();
            }
            if (japDownloadManager.getDownloadResult() == -1) {
                return -1;
            }
            this.m_arBufferNewJapJar = japDownloadManager.getNewJar();
            this.downloadPage.m_labelIconStep2.setIcon(this.downloadPage.stepfinished);
            return 0;
        }
        catch (Exception ex2) {
            LogHolder.log(3, LogType.MISC, ex2);
            return -1;
        }
    }
    
    private int createNewJAPJar() {
        try {
            if (this.m_strTempDirectory == null) {
                this.m_fileNewJapJar = new File(this.m_strAktJapJarPath + this.m_strAktJapJarFileName + this.m_strNewJapVersion + ".new" + this.m_strAktJapJarExtension);
            }
            else {
                this.m_fileNewJapJar = new File(this.m_strTempDirectory + this.m_fileAktJapJar.getName());
                this.downloadPage.m_labelStep3.setText(JAPMessages.getString("updateM_labelStep3Part1") + " " + this.m_fileAktJapJar.getName());
            }
            final FileOutputStream fileOutputStream = new FileOutputStream(this.m_fileNewJapJar);
            if (this.m_arBufferNewJapJar == null) {
                fileOutputStream.close();
                return -1;
            }
            this.downloadPage.m_labelIconStep3.setIcon(this.downloadPage.arrow);
            fileOutputStream.write(this.m_arBufferNewJapJar);
            fileOutputStream.flush();
            fileOutputStream.close();
            this.downloadPage.progressBar.setValue(440);
            this.downloadPage.progressBar.repaint();
            this.downloadPage.m_labelIconStep3.setIcon(this.downloadPage.stepfinished);
            return 0;
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, ex);
            return -1;
        }
    }
    
    private synchronized int applyJARDiffJAPJar() {
        try {
            this.m_fileNewJapJar = new File(this.m_strAktJapJarPath + this.m_strAktJapJarFileName + this.m_strNewJapVersion + ".new" + this.m_strAktJapJarExtension);
            if (JAPUtil.applyJarDiff(this.m_fileAktJapJar, this.m_fileNewJapJar, this.m_arBufferNewJapJar) != 0) {
                return -1;
            }
            this.downloadPage.m_labelIconStep3.setIcon(this.downloadPage.arrow);
            this.downloadPage.progressBar.setValue(440);
            this.downloadPage.progressBar.repaint();
            this.downloadPage.m_labelIconStep3.setIcon(this.downloadPage.stepfinished);
            return 0;
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, ex);
            return -1;
        }
    }
    
    private boolean checkSignature() {
        return JarVerifier.verify(this.m_fileNewJapJar, JAPModel.getJAPCodeSigningCert());
    }
    
    private int overwriteJapJar() {
        boolean b = false;
        try {
            if (this.m_fileAktJapJar != null && this.m_fileAktJapJar.equals(JAPUpdateWizard.CLASSFILE)) {
                GUIUtils.setLoadImages(false);
            }
            this.downloadPage.m_labelIconStep5.setIcon(this.downloadPage.arrow);
            this.host.setCancelEnabled(false);
            try {
                Util.copyStream(new FileInputStream(this.m_fileNewJapJar), new FileOutputStream(this.m_fileAktJapJar));
            }
            catch (SecurityException ex) {
                LogHolder.log(4, LogType.MISC, ex);
                b = true;
            }
            catch (IOException ex2) {
                LogHolder.log(4, LogType.MISC, ex2);
                b = true;
            }
            if (b || !RecursiveFileTool.equals(this.m_fileNewJapJar, this.m_fileAktJapJar, true)) {
                if (this.m_strTempDirectory == null) {
                    this.m_strTempDirectory = AbstractOS.getInstance().getTempPath();
                    if (this.m_strTempDirectory == null) {
                        throw new Exception("Administrator copy failed!");
                    }
                    final File fileNewJapJar = new File(this.m_strTempDirectory + this.m_fileAktJapJar.getName());
                    Util.copyStream(new FileInputStream(this.m_fileNewJapJar), new FileOutputStream(fileNewJapJar));
                    this.m_fileNewJapJar = fileNewJapJar;
                }
                final AbstractOS.AbstractRetryCopyProcess abstractRetryCopyProcess = new AbstractOS.AbstractRetryCopyProcess(12) {
                    public boolean checkRetry() {
                        return JAPDialog.showYesNoDialog(JAPUpdateWizard.this.downloadPage, JAPMessages.getString(JAPUpdateWizard.MSG_ENTER_ADMIN_PASSWORD));
                    }
                    
                    public boolean incrementProgress() {
                        if (super.incrementProgress()) {
                            JAPUpdateWizard.this.downloadPage.progressBar.setValue(440 + (this.getCurrentStep() + 1) * 5);
                            JAPUpdateWizard.this.downloadPage.progressBar.repaint();
                            return true;
                        }
                        return false;
                    }
                    
                    public void reset() {
                        super.reset();
                        JAPUpdateWizard.this.downloadPage.progressBar.setValue(440);
                    }
                };
                this.host.lockDialog();
                if (!AbstractOS.getInstance().copyAsRoot(this.m_fileNewJapJar, new File(this.m_fileAktJapJar.getParent()), abstractRetryCopyProcess) || !RecursiveFileTool.equals(this.m_fileNewJapJar, this.m_fileAktJapJar, true)) {
                    throw new Exception("Administrator copy failed!");
                }
                this.host.unlockDialog();
            }
            this.downloadPage.progressBar.setValue(500);
            this.downloadPage.progressBar.repaint();
            this.downloadPage.m_labelIconStep5.setIcon(this.downloadPage.stepfinished);
            return 0;
        }
        catch (Exception ex3) {
            this.host.unlockDialog();
            LogHolder.log(3, LogType.MISC, ex3);
            GUIUtils.setLoadImages(true);
            return -1;
        }
    }
    
    private void resetChanges() {
        if (this.updateAborted) {
            this.m_Status = 1;
        }
        else {
            this.m_Status = -1;
        }
        if (this.m_fileJapJarCopy != null) {
            this.m_fileJapJarCopy.delete();
        }
        if (this.m_fileNewJapJar != null) {
            this.m_fileNewJapJar.delete();
        }
        if (this.updJapJar != null) {
            this.updJapJar.delete();
        }
        this.host.getDialogParent().dispose();
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
        MSG_JAVA_TOO_OLD = ((JAPUpdateWizard.class$update$JAPUpdateWizard == null) ? (JAPUpdateWizard.class$update$JAPUpdateWizard = class$("update.JAPUpdateWizard")) : JAPUpdateWizard.class$update$JAPUpdateWizard).getName() + "_javaTooOld";
        MSG_ADMIN_RIGHTS_NEEDED = ((JAPUpdateWizard.class$update$JAPUpdateWizard == null) ? (JAPUpdateWizard.class$update$JAPUpdateWizard = class$("update.JAPUpdateWizard")) : JAPUpdateWizard.class$update$JAPUpdateWizard).getName() + "_adminRightsNeeded";
        MSG_ENTER_ADMIN_PASSWORD = ((JAPUpdateWizard.class$update$JAPUpdateWizard == null) ? (JAPUpdateWizard.class$update$JAPUpdateWizard = class$("update.JAPUpdateWizard")) : JAPUpdateWizard.class$update$JAPUpdateWizard).getName() + "_enterAdminPassword";
        CLASSFILE = ClassUtil.getClassDirectory((JAPUpdateWizard.class$anon$util$ClassUtil == null) ? (JAPUpdateWizard.class$anon$util$ClassUtil = class$("anon.util.ClassUtil")) : JAPUpdateWizard.class$anon$util$ClassUtil);
    }
    
    final class JapDownloadManager implements Runnable
    {
        private ListenerInterface targetInterface;
        private String fileName;
        private int downloadResult;
        private byte[] newJarBuff;
        
        public JapDownloadManager(final URL url) throws Exception {
            this.downloadResult = -1;
            this.newJarBuff = null;
            final String host = url.getHost();
            int port = url.getPort();
            if (port == -1) {
                port = 80;
            }
            this.targetInterface = new ListenerInterface(host, port);
            this.fileName = url.getFile();
        }
        
        public void run() {
            try {
                HTTPResponse get = null;
                boolean b = false;
                final IMutableProxyInterface updateProxyInterface = JAPModel.getInstance().getUpdateProxyInterface();
                for (int n = 0; n < 2 && !Thread.currentThread().isInterrupted(); ++n) {
                    if (n == 1) {
                        b = true;
                    }
                    final IProxyInterfaceGetter proxyInterface = updateProxyInterface.getProxyInterface(b);
                    if (proxyInterface != null) {
                        try {
                            get = HTTPConnectionFactory.getInstance().createHTTPConnection(this.targetInterface, proxyInterface.getProxyInterface()).Get(this.fileName);
                        }
                        catch (Exception ex) {
                            LogHolder.log(4, LogType.NET, ex);
                            continue;
                        }
                        if (get.getStatusCode() == 200) {
                            break;
                        }
                    }
                }
                if (get == null || get.getStatusCode() != 200) {
                    synchronized (this) {
                        this.notifyAll();
                    }
                    return;
                }
                final int headerAsInt = get.getHeaderAsInt("Content-Length");
                final InputStream inputStream = get.getInputStream();
                final byte[] array = new byte[2048];
                this.newJarBuff = new byte[headerAsInt];
                int n2 = 0;
                for (int i = inputStream.read(array); i > 0; i = inputStream.read(array)) {
                    System.arraycopy(array, 0, this.newJarBuff, n2, i);
                    n2 += i;
                    JAPUpdateWizard.this.downloadPage.progressBar.setValue((int)(400L * n2 / headerAsInt) + 5);
                    JAPUpdateWizard.this.downloadPage.progressBar.repaint();
                    if (JAPUpdateWizard.this.updateAborted) {
                        inputStream.close();
                        synchronized (this) {
                            this.notifyAll();
                        }
                        return;
                    }
                }
                this.downloadResult = 0;
                synchronized (this) {
                    this.notifyAll();
                }
            }
            catch (Exception ex2) {
                synchronized (this) {
                    this.notifyAll();
                }
            }
        }
        
        public void startDownload() {
            new Thread(this).start();
        }
        
        public int getDownloadResult() {
            return this.downloadResult;
        }
        
        public byte[] getNewJar() {
            if (this.getDownloadResult() == 0) {
                return this.newJarBuff;
            }
            return null;
        }
    }
}
