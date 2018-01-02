// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.msg;

import java.util.ListResourceBundle;

public class ImplementationMessages extends ListResourceBundle
{
    public static final Object[][] CONTENTS;
    
    public Object[][] getContents() {
        return ImplementationMessages.CONTENTS;
    }
    
    static {
        CONTENTS = new Object[][] { { "BadMajorCode", "The majorCode parameter to createMessage was out of bounds." }, { "FormatFailed", "An internal error occurred while formatting the following message:\n  " }, { "ENC4", "Invalid UTF-8 code. (byte: 0x{0})" }, { "ENC5", "Invalid UTF-8 code. (bytes: 0x{0} 0x{1})" }, { "ENC6", "Invalid UTF-8 code. (bytes: 0x{0} 0x{1} 0x{2})" }, { "ENC7", "Invalid UTF-8 code. (bytes: 0x{0} 0x{1} 0x{2} 0x{3})" }, { "FileNotFound", "File \"{0}\" not found." }, { "VAL_BST", "Invalid ContentSpecNode.NODE_XXX value for binary op CMNode" }, { "VAL_CMSI", "Invalid CMStateSet bit index" }, { "VAL_CST", "Unknown ContentSpecNode.NODE_XXX value" }, { "VAL_LST", "Invalid ContentSpecNode.NODE_XXX value for leaf CMNode" }, { "VAL_NIICM", "Only * unary ops should be in the internal content model tree" }, { "VAL_NPCD", "PCData node found in non-mixed model content" }, { "VAL_UST", "Invalid ContentSpecNode.NODE_XXX value for unary op CMNode" }, { "VAL_WCGHI", "The input to whatCanGoHere() is inconsistent" }, { "INT_DCN", "Internal Error: dataChunk == NULL" }, { "INT_PCN", "Internal Error: fPreviousChunk == NULL" }, { "FatalError", "Stopping after fatal error: {0}" } };
    }
}
