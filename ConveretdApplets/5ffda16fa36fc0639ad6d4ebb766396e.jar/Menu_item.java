import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class Menu_item
{
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int x_txt;
    private int y_txt;
    private String txt;
    private String url;
    private MiColor fondo;
    private MiColor texto;
    private MiColor fondo1;
    private MiColor texto1;
    private MiColor fondo2;
    private MiColor texto2;
    private MiColor texto_fcolor;
    private MiColor fondo_fcolor;
    private Font fuente;
    private int max_iter;
    private int iter;
    private boolean dibujar;
    private boolean mouse_in;
    private boolean recuadro;
    
    public Menu_item(final int x, final int y, final int ancho, final int alto, final String s, final String url, final Color color, final Color color2, final Color color3, final Color color4, final int max_iter, final String s2, final int n, final Graphics graphics) {
        this.max_iter = 15;
        this.iter = 0;
        this.recuadro = false;
        this.txt = this.spacer(s);
        this.url = url;
        this.max_iter = max_iter;
        this.recuadro = (n == 1);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.x = x;
        this.y = y;
        if (s2.compareTo("L") == 0) {
            this.x_txt = x + 5;
        }
        else if (s2.compareTo("R") == 0) {
            this.x_txt = x + ancho - fontMetrics.stringWidth(this.txt) - 5;
        }
        else {
            this.x_txt = x + 5 + (ancho - fontMetrics.stringWidth(this.txt) - 10) / 2;
        }
        this.y_txt = y + alto / 2 + fontMetrics.getMaxAscent() / 2;
        this.ancho = ancho;
        this.alto = alto;
        this.fondo1 = new MiColor(color);
        this.texto1 = new MiColor(color2);
        this.fondo2 = new MiColor(color3);
        this.texto2 = new MiColor(color4);
        this.texto = new MiColor(this.texto1);
        this.fondo = new MiColor(this.fondo1);
        this.texto_fcolor = new MiColor();
        this.fondo_fcolor = new MiColor();
        this.texto_fcolor.calcula_fade(this.texto2, this.texto1, this.max_iter);
        this.fondo_fcolor.calcula_fade(this.fondo2, this.fondo1, this.max_iter);
        this.dibujar = true;
        this.mouse_in = false;
    }
    
    public void dibujar(final Graphics graphics) {
        if (this.dibujar) {
            graphics.setColor(this.fondo.getColor());
            graphics.fillRect(this.x, this.y, this.ancho, this.alto);
            graphics.setColor(this.texto.getColor());
            if (this.recuadro) {
                graphics.drawRect(this.x, this.y, this.ancho - 1, this.alto - 1);
            }
            graphics.drawString(this.txt, this.x_txt, this.y_txt);
            if (this.mouse_in) {
                this.dibujar = false;
            }
            else if (this.iter <= 0) {
                this.dibujar = false;
            }
            else {
                --this.iter;
                this.texto.sumar(this.texto_fcolor);
                this.fondo.sumar(this.fondo_fcolor);
            }
        }
    }
    
    public void mouse_position(final int n, final int n2, final boolean b) {
        if (!b && n >= this.x && n < this.x + this.ancho && n2 >= this.y && n2 < this.y + this.alto) {
            if (!this.mouse_in) {
                this.mouse_in = true;
                this.dibujar = true;
                this.iter = this.max_iter;
                this.texto.setColor(this.texto2);
                this.fondo.setColor(this.fondo2);
            }
        }
        else if (this.mouse_in) {
            this.mouse_in = false;
            this.dibujar = true;
        }
    }
    
    public String mouse_click(final int n, final int n2) {
        if (this.mouse_in) {
            return this.url;
        }
        return "";
    }
    
    private String spacer(final String s) {
        String s2 = "";
        for (int i = 0; i < s.length(); ++i) {
            s2 += s.substring(i, i + 1);
            if (i < s.length() - 1) {
                s2 += " ";
            }
        }
        return s2;
    }
    
    private class MiColor
    {
        public float r;
        public float g;
        public float b;
        
        public MiColor() {
            this.r = 0.0f;
            this.g = 0.0f;
            this.b = 0.0f;
        }
        
        public MiColor(final float r, final float g, final float b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
        
        public MiColor(final Color color) {
            this.r = color.getRed();
            this.g = color.getGreen();
            this.b = color.getBlue();
        }
        
        public MiColor(final MiColor miColor) {
            this.r = miColor.r;
            this.g = miColor.g;
            this.b = miColor.b;
        }
        
        public void calcula_fade(final MiColor miColor, final MiColor miColor2, final int n) {
            this.r = (miColor2.r - miColor.r) / n;
            this.g = (miColor2.g - miColor.g) / n;
            this.b = (miColor2.b - miColor.b) / n;
        }
        
        public void sumar(final MiColor miColor) {
            this.r += miColor.r;
            this.g += miColor.g;
            this.b += miColor.b;
        }
        
        public Color getColor() {
            return new Color((int)this.r, (int)this.g, (int)this.b);
        }
        
        public void setColor(final MiColor miColor) {
            this.r = miColor.r;
            this.g = miColor.g;
            this.b = miColor.b;
        }
    }
}
