import java.util.Hashtable;
import java.awt.Point;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaUnitat extends AbstractBox
{
    String PosAnt_Fill;
    public static Properties translate;
    
    public static final String getUnitName(final String s) {
        String s2 = CapsaUnitat.translate.getProperty(s);
        if (s2 == null) {
            if (s.startsWith("\\SI")) {
                s2 = s.substring(3);
            }
            else {
                s2 = s;
            }
        }
        return s2;
    }
    
    public CapsaUnitat() {
        super.color = 3;
    }
    
    public final void inicialitzaModalitat(final Object o) {
        this.PosAnt_Fill = (String)o;
        super.dibs = new NeuterBox[] { NeuterBox.crea(this.PosAnt_Fill) };
        super.ndibs = super.dibs.length;
    }
    
    public final void inicialitzaFills(final AbstractBox[] array) {
        AbstractBox treureNoCalc = array[0];
        if (treureNoCalc instanceof TokensBox) {
            treureNoCalc = treureNoCalc.TreureNoCalc(0);
        }
        this.AfegirNoCalc(treureNoCalc, super.x);
    }
    
    public final int nombreMinimDeFills() {
        return 1;
    }
    
    public final void enCalculRect() {
        super.baseline = super.fill[0].baseline;
        super.width = this.em(0.05f);
        if (super.ndibs >= 1) {
            super.dibs[0].x = super.width;
            super.baseline = Math.max(super.dibs[0].baseline, super.baseline);
            super.dibs[0].y = super.baseline - super.dibs[0].baseline;
            super.width += super.dibs[0].width + this.em(0.0f);
        }
        super.fill[0].x = super.width;
        super.fill[0].y = super.baseline - super.fill[0].baseline;
        super.width += super.fill[0].width + this.em(0.05f);
        super.height = super.fill[0].yh();
        if (super.ndibs >= 1) {
            super.height = Math.max(super.height, super.dibs[0].height);
        }
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        if (new Point(point.x - posicioReal.x, point.y - posicioReal.y).x < super.width / 2) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
    }
    
    public static final void XMLcontent(final String s, final String s2, final BoxScripting boxScripting) {
        final String s3 = MathML2BoxCalc.special2csymbol.get(s2);
        String string = NeuterBox.name2string.get(s2);
        String s4 = "http://.../units/" + s3;
        if (s != null) {
            final String s5 = MathML2BoxCalc.special2csymbol.get(s);
            string = NeuterBox.name2string.get(s) + string;
            s4 = s4 + "#" + s5;
        }
        boxScripting.appendAttribute("definitionURL", s4);
        boxScripting.appendScript(XMLParser.string2CharData(string));
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return this.PosAnt_Fill;
        }
        if (boxScripting.scriptMode == 1) {
            return "csymbol";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            XMLcontent(this.PosAnt_Fill, ((SpecialSymbolBox)super.fill[0]).nom, boxScripting);
        }
        else if (boxScripting.scriptMode == 0) {
            super.onScript(boxScripting);
        }
        else if (boxScripting.scriptMode == 2) {
            boxScripting.appendText(" ");
            String substring;
            if (this.PosAnt_Fill != null) {
                substring = this.PosAnt_Fill.substring(3);
                final String property = CapsaUnitat.translate.getProperty(this.PosAnt_Fill);
                if (property != null) {
                    substring = property;
                }
            }
            else {
                substring = "";
            }
            final WirisBoxScripting wirisBoxScripting = new WirisBoxScripting();
            super.fill[0].script(wirisBoxScripting);
            String s;
            for (s = wirisBoxScripting.toString(); s.charAt(0) == ' '; s = s.substring(1)) {}
            boxScripting.appendText(substring + s);
        }
    }
    
    public final int getSplitFactor(final int n) {
        return 800;
    }
    
    static {
        Utils.loadBinHashtable("\\SIyotta@yotta@\\SIzetta@zetta@\\SIexa@exa@\\SIpeta@peta@\\SItera@tera@\\SIgiga@giga@\\SImega@mega@\\SIkilo@kilo@\\SIhecto@hecto@\\SIdeka@deka@\\SIdeci@deci@\\SIcenti@centi@\\SImilli@milli@\\SImicro@micro@\\SInano@nano@\\SIpico@pico@\\SIfemto@femto@\\SIatto@atto@\\SIzepto@zepto@\\SIyocto@yocto@\\SImeter@meter@\\SIgram@gram@\\SIampere@ampere@\\SImole@mole@\\SIliter@liter@\\SIradian@radian@\\SIsteradian@steradian@\\SIsecond@second@\\SIminute@minute@\\SIhour@hour@\\SIkelvin@kelvin@\\SIcandela@candela@\\SIliter@liter@\\SIradian@radian@\\SIsteradian@steradian@\\SIhertz@hertz@\\SInewton@newton@\\SIpascal@pascal@\\SIjoule@joule@\\SIwatt@watt@\\SIcoulomb@coulomb@\\SIvolt@volt@\\SIfarad@farad@\\SIohm@ohm@\\SIsiemens@siemens@\\SIweber@weber@\\SItesla@tesla@\\SIhenry@henry@\\SIlumen@lumen@\\SIlux@lux@\\SIbecquerel@becquerel@\\SIgray@gray@\\SIsievert@sievert@\\SIkatal@katal@\\SIbar@bar@", CapsaUnitat.translate = new Properties(), null);
    }
}
