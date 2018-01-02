// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.properties.client;

import java.io.IOException;
import java.util.Vector;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class XMLInputStream extends PushbackInputStream
{
    private String[] attributes;
    private StringBuffer lastToken;
    private boolean tagIsClosing;
    private String tagName;
    private String[] values;
    
    public XMLInputStream(final InputStream in) {
        super(in);
    }
    
    private void determineAttributes() {
        final StringTokenizer st = new StringTokenizer(this.lastToken.toString().trim(), "= \"'");
        if (st.countTokens() > 1) {
            final Vector tempAttributes = new Vector();
            final Vector tempValues = new Vector();
            st.nextToken();
            while (st.hasMoreElements()) {
                final String tempAttribute = st.nextToken();
                if (st.hasMoreElements()) {
                    final String tempValue = st.nextToken();
                    if (tempAttribute == null || tempValue == null) {
                        continue;
                    }
                    tempAttributes.addElement(tempAttribute);
                    tempValues.addElement(tempValue);
                }
            }
            tempAttributes.copyInto(this.attributes = new String[tempAttributes.size()]);
            tempValues.copyInto(this.values = new String[tempValues.size()]);
        }
    }
    
    private void determineName() {
        final StringBuffer temp = new StringBuffer();
        int i = 0;
        while (this.lastToken.charAt(i++) != '<') {}
        while (this.lastToken.charAt(i) == ' ' || this.lastToken.charAt(i) == '<' || this.lastToken.charAt(i) == '/') {
            if (this.lastToken.charAt(i) == '/') {
                this.tagIsClosing = true;
            }
            ++i;
        }
        while (this.lastToken.charAt(i) != ' ' && this.lastToken.charAt(i) != '>') {
            temp.append(this.lastToken.charAt(i++));
        }
        this.tagName = temp.toString();
    }
    
    public String getAttributeValue(final String attribute) {
        if (this.attributes == null) {
            return null;
        }
        for (int i = 0; i < this.attributes.length; ++i) {
            if (this.attributes[i].equals(attribute)) {
                return this.values[i];
            }
        }
        return null;
    }
    
    public String getNextToken() throws IOException {
        this.lastToken = new StringBuffer();
        this.tagName = "";
        this.tagIsClosing = false;
        this.attributes = null;
        int i = this.read();
        if (i == -1) {
            return null;
        }
        boolean inTag = false;
        while (i != -1) {
            final char c = (char)i;
            if (inTag) {
                this.lastToken.append(c);
                if (c == '>') {
                    final String s = this.lastToken.toString().trim();
                    if (s.indexOf("<!--") != 0) {
                        this.determineName();
                        if (!this.isTagClosing()) {
                            this.determineAttributes();
                        }
                        return s;
                    }
                    this.lastToken = new StringBuffer();
                    inTag = false;
                }
            }
            else {
                if (c == '<') {
                    if (this.lastToken.toString().trim().length() != 0) {
                        this.unread(i);
                        return this.lastToken.toString();
                    }
                    inTag = true;
                }
                this.lastToken.append(c);
            }
            i = this.read();
        }
        return this.lastToken.toString();
    }
    
    public String getTagName() {
        return this.tagName;
    }
    
    public boolean isTagClosing() {
        return this.tagIsClosing;
    }
}
