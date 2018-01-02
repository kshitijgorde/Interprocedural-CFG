import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaInici extends AbstractBox implements CommandsOwner
{
    public CapsaInici() {
        super.color = 3;
        super.dibs = new NeuterBox[] { NeuterBox.crea("begin"), NeuterBox.crea("end") };
        super.ndibs = super.dibs.length;
    }
    
    public final int nombreMinimDeFills() {
        return 2;
    }
    
    public final void enCalculRect() {
        super.dibs[0].x = this.em(0.1f);
        super.dibs[0].y = 0;
        super.fill[0].x = this.em(0.1f) + this.em(1.0f);
        super.fill[0].y = super.dibs[0].y + super.dibs[0].height + this.em(0.0f);
        super.dibs[1].x = this.em(0.1f);
        super.dibs[1].y = super.fill[0].yh() + this.em(0.0f);
        super.fill[1].x = super.dibs[1].x + super.dibs[1].width + this.em(" ");
        super.fill[1].y = super.dibs[1].y;
        final AbstractNeuterBox abstractNeuterBox = super.dibs[1];
        abstractNeuterBox.y += super.fill[1].baseline - super.dibs[1].baseline;
        this.I();
        super.width += this.em(0.1f);
        super.baseline = this.em(0.75f);
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        return this.I(point, array);
    }
    
    public final boolean fbvisible(final int n) {
        return n != 1;
    }
    
    public final boolean posicio_final() {
        return false;
    }
    
    public final boolean inserta_final() {
        return true;
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return "begincommands";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            final boolean b = !(super.fill[1] instanceof EmptyBox);
            final boolean b2 = b && boxScripting.oneChild;
            if (b2) {
                boxScripting.openTag("mrow");
            }
            boxScripting.openTag("apply");
            XMLBoxUtils.csymbol(boxScripting, "begin");
            XMLBoxUtils.mrow(super.fill[0], boxScripting);
            boxScripting.closeTag();
            if (b) {
                super.fill[1].script(boxScripting);
            }
            if (b2) {
                boxScripting.closeTag();
            }
        }
        else if (boxScripting.scriptMode == 0) {
            super.onScript(boxScripting);
        }
        else if (boxScripting.scriptMode == 2) {
            boxScripting.appendText(" begin ");
            super.fill[0].script(boxScripting);
            boxScripting.appendText(" end ");
            super.fill[1].script(boxScripting);
            boxScripting.appendText(" ");
        }
    }
}
