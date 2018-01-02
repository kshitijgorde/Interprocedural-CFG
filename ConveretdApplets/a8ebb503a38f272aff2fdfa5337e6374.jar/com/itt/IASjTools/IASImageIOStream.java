// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.IASjTools;

import java.io.RandomAccessFile;
import java.awt.Dimension;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayInputStream;
import com.ittvis.imageio.jpip.UUIDBox;
import com.ittvis.imageio.util.GeoTiffIIOMetadataAdapter;
import com.ittvis.imageio.jpip.XMLBox;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.event.IIOReadWarningListener;
import javax.imageio.event.IIOReadUpdateListener;
import javax.imageio.event.IIOReadProgressListener;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.ImageReadParam;
import java.awt.Rectangle;
import com.ittvis.imageio.jpip.J2KImageReadParam;
import java.net.URISyntaxException;
import java.net.MalformedURLException;
import javax.imageio.ImageReader;
import java.util.Iterator;
import java.net.URL;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.net.URI;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOException;
import com.ittvis.imageio.jpip.JPIPImageReaderBase;
import com.ittvis.imageio.jpip.JPIPImageInputStreamInterface;
import com.ittvis.imageio.jpip.J2KMetadata;

public class IASImageIOStream implements IASStream
{
    private J2KMetadata metadata;
    private JPIPImageInputStreamInterface input;
    private JPIPImageReaderBase reader;
    private String inputStreamType;
    
    public IASImageIOStream() {
        this.metadata = null;
        this.inputStreamType = new String();
    }
    
    public void cancelStream() {
        try {
            if (this.input != null) {
                this.input.abort();
            }
            if (this.reader != null) {
                this.reader.abort();
            }
        }
        catch (IIOException ex) {
            System.out.println("Could not cancel stream!");
            System.out.println(ex.toString());
        }
    }
    
    public void close() {
        try {
            this.input.close();
        }
        catch (IOException ex) {}
    }
    
    public void openImage(final String s, final String s2, final String s3, final boolean useCache, final File cacheDirectory) throws MalformedURLException, IOException, URISyntaxException {
        this.cancelStream();
        final URI uri = new URI(s);
        String s4 = "http";
        if (uri.getScheme().equalsIgnoreCase("jpips")) {
            s4 = "https";
        }
        URL url;
        if (s2 != null) {
            url = new URI(s4, s2 + ":" + s3, uri.getHost(), uri.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment()).toURL();
        }
        else {
            url = new URI(s4, null, uri.getHost(), uri.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment()).toURL();
        }
        try {
            ImageIO.setUseCache(useCache);
            ImageIO.setCacheDirectory(cacheDirectory);
            this.input = (JPIPImageInputStreamInterface)ImageIO.createImageInputStream(url);
            this.inputStreamType = this.input.getClass().toString();
        }
        catch (IIOException ex) {
            throw new IOException("Could not open image - please check target url");
        }
        final Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(this.input);
        while (imageReaders.hasNext()) {
            final ImageReader next = imageReaders.next();
            if (next instanceof JPIPImageReaderBase) {
                (this.reader = (JPIPImageReaderBase)next).setInput((Object)this.input);
                break;
            }
            System.out.println("Reader is NOT JPIPImageReaderBase, trying again");
        }
        if (this.reader == null) {
            JOptionPane.showMessageDialog(null, "Error loading JPIPImageReader! Application will exit.  Please try again.", "Error on startup", 0);
            System.exit(0);
        }
        this.metadata = (J2KMetadata)this.reader.getImageMetadata(0);
    }
    
    public void setWindow(final int n, final int n2, final int n3, final int n4, final int n5, final int[] sourceBands, final int quality, final float[] draMin, final float[] draMax, final double sharpGain, final boolean thumbnail) throws IOException {
        final J2KImageReadParam j2KImageReadParam = new J2KImageReadParam();
        final int n6 = (int)Math.pow(2.0, n5);
        j2KImageReadParam.setSourceSubsampling(n6, n6, 0, 0);
        j2KImageReadParam.setSourceRegion(new Rectangle(n, n2, n3, n4));
        j2KImageReadParam.setThumbnail(thumbnail);
        j2KImageReadParam.setSourceBands(sourceBands);
        j2KImageReadParam.setQuality(quality);
        j2KImageReadParam.setDraMin(draMin);
        j2KImageReadParam.setDraMax(draMax);
        j2KImageReadParam.setSharpGain(sharpGain);
        if (this.reader != null) {
            this.reader.read(0, (ImageReadParam)j2KImageReadParam);
        }
    }
    
    public IIOMetadata getMetadata() {
        return (IIOMetadata)this.metadata;
    }
    
    public int getTileX() {
        return this.input.getXTileSize();
    }
    
    public int getTileY() {
        return this.input.getYTileSize();
    }
    
    public int getSDimsX() {
        return this.input.getXSize();
    }
    
    public int getSDimsY() {
        return this.input.getYSize();
    }
    
    public int getNLayers() {
        return this.input.getNLayers();
    }
    
    public int getNDecompositionLevels() {
        return this.input.getDecompositionLevels();
    }
    
    public void removalAllListeners() {
        if (this.reader != null) {
            this.reader.removeAllIIOReadProgressListeners();
            this.reader.removeAllIIOReadUpdateListeners();
            this.reader.removeAllIIOReadWarningListeners();
        }
    }
    
    public void addIIOReadProgressListener(final IIOReadProgressListener iioReadProgressListener) {
        if (this.reader != null) {
            this.reader.addIIOReadProgressListener(iioReadProgressListener);
        }
    }
    
    public void addIIOReadUpdateListener(final IIOReadUpdateListener iioReadUpdateListener) {
        if (this.reader != null) {
            this.reader.addIIOReadUpdateListener(iioReadUpdateListener);
        }
    }
    
    public void addIIOReadWarningListener(final IIOReadWarningListener iioReadWarningListener) {
        if (this.reader != null) {
            this.reader.addIIOReadWarningListener(iioReadWarningListener);
        }
    }
    
    public List getXML() {
        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList elementList = this.metadata.getElementList(XMLBox.getName(2020437024));
        for (int i = 0; i < elementList.size(); ++i) {
            final XMLBox xmlBox = elementList.get(i);
            if (xmlBox != null) {
                list.add(new String(xmlBox.getContent()).replace('\0', ' '));
            }
        }
        return list;
    }
    
    public GeoTiffIIOMetadataAdapter getGeoInfo() throws IOException {
        final UUIDBox uuidBox = (UUIDBox)this.metadata.getElement(UUIDBox.getName(1970628964));
        if (uuidBox == null) {
            return null;
        }
        final Iterator<ImageReader> imageReadersByFormatName = ImageIO.getImageReadersByFormatName("TIFF");
        if (!imageReadersByFormatName.hasNext()) {
            throw new IllegalStateException("No TIFF reader found");
        }
        final ImageReader imageReader = imageReadersByFormatName.next();
        final ImageInputStream imageInputStream = ImageIO.createImageInputStream(new ByteArrayInputStream(uuidBox.getData()));
        if (imageInputStream != null) {
            imageReader.setInput(imageInputStream);
            return new GeoTiffIIOMetadataAdapter(imageReader.getImageMetadata(0));
        }
        return null;
    }
    
    public Object getRawData() {
        return this.reader.getRawData();
    }
    
    public Object getRawDataThumbnail() {
        return this.reader.getRawDataThumbnail();
    }
    
    public int getBytesStreamed() {
        return this.reader.getBytesStreamed();
    }
    
    public Dimension getRawDataBufferSize() {
        return this.reader.getAdjustedRequestRegion();
    }
    
    public Dimension getRawDataBufferSizeThumbnail() {
        return this.reader.getAdjustedRequestRegionThumbnail();
    }
    
    public long getImageFileSize() {
        return this.reader.getImageFileSize();
    }
    
    public String getInputStreamType() {
        return this.inputStreamType;
    }
    
    public void copyCodestream(final RandomAccessFile randomAccessFile, final String s, final ArrayList list) throws IOException {
        this.reader.copyCodestream(randomAccessFile, s, list);
    }
    
    public JPIPImageInputStreamInterface getInput() {
        return this.input;
    }
    
    public void setTransmissionLength(final int transmissionLength) {
        this.reader.setTransmissionLength(transmissionLength);
    }
}
