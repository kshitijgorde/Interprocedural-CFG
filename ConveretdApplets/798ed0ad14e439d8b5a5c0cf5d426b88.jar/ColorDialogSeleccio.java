import java.awt.event.ActionEvent;
import java.awt.Frame;
import java.awt.Color;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorDialogSeleccio extends ColorDialog implements ActionListener
{
    Color aplicar;
    
    public ColorDialogSeleccio(final Frame frame) {
        super(frame);
        this.canviaAtributSeleccio();
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Ok")) {
            this.aplicar();
            this.setVisible(false);
        }
        else if (actionCommand.equals("Cancel")) {
            super.color = this.aplicar;
            this.setVisible(false);
        }
    }
    
    private final void canviaAtributSeleccio() {
        super.color = new Color(0, 0, 0);
        this.aplicar = super.color;
        this.setColor(super.color);
    }
    
    public final void aplicar() {
        if (super.I != null) {
            ((FormulaEditor)super.I).canviaAtributSeleccio(new Attributes(super.color));
        }
    }
}
