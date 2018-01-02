// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import anon.infoservice.MixInfo;
import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JList;
import java.awt.Point;
import java.awt.IllegalComponentStateException;
import java.awt.event.MouseEvent;
import java.awt.AWTEvent;
import gui.PopupMenu;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import gui.GUIUtils;
import anon.util.JAPMessages;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import java.util.Vector;
import anon.client.TrustModel;
import javax.swing.ListCellRenderer;
import javax.swing.ComboBoxModel;
import javax.swing.JPopupMenu;
import anon.infoservice.MixCascade;
import javax.swing.JComboBox;

public class JAPMixCascadeComboBox extends JComboBox
{
    static final String ITEM_AVAILABLE_SERVERS = "ITEM_AVAILABLE_SERVERS";
    static final String ITEM_NO_SERVERS_AVAILABLE = "ITEM_NO_SERVERS_AVAILABLE";
    private MixCascade m_currentCascade;
    private JPopupMenu m_comboPopup;
    private JAPMixCascadeComboBoxListCellRender m_renderer;
    
    public JAPMixCascadeComboBox() {
        this.setModel(new JAPMixCascadeComboBoxModel());
        this.setRenderer(this.m_renderer = new JAPMixCascadeComboBoxListCellRender());
        this.setEditable(false);
    }
    
    public void addItem(final Object o) {
    }
    
    public MixCascade getMixCascade() {
        return this.m_currentCascade;
    }
    
    public void showStaticPopup() {
        if (this.m_comboPopup != null) {
            this.m_comboPopup.setVisible(true);
        }
        else {
            super.showPopup();
        }
    }
    
    public boolean isPopupVisible() {
        return this.m_comboPopup != null && this.m_comboPopup.isVisible();
    }
    
    public void closeCascadePopupMenu() {
        this.m_renderer.closeCascadePopupMenu();
        if (this.m_comboPopup != null) {
            this.m_comboPopup.setVisible(false);
        }
        else {
            super.hidePopup();
        }
    }
    
    public synchronized void setMixCascade(final MixCascade currentCascade) {
        if (this.m_currentCascade == currentCascade) {
            return;
        }
        this.removeAllItems();
        this.m_currentCascade = currentCascade;
        if (this.m_currentCascade != null) {
            super.addItem(currentCascade);
        }
    }
    
    public void removeAllItems() {
        this.setModel(new JAPMixCascadeComboBoxModel());
        final Vector trustModels = TrustModel.getTrustModels();
        for (int i = 0; i < trustModels.size(); ++i) {
            final TrustModel trustModel = trustModels.elementAt(i);
            if (trustModel.isAdded()) {
                super.addItem(trustModel);
            }
        }
    }
    
    public void setNoDataAvailable() {
        super.insertItemAt("ITEM_NO_SERVERS_AVAILABLE", 1);
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        if (preferredSize.width > 50) {
            preferredSize.width = 50;
        }
        return preferredSize;
    }
    
    final class JAPMixCascadeComboBoxModel extends DefaultComboBoxModel
    {
        public void setSelectedItem(final Object selectedItem) {
            if (selectedItem instanceof TrustModel) {
                boolean b = false;
                boolean b2 = false;
                if (TrustModel.getCurrentTrustModel() == null || !TrustModel.getCurrentTrustModel().equals(selectedItem)) {
                    b2 = true;
                    b = true;
                }
                else if (!JAPController.getInstance().getAnonMode() || !TrustModel.getCurrentTrustModel().isTrusted(JAPController.getInstance().getCurrentMixCascade())) {
                    b2 = true;
                    b = false;
                }
                if (b2) {
                    Object selectedItem2 = null;
                    for (int i = 0; i < this.getSize(); ++i) {
                        if (this.getElementAt(i) instanceof MixCascade) {
                            selectedItem2 = this.getElementAt(i);
                        }
                    }
                    if (selectedItem2 != null) {
                        JAPMixCascadeComboBox.this.closeCascadePopupMenu();
                        super.setSelectedItem(selectedItem2);
                        if (b) {
                            JAPController.getInstance().switchTrustFilter((TrustModel)selectedItem);
                        }
                        else {
                            JAPController.getInstance().switchToNextMixCascade();
                        }
                    }
                }
                return;
            }
            if (selectedItem.equals("ITEM_NO_SERVERS_AVAILABLE") || selectedItem.equals("ITEM_AVAILABLE_SERVERS")) {
                return;
            }
            super.setSelectedItem(selectedItem);
        }
    }
    
    final class JAPMixCascadeComboBoxListCellRender implements ListCellRenderer
    {
        private final Color m_newCascadeColor;
        private JLabel m_componentNoServer;
        private JLabel m_componentAvailableServer;
        private JLabel m_componentUserServer;
        private JLabel m_componentAvailableCascade;
        private JLabel[] m_flags;
        private JLabel[] m_names;
        private JPanel m_componentPanel;
        private GridBagConstraints m_componentConstraints;
        private Object SYNC_POPUP;
        private JLabel m_lblCascadePopupMenu;
        private JLabel m_lblMenuArrow;
        private JPanel m_cascadePopupMenu;
        private CascadePopupMenu m_currentCascadePopup;
        private final /* synthetic */ JAPMixCascadeComboBox this$0;
        
        public JAPMixCascadeComboBoxListCellRender() {
            this.m_newCascadeColor = new Color(255, 255, 170);
            this.SYNC_POPUP = new Object();
            (this.m_componentPanel = new JPanel(new GridBagLayout())).setOpaque(true);
            this.m_componentConstraints = new GridBagConstraints();
            this.m_componentConstraints.anchor = 17;
            this.m_componentConstraints.gridy = 0;
            this.m_componentConstraints.insets = new Insets(0, 0, 0, 0);
            this.m_componentConstraints.fill = 2;
            (this.m_componentNoServer = new JLabel(JAPMessages.getString("ngMixComboNoServers"))).setIcon(GUIUtils.loadImageIcon("error.gif", true));
            this.m_componentNoServer.setBorder(new EmptyBorder(0, 3, 0, 3));
            this.m_componentNoServer.setForeground(Color.red);
            (this.m_componentAvailableServer = new JLabel(JAPMessages.getString("ngMixComboAvailableServers"))).setOpaque(true);
            this.m_componentAvailableServer.setHorizontalAlignment(2);
            this.m_componentAvailableServer.setBorder(new EmptyBorder(1, 3, 1, 3));
            (this.m_componentUserServer = new JLabel(JAPMessages.getString("ngMixComboUserServers"))).setBorder(new EmptyBorder(1, 3, 1, 3));
            this.m_componentUserServer.setHorizontalAlignment(2);
            this.m_componentUserServer.setOpaque(true);
            (this.m_componentAvailableCascade = new JLabel()).setHorizontalAlignment(2);
            this.m_componentAvailableCascade.setOpaque(true);
            this.m_componentAvailableCascade.setBorder(new EmptyBorder(1, 3, 1, 3));
            this.m_flags = new JLabel[3];
            this.m_names = new JLabel[3];
            for (int i = 0; i < this.m_flags.length; ++i) {
                (this.m_flags[i] = new JLabel()).setHorizontalAlignment(2);
                this.m_flags[i].setOpaque(true);
                this.m_flags[i].setBorder(new EmptyBorder(0, 1, 0, 2));
                (this.m_names[i] = new JLabel()).setHorizontalAlignment(2);
                this.m_names[i].setOpaque(true);
            }
            (this.m_lblCascadePopupMenu = new JLabel()).setOpaque(true);
            (this.m_cascadePopupMenu = new JPanel(new GridBagLayout())).setBorder(new EmptyBorder(1, 3, 1, 1));
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.anchor = 17;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            this.m_cascadePopupMenu.add(this.m_lblCascadePopupMenu, gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridx;
            gridBagConstraints.anchor = 13;
            gridBagConstraints.weightx = 1.0;
            (this.m_lblMenuArrow = new JLabel(GUIUtils.loadImageIcon("arrow46.gif", true))).setOpaque(true);
            this.m_cascadePopupMenu.add(this.m_lblMenuArrow, gridBagConstraints);
            this.m_cascadePopupMenu.setOpaque(true);
            synchronized (this.SYNC_POPUP) {
                (this.m_currentCascadePopup = new CascadePopupMenu(true)).registerExitHandler(new PopupMenu.ExitHandler() {
                    private final /* synthetic */ JAPMixCascadeComboBoxListCellRender this$1 = this$1;
                    
                    public void exited() {
                        this.this$1.m_currentCascadePopup.setVisible(false);
                        if (this.this$1.this$0.m_comboPopup == null || !this.this$1.this$0.m_comboPopup.isVisible()) {
                            this.this$1.this$0.showPopup();
                        }
                    }
                });
            }
            GUIUtils.addAWTEventListener(new GUIUtils.AWTEventListener() {
                private final /* synthetic */ JAPMixCascadeComboBoxListCellRender this$1 = this$1;
                
                public void eventDispatched(final AWTEvent awtEvent) {
                    if (awtEvent instanceof MouseEvent) {
                        final MouseEvent mouseEvent = (MouseEvent)awtEvent;
                        if (awtEvent.getSource() instanceof Component) {
                            final Component component = (Component)awtEvent.getSource();
                            Point point = null;
                            try {
                                final Point locationOnScreen;
                                point = (locationOnScreen = component.getLocationOnScreen());
                                locationOnScreen.x += mouseEvent.getX();
                                final Point point2 = point;
                                point2.y += mouseEvent.getY();
                            }
                            catch (IllegalComponentStateException ex) {}
                            synchronized (this.this$1.SYNC_POPUP) {
                                if (this.this$1.m_currentCascadePopup.getRelativePosition(point) == null && GUIUtils.getRelativePosition(point, this.this$1.this$0.m_comboPopup) == null && this.this$1.m_currentCascadePopup.isVisible() && (this.this$1.this$0.m_comboPopup == null || !this.this$1.this$0.m_comboPopup.isVisible())) {
                                    this.this$1.this$0.showStaticPopup();
                                }
                            }
                        }
                    }
                }
            });
        }
        
        public void closeCascadePopupMenu() {
            synchronized (this.SYNC_POPUP) {
                if (this.m_currentCascadePopup != null) {
                    this.m_currentCascadePopup.setVisible(false);
                }
            }
        }
        
        public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
            if (JAPMixCascadeComboBox.this.m_comboPopup == null) {
                GUIUtils.getMousePosition();
                Container container = this.m_cascadePopupMenu.getParent();
                while (container != null) {
                    container = container.getParent();
                    if (container instanceof JPopupMenu) {
                        JAPMixCascadeComboBox.this.m_comboPopup = (JPopupMenu)container;
                        break;
                    }
                }
            }
            if (o == null) {
                return new JLabel();
            }
            synchronized (this.SYNC_POPUP) {
                if (b && this.m_currentCascadePopup.isVisible() && this.m_currentCascadePopup.getTrustModel() != null && !this.m_currentCascadePopup.getTrustModel().equals(o) && this.m_currentCascadePopup.getMousePosition() == null) {
                    this.m_currentCascadePopup.setVisible(false);
                }
            }
            if (o instanceof TrustModel) {
                if (b) {
                    synchronized (this.SYNC_POPUP) {
                        if (!this.m_currentCascadePopup.isVisible()) {
                            final Point locationOnScreen = list.getLocationOnScreen();
                            final int n2 = locationOnScreen.x + list.getWidth();
                            final int n3 = locationOnScreen.y + list.indexToLocation(n).y;
                            if (this.m_currentCascadePopup.update((TrustModel)o)) {
                                final int n4 = n3 - this.m_currentCascadePopup.getHeaderHeight();
                                Point location = this.m_currentCascadePopup.calculateLocationOnScreen(list, new Point(n2, n4));
                                if (location.x < n2) {
                                    location = this.m_currentCascadePopup.calculateLocationOnScreen(list, new Point(locationOnScreen.x - this.m_currentCascadePopup.getWidth(), n4));
                                }
                                this.m_currentCascadePopup.setLocation(location);
                                this.m_currentCascadePopup.setVisible(true);
                            }
                        }
                    }
                    this.m_cascadePopupMenu.setBackground(list.getSelectionBackground());
                    this.m_cascadePopupMenu.setForeground(list.getSelectionForeground());
                }
                else {
                    this.m_cascadePopupMenu.setBackground(list.getBackground());
                    this.m_cascadePopupMenu.setForeground(list.getForeground());
                }
                this.m_lblMenuArrow.setBackground(this.m_cascadePopupMenu.getBackground());
                this.m_lblMenuArrow.setForeground(this.m_cascadePopupMenu.getForeground());
                this.m_lblCascadePopupMenu.setBackground(this.m_cascadePopupMenu.getBackground());
                this.m_lblCascadePopupMenu.setForeground(this.m_cascadePopupMenu.getForeground());
                if (((TrustModel)o).equals(TrustModel.getCurrentTrustModel())) {
                    this.m_lblCascadePopupMenu.setFont(new Font(this.m_lblCascadePopupMenu.getFont().getName(), 1, this.m_lblCascadePopupMenu.getFont().getSize()));
                }
                else {
                    this.m_lblCascadePopupMenu.setFont(new Font(this.m_lblCascadePopupMenu.getFont().getName(), 0, this.m_lblCascadePopupMenu.getFont().getSize()));
                }
                this.m_lblCascadePopupMenu.setText(((TrustModel)o).getName());
                return this.m_cascadePopupMenu;
            }
            if (o.equals("ITEM_NO_SERVERS_AVAILABLE")) {
                return this.m_componentNoServer;
            }
            if (o.equals("ITEM_AVAILABLE_SERVERS")) {
                return this.m_componentAvailableServer;
            }
            final MixCascade mixCascade = (MixCascade)o;
            ImageIcon icon;
            if (mixCascade.isUserDefined()) {
                if (TrustModel.getCurrentTrustModel().isTrusted(mixCascade)) {
                    icon = GUIUtils.loadImageIcon("servermanuell.gif", true);
                }
                else {
                    icon = GUIUtils.loadImageIcon("cdisabled.gif", true);
                }
            }
            else if (mixCascade.isPayment()) {
                if (TrustModel.getCurrentTrustModel().isTrusted(mixCascade)) {
                    icon = GUIUtils.loadImageIcon("serverwithpayment.gif", true);
                }
                else {
                    icon = GUIUtils.loadImageIcon("cdisabled.gif", true);
                }
            }
            else if (TrustModel.getCurrentTrustModel().isTrusted(mixCascade)) {
                icon = GUIUtils.loadImageIcon("serverfrominternet.gif", true);
            }
            else {
                icon = GUIUtils.loadImageIcon("cdisabled.gif", true);
            }
            if (mixCascade.isSocks5Supported()) {
                icon = GUIUtils.combine(icon, GUIUtils.loadImageIcon("socks_icon.gif", true));
            }
            final JLabel componentAvailableCascade = this.m_componentAvailableCascade;
            componentAvailableCascade.setIcon(icon);
            Color color;
            Color color2;
            if (b) {
                color = list.getSelectionBackground();
                color2 = list.getSelectionForeground();
            }
            else {
                color = list.getBackground();
                color2 = list.getForeground();
            }
            this.setSelectionColors(color, color2);
            final Vector decomposedCascadeName = mixCascade.getDecomposedCascadeName();
            this.m_componentConstraints.gridx = 0;
            this.m_componentConstraints.weightx = 0.0;
            this.m_componentPanel.add(componentAvailableCascade, this.m_componentConstraints);
            while (decomposedCascadeName.size() > 1 && decomposedCascadeName.size() > mixCascade.getNumberOfOperatorsShown()) {
                decomposedCascadeName.removeElementAt(decomposedCascadeName.size() - 1);
            }
            for (int i = 0; i < this.m_flags.length; ++i) {
                this.m_flags[i].setIcon(null);
                this.m_flags[i].setText("");
                this.m_names[i].setText("");
            }
            for (int n5 = 0; n5 < decomposedCascadeName.size() && n5 < this.m_flags.length; ++n5) {
                final GridBagConstraints componentConstraints = this.m_componentConstraints;
                ++componentConstraints.gridx;
                this.m_names[n5].setText(decomposedCascadeName.elementAt(n5));
                this.m_componentPanel.add(this.m_names[n5], this.m_componentConstraints);
                if (n5 + 1 == mixCascade.getNumberOfOperatorsShown() || n5 + 1 == decomposedCascadeName.size()) {
                    this.m_componentConstraints.weightx = 1.0;
                }
                else {
                    this.m_flags[n5].setText("-");
                }
                final GridBagConstraints componentConstraints2 = this.m_componentConstraints;
                ++componentConstraints2.gridx;
                final MixInfo mixInfo = mixCascade.getMixInfo(n5);
                if (mixInfo != null && mixInfo.getCertPath() != null && mixInfo.getCertPath().getSubject() != null) {
                    this.m_flags[n5].setIcon(GUIUtils.loadImageIcon("flags/" + mixInfo.getCertPath().getSubject().getCountryCode() + ".png"));
                }
                else {
                    this.m_flags[n5].setIcon(null);
                }
                this.m_componentPanel.add(this.m_flags[n5], this.m_componentConstraints);
            }
            return this.m_componentPanel;
        }
        
        private void setSelectionColors(final Color color, final Color color2) {
            this.m_componentAvailableCascade.setBackground(color);
            this.m_componentAvailableCascade.setForeground(color2);
            for (int i = 0; i < this.m_flags.length; ++i) {
                this.m_names[i].setBackground(color);
                this.m_names[i].setForeground(color2);
                this.m_flags[i].setBackground(color);
                this.m_flags[i].setForeground(color2);
            }
            this.m_componentPanel.setBackground(color);
            this.m_componentPanel.setForeground(color2);
        }
    }
}
