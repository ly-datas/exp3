package com.example.expwork3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class FourthActivity extends AppCompatActivity {
    private ListView listView;
    private List<Item> list;
    private AdapterCursor adapter;
    private String[] names = new String[]{"Lion","Tiger","Monkey","Dog","Cat","dfi","sdh","sdfho","efjsod"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        listView = findViewById(R.id.mylist2);
        list = new ArrayList<>();
        for(int i=0;i<names.length;i++){
            list.add(new Item(names[i],false));
        }
        adapter = new AdapterCursor(list,FourthActivity.this);
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            int num = 0;
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                // 调整选定条目
                if (checked == true) {
                    list.get(position).setBo(true);
                    //实时刷新
                    adapter.notifyDataSetChanged();
                    num++;
                } else {
                    list.get(position).setBo(false);
                    //实时刷新
                    adapter.notifyDataSetChanged();
                    num--;
                }
                // 用TextView显示
                mode.setTitle("  " + num + " Selected");
            }


            /*
             * 参数：ActionMode是长按后出现的标题栏
             * 		Menu是标题栏的菜单内容
             */
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // 设置长按后所要显示的标题栏的内容
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.actionmode_menu, menu);
                num = 0;
                adapter.notifyDataSetChanged();
                return true;

            }


            /*
             * 可在此方法中进行标题栏UI的创建和更新
             */
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

                adapter.notifyDataSetChanged();
                return false;
            }

            public void refresh(){
                for(int i = 0; i < 6; i++){
                    list.get(i).setBo(false);
                }
            }
            public void delete(){
                for(int i=0;i<list.size();i++){
                    if(list.get(i).isBo()){
                        list.remove(i);
                    }
                }
            }
            /*
             * 可在此方法中监听标题栏Menu的监听，从而进行相应操作
             * 设置actionMode菜单每个按钮的点击事件
             */
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    //全选
                    case R.id.action_setting:
                        num = 0;
                        refresh();
                        adapter.notifyDataSetChanged();
                        mode.finish(); // 偷了个懒，每个菜单按钮都设置返回，结束多选模式
                        return true;
                    //删除
                    case R.id.action_delete:
                        adapter.notifyDataSetChanged();
                        num = 0;
                        delete();
                        mode.finish();// 偷了个懒，每个菜单按钮都设置返回，结束多选模式
                        return true;
                    default:
                        refresh();
                        adapter.notifyDataSetChanged();
                        num = 0;
                        return false;
                }

            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                refresh();
                adapter.notifyDataSetChanged();

            }

        });
    }
}
