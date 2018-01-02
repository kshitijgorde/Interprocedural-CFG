// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.Attributes;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import org.apache.xml.utils.SystemIDResolver;
import java.util.StringTokenizer;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.InputSource;
import javax.xml.transform.Source;
import java.util.Vector;
import org.xml.sax.helpers.DefaultHandler;

public class StylesheetPIHandler extends DefaultHandler
{
    String m_baseID;
    String m_media;
    String m_title;
    String m_charset;
    Vector m_stylesheets;
    
    public StylesheetPIHandler(final String baseID, final String media, final String title, final String charset) {
        this.m_stylesheets = new Vector();
        this.m_baseID = baseID;
        this.m_media = media;
        this.m_title = title;
        this.m_charset = charset;
    }
    
    public Source getAssociatedStylesheet() {
        final int sz = this.m_stylesheets.size();
        if (sz > 0) {
            final SAXSource ssource = new SAXSource(this.m_stylesheets.elementAt(sz - 1));
            return ssource;
        }
        return null;
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        if (target.equals("xml-stylesheet")) {
            String href = null;
            String type = null;
            String title = null;
            String media = null;
            String charset = null;
            boolean alternate = false;
            final StringTokenizer tokenizer = new StringTokenizer(data, " \t=", true);
            boolean lookedAhead = false;
            String token = "";
            while (tokenizer.hasMoreTokens()) {
                if (!lookedAhead) {
                    token = tokenizer.nextToken();
                }
                else {
                    lookedAhead = false;
                }
                if (tokenizer.hasMoreTokens()) {
                    if (token.equals(" ") || token.equals("\t")) {
                        continue;
                    }
                    if (token.equals("=")) {
                        continue;
                    }
                }
                final String name = token;
                if (name.equals("type")) {
                    for (token = tokenizer.nextToken(); tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("=")); token = tokenizer.nextToken()) {}
                    type = token.substring(1, token.length() - 1);
                }
                else {
                    if (name.equals("href")) {
                        for (token = tokenizer.nextToken(); tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("=")); token = tokenizer.nextToken()) {}
                        href = token;
                        if (tokenizer.hasMoreTokens()) {
                            for (token = tokenizer.nextToken(); token.equals("=") && tokenizer.hasMoreTokens(); token = tokenizer.nextToken(), lookedAhead = true) {
                                href = String.valueOf(href) + token + tokenizer.nextToken();
                                if (!tokenizer.hasMoreTokens()) {
                                    break;
                                }
                            }
                        }
                        href = href.substring(1, href.length() - 1);
                        try {
                            href = SystemIDResolver.getAbsoluteURI(href, this.m_baseID);
                            continue;
                        }
                        catch (TransformerException te) {
                            throw new SAXException(te);
                        }
                    }
                    if (name.equals("title")) {
                        for (token = tokenizer.nextToken(); tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("=")); token = tokenizer.nextToken()) {}
                        title = token.substring(1, token.length() - 1);
                    }
                    else if (name.equals("media")) {
                        for (token = tokenizer.nextToken(); tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("=")); token = tokenizer.nextToken()) {}
                        media = token.substring(1, token.length() - 1);
                    }
                    else if (name.equals("charset")) {
                        for (token = tokenizer.nextToken(); tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("=")); token = tokenizer.nextToken()) {}
                        charset = token.substring(1, token.length() - 1);
                    }
                    else {
                        if (!name.equals("alternate")) {
                            continue;
                        }
                        for (token = tokenizer.nextToken(); tokenizer.hasMoreTokens() && (token.equals(" ") || token.equals("\t") || token.equals("=")); token = tokenizer.nextToken()) {}
                        alternate = token.substring(1, token.length() - 1).equals("yes");
                    }
                }
            }
            if (type != null && (type.equals("text/xsl") || type.equals("text/xml") || type.equals("application/xml+xslt")) && href != null) {
                if (this.m_media != null) {
                    if (media == null) {
                        return;
                    }
                    if (!media.equals(this.m_media)) {
                        return;
                    }
                }
                if (this.m_charset != null) {
                    if (charset == null) {
                        return;
                    }
                    if (!charset.equals(this.m_charset)) {
                        return;
                    }
                }
                if (this.m_title != null) {
                    if (title == null) {
                        return;
                    }
                    if (!title.equals(this.m_title)) {
                        return;
                    }
                }
                this.m_stylesheets.addElement(new InputSource(href));
            }
        }
    }
    
    public void startElement(final String namespaceURI, final String localName, final String qName, final Attributes atts) throws SAXException {
        throw new StopParseException();
    }
}
