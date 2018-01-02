// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JDialog;

public class CompoundFormConfigFactory
{
    public static JDialog getConfigDialog(final BasicCompoundForm basicCompoundForm) {
        final JDialog dialog = new JDialog((Frame)basicCompoundForm.getTopLevelAncestor(), "");
        buildContent(dialog, basicCompoundForm);
        return dialog;
    }
    
    private static void buildContent(final JDialog dialog, final BasicCompoundForm basicCompoundForm) {
        final Container contentPane = dialog.getContentPane();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        contentPane.setLayout(layout);
        final ConfigPanel configPanel = new ConfigPanel(basicCompoundForm.getModel());
        gridBagConstraints.insets = new Insets(11, 11, 0, 11);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = 10;
        contentPane.add(configPanel, gridBagConstraints);
        gridBagConstraints.insets = new Insets(17, 12, 11, 11);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 13;
        contentPane.add(buildButtonPane(dialog, configPanel), gridBagConstraints);
        dialog.pack();
        dialog.setResizable(false);
    }
    
    private static JPanel buildButtonPane(final JDialog dialog, final ConfigPanel configPanel) {
        final JPanel panel = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        panel.setLayout(layout);
        final JButton defaultButton = new JButton("Close");
        defaultButton.setMnemonic(67);
        defaultButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                dialog.setVisible(false);
            }
        });
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.anchor = 10;
        panel.add(defaultButton, gridBagConstraints);
        dialog.getRootPane().setDefaultButton(defaultButton);
        return panel;
    }
}
