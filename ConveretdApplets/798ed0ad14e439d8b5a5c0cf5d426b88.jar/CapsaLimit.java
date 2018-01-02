import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaLimit extends AbstractBox
{
    int PosAnt_Fill;
    String PosSeg_Fill;
    
    public CapsaLimit() {
        super.color = 3;
        super.dibs = new NeuterBox[] { NeuterBox.crea("lim"), NeuterBox.crea("\\longrightarrow"), NeuterBox.crea("") };
        super.ndibs = super.dibs.length;
    }
    
    public final void inicialitzaModalitat(final Object o) {
        this.PosSeg_Fill = (String)o;
        if (this.PosSeg_Fill.equals("limit")) {
            this.setTipus(0);
        }
        else if (this.PosSeg_Fill.equals("rightlimit")) {
            this.setTipus(1);
        }
        else if (this.PosSeg_Fill.equals("leftlimit")) {
            this.setTipus(-1);
        }
    }
    
    public final void setTipus(final int posAnt_Fill) {
        switch (this.PosAnt_Fill = posAnt_Fill) {
            case 0: {
                super.dibs[2] = NeuterBox.crea("");
                break;
            }
            case 1: {
                super.dibs[2] = NeuterBox.crea("+");
                break;
            }
            case -1: {
                super.dibs[2] = NeuterBox.crea("-");
                break;
            }
        }
    }
    
    public final int nombreMinimDeFills() {
        return 2;
    }
    
    public final void enCalculRect() {
        super.baseline = super.dibs[0].baseline;
        super.dibs[0].y = super.baseline - super.dibs[0].baseline;
        final int n = super.dibs[0].y + super.dibs[0].height + this.em(-0.15f) + Math.max(super.fill[0].baseline, super.fill[1].baseline);
        super.fill[0].y = n - super.fill[0].baseline;
        super.dibs[1].y = n - super.dibs[1].baseline;
        super.fill[1].y = n - super.fill[1].baseline;
        super.dibs[2].y = super.fill[1].y - super.dibs[2].baseline / 2;
        super.height = Math.max(super.fill[0].yh(), super.fill[1].yh());
        super.dibs[0].x = this.em(0.08f) + Math.max(0, super.fill[0].width + super.dibs[1].width / 2 - super.dibs[0].width / 2);
        super.fill[0].x = super.dibs[0].x + super.dibs[0].width / 2 - super.dibs[1].width / 2 - super.fill[0].width;
        super.dibs[1].x = super.fill[0].xw();
        super.fill[1].x = super.dibs[1].x + super.dibs[1].width;
        super.dibs[2].x = super.fill[1].xw();
        super.width = Math.max(super.dibs[0].x + super.dibs[0].width, super.dibs[2].x + super.dibs[2].width) + this.em(0.08f);
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
        if (point2.x < super.fill[1].x - this.em(0.35f)) {
            return super.fill[0].PosicioMesProperaGlobal(point, array);
        }
        if (point2.x < super.width - this.em(0.08f)) {
            return super.fill[1].PosicioMesProperaGlobal(point, array);
        }
        return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
    }
    
    public final int getFactorEscalaAbsolutFill(final BoxComponent boxComponent, final int n) {
        if (n == 0 || n == 1) {
            return 70;
        }
        return 100;
    }
    
    public final int getFactorEscalaDibAbsolut(final int n) {
        if (n == 1 || n == 2) {
            return 70;
        }
        return 100;
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            switch (this.PosAnt_Fill) {
                case 0: {
                    return "limit";
                }
                case 1: {
                    return "rightlimit";
                }
                case -1: {
                    return "leftlimit";
                }
            }
        }
        else if (boxScripting.scriptMode == 1) {
            return "munder";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            XMLBoxUtils.mo(boxScripting, "lim");
            boxScripting.openTag("mrow");
            XMLBoxUtils.mrow(super.fill[0], boxScripting);
            if (this.PosAnt_Fill == 0) {
                XMLBoxUtils.mo(boxScripting, "\u2192");
            }
            if (this.PosAnt_Fill == 1) {
                XMLBoxUtils.mo(boxScripting, "\u2198");
            }
            if (this.PosAnt_Fill == -1) {
                XMLBoxUtils.mo(boxScripting, "\u2197");
            }
            XMLBoxUtils.mrow(super.fill[1], boxScripting);
            boxScripting.closeTag();
        }
        else if (boxScripting.scriptMode == 2) {
            final int posAnt_Fill = this.PosAnt_Fill;
            boxScripting.openTag("limit_operator");
            boxScripting.appendText("(");
            super.fill[0].script(boxScripting);
            boxScripting.appendText(",");
            super.fill[1].script(boxScripting);
            if (posAnt_Fill != 0) {
                boxScripting.appendText("," + posAnt_Fill);
            }
            boxScripting.appendText(")");
            boxScripting.closeTag();
        }
    }
    
    public final int getSplitFactor(final int n) {
        for (int i = 0; i < super.ndibs; ++i) {
            if (n > super.dibs[i].y && n < super.dibs[i].yh()) {
                return 800;
            }
        }
        return 700;
    }
}
