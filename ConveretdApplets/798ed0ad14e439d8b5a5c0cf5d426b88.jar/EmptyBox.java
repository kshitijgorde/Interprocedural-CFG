import java.util.Stack;
import java.awt.Point;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class EmptyBox extends AbstractBox
{
    public EmptyBox() {
        super.color = 6;
    }
    
    public final void enCalculRect(final BoxComponent boxComponent) {
        emptyBoxCalculRect(this, boxComponent, "");
    }
    
    protected final void onPaint(final BoxComponent boxComponent, final Graphics graphics) {
        paintArgumentBox(boxComponent, graphics, this, "");
    }
    
    public static final void emptyBoxCalculRect(final AbstractBox abstractBox, final BoxComponent boxComponent, final String s) {
        if (s.length() == 0) {
            abstractBox.width = abstractBox.em("8");
        }
        else {
            abstractBox.width = abstractBox.em(s) + 6;
        }
        abstractBox.height = abstractBox.em();
        abstractBox.baseline = abstractBox.em(0.75f);
    }
    
    public static final void paintArgumentBox(final BoxComponent boxComponent, final Graphics graphics, final AbstractBox abstractBox, final String s) {
        if (abstractBox.getParentBox() != null && abstractBox.getParentBox().fbvisible(abstractBox.p_en_pare)) {
            final int[] array = new int[4];
            final int[] array2 = new int[4];
            array2[array[0] = 0] = I(abstractBox, s);
            array[2] = abstractBox.width - 1;
            array2[2] = abstractBox.height - 1 - I(abstractBox, s);
            if (boxComponent.getMathRTL()) {
                final int[] array3 = array;
                final int n = 0;
                ++array3[n];
            }
            array[1] = array[0];
            array2[1] = array2[2];
            array[3] = array[2];
            array2[3] = array2[0];
            graphics.drawPolygon(array, array2, 4);
        }
        graphics.drawString(s, 3, abstractBox.baseline);
    }
    
    private static final int I(final AbstractBox abstractBox, final String s) {
        if (s.length() == 0) {
            return abstractBox.height / 8;
        }
        return 0;
    }
    
    private final int Z() {
        return super.height / 8;
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
    
    public final int TamanyCaret(final int n) {
        return super.height - 2 * this.Z();
    }
    
    public final Point PosicioCaret(final Point point, final int n, final BoxComponent boxComponent) {
        return new Point(point.x + super.width / 2, point.y + this.Z());
    }
    
    public final AbstractSelection Selecciona(final Stack stack, final Stack stack2, final boolean b) {
        return new EmptySelection(this);
    }
    
    public final AbstractSelection SeleccionaDreta(final Stack stack) {
        return new EmptySelection(this);
    }
    
    public final AbstractSelection SeleccionaEsquerra(final Stack stack) {
        return new EmptySelection(this);
    }
    
    public final int getFactorEscalaAbsolut(final BoxComponent boxComponent) {
        return getFactorEscalaAbsolut("");
    }
    
    public static final int getFactorEscalaAbsolut(final String s) {
        if (s.length() == 0) {
            return 100;
        }
        return 83;
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            if (this.isStyle(128)) {
                return "p";
            }
            if (boxScripting.isTop() || boxScripting.oneChild) {
                if (this.needsMStyle()) {
                    return "mstyle";
                }
                return "mrow";
            }
        }
        return null;
    }
    
    public final int getSplitFactor(final int n) {
        if (this.getParentBox() != null && this.getParentBox().fbvisible(super.p_en_pare)) {
            return 800;
        }
        return 0;
    }
    
    public final boolean isEmpty(final BoxComponent boxComponent) {
        return true;
    }
    
    public final boolean isValid(final int n) {
        return true;
    }
}
