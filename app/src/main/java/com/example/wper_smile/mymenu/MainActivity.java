package com.example.wper_smile.mymenu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //显示ListView
        showListView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "点击了Setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                Toast.makeText(MainActivity.this, "点击了About", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //显示ListView的内容
    private void showListView()
    {
        ListView listview=(ListView)findViewById(R.id.myListView);

        /*创建适配器*/
        ArrayAdapter<String>adapter= new ArrayAdapter<String>
                        (this,R.layout.support_simple_spinner_dropdown_item,getData());
        listview.setAdapter(adapter);
        //注册上下文菜单
        this.registerForContextMenu(listview);
    }


    /**
     * 构造listview显示的数据
     * */

    private ArrayList<String>getData(){
        ArrayList<String> list=new ArrayList<String>();
        for (int i=0;i<2;i++)
            list.add("这个第"+(i+1)+"上下文菜单");
        return list;
    }

    //为上下文菜单设置信息
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //设置Menu显示内容
        menu.setHeaderTitle("文件操作");
        menu.add(1,1,1,"复制");
        menu.add(1,2,1,"粘贴");
        menu.add(1,3,1,"删除");
    }

    //为上下文菜单设置响应事件
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Toast.makeText(MainActivity.this, "点击了复制", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this, "点击了粘贴", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this, "点击了删除", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
    //弹出菜单
    public void showPopupMenu(View v){
        PopupMenu popupMenu=new PopupMenu(this,v);
        //实例化子菜单
        popupMenu.getMenuInflater().inflate(R.menu.pop,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.change:
                        Toast.makeText(MainActivity.this, "点击了修改", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
                // 菜单项点击，不仅提供onOptionsItemSelected()的一种触发方式，
                // 如果我们希望将菜单项点击的事件传递下去，继续触发其他处理，则返回false，
                // 如果我们认为全部已经处理完，到此为止，不需要将事件传递下去，则返回true。
                // 如果采用return super.onOptionsItemSelected(item); 则返回值为flase，即系统缺省返回false。
            }
        });
        popupMenu.show();
    }
}
