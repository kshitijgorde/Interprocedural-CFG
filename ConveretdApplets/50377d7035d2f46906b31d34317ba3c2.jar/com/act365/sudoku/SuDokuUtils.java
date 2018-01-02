// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

import java.util.Date;
import java.text.DateFormat;
import java.util.StringTokenizer;

public class SuDokuUtils
{
    public static final int NUMERIC = 0;
    public static final int ALPHANUMERIC = 1;
    public static final String[] labels;
    public static int defaultFormat;
    
    static {
        labels = new String[] { "Numeric from 1", "Alphanumeric from 0" };
        SuDokuUtils.defaultFormat = 0;
    }
    
    public static String toString(final int[][] data, final int boxesAcross, final int maxDatum, final int format) {
        final int cellsInRow = data.length;
        final int boxesDown = data.length / boxesAcross;
        final StringBuffer sb = new StringBuffer();
        int number = maxDatum;
        int fieldWidth = 1;
        if (format == 0) {
            while ((number /= 10) >= 1) {
                ++fieldWidth;
            }
        }
        final int boxWidth = cellsInRow / boxesAcross * (fieldWidth + 1) + 2;
        for (int i = 0; i < cellsInRow; ++i) {
            if (i > 0 && i % boxesAcross == 0) {
                for (int k = 0; k < (fieldWidth + 1) * cellsInRow + (boxesAcross - 1) * 2; ++k) {
                    if (k % boxWidth == boxWidth - 1) {
                        sb.append('+');
                    }
                    else {
                        sb.append('-');
                    }
                }
                sb.append(" \n");
            }
            for (int j = 0; j < cellsInRow; ++j) {
                if (j > 0 && j % boxesDown == 0) {
                    sb.append(" |");
                }
                int k = 0;
                if (data[i][j] > 0) {
                    int numberWidth = 1;
                    number = data[i][j];
                    if (format == 0) {
                        while ((number /= 10) >= 1) {
                            ++numberWidth;
                        }
                    }
                    while (k < 1 + fieldWidth - numberWidth) {
                        sb.append(' ');
                        ++k;
                    }
                    switch (format) {
                        case 0: {
                            sb.append(data[i][j]);
                            break;
                        }
                        case 1: {
                            if (data[i][j] > 10) {
                                sb.append((char)(65 + data[i][j] - 11));
                                break;
                            }
                            sb.append(data[i][j] - 1);
                            break;
                        }
                    }
                }
                else {
                    sb.append(' ');
                    while (k < fieldWidth) {
                        sb.append('.');
                        ++k;
                    }
                }
            }
            sb.append(" \n");
        }
        return sb.toString();
    }
    
    public static String toString(final int[][] data, final int boxesAcross, final int maxDatum) {
        return toString(data, boxesAcross, maxDatum, SuDokuUtils.defaultFormat);
    }
    
    public static String toString(final int[][] data, final int boxesAcross) {
        return toString(data, boxesAcross, data.length, SuDokuUtils.defaultFormat);
    }
    
    public static void populate(final int[][] data, final String s, final int format) {
        final StringTokenizer st = new StringTokenizer(s, " \t\n\r*|¦-+");
        for (int i = 0; i < data.length; ++i) {
            String token;
            for (int j = 0; j < data[0].length; data[i][j++] = parse(token)) {
                token = st.nextToken();
            }
        }
    }
    
    public static void populate(final int[][] data, final String s) {
        populate(data, s, SuDokuUtils.defaultFormat);
    }
    
    public static int parse(final String s, final int format) {
        int datum = 0;
        switch (format) {
            case 0: {
                try {
                    datum = Integer.parseInt(s);
                }
                catch (NumberFormatException ex) {}
                break;
            }
            case 1: {
                if (s.length() == 1) {
                    final char c = s.charAt(0);
                    if (c >= 'A' && c <= 'Z') {
                        datum = c - 'A' + '\u000b';
                        break;
                    }
                    if (c >= 'a' && c <= 'z') {
                        datum = c - 'a' + '\u000b';
                        break;
                    }
                }
                try {
                    datum = 1 + Integer.parseInt(s);
                }
                catch (NumberFormatException ex2) {}
                break;
            }
        }
        return datum;
    }
    
    public static int parse(final String s) {
        return parse(s, SuDokuUtils.defaultFormat);
    }
    
    public static String toString(final int datum, final int format) {
        if (datum > 0) {
            switch (format) {
                case 0: {
                    return Integer.toString(datum);
                }
                case 1: {
                    if (datum > 10) {
                        return Character.toString((char)(65 + datum - 11));
                    }
                    return Integer.toString(datum - 1);
                }
            }
        }
        return new String();
    }
    
    public static String toString(final int datum) {
        return toString(datum, SuDokuUtils.defaultFormat);
    }
    
    public static String libraryBookHeader(final String className, final int cellsInRow, final int boxesAcross, final String[] featuredGrades) {
        final StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
        sb.append("<sudoku-book>\n");
        sb.append("<note>Generated by ");
        sb.append(className);
        sb.append(" on ");
        sb.append(DateFormat.getDateTimeInstance().format(new Date()));
        sb.append(".</note>\n");
        sb.append("<user>0</user>\n");
        sb.append("<last>000000000000</last>\n");
        sb.append("<checked>000000000000</checked>\n");
        sb.append("<xtra>0</xtra>\n");
        sb.append("<puzzle-type>");
        sb.append((cellsInRow == 9 && boxesAcross == 3) ? '0' : '1');
        sb.append("</puzzle-type>\n");
        sb.append("<cells-in-row>");
        sb.append(cellsInRow);
        sb.append("</cells-in-row>\n");
        sb.append("<boxes-across>");
        sb.append(boxesAcross);
        sb.append("</boxes-across>\n");
        sb.append("<boxes-down>");
        sb.append(cellsInRow / boxesAcross);
        sb.append("</boxes-down>\n");
        for (int i = 0; i < featuredGrades.length; ++i) {
            sb.append("<featuredGrade>");
            sb.append(featuredGrades[i]);
            sb.append("</featuredGrade>\n");
        }
        return sb.toString();
    }
    
    public static String libraryBookFooter() {
        return "</sudoku-book>\n";
    }
}
