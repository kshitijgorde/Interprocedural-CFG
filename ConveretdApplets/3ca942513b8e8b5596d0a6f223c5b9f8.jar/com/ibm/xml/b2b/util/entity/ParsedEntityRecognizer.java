// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import com.ibm.xml.b2b.util.CharConversionError;
import java.io.InputStreamReader;
import com.ibm.xml.b2b.util.UCSEncodingSupport;
import com.ibm.xml.b2b.util.IANACharset;
import java.io.IOException;
import com.ibm.xml.b2b.util.IOExceptionWrapper;
import com.ibm.xml.b2b.util.URI;
import com.ibm.xml.b2b.util.UTF8EncodingSupport;
import java.io.InputStream;
import java.io.Reader;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.RewindableInputStream;
import com.ibm.xml.b2b.util.ReusableByteArrayInputStream;
import com.ibm.xml.b2b.util.EncodingSupport;

abstract class ParsedEntityRecognizer
{
    public static final boolean ALLOW_JAVA_ENCODINGS = true;
    protected static final int ENCODING_UNKNOWN = 0;
    protected static final int ENCODING_UTF8N = 1;
    protected static final int ENCODING_UTF16BE = 2;
    protected static final int ENCODING_UTF16LE = 3;
    protected static final int ENCODING_UTF32BE = 4;
    protected static final int ENCODING_UTF32LE = 5;
    protected static final int ENCODING_LATIN = 6;
    protected static final int ENCODING_EBCDIC = 7;
    protected static final int ENCODING_UCS2BE = 8;
    protected static final int ENCODING_UCS2LE = 9;
    protected static final int ENCODING_UCS4BE = 10;
    protected static final int ENCODING_UCS4LE = 11;
    protected boolean fIsDocumentEntity;
    protected boolean fIsExternalEntity;
    private static final boolean CLEAR_TO_END_OF_BUFFER = false;
    private static final int INITIAL_BUFFER_LENGTH = 32768;
    private boolean[] fSkipLF;
    private byte[] fByteIOBuffer;
    private char[] fCharIOBuffer;
    private int fFreeBytesPointerCount;
    private int fInUseBytesPointerCount;
    private byte[][] fBytesPointer;
    private int fFreeCharsPointerCount;
    private int fInUseCharsPointerCount;
    private char[][] fCharsPointer;
    private int[] fOffsetPointer;
    private int[] fSurrogate;
    private EncodingSupport fUTF8EncodingSupport;
    private ReusableByteArrayInputStream fByteArrayStream;
    private RewindableInputStream fStreamWrapper;
    private LatinEncodingDeclReader fLatinEncodingDeclReader;
    private EBCDICEncodingDeclReader fEBCDICEncodingDeclReader;
    private UCSEncodingDeclReader fUCSEncodingDeclReader;
    
    protected abstract XMLString getEntityContent();
    
    protected abstract boolean setCharacterStreamSource(final Reader p0);
    
    protected abstract boolean setByteStreamSource(final InputStream p0, final EncodingSupport p1);
    
    protected abstract boolean setByteArraySource(final byte[] p0, final int p1, final int p2, final EncodingSupport p3);
    
    protected abstract boolean setCharArraySource(final char[] p0, final int p1, final int p2);
    
    protected abstract boolean setStringSource(final String p0);
    
    protected abstract boolean readUTF8ByteStream(final InputStream p0, final EncodingSupport p1);
    
    protected abstract boolean readUCSByteStreamWithBOM(final InputStream p0, final EncodingSupport p1);
    
    protected abstract boolean readLatinByteStream(final InputStream p0, final EncodingSupport p1);
    
    protected abstract boolean readEBCDICByteStream(final InputStream p0, final EncodingSupport p1);
    
    protected abstract boolean readUCSByteStream(final InputStream p0, final EncodingSupport p1);
    
    protected ParsedEntityRecognizer(final boolean b) {
        if (b) {
            this.fSkipLF = new boolean[1];
        }
        this.fBytesPointer = new byte[4][];
        this.fCharsPointer = new char[4][];
        this.fOffsetPointer = new int[1];
        this.fSurrogate = new int[1];
        this.fUTF8EncodingSupport = UTF8EncodingSupport.getInstance();
    }
    
    protected void reset(final boolean b) {
        if (!b) {
            for (int i = 0; i < this.fBytesPointer.length; ++i) {
                if (this.fBytesPointer[i] != null) {
                    this.fBytesPointer[i] = null;
                }
            }
            for (int j = 0; j < this.fCharsPointer.length; ++j) {
                if (this.fCharsPointer[j] != null) {
                    this.fCharsPointer[j] = null;
                }
            }
            if (this.fByteIOBuffer != null) {
                this.fByteIOBuffer = null;
            }
            if (this.fCharIOBuffer != null) {
                this.fCharIOBuffer = null;
            }
        }
    }
    
    protected final boolean setSource(final EntityInputSource entityInputSource, final boolean fIsDocumentEntity, final boolean fIsExternalEntity) {
        this.fIsDocumentEntity = fIsDocumentEntity;
        this.fIsExternalEntity = fIsExternalEntity;
        final XMLString content = entityInputSource.getContent();
        if (content == null) {
            final Reader characterStream = entityInputSource.getCharacterStream();
            if (characterStream != null) {
                return this.setCharacterStreamSource(characterStream);
            }
            InputStream inputStream = entityInputSource.getByteStream();
            final String encoding = entityInputSource.getEncoding();
            boolean b;
            if (inputStream == null) {
                final String systemId = entityInputSource.getSystemId();
                try {
                    inputStream = URI.openStream(systemId);
                }
                catch (IOException ex) {
                    throw new IOExceptionWrapper(ex);
                }
                b = true;
            }
            else {
                b = false;
            }
            boolean b2;
            if (encoding == null) {
                b2 = this.detectEncodingSetSource(inputStream);
            }
            else {
                final EncodingSupport encodingSupport = IANACharset.getEncodingSupport(encoding.toUpperCase());
                if (encodingSupport != null) {
                    b2 = this.setByteStreamSource(inputStream, encodingSupport);
                }
                else {
                    b2 = this.convertByteStreamWithUnknownEncoding(inputStream, encoding);
                }
            }
            if (b) {
                try {
                    inputStream.close();
                }
                catch (IOException ex2) {
                    throw new IOExceptionWrapper(ex2);
                }
            }
            return b2;
        }
        else if (content.bytes != null) {
            if (content.encoding != null) {
                return this.setByteArraySource(content.bytes, content.offset, content.endOffset, content.encoding);
            }
            if (!this.fIsExternalEntity) {
                throw new RuntimeException("ParsedEntityRecognizer#setSource()");
            }
            if (this.fByteArrayStream == null) {
                this.fByteArrayStream = new ReusableByteArrayInputStream(content.bytes, content.offset, content.endOffset - content.offset);
            }
            else {
                this.fByteArrayStream.setBuffer(content.bytes, content.offset, content.endOffset - content.offset);
            }
            return this.detectEncodingSetSource(this.fByteArrayStream);
        }
        else {
            if (content.chars != null) {
                return this.setCharArraySource(content.chars, content.offset, content.endOffset);
            }
            if (content.str != null) {
                return this.setStringSource(content.str);
            }
            throw new IllegalArgumentException("ParsedEntityRecognizer#setSource()");
        }
    }
    
    private boolean detectEncodingSetSource(final InputStream stream) {
        RewindableInputStream fStreamWrapper = this.fStreamWrapper;
        if (fStreamWrapper == null) {
            final RewindableInputStream fStreamWrapper2 = new RewindableInputStream();
            this.fStreamWrapper = fStreamWrapper2;
            fStreamWrapper = fStreamWrapper2;
        }
        fStreamWrapper.setStream(stream);
        switch (detectEncoding(fStreamWrapper)) {
            default: {
                return this.readUTF8ByteStream(fStreamWrapper, this.fUTF8EncodingSupport);
            }
            case 2: {
                return this.readUCSByteStreamWithBOM(fStreamWrapper, UCSEncodingSupport.getInstance(0));
            }
            case 3: {
                return this.readUCSByteStreamWithBOM(fStreamWrapper, UCSEncodingSupport.getInstance(1));
            }
            case 4: {
                return this.readUCSByteStreamWithBOM(fStreamWrapper, UCSEncodingSupport.getInstance(2));
            }
            case 5: {
                return this.readUCSByteStreamWithBOM(fStreamWrapper, UCSEncodingSupport.getInstance(3));
            }
            case 6: {
                if (this.fLatinEncodingDeclReader == null) {
                    this.fLatinEncodingDeclReader = new LatinEncodingDeclReader();
                }
                final String encoding = this.fLatinEncodingDeclReader.getEncoding(fStreamWrapper, this.fIsDocumentEntity);
                fStreamWrapper.rewind();
                boolean b;
                if (encoding == null) {
                    b = this.readUTF8ByteStream(fStreamWrapper, this.fUTF8EncodingSupport);
                }
                else {
                    final EncodingSupport encodingSupport = IANACharset.getEncodingSupport(encoding.toUpperCase());
                    if (encodingSupport == null) {
                        b = this.convertByteStreamWithUnknownEncoding(fStreamWrapper, encoding);
                    }
                    else {
                        b = this.readLatinByteStream(fStreamWrapper, encodingSupport);
                    }
                }
                return b;
            }
            case 7: {
                if (this.fEBCDICEncodingDeclReader == null) {
                    this.fEBCDICEncodingDeclReader = new EBCDICEncodingDeclReader();
                }
                final String encoding2 = this.fEBCDICEncodingDeclReader.getEncoding(fStreamWrapper, this.fIsDocumentEntity);
                fStreamWrapper.rewind();
                boolean b2;
                if (encoding2 == null) {
                    b2 = this.readUTF8ByteStream(fStreamWrapper, this.fUTF8EncodingSupport);
                }
                else {
                    final EncodingSupport encodingSupport2 = IANACharset.getEncodingSupport(encoding2.toUpperCase());
                    if (encodingSupport2 == null) {
                        b2 = this.convertByteStreamWithUnknownEncoding(fStreamWrapper, encoding2);
                    }
                    else {
                        b2 = this.readEBCDICByteStream(fStreamWrapper, encodingSupport2);
                    }
                }
                return b2;
            }
            case 8: {
                if (this.fUCSEncodingDeclReader == null) {
                    this.fUCSEncodingDeclReader = new UCSEncodingDeclReader();
                }
                this.fUCSEncodingDeclReader.setState(2, true);
                final String encoding3 = this.fUCSEncodingDeclReader.getEncoding(fStreamWrapper, this.fIsDocumentEntity);
                fStreamWrapper.rewind();
                boolean b3;
                if (encoding3 == null) {
                    b3 = this.readUTF8ByteStream(fStreamWrapper, this.fUTF8EncodingSupport);
                }
                else {
                    final EncodingSupport encodingSupport3 = IANACharset.getEncodingSupport(encoding3.toUpperCase());
                    if (encodingSupport3 == null) {
                        b3 = this.convertByteStreamWithUnknownEncoding(fStreamWrapper, encoding3);
                    }
                    else {
                        b3 = this.readUCSByteStream(fStreamWrapper, encodingSupport3);
                    }
                }
                return b3;
            }
            case 9: {
                if (this.fUCSEncodingDeclReader == null) {
                    this.fUCSEncodingDeclReader = new UCSEncodingDeclReader();
                }
                this.fUCSEncodingDeclReader.setState(2, false);
                final String encoding4 = this.fUCSEncodingDeclReader.getEncoding(fStreamWrapper, this.fIsDocumentEntity);
                fStreamWrapper.rewind();
                boolean b4;
                if (encoding4 == null) {
                    b4 = this.readUTF8ByteStream(fStreamWrapper, this.fUTF8EncodingSupport);
                }
                else {
                    final EncodingSupport encodingSupport4 = IANACharset.getEncodingSupport(encoding4.toUpperCase());
                    if (encodingSupport4 == null) {
                        b4 = this.convertByteStreamWithUnknownEncoding(fStreamWrapper, encoding4);
                    }
                    else {
                        b4 = this.readUCSByteStream(fStreamWrapper, encodingSupport4);
                    }
                }
                return b4;
            }
            case 10: {
                if (this.fUCSEncodingDeclReader == null) {
                    this.fUCSEncodingDeclReader = new UCSEncodingDeclReader();
                }
                this.fUCSEncodingDeclReader.setState(4, true);
                final String encoding5 = this.fUCSEncodingDeclReader.getEncoding(fStreamWrapper, this.fIsDocumentEntity);
                fStreamWrapper.rewind();
                boolean b5;
                if (encoding5 == null) {
                    b5 = this.readUTF8ByteStream(fStreamWrapper, this.fUTF8EncodingSupport);
                }
                else {
                    final EncodingSupport encodingSupport5 = IANACharset.getEncodingSupport(encoding5.toUpperCase());
                    if (encodingSupport5 == null) {
                        b5 = this.convertByteStreamWithUnknownEncoding(fStreamWrapper, encoding5);
                    }
                    else {
                        b5 = this.readUCSByteStream(fStreamWrapper, encodingSupport5);
                    }
                }
                return b5;
            }
            case 11: {
                if (this.fUCSEncodingDeclReader == null) {
                    this.fUCSEncodingDeclReader = new UCSEncodingDeclReader();
                }
                this.fUCSEncodingDeclReader.setState(4, false);
                final String encoding6 = this.fUCSEncodingDeclReader.getEncoding(fStreamWrapper, this.fIsDocumentEntity);
                fStreamWrapper.rewind();
                boolean b6;
                if (encoding6 == null) {
                    b6 = this.readUTF8ByteStream(fStreamWrapper, this.fUTF8EncodingSupport);
                }
                else {
                    final EncodingSupport encodingSupport6 = IANACharset.getEncodingSupport(encoding6.toUpperCase());
                    if (encodingSupport6 == null) {
                        b6 = this.convertByteStreamWithUnknownEncoding(fStreamWrapper, encoding6);
                    }
                    else {
                        b6 = this.readUCSByteStream(fStreamWrapper, encodingSupport6);
                    }
                }
                return b6;
            }
        }
    }
    
    private static final int detectEncoding(final RewindableInputStream rewindableInputStream) {
        int n = 0;
        try {
            switch (rewindableInputStream.read()) {
                case 239: {
                    if (rewindableInputStream.read() == 187 && rewindableInputStream.read() == 191) {
                        rewindableInputStream.setStartOffset(3);
                        n = 1;
                        break;
                    }
                    break;
                }
                case 60: {
                    final int read = rewindableInputStream.read();
                    if (read == 0) {
                        final int read2 = rewindableInputStream.read();
                        if (read2 == 0) {
                            if (rewindableInputStream.read() == 0) {
                                n = 11;
                                break;
                            }
                            break;
                        }
                        else {
                            if (read2 == 63 && rewindableInputStream.read() == 0) {
                                n = 9;
                                break;
                            }
                            break;
                        }
                    }
                    else {
                        if (read == 63 && rewindableInputStream.read() == 120 && rewindableInputStream.read() == 109) {
                            n = 6;
                            break;
                        }
                        break;
                    }
                    break;
                }
                case 76: {
                    if (rewindableInputStream.read() == 111 && rewindableInputStream.read() == 167 && rewindableInputStream.read() == 148) {
                        n = 7;
                        break;
                    }
                    break;
                }
                case 0: {
                    final int read3 = rewindableInputStream.read();
                    if (read3 == 0) {
                        final int read4 = rewindableInputStream.read();
                        if (read4 == 0) {
                            if (rewindableInputStream.read() == 60) {
                                n = 10;
                                break;
                            }
                            break;
                        }
                        else {
                            if (read4 == 254 && rewindableInputStream.read() == 255) {
                                rewindableInputStream.setStartOffset(4);
                                n = 4;
                                break;
                            }
                            break;
                        }
                    }
                    else {
                        if (read3 == 60 && rewindableInputStream.read() == 0 && rewindableInputStream.read() == 63) {
                            n = 8;
                            break;
                        }
                        break;
                    }
                    break;
                }
                case 254: {
                    if (rewindableInputStream.read() == 255) {
                        rewindableInputStream.setStartOffset(2);
                        n = 2;
                        break;
                    }
                    break;
                }
                case 255: {
                    if (rewindableInputStream.read() != 254) {
                        break;
                    }
                    rewindableInputStream.setStartOffset(2);
                    n = 3;
                    if (rewindableInputStream.read() == 0 && rewindableInputStream.read() == 0) {
                        rewindableInputStream.setStartOffset(4);
                        n = 5;
                        break;
                    }
                    break;
                }
            }
        }
        catch (IOException ex) {
            throw new IOExceptionWrapper(ex);
        }
        rewindableInputStream.rewind();
        return n;
    }
    
    protected final boolean convertByteStreamWithUnknownEncoding(final InputStream inputStream, final String s) {
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(inputStream, s);
        }
        catch (IOException ex) {
            throw new IOExceptionWrapper(ex);
        }
        return this.readCharacterStream(inputStreamReader);
    }
    
    private static int checkUTF8BOM(final InputStream inputStream, final byte[] array) throws IOException {
        int n = 0;
        final int read = inputStream.read();
        if (read == 239) {
            final int read2 = inputStream.read();
            if (read2 == 187) {
                final int read3 = inputStream.read();
                if (read3 == 191) {
                    return 0;
                }
                array[n++] = -17;
                array[n++] = -69;
                if (read3 != -1) {
                    array[n++] = (byte)read3;
                    return n;
                }
            }
            else {
                array[n++] = -17;
                if (read2 != -1) {
                    array[n++] = (byte)read2;
                    return n;
                }
            }
        }
        else if (read != -1) {
            array[n++] = (byte)read;
            return n;
        }
        return n - 3;
    }
    
    protected final boolean readByteStream(final InputStream inputStream, final EncodingSupport encodingSupport, final boolean b) {
        final boolean asciiTransparent = encodingSupport.isASCIITransparent();
        final boolean b2 = this.fSkipLF != null;
        if (b2) {
            this.fSkipLF[0] = false;
        }
        byte[] array = this.fBytesPointer[0];
        if (array == null) {
            final byte[][] fBytesPointer = this.fBytesPointer;
            final int n = 0;
            final byte[] array2 = new byte[32768];
            fBytesPointer[n] = array2;
            array = array2;
        }
        int n2 = array.length;
        int n3 = 0;
        try {
            int i = 0;
            if (b && encodingSupport == this.fUTF8EncodingSupport) {
                n3 = checkUTF8BOM(inputStream, array);
                if (n3 < 0) {
                    n3 += 3;
                    i = -1;
                }
                if (n3 > 0 && asciiTransparent && b2) {
                    n3 = encodingSupport.normalizeLineBreaks(array, 0, n3, this.fSkipLF);
                }
            }
            while (i >= 0) {
                int n4 = n2 - n3;
                if (n4 == 0) {
                    n4 = n3;
                    System.arraycopy(array, 0, this.fBytesPointer[0] = new byte[n4 << 1], 0, n4);
                    array = this.fBytesPointer[0];
                    n2 = array.length;
                }
                i = inputStream.read(array, n3, n4);
                if (i >= 0) {
                    if (asciiTransparent && b2) {
                        n3 = encodingSupport.normalizeLineBreaks(array, n3, n3 + i, this.fSkipLF);
                    }
                    else {
                        n3 += i;
                    }
                }
            }
        }
        catch (IOException ex) {
            throw new IOExceptionWrapper(ex);
        }
        this.orphanBytes();
        if (!asciiTransparent && b2) {
            n3 = encodingSupport.normalizeLineBreaks(array, 0, n3, this.fSkipLF);
        }
        array[n3] = 0;
        this.getEntityContent().setValues(array, 0, n3, encodingSupport);
        return true;
    }
    
    private final void prepareForConvertToBytes() {
        if (this.fBytesPointer[0] == null) {
            this.fBytesPointer[0] = new byte[32768];
        }
        this.fOffsetPointer[0] = 0;
        if (this.fSkipLF != null) {
            this.fSkipLF[0] = false;
        }
    }
    
    private final void checkAfterConvertToBytes(final EncodingSupport encodingSupport) {
        byte[] array = this.fBytesPointer[0];
        final int n = this.fOffsetPointer[0];
        if (n == array.length) {
            array = new byte[n << 1];
            System.arraycopy(this.fBytesPointer[0], 0, array, 0, n);
            this.fBytesPointer[0] = array;
        }
        this.orphanBytes();
        array[n] = 0;
        this.getEntityContent().setValues(array, 0, n, encodingSupport);
    }
    
    protected final boolean convertByteStreamToUTF8(final InputStream inputStream, final EncodingSupport encodingSupport, final boolean b) {
        if (this.fByteIOBuffer == null) {
            this.fByteIOBuffer = new byte[32768];
        }
        if (this.fCharsPointer[0] == null) {
            this.fCharsPointer[0] = new char[32768];
        }
        this.prepareForConvertToBytes();
        encodingSupport.readCharacters(inputStream, this.fCharsPointer, this.fOffsetPointer, this.fSkipLF, this.fByteIOBuffer, b);
        final int n = this.fOffsetPointer[0];
        this.fOffsetPointer[0] = 0;
        this.fUTF8EncodingSupport.convertCharsToBytes(this.fCharsPointer[0], 0, n, this.fBytesPointer, this.fOffsetPointer);
        this.checkAfterConvertToBytes(this.fUTF8EncodingSupport);
        return true;
    }
    
    protected final boolean convertByteStream(final InputStream inputStream, final EncodingSupport encodingSupport, final EncodingSupport encodingSupport2, final boolean b) {
        if (this.fByteIOBuffer == null) {
            this.fByteIOBuffer = new byte[32768];
        }
        if (this.fCharsPointer[0] == null) {
            this.fCharsPointer[0] = new char[32768];
        }
        this.prepareForConvertToBytes();
        encodingSupport.readCharacters(inputStream, this.fCharsPointer, this.fOffsetPointer, this.fSkipLF, this.fByteIOBuffer, b);
        final int n = this.fOffsetPointer[0];
        this.fOffsetPointer[0] = 0;
        encodingSupport2.convertCharsToBytes(this.fCharsPointer[0], 0, n, this.fBytesPointer, this.fOffsetPointer);
        this.checkAfterConvertToBytes(encodingSupport2);
        return true;
    }
    
    protected final boolean convertCharacterStreamToUTF8(final Reader reader) {
        if (this.fCharIOBuffer == null) {
            this.fCharIOBuffer = new char[32768];
        }
        this.prepareForConvertToBytes();
        this.fSurrogate[0] = -1;
        try {
            while (true) {
                final int read = reader.read(this.fCharIOBuffer, 0, 32768);
                if (read == -1) {
                    break;
                }
                UTF8EncodingSupport.normalizeCharsToBytes(this.fCharIOBuffer, 0, read, this.fBytesPointer, this.fOffsetPointer, this.fSurrogate, this.fSkipLF);
            }
        }
        catch (IOException ex) {
            throw new IOExceptionWrapper(ex);
        }
        if (this.fSurrogate[0] != -1) {
            CharConversionError.partialMultiPartCharacterSequence();
        }
        this.checkAfterConvertToBytes(this.fUTF8EncodingSupport);
        return true;
    }
    
    protected final boolean convertCharacterStream(final Reader reader, final EncodingSupport encodingSupport) {
        if (this.fCharIOBuffer == null) {
            this.fCharIOBuffer = new char[32768];
        }
        this.prepareForConvertToBytes();
        try {
            while (true) {
                int n = reader.read(this.fCharIOBuffer, 0, 32768);
                if (n == -1) {
                    break;
                }
                if (this.fSkipLF != null) {
                    n = this.normalizeLineBreaks(this.fCharIOBuffer, 0, n);
                }
                encodingSupport.convertCharsToBytes(this.fCharIOBuffer, 0, n, this.fBytesPointer, this.fOffsetPointer);
            }
        }
        catch (IOException ex) {
            throw new IOExceptionWrapper(ex);
        }
        this.checkAfterConvertToBytes(encodingSupport);
        return true;
    }
    
    protected final boolean convertBytesToUTF8(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport) {
        if (this.fCharsPointer[0] == null) {
            this.fCharsPointer[0] = new char[32768];
        }
        this.fOffsetPointer[0] = 0;
        encodingSupport.convertBytesToChars(array, n, n2, this.fCharsPointer, this.fOffsetPointer);
        final int n3 = this.fOffsetPointer[0];
        this.prepareForConvertToBytes();
        this.fSurrogate[0] = -1;
        UTF8EncodingSupport.normalizeCharsToBytes(this.fCharsPointer[0], 0, n3, this.fBytesPointer, this.fOffsetPointer, this.fSurrogate, this.fSkipLF);
        if (this.fSurrogate[0] != -1) {
            CharConversionError.partialMultiPartCharacterSequence();
        }
        this.checkAfterConvertToBytes(this.fUTF8EncodingSupport);
        return true;
    }
    
    protected final boolean convertBytes(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport, final EncodingSupport encodingSupport2) {
        if (this.fCharsPointer[0] == null) {
            this.fCharsPointer[0] = new char[32768];
        }
        this.fOffsetPointer[0] = 0;
        encodingSupport.convertBytesToChars(array, n, n2, this.fCharsPointer, this.fOffsetPointer);
        final int n3 = this.fOffsetPointer[0];
        this.prepareForConvertToBytes();
        encodingSupport2.convertCharsToBytes(this.fCharsPointer[0], 0, n3, this.fBytesPointer, this.fOffsetPointer);
        this.checkAfterConvertToBytes(encodingSupport2);
        return true;
    }
    
    protected final boolean convertCharactersToUTF8(final char[] array, final int n, final int n2) {
        this.prepareForConvertToBytes();
        this.fUTF8EncodingSupport.convertCharsToBytes(array, n, n2, this.fBytesPointer, this.fOffsetPointer);
        this.checkAfterConvertToBytes(this.fUTF8EncodingSupport);
        return true;
    }
    
    protected final boolean convertCharacters(final char[] array, final int n, final int n2, final EncodingSupport encodingSupport) {
        this.prepareForConvertToBytes();
        encodingSupport.convertCharsToBytes(array, n, n2, this.fBytesPointer, this.fOffsetPointer);
        this.checkAfterConvertToBytes(encodingSupport);
        return true;
    }
    
    protected final boolean convertStringToUTF8(final String s) {
        int n = s.length();
        if (this.fCharsPointer[0] == null) {
            this.fCharsPointer[0] = new char[32768];
        }
        char[] array = this.fCharsPointer[0];
        if (n + 1 > array.length) {
            int n2;
            for (n2 = array.length << 1; n + 1 > n2; n2 <<= 1) {}
            array = new char[n2];
            this.fCharsPointer[0] = array;
        }
        s.getChars(0, n, array, 0);
        if (this.fSkipLF != null) {
            n = this.normalizeLineBreaks(array, 0, n);
        }
        this.prepareForConvertToBytes();
        this.fUTF8EncodingSupport.convertCharsToBytes(array, 0, n, this.fBytesPointer, this.fOffsetPointer);
        this.checkAfterConvertToBytes(this.fUTF8EncodingSupport);
        return true;
    }
    
    protected final boolean convertString(final String s, final EncodingSupport encodingSupport) {
        int n = s.length();
        if (this.fCharsPointer[0] == null) {
            this.fCharsPointer[0] = new char[32768];
        }
        char[] array = this.fCharsPointer[0];
        if (n + 1 > array.length) {
            int n2;
            for (n2 = array.length << 1; n + 1 > n2; n2 <<= 1) {}
            array = new char[n2];
            this.fCharsPointer[0] = array;
        }
        s.getChars(0, n, array, 0);
        if (this.fSkipLF != null) {
            n = this.normalizeLineBreaks(array, 0, n);
        }
        this.prepareForConvertToBytes();
        encodingSupport.convertCharsToBytes(array, 0, n, this.fBytesPointer, this.fOffsetPointer);
        this.checkAfterConvertToBytes(encodingSupport);
        return true;
    }
    
    protected boolean readCharacterStream(final Reader reader) {
        char[] array = this.fCharsPointer[0];
        int normalizeLineBreaks = 0;
        if (array == null) {
            final char[][] fCharsPointer = this.fCharsPointer;
            final int n = 0;
            final char[] array2 = new char[32768];
            fCharsPointer[n] = array2;
            array = array2;
        }
        int n2 = array.length;
        if (this.fSkipLF != null) {
            this.fSkipLF[0] = false;
        }
        try {
            while (true) {
                int n3 = n2 - normalizeLineBreaks;
                if (n3 == 0) {
                    n3 = normalizeLineBreaks;
                    System.arraycopy(array, 0, this.fCharsPointer[0] = new char[n3 << 1], 0, n3);
                    array = this.fCharsPointer[0];
                    n2 = array.length;
                }
                final int read = reader.read(array, normalizeLineBreaks, n3);
                if (read == -1) {
                    break;
                }
                if (this.fSkipLF != null) {
                    normalizeLineBreaks = this.normalizeLineBreaks(array, normalizeLineBreaks, normalizeLineBreaks + read);
                }
                else {
                    normalizeLineBreaks += read;
                }
            }
        }
        catch (IOException ex) {
            throw new IOExceptionWrapper(ex);
        }
        this.orphanCharacters();
        array[normalizeLineBreaks] = '\0';
        this.getEntityContent().setValues(array, 0, normalizeLineBreaks);
        return true;
    }
    
    private final void prepareForConvertToCharacters() {
        if (this.fCharsPointer[0] == null) {
            this.fCharsPointer[0] = new char[32768];
        }
        this.fOffsetPointer[0] = 0;
        if (this.fSkipLF != null) {
            this.fSkipLF[0] = false;
        }
    }
    
    private final void checkAfterConvertToCharacters() {
        char[] array = this.fCharsPointer[0];
        final int n = this.fOffsetPointer[0];
        if (n == array.length) {
            array = new char[n << 1];
            System.arraycopy(this.fCharsPointer[0], 0, array, 0, n);
            this.fCharsPointer[0] = array;
        }
        this.orphanCharacters();
        array[n] = '\0';
        this.getEntityContent().setValues(array, 0, n);
    }
    
    protected final boolean convertByteStreamToUTF16(final InputStream inputStream, final EncodingSupport encodingSupport, final boolean b) {
        if (this.fByteIOBuffer == null) {
            this.fByteIOBuffer = new byte[32768];
        }
        this.prepareForConvertToCharacters();
        encodingSupport.readCharacters(inputStream, this.fCharsPointer, this.fOffsetPointer, this.fSkipLF, this.fByteIOBuffer, b);
        this.checkAfterConvertToCharacters();
        return true;
    }
    
    protected final boolean convertBytesToUTF16(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport) {
        this.prepareForConvertToCharacters();
        encodingSupport.convertBytesToChars(array, n, n2, this.fCharsPointer, this.fOffsetPointer);
        this.checkAfterConvertToCharacters();
        return true;
    }
    
    protected final boolean convertStringToUTF16(final String s) {
        this.prepareForConvertToCharacters();
        char[] array = this.fCharsPointer[0];
        int n = s.length();
        if (n + 1 > array.length) {
            int n2;
            for (n2 = array.length << 1; n + 1 > n2; n2 <<= 1) {}
            array = new char[n2];
            this.fCharsPointer[0] = array;
        }
        this.orphanCharacters();
        s.getChars(0, n, array, 0);
        if (this.fSkipLF != null) {
            n = this.normalizeLineBreaks(array, 0, n);
        }
        array[n] = '\0';
        this.getEntityContent().setValues(array, 0, n);
        return true;
    }
    
    private final void orphanBytes() {
        final byte[] array = this.fBytesPointer[0];
        int n = 1 + this.fFreeBytesPointerCount;
        final int n2 = n + this.fInUseBytesPointerCount;
        if (n > 1) {
            --n;
            --this.fFreeBytesPointerCount;
            this.fBytesPointer[0] = this.fBytesPointer[n];
            this.fBytesPointer[n] = array;
        }
        else {
            this.fBytesPointer[0] = null;
            if (n2 == this.fBytesPointer.length) {
                final byte[][] fBytesPointer = new byte[n2 << 1][];
                System.arraycopy(this.fBytesPointer, 0, fBytesPointer, 0, n2);
                this.fBytesPointer = fBytesPointer;
            }
            this.fBytesPointer[n2] = array;
        }
        ++this.fInUseBytesPointerCount;
    }
    
    private final void orphanCharacters() {
        final char[] array = this.fCharsPointer[0];
        int n = 1 + this.fFreeCharsPointerCount;
        final int n2 = n + this.fInUseCharsPointerCount;
        if (n > 1) {
            --n;
            --this.fFreeCharsPointerCount;
            this.fCharsPointer[0] = this.fCharsPointer[n];
            this.fCharsPointer[n] = array;
        }
        else {
            this.fCharsPointer[0] = null;
            if (n2 == this.fCharsPointer.length) {
                final char[][] fCharsPointer = new char[n2 << 1][];
                System.arraycopy(this.fCharsPointer, 0, fCharsPointer, 0, n2);
                this.fCharsPointer = fCharsPointer;
            }
            this.fCharsPointer[n2] = array;
        }
        ++this.fInUseCharsPointerCount;
    }
    
    protected final void releaseBytes(final byte[] array) {
        final int n = 1 + this.fFreeBytesPointerCount;
        int n2 = n + this.fInUseBytesPointerCount;
        int n3 = 0;
        for (int i = n; i < n2; ++i) {
            if (this.fBytesPointer[i] == array) {
                n3 = i;
                break;
            }
        }
        if (n3 == 0) {
            return;
        }
        --n2;
        if (n3 < n2) {
            this.fBytesPointer[n3] = this.fBytesPointer[n2];
        }
        --this.fInUseBytesPointerCount;
        if (this.fBytesPointer[0] == null) {
            this.fBytesPointer[0] = array;
            this.fBytesPointer[n2] = null;
        }
        else {
            this.fBytesPointer[n2] = this.fBytesPointer[n];
            this.fBytesPointer[n] = array;
            ++this.fFreeBytesPointerCount;
        }
    }
    
    protected final void releaseCharacters(final char[] array) {
        final int n = 1 + this.fFreeCharsPointerCount;
        int n2 = n + this.fInUseCharsPointerCount;
        int n3 = 0;
        for (int i = n; i < n2; ++i) {
            if (this.fCharsPointer[i] == array) {
                n3 = i;
                break;
            }
        }
        if (n3 == 0) {
            return;
        }
        --n2;
        if (n3 < n2) {
            this.fCharsPointer[n3] = this.fCharsPointer[n2];
        }
        --this.fInUseCharsPointerCount;
        if (this.fCharsPointer[0] == null) {
            this.fCharsPointer[0] = array;
            this.fCharsPointer[n2] = null;
        }
        else {
            this.fCharsPointer[n2] = this.fCharsPointer[n];
            this.fCharsPointer[n] = array;
            ++this.fFreeCharsPointerCount;
        }
    }
    
    private final int normalizeLineBreaks(final char[] array, int n, final int n2) {
        boolean b = this.fSkipLF[0];
        for (int i = n; i < n2; ++i) {
            char c = array[i];
            if (b) {
                b = false;
                if (c == '\n') {
                    continue;
                }
            }
            if (c == '\r') {
                c = '\n';
                b = true;
            }
            array[n++] = c;
        }
        this.fSkipLF[0] = b;
        return n;
    }
}
