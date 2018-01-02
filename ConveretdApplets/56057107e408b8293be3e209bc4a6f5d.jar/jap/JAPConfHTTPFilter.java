// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.util.Observable;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.border.Border;
import anon.util.JAPMessages;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.util.Observer;

final class JAPConfHTTPFilter extends AbstractJAPConfModule implements Observer, ActionListener
{
    private TitledBorder m_borderCert;
    private JRadioButton m_btnFilterOn;
    private JRadioButton m_btnFilterOff;
    private JCheckBox m_boxUserAgent;
    private JCheckBox m_boxLanguage;
    private JCheckBox m_boxEncoding;
    private JCheckBox m_boxFileTypes;
    
    public JAPConfHTTPFilter() {
        super(null);
    }
    
    public void recreateRootPanel() {
        final JPanel rootPanel = this.getRootPanel();
        rootPanel.removeAll();
        rootPanel.setBorder(this.m_borderCert = new TitledBorder(JAPMessages.getString("confHTTPFilterTab")));
        rootPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 18;
        (this.m_btnFilterOn = new JRadioButton("jap.JAPConfHTTPFilter_filterOn")).setSelected(true);
        this.m_btnFilterOn.addActionListener(this);
        rootPanel.add(this.m_btnFilterOn, gridBagConstraints);
        gridBagConstraints.insets = new Insets(0, 20, 0, 0);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        ++gridBagConstraints2.gridy;
        (this.m_boxUserAgent = new JCheckBox("jap.JAPConfHTTPFilter_userAgent")).setSelected(true);
        rootPanel.add(this.m_boxUserAgent, gridBagConstraints);
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        (this.m_boxLanguage = new JCheckBox("jap.JAPConfHTTPFilter_language")).setSelected(true);
        rootPanel.add(this.m_boxLanguage, gridBagConstraints);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        ++gridBagConstraints4.gridy;
        (this.m_boxEncoding = new JCheckBox("jap.JAPConfHTTPFilter_encoding")).setSelected(true);
        rootPanel.add(this.m_boxEncoding, gridBagConstraints);
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        ++gridBagConstraints5.gridy;
        (this.m_boxFileTypes = new JCheckBox("jap.JAPConfHTTPFilter_fileTypes")).setSelected(true);
        rootPanel.add(this.m_boxFileTypes, gridBagConstraints);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
        ++gridBagConstraints6.gridy;
        gridBagConstraints.weighty = 1.0;
        (this.m_btnFilterOff = new JRadioButton("jap.JAPConfHTTPFilter_filterOff")).addActionListener(this);
        rootPanel.add(this.m_btnFilterOff, gridBagConstraints);
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.m_btnFilterOn);
        buttonGroup.add(this.m_btnFilterOff);
    }
    
    public String getTabTitle() {
        return JAPMessages.getString("confHTTPFilterTab");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.m_btnFilterOff) {
            this.m_boxUserAgent.setEnabled(false);
            this.m_boxLanguage.setEnabled(false);
            this.m_boxEncoding.setEnabled(false);
            this.m_boxFileTypes.setEnabled(false);
        }
        else if (actionEvent.getSource() == this.m_btnFilterOn) {
            this.m_boxUserAgent.setEnabled(true);
            this.m_boxLanguage.setEnabled(true);
            this.m_boxEncoding.setEnabled(true);
            this.m_boxFileTypes.setEnabled(true);
        }
    }
    
    public void update(final Observable observable, final Object o) {
    }
    
    protected void onUpdateValues() {
    }
    
    protected boolean onOkPressed() {
        return true;
    }
    
    protected void onResetToDefaultsPressed() {
    }
    
    public String getHelpContext() {
        return "httpFilter";
    }
}
