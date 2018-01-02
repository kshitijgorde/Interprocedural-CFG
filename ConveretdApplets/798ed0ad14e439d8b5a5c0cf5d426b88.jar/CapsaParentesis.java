import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaParentesis extends AbstractBox
{
    char AmpladaLinia;
    char AmpladaParentesis;
    static final int[] PosAnt_Fill;
    static final int[] PosSeg_Fill;
    static final int[] PosicioMesProperaGlobal;
    static final int[] PosicioReal;
    static final int[] append;
    static final int[] appendAttribute;
    static float[] appendText;
    static float[] attributes;
    
    public CapsaParentesis() {
        super.color = 3;
    }
    
    public final void inicialitzaModalitat(final Object o) {
        this.AmpladaLinia = ((String)o).charAt(0);
        this.AmpladaParentesis = ((String)o).charAt(1);
    }
    
    public final int nombreMinimDeFills() {
        return 1;
    }
    
    public final void enCalculRect() {
        super.height = super.fill[0].height;
        super.baseline = super.fill[0].baseline;
        super.fill[0].x = this.em(0.1f + AmpladaParentesis(this, this.AmpladaLinia) + 0.05f);
        super.fill[0].y = 0;
        super.width = super.fill[0].xw() + this.em(0.1f + AmpladaParentesis(this, this.AmpladaParentesis) + 0.05f);
        final int n = super.fill[0].baseline - this.em(0.5f);
        final int n2 = super.fill[0].height - super.fill[0].baseline;
        if (n > n2) {
            super.fill[0].y = 0;
            super.baseline = n + this.em(0.5f);
            super.height = 2 * n + this.em(0.5f);
        }
        if (n2 > n) {
            super.fill[0].y = n2 - n;
            super.baseline = n2 + this.em(0.5f);
            super.height = 2 * n2 + this.em(0.5f);
        }
    }
    
    protected final void I(final Graphics graphics) {
        final int height = super.height;
        final int width = super.width;
        final int em = this.em(AmpladaParentesis(this, this.AmpladaLinia));
        final int em2 = this.em(AmpladaParentesis(this, this.AmpladaParentesis));
        int n = 0;
        int n2 = height;
        if (height > this.em()) {
            final int n3 = (int)Math.max(Math.min(height - this.em(), 4.0f * this.em() / 19.0f), 0.0f);
            n += n3;
            n2 -= 2 * n3;
        }
        dibuixaParentesis(this.AmpladaLinia, super.I, super.EM, super.baseline, false, new Rectangle(this.em(0.1f), n, em, n2), graphics);
        dibuixaParentesis(this.AmpladaParentesis, super.I, super.EM, super.baseline, true, new Rectangle(width - em2 - this.em(0.1f), n, em2, n2), graphics);
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        if (point2.x < this.em(0.1f) + this.em(AmpladaParentesis(this, this.AmpladaLinia)) / 2) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        if (point2.x > super.width - this.em(0.1f) - this.em(AmpladaParentesis(this, this.AmpladaParentesis)) / 2) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        return super.fill[0].PosicioMesProperaGlobal(point, array);
    }
    
    public static final float AmpladaParentesis(final AbstractBox abstractBox, final char c, final int n) {
        return AmpladaParentesis(abstractBox, c);
    }
    
    public static final float AmpladaParentesis(final AbstractBox abstractBox, final char c) {
        switch (c) {
            case 'X': {
                return 0.0f;
            }
            case 'B': {
                return abstractBox.fontMetrics.charWidth('{') / abstractBox.EM;
            }
            case 'p': {
                return abstractBox.fontMetrics.charWidth('(') / abstractBox.EM;
            }
            case 'F':
            case 'L':
            case 'b': {
                return abstractBox.fontMetrics.charWidth('[') / abstractBox.EM;
            }
            case 'v': {
                return abstractBox.fontMetrics.charWidth('|') / abstractBox.EM;
            }
            case 'V': {
                return 1.0f * Math.max(2, abstractBox.EM / 9) / abstractBox.EM + 0.2f;
            }
            case 'e': {
                return 0.4f;
            }
            default: {
                return 0.2f;
            }
        }
    }
    
    public static final float AmpladaLinia(final Graphics graphics, final int n, final char c, final boolean b) {
        if (c != 'V') {
            return n * 1.5f / (b ? 19.0f : 38.0f);
        }
        if (GraphicsUtils.isFileGraphics(graphics)) {
            return n * 1.0f / (b ? 19.0f : 38.0f);
        }
        return 1.0f;
    }
    
    public static final void dibuixaParentesis(final char c, final Font font, final int n, final int n2, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        String s = null;
        int[] array = null;
        int[] array2 = null;
        int[] array3 = null;
        int n3 = 0;
        int[] array4 = null;
        int[] array5 = null;
        int n4 = 0;
        final boolean b2 = (font.getStyle() & 0x1) != 0x0;
        final boolean b3 = b2 && !GraphicsUtils.isFileGraphics(graphics) && OmegaSystem.versio_java < 2;
        final boolean b4 = 4 * rectangle.height < 5 * n;
        GraphicsUtils.setLineWidth(graphics, AmpladaLinia(graphics, n, c, b2));
        if (c != 'V') {
            GraphicsUtils.setRendering(graphics, true);
        }
        switch (c) {
            case 'p': {
                if (b4) {
                    s = (b ? ")" : "(");
                    break;
                }
                graphics.translate(rectangle.x, rectangle.y);
                n3 = 0;
                if (!b) {
                    graphics.drawArc(0, 0, rectangle.width * 2 - 1, rectangle.height - 1, 90, 180);
                    if (b3) {
                        graphics.drawArc(1, 0, rectangle.width * 2 - 1, rectangle.height - 1, 90, 180);
                    }
                }
                else {
                    graphics.drawArc(0 - rectangle.width, 0, rectangle.width * 2 - 1, rectangle.height - 1, 90, -180);
                    if (b3) {
                        graphics.drawArc(0 - rectangle.width - 1, 0, rectangle.width * 2 - 1, rectangle.height - 1, 90, -180);
                    }
                }
                graphics.translate(-rectangle.x, -rectangle.y);
                break;
            }
            case 'b': {
                if (b4) {
                    s = (b ? "]" : "[");
                    break;
                }
                n3 = 4;
                array2 = new int[n3];
                array3 = new int[n3];
                array2[0] = rectangle.width - 1;
                array2[1] = (array3[0] = 0);
                array2[2] = (array3[1] = 0);
                array3[2] = rectangle.height - 1;
                array2[3] = rectangle.width - 1;
                array3[3] = rectangle.height - 1;
                array = CapsaParentesis.PosSeg_Fill;
                break;
            }
            case 'B': {
                if (b4) {
                    s = (b ? "}" : "{");
                    break;
                }
                final int n5 = rectangle.height / 2 - rectangle.width / 2;
                final int n6 = rectangle.height / 2 + rectangle.width / 2;
                final int n7 = 40 * rectangle.width / 100;
                n3 = 7;
                array2 = new int[n3];
                array3 = new int[n3];
                array2[0] = rectangle.width - 1;
                array3[0] = 0;
                array2[1] = n7;
                array3[1] = 0;
                array2[2] = n7;
                array3[2] = n5 - 1;
                array2[3] = 0;
                array3[3] = rectangle.height / 2 - 1;
                array2[4] = n7;
                array3[4] = n6 - 1;
                array2[5] = n7;
                array3[5] = rectangle.height - 1;
                array2[6] = rectangle.width - 1;
                array3[6] = rectangle.height - 1;
                array = CapsaParentesis.PosAnt_Fill;
                break;
            }
            case 'v': {
                if (b4) {
                    s = "|";
                    break;
                }
                final int n8 = 40 * rectangle.width / 100;
                n3 = 2;
                array2 = new int[n3];
                array3 = new int[n3];
                array2[0] = n8;
                array3[0] = 0;
                array2[1] = n8;
                array3[1] = rectangle.height - 1;
                array = CapsaParentesis.PosicioMesProperaGlobal;
                break;
            }
            case 'V': {
                n3 = 2;
                array2 = new int[n3];
                array3 = new int[n3];
                array2[0] = 0;
                array2[1] = (array3[0] = 0);
                array3[1] = rectangle.height - 1;
                array = null;
                n4 = 2;
                array4 = new int[n4];
                array5 = new int[n4];
                array4[0] = Math.max(2, n / 9);
                array5[0] = 0;
                array4[1] = array4[0];
                array5[1] = rectangle.height - 1;
                break;
            }
            case 'e': {
                n3 = 3;
                array2 = new int[n3];
                array3 = new int[n3];
                array2[0] = rectangle.width - 2;
                array2[1] = (array3[0] = 0);
                array3[1] = rectangle.height / 2;
                array2[2] = rectangle.width - 2;
                array3[2] = rectangle.height - 1;
                array = CapsaParentesis.PosicioReal;
                break;
            }
            case 'F': {
                n3 = 3;
                array2 = new int[n3];
                array3 = new int[n3];
                array2[0] = rectangle.width - 1;
                array2[1] = (array3[0] = 0);
                array2[2] = (array3[1] = 0);
                array3[2] = rectangle.height - 1;
                array = CapsaParentesis.append;
                break;
            }
            case 'L': {
                n3 = 3;
                array2 = new int[n3];
                array3 = new int[n3];
                array2[0] = 0;
                array2[1] = (array3[0] = 0);
                array3[1] = rectangle.height - 1;
                array2[2] = rectangle.width - 1;
                array3[2] = rectangle.height - 1;
                array = CapsaParentesis.appendAttribute;
                break;
            }
            case 'X': {
                break;
            }
            case '.': {
                break;
            }
            default: {
                System.err.println("Par\ufffdntesi no reconegut: " + c);
                return;
            }
        }
        if (s != null) {
            graphics.translate(rectangle.x, 0);
            graphics.drawString(s, 0, n2);
            graphics.translate(-rectangle.x, 0);
        }
        else if (n3 > 0) {
            graphics.translate(rectangle.x, rectangle.y);
            if (!b) {
                graphics.drawPolyline(array2, array3, n3);
                if (b3 && array != null) {
                    for (int i = 0; i < n3; ++i) {
                        array2[i] += array[2 * i];
                        array3[i] += array[2 * i + 1];
                    }
                    graphics.drawPolyline(array2, array3, n3);
                }
                if (n4 > 0) {
                    graphics.drawPolyline(array4, array5, n4);
                }
            }
            else {
                for (int j = 0; j < n3; ++j) {
                    array2[j] = rectangle.width - array2[j] - 1;
                }
                graphics.drawPolyline(array2, array3, n3);
                if (b3 && array != null) {
                    for (int k = 0; k < n3; ++k) {
                        array2[k] -= array[2 * k];
                        array3[k] += array[2 * k + 1];
                    }
                    graphics.drawPolyline(array2, array3, n3);
                }
                if (n4 > 0) {
                    for (int l = 0; l < n4; ++l) {
                        array4[l] = rectangle.width - array4[l] - 1;
                    }
                    graphics.drawPolyline(array4, array5, n4);
                }
            }
            graphics.translate(-rectangle.x, -rectangle.y);
        }
        GraphicsUtils.setLineWidth(graphics, 1.0f);
        if (c != 'V') {
            GraphicsUtils.setRendering(graphics, false);
        }
    }
    
    static final void I(final BoxScripting boxScripting, final char c, final char c2) {
        char c3 = '\0';
        if (c == 'b') {
            c3 = '[';
        }
        else if (c == 'B') {
            c3 = '{';
        }
        else if (c == 'v') {
            c3 = '|';
        }
        else if (c == 'V') {
            c3 = '\u2016';
        }
        else if (c == 'F') {
            c3 = '\u2308';
        }
        else if (c == 'L') {
            c3 = '\u230a';
        }
        else if (c == '.') {
            c3 = ' ';
        }
        if (c3 != '\0') {
            boxScripting.appendAttribute("open", "" + c3);
        }
        char c4 = '\0';
        if (c2 == 'b') {
            c4 = ']';
        }
        else if (c2 == 'B') {
            c4 = '}';
        }
        else if (c2 == 'v') {
            c4 = '|';
        }
        else if (c2 == 'V') {
            c4 = '\u2016';
        }
        else if (c2 == 'F') {
            c4 = '\u2309';
        }
        else if (c2 == 'L') {
            c4 = '\u230b';
        }
        else if (c2 == '.') {
            c4 = ' ';
        }
        if (c4 != '\0') {
            boxScripting.appendAttribute("close", "" + c4);
        }
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            switch (this.AmpladaLinia) {
                case 'E': {
                    return "round";
                }
                case 'F': {
                    return "ceil";
                }
                case 'L': {
                    return "floor";
                }
                default: {
                    if (this.AmpladaLinia == this.AmpladaParentesis) {
                        return this.AmpladaLinia + "parentesis";
                    }
                    return "parentesis";
                }
            }
        }
        else {
            if (boxScripting.scriptMode == 1) {
                return "mfenced";
            }
            return null;
        }
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            XMLBoxUtils.mrow(super.fill[0], boxScripting);
        }
        else if (boxScripting.scriptMode == 0) {
            switch (this.AmpladaLinia) {
                case 'E':
                case 'F':
                case 'L': {
                    break;
                }
                default: {
                    if (this.AmpladaLinia != this.AmpladaParentesis) {
                        boxScripting.appendText("{" + this.AmpladaLinia + "}");
                        super.script(boxScripting);
                        boxScripting.appendText("{" + this.AmpladaParentesis + "}");
                        return;
                    }
                    break;
                }
            }
            super.onScript(boxScripting);
        }
        else if (boxScripting.scriptMode == 2) {
            switch (this.AmpladaLinia) {
                case 'p': {
                    boxScripting.appendText("(");
                    break;
                }
                case 'b': {
                    boxScripting.appendText("[");
                    break;
                }
                case 'B': {
                    boxScripting.appendText("{");
                    break;
                }
                case 'v': {
                    boxScripting.appendText("absolute(");
                    break;
                }
                case 'V': {
                    boxScripting.appendText("norm(");
                    break;
                }
                case 'E': {
                    boxScripting.appendText("round(");
                    break;
                }
                case 'F': {
                    boxScripting.appendText("ceil(");
                    break;
                }
                case 'L': {
                    boxScripting.appendText("floor(");
                    break;
                }
            }
            super.fill[0].script(boxScripting);
            char c = this.AmpladaParentesis;
            if (c == 'e') {
                c = this.AmpladaLinia;
            }
            switch (c) {
                case 112: {
                    boxScripting.appendText(")");
                    break;
                }
                case 98: {
                    boxScripting.appendText("]");
                    break;
                }
                case 66: {
                    boxScripting.appendText("}");
                    break;
                }
                case 118: {
                    boxScripting.appendText(")");
                    break;
                }
                case 86: {
                    boxScripting.appendText(")");
                    break;
                }
                case 69: {
                    boxScripting.appendText(")");
                    break;
                }
                case 70: {
                    boxScripting.appendText(")");
                    break;
                }
                case 76: {
                    boxScripting.appendText(")");
                    break;
                }
            }
        }
    }
    
    public final void attributes(final BoxScripting boxScripting) {
        I(boxScripting, this.AmpladaLinia, this.AmpladaParentesis);
        super.attributes(boxScripting);
    }
    
    static {
        PosAnt_Fill = new int[] { 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, -1, 0, -1 };
        PosSeg_Fill = new int[] { 0, 1, 1, 1, 1, -1, 0, -1 };
        PosicioMesProperaGlobal = new int[] { 1, 0, 1, 0 };
        PosicioReal = new int[] { 1, 0, 1, 0, 1, 0 };
        append = new int[] { 0, 1, 1, 1, 1, 0 };
        appendAttribute = new int[] { 1, 0, 1, -1, 0, -1 };
        CapsaParentesis.appendText = new float[] { 1.0f, 0.0f, 0.0f, 1.0f, 0.3f, 0.3f };
        CapsaParentesis.attributes = new float[] { 0.0f, 0.2f, 0.8f, 1.0f, 0.8f, 0.2f };
    }
}
