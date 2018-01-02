// 
// Decompiled by Procyon v0.5.30
// 

package clases;

import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

public class HistogramaBM extends Applet implements MouseMotionListener, MouseListener
{
    int nvalores;
    float[] valores;
    float[] valores2;
    String[] leyendahor;
    String[] leyendaver;
    Color bgcolor;
    Color colorgrafico;
    Color colorleyenda;
    Color colorejes;
    Color colorlineas;
    Color colorbarra1;
    Color colorbarra2;
    Color colorbarra3;
    Color colorbarra4;
    Color colorbarra5;
    Color colorleyu;
    String fontname;
    int fontsize;
    int fontstyle;
    Font font;
    Font fontleyenda;
    Font fontleyenda2;
    FontMetrics fontMetrics;
    int fontHeight;
    int ancho;
    int alto;
    int altografico;
    int anchografico;
    int numlineasy;
    int sepver1;
    int sepver2;
    int sephor1;
    int sephor2;
    float MaximoY;
    float MinimoY;
    int anchobarra;
    int sepmin;
    int nlineasx;
    int margenbarra;
    int decimales;
    String titulo;
    Color colortitulo;
    Color colorborde;
    Font ftitulo;
    int letratitulo;
    FontMetrics fmtitulo;
    Color colorultimo;
    float linea;
    Color colorlinea;
    int poslinea;
    boolean minimocero;
    boolean lineaunion;
    Color colorunion;
    Color colorlin2;
    Color colorpos;
    Color colorneg;
    int poscero;
    Color colornegativo;
    boolean mostrarbarra;
    int altodato;
    boolean mostrarultimo;
    int largolinea;
    boolean mostrarleyultimo;
    float rotado;
    int profundidad;
    boolean relleno;
    boolean mostrardosfilas;
    boolean mostrarcolores;
    boolean leyinvertida;
    Color[] colores;
    boolean segundobarra;
    float mayor;
    int posmayor;
    boolean mostrarinfo;
    int intervalover;
    int[] barrax;
    int[] barray;
    int EnBarra;
    static final Cursor HAND_CURSOR;
    static final Cursor NORM_CURSOR;
    Image offImage;
    Graphics offGraphics;
    boolean mostraralt;
    Color coloralt;
    int PosX;
    int PosY;
    boolean mostrarinfobarra;
    int anchosaliente;
    int numlinver;
    boolean mostrarcopy;
    boolean mostrarlineas;
    String leyenda1;
    String leyenda2;
    String servidor;
    String fichapopup;
    String ticker;
    String leyendainf;
    
    public void init() {
        this.parseArgs();
        this.ancho = this.getSize().width;
        this.alto = this.getSize().height;
        this.offImage = this.createImage(this.getSize().width, this.getSize().height);
        this.offGraphics = this.offImage.getGraphics();
        this.font = new Font(this.fontname, this.fontstyle, this.fontsize);
        this.fontleyenda2 = new Font(this.fontname, 1, this.fontsize);
        if (!this.mostrardosfilas && this.leyinvertida) {
            final AffineTransform faux = this.font.getTransform();
            faux.rotate(this.rotado);
            this.fontleyenda = this.font.deriveFont(faux);
        }
        else {
            this.fontleyenda = this.font;
        }
        this.fontMetrics = this.getFontMetrics(this.font);
        this.fontHeight = this.fontMetrics.getHeight();
        this.ftitulo = new Font(this.fontname, this.fontstyle, this.letratitulo);
        this.fmtitulo = this.getFontMetrics(this.ftitulo);
        this.anchografico = this.ancho - this.sephor1 - this.sephor2;
        this.altografico = this.alto - this.sepver1 - this.sepver2;
        if (this.mostrarultimo) {
            this.altografico -= this.altodato;
        }
        if (this.mostrarultimo) {
            final float dato = this.altografico * (this.MaximoY - this.linea) / (this.MaximoY - this.MinimoY);
            this.poslinea = (int)dato;
        }
        if ((this.anchobarra + this.sepmin) * this.nvalores > this.anchografico) {
            this.anchobarra = (this.anchografico - this.sepmin * this.nvalores) / this.nvalores;
        }
        float faux2 = this.altografico * (this.MaximoY - this.mayor);
        faux2 /= this.MaximoY - this.MinimoY;
        this.posmayor = (int)faux2;
        this.CalcularEjes();
        this.barrax = new int[this.nvalores];
        this.barray = new int[this.nvalores];
        float dato2 = 0.0f;
        final int sep = (this.anchografico - 10) / this.nvalores;
        for (int i = 0; i < this.nvalores; ++i) {
            this.barrax[i] = this.SituarEnX(sep * i + sep / 2);
            dato2 = this.altografico * (this.MaximoY - this.valores[i]) / (this.MaximoY - this.MinimoY);
            this.barray[i] = this.SituarEnY((int)dato2);
        }
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    public void update(final Graphics g) {
        this.offGraphics.setColor(this.bgcolor);
        this.paint(this.offGraphics);
        g.drawImage(this.offImage, 0, 0, this);
    }
    
    public void paint(final Graphics g) {
        g.setColor(this.bgcolor);
        g.fillRect(0, 0, this.ancho, this.alto);
        g.setColor(this.colorgrafico);
        g.fillRect(this.sephor1, this.sepver1, this.anchografico, this.altografico);
        g.setColor(this.colorejes);
        g.drawLine(this.SituarEnX(0), this.SituarEnY(this.altografico), this.SituarEnX(this.anchografico), this.SituarEnY(this.altografico));
        g.drawLine(this.SituarEnX(this.anchografico), this.SituarEnY(0), this.SituarEnX(this.anchografico), this.SituarEnY(this.altografico));
        this.DibujarEjeY(g);
        g.setColor(this.colortitulo);
        g.setFont(this.ftitulo);
        final int pos = 4;
        g.drawString(this.titulo, this.SituarEnX(pos), this.SituarEnY(0) + 10);
        if (this.mostrarinfo) {
            g.setColor(this.colorbarra1.darker());
            g.fillRect(this.SituarEnX(this.anchografico / 2), this.SituarEnY(0) + 2, 6, 6);
            g.drawString(this.leyenda1, this.SituarEnX(this.anchografico / 2) + 10, this.SituarEnY(0) + 9);
            g.setColor(this.colorlin2.darker());
            g.fillRect(this.SituarEnX(this.anchografico / 2), this.SituarEnY(0) + 12, 6, 6);
            g.drawString(this.leyenda2, this.SituarEnX(this.anchografico / 2) + 10, this.SituarEnY(0) + 19);
        }
        this.DibujarBarras(g);
        if (this.mostrarleyultimo) {
            final int posy = this.alto - this.altodato + 3;
            int posx = 2;
            g.setColor(this.colorlinea);
            g.drawLine(posx, posy, posx + this.largolinea, posy);
            posx += 20;
            g.drawString("  Ultimo: " + this.PonerDecimales(this.linea), posx, posy + this.altodato / 2);
        }
    }
    
    public void CalcularEjes() {
        float sep = 0.0f;
        if (this.intervalover > 0) {
            this.nlineasx = (int)((this.MaximoY - this.MinimoY) / this.intervalover) + 1;
            this.numlinver = this.nlineasx;
            (this.leyendaver = new String[this.nlineasx + 1])[this.nlineasx] = this.PonerDecimales(this.MinimoY);
            int aux = 0;
            if (this.MinimoY < 0.0f) {
                while (aux > this.MinimoY + this.intervalover) {
                    aux -= this.intervalover;
                }
            }
            for (int i = this.nlineasx - 1; i > -1; --i) {
                this.leyendaver[i] = this.PonerDecimales(Math.round(aux));
                aux += this.intervalover;
            }
            this.leyendaver[0] = this.PonerDecimales(Math.round(this.MaximoY));
        }
        else {
            sep = (this.MaximoY - this.MinimoY) / this.nlineasx;
            this.numlinver = this.nlineasx;
            this.leyendaver = new String[this.nlineasx + 1];
            for (int cont = 0, i = this.nlineasx; i >= 0 && cont < this.nlineasx; ++cont, --i) {
                this.leyendaver[cont] = this.PonerDecimales(Math.round(this.MaximoY - sep * i));
            }
            this.leyendaver[this.nlineasx] = this.PonerDecimales(Math.round(this.MaximoY));
        }
    }
    
    public void DibujarEjeY(final Graphics g) {
        final float sep = (this.MaximoY - this.MinimoY) / this.nlineasx;
        g.setFont(this.font);
        if (this.mostrarcopy) {
            g.setColor(Color.gray);
            g.drawString("© Infomercados.com", this.SituarEnX(2), this.SituarEnY(20));
        }
        if (this.intervalover > 0 || this.nlineasx > 0) {
            g.setColor(this.colorleyenda);
            g.drawString(this.leyendaver[0], this.SituarEnX(this.anchografico) + 2, this.SituarEnY(this.altografico) + 5);
        }
        float dato = 0.0f;
        int posy = 0;
        int cont = this.numlinver - 1;
        int aux = 0;
        if (this.MinimoY < 0.0f) {
            while (aux > this.MinimoY + this.intervalover) {
                aux -= this.intervalover;
            }
        }
        for (int i = 1; i <= this.nlineasx; ++i) {
            if (this.intervalover > 0) {
                dato = this.altografico * (this.MaximoY - aux) / (this.MaximoY - this.MinimoY);
                posy = (int)dato;
                aux += this.intervalover;
            }
            else {
                dato = this.altografico * (sep * i) / (this.MaximoY - this.MinimoY);
                posy = (int)dato;
            }
            if (posy > 0 && posy < this.altografico) {
                if (this.leyendaver[cont] != null) {
                    g.setColor(this.colorleyenda);
                    g.drawString(this.leyendaver[cont], this.SituarEnX(this.anchografico) + this.anchosaliente + 2, posy + 5);
                }
                g.setColor(this.colorlineas);
                g.drawLine(this.SituarEnX(0), this.SituarEnY(posy), this.SituarEnX(this.anchografico), this.SituarEnY(posy));
                g.setColor(this.colorleyenda);
                g.drawLine(this.SituarEnX(this.anchografico), this.SituarEnY(posy), this.SituarEnX(this.anchografico + this.anchosaliente), this.SituarEnY(posy));
            }
            --cont;
        }
        if (this.mostrarlineas && this.valores2 != null) {
            float media = 0.0f;
            for (int k = 0; k < this.nvalores; ++k) {
                media += this.valores2[k];
            }
            media /= this.nvalores;
            dato = this.altografico * (this.MaximoY - media) / (this.MaximoY - this.MinimoY);
            posy = (int)dato;
            g.setColor(Color.red);
            g.drawLine(this.SituarEnX(0), this.SituarEnY(posy), this.SituarEnX(this.anchografico), this.SituarEnY(posy));
        }
        if (this.MinimoY < 0.0f) {
            dato = this.altografico * -this.MinimoY / (this.MaximoY - this.MinimoY);
            posy = (int)dato;
            this.poscero = this.SituarEnY(this.altografico - posy);
            g.setColor(Color.black);
            g.drawLine(this.SituarEnX(0), this.poscero, this.SituarEnX(this.anchografico), this.poscero);
        }
        else {
            this.poscero = this.SituarEnY(this.altografico);
        }
    }
    
    public void DibujarBarras(final Graphics g) {
        int posxant = 0;
        int posyant = 0;
        int posx = 0;
        int posy = 0;
        int posy2 = 0;
        int posy2ant = 0;
        String cad = "";
        String cad2 = "";
        int largo = 0;
        final int sep = this.anchografico / this.nvalores;
        int pos = 0;
        int posxini = 0;
        int medio = 0;
        g.setFont(this.font);
        for (int i = 0; i < this.nvalores; ++i) {
            posx = this.barrax[i];
            posy = this.barray[i];
            if (this.valores2 != null) {
                final float dato = this.altografico * (this.MaximoY - this.valores2[i]) / (this.MaximoY - this.MinimoY);
                posy2 = (int)dato;
                posy2 = this.SituarEnY(posy2);
            }
            final Color colorbarra = this.colorbarra1;
            if (this.mostrarcolores) {
                g.setColor(this.colores[i]);
            }
            g.setColor(colorbarra);
            if (this.valores[i] != 0.0f) {
                if (this.MinimoY < 0.0f) {
                    if (this.valores[i] > 0.0f) {
                        if (this.mostrarbarra) {
                            this.DibujarProf(g, posx - this.anchobarra / 2, posy, this.valores[i], this.poscero - posy, colorbarra);
                            if (this.relleno) {
                                g.fillRect(posx - this.anchobarra / 2, posy, this.anchobarra, this.poscero - posy);
                            }
                            else {
                                g.drawRect(posx - this.anchobarra / 2, posy, this.anchobarra, this.poscero - posy);
                            }
                        }
                    }
                    else if (this.mostrarbarra) {
                        this.DibujarProf(g, posx - this.anchobarra / 2, posy, this.valores[i], posy - this.poscero, colorbarra);
                        if (this.relleno) {
                            g.fillRect(posx - this.anchobarra / 2, this.poscero, this.anchobarra, posy - this.poscero);
                        }
                        else {
                            g.drawRect(posx - this.anchobarra / 2, this.poscero, this.anchobarra, posy - this.poscero);
                        }
                    }
                }
                else if (this.mostrarbarra) {
                    this.DibujarProf(g, posx - this.anchobarra / 2, posy, this.valores[i], this.SituarEnY(this.altografico) - posy, colorbarra);
                    g.fillRect(posx - this.anchobarra / 2, posy, this.anchobarra, this.SituarEnY(this.altografico) - posy);
                }
            }
            g.setColor(this.colorleyenda);
            g.drawLine(posx + this.anchobarra / 2, this.SituarEnY(this.altografico), posx + this.anchobarra / 2, this.SituarEnY(this.altografico + this.anchosaliente));
            if (this.valores2 != null) {
                g.setColor(this.colorlin2);
                if (this.segundobarra) {
                    if (this.valores2[i] > 0.0f) {
                        this.DibujarProf(g, posx + this.anchobarra / 2, posy2, this.valores2[i], this.poscero - posy2, this.colorlin2);
                        g.fillRect(posx + this.anchobarra / 2, posy2, this.anchobarra, this.poscero - posy2);
                    }
                    else {
                        this.DibujarProf(g, posx + this.anchobarra / 2, this.poscero, this.valores2[i], posy2 - this.poscero, this.colorlin2);
                        g.fillRect(posx + this.anchobarra / 2, this.poscero, this.anchobarra, posy2 - this.poscero);
                    }
                }
                else {
                    if (i > 0) {
                        g.drawLine(posxant, posy2ant, posx, posy2);
                    }
                    posy2ant = posy2;
                }
            }
            if (this.EnBarra > -1 && this.mostraralt && this.EnBarra == i) {
                g.setFont(this.font);
                g.setColor(this.colorborde);
                if (this.PosX >= this.barrax[this.EnBarra] - this.anchobarra / 2 && this.PosX < this.barrax[this.EnBarra] + this.anchobarra / 2) {
                    cad = String.valueOf(this.valores[this.EnBarra]).toString();
                    cad = this.reemplazar(cad, ".0", "");
                    if (this.valores[this.EnBarra] > 0.0f) {
                        g.drawRect(this.barrax[this.EnBarra] - this.anchobarra / 2, this.barray[this.EnBarra], this.anchobarra, this.poscero - this.barray[this.EnBarra] - 1);
                    }
                    else {
                        g.drawRect(this.barrax[this.EnBarra] - this.anchobarra / 2, this.poscero, this.anchobarra, this.barray[this.EnBarra] - this.poscero - 1);
                    }
                }
                else if (this.PosX >= this.barrax[this.EnBarra] + this.anchobarra / 2 && this.PosX < this.barrax[this.EnBarra] + this.anchobarra + this.anchobarra / 2) {
                    cad = String.valueOf(this.valores2[this.EnBarra]).toString();
                    cad = this.reemplazar(cad, ".0", "");
                    if (this.valores2[this.EnBarra] > 0.0f) {
                        g.drawRect(this.barrax[this.EnBarra] + this.anchobarra / 2, posy2, this.anchobarra, this.poscero - posy2 - 1);
                    }
                    else {
                        g.drawRect(this.barrax[this.EnBarra] + this.anchobarra / 2, this.poscero, this.anchobarra, posy2 - this.poscero - 1);
                    }
                }
                g.setColor(Color.black);
                largo = this.fontMetrics.stringWidth(cad);
                g.drawRect(this.PosX - 2, this.PosY - 10, largo + 4, 12);
                g.setColor(this.coloralt);
                g.fillRect(this.PosX - 1, this.PosY - 9, largo + 3, 11);
                g.setColor(this.colorleyenda);
                g.drawString(cad, this.PosX, this.PosY);
            }
            if (this.lineaunion && i > 0) {
                g.setColor(this.colorunion);
                g.drawLine(posxant, posyant, posx, posy);
            }
            if (this.mostrarinfobarra) {
                g.setColor(this.colorleyenda);
                g.setFont(this.font);
                cad = this.PonerDecimales(this.valores[i]);
                largo = this.fontMetrics.stringWidth(cad);
                if (this.valores[i] > 0.0f) {
                    g.drawString(cad, posx - largo / 2, posy - this.profundidad - 3);
                }
                else if (this.valores[i] < 0.0f) {
                    g.drawString(cad, posx - largo / 2, posy + this.profundidad + 3);
                }
                else {
                    g.drawString(cad, posx - largo / 2, posy - this.profundidad - 3);
                }
            }
            if (this.valores2 != null & false) {
                cad = this.PonerDecimales(this.valores2[i]);
                largo = this.fontMetrics.stringWidth(cad);
                g.setColor(this.colorleyu);
                g.drawString(cad, posx + this.anchobarra / 2 + 1, posy2 + (this.poscero - posy2) / 2);
            }
            g.setFont(this.fontleyenda);
            g.setColor(this.colorleyenda);
            if (this.mostrardosfilas) {
                pos = this.leyendahor[i].indexOf(" ");
                if (pos > 0) {
                    cad = this.leyendahor[i].substring(0, pos);
                }
                else {
                    cad = this.leyendahor[i];
                }
                if (!this.leyendahor[i].substring(pos + 1).equalsIgnoreCase(cad2)) {
                    if (!cad2.equalsIgnoreCase("")) {
                        medio = posx - posxini;
                        medio -= (posx - posxini) / 2;
                        medio -= this.fontMetrics.stringWidth(cad2) + 20;
                        g.setFont(this.fontleyenda2);
                        g.drawString(cad2, posxini + medio, this.SituarEnY(this.altografico) + 25);
                        g.setFont(this.fontleyenda);
                        if (posx + this.profundidad < this.SituarEnX(this.anchografico)) {
                            g.setColor(this.colorlineas);
                            g.drawLine(posx - this.anchobarra, this.SituarEnY(this.altografico), posx - this.anchobarra, this.SituarEnY(this.altografico) + 25);
                        }
                        g.setColor(this.colorlineas);
                    }
                    posxini = posx;
                    cad2 = this.leyendahor[i].substring(pos + 1);
                }
                g.setColor(this.colorleyenda);
                g.drawString(cad, posx, this.SituarEnY(this.altografico) + 10 + this.anchosaliente + 2);
            }
            else {
                largo = this.fontMetrics.stringWidth(this.leyendahor[i]);
                if (this.leyinvertida) {
                    g.drawString(this.leyendahor[i], posx, this.SituarEnY(this.altografico) + largo);
                }
                else {
                    g.drawString(this.leyendahor[i], posx, this.SituarEnY(this.altografico) + 10 + this.anchosaliente + 2);
                }
            }
            posxant = posx;
            posyant = posy;
        }
        if (!cad2.equalsIgnoreCase("")) {
            g.setFont(this.fontleyenda2);
            g.setColor(this.colorleyenda);
            medio = posx - posxini;
            g.drawString(cad2, posxini + medio, this.SituarEnY(this.altografico) + 25);
        }
        if (this.mostrarultimo) {
            g.setColor(this.colorlinea);
            g.drawLine(this.SituarEnX(0), this.SituarEnY(this.poslinea), this.SituarEnX(this.anchografico), this.SituarEnY(this.poslinea));
        }
        g.setFont(this.font);
        g.setColor(this.colorleyenda);
        if (!this.leyendainf.equalsIgnoreCase("")) {
            g.drawString(this.leyendainf, this.SituarEnX(this.anchografico / 2) - 18, this.SituarEnY(this.altografico) + 23 + this.anchosaliente);
        }
    }
    
    public void DibujarProf(final Graphics g, final int posx, final int posy, final float valor, final int alto, final Color color) {
        if (this.profundidad > 0) {
            g.setColor(color.darker());
            if (this.MinimoY < 0.0f) {
                if (valor > 0.0f) {
                    final int[] x = new int[3];
                    final int[] y = new int[3];
                    x[0] = posx;
                    y[0] = posy;
                    x[1] = posx + this.profundidad;
                    y[1] = posy - this.profundidad;
                    x[2] = posx + this.profundidad;
                    y[2] = posy;
                    g.fillPolygon(x, y, 3);
                }
                else {
                    final int[] x = new int[3];
                    final int[] y = new int[3];
                    x[0] = posx;
                    y[0] = posy;
                    x[1] = posx + this.profundidad;
                    y[1] = posy + this.profundidad;
                    x[2] = posx + this.profundidad;
                    if ((y[2] = posy) == this.poscero) {
                        final int[] array = y;
                        final int n = 0;
                        array[n] += alto;
                        final int[] array2 = y;
                        final int n2 = 1;
                        array2[n2] += alto;
                        final int[] array3 = y;
                        final int n3 = 2;
                        array3[n3] += alto;
                    }
                    g.fillPolygon(x, y, 3);
                }
            }
            else {
                final int[] x = new int[3];
                final int[] y = new int[3];
                x[0] = posx;
                y[0] = posy;
                x[1] = posx + this.profundidad;
                y[1] = posy - this.profundidad;
                x[2] = posx + this.profundidad;
                y[2] = posy;
                g.fillPolygon(x, y, 3);
            }
            if (valor < 0.0f) {
                g.fillRect(posx + this.profundidad, this.poscero, this.anchobarra, alto + this.profundidad);
            }
            else {
                g.fillRect(posx + this.profundidad, posy - this.profundidad, this.anchobarra, alto + this.profundidad);
            }
            g.setColor(color);
        }
    }
    
    public int SituarEnX(final int pos) {
        return pos + this.sephor1;
    }
    
    public int SituarEnY(final int pos) {
        return pos + this.sepver1;
    }
    
    public int DarInt(final String cad) {
        int res = 0;
        try {
            res = Integer.parseInt(cad);
        }
        catch (Exception ex) {
            System.out.println("Fallo al convertir a float.");
        }
        return res;
    }
    
    public String DarLeyenda(final String cad) {
        final int pos = cad.indexOf("-");
        String res = cad;
        if (pos > -1 && cad.length() > pos + 1) {
            this.leyinvertida = true;
            res = "";
            final String mes = cad.substring(0, pos);
            if (mes.equalsIgnoreCase("1")) {
                res = "Ene";
            }
            if (mes.equalsIgnoreCase("2")) {
                res = "Feb";
            }
            if (mes.equalsIgnoreCase("3")) {
                res = "Mar";
            }
            if (mes.equalsIgnoreCase("4")) {
                res = "Abr";
            }
            if (mes.equalsIgnoreCase("5")) {
                res = "May";
            }
            if (mes.equalsIgnoreCase("6")) {
                res = "Jun";
            }
            if (mes.equalsIgnoreCase("7")) {
                res = "Jul";
            }
            if (mes.equalsIgnoreCase("8")) {
                res = "Ago";
            }
            if (mes.equalsIgnoreCase("9")) {
                res = "Sep";
            }
            if (mes.equalsIgnoreCase("10")) {
                res = "Oct";
            }
            if (mes.equalsIgnoreCase("11")) {
                res = "Nov";
            }
            if (mes.equalsIgnoreCase("12")) {
                res = "Div";
            }
            res = String.valueOf(res) + " 0" + cad.substring(pos + 1);
        }
        return res;
    }
    
    public void parseArgs() {
        String aux = "";
        aux = this.getParameter("rotado");
        if (aux == null) {
            this.rotado = -89.5f;
        }
        else {
            this.rotado = this.DarFloat(aux);
        }
        aux = this.getParameter("sephor1");
        if (aux == null) {
            this.sephor1 = 5;
        }
        else {
            this.sephor1 = Integer.parseInt(aux);
        }
        aux = this.getParameter("sephor2");
        if (aux == null) {
            this.sephor2 = 50;
        }
        else {
            this.sephor2 = Integer.parseInt(aux);
        }
        aux = this.getParameter("sepver1");
        if (aux == null) {
            this.sepver1 = 5;
        }
        else {
            this.sepver1 = Integer.parseInt(aux);
        }
        aux = this.getParameter("sepver2");
        if (aux == null) {
            this.sepver2 = 40;
        }
        else {
            this.sepver2 = Integer.parseInt(aux);
        }
        aux = this.getParameter("nvalores");
        if (aux == null) {
            this.nvalores = 8;
        }
        else {
            this.nvalores = Integer.parseInt(aux);
        }
        aux = this.getParameter("anchosaliente");
        if (aux == null) {
            this.anchosaliente = 3;
        }
        else {
            this.anchosaliente = Integer.parseInt(aux);
        }
        aux = this.getParameter("nlineasx");
        if (aux == null) {
            this.nlineasx = 4;
        }
        else {
            this.nlineasx = Integer.parseInt(aux);
        }
        aux = this.getParameter("margenbarra");
        if (aux == null) {
            this.margenbarra = 30;
        }
        else {
            this.margenbarra = Integer.parseInt(aux);
        }
        aux = this.getParameter("anchobarra");
        if (aux == null) {
            this.anchobarra = 10;
        }
        else {
            this.anchobarra = Integer.parseInt(aux);
        }
        aux = this.getParameter("sepmin");
        if (aux == null) {
            this.sepmin = 5;
        }
        else {
            this.sepmin = Integer.parseInt(aux);
        }
        aux = this.getParameter("decimales");
        if (aux == null) {
            this.decimales = 2;
        }
        else {
            this.decimales = Integer.parseInt(aux);
        }
        this.bgcolor = this.parseColor(this.getParameter("bgcolor"));
        if (this.bgcolor == null) {
            this.bgcolor = this.parseColor("255,255,255");
        }
        this.colorgrafico = this.parseColor(this.getParameter("colorgrafico"));
        if (this.colorgrafico == null) {
            this.colorgrafico = Color.white;
        }
        this.colorleyenda = this.parseColor(this.getParameter("colorleyenda"));
        if (this.colorleyenda == null) {
            this.colorleyenda = Color.black;
        }
        this.colorbarra1 = this.parseColor(this.getParameter("colorbarra1"));
        if (this.colorbarra1 == null) {
            this.colorbarra1 = this.parseColor("99,150,206");
        }
        this.colorbarra2 = this.parseColor(this.getParameter("colorbarra2"));
        if (this.colorbarra2 == null) {
            this.colorbarra2 = Color.orange;
        }
        this.colorbarra3 = this.parseColor(this.getParameter("colorbarra3"));
        if (this.colorbarra3 == null) {
            this.colorbarra3 = this.parseColor("206,0,0");
        }
        this.colorbarra4 = this.parseColor(this.getParameter("colorbarra4"));
        if (this.colorbarra4 == null) {
            this.colorbarra4 = this.parseColor("255,77,77");
        }
        this.colorbarra5 = this.parseColor(this.getParameter("colorbarra5"));
        if (this.colorbarra5 == null) {
            this.colorbarra5 = Color.black;
        }
        this.colorleyu = this.parseColor(this.getParameter("colorleyu"));
        if (this.colorleyu == null) {
            this.colorleyu = Color.blue;
        }
        this.colorejes = this.parseColor(this.getParameter("colorejes"));
        if (this.colorejes == null) {
            this.colorejes = Color.black;
        }
        this.colorlineas = this.parseColor(this.getParameter("colorlineas"));
        if (this.colorlineas == null) {
            this.colorlineas = this.parseColor("222,215,222");
        }
        this.colorultimo = this.parseColor(this.getParameter("colorultimo"));
        if (this.colorultimo == null) {
            this.colorultimo = this.parseColor("153,153,255");
        }
        this.colortitulo = this.parseColor(this.getParameter("colortitulo"));
        if (this.colortitulo == null) {
            this.colortitulo = this.parseColor("0,0,132");
        }
        this.colorborde = this.parseColor(this.getParameter("colorborde"));
        if (this.colorborde == null) {
            this.colorborde = this.parseColor("255,255,255");
        }
        this.colorlinea = this.parseColor(this.getParameter("colorlinea"));
        if (this.colorlinea == null) {
            this.colorlinea = Color.red;
        }
        this.colornegativo = this.parseColor(this.getParameter("colornegativo"));
        if (this.colornegativo == null) {
            this.colornegativo = this.parseColor("250,72,72");
        }
        String cadvalores = this.getParameter("valores");
        if (cadvalores == null) {
            cadvalores = new String("20,8,15,11,5,46,9,9");
        }
        String cadvalores2 = this.getParameter("valores2");
        if (cadvalores2 == null) {
            cadvalores2 = new String("11,12,13,14,69,70,100,100");
        }
        aux = String.valueOf(this.getParameter("mostrardosfilas")).toString();
        if (aux.equals("yes")) {
            this.mostrardosfilas = true;
        }
        else {
            this.mostrardosfilas = false;
        }
        aux = String.valueOf(this.getParameter("segundobarra")).toString();
        if (aux.equals("no")) {
            this.segundobarra = false;
        }
        else {
            this.segundobarra = true;
        }
        aux = String.valueOf(this.getParameter("mostrarcolores")).toString();
        if (aux.equals("yes")) {
            this.mostrarcolores = true;
        }
        else {
            this.mostrarcolores = false;
        }
        this.leyenda1 = this.getParameter("leyenda1");
        if (this.leyenda1 == null) {
            this.leyenda1 = new String("ACX");
        }
        this.leyenda2 = this.getParameter("leyenda2");
        if (this.leyenda2 == null) {
            this.leyenda2 = new String("Sector");
        }
        String cadleyenda = this.getParameter("leyenda");
        if (cadleyenda == null) {
            cadleyenda = new String("2001,2002,2003,2004,2005,2006,2007,2008");
        }
        this.titulo = this.getParameter("titulo");
        if (this.titulo == null) {
            this.titulo = new String("PER ACX 1999-2005");
        }
        this.valores = new float[this.nvalores];
        this.leyendahor = new String[this.nvalores];
        aux = cadvalores;
        this.colores = new Color[this.nvalores];
        for (int i = 0; i < this.nvalores; ++i) {
            final int pos = aux.indexOf(",");
            if (pos > 0) {
                this.valores[i] = this.DarFloat(aux.substring(0, pos));
                aux = aux.substring(pos + 1);
            }
            else {
                this.valores[i] = this.DarFloat(aux);
                aux = "";
            }
            this.colores[i] = this.parseColor(this.getParameter("color" + String.valueOf(i + 1).toString()));
            if (this.colores[i] == null) {
                this.colores[i] = this.colorbarra1;
            }
        }
        if (!cadvalores2.equalsIgnoreCase("")) {
            this.valores2 = new float[this.nvalores];
            aux = cadvalores2;
            for (int i = 0; i < this.nvalores; ++i) {
                final int pos = aux.indexOf(",");
                if (pos > 0) {
                    this.valores2[i] = this.DarFloat(aux.substring(0, pos));
                    aux = aux.substring(pos + 1);
                }
                else {
                    this.valores2[i] = this.DarFloat(aux);
                    aux = "";
                }
            }
        }
        this.MaximoY = this.valores[0];
        this.MinimoY = this.valores[0];
        for (int i = 0; i < this.nvalores; ++i) {
            if (this.MinimoY > this.valores[i]) {
                this.MinimoY = this.valores[i];
            }
            if (this.MaximoY < this.valores[i]) {
                this.MaximoY = this.valores[i];
            }
            if (!cadvalores2.equalsIgnoreCase("")) {
                if (this.MinimoY > this.valores2[i]) {
                    this.MinimoY = this.valores2[i];
                }
                if (this.MaximoY < this.valores2[i]) {
                    this.MaximoY = this.valores2[i];
                }
            }
        }
        final float menor = this.MinimoY;
        this.mayor = this.MaximoY;
        float margen;
        if (Math.abs(this.MaximoY) > Math.abs(this.MinimoY)) {
            margen = this.margenbarra * this.MaximoY / 100.0f;
        }
        else {
            margen = this.margenbarra * this.MinimoY / 100.0f;
        }
        margen = Math.abs(margen);
        if (this.MaximoY < 0.0f && margen < 0.0f) {
            this.MaximoY -= margen;
        }
        else {
            this.MaximoY += margen;
        }
        aux = cadleyenda;
        for (int j = 0; j < this.nvalores; ++j) {
            final int pos2 = aux.indexOf(",");
            if (pos2 > 0) {
                if (this.mostrardosfilas) {
                    this.leyendahor[j] = aux.substring(0, pos2);
                }
                else {
                    this.leyendahor[j] = this.DarLeyenda(aux.substring(0, pos2));
                }
                aux = aux.substring(pos2 + 1);
            }
            else {
                if (this.mostrardosfilas) {
                    this.leyendahor[j] = aux;
                }
                else {
                    this.leyendahor[j] = this.DarLeyenda(aux);
                }
                aux = "";
            }
        }
        aux = String.valueOf(this.getParameter("mostrarultimo")).toString();
        if (aux.equals("yes")) {
            this.mostrarultimo = true;
        }
        else {
            this.mostrarultimo = false;
        }
        aux = this.getParameter("linea");
        if (aux == null) {
            this.linea = 29.0f;
        }
        else {
            this.linea = this.DarFloat(aux);
        }
        if (this.mostrarultimo) {
            if (this.linea > this.MaximoY) {
                this.MaximoY = this.linea + margen;
            }
            if (this.linea < this.MinimoY) {
                this.MinimoY = this.linea - margen;
            }
        }
        aux = String.valueOf(this.getParameter("minimocero")).toString();
        if (aux.equals("no")) {
            this.minimocero = false;
        }
        else {
            this.minimocero = true;
        }
        if (this.minimocero && this.MinimoY > 0.0f) {
            this.MinimoY = 0.0f;
        }
        if (!this.minimocero) {
            if (this.MinimoY < 0.0f && margen < 0.0f) {
                this.MinimoY += margen;
            }
            else {
                this.MinimoY -= margen;
            }
        }
        aux = this.getParameter("Minimo");
        if (aux != null) {
            this.MinimoY = this.DarFloat(aux);
        }
        if (this.MaximoY < 0.0f) {
            this.MaximoY = 0.0f;
        }
        aux = String.valueOf(this.getParameter("lineaunion")).toString();
        if (aux.equals("no")) {
            this.lineaunion = false;
        }
        else {
            this.lineaunion = true;
        }
        this.colorunion = this.parseColor(this.getParameter("colorunion"));
        if (this.colorunion == null) {
            this.colorunion = Color.black;
        }
        this.colorlin2 = this.parseColor(this.getParameter("colorlin2"));
        if (this.colorlin2 == null) {
            this.colorlin2 = this.parseColor("165,165,165");
        }
        this.colorpos = this.parseColor(this.getParameter("colorpos"));
        if (this.colorpos == null) {
            this.colorpos = Color.green;
        }
        this.colorneg = this.parseColor(this.getParameter("colorneg"));
        if (this.colorneg == null) {
            this.colorneg = Color.green;
        }
        aux = String.valueOf(this.getParameter("mostraralt")).toString();
        if (aux.equals("no")) {
            this.mostraralt = false;
        }
        else {
            this.mostraralt = true;
        }
        aux = String.valueOf(this.getParameter("mostrarinfobarra")).toString();
        if (aux.equals("yes")) {
            this.mostrarinfobarra = true;
        }
        else {
            this.mostrarinfobarra = false;
        }
        aux = String.valueOf(this.getParameter("mostrarbarra")).toString();
        if (aux.equals("no")) {
            this.mostrarbarra = false;
        }
        else {
            this.mostrarbarra = true;
        }
        aux = String.valueOf(this.getParameter("relleno")).toString();
        if (aux.equals("no")) {
            this.relleno = false;
        }
        else {
            this.relleno = true;
        }
        aux = this.getParameter("profundidad");
        if (aux == null) {
            this.profundidad = 3;
        }
        else {
            this.profundidad = Integer.parseInt(aux);
        }
        aux = String.valueOf(this.getParameter("mostrarleyultimo")).toString();
        if (aux.equals("yes")) {
            this.mostrarleyultimo = true;
        }
        else {
            this.mostrarleyultimo = false;
        }
        aux = String.valueOf(this.getParameter("mostrarinfo")).toString();
        if (aux.equals("no")) {
            this.mostrarinfo = false;
        }
        else {
            this.mostrarinfo = true;
        }
        aux = this.getParameter("largolinea");
        if (aux == null) {
            this.largolinea = 20;
        }
        else {
            this.largolinea = Integer.parseInt(aux);
        }
        this.fontname = this.getParameter("Fuente");
        if (this.fontname == null) {
            this.fontname = new String("Tahoma");
        }
        String s = this.getParameter("TamFuente");
        try {
            this.fontsize = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.fontsize = 9;
        }
        s = this.getParameter("EstiloFuente");
        try {
            if (s.equalsIgnoreCase("Bold")) {
                this.fontstyle = 1;
            }
            else if (s.equalsIgnoreCase("Italic")) {
                this.fontstyle = 2;
            }
            else if (s.equalsIgnoreCase("BoldItalic") || s.equalsIgnoreCase("ItalicBold")) {
                this.fontstyle = 3;
            }
            else {
                this.fontstyle = 0;
            }
        }
        catch (Exception ex2) {
            this.fontstyle = 0;
        }
        s = this.getParameter("letratitulo");
        try {
            this.letratitulo = Integer.parseInt(s);
        }
        catch (Exception ex3) {
            this.letratitulo = 10;
        }
        s = this.getParameter("altodato");
        try {
            this.altodato = Integer.parseInt(s);
        }
        catch (Exception ex4) {
            this.altodato = 10;
        }
        s = this.getParameter("intervalover");
        try {
            this.intervalover = Integer.parseInt(s);
        }
        catch (Exception ex5) {
            this.intervalover = 0;
        }
        this.coloralt = this.parseColor(this.getParameter("coloralt"));
        if (this.coloralt == null) {
            this.coloralt = Color.white;
        }
        aux = String.valueOf(this.getParameter("mostrarcopy")).toString();
        if (aux.equals("no")) {
            this.mostrarcopy = false;
        }
        else {
            this.mostrarcopy = true;
        }
        aux = String.valueOf(this.getParameter("mostrarlineas")).toString();
        if (aux.equals("no")) {
            this.mostrarlineas = false;
        }
        else {
            this.mostrarlineas = true;
        }
        this.leyendainf = this.getParameter("leyendainf");
        if (this.leyendainf == null) {
            this.leyendainf = "A\u00f1o";
        }
        this.servidor = this.getParameter("servidor");
        if (this.servidor == null) {
            this.servidor = this.getCodeBase().getHost();
        }
        this.ticker = this.getParameter("ticker");
        if (this.ticker == null) {
            this.ticker = "ACX";
        }
        this.fichapopup = this.getParameter("fichapopup");
        if (this.fichapopup == null) {
            this.fichapopup = "webn/analisis/abrirper.asp";
        }
    }
    
    private Color parseColor(final String s) {
        try {
            if (s.equalsIgnoreCase("Yellow")) {
                return Color.yellow;
            }
            if (s.equalsIgnoreCase("Blue")) {
                return Color.blue;
            }
            if (s.equalsIgnoreCase("Black")) {
                return Color.black;
            }
            if (s.equalsIgnoreCase("Red")) {
                return Color.red;
            }
            if (s.equalsIgnoreCase("White")) {
                return Color.white;
            }
            if (s.equalsIgnoreCase("Green")) {
                return Color.green;
            }
            if (s.equalsIgnoreCase("Pink")) {
                return Color.pink;
            }
            if (s.equalsIgnoreCase("Orange")) {
                return Color.orange;
            }
            final int off1 = s.indexOf(44);
            final int r = Integer.parseInt(s.substring(0, off1));
            final int off2 = s.indexOf(44, off1 + 1);
            final int g = Integer.parseInt(s.substring(off1 + 1, off2));
            final int b = Integer.parseInt(s.substring(off2 + 1));
            return new Color(r, g, b);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public float DarFloat(final String cad) {
        float res = 0.0f;
        if (!cad.equalsIgnoreCase("")) {
            final String dato = cad.replace(',', '.');
            try {
                final Float numero = new Float(dato);
                res = numero;
            }
            catch (Exception ex) {
                System.out.println("Fallo al convertir a float: " + cad);
            }
        }
        return res;
    }
    
    public String reemplazar(final String cad, final String cad1, final String cad2) {
        String res = cad;
        for (int pos = res.indexOf(cad1); pos != -1; pos = res.indexOf(cad1)) {
            String aux = String.valueOf(res.substring(0, pos)) + cad2;
            aux = (res = String.valueOf(aux) + res.substring(pos + cad1.length()));
        }
        return res;
    }
    
    public float redondear(final float num, final int decimales) {
        double res = num;
        if (decimales == 0) {
            res = Math.round(res);
        }
        else {
            res = num * Math.pow(10.0, decimales);
            res = Math.round(res);
            res /= Math.pow(10.0, decimales);
        }
        return (float)res;
    }
    
    public String PonerDecimales(final float numero) {
        String res = String.valueOf(String.valueOf(this.redondear(Math.abs(numero), 2)).toString()) + " ";
        String resto = "";
        String cadena = "";
        final String separadordecimal = ",";
        final String separadormiles = ".";
        res = this.reemplazar(res, ".0 ", " ");
        res = this.reemplazar(res, " ", "");
        String sep = ".";
        if (!separadordecimal.equalsIgnoreCase("")) {
            res = this.reemplazar(res, ".", separadordecimal);
            sep = separadordecimal;
        }
        if (!separadormiles.equalsIgnoreCase("") || !separadormiles.equalsIgnoreCase(separadordecimal)) {
            final int pos = res.indexOf(sep);
            String aux = res;
            if (pos > -1) {
                aux = res.substring(0, pos);
                resto = res.substring(pos);
            }
            while (aux.length() > 0) {
                if (aux.length() > 3) {
                    cadena = String.valueOf(separadormiles) + aux.substring(aux.length() - 3, aux.length()) + cadena;
                    aux = aux.substring(0, aux.length() - 3);
                }
                else {
                    cadena = String.valueOf(aux) + cadena;
                    aux = "";
                }
            }
            res = String.valueOf(cadena) + resto;
        }
        if (numero < 0.0f) {
            res = "-" + res;
        }
        return res;
    }
    
    public HistogramaBM() {
        this.leyinvertida = false;
        this.mayor = 0.0f;
        this.posmayor = 0;
        this.mostrarinfo = false;
        this.intervalover = 10;
        this.EnBarra = -1;
        this.mostraralt = true;
        this.PosX = -1;
        this.PosY = -1;
        this.mostrarinfobarra = false;
        this.anchosaliente = 0;
        this.numlinver = 0;
        this.mostrarcopy = true;
        this.mostrarlineas = true;
        this.leyenda1 = "";
        this.leyenda2 = "";
        this.leyendainf = "";
        this.titulo = "";
        this.poslinea = 0;
        this.mostrarbarra = true;
        this.altodato = 7;
    }
    
    public void mouseDragged(final MouseEvent p1) {
    }
    
    public void mouseMoved(final MouseEvent e) {
        final int x = e.getX();
        final int y = e.getY();
        this.EnBarra = -1;
        this.PosY = -1;
        this.PosX = -1;
        this.setCursor(HistogramaBM.NORM_CURSOR);
        if (x > this.SituarEnX(0) && x < this.SituarEnX(this.anchografico) && y > this.SituarEnY(0) && y < this.SituarEnY(this.altografico)) {
            this.setCursor(HistogramaBM.HAND_CURSOR);
            int i;
            int res;
            for (i = 0, res = -1; i < this.nvalores && res == -1; ++i) {
                if (x > this.barrax[i] && x < this.barrax[i] + this.anchobarra && y > this.barray[i] && y < this.SituarEnY(this.altografico)) {
                    res = i;
                }
            }
            if (res > -1) {
                this.EnBarra = res;
                this.PosY = y;
                this.PosX = x;
                this.repaint();
            }
        }
    }
    
    public void mouseReleased(final MouseEvent p1) {
    }
    
    public void mouseEntered(final MouseEvent p1) {
    }
    
    public void mouseClicked(final MouseEvent e) {
        final int x = e.getX();
        final int y = e.getY();
        if (x > this.SituarEnX(0) && x < this.SituarEnX(this.anchografico) && y > this.SituarEnY(0) && y < this.SituarEnY(this.altografico)) {
            try {
                final URL url = new URL("http://" + this.servidor + "/" + this.fichapopup + "?TI=" + this.ticker);
                this.getAppletContext().showDocument(url, "_top");
            }
            catch (Exception ex) {
                this.showStatus("Error al cargar la ficha diaria.");
            }
        }
    }
    
    public void mousePressed(final MouseEvent p1) {
    }
    
    public void mouseExited(final MouseEvent p1) {
    }
    
    static {
        HAND_CURSOR = new Cursor(12);
        NORM_CURSOR = new Cursor(0);
    }
}
