import java.util.Vector;
import java.awt.Frame;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class MathML2BoxCalc extends MathML2Box
{
    static Hashtable AfegirNoCalc;
    static Hashtable TRUE;
    static Hashtable XMLcontent;
    static Hashtable add;
    static Hashtable amaga;
    static Hashtable appendScript;
    static Hashtable busca_diferencial;
    static Hashtable children;
    static Hashtable context;
    static Hashtable convertOldFormat;
    public static Hashtable special2csymbol;
    static Class createBox;
    static Class element_name;
    static Class equals;
    static Class equalsIgnoreCase;
    static Class esCondicio;
    static Class extra;
    static Class forName;
    static Class get;
    static Class getMainFrame;
    static Class getMessage;
    static Class getText;
    static Class indexOf;
    static Class infere_rec;
    static Class inicialitzaFills;
    static Class inicialitzaModalitat;
    static Class loadBinHashtable;
    static Class localName;
    static Class max;
    static Class out;
    static Class parseInt;
    static Class plainString;
    static Class printStackTrace;
    static Class println;
    static Class put;
    static Class remove;
    static Class show;
    static Class string;
    static Class string2CharData;
    static Class substitute;
    static Class substring;
    
    public static final String specialTag(final String s) {
        final String s2 = MathML2BoxCalc.amaga.get(s);
        if (s2 != null) {
            return s2;
        }
        if (MathML2BoxCalc.TRUE.get(s) != null) {
            return "mo";
        }
        if (MathML2BoxCalc.add.get(s) != null) {
            return "cn";
        }
        if (MathML2BoxCalc.special2csymbol.get(s) != null) {
            return "csymbol";
        }
        return null;
    }
    
    public static final void special(final BoxScripting boxScripting, final String s) {
        if (MathML2BoxCalc.amaga.get(s) != null) {
            return;
        }
        String s2 = MathML2BoxCalc.TRUE.get(s);
        if (s2 == null) {
            s2 = MathML2BoxCalc.add.get(s);
        }
        if (s2 == null) {
            s2 = MathML2BoxCalc.special2csymbol.get(s);
            if (s2 != null) {
                CapsaUnitat.XMLcontent(null, s, boxScripting);
                return;
            }
        }
        if (s2 != null) {
            boxScripting.appendScript(XMLParser.string2CharData(s2));
        }
    }
    
    protected final String I() {
        return "session@0@2@group@1@2@command@2@0@input@3@4@output@4@0@quorem@5@3@quoremdone@6@3@sub@7@3@sup@8@3@matrix@9@3@iintegralf@10@0@iintegral@11@3@integralf@12@3@integral@13@3@xtori@14@3@limit@15@3@idmatrix@16@3@multiline@17@3@vlist@18@3@vcenter@19@3@comment@20@0@editbox@21@1@imagebutton@22@0@button@23@0@library@24@0@plotter@25@0@wtable@26@3@wif@27@0@wbegin@28@0@image@29@0@choice@30@0@vtop@31@3@frame@32@0@cell@33@4@formanswer@34@3@naturalnumbers@200@0@integers@201@0@rationals@202@0@reals@203@0@complexes@204@0@exponentiale@205@0@imaginaryi@206@0@pi@207@0@infinity@208@0@if@300@0@begin@301@4@for@302@0@while@303@0@repeat@304@0@C@305@3@P@306@3@V@307@3@CR@308@3@PR@309@3@VR@310@3@binomial_coefficient@311@3@local@312@0@textbox@402@1@div@400@0@p@401@1@playerbox@500@0@slider@501@0";
    }
    
    protected final AbstractBox I(final XMLElement xmlElement) {
        final int i = this.I(xmlElement.localName());
        switch (i) {
            case 22:
            case 23: {
                return new EmptyBox();
            }
            case 25: {
                return new EmptyBox();
            }
            case 104:
            case 106: {
                if (xmlElement.children.length != 1 || !xmlElement.children[0].localName().equals("text")) {
                    break;
                }
                final String text = xmlElement.children[0].getText();
                String s;
                if (i == 104) {
                    AbstractBox abstractBox;
                    if ((abstractBox = SpaceBox.createBox(text)) != null || (abstractBox = MarkupBox.createBox(text)) != null) {
                        return abstractBox;
                    }
                    s = MathML2BoxCalc.AfegirNoCalc.get(text);
                }
                else {
                    s = MathML2BoxCalc.XMLcontent.get(text);
                }
                if (s != null) {
                    return new SpecialSymbolBox(s, text);
                }
                break;
            }
            case 127: {
                if (xmlElement.children.length != 1 || !xmlElement.children[0].localName().equals("text") || xmlElement.attributes == null) {
                    return new SpecialSymbolBox(MathML2BoxCalc.appendScript.get(xmlElement.localName()));
                }
                final String s2 = xmlElement.attributes.get("definitionURL");
                if (s2 == null) {
                    return new SpecialSymbolBox(MathML2BoxCalc.appendScript.get(xmlElement.localName()));
                }
                final String trim = s2.trim();
                final int index = trim.indexOf("http://.../units/");
                if (index >= 0) {
                    final CapsaUnitat capsaUnitat = new CapsaUnitat();
                    final int index2 = trim.indexOf("#", index + 17);
                    String s3;
                    if (index2 > 0) {
                        s3 = trim.substring(index + 17, index2);
                        capsaUnitat.inicialitzaModalitat(MathML2BoxCalc.busca_diferencial.get(trim.substring(index2 + 1)));
                    }
                    else {
                        s3 = trim.substring(index + 17);
                    }
                    capsaUnitat.inicialitzaFills(new SpecialSymbolBox((String)MathML2BoxCalc.busca_diferencial.get(s3)));
                    return capsaUnitat;
                }
                return new SpecialSymbolBox(MathML2BoxCalc.appendScript.get(xmlElement.localName()));
            }
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208: {
                return new SpecialSymbolBox(MathML2BoxCalc.appendScript.get(xmlElement.localName()));
            }
            case 108: {
                XMLElement xmlElement2;
                if (xmlElement.children[0].localName().equals("csymbol")) {
                    xmlElement2 = new XMLElement(xmlElement.children[0].getText(), xmlElement.children.length - 1);
                }
                else {
                    xmlElement2 = new XMLElement(xmlElement.children[0].localName(), xmlElement.children.length - 1);
                }
                System.arraycopy(xmlElement.children, 1, xmlElement2.children, 0, xmlElement2.children.length);
                return this.I((XMLNode)xmlElement2);
            }
            case 8: {
                if (xmlElement.children.length == 1 && this.I(xmlElement.children[0], "T")) {
                    return new CapsaTransposadora();
                }
                break;
            }
            case 126: {
                if (xmlElement.children.length != 1 || xmlElement.attributes == null) {
                    break;
                }
                final Object value = xmlElement.attributes.get("actiontype");
                int n = 0;
                if (value.equals("argument")) {
                    return new ArgumentBox(xmlElement.children[0].getText());
                }
                if (value.equals("no-visible")) {
                    n = 4;
                }
                if (n != 0) {
                    this.Z(n);
                    final AbstractBox j = this.I(xmlElement.children[0]);
                    this.I(true);
                    this.Z();
                    return j;
                }
                break;
            }
            case 134: {
                return this.string((xmlElement.children.length > 0) ? xmlElement.children[0].getText() : "");
            }
        }
        return super.I(xmlElement);
    }
    
    protected final AbstractBox I(final int n, final Hashtable hashtable, final Object[] array, final AbstractBox[] array2) {
        switch (n) {
            case 0:
            case 500: {
                if (hashtable != null) {
                    final String s = hashtable.get("lang");
                    if (s != null && !s.equals("en")) {
                        final String substitute = Utils.substitute(Utils.substitute("The content has been created in a different language (%1) and it\nmight not work with this version (%2).", "%1", s), "%2", "en");
                        final Frame mainFrame = OmegaSystem.context.getMainFrame();
                        if (mainFrame != null) {
                            final MessageBox messageBox = new MessageBox(mainFrame, "Warning", 1);
                            messageBox.add(substitute);
                            messageBox.show();
                        }
                        else {
                            System.out.println(substitute);
                        }
                    }
                    break;
                }
                break;
            }
            case 9:
            case 26: {
                int int1 = 0;
                int int2 = 0;
                String s2 = null;
                if (hashtable != null) {
                    int1 = Integer.parseInt(hashtable.get("rows"));
                    int2 = Integer.parseInt(hashtable.get("cells"));
                    final String s3 = hashtable.get("open");
                    String s5;
                    if (s3 != null) {
                        final String s4 = MathML2BoxCalc.children.get(s3);
                        s5 = ((s4 == null) ? "." : s4);
                    }
                    else {
                        s5 = "p";
                    }
                    final String s6 = hashtable.get("close");
                    if (s6 != null) {
                        final String s7 = MathML2BoxCalc.children.get(s6);
                        s2 = s5 + ((s7 == null) ? "." : s7);
                    }
                    else {
                        s2 = s5 + "p";
                    }
                }
                if (n == 26) {
                    s2 = "X";
                }
                if (s2 == null) {
                    s2 = "p";
                }
                final MatrixBox matrixBox = new MatrixBox(int2, int1);
                matrixBox.inicialitzaModalitat(s2);
                matrixBox.inicialitzaFills(array2);
                return matrixBox;
            }
            case 15: {
                final String plainString = array2[0].plainString();
                final CapsaLimit capsaLimit = new CapsaLimit();
                final AbstractBox[] array3 = { array2[1], array2[2] };
                capsaLimit.inicialitzaModalitat(MathML2BoxCalc.convertOldFormat.get(plainString));
                capsaLimit.inicialitzaFills(array3);
                return capsaLimit;
            }
            case 24: {
                final CapsaLlibreria capsaLlibreria = new CapsaLlibreria();
                capsaLlibreria.inicialitzaFills(array2[0] = MathML2Box.I(array2[0]), array2[1]);
                if (hashtable != null) {
                    final String s8 = hashtable.get("closed");
                    if (s8 != null && s8.equalsIgnoreCase("true")) {
                        capsaLlibreria.amaga(null);
                    }
                }
                return capsaLlibreria;
            }
            case 27: {
                final CapsaSi capsaSi = new CapsaSi();
                capsaSi.extra = 1;
                for (int i = 0; i < array2.length; ++i) {
                    if (CapsaSi.esCondicio(i, array2.length, 1) || i + 1 == array2.length) {
                        array2[i] = MathML2Box.I(array2[i]);
                    }
                    else {
                        array2[i] = MathML2Box.Z(array2[i]);
                    }
                }
                capsaSi.inicialitzaFills(array2);
                return capsaSi;
            }
            case 34: {
                final TokensBox tokensBox = new TokensBox();
                for (int j = 0; j < array2.length; ++j) {
                    tokensBox.AfegirNoCalc(array2[j]);
                }
                return tokensBox;
            }
            case 107: {
                String s14;
                if (hashtable != null) {
                    final String s9 = hashtable.get("open");
                    String s11;
                    if (s9 != null) {
                        final String s10 = MathML2BoxCalc.children.get(s9);
                        s11 = ((s10 == null) ? "." : s10);
                    }
                    else {
                        s11 = "p";
                    }
                    final String s12 = hashtable.get("close");
                    if (s12 != null) {
                        final String s13 = MathML2BoxCalc.children.get(s12);
                        s14 = s11 + ((s13 == null) ? "." : s13);
                    }
                    else {
                        s14 = s11 + "p";
                    }
                }
                else {
                    s14 = "pp";
                }
                final CapsaParentesis capsaParentesis = new CapsaParentesis();
                capsaParentesis.inicialitzaModalitat(s14);
                capsaParentesis.inicialitzaFills(array2);
                return capsaParentesis;
            }
            case 14:
            case 122: {
                final String plainString2 = array2[0].plainString();
                AbstractBox abstractBox;
                AbstractBox[] array4;
                if (n == 14) {
                    abstractBox = new CapsaTori2();
                    array4 = new AbstractBox[] { array2[1], array2[2], array2[3] };
                }
                else {
                    abstractBox = new CapsaTori();
                    array4 = new AbstractBox[] { array2[1] };
                }
                abstractBox.inicialitzaModalitat(MathML2BoxCalc.context.get(plainString2));
                abstractBox.inicialitzaFills(array4);
                return abstractBox;
            }
            case 121:
            case 123: {
                final AbstractBox abstractBox2 = array2[1];
                array2[1] = array2[0];
                array2[0] = abstractBox2;
                break;
            }
            case 128:
            case 302:
            case 303: {
                array2[0] = MathML2Box.I(array2[0]);
                array2[1] = MathML2Box.Z(array2[1]);
                break;
            }
            case 300: {
                for (int k = 0; k < array2.length; ++k) {
                    if (CapsaSi.esCondicio(k, array2.length, 0)) {
                        array2[k] = MathML2Box.I(array2[k]);
                    }
                    else {
                        array2[k] = MathML2Box.Z(array2[k]);
                    }
                }
                break;
            }
            case 28:
            case 304: {
                array2[0] = MathML2Box.Z(array2[0]);
                array2[1] = MathML2Box.I(array2[1]);
                break;
            }
        }
        return null;
    }
    
    protected final Class I(final int n) {
        super.I = null;
        switch (n) {
            case 0: {
                return (MathML2BoxCalc.createBox == null) ? (MathML2BoxCalc.createBox = AfegirNoCalc("InterfaceBox")) : MathML2BoxCalc.createBox;
            }
            case 1: {
                return (MathML2BoxCalc.element_name == null) ? (MathML2BoxCalc.element_name = AfegirNoCalc("CapsaComandes")) : MathML2BoxCalc.element_name;
            }
            case 2: {
                return (MathML2BoxCalc.equals == null) ? (MathML2BoxCalc.equals = AfegirNoCalc("CapsaComanda")) : MathML2BoxCalc.equals;
            }
            case 5:
            case 6: {
                return (MathML2BoxCalc.equalsIgnoreCase == null) ? (MathML2BoxCalc.equalsIgnoreCase = AfegirNoCalc("CapsaDiv")) : MathML2BoxCalc.equalsIgnoreCase;
            }
            case 7: {
                return (MathML2BoxCalc.esCondicio == null) ? (MathML2BoxCalc.esCondicio = AfegirNoCalc("CapsaSubindex")) : MathML2BoxCalc.esCondicio;
            }
            case 8: {
                return (MathML2BoxCalc.extra == null) ? (MathML2BoxCalc.extra = AfegirNoCalc("CapsaSuperindex")) : MathML2BoxCalc.extra;
            }
            case 9: {
                super.I = "p";
                return (MathML2BoxCalc.forName == null) ? (MathML2BoxCalc.forName = AfegirNoCalc("MatrixBox")) : MathML2BoxCalc.forName;
            }
            case 10: {
                super.I = "iintegralf";
                return (MathML2BoxCalc.get == null) ? (MathML2BoxCalc.get = AfegirNoCalc("CapsaIntegrate")) : MathML2BoxCalc.get;
            }
            case 11: {
                super.I = "iintegral";
                return (MathML2BoxCalc.get == null) ? (MathML2BoxCalc.get = AfegirNoCalc("CapsaIntegrate")) : MathML2BoxCalc.get;
            }
            case 12: {
                super.I = "integralf";
                return (MathML2BoxCalc.get == null) ? (MathML2BoxCalc.get = AfegirNoCalc("CapsaIntegrate")) : MathML2BoxCalc.get;
            }
            case 13: {
                super.I = "integral";
                return (MathML2BoxCalc.get == null) ? (MathML2BoxCalc.get = AfegirNoCalc("CapsaIntegrate")) : MathML2BoxCalc.get;
            }
            case 14: {
                return (MathML2BoxCalc.getMainFrame == null) ? (MathML2BoxCalc.getMainFrame = AfegirNoCalc("CapsaTori2")) : MathML2BoxCalc.getMainFrame;
            }
            case 15: {
                return (MathML2BoxCalc.getMessage == null) ? (MathML2BoxCalc.getMessage = AfegirNoCalc("CapsaLimit")) : MathML2BoxCalc.getMessage;
            }
            case 16: {
                return (MathML2BoxCalc.getText == null) ? (MathML2BoxCalc.getText = AfegirNoCalc("CapsaMatriuIdentitat")) : MathML2BoxCalc.getText;
            }
            case 17: {
                return (MathML2BoxCalc.indexOf == null) ? (MathML2BoxCalc.indexOf = AfegirNoCalc("MultilineBox")) : MathML2BoxCalc.indexOf;
            }
            case 18: {
                super.I = "box";
                return (MathML2BoxCalc.infere_rec == null) ? (MathML2BoxCalc.infere_rec = AfegirNoCalc("TokensVBox")) : MathML2BoxCalc.infere_rec;
            }
            case 19: {
                super.I = "center";
                return (MathML2BoxCalc.infere_rec == null) ? (MathML2BoxCalc.infere_rec = AfegirNoCalc("TokensVBox")) : MathML2BoxCalc.infere_rec;
            }
            case 20: {
                super.I = Boolean.TRUE;
                return (MathML2BoxCalc.equals == null) ? (MathML2BoxCalc.equals = AfegirNoCalc("CapsaComanda")) : MathML2BoxCalc.equals;
            }
            case 28: {
                return (MathML2BoxCalc.inicialitzaFills == null) ? (MathML2BoxCalc.inicialitzaFills = AfegirNoCalc("CapsaInici")) : MathML2BoxCalc.inicialitzaFills;
            }
            case 31: {
                super.I = "top";
                return (MathML2BoxCalc.infere_rec == null) ? (MathML2BoxCalc.infere_rec = AfegirNoCalc("TokensVBox")) : MathML2BoxCalc.infere_rec;
            }
            case 33: {
                return (MathML2BoxCalc.inicialitzaModalitat == null) ? (MathML2BoxCalc.inicialitzaModalitat = AfegirNoCalc("CapsaCella")) : MathML2BoxCalc.inicialitzaModalitat;
            }
            case 101: {
                return (MathML2BoxCalc.loadBinHashtable == null) ? (MathML2BoxCalc.loadBinHashtable = AfegirNoCalc("FractionBox")) : MathML2BoxCalc.loadBinHashtable;
            }
            case 102: {
                super.I = "number";
                return (MathML2BoxCalc.localName == null) ? (MathML2BoxCalc.localName = AfegirNoCalc("TextBox")) : MathML2BoxCalc.localName;
            }
            case 103: {
                super.I = "ident";
                return (MathML2BoxCalc.localName == null) ? (MathML2BoxCalc.localName = AfegirNoCalc("TextBox")) : MathML2BoxCalc.localName;
            }
            case 104: {
                super.I = "symbol";
                return (MathML2BoxCalc.localName == null) ? (MathML2BoxCalc.localName = AfegirNoCalc("TextBox")) : MathML2BoxCalc.localName;
            }
            case 109: {
                super.I = "vv";
                return (MathML2BoxCalc.max == null) ? (MathML2BoxCalc.max = AfegirNoCalc("CapsaParentesis")) : MathML2BoxCalc.max;
            }
            case 110: {
                super.I = "VV";
                return (MathML2BoxCalc.max == null) ? (MathML2BoxCalc.max = AfegirNoCalc("CapsaParentesis")) : MathML2BoxCalc.max;
            }
            case 111: {
                super.I = "FF";
                return (MathML2BoxCalc.max == null) ? (MathML2BoxCalc.max = AfegirNoCalc("CapsaParentesis")) : MathML2BoxCalc.max;
            }
            case 112: {
                super.I = "LL";
                return (MathML2BoxCalc.max == null) ? (MathML2BoxCalc.max = AfegirNoCalc("CapsaParentesis")) : MathML2BoxCalc.max;
            }
            case 120: {
                super.I = "sqrt";
                return (MathML2BoxCalc.out == null) ? (MathML2BoxCalc.out = AfegirNoCalc("RootBox")) : MathML2BoxCalc.out;
            }
            case 121: {
                super.I = "root";
                return (MathML2BoxCalc.out == null) ? (MathML2BoxCalc.out = AfegirNoCalc("RootBox")) : MathML2BoxCalc.out;
            }
            case 122: {
                return (MathML2BoxCalc.parseInt == null) ? (MathML2BoxCalc.parseInt = AfegirNoCalc("CapsaTori")) : MathML2BoxCalc.parseInt;
            }
            case 123: {
                return (MathML2BoxCalc.plainString == null) ? (MathML2BoxCalc.plainString = AfegirNoCalc("CapsaDerivada")) : MathML2BoxCalc.plainString;
            }
            case 125: {
                super.I = "e";
                return (MathML2BoxCalc.printStackTrace == null) ? (MathML2BoxCalc.printStackTrace = AfegirNoCalc("CapsaProducteEscalar")) : MathML2BoxCalc.printStackTrace;
            }
            case 129: {
                return (MathML2BoxCalc.localName == null) ? (MathML2BoxCalc.localName = AfegirNoCalc("TextBox")) : MathML2BoxCalc.localName;
            }
            case 137: {
                super.I = "mtext";
                return (MathML2BoxCalc.localName == null) ? (MathML2BoxCalc.localName = AfegirNoCalc("TextBox")) : MathML2BoxCalc.localName;
            }
            case 300: {
                super.I = new Integer(0);
                return (MathML2BoxCalc.println == null) ? (MathML2BoxCalc.println = AfegirNoCalc("CapsaSi")) : MathML2BoxCalc.println;
            }
            case 301: {
                return (MathML2BoxCalc.inicialitzaFills == null) ? (MathML2BoxCalc.inicialitzaFills = AfegirNoCalc("CapsaInici")) : MathML2BoxCalc.inicialitzaFills;
            }
            case 302: {
                return (MathML2BoxCalc.put == null) ? (MathML2BoxCalc.put = AfegirNoCalc("CapsaPer")) : MathML2BoxCalc.put;
            }
            case 303: {
                return (MathML2BoxCalc.remove == null) ? (MathML2BoxCalc.remove = AfegirNoCalc("CapsaMentre")) : MathML2BoxCalc.remove;
            }
            case 304: {
                return (MathML2BoxCalc.show == null) ? (MathML2BoxCalc.show = AfegirNoCalc("CapsaRepetir")) : MathML2BoxCalc.show;
            }
            case 305: {
                super.I = "C";
                return (MathML2BoxCalc.string == null) ? (MathML2BoxCalc.string = AfegirNoCalc("CapsaVariacions")) : MathML2BoxCalc.string;
            }
            case 306: {
                super.I = "P";
                return (MathML2BoxCalc.string == null) ? (MathML2BoxCalc.string = AfegirNoCalc("CapsaVariacions")) : MathML2BoxCalc.string;
            }
            case 307: {
                super.I = "V";
                return (MathML2BoxCalc.string == null) ? (MathML2BoxCalc.string = AfegirNoCalc("CapsaVariacions")) : MathML2BoxCalc.string;
            }
            case 308: {
                super.I = "CR";
                return (MathML2BoxCalc.string == null) ? (MathML2BoxCalc.string = AfegirNoCalc("CapsaVariacions")) : MathML2BoxCalc.string;
            }
            case 309: {
                super.I = "PR";
                return (MathML2BoxCalc.string == null) ? (MathML2BoxCalc.string = AfegirNoCalc("CapsaVariacions")) : MathML2BoxCalc.string;
            }
            case 310: {
                super.I = "VR";
                return (MathML2BoxCalc.string == null) ? (MathML2BoxCalc.string = AfegirNoCalc("CapsaVariacions")) : MathML2BoxCalc.string;
            }
            case 311: {
                super.I = "p";
                return (MathML2BoxCalc.string2CharData == null) ? (MathML2BoxCalc.string2CharData = AfegirNoCalc("CapsaCombinatoria")) : MathML2BoxCalc.string2CharData;
            }
            case 312: {
                return (MathML2BoxCalc.substitute == null) ? (MathML2BoxCalc.substitute = AfegirNoCalc("CapsaLocal")) : MathML2BoxCalc.substitute;
            }
            case 400: {
                return (MathML2BoxCalc.indexOf == null) ? (MathML2BoxCalc.indexOf = AfegirNoCalc("MultilineBox")) : MathML2BoxCalc.indexOf;
            }
            case 501: {
                return (MathML2BoxCalc.substring == null) ? (MathML2BoxCalc.substring = AfegirNoCalc("Slider")) : MathML2BoxCalc.substring;
            }
            default: {
                return super.I(n);
            }
        }
    }
    
    protected final XMLNode Z(final XMLElement xmlElement) {
        return this.infere_rec((XMLElement)this.convertOldFormat(xmlElement));
    }
    
    private XMLNode infere_rec(final XMLElement xmlElement) {
        boolean b = false;
        for (int i = 0; i < xmlElement.children.length; ++i) {
            final XMLNode xmlNode = xmlElement.children[i];
            if (xmlNode instanceof XMLElement) {
                try {
                    xmlElement.children[i] = this.infere_rec((XMLElement)xmlNode);
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
        for (int j = 0; j < xmlElement.children.length; ++j) {
            final XMLNode xmlNode2 = xmlElement.children[j];
            if (xmlNode2 instanceof XMLElement) {
                final XMLElement xmlElement2 = (XMLElement)xmlNode2;
                int n;
                if (xmlElement2.localName().equals("msub")) {
                    n = 1;
                }
                else if (xmlElement2.localName().equals("msup")) {
                    n = 2;
                }
                else if (xmlElement2.localName().equals("msubsup")) {
                    n = 3;
                }
                else {
                    n = 0;
                }
                if ((n > 0 && xmlElement2.children.length == 2) || (n == 3 && xmlElement2.children.length == 3)) {
                    final XMLElement xmlElement3 = new XMLElement(xmlElement2.localName().substring(1), xmlElement2.children.length - 1);
                    System.arraycopy(xmlElement2.children, 1, xmlElement3.children, 0, xmlElement3.children.length);
                    if (n == 1 && (this.I(xmlElement2.children[0], "I") || xmlElement2.children[0].localName().equals("ident"))) {
                        xmlElement3.element_name = "idmatrix";
                        xmlElement.children[j] = xmlElement3;
                        continue;
                    }
                    int length = 1;
                    XMLNode[] array = xmlElement2.children;
                    if (array[0].localName().equals("mrow")) {
                        array = ((XMLElement)array[0]).children;
                        length = array.length;
                    }
                    if (xmlElement.localName().equals("mrow") || xmlElement.localName().equals("math") || xmlElement.localName().equals("mtd")) {
                        final XMLNode[] children = new XMLNode[xmlElement.children.length + length];
                        System.arraycopy(xmlElement.children, 0, children, 0, j);
                        System.arraycopy(array, 0, children, j, length);
                        children[j + length] = xmlElement3;
                        this.I(children, j, length + 1, xmlElement2);
                        System.arraycopy(xmlElement.children, j + 1, children, j + length + 1, xmlElement.children.length - j - 1);
                        xmlElement.children = children;
                        --j;
                    }
                    else {
                        final XMLElement xmlElement4 = new XMLElement("mrow", length + 1);
                        System.arraycopy(array, 0, xmlElement4.children, 0, length);
                        xmlElement4.children[length] = xmlElement3;
                        this.I(xmlElement4.children, 0, length + 1, xmlElement2);
                        xmlElement.children[j] = xmlElement4;
                    }
                }
                if (xmlElement2.localName().equals("mfenced") && xmlElement2.children.length == 1) {
                    String s = null;
                    String s2 = null;
                    final Vector vector = new Vector();
                    final XMLElement z;
                    if ((z = this.Z(xmlElement2.children[0], "mtable")) != null) {
                        final XMLElement k = this.I("matrix", z);
                        this.appendAttributes(k, xmlElement2);
                        if (xmlElement2.attributes != null) {
                            s = xmlElement2.attributes.get("open");
                            s2 = xmlElement2.attributes.get("close");
                            if (s != null) {
                                k.attributes.put("open", s);
                            }
                            if (s2 != null) {
                                k.attributes.put("close", s2);
                            }
                        }
                        if (Integer.parseInt((String)k.attributes.get("cells")) == 1 && s != null && s.equals("{") && s2 != null && s2.equals("}")) {
                            k.attributes = null;
                            k.element_name = "vlist";
                            xmlElement2.children[0] = k;
                        }
                        else {
                            xmlElement.children[j] = k;
                        }
                    }
                }
                if (!xmlElement.localName().equals("mfenced") && xmlElement2.localName().equals("mtable")) {
                    XMLElement xmlElement5;
                    if (xmlElement.localName().equals("munder") && j == 1) {
                        xmlElement5 = this.I("vcenter", xmlElement2);
                    }
                    else {
                        String string = "multiline";
                        if (xmlElement2.attributes != null) {
                            final String s3 = xmlElement2.attributes.get("align");
                            if (s3 != null) {
                                string = "v" + s3;
                            }
                        }
                        xmlElement5 = this.I(string, xmlElement2);
                    }
                    xmlElement.children[j] = xmlElement5;
                }
                if (xmlElement2.localName().equals("wtable")) {
                    final XMLElement l = this.I("wtable", xmlElement2);
                    this.appendAttributes(l, xmlElement2);
                    xmlElement.children[j] = l;
                }
                if (this.I(xmlNode2, "\u222b")) {
                    b = true;
                }
                final XMLElement z2;
                if (xmlElement2.localName().equals("munderover") && xmlElement2.children.length == 3 && (z2 = this.Z(xmlElement2.children[1], "mrow")) != null) {
                    final XMLElement xmlElement6 = new XMLElement("xtori", 4);
                    int n2;
                    for (n2 = 0; n2 < z2.children.length && !this.I(z2.children[n2], "="); ++n2) {}
                    xmlElement6.children[0] = xmlElement2.children[0];
                    xmlElement6.children[1] = this.mrow(z2.children, 0, n2);
                    xmlElement6.children[2] = this.mrow(z2.children, n2 + 1, z2.children.length - n2 - 1);
                    xmlElement6.children[3] = xmlElement2.children[2];
                    xmlElement.children[j] = xmlElement6;
                }
                final XMLElement z3;
                if (xmlElement2.localName().equals("munder") && xmlElement2.children.length == 2 && this.I(xmlElement2.children[0], "lim") && (z3 = this.Z(xmlElement2.children[1], "mrow")) != null) {
                    final XMLElement xmlElement7 = new XMLElement("limit", 3);
                    int n3;
                    for (n3 = 0; n3 < z3.children.length && (!z3.children[n3].localName().equals("mo") || MathML2BoxCalc.convertOldFormat.get(z3.children[n3].getText()) == null); ++n3) {}
                    xmlElement7.children[0] = z3.children[n3];
                    xmlElement7.children[1] = this.mrow(z3.children, 0, n3);
                    xmlElement7.children[2] = this.mrow(z3.children, n3 + 1, z3.children.length - n3 - 1);
                    xmlElement.children[j] = xmlElement7;
                }
            }
        }
        if (b) {
            for (int n4 = 0; n4 < xmlElement.children.length; ++n4) {
                final XMLNode xmlNode3 = xmlElement.children[n4];
                if (xmlNode3 instanceof XMLElement) {
                    final XMLElement xmlElement8 = (XMLElement)xmlNode3;
                    if (this.I(xmlNode3, "\u222b") && !xmlElement.localName().equals("msubsup")) {
                        final int busca_diferencial = this.busca_diferencial(xmlElement, n4);
                        final XMLElement xmlElement9 = new XMLElement();
                        final XMLElement z4;
                        int n5;
                        if ((z4 = this.Z(xmlElement.children[n4 + 1], "subsup")) != null) {
                            n5 = 1;
                            if (busca_diferencial < 0) {
                                xmlElement9.children = new XMLNode[] { z4.children[0], z4.children[1] };
                                xmlElement9.element_name = "integralf";
                            }
                            else {
                                xmlElement9.children = new XMLNode[] { z4.children[0], z4.children[1], null };
                                xmlElement9.element_name = "integral";
                            }
                        }
                        else {
                            n5 = 0;
                            if (busca_diferencial < 0) {
                                xmlElement9.children = null;
                                xmlElement9.element_name = "iintegralf";
                            }
                            else {
                                xmlElement9.children = new XMLNode[] { null };
                                xmlElement9.element_name = "iintegral";
                            }
                        }
                        if (busca_diferencial >= 0) {
                            final XMLElement xmlElement10 = new XMLElement("mrow", busca_diferencial - n4 - 1 - n5);
                            System.arraycopy(xmlElement.children, n4 + 1 + n5, xmlElement10.children, 0, busca_diferencial - n4 - 1 - n5);
                            xmlElement9.children[xmlElement9.children.length - 1] = xmlElement10;
                        }
                        xmlElement.remove(n4 + 1, Math.max(busca_diferencial, n4 + n5) - n4);
                        xmlElement.children[n4] = xmlElement9;
                    }
                }
            }
        }
        return xmlElement;
    }
    
    private int busca_diferencial(final XMLElement xmlElement, int i) {
        int n = 0;
        while (i < xmlElement.children.length) {
            final XMLNode xmlNode = xmlElement.children[i];
            if (this.I(xmlNode, "\u222b")) {
                ++n;
            }
            else if (this.I(xmlNode, "\u2146") && --n == 0) {
                return i;
            }
            ++i;
        }
        return -1;
    }
    
    private XMLNode convertOldFormat(final XMLNode xmlNode) {
        return xmlNode;
    }
    
    private static final Class AfegirNoCalc(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MathML2BoxCalc.AfegirNoCalc = new Hashtable();
        MathML2BoxCalc.TRUE = new Hashtable();
        MathML2BoxCalc.XMLcontent = new Hashtable();
        MathML2BoxCalc.add = new Hashtable();
        MathML2BoxCalc.amaga = new Hashtable();
        MathML2BoxCalc.appendScript = new Hashtable();
        MathML2BoxCalc.busca_diferencial = new Hashtable();
        MathML2BoxCalc.children = new Hashtable();
        MathML2BoxCalc.context = new Hashtable();
        MathML2BoxCalc.convertOldFormat = new Hashtable();
        MathML2BoxCalc.special2csymbol = new Hashtable();
        Utils.loadBinHashtable("+@+@-@-@*@*@/@/@=@=@:@:@{@\\{@}@\\}@&@\\&@^@\\^@$@\\$@\"@\\quote@\\@\\\\@%@\\%@'@\\prima@°@\\SIangledegree@\u2192@\\rightarrow@\u21d2@\\Rightarrow@\u2190@\\leftarrow@\u21d0@\\Leftarrow@:\u21d0@\\delayedruletuple@\u21a6@\\longmapsto@\u222a@\\cup@\u2229@\\cap@\u2208@\\in@\u2209@\\notin@\u2a7d@\\leq@\u2a7e@\\geq@\u2a75@\\eq@==@\\eq@\u2260@\\neq@:=@\\assign@\u00d7@\\times@\u2227@\\wedge@\u2228@\\vee@T@T@ @\\space@#i@\\row@#j@\\column@\u2205@\\empty@±@\\pm@\u2220@\\ang@^@\\hat@\u25b5@\\utri@caret@\\caret@beginselection@\\beginselection@endselection@\\endselection", MathML2BoxCalc.AfegirNoCalc, MathML2BoxCalc.TRUE);
        Utils.loadBinHashtable("\u03c0@\\Opi@\u2147@\\Oe@\u2148@\\Oi@j@\\Oj@\u221e@\\infty@+\u221e@\\pinfty@-\u221e@\\minfty@±\u221e@\\pminfty", MathML2BoxCalc.XMLcontent, MathML2BoxCalc.add);
        Utils.loadBinHashtable("naturalnumbers@\\NN@integers@\\ZZ@rationals@\\QQ@reals@\\RR@complexes@\\CC@exponentiale@\\Oe@imaginaryi@\\Oi@pi@\\Opi@infinity@\\infty", MathML2BoxCalc.appendScript, MathML2BoxCalc.amaga);
        Utils.loadBinHashtable("y@\\SIyocto@z@\\SIzepto@a@\\SIatto@f@\\SIfemto@p@\\SIpico@n@\\SInano@u@\\SImicro@m@\\SImilli@c@\\SIcenti@d@\\SIdeci@da@\\SIdeka@h@\\SIhecto@k@\\SIkilo@M@\\SImega@G@\\SIgiga@T@\\SItera@P@\\SIpeta@E@\\SIexa@Z@\\SIzetta@Y@\\SIyotta@meter@\\SImeter@gram@\\SIgram@second@\\SIsecond@ampere@\\SIampere@kelvin@\\SIkelvin@mole@\\SImole@candela@\\SIcandela@liter@\\SIliter@radian@\\SIradian@steradian@\\SIsteradian@hertz@\\SIhertz@newton@\\SInewton@pascal@\\SIpascal@joule@\\SIjoule@watt@\\SIwatt@coulomb@\\SIcoulomb@volt@\\SIvolt@farad@\\SIfarad@ohm@\\SIohm@siemens@\\SIsiemens@weber@\\SIweber@tesla@\\SItesla@henry@\\SIhenry@lumen@\\SIlumen@lux@\\SIlux@becquerel@\\SIbecquerel@gray@\\SIgray@sievert@\\SIsievert@katal@\\SIkatal@minute@\\SIminute@hour@\\SIhour@bar@\\SIbar@degree/angular@\\SIangledegree@minute/angular@\\SIangleminute@second/angular@\\SIanglesecond", MathML2BoxCalc.busca_diferencial, MathML2BoxCalc.special2csymbol);
        Utils.loadBinHashtable("(@p@)@p@[@b@]@b@{@B@}@B@\u2308@F@\u2309@F@\u230a@L@\u230b@L@|@v@\u2016@V", MathML2BoxCalc.children, null);
        Utils.loadBinHashtable("@\u2211@sum@\u220f@prod@\u22c3@bigcup@\u22c2@bigcap", MathML2BoxCalc.context, null);
        Utils.loadBinHashtable("@\u2198@rightlimit@\u2197@leftlimit@\u2192@limit@\\rightarrow@limit", MathML2BoxCalc.convertOldFormat, null);
    }
}
