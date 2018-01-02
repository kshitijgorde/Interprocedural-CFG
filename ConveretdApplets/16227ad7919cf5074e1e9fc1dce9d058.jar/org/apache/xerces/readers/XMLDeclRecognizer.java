// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import org.apache.xerces.utils.ChunkyByteArray;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.framework.XMLErrorReporter;
import java.util.Stack;

public abstract class XMLDeclRecognizer
{
    private static final char[] xml_string;
    private static final char[] version_string;
    private static final char[] encoding_string;
    
    public static void registerDefaultRecognizers(final Stack stack) {
        stack.push(new EBCDICRecognizer());
        stack.push(new UCSRecognizer());
        stack.push(new UTF8Recognizer());
    }
    
    public abstract XMLEntityHandler.EntityReader recognize(final XMLEntityReaderFactory p0, final XMLEntityHandler p1, final XMLErrorReporter p2, final boolean p3, final StringPool p4, final ChunkyByteArray p5, final boolean p6, final boolean p7) throws Exception;
    
    protected int prescanXMLDeclOrTextDecl(final XMLEntityHandler.EntityReader entityReader, final boolean b) throws Exception {
        if (!entityReader.lookingAtChar('<', true)) {
            return -1;
        }
        if (!entityReader.lookingAtChar('?', true)) {
            return -1;
        }
        if (!entityReader.skippedString(XMLDeclRecognizer.xml_string)) {
            return -1;
        }
        entityReader.skipPastSpaces();
        if (entityReader.skippedString(XMLDeclRecognizer.version_string)) {
            entityReader.skipPastSpaces();
            if (!entityReader.lookingAtChar('=', true)) {
                return -1;
            }
            entityReader.skipPastSpaces();
            if (entityReader.scanStringLiteral() < 0) {
                return -1;
            }
            if (!entityReader.lookingAtSpace(true)) {
                return -1;
            }
            entityReader.skipPastSpaces();
        }
        else if (b) {
            return -1;
        }
        if (!entityReader.skippedString(XMLDeclRecognizer.encoding_string)) {
            return -1;
        }
        entityReader.skipPastSpaces();
        if (!entityReader.lookingAtChar('=', true)) {
            return -1;
        }
        entityReader.skipPastSpaces();
        return entityReader.scanStringLiteral();
    }
    
    static {
        xml_string = new char[] { 'x', 'm', 'l' };
        version_string = new char[] { 'v', 'e', 'r', 's', 'i', 'o', 'n' };
        encoding_string = new char[] { 'e', 'n', 'c', 'o', 'd', 'i', 'n', 'g' };
    }
}
