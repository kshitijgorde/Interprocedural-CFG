// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

import org.apache.xerces.util.XMLChar;
import org.w3c.dom.DOMError;
import org.apache.xerces.dom.DOMMessageFormatter;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.apache.xerces.util.XML11Char;
import java.io.OutputStream;
import java.io.Writer;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.NamespaceSupport;

public class XML11Serializer extends XMLSerializer
{
    protected static final boolean DEBUG = false;
    protected NamespaceSupport fNSBinder;
    protected NamespaceSupport fLocalNSBinder;
    protected SymbolTable fSymbolTable;
    protected boolean fDOML1;
    protected int fNamespaceCounter;
    protected static final String PREFIX = "NS";
    protected boolean fNamespaces;
    private boolean fPreserveSpace;
    
    public XML11Serializer() {
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        super._format.setVersion("1.1");
    }
    
    public XML11Serializer(final OutputFormat outputFormat) {
        super(outputFormat);
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        super._format.setVersion("1.1");
    }
    
    public XML11Serializer(final Writer writer, final OutputFormat outputFormat) {
        super(writer, outputFormat);
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        super._format.setVersion("1.1");
    }
    
    public XML11Serializer(final OutputStream outputStream, final OutputFormat outputFormat) {
        super(outputStream, (outputFormat != null) ? outputFormat : new OutputFormat("xml", null, false));
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        super._format.setVersion("1.1");
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        try {
            final ElementState content = this.content();
            if (content.inCData || content.doCData) {
                if (!content.inCData) {
                    super._printer.printText("<![CDATA[");
                    content.inCData = true;
                }
                final int nextIndent = super._printer.getNextIndent();
                super._printer.setNextIndent(0);
                for (int n3 = n + n2, i = n; i < n3; ++i) {
                    final char c = array[i];
                    if (c == ']' && i + 2 < n3 && array[i + 1] == ']' && array[i + 2] == '>') {
                        super._printer.printText("]]]]><![CDATA[>");
                        i += 2;
                    }
                    else if (!XML11Char.isXML11Valid(c)) {
                        if (++i < n3) {
                            this.surrogates(c, array[i]);
                        }
                        else {
                            this.fatalError("The character '" + c + "' is an invalid XML character");
                        }
                    }
                    else if (super._encodingInfo.isPrintable(c) && XML11Char.isXML11ValidLiteral(c)) {
                        super._printer.printText(c);
                    }
                    else {
                        super._printer.printText("]]>&#x");
                        super._printer.printText(Integer.toHexString(c));
                        super._printer.printText(";<![CDATA[");
                    }
                }
                super._printer.setNextIndent(nextIndent);
            }
            else if (content.preserveSpace) {
                final int nextIndent2 = super._printer.getNextIndent();
                super._printer.setNextIndent(0);
                this.printText(array, n, n2, true, content.unescaped);
                super._printer.setNextIndent(nextIndent2);
            }
            else {
                this.printText(array, n, n2, false, content.unescaped);
            }
        }
        catch (IOException ex) {
            throw new SAXException(ex);
        }
    }
    
    protected void printEscaped(final String s) throws IOException {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (!XML11Char.isXML11Valid(char1)) {
                if (++i < length) {
                    this.surrogates(char1, s.charAt(i));
                }
                else {
                    this.fatalError("The character '" + char1 + "' is an invalid XML character");
                }
            }
            else if (char1 == '\n' || char1 == '\r' || char1 == '\t' || char1 == '\u0085' || char1 == '\u2028') {
                this.printHex(char1);
            }
            else if (char1 == '<') {
                super._printer.printText("&lt;");
            }
            else if (char1 == '&') {
                super._printer.printText("&amp;");
            }
            else if (char1 == '\"') {
                super._printer.printText("&quot;");
            }
            else if (char1 >= ' ' && super._encodingInfo.isPrintable(char1)) {
                super._printer.printText(char1);
            }
            else {
                this.printHex(char1);
            }
        }
    }
    
    protected final void printCDATAText(final String s) throws IOException {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == ']' && i + 2 < length && s.charAt(i + 1) == ']' && s.charAt(i + 2) == '>') {
                if (super.fDOMErrorHandler != null) {
                    if ((super.features & 0x10) == 0x0 && (super.features & 0x2) == 0x0) {
                        this.modifyDOMError(DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "EndingCDATA", null), (short)3, null, super.fCurrentNode);
                        if (!super.fDOMErrorHandler.handleError(super.fDOMError)) {
                            throw new IOException();
                        }
                    }
                    else {
                        this.modifyDOMError(DOMMessageFormatter.formatMessage("http://apache.org/xml/serializer", "SplittingCDATA", null), (short)1, null, super.fCurrentNode);
                        super.fDOMErrorHandler.handleError(super.fDOMError);
                    }
                }
                super._printer.printText("]]]]><![CDATA[>");
                i += 2;
            }
            else if (!XML11Char.isXML11Valid(char1)) {
                if (++i < length) {
                    this.surrogates(char1, s.charAt(i));
                }
                else {
                    this.fatalError("The character '" + char1 + "' is an invalid XML character");
                }
            }
            else if (super._encodingInfo.isPrintable(char1) && XML11Char.isXML11ValidLiteral(char1)) {
                super._printer.printText(char1);
            }
            else {
                super._printer.printText("]]>&#x");
                super._printer.printText(Integer.toHexString(char1));
                super._printer.printText(";<![CDATA[");
            }
        }
    }
    
    protected final void printXMLChar(final int n) throws IOException {
        if (n == 13 || n == 133 || n == 8232) {
            this.printHex(n);
        }
        else if (n == 60) {
            super._printer.printText("&lt;");
        }
        else if (n == 38) {
            super._printer.printText("&amp;");
        }
        else if (n == 62) {
            super._printer.printText("&gt;");
        }
        else if (super._encodingInfo.isPrintable((char)n) && XML11Char.isXML11ValidLiteral(n)) {
            super._printer.printText((char)n);
        }
        else {
            this.printHex(n);
        }
    }
    
    protected final void surrogates(final int n, final int n2) throws IOException {
        if (XMLChar.isHighSurrogate(n)) {
            if (!XMLChar.isLowSurrogate(n2)) {
                this.fatalError("The character '" + (char)n2 + "' is an invalid XML character");
            }
            else {
                final int supplemental = XMLChar.supplemental((char)n, (char)n2);
                if (!XML11Char.isXML11Valid(supplemental)) {
                    this.fatalError("The character '" + (char)supplemental + "' is an invalid XML character");
                }
                else if (this.content().inCData) {
                    super._printer.printText("]]>&#x");
                    super._printer.printText(Integer.toHexString(supplemental));
                    super._printer.printText(";<![CDATA[");
                }
                else {
                    this.printHex(supplemental);
                }
            }
        }
        else {
            this.fatalError("The character '" + (char)n + "' is an invalid XML character");
        }
    }
    
    protected void printText(final String s, final boolean b, final boolean b2) throws IOException {
        final int length = s.length();
        if (b) {
            for (int i = 0; i < length; ++i) {
                final char char1 = s.charAt(i);
                if (!XML11Char.isXML11Valid(char1)) {
                    if (++i < length) {
                        this.surrogates(char1, s.charAt(i));
                    }
                    else {
                        this.fatalError("The character '" + char1 + "' is an invalid XML character");
                    }
                }
                else if (b2 && XML11Char.isXML11ValidLiteral(char1)) {
                    super._printer.printText(char1);
                }
                else {
                    this.printXMLChar(char1);
                }
            }
        }
        else {
            for (int j = 0; j < length; ++j) {
                final char char2 = s.charAt(j);
                if (!XML11Char.isXML11Valid(char2)) {
                    if (++j < length) {
                        this.surrogates(char2, s.charAt(j));
                    }
                    else {
                        this.fatalError("The character '" + char2 + "' is an invalid XML character");
                    }
                }
                else if (b2 && XML11Char.isXML11ValidLiteral(char2)) {
                    super._printer.printText(char2);
                }
                else {
                    this.printXMLChar(char2);
                }
            }
        }
    }
    
    protected void printText(final char[] array, int n, int n2, final boolean b, final boolean b2) throws IOException {
        if (b) {
            while (n2-- > 0) {
                final char c = array[n++];
                if (!XML11Char.isXML11Valid(c)) {
                    if (n2-- > 0) {
                        this.surrogates(c, array[n++]);
                    }
                    else {
                        this.fatalError("The character '" + c + "' is an invalid XML character");
                    }
                }
                else if (b2 && XML11Char.isXML11ValidLiteral(c)) {
                    super._printer.printText(c);
                }
                else {
                    this.printXMLChar(c);
                }
            }
        }
        else {
            while (n2-- > 0) {
                final char c2 = array[n++];
                if (!XML11Char.isXML11Valid(c2)) {
                    if (n2-- > 0) {
                        this.surrogates(c2, array[n++]);
                    }
                    else {
                        this.fatalError("The character '" + c2 + "' is an invalid XML character");
                    }
                }
                else if (b2 && XML11Char.isXML11ValidLiteral(c2)) {
                    super._printer.printText(c2);
                }
                else {
                    this.printXMLChar(c2);
                }
            }
        }
    }
    
    public boolean reset() {
        super.reset();
        return true;
    }
}
