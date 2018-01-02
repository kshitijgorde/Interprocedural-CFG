// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.controller;

import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import com.itt.J2KViewer.util.ImageUtils;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;
import java.awt.Dimension;

public class DimensionManager
{
    private ViewCentral viewCentral;
    protected int discardLevel;
    protected Dimension displaySize;
    protected Rectangle displaySpace;
    Rectangle imageSpace;
    protected AffineTransform imageSpaceTransform;
    protected Rectangle displayWindow;
    protected Point displayWindowCenter;
    protected Rectangle imageWindow;
    protected Rectangle requestWindow;
    protected double rotationAngle;
    protected AffineTransform requestTransform;
    protected Point centerOffset;
    protected Point requestOffset;
    private Dimension[] imageSize;
    
    public DimensionManager(final ViewCentral viewCentral) {
        this.viewCentral = null;
        this.discardLevel = 0;
        this.displaySize = null;
        this.displaySpace = null;
        this.imageSpace = null;
        this.imageSpaceTransform = null;
        this.displayWindow = null;
        this.displayWindowCenter = null;
        this.imageWindow = null;
        this.requestWindow = null;
        this.rotationAngle = 0.0;
        this.requestTransform = null;
        this.centerOffset = null;
        this.requestOffset = null;
        this.imageSize = null;
        this.viewCentral = viewCentral;
    }
    
    public void setDiscardLevel(final int discardLevel) {
        this.discardLevel = discardLevel;
    }
    
    public void setDisplaySize(final Dimension dimension) {
        this.displaySize = new Dimension(dimension);
    }
    
    public Rectangle getImageSpace() {
        return new Rectangle(this.imageSpace);
    }
    
    public Rectangle getDisplaySpace() {
        return new Rectangle(this.displaySpace);
    }
    
    public void newImage(final int n, final int n2, final int n3) {
        this.imageSpace = new Rectangle(0, 0, n, n2);
        this.displaySpace = new Rectangle(0, 0, n, n2);
        this.displayWindow = new Rectangle();
        this.displayWindowCenter = new Point(0, 0);
        this.rotationAngle = 0.0;
        this.centerOffset = new Point(0, 0);
        this.requestOffset = new Point(0, 0);
        this.imageSpaceTransform = new AffineTransform();
        this.requestTransform = new AffineTransform();
        (this.imageSize = new Dimension[n3 + 1])[0] = new Dimension(n, n2);
        for (int i = 1; i <= n3; ++i) {
            int fullToZoomed = ImageUtils.fullToZoomed(n, i);
            int fullToZoomed2 = ImageUtils.fullToZoomed(n2, i);
            if (fullToZoomed == 0) {
                fullToZoomed = 1;
            }
            if (fullToZoomed2 == 0) {
                fullToZoomed2 = 1;
            }
            this.imageSize[i] = new Dimension(fullToZoomed, fullToZoomed2);
        }
    }
    
    public void setDisplayWindowCenter(final Point point) {
        this.displayWindowCenter = new Point(point);
    }
    
    public Point getDisplayWindowCenter() {
        return new Point(this.displayWindowCenter);
    }
    
    public double getRotationAngle() {
        return this.rotationAngle;
    }
    
    public void setRotationAngle(final double rotationAngle) {
        this.rotationAngle = rotationAngle;
        final Point point = new Point(this.displayToImage(this.displayWindowCenter));
        (this.imageSpaceTransform = new AffineTransform()).rotate(this.rotationAngle, this.imageSpace.width / 2, this.imageSpace.height / 2);
        final Point[] array = new Point[4];
        array[0] = new Point(0, 0);
        this.imageSpaceTransform.transform(array[0], array[0]);
        array[1] = new Point(this.imageSpace.width - 1, 0);
        this.imageSpaceTransform.transform(array[1], array[1]);
        array[2] = new Point(this.imageSpace.width - 1, this.imageSpace.height - 1);
        this.imageSpaceTransform.transform(array[2], array[2]);
        array[3] = new Point(0, this.imageSpace.height - 1);
        this.imageSpaceTransform.transform(array[3], array[3]);
        this.displaySpace.setBounds(this.getBoundingRectangle(array));
        this.displayWindowCenter = this.imageToDisplay(point);
    }
    
    public Rectangle getDisplayWindow() {
        return new Rectangle(this.displayWindow);
    }
    
    public Rectangle getRequestWindow() {
        if (this.requestWindow != null) {
            return new Rectangle(this.requestWindow);
        }
        return null;
    }
    
    public AffineTransform getRequestTransform() {
        return new AffineTransform(this.requestTransform);
    }
    
    public Point getAdjustedDisplayWindowCenter(final Point point) {
        final Point point2 = new Point(point);
        final Dimension dimension = new Dimension(ImageUtils.zoomedToFull(this.displaySize.width, this.discardLevel), ImageUtils.zoomedToFull(this.displaySize.height, this.discardLevel));
        final Rectangle rectangle = new Rectangle(point.x - dimension.width / 2, point.y - dimension.height / 2, dimension.width, dimension.height);
        if (rectangle.width > this.displaySpace.width) {
            point2.x = this.displaySpace.x + this.displaySpace.width / 2;
        }
        else {
            final int n = rectangle.x + rectangle.width - 1;
            final int n2 = this.displaySpace.x + this.displaySpace.width - 1;
            if (rectangle.x < this.displaySpace.x) {
                rectangle.x = this.displaySpace.x;
                point2.x = rectangle.x + dimension.width / 2;
            }
            else if (n > n2) {
                rectangle.x = n2 - dimension.width + 1;
                point2.x = rectangle.x + dimension.width / 2;
            }
        }
        if (rectangle.height > this.displaySpace.height) {
            point2.y = this.displaySpace.y + this.displaySpace.height / 2;
        }
        else {
            final int n3 = rectangle.y + rectangle.height - 1;
            final int n4 = this.displaySpace.y + this.displaySpace.height - 1;
            if (rectangle.y < this.displaySpace.y) {
                rectangle.y = this.displaySpace.y;
                point2.y = rectangle.y + dimension.height / 2;
            }
            else if (n3 > n4) {
                rectangle.y = n4 - dimension.height + 1;
                point2.y = rectangle.y + dimension.height / 2;
            }
        }
        return point2;
    }
    
    public void adjustDisplayWindow(final boolean b) {
        if (b) {
            this.displayWindowCenter = this.getAdjustedDisplayWindowCenter(this.displayWindowCenter);
        }
        final Dimension dimension = new Dimension(ImageUtils.zoomedToFull(this.displaySize.width, this.discardLevel), ImageUtils.zoomedToFull(this.displaySize.height, this.discardLevel));
        if (this.discardLevel < 0) {
            final Dimension dimension2 = dimension;
            dimension2.width += 1 << -this.discardLevel;
            final Dimension dimension3 = dimension;
            dimension3.height += 1 << -this.discardLevel;
        }
        this.displayWindow = new Rectangle(this.displayWindowCenter.x - dimension.width / 2, this.displayWindowCenter.y - dimension.height / 2, dimension.width, dimension.height);
        this.adjustImageWindow();
    }
    
    protected void adjustImageWindow() {
        this.requestOffset = new Point(0, 0);
        this.requestTransform = new AffineTransform();
        this.imageWindow = this.getBoundingRectangle(new Point[] { this.displayToImage(new Point(this.displayWindow.x, this.displayWindow.y)), this.displayToImage(new Point(this.displayWindow.x + this.displayWindow.width - 1, this.displayWindow.y)), this.displayToImage(new Point(this.displayWindow.x + this.displayWindow.width - 1, this.displayWindow.y + this.displayWindow.height - 1)), this.displayToImage(new Point(this.displayWindow.x, this.displayWindow.y + this.displayWindow.height - 1)) });
        this.centerOffset = new Point((this.displayWindow.width - this.imageWindow.width) / 2, (this.displayWindow.height - this.imageWindow.height) / 2);
        this.requestWindow = new Rectangle(this.imageWindow);
        final int x = this.imageSpace.x - this.requestWindow.x;
        if (x > 0) {
            final Rectangle requestWindow = this.requestWindow;
            requestWindow.x += x;
            final Rectangle requestWindow2 = this.requestWindow;
            requestWindow2.width -= x;
            this.requestOffset.x = x;
        }
        final int y = this.imageSpace.y - this.requestWindow.y;
        if (y > 0) {
            final Rectangle requestWindow3 = this.requestWindow;
            requestWindow3.y += y;
            final Rectangle requestWindow4 = this.requestWindow;
            requestWindow4.height -= y;
            this.requestOffset.y = y;
        }
        final int n = this.requestWindow.x + this.requestWindow.width - (this.imageSpace.x + this.imageSpace.width);
        if (n > 0) {
            final Rectangle requestWindow5 = this.requestWindow;
            requestWindow5.width -= n;
        }
        final int n2 = this.requestWindow.y + this.requestWindow.height - (this.imageSpace.y + this.imageSpace.height);
        if (n2 > 0) {
            final Rectangle requestWindow6 = this.requestWindow;
            requestWindow6.height -= n2;
        }
        this.requestTransform.rotate(this.rotationAngle, this.displaySize.width / 2, this.displaySize.height / 2);
        this.requestTransform.translate(ImageUtils.fullToZoomed(this.centerOffset.x, this.discardLevel), ImageUtils.fullToZoomed(this.centerOffset.y, this.discardLevel));
        this.requestTransform.translate(ImageUtils.fullToZoomed(this.requestOffset.x, this.discardLevel), ImageUtils.fullToZoomed(this.requestOffset.y, this.discardLevel));
    }
    
    public AffineTransform getOverviewImageTransform(final int n) {
        final AffineTransform affineTransform = new AffineTransform();
        final Rectangle fullToZoomed = ImageUtils.fullToZoomed(this.displaySpace, n);
        final Dimension imageSize = this.getImageSize(n);
        final Point point = new Point((fullToZoomed.width - imageSize.width) / 2, (fullToZoomed.height - imageSize.height) / 2);
        affineTransform.translate(point.x, point.y);
        affineTransform.rotate(this.rotationAngle, fullToZoomed.x + fullToZoomed.width / 2, fullToZoomed.y + fullToZoomed.height / 2);
        return affineTransform;
    }
    
    public int getBestFitDiscardLevel(final Dimension dimension) {
        int n = this.imageSize.length - 1;
        for (int n2 = 0, n3 = 0; n2 == 0 && n3 < this.imageSize.length; ++n3) {
            if (this.imageSize[n3].width <= dimension.width || this.imageSize[n3].height <= dimension.height) {
                n = n3;
                n2 = 1;
            }
        }
        return n;
    }
    
    public Dimension[] getImageSizes() {
        return this.imageSize;
    }
    
    public Dimension getImageSize(final int n) {
        Dimension dimension;
        if (n < 0) {
            dimension = new Dimension(ImageUtils.fullToZoomed(this.imageSize[0].width, n), ImageUtils.fullToZoomed(this.imageSize[0].height, n));
        }
        else if (n >= this.imageSize.length) {
            dimension = this.imageSize[this.imageSize.length - 1];
        }
        else {
            dimension = this.imageSize[n];
        }
        return dimension;
    }
    
    protected Rectangle getBoundingRectangle(final Point[] array) {
        Rectangle rectangle = null;
        if (array != null && array.length > 0) {
            int n = array[0].x;
            int n2 = array[0].x;
            int n3 = array[0].y;
            int n4 = array[0].y;
            for (int i = 1; i < array.length; ++i) {
                n = Math.min(n, array[i].x);
                n2 = Math.max(n2, array[i].x);
                n3 = Math.min(n3, array[i].y);
                n4 = Math.max(n4, array[i].y);
            }
            rectangle = new Rectangle(n, n3, n2 - n + 1, n4 - n3 + 1);
        }
        return rectangle;
    }
    
    public Point imageToDisplay(final Point point) {
        final Point point2 = new Point(point);
        if (this.imageSpaceTransform != null) {
            this.imageSpaceTransform.transform(point, point2);
        }
        return point2;
    }
    
    public Point displayToImage(final Point point) {
        final Point point2 = new Point(point);
        if (this.imageSpaceTransform != null) {
            try {
                this.imageSpaceTransform.inverseTransform(point, point2);
            }
            catch (NoninvertibleTransformException ex) {
                ex.printStackTrace();
            }
        }
        return point2;
    }
    
    public Point displayWindowToImage(final Point point) {
        return this.displayToImage(new Point(this.displayWindow.x + ImageUtils.zoomedToFull(point.x, this.discardLevel), this.displayWindow.y + ImageUtils.zoomedToFull(point.y, this.discardLevel)));
    }
}
