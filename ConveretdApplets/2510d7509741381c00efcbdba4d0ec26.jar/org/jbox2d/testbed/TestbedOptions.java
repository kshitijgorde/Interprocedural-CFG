// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed;

import processing.core.PApplet;
import org.jbox2d.common.Color3f;
import java.lang.reflect.Field;
import org.jbox2d.common.Vec2;
import java.util.ArrayList;

public class TestbedOptions
{
    public static float checkboxSize;
    public static float padding;
    public static float sliderWidth;
    public float borderWidth;
    public TestbedMain p;
    public ArrayList<Checkbox> checkBoxes;
    public ArrayList<SliderInt> sliderInts;
    public String titleString;
    public TestSettings settings;
    
    static {
        TestbedOptions.checkboxSize = 15.0f;
        TestbedOptions.padding = 5.0f;
        TestbedOptions.sliderWidth = 200.0f;
    }
    
    public TestbedOptions(final TestbedMain _p) {
        this.borderWidth = 50.0f;
        this.titleString = "*** TESTBED OPTIONS - press 'o' to return to test ***";
        this.p = _p;
        this.checkBoxes = new ArrayList<Checkbox>();
        this.sliderInts = new ArrayList<SliderInt>();
    }
    
    public void initialize(final AbstractExample test) {
        this.settings = test.settings;
        this.checkBoxes.clear();
        this.sliderInts.clear();
        try {
            final Class<?> myClass = this.settings.getClass();
            final Field[] fields = myClass.getFields();
            final Vec2 pos = new Vec2(this.borderWidth + TestbedOptions.padding, this.borderWidth + 2.0f * TestbedOptions.padding + 2.0f * TestbedOptions.checkboxSize);
            for (int i = 0; i < fields.length; ++i) {
                if (fields[i].getType().getCanonicalName().equals("boolean")) {
                    final String fieldName = fields[i].getName();
                    final boolean initialV = fields[i].getBoolean(this.settings);
                    final Checkbox myCheck = new Checkbox(pos, fieldName, initialV, fields[i]);
                    this.checkBoxes.add(myCheck);
                    final Vec2 vec2 = pos;
                    vec2.y += TestbedOptions.checkboxSize + TestbedOptions.padding;
                    if (pos.y + TestbedOptions.checkboxSize + TestbedOptions.padding > this.p.height - this.borderWidth) {
                        pos.y = this.borderWidth + 2.0f * TestbedOptions.padding + 2.0f * TestbedOptions.checkboxSize;
                        final Vec2 vec3 = pos;
                        vec3.x += (this.p.width - 2.0f * this.borderWidth) * 0.5f;
                    }
                }
                else if (fields[i].getType().getCanonicalName().equals("int")) {
                    final String fieldName = fields[i].getName();
                    final int initialV2 = fields[i].getInt(this.settings);
                    int maxIntValue = 100;
                    if (fieldName.equals("hz")) {
                        maxIntValue = 200;
                    }
                    final SliderInt mySlider = new SliderInt(pos, TestbedOptions.sliderWidth, fieldName, initialV2, 1, maxIntValue, fields[i]);
                    this.sliderInts.add(mySlider);
                    final Vec2 vec4 = pos;
                    vec4.y += 2.0f * (TestbedOptions.checkboxSize + TestbedOptions.padding);
                    if (pos.y + TestbedOptions.checkboxSize + TestbedOptions.padding > this.p.height - this.borderWidth) {
                        pos.y = this.borderWidth + 2.0f * TestbedOptions.padding + 2.0f * TestbedOptions.checkboxSize;
                        final Vec2 vec5 = pos;
                        vec5.x += (this.p.width - 2.0f * this.borderWidth) * 0.5f;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void handleOptions() {
        this.p.fill(20.0f, 20.0f, 110.0f, 255.0f);
        this.p.stroke(255);
        this.p.rect(this.borderWidth, this.borderWidth, this.p.width - 2.0f * this.borderWidth, this.p.height - 2.0f * this.borderWidth);
        final ProcessingDebugDraw g = (ProcessingDebugDraw)this.p.currentTest.m_debugDraw;
        g.drawString(this.borderWidth + TestbedOptions.padding, this.borderWidth + TestbedOptions.padding + AbstractExample.textLineHeight, this.titleString, new Color3f(255.0f, 255.0f, 255.0f));
        for (int i = 0; i < this.checkBoxes.size(); ++i) {
            this.checkBoxes.get(i).process();
            this.checkBoxes.get(i).draw();
        }
        for (int i = 0; i < this.sliderInts.size(); ++i) {
            this.sliderInts.get(i).process();
            this.sliderInts.get(i).draw();
        }
    }
    
    class Checkbox
    {
        public String label;
        public boolean value;
        public Vec2 position;
        public Field attachedValue;
        
        public Checkbox(final Vec2 _position, final String _label, final boolean initialValue, final Field _attached) {
            this.position = _position.clone();
            this.label = _label;
            this.value = initialValue;
            this.attachedValue = _attached;
        }
        
        public void process() {
            if (TestbedOptions.this.p.mousePressed && !TestbedOptions.this.p.pmousePressed && this.isMouseOver()) {
                try {
                    this.value = !this.value;
                    this.attachedValue.setBoolean(TestbedOptions.this.p.currentTest.settings, this.value);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        private boolean isMouseOver() {
            return TestbedOptions.this.p.mouseX > this.position.x && TestbedOptions.this.p.mouseX < this.position.x + TestbedOptions.checkboxSize && TestbedOptions.this.p.mouseY > this.position.y && TestbedOptions.this.p.mouseY < this.position.y + TestbedOptions.checkboxSize;
        }
        
        public void draw() {
            if (this.isMouseOver()) {
                TestbedOptions.this.p.fill(155.0f, 155.0f, 155.0f, 200.0f);
            }
            else {
                TestbedOptions.this.p.fill(100.0f, 100.0f, 100.0f, 200.0f);
            }
            TestbedOptions.this.p.stroke(0);
            TestbedOptions.this.p.rect(this.position.x, this.position.y, TestbedOptions.checkboxSize, TestbedOptions.checkboxSize);
            if (this.value) {
                TestbedOptions.this.p.fill(0.0f, 0.0f, 0.0f);
                TestbedOptions.this.p.noStroke();
                TestbedOptions.this.p.ellipse(this.position.x + TestbedOptions.checkboxSize * 0.5f, this.position.y + TestbedOptions.checkboxSize * 0.5f, TestbedOptions.checkboxSize * 0.7f, TestbedOptions.checkboxSize * 0.7f);
            }
            final ProcessingDebugDraw g = (ProcessingDebugDraw)TestbedOptions.this.p.currentTest.m_debugDraw;
            final int lineHeight = AbstractExample.textLineHeight;
            g.drawString((int)(this.position.x + TestbedOptions.checkboxSize + TestbedOptions.padding * 2.0f), (int)(this.position.y + 0.5f * (TestbedOptions.checkboxSize + lineHeight)), this.label, new Color3f(255.0f, 255.0f, 255.0f));
        }
    }
    
    class SliderInt
    {
        public String label;
        public int value;
        public int minValue;
        public int maxValue;
        public float width;
        public Vec2 position;
        public Field attachedValue;
        
        public SliderInt(final Vec2 _position, final float _width, final String _label, final int initialValue, final int _minValue, final int _maxValue, final Field _attached) {
            this.position = _position.clone();
            this.label = _label;
            this.value = initialValue;
            this.minValue = _minValue;
            this.maxValue = _maxValue;
            this.attachedValue = _attached;
            this.width = _width;
        }
        
        public void process() {
            if (TestbedOptions.this.p.mousePressed && this.isMouseOver()) {
                try {
                    this.value = PApplet.floor(PApplet.map((float)TestbedOptions.this.p.mouseX, this.position.x, this.position.x + this.width, (float)this.minValue, (float)this.maxValue));
                    this.attachedValue.setInt(TestbedOptions.this.p.currentTest.settings, this.value);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        private boolean isMouseOver() {
            return TestbedOptions.this.p.mouseX > this.position.x && TestbedOptions.this.p.mouseX < this.position.x + this.width && TestbedOptions.this.p.mouseY > this.position.y && TestbedOptions.this.p.mouseY < this.position.y + TestbedOptions.checkboxSize;
        }
        
        public void draw() {
            if (this.isMouseOver()) {
                TestbedOptions.this.p.fill(125.0f, 125.0f, 125.0f, 220.0f);
            }
            else {
                TestbedOptions.this.p.fill(100.0f, 100.0f, 100.0f, 200.0f);
            }
            TestbedOptions.this.p.stroke(0);
            TestbedOptions.this.p.rect(this.position.x, this.position.y, this.width, TestbedOptions.checkboxSize);
            TestbedOptions.this.p.ellipse(PApplet.map((float)this.value, (float)this.minValue, (float)this.maxValue, this.position.x, this.position.x + this.width), this.position.y + TestbedOptions.checkboxSize * 0.5f, 0.7f * TestbedOptions.checkboxSize, 0.7f * TestbedOptions.checkboxSize);
            final ProcessingDebugDraw g = (ProcessingDebugDraw)TestbedOptions.this.p.currentTest.m_debugDraw;
            final int lineHeight = AbstractExample.textLineHeight;
            g.drawString(this.position.x + TestbedOptions.padding, this.position.y + (TestbedOptions.checkboxSize + lineHeight), String.valueOf(this.label) + ": " + this.value, new Color3f(255.0f, 255.0f, 255.0f));
        }
    }
}
