// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import java.awt.Graphics;
import jfig.canvas.ObjectPainter;
import jfig.canvas.FigTrafo2D;
import java.awt.Point;

public class FigCompound extends FigBaseobject
{
    FigObjectList members;
    
    public void addMember(final FigObject figObject) {
        this.members.insert(figObject);
        this.update_bbox();
    }
    
    public void fastAddMember(final FigObject figObject) {
        this.members.insert(figObject);
    }
    
    public boolean deleteMember(final FigObject figObject) {
        final boolean delete = this.members.delete(figObject);
        if (delete) {
            this.update_bbox();
        }
        return delete;
    }
    
    public boolean deleteAllMembers() {
        final boolean hasMoreElements = this.members.elements().hasMoreElements();
        this.members = new FigObjectList();
        this.update_bbox();
        return hasMoreElements;
    }
    
    public boolean isMember(final FigObject figObject) {
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            if (listNode.obj == figObject) {
                return true;
            }
        }
        return false;
    }
    
    public void update_bbox() {
        if (this.members.get_first() == null) {
            this.bbox = new FigBbox(0, 0, 0, 0);
        }
        else {
            this.bbox = new FigBbox(this.members.get_first().obj.getBbox());
            for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
                this.bbox = this.bbox.union(listNode.obj.getBbox());
            }
        }
        this.sc_bbox_timestamp = 0L;
        this.build_sc_bbox();
        final ListNode get_last = this.members.get_last();
        if (get_last != null) {
            this.attribs.currentLayer = get_last.obj.getAttributes().currentLayer;
        }
    }
    
    public FigObjectList getMembers() {
        return this.members;
    }
    
    public void move(final int n, final int n2) {
        super.move(n, n2);
        if (this.members == null) {
            return;
        }
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            listNode.obj.move(n, n2);
        }
        this.update_bbox();
    }
    
    public void mirrorX(final int n, final int n2) {
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            listNode.obj.mirrorX(n, n2);
        }
        this.x = 2 * n - this.x;
        this.update_bbox();
    }
    
    public void mirrorY(final int n, final int n2) {
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            listNode.obj.mirrorY(n, n2);
        }
        this.y = 2 * n2 - this.y;
        this.update_bbox();
    }
    
    public void scale(final Point point, final double n, final double n2) {
        if (this.members == null) {
            return;
        }
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            listNode.obj.scale(point, n, n2);
        }
        this.update_bbox();
    }
    
    public void update(final FigAttribs figAttribs) {
        if (this.members == null) {
            return;
        }
        this.attribs.update(figAttribs);
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            listNode.obj.update(figAttribs);
        }
        this.update_bbox();
    }
    
    public void rebuild() {
        if (this.members == null) {
            return;
        }
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            listNode.obj.rebuild();
        }
        this.update_bbox();
    }
    
    public void changeLayerRecursively(final FigAttribs figAttribs) {
        final ListNode get_last = this.members.get_last();
        final int currentLayer = figAttribs.currentLayer;
        int currentLayer2 = 0;
        if (get_last != null) {
            currentLayer2 = get_last.obj.getAttributes().currentLayer;
        }
        final int n = currentLayer - currentLayer2;
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            final FigObject obj = listNode.obj;
            final FigAttribs attributes = obj.getAttributes();
            attributes.currentLayer += n;
            if (obj instanceof FigCompound) {
                ((FigCompound)obj).changeLayerRecursively(figAttribs);
            }
        }
    }
    
    public FigObject copy() {
        if (this.members == null) {
            return null;
        }
        final FigCompound figCompound = new FigCompound();
        figCompound.setTrafo(this.trafo);
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            figCompound.fastAddMember(listNode.obj.copy());
        }
        figCompound.update_bbox();
        return figCompound;
    }
    
    public void setTrafo(final FigTrafo2D figTrafo2D) {
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            listNode.obj.setTrafo(figTrafo2D);
        }
        super.setTrafo(figTrafo2D);
        this.update_bbox();
    }
    
    public void setVisible(final boolean b) {
        this.visible = b;
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            listNode.obj.setVisible(b);
        }
    }
    
    public void setObjectPainter(final ObjectPainter objectPainter) {
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            listNode.obj.setObjectPainter(objectPainter);
        }
        this.painter = objectPainter;
    }
    
    public void paint(final Graphics graphics) {
        if (this.members == null) {
            return;
        }
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            listNode.obj.paint(graphics);
        }
        super.paint(graphics);
    }
    
    public void paintSave(final Graphics graphics, final FigTrafo2D figTrafo2D) {
        if (this.members == null) {
            return;
        }
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            final Graphics create = graphics.create();
            listNode.obj.paintSave(create, figTrafo2D);
            create.dispose();
        }
    }
    
    public void paintInverse(final Graphics graphics) {
        if (this.members == null) {
            return;
        }
        for (ListNode listNode = this.members.get_last(); listNode != null; listNode = this.members.get_prev(listNode)) {
            listNode.obj.paint(graphics);
        }
        super.paint(graphics);
    }
    
    public boolean canRotate(final double n) {
        if (this.members == null) {
            return false;
        }
        boolean b = true;
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            b = (b && listNode.obj.canRotate(n));
        }
        return b;
    }
    
    public void rotate(final Point point, final double n) throws Exception {
        if (this.members == null) {
            return;
        }
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            listNode.obj.rotate(point, n);
        }
        this.update_bbox();
    }
    
    public void paint(final Graphics graphics, final FigBbox figBbox) {
        if (this.members == null) {
            return;
        }
        for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
            if (listNode.obj.getBbox().isVisible(figBbox)) {
                if (listNode.obj instanceof FigCompound) {
                    ((FigCompound)listNode.obj).paint(graphics, figBbox);
                }
                else {
                    listNode.obj.paint(graphics);
                }
            }
        }
        super.paint(graphics);
    }
    
    public double minDistanceEuclid(final Point point) {
        double n = this.bbox.minDistance(point);
        if (this.bbox.isInside(point)) {
            n = Math.min(n, 0.4 * this.trafo.getSnapRelative());
        }
        return n;
    }
    
    public String toString() {
        String s = "FigCompound at (" + this.x + ", " + this.y + ") on layer " + this.getLayer();
        if (this.members != null) {
            for (ListNode listNode = this.members.get_first(); listNode != null; listNode = this.members.get_next(listNode)) {
                s = s + "\n..." + listNode.obj.toString();
            }
        }
        return s;
    }
    
    public FigCompound() {
        this.members = new FigObjectList();
        this.update_bbox();
    }
}
