// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload.list.renderer;

import jmaster.util.swing.easylist.EasyListModel;
import javax.swing.Icon;

public class GeneralPurposeCheckboxRenderer extends AbstractUploadFileRendererComponent
{
    protected Icon x;
    protected Icon v;
    protected Icon y;
    protected Icon w;
    
    public Icon getCheckedIcon() {
        return this.x;
    }
    
    public void setCheckedIcon(final Icon x) {
        this.x = x;
    }
    
    public Icon getUncheckedIcon() {
        return this.v;
    }
    
    public void setUncheckedIcon(final Icon v) {
        this.v = v;
    }
    
    public Icon getCheckedDisabledIcon() {
        return this.y;
    }
    
    public void setCheckedDisabledIcon(final Icon y) {
        this.y = y;
    }
    
    public Icon getUncheckedDisabledIcon() {
        return this.w;
    }
    
    public void setUncheckedDisabledIcon(final Icon w) {
        this.w = w;
    }
    
    public void prepare() {
        this.setVisible(this.V.B().getGeneralPurposeCheckParamName() != null);
        if (this.isVisible()) {
            this.setActionEnabled(this.getFile().isCheckedUpdatable());
            this.setToolTipText(this.V.B().getGeneralPurposeCheckboxTooltip());
            if (this.getFile().isCheckedUpdatable()) {
                this.setIcon(this.getFile().isChecked() ? this.x : this.v);
            }
            else {
                this.setIcon(this.getFile().isChecked() ? this.y : this.w);
            }
        }
        super.prepare();
    }
    
    protected void A() {
        this.getFile().setChecked(!this.getFile().isChecked());
        ((EasyListModel)this.S.getModel()).updateItem(this.getFile());
    }
}
