import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Vector;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaComandes extends AbstractBox
{
    public boolean calculant;
    private PlotterPanel[] plotters;
    static Class PosicioMesPropera;
    
    public CapsaComandes() {
        this.calculant = false;
        super.color = 4;
    }
    
    public final void inicialitzaFills() {
        final TokensVBox tokensVBox = new TokensVBox();
        tokensVBox.setDefaultChildClass((CapsaComandes.PosicioMesPropera == null) ? (CapsaComandes.PosicioMesPropera = I("CapsaComanda")) : CapsaComandes.PosicioMesPropera);
        tokensVBox.inicialitzaFills();
        this.AfegirNoCalc(tokensVBox);
    }
    
    public final void enAfegir(final BoxComponent boxComponent, final AbstractBox abstractBox, final int n) {
        if (abstractBox instanceof TokensVBox) {
            final TokensVBox tokensVBox = (TokensVBox)abstractBox;
            tokensVBox.setDefaultChildClass((CapsaComandes.PosicioMesPropera == null) ? (CapsaComandes.PosicioMesPropera = I("CapsaComanda")) : CapsaComandes.PosicioMesPropera);
            tokensVBox.setType(0);
            tokensVBox.interSpace = new Length("0.2em");
            tokensVBox.paddingLeft = new Length("0.2em");
            tokensVBox.paddingRight = new Length("0.1em");
            tokensVBox.paddingTop = new Length("0.2em");
            tokensVBox.paddingBottom = new Length("0.2em");
        }
    }
    
    public final int nombreMinimDeFills() {
        return 1;
    }
    
    public final void enCalculRect(final BoxComponent boxComponent) {
        super.height = super.fill[0].height;
        super.fill[0].x = 0;
        super.fill[0].y = 0;
        super.width = super.fill[0].width;
        super.baseline = super.fill[0].baseline;
    }
    
    public final Length getRecommendedWidth(final int n, final BoxComponent boxComponent) {
        return super.recommendedWidth;
    }
    
    protected final void onPaint(final BoxComponent boxComponent, final Graphics graphics) {
        if (this.calculant) {
            final Color color = graphics.getColor();
            graphics.setColor(boxComponent.getColor(9, this));
            graphics.fillRect(0, 0, super.width, super.height);
            graphics.setColor(color);
        }
        if ((super.estil & 0x2) == 0x0) {
            final int[] array = new int[9];
            final int[] array2 = new int[9];
            int n;
            if (GraphicsUtils.isFileGraphics(graphics)) {
                n = (int)Math.ceil(super.EM * 1.0f / 19.0f);
            }
            else {
                n = 1;
            }
            array[0] = this.em(0.2f) + 1;
            array2[0] = 0;
            array[1] = array[0];
            array2[1] += n;
            array[2] = n;
            array2[2] = array2[1];
            array[3] = array[2];
            array2[3] = array2[0] + super.height - n;
            array[4] = array[0];
            array2[4] = array2[3];
            array[5] = array[4];
            array2[5] = super.height;
            array[6] = 0;
            array2[6] = array2[5];
            array2[7] = (array[7] = 0);
            array[8] = array[0];
            array2[8] = array2[0];
            graphics.fillPolygon(array, array2, 9);
        }
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        return super.fill[0].PosicioMesPropera(point, array);
    }
    
    public final void setPlotters(final PlotterPanel[] plotters) {
        this.plotters = plotters;
    }
    
    public final PlotterPanel[] getPlotters() {
        return this.plotters;
    }
    
    public final void ObteOmegaStrings2(final Vector vector, final Vector vector2, final boolean b) {
        CapsaLlibreria.I(new BoxPosition(this, 0), vector, vector2);
        final AbstractBox abstractBox = super.fill[0];
        for (int i = 0; i < abstractBox.nfills; ++i) {
            String cadenaOmega = FormulaEditorCalc.cadenaOmega(abstractBox.fill[i]);
            if (!b) {
                String s = this.trimRight(cadenaOmega);
                if (s.length() > 0 && !s.endsWith(";")) {
                    s += ";";
                }
                cadenaOmega = s;
            }
            vector.addElement(cadenaOmega);
            vector2.addElement(abstractBox.fill[i]);
        }
    }
    
    public final boolean fbvisible(final int n) {
        return false;
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            return "group";
        }
        if (boxScripting.scriptMode == 0) {
            return "commands";
        }
        return null;
    }
    
    public final int getSplitFactor(final int n) {
        return 100;
    }
    
    public final int kindChildBox(final int n, final int n2) {
        return 2;
    }
    
    public final boolean isEmpty(final BoxComponent boxComponent) {
        return super.nfills <= 0 || super.fill[0].isEmpty(boxComponent) || (super.fill[0].nfills == 1 && super.fill[0].fill[0].isEmpty(boxComponent));
    }
    
    public final int getNumGroup() {
        try {
            final AbstractBox parentBox = this.getParentBox();
            for (int i = 0; i < parentBox.fill.length; ++i) {
                if (this.equals(parentBox.fill[i])) {
                    return i + 1;
                }
            }
        }
        catch (Exception ex) {}
        return 1;
    }
    
    public final String trimRight(final String s) {
        int length;
        for (length = s.length(); 0 < length && s.charAt(length - 1) <= ' '; --length) {}
        return s.substring(0, length);
    }
    
    public final void updatePlotters(final String s, final ByteArrayInputStream byteArrayInputStream) {
        for (int i = 0; i < this.plotters.length; ++i) {
            final PlotterCanvas plotComp = this.plotters[i].getPlotComp();
            if (plotComp.getName().equals(s)) {
                plotComp.setData(byteArrayInputStream, this, false);
                plotComp.repaintNow();
            }
        }
    }
    
    static final Class I(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
