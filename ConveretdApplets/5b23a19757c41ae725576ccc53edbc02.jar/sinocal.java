import java.io.IOException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class sinocal
{
    Vector calindex;
    static final int JD = 0;
    static final int DYNASTY = 1;
    static final int RULER = 2;
    static final int REIGN = 3;
    static final int REIGNYEAR = 4;
    static final int YCYCLE = 5;
    static final int MONTHTYPE = 6;
    static final int CMONTH = 9;
    static final int MCYCLE = 7;
    static final int CDAY = 8;
    static final int DCYCLE = 10;
    static final int STEM = 0;
    static final int BRANCH = 1;
    static final int WYEAR = 0;
    static final int WMONTH = 1;
    static final int WDAY = 2;
    static final String[] WEEKDAYS;
    static final String[] WMONTHS;
    static String[] stems;
    static String[] stempy;
    static String[] branches;
    static String[] branchpy;
    static String[] sbcycle;
    
    public static int JD2DayofWeek(final int n) {
        return n - 7 * ((n + 1) / 7) + 2 - 1;
    }
    
    public static int Gregorian2JD(final int n, final int n2, final int n3) {
        return 1461 * (n + 4800 + (n2 - 14) / 12) / 4 + 367 * (n2 - 2 - 12 * ((n2 - 14) / 12)) / 12 - 3 * ((n + 4900 + (n2 - 14) / 12) / 100) / 4 + n3 - 32075;
    }
    
    public static int[] JD2Gregorian(final int n) {
        final int[] array = new int[3];
        final int n2 = n + 68569;
        final int n3 = 4 * n2 / 146097;
        final int n4 = n2 - (146097 * n3 + 3) / 4;
        final int n5 = 4000 * (n4 + 1) / 1461001;
        final int n6 = n4 - 1461 * n5 / 4 + 31;
        final int n7 = 80 * n6 / 2447;
        array[2] = n6 - 2447 * n7 / 80;
        final int n8 = n7 / 11;
        array[1] = n7 + 2 - 12 * n8;
        array[0] = 100 * (n3 - 49) + n5 + n8;
        return array;
    }
    
    public static int Julian2JD(final int n, final int n2, final int n3) {
        return 367 * n - 7 * (n + 5001 + (n2 - 9) / 7) / 4 + 275 * n2 / 9 + n3 + 1729777;
    }
    
    public static int[] JD2Julian(final int n) {
        final int[] array = new int[3];
        final int n2 = n + 1402;
        final int n3 = (n2 - 1) / 1461;
        final int n4 = n2 - 1461 * n3;
        final int n5 = (n4 - 1) / 365 - n4 / 1461;
        final int n6 = n4 - 365 * n5 + 30;
        final int n7 = 80 * n6 / 2447;
        array[2] = n6 - 2447 * n7 / 80;
        final int n8 = n7 / 11;
        array[1] = n7 + 2 - 12 * n8;
        array[0] = 4 * n3 + n5 + n8 - 4716 - 1;
        return array;
    }
    
    public static int[] JD2cyear(final int n) {
        final int[] array = new int[2];
        final int n2 = 2696 + n;
        array[0] = n2 % 10;
        array[1] = n2 % 12;
        return array;
    }
    
    public static int[] JD2cday(final int n) {
        final int[] array = new int[2];
        final int n2 = (n - 11) % 60;
        array[0] = n2 % 10;
        array[1] = n2 % 12;
        return array;
    }
    
    public sinocal() {
        this.calindex = new Vector(10);
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("calindex.txt"), "Big5"));
            String line;
            do {
                line = bufferedReader.readLine();
                if (line == null) {
                    return;
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");
                this.calindex.addElement(new indexentry(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), stringTokenizer.nextToken(), stringTokenizer.nextToken()));
            } while (line != null);
        }
        catch (Exception ex) {
            System.err.println("Error in loading calendar dynasty index file");
        }
    }
    
    public int Chinese2JD(final String s, final String s2, final String s3, final int n, final int n2, final boolean b, final int n3) {
        final boolean b2 = false;
        final String[] array = new String[9];
        final String[] array2 = new String[8];
        String dynastyfile = null;
        for (int i = 0; i < this.calindex.size(); ++i) {
            final indexentry indexentry = this.calindex.elementAt(i);
            if (s.equals(indexentry.dynastyname)) {
                dynastyfile = indexentry.dynastyfile;
                break;
            }
        }
        if (dynastyfile == null) {
            System.out.println("Dynasty not in current list of dynasties");
            return -1;
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("calinfo/" + dynastyfile), "Big5"));
            final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), "\t");
            for (int j = 0; j < 8; ++j) {
                array[j] = stringTokenizer.nextToken();
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(line, "\t");
                for (int k = 0; k < 8; ++k) {
                    array2[k] = stringTokenizer2.nextToken();
                }
                array[0] = array2[0];
                if (!array2[1].equals("#")) {
                    array[1] = array2[1];
                }
                if (!array2[2].equals("#")) {
                    array[2] = array2[2];
                }
                if (!array2[3].equals("#")) {
                    array[3] = array2[3];
                }
                array[6] = array2[6];
                if (array2[6].equals("1")) {
                    array[4] = Integer.toString(Integer.parseInt(array[4]) + 1);
                    array[5] = Integer.toString(Integer.parseInt(array[5]) % 60 + 1);
                    array[7] = Integer.toString(Integer.parseInt(array[7]) % 60 + 1);
                }
                else if (!array2[6].equals("L")) {
                    array[7] = Integer.toString(Integer.parseInt(array[7]) % 60 + 1);
                }
                if (!array2[4].equals("#")) {
                    array[4] = array2[4];
                }
                int n4 = (Integer.parseInt(array[7]) + 10) % 12;
                if (n4 == 0) {
                    n4 = 12;
                }
                if (array[2].equals(s2) && array[3].equals(s3) && Integer.parseInt(array[4]) == n && array[6].equals("L") == b && n4 == n2) {
                    return Integer.parseInt(array[0]) + n3 - 1;
                }
            }
        }
        catch (IOException ex) {
            System.err.println("Error in Lunar->Solar date conversion");
            System.err.println(ex.getMessage());
        }
        return b2 ? 1 : 0;
    }
    
    public String[] JD2Chinese(final int n) {
        final String[] array = new String[11];
        final String[] array2 = new String[8];
        String dynastyfile = null;
        for (int i = 0; i < this.calindex.size(); ++i) {
            final indexentry indexentry = this.calindex.elementAt(i);
            if (n >= indexentry.dynastystart && n < indexentry.dynastyend) {
                dynastyfile = indexentry.dynastyfile;
                break;
            }
        }
        if (dynastyfile == null) {
            System.out.println("Outside of presently supported ranges.");
            return null;
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("calinfo/" + dynastyfile), "Big5"));
            final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), "\t");
            for (int j = 0; j < 8; ++j) {
                array[j] = stringTokenizer.nextToken();
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(line, "\t");
                for (int k = 0; k < 8; ++k) {
                    array2[k] = stringTokenizer2.nextToken();
                }
                if (n < Integer.parseInt(array2[0])) {
                    array[8] = Integer.toString(n - Integer.parseInt(array[0]) + 1);
                    int n2 = (Integer.parseInt(array[7]) + 10) % 12;
                    if (n2 == 0) {
                        n2 = 12;
                    }
                    array[9] = String.valueOf(n2);
                    array[10] = Integer.toString((n - 11) % 60 + 1);
                    return array;
                }
                array[0] = array2[0];
                if (!array2[1].equals("#")) {
                    array[1] = array2[1];
                }
                if (!array2[2].equals("#")) {
                    array[2] = array2[2];
                }
                if (!array2[3].equals("#")) {
                    array[3] = array2[3];
                }
                array[6] = array2[6];
                if (array2[6].equals("1")) {
                    array[4] = Integer.toString(Integer.parseInt(array[4]) + 1);
                    array[5] = Integer.toString(Integer.parseInt(array[5]) % 60 + 1);
                    array[7] = Integer.toString(Integer.parseInt(array[7]) % 60 + 1);
                }
                else if (!array2[6].equals("L")) {
                    array[7] = Integer.toString(Integer.parseInt(array[7]) % 60 + 1);
                }
                if (array2[4].equals("#")) {
                    continue;
                }
                array[4] = array2[4];
            }
        }
        catch (IOException ex) {
            System.err.println("Error in Solar->Lunar date conversion");
            System.err.println(ex.getMessage());
        }
        return array;
    }
    
    public String[] findChineseAfterJD(final int n, final int n2, final int n3) {
        final String[] array = new String[9];
        final String[] array2 = new String[8];
        String dynastyfile = null;
        for (int i = 0; i < this.calindex.size(); ++i) {
            final indexentry indexentry = this.calindex.elementAt(i);
            if (n >= indexentry.dynastystart && n < indexentry.dynastyend) {
                dynastyfile = indexentry.dynastyfile;
                break;
            }
        }
        if (dynastyfile == null) {
            System.out.println("Outside of presently supported ranges.");
            return null;
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("calinfo/" + dynastyfile), "Big5"));
            final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), "\t");
            for (int j = 0; j < 8; ++j) {
                array[j] = stringTokenizer.nextToken();
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(line, "\t");
                for (int k = 0; k < 8; ++k) {
                    array2[k] = stringTokenizer2.nextToken();
                }
                if (n < Integer.parseInt(array2[0])) {
                    array[8] = Integer.toString(n - Integer.parseInt(array[0]) + 1);
                    return array;
                }
                array[0] = array2[0];
                if (!array2[1].equals("#")) {
                    array[1] = array2[1];
                }
                if (!array2[2].equals("#")) {
                    array[2] = array2[2];
                }
                if (!array2[3].equals("#")) {
                    array[3] = array2[3];
                }
                array[6] = array2[6];
                if (array2[6].equals("1")) {
                    array[4] = Integer.toString(Integer.parseInt(array[4]) + 1);
                    array[5] = Integer.toString(Integer.parseInt(array[5]) % 60 + 1);
                    array[7] = Integer.toString(Integer.parseInt(array[7]) % 60 + 1);
                }
                else if (!array2[6].equals("L")) {
                    array[7] = Integer.toString(Integer.parseInt(array[7]) % 60 + 1);
                }
                if (array2[4].equals("#")) {
                    continue;
                }
                array[4] = array2[4];
            }
        }
        catch (IOException ex) {
            System.err.println("Error in Solar->Lunar date conversion");
            System.err.println(ex.getMessage());
        }
        return array;
    }
    
    protected void printChineseDate(final String[] array) {
        System.out.println("Dynasty    : " + array[1]);
        System.out.println("Ruler      : " + array[2]);
        System.out.println("Reign      : " + array[3]);
        System.out.println("Reign Year : " + array[4]);
        System.out.println("Year Cycle : " + sinocal.stempy[Integer.parseInt(array[5]) % 10 - 1] + " " + sinocal.branchpy[Integer.parseInt(array[5]) % 12 - 1]);
        int n = (Integer.parseInt(array[7]) + 10) % 12;
        if (n == 0) {
            n = 12;
        }
        System.out.println("Lunar Month: " + n);
        System.out.print("Leap Month : ");
        if (array[6].equals("L")) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
        System.out.println("Month Cycle: " + sinocal.stempy[Integer.parseInt(array[7]) % 10 - 1] + " " + sinocal.branchpy[Integer.parseInt(array[7]) % 12 - 1]);
        System.out.println("Lunar Day  : " + array[8]);
        final int[] jd2cday = JD2cday(Integer.parseInt(array[0]) + Integer.parseInt(array[8]) - 1);
        System.out.println("Day Cycle  : " + sinocal.stempy[jd2cday[0]] + sinocal.branchpy[jd2cday[1]]);
    }
    
    protected String ChineseDate2String(final String[] array) {
        final String string = String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer("Dynasty    : ").append(array[1]).append("\n").toString())).append("Ruler      : ").append(array[2]).append("\n").toString())).append("Reign      : ").append(array[3]).append("\n").toString())).append("Reign Year : ").append(array[4]).append("\n").toString()) + "Year Cycle : " + sinocal.stempy[Integer.parseInt(array[5]) % 10 - 1] + " " + sinocal.branchpy[Integer.parseInt(array[5]) % 12 - 1] + "\n";
        int n = (Integer.parseInt(array[7]) + 10) % 12;
        if (n == 0) {
            n = 12;
        }
        final String string2 = String.valueOf(new StringBuffer(String.valueOf(string)).append("Lunar Month: ").append(n).append("\n").toString()) + "Leap Month : ";
        String s;
        if (array[6].equals("L")) {
            s = String.valueOf(string2) + "YES\n";
        }
        else {
            s = String.valueOf(string2) + "NO\n";
        }
        final String string3 = String.valueOf(new StringBuffer(String.valueOf(s)).append("Month Cycle: ").append(sinocal.stempy[Integer.parseInt(array[7]) % 10 - 1]).append(" ").append(sinocal.branchpy[Integer.parseInt(array[7]) % 12 - 1]).append("\n").toString()) + "Lunar Day  : " + array[8] + "\n";
        final int[] jd2cday = JD2cday(Integer.parseInt(array[0]) + Integer.parseInt(array[8]) - 1);
        return String.valueOf(string3) + "Day Cycle  : " + sinocal.stempy[jd2cday[0]] + " " + sinocal.branchpy[jd2cday[1]] + "\n";
    }
    
    public void printWesternDate(final int[] array) {
        System.out.print(String.valueOf(sinocal.WMONTHS[array[1] - 1]) + " ");
        System.out.print(String.valueOf(array[2]) + ", ");
        if (array[0] > 0) {
            System.out.println(String.valueOf(array[0]) + " AD");
            return;
        }
        array[0] = 1 - array[0];
        System.out.println(String.valueOf(array[0]) + " BC");
    }
    
    public static void main(final String[] array) {
        final String s = array[0];
        final sinocal sinocal = new sinocal();
        final int int1 = Integer.parseInt(array[1]);
        if (s.equals("-jd2c")) {
            final String[] jd2Chinese = sinocal.JD2Chinese(int1);
            if (jd2Chinese != null) {
                sinocal.printChineseDate(jd2Chinese);
            }
        }
        else if (s.equals("-jd2g")) {
            sinocal.printWesternDate(JD2Gregorian(int1));
        }
        else if (s.equals("-jd2j")) {
            sinocal.printWesternDate(JD2Julian(int1));
        }
        else if (s.equals("-jd2wd")) {
            System.out.println(sinocal.WEEKDAYS[JD2DayofWeek(int1)]);
        }
        System.exit(0);
    }
    
    static {
        WEEKDAYS = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        WMONTHS = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        sinocal.stems = new String[] { "\u7532", "\u4e59", "\u4e19", "\u4e01", "\u620a", "\u5df1", "\u5e9a", "\u8f9b", "\u58ec", "\u7678" };
        sinocal.stempy = new String[] { "jia", "yi", "bing", "ding", "wu", "ji", "geng", "xin", "ren", "gui" };
        sinocal.branches = new String[] { "\u5b50", "\u4e11", "\u5bc5", "\u536f", "\u8fb0", "\u5df3", "\u5348", "\u672a", "\u7533", "\u9149", "\u620c", "\u4ea5" };
        sinocal.branchpy = new String[] { "zi", "chou", "yin", "mao", "chen", "si", "wu", "wei", "shen", "yu", "xu", "fu" };
        sinocal.sbcycle = new String[] { "jia-zi", "yi-chou", "bing-yin", "ding-mao", "wu-chen", "ji-si", "geng-wu", "xin-wei", "ren-shen", "gui-you", "jia-xu", "yi-hai", "bing-zi", "ding-chou", "wu-yin", "ji-mao", "geng-chen", "xin-si", "ren-wu", "gui-wei", "jia-shen", "yi-you", "bing-xu", "ding-hai", "wu-zi", "ji-chou", "geng-yin", "xin-mao", "ren-chen", "gui-si", "jia-wu", "yi-wei", "bing-shen", "ding-you", "wu-xu", "ji-hai", "geng-zi", "xin-chou", "ren-yin", "gui-mao", "jia-chen", "yi-si", "bing-wu", "ding-wei", "wu-shen", "ji-you", "geng-xu", "xin-hai", "ren-zi", "gui-chou", "jia-yin", "yi-mao", "bing-chen", "ding-si", "wu-wu", "ji-wei", "geng-shen", "xin0you", "ren-xu", "gui-hai" };
    }
    
    public class indexentry
    {
        public int dynastystart;
        public int dynastyend;
        public String dynastyfile;
        public String dynastyname;
        
        public indexentry(final int dynastystart, final int dynastyend, final String dynastyfile, final String dynastyname) {
            this.dynastystart = dynastystart;
            this.dynastyend = dynastyend;
            this.dynastyfile = dynastyfile;
            this.dynastyname = dynastyname;
        }
    }
}
