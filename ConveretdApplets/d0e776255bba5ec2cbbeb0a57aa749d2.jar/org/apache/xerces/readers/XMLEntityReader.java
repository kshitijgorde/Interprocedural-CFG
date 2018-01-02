// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import org.apache.xerces.utils.StringPool;
import org.apache.xerces.utils.QName;
import org.apache.xerces.framework.XMLErrorReporter;

abstract class XMLEntityReader implements XMLEntityHandler.EntityReader
{
    protected XMLEntityHandler fEntityHandler;
    protected XMLErrorReporter fErrorReporter;
    protected boolean fSendCharDataAsCharArray;
    protected XMLEntityHandler.CharDataHandler fCharDataHandler;
    protected boolean fInCDSect;
    private boolean fStillActive;
    protected int fCarriageReturnCounter;
    protected int fLinefeedCounter;
    protected int fCharacterCounter;
    protected int fCurrentOffset;
    
    protected XMLEntityReader(final XMLEntityHandler fEntityHandler, final XMLErrorReporter fErrorReporter, final boolean fSendCharDataAsCharArray) {
        this.fEntityHandler = null;
        this.fErrorReporter = null;
        this.fCharDataHandler = null;
        this.fInCDSect = false;
        this.fStillActive = true;
        this.fCarriageReturnCounter = 1;
        this.fLinefeedCounter = 1;
        this.fCharacterCounter = 1;
        this.fCurrentOffset = 0;
        this.fEntityHandler = fEntityHandler;
        this.fErrorReporter = fErrorReporter;
        this.fSendCharDataAsCharArray = fSendCharDataAsCharArray;
        this.fCharDataHandler = this.fEntityHandler.getCharDataHandler();
    }
    
    protected XMLEntityReader(final XMLEntityHandler fEntityHandler, final XMLErrorReporter fErrorReporter, final boolean fSendCharDataAsCharArray, final int fLinefeedCounter, final int fCharacterCounter) {
        this.fEntityHandler = null;
        this.fErrorReporter = null;
        this.fCharDataHandler = null;
        this.fInCDSect = false;
        this.fStillActive = true;
        this.fCarriageReturnCounter = 1;
        this.fLinefeedCounter = 1;
        this.fCharacterCounter = 1;
        this.fCurrentOffset = 0;
        this.fEntityHandler = fEntityHandler;
        this.fErrorReporter = fErrorReporter;
        this.fSendCharDataAsCharArray = fSendCharDataAsCharArray;
        this.fCharDataHandler = this.fEntityHandler.getCharDataHandler();
        this.fLinefeedCounter = fLinefeedCounter;
        this.fCharacterCounter = fCharacterCounter;
    }
    
    protected void init(final XMLEntityHandler fEntityHandler, final XMLErrorReporter fErrorReporter, final boolean fSendCharDataAsCharArray, final int fLinefeedCounter, final int fCharacterCounter) {
        this.fEntityHandler = fEntityHandler;
        this.fErrorReporter = fErrorReporter;
        this.fSendCharDataAsCharArray = fSendCharDataAsCharArray;
        this.fCharDataHandler = this.fEntityHandler.getCharDataHandler();
        this.fLinefeedCounter = fLinefeedCounter;
        this.fCharacterCounter = fCharacterCounter;
        this.fStillActive = true;
        this.fInCDSect = false;
        this.fCarriageReturnCounter = 1;
        this.fCurrentOffset = 0;
    }
    
    public int currentOffset() {
        return this.fCurrentOffset;
    }
    
    public int getLineNumber() {
        if (this.fLinefeedCounter > 1) {
            return this.fLinefeedCounter;
        }
        return this.fCarriageReturnCounter;
    }
    
    public int getColumnNumber() {
        return this.fCharacterCounter;
    }
    
    public void setInCDSect(final boolean fInCDSect) {
        this.fInCDSect = fInCDSect;
    }
    
    public boolean getInCDSect() {
        return this.fInCDSect;
    }
    
    protected XMLEntityHandler.EntityReader changeReaders() throws Exception {
        XMLEntityHandler.EntityReader changeReaders = null;
        if (this.fStillActive) {
            changeReaders = this.fEntityHandler.changeReaders();
            this.fStillActive = false;
        }
        return changeReaders;
    }
    
    public abstract int scanContent(final QName p0) throws Exception;
    
    public abstract void scanQName(final char p0, final QName p1) throws Exception;
    
    public abstract boolean scanExpectedName(final char p0, final StringPool.CharArrayRange p1) throws Exception;
    
    public abstract int scanName(final char p0) throws Exception;
    
    public abstract int scanEntityValue(final int p0, final boolean p1) throws Exception;
    
    public abstract int scanAttValue(final char p0, final boolean p1) throws Exception;
    
    public abstract int scanStringLiteral() throws Exception;
    
    public abstract int scanCharRef(final boolean p0) throws Exception;
    
    public abstract int scanInvalidChar() throws Exception;
    
    public abstract boolean skippedString(final char[] p0) throws Exception;
    
    public abstract void skipPastNmtoken(final char p0) throws Exception;
    
    public abstract void skipPastName(final char p0) throws Exception;
    
    public abstract void skipPastSpaces() throws Exception;
    
    public abstract void skipToChar(final char p0) throws Exception;
    
    public abstract boolean lookingAtSpace(final boolean p0) throws Exception;
    
    public abstract boolean lookingAtValidChar(final boolean p0) throws Exception;
    
    public abstract boolean lookingAtChar(final char p0, final boolean p1) throws Exception;
    
    public abstract int addSymbol(final int p0, final int p1);
    
    public abstract int addString(final int p0, final int p1);
    
    public abstract void append(final XMLEntityHandler.CharBuffer p0, final int p1, final int p2);
}
