// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import java.awt.Image;
import javax.swing.JToggleButton;

public class MyJToggleButton extends JToggleButton
{
    private Image backGround;
    private Border lightGray;
    
    public MyJToggleButton(final Image backGround, final String s, final ImageIcon imageIcon) {
        super(s, imageIcon);
        this.backGround = null;
        this.lightGray = BorderFactory.createLineBorder(Color.lightGray);
        this.backGround = backGround;
        this.setOpaque(true);
        this.setFont(new Font("Times New Roman", 1, 12));
        this.setBorder(this.lightGray);
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.setOpaque(false);
        if (this.backGround != null) {
            graphics.drawImage(this.backGround, 0, 0, this.getSize().width, this.getSize().height, null);
        }
    }
    
    @Override
    public void setBackground(final Color background) {
        super.setBackground(background);
    }
}
