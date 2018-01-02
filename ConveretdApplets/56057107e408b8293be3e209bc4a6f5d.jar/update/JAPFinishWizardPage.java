// 
// Decompiled by Procyon v0.5.30
// 

package update;

import java.awt.Component;
import gui.JAPMultilineLabel;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import anon.util.JAPMessages;
import gui.GUIUtils;
import javax.swing.JLabel;
import gui.wizard.BasicWizardPage;

public class JAPFinishWizardPage extends BasicWizardPage
{
    public JLabel m_labelBackupOfJapJar;
    
    public JAPFinishWizardPage() {
        this.setIcon(GUIUtils.loadImageIcon("install.gif", false));
        this.setPageTitle(JAPMessages.getString("updateTitel_Update-WizardFertig"));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        super.m_panelComponents.setLayout(layout);
        final JAPMultilineLabel japMultilineLabel = new JAPMultilineLabel(JAPMessages.getString("updateFinishMessage"));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 18;
        layout.setConstraints(japMultilineLabel, gridBagConstraints);
        super.m_panelComponents.add(japMultilineLabel, gridBagConstraints);
        this.m_labelBackupOfJapJar = new JLabel();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.fill = 2;
        layout.setConstraints(this.m_labelBackupOfJapJar, gridBagConstraints);
        super.m_panelComponents.add(this.m_labelBackupOfJapJar);
        final JLabel label = new JLabel();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 1;
        layout.setConstraints(label, gridBagConstraints);
        super.m_panelComponents.add(label);
    }
}
