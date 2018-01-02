// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import java.util.Vector;

public class ObjectArray
{
    private int m_minArraySize;
    private Vector m_Arrays;
    private _ObjectArray m_currentArray;
    private int m_nextSlot;
    
    public ObjectArray() {
        this.m_minArraySize = 10;
        this.m_Arrays = new Vector(200);
        this.init(10);
    }
    
    public ObjectArray(final int minArraySize) {
        this.m_minArraySize = 10;
        this.m_Arrays = new Vector(200);
        this.init(minArraySize);
    }
    
    private void init(final int size) {
        this.m_minArraySize = size;
        this.m_currentArray = new _ObjectArray(this.m_minArraySize);
    }
    
    public Object getAt(final int idx) {
        final int arrayIndx = idx / this.m_minArraySize;
        final int arrayOffset = idx - arrayIndx * this.m_minArraySize;
        if (arrayIndx < this.m_Arrays.size()) {
            final _ObjectArray a = this.m_Arrays.elementAt(arrayIndx);
            return a.objects[arrayOffset];
        }
        return this.m_currentArray.objects[arrayOffset];
    }
    
    public void setAt(final int idx, final Object obj) {
        final int arrayIndx = idx / this.m_minArraySize;
        final int arrayOffset = idx - arrayIndx * this.m_minArraySize;
        if (arrayIndx < this.m_Arrays.size()) {
            final _ObjectArray a = this.m_Arrays.elementAt(arrayIndx);
            a.objects[arrayOffset] = obj;
        }
        else {
            this.m_currentArray.objects[arrayOffset] = obj;
        }
    }
    
    public int append(final Object o) {
        if (this.m_nextSlot >= this.m_minArraySize) {
            this.m_Arrays.addElement(this.m_currentArray);
            this.m_nextSlot = 0;
            this.m_currentArray = new _ObjectArray(this.m_minArraySize);
        }
        this.m_currentArray.objects[this.m_nextSlot] = o;
        final int pos = this.m_Arrays.size() * this.m_minArraySize + this.m_nextSlot;
        ++this.m_nextSlot;
        return pos;
    }
    
    public static void main(final String[] args) {
        final String[] word = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty", "Twenty-One", "Twenty-Two", "Twenty-Three", "Twenty-Four", "Twenty-Five", "Twenty-Six", "Twenty-Seven", "Twenty-Eight", "Twenty-Nine", "Thirty", "Thirty-One", "Thirty-Two", "Thirty-Three", "Thirty-Four", "Thirty-Five", "Thirty-Six", "Thirty-Seven", "Thirty-Eight", "Thirty-Nine" };
        final ObjectArray m_ObjectArray = new ObjectArray();
        for (int x = 0; x < word.length; ++x) {
            System.out.print(" - " + m_ObjectArray.append(word[x]));
        }
        System.out.println("\n");
        for (int x2 = 0; x2 < word.length; ++x2) {
            final String s = (String)m_ObjectArray.getAt(x2);
            System.out.println(s);
        }
        System.out.println((String)m_ObjectArray.getAt(5));
        System.out.println((String)m_ObjectArray.getAt(10));
        System.out.println((String)m_ObjectArray.getAt(20));
        System.out.println((String)m_ObjectArray.getAt(2));
        System.out.println((String)m_ObjectArray.getAt(15));
        System.out.println((String)m_ObjectArray.getAt(30));
        System.out.println((String)m_ObjectArray.getAt(6));
        System.out.println((String)m_ObjectArray.getAt(8));
        System.out.println((String)m_ObjectArray.getAt(40));
    }
    
    class _ObjectArray
    {
        public Object[] objects;
        
        public _ObjectArray(final int size) {
            this.objects = new Object[size];
        }
    }
}
