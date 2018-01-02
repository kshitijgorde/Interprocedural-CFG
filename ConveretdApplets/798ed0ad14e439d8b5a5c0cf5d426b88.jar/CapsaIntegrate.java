import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaIntegrate extends AbstractBox
{
    String PosAnt_Fill;
    boolean PosSeg_Fill;
    boolean PosicioMesProperaGlobal;
    
    public CapsaIntegrate() {
        super.color = 3;
        super.dibs = new NeuterBox[] { NeuterBox.crea("integral"), NeuterBox.crea("d") };
    }
    
    public final void inicialitzaModalitat(final Object o) {
        this.PosAnt_Fill = (String)o;
        this.PosSeg_Fill = this.PosAnt_Fill.startsWith("integral");
        this.PosicioMesProperaGlobal = !this.PosAnt_Fill.endsWith("f");
        if (!this.PosSeg_Fill) {
            this.AfegirNoCalc(new EmptyBox());
            this.AfegirNoCalc(new EmptyBox());
        }
    }
    
    public final int nombreMinimDeFills() {
        int n = 2;
        if (this.PosicioMesProperaGlobal) {
            ++n;
        }
        return n;
    }
    
    public final int getFactorEscalaAbsolutFill(final BoxComponent boxComponent, final int n) {
        if (n < 2) {
            return 80;
        }
        return 100;
    }
    
    public final boolean fbvisible(final int n) {
        return this.PosSeg_Fill || n == 2;
    }
    
    public final void enCalculRect() {
        if (!(super.fill[0] instanceof EmptyBox) || !(super.fill[1] instanceof EmptyBox)) {
            this.PosSeg_Fill = true;
        }
        if (super.fill[0] instanceof EmptyBox && super.fill[1] instanceof EmptyBox && this.PosAnt_Fill.startsWith("iintegral")) {
            this.PosSeg_Fill = false;
        }
        if (!this.PosSeg_Fill) {
            super.dibs[0].x = this.em(0.0f);
            super.dibs[0].y = 0;
            super.fill[0].x = this.em(0.5f);
            super.fill[0].y = this.em(3.0f) - super.fill[0].baseline;
            super.fill[1].x = this.em(1.1f);
            super.fill[1].y = this.em(0.3f) - super.fill[1].baseline;
            super.width = super.dibs[0].x + super.dibs[0].width;
            super.height = super.dibs[0].height;
            super.baseline = super.dibs[0].baseline;
        }
        else {
            super.fill[0].x = this.em(0.5f);
            super.fill[1].x = this.em(1.1f);
            super.fill[1].y = 0;
            if (this.em(0.3f) - super.fill[1].baseline + super.fill[1].height <= super.dibs[0].height / 2) {
                super.baseline = super.fill[1].y + super.fill[1].baseline - this.em(0.3f) + super.dibs[0].baseline;
            }
            else {
                super.baseline = super.fill[1].yh() - super.dibs[0].height / 2 + super.dibs[0].baseline + 1;
            }
            super.dibs[0].x = this.em(0.0f);
            super.dibs[0].y = super.baseline - super.dibs[0].baseline;
            if (this.em(3.0f) - super.fill[0].baseline > super.dibs[0].height / 2) {
                super.fill[0].y = super.dibs[0].y + this.em(3.0f) - super.fill[0].baseline;
            }
            else {
                super.fill[0].y = super.dibs[0].y + super.dibs[0].height / 2 + 1;
            }
            super.width = Math.max(super.fill[0].xw(), super.fill[1].xw());
            super.height = super.fill[0].yh();
        }
        if (this.PosicioMesProperaGlobal) {
            super.fill[2].x = super.width + this.em(0.0f);
            super.fill[2].y = super.baseline - super.fill[2].baseline;
            final int max = Math.max(0, -super.fill[2].y);
            final AbstractNeuterBox abstractNeuterBox = super.dibs[0];
            abstractNeuterBox.y += max;
            final AbstractBox abstractBox = super.fill[0];
            abstractBox.y += max;
            final AbstractBox abstractBox2 = super.fill[1];
            abstractBox2.y += max;
            final AbstractBox abstractBox3 = super.fill[2];
            abstractBox3.y += max;
            super.height += max;
            super.baseline += max;
            super.dibs[1].x = super.fill[2].xw() + this.em(0.1f);
            super.dibs[1].y = super.baseline - super.dibs[1].baseline;
            super.width = super.dibs[1].x + super.dibs[1].width;
            super.height = Math.max(super.height, super.fill[2].yh());
            super.ndibs = 2;
        }
        else {
            super.ndibs = 1;
        }
        super.width += this.em(0.0f);
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        if (this.PosicioMesProperaGlobal) {
            if (point2.x < this.em(0.0f) + super.dibs[0].width / 2) {
                return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
            }
            if (point2.x >= super.width - this.em(0.0f)) {
                return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
            }
            return super.fill[2].PosicioMesProperaGlobal(point, array);
        }
        else {
            if (point2.x < super.width / 2) {
                return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
            }
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode != 0) {
            return null;
        }
        if (this.PosSeg_Fill) {
            if (this.PosicioMesProperaGlobal) {
                return "integral";
            }
            return "integralf";
        }
        else {
            if (this.PosicioMesProperaGlobal) {
                return "iintegral";
            }
            return "iintegralf ";
        }
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            if (this.PosSeg_Fill) {
                super.fill[0].script(boxScripting);
                super.fill[1].script(boxScripting);
            }
            if (this.PosicioMesProperaGlobal) {
                super.fill[2].script(boxScripting);
            }
        }
        else if (boxScripting.scriptMode == 1) {
            if (this.PosSeg_Fill) {
                boxScripting.openTag("msubsup");
            }
            boxScripting.openTag("mo");
            boxScripting.appendText("\u222b");
            boxScripting.closeTag();
            if (this.PosSeg_Fill) {
                XMLBoxUtils.mrow(super.fill[0], boxScripting);
                XMLBoxUtils.mrow(super.fill[1], boxScripting);
                boxScripting.closeTag();
            }
            if (this.PosicioMesProperaGlobal) {
                XMLBoxUtils.mrow(super.fill[2], boxScripting);
                boxScripting.openTag("mo");
                boxScripting.appendText("\u2146");
                boxScripting.closeTag();
            }
        }
        else if (boxScripting.scriptMode == 2) {
            if (this.PosSeg_Fill) {
                if (this.PosicioMesProperaGlobal) {
                    boxScripting.openTag("defined_integral_operator");
                }
                else {
                    boxScripting.openTag("defined_alone_integral_operator");
                }
                boxScripting.appendText("(");
                super.fill[0].script(boxScripting);
                boxScripting.appendText(",");
                super.fill[1].script(boxScripting);
                boxScripting.appendText(")");
                boxScripting.closeTag();
            }
            else if (this.PosicioMesProperaGlobal) {
                boxScripting.appendText(" integral_operator ");
            }
            else {
                boxScripting.appendText(" alone_integral_operator ");
            }
            if (this.PosicioMesProperaGlobal) {
                super.fill[2].script(boxScripting);
                boxScripting.appendText(" differential_operator ");
            }
        }
    }
    
    public final int getSplitFactor(final int n) {
        return 700;
    }
}
