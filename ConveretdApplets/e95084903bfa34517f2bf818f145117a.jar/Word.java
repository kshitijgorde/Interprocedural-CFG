// 
// Decompiled by Procyon v0.5.30
// 

public class Word extends Collection implements Cloneable
{
    private static String CANT_DO;
    private static String ON_AN_EMPTY_WORD;
    private String value;
    
    public Word() {
        this.value = "";
    }
    
    public Word(final String value) {
        if (value == null) {
            this.value = "";
        }
        else {
            this.value = value;
        }
    }
    
    public Word(final Word word, final Word word2) {
        this.value = word.toString() + word2.toString();
    }
    
    public Object butFirst() throws TTRuntimeError {
        switch (this.value.length()) {
            case 0: {
                throw new TTRuntimeError(Word.CANT_DO + "butfirst" + Word.ON_AN_EMPTY_WORD);
            }
            case 1: {
                return new Word();
            }
            default: {
                return new Word(this.value.substring(1));
            }
        }
    }
    
    public Object butLast() throws TTRuntimeError {
        switch (this.value.length()) {
            case 0: {
                throw new TTRuntimeError(Word.CANT_DO + "butlast" + Word.ON_AN_EMPTY_WORD);
            }
            case 1: {
                return new Word();
            }
            default: {
                return new Word(this.value.substring(0, this.value.length() - 1));
            }
        }
    }
    
    public Object clone() {
        return new Word(this.value);
    }
    
    public Integer count() {
        return new Integer(this.value.length());
    }
    
    public Boolean emptyp() {
        if (this.value.length() == 0) {
            return new Boolean(true);
        }
        return new Boolean(false);
    }
    
    public boolean equals(final Object o) {
        if (o instanceof Boolean) {
            return this.value.equals(((Boolean)o).toString());
        }
        if (o instanceof Number) {
            return this.value.equals(((Number)o).toString());
        }
        if (o instanceof String) {
            return ((String)o).equals(this.value);
        }
        return o instanceof Word && ((Word)o).equals(this.value);
    }
    
    public Object first() throws TTRuntimeError {
        switch (this.value.length()) {
            case 0: {
                throw new TTRuntimeError(Word.CANT_DO + "first" + Word.ON_AN_EMPTY_WORD);
            }
            case 1: {
                return new Word(this.value);
            }
            default: {
                return new Word(this.value.substring(0, 1));
            }
        }
    }
    
    public Boolean isArray() {
        return new Boolean(false);
    }
    
    public Boolean isList() {
        return new Boolean(false);
    }
    
    public Boolean isWord() {
        return new Boolean(true);
    }
    
    public Object item(int n) {
        if (--n < 0) {
            return null;
        }
        if (n >= this.value.length()) {
            return null;
        }
        return new Word(this.value.substring(n, n + 1));
    }
    
    public Object last() throws TTRuntimeError {
        switch (this.value.length()) {
            case 0: {
                throw new TTRuntimeError(Word.CANT_DO + "last" + Word.ON_AN_EMPTY_WORD);
            }
            case 1: {
                return new Word(this.value);
            }
            default: {
                final int length = this.value.length();
                return new Word(this.value.substring(length - 1, length));
            }
        }
    }
    
    public Boolean memberp(final Object o) {
        if (!(o instanceof Word)) {
            return new Boolean(false);
        }
        final String string = ((Word)o).toString();
        if (string.length() != 1) {
            return new Boolean(false);
        }
        if (this.value.indexOf(string) < 0) {
            return new Boolean(false);
        }
        return new Boolean(true);
    }
    
    public String toString() {
        return this.value;
    }
    
    static {
        Word.CANT_DO = "Can't do ";
        Word.ON_AN_EMPTY_WORD = " on an empty word";
    }
}
