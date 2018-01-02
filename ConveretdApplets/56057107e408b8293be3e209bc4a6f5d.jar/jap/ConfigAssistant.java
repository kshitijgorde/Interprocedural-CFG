// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import platform.AbstractOS;
import gui.JAPHelpContext;
import gui.help.JAPHelp;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import java.text.MessageFormat;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.border.Border;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Color;
import javax.swing.text.JTextComponent;
import gui.JTextComponentToClipboardCopier;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import javax.swing.AbstractButton;
import logging.LogType;
import java.util.Locale;
import anon.util.LanguageMapper;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import gui.dialog.DialogContentPaneOptions;
import gui.dialog.SimpleWizardContentPane;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import gui.dialog.DialogContentPane;
import gui.GUIUtils;
import java.awt.Insets;
import anon.util.JAPMessages;
import java.awt.Component;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import gui.dialog.JAPDialog;

public class ConfigAssistant extends JAPDialog
{
    private static final String BROWSER_JONDOFOX = "JonDoFox";
    private static final String BROWSER_FIREFOX = "Mozilla Firefox";
    private static final String MSG_WELCOME;
    private static final String MSG_HELP;
    private static final String MSG_ANON_HP;
    private static final String MSG_TITLE;
    private static final String MSG_FINISHED;
    private static final String MSG_FINISHED_ANONTEST;
    private static final String MSG_FINISHED_TROUBLESHOOTING;
    private static final String MSG_BROWSER_CONF;
    private static final String MSG_RECOMMENDED;
    private static final String MSG_OTHER_BROWSERS;
    private static final String MSG_CLICK_TO_VIEW_HELP;
    private static final String MSG_BROWSER_TEST;
    private static final String MSG_MAKE_SELECTION;
    private static final String MSG_ERROR_NO_WARNING;
    private static final String MSG_EXPLAIN_NO_WARNING;
    private static final String MSG_EXPLAIN_NO_DIRECT_CONNECTION;
    private static final String MSG_EXPLAIN_FIREWALL;
    private static final String MSG_ERROR_WARNING_NO_SURFING;
    private static final String MSG_SUCCESS_WARNING;
    private static final String MSG_REALLY_CLOSE;
    private static final String MSG_DEACTIVATE_ACTIVE;
    private static final String MSG_ANON_TEST;
    private static final String MSG_ERROR_NO_SERVICE_AVAILABLE;
    private static final String MSG_ERROR_NO_CONNECTION;
    private static final String MSG_ERROR_CONNECTION_SLOW;
    private static final String MSG_ERROR_NO_SURFING;
    private static final String MSG_SUCCESS_CONNECTION;
    private static final String MSG_EXPLAIN_NO_CONNECTION;
    private static final String MSG_EXPLAIN_BAD_CONNECTION;
    private static final String MSG_EXPLAIN_CHOOSE_OTHER_SERVICE;
    private static final String MSG_EXPLAIN_NO_SERVICE_AVAILABLE;
    private static final String MSG_ERROR_WARNING_IN_BROWSER;
    private static final String MSG_EXPLAIN_WARNING_IN_BROWSER;
    private static final String MSG_SELECT_VIEW;
    private static final String MSG_SET_NEW_VIEW;
    private static final String MSG_SET_NEW_LANGUAGE;
    private static final String MSG_EXPLAIN_RESTART;
    private static final String[] PROXIES;
    private static final String IMG_ARROW = "arrow46.gif";
    private static final String IMG_HELP_BUTTON;
    private static final String IMG_SERVICES;
    private JTextPane[] m_lblHostnames;
    private JTextPane[] m_lblPorts;
    private JRadioButton m_radioNoWarning;
    private JRadioButton m_radioSuccessWarning;
    private JRadioButton m_radioErrorWarningNoSurfing;
    private JRadioButton m_radioWarningInBrowser;
    private ButtonGroup m_groupWarning;
    private JRadioButton m_radioNoConnection;
    private JRadioButton m_radioConnectionSlow;
    private JRadioButton m_noSurfing;
    private JRadioButton m_ConnectionOK;
    private JRadioButton m_radioNoServiceAvailable;
    private ButtonGroup m_groupAnon;
    private JRadioButton m_radioSimpleView;
    private JRadioButton m_radioAdvancedView;
    private ButtonGroup m_groupView;
    private boolean m_bFinished;
    static /* synthetic */ Class class$jap$ConfigAssistant;
    
    public ConfigAssistant(final Component component) {
        super(component, JAPMessages.getString(ConfigAssistant.MSG_TITLE), false);
        this.m_lblHostnames = new JTextPane[ConfigAssistant.PROXIES.length];
        this.m_lblPorts = new JTextPane[ConfigAssistant.PROXIES.length];
        this.m_bFinished = false;
        this.init();
    }
    
    public ConfigAssistant(final JAPDialog japDialog) {
        super(japDialog, JAPMessages.getString(ConfigAssistant.MSG_TITLE), false);
        this.m_lblHostnames = new JTextPane[ConfigAssistant.PROXIES.length];
        this.m_lblPorts = new JTextPane[ConfigAssistant.PROXIES.length];
        this.m_bFinished = false;
        this.init();
    }
    
    private void init() {
        final Locale locale = JAPMessages.getLocale();
        final Insets insets = new Insets(0, 0, 0, 5);
        final DialogContentPane.Layout layout = new DialogContentPane.Layout(GUIUtils.loadImageIcon("install.gif"));
        final Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();
        final SimpleWizardContentPane simpleWizardContentPane = new SimpleWizardContentPane(this, JAPMessages.getString(ConfigAssistant.MSG_WELCOME), layout, null);
        final JComponent contentPane = simpleWizardContentPane.getContentPane();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        contentPane.setLayout(new GridBagLayout());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        final JLabel label = new JLabel("line");
        label.setForeground(contentPane.getBackground());
        contentPane.add(label, gridBagConstraints);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        contentPane.add(new JLabel(JAPMessages.getString("settingsLanguage")), gridBagConstraints);
        final JComboBox comboBox = new JComboBox<LanguageMapper>();
        final String[] supportedLanguages = JAPConstants.getSupportedLanguages();
        for (int i = 0; i < supportedLanguages.length; ++i) {
            comboBox.addItem(new LanguageMapper(supportedLanguages[i], new Locale(supportedLanguages[i], "")));
        }
        comboBox.setSelectedItem(new LanguageMapper(JAPMessages.getLocale().getLanguage()));
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridx;
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        contentPane.add(comboBox, gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridx;
        gridBagConstraints.weightx = 1.0;
        contentPane.add(new JLabel(), gridBagConstraints);
        final SimpleWizardContentPane simpleWizardContentPane2 = new SimpleWizardContentPane(JAPMessages.getString(ConfigAssistant.MSG_SELECT_VIEW), layout, new DialogContentPaneOptions(simpleWizardContentPane)) {
            private final /* synthetic */ JComboBox val$comboLang = comboBox;
            
            public CheckError[] checkUpdate() {
                JAPMessages.init(((LanguageMapper)this.val$comboLang.getSelectedItem()).getLocale(), "JAPMessages");
                this.getButtonCancel().setText(JAPMessages.getString(DialogContentPane.MSG_CANCEL));
                this.getButtonNo().setText(JAPMessages.getString(DialogContentPane.MSG_PREVIOUS));
                this.getButtonYesOK().setText(JAPMessages.getString(DialogContentPane.MSG_NEXT));
                this.setText(JAPMessages.getString(ConfigAssistant.MSG_SELECT_VIEW));
                ConfigAssistant.this.m_radioSimpleView.setText(JAPMessages.getString("ngSettingsViewSimplified"));
                ConfigAssistant.this.m_radioAdvancedView.setText(JAPMessages.getString("ngSettingsViewNormal"));
                return super.checkUpdate();
            }
            
            public CheckError[] checkYesOK() {
                final CheckError[] checkYesOK = super.checkYesOK();
                if (ConfigAssistant.this.m_groupView.getSelection() == null) {
                    return new CheckError[] { new CheckError(JAPMessages.getString(ConfigAssistant.MSG_MAKE_SELECTION), LogType.GUI) };
                }
                return checkYesOK;
            }
        };
        final JComponent contentPane2 = simpleWizardContentPane2.getContentPane();
        contentPane2.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.anchor = 17;
        this.m_radioSimpleView = new JRadioButton(JAPMessages.getString("ngSettingsViewSimplified"));
        this.m_radioAdvancedView = new JRadioButton(JAPMessages.getString("ngSettingsViewNormal"));
        if (!JAPController.getInstance().isConfigAssistantShown() || JAPModel.getDefaultView() != 2) {
            if (JAPModel.getDefaultView() == 1) {
                this.m_radioAdvancedView.setSelected(true);
            }
            else {
                this.m_radioSimpleView.setSelected(true);
            }
        }
        (this.m_groupView = new ButtonGroup()).add(this.m_radioSimpleView);
        this.m_groupView.add(this.m_radioAdvancedView);
        contentPane2.add(this.m_radioSimpleView, gridBagConstraints5);
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints5;
        ++gridBagConstraints6.gridy;
        contentPane2.add(this.m_radioAdvancedView, gridBagConstraints5);
        final SimpleWizardContentPane simpleWizardContentPane3 = new SimpleWizardContentPane((JAPDialog)this, new DialogContentPaneOptions(simpleWizardContentPane2)) {
            private final /* synthetic */ JComboBox val$comboLang = comboBox;
            
            public CheckError[] checkUpdate() {
                String s = "";
                JAPMessages.init(((LanguageMapper)this.val$comboLang.getSelectedItem()).getLocale(), "JAPMessages");
                this.getButtonCancel().setText(JAPMessages.getString(DialogContentPane.MSG_CANCEL));
                this.getButtonNo().setText(JAPMessages.getString(DialogContentPane.MSG_PREVIOUS));
                if (!((LanguageMapper)this.val$comboLang.getSelectedItem()).getLocale().equals(locale)) {
                    s = JAPMessages.getString(ConfigAssistant.MSG_SET_NEW_LANGUAGE);
                }
                if ((ConfigAssistant.this.m_radioSimpleView.isSelected() && JAPModel.getDefaultView() == 1) || (ConfigAssistant.this.m_radioAdvancedView.isSelected() && JAPModel.getDefaultView() == 2)) {
                    String s2;
                    if (ConfigAssistant.this.m_radioSimpleView.isSelected()) {
                        s2 = JAPMessages.getString("ngSettingsViewSimplified");
                    }
                    else {
                        s2 = JAPMessages.getString("ngSettingsViewNormal");
                    }
                    s = s + " " + JAPMessages.getString(ConfigAssistant.MSG_SET_NEW_VIEW, s2);
                }
                this.setText(s + "<br><br>" + JAPMessages.getString(ConfigAssistant.MSG_EXPLAIN_RESTART));
                return super.checkUpdate();
            }
            
            public boolean isSkippedAsNextContentPane() {
                return ((LanguageMapper)this.val$comboLang.getSelectedItem()).getLocale().equals(locale) && ((ConfigAssistant.this.m_radioSimpleView.isSelected() && JAPModel.getDefaultView() == 2) || (ConfigAssistant.this.m_radioAdvancedView.isSelected() && JAPModel.getDefaultView() == 1));
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return true;
            }
        };
        simpleWizardContentPane3.addComponentListener(new ComponentAdapter() {
            public void componentShown(final ComponentEvent componentEvent) {
                JAPMessages.init(locale, "JAPMessages");
            }
        });
        final SimpleWizardContentPane simpleWizardContentPane4 = new SimpleWizardContentPane(JAPMessages.getString(ConfigAssistant.MSG_BROWSER_CONF), layout, new DialogContentPaneOptions(simpleWizardContentPane3)) {
            private final /* synthetic */ DialogContentPane val$paneRestart = simpleWizardContentPane3;
            
            public CheckError[] checkUpdate() {
                for (int i = 0; i < ConfigAssistant.this.m_lblPorts.length; ++i) {
                    ConfigAssistant.this.m_lblPorts[i].setText("" + JAPModel.getHttpListenerPortNumber());
                }
                return super.checkUpdate();
            }
            
            public boolean isMoveForwardAllowed() {
                return this.val$paneRestart.isSkippedAsNextContentPane();
            }
        };
        final JComponent contentPane3 = simpleWizardContentPane4.getContentPane();
        contentPane3.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.gridx = 1;
        gridBagConstraints7.gridy = 0;
        gridBagConstraints7.anchor = 17;
        gridBagConstraints7.insets = insets;
        final JTextComponentToClipboardCopier textComponentToClipboardCopier = new JTextComponentToClipboardCopier(false);
        gridBagConstraints7.gridy = 0;
        gridBagConstraints7.fill = 2;
        for (int j = 0; j < ConfigAssistant.PROXIES.length; ++j) {
            final GridBagConstraints gridBagConstraints8 = gridBagConstraints7;
            ++gridBagConstraints8.gridy;
            this.addProxyInfo(contentPane3, gridBagConstraints7, ConfigAssistant.PROXIES[j]);
            final GridBagConstraints gridBagConstraints9 = gridBagConstraints7;
            ++gridBagConstraints9.gridy;
            gridBagConstraints7.gridx = 5;
            (this.m_lblHostnames[j] = GUIUtils.createSelectableAndResizeableLabel(contentPane3)).setText("localhost");
            textComponentToClipboardCopier.registerTextComponent(this.m_lblHostnames[j]);
            this.m_lblHostnames[j].setBackground(Color.white);
            contentPane3.add(this.m_lblHostnames[j], gridBagConstraints7);
            final GridBagConstraints gridBagConstraints10 = gridBagConstraints7;
            ++gridBagConstraints10.gridx;
            contentPane3.add(new JLabel(":"), gridBagConstraints7);
            final GridBagConstraints gridBagConstraints11 = gridBagConstraints7;
            ++gridBagConstraints11.gridx;
            (this.m_lblPorts[j] = GUIUtils.createSelectableAndResizeableLabel(contentPane3)).setText("65535");
            textComponentToClipboardCopier.registerTextComponent(this.m_lblPorts[j]);
            this.m_lblPorts[j].setBackground(Color.white);
            contentPane3.add(this.m_lblPorts[j], gridBagConstraints7);
            final GridBagConstraints gridBagConstraints12 = gridBagConstraints7;
            ++gridBagConstraints12.gridy;
        }
        gridBagConstraints7.gridy = 0;
        this.addBrowserInstallationInfo(contentPane3, gridBagConstraints7, "JonDoFox", "jondofox", true);
        this.addBrowserInstallationInfo(contentPane3, gridBagConstraints7, "Mozilla Firefox", "jondofox", false);
        this.addBrowserInstallationInfo(contentPane3, gridBagConstraints7, JAPMessages.getString(ConfigAssistant.MSG_OTHER_BROWSERS), "browser", false);
        final SimpleWizardContentPane simpleWizardContentPane5 = new SimpleWizardContentPane((JAPDialog)this, JAPMessages.getString(ConfigAssistant.MSG_BROWSER_TEST, JAPMessages.getString(LinkedCheckBox.MSG_REMEMBER_ANSWER)), layout, new DialogContentPaneOptions(simpleWizardContentPane4)) {
            public CheckError[] checkYesOK() {
                final CheckError[] checkYesOK = super.checkYesOK();
                if (ConfigAssistant.this.m_groupWarning.getSelection() == null) {
                    return new CheckError[] { new CheckError(JAPMessages.getString(ConfigAssistant.MSG_MAKE_SELECTION), LogType.GUI) };
                }
                return checkYesOK;
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return ConfigAssistant.this.m_groupWarning.getSelection() != null && ConfigAssistant.this.m_radioNoWarning.isSelected();
            }
        };
        final JComponent contentPane4 = simpleWizardContentPane5.getContentPane();
        contentPane4.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
        gridBagConstraints13.gridx = 0;
        gridBagConstraints13.gridy = 0;
        gridBagConstraints13.anchor = 17;
        contentPane4.add(this.m_radioNoWarning = new JRadioButton(JAPMessages.getString(ConfigAssistant.MSG_ERROR_NO_WARNING)), gridBagConstraints13);
        this.m_radioErrorWarningNoSurfing = new JRadioButton(JAPMessages.getString(ConfigAssistant.MSG_ERROR_WARNING_NO_SURFING));
        final GridBagConstraints gridBagConstraints14 = gridBagConstraints13;
        ++gridBagConstraints14.gridy;
        contentPane4.add(this.m_radioErrorWarningNoSurfing, gridBagConstraints13);
        this.m_radioWarningInBrowser = new JRadioButton(JAPMessages.getString(ConfigAssistant.MSG_ERROR_WARNING_IN_BROWSER));
        final GridBagConstraints gridBagConstraints15 = gridBagConstraints13;
        ++gridBagConstraints15.gridy;
        contentPane4.add(this.m_radioWarningInBrowser, gridBagConstraints13);
        (this.m_radioSuccessWarning = new JRadioButton(JAPMessages.getString(ConfigAssistant.MSG_SUCCESS_WARNING))).setForeground(new Color(0, 160, 0));
        final GridBagConstraints gridBagConstraints16 = gridBagConstraints13;
        ++gridBagConstraints16.gridy;
        contentPane4.add(this.m_radioSuccessWarning, gridBagConstraints13);
        (this.m_groupWarning = new ButtonGroup()).add(this.m_radioNoWarning);
        this.m_groupWarning.add(this.m_radioErrorWarningNoSurfing);
        this.m_groupWarning.add(this.m_radioWarningInBrowser);
        this.m_groupWarning.add(this.m_radioSuccessWarning);
        final SimpleWizardContentPane simpleWizardContentPane6 = new SimpleWizardContentPane((JAPDialog)this, JAPMessages.getString(ConfigAssistant.MSG_EXPLAIN_NO_WARNING), layout, new DialogContentPaneOptions(simpleWizardContentPane5)) {
            public boolean isSkippedAsNextContentPane() {
                return ConfigAssistant.this.m_groupWarning.getSelection() != null && !ConfigAssistant.this.m_radioNoWarning.isSelected();
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return true;
            }
        };
        simpleWizardContentPane6.setDefaultButtonOperation(33152);
        final SimpleWizardContentPane simpleWizardContentPane7 = new SimpleWizardContentPane((JAPDialog)this, JAPMessages.getString(ConfigAssistant.MSG_EXPLAIN_WARNING_IN_BROWSER, new Object[] { JAPMessages.getString("ngBttnAnonDetails"), JAPMessages.getString("settingsInfoServiceConfigAdvancedSettingsTabTitle"), JAPMessages.getString(JAPConfAnonGeneral.MSG_DENY_NON_ANONYMOUS_SURFING) }), layout, new DialogContentPaneOptions(simpleWizardContentPane6)) {
            public boolean isSkippedAsNextContentPane() {
                return ConfigAssistant.this.m_groupWarning.getSelection() != null && !ConfigAssistant.this.m_radioWarningInBrowser.isSelected();
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return true;
            }
        };
        simpleWizardContentPane7.setDefaultButtonOperation(33152);
        final SimpleWizardContentPane simpleWizardContentPane8 = new SimpleWizardContentPane((JAPDialog)this, JAPMessages.getString(ConfigAssistant.MSG_EXPLAIN_NO_DIRECT_CONNECTION) + " " + JAPMessages.getString(JAPConf.MSG_READ_PANEL_HELP, new Object[] { JAPMessages.getString("confButton"), JAPMessages.getString("ngTreeNetwork") }) + "<br><br>" + JAPMessages.getString(ConfigAssistant.MSG_EXPLAIN_FIREWALL), layout, new DialogContentPaneOptions(simpleWizardContentPane7)) {
            public boolean isSkippedAsNextContentPane() {
                return ConfigAssistant.this.m_groupWarning.getSelection() != null && !ConfigAssistant.this.m_radioErrorWarningNoSurfing.isSelected();
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return true;
            }
        };
        simpleWizardContentPane8.setDefaultButtonOperation(33152);
        final SimpleWizardContentPane simpleWizardContentPane9 = new SimpleWizardContentPane((JAPDialog)this, JAPMessages.getString(ConfigAssistant.MSG_ANON_TEST), layout, new DialogContentPaneOptions(simpleWizardContentPane8)) {
            public CheckError[] checkYesOK() {
                final CheckError[] checkYesOK = super.checkYesOK();
                if (ConfigAssistant.this.m_groupAnon.getSelection() == null) {
                    return new CheckError[] { new CheckError(JAPMessages.getString(ConfigAssistant.MSG_MAKE_SELECTION), LogType.GUI) };
                }
                return checkYesOK;
            }
        };
        final JComponent contentPane5 = simpleWizardContentPane9.getContentPane();
        contentPane5.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
        gridBagConstraints17.gridx = 0;
        gridBagConstraints17.gridy = 0;
        gridBagConstraints17.anchor = 17;
        contentPane5.add(this.m_radioNoServiceAvailable = new JRadioButton(JAPMessages.getString(ConfigAssistant.MSG_ERROR_NO_SERVICE_AVAILABLE)), gridBagConstraints17);
        this.m_radioNoConnection = new JRadioButton(JAPMessages.getString(ConfigAssistant.MSG_ERROR_NO_CONNECTION));
        final GridBagConstraints gridBagConstraints18 = gridBagConstraints17;
        ++gridBagConstraints18.gridy;
        contentPane5.add(this.m_radioNoConnection, gridBagConstraints17);
        this.m_noSurfing = new JRadioButton(JAPMessages.getString(ConfigAssistant.MSG_ERROR_NO_SURFING));
        final GridBagConstraints gridBagConstraints19 = gridBagConstraints17;
        ++gridBagConstraints19.gridy;
        contentPane5.add(this.m_noSurfing, gridBagConstraints17);
        this.m_radioConnectionSlow = new JRadioButton(JAPMessages.getString(ConfigAssistant.MSG_ERROR_CONNECTION_SLOW));
        final GridBagConstraints gridBagConstraints20 = gridBagConstraints17;
        ++gridBagConstraints20.gridy;
        contentPane5.add(this.m_radioConnectionSlow, gridBagConstraints17);
        (this.m_ConnectionOK = new JRadioButton(JAPMessages.getString(ConfigAssistant.MSG_SUCCESS_CONNECTION))).setForeground(new Color(0, 160, 0));
        final GridBagConstraints gridBagConstraints21 = gridBagConstraints17;
        ++gridBagConstraints21.gridy;
        contentPane5.add(this.m_ConnectionOK, gridBagConstraints17);
        (this.m_groupAnon = new ButtonGroup()).add(this.m_radioNoServiceAvailable);
        this.m_groupAnon.add(this.m_radioNoConnection);
        this.m_groupAnon.add(this.m_noSurfing);
        this.m_groupAnon.add(this.m_radioConnectionSlow);
        this.m_groupAnon.add(this.m_ConnectionOK);
        final SimpleWizardContentPane simpleWizardContentPane10 = new SimpleWizardContentPane((JAPDialog)this, JAPMessages.getString(ConfigAssistant.MSG_EXPLAIN_FIREWALL) + "<br><br>" + JAPMessages.getString(ConfigAssistant.MSG_EXPLAIN_NO_SERVICE_AVAILABLE, new Object[] { JAPMessages.getString("ngBttnAnonDetails"), JAPMessages.getString("ngAnonGeneralPanelTitle"), JAPMessages.getString(JAPConfAnonGeneral.MSG_CONNECTION_TIMEOUT), JAPMessages.getString("ngSettingsViewNormal"), JAPMessages.getString("confButton"), JAPMessages.getString("ngTreeNetwork") }), layout, new DialogContentPaneOptions(simpleWizardContentPane9)) {
            public boolean isSkippedAsNextContentPane() {
                return ConfigAssistant.this.m_groupAnon.getSelection() != null && !ConfigAssistant.this.m_radioNoServiceAvailable.isSelected();
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return true;
            }
        };
        simpleWizardContentPane10.setDefaultButtonOperation(33152);
        final SimpleWizardContentPane simpleWizardContentPane11 = new SimpleWizardContentPane((JAPDialog)this, JAPMessages.getString(ConfigAssistant.MSG_EXPLAIN_NO_CONNECTION) + " " + JAPMessages.getString(ConfigAssistant.MSG_EXPLAIN_CHOOSE_OTHER_SERVICE, new String[] { JAPMessages.getString(JAPNewView.MSG_SERVICE_NAME), JAPMessages.getString("ngAnonymitaet"), JAPMessages.getString("ngAnonOn") }), layout, new DialogContentPaneOptions(simpleWizardContentPane10)) {
            public CheckError[] checkUpdate() {
                ConfigAssistant.this.m_radioNoServiceAvailable.setVisible(true);
                return super.checkUpdate();
            }
            
            public boolean isSkippedAsNextContentPane() {
                return ConfigAssistant.this.m_groupAnon.getSelection() != null && !ConfigAssistant.this.m_radioNoConnection.isSelected();
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return true;
            }
        };
        simpleWizardContentPane11.setDefaultButtonOperation(33152);
        final JLabel label2 = new JLabel(this.loadServicesIcon());
        label2.setBorder(raisedBevelBorder);
        simpleWizardContentPane11.getContentPane().add(label2);
        final SimpleWizardContentPane simpleWizardContentPane12 = new SimpleWizardContentPane((JAPDialog)this, JAPMessages.getString(ConfigAssistant.MSG_EXPLAIN_BAD_CONNECTION) + " " + JAPMessages.getString(ConfigAssistant.MSG_EXPLAIN_CHOOSE_OTHER_SERVICE, new String[] { JAPMessages.getString(JAPNewView.MSG_SERVICE_NAME), JAPMessages.getString("ngAnonymitaet"), JAPMessages.getString("ngAnonOn") }), layout, new DialogContentPaneOptions(simpleWizardContentPane11)) {
            public boolean isSkippedAsNextContentPane() {
                return ConfigAssistant.this.m_groupAnon.getSelection() != null && !ConfigAssistant.this.m_noSurfing.isSelected() && !ConfigAssistant.this.m_radioConnectionSlow.isSelected();
            }
            
            public boolean isSkippedAsPreviousContentPane() {
                return true;
            }
        };
        simpleWizardContentPane12.setDefaultButtonOperation(33152);
        final JLabel label3 = new JLabel(this.loadServicesIcon());
        label3.setBorder(raisedBevelBorder);
        simpleWizardContentPane12.getContentPane().add(label3);
        final SimpleWizardContentPane simpleWizardContentPane13 = new SimpleWizardContentPane(this, JAPMessages.getString(ConfigAssistant.MSG_FINISHED), layout, new DialogContentPaneOptions(simpleWizardContentPane12));
        final JComponent contentPane6 = simpleWizardContentPane13.getContentPane();
        contentPane6.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
        gridBagConstraints22.gridx = 0;
        gridBagConstraints22.gridy = -1;
        this.addBrowserInstallationInfo(contentPane6, gridBagConstraints22, JAPMessages.getString(ConfigAssistant.MSG_FINISHED_ANONTEST), "security_test", false);
        this.addBrowserInstallationInfo(contentPane6, gridBagConstraints22, "JonDoFox", "jondofox", false);
        this.addBrowserInstallationInfo(contentPane6, gridBagConstraints22, JAPMessages.getString(ConfigAssistant.MSG_FINISHED_TROUBLESHOOTING), "trouble", false);
        simpleWizardContentPane13.getButtonCancel().setVisible(false);
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            private final /* synthetic */ DialogContentPane val$paneFinish = simpleWizardContentPane13;
            private final /* synthetic */ JAPDialog val$thisDialog = this;
            private final /* synthetic */ DialogContentPane val$paneRestart = simpleWizardContentPane3;
            private final /* synthetic */ JComboBox val$comboLang = comboBox;
            
            public void windowClosing(final WindowEvent windowEvent) {
                int n = 1;
                if (!this.val$paneFinish.isVisible()) {
                    n = ((JAPDialog.showConfirmDialog(this.val$thisDialog, JAPMessages.getString(ConfigAssistant.MSG_REALLY_CLOSE), 2, 3) == 0) ? 1 : 0);
                }
                else {
                    ConfigAssistant.this.m_bFinished = true;
                }
                if (n != 0) {
                    ConfigAssistant.this.dispose();
                }
            }
            
            public void windowClosed(final WindowEvent windowEvent) {
                if (this.val$paneRestart.getButtonValue() == 0) {
                    JAPMessages.setLocale(((LanguageMapper)this.val$comboLang.getSelectedItem()).getLocale());
                    if (ConfigAssistant.this.m_radioSimpleView.isSelected()) {
                        JAPModel.getInstance().setDefaultView(2);
                    }
                    else if (ConfigAssistant.this.m_radioAdvancedView.isSelected()) {
                        JAPModel.getInstance().setDefaultView(1);
                    }
                    JAPController.goodBye(false);
                }
                else {
                    JAPController.getInstance().setConfigAssistantShown();
                }
            }
        });
        this.doClosingOnContentPaneCancel(true);
        DialogContentPane.updateDialogOptimalSized(simpleWizardContentPane);
        this.setResizable(false);
        this.m_radioNoServiceAvailable.setVisible(false);
    }
    
    private ImageIcon loadServicesIcon() {
        ImageIcon imageIcon = GUIUtils.loadImageIcon(MessageFormat.format(ConfigAssistant.IMG_SERVICES, "_" + JAPMessages.getLocale().getLanguage()));
        if (imageIcon == null) {
            imageIcon = GUIUtils.loadImageIcon(MessageFormat.format(ConfigAssistant.IMG_SERVICES, ""));
        }
        return imageIcon;
    }
    
    private void addProxyInfo(final JComponent component, final GridBagConstraints gridBagConstraints, final String s) {
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 13;
        component.add(new JLabel(s + " Hostname"), gridBagConstraints);
        final JLabel label = new JLabel(":");
        ++gridBagConstraints.gridx;
        component.add(label, gridBagConstraints);
        final JLabel label2 = new JLabel(s + " Port");
        ++gridBagConstraints.gridx;
        component.add(label2, gridBagConstraints);
    }
    
    private void addBrowserInstallationInfo(final JComponent component, final GridBagConstraints gridBagConstraints, final String s, final String s2, final boolean b) {
        gridBagConstraints.gridx = 0;
        ++gridBagConstraints.gridy;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        component.add(new JLabel(GUIUtils.loadImageIcon("arrow46.gif")), gridBagConstraints);
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridx = 1;
        JLabel label;
        if (b) {
            label = new JLabel(s + " (" + JAPMessages.getString(ConfigAssistant.MSG_RECOMMENDED) + ")");
        }
        else {
            label = new JLabel(s);
        }
        this.registerLink(label, s2, true);
        component.add(label, gridBagConstraints);
        final JLabel label2 = new JLabel();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        component.add(label2, gridBagConstraints);
        gridBagConstraints.weightx = 0.0;
    }
    
    private void registerLink(final JLabel label, final String toolTipText, final boolean b) {
        label.setForeground(Color.blue);
        if (b) {
            label.setToolTipText(JAPMessages.getString(ConfigAssistant.MSG_CLICK_TO_VIEW_HELP));
        }
        else {
            label.setToolTipText(toolTipText);
        }
        label.setCursor(Cursor.getPredefinedCursor(12));
        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (b) {
                    JAPHelp.getInstance().setContext(JAPHelpContext.createHelpContext(toolTipText, ConfigAssistant.this.getContentPane()));
                    JAPHelp.getInstance().setVisible(true);
                }
                else {
                    try {
                        AbstractOS.getInstance().openURL(new URL(toolTipText));
                    }
                    catch (MalformedURLException ex) {}
                }
            }
        });
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
        MSG_WELCOME = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_welcome";
        MSG_HELP = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_help";
        MSG_ANON_HP = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_anonHP";
        MSG_TITLE = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_title";
        MSG_FINISHED = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_finished";
        MSG_FINISHED_ANONTEST = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_menuFinishAnontest";
        MSG_FINISHED_TROUBLESHOOTING = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_menuFinishTroubleshooting";
        MSG_BROWSER_CONF = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_browserConf";
        MSG_RECOMMENDED = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_recommended";
        MSG_OTHER_BROWSERS = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_otherBrowsers";
        MSG_CLICK_TO_VIEW_HELP = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_clickToViewHelp";
        MSG_BROWSER_TEST = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_browserTest";
        MSG_MAKE_SELECTION = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_makeSelection";
        MSG_ERROR_NO_WARNING = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_errorNoWarning";
        MSG_EXPLAIN_NO_WARNING = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_explainNoWarning";
        MSG_EXPLAIN_NO_DIRECT_CONNECTION = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_explainNoDirectConnection";
        MSG_EXPLAIN_FIREWALL = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_explainFirewall";
        MSG_ERROR_WARNING_NO_SURFING = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_errorWarningNoSurfing";
        MSG_SUCCESS_WARNING = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_successWarning";
        MSG_REALLY_CLOSE = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_reallyClose";
        MSG_DEACTIVATE_ACTIVE = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_deactivateActiveContent";
        MSG_ANON_TEST = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_anonTest";
        MSG_ERROR_NO_SERVICE_AVAILABLE = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_errorNoServiceAvailable";
        MSG_ERROR_NO_CONNECTION = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_errorNoConnection";
        MSG_ERROR_CONNECTION_SLOW = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_errorConnectionSlow";
        MSG_ERROR_NO_SURFING = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_errorNoSurfing";
        MSG_SUCCESS_CONNECTION = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_successConnection";
        MSG_EXPLAIN_NO_CONNECTION = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_explainNoConnection";
        MSG_EXPLAIN_BAD_CONNECTION = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_explainBadConnection";
        MSG_EXPLAIN_CHOOSE_OTHER_SERVICE = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_explainChooseOtherService";
        MSG_EXPLAIN_NO_SERVICE_AVAILABLE = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_explainNoServiceAvailable";
        MSG_ERROR_WARNING_IN_BROWSER = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_errorWarningInBrowser";
        MSG_EXPLAIN_WARNING_IN_BROWSER = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_explainWarningInBrowser";
        MSG_SELECT_VIEW = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_selectView";
        MSG_SET_NEW_VIEW = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_setNewView";
        MSG_SET_NEW_LANGUAGE = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_setNewLanguage";
        MSG_EXPLAIN_RESTART = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_explainRestart";
        PROXIES = new String[] { "HTTP(S)", "SSL/FTP" };
        IMG_HELP_BUTTON = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_en_help.gif";
        IMG_SERVICES = ((ConfigAssistant.class$jap$ConfigAssistant == null) ? (ConfigAssistant.class$jap$ConfigAssistant = class$("jap.ConfigAssistant")) : ConfigAssistant.class$jap$ConfigAssistant).getName() + "_services{0}.gif";
    }
}
