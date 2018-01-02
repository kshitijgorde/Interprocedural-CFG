// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.output;

import org.jdom.Verifier;
import java.lang.reflect.Method;

public class Format implements Cloneable
{
    private static final String CVS_ID = "@(#) $RCSfile: Format.java,v $ $Revision: 1.14 $ $Date: 2009/07/23 05:54:23 $ $Name: jdom_1_1_1 $";
    private static final String STANDARD_INDENT = "  ";
    private static final String STANDARD_LINE_SEPARATOR = "\r\n";
    private static final String STANDARD_ENCODING = "UTF-8";
    String indent;
    String lineSeparator;
    String encoding;
    boolean omitDeclaration;
    boolean omitEncoding;
    boolean expandEmptyElements;
    boolean ignoreTrAXEscapingPIs;
    TextMode mode;
    EscapeStrategy escapeStrategy;
    static /* synthetic */ Class class$java$lang$String;
    
    public static Format getRawFormat() {
        return new Format();
    }
    
    public static Format getPrettyFormat() {
        final Format f = new Format();
        f.setIndent("  ");
        f.setTextMode(TextMode.TRIM);
        return f;
    }
    
    public static Format getCompactFormat() {
        final Format f = new Format();
        f.setTextMode(TextMode.NORMALIZE);
        return f;
    }
    
    private Format() {
        this.indent = null;
        this.lineSeparator = "\r\n";
        this.encoding = "UTF-8";
        this.omitDeclaration = false;
        this.omitEncoding = false;
        this.expandEmptyElements = false;
        this.ignoreTrAXEscapingPIs = false;
        this.mode = TextMode.PRESERVE;
        this.escapeStrategy = new DefaultEscapeStrategy(this.encoding);
    }
    
    public Format setEscapeStrategy(final EscapeStrategy strategy) {
        this.escapeStrategy = strategy;
        return this;
    }
    
    public EscapeStrategy getEscapeStrategy() {
        return this.escapeStrategy;
    }
    
    public Format setLineSeparator(final String separator) {
        this.lineSeparator = separator;
        return this;
    }
    
    public String getLineSeparator() {
        return this.lineSeparator;
    }
    
    public Format setOmitEncoding(final boolean omitEncoding) {
        this.omitEncoding = omitEncoding;
        return this;
    }
    
    public boolean getOmitEncoding() {
        return this.omitEncoding;
    }
    
    public Format setOmitDeclaration(final boolean omitDeclaration) {
        this.omitDeclaration = omitDeclaration;
        return this;
    }
    
    public boolean getOmitDeclaration() {
        return this.omitDeclaration;
    }
    
    public Format setExpandEmptyElements(final boolean expandEmptyElements) {
        this.expandEmptyElements = expandEmptyElements;
        return this;
    }
    
    public boolean getExpandEmptyElements() {
        return this.expandEmptyElements;
    }
    
    public void setIgnoreTrAXEscapingPIs(final boolean ignoreTrAXEscapingPIs) {
        this.ignoreTrAXEscapingPIs = ignoreTrAXEscapingPIs;
    }
    
    public boolean getIgnoreTrAXEscapingPIs() {
        return this.ignoreTrAXEscapingPIs;
    }
    
    public Format setTextMode(final TextMode mode) {
        this.mode = mode;
        return this;
    }
    
    public TextMode getTextMode() {
        return this.mode;
    }
    
    public Format setIndent(final String indent) {
        this.indent = indent;
        return this;
    }
    
    public String getIndent() {
        return this.indent;
    }
    
    public Format setEncoding(final String encoding) {
        this.encoding = encoding;
        this.escapeStrategy = new DefaultEscapeStrategy(encoding);
        return this;
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    protected Object clone() {
        Format format = null;
        try {
            format = (Format)super.clone();
        }
        catch (CloneNotSupportedException ex) {}
        return format;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    class DefaultEscapeStrategy implements EscapeStrategy
    {
        private int bits;
        Object encoder;
        Method canEncode;
        
        public DefaultEscapeStrategy(final String encoding) {
            if ("UTF-8".equalsIgnoreCase(encoding) || "UTF-16".equalsIgnoreCase(encoding)) {
                this.bits = 16;
            }
            else if ("ISO-8859-1".equalsIgnoreCase(encoding) || "Latin1".equalsIgnoreCase(encoding)) {
                this.bits = 8;
            }
            else if ("US-ASCII".equalsIgnoreCase(encoding) || "ASCII".equalsIgnoreCase(encoding)) {
                this.bits = 7;
            }
            else {
                this.bits = 0;
                try {
                    final Class charsetClass = Class.forName("java.nio.charset.Charset");
                    final Class encoderClass = Class.forName("java.nio.charset.CharsetEncoder");
                    final Method forName = charsetClass.getMethod("forName", (Format.class$java$lang$String == null) ? (Format.class$java$lang$String = Format.class$("java.lang.String")) : Format.class$java$lang$String);
                    final Object charsetObj = forName.invoke(null, encoding);
                    final Method newEncoder = charsetClass.getMethod("newEncoder", (Class[])null);
                    this.encoder = newEncoder.invoke(charsetObj, (Object[])null);
                    this.canEncode = encoderClass.getMethod("canEncode", Character.TYPE);
                }
                catch (Exception ex) {}
            }
        }
        
        public boolean shouldEscape(final char ch) {
            if (this.bits == 16) {
                return Verifier.isHighSurrogate(ch);
            }
            if (this.bits == 8) {
                return ch > '\u00ff';
            }
            if (this.bits == 7) {
                return ch > '\u007f';
            }
            if (Verifier.isHighSurrogate(ch)) {
                return true;
            }
            if (this.canEncode != null && this.encoder != null) {
                try {
                    final Boolean val = (Boolean)this.canEncode.invoke(this.encoder, new Character(ch));
                    return !val;
                }
                catch (Exception ex) {}
            }
            return false;
        }
    }
    
    public static class TextMode
    {
        public static final TextMode PRESERVE;
        public static final TextMode TRIM;
        public static final TextMode NORMALIZE;
        public static final TextMode TRIM_FULL_WHITE;
        private final String name;
        
        private TextMode(final String name) {
            this.name = name;
        }
        
        public String toString() {
            return this.name;
        }
        
        static {
            PRESERVE = new TextMode("PRESERVE");
            TRIM = new TextMode("TRIM");
            NORMALIZE = new TextMode("NORMALIZE");
            TRIM_FULL_WHITE = new TextMode("TRIM_FULL_WHITE");
        }
    }
}
