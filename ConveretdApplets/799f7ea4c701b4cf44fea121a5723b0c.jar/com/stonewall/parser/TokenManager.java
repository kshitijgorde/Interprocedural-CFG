// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.ArrayList;
import org.xmodel.log.Log;
import java.util.List;
import java.util.regex.Pattern;

public class TokenManager
{
    private static Pattern tokenPattern;
    private Context context;
    private List<String> tokens;
    private Dictionary<Integer> skipped;
    private Dictionary<String> references;
    private Dictionary<Keyword> keywords;
    private Dictionary<Translation> translations;
    static Log log;
    
    static {
        TokenManager.tokenPattern = Pattern.compile("(\"[^\"]+\")+|[\\S]+");
        TokenManager.log = Log.getLog(TokenManager.class);
    }
    
    TokenManager(final Context context) {
        this.tokens = new ArrayList<String>();
        this.skipped = new Dictionary<Integer>();
        this.references = new Dictionary<String>();
        this.keywords = new Dictionary<Keyword>();
        this.translations = new Dictionary<Translation>();
        this.context = context;
    }
    
    void reset() {
        this.reset(true);
    }
    
    void reset(final boolean clearTokens) {
        if (clearTokens) {
            this.tokens.clear();
        }
        this.skipped.clear();
        this.references.clear();
        this.keywords.clear();
        this.translations.clear();
    }
    
    void add(final Keyword keyword) {
        this.keywords.put(keyword.name, keyword);
    }
    
    void add(final Translation translation) {
        this.translations.put(translation.key, translation);
    }
    
    List<String> tokens() {
        return this.tokens;
    }
    
    Dictionary<String> references() {
        return this.references;
    }
    
    Dictionary<Keyword> keywords() {
        return this.keywords;
    }
    
    Dictionary<Translation> translations() {
        return this.translations;
    }
    
    List<String> parseTokens(final String line) {
        this.reset();
        final Matcher m = TokenManager.tokenPattern.matcher(line);
        while (m.find()) {
            final String t = this.stripQuotes(m.group());
            this.tokens.add(t);
        }
        this.processTokens(0);
        return this.tokens;
    }
    
    void processTokens(final int offset) {
        this.skipped.clear();
        this.references.clear();
        final List<String> result = new ArrayList<String>();
        for (int i = 0; i < this.tokens.size(); ++i) {
            if (i >= offset) {
                String key = this.tokens.get(i);
                key = this.translate(key, new HashSet<String>());
                final Keyword keyword = this.keywords.get(key);
                if (keyword == null) {
                    result.add(key);
                }
                else if (keyword.skip > this.getSkipped(key)) {
                    result.add(key);
                    this.skipped.put(key, this.getSkipped(key) + 1);
                }
                else if (keyword.offset >= 0) {
                    i += keyword.offset;
                    if (keyword.limit > 1) {
                        this.mapKeyword(key, this.getTuple(i, keyword.limit));
                        i += keyword.limit;
                    }
                    else {
                        this.mapKeyword(key, this.tokenAt(key, i));
                    }
                }
            }
        }
        this.tokens = result;
    }
    
    private void mapKeyword(final String key, String value) {
        final String ref = this.references.get(key);
        if (ref != null) {
            final List<String> list = new ArrayList<String>();
            String[] split;
            for (int length = (split = Tuple.split(ref)).length, i = 0; i < length; ++i) {
                final String s = split[i];
                list.add(s);
            }
            String[] split2;
            for (int length2 = (split2 = Tuple.split(value)).length, j = 0; j < length2; ++j) {
                final String s = split2[j];
                list.add(s);
            }
            value = Tuple.join(list);
        }
        this.references.put(key, value);
    }
    
    private String getTuple(final int i, final int limit) {
        final List<String> list = new ArrayList<String>();
        for (int n = 0; n < limit; ++n) {
            final int index = i + n;
            if (index >= this.tokens.size()) {
                break;
            }
            list.add(this.tokens.get(index));
        }
        return Tuple.join(list);
    }
    
    private int getSkipped(final String key) {
        final Integer n = this.skipped.get(key);
        return (n != null) ? n : 0;
    }
    
    private String tokenAt(final String key, final int index) {
        String result = null;
        if (index < 0 || index >= this.tokens.size()) {
            TokenManager.log.error("at:\n" + this.id() + "\n\tkeyword (" + key + ") at index (" + index + ") is {out-of-bounds} for tokens " + this.tokens);
        }
        else {
            result = this.tokens.get(index);
        }
        return result;
    }
    
    private String translate(final String s, final Set<String> history) {
        String result = s;
        if (history.contains(s)) {
            TokenManager.log.error("at:\n" + this.id() + ":mapping loop detected on: " + s + " history=" + history);
            return s;
        }
        final Translation t = this.translations.get(s);
        if (t != null) {
            history.add(s);
            result = this.translate(t.value, history);
        }
        return result;
    }
    
    private String stripQuotes(String s) {
        if (s.length() > 1 && s.charAt(0) == '\"') {
            s = s.substring(1, s.length() - 1);
        }
        else {
            s = this.setCase(s);
        }
        return s;
    }
    
    private String setCase(final String s) {
        String result = s;
        final String property = System.getProperty("org.parser.token.case", TokenCase.LOWER.name());
        switch (TokenCase.valueOf(property.toUpperCase())) {
            case LOWER: {
                result = s.toLowerCase();
                break;
            }
            case UPPER: {
                result = s.toUpperCase();
                break;
            }
        }
        return result;
    }
    
    private String id() {
        return this.context.id();
    }
    
    enum TokenCase
    {
        NATIVE("NATIVE", 0), 
        UPPER("UPPER", 1), 
        LOWER("LOWER", 2);
        
        private TokenCase(final String s, final int n) {
        }
    }
}
