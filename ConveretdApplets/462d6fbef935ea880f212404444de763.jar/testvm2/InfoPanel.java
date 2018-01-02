// 
// Decompiled by Procyon v0.5.30
// 

package testvm2;

import java.awt.Component;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class InfoPanel extends JPanel
{
    public InfoPanel(final String[] sysPropertiesStrings) {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(5, 0, 0, 0));
        final InfoBorderPanel borderPanel = new InfoBorderPanel(sysPropertiesStrings);
        borderPanel.setBackground(Color.decode("#FFFFFF"));
        this.add(borderPanel, "Center");
    }
}
