// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import javax.swing.BorderFactory;
import java.awt.Color;
import java.util.Date;
import anon.infoservice.ServiceOperator;
import anon.util.CountryMapper;
import anon.infoservice.ServiceLocation;
import java.util.Enumeration;
import java.awt.event.ItemListener;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.AbstractButton;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.Icon;
import gui.GUIUtils;
import javax.swing.border.Border;
import anon.util.JAPMessages;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.util.Vector;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public final class ServerListPanel extends JPanel implements ActionListener
{
    private static final String MSG_MIX_CLICK;
    private static final String MSG_MIX_COUNTRY;
    private static final String MSG_OPERATOR_COUNTRY;
    private static final String MSG_MIX_AND_OPERATOR_COUNTRY;
    private boolean m_bEnabled;
    private ButtonGroup m_bgMixe;
    private int m_selectedIndex;
    private Vector m_itemListeners;
    private JRadioButton[] m_mixButtons;
    private JLabel[] m_mixFlags;
    private JLabel[] m_operatorFlags;
    static /* synthetic */ Class class$jap$ServerListPanel;
    
    public ServerListPanel(int n, final boolean bEnabled, final int n2) {
        int n3 = 0;
        if (n < 1) {
            n = 1;
        }
        if (n2 > 0 && n2 < n) {
            n3 = n2;
        }
        this.m_mixButtons = new JRadioButton[n];
        this.m_mixFlags = new JLabel[n];
        this.m_operatorFlags = new JLabel[n];
        this.m_itemListeners = new Vector();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.m_bgMixe = new ButtonGroup();
        this.m_selectedIndex = 0;
        this.setLayout(layout);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        for (int i = 0; i < n; ++i) {
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridx = i * 2;
            gridBagConstraints.gridheight = 3;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            this.m_mixButtons[i] = new JRadioButton();
            if (bEnabled) {
                this.m_mixButtons[i].setToolTipText(JAPMessages.getString("serverPanelAdditional"));
            }
            this.m_mixButtons[i].addActionListener(this);
            this.m_mixButtons[i].setBorder(null);
            this.m_mixButtons[i].setFocusPainted(false);
            this.m_mixButtons[i].setRolloverEnabled(true);
            this.m_mixButtons[i].setIcon(GUIUtils.loadImageIcon("server.gif", true));
            this.m_mixButtons[i].setRolloverIcon(GUIUtils.loadImageIcon("server_blau.gif", true));
            this.m_mixButtons[i].setSelectedIcon(GUIUtils.loadImageIcon("server_rot.gif", true));
            this.m_mixButtons[i].setCursor(Cursor.getPredefinedCursor(12));
            if (i == n3) {
                this.m_selectedIndex = i;
                this.m_mixButtons[i].setSelected(true);
            }
            this.add(this.m_mixButtons[i], gridBagConstraints);
            this.m_bgMixe.add(this.m_mixButtons[i]);
            this.m_bEnabled = bEnabled;
            this.m_mixButtons[i].setEnabled(this.m_bEnabled);
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.gridx = i * 2 + 1;
            gridBagConstraints.weightx = 0.0;
            (this.m_mixFlags[i] = new JLabel(" ")).setFont(new Font("", 0, (int)(14.0 * (1.0 + JAPModel.getInstance().getFontSize() * 0.1))));
            this.add(this.m_mixFlags[i], gridBagConstraints);
            gridBagConstraints.gridx = i * 2 + 1;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.gridy = 1;
            if (n == 1) {
                gridBagConstraints.weightx = 0.5;
            }
            else {
                gridBagConstraints.weightx = 0.5 / (n - 1);
            }
            this.add(new JSeparator(), gridBagConstraints);
            gridBagConstraints.gridy = 2;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.gridx = i * 2 + 1;
            gridBagConstraints.weightx = 0.0;
            (this.m_operatorFlags[i] = new JLabel("")).setFont(new Font("", 0, (int)(10.0 * (1.0 + JAPModel.getInstance().getFontSize() * 0.1))));
            final JPanel panel = new JPanel(new GridBagLayout());
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.gridx = 0;
            gridBagConstraints2.gridy = 0;
            gridBagConstraints2.fill = 0;
            gridBagConstraints2.anchor = 17;
            panel.add(this.m_operatorFlags[i], gridBagConstraints2);
            gridBagConstraints2.weightx = 1.0;
            gridBagConstraints2.gridx = 1;
            gridBagConstraints2.fill = 2;
            panel.add(new JLabel(), gridBagConstraints2);
            this.add(panel, gridBagConstraints);
        }
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridx;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 13;
        this.add(new JLabel(GUIUtils.loadImageIcon("cloud.png", true)), gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridx;
        gridBagConstraints.weightx = 0.7;
        this.add(new JLabel(""), gridBagConstraints);
    }
    
    public boolean areMixButtonsEnabled() {
        return this.m_bEnabled;
    }
    
    public int getNumberOfMixes() {
        return this.m_mixButtons.length;
    }
    
    public synchronized void moveToPrevious() {
        final JRadioButton setSelectedIndex = this.setSelectedIndex(this.m_selectedIndex - 1);
        if (setSelectedIndex != null) {
            this.actionPerformed(new ActionEvent(setSelectedIndex, 1001, null));
        }
    }
    
    public synchronized void moveToNext() {
        final JRadioButton setSelectedIndex = this.setSelectedIndex(this.m_selectedIndex + 1);
        if (setSelectedIndex != null) {
            this.actionPerformed(new ActionEvent(setSelectedIndex, 1001, null));
        }
    }
    
    public synchronized void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        final Enumeration<AbstractButton> elements = this.m_bgMixe.getElements();
        int selectedIndex = 0;
        while (elements.hasMoreElements()) {
            if (source == elements.nextElement()) {
                this.m_selectedIndex = selectedIndex;
                final ItemEvent itemEvent = new ItemEvent((ItemSelectable)source, 701, source, 1);
                final Enumeration<ItemListener> elements2 = (Enumeration<ItemListener>)this.m_itemListeners.elements();
                while (elements2.hasMoreElements()) {
                    elements2.nextElement().itemStateChanged(itemEvent);
                }
                return;
            }
            ++selectedIndex;
        }
    }
    
    public void addItemListener(final ItemListener itemListener) {
        this.m_itemListeners.addElement(itemListener);
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        this.m_itemListeners.removeElement(itemListener);
    }
    
    public synchronized JRadioButton setSelectedIndex(final int n) {
        if (n < 0) {
            return null;
        }
        Enumeration<AbstractButton> elements;
        int selectedIndex;
        for (elements = this.m_bgMixe.getElements(), selectedIndex = 0; selectedIndex < n && elements.hasMoreElements(); ++selectedIndex) {
            elements.nextElement();
        }
        if (!elements.hasMoreElements()) {
            return null;
        }
        this.m_selectedIndex = selectedIndex;
        final JRadioButton radioButton = elements.nextElement();
        radioButton.setSelected(true);
        return radioButton;
    }
    
    private synchronized void updateFlag(final int n, final ServiceLocation serviceLocation) {
        if (serviceLocation != null) {
            try {
                final CountryMapper countryMapper = new CountryMapper(serviceLocation.getCountryCode(), JAPMessages.getLocale());
                this.m_mixFlags[n].setIcon(GUIUtils.loadImageIcon("flags/" + countryMapper.getISOCode() + ".png"));
                this.m_mixFlags[n].setToolTipText(JAPMessages.getString(ServerListPanel.MSG_MIX_COUNTRY, countryMapper.toString()));
            }
            catch (IllegalArgumentException ex) {
                this.m_mixFlags[n].setIcon(null);
                this.m_mixFlags[n].setToolTipText(null);
            }
        }
        else {
            this.m_mixFlags[n].setIcon(null);
            this.m_mixFlags[n].setToolTipText(null);
        }
    }
    
    public synchronized void update(final int n, final ServiceOperator serviceOperator, final ServiceLocation serviceLocation) {
        if (serviceOperator != null && serviceOperator.getCountryCode() != null) {
            if (serviceLocation != null && serviceLocation.getCountryCode() != null && !serviceLocation.getCountryCode().equals(serviceOperator.getCountryCode())) {
                this.updateFlag(n, serviceLocation);
                this.updateOperatorFlag(n, serviceOperator, false);
            }
            else {
                this.updateOperatorFlag(n, serviceOperator, true);
            }
            if (serviceOperator.getCertPath().isVerified()) {
                if (!serviceOperator.getCertPath().isValid(new Date())) {
                    this.m_operatorFlags[n].setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
                }
                else if (serviceOperator.getCertPath().countVerifiedAndValidPaths() > 2) {
                    this.m_operatorFlags[n].setBorder(BorderFactory.createLineBorder(Color.green, 2));
                }
                else if (serviceOperator.getCertPath().countVerifiedAndValidPaths() > 1) {
                    this.m_operatorFlags[n].setBorder(BorderFactory.createLineBorder(new Color(100, 215, 255), 2));
                }
                else {
                    this.m_operatorFlags[n].setBorder(BorderFactory.createEmptyBorder());
                }
            }
            else {
                this.m_operatorFlags[n].setBorder(BorderFactory.createLineBorder(Color.red, 2));
            }
        }
        else {
            this.updateOperatorFlag(n, serviceOperator, false);
            this.updateFlag(n, serviceLocation);
        }
    }
    
    private synchronized void updateOperatorFlag(final int n, final ServiceOperator serviceOperator, final boolean b) {
        if (serviceOperator != null && serviceOperator.getCountryCode() != null) {
            final CountryMapper countryMapper = new CountryMapper(serviceOperator.getCountryCode(), JAPMessages.getLocale());
            this.m_operatorFlags[n].setIcon(GUIUtils.loadImageIcon("flags/" + countryMapper.getISOCode() + ".png"));
            if (b) {
                this.m_operatorFlags[n].setToolTipText(JAPMessages.getString(ServerListPanel.MSG_MIX_AND_OPERATOR_COUNTRY, countryMapper.toString()));
                this.updateFlag(n, null);
            }
            else {
                this.m_operatorFlags[n].setToolTipText(JAPMessages.getString(ServerListPanel.MSG_OPERATOR_COUNTRY, countryMapper.toString()));
            }
        }
        else {
            this.m_operatorFlags[n].setIcon(null);
            this.m_operatorFlags[n].setToolTipText(null);
        }
    }
    
    public int getSelectedIndex() {
        return this.m_selectedIndex;
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
        MSG_MIX_CLICK = ((ServerListPanel.class$jap$ServerListPanel == null) ? (ServerListPanel.class$jap$ServerListPanel = class$("jap.ServerListPanel")) : ServerListPanel.class$jap$ServerListPanel).getName() + "_mixClick";
        MSG_MIX_COUNTRY = ((ServerListPanel.class$jap$ServerListPanel == null) ? (ServerListPanel.class$jap$ServerListPanel = class$("jap.ServerListPanel")) : ServerListPanel.class$jap$ServerListPanel).getName() + "_mixCountry";
        MSG_OPERATOR_COUNTRY = ((ServerListPanel.class$jap$ServerListPanel == null) ? (ServerListPanel.class$jap$ServerListPanel = class$("jap.ServerListPanel")) : ServerListPanel.class$jap$ServerListPanel).getName() + "_operatorCountry";
        MSG_MIX_AND_OPERATOR_COUNTRY = ((ServerListPanel.class$jap$ServerListPanel == null) ? (ServerListPanel.class$jap$ServerListPanel = class$("jap.ServerListPanel")) : ServerListPanel.class$jap$ServerListPanel).getName() + "_mixAndOperatorCountry";
    }
}
