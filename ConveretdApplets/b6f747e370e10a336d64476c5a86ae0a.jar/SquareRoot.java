import java.text.DecimalFormat;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class SquareRoot implements CVSTest
{
    private static final int MAX_INPUT = 1000000;
    private static final int TEST_ZERO = 0;
    private static final int TEST_POS_INT = 1;
    private static final int TEST_NEQ_INT = 2;
    private static final int TEST_POS_DBL = 3;
    private static final int TEST_NEQ_DBL = 4;
    private static final int TEST_NAN = 5;
    private static final int TEST_OUT_OF_RANGE = 6;
    private boolean[] _boolBugs;
    private final int _seed;
    private final Random _random;
    private static final DecimalFormat _format;
    
    public SquareRoot() {
        this._seed = new Random().nextInt();
        this._random = new Random(this._seed);
        this._boolBugs = new boolean[7];
        for (int i = 0; i < this._boolBugs.length; ++i) {
            final int spin = this._random.nextInt(9);
            this._boolBugs[i] = (spin < 2);
        }
    }
    
    public String test(final String input) {
        try {
            final int intInput = Integer.parseInt(input);
            if (Math.abs(intInput) >= 1000000) {
                return this.testOutOfRange(intInput);
            }
            if (intInput < 0) {
                return this.testNegativeInt(intInput);
            }
            if (intInput > 0) {
                return this.testPositiveInt(intInput);
            }
            return this.testZero();
        }
        catch (NumberFormatException e) {
            try {
                final double dblInput = Double.parseDouble(input);
                if (Math.abs(dblInput) >= 1000000.0) {
                    return this.testOutOfRange(dblInput);
                }
                if (Math.abs(dblInput) < 0.0) {
                    return this.testZero();
                }
                if (dblInput < 0.0) {
                    return this.testNeqativeDouble(dblInput);
                }
                if (dblInput > 0.0) {
                    return this.testPositiveDouble(dblInput);
                }
            }
            catch (NumberFormatException ex) {}
            return this.testNotANumber();
        }
    }
    
    public String fix(final String input) {
        try {
            final int intInput = Integer.parseInt(input);
            if (Math.abs(intInput) >= 1000000) {
                if (this._boolBugs[6]) {
                    final int spin = this._random.nextInt(9);
                    this._boolBugs[6] = (spin < 2);
                }
                else {
                    this._boolBugs[6] = true;
                }
                return "Fixed Bug: Out Of Range";
            }
            if (intInput < 0) {
                if (this._boolBugs[2]) {
                    final int spin = this._random.nextInt(9);
                    this._boolBugs[2] = (spin < 2);
                }
                else {
                    this._boolBugs[2] = true;
                }
                return "Fixed Bug: Negative Integers";
            }
            if (intInput > 0) {
                if (this._boolBugs[1]) {
                    final int spin = this._random.nextInt(9);
                    this._boolBugs[1] = (spin < 2);
                }
                else {
                    this._boolBugs[1] = true;
                }
                return "Fixed Bug: Positive Integers";
            }
            if (this._boolBugs[0]) {
                final int spin = this._random.nextInt(9);
                this._boolBugs[0] = (spin < 2);
            }
            else {
                this._boolBugs[0] = true;
            }
            return "Fixed Bug: Zero";
        }
        catch (NumberFormatException e) {
            try {
                final double dblInput = Double.parseDouble(input);
                if (Math.abs(dblInput) >= 1000000.0) {
                    if (this._boolBugs[6]) {
                        final int spin2 = this._random.nextInt(9);
                        this._boolBugs[6] = (spin2 < 2);
                    }
                    else {
                        this._boolBugs[6] = true;
                    }
                    return "Fixed Bug: Out Of Range";
                }
                if (Math.abs(dblInput) < 0.0) {
                    if (this._boolBugs[0]) {
                        final int spin2 = this._random.nextInt(9);
                        this._boolBugs[0] = (spin2 < 2);
                    }
                    else {
                        this._boolBugs[0] = true;
                    }
                    return "Fixed Bug: Zero";
                }
                if (dblInput < 0.0) {
                    if (this._boolBugs[4]) {
                        final int spin2 = this._random.nextInt(9);
                        this._boolBugs[4] = (spin2 < 2);
                    }
                    else {
                        this._boolBugs[4] = true;
                    }
                    return "Fixed Bug: Negative Reals";
                }
                if (dblInput > 0.0) {
                    if (this._boolBugs[3]) {
                        final int spin2 = this._random.nextInt(9);
                        this._boolBugs[3] = (spin2 < 2);
                    }
                    else {
                        this._boolBugs[3] = true;
                    }
                    return "Fixed Bug: Positive Reals";
                }
            }
            catch (NumberFormatException ex) {}
            if (this._boolBugs[5]) {
                final int spin3 = this._random.nextInt(9);
                this._boolBugs[5] = (spin3 < 2);
            }
            else {
                this._boolBugs[5] = true;
            }
            return "Fixed Bug: Not A Number";
        }
    }
    
    public String release() {
        for (int i = 0; i < this._boolBugs.length; ++i) {
            if (this._boolBugs[i]) {
                switch (i) {
                    case 0: {
                        return "Product Returned: Bug Zero";
                    }
                    case 1: {
                        return "Product Returned: Bug Positive Integers";
                    }
                    case 2: {
                        return "Product Returned: Bug Negative Integers";
                    }
                    case 3: {
                        return "Product Returned: Bug Positive Reals";
                    }
                    case 4: {
                        return "Product Returned: Bug Negative Reals";
                    }
                    case 5: {
                        return "Product Returned: Bug Not A Number";
                    }
                    case 6: {
                        return "Product Returned: Bug Out Of Range";
                    }
                }
            }
        }
        return "Product Successful!";
    }
    
    private String testZero() {
        if (this._boolBugs[0]) {
            return "Error: Not A Number!";
        }
        return "0";
    }
    
    private String testPositiveInt(final int input) {
        if (this._boolBugs[1]) {
            final DecimalFormat formatWrong = new DecimalFormat("0.00");
            return "" + formatWrong.format(Math.sqrt(input));
        }
        return "" + SquareRoot._format.format(Math.sqrt(input));
    }
    
    private String testNegativeInt(final int input) {
        if (this._boolBugs[2]) {
            return "" + SquareRoot._format.format(Math.sqrt(input * -1.0));
        }
        return "Error: Neqative Number!";
    }
    
    private String testPositiveDouble(final double input) {
        if (this._boolBugs[3]) {
            return "" + SquareRoot._format.format(Math.round(Math.sqrt(input)));
        }
        return "" + SquareRoot._format.format(Math.sqrt(input));
    }
    
    private String testNeqativeDouble(final double input) {
        if (this._boolBugs[4]) {
            return "" + SquareRoot._format.format(Math.sqrt(input * -1.0));
        }
        return "Error: Neqative Number!";
    }
    
    private String testNotANumber() {
        if (this._boolBugs[5]) {
            return "0";
        }
        return "Error: Not A Number!";
    }
    
    private String testOutOfRange(final double input) {
        if (!this._boolBugs[6]) {
            return "Error: Number Out Of Range!";
        }
        if (input < 0.0) {
            return this.testNeqativeDouble(input);
        }
        return this.testPositiveDouble(input);
    }
    
    static {
        _format = new DecimalFormat("0.000");
    }
}
