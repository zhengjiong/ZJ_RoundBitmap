package com.zj.example.roundbitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by zj on 2017/5/1.
 */

public class Demo3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo3_layout);

        final ImageView imageView = (ImageView) findViewById(R.id.img);

        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Void... params) {
                int width = getResources().getDimensionPixelSize(R.dimen.img_width);
                int height = getResources().getDimensionPixelSize(R.dimen.img_height);
                Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.src);

                Canvas canvas = new Canvas(result);
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

                Rect bitmapRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                RectF imgRectF = new RectF(0, 0, width, height);

                Path path = new Path();
                /**
                 * Path.Direction有两个值：
                 * Path.Direction.CCW：是counter-clockwise缩写，指创建逆时针方向的矩形路径；
                 * Path.Direction.CW：是clockwise的缩写，指创建顺时针方向的矩形路径；
                 * 整个属性不是关键属性,不影响结果
                 */
                path.addRoundRect(imgRectF, 90, 90, Path.Direction.CW);


                /*
                Region.Op.UNION：显示两层。
                Region.Op.XOR：去交集，显示两层。
                Region.Op.DIFFERENCE：去交集，显示下层。
                Region.Op.INTERSECT：取交集，显示上层。
                */
                canvas.clipPath(path, Region.Op.INTERSECT);//根据path的形状来裁剪
                canvas.drawBitmap(bitmap, bitmapRect, imgRectF, paint);

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
