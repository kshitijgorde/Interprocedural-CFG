// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

import java.util.Iterator;
import java.util.ArrayList;

public class ForceSimulator
{
    private ArrayList items;
    private ArrayList springs;
    private Force[] iforces;
    private Force[] sforces;
    private int iflen;
    private int sflen;
    private Integrator integrator;
    private float speedLimit;
    
    public ForceSimulator() {
        this(new RungeKuttaIntegrator());
    }
    
    public ForceSimulator(final Integrator integrator) {
        this.speedLimit = 1.0f;
        this.integrator = integrator;
        this.iforces = new Force[5];
        this.sforces = new Force[5];
        this.iflen = 0;
        this.sflen = 0;
        this.items = new ArrayList();
        this.springs = new ArrayList();
    }
    
    public float getSpeedLimit() {
        return this.speedLimit;
    }
    
    public void setSpeedLimit(final float speedLimit) {
        this.speedLimit = speedLimit;
    }
    
    public Integrator getIntegrator() {
        return this.integrator;
    }
    
    public void setIntegrator(final Integrator integrator) {
        this.integrator = integrator;
    }
    
    public void clear() {
        this.items.clear();
        final Iterator<Spring> iterator = (Iterator<Spring>)this.springs.iterator();
        final Spring.SpringFactory factory = Spring.getFactory();
        while (iterator.hasNext()) {
            factory.reclaim(iterator.next());
        }
        this.springs.clear();
    }
    
    public void addForce(final Force force) {
        if (force.isItemForce()) {
            if (this.iforces.length == this.iflen) {
                final Force[] iforces = new Force[this.iflen + 10];
                System.arraycopy(this.iforces, 0, iforces, 0, this.iforces.length);
                this.iforces = iforces;
            }
            this.iforces[this.iflen++] = force;
        }
        if (force.isSpringForce()) {
            if (this.sforces.length == this.sflen) {
                final Force[] sforces = new Force[this.sflen + 10];
                System.arraycopy(this.sforces, 0, sforces, 0, this.sforces.length);
                this.sforces = sforces;
            }
            this.sforces[this.sflen++] = force;
        }
    }
    
    public Force[] getForces() {
        final Force[] array = new Force[this.iflen + this.sflen];
        System.arraycopy(this.iforces, 0, array, 0, this.iflen);
        System.arraycopy(this.sforces, 0, array, this.iflen, this.sflen);
        return array;
    }
    
    public void addItem(final ForceItem forceItem) {
        this.items.add(forceItem);
    }
    
    public boolean removeItem(final ForceItem forceItem) {
        return this.items.remove(forceItem);
    }
    
    public Iterator getItems() {
        return this.items.iterator();
    }
    
    public Spring addSpring(final ForceItem forceItem, final ForceItem forceItem2) {
        return this.addSpring(forceItem, forceItem2, -1.0f, -1.0f);
    }
    
    public Spring addSpring(final ForceItem forceItem, final ForceItem forceItem2, final float n) {
        return this.addSpring(forceItem, forceItem2, -1.0f, n);
    }
    
    public Spring addSpring(final ForceItem forceItem, final ForceItem forceItem2, final float n, final float n2) {
        if (forceItem == null || forceItem2 == null) {
            throw new IllegalArgumentException("ForceItems must be non-null");
        }
        final Spring spring = Spring.getFactory().getSpring(forceItem, forceItem2, n, n2);
        this.springs.add(spring);
        return spring;
    }
    
    public Iterator getSprings() {
        return this.springs.iterator();
    }
    
    public void runSimulator(final long n) {
        this.accumulate();
        this.integrator.integrate(this, n);
    }
    
    public void accumulate() {
        for (int i = 0; i < this.iflen; ++i) {
            this.iforces[i].init(this);
        }
        for (int j = 0; j < this.sflen; ++j) {
            this.sforces[j].init(this);
        }
        for (final ForceItem forceItem : this.items) {
            forceItem.force[0] = 0.0f;
            forceItem.force[1] = 0.0f;
            for (int k = 0; k < this.iflen; ++k) {
                this.iforces[k].getForce(forceItem);
            }
        }
        for (final Spring spring : this.springs) {
            for (int l = 0; l < this.sflen; ++l) {
                this.sforces[l].getForce(spring);
            }
        }
    }
}
