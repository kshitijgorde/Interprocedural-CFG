// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.util.Vector;
import javax.swing.ImageIcon;
import anon.infoservice.MixCascade;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JMenu;
import gui.JAPHelpContext;
import javax.swing.JFrame;
import gui.help.JAPHelp;
import platform.AbstractOS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JLabel;
import gui.GUIUtils;
import anon.util.JAPMessages;
import anon.infoservice.Database;
import anon.infoservice.StatusInfo;
import anon.client.TrustModel;
import gui.PopupMenu;

public class SystrayPopupMenu extends PopupMenu
{
    private static final String MSG_EXIT;
    private static final String MSG_SHOW_MAIN_WINDOW;
    private static final String MSG_SETTINGS;
    private static final String MSG_ANONYMITY_MODE;
    private static final String MSG_CURRENT_SERVICE;
    private static final String MSG_CONNECTED;
    private static final String MSG_NOT_CONNECTED;
    private static final String MSG_USER_NUMBER;
    private static final String MSG_SHOW_DETAILS;
    private static final String MSG_OPEN_BROWSER;
    public static final String MSG_ANONYMITY;
    public static final String MSG_ANONYMITY_ASCII;
    private MainWindowListener m_mainWindowListener;
    static /* synthetic */ Class class$jap$SystrayPopupMenu;
    static /* synthetic */ Class class$jap$SystrayPopupMenu$MainWindowListener;
    static /* synthetic */ Class class$anon$infoservice$StatusInfo;
    
    public SystrayPopupMenu(final MainWindowListener mainWindowListener) {
        if (mainWindowListener == null) {
            throw new IllegalArgumentException(((SystrayPopupMenu.class$jap$SystrayPopupMenu$MainWindowListener == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu$MainWindowListener = class$("jap.SystrayPopupMenu$MainWindowListener")) : SystrayPopupMenu.class$jap$SystrayPopupMenu$MainWindowListener).getName() + " is null!");
        }
        this.m_mainWindowListener = mainWindowListener;
        final MixCascade currentMixCascade = JAPController.getInstance().getCurrentMixCascade();
        final TrustModel currentTrustModel = TrustModel.getCurrentTrustModel();
        String s;
        if (JAPController.getInstance().isAnonConnected()) {
            final StatusInfo statusInfo = (StatusInfo)Database.getInstance((SystrayPopupMenu.class$anon$infoservice$StatusInfo == null) ? (SystrayPopupMenu.class$anon$infoservice$StatusInfo = class$("anon.infoservice.StatusInfo")) : SystrayPopupMenu.class$anon$infoservice$StatusInfo).getEntryById(currentMixCascade.getId());
            s = JAPMessages.getString(SystrayPopupMenu.MSG_ANONYMITY) + ": " + currentMixCascade.getDistribution() + "," + ((statusInfo == null || statusInfo.getAnonLevel() < 0) ? "?" : ("" + statusInfo.getAnonLevel())) + " / " + 6 + "," + 6;
        }
        else {
            s = JAPMessages.getString(SystrayPopupMenu.MSG_NOT_CONNECTED);
        }
        ImageIcon icon;
        if (currentMixCascade.isPayment()) {
            icon = GUIUtils.loadImageIcon("serverwithpayment.gif");
        }
        else if (currentMixCascade.isUserDefined()) {
            icon = GUIUtils.loadImageIcon("servermanuell.gif");
        }
        else {
            icon = GUIUtils.loadImageIcon("serverfrominternet.gif");
        }
        final JLabel label = new JLabel(GUIUtils.trim(currentMixCascade.getName()));
        GUIUtils.setFontStyle(label, 1);
        label.setIcon(icon);
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 17;
        panel.add(label, gridBagConstraints);
        this.add(panel);
        final JPanel panel2 = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.insets = new Insets(0, icon.getIconWidth() + label.getIconTextGap(), 0, 5);
        panel2.add(new JLabel("(" + s + ")"), gridBagConstraints2);
        this.add(panel2);
        final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem(JAPMessages.getString(SystrayPopupMenu.MSG_ANONYMITY_MODE));
        GUIUtils.setFontStyle(checkBoxMenuItem, 0);
        checkBoxMenuItem.setSelected(JAPController.getInstance().getAnonMode());
        checkBoxMenuItem.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                JAPController.getInstance().setAnonMode(checkBoxMenuItem.isSelected());
            }
        });
        this.add(checkBoxMenuItem);
        final JMenuItem menuItem = new JMenuItem(JAPMessages.getString(SystrayPopupMenu.MSG_SHOW_DETAILS));
        GUIUtils.setFontStyle(menuItem, 0);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SystrayPopupMenu.this.m_mainWindowListener.onShowSettings("ANON_TAB", JAPController.getInstance().getCurrentMixCascade());
            }
        });
        this.add(menuItem);
        this.addSeparator();
        final JMenuItem menuItem2 = new JMenuItem(JAPMessages.getString(SystrayPopupMenu.MSG_SHOW_MAIN_WINDOW));
        GUIUtils.setFontStyle(menuItem2, 0);
        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SystrayPopupMenu.this.m_mainWindowListener.onShowMainWindow();
            }
        });
        this.add(menuItem2);
        if (AbstractOS.getInstance().isDefaultURLAvailable()) {
            final JMenuItem menuItem3 = new JMenuItem(JAPMessages.getString(SystrayPopupMenu.MSG_OPEN_BROWSER));
            GUIUtils.setFontStyle(menuItem3, 0);
            menuItem3.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    AbstractOS.getInstance().openBrowser();
                }
            });
            this.add(menuItem3);
        }
        final JMenuItem menuItem4 = new JMenuItem(JAPMessages.getString(SystrayPopupMenu.MSG_SETTINGS));
        GUIUtils.setFontStyle(menuItem4, 0);
        menuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SystrayPopupMenu.this.m_mainWindowListener.onShowSettings(null, null);
            }
        });
        this.add(menuItem4);
        final JMenuItem menuItem5 = new JMenuItem(JAPMessages.getString(JAPHelp.MSG_HELP_MENU_ITEM));
        GUIUtils.setFontStyle(menuItem5, 0);
        this.add(menuItem5);
        menuItem5.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                SystrayPopupMenu.this.m_mainWindowListener.onShowHelp();
                JAPHelp.getInstance().setContext(JAPHelpContext.createHelpContext("index", (JAPController.getInstance().getViewWindow() instanceof JFrame) ? ((JFrame)JAPController.getInstance().getViewWindow()) : null));
                JAPHelp.getInstance().loadCurrentContext();
            }
        });
        this.addSeparator();
        final JPanel panel3 = new JPanel(new GridBagLayout());
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.anchor = 10;
        panel3.add(new JLabel(JAPMessages.getString("ngMixComboAvailableServers")), gridBagConstraints3);
        this.add(panel3);
        final Vector trustModels = TrustModel.getTrustModels();
        for (int i = 0; i < trustModels.size(); ++i) {
            final TrustModel trustModel = trustModels.elementAt(i);
            if (trustModel.isAdded()) {
                final JMenu menu = new JMenu(trustModel.getName());
                if (currentTrustModel.equals(trustModel)) {
                    GUIUtils.setFontStyle(menu, 1);
                }
                else {
                    GUIUtils.setFontStyle(menu, 0);
                }
                final CascadePopupMenu cascadePopupMenu = new CascadePopupMenu(menu.getPopupMenu());
                this.add(menu);
                menu.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(final MouseEvent mouseEvent) {
                        if (TrustModel.getCurrentTrustModel() == null || !TrustModel.getCurrentTrustModel().equals(trustModel)) {
                            JAPController.getInstance().switchTrustFilter(trustModel);
                            SystrayPopupMenu.this.setVisible(false);
                        }
                        else if (!JAPController.getInstance().getAnonMode() || !TrustModel.getCurrentTrustModel().isTrusted(JAPController.getInstance().getCurrentMixCascade())) {
                            JAPController.getInstance().switchToNextMixCascade();
                            SystrayPopupMenu.this.setVisible(false);
                        }
                    }
                });
                menu.addMenuListener(new MenuListener() {
                    public void menuSelected(final MenuEvent menuEvent) {
                        cascadePopupMenu.update(trustModel);
                    }
                    
                    public void menuDeselected(final MenuEvent menuEvent) {
                    }
                    
                    public void menuCanceled(final MenuEvent menuEvent) {
                    }
                });
            }
        }
        final JMenuItem menuItem6 = new JMenuItem(JAPMessages.getString(SystrayPopupMenu.MSG_EXIT));
        GUIUtils.setFontStyle(menuItem6, 0);
        menuItem6.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPController.goodBye(true);
            }
        });
        this.addSeparator();
        this.add(menuItem6);
        this.pack();
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
        MSG_EXIT = ((SystrayPopupMenu.class$jap$SystrayPopupMenu == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : SystrayPopupMenu.class$jap$SystrayPopupMenu).getName() + "_exit";
        MSG_SHOW_MAIN_WINDOW = ((SystrayPopupMenu.class$jap$SystrayPopupMenu == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : SystrayPopupMenu.class$jap$SystrayPopupMenu).getName() + "_showMainWindow";
        MSG_SETTINGS = ((SystrayPopupMenu.class$jap$SystrayPopupMenu == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : SystrayPopupMenu.class$jap$SystrayPopupMenu).getName() + "_settings";
        MSG_ANONYMITY_MODE = ((SystrayPopupMenu.class$jap$SystrayPopupMenu == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : SystrayPopupMenu.class$jap$SystrayPopupMenu).getName() + "_anonymityMode";
        MSG_CURRENT_SERVICE = ((SystrayPopupMenu.class$jap$SystrayPopupMenu == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : SystrayPopupMenu.class$jap$SystrayPopupMenu).getName() + "_currentService";
        MSG_CONNECTED = ((SystrayPopupMenu.class$jap$SystrayPopupMenu == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : SystrayPopupMenu.class$jap$SystrayPopupMenu).getName() + "_connected";
        MSG_NOT_CONNECTED = ((SystrayPopupMenu.class$jap$SystrayPopupMenu == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : SystrayPopupMenu.class$jap$SystrayPopupMenu).getName() + "_notConnected";
        MSG_USER_NUMBER = ((SystrayPopupMenu.class$jap$SystrayPopupMenu == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : SystrayPopupMenu.class$jap$SystrayPopupMenu).getName() + "_userNumber";
        MSG_SHOW_DETAILS = ((SystrayPopupMenu.class$jap$SystrayPopupMenu == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : SystrayPopupMenu.class$jap$SystrayPopupMenu).getName() + "_showDetails";
        MSG_OPEN_BROWSER = ((SystrayPopupMenu.class$jap$SystrayPopupMenu == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : SystrayPopupMenu.class$jap$SystrayPopupMenu).getName() + "_openBrowser";
        MSG_ANONYMITY = ((SystrayPopupMenu.class$jap$SystrayPopupMenu == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : SystrayPopupMenu.class$jap$SystrayPopupMenu).getName() + "_anonymity";
        MSG_ANONYMITY_ASCII = ((SystrayPopupMenu.class$jap$SystrayPopupMenu == null) ? (SystrayPopupMenu.class$jap$SystrayPopupMenu = class$("jap.SystrayPopupMenu")) : SystrayPopupMenu.class$jap$SystrayPopupMenu).getName() + "_anonymityOnlyAsciiCharacters";
    }
    
    public interface MainWindowListener
    {
        void onShowMainWindow();
        
        void onShowSettings(final String p0, final Object p1);
        
        void onShowHelp();
    }
}
