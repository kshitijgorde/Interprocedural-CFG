import java.util.StringTokenizer;
import java.util.Enumeration;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class LatexACapsa extends ParseLatex$UserRender
{
    int AddCapsa;
    AbstractBox AddCapsaManteTokens;
    AbstractBox I;
    Font Z;
    Color C;
    int B;
    int D;
    static Class AddCapsaSola;
    static Class AfegirNoCalc;
    static Class ImposaAtributs;
    static Class InserirCapsa;
    static Class Token2Capsa;
    static Class Token2CapsaSola;
    static Class copiaEstil;
    static Class equals;
    static Class estil;
    static Class fill;
    static Class fontPropia;
    static Class forName;
    static Class forca_estil;
    static Class forced_color;
    static Class getClass;
    static Class getMessage;
    static Class hasMoreElements;
    static Class id2Capsa;
    static Class keys;
    static Class nextElement;
    static Class nextToken;
    static Class nfills;
    static Class out;
    static Class parseInt;
    
    public LatexACapsa() {
        this.Z = null;
        this.C = null;
        this.B = 0;
        this.D = 0;
        super.F = new ParseLatex(this, System.out);
        this.AddCapsa = 0;
        this.AddCapsa();
        super.F.I("\\sqrt", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.AddCapsaSola == null) ? (LatexACapsa.AddCapsaSola = AddCapsaManteTokens("RootBox")) : LatexACapsa.AddCapsaSola, "sqrt"));
        super.F.I("\\root", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.AddCapsaSola == null) ? (LatexACapsa.AddCapsaSola = AddCapsaManteTokens("RootBox")) : LatexACapsa.AddCapsaSola, "root"));
        super.F.I("_", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.AfegirNoCalc == null) ? (LatexACapsa.AfegirNoCalc = AddCapsaManteTokens("CapsaSubindex")) : LatexACapsa.AfegirNoCalc, null));
        super.F.I("^", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.ImposaAtributs == null) ? (LatexACapsa.ImposaAtributs = AddCapsaManteTokens("CapsaSuperindex")) : LatexACapsa.ImposaAtributs, null));
        super.F.I("\\transpose", 0, new LatexACapsa$Universal(this, super.F, 0, (LatexACapsa.InserirCapsa == null) ? (LatexACapsa.InserirCapsa = AddCapsaManteTokens("CapsaTransposadora")) : LatexACapsa.InserirCapsa, null));
        super.F.I("\\differentiate", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.Token2Capsa == null) ? (LatexACapsa.Token2Capsa = AddCapsaManteTokens("CapsaDerivada")) : LatexACapsa.Token2Capsa, null));
        super.F.I("\\integral", 3, new LatexACapsa$Universal(this, super.F, 3, (LatexACapsa.Token2CapsaSola == null) ? (LatexACapsa.Token2CapsaSola = AddCapsaManteTokens("CapsaIntegrate")) : LatexACapsa.Token2CapsaSola, "integral"));
        super.F.I("\\iintegral", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.Token2CapsaSola == null) ? (LatexACapsa.Token2CapsaSola = AddCapsaManteTokens("CapsaIntegrate")) : LatexACapsa.Token2CapsaSola, "iintegral"));
        super.F.I("\\integralf", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.Token2CapsaSola == null) ? (LatexACapsa.Token2CapsaSola = AddCapsaManteTokens("CapsaIntegrate")) : LatexACapsa.Token2CapsaSola, "integralf"));
        super.F.I("\\iintegralf", 0, new LatexACapsa$Universal(this, super.F, 0, (LatexACapsa.Token2CapsaSola == null) ? (LatexACapsa.Token2CapsaSola = AddCapsaManteTokens("CapsaIntegrate")) : LatexACapsa.Token2CapsaSola, "iintegralf"));
        super.F.I("\\emptybox", 0, new LatexACapsa$Universal(this, super.F, 0, (LatexACapsa.copiaEstil == null) ? (LatexACapsa.copiaEstil = AddCapsaManteTokens("EmptyBox")) : LatexACapsa.copiaEstil, null));
        super.F.I("\\specialemptybox", 1, new LatexACapsa$Particular(this, super.F, 7));
        super.F.I("\\parentesis", 3, new LatexACapsa$Particular(this, super.F, 9));
        super.F.I("\\pparentesis", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.equals == null) ? (LatexACapsa.equals = AddCapsaManteTokens("CapsaParentesis")) : LatexACapsa.equals, "pp"));
        super.F.I("\\bparentesis", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.equals == null) ? (LatexACapsa.equals = AddCapsaManteTokens("CapsaParentesis")) : LatexACapsa.equals, "bb"));
        super.F.I("\\Bparentesis", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.equals == null) ? (LatexACapsa.equals = AddCapsaManteTokens("CapsaParentesis")) : LatexACapsa.equals, "BB"));
        super.F.I("\\vparentesis", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.equals == null) ? (LatexACapsa.equals = AddCapsaManteTokens("CapsaParentesis")) : LatexACapsa.equals, "vv"));
        super.F.I("\\Vparentesis", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.equals == null) ? (LatexACapsa.equals = AddCapsaManteTokens("CapsaParentesis")) : LatexACapsa.equals, "VV"));
        super.F.I("\\ceil", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.equals == null) ? (LatexACapsa.equals = AddCapsaManteTokens("CapsaParentesis")) : LatexACapsa.equals, "FF"));
        super.F.I("\\floor", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.equals == null) ? (LatexACapsa.equals = AddCapsaManteTokens("CapsaParentesis")) : LatexACapsa.equals, "LL"));
        super.F.I("\\pcombinatori", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.estil == null) ? (LatexACapsa.estil = AddCapsaManteTokens("CapsaCombinatoria")) : LatexACapsa.estil, "p"));
        super.F.I("\\bcombinatori", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.estil == null) ? (LatexACapsa.estil = AddCapsaManteTokens("CapsaCombinatoria")) : LatexACapsa.estil, "b"));
        super.F.I("\\Bcombinatori", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.estil == null) ? (LatexACapsa.estil = AddCapsaManteTokens("CapsaCombinatoria")) : LatexACapsa.estil, "B"));
        super.F.I("\\vcombinatori", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.estil == null) ? (LatexACapsa.estil = AddCapsaManteTokens("CapsaCombinatoria")) : LatexACapsa.estil, "v"));
        super.F.I("\\Vcombinatori", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.estil == null) ? (LatexACapsa.estil = AddCapsaManteTokens("CapsaCombinatoria")) : LatexACapsa.estil, "V"));
        super.F.I("\\scalarproduct", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.fill == null) ? (LatexACapsa.fill = AddCapsaManteTokens("CapsaProducteEscalar")) : LatexACapsa.fill, "e"));
        super.F.I("\\identitymatrix", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.fontPropia == null) ? (LatexACapsa.fontPropia = AddCapsaManteTokens("CapsaMatriuIdentitat")) : LatexACapsa.fontPropia, null));
        super.F.I("\\sum", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.forName == null) ? (LatexACapsa.forName = AddCapsaManteTokens("CapsaTori")) : LatexACapsa.forName, "sum"));
        super.F.I("\\prod", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.forName == null) ? (LatexACapsa.forName = AddCapsaManteTokens("CapsaTori")) : LatexACapsa.forName, "prod"));
        super.F.I("\\sumx", 3, new LatexACapsa$Universal(this, super.F, 3, (LatexACapsa.forca_estil == null) ? (LatexACapsa.forca_estil = AddCapsaManteTokens("CapsaTori2")) : LatexACapsa.forca_estil, "sum"));
        super.F.I("\\prodx", 3, new LatexACapsa$Universal(this, super.F, 3, (LatexACapsa.forca_estil == null) ? (LatexACapsa.forca_estil = AddCapsaManteTokens("CapsaTori2")) : LatexACapsa.forca_estil, "prod"));
        super.F.I("\\beginbox", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.forced_color == null) ? (LatexACapsa.forced_color = AddCapsaManteTokens("CapsaInici")) : LatexACapsa.forced_color, null));
        super.F.I("\\whilebox", 3, new LatexACapsa$Universal(this, super.F, 3, (LatexACapsa.getClass == null) ? (LatexACapsa.getClass = AddCapsaManteTokens("CapsaMentre")) : LatexACapsa.getClass, null));
        super.F.I("\\repeatbox", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.getMessage == null) ? (LatexACapsa.getMessage = AddCapsaManteTokens("CapsaRepetir")) : LatexACapsa.getMessage, null));
        super.F.I("\\forbox", 3, new LatexACapsa$Universal(this, super.F, 3, (LatexACapsa.hasMoreElements == null) ? (LatexACapsa.hasMoreElements = AddCapsaManteTokens("CapsaPer")) : LatexACapsa.hasMoreElements, null));
        super.F.I("\\beginifbox", "\\endifbox", new LatexACapsa$Si(this, super.F, 1));
        super.F.I("\\local", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.id2Capsa == null) ? (LatexACapsa.id2Capsa = AddCapsaManteTokens("CapsaLocal")) : LatexACapsa.id2Capsa, null));
        super.F.I("\\begincommands", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.forced_color == null) ? (LatexACapsa.forced_color = AddCapsaManteTokens("CapsaInici")) : LatexACapsa.forced_color, null));
        super.F.I("\\while", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.getClass == null) ? (LatexACapsa.getClass = AddCapsaManteTokens("CapsaMentre")) : LatexACapsa.getClass, null));
        super.F.I("\\repeat", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.getMessage == null) ? (LatexACapsa.getMessage = AddCapsaManteTokens("CapsaRepetir")) : LatexACapsa.getMessage, null));
        super.F.I("\\for", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.hasMoreElements == null) ? (LatexACapsa.hasMoreElements = AddCapsaManteTokens("CapsaPer")) : LatexACapsa.hasMoreElements, null));
        super.F.I("\\beginif", "\\endif", new LatexACapsa$Si(this, super.F, 0));
        super.F.I("\\Cnm", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.keys == null) ? (LatexACapsa.keys = AddCapsaManteTokens("CapsaVariacions")) : LatexACapsa.keys, "C"));
        super.F.I("\\CRnm", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.keys == null) ? (LatexACapsa.keys = AddCapsaManteTokens("CapsaVariacions")) : LatexACapsa.keys, "CR"));
        super.F.I("\\Vnm", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.keys == null) ? (LatexACapsa.keys = AddCapsaManteTokens("CapsaVariacions")) : LatexACapsa.keys, "V"));
        super.F.I("\\VRnm", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.keys == null) ? (LatexACapsa.keys = AddCapsaManteTokens("CapsaVariacions")) : LatexACapsa.keys, "VR"));
        super.F.I("\\Pnm", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.keys == null) ? (LatexACapsa.keys = AddCapsaManteTokens("CapsaVariacions")) : LatexACapsa.keys, "P"));
        super.F.I("\\PRnm", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.keys == null) ? (LatexACapsa.keys = AddCapsaManteTokens("CapsaVariacions")) : LatexACapsa.keys, "PR"));
        super.F.I("\\SIyocto", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIyocto"));
        super.F.I("\\SIzepto", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIzepto"));
        super.F.I("\\SIatto", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIatto"));
        super.F.I("\\SIfemto", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIfemto"));
        super.F.I("\\SIpico", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIpico"));
        super.F.I("\\SInano", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SInano"));
        super.F.I("\\SImicro", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SImicro"));
        super.F.I("\\SImilli", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SImilli"));
        super.F.I("\\SIcenti", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIcenti"));
        super.F.I("\\SIdeci", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIdeci"));
        super.F.I("\\SIdeka", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIdeka"));
        super.F.I("\\SIhecto", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIhecto"));
        super.F.I("\\SIkilo", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIkilo"));
        super.F.I("\\SImega", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SImega"));
        super.F.I("\\SIgiga", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIgiga"));
        super.F.I("\\SItera", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SItera"));
        super.F.I("\\SIpeta", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIpeta"));
        super.F.I("\\SIexa", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIexa"));
        super.F.I("\\SIzetta", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIzetta"));
        super.F.I("\\SIyotta", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextElement == null) ? (LatexACapsa.nextElement = AddCapsaManteTokens("CapsaUnitat")) : LatexACapsa.nextElement, "\\SIyotta"));
        super.F.I("\\beginpmatrix", "\\endpmatrix", new LatexACapsa$Matriu(this, super.F, "p"));
        super.F.I("\\beginbmatrix", "\\endbmatrix", new LatexACapsa$Matriu(this, super.F, "b"));
        super.F.I("\\beginBmatrix", "\\endBmatrix", new LatexACapsa$Matriu(this, super.F, "B"));
        super.F.I("\\beginvmatrix", "\\endvmatrix", new LatexACapsa$Matriu(this, super.F, "v"));
        super.F.I("\\beginVmatrix", "\\endVmatrix", new LatexACapsa$Matriu(this, super.F, "V"));
        super.F.I("\\begintable", "\\endtable", new LatexACapsa$Matriu(this, super.F, "X"));
        super.F.I("\\beginpvector", "\\endpvector", new LatexACapsa$Vector(this, super.F, "p"));
        super.F.I("\\beginbvector", "\\endbvector", new LatexACapsa$Vector(this, super.F, "b"));
        super.F.I("\\beginBvector", "\\endBvector", new LatexACapsa$Vector(this, super.F, "B"));
        super.F.I("\\beginvvector", "\\endvvector", new LatexACapsa$Vector(this, super.F, "v"));
        super.F.I("\\beginVvector", "\\endVvector", new LatexACapsa$Vector(this, super.F, "V"));
        super.F.I("\\beginvbox", "\\endvbox", new LatexACapsa$vBox(this, super.F, "box"));
        super.F.I("\\beginvtop", "\\endvtop", new LatexACapsa$vBox(this, super.F, "top"));
        super.F.I("\\beginvcenter", "\\endvcenter", new LatexACapsa$vBox(this, super.F, "center"));
        super.F.I("\\beginvbottom", "\\endvbottom", new LatexACapsa$vBox(this, super.F, "bottom"));
        super.F.I("\\beginmultiline", "\\endmultiline", new LatexACapsa$Particular(this, super.F, 10));
        super.F.I("\\interface", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextToken == null) ? (LatexACapsa.nextToken = AddCapsaManteTokens("InterfaceBox")) : LatexACapsa.nextToken, null, false));
        super.F.I("\\commands", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nfills == null) ? (LatexACapsa.nfills = AddCapsaManteTokens("CapsaComandes")) : LatexACapsa.nfills, null, false));
        super.F.I("\\command", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.out == null) ? (LatexACapsa.out = AddCapsaManteTokens("CapsaComanda")) : LatexACapsa.out, new Boolean(false), false));
        super.F.I("\\comment", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.out == null) ? (LatexACapsa.out = AddCapsaManteTokens("CapsaComanda")) : LatexACapsa.out, new Boolean(true), false));
        super.F.I("\\library", 3, new LatexACapsa$Universal(this, super.F, 3, (LatexACapsa.parseInt == null) ? (LatexACapsa.parseInt = AddCapsaManteTokens("CapsaLlibreria")) : LatexACapsa.parseInt, null, false));
        super.F.I("\\relax", 0, new LatexACapsa$Particular(this, super.F, 6));
        super.F.I("\\interficie", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nextToken == null) ? (LatexACapsa.nextToken = AddCapsaManteTokens("InterfaceBox")) : LatexACapsa.nextToken, null, false));
        super.F.I("\\comandes", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.nfills == null) ? (LatexACapsa.nfills = AddCapsaManteTokens("CapsaComandes")) : LatexACapsa.nfills, null, false));
        super.F.I("\\comanda", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.out == null) ? (LatexACapsa.out = AddCapsaManteTokens("CapsaComanda")) : LatexACapsa.out, new Boolean(false), false));
        super.F.I("\\comentari", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.out == null) ? (LatexACapsa.out = AddCapsaManteTokens("CapsaComanda")) : LatexACapsa.out, new Boolean(true), false));
        super.F.I("\\inici", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.forced_color == null) ? (LatexACapsa.forced_color = AddCapsaManteTokens("CapsaInici")) : LatexACapsa.forced_color, null));
        super.F.I("\\mentre", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.getClass == null) ? (LatexACapsa.getClass = AddCapsaManteTokens("CapsaMentre")) : LatexACapsa.getClass, null));
        super.F.I("\\per", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.hasMoreElements == null) ? (LatexACapsa.hasMoreElements = AddCapsaManteTokens("CapsaPer")) : LatexACapsa.hasMoreElements, null));
        super.F.I("\\beginsi", "\\endsi", new LatexACapsa$Si(this, super.F, 0));
        super.F.I("\\producteescalar", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.fill == null) ? (LatexACapsa.fill = AddCapsaManteTokens("CapsaProducteEscalar")) : LatexACapsa.fill, "e"));
        super.F.I("\\matriuidentitat", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.fontPropia == null) ? (LatexACapsa.fontPropia = AddCapsaManteTokens("CapsaMatriuIdentitat")) : LatexACapsa.fontPropia, null));
        super.F.I("\\especialemptybox", 1, new LatexACapsa$Particular(this, super.F, 7));
        super.F.I("\\primitiva", 1, new LatexACapsa$Universal(this, super.F, 1, (LatexACapsa.Token2CapsaSola == null) ? (LatexACapsa.Token2CapsaSola = AddCapsaManteTokens("CapsaIntegrate")) : LatexACapsa.Token2CapsaSola, "iintegral"));
        super.F.I("\\primitivaf", 0, new LatexACapsa$Universal(this, super.F, 0, (LatexACapsa.Token2CapsaSola == null) ? (LatexACapsa.Token2CapsaSola = AddCapsaManteTokens("CapsaIntegrate")) : LatexACapsa.Token2CapsaSola, "iintegralf"));
        super.F.I("\\transposada", 0, new LatexACapsa$Universal(this, super.F, 0, (LatexACapsa.InserirCapsa == null) ? (LatexACapsa.InserirCapsa = AddCapsaManteTokens("CapsaTransposadora")) : LatexACapsa.InserirCapsa, null));
        super.F.I("\\derivada", 2, new LatexACapsa$Universal(this, super.F, 2, (LatexACapsa.Token2Capsa == null) ? (LatexACapsa.Token2Capsa = AddCapsaManteTokens("CapsaDerivada")) : LatexACapsa.Token2Capsa, null));
        super.F.I("\\font", 4, new LatexACapsa$Format(this, super.F, 1));
        super.F.I("\\color", 4, new LatexACapsa$Format(this, super.F, 2));
        super.F.I("\\localcomment", 1, new LatexACapsa$Format(this, super.F, 3, 1));
        super.F.I("\\noeditable", 1, new LatexACapsa$Format(this, super.F, 3, 2));
        super.F.I("\\invisible", 1, new LatexACapsa$Format(this, super.F, 3, 4));
        super.F.I("\\beginproperties", "\\endproperties", new LatexACapsa$Format(this, super.F, 4));
        super.F.I("\\button", 2, new LatexACapsa$Format(this, super.F, 12));
        this.I = new EmptyBox();
        final Enumeration<String> keys = Especials.simbols2wiris.keys();
        while (keys.hasMoreElements()) {
            super.F.I(keys.nextElement(), 0, new LatexACapsa$Particular(this, super.F, 8));
        }
    }
    
    public final synchronized AbstractBox parse(final String s) {
        this.AddCapsa = 0;
        super.F.Z(s);
        if (this.AddCapsa == 0) {
            return new EmptyBox();
        }
        return this.AddCapsaManteTokens;
    }
    
    private AbstractBox Token2Capsa(final Token token) {
        this.I();
        final AbstractBox addCapsaManteTokens = this.AddCapsaManteTokens;
        final int addCapsa = this.AddCapsa;
        this.AddCapsa = 0;
        token.renderitza(super.F.B);
        this.I();
        AbstractBox addCapsaManteTokens2;
        if (this.AddCapsa == 0) {
            addCapsaManteTokens2 = new EmptyBox();
        }
        else {
            addCapsaManteTokens2 = this.AddCapsaManteTokens;
        }
        this.AddCapsa = addCapsa;
        this.AddCapsaManteTokens = addCapsaManteTokens;
        return addCapsaManteTokens2;
    }
    
    private AbstractBox Token2CapsaSola(final Token token) {
        AbstractBox token2Capsa = this.Token2Capsa(token);
        if (token2Capsa instanceof TokensBox && token2Capsa.nfills == 1) {
            token2Capsa = token2Capsa.fill[0];
        }
        return token2Capsa;
    }
    
    private void AddCapsaManteTokens(final AbstractBox abstractBox) {
        this.ImposaAtributs(abstractBox);
        if (this.AddCapsa == 0) {
            this.AddCapsaManteTokens = new TokensBox();
        }
        this.AddCapsaManteTokens.AfegirNoCalc(abstractBox);
        ++this.AddCapsa;
    }
    
    private void AddCapsa(final AbstractBox addCapsaManteTokens) {
        this.ImposaAtributs(addCapsaManteTokens);
        if (this.AddCapsa == 0) {
            if (addCapsaManteTokens instanceof EmptyBox) {
                this.AddCapsaManteTokens = addCapsaManteTokens;
            }
            else {
                final TokensBox addCapsaManteTokens2 = new TokensBox();
                addCapsaManteTokens2.InserirCapsa(null, addCapsaManteTokens);
                this.AddCapsaManteTokens = addCapsaManteTokens2;
            }
        }
        else if (this.AddCapsaManteTokens instanceof EmptyBox) {
            final TokensBox addCapsaManteTokens3 = new TokensBox();
            addCapsaManteTokens3.InserirCapsa(null, this.AddCapsaManteTokens);
            addCapsaManteTokens3.InserirCapsa(null, addCapsaManteTokens);
            this.AddCapsaManteTokens = addCapsaManteTokens3;
        }
        else {
            if (!(this.AddCapsaManteTokens instanceof TokensBox)) {
                return;
            }
            ((TokensBox)this.AddCapsaManteTokens).InserirCapsa(null, addCapsaManteTokens);
        }
        ++this.AddCapsa;
    }
    
    private void ImposaAtributs(final AbstractBox abstractBox) {
        if (this.Z != null) {
            abstractBox.fontPropia = this.Z;
        }
        if (this.C != null) {
            abstractBox.forced_color = this.C;
        }
        abstractBox.estil = AbstractBox.copiaEstil(abstractBox.estil, this.B, this.D & ~abstractBox.forca_estil);
        abstractBox.forca_estil |= this.D;
    }
    
    private void AddCapsaSola(final AbstractBox addCapsaManteTokens) {
        this.ImposaAtributs(addCapsaManteTokens);
        if (this.AddCapsa == 0) {
            this.AddCapsaManteTokens = addCapsaManteTokens;
        }
        else if (this.AddCapsa == 1 && !(this.AddCapsaManteTokens instanceof TokensBox)) {
            final TokensBox addCapsaManteTokens2 = new TokensBox();
            addCapsaManteTokens2.InserirCapsa(null, this.AddCapsaManteTokens);
            addCapsaManteTokens2.InserirCapsa(null, addCapsaManteTokens);
            this.AddCapsaManteTokens = addCapsaManteTokens2;
        }
        else {
            ((TokensBox)this.AddCapsaManteTokens).InserirCapsa(null, addCapsaManteTokens);
        }
        ++this.AddCapsa;
    }
    
    protected final void I(final String s, final int n) {
        switch (n) {
            case 0: {
                this.AddCapsa(new TextBox(s, 1));
                break;
            }
            case 3: {
                this.AddCapsa(new SpaceBox(0));
                break;
            }
            case 1: {
                this.AddCapsa(new TextBox(s, 3));
                break;
            }
            case 2: {
                this.AddCapsa(new TextBox(s, 2));
                break;
            }
            case 4: {
                this.AddCapsa(new EmptyBox());
                break;
            }
            default: {
                this.AddCapsa(new TextBox(s, 1));
                break;
            }
        }
    }
    
    private final void AddCapsa() {
        final StringTokenizer stringTokenizer = new StringTokenizer("\\argument#1#2#argument#1#\\frac#2#3#null#1#\\objectanddomain#2#4#null#0#\\quorem#2#5#null#1#\\quoremdone#4#5#null#1#\\limit#2#6#limit#1#\\rightlimit#2#6#rightlimit#1#\\leftlimit#2#6#leftlimit#1#\\limitd#2#6#rightlimit#1#\\limite#2#6#leftlimit#1", "#");
        while (stringTokenizer.hasMoreElements()) {
            final String nextToken = stringTokenizer.nextToken();
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final Class<? extends AbstractBox> class1 = Capses.id2Capsa(Integer.parseInt(stringTokenizer.nextToken())).getClass();
            String nextToken2 = stringTokenizer.nextToken();
            final boolean b = !stringTokenizer.nextToken().equals("0");
            if (nextToken2.equals("null")) {
                nextToken2 = null;
            }
            super.F.I(nextToken, int1, new LatexACapsa$Universal(this, super.F, int1, class1, nextToken2, b));
        }
    }
    
    private static final Class AddCapsaManteTokens(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static final AbstractBox I(final LatexACapsa latexACapsa, final Token token) {
        return latexACapsa.Token2Capsa(token);
    }
    
    static final void I(final LatexACapsa latexACapsa, final AbstractBox abstractBox) {
        latexACapsa.AddCapsa(abstractBox);
    }
    
    static final void Z(final LatexACapsa latexACapsa, final AbstractBox abstractBox) {
        latexACapsa.AddCapsaSola(abstractBox);
    }
    
    static final AbstractBox Z(final LatexACapsa latexACapsa, final Token token) {
        return latexACapsa.Token2CapsaSola(token);
    }
    
    static final void C(final LatexACapsa latexACapsa, final AbstractBox abstractBox) {
        latexACapsa.AddCapsaManteTokens(abstractBox);
    }
}
