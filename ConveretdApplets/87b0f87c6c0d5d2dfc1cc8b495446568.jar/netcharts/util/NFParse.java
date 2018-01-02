// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.awt.Color;
import java.util.Date;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Image;
import java.util.Hashtable;
import java.util.Stack;
import java.awt.Component;
import java.applet.Applet;
import java.util.Vector;
import java.awt.MediaTracker;

public class NFParse
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
    private NFParam b;
    private MediaTracker c;
    private Vector d;
    private NFDataBean e;
    private NFDataBeanObserver f;
    private Applet g;
    private String h;
    private Component i;
    private NFImageCache j;
    private NFContext k;
    private Vector l;
    private Stack m;
    private NFState n;
    private int o;
    private Vector p;
    private Hashtable q;
    private NFDataBeanTable r;
    private NFToken s;
    
    public NFParse(final NFParam b) {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = new Stack();
        this.n = new NFState(0, null, null);
        this.o = 0;
        this.p = null;
        this.q = new Hashtable();
        this.r = null;
        this.s = null;
        this.b = b;
    }
    
    public void setContext(final NFContext nfContext) {
        this.k = nfContext;
        if (this.j != null) {
            this.j.setContext(nfContext);
        }
    }
    
    public NFContext getContext() {
        return this.k;
    }
    
    public void setDateFormats(final Vector l) {
        this.l = l;
    }
    
    public Vector getDateFormats() {
        return this.l;
    }
    
    public void setParam(final NFParam b) {
        this.b = b;
    }
    
    public void setApp(final Applet g) {
        this.g = g;
    }
    
    public void setComponent(final Component i) {
        this.i = i;
    }
    
    public void setFileBase(final String h) {
        this.h = h;
    }
    
    public void setDataBeanObserver(final NFDataBeanObserver f) {
        this.f = f;
    }
    
    private Object a(final NFParamDef nfParamDef) {
        return this.b.getValue(nfParamDef, false);
    }
    
    private Object a(final NFParamDef nfParamDef, final boolean b) {
        return this.b.getValue(nfParamDef, b);
    }
    
    private void a(final NFParamDef nfParamDef, final Object o) {
        this.b.setValue(nfParamDef, o, false);
    }
    
    private void a(final NFParamDef nfParamDef, final Object o, final boolean b) {
        this.b.setValue(nfParamDef, o, b);
    }
    
    private static void a(final String s, final String s2) throws NFParamException {
        throw new NFParamException(s + ": " + s2);
    }
    
    private void a(final Stack stack, final int n, final NFParamDef nfParamDef, final NFParamDef nfParamDef2) {
        stack.push(new NFState(n, nfParamDef, nfParamDef2));
    }
    
    private int a(final Stack stack) {
        return stack.peek().a;
    }
    
    private NFParamDef b(final Stack stack) {
        return stack.peek().b;
    }
    
    private NFParamDef c(final Stack stack) {
        return stack.peek().c;
    }
    
    private void d(final Stack stack) {
        stack.pop();
    }
    
    private void a(final Stack stack, final Object o) {
        final NFState nfState = stack.peek();
        if (nfState.c != null) {
            this.a(nfState.c, o, true);
        }
        else {
            this.a(nfState.b, o);
        }
    }
    
    private void a(final NFParamDef nfParamDef, final NFParamDef nfParamDef2, final Object o) {
        if (nfParamDef != null) {
            this.a(nfParamDef, o, true);
        }
        else {
            this.a(nfParamDef2, o);
        }
    }
    
    private Object b(final NFParamDef nfParamDef) {
        Vector<Object> vector;
        if (nfParamDef.inVector == null || nfParamDef.inVector.vector_tmp == null || nfParamDef.inVector.vector_tmp.size() == 0) {
            vector = null;
        }
        else {
            vector = nfParamDef.inVector.vector_tmp.elementAt(nfParamDef.inVector.vector_tmp.size() - 1);
        }
        int size = nfParamDef.tuple_tmp.size();
        Object o;
        if (vector == null) {
            if (size >= nfParamDef.tuple_def.size()) {
                size = nfParamDef.tuple_def.size() - 1;
            }
            o = this.a(nfParamDef.tuple_def.elementAt(size));
        }
        else {
            if (size >= vector.size()) {
                size = vector.size() - 1;
            }
            o = vector.elementAt(size);
        }
        return o;
    }
    
    private void a(final NFState nfState, final Stack stack, final String s) throws Exception {
        final NFParamDef c = this.c(stack);
        if (c == null) {
            throw new Exception("Illegal syntax, expected " + this.a(this.a(stack)));
        }
        if (c.type == 6 && s.equals(",")) {
            switch (this.a(stack)) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 8: {
                    this.a(stack, this.b(stack), null);
                    this.d(stack);
                    return;
                }
            }
        }
        if (c.type != 5) {
            throw new Exception("Illegal syntax, expected " + this.a(this.a(stack)));
        }
        if (s.equals(",")) {
            ++nfState.e;
            this.a(c, this.b(c), true);
            this.d(stack);
            return;
        }
        if (s.equals(")")) {
            ++nfState.e;
            for (int i = c.tuple_tmp.size(); i < c.tuple_def.size(); ++i) {
                this.a(c, this.b(c), true);
            }
            while (this.a(stack) != 5) {
                this.d(stack);
            }
        }
    }
    
    private String a(final int n) {
        switch (n) {
            case 0: {
                return "Param ";
            }
            case 2: {
                return "String";
            }
            case 1: {
                return "Number";
            }
            case 3: {
                return "Symbol";
            }
            case 4: {
                return "Color ";
            }
            case 5: {
                return "Tuple ";
            }
            case 6: {
                return "Vector";
            }
            case 7: {
                return "Image ";
            }
            case 8: {
                return "Date  ";
            }
            case 44: {
                return "Comma ";
            }
            case 40: {
                return "Left  ";
            }
            case 41: {
                return "Right ";
            }
            case 61: {
                return "Equal ";
            }
            default: {
                return "Unknown";
            }
        }
    }
    
    private void a() {
        if (this.o > 0) {
            --this.o;
        }
    }
    
    private String b() {
        if (this.o >= this.p.size()) {
            return null;
        }
        final String element = this.p.elementAt(this.o);
        ++this.o;
        String string;
        if (element instanceof String) {
            string = element;
        }
        else {
            string = element.toString();
        }
        return string;
    }
    
    private Object c() {
        if (this.o >= this.p.size()) {
            return null;
        }
        final Object element = this.p.elementAt(this.o);
        ++this.o;
        return element;
    }
    
    public void parseStatement(final NFKeyValue nfKeyValue, final Vector vector) throws Exception {
        this.parseTokens((String)nfKeyValue.key, (Vector)nfKeyValue.value, vector);
    }
    
    public void parseTokens(final String s, final Vector p3, final Vector d) throws Exception {
        this.d = d;
        this.e = null;
        if (s.equals("Update")) {
            this.b.update();
            return;
        }
        this.n.a = 0;
        this.n.b = null;
        this.n.c = null;
        this.n.d = -1;
        this.n.e = -1;
        this.m.removeAllElements();
        this.a(this.m, 0, null, null);
        this.p = p3;
        this.o = 0;
        String b = s;
        while (!this.m.empty()) {
            try {
                this.b(this.n, this.m, b);
            }
            catch (Exception ex) {
                this.a(this.n, this.m, b, ex.getMessage());
            }
            b = this.b();
        }
        this.a(s);
    }
    
    private void a(final NFState nfState, final Stack stack, final String s, final String s2) throws Exception {
        if (nfState.b != null) {
            String s3 = nfState.b.param + ": ";
            if (nfState.d > -1) {
                s3 = s3 + "item(" + (nfState.d + 1) + "): ";
            }
            if (nfState.e > -1) {
                s3 = s3 + "attr(" + (nfState.e + 1) + "): ";
            }
            throw new Exception(s3 + s2);
        }
        throw new Exception(s2);
    }
    
    private void b(final NFState nfState, final Stack stack, String f) throws Exception {
        switch (this.a(stack)) {
            case 0: {
                if (f == null) {
                    this.d(stack);
                    return;
                }
                nfState.b = null;
                nfState.d = -1;
                nfState.e = -1;
                nfState.f = null;
                f = NFToken.stripQuotes(f);
                NFParamDef nfParamDef;
                try {
                    nfParamDef = this.b.getParamDef(f);
                    this.e = (NFDataBean)nfParamDef.dataBean;
                    if (this.e != null) {
                        this.e.setExprParam(f);
                    }
                }
                catch (Exception ex) {
                    try {
                        this.e = this.b(f);
                        if (this.e != null) {
                            this.e.setExprParam(f);
                        }
                        nfParamDef = this.b.getParamDef(f);
                    }
                    catch (Exception ex2) {
                        throw new Exception("Undefined parameter <" + f + ">");
                    }
                }
                nfState.b = nfParamDef;
                nfState.f = f;
                this.a(stack, nfParamDef.type, nfParamDef, null);
                if (nfParamDef.type == 6) {
                    nfState.d = 0;
                    nfParamDef.vector_def.inVector = nfParamDef;
                    if (nfParamDef.vector_tmp == null) {
                        nfParamDef.vector_tmp = new Vector();
                    }
                    else {
                        nfParamDef.vector_tmp.removeAllElements();
                    }
                    this.a(stack, nfParamDef.vector_def.type, nfParamDef.vector_def, nfParamDef);
                }
                this.a(stack, 61, null, null);
                break;
            }
            case 61: {
                if (f == null) {
                    this.d(stack);
                    break;
                }
                if (!f.equals("=")) {
                    this.a();
                }
                this.d(stack);
                break;
            }
            case 1:
            case 2:
            case 3:
            case 4:
            case 7:
            case 8: {
                if (f == null) {
                    this.d(stack);
                    break;
                }
                if (f.equals(",") || f.equals(")")) {
                    this.a(nfState, stack, f);
                    this.a();
                    break;
                }
                f = NFToken.stripQuotes(f);
                if (f.length() == 0 && this.a(stack) != 2) {
                    break;
                }
                this.b(nfState, stack, f, nfState.f);
                break;
            }
            case 5: {
                if (f == null) {
                    this.d(stack);
                    break;
                }
                if (f.length() == 0) {
                    this.d(stack);
                    break;
                }
                final NFParamDef b = this.b(stack);
                if (f.equals("(")) {
                    nfState.e = 0;
                    if (b.tuple_tmp == null) {
                        b.tuple_tmp = new Vector();
                    }
                    else {
                        b.tuple_tmp.removeAllElements();
                    }
                    for (int i = b.tuple_def.size(); i > 0; --i) {
                        final NFParamDef nfParamDef2 = b.tuple_def.elementAt(i - 1);
                        this.a(stack, nfParamDef2.type, nfParamDef2, b);
                        if (i > 1) {
                            this.a(stack, 44, null, null);
                        }
                    }
                    break;
                }
                if (f.equals(",") && b.varLength && b.tuple_tmp != null && b.tuple_tmp.size() >= b.tuple_def.size()) {
                    final NFParamDef nfParamDef3 = b.tuple_def.elementAt(b.tuple_def.size() - 1);
                    this.a(stack, nfParamDef3.type, nfParamDef3, b);
                    this.a(stack, 44, null, null);
                    this.a();
                    break;
                }
                if (f.equals(")")) {
                    b.tuple_val = b.tuple_tmp;
                    b.tuple_tmp = null;
                    if (this.c(stack) != null) {
                        this.a(stack, this.a(b));
                    }
                    else {
                        b.changed = true;
                    }
                    this.d(stack);
                    break;
                }
                this.e = this.c(f);
                if (this.e != null) {
                    this.a(this.e, stack, nfState.f);
                    break;
                }
                throw new Exception("Expected tuple '(' or ')', found <" + f + ">");
            }
            case 44: {
                if (f == null) {
                    this.d(stack);
                    break;
                }
                if (f.equals(",")) {
                    ++nfState.e;
                    this.d(stack);
                    break;
                }
                if (f.equals(")")) {
                    ++nfState.e;
                    this.d(stack);
                    this.a();
                    break;
                }
                throw new Exception("Expected comma, found <" + f + ">");
            }
            case 6: {
                final NFParamDef b2 = this.b(stack);
                if (f != null && f.equals(",")) {
                    ++nfState.d;
                    this.a(stack, b2.vector_def.type, b2.vector_def, b2);
                    break;
                }
                b2.vector_val = b2.vector_tmp;
                b2.vector_tmp = null;
                b2.changed = true;
                this.d(stack);
                if (f != null) {
                    this.a();
                    break;
                }
                break;
            }
        }
    }
    
    private void b(final NFState nfState, final Stack stack, final String s, final String s2) throws Exception {
        final NFParamDef b = this.b(stack);
        if (!((s2 == null) ? b.param : s2).toLowerCase().startsWith("debug")) {
            final NFDataBean c = this.c(s);
            if (c != null) {
                this.a(this.e = c, stack, s2);
                return;
            }
        }
        this.a(stack, b, s);
        this.d(stack);
    }
    
    private void a(final String s) throws Exception {
        if (s.toUpperCase().startsWith("DEBUG")) {
            if (s.equalsIgnoreCase("DebugSet") && NFContext.getUserAgentType() != 3) {
                NFDebug.set((Vector)this.b.get("DebugSet"));
            }
            if (s.equalsIgnoreCase("DebugClear") && NFContext.getUserAgentType() != 3) {
                NFDebug.clear((Vector)this.b.get("DebugClear"));
            }
            return;
        }
        if (this.e != null) {
            switch (this.e.loadDataMode(s)) {
                case 1: {
                    final NFDataBean dataBean = this.r.newDataBean(this.e);
                    dataBean.setExprParam(this.e.getExprParam());
                    dataBean.loadParams(this.b);
                    this.d.addElement(dataBean);
                    this.a(dataBean);
                    this.b(dataBean);
                    break;
                }
                case 2: {
                    final NFDataBean dataBean2 = this.r.newDataBean(this.e);
                    dataBean2.setExprParam(this.e.getExprParam());
                    dataBean2.loadParams(this.b);
                    this.d.addElement(dataBean2);
                    this.a(dataBean2);
                    break;
                }
            }
            this.e = null;
        }
    }
    
    private void a(final NFDataBean expr) throws Exception {
        final String exprParam = expr.getExprParam();
        if (exprParam != null) {
            this.b.getParamDef(exprParam).expr = expr;
        }
    }
    
    private void b(final NFDataBean nfDataBean) throws Exception {
        final String exprParam = nfDataBean.getExprParam();
        Object paramDef = null;
        if (exprParam != null) {
            paramDef = this.b.getParamDef(exprParam);
        }
        this.f.dataBeanLoadParams(this, paramDef, nfDataBean);
    }
    
    private void a(final Stack stack, final NFParamDef nfParamDef, final Object o) throws NFParamException {
        this.b(this.c(stack), nfParamDef, o);
    }
    
    private void b(final NFParamDef nfParamDef, final NFParamDef nfParamDef2, final Object o) throws NFParamException {
        switch (nfParamDef2.type) {
            case 7: {
                this.c(nfParamDef, nfParamDef2, o);
                break;
            }
            case 8: {
                this.d(nfParamDef, nfParamDef2, o);
                break;
            }
            case 2: {
                this.e(nfParamDef, nfParamDef2, o);
                break;
            }
            case 1: {
                this.f(nfParamDef, nfParamDef2, o);
                break;
            }
            case 4: {
                this.g(nfParamDef, nfParamDef2, o);
                break;
            }
            case 3: {
                this.h(nfParamDef, nfParamDef2, o);
                break;
            }
            default: {
                throw new NFParamException("SYSTEM ERROR: Unknown value type = " + nfParamDef2.type);
            }
        }
    }
    
    private void c(final NFParamDef nfParamDef, final NFParamDef nfParamDef2, final Object o) throws NFParamException {
        if (o == null) {
            this.a(nfParamDef, nfParamDef2, (Object)null);
            return;
        }
        if (o instanceof NFParamImage) {
            this.a(nfParamDef, nfParamDef2, o);
            return;
        }
        NFParamImage nfParamImage;
        if (o instanceof Image) {
            nfParamImage = new NFParamImage("MemoryImage");
            nfParamImage.im = (Image)o;
        }
        else {
            if (!(o instanceof String)) {
                throw new NFParamException("Illegal IMAGE value");
            }
            final String imageLabel = (String)o;
            if (imageLabel == null || imageLabel.equalsIgnoreCase("null")) {
                this.a(nfParamDef, nfParamDef2, (Object)null);
                return;
            }
            nfParamImage = new NFParamImage(imageLabel);
            String resolvePath = NFUtil.resolvePath(imageLabel, this.k);
            if (resolvePath == null) {
                resolvePath = imageLabel;
            }
            if (this.i != null && NFContext.getUserAgentType() != 0 && NFUtil.getJDKVersion() >= 1.1) {
                try {
                    final URL fileURL = NFUtil.getFileURL(resolvePath, this.k);
                    nfParamDef2.imageLabel = imageLabel;
                    if (this.j == null) {
                        (this.j = new NFImageCache(this.i, this.g)).setContext(this.k);
                    }
                    nfParamImage.im = this.j.getImage(fileURL);
                }
                catch (Exception ex) {
                    nfParamImage.im = null;
                }
            }
            if (this.g != null && nfParamImage.im == null) {
                try {
                    final URL fileURL2 = NFUtil.getFileURL(resolvePath, this.k);
                    nfParamDef2.imageLabel = imageLabel;
                    if (NFUtil.getJDKVersion() >= 1.1) {
                        nfParamImage.im = NFNetworkAccess.getImageFromURL(fileURL2);
                    }
                    else {
                        nfParamImage.im = this.g.getImage(fileURL2);
                    }
                }
                catch (Exception ex2) {
                    nfParamImage.im = null;
                }
            }
            if (nfParamImage.im == null) {
                try {
                    final URL fileURL3 = NFUtil.getFileURL(resolvePath, this.k);
                    nfParamDef2.imageLabel = imageLabel;
                    nfParamImage.im = Toolkit.getDefaultToolkit().getImage(fileURL3);
                }
                catch (Exception ex3) {
                    nfParamImage.im = null;
                }
            }
        }
        if (nfParamImage.im != null) {
            try {
                if (this.c == null) {
                    this.c = new MediaTracker(this.g);
                }
                this.c.addImage(nfParamImage.im, 0);
                this.c.waitForID(0);
                if (NFUtil.getJDKVersion() >= 1.1) {
                    NF11Util.removeImage(this.c, nfParamImage.im, 0);
                }
            }
            catch (Exception ex4) {
                nfParamImage.im = null;
            }
        }
        this.a(nfParamDef, nfParamDef2, nfParamImage);
    }
    
    private void d(final NFParamDef nfParamDef, final NFParamDef nfParamDef2, final Object o) throws NFParamException {
        if (o == null) {
            this.a(nfParamDef, nfParamDef2, new Double(Double.NaN));
            return;
        }
        if (o instanceof Number || o instanceof NFDate || o instanceof NFTimeUnit) {
            this.a(nfParamDef, nfParamDef2, o);
            return;
        }
        if (!(o instanceof String)) {
            throw new NFParamException("Illegal DATE Value");
        }
        final String s = (String)o;
        if (s.length() == 0 || s.equalsIgnoreCase("null")) {
            this.a(nfParamDef, nfParamDef2, new Double(Double.NaN));
            return;
        }
        try {
            if (this.d(s)) {
                this.a(nfParamDef, nfParamDef2, Double.valueOf(s));
                return;
            }
        }
        catch (Exception ex) {}
        if (NFUtil.getJDKVersion() >= 1.1) {
            int n = 0;
            while (this.l != null) {
                if (n >= this.l.size()) {
                    break;
                }
                final Date date = NF11Util.parseDate(s, this.l.elementAt(n));
                if (date != null) {
                    this.a(nfParamDef, nfParamDef2, new NFDate(date.getTime()));
                    return;
                }
                ++n;
            }
        }
        try {
            final NFDate nfDate = new NFDate();
            nfDate.setTime(nfDate.parseDate(s));
            this.a(nfParamDef, nfParamDef2, nfDate);
        }
        catch (Exception ex2) {
            try {
                final NFTimeUnit nfTimeUnit = new NFTimeUnit();
                nfTimeUnit.parse(s);
                this.a(nfParamDef, nfParamDef2, nfTimeUnit);
            }
            catch (Exception ex3) {
                throw new NFParamException("Illegal DATE/TIME UNIT entry");
            }
        }
    }
    
    private void e(final NFParamDef nfParamDef, final NFParamDef nfParamDef2, final Object o) throws NFParamException {
        if (o == null) {
            this.a(nfParamDef, nfParamDef2, (Object)null);
            return;
        }
        if (!(o instanceof String)) {
            this.a(nfParamDef, nfParamDef2, o.toString());
            return;
        }
        final String s = (String)o;
        if (s.equalsIgnoreCase("null")) {
            this.a(nfParamDef, nfParamDef2, (Object)null);
            return;
        }
        this.a(nfParamDef, nfParamDef2, s);
    }
    
    private void f(final NFParamDef nfParamDef, final NFParamDef nfParamDef2, final Object o) throws NFParamException {
        if (o == null) {
            this.a(nfParamDef, nfParamDef2, new Double(Double.NaN));
            return;
        }
        if (o instanceof Number) {
            this.a(nfParamDef, nfParamDef2, o);
            return;
        }
        if (!(o instanceof String)) {
            throw new NFParamException("Illegal NUMBER syntax");
        }
        final String upperCase = ((String)o).trim().toUpperCase();
        if (upperCase.length() == 0 || upperCase.equalsIgnoreCase("null")) {
            this.a(nfParamDef, nfParamDef2, new Double(Double.NaN));
            return;
        }
        final int index = upperCase.indexOf(69);
        if (index != -1) {
            try {
                this.a(nfParamDef, nfParamDef2, new Double(Double.valueOf(upperCase.substring(0, index)) * Math.pow(10.0, Integer.parseInt(upperCase.substring(index + 1)))));
                return;
            }
            catch (Exception ex) {
                throw new NFParamException("Expected NUMBER, found <" + upperCase + ">");
            }
        }
        try {
            this.a(nfParamDef, nfParamDef2, Double.valueOf(upperCase));
        }
        catch (Exception ex2) {
            throw new NFParamException("Expected NUMBER, found <" + upperCase + ">");
        }
    }
    
    private void g(final NFParamDef nfParamDef, final NFParamDef nfParamDef2, final Object o) throws NFParamException {
        if (o == null) {
            this.a(nfParamDef, nfParamDef2, (Object)null);
            return;
        }
        if (o instanceof Color) {
            this.a(nfParamDef, nfParamDef2, o);
            return;
        }
        if (!(o instanceof String)) {
            throw new NFParamException("Illegal COLOR syntax");
        }
        final String s = (String)o;
        if (s.length() == 0 || s.equalsIgnoreCase("null")) {
            this.a(nfParamDef, nfParamDef2, (Object)null);
            return;
        }
        final Color value = NFColor.get(s.toLowerCase());
        if (value == null) {
            throw new NFParamException("Illegal COLOR value <" + s + ">");
        }
        this.a(nfParamDef, nfParamDef2, value);
    }
    
    private void h(final NFParamDef nfParamDef, final NFParamDef nfParamDef2, final Object o) throws NFParamException {
        if (o instanceof Number) {
            this.a(nfParamDef, nfParamDef2, o);
            return;
        }
        if (!(o instanceof String)) {
            throw new NFParamException("Illegal SYMBOL value");
        }
        if (nfParamDef2.symtable == null) {
            throw new NFParamException("SYSTEM ERROR: Empty symbol table for " + nfParamDef2.param);
        }
        String s = (String)o;
        if (s.length() == 0) {
            s = null;
        }
        final Object value = nfParamDef2.symtable.get(s);
        if (value == null) {
            throw new NFParamException("Illegal value <" + s + ">");
        }
        this.a(nfParamDef, nfParamDef2, value);
    }
    
    private NFDataBean b(final String s) throws Exception {
        if (this.r == null) {
            this.r = NFDataBeanTable.getDefault();
        }
        final NFDataBean dataBeanForParam = this.r.getDataBeanForParam(s);
        if (dataBeanForParam == null) {
            return null;
        }
        dataBeanForParam.defineParams(this.b);
        return dataBeanForParam;
    }
    
    private NFDataBean c(final String s) throws Exception {
        if (this.r == null) {
            this.r = NFDataBeanTable.getDefault();
        }
        final NFDataBean dataBeanForKeyword = this.r.getDataBeanForKeyword(s);
        if (dataBeanForKeyword == null) {
            return null;
        }
        dataBeanForKeyword.defineParams(this.b);
        return dataBeanForKeyword;
    }
    
    private void a(final NFDataBean nfDataBean, final Stack stack, final String s) throws Exception {
        final String keyword = nfDataBean.getKeyword();
        NFParamDef nfParamDef = this.c(stack);
        if (nfParamDef == null) {
            nfParamDef = this.b(stack);
        }
        nfParamDef.loaded = true;
        nfDataBean.setExprParam((s == null) ? nfParamDef.param : s);
        while (!stack.empty()) {
            stack.pop();
        }
        final String b = this.b();
        if (b != null && !b.equals(",")) {
            this.a();
        }
        try {
            final NFParamDef paramDef = this.b.getParamDef(keyword);
            this.n.d = -1;
            this.n.e = -1;
            this.n.b = paramDef;
            this.a(stack, paramDef.type, paramDef, null);
            if (paramDef.type == 6) {
                this.n.d = 0;
                paramDef.vector_def.inVector = paramDef;
                if (paramDef.vector_tmp == null) {
                    paramDef.vector_tmp = new Vector();
                }
                else {
                    paramDef.vector_tmp.removeAllElements();
                }
                this.a(stack, paramDef.vector_def.type, paramDef.vector_def, paramDef);
            }
        }
        catch (Exception ex) {
            throw new Exception("Undefined DataBean Expr <" + keyword + ">");
        }
    }
    
    private boolean d(final String s) {
        final int length = s.length();
        int i = 0;
        while (i < length) {
            switch (s.charAt(i)) {
                case '+':
                case '-':
                case '.':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case 'E':
                case 'e': {
                    ++i;
                    continue;
                }
                default: {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean loadDataTable(final String s, final Vector vector) throws NFParamException {
        NFParamDef nfParamDef = this.b.getParamDef(s);
        NFParamDef nfParamDef2 = null;
        if (nfParamDef != null && nfParamDef.type == 6) {
            nfParamDef2 = nfParamDef;
            nfParamDef = nfParamDef2.vector_def;
            if (nfParamDef2.vector_tmp == null) {
                nfParamDef2.vector_tmp = new Vector();
            }
            else {
                nfParamDef2.vector_tmp.removeAllElements();
            }
        }
        for (int i = 0; i < vector.size(); ++i) {
            this.a(nfParamDef2, nfParamDef, vector.elementAt(i));
        }
        if (nfParamDef2 != null) {
            nfParamDef2.vector_val = nfParamDef2.vector_tmp;
            nfParamDef2.vector_tmp = null;
            nfParamDef2.changed = true;
        }
        return true;
    }
    
    public boolean loadData(final String s, final Object o) throws NFParamException {
        this.setAttr(s, o);
        return true;
    }
    
    public synchronized void setAttr(final NFParamDef nfParamDef, final Object o) throws NFParamException {
        this.b(null, nfParamDef, o);
        final NFParamDef parent = nfParamDef.parent;
        if (parent == null || parent.type != 5) {
            return;
        }
        final Vector tuple_def = parent.tuple_def;
        Vector<Object> tuple_val = (Vector<Object>)parent.tuple_val;
        final int size = tuple_def.size();
        if (tuple_val == null || tuple_val.size() < size) {
            tuple_val = new Vector<Object>();
            for (int i = 0; i < size; ++i) {
                tuple_val.addElement(this.a(tuple_def.elementAt(i)));
            }
            parent.tuple_val = tuple_val;
            parent.changed = true;
        }
        for (int j = 0; j < size; ++j) {
            if (tuple_def.elementAt(j) == nfParamDef) {
                tuple_val.setElementAt(this.a(nfParamDef), j);
                parent.changed = true;
                break;
            }
        }
    }
    
    public synchronized void setAttr(final String s, final Object o) throws NFParamException {
        NFParamDef nfParamDef = this.b.getParamDef(s);
        NFParamDef nfParamDef2 = null;
        if (nfParamDef != null && nfParamDef.type == 6) {
            nfParamDef2 = nfParamDef;
            nfParamDef = nfParamDef2.vector_def;
        }
        if (nfParamDef2 == null && nfParamDef.type != 5) {
            this.setAttr(nfParamDef, o);
            return;
        }
        if (!(o instanceof Vector)) {
            throw new NFParamException("Expected Vector, found " + o);
        }
        if (nfParamDef2 == null && nfParamDef.type == 5) {
            this.a(nfParamDef2, nfParamDef, (Vector)o);
            return;
        }
        if (nfParamDef2.vector_tmp == null) {
            nfParamDef2.vector_tmp = new Vector();
        }
        else {
            nfParamDef2.vector_tmp.removeAllElements();
        }
        final Vector vector = (Vector)o;
        for (int i = 0; i < vector.size(); ++i) {
            if (nfParamDef.type != 5) {
                this.b(nfParamDef2, nfParamDef, vector.elementAt(i));
            }
            else {
                final Vector element = vector.elementAt(i);
                if (!(element instanceof Vector)) {
                    throw new NFParamException("Item(" + (i + 1) + ") is not a Vector");
                }
                this.a(nfParamDef2, nfParamDef, element);
            }
        }
        nfParamDef2.vector_val = nfParamDef2.vector_tmp;
        nfParamDef2.vector_tmp = null;
        nfParamDef2.changed = true;
    }
    
    public synchronized void setAttr(final String s, final Object o, final Object o2) throws NFParamException {
        this.setAttr(this.b.getParamDef(s), o, o2);
    }
    
    public synchronized void setAttr(final NFParamDef nfParamDef, final Object o, final Object o2) throws NFParamException {
        if (nfParamDef.type != 5 && nfParamDef.type != 6) {
            this.setAttr(nfParamDef, o2);
            return;
        }
        this.setAttr(nfParamDef, this.getItemIndex(nfParamDef, o), o2);
    }
    
    public synchronized void setAttr(final String s, final int n, final Object o) throws NFParamException {
        this.setAttr(this.b.getParamDef(s), n, o);
    }
    
    public synchronized void setAttr(final NFParamDef nfParamDef, final int n, final Object o) throws NFParamException {
        if (nfParamDef.type == 6) {
            this.a(nfParamDef, n, o);
            return;
        }
        if (nfParamDef.type == 5) {
            this.setTupleAttr(nfParamDef, n, o);
            return;
        }
        this.setAttr(nfParamDef, o);
    }
    
    public synchronized void setTupleAttr(final NFParamDef nfParamDef, final int n, final Object o) throws NFParamException {
        final Vector tuple_def = nfParamDef.tuple_def;
        if (tuple_def == null || tuple_def.size() == 0) {
            throw new NFParamException(nfParamDef.param + " has no attributes defined");
        }
        if (n < 0 || n >= tuple_def.size()) {
            throw new NFParamException(nfParamDef.param + " has no attribute with an index of " + n);
        }
        this.setAttr(tuple_def.elementAt(n), o);
    }
    
    private synchronized void a(final NFParamDef nfParamDef, final int n, final Object o) throws NFParamException {
        final Vector vector_val = nfParamDef.vector_val;
        if (vector_val == null || vector_val.size() == 0) {
            throw new NFParamException(nfParamDef.param + " has no items defined");
        }
        if (n < 0 || n >= vector_val.size()) {
            throw new NFParamException(nfParamDef.param + " has no item with an index of " + n);
        }
        if (nfParamDef.vector_tmp == null) {
            nfParamDef.vector_tmp = new Vector();
        }
        else {
            nfParamDef.vector_tmp.removeAllElements();
        }
        final NFParamDef vector_def = nfParamDef.vector_def;
        if (vector_def.type != 5) {
            this.b(nfParamDef, vector_def, o);
        }
        else {
            if (!(o instanceof Vector)) {
                throw new NFParamException(nfParamDef.param + " requires Vector data, found " + o);
            }
            this.a(nfParamDef, vector_def, (Vector)o);
        }
        vector_val.setElementAt(nfParamDef.vector_tmp.elementAt(0), n);
        nfParamDef.vector_tmp = null;
        nfParamDef.changed = true;
    }
    
    public synchronized void setAttr(final String s, final Object o, final Object o2, final Object o3) throws NFParamException {
        final NFParamDef paramDef = this.b.getParamDef(s);
        if (paramDef.type != 6) {
            this.setAttr(paramDef, o, o3);
            return;
        }
        this.setAttr(paramDef, this.getItemIndex(paramDef, o), o2, o3);
    }
    
    public synchronized void setAttr(final String s, final Object o, final int n, final Object o2) throws NFParamException {
        final NFParamDef paramDef = this.b.getParamDef(s);
        this.setAttr(paramDef, this.getItemIndex(paramDef, o), n, o2);
    }
    
    public synchronized void setAttr(final String s, final int n, final Object o, final Object o2) throws NFParamException {
        this.setAttr(this.b.getParamDef(s), n, o, o2);
    }
    
    public synchronized void setAttr(final NFParamDef nfParamDef, final int n, final Object o, final Object o2) throws NFParamException {
        if (nfParamDef.type != 6) {
            this.setAttr(nfParamDef, n, o2);
            return;
        }
        final NFParamDef vector_def = nfParamDef.vector_def;
        if (vector_def.type != 5) {
            this.setAttr(nfParamDef, n, o2);
            return;
        }
        this.setAttr(nfParamDef, n, this.getItemIndex(vector_def, o), o2);
    }
    
    public synchronized void setAttr(final String s, final int n, final int n2, final Object o) throws NFParamException {
        this.setAttr(this.b.getParamDef(s), n, n2, o);
    }
    
    public synchronized void setAttr(final NFParamDef nfParamDef, final int n, final int n2, final Object o) throws NFParamException {
        if (nfParamDef.type != 6) {
            this.setAttr(nfParamDef, n, o);
            return;
        }
        final NFParamDef vector_def = nfParamDef.vector_def;
        if (vector_def.type != 5) {
            this.setAttr(nfParamDef, n, o);
            return;
        }
        final Vector vector_val = nfParamDef.vector_val;
        if (vector_val == null || vector_val.size() == 0) {
            throw new NFParamException(nfParamDef.param + " has no items defined");
        }
        if (n < 0 || n >= vector_val.size()) {
            throw new NFParamException(nfParamDef.param + " has no item with an index of " + n);
        }
        final Vector tuple_def = vector_def.tuple_def;
        if (tuple_def == null || tuple_def.size() == 0) {
            throw new NFParamException(nfParamDef.param + " has no tuple definitions");
        }
        final Vector tuple_val = vector_def.tuple_val;
        if (tuple_val == null || tuple_val.size() < tuple_def.size()) {
            throw new NFParamException(nfParamDef.param + " has no default tuple values");
        }
        if (n2 < 0 || n2 >= tuple_def.size()) {
            throw new NFParamException(nfParamDef.param + " has no attribute with an index of " + n2);
        }
        final NFParamDef nfParamDef2 = vector_def.tuple_def.elementAt(n2);
        final Object a = this.a(nfParamDef2);
        this.b(null, nfParamDef2, o);
        final Object a2 = this.a(nfParamDef2);
        this.a(nfParamDef2, a);
        vector_val.elementAt(n).setElementAt(a2, n2);
        nfParamDef.changed = true;
    }
    
    public int getItemIndex(final String s, final Object o) throws NFParamException {
        return this.getItemIndex(this.b.getParamDef(s), o);
    }
    
    public int getItemIndex(final NFParamDef nfParamDef, final Object o) throws NFParamException {
        return this.getItemIndex(nfParamDef, o, true);
    }
    
    public int getItemIndex(final String s, final Object o, final boolean b) throws NFParamException {
        return this.getItemIndex(this.b.getParamDef(s), o, b);
    }
    
    public int getItemIndex(final NFParamDef nfParamDef, final Object o, final boolean b) throws NFParamException {
        final String string = o.toString();
        switch (nfParamDef.type) {
            default: {
                return 0;
            }
            case 5: {
                final Vector tuple_def = nfParamDef.tuple_def;
                if (tuple_def == null) {
                    throw new NFParamException(nfParamDef.param + " has no attributes defined");
                }
                for (int i = 0; i < tuple_def.size(); ++i) {
                    final NFParamDef nfParamDef2 = tuple_def.elementAt(i);
                    if (nfParamDef2.param.equals(string)) {
                        return i;
                    }
                    if (nfParamDef2.param.equals(nfParamDef.param + string)) {
                        return i;
                    }
                    if (nfParamDef.parent != null && nfParamDef2.param.equals(nfParamDef.parent.param + string)) {
                        return i;
                    }
                }
                if (b) {
                    throw new NFParamException(nfParamDef.param + " has no attribute named <" + string + ">");
                }
                return -1;
            }
            case 6: {
                final NFParamDef vector_def = nfParamDef.vector_def;
                if (vector_def == null) {
                    throw new NFParamException(nfParamDef.param + " has no definition");
                }
                final Vector vector_val = nfParamDef.vector_val;
                if (vector_val == null || vector_val.size() == 0) {
                    throw new NFParamException(nfParamDef.param + " has no items defined");
                }
                for (int j = 0; j < vector_val.size(); ++j) {
                    if (vector_def.type != 5) {
                        if (string.equals(vector_val.elementAt(j).toString())) {
                            return j;
                        }
                    }
                    else {
                        final Vector<Object> vector = vector_val.elementAt(j);
                        if (vector != null) {
                            if (vector.size() >= 1) {
                                if (string.equals(vector.elementAt(0).toString())) {
                                    return j;
                                }
                            }
                        }
                    }
                }
                if (b) {
                    throw new NFParamException(nfParamDef.param + " has no item with a key value of <" + string + ">");
                }
                return -1;
            }
        }
    }
    
    public synchronized Object getAttr(final String s) throws NFParamException {
        return this.b.cloneValue(this.b.getParamDef(s));
    }
    
    private void a(final NFParamDef nfParamDef, final NFParamDef nfParamDef2, final Vector vector) throws NFParamException {
        if (nfParamDef2.type != 5) {
            this.b(nfParamDef, nfParamDef2, vector.elementAt(0));
            return;
        }
        if (nfParamDef2.tuple_tmp == null) {
            nfParamDef2.tuple_tmp = new Vector();
        }
        else {
            nfParamDef2.tuple_tmp.removeAllElements();
        }
        final int size = vector.size();
        final int size2 = nfParamDef2.tuple_def.size();
        while (true) {
            for (int i = 0; i < size; ++i) {
                int n = i;
                if (n >= size2) {
                    if (!nfParamDef2.varLength) {
                        while (i < size2) {
                            this.a(nfParamDef2, this.b(nfParamDef2), true);
                            ++i;
                        }
                        nfParamDef2.tuple_val = nfParamDef2.tuple_tmp;
                        nfParamDef2.tuple_tmp = null;
                        if (nfParamDef != null) {
                            this.a(nfParamDef, nfParamDef2, this.a(nfParamDef2));
                        }
                        else {
                            nfParamDef2.changed = true;
                        }
                        return;
                    }
                    n = size2 - 1;
                }
                final NFParamDef nfParamDef3 = nfParamDef2.tuple_def.elementAt(n);
                try {
                    final Object element = vector.elementAt(i);
                    if (element == null) {
                        this.a(nfParamDef2, this.b(nfParamDef2), true);
                    }
                    else {
                        this.b(nfParamDef2, nfParamDef3, element);
                    }
                }
                catch (Exception ex) {
                    throw new NFParamException("Col(" + (i + 1) + "): " + ex.getMessage());
                }
            }
            continue;
        }
    }
    
    public static Vector parseStringVector(final String input, final boolean b) {
        if (input == null) {
            return null;
        }
        final NFToken nfToken = new NFToken();
        nfToken.setInput(input);
        final Vector<String> vector = new Vector<String>();
        try {
            StringBuffer nextToken;
            while ((nextToken = nfToken.nextToken()) != null) {
                final String string = nextToken.toString();
                if (",".equals(string)) {
                    continue;
                }
                vector.addElement(b ? NFToken.stripQuotes(string) : string);
            }
        }
        catch (Exception ex) {}
        return vector;
    }
}
