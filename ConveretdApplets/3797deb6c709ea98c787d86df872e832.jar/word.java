// 
// Decompiled by Procyon v0.5.30
// 

class word
{
    private String word;
    private String topic;
    private String[] guesses;
    private String charsTried;
    private int numWrong;
    
    public word(final String word, final String topic) {
        this.word = word;
        this.topic = topic;
        this.guesses = new String[word.length()];
        this.charsTried = "";
        this.numWrong = 0;
        for (int i = 0; i < word.length(); ++i) {
            this.guesses[i] = "- ";
        }
    }
    
    public boolean isCorrect(final String s) {
        return this.word.equals(s);
    }
    
    public String getTried() {
        return this.charsTried;
    }
    
    public String getTopic() {
        return this.topic;
    }
    
    public int getNumWrong() {
        return this.numWrong;
    }
    
    public boolean guess(final String s) {
        if (this.charsTried.indexOf(s) != -1) {
            return true;
        }
        int i = this.word.indexOf(s);
        if (i == -1) {
            this.charsTried = this.charsTried.concat(s);
            ++this.numWrong;
            return false;
        }
        do {
            this.guesses[i] = s;
            i = this.word.indexOf(s, i + 1);
        } while (i != -1);
        return true;
    }
    
    public String getGuesses() {
        String concat = "";
        for (int i = 0; i < this.word.length(); ++i) {
            concat = concat.concat(this.guesses[i]);
        }
        return concat;
    }
    
    public String toString() {
        return this.getGuesses();
    }
    
    public String getWord() {
        return this.word;
    }
}
