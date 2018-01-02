// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.Rectangle;
import java.util.Vector;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import com.hw.client.util.a;
import javax.swing.JTree;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.dnd.DropTarget;
import com.wimba.clients.vboard.n;
import java.awt.dnd.DragSource;
import javax.swing.Timer;
import javax.swing.tree.TreePath;
import java.awt.dnd.DropTargetListener;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragGestureListener;

public final class cl implements DragGestureListener, DragSourceListener, DropTargetListener
{
    private aL a;
    private TreePath b;
    private TreePath c;
    private Timer d;
    private ak e;
    private DragSource f;
    private cQ g;
    private n h;
    
    public cl(final aL a) {
        this.c = null;
        this.e = null;
        this.a = a;
        this.h = a.a;
        new DropTarget(this.a, this);
        (this.f = DragSource.getDefaultDragSource()).createDefaultDragGestureRecognizer(this.a, 3, this);
        if (this.h.D()) {
            (this.d = new Timer(2000, new dj(this))).setRepeats(false);
        }
    }
    
    public final TreePath a() {
        return this.c;
    }
    
    private void a(final Point point) {
        final cQ g = new cQ(this.a, point);
        if (this.g != null && g.d.equals(this.g.d)) {
            return;
        }
        if (g.f == null) {
            this.c();
            return;
        }
        boolean b = false;
        if (this.g == null || this.g.e != g.e || this.g.h != g.h) {
            b = true;
        }
        final cQ g2 = this.g;
        this.g = g;
        if (b) {
            if (g2 != null && this.g.e != g2.e) {
                this.a.repaint(g2.g);
            }
            this.a.repaint(this.g.g);
            if (this.c == null && this.b != null && this.b.getPathCount() > 0) {
                this.c = this.b;
                if (com.hw.client.util.a.a()) {
                    com.hw.client.util.a.b("VBDragDropHandler.computeDragStateInfo - _rootpath = " + this.c);
                }
            }
        }
    }
    
    public final void dragEnter(final DragSourceDragEvent dragSourceDragEvent) {
        final DragSourceContext dragSourceContext = dragSourceDragEvent.getDragSourceContext();
        final int dropAction;
        if (((dropAction = dragSourceDragEvent.getDropAction()) & 0x1) != 0x0) {
            dragSourceContext.setCursor(DragSource.DefaultCopyDrop);
            return;
        }
        if ((dropAction & 0x2) != 0x0) {
            dragSourceContext.setCursor(DragSource.DefaultMoveDrop);
            return;
        }
        dragSourceContext.setCursor(DragSource.DefaultCopyNoDrop);
    }
    
    public final void dragOver(final DragSourceDragEvent dragSourceDragEvent) {
    }
    
    public final void dropActionChanged(final DragSourceDragEvent dragSourceDragEvent) {
    }
    
    public final void dragExit(final DragSourceEvent dragSourceEvent) {
        dragSourceEvent.getDragSourceContext().setCursor(DragSource.DefaultCopyNoDrop);
        this.c();
    }
    
    public final void dragDropEnd(final DragSourceDropEvent dragSourceDropEvent) {
        this.c();
    }
    
    public final void dragGestureRecognized(final DragGestureEvent dragGestureEvent) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBDragDropHandler.dragGestureRecognized");
        }
        if (this.a.a.G()) {
            return;
        }
        this.e = new ak(this.a.a.O().a(this.a.a()));
        this.a.a((TreePath[])null);
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBDragDropHandler.dragGestureRecognized: Nb of nodes = " + this.e.a());
        }
        if (this.e != null) {
            this.f.startDrag(dragGestureEvent, DragSource.DefaultMoveNoDrop, this.e, this);
            return;
        }
        com.hw.client.util.a.b("VBDragDropHandler.dragGestureRecognized - Nothing selected");
    }
    
    public final void dragEnter(final DropTargetDragEvent dropTargetDragEvent) {
        if (this.a(dropTargetDragEvent)) {
            dropTargetDragEvent.acceptDrag(Z.a(dropTargetDragEvent));
        }
        else {
            dropTargetDragEvent.rejectDrag();
        }
        this.c = null;
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBDragDropHandler.dragEnter - _rootPath has been initialized");
        }
    }
    
    public final void dragOver(final DropTargetDragEvent dropTargetDragEvent) {
        if (this.a(dropTargetDragEvent)) {
            dropTargetDragEvent.acceptDrag(Z.a(dropTargetDragEvent));
            this.a(dropTargetDragEvent.getLocation());
        }
        else {
            dropTargetDragEvent.rejectDrag();
            this.c();
        }
        if (this.h.D()) {
            final Point location = dropTargetDragEvent.getLocation();
            final TreePath closestPathForLocation;
            if ((closestPathForLocation = this.a.getClosestPathForLocation(location.x, location.y)) != this.b) {
                this.b = closestPathForLocation;
                this.d.restart();
            }
        }
    }
    
    public final void dropActionChanged(final DropTargetDragEvent dropTargetDragEvent) {
        if (this.a(dropTargetDragEvent)) {
            dropTargetDragEvent.acceptDrag(Z.a(dropTargetDragEvent));
            return;
        }
        dropTargetDragEvent.rejectDrag();
    }
    
    public final void dragExit(final DropTargetEvent dropTargetEvent) {
        this.c();
    }
    
    public final void drop(final DropTargetDropEvent dropTargetDropEvent) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBDragDropHandler.drop");
        }
        this.a(dropTargetDropEvent.getLocation());
        try {
            if (dropTargetDropEvent.getDropAction() == 2) {
                final Transferable transferable = dropTargetDropEvent.getTransferable();
                Label_1092: {
                    aR[] array;
                    try {
                        array = (aR[])transferable.getTransferData(new DataFlavor("application/x-java-serialized-object; class=com.wimba.clients.vboard.tree.VBTreeNodes"));
                    }
                    catch (Exception ex) {
                        com.hw.client.util.a.b(ex.getMessage(), ex);
                        break Label_1092;
                    }
                    dropTargetDropEvent.acceptDrop(2);
                    final Vector vector = new Vector<String>();
                    int n = 1;
                    int n2 = 0;
                    for (int i = 0; i < array.length; ++i) {
                        if (com.hw.client.util.a.a()) {
                            com.hw.client.util.a.b("VBDragDropHandler.dropActionMove: nodes " + i + " out of " + array.length);
                        }
                        final aR ar = array[i];
                        final DataFlavor dataFlavor = new DataFlavor("application/x-java-serialized-object; class=com.wimba.common.vboard.MessageHeaders");
                        if (!ar.isDataFlavorSupported(dataFlavor)) {
                            dropTargetDropEvent.rejectDrop();
                            break Label_1092;
                        }
                        Object transferData;
                        try {
                            transferData = ar.getTransferData(dataFlavor);
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                            break Label_1092;
                        }
                        final aH ah;
                        if ((ah = (aH)transferData) == null) {
                            dropTargetDropEvent.getDropTargetContext().dropComplete(false);
                            break Label_1092;
                        }
                        final int l = ah.l();
                        final String b = ah.b();
                        if (com.hw.client.util.a.a()) {
                            com.hw.client.util.a.b("VBDragDropHandler.dropActionMove: dragged_reference / dragged_position = " + b + " / " + l);
                        }
                        String s = null;
                        if (this.g != null && this.g.e != null) {
                            final aH a;
                            final int j = (a = this.g.e.a()).l();
                            String s2 = a.b();
                            if (this.g.h == cQ.b) {
                                s2 = a.a();
                            }
                            if (com.hw.client.util.a.a()) {
                                com.hw.client.util.a.b("VBDragDropHandler.dropActionMove + dropped_reference/ dropped_position = " + s2 + " / " + j);
                            }
                            if (ah.a().equals(a.a())) {
                                if (com.hw.client.util.a.a()) {
                                    com.hw.client.util.a.b("VBDragDropHandler.drop: Nothing to do - drag and drop nodes equal");
                                }
                                dropTargetDropEvent.getDropTargetContext().dropComplete(false);
                                break Label_1092;
                            }
                            if (s2 == null && !Boolean.valueOf(this.h.y())) {
                                if (com.hw.client.util.a.a()) {
                                    com.hw.client.util.a.b("VBDragDropHandler.dropActionMove: REVERSE MODE ");
                                }
                                if (this.g.h == cQ.c) {
                                    this.g.h = cQ.a;
                                }
                                else if (this.g.h == cQ.a) {
                                    this.g.h = cQ.c;
                                }
                            }
                            if ((b == null && s2 != null) || (b != null && !b.equals(s2))) {
                                if (com.hw.client.util.a.a()) {
                                    com.hw.client.util.a.b("VBDragDropHandler.dropActionMove: CASE 1: old and new references are different");
                                }
                                if (s2 == null || b == null) {
                                    if (n != 0) {
                                        if (this.g.h == cQ.a) {
                                            n2 = j;
                                        }
                                        else if (this.g.h == cQ.c) {
                                            n2 = j + 1;
                                        }
                                    }
                                    else {
                                        ++n2;
                                    }
                                }
                                else if (this.g.h == cQ.a) {
                                    n2 = j;
                                }
                                else if (this.g.h == cQ.c) {
                                    n2 = j + 1;
                                }
                            }
                            else {
                                if (com.hw.client.util.a.a()) {
                                    com.hw.client.util.a.b("VBDragDropHandler.dropActionMove: CASE 2: old and new references are the same");
                                }
                                if (l < j) {
                                    if (com.hw.client.util.a.a()) {
                                        com.hw.client.util.a.b("VBDragDropHandler.dropActionMove: CASE 2-A: dragged_position < dropped_position");
                                    }
                                    if (this.g.h == cQ.a) {
                                        n2 = j - 1;
                                    }
                                    else if (this.g.h == cQ.c) {
                                        n2 = j;
                                    }
                                }
                                else if (l > j) {
                                    if (com.hw.client.util.a.a()) {
                                        com.hw.client.util.a.b("VBDragDropHandler.dropActionMove: CASE 2-B: dragged_position > dropped_position");
                                    }
                                    if (s2 == null) {
                                        n2 = j;
                                    }
                                    else if (this.g.h == cQ.a) {
                                        n2 = j;
                                    }
                                    else if (this.g.h == cQ.c) {
                                        n2 = j + 1;
                                    }
                                }
                                else {
                                    com.hw.client.util.a.e("VBDragDropHandler.drop: dragged and dropped nodes have same position. should have been caught before");
                                }
                            }
                            if (this.g.h == cQ.b) {
                                s = a.a();
                            }
                            else {
                                s = a.b();
                            }
                        }
                        if (com.hw.client.util.a.a()) {
                            com.hw.client.util.a.b("VBDragDropHandler.dropActionMove: reference / newposition = " + s + " / " + n2);
                        }
                        final n a2 = this.a.a;
                        final String a3 = ah.a();
                        final String s3 = s;
                        final int n3 = n2;
                        final String s4 = s3;
                        final String s5 = a3;
                        final n n4 = a2;
                        if (com.hw.client.util.a.a()) {
                            com.hw.client.util.a.b("VBoardDesktopPane.moveMessage: message_id=" + s5 + ", new_reference=" + s4 + ", new_position=" + n3);
                        }
                        n4.F().a(null, n4.e("main_dlg_reordering_title"), n4.e("main_dlg_reordering_msg"));
                        n4.a(s5, n3, s4);
                        if (!vector.contains(s)) {
                            vector.add(s);
                        }
                        if (!vector.contains(b)) {
                            vector.add(b);
                        }
                        n = 0;
                    }
                    for (int k = 0; k < vector.size(); ++k) {
                        final String s6 = vector.elementAt(k);
                        if (com.hw.client.util.a.a()) {
                            com.hw.client.util.a.b("VBDragDropHandler.dropActionMove: reloading list for reference " + s6);
                        }
                        this.a.a.b(s6);
                    }
                    dropTargetDropEvent.getDropTargetContext().dropComplete(true);
                }
                dropTargetDropEvent.getDropTargetContext().dropComplete(true);
            }
        }
        catch (ClassNotFoundException ex3) {
            ex3.printStackTrace();
        }
        finally {
            this.c();
        }
    }
    
    private void c() {
        if (this.g != null) {
            final Rectangle g = this.g.g;
            this.g = null;
            this.a.repaint(g);
        }
    }
    
    private boolean a(final DropTargetDragEvent dropTargetDragEvent) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VBDragDropHandler.isDropOK");
        }
        if (dropTargetDragEvent.getDropAction() == 1) {
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VBDragDropHandler.isDropOK: return false (is copy)");
            }
            return false;
        }
        if (dropTargetDragEvent.getDropAction() != 2) {
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VBDragDropHandler.isDropOK return false (is not copy or move)");
            }
            return false;
        }
        if (!this.h.K()) {
            com.hw.client.util.a.d("You have not the rights to reorder messages or import audio");
            return false;
        }
        this.a(dropTargetDragEvent.getLocation());
        if (this.g == null || this.g.e == null) {
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VBDragDropHandler.isDropOK: return false (no node next to the cursor)");
            }
            return false;
        }
        final String[] b = this.e.b();
        final aH a = this.g.e.a();
        for (int i = 0; i < b.length; ++i) {
            final String s = b[i];
            final String a2 = a.a();
            final String s2 = s;
            boolean b2 = false;
            Label_0227: {
                if (a2 != null && a2.equals(s2)) {
                    b2 = true;
                }
                else {
                    for (String s3 = ((co)this.a.getModel()).b(a2).b(); s3 != null; s3 = ((co)this.a.getModel()).b(s3).b()) {
                        if (s3.equals(s2)) {
                            b2 = true;
                            break Label_0227;
                        }
                    }
                    b2 = false;
                }
            }
            if (b2) {
                if (com.hw.client.util.a.a()) {
                    com.hw.client.util.a.b("VBDragDropHandler.isDropOK: return false (dropped in current thread) ");
                }
                return false;
            }
        }
        final Transferable transferable = new DropTargetDropEvent(dropTargetDragEvent.getDropTargetContext(), dropTargetDragEvent.getLocation(), dropTargetDragEvent.getDropAction(), 0).getTransferable();
        try {
            if (!transferable.isDataFlavorSupported(new DataFlavor("application/x-java-serialized-object; class=com.wimba.clients.vboard.tree.VBTreeNodes"))) {
                if (com.hw.client.util.a.a()) {
                    com.hw.client.util.a.b("VBDragDropHandler.isDropOK return false (is not a VBTreeNodes)");
                }
                return false;
            }
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public final cQ b() {
        return this.g;
    }
    
    static TreePath a(final cl cl) {
        return cl.b;
    }
    
    static aL b(final cl cl) {
        return cl.a;
    }
}
