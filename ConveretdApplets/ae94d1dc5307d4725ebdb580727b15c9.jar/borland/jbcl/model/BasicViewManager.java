// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

public class BasicViewManager implements SingletonViewManager, VectorViewManager, MatrixViewManager, GraphViewManager
{
    ItemPainter painter;
    ItemEditor editor;
    
    public BasicViewManager() {
    }
    
    public BasicViewManager(final ItemPainter painter) {
        this.painter = painter;
    }
    
    public BasicViewManager(final ItemPainter painter, final ItemEditor editor) {
        this.painter = painter;
        this.editor = editor;
    }
    
    public void setPainter(final ItemPainter p) {
        this.painter = p;
    }
    
    public ItemPainter getPainter() {
        return this.painter;
    }
    
    public void setEditor(final ItemEditor e) {
        this.editor = e;
    }
    
    public ItemEditor getEditor() {
        return this.editor;
    }
    
    public ItemPainter getPainter(final Object data, final int state) {
        return this.painter;
    }
    
    public ItemEditor getEditor(final Object data, final int state) {
        return this.editor;
    }
    
    public ItemPainter getPainter(final int index, final Object data, final int state) {
        return this.painter;
    }
    
    public ItemEditor getEditor(final int index, final Object data, final int state) {
        return this.editor;
    }
    
    public ItemPainter getPainter(final int row, final int col, final Object data, final int state) {
        return this.painter;
    }
    
    public ItemEditor getEditor(final int row, final int col, final Object data, final int state) {
        return this.editor;
    }
    
    public ItemPainter getPainter(final GraphLocation node, final Object data, final int state) {
        return this.painter;
    }
    
    public ItemEditor getEditor(final GraphLocation node, final Object data, final int state) {
        return this.editor;
    }
}
