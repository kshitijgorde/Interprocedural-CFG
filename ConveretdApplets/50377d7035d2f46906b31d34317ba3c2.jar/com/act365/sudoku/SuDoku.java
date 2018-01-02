// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Component;
import com.act365.awt.Frame;

public class SuDoku extends Frame
{
    SuDoku(final Grid grid) {
        super("Su Doku Solver");
        final GridContainer gc = new GridContainer(grid);
        final ControlContainer control = new ControlContainer(gc, this.getClass());
        final SuDokuContainer suDoku = new SuDokuContainer(gc, control);
        this.add(suDoku);
        this.setSize(suDoku.getBestSize());
    }
    
    public static void main(final String[] args) {
        int boxesAcross = 3;
        int boxesDown = 3;
        boolean standardInput = false;
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("-a")) {
                if (++i < args.length) {
                    try {
                        boxesAcross = Integer.parseInt(args[i]);
                    }
                    catch (NumberFormatException e2) {
                        System.err.println("boxesAcross should be an integer");
                        System.exit(1);
                    }
                }
                else {
                    System.err.println("-a requires an argument");
                    System.exit(1);
                }
            }
            else if (args[i].equals("-d")) {
                if (++i < args.length) {
                    try {
                        boxesDown = Integer.parseInt(args[i]);
                    }
                    catch (NumberFormatException e2) {
                        System.err.println("boxesDown should be an integer");
                        System.exit(1);
                    }
                }
                else {
                    System.err.println("-d requires an argument");
                    System.exit(1);
                }
            }
            else if (args[i].equals("-i")) {
                standardInput = true;
            }
            else {
                System.err.println("Usage: SuDoku [-a boxesAcross] [-d boxesDown]");
                System.exit(1);
            }
        }
        Grid grid = null;
        if (standardInput) {
            final StringBuffer gridText = new StringBuffer();
            final BufferedReader standardInputReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String text;
                while ((text = standardInputReader.readLine()) != null && text.length() != 0) {
                    gridText.append(text);
                    gridText.append('\n');
                }
                grid = new Grid();
                grid.populate(gridText.toString());
            }
            catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(2);
            }
        }
        else {
            grid = new Grid(boxesAcross, boxesDown);
        }
        new SuDoku(grid).show();
    }
}
