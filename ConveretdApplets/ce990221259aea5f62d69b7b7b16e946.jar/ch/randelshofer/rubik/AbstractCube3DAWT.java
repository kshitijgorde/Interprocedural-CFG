// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik;

import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import ch.randelshofer.gui.event.ChangeListener;
import java.util.Enumeration;
import java.util.Vector;
import ch.randelshofer.geom3d.SceneNode;
import ch.randelshofer.util.PooledSequentialDispatcherAWT;
import ch.randelshofer.gui.event.ChangeEvent;
import ch.randelshofer.gui.event.EventListenerList;
import ch.randelshofer.geom3d.TransformNode;
import ch.randelshofer.geom3d.Transform3D;
import ch.randelshofer.geom3d.Shape3D;
import java.awt.Color;

public abstract class AbstractCube3DAWT implements RubikListener
{
    public static final Color PART_FILL_COLOR;
    public static final Color CENTER_FILL_COLOR;
    public static final Color PART_BORDER_COLOR;
    protected Shape3D[] cornerShapes;
    protected Transform3D[] cornerIdentityTransforms;
    protected Shape3D[] edgeShapes;
    protected Transform3D[] edgeIdentityTransforms;
    protected Shape3D[] sideShapes;
    protected Transform3D[] sideIdentityTransforms;
    protected Shape3D centerShape;
    protected TransformNode sceneTransform;
    protected TransformNode[] cornerTransforms;
    protected TransformNode[] edgeTransforms;
    protected TransformNode[] sideTransforms;
    protected TransformNode centerTransform;
    RubiksCubeCore model;
    EventListenerList listenerList;
    protected ChangeEvent changeEvent;
    protected boolean isAnimated;
    protected PooledSequentialDispatcherAWT dispatcher;
    private boolean isLazy;
    private double explosion;
    public static final double[][] CORNER_EXPLODE_TRANSLATION;
    public static final double[][] EDGE_EXPLODE_TRANSLATION;
    public static final double[][] SIDE_EXPLODE_TRANSLATION;
    public static final int TWIST_MODE = 0;
    public static final int PARTS_MODE = 1;
    protected int mode;
    static /* synthetic */ Class class$ch$randelshofer$gui$event$ChangeListener;
    
    public AbstractCube3DAWT() {
        this.cornerShapes = new Shape3D[8];
        this.cornerIdentityTransforms = new Transform3D[8];
        this.edgeShapes = new Shape3D[12];
        this.edgeIdentityTransforms = new Transform3D[12];
        this.sideShapes = new Shape3D[6];
        this.sideIdentityTransforms = new Transform3D[6];
        this.cornerTransforms = new TransformNode[8];
        this.edgeTransforms = new TransformNode[12];
        this.sideTransforms = new TransformNode[6];
        this.model = new RubiksCubeCore();
        this.listenerList = new EventListenerList();
        this.explosion = 0.0;
        this.mode = 0;
        this.init();
    }
    
    private void computeTransformation() {
        synchronized (this.model) {
            for (int i = 0; i < this.sideTransforms.length; ++i) {
                final int sideLocation = this.model.getSideLocation(i);
                final Transform3D transform = (Transform3D)this.sideIdentityTransforms[sideLocation].clone();
                transform.translate(AbstractCube3DAWT.SIDE_EXPLODE_TRANSLATION[sideLocation][0] * this.explosion, AbstractCube3DAWT.SIDE_EXPLODE_TRANSLATION[sideLocation][1] * this.explosion, AbstractCube3DAWT.SIDE_EXPLODE_TRANSLATION[sideLocation][2] * this.explosion);
                this.sideTransforms[i].setTransform(transform);
            }
            for (int j = 0; j < this.edgeTransforms.length; ++j) {
                final int edgeLocation = this.model.getEdgeLocation(j);
                final Transform3D transform2 = this.edgeTransforms[j].getTransform();
                transform2.setToIdentity();
                if (this.model.getEdgeOrientation(j) == 1) {
                    transform2.rotateZ(3.141592653589793);
                    transform2.rotateX(1.5707963267948966);
                }
                final Transform3D transform3D = (Transform3D)this.edgeIdentityTransforms[edgeLocation].clone();
                transform3D.translate(AbstractCube3DAWT.EDGE_EXPLODE_TRANSLATION[edgeLocation][0] * this.explosion, AbstractCube3DAWT.EDGE_EXPLODE_TRANSLATION[edgeLocation][1] * this.explosion, AbstractCube3DAWT.EDGE_EXPLODE_TRANSLATION[edgeLocation][2] * this.explosion);
                transform2.concatenate(transform3D);
            }
            for (int k = 0; k < this.cornerTransforms.length; ++k) {
                final int cornerLocation = this.model.getCornerLocation(k);
                final Transform3D transform3 = this.cornerTransforms[k].getTransform();
                transform3.setToIdentity();
                switch (this.model.getCornerOrientation(k)) {
                    case 1: {
                        transform3.rotateZ(-1.5707963267948966);
                        transform3.rotateX(1.5707963267948966);
                        break;
                    }
                    case 2: {
                        transform3.rotate(-1.5707963267948966, 0.0, 1.5707963267948966);
                        break;
                    }
                }
                final Transform3D transform3D2 = (Transform3D)this.cornerIdentityTransforms[cornerLocation].clone();
                transform3D2.translate(AbstractCube3DAWT.CORNER_EXPLODE_TRANSLATION[cornerLocation][0] * this.explosion, AbstractCube3DAWT.CORNER_EXPLODE_TRANSLATION[cornerLocation][1] * this.explosion, AbstractCube3DAWT.CORNER_EXPLODE_TRANSLATION[cornerLocation][2] * this.explosion);
                transform3.concatenate(transform3D2);
            }
        }
    }
    
    public SceneNode getScene() {
        return this.sceneTransform;
    }
    
    public void update() {
        this.computeTransformation();
        this.fireStateChanged();
    }
    
    public void setModel(final RubiksCubeCore model) {
        if (this.model != null) {
            this.model.removeRubikListener(this);
        }
        this.model = model;
        if (this.model != null) {
            this.model.addRubikListener(this);
            this.update();
        }
    }
    
    public RubiksCubeCore getModel() {
        return this.model;
    }
    
    public void setCornerVisible(final int n, final boolean visible) {
        this.cornerShapes[n].setVisible(visible);
        this.fireStateChanged();
    }
    
    public void setEdgeVisible(final int n, final boolean visible) {
        this.edgeShapes[n].setVisible(visible);
        this.fireStateChanged();
    }
    
    public void setSideVisible(final int n, final boolean visible) {
        this.sideShapes[n].setVisible(visible);
        this.fireStateChanged();
    }
    
    public void setCenterVisible(final boolean visible) {
        this.centerShape.setVisible(visible);
        this.fireStateChanged();
    }
    
    public void setExplosion(final double n) {
        this.explosion = 27.0 * n;
        this.computeTransformation();
        this.fireStateChanged();
    }
    
    public double getExplosion() {
        return this.explosion / 27.0;
    }
    
    public abstract void setStickerColor(final int p0, final int p1, final Color p2);
    
    protected void init() {
        this.initCorners();
        this.initEdges();
        this.initSides();
        this.initCenter();
        this.initTransforms();
        this.initActions();
        this.setModel(new RubiksCubeCore());
    }
    
    protected abstract void initCorners();
    
    protected abstract void initEdges();
    
    protected abstract void initSides();
    
    protected abstract void initCenter();
    
    protected void initTransforms() {
        this.sceneTransform = new TransformNode();
        (this.cornerIdentityTransforms[0] = new Transform3D()).translate(-18.0, 18.0, 18.0);
        (this.cornerIdentityTransforms[1] = new Transform3D()).rotateZ(-1.5707963267948966);
        this.cornerIdentityTransforms[1].rotateX(1.5707963267948966);
        this.cornerIdentityTransforms[1].translate(-18.0, 18.0, 18.0);
        this.cornerIdentityTransforms[1].rotateZ(-1.5707963267948966);
        (this.cornerIdentityTransforms[2] = new Transform3D()).rotateZ(-1.5707963267948966);
        this.cornerIdentityTransforms[2].rotateX(1.5707963267948966);
        this.cornerIdentityTransforms[2].translate(-18.0, 18.0, 18.0);
        this.cornerIdentityTransforms[2].rotateZ(1.5707963267948966);
        (this.cornerIdentityTransforms[3] = new Transform3D()).translate(-18.0, 18.0, 18.0);
        this.cornerIdentityTransforms[3].rotateZ(3.141592653589793);
        (this.cornerIdentityTransforms[4] = new Transform3D()).translate(-18.0, 18.0, 18.0);
        this.cornerIdentityTransforms[4].rotateY(3.141592653589793);
        (this.cornerIdentityTransforms[5] = new Transform3D()).translate(-18.0, 18.0, 18.0);
        this.cornerIdentityTransforms[5].rotate(3.141592653589793, 1.5707963267948966, 0.0);
        (this.cornerIdentityTransforms[6] = new Transform3D()).rotate(-1.5707963267948966, 0.0, 1.5707963267948966);
        this.cornerIdentityTransforms[6].translate(-18.0, 18.0, 18.0);
        this.cornerIdentityTransforms[6].rotateX(1.5707963267948966);
        (this.cornerIdentityTransforms[7] = new Transform3D()).translate(-18.0, 18.0, 18.0);
        this.cornerIdentityTransforms[7].rotateX(3.141592653589793);
        (this.edgeIdentityTransforms[0] = new Transform3D()).rotateZ(3.141592653589793);
        this.edgeIdentityTransforms[0].rotateX(1.5707963267948966);
        this.edgeIdentityTransforms[0].translate(0.0, 18.0, 18.0);
        (this.edgeIdentityTransforms[1] = new Transform3D()).translate(0.0, 18.0, 18.0);
        this.edgeIdentityTransforms[1].rotateZ(-1.5707963267948966);
        (this.edgeIdentityTransforms[2] = new Transform3D()).rotateZ(3.141592653589793);
        this.edgeIdentityTransforms[2].rotateX(1.5707963267948966);
        this.edgeIdentityTransforms[2].translate(0.0, 18.0, 18.0);
        this.edgeIdentityTransforms[2].rotateZ(3.141592653589793);
        (this.edgeIdentityTransforms[3] = new Transform3D()).translate(0.0, 18.0, 18.0);
        this.edgeIdentityTransforms[3].rotate(0.0, -1.5707963267948966, 0.0);
        (this.edgeIdentityTransforms[4] = new Transform3D()).translate(0.0, 18.0, 18.0);
        this.edgeIdentityTransforms[4].rotateZ(1.5707963267948966);
        (this.edgeIdentityTransforms[5] = new Transform3D()).rotateZ(3.141592653589793);
        this.edgeIdentityTransforms[5].rotateX(1.5707963267948966);
        this.edgeIdentityTransforms[5].translate(0.0, 18.0, 18.0);
        this.edgeIdentityTransforms[5].rotate(0.0, -1.5707963267948966, 1.5707963267948966);
        (this.edgeIdentityTransforms[6] = new Transform3D()).rotateZ(3.141592653589793);
        this.edgeIdentityTransforms[6].rotateX(1.5707963267948966);
        this.edgeIdentityTransforms[6].translate(0.0, 18.0, 18.0);
        this.edgeIdentityTransforms[6].rotate(0.0, 3.141592653589793, 0.0);
        (this.edgeIdentityTransforms[7] = new Transform3D()).translate(0.0, 18.0, 18.0);
        this.edgeIdentityTransforms[7].rotate(0.0, 3.141592653589793, 1.5707963267948966);
        (this.edgeIdentityTransforms[8] = new Transform3D()).rotateZ(3.141592653589793);
        this.edgeIdentityTransforms[8].rotateX(1.5707963267948966);
        this.edgeIdentityTransforms[8].translate(0.0, 18.0, 18.0);
        this.edgeIdentityTransforms[8].rotate(3.141592653589793, 0.0, 0.0);
        (this.edgeIdentityTransforms[9] = new Transform3D()).translate(0.0, 18.0, 18.0);
        this.edgeIdentityTransforms[9].rotate(0.0, 1.5707963267948966, 0.0);
        (this.edgeIdentityTransforms[10] = new Transform3D()).translate(0.0, 18.0, 18.0);
        this.edgeIdentityTransforms[10].rotate(0.0, 3.141592653589793, -1.5707963267948966);
        (this.edgeIdentityTransforms[11] = new Transform3D()).translate(0.0, 18.0, 18.0);
        this.edgeIdentityTransforms[11].rotate(0.0, -1.5707963267948966, 3.141592653589793);
        (this.sideIdentityTransforms[0] = new Transform3D()).translate(0.0, 0.0, 18.0);
        (this.sideIdentityTransforms[1] = new Transform3D()).translate(0.0, 0.0, 18.0);
        this.sideIdentityTransforms[1].rotateY(-1.5707963267948966);
        (this.sideIdentityTransforms[2] = new Transform3D()).translate(0.0, 0.0, 18.0);
        this.sideIdentityTransforms[2].rotateX(-1.5707963267948966);
        (this.sideIdentityTransforms[3] = new Transform3D()).translate(0.0, 0.0, 18.0);
        this.sideIdentityTransforms[3].rotateY(3.141592653589793);
        (this.sideIdentityTransforms[4] = new Transform3D()).translate(0.0, 0.0, 18.0);
        this.sideIdentityTransforms[4].rotateY(1.5707963267948966);
        (this.sideIdentityTransforms[5] = new Transform3D()).translate(0.0, 0.0, 18.0);
        this.sideIdentityTransforms[5].rotateX(1.5707963267948966);
        for (int i = 0; i < 8; ++i) {
            (this.cornerTransforms[i] = new TransformNode()).addChild(this.cornerShapes[i]);
            this.sceneTransform.addChild(this.cornerTransforms[i]);
        }
        for (int j = 0; j < 12; ++j) {
            (this.edgeTransforms[j] = new TransformNode()).addChild(this.edgeShapes[j]);
            this.sceneTransform.addChild(this.edgeTransforms[j]);
        }
        for (int k = 0; k < 6; ++k) {
            (this.sideTransforms[k] = new TransformNode()).addChild(this.sideShapes[k]);
            this.sceneTransform.addChild(this.sideTransforms[k]);
        }
        (this.centerTransform = new TransformNode()).addChild(this.centerShape);
        this.sceneTransform.addChild(this.centerTransform);
    }
    
    protected abstract void initActions();
    
    public void rubikTwisted(final RubikEvent rubikEvent) {
        this.update();
    }
    
    public void rubikTwisting(final RubikEvent rubikEvent) {
        if (this.isAnimated) {
            this.animateTwist(rubikEvent);
        }
    }
    
    protected void animateTwist(final RubikEvent rubikEvent) {
        final Vector<TransformNode> vector = new Vector<TransformNode>();
        final Transform3D transform3D = new Transform3D();
        final int layerMask = rubikEvent.getLayerMask();
        final double n = rubikEvent.getAngle();
        final int n2 = (n == 2.0 || n == -2.0) ? 20 : 10;
        final double n3 = 1.5707963267948966 / n2 * n;
        switch (rubikEvent.getAxis()) {
            case 0: {
                transform3D.rotateX(n3);
                if ((layerMask & 0x1) == 0x1) {
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(0)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(1)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(6)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(7)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(1)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(9)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(10)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(11)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(4)]);
                }
                if ((layerMask & 0x2) == 0x2) {
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(0)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(2)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(6)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(8)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(0)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(2)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(3)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(5)]);
                    vector.addElement(this.centerTransform);
                }
                if ((layerMask & 0x4) == 0x4) {
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(2)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(3)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(4)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(5)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(3)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(4)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(5)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(7)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(1)]);
                    break;
                }
                break;
            }
            case 1: {
                transform3D.rotateY(n3);
                if ((layerMask & 0x1) == 0x1) {
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(1)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(3)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(5)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(7)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(2)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(5)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(8)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(11)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(2)]);
                }
                if ((layerMask & 0x2) == 0x2) {
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(1)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(4)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(7)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(10)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(0)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(1)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(3)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(4)]);
                    vector.addElement(this.centerTransform);
                }
                if ((layerMask & 0x4) == 0x4) {
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(0)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(2)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(4)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(6)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(0)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(3)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(6)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(9)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(5)]);
                    break;
                }
                break;
            }
            case 2: {
                transform3D.rotateZ(n3);
                if ((layerMask & 0x1) == 0x1) {
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(4)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(5)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(6)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(7)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(6)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(7)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(8)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(10)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(3)]);
                }
                if ((layerMask & 0x2) == 0x2) {
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(3)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(5)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(9)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(11)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(1)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(2)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(4)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(5)]);
                    vector.addElement(this.centerTransform);
                }
                if ((layerMask & 0x4) == 0x4) {
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(0)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(1)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(2)]);
                    vector.addElement(this.cornerTransforms[this.model.getCornerAt(3)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(0)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(1)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(2)]);
                    vector.addElement(this.edgeTransforms[this.model.getEdgeAt(4)]);
                    vector.addElement(this.sideTransforms[this.model.getSideAt(0)]);
                    break;
                }
                break;
            }
        }
        try {
            Thread.sleep(50L);
        }
        catch (InterruptedException ex) {}
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 1; i < n2; ++i) {
            synchronized (this.model) {
                final Enumeration<TransformNode> elements = vector.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().getTransform().concatenate(transform3D);
                }
            }
            this.fireStateChanged();
            currentTimeMillis += 50L;
            final long n4 = currentTimeMillis - System.currentTimeMillis();
            if (n4 > 0L) {
                try {
                    Thread.sleep(n4);
                }
                catch (InterruptedException ex2) {}
            }
            else {
                currentTimeMillis -= n4;
                Thread.yield();
            }
        }
        this.computeTransformation();
        this.fireStateChanged();
    }
    
    public void rubikChanged(final RubikEvent rubikEvent) {
        this.update();
    }
    
    public void rubikPartRotated(final RubikEvent rubikEvent) {
        this.update();
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        this.listenerList.add((AbstractCube3DAWT.class$ch$randelshofer$gui$event$ChangeListener == null) ? (AbstractCube3DAWT.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : AbstractCube3DAWT.class$ch$randelshofer$gui$event$ChangeListener, changeListener);
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listenerList.remove((AbstractCube3DAWT.class$ch$randelshofer$gui$event$ChangeListener == null) ? (AbstractCube3DAWT.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : AbstractCube3DAWT.class$ch$randelshofer$gui$event$ChangeListener, changeListener);
    }
    
    public void setAnimated(final boolean isAnimated) {
        this.isAnimated = isAnimated;
    }
    
    public boolean isAnimated() {
        return this.isAnimated;
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractCube3DAWT.class$ch$randelshofer$gui$event$ChangeListener == null) ? (AbstractCube3DAWT.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : AbstractCube3DAWT.class$ch$randelshofer$gui$event$ChangeListener)) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    public void setDispatcher(final PooledSequentialDispatcherAWT dispatcher) {
        this.dispatcher = dispatcher;
    }
    
    public PooledSequentialDispatcherAWT getDispatcher() {
        if (this.dispatcher == null) {
            this.dispatcher = new PooledSequentialDispatcherAWT();
        }
        return this.dispatcher;
    }
    
    public void setMode(final int mode) {
        switch (this.mode = mode) {
            case 0: {
                for (int i = 0; i < 8; ++i) {
                    final Shape3D shape3D = this.cornerShapes[i];
                    if (shape3D.isWireframe()) {
                        shape3D.setWireframe(false);
                        shape3D.setVisible(false);
                    }
                }
                for (int j = 0; j < 12; ++j) {
                    final Shape3D shape3D2 = this.edgeShapes[j];
                    if (shape3D2.isWireframe()) {
                        shape3D2.setWireframe(false);
                        shape3D2.setVisible(false);
                    }
                }
                for (int k = 0; k < 6; ++k) {
                    final Shape3D shape3D3 = this.sideShapes[k];
                    if (shape3D3.isWireframe()) {
                        shape3D3.setWireframe(false);
                        shape3D3.setVisible(false);
                    }
                }
                final Shape3D centerShape = this.centerShape;
                if (centerShape.isWireframe()) {
                    centerShape.setWireframe(false);
                    centerShape.setVisible(false);
                    break;
                }
                break;
            }
            case 1: {
                for (int l = 0; l < 8; ++l) {
                    final Shape3D shape3D4 = this.cornerShapes[l];
                    if (!shape3D4.isVisible()) {
                        shape3D4.setWireframe(true);
                        shape3D4.setVisible(true);
                    }
                }
                for (int n = 0; n < 12; ++n) {
                    final Shape3D shape3D5 = this.edgeShapes[n];
                    if (!shape3D5.isVisible()) {
                        shape3D5.setWireframe(true);
                        shape3D5.setVisible(true);
                    }
                }
                for (int n2 = 0; n2 < 6; ++n2) {
                    final Shape3D shape3D6 = this.sideShapes[n2];
                    if (!shape3D6.isVisible()) {
                        shape3D6.setWireframe(true);
                        shape3D6.setVisible(true);
                    }
                }
                final Shape3D centerShape2 = this.centerShape;
                if (!centerShape2.isVisible()) {
                    centerShape2.setWireframe(true);
                    centerShape2.setVisible(true);
                    break;
                }
                break;
            }
        }
        this.fireStateChanged();
    }
    
    public abstract String getName();
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        PART_FILL_COLOR = new Color(20, 20, 20);
        CENTER_FILL_COLOR = Color.white;
        PART_BORDER_COLOR = Color.black;
        CORNER_EXPLODE_TRANSLATION = new double[][] { { -1.0, 1.0, 1.0 }, { -1.0, -1.0, 1.0 }, { 1.0, 1.0, 1.0 }, { 1.0, -1.0, 1.0 }, { 1.0, 1.0, -1.0 }, { 1.0, -1.0, -1.0 }, { -1.0, 1.0, -1.0 }, { -1.0, -1.0, -1.0 } };
        EDGE_EXPLODE_TRANSLATION = new double[][] { { 0.0, 1.0, 1.0 }, { -1.0, 0.0, 1.0 }, { 0.0, -1.0, 1.0 }, { 1.0, 1.0, 0.0 }, { 1.0, 0.0, 1.0 }, { 1.0, -1.0, 0.0 }, { 0.0, 1.0, -1.0 }, { 1.0, 0.0, -1.0 }, { 0.0, -1.0, -1.0 }, { -1.0, 1.0, 0.0 }, { -1.0, 0.0, -1.0 }, { -1.0, -1.0, 0.0 } };
        SIDE_EXPLODE_TRANSLATION = new double[][] { { 0.0, 0.0, 1.0 }, { 1.0, 0.0, 0.0 }, { 0.0, -1.0, 0.0 }, { 0.0, 0.0, -1.0 }, { -1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 } };
    }
    
    class SideEvent implements Runnable
    {
        private int side;
        private boolean isClockwise;
        
        public SideEvent(final int side, final boolean isClockwise) {
            this.side = side;
            this.isClockwise = isClockwise;
        }
        
        public void run() {
            AbstractCube3DAWT.this.model.twistSide(this.side, this.isClockwise);
        }
    }
    
    class SideAction implements ActionListener
    {
        private int side;
        
        public SideAction(final int side) {
            this.side = side;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() instanceof MouseEvent && ((MouseEvent)actionEvent.getSource()).getClickCount() <= 1) {
                AbstractCube3DAWT.this.getDispatcher().dispatch(new SideEvent(AbstractCube3DAWT.this.model.getSideLocation(this.side), (actionEvent.getModifiers() & 0x9) != 0x0));
            }
        }
    }
    
    class EdgeEvent implements Runnable
    {
        private int side;
        private boolean isClockwise;
        
        public EdgeEvent(final int side, final boolean isClockwise) {
            this.side = side;
            this.isClockwise = isClockwise;
        }
        
        public void run() {
            AbstractCube3DAWT.this.model.twistEdge(this.side, this.isClockwise);
        }
    }
    
    class EdgeAction implements ActionListener
    {
        private int edge;
        private int orientation;
        
        public EdgeAction(final int edge, final int orientation) {
            this.edge = edge;
            this.orientation = orientation;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() instanceof MouseEvent && ((MouseEvent)actionEvent.getSource()).getClickCount() <= 1) {
                AbstractCube3DAWT.this.getDispatcher().dispatch(new EdgeEvent(AbstractCube3DAWT.this.model.getEdgeLayerSide(this.edge, this.orientation), (actionEvent.getModifiers() & 0x9) != 0x0));
            }
        }
    }
    
    class CrnrActn implements ActionListener
    {
        private int corner;
        private int orientation;
        
        public CrnrActn(final int corner, final int orientation) {
            this.corner = corner;
            this.orientation = orientation;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() instanceof MouseEvent && ((MouseEvent)actionEvent.getSource()).getClickCount() <= 1) {
                AbstractCube3DAWT.this.getDispatcher().dispatch(new SideEvent(AbstractCube3DAWT.this.model.getCornerSide(this.corner, this.orientation), (actionEvent.getModifiers() & 0x9) != 0x0));
            }
        }
    }
}
