import java.util.StringTokenizer;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class XMLParser
{
    int n;
    int i;
    char c;
    char[] append;
    XMLElement[] attributes;
    int charAt;
    StringBuffer clone;
    StringBuffer element_name;
    int equals;
    int err;
    public static Hashtable entity2char;
    static Hashtable get;
    public static Hashtable opt_char2entity;
    public static Hashtable char2entity_and_space;
    
    public static final String string2CharData(final String s) {
        return string2CharData(XMLParser.get, s);
    }
    
    public static final String string2CharData(final Hashtable hashtable, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            final String s2 = hashtable.get("" + char1);
            if (s2 != null) {
                sb.append("&");
                sb.append(s2);
                sb.append(";");
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    public final XMLElement parseXML(final String s) {
        return this.parseXML(s.toCharArray());
    }
    
    public final XMLElement parseXML(final char[] array) {
        try {
            return this.parseXMLPrim(array);
        }
        catch (Throwable t) {
            throw new Error("XML parser error at line " + this.equals + " and column " + (this.i - this.err), t);
        }
    }
    
    public final XMLElement parseXMLPrim(final char[] append) {
        final StringBuffer sb = new StringBuffer();
        this.clone = new StringBuffer();
        this.element_name = new StringBuffer();
        this.attributes = new XMLElement[100];
        this.charAt = 0;
        this.equals = 0;
        this.err = 0;
        this.n = append.length;
        this.append = append;
        try {
            this.attributes[this.charAt] = new XMLElement();
            this.attributes[this.charAt].element_name = "";
            this.attributes[this.charAt].attributes = null;
            ++this.charAt;
            this.i = 0;
            this.append();
            while (this.i <= this.n) {
                final String attributes = this.attributes();
                if (this.c == '<') {
                    this.append();
                    if (this.c == '/') {
                        this.append();
                        this.attributes();
                        this.char2entity_and_space();
                        this.attributes();
                        this.equals('>');
                        this.err();
                        this.append();
                    }
                    else if (this.c == '!') {
                        this.append();
                        this.equals('-');
                        this.append();
                        this.equals('-');
                        this.append();
                        this.hasMoreElements("-->");
                    }
                    else {
                        boolean b;
                        if (this.c == '?') {
                            b = true;
                            this.append();
                        }
                        else {
                            b = false;
                        }
                        this.attributes[this.charAt] = new XMLElement();
                        this.attributes[this.charAt].element_name = (b ? ("?" + this.char2entity_and_space()) : this.char2entity_and_space());
                        this.attributes[this.charAt].attributes = this.entity2char();
                        this.attributes();
                        ++this.charAt;
                        if (b) {
                            if (this.attributes[this.charAt - 1].element_name.equals("?xml")) {
                                --this.charAt;
                                this.hasMoreElements("?>");
                            }
                            else {
                                this.equals('?');
                                this.append();
                                this.equals('>');
                                this.err();
                                this.append();
                            }
                        }
                        else if (this.c == '/') {
                            this.append();
                            this.equals('>');
                            this.err();
                            this.append();
                        }
                        else {
                            this.equals('>');
                            this.append();
                        }
                    }
                }
                else {
                    sb.setLength(0);
                    sb.append(attributes);
                    do {
                        if (this.c == '&') {
                            sb.append(this.charAt());
                        }
                        else {
                            sb.append(this.c);
                            if (this.i == this.n) {
                                ++this.i;
                                break;
                            }
                            this.append();
                        }
                    } while (this.c != '<');
                    this.get(sb.toString());
                }
            }
            this.err();
        }
        catch (Exception ex) {
            if (this.i < this.n || this.charAt != 1) {
                ex.printStackTrace();
            }
        }
        if (this.charAt != 1) {
            System.err.println("Falta tancar tags");
        }
        this.attributes[0].I();
        return this.attributes[0];
    }
    
    final void append() {
        if (this.i >= this.n) {
            throw new Exception("end");
        }
        this.c = this.append[this.i];
        ++this.i;
        if (this.c == '\r') {
            this.c = '\n';
            if (this.i < this.n && this.append[this.i] == '\n') {
                ++this.i;
            }
        }
        if (this.c == '\n') {
            ++this.equals;
            this.err = this.i;
        }
    }
    
    private final String attributes() {
        final StringBuffer sb = new StringBuffer();
        while (this.c == ' ' || this.c == '\n' || this.c == '\r' || this.c == '\t') {
            sb.append(this.c);
            this.append();
        }
        return sb.toString();
    }
    
    private final String char2entity_and_space() {
        this.clone.setLength(0);
        while ((this.c >= '0' && this.c <= '9') || (this.c >= 'a' && this.c <= 'z') || (this.c >= 'A' && this.c <= 'Z') || this.c == ':' || this.c == '_' || this.c == '-') {
            this.clone.append(this.c);
            this.append();
        }
        return this.clone.toString();
    }
    
    private final String charAt() {
        this.clone.setLength(0);
        this.append();
        while (this.c != ';') {
            this.clone.append(this.c);
            this.append();
        }
        this.append();
        final String string = this.clone.toString();
        if (string.startsWith("#")) {
            String s;
            int n;
            if (string.startsWith("#x")) {
                s = string.substring(2);
                n = 16;
            }
            else {
                s = string.substring(1);
                n = 10;
            }
            return String.valueOf((char)Integer.parseInt(s, n));
        }
        String s2 = XMLParser.entity2char.get(string);
        if (s2 == null) {
            s2 = "";
        }
        return s2;
    }
    
    private final String element_name() {
        this.element_name.setLength(0);
        if (this.c != '\"' && this.c != '\'') {
            new Exception("Syntax error");
        }
        final char c = this.c;
        this.append();
        while (this.c != c) {
            if (this.c == '&') {
                this.element_name.append(this.charAt());
            }
            else {
                this.element_name.append(this.c);
                this.append();
            }
        }
        this.append();
        return this.element_name.toString();
    }
    
    private final Hashtable entity2char() {
        Hashtable<String, String> hashtable = null;
        while (true) {
            this.attributes();
            final String char2entity_and_space = this.char2entity_and_space();
            if (char2entity_and_space.length() == 0) {
                break;
            }
            this.attributes();
            this.equals('=');
            this.append();
            this.attributes();
            final String element_name = this.element_name();
            if (hashtable == null) {
                hashtable = new Hashtable<String, String>();
            }
            hashtable.put(char2entity_and_space, element_name);
        }
        return hashtable;
    }
    
    private final void equals(final char c) {
        if (this.c != c) {
            throw new Exception("Syntax error");
        }
    }
    
    private final void err() {
        if (this.charAt == 1) {
            throw new Exception("end");
        }
        if (this.charAt < 1) {
            throw new Exception("Stack error");
        }
        this.attributes[this.charAt - 2].I(this.attributes[this.charAt - 1]);
        --this.charAt;
    }
    
    private final void get(final String text) {
        if (this.charAt <= 0) {
            throw new Exception("Stack error");
        }
        final XMLText xmlText = new XMLText();
        xmlText.element_name = "text";
        xmlText.text = text;
        this.attributes[this.charAt - 1].I(xmlText);
    }
    
    private final void hasMoreElements(final String s) {
        final int length = s.length();
        final int[] array = new int[length + 1];
        for (int i = 0; i < length + 1; ++i) {
            array[i] = 0;
        }
    Block_4:
        while (true) {
            for (int j = length; j >= 0; --j) {
                if (array[j] == j) {
                    if (j == length) {
                        break Block_4;
                    }
                    if (this.c == s.charAt(j)) {
                        array[j + 1] = j + 1;
                    }
                    else {
                        array[j + 1] = 0;
                    }
                }
            }
            this.append();
        }
    }
    
    public static final void loadBinHashtable(final String s, final Hashtable hashtable, final Hashtable hashtable2, final char c) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "" + c);
        while (stringTokenizer.hasMoreElements()) {
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            if (hashtable != null) {
                hashtable.put(nextToken, nextToken2);
            }
            if (hashtable2 != null) {
                hashtable2.put(nextToken2, nextToken);
            }
        }
    }
    
    static {
        XMLParser.entity2char = new Hashtable();
        XMLParser.get = new Hashtable();
        XMLParser.opt_char2entity = new Hashtable();
        XMLParser.char2entity_and_space = new Hashtable();
        loadBinHashtable("lt#<#gt#>#amp#&#apos#'#quot#\"#nbsp# ", XMLParser.entity2char, XMLParser.get, '#');
        loadBinHashtable("epsi#\u220a#omicron#\u03be#alpha#\u03b1#beta#\u03b2#chi#\u03c7#Delta#\u0394#delta#\u03b4#epsiv#\u03b5#eta#\u03b7#Gamma#\u0393#gamma#\u03b3#Gammad#\u03dc#gammad#\u03dd#iota#\u03b9#kappa#\u03ba#kappav#\u03f0#Lambda#\u039b#lambda#\u03bb#mu#\u03bc#nu#\u03bd#Omega#\u03a9#omega#\u03c9#Phi#\u03a6#phi#\u03c6#phiv#\u03d5#Pi#\u03a0#pi#\u03c0#piv#\u03d6#Psi#\u03a8#psi#\u03c8#rho#\u03c1#rhov#\u03f1#Sigma#\u03a3#sigma#\u03c3#sigmav#\u03c2#tau#\u03c4#Theta#\u0398#theta#\u03b8#thetav#\u03d1#Upsi#\u03d2#upsi#\u03c5#Xi#\u039e#xi#\u03be#zeta#\u03b6#rarr#\u2192#rArr#\u21d2#larr#\u2190#lArr#\u21d0#map#\u21a6#ExponentialE#\u2147#ImaginaryI#\u2148#infin#\u221e#plusmn#±#cup#\u222a#cap#\u2229#isin#\u2208#notin#\u2209#les#\u2a7d#ges#\u2a7e#Equal#\u2a75#ne#\u2260#times#\u00d7#and#\u2227#or#\u2228#lceil#\u2308#lfloor#\u230a#rceil#\u2309#rfloor#\u230b#verbar#|#Verbar#\u2016#int#\u222b#DifferentialD#\u2146#sum#\u2211#prod#\u220f#xcup#\u22c3#xcap#\u22c2#nearr#\u2197#searr#\u2198#nbsp# #deg#°#ang#\u2220#empty#\u2205#xutri#\u25b3#utri#\u25b5#Hat#^", XMLParser.entity2char, XMLParser.opt_char2entity, '#');
        (XMLParser.char2entity_and_space = (Hashtable)XMLParser.get.clone()).put(new Character(' '), " ");
    }
}
