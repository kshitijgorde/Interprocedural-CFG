// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.regex.Pattern;

class MatchQueryExp extends QueryEval implements QueryExp
{
    private static final long serialVersionUID = -7156603696948215014L;
    private AttributeValueExp exp;
    private String pattern;
    private transient Pattern re;
    
    public MatchQueryExp() {
    }
    
    public MatchQueryExp(final AttributeValueExp attr, final String string) {
        this.exp = attr;
        this.pattern = string;
        this.generateRegExp();
    }
    
    public boolean apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        final ValueExp calcAttr = this.exp.apply(name);
        return calcAttr instanceof StringValueExp && this.re.matcher(calcAttr.toString()).matches();
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.generateRegExp();
    }
    
    public String toString() {
        return new String("(" + this.exp.toString() + " matches " + this.pattern.toString() + ")");
    }
    
    private void generateRegExp() {
        final StringBuffer buffer = new StringBuffer();
        final char[] chars = this.pattern.toCharArray();
        boolean escaping = false;
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == '\\' && !escaping) {
                escaping = true;
            }
            else {
                if (chars[i] == '?' && !escaping) {
                    buffer.append("(?:.)");
                }
                else if (chars[i] == '?') {
                    buffer.append("\\?");
                }
                else if (chars[i] == '*' && !escaping) {
                    buffer.append("(?:.)*");
                }
                else if (chars[i] == '*') {
                    buffer.append("\\*");
                }
                else if (chars[i] == '^') {
                    buffer.append("\\^");
                }
                else if (chars[i] == '$') {
                    buffer.append("\\$");
                }
                else if (chars[i] == '\\') {
                    buffer.append("\\\\");
                }
                else if (chars[i] == '.') {
                    buffer.append("\\.");
                }
                else if (chars[i] == '|') {
                    buffer.append("\\|");
                }
                else if (chars[i] == '[' && escaping) {
                    buffer.append("\\[");
                }
                else {
                    buffer.append(chars[i]);
                }
                escaping = false;
            }
        }
        if (escaping) {
            buffer.append("\\\\");
        }
        this.re = Pattern.compile(buffer.toString());
    }
}
