// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import ch.randelshofer.util.SingletonEnumeration;
import java.util.Enumeration;
import ch.randelshofer.rubik.RubiksCubeCore;
import java.util.Vector;

public class PermutationNode extends ScriptNode
{
    private Vector sequence;
    private int sign;
    private int type;
    public static final int SIDE_PERMUTATION = 1;
    public static final int EDGE_PERMUTATION = 2;
    public static final int CORNER_PERMUTATION = 3;
    public static final int UNDEFINED = -1;
    private static final int[][] SIDE_SYMBOLS;
    private static final int[][] EDGE_SYMBOLS;
    private static final int[][] CORNER_SYMBOLS;
    
    public PermutationNode() {
        this.sequence = new Vector();
        this.sign = -1;
        this.type = -1;
        this.setAllowsChildren(false);
    }
    
    public PermutationNode(final int n, final int n2) {
        super(n, n2);
        this.sequence = new Vector();
        this.sign = -1;
        this.type = -1;
        this.setAllowsChildren(false);
    }
    
    public int getSymbol() {
        return 119;
    }
    
    public int getFullTurnCount() {
        return 0;
    }
    
    public int getQuarterTurnCount() {
        return 0;
    }
    
    public void addPermItem(final int type, final int n, final int[] array) {
        if (this.type == -1) {
            this.type = type;
        }
        if (this.type != type) {
            throw new IllegalArgumentException("Permutation of different part types is not supported.");
        }
        int sign = 0;
        switch (n) {
            case 92: {
                sign = 1;
                break;
            }
            case 93: {
                sign = 2;
                break;
            }
            case 91: {
                sign = 3;
                break;
            }
            default: {
                sign = 0;
                break;
            }
        }
        if (sign == 3) {
            if (type == 3) {
                sign = 2;
            }
            else if (type == 2) {
                sign = 1;
            }
        }
        if (this.sequence.size() == 0) {
            this.sign = sign;
        }
        else if (type != 1 && sign != 0) {
            throw new IllegalArgumentException("Illegal sign.");
        }
        final PermutationItem permutationItem = new PermutationItem();
        int location = -1;
        switch (type) {
            case 1: {
                switch (array[0]) {
                    case 85: {
                        location = 1;
                        break;
                    }
                    case 86: {
                        location = 5;
                        break;
                    }
                    case 90: {
                        location = 3;
                        break;
                    }
                    case 88: {
                        location = 4;
                        break;
                    }
                    case 89: {
                        location = 2;
                        break;
                    }
                    case 87: {
                        location = 0;
                        break;
                    }
                }
                permutationItem.location = location;
                permutationItem.orientation = ((this.sequence.size() == 0) ? 0 : sign);
                break;
            }
            case 2: {
                if (n != 0 && n != 91) {
                    throw new IllegalArgumentException("Illegal sign for edge part.");
                }
                final int min = Math.min(array[0], array[1]);
                final int max = Math.max(array[0], array[1]);
                final int n2 = array[0];
                int location2;
                boolean orientation;
                if (min == 86 && max == 87) {
                    location2 = 0;
                    orientation = (n2 == 86);
                }
                else if (min == 87 && max == 88) {
                    location2 = 1;
                    orientation = (n2 == 87);
                }
                else if (min == 87 && max == 89) {
                    location2 = 2;
                    orientation = (n2 == 89);
                }
                else if (min == 85 && max == 86) {
                    location2 = 3;
                    orientation = (n2 == 85);
                }
                else if (min == 85 && max == 87) {
                    location2 = 4;
                    orientation = (n2 == 87);
                }
                else if (min == 85 && max == 89) {
                    location2 = 5;
                    orientation = (n2 == 85);
                }
                else if (min == 86 && max == 90) {
                    location2 = 6;
                    orientation = (n2 == 86);
                }
                else if (min == 85 && max == 90) {
                    location2 = 7;
                    orientation = (n2 == 90);
                }
                else if (min == 89 && max == 90) {
                    location2 = 8;
                    orientation = (n2 == 89);
                }
                else if (min == 86 && max == 88) {
                    location2 = 9;
                    orientation = (n2 == 88);
                }
                else if (min == 88 && max == 90) {
                    location2 = 10;
                    orientation = (n2 == 90);
                }
                else {
                    if (min != 88 || max != 89) {
                        throw new IllegalArgumentException("Impossible edge part.");
                    }
                    location2 = 11;
                    orientation = (n2 == 88);
                }
                permutationItem.location = location2;
                permutationItem.orientation = (orientation ? 1 : 0);
                break;
            }
            case 3: {
                if (n == 93) {
                    throw new IllegalArgumentException("Illegal sign for corner part.");
                }
                int n3 = array[0];
                int n4 = array[1];
                int n5 = array[2];
                if (n3 > n4) {
                    final int n6 = n4;
                    n4 = n3;
                    n3 = n6;
                }
                if (n3 > n5) {
                    final int n7 = n5;
                    n5 = n3;
                    n3 = n7;
                }
                if (n4 > n5) {
                    final int n8 = n5;
                    n5 = n4;
                    n4 = n8;
                }
                int location3;
                int orientation2;
                if (n3 == 86 && n4 == 87 && n5 == 88) {
                    location3 = 0;
                    if (array[0] == 86) {
                        orientation2 = ((array[1] == 87) ? 0 : 3);
                    }
                    else if (array[0] == 87) {
                        orientation2 = ((array[1] == 88) ? 2 : 5);
                    }
                    else {
                        orientation2 = ((array[1] == 86) ? 1 : 4);
                    }
                }
                else if (n3 == 87 && n4 == 88 && n5 == 89) {
                    location3 = 1;
                    if (array[0] == 89) {
                        orientation2 = ((array[1] == 88) ? 0 : 3);
                    }
                    else if (array[0] == 88) {
                        orientation2 = ((array[1] == 87) ? 2 : 5);
                    }
                    else {
                        orientation2 = ((array[1] == 89) ? 1 : 4);
                    }
                }
                else if (n3 == 85 && n4 == 86 && n5 == 87) {
                    location3 = 2;
                    if (array[0] == 86) {
                        orientation2 = ((array[1] == 85) ? 0 : 3);
                    }
                    else if (array[0] == 85) {
                        orientation2 = ((array[1] == 87) ? 2 : 5);
                    }
                    else {
                        orientation2 = ((array[1] == 86) ? 1 : 4);
                    }
                }
                else if (n3 == 85 && n4 == 87 && n5 == 89) {
                    location3 = 3;
                    if (array[0] == 89) {
                        orientation2 = ((array[1] == 87) ? 0 : 3);
                    }
                    else if (array[0] == 87) {
                        orientation2 = ((array[1] == 85) ? 2 : 5);
                    }
                    else {
                        orientation2 = ((array[1] == 89) ? 1 : 4);
                    }
                }
                else if (n3 == 85 && n4 == 86 && n5 == 90) {
                    if (array[0] == 86) {
                        orientation2 = ((array[1] == 90) ? 0 : 3);
                    }
                    else if (array[0] == 90) {
                        orientation2 = ((array[1] == 85) ? 2 : 5);
                    }
                    else {
                        orientation2 = ((array[1] == 86) ? 1 : 4);
                    }
                    location3 = 4;
                }
                else if (n3 == 85 && n4 == 89 && n5 == 90) {
                    location3 = 5;
                    if (array[0] == 89) {
                        orientation2 = ((array[1] == 85) ? 0 : 3);
                    }
                    else if (array[0] == 85) {
                        orientation2 = ((array[1] == 90) ? 2 : 5);
                    }
                    else {
                        orientation2 = ((array[1] == 89) ? 1 : 4);
                    }
                }
                else if (n3 == 86 && n4 == 88 && n5 == 90) {
                    location3 = 6;
                    if (array[0] == 86) {
                        orientation2 = ((array[1] == 88) ? 0 : 3);
                    }
                    else if (array[0] == 88) {
                        orientation2 = ((array[1] == 90) ? 2 : 5);
                    }
                    else {
                        orientation2 = ((array[1] == 86) ? 1 : 4);
                    }
                }
                else {
                    if (n3 != 88 || n4 != 89 || n5 != 90) {
                        throw new IllegalArgumentException("Impossible corner part.");
                    }
                    location3 = 7;
                    if (array[0] == 89) {
                        orientation2 = ((array[1] == 90) ? 0 : 3);
                    }
                    else if (array[0] == 90) {
                        orientation2 = ((array[1] == 88) ? 2 : 5);
                    }
                    else {
                        orientation2 = ((array[1] == 89) ? 1 : 4);
                    }
                }
                permutationItem.location = location3;
                permutationItem.orientation = orientation2;
                for (int i = 0; i < this.sequence.size(); ++i) {
                    if (((PermutationItem)this.sequence.elementAt(i)).orientation / 3 != permutationItem.orientation / 3) {
                        throw new IllegalArgumentException("Corner permutation cannot be clockwise and anticlockwise at the same time.");
                    }
                }
                break;
            }
        }
        for (int j = 0; j < this.sequence.size(); ++j) {
            if (((PermutationItem)this.sequence.elementAt(j)).location == permutationItem.location) {
                throw new IllegalArgumentException("Illegal multiple occurence of same part.");
            }
        }
        this.sequence.addElement(permutationItem);
    }
    
    public int getPermItemCount() {
        return this.sequence.size();
    }
    
    public void applyTo(final RubiksCubeCore rubiksCubeCore, final boolean b) {
        if (b) {
            this.applyInverseTo(rubiksCubeCore);
        }
        else {
            this.applyTo(rubiksCubeCore);
        }
    }
    
    public void applyTo(final RubiksCubeCore rubiksCubeCore) {
        int[] array = null;
        int[] array2 = null;
        final PermutationItem[] array3 = new PermutationItem[this.sequence.size()];
        for (int i = 0; i < array3.length; ++i) {
            array3[i] = (PermutationItem)this.sequence.elementAt(i);
        }
        int n = 0;
        switch (this.type) {
            case 1: {
                n = 4;
                array = rubiksCubeCore.getSideLocations();
                array2 = rubiksCubeCore.getSideOrientations();
                break;
            }
            case 3: {
                n = 3;
                array = rubiksCubeCore.getCornerLocations();
                array2 = rubiksCubeCore.getCornerOrientations();
                break;
            }
            case 2: {
                n = 2;
                array = rubiksCubeCore.getEdgeLocations();
                array2 = rubiksCubeCore.getEdgeOrientations();
                break;
            }
        }
        int j;
        for (j = 0; j < array3.length - 1; ++j) {
            final int n2 = (array3[j + 1].orientation - array3[j].orientation + array2[array3[j].location]) % n;
            array2[array3[j].location] = ((n2 < 0) ? (n + n2) : n2);
        }
        final int n3 = (this.sign + array3[0].orientation - array3[j].orientation + array2[array3[j].location]) % n;
        array2[array3[j].location] = ((n3 < 0) ? (n + n3) : n3);
        final int n4 = array[array3[array3.length - 1].location];
        final int n5 = array2[array3[array3.length - 1].location];
        for (int k = array3.length - 1; k > 0; --k) {
            array[array3[k].location] = array[array3[k - 1].location];
            array2[array3[k].location] = array2[array3[k - 1].location];
        }
        array[array3[0].location] = n4;
        array2[array3[0].location] = n5;
        switch (this.type) {
            case 1: {
                rubiksCubeCore.setSides(array, array2);
                break;
            }
            case 3: {
                rubiksCubeCore.setCorners(array, array2);
                break;
            }
            case 2: {
                rubiksCubeCore.setEdges(array, array2);
                break;
            }
        }
    }
    
    public void applyInverseTo(final RubiksCubeCore rubiksCubeCore) {
        int[] array = null;
        int[] array2 = null;
        final PermutationItem[] array3 = new PermutationItem[this.sequence.size()];
        for (int i = 0; i < array3.length; ++i) {
            array3[i] = (PermutationItem)this.sequence.elementAt(i);
        }
        int n = 0;
        switch (this.type) {
            case 1: {
                n = 4;
                array = rubiksCubeCore.getSideLocations();
                array2 = rubiksCubeCore.getSideOrientations();
                break;
            }
            case 3: {
                n = 3;
                array = rubiksCubeCore.getCornerLocations();
                array2 = rubiksCubeCore.getCornerOrientations();
                break;
            }
            case 2: {
                n = 2;
                array = rubiksCubeCore.getEdgeLocations();
                array2 = rubiksCubeCore.getEdgeOrientations();
                break;
            }
        }
        int j;
        for (j = array3.length - 1; j > 0; --j) {
            final int n2 = (array3[j - 1].orientation - array3[j].orientation + array2[array3[j].location]) % n;
            array2[array3[j].location] = ((n2 < 0) ? (n + n2) : n2);
        }
        final int n3 = (-this.sign + array3[array3.length - 1].orientation - array3[j].orientation + array2[array3[j].location]) % n;
        array2[array3[j].location] = ((n3 < 0) ? (n + n3) : n3);
        final int n4 = array[array3[0].location];
        final int n5 = array2[array3[0].location];
        for (int k = 1; k < array3.length; ++k) {
            array[array3[k - 1].location] = array[array3[k].location];
            array2[array3[k - 1].location] = array2[array3[k].location];
        }
        array[array3[array3.length - 1].location] = n4;
        array2[array3[array3.length - 1].location] = n5;
        switch (this.type) {
            case 1: {
                rubiksCubeCore.setSides(array, array2);
                break;
            }
            case 3: {
                rubiksCubeCore.setCorners(array, array2);
                break;
            }
            case 2: {
                rubiksCubeCore.setEdges(array, array2);
                break;
            }
        }
    }
    
    public void inverse() {
        final Vector sequence = this.sequence;
        this.sequence = new Vector(sequence.size());
        if (sequence.size() > 0) {
            final PermutationItem permutationItem = sequence.elementAt(0);
            final PermutationItem permutationItem2 = new PermutationItem();
            permutationItem2.orientation = permutationItem.orientation;
            permutationItem2.location = permutationItem.location;
            this.sequence.addElement(permutationItem2);
        }
        for (int i = sequence.size() - 1; i >= 1; --i) {
            final PermutationItem permutationItem3 = sequence.elementAt(i);
            final PermutationItem permutationItem4 = new PermutationItem();
            permutationItem4.orientation = permutationItem3.orientation;
            permutationItem4.location = permutationItem3.location;
            this.sequence.addElement(permutationItem4);
        }
        switch (this.type) {
            case 1: {
                if (this.sign != 0) {
                    this.sign = 4 - this.sign;
                    for (int j = 1; j < this.sequence.size(); ++j) {
                        final PermutationItem permutationItem5 = this.sequence.elementAt(j);
                        permutationItem5.orientation = (this.sign + permutationItem5.orientation) % 4;
                    }
                    break;
                }
                break;
            }
            case 3: {
                if (this.sign != 0) {
                    this.sign = 3 - this.sign;
                    for (int k = 1; k < this.sequence.size(); ++k) {
                        final PermutationItem permutationItem6 = this.sequence.elementAt(k);
                        permutationItem6.orientation = (this.sign + permutationItem6.orientation) % 3;
                    }
                    break;
                }
                break;
            }
            case 2: {
                if (this.sign != 0) {
                    for (int l = 1; l < this.sequence.size(); ++l) {
                        final PermutationItem permutationItem7 = this.sequence.elementAt(l);
                        permutationItem7.orientation ^= this.sign;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    public void reflect() {
    }
    
    public Enumeration resolvedEnumeration(final boolean b) {
        if (b) {
            final PermutationNode permutationNode = (PermutationNode)this.clone();
            permutationNode.inverse();
            return new SingletonEnumeration(permutationNode);
        }
        return new SingletonEnumeration(this);
    }
    
    public void transform(final int n) {
        final RubiksCubeCore rubiksCubeCore = new RubiksCubeCore();
        final int axis = ScriptParser.getAxis(n);
        final int layerMask = ScriptParser.getLayerMask(n);
        final int angle = ScriptParser.getAngle(n);
        if (axis == -1 || angle == 0 || layerMask == -1) {
            return;
        }
        rubiksCubeCore.transform(axis, layerMask, angle);
        this.applyTo(rubiksCubeCore);
        rubiksCubeCore.transform(axis, layerMask, -angle);
        int[] array = null;
        int[] array2 = null;
        int n2 = 0;
        switch (this.type) {
            case 3: {
                n2 = 3;
                array = rubiksCubeCore.getCornerLocations();
                array2 = rubiksCubeCore.getCornerOrientations();
                break;
            }
            case 2: {
                n2 = 2;
                array = rubiksCubeCore.getEdgeLocations();
                array2 = rubiksCubeCore.getEdgeOrientations();
                break;
            }
            case 1: {
                n2 = 4;
                array = rubiksCubeCore.getSideLocations();
                array2 = rubiksCubeCore.getSideOrientations();
                break;
            }
        }
        this.sequence.removeAllElements();
        final boolean[] array3 = new boolean[array.length];
        int location;
        for (location = 0; location < array.length && array[location] == location && array2[location] == 0; ++location) {}
        final PermutationItem permutationItem = new PermutationItem();
        permutationItem.location = location;
        permutationItem.orientation = 0;
        this.sequence.addElement(permutationItem);
        array3[location] = true;
        int orientation = 0;
        int location2;
        for (location2 = 0; array[location2] != location; ++location2) {}
        while (!array3[location2]) {
            array3[location2] = true;
            orientation = (n2 + orientation + array2[location2]) % n2;
            final PermutationItem permutationItem2 = new PermutationItem();
            permutationItem2.location = location2;
            permutationItem2.orientation = orientation;
            this.sequence.addElement(permutationItem2);
            int n3;
            for (n3 = 0; array[n3] != location2; ++n3) {}
            location2 = n3;
        }
        this.sign = (n2 + orientation + array2[location]) % n2;
    }
    
    public Object clone() {
        final PermutationNode permutationNode = (PermutationNode)super.clone();
        permutationNode.sequence = new Vector();
        final Enumeration<PermutationItem> elements = this.sequence.elements();
        while (elements.hasMoreElements()) {
            permutationNode.sequence.addElement(elements.nextElement().clone());
        }
        return permutationNode;
    }
    
    static {
        SIDE_SYMBOLS = new int[][] { { 87 }, { 85 }, { 89 }, { 90 }, { 88 }, { 86 } };
        EDGE_SYMBOLS = new int[][] { { 87, 86 }, { 88, 87 }, { 87, 89 }, { 86, 85 }, { 85, 87 }, { 89, 85 }, { 90, 86 }, { 85, 90 }, { 90, 89 }, { 86, 88 }, { 88, 90 }, { 89, 88 } };
        CORNER_SYMBOLS = new int[][] { { 86, 87, 88 }, { 89, 88, 87 }, { 86, 85, 87 }, { 89, 87, 85 }, { 86, 90, 85 }, { 89, 85, 90 }, { 86, 88, 90 }, { 89, 90, 88 } };
    }
    
    private static class PermutationItem implements Cloneable
    {
        public int orientation;
        public int location;
        
        public Object clone() {
            try {
                return super.clone();
            }
            catch (CloneNotSupportedException ex) {
                throw new InternalError(ex.getMessage());
            }
        }
    }
}
