// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import anon.infoservice.JavaVersionDBEntry;
import update.JAPUpdateWizard;
import anon.infoservice.AbstractDatabaseEntry;
import anon.infoservice.Database;
import anon.infoservice.InfoServiceHolder;
import java.util.Observable;
import java.util.Date;
import javax.swing.text.Highlighter;
import logging.LogHolder;
import logging.LogType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Insets;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.Icon;
import gui.GUIUtils;
import java.awt.Component;
import anon.util.JAPMessages;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import anon.infoservice.JAPVersionInfo;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.Observer;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

final class JAPConfUpdate extends AbstractJAPConfModule implements ActionListener, ItemListener, Runnable, Observer
{
    private static final String COMMAND_UPGRADE = "UPGRADE";
    private static final String COMMAND_CHECKFORUPGRADE = "CHECKFORUPGRADE";
    private static final String MSG_ALLOW_DIRECT_CONN;
    private static final String MSG_REMIND_OPTIONAL_UPDATE;
    private static final String MSG_REMIND_JAVA_UPDATE;
    private static final String MSG_INFO;
    private JTextArea m_taInfo;
    private JScrollPane m_taInfoScrollPane;
    private JLabel m_labelVersion;
    private JLabel m_labelDate;
    private JComboBox m_comboType;
    private JButton m_bttnUpgrade;
    private JButton m_bttnCheckForUpgrade;
    private JComboBox m_comboAnonymousConnection;
    private JCheckBox m_cbxRemindOptionalUpdate;
    private JCheckBox m_cbxRemindJavaUpdate;
    private Thread m_threadGetVersionInfo;
    private JAPVersionInfo m_devVersion;
    private JAPVersionInfo m_releaseVersion;
    private DateFormat m_DateFormat;
    static /* synthetic */ Class class$jap$JAPConfUpdate;
    static /* synthetic */ Class class$anon$infoservice$JAPVersionInfo;
    
    public JAPConfUpdate() {
        super(null);
    }
    
    protected boolean initObservers() {
        if (super.initObservers()) {
            synchronized (super.LOCK_OBSERVABLE) {
                JAPModel.getInstance().addObserver(this);
                return true;
            }
        }
        return false;
    }
    
    public void recreateRootPanel() {
        final JPanel rootPanel = this.getRootPanel();
        rootPanel.removeAll();
        final GridBagLayout layout = new GridBagLayout();
        rootPanel.setLayout(layout);
        final JPanel panel = new JPanel();
        final GridBagLayout layout2 = new GridBagLayout();
        panel.setLayout(layout2);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 3;
        gridBagConstraints.anchor = 17;
        (this.m_bttnUpgrade = new JButton(JAPMessages.getString("confUpgrade"))).addActionListener(this);
        this.m_bttnUpgrade.setActionCommand("UPGRADE");
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 1;
        layout2.setConstraints(this.m_bttnUpgrade, gridBagConstraints);
        this.m_bttnUpgrade.setEnabled(false);
        panel.add(this.m_bttnUpgrade);
        (this.m_bttnCheckForUpgrade = new JButton(JAPMessages.getString("confCheckForUpgrade"))).setIcon(GUIUtils.loadImageIcon("reload.gif", true, false));
        this.m_bttnCheckForUpgrade.setDisabledIcon(GUIUtils.loadImageIcon("reloaddisabled_anim.gif", true, false));
        this.m_bttnCheckForUpgrade.setPressedIcon(GUIUtils.loadImageIcon("reloadrollover.gif", true, false));
        this.m_bttnCheckForUpgrade.addActionListener(this);
        this.m_bttnCheckForUpgrade.setActionCommand("CHECKFORUPGRADE");
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        layout2.setConstraints(this.m_bttnCheckForUpgrade, gridBagConstraints);
        this.m_bttnCheckForUpgrade.setEnabled(true);
        panel.add(this.m_bttnCheckForUpgrade);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final TitledBorder border = new TitledBorder(" " + JAPMessages.getString("updateTitleBorderInstalled") + " ");
        final JPanel panel2 = new JPanel(gridBagLayout);
        panel2.setBorder(border);
        final JLabel label = new JLabel("Version: ");
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.anchor = 18;
        gridBagConstraints2.weighty = 0.33;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
        gridBagLayout.setConstraints(label, gridBagConstraints2);
        panel2.add(label);
        final JLabel label2 = new JLabel("00.12.005");
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.fill = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagLayout.setConstraints(label2, gridBagConstraints2);
        panel2.add(label2);
        final JLabel label3 = new JLabel(JAPMessages.getString("updateLabelDate") + " ");
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 0;
        gridBagLayout.setConstraints(label3, gridBagConstraints2);
        panel2.add(label3);
        String s = JAPConstants.strReleaseDate;
        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
            Date date;
            try {
                date = simpleDateFormat.parse(s + " GMT");
            }
            catch (ParseException ex2) {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").parse(s + " GMT");
            }
            this.m_DateFormat = DateFormat.getDateTimeInstance(2, 2);
            s = this.m_DateFormat.format(date);
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.MISC, ex);
        }
        final JLabel label4 = new JLabel(s);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 1;
        gridBagLayout.setConstraints(label4, gridBagConstraints2);
        panel2.add(label4);
        final JLabel label5 = new JLabel(JAPMessages.getString("updateType") + ": ");
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 0;
        gridBagLayout.setConstraints(label5, gridBagConstraints2);
        panel2.add(label5);
        final JLabel label6 = new JLabel(JAPMessages.getString("updateReleaseVersion"));
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 1;
        gridBagLayout.setConstraints(label6, gridBagConstraints2);
        panel2.add(label6);
        final GridBagLayout gridBagLayout2 = new GridBagLayout();
        final TitledBorder border2 = new TitledBorder(" " + JAPMessages.getString("updateTitleBorderLatest") + " ");
        final JPanel panel3 = new JPanel(gridBagLayout2);
        panel3.setBorder(border2);
        final JLabel label7 = new JLabel("Version: ");
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 0;
        gridBagLayout2.setConstraints(label7, gridBagConstraints2);
        panel3.add(label7);
        this.m_labelVersion = new JLabel(JAPMessages.getString("updateUnknown"));
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 1;
        gridBagLayout2.setConstraints(this.m_labelVersion, gridBagConstraints2);
        panel3.add(this.m_labelVersion);
        final JLabel label8 = new JLabel(JAPMessages.getString("updateLabelDate") + " ");
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 0;
        gridBagLayout2.setConstraints(label8, gridBagConstraints2);
        panel3.add(label8);
        this.m_labelDate = new JLabel(JAPMessages.getString("updateUnknown"));
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 1;
        gridBagLayout2.setConstraints(this.m_labelDate, gridBagConstraints2);
        panel3.add(this.m_labelDate);
        final JLabel label9 = new JLabel(JAPMessages.getString("updateType") + ": ");
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.fill = 0;
        gridBagLayout2.setConstraints(label9, gridBagConstraints2);
        panel3.add(label9);
        (this.m_comboType = new JComboBox()).addItem(JAPMessages.getString("updateReleaseVersion"));
        this.m_comboType.addItem(JAPMessages.getString("updateDevelopmentVersion"));
        this.m_comboType.setEnabled(false);
        this.m_comboType.addItemListener(this);
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 1;
        gridBagLayout2.setConstraints(this.m_comboType, gridBagConstraints2);
        panel3.add(this.m_comboType);
        final JPanel panel4 = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        (this.m_taInfo = new JTextArea()).setEditable(false);
        this.m_taInfo.setHighlighter(null);
        this.m_taInfoScrollPane = new JScrollPane(this.m_taInfo);
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.anchor = 18;
        panel4.add(new JLabel(JAPMessages.getString(JAPConfUpdate.MSG_INFO)), gridBagConstraints3);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints3;
        ++gridBagConstraints4.gridy;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.weighty = 1.0;
        gridBagConstraints3.fill = 1;
        gridBagConstraints3.insets = new Insets(10, 0, 0, 0);
        panel4.add(this.m_taInfoScrollPane, gridBagConstraints3);
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.weightx = 0.0;
        gridBagConstraints5.weighty = 0.0;
        gridBagConstraints5.anchor = 18;
        gridBagConstraints5.fill = 1;
        layout.setConstraints(panel2, gridBagConstraints5);
        rootPanel.add(panel2);
        gridBagConstraints5.gridx = 1;
        gridBagConstraints5.gridy = 0;
        rootPanel.add(panel3, gridBagConstraints5);
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 2;
        gridBagConstraints5.gridwidth = 2;
        final JPanel panel5 = new JPanel();
        panel5.add(new JLabel(JAPMessages.getString(JAPConfUpdate.MSG_ALLOW_DIRECT_CONN) + ":"));
        final String[] array = new String[JAPModel.getMsgConnectionAnonymous().length];
        System.arraycopy(JAPModel.getMsgConnectionAnonymous(), 0, array, 0, array.length);
        for (int i = 0; i < array.length; ++i) {
            array[i] = JAPMessages.getString(array[i]);
        }
        panel5.add(this.m_comboAnonymousConnection = new JComboBox(array));
        gridBagConstraints5.fill = 0;
        rootPanel.add(panel5, gridBagConstraints5);
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints5;
        ++gridBagConstraints6.gridy;
        rootPanel.add(this.m_cbxRemindOptionalUpdate = new JCheckBox(JAPMessages.getString(JAPConfUpdate.MSG_REMIND_OPTIONAL_UPDATE)), gridBagConstraints5);
        final GridBagConstraints gridBagConstraints7 = gridBagConstraints5;
        ++gridBagConstraints7.gridy;
        this.m_cbxRemindJavaUpdate = new JCheckBox(JAPMessages.getString(JAPConfUpdate.MSG_REMIND_JAVA_UPDATE));
        if (JAPController.getInstance().hasPortableJava()) {
            this.m_cbxRemindJavaUpdate.setEnabled(false);
        }
        rootPanel.add(this.m_cbxRemindJavaUpdate, gridBagConstraints5);
        final GridBagConstraints gridBagConstraints8 = gridBagConstraints5;
        ++gridBagConstraints8.gridy;
        gridBagConstraints5.anchor = 10;
        gridBagConstraints5.fill = 1;
        gridBagConstraints5.weightx = 1.0;
        gridBagConstraints5.weighty = 1.0;
        layout.setConstraints(panel4, gridBagConstraints5);
        rootPanel.add(panel4);
        final GridBagConstraints gridBagConstraints9 = gridBagConstraints5;
        ++gridBagConstraints9.gridy;
        gridBagConstraints5.weighty = 0.0;
        gridBagConstraints5.fill = 2;
        gridBagConstraints5.anchor = 15;
        layout.setConstraints(panel, gridBagConstraints5);
        rootPanel.add(panel);
        this.updateValues(false);
    }
    
    public void update(final Observable observable, final Object o) {
        if (o != null) {
            if (o.equals(JAPModel.CHANGED_ALLOW_UPDATE_DIRECT_CONNECTION)) {
                this.m_comboAnonymousConnection.setSelectedIndex(JAPModel.getInstance().getUpdateAnonymousConnectionSetting());
            }
            else if (o.equals(JAPModel.CHANGED_NOTIFY_JAP_UPDATES)) {
                this.m_cbxRemindOptionalUpdate.setSelected(JAPModel.getInstance().isReminderForOptionalUpdateActivated());
            }
            else if (o.equals(JAPModel.CHANGED_NOTIFY_JAVA_UPDATES)) {
                this.m_cbxRemindJavaUpdate.setSelected(JAPModel.getInstance().isReminderForJavaUpdateActivated());
            }
        }
    }
    
    protected boolean onOkPressed() {
        JAPModel.getInstance().setUpdateAnonymousConnectionSetting(this.m_comboAnonymousConnection.getSelectedIndex());
        JAPModel.getInstance().setReminderForOptionalUpdate(this.m_cbxRemindOptionalUpdate.isSelected());
        JAPModel.getInstance().setReminderForJavaUpdate(this.m_cbxRemindJavaUpdate.isSelected());
        return true;
    }
    
    public void onResetToDefaultsPressed() {
        this.m_comboAnonymousConnection.setSelectedIndex(0);
        this.m_cbxRemindOptionalUpdate.setSelected(true);
        this.m_cbxRemindJavaUpdate.setSelected(!JAPController.getInstance().isPortableMode());
    }
    
    protected void onUpdateValues() {
        this.m_comboAnonymousConnection.setSelectedIndex(JAPModel.getInstance().getUpdateAnonymousConnectionSetting());
        this.m_cbxRemindOptionalUpdate.setSelected(JAPModel.getInstance().isReminderForOptionalUpdateActivated());
        this.m_cbxRemindJavaUpdate.setSelected(JAPModel.getInstance().isReminderForJavaUpdateActivated());
    }
    
    public void run() {
        this.updateVersionInfo(true);
    }
    
    public void updateVersionInfo(final boolean b) {
        if (b) {
            this.m_taInfo.setText(JAPMessages.getString("updateFetchVersionInfo"));
            this.m_releaseVersion = InfoServiceHolder.getInstance().getJAPVersionInfo(1);
            this.m_devVersion = InfoServiceHolder.getInstance().getJAPVersionInfo(2);
        }
        else {
            final JAPVersionInfo devVersion = (JAPVersionInfo)Database.getInstance((JAPConfUpdate.class$anon$infoservice$JAPVersionInfo == null) ? (JAPConfUpdate.class$anon$infoservice$JAPVersionInfo = class$("anon.infoservice.JAPVersionInfo")) : JAPConfUpdate.class$anon$infoservice$JAPVersionInfo).getEntryById("/japDevelopment.jnlp");
            final JAPVersionInfo releaseVersion = (JAPVersionInfo)Database.getInstance((JAPConfUpdate.class$anon$infoservice$JAPVersionInfo == null) ? (JAPConfUpdate.class$anon$infoservice$JAPVersionInfo = class$("anon.infoservice.JAPVersionInfo")) : JAPConfUpdate.class$anon$infoservice$JAPVersionInfo).getEntryById("/japRelease.jnlp");
            if (devVersion == null || releaseVersion == null) {
                return;
            }
            this.m_releaseVersion = releaseVersion;
            this.m_devVersion = devVersion;
        }
        if (this.m_releaseVersion == null || this.m_devVersion == null) {
            this.m_taInfo.setText(JAPMessages.getString("updateFetchVersionInfoFailed"));
        }
        else {
            Database.getInstance((JAPConfUpdate.class$anon$infoservice$JAPVersionInfo == null) ? (JAPConfUpdate.class$anon$infoservice$JAPVersionInfo = class$("anon.infoservice.JAPVersionInfo")) : JAPConfUpdate.class$anon$infoservice$JAPVersionInfo).update(this.m_releaseVersion);
            Database.getInstance((JAPConfUpdate.class$anon$infoservice$JAPVersionInfo == null) ? (JAPConfUpdate.class$anon$infoservice$JAPVersionInfo = class$("anon.infoservice.JAPVersionInfo")) : JAPConfUpdate.class$anon$infoservice$JAPVersionInfo).update(this.m_devVersion);
            this.m_comboType.setEnabled(true);
            final JAPVersionInfo releaseVersion2 = this.m_releaseVersion;
            String text;
            if ("00.12.005".compareTo(releaseVersion2.getJapVersion()) >= 0) {
                text = JAPMessages.getString("japUpdate_YouHaveAlreadyTheNewestVersion");
            }
            else {
                text = JAPMessages.getString("japUpdate_NewVersionAvailable");
                if (!releaseVersion2.isJavaVersionStillSupported()) {
                    text = text + "\n" + JAPMessages.getString(JAPUpdateWizard.MSG_JAVA_TOO_OLD, new Object[] { JavaVersionDBEntry.CURRENT_JAVA_VERSION, releaseVersion2.getSupportedJavaVersion() });
                }
            }
            this.m_taInfo.setText(text);
            this.m_taInfoScrollPane.getHorizontalScrollBar().setValue(0);
            this.m_labelVersion.setText(this.m_releaseVersion.getJapVersion());
            if (this.m_releaseVersion.getDate() != null) {
                this.m_labelDate.setText(this.m_DateFormat.format(this.m_releaseVersion.getDate()));
            }
            else {
                this.m_labelDate.setText(JAPMessages.getString("updateUnknown"));
            }
            this.m_bttnUpgrade.setEnabled(true);
            this.m_comboType.setSelectedIndex(0);
            this.itemStateChanged(new ItemEvent(this.m_comboType, 0, this.m_comboType, 1));
        }
        this.m_bttnCheckForUpgrade.setEnabled(true);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("UPGRADE")) {
            try {
                this.m_threadGetVersionInfo.join();
            }
            catch (NullPointerException ex2) {}
            catch (Exception ex) {
                LogHolder.log(2, LogType.MISC, ex);
            }
            if (this.m_comboType.getSelectedIndex() == 0) {
                new JAPUpdateWizard(this.m_releaseVersion, this.getRootPanel());
            }
            else {
                new JAPUpdateWizard(this.m_devVersion, this.getRootPanel());
            }
        }
        else if (actionEvent.getActionCommand().equals("CHECKFORUPGRADE")) {
            this.m_bttnCheckForUpgrade.setEnabled(false);
            (this.m_threadGetVersionInfo = new Thread(this)).start();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            if (this.m_comboType.getSelectedIndex() == 0) {
                this.m_labelVersion.setText(this.m_releaseVersion.getJapVersion());
                if (this.m_releaseVersion.getDate() != null) {
                    this.m_labelDate.setText(this.m_DateFormat.format(this.m_releaseVersion.getDate()));
                }
                else {
                    this.m_labelDate.setText(JAPMessages.getString("updateUnknown"));
                }
            }
            else {
                this.m_labelVersion.setText(this.m_devVersion.getJapVersion());
                if (this.m_devVersion.getDate() != null) {
                    this.m_labelDate.setText(this.m_DateFormat.format(this.m_devVersion.getDate()));
                }
                else {
                    this.m_labelDate.setText(JAPMessages.getString("updateUnknown"));
                }
            }
        }
    }
    
    public String getTabTitle() {
        return JAPMessages.getString("ngUpdatePanelTitle");
    }
    
    public String getHelpContext() {
        return "update";
    }
    
    protected void onRootPanelShown() {
        this.updateVersionInfo(false);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JAPConfUpdate.this.m_taInfoScrollPane.getHorizontalScrollBar().setValue(0);
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
        MSG_ALLOW_DIRECT_CONN = ((JAPConfUpdate.class$jap$JAPConfUpdate == null) ? (JAPConfUpdate.class$jap$JAPConfUpdate = class$("jap.JAPConfUpdate")) : JAPConfUpdate.class$jap$JAPConfUpdate).getName() + "_allowDirectConnection";
        MSG_REMIND_OPTIONAL_UPDATE = ((JAPConfUpdate.class$jap$JAPConfUpdate == null) ? (JAPConfUpdate.class$jap$JAPConfUpdate = class$("jap.JAPConfUpdate")) : JAPConfUpdate.class$jap$JAPConfUpdate).getName() + "_remindOptionalUpdate";
        MSG_REMIND_JAVA_UPDATE = ((JAPConfUpdate.class$jap$JAPConfUpdate == null) ? (JAPConfUpdate.class$jap$JAPConfUpdate = class$("jap.JAPConfUpdate")) : JAPConfUpdate.class$jap$JAPConfUpdate).getName() + "_remindJavaUpdate";
        MSG_INFO = ((JAPConfUpdate.class$jap$JAPConfUpdate == null) ? (JAPConfUpdate.class$jap$JAPConfUpdate = class$("jap.JAPConfUpdate")) : JAPConfUpdate.class$jap$JAPConfUpdate).getName() + "_info";
    }
}
