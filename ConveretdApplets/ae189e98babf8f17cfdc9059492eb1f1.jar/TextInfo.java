// 
// Decompiled by Procyon v0.5.30
// 

public class TextInfo
{
    StringBuffer sbText;
    int curPos;
    private char curChar;
    private String wordResult;
    private boolean ignoreUpper;
    private boolean wordComplete;
    private boolean wordsToCheck;
    private boolean done;
    private boolean hasLetters;
    private boolean hasLower;
    private boolean hasDigits;
    private WordBundle result;
    private int startPos;
    private int textLength;
    
    public TextInfo() {
    }
    
    public TextInfo(final String s) {
        this.sbText = new StringBuffer(s);
        this.curPos = 0;
        this.textLength = s.length();
        this.wordsToCheck = (this.textLength > 0);
    }
    
    public final void setIgnore(final boolean ignoreUpper) {
        this.ignoreUpper = ignoreUpper;
    }
    
    public final boolean isWordsToCheck() {
        return this.wordsToCheck;
    }
    
    public final void setWordsToCheck(final boolean wordsToCheck) {
        this.wordsToCheck = wordsToCheck;
    }
    
    public final int getCurPos() {
        return this.curPos;
    }
    
    public final int getTextLength() {
        return this.textLength;
    }
    
    public StringBuffer getText() {
        return this.sbText;
    }
    
    public final void setText(final StringBuffer sbText) {
        this.sbText = sbText;
        this.textLength = this.sbText.length();
    }
    
    public String getTextString() {
        return this.sbText.toString();
    }
    
    public final WordBundle getNextWord() {
        this.wordResult = "";
        this.wordComplete = false;
        this.done = false;
        this.hasLetters = false;
        this.hasLower = false;
        this.hasDigits = false;
        while (!this.done) {
            this.startPos = this.curPos;
            while (!this.wordComplete) {
                if (this.curPos < this.textLength) {
                    this.curChar = this.sbText.charAt(this.curPos);
                    if (this.curChar >= 'a' && this.curChar <= 'z') {
                        this.wordResult = String.valueOf(this.wordResult) + this.curChar;
                        this.hasLetters = true;
                        this.hasLower = true;
                    }
                    else if (this.curChar >= 'A' && this.curChar <= 'Z') {
                        this.wordResult = String.valueOf(this.wordResult) + this.curChar;
                        this.hasLetters = true;
                    }
                    else if (this.curChar >= '0' && this.curChar <= '9') {
                        this.wordResult = String.valueOf(this.wordResult) + this.curChar;
                        this.hasDigits = true;
                    }
                    else {
                        this.wordComplete = true;
                    }
                    ++this.curPos;
                }
                else {
                    this.wordComplete = true;
                    this.wordsToCheck = false;
                    this.done = true;
                }
            }
            if (this.hasLetters && !this.hasDigits && (!this.ignoreUpper || this.hasLower)) {
                this.result = new WordBundle(this.wordResult, this.startPos);
                this.done = true;
            }
            else {
                this.wordResult = "";
                this.result = new WordBundle(this.wordResult, this.startPos);
                this.wordComplete = false;
            }
            this.hasLetters = false;
            this.hasDigits = false;
            this.hasLower = false;
        }
        return this.result;
    }
}
