// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.event.ChangeEvent;

public abstract class AbstractModel implements Model
{
    protected Isometry viewMatrix;
    private Isometry inversViewMatrix;
    protected ModelPoint orientationRoot;
    protected Isometry isom1;
    protected Isometry isom2;
    protected Isometry isom3;
    protected ModelPoint z1;
    protected ModelPoint z2;
    protected ModelPoint z3;
    protected ModelPoint z4;
    protected ModelPoint zero;
    protected transient ChangeEvent changeEvent;
    protected EventListenerList listenerList;
    static /* synthetic */ Class class$javax$swing$event$ChangeListener;
    
    public AbstractModel() {
        this.isom1 = this.getIdentity();
        this.isom2 = this.getIdentity();
        this.isom3 = this.getIdentity();
        this.z1 = this.getOrigin();
        this.z2 = this.getOrigin();
        this.z3 = this.getOrigin();
        this.z4 = this.getOrigin();
        this.zero = this.getOrigin();
        this.changeEvent = null;
        this.listenerList = new EventListenerList();
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        this.listenerList.add((AbstractModel.class$javax$swing$event$ChangeListener == null) ? (AbstractModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")) : AbstractModel.class$javax$swing$event$ChangeListener, changeListener);
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listenerList.remove((AbstractModel.class$javax$swing$event$ChangeListener == null) ? (AbstractModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")) : AbstractModel.class$javax$swing$event$ChangeListener, changeListener);
    }
    
    public ChangeListener[] getChangeListeners() {
        return this.listenerList.getListeners((Class<ChangeListener>)((AbstractModel.class$javax$swing$event$ChangeListener == null) ? (AbstractModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")) : AbstractModel.class$javax$swing$event$ChangeListener));
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractModel.class$javax$swing$event$ChangeListener == null) ? (AbstractModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")) : AbstractModel.class$javax$swing$event$ChangeListener)) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    public Isometry getInversViewMatrix() {
        return this.inversViewMatrix;
    }
    
    public Isometry getViewMatrix() {
        return this.viewMatrix;
    }
    
    public void setViewMatrix(final Isometry viewMatrix) {
        if (viewMatrix != null) {
            this.viewMatrix = viewMatrix;
            this.inversViewMatrix = this.viewMatrix.getInvers();
        }
        else {
            this.viewMatrix = this.getIdentity();
            this.inversViewMatrix = this.getIdentity();
        }
        this.fireStateChanged();
    }
    
    public void setOrientationRoot(final ModelPoint orientationRoot) {
        this.orientationRoot = orientationRoot;
        this.setViewMatrix(this.getViewMatrix());
    }
    
    public Isometry getIdentity() {
        return this.getRotation(0.0);
    }
    
    public Isometry getRotation(final ModelPoint modelPoint, final double n) {
        final Isometry translation = this.getTranslation(modelPoint);
        final Isometry isometry = (Isometry)translation.clone();
        translation.invert();
        isometry.multiplyRight(this.getRotation(n));
        isometry.multiplyRight(translation);
        return isometry;
    }
    
    public Isometry getTranslation(final ModelPoint modelPoint) {
        final Isometry identity = this.getIdentity();
        this.getTranslation(identity, modelPoint);
        return identity;
    }
    
    public void getTranslation(final Isometry isometry, final ModelPoint modelPoint) {
        this.getTranslation(isometry, modelPoint, 1.0);
    }
    
    public void getTranslation(final Isometry isometry, final ModelPoint modelPoint, final ModelPoint modelPoint2) {
        this.getTranslation(isometry, modelPoint, modelPoint2, 1.0);
    }
    
    public Isometry getTranslation(final ModelPoint modelPoint, final ModelPoint modelPoint2) {
        final Isometry identity = this.getIdentity();
        this.getTranslation(identity, modelPoint, modelPoint2);
        return identity;
    }
    
    public Isometry getTranslation(final ModelPoint modelPoint, final double n) {
        final Isometry identity = this.getIdentity();
        this.getTranslation(identity, modelPoint, n);
        return identity;
    }
    
    public Isometry getTranslation(final ModelPoint modelPoint, final ModelPoint modelPoint2, final double n) {
        final Isometry identity = this.getIdentity();
        this.getTranslation(identity, modelPoint, modelPoint2, n);
        return identity;
    }
    
    public void getTranslation(final Isometry isometry, final ModelPoint modelPoint, final ModelPoint to, final double n) {
        this.z1.setTo(to);
        try {
            this.getTranslation(this.isom1, modelPoint);
            isometry.setTo(this.isom1);
            this.isom1.invert();
            this.isom1.apply(this.z1);
            this.getTranslation(this.isom2, this.z1, n);
            this.isom2.multiplyRight(this.isom1);
            isometry.multiplyRight(this.isom2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(modelPoint);
            System.out.println(to);
            System.out.println(n);
            System.out.println(this.isom1);
            System.out.println(this.isom2);
            System.out.println(isometry);
        }
    }
    
    public void getTranslation(final Isometry isometry, final ModelVector modelVector, final double n) {
        this.getTranslation(isometry, modelVector.getBase(), this.exp(modelVector, n));
    }
    
    public Isometry getTranslation(final ModelVector modelVector, final double n) {
        final Isometry identity = this.getIdentity();
        this.getTranslation(identity, modelVector, n);
        return identity;
    }
    
    public double dist2(final ModelPoint modelPoint) {
        final double dist = this.dist(modelPoint);
        return dist * dist;
    }
    
    public double dist(final ModelPoint modelPoint) {
        return Math.sqrt(this.dist2(modelPoint));
    }
    
    public double dist(final ModelPoint modelPoint, final ModelPoint modelPoint2) {
        return Math.sqrt(this.dist2(modelPoint, modelPoint2));
    }
    
    public double dist2(final ModelPoint modelPoint, final ModelPoint modelPoint2) {
        final double dist = this.dist(modelPoint, modelPoint2);
        return dist * dist;
    }
    
    public double length2(final ModelVector modelVector) {
        return this.product(modelVector, modelVector);
    }
    
    public double length(final ModelVector modelVector) {
        return Math.sqrt(this.length2(modelVector));
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
