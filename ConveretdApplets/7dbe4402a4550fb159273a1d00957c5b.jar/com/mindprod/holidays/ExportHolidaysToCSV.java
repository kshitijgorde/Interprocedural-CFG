// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.io.InputStream;
import com.mindprod.common11.Misc;
import java.io.IOException;
import java.util.Iterator;
import com.mindprod.common11.BigDate;
import com.mindprod.csv.CSVWriter;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public final class ExportHolidaysToCSV
{
    private static ArrayList<HolInfo> holidayDelegates;
    
    private static void exportHolidays(final int desiredYear, final boolean verbose) throws IOException {
        final PrintWriter pw = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("holidaysInYear" + desiredYear + ".csv"), 32768), "UTF-8"));
        final CSVWriter w = new CSVWriter(pw, 0, ',', '\"', '#', true);
        w.nl("holiday, when observed in " + desiredYear + ", first observed, first proclaimed" + (verbose ? ", authority, rule" : ""));
        for (final HolInfo h : ExportHolidaysToCSV.holidayDelegates) {
            w.put(h.getName());
            w.put(new BigDate(h.when(desiredYear)).toString());
            w.put(h.getFirstYear(1));
            w.put(h.getFirstYear(0));
            if (verbose) {
                w.put(h.getAuthority());
                w.put(h.getRule());
            }
            w.nl();
        }
        System.out.println(w.getLineCount() + " holidays exported to holidaysInYear" + desiredYear + ".csv\nHolidays that don't occur in " + desiredYear + " are excluded.");
        w.close();
    }
    
    private static void findHolidays(final int desiredYear) {
        String[][] definedHolidayClassNames = null;
        try {
            final InputStream fis = ExportHolidaysToCSV.class.getResourceAsStream("Holiday.properties");
            definedHolidayClassNames = Misc.loadProperties(fis);
        }
        catch (IOException oops) {
            System.out.println(oops + " Problem accessing Holiday.properties file.");
            System.exit(1);
        }
        final int length = definedHolidayClassNames[0].length;
        ExportHolidaysToCSV.holidayDelegates = new ArrayList<HolInfo>(length);
        for (int i = 0; i < length; ++i) {
            try {
                final HolInfo h = (HolInfo)Class.forName("com.mindprod.holidays." + definedHolidayClassNames[0][i]).newInstance();
                if (h != null && h.when(desiredYear, false) != Integer.MIN_VALUE) {
                    ExportHolidaysToCSV.holidayDelegates.add(h);
                }
            }
            catch (Exception oops2) {
                System.out.println(oops2 + " Bug in Holiday.properties or class file for " + definedHolidayClassNames[0][i]);
                System.exit(1);
            }
        }
    }
    
    public static void main(final String[] args) throws IOException {
        int desiredYear = Misc.thisYear();
        boolean verbose = false;
        for (final String arg : args) {
            if (arg.equals("verbose")) {
                verbose = true;
            }
            else {
                desiredYear = Integer.parseInt(arg);
            }
        }
        findHolidays(desiredYear);
        Collections.sort(ExportHolidaysToCSV.holidayDelegates, new HolidaySort(desiredYear, false));
        exportHolidays(desiredYear, verbose);
    }
}
