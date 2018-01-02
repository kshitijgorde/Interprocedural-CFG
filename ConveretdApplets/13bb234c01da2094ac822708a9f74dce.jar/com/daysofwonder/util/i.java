// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.io.FileInputStream;
import java.io.File;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

public class i extends Properties
{
    private Hashtable a;
    
    public i() {
        this.a = new Hashtable();
    }
    
    public void load(final InputStream inputStream) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s;
        do {
            s = bufferedReader.readLine();
            if (s != null && s.length() > 0 && s.charAt(0) != '#') {
                if (s.charAt(0) == '!') {
                    continue;
                }
                if (s.toLowerCase().trim().startsWith("include")) {
                    String trim;
                    int length;
                    for (trim = s.trim(), length = "include".length(); length < trim.length() && "\"'".indexOf(trim.charAt(length)) == -1; ++length) {}
                    int n2;
                    int n;
                    for (n = (n2 = length + 1); n2 < trim.length() && "\"'".indexOf(trim.charAt(n2)) == -1; ++n2) {}
                    final int n3 = n2;
                    if (n3 == n) {
                        continue;
                    }
                    final File file = new File(this.c(trim.substring(n, n3)));
                    this.a.clear();
                    if (!file.exists()) {
                        continue;
                    }
                    final FileInputStream fileInputStream = new FileInputStream(file);
                    this.load(fileInputStream);
                    fileInputStream.close();
                }
                else {
                    while (this.a(s)) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            line = "";
                        }
                        final String substring = s.substring(0, s.length() - 1);
                        int n4;
                        for (n4 = 0; n4 < line.length() && " \t\n\f\r".indexOf(line.charAt(n4)) != -1; ++n4) {}
                        s = substring + line.substring(n4, line.length());
                    }
                    int n5;
                    for (n5 = 0; n5 < s.length() && " \t\n\f\r".indexOf(s.charAt(n5)) != -1; ++n5) {}
                    if (n5 == s.length()) {
                        continue;
                    }
                    final int n6 = n5;
                    while (n5 < s.length() && "=:".indexOf(s.charAt(n5)) == -1) {
                        ++n5;
                    }
                    final int n7 = n5;
                    int n8;
                    for (n8 = n7 - 1; n8 >= 0 && " \t\n\f\r".indexOf(s.charAt(n8)) != -1; --n8) {}
                    final int n9 = n8 + 1;
                    int n10;
                    for (n10 = n7 + 1; n10 < s.length() && " \t\n\f\r".indexOf(s.charAt(n10)) != -1; ++n10) {}
                    final int n11 = n10;
                    int n12;
                    for (n12 = s.length() - 1; n12 >= 0 && " \t\n\f\r".indexOf(s.charAt(n12)) != -1; --n12) {}
                    final int n13 = n12 + 1;
                    if (n13 <= n11) {
                        continue;
                    }
                    this.setProperty(this.b(s.substring(n6, n9)), this.b((n7 < s.length()) ? s.substring(n11, n13) : ""));
                }
            }
        } while (s != null);
    }
    
    private boolean a(final String s) {
        return s != null && s.trim().endsWith("\\");
    }
    
    private String b(final String s) {
        final StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i < s.length()) {
            final char char1 = s.charAt(i++);
            if (char1 == '\\') {
                final char char2 = s.charAt(i++);
                if (char2 == '\\') {
                    sb.append('\\');
                }
                else if (char2 == 'u') {
                    int n = 0;
                    for (int j = 0; j < 4; ++j) {
                        final char char3 = s.charAt(i++);
                        char c;
                        if (char3 >= '0' && char3 < '9') {
                            c = (char)(char3 - '0');
                        }
                        else if (char3 >= 'a' && char3 <= 'f') {
                            c = (char)('\n' + char3 - 'a');
                        }
                        else {
                            if (char3 < 'A' || char3 > 'F') {
                                throw new IllegalArgumentException("Malformed string");
                            }
                            c = (char)('\n' + char3 - 'A');
                        }
                        n = (n << 4) + c;
                    }
                    sb.append((char)n);
                }
                else if (char2 == 't') {
                    sb.append('\t');
                }
                else if (char2 == 'n') {
                    sb.append('\n');
                }
                else if (char2 == 'f') {
                    sb.append('\f');
                }
                else if (char2 == 'r') {
                    sb.append('\r');
                }
                else {
                    sb.append(char2);
                }
            }
            else if (char1 == '$') {
                if (s.charAt(i++) != '{') {
                    continue;
                }
                final StringBuffer sb2 = new StringBuffer();
                sb2.append("${");
                char char4;
                while ((char4 = s.charAt(i++)) != '}') {
                    sb2.append(char4);
                }
                sb2.append("}");
                this.a.clear();
                sb.append(this.c(sb2.toString()));
                this.a.clear();
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    private String c(final String s) {
        String s2 = s;
        final int index = s.indexOf("${");
        if (index != -1) {
            final int n = index + 2;
            final int index2 = s.indexOf("}", index);
            final int n2 = index2 + 1;
            if (index2 != -1) {
                final String substring = s.substring(n, index2);
                if (!this.a.containsKey(substring)) {
                    final String property = this.getProperty(substring);
                    final StringBuffer sb = new StringBuffer(s2);
                    String s3;
                    int n3;
                    if (property != null) {
                        this.a.put(substring, null);
                        sb.replace(index, n2, property);
                        s3 = sb.toString();
                        n3 = s3.indexOf("${");
                    }
                    else {
                        final StringBuffer sb2 = new StringBuffer();
                        sb2.append("${");
                        sb2.append(substring);
                        sb2.append("}");
                        sb.replace(index, n2, sb2.toString());
                        s3 = sb.toString();
                        n3 = s3.indexOf("${", index + 1);
                    }
                    if (n3 != -1) {
                        return this.c(s3);
                    }
                    s2 = s3;
                }
            }
        }
        return s2;
    }
}
