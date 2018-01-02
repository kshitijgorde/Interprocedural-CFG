import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaComanda extends AbstractBox
{
    AbstractBox Composa;
    AbstractBox PosAnt_Fora;
    
    public CapsaComanda() {
        this.Composa = null;
        this.PosAnt_Fora = null;
        super.color = 4;
    }
    
    public final void inicialitzaModalitat(final Object o) {
        if (o) {
            super.estil = 1;
            super.forca_estil = 1;
        }
    }
    
    public final void inicialitzaFills() {
        final MultilineBox multilineBox = new MultilineBox();
        multilineBox.inicialitzaFills();
        this.AfegirNoCalc(multilineBox);
        this.AfegirNoCalc(new EmptyBox());
    }
    
    public final void inicialitzaFills(final AbstractBox[] array) {
        this.AfegirNoCalc(array[0]);
        if (array.length == 1) {
            this.AfegirNoCalc(new EmptyBox());
            return;
        }
        if (array[1] instanceof CapsaObjectAndDomain) {
            this.AfegirNoCalc(array[1].fill[0]);
            this.AfegirNoCalc(array[1].fill[1]);
        }
        else if (array.length >= 2) {
            this.AfegirNoCalc(array[1]);
        }
        else if ((super.estil & 0x1) != 0x0) {
            this.AfegirNoCalc(new EmptyBox());
        }
        for (int i = 2; i < array.length; ++i) {
            this.AfegirNoCalc(array[i]);
        }
    }
    
    public final void comentat(final BoxComponent boxComponent) {
        if ((super.estil & 0x1) != 0x0) {
            this.setStyleRec(1, false);
            if (this.Composa != null) {
                this.SubstitueixCapsa(this.Composa, 1, boxComponent);
                this.Composa = null;
            }
            if (this.PosAnt_Fora != null) {
                this.SubstitueixCapsa(this.PosAnt_Fora, 2, boxComponent);
                this.PosAnt_Fora = null;
            }
        }
        else {
            this.setStyleRec(1, true);
            if (!this.fillBuit(1)) {
                this.SubstitueixCapsa(new EmptyBox(), 1, boxComponent);
            }
            if (super.nfills >= 3 && !this.fillBuit(2)) {
                this.SubstitueixCapsa(new EmptyBox(), 2, boxComponent);
            }
        }
    }
    
    public final int nombreMinimDeFills() {
        return 2;
    }
    
    public final void enCalculRect() {
    }
    
    public final void enCalculRect(final BoxComponent boxComponent) {
        int userUnit;
        if (super.recommendedWidth != null) {
            userUnit = super.recommendedWidth.toUserUnit(super.EM, boxComponent.getDPI());
        }
        else {
            userUnit = Integer.MAX_VALUE;
        }
        super.fill[0].x = this.em(0.4f);
        super.fill[0].y = 0;
        super.baseline = super.fill[0].baseline;
        super.width = super.fill[0].xw();
        super.height = super.fill[0].height;
        if (!this.fillBuit(1) || !this.fillBuit(2)) {
            super.fill[1].x = super.width + this.em(2.0f);
            if (super.fill[1].xw() < userUnit) {
                super.width = super.fill[1].xw();
                super.fill[1].y = super.baseline - super.fill[1].baseline;
                super.height = Math.max(super.height, super.fill[1].yh());
                final int max = Math.max(0, super.fill[1].baseline - super.baseline);
                super.baseline += max;
                super.height += max;
                final AbstractBox abstractBox = super.fill[0];
                abstractBox.y += max;
                final AbstractBox abstractBox2 = super.fill[1];
                abstractBox2.y += max;
                if (!this.fillBuit(2)) {
                    super.fill[2].x = super.fill[1].xw() + this.em(1.2f);
                    if (super.fill[2].xw() < userUnit) {
                        super.width = Math.max(super.width, super.fill[2].xw());
                        super.fill[2].y = super.baseline - super.fill[2].baseline;
                        super.height = Math.max(super.height, super.fill[2].yh());
                        final int max2 = Math.max(0, super.fill[2].baseline - super.baseline);
                        super.baseline += max2;
                        super.height += max2;
                        final AbstractBox abstractBox3 = super.fill[0];
                        abstractBox3.y += max2;
                        final AbstractBox abstractBox4 = super.fill[1];
                        abstractBox4.y += max2;
                        final AbstractBox abstractBox5 = super.fill[2];
                        abstractBox5.y += max2;
                    }
                    else {
                        super.fill[2].x = super.fill[1].x + this.em(1.2f);
                        super.fill[2].y = super.fill[1].yh() + this.em(0.1f);
                        super.width = Math.max(super.width, super.fill[2].xw());
                        super.height = Math.max(super.height, super.fill[2].yh());
                        super.baseline = super.fill[2].yb();
                    }
                }
            }
            else {
                super.fill[1].x = this.em(0.4f) + this.em(2.0f);
                super.fill[1].y = super.fill[0].yh() + this.em(0.1f);
                super.width = Math.max(super.width, super.fill[1].xw());
                super.height = super.fill[1].yh();
                super.baseline = super.fill[1].yb();
                if (!this.fillBuit(2)) {
                    super.fill[2].x = super.fill[1].xw() + this.em(1.2f);
                    if (super.fill[2].xw() < userUnit) {
                        super.width = Math.max(super.width, super.fill[2].xw());
                        super.fill[2].y = super.baseline - super.fill[2].baseline;
                        super.height = Math.max(super.height, super.fill[2].yh());
                        final int max3 = Math.max(0, super.fill[1].y - super.fill[2].y);
                        super.baseline += max3;
                        super.height += max3;
                        final AbstractBox abstractBox6 = super.fill[1];
                        abstractBox6.y += max3;
                        final AbstractBox abstractBox7 = super.fill[2];
                        abstractBox7.y += max3;
                    }
                    else {
                        super.fill[2].x = super.fill[1].x + this.em(1.2f);
                        super.fill[2].y = super.fill[1].yh() + this.em(0.1f);
                        super.width = Math.max(super.width, super.fill[2].xw());
                        super.height = Math.max(super.height, super.fill[2].yh());
                        super.baseline = super.fill[2].yb();
                    }
                }
            }
        }
        for (int n = super.nfills - 1; n >= 1 && this.fillBuit(n); --n) {
            super.fill[n].x = 0;
            super.fill[n].y = 0;
            super.fill[n].width = 0;
            super.fill[n].height = 0;
        }
    }
    
    protected final void onPaint(final BoxComponent boxComponent, final Graphics graphics) {
        if ((super.estil & 0x1) != 0x0) {
            return;
        }
        if (GraphicsUtils.isFileGraphics(graphics)) {
            GraphicsUtils.setLineWidth(graphics, super.EM * 1.0f / 19.0f);
        }
        else {
            GraphicsUtils.setLineWidth(graphics, 1.0f);
        }
        if ((super.estil & 0x2) == 0x0) {
            IsGraphics2D.drawLine(graphics, 0, 0, 0, super.height - 1);
        }
        if (!this.fillBuit(1) || !this.fillBuit(2)) {
            final Color color = graphics.getColor();
            boxComponent.setColor(graphics, boxComponent.getColor(5, this));
            final int[] array = new int[7];
            final int[] array2 = new int[7];
            final int em = this.em(0.083333336f);
            array[0] = super.fill[1].x - this.em(1.5f);
            array2[0] = super.fill[1].yb() - this.em(0.3125f) - em;
            array[1] = array[0] + this.em(1.0f) - 3 * em;
            array2[1] = array2[0];
            array[2] = array[1] - 1 * em;
            array2[2] = array2[1] - 2 * em;
            array[3] = array[2] + 4 * em;
            array2[3] = array2[2] + 3 * em;
            array[4] = array[3] - 4 * em;
            array2[4] = array2[3] + 3 * em;
            array[5] = array[4] + 1 * em;
            array2[5] = array2[4] - 2 * em;
            array[6] = array[5] - this.em(1.0f) + 3 * em;
            array2[6] = array2[5];
            GraphicsUtils.setRendering(graphics, true);
            graphics.fillPolygon(array, array2, 7);
            GraphicsUtils.setRendering(graphics, false);
            if (super.nfills == 3 && !(super.fill[2] instanceof EmptyBox)) {
                graphics.drawString("::", super.fill[2].x - this.em(0.8f), super.baseline);
            }
            graphics.setColor(color);
        }
    }
    
    private boolean fillBuit(final int n) {
        return n >= super.nfills || super.fill[n] instanceof EmptyBox || (super.fill[n] instanceof MultilineBox && super.fill[n].nfills == 1 && super.fill[n].fill[0] instanceof EmptyBox);
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        return super.fill[0].PosicioMesPropera(point, array);
    }
    
    public final BoxPosition PosSeg_Fill(final int n, final AbstractBox[] array) {
        return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
    }
    
    public final BoxPosition PosAnt_Fora(final AbstractBox[] array) {
        return super.fill[0].PosAnt_Fora(array);
    }
    
    public final boolean fbvisible(final int n) {
        return false;
    }
    
    public static final void eliminaResultats(final BoxComponent boxComponent, final AbstractBox abstractBox) {
        eliminaResultatPrim(abstractBox);
        if (boxComponent != null) {
            abstractBox.Composa(boxComponent);
        }
    }
    
    private static void eliminaResultatPrim(final AbstractBox abstractBox) {
        if (abstractBox instanceof CapsaComanda) {
            while (abstractBox.nfills > 1) {
                abstractBox.Treure(1, null);
            }
            abstractBox.AfegirNoCalc(new EmptyBox(), 1);
        }
        for (int i = 0; i < abstractBox.nfills; ++i) {
            eliminaResultatPrim(abstractBox.fill[i]);
        }
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode != 1 && boxScripting.scriptMode != 0) {
            return null;
        }
        if ((super.estil & 0x1) != 0x0) {
            return "comment";
        }
        return "command";
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            if ((super.estil & 0x1) != 0x0) {
                super.nfills = 1;
            }
            if (super.nfills >= 3) {
                super.fill[0].script(boxScripting);
                boxScripting.openTag("objectanddomain");
                super.fill[1].script(boxScripting);
                super.fill[2].script(boxScripting);
                boxScripting.closeTag();
            }
            super.onScript(boxScripting);
        }
        else if (boxScripting.scriptMode == 1) {
            for (int i = 0; i < super.nfills; ++i) {
                String s;
                if (i == 0) {
                    if ((super.estil & 0x1) != 0x0) {
                        s = null;
                    }
                    else {
                        s = "input";
                    }
                }
                else if (i == 1) {
                    s = "output";
                }
                else if (i == 2) {
                    s = "domain";
                }
                else {
                    s = null;
                }
                if (i != super.nfills - 1 || !this.fillBuit(i)) {
                    if (s != null) {
                        boxScripting.openTag(s);
                        boxScripting.setNameSpace("wiris");
                    }
                    boxScripting.openTag("math");
                    boxScripting.appendAttribute("xmlns", "http://www.w3.org/1998/Math/MathML");
                    super.fill[i].script(boxScripting);
                    boxScripting.closeTag();
                    if (s != null) {
                        boxScripting.closeTag();
                    }
                }
            }
        }
        else if (boxScripting.scriptMode == 2) {
            if ((super.estil & 0x1) != 0x0) {
                return;
            }
            super.fill[0].script(boxScripting);
        }
        else if (boxScripting.scriptMode == -1) {
            for (int j = 0; j < super.nfills; ++j) {
                super.fill[j].script(boxScripting);
            }
        }
    }
    
    public final Length getRecommendedWidth(final int n, final BoxComponent boxComponent) {
        if (super.recommendedWidth != null) {
            int n2 = super.recommendedWidth.toUserUnit(super.EM, boxComponent.getDPI());
            if (n2 >= 0) {
                int n3;
                if (n == 2) {
                    n3 = n2 - this.em(1.2f);
                }
                else if (n == 1) {
                    n3 = n2 - this.em(2.4f);
                }
                else {
                    n3 = n2 - this.em(0.4f);
                }
                n2 = Math.max(0, n3);
            }
            return Length.create(n2, boxComponent.getDPI());
        }
        return null;
    }
    
    public final int getSplitFactor(final int n) {
        return 200;
    }
    
    public final boolean isEmpty(final BoxComponent boxComponent) {
        boolean b = true;
        for (int i = 0; i < super.nfills; ++i) {
            b = (super.fill[i] instanceof EmptyBox || (super.fill[i] instanceof MultilineBox && super.fill[i].isEmpty(boxComponent)));
            if (!b) {
                break;
            }
        }
        return b;
    }
    
    public final int nPosicions() {
        if (this.fillBuit(1)) {
            return 1;
        }
        if (this.fillBuit(2)) {
            return 2;
        }
        return super.nPosicions();
    }
    
    public final int kindChildBox(final int n, final int n2) {
        if (n <= 1) {
            return 3;
        }
        return 0;
    }
}
