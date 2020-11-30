# 实验三-UI组件

实验准备：创建用于编写本次实验代码文件的空项目ExpWork3，每个题目按照先定义布局文件再编写activity的顺序，依次实现实验要求。

## 一、利用ListView+SimpleAdapter实现布局并通过Toast显示选中的Item

1、在activity_main.xml中创建一个ListView:

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <!--定义一个listview-->
    <ListView
        android:id="@+id/mylist"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
       />
</LinearLayout>
```

2、在simple_item.xml中定义每一个list的布局：

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="70dp"
        android:orientation="horizontal">
        <TextView
            android:id="@+id/animal"
            android:layout_width="80dp"
            android:layout_height="80dp"
            android:textSize="20dp"
            android:textStyle="bold"
            android:layout_marginTop="30dp"
            android:layout_marginLeft="10dp"/>
        <ImageView
            android:id="@+id/image"
            android:layout_width="60dp"
            android:layout_height="60dp"
            android:layout_marginEnd="10dp"
            android:layout_centerVertical="true"
            android:layout_alignParentEnd="true"
            />
    </RelativeLayout>
</RelativeLayout>
```

3、在MainActivity.java中：（这里只粘贴关键代码）

3.1、定义、存储动物名称和图片信息：

```
private String[] animals = new String[]{"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
    private Integer[] imageIds = new Integer[]{R.drawable.lion,R.drawable.tiger,R.drawable.monkey,
                                    R.drawable.dog,R.drawable.cat,R.drawable.elephant};
```

```
final List<Map<String,Object>> listItems = new ArrayList<>();
        for (int i = 0; i < animals.length; i++) {
            Map<String,Object> listItem = new HashMap<>();
            listItem.put("animalName",animals[i]);
            listItem.put("image",imageIds[i]);
            listItems.add(listItem);
        }
```

3.2、定义一个SimpleAdapter：

```
SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,
                                        R.layout.simple_item,new String[]{"animalName","image"},
                                        new int[]{R.id.animal,R.id.image});
        final ListView listView = findViewById(R.id.mylist);
        listView.setAdapter(simpleAdapter);
```

3.3、对ListView的点击事件监听并通过Toast显示选中的Item：

```
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast toast= Toast.makeText(context,animals[position],Toast.LENGTH_SHORT);
                toast.show();
            }
        });
```

4、实验效果截图
<br/>
<img src="https://i.loli.net/2020/11/30/vJQDNSVWf4l5mUo.jpg" style="zoom:33%;"  width="200" height="400" />

## 二、利用AlertDialog自定义对话框

1、在activity_second.xml中定义自定义对话框的布局效果：

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android" 		   android:layout_width="match_parent"
    android:layout_height="match_parent">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="center"
        android:background="#ffffff"
        android:orientation="vertical">

        <!--自定义对话框title-->
        <TextView
            android:id="@+id/tv"
            android:layout_width="match_parent"
            android:layout_height="40dp"
            android:background="#FF9800"
            android:gravity="center"
            android:text="@string/tv"
            android:textColor="#ffffff"
            android:textSize="18dp"
            android:visibility="visible"/>

        <!--自定义对话框内容-->
        <LinearLayout
            android:id="@+id/ctv"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:gravity="center"
            android:orientation="vertical"
            android:paddingBottom="10dp">
            <EditText
                android:id="@+id/username"
                android:layout_width="match_parent"
                android:layout_height="55dp"
                android:hint="@string/username"/>
            <EditText
                android:id="@+id/passwd"
                android:layout_width="match_parent"
                android:layout_height="45dp"
                android:hint="@string/passwd"
                android:outlineSpotShadowColor="#FF03A9F4"
                />
        </LinearLayout>

        <!--自定义对话框下方操作按钮-->
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="55dp"
            android:layout_gravity="bottom"
            android:background="#e0e0e0"
            android:orientation="horizontal">

            <TextView
                android:id="@+id/btn_cancel"
                android:layout_width="0dp"
                android:layout_weight="1"
                android:layout_height="match_parent"
                android:layout_marginLeft="20dp"
                android:layout_marginRight="20dp"
                android:gravity="center"
                android:text="@string/btn_cancel"
                android:textStyle="bold"
                android:textSize="20dp"
                android:textColor="#000"/>
            <TextView
                android:id="@+id/btn_sign"
                android:layout_width="0dp"
                android:layout_weight="1"
                android:layout_height="match_parent"
                android:layout_marginLeft="20dp"
                android:gravity="center"
                android:text="@string/btn_sign"
                android:textColor="#000"
                android:textStyle="bold"
                android:textSize="20dp"/>

        </LinearLayout>
    </LinearLayout>
</LinearLayout>
```

2、在SecondActivity.java通过AlertDialog对象的setView方法显示自定义对话框：

```java
package com.example.expwork3;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_second);

        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this)
                            .setView(R.layout.activity_second)
                            .create();
        alertDialog.show();
    }
}

```

3、实验效果截图：
<br/>

<img src="https://i.loli.net/2020/11/30/XjAwLrZCQ8qmkli.jpg" alt="8DC0E95A1F741678FB94AA402E2E68A3.jpg" width="200" height="400" />

## 三、使用XML定义菜单

1、创建一个菜单资源文件menu_main.xml:

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:title="@string/font_size">
        <menu>
            <group android:checkableBehavior="single">
                <item
                    android:id="@+id/font_10"
                    android:title="@string/font_10"/>
                <item
                    android:id="@+id/font_16"
                    android:title="@string/font_16"/>
                <item
                    android:id="@+id/font_20"
                    android:title="@string/font_20"/>
            </group>
        </menu>
    </item>

    <item android:id="@+id/plain_item"
        android:title="@string/plain_item">
    </item>
    <item android:title="@string/font_color">
        <menu>
            <group>
                <item
                    android:id="@+id/red_font"
                    android:title="@string/red_font"/>
                <item
                    android:id="@+id/black_font"
                    android:title="@string/black_font"/>
            </group>
        </menu>
    </item>
</menu>
```

2、创建一个activity_third.xml文件存放一段用于测试的文本：

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ThirdActivity">

    <TextView
        android:id="@+id/text"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/text"/>
</LinearLayout>
```

3、ThirdActivity.java代码：

```
package com.example.expwork3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        text = findViewById(R.id.text);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem mi){
        if(mi.isCheckable()){
            mi.setChecked(true);
        }
        switch (mi.getItemId()){
            case R.id.font_10: 
                text.setTextSize(10); 
                break;
            case R.id.font_16: 
                text.setTextSize(16); 
                break;
            case R.id.font_20: 
                text.setTextSize(20); 
                break;
            case R.id.red_font: 
                text.setTextColor(Color.RED); 
                break;
            case R.id.black_font: 
                text.setTextColor(Color.BLACK); 
                break;
            case R.id.plain_item:
                Toast.makeText(ThirdActivity.this,"您选择了普通样式",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
```

4、实验效果截图：

普通样式：<br/>

<img src="https://i.loli.net/2020/11/30/n8RBZTxbPrKqH6C.jpg" alt="_VN@OH`5_0_X80T_1VI_X_J.jpg" width="200" height="400"/>

字体大小变换：<br/>

<img src="https://i.loli.net/2020/11/30/pUj7H1yOMB8YKJz.png" alt="_CD7_`3_Q8N___Q3GN@X_KM.png" width="600" />

字体颜色变换：<br/>

<img src="https://i.loli.net/2020/11/30/kED6jxtYsQKFRdV.png" alt="LBS_1N5_J5F2_5__X_BY_VB.png" width="600" />

## 四、创建上下文操作模式（ActionMode）的上下文菜单

