// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.util.Enumeration;
import java.awt.Font;
import anon.infoservice.MixCascade;
import javax.swing.Icon;
import gui.GUIUtils;
import anon.util.JAPMessages;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.util.Vector;
import anon.infoservice.Database;
import javax.swing.JPopupMenu;
import anon.client.TrustModel;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import java.util.Hashtable;
import java.awt.Color;
import gui.PopupMenu;

public class CascadePopupMenu extends PopupMenu
{
    private static final String MSG_EDIT_FILTER;
    private static final int MAX_CASCADE_NAME_LENGTH = 35;
    private final Color m_newCascadeColor;
    private Hashtable m_menuItems;
    private JMenuItem m_editFilter;
    private ActionListener m_cascadeItemListener;
    private TrustModel m_trustModel;
    private int m_headerHeight;
    static /* synthetic */ Class class$jap$JAPConfAnon;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    static /* synthetic */ Class class$anon$infoservice$NewCascadeIDEntry;
    
    public CascadePopupMenu() {
        this(new JPopupMenu());
    }
    
    public CascadePopupMenu(final boolean b) {
        super(b);
        this.m_newCascadeColor = new Color(255, 255, 170);
        this.m_headerHeight = 0;
        this.m_menuItems = new Hashtable();
        this.m_cascadeItemListener = new CascadeItemListener();
    }
    
    public CascadePopupMenu(final JPopupMenu popupMenu) {
        super(popupMenu);
        this.m_newCascadeColor = new Color(255, 255, 170);
        this.m_headerHeight = 0;
        this.m_menuItems = new Hashtable();
        this.m_cascadeItemListener = new CascadeItemListener();
    }
    
    public TrustModel getTrustModel() {
        return this.m_trustModel;
    }
    
    public int getHeaderHeight() {
        return this.m_headerHeight;
    }
    
    public boolean update(final TrustModel trustModel) {
        boolean b = false;
        if (trustModel == null) {
            throw new IllegalArgumentException("Given argument is null!");
        }
        this.m_trustModel = trustModel;
        final Hashtable entryHash = Database.getInstance((CascadePopupMenu.class$anon$infoservice$MixCascade == null) ? (CascadePopupMenu.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : CascadePopupMenu.class$anon$infoservice$MixCascade).getEntryHash();
        final MixCascade currentMixCascade = JAPController.getInstance().getCurrentMixCascade();
        if (currentMixCascade != null && !entryHash.containsKey(currentMixCascade.getId())) {
            entryHash.put(currentMixCascade.getId(), currentMixCascade);
        }
        final Enumeration<MixCascade> elements = entryHash.elements();
        if (elements.hasMoreElements()) {
            final Vector vector = new Vector<JMenuItem>();
            this.removeAll();
            this.m_menuItems.clear();
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.anchor = 10;
            panel.add(new JLabel(this.m_trustModel.getName()), gridBagConstraints);
            this.add(panel);
            final JSeparator separator = new JSeparator();
            this.addSeparator(separator);
            this.m_headerHeight = panel.getPreferredSize().height + separator.getPreferredSize().height;
            if (trustModel == TrustModel.getCustomFilter()) {
                (this.m_editFilter = new JMenuItem(JAPMessages.getString(CascadePopupMenu.MSG_EDIT_FILTER))).addActionListener(this.m_cascadeItemListener);
                this.m_editFilter.setIcon(GUIUtils.loadImageIcon("servermanuell.gif", true));
                this.add(this.m_editFilter);
                this.addSeparator(new JSeparator());
            }
            while (elements.hasMoreElements()) {
                final MixCascade mixCascade = elements.nextElement();
                if (!this.m_trustModel.isTrusted(mixCascade)) {
                    continue;
                }
                ImageIcon imageIcon;
                if (mixCascade.isUserDefined()) {
                    imageIcon = GUIUtils.loadImageIcon("servermanuell.gif");
                }
                else if (mixCascade.isPayment()) {
                    imageIcon = GUIUtils.loadImageIcon("serverwithpayment.gif");
                }
                else {
                    imageIcon = GUIUtils.loadImageIcon("serverfrominternet.gif");
                }
                if (mixCascade.isSocks5Supported()) {
                    imageIcon = GUIUtils.combine(imageIcon, GUIUtils.loadImageIcon("socks_icon.gif", true));
                }
                final JMenuItem menuItem = new JMenuItem(GUIUtils.trim(mixCascade.getName(), 35), imageIcon);
                if (this.isNewCascade(mixCascade)) {
                    menuItem.setBackground(this.m_newCascadeColor);
                }
                if (currentMixCascade != null && currentMixCascade.equals(mixCascade)) {
                    menuItem.setFont(new Font(menuItem.getFont().getName(), 1, menuItem.getFont().getSize()));
                    this.add(menuItem);
                }
                else {
                    menuItem.setFont(new Font(menuItem.getFont().getName(), 0, menuItem.getFont().getSize()));
                    if (mixCascade.isUserDefined()) {
                        vector.addElement(menuItem);
                    }
                    else {
                        this.add(menuItem);
                    }
                }
                menuItem.addActionListener(this.m_cascadeItemListener);
                this.m_menuItems.put(menuItem, mixCascade);
                b = true;
            }
            for (int i = 0; i < vector.size(); ++i) {
                this.add(vector.elementAt(i));
            }
        }
        if (b) {
            this.pack();
        }
        return b;
    }
    
    private boolean isNewCascade(final MixCascade mixCascade) {
        return Database.getInstance((CascadePopupMenu.class$anon$infoservice$NewCascadeIDEntry == null) ? (CascadePopupMenu.class$anon$infoservice$NewCascadeIDEntry = class$("anon.infoservice.NewCascadeIDEntry")) : CascadePopupMenu.class$anon$infoservice$NewCascadeIDEntry).getNumberOfEntries() * 2 < Database.getInstance((CascadePopupMenu.class$anon$infoservice$MixCascade == null) ? (CascadePopupMenu.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : CascadePopupMenu.class$anon$infoservice$MixCascade).getNumberOfEntries() && Database.getInstance((CascadePopupMenu.class$anon$infoservice$NewCascadeIDEntry == null) ? (CascadePopupMenu.class$anon$infoservice$NewCascadeIDEntry = class$("anon.infoservice.NewCascadeIDEntry")) : CascadePopupMenu.class$anon$infoservice$NewCascadeIDEntry).getEntryById(mixCascade.getMixIDsAsString()) != null;
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
        MSG_EDIT_FILTER = ((CascadePopupMenu.class$jap$JAPConfAnon == null) ? (CascadePopupMenu.class$jap$JAPConfAnon = class$("jap.JAPConfAnon")) : CascadePopupMenu.class$jap$JAPConfAnon).getName() + "_editFilter";
    }
    
    private class CascadeItemListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == CascadePopupMenu.this.m_editFilter) {
                JAPController.getInstance().showConfigDialog("ANON_TAB", Boolean.TRUE);
                CascadePopupMenu.this.setVisible(false);
            }
            else {
                final MixCascade currentMixCascade = CascadePopupMenu.this.m_menuItems.get(actionEvent.getSource());
                if (currentMixCascade != null) {
                    TrustModel.setCurrentTrustModel(CascadePopupMenu.this.m_trustModel);
                    JAPController.getInstance().setCurrentMixCascade(currentMixCascade);
                    CascadePopupMenu.this.setVisible(false);
                }
            }
        }
    }
}
