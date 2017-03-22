package com.baoxin.bx_framework.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoxin.bx_framework.R;
import com.baoxin.bx_framework.ui.fragment1.Fragment1Activity;
import com.baoxin.bx_framework.ui.fragment2.Fragment2Activity;
import com.baoxin.bx_framework.ui.fragment3.Fragment3Activity;
import com.baoxin.bx_framework.ui.fragment4.Fragment4Activity;
import com.baoxin.bx_framework.ui.fragment5.Fragment5Activity;
import com.baoxin.bx_framework.ui.view.popup.ActionItem;
import com.baoxin.bx_framework.ui.view.popup.TitlePopup;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static String TAG = "BAOXIN--->MainActivity";
    private Context context;
    private TextView txt_left, txt_right, txt_title;
    private TextView tv_tab_number1, tv_tab_number2, tv_tab_number3, tv_tab_number4, tv_tab_number5;
    private ImageView img_right, img_left;
    private Fragment1Activity fragment1Activity;
    private Fragment2Activity fragment2Activity;
    private Fragment3Activity fragment3Activity;
    private Fragment4Activity fragment4Activity;
    private Fragment5Activity fragment5Activity;
    private ImageView[] imagebuttons;
    private TextView[] tv_tabs;
    private Fragment[] fragments;
    private RelativeLayout re_tab1, re_tab2, re_tab3, re_tab4, re_tab5;
    private int index;
    private int currentTabIndex;// 当前fragment的index
    private TextView[] red_dot;
    private TitlePopup titlePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        init();
        findViewById();
        initViews();
        setOnListener();
        initTabView();
        initPopWindow();
    }

    /**
     * 初始化数据部分
     */
    private void init() {

    }

    /**
     * 首页的view
     */
    private void findViewById() {
        txt_left = (TextView) findViewById(R.id.txt_left);
        txt_right = (TextView) findViewById(R.id.txt_right);
        txt_title = (TextView) findViewById(R.id.txt_title);
        img_right = (ImageView) findViewById(R.id.img_right);
        img_left = (ImageView) findViewById(R.id.img_left);

        re_tab1 = (RelativeLayout) findViewById(R.id.re_tab1);
        re_tab2 = (RelativeLayout) findViewById(R.id.re_tab2);
        re_tab3 = (RelativeLayout) findViewById(R.id.re_tab3);
        re_tab4 = (RelativeLayout) findViewById(R.id.re_tab4);
        re_tab5 = (RelativeLayout) findViewById(R.id.re_tab5);
    }

    /**
     * 初始化视图的展示
     */
    private void initViews() {
        txt_left.setVisibility(View.VISIBLE);
        txt_right.setVisibility(View.INVISIBLE);
        img_right.setVisibility(View.VISIBLE);
        img_left.setVisibility(View.INVISIBLE);
    }

    /**
     * 监听设置
     */
    private void setOnListener() {
        img_left.setOnClickListener(this);
        txt_left.setOnClickListener(this);
        img_right.setOnClickListener(this);
        txt_right.setOnClickListener(this);

        re_tab1.setOnClickListener(this);
        re_tab2.setOnClickListener(this);
        re_tab3.setOnClickListener(this);
        re_tab4.setOnClickListener(this);
        re_tab5.setOnClickListener(this);

    }

    /**
     * Fragment部分的初始化
     */
    private void initTabView() {
        fragment1Activity = new Fragment1Activity();
        fragment2Activity = new Fragment2Activity();
        fragment3Activity = new Fragment3Activity();
        fragment4Activity = new Fragment4Activity();
        fragment5Activity = new Fragment5Activity();
        fragments = new Fragment[]{fragment1Activity, fragment2Activity,
                fragment3Activity, fragment4Activity, fragment5Activity};

        tv_tabs = new TextView[5];
        tv_tabs[0] = (TextView) findViewById(R.id.tv_tab1);
        tv_tabs[1] = (TextView) findViewById(R.id.tv_tab2);
        tv_tabs[2] = (TextView) findViewById(R.id.tv_tab3);
        tv_tabs[3] = (TextView) findViewById(R.id.tv_tab4);
        tv_tabs[4] = (TextView) findViewById(R.id.tv_tab5);
        tv_tabs[0].setTextColor(getResources().getColor(R.color.main_color));

        imagebuttons = new ImageView[5];
        imagebuttons[0] = (ImageView) findViewById(R.id.img_tab1);
        imagebuttons[1] = (ImageView) findViewById(R.id.img_tab2);
        imagebuttons[2] = (ImageView) findViewById(R.id.img_tab3);
        imagebuttons[3] = (ImageView) findViewById(R.id.img_tab4);
        imagebuttons[4] = (ImageView) findViewById(R.id.img_tab5);
        imagebuttons[0].setSelected(true);

        red_dot = new TextView[5];
        red_dot[0] = (TextView) findViewById(R.id.tv_tab_number1);
        red_dot[1] = (TextView) findViewById(R.id.tv_tab_number2);
        red_dot[2] = (TextView) findViewById(R.id.tv_tab_number3);
        red_dot[3] = (TextView) findViewById(R.id.tv_tab_number4);
        red_dot[4] = (TextView) findViewById(R.id.tv_tab_number5);
        // 添加显示第一个fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment1Activity)
                .add(R.id.fragment_container, fragment2Activity)
                .add(R.id.fragment_container, fragment3Activity)
                .add(R.id.fragment_container, fragment4Activity)
                .add(R.id.fragment_container, fragment5Activity)
                .hide(fragment2Activity).hide(fragment3Activity)
                .hide(fragment4Activity).hide(fragment5Activity)
                .show(fragment1Activity).commit();
    }

    private void initPopWindow() {
        // 实例化标题栏弹窗
        titlePopup = new TitlePopup(this, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        titlePopup.setItemOnClickListener(onitemClick);
        // 给标题栏弹窗添加子类
        titlePopup.addAction(new ActionItem(this, R.string.title_popup1, R.drawable.icon_menu_sao));
        titlePopup.addAction(new ActionItem(this, R.string.title_popup2, R.drawable.icon_menu_sao));
        titlePopup.addAction(new ActionItem(this, R.string.title_popup3, R.drawable.icon_menu_sao));
        titlePopup.addAction(new ActionItem(this, R.string.title_popup4, R.drawable.icon_menu_sao));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_right:
                if (index == 0) {
                    titlePopup.show(findViewById(R.id.layout_bar));
                } else {

                }
                break;
            case R.id.txt_right:
                if (index == 1) {
                    red_dot[0] .setVisibility(View.INVISIBLE);
                    red_dot[1] .setVisibility(View.INVISIBLE);
                    red_dot[2] .setVisibility(View.INVISIBLE);
                    red_dot[3] .setVisibility(View.INVISIBLE);
                    red_dot[4] .setVisibility(View.INVISIBLE);
                } else {

                }
                break;
            case R.id.re_tab1:
                txt_left.setVisibility(View.VISIBLE);
                txt_right.setVisibility(View.INVISIBLE);
                img_left.setVisibility(View.INVISIBLE);
                img_right.setVisibility(View.VISIBLE);
                txt_title.setText(R.string.fragment_name_1);
                index = 0;
                if (fragment1Activity != null) {

                }
                fragmentChange(index);
                break;
            case R.id.re_tab2:
                txt_left.setVisibility(View.INVISIBLE);
                txt_right.setVisibility(View.VISIBLE);
                img_left.setVisibility(View.INVISIBLE);
                img_right.setVisibility(View.INVISIBLE);
                txt_title.setText(R.string.fragment_name_2);
                index = 1;
                if (fragment2Activity != null) {

                }
                fragmentChange(index);
                break;
            case R.id.re_tab3:
                txt_left.setVisibility(View.VISIBLE);
                txt_right.setVisibility(View.INVISIBLE);
                img_left.setVisibility(View.INVISIBLE);
                img_right.setVisibility(View.VISIBLE);

                txt_title.setText(R.string.fragment_name_3);
                index = 2;
                if (fragment3Activity != null) {

                }
                fragmentChange(index);
                break;
            case R.id.re_tab4:
                txt_left.setVisibility(View.INVISIBLE);
                txt_right.setVisibility(View.INVISIBLE);
                img_left.setVisibility(View.INVISIBLE);
                img_right.setVisibility(View.VISIBLE);
                txt_title.setText(R.string.fragment_name_4);
                index = 3;
                if (fragment4Activity != null) {

                }
                fragmentChange(index);
                break;
            case R.id.re_tab5:
                txt_left.setVisibility(View.INVISIBLE);
                txt_right.setVisibility(View.INVISIBLE);
                img_left.setVisibility(View.INVISIBLE);
                img_right.setVisibility(View.INVISIBLE);
                txt_title.setText(R.string.fragment_name_5);
                index = 4;
                if (fragment5Activity != null) {

                }
                fragmentChange(index);
                break;
        }
    }

    private void fragmentChange(int index) {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        imagebuttons[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        imagebuttons[index].setSelected(true);
        tv_tabs[currentTabIndex].setTextColor(getResources().getColor(R.color.text_color_tab));
        tv_tabs[index].setTextColor(getResources().getColor(R.color.main_color));
        currentTabIndex = index;
    }

    private int keyBackClickCount = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (keyBackClickCount++) {
                case 0:
                    Toast.makeText(this, "再次按返回键退出", Toast.LENGTH_SHORT).show();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            keyBackClickCount = 0;
                        }
                    }, 3000);
                    break;
                case 1:
                    finish();
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                    break;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private TitlePopup.OnItemOnClickListener onitemClick = new TitlePopup.OnItemOnClickListener() {

        @Override
        public void onItemClick(ActionItem item, int position) {
            // mLoadingDialog.show();
            switch (position) {
                case 0:// 发起群聊

                    break;
                case 1:// 添加朋友

                    break;
                case 2:// 扫一扫
                    break;

                case 3:// 收钱
                    break;
                default:
                    break;
            }
        }
    };
}
