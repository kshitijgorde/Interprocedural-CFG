import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScriptOutput
{
    private StringBuffer sb;
    boolean append;
    boolean appendScript;
    int booleanValue;
    Stack charAt;
    int extraSpace;
    private int previous;
    
    public ScriptOutput(final int extraSpace) {
        this.sb = new StringBuffer();
        this.append = false;
        this.booleanValue = 0;
        this.charAt = new Stack();
        this.extraSpace = extraSpace;
        this.previous = -1;
    }
    
    public final void appendText(final String s, final int n) {
        if (((n != 1 && n != 5) || (this.previous != 1 && this.previous != 5)) && (this.extraSpace == 2 || this.appendScript)) {
            this.extraSpace(s);
        }
        this.appendScript = false;
        this.append(s, n);
    }
    
    public final void appendScript(final String s) {
        if (this.extraSpace == 2 || !this.appendScript) {
            this.extraSpace(s);
        }
        this.appendScript = true;
        this.append(s, -1);
    }
    
    private void extraSpace(final String s) {
        final int length = this.sb.length();
        if (length > 0 && s.length() > 0 && Character.isLetter(s.charAt(0))) {
            if (Character.isLetter(this.sb.charAt(length - 1))) {
                this.append(" ", -1);
            }
            else if (this.extraSpace == 2) {
                int n;
                for (n = length - 1; n >= 0 && Character.isDigit(this.sb.charAt(n)); --n) {}
                if (n >= 0) {
                    int n2;
                    for (n2 = n; n2 >= 0 && this.isWirisIdentifierPart(this.sb.charAt(n2)); --n2) {}
                    if (n2 < n) {
                        this.append(" ", -1);
                    }
                }
            }
        }
    }
    
    private final boolean isWirisIdentifierPart(final char c) {
        return Character.isJavaIdentifierPart(c) || c == '?' || c == '´';
    }
    
    public final String toString() {
        return this.sb.toString();
    }
    
    public final void beginBox(final BoxScripting boxScripting, final AbstractBox abstractBox, String string) {
        if (this.append) {
            if (boxScripting.scriptMode == 0) {
                boxScripting.appendScript("{");
            }
            if (boxScripting.scriptMode == 2 && this.booleanValue > 0) {
                boxScripting.appendScript(",");
            }
        }
        ++this.booleanValue;
        this.charAt.push(new Boolean(this.append));
        if (string == null) {
            this.append = false;
            return;
        }
        this.charAt.push(new Integer(this.booleanValue));
        this.append = true;
        this.booleanValue = 0;
        if (boxScripting.scriptMode == 0) {
            if (Character.isLetter(string.charAt(0))) {
                string = "\\" + string;
            }
            boxScripting.openTag(string);
        }
        else {
            boxScripting.openTag(string);
            boxScripting.appendScript("(");
        }
    }
    
    public final void endBox(final BoxScripting boxScripting, final AbstractBox abstractBox, final String s) {
        if (s != null) {
            this.booleanValue = this.charAt.pop();
        }
        this.append = this.charAt.pop();
        if (this.append && boxScripting.scriptMode == 0) {
            boxScripting.appendScript("}");
        }
        if (s != null && boxScripting.scriptMode == 2) {
            boxScripting.appendScript(")");
        }
    }
    
    public static final void onScript(final BoxScripting boxScripting, final AbstractBox[] array, final int n, final int n2) {
        for (int i = n; i < n2; ++i) {
            array[i].script(boxScripting);
        }
    }
    
    public final int getPosition() {
        return this.sb.length();
    }
    
    private void append(final String s, final int previous) {
        this.sb.append(s);
        this.previous = previous;
    }
}
