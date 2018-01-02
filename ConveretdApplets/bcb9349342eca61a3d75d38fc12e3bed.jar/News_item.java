import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class News_item
{
    private static final int MAX_TXTS = 10;
    private int ancho;
    private int alto;
    private int x_tit1;
    private int x_tit2;
    private int y_tit;
    private int x_txt;
    private int y_txt;
    private float x_tit1_var;
    private float x_tit1_step;
    private String tit1;
    private String tit2;
    private String txt;
    private String[] txts;
    private int txts_count;
    private int txts_height;
    private MiColor fondo_tit;
    private MiColor texto_tit;
    private MiColor texto_tit_var;
    private MiColor fondo_txt;
    private MiColor texto_txt;
    private MiColor texto_txt_var;
    private MiColor fade_tit;
    private MiColor fade_txt;
    private Font fuente_tit;
    private Font fuente_txt;
    private int max_iter_fade;
    private int max_iter_still;
    private int iter;
    private boolean mouse_in;
    private int vibracion;
    private Random rnd;
    private byte modo;
    
    public News_item(final int ancho, final int alto, final String tit1, final String tit2, final String s, final Color color, final Color color2, final Font fuente_tit, final Color color3, final Color color4, final Font fuente_txt, final int vibracion, final int max_iter_fade, final int max_iter_still, final Graphics graphics) {
        this.txts = new String[10];
        this.txts_count = 0;
        this.max_iter_fade = 25;
        this.max_iter_still = 100;
        this.tit1 = tit1;
        this.tit2 = tit2;
        this.ancho = ancho;
        this.alto = alto;
        this.vibracion = vibracion;
        this.max_iter_fade = max_iter_fade;
        this.max_iter_still = max_iter_still;
        final FontMetrics fontMetrics = graphics.getFontMetrics(fuente_tit);
        this.y_tit = fontMetrics.getHeight() + 5;
        this.x_tit1 = ancho - fontMetrics.stringWidth(tit1 + " " + tit2) - 5;
        this.x_tit1_step = (5 - this.x_tit1) / this.max_iter_fade;
        this.x_tit2 = ancho - fontMetrics.stringWidth(tit2) - 5;
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(fuente_txt);
        this.x_txt = 5;
        this.y_txt = this.y_tit + 5 + fontMetrics2.getHeight();
        this.wrapString(s, fontMetrics2, ancho - 10);
        this.txts_height = fontMetrics2.getHeight();
        this.texto_tit = new MiColor(color);
        this.fondo_tit = new MiColor(color2);
        (this.fade_tit = new MiColor()).calcula_fade(this.fondo_tit, this.texto_tit, this.max_iter_fade);
        this.texto_txt = new MiColor(color3);
        this.fondo_txt = new MiColor(color4);
        (this.fade_txt = new MiColor()).calcula_fade(this.fondo_txt, this.texto_txt, this.max_iter_fade);
        this.fuente_tit = fuente_tit;
        this.fuente_txt = fuente_txt;
        this.mouse_in = false;
        this.modo = 0;
        this.rnd = new Random();
        this.texto_tit_var = new MiColor(color2);
        this.texto_txt_var = new MiColor(color4);
        this.x_tit1_var = this.x_tit1;
        System.out.println("X: " + this.x_tit1);
        System.out.println("S: " + this.x_tit1_step);
        System.out.println("V: " + this.x_tit1_var);
    }
    
    public boolean dibujar(final Graphics graphics) {
        boolean b = false;
        graphics.setColor(this.fondo_tit.getColor());
        graphics.fillRect(0, 0, this.ancho, this.y_tit + 5);
        graphics.setColor(this.texto_tit_var.getColor());
        graphics.setFont(this.fuente_tit);
        graphics.drawString(this.tit1, (int)this.x_tit1_var, this.y_tit - 2);
        graphics.drawString(this.tit2, this.x_tit2 + (int)(this.vibracion * this.rnd.nextFloat()) - this.vibracion / 2, this.y_tit - 2 + (int)(this.vibracion * this.rnd.nextFloat()) - this.vibracion / 2);
        graphics.setColor(this.fondo_txt.getColor());
        graphics.fillRect(0, this.y_tit + 5, this.ancho, this.alto - this.y_tit - 5);
        graphics.setColor(this.texto_txt_var.getColor());
        graphics.setFont(this.fuente_txt);
        for (int i = 0; i <= this.txts_count; ++i) {
            graphics.drawString(this.txts[i], this.x_txt, this.y_txt + this.txts_height * i);
        }
        if (this.mouse_in) {
            graphics.setColor(new Color(255, 255, 255));
            graphics.drawRect(0, 0, this.ancho - 1, this.alto - 1);
        }
        switch (this.modo) {
            case 0: {
                this.texto_tit_var.sumar(this.fade_tit);
                this.texto_txt_var.sumar(this.fade_txt);
                this.x_tit1_var += this.x_tit1_step;
                ++this.iter;
                if (this.iter >= this.max_iter_fade) {
                    this.iter = 0;
                    this.modo = 1;
                }
                b = true;
                break;
            }
            case 1: {
                ++this.iter;
                if (this.iter >= this.max_iter_still) {
                    if (this.mouse_in) {
                        this.iter = this.max_iter_still;
                    }
                    else {
                        this.iter = 0;
                        this.modo = 2;
                    }
                }
                b = true;
                break;
            }
            case 2: {
                this.texto_tit_var.restar(this.fade_tit);
                this.texto_txt_var.restar(this.fade_txt);
                ++this.iter;
                b = true;
                if (this.iter >= this.max_iter_fade) {
                    this.iter = 0;
                    this.modo = 0;
                    this.x_tit1_var = this.x_tit1;
                    b = false;
                    break;
                }
                break;
            }
        }
        return b;
    }
    
    public void set_mouse_in(final boolean mouse_in) {
        this.mouse_in = mouse_in;
    }
    
    public void force_next() {
        this.modo = 2;
        this.iter = this.max_iter_fade;
        this.mouse_in = false;
        this.texto_tit_var.setColor(this.fondo_tit);
        this.texto_txt_var.setColor(this.fondo_txt);
        this.x_tit1_var = this.x_tit1;
    }
    
    private void wrapString(final String s, final FontMetrics fontMetrics, final int n) {
        String nextToken = "";
        String string = "";
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        int n2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            if (n2 == 0) {
                nextToken = stringTokenizer.nextToken();
            }
            if (fontMetrics.stringWidth(string) + fontMetrics.stringWidth(nextToken) >= n && stringTokenizer.countTokens() == 0) {
                this.txts[this.txts_count] = string;
                this.txts[++this.txts_count] = nextToken;
                return;
            }
            if (fontMetrics.stringWidth(string) + fontMetrics.stringWidth(nextToken) >= n) {
                n2 = 1;
                this.txts[this.txts_count++] = string;
                string = "";
            }
            else {
                if (stringTokenizer.countTokens() == 0) {
                    this.txts[this.txts_count] = string + nextToken;
                    return;
                }
                n2 = 0;
                string = string + nextToken + " ";
            }
        }
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
        
        public void restar(final MiColor miColor) {
            this.r -= miColor.r;
            this.g -= miColor.g;
            this.b -= miColor.b;
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
