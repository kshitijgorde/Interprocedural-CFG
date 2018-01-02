import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class sp extends MouseAdapter
{
    private final var n;
    
    private sp(final var n) {
        this.n = n;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Component && !((Component)mouseEvent.getSource()).isEnabled()) {
            return;
        }
        String s = var.b(this.n).get(mouseEvent.getSource());
        if (mouseEvent.getSource() == var.a(this.n) && var.a(this.n).isMultipleMode()) {
            s = var._(this.n).b()._("selectStockMulti_Info");
        }
        else if (var.b(this.n) && (mouseEvent.getSource() == var._(this.n) || mouseEvent.getSource() == var.a(this.n))) {
            s = var._(this.n).b()._("selectIndicatorDefinitionEnabled_Info");
        }
        if (s != null) {
            this.n.b(s);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Component && !((Component)mouseEvent.getSource()).isEnabled()) {
            return;
        }
        String s = var.b(this.n).get(mouseEvent.getSource());
        if (mouseEvent.getSource() == var.a(this.n) && var.a(this.n).isMultipleMode()) {
            s = var._(this.n).b()._("selectStockMulti_Info");
        }
        else if (var.b(this.n) && (mouseEvent.getSource() == var._(this.n) || mouseEvent.getSource() == var.a(this.n))) {
            s = var._(this.n).b()._("selectIndicatorDefinitionEnabled_Info");
        }
        if (s != null && s.equals(var.a(this.n).getText())) {
            this.n.b("");
        }
    }
    
    sp(final var var, final tp tp) {
        this(var);
    }
}
