// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.b2b2dtm;

import com.ibm.xml.b2b.util.IOExceptionWrapper;
import java.io.IOException;
import java.io.InputStream;
import com.ibm.xml.b2b.util.URI;
import org.apache.xml.utils.SystemIDResolver;
import java.io.Reader;
import javax.xml.transform.stream.StreamSource;
import com.ibm.xml.b2b.util.RewindableInputStream;

final class SimpleXMLDeclChecker
{
    private static final int CODEPOINT_ILLEGAL = 0;
    private static final int CODEPOINT_WHITESPACE = 1;
    private static final int CODEPOINT_EQUALSIGN = 2;
    private static final int CODEPOINT_SINGLEQUOTE = 3;
    private static final int CODEPOINT_DOUBLEQUOTE = 4;
    private static final int CODEPOINT_LETTER = 5;
    private static final int CODEPOINT_DIGIT = 6;
    private static final int CODEPOINT_HYPHEN = 7;
    private static final int CODEPOINT_UNDERSCORE = 8;
    private static final int CODEPOINT_PERIOD = 9;
    private static final int CODEPOINT_LESSTHAN = 10;
    private static final int CODEPOINT_GREATERTHAN = 11;
    private static final int CODEPOINT_QUESTIONMARK = 12;
    private static final int LITERAL_STARTPIXML = 0;
    private static final int LITERAL_VERSION = 1;
    private static final int LITERAL_VERSION10 = 2;
    private static final int LITERAL_ENCODING = 3;
    private static final int LITERAL_STANDALONE = 4;
    private static final int LITERAL_YES = 5;
    private static final int LITERAL_NO = 6;
    private static final int LITERAL_ENDPI = 7;
    private static final int STATE_START = 0;
    private static final int STATE_VERSION = 1;
    private static final int STATE_ENCODING = 2;
    private static final int STATE_STANDALONE = 3;
    private static final int STATE_FINISHED = 4;
    private static final byte ___ = 0;
    private static final byte WSP = 1;
    private static final byte PER = 9;
    private static final byte LSS = 10;
    private static final byte HYP = 7;
    private static final byte USC = 8;
    private static final byte GTR = 11;
    private static final byte QST = 12;
    private static final byte SQT = 3;
    private static final byte EQL = 2;
    private static final byte DQT = 4;
    private static final byte LTR = 5;
    private static final byte DIG = 6;
    private static final int[] STARTPIXML_Latin;
    private static final int[] VERSION_Latin;
    private static final int[] VERSION10_Latin;
    private static final int[] ENCODING_Latin;
    private static final int[] STANDALONE_Latin;
    private static final int[] YES_Latin;
    private static final int[] NO_Latin;
    private static final int[] ENDPI_Latin;
    private static final byte[] encNameCharMapLatin;
    private static final int AUTO_DETECT_ENCODING_UNKNOWN = 0;
    private static final int AUTO_DETECT_ENCODING_UTF8N = 1;
    private static final int AUTO_DETECT_ENCODING_UTF16BE = 2;
    private static final int AUTO_DETECT_ENCODING_UTF16LE = 3;
    private static final int AUTO_DETECT_ENCODING_UTF32BE = 4;
    private static final int AUTO_DETECT_ENCODING_UTF32LE = 5;
    private static final int AUTO_DETECT_ENCODING_LATIN = 6;
    private static final int AUTO_DETECT_ENCODING_EBCDIC = 7;
    private static final int AUTO_DETECT_ENCODING_UCS2BE = 8;
    private static final int AUTO_DETECT_ENCODING_UCS2LE = 9;
    private static final int AUTO_DETECT_ENCODING_UCS4BE = 10;
    private static final int AUTO_DETECT_ENCODING_UCS4LE = 11;
    private RewindableReader fReader;
    private RewindableInputStream fStream;
    private int fBytesPerChar;
    private boolean fIsBigEndian;
    
    public boolean checkXMLDecl(final StreamSource source) throws IOException {
        final Reader rdr = source.getReader();
        if (rdr != null) {
            RewindableReader rr = this.fReader;
            if (rr == null) {
                final RewindableReader fReader = new RewindableReader();
                this.fReader = fReader;
                rr = fReader;
            }
            rr.setReader(rdr);
            source.setReader(rr);
            final boolean result = this.checkXMLDeclReader(source, rr);
            rr.rewind();
            return result;
        }
        InputStream stream = source.getInputStream();
        RewindableInputStream is = this.fStream;
        if (is == null) {
            final RewindableInputStream fStream = new RewindableInputStream();
            this.fStream = fStream;
            is = fStream;
        }
        if (stream == null) {
            String uri = source.getSystemId();
            uri = SystemIDResolver.getAbsoluteURI(uri);
            stream = URI.openStream(uri);
        }
        is.setStream(stream);
        source.setInputStream(is);
        return this.checkXMLDeclStream(source);
    }
    
    private boolean checkXMLDeclReader(final StreamSource source, final Reader rdr) throws IOException {
        int state = 0;
        int ch = this.readChar();
        if (!this.skipReaderLiteral(ch, 0)) {
            return true;
        }
        ch = this.skipReaderSpaces();
        do {
            if (state == 0) {
                if (!this.skipReaderLiteral(ch, 1)) {
                    return false;
                }
                state = 1;
            }
            else if (state == 1 && ch == 101) {
                if (!this.skipReaderLiteral(ch, 3)) {
                    return false;
                }
                state = 2;
            }
            else {
                if (ch != 115) {
                    break;
                }
                if (!this.skipReaderLiteral(ch, 4)) {
                    return false;
                }
                state = 3;
            }
            ch = this.skipReaderSpaces();
            if (ch != 61) {
                return false;
            }
            ch = this.skipReaderSpaces();
            final boolean dquote = ch == 34;
            if (!dquote && ch != 39) {
                return false;
            }
            final int qchar = ch;
            ch = this.readChar();
            switch (state) {
                case 1: {
                    if (!this.skipReaderLiteral(ch, 2) || this.readChar() != qchar) {
                        return false;
                    }
                    ch = this.readChar();
                    if (ch == 32 || ch == 10 || ch == 9 || ch == 13) {
                        ch = this.skipReaderSpaces();
                        continue;
                    }
                    state = 4;
                    continue;
                }
                case 2: {
                    if (!this.scanReaderEncodingName(ch, qchar)) {
                        return false;
                    }
                    ch = this.readChar();
                    if (ch == 32 || ch == 10 || ch == 9 || ch == 13) {
                        ch = this.skipReaderSpaces();
                        continue;
                    }
                    state = 4;
                    continue;
                }
                case 3: {
                    if (ch == 121) {
                        if (!this.skipReaderLiteral(ch, 5) || this.readChar() != qchar) {
                            return false;
                        }
                    }
                    else if (!this.skipReaderLiteral(ch, 6) || this.readChar() != qchar) {
                        return false;
                    }
                    ch = this.skipReaderSpaces();
                    state = 4;
                }
                default: {
                    continue;
                }
            }
        } while (state != 4);
        return ch == 63 && this.readChar() == 62;
    }
    
    private int readChar() throws IOException {
        return this.fReader.read();
    }
    
    private boolean skipReaderLiteral(final int firstChar, final int literal) throws IOException {
        int[] litData = null;
        int litLength = 0;
        switch (literal) {
            case 0: {
                litData = SimpleXMLDeclChecker.STARTPIXML_Latin;
                litLength = 5;
                break;
            }
            case 1: {
                litData = SimpleXMLDeclChecker.VERSION_Latin;
                litLength = 7;
                break;
            }
            case 2: {
                litData = SimpleXMLDeclChecker.VERSION10_Latin;
                litLength = 3;
                break;
            }
            case 3: {
                litData = SimpleXMLDeclChecker.ENCODING_Latin;
                litLength = 8;
                break;
            }
            case 4: {
                litData = SimpleXMLDeclChecker.STANDALONE_Latin;
                litLength = 10;
                break;
            }
            case 5: {
                litData = SimpleXMLDeclChecker.YES_Latin;
                litLength = 3;
                break;
            }
            case 6: {
                litData = SimpleXMLDeclChecker.NO_Latin;
                litLength = 2;
                break;
            }
            default: {
                return false;
            }
        }
        if (firstChar != litData[0]) {
            return false;
        }
        for (int i = 1; i < litLength; ++i) {
            final int b = this.readChar();
            if (b != litData[i]) {
                return false;
            }
        }
        if (literal == 0) {
            final int b = this.readChar();
            if (b != 32 && b != 10 && b != 9 && b != 13) {
                return false;
            }
        }
        return true;
    }
    
    private int skipReaderSpaces() throws IOException {
        int b;
        do {
            b = this.readChar();
        } while (b == 32 || b == 10 || b == 9 || b == 13);
        return b;
    }
    
    private boolean scanReaderEncodingName(final int firstChar, final int qchar) throws IOException {
        if (firstChar == -1) {
            return false;
        }
        if (firstChar >= 128 || SimpleXMLDeclChecker.encNameCharMapLatin[firstChar] != 1) {
            return false;
        }
        int b;
        do {
            b = this.readChar();
            if (b == qchar) {
                return true;
            }
            if (b == -1) {
                return false;
            }
        } while (b < 128 && SimpleXMLDeclChecker.encNameCharMapLatin[b] != 0);
        return false;
    }
    
    public boolean checkXMLDeclStream(final StreamSource source) throws IOException {
        boolean result = false;
        switch (autoDetectEncoding(this.fStream)) {
            default: {
                result = true;
                break;
            }
            case 1: {
                result = this.checkXMLDeclLatin();
                break;
            }
            case 2: {
                result = this.checkXMLDeclUCS(true, 2, true);
                break;
            }
            case 3: {
                result = this.checkXMLDeclUCS(true, 2, false);
                break;
            }
            case 4: {
                result = this.checkXMLDeclUCS(true, 4, true);
                break;
            }
            case 5: {
                result = this.checkXMLDeclUCS(true, 4, false);
                break;
            }
            case 6: {
                result = this.checkXMLDeclLatin();
                break;
            }
            case 7: {
                result = false;
                break;
            }
            case 8: {
                result = this.checkXMLDeclUCS(false, 2, true);
                break;
            }
            case 9: {
                result = this.checkXMLDeclUCS(false, 2, false);
                break;
            }
            case 10: {
                result = this.checkXMLDeclUCS(false, 4, true);
                break;
            }
            case 11: {
                result = this.checkXMLDeclUCS(false, 4, false);
                break;
            }
        }
        this.fStream.rewind();
        return result;
    }
    
    private static final int autoDetectEncoding(final RewindableInputStream stream) {
        try {
            final int b0 = stream.read();
            switch (b0) {
                case 239: {
                    final int b2 = stream.read();
                    if (b2 != 187) {
                        break;
                    }
                    final int b3 = stream.read();
                    if (b3 == 191) {
                        return 1;
                    }
                    break;
                }
                case 60: {
                    final int b2 = stream.read();
                    if (b2 == 0) {
                        final int b3 = stream.read();
                        if (b3 == 0) {
                            final int b4 = stream.read();
                            if (b4 == 0) {
                                stream.rewind();
                                return 11;
                            }
                            break;
                        }
                        else {
                            if (b3 != 63) {
                                break;
                            }
                            final int b4 = stream.read();
                            if (b4 == 0) {
                                stream.rewind();
                                return 9;
                            }
                            break;
                        }
                    }
                    else {
                        if (b2 != 63) {
                            break;
                        }
                        final int b3 = stream.read();
                        if (b3 != 120) {
                            break;
                        }
                        final int b4 = stream.read();
                        if (b4 == 109) {
                            stream.rewind();
                            return 6;
                        }
                        break;
                    }
                    break;
                }
                case 76: {
                    final int b2 = stream.read();
                    if (b2 != 111) {
                        break;
                    }
                    final int b3 = stream.read();
                    if (b3 != 167) {
                        break;
                    }
                    final int b4 = stream.read();
                    if (b4 == 148) {
                        stream.rewind();
                        return 7;
                    }
                    break;
                }
                case 0: {
                    final int b2 = stream.read();
                    if (b2 == 0) {
                        final int b3 = stream.read();
                        if (b3 == 0) {
                            final int b4 = stream.read();
                            if (b4 == 60) {
                                stream.rewind();
                                return 10;
                            }
                            break;
                        }
                        else {
                            if (b3 != 254) {
                                break;
                            }
                            final int b4 = stream.read();
                            if (b4 == 255) {
                                return 4;
                            }
                            break;
                        }
                    }
                    else {
                        if (b2 != 60) {
                            break;
                        }
                        final int b3 = stream.read();
                        if (b3 != 0) {
                            break;
                        }
                        final int b4 = stream.read();
                        if (b4 == 63) {
                            stream.rewind();
                            return 8;
                        }
                        break;
                    }
                    break;
                }
                case 254: {
                    final int b2 = stream.read();
                    if (b2 == 255) {
                        return 2;
                    }
                    break;
                }
                case 255: {
                    final int b2 = stream.read();
                    if (b2 == 254) {
                        stream.mark(2);
                        final int b3 = stream.read();
                        if (b3 == 0) {
                            final int b4 = stream.read();
                            if (b4 == 0) {
                                return 5;
                            }
                        }
                        stream.reset();
                        return 3;
                    }
                    break;
                }
            }
        }
        catch (IOException ex) {
            throw new IOExceptionWrapper(ex);
        }
        return 0;
    }
    
    public boolean checkXMLDeclLatin() throws IOException {
        int state = 0;
        int b = this.readByte();
        if (!this.skipLatinLiteral(b, 0)) {
            return true;
        }
        b = this.skipLatinSpaces();
        do {
            if (state == 0) {
                if (!this.skipLatinLiteral(b, 1)) {
                    return false;
                }
                state = 1;
            }
            else if (state == 1 && b == 101) {
                if (!this.skipLatinLiteral(b, 3)) {
                    return false;
                }
                state = 2;
            }
            else {
                if (b != 115) {
                    break;
                }
                if (!this.skipLatinLiteral(b, 4)) {
                    return false;
                }
                state = 3;
            }
            b = this.skipLatinSpaces();
            if (b != 61) {
                return false;
            }
            b = this.skipLatinSpaces();
            final boolean dquote = b == 34;
            if (!dquote && b != 39) {
                return false;
            }
            final int qchar = b;
            b = this.readByte();
            switch (state) {
                case 1: {
                    if (!this.skipLatinLiteral(b, 2) || this.readByte() != qchar) {
                        return false;
                    }
                    b = this.readByte();
                    if (b == 32 || b == 10 || b == 9 || b == 13) {
                        b = this.skipLatinSpaces();
                        continue;
                    }
                    state = 4;
                    continue;
                }
                case 2: {
                    if (!this.scanLatinEncodingName(b, qchar)) {
                        return false;
                    }
                    b = this.readByte();
                    if (b == 32 || b == 10 || b == 9 || b == 13) {
                        b = this.skipLatinSpaces();
                        continue;
                    }
                    state = 4;
                    continue;
                }
                case 3: {
                    if (b == 121) {
                        if (!this.skipLatinLiteral(b, 5) || this.readByte() != qchar) {
                            return false;
                        }
                    }
                    else if (!this.skipLatinLiteral(b, 6) || this.readByte() != qchar) {
                        return false;
                    }
                    b = this.skipLatinSpaces();
                    state = 4;
                }
                default: {
                    continue;
                }
            }
        } while (state != 4);
        return b == 63 && this.readByte() == 62;
    }
    
    private int readByte() throws IOException {
        return this.fStream.read();
    }
    
    private boolean skipLatinLiteral(final int firstByte, final int literal) throws IOException {
        int[] litData = null;
        int litLength = 0;
        switch (literal) {
            case 0: {
                litData = SimpleXMLDeclChecker.STARTPIXML_Latin;
                litLength = 5;
                break;
            }
            case 1: {
                litData = SimpleXMLDeclChecker.VERSION_Latin;
                litLength = 7;
                break;
            }
            case 2: {
                litData = SimpleXMLDeclChecker.VERSION10_Latin;
                litLength = 3;
                break;
            }
            case 3: {
                litData = SimpleXMLDeclChecker.ENCODING_Latin;
                litLength = 8;
                break;
            }
            case 4: {
                litData = SimpleXMLDeclChecker.STANDALONE_Latin;
                litLength = 10;
                break;
            }
            case 5: {
                litData = SimpleXMLDeclChecker.YES_Latin;
                litLength = 3;
                break;
            }
            case 6: {
                litData = SimpleXMLDeclChecker.NO_Latin;
                litLength = 2;
                break;
            }
            default: {
                return false;
            }
        }
        if (firstByte != litData[0]) {
            return false;
        }
        for (int i = 1; i < litLength; ++i) {
            final int b = this.readByte();
            if (b != litData[i]) {
                return false;
            }
        }
        if (literal == 0) {
            final int b = this.readByte();
            if (b != 32 && b != 10 && b != 9 && b != 13) {
                return false;
            }
        }
        return true;
    }
    
    private int skipLatinSpaces() throws IOException {
        int b;
        do {
            b = this.readByte();
        } while (b == 32 || b == 10 || b == 9 || b == 13);
        return b;
    }
    
    private boolean scanLatinEncodingName(final int firstByte, final int qchar) throws IOException {
        if (firstByte == -1) {
            return false;
        }
        if (firstByte >= 128 || SimpleXMLDeclChecker.encNameCharMapLatin[firstByte] != 1) {
            return false;
        }
        int b;
        do {
            b = this.readByte();
            if (b == qchar) {
                return true;
            }
            if (b == -1) {
                return false;
            }
        } while (b < 128 && SimpleXMLDeclChecker.encNameCharMapLatin[b] != 0);
        return false;
    }
    
    public boolean checkXMLDeclUCS(final boolean withBOM, final int bytesPerChar, final boolean isBigEndian) throws IOException {
        int state = 0;
        this.fBytesPerChar = bytesPerChar;
        this.fIsBigEndian = isBigEndian;
        int b = this.readUCSChar();
        if (!this.skipUCSLiteral(b, 0)) {
            return true;
        }
        b = this.skipUCSSpaces();
        do {
            if (state == 0) {
                if (!this.skipUCSLiteral(b, 1)) {
                    return false;
                }
                state = 1;
            }
            else if (state == 1 && b == 101) {
                if (!this.skipUCSLiteral(b, 3)) {
                    return false;
                }
                state = 2;
            }
            else {
                if (b != 115) {
                    break;
                }
                if (!this.skipUCSLiteral(b, 4)) {
                    return false;
                }
                state = 3;
            }
            b = this.skipUCSSpaces();
            if (b != 61) {
                return false;
            }
            b = this.skipUCSSpaces();
            final boolean dquote = b == 34;
            if (!dquote && b != 39) {
                return false;
            }
            final int qchar = b;
            b = this.readUCSChar();
            switch (state) {
                case 1: {
                    if (!this.skipUCSLiteral(b, 2) || this.readUCSChar() != qchar) {
                        return false;
                    }
                    b = this.readUCSChar();
                    if (b == 32 || b == 10 || b == 9 || b == 13) {
                        b = this.skipUCSSpaces();
                        continue;
                    }
                    state = 4;
                    continue;
                }
                case 2: {
                    if (!this.scanUCSEncodingName(b, qchar)) {
                        return false;
                    }
                    b = this.readUCSChar();
                    if (b == 32 || b == 10 || b == 9 || b == 13) {
                        b = this.skipUCSSpaces();
                        continue;
                    }
                    state = 4;
                    continue;
                }
                case 3: {
                    if (b == 121) {
                        if (!this.skipUCSLiteral(b, 5) || this.readUCSChar() != qchar) {
                            return false;
                        }
                    }
                    else if (!this.skipUCSLiteral(b, 6) || this.readUCSChar() != qchar) {
                        return false;
                    }
                    b = this.skipUCSSpaces();
                    state = 4;
                }
                default: {
                    continue;
                }
            }
        } while (state != 4);
        return b == 63 && this.readUCSChar() == 62;
    }
    
    private int readUCSChar() throws IOException {
        final int b0 = this.fStream.read();
        if (b0 == -1) {
            return -1;
        }
        final int b2 = this.fStream.read();
        if (b2 == -1) {
            return -1;
        }
        if (this.fBytesPerChar == 2) {
            if (this.fIsBigEndian) {
                return (b0 << 8) + b2;
            }
            return (b2 << 8) + b0;
        }
        else {
            final int b3 = this.fStream.read();
            if (b3 == -1) {
                return -1;
            }
            final int b4 = this.fStream.read();
            if (b4 == -1) {
                return -1;
            }
            if (this.fIsBigEndian) {
                return (b0 << 24) + (b2 << 16) + (b3 << 8) + b4;
            }
            return (b4 << 24) + (b3 << 16) + (b2 << 8) + b0;
        }
    }
    
    private boolean skipUCSLiteral(final int firstByte, final int literal) throws IOException {
        int[] litData = null;
        int litLength = 0;
        switch (literal) {
            case 0: {
                litData = SimpleXMLDeclChecker.STARTPIXML_Latin;
                litLength = 5;
                break;
            }
            case 1: {
                litData = SimpleXMLDeclChecker.VERSION_Latin;
                litLength = 7;
                break;
            }
            case 2: {
                litData = SimpleXMLDeclChecker.VERSION10_Latin;
                litLength = 3;
                break;
            }
            case 3: {
                litData = SimpleXMLDeclChecker.ENCODING_Latin;
                litLength = 8;
                break;
            }
            case 4: {
                litData = SimpleXMLDeclChecker.STANDALONE_Latin;
                litLength = 10;
                break;
            }
            case 5: {
                litData = SimpleXMLDeclChecker.YES_Latin;
                litLength = 3;
                break;
            }
            case 6: {
                litData = SimpleXMLDeclChecker.NO_Latin;
                litLength = 2;
                break;
            }
            default: {
                return false;
            }
        }
        if (firstByte != litData[0]) {
            return false;
        }
        for (int i = 1; i < litLength; ++i) {
            final int b = this.readUCSChar();
            if (b != litData[i]) {
                return false;
            }
        }
        if (literal == 0) {
            final int b = this.readUCSChar();
            if (b != 32 && b != 10 && b != 9 && b != 13) {
                return false;
            }
        }
        return true;
    }
    
    private int skipUCSSpaces() throws IOException {
        int b;
        do {
            b = this.readUCSChar();
        } while (b == 32 || b == 10 || b == 9 || b == 13);
        return b;
    }
    
    private boolean scanUCSEncodingName(final int firstChar, final int qchar) throws IOException {
        if (firstChar == -1) {
            return false;
        }
        if (firstChar >= 128 || SimpleXMLDeclChecker.encNameCharMapLatin[firstChar] != 1) {
            return false;
        }
        int b;
        do {
            b = this.readUCSChar();
            if (b == qchar) {
                return true;
            }
            if (b == -1) {
                return false;
            }
        } while (b < 128 && SimpleXMLDeclChecker.encNameCharMapLatin[b] != 0);
        return false;
    }
    
    static {
        STARTPIXML_Latin = new int[] { 60, 63, 120, 109, 108 };
        VERSION_Latin = new int[] { 118, 101, 114, 115, 105, 111, 110 };
        VERSION10_Latin = new int[] { 49, 46, 48 };
        ENCODING_Latin = new int[] { 101, 110, 99, 111, 100, 105, 110, 103 };
        STANDALONE_Latin = new int[] { 115, 116, 97, 110, 100, 97, 108, 111, 110, 101 };
        YES_Latin = new int[] { 121, 101, 115 };
        NO_Latin = new int[] { 110, 111 };
        ENDPI_Latin = new int[] { 63, 62 };
        encNameCharMapLatin = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
    }
    
    final class RewindableReader extends Reader
    {
        private Reader fReader;
        private char[] fData;
        private int fStartOffset;
        private int fEndOffset;
        private int fOffset;
        private int fLength;
        private int fMark;
        
        public RewindableReader() {
            this.fData = new char[4096];
        }
        
        public void setReader(final Reader rdr) {
            this.fReader = rdr;
            this.fStartOffset = 0;
            this.fEndOffset = -1;
            this.fOffset = 0;
            this.fLength = 0;
            this.fMark = 0;
        }
        
        public void setStartOffset(final int offset) {
            this.fStartOffset = offset;
        }
        
        public void rewind() {
            this.fOffset = this.fStartOffset;
        }
        
        public int read() throws IOException {
            if (this.fOffset < this.fLength) {
                return this.fData[this.fOffset++] & '\u00ff';
            }
            if (this.fOffset == this.fEndOffset) {
                return -1;
            }
            if (this.fOffset == this.fData.length) {
                final char[] newData = new char[this.fOffset << 1];
                System.arraycopy(this.fData, 0, newData, 0, this.fOffset);
                this.fData = newData;
            }
            final int count = this.fReader.read(this.fData, this.fOffset, this.fData.length - this.fOffset);
            if (count == -1) {
                this.fEndOffset = this.fOffset;
                return -1;
            }
            this.fLength += count;
            return this.fData[this.fOffset++] & '\u00ff';
        }
        
        public int read(final char[] b, int off, int len) throws IOException {
            final int charsLeft = this.fLength - this.fOffset;
            if (charsLeft == 0) {
                if (this.fOffset == this.fEndOffset) {
                    return -1;
                }
                return this.fReader.read(b, off, len);
            }
            else {
                int count;
                if (len < charsLeft) {
                    if (len <= 0) {
                        return 0;
                    }
                    count = len;
                }
                else {
                    count = charsLeft;
                }
                if (b != null) {
                    System.arraycopy(this.fData, this.fOffset, b, off, count);
                }
                this.fOffset += count;
                if (count == len || this.fOffset == this.fEndOffset) {
                    return count;
                }
                off += count;
                len -= count;
                final int result = this.fReader.read(b, off, len);
                if (result == -1) {
                    this.fEndOffset = this.fOffset;
                    return count;
                }
                return count + result;
            }
        }
        
        public long skip(long n) throws IOException {
            if (n <= 0L) {
                return 0L;
            }
            final int charsLeft = this.fLength - this.fOffset;
            if (charsLeft == 0) {
                if (this.fOffset == this.fEndOffset) {
                    return 0L;
                }
                return this.fReader.skip(n);
            }
            else {
                if (n <= charsLeft) {
                    this.fOffset += (int)n;
                    return n;
                }
                this.fOffset += charsLeft;
                if (this.fOffset == this.fEndOffset) {
                    return charsLeft;
                }
                n -= charsLeft;
                return this.fReader.skip(n) + charsLeft;
            }
        }
        
        public void mark(final int howmuch) {
            this.fMark = this.fOffset;
        }
        
        public void reset() {
            this.fOffset = this.fMark;
        }
        
        public boolean markSupported() {
            return true;
        }
        
        public void close() throws IOException {
            if (this.fReader != null) {
                this.fReader.close();
                this.fReader = null;
            }
        }
    }
}
