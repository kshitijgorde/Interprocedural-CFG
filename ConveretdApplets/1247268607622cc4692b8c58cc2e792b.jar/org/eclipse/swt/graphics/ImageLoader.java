// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import java.io.IOException;
import org.eclipse.swt.internal.Compatibility;
import java.io.OutputStream;
import org.eclipse.swt.internal.image.FileFormat;
import org.eclipse.swt.SWT;
import java.io.InputStream;
import java.util.Vector;

public class ImageLoader
{
    public ImageData[] data;
    public int logicalScreenWidth;
    public int logicalScreenHeight;
    public int backgroundPixel;
    public int repeatCount;
    Vector imageLoaderListeners;
    
    public ImageLoader() {
        this.reset();
    }
    
    void reset() {
        this.data = null;
        this.logicalScreenWidth = 0;
        this.logicalScreenHeight = 0;
        this.backgroundPixel = -1;
        this.repeatCount = 1;
    }
    
    public ImageData[] load(final InputStream inputStream) {
        if (inputStream == null) {
            SWT.error(4);
        }
        this.reset();
        return this.data = FileFormat.load(inputStream, this);
    }
    
    public ImageData[] load(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       8
        //     4: iconst_4       
        //     5: invokestatic    org/eclipse/swt/SWT.error:(I)V
        //     8: aconst_null    
        //     9: astore_2       
        //    10: aload_1        
        //    11: invokestatic    org/eclipse/swt/internal/Compatibility.newFileInputStream:(Ljava/lang/String;)Ljava/io/InputStream;
        //    14: astore_2       
        //    15: aload_0        
        //    16: aload_2        
        //    17: invokevirtual   org/eclipse/swt/graphics/ImageLoader.load:(Ljava/io/InputStream;)[Lorg/eclipse/swt/graphics/ImageData;
        //    20: astore          6
        //    22: jsr             46
        //    25: aload           6
        //    27: areturn        
        //    28: astore_3       
        //    29: bipush          39
        //    31: aload_3        
        //    32: invokestatic    org/eclipse/swt/SWT.error:(ILjava/lang/Throwable;)V
        //    35: goto            62
        //    38: astore          5
        //    40: jsr             46
        //    43: aload           5
        //    45: athrow         
        //    46: astore          4
        //    48: aload_2        
        //    49: ifnull          60
        //    52: aload_2        
        //    53: invokevirtual   java/io/InputStream.close:()V
        //    56: goto            60
        //    59: pop            
        //    60: ret             4
        //    62: jsr             46
        //    65: aconst_null    
        //    66: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     25     28     67     Ljava/io/IOException;
        //  10     25     38     46     Any
        //  28     35     38     46     Any
        //  62     65     38     46     Any
        //  48     56     59     60     Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 49, Size: 49
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3551)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void save(final OutputStream outputStream, final int n) {
        if (outputStream == null) {
            SWT.error(4);
        }
        FileFormat.save(outputStream, n, this);
    }
    
    public void save(final String s, final int n) {
        if (s == null) {
            SWT.error(4);
        }
        OutputStream fileOutputStream = null;
        try {
            fileOutputStream = Compatibility.newFileOutputStream(s);
        }
        catch (IOException ex) {
            SWT.error(39, ex);
        }
        this.save(fileOutputStream, n);
        try {
            fileOutputStream.close();
        }
        catch (IOException ex2) {}
    }
    
    public void addImageLoaderListener(final ImageLoaderListener imageLoaderListener) {
        if (imageLoaderListener == null) {
            SWT.error(4);
        }
        if (this.imageLoaderListeners == null) {
            this.imageLoaderListeners = new Vector();
        }
        this.imageLoaderListeners.addElement(imageLoaderListener);
    }
    
    public void removeImageLoaderListener(final ImageLoaderListener imageLoaderListener) {
        if (imageLoaderListener == null) {
            SWT.error(4);
        }
        if (this.imageLoaderListeners == null) {
            return;
        }
        this.imageLoaderListeners.removeElement(imageLoaderListener);
    }
    
    public boolean hasListeners() {
        return this.imageLoaderListeners != null && this.imageLoaderListeners.size() > 0;
    }
    
    public void notifyListeners(final ImageLoaderEvent imageLoaderEvent) {
        if (!this.hasListeners()) {
            return;
        }
        for (int size = this.imageLoaderListeners.size(), i = 0; i < size; ++i) {
            ((ImageLoaderListener)this.imageLoaderListeners.elementAt(i)).imageDataLoaded(imageLoaderEvent);
        }
    }
}
