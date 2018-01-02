// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.net.URL;
import java.util.Date;
import java.awt.Color;
import java.util.Enumeration;
import java.util.Observer;
import java.awt.Component;
import java.util.Vector;
import java.util.Hashtable;
import java.applet.Applet;
import java.util.Observable;

public class NFParam extends Observable
{
    public static final int PARAM = 0;
    public static final int NUMBER = 1;
    public static final int STRING = 2;
    public static final int SYMBOL = 3;
    public static final int COLOR = 4;
    public static final int TUPLE = 5;
    public static final int VECTOR = 6;
    public static final int IMAGE = 7;
    public static final int DATE = 8;
    static final boolean a = false;
    private Applet b;
    private Hashtable c;
    private Vector d;
    private Component e;
    private static StringBuffer f;
    private StringBuffer g;
    
    public NFParam() {
        this.g = new StringBuffer();
        this.b = null;
        this.c = new Hashtable();
    }
    
    public NFParam(final Applet applet) {
        this(applet, applet);
    }
    
    public NFParam(final Applet b, final Component e) {
        this.g = new StringBuffer();
        this.b = b;
        this.e = e;
        this.c = new Hashtable();
    }
    
    public void setComponent(final Component e) {
        this.e = e;
    }
    
    public Component getComponent() {
        return this.e;
    }
    
    public void setApp(final Applet b) {
        this.b = b;
    }
    
    public Applet getApp() {
        return this.b;
    }
    
    public void addObserver(final Observer observer) {
        super.addObserver(observer);
    }
    
    public NFParamDef getParamDef(final String s) throws NFParamException {
        final NFParamDef nfParamDef = this.c.get(s);
        if (nfParamDef == null) {
            throw new NFParamException("Undefined Parameter <" + s + ">");
        }
        return nfParamDef;
    }
    
    public Object getParamExpr(final String s) throws NFParamException {
        return this.getParamDef(s).expr;
    }
    
    public boolean exists(final String s) {
        try {
            this.getParamDef(s);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public boolean changed(final String s) {
        NFParamDef paramDef;
        try {
            paramDef = this.getParamDef(s);
        }
        catch (Exception ex) {
            return false;
        }
        return paramDef.changed;
    }
    
    public void setChanged(final String s) {
        NFParamDef paramDef;
        try {
            paramDef = this.getParamDef(s);
        }
        catch (Exception ex) {
            return;
        }
        paramDef.changed = true;
    }
    
    public Object get(final String s) throws NFParamException {
        final NFParamDef paramDef = this.getParamDef(s);
        paramDef.changed = false;
        return this.getValue(paramDef);
    }
    
    public void remove(final String s) {
        this.c.remove(s);
        if (this.d != null && this.d.contains(s)) {
            this.d.removeElement(s);
        }
    }
    
    public Enumeration getKeys() {
        return this.c.keys();
    }
    
    public Vector getPrimaryKeys(Vector vector) {
        if (vector == null) {
            vector = new Vector<String>();
        }
        final Enumeration<String> keys = this.c.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            try {
                if (this.getParamDef(s).parent != null) {
                    continue;
                }
                vector.addElement(s);
            }
            catch (Exception ex) {}
        }
        return vector;
    }
    
    public Vector getChildKeys(final String s) throws NFParamException {
        final Vector<String> vector = new Vector<String>();
        final NFParamDef paramDef = this.getParamDef(s);
        if (paramDef.type != 5 && paramDef.type != 6) {
            throw new NFParamException(s + " is not a TUPLE or VECTOR");
        }
        if (paramDef.type == 5) {
            for (int size = paramDef.tuple_def.size(), i = 0; i < size; ++i) {
                vector.addElement(((NFParamDef)paramDef.tuple_def.elementAt(i)).param);
            }
        }
        else {
            vector.addElement(paramDef.vector_def.param);
        }
        return vector;
    }
    
    public Color getColor(final String s) {
        return NFColor.get(s.toLowerCase());
    }
    
    public int getType(final String s) throws NFParamException {
        return this.getParamDef(s).type;
    }
    
    public String getTypeString(final String s) throws NFParamException {
        return this.getTypeString(this.getParamDef(s).type);
    }
    
    public String getTypeString(final int n) {
        switch (n) {
            case 2: {
                return "STRING";
            }
            case 1: {
                return "NUMBER";
            }
            case 3: {
                return "SYMBOL";
            }
            case 4: {
                return "COLOR ";
            }
            case 7: {
                return "IMAGE ";
            }
            case 8: {
                return "DATE  ";
            }
            case 5: {
                return "TUPLE ";
            }
            case 6: {
                return "VECTOR";
            }
            default: {
                return "ERROR ";
            }
        }
    }
    
    public Enumeration getSymbolKeys(final String s) throws NFParamException {
        final NFParamDef paramDef = this.getParamDef(s);
        if (paramDef.type != 3) {
            throw new NFParamException(s + " is not a SYMBOL");
        }
        return paramDef.symtable.keys();
    }
    
    public Object valueToKey(final String s, final Object o) throws NFParamException {
        final Hashtable<Object, String> hashtable = new Hashtable<Object, String>();
        final NFParamDef paramDef = this.getParamDef(s);
        final Enumeration keys = paramDef.symtable.keys();
        while (keys.hasMoreElements()) {
            final String nextElement = keys.nextElement();
            hashtable.put(paramDef.symtable.get(nextElement), nextElement);
        }
        return hashtable.get(o);
    }
    
    public String toString(final String s, final Object o) throws NFParamException {
        return this.toString(this.getParamDef(s), o);
    }
    
    public String toString(final String s) throws NFParamException {
        final NFParamDef paramDef = this.getParamDef(s);
        return this.toString(paramDef, this.getValue(paramDef));
    }
    
    public String toString(final NFParamDef nfParamDef, final Object o) {
        switch (nfParamDef.type) {
            case 2: {
                if (o == null) {
                    return "\"\"";
                }
                return "\"" + doEscapes(o.toString()) + "\"";
            }
            case 1: {
                if (o == null) {
                    return "null";
                }
                if (o instanceof Double && ((Double)o).isNaN()) {
                    return "null";
                }
                String s = o.toString();
                if (s.endsWith(".0")) {
                    s = s.substring(0, s.length() - 2);
                }
                return s;
            }
            case 4: {
                if (o == null) {
                    return "null";
                }
                return NFColor.toString((Color)o);
            }
            case 3: {
                if (o == null || nfParamDef.symtable == null) {
                    return "null";
                }
                final Enumeration<String> keys = nfParamDef.symtable.keys();
                while (keys.hasMoreElements()) {
                    final String s2 = keys.nextElement();
                    if (nfParamDef.symtable.get(s2).equals(o)) {
                        return s2;
                    }
                }
                return "null";
            }
            case 5: {
                if (o == null) {
                    return "()";
                }
                final Vector vector = (Vector)o;
                if (nfParamDef.tuple_def == null || vector == null) {
                    return "()";
                }
                if (vector.size() < nfParamDef.tuple_def.size()) {
                    return "()";
                }
                final StringBuffer sb = new StringBuffer();
                sb.append('(');
                for (int i = 0; i < vector.size(); ++i) {
                    int n = i;
                    if (n >= nfParamDef.tuple_def.size()) {
                        n = nfParamDef.tuple_def.size() - 1;
                    }
                    final NFParamDef nfParamDef2 = nfParamDef.tuple_def.elementAt(n);
                    if (i > 0) {
                        sb.append(',');
                    }
                    sb.append(this.toString(nfParamDef2, vector.elementAt(i)));
                }
                sb.append(')');
                return sb.toString();
            }
            case 6: {
                if (o == null) {
                    return "";
                }
                final Vector vector2 = (Vector)o;
                if (nfParamDef.vector_def == null || vector2 == null) {
                    return "";
                }
                if (vector2.size() < 1) {
                    return "";
                }
                final StringBuffer sb2 = new StringBuffer();
                int n2 = 0;
                for (int j = 0; j < vector2.size(); ++j) {
                    if (j > 0) {
                        sb2.append(',');
                        ++n2;
                    }
                    final String string = this.toString(nfParamDef.vector_def, vector2.elementAt(j));
                    if (n2 + string.length() + 1 > 72) {
                        sb2.append("\n\t");
                        n2 = 0;
                    }
                    sb2.append(string);
                    n2 += string.length();
                }
                return sb2.toString();
            }
            case 7: {
                if (o == null) {
                    return "null";
                }
                String s3;
                if (o instanceof NFParamImage) {
                    s3 = ((NFParamImage)o).filename;
                }
                else {
                    s3 = nfParamDef.imageLabel;
                }
                if (s3 == null || s3.length() < 1 || s3.equals("null")) {
                    return "null";
                }
                return "\"" + s3 + "\"";
            }
            case 8: {
                if (o == null) {
                    return "null";
                }
                if (o instanceof Double && ((Double)o).isNaN()) {
                    return "null";
                }
                String s4 = o.toString();
                if (o instanceof NFDate || o instanceof NFTimeUnit) {
                    s4 = "\"" + s4 + "\"";
                }
                else if (s4.endsWith(".0")) {
                    s4 = s4.substring(0, s4.length() - 2);
                }
                return s4;
            }
            default: {
                return "null";
            }
        }
    }
    
    public static synchronized String doEscapes(final String s) {
        NFParam.f.setLength(0);
        doEscapes(s, NFParam.f);
        return NFParam.f.toString();
    }
    
    public static void doEscapes(final String s, final StringBuffer sb) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 13: {
                    sb.append("\\r");
                    break;
                }
                case 10: {
                    sb.append("\\n");
                    break;
                }
                case 9: {
                    sb.append("\\t");
                    break;
                }
                case 34: {
                    sb.append("\\\"");
                    break;
                }
                case 39: {
                    sb.append("\\'");
                    break;
                }
                case 123: {
                    sb.append("\\{");
                    break;
                }
                case 125: {
                    sb.append("\\}");
                    break;
                }
                default: {
                    sb.append(char1);
                    break;
                }
            }
        }
    }
    
    public static String undoEscapes(final String s) {
        NFParam.f.setLength(0);
        undoEscapes(s, NFParam.f);
        return NFParam.f.toString();
    }
    
    public static void undoEscapes(final String s, final StringBuffer sb) {
        int n = 0;
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (n != 0) {
                switch (char1) {
                    case 110: {
                        sb.append('\n');
                        break;
                    }
                    case 114: {
                        sb.append('\r');
                        break;
                    }
                    case 116: {
                        sb.append('\t');
                        break;
                    }
                    case 123: {
                        sb.append('{');
                        break;
                    }
                    case 125: {
                        sb.append('}');
                        break;
                    }
                    case 34: {
                        sb.append('\"');
                        break;
                    }
                    case 39: {
                        sb.append('\'');
                        break;
                    }
                    case 92: {
                        sb.append('\\');
                        break;
                    }
                    default: {
                        sb.append('\\');
                        sb.append(char1);
                        break;
                    }
                }
                n = 0;
            }
            else if (char1 == '\\') {
                n = 1;
            }
            else {
                sb.append(char1);
            }
        }
    }
    
    public Object getValue(final NFParamDef nfParamDef) {
        return this.getValue(nfParamDef, false);
    }
    
    public Object getValue(final NFParamDef nfParamDef, final boolean b) {
        switch (nfParamDef.type) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 7:
            case 8: {
                return nfParamDef.val;
            }
            case 5: {
                if (b) {
                    return nfParamDef.tuple_tmp;
                }
                return nfParamDef.tuple_val;
            }
            case 6: {
                if (b) {
                    return nfParamDef.vector_tmp;
                }
                return nfParamDef.vector_val;
            }
            default: {
                return null;
            }
        }
    }
    
    public Object cloneValue(final NFParamDef nfParamDef) {
        return this.cloneValue(nfParamDef, this.getValue(nfParamDef));
    }
    
    public Object cloneValue(NFParamDef nfParamDef, Object o) {
        if (o == null) {
            return null;
        }
        switch (nfParamDef.type) {
            case 2: {
                return new String((String)o);
            }
            case 1: {
                return new Double(((Number)o).doubleValue());
            }
            case 3: {
                return new Integer(((Number)o).intValue());
            }
            case 4: {
                return new Color(((Color)o).getRGB());
            }
            case 7: {
                if (o instanceof NFParamImage) {
                    return new String(((NFParamImage)o).filename);
                }
                return new String(o.toString());
            }
            case 8: {
                if (o instanceof NFDate) {
                    return new NFDate(((Date)o).getTime());
                }
                if (o instanceof NFTimeUnit) {
                    return new NFTimeUnit(((NFTimeUnit)o).toString());
                }
                if (o instanceof Number) {
                    return new Double(((Number)o).doubleValue());
                }
                return null;
            }
            case 5: {
                final Vector vector = (Vector)o;
                final Vector<Object> vector2 = new Vector<Object>();
                final Vector tuple_def = nfParamDef.tuple_def;
                for (int n = 0; n < tuple_def.size() && n < vector.size(); ++n) {
                    o = vector.elementAt(n);
                    nfParamDef = tuple_def.elementAt(n);
                    vector2.addElement(this.cloneValue(nfParamDef, o));
                }
                return vector2;
            }
            case 6: {
                final Vector vector3 = (Vector)o;
                final Vector<Object> vector4 = new Vector<Object>();
                for (int i = 0; i < vector3.size(); ++i) {
                    o = vector3.elementAt(i);
                    vector4.addElement(this.cloneValue(nfParamDef.vector_def, o));
                }
                return vector4;
            }
            default: {
                return null;
            }
        }
    }
    
    public void setValue(final NFParamDef nfParamDef, final Object o) {
        this.setValue(nfParamDef, o, false);
    }
    
    public void setValue(final NFParamDef nfParamDef, final Object val, final boolean b) {
        switch (nfParamDef.type) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 7:
            case 8: {
                nfParamDef.val = val;
                nfParamDef.changed = true;
                break;
            }
            case 5: {
                if (b) {
                    if (nfParamDef.tuple_tmp == null) {
                        nfParamDef.tuple_tmp = new Vector();
                    }
                    nfParamDef.tuple_tmp.addElement(val);
                    break;
                }
                nfParamDef.tuple_val.addElement(val);
                break;
            }
            case 6: {
                if (b) {
                    if (nfParamDef.vector_tmp == null) {
                        nfParamDef.vector_tmp = new Vector();
                    }
                    nfParamDef.vector_tmp.addElement(val);
                    break;
                }
                nfParamDef.vector_val.addElement(val);
                break;
            }
        }
        nfParamDef.loaded = true;
    }
    
    private static void a(final String s, final String s2) throws NFParamException {
        throw new NFParamException(s + ": " + s2);
    }
    
    private synchronized void a(final String s) {
        final int length = s.length();
        this.g.setLength(0);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 59: {
                    this.g.append(';');
                }
                case 10: {
                    NFDebug.print("NFParam: " + this.g.toString());
                    this.g.setLength(0);
                    break;
                }
                default: {
                    this.g.append(char1);
                    break;
                }
            }
        }
        if (this.g.length() > 0) {
            NFDebug.print("NFParam: " + this.g.toString());
        }
    }
    
    public void update() {
        this.update("LoadParams");
    }
    
    public void update(final String s) {
        this.setChanged();
        this.notifyObservers(s);
        this.clearChanged();
    }
    
    public static final URL getFileURL(final String s) throws Exception {
        return NFUtil.getFileURL(s);
    }
    
    public URL stringToURL(final String s) {
        if (s == null || s.length() < 1) {
            return null;
        }
        URL fileURL;
        try {
            fileURL = new URL(this.b.getDocumentBase(), s);
        }
        catch (Exception ex2) {
            try {
                fileURL = NFUtil.getFileURL(s);
            }
            catch (Exception ex) {
                NFDebug.print("NetCharts: Invalid URL: " + s + " - " + ex.getMessage());
                fileURL = null;
            }
        }
        return fileURL;
    }
    
    public static void printError(final Exception ex) {
        if (ex instanceof NFParamException) {
            printError(ex.getMessage());
        }
        else {
            ex.printStackTrace();
        }
    }
    
    public static void printError(final String s) {
        NFDebug.print("NetCharts: ******************************");
        NFDebug.print("NetCharts: " + s);
        NFDebug.print("NetCharts: ******************************");
    }
    
    public NFParamDef defineLabel(final String s) {
        return this.defineLabel(s, true);
    }
    
    public NFParamDef defineLabel(final String s, final boolean b) {
        return this.defineLabel(s, b, false);
    }
    
    public NFParamDef defineLabel(final String s, final boolean b, final boolean b2) {
        final Vector vector = new Vector();
        this.defineLabel(s, vector, b, b2);
        return this.defineTuple(s, vector);
    }
    
    public int defineLabel(final String s, final Vector vector) {
        return this.defineLabel(s, vector, true);
    }
    
    public int defineLabel(final String s, final Vector vector, final boolean b) {
        return this.defineLabel(s, vector, b, false);
    }
    
    public int defineLabel(final String s, final Vector vector, final boolean b, final boolean b2) {
        vector.addElement(this.defineString(s + "Label", ""));
        vector.addElement(this.defineColor(s + "Color", Color.white));
        vector.addElement(this.defineString(s + "Font", "TimesRoman"));
        vector.addElement(this.defineNumber(s + "FontWidth", new Integer(16)));
        vector.addElement(this.defineNumber(s + "Angle", new Integer(0)));
        if (b) {
            final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
            hashtable.put("RIGHT", new Integer(0));
            hashtable.put("LEFT", new Integer(1));
            hashtable.put("CENTER", new Integer(4));
            hashtable.put("null", new Integer(-1));
            vector.addElement(this.defineSymbol(s + "Justify", hashtable, new Integer(-1)));
        }
        if (b2) {
            final Hashtable<String, Integer> hashtable2 = new Hashtable<String, Integer>();
            hashtable2.put("RIGHT", new Integer(0));
            hashtable2.put("LEFT", new Integer(1));
            hashtable2.put("CENTER", new Integer(4));
            hashtable2.put("BOTTOM", new Integer(3));
            hashtable2.put("TOP", new Integer(2));
            vector.addElement(this.defineSymbol(s + "EJustify", hashtable2, new Integer(4)));
        }
        return labelParamCount(b, b2);
    }
    
    public static int labelParamCount() {
        return labelParamCount(true, false);
    }
    
    public static int labelParamCount(final boolean b, final boolean b2) {
        int n = 5;
        if (b) {
            ++n;
        }
        if (b2) {
            ++n;
        }
        return n;
    }
    
    public NFParamDef defineRegion(final String s) {
        final Vector vector = new Vector();
        this.defineRegion(s, vector);
        return this.defineTuple(s, vector);
    }
    
    public int defineRegion(final String s, final Vector vector) {
        vector.addElement(this.defineColor(s + "Color", null));
        vector.addElement(this.defineBorderType(s + "Type", 0));
        vector.addElement(this.defineNumber(s + "Width", new Integer(4)));
        vector.addElement(this.defineImage(s + "ImageURL"));
        vector.addElement(this.defineImageType(s + "ImageType", 0));
        vector.addElement(this.defineColor(s + "BorderColor", Color.black));
        return 6;
    }
    
    public void defineActiveLabel(final String s) {
        final Vector vector = new Vector();
        this.defineActiveLabel(s, vector);
        this.defineVector(s, this.defineTuple(s + "Tuple", vector));
    }
    
    public int defineActiveLabel(final String s, final Vector vector) {
        vector.addElement(this.defineString(s + "Label", ""));
        vector.addElement(this.defineString(s + "URL", ""));
        vector.addElement(this.defineString(s + "Target", ""));
        return 3;
    }
    
    public void defineLicenseParams() {
        this.defineString("LicenseURL", null);
        this.defineVector("LicenseKeys", this.defineString("LicenseKey", null));
    }
    
    public NFParamDef defineNoteAxisTuple(final String s) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(this.defineString(s + "XAxis", null));
        vector.addElement(this.defineString(s + "YAxis", null));
        return this.defineTuple(s + "Tuple", vector);
    }
    
    public void defineNoteSets() {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(this.defineString("NoteSetsTupleName"));
        vector.addElement(this.defineNoteJustify("NoteSetsTupleJustify"));
        this.defineVector("NoteSets", this.defineTuple("NoteSetsTuple", vector));
        this.defineVector("NoteLabel", this.defineLabel("NoteLabel"));
        this.defineVector("NoteBox", this.defineRegion("NoteBox"));
        this.defineVector("NoteArrow", this.defineNoteArrowTuple("NoteArrow"));
        this.defineVector("NoteAxis", this.defineNoteAxisTuple("NoteAxis"));
        this.defineVector("NoteDrag", this.defineString("NoteDragItem", "ON"));
        final NFParamDef defineNoteSetTuple = this.defineNoteSetTuple("NoteSet");
        for (int i = 1; i <= 20; ++i) {
            this.defineVector("NoteSet" + i, defineNoteSetTuple);
            this.defineActiveLabel("NoteActiveLabels" + i);
        }
    }
    
    public NFParamDef defineNoteJustify(final String s) {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("RIGHT", new Integer(0));
        hashtable.put("LEFT", new Integer(1));
        hashtable.put("TOP", new Integer(2));
        hashtable.put("BOTTOM", new Integer(3));
        hashtable.put("CENTER", new Integer(4));
        hashtable.put("TOPRIGHT", new Integer(5));
        hashtable.put("TOPLEFT", new Integer(6));
        hashtable.put("BOTTOMRIGHT", new Integer(7));
        hashtable.put("BOTTOMLEFT", new Integer(8));
        return this.defineSymbol(s, hashtable, new Integer(4));
    }
    
    public NFParamDef defineNoteArrowTuple(final String s) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        this.defineLine(s + "Line", vector);
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("NONE", new Integer(0));
        hashtable.put("FROMTO", new Integer(1));
        hashtable.put("TOFROM", new Integer(2));
        hashtable.put("BOTH", new Integer(3));
        hashtable.put("RECTANGLE", new Integer(4));
        hashtable.put("ELLIPSE", new Integer(5));
        vector.addElement(this.defineSymbol(s + "Type", hashtable, new Integer(1)));
        this.defineArrow(s, vector);
        return this.defineTuple(s + "Tuple", vector);
    }
    
    public NFParamDef defineNoteSetTuple(final String s) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(this.defineString(s + "Text", ""));
        vector.addElement(this.defineDate(s + "X", new Integer(0)));
        vector.addElement(this.defineDate(s + "Y", new Integer(0)));
        vector.addElement(this.defineDate(s + "X1", null));
        vector.addElement(this.defineDate(s + "Y1", null));
        vector.addElement(this.defineDate(s + "X2", null));
        vector.addElement(this.defineDate(s + "Y2", null));
        vector.addElement(this.defineDate(s + "X3", null));
        vector.addElement(this.defineDate(s + "Y3", null));
        return this.defineTuple(s + "Tuple", vector);
    }
    
    public void defineDebugParams(final String s) {
        this.defineVector(s, this.defineSymbol(s + "Item", NFDebug.getOptions(), new Integer(0)));
    }
    
    public void defineJdbcParams() {
    }
    
    public NFParamDef defineString(final String s) {
        return this.defineString(s, null);
    }
    
    public NFParamDef defineString(final String s, final String val) {
        final NFParamDef nfParamDef = new NFParamDef(s, 2);
        nfParamDef.val = val;
        this.c.put(s, nfParamDef);
        return nfParamDef;
    }
    
    public NFParamDef defineNumber(final String s) {
        return this.defineNumber(s, null);
    }
    
    public NFParamDef defineNumber(final String s, final Number val) {
        final NFParamDef nfParamDef = new NFParamDef(s, 1);
        nfParamDef.val = val;
        this.c.put(s, nfParamDef);
        return nfParamDef;
    }
    
    public NFParamDef defineColor(final String s) {
        return this.defineColor(s, Color.white);
    }
    
    public NFParamDef defineColor(final String s, final Color val) {
        final NFParamDef nfParamDef = new NFParamDef(s, 4);
        nfParamDef.val = val;
        this.c.put(s, nfParamDef);
        return nfParamDef;
    }
    
    public NFParamDef defineSymbol(final String s, final Hashtable hashtable) {
        return this.defineSymbol(s, hashtable, null);
    }
    
    public NFParamDef defineSymbol(final String s, final Hashtable symtable, final Object val) {
        final NFParamDef nfParamDef = new NFParamDef(s, 3);
        nfParamDef.symtable = symtable;
        nfParamDef.val = val;
        this.c.put(s, nfParamDef);
        return nfParamDef;
    }
    
    public NFParamDef defineTuple(final String s, final Vector tuple_def) {
        final NFParamDef parent = new NFParamDef(s, 5);
        parent.tuple_def = tuple_def;
        parent.tuple_val = new Vector();
        this.c.put(s, parent);
        for (int size = tuple_def.size(), i = 0; i < size; ++i) {
            tuple_def.elementAt(i).parent = parent;
        }
        return parent;
    }
    
    public NFParamDef defineTuple(final String s, final Vector vector, final boolean varLength) {
        final NFParamDef defineTuple = this.defineTuple(s, vector);
        defineTuple.varLength = varLength;
        return defineTuple;
    }
    
    public void defineVector(final String s, final NFParamDef vector_def) {
        final NFParamDef parent = new NFParamDef(s, 6);
        parent.vector_def = vector_def;
        parent.vector_val = new Vector();
        this.c.put(s, parent);
        vector_def.parent = parent;
    }
    
    public void defineAlias(final String s, final String s2) {
        if (s == null || s2 == null) {
            return;
        }
        NFParamDef paramDef = null;
        try {
            paramDef = this.getParamDef(s2);
        }
        catch (Exception ex) {}
        if (paramDef != null) {
            if (this.d == null) {
                this.d = new Vector();
            }
            this.c.put(s, paramDef);
            this.d.addElement(s);
        }
    }
    
    public boolean isAliasedParam(final String s) {
        return this.d != null && this.d.contains(s);
    }
    
    public NFParamDef defineImage(final String s) {
        return this.defineImage(s, null);
    }
    
    public NFParamDef defineImage(final String s, final URL url) {
        final NFParamDef nfParamDef = new NFParamDef(s, 7);
        nfParamDef.val = null;
        nfParamDef.imageLabel = "";
        this.c.put(s, nfParamDef);
        return nfParamDef;
    }
    
    public NFParamDef defineDate(final String s) {
        return this.defineDate(s, null);
    }
    
    public NFParamDef defineDate(final String s, final Object val) {
        final NFParamDef nfParamDef = new NFParamDef(s, 8);
        nfParamDef.val = val;
        this.c.put(s, nfParamDef);
        return nfParamDef;
    }
    
    public NFParamDef defineChartType(final String s, final int n) {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("BREAK", new Integer(-2));
        hashtable.put("DEFAULT", new Integer(-1));
        hashtable.put("BARCHART", new Integer(1));
        hashtable.put("BOXCHART", new Integer(2));
        hashtable.put("COMBOCHART", new Integer(3));
        hashtable.put("DIAGRAM", new Integer(4));
        hashtable.put("PIECHART", new Integer(5));
        hashtable.put("STRIPCHART", new Integer(6));
        hashtable.put("STOCKCHART", new Integer(7));
        hashtable.put("TIMECHART", new Integer(8));
        hashtable.put("XYCHART", new Integer(9));
        hashtable.put("LINECHART", new Integer(10));
        hashtable.put("MULTICHART", new Integer(11));
        hashtable.put("DIALCHART", new Integer(12));
        hashtable.put("BUBBLECHART", new Integer(14));
        hashtable.put("RADARCHART", new Integer(15));
        hashtable.put("BAR", new Integer(1));
        hashtable.put("BOX", new Integer(2));
        hashtable.put("COMBO", new Integer(3));
        hashtable.put("PIE", new Integer(5));
        hashtable.put("STRIP", new Integer(6));
        hashtable.put("STOCK", new Integer(7));
        hashtable.put("TIME", new Integer(8));
        hashtable.put("XY", new Integer(9));
        hashtable.put("LINE", new Integer(10));
        hashtable.put("MULTI", new Integer(11));
        hashtable.put("DIAL", new Integer(12));
        hashtable.put("BUBBLE", new Integer(14));
        hashtable.put("RADAR", new Integer(15));
        return this.defineSymbol(s, hashtable, new Integer(n));
    }
    
    public NFParamDef defineBorderType(final String s, final int n) {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("NONE", new Integer(0));
        hashtable.put("BOX", new Integer(1));
        hashtable.put("SHADOW", new Integer(2));
        hashtable.put("RAISED", new Integer(3));
        hashtable.put("RECESS", new Integer(4));
        return this.defineSymbol(s, hashtable, new Integer(n));
    }
    
    public NFParamDef defineImageType(final String s, final int n) {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("TILE", new Integer(0));
        hashtable.put("SIZE", new Integer(1));
        hashtable.put("CENTER", new Integer(4));
        return this.defineSymbol(s, hashtable, new Integer(n));
    }
    
    public NFParamDef defineGraphSymbol(final String s) {
        final Vector vector = new Vector();
        this.defineGraphSymbol(s, vector);
        return this.defineTuple(s, vector);
    }
    
    public Hashtable getSymbolTypeTable() {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("NONE", new Integer(0));
        hashtable.put("CIRCLE", new Integer(1));
        hashtable.put("SQUARE", new Integer(2));
        hashtable.put("TRIANGLEUP", new Integer(4));
        hashtable.put("TRIANGLEDOWN", new Integer(5));
        hashtable.put("DIAMOND", new Integer(3));
        hashtable.put("CROSS", new Integer(6));
        hashtable.put("HRECTANGLE", new Integer(8));
        hashtable.put("VRECTANGLE", new Integer(7));
        hashtable.put("BAR", new Integer(9));
        hashtable.put("TRIANGLEBAR", new Integer(10));
        hashtable.put("DIAMONDBAR", new Integer(11));
        hashtable.put("CYLINDER", new Integer(12));
        hashtable.put("PIEHORIZONTAL", new Integer(14));
        hashtable.put("PIEVERTICAL", new Integer(13));
        hashtable.put("IMAGE", new Integer(15));
        return hashtable;
    }
    
    public Hashtable getSymbolStyleTable() {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("FILLED", new Integer(1));
        hashtable.put("OUTLINED", new Integer(2));
        hashtable.put("BOTH", new Integer(3));
        return hashtable;
    }
    
    public int defineGraphSymbol(final String s, final Vector vector) {
        vector.addElement(this.defineSymbol(s + "SymType", this.getSymbolTypeTable(), new Integer(1)));
        vector.addElement(this.defineNumber(s + "SymSize", new Integer(4)));
        vector.addElement(this.defineSymbol(s + "SymStyle", this.getSymbolStyleTable(), new Integer(1)));
        return gsParamCount();
    }
    
    public static int gsParamCount() {
        return 3;
    }
    
    public NFParamDef defineLine(final String s) {
        final Vector vector = new Vector();
        this.defineLine(s, vector);
        return this.defineTuple(s, vector);
    }
    
    public int defineLine(final String s, final Vector vector) {
        vector.addElement(this.defineLineStyle(s + "LineStyle", 0));
        vector.addElement(this.defineNumber(s + "LineWidth", new Integer(1)));
        vector.addElement(this.defineColor(s + "LineColor", null));
        return lineParamCount();
    }
    
    public Hashtable getLineStyleTable() {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("NONE", new Integer(0));
        hashtable.put("SOLID", new Integer(1));
        hashtable.put("DOTTED", new Integer(2));
        hashtable.put("DASHED", new Integer(3));
        hashtable.put("DOTDASH", new Integer(4));
        return hashtable;
    }
    
    public NFParamDef defineLineStyle(final String s, final int n) {
        return this.defineSymbol(s, this.getLineStyleTable(), new Integer(n));
    }
    
    public static int lineParamCount() {
        return 3;
    }
    
    public NFParamDef defineArrow(final String s) {
        final Vector vector = new Vector();
        this.defineArrow(s, vector);
        return this.defineTuple(s, vector);
    }
    
    public int defineArrow(final String s, final Vector vector) {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("NONE", new Integer(0));
        hashtable.put("LINE", new Integer(1));
        hashtable.put("BLOCK", new Integer(2));
        hashtable.put("SHARP", new Integer(3));
        hashtable.put("ROUND", new Integer(4));
        vector.addElement(this.defineSymbol(s + "ArrowStyle", hashtable, new Integer(1)));
        vector.addElement(this.defineNumber(s + "ArrowLength", new Integer(10)));
        vector.addElement(this.defineNumber(s + "ArrowWidth", new Integer(5)));
        return 3;
    }
    
    public double getChartScale() {
        double doubleValue = 0.0;
        try {
            final Number n = (Number)this.get("ScaleFactor");
            if (n != null) {
                doubleValue = n.doubleValue();
            }
        }
        catch (Exception ex) {}
        if (Double.isNaN(doubleValue)) {
            return 0.0;
        }
        return doubleValue;
    }
    
    public void clean() {
        final Enumeration keys = this.getKeys();
        while (keys.hasMoreElements()) {
            try {
                final NFParamDef paramDef = this.getParamDef(keys.nextElement());
                if (paramDef == null || !paramDef.loaded) {
                    continue;
                }
                switch (paramDef.type) {
                    case 6: {
                        paramDef.vector_val.removeAllElements();
                        break;
                    }
                    case 5: {
                        paramDef.tuple_val.removeAllElements();
                        break;
                    }
                    default: {
                        paramDef.val = null;
                        break;
                    }
                }
                paramDef.changed = true;
                paramDef.expr = null;
            }
            catch (Exception ex) {}
        }
    }
    
    static {
        NFParam.f = new StringBuffer();
    }
}
