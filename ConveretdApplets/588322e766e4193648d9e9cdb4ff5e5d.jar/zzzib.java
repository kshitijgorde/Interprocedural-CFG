import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class zzzib extends Canvas
{
    public zzzib() {
        this.setBackground(Color.yellow);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.getFont());
        final String s = "Anagrammes";
        final String s2 = "v1.0 Jeux-Mots.com";
        final String s3 = "Programm\u00e9 par Martin Mamo";
        final String s4 = "          Instructions :         ";
        final String s5 = "cliquez sur RECHERCHER";
        final String s6 = "   Inscrivez les lettres dans la case et    ";
        final String s7 = "   STOP arrete la recherche   ";
        final String s8 = "Les 100 premiers r\u00e9sultats apparaitrons dans le cadre";
        final String s9 = "PLUS D'ANAGRAMMES permet d'en afficher 100 de plus";
        final int stringWidth = fontMetrics.stringWidth(s);
        final int stringWidth2 = fontMetrics.stringWidth(s2);
        final int stringWidth3 = fontMetrics.stringWidth(s3);
        final int stringWidth4 = fontMetrics.stringWidth(s4);
        final int stringWidth5 = fontMetrics.stringWidth(s5);
        final int stringWidth6 = fontMetrics.stringWidth(s6);
        final int stringWidth7 = fontMetrics.stringWidth(s7);
        final int stringWidth8 = fontMetrics.stringWidth(s8);
        final int stringWidth9 = fontMetrics.stringWidth(s9);
        final int height = fontMetrics.getHeight();
        final int n = height * 2;
        graphics.drawString(s, (width - stringWidth) / 2, n);
        final int n2 = n + height;
        graphics.drawString(s2, (width - stringWidth2) / 2, n2);
        final int n3 = n2 + height;
        graphics.drawString(s3, (width - stringWidth3) / 2, n3);
        final int n4 = n3 + height;
        graphics.drawString(s4, (width - stringWidth4) / 2, n4);
        final int n5 = n4 + height;
        graphics.drawString(s6, (width - stringWidth6) / 2, n5);
        final int n6 = n5 + height;
        graphics.drawString(s5, (width - stringWidth5) / 2, n6);
        final int n7 = n6 + height * 3;
        graphics.drawString(s7, (width - stringWidth7) / 2, n7);
        final int n8 = n7 + height;
        graphics.drawString(s8, (width - stringWidth8) / 2, n8);
        graphics.drawString(s9, (width - stringWidth9) / 2, n8 + height);
    }
}
