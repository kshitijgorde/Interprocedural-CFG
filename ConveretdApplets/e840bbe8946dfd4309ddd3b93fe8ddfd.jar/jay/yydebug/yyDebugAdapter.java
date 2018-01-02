// 
// Decompiled by Procyon v0.5.30
// 

package jay.yydebug;

import java.io.PrintStream;

public class yyDebugAdapter implements yyDebug
{
    protected final PrintStream out;
    
    public yyDebugAdapter(final PrintStream out) {
        this.out = out;
    }
    
    public yyDebugAdapter() {
        this(System.out);
    }
    
    public void push(final int n, final Object o) {
        this.out.println("push\tstate " + n + "\tvalue " + o);
    }
    
    public void lex(final int n, final int n2, final String s, final Object o) {
        this.out.println("lex\tstate " + n + "\treading " + s + "\tvalue " + o);
    }
    
    public void shift(final int n, final int n2, final int n3) {
        switch (n3) {
            default: {
                this.out.println("shift\tfrom state " + n + " to " + n2);
                break;
            }
            case 0:
            case 1:
            case 2: {
                this.out.println("shift\tfrom state " + n + " to " + n2 + "\t" + n3 + " left to recover");
                break;
            }
            case 3: {
                this.out.println("shift\tfrom state " + n + " to " + n2 + "\ton error");
                break;
            }
        }
    }
    
    public void pop(final int n) {
        this.out.println("pop\tstate " + n + "\ton error");
    }
    
    public void discard(final int n, final int n2, final String s, final Object o) {
        this.out.println("discard\tstate " + n + "\ttoken " + s + "\tvalue " + o);
    }
    
    public void reduce(final int n, final int n2, final int n3, final String s, final int n4) {
        this.out.println("reduce\tstate " + n + "\tuncover " + n2 + "\trule (" + n3 + ") " + s);
    }
    
    public void shift(final int n, final int n2) {
        this.out.println("goto\tfrom state " + n + " to " + n2);
    }
    
    public void accept(final Object o) {
        this.out.println("accept\tvalue " + o);
    }
    
    public void error(final String s) {
        this.out.println("error\t" + s);
    }
    
    public void reject() {
        this.out.println("reject");
    }
}
