// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.scene;

import java.awt.Graphics2D;
import java.awt.Graphics;
import com.masystem.beergame.ui.graphics.GraphicsTools;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import com.masystem.beergame.ui.component.swing.HasPaintProperties;
import java.awt.Dimension;
import java.util.Iterator;
import java.awt.Component;
import com.masystem.beergame.ui.component.swing.NeedsInitialization;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import com.masystem.beergame.ui.component.swing.ImageComponent;
import javax.swing.JComponent;
import java.util.List;

public class Node
{
    private List<Node> children;
    protected Node parent;
    private PaintProperties paintProperties;
    private JComponent displayedComponent;
    private JComponent component;
    private ImageComponent cacheComponent;
    private boolean cacheEnabled;
    protected Object id;
    private int order;
    private float width;
    private float height;
    private float pivotX;
    private float pivotY;
    private float x;
    private float y;
    private float scalingX;
    private float scalingY;
    private float originScalingX;
    private float originScalingY;
    private int originOrder;
    private Rectangle displayBounds;
    private boolean visible;
    private boolean isSetup;
    
    public Node() {
        this(null);
    }
    
    public Node(final JComponent component) {
        this(component, 0);
    }
    
    private Node(final JComponent component, final int order) {
        this.displayBounds = new Rectangle();
        this.paintProperties = new PaintProperties();
        this.order = order;
        this.scalingX = 1.0f;
        this.scalingY = 1.0f;
        this.pivotX = 0.5f;
        this.pivotY = 0.5f;
        this.originScalingX = 1.0f;
        this.originScalingY = 1.0f;
        this.visible = true;
        this.setComponent(component);
    }
    
    public void onSetup() {
    }
    
    public void onTearDown() {
    }
    
    public JComponent getComponent() {
        return this.component;
    }
    
    public final void setComponent(final JComponent component) {
        if (component == null || component != this.component) {
            if ((this.component = component) != null) {
                component.setLayout(null);
            }
            if (!this.cacheEnabled) {
                this.setDisplayedComponent(component);
                if (component != null) {
                    this.setPreferredSize();
                }
                else {
                    this.setSize(0.0f, 0.0f);
                }
            }
            if (component instanceof NeedsInitialization) {
                ((NeedsInitialization)component).initialize();
            }
        }
    }
    
    private void setDisplayedComponent(final JComponent displayedComponent) {
        final int index;
        if (this.parent != null && (index = this.parent.children.indexOf(this)) != -1) {
            this.parent.getComponent().remove(this.displayedComponent);
            this.parent.getComponent().add(displayedComponent, this.parent.children.size() - 1 - index);
            displayedComponent.repaint();
        }
        this.displayedComponent = displayedComponent;
        this.setPaintProperties(this.paintProperties);
        this.updateVisibility();
    }
    
    public final void setId(final Object id) {
        this.id = id;
    }
    
    public final Node getById(final Object... array) {
        return this.getByIdPath(0, array);
    }
    
    private Node getByIdPath(int n, final Object... array) {
    Label_0000:
        while (array != null && array[n] != null) {
            if (n < array.length) {
                final Object o;
                if ((o = array[n]) == null) {
                    throw new IllegalArgumentException("Cannot get null id.");
                }
                if (this.children != null) {
                    for (final Node node : this.children) {
                        if (o.equals(node.id)) {
                            if (n == array.length - 1) {
                                return node;
                            }
                            final Node node2 = node;
                            ++n;
                            this = node2;
                            continue Label_0000;
                        }
                    }
                }
            }
            return null;
        }
        throw new IllegalArgumentException("Cannot get null id.");
    }
    
    public final Node findById(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Cannot find null id.");
        }
        if (o.equals(this.id)) {
            return this;
        }
        if (this.children != null) {
            final Iterator<Node> iterator = this.children.iterator();
            while (iterator.hasNext()) {
                final Node byId;
                if ((byId = iterator.next().findById(o)) != null) {
                    return byId;
                }
            }
        }
        return null;
    }
    
    public final Node findAncestorById(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Cannot find null id.");
        }
        if (o.equals(this.id)) {
            return this;
        }
        Node node;
        for (node = this.parent; node != null && !o.equals(node.id); node = node.parent) {}
        return node;
    }
    
    public final Node getParent() {
        return this.parent;
    }
    
    public final void setPivot(final float n, final float n2) {
        this.pivotX = 0.0f;
        this.pivotY = 0.5f;
        this.refreshDisplayBounds();
    }
    
    public final float getWidth() {
        return this.width + 0.0f + 0.0f;
    }
    
    public final float getHeight() {
        return this.height + 0.0f + 0.0f;
    }
    
    public void setSize(final float n, final float n2) {
        this.doSetSize(n, n2);
    }
    
    public void setPreferredSize() {
        this.doSetSize(-2.0f, -2.0f);
    }
    
    public void setSizeRelativeToParent(final float n, final float n2) {
        this.setSizeRelativeTo(this.parent, n, n2);
    }
    
    public void setSizeRelativeTo(final Node node, float n, final float n2) {
        n = ((n != -2.0f) ? ((n != -1.0f) ? relativeToNodeWidth(n, node) : -1.0f) : -2.0f);
        this.doSetSize(n, (n2 != -2.0f) ? ((n2 != -1.0f) ? relativeToNodeHeight(n2, node) : -1.0f) : -2.0f);
    }
    
    private void doSetSize(float width, float height) {
        if (width != -1.0f || height != -1.0f) {
            if (width == -2.0f || height == -2.0f) {
                final Dimension preferredSize = this.displayedComponent.getPreferredSize();
                if (width == -2.0f) {
                    width = preferredSize.width;
                }
                if (height == -2.0f) {
                    height = preferredSize.height;
                }
            }
            if (width != this.width || height != this.height) {
                if (width != -1.0f) {
                    this.width = width;
                }
                if (height != -1.0f) {
                    this.height = height;
                }
                this.refreshDisplayBounds();
            }
        }
    }
    
    public final float getX() {
        return this.x;
    }
    
    public final float getY() {
        return this.y;
    }
    
    public final void setPosition(final float n, final float n2) {
        this.doSetPosition(n, n2);
    }
    
    public final void setPositionRelativeToParent(float n, float n2) {
        n = ((n != -1.0f) ? relativeToNodeWidth(n, this.parent) : -1.0f);
        n2 = ((n2 != -1.0f) ? relativeToNodeHeight(n2, this.parent) : -1.0f);
        this.doSetPosition(n, n2);
    }
    
    public final void setPositionRelativeToScene(final float n, final float n2) {
        this.setPositionRelativeTo(Scene.getCurrentScene(), n, n2);
    }
    
    public final void setPositionRelativeTo(final Node node, float n, final float n2) {
        n = ((n != -1.0f) ? (node.getLeft() + relativeToNodeWidth(n, node)) : -1.0f);
        this.doSetPosition(n, (n2 != -1.0f) ? (node.getTop() + relativeToNodeHeight(n2, node)) : -1.0f);
    }
    
    private void doSetPosition(final float x, final float y) {
        final boolean b = x != -1.0f;
        final boolean b2 = y != -1.0f;
        if ((b || b2) && (x != this.x || y != this.y)) {
            if (b) {
                this.x = x;
            }
            if (b2) {
                this.y = y;
            }
            this.refreshDisplayBounds();
        }
    }
    
    public final float getLeft() {
        return this.x - this.pivotX * this.getWidth();
    }
    
    public final float getTop() {
        return this.y - this.pivotY * this.getHeight();
    }
    
    public final float getRight() {
        return this.getLeft() + this.getWidth();
    }
    
    public final float getBottom() {
        return this.getTop() + this.getHeight();
    }
    
    public final void setScaling(final float n) {
        if (n != -1.0f) {
            this.scalingX = n;
        }
        if (n != -1.0f) {
            this.scalingY = n;
        }
        this.refreshDisplayBounds();
    }
    
    public final void setOrder(final int order) {
        if (order != this.order) {
            this.order = order;
            final Node parent;
            if ((parent = this.parent) != null) {
                parent.removeChild(this);
                parent.addChild(this);
            }
        }
    }
    
    public final boolean isOpaque() {
        return this.displayedComponent.isOpaque();
    }
    
    public final void setOpaque(final boolean opaque) {
        if (opaque != this.displayedComponent.isOpaque()) {
            this.displayedComponent.setOpaque(opaque);
        }
    }
    
    public final boolean isVisible() {
        return this.displayedComponent.isVisible();
    }
    
    public final void setVisible(final boolean visible) {
        if (visible != this.visible) {
            this.visible = visible;
            this.updateVisibility();
        }
    }
    
    public final float getAlpha() {
        return this.paintProperties.getAlpha();
    }
    
    public final void setAlpha(final float alpha) {
        if (alpha != this.paintProperties.getAlpha()) {
            this.paintProperties.setAlpha(alpha);
            this.updateVisibility();
            this.displayedComponent.repaint();
        }
    }
    
    public final int getFiltering() {
        return this.paintProperties.getFiltering();
    }
    
    public final void setFiltering(final int filtering) {
        if (filtering != this.paintProperties.getFiltering()) {
            this.paintProperties.setFiltering(filtering);
            this.displayedComponent.repaint();
        }
    }
    
    public final PaintProperties getPaintProperties() {
        return this.paintProperties;
    }
    
    public final void setPaintProperties(final PaintProperties paintProperties) {
        this.paintProperties = paintProperties;
        if (this.displayedComponent instanceof HasPaintProperties) {
            ((HasPaintProperties)this.displayedComponent).setPaintProperties(paintProperties);
            this.displayedComponent.repaint();
        }
    }
    
    private void updateVisibility() {
        if (this.displayedComponent != null) {
            this.displayedComponent.setVisible(this.visible && this.paintProperties.getAlpha() >= 0.00390625f);
        }
    }
    
    public final int getNbrChildren() {
        if (this.children != null) {
            return this.children.size();
        }
        return 0;
    }
    
    public final Node getChild(final int n) {
        return this.children.get(n);
    }
    
    public final void addChild(final Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Cannot add null child.");
        }
        if (node == this) {
            throw new IllegalArgumentException("Cannot add a node to itself.");
        }
        if (node.parent != null) {
            throw new IllegalStateException("Node already has a parent. " + node + ": " + node.parent);
        }
        if (this.children == null) {
            this.children = new ArrayList<Node>();
        }
        final int addNodeSorted = addNodeSorted(node, this.children);
        node.parent = this;
        final JComponent displayedComponent;
        if ((displayedComponent = node.displayedComponent) != null) {
            this.getComponent().add(displayedComponent, this.children.size() - 1 - addNodeSorted);
            this.getComponent().repaint();
        }
        if (node.width == 0.0f && node.height == 0.0f) {
            node.setSizeRelativeToParent(1.0f, 1.0f);
        }
        if (node.x == 0.0f && node.y == 0.0f) {
            node.setPositionRelativeToParent(0.5f, 0.5f);
        }
        node.updateForParent();
        if (!node.isSetup) {
            node.onSetup();
            node.isSetup = true;
        }
    }
    
    public final void removeChild(final Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Cannot remove null child.");
        }
        if (node.parent == this) {
            this.children.remove(node);
            node.parent = null;
            this.getComponent().remove(node.displayedComponent);
            this.getComponent().repaint();
        }
    }
    
    public final void removeAllChildren() {
        if (this.children != null) {
            final Iterator<Node> iterator = this.children.iterator();
            while (iterator.hasNext()) {
                final Node node;
                (node = iterator.next()).parent = null;
                this.getComponent().remove(node.displayedComponent);
            }
            this.children.clear();
            this.getComponent().repaint();
        }
    }
    
    public final boolean isCacheEnabled() {
        return this.cacheEnabled;
    }
    
    public void setCacheEnabled(final boolean cacheEnabled) {
        if (cacheEnabled != this.cacheEnabled) {
            this.cacheEnabled = cacheEnabled;
            if (cacheEnabled) {
                if (this.cacheEnabled) {
                    if (this.cacheComponent == null) {
                        this.cacheComponent = new ImageComponent();
                    }
                    if ((int)this.width == this.cacheComponent.getWidth() || (int)this.height == this.cacheComponent.getHeight()) {
                        this.cacheComponent.setImage(this.toImage(this.cacheComponent.getImage()));
                    }
                    else {
                        this.cacheComponent.setImage(this.toImage(null));
                    }
                }
                this.setDisplayedComponent(this.cacheComponent);
            }
            else {
                this.setDisplayedComponent(this.component);
            }
            this.refreshDisplayBounds();
        }
    }
    
    public final void destroyCache() {
        this.setCacheEnabled(false);
        this.cacheComponent = null;
    }
    
    public final BufferedImage toImage() {
        return this.toImage(null);
    }
    
    private BufferedImage toImage(BufferedImage optimizedImage) {
        if (optimizedImage == null) {
            int n = 3;
            if (this.component != null && this.component.isOpaque()) {
                n = 1;
            }
            optimizedImage = GraphicsTools.createOptimizedImage((int)this.width, (int)this.height, n);
        }
        final Graphics2D graphics;
        final Graphics2D graphics2D = graphics = optimizedImage.createGraphics();
        final JComponent displayedComponent;
        if ((displayedComponent = this.displayedComponent) != null) {
            graphics.translate(0, 0);
            displayedComponent.paint(graphics);
            graphics.translate(0, 0);
        }
        graphics2D.dispose();
        return optimizedImage;
    }
    
    private void updateForParent() {
        final Node parent = this.parent;
        if (this.parent != null) {
            this.originScalingX = parent.originScalingX * parent.scalingX;
            this.originScalingY = parent.originScalingY * parent.scalingY;
            this.originOrder = parent.originOrder + parent.order;
            this.refreshDisplayBounds();
        }
    }
    
    public final Rectangle getDisplayBounds() {
        return this.displayBounds;
    }
    
    protected void refreshDisplayBounds() {
        final float n = this.width * this.originScalingX * this.scalingX;
        final float n2 = this.height * this.originScalingY * this.scalingY;
        final float n3 = (this.x + 0.0f + -this.pivotX * this.getWidth() * this.scalingX) * this.originScalingX;
        final float n4 = (this.y + 0.0f + -this.pivotY * this.getHeight() * this.scalingY) * this.originScalingY;
        final Rectangle displayBounds;
        (displayBounds = this.displayBounds).x = Math.round(n3);
        displayBounds.y = Math.round(n4);
        displayBounds.width = Math.round(n3 + n) - Math.round(n3);
        displayBounds.height = Math.round(n4 + n2) - Math.round(n4);
        if (this.displayedComponent != null) {
            this.displayedComponent.setBounds(this.displayBounds);
        }
        if (this.children != null) {
            final Iterator<Node> iterator = this.children.iterator();
            while (iterator.hasNext()) {
                iterator.next().updateForParent();
            }
        }
    }
    
    private static float relativeToNodeWidth(final float n, final Node node) {
        if (node != null) {
            return n * node.getWidth();
        }
        return 0.0f;
    }
    
    private static float relativeToNodeHeight(final float n, final Node node) {
        if (node != null) {
            return n * node.getHeight();
        }
        return 0.0f;
    }
    
    @Override
    public String toString() {
        if (this.id == null) {
            return super.toString();
        }
        return this.id + " (" + super.toString() + ")";
    }
    
    private static int addNodeSorted(final Node node, final List<Node> list) {
        final int order = node.order;
        for (int i = list.size() - 1; i >= 0; --i) {
            if (order - list.get(i).order >= 0) {
                list.add(i + 1, node);
                return i + 1;
            }
        }
        list.add(0, node);
        return 0;
    }
}
