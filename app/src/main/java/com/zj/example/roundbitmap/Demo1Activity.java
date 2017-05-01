package com.zj.example.roundbitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by zj on 2017/5/1.
 */

public class Demo1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo1_layout);

        final ImageView imageView = (ImageView) findViewById(R.id.img);

        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Void... params) {
                int width = getResources().getDimensionPixelSize(R.dimen.img_width);
                int height = getResources().getDimensionPixelSize(R.dimen.img_height);
                Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.src);
                Canvas canvas = new Canvas(result);//canvas就在图画在这个bitmap上

                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

                canvas.drawRoundRect(new RectF(0, 0, width, height), 90, 90, paint);

                /**
                 * PorterDuff.Mode.SRC_IN：取交集，显示上层。
                 * PorterDuff.Mode.DST_IN：取交集，显示下层。
                 */
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

                Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                Rect dst = new Rect(0, 0, width, height);
                /**
                 * 参数src代表,bitmap的尺寸
                 * dst是imageview的大小,
                 * 这样会自动自动缩放图片,虽然会有拉伸,但是整个图片都可以完整显示
                 */
                canvas.drawBitmap(bitmap, src, dst, paint);
                return result;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                imageView.setImageBitmap(bitmap);

            }
        }.execute();
    }
}
