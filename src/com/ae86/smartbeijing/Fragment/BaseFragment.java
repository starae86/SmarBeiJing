package com.ae86.smartbeijing.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

	public Activity mActivity;//���activity����MainActivity

	// Fragment����
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();// ��ȡ��ǰfragment��������activity
	}

	// ��ʼ��fragment�Ĳ���
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = initView();
		return view;
	}

	// fragment��������activity��onCreate����ִ�н���
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// ��ʼ������
		initData();
	}

	// ��ʼ������, ����������ʵ��
	public abstract View initView();

	// ��ʼ������, ����������ʵ��
	public abstract void initData();
}