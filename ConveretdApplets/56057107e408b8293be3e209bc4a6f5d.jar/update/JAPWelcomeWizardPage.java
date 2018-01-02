// 
// Decompiled by Procyon v0.5.30
// 

package update;

import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import gui.JAPMultilineLabel;
import java.net.MalformedURLException;
import logging.LogHolder;
import logging.LogType;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseListener;
import platform.AbstractOS;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Cursor;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import anon.util.JAPMessages;
import gui.GUIUtils;
import anon.util.ClassUtil;
import anon.infoservice.JAPVersionInfo;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import gui.wizard.BasicWizardPage;

public class JAPWelcomeWizardPage extends BasicWizardPage implements ActionListener
{
    private static final long serialVersionUID = 1L;
    public static final String MSG_CHANGELOG_URL;
    public static final String MSG_CHANGELOG_URL_BETA;
    public static final String MSG_CHANGELOG;
    private static final String MSG_CHANGELOG_TT;
    private JTextField m_tfJapPath;
    private JLabel m_labelClickNext;
    private JButton m_bttnChooseJapFile;
    private File m_fileAktJapJar;
    private JCheckBox m_cbIncrementalUpdate;
    private JarFileFilter jarFileFilter;
    private final String COMMAND_SEARCH = "SEARCH";
    private boolean m_bIncrementalUpdate;
    final JFileChooser m_fileChooser;
    static /* synthetic */ Class class$update$JAPWelcomeWizardPage;
    static /* synthetic */ Class class$anon$util$ClassUtil;
    
    public JAPWelcomeWizardPage(final JAPVersionInfo japVersionInfo) {
        this.m_tfJapPath = null;
        this.m_bttnChooseJapFile = null;
        this.jarFileFilter = new JarFileFilter();
        this.m_bIncrementalUpdate = false;
        this.m_fileChooser = new JFileChooser(ClassUtil.getClassDirectory((JAPWelcomeWizardPage.class$anon$util$ClassUtil == null) ? (JAPWelcomeWizardPage.class$anon$util$ClassUtil = class$("anon.util.ClassUtil")) : JAPWelcomeWizardPage.class$anon$util$ClassUtil).getParent());
        this.setIcon(GUIUtils.loadImageIcon("install.gif", false));
        this.setPageTitle(JAPMessages.getString("updateWelcomeWizardPageTitle", new Object[] { japVersionInfo.getJapVersion() + (japVersionInfo.getId().equals("/japRelease.jnlp") ? "" : "-dev") }));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        super.m_panelComponents.setLayout(layout);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        try {
            URL url;
            if (japVersionInfo.getId().equals("/japRelease.jnlp")) {
                url = new URL(JAPMessages.getString(JAPWelcomeWizardPage.MSG_CHANGELOG_URL));
            }
            else {
                url = new URL(JAPMessages.getString(JAPWelcomeWizardPage.MSG_CHANGELOG_URL_BETA));
            }
            final URL url2 = url;
            final JLabel label = new JLabel(JAPMessages.getString(JAPWelcomeWizardPage.MSG_CHANGELOG));
            label.setToolTipText(JAPMessages.getString(JAPWelcomeWizardPage.MSG_CHANGELOG_TT));
            label.setCursor(Cursor.getPredefinedCursor(12));
            label.setForeground(Color.blue);
            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(final MouseEvent mouseEvent) {
                    AbstractOS.getInstance().openURL(url2);
                }
            });
            gridBagConstraints.insets = new Insets(10, 0, 10, 0);
            super.m_panelComponents.add(label, gridBagConstraints);
        }
        catch (MalformedURLException ex) {
            LogHolder.log(2, LogType.GUI, ex);
        }
        gridBagConstraints.gridy = 1;
        super.m_panelComponents.add(new JAPMultilineLabel(JAPMessages.getString("updateIntroductionMessage")), gridBagConstraints);
        this.m_tfJapPath = new JTextField(20);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(10, 0, 0, 10);
        gridBagConstraints.fill = 2;
        layout.setConstraints(this.m_tfJapPath, gridBagConstraints);
        super.m_panelComponents.add(this.m_tfJapPath, gridBagConstraints);
        this.m_tfJapPath.setText(ClassUtil.getClassDirectory((JAPWelcomeWizardPage.class$anon$util$ClassUtil == null) ? (JAPWelcomeWizardPage.class$anon$util$ClassUtil = class$("anon.util.ClassUtil")) : JAPWelcomeWizardPage.class$anon$util$ClassUtil).getParent() + System.getProperty("file.separator", "/") + "JAP.jar");
        this.m_bttnChooseJapFile = new JButton(JAPMessages.getString("updateM_chooseFolder_bttn"));
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        gridBagConstraints.fill = 0;
        layout.setConstraints(this.m_bttnChooseJapFile, gridBagConstraints);
        super.m_panelComponents.add(this.m_bttnChooseJapFile);
        this.m_bttnChooseJapFile.addActionListener(this);
        this.m_bttnChooseJapFile.setActionCommand("SEARCH");
        (this.m_cbIncrementalUpdate = new JCheckBox(JAPMessages.getString("updateM_doIncrementalUpdate"))).setVisible(false);
        this.m_cbIncrementalUpdate.setToolTipText(JAPMessages.getString("updateM_doIncrementalUpdate"));
        this.m_cbIncrementalUpdate.setSelected(this.m_bIncrementalUpdate);
        gridBagConstraints.insets = new Insets(0, 10, 10, 0);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 2;
        layout.setConstraints(this.m_cbIncrementalUpdate, gridBagConstraints);
        super.m_panelComponents.add(this.m_cbIncrementalUpdate);
        this.m_labelClickNext = new JLabel(JAPMessages.getString("updateM_labelClickNext"));
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 2;
        layout.setConstraints(this.m_labelClickNext, gridBagConstraints);
        super.m_panelComponents.add(this.m_labelClickNext);
        final JLabel label2 = new JLabel("");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 3;
        layout.setConstraints(label2, gridBagConstraints);
        super.m_panelComponents.add(label2);
    }
    
    public boolean checkPage() {
        boolean b = false;
        if (!this.m_tfJapPath.getText().equals("")) {
            final File fileAktJapJar = new File(this.m_tfJapPath.getText());
            if (fileAktJapJar.isFile() && fileAktJapJar.exists()) {
                this.m_fileAktJapJar = fileAktJapJar;
                b = true;
            }
            else {
                this.showInformationDialog(JAPMessages.getString("updateM_SelectedJapJarDoesNotExist"));
                b = false;
            }
        }
        return b;
    }
    
    public File getJapJarFile() {
        return this.m_fileAktJapJar;
    }
    
    public boolean isIncrementalUpdate() {
        return this.m_cbIncrementalUpdate.isSelected();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("SEARCH")) {
            this.m_fileChooser.setDialogTitle(JAPMessages.getString("updateM_fileChooserTitle"));
            this.m_fileChooser.setApproveButtonText(JAPMessages.getString("updateM_fileChooserApprove_bttn"));
            this.m_fileChooser.setFileSelectionMode(0);
            this.m_fileChooser.addChoosableFileFilter(this.jarFileFilter);
            if (GUIUtils.showMonitoredFileChooser(this.m_fileChooser, this) == 0) {
                this.m_fileAktJapJar = this.m_fileChooser.getSelectedFile();
                if (!this.m_fileAktJapJar.isFile()) {
                    this.m_fileChooser.cancelSelection();
                    this.showInformationDialog(JAPMessages.getString("updateM_fileChooserDialogNotAFile"));
                    this.m_tfJapPath.setText("");
                }
                else if (!this.m_fileAktJapJar.exists()) {
                    if (this.m_tfJapPath.getText().equals("")) {
                        this.m_fileChooser.cancelSelection();
                        this.showInformationDialog(JAPMessages.getString("updateM_fileChooserDialogFileNotExists"));
                        this.m_tfJapPath.setText("");
                    }
                    else {
                        this.m_tfJapPath.getText();
                    }
                }
                else {
                    this.m_tfJapPath.setText(this.m_fileAktJapJar.getAbsolutePath());
                }
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
        MSG_CHANGELOG_URL = ((JAPWelcomeWizardPage.class$update$JAPWelcomeWizardPage == null) ? (JAPWelcomeWizardPage.class$update$JAPWelcomeWizardPage = class$("update.JAPWelcomeWizardPage")) : JAPWelcomeWizardPage.class$update$JAPWelcomeWizardPage).getName() + "_changelogURL";
        MSG_CHANGELOG_URL_BETA = ((JAPWelcomeWizardPage.class$update$JAPWelcomeWizardPage == null) ? (JAPWelcomeWizardPage.class$update$JAPWelcomeWizardPage = class$("update.JAPWelcomeWizardPage")) : JAPWelcomeWizardPage.class$update$JAPWelcomeWizardPage).getName() + "_changelogURLBeta";
        MSG_CHANGELOG = ((JAPWelcomeWizardPage.class$update$JAPWelcomeWizardPage == null) ? (JAPWelcomeWizardPage.class$update$JAPWelcomeWizardPage = class$("update.JAPWelcomeWizardPage")) : JAPWelcomeWizardPage.class$update$JAPWelcomeWizardPage).getName() + "_changelog";
        MSG_CHANGELOG_TT = ((JAPWelcomeWizardPage.class$update$JAPWelcomeWizardPage == null) ? (JAPWelcomeWizardPage.class$update$JAPWelcomeWizardPage = class$("update.JAPWelcomeWizardPage")) : JAPWelcomeWizardPage.class$update$JAPWelcomeWizardPage).getName() + "_changelogTT";
    }
}
