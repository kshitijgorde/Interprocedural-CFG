// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xml.utils.PrefixResolver;
import org.w3c.dom.Node;
import org.apache.xpath.XPathContext;
import org.apache.xpath.XPath;
import org.apache.xml.utils.FastStringBuffer;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.apache.xalan.res.XSLMessages;
import org.apache.xml.utils.StringBufferPool;
import java.util.StringTokenizer;
import org.apache.xalan.processor.StylesheetHandler;
import java.util.Vector;
import java.io.Serializable;

public class AVT implements Serializable
{
    private String m_simpleString;
    private Vector m_parts;
    private String m_rawName;
    private String m_name;
    private String m_uri;
    
    public AVT(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String stringedValue) throws TransformerException {
        this.m_simpleString = null;
        this.m_parts = null;
        this.m_uri = uri;
        this.m_name = name;
        this.m_rawName = rawName;
        final StringTokenizer tokenizer = new StringTokenizer(stringedValue, "{}\"'", true);
        final int nTokens = tokenizer.countTokens();
        if (nTokens < 2) {
            this.m_simpleString = stringedValue;
        }
        else {
            final FastStringBuffer buffer = StringBufferPool.get();
            final FastStringBuffer exprBuffer = StringBufferPool.get();
            try {
                this.m_parts = new Vector(nTokens + 1);
                String t = null;
                String lookahead = null;
                String error = null;
                while (tokenizer.hasMoreTokens()) {
                    if (lookahead != null) {
                        t = lookahead;
                        lookahead = null;
                    }
                    else {
                        t = tokenizer.nextToken();
                    }
                    if (t.length() == 1) {
                        switch (t.charAt(0)) {
                            case '\"':
                            case '\'': {
                                buffer.append(t);
                                break;
                            }
                            case '{': {
                                lookahead = tokenizer.nextToken();
                                if (lookahead.equals("{")) {
                                    buffer.append(lookahead);
                                    lookahead = null;
                                    break;
                                }
                                if (buffer.length() > 0) {
                                    this.m_parts.addElement(new AVTPartSimple(buffer.toString()));
                                    buffer.setLength(0);
                                }
                                exprBuffer.setLength(0);
                                while (lookahead != null) {
                                    if (lookahead.length() == 1) {
                                        switch (lookahead.charAt(0)) {
                                            case '\"':
                                            case '\'': {
                                                exprBuffer.append(lookahead);
                                                String quote;
                                                for (quote = lookahead, lookahead = tokenizer.nextToken(); !lookahead.equals(quote); lookahead = tokenizer.nextToken()) {
                                                    exprBuffer.append(lookahead);
                                                }
                                                exprBuffer.append(lookahead);
                                                lookahead = tokenizer.nextToken();
                                                continue;
                                            }
                                            case '{': {
                                                error = XSLMessages.createMessage(1, null);
                                                continue;
                                            }
                                            case '}': {
                                                buffer.setLength(0);
                                                final XPath xpath = handler.createXPath(exprBuffer.toString());
                                                this.m_parts.addElement(new AVTPartXPath(xpath));
                                                lookahead = null;
                                                continue;
                                            }
                                            default: {
                                                exprBuffer.append(lookahead);
                                                lookahead = tokenizer.nextToken();
                                                continue;
                                            }
                                        }
                                    }
                                    else {
                                        exprBuffer.append(lookahead);
                                        lookahead = tokenizer.nextToken();
                                    }
                                }
                                if (error != null) {
                                    break;
                                }
                                break;
                            }
                            case '}': {
                                lookahead = tokenizer.nextToken();
                                if (lookahead.equals("}")) {
                                    buffer.append(lookahead);
                                    lookahead = null;
                                    break;
                                }
                                try {
                                    handler.warn(1, null);
                                }
                                catch (SAXException se) {
                                    throw new TransformerException(se);
                                }
                                buffer.append("}");
                                break;
                            }
                            default: {
                                buffer.append(t);
                                break;
                            }
                        }
                    }
                    else {
                        buffer.append(t);
                    }
                    if (error != null) {
                        try {
                            handler.warn(14, new Object[] { error });
                            break;
                        }
                        catch (SAXException se) {
                            throw new TransformerException(se);
                        }
                    }
                }
                if (buffer.length() > 0) {
                    this.m_parts.addElement(new AVTPartSimple(buffer.toString()));
                    buffer.setLength(0);
                }
            }
            finally {
                StringBufferPool.free(buffer);
                StringBufferPool.free(exprBuffer);
            }
        }
        if (this.m_parts == null && this.m_simpleString == null) {
            this.m_simpleString = "";
        }
    }
    
    public boolean canTraverseOutsideSubtree() {
        if (this.m_parts != null) {
            for (int n = this.m_parts.size(), i = 0; i < n; ++i) {
                final AVTPart part = this.m_parts.elementAt(i);
                if (part.canTraverseOutsideSubtree()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public String evaluate(final XPathContext xctxt, final Node context, final PrefixResolver nsNode) throws TransformerException {
        final FastStringBuffer buf = StringBufferPool.get();
        try {
            if (this.m_simpleString != null) {
                return this.m_simpleString;
            }
            if (this.m_parts != null) {
                buf.setLength(0);
                for (int n = this.m_parts.size(), i = 0; i < n; ++i) {
                    final AVTPart part = this.m_parts.elementAt(i);
                    part.evaluate(xctxt, buf, context, nsNode);
                }
                return buf.toString();
            }
            return "";
        }
        finally {
            StringBufferPool.free(buf);
        }
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public String getRawName() {
        return this.m_rawName;
    }
    
    public String getSimpleString() {
        if (this.m_simpleString != null) {
            return this.m_simpleString;
        }
        if (this.m_parts != null) {
            final FastStringBuffer buf = StringBufferPool.get();
            String s;
            try {
                buf.setLength(0);
                for (int n = this.m_parts.size(), i = 0; i < n; ++i) {
                    final AVTPart part = this.m_parts.elementAt(i);
                    buf.append(part.getSimpleString());
                }
                s = buf.toString();
            }
            finally {
                StringBufferPool.free(buf);
            }
            return s;
        }
        return "";
    }
    
    public String getURI() {
        return this.m_uri;
    }
    
    public boolean isContextInsensitive() {
        return this.m_simpleString != null;
    }
    
    public void setName(final String name) {
        this.m_name = name;
    }
    
    public void setRawName(final String rawName) {
        this.m_rawName = rawName;
    }
    
    public void setURI(final String uri) {
        this.m_uri = uri;
    }
}
