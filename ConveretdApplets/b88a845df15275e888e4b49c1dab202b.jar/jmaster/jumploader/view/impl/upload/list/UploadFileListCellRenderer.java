// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list;

import java.awt.Component;
import javax.swing.JList;
import jmaster.jumploader.model.api.config.ViewConfig;
import jmaster.jumploader.view.impl.file.list.renderer.AbstractFileRendererComponent;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.B;
import jmaster.util.swing.easylist.EasyListCellRenderer;

public class UploadFileListCellRenderer extends EasyListCellRenderer
{
    private static final long I = 1978391225113644214L;
    private static final String H = "uploadFileListCellRenderer";
    protected B G;
    protected IMainView F;
    
    public UploadFileListCellRenderer(final B g, final IMainView f) {
        this.G = g;
        this.F = f;
        final ViewConfig h = g.H();
        this.init("uploadFileListCellRenderer" + ((h.getUploadListViewName() == null) ? "" : h.getUploadListViewName()));
        for (int i = 0; i < this.getComponentCount2(); ++i) {
            final AbstractFileRendererComponent abstractFileRendererComponent = (AbstractFileRendererComponent)this.getComponentAt2(i);
            abstractFileRendererComponent.setModel(this.G);
            abstractFileRendererComponent.setView(this.F);
        }
    }
    
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        super.getListCellRendererComponent(list, o, n, b, b2);
        return this;
    }
}
