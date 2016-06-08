package com.l.tunerlistview;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HorizontalListViewAdapter extends BaseAdapter{
	
	private List<Button> btdatas;
	private Context context;
	private LayoutInflater mInflater;
	
	
	public HorizontalListViewAdapter(Context context,List<Button> btdatas) {
		this.context = context;
		this.btdatas = btdatas;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return btdatas.size();
	}

	@Override
	public Object getItem(int position) {
		return btdatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.horizontallistivew_item, null);
			holder.button = (Button) convertView.findViewById(R.id.bt_list_item);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}

		
//		holder.mTitle.setText(mTitles[position]);
//		iconBitmap = getPropThumnail(mIconIDs[position]);
//		holder.mImage.setImageBitmap(iconBitmap);
		
		holder.button.setText(position);

		return convertView;
	}

	private static class ViewHolder {
		private Button button ;
	}
		
	

}




###



<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <RelativeLayout
        android:layout_width="fill_parent"
        android:layout_height="100dp"
        android:layout_marginTop="100dp"
        android:orientation="horizontal" >

        <View
            android:id="@+id/leftview"
            android:layout_height="fill_parent" 
            android:layout_width="20dp"

            />

        <com.l.tunerlistview.HorizontalListView
            android:id="@+id/hlv"
            android:layout_width="match_parent"
            android:layout_height="fill_parent"
            android:scrollbars="horizontal"
            android:layout_toRightOf="@id/leftview"
 >
        </com.l.tunerlistview.HorizontalListView>

        <View
            android:id="@+id/rigthview"
            android:layout_height="fill_parent" 
            android:layout_width="20dp"
            android:layout_alignParentRight="true"
/>
    </RelativeLayout>

</RelativeLayout>

###


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:paddingLeft="2dip"
    android:paddingRight="2dip"
    android:paddingTop="2dip"
    android:paddingBottom="2dip"
    android:orientation="vertical" 
    android:gravity="center"
    android:clickable="true">
  <button
      android:id="@+id/bt_list_item"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"/>

</LinearLayout>

####


http://blog.csdn.net/yanzi1225627/article/details/21294553
http://blog.csdn.net/chen88358323/article/details/8257657
http://blog.csdn.net/lmj623565791/article/details/38140505/
http://blog.csdn.net/deng0zhaotai/article/details/12789597
http://www.cnblogs.com/android100/archive/2012/07/26/android-Viewpager-HorizontalScrollView.html
http://longyi-java.iteye.com/blog/976067
http://blog.csdn.net/swdrt/article/details/9090557
http://blog.csdn.net/lmj623565791/article/details/38902805


http://www.xuebuyuan.com/1974242.html
