// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.XPathContext;
import org.apache.xpath.XPath;
import org.apache.xml.utils.FastStringBuffer;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import java.util.NoSuchElementException;
import org.apache.xalan.res.XSLMessages;
import org.apache.xml.utils.StringBufferPool;
import java.util.StringTokenizer;
import org.apache.xalan.processor.StylesheetHandler;
import java.util.Vector;
import java.io.Serializable;

public class AVT implements Serializable, XSLTVisitable
{
    private String m_simpleString;
    private Vector m_parts;
    private String m_rawName;
    private String m_name;
    private String m_uri;
    
    public String getRawName() {
        return this.m_rawName;
    }
    
    public void setRawName(final String rawName) {
        this.m_rawName = rawName;
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public void setName(final String name) {
        this.m_name = name;
    }
    
    public String getURI() {
        return this.m_uri;
    }
    
    public void setURI(final String uri) {
        this.m_uri = uri;
    }
    
    public AVT(final StylesheetHandler handler, final String uri, final String name, final String rawName, final String stringedValue, final ElemTemplateElement owner) throws TransformerException {
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
                                try {
                                    lookahead = tokenizer.nextToken();
                                    if (lookahead.equals("{")) {
                                        buffer.append(lookahead);
                                        lookahead = null;
                                    }
                                    else {
                                        if (buffer.length() > 0) {
                                            this.m_parts.addElement(new AVTPartSimple(buffer.toString()));
                                            buffer.setLength(0);
                                        }
                                        exprBuffer.setLength(0);
                                        while (null != lookahead) {
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
                                                        error = XSLMessages.createMessage("ER_NO_CURLYBRACE", null);
                                                        lookahead = null;
                                                        continue;
                                                    }
                                                    case '}': {
                                                        buffer.setLength(0);
                                                        final XPath xpath = handler.createXPath(exprBuffer.toString(), owner);
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
                                        if (error != null) {}
                                    }
                                }
                                catch (NoSuchElementException ex) {
                                    error = XSLMessages.createMessage("ER_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { name, stringedValue });
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
                                    handler.warn("WG_FOUND_CURLYBRACE", null);
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
                    if (null != error) {
                        try {
                            handler.warn("WG_ATTR_TEMPLATE", new Object[] { error });
                        }
                        catch (SAXException se) {
                            throw new TransformerException(se);
                        }
                        break;
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
        if (null == this.m_parts && null == this.m_simpleString) {
            this.m_simpleString = "";
        }
    }
    
    public String getSimpleString() {
        if (null != this.m_simpleString) {
            return this.m_simpleString;
        }
        if (null != this.m_parts) {
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
    
    public String evaluate(final XPathContext xctxt, final int context, final PrefixResolver nsNode) throws TransformerException {
        final FastStringBuffer buf = StringBufferPool.get();
        try {
            if (null != this.m_simpleString) {
                return this.m_simpleString;
            }
            if (null != this.m_parts) {
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
    
    public boolean isContextInsensitive() {
        return null != this.m_simpleString;
    }
    
    public boolean canTraverseOutsideSubtree() {
        if (null != this.m_parts) {
            for (int n = this.m_parts.size(), i = 0; i < n; ++i) {
                final AVTPart part = this.m_parts.elementAt(i);
                if (part.canTraverseOutsideSubtree()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        if (null != this.m_parts) {
            for (int n = this.m_parts.size(), i = 0; i < n; ++i) {
                final AVTPart part = this.m_parts.elementAt(i);
                part.fixupVariables(vars, globalsSize);
            }
        }
    }
    
    public void callVisitors(final XSLTVisitor visitor) {
        if (visitor.visitAVT(this) && null != this.m_parts) {
            for (int n = this.m_parts.size(), i = 0; i < n; ++i) {
                final AVTPart part = this.m_parts.elementAt(i);
                part.callVisitors(visitor);
            }
        }
    }
    
    public boolean isSimple() {
        return this.m_simpleString != null;
    }
}
