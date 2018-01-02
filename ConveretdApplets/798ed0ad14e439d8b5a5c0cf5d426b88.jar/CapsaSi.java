import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaSi extends AbstractBox implements CommandsOwner
{
    public int extra;
    
    public CapsaSi() {
        this.extra = 0;
        super.color = 3;
    }
    
    public final void inicialitzaFills(final AbstractBox[] array) {
        final int extra = this.extra;
        this.extra = -1;
        super.inicialitzaFills(array);
        if (extra != 1) {
            this.AfegirNoCalc(new EmptyBox());
        }
        this.extra = 1;
        this.enAfegir(null, null, -1);
    }
    
    public final void inicialitzaModalitat(final Object o) {
        this.extra = (int)o;
    }
    
    public final int nombreMinimDeFills() {
        return 2;
    }
    
    public final void enTreure() {
        final boolean b = 2 * ((super.nfills - this.extra) / 2) != super.nfills - this.extra;
        super.ndibs = super.nfills - this.extra + 1;
        if (b) {
            super.dibs[super.nfills - this.extra - 1] = super.dibs[super.dibs.length - 2];
        }
        super.dibs[super.nfills - this.extra] = super.dibs[super.dibs.length - 1];
    }
    
    public final void enAfegir(final BoxComponent boxComponent, final AbstractBox abstractBox, final int n) {
        if (this.extra == -1) {
            return;
        }
        final int n2 = (super.nfills - this.extra) / 2;
        final boolean b = 2 * n2 != super.nfills - this.extra;
        (super.dibs = new NeuterBox[super.nfills - this.extra + 1])[0] = NeuterBox.crea("if");
        super.dibs[1] = NeuterBox.crea("then");
        for (int i = 1; i < n2; ++i) {
            super.dibs[2 * i] = NeuterBox.crea("else_if");
            super.dibs[2 * i + 1] = NeuterBox.crea("then");
        }
        if (b) {
            super.dibs[2 * n2] = NeuterBox.crea("else");
        }
        super.dibs[super.nfills - this.extra] = NeuterBox.crea("end");
        super.ndibs = super.dibs.length;
        if (boxComponent != null) {
            for (int j = 0; j < super.dibs.length; ++j) {
                super.dibs[j].setFont(boxComponent, super.I);
                super.dibs[j].calculRect(boxComponent);
            }
        }
    }
    
    public final void enCalculRect() {
        final int n = (super.nfills - this.extra) / 2;
        final boolean b = 2 * n != super.nfills - this.extra;
        super.dibs[0].x = this.em(0.1f);
        super.dibs[0].y = 0 + super.fill[0].baseline - super.dibs[0].baseline;
        super.fill[0].x = super.dibs[0].x + super.dibs[0].width + this.em(0.4f);
        super.fill[0].y = 0;
        super.dibs[1].x = super.fill[0].xw() + this.em(0.4f);
        super.dibs[1].y = 0 + super.fill[0].baseline - super.dibs[1].baseline;
        super.fill[1].x = this.em(0.1f) + this.em(1.0f);
        super.fill[1].y = super.fill[0].yh() + this.em(0.0f);
        super.width = Math.max(super.dibs[1].x + super.dibs[1].width, super.fill[1].xw());
        for (int i = 1; i < n; ++i) {
            final int y = super.fill[2 * i - 1].y + super.fill[2 * i - 1].height + this.em(0.0f);
            super.dibs[2 * i].x = this.em(0.1f);
            super.dibs[2 * i].y = y + super.fill[2 * i].baseline - super.dibs[2 * i].baseline;
            super.fill[2 * i].x = super.dibs[2 * i].x + super.dibs[2 * i].width + this.em(0.4f);
            super.fill[2 * i].y = y;
            super.dibs[2 * i + 1].x = super.fill[2 * i].xw() + this.em(0.4f);
            super.dibs[2 * i + 1].y = y + super.fill[2 * i].baseline - super.dibs[2 * i + 1].baseline;
            super.fill[2 * i + 1].x = this.em(0.1f) + this.em(1.0f);
            super.fill[2 * i + 1].y = super.fill[2 * i].yh() + this.em(0.0f);
            super.width = Math.max(super.width, super.dibs[2 * i + 1].x + super.dibs[2 * i + 1].width);
            super.width = Math.max(super.width, super.fill[2 * i + 1].x + super.fill[2 * i + 1].width);
        }
        super.dibs[2 * n].x = this.em(0.1f);
        super.dibs[2 * n].y = super.fill[2 * n - 1].yh() + this.em(0.0f);
        if (b) {
            super.fill[2 * n].x = this.em(0.1f) + this.em(1.0f);
            super.fill[2 * n].y = super.dibs[2 * n].y + super.dibs[2 * n].height + this.em(0.0f);
            super.dibs[2 * n + 1].x = this.em(0.1f);
            super.dibs[2 * n + 1].y = super.fill[2 * n].yh() + this.em(0.0f);
            super.width = Math.max(super.width, super.fill[2 * n].xw());
        }
        if (this.extra == 1) {
            super.fill[super.nfills - 1].x = super.dibs[super.ndibs - 1].x + super.dibs[super.ndibs - 1].width + this.em(" ");
            super.fill[super.nfills - 1].y = super.dibs[super.ndibs - 1].y;
            final AbstractNeuterBox abstractNeuterBox = super.dibs[super.ndibs - 1];
            abstractNeuterBox.y += super.fill[super.nfills - 1].baseline - super.dibs[super.ndibs - 1].baseline;
            super.width = Math.max(super.width, super.fill[super.nfills - 1].xw());
        }
        if (this.extra == 0) {
            super.height = super.dibs[super.nfills - this.extra].y + super.dibs[super.nfills - this.extra].height + 0;
        }
        else {
            super.height = super.fill[super.nfills - 1].y + super.fill[super.nfills - 1].height + 0;
        }
        super.baseline = super.fill[0].y + super.fill[0].baseline;
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        return this.I(point, array);
    }
    
    public final boolean posicio_final() {
        return false;
    }
    
    public final boolean inserta_final() {
        return true;
    }
    
    public final boolean fbvisible(final int n) {
        return this.extra != 1 || n != super.nfills - 1;
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            final boolean b = !(super.fill[super.nfills - 1] instanceof EmptyBox);
            final boolean b2 = b && boxScripting.oneChild;
            if (b2) {
                boxScripting.openTag("mrow");
            }
            XMLBoxUtils.beginBox(boxScripting, this, "apply");
            XMLBoxUtils.csymbol(boxScripting, "if");
            for (int i = 0; i < super.nfills - 1; ++i) {
                XMLBoxUtils.mrow(super.fill[i], boxScripting);
            }
            XMLBoxUtils.endBox(boxScripting, this, "apply");
            if (b) {
                super.fill[super.nfills - 1].script(boxScripting);
                if (b2) {
                    boxScripting.closeTag();
                }
            }
        }
        else if (boxScripting.scriptMode == 0) {
            boxScripting.appendScript("\\beginif ");
            super.fill[0].script(boxScripting);
            boxScripting.appendScript("&");
            super.fill[1].script(boxScripting);
            final int n = (super.nfills - this.extra) / 2;
            final boolean b3 = 2 * n != super.nfills - this.extra;
            for (int j = 1; j < n; ++j) {
                boxScripting.appendScript("\\cr ");
                super.fill[2 * j].script(boxScripting);
                boxScripting.appendScript("&");
                super.fill[2 * j + 1].script(boxScripting);
            }
            if (b3) {
                boxScripting.appendScript("\\cr ");
                super.fill[2 * n].script(boxScripting);
            }
            boxScripting.appendScript("\\endif ");
            if (this.extra == 1) {
                super.fill[super.nfills - 1].script(boxScripting);
            }
        }
        else if (boxScripting.scriptMode == 2) {
            boxScripting.appendText(" if ");
            super.fill[0].script(boxScripting);
            boxScripting.appendText(" then ");
            super.fill[1].script(boxScripting);
            final int n2 = (super.nfills - this.extra) / 2;
            final boolean b4 = 2 * n2 != super.nfills - this.extra;
            for (int k = 1; k < n2; ++k) {
                boxScripting.appendText(" else_if ");
                super.fill[2 * k].script(boxScripting);
                boxScripting.appendText(" then ");
                super.fill[2 * k + 1].script(boxScripting);
            }
            if (b4) {
                boxScripting.appendText(" else ");
                super.fill[2 * n2].script(boxScripting);
            }
            boxScripting.appendText(" end ");
            if (this.extra == 1) {
                super.fill[super.nfills - 1].script(boxScripting);
                boxScripting.appendText(" ");
            }
        }
    }
    
    public static final boolean esCondicio(final int n, final int n2, final int n3) {
        return n % 2 == 0 && n + 1 + n3 != n2;
    }
    
    public final int getSplitFactor(final int n) {
        for (int i = 0; i < super.ndibs; ++i) {
            if (n > super.dibs[i].y && n < super.dibs[i].yh()) {
                return 800;
            }
        }
        return 600;
    }
}
