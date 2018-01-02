// 
// Decompiled by Procyon v0.5.30
// 

package testvm2;

import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BannerPanel extends JPanel
{
    private BannerTextPanel textPanel;
    private JLabel imgLabel;
    
    public BannerPanel(final Icon imgIcon, final String title, final String subtitle, final int size) {
        this.setLayout(new FlowLayout(0));
        (this.imgLabel = new JLabel(imgIcon)).setBackground(Color.decode("#FFFFFF"));
        this.add(this.imgLabel);
        (this.textPanel = new BannerTextPanel(title, subtitle, size)).setBackground(Color.decode("#FFFFFF"));
        this.add(this.textPanel);
    }
    
    public void updateSubtitle(final String s) {
        this.textPanel.setSubtitle(s);
        this.textPanel.repaint();
    }
}
