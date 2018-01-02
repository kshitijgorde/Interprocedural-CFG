// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import java.awt.Rectangle;
import java.awt.Container;
import jmaster.jumploader.view.impl.upload.MetadataView;
import javax.swing.JViewport;
import java.awt.Component;
import javax.swing.Icon;

public class MetadataEditActionRenderer extends AbstractUploadFileRendererComponent
{
    boolean s;
    protected Icon u;
    protected Icon t;
    
    public MetadataEditActionRenderer() {
        this.s = false;
    }
    
    public Icon getDefaultIcon() {
        return this.u;
    }
    
    public void setDefaultIcon(final Icon u) {
        this.u = u;
    }
    
    public Icon getRequiredIcon() {
        return this.t;
    }
    
    public void setRequiredIcon(final Icon t) {
        this.t = t;
    }
    
    public void prepare() {
        this.setVisible(this.V.B().isUseMetadata() && this.V.D().isReady());
        if (this.isVisible()) {
            if (!this.s && this.isActionEnabled()) {
                this.showMetadata();
                this.V.M().setVisible(false);
                this.s = true;
            }
            this.setIcon(this.V.K().A(this.getFile()) ? this.u : this.t);
        }
        super.prepare();
    }
    
    protected void A() {
        this.showMetadata();
    }
    
    public void showMetadata() {
        final MetadataView m = this.V.M();
        if (!this.S.equals(m.getParent())) {
            this.S.add(m);
            m.invalidate();
            this.S.invalidate();
        }
        if (this.P != null) {
            m.setLocation(this.P.getX(), this.P.getY());
        }
        else {
            m.setLocation(0, 0);
        }
        final Container parent = this.S.getParent();
        if (parent instanceof JViewport) {
            final Rectangle viewRect = ((JViewport)parent).getViewRect();
            final Rectangle bounds = m.getBounds();
            while (bounds.x + bounds.width > viewRect.x + viewRect.width) {
                final Rectangle rectangle = bounds;
                --rectangle.x;
            }
            while (bounds.y + bounds.height > viewRect.y + viewRect.height) {
                final Rectangle rectangle2 = bounds;
                --rectangle2.y;
            }
            m.setLocation(bounds.x, bounds.y);
        }
        m.setFile(this.getUploadFile());
        m.loadDataFromFile();
        m.setVisible(true);
        m.requestFocus();
    }
}
