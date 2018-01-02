// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.comp;

import org.w3c.dom.Document;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import edu.hws.eck.umb.MandelbrotSettings;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.awt.Dimension;
import java.math.BigDecimal;

public class MandelbrotCL
{
    private static final int HP_CUTOFF_EXP = 15;
    private static final double HP_CUTOFF = 1.0E-15;
    private static final BigDecimal TWO;
    private static final BigDecimal TEN;
    private static BigDecimal xmin;
    private static BigDecimal xmax;
    private static BigDecimal ymin;
    private static BigDecimal ymax;
    
    public static void main(final String[] array) {
        Dimension dimension = null;
        String s = "PNG";
        boolean b = true;
        boolean b2 = false;
        final TaskManager taskManager = new TaskManager();
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equalsIgnoreCase("-onepass")) {
                b = false;
                System.out.println("Second pass for subpixel sampling DISABLED.");
            }
            else if (array[i].equalsIgnoreCase("-twopass")) {
                b = true;
                System.out.println("Second pass for subpixel sampling ENABLED.");
            }
            else if (array[i].equalsIgnoreCase("-size") || array[i].equalsIgnoreCase("-g")) {
                if (i == array.length - 1) {
                    System.out.println("Missing value for " + array[i] + "; IGNORED.");
                }
                else {
                    ++i;
                    final String s2 = array[i];
                    String[] array2 = s2.split("x");
                    if (array2.length < 2) {
                        array2 = s2.split("X");
                    }
                    try {
                        final int int1 = Integer.parseInt(array2[0]);
                        final int int2 = Integer.parseInt(array2[1]);
                        if (int1 <= 0 || int2 <= 0) {
                            throw new Exception();
                        }
                        dimension = new Dimension(int1, int2);
                        System.out.println("Image size set to " + int1 + "x" + int2 + ".");
                    }
                    catch (Exception ex) {
                        System.out.println("Bad value for " + array[i] + "; IGNORED.");
                    }
                }
            }
            else if (array[i].equalsIgnoreCase("-format")) {
                if (i == array.length - 1) {
                    System.out.println("Missing value for -format; IGNORED.");
                }
                else {
                    ++i;
                    s = array[i];
                    System.out.println("Image format set to " + s + ".");
                }
            }
            else if (array[i].equalsIgnoreCase("-net")) {
                if (i == array.length - 1) {
                    System.out.println("Missing value for -net; IGNORED.");
                }
                else {
                    ++i;
                    taskManager.setNetworkingEnabled(true);
                    System.out.println("Enabling network.");
                    final String[] split = array[i].split(",");
                    for (int j = 0; j < split.length; ++j) {
                        final String[] split2 = split[j].split(":");
                        int int3;
                        if (split2.length == 1) {
                            int3 = 17071;
                        }
                        else {
                            try {
                                int3 = Integer.parseInt(split2[1].trim());
                            }
                            catch (NumberFormatException ex2) {
                                System.out.println("Illegal port number " + split2[1]);
                                continue;
                            }
                        }
                        System.out.println("Adding network worker " + split2[0] + ":" + int3);
                        taskManager.addNetworkWorker(split2[0], int3);
                    }
                }
            }
            else {
                processFile(array[i], dimension, s, b, taskManager);
                b2 = true;
            }
        }
        if (!b2) {
            System.out.println("No files specified on command line!");
        }
    }
    
    private static void processFile(final String s, Dimension dimension, final String s2, final boolean b, final TaskManager taskManager) {
        final File f = new File(s);
        Document parse;
        try {
            parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        }
        catch (IOException ex2) {
            System.out.println("Can't read file " + s + "; IGNORED");
            return;
        }
        catch (SAXException ex3) {
            System.out.println("File " + s + " is not XML; IGNORED");
            return;
        }
        catch (ParserConfigurationException ex4) {
            System.out.println("Can't read an XML file!!");
            return;
        }
        MandelbrotSettings fromXML;
        try {
            fromXML = MandelbrotSettings.createFromXML(parse.getDocumentElement());
        }
        catch (IOException ex5) {
            System.out.println("File " + s + " does not contain Mandelbrot Settings; IGNORED");
            return;
        }
        if (dimension == null) {
            dimension = new Dimension(800, 600);
        }
        final BufferedImage bufferedImage = new BufferedImage(dimension.width, dimension.height, 1);
        final int[][] array = new int[bufferedImage.getHeight()][bufferedImage.getWidth() * 2];
        int[][] array2 = null;
        if (b) {
            array2 = new int[bufferedImage.getHeight() + 1][bufferedImage.getWidth() + 1];
        }
        final TaskManager.Job job = taskManager.createJob();
        System.out.println("Using " + taskManager.getThreadPoolSize() + " threads");
        final int height = bufferedImage.getHeight();
        final int width = bufferedImage.getWidth();
        MandelbrotCL.xmin = fromXML.getLimits()[0];
        MandelbrotCL.xmax = fromXML.getLimits()[1];
        MandelbrotCL.ymin = fromXML.getLimits()[2];
        MandelbrotCL.ymax = fromXML.getLimits()[3];
        checkAspect(bufferedImage);
        final BigDecimal divide = MandelbrotCL.ymax.subtract(MandelbrotCL.ymin).setScale(2 * MandelbrotCL.ymax.scale()).divide(new BigDecimal(height - 1), MandelbrotCL.ymax.scale(), 6);
        System.out.println("dy is " + divide);
        final boolean b2 = fromXML.isHighPrecisionEnabled() && Math.abs(divide.doubleValue()) < 1.0E-15;
        final int maxIterations = fromXML.getMaxIterations();
        for (int i = 0; i < height; ++i) {
            job.add(new MandelbrotTask(i, MandelbrotCL.xmin, MandelbrotCL.xmax, MandelbrotCL.ymax.subtract(divide.multiply(new BigDecimal(i))), width, maxIterations, b2));
        }
        job.close();
        System.out.println("Total Memory Available: " + Runtime.getRuntime().totalMemory() / 1000000.0 + " meg");
        System.out.println("Free Memory: " + Runtime.getRuntime().freeMemory() / 1000000.0 + " meg");
        System.out.println("Computing data for file " + s + " ... ");
        if (b) {
            System.out.println("\nComputing first pass.");
        }
        System.out.flush();
        final int[][] array3 = new int[bufferedImage.getHeight()][];
        int n = 0;
        boolean await;
        do {
            await = job.await(300000);
            final MandelbrotTask[] finishedTasks = job.finishedTasks();
            if (finishedTasks.length > 0) {
                for (final MandelbrotTask mandelbrotTask : finishedTasks) {
                    array3[mandelbrotTask.getRowNumber()] = mandelbrotTask.getResults();
                }
                n += finishedTasks.length;
                System.out.println(n + " rows of " + height + " completed.");
            }
        } while (!await);
        if (b) {
            System.out.println("\nDone first pass.  Starting second pass...");
            Runtime.getRuntime().gc();
            System.out.println("Free Memory: " + Runtime.getRuntime().freeMemory() / 1000000.0 + " meg");
            System.out.flush();
            final TaskManager.Job job2 = taskManager.createJob();
            final int n2 = bufferedImage.getHeight() + 1;
            final int n3 = bufferedImage.getWidth() + 1;
            final int[][] array5 = new int[n2][];
            final BigDecimal divide2 = divide.divide(MandelbrotCL.TWO, MandelbrotCL.ymax.scale(), 6);
            final BigDecimal subtract = MandelbrotCL.xmin.subtract(divide2);
            final BigDecimal add = MandelbrotCL.xmax.add(divide2);
            final BigDecimal add2 = MandelbrotCL.ymax.add(divide2);
            for (int k = 0; k < n2; ++k) {
                job2.add(new MandelbrotTask(k, subtract, add, add2.subtract(divide.multiply(new BigDecimal(k))), n3, maxIterations, b2));
            }
            job2.close();
            array2 = new int[bufferedImage.getHeight() + 1][];
            boolean await2;
            do {
                await2 = job2.await(300000);
                final MandelbrotTask[] finishedTasks2 = job2.finishedTasks();
                if (finishedTasks2.length > 0) {
                    for (final MandelbrotTask mandelbrotTask2 : finishedTasks2) {
                        array2[mandelbrotTask2.getRowNumber()] = mandelbrotTask2.getResults();
                    }
                    n += finishedTasks2.length;
                    System.out.println(n + " rows of " + n2 + " completed for second pass.");
                }
            } while (!await2);
        }
        final int rgb = fromXML.getMandelbrotColor().getRGB();
        int length3 = fromXML.getPaletteMapping().getLength();
        if (length3 == 0) {
            length3 = maxIterations;
        }
        final int[] rgBs = fromXML.getPalette().makeRGBs(length3, fromXML.getPaletteMapping().getOffset());
        if (b) {
            final float[][] array7 = new float[rgBs.length][];
            for (int n4 = 0; n4 < rgBs.length; ++n4) {
                array7[n4] = new Color(rgBs[n4]).getRGBColorComponents(null);
            }
            final float[] rgbColorComponents = fromXML.getMandelbrotColor().getRGBColorComponents(null);
            for (int n5 = 0; n5 < array3.length; ++n5) {
                applySubpixelData(bufferedImage, n5, maxIterations, array7, rgbColorComponents, array3, array2);
            }
        }
        else {
            for (int n6 = 0; n6 < array3.length; ++n6) {
                final int[] array8 = array3[n6];
                for (int n7 = 0; n7 < array8.length; ++n7) {
                    int n8;
                    if (array8[n7] == maxIterations) {
                        n8 = rgb;
                    }
                    else {
                        n8 = rgBs[array8[n7] % rgBs.length];
                    }
                    bufferedImage.setRGB(n7, n6, n8);
                }
            }
        }
        final int[][] array9 = null;
        final int[][] array10 = null;
        final String string = s + "_" + bufferedImage.getWidth() + "x" + bufferedImage.getHeight() + "." + s2.toLowerCase();
        System.out.print("\n Saving " + string + " ...");
        try {
            final File file = new File(string);
            if (!ImageIO.write(bufferedImage, s2, file)) {
                if (file.isFile()) {
                    file.delete();
                }
                throw new Exception("Format '" + s2 + "' not implemented.");
            }
            System.out.println("Done.");
        }
        catch (Exception ex) {
            System.out.println(" ERROR WHILE TRYING TO WRITE FILE: " + ex);
        }
    }
    
    private static void checkAspect(final BufferedImage bufferedImage) {
        if (MandelbrotCL.xmin.scale() < 23) {
            MandelbrotCL.xmin.setScale(23);
        }
        if (MandelbrotCL.xmax.scale() < 23) {
            MandelbrotCL.xmax.setScale(23);
        }
        if (MandelbrotCL.ymin.scale() < 23) {
            MandelbrotCL.ymin.setScale(23);
        }
        if (MandelbrotCL.ymax.scale() < 23) {
            MandelbrotCL.ymax.setScale(23);
        }
        BigDecimal bigDecimal = MandelbrotCL.xmax.subtract(MandelbrotCL.xmin).setScale(Math.max(MandelbrotCL.xmax.scale(), 15) * 2, 6).divide(new BigDecimal(bufferedImage.getWidth()), 6);
        int n = 0;
        while (bigDecimal.compareTo(MandelbrotCL.TWO) < 0) {
            ++n;
            bigDecimal = bigDecimal.multiply(MandelbrotCL.TEN);
        }
        if (n < 15) {
            n = 15;
        }
        final int n2 = n + 5 + (n - 10) / 10;
        MandelbrotCL.xmin = MandelbrotCL.xmin.setScale(n2, 6);
        MandelbrotCL.xmax = MandelbrotCL.xmax.setScale(n2, 6);
        MandelbrotCL.ymin = MandelbrotCL.ymin.setScale(n2, 6);
        MandelbrotCL.ymax = MandelbrotCL.ymax.setScale(n2, 6);
        final BigDecimal subtract = MandelbrotCL.xmax.subtract(MandelbrotCL.xmin);
        final BigDecimal subtract2 = MandelbrotCL.ymax.subtract(MandelbrotCL.ymin);
        final BigDecimal divide = subtract.divide(subtract2, 6);
        final BigDecimal bigDecimal2 = new BigDecimal(bufferedImage.getWidth() / bufferedImage.getHeight());
        if (divide.compareTo(bigDecimal2) < 0) {
            final BigDecimal divide2 = subtract.multiply(bigDecimal2).divide(divide, 6);
            final BigDecimal divide3 = MandelbrotCL.xmax.add(MandelbrotCL.xmin).divide(MandelbrotCL.TWO, 6);
            MandelbrotCL.xmax = divide3.add(divide2.divide(MandelbrotCL.TWO, 6)).setScale(n2, 6);
            MandelbrotCL.xmin = divide3.subtract(divide2.divide(MandelbrotCL.TWO, 6)).setScale(n2, 6);
        }
        else if (divide.compareTo(bigDecimal2) > 0) {
            final BigDecimal divide4 = subtract2.multiply(divide).divide(bigDecimal2, 6);
            final BigDecimal divide5 = MandelbrotCL.ymax.add(MandelbrotCL.ymin).divide(MandelbrotCL.TWO, 6);
            MandelbrotCL.ymax = divide5.add(divide4.divide(MandelbrotCL.TWO, 6)).setScale(n2, 6);
            MandelbrotCL.ymin = divide5.subtract(divide4.divide(MandelbrotCL.TWO, 6)).setScale(n2, 6);
        }
    }
    
    private static void applySubpixelData(final BufferedImage bufferedImage, final int n, final int n2, final float[][] array, final float[] array2, final int[][] array3, final int[][] array4) {
        final int[] array5 = array3[n];
        final int[] array6 = array4[n];
        final int[] array7 = array4[n + 1];
        for (int i = 0; i < array5.length; ++i) {
            float[] array8;
            if (array5[i] == n2) {
                array8 = array2;
            }
            else {
                array8 = array[array5[i] % array.length];
            }
            float[] array9;
            if (array6[i] == n2) {
                array9 = array2;
            }
            else {
                array9 = array[array6[i] % array.length];
            }
            float[] array10;
            if (array6[i + 1] == n2) {
                array10 = array2;
            }
            else {
                array10 = array[array6[i + 1] % array.length];
            }
            float[] array11;
            if (array7[i] == n2) {
                array11 = array2;
            }
            else {
                array11 = array[array7[i] % array.length];
            }
            float[] array12;
            if (array7[i + 1] == n2) {
                array12 = array2;
            }
            else {
                array12 = array[array7[i + 1] % array.length];
            }
            bufferedImage.setRGB(i, n, new Color((4.0f * array8[0] + array9[0] + array10[0] + array11[0] + array12[0]) / 8.0f, (4.0f * array8[1] + array9[1] + array10[1] + array11[1] + array12[1]) / 8.0f, (4.0f * array8[2] + array9[2] + array10[2] + array11[2] + array12[2]) / 8.0f).getRGB());
        }
    }
    
    static {
        TWO = new BigDecimal("2");
        TEN = new BigDecimal("10");
    }
}
