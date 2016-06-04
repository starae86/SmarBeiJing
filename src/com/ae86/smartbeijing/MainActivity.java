package com.ae86.smartbeijing;

import com.ae86.smartbeijing.Fragment.ContentFragment;
import com.ae86.smartbeijing.Fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends SlidingFragmentActivity{
	private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
	private static final String TAG_CONTENT = "TAG_CONTENT";
	private TextView tv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.left_menu);
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// ȫ������
		slidingMenu.setBehindOffset(200);// ��ĻԤ��200���ؿ��
		initFragment();
		
	}
	/**
	 * ��ʼ��fragment
	 */
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();// ��ʼ����
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),
				TAG_LEFT_MENU);// ��fragment�滻֡����;��1:֡����������id;��2:��Ҫ�滻��fragment;��3:���
		transaction.replace(R.id.fl_main, new ContentFragment(), TAG_CONTENT);
		transaction.commit();// �ύ����
		// Fragment fragment =
		// fm.findFragmentByTag(TAG_LEFT_MENU);//���ݱ���ҵ���Ӧ��fragment
	}
}
