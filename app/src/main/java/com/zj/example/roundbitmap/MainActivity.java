package com.zj.example.roundbitmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * http://gavinliu.cn/2016/04/12/Android-实现图片圆角显示的几种方式/
 *
 * 图片的圆角显示，从实现方法上讲分为两种做法：

 * 在图片上做
 * 在控件上做
 * 从代码上讲，均使用 Android Graphics API：

 * Xfermodes
 * Regions
 * Canvas.drawRoundRect
 * Canvas.clipPath
 * GradientDrawable.setCornerRadii
 *
 * Clip + Region
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listview);

        List<Map<String, ?>> data = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("name", "demo1-在图片上做:使用PorterDuffXfermode来实现圆角, 用new Canvas的方式");
        data.add(map);

        map = new HashMap<>();
        map.put("name", "demo2-在图片上做:使用PorterDuffXfermode来实现圆角, 用自定义控件的方式");
        data.add(map);


        map = new HashMap<>();
        map.put("name", "demo3-在图片上做:使用Clip + Region来实现圆角, 用new Canvas的方式");
        data.add(map);

        map = new HashMap<>();
        map.put("name", "demo4-在控件上做:使用PorterDuffXfermode来实现圆角, 用自定义控件的方式");
        data.add(map);

        listView.setAdapter(new SimpleAdapter(this,
                data,
                android.R.layout.simple_list_item_1, new String[]{"name"}, //第四个参数通过源码可以看出需要的是一个K值的字符串数组
                new int[]{android.R.id.text1}));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, Demo1Activity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, Demo2Activity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Demo3Activity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, Demo4Activity.class));
                        break;
                }
            }
        });
    }
}
