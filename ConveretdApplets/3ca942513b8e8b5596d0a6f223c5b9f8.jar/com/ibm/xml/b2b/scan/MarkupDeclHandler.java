// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;

public interface MarkupDeclHandler
{
    public static final int SEPARATOR_CHOICE = 0;
    public static final int SEPARATOR_SEQUENCE = 1;
    public static final int OCCURRENCE_ZERO_OR_ONE = 0;
    public static final int OCCURRENCE_ZERO_OR_MORE = 1;
    public static final int OCCURRENCE_ONE_OR_MORE = 2;
    
    void startElementDecl(final QName p0);
    
    void contentModelANY();
    
    void contentModelEMPTY();
    
    void contentModelStartGroup();
    
    void contentModelPCDATA();
    
    void contentModelElement(final QName p0);
    
    void contentModelSeparator(final int p0);
    
    void contentModelOccurrence(final int p0);
    
    void contentModelEndGroup();
    
    void endElementDecl();
    
    void startAttlistDecl(final QName p0);
    
    void startAttDef(final QName p0, final XMLString p1);
    
    void startEnumerationType();
    
    void enumerationType(final XMLString p0);
    
    void endEnumerationType();
    
    void startDefaultAttValue();
    
    void defaultAttValueCharacters(final XMLString p0);
    
    void defaultAttValueCharacter(final int p0, final boolean p1);
    
    boolean entityReferenceInDefaultAttValue(final XMLName p0);
    
    void endAttDef(final XMLString p0);
    
    void endAttlistDecl();
    
    void startEntityValue();
    
    void entityValueCharacters(final XMLString p0);
    
    void entityValueCharacter(final int p0);
    
    void entityReferenceInEntityValue(final XMLName p0);
    
    boolean peReferenceInEntityValue(final XMLName p0);
    
    void internalEntityDecl(final XMLName p0);
    
    void externalEntityDecl(final XMLName p0, final XMLString p1, final XMLString p2);
    
    void unparsedEntityDecl(final XMLName p0, final XMLString p1, final XMLString p2, final XMLName p3);
    
    void internalPEDecl(final XMLName p0);
    
    void externalPEDecl(final XMLName p0, final XMLString p1, final XMLString p2);
    
    void notationDecl(final XMLName p0, final XMLString p1, final XMLString p2);
    
    void processingInstruction(final XMLName p0, final XMLString p1);
    
    void comment(final XMLString p0);
}
