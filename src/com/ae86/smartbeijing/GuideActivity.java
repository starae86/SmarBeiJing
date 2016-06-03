package com.ae86.smartbeijing;

import java.util.ArrayList;

import com.ae86.smartbeijing.Utils.PrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class GuideActivity extends Activity {

	private ViewPager mViewPager;
	private int[] mImageIds = new int[] { R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3 };
	private ArrayList<ImageView> mImageViewList;
	private LinearLayout llContainer;
	private ImageView ivRedPoint;
	private int mPointDis;
	private Button btnStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		mViewPager = (ViewPager) findViewById(R.id.vp_guide);
		ivRedPoint = (ImageView) findViewById(R.id.iv_red_point);
		llContainer = (LinearLayout) findViewById(R.id.ll_container);
		ivRedPoint = (ImageView) findViewById(R.id.iv_red_point);
		btnStart = (Button) findViewById(R.id.btn_start);

		initData();
		ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				ivRedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				mPointDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
				System.out.println("圆点距离:" + mPointDis);
			}
		});

		mViewPager.setAdapter(new GuideAdapter());
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if (position == mImageViewList.size() - 1) {// 最后一个页面显示开始按钮
					btnStart.setVisibility(View.VISIBLE);
				} else {
					btnStart.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// 当前滑动过程中的回调
				System.out.println("当前位置：" + position + ";偏移百分比: " + positionOffset);
				// 更新小圆点位置
				int leftMatgin = (int) (mPointDis * positionOffset) + position * mPointDis;
				RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
				params.leftMargin = leftMatgin;// 修改左边距
				// 重新设置布局参数
				ivRedPoint.setLayoutParams(params);

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		btnStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 更新SP
				PrefUtils.setBoolean(getApplicationContext(), "is_first_enter", false);

				startActivity(new Intent(getApplicationContext(), MainActivity.class));
				finish();
			}
		});
	}

	private void initData() {
		mImageViewList = new ArrayList<ImageView>();
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView view = new ImageView(this);
			view.setBackgroundResource(mImageIds[i]);
			mImageViewList.add(view);
			ImageView point = new ImageView(this);
			point.setImageResource(R.drawable.shape_point_gray);
			// 初始化布局参数，父控件是谁就是谁声明的布局参数
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			if (i > 0) {
				params.leftMargin = 10;
			}
			point.setLayoutParams(params);// 设置布局参数
			llContainer.addView(point);// 给容器添加图片
		}
	}

	class GuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		// 初始化布局item
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = mImageViewList.get(position);
			container.addView(view);
			return view;
		}

		// 销毁item
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
}
