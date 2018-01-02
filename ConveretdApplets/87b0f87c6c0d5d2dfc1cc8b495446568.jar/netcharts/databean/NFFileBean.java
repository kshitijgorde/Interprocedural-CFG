// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.databean;

import netcharts.util.NFDebug;
import java.io.InputStream;
import netcharts.util.NFFile;
import netcharts.util.NFHttpClient;
import netcharts.util.NFContext;
import netcharts.util.NFUtil;
import netcharts.util.NFDataBeanObserver;
import netcharts.util.NFKeyValue;
import netcharts.util.NFParamDef;
import java.util.Vector;
import netcharts.util.NFParam;
import java.util.Date;
import java.net.URL;
import netcharts.util.NFToken;
import netcharts.util.NFDataBean;

public class NFFileBean extends NFDataBean
{
    public String startDelim;
    public String itemDelim;
    public String endDelim;
    public String strings;
    public String whitespace;
    public String comments;
    public String filename;
    private final int a = 1;
    private final int b = 2;
    private final int c = 3;
    private final int d = 4;
    private NFToken e;
    private int[] f;
    private StringBuffer g;
    private URL h;
    private Date i;
    private StringBuffer j;
    
    public NFFileBean() {
        this.startDelim = "(";
        this.itemDelim = ",";
        this.endDelim = ")\n\r";
        this.strings = "'\"";
        this.whitespace = " \t";
        this.comments = "#";
        this.filename = "";
        this.e = null;
        this.f = null;
        this.g = new StringBuffer();
        this.h = null;
        this.i = null;
        this.j = new StringBuffer();
        super.name = "NFFileBean";
        super.desc = "Delimited File Processing";
        super.prefix = "File";
        super.keyword = "FILE";
        super.debugMode |= 0x40L;
    }
    
    public void defineParams(final NFParam nfParam) {
        if (!nfParam.exists("FileFormat")) {
            final Vector<NFParamDef> vector = new Vector<NFParamDef>();
            vector.addElement(nfParam.defineString("FileStartDelim", null));
            vector.addElement(nfParam.defineString("FileItemDelim", null));
            vector.addElement(nfParam.defineString("FileEndDelim", null));
            vector.addElement(nfParam.defineString("FileWhiteSpace", null));
            vector.addElement(nfParam.defineString("FileStrings", null));
            vector.addElement(nfParam.defineString("FileComments", null));
            nfParam.defineTuple("FileFormat", vector).dataBean = this;
        }
        if (!nfParam.exists("FILE")) {
            nfParam.defineString("FILE", null).dataBean = this;
        }
    }
    
    public void loadParams(final NFParam nfParam) throws Exception {
        final Vector vector = (Vector)nfParam.get("FileFormat");
        if (vector != null && vector.size() > 0) {
            this.startDelim = this.a("startDelim", this.startDelim, vector, 0);
            this.itemDelim = this.a("itemDelim", this.itemDelim, vector, 1);
            this.endDelim = this.a("endDelim", this.endDelim, vector, 2);
            this.whitespace = this.a("whitespace", this.whitespace, vector, 3);
            this.strings = this.a("strings", this.strings, vector, 4);
            this.comments = this.a("comments", this.comments, vector, 5);
        }
        this.filename = (String)nfParam.get("FILE");
    }
    
    private String a(final String s, final String s2, final Vector vector, final int n) {
        final String s3 = vector.elementAt(n);
        if (s3 == null || s3.equalsIgnoreCase("null")) {
            return s2;
        }
        if (this.inDebugMode()) {
            this.debug(s + " = <" + (Object)NFToken.doEscapes(s3, null, false) + ">");
        }
        return s3;
    }
    
    public StringBuffer getParam(final String s, StringBuffer j) {
        if (j == null) {
            j = this.j;
            j.setLength(0);
        }
        if (s.equals("FileFormat")) {
            j.append("(\"");
            NFToken.doEscapes(this.startDelim, j, false);
            j.append("\",\"");
            NFToken.doEscapes(this.itemDelim, j, false);
            j.append("\",\"");
            NFToken.doEscapes(this.endDelim, j, false);
            j.append("\",\"");
            NFToken.doEscapes(this.whitespace, j, false);
            j.append("\",\"");
            NFToken.doEscapes(this.strings, j, false);
            j.append("\",\"");
            NFToken.doEscapes(this.comments, j, false);
            j.append("\")");
            return j;
        }
        if (super.exprParam != null && s.equals(super.exprParam)) {
            j.append("FILE \"");
            NFToken.doEscapes(this.filename, j, false);
            j.append("\"");
            return j;
        }
        j.append("ERROR");
        return j;
    }
    
    public Vector getParams(Vector vector) {
        if (vector == null) {
            vector = new Vector<NFKeyValue>();
        }
        final NFKeyValue nfKeyValue = new NFKeyValue();
        nfKeyValue.key = "FileFormat";
        nfKeyValue.value = this.getParam("FileFormat", new StringBuffer());
        vector.addElement(nfKeyValue);
        if (super.exprParam != null && this.filename.length() > 0) {
            final NFKeyValue nfKeyValue2 = new NFKeyValue();
            nfKeyValue2.key = super.exprParam;
            nfKeyValue2.value = this.getParam(super.exprParam, new StringBuffer());
            vector.addElement(nfKeyValue2);
        }
        return vector;
    }
    
    public boolean loadData(final NFDataBeanObserver nfDataBeanObserver, final Object o) throws Exception {
        int n = -1;
        int n2 = -1;
        try {
            this.h = NFUtil.getFileURL(NFUtil.resolvePath(this.filename, super.ctxt), super.ctxt);
            if (this.inDebugMode()) {
                this.debug("+++++++++++++++++++++++");
                this.debug("Parameter = " + super.exprParam);
                this.debug("filename  = " + this.filename);
                this.debug("File URL  = " + this.h);
            }
            NFHttpClient nfHttpClient = null;
            InputStream input;
            try {
                if (this.h.getProtocol().equalsIgnoreCase("file") || this.h.getProtocol().equalsIgnoreCase("zip") || NFContext.getUserAgentType() == 0) {
                    input = this.h.openStream();
                }
                else {
                    nfHttpClient = new NFHttpClient(super.ctxt);
                    input = nfHttpClient.getContentAsInputStream(this.h);
                }
            }
            catch (Exception ex2) {
                throw new Exception("Unable to access " + this.h);
            }
            if (this.e == null) {
                this.e = new NFToken();
            }
            if (this.f == null) {
                this.f = new int[256];
                for (int i = 0; i < 255; ++i) {
                    this.f[i] = 1;
                }
            }
            this.e.setInput(input);
            this.e.returnQuotes = false;
            this.e.setCharRange(2, 0, 255);
            if (this.startDelim != null) {
                for (int j = 0; j < this.startDelim.length(); ++j) {
                    final char char1 = this.startDelim.charAt(j);
                    this.e.setCharType(3, char1, 0);
                    if (char1 < '\u0100') {
                        this.f[char1] = 2;
                    }
                }
            }
            if (this.itemDelim != null) {
                for (int k = 0; k < this.itemDelim.length(); ++k) {
                    final char char2 = this.itemDelim.charAt(k);
                    this.e.setCharType(3, char2, 0);
                    if (char2 < '\u0100') {
                        this.f[char2] = 3;
                    }
                }
            }
            if (this.endDelim != null) {
                for (int l = 0; l < this.endDelim.length(); ++l) {
                    final char char3 = this.endDelim.charAt(l);
                    this.e.setCharType(3, char3, 0);
                    if (char3 < '\u0100') {
                        this.f[char3] = 4;
                    }
                }
            }
            if (this.strings != null) {
                for (int n3 = 0; n3 < this.strings.length(); ++n3) {
                    final char char4 = this.strings.charAt(n3);
                    this.e.setCharType(1, char4, char4);
                }
            }
            if (this.whitespace != null) {
                for (int n4 = 0; n4 < this.whitespace.length(); ++n4) {
                    this.e.setCharType(4, this.whitespace.charAt(n4), 0);
                }
            }
            if (this.comments != null) {
                for (int n5 = 0; n5 < this.comments.length(); ++n5) {
                    this.e.setCharType(6, this.comments.charAt(n5), 0);
                }
            }
            final StringBuffer sb = new StringBuffer();
            int n6 = 0;
            n = 0;
            n2 = 0;
            final Vector<Vector<Object>> vector = new Vector<Vector<Object>>();
            Vector<Object> vector2 = new Vector<Object>();
            while (true) {
                sb.setLength(0);
                final StringBuffer nextToken = this.e.nextToken(sb);
                if (nextToken == null) {
                    if (vector2.size() > 0) {
                        if (this.inDebugMode()) {
                            this.a(n + 1, vector2);
                        }
                        n2 = -1;
                        ++n;
                        vector.addElement(vector2);
                    }
                    this.e.close();
                    nfDataBeanObserver.dataBeanLoadData(this, o, super.exprParam, vector);
                    if (nfHttpClient == null) {
                        this.i = NFFile.getLastModified(this.h);
                    }
                    else {
                        this.i = nfHttpClient.getLastModifiedAsDate();
                    }
                    if (this.i == null) {
                        this.i = new Date(System.currentTimeMillis());
                    }
                    if (this.inDebugMode()) {
                        this.debug("Processed " + n + " Item(s)");
                        this.debug("-----------------------");
                    }
                    return false;
                }
                final String string = nextToken.toString();
                switch (this.a(nextToken)) {
                    case 3: {
                        if (n6 != 0) {
                            n6 = 0;
                            continue;
                        }
                        vector2.addElement(null);
                        ++n2;
                        continue;
                    }
                    case 1: {
                        if (n6 != 0) {
                            throw new Exception("Expected Delimiter, Found <" + string + ">");
                        }
                        vector2.addElement(string);
                        ++n2;
                        n6 = 1;
                        continue;
                    }
                    case 2:
                    case 4: {
                        if (vector2.size() > 0) {
                            if (this.inDebugMode()) {
                                this.a(n + 1, vector2);
                            }
                            n2 = -1;
                            ++n;
                            vector.addElement(vector2);
                            vector2 = new Vector<Object>();
                        }
                        n6 = 0;
                        continue;
                    }
                }
            }
        }
        catch (Exception ex) {
            String s = ex.getMessage();
            if (s == null || s.length() < 1) {
                s = ex.toString();
            }
            if (n > -1) {
                if (n2 > -1) {
                    s = "Attr(" + (n2 + 1) + "): " + s;
                }
                s = "Item(" + (n + 1) + "): " + s;
            }
            final String string2 = super.exprParam + ": FILE: " + s;
            this.debug("ERROR: " + string2);
            throw new Exception(string2);
        }
    }
    
    private int a(final StringBuffer sb) {
        if (sb.length() != 1) {
            return 1;
        }
        final char char1 = sb.charAt(0);
        if (char1 > '\u00ff') {
            return 1;
        }
        return this.f[char1];
    }
    
    private synchronized void a(final int n, final Vector vector) {
        char char1 = ',';
        if (this.itemDelim != null && this.itemDelim.length() > 0) {
            char1 = this.itemDelim.charAt(0);
        }
        this.g.setLength(0);
        this.g.append("NFFileBean: Item(" + n + "): ");
        for (int i = 0; i < vector.size(); ++i) {
            if (i > 0) {
                this.g.append(char1);
            }
            final String s = vector.elementAt(i);
            if (s != null) {
                NFToken.doEscapes(s, this.g, false);
            }
        }
        NFDebug.print(this.g.toString());
    }
    
    public boolean reloadNeeded(Date i) {
        if (i == null) {
            i = this.i;
        }
        return NFFile.modifiedSince(this.h, i);
    }
    
    public void toString(final StringBuffer sb) {
        super.toString(sb);
        sb.append(" = ");
        sb.append(this.filename);
    }
}
