package com.example.x2y.englishapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView SearchTV;
    ViewPager viewPager;
    BottomNavigationView navigation;//底部导航栏对象
    List<Fragment>listFragment;//存储页面对象


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化

        //设置卡片中文字大小


        //搜索框点击跳转搜索页
        SearchTV = (TextView) findViewById(R.id.home_search);
        Intent serarch_intent = new Intent();
        SearchTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView(){
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        navigation = (BottomNavigationView)findViewById(R.id.navigation);
        //向ViewPaper添加个页面
        listFragment = new ArrayList<>();
        listFragment.add(new homeFragment());
        listFragment.add(new reviewFragment());
        listFragment.add(new personFragment());
        MyFragAdpter myFragAdpter = new MyFragAdpter(getSupportFragmentManager(),this,listFragment);
        viewPager.setAdapter(myFragAdpter);

        //导航栏点击事件和ViewPager滑动事件,让两个控件相互关联
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId()){
                   case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        return true;
                   case R.id.navigation_dashboard:
                       viewPager.setCurrentItem(1);
                       return  true;
                   case R.id.navigation_notifications:
                       viewPager.setCurrentItem(2);
                       return true;
                       default:
                           break;
               }
               return false;
           }
       });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                //注意这个方法滑动时会调用多次，下面是参数解释：
                // i当前所处页面索引,滑动调用的最后一次绝对是滑动停止所在页面
                // v表示从位置的页面偏移的[0,1]的值。
                // il以像素为单位的值，表示与位置的偏移

            }

            @Override
            public void onPageSelected(int i) {
                //该方法只在滑动停止时调用，position滑动停止所在页面位置
                // 当滑动到某一位置，导航栏对应位置被按下
                 navigation.getMenu().getItem(i).setChecked(true);
                // 这里使用navigation.setSelectedItemId(position);无效，
                // setSelectedItemId(position)的官网原句：Set the selected
                // menu item ID. This behaves the same as tapping on an item
                // 未找到原因

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }


}