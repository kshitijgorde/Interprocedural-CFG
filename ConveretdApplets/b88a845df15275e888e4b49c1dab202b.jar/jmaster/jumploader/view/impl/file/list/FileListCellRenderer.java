// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list;

import java.awt.Component;
import javax.swing.JList;
import jmaster.jumploader.model.api.config.ViewConfig;
import jmaster.jumploader.view.impl.file.list.renderer.AbstractFileRendererComponent;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.B;
import jmaster.util.swing.easylist.EasyListCellRenderer;

public class FileListCellRenderer extends EasyListCellRenderer
{
    private static final long M = 4706225221855941778L;
    private static final String L = "fileListCellRenderer";
    protected B K;
    protected IMainView J;
    
    public FileListCellRenderer(final B k, final IMainView j) {
        this.K = k;
        this.J = j;
        final ViewConfig h = k.H();
        this.init("fileListCellRenderer" + ((h.getUploadListViewName() == null) ? "" : h.getUploadListViewName()));
        for (int i = 0; i < this.getComponentCount2(); ++i) {
            final AbstractFileRendererComponent abstractFileRendererComponent = (AbstractFileRendererComponent)this.getComponentAt2(i);
            abstractFileRendererComponent.setModel(this.K);
            abstractFileRendererComponent.setView(this.J);
        }
    }
    
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        super.getListCellRendererComponent(list, o, n, b, b2);
        return this;
    }
}
