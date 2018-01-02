import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class uea extends MouseAdapter
{
    private final super da;
    
    private uea(final super da) {
        this.da = da;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Component && !((Component)mouseEvent.getSource()).isEnabled()) {
            return;
        }
        String s = super._(this.da).get(mouseEvent.getSource());
        if (mouseEvent.getSource() == super.b(this.da) && super.b(this.da).isMultipleMode()) {
            s = super.b(this.da).a().a("selectStockMulti_Info");
        }
        else if (super.l(this.da) && (mouseEvent.getSource() == super.b(this.da) || mouseEvent.getSource() == super._(this.da))) {
            s = super.b(this.da).a().a("selectIndicatorDefinitionEnabled_Info");
        }
        if (s != null) {
            this.da.a(s);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Component && !((Component)mouseEvent.getSource()).isEnabled()) {
            return;
        }
        String s = super._(this.da).get(mouseEvent.getSource());
        if (mouseEvent.getSource() == super.b(this.da) && super.b(this.da).isMultipleMode()) {
            s = super.b(this.da).a().a("selectStockMulti_Info");
        }
        else if (super.l(this.da) && (mouseEvent.getSource() == super.b(this.da) || mouseEvent.getSource() == super._(this.da))) {
            s = super.b(this.da).a().a("selectIndicatorDefinitionEnabled_Info");
        }
        if (s != null && s.equals(super.b(this.da).getText())) {
            this.da.a("");
        }
    }
    
    uea(final super super1, final vea vea) {
        this(super1);
    }
}
