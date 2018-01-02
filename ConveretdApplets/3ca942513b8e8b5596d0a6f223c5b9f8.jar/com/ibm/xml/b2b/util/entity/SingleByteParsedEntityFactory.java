// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import java.io.InputStream;
import com.ibm.xml.b2b.util.EncodingSupport;
import java.io.Reader;
import com.ibm.xml.b2b.util.SingleByteStringBuffer;
import com.ibm.xml.b2b.util.XMLStringBuffer;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.SingleByteEncodingSupport;

public final class SingleByteParsedEntityFactory extends ParsedEntityRecognizer implements ParsedEntityFactory
{
    private static final int FREE_ENTITY_COUNT = 8;
    private SingleByteEncodingSupport fEncoding;
    private int fFreeEntityCount;
    private ParsedEntity[] fFreeEntities;
    private ParsedEntity fEntityContent;
    private EntityInputSource fStringSource;
    
    public SingleByteParsedEntityFactory(final boolean b, final SingleByteEncodingSupport fEncoding) {
        super(b);
        this.fEncoding = fEncoding;
        this.fFreeEntities = new ParsedEntity[8];
    }
    
    public void reset(final boolean b) {
        super.reset(b);
        if (!b) {
            while (this.fFreeEntityCount > 0) {
                --this.fFreeEntityCount;
                this.fFreeEntities[this.fFreeEntityCount] = null;
            }
            if (this.fStringSource != null) {
                this.fStringSource = null;
            }
        }
    }
    
    public ParsedEntity createParsedEntity(final EntityInputSource entityInputSource, final boolean b) {
        if (this.fFreeEntityCount > 0) {
            final ParsedEntity[] fFreeEntities = this.fFreeEntities;
            final int fFreeEntityCount = this.fFreeEntityCount - 1;
            this.fFreeEntityCount = fFreeEntityCount;
            this.fEntityContent = fFreeEntities[fFreeEntityCount];
        }
        else {
            this.fEntityContent = new ByteArrayParsedEntity(this);
        }
        this.fEntityContent.clear();
        if (!super.setSource(entityInputSource, b, true)) {
            return null;
        }
        return this.fEntityContent;
    }
    
    public ParsedEntity createParsedEntity(final XMLString content) {
        if (this.fStringSource == null) {
            this.fStringSource = new EntityInputSource();
        }
        this.fStringSource.setContent(content);
        if (this.fFreeEntityCount > 0) {
            final ParsedEntity[] fFreeEntities = this.fFreeEntities;
            final int fFreeEntityCount = this.fFreeEntityCount - 1;
            this.fFreeEntityCount = fFreeEntityCount;
            this.fEntityContent = fFreeEntities[fFreeEntityCount];
        }
        else {
            this.fEntityContent = new ByteArrayParsedEntity(this);
        }
        this.fEntityContent.clear();
        if (!super.setSource(this.fStringSource, false, false)) {
            return null;
        }
        return this.fEntityContent;
    }
    
    public void releaseParsedEntity(final ParsedEntity parsedEntity) {
        super.releaseBytes(parsedEntity.bytes);
        if (this.fFreeEntityCount < 8) {
            this.fFreeEntities[this.fFreeEntityCount++] = parsedEntity;
        }
    }
    
    public XMLStringBuffer createStringBuffer() {
        return new SingleByteStringBuffer(this.fEncoding);
    }
    
    protected XMLString getEntityContent() {
        return this.fEntityContent;
    }
    
    protected boolean setCharacterStreamSource(final Reader reader) {
        return super.convertCharacterStream(reader, this.fEncoding);
    }
    
    protected boolean setByteStreamSource(final InputStream inputStream, final EncodingSupport encodingSupport) {
        if (encodingSupport == this.fEncoding) {
            return super.readByteStream(inputStream, encodingSupport, true);
        }
        return super.convertByteStream(inputStream, encodingSupport, this.fEncoding, true);
    }
    
    protected boolean setByteArraySource(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport) {
        if (encodingSupport == this.fEncoding) {
            this.fEntityContent.setValues(array, n, n2, encodingSupport);
            return true;
        }
        return super.convertBytes(array, n, n2, encodingSupport, this.fEncoding);
    }
    
    protected boolean setCharArraySource(final char[] array, final int n, final int n2) {
        return super.convertCharacters(array, n, n2, this.fEncoding);
    }
    
    protected boolean setStringSource(final String s) {
        return super.convertString(s, this.fEncoding);
    }
    
    protected boolean readUTF8ByteStream(final InputStream inputStream, final EncodingSupport encodingSupport) {
        return super.convertByteStream(inputStream, encodingSupport, this.fEncoding, false);
    }
    
    protected boolean readUCSByteStreamWithBOM(final InputStream inputStream, final EncodingSupport encodingSupport) {
        return super.convertByteStream(inputStream, encodingSupport, this.fEncoding, false);
    }
    
    protected boolean readLatinByteStream(final InputStream inputStream, final EncodingSupport encodingSupport) {
        if (encodingSupport == this.fEncoding) {
            return super.readByteStream(inputStream, encodingSupport, false);
        }
        return super.convertByteStream(inputStream, encodingSupport, this.fEncoding, false);
    }
    
    protected boolean readEBCDICByteStream(final InputStream inputStream, final EncodingSupport encodingSupport) {
        if (encodingSupport == this.fEncoding) {
            return super.readByteStream(inputStream, encodingSupport, false);
        }
        return super.convertByteStream(inputStream, encodingSupport, this.fEncoding, false);
    }
    
    protected boolean readUCSByteStream(final InputStream inputStream, final EncodingSupport encodingSupport) {
        return super.convertByteStream(inputStream, encodingSupport, this.fEncoding, true);
    }
    
    protected boolean readCharacterStream(final Reader reader) {
        return super.convertCharacterStream(reader, this.fEncoding);
    }
}
