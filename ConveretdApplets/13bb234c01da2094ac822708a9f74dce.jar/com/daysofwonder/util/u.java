// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.util.NoSuchElementException;

class u implements y
{
    int a;
    int b;
    final /* synthetic */ G c;
    
    private u(final G c) {
        this.c = c;
        this.a = 0;
        this.b = -1;
    }
    
    public boolean a() {
        return this.a != this.c.a();
    }
    
    public Object b() {
        try {
            final Object b = this.c.b(this.a);
            this.b = this.a++;
            return b;
        }
        catch (IndexOutOfBoundsException ex) {
            throw new NoSuchElementException();
        }
    }
    
    public void c() {
        if (this.b == -1) {
            throw new IllegalStateException();
        }
        try {
            this.c.c(this.b);
            if (this.b < this.a) {
                --this.a;
            }
            this.b = -1;
        }
        catch (IndexOutOfBoundsException ex) {}
    }
}
