// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.compiler;

import java.util.Vector;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.PrefixResolver;

class Lexer
{
    private Compiler m_compiler;
    PrefixResolver m_namespaceContext;
    XPathParser m_processor;
    static final int TARGETEXTRA = 10000;
    private int[] m_patternMap;
    private int m_patternMapSize;
    
    Lexer(final Compiler compiler, final PrefixResolver resolver, final XPathParser xpathProcessor) {
        this.m_patternMap = new int[100];
        this.m_compiler = compiler;
        this.m_namespaceContext = resolver;
        this.m_processor = xpathProcessor;
    }
    
    private final void addToTokenQueue(final String s) {
        this.m_compiler.m_tokenQueue[this.m_compiler.m_tokenQueueSize++] = s;
    }
    
    final int getKeywordToken(final String key) {
        int tok;
        try {
            final Integer itok = Keywords.m_keywords.get(key);
            tok = ((itok != null) ? itok : 0);
        }
        catch (NullPointerException ex) {
            tok = 0;
        }
        catch (ClassCastException ex2) {
            tok = 0;
        }
        return tok;
    }
    
    private int getTokenQueuePosFromMap(final int i) {
        final int pos = this.m_patternMap[i];
        return (pos >= 10000) ? (pos - 10000) : pos;
    }
    
    private int mapNSTokens(final String pat, final int startSubstring, final int posOfNSSep, final int posOfScan) throws TransformerException {
        final String prefix = pat.substring(startSubstring, posOfNSSep);
        String uName;
        if (this.m_namespaceContext != null && !prefix.equals("*") && !prefix.equals("xmlns")) {
            try {
                if (prefix.length() > 0) {
                    uName = this.m_namespaceContext.getNamespaceForPrefix(prefix);
                }
                else {
                    uName = this.m_namespaceContext.getNamespaceForPrefix(prefix);
                }
            }
            catch (ClassCastException ex) {
                uName = this.m_namespaceContext.getNamespaceForPrefix(prefix);
            }
        }
        else {
            uName = prefix;
        }
        if (uName != null && uName.length() > 0) {
            this.addToTokenQueue(uName);
            this.addToTokenQueue(":");
            final String s = pat.substring(posOfNSSep + 1, posOfScan);
            if (s.length() > 0) {
                this.addToTokenQueue(s);
            }
        }
        else {
            this.m_processor.error(52, new String[] { prefix });
        }
        return -1;
    }
    
    private boolean mapPatternElemPos(final int nesting, boolean isStart, final boolean isAttrName) {
        if (nesting == 0) {
            if (!isStart) {
                final int[] patternMap = this.m_patternMap;
                final int n = this.m_patternMapSize - 1;
                patternMap[n] -= 10000;
            }
            this.m_patternMap[this.m_patternMapSize] = this.m_compiler.m_tokenQueueSize - (isAttrName ? 1 : 0) + 10000;
            ++this.m_patternMapSize;
            isStart = false;
        }
        return isStart;
    }
    
    private void recordTokenString(final Vector targetStrings) {
        int tokPos = this.getTokenQueuePosFromMap(this.m_patternMapSize - 1);
        this.resetTokenMark(tokPos + 1);
        if (this.m_processor.lookahead('(', 1)) {
            final int tok = this.getKeywordToken(this.m_processor.m_token);
            switch (tok) {
                case 1030: {
                    targetStrings.addElement("#comment");
                    break;
                }
                case 1031: {
                    targetStrings.addElement("#text");
                    break;
                }
                case 1033: {
                    targetStrings.addElement("*");
                    break;
                }
                case 35: {
                    targetStrings.addElement("/");
                    break;
                }
                case 36: {
                    targetStrings.addElement("*");
                    break;
                }
                case 1032: {
                    targetStrings.addElement("*");
                    break;
                }
                default: {
                    targetStrings.addElement("*");
                    break;
                }
            }
        }
        else {
            if (this.m_processor.tokenIs('@')) {
                ++tokPos;
                this.resetTokenMark(tokPos + 1);
            }
            if (this.m_processor.lookahead(':', 1)) {
                tokPos += 2;
            }
            targetStrings.addElement(this.m_compiler.m_tokenQueue[tokPos]);
        }
    }
    
    private final void resetTokenMark(final int mark) {
        final int qsz = this.m_compiler.m_tokenQueueSize;
        this.m_processor.m_queueMark = ((mark > 0) ? ((mark <= qsz) ? (mark - 1) : mark) : 0);
        if (this.m_processor.m_queueMark < qsz) {
            this.m_processor.m_token = (String)this.m_compiler.m_tokenQueue[this.m_processor.m_queueMark++];
            this.m_processor.m_tokenChar = this.m_processor.m_token.charAt(0);
        }
        else {
            this.m_processor.m_token = null;
            this.m_processor.m_tokenChar = '\0';
        }
    }
    
    void tokenize(final String pat) throws TransformerException {
        this.tokenize(pat, null);
    }
    
    void tokenize(final String pat, final Vector targetStrings) throws TransformerException {
        this.m_compiler.m_tokenQueueSize = 0;
        this.m_compiler.m_currentPattern = pat;
        this.m_patternMapSize = 0;
        this.m_compiler.m_opMap = new int[2500];
        final int nChars = pat.length();
        int startSubstring = -1;
        int posOfNSSep = -1;
        boolean isStartOfPat = true;
        boolean isAttrName = false;
        boolean isNum = false;
        int nesting = 0;
        for (int i = 0; i < nChars; ++i) {
            char c = pat.charAt(i);
            Label_0634: {
                switch (c) {
                    case '\"': {
                        if (startSubstring != -1) {
                            isNum = false;
                            isStartOfPat = this.mapPatternElemPos(nesting, isStartOfPat, isAttrName);
                            isAttrName = false;
                            if (posOfNSSep != -1) {
                                posOfNSSep = this.mapNSTokens(pat, startSubstring, posOfNSSep, i);
                            }
                            else {
                                this.addToTokenQueue(pat.substring(startSubstring, i));
                            }
                        }
                        startSubstring = i;
                        ++i;
                        while (i < nChars && (c = pat.charAt(i)) != '\"') {
                            ++i;
                        }
                        if (c == '\"') {
                            this.addToTokenQueue(pat.substring(startSubstring, i + 1));
                            startSubstring = -1;
                            continue;
                        }
                        this.m_processor.error(26, null);
                        continue;
                    }
                    case '\'': {
                        if (startSubstring != -1) {
                            isNum = false;
                            isStartOfPat = this.mapPatternElemPos(nesting, isStartOfPat, isAttrName);
                            isAttrName = false;
                            if (posOfNSSep != -1) {
                                posOfNSSep = this.mapNSTokens(pat, startSubstring, posOfNSSep, i);
                            }
                            else {
                                this.addToTokenQueue(pat.substring(startSubstring, i));
                            }
                        }
                        startSubstring = i;
                        ++i;
                        while (i < nChars && (c = pat.charAt(i)) != '\'') {
                            ++i;
                        }
                        if (c == '\'') {
                            this.addToTokenQueue(pat.substring(startSubstring, i + 1));
                            startSubstring = -1;
                            continue;
                        }
                        this.m_processor.error(27, null);
                        continue;
                    }
                    case '\t':
                    case '\n':
                    case '\r':
                    case ' ': {
                        if (startSubstring != -1) {
                            isNum = false;
                            isStartOfPat = this.mapPatternElemPos(nesting, isStartOfPat, isAttrName);
                            isAttrName = false;
                            if (posOfNSSep != -1) {
                                posOfNSSep = this.mapNSTokens(pat, startSubstring, posOfNSSep, i);
                            }
                            else {
                                this.addToTokenQueue(pat.substring(startSubstring, i));
                            }
                            startSubstring = -1;
                        }
                        continue;
                    }
                    case '@': {
                        isAttrName = true;
                    }
                    case '-': {
                        if (c != '-') {
                            break Label_0634;
                        }
                        if (isNum || startSubstring == -1) {
                            isNum = false;
                            break Label_0634;
                        }
                        continue;
                    }
                    case '!':
                    case '$':
                    case '(':
                    case ')':
                    case '*':
                    case '+':
                    case ',':
                    case '/':
                    case '<':
                    case '=':
                    case '>':
                    case '[':
                    case '\\':
                    case ']':
                    case '^':
                    case '|': {
                        if (startSubstring != -1) {
                            isNum = false;
                            isStartOfPat = this.mapPatternElemPos(nesting, isStartOfPat, isAttrName);
                            isAttrName = false;
                            if (posOfNSSep != -1) {
                                posOfNSSep = this.mapNSTokens(pat, startSubstring, posOfNSSep, i);
                            }
                            else {
                                this.addToTokenQueue(pat.substring(startSubstring, i));
                            }
                            startSubstring = -1;
                        }
                        else if (c == '/' && isStartOfPat) {
                            isStartOfPat = this.mapPatternElemPos(nesting, isStartOfPat, isAttrName);
                        }
                        else if (c == '*') {
                            isStartOfPat = this.mapPatternElemPos(nesting, isStartOfPat, isAttrName);
                            isAttrName = false;
                        }
                        if (nesting == 0 && c == '|') {
                            if (targetStrings != null) {
                                this.recordTokenString(targetStrings);
                            }
                            isStartOfPat = true;
                        }
                        if (c == ')' || c == ']') {
                            --nesting;
                        }
                        else if (c == '(' || c == '[') {
                            ++nesting;
                        }
                        this.addToTokenQueue(pat.substring(i, i + 1));
                        continue;
                    }
                    case ':': {
                        if (posOfNSSep == i - 1) {
                            if (startSubstring != -1 && startSubstring < i - 1) {
                                this.addToTokenQueue(pat.substring(startSubstring, i - 1));
                            }
                            isNum = false;
                            isAttrName = false;
                            startSubstring = -1;
                            posOfNSSep = -1;
                            this.addToTokenQueue(pat.substring(i - 1, i + 1));
                            continue;
                        }
                        posOfNSSep = i;
                        break;
                    }
                }
            }
            if (startSubstring == -1) {
                startSubstring = i;
                isNum = Character.isDigit(c);
            }
            else if (isNum) {
                isNum = Character.isDigit(c);
            }
        }
        if (startSubstring != -1) {
            isNum = false;
            isStartOfPat = this.mapPatternElemPos(nesting, isStartOfPat, isAttrName);
            if (posOfNSSep != -1) {
                posOfNSSep = this.mapNSTokens(pat, startSubstring, posOfNSSep, nChars);
            }
            else {
                this.addToTokenQueue(pat.substring(startSubstring, nChars));
            }
        }
        if (this.m_compiler.m_tokenQueueSize == 0) {
            this.m_processor.error(28, null);
        }
        else if (targetStrings != null) {
            this.recordTokenString(targetStrings);
        }
        this.m_processor.m_queueMark = 0;
    }
}
