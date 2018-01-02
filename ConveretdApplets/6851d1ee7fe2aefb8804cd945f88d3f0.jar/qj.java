import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class qj extends MouseAdapter
{
    private final n ta;
    
    private qj(final n ta) {
        this.ta = ta;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Component && !((Component)mouseEvent.getSource()).isEnabled()) {
            return;
        }
        String s = (String)n.a(this.ta).get(mouseEvent.getSource());
        if (mouseEvent.getSource() == n.b(this.ta) && n.b(this.ta).isMultipleMode()) {
            s = n.a(this.ta).a().b("selectStockMulti_Info");
        }
        else if (n._(this.ta) && (mouseEvent.getSource() == n._(this.ta) || mouseEvent.getSource() == n.d(this.ta))) {
            s = n.a(this.ta).a().b("selectIndicatorDefinitionEnabled_Info");
        }
        if (s != null) {
            this.ta.b(s);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof Component && !((Component)mouseEvent.getSource()).isEnabled()) {
            return;
        }
        String s = (String)n.a(this.ta).get(mouseEvent.getSource());
        if (mouseEvent.getSource() == n.b(this.ta) && n.b(this.ta).isMultipleMode()) {
            s = n.a(this.ta).a().b("selectStockMulti_Info");
        }
        else if (n._(this.ta) && (mouseEvent.getSource() == n._(this.ta) || mouseEvent.getSource() == n.d(this.ta))) {
            s = n.a(this.ta).a().b("selectIndicatorDefinitionEnabled_Info");
        }
        if (s != null && s.equals(n._(this.ta).getText())) {
            this.ta.b("");
        }
    }
    
    qj(final n n, final rj rj) {
        this(n);
    }
}
