import java.io.IOException;
import java.io.Writer;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import java.io.Reader;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_eA
{
    private Hashtable a;
    public Vector a;
    public String a;
    public String b;
    private Hashtable b;
    private boolean a;
    private boolean b;
    private char a;
    private Reader a;
    private int a;
    
    public rp_eA() {
        this(new Hashtable(), false, true, true);
    }
    
    private rp_eA(final Hashtable b, final boolean b2, final boolean b3, final boolean a) {
        this.b = b2;
        this.a = a;
        this.a = null;
        this.b = "";
        this.a = new Hashtable();
        this.a = new Vector();
        this.b = b;
        final Enumeration<Object> keys = this.b.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            final Object value;
            if ((value = this.b.get(nextElement)) instanceof String) {
                this.b.put(nextElement, ((String)value).toCharArray());
            }
        }
        if (b3) {
            this.b.put("amp", new char[] { '&' });
            this.b.put("quot", new char[] { '\"' });
            this.b.put("apos", new char[] { '\'' });
            this.b.put("lt", new char[] { '<' });
            this.b.put("gt", new char[] { '>' });
        }
    }
    
    public final Vector a() {
        try {
            return (Vector)this.a.clone();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final String a(String upperCase, String s) {
        final rp_eA rp_eA = this;
        final String s2 = upperCase;
        s = s;
        upperCase = s2;
        this = rp_eA;
        if (rp_eA.a) {
            upperCase = upperCase.toUpperCase();
        }
        String value;
        if ((value = this.a.get(upperCase)) == null) {
            value = s;
        }
        return value;
    }
    
    public final int a(String upperCase, final int n) {
        if (this.a) {
            upperCase = upperCase.toUpperCase();
        }
        final String s;
        if ((s = this.a.get(upperCase)) == null) {
            return n;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            throw this.a(upperCase, s);
        }
    }
    
    public final double a(String upperCase, double n) {
        if (this.a) {
            upperCase = upperCase.toUpperCase();
        }
        if ((n = this.a.get(upperCase)) == null) {
            return 0.0;
        }
        try {
            return Double.valueOf((String)n);
        }
        catch (NumberFormatException ex) {
            throw this.a(upperCase, (String)n);
        }
    }
    
    public final String a(String s) {
        final rp_eA rp_eA = this;
        s = s;
        this = rp_eA;
        return rp_eA.a(s, (String)null);
    }
    
    public final void a(Reader a) {
        final rp_eA rp_eA = this;
        a = a;
        this = rp_eA;
        rp_eA.a = '\0';
        this.a = a;
        this.a = 1;
        while (this.a() == '<') {
            final char b;
            if ((b = this.b()) != '!' && b != '?') {
                this.a = b;
                this.a(this);
                return;
            }
            this.a(0);
        }
        throw this.a("<");
    }
    
    public final String toString() {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
            this.a(outputStreamWriter);
            outputStreamWriter.flush();
            return new String(byteArrayOutputStream.toByteArray());
        }
        catch (IOException ex) {
            return super.toString();
        }
    }
    
    private void a(final Writer writer) {
        if (this.a == null) {
            a(writer, this.b);
            return;
        }
        writer.write(60);
        writer.write(this.a);
        if (!this.a.isEmpty()) {
            final Enumeration<String> keys = (Enumeration<String>)this.a.keys();
            while (keys.hasMoreElements()) {
                writer.write(32);
                final String s = keys.nextElement();
                final String s2 = this.a.get(s);
                writer.write(s);
                writer.write(61);
                writer.write(34);
                a(writer, s2);
                writer.write(34);
            }
        }
        if (this.b != null && this.b.length() > 0) {
            writer.write(62);
            a(writer, this.b);
        }
        else {
            if (this.a.isEmpty()) {
                writer.write(47);
                writer.write(62);
                return;
            }
            writer.write(62);
            final Enumeration<rp_eA> elements = (Enumeration<rp_eA>)this.a.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().a(writer);
            }
        }
        writer.write(60);
        writer.write(47);
        writer.write(this.a);
        writer.write(62);
    }
    
    private static void a(final Writer writer, final String s) {
        for (int i = 0; i < s.length(); ++i) {
            final char char1;
            switch (char1 = s.charAt(i)) {
                case '<': {
                    writer.write(38);
                    writer.write(108);
                    writer.write(116);
                    writer.write(59);
                    break;
                }
                case '>': {
                    writer.write(38);
                    writer.write(103);
                    writer.write(116);
                    writer.write(59);
                    break;
                }
                case '&': {
                    writer.write(38);
                    writer.write(97);
                    writer.write(109);
                    writer.write(112);
                    writer.write(59);
                    break;
                }
                case '\"': {
                    writer.write(38);
                    writer.write(113);
                    writer.write(117);
                    writer.write(111);
                    writer.write(116);
                    writer.write(59);
                    break;
                }
                case '\'': {
                    writer.write(38);
                    writer.write(97);
                    writer.write(112);
                    writer.write(111);
                    writer.write(115);
                    writer.write(59);
                    break;
                }
                default: {
                    final char c;
                    if ((c = char1) < ' ' || c > '~') {
                        writer.write(38);
                        writer.write(35);
                        writer.write(120);
                        writer.write(Integer.toString(c, 16));
                        writer.write(59);
                        break;
                    }
                    writer.write(char1);
                    break;
                }
            }
        }
    }
    
    private void a(final StringBuffer sb) {
        char b;
        while (((b = this.b()) >= 'A' && b <= 'Z') || (b >= 'a' && b <= 'z') || (b >= '0' && b <= '9') || b == '_' || b == '.' || b == ':' || b == '-' || b > '~') {
            sb.append(b);
        }
        final rp_eA rp_eA = this;
        final char a = b;
        this = rp_eA;
        rp_eA.a = a;
    }
    
    private char a() {
        while (true) {
            final char b;
            switch (b = this.b()) {
                case '\t':
                case '\n':
                case '\r':
                case ' ': {
                    continue;
                }
                default: {
                    return b;
                }
            }
        }
    }
    
    private char a(final StringBuffer sb) {
        while (true) {
            final char b;
            switch (b = this.b()) {
                case '\t':
                case '\n':
                case ' ': {
                    sb.append(b);
                }
                case '\r': {
                    continue;
                }
                default: {
                    return b;
                }
            }
        }
    }
    
    private void b(final StringBuffer sb) {
        final char b;
        if ((b = this.b()) != '\'' && b != '\"') {
            throw this.a("' or \"");
        }
        char b2;
        while ((b2 = this.b()) != b) {
            if (b2 == '&') {
                this.d(sb);
            }
            else {
                sb.append(b2);
            }
        }
    }
    
    private void c(final StringBuffer sb) {
        char b2;
        while (true) {
            final char b;
            if ((b = this.b()) == '<') {
                if ((b2 = this.b()) != '!') {
                    break;
                }
                this.a(sb);
            }
            else if (b == '&') {
                this.d(sb);
            }
            else {
                sb.append(b);
            }
        }
        final rp_eA rp_eA = this;
        final char a = b2;
        this = rp_eA;
        rp_eA.a = a;
    }
    
    private boolean a(final StringBuffer sb) {
        final char b;
        if ((b = this.b()) != '[') {
            this.a = b;
            this.a(0);
            return false;
        }
        if (!this.a("CDATA[")) {
            this.a(1);
            return false;
        }
    Label_0043:
        while (true) {
            int i = 0;
            while (i < 3) {
                final char b2;
                switch (b2 = this.b()) {
                    case ']': {
                        if (i < 2) {
                            ++i;
                            continue;
                        }
                        sb.append(']');
                        continue;
                    }
                    case '>': {
                        if (i < 2) {
                            for (int j = 0; j < i; ++j) {
                                sb.append(']');
                            }
                            i = 0;
                            sb.append('>');
                            continue;
                        }
                        i = 3;
                        continue;
                    }
                    default: {
                        for (int k = 0; k < i; ++k) {
                            sb.append(']');
                        }
                        sb.append(b2);
                        continue Label_0043;
                    }
                }
            }
            break;
        }
        return true;
    }
    
    private void a() {
        int i = 2;
        while (i > 0) {
            if (this.b() == '-') {
                --i;
            }
            else {
                i = 2;
            }
        }
        if (this.b() != '>') {
            throw this.a(">");
        }
    }
    
    private void a(int n) {
        int i = 1;
        char c = '\0';
        if (n == 0) {
            final char b;
            if ((b = this.b()) == '[') {
                ++n;
            }
            else if (b == '-') {
                final char b2;
                if ((b2 = this.b()) == '[') {
                    ++n;
                }
                else if (b2 == ']') {
                    --n;
                }
                else if (b2 == '-') {
                    this.a();
                    return;
                }
            }
        }
        while (i > 0) {
            final char b3 = this.b();
            if (c == '\0') {
                if (b3 == '\"' || b3 == '\'') {
                    c = b3;
                }
                else if (n <= 0) {
                    if (b3 == '<') {
                        ++i;
                    }
                    else if (b3 == '>') {
                        --i;
                    }
                }
                if (b3 == '[') {
                    ++n;
                }
                else {
                    if (b3 != ']') {
                        continue;
                    }
                    --n;
                }
            }
            else {
                if (b3 != c) {
                    continue;
                }
                c = '\0';
            }
        }
    }
    
    private boolean a(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            if (this.b() != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    private char b() {
        if (this.a != '\0') {
            final char a = this.a;
            this.a = '\0';
            return a;
        }
        final int read;
        if ((read = this.a.read()) < 0) {
            this = this;
            throw new rp_u(this.a, this.a, "Unexpected end of data reached");
        }
        if (read == 10) {
            final rp_eA rp_eA = this;
            ++rp_eA.a;
            return '\n';
        }
        return (char)read;
    }
    
    private void a(final rp_eA rp_eA) {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        final String string = sb.toString();
        rp_eA.a = string;
        char a;
        for (a = this.a(); a != '>' && a != '/'; a = this.a()) {
            sb.setLength(0);
            this.a = a;
            this.a(sb);
            final String string2 = sb.toString();
            if (this.a() != '=') {
                throw this.a("=");
            }
            this.a = this.a();
            sb.setLength(0);
            this.b(sb);
            final String s = string2;
            final StringBuffer sb2 = sb;
            String upperCase = s;
            if (rp_eA.a) {
                upperCase = upperCase.toUpperCase();
            }
            rp_eA.a.put(upperCase, sb2.toString());
        }
        if (a == '/') {
            if (this.b() != '>') {
                throw this.a(">");
            }
        }
        else {
            sb.setLength(0);
            char c = this.a(sb);
            boolean b = false;
            Label_0312: {
                if (c != '<') {
                    this.a = c;
                    this.c(sb);
                }
                else {
                    while ((c = this.b()) == '!') {
                        if (this.a(sb)) {
                            b = true;
                            this.c(sb);
                            break Label_0312;
                        }
                        if ((c = this.a(sb)) != '<') {
                            this.a = c;
                            this.c(sb);
                            break Label_0312;
                        }
                    }
                    sb.setLength(0);
                }
            }
            if (sb.length() == 0 && !b) {
                while (c != '/') {
                    if (c == '!') {
                        if (this.b() != '-') {
                            throw this.a("Comment or Element");
                        }
                        if (this.b() != '-') {
                            throw this.a("Comment or Element");
                        }
                        this.a();
                    }
                    else {
                        this.a = c;
                        final rp_eA rp_eA2 = new rp_eA(this.b, this.b, false, this.a);
                        this.a(rp_eA2);
                        rp_eA.a.addElement(rp_eA2);
                    }
                    if (this.a() != '<') {
                        throw this.a("<");
                    }
                    c = this.b();
                }
                this.a = c;
            }
            else if (this.b) {
                rp_eA.b = sb.toString().trim();
            }
            else {
                rp_eA.b = sb.toString();
            }
            if (this.b() != '/') {
                throw this.a("/");
            }
            this.a = this.a();
            if (!this.a(string)) {
                throw this.a(string);
            }
            if (this.a() != '>') {
                throw this.a(">");
            }
        }
    }
    
    private void d(final StringBuffer sb) {
        final StringBuffer sb2 = new StringBuffer();
        char b;
        while ((b = this.b()) != ';') {
            sb2.append(b);
        }
        final String string;
        if ((string = sb2.toString()).charAt(0) == '#') {
            char c;
            try {
                if (string.charAt(1) == 'x') {
                    c = (char)Integer.parseInt(string.substring(2), 16);
                }
                else {
                    c = (char)Integer.parseInt(string.substring(1), 10);
                }
            }
            catch (NumberFormatException ex) {
                throw this.b(string);
            }
            sb.append(c);
            return;
        }
        final char[] array;
        if ((array = this.b.get(string)) == null) {
            throw this.b(string);
        }
        sb.append(array);
    }
    
    private rp_u a(String string, final String s) {
        string = "Attribute \"" + string + "\" does not contain a valid " + "value (\"" + s + "\")";
        return new rp_u(this.a, this.a, string);
    }
    
    private rp_u a(String string) {
        string = "Expected: " + string;
        return new rp_u(this.a, this.a, string);
    }
    
    private rp_u b(String string) {
        string = "Unknown or invalid entity: &" + string + ";";
        return new rp_u(this.a, this.a, string);
    }
}
