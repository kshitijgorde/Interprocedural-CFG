import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

class graphic_app
{
    static void ajouter(final Container container, final Component component, final int gridx, final int gridy, final int gridwidth, final int gridheight, final int fill, final int anchor, final int ipadx, final int ipady, final int n, final int n2, final int n3, final int n4, final double weightx, final double weighty) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.fill = fill;
        gridBagConstraints.anchor = anchor;
        gridBagConstraints.ipadx = ipadx;
        gridBagConstraints.ipady = ipady;
        if (n + n2 + n3 + n4 > 0) {
            gridBagConstraints.insets = new Insets(n, n2, n3, n4);
        }
        gridBagConstraints.weightx = weightx;
        gridBagConstraints.weighty = weighty;
        ((GridBagLayout)container.getLayout()).setConstraints(component, gridBagConstraints);
        container.add(component);
    }
    
    static void ajouter(final Container container, final Component component, final int n, final int n2, final int n3, final int n4, final double n5, final double n6) {
        ajouter(container, component, n, n2, n3, n4, 1, 18, 0, 0, 0, 0, 0, 0, n5, n6);
    }
}
