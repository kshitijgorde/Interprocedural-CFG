// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.title;

import java.util.Date;
import java.text.DateFormat;
import org.jfree.ui.Spacer;
import org.jfree.ui.VerticalAlignment;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import java.awt.Paint;
import java.awt.Color;
import java.awt.Font;
import java.util.Locale;

public class DateTitle extends TextTitle
{
    public DateTitle() {
        this(1);
    }
    
    public DateTitle(final int style) {
        this(style, Locale.getDefault(), new Font("Dialog", 0, 12), Color.black);
    }
    
    public DateTitle(final int style, final Locale locale, final Font font, final Paint paint) {
        this(style, locale, font, paint, RectangleEdge.BOTTOM, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, Title.DEFAULT_SPACER);
    }
    
    public DateTitle(final int style, final Locale locale, final Font font, final Paint paint, final RectangleEdge position, final HorizontalAlignment horizontalAlignment, final VerticalAlignment verticalAlignment, final Spacer spacer) {
        super(DateFormat.getDateInstance(style, locale).format(new Date()), font, paint, position, horizontalAlignment, verticalAlignment, spacer);
    }
    
    public void setDateFormat(final int style, final Locale locale) {
        this.setText(DateFormat.getDateInstance(style, locale).format(new Date()));
    }
}
