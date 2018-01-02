// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.image.ColorModel;
import java.awt.image.SampleModel;
import java.io.IOException;
import java.util.Hashtable;
import java.awt.image.BufferedImage;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.awt.image.Raster;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.awt.image.RenderedImage;
import com.sun.image.codec.jpeg.JPEGQTable;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import java.io.OutputStream;

public class k extends r
{
    private R J;
    
    public k(final OutputStream outputStream, final M m) {
        super(outputStream, m);
        this.J = null;
        if (m != null) {
            this.J = (R)m;
        }
    }
    
    static void A(final R r, final JPEGEncodeParam jpegEncodeParam, final int n) {
        for (int i = 0; i < n; ++i) {
            jpegEncodeParam.setHorizontalSubsampling(i, r.E(i));
            jpegEncodeParam.setVerticalSubsampling(i, r.G(i));
            if (r.H(i)) {
                final int[] f = r.F(i);
                final int j = r.I(i);
                jpegEncodeParam.setQTableComponentMapping(i, j);
                jpegEncodeParam.setQTable(j, new JPEGQTable(f));
            }
        }
        if (r.H()) {
            jpegEncodeParam.setQuality(r.K(), true);
        }
        jpegEncodeParam.setRestartInterval(r.G());
        if (r.J()) {
            jpegEncodeParam.setImageInfoValid(false);
            jpegEncodeParam.setTableInfoValid(true);
        }
        if (r.L()) {
            jpegEncodeParam.setTableInfoValid(false);
            jpegEncodeParam.setImageInfoValid(true);
        }
        if (!r.I()) {
            jpegEncodeParam.setMarkerData(224, (byte[][])null);
        }
    }
    
    public void A(final RenderedImage renderedImage) throws IOException {
        final SampleModel sampleModel = renderedImage.getSampleModel();
        final ColorModel colorModel = renderedImage.getColorModel();
        final int numColorComponents = colorModel.getNumColorComponents();
        if (sampleModel.getTransferType() != 0 || (numColorComponents != 1 && numColorComponents != 3)) {
            throw new RuntimeException(m.A("JPEGImageEncoder0"));
        }
        final int type = colorModel.getColorSpace().getType();
        if (type != 6 && type != 5) {
            throw new Error(m.A("JPEGImageEncoder1"));
        }
        WritableRaster writableTranslatedChild = (WritableRaster)renderedImage.getData();
        if (writableTranslatedChild.getMinX() != 0 || writableTranslatedChild.getMinY() != 0) {
            writableTranslatedChild = writableTranslatedChild.createWritableTranslatedChild(0, 0);
        }
        BufferedImage convertToIntDiscrete;
        JPEGEncodeParam jpegEncodeParam;
        if (colorModel instanceof IndexColorModel) {
            convertToIntDiscrete = ((IndexColorModel)colorModel).convertToIntDiscrete(writableTranslatedChild, false);
            jpegEncodeParam = JPEGCodec.getDefaultJPEGEncodeParam(convertToIntDiscrete);
        }
        else {
            convertToIntDiscrete = new BufferedImage(colorModel, writableTranslatedChild, false, null);
            jpegEncodeParam = JPEGCodec.getDefaultJPEGEncodeParam(convertToIntDiscrete);
        }
        if (this.J != null) {
            A(this.J, jpegEncodeParam, numColorComponents);
        }
        final JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(this.A, jpegEncodeParam);
        try {
            jpegEncoder.encode(convertToIntDiscrete);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
