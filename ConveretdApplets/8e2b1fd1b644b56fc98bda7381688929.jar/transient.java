import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.AWTEventMulticaster;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
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

public final class transient extends Component implements MouseListener, MouseMotionListener
{
    private Image zwa;
    private String Awa;
    private boolean Bwa;
    ActionListener Cwa;
    private Dimension size;
    private boolean Dwa;
    private boolean pqa;
    private hea Ewa;
    private Color Fwa;
    private Color Gwa;
    private Color Csa;
    private boolean Qqa;
    private Dimension ea;
    private Graphics fa;
    private Image ga;
    
    public transient(final String s, final String awa) {
        this.Bwa = false;
        this.Dwa = false;
        this.pqa = false;
        this.Ewa = null;
        this.Fwa = null;
        this.Gwa = null;
        this.Csa = Color.black;
        this.Qqa = false;
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.getClass().getResourceAsStream(s));
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
            int read;
            while ((read = bufferedInputStream.read()) != -1) {
                byteArrayOutputStream.write(read);
            }
            this.zwa = Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray());
            bufferedInputStream.close();
            byteArrayOutputStream.close();
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.zwa, 1);
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.Awa = awa;
        if (this.zwa == null) {
            this.size = new Dimension(22, 22);
        }
        else {
            this.size = new Dimension(this.zwa.getWidth(this) + 2, this.zwa.getHeight(this) + 2);
        }
        this.addMouseListener(this);
    }
    
    public transient(final String s, final String s2, final boolean pqa) {
        this(s, s2);
        this.Dwa = true;
        this.pqa = pqa;
    }
    
    public transient(final String s, final String s2, final hea ewa, final boolean pqa) {
        this(s, s2);
        this.Dwa = true;
        this.Ewa = ewa;
        this.pqa = pqa;
        if (ewa != null) {
            this.Ewa.b(this);
            this.Ewa._(this);
        }
    }
    
    public String a() {
        return this.Awa;
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        this.Cwa = AWTEventMulticaster.add(this.Cwa, actionListener);
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        this.Cwa = AWTEventMulticaster.remove(this.Cwa, actionListener);
    }
    
    public boolean getState() {
        return this.pqa;
    }
    
    public void setState(final boolean pqa) {
        if (pqa || this.Ewa == null) {
            this.pqa = pqa;
            this.repaint();
            if (this.Ewa != null) {
                this.Ewa._(this);
            }
        }
    }
    
    public void l(final boolean pqa) {
        this.pqa = pqa;
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
        if (this.Dwa && this.pqa) {
            this.Bwa = false;
        }
        else {
            this.Bwa = true;
        }
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.isEnabled()) {
            return;
        }
        this.Bwa = false;
        if (!this.pqa || this.Ewa == null) {
            this.pqa = !this.pqa;
        }
        this.repaint();
        if (this.Ewa != null) {
            this.Ewa._(this);
        }
        if (this.Cwa != null) {
            this.Cwa.actionPerformed(new ActionEvent(this, 1001, ""));
        }
    }
    
    public Dimension getMinimumSize() {
        return this.size;
    }
    
    public void paint(final Graphics graphics) {
        if (this.Qqa) {
            final Dimension size = this.getSize();
            if (this.ga == null || this.fa == null || this.ea == null || size.width != this.ea.width || size.height != this.ea.height) {
                if (this.fa != null) {
                    this.fa.dispose();
                }
                if (this.ga != null) {
                    this.ga.flush();
                }
                this.ga = this.createImage(size.width, size.height);
                this.fa = this.ga.getGraphics();
                this.ea = size;
            }
            this._(this.fa);
            graphics.drawImage(this.ga, 0, 0, this);
        }
        else {
            this._(graphics);
        }
        super.paint(graphics);
    }
    
    public void _(final Graphics graphics) {
        if (this.Fwa == null || this.Gwa == null) {
            this.Fwa = this.getBackground().brighter();
            this.Gwa = this.getBackground().darker();
        }
        if (this.Bwa || (this.Dwa && this.pqa)) {
            graphics.setColor(this.Fwa);
            graphics.fillRect(1, 1, this.size.width - 2, this.size.height - 2);
            if (this.zwa != null) {
                graphics.drawImage(this.zwa, 2, 2, this);
            }
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(1, 1, this.size.width - 3, this.size.height - 3, false);
        }
        else {
            graphics.setColor(this.getBackground());
            graphics.fillRect(1, 1, this.size.width - 2, this.size.height - 2);
            if (this.zwa != null) {
                graphics.drawImage(this.zwa, 1, 1, this);
            }
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(1, 1, this.size.width - 3, this.size.height - 3, true);
        }
        if (!this.isEnabled()) {
            graphics.setColor(this.Gwa);
            for (int i = 2; i <= this.size.height - 2; i += 3) {
                graphics.drawLine(1, i, this.size.width - 2, i);
            }
        }
        graphics.setColor(this.Csa);
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
        this.Fwa = null;
        this.Gwa = null;
    }
    
    public void e(final Color csa) {
        if (csa != null) {
            this.Csa = csa;
        }
    }
    
    public void dispose() {
        if (this.zwa != null) {
            this.zwa.flush();
            this.zwa = null;
        }
    }
}
