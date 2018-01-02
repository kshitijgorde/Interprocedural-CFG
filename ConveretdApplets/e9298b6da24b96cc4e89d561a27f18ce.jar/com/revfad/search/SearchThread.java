// 
// Decompiled by Procyon v0.5.30
// 

package com.revfad.search;

import java.io.IOException;
import java.util.Hashtable;

public final class SearchThread extends Thread implements DocumentScanner
{
    private boolean inTag;
    private CircularStringBuffer contextBuffer;
    private StringBuffer anchorBuffer;
    private SearchSieve anchorFinder;
    private DocumentQueue queue;
    private SearchThreadListener listener;
    private boolean exactMatchesOnly;
    private int leadingContextLength;
    private int trailingContextLength;
    private boolean ignoreTagContent;
    private boolean mustMatchAll;
    private boolean allowMultipleMatches;
    private SearchSieve[] patterns;
    private int numberOfPatterns;
    private String nearestAnchor;
    private boolean searchingDocument;
    private volatile boolean continueSearching;
    
    public SearchThread(final SearchSieve[] array, final DocumentQueue queue, final SearchThreadListener listener, final Hashtable hashtable) {
        this.anchorFinder = new SearchSieve("<a name=\"");
        this.queue = queue;
        this.listener = listener;
        this.exactMatchesOnly = Option.EXACT_MATCHES_ONLY.getFromWithDefault(hashtable, false);
        this.leadingContextLength = Option.LEADING_CONTEXT_LENGTH.getFromWithDefault(hashtable, 15);
        this.trailingContextLength = Option.TRAILING_CONTEXT_LENGTH.getFromWithDefault(hashtable, 0);
        this.ignoreTagContent = Option.IGNORE_TAG_CONTENT.getFromWithDefault(hashtable, false);
        this.mustMatchAll = Option.MUST_MATCH_ALL.getFromWithDefault(hashtable, false);
        this.allowMultipleMatches = Option.ALLOW_MULTIPLE_MATCHES.getFromWithDefault(hashtable, false);
        this.numberOfPatterns = array.length;
        this.patterns = new SearchSieve[this.numberOfPatterns];
        for (int i = 0; i < array.length; ++i) {
            this.patterns[i] = new SearchSieve(array[i]);
        }
        if (this.exactMatchesOnly) {
            this.contextBuffer = new CircularStringBuffer(this.leadingContextLength + 1);
        }
        else {
            this.contextBuffer = new CircularStringBuffer(this.leadingContextLength);
        }
        this.continueSearching = true;
    }
    
    public void run() {
        Document next;
        while (this.continueSearching && (next = this.queue.next()) != null) {
            this.listener.searching(this, next);
            try {
                this.inTag = false;
                this.contextBuffer.reset();
                this.anchorFinder.reset();
                this.nearestAnchor = null;
                this.anchorBuffer = null;
                this.searchingDocument = true;
                for (int i = 0; i < this.numberOfPatterns; ++i) {
                    this.patterns[i].reset();
                }
                next.beginReadingFor(this);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            if (this.continueSearching) {
                this.listener.finishedSearching(this, next);
            }
            for (int j = 0; j < this.numberOfPatterns; ++j) {
                this.patterns[j].reset();
            }
        }
    }
    
    public char[] buffer(final Document document) {
        return new char[Math.min(Math.max(4096, document.contentLength()), 100000)];
    }
    
    public boolean shouldContinue(final Document document) {
        return this.continueSearching && this.searchingDocument;
    }
    
    public void scan(final char[] array, final int n, final Document document) {
        for (int n2 = 0; n2 < n && this.searchingDocument; ++n2) {
            final char c = array[n2];
            final char lowerCase = Character.toLowerCase(c);
            if (this.anchorBuffer == null) {
                if (this.anchorFinder.addChar(lowerCase)) {
                    this.anchorBuffer = new StringBuffer();
                }
            }
            else if (lowerCase == '\"') {
                if (this.anchorBuffer.length() != 0) {
                    this.nearestAnchor = this.anchorBuffer.toString();
                    this.anchorBuffer = null;
                }
            }
            else {
                this.anchorBuffer.append(c);
            }
            if (lowerCase == '<') {
                this.inTag = true;
            }
            if (!this.ignoreTagContent || !this.inTag) {
                this.contextBuffer.append(c);
                boolean b = true;
                boolean b2 = false;
                SearchSieve searchSieve = null;
                for (int i = 0; i < this.numberOfPatterns; ++i) {
                    final SearchSieve searchSieve2 = this.patterns[i];
                    boolean b3 = searchSieve2.addChar(lowerCase);
                    if (b3) {
                        searchSieve = searchSieve2;
                    }
                    else {
                        b3 = searchSieve2.hasMatched();
                    }
                    b = (b && b3);
                    b2 = (b2 || b3);
                }
                if ((b2 && !this.mustMatchAll) || (b && this.mustMatchAll)) {
                    String string = "";
                    if (n2 != n - 1) {
                        final StringBuffer sb = new StringBuffer();
                        int n3 = 0;
                        for (int n4 = n2 + 1; n4 < n && sb.length() < this.trailingContextLength; ++n4) {
                            final char c2 = array[n4];
                            if (c2 == '<') {
                                n3 = 1;
                            }
                            if (!this.ignoreTagContent || n3 == 0) {
                                sb.append(c2);
                            }
                            if (c2 == '>') {
                                n3 = 0;
                            }
                        }
                        string = sb.toString();
                    }
                    this.listener.foundMatch(this, new Match(searchSieve.getPattern(), document, this.contextBuffer.toString() + string, this.nearestAnchor));
                    if (this.allowMultipleMatches) {
                        for (int j = 0; j < this.numberOfPatterns; ++j) {
                            this.patterns[j].reset();
                        }
                    }
                    else {
                        this.searchingDocument = false;
                    }
                }
            }
            if (lowerCase == '>') {
                this.inTag = false;
            }
        }
    }
    
    public void stopSearching() {
        this.continueSearching = false;
    }
    
    public static final class Option
    {
        public static final Option EXACT_MATCHES_ONLY;
        public static final Option LEADING_CONTEXT_LENGTH;
        public static final Option TRAILING_CONTEXT_LENGTH;
        public static final Option IGNORE_TAG_CONTENT;
        public static final Option MUST_MATCH_ALL;
        public static final Option ALLOW_MULTIPLE_MATCHES;
        private String name;
        
        private Option(final String name) {
            this.name = name;
        }
        
        public boolean getFromWithDefault(final Hashtable hashtable, final boolean b) {
            final Boolean value = hashtable.get(this);
            if (value == null) {
                return b;
            }
            return Boolean.TRUE == value;
        }
        
        public int getFromWithDefault(final Hashtable hashtable, final int n) {
            final Integer value = hashtable.get(this);
            if (value == null) {
                return n;
            }
            return value;
        }
        
        public String toString() {
            return this.name;
        }
        
        static {
            EXACT_MATCHES_ONLY = new Option("EXACT_MATCHES_ONLY");
            LEADING_CONTEXT_LENGTH = new Option("LEADING_CONTEXT_LENGTH");
            TRAILING_CONTEXT_LENGTH = new Option("TRAILING_CONTEXT_LENGTH");
            IGNORE_TAG_CONTENT = new Option("IGNORE_TAG_CONTENT");
            MUST_MATCH_ALL = new Option("MUST_MATCH_ALL");
            ALLOW_MULTIPLE_MATCHES = new Option("ALLOW_MULTIPLE_MATCHES");
        }
    }
}
