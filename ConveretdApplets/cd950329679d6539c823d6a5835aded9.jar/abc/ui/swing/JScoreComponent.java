// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import abc.notation.MusicElement;
import java.awt.geom.Point2D;
import java.awt.Point;
import abc.notation.Tune;
import java.io.IOException;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.io.File;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JComponent;

public class JScoreComponent extends JComponent
{
    private static final Color SELECTED_ITEM_COLOR;
    protected JTune m_jTune;
    protected Dimension m_dimension;
    private int XOffset;
    protected ScoreMetrics m_metrics;
    protected BufferedImage m_bufferedImage;
    protected Graphics2D m_bufferedImageGfx;
    protected boolean m_isBufferedImageOutdated;
    protected float m_size;
    protected boolean m_isJustified;
    protected JScoreElement m_selectedItem;
    protected int staffLinesSpacing;
    
    public JScoreComponent() {
        this.m_jTune = null;
        this.m_dimension = null;
        this.XOffset = 0;
        this.m_metrics = null;
        this.m_bufferedImage = null;
        this.m_bufferedImageGfx = null;
        this.m_isBufferedImageOutdated = true;
        this.m_size = 45.0f;
        this.m_isJustified = false;
        this.m_selectedItem = null;
        this.staffLinesSpacing = -1;
        this.m_dimension = new Dimension(1, 1);
        this.initGfx();
    }
    
    protected void initGfx() {
        this.m_bufferedImage = new BufferedImage((int)this.m_dimension.getWidth(), (int)this.m_dimension.getHeight(), 2);
        this.m_bufferedImageGfx = this.m_bufferedImage.createGraphics();
        this.m_metrics = new ScoreMetrics(this.m_bufferedImageGfx, this.m_size);
        this.staffLinesSpacing = (int)(this.m_metrics.getStaffCharBounds().getHeight() * 2.5);
    }
    
    protected void drawIn(final Graphics2D g) {
        if (this.m_jTune != null) {
            this.m_jTune.render(g);
        }
    }
    
    public void paint(final Graphics g) {
        if (this.m_isBufferedImageOutdated) {
            if (this.m_bufferedImage == null || this.m_dimension.getWidth() > this.m_bufferedImage.getWidth() || this.m_dimension.getHeight() > this.m_bufferedImage.getHeight()) {
                this.initGfx();
            }
            this.m_bufferedImageGfx.setColor(this.getBackground());
            this.m_bufferedImageGfx.fillRect(0, 0, this.m_bufferedImage.getWidth(), this.m_bufferedImage.getHeight());
            this.drawIn(this.m_bufferedImageGfx);
            this.m_isBufferedImageOutdated = false;
        }
        ((Graphics2D)g).drawImage(this.m_bufferedImage, 0, 0, null);
    }
    
    public void setSize(final float size) {
        this.m_size = size;
        this.initGfx();
        if (this.m_jTune != null) {
            this.setTune(this.m_jTune.getTune());
        }
        this.repaint();
    }
    
    public void writeScoreTo(final File file) throws IOException {
        this.initGfx();
        this.m_bufferedImageGfx.setColor(this.getBackground());
        this.m_bufferedImageGfx.setComposite(AlphaComposite.Clear);
        this.m_bufferedImageGfx.fillRect(0, 0, this.m_bufferedImage.getWidth(), this.m_bufferedImage.getHeight());
        this.m_bufferedImageGfx.setComposite(AlphaComposite.SrcOver);
        this.drawIn(this.m_bufferedImageGfx);
        this.m_isBufferedImageOutdated = false;
        ImageIO.write(this.m_bufferedImage, "png", file);
    }
    
    public void setTune(final Tune tune) {
        if (this.m_metrics == null) {
            this.m_metrics = new ScoreMetrics((Graphics2D)this.getGraphics());
        }
        (this.m_jTune = new JTune(tune, new Point(this.XOffset, 0), this.m_metrics, this.m_isJustified)).setStaffLinesSpacing(this.staffLinesSpacing);
        this.m_selectedItem = null;
        this.m_dimension.setSize(this.m_jTune.getWidth(), this.m_jTune.getHeight());
        this.setPreferredSize(this.m_dimension);
        this.setSize(this.m_dimension);
        this.m_isBufferedImageOutdated = true;
        this.repaint();
    }
    
    public void setJustification(final boolean isJustified) {
        this.m_isJustified = isJustified;
        if (this.m_jTune != null) {
            this.setTune(this.m_jTune.getTune());
        }
    }
    
    public void setStaffLinesSpacing(final int spacing) {
        this.staffLinesSpacing = spacing;
        if (this.m_jTune != null) {
            this.setTune(this.m_jTune.getTune());
        }
    }
    
    public int getStaffLinesSpacing() {
        return this.staffLinesSpacing;
    }
    
    public boolean isJustified() {
        return this.m_isJustified;
    }
    
    public JScoreElement getScoreElementAt(final Point location) {
        if (this.m_jTune != null) {
            return this.m_jTune.getScoreElementAt(location);
        }
        return null;
    }
    
    public void setSelectedItem(final MusicElement elmnt) {
        JScoreElementAbstract r = null;
        if (elmnt != null) {
            r = this.m_jTune.getRenditionObjectsMapping().get(elmnt);
        }
        this.setSelectedItem(r);
    }
    
    public void setSelectedItem(final JScoreElement elmnt) {
        if (this.m_selectedItem != null) {
            this.m_bufferedImageGfx.setColor(Color.BLACK);
            ((JScoreElementAbstract)this.m_selectedItem).render(this.m_bufferedImageGfx);
        }
        if (elmnt != null) {
            this.m_selectedItem = elmnt;
            this.m_bufferedImageGfx.setColor(JScoreComponent.SELECTED_ITEM_COLOR);
            ((JScoreElementAbstract)this.m_selectedItem).render(this.m_bufferedImageGfx);
        }
        this.repaint();
    }
    
    public JScoreElement getRenditionElementFor(final MusicElement elmnt) {
        if (this.m_jTune != null) {
            return this.m_jTune.getRenditionObjectsMapping().get(elmnt);
        }
        return null;
    }
    
    static {
        SELECTED_ITEM_COLOR = Color.RED;
    }
}
