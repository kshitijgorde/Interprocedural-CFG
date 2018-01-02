// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.lang.reflect.InvocationTargetException;
import anon.infoservice.StatusInfo;
import anon.infoservice.MixCascade;
import java.awt.event.ActionEvent;
import anon.util.Util;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Window;
import java.awt.Point;
import anon.infoservice.JavaVersionDBEntry;
import javax.swing.SwingUtilities;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Insets;
import anon.util.JAPMessages;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import logging.LogHolder;
import logging.LogType;
import java.awt.Frame;
import gui.GUIUtils;
import java.text.NumberFormat;
import java.awt.Font;
import gui.dialog.JAPDialog;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JWindow;

public final class JAPViewIconified extends JWindow implements ActionListener
{
    private static final long serialVersionUID = 1L;
    public static final String XML_LOCATION = "IconifiedLocation";
    public static final String MSG_ANON_LOW;
    public static final String MSG_ANON_FAIR;
    public static final String MSG_ANON_HIGH;
    public static final String MSG_ANON;
    private static final String MSG_TT_SWITCH_ANONYMITY;
    private static final String STR_HIDDEN_WINDOW;
    private static JFrame m_frameParent;
    private JAPController m_Controller;
    private AbstractJAPMainView m_mainView;
    private JLabel m_labelBytes;
    private JLabel m_labelUsers;
    private JLabel m_labelTraffic;
    private JLabel m_labelAnon;
    private JLabel m_lblJAPIcon;
    private JAPDialog ms_popupWindow;
    private JLabel m_lblBytes;
    private Font m_fontDlg;
    private NumberFormat m_NumberFormat;
    private boolean m_anonModeDisabled;
    private Object SYNC_CURSOR;
    private GUIUtils.WindowDocker m_docker;
    private Runnable m_runnableValueUpdate;
    static /* synthetic */ Class class$jap$JAPViewIconified;
    
    private static JFrame getParentFrame() {
        if (JAPViewIconified.m_frameParent == null) {
            JAPViewIconified.m_frameParent = new JFrame(JAPViewIconified.STR_HIDDEN_WINDOW);
        }
        return JAPViewIconified.m_frameParent;
    }
    
    public JAPViewIconified(final AbstractJAPMainView mainView) {
        super(getParentFrame());
        this.m_anonModeDisabled = false;
        this.SYNC_CURSOR = new Object();
        this.m_fontDlg = new Font("Sans", 1, 11);
        this.setName(JAPViewIconified.STR_HIDDEN_WINDOW);
        this.m_mainView = mainView;
        if (JAPViewIconified.m_frameParent != null) {
            JAPViewIconified.m_frameParent.setIconImage(this.m_mainView.getIconImage());
        }
        LogHolder.log(6, LogType.MISC, "Initializing...");
        this.m_Controller = JAPController.getInstance();
        this.m_NumberFormat = NumberFormat.getInstance();
        this.m_runnableValueUpdate = new Runnable() {
            public void run() {
                JAPViewIconified.this.updateValues1();
            }
        };
        this.init();
    }
    
    private void init() {
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JPanel panel = new JPanel(gridBagLayout);
        panel.setOpaque(false);
        (this.m_lblBytes = new JLabel(JAPMessages.getString("iconifiedviewBytes") + ": ", 4)).setFont(this.m_fontDlg);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(3, 3, 0, 0);
        gridBagConstraints.anchor = 18;
        gridBagLayout.setConstraints(this.m_lblBytes, gridBagConstraints);
        panel.add(this.m_lblBytes);
        gridBagConstraints.weightx = 1.0;
        (this.m_labelBytes = new JLabel("000000,0", 2)).setFont(this.m_fontDlg);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagLayout.setConstraints(this.m_labelBytes, gridBagConstraints);
        panel.add(this.m_labelBytes);
        final JLabel label = new JLabel(JAPMessages.getString("iconifiedviewUsers") + ": ", 4);
        label.setFont(this.m_fontDlg);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        (this.m_labelUsers = new JLabel("", 2)).setFont(this.m_fontDlg);
        gridBagConstraints.gridx = 1;
        gridBagLayout.setConstraints(this.m_labelUsers, gridBagConstraints);
        final JLabel label2 = new JLabel(JAPMessages.getString("iconifiedviewTraffic") + ": ", 4);
        label2.setFont(this.m_fontDlg);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        (this.m_labelTraffic = new JLabel("", 2)).setFont(this.m_fontDlg);
        gridBagConstraints.gridx = 1;
        gridBagLayout.setConstraints(this.m_labelTraffic, gridBagConstraints);
        final JLabel label3 = new JLabel(JAPMessages.getString(JAPViewIconified.MSG_ANON) + ": ", 4);
        label3.setFont(this.m_fontDlg);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        gridBagConstraints.gridx = 0;
        panel.add(label3, gridBagConstraints);
        final char[] array = new char[Math.max(Math.max(Math.max(0, JAPMessages.getString(JAPViewIconified.MSG_ANON_LOW).length()), JAPMessages.getString(JAPViewIconified.MSG_ANON_FAIR).length()), JAPMessages.getString(JAPViewIconified.MSG_ANON_HIGH).length())];
        for (int i = 0; i < array.length; ++i) {
            array[i] = 'A';
        }
        (this.m_labelAnon = new JLabel(new String(array), 2)).setFont(this.m_fontDlg);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridx;
        panel.add(this.m_labelAnon, gridBagConstraints);
        final JButton button = new JButton(GUIUtils.loadImageIcon("enlarge.gif", true, false));
        button.setOpaque(false);
        button.addActionListener(this);
        button.setToolTipText(JAPMessages.getString("enlargeWindow"));
        JAPUtil.setMnemonic(button, JAPMessages.getString("iconifyButtonMn"));
        final JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new LineBorder(Color.black, 1));
        contentPane.add(panel, "Center");
        final JPanel panel2 = new JPanel(new BorderLayout());
        (this.m_lblJAPIcon = new JLabel(GUIUtils.loadImageIcon(((JAPViewIconified.class$jap$JAPViewIconified == null) ? (JAPViewIconified.class$jap$JAPViewIconified = class$("jap.JAPViewIconified")) : JAPViewIconified.class$jap$JAPViewIconified).getName() + "_icon16discon.gif", true, false))).setToolTipText(JAPMessages.getString(JAPViewIconified.MSG_TT_SWITCH_ANONYMITY));
        this.m_lblJAPIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (!JAPViewIconified.this.m_anonModeDisabled && GUIUtils.isMouseButton(mouseEvent, 16)) {
                    JAPViewIconified.this.m_lblJAPIcon.setBorder(BorderFactory.createLoweredBevelBorder());
                    JAPViewIconified.this.m_Controller.setAnonMode(!JAPViewIconified.this.m_Controller.getAnonMode());
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                synchronized (JAPViewIconified.this.SYNC_CURSOR) {
                    if (!JAPViewIconified.this.m_anonModeDisabled) {
                        JAPViewIconified.this.setCursor(Cursor.getPredefinedCursor(12));
                        JAPViewIconified.this.getRootPane().setToolTipText(JAPMessages.getString(JAPViewIconified.MSG_TT_SWITCH_ANONYMITY));
                    }
                }
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                synchronized (JAPViewIconified.this.SYNC_CURSOR) {
                    JAPViewIconified.this.setCursor(Cursor.getPredefinedCursor(0));
                    JAPViewIconified.this.getRootPane().setToolTipText(null);
                }
            }
        });
        panel2.add(this.m_lblJAPIcon, "South");
        panel2.add(button, "North");
        contentPane.add(panel2, "East");
        contentPane.addMouseListener(new MouseAdapter() {
            private final /* synthetic */ JAPViewIconified this$0;
            
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (SwingUtilities.isRightMouseButton(mouseEvent) || mouseEvent.isPopupTrigger()) {
                    final SystrayPopupMenu systrayPopupMenu = new SystrayPopupMenu(new SystrayPopupMenu.MainWindowListener() {
                        private final /* synthetic */ JAPViewIconified$3 this$1 = this$1;
                        
                        public void onShowMainWindow() {
                            this.this$1.this$0.switchBackToMainView();
                        }
                        
                        public void onShowSettings(final String s, final Object o) {
                            this.this$1.this$0.m_mainView.showConfigDialog(s, o);
                        }
                        
                        public void onShowHelp() {
                            this.this$1.this$0.switchBackToMainView();
                        }
                    });
                    if (JavaVersionDBEntry.CURRENT_JAVA_VENDOR.toLowerCase().indexOf("sun") >= 0 && JavaVersionDBEntry.CURRENT_JAVA_VERSION.compareTo("1.6.0_02") >= 0) {
                        if (JAPViewIconified.this.ms_popupWindow == null) {
                            JAPViewIconified.this.ms_popupWindow = new JAPDialog(JAPViewIconified.this, JAPViewIconified.STR_HIDDEN_WINDOW, false);
                            JAPViewIconified.this.ms_popupWindow.setName(JAPViewIconified.STR_HIDDEN_WINDOW);
                            JAPViewIconified.this.ms_popupWindow.pack();
                            JAPViewIconified.this.ms_popupWindow.setLocation(20000, 20000);
                        }
                        JAPViewIconified.this.ms_popupWindow.setVisible(true);
                        systrayPopupMenu.show(JAPViewIconified.this.ms_popupWindow.getContentPane(), JAPViewIconified.this, new Point(mouseEvent.getX() + JAPViewIconified.this.getLocation().x, mouseEvent.getY() + JAPViewIconified.this.getLocation().y));
                    }
                    else {
                        systrayPopupMenu.show(JAPViewIconified.this, new Point(mouseEvent.getX() + JAPViewIconified.this.getLocation().x, mouseEvent.getY() + JAPViewIconified.this.getLocation().y));
                    }
                }
                else if (mouseEvent.getClickCount() > 1) {
                    JAPViewIconified.this.switchBackToMainView();
                }
            }
        });
        this.setContentPane(contentPane);
        this.m_docker = new GUIUtils.WindowDocker(contentPane);
        this.pack();
        final GUIUtils.Screen currentScreen = GUIUtils.getCurrentScreen(this);
        if (this.getSize().width > currentScreen.getWidth() || this.getSize().height > currentScreen.getHeight()) {
            LogHolder.log(3, LogType.GUI, "Packed iconified view with illegal size! Width:" + this.getSize().width + " Height:" + this.getSize().height + "\nSetting defaults...");
            if (JAPModel.getInstance().getIconifiedSize() != null && JAPModel.getInstance().getIconifiedSize().width > 0 && JAPModel.getInstance().getIconifiedSize().height > 0) {
                this.setSize(JAPModel.getInstance().getIconifiedSize());
            }
            else {
                this.setSize(new Dimension(151, 85));
            }
        }
        else {
            JAPModel.getInstance().setIconifiedSize(this.getSize());
        }
        GUIUtils.moveToUpRightCorner(this);
        GUIUtils.restoreLocation(this, JAPModel.getInstance().getIconifiedWindowLocation());
        this.m_labelBytes.setText(Util.formatBytesValueWithoutUnit(0L));
        this.m_lblBytes.setText(Util.formatBytesValueOnlyUnit(0L) + ": ");
        this.m_labelUsers.setText(JAPMessages.getString("iconifiedViewNA"));
        this.m_labelTraffic.setText(JAPMessages.getString("iconifiedViewNA"));
        this.m_labelAnon.setText(JAPMessages.getString("iconifiedViewNA"));
    }
    
    public void setVisible(final boolean visible) {
        if (visible) {
            GUIUtils.setAlwaysOnTop(this, JAPModel.getInstance().isMiniViewOnTop());
        }
        JAPController.getInstance().switchViewWindow(!visible);
        super.setVisible(visible);
    }
    
    public void switchBackToMainView() {
        if (this.m_mainView == null || (!this.isVisible() && this.m_mainView.isVisible())) {
            return;
        }
        this.m_mainView.setVisible(true);
        this.setVisible(false);
        this.m_mainView.toFront();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.switchBackToMainView();
    }
    
    private void updateValues1() {
        synchronized (this.m_runnableValueUpdate) {
            try {
                Label_0414: {
                    if (this.m_Controller.isAnonConnected()) {
                        final MixCascade currentMixCascade = this.m_Controller.getCurrentMixCascade();
                        final StatusInfo currentStatus = currentMixCascade.getCurrentStatus();
                        final int anonLevel = currentStatus.getAnonLevel();
                        this.m_labelAnon.setText(currentMixCascade.getDistribution() + "," + ((anonLevel < 0) ? "?" : ("" + anonLevel)) + " / " + 6 + "," + 6);
                        if (currentStatus.getNrOfActiveUsers() != -1) {
                            this.m_labelUsers.setText(this.m_NumberFormat.format(currentStatus.getNrOfActiveUsers()));
                        }
                        else {
                            this.m_labelUsers.setText(JAPMessages.getString("iconifiedViewNA"));
                        }
                        final int trafficSituation = currentStatus.getTrafficSituation();
                        if (trafficSituation > -1) {
                            if (trafficSituation < 30) {
                                this.m_labelTraffic.setText(JAPMessages.getString("iconifiedViewMeterTrafficLow"));
                            }
                            else if (trafficSituation < 60) {
                                this.m_labelTraffic.setText(JAPMessages.getString("iconifiedViewMeterTrafficMedium"));
                            }
                            else {
                                this.m_labelTraffic.setText(JAPMessages.getString("iconifiedViewMeterTrafficHigh"));
                            }
                        }
                        else {
                            this.m_labelTraffic.setText(JAPMessages.getString("iconifiedViewNA"));
                        }
                        synchronized (this.m_lblJAPIcon) {
                            String s;
                            if (JAPModel.getInstance().getProgramName().equals("JonDo")) {
                                s = "JonDo.ico.gif";
                            }
                            else {
                                s = "icon16.gif";
                            }
                            this.m_lblJAPIcon.setIcon(GUIUtils.loadImageIcon(s, true, false));
                            break Label_0414;
                        }
                    }
                    this.m_labelUsers.setText(JAPMessages.getString("iconifiedViewNA"));
                    this.m_labelTraffic.setText(JAPMessages.getString("iconifiedViewNA"));
                    this.m_labelAnon.setText(JAPMessages.getString("iconifiedViewNA"));
                    synchronized (this.m_lblJAPIcon) {
                        this.m_lblJAPIcon.setIcon(GUIUtils.loadImageIcon(((JAPViewIconified.class$jap$JAPViewIconified == null) ? (JAPViewIconified.class$jap$JAPViewIconified = class$("jap.JAPViewIconified")) : JAPViewIconified.class$jap$JAPViewIconified).getName() + "_icon16discon.gif", true, false));
                    }
                }
                this.setButtonBorder();
            }
            catch (Throwable t) {}
        }
    }
    
    public void dispose() {
        this.m_docker.finalize();
        super.dispose();
    }
    
    public void disableSetAnonMode() {
        this.m_anonModeDisabled = true;
    }
    
    public void updateValues(final boolean b) {
        if (SwingUtilities.isEventDispatchThread()) {
            this.m_runnableValueUpdate.run();
        }
        else {
            try {
                if (b) {
                    SwingUtilities.invokeAndWait(this.m_runnableValueUpdate);
                }
            }
            catch (InvocationTargetException ex) {}
            catch (InterruptedException ex2) {}
            SwingUtilities.invokeLater(this.m_runnableValueUpdate);
        }
    }
    
    public void channelsChanged(final int n) {
    }
    
    public void packetMixed(final long n) {
        final Runnable runnable = new Runnable() {
            public void run() {
                JAPViewIconified.this.m_lblBytes.setText(Util.formatBytesValueOnlyUnit(n) + ":");
                JAPViewIconified.this.m_labelBytes.setText(Util.formatBytesValueWithoutUnit(n));
            }
        };
        if (SwingUtilities.isEventDispatchThread()) {
            runnable.run();
        }
        else {
            SwingUtilities.invokeLater(runnable);
        }
    }
    
    private void setButtonBorder() {
        if (!this.m_anonModeDisabled) {
            this.m_lblJAPIcon.setBorder(BorderFactory.createRaisedBevelBorder());
        }
        else {
            this.m_lblJAPIcon.setBorder(BorderFactory.createEmptyBorder());
        }
    }
    
    public void blink() {
        if (this.isVisible()) {
            synchronized (this.m_lblJAPIcon) {
                if (this.m_Controller.isAnonConnected()) {
                    this.m_lblJAPIcon.setIcon(GUIUtils.loadImageIcon(((JAPViewIconified.class$jap$JAPViewIconified == null) ? (JAPViewIconified.class$jap$JAPViewIconified = class$("jap.JAPViewIconified")) : JAPViewIconified.class$jap$JAPViewIconified).getName() + "_icon16red.gif", true, false));
                    try {
                        this.m_lblJAPIcon.wait(250L);
                    }
                    catch (InterruptedException ex) {}
                }
                if (this.m_Controller.isAnonConnected()) {
                    String s;
                    if (JAPModel.getInstance().getProgramName().equals("JonDo")) {
                        s = "JonDo.ico.gif";
                    }
                    else {
                        s = "icon16.gif";
                    }
                    this.m_lblJAPIcon.setIcon(GUIUtils.loadImageIcon(s, true, false));
                }
                else {
                    this.m_lblJAPIcon.setIcon(GUIUtils.loadImageIcon(((JAPViewIconified.class$jap$JAPViewIconified == null) ? (JAPViewIconified.class$jap$JAPViewIconified = class$("jap.JAPViewIconified")) : JAPViewIconified.class$jap$JAPViewIconified).getName() + "_icon16discon.gif", true, false));
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
        MSG_ANON_LOW = ((JAPViewIconified.class$jap$JAPViewIconified == null) ? (JAPViewIconified.class$jap$JAPViewIconified = class$("jap.JAPViewIconified")) : JAPViewIconified.class$jap$JAPViewIconified).getName() + "_anonLow";
        MSG_ANON_FAIR = ((JAPViewIconified.class$jap$JAPViewIconified == null) ? (JAPViewIconified.class$jap$JAPViewIconified = class$("jap.JAPViewIconified")) : JAPViewIconified.class$jap$JAPViewIconified).getName() + "_anonFair";
        MSG_ANON_HIGH = ((JAPViewIconified.class$jap$JAPViewIconified == null) ? (JAPViewIconified.class$jap$JAPViewIconified = class$("jap.JAPViewIconified")) : JAPViewIconified.class$jap$JAPViewIconified).getName() + "_anonHigh";
        MSG_ANON = ((JAPViewIconified.class$jap$JAPViewIconified == null) ? (JAPViewIconified.class$jap$JAPViewIconified = class$("jap.JAPViewIconified")) : JAPViewIconified.class$jap$JAPViewIconified).getName() + "_anon";
        MSG_TT_SWITCH_ANONYMITY = ((JAPViewIconified.class$jap$JAPViewIconified == null) ? (JAPViewIconified.class$jap$JAPViewIconified = class$("jap.JAPViewIconified")) : JAPViewIconified.class$jap$JAPViewIconified).getName() + "_ttSwitchAnonymity";
        STR_HIDDEN_WINDOW = Double.toString(Math.random());
    }
}
