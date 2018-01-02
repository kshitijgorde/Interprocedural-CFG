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

class Lp extends Canvas implements MouseListener
{
    private Dimension MDb;
    private Graphics NDb;
    private Image ODb;
    private final Color[][] PDb;
    private int QDb;
    private int RDb;
    private int SDb;
    private int TDb;
    private Mp UDb;
    private Mp VDb;
    private final Kp n;
    
    public Lp(final Kp n) {
        this.n = n;
        this.MDb = null;
        this.NDb = null;
        this.ODb = null;
        this.PDb = new Color[][] { { new Color(16777215), new Color(16053492), new Color(16774875), new Color(16776424), new Color(16777188), new Color(14876644), new Color(15007743), new Color(15532031), new Color(15527167), new Color(16771327) }, { new Color(15000804), new Color(16764108), new Color(16764057), new Color(16777113), new Color(16777164), new Color(10092441), new Color(10092543), new Color(13434879), new Color(13421823), new Color(16764159) }, { new Color(13421772), new Color(16737894), new Color(16750950), new Color(16777062), new Color(16777011), new Color(6750105), new Color(3407871), new Color(6750207), new Color(10066431), new Color(16751103) }, { new Color(12632256), new Color(16711680), new Color(16750848), new Color(16764006), new Color(16776960), new Color(3407667), new Color(6737100), new Color(3394815), new Color(6710988), new Color(13395660) }, { new Color(10066329), new Color(13369344), new Color(16737792), new Color(16763955), new Color(16763904), new Color(3394560), new Color(52428), new Color(3368703), new Color(6697983), new Color(13382604) }, { new Color(6710886), new Color(10027008), new Color(13395456), new Color(13408563), new Color(10066176), new Color(39168), new Color(3381657), new Color(3355647), new Color(6684876), new Color(10040217) }, { new Color(3355443), new Color(6684672), new Color(10040064), new Color(10053171), new Color(6710784), new Color(26112), new Color(3368550), new Color(153), new Color(3355545), new Color(6697830) }, { new Color(0), new Color(3342336), new Color(6697728), new Color(6697779), new Color(3355392), new Color(13056), new Color(13107), new Color(102), new Color(3342489), new Color(3342387) } };
        this.QDb = this.PDb[0].length;
        this.RDb = this.PDb.length;
        this.SDb = -1;
        this.TDb = -1;
        this.UDb = new Mp(0.0, 1.0, 0.0, 1.0);
        this.VDb = new Mp(0.0, 1.0, 0.0, 1.0);
        this.UDb.b(false);
        this.VDb.b(false);
        this.addMouseListener(this);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final float n = width / this.QDb;
        final float n2 = height / this.RDb;
        final int round = Math.round(n);
        final int round2 = Math.round(n2);
        this.UDb.a(0.0, this.QDb - 1);
        this.UDb.b(n / 2.0f, width - n / 2.0f);
        this.VDb.a(0.0, this.RDb - 1);
        this.VDb.b(n2 / 2.0f, height - n2 / 2.0f);
        if (this.ODb == null || this.NDb == null || this.MDb == null || width != this.MDb.width || height != this.MDb.height) {
            if (this.NDb != null) {
                this.NDb.dispose();
            }
            if (this.ODb != null) {
                this.ODb.flush();
            }
            this.ODb = this.createImage(width, height);
            this.NDb = this.ODb.getGraphics();
            this.MDb = new Dimension(width, height);
        }
        for (int i = 0; i < this.RDb; ++i) {
            for (int j = 0; j < this.QDb; ++j) {
                final int round3 = Math.round(j * n);
                final int round4 = Math.round(i * n2);
                this.NDb.setColor(this.PDb[i][j]);
                this.NDb.fillRect(round3, round4, round + 1, round2 + 1);
                this.NDb.setXORMode(Color.black);
                this.NDb.setPaintMode();
            }
        }
        if (this.SDb >= 0 && this.TDb >= 0) {
            final int round5 = Math.round(this.SDb * n);
            final int round6 = Math.round(this.TDb * n2);
            this.NDb.setColor(Color.black);
            this.NDb.drawRect(round5 + 1, round6 + 1, round - 3, round2 - 3);
            this.NDb.setColor(Color.white);
            this.NDb.drawRect(round5 + 2, round6 + 2, round - 5, round2 - 5);
        }
        graphics.drawImage(this.ODb, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        int sDb = (int)Math.round(this.UDb.e(mouseEvent.getX()));
        int tDb = (int)Math.round(this.VDb.e(mouseEvent.getY()));
        if (sDb < 0) {
            sDb = 0;
        }
        if (sDb > this.QDb - 1) {
            sDb = this.QDb - 1;
        }
        if (tDb < 0) {
            tDb = 0;
        }
        if (tDb > this.RDb - 1) {
            tDb = this.RDb - 1;
        }
        this.SDb = sDb;
        this.TDb = tDb;
        this.repaint();
    }
    
    public Color b() {
        if (this.SDb >= 0 && this.TDb >= 0) {
            return this.PDb[this.TDb][this.SDb];
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
