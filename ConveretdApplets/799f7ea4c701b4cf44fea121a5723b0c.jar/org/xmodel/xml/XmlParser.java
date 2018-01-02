// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xml;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.xmodel.ModelObject;
import java.io.FileReader;
import java.io.File;
import java.text.ParseException;
import java.io.IOException;
import org.xmodel.IModelObject;
import java.util.HashMap;
import org.xmodel.ModelObjectFactory;
import java.io.Reader;
import java.util.Map;
import org.xmodel.IModelObjectFactory;

public final class XmlParser
{
    private IModelObjectFactory E;
    private Map<String, String> G;
    private Reader F;
    private char[] D;
    private int C;
    private int A;
    private int B;
    private int H;
    private boolean I;
    private boolean J;
    
    public XmlParser(final Reader f) {
        this.E = new ModelObjectFactory();
        this.F = f;
        this.D = new char[4096];
        this.B = -1;
        (this.G = new HashMap<String, String>()).put("lt", "<");
        this.G.put("gt", ">");
        this.G.put("amp", "&");
        this.G.put("quot", "\"");
        this.G.put("apos", "'");
    }
    
    public String lookupEntity(final String s) {
        return this.G.get(s);
    }
    
    public final IModelObject parse() throws IOException, ParseException {
        final IModelObject object = this.E.createObject(null, "document");
        while (this.A(object, null)) {}
        return object;
    }
    
    private final IModelObject H() throws IOException, ParseException {
        final StringBuilder sb = new StringBuilder();
        if (!this.B(sb)) {
            return null;
        }
        final IModelObject object = this.E.createObject(null, sb.toString());
        final StringBuilder sb2 = new StringBuilder();
        final char e = this.E();
        if (e == '/') {
            if (this.E() != '>') {
                throw this.C("Expected > character.");
            }
            return object;
        }
        else {
            if (e == '>') {
                while (this.A(object, sb2)) {}
            }
            else {
                --this.C;
                while (this.C(object)) {}
                final char e2 = this.E();
                if (e2 == '/') {
                    if (this.E() != '>') {
                        throw this.C("Expected > character.");
                    }
                    return object;
                }
                else {
                    if (e2 != '>') {
                        throw this.C("Expected end of element tag.");
                    }
                    while (this.A(object, sb2)) {}
                }
            }
            if (this.E() != '/') {
                throw this.C("Illegal character.");
            }
            if (!this.A(object.getType())) {
                throw this.C(String.format("Expected \"%s\" end tag.", object.getType()));
            }
            if (this.E() != '>') {
                throw this.C("Illegal character.");
            }
            object.setValue(sb2.toString());
            return object;
        }
    }
    
    private final boolean C(final IModelObject modelObject) throws IOException, ParseException {
        this.E();
        --this.C;
        final StringBuilder sb = new StringBuilder();
        if (!this.B(sb)) {
            --this.C;
            return false;
        }
        if (this.G() != '=') {
            throw this.C("Expected = character.");
        }
        final StringBuilder sb2 = new StringBuilder();
        final char g = this.G();
        if (g != '\'' && g != '\"') {
            throw this.C("Expected ' or \" character.");
        }
        for (char c = this.G(); c != g; c = this.G()) {
            if (c == '<') {
                throw this.C("Illegal character.");
            }
            if (c == '&') {
                this.C(sb2);
            }
            else {
                sb2.append(c);
            }
        }
        modelObject.setAttribute(sb.toString(), sb2.toString());
        return true;
    }
    
    private boolean A(final IModelObject modelObject, final StringBuilder sb) throws IOException, ParseException {
        char c = this.G();
        if (this.I) {
            return false;
        }
        while (c != '<' && !this.I) {
            if (c == '&') {
                this.C(sb);
            }
            else if (sb != null) {
                sb.append(c);
            }
            c = this.G();
        }
        final char g = this.G();
        if (this.I) {
            return false;
        }
        if (g == '/') {
            --this.C;
            return false;
        }
        if (g == '!') {
            switch (this.G()) {
                case '-': {
                    if (this.A(modelObject)) {
                        return true;
                    }
                }
                case '[': {
                    if (this.A(sb)) {
                        return true;
                    }
                }
                case 'D': {
                    if (this.D()) {
                        return true;
                    }
                }
                case 'E': {
                    if (this.A()) {
                        return true;
                    }
                    break;
                }
            }
            throw this.C("Illegal declaration.");
        }
        if (g == '?') {
            if (this.B(modelObject)) {
                return true;
            }
            throw this.C("Illegal declaration.");
        }
        else {
            --this.C;
            final IModelObject h = this.H();
            if (h != null) {
                if (this.J) {
                    h.setAttribute("!position", sb.length());
                }
                modelObject.addChild(h);
                return true;
            }
            return false;
        }
    }
    
    private void C(final StringBuilder sb) throws IOException, ParseException {
        char c = this.G();
        if (c == '#') {
            final StringBuilder sb2 = new StringBuilder();
            final char g = this.G();
            if (g == 'x') {
                final char g2 = this.G();
                while (g2 != ';') {
                    if (!C(g2)) {
                        throw this.C("Expected hex digit character.");
                    }
                    sb2.append(g2);
                }
                sb.append((char)Integer.parseInt(sb2.toString(), 16));
            }
            else {
                while (g != ';') {
                    if (!D(g)) {
                        throw this.C("Expected digit character.");
                    }
                    sb2.append(g);
                }
                sb.append((char)Integer.parseInt(sb2.toString()));
            }
        }
        else {
            final StringBuilder sb3 = new StringBuilder();
            while (c != ';' && !this.I) {
                sb3.append(c);
                c = this.G();
            }
            final String lookupEntity = this.lookupEntity(sb3.toString());
            if (lookupEntity == null) {
                throw this.C("Undefined entity.");
            }
            sb.append(lookupEntity);
        }
    }
    
    private final boolean A() throws IOException, ParseException {
        if (!this.A("NTITY")) {
            return false;
        }
        this.E();
        if (this.I) {
            return false;
        }
        --this.C;
        final StringBuilder sb = new StringBuilder();
        if (!this.B(sb)) {
            return false;
        }
        final String string = sb.toString();
        sb.setLength(0);
        final char e = this.E();
        if (e != '\'' && e != '\"') {
            throw this.C("Expected ' or \" character.");
        }
        for (char c = this.G(); c != e; c = this.G()) {
            if (c == '<') {
                throw this.C("Illegal character.");
            }
            if (c == '&') {
                this.C(sb);
            }
            else {
                sb.append(c);
            }
        }
        this.G.put(string, sb.toString());
        return true;
    }
    
    private final boolean D() throws IOException, ParseException {
        if (!this.A("OCTYPE")) {
            return false;
        }
        char c = this.G();
        if (this.I) {
            return false;
        }
        while (c != '>') {
            c = this.G();
            if (this.I) {
                return false;
            }
        }
        return true;
    }
    
    private final boolean A(final StringBuilder sb) throws IOException, ParseException {
        if (!this.A("CDATA[")) {
            return false;
        }
        char c = this.G();
        if (this.I) {
            return false;
        }
        while (I(c) && !this.I && (c != ']' || !this.B("]>"))) {
            sb.append(c);
            c = this.G();
        }
        --this.C;
        return true;
    }
    
    private final boolean B(final IModelObject modelObject) throws IOException, ParseException {
        final StringBuilder value = new StringBuilder();
        value.append('?');
        if (!this.B(value)) {
            throw this.C("Expected processing-instruction name.");
        }
        final IModelObject object = this.E.createObject(null, value.toString());
        value.setLength(0);
        char c = this.E();
        if (this.I) {
            return false;
        }
        while (I(c) && !this.I) {
            if (c == '?') {
                final char g = this.G();
                if (this.I) {
                    return false;
                }
                if (g == '>') {
                    break;
                }
                value.append('?');
                value.append(g);
            }
            else {
                value.append(c);
            }
            c = this.G();
        }
        object.setValue(value);
        modelObject.addChild(object);
        return true;
    }
    
    private final boolean A(final IModelObject modelObject) throws IOException, ParseException {
        final char g = this.G();
        if (this.I) {
            return false;
        }
        if (g != '-' || this.I) {
            return false;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("!--");
        char c = '\0';
        char c2 = this.G();
        if (this.I) {
            return false;
        }
        while (I(c2) && !this.I) {
            sb.append(c2);
            if (c2 == '-' && c == '-') {
                break;
            }
            c = c2;
            c2 = this.G();
        }
        if (this.G() != '>') {
            return false;
        }
        modelObject.addChild(this.E.createObject(null, sb.toString()));
        return true;
    }
    
    private final boolean A(final String s) throws IOException, ParseException {
        final char g = this.G();
        if (this.I || g != s.charAt(0)) {
            --this.C;
            return false;
        }
        for (int i = 1; i < s.length(); ++i) {
            final char g2 = this.G();
            if (this.I || g2 != s.charAt(i)) {
                throw this.C("Illegal character.");
            }
        }
        return true;
    }
    
    private final boolean B(final String s) throws IOException {
        this.F();
        char c = this.G();
        for (int n = 0; n < s.length() && !this.I; ++n) {
            if (c != s.charAt(n)) {
                this.C();
                return false;
            }
            c = this.G();
        }
        return true;
    }
    
    private final boolean B(final StringBuilder sb) throws IOException {
        final char g = this.G();
        if (this.I) {
            return false;
        }
        if (!F(g) && g != '_' && g != ':') {
            return false;
        }
        sb.append(g);
        char c = this.G();
        if (this.I) {
            return true;
        }
        while (A(c) && !this.I) {
            sb.append(c);
            c = this.G();
        }
        --this.C;
        return true;
    }
    
    private final char E() throws IOException {
        char c;
        for (c = this.G(); !this.I && G(c); c = this.G()) {}
        return c;
    }
    
    private final void F() {
        this.B = this.C;
    }
    
    private final void C() throws IOException {
        if (this.B == -1) {
            throw new IOException("Buffer overrun.");
        }
        this.C = this.B;
    }
    
    private final char G() throws IOException {
        char c = this.B();
        if (c == '\r') {
            c = this.B();
            if (c != '\n') {
                --this.C;
                return '\n';
            }
        }
        return c;
    }
    
    private final char B() throws IOException {
        if (this.C >= this.A) {
            if (this.B < 0) {
                if (this.A > 0) {
                    this.D[0] = this.D[this.A - 1];
                }
                this.A = this.F.read(this.D, 1, this.D.length - 1) + 1;
                if (this.A <= 0) {
                    this.I = true;
                    return '\0';
                }
                this.C = 1;
                this.H += this.A;
            }
            else if (this.B > 0) {
                System.arraycopy(this.D, this.B, this.D, 0, this.A - this.B);
                final int read = this.F.read(this.D, this.A, this.D.length - this.A);
                if (read < 0) {
                    this.I = true;
                    return '\0';
                }
                this.C -= this.B;
                this.B = 0;
                this.H += read;
            }
            else {
                this.B = -1;
            }
        }
        return this.D[this.C++];
    }
    
    private final ParseException C(final String s) {
        return new ParseException(s, this.H - this.A + this.C);
    }
    
    private static final boolean A(final char c) {
        switch (c) {
            case '-':
            case '.':
            case ':':
            case '_': {
                return true;
            }
            default: {
                return F(c) || D(c) || (B(c) || E(c));
            }
        }
    }
    
    private static final boolean G(final char c) {
        switch (c) {
            case '\t':
            case '\n':
            case '\r':
            case ' ': {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private static final boolean I(final char c) {
        return c == '\t' || c == '\n' || c == '\r' || (c >= ' ' && c <= '\ud7ff') || (c >= '\ue000' && c <= '\ufffd') || (c >= 65536 && c <= 1114111);
    }
    
    private static final boolean F(final char c) {
        return J(c) || H(c);
    }
    
    private static final boolean C(final char c) {
        return c >= '0' && c <= 'f' && (c <= '9' || c >= 'A') && (c <= 'F' || c >= 'a');
    }
    
    private static final boolean D(final char c) {
        return (c >= '0' && c <= '9') || (c >= '\u0660' && c <= '\u0f29' && ((c >= '\u0660' && c <= '\u0669') || (c >= '\u06f0' && c <= '\u06f9') || (c >= '\u0966' && c <= '\u096f') || (c >= '\u09e6' && c <= '\u09ef') || (c >= '\u0a66' && c <= '\u0a6f') || (c >= '\u0ae6' && c <= '\u0aef') || (c >= '\u0b66' && c <= '\u0b6f') || (c >= '\u0be7' && c <= '\u0bef') || (c >= '\u0c66' && c <= '\u0c6f') || (c >= '\u0ce6' && c <= '\u0cef') || (c >= '\u0d66' && c <= '\u0d6f') || (c >= '\u0e50' && c <= '\u0e59') || (c >= '\u0ed0' && c <= '\u0ed9') || (c >= '\u0f20' && c <= '\u0f29')));
    }
    
    private static final boolean E(final char c) {
        return c >= '·' && (c == '·' || c == '\u02d0' || c == '\u02d1' || c == '\u0387' || c == '\u0640' || c == '\u0e46' || c == '\u0ec6' || c == '\u3005' || (c >= '\u3031' && c <= '\u3035') || (c >= '\u309d' && c <= '\u309e') || (c >= '\u30fc' && c <= '\u30fe'));
    }
    
    private static final boolean B(final char c) {
        if (c <= '\u05c4') {
            if (c < '\u0300') {
                return false;
            }
            if (c >= '\u0300' && c <= '\u0345') {
                return true;
            }
            if (c >= '\u0360' && c <= '\u0361') {
                return true;
            }
            if (c >= '\u0483' && c <= '\u0486') {
                return true;
            }
            if (c >= '\u0591' && c <= '\u05a1') {
                return true;
            }
            if (c >= '\u05a3' && c <= '\u05b9') {
                return true;
            }
            if (c >= '\u05bb' && c <= '\u05bd') {
                return true;
            }
            if (c == '\u05bf') {
                return true;
            }
            if (c >= '\u05c1' && c <= '\u05c2') {
                return true;
            }
            if (c == '\u05c4') {
                return true;
            }
        }
        else if (c <= '\u09e3') {
            if (c < '\u064b') {
                return false;
            }
            if (c >= '\u064b' && c <= '\u0652') {
                return true;
            }
            if (c == '\u0670') {
                return true;
            }
            if (c >= '\u06d6' && c <= '\u06dc') {
                return true;
            }
            if (c >= '\u06dd' && c <= '\u06df') {
                return true;
            }
            if (c >= '\u06e0' && c <= '\u06e4') {
                return true;
            }
            if (c >= '\u06e7' && c <= '\u06e8') {
                return true;
            }
            if (c >= '\u06ea' && c <= '\u06ed') {
                return true;
            }
            if (c >= '\u0901' && c <= '\u0903') {
                return true;
            }
            if (c == '\u093c') {
                return true;
            }
            if (c >= '\u093e' && c <= '\u094c') {
                return true;
            }
            if (c == '\u094d') {
                return true;
            }
            if (c >= '\u0951' && c <= '\u0954') {
                return true;
            }
            if (c >= '\u0962' && c <= '\u0963') {
                return true;
            }
            if (c >= '\u0981' && c <= '\u0983') {
                return true;
            }
            if (c == '\u09bc') {
                return true;
            }
            if (c == '\u09be') {
                return true;
            }
            if (c == '\u09bf') {
                return true;
            }
            if (c >= '\u09c0' && c <= '\u09c4') {
                return true;
            }
            if (c >= '\u09c7' && c <= '\u09c8') {
                return true;
            }
            if (c >= '\u09cb' && c <= '\u09cd') {
                return true;
            }
            if (c == '\u09d7') {
                return true;
            }
            if (c >= '\u09e2' && c <= '\u09e3') {
                return true;
            }
        }
        else if (c <= '\u0acd') {
            if (c < '\u0a02') {
                return false;
            }
            if (c == '\u0a02') {
                return true;
            }
            if (c == '\u0a3c') {
                return true;
            }
            if (c == '\u0a3e') {
                return true;
            }
            if (c == '\u0a3f') {
                return true;
            }
            if (c >= '\u0a40' && c <= '\u0a42') {
                return true;
            }
            if (c >= '\u0a47' && c <= '\u0a48') {
                return true;
            }
            if (c >= '\u0a4b' && c <= '\u0a4d') {
                return true;
            }
            if (c >= '\u0a70' && c <= '\u0a71') {
                return true;
            }
            if (c >= '\u0a81' && c <= '\u0a83') {
                return true;
            }
            if (c == '\u0abc') {
                return true;
            }
            if (c >= '\u0abe' && c <= '\u0ac5') {
                return true;
            }
            if (c >= '\u0ac7' && c <= '\u0ac9') {
                return true;
            }
            if (c >= '\u0acb' && c <= '\u0acd') {
                return true;
            }
        }
        else if (c <= '\u0cd6') {
            if (c < '\u0b01') {
                return false;
            }
            if (c >= '\u0b01' && c <= '\u0b03') {
                return true;
            }
            if (c == '\u0b3c') {
                return true;
            }
            if (c >= '\u0b3e' && c <= '\u0b43') {
                return true;
            }
            if (c >= '\u0b47' && c <= '\u0b48') {
                return true;
            }
            if (c >= '\u0b4b' && c <= '\u0b4d') {
                return true;
            }
            if (c >= '\u0b56' && c <= '\u0b57') {
                return true;
            }
            if (c >= '\u0b82' && c <= '\u0b83') {
                return true;
            }
            if (c >= '\u0bbe' && c <= '\u0bc2') {
                return true;
            }
            if (c >= '\u0bc6' && c <= '\u0bc8') {
                return true;
            }
            if (c >= '\u0bca' && c <= '\u0bcd') {
                return true;
            }
            if (c == '\u0bd7') {
                return true;
            }
            if (c >= '\u0c01' && c <= '\u0c03') {
                return true;
            }
            if (c >= '\u0c3e' && c <= '\u0c44') {
                return true;
            }
            if (c >= '\u0c46' && c <= '\u0c48') {
                return true;
            }
            if (c >= '\u0c4a' && c <= '\u0c4d') {
                return true;
            }
            if (c >= '\u0c55' && c <= '\u0c56') {
                return true;
            }
            if (c >= '\u0c82' && c <= '\u0c83') {
                return true;
            }
            if (c >= '\u0cbe' && c <= '\u0cc4') {
                return true;
            }
            if (c >= '\u0cc6' && c <= '\u0cc8') {
                return true;
            }
            if (c >= '\u0cca' && c <= '\u0ccd') {
                return true;
            }
            if (c >= '\u0cd5' && c <= '\u0cd6') {
                return true;
            }
        }
        else if (c <= '\u0fb9') {
            if (c < '\u0d02') {
                return false;
            }
            if (c >= '\u0d02' && c <= '\u0d03') {
                return true;
            }
            if (c >= '\u0d3e' && c <= '\u0d43') {
                return true;
            }
            if (c >= '\u0d46' && c <= '\u0d48') {
                return true;
            }
            if (c >= '\u0d4a' && c <= '\u0d4d') {
                return true;
            }
            if (c == '\u0d57') {
                return true;
            }
            if (c == '\u0e31') {
                return true;
            }
            if (c >= '\u0e34' && c <= '\u0e3a') {
                return true;
            }
            if (c >= '\u0e47' && c <= '\u0e4e') {
                return true;
            }
            if (c == '\u0eb1') {
                return true;
            }
            if (c >= '\u0eb4' && c <= '\u0eb9') {
                return true;
            }
            if (c >= '\u0ebb' && c <= '\u0ebc') {
                return true;
            }
            if (c >= '\u0ec8' && c <= '\u0ecd') {
                return true;
            }
            if (c >= '\u0f18' && c <= '\u0f19') {
                return true;
            }
            if (c == '\u0f35') {
                return true;
            }
            if (c == '\u0f37') {
                return true;
            }
            if (c == '\u0f39') {
                return true;
            }
            if (c == '\u0f3e') {
                return true;
            }
            if (c == '\u0f3f') {
                return true;
            }
            if (c >= '\u0f71' && c <= '\u0f84') {
                return true;
            }
            if (c >= '\u0f86' && c <= '\u0f8b') {
                return true;
            }
            if (c >= '\u0f90' && c <= '\u0f95') {
                return true;
            }
            if (c == '\u0f97') {
                return true;
            }
            if (c >= '\u0f99' && c <= '\u0fad') {
                return true;
            }
            if (c >= '\u0fb1' && c <= '\u0fb7') {
                return true;
            }
            if (c == '\u0fb9') {
                return true;
            }
        }
        else if (c <= '\u309a') {
            if (c < '\u20d0') {
                return false;
            }
            if (c >= '\u20d0' && c <= '\u20dc') {
                return true;
            }
            if (c == '\u20e1') {
                return true;
            }
            if (c >= '\u302a' && c <= '\u302f') {
                return true;
            }
            if (c == '\u3099') {
                return true;
            }
            if (c == '\u309a') {
                return true;
            }
        }
        return false;
    }
    
    private static final boolean H(final char c) {
        return c >= '\u3007' && ((c >= '\u4e00' && c <= '\u9fa5') || c == '\u3007' || (c >= '\u3021' && c <= '\u3029'));
    }
    
    private static final boolean J(final char c) {
        if (c <= '\u0100') {
            if (c < 'A') {
                return false;
            }
            if (c >= 'A' && c <= 'Z') {
                return true;
            }
            if (c >= 'a' && c <= 'z') {
                return true;
            }
            if (c >= '\u00c0' && c <= '\u00d6') {
                return true;
            }
            if (c >= '\u00d8' && c <= '\u00f6') {
                return true;
            }
            if (c >= '\u00f8') {
                return true;
            }
        }
        else if (c <= '\u0217') {
            if (c >= '\u0100' && c <= '\u0131') {
                return true;
            }
            if (c >= '\u0134' && c <= '\u013e') {
                return true;
            }
            if (c >= '\u0141' && c <= '\u0148') {
                return true;
            }
            if (c >= '\u014a' && c <= '\u017e') {
                return true;
            }
            if (c >= '\u0180' && c <= '\u01c3') {
                return true;
            }
            if (c >= '\u01cd' && c <= '\u01f0') {
                return true;
            }
            if (c >= '\u01f4' && c <= '\u01f5') {
                return true;
            }
            if (c >= '\u01fa') {
                return true;
            }
        }
        else if (c <= '\u03f3') {
            if (c < '\u0250') {
                return false;
            }
            if (c >= '\u0250' && c <= '\u02a8') {
                return true;
            }
            if (c >= '\u02bb' && c <= '\u02c1') {
                return true;
            }
            if (c == '\u0386') {
                return true;
            }
            if (c >= '\u0388' && c <= '\u038a') {
                return true;
            }
            if (c == '\u038c') {
                return true;
            }
            if (c >= '\u038e' && c <= '\u03a1') {
                return true;
            }
            if (c >= '\u03a3' && c <= '\u03ce') {
                return true;
            }
            if (c >= '\u03d0' && c <= '\u03d6') {
                return true;
            }
            if (c == '\u03da') {
                return true;
            }
            if (c == '\u03dc') {
                return true;
            }
            if (c == '\u03de') {
                return true;
            }
            if (c == '\u03e0') {
                return true;
            }
            if (c >= '\u03e2') {
                return true;
            }
        }
        else if (c <= '\u04f9') {
            if (c < '\u0401') {
                return false;
            }
            if (c >= '\u0401' && c <= '\u040c') {
                return true;
            }
            if (c >= '\u040e' && c <= '\u044f') {
                return true;
            }
            if (c >= '\u0451' && c <= '\u045c') {
                return true;
            }
            if (c >= '\u045e' && c <= '\u0481') {
                return true;
            }
            if (c >= '\u0490' && c <= '\u04c4') {
                return true;
            }
            if (c >= '\u04c7' && c <= '\u04c8') {
                return true;
            }
            if (c >= '\u04cb' && c <= '\u04cc') {
                return true;
            }
            if (c >= '\u04d0' && c <= '\u04eb') {
                return true;
            }
            if (c >= '\u04ee' && c <= '\u04f5') {
                return true;
            }
            if (c >= '\u04f8') {
                return true;
            }
        }
        else if (c <= '\u06e6') {
            if (c < '\u0531') {
                return false;
            }
            if (c >= '\u0531' && c <= '\u0556') {
                return true;
            }
            if (c == '\u0559') {
                return true;
            }
            if (c >= '\u0561' && c <= '\u0586') {
                return true;
            }
            if (c >= '\u05d0' && c <= '\u05ea') {
                return true;
            }
            if (c >= '\u05f0' && c <= '\u05f2') {
                return true;
            }
            if (c >= '\u0621' && c <= '\u063a') {
                return true;
            }
            if (c >= '\u0641' && c <= '\u064a') {
                return true;
            }
            if (c >= '\u0671' && c <= '\u06b7') {
                return true;
            }
            if (c >= '\u06ba' && c <= '\u06be') {
                return true;
            }
            if (c >= '\u06c0' && c <= '\u06ce') {
                return true;
            }
            if (c >= '\u06d0' && c <= '\u06d3') {
                return true;
            }
            if (c == '\u06d5') {
                return true;
            }
            if (c >= '\u06e5') {
                return true;
            }
        }
        else if (c <= '\u09f1') {
            if (c < '\u0905') {
                return false;
            }
            if (c >= '\u0905' && c <= '\u0939') {
                return true;
            }
            if (c == '\u093d') {
                return true;
            }
            if (c >= '\u0958' && c <= '\u0961') {
                return true;
            }
            if (c >= '\u0985' && c <= '\u098c') {
                return true;
            }
            if (c >= '\u098f' && c <= '\u0990') {
                return true;
            }
            if (c >= '\u0993' && c <= '\u09a8') {
                return true;
            }
            if (c >= '\u09aa' && c <= '\u09b0') {
                return true;
            }
            if (c == '\u09b2') {
                return true;
            }
            if (c >= '\u09b6' && c <= '\u09b9') {
                return true;
            }
            if (c >= '\u09dc' && c <= '\u09dd') {
                return true;
            }
            if (c >= '\u09df' && c <= '\u09e1') {
                return true;
            }
            if (c >= '\u09f0') {
                return true;
            }
        }
        else if (c <= '\u0ae0') {
            if (c < '\u0a05') {
                return false;
            }
            if (c >= '\u0a05' && c <= '\u0a0a') {
                return true;
            }
            if (c >= '\u0a0f' && c <= '\u0a10') {
                return true;
            }
            if (c >= '\u0a13' && c <= '\u0a28') {
                return true;
            }
            if (c >= '\u0a2a' && c <= '\u0a30') {
                return true;
            }
            if (c >= '\u0a32' && c <= '\u0a33') {
                return true;
            }
            if (c >= '\u0a35' && c <= '\u0a36') {
                return true;
            }
            if (c >= '\u0a38' && c <= '\u0a39') {
                return true;
            }
            if (c >= '\u0a59' && c <= '\u0a5c') {
                return true;
            }
            if (c == '\u0a5e') {
                return true;
            }
            if (c >= '\u0a72' && c <= '\u0a74') {
                return true;
            }
            if (c >= '\u0a85' && c <= '\u0a8b') {
                return true;
            }
            if (c == '\u0a8d') {
                return true;
            }
            if (c >= '\u0a8f' && c <= '\u0a91') {
                return true;
            }
            if (c >= '\u0a93' && c <= '\u0aa8') {
                return true;
            }
            if (c >= '\u0aaa' && c <= '\u0ab0') {
                return true;
            }
            if (c >= '\u0ab2' && c <= '\u0ab3') {
                return true;
            }
            if (c >= '\u0ab5' && c <= '\u0ab9') {
                return true;
            }
            if (c == '\u0abd') {
                return true;
            }
            if (c == '\u0ae0') {
                return true;
            }
        }
        else if (c <= '\u0bb9') {
            if (c < '\u0b05') {
                return false;
            }
            if (c >= '\u0b05' && c <= '\u0b0c') {
                return true;
            }
            if (c >= '\u0b0f' && c <= '\u0b10') {
                return true;
            }
            if (c >= '\u0b13' && c <= '\u0b28') {
                return true;
            }
            if (c >= '\u0b2a' && c <= '\u0b30') {
                return true;
            }
            if (c >= '\u0b32' && c <= '\u0b33') {
                return true;
            }
            if (c >= '\u0b36' && c <= '\u0b39') {
                return true;
            }
            if (c == '\u0b3d') {
                return true;
            }
            if (c >= '\u0b5c' && c <= '\u0b5d') {
                return true;
            }
            if (c >= '\u0b5f' && c <= '\u0b61') {
                return true;
            }
            if (c >= '\u0b85' && c <= '\u0b8a') {
                return true;
            }
            if (c >= '\u0b8e' && c <= '\u0b90') {
                return true;
            }
            if (c >= '\u0b92' && c <= '\u0b95') {
                return true;
            }
            if (c >= '\u0b99' && c <= '\u0b9a') {
                return true;
            }
            if (c == '\u0b9c') {
                return true;
            }
            if (c >= '\u0b9e' && c <= '\u0b9f') {
                return true;
            }
            if (c >= '\u0ba3' && c <= '\u0ba4') {
                return true;
            }
            if (c >= '\u0ba8' && c <= '\u0baa') {
                return true;
            }
            if (c >= '\u0bae' && c <= '\u0bb5') {
                return true;
            }
            if (c >= '\u0bb7') {
                return true;
            }
        }
        else if (c <= '\u0ce1') {
            if (c < '\u0c05') {
                return false;
            }
            if (c >= '\u0c05' && c <= '\u0c0c') {
                return true;
            }
            if (c >= '\u0c0e' && c <= '\u0c10') {
                return true;
            }
            if (c >= '\u0c12' && c <= '\u0c28') {
                return true;
            }
            if (c >= '\u0c2a' && c <= '\u0c33') {
                return true;
            }
            if (c >= '\u0c35' && c <= '\u0c39') {
                return true;
            }
            if (c >= '\u0c60' && c <= '\u0c61') {
                return true;
            }
            if (c >= '\u0c85' && c <= '\u0c8c') {
                return true;
            }
            if (c >= '\u0c8e' && c <= '\u0c90') {
                return true;
            }
            if (c >= '\u0c92' && c <= '\u0ca8') {
                return true;
            }
            if (c >= '\u0caa' && c <= '\u0cb3') {
                return true;
            }
            if (c >= '\u0cb5' && c <= '\u0cb9') {
                return true;
            }
            if (c == '\u0cde') {
                return true;
            }
            if (c >= '\u0ce0') {
                return true;
            }
        }
        else if (c <= '\u0d61') {
            if (c < '\u0d05') {
                return false;
            }
            if (c >= '\u0d05' && c <= '\u0d0c') {
                return true;
            }
            if (c >= '\u0d0e' && c <= '\u0d10') {
                return true;
            }
            if (c >= '\u0d12' && c <= '\u0d28') {
                return true;
            }
            if (c >= '\u0d2a' && c <= '\u0d39') {
                return true;
            }
            if (c >= '\u0d60' && c <= '\u0d61') {
                return true;
            }
        }
        else if (c <= '\u0ec4') {
            if (c < '\u0e01') {
                return false;
            }
            if (c >= '\u0e01' && c <= '\u0e2e') {
                return true;
            }
            if (c == '\u0e30') {
                return true;
            }
            if (c >= '\u0e32' && c <= '\u0e33') {
                return true;
            }
            if (c >= '\u0e40' && c <= '\u0e45') {
                return true;
            }
            if (c >= '\u0e81' && c <= '\u0e82') {
                return true;
            }
            if (c == '\u0e84') {
                return true;
            }
            if (c >= '\u0e87' && c <= '\u0e88') {
                return true;
            }
            if (c == '\u0e8a') {
                return true;
            }
            if (c == '\u0e8d') {
                return true;
            }
            if (c >= '\u0e94' && c <= '\u0e97') {
                return true;
            }
            if (c >= '\u0e99' && c <= '\u0e9f') {
                return true;
            }
            if (c >= '\u0ea1' && c <= '\u0ea3') {
                return true;
            }
            if (c == '\u0ea5') {
                return true;
            }
            if (c == '\u0ea7') {
                return true;
            }
            if (c >= '\u0eaa' && c <= '\u0eab') {
                return true;
            }
            if (c >= '\u0ead' && c <= '\u0eae') {
                return true;
            }
            if (c == '\u0eb0') {
                return true;
            }
            if (c >= '\u0eb2' && c <= '\u0eb3') {
                return true;
            }
            if (c == '\u0ebd') {
                return true;
            }
            if (c >= '\u0ec0') {
                return true;
            }
        }
        else if (c <= '\u11f9') {
            if (c < '\u0f40') {
                return false;
            }
            if (c >= '\u0f40' && c <= '\u0f47') {
                return true;
            }
            if (c >= '\u0f49' && c <= '\u0f69') {
                return true;
            }
            if (c >= '\u10a0' && c <= '\u10c5') {
                return true;
            }
            if (c >= '\u10d0' && c <= '\u10f6') {
                return true;
            }
            if (c == '\u1100') {
                return true;
            }
            if (c >= '\u1102' && c <= '\u1103') {
                return true;
            }
            if (c >= '\u1105' && c <= '\u1107') {
                return true;
            }
            if (c == '\u1109') {
                return true;
            }
            if (c >= '\u110b' && c <= '\u110c') {
                return true;
            }
            if (c >= '\u110e' && c <= '\u1112') {
                return true;
            }
            if (c == '\u113c') {
                return true;
            }
            if (c == '\u113e') {
                return true;
            }
            if (c == '\u1140') {
                return true;
            }
            if (c == '\u114c') {
                return true;
            }
            if (c == '\u114e') {
                return true;
            }
            if (c == '\u1150') {
                return true;
            }
            if (c >= '\u1154' && c <= '\u1155') {
                return true;
            }
            if (c == '\u1159') {
                return true;
            }
            if (c >= '\u115f' && c <= '\u1161') {
                return true;
            }
            if (c == '\u1163') {
                return true;
            }
            if (c == '\u1165') {
                return true;
            }
            if (c == '\u1167') {
                return true;
            }
            if (c == '\u1169') {
                return true;
            }
            if (c >= '\u116d' && c <= '\u116e') {
                return true;
            }
            if (c >= '\u1172' && c <= '\u1173') {
                return true;
            }
            if (c == '\u1175') {
                return true;
            }
            if (c == '\u119e') {
                return true;
            }
            if (c == '\u11a8') {
                return true;
            }
            if (c == '\u11ab') {
                return true;
            }
            if (c >= '\u11ae' && c <= '\u11af') {
                return true;
            }
            if (c >= '\u11b7' && c <= '\u11b8') {
                return true;
            }
            if (c == '\u11ba') {
                return true;
            }
            if (c >= '\u11bc' && c <= '\u11c2') {
                return true;
            }
            if (c == '\u11eb') {
                return true;
            }
            if (c == '\u11f0') {
                return true;
            }
            if (c == '\u11f9') {
                return true;
            }
        }
        else if (c <= '\u1ffc') {
            if (c < '\u1e00') {
                return false;
            }
            if (c >= '\u1e00' && c <= '\u1e9b') {
                return true;
            }
            if (c >= '\u1ea0' && c <= '\u1ef9') {
                return true;
            }
            if (c >= '\u1f00' && c <= '\u1f15') {
                return true;
            }
            if (c >= '\u1f18' && c <= '\u1f1d') {
                return true;
            }
            if (c >= '\u1f20' && c <= '\u1f45') {
                return true;
            }
            if (c >= '\u1f48' && c <= '\u1f4d') {
                return true;
            }
            if (c >= '\u1f50' && c <= '\u1f57') {
                return true;
            }
            if (c == '\u1f59') {
                return true;
            }
            if (c == '\u1f5b') {
                return true;
            }
            if (c == '\u1f5d') {
                return true;
            }
            if (c >= '\u1f5f' && c <= '\u1f7d') {
                return true;
            }
            if (c >= '\u1f80' && c <= '\u1fb4') {
                return true;
            }
            if (c >= '\u1fb6' && c <= '\u1fbc') {
                return true;
            }
            if (c == '\u1fbe') {
                return true;
            }
            if (c >= '\u1fc2' && c <= '\u1fc4') {
                return true;
            }
            if (c >= '\u1fc6' && c <= '\u1fcc') {
                return true;
            }
            if (c >= '\u1fd0' && c <= '\u1fd3') {
                return true;
            }
            if (c >= '\u1fd6' && c <= '\u1fdb') {
                return true;
            }
            if (c >= '\u1fe0' && c <= '\u1fec') {
                return true;
            }
            if (c >= '\u1ff2' && c <= '\u1ff4') {
                return true;
            }
            if (c >= '\u1ff6') {
                return true;
            }
        }
        else if (c <= '\ud7a3') {
            if (c < '\u2126') {
                return false;
            }
            if (c == '\u2126') {
                return true;
            }
            if (c >= '\u212a' && c <= '\u212b') {
                return true;
            }
            if (c == '\u212e') {
                return true;
            }
            if (c >= '\u2180' && c <= '\u2182') {
                return true;
            }
            if (c >= '\u3041' && c <= '\u3094') {
                return true;
            }
            if (c >= '\u30a1' && c <= '\u30fa') {
                return true;
            }
            if (c >= '\u3105' && c <= '\u312c') {
                return true;
            }
            if (c >= '\uac00') {
                return true;
            }
        }
        return false;
    }
    
    public static void main(final String[] array) throws Exception {
        File[] listFiles;
        for (int length = (listFiles = new File(".").listFiles()).length, i = 0; i < length; ++i) {
            final File file = listFiles[i];
            if (file.getName().endsWith("test.xml")) {
                final FileReader fileReader = new FileReader(file);
                System.out.println(file);
                try {
                    System.out.println(((ModelObject)new XmlParser(fileReader).parse()).toXml());
                }
                catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
