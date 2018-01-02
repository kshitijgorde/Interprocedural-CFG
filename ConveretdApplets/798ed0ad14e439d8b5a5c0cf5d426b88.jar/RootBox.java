import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class RootBox extends AbstractBox
{
    String PosAnt_Fill;
    boolean PosSeg_Fill;
    int PosicioMesProperaGlobal;
    AbstractNeuterBox PosicioReal;
    AbstractNeuterBox draw;
    Rectangle r;
    boolean em;
    
    public RootBox() {
        this.PosicioMesProperaGlobal = 0;
        this.PosicioReal = null;
        this.draw = null;
        this.r = new Rectangle();
        super.color = 3;
    }
    
    public final void inicialitzaModalitat(final Object o) {
        this.PosAnt_Fill = (String)o;
        this.em = !this.PosAnt_Fill.equals("puresqrt");
        if (this.PosAnt_Fill.equals("sqrt")) {
            this.AfegirNoCalc(new EmptyBox());
        }
    }
    
    public final int nombreMinimDeFills() {
        return this.em ? 2 : 1;
    }
    
    public final void enCalculRect() {
        if (super.nfills == 2 && !(super.fill[0] instanceof EmptyBox)) {
            this.PosAnt_Fill = "root";
        }
        final int em = this.em ? 1 : 0;
        super.fill[em].x = this.em(0.0f) + this.em(0.8f) + this.em(0.0f);
        super.fill[em].y = 0 + this.em(0.05f) + this.em(0.1f);
        super.width = super.fill[em].xw() + this.em(0.1f) + this.em(0.0f);
        super.height = super.fill[em].yh() + this.em(0.0f) + 0;
        int n;
        if (this.em) {
            super.fill[0].x = this.em(0.0f) + this.em(0.4f) - super.fill[0].width;
            if (this.em(0.4f) - super.fill[0].baseline + super.fill[0].height < Math.round(0.5f * (this.em(0.05f) + this.em(0.1f) + super.fill[em].height + this.em(0.0f)))) {
                super.fill[0].y = 0 + this.em(0.4f) - super.fill[0].baseline;
            }
            else {
                super.fill[0].y = 0 + Math.round(0.5f * (this.em(0.05f) + this.em(0.1f) + super.fill[em].height + this.em(0.0f))) - super.fill[0].height - this.em(0.1f);
            }
            n = super.fill[0].y - 0;
        }
        else {
            n = 0;
        }
        if (n < 0) {
            if (this.em) {
                final AbstractBox abstractBox = super.fill[0];
                abstractBox.y -= n;
            }
            final AbstractBox abstractBox2 = super.fill[em];
            abstractBox2.y -= n;
            super.height -= n;
        }
        final int n2 = (this.em ? super.fill[0].x : 0) - this.em(0.0f);
        if (n2 < 0) {
            if (this.em) {
                final AbstractBox abstractBox3 = super.fill[0];
                abstractBox3.x -= n2;
            }
            final AbstractBox abstractBox4 = super.fill[em];
            abstractBox4.x -= n2;
            super.width -= n2;
        }
        super.baseline = super.fill[em].y + super.fill[em].baseline;
        if (super.dibs == null) {
            super.dibs = new NeuterBox[1];
        }
        if (this.em && super.fill[0].width > this.em(0.4f)) {
            if (!this.PosSeg_Fill || this.PosicioMesProperaGlobal != super.I.getSize()) {
                this.PosicioMesProperaGlobal = super.I.getSize();
                if (this.draw == null) {
                    this.draw = new CapsaNeutraPoly2(17);
                }
                super.dibs[0] = this.draw;
                this.PosSeg_Fill = true;
            }
        }
        else if (this.PosSeg_Fill || this.PosicioMesProperaGlobal != super.I.getSize()) {
            this.PosicioMesProperaGlobal = super.I.getSize();
            if (this.PosicioReal == null) {
                this.PosicioReal = new CapsaNeutraPoly2(16);
            }
            super.dibs[0] = this.PosicioReal;
            this.PosSeg_Fill = false;
        }
        final int x = super.fill[em].x - this.em(0.0f) - this.em(0.8f);
        final int y = super.fill[em].y - this.em(0.1f) - this.em(0.05f);
        final int em2 = this.em(0.8f);
        if (super.dibs != null) {
            super.dibs[0].width = this.em(0.8f);
            super.dibs[0].height = this.em(0.1f) + super.fill[em].height + this.em(0.0f);
            super.dibs[0].x = x;
            super.dibs[0].y = y + 1;
        }
        this.r.width = this.em(0.035f) + this.em(0.0f) + super.fill[em].width + this.em(0.1f);
        this.r.height = this.em(0.05f);
        this.r.x = x + em2 - this.em(0.08f);
        this.r.y = y;
    }
    
    protected final void I(final Graphics graphics) {
        if (this.PosSeg_Fill) {
            final int em = this.em ? 1 : 0;
            graphics.fillRect(this.em(0.0f), super.fill[em].y - this.em(0.1f) - this.em(0.05f) + Math.round(0.5f * (this.em(0.05f) + this.em(0.1f) + super.fill[em].height + this.em(0.0f))) - 1 - this.em(0.0425f), super.fill[em].x - this.em(0.0f) - this.em(0.8f) - this.em(0.0f) + this.em(0.05f) + this.em(0.08f), this.em(0.05f));
        }
        graphics.translate(super.dibs[0].x, super.dibs[0].y);
        ((CapsaNeutraPoly2)super.dibs[0]).C.draw(0, 0, super.dibs[0].width, super.dibs[0].height, true, true, graphics);
        graphics.translate(-super.dibs[0].x, -super.dibs[0].y);
        graphics.fillRect(this.r.x, this.r.y, this.r.width, this.r.height);
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        if (point2.x < this.em(0.0f)) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        if (point2.x >= super.width - this.em(0.0f)) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        final int em = this.em ? 1 : 0;
        if (this.em && point2.x < super.fill[em].x) {
            return super.fill[0].PosicioMesProperaGlobal(point, array);
        }
        return super.fill[em].PosicioMesProperaGlobal(point, array);
    }
    
    public final boolean fbvisible(final int n) {
        return !this.em || !this.PosAnt_Fill.equals("sqrt") || n != 0;
    }
    
    public final int getFactorEscalaAbsolutFill(final BoxComponent boxComponent, final int n) {
        if (this.em && n == 0) {
            return 60;
        }
        return 100;
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return this.PosAnt_Fill.equals("sqrt") ? "sqrt" : "root";
        }
        if (boxScripting.scriptMode == 1) {
            return this.PosAnt_Fill.equals("root") ? "mroot" : "msqrt";
        }
        if (boxScripting.scriptMode == 2) {
            return (super.fill[0] instanceof EmptyBox) ? "do_sqrt" : "do_root";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            XMLBoxUtils.mrow(super.fill[this.em], boxScripting);
            if (this.PosAnt_Fill.equals("root")) {
                XMLBoxUtils.mrow(super.fill[0], boxScripting);
            }
        }
        else if (boxScripting.scriptMode == 0) {
            if (this.PosAnt_Fill.equals("root")) {
                super.fill[0].script(boxScripting);
            }
            super.fill[1].script(boxScripting);
        }
        else if (boxScripting.scriptMode == 2) {
            super.fill[1].script(boxScripting);
            if (!(super.fill[0] instanceof EmptyBox)) {
                super.fill[0].script(boxScripting);
            }
        }
    }
    
    public final int getSplitFactor(final int n) {
        return 700;
    }
}
