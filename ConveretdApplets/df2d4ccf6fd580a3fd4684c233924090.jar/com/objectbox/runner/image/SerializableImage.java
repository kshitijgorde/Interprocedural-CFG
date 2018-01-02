// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.image;

import java.awt.image.PixelGrabber;
import java.io.ObjectOutputStream;
import java.awt.image.MemoryImageSource;
import java.io.ObjectInputStream;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Canvas;
import java.awt.image.ImageProducer;
import java.io.FileInputStream;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.net.URL;
import java.awt.Toolkit;
import java.io.InputStream;
import java.io.Serializable;
import java.awt.Image;

public class SerializableImage extends Image implements Serializable
{
    private transient Image image;
    private byte[] bytes;
    private String location;
    private boolean isLocationURL;
    
    public SerializableImage(final byte[] bytes) {
        this.bytes = bytes;
        this.getImageFromBytes();
    }
    
    public SerializableImage(final Image image) {
        loadAllBits(this.image = image);
    }
    
    public SerializableImage(final InputStream inputStream) {
        this.isLocationURL = false;
        try {
            inputStream.read(this.bytes = new byte[inputStream.available()]);
            loadAllBits(this.image = Toolkit.getDefaultToolkit().createImage(this.bytes));
        }
        catch (Exception ex) {
            if (inputStream == null) {
                throw new ImageNotFoundException("InputStream for image is null");
            }
            ex.printStackTrace();
        }
    }
    
    public SerializableImage(final String s) {
        this(s, false);
    }
    
    public SerializableImage(final String location, final boolean b) {
        this.isLocationURL = false;
        this.location = location;
        this.getImageFromFilesystem();
        if (b) {
            this.getImageBytesFromFilesystem();
        }
    }
    
    public SerializableImage(final URL url) {
        this(url, false);
    }
    
    public SerializableImage(final URL url, final boolean b) {
        this.isLocationURL = true;
        try {
            this.location = url.toExternalForm();
        }
        catch (NullPointerException ex) {
            throw new ImageNotFoundException("URL for image is null");
        }
        this.getImageFromURL();
        if (b) {
            this.getImageBytesFromURL();
        }
    }
    
    public void flush() {
        this.image.flush();
    }
    
    public byte[] getBytes() {
        return this.bytes;
    }
    
    final byte[] getBytesFromStream(final InputStream inputStream) throws IOException {
        final Vector<Byte> vector = new Vector<Byte>();
        int n = 0;
        int read;
        while ((read = inputStream.read()) != -1) {
            ++n;
            vector.addElement(new Byte((byte)read));
        }
        final byte[] array = new byte[n];
        int n2 = 0;
        final Enumeration<Byte> elements = vector.elements();
        while (elements.hasMoreElements()) {
            array[n2++] = elements.nextElement();
        }
        return array;
    }
    
    public Graphics getGraphics() {
        return this.image.getGraphics();
    }
    
    public int getHeight(final ImageObserver imageObserver) {
        return this.image.getHeight(imageObserver);
    }
    
    public Image getImage() {
        return this.image;
    }
    
    final void getImageBytesFromFilesystem() {
        try {
            this.bytes = this.getBytesFromStream(new FileInputStream(this.location));
        }
        catch (IOException ex) {
            throw new ImageNotFoundException(ex, "Error loading bytes from file stream");
        }
    }
    
    final void getImageBytesFromURL() {
        try {
            this.bytes = this.getBytesFromStream(new URL(this.location).openStream());
        }
        catch (Exception ex) {
            throw new ImageNotFoundException(ex, "Error loading bytes from URL stream");
        }
    }
    
    final void getImageFromBytes() {
        loadAllBits(this.image = Toolkit.getDefaultToolkit().createImage(this.bytes));
    }
    
    final void getImageFromFilesystem() throws ImageNotFoundException {
        this.image = Toolkit.getDefaultToolkit().getImage(this.location);
        if (!loadAllBits(this.image)) {
            throw new ImageNotFoundException("File " + this.location + " was not found.");
        }
    }
    
    final void getImageFromURL() throws ImageNotFoundException {
        try {
            this.image = Toolkit.getDefaultToolkit().getImage(new URL(this.location));
        }
        catch (Exception ex) {
            throw new ImageNotFoundException(ex, "URL" + this.location + " was not found.");
        }
        if (!loadAllBits(this.image)) {
            throw new ImageNotFoundException("URL" + this.location + " was not found.");
        }
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public Object getProperty(final String s, final ImageObserver imageObserver) {
        return this.image.getProperty(s, imageObserver);
    }
    
    public ImageProducer getSource() {
        return this.image.getSource();
    }
    
    public int getWidth(final ImageObserver imageObserver) {
        return this.image.getWidth(imageObserver);
    }
    
    public boolean isLocationURL() {
        return this.isLocationURL;
    }
    
    public static final boolean loadAllBits(final Image image) {
        final MediaTracker mediaTracker = new MediaTracker(new Canvas());
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        return !mediaTracker.isErrorID(0);
    }
    
    public SerializableImage[] parse(final int n) {
        final SerializableImage[] array = new SerializableImage[n];
        final int n2 = this.image.getWidth(null) / n;
        final int height = this.image.getHeight(null);
        for (int i = 0; i < n; ++i) {
            array[i] = new SerializableImage(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(this.image.getSource(), new CropImageFilter(i * n2, 0, n2, height))));
            loadAllBits(array[i].getImage());
        }
        return array;
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        if (this.bytes != null) {
            this.getImageFromBytes();
        }
        else if (this.location != null) {
            if (this.isLocationURL()) {
                this.getImageFromURL();
            }
            else {
                this.getImageFromFilesystem();
            }
        }
        else {
            final int int1 = objectInputStream.readInt();
            this.image = new Canvas().createImage(new MemoryImageSource(int1, objectInputStream.readInt(), (int[])objectInputStream.readObject(), 0, int1));
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (this.bytes == null && this.location == null) {
            final int width = this.image.getWidth(null);
            final int height = this.image.getHeight(null);
            objectOutputStream.writeInt(width);
            objectOutputStream.writeInt(height);
            final int[] array = new int[width * height];
            final PixelGrabber pixelGrabber = new PixelGrabber(this.image, 0, 0, width, height, array, 0, width);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {}
            objectOutputStream.writeObject(array);
        }
    }
}
