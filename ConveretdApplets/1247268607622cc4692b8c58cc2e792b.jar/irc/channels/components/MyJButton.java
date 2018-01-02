// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JButton;

public class MyJButton extends JButton
{
    private Image backGround;
    private int pos;
    
    public MyJButton(final Image backGround, final String s, final ImageIcon imageIcon, final int pos) {
        super(s, imageIcon);
        this.backGround = null;
        this.pos = 0;
        this.pos = pos;
        this.backGround = backGround;
        this.setFont(new Font("Times New Roman", 1, 12));
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (this.backGround != null) {
            if (this.pos == 1) {
                graphics.drawImage(this.backGround, 0, -20, this.getSize().width, this.getSize().height, null);
            }
            else {
                graphics.drawImage(this.backGround, 0, 0, this.getSize().width, this.getSize().height, null);
            }
        }
    }
}
