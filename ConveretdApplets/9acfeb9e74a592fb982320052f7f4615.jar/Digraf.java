import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Digraf extends Canvas implements MouseListener
{
    Difus padre;
    Image clonima;
    Graphics clongraf;
    Rectangle rgraf;
    boolean bcurva;
    int intervalx;
    int acumulador;
    int[] bars1;
    int[] bars2;
    String destip;
    
    public Digraf(final Difus padre) {
        this.rgraf = new Rectangle(0, 0, 0, 0);
        this.bcurva = false;
        this.intervalx = 10;
        this.bars1 = new int[24];
        this.bars2 = new int[24];
        this.destip = " ";
        this.padre = padre;
        this.addMouseListener(this);
        this.setBackground(Color.black);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.clonima, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void fixpaint(final Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.drawLine(this.rgraf.width / 2, 2, this.rgraf.width / 2, this.rgraf.height - 2);
        graphics.drawLine(4, this.rgraf.height / 2, this.rgraf.width, this.rgraf.height / 2);
        graphics.setColor(Color.yellow);
        graphics.drawString("Mean free path= 16 pixels", 10, 10);
        graphics.clearRect(2, this.rgraf.height - 30, 60, 30);
        graphics.setColor(Color.yellow);
        graphics.drawString("N = " + String.valueOf(this.padre.calcular.contador), 10, this.rgraf.height - 10);
        graphics.clearRect(this.rgraf.width - 80, this.rgraf.height - 30, 80, 30);
        graphics.setColor(Color.yellow);
        this.destip = String.valueOf(this.padre.calcular.desv_tip);
        try {
            this.destip = this.destip.substring(0, 5);
            graphics.drawString("st. dev.= " + this.destip, this.rgraf.width - 80, this.rgraf.height - 10);
        }
        catch (StringIndexOutOfBoundsException ex) {}
        if (this.bcurva & !this.padre.btrace) {
            this.cal_bar();
            this.curvapaint(graphics, this.bars2);
        }
    }
    
    private void cal_bar() {
        ++this.acumulador;
        for (int i = 0; i < 12; ++i) {
            final int[] recuento = this.padre.calcular.recuento(i * this.intervalx, (i + 1) * this.intervalx);
            this.bars1[2 * i] += recuento[0] / 4;
            this.bars1[2 * i + 1] += recuento[1] / 4;
        }
        if (this.acumulador == 3) {
            this.acumulador = 0;
            for (int j = 0; j < 24; ++j) {
                this.bars2[j] = this.bars1[j];
                this.bars1[j] = 0;
            }
        }
    }
    
    public void curvapaint(final Graphics graphics, final int[] array) {
        graphics.setColor(Color.blue);
        for (int i = 0; i < 12; ++i) {
            graphics.drawRect(this.rgraf.width / 2 + i * this.intervalx, this.rgraf.height - array[2 * i], this.intervalx, array[2 * i]);
            graphics.drawRect(this.rgraf.width / 2 - (i + 1) * this.intervalx, this.rgraf.height - array[2 * i + 1], this.intervalx, array[2 * i + 1]);
        }
    }
    
    public void iniciar() {
        this.rgraf = this.getBounds();
        this.intervalx = this.rgraf.width / 24;
        this.clonima = this.createImage(this.rgraf.width, this.rgraf.height);
        this.clongraf = this.clonima.getGraphics();
        this.repaint();
    }
    
    public void dibusalto(final int n, final boolean b) {
        if (!b) {
            this.clongraf.clearRect(0, 0, this.rgraf.width, this.rgraf.height);
        }
        this.fixpaint(this.clongraf);
        this.clongraf.setColor(Color.white);
        for (int i = 0; i < this.padre.calcular.num_partic; ++i) {
            if (!this.padre.calcular.fuera_lim[i]) {
                this.clongraf.drawLine(this.padre.calcular.icx[n][i], this.padre.calcular.icy[n][i], this.padre.calcular.icx[n][i], this.padre.calcular.icy[n][i]);
            }
        }
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.bcurva) {
            this.bcurva = true;
            return;
        }
        this.bcurva = false;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
}
