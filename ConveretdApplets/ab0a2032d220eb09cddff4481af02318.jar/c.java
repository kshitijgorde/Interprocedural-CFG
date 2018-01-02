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

public class c extends Component implements MouseListener, MouseMotionListener
{
    private Image fJb;
    private String gJb;
    private boolean hJb;
    ActionListener iJb;
    private Dimension size;
    private boolean jJb;
    private boolean Ha;
    private Uo kJb;
    private Color lJb;
    private Color mJb;
    private Color la;
    private boolean kIb;
    private Dimension MDb;
    private Graphics NDb;
    private Image ODb;
    
    public c(final String s, final String gJb) {
        this.hJb = false;
        this.jJb = false;
        this.Ha = false;
        this.kJb = null;
        this.lJb = null;
        this.mJb = null;
        this.la = Color.black;
        this.kIb = false;
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.getClass().getResourceAsStream(s));
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
            int read;
            while ((read = bufferedInputStream.read()) != -1) {
                byteArrayOutputStream.write(read);
            }
            this.fJb = Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray());
            bufferedInputStream.close();
            byteArrayOutputStream.close();
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.fJb, 1);
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.gJb = gJb;
        if (this.fJb == null) {
            this.size = new Dimension(22, 22);
        }
        else {
            this.size = new Dimension(this.fJb.getWidth(this) + 2, this.fJb.getHeight(this) + 2);
        }
        this.addMouseListener(this);
    }
    
    public c(final String s, final String s2, final boolean ha) {
        this(s, s2);
        this.jJb = true;
        this.Ha = ha;
    }
    
    public c(final String s, final String s2, final Uo kJb, final boolean ha) {
        this(s, s2);
        this.jJb = true;
        this.kJb = kJb;
        this.Ha = ha;
        kJb.b(this);
        if (kJb != null) {
            kJb._(this);
        }
    }
    
    public String j() {
        return this.gJb;
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        this.iJb = AWTEventMulticaster.add(this.iJb, actionListener);
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        this.iJb = AWTEventMulticaster.remove(this.iJb, actionListener);
    }
    
    public boolean getState() {
        return this.Ha;
    }
    
    public void setState(final boolean ha) {
        if (ha || this.kJb == null) {
            this.Ha = ha;
            this.repaint();
            if (this.kJb != null) {
                this.kJb._(this);
            }
        }
    }
    
    public void F(final boolean ha) {
        this.Ha = ha;
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
        if (this.jJb && this.Ha) {
            this.hJb = false;
        }
        else {
            this.hJb = true;
        }
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.isEnabled()) {
            return;
        }
        this.hJb = false;
        if (!this.Ha || this.kJb == null) {
            this.Ha = !this.Ha;
        }
        this.repaint();
        if (this.kJb != null) {
            this.kJb._(this);
        }
        if (this.iJb != null) {
            this.iJb.actionPerformed(new ActionEvent(this, 1001, ""));
        }
    }
    
    public Dimension getMinimumSize() {
        return this.size;
    }
    
    public void paint(final Graphics graphics) {
        if (this.kIb) {
            final Dimension size = this.getSize();
            if (this.ODb == null || this.NDb == null || this.MDb == null || size.width != this.MDb.width || size.height != this.MDb.height) {
                if (this.NDb != null) {
                    this.NDb.dispose();
                }
                if (this.ODb != null) {
                    this.ODb.flush();
                }
                this.ODb = this.createImage(size.width, size.height);
                this.NDb = this.ODb.getGraphics();
                this.MDb = size;
            }
            this.g(this.NDb);
            graphics.drawImage(this.ODb, 0, 0, this);
        }
        else {
            this.g(graphics);
        }
        super.paint(graphics);
    }
    
    public void g(final Graphics graphics) {
        if (this.lJb == null || this.mJb == null) {
            this.lJb = this.getBackground().brighter();
            this.mJb = this.getBackground().darker();
        }
        if (this.hJb || (this.jJb && this.Ha)) {
            graphics.setColor(this.lJb);
            graphics.fillRect(1, 1, this.size.width - 2, this.size.height - 2);
            if (this.fJb != null) {
                graphics.drawImage(this.fJb, 2, 2, this);
            }
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(1, 1, this.size.width - 3, this.size.height - 3, false);
        }
        else {
            graphics.setColor(this.getBackground());
            graphics.fillRect(1, 1, this.size.width - 2, this.size.height - 2);
            if (this.fJb != null) {
                graphics.drawImage(this.fJb, 1, 1, this);
            }
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(1, 1, this.size.width - 3, this.size.height - 3, true);
        }
        if (!this.isEnabled()) {
            graphics.setColor(this.mJb);
            for (int i = 2; i <= this.size.height - 2; i += 3) {
                graphics.drawLine(1, i, this.size.width - 2, i);
            }
        }
        graphics.setColor(this.la);
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
        this.lJb = null;
        this.mJb = null;
    }
    
    public void a(final Color la) {
        if (la != null) {
            this.la = la;
        }
    }
    
    public void dispose() {
        if (this.fJb != null) {
            this.fJb.flush();
            this.fJb = null;
        }
    }
}
