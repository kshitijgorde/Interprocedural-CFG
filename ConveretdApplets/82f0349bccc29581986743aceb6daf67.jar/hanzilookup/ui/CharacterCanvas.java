// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.ui;

import java.util.EventObject;
import java.util.Iterator;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.util.LinkedHashSet;
import java.awt.BasicStroke;
import java.util.Set;
import java.awt.Stroke;
import javax.swing.event.MouseInputListener;
import javax.swing.JLabel;

public class CharacterCanvas extends JLabel implements MouseInputListener
{
    private WrittenCharacter inputCharacter;
    private WrittenCharacter.WrittenStroke currentStroke;
    private WrittenCharacter.WrittenPoint previousPoint;
    private Stroke paintStroke;
    private Set strokesListeners;
    private static final double MIN_STROKE_SEGMENT_LENGTH = 5.0;
    
    public CharacterCanvas() {
        this.inputCharacter = new WrittenCharacter();
        this.paintStroke = new BasicStroke(3.0f, 1, 1);
        this.strokesListeners = new LinkedHashSet();
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void clear() {
        this.inputCharacter.clear();
        this.currentStroke = null;
    }
    
    public void undo() {
        final List strokesList = this.inputCharacter.getStrokeList();
        if (strokesList.size() > 0) {
            strokesList.remove(strokesList.size() - 1);
        }
    }
    
    public WrittenCharacter getCharacter() {
        return this.inputCharacter;
    }
    
    public void mousePressed(final MouseEvent e) {
        if (SwingUtilities.getRoot(this).isFocusOwner()) {
            this.requestFocus();
        }
        this.previousPoint = this.inputCharacter.new WrittenPoint(e.getX(), e.getY());
    }
    
    public void mouseDragged(final MouseEvent e) {
        final WrittenCharacter.WrittenPoint nextPoint = this.inputCharacter.new WrittenPoint(e.getX(), e.getY());
        if (this.previousPoint != null && this.previousPoint.distance(nextPoint) >= 5.0) {
            if (this.currentStroke == null) {
                (this.currentStroke = this.inputCharacter.new WrittenStroke()).addPoint(this.previousPoint);
            }
            this.currentStroke.addPoint(nextPoint);
            this.previousPoint = nextPoint;
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
        this.mouseDragged(e);
        if (this.currentStroke != null) {
            this.inputCharacter.addStroke(this.currentStroke);
            this.previousPoint = null;
            this.currentStroke = null;
            this.repaint();
        }
        this.notifyStrokesListeners();
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2D = (Graphics2D)g;
        g2D.setStroke(this.paintStroke);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.currentStroke != null) {
            this.paintStroke(this.currentStroke, g);
        }
        this.paintCharacter(g);
    }
    
    protected void paintCharacter(final Graphics g) {
        final List strokeList = this.inputCharacter.getStrokeList();
        for (final WrittenCharacter.WrittenStroke nextStroke : strokeList) {
            this.paintStroke(nextStroke, g);
        }
    }
    
    protected void paintStroke(final WrittenCharacter.WrittenStroke stroke, final Graphics g) {
        g.setColor(Color.BLACK);
        final Iterator pointIter = stroke.getPointList().iterator();
        WrittenCharacter.WrittenPoint previousPoint = pointIter.next();
        while (pointIter.hasNext()) {
            final WrittenCharacter.WrittenPoint nextPoint = pointIter.next();
            g.drawLine((int)previousPoint.getX(), (int)previousPoint.getY(), (int)nextPoint.getX(), (int)nextPoint.getY());
            previousPoint = nextPoint;
        }
    }
    
    public void addStrokesListener(final StrokesListener listener) {
        if (listener != null) {
            synchronized (this.strokesListeners) {
                this.strokesListeners.add(listener);
            }
            // monitorexit(this.strokesListeners)
        }
    }
    
    public void removeStrokesListener(final StrokesListener listener) {
        if (listener != null) {
            synchronized (this.strokesListeners) {
                this.strokesListeners.remove(listener);
            }
            // monitorexit(this.strokesListeners)
        }
    }
    
    private void notifyStrokesListeners() {
        synchronized (this.strokesListeners) {
            for (final StrokesListener nextListener : this.strokesListeners) {
                nextListener.strokeFinished(new StrokeEvent((StrokeEvent)null));
            }
        }
        // monitorexit(this.strokesListeners)
    }
    
    public class StrokeEvent extends EventObject
    {
        private StrokeEvent() {
            super(CharacterCanvas.this);
        }
    }
    
    public interface StrokesListener
    {
        void strokeFinished(final StrokeEvent p0);
    }
}
