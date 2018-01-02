// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.Image;
import javax.swing.JButton;

public class PopSmileJob implements Runnable
{
    private PopSmile parentPanel;
    private JButton iconButton;
    private Image iconImage;
    
    public PopSmileJob(final PopSmile parentPanel, final JButton iconButton, final Image iconImage) {
        this.parentPanel = parentPanel;
        this.iconButton = iconButton;
        this.iconImage = iconImage;
    }
    
    public void run() {
        this.parentPanel.changeButton(this.iconButton, this.iconImage);
    }
}
