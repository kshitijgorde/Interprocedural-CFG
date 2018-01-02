import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class NotOperator implements CVSTest
{
    private static final int TEST_ZERO = 0;
    private static final int TEST_ONE = 1;
    private static final int TEST_OTHER_CHARS = 2;
    private boolean[] _boolBugs;
    private StringBuffer _output;
    private final int _seed;
    private final Random _random;
    
    public NotOperator() {
        this._seed = new Random().nextInt();
        this._random = new Random(this._seed);
        this._boolBugs = new boolean[3];
        final int flip = this._random.nextInt(3);
        this._boolBugs[flip] = true;
    }
    
    public String test(final String input) {
        this._output = new StringBuffer(input.length());
        for (int i = 0; i < input.length(); ++i) {
            switch (input.charAt(i)) {
                case '0': {
                    this.testZero();
                    break;
                }
                case '1': {
                    this.testOne();
                    break;
                }
                default: {
                    if (!this.testOthers()) {}
                    break;
                }
            }
        }
        return this._output.toString();
    }
    
    private void testZero() {
        if (this._boolBugs[0]) {
            this._output.append('0');
        }
        else {
            this._output.append('1');
        }
    }
    
    private void testOne() {
        if (this._boolBugs[1]) {
            this._output.append('1');
        }
        else {
            this._output.append('0');
        }
    }
    
    private boolean testOthers() {
        if (this._boolBugs[2]) {
            return false;
        }
        this._output = new StringBuffer("Error: Illegal Character");
        return true;
    }
    
    public String fix(final String input) {
        for (int i = 0; i < input.length(); ++i) {
            switch (input.charAt(i)) {
                case '0': {
                    if (this._boolBugs[0]) {
                        this._boolBugs[0] = (this._random.nextInt(9) < 2);
                        return "Fixed Bug: Zero";
                    }
                    break;
                }
                case '1': {
                    if (this._boolBugs[1]) {
                        this._boolBugs[1] = (this._random.nextInt(9) < 2);
                        return "Fixed Bug: One";
                    }
                    break;
                }
                default: {
                    if (this._boolBugs[2]) {
                        this._boolBugs[2] = (this._random.nextInt(9) < 2);
                        return "Fixed Bug: Illegal Character";
                    }
                    break;
                }
            }
        }
        this._boolBugs[this._random.nextInt(this._boolBugs.length - 1)] = true;
        return "Error: No bugs found.";
    }
    
    public String release() {
        for (int i = 0; i < this._boolBugs.length; ++i) {
            if (this._boolBugs[i]) {
                switch (i) {
                    case 0: {
                        return "Product Returned: Bug Zero";
                    }
                    case 1: {
                        return "Product Returned: Bug One";
                    }
                    case 2: {
                        return "Product Returned: Bug Illegal Characters";
                    }
                }
            }
        }
        return "Product Successful!";
    }
}
