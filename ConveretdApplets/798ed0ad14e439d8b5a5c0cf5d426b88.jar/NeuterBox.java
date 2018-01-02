import java.awt.FontMetrics;
import java.util.Hashtable;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class NeuterBox extends AbstractNeuterBox
{
    Font B;
    int forName;
    int D;
    String F;
    boolean J;
    public static final Hashtable name2string;
    static Hashtable S;
    static Hashtable getAscent;
    static Class getDescent;
    static Class getFontMetrics;
    static Class getMessage;
    static Class getSize;
    
    public NeuterBox() {
        this.B = null;
        this.J = false;
    }
    
    public final void setFont(final BoxComponent boxComponent, final Font b) {
        if (this.B != b || super.Z != null) {
            if (super.Z != null) {
                this.B = new Font(super.Z, b.getStyle(), b.getSize());
            }
            else {
                this.B = b;
            }
            final FontMetrics fontMetrics = boxComponent.getFontMetrics(this.B);
            if (OmegaSystem.versio_java >= 2) {
                super.I = fontMetrics.getAscent() + fontMetrics.getDescent() / 2;
            }
            else {
                super.I = fontMetrics.getAscent() + fontMetrics.getDescent();
            }
            this.forName = this.B.getSize();
            this.onSetFont(boxComponent, this.B);
            this.D = Math.round(super.I * 12.0f / 19.0f);
        }
    }
    
    public void onSetFont(final BoxComponent boxComponent, final Font font) {
    }
    
    public final int getTimes(final int n) {
        final int style = this.B.getStyle();
        final Font b = this.B;
        int n2 = (int)(((style == 1) ? 1.3f : 1.0f) * n / 11.0f);
        if (n2 == 0) {
            n2 = 1;
        }
        return n2;
    }
    
    public static final NeuterBox crea(final String command) {
        final Class<NeuterBox> clazz = NeuterBox.getAscent.get(command);
        NeuterBox neuterBox;
        if (clazz != null) {
            neuterBox = null;
            try {
                neuterBox = clazz.newInstance();
            }
            catch (InstantiationException ex) {
                System.out.println(ex.toString());
            }
            catch (IllegalAccessException ex2) {
                System.out.println(ex2.toString());
            }
        }
        else {
            final Integer n = NeuterBox.S.get(command);
            if (n != null) {
                neuterBox = new CapsaNeutraPoly2(n);
            }
            else {
                final String s = NeuterBox.name2string.get(command);
                if (s != null) {
                    neuterBox = new CapsaNeutraString(s);
                }
                else {
                    neuterBox = new CapsaNeutraString(command);
                }
            }
        }
        neuterBox.setCommand(command);
        return neuterBox;
    }
    
    public static final NeuterBox creaRectangle() {
        return new CapsaNeutraPlena();
    }
    
    public void setCommand(final String f) {
        this.F = f;
    }
    
    private static final Class forName(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        name2string = new Hashtable();
        NeuterBox.S = new Hashtable();
        (NeuterBox.getAscent = new Hashtable()).put("\\rightarrow", (NeuterBox.getDescent == null) ? (NeuterBox.getDescent = forName("CapsaNeutraFletxa")) : NeuterBox.getDescent);
        NeuterBox.getAscent.put("\\rightarrow", (NeuterBox.getDescent == null) ? (NeuterBox.getDescent = forName("CapsaNeutraFletxa")) : NeuterBox.getDescent);
        NeuterBox.getAscent.put("\\longrightarrow", (NeuterBox.getDescent == null) ? (NeuterBox.getDescent = forName("CapsaNeutraFletxa")) : NeuterBox.getDescent);
        NeuterBox.getAscent.put("\\Rightarrow", (NeuterBox.getDescent == null) ? (NeuterBox.getDescent = forName("CapsaNeutraFletxa")) : NeuterBox.getDescent);
        NeuterBox.getAscent.put("\\Longrightarrow", (NeuterBox.getDescent == null) ? (NeuterBox.getDescent = forName("CapsaNeutraFletxa")) : NeuterBox.getDescent);
        NeuterBox.getAscent.put("\\leftarrow", (NeuterBox.getDescent == null) ? (NeuterBox.getDescent = forName("CapsaNeutraFletxa")) : NeuterBox.getDescent);
        NeuterBox.getAscent.put("\\longleftarrow", (NeuterBox.getDescent == null) ? (NeuterBox.getDescent = forName("CapsaNeutraFletxa")) : NeuterBox.getDescent);
        NeuterBox.getAscent.put("\\Leftarrow", (NeuterBox.getDescent == null) ? (NeuterBox.getDescent = forName("CapsaNeutraFletxa")) : NeuterBox.getDescent);
        NeuterBox.getAscent.put("\\Longleftarrow", (NeuterBox.getDescent == null) ? (NeuterBox.getDescent = forName("CapsaNeutraFletxa")) : NeuterBox.getDescent);
        NeuterBox.getAscent.put("\\mapsto", (NeuterBox.getDescent == null) ? (NeuterBox.getDescent = forName("CapsaNeutraFletxa")) : NeuterBox.getDescent);
        NeuterBox.getAscent.put("\\longmapsto", (NeuterBox.getDescent == null) ? (NeuterBox.getDescent = forName("CapsaNeutraFletxa")) : NeuterBox.getDescent);
        NeuterBox.getAscent.put("\\delayedruletuple", (NeuterBox.getDescent == null) ? (NeuterBox.getDescent = forName("CapsaNeutraFletxa")) : NeuterBox.getDescent);
        NeuterBox.getAscent.put("\\eq", (NeuterBox.getFontMetrics == null) ? (NeuterBox.getFontMetrics = forName("CapsaNeutraIgual")) : NeuterBox.getFontMetrics);
        NeuterBox.getAscent.put("\\neq", (NeuterBox.getFontMetrics == null) ? (NeuterBox.getFontMetrics = forName("CapsaNeutraIgual")) : NeuterBox.getFontMetrics);
        NeuterBox.getAscent.put("\\assign", (NeuterBox.getFontMetrics == null) ? (NeuterBox.getFontMetrics = forName("CapsaNeutraIgual")) : NeuterBox.getFontMetrics);
        NeuterBox.getAscent.put("=", (NeuterBox.getFontMetrics == null) ? (NeuterBox.getFontMetrics = forName("CapsaNeutraIgual")) : NeuterBox.getFontMetrics);
        CapsaNeutraPoly2.I("integral", 7);
        CapsaNeutraPoly2.I("sumatori", 11);
        CapsaNeutraPoly2.I("productori", 12);
        CapsaNeutraPoly2.I("bigcap", 14);
        CapsaNeutraPoly2.I("bigcup", 15);
        CapsaNeutraPoly2.I("\\cap", 13);
        CapsaNeutraPoly2.I("\\cup", 1);
        CapsaNeutraPoly2.I("\\wedge", 10);
        CapsaNeutraPoly2.I("\\vee", 9);
        CapsaNeutraPoly2.I("\\Opi", 8);
        NeuterBox.getAscent.put("+", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("-", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("*", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("/", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("<", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put(">", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("\\leq", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("\\geq", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("\\ang", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put(":", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("\\in", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("\\notin", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("\\pm", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("\\hat", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("\\utri", (NeuterBox.getMessage == null) ? (NeuterBox.getMessage = forName("CapsaNeutraOp")) : NeuterBox.getMessage);
        NeuterBox.getAscent.put("\\pinfty", (NeuterBox.getSize == null) ? (NeuterBox.getSize = forName("CapsaNeutraInfinit")) : NeuterBox.getSize);
        NeuterBox.getAscent.put("\\minfty", (NeuterBox.getSize == null) ? (NeuterBox.getSize = forName("CapsaNeutraInfinit")) : NeuterBox.getSize);
        NeuterBox.getAscent.put("\\pminfty", (NeuterBox.getSize == null) ? (NeuterBox.getSize = forName("CapsaNeutraInfinit")) : NeuterBox.getSize);
        NeuterBox.S.put("\\NN", new Integer(2));
        NeuterBox.S.put("\\ZZ", new Integer(3));
        NeuterBox.S.put("\\QQ", new Integer(4));
        NeuterBox.S.put("\\RR", new Integer(5));
        NeuterBox.S.put("\\CC", new Integer(6));
        Utils.loadBinHashtable("@\\{@{@\\}@}@\\&@&@\\^@^@\\_@_@\\$@$@\\quote@\"@\\backslash@\\@\\\\@\\@\\%@%@\\i@&&@\\caret@\\caret@\\Opi@\u03c0@\\Oe@e@\\Oi@i@\\Oj@j@\\infty@\u221e@\\pinfty@+\u221e@\\minfty@\u2212\u221e@\\pminfty@±\u221e@\\in@ en @\\leq@\u2264@\\geq@\u2265@\\ang@\u2220@\\pm@±@\\hat@^@\\utri@\u25b5@\\prima@'@\\times@\u00d7@\\NN@N@\\ZZ@Z@\\QQ@Q@\\RR@R@\\CC@C@\\SIyocto@y@\\SIzepto@z@\\SIatto@a@\\SIfemto@f@\\SIpico@p@\\SInano@n@\\SImicro@\u03bc@\\SImilli@m@\\SIcenti@c@\\SIdeci@d@\\SIdeka@da@\\SIhecto@h@\\SIkilo@k@\\SImega@M@\\SIgiga@G@\\SItera@T@\\SIpeta@P@\\SIexa@E@\\SIzetta@Z@\\SIyotta@Y@\\SImeter@m@\\SIgram@g@\\SIsecond@s@\\SIampere@A@\\SIkelvin@K@\\SImole@mol@\\SIcandela@cd@\\SIliter@l@\\SIradian@rad@\\SIsteradian@sr@\\SIhertz@Hz@\\SInewton@N@\\SIpascal@Pa@\\SIjoule@J@\\SIwatt@W@\\SIcoulomb@C@\\SIvolt@V@\\SIfarad@F@\\SIohm@\u03a9@\\SIsiemens@S@\\SIweber@Wb@\\SItesla@T@\\SIhenry@H@\\SIlumen@lm@\\SIlux@lx@\\SIbecquerel@Bq@\\SIgray@Gy@\\SIsievert@Sv@\\SIkatal@kat@\\SIminute@min@\\SIhour@h@\\SIbar@b@\\SIangledegree@°@\\SIangleminute@'@\\SIanglesecond@''@\\row@#i@\\column@#j", NeuterBox.name2string, null);
        NeuterBox.name2string.put("\\space", " ");
        NeuterBox.name2string.put("\\espai", " ");
    }
}
