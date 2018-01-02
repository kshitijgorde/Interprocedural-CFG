import java.awt.image.ImageObserver;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class QMButton extends Canvas implements MouseListener
{
    private Image gbuffer;
    private Graphics gbuf;
    private FontMetrics fm;
    private boolean ralink;
    private boolean binnen;
    private String tag;
    private QMData qmd;
    private int nr;
    public Dimension dim;
    public static Image[] pics;
    
    public QMButton(final QMData qmd, final int n) {
        this.ralink = false;
        this.binnen = false;
        this.nr = 0;
        this.qmd = qmd;
        this.ralink = true;
        this.tag = "Visit RealApplets";
        this.setBackground(QuizMaster.colors[0]);
        this.setSize(this.dim = new Dimension(n, 20));
        this.addMouseListener(this);
    }
    
    public QMButton(final QMData qmd, final String tag, final int n, final int nr) {
        this.ralink = false;
        this.binnen = false;
        this.nr = 0;
        this.qmd = qmd;
        this.tag = tag;
        this.nr = nr;
        this.setSize(this.dim = new Dimension(n, 20));
        this.addMouseListener(this);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.ralink) {
            try {
                this.qmd.qm.getAppletContext().showDocument(new URL("http://www.realapplets.com"), "_blank");
            }
            catch (MalformedURLException ex) {}
        }
        else {
            this.qmd.actionPerformed(this.nr);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.binnen = true;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.binnen = false;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics gbuf) {
        if (this.gbuffer == null) {
            try {
                this.gbuffer = this.createImage(this.dim.width, this.dim.height);
                this.gbuf = this.gbuffer.getGraphics();
            }
            catch (Exception ex) {
                this.gbuf = gbuf;
                this.gbuffer = null;
            }
            this.gbuf.setFont(new Font("Arial", 0, 14));
            this.fm = this.gbuf.getFontMetrics();
        }
        this.gbuf.drawImage(QMButton.pics[this.binnen], 0, 0, this.dim.width, this.dim.height, this);
        this.gbuf.setColor(QuizMaster.colors[this.binnen ? 9 : 8]);
        this.gbuf.drawString(this.tag, (this.dim.width - this.fm.stringWidth(this.tag)) / 2, this.dim.height - 3 - this.fm.getMaxDescent());
        if (this.gbuffer != null) {
            gbuf.drawImage(this.gbuffer, 0, 0, this.dim.width, this.dim.height, this);
        }
    }
    
    static {
        QMButton.pics = new Image[2];
    }
}
