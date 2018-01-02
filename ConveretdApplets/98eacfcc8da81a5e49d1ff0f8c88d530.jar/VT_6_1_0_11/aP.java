// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.Point;
import java.awt.Component;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.text.JTextComponent;
import javax.swing.undo.UndoManager;
import java.util.ResourceBundle;
import javax.swing.event.UndoableEditListener;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseListener;

public final class aP extends P implements MouseListener, DocumentListener, UndoableEditListener
{
    private ResourceBundle a;
    private final UndoManager b;
    private bY c;
    private boolean d;
    
    public aP(final String s, final int n, final int n2, final ResourceBundle a) {
        super(s, 1, 1);
        this.b = new UndoManager();
        this.d = false;
        this.a = a;
        this.b.setLimit(100);
        this.addMouseListener(this);
        this.getDocument().addDocumentListener(this);
        this.getDocument().addUndoableEditListener(this);
    }
    
    private bY c() {
        if (this.c == null) {
            this.c = new dl().b(this, this.a.getString("rightclick_undo"));
        }
        return this.c;
    }
    
    public final void insertUpdate(final DocumentEvent documentEvent) {
        this.d = true;
    }
    
    public final void removeUpdate(final DocumentEvent documentEvent) {
        this.d = true;
    }
    
    public final void changedUpdate(final DocumentEvent documentEvent) {
        this.d = true;
    }
    
    public final boolean a() {
        return this.d;
    }
    
    public final void undoableEditHappened(final UndoableEditEvent undoableEditEvent) {
        this.b.addEdit(undoableEditEvent.getEdit());
        this.getActionMap().put("Undo", this.c());
        this.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.requestFocus();
        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
            final JPopupMenu popupMenu;
            (popupMenu = new JPopupMenu()).add(this.c());
            popupMenu.add(new dl().c(this, this.a.getString("rightclick_cut")));
            popupMenu.add(new dl().d(this, this.a.getString("rightclick_copy")));
            popupMenu.add(new dl().e(this, this.a.getString("rightclick_paste")));
            popupMenu.add(new dl().f(this, this.a.getString("rightclick_delete")));
            popupMenu.addSeparator();
            popupMenu.add(new dl().a(this, this.a.getString("rightclick_select_all")));
            final Point convertPoint = SwingUtilities.convertPoint(mouseEvent.getComponent(), mouseEvent.getPoint(), this);
            popupMenu.show(this, convertPoint.x, convertPoint.y);
        }
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void b() {
        if (this.b.canUndo()) {
            this.b.undo();
        }
    }
}
