// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

final class AlignJava extends TextProcessor
{
    private static final boolean DEBUGGING = false;
    private static final int MAX_COLS = 1024;
    private static final int padding = 2;
    private AlignJavaCommentState commentState;
    private StringBuilder cooked;
    private String raw;
    private final int[] biggestWidth;
    private int colIndex;
    private int cols;
    private int pass;
    private int width;
    
    AlignJava() {
        this.biggestWidth = new int[1024];
        this.pass = 1;
    }
    
    public String process(final String raw) {
        if (raw == null) {
            return null;
        }
        this.raw = raw;
        this.cooked = new StringBuilder(raw.length() * 2);
        for (int i = 0; i < 40; ++i) {
            this.biggestWidth[i] = 0;
        }
        this.cols = 0;
        this.pass = 1;
        this.doAPass();
        for (int i = 0; i < this.cols; ++i) {
            final int[] biggestWidth = this.biggestWidth;
            final int n = i;
            biggestWidth[n] += 2;
        }
        this.pass = 2;
        this.doAPass();
        return this.cooked.toString();
    }
    
    private AlignJavaCategory categorise(final char c) {
        switch (c) {
            case '\n': {
                switch (this.commentState) {
                    default: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.NEWLINE;
                    }
                    case INSIDE_QUOTES: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.NEWLINE;
                    }
                    case INSIDE_SINGLE_QUOTES: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.NEWLINE;
                    }
                    case SEEN_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.NEWLINE;
                    }
                    case SEEN_SINGLE_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.NEWLINE;
                    }
                    case INSIDE_STAR_COMMENT: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case SEEN_SLASH: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.NEWLINE;
                    }
                    case SEEN_SLASH_STAR_STAR: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case INSIDE_SLASH_SLASH: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.NEWLINE;
                    }
                }
                break;
            }
            case '\0':
            case '\u0001':
            case '\u0002':
            case '\u0003':
            case '\u0004':
            case '\u0005':
            case '\u0006':
            case '\u0007':
            case '\b':
            case '\t':
            case '\u000b':
            case '\f':
            case '\r':
            case '\u000e':
            case '\u000f':
            case '\u0010':
            case '\u0011':
            case '\u0012':
            case '\u0013':
            case '\u0014':
            case '\u0015':
            case '\u0016':
            case '\u0017':
            case '\u0018':
            case '\u0019':
            case '\u001a':
            case '\u001b':
            case '\u001c':
            case '\u001d':
            case '\u001e':
            case '\u001f':
            case ' ': {
                switch (this.commentState) {
                    default: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.WHITESPACE;
                    }
                    case INSIDE_QUOTES: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_SINGLE_QUOTES: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_SINGLE_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_STAR_COMMENT: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case SEEN_SLASH: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.WHITESPACE;
                    }
                    case SEEN_SLASH_STAR_STAR: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case INSIDE_SLASH_SLASH: {
                        this.commentState = AlignJavaCommentState.INSIDE_SLASH_SLASH;
                        return AlignJavaCategory.COMMENT;
                    }
                }
                break;
            }
            case '\"': {
                switch (this.commentState) {
                    default: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_QUOTES: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_SINGLE_QUOTES: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_SINGLE_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_STAR_COMMENT: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case SEEN_SLASH: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_SLASH_STAR_STAR: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case INSIDE_SLASH_SLASH: {
                        this.commentState = AlignJavaCommentState.INSIDE_SLASH_SLASH;
                        return AlignJavaCategory.COMMENT;
                    }
                }
                break;
            }
            case '\'': {
                switch (this.commentState) {
                    default: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_QUOTES: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_SINGLE_QUOTES: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_SINGLE_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_STAR_COMMENT: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case SEEN_SLASH: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_SLASH_STAR_STAR: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case INSIDE_SLASH_SLASH: {
                        this.commentState = AlignJavaCommentState.INSIDE_SLASH_SLASH;
                        return AlignJavaCategory.COMMENT;
                    }
                }
                break;
            }
            case '*': {
                switch (this.commentState) {
                    default: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.ORDINARY;
                    }
                    case INSIDE_QUOTES: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_SINGLE_QUOTES: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_SINGLE_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_STAR_COMMENT: {
                        this.commentState = AlignJavaCommentState.SEEN_SLASH_STAR_STAR;
                        return AlignJavaCategory.COMMENT;
                    }
                    case SEEN_SLASH: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case SEEN_SLASH_STAR_STAR: {
                        this.commentState = AlignJavaCommentState.SEEN_SLASH_STAR_STAR;
                        return AlignJavaCategory.COMMENT;
                    }
                    case INSIDE_SLASH_SLASH: {
                        this.commentState = AlignJavaCommentState.INSIDE_SLASH_SLASH;
                        return AlignJavaCategory.COMMENT;
                    }
                }
                break;
            }
            case '\\': {
                switch (this.commentState) {
                    default: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.ORDINARY;
                    }
                    case INSIDE_QUOTES: {
                        this.commentState = AlignJavaCommentState.SEEN_QUOTE_BACK;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_SINGLE_QUOTES: {
                        this.commentState = AlignJavaCommentState.SEEN_SINGLE_QUOTE_BACK;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_SINGLE_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_STAR_COMMENT: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case SEEN_SLASH: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.ORDINARY;
                    }
                    case SEEN_SLASH_STAR_STAR: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case INSIDE_SLASH_SLASH: {
                        this.commentState = AlignJavaCommentState.INSIDE_SLASH_SLASH;
                        return AlignJavaCategory.COMMENT;
                    }
                }
                break;
            }
            case '/': {
                switch (this.commentState) {
                    default: {
                        this.commentState = AlignJavaCommentState.SEEN_SLASH;
                        return AlignJavaCategory.ORDINARY;
                    }
                    case INSIDE_QUOTES: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_SINGLE_QUOTES: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_SINGLE_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_STAR_COMMENT: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case SEEN_SLASH: {
                        this.commentState = AlignJavaCommentState.INSIDE_SLASH_SLASH;
                        return AlignJavaCategory.COMMENT;
                    }
                    case SEEN_SLASH_STAR_STAR: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.COMMENT;
                    }
                    case INSIDE_SLASH_SLASH: {
                        this.commentState = AlignJavaCommentState.INSIDE_SLASH_SLASH;
                        return AlignJavaCategory.COMMENT;
                    }
                }
                break;
            }
            case ',': {
                switch (this.commentState) {
                    default: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.COMMA;
                    }
                    case INSIDE_QUOTES: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_SINGLE_QUOTES: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_SINGLE_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_STAR_COMMENT: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case SEEN_SLASH: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.COMMA;
                    }
                    case SEEN_SLASH_STAR_STAR: {
                        this.commentState = AlignJavaCommentState.SEEN_SLASH_STAR_STAR;
                        return AlignJavaCategory.COMMENT;
                    }
                    case INSIDE_SLASH_SLASH: {
                        this.commentState = AlignJavaCommentState.INSIDE_SLASH_SLASH;
                        return AlignJavaCategory.COMMENT;
                    }
                }
                break;
            }
            default: {
                switch (this.commentState) {
                    default: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.ORDINARY;
                    }
                    case INSIDE_QUOTES: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_SINGLE_QUOTES: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case SEEN_SINGLE_QUOTE_BACK: {
                        this.commentState = AlignJavaCommentState.INSIDE_SINGLE_QUOTES;
                        return AlignJavaCategory.QUOTED;
                    }
                    case INSIDE_STAR_COMMENT: {
                        this.commentState = AlignJavaCommentState.INSIDE_STAR_COMMENT;
                        return AlignJavaCategory.COMMENT;
                    }
                    case SEEN_SLASH: {
                        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
                        return AlignJavaCategory.ORDINARY;
                    }
                    case SEEN_SLASH_STAR_STAR: {
                        this.commentState = AlignJavaCommentState.SEEN_SLASH_STAR_STAR;
                        return AlignJavaCategory.COMMENT;
                    }
                    case INSIDE_SLASH_SLASH: {
                        this.commentState = AlignJavaCommentState.INSIDE_SLASH_SLASH;
                        return AlignJavaCategory.COMMENT;
                    }
                }
                break;
            }
        }
    }
    
    private void doAPass() {
        AlignBlankState blankState = AlignBlankState.IN_LEADING;
        this.commentState = AlignJavaCommentState.OUTSIDE_QUOTES;
        this.startLine();
        for (int rawLen = this.raw.length(), i = 0; i < rawLen; ++i) {
            final char c = this.raw.charAt(i);
            switch (this.categorise(c)) {
                case WHITESPACE: {
                    switch (blankState) {
                        case IN_LEADING: {
                            blankState = AlignBlankState.IN_LEADING;
                            break;
                        }
                        case IN_MIDDLE: {
                            blankState = AlignBlankState.IN_TRAILING;
                            break;
                        }
                        case IN_TRAILING: {
                            blankState = AlignBlankState.IN_TRAILING;
                            break;
                        }
                    }
                    break;
                }
                case COMMA: {
                    switch (blankState) {
                        case IN_LEADING: {
                            this.startField();
                            this.inField(c);
                            this.endField();
                            blankState = AlignBlankState.IN_LEADING;
                            break;
                        }
                        case IN_MIDDLE: {
                            this.inField(c);
                            this.endField();
                            blankState = AlignBlankState.IN_LEADING;
                            break;
                        }
                        case IN_TRAILING: {
                            this.inField(c);
                            this.endField();
                            blankState = AlignBlankState.IN_LEADING;
                            break;
                        }
                    }
                    break;
                }
                case ORDINARY: {
                    switch (blankState) {
                        case IN_LEADING: {
                            this.startField();
                            this.inField(c);
                            blankState = AlignBlankState.IN_MIDDLE;
                            break;
                        }
                        case IN_MIDDLE: {
                            this.inField(c);
                            blankState = AlignBlankState.IN_MIDDLE;
                            break;
                        }
                        case IN_TRAILING: {
                            this.endField();
                            this.startField();
                            this.inField(c);
                            blankState = AlignBlankState.IN_MIDDLE;
                            break;
                        }
                    }
                    break;
                }
                case COMMENT:
                case QUOTED: {
                    switch (blankState) {
                        case IN_LEADING: {
                            this.startField();
                            this.inField(c);
                            blankState = AlignBlankState.IN_MIDDLE;
                            break;
                        }
                        case IN_MIDDLE: {
                            this.inField(c);
                            blankState = AlignBlankState.IN_MIDDLE;
                            break;
                        }
                        case IN_TRAILING: {
                            this.endField();
                            this.startField();
                            this.inField(c);
                            blankState = AlignBlankState.IN_MIDDLE;
                            break;
                        }
                    }
                    break;
                }
                case NEWLINE: {
                    switch (blankState) {
                        case IN_MIDDLE: {
                            this.endField();
                            break;
                        }
                        case IN_TRAILING: {
                            this.endField();
                            break;
                        }
                    }
                    this.endLine();
                    this.startLine();
                    blankState = AlignBlankState.IN_LEADING;
                    break;
                }
            }
        }
    }
    
    private void endField() {
        switch (this.pass) {
            case 2: {
                if (this.colIndex < this.cols - 1) {
                    this.width = this.biggestWidth[this.colIndex] - this.width;
                    while (this.width > 0) {
                        this.cooked.append(' ');
                        --this.width;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    private void endLine() {
        switch (this.pass) {
            case 2: {
                this.cooked.append('\n');
                break;
            }
        }
    }
    
    private void inField(final char c) {
        switch (this.pass) {
            case 1: {
                if (++this.width > this.biggestWidth[this.colIndex]) {
                    this.biggestWidth[this.colIndex] = this.width;
                    break;
                }
                break;
            }
            case 2: {
                ++this.width;
                this.cooked.append(c);
                break;
            }
        }
    }
    
    private void startField() {
        switch (this.pass) {
            case 1: {
                this.width = 0;
                if (++this.colIndex > this.cols - 1) {
                    this.cols = this.colIndex + 1;
                    break;
                }
                break;
            }
            case 2: {
                this.width = 0;
                ++this.colIndex;
                break;
            }
        }
    }
    
    private void startLine() {
        this.colIndex = -1;
        this.width = 0;
    }
    
    public static void main(final String[] args) {
    }
}
