// 
// Decompiled by Procyon v0.5.30
// 

public class GameEvent
{
    public static final int WORDFOUND = 1;
    public static final int REQUESTSIZE = 2;
    private int id;
    private int wordIndex;
    private int result;
    
    public int getID() {
        return this.id;
    }
    
    public int getWordIndex() {
        return this.wordIndex;
    }
    
    public void setID(final int id) {
        this.id = id;
    }
    
    public void setWordIndex(final int wordIndex) {
        this.wordIndex = wordIndex;
    }
    
    public int getResult() {
        return this.result;
    }
    
    public void setResult(final int result) {
        this.result = result;
    }
}
