import java.text.DecimalFormat;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class BattingAvg implements CVSTest
{
    private static final int TEST_ZERO = 0;
    private static final int TEST_HITS = 1;
    private static final int TEST_WALKS = 2;
    private static final int TEST_OUTS = 3;
    private static final int TEST_KS = 4;
    private static final int TEST_SACS = 5;
    private static final int TEST_SPACE = 6;
    private static final int TEST_OTHER_CHARS = 7;
    private boolean[] _boolBugs;
    private int _hits;
    private int _atbats;
    private String _error;
    private final int _seed;
    private final Random _random;
    private static final DecimalFormat _format;
    
    public BattingAvg() {
        this._seed = new Random().nextInt();
        this._random = new Random(this._seed);
        this._boolBugs = new boolean[8];
        for (int i = 0; i < this._boolBugs.length; ++i) {
            final int spin = this._random.nextInt(9);
            this._boolBugs[i] = (spin < 2);
        }
    }
    
    public String test(final String input) {
        this._error = "";
        this._hits = 0;
        this._atbats = 0;
        for (int i = 0; i < input.length(); ++i) {
            switch (input.charAt(i)) {
                case '0': {
                    this.testZero();
                    break;
                }
                case 'H':
                case 'h': {
                    this.testHits();
                    break;
                }
                case 'W':
                case 'w': {
                    this.testWalks();
                    break;
                }
                case 'O':
                case 'o': {
                    this.testOuts();
                    break;
                }
                case 'K':
                case 'k': {
                    this.testKs();
                    break;
                }
                case 'S':
                case 's': {
                    this.testSacs();
                    break;
                }
                case ' ': {
                    this.testSpace();
                    break;
                }
                default: {
                    this.testOthers();
                    break;
                }
            }
        }
        return this.battingAvg();
    }
    
    private String battingAvg() {
        if (this._error.length() > 0) {
            return this._error;
        }
        if (this._atbats == 0) {
            return "No at bats.";
        }
        final double avg = this._hits * 1.0 / (this._atbats * 1.0);
        return "" + BattingAvg._format.format(avg);
    }
    
    public String fix(final String input) {
        for (int i = 0; i < input.length(); ++i) {
            switch (input.charAt(i)) {
                case '0': {
                    if (this._boolBugs[0]) {
                        this._boolBugs[0] = (this._random.nextInt(9) < 2);
                        return "Fixed Bug: Zero as the letter '0'";
                    }
                    break;
                }
                case 'H':
                case 'h': {
                    if (this._boolBugs[1]) {
                        this._boolBugs[1] = (this._random.nextInt(9) < 2);
                        return "Fixed Bug: Hits Not Counted in 'At Bats'";
                    }
                    break;
                }
                case 'W':
                case 'w': {
                    if (this._boolBugs[2]) {
                        this._boolBugs[2] = (this._random.nextInt(9) < 2);
                        return "Fixed Bug: Walks Counted in 'At Bats'";
                    }
                    break;
                }
                case 'O':
                case 'o': {
                    if (this._boolBugs[3]) {
                        this._boolBugs[3] = (this._random.nextInt(9) < 2);
                        return "Fixed Bug: Outs Not Counted in 'At Bats'";
                    }
                    break;
                }
                case 'K':
                case 'k': {
                    if (this._boolBugs[4]) {
                        this._boolBugs[4] = (this._random.nextInt(9) < 2);
                        return "Fixed Bug: Strike Outs Not Counted in 'At Bats'";
                    }
                    break;
                }
                case 'S':
                case 's': {
                    if (this._boolBugs[5]) {
                        this._boolBugs[5] = (this._random.nextInt(9) < 2);
                        return "Fixed Bug: Sacrifices Counted in 'At Bats'";
                    }
                    break;
                }
                case ' ': {
                    if (this._boolBugs[6]) {
                        this._boolBugs[6] = (this._random.nextInt(9) < 2);
                        return "Fixed Bug: Space as Illegal Character";
                    }
                    break;
                }
                default: {
                    if (this._boolBugs[7]) {
                        this._boolBugs[7] = (this._random.nextInt(9) < 2);
                        return "Fixed Bug: Illegal Characters ignored";
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
                        return "Product Returned: Bug Zero as the letter '0'";
                    }
                    case 1: {
                        return "Product Returned: Bug Hits Not Counted in 'At Bats'";
                    }
                    case 2: {
                        return "Product Returned: Bug Walks Counted in 'At Bats'";
                    }
                    case 3: {
                        return "Product Returned: Bug Outs Not Counted in 'At Bats'";
                    }
                    case 4: {
                        return "Product Returned: Bug Strike Outs Not Counted in 'At Bats'";
                    }
                    case 5: {
                        return "Product Returned: Bug Sacrifices Counted in 'At Bats'";
                    }
                    case 6: {
                        return "Product Returned: Bug Space as Illegal Character";
                    }
                    case 7: {
                        return "Product Returned: Bug Illegal Characters ignored";
                    }
                }
            }
        }
        return "Product Successful!";
    }
    
    private void testZero() {
        if (!this._boolBugs[0]) {
            this._error = ((this._error.length() == 0) ? "Error: Illegal Character" : this._error);
        }
    }
    
    private void testHits() {
        if (this._boolBugs[1]) {
            ++this._hits;
        }
        else {
            ++this._hits;
            ++this._atbats;
        }
    }
    
    private void testWalks() {
        if (this._boolBugs[2]) {
            ++this._atbats;
        }
    }
    
    private void testOuts() {
        if (!this._boolBugs[3]) {
            ++this._atbats;
        }
    }
    
    private void testKs() {
        if (!this._boolBugs[4]) {
            ++this._atbats;
        }
    }
    
    private void testSacs() {
        if (this._boolBugs[5]) {
            ++this._atbats;
        }
    }
    
    private void testSpace() {
        if (this._boolBugs[6]) {
            this._error = ((this._error.length() == 0) ? "Error: Illegal Character" : this._error);
        }
    }
    
    private void testOthers() {
        if (!this._boolBugs[7]) {
            this._error = ((this._error.length() == 0) ? "Error: Illegal Character" : this._error);
        }
    }
    
    static {
        _format = new DecimalFormat("0.000");
    }
}
