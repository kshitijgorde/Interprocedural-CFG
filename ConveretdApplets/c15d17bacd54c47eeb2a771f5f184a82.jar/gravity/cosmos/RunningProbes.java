// 
// Decompiled by Procyon v0.5.30
// 

package gravity.cosmos;

import gravity.gameObjects.Target;
import gravity.gameObjects.UFO;
import gravity.gameObjects.Planet;
import gravity.tools.Vector2D;
import java.util.Enumeration;
import gravity.gameObjects.GameObject;
import gravity.gameObjects.Probe;
import gravity.tools.AssertionException;
import gravity.tools.Assert;
import java.util.Vector;
import gravity.ConstantsLibrary;

public class RunningProbes implements Runnable
{
    private Cosmos _cosmos;
    private ConstantsLibrary _cl;
    private Vector _runningProbes;
    private Thread _runningTheProbes;
    
    public RunningProbes(final Cosmos cosmos, final ConstantsLibrary cl) throws AssertionException {
        this._runningProbes = new Vector();
        this._cosmos = cosmos;
        this._cl = cl;
        Assert.assert(this._cosmos != null && this._cl != null);
    }
    
    public void add(final Probe probe) {
        this._runningProbes.addElement(probe);
        if (this._runningTheProbes == null) {
            (this._runningTheProbes = new Thread(this)).start();
        }
    }
    
    public boolean isAProbeRunning() {
        return this._runningTheProbes != null;
    }
    
    public void run() {
        while (this._runningTheProbes != null) {
            final Enumeration elements = this._runningProbes.elements();
            while (elements.hasMoreElements() && this._runningTheProbes != null) {
                final Probe probe = elements.nextElement();
                this.moveProbe(probe);
                if (probe.getTicks() % 50 == 0 && probe.getMission().getMinTicksNr() > 0) {
                    this.sendIncomingData(probe);
                }
                if (!probe.isOnScreen(this._cl.VISIBLEORIGIN, this._cl.VISIBLEDIMENSION)) {
                    this._cosmos.zoomOut();
                    final Vector2D forceOnVector2D = this._cosmos.forceOnVector2D(probe.getCoord());
                    if (Math.pow(forceOnVector2D.x, 2.0) + Math.pow(forceOnVector2D.y, 2.0) < 5.0E-4) {
                        this.cancelProbe(probe);
                    }
                }
                if (this.localize(probe) && !probe.isLeaving()) {
                    this._cosmos.add(probe);
                    this.cancelProbe(probe);
                }
            }
            if (this._runningProbes.isEmpty()) {
                this._runningTheProbes = null;
            }
            try {
                Thread.sleep(5L);
            }
            catch (Exception ex) {}
        }
    }
    
    private void moveProbe(final Probe probe) {
        final Vector2D forceOnVector2D = this._cosmos.forceOnVector2D(probe.getCoord());
        final Vector2D speed2;
        final Vector2D speed = speed2 = probe.getSpeed();
        speed2.x += forceOnVector2D.x * 0.035;
        final Vector2D vector2D = speed;
        vector2D.y += forceOnVector2D.y * 0.035;
        try {
            probe.setSpeed(speed);
        }
        catch (AssertionException ex) {}
        probe.move(0.035);
        this._cosmos.addTraceDot(probe);
    }
    
    private boolean localize(final Probe probe) {
        boolean b = false;
        boolean b2 = true;
        GameObject gameObject = null;
        for (Enumeration matter = this._cosmos.getMatter(); matter.hasMoreElements() && !b; b |= probe.didHit(gameObject)) {
            try {
                gameObject = matter.nextElement();
            }
            catch (Exception ex) {}
            if (gameObject instanceof Planet) {
                try {
                    if (((Planet)gameObject).isInsideAtmosphere(probe) && probe.getMission().getMinPlanetsScannedNr() > 0) {
                        this.setBeingScanned(probe, gameObject);
                        b2 = false;
                    }
                }
                catch (AssertionException ex2) {}
            }
            if ((gameObject instanceof UFO || gameObject instanceof Target) && !probe.getTargetScanned() && probe.getMission().getScanTarget()) {
                try {
                    if (probe.distance(gameObject) < gameObject.getSize() * 3.0 / 2.0) {
                        this.setBeingScanned(probe, gameObject);
                        b2 = false;
                    }
                }
                catch (AssertionException ex3) {}
            }
        }
        if (b2) {
            this.setBeingScanned(probe, null);
        }
        return b;
    }
    
    private void setBeingScanned(final Probe probe, final GameObject gameObject) {
        final GameObject scan = probe.scan(gameObject);
        if (scan != gameObject) {
            if (scan instanceof Planet) {
                ((Planet)scan).setInsideAtmosphere(false);
                this._cosmos.tellActionListeners("update");
            }
            if (this._runningTheProbes == null) {
                return;
            }
            if (scan != null && probe.hitWhat() == null) {
                this.sendIncomingData(probe);
            }
            if (gameObject instanceof Planet) {
                ((Planet)gameObject).setInsideAtmosphere(true);
                this._cosmos.tellActionListeners("update");
            }
        }
    }
    
    private void sendIncomingData(final Probe probe) {
        this._cosmos.tellObservers(new ProbeIncomingData(probe.getScannedPlanetsNr(), probe.getTicks(), probe.getTargetScanned()));
    }
    
    private void cancelProbe(final Probe probe) {
        this._runningProbes.removeElement(probe);
        if (this._runningProbes.isEmpty()) {
            this._runningTheProbes = null;
        }
        this._cosmos.tellObservers(new ProbeUpdateData(probe.hitWhat(), probe.getScannedPlanetsNr(), probe.getTicks(), probe.getTargetScanned()));
        this.setBeingScanned(probe, null);
    }
    
    public void killProbes() {
        this._runningTheProbes = null;
        final Enumeration<Probe> elements = this._runningProbes.elements();
        while (elements.hasMoreElements()) {
            try {
                final Probe probe = elements.nextElement();
                probe.abort();
                this.cancelProbe(probe);
            }
            catch (Exception ex) {}
        }
        this._runningProbes.removeAllElements();
        final Enumeration matter = this._cosmos.getMatter();
        while (matter.hasMoreElements()) {
            final GameObject gameObject = matter.nextElement();
            if (gameObject instanceof Planet) {
                ((Planet)gameObject).setInsideAtmosphere(false);
            }
        }
        this._cosmos.tellActionListeners("update");
    }
}
