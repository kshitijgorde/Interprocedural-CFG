// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.draw;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;

public class DrawLine
{
    private Color color;
    private PatternMaker patternMaker;
    private boolean doDrawLine;
    private boolean isSpecialLine;
    private boolean centerPattern;
    
    public DrawLine() {
        this(1, 0, 1, null);
    }
    
    public DrawLine(final int stroke, final int gap, final int thickness, final Color color) {
        this.doDrawLine = true;
        this.isSpecialLine = false;
        this.centerPattern = false;
        this.color = color;
        this.patternMaker = this.createPixelDrawer(stroke, gap, thickness);
        if (this.patternMaker != null) {
            this.doDrawLine = true;
            if (!(this.patternMaker instanceof SolidLineMaker)) {
                this.isSpecialLine = true;
            }
            else {
                this.isSpecialLine = false;
            }
        }
        else {
            this.doDrawLine = false;
        }
    }
    
    public DrawLine(final PatternMaker pm, final Color color) {
        this.doDrawLine = true;
        this.isSpecialLine = false;
        this.centerPattern = false;
        this.color = color;
        this.patternMaker = pm;
        this.isSpecialLine = true;
    }
    
    protected PatternMaker createPixelDrawer(final int stroke, final int gap, final int thickness) {
        if (gap <= 0 && stroke > 0) {
            if (thickness > 1) {
                return new ThickLineMaker(thickness);
            }
            if (thickness == 1) {
                return new SolidLineMaker();
            }
        }
        else if (stroke > 0) {
            if (thickness > 1) {
                return new ThickDashedLineMaker(stroke, gap, thickness);
            }
            if (thickness == 1) {
                return new DashedLineMaker(stroke, gap);
            }
        }
        return null;
    }
    
    public void resetLineState() {
        if (this.patternMaker != null) {
            this.patternMaker.reset();
        }
    }
    
    public void centerPatternOnLine(final boolean center) {
        this.centerPattern = center;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public PatternMaker getPatternMaker() {
        return this.patternMaker;
    }
    
    public void drawLine(final Graphics graphics, final int x1, final int y1, final int x2, final int y2) {
        if (this.doDrawLine) {
            if (this.color != null) {
                graphics.setColor(this.color);
            }
            final Point p1 = new Point(x1, y1);
            final Point p2 = new Point(x2, y2);
            if (this.isSpecialLine) {
                this.drawSpecialLine(graphics, p1.x, p1.y, p2.x, p2.y, this.patternMaker);
            }
            else {
                graphics.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
    
    private void drawSpecialLine(final Graphics graphics, int x1, int y1, int x2, int y2, final PatternMaker pdrawer) {
        int dy_neg = 1;
        int dx_neg = 1;
        int switch_x_y = 0;
        int neg_slope = 0;
        int dx = x2 - x1;
        int dy = y2 - y1;
        if (this.centerPattern) {
            pdrawer.centerPattern(Math.max(Math.abs(dx), Math.abs(dy)));
        }
        if (this.color != null) {
            graphics.setColor(this.color);
        }
        if (dx == 0) {
            if (y2 < y1) {
                final int tempy = y1;
                y1 = y2;
                y2 = tempy;
            }
            pdrawer.setWidthDirection(true);
            for (int n = y1; n <= y2; ++n) {
                pdrawer.draw(graphics, x1, n);
            }
            return;
        }
        if (dy == 0) {
            if (x2 < x1) {
                final int tempx = x1;
                x1 = x2;
                x2 = tempx;
            }
            pdrawer.setWidthDirection(false);
            for (int n = x1; n <= x2; ++n) {
                pdrawer.draw(graphics, n, y1);
            }
            return;
        }
        float m = dy / dx;
        pdrawer.setWidthDirection(false);
        if (m > 1.0f || m < -1.0f) {
            int temp = x1;
            x1 = y1;
            y1 = temp;
            temp = x2;
            x2 = y2;
            y2 = temp;
            dx = x2 - x1;
            dy = y2 - y1;
            m = dy / dx;
            switch_x_y = 1;
            pdrawer.setWidthDirection(true);
        }
        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
            dx = x2 - x1;
            dy = y2 - y1;
            m = dy / dx;
        }
        if (m < 0.0f) {
            if (dy < 0) {
                dy_neg = -1;
                dx_neg = 1;
            }
            else {
                dy_neg = 1;
                dx_neg = -1;
            }
            neg_slope = 1;
        }
        int d = 2 * (dy * dy_neg) - dx * dx_neg;
        final int incrH = 2 * dy * dy_neg;
        final int incrHV = 2 * (dy * dy_neg - dx * dx_neg);
        int x3 = x1;
        int y3 = y1;
        int tempx = x3;
        int tempy = y3;
        if (switch_x_y == 1) {
            final int temp = x3;
            x3 = y3;
            y3 = temp;
        }
        pdrawer.draw(graphics, x3, y3);
        x3 = tempx;
        y3 = tempy;
        while (x3 < x2) {
            if (d <= 0) {
                ++x3;
                d += incrH;
            }
            else {
                d += incrHV;
                ++x3;
                if (neg_slope == 0) {
                    ++y3;
                }
                else {
                    --y3;
                }
            }
            tempx = x3;
            tempy = y3;
            if (switch_x_y == 1) {
                final int temp = x3;
                x3 = y3;
                y3 = temp;
            }
            pdrawer.draw(graphics, x3, y3);
            x3 = tempx;
            y3 = tempy;
        }
    }
    
    public LargePoint getDataPointYOnLineAtX(final double x, final LargePoint start, final LargePoint end) {
        if (x <= start.x) {
            return new LargePoint(start);
        }
        if (x >= end.x) {
            return new LargePoint(end);
        }
        final double m = (end.y - start.y) / (end.x - start.x);
        return new LargePoint(x, this.solveForY(start, m, x));
    }
    
    public boolean clipDataToExtents(final LargeRectangle clipRect, final LargePoint startPoint, final LargePoint endPoint) {
        final double m = (endPoint.y - startPoint.y) / (endPoint.x - startPoint.x);
        if (m != 0.0) {
            final LargeRectangle bounds = new LargeRectangle(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y), Math.abs(startPoint.x - endPoint.x), Math.abs(startPoint.y - endPoint.y));
            final LargeRectangle intersection = bounds.intersection(clipRect);
            final LargePoint tempStart = new LargePoint(startPoint);
            final LargePoint tempEnd = new LargePoint(endPoint);
            final boolean found = this.clipEnd(intersection, m, startPoint, tempEnd) && this.clipEnd(intersection, m, endPoint, tempStart);
            return found;
        }
        if (startPoint.y <= clipRect.y + clipRect.height && startPoint.y >= clipRect.y) {
            if (startPoint.x < clipRect.x) {
                startPoint.x = clipRect.x;
            }
            if (endPoint.x > clipRect.x + clipRect.width) {
                endPoint.x = clipRect.x + clipRect.width;
            }
            return true;
        }
        return false;
    }
    
    protected double solveForX(final LargePoint p, final double m, final double y) {
        return p.x - (p.y - y) / m;
    }
    
    protected double solveForY(final LargePoint p, final double m, final double x) {
        return p.y - m * (p.x - x);
    }
    
    private boolean RectContainsOrEquals(final LargeRectangle rect, final double x, final double y) {
        return rect.x <= x && rect.x + rect.width >= x && rect.y <= y && rect.y + rect.height >= y;
    }
    
    protected boolean clipEnd(final LargeRectangle clipRect, final double m, final LargePoint point, final LargePoint otherEnd) {
        boolean found = false;
        if (!clipRect.contains(point)) {
            final double yTarget = (point.y > otherEnd.y) ? (clipRect.y + clipRect.height) : clipRect.y;
            double tryX = this.solveForX(otherEnd, m, yTarget);
            double tryY;
            if (tryX < clipRect.x) {
                tryX = clipRect.x;
                tryY = this.solveForY(otherEnd, m, tryX);
            }
            else if (tryX > clipRect.x + clipRect.width) {
                tryX = clipRect.x + clipRect.width;
                tryY = this.solveForY(otherEnd, m, tryX);
            }
            else {
                tryY = yTarget;
            }
            if (this.RectContainsOrEquals(clipRect, tryX, tryY)) {
                point.x = tryX;
                point.y = tryY;
                found = true;
            }
        }
        else {
            found = true;
        }
        return found;
    }
    
    public static class SolidLineMaker implements PatternMaker
    {
        public void draw(final Graphics graphics, final int x, final int y) {
            graphics.drawLine(x, y, x, y);
        }
        
        public void setWidthDirection(final boolean direction) {
        }
        
        public void reset() {
        }
        
        public void centerPattern(final int length) {
        }
    }
    
    public static class DashedLineMaker implements PatternMaker
    {
        int count;
        int endcount;
        int stroke;
        int gap;
        boolean instroke;
        
        public DashedLineMaker(final int s, final int g) {
            this.stroke = s;
            this.gap = g;
            this.reset();
        }
        
        public void draw(final Graphics graphics, final int x, final int y) {
            if (this.instroke) {
                graphics.drawLine(x, y, x, y);
            }
            if (this.count >= this.endcount) {
                if (this.instroke) {
                    this.endcount = this.gap;
                }
                else {
                    this.endcount = this.stroke;
                }
                this.count = 0;
                this.instroke = !this.instroke;
            }
            ++this.count;
        }
        
        public void setWidthDirection(final boolean direction) {
        }
        
        public void reset() {
            this.count = 0;
            this.endcount = this.stroke;
            this.instroke = true;
        }
        
        public void centerPattern(final int length) {
            int leftOver = length % (this.stroke + this.gap);
            if (leftOver > this.stroke) {
                this.instroke = false;
                this.endcount = this.gap;
                leftOver -= this.stroke;
                this.count = this.gap - leftOver / 2;
            }
            else {
                this.instroke = true;
                this.endcount = this.stroke;
                leftOver = this.stroke - leftOver;
                this.count = leftOver / 2;
            }
        }
    }
    
    public static class ThickLineMaker implements PatternMaker
    {
        int thickness;
        boolean widthIsY;
        int shift;
        
        public ThickLineMaker(final int thick) {
            this.thickness = 1;
            this.widthIsY = true;
            this.shift = 0;
            this.thickness = thick;
        }
        
        public void draw(final Graphics graphics, final int x, final int y) {
            this.shift = 0;
            boolean add = true;
            if (this.widthIsY) {
                for (int i = 0; i < this.thickness; ++i) {
                    if (add) {
                        graphics.drawLine(x + this.shift, y, x + this.shift, y);
                        ++this.shift;
                    }
                    else {
                        graphics.drawLine(x - this.shift, y, x - this.shift, y);
                    }
                    add = !add;
                }
            }
            else {
                for (int i = 0; i < this.thickness; ++i) {
                    if (add) {
                        graphics.drawLine(x, y + this.shift, x, y + this.shift);
                        ++this.shift;
                    }
                    else {
                        graphics.drawLine(x, y - this.shift, x, y - this.shift);
                    }
                    add = !add;
                }
            }
        }
        
        public void setWidthDirection(final boolean direction) {
            this.widthIsY = direction;
        }
        
        public void reset() {
            this.shift = 0;
        }
        
        public void centerPattern(final int length) {
        }
    }
    
    public static class ThickDashedLineMaker extends ThickLineMaker
    {
        int count;
        int endcount;
        int stroke;
        int gap;
        boolean instroke;
        
        public ThickDashedLineMaker(final int s, final int g, final int thick) {
            super(thick);
            this.stroke = s;
            this.gap = g;
            this.reset();
        }
        
        public void draw(final Graphics graphics, final int x, final int y) {
            if (this.instroke) {
                super.draw(graphics, x, y);
            }
            if (this.count >= this.endcount) {
                if (this.instroke) {
                    this.endcount = this.gap;
                }
                else {
                    this.endcount = this.stroke;
                }
                this.count = 0;
                this.instroke = !this.instroke;
            }
            ++this.count;
        }
        
        public void reset() {
            super.reset();
            this.count = 0;
            this.endcount = this.stroke;
            this.instroke = true;
        }
        
        public void centerPattern(final int length) {
            int leftOver = length % (this.stroke + this.gap);
            if (leftOver > this.stroke) {
                this.instroke = false;
                this.endcount = this.gap;
                leftOver -= this.stroke;
                this.count = this.gap - leftOver / 2;
            }
            else {
                this.instroke = true;
                this.endcount = this.stroke;
                leftOver = this.stroke - leftOver;
                this.count = leftOver / 2;
            }
        }
    }
    
    public static class QuestionMarkMaker implements PatternMaker
    {
        int count;
        int endcount;
        int gap;
        int type;
        public static final int SMALL = 0;
        public static final int NORMAL = 1;
        public static final int BOLD = 2;
        
        public QuestionMarkMaker(final int g) {
            this(g, 1);
        }
        
        public QuestionMarkMaker(final int g, final int qtype) {
            this.gap = g;
            this.type = qtype;
            this.reset();
        }
        
        public void draw(final Graphics graphics, final int x, final int y) {
            if (this.count >= this.endcount) {
                switch (this.type) {
                    case 1: {
                        this.drawQuestionMark(graphics, x, y);
                        break;
                    }
                    case 2: {
                        this.drawBoldQuestionMark(graphics, x, y);
                        break;
                    }
                    case 0: {
                        this.drawSmallQuestionMark(graphics, x, y);
                        break;
                    }
                    default: {
                        this.drawQuestionMark(graphics, x, y);
                        break;
                    }
                }
                this.count = 0;
            }
            ++this.count;
        }
        
        private void drawSmallQuestionMark(final Graphics graphics, final int x, final int y) {
            graphics.drawLine(x, y - 3, x, y - 3);
            graphics.drawLine(x - 1, y - 2, x - 1, y - 2);
            graphics.drawLine(x + 1, y - 2, x + 1, y - 2);
            graphics.drawLine(x + 1, y - 1, x + 1, y - 1);
            graphics.drawLine(x, y, x, y);
            graphics.drawLine(x, y + 2, x, y + 2);
        }
        
        private void drawQuestionMark(final Graphics graphics, final int x, final int y) {
            graphics.drawLine(x, y - 3, x + 1, y - 3);
            graphics.drawLine(x - 1, y - 2, x - 1, y - 2);
            graphics.drawLine(x + 2, y - 2, x + 2, y - 2);
            graphics.drawLine(x + 2, y - 1, x + 2, y - 1);
            graphics.drawLine(x + 1, y, x + 1, y);
            graphics.drawLine(x, y + 1, x, y + 1);
            graphics.drawLine(x, y + 3, x, y + 3);
        }
        
        private void drawBoldQuestionMark(final Graphics graphics, final int x, final int y) {
            graphics.drawLine(x - 1, y - 4, x + 2, y - 4);
            graphics.drawLine(x - 2, y - 3, x - 1, y - 3);
            graphics.drawLine(x + 2, y - 3, x + 3, y - 3);
            graphics.drawLine(x - 2, y - 2, x - 1, y - 2);
            graphics.drawLine(x + 2, y - 2, x + 3, y - 2);
            graphics.drawLine(x + 1, y - 1, x + 2, y - 1);
            graphics.drawLine(x, y, x + 1, y);
            graphics.drawLine(x, y + 1, x + 1, y + 1);
            graphics.drawLine(x, y + 3, x + 1, y + 3);
            graphics.drawLine(x, y + 4, x + 1, y + 4);
        }
        
        public void setWidthDirection(final boolean direction) {
        }
        
        public void reset() {
            this.count = 0;
            this.endcount = this.gap;
        }
        
        public void centerPattern(final int length) {
            this.endcount = this.gap;
            if (length < this.gap) {
                this.count = this.gap - length / 2;
            }
            else {
                final int leftOver = length % this.gap;
                this.count = (this.gap - leftOver) / 2;
            }
        }
    }
    
    public interface PatternMaker
    {
        void draw(final Graphics p0, final int p1, final int p2);
        
        void reset();
        
        void centerPattern(final int p0);
        
        void setWidthDirection(final boolean p0);
    }
}
