// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Iterator;
import java.util.List;
import org.jdom.Element;

public class Tokens extends Action
{
    Tokens(final Element root, final Context context) {
        super(root, context);
    }
    
    @Override
    boolean condition() {
        return true;
    }
    
    @Override
    boolean apply() {
        final int offset = Integer.valueOf(this.root().getAttributeValue("offset", "0"));
        final TokenManager tokenManager = this.context().tokenManager();
        try {
            tokenManager.reset(false);
            this.importTranslations(tokenManager);
            this.importKeywords(tokenManager);
            tokenManager.processTokens(offset);
            this.context().tokenManagerChanged();
        }
        catch (Exception e) {
            Tokens.log.error("token error at: " + this.id(), e);
        }
        return true;
    }
    
    void importTranslations(final TokenManager tm) throws Exception {
        final List<Element> translations = (List<Element>)this.root().getChildren("translate", Parser.namespace);
        for (final Element e : translations) {
            final String key = e.getAttributeValue("eq");
            final String value = e.getTextTrim();
            tm.add(new Translation(key, value));
        }
    }
    
    void importKeywords(final TokenManager tm) throws Exception {
        final List<Element> tokens = (List<Element>)this.root().getChildren("keyword", Parser.namespace);
        for (final Element e : tokens) {
            final String key = e.getTextTrim();
            final String skip = e.getAttributeValue("skip", "0");
            final String offset = e.getAttributeValue("offset", "1");
            final String limit = e.getAttributeValue("limit", "1");
            tm.add(new Keyword(key, skip, offset, limit));
        }
    }
}
