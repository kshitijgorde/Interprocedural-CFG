// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RegexTest
{
    public static void main(final String[] args) {
        while (true) {
            System.out.print("Enter Regex  > ");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String regex;
            try {
                regex = in.readLine();
            }
            catch (IOException ioEx) {
                System.err.println(ioEx.getMessage());
                break;
            }
            regex = regex.trim();
            System.out.print("Enter String > ");
            in = new BufferedReader(new InputStreamReader(System.in));
            String input;
            try {
                input = in.readLine();
            }
            catch (IOException ioEx) {
                System.err.println(ioEx.getMessage());
                break;
            }
            input = input.trim();
            if (regex.length() > 0 && input.length() > 0) {
                final Pattern p = Pattern.compile(regex);
                final Matcher m = p.matcher(input);
                if (input.matches(regex)) {
                    System.out.println("            >> Match. :D ");
                }
                else if (m.find()) {
                    System.out.println("            >> Found. :) ");
                }
                else {
                    System.out.println("            >> Uh-oh. :( ");
                }
            }
        }
    }
}
