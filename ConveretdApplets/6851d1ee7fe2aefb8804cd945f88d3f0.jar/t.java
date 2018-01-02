import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.AWTEventMulticaster;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.io.IOException;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public final class t extends Component implements MouseListener, MouseMotionListener
{
    private Image jsa;
    private String ksa;
    private boolean lsa;
    ActionListener msa;
    private Dimension size;
    private boolean nsa;
    private boolean Bja;
    private dj osa;
    private Color psa;
    private Color qsa;
    private Color rma;
    private boolean ela;
    private Dimension Gja;
    private Graphics Hja;
    private Image Ija;
    private static final Component rsa;
    private static final MediaTracker ssa;
    private static int tsa;
    
    public t(final String s, final String ksa) {
        this.lsa = false;
        this.nsa = false;
        this.Bja = false;
        this.osa = null;
        this.psa = null;
        this.qsa = null;
        this.rma = Color.black;
        this.ela = false;
        try {
            this.getClass().getResource(s).openConnection().setDefaultUseCaches(true);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            this.jsa = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(s));
            synchronized (t.ssa) {
                final int n = t.tsa++;
                t.ssa.addImage(this.jsa, n);
                t.ssa.waitForID(n, 0L);
                t.ssa.removeImage(this.jsa, n);
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        this.ksa = ksa;
        if (this.jsa == null) {
            this.size = new Dimension(22, 22);
        }
        else {
            this.size = new Dimension(this.jsa.getWidth(this) + 2, this.jsa.getHeight(this) + 2);
        }
        this.addMouseListener(this);
    }
    
    public t(final String s, final String s2, final boolean bja) {
        this(s, s2);
        this.nsa = true;
        this.Bja = bja;
    }
    
    public t(final String s, final String s2, final dj osa, final boolean bja) {
        this(s, s2);
        this.nsa = true;
        this.osa = osa;
        this.Bja = bja;
        if (osa != null) {
            this.osa.b(this);
            this.osa._(this);
        }
    }
    
    public String a() {
        return this.ksa;
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        this.msa = AWTEventMulticaster.add(this.msa, actionListener);
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        this.msa = AWTEventMulticaster.remove(this.msa, actionListener);
    }
    
    public boolean getState() {
        return this.Bja;
    }
    
    public void setState(final boolean bja) {
        if (bja || this.osa == null) {
            this.Bja = bja;
            this.repaint();
            if (this.osa != null) {
                this.osa._(this);
            }
        }
    }
    
    public void f(final boolean bja) {
        this.Bja = bja;
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this.isEnabled()) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        if (this.nsa && this.Bja) {
            this.lsa = false;
        }
        else {
            this.lsa = true;
        }
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.isEnabled()) {
            return;
        }
        this.lsa = false;
        if (!this.Bja || this.osa == null) {
            this.Bja = !this.Bja;
        }
        this.repaint();
        if (this.osa != null) {
            this.osa._(this);
        }
        if (this.msa != null) {
            this.msa.actionPerformed(new ActionEvent(this, 1001, ""));
        }
    }
    
    public Dimension getMinimumSize() {
        return this.size;
    }
    
    public void paint(final Graphics graphics) {
        if (this.ela) {
            final Dimension size = this.getSize();
            if (this.Ija == null || this.Hja == null || this.Gja == null || size.width != this.Gja.width || size.height != this.Gja.height) {
                if (this.Hja != null) {
                    this.Hja.dispose();
                }
                if (this.Ija != null) {
                    this.Ija.flush();
                }
                this.Ija = this.createImage(size.width, size.height);
                this.Hja = this.Ija.getGraphics();
                this.Gja = size;
            }
            this._(this.Hja);
            graphics.drawImage(this.Ija, 0, 0, this);
        }
        else {
            this._(graphics);
        }
        super.paint(graphics);
    }
    
    public void _(final Graphics graphics) {
        if (this.psa == null || this.qsa == null) {
            this.psa = this.getBackground().brighter();
            this.qsa = this.getBackground().darker();
        }
        if (this.lsa || (this.nsa && this.Bja)) {
            graphics.setColor(this.psa);
            graphics.fillRect(1, 1, this.size.width - 2, this.size.height - 2);
            if (this.jsa != null) {
                graphics.drawImage(this.jsa, 2, 2, this);
            }
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(1, 1, this.size.width - 3, this.size.height - 3, false);
        }
        else {
            graphics.setColor(this.getBackground());
            graphics.fillRect(1, 1, this.size.width - 2, this.size.height - 2);
            if (this.jsa != null) {
                graphics.drawImage(this.jsa, 1, 1, this);
            }
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(1, 1, this.size.width - 3, this.size.height - 3, true);
        }
        if (!this.isEnabled()) {
            graphics.setColor(this.qsa);
            for (int i = 2; i <= this.size.height - 2; i += 3) {
                graphics.drawLine(1, i, this.size.width - 2, i);
            }
        }
        graphics.setColor(this.rma);
        graphics.drawRoundRect(0, 0, this.size.width - 1, this.size.height - 1, 2, 2);
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.repaint();
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        this.psa = null;
        this.qsa = null;
    }
    
    public void f(final Color rma) {
        if (rma != null) {
            this.rma = rma;
        }
    }
    
    public void dispose() {
        if (this.jsa != null) {
            this.jsa.flush();
            this.jsa = null;
        }
    }
    
    static {
        rsa = new ej();
        ssa = new MediaTracker(t.rsa);
    }
}
