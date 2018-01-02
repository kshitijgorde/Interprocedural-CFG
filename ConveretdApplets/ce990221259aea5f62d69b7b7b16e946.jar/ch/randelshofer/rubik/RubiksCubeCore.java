// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik;

import java.util.EventListener;
import ch.randelshofer.util.Arrays;
import ch.randelshofer.gui.event.EventListenerList;

public class RubiksCubeCore implements Cloneable
{
    protected int[] cornerLoc;
    protected int[] cornerOrient;
    protected int[] edgeLoc;
    protected int[] edgeOrient;
    protected int[] sideLoc;
    protected int[] sideOrient;
    protected static final int[] SIDE_TRANSLATION;
    EventListenerList listenerList;
    private boolean quiet;
    protected static final int[][] EDGE_TRANSLATION;
    protected static final int[][] CORNER_TRANSLATION;
    private static final int[][] EDGE_SIDE_MAP;
    static /* synthetic */ Class class$ch$randelshofer$rubik$RubikListener;
    
    public RubiksCubeCore() {
        this.cornerLoc = new int[8];
        this.cornerOrient = new int[8];
        this.edgeLoc = new int[12];
        this.edgeOrient = new int[12];
        this.sideLoc = new int[6];
        this.sideOrient = new int[6];
        this.listenerList = new EventListenerList();
        this.reset();
    }
    
    public boolean equals(final Object o) {
        if (o == null || !(o instanceof RubiksCubeCore)) {
            return false;
        }
        final RubiksCubeCore rubiksCubeCore = (RubiksCubeCore)o;
        return Arrays.equals(rubiksCubeCore.cornerLoc, this.cornerLoc) && Arrays.equals(rubiksCubeCore.cornerOrient, this.cornerOrient) && Arrays.equals(rubiksCubeCore.edgeLoc, this.edgeLoc) && Arrays.equals(rubiksCubeCore.edgeOrient, this.edgeOrient) && Arrays.equals(rubiksCubeCore.sideLoc, this.sideLoc) && Arrays.equals(rubiksCubeCore.sideOrient, this.sideOrient);
    }
    
    public int hashCode() {
        int n = 0;
        for (int i = 0; i < this.cornerLoc.length; ++i) {
            n <<= 1 + this.cornerLoc[i];
        }
        for (int j = 0; j < this.edgeLoc.length; ++j) {
            n <<= 1 + this.edgeLoc[j];
        }
        return n;
    }
    
    public void reset() {
        for (int i = 0; i < 8; ++i) {
            this.cornerLoc[i] = i;
            this.cornerOrient[i] = 0;
        }
        for (int j = 0; j < 12; ++j) {
            this.edgeLoc[j] = j;
            this.edgeOrient[j] = 0;
        }
        for (int k = 0; k < 6; ++k) {
            this.sideLoc[k] = k;
            this.sideOrient[k] = 0;
        }
        this.fireRubikChanged(new RubikEvent(this, 0, 0, 0));
    }
    
    public boolean isSolved() {
        for (int i = 0; i < 8; ++i) {
            if (this.cornerLoc[i] != i) {
                return false;
            }
            if (this.cornerOrient[i] != 0) {
                return false;
            }
        }
        for (int j = 0; j < 12; ++j) {
            if (this.edgeLoc[j] != j) {
                return false;
            }
            if (this.edgeOrient[j] != 0) {
                return false;
            }
        }
        for (int k = 0; k < 6; ++k) {
            if (this.sideLoc[k] != k) {
                return false;
            }
            if (this.sideOrient[k] != 0) {
                return false;
            }
        }
        return true;
    }
    
    public void twistSide(final int n, final boolean b) {
        switch (n) {
            case 0: {
                this.transform(2, 4, b ? -1 : 1);
                break;
            }
            case 1: {
                this.transform(0, 4, b ? -1 : 1);
                break;
            }
            case 2: {
                this.transform(1, 1, b ? 1 : -1);
                break;
            }
            case 3: {
                this.transform(2, 1, b ? 1 : -1);
                break;
            }
            case 4: {
                this.transform(0, 1, b ? 1 : -1);
                break;
            }
            case 5: {
                this.transform(1, 4, b ? -1 : 1);
                break;
            }
        }
    }
    
    public void twistEdge(final int n, final boolean b) {
        switch (n) {
            case 0: {
                this.transform(2, 2, b ? -1 : 1);
                break;
            }
            case 1: {
                this.transform(0, 2, b ? -1 : 1);
                break;
            }
            case 2: {
                this.transform(1, 2, b ? 1 : -1);
                break;
            }
            case 3: {
                this.transform(2, 2, b ? 1 : -1);
                break;
            }
            case 4: {
                this.transform(0, 2, b ? 1 : -1);
                break;
            }
            case 5: {
                this.transform(1, 2, b ? -1 : 1);
                break;
            }
        }
    }
    
    public void transform(final int n, final int n2, final int n3) {
        synchronized (this) {
            if (n < 0 || n > 2) {
                throw new IllegalArgumentException("axis: " + n);
            }
            if (n2 < 0 || n2 > 7) {
                throw new IllegalArgumentException("layerMask: " + n2);
            }
            if (n3 < -2 || n3 > 2) {
                throw new IllegalArgumentException("angle: " + n3);
            }
            if (n3 == 0) {
                return;
            }
            final int n4 = (n3 == -2) ? 2 : ((n3 == -1) ? 0 : n3);
            Label_0322: {
                if ((n2 & 0x1) != 0x0) {
                    switch (n) {
                        case 0: {
                            switch (n4) {
                                case 0: {
                                    this.twistLeftClockwise();
                                    break;
                                }
                                case 1: {
                                    this.twistLeftCounterClockwise();
                                    break;
                                }
                                case 2: {
                                    this.twistLeftDouble();
                                    break;
                                }
                            }
                            break;
                        }
                        case 1: {
                            switch (n4) {
                                case 0: {
                                    this.twistBottomClockwise();
                                    break;
                                }
                                case 1: {
                                    this.twistBottomCounterClockwise();
                                    break;
                                }
                                case 2: {
                                    this.twistBottomDouble();
                                    break;
                                }
                            }
                            break;
                        }
                        case 2: {
                            switch (n4) {
                                case 0: {
                                    this.twistBackClockwise();
                                    break Label_0322;
                                }
                                case 1: {
                                    this.twistBackCounterClockwise();
                                    break Label_0322;
                                }
                                case 2: {
                                    this.twistBackDouble();
                                    break Label_0322;
                                }
                            }
                            break;
                        }
                    }
                }
            }
            Label_0498: {
                if ((n2 & 0x2) != 0x0) {
                    switch (n) {
                        case 0: {
                            switch (n4) {
                                case 0: {
                                    this.twistMiddleLeftClockwise();
                                    break;
                                }
                                case 1: {
                                    this.twistMiddleLeftCounterClockwise();
                                    break;
                                }
                                case 2: {
                                    this.twistMiddleLeftDouble();
                                    break;
                                }
                            }
                            break;
                        }
                        case 1: {
                            switch (n4) {
                                case 0: {
                                    this.twistMiddleBottomClockwise();
                                    break;
                                }
                                case 1: {
                                    this.twistMiddleBottomCounterClockwise();
                                    break;
                                }
                                case 2: {
                                    this.twistMiddleBottomDouble();
                                    break;
                                }
                            }
                            break;
                        }
                        case 2: {
                            switch (n4) {
                                case 0: {
                                    this.twistMiddleBackClockwise();
                                    break Label_0498;
                                }
                                case 1: {
                                    this.twistMiddleBackCounterClockwise();
                                    break Label_0498;
                                }
                                case 2: {
                                    this.twistMiddleBackDouble();
                                    break Label_0498;
                                }
                            }
                            break;
                        }
                    }
                }
            }
            Label_0674: {
                if ((n2 & 0x4) != 0x0) {
                    switch (n) {
                        case 0: {
                            switch (n4) {
                                case 0: {
                                    this.twistRightCounterClockwise();
                                    break;
                                }
                                case 1: {
                                    this.twistRightClockwise();
                                    break;
                                }
                                case 2: {
                                    this.twistRightDouble();
                                    break;
                                }
                            }
                            break;
                        }
                        case 1: {
                            switch (n4) {
                                case 0: {
                                    this.twistTopCounterClockwise();
                                    break;
                                }
                                case 1: {
                                    this.twistTopClockwise();
                                    break;
                                }
                                case 2: {
                                    this.twistTopDouble();
                                    break;
                                }
                            }
                            break;
                        }
                        case 2: {
                            switch (n4) {
                                case 0: {
                                    this.twistFrontCounterClockwise();
                                    break Label_0674;
                                }
                                case 1: {
                                    this.twistFrontClockwise();
                                    break Label_0674;
                                }
                                case 2: {
                                    this.twistFrontDouble();
                                    break Label_0674;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        this.fireRubikTwisted(new RubikEvent(this, n, n2, n3));
    }
    
    public void transform(final RubiksCubeCore rubiksCubeCore) {
        final int[] array = this.cornerLoc.clone();
        final int[] array2 = this.cornerOrient.clone();
        for (int i = 0; i < rubiksCubeCore.cornerLoc.length; ++i) {
            this.cornerLoc[i] = array[rubiksCubeCore.cornerLoc[i]];
            this.cornerOrient[i] = (array2[rubiksCubeCore.cornerLoc[i]] + rubiksCubeCore.cornerOrient[i]) % 3;
        }
        final int[] array3 = this.edgeLoc.clone();
        final int[] array4 = this.edgeOrient.clone();
        for (int j = 0; j < rubiksCubeCore.edgeLoc.length; ++j) {
            this.edgeLoc[j] = array3[rubiksCubeCore.edgeLoc[j]];
            this.edgeOrient[j] = (array4[rubiksCubeCore.edgeLoc[j]] + rubiksCubeCore.edgeOrient[j]) % 2;
        }
        final int[] array5 = this.sideLoc.clone();
        final int[] array6 = this.sideOrient.clone();
        for (int k = 0; k < rubiksCubeCore.sideLoc.length; ++k) {
            this.sideLoc[k] = array5[rubiksCubeCore.sideLoc[k]];
            this.sideOrient[k] = (array6[rubiksCubeCore.sideLoc[k]] + rubiksCubeCore.sideOrient[k]) % 4;
        }
        this.fireRubikChanged(new RubikEvent(this, 0, 0, 0));
    }
    
    public void setTo(final RubiksCubeCore rubiksCubeCore) {
        System.arraycopy(rubiksCubeCore.sideLoc, 0, this.sideLoc, 0, this.sideLoc.length);
        System.arraycopy(rubiksCubeCore.sideOrient, 0, this.sideOrient, 0, this.sideOrient.length);
        System.arraycopy(rubiksCubeCore.edgeLoc, 0, this.edgeLoc, 0, this.edgeLoc.length);
        System.arraycopy(rubiksCubeCore.edgeOrient, 0, this.edgeOrient, 0, this.edgeOrient.length);
        System.arraycopy(rubiksCubeCore.cornerLoc, 0, this.cornerLoc, 0, this.cornerLoc.length);
        System.arraycopy(rubiksCubeCore.cornerOrient, 0, this.cornerOrient, 0, this.cornerOrient.length);
        this.fireRubikChanged(new RubikEvent(this, 0, 0, 0));
    }
    
    private void twistFrontDouble() {
        this.twistFrontClockwise();
        this.twistFrontClockwise();
    }
    
    private void twistFrontCounterClockwise() {
        this.twistFrontClockwise();
        this.twistFrontClockwise();
        this.twistFrontClockwise();
    }
    
    private void twistFrontClockwise() {
        final int n = this.cornerLoc[0];
        this.cornerLoc[0] = this.cornerLoc[1];
        this.cornerLoc[1] = this.cornerLoc[3];
        this.cornerLoc[3] = this.cornerLoc[2];
        this.cornerLoc[2] = n;
        final int n2 = this.cornerOrient[0];
        this.cornerOrient[0] = (this.cornerOrient[1] + 1) % 3;
        this.cornerOrient[1] = (this.cornerOrient[3] + 2) % 3;
        this.cornerOrient[3] = (this.cornerOrient[2] + 1) % 3;
        this.cornerOrient[2] = (n2 + 2) % 3;
        final int n3 = this.edgeLoc[0];
        this.edgeLoc[0] = this.edgeLoc[1];
        this.edgeLoc[1] = this.edgeLoc[2];
        this.edgeLoc[2] = this.edgeLoc[4];
        this.edgeLoc[4] = n3;
        final int n4 = this.edgeOrient[0];
        this.edgeOrient[0] = (this.edgeOrient[1] + 1) % 2;
        this.edgeOrient[1] = (this.edgeOrient[2] + 1) % 2;
        this.edgeOrient[2] = (this.edgeOrient[4] + 1) % 2;
        this.edgeOrient[4] = (n4 + 1) % 2;
        this.sideOrient[0] = (this.sideOrient[0] + 3) % 4;
    }
    
    private void twistRightDouble() {
        this.twistRightClockwise();
        this.twistRightClockwise();
    }
    
    private void twistRightCounterClockwise() {
        this.twistRightClockwise();
        this.twistRightClockwise();
        this.twistRightClockwise();
    }
    
    private void twistRightClockwise() {
        final int n = this.cornerLoc[2];
        this.cornerLoc[2] = this.cornerLoc[3];
        this.cornerLoc[3] = this.cornerLoc[5];
        this.cornerLoc[5] = this.cornerLoc[4];
        this.cornerLoc[4] = n;
        final int n2 = this.cornerOrient[2];
        this.cornerOrient[2] = (this.cornerOrient[3] + 1) % 3;
        this.cornerOrient[3] = (this.cornerOrient[5] + 2) % 3;
        this.cornerOrient[5] = (this.cornerOrient[4] + 1) % 3;
        this.cornerOrient[4] = (n2 + 2) % 3;
        final int n3 = this.edgeLoc[3];
        this.edgeLoc[3] = this.edgeLoc[4];
        this.edgeLoc[4] = this.edgeLoc[5];
        this.edgeLoc[5] = this.edgeLoc[7];
        this.edgeLoc[7] = n3;
        final int n4 = this.edgeOrient[3];
        this.edgeOrient[3] = (this.edgeOrient[4] + 1) % 2;
        this.edgeOrient[4] = (this.edgeOrient[5] + 1) % 2;
        this.edgeOrient[5] = (this.edgeOrient[7] + 1) % 2;
        this.edgeOrient[7] = (n4 + 1) % 2;
        this.sideOrient[1] = (this.sideOrient[1] + 3) % 4;
    }
    
    private void twistLeftDouble() {
        this.twistLeftClockwise();
        this.twistLeftClockwise();
    }
    
    private void twistLeftCounterClockwise() {
        this.twistLeftClockwise();
        this.twistLeftClockwise();
        this.twistLeftClockwise();
    }
    
    private void twistLeftClockwise() {
        final int n = this.cornerLoc[0];
        this.cornerLoc[0] = this.cornerLoc[6];
        this.cornerLoc[6] = this.cornerLoc[7];
        this.cornerLoc[7] = this.cornerLoc[1];
        this.cornerLoc[1] = n;
        final int n2 = this.cornerOrient[0];
        this.cornerOrient[0] = (this.cornerOrient[6] + 2) % 3;
        this.cornerOrient[6] = (this.cornerOrient[7] + 1) % 3;
        this.cornerOrient[7] = (this.cornerOrient[1] + 2) % 3;
        this.cornerOrient[1] = (n2 + 1) % 3;
        final int n3 = this.edgeLoc[1];
        this.edgeLoc[1] = this.edgeLoc[9];
        this.edgeLoc[9] = this.edgeLoc[10];
        this.edgeLoc[10] = this.edgeLoc[11];
        this.edgeLoc[11] = n3;
        final int n4 = this.edgeOrient[1];
        this.edgeOrient[1] = (this.edgeOrient[9] + 1) % 2;
        this.edgeOrient[9] = (this.edgeOrient[10] + 1) % 2;
        this.edgeOrient[10] = (this.edgeOrient[11] + 1) % 2;
        this.edgeOrient[11] = (n4 + 1) % 2;
        this.sideOrient[4] = (this.sideOrient[4] + 3) % 4;
    }
    
    private void twistTopDouble() {
        this.twistTopClockwise();
        this.twistTopClockwise();
    }
    
    private void twistTopCounterClockwise() {
        this.twistTopClockwise();
        this.twistTopClockwise();
        this.twistTopClockwise();
    }
    
    private void twistTopClockwise() {
        final int n = this.cornerLoc[0];
        this.cornerLoc[0] = this.cornerLoc[2];
        this.cornerLoc[2] = this.cornerLoc[4];
        this.cornerLoc[4] = this.cornerLoc[6];
        this.cornerLoc[6] = n;
        final int n2 = this.cornerOrient[0];
        this.cornerOrient[0] = this.cornerOrient[2];
        this.cornerOrient[2] = this.cornerOrient[4];
        this.cornerOrient[4] = this.cornerOrient[6];
        this.cornerOrient[6] = n2;
        final int n3 = this.edgeLoc[0];
        this.edgeLoc[0] = this.edgeLoc[3];
        this.edgeLoc[3] = this.edgeLoc[6];
        this.edgeLoc[6] = this.edgeLoc[9];
        this.edgeLoc[9] = n3;
        final int n4 = this.edgeOrient[0];
        this.edgeOrient[0] = (this.edgeOrient[3] + 1) % 2;
        this.edgeOrient[3] = (this.edgeOrient[6] + 1) % 2;
        this.edgeOrient[6] = (this.edgeOrient[9] + 1) % 2;
        this.edgeOrient[9] = (n4 + 1) % 2;
        this.sideOrient[5] = (this.sideOrient[5] + 3) % 4;
    }
    
    private void twistBottomDouble() {
        this.twistBottomClockwise();
        this.twistBottomClockwise();
    }
    
    private void twistBottomCounterClockwise() {
        this.twistBottomClockwise();
        this.twistBottomClockwise();
        this.twistBottomClockwise();
    }
    
    private void twistBottomClockwise() {
        final int n = this.cornerLoc[1];
        this.cornerLoc[1] = this.cornerLoc[7];
        this.cornerLoc[7] = this.cornerLoc[5];
        this.cornerLoc[5] = this.cornerLoc[3];
        this.cornerLoc[3] = n;
        final int n2 = this.cornerOrient[1];
        this.cornerOrient[1] = this.cornerOrient[7];
        this.cornerOrient[7] = this.cornerOrient[5];
        this.cornerOrient[5] = this.cornerOrient[3];
        this.cornerOrient[3] = n2;
        final int n3 = this.edgeLoc[2];
        this.edgeLoc[2] = this.edgeLoc[11];
        this.edgeLoc[11] = this.edgeLoc[8];
        this.edgeLoc[8] = this.edgeLoc[5];
        this.edgeLoc[5] = n3;
        final int n4 = this.edgeOrient[2];
        this.edgeOrient[2] = (this.edgeOrient[11] + 1) % 2;
        this.edgeOrient[11] = (this.edgeOrient[8] + 1) % 2;
        this.edgeOrient[8] = (this.edgeOrient[5] + 1) % 2;
        this.edgeOrient[5] = (n4 + 1) % 2;
        this.sideOrient[2] = (this.sideOrient[2] + 3) % 4;
    }
    
    private void twistMiddleBackDouble() {
        this.twistMiddleFrontDouble();
    }
    
    private void twistMiddleBackCounterClockwise() {
        this.twistMiddleFrontClockwise();
    }
    
    private void twistMiddleBackClockwise() {
        this.twistMiddleFrontCounterClockwise();
    }
    
    private void twistMiddleFrontDouble() {
        this.twistMiddleFrontClockwise();
        this.twistMiddleFrontClockwise();
    }
    
    private void twistMiddleFrontCounterClockwise() {
        this.twistMiddleFrontClockwise();
        this.twistMiddleFrontClockwise();
        this.twistMiddleFrontClockwise();
    }
    
    private void twistMiddleFrontClockwise() {
        final int n = this.edgeLoc[3];
        this.edgeLoc[3] = this.edgeLoc[9];
        this.edgeLoc[9] = this.edgeLoc[11];
        this.edgeLoc[11] = this.edgeLoc[5];
        this.edgeLoc[5] = n;
        final int n2 = this.edgeOrient[3];
        this.edgeOrient[3] = (this.edgeOrient[9] + 1) % 2;
        this.edgeOrient[9] = (this.edgeOrient[11] + 1) % 2;
        this.edgeOrient[11] = (this.edgeOrient[5] + 1) % 2;
        this.edgeOrient[5] = (n2 + 1) % 2;
        final int n3 = this.sideLoc[1];
        this.sideLoc[1] = this.sideLoc[5];
        this.sideLoc[5] = this.sideLoc[4];
        this.sideLoc[4] = this.sideLoc[2];
        this.sideLoc[2] = n3;
        final int n4 = this.sideOrient[1];
        this.sideOrient[1] = (this.sideOrient[5] + 1) % 4;
        this.sideOrient[5] = (this.sideOrient[4] + 3) % 4;
        this.sideOrient[4] = (this.sideOrient[2] + 1) % 4;
        this.sideOrient[2] = (n4 + 3) % 4;
    }
    
    private void twistMiddleBottomDouble() {
        this.twistMiddleTopDouble();
    }
    
    private void twistMiddleBottomCounterClockwise() {
        this.twistMiddleTopClockwise();
    }
    
    private void twistMiddleBottomClockwise() {
        this.twistMiddleTopCounterClockwise();
    }
    
    private void twistMiddleTopDouble() {
        this.twistMiddleTopClockwise();
        this.twistMiddleTopClockwise();
    }
    
    private void twistMiddleTopCounterClockwise() {
        this.twistMiddleTopClockwise();
        this.twistMiddleTopClockwise();
        this.twistMiddleTopClockwise();
    }
    
    private void twistMiddleTopClockwise() {
        final int n = this.edgeLoc[1];
        this.edgeLoc[1] = this.edgeLoc[4];
        this.edgeLoc[4] = this.edgeLoc[7];
        this.edgeLoc[7] = this.edgeLoc[10];
        this.edgeLoc[10] = n;
        final int n2 = this.edgeOrient[1];
        this.edgeOrient[1] = (this.edgeOrient[4] + 1) % 2;
        this.edgeOrient[4] = (this.edgeOrient[7] + 1) % 2;
        this.edgeOrient[7] = (this.edgeOrient[10] + 1) % 2;
        this.edgeOrient[10] = (n2 + 1) % 2;
        final int n3 = this.sideLoc[0];
        this.sideLoc[0] = this.sideLoc[1];
        this.sideLoc[1] = this.sideLoc[3];
        this.sideLoc[3] = this.sideLoc[4];
        this.sideLoc[4] = n3;
        final int n4 = this.sideOrient[0];
        this.sideOrient[0] = (this.sideOrient[1] + 1) % 4;
        this.sideOrient[1] = (this.sideOrient[3] + 1) % 4;
        this.sideOrient[3] = (this.sideOrient[4] + 1) % 4;
        this.sideOrient[4] = (n4 + 1) % 4;
    }
    
    private void twistMiddleLeftDouble() {
        this.twistMiddleRightDouble();
    }
    
    private void twistMiddleLeftCounterClockwise() {
        this.twistMiddleRightClockwise();
    }
    
    private void twistMiddleLeftClockwise() {
        this.twistMiddleRightCounterClockwise();
    }
    
    private void twistMiddleRightDouble() {
        this.twistMiddleRightClockwise();
        this.twistMiddleRightClockwise();
    }
    
    private void twistMiddleRightCounterClockwise() {
        this.twistMiddleRightClockwise();
        this.twistMiddleRightClockwise();
        this.twistMiddleRightClockwise();
    }
    
    private void twistMiddleRightClockwise() {
        final int n = this.edgeLoc[0];
        this.edgeLoc[0] = this.edgeLoc[2];
        this.edgeLoc[2] = this.edgeLoc[8];
        this.edgeLoc[8] = this.edgeLoc[6];
        this.edgeLoc[6] = n;
        final int n2 = this.edgeOrient[0];
        this.edgeOrient[0] = (this.edgeOrient[2] + 1) % 2;
        this.edgeOrient[2] = (this.edgeOrient[8] + 1) % 2;
        this.edgeOrient[8] = (this.edgeOrient[6] + 1) % 2;
        this.edgeOrient[6] = (n2 + 1) % 2;
        final int n3 = this.sideLoc[0];
        this.sideLoc[0] = this.sideLoc[2];
        this.sideLoc[2] = this.sideLoc[3];
        this.sideLoc[3] = this.sideLoc[5];
        this.sideLoc[5] = n3;
        final int n4 = this.sideOrient[0];
        this.sideOrient[0] = (this.sideOrient[2] + 1) % 4;
        this.sideOrient[2] = (this.sideOrient[3] + 1) % 4;
        this.sideOrient[3] = (this.sideOrient[5] + 1) % 4;
        this.sideOrient[5] = (n4 + 1) % 4;
    }
    
    private void twistBackDouble() {
        this.twistBackClockwise();
        this.twistBackClockwise();
    }
    
    private void twistBackCounterClockwise() {
        final int n = this.cornerLoc[6];
        this.cornerLoc[6] = this.cornerLoc[7];
        this.cornerLoc[7] = this.cornerLoc[5];
        this.cornerLoc[5] = this.cornerLoc[4];
        this.cornerLoc[4] = n;
        final int n2 = this.cornerOrient[6];
        this.cornerOrient[6] = (this.cornerOrient[7] + 2) % 3;
        this.cornerOrient[7] = (this.cornerOrient[5] + 1) % 3;
        this.cornerOrient[5] = (this.cornerOrient[4] + 2) % 3;
        this.cornerOrient[4] = (n2 + 1) % 3;
        final int n3 = this.edgeLoc[10];
        this.edgeLoc[10] = this.edgeLoc[8];
        this.edgeLoc[8] = this.edgeLoc[7];
        this.edgeLoc[7] = this.edgeLoc[6];
        this.edgeLoc[6] = n3;
        final int n4 = this.edgeOrient[10];
        this.edgeOrient[10] = (this.edgeOrient[8] + 1) % 2;
        this.edgeOrient[8] = (this.edgeOrient[7] + 1) % 2;
        this.edgeOrient[7] = (this.edgeOrient[6] + 1) % 2;
        this.edgeOrient[6] = (n4 + 1) % 2;
        this.sideOrient[3] = (this.sideOrient[3] + 1) % 4;
    }
    
    private void twistBackClockwise() {
        final int n = this.cornerLoc[4];
        this.cornerLoc[4] = this.cornerLoc[5];
        this.cornerLoc[5] = this.cornerLoc[7];
        this.cornerLoc[7] = this.cornerLoc[6];
        this.cornerLoc[6] = n;
        final int n2 = this.cornerOrient[4];
        this.cornerOrient[4] = (this.cornerOrient[5] + 1) % 3;
        this.cornerOrient[5] = (this.cornerOrient[7] + 2) % 3;
        this.cornerOrient[7] = (this.cornerOrient[6] + 1) % 3;
        this.cornerOrient[6] = (n2 + 2) % 3;
        final int n3 = this.edgeLoc[6];
        this.edgeLoc[6] = this.edgeLoc[7];
        this.edgeLoc[7] = this.edgeLoc[8];
        this.edgeLoc[8] = this.edgeLoc[10];
        this.edgeLoc[10] = n3;
        final int n4 = this.edgeOrient[6];
        this.edgeOrient[6] = (this.edgeOrient[7] + 1) % 2;
        this.edgeOrient[7] = (this.edgeOrient[8] + 1) % 2;
        this.edgeOrient[8] = (this.edgeOrient[10] + 1) % 2;
        this.edgeOrient[10] = (n4 + 1) % 2;
        this.sideOrient[3] = (this.sideOrient[3] + 3) % 4;
    }
    
    private void rotateFrontClockwise() {
        this.twistFrontClockwise();
        this.twistMiddleFrontClockwise();
        this.twistBackCounterClockwise();
    }
    
    private void rotateFrontCounterClockwise() {
        this.twistFrontCounterClockwise();
        this.twistMiddleFrontCounterClockwise();
        this.twistBackClockwise();
    }
    
    private void rotateFrontDouble() {
        this.twistFrontDouble();
        this.twistMiddleFrontDouble();
        this.twistBackDouble();
    }
    
    private void rotateBackClockwise() {
        this.rotateFrontCounterClockwise();
    }
    
    private void rotateBackCounterClockwise() {
        this.rotateFrontClockwise();
    }
    
    private void rotateBackDouble() {
        this.rotateFrontDouble();
    }
    
    private void rotateTopClockwise() {
        this.twistTopClockwise();
        this.twistMiddleTopClockwise();
        this.twistBottomCounterClockwise();
    }
    
    private void rotateTopCounterClockwise() {
        this.twistTopCounterClockwise();
        this.twistMiddleTopCounterClockwise();
        this.twistBottomClockwise();
    }
    
    private void rotateTopDouble() {
        this.twistTopDouble();
        this.twistMiddleTopDouble();
        this.twistBottomDouble();
    }
    
    private void rotateBottomClockwise() {
        this.rotateTopCounterClockwise();
    }
    
    private void rotateBottomCounterClockwise() {
        this.rotateTopClockwise();
    }
    
    private void rotateBottomDouble() {
        this.rotateTopDouble();
    }
    
    private void rotateRightClockwise() {
        this.twistRightClockwise();
        this.twistMiddleRightClockwise();
        this.twistLeftCounterClockwise();
    }
    
    private void rotateRightCounterClockwise() {
        this.twistRightCounterClockwise();
        this.twistMiddleRightCounterClockwise();
        this.twistLeftClockwise();
    }
    
    private void rotateRightDouble() {
        this.twistRightDouble();
        this.twistMiddleRightDouble();
        this.twistLeftDouble();
    }
    
    private void rotateLeftClockwise() {
        this.rotateRightCounterClockwise();
    }
    
    private void rotateLeftCounterClockwise() {
        this.rotateRightClockwise();
    }
    
    private void rotateLeftDouble() {
        this.rotateRightDouble();
    }
    
    public int[] getCornerLocations() {
        return this.cornerLoc;
    }
    
    public int[] getCornerOrientations() {
        return this.cornerOrient;
    }
    
    public void setCorners(final int[] cornerLoc, final int[] cornerOrient) {
        this.cornerLoc = cornerLoc;
        this.cornerOrient = cornerOrient;
        this.fireRubikChanged(new RubikEvent(this, 0, 0, 0));
    }
    
    public int getCornerAt(final int n) {
        return this.cornerLoc[n];
    }
    
    public int getCornerLocation(final int n) {
        if (this.cornerLoc[n] == n) {
            return n;
        }
        int n2;
        for (n2 = this.cornerLoc.length - 1; n2 >= 0 && this.cornerLoc[n2] != n; --n2) {}
        return n2;
    }
    
    public int getCornerOrientation(final int n) {
        return this.cornerOrient[this.getCornerLocation(n)];
    }
    
    public int[] getEdgeLocations() {
        return this.edgeLoc;
    }
    
    public int[] getEdgeOrientations() {
        return this.edgeOrient;
    }
    
    public void setEdges(final int[] edgeLoc, final int[] edgeOrient) {
        this.edgeLoc = edgeLoc;
        this.edgeOrient = edgeOrient;
        this.fireRubikChanged(new RubikEvent(this, 0, 0, 0));
    }
    
    public int getEdgeAt(final int n) {
        return this.edgeLoc[n];
    }
    
    public int getEdgeLocation(final int n) {
        if (this.edgeLoc[n] == n) {
            return n;
        }
        int n2;
        for (n2 = this.edgeLoc.length - 1; n2 >= 0 && this.edgeLoc[n2] != n; --n2) {}
        return n2;
    }
    
    public int getEdgeOrientation(final int n) {
        return this.edgeOrient[this.getEdgeLocation(n)];
    }
    
    public int[] getSideLocations() {
        return this.sideLoc;
    }
    
    public int[] getSideOrientations() {
        return this.sideOrient;
    }
    
    public void setSides(final int[] sideLoc, final int[] sideOrient) {
        this.sideLoc = sideLoc;
        this.sideOrient = sideOrient;
        this.fireRubikChanged(new RubikEvent(this, 0, 0, 0));
    }
    
    public int getSideAt(final int n) {
        return this.sideLoc[n];
    }
    
    public int getSideLocation(final int n) {
        if (this.sideLoc[n] == n) {
            return n;
        }
        int n2;
        for (n2 = this.sideLoc.length - 1; n2 >= 0 && this.sideLoc[n2] != n; --n2) {}
        return n2;
    }
    
    public int getSideOrientation(final int n) {
        return this.sideOrient[this.getSideLocation(n)];
    }
    
    public int getCubeOrientation() {
        switch (this.sideLoc[0] * 6 + this.sideLoc[1]) {
            case 1: {
                return 0;
            }
            case 13: {
                return 1;
            }
            case 19: {
                return 2;
            }
            case 31: {
                return 3;
            }
            case 9: {
                return 4;
            }
            case 22: {
                return 5;
            }
            case 24: {
                return 6;
            }
            case 5: {
                return 7;
            }
            case 4: {
                return 8;
            }
            case 2: {
                return 9;
            }
            case 11: {
                return 10;
            }
            case 34: {
                return 11;
            }
            case 26: {
                return 12;
            }
            case 6: {
                return 13;
            }
            case 27: {
                return 14;
            }
            case 8: {
                return 15;
            }
            case 16: {
                return 16;
            }
            case 29: {
                return 17;
            }
            case 12: {
                return 18;
            }
            case 15: {
                return 19;
            }
            case 20: {
                return 20;
            }
            case 23: {
                return 21;
            }
            case 33: {
                return 22;
            }
            case 30: {
                return 23;
            }
            default: {
                return -1;
            }
        }
    }
    
    public int getPartSide(final int n, final int n2) {
        if (n < 8) {
            return this.getCornerSide(n, n2);
        }
        if (n < 20) {
            return this.getEdgeSide(n - 8, n2);
        }
        if (n < 26) {
            return this.getSideLocation(n - 20);
        }
        return n2;
    }
    
    public int getCornerSide(final int n, final int n2) {
        final int cornerLocation = this.getCornerLocation(n);
        return RubiksCubeCore.CORNER_TRANSLATION[cornerLocation][(6 + n2 * 2 - this.cornerOrient[cornerLocation] * 2) % 6];
    }
    
    public int getEdgeSide(final int n, final int n2) {
        final int edgeLocation = this.getEdgeLocation(n);
        switch (n2) {
            case 0: {
                return RubiksCubeCore.EDGE_TRANSLATION[edgeLocation][(4 - this.edgeOrient[edgeLocation] * 2) % 4];
            }
            case 1: {
                return RubiksCubeCore.EDGE_TRANSLATION[edgeLocation][(6 - this.edgeOrient[edgeLocation] * 2) % 4];
            }
            default: {
                throw new IllegalArgumentException("invalid orientation:" + n2);
            }
        }
    }
    
    public int getEdgeLayerSide(final int n, final int n2) {
        final int edgeLocation = this.getEdgeLocation(n);
        return RubiksCubeCore.EDGE_SIDE_MAP[edgeLocation][(n2 + this.edgeOrient[edgeLocation]) % 2];
    }
    
    public void addRubikListener(final RubikListener rubikListener) {
        this.listenerList.add((RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener == null) ? (RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener = class$("ch.randelshofer.rubik.RubikListener")) : RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener, rubikListener);
    }
    
    public void removeRubikListener(final RubikListener rubikListener) {
        this.listenerList.remove((RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener == null) ? (RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener = class$("ch.randelshofer.rubik.RubikListener")) : RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener, rubikListener);
    }
    
    protected void fireRubikTwisted(final RubikEvent rubikEvent) {
        if (!this.quiet) {
            final Object[] listenerList = this.listenerList.getListenerList();
            for (int i = listenerList.length - 2; i >= 0; i -= 2) {
                if (listenerList[i] == ((RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener == null) ? (RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener = class$("ch.randelshofer.rubik.RubikListener")) : RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener)) {
                    ((RubikListener)listenerList[i + 1]).rubikTwisting(rubikEvent);
                }
            }
            for (int j = listenerList.length - 2; j >= 0; j -= 2) {
                if (listenerList[j] == ((RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener == null) ? (RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener = class$("ch.randelshofer.rubik.RubikListener")) : RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener)) {
                    ((RubikListener)listenerList[j + 1]).rubikTwisted(rubikEvent);
                }
            }
        }
    }
    
    protected void fireRubikChanged(final RubikEvent rubikEvent) {
        if (!this.quiet) {
            final Object[] listenerList = this.listenerList.getListenerList();
            for (int i = listenerList.length - 2; i >= 0; i -= 2) {
                if (listenerList[i] == ((RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener == null) ? (RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener = class$("ch.randelshofer.rubik.RubikListener")) : RubiksCubeCore.class$ch$randelshofer$rubik$RubikListener)) {
                    ((RubikListener)listenerList[i + 1]).rubikChanged(rubikEvent);
                }
            }
        }
    }
    
    public void setQuiet(final boolean quiet) {
        if (quiet != this.quiet && !(this.quiet = quiet)) {
            this.fireRubikChanged(new RubikEvent(this, 0, 0, 0));
        }
    }
    
    public Object clone() {
        try {
            final RubiksCubeCore rubiksCubeCore = (RubiksCubeCore)super.clone();
            rubiksCubeCore.cornerLoc = this.cornerLoc.clone();
            rubiksCubeCore.cornerOrient = this.cornerOrient.clone();
            rubiksCubeCore.edgeLoc = this.edgeLoc.clone();
            rubiksCubeCore.edgeOrient = this.edgeOrient.clone();
            rubiksCubeCore.sideLoc = this.sideLoc.clone();
            rubiksCubeCore.sideOrient = this.sideOrient.clone();
            rubiksCubeCore.listenerList = new EventListenerList();
            return rubiksCubeCore;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError(ex.getMessage());
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        SIDE_TRANSLATION = new int[] { 0, 1, 2, 3, 4, 5 };
        EDGE_TRANSLATION = new int[][] { { 0, 1, 5, 7 }, { 4, 5, 0, 3 }, { 0, 7, 2, 1 }, { 5, 5, 1, 1 }, { 1, 3, 0, 5 }, { 2, 5, 1, 7 }, { 3, 1, 5, 1 }, { 1, 5, 3, 3 }, { 3, 7, 2, 7 }, { 5, 3, 4, 1 }, { 4, 3, 3, 5 }, { 2, 3, 4, 7 } };
        CORNER_TRANSLATION = new int[][] { { 5, 6, 0, 0, 4, 2 }, { 2, 0, 4, 8, 0, 6 }, { 5, 8, 1, 0, 0, 2 }, { 2, 2, 0, 8, 1, 6 }, { 5, 2, 3, 0, 1, 2 }, { 2, 8, 1, 8, 3, 6 }, { 5, 0, 4, 0, 3, 2 }, { 2, 6, 3, 8, 4, 6 } };
        EDGE_SIDE_MAP = new int[][] { { 4, 1 }, { 5, 2 }, { 1, 4 }, { 3, 0 }, { 2, 5 }, { 0, 3 }, { 1, 4 }, { 5, 2 }, { 4, 1 }, { 0, 3 }, { 2, 5 }, { 3, 0 } };
    }
}
