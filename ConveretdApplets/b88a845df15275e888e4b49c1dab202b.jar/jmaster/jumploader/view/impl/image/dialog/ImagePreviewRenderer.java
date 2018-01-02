// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image.dialog;

import jmaster.jumploader.view.impl.file.list.renderer.AbstractFileRendererComponent;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.B;
import jmaster.util.swing.easylist.EasyListCellRenderer;

public class ImagePreviewRenderer extends EasyListCellRenderer
{
    private static final long O = 1978391225113644214L;
    private static final String N = "imagePreviewRenderer";
    
    public ImagePreviewRenderer(final B model, final IMainView mainView) {
        this.init("imagePreviewRenderer");
        for (int i = 0; i < this.getComponentCount2(); ++i) {
            ((AbstractFileRendererComponent)this.getComponentAt2(i)).setModel(model);
        }
    }
}
