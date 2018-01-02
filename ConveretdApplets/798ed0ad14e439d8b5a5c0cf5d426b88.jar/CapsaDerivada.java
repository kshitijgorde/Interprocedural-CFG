import java.awt.Point;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaDerivada extends AbstractBox
{
    int PosAnt_Fill;
    
    public CapsaDerivada() {
        this(0);
    }
    
    public CapsaDerivada(final int posAnt_Fill) {
        this.PosAnt_Fill = posAnt_Fill;
        super.color = 3;
    }
    
    public final int nombreMinimDeFills() {
        return 2;
    }
    
    public final void enCalculRect() {
        final int n = this.em(0.1f) + this.em(0.7f) + this.em(0.0f);
        final int max = Math.max(super.fill[0].width, super.fill[1].width);
        super.fill[0].x = n + (max - super.fill[0].width) / 2;
        super.fill[1].x = n + (max - super.fill[1].width) / 2;
        super.width = n + max + this.em(0.1f) + this.em(0.1f);
        super.fill[0].y = 0;
        super.fill[1].y = 0 + super.fill[0].yh() + this.em(0.05f) + this.em(0.05f) + this.em(0.1f);
        super.height = super.fill[1].yh() + 0;
        super.baseline = super.fill[0].height + this.em(0.05f) + this.em(0.3125f);
    }
    
    protected final void I(final Graphics graphics) {
        graphics.fillRect(this.em(0.1f), super.fill[0].height + this.em(0.05f), super.width - this.em(0.1f) - this.em(0.1f), this.em(0.05f));
        if (this.PosAnt_Fill == 0) {
            graphics.drawString("d", super.fill[0].x - this.em(0.1f) - this.em(0.5f), super.fill[0].y + super.fill[0].baseline);
            graphics.drawString("d", super.fill[1].x - this.em(0.1f) - this.em(0.5f), super.fill[1].y + super.fill[1].baseline);
        }
        else if (this.PosAnt_Fill == 1) {
            graphics.drawString("\u2202", super.fill[0].x - this.em(0.1f) - this.em(0.5f), super.fill[0].y + super.fill[0].baseline);
            graphics.drawString("\u2202", super.fill[1].x - this.em(0.1f) - this.em(0.5f), super.fill[1].y + super.fill[1].baseline);
        }
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        if (point2.x <= 1) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        if (point2.x >= super.width - 1) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        if (point2.y < super.fill[0].yh()) {
            return super.fill[0].PosicioMesProperaGlobal(point, array);
        }
        return super.fill[1].PosicioMesProperaGlobal(new Point(point.x, super.fill[1].PosicioReal().y), array);
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            return "apply";
        }
        if (boxScripting.scriptMode == 0) {
            return "differentiate";
        }
        if (boxScripting.scriptMode == 2) {
            return "differentiate";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            boxScripting.openTag("diff");
            boxScripting.closeTag();
            boxScripting.openTag("bvar");
            XMLBoxUtils.mrow(super.fill[1], boxScripting);
            boxScripting.closeTag();
            XMLBoxUtils.mrow(super.fill[0], boxScripting);
        }
        else {
            super.onScript(boxScripting);
        }
    }
    
    public final int getSplitFactor(final int n) {
        return 700;
    }
}
