// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.event.ActionEvent;
import borland.jbcl.model.SingletonModelEvent;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import borland.jbcl.model.ItemPainter;
import java.awt.Dimension;
import borland.jbcl.model.ItemPaintSite;
import borland.jbcl.model.ToggleItemEditor;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.SystemColor;
import borland.jbcl.model.SingletonModelMulticaster;
import borland.jbcl.util.KeyMulticaster;
import java.awt.Insets;
import java.awt.Point;
import borland.jbcl.model.ItemEditor;
import borland.jbcl.model.SingletonViewManager;
import borland.jbcl.model.WritableSingletonModel;
import borland.jbcl.model.SingletonModel;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import borland.jbcl.model.ItemEditSite;
import borland.jbcl.model.SingletonModelListener;

public class FieldView extends BeanPanel implements SingletonView, SingletonModelListener, ItemEditSite, KeyListener, FocusListener
{
    private transient SingletonModel model;
    private transient WritableSingletonModel writeModel;
    private boolean readOnly;
    private transient SingletonViewManager viewManager;
    protected BorderItemPainter border;
    private ItemEditor editor;
    private Point editClickPoint;
    private boolean selectable;
    private boolean postOnEndEdit;
    private boolean autoEdit;
    private boolean editInPlace;
    private boolean showFocus;
    private boolean showRollover;
    private boolean flat;
    private int state;
    private Insets margins;
    private int alignment;
    private int preferredHeight;
    private int preferredWidth;
    private boolean endEditFailed;
    private transient KeyMulticaster keyMulticaster;
    private transient SingletonModelMulticaster modelMulticaster;
    protected boolean postOnFocusLost;
    
    public FieldView() {
        this.border = new BorderItemPainter(9);
        this.selectable = false;
        this.postOnEndEdit = true;
        this.autoEdit = true;
        this.editInPlace = true;
        this.showFocus = true;
        this.showRollover = false;
        this.flat = false;
        this.margins = new Insets(2, 2, 2, 2);
        this.alignment = 33;
        this.preferredHeight = 20;
        this.preferredWidth = 100;
        this.endEditFailed = false;
        this.keyMulticaster = new KeyMulticaster();
        this.modelMulticaster = new SingletonModelMulticaster();
        this.postOnFocusLost = true;
        super.setBackground(SystemColor.window);
        super.addKeyListener(this.keyMulticaster);
    }
    
    public SingletonModel getModel() {
        return this.model;
    }
    
    public void setModel(final SingletonModel sm) {
        this.safeEndEdit();
        if (this.model != null) {
            this.model.removeModelListener(this);
            this.model.removeModelListener(this.modelMulticaster);
        }
        this.model = sm;
        if (this.model != null) {
            this.model.addModelListener(this);
            this.model.addModelListener(this.modelMulticaster);
        }
        if (this.model instanceof WritableSingletonModel) {
            this.writeModel = (WritableSingletonModel)this.model;
        }
        else {
            this.writeModel = null;
        }
        this.repaint(100L);
    }
    
    public WritableSingletonModel getWriteModel() {
        return this.readOnly ? null : this.writeModel;
    }
    
    public void addModelListener(final SingletonModelListener l) {
        this.modelMulticaster.add(l);
    }
    
    public void removeModelListener(final SingletonModelListener l) {
        this.modelMulticaster.remove(l);
    }
    
    public boolean isReadOnly() {
        return this.readOnly || this.writeModel == null;
    }
    
    public void setReadOnly(final boolean ro) {
        this.readOnly = ro;
    }
    
    public void setEditInPlace(final boolean editInPlace) {
        this.editInPlace = editInPlace;
    }
    
    public boolean isEditInPlace() {
        return this.editInPlace;
    }
    
    public boolean isPostOnEndEdit() {
        return this.postOnEndEdit;
    }
    
    public void setPostOnEndEdit(final boolean post) {
        this.postOnEndEdit = post;
    }
    
    public void setAutoEdit(final boolean edit) {
        this.autoEdit = edit;
    }
    
    public boolean isAutoEdit() {
        return this.autoEdit;
    }
    
    public boolean isEditing() {
        return this.editor != null;
    }
    
    public boolean isPostOnFocusLost() {
        return this.postOnFocusLost;
    }
    
    public void setPostOnFocusLost(final boolean post) {
        this.postOnFocusLost = post;
    }
    
    public boolean isShowFocus() {
        return this.showFocus;
    }
    
    public void setShowFocus(final boolean visible) {
        if (this.showFocus != visible) {
            this.showFocus = visible;
            this.repaint(100L);
        }
    }
    
    public void setFlat(final boolean flat) {
        if (flat != this.flat) {
            this.flat = flat;
            this.invalidate();
            this.repaint(100L);
        }
    }
    
    public boolean isFlat() {
        return this.flat;
    }
    
    public Insets getItemMargins() {
        return this.margins;
    }
    
    public void setItemMargins(final Insets margins) {
        this.margins = margins;
        this.repaint(100L);
    }
    
    public int getAlignment() {
        return this.alignment;
    }
    
    public void setAlignment(final int align) {
        this.alignment = align;
    }
    
    public SingletonViewManager getViewManager() {
        return this.viewManager;
    }
    
    public void setViewManager(final SingletonViewManager viewManager) {
        this.safeEndEdit();
        this.viewManager = viewManager;
        this.repaint();
    }
    
    public boolean isSelectable() {
        return this.selectable;
    }
    
    public void setSelectable(final boolean select) {
        if (!(this.selectable = select)) {
            this.state &= 0xFFFFFFFC;
            this.repaint(100L);
        }
    }
    
    public boolean isSelected() {
        return this.selectable && (this.state & 0x4) != 0x0;
    }
    
    public void setSelected(final boolean selected) {
        if (this.selectable && selected) {
            this.state |= 0x4;
        }
        else {
            this.state &= 0xFFFFFFFB;
        }
        this.repaint(100L);
    }
    
    public void setShowRollover(final boolean showRollover) {
        this.showRollover = showRollover;
    }
    
    public boolean isShowRollover() {
        return this.showRollover;
    }
    
    protected String paramString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(super.paramString()).concat(String.valueOf(",selectable="))).concat(String.valueOf(this.selectable))).concat(String.valueOf(",state="))).concat(String.valueOf(this.state));
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public Point getEditClickPoint() {
        return this.editClickPoint;
    }
    
    public Graphics getSiteGraphics() {
        final Graphics g = this.getGraphics();
        if (g != null) {
            g.setFont(this.getFont());
        }
        return g;
    }
    
    public Component getSiteComponent() {
        return this;
    }
    
    public void startEdit() {
        if (this.model == null || this.viewManager == null || !this.editInPlace || this.isReadOnly() || !this.writeModel.canSet(true)) {
            return;
        }
        final Object data = this.model.get();
        this.editor = this.getEditor(data);
        if (this.editor != null) {
            final Component editorComponent = this.editor.getComponent();
            if (editorComponent != null) {
                editorComponent.setVisible(false);
                this.add(editorComponent);
            }
            final Rectangle r = this.outerRect();
            this.editor.addKeyListener(this);
            this.editor.addKeyListener(this.keyMulticaster);
            this.editor.startEdit(data, r, this);
            if (editorComponent != null) {
                editorComponent.addFocusListener(this);
            }
            this.editClickPoint = null;
        }
    }
    
    private boolean isToggleItem() {
        if (this.model == null || this.viewManager == null || !this.editInPlace) {
            return false;
        }
        final Object data = this.model.get();
        final ItemEditor ie = this.getEditor(data);
        return ie instanceof ToggleItemEditor && !this.isReadOnly() && this.writeModel.canSet(false);
    }
    
    public void endEdit() throws Exception {
        this.endEdit(this.postOnEndEdit);
    }
    
    public void endEdit(final boolean post) throws Exception {
        this.endEditFailed = false;
        if (this.editor != null) {
            boolean okToEnd = true;
            try {
                if (!post || (okToEnd = this.editor.canPost())) {
                    if (post && okToEnd) {
                        this.writeModel.set(this.editor.getValue());
                    }
                    if (okToEnd && this.editor != null) {
                        this.editor.endEdit(post);
                        final Component editorComponent = this.editor.getComponent();
                        this.editor.removeKeyListener(this);
                        this.editor.removeKeyListener(this.keyMulticaster);
                        if (editorComponent != null) {
                            this.remove(editorComponent);
                            editorComponent.removeFocusListener(this);
                        }
                        this.editClickPoint = null;
                        this.editor = null;
                    }
                }
            }
            catch (Exception x) {
                this.endEditFailed = true;
                throw x;
            }
        }
    }
    
    public void safeEndEdit() {
        this.safeEndEdit(this.postOnEndEdit);
    }
    
    public void safeEndEdit(final boolean post) {
        try {
            this.endEdit(post);
        }
        catch (Exception ex) {}
    }
    
    public Rectangle getInnerRect(final Graphics g) {
        if (this.flat) {
            return this.outerRect();
        }
        final Dimension outerSz = this.getSize();
        final Dimension borderSz = this.border.getPreferredSize(null, g, this.state, this);
        final Dimension innerSz = new Dimension(outerSz.width - borderSz.width, outerSz.height - borderSz.height);
        final int left = borderSz.width / 2;
        final int top = borderSz.height / 2;
        return new Rectangle(left, top, innerSz.width, innerSz.height);
    }
    
    private Rectangle outerRect() {
        final Dimension outerSz = this.getSize();
        return new Rectangle(0, 0, outerSz.width, outerSz.height);
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        final Dimension outerSz = this.getSize();
        final Object data = (this.model != null) ? this.model.get() : null;
        final ItemPainter painter = this.getPainter(data);
        final Rectangle r = this.getInnerRect(g);
        if (painter != null) {
            g.setColor(this.getBackground());
            g.setFont(this.getFont());
            painter.paint(data, g, r, this.state, this);
        }
        else {
            g.setColor(Color.red);
            g.fillRect(r.x, r.y, r.width, r.height);
        }
        if (!this.flat) {
            this.border.paint(null, g, new Rectangle(0, 0, outerSz.width, outerSz.height), this.state, this);
        }
    }
    
    public void keyTyped(final KeyEvent e) {
    }
    
    public void keyPressed(final KeyEvent e) {
        if (this.editor == null) {
            return;
        }
        switch (e.getKeyCode()) {
            case 10: {
                this.safeEndEdit(true);
                if (!this.endEditFailed) {
                    e.consume();
                    this.fireActionEvent();
                }
                break;
            }
            case 27: {
                this.safeEndEdit(false);
                e.consume();
                break;
            }
        }
    }
    
    public void keyReleased(final KeyEvent e) {
    }
    
    protected void processKeyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case 10: {
                if (e.isControlDown() && this.editor == null && !this.isReadOnly() && this.writeModel.canSet(false)) {
                    this.startEdit();
                }
                break;
            }
            case 113: {
                if (this.editor == null && !this.isToggleItem() && !this.isReadOnly() && this.writeModel.canSet(false)) {
                    this.startEdit();
                }
                break;
            }
            case 32: {
                if (this.isToggleItem()) {
                    this.startEdit();
                    e.consume();
                }
                break;
            }
            case 25:
            case 229: {
                if (this.editor == null && !this.isReadOnly() && this.writeModel.canSet(false)) {
                    this.startEdit();
                    break;
                }
                break;
            }
        }
    }
    
    protected void processKeyTyped(final KeyEvent e) {
        final char kChar = e.getKeyChar();
        if (this.editor != null || !this.autoEdit || e.isConsumed() || this.isReadOnly() || kChar == '\0' || kChar == '\t' || kChar == '\r' || kChar == '\n' || kChar == ' ' || kChar == '\u001b' || this.isToggleItem() || (e.isAltDown() ^ e.isControlDown()) || !this.writeModel.canSet(false)) {
            return;
        }
        this.startEdit();
        if (this.editor != null) {
            final Component eComp = this.editor.getComponent();
            if (eComp != null) {
                eComp.dispatchEvent(e);
            }
        }
    }
    
    public void focusGained(final FocusEvent e) {
    }
    
    public void focusLost(final FocusEvent e) {
        this.state &= 0xFFFFFFFD;
        if (!e.isTemporary() && this.postOnFocusLost) {
            this.safeEndEdit(this.postOnEndEdit);
        }
    }
    
    protected void processFocusEvent(final FocusEvent e) {
        super.processFocusEvent(e);
        switch (e.getID()) {
            case 1004: {
                if (this.showFocus) {
                    this.state |= 0x2;
                }
                if (this.editor != null && this.editor.getComponent() != null) {
                    this.editor.getComponent().requestFocus();
                }
                this.state &= 0xFFFFFF7F;
                this.repaint();
                break;
            }
            case 1005: {
                this.state &= 0xFFFFFFFD;
                this.state |= 0x80;
                this.repaint();
                break;
            }
        }
    }
    
    protected void processMousePressed(final MouseEvent e) {
        super.processMousePressed(e);
        this.state &= 0xFFFFFFBF;
        if (!e.isMetaDown()) {
            if (this.selectable) {
                if (this.isSelected()) {
                    this.state &= 0xFFFFFFFC;
                }
                else {
                    this.state |= 0x4;
                }
            }
            if ((this.state & 0x2) != 0x0 && this.editor == null && !this.isReadOnly() && this.writeModel.canSet(false)) {
                this.editClickPoint = new Point(e.getX(), e.getY());
                this.startEdit();
                return;
            }
            this.state |= 0x2;
            if (this.isToggleItem()) {
                this.editClickPoint = new Point(e.getX(), e.getY());
                this.startEdit();
            }
        }
        this.repaint();
    }
    
    protected void processMouseEntered(final MouseEvent e) {
        if (this.showRollover) {
            this.state |= 0x40;
            this.repaint();
        }
    }
    
    protected void processMouseExited(final MouseEvent e) {
        if (this.showRollover) {
            this.state &= 0xFFFFFFBF;
            this.repaint();
        }
    }
    
    public boolean isEnabled() {
        return (this.state & 0x1) == 0x0;
    }
    
    public void setEnabled(final boolean enabled) {
        if (enabled) {
            this.state &= 0xFFFFFFFE;
        }
        else {
            this.state |= 0x1;
        }
        super.setEnabled(enabled);
    }
    
    protected ItemPainter getPainter(final Object data) {
        return (this.viewManager != null) ? this.viewManager.getPainter(data, this.state) : null;
    }
    
    protected ItemEditor getEditor(final Object data) {
        return (this.viewManager != null) ? this.viewManager.getEditor(data, this.state) : null;
    }
    
    public void modelContentChanged(final SingletonModelEvent e) {
        if (this.editor != null) {
            this.safeEndEdit(false);
        }
        this.repaint();
    }
    
    public Dimension getPreferredSize() {
        final Graphics g = this.getSiteGraphics();
        final Object data = (this.model != null) ? this.model.get() : null;
        final ItemPainter painter = this.getPainter(data);
        Dimension size;
        if (painter != null) {
            size = painter.getPreferredSize(data, g, this.state, this);
        }
        else {
            size = new Dimension(0, 0);
        }
        final Dimension borderSz = this.flat ? new Dimension(0, 0) : this.border.getPreferredSize(null, g, this.state, this);
        final Dimension dimension = size;
        dimension.width += borderSz.width;
        final Dimension dimension2 = size;
        dimension2.height += borderSz.height;
        if (this.preferredHeight > size.height) {
            size.height = this.preferredHeight;
        }
        if (this.preferredWidth > size.width) {
            size.width = this.preferredWidth;
        }
        return size;
    }
    
    public void doLayout() {
        if (this.editor != null) {
            final Rectangle r = this.outerRect();
            this.editor.changeBounds(r);
        }
    }
    
    public void setPreferredHeight(final int preferredHeight) {
        this.preferredHeight = preferredHeight;
    }
    
    public int getPreferredHeight() {
        return this.preferredHeight;
    }
    
    public void setPreferredWidth(final int preferredWidth) {
        this.preferredWidth = preferredWidth;
    }
    
    public int getPreferredWidth() {
        return this.preferredWidth;
    }
    
    public void addKeyListener(final KeyListener l) {
        this.keyMulticaster.add(l);
    }
    
    public void removeKeyListener(final KeyListener l) {
        this.keyMulticaster.remove(l);
    }
    
    private void fireActionEvent() {
        final Object item = (this.model != null) ? this.model.get() : null;
        final String action = (item != null) ? item.toString() : "";
        this.processActionEvent(new ActionEvent(this, 1001, action));
    }
}
