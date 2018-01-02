// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

public interface ab
{
    public static final String a = new String(charArray).intern();
    public static final String b = new String(charArray2).intern();
    
    void setName(final String p0);
    
    String getName();
    
    void setType(final String p0);
    
    String getType();
    
    double getPreFPS();
    
    double getAverFPS();
    
    void reCountFps();
    
    void addCamera(final d p0);
    
    d getCamera();
    
    void takePictureOfSelf();
    
    void takeEmptyPicture();
    
    void takeEmptyPicture(final d p0);
    
    void takePictureOfSelf(final d p0);
    
    void render();
    
    void setEnabled(final boolean p0);
    
    default static {
        final char[] charArray = "dhBt\u001cabBm\u001cE".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '7';
                            break;
                        }
                        case 1: {
                            c2 = '\u000b';
                            break;
                        }
                        case 2: {
                            c2 = '\'';
                            break;
                        }
                        case 3: {
                            c2 = '\u001a';
                            break;
                        }
                        default: {
                            c2 = 'y';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n > n3) {
                continue;
            }
            break;
        }
        final char[] charArray2 = "zjWL\u0010R|Bh".toCharArray();
        int length2;
        int n5;
        final int n4 = n5 = (length2 = charArray2.length);
        int n6 = 0;
        while (true) {
            Label_0210: {
                if (n4 > 1) {
                    break Label_0210;
                }
                length2 = (n5 = n6);
                do {
                    final char c3 = charArray2[n5];
                    char c4 = '\0';
                    switch (n6 % 5) {
                        case 0: {
                            c4 = '7';
                            break;
                        }
                        case 1: {
                            c4 = '\u000b';
                            break;
                        }
                        case 2: {
                            c4 = '\'';
                            break;
                        }
                        case 3: {
                            c4 = '\u001a';
                            break;
                        }
                        default: {
                            c4 = 'y';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n6;
                } while (n4 == 0);
            }
            if (n4 <= n6) {
                return;
            }
            continue;
        }
    }
}
