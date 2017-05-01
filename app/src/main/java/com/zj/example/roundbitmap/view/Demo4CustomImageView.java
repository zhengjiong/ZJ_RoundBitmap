package com.zj.example.roundbitmap.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zj.example.roundbitmap.R;

/**
 * Created by zj on 2017/5/1.
 */
public class Demo4CustomImageView extends ImageView {

    public Demo4CustomImageView(Context context) {
        this(context, null);
    }

    public Demo4CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo4CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*
         * 将绘制操作保存到新的图层（更官方的说法应该是离屏缓存）我们将在1/3中学习到Canvas的全部用法这里就先follow me
         */
        /*int sc = canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(), null, Canvas.ALL_SAVE_FLAG);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.src);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setFilterBitmap(true);


        Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Rect dst = new Rect(0, 0, width, height);

        super.onDraw(canvas);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRoundRect(30f,30f, getMeasuredWidth()-30f, getMeasuredHeight()-30f,
                90, 90, paint);

        canvas.restoreToCount(sc);*/

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.src);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setFilterBitmap(true);
        paint.setColor(Color.WHITE);

        Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Rect dst = new Rect(0, 0, width, height);

        int saveCount = canvas.getSaveCount();
        canvas.save();
        super.onDraw(canvas);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRoundRect(new RectF(30, 30, getMeasuredWidth()-30, getMeasuredHeight() - 30), 90, 90, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saveCount);
    }
}
