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

public final class aV extends ar implements MouseListener, DocumentListener, UndoableEditListener
{
    private ResourceBundle b;
    final UndoManager a;
    private bY c;
    private boolean d;
    
    public aV(final ResourceBundle b) {
        this.a = new UndoManager();
        this.d = false;
        this.b = b;
        this.b();
    }
    
    public aV(final String s, final ResourceBundle b) {
        super(s);
        this.a = new UndoManager();
        this.d = false;
        this.b = b;
        this.b();
    }
    
    private void b() {
        this.a.setLimit(100);
        this.addMouseListener(this);
        this.getDocument().addDocumentListener(this);
        this.getDocument().addUndoableEditListener(this);
    }
    
    private bY c() {
        if (this.c == null) {
            this.c = new dl().b(this, this.b.getString("rightclick_undo"));
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
        this.a.addEdit(undoableEditEvent.getEdit());
        this.getActionMap().put("Undo", this.c());
        this.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.requestFocus();
        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
            final JPopupMenu popupMenu;
            (popupMenu = new JPopupMenu()).add(this.c());
            popupMenu.add(new dl().c(this, this.b.getString("rightclick_cut")));
            popupMenu.add(new dl().d(this, this.b.getString("rightclick_copy")));
            popupMenu.add(new dl().e(this, this.b.getString("rightclick_paste")));
            popupMenu.add(new dl().f(this, this.b.getString("rightclick_delete")));
            popupMenu.addSeparator();
            popupMenu.add(new dl().a(this, this.b.getString("rightclick_select_all")));
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
}
