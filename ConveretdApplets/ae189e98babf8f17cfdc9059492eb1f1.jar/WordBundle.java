// 
// Decompiled by Procyon v0.5.30
// 

public class WordBundle
{
    String word;
    String upperWord;
    int position;
    
    public WordBundle(final String word, final int position) {
        this.word = word;
        this.upperWord = this.word.toUpperCase();
        this.position = position;
    }
    
    public String getUpperWord() {
        return this.upperWord;
    }
    
    public String getWord() {
        return this.word;
    }
    
    public int getPosition() {
        return this.position;
    }
}
