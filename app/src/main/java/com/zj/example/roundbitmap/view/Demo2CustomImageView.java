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
public class Demo2CustomImageView extends AppCompatImageView {

    public Demo2CustomImageView(Context context) {
        this(context, null);
    }

    public Demo2CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo2CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*
         * 将绘制操作保存到新的图层（更官方的说法应该是离屏缓存）我们将在1/3中学习到Canvas的全部用法这里就先follow me
         */
        int sc = canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(), null, Canvas.ALL_SAVE_FLAG);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.src);
        Bitmap circleBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setFilterBitmap(true);


        Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Rect dst = new Rect(0, 0, width, height);


        // 先绘制dis目标图
        canvas.drawRoundRect(new RectF(0, 0, width, height), 90, 90, paint);

        // 设置混合模式
        /**
         * PorterDuff.Mode.SRC_IN：取交集，显示上层。
         * PorterDuff.Mode.DST_IN：取交集，显示下层。
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));


        // 再绘制src源图
        /**
         * 参数src代表,bitmap的尺寸
         * dst是imageview的大小,
         * 这样会自动自动缩放图片,虽然会有拉伸,但是整个图片都可以完整显示
         */
        canvas.drawBitmap(bitmap, src, dst, paint);



        // 还原混合模式
        paint.setXfermode(null);
        // 还原画布
        canvas.restoreToCount(sc);
    }
}
