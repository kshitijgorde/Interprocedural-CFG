import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class VersionControl
{
    private int _revHigh;
    private int _revLow;
    private String _projectName;
    private Vector _vectorValues;
    private Vector _vectorTests;
    private CVSTest _testClass;
    
    public VersionControl() {
        this._projectName = "";
        this._vectorValues = new Vector();
        this._vectorTests = new Vector();
        this._testClass = null;
        this._revHigh = 0;
        this._revLow = 0;
    }
    
    public VersionControl(final String projectName, final CVSTest testClass) {
        this._projectName = projectName;
        this._vectorValues = new Vector();
        this._vectorTests = new Vector();
        this._testClass = testClass;
        this._revHigh = 0;
        this._revLow = 0;
        final String[] init = { "" + this._revHigh + "." + this._revLow, "Ready", "Ready for Debugging" };
        this._vectorValues.add(init);
    }
    
    public String[] checkIn(final String comment) {
        final String[] result = { "" + this._revHigh + "." + this._revLow, "Check In", comment };
        this._vectorValues.add(0, result);
        return result;
    }
    
    public String[] checkOut() {
        final String[] result = { "" + this._revHigh + "." + this._revLow, "Check Out", "" };
        this._vectorValues.add(0, result);
        return result;
    }
    
    public String[] release() {
        final String[] result = new String[3];
        ++this._revHigh;
        result[this._revLow = 0] = "" + this._revHigh + "." + this._revLow;
        result[1] = "Release";
        result[2] = "Shipped to Customers";
        this._vectorValues.add(0, result);
        return result;
    }
    
    public String[] feedback() {
        final String[] result = { "" + this._revHigh + "." + this._revLow, "Feedback", this._testClass.release() };
        this._vectorValues.add(0, result);
        return result;
    }
    
    public String[] failed(final String comment) {
        final String[] result = { "" + this._revHigh + "." + this._revLow, "Failed", comment };
        this._vectorValues.add(0, result);
        return result;
    }
    
    public String[] ready() {
        final String[] result = new String[3];
        ++this._revLow;
        result[0] = "" + this._revHigh + "." + this._revLow;
        result[1] = "Ready";
        result[2] = "Ready for Debugging.";
        this._vectorValues.add(0, result);
        return result;
    }
    
    public String[] fix(final String input) {
        final String[] result = { "" + this._revHigh + "." + this._revLow, "Fixed", null };
        if (this._testClass == null) {
            result[2] = "";
        }
        else {
            result[2] = this._testClass.fix(input);
        }
        this._vectorValues.add(0, result);
        return result;
    }
    
    public String getName() {
        return this._projectName;
    }
    
    public String[][] getAll() {
        final String[][] all = new String[this._vectorValues.size()][3];
        for (int i = 0; i < all.length; ++i) {
            all[i] = this._vectorValues.elementAt(i);
        }
        return all;
    }
    
    public String[][] getAllTests() {
        final String[][] all = new String[this._vectorTests.size()][3];
        for (int i = 0; i < all.length; ++i) {
            (all[i] = this._vectorTests.elementAt(i))[0] = "" + (all.length - i);
        }
        return all;
    }
    
    public String[][] getAllTestsClean() {
        final String[][] all = new String[this._vectorTests.size()][3];
        for (int i = 0; i < all.length; ++i) {
            (all[i] = this._vectorTests.elementAt(i))[0] = "" + (all.length - i);
            all[i][3] = "";
        }
        return all;
    }
    
    public String[] addTest(final String input, final String output, final boolean pass) {
        final String[] result = { "" + (this._vectorTests.size() + 1), input, output, pass ? "P" : "F" };
        this._vectorTests.add(0, result);
        return result;
    }
    
    public String[][] removeTest(final int index) {
        try {
            this._vectorTests.remove(index);
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        return this.getAllTests();
    }
    
    public String test(final String input) {
        if (this._testClass == null) {
            return "";
        }
        return this._testClass.test(input);
    }
}
