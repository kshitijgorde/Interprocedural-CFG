// 
// Decompiled by Procyon v0.5.30
// 

public class PolygonTester
{
    private static double CHECKSUM_TOL;
    private static double CHECKSUM_FOR_UNDRAWABLE;
    private static double CHECKSUM_FOR_NaN;
    private static int[] TESTLIST;
    private static boolean DO_RUN_TESTLIST;
    private static boolean DO_VIEW_TESTLIST;
    private static boolean DO_COMPARE_TESTLIST;
    private static boolean DO_VIEW_FAILURES;
    private static boolean GENERATE_ALL_TEST_DATA;
    private static double[][] test_data;
    
    static {
        PolygonTester.CHECKSUM_TOL = 0.001;
        PolygonTester.CHECKSUM_FOR_UNDRAWABLE = 99.0;
        PolygonTester.CHECKSUM_FOR_NaN = 111.0;
        PolygonTester.TESTLIST = new int[] { 47 };
        PolygonTester.DO_RUN_TESTLIST = false;
        PolygonTester.DO_VIEW_TESTLIST = true;
        PolygonTester.DO_COMPARE_TESTLIST = false;
        PolygonTester.DO_VIEW_FAILURES = false;
        PolygonTester.GENERATE_ALL_TEST_DATA = false;
        PolygonTester.test_data = new double[][] { { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 0.0, 0.0, 0.0, -13.170098235553688 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 1.0, 0.0, 0.0, -23.166006705088137 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 2.0, 0.0, 0.0, -23.71170177796192 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 0.0, 0.0, -23.699891664738313 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 2.0, 0.0, 0.0, 0.0, 99.0 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 2.0, 1.0, 0.0, 0.0, -16.46530716246306 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 2.0, 2.0, 0.0, 0.0, -17.293344523784988 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 2.0, 3.0, 0.0, 0.0, -17.855757619663695 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 1.0, 0.0, 0.0, 0.0, 99.0 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 1.0, 1.0, 0.0, 0.0, 99.0 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 1.0, 2.0, 0.0, 0.0, -9.37162243784951 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 1.0, 3.0, 0.0, 0.0, -10.182646857574282 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 4.0, 0.0, 0.0, 0.0, -13.493187001943612 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 4.0, 1.0, 0.0, 0.0, -28.21637400388727 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 4.0, 2.0, 0.0, 0.0, -28.920951780840344 }, { 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 4.0, 3.0, 0.0, 0.0, -28.21637917549823 }, { 19.0, 16.0, 32.0, 12.0, 20.0, 13.0, 10.0, 0.0, 0.0, 0.0, 99.0 }, { 19.0, 16.0, 32.0, 12.0, 20.0, 13.0, 10.0, 1.0, 0.0, 0.0, -70.28447087731463 }, { 19.0, 16.0, 32.0, 12.0, 20.0, 13.0, 10.0, 2.0, 0.0, 0.0, -5.499693932541803 }, { 19.0, 16.0, 32.0, 12.0, 20.0, 13.0, 10.0, 3.0, 0.0, 0.0, -23.15925880798242 }, { 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 1.0, 0.0, 0.0, 0.0, 99.0 }, { 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 1.0, 1.0, 0.0, 0.0, 99.0 }, { 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 1.0, 2.0, 0.0, 0.0, 26.335824215700512 }, { 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 1.0, 3.0, 0.0, 0.0, 3.038744392987885 }, { 2.39, 1.12, 1.87, 1.65, 1.9, 2.22, 1.1, 0.0, 0.0, 0.0, 99.0 }, { 2.39, 1.12, 1.87, 1.65, 1.9, 2.22, 1.1, 1.0, 0.0, 0.0, -12.028074565336867 }, { 2.39, 1.12, 1.87, 1.65, 1.9, 2.22, 1.1, 2.0, 0.0, 0.0, -10.812293075138461 }, { 2.39, 1.12, 1.87, 1.65, 1.9, 2.22, 1.1, 3.0, 0.0, 0.0, -10.681694411603393 }, { 1.0, 2.0, 1.87, 4.0, 1.9, 2.22, 1.0, 0.0, 0.0, 0.0, 99.0 }, { 1.0, 2.0, 1.87, 4.0, 1.9, 2.22, 1.0, 1.0, 0.0, 0.0, 99.0 }, { 1.0, 2.0, 1.87, 4.0, 1.9, 2.22, 1.0, 2.0, 0.0, 0.0, -10.295925312293404 }, { 1.0, 2.0, 1.87, 4.0, 1.9, 2.22, 1.0, 3.0, 0.0, 0.0, -17.712941128554977 }, { 0.2, 0.01, 1.87, 0.135, 1.9, 2.22, 1.0, 0.0, 0.0, 0.0, -1.0803041804686822 }, { 0.2, 0.01, 1.87, 0.135, 1.9, 2.22, 1.0, 1.0, 0.0, 0.0, 4.615634279794126 }, { 0.2, 0.01, 1.87, 0.135, 1.9, 2.22, 1.0, 2.0, 0.0, 0.0, 1.2194487560704412 }, { 0.2, 0.01, 1.87, 0.135, 1.9, 2.22, 1.0, 3.0, 0.0, 0.0, -1.0802650207611078 }, { 3.0, 2.0, 15.0, 1.0, 17.0, 18.0, 10.0, 0.0, 0.0, 0.0, -3.987428734246992 }, { 3.0, 2.0, 15.0, 1.0, 17.0, 18.0, 10.0, 1.0, 0.0, 0.0, -15.268958329828838 }, { 3.0, 2.0, 15.0, 1.0, 17.0, 18.0, 10.0, 2.0, 0.0, 0.0, -12.310396727538564 }, { 3.0, 2.0, 15.0, 1.0, 17.0, 18.0, 10.0, 3.0, 0.0, 0.0, -16.498896215677505 }, { 19.0, 50.0, 15.0, 12.0, 20.0, 13.0, 10.0, 0.0, 0.0, 0.0, 99.0 }, { 19.0, 50.0, 15.0, 12.0, 20.0, 13.0, 10.0, 1.0, 0.0, 0.0, -84.25393346890625 }, { 19.0, 50.0, 15.0, 12.0, 20.0, 13.0, 10.0, 2.0, 0.0, 0.0, -74.25166432435611 }, { 19.0, 50.0, 15.0, 12.0, 20.0, 13.0, 10.0, 3.0, 0.0, 0.0, -51.163113574479816 }, { 19.0, 100.0, 5.0, 100.0, 50.0, 13.0, 10.0, 0.0, 0.0, 0.0, 99.0 }, { 19.0, 100.0, 5.0, 100.0, 50.0, 13.0, 10.0, 1.0, 0.0, 0.0, 99.0 }, { 19.0, 100.0, 5.0, 100.0, 50.0, 13.0, 10.0, 2.0, 0.0, 0.0, -257.08631262465985 }, { 19.0, 100.0, 5.0, 100.0, 50.0, 13.0, 10.0, 3.0, 0.0, 0.0, -404.39154167972686 }, { 1.0, 2.0, 1.87, 4.0, 1.9, 2.22, 10.0, 0.0, 0.0, 0.0, -17.053623534277975 }, { 1.0, 2.0, 1.87, 4.0, 1.9, 2.22, 10.0, 1.0, 0.0, 0.0, -56.587894249375864 }, { 1.0, 2.0, 1.87, 4.0, 1.9, 2.22, 10.0, 2.0, 0.0, 0.0, -52.27881983790355 }, { 1.0, 2.0, 1.87, 4.0, 1.9, 2.22, 10.0, 3.0, 0.0, 0.0, -56.58785306615698 }, { 4.27, 1.84, 0.9, 0.92, 0.13, 0.18, 0.24, 0.0, 0.0, 0.0, 99.0 }, { 4.27, 1.84, 0.9, 0.92, 0.13, 0.18, 0.24, 1.0, 0.0, 0.0, -16.107141250661176 }, { 4.27, 1.84, 0.9, 0.92, 0.13, 0.18, 0.24, 2.0, 0.0, 0.0, -35.60108244519265 }, { 4.27, 1.84, 0.9, 0.92, 0.13, 0.18, 0.24, 3.0, 0.0, 0.0, -25.719220197548434 }, { 21702.0, 26600.0, 7695.0, 5159.0, 433.0, 6978.0, 795.0, 0.0, 0.0, 0.0, 99.0 }, { 21702.0, 26600.0, 7695.0, 5159.0, 433.0, 6978.0, 795.0, 1.0, 0.0, 0.0, 4691.0543350742355 }, { 21702.0, 26600.0, 7695.0, 5159.0, 433.0, 6978.0, 795.0, 2.0, 0.0, 0.0, -512.445528838304 }, { 21702.0, 26600.0, 7695.0, 5159.0, 433.0, 6978.0, 795.0, 3.0, 0.0, 0.0, -555.0348037318994 }, { 18.0, 42.0, 2.0, 26.0, 1.0, 9.0, 1.0, 0.0, 0.0, 0.0, 99.0 }, { 18.0, 42.0, 2.0, 26.0, 1.0, 9.0, 1.0, 1.0, 0.0, 0.0, 99.0 }, { 18.0, 42.0, 2.0, 26.0, 1.0, 9.0, 1.0, 2.0, 0.0, 0.0, -228.4446953590244 }, { 18.0, 42.0, 2.0, 26.0, 1.0, 9.0, 1.0, 3.0, 0.0, 0.0, -178.911453526563 }, { 9.0, 21.0, 4.0, 34.0, 2.0, 27.0, 4.0, 0.0, 0.0, 0.0, 99.0 }, { 9.0, 21.0, 4.0, 34.0, 2.0, 27.0, 4.0, 1.0, 0.0, 0.0, 99.0 }, { 9.0, 21.0, 4.0, 34.0, 2.0, 27.0, 4.0, 2.0, 0.0, 0.0, -50.56117547074938 }, { 9.0, 21.0, 4.0, 34.0, 2.0, 27.0, 4.0, 3.0, 0.0, 0.0, -48.10215226709711 }, { 2.0, 3.0, 7.0, 3.0, 2.0, 5.0, 1.19, 0.0, 0.0, 0.0, 99.0 }, { 2.0, 3.0, 7.0, 3.0, 2.0, 5.0, 1.19, 1.0, 0.0, 0.0, 36.64032370212906 }, { 2.0, 3.0, 7.0, 3.0, 2.0, 5.0, 1.19, 2.0, 0.0, 0.0, 36.026636102606666 }, { 2.0, 3.0, 7.0, 3.0, 2.0, 5.0, 1.19, 3.0, 0.0, 0.0, 24.357136396056948 }, { 4.0, 4.0, 4.0, 3.0, 3.0, 3.0, 3.0, 0.0, 0.0, 0.0, -12.83967482312227 }, { 4.0, 4.0, 4.0, 3.0, 3.0, 3.0, 3.0, 1.0, 0.0, 0.0, -23.139391639620435 }, { 4.0, 4.0, 4.0, 3.0, 3.0, 3.0, 3.0, 2.0, 0.0, 0.0, -23.128271273418584 }, { 4.0, 4.0, 4.0, 3.0, 3.0, 3.0, 3.0, 3.0, 0.0, 0.0, -23.12891506587217 }, { 3.0, 1.84, 0.92, 0.9, 0.18, 0.13, 1.0, 3.0, 0.0, 0.0, -27.125633023895183 }, { 1.0, 0.3, 0.3, 1.0, 1.0, 3.0, 2.0, 2.0, 0.0, 0.0, -17.143088115198506 }, { 1.0, 0.4, 0.4, 1.0, 1.0, 3.0, 2.0, 2.0, 0.0, 0.0, 12345.0 } };
    }
    
    private static void compare1() {
        Util.TYPE1_FIX = false;
    }
    
    private static void compare2() {
        Util.TYPE1_FIX = true;
    }
    
    public static void main(final String[] args) {
        if (PolygonTester.DO_RUN_TESTLIST || PolygonTester.DO_VIEW_TESTLIST || PolygonTester.DO_COMPARE_TESTLIST) {
            do_testlist(PolygonTester.TESTLIST, PolygonTester.DO_RUN_TESTLIST, PolygonTester.DO_VIEW_TESTLIST, PolygonTester.DO_COMPARE_TESTLIST);
        }
        else {
            final int num_tests = PolygonTester.test_data.length;
            final int[] testlist = new int[num_tests];
            for (int i = 0; i < num_tests; ++i) {
                testlist[i] = i;
            }
            do_testlist(testlist, true, false, false);
        }
    }
    
    private static void do_testlist(final int[] testlist, final boolean do_run, final boolean do_view, final boolean do_compare_views) {
        int num_failures = 0;
        for (int i = 0; i < testlist.length; ++i) {
            final int test_num = testlist[i];
            if (test_num < 0 || test_num >= PolygonTester.test_data.length) {
                Util.debug(1, "invalid test number : must be between 0 and " + (PolygonTester.test_data.length - 1));
                return;
            }
            if (do_run && !run_test(test_num, false, false)) {
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
                run_test(test_num, true, PolygonTester.DO_VIEW_FAILURES);
            }
        }
        Util.debug(1, "Got " + num_failures + " failures from " + testlist.length + " test cases");
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
        return (isNaN(expected) && isNaN(found)) || (!isNaN(expected) && !isNaN(found) && Math.abs(expected - found) < PolygonTester.CHECKSUM_TOL);
    }
    
    private static boolean isNaN(final double x) {
        return x == PolygonTester.CHECKSUM_FOR_NaN || (x <= 1.0 && x >= 2.0);
    }
    
    private static String messageFrom(final double checksum) {
        if (isNaN(checksum)) {
            return "crash";
        }
        if (checksum == PolygonTester.CHECKSUM_FOR_UNDRAWABLE) {
            return "undrawable";
        }
        return new StringBuilder().append(checksum).toString();
    }
    
    private static void describe_result(final String outcome, final int deb_level, final int test_num, final double expected_checksum, final double found_checksum) {
        Util.debug(deb_level, "test " + outcome + " : test " + test_num + " expected " + messageFrom(expected_checksum) + " and got " + messageFrom(found_checksum));
    }
    
    private static boolean run_test(final int test_num, final boolean generate_fresh_test_data, final boolean view_failure) {
        final double a = PolygonTester.test_data[test_num][0];
        final double b = PolygonTester.test_data[test_num][1];
        final double c = PolygonTester.test_data[test_num][2];
        final double ab = PolygonTester.test_data[test_num][3];
        final double ac = PolygonTester.test_data[test_num][4];
        final double bc = PolygonTester.test_data[test_num][5];
        final double abc = PolygonTester.test_data[test_num][6];
        final int type = (int)PolygonTester.test_data[test_num][7];
        final boolean imp = PolygonTester.test_data[test_num][8] == 1.0;
        final boolean simpl = PolygonTester.test_data[test_num][9] == 1.0;
        final double expected_checksum = PolygonTester.test_data[test_num][10];
        double checksum_found = PolygonTester.CHECKSUM_FOR_UNDRAWABLE;
        try {
            final PolygonDiagram pd = new PolygonDiagram(a, b, c, ab, ac, bc, abc, type, imp, simpl);
            checksum_found = pd.getChecksum();
        }
        catch (CannotDrawDiagramException ex) {}
        if (PolygonTester.GENERATE_ALL_TEST_DATA) {
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
        final double a = PolygonTester.test_data[test_num][0];
        final double b = PolygonTester.test_data[test_num][1];
        final double c = PolygonTester.test_data[test_num][2];
        final double ab = PolygonTester.test_data[test_num][3];
        final double ac = PolygonTester.test_data[test_num][4];
        final double bc = PolygonTester.test_data[test_num][5];
        final double abc = PolygonTester.test_data[test_num][6];
        final int type = (int)PolygonTester.test_data[test_num][7];
        final boolean imp = PolygonTester.test_data[test_num][8] == 1.0;
        try {
            final PolygonWindow polygonWindow = new PolygonWindow(a, b, c, ab, ac, bc, abc, type, imp, "test " + test_num + " " + for_title, 1);
            sleep(2000);
        }
        catch (Exception ex) {}
    }
}
