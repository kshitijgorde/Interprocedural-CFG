// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.util.Iterator;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.imagemap.OverLIBToolTipTagFragmentGenerator;
import org.jfree.chart.imagemap.URLTagFragmentGenerator;
import org.jfree.chart.imagemap.ToolTipTagFragmentGenerator;
import org.jfree.chart.imagemap.StandardURLTagFragmentGenerator;
import org.jfree.chart.imagemap.StandardToolTipTagFragmentGenerator;
import java.io.PrintWriter;
import java.awt.Image;
import com.keypoint.PngEncoder;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public abstract class ChartUtilities
{
    private static final float DEFAULT_JPEG_QUALITY = 0.75f;
    private static final int DEFAULT_PNG_COMPRESSION = 9;
    
    public static void writeChartAsPNG(final OutputStream out, final JFreeChart chart, final int width, final int height) throws IOException {
        writeChartAsPNG(out, chart, width, height, null, false, 9);
    }
    
    public static void writeChartAsPNG(final OutputStream out, final JFreeChart chart, final int width, final int height, final boolean encodeAlpha, final int compression) throws IOException {
        writeChartAsPNG(out, chart, width, height, null, encodeAlpha, compression);
    }
    
    public static void writeChartAsPNG(final OutputStream out, final JFreeChart chart, final int width, final int height, final ChartRenderingInfo info) throws IOException {
        writeChartAsPNG(out, chart, width, height, info, false, 9);
    }
    
    public static void writeChartAsPNG(final OutputStream out, final JFreeChart chart, final int width, final int height, final ChartRenderingInfo info, final boolean encodeAlpha, final int compression) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Null 'out' argument.");
        }
        if (chart == null) {
            throw new IllegalArgumentException("Null 'chart' argument.");
        }
        final BufferedImage chartImage = chart.createBufferedImage(width, height, 2, info);
        writeBufferedImageAsPNG(out, chartImage, encodeAlpha, compression);
    }
    
    public static void writeScaledChartAsPNG(final OutputStream out, final JFreeChart chart, final int width, final int height, final int widthScaleFactor, final int heightScaleFactor) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Null 'out' argument.");
        }
        if (chart == null) {
            throw new IllegalArgumentException("Null 'chart' argument.");
        }
        final double desiredWidth = width * widthScaleFactor;
        final double desiredHeight = height * heightScaleFactor;
        final double defaultWidth = width;
        final double defaultHeight = height;
        boolean scale = false;
        if (widthScaleFactor != 1 || heightScaleFactor != 1) {
            scale = true;
        }
        final double scaleX = desiredWidth / defaultWidth;
        final double scaleY = desiredHeight / defaultHeight;
        final BufferedImage image = new BufferedImage((int)desiredWidth, (int)desiredHeight, 2);
        final Graphics2D g2 = image.createGraphics();
        if (scale) {
            final AffineTransform saved = g2.getTransform();
            g2.transform(AffineTransform.getScaleInstance(scaleX, scaleY));
            chart.draw(g2, new Rectangle2D.Double(0.0, 0.0, defaultWidth, defaultHeight), null, null);
            g2.setTransform(saved);
            g2.dispose();
        }
        else {
            chart.draw(g2, new Rectangle2D.Double(0.0, 0.0, defaultWidth, defaultHeight), null, null);
        }
        out.write(encodeAsPNG(image));
    }
    
    public static void saveChartAsPNG(final File file, final JFreeChart chart, final int width, final int height) throws IOException {
        saveChartAsPNG(file, chart, width, height, null);
    }
    
    public static void saveChartAsPNG(final File file, final JFreeChart chart, final int width, final int height, final ChartRenderingInfo info) throws IOException {
        saveChartAsPNG(file, chart, width, height, info, true, 9);
    }
    
    public static void saveChartAsPNG(final File file, final JFreeChart chart, final int width, final int height, final ChartRenderingInfo info, final boolean encodeAlpha, final int compression) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("Null 'file' argument.");
        }
        if (chart == null) {
            throw new IllegalArgumentException("Null 'chart' argument.");
        }
        final OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        writeChartAsPNG(out, chart, width, height, info, encodeAlpha, compression);
        out.close();
    }
    
    public static void writeChartAsJPEG(final OutputStream out, final JFreeChart chart, final int width, final int height) throws IOException {
        writeChartAsJPEG(out, 0.75f, chart, width, height, null);
    }
    
    public static void writeChartAsJPEG(final OutputStream out, final float quality, final JFreeChart chart, final int width, final int height) throws IOException {
        writeChartAsJPEG(out, quality, chart, width, height, null);
    }
    
    public static void writeChartAsJPEG(final OutputStream out, final JFreeChart chart, final int width, final int height, final ChartRenderingInfo info) throws IOException {
        writeChartAsJPEG(out, 0.75f, chart, width, height, info);
    }
    
    public static void writeChartAsJPEG(final OutputStream out, final float quality, final JFreeChart chart, final int width, final int height, final ChartRenderingInfo info) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Null 'out' argument.");
        }
        if (chart == null) {
            throw new IllegalArgumentException("Null 'chart' argument.");
        }
        final BufferedImage chartImage = chart.createBufferedImage(width, height, info);
        writeBufferedImageAsJPEG(out, quality, chartImage);
    }
    
    public static void saveChartAsJPEG(final File file, final JFreeChart chart, final int width, final int height) throws IOException {
        saveChartAsJPEG(file, 0.75f, chart, width, height, null);
    }
    
    public static void saveChartAsJPEG(final File file, final float quality, final JFreeChart chart, final int width, final int height) throws IOException {
        saveChartAsJPEG(file, quality, chart, width, height, null);
    }
    
    public static void saveChartAsJPEG(final File file, final JFreeChart chart, final int width, final int height, final ChartRenderingInfo info) throws IOException {
        saveChartAsJPEG(file, 0.75f, chart, width, height, info);
    }
    
    public static void saveChartAsJPEG(final File file, final float quality, final JFreeChart chart, final int width, final int height, final ChartRenderingInfo info) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("Null 'file' argument.");
        }
        if (chart == null) {
            throw new IllegalArgumentException("Null 'chart' argument.");
        }
        final OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        writeChartAsJPEG(out, quality, chart, width, height, info);
        out.close();
    }
    
    public static void writeBufferedImageAsJPEG(final OutputStream out, final BufferedImage image) throws IOException {
        writeBufferedImageAsJPEG(out, 0.75f, image);
    }
    
    public static void writeBufferedImageAsJPEG(final OutputStream out, final float quality, final BufferedImage image) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Null 'out' argument.");
        }
        if (image == null) {
            throw new IllegalArgumentException("Null 'image' argument.");
        }
        final JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        final JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
        param.setQuality(quality, true);
        encoder.encode(image, param);
    }
    
    public static void writeBufferedImageAsPNG(final OutputStream out, final BufferedImage image) throws IOException {
        writeBufferedImageAsPNG(out, image, false, 9);
    }
    
    public static void writeBufferedImageAsPNG(final OutputStream out, final BufferedImage image, final boolean encodeAlpha, final int compression) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Null 'out' argument.");
        }
        if (image == null) {
            throw new IllegalArgumentException("Null 'image' argument.");
        }
        out.write(encodeAsPNG(image, encodeAlpha, compression));
    }
    
    public static byte[] encodeAsPNG(final BufferedImage image) {
        return encodeAsPNG(image, false, 9);
    }
    
    public static byte[] encodeAsPNG(final BufferedImage image, final boolean encodeAlpha, final int compression) {
        if (image == null) {
            throw new IllegalArgumentException("Null 'image' argument.");
        }
        final PngEncoder encoder = new PngEncoder(image, encodeAlpha, 0, compression);
        return encoder.pngEncode();
    }
    
    public static void writeImageMap(final PrintWriter writer, final String name, final ChartRenderingInfo info) throws IOException {
        writeImageMap(writer, name, info, new StandardToolTipTagFragmentGenerator(), new StandardURLTagFragmentGenerator());
    }
    
    public static void writeImageMap(final PrintWriter writer, final String name, final ChartRenderingInfo info, final boolean useOverLibForToolTips) throws IOException {
        ToolTipTagFragmentGenerator toolTipTagFragmentGenerator = null;
        if (useOverLibForToolTips) {
            toolTipTagFragmentGenerator = new OverLIBToolTipTagFragmentGenerator();
        }
        else {
            toolTipTagFragmentGenerator = new StandardToolTipTagFragmentGenerator();
        }
        writeImageMap(writer, name, info, toolTipTagFragmentGenerator, new StandardURLTagFragmentGenerator());
    }
    
    public static void writeImageMap(final PrintWriter writer, final String name, final ChartRenderingInfo info, final ToolTipTagFragmentGenerator toolTipTagFragmentGenerator, final URLTagFragmentGenerator urlTagFragmentGenerator) throws IOException {
        writer.println(getImageMap(name, info, toolTipTagFragmentGenerator, urlTagFragmentGenerator));
    }
    
    public static String getImageMap(final String name, final ChartRenderingInfo info) {
        return getImageMap(name, info, new StandardToolTipTagFragmentGenerator(), new StandardURLTagFragmentGenerator());
    }
    
    public static String getImageMap(final String name, final ChartRenderingInfo info, final ToolTipTagFragmentGenerator toolTipTagFragmentGenerator, final URLTagFragmentGenerator urlTagFragmentGenerator) {
        final StringBuffer sb = new StringBuffer();
        sb.append("<MAP NAME=\"" + name + "\">");
        sb.append(System.getProperty("line.separator"));
        final EntityCollection entities = info.getEntityCollection();
        if (entities != null) {
            for (final ChartEntity entity : entities) {
                final String area = entity.getImageMapAreaTag(toolTipTagFragmentGenerator, urlTagFragmentGenerator);
                if (area.length() > 0) {
                    sb.append(area);
                    sb.append(System.getProperty("line.separator"));
                }
            }
        }
        sb.append("</MAP>");
        return sb.toString();
    }
}
