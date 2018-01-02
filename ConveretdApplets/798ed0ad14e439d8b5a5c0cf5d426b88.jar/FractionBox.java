import java.awt.Point;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class FractionBox extends AbstractBox
{
    public static int defaultShrinkFactor;
    public static int smallShrinkFactor;
    
    public FractionBox() {
        super.color = 3;
    }
    
    public final int nombreMinimDeFills() {
        return 2;
    }
    
    public final void enCalculRect() {
        final int n = this.em(0.08f) + this.em(0.15f);
        final int max = Math.max(super.fill[0].width, super.fill[1].width);
        super.fill[0].x = n + (max - super.fill[0].width) / 2;
        super.fill[1].x = n + (max - super.fill[1].width) / 2;
        super.width = n + max + this.em(0.13f) + this.em(0.08f);
        super.fill[0].y = 0;
        super.fill[1].y = 0 + super.fill[0].height + this.em(0.05f) + this.em(0.05f) + this.em(0.1f);
        super.height = super.fill[1].yh() + 0;
        super.baseline = super.fill[0].height + this.em(0.05f) + this.em(0.3125f);
    }
    
    protected final void I(final Graphics graphics) {
        graphics.fillRect(this.em(0.08f), super.fill[0].height + this.em(0.05f), super.width - this.em(0.08f) - this.em(0.08f), Math.max(this.em(0.05f), 1));
    }
    
    public final int kindChildBox(final int n, final int n2) {
        return 1;
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        if (point2.x < this.em(0.08f)) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        if (point2.x >= super.width - this.em(0.08f)) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        if (point2.y < super.fill[0].yh()) {
            return super.fill[0].PosicioMesProperaGlobal(point, array);
        }
        return super.fill[1].PosicioMesProperaGlobal(new Point(point.x, super.fill[1].PosicioReal().y), array);
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return "frac";
        }
        if (boxScripting.scriptMode == 1) {
            return "mfrac";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 2) {
            boxScripting.appendText("((");
            super.fill[0].script(boxScripting);
            boxScripting.appendText(")/(");
            super.fill[1].script(boxScripting);
            boxScripting.appendText("))");
            return;
        }
        super.onScript(boxScripting);
    }
    
    public final int getSplitFactor(final int n) {
        return 700;
    }
    
    public final int getFactorEscalaAbsolut(final BoxComponent boxComponent) {
        return FractionBox.defaultShrinkFactor;
    }
    
    static {
        FractionBox.defaultShrinkFactor = 100;
        FractionBox.smallShrinkFactor = 90;
    }
}
