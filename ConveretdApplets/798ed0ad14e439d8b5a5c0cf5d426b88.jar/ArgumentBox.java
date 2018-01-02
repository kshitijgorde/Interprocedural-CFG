import java.awt.Point;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class ArgumentBox extends AbstractBox
{
    protected int[] x;
    protected int[] y;
    public String cadena;
    
    public ArgumentBox() {
        this.x = new int[4];
        this.y = new int[4];
        super.color = 6;
    }
    
    public ArgumentBox(final String s) {
        this();
        this.cadena = new String(s);
    }
    
    public final void inicialitzaFills(final AbstractBox[] array) {
        this.cadena = array[0].plainString();
    }
    
    public final void enCalculRect(final BoxComponent boxComponent) {
        EmptyBox.emptyBoxCalculRect(this, boxComponent, this.cadena);
    }
    
    protected final void onPaint(final BoxComponent boxComponent, final Graphics graphics) {
        EmptyBox.paintArgumentBox(boxComponent, graphics, this, this.cadena);
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        return new BoxPosition(this, 0);
    }
    
    public final BoxPosition PosSeg_Fora(final AbstractBox[] array) {
        return new BoxPosition(this, 0);
    }
    
    public final BoxPosition PosAnt_Fora(final AbstractBox[] array) {
        return new BoxPosition(this, 0);
    }
    
    public final Point PosicioCaret(final Point point, final int n, final BoxComponent boxComponent) {
        return new Point(point.x + super.width / 2, point.y);
    }
    
    public final int getFactorEscalaAbsolut(final BoxComponent boxComponent) {
        return EmptyBox.getFactorEscalaAbsolut(this.cadena);
    }
    
    public final boolean posicio_final() {
        return false;
    }
    
    public final boolean posicio_inicial() {
        return false;
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            return "maction";
        }
        if (boxScripting.scriptMode == 0) {
            return "argument";
        }
        if (boxScripting.scriptMode == 2) {
            return "error";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            boxScripting.openTag("mtext");
            boxScripting.appendText(this.cadena);
            boxScripting.closeTag();
        }
        else if (boxScripting.scriptMode == 0) {
            boxScripting.appendScript("{");
            boxScripting.appendText(this.cadena);
            boxScripting.appendScript("}");
        }
        else if (boxScripting.scriptMode == 2) {
            if (super.cadena_omega != null && super.cadena_omega.length() != 0) {
                boxScripting.appendText("(\"Missing: " + super.cadena_omega + "\")");
            }
            else {
                boxScripting.appendText("(\"Missing: " + this.cadena + "\")");
            }
        }
    }
    
    public final void attributes(final BoxScripting boxScripting) {
        boxScripting.appendAttribute("actiontype", "argument");
    }
    
    public final int getSplitFactor(final int n) {
        return 800;
    }
    
    public final boolean isValid(final int n) {
        return true;
    }
}
