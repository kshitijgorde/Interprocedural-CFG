import java.util.StringTokenizer;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class LittleManCompiler
{
    private String sourceCodeString;
    private int numLines;
    private Messenger messages;
    private int size;
    private Memory memory;
    private Hashtable codeSet;
    private Hashtable labels;
    private String[] sourceCode;
    
    public LittleManCompiler(final Memory memory, final Messenger messages) {
        this.messages = messages;
        this.memory = memory;
        this.size = this.memory.getMemorySize();
        this.sourceCode = new String[this.size];
        for (int i = 0; i < this.size; ++i) {
            this.sourceCode[i] = "";
        }
        this.codeSet = new Hashtable(30);
        this.buildCodeSet();
    }
    
    public void compile(final String s) throws LittleManException {
        this.sourceCodeString = s.trim().toUpperCase() + "\n";
        final StringTokenizer stringTokenizer = new StringTokenizer(this.sourceCodeString, "\n");
        this.sourceCode = new String[this.size];
        this.numLines = 0;
        while (stringTokenizer.hasMoreElements()) {
            final String nextToken = stringTokenizer.nextToken();
            this.messages.sendMessage(this.numLines + " : " + nextToken);
            if (!nextToken.trim().equals("")) {
                this.sourceCode[this.numLines] = nextToken;
                ++this.numLines;
            }
        }
        try {
            this.buildLabels();
            this.translate();
        }
        catch (LittleManException ex) {
            this.messages.sendMessage(ex.toString());
            throw new LittleManException("----- Compile Unsuccessful -----");
        }
    }
    
    private void buildLabels() throws LittleManException {
        this.messages.sendMessage("\n----- Resolving Labels -----");
        this.labels = new Hashtable(2 * this.size);
        for (int i = 0; i < this.numLines; ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.sourceCode[i]);
            if (stringTokenizer.hasMoreTokens()) {
                final String upperCase = stringTokenizer.nextToken().toUpperCase();
                if (!this.codeSet.containsKey(upperCase.trim())) {
                    this.messages.sendMessage(upperCase + " is a label for Address : " + i);
                    this.sourceCode[i] = "";
                    while (stringTokenizer.hasMoreTokens()) {
                        final StringBuffer sb = new StringBuffer();
                        final String[] sourceCode = this.sourceCode;
                        final int n = i;
                        sourceCode[n] = sb.append(sourceCode[n]).append(stringTokenizer.nextToken()).append(" ").toString();
                    }
                    if (this.labels.contains(upperCase)) {
                        throw new LittleManException("Label already exists in line " + i);
                    }
                    if (upperCase.length() > 10) {
                        throw new LittleManException("Label : " + upperCase + "is more than " + 10 + " characters long");
                    }
                    this.labels.put(upperCase, new Integer(i));
                }
            }
        }
    }
    
    private void translate() throws LittleManException {
        this.messages.sendMessage("\n----- Translating Mnemonics -----");
        for (int i = 0; i < this.numLines; ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.sourceCode[i]);
            if (!stringTokenizer.hasMoreTokens()) {
                throw new LittleManException("Missing Data in line " + i);
            }
            final String trim = stringTokenizer.nextToken().trim();
            this.messages.sendMessage("Line " + i + " : " + trim);
            if (!this.codeSet.containsKey(trim)) {
                throw new LittleManException("Illegal Instruction \"" + trim + "\" in line " + i);
            }
            final int intValue = this.codeSet.get(trim);
            if (intValue == 901 || intValue == 902 || intValue == 0) {
                this.messages.sendMessage("     Opcode = " + intValue);
                this.memory.setData(i, intValue);
            }
            else {
                String trim2 = "0";
                if (!stringTokenizer.hasMoreTokens() && intValue != 999) {
                    throw new LittleManException("Target label expected in line : " + i);
                }
                if (stringTokenizer.hasMoreTokens()) {
                    trim2 = stringTokenizer.nextToken().trim();
                }
                if (intValue == 999) {
                    try {
                        final int int1 = Integer.parseInt(trim2);
                        if (int1 < 1000 && int1 > -1000) {
                            this.memory.setData(i, int1);
                            continue;
                        }
                        throw new LittleManException("Data " + int1 + " is out of range in " + "line : " + i);
                    }
                    catch (NumberFormatException ex) {
                        throw new LittleManException("Data \"" + trim2 + "\" is not an Integer");
                    }
                }
                if (!this.labels.containsKey(trim2)) {
                    throw new LittleManException("Target Label \"" + trim2 + "\" is not valid.");
                }
                final int intValue2 = this.labels.get(trim2);
                String s;
                if (intValue2 < 10) {
                    s = "0" + intValue2;
                }
                else {
                    s = "" + intValue2;
                }
                this.messages.sendMessage("     Opcode = " + intValue + "  Address = " + s);
                this.memory.setData(i, intValue * 100 + intValue2);
            }
        }
    }
    
    private void buildCodeSet() {
        this.codeSet.put("LDA", new Integer(5));
        this.codeSet.put("LDO", new Integer(5));
        this.codeSet.put("STA", new Integer(3));
        this.codeSet.put("STO", new Integer(3));
        this.codeSet.put("ADD", new Integer(1));
        this.codeSet.put("SUB", new Integer(2));
        this.codeSet.put("IN", new Integer(901));
        this.codeSet.put("INP", new Integer(901));
        this.codeSet.put("OUT", new Integer(902));
        this.codeSet.put("COB", new Integer(0));
        this.codeSet.put("HLT", new Integer(0));
        this.codeSet.put("BRZ", new Integer(7));
        this.codeSet.put("BRP", new Integer(8));
        this.codeSet.put("BR", new Integer(6));
        this.codeSet.put("BRA", new Integer(6));
        this.codeSet.put("DAT", new Integer(999));
    }
}
