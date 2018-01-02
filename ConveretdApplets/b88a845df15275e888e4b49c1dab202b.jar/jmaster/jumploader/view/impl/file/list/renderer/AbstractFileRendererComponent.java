// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list.renderer;

import jmaster.jumploader.model.api.file.IFile;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.B;
import jmaster.util.swing.easylist.EasyListCellRendererComponent;

public abstract class AbstractFileRendererComponent extends EasyListCellRendererComponent
{
    protected B V;
    protected IMainView U;
    
    public B getModel() {
        return this.V;
    }
    
    public void setModel(final B v) {
        this.V = v;
    }
    
    public IMainView getView() {
        return this.U;
    }
    
    public void setView(final IMainView u) {
        this.U = u;
    }
    
    public IFile getFile() {
        return (IFile)this.L;
    }
    
    public void setFile(final IFile l) {
        this.L = l;
    }
}
