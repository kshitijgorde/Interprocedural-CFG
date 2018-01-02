// 
// Decompiled by Procyon v0.5.30
// 

package gravity.cosmos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gravity.tools.DoubleGraphics;
import gravity.gameObjects.TraceDot;
import gravity.tools.Vector2D;
import gravity.gameObjects.Probe;
import gravity.gameObjects.GameObject;
import gravity.tools.AssertionException;
import gravity.tools.Assert;
import java.util.Enumeration;
import java.util.Vector;
import gravity.ConstantsLibrary;
import java.util.Observable;

public class Cosmos extends Observable
{
    private ConstantsLibrary _cl;
    private Vector _matter;
    private Vector _traceDots;
    private Vector _lastRun;
    private Vector _actionListeners;
    private Enumeration _update;
    private Enumeration _updateZoomOut;
    private int _traceDotColor;
    private RunningProbes _runningProbes;
    
    public Cosmos(final ConstantsLibrary cl) throws AssertionException {
        this._traceDotColor = -1;
        Assert.assert(cl != null);
        this._cl = cl;
        this._matter = new Vector();
        this._traceDots = new Vector();
        this._lastRun = new Vector();
        this._update = this._lastRun.elements();
        this._updateZoomOut = this._lastRun.elements();
        this._actionListeners = new Vector();
        try {
            this._runningProbes = new RunningProbes(this, this._cl);
        }
        catch (AssertionException ex) {}
    }
    
    public void add(final GameObject gameObject) {
        this._matter.addElement(gameObject);
    }
    
    public Enumeration getMatter() {
        return this._matter.elements();
    }
    
    public void remove(final GameObject gameObject) {
        this._matter.removeElement(gameObject);
        this.tellActionListeners("repaint");
    }
    
    public synchronized void runProbe(final Probe probe) {
        if (!this.isAProbeRunning()) {
            final Enumeration<Object> elements = this._lastRun.elements();
            while (elements.hasMoreElements()) {
                this._traceDots.addElement(elements.nextElement());
            }
            this._lastRun.removeAllElements();
            this._update = this._lastRun.elements();
            this._updateZoomOut = this._lastRun.elements();
        }
        if (++this._traceDotColor >= this._cl.TRACEDOTCOLORS.length) {
            this._traceDotColor = 0;
        }
        probe.setColor(this._cl.TRACEDOTCOLORS[this._traceDotColor]);
        this._runningProbes.add(probe);
    }
    
    public void killProbes() {
        this._runningProbes.killProbes();
    }
    
    public Vector2D forceOnVector2D(final Vector2D vector2D) {
        final Vector2D vector2D2 = new Vector2D();
        final Enumeration<GameObject> elements = (Enumeration<GameObject>)this._matter.elements();
        while (elements.hasMoreElements()) {
            try {
                final GameObject gameObject = elements.nextElement();
                final double n = gameObject.grav() * this._cl.GRAVCONSTANT / Math.pow(Math.max(gameObject.distance(new GameObject(vector2D)), 1.0E-5), 3.0);
                final Vector2D vector2D3 = vector2D2;
                vector2D3.x += (gameObject.getCoord().x - vector2D.x) * n;
                final Vector2D vector2D4 = vector2D2;
                vector2D4.y += (gameObject.getCoord().y - vector2D.y) * n;
            }
            catch (Exception ex) {
                return new Vector2D();
            }
        }
        return vector2D2;
    }
    
    public boolean objectTooNear(final GameObject gameObject) {
        final Enumeration<GameObject> elements = this._matter.elements();
        try {
            while (elements.hasMoreElements()) {
                if (gameObject.distance(elements.nextElement()) < 17.5) {
                    return true;
                }
            }
        }
        catch (Exception ex) {
            return true;
        }
        return false;
    }
    
    public boolean isAProbeRunning() {
        return this._runningProbes.isAProbeRunning();
    }
    
    public void addTraceDot(final Probe probe) {
        try {
            final TraceDot traceDot = new TraceDot(probe.getCoord());
            traceDot.setColor(probe.getColor());
            this._lastRun.addElement(traceDot);
        }
        catch (AssertionException ex) {}
        this.tellActionListeners("update");
    }
    
    public void paint(final DoubleGraphics doubleGraphics) {
        this.paintGameObjectVector(this._traceDots, doubleGraphics);
        this._update = this._lastRun.elements();
        this.update(doubleGraphics);
    }
    
    public void update(final DoubleGraphics doubleGraphics) {
        while (this._update.hasMoreElements()) {
            try {
                this._update.nextElement().draw(doubleGraphics);
            }
            catch (Exception ex) {}
        }
        this.paintGameObjectVector(this._matter, doubleGraphics);
    }
    
    private void paintGameObjectVector(final Vector vector, final DoubleGraphics doubleGraphics) {
        final Enumeration<GameObject> elements = vector.elements();
        while (elements.hasMoreElements()) {
            try {
                elements.nextElement().draw(doubleGraphics);
            }
            catch (Exception ex) {}
        }
    }
    
    public void zoomOut() {
        this.tellActionListeners("zoomOut");
    }
    
    public void paintZoomOut(final DoubleGraphics doubleGraphics, final boolean b) {
        if (!b) {
            this._updateZoomOut = this._lastRun.elements();
        }
        while (this._updateZoomOut.hasMoreElements()) {
            try {
                final Vector2D coord = this._updateZoomOut.nextElement().getCoord();
                doubleGraphics.drawVector2D(coord.x, coord.y);
            }
            catch (Exception ex) {}
        }
    }
    
    public void refresh() {
        this._traceDots.removeAllElements();
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this._actionListeners.addElement(actionListener);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this._actionListeners.removeElement(actionListener);
    }
    
    public void tellActionListeners(final String s) {
        final Enumeration<ActionListener> elements = this._actionListeners.elements();
        while (elements.hasMoreElements()) {
            try {
                elements.nextElement().actionPerformed(new ActionEvent(this, 0, s));
            }
            catch (Exception ex) {}
        }
    }
    
    public void tellObservers(final Object o) {
        this.setChanged();
        this.notifyObservers(o);
    }
    
    protected void finalize() {
        this.killProbes();
    }
}
