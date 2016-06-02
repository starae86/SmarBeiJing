package com.ae86.smartbeijing;


import java.util.ArrayList;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

public class GuideActivity extends Activity{
	
	private ViewPager mViewPager;
	private int[] mImageIds = new int[] { R.drawable.guide_1,
			R.drawable.guide_2, R.drawable.guide_3 };
	private ArrayList<ImageView> mImageViewList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		mViewPager = (ViewPager) findViewById(R.id.vp_guide);
		initData();
		mViewPager.setAdapter(new GuideAdapter());
	}
	
	private void initData(){
		mImageViewList=new ArrayList<ImageView>();
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView view = new ImageView(this);
			view.setBackgroundResource(mImageIds[i]);
			mImageViewList.add(view);
			
		}
	}
	
	
 	class GuideAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		//��ʼ������item
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = mImageViewList.get(position);
			container.addView(view);
			return view;
		}
		//����item
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
}
