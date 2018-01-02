// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import gui.help.JAPHelp;
import gui.help.JAPExternalHelpViewer;
import platform.WindowsOS;
import gui.TitledGridBagPanel;
import anon.infoservice.JavaVersionDBEntry;
import gui.JAPDll;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.util.Dictionary;
import java.util.Hashtable;
import anon.util.LanguageMapper;
import java.util.Locale;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.LookAndFeel;
import gui.dialog.WorkerContentPane;
import logging.LogHolder;
import anon.util.IReturnRunnable;
import gui.dialog.SimpleWizardContentPane;
import javax.swing.filechooser.FileFilter;
import anon.util.ClassUtil;
import java.util.Vector;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import logging.LogType;
import gui.dialog.DialogContentPane;
import gui.dialog.DialogContentPaneOptions;
import gui.dialog.JAPDialog;
import anon.util.JAPMessages;
import java.awt.Component;
import gui.GUIUtils;
import platform.AbstractOS;
import javax.swing.JFileChooser;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

final class JAPConfUI extends AbstractJAPConfModule
{
    private static final String MSG_ON_CLOSING_JAP;
    private static final String MSG_WARNING_ON_CLOSING_JAP;
    private static final String MSG_FONT_SIZE;
    private static final String MSG_WARNING_IMPORT_LNF;
    private static final String MSG_INCOMPATIBLE_JAVA;
    private static final String MSG_REMOVE;
    private static final String MSG_IMPORT;
    private static final String MSG_COULD_NOT_REMOVE;
    private static final String MSG_TITLE_IMPORT;
    private static final String MSG_PROGRESS_IMPORTING;
    private static final String MSG_IMPORT_SUCCESSFUL;
    private static final String MSG_NO_LNF_FOUND;
    private static final String MSG_LOOK_AND_FEEL_CHANGED;
    private static final String MSG_RESTART_TO_UNLOAD;
    private static final String MSG_DIALOG_FORMAT;
    private static final String MSG_DIALOG_FORMAT_TEST;
    private static final String MSG_DIALOG_FORMAT_TEST_2;
    private static final String MSG_DIALOG_FORMAT_TEST_BTN;
    private static final String MSG_DIALOG_FORMAT_GOLDEN_RATIO;
    private static final String MSG_TEST_BROWSER_PATH;
    private static final String MSG_BROWSER_SHOULD_OPEN;
    private static final String MSG_BROWSER_DOES_NOT_OPEN;
    private static final String MSG_BROWSER_TEST_PATH;
    private static final String MSG_BROWSER_NEW_PATH;
    private static final String MSG_BROWSER_TEST_BUTTON;
    private static final String MSG_BROWSER_TEST_EXPLAIN;
    private static final String MSG_HELP_PATH;
    private static final String MSG_HELP_PATH_CHOOSE;
    private static final String MSG_BROWSER_PATH;
    private static final String MSG_BROWSER_PATH_CHOOSE;
    private static final String MSG_NO_NATIVE_LIBRARY;
    private static final String MSG_NO_NATIVE_WINDOWS_LIBRARY;
    private static final String MSG_WINDOW_POSITION;
    private static final String MSG_WINDOW_MAIN;
    private static final String MSG_WINDOW_CONFIG;
    private static final String MSG_WINDOW_ICON;
    private static final String MSG_WINDOW_HELP;
    private static final String MSG_WINDOW_SIZE;
    private static final String MSG_MINI_ON_TOP;
    private static final String MSG_MINI_ON_TOP_TT;
    private static final String MSG_CHOOSE_OTHER_DIR;
    private TitledBorder m_borderLookAndFeel;
    private TitledBorder m_borderView;
    private JComboBox m_comboLanguage;
    private JComboBox m_comboUI;
    private JComboBox m_comboDialogFormat;
    private JCheckBox m_cbSaveWindowLocationMain;
    private JCheckBox m_cbSaveWindowLocationIcon;
    private JCheckBox m_cbSaveWindowLocationConfig;
    private JCheckBox m_cbSaveWindowLocationHelp;
    private JCheckBox m_cbSaveWindowSizeConfig;
    private JCheckBox m_cbSaveWindowSizeHelp;
    private JCheckBox m_cbAfterStart;
    private JCheckBox m_cbShowSplash;
    private JCheckBox m_cbStartPortableFirefox;
    private JRadioButton m_rbViewSimplified;
    private JRadioButton m_rbViewNormal;
    private JRadioButton m_rbViewMini;
    private JRadioButton m_rbViewSystray;
    private JCheckBox m_cbWarnOnClose;
    private JCheckBox m_cbMiniOnTop;
    private JCheckBox m_cbIgnoreDLLUpdate;
    private JSlider m_slidFontSize;
    private JButton m_btnAddUI;
    private JButton m_btnDeleteUI;
    private File m_currentDirectory;
    private JTextField m_helpPathField;
    private JButton m_helpPathButton;
    private JTextField m_portableBrowserPathField;
    private JButton m_portableBrowserPathButton;
    private Observer m_modelObserver;
    private boolean m_bClickedBrowserPath;
    static /* synthetic */ Class class$jap$JAPConfUI;
    
    public JAPConfUI() {
        super(null);
        this.m_bClickedBrowserPath = false;
        this.m_modelObserver = new Observer() {
            public void update(final Observable observable, final Object o) {
                if (o == JAPModel.CHANGED_HELP_PATH) {
                    JAPConfUI.this.updateHelpPath();
                    if (JAPModel.getInstance().isHelpPathChangeable()) {
                        JAPConfUI.this.m_helpPathButton.setEnabled(true);
                    }
                    else {
                        JAPConfUI.this.m_helpPathButton.setEnabled(false);
                    }
                }
                else if (o == JAPModel.CHANGED_DLL_UPDATE) {
                    JAPConfUI.this.m_cbIgnoreDLLUpdate.setSelected(!JAPModel.getInstance().isDLLWarningActive());
                    JAPConfUI.this.m_cbIgnoreDLLUpdate.setEnabled(JAPModel.getInstance().getDllUpdatePath() != null);
                }
            }
        };
    }
    
    protected boolean initObservers() {
        if (super.initObservers()) {
            synchronized (super.LOCK_OBSERVABLE) {
                JAPModel.getInstance().addObserver(this.m_modelObserver);
                return true;
            }
        }
        return false;
    }
    
    public void chooseBrowserPath() {
        if (this.m_bClickedBrowserPath) {
            return;
        }
        this.m_bClickedBrowserPath = true;
        this.chooseBrowserPath(null);
        this.m_bClickedBrowserPath = false;
    }
    
    private void chooseBrowserPath(final String s) {
        File selectedFile = null;
        JFileChooser fileChooser;
        if (s != null && new File(s).exists()) {
            fileChooser = new JFileChooser(s);
        }
        else if (JAPModel.getInstance().getPortableBrowserpath() != null) {
            fileChooser = new JFileChooser(JAPModel.getInstance().getPortableBrowserpath());
        }
        else if (AbstractOS.getInstance().getDefaultBrowserPath() != null) {
            fileChooser = new JFileChooser(AbstractOS.getInstance().getDefaultBrowserPath());
        }
        else {
            fileChooser = new JFileChooser(System.getProperty("user.dir"));
        }
        fileChooser.setFileSelectionMode(0);
        if (GUIUtils.showMonitoredFileChooser(fileChooser, this.getRootPanel()) == 0) {
            selectedFile = fileChooser.getSelectedFile();
        }
        if (selectedFile != null) {
            final String relativePath = AbstractOS.toRelativePath(selectedFile.getPath());
            final JAPDialog japDialog = new JAPDialog(this.getRootPanel(), JAPMessages.getString(JAPConfUI.MSG_TEST_BROWSER_PATH));
            final DialogContentPane dialogContentPane = new DialogContentPane(JAPMessages.getString(JAPConfUI.MSG_BROWSER_TEST_EXPLAIN), new DialogContentPaneOptions(1)) {
                private boolean m_bValid = false;
                
                public CheckError[] checkNo() {
                    CheckError[] array = null;
                    if (AbstractOS.getInstance().openBrowser(AbstractOS.createBrowserCommand(relativePath))) {
                        this.printStatusMessage(JAPMessages.getString(JAPConfUI.MSG_BROWSER_SHOULD_OPEN));
                        this.m_bValid = true;
                    }
                    else {
                        array = new CheckError[] { new CheckError(JAPMessages.getString(JAPConfUI.MSG_BROWSER_DOES_NOT_OPEN), LogType.GUI) };
                    }
                    return array;
                }
                
                public CheckError[] checkYesOK() {
                    CheckError[] array = null;
                    if (!this.m_bValid) {
                        array = new CheckError[] { new CheckError(JAPMessages.getString(JAPConfUI.MSG_BROWSER_TEST_PATH), LogType.GUI) };
                    }
                    return array;
                }
            };
            dialogContentPane.getButtonNo().setText(JAPMessages.getString(JAPConfUI.MSG_BROWSER_TEST_BUTTON));
            dialogContentPane.getButtonCancel().setText(JAPMessages.getString(JAPConfUI.MSG_BROWSER_NEW_PATH));
            dialogContentPane.getButtonYesOK().setText(JAPMessages.getString(DialogContentPane.MSG_OK));
            dialogContentPane.setDefaultButtonOperation(40960);
            DialogContentPane.updateDialogOptimalSized(dialogContentPane);
            japDialog.setVisible(true);
            japDialog.dispose();
            if (dialogContentPane.getButtonValue() == 0) {
                this.m_portableBrowserPathField.setText(relativePath);
                this.m_portableBrowserPathField.repaint();
            }
            else if (dialogContentPane.getButtonValue() == 2) {
                this.chooseBrowserPath(selectedFile.getPath());
            }
        }
    }
    
    public void recreateRootPanel() {
        final JPanel rootPanel = this.getRootPanel();
        rootPanel.removeAll();
        final boolean b = JAPModel.getDefaultView() == 2;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        rootPanel.setLayout(layout);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 2;
        rootPanel.add(this.createLookAndFeelPanel(), gridBagConstraints);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 1;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        gridBagConstraints.gridx = 0;
        rootPanel.add(this.createViewPanel(), gridBagConstraints);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridx = 1;
        rootPanel.add(this.createAfterStartupPanel(), gridBagConstraints);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        gridBagConstraints.fill = 1;
        final JPanel windowSizePanel = this.createWindowSizePanel();
        if (!b) {
            rootPanel.add(windowSizePanel, gridBagConstraints);
        }
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridy;
        final JPanel afterShutdownPanel = this.createAfterShutdownPanel();
        if (!b) {
            rootPanel.add(afterShutdownPanel, gridBagConstraints);
        }
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        --gridBagConstraints5.gridy;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = 1;
        final JPanel windowPanel = this.createWindowPanel();
        if (!b) {
            rootPanel.add(windowPanel, gridBagConstraints);
        }
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
        gridBagConstraints6.gridy += 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 1;
        final JPanel helpPathPanel = this.createHelpPathPanel();
        if (JAPModel.getInstance().isHelpPathChangeable()) {
            rootPanel.add(helpPathPanel, gridBagConstraints);
        }
        final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
        ++gridBagConstraints7.gridy;
        final JPanel browserPathPanel = this.createBrowserPathPanel();
        if (JAPController.getInstance().isPortableMode()) {
            rootPanel.add(browserPathPanel, gridBagConstraints);
        }
        final GridBagConstraints gridBagConstraints8 = gridBagConstraints;
        ++gridBagConstraints8.gridy;
        gridBagConstraints.weighty = 1.0;
        rootPanel.add(new JPanel(), gridBagConstraints);
    }
    
    public void afterPack() {
        this.m_comboUI.setVisible(true);
    }
    
    public void beforePack() {
        this.m_comboUI.setVisible(false);
    }
    
    private JPanel createLookAndFeelPanel() {
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.m_borderLookAndFeel = new TitledBorder(JAPMessages.getString("settingsLookAndFeelBorder"));
        final JPanel panel = new JPanel(gridBagLayout);
        panel.setBorder(this.m_borderLookAndFeel);
        final JLabel label = new JLabel(JAPMessages.getString("settingsLookAndFeel"));
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 18;
        panel.add(label, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        panel.add(this.m_comboUI = new JComboBox(), gridBagConstraints);
        this.m_comboUI.setVisible(false);
        this.m_btnDeleteUI = new JButton(JAPMessages.getString(JAPConfUI.MSG_REMOVE));
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridx;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 0;
        panel.add(this.m_btnDeleteUI, gridBagConstraints);
        this.m_btnDeleteUI.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                try {
                    synchronized (JAPConfUI.this.m_comboUI) {
                        final UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
                        final Vector vector = new Vector<UIManager.LookAndFeelInfo>(installedLookAndFeels.length - 1);
                        final File classDirectory = ClassUtil.getClassDirectory(installedLookAndFeels[JAPConfUI.this.m_comboUI.getSelectedIndex()].getClassName());
                        for (int i = 0; i < installedLookAndFeels.length; ++i) {
                            final File classDirectory2 = ClassUtil.getClassDirectory(installedLookAndFeels[i].getClassName());
                            if (classDirectory2 == null || !classDirectory.equals(classDirectory2)) {
                                vector.addElement(installedLookAndFeels[i]);
                            }
                        }
                        final UIManager.LookAndFeelInfo[] installedLookAndFeels2 = new UIManager.LookAndFeelInfo[vector.size()];
                        for (int j = 0; j < installedLookAndFeels2.length; ++j) {
                            installedLookAndFeels2[j] = vector.elementAt(j);
                        }
                        UIManager.setInstalledLookAndFeels(installedLookAndFeels2);
                        JAPModel.getInstance().removeLookAndFeelFile(classDirectory);
                        JAPConfUI.this.updateUICombo();
                    }
                    JAPDialog.showMessageDialog(JAPConfUI.this.getRootPanel(), JAPMessages.getString(JAPConfUI.MSG_RESTART_TO_UNLOAD));
                }
                catch (Exception ex) {
                    JAPDialog.showErrorDialog(JAPConfUI.this.getRootPanel(), JAPMessages.getString(JAPConfUI.MSG_COULD_NOT_REMOVE), LogType.MISC, ex);
                }
            }
        });
        this.m_btnAddUI = new JButton(JAPMessages.getString(JAPConfUI.MSG_IMPORT));
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridx;
        panel.add(this.m_btnAddUI, gridBagConstraints);
        this.m_btnAddUI.addActionListener(new ActionListener() {
            private final /* synthetic */ JAPConfUI this$0;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                final JFileChooser fileChooser = new JFileChooser(JAPConfUI.this.m_currentDirectory);
                final JAPDialog japDialog = new JAPDialog(JAPConfUI.this.getRootPanel(), JAPMessages.getString(JAPConfUI.MSG_TITLE_IMPORT));
                final LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
                fileChooser.setFileFilter(new FileFilter() {
                    public boolean accept(final File file) {
                        return file.isDirectory() || file.getName().endsWith(".jar");
                    }
                    
                    public String getDescription() {
                        return "*.jar";
                    }
                });
                final SimpleWizardContentPane simpleWizardContentPane = new SimpleWizardContentPane(japDialog, (DialogContentPaneOptions)null) {
                    boolean m_bCanceled = false;
                    
                    public CheckError[] checkYesOK() {
                        this.m_bCanceled = false;
                        final CheckError[] checkYesOK = super.checkYesOK();
                        fileChooser.setFileSelectionMode(0);
                        if (GUIUtils.showMonitoredFileChooser(fileChooser, japDialog.getContentPane()) != 0) {
                            this.m_bCanceled = true;
                        }
                        return checkYesOK;
                    }
                    
                    public Object getValue() {
                        return new Boolean(this.m_bCanceled);
                    }
                };
                final IReturnRunnable returnRunnable = new IReturnRunnable() {
                    Object m_value;
                    private final /* synthetic */ JAPConfUI$4 this$1 = this$1;
                    
                    public Object getValue() {
                        return this.m_value;
                    }
                    
                    public void run() {
                        if (fileChooser.getSelectedFile() != null) {
                            this.this$1.this$0.m_currentDirectory = fileChooser.getCurrentDirectory();
                            try {
                                final Vector registerLookAndFeelClasses;
                                if ((registerLookAndFeelClasses = GUIUtils.registerLookAndFeelClasses(fileChooser.getSelectedFile())).size() > 0) {
                                    for (int i = 0; i < registerLookAndFeelClasses.size(); ++i) {
                                        LogHolder.log(5, LogType.GUI, "Added new L&F class file: " + registerLookAndFeelClasses.elementAt(i));
                                        JAPModel.getInstance().addLookAndFeelFile(registerLookAndFeelClasses.elementAt(i));
                                    }
                                    this.this$1.this$0.updateUICombo();
                                    this.m_value = JAPMessages.getString(JAPConfUI.MSG_IMPORT_SUCCESSFUL);
                                }
                                else {
                                    this.m_value = new Exception(JAPMessages.getString(JAPConfUI.MSG_NO_LNF_FOUND));
                                }
                            }
                            catch (IllegalAccessException ex) {
                                this.m_value = new Exception(JAPMessages.getString(JAPConfUI.MSG_INCOMPATIBLE_JAVA));
                            }
                            fileChooser.setSelectedFile(null);
                        }
                    }
                };
                final WorkerContentPane workerContentPane = new WorkerContentPane(JAPMessages.getString(JAPConfUI.MSG_PROGRESS_IMPORTING) + "...", (DialogContentPane)simpleWizardContentPane, (Runnable)returnRunnable) {
                    private final /* synthetic */ DialogContentPane val$pane = simpleWizardContentPane;
                    
                    public boolean isSkippedAsNextContentPane() {
                        return (boolean)this.val$pane.getValue();
                    }
                };
                final SimpleWizardContentPane simpleWizardContentPane2 = new SimpleWizardContentPane(japDialog, new DialogContentPaneOptions(workerContentPane)) {
                    private final /* synthetic */ IReturnRunnable val$doIt = returnRunnable;
                    private final /* synthetic */ DialogContentPane val$pane = simpleWizardContentPane;
                    
                    public CheckError[] checkUpdate() {
                        this.setText((String)this.val$doIt.getValue());
                        return null;
                    }
                    
                    public boolean isSkippedAsNextContentPane() {
                        return (boolean)this.val$pane.getValue() || this.val$doIt.getValue() instanceof Exception;
                    }
                    
                    public boolean isSkippedAsPreviousContentPane() {
                        return true;
                    }
                };
                simpleWizardContentPane2.getButtonCancel().setVisible(false);
                new SimpleWizardContentPane(japDialog, new DialogContentPaneOptions(simpleWizardContentPane2)) {
                    private final /* synthetic */ IReturnRunnable val$doIt = returnRunnable;
                    private final /* synthetic */ DialogContentPane val$pane = simpleWizardContentPane;
                    
                    public boolean isSkippedAsPreviousContentPane() {
                        return true;
                    }
                    
                    public CheckError[] checkUpdate() {
                        this.setText(((Exception)this.val$doIt.getValue()).getMessage());
                        return null;
                    }
                    
                    public boolean isSkippedAsNextContentPane() {
                        return (boolean)this.val$pane.getValue() || !(this.val$doIt.getValue() instanceof Exception);
                    }
                }.getButtonCancel().setVisible(false);
                final JLabel label = new JLabel("AAAAAAAAAAAAAAAAAAAAAAAA");
                workerContentPane.getContentPane().add(label);
                DialogContentPane.updateDialogOptimalSized(simpleWizardContentPane);
                label.setVisible(false);
                japDialog.setVisible(true);
                if (lookAndFeel != UIManager.getLookAndFeel()) {
                    JAPDialog.showMessageDialog(JAPConfUI.this.getRootPanel(), JAPMessages.getString(JAPConfUI.MSG_LOOK_AND_FEEL_CHANGED));
                }
            }
        });
        this.m_comboUI.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                synchronized (JAPConfUI.this.m_comboUI) {
                    if (JAPConfUI.this.m_comboUI.getSelectedIndex() >= 0) {
                        final String className = UIManager.getInstalledLookAndFeels()[JAPConfUI.this.m_comboUI.getSelectedIndex()].getClassName();
                        final String lookAndFeel = JAPModel.getInstance().getLookAndFeel();
                        final String name = UIManager.getLookAndFeel().getClass().getName();
                        final File classDirectory = ClassUtil.getClassDirectory(name);
                        final File classDirectory2 = ClassUtil.getClassDirectory(lookAndFeel);
                        final File classDirectory3 = ClassUtil.getClassDirectory(className);
                        if ((classDirectory3 != null && classDirectory2 != null && classDirectory2.equals(classDirectory3)) || (classDirectory3 != null && classDirectory != null && classDirectory.equals(classDirectory3)) || lookAndFeel.equals(className) || name.equals(className) || JAPModel.getInstance().isSystemLookAndFeel(className)) {
                            JAPConfUI.this.m_btnDeleteUI.setEnabled(false);
                        }
                        else {
                            JAPConfUI.this.m_btnDeleteUI.setEnabled(true);
                        }
                    }
                }
            }
        });
        final JLabel label2 = new JLabel(JAPMessages.getString("settingsLanguage"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        panel.add(label2, gridBagConstraints);
        this.m_comboLanguage = new JComboBox();
        final String[] supportedLanguages = JAPConstants.getSupportedLanguages();
        for (int i = 0; i < supportedLanguages.length; ++i) {
            this.m_comboLanguage.addItem(new LanguageMapper(supportedLanguages[i], new Locale(supportedLanguages[i], "")));
        }
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 1;
        panel.add(this.m_comboLanguage, gridBagConstraints);
        final JLabel label3 = new JLabel(JAPMessages.getString(JAPMessages.getString(JAPConfUI.MSG_DIALOG_FORMAT)));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        panel.add(label3, gridBagConstraints);
        label3.setVisible(JAPModel.getInstance().isDialogFormatShown());
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = 2;
        (this.m_comboDialogFormat = new JComboBox()).addItem(new DialogFormat(JAPMessages.getString(JAPConfUI.MSG_DIALOG_FORMAT_GOLDEN_RATIO), 0));
        this.m_comboDialogFormat.addItem(new DialogFormat("4:3", 1));
        this.m_comboDialogFormat.addItem(new DialogFormat("16:9", 2));
        panel.add(this.m_comboDialogFormat, gridBagConstraints);
        final JButton button = new JButton(JAPMessages.getString(JAPConfUI.MSG_DIALOG_FORMAT_TEST_BTN));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final int optimizedFormat = JAPDialog.getOptimizedFormat();
                JAPDialog.setOptimizedFormat(((DialogFormat)JAPConfUI.this.m_comboDialogFormat.getSelectedItem()).getFormat());
                JAPDialog.showMessageDialog(JAPConfUI.this.getRootPanel(), JAPMessages.getString(JAPConfUI.MSG_DIALOG_FORMAT_TEST));
                JAPDialog.setOptimizedFormat(optimizedFormat);
            }
        });
        gridBagConstraints.gridx = 2;
        gridBagConstraints.weightx = 0.0;
        panel.add(button, gridBagConstraints);
        this.m_comboDialogFormat.setVisible(JAPModel.getInstance().isDialogFormatShown());
        button.setVisible(JAPModel.getInstance().isDialogFormatShown());
        final JButton button2 = new JButton(JAPMessages.getString(JAPConfUI.MSG_DIALOG_FORMAT_TEST_BTN));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final int optimizedFormat = JAPDialog.getOptimizedFormat();
                JAPDialog.setOptimizedFormat(((DialogFormat)JAPConfUI.this.m_comboDialogFormat.getSelectedItem()).getFormat());
                JAPDialog.showMessageDialog(JAPConfUI.this.getRootPanel(), JAPMessages.getString(JAPConfUI.MSG_DIALOG_FORMAT_TEST_2));
                JAPDialog.setOptimizedFormat(optimizedFormat);
            }
        });
        gridBagConstraints.gridx = 3;
        gridBagConstraints.weightx = 0.0;
        panel.add(button2, gridBagConstraints);
        button2.setVisible(JAPModel.getInstance().isDialogFormatShown());
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridy;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        panel.add(new JLabel(JAPMessages.getString(JAPConfUI.MSG_FONT_SIZE)), gridBagConstraints);
        (this.m_slidFontSize = new JSlider(0, 0, 3, JAPModel.getInstance().getFontSize())).setPaintTicks(false);
        this.m_slidFontSize.setPaintLabels(true);
        this.m_slidFontSize.setMajorTickSpacing(1);
        this.m_slidFontSize.setMinorTickSpacing(1);
        this.m_slidFontSize.setSnapToTicks(true);
        this.m_slidFontSize.setPaintTrack(true);
        final Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>(4);
        for (int j = 0; j <= 3; ++j) {
            labelTable.put(new Integer(j), new JLabel("1" + j + "0%"));
        }
        this.m_slidFontSize.setLabelTable(labelTable);
        gridBagConstraints.gridwidth = 3;
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        ++gridBagConstraints5.gridx;
        panel.add(this.m_slidFontSize, gridBagConstraints);
        return panel;
    }
    
    private JPanel createWindowSizePanel() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder(JAPMessages.getString(JAPConfUI.MSG_WINDOW_SIZE)));
        this.m_cbSaveWindowSizeConfig = new JCheckBox(JAPMessages.getString(JAPConfUI.MSG_WINDOW_CONFIG));
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        panel.add(this.m_cbSaveWindowSizeConfig, gridBagConstraints);
        this.m_cbSaveWindowSizeHelp = new JCheckBox(JAPMessages.getString(JAPConfUI.MSG_WINDOW_HELP));
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        return panel;
    }
    
    private JPanel createWindowPanel() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder(JAPMessages.getString(JAPConfUI.MSG_WINDOW_POSITION)));
        this.m_cbSaveWindowLocationMain = new JCheckBox(JAPMessages.getString(JAPConfUI.MSG_WINDOW_MAIN));
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        panel.add(this.m_cbSaveWindowLocationMain, gridBagConstraints);
        this.m_cbSaveWindowLocationConfig = new JCheckBox(JAPMessages.getString(JAPConfUI.MSG_WINDOW_CONFIG));
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        panel.add(this.m_cbSaveWindowLocationConfig, gridBagConstraints);
        this.m_cbSaveWindowLocationIcon = new JCheckBox(JAPMessages.getString(JAPConfUI.MSG_WINDOW_ICON));
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        panel.add(this.m_cbSaveWindowLocationIcon, gridBagConstraints);
        this.m_cbSaveWindowLocationHelp = new JCheckBox(JAPMessages.getString(JAPConfUI.MSG_WINDOW_HELP));
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridy;
        return panel;
    }
    
    private JPanel createViewPanel() {
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.m_borderView = new TitledBorder(JAPMessages.getString("ngSettingsViewBorder"));
        final JPanel panel = new JPanel(gridBagLayout);
        panel.setBorder(this.m_borderView);
        this.m_rbViewNormal = new JRadioButton(JAPMessages.getString("ngSettingsViewNormal"));
        this.m_rbViewSimplified = new JRadioButton(JAPMessages.getString("ngSettingsViewSimplified"));
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.m_rbViewNormal);
        buttonGroup.add(this.m_rbViewSimplified);
        gridBagConstraints.insets = new Insets(0, 10, 10, 10);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 18;
        panel.add(this.m_rbViewNormal, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        panel.add(this.m_rbViewSimplified, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        this.m_cbMiniOnTop = new JCheckBox(JAPMessages.getString(JAPConfUI.MSG_MINI_ON_TOP));
        if (JAPDll.getDllVersion() == null && JavaVersionDBEntry.CURRENT_JAVA_VERSION.compareTo("1.5") < 0) {
            this.m_cbMiniOnTop.setEnabled(false);
            this.m_cbMiniOnTop.setToolTipText(JAPMessages.getString(JAPConfUI.MSG_MINI_ON_TOP_TT));
        }
        panel.add(this.m_cbMiniOnTop, gridBagConstraints);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        this.m_cbIgnoreDLLUpdate = new JCheckBox(JAPMessages.getString(JAPDll.MSG_IGNORE_UPDATE));
        if (JAPDll.getDllVersion() == null || JAPModel.getInstance().getDllUpdatePath() == null) {
            this.m_cbIgnoreDLLUpdate.setEnabled(false);
        }
        panel.add(this.m_cbIgnoreDLLUpdate, gridBagConstraints);
        return panel;
    }
    
    private JPanel createAfterShutdownPanel() {
        final TitledGridBagPanel titledGridBagPanel = new TitledGridBagPanel(JAPMessages.getString(JAPConfUI.MSG_ON_CLOSING_JAP), new Insets(0, 10, 0, 10));
        (this.m_cbWarnOnClose = new JCheckBox(JAPMessages.getString(JAPConfUI.MSG_WARNING_ON_CLOSING_JAP))).setEnabled(!JAPController.getInstance().isPortableMode());
        titledGridBagPanel.addRow(this.m_cbWarnOnClose, null);
        return titledGridBagPanel;
    }
    
    private JPanel createAfterStartupPanel() {
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JPanel panel = new JPanel(gridBagLayout);
        panel.setBorder(new TitledBorder(JAPMessages.getString("ngSettingsStartBorder")));
        (this.m_cbAfterStart = new JCheckBox(JAPMessages.getString("ngViewAfterStart"))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPConfUI.this.updateThirdPanel(JAPConfUI.this.m_cbAfterStart.isSelected());
            }
        });
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.weightx = 1.0;
        panel.add(this.m_cbAfterStart, gridBagConstraints);
        this.m_rbViewMini = new JRadioButton(JAPMessages.getString("ngViewMini"));
        this.m_rbViewSystray = new JRadioButton(JAPMessages.getString("ngViewSystray"));
        if (JAPDll.getDllVersion() == null) {
            if (AbstractOS.getInstance() instanceof WindowsOS) {
                this.m_rbViewSystray.setToolTipText(JAPMessages.getString(JAPConfUI.MSG_NO_NATIVE_WINDOWS_LIBRARY));
            }
            else {
                this.m_rbViewSystray.setToolTipText(JAPMessages.getString(JAPConfUI.MSG_NO_NATIVE_LIBRARY));
            }
        }
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.m_rbViewMini);
        buttonGroup.add(this.m_rbViewSystray);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 30, 0, 10);
        panel.add(this.m_rbViewMini, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        panel.add(this.m_rbViewSystray, gridBagConstraints);
        (this.m_cbShowSplash = new JCheckBox(JAPMessages.getString("ngViewShowSplash"))).setEnabled(!JAPModel.getInstance().getShowSplashDisabled());
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        panel.add(this.m_cbShowSplash, gridBagConstraints);
        (this.m_cbStartPortableFirefox = new JCheckBox(JAPMessages.getString("ngViewStartPortableFirefox"))).setEnabled(JAPController.getInstance().isPortableMode());
        gridBagConstraints.gridy = 4;
        panel.add(this.m_cbStartPortableFirefox, gridBagConstraints);
        return panel;
    }
    
    private JPanel createBrowserPathPanel() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder(JAPMessages.getString(JAPConfUI.MSG_BROWSER_PATH)));
        gridBagConstraints.weightx = 1.0;
        (this.m_portableBrowserPathField = new JTextField(10)).setEditable(false);
        this.m_portableBrowserPathButton = new JButton(JAPMessages.getString(JAPConfUI.MSG_BROWSER_PATH_CHOOSE));
        if (JAPModel.getInstance().getPortableBrowserpath() != null) {
            this.m_portableBrowserPathField.setText(JAPModel.getInstance().getPortableBrowserpath());
        }
        else if (AbstractOS.getInstance().getDefaultBrowserPath() != null) {
            this.m_portableBrowserPathField.setText(AbstractOS.getInstance().getDefaultBrowserPath());
        }
        else {
            this.m_portableBrowserPathField.setText("");
        }
        this.m_portableBrowserPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPConfUI.this.chooseBrowserPath();
            }
        });
        if (!JAPController.getInstance().isPortableMode()) {
            this.m_portableBrowserPathButton.setEnabled(false);
        }
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        panel.add(this.m_portableBrowserPathField, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridx;
        gridBagConstraints.weightx = 0.0;
        panel.add(this.m_portableBrowserPathButton, gridBagConstraints);
        return panel;
    }
    
    private JPanel createHelpPathPanel() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder(JAPMessages.getString(JAPConfUI.MSG_HELP_PATH)));
        gridBagConstraints.weightx = 1.0;
        (this.m_helpPathField = new JTextField(10)).setEditable(false);
        this.m_helpPathButton = new JButton(JAPMessages.getString(JAPConfUI.MSG_HELP_PATH_CHOOSE));
        if (JAPModel.getInstance().isHelpPathDefined()) {
            this.m_helpPathField.setText(JAPModel.getInstance().getHelpPath());
        }
        else {
            this.m_helpPathField.setText("");
        }
        this.m_helpPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final JAPModel instance = JAPModel.getInstance();
                File selectedFile = null;
                final JFileChooser fileChooser = new JFileChooser(instance.getHelpPath());
                fileChooser.setFileSelectionMode(1);
                if (GUIUtils.showMonitoredFileChooser(fileChooser, JAPConfUI.this.getRootPanel()) == 0) {
                    selectedFile = fileChooser.getSelectedFile();
                }
                if (selectedFile != null) {
                    final String helpPathValidityCheck = instance.helpPathValidityCheck(selectedFile);
                    if (helpPathValidityCheck.equals("HELP_IS_VALID") || helpPathValidityCheck.equals("helpJonDoExists")) {
                        JAPConfUI.this.m_helpPathField.setText(selectedFile.getPath());
                        JAPConfUI.this.m_helpPathField.repaint();
                    }
                    else {
                        JAPDialog.showErrorDialog(JAPConf.getInstance(), JAPMessages.getString(helpPathValidityCheck) + " " + JAPMessages.getString(JAPConfUI.MSG_CHOOSE_OTHER_DIR), LogType.MISC);
                    }
                }
            }
        });
        if (!JAPModel.getInstance().isHelpPathChangeable()) {
            this.m_helpPathButton.setEnabled(false);
        }
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        panel.add(this.m_helpPathField, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridx;
        gridBagConstraints.weightx = 0.0;
        panel.add(this.m_helpPathButton, gridBagConstraints);
        return panel;
    }
    
    private void submitHelpPathChange() {
        if (this.m_helpPathField == null) {
            return;
        }
        final JAPModel instance = JAPModel.getInstance();
        final String helpPathValidityCheck = JAPModel.getInstance().helpPathValidityCheck(this.m_helpPathField.getText());
        if ((helpPathValidityCheck.equals("HELP_IS_VALID") || helpPathValidityCheck.equals("helpJonDoExists")) && (instance.getHelpPath() == null || !instance.getHelpPath().equals(this.m_helpPathField.getText()))) {
            final JAPDialog japDialog = new JAPDialog(this.getRootPanel(), JAPMessages.getString(JAPExternalHelpViewer.MSG_HELP_INSTALL));
            final WorkerContentPane workerContentPane = new WorkerContentPane(japDialog, JAPMessages.getString("helpInstallProgress"), new Runnable() {
                public void run() {
                    instance.setHelpPath(new File(JAPConfUI.this.m_helpPathField.getText()));
                }
            }, instance.getHelpFileStorageObservable());
            workerContentPane.updateDialog();
            japDialog.setResizable(false);
            japDialog.setVisible(true);
            if (workerContentPane.getProgressStatus() != 0) {
                this.resetHelpPath();
                JAPDialog.showErrorDialog(JAPConf.getInstance(), JAPMessages.getString(JAPExternalHelpViewer.MSG_HELP_INSTALL_FAILED), LogType.MISC);
            }
        }
    }
    
    private void resetHelpPath() {
        if (this.m_helpPathField != null) {
            this.m_helpPathField.setText("");
        }
    }
    
    private void updateHelpPath() {
        if (this.m_helpPathField != null) {
            if (JAPModel.getInstance().isHelpPathDefined() && JAPModel.getInstance().isHelpPathChangeable()) {
                this.m_helpPathField.setText(JAPModel.getInstance().getHelpPath());
            }
            else {
                this.m_helpPathField.setText("");
            }
        }
    }
    
    public String getTabTitle() {
        return JAPMessages.getString("ngUIPanelTitle");
    }
    
    protected void onCancelPressed() {
        this.updateValues(false);
    }
    
    protected boolean onOkPressed() {
        final JAPModel instance = JAPModel.getInstance();
        int fontSize = instance.getFontSize();
        if (instance.setFontSize(this.m_slidFontSize.getValue())) {
            if (!instance.isConfigWindowSizeSaved()) {
                this.beforePack();
                JAPConf.getInstance().doPack();
                this.afterPack();
            }
        }
        else {
            fontSize = -1;
        }
        instance.setSaveMainWindowPosition(this.m_cbSaveWindowLocationMain.isSelected());
        instance.setSaveConfigWindowPosition(this.m_cbSaveWindowLocationConfig.isSelected());
        instance.setSaveIconifiedWindowPosition(this.m_cbSaveWindowLocationIcon.isSelected());
        instance.setSaveHelpWindowPosition(this.m_cbSaveWindowLocationHelp.isSelected());
        instance.setSaveHelpWindowSize(this.m_cbSaveWindowSizeHelp.isSelected());
        instance.setSaveConfigWindowSize(this.m_cbSaveWindowSizeConfig.isSelected());
        if (JAPHelp.getHelpDialog() != null) {
            JAPHelp.getHelpDialog().resetAutomaticLocation(this.m_cbSaveWindowLocationHelp.isSelected());
        }
        if (JAPModel.getInstance().isConfigWindowSizeSaved()) {
            JAPModel.getInstance().setConfigSize(JAPConf.getInstance().getSize());
        }
        JAPController.getInstance().setMinimizeOnStartup(this.m_rbViewMini.isSelected() && this.m_cbAfterStart.isSelected());
        JAPController.getInstance().setMoveToSystrayOnStartup(this.m_rbViewSystray.isSelected() && this.m_cbAfterStart.isSelected());
        JAPModel.getInstance().setShowSplashScreen(this.m_cbShowSplash.isSelected());
        JAPModel.getInstance().setStartPortableFirefox(this.m_cbStartPortableFirefox.isSelected());
        JAPModel.getInstance().setNeverRemindGoodbye(!this.m_cbWarnOnClose.isSelected());
        JAPModel.getInstance().setMiniViewOnTop(this.m_cbMiniOnTop.isSelected());
        JAPModel.getInstance().setDllWarning(!this.m_cbIgnoreDLLUpdate.isSelected());
        Locale locale;
        if (this.m_comboLanguage.getSelectedIndex() >= 0) {
            locale = ((LanguageMapper)this.m_comboLanguage.getSelectedItem()).getLocale();
        }
        else {
            locale = JAPMessages.getLocale();
        }
        if (!JAPMessages.getLocale().equals(locale)) {
            JAPConf.getInstance().addNeedRestart(new JAPConf.AbstractRestartNeedingConfigChange() {
                public String getName() {
                    return JAPMessages.getString("settingsLanguage");
                }
                
                public void doChange() {
                    JAPMessages.setLocale(locale);
                }
            });
        }
        int n = 1;
        if (this.m_rbViewSimplified.isSelected()) {
            n = 2;
        }
        if (JAPModel.getDefaultView() != n) {
            JAPConf.getInstance().addNeedRestart(new JAPConf.AbstractRestartNeedingConfigChange() {
                public String getName() {
                    return JAPMessages.getString("ngSettingsViewBorder");
                }
                
                public void doChange() {
                    JAPController.getInstance().setDefaultView(n);
                }
            });
        }
        if (this.m_portableBrowserPathField.getText() != null && this.m_portableBrowserPathField.getText().trim().length() > 0 && !AbstractOS.toAbsolutePath(this.m_portableBrowserPathField.getText()).equals(AbstractOS.toAbsolutePath(JAPModel.getInstance().getPortableBrowserpath()))) {
            JAPConf.getInstance().addNeedRestart(new JAPConf.AbstractRestartNeedingConfigChange() {
                public String getName() {
                    return JAPMessages.getString(JAPConfUI.MSG_BROWSER_PATH);
                }
                
                public void doChange() {
                    JAPModel.getInstance().setPortableBrowserpath(JAPConfUI.this.m_portableBrowserPathField.getText());
                }
            });
        }
        JAPDialog.setOptimizedFormat(((DialogFormat)this.m_comboDialogFormat.getSelectedItem()).getFormat());
        String s;
        if (this.m_comboUI.getSelectedIndex() >= 0) {
            s = UIManager.getInstalledLookAndFeels()[this.m_comboUI.getSelectedIndex()].getClassName();
        }
        else {
            s = UIManager.getLookAndFeel().getClass().getName();
        }
        if (UIManager.getLookAndFeel().getClass().getName().equals(s)) {
            s = null;
        }
        if (s != null || fontSize >= 0) {
            JAPConf.getInstance().addNeedRestart(new JAPConf.AbstractRestartNeedingConfigChange() {
                public String getName() {
                    return JAPMessages.getString("settingsLookAndFeel");
                }
                
                public void doChange() {
                    if (s != null) {
                        JAPModel.getInstance().setLookAndFeel(s);
                    }
                }
                
                public void doCancel() {
                    if (fontSize >= 0) {
                        JAPConfUI.this.m_slidFontSize.setValue(fontSize);
                        JAPModel.getInstance().setFontSize(fontSize);
                        JAPConfUI.this.beforePack();
                        JAPConf.getInstance().doPack();
                        JAPConfUI.this.afterPack();
                    }
                }
            });
        }
        this.submitHelpPathChange();
        return true;
    }
    
    private void setLanguageComboIndex(final Locale locale) {
        final LanguageMapper languageMapper = new LanguageMapper(locale.getLanguage());
        int i;
        for (i = 0; i < this.m_comboLanguage.getItemCount(); ++i) {
            if (this.m_comboLanguage.getItemAt(i).equals(languageMapper)) {
                this.m_comboLanguage.setSelectedIndex(i);
                break;
            }
        }
        if (i == this.m_comboLanguage.getItemCount()) {
            this.m_comboLanguage.setSelectedIndex(0);
        }
    }
    
    protected void onUpdateValues() {
        this.updateUICombo();
        if (JAPModel.getInstance().getPortableBrowserpath() != null) {
            this.m_portableBrowserPathField.setText(JAPModel.getInstance().getPortableBrowserpath());
        }
        else {
            this.m_portableBrowserPathField.setText(AbstractOS.getInstance().getDefaultBrowserPath());
        }
        this.m_slidFontSize.setValue(JAPModel.getInstance().getFontSize());
        this.setLanguageComboIndex(JAPMessages.getLocale());
        this.m_cbSaveWindowLocationMain.setSelected(JAPModel.isMainWindowLocationSaved());
        this.m_cbSaveWindowLocationConfig.setSelected(JAPModel.getInstance().isConfigWindowLocationSaved());
        this.m_cbSaveWindowLocationIcon.setSelected(JAPModel.getInstance().isIconifiedWindowLocationSaved());
        this.m_cbSaveWindowLocationHelp.setSelected(JAPModel.getInstance().isHelpWindowLocationSaved());
        this.m_cbSaveWindowSizeHelp.setSelected(JAPModel.getInstance().isHelpWindowSizeSaved());
        this.m_cbSaveWindowSizeConfig.setSelected(JAPModel.getInstance().isConfigWindowSizeSaved());
        this.m_rbViewNormal.setSelected(JAPModel.getDefaultView() == 1);
        this.m_rbViewSimplified.setSelected(JAPModel.getDefaultView() == 2);
        this.m_rbViewSystray.setSelected(JAPModel.getMoveToSystrayOnStartup());
        this.m_rbViewMini.setSelected(JAPModel.getMinimizeOnStartup());
        this.m_cbMiniOnTop.setSelected(JAPModel.getInstance().isMiniViewOnTop());
        this.m_cbIgnoreDLLUpdate.setSelected(!JAPModel.getInstance().isDLLWarningActive());
        this.m_cbWarnOnClose.setSelected(!JAPModel.getInstance().isNeverRemindGoodbye());
        this.m_cbShowSplash.setSelected(JAPModel.getInstance().getShowSplashScreen());
        this.m_cbStartPortableFirefox.setSelected(JAPModel.getInstance().getStartPortableFirefox());
        final boolean b = JAPModel.getMoveToSystrayOnStartup() || JAPModel.getMinimizeOnStartup();
        for (int i = 0; i < this.m_comboDialogFormat.getItemCount(); ++i) {
            if (((DialogFormat)this.m_comboDialogFormat.getItemAt(i)).getFormat() == JAPDialog.getOptimizedFormat()) {
                this.m_comboDialogFormat.setSelectedIndex(i);
                break;
            }
        }
        this.updateThirdPanel(b);
        this.updateHelpPath();
    }
    
    public void onResetToDefaultsPressed() {
        this.setLanguageComboIndex(JAPMessages.getSystemLocale());
        final UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < installedLookAndFeels.length; ++i) {
            if (installedLookAndFeels[i].getClassName().equals(UIManager.getCrossPlatformLookAndFeelClassName())) {
                this.m_comboUI.setSelectedIndex(i);
                break;
            }
        }
        this.m_portableBrowserPathField.setText(AbstractOS.getInstance().getDefaultBrowserPath());
        this.m_cbSaveWindowLocationConfig.setSelected(false);
        this.m_cbSaveWindowLocationIcon.setSelected(true);
        this.m_cbSaveWindowLocationMain.setSelected(true);
        this.m_cbSaveWindowLocationHelp.setSelected(false);
        this.m_cbSaveWindowSizeHelp.setSelected(false);
        this.m_cbSaveWindowSizeConfig.setSelected(false);
        this.m_rbViewNormal.setSelected(false);
        this.m_rbViewSimplified.setSelected(true);
        this.m_rbViewSystray.setSelected(false);
        this.m_rbViewMini.setSelected(false);
        this.m_cbMiniOnTop.setSelected(true);
        this.m_cbIgnoreDLLUpdate.setSelected(false);
        this.m_cbShowSplash.setSelected(true);
        this.m_cbStartPortableFirefox.setSelected(true);
        this.m_cbWarnOnClose.setSelected(true);
        this.updateThirdPanel(false);
        this.resetHelpPath();
    }
    
    private void updateThirdPanel(final boolean b) {
        this.m_cbAfterStart.setSelected(b);
        this.m_rbViewMini.setEnabled(b);
        this.m_rbViewSystray.setEnabled(b && JAPDll.getDllVersion() != null);
        if (b && !this.m_rbViewSystray.isSelected() && !this.m_rbViewMini.isSelected()) {
            this.m_rbViewMini.setSelected(true);
        }
    }
    
    public String getHelpContext() {
        return "appearance";
    }
    
    private void updateUICombo() {
        synchronized (this.m_comboUI) {
            final UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
            final Vector vector = new Vector<UIManager.LookAndFeelInfo>(installedLookAndFeels.length);
            final Vector vector2 = new Vector<String>(installedLookAndFeels.length);
            final String name = UIManager.getLookAndFeel().getClass().getName();
            for (int i = 0; i < installedLookAndFeels.length; ++i) {
                if (!vector2.contains(installedLookAndFeels[i].getClassName())) {
                    vector2.addElement(installedLookAndFeels[i].getClassName());
                    vector.addElement(installedLookAndFeels[i]);
                }
            }
            final UIManager.LookAndFeelInfo[] installedLookAndFeels2 = new UIManager.LookAndFeelInfo[vector.size()];
            for (int j = 0; j < installedLookAndFeels2.length; ++j) {
                installedLookAndFeels2[j] = vector.elementAt(j);
            }
            UIManager.setInstalledLookAndFeels(installedLookAndFeels2);
            this.m_comboUI.removeAllItems();
            for (int k = 0; k < installedLookAndFeels2.length; ++k) {
                this.m_comboUI.addItem(installedLookAndFeels2[k].getName());
            }
            int l;
            for (l = 0; l < installedLookAndFeels2.length; ++l) {
                if (installedLookAndFeels2[l].getClassName().equals(name)) {
                    this.m_comboUI.setSelectedIndex(l);
                    break;
                }
            }
            if (l >= installedLookAndFeels2.length) {
                this.m_comboUI.addItem("(unknown)");
                this.m_comboUI.setSelectedIndex(l);
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
        MSG_ON_CLOSING_JAP = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_onClosingJAP";
        MSG_WARNING_ON_CLOSING_JAP = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_warningOnClosingJAP";
        MSG_FONT_SIZE = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_fontSize";
        MSG_WARNING_IMPORT_LNF = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_warningImportLNF";
        MSG_INCOMPATIBLE_JAVA = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_incompatibleJava";
        MSG_REMOVE = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_remove";
        MSG_IMPORT = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_import";
        MSG_COULD_NOT_REMOVE = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_couldNotRemove";
        MSG_TITLE_IMPORT = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_titleImport";
        MSG_PROGRESS_IMPORTING = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_progressImport";
        MSG_IMPORT_SUCCESSFUL = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_importSuccessful";
        MSG_NO_LNF_FOUND = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_noLNFFound";
        MSG_LOOK_AND_FEEL_CHANGED = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_lnfChanged";
        MSG_RESTART_TO_UNLOAD = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_restartToUnload";
        MSG_DIALOG_FORMAT = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_lblDialogFormat";
        MSG_DIALOG_FORMAT_TEST = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_dialogFormatTest";
        MSG_DIALOG_FORMAT_TEST_2 = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_dialogFormatTest2";
        MSG_DIALOG_FORMAT_TEST_BTN = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_dialogFormatTestBtn";
        MSG_DIALOG_FORMAT_GOLDEN_RATIO = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_dialogFormatGoldenRatio";
        MSG_TEST_BROWSER_PATH = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_testBrowserPath";
        MSG_BROWSER_SHOULD_OPEN = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_browserShouldOpen";
        MSG_BROWSER_DOES_NOT_OPEN = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_browserDoesNotStart";
        MSG_BROWSER_TEST_PATH = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_browserTestPath";
        MSG_BROWSER_NEW_PATH = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_browserNewPath";
        MSG_BROWSER_TEST_BUTTON = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_browserTestBtn";
        MSG_BROWSER_TEST_EXPLAIN = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_browserTestExplain";
        MSG_HELP_PATH = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_helpPath";
        MSG_HELP_PATH_CHOOSE = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_helpPathChange";
        MSG_BROWSER_PATH = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_browserPath";
        MSG_BROWSER_PATH_CHOOSE = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_browserPathChange";
        MSG_NO_NATIVE_LIBRARY = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_noNativeLibrary";
        MSG_NO_NATIVE_WINDOWS_LIBRARY = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_noNativeWindowsLibrary";
        MSG_WINDOW_POSITION = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_windowPosition";
        MSG_WINDOW_MAIN = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_windowMain";
        MSG_WINDOW_CONFIG = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_windowConfig";
        MSG_WINDOW_ICON = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_windowIcon";
        MSG_WINDOW_HELP = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_windowHelp";
        MSG_WINDOW_SIZE = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_windowSize";
        MSG_MINI_ON_TOP = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_miniOnTop";
        MSG_MINI_ON_TOP_TT = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_miniOnTopTT";
        MSG_CHOOSE_OTHER_DIR = ((JAPConfUI.class$jap$JAPConfUI == null) ? (JAPConfUI.class$jap$JAPConfUI = class$("jap.JAPConfUI")) : JAPConfUI.class$jap$JAPConfUI).getName() + "_chooseOtherDir";
    }
    
    private class DialogFormat
    {
        String m_description;
        int m_format;
        
        public DialogFormat(final String description, final int format) {
            this.m_description = description;
            this.m_format = format;
        }
        
        public String toString() {
            return this.m_description;
        }
        
        public int getFormat() {
            return this.m_format;
        }
    }
}
