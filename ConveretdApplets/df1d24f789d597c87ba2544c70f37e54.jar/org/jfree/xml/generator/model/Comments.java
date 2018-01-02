// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

public class Comments
{
    private String[] openTagComment;
    private String[] closeTagComment;
    
    public Comments(final String[] openTagComment, final String[] closeTagComment) {
        this.openTagComment = openTagComment;
        this.closeTagComment = closeTagComment;
    }
    
    public String[] getOpenTagComment() {
        return this.openTagComment;
    }
    
    public String[] getCloseTagComment() {
        return this.closeTagComment;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Comments:={open=");
        if (this.openTagComment == null) {
            sb.append("null");
        }
        else {
            sb.append("{");
            for (int i = 0; i < this.openTagComment.length; ++i) {
                sb.append("[");
                sb.append(this.openTagComment[i]);
                sb.append("]");
            }
            sb.append("}");
        }
        sb.append(", close=");
        if (this.closeTagComment == null) {
            sb.append("null");
        }
        else {
            sb.append("{");
            for (int j = 0; j < this.closeTagComment.length; ++j) {
                sb.append("[");
                sb.append(this.closeTagComment[j]);
                sb.append("]");
            }
            sb.append("}");
        }
        sb.append("}");
        return sb.toString();
    }
}
