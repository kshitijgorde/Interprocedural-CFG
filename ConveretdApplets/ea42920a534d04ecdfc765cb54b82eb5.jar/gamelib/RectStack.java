// 
// Decompiled by Procyon v0.5.30
// 

package gamelib;

import java.awt.Rectangle;

class RectStack
{
    static final int capacity = 1024;
    private Rectangle[] data;
    int size;
    
    RectStack() {
        this.data = new Rectangle[1024];
        for (int i = 0; i < 1024; ++i) {
            this.data[i] = new Rectangle();
        }
    }
    
    final void clear() {
        this.size = 0;
    }
    
    final Rectangle item(final int n) {
        return this.data[n];
    }
    
    final void mergeRects() {
        for (int i = 0; i < this.size; ++i) {
            for (int j = i + 1; j < this.size; ++j) {
                if (this.data[i].intersects(this.data[j])) {
                    this.data[i].add(this.data[j]);
                    this.remove(j);
                    j = i;
                }
            }
        }
    }
    
    final void push(final Rectangle rectangle) {
        final Rectangle rectangle2 = this.data[this.size];
        rectangle2.x = rectangle.x;
        rectangle2.y = rectangle.y;
        rectangle2.width = rectangle.width;
        rectangle2.height = rectangle.height;
        ++this.size;
    }
    
    final void remove(final int n) {
        final Rectangle rectangle = this.data[n];
        --this.size;
        System.arraycopy(this.data, n + 1, this.data, n, this.size - n);
        this.data[this.size] = rectangle;
    }
}
