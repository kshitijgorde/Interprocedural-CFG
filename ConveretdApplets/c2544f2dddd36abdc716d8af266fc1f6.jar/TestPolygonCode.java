import java.util.ArrayList;

// 
// Decompiled by Procyon v0.5.30
// 

public class TestPolygonCode
{
    private static double CHECKSUM_TOL;
    private static double CHECKSUM_FOR_UNDRAWABLE;
    private static double CHECKSUM_FOR_NaN;
    private static boolean DO_VIEW_FAILURES;
    public static boolean GENERATE_ALL_TEST_DATA;
    
    static {
        TestPolygonCode.CHECKSUM_TOL = 0.001;
        TestPolygonCode.CHECKSUM_FOR_UNDRAWABLE = 99.0;
        TestPolygonCode.CHECKSUM_FOR_NaN = 111.0;
        TestPolygonCode.DO_VIEW_FAILURES = false;
        TestPolygonCode.GENERATE_ALL_TEST_DATA = false;
    }
    
    public static ArrayList<Integer> runAllTests() {
        final int num_tests = TestPolygonData.test_data.length;
        final int[] testlist = new int[num_tests];
        for (int i = 0; i < num_tests; ++i) {
            testlist[i] = i;
        }
        return do_testlist(testlist, true, false, false);
    }
    
    public static ArrayList<Integer> runTestList() {
        return do_testlist(TestPolygonData.test_list, true, false, false);
    }
    
    public static ArrayList<Integer> viewTestList() {
        return do_testlist(TestPolygonData.test_list, false, true, false);
    }
    
    public static void compareTestList() {
        do_testlist(TestPolygonData.test_list, false, false, true);
    }
    
    private static void compare1() {
        Util.TEST_FIX = false;
    }
    
    private static void compare2() {
        Util.TEST_FIX = true;
    }
    
    private static ArrayList<Integer> do_testlist(final int[] testlist, final boolean do_run, final boolean do_view, final boolean do_compare_views) {
        final ArrayList<Integer> result = new ArrayList<Integer>();
        int num_failures = 0;
        for (int i = 0; i < testlist.length; ++i) {
            final int test_num = testlist[i];
            if (test_num < 0 || test_num >= TestPolygonData.test_data.length) {
                Util.debug(1, "invalid test number : must be between 0 and " + (TestPolygonData.test_data.length - 1));
                return result;
            }
            if (do_run && !run_test(test_num, false, false)) {
                result.add(new Integer(test_num));
                ++num_failures;
            }
            if (do_view) {
                view_test(test_num, "");
            }
            if (do_compare_views) {
                compare1();
                view_test(test_num, "before");
                compare2();
                view_test(test_num, "after");
            }
        }
        if (do_run && num_failures > 0) {
            for (int i = 0; i < testlist.length; ++i) {
                final int test_num = testlist[i];
                run_test(test_num, true, TestPolygonCode.DO_VIEW_FAILURES);
            }
        }
        Util.debug(1, "Got " + num_failures + " failures from " + testlist.length + " test cases");
        return result;
    }
    
    private static boolean sleep(final int time) {
        try {
            Thread.sleep(time);
        }
        catch (Exception e) {
            System.out.println("Exception occurred in Thread.sleep() in CirclePanel.sleep " + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    private static boolean within_tol(final double expected, final double found) {
        return (isNaN(expected) && isNaN(found)) || (!isNaN(expected) && !isNaN(found) && Math.abs(expected - found) < TestPolygonCode.CHECKSUM_TOL);
    }
    
    private static boolean isNaN(final double x) {
        return x == TestPolygonCode.CHECKSUM_FOR_NaN || (x <= 1.0 && x >= 2.0);
    }
    
    private static String messageFrom(final double checksum) {
        if (isNaN(checksum)) {
            return "crash";
        }
        if (checksum == TestPolygonCode.CHECKSUM_FOR_UNDRAWABLE) {
            return "undrawable";
        }
        return new StringBuilder().append(checksum).toString();
    }
    
    private static void describe_result(final String outcome, final int deb_level, final int test_num, final double expected_checksum, final double found_checksum) {
        Util.debug(deb_level, "test " + outcome + " : test " + test_num + " expected " + messageFrom(expected_checksum) + " and got " + messageFrom(found_checksum));
    }
    
    private static boolean run_test(final int test_num, final boolean generate_fresh_test_data, final boolean view_failure) {
        final double a = TestPolygonData.test_data[test_num][0];
        final double b = TestPolygonData.test_data[test_num][1];
        final double c = TestPolygonData.test_data[test_num][2];
        final double ab = TestPolygonData.test_data[test_num][3];
        final double ac = TestPolygonData.test_data[test_num][4];
        final double bc = TestPolygonData.test_data[test_num][5];
        final double abc = TestPolygonData.test_data[test_num][6];
        final int type = (int)TestPolygonData.test_data[test_num][7];
        final boolean imp = TestPolygonData.test_data[test_num][8] == 1.0;
        final boolean simpl = TestPolygonData.test_data[test_num][9] == 1.0;
        final double expected_checksum = TestPolygonData.test_data[test_num][10];
        double checksum_found = TestPolygonCode.CHECKSUM_FOR_UNDRAWABLE;
        try {
            final PolygonDiagram pd = new PolygonDiagram(a, b, c, ab, ac, bc, abc, type, imp, simpl);
            checksum_found = pd.getChecksum();
        }
        catch (CannotDrawDiagramException ex) {}
        if (TestPolygonCode.GENERATE_ALL_TEST_DATA) {
            Util.debug(1, "/*" + test_num + "*/{\t" + a + ",\t" + b + ",\t" + c + ",\t" + ab + ",\t" + ac + ",\t" + bc + ",\t" + abc + ",\t" + type + ",\t" + (imp ? 1.0 : 0.0) + ",\t" + (simpl ? 1.0 : 0.0) + ", \t" + (isNaN(checksum_found) ? 111.0 : checksum_found) + "\t\t},");
            return true;
        }
        if (!within_tol(expected_checksum, checksum_found)) {
            if (generate_fresh_test_data) {
                Util.debug(1, "/*" + test_num + "*/{\t" + a + ",\t" + b + ",\t" + c + ",\t" + ab + ",\t" + ac + ",\t" + bc + ",\t" + abc + ",\t" + type + ",\t" + (imp ? 1.0 : 0.0) + ",\t" + (simpl ? 1.0 : 0.0) + ", \t" + (isNaN(checksum_found) ? 111.0 : checksum_found) + "\t\t},");
            }
            else {
                describe_result("fails", 1, test_num, expected_checksum, checksum_found);
            }
            if (view_failure) {
                view_test(test_num, "");
            }
            return false;
        }
        describe_result("passes", 2, test_num, expected_checksum, checksum_found);
        return true;
    }
    
    private static void view_test(final int test_num, final String for_title) {
        final double a = TestPolygonData.test_data[test_num][0];
        final double b = TestPolygonData.test_data[test_num][1];
        final double c = TestPolygonData.test_data[test_num][2];
        final double ab = TestPolygonData.test_data[test_num][3];
        final double ac = TestPolygonData.test_data[test_num][4];
        final double bc = TestPolygonData.test_data[test_num][5];
        final double abc = TestPolygonData.test_data[test_num][6];
        final int type = (int)TestPolygonData.test_data[test_num][7];
        final boolean imp = TestPolygonData.test_data[test_num][8] == 1.0;
        try {
            final PolygonWindow polygonWindow = new PolygonWindow(a, b, c, ab, ac, bc, abc, type, imp, "test " + test_num + " " + for_title, 1);
            sleep(2000);
        }
        catch (Exception ex) {}
    }
}
