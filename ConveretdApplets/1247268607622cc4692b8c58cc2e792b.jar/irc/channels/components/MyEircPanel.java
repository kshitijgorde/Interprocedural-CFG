// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import irc.managers.avatar;
import java.awt.Font;
import java.awt.Color;
import irc.main;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import irc.EIRC;
import java.awt.Image;
import javax.swing.JComponent;

public class MyEircPanel extends JComponent
{
    private Image myb;
    private Image accueilcenter;
    private Image accueilleft;
    private Image accueilright;
    private Image baccueilcenter;
    private Image baccueilleft;
    private Image baccueilright;
    private int width;
    private int height;
    
    public MyEircPanel() {
        this.setOpaque(false);
        this.setBackground(EIRC.mainbg);
    }
    
    public void paintComponent(final Graphics graphics) {
        this.setBackground(EIRC.mainbg);
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        graphics.drawImage(this.myb, 0, 0, this.width, 30, EIRC.mainbg, null);
        graphics.drawImage(this.accueilcenter, 150, 30, this.width - 300, this.height - 30, EIRC.mainbg, null);
        graphics.drawImage(this.accueilleft, 0, 30, 150, this.height - 30, EIRC.mainbg, this);
        graphics.drawImage(this.accueilright, this.width - 150, 30, 150, this.height - 30, EIRC.mainbg, null);
        graphics.drawImage(this.baccueilcenter, 150, this.height - 116, this.width - 300, 116, null);
        graphics.drawImage(this.baccueilleft, 0, this.height - 116, 150, 116, this);
        graphics.drawImage(this.baccueilright, this.width - 150, this.height - 116, 150, 116, null);
        if (main.istest) {
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Times New Roman", 1, 18));
            graphics.drawString(EIRC.memoire, 20, this.height - 205);
            graphics.drawString("CPU inst : " + EIRC.cpuinst + "CPU moy : " + EIRC.cpumoyen, 20, this.height - 185);
        }
    }
    
    public void initimage1() {
        this.myb = avatar.MybackGround;
        this.accueilcenter = main.accueilcenter;
        this.accueilleft = main.accueilleft;
        this.accueilright = main.accueilright;
        this.baccueilcenter = main.baccueilcenter;
        this.baccueilleft = main.baccueilleft;
        this.baccueilright = main.baccueilright;
    }
}
