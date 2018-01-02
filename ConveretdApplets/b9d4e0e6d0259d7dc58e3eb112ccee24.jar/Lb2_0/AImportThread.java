// 
// Decompiled by Procyon v0.5.30
// 

package Lb2_0;

import java.io.IOException;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class AImportThread extends Thread
{
    int iTextCount;
    int iPageCount;
    int iChar;
    int iCount;
    int iTextLength;
    int[] iCrypt;
    int iCursorX;
    int iCursorY;
    int iTypeFace;
    int iTypeStyle;
    int iTypeSize;
    int iPageNumber;
    String currentString;
    String currentWord;
    char cChar;
    URL aBase;
    URL fFile;
    pageCanvas pC;
    vectorCanvas vC;
    
    public AImportThread(final vectorCanvas vCan, final URL aBase, final String sName) {
        try {
            this.fFile = new URL(aBase, sName);
        }
        catch (MalformedURLException ex) {}
        this.vC = vCan;
        this.iCrypt = new int[8];
        this.currentWord = "LAZYBEE";
        for (int i = 0; i < 7; ++i) {
            this.cChar = this.currentWord.charAt(i);
            this.iCrypt[i] = this.cChar;
        }
    }
    
    public void run() {
        try {
            final DataInputStream din = new DataInputStream(this.fFile.openStream());
            this.iPageCount = din.readChar();
            this.vC.putPageTotal(this.iPageCount);
            for (int i = 0; i < this.iPageCount + 1; ++i) {
                this.iTextCount = din.readChar();
                this.pC = new pageCanvas(this.iTextCount - 1);
                for (int j = 0; j < this.iTextCount; ++j) {
                    this.iTextLength = din.readChar();
                    this.iCount = 0;
                    this.currentString = "";
                    for (int k = 0; k < this.iTextLength; ++k) {
                        this.cChar = din.readChar();
                        this.iChar = this.cChar;
                        this.iChar -= this.iCrypt[this.iCount];
                        ++this.iCount;
                        if (this.iCount == 7) {
                            this.iCount = 0;
                        }
                        this.cChar = (char)this.iChar;
                        this.currentString += this.cChar;
                    }
                    this.iCursorX = din.readChar();
                    this.iCursorY = din.readChar();
                    this.iTypeFace = din.readChar();
                    this.iTypeStyle = din.readChar();
                    this.iTypeSize = din.readChar();
                    this.pC.putStuff(j, this.currentString, this.iTypeFace, this.iTypeStyle, this.iTypeSize, this.iCursorX, this.iCursorY);
                }
                this.iPageNumber = this.vC.addItem(this.pC, i);
                this.vC.putCurrentPage(this.iPageNumber);
                Thread.currentThread();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
        }
        catch (IOException exc) {
            System.err.println("Error: " + exc);
        }
    }
}
