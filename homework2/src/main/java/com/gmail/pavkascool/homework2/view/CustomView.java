package com.gmail.pavkascool.homework2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class CustomView extends View {

    private ShapeDrawable shape;
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        shape = new ShapeDrawable(new OvalShape());
        shape.setIntrinsicHeight(160);
        shape.setIntrinsicWidth(160);
        shape.setBounds(new Rect(0, 0, 160, 160));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(50, 50, 80, new Paint());
    }
}
