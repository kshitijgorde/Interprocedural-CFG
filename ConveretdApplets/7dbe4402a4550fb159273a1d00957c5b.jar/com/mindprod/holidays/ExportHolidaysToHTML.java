// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import java.io.InputStream;
import com.mindprod.common11.Misc;
import java.io.IOException;
import java.util.Iterator;
import com.mindprod.entities.EntifyStrings;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import com.mindprod.common11.BigDate;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public final class ExportHolidaysToHTML
{
    private static final int thisYear;
    private static final String EXPORT_TO = "E:/mindprod/jgloss/include/holidays.html";
    private static HolInfo[] holidayDelegates;
    private static ArrayList<HolInfo> sortedHolidays;
    
    private static void exportHolidays() throws IOException {
        ExportHolidaysToHTML.sortedHolidays = new ArrayList<HolInfo>(ExportHolidaysToHTML.holidayDelegates.length);
        for (final HolInfo h : ExportHolidaysToHTML.holidayDelegates) {
            if (h != null && h.when(ExportHolidaysToHTML.thisYear, false) != Integer.MIN_VALUE) {
                ExportHolidaysToHTML.sortedHolidays.add(h);
            }
        }
        Collections.sort(ExportHolidaysToHTML.sortedHolidays, new HolidaySort(ExportHolidaysToHTML.thisYear, false));
        final int[] holidaysInMonth = new int[12];
        for (final HolInfo h2 : ExportHolidaysToHTML.sortedHolidays) {
            final int[] array = holidaysInMonth;
            final int n = new BigDate(h2.when(ExportHolidaysToHTML.thisYear, false)).getMM() - 1;
            ++array[n];
        }
        int springSummerRows = 0;
        for (int i = 0; i < 6; ++i) {
            if (holidaysInMonth[i] > springSummerRows) {
                springSummerRows = holidaysInMonth[i];
            }
        }
        int fallWinterRows = 0;
        for (int j = 6; j < 12; ++j) {
            if (holidaysInMonth[j] > fallWinterRows) {
                fallWinterRows = holidaysInMonth[j];
            }
        }
        final String[][] matrix = new String[Math.max(springSummerRows, fallWinterRows)][12];
        int row = 0;
        int prevMonth = -1;
        for (final HolInfo h3 : ExportHolidaysToHTML.sortedHolidays) {
            final int month = new BigDate(h3.when(ExportHolidaysToHTML.thisYear, false)).getMM();
            if (month != prevMonth) {
                row = 0;
                prevMonth = month;
            }
            matrix[row++][month - 1] = h3.getName();
        }
        final FileOutputStream fos = new FileOutputStream("E:/mindprod/jgloss/include/holidays.html", false);
        final OutputStreamWriter osw = new OutputStreamWriter(fos);
        final BufferedWriter bw = new BufferedWriter(osw, 4096);
        final PrintWriter prw = new PrintWriter(bw, false);
        prw.println("<table class=\"borderless\" summary=\"holidays\">\n<!-- DO NOT EDIT. generated by com.mindprod.holidays.ExportHolidaysToHTML program. --><thead>\n<tr>\n<th>January</th>\n<th>February</th>\n<th>March</th>\n<th>April</th>\n<th>May</th>\n<th>June</th>\n</tr>\n</thead>\n<tbody>");
        for (int row2 = 0; row2 < springSummerRows; ++row2) {
            prw.println("<tr><!-- DO NOT EDIT. generated by com.mindprod.holidays.ExportHolidaysToHTML program. -->");
            for (int month2 = 0; month2 < 6; ++month2) {
                if (matrix[row2][month2] == null) {
                    prw.println("<td>&nbsp;</td>");
                }
                else {
                    prw.print("<td>");
                    prw.print(EntifyStrings.entifyHTML(matrix[row2][month2]));
                    prw.println("</td>");
                }
            }
            prw.println("</tr>");
        }
        prw.println("<tr>\n<!-- DO NOT EDIT. generated by com.mindprod.holidays.ExportHolidaysToHTML program. --><th>July</th>\n<th>August</th>\n<th>September</th>\n<th>October</th>\n<th>November</th>\n<th>December</th>\n</tr>");
        for (int row2 = 0; row2 < fallWinterRows; ++row2) {
            prw.println("<tr><!-- DO NOT EDIT. generated by com.mindprod.holidays.ExportHolidaysToHTML program. -->");
            for (int month2 = 6; month2 < 12; ++month2) {
                if (matrix[row2][month2] == null) {
                    prw.println("<td>&nbsp;</td>");
                }
                else {
                    prw.print("<td>");
                    prw.print(EntifyStrings.entifyHTML(matrix[row2][month2]));
                    prw.println("</td>");
                }
            }
            prw.println("</tr>");
        }
        prw.println("</tbody>\n<!-- DO NOT EDIT. generated by com.mindprod.holidays.ExportHolidaysToHTML program. --></table>");
        prw.close();
    }
    
    private static void findHolidays() {
        String[][] result = null;
        try {
            final InputStream fis = ExportHolidaysToHTML.class.getResourceAsStream("Holiday.properties");
            result = Misc.loadProperties(fis);
        }
        catch (IOException oops) {
            System.out.println(oops + " Problem accessing Holiday.properties file.");
            System.exit(1);
        }
        final int length = result[0].length;
        int j = 0;
        ExportHolidaysToHTML.holidayDelegates = new HolInfo[length];
        for (int i = 0; i < length; ++i) {
            try {
                if (result[1][i].equalsIgnoreCase("yes")) {
                    ExportHolidaysToHTML.holidayDelegates[j++] = (HolInfo)Class.forName("com.mindprod.holidays." + result[0][i]).newInstance();
                }
            }
            catch (Exception oops2) {
                System.out.println(oops2 + " Bug in Holiday.properties or class file for " + result[0][i]);
                System.exit(1);
            }
        }
    }
    
    public static void main(final String[] args) throws IOException {
        findHolidays();
        exportHolidays();
    }
    
    static {
        thisYear = Misc.thisYear();
    }
}