// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

public class InputHistory
{
    private String[] history;
    private int current;
    private int first;
    private int last;
    private static int size;
    String previous;
    
    private int upNo(final int num) {
        if (num == InputHistory.size) {
            return 0;
        }
        return num + 1;
    }
    
    public InputHistory() {
        this.history = new String[100];
        this.current = 0;
        this.first = 0;
        this.last = 0;
        this.previous = "";
        this.history[0] = "";
    }
    
    public String down() {
        if (this.current == this.first) {
            return this.history[this.first];
        }
        this.current = this.upNo(this.current);
        return this.history[this.current];
    }
    
    public void addLine(final String str) {
        if (!str.equals("")) {
            this.history[this.first] = str;
            if (!str.equals(this.previous)) {
                this.previous = str;
                this.first = this.upNo(this.first);
                if (this.first == this.last) {
                    this.last = this.upNo(this.last);
                }
            }
            this.current = this.first;
            this.history[this.first] = "";
        }
    }
    
    static {
        InputHistory.size = 99;
    }
    
    public String up(final String str) {
        if (this.current == this.last) {
            return "";
        }
        if (this.current == this.first) {
            this.history[this.first] = str;
        }
        this.current = this.downNo(this.current);
        return this.history[this.current];
    }
    
    private int downNo(final int num) {
        if (num == 0) {
            return InputHistory.size;
        }
        return num - 1;
    }
}
