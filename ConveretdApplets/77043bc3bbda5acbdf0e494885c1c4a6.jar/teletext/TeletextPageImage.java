// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.Image;
import java.awt.image.MemoryImageSource;

public class TeletextPageImage
{
    TeletextPage teletextPage;
    TeletextPageRenderBuffer teletextPageRenderBuffer;
    int[] pixelData;
    MemoryImageSource memoryImageSource;
    Image image;
    IndexColorModel colorModel;
    
    public TeletextPageImage(final TeletextPage teletextPage) {
        this.teletextPage = teletextPage;
        this.teletextPageRenderBuffer = new TeletextPageRenderBuffer(this.teletextPage);
        this.pixelData = new int[this.getPixelWidth() * this.getPixelHeight()];
        this.colorModel = new IndexColorModel(3, 8, new byte[] { 0, -1, 0, -1, 0, -1, 0, -1 }, new byte[] { 0, 0, -1, -1, 0, 0, -1, -1 }, new byte[] { 0, 0, 0, 0, -1, -1, -1, -1 }, 255);
        (this.memoryImageSource = new MemoryImageSource(this.getPixelWidth(), this.getPixelHeight(), this.colorModel, this.pixelData, 0, this.getPixelWidth())).setAnimated(true);
        this.image = Toolkit.getDefaultToolkit().createImage(this.memoryImageSource);
    }
    
    public int getPixelWidth() {
        return TeletextFont.getPixelWidth() * this.teletextPage.getWidth();
    }
    
    public int getPixelHeight() {
        return TeletextFont.getPixelHeight() * this.teletextPage.getHeight();
    }
    
    public void setPage(final TeletextPage teletextPage) {
        this.teletextPage = teletextPage;
        this.teletextPageRenderBuffer.setPage(this.teletextPage);
        this.render();
        this.updateImage();
    }
    
    public TeletextPage getPage() {
        return this.teletextPage;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public TeletextPageRenderBuffer getRenderBuffer() {
        return this.teletextPageRenderBuffer;
    }
    
    public void renderLine(final int n) {
        this.teletextPageRenderBuffer.processLine(n);
        boolean doubleHeightFlag = false;
        if (n > 0) {
            doubleHeightFlag = this.teletextPageRenderBuffer.getDoubleHeightFlag(n - 1);
        }
        for (int i = 0; i < TeletextFont.getPixelHeight(); ++i) {
            for (int j = 0; j < this.teletextPage.getWidth(); ++j) {
                TeletextPageRenderCell teletextPageRenderCell = this.teletextPageRenderBuffer.getCellAt(j, n);
                for (int k = 0; k < TeletextFont.getByteWidth(); ++k) {
                    int n2;
                    if (doubleHeightFlag) {
                        teletextPageRenderCell = this.teletextPageRenderBuffer.getCellAt(j, n - 1);
                        if (teletextPageRenderCell.doubleHeight) {
                            n2 = TeletextFont.getFontBitmap(teletextPageRenderCell.visibleCharacter)[(TeletextFont.getPixelHeight() + i >> 1) * TeletextFont.getByteWidth() + k];
                        }
                        else {
                            n2 = 0;
                        }
                    }
                    else if (teletextPageRenderCell.doubleHeight) {
                        n2 = TeletextFont.getFontBitmap(teletextPageRenderCell.visibleCharacter)[(i >> 1) * 2 + k];
                    }
                    else {
                        n2 = TeletextFont.getFontBitmap(teletextPageRenderCell.visibleCharacter)[i * 2 + k];
                    }
                    for (int l = 0; l < 8; ++l) {
                        final int n3 = j * TeletextFont.getPixelWidth() + k * 8 + l;
                        final int n4 = n * TeletextFont.getPixelHeight() + i;
                        if ((n2 & 1 << 7 - l) != 0x0) {
                            this.pixelData[n4 * this.getPixelWidth() + n3] = teletextPageRenderCell.foreground;
                        }
                        else {
                            this.pixelData[n4 * this.getPixelWidth() + n3] = teletextPageRenderCell.background;
                        }
                    }
                }
            }
        }
        if (this.teletextPageRenderBuffer.getDoubleHeightFlag(n) && n < this.teletextPage.getHeight()) {
            this.renderLine(n + 1);
        }
    }
    
    public void render() {
        this.renderLine(0);
        for (int i = 1; i < this.teletextPage.getHeight(); ++i) {
            if (!this.teletextPageRenderBuffer.getDoubleHeightFlag(i - 1)) {
                this.renderLine(i);
            }
        }
    }
    
    public void updateImage(final int n, final int n2, final int n3, final int n4) {
        this.memoryImageSource.newPixels(n * TeletextFont.getPixelWidth(), n2 * TeletextFont.getPixelHeight(), (n3 + 1) * TeletextFont.getPixelWidth(), (n4 + (this.teletextPageRenderBuffer.getDoubleHeightFlag(n4) ? 2 : 1)) * TeletextFont.getPixelHeight());
    }
    
    public void updateImage(final int n) {
        this.updateImage(0, n, this.teletextPage.getWidth() - 1, n);
    }
    
    public void updateImage(final int n, final int n2) {
        this.updateImage(n, n2, n, n2);
    }
    
    public void updateImage() {
        this.memoryImageSource.newPixels(0, 0, this.getPixelWidth(), this.getPixelHeight());
    }
}
