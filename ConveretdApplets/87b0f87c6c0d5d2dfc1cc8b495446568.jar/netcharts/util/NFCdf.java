// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.BufferedInputStream;
import java.awt.Dimension;
import java.util.Enumeration;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;

public class NFCdf
{
    public int id;
    public int type;
    public String name;
    public String codebase;
    public int width;
    public int height;
    public NFHtmlTag appletTag;
    public Vector tags;
    public Hashtable params;
    public Vector exprs;
    public Vector script;
    public boolean maintainScript;
    public String fileBase;
    public URL url;
    private static final boolean a = false;
    private NFToken b;
    
    public NFCdf() {
        this.id = 0;
        this.type = -1;
        this.name = "NoNameChart";
        this.codebase = null;
        this.width = 400;
        this.height = 250;
        this.appletTag = null;
        this.tags = new Vector();
        this.params = new Hashtable();
        this.exprs = new Vector();
        this.script = new Vector();
        this.maintainScript = true;
        this.fileBase = null;
        this.url = null;
        this.b = null;
        this.b();
    }
    
    public static void parseHtml(final Vector vector) throws Exception {
        Object o = null;
        int id = 0;
        int i = 0;
        while (i < vector.size()) {
            final NFHtmlTag element = vector.elementAt(i);
            if (element instanceof NFHtmlTag) {
                final NFHtmlTag nfHtmlTag = element;
                if (o != null) {
                    if (nfHtmlTag.name.equalsIgnoreCase("/applet")) {
                        vector.removeElementAt(i);
                        vector.insertElementAt(o, i);
                        ++i;
                        o = null;
                    }
                    else {
                        if (nfHtmlTag.name.equalsIgnoreCase("param") && nfHtmlTag.get("name") != null && nfHtmlTag.get("name").toLowerCase().startsWith("chartscript")) {
                            nfHtmlTag.put("value", "\"" + NFParam.doEscapes(nfHtmlTag.get("value")) + "\"");
                        }
                        ((NFCdf)o).a((Object)nfHtmlTag);
                        vector.removeElementAt(i);
                    }
                }
                else {
                    final int a = a(nfHtmlTag);
                    if (a < 0) {
                        ++i;
                    }
                    else {
                        ++id;
                        o = new NFCdf();
                        ((NFCdf)o).type = a;
                        ((NFCdf)o).id = id;
                        ((NFCdf)o).b(nfHtmlTag);
                        vector.removeElementAt(i);
                    }
                }
            }
            else if (o != null) {
                ((NFCdf)o).a((Object)element);
                vector.removeElementAt(i);
            }
            else {
                ++i;
            }
        }
    }
    
    public static void parseCDF(final String s, final Vector vector) throws Exception {
        parseCDF(s, vector, null);
    }
    
    public static void parseCDF(final String s, final Vector vector, final NFContext context) throws Exception {
        URL url;
        if (context != null) {
            url = NFUtil.getFileURL(s, context);
        }
        else {
            url = NFUtil.getFileURL(s);
        }
        InputStream inputStream;
        if (url.getProtocol().equalsIgnoreCase("file") || NFContext.getUserAgentType() == 0) {
            inputStream = url.openStream();
        }
        else {
            final NFHttpClient nfHttpClient = new NFHttpClient();
            if (context != null) {
                nfHttpClient.setContext(context);
            }
            inputStream = nfHttpClient.getContentAsInputStream(url);
        }
        parseCDF(inputStream, vector, context);
        inputStream.close();
    }
    
    public static void parseCDL(final String s, final Vector vector) throws Exception {
        parseCDL(s, vector, null);
    }
    
    public static void parseCDL(final String s, final Vector vector, final NFContext nfContext) throws Exception {
        if (s == null) {
            return;
        }
        final StringBufferInputStream stringBufferInputStream = new StringBufferInputStream(s);
        parseCDF(stringBufferInputStream, vector, nfContext);
        stringBufferInputStream.close();
    }
    
    public static void parseCDF(final InputStream inputStream, final Vector vector) throws Exception {
        parseCDF(inputStream, vector, (NFContext)null);
    }
    
    public static void parseCDF(final InputStream inputStream, final Vector vector, final NFContext nfContext) throws Exception {
        parseCDF(inputStream, vector, null, nfContext);
    }
    
    public static void parseCDF(final InputStream inputStream, final Vector vector, final String s) throws Exception {
        parseCDF(inputStream, vector, s, null, null);
    }
    
    public static void parseCDF(final InputStream inputStream, final Vector vector, final String s, final NFContext nfContext) throws Exception {
        parseCDF(inputStream, vector, s, null, nfContext);
    }
    
    public static void parseCDF(final InputStream inputStream, final Vector vector, final String s, final String s2) throws Exception {
        parseCDF(inputStream, vector, s, s2, null);
    }
    
    public static void parseCDF(final InputStream inputStream, final Vector vector, final String s, final String s2, final NFContext nfContext) throws Exception {
        parseCDF(inputStream, vector, s, s2, nfContext, true);
    }
    
    public static void parseCDF(final InputStream inputStream, final Vector vector, final String s, final String s2, final NFContext nfContext, final boolean b) throws Exception {
        final NFCdf nfCdf = new NFCdf();
        nfCdf.a(inputStream, b);
        int i = 0;
        int size = nfCdf.exprs.size();
        while (i < size) {
            final NFCdfExpr nfCdfExpr = nfCdf.exprs.elementAt(i);
            if (!nfCdfExpr.name.startsWith("ChartScript") && !nfCdfExpr.name.startsWith("ChartURL")) {
                ++i;
            }
            else {
                String s3 = NFToken.stripQuotes(nfCdfExpr.tokens.elementAt(0));
                nfCdf.exprs.removeElementAt(i);
                --size;
                final NFCdf nfCdf2 = new NFCdf();
                if (nfCdfExpr.name.startsWith("ChartScript")) {
                    nfCdf2.url = null;
                    nfCdf2.c(s3);
                }
                else {
                    if (s2 != null && s3.toLowerCase().startsWith("http:")) {
                        s3 += s2;
                    }
                    NFDebug.print("NFCdf: filename = " + s3);
                    if (s == null) {
                        nfCdf2.url = NFUtil.getFileURL(s3, nfContext);
                    }
                    else {
                        nfCdf2.url = NFUtil.getFileURL(s3, s);
                    }
                    nfCdf2.url.getFile().toLowerCase();
                    final String string = nfCdf2.url.toString();
                    final int lastIndex = string.lastIndexOf(NFFile.httpFileSep);
                    if (lastIndex != -1) {
                        nfCdf2.fileBase = string.substring(0, lastIndex + 1);
                    }
                    String substring = s3;
                    if (s3.indexOf(63) != -1) {
                        substring = s3.substring(0, s3.indexOf(63));
                        if (s == null) {
                            nfCdf2.url = NFUtil.getFileURL(substring, nfContext);
                        }
                        else {
                            nfCdf2.url = NFUtil.getFileURL(substring, s);
                        }
                    }
                    if (!NFToken.stripQuotes(substring).endsWith(".cdl")) {
                        nfCdf2.type = -1;
                    }
                    else {
                        nfCdf2.a(s3, s, nfContext);
                    }
                }
                vector.addElement(nfCdf2);
            }
        }
        if (size > 0) {
            vector.addElement(nfCdf);
        }
    }
    
    public void loadParams(final NFLoadParams nfLoadParams, final Vector vector) throws Exception {
        this.script.removeAllElements();
        nfLoadParams.setFileBase(this.fileBase);
        final NFParam param = nfLoadParams.getParam();
        for (int size = this.exprs.size(), i = 0; i < size; ++i) {
            final NFCdfExpr nfCdfExpr = this.exprs.elementAt(i);
            try {
                nfLoadParams.loadParams(nfCdfExpr.name, nfCdfExpr.tokens);
                if (this.maintainScript) {
                    final NFParamDef paramDef = param.getParamDef(nfCdfExpr.name);
                    if (nfCdfExpr.name.compareTo("IncludeFile") != 0 && paramDef.dataBean != null) {
                        this.params.remove(nfCdfExpr.name);
                    }
                    else {
                        final NFKeyValue nfKeyValue = new NFKeyValue();
                        nfKeyValue.key = nfCdfExpr.name;
                        this.script.addElement(nfKeyValue);
                        final Object paramExpr = param.getParamExpr(nfCdfExpr.name);
                        if (paramExpr != null) {
                            nfCdfExpr.value = "EXPRESSION";
                            this.params.put(nfCdfExpr.name, paramExpr);
                            nfKeyValue.value = paramExpr;
                        }
                        else {
                            nfCdfExpr.value = param.toString(nfCdfExpr.name);
                            this.params.put(nfCdfExpr.name, nfCdfExpr.value);
                            nfKeyValue.value = nfCdfExpr.value;
                        }
                    }
                }
            }
            catch (Exception ex) {
                this.params.remove(nfCdfExpr.name);
                if (vector == null) {
                    throw ex;
                }
                vector.addElement(nfCdfExpr.name + ": " + ex.getMessage());
            }
        }
    }
    
    public void printHtml(final PrintWriter printWriter, final boolean b) {
        printWriter.println("<applet\tname=" + this.name);
        printWriter.println("\tcode=" + NFGlobal.classNames[this.type]);
        if (this.appletTag != null) {
            final Enumeration<String> keys = this.appletTag.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (!s.equalsIgnoreCase("name") && !s.equalsIgnoreCase("code") && !s.equalsIgnoreCase("codebase") && !s.equalsIgnoreCase("width")) {
                    if (s.equalsIgnoreCase("height")) {
                        continue;
                    }
                    printWriter.println("\t" + s + "=" + (String)this.appletTag.get(s));
                }
            }
        }
        if (this.codebase != null) {
            printWriter.println("\tcodebase=" + this.codebase);
        }
        else {
            printWriter.println("\tcodebase=\"/classes\"");
        }
        printWriter.print("\twidth=" + this.width + " height=" + this.height + ">");
        if (b) {
            printWriter.println("");
        }
        int n = 0;
        for (int i = 0; i < this.tags.size(); ++i) {
            final Object element = this.tags.elementAt(i);
            if (element instanceof NFHtmlTag) {
                final NFHtmlTag nfHtmlTag = (NFHtmlTag)element;
                final String s2 = nfHtmlTag.get("name");
                if (s2 != null && s2.equalsIgnoreCase("NFParamScript")) {
                    if (n == 0) {
                        this.a(printWriter);
                        n = 1;
                    }
                }
                else {
                    printWriter.print("<" + nfHtmlTag.original + ">");
                }
            }
            else if (element instanceof String) {
                printWriter.print((String)element);
            }
        }
        if (n == 0) {
            this.a(printWriter);
            printWriter.println("");
        }
        printWriter.print("</applet>");
        if (b) {
            printWriter.println("");
        }
    }
    
    public void printCDF(final PrintWriter printWriter) {
        this.printCDF(printWriter, null);
    }
    
    public void printCDF(final PrintWriter printWriter, final String s) {
        final Vector a = this.a();
        int a2 = this.a(a);
        if (a2 < 11) {
            a2 = 11;
        }
        String s2 = "%-" + a2 + "s = %s;";
        if (s != null) {
            s2 = s + s2;
        }
        printWriter.println(NFUtil.sprintf(s2, "ChartName", "\"" + this.name + "\""));
        if (this.type != -1) {
            printWriter.println(NFUtil.sprintf(s2, "ChartType", NFGlobal.classSymbol[this.type]));
        }
        printWriter.println(NFUtil.sprintf(s2, "ChartWidth", this.width + ""));
        printWriter.println(NFUtil.sprintf(s2, "ChartHeight", this.height + ""));
        printWriter.println("");
        this.a(printWriter, s2, a, false);
    }
    
    private void a(final PrintWriter printWriter) {
        final Vector a = this.a();
        final String string = "%-" + this.a(a) + "s = %s;";
        printWriter.println("<param name=NFParamScript value='\n");
        this.a(printWriter, string, a, true);
        printWriter.print("\n'>");
    }
    
    public void printChartScript(final PrintWriter printWriter, final int n) {
        final Vector a = this.a();
        final String string = "%-" + this.a(a) + "s = %s;";
        printWriter.println("<param name=ChartScript" + n + " value='\n");
        this.a(printWriter, string, a, true);
        printWriter.print("\n'>");
    }
    
    public void printMultiChart(final PrintWriter printWriter) {
        printWriter.println("<applet\tname=" + this.name);
        printWriter.println("\tcode=" + NFGlobal.classNames[this.type]);
        if (this.appletTag != null) {
            final Enumeration<String> keys = (Enumeration<String>)this.appletTag.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (!s.equalsIgnoreCase("name") && !s.equalsIgnoreCase("code") && !s.equalsIgnoreCase("codebase") && !s.equalsIgnoreCase("width")) {
                    if (s.equalsIgnoreCase("height")) {
                        continue;
                    }
                    printWriter.println("\t" + s + "=" + (String)this.appletTag.get(s));
                }
            }
        }
        if (this.codebase != null) {
            printWriter.println("\tcodebase=" + this.codebase);
        }
        printWriter.print("\twidth=" + this.width + " height=" + this.height + ">");
        printWriter.println("");
        int n = 0;
        for (int i = 0; i < this.tags.size(); ++i) {
            final Object element = this.tags.elementAt(i);
            if (element instanceof NFHtmlTag) {
                final NFHtmlTag nfHtmlTag = (NFHtmlTag)element;
                final String s2 = nfHtmlTag.get("name");
                if (s2 != null && s2.equalsIgnoreCase("NFParamScript")) {
                    if (n == 0) {
                        this.a(printWriter);
                        n = 1;
                    }
                }
                else {
                    printWriter.print("<" + nfHtmlTag.original + ">");
                }
            }
            else if (element instanceof String) {
                printWriter.print((String)element);
            }
        }
        final Vector a = this.a();
        this.a(printWriter, "<param name=%-" + this.a(a) + "s value='%s'>", a, true);
    }
    
    private Vector a() {
        final int size = this.script.size();
        final Vector<NFKeyValue> vector = new Vector<NFKeyValue>();
        for (int i = 0; i < size; ++i) {
            final NFKeyValue nfKeyValue = this.script.elementAt(i);
            final String s = (String)nfKeyValue.key;
            final Object value = nfKeyValue.value;
            if (!s.startsWith("Chart") || s.equals("Charts")) {
                if (value instanceof NFDataBean) {
                    ((NFDataBean)value).getParams(vector);
                }
                else {
                    vector.addElement(nfKeyValue);
                }
            }
        }
        return vector;
    }
    
    private int a(final Vector vector) {
        int length = 0;
        for (int size = vector.size(), i = 0; i < size; ++i) {
            final String s = (String)vector.elementAt(i).key;
            if (s.length() > length) {
                length = s.length();
            }
        }
        return length;
    }
    
    private void a(final PrintWriter printWriter, final String s, final Vector vector, final boolean b) {
        for (int size = vector.size(), i = 0; i < size; ++i) {
            final NFKeyValue nfKeyValue = vector.elementAt(i);
            final String s2 = (String)nfKeyValue.key;
            String s3 = nfKeyValue.value.toString();
            if (b) {
                int j = s3.indexOf("\\'");
                StringBuffer sb = null;
                while (j >= 0) {
                    if (sb == null) {
                        sb = new StringBuffer();
                    }
                    else {
                        sb.setLength(0);
                    }
                    sb.append(s3.substring(0, j));
                    sb.append("&#039;");
                    sb.append(s3.substring(j + 2));
                    s3 = sb.toString();
                    j = s3.indexOf("\\'");
                }
            }
            printWriter.println(NFUtil.sprintf(s, s2, s3));
        }
    }
    
    private static int a(final NFHtmlTag nfHtmlTag) {
        if (!nfHtmlTag.name.equalsIgnoreCase("applet")) {
            return -1;
        }
        final String s = nfHtmlTag.get("code");
        if (s == null || s.length() == 0) {
            return -1;
        }
        for (int i = 0; i < NFGlobal.classNames.length; ++i) {
            final String s2 = NFGlobal.classNames[i];
            if (s.equalsIgnoreCase(s2)) {
                return i;
            }
            if (s.equalsIgnoreCase("netcharts.apps." + s2)) {
                return i;
            }
        }
        return -1;
    }
    
    private static int a(final String s) {
        for (int i = 0; i < NFGlobal.classSymbol.length; ++i) {
            if (s.equalsIgnoreCase(NFGlobal.classSymbol[i])) {
                return i;
            }
        }
        return -1;
    }
    
    private static Dimension b(final Vector vector) {
        final Dimension dimension = new Dimension(400, 250);
        try {
            dimension.width = Integer.parseInt(vector.elementAt(1));
        }
        catch (Exception ex) {}
        try {
            dimension.height = Integer.parseInt(vector.elementAt(3));
        }
        catch (Exception ex2) {}
        return dimension;
    }
    
    private void b(final NFHtmlTag appletTag) {
        this.appletTag = appletTag;
        String string = this.appletTag.get("name");
        if (string == null || string.length() == 0) {
            string = "Chart" + this.id;
        }
        this.name = string;
        String s = this.appletTag.get("width");
        if (s == null || s.length() == 0) {
            s = "400";
        }
        this.width = Integer.parseInt(s);
        String s2 = this.appletTag.get("height");
        if (s2 == null || s2.length() == 0) {
            s2 = "250";
        }
        this.height = Integer.parseInt(s2);
        final String codebase = this.appletTag.get("codebase");
        if (codebase != null && codebase.length() > 0) {
            this.codebase = codebase;
        }
    }
    
    private void a(final Object o) throws Exception {
        this.tags.addElement(o);
        if (!(o instanceof NFHtmlTag)) {
            return;
        }
        final NFHtmlTag nfHtmlTag = (NFHtmlTag)o;
        if (!nfHtmlTag.name.equalsIgnoreCase("param")) {
            return;
        }
        final String s = nfHtmlTag.get("name");
        if (s == null || s.length() == 0) {
            return;
        }
        final String s2 = nfHtmlTag.get("value");
        if (s2 == null || s2.length() == 0) {
            return;
        }
        if (s.equalsIgnoreCase("NFParamScript")) {
            this.c(s2);
            return;
        }
        if (s.equalsIgnoreCase("NFParamURL")) {
            this.b(s2);
            return;
        }
        this.c(s + "=" + s2);
    }
    
    private void b(final String s) throws Exception {
        this.a(s, null);
    }
    
    private void a(final String s, final String s2) throws Exception {
        this.a(s, s2, null);
    }
    
    private void a(final String s, final String s2, final NFContext context) throws Exception {
        try {
            URL url;
            if (s2 == null) {
                url = NFUtil.getFileURL(s, context);
            }
            else {
                url = NFUtil.getFileURL(s, s2);
            }
            InputStream inputStream;
            if (url.getProtocol().equalsIgnoreCase("file") || NFContext.getUserAgentType() == 0) {
                inputStream = url.openStream();
            }
            else {
                final NFHttpClient nfHttpClient = new NFHttpClient();
                if (context != null) {
                    nfHttpClient.setContext(context);
                }
                inputStream = nfHttpClient.getContentAsInputStream(url);
            }
            this.a(inputStream);
            inputStream.close();
        }
        catch (Exception ex) {
            throw new Exception("Unable to open " + s);
        }
    }
    
    private void b() {
        if (this.b != null) {
            return;
        }
        this.b = new NFToken();
    }
    
    private void c(final String input) throws Exception {
        this.b.setInput(input);
        this.c();
    }
    
    private void a(final InputStream inputStream) throws Exception {
        this.a(inputStream, true);
    }
    
    private void a(final BufferedInputStream bufferedInputStream) throws Exception {
        this.a(bufferedInputStream, false);
    }
    
    private void a(final InputStream inputStream, final boolean b) throws Exception {
        InputStream inputStream2;
        if (b) {
            inputStream2 = new BufferedInputStream(inputStream);
        }
        else {
            inputStream2 = inputStream;
        }
        this.b.setInput(inputStream2, false);
        this.c();
    }
    
    private void c() throws Exception {
        while (true) {
            final NFKeyValue nextStatement = this.b.nextStatement();
            if (nextStatement == null) {
                break;
            }
            final String name = (String)nextStatement.key;
            final Vector tokens = (Vector)nextStatement.value;
            if (name.startsWith("Chart") && tokens.size() > 0) {
                final String stripQuotes = NFToken.stripQuotes(tokens.elementAt(0));
                if (name.equals("ChartName")) {
                    this.name = stripQuotes;
                    continue;
                }
                if (name.equals("ChartType")) {
                    final int a = a(stripQuotes);
                    if (a != -1) {
                        this.type = a;
                        continue;
                    }
                    continue;
                }
                else {
                    if (name.equals("ChartWidth")) {
                        this.width = Integer.parseInt(stripQuotes);
                        continue;
                    }
                    if (name.equals("ChartHeight")) {
                        this.height = Integer.parseInt(stripQuotes);
                        continue;
                    }
                    if (name.equals("ChartSize")) {
                        final Dimension b = b(tokens);
                        this.width = b.width;
                        this.height = b.height;
                        continue;
                    }
                }
            }
            final NFCdfExpr nfCdfExpr = new NFCdfExpr();
            nfCdfExpr.name = name;
            nfCdfExpr.value = null;
            nfCdfExpr.tokens = tokens;
            this.exprs.addElement(nfCdfExpr);
        }
    }
}
