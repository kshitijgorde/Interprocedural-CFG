// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.imagetools;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import com.itt.J2KViewer.controller.DimensionManager;
import javax.swing.JPanel;
import com.itt.J2KViewer.util.ImageUtils;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import javax.swing.SwingUtilities;
import java.beans.PropertyVetoException;
import javax.imageio.ImageReader;
import com.itt.IASjTools.IASComponent;
import com.ittvis.imageio.jpip.ColorSpecificationBox;
import com.ittvis.imageio.jpip.HeaderBox;
import com.ittvis.imageio.jpip.J2KMetadata;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.MalformedURLException;
import com.itt.J2KViewer.controller.PropertyManager;
import java.io.File;
import com.itt.IASjTools.IASJ2KException;
import com.itt.IASjTools.IASImage;
import com.itt.IASjTools.IASImageIOStream;
import com.itt.J2KViewer.util.Log;
import java.awt.image.BufferedImage;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.gui.MainImagePanel;
import javax.imageio.event.IIOReadUpdateListener;
import javax.imageio.event.IIOReadProgressListener;

public class MainImageStream implements IIOReadProgressListener, IIOReadUpdateListener
{
    private MainImagePanel mainImagePanel;
    private ViewCentral viewCentral;
    private BufferedImage currentImage;
    private Log log;
    private IASImageIOStream stream;
    private IASImage iasImage;
    private final int JP2_bilevel1_SPACE = 0;
    private final int JP2_YCbCr1_SPACE = 1;
    private final int JP2_YCbCr2_SPACE = 3;
    private final int JP2_YCbCr3_SPACE = 4;
    private final int JP2_PhotoYCC_SPACE = 9;
    private final int JP2_CMY_SPACE = 11;
    private final int JP2_CMYK_SPACE = 12;
    private final int JP2_YCCK_SPACE = 13;
    private final int JP2_CIELab_SPACE = 14;
    private final int JP2_bilevel2_SPACE = 15;
    private final int JP2_sRGB_SPACE = 16;
    private final int JP2_sLUM_SPACE = 17;
    private final int JP2_sYCC_SPACE = 18;
    private final int JP2_CIEJab_SPACE = 19;
    private final int JP2_esRGB_SPACE = 20;
    private final int JP2_ROMMRGB_SPACE = 21;
    private final int JP2_YPbPr60_SPACE = 22;
    private final int JP2_YPbPr50_SPACE = 23;
    private final int JP2_esYCC_SPACE = 24;
    private final int JP2_iccLUM_SPACE = 100;
    private final int JP2_iccRGB_SPACE = 101;
    private final int JP2_iccANY_SPACE = 102;
    private final int JP2_vendor_SPACE = 200;
    private int imageWidth;
    private int imageHeight;
    private int[] imageBands;
    static /* synthetic */ Class class$com$itt$J2KViewer$imagetools$MainImageStream;
    
    public MainImageStream(final ViewCentral viewCentral, final MainImagePanel mainImagePanel) throws IASJ2KException {
        this.currentImage = null;
        this.log = new Log((MainImageStream.class$com$itt$J2KViewer$imagetools$MainImageStream == null) ? (MainImageStream.class$com$itt$J2KViewer$imagetools$MainImageStream = class$("com.itt.J2KViewer.imagetools.MainImageStream")) : MainImageStream.class$com$itt$J2KViewer$imagetools$MainImageStream);
        this.stream = new IASImageIOStream();
        this.iasImage = null;
        this.viewCentral = viewCentral;
        this.mainImagePanel = mainImagePanel;
    }
    
    public void cancelStream() {
        this.stream.cancelStream();
        this.currentImage = null;
    }
    
    public void close() {
        this.stream.close();
    }
    
    public void openImage(final String s, final String s2, final String s3, final boolean b, final File file) throws MalformedURLException, IOException, URISyntaxException {
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        this.iasImage = null;
        this.stream.removalAllListeners();
        this.stream.openImage(s, s2, s3, b, file);
        this.stream.addIIOReadProgressListener(this);
        this.stream.addIIOReadUpdateListener(this);
        propertyManager.setTotalBytes(this.stream.getImageFileSize());
        if (propertyManager.getTransmissionLength() > 0) {
            this.stream.setTransmissionLength(propertyManager.getTransmissionLength());
        }
    }
    
    public void setMaxQualityLayers(final int n) {
    }
    
    public void setWindow(final int n, final int n2, final int n3, final int n4, final int n5) throws Exception {
        final int width = this.iasImage.getWidth();
        final int height = this.iasImage.getHeight();
        if (n3 < 1 || n3 > width || n4 < 1 || n4 > height) {
            throw new Exception("Illegal window 'width' or 'height' specification. Width must be less than or equal to: " + width + ". " + "Height must be less than or equal to: " + height);
        }
        if (n < 0 || n >= width || n2 < 0 || n2 >= height) {
            throw new Exception("Illegal window 'x' or 'y' offset specification");
        }
        if (n + n3 > width || n2 + n4 > height) {
            throw new Exception("Illegal window specification");
        }
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DRAManager draManager = this.viewCentral.getDRAManager();
        final int[] rgbMap = propertyManager.getRGBMap();
        final int qualityLayers = propertyManager.getQualityLayers();
        final double sharpGain = propertyManager.getSharpGain();
        final float[] array = new float[rgbMap.length];
        final float[] array2 = new float[rgbMap.length];
        for (int i = 0; i < rgbMap.length; ++i) {
            array[i] = draManager.getStretchMin(i);
            array2[i] = draManager.getStretchMax(i);
        }
        this.stream.setWindow(n, n2, n3, n4, n5, rgbMap, qualityLayers, array, array2, sharpGain, false);
    }
    
    public String getRawImageFileProperties() {
        return this.stream.getMetadata().toString();
    }
    
    public IASImage getImageFileProperties() {
        if (this.iasImage == null) {
            this.iasImage = new IASImage();
            if (this.stream.getMetadata() != null) {
                final J2KMetadata j2KMetadata = (J2KMetadata)this.stream.getMetadata();
                final HeaderBox headerBox = (HeaderBox)j2KMetadata.getElement(HeaderBox.getName(1768449138));
                this.iasImage.setNumComponents(headerBox.getNumComponents());
                this.iasImage.setSsize(headerBox.getWidth(), headerBox.getHeight());
                this.iasImage.getTileRange().setX(this.stream.getTileX());
                this.iasImage.getTileRange().setY(this.stream.getTileY());
                switch (((ColorSpecificationBox)j2KMetadata.getElement(ColorSpecificationBox.getName(1668246642))).getEnumeratedColorSpace()) {
                    case 0: {
                        this.iasImage.setColorSpace("bilevel1");
                        break;
                    }
                    case 1: {
                        this.iasImage.setColorSpace("YCbCr1");
                        break;
                    }
                    case 3: {
                        this.iasImage.setColorSpace("YCbCr2");
                        break;
                    }
                    case 4: {
                        this.iasImage.setColorSpace("YCbCr3");
                        break;
                    }
                    case 9: {
                        this.iasImage.setColorSpace("PhotoYCC");
                        break;
                    }
                    case 11: {
                        this.iasImage.setColorSpace("CMY");
                        break;
                    }
                    case 12: {
                        this.iasImage.setColorSpace("CMYK");
                        break;
                    }
                    case 13: {
                        this.iasImage.setColorSpace("YCCK");
                        break;
                    }
                    case 14: {
                        this.iasImage.setColorSpace("CIELab");
                        break;
                    }
                    case 15: {
                        this.iasImage.setColorSpace("bilevel2");
                        break;
                    }
                    case 16: {
                        this.iasImage.setColorSpace("sRGB");
                        break;
                    }
                    case 17: {
                        this.iasImage.setColorSpace("sLUM");
                        break;
                    }
                    case 18: {
                        this.iasImage.setColorSpace("sYCC");
                        break;
                    }
                    case 19: {
                        this.iasImage.setColorSpace("CIEJab");
                        break;
                    }
                    case 20: {
                        this.iasImage.setColorSpace("esRGB");
                        break;
                    }
                    case 21: {
                        this.iasImage.setColorSpace("ROMMRGB");
                        break;
                    }
                    case 22: {
                        this.iasImage.setColorSpace("YPbPr60");
                        break;
                    }
                    case 23: {
                        this.iasImage.setColorSpace("YPbPr50");
                        break;
                    }
                    case 24: {
                        this.iasImage.setColorSpace("esYCC");
                        break;
                    }
                    case 100: {
                        this.iasImage.setColorSpace("iccLUM");
                        break;
                    }
                    case 101: {
                        this.iasImage.setColorSpace("iccRGB");
                        break;
                    }
                    case 102: {
                        this.iasImage.setColorSpace("iccANY");
                        break;
                    }
                    case 200: {
                        this.iasImage.setColorSpace("vendor");
                        break;
                    }
                    default: {
                        this.iasImage.setColorSpace("unknown");
                        break;
                    }
                }
                final short numComponents = headerBox.getNumComponents();
                if (numComponents == 1) {
                    final IASComponent iasComponent = new IASComponent();
                    iasComponent.setPrecision(headerBox.getBitDepth() + 1);
                    iasComponent.getSDims().setX(this.stream.getSDimsX());
                    iasComponent.getSDims().setY(this.stream.getSDimsY());
                    iasComponent.getSTiles().setX(this.stream.getTileX());
                    iasComponent.getSTiles().setY(this.stream.getTileY());
                    this.iasImage.getComponents().add(iasComponent);
                    this.iasImage.getComponents().add(iasComponent);
                    this.iasImage.getComponents().add(iasComponent);
                }
                else {
                    for (short n = 0; n < numComponents; ++n) {
                        final IASComponent iasComponent2 = new IASComponent();
                        iasComponent2.setPrecision(headerBox.getBitDepth() + 1);
                        iasComponent2.getSDims().setX(this.stream.getSDimsX());
                        iasComponent2.getSDims().setY(this.stream.getSDimsY());
                        iasComponent2.getSTiles().setX(this.stream.getTileX());
                        iasComponent2.getSTiles().setY(this.stream.getTileY());
                        this.iasImage.getComponents().add(iasComponent2);
                    }
                }
                this.iasImage.setNumQualityLayers(this.stream.getNLayers());
                this.iasImage.setNumDiscardLevels(this.stream.getNDecompositionLevels());
            }
        }
        return this.iasImage;
    }
    
    public void imageComplete(final ImageReader imageReader) {
        this.viewCentral.getPropertyManager().setInitialImageLoad(false);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainImageStream.this.viewCentral.getPropertyManager().setIASStatus("Idle");
                }
                catch (PropertyVetoException ex) {
                    MainImageStream.this.log.warn("Unable to set IAS status");
                }
            }
        });
    }
    
    public void imageProgress(final ImageReader imageReader, final float n) {
        SwingUtilities.invokeLater(new Runnable() {
            private final /* synthetic */ long val$bytes = (long)n;
            
            public void run() {
                try {
                    MainImageStream.this.viewCentral.getPropertyManager().setIASStatus("Streaming");
                    MainImageStream.this.viewCentral.getPropertyManager().setBytesTransferred(this.val$bytes);
                }
                catch (PropertyVetoException ex) {
                    MainImageStream.this.log.warn("Unable to set IAS status");
                }
            }
        });
    }
    
    public void imageStarted(final ImageReader imageReader, final int n) {
    }
    
    public void readAborted(final ImageReader imageReader) {
    }
    
    public void sequenceComplete(final ImageReader imageReader) {
    }
    
    public void sequenceStarted(final ImageReader imageReader, final int n) {
    }
    
    public void thumbnailComplete(final ImageReader imageReader) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainImageStream.this.viewCentral.getPropertyManager().setIASStatus("Idle");
                }
                catch (PropertyVetoException ex) {
                    MainImageStream.this.log.warn("Unable to set IAS status");
                }
            }
        });
    }
    
    public void thumbnailProgress(final ImageReader imageReader, final float n) {
        SwingUtilities.invokeLater(new Runnable() {
            private final /* synthetic */ long val$bytes = (long)n;
            
            public void run() {
                try {
                    MainImageStream.this.viewCentral.getPropertyManager().setIASStatus("Streaming");
                    MainImageStream.this.viewCentral.getPropertyManager().setBytesTransferred(this.val$bytes);
                }
                catch (PropertyVetoException ex) {
                    MainImageStream.this.log.warn("Unable to set IAS status");
                }
            }
        });
    }
    
    public void thumbnailStarted(final ImageReader imageReader, final int n, final int n2) {
    }
    
    public void imageUpdate(final ImageReader imageReader, final BufferedImage bufferedImage, final int n, final int n2, final int imageWidth, final int imageHeight, final int n3, final int n4, final int[] imageBands) {
        final DRAManager draManager = this.viewCentral.getDRAManager();
        final PropertyManager propertyManager = this.viewCentral.getPropertyManager();
        final DimensionManager dimensionManager = this.viewCentral.getDimensionManager();
        final int displayWidth = this.mainImagePanel.getDisplayWidth();
        final int displayHeight = this.mainImagePanel.getDisplayHeight();
        final boolean useIgnoreValue = draManager.useIgnoreValue();
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.imageBands = imageBands;
        final Image image = this.mainImagePanel.createImage(displayWidth, displayHeight);
        if (image != null) {
            final Graphics2D graphics2D = (Graphics2D)image.getGraphics();
            graphics2D.setColor(this.mainImagePanel.getImageDisplayPanel().getBackground());
            graphics2D.clearRect(0, 0, displayWidth, displayHeight);
            final AffineTransform requestTransform = dimensionManager.getRequestTransform();
            final int discardedZoomLevels = this.viewCentral.getPropertyManager().getDiscardedZoomLevels();
            if (discardedZoomLevels < 0) {
                final double n5 = 1 << -discardedZoomLevels;
                requestTransform.scale(n5, n5);
            }
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.drawImage(bufferedImage, requestTransform, this.mainImagePanel);
        }
        if (image == null) {
            this.currentImage = null;
        }
        else {
            this.currentImage = ImageUtils.convertImageToBufferedImage(image, this.mainImagePanel);
        }
        final int[] array = new int[2];
        final int[] array2 = new int[2];
        final int[] array3 = new int[2];
        array[0] = Integer.MAX_VALUE;
        array[1] = Integer.MIN_VALUE;
        array2[0] = Integer.MAX_VALUE;
        array2[1] = Integer.MIN_VALUE;
        array3[0] = Integer.MAX_VALUE;
        array3[1] = Integer.MIN_VALUE;
        final int n6 = (int)draManager.getMinValue();
        final int n7 = (int)draManager.getMaxValue() - n6 + 1;
        int[] array4 = null;
        int[] array5 = null;
        final int[] array6 = new int[n7];
        if (imageBands.length > 2) {
            array4 = new int[n7];
            array5 = new int[n7];
        }
        final Object rawData = this.stream.getRawData();
        int[] array7 = null;
        short[] array8 = null;
        byte[] array9 = null;
        if (rawData instanceof int[]) {
            array7 = (int[])rawData;
        }
        else if (rawData instanceof short[]) {
            array8 = (short[])rawData;
        }
        else {
            array9 = (byte[])rawData;
        }
        int n8 = 0;
        final int[] array10 = new int[imageBands.length];
        for (int i = 0; i < imageHeight; ++i) {
            for (int j = 0; j < imageWidth; ++j) {
                for (int k = 0; k < imageBands.length; ++k) {
                    if (array7 != null) {
                        array10[k] = (0xFFFF & array7[n8++]);
                    }
                    else if (array8 != null) {
                        array10[k] = (0xFFFF & array8[n8++]);
                    }
                    else {
                        array10[k] = (0xFF & array9[n8++]);
                    }
                }
                final int n9 = array10[0];
                array[0] = ((n9 < array[0]) ? n9 : array[0]);
                array[1] = ((n9 > array[1]) ? n9 : array[1]);
                final int ignoreValue = draManager.getIgnoreValue();
                if (useIgnoreValue) {
                    if (n9 != ignoreValue) {
                        final int[] array11 = array6;
                        final int n10 = n9 - n6;
                        ++array11[n10];
                    }
                }
                else {
                    final int[] array12 = array6;
                    final int n11 = n9 - n6;
                    ++array12[n11];
                }
                if (imageBands.length > 2) {
                    final int n12 = array10[1];
                    final int n13 = array10[2];
                    array2[0] = ((n12 < array2[0]) ? n12 : array2[0]);
                    array2[1] = ((n12 > array2[1]) ? n12 : array2[1]);
                    array3[0] = ((n13 < array3[0]) ? n13 : array3[0]);
                    array3[1] = ((n13 > array3[1]) ? n13 : array3[1]);
                    if (useIgnoreValue) {
                        if (n12 != ignoreValue) {
                            final int[] array13 = array4;
                            final int n14 = n12 - n6;
                            ++array13[n14];
                        }
                        if (n13 != ignoreValue) {
                            final int[] array14 = array5;
                            final int n15 = n13 - n6;
                            ++array14[n15];
                        }
                    }
                    else {
                        final int[] array15 = array4;
                        final int n16 = n12 - n6;
                        ++array15[n16];
                        final int[] array16 = array5;
                        final int n17 = n13 - n6;
                        ++array16[n17];
                    }
                }
            }
        }
        draManager.setDataRange(0, array[0], array[1]);
        draManager.setDataRange(1, array2[0], array2[1]);
        draManager.setDataRange(2, array3[0], array3[1]);
        draManager.setHistograms(array6, array4, array5);
        propertyManager.setNewDataRange(true);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainImageStream.this.mainImagePanel.updateDisplay();
            }
        });
        if (propertyManager.isUponLoadDoAutoDRA() && propertyManager.isInitialImageLoad()) {
            draManager.auto();
        }
    }
    
    public void passComplete(final ImageReader imageReader, final BufferedImage bufferedImage) {
    }
    
    public void passStarted(final ImageReader imageReader, final BufferedImage bufferedImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int[] array) {
    }
    
    public void thumbnailPassComplete(final ImageReader imageReader, final BufferedImage bufferedImage) {
    }
    
    public void thumbnailPassStarted(final ImageReader imageReader, final BufferedImage bufferedImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int[] array) {
    }
    
    public void thumbnailUpdate(final ImageReader imageReader, final BufferedImage bufferedImage, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array) {
    }
    
    public BufferedImage getCurrentImage() {
        return this.currentImage;
    }
    
    public IASImageIOStream getStream() {
        return this.stream;
    }
    
    public int[] getRGBValues(final int n, final int n2) {
        int[] array = null;
        if (this.currentImage != null) {
            try {
                final int rgb = this.currentImage.getRGB(n, n2);
                array = new int[] { (rgb & 0xFF0000) >> 16, (rgb & 0xFF00) >> 8, rgb & 0xFF };
            }
            catch (Exception ex) {}
        }
        return array;
    }
    
    public int[] getRawDataValues(final int n, final int n2) {
        int[] array = null;
        final int[] rgbMap = this.viewCentral.getPropertyManager().getRGBMap();
        final Object rawData = this.stream.getRawData();
        if (rawData != null && this.currentImage != null && rgbMap != null) {
            array = new int[rgbMap.length];
            int n3 = (n + n2 * this.stream.getRawDataBufferSize().width) * rgbMap.length;
            if (rawData instanceof int[]) {
                final int[] array2 = (int[])rawData;
                if (n3 < array2.length) {
                    for (int i = 0; i < rgbMap.length; ++i) {
                        array[i] = array2[n3++];
                    }
                }
            }
            else if (rawData instanceof short[]) {
                final short[] array3 = (short[])rawData;
                if (n3 < array3.length) {
                    for (int j = 0; j < rgbMap.length; ++j) {
                        array[j] = array3[n3++];
                    }
                }
            }
            else {
                final byte[] array4 = (byte[])rawData;
                if (n3 < array4.length) {
                    for (int k = 0; k < rgbMap.length; ++k) {
                        array[k] = (0xFF & array4[n3++]);
                    }
                }
            }
        }
        return array;
    }
    
    public ArrayList calculateHistogram(final boolean b, final int n) {
        final DRAManager draManager = this.viewCentral.getDRAManager();
        final int n2 = (int)draManager.getMinValue();
        final int n3 = (int)draManager.getMaxValue() - (int)draManager.getMinValue() + 1;
        int[] array = null;
        int[] array2 = null;
        final int[] array3 = new int[n3];
        if (this.imageBands.length > 2) {
            array = new int[n3];
            array2 = new int[n3];
        }
        final Object rawData = this.stream.getRawData();
        int[] array4 = null;
        short[] array5 = null;
        byte[] array6 = null;
        if (rawData instanceof int[]) {
            array4 = (int[])rawData;
        }
        else if (rawData instanceof short[]) {
            array5 = (short[])rawData;
        }
        else {
            array6 = (byte[])rawData;
        }
        int n4 = 0;
        final int[] array7 = new int[this.imageBands.length];
        for (int i = 0; i < this.imageHeight; ++i) {
            for (int j = 0; j < this.imageWidth; ++j) {
                for (int k = 0; k < this.imageBands.length; ++k) {
                    if (array4 != null) {
                        array7[k] = (0xFFFF & array4[n4++]);
                    }
                    else if (array5 != null) {
                        array7[k] = (0xFFFF & array5[n4++]);
                    }
                    else {
                        array7[k] = (0xFF & array6[n4++]);
                    }
                }
                final int n5 = array7[0];
                if (b) {
                    if (n5 != n) {
                        final int[] array8 = array3;
                        final int n6 = n5 - n2;
                        ++array8[n6];
                    }
                }
                else {
                    final int[] array9 = array3;
                    final int n7 = n5 - n2;
                    ++array9[n7];
                }
                if (this.imageBands.length > 2) {
                    final int n8 = array7[1];
                    final int n9 = array7[2];
                    if (b) {
                        if (n8 != n) {
                            final int[] array10 = array;
                            final int n10 = n8 - n2;
                            ++array10[n10];
                        }
                        if (n9 != n) {
                            final int[] array11 = array2;
                            final int n11 = n9 - n2;
                            ++array11[n11];
                        }
                    }
                    else {
                        final int[] array12 = array;
                        final int n12 = n8 - n2;
                        ++array12[n12];
                        final int[] array13 = array2;
                        final int n13 = n9 - n2;
                        ++array13[n13];
                    }
                }
            }
        }
        final ArrayList<int[]> list = new ArrayList<int[]>();
        list.add(0, array3);
        list.add(1, array);
        list.add(2, array2);
        return list;
    }
    
    public void copyCodestream(final RandomAccessFile randomAccessFile, final String s, final ArrayList list) throws IOException {
        this.stream.copyCodestream(randomAccessFile, s, list);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
