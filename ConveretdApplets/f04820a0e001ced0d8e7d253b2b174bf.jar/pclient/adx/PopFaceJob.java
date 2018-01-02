// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.Image;
import javax.swing.JButton;

public class PopFaceJob implements Runnable
{
    private PopFace parentPanel;
    private JButton iconButton;
    private Image iconImage;
    
    public PopFaceJob(final PopFace parentPanel, final JButton iconButton, final Image iconImage) {
        this.parentPanel = parentPanel;
        this.iconButton = iconButton;
        this.iconImage = iconImage;
    }
    
    public void run() {
        this.parentPanel.changeButton(this.iconButton, this.iconImage);
    }
}
