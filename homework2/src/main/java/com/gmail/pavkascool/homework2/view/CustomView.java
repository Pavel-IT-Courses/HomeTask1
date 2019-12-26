package com.gmail.pavkascool.homework2.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

import androidx.annotation.Nullable;

public class CustomView extends View implements View.OnTouchListener {

    public static int[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
    private static Random random = new Random();

    private CustomListener customListener;

    private Paint paint;
    private RectF rect;

    int w, h;

    float radius;


    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        shuffleColors();
        paint = new Paint();
        setOnTouchListener(this);
    }


    public void shuffleColors() {
        for (int i = colors.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = colors[j];
            colors[j] = colors[i];
            colors[i] = temp;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        w = getWidth();
        h = getHeight();
        radius = Math.min(w, h) / 3;
        rect = new RectF(w / 2 - radius, h / 2 - radius, w / 2 + radius, h / 2 + radius);
        for (int i = 0; i < colors.length; i++) {
            paint.setColor(colors[i]);
            canvas.drawArc(rect, i * 90, 90, true, paint);
        }
        paint.setColor(Color.GRAY);
        canvas.drawCircle(w / 2, h / 2, radius / 3, paint);


    }


    public void setCustomListener(CustomListener customListener) {
        this.customListener = customListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            float cX = x - w / 2;
            float cY = y - h / 2;

            float r = (float) Math.sqrt(cX * cX + cY * cY);
            if (r > radius) {
                return false;
            }
            if (r < radius / 3) {
                shuffleColors();
                invalidate();
                return true;
            }

            int index = 0;
            if (cX > 0 && cY > 0) index = 0;
            if (cX < 0 && cY > 0) index = 1;
            if (cX < 0 && cY < 0) index = 2;
            if (cX > 0 && cY < 0) index = 3;

            customListener.onCustomTouchDown(this, index, x, y);
        }
        return true;
    }

}
