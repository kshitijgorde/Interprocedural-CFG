import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Rh extends Canvas implements MouseListener
{
    private Dimension Gja;
    private Graphics Hja;
    private Image Ija;
    private final Color[][] Jja;
    private int Kja;
    private int Lja;
    private int Mja;
    private int Nja;
    private var Oja;
    private var Pja;
    
    public Rh() {
        this.Gja = null;
        this.Hja = null;
        this.Ija = null;
        this.Jja = new Color[][] { { new Color(16777215), new Color(16053492), new Color(16774875), new Color(16776424), new Color(16777188), new Color(14876644), new Color(15007743), new Color(15532031), new Color(15527167), new Color(16771327) }, { new Color(15000804), new Color(16764108), new Color(16764057), new Color(16777113), new Color(16777164), new Color(10092441), new Color(10092543), new Color(13434879), new Color(13421823), new Color(16764159) }, { new Color(13421772), new Color(16737894), new Color(16750950), new Color(16777062), new Color(16777011), new Color(6750105), new Color(3407871), new Color(6750207), new Color(10066431), new Color(16751103) }, { new Color(12632256), new Color(16711680), new Color(16750848), new Color(16764006), new Color(16776960), new Color(3407667), new Color(6737100), new Color(3394815), new Color(6710988), new Color(13395660) }, { new Color(10066329), new Color(13369344), new Color(16737792), new Color(16763955), new Color(16763904), new Color(3394560), new Color(52428), new Color(3368703), new Color(6697983), new Color(13382604) }, { new Color(6710886), new Color(10027008), new Color(13395456), new Color(13408563), new Color(10066176), new Color(39168), new Color(3381657), new Color(3355647), new Color(6684876), new Color(10040217) }, { new Color(3355443), new Color(6684672), new Color(10040064), new Color(10053171), new Color(6710784), new Color(26112), new Color(3368550), new Color(153), new Color(3355545), new Color(6697830) }, { new Color(0), new Color(3342336), new Color(6697728), new Color(6697779), new Color(3355392), new Color(13056), new Color(13107), new Color(102), new Color(3342489), new Color(3342387) } };
        this.Kja = this.Jja[0].length;
        this.Lja = this.Jja.length;
        this.Mja = -1;
        this.Nja = -1;
        this.Oja = new var(0.0, 1.0, 0.0, 1.0);
        this.Pja = new var(0.0, 1.0, 0.0, 1.0);
        this.Oja.g(false);
        this.Pja.g(false);
        this.addMouseListener(this);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final float n = width / this.Kja;
        final float n2 = height / this.Lja;
        final int round = Math.round(n);
        final int round2 = Math.round(n2);
        this.Oja.b(0.0, this.Kja - 1);
        this.Oja._(n / 2.0f, width - n / 2.0f);
        this.Pja.b(0.0, this.Lja - 1);
        this.Pja._(n2 / 2.0f, height - n2 / 2.0f);
        if (this.Ija == null || this.Hja == null || this.Gja == null || width != this.Gja.width || height != this.Gja.height) {
            if (this.Hja != null) {
                this.Hja.dispose();
            }
            if (this.Ija != null) {
                this.Ija.flush();
            }
            this.Ija = this.createImage(width, height);
            this.Hja = this.Ija.getGraphics();
            this.Gja = new Dimension(width, height);
        }
        for (int i = 0; i < this.Lja; ++i) {
            for (int j = 0; j < this.Kja; ++j) {
                final int round3 = Math.round(j * n);
                final int round4 = Math.round(i * n2);
                this.Hja.setColor(this.Jja[i][j]);
                this.Hja.fillRect(round3, round4, round + 1, round2 + 1);
                this.Hja.setXORMode(Color.black);
                this.Hja.setPaintMode();
            }
        }
        if (this.Mja >= 0 && this.Nja >= 0) {
            final int round5 = Math.round(this.Mja * n);
            final int round6 = Math.round(this.Nja * n2);
            this.Hja.setColor(Color.black);
            this.Hja.drawRect(round5 + 1, round6 + 1, round - 3, round2 - 3);
            this.Hja.setColor(Color.white);
            this.Hja.drawRect(round5 + 2, round6 + 2, round - 5, round2 - 5);
        }
        graphics.drawImage(this.Ija, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        int mja = (int)Math.round(this.Oja.a(mouseEvent.getX()));
        int nja = (int)Math.round(this.Pja.a(mouseEvent.getY()));
        if (mja < 0) {
            mja = 0;
        }
        if (mja > this.Kja - 1) {
            mja = this.Kja - 1;
        }
        if (nja < 0) {
            nja = 0;
        }
        if (nja > this.Lja - 1) {
            nja = this.Lja - 1;
        }
        this.Mja = mja;
        this.Nja = nja;
        this.repaint();
    }
    
    public Color _() {
        if (this.Mja >= 0 && this.Nja >= 0) {
            return this.Jja[this.Nja][this.Mja];
        }
        return null;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(260, 180);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(260, 180);
    }
    
    public Dimension getMaximumSize() {
        return new Dimension(260, 180);
    }
}
