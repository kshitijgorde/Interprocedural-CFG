import javax.servlet.GenericServlet;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ChartRequestHandler extends HttpServlet
{
    public void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String[] parameterValues = ((ServletRequest)httpServletRequest).getParameterValues("type");
        final String[] parameterValues2 = ((ServletRequest)httpServletRequest).getParameterValues("code");
        String s;
        if (parameterValues != null) {
            s = parameterValues[0];
        }
        else {
            s = null;
        }
        String s2;
        if (parameterValues2 != null) {
            s2 = parameterValues2[0];
        }
        else {
            s2 = null;
        }
        ((ServletResponse)httpServletResponse).setContentType("application/x-www-form-urlencoded");
        String s3 = "";
        if (s.equals("item")) {
            s3 = new String("ItemData");
        }
        if (s.equals("index")) {
            s3 = new String("IndexData");
        }
        final String s4 = new String("c:\\consensus\\" + s3 + "\\" + s2 + ".txt");
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream((OutputStream)((ServletResponse)httpServletResponse).getOutputStream());
        final DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)((ServletResponse)httpServletResponse).getOutputStream());
        new OutputStreamWriter(System.out).getEncoding();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(s4)));
            try {
                final int int1 = Integer.parseInt(bufferedReader.readLine().trim());
                dataOutputStream.writeInt(int1);
                for (int i = 0; i < int1; ++i) {
                    if (s.equals("item")) {
                        final String trim = bufferedReader.readLine().trim();
                        final double doubleValue = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue2 = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue3 = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue4 = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue5 = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue6 = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue7 = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue8 = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue9 = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue10 = Double.valueOf(bufferedReader.readLine());
                        final int int2 = Integer.parseInt(bufferedReader.readLine().trim());
                        final Vector<au> vector = new Vector<au>();
                        for (int j = 0; j < int2; ++j) {
                            final int int3 = Integer.parseInt(bufferedReader.readLine().trim());
                            final String trim2 = bufferedReader.readLine().trim();
                            String s5 = bufferedReader.readLine().trim();
                            final int int4 = Integer.parseInt(s5);
                            String trim3 = bufferedReader.readLine().trim();
                            if (trim3.equals("NULL")) {
                                trim3 = "";
                            }
                            int int5 = 0;
                            String trim4 = "";
                            int int6 = 0;
                            String trim5 = "";
                            int int7 = 0;
                            String trim6 = "";
                            int int8 = 0;
                            String trim7 = "";
                            int int9 = 0;
                            try {
                                s5 = bufferedReader.readLine().trim();
                                if (s5.compareTo("NULL") != 0) {
                                    int5 = Integer.parseInt(s5);
                                }
                                else {
                                    int5 = 0;
                                }
                                trim4 = bufferedReader.readLine().trim();
                                if (trim4.equals("NULL")) {
                                    trim4 = "";
                                }
                                s5 = bufferedReader.readLine();
                                if (s5.compareTo("NULL") != 0) {
                                    int6 = Integer.parseInt(s5);
                                }
                                else {
                                    int6 = 0;
                                }
                                trim5 = bufferedReader.readLine().trim();
                                s5 = bufferedReader.readLine();
                                if (s5.compareTo("NULL") != 0) {
                                    int7 = Integer.parseInt(s5);
                                }
                                else {
                                    int7 = 0;
                                }
                                trim6 = bufferedReader.readLine().trim();
                                s5 = bufferedReader.readLine();
                                if (s5.compareTo("NULL") != 0) {
                                    int8 = Integer.parseInt(s5);
                                }
                                else {
                                    int8 = 0;
                                }
                                trim7 = bufferedReader.readLine().trim();
                                s5 = bufferedReader.readLine();
                                if (s5.compareTo("NULL") != 0) {
                                    int9 = Integer.parseInt(s5);
                                }
                                else {
                                    int9 = 0;
                                }
                            }
                            catch (NumberFormatException ex) {
                                ((GenericServlet)this).getServletContext().log(trim + " : " + s5, (Throwable)ex);
                            }
                            String trim8 = bufferedReader.readLine().trim();
                            if (trim8.equals("NULL")) {
                                trim8 = "";
                            }
                            String trim9 = bufferedReader.readLine().trim();
                            if (trim9.equals("NULL")) {
                                trim9 = "\ucca8\ubd80\ud30c\uc77c\uc5c6\uc74c";
                            }
                            int n = 0;
                            int n2 = 0;
                            int n3 = 0;
                            if (trim5.compareTo("2011") == 0) {
                                n = int7;
                            }
                            else if (trim6.compareTo("2011") == 0) {
                                n = int8;
                            }
                            else if (trim7.compareTo("2011") == 0) {
                                n = int9;
                            }
                            if (trim5.compareTo("2012") == 0) {
                                n2 = int7;
                            }
                            else if (trim6.compareTo("2012") == 0) {
                                n2 = int8;
                            }
                            else if (trim7.compareTo("2012") == 0) {
                                n2 = int9;
                            }
                            if (trim5.compareTo("2013") == 0) {
                                n3 = int7;
                            }
                            else if (trim6.compareTo("2013") == 0) {
                                n3 = int8;
                            }
                            else if (trim7.compareTo("2013") == 0) {
                                n3 = int9;
                            }
                            final String s6 = new String("");
                            String s7 = null;
                            switch (int4) {
                                case 0: {
                                    s7 = new String("\ub300\uc6b0\uc99d\uad8c");
                                    break;
                                }
                                case 1: {
                                    s7 = new String("\uc0bc\uc131\uc99d\uad8c");
                                    break;
                                }
                                case 2: {
                                    s7 = new String("\uc5d8\uc9c0\uc99d\uad8c");
                                    break;
                                }
                                case 3: {
                                    s7 = new String("\ub300\uc2e0\uc99d\uad8c");
                                    break;
                                }
                                case 4: {
                                    s7 = new String("\ud604\ub300\uc99d\uad8c");
                                    break;
                                }
                                case 5: {
                                    s7 = new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c");
                                    break;
                                }
                                case 6: {
                                    s7 = new String("\ub3d9\uc6d0\uc99d\uad8c");
                                    break;
                                }
                                case 7: {
                                    s7 = new String("\ub3d9\uc591\uc99d\uad8c");
                                    break;
                                }
                                case 19: {
                                    s7 = new String("\ub3d9\ubd80\uc99d\uad8c");
                                    break;
                                }
                                case 8: {
                                    s7 = new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c");
                                    break;
                                }
                                case 9: {
                                    s7 = new String("\ud55c\ud654\uc99d\uad8c");
                                    break;
                                }
                                case 10: {
                                    s7 = new String("\uad50\ubcf4\uc99d\uad8c");
                                    break;
                                }
                                case 11: {
                                    s7 = new String("\uba54\ub9ac\uce20\uc99d\uad8c");
                                    break;
                                }
                                case 14: {
                                    s7 = new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c");
                                    break;
                                }
                                case 15: {
                                    s7 = new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c");
                                    break;
                                }
                                case 16: {
                                    s7 = new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c");
                                    break;
                                }
                                case 12: {
                                    s7 = new String("\uc2e0\uc601\uc99d\uad8c");
                                    break;
                                }
                                case 13: {
                                    s7 = new String("NH\uc99d\uad8c");
                                    break;
                                }
                                case 17: {
                                    s7 = new String("\uc6b0\ub9ac\uc99d\uad8c");
                                    break;
                                }
                                case 18: {
                                    s7 = new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c");
                                    break;
                                }
                                case 20: {
                                    s7 = new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c");
                                    break;
                                }
                                case 49: {
                                    s7 = new String("\uba54\ub9b4\ub9b0\uce58");
                                    break;
                                }
                                case 50: {
                                    s7 = new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4");
                                    break;
                                }
                                case 51: {
                                    s7 = new String("JP\ubaa8\uac74");
                                    break;
                                }
                                case 52: {
                                    s7 = new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac");
                                    break;
                                }
                                case 53: {
                                    s7 = new String("UBS");
                                    break;
                                }
                                case 54: {
                                    s7 = new String("CSFB");
                                    break;
                                }
                                case 55: {
                                    s7 = new String("\ub9e5\ucffc\ub9ac");
                                    break;
                                }
                                case 56: {
                                    s7 = new String("ABN\uc554\ub85c");
                                    break;
                                }
                                case 57: {
                                    s7 = new String("HSBC");
                                    break;
                                }
                                case 58: {
                                    s7 = new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124");
                                    break;
                                }
                                case 59: {
                                    s7 = new String("CGM");
                                    break;
                                }
                                case 60: {
                                    s7 = new String("\ub2e4\uc774\uc640");
                                    break;
                                }
                                case 61: {
                                    s7 = new String("\ub178\ubb34\ub77c");
                                    break;
                                }
                                case 62: {
                                    continue;
                                }
                                case 63: {
                                    s7 = new String("\ub3c4\uc774\uce58");
                                    break;
                                }
                                case 65: {
                                    s7 = new String("\ud30c\ub9ac\ubc14");
                                    break;
                                }
                                case 66: {
                                    s7 = new String("RBS");
                                    break;
                                }
                                case 21: {
                                    s7 = new String("KB\uc99d\uad8c");
                                    break;
                                }
                                default: {
                                    s7 = "";
                                    break;
                                }
                            }
                            if (s7.compareTo("") != 0) {
                                vector.addElement(new au(int3, trim2, s7, trim3, int5, trim4, int6, n, n2, n3, trim8, trim9));
                            }
                        }
                        final at at = new at(trim, doubleValue, doubleValue4, doubleValue3, doubleValue2, doubleValue5, doubleValue6, doubleValue7, doubleValue8, doubleValue9, doubleValue10, vector);
                        try {
                            objectOutputStream.writeObject(at);
                            objectOutputStream.flush();
                            continue;
                        }
                        catch (IOException ex2) {
                            ((GenericServlet)this).getServletContext().log(" error : statement error", (Throwable)ex2);
                            continue;
                        }
                    }
                    if (s.equals("index")) {
                        final String trim10 = bufferedReader.readLine().trim();
                        final double doubleValue11 = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue12 = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue13 = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue14 = Double.valueOf(bufferedReader.readLine());
                        final double doubleValue15 = Double.valueOf(bufferedReader.readLine());
                        final int int10 = Integer.parseInt(bufferedReader.readLine().trim());
                        final String s8 = s;
                        final boolean b = false;
                        final String s9 = "";
                        final boolean b2 = false;
                        final boolean b3 = false;
                        final boolean b4 = false;
                        final boolean b5 = false;
                        final Vector<au> vector2 = new Vector<au>();
                        for (int k = 0; k < int10; ++k) {
                            final int int11 = Integer.parseInt(bufferedReader.readLine().trim());
                            final int int12 = Integer.parseInt(bufferedReader.readLine().trim());
                            String trim11 = bufferedReader.readLine().trim();
                            if (trim11.equals("NULL")) {
                                trim11 = "";
                            }
                            String trim12 = bufferedReader.readLine().trim();
                            if (trim12.equals("NULL")) {
                                trim12 = "\ucca8\ubd80\ud30c\uc77c\uc5c6\uc74c";
                            }
                            String trim13 = bufferedReader.readLine().trim();
                            if (trim13.equals("NULL")) {
                                trim13 = "";
                            }
                            final String s10 = new String("");
                            String s11 = null;
                            switch (int12) {
                                case 0: {
                                    s11 = new String("\ub300\uc6b0\uc99d\uad8c");
                                    break;
                                }
                                case 1: {
                                    s11 = new String("\uc0bc\uc131\uc99d\uad8c");
                                    break;
                                }
                                case 2: {
                                    s11 = new String("\uc5d8\uc9c0\uc99d\uad8c");
                                    break;
                                }
                                case 3: {
                                    s11 = new String("\ub300\uc2e0\uc99d\uad8c");
                                    break;
                                }
                                case 4: {
                                    s11 = new String("\ud604\ub300\uc99d\uad8c");
                                    break;
                                }
                                case 5: {
                                    s11 = new String("\uad7f\ubaa8\ub2dd\uc99d\uad8c");
                                    break;
                                }
                                case 6: {
                                    s11 = new String("\ub3d9\uc6d0\uc99d\uad8c");
                                    break;
                                }
                                case 7: {
                                    s11 = new String("\ub3d9\uc591\uc99d\uad8c");
                                    break;
                                }
                                case 19: {
                                    s11 = new String("\ub3d9\ubd80\uc99d\uad8c");
                                    break;
                                }
                                case 8: {
                                    s11 = new String("\uc5d0\uc2a4\ucf00\uc774\uc99d\uad8c");
                                    break;
                                }
                                case 9: {
                                    s11 = new String("\ud55c\ud654\uc99d\uad8c");
                                    break;
                                }
                                case 10: {
                                    s11 = new String("\uad50\ubcf4\uc99d\uad8c");
                                    break;
                                }
                                case 11: {
                                    s11 = new String("\uba54\ub9ac\uce20\uc99d\uad8c");
                                    break;
                                }
                                case 14: {
                                    s11 = new String("\ubbf8\ub798\uc5d0\uc14b\uc99d\uad8c");
                                    break;
                                }
                                case 15: {
                                    s11 = new String("\ud55c\uad6d\ud22c\uc790\uc2e0\ud0c1\uc99d\uad8c");
                                    break;
                                }
                                case 16: {
                                    s11 = new String("\uc720\uc9c4\ud22c\uc790\uc99d\uad8c");
                                    break;
                                }
                                case 12: {
                                    s11 = new String("\uc2e0\uc601\uc99d\uad8c");
                                    break;
                                }
                                case 13: {
                                    s11 = new String("NH\uc99d\uad8c");
                                    break;
                                }
                                case 17: {
                                    s11 = new String("\uc6b0\ub9ac\uc99d\uad8c");
                                    break;
                                }
                                case 18: {
                                    s11 = new String("\uc81c\uc77c\ud22c\uc790\uc99d\uad8c");
                                    break;
                                }
                                case 20: {
                                    s11 = new String("\uc774\ud2b8\ub808\uc774\ub4dc\uc99d\uad8c");
                                    break;
                                }
                                case 21: {
                                    s11 = new String("KB\uc99d\uad8c");
                                    break;
                                }
                                case 49: {
                                    s11 = new String("\uba54\ub9b4\ub9b0\uce58");
                                    break;
                                }
                                case 50: {
                                    s11 = new String("\uace8\ub4dc\ub9cc\uc0ad\uc2a4");
                                    break;
                                }
                                case 51: {
                                    s11 = new String("JP\ubaa8\uac74");
                                    break;
                                }
                                case 52: {
                                    s11 = new String("\ubaa8\uac74\uc2a4\ud0e0\ub9ac");
                                    break;
                                }
                                case 53: {
                                    s11 = new String("UBS");
                                    break;
                                }
                                case 54: {
                                    s11 = new String("CSFB");
                                    break;
                                }
                                case 55: {
                                    s11 = new String("\ub9e5\ucffc\ub9ac");
                                    break;
                                }
                                case 56: {
                                    s11 = new String("ABN\uc554\ub85c");
                                    break;
                                }
                                case 57: {
                                    s11 = new String("HSBC");
                                    break;
                                }
                                case 58: {
                                    s11 = new String("\ud06c\ub808\ub514\ub9ac\uc694\ub124");
                                    break;
                                }
                                case 59: {
                                    s11 = new String("CGM");
                                    break;
                                }
                                case 60: {
                                    s11 = new String("\ub2e4\uc774\uc640");
                                    break;
                                }
                                case 61: {
                                    s11 = new String("\ub178\ubb34\ub77c");
                                    break;
                                }
                                case 62: {
                                    continue;
                                }
                                case 63: {
                                    s11 = new String("\ub3c4\uc774\uce58");
                                    break;
                                }
                                case 65: {
                                    s11 = new String("\ud30c\ub9ac\ubc14");
                                    break;
                                }
                                case 66: {
                                    s11 = new String("RBS");
                                    break;
                                }
                                default: {
                                    s11 = "";
                                    break;
                                }
                            }
                            if (s11.compareTo("") != 0) {
                                vector2.addElement(new au(int11, s8, s11, trim11, (int)(b ? 1 : 0), s9, (int)(b2 ? 1 : 0), (int)(b3 ? 1 : 0), (int)(b4 ? 1 : 0), (int)(b5 ? 1 : 0), trim13, trim12));
                            }
                        }
                        final at at2 = new at(trim10, doubleValue11, doubleValue14, doubleValue13, doubleValue12, doubleValue15, 0.0, 0.0, 0.0, 0.0, 0.0, vector2);
                        try {
                            objectOutputStream.writeObject(at2);
                            objectOutputStream.flush();
                        }
                        catch (IOException ex3) {
                            ((GenericServlet)this).getServletContext().log(" error : statement error", (Throwable)ex3);
                        }
                    }
                }
            }
            catch (IOException ex4) {
                System.exit(1);
            }
            finally {
                bufferedReader.close();
            }
        }
        catch (FileNotFoundException ex5) {
            dataOutputStream.writeInt(-1);
        }
        finally {
            dataOutputStream.close();
            objectOutputStream.close();
        }
    }
    
    private String _mthif(final String s) {
        try {
            if (s == null || s.equals("")) {
                return new String("");
            }
            return new String(s.getBytes("KSC5601"), "8859_1");
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
    private String a(final String s) {
        try {
            if (s == null || s.equals("")) {
                return new String("");
            }
            return new String(s.getBytes("8859_1"), "KSC5601");
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
}
