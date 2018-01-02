// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.util;

import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.io.File;
import org.xmodel.log.Log;

public class ChangeFileComment
{
    private static Log A;
    File B;
    
    static {
        ChangeFileComment.A = Log.getLog("org.xmodel.util");
    }
    
    public static void main(final String[] array) throws Exception {
        final File file = new File("/Applications/eclipse342/svn/trunk/client/XModel");
        final String a = A();
        final ChangeFileComment changeFileComment = new ChangeFileComment(file);
        changeFileComment.A(a);
        int n = 0;
        for (final File file2 : changeFileComment.B()) {
            final String[] split = A(file2).split("\\n+");
            for (int i = 0; i < split.length; ++i) {
                split[i] = split[i].trim();
                if (split[i].length() != 0) {
                    if (split[i].length() <= 2 || split[i].charAt(0) != '/' || split[i].charAt(1) != '/') {
                        ++n;
                    }
                }
            }
            System.out.printf("%s -> %d\n", file2, split.length);
        }
        System.out.printf("Total: " + n, new Object[0]);
    }
    
    public ChangeFileComment(final File b) {
        this.B = b;
    }
    
    private void A(final String s) {
        final Pattern compile = Pattern.compile("\\A(/\\*.+?\\*/\\s*).*\\Z", 40);
        final List<File> b = this.B();
        for (int i = 0; i < b.size(); ++i) {
            final File file = b.get(i);
            final String a = A(file);
            if (a != null) {
                final String a2 = A(s, file.getName());
                final Matcher matcher = compile.matcher(a);
                if (matcher.matches()) {
                    final int start = matcher.start(1);
                    final int end = matcher.end(1);
                    final StringBuffer sb = new StringBuffer();
                    if (start > 0) {
                        sb.append(a.substring(0, start));
                    }
                    sb.append(a2);
                    sb.append(a.substring(end));
                    A(file, sb.toString());
                }
                else {
                    A(file, String.valueOf(a2) + a);
                }
            }
        }
    }
    
    private static String A(final File file) {
        try {
            final StringBuffer sb = new StringBuffer();
            final FileReader fileReader = new FileReader(file);
            final char[] array = new char[8192];
            while (fileReader.ready()) {
                sb.append(array, 0, fileReader.read(array));
            }
            fileReader.close();
            return sb.toString();
        }
        catch (IOException ex) {
            ChangeFileComment.A.exception(ex);
            return null;
        }
    }
    
    private static void A(final File file, final String s) {
        try {
            System.out.println(file.getName());
            final FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(s);
            fileWriter.close();
        }
        catch (IOException ex) {
            ChangeFileComment.A.exception(ex);
        }
    }
    
    private List<File> B() {
        final ArrayList<File> list = new ArrayList<File>();
        final Stack<File> stack = new Stack<File>();
        stack.push(this.B);
        while (!stack.empty()) {
            final File[] listFiles = stack.pop().listFiles();
            if (listFiles != null) {
                for (int i = 0; i < listFiles.length; ++i) {
                    if (listFiles[i].isDirectory()) {
                        stack.push(listFiles[i]);
                    }
                    if (listFiles[i].toString().endsWith(".java")) {
                        list.add(listFiles[i]);
                    }
                }
            }
        }
        return list;
    }
    
    private static String A() throws Exception {
        final InputStream resourceAsStream = ChangeFileComment.class.getResourceAsStream("header.txt");
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        final StringBuilder sb = new StringBuilder();
        sb.append("/*");
        sb.append("\n");
        while (bufferedReader.ready()) {
            final String line = bufferedReader.readLine();
            sb.append(" * ");
            sb.append(line);
            sb.append("\n");
        }
        sb.append(" */");
        sb.append("\n");
        bufferedReader.close();
        resourceAsStream.close();
        return sb.toString();
    }
    
    private static String A(final String s, final String s2) {
        return s.replaceFirst("[$]FILE", s2);
    }
}
