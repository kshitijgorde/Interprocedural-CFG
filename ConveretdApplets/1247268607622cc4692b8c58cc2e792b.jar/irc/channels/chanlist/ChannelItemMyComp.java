// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.chanlist;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JPanel;

public class ChannelItemMyComp extends JPanel
{
    private String nomSalon;
    private String nbSalon;
    private boolean ismyregion;
    private FontMetrics metrics;
    private int pixelSizeNom;
    private Font f;
    private int lenghtnom;
    private String nom;
    
    public ChannelItemMyComp() {
        this.nbSalon = null;
        this.ismyregion = false;
        this.lenghtnom = 140;
        this.f = new Font("Times New Roman", 0, 14);
    }
    
    public String getNbSalon() {
        return this.nbSalon;
    }
    
    public String getNomSalon() {
        return this.nomSalon;
    }
    
    public boolean isIsmyregion() {
        return this.ismyregion;
    }
    
    @Override
    public void paint(final Graphics graphics) {
        this.setFont(this.f);
        graphics.setFont(this.f);
        this.metrics = graphics.getFontMetrics();
        this.pixelSizeNom = this.metrics.stringWidth(this.nomSalon);
        if (!this.nomSalon.startsWith("#")) {
            int n = (this.getWidth() - this.pixelSizeNom) / 2;
            if (n < 0) {
                n = 0;
            }
            graphics.setColor(Color.white);
            graphics.drawString(this.nomSalon, n, 15);
        }
        else {
            if (this.nomSalon.equalsIgnoreCase("#radio") || this.isIsmyregion()) {
                graphics.setColor(Color.red);
            }
            else {
                graphics.setColor(Color.BLUE);
            }
            if (this.getWidth() < 170) {
                this.lenghtnom = this.getWidth() - 35;
            }
            if (this.pixelSizeNom > this.lenghtnom) {
                this.nom = "";
                for (int n2 = 0; this.metrics.stringWidth(this.nom) < this.lenghtnom && n2 < this.nomSalon.length(); ++n2) {
                    this.nom += this.nomSalon.charAt(n2);
                }
                this.nomSalon = this.nom + "..";
            }
            graphics.drawString(this.nomSalon.substring(1), 10, 15);
            if (this.nbSalon != null && !this.nbSalon.equals("")) {
                graphics.setColor(new Color(170, 0, 170));
                graphics.drawString(this.nbSalon, this.lenghtnom + 20, 15);
            }
        }
    }
    
    public void setIsmyregion(final boolean ismyregion) {
        this.ismyregion = ismyregion;
    }
    
    public void setNbSalon(final String nbSalon) {
        this.nbSalon = nbSalon;
    }
    
    public void setNomSalon(final String nomSalon) {
        this.nomSalon = nomSalon;
    }
}
