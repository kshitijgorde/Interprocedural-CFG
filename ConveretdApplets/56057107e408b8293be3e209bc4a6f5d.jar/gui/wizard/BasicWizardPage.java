// 
// Decompiled by Procyon v0.5.30
// 

package gui.wizard;

import gui.dialog.JAPDialog;
import javax.swing.JComponent;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BasicWizardPage extends JPanel implements WizardPage
{
    private String m_strTitle;
    private JLabel m_labelTitle;
    private ImageIcon m_Icon;
    private JLabel m_labelIcon;
    protected JPanel m_panelComponents;
    private String message;
    
    public BasicWizardPage() {
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.m_labelTitle = new JLabel();
        this.m_labelIcon = new JLabel();
        this.m_panelComponents = new JPanel();
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.m_labelIcon, gridBagConstraints);
        this.add(this.m_labelIcon);
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(this.m_labelTitle, gridBagConstraints);
        this.add(this.m_labelTitle);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 10, 10);
        layout.setConstraints(this.m_panelComponents, gridBagConstraints);
        this.add(this.m_panelComponents);
    }
    
    public void setPageTitle(final String s) {
        this.m_strTitle = s;
        this.m_labelTitle.setText(s);
    }
    
    public void deactivated(final WizardHost wizardHost) {
    }
    
    public void setIcon(final ImageIcon imageIcon) {
        this.m_Icon = imageIcon;
        this.m_labelIcon.setIcon(imageIcon);
    }
    
    public void activated(final WizardHost wizardHost) {
    }
    
    public JComponent getPageComponent() {
        return this;
    }
    
    public boolean checkPage() {
        return false;
    }
    
    public void showInformationDialog(final String s) {
        JAPDialog.showMessageDialog(this, s);
    }
    
    public ImageIcon getIcon() {
        return this.m_Icon;
    }
}
