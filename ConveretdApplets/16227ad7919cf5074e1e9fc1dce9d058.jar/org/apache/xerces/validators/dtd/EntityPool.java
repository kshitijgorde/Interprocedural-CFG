// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.dtd;

import org.xml.sax.helpers.LocatorImpl;
import org.xml.sax.Locator;
import java.util.Vector;
import org.apache.xerces.framework.XMLErrorReporter;
import org.apache.xerces.utils.StringPool;

public final class EntityPool
{
    static final int CHUNK_SHIFT = 5;
    static final int CHUNK_SIZE = 32;
    static final int CHUNK_MASK = 31;
    static final int INITIAL_CHUNK_COUNT = 32;
    private StringPool fStringPool;
    private XMLErrorReporter fErrorReporter;
    private int fEntityCount;
    private int[][] fName;
    private int[][] fValue;
    private int[][] fLocation;
    private int[][] fPublicId;
    private int[][] fSystemId;
    private int[][] fNotationName;
    private byte[][] fDeclIsExternal;
    private int fNotationListHead;
    private boolean fCreateStandardEntities;
    private Vector fRequiredNotations;
    
    public EntityPool(final StringPool fStringPool, final XMLErrorReporter fErrorReporter, final boolean fCreateStandardEntities) {
        this.fName = new int[32][];
        this.fValue = new int[32][];
        this.fLocation = new int[32][];
        this.fPublicId = new int[32][];
        this.fSystemId = new int[32][];
        this.fNotationName = new int[32][];
        this.fDeclIsExternal = new byte[32][];
        this.fNotationListHead = -1;
        this.fCreateStandardEntities = false;
        this.fStringPool = fStringPool;
        this.fErrorReporter = fErrorReporter;
        this.fCreateStandardEntities = fCreateStandardEntities;
        if (this.fCreateStandardEntities) {
            this.createInternalEntity("lt", "&#60;");
            this.createInternalEntity("gt", ">");
            this.createInternalEntity("amp", "&#38;");
            this.createInternalEntity("apos", "'");
            this.createInternalEntity("quot", "\"");
        }
    }
    
    public void reset(final StringPool fStringPool) {
        this.fStringPool = fStringPool;
        this.fEntityCount = 0;
        this.fNotationListHead = -1;
        if (this.fRequiredNotations != null) {
            this.fRequiredNotations.removeAllElements();
        }
        if (this.fCreateStandardEntities) {
            this.createInternalEntity("lt", "&#60;");
            this.createInternalEntity("gt", ">");
            this.createInternalEntity("amp", "&#38;");
            this.createInternalEntity("apos", "'");
            this.createInternalEntity("quot", "\"");
        }
    }
    
    private void createInternalEntity(final String s, final String s2) {
        final int n = this.fEntityCount >> 5;
        final int n2 = this.fEntityCount & 0x1F;
        this.ensureCapacity(n);
        this.fName[n][n2] = this.fStringPool.addSymbol(s);
        this.fValue[n][n2] = this.fStringPool.addString(s2);
        this.fLocation[n][n2] = -1;
        this.fPublicId[n][n2] = -1;
        this.fSystemId[n][n2] = -1;
        this.fNotationName[n][n2] = -1;
        ++this.fEntityCount;
    }
    
    private boolean ensureCapacity(final int n) {
        try {
            return this.fName[n][0] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            final int[][] fName = new int[n * 2][];
            System.arraycopy(this.fName, 0, fName, 0, n);
            this.fName = fName;
            final int[][] fValue = new int[n * 2][];
            System.arraycopy(this.fValue, 0, fValue, 0, n);
            this.fValue = fValue;
            final int[][] fLocation = new int[n * 2][];
            System.arraycopy(this.fLocation, 0, fLocation, 0, n);
            this.fLocation = fLocation;
            final int[][] fPublicId = new int[n * 2][];
            System.arraycopy(this.fPublicId, 0, fPublicId, 0, n);
            this.fPublicId = fPublicId;
            final int[][] fSystemId = new int[n * 2][];
            System.arraycopy(this.fSystemId, 0, fSystemId, 0, n);
            this.fSystemId = fSystemId;
            final int[][] fNotationName = new int[n * 2][];
            System.arraycopy(this.fNotationName, 0, fNotationName, 0, n);
            this.fNotationName = fNotationName;
            final byte[][] fDeclIsExternal = new byte[n * 2][];
            System.arraycopy(this.fDeclIsExternal, 0, fDeclIsExternal, 0, n);
            this.fDeclIsExternal = fDeclIsExternal;
        }
        catch (NullPointerException ex2) {}
        this.fName[n] = new int[32];
        this.fValue[n] = new int[32];
        this.fLocation[n] = new int[32];
        this.fPublicId[n] = new int[32];
        this.fSystemId[n] = new int[32];
        this.fNotationName[n] = new int[32];
        this.fDeclIsExternal[n] = new byte[32];
        return true;
    }
    
    public int addEntityDecl(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        final int n7 = this.fEntityCount >> 5;
        final int n8 = this.fEntityCount & 0x1F;
        this.ensureCapacity(n7);
        this.fName[n7][n8] = n;
        this.fValue[n7][n8] = n2;
        this.fLocation[n7][n8] = n3;
        this.fPublicId[n7][n8] = n4;
        this.fSystemId[n7][n8] = n5;
        this.fNotationName[n7][n8] = n6;
        this.fDeclIsExternal[n7][n8] = (byte)(b ? -128 : 0);
        return this.fEntityCount++;
    }
    
    public int addNotationDecl(final int n, final int n2, final int n3, final boolean b) {
        int n4;
        int n5;
        for (int i = this.fNotationListHead; i != -1; i = this.fValue[n4][n5]) {
            n4 = i >> 5;
            n5 = (i & 0x1F);
            if (this.fNotationName[n4][n5] == n) {
                return -1;
            }
        }
        final int n6 = this.fEntityCount >> 5;
        final int n7 = this.fEntityCount & 0x1F;
        this.ensureCapacity(n6);
        this.fName[n6][n7] = -1;
        this.fValue[n6][n7] = this.fNotationListHead;
        this.fPublicId[n6][n7] = n2;
        this.fSystemId[n6][n7] = n3;
        this.fNotationName[n6][n7] = n;
        this.fDeclIsExternal[n6][n7] = (byte)(b ? -128 : 0);
        return this.fNotationListHead = this.fEntityCount++;
    }
    
    public int lookupEntity(final int n) {
        if (n == -1) {
            return -1;
        }
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < this.fEntityCount; ++i) {
            if (this.fName[n2][n3] == n) {
                return i;
            }
            if (++n3 == 32) {
                ++n2;
                n3 = 0;
            }
        }
        return -1;
    }
    
    public boolean isExternalEntity(final int n) {
        return this.fValue[n >> 5][n & 0x1F] == -1;
    }
    
    public boolean isUnparsedEntity(final int n) {
        return this.fNotationName[n >> 5][n & 0x1F] != -1;
    }
    
    public boolean getEntityDeclIsExternal(final int n) {
        return this.fDeclIsExternal[n >> 5][n & 0x1F] < 0;
    }
    
    public int getEntityName(final int n) {
        return this.fName[n >> 5][n & 0x1F];
    }
    
    public int getEntityValue(final int n) {
        return this.fValue[n >> 5][n & 0x1F];
    }
    
    public int getEntityLocation(final int n) {
        return this.fLocation[n >> 5][n & 0x1F];
    }
    
    public int getPublicId(final int n) {
        return this.fPublicId[n >> 5][n & 0x1F];
    }
    
    public int getSystemId(final int n) {
        return this.fSystemId[n >> 5][n & 0x1F];
    }
    
    public int lookupNotation(final int n) {
        int n2;
        int n3;
        for (int i = this.fNotationListHead; i != -1; i = this.fValue[n2][n3]) {
            n2 = i >> 5;
            n3 = (i & 0x1F);
            if (this.fNotationName[n2][n3] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean getNotationDeclIsExternal(final int n) {
        return this.fDeclIsExternal[n >> 5][n & 0x1F] < 0;
    }
    
    public int getNotationName(final int n) {
        return this.fNotationName[n >> 5][n & 0x1F];
    }
    
    public void addRequiredNotation(final int n, final Locator locator, final int n2, final int n3, final Object[] array) {
        if (this.fRequiredNotations == null) {
            this.fRequiredNotations = new Vector();
        }
        for (int i = 0; i < this.fRequiredNotations.size(); ++i) {
            if (((RequiredNotation)this.fRequiredNotations.elementAt(i)).fNotationName == n) {
                return;
            }
        }
        this.fRequiredNotations.addElement(new RequiredNotation(n, locator, n2, n3, array));
    }
    
    public void checkRequiredNotations() throws Exception {
        if (this.fRequiredNotations == null) {
            return;
        }
        for (int i = 0; i < this.fRequiredNotations.size(); ++i) {
            final RequiredNotation requiredNotation = this.fRequiredNotations.elementAt(i);
            if (this.lookupNotation(requiredNotation.fNotationName) == -1) {
                this.fErrorReporter.reportError(requiredNotation.fLocator, "http://www.w3.org/TR/1998/REC-xml-19980210", requiredNotation.fMajorCode, requiredNotation.fMinorCode, requiredNotation.fArgs, 1);
            }
        }
    }
    
    class RequiredNotation
    {
        int fNotationName;
        LocatorImpl fLocator;
        int fMajorCode;
        int fMinorCode;
        Object[] fArgs;
        
        RequiredNotation(final int fNotationName, final Locator locator, final int fMajorCode, final int fMinorCode, final Object[] fArgs) {
            this.fNotationName = fNotationName;
            this.fLocator = new LocatorImpl(locator);
            this.fMajorCode = fMajorCode;
            this.fMinorCode = fMinorCode;
            this.fArgs = fArgs;
        }
    }
}
