// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml;

import org.xml.sax.SAXException;
import java.util.ArrayList;
import org.xml.sax.ext.LexicalHandler;

public class CommentHandler implements LexicalHandler
{
    public static final String OPEN_TAG_COMMENT = "parser.comment.open";
    public static final String CLOSE_TAG_COMMENT = "parser.comment.close";
    private final ArrayList comment;
    private boolean inDTD;
    
    public CommentHandler() {
        this.comment = new ArrayList();
    }
    
    public void startDTD(final String s, final String s2, final String s3) throws SAXException {
        this.inDTD = true;
    }
    
    public void endDTD() throws SAXException {
        this.inDTD = false;
    }
    
    public void startEntity(final String s) throws SAXException {
    }
    
    public void endEntity(final String s) throws SAXException {
    }
    
    public void startCDATA() throws SAXException {
    }
    
    public void endCDATA() throws SAXException {
    }
    
    public void comment(final char[] array, final int n, final int n2) throws SAXException {
        if (!this.inDTD) {
            this.comment.add(new String(array, n, n2));
        }
    }
    
    public String[] getComments() {
        if (this.comment.isEmpty()) {
            return null;
        }
        return this.comment.toArray(new String[this.comment.size()]);
    }
    
    public void clearComments() {
        this.comment.clear();
    }
}
