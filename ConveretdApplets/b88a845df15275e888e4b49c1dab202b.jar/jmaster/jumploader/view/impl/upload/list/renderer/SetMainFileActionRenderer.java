// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import jmaster.util.C.B;
import jmaster.jumploader.model.api.common.IAttribute;
import java.awt.Cursor;
import javax.swing.Icon;

public class SetMainFileActionRenderer extends AbstractUploadFileRendererComponent
{
    private Icon a;
    private Icon _;
    private String Z;
    private String b;
    private Cursor X;
    private Cursor Y;
    
    public Icon getIconMainFile() {
        return this.a;
    }
    
    public void setIconMainFile(final Icon a) {
        this.a = a;
    }
    
    public Icon getIconNonMainFile() {
        return this._;
    }
    
    public void setIconNonMainFile(final Icon _) {
        this._ = _;
    }
    
    public String getToolTipTextMainFile() {
        return this.Z;
    }
    
    public void setToolTipTextMainFile(final String z) {
        this.Z = z;
    }
    
    public String getToolTipTextNonMainFile() {
        return this.b;
    }
    
    public void setToolTipTextNonMainFile(final String b) {
        this.b = b;
    }
    
    public Cursor getCursorMainFile() {
        return this.X;
    }
    
    public void setCursorMainFile(final Cursor x) {
        this.X = x;
    }
    
    public Cursor getCursorNonMainFile() {
        return this.Y;
    }
    
    public void setCursorNonMainFile(final Cursor y) {
        this.Y = y;
    }
    
    public void prepare() {
        this.setVisible(this.V.B().isUseMainFile());
        if (this.isVisible()) {
            final IAttribute attributeByName = this.getUploadFile().getAttributeSet().getAttributeByName("mainFile");
            final boolean b = attributeByName != null && "true".equals(attributeByName.getValue());
            this.setActionEnabled(true);
            this.setIcon(b ? this.a : this._);
            this.setToolTipText(b ? this.Z : this.b);
            this.setCursor(b ? this.X : this.Y);
        }
        super.prepare();
    }
    
    protected void A() {
        jmaster.util.C.B.A(this, "setMainFile");
    }
    
    public void setMainFile() {
        try {
            this.getModel().D().setMainFile(this.getUploadFile());
        }
        catch (Exception ex) {
            this.F.E(ex, ex);
        }
    }
}
