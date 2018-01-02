// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui;

import java.beans.PropertyChangeListener;
import java.awt.event.MouseEvent;
import java.awt.Polygon;
import ch.randelshofer.util.Arrays;
import ch.randelshofer.geom3d.Face3D;
import ch.randelshofer.geom3d.Transform3D;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import ch.randelshofer.gui.event.ChangeEvent;
import ch.randelshofer.geom3d.DefaultTransform3DModel;
import java.awt.Color;
import java.beans.PropertyChangeSupport;
import java.awt.Insets;
import java.util.Vector;
import ch.randelshofer.geom3d.Point3D;
import ch.randelshofer.geom3d.Transform3DModel;
import java.awt.Image;
import java.awt.Graphics;
import ch.randelshofer.geom3d.SceneNode;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import ch.randelshofer.gui.event.ChangeListener;
import java.awt.Canvas;

public class Canvas3DAWT extends Canvas implements ChangeListener, MouseListener, MouseMotionListener
{
    private Dimension preferredSize;
    protected SceneNode scene;
    protected Graphics backGfx;
    protected Image backImg;
    protected Dimension backSize;
    protected Transform3DModel transformModel;
    protected int prevx;
    protected int prevy;
    private Object lock;
    protected Point3D observer;
    protected Point3D lightSource;
    protected double ambientLightIntensity;
    protected double lightSourceIntensity;
    private Image backgroundImage;
    private boolean isArmed;
    protected boolean isAdjusting;
    private int unpaintedStates;
    private boolean isMouseDrag;
    protected double scaleFactor;
    protected Vector activeFaces;
    protected boolean isPopupTrigger;
    private boolean isRotateOnMouseDrag;
    protected Insets paintInsets;
    private PropertyChangeSupport changeSupport;
    
    public Canvas3DAWT() {
        this.preferredSize = new Dimension(200, 200);
        this.backSize = new Dimension(0, 0);
        this.lock = new Object();
        this.observer = new Point3D(0.0, 0.0, 260.0);
        this.lightSource = new Point3D(-500.0, 500.0, 1000.0);
        this.ambientLightIntensity = 0.6;
        this.lightSourceIntensity = 1.0;
        this.isArmed = true;
        this.unpaintedStates = 1;
        this.isMouseDrag = false;
        this.scaleFactor = 1.0;
        this.activeFaces = new Vector();
        this.isRotateOnMouseDrag = false;
        this.paintInsets = new Insets(0, 0, 0, 0);
        this.addMouseListener(this);
        this.setBackground(Color.white);
        this.setRotateOnMouseDrag(true);
        this.setTransformModel(new DefaultTransform3DModel());
    }
    
    public void setTransformModel(final Transform3DModel transformModel) {
        final Transform3DModel transformModel2 = this.transformModel;
        if (transformModel2 != null) {
            transformModel2.removeChangeListener(this);
        }
        (this.transformModel = transformModel).addChangeListener(this);
        this.stateChanged(null);
        this.firePropertyChange("transformModel", transformModel2, transformModel);
    }
    
    public Transform3DModel getTransformModel() {
        return this.transformModel;
    }
    
    public void setRotateOnMouseDrag(final boolean isRotateOnMouseDrag) {
        if (isRotateOnMouseDrag != this.isRotateOnMouseDrag) {
            this.isRotateOnMouseDrag = isRotateOnMouseDrag;
            if (isRotateOnMouseDrag) {
                this.addMouseMotionListener(this);
            }
            else {
                this.removeMouseMotionListener(this);
            }
        }
    }
    
    public void setPaintInsets(final int top, final int left, final int bottom, final int right) {
        if (this.paintInsets == null) {
            this.paintInsets = new Insets(top, left, bottom, right);
        }
        else {
            this.paintInsets.top = top;
            this.paintInsets.left = left;
            this.paintInsets.bottom = bottom;
            this.paintInsets.right = right;
        }
    }
    
    public void setSyncObject(final Object lock) {
        this.lock = lock;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.backGfx == null || this.backSize.width != size.width || this.backSize.height != size.height) {
            if (size.width <= 0 || size.height <= 0) {
                return;
            }
            this.createBackGraphics(size);
            this.backSize = size;
            this.unpaintedStates = 1;
        }
        synchronized (this.lock) {
            if (this.unpaintedStates > 0) {
                this.unpaintedStates = 0;
                this.paintBackground(this.backGfx);
                this.paint3D(this.backGfx);
            }
        }
        graphics.drawImage(this.backImg, 0, 0, this);
    }
    
    protected void createBackGraphics(final Dimension dimension) {
        this.backImg = this.createImage(dimension.width, dimension.height);
        this.backGfx = this.backImg.getGraphics();
    }
    
    public void setToIdentity() {
        this.transformModel.setToIdentity();
    }
    
    public void setObserver(final float n) {
        this.observer = new Point3D(0.0, 0.0, n);
    }
    
    public void setAmbientLightIntensity(final double ambientLightIntensity) {
        this.ambientLightIntensity = ambientLightIntensity;
    }
    
    public void setLightSourceIntensity(final double lightSourceIntensity) {
        this.lightSourceIntensity = lightSourceIntensity;
    }
    
    public void setLightSource(final Point3D lightSource) {
        this.lightSource = lightSource;
    }
    
    public void setBackgroundImage(final Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(backgroundImage, 0);
        mediaTracker.checkID(0, true);
    }
    
    public void setTransform(final Transform3D transform) {
        this.transformModel.setTransform(transform);
    }
    
    public Transform3D getTransform() {
        return this.transformModel.getTransform();
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        ++this.unpaintedStates;
        if ((n & 0x40) != 0x0 && image == this.backgroundImage) {
            this.backgroundImage = null;
        }
        return super.imageUpdate(image, n, n2, n3, n4, n5);
    }
    
    public void setScaleFactor(final double scaleFactor) {
        this.scaleFactor = scaleFactor;
        this.stateChanged(null);
    }
    
    public double getScaleFactor() {
        return this.scaleFactor;
    }
    
    public void setScene(final SceneNode scene) {
        this.scene = scene;
        this.stateChanged(null);
    }
    
    private void paintBackground(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.backSize.width, this.backSize.height);
        if (this.backgroundImage != null) {
            graphics.drawImage(this.backgroundImage, 0, 0, this.backSize.width, this.backSize.height, this);
        }
    }
    
    protected void paint3D(final Graphics graphics) {
        if (this.scene == null) {
            return;
        }
        final Transform3D transform = this.transformModel.getTransform();
        final int n = this.size().width / 2;
        final int n2 = this.size().height / 2;
        final double n3 = Math.min(n, n2) * this.scaleFactor;
        final double n4 = -n3;
        final Vector vector = new Vector();
        this.activeFaces.removeAllElements();
        this.scene.addVisibleFaces(vector, transform, this.observer);
        final Face3D[] array = new Face3D[vector.size()];
        vector.copyInto(array);
        Arrays.sort(array);
        int[] array2 = new int[5];
        int[] array3 = new int[5];
        final double x = this.observer.x;
        final double y = this.observer.y;
        final double z = this.observer.z;
        for (int i = 0; i < array.length; ++i) {
            final Face3D face3D = array[i];
            final float[] coords = face3D.getCoords();
            final int[] vertices = face3D.getVertices();
            if (array2.length < vertices.length + 1) {
                array2 = new int[vertices.length + 1];
                array3 = new int[vertices.length + 1];
            }
            for (int j = 0; j < vertices.length; ++j) {
                final int n5 = vertices[j] * 3;
                final double n6 = coords[vertices[j] * 3 + 2] - z;
                if (n6 != 0.0) {
                    array2[j] = n + (int)((x - (z * coords[n5] - x) / n6) * n3);
                    array3[j] = n2 + (int)((y - (z * coords[n5 + 1] - y) / n6) * n4);
                }
                else {
                    array2[j] = n + (int)(x * n3);
                    array3[j] = n2 + (int)(y * n4);
                }
            }
            final Color fillColor;
            if ((fillColor = face3D.getFillColor()) != null) {
                double brightness;
                if (this.lightSource == null) {
                    brightness = 1.0;
                }
                else {
                    brightness = face3D.getBrightness(this.lightSource, this.lightSourceIntensity, this.ambientLightIntensity);
                }
                graphics.setColor(new Color(Math.min(255, (int)(fillColor.getRed() * brightness)), Math.min(255, (int)(fillColor.getGreen() * brightness)), Math.min(255, (int)(fillColor.getBlue() * brightness))));
                graphics.fillPolygon(array2, array3, vertices.length);
            }
            final Color borderColor;
            if ((borderColor = face3D.getBorderColor()) != null) {
                graphics.setColor(borderColor);
                array2[vertices.length] = array2[0];
                array3[vertices.length] = array3[0];
                graphics.drawPolygon(array2, array3, vertices.length + 1);
            }
            if (face3D.getAction() != null) {
                this.activeFaces.addElement(new Polygon(array2, array3, vertices.length));
                this.activeFaces.addElement(face3D);
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.isEnabled() && !this.isPopupTrigger) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            this.prevx = x;
            this.prevy = y;
            for (int i = this.activeFaces.size() - 2; i >= 0; i -= 2) {
                final Polygon polygon = this.activeFaces.elementAt(i);
                final Face3D face3D = this.activeFaces.elementAt(i + 1);
                if (polygon.contains(x, y)) {
                    face3D.handleEvent(mouseEvent);
                    break;
                }
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.isArmed = true;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.isArmed = false;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.isPopupTrigger = mouseEvent.isPopupTrigger();
        if (this.isEnabled() && !this.isPopupTrigger) {
            this.isAdjusting = true;
            this.prevx = mouseEvent.getX();
            this.prevy = mouseEvent.getY();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.isAdjusting) {
            this.isAdjusting = false;
            this.stateChanged(null);
        }
        this.isPopupTrigger |= mouseEvent.isPopupTrigger();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.isPopupTrigger = false;
        if (this.isAdjusting && this.isArmed && this.isEnabled()) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            final Dimension size = this.getSize();
            this.transformModel.rotate((this.prevy - y) * (6.283185307179586 / size.width), (this.prevx - x) * (6.283185307179586 / size.height), 0.0);
            this.prevx = x;
            this.prevy = y;
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.isPopupTrigger = false;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        ++this.unpaintedStates;
        if (this.unpaintedStates == 1 || this.unpaintedStates > 10) {
            this.repaint();
        }
    }
    
    public void setPreferredSize(final Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }
    
    public Dimension getPreferredSize() {
        return (this.preferredSize != null) ? this.preferredSize : super.getPreferredSize();
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (propertyChangeListener == null) {
            return;
        }
        if (this.changeSupport == null) {
            this.changeSupport = new PropertyChangeSupport(this);
        }
        this.changeSupport.addPropertyChangeListener(propertyChangeListener);
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        if (propertyChangeListener == null || this.changeSupport == null) {
            return;
        }
        this.changeSupport.removePropertyChangeListener(propertyChangeListener);
    }
    
    protected void firePropertyChange(final String s, final Object o, final Object o2) {
        final PropertyChangeSupport changeSupport = this.changeSupport;
        if (changeSupport == null) {
            return;
        }
        changeSupport.firePropertyChange(s, o, o2);
    }
}
