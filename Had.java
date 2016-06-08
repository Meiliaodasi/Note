package com.l.tunerlistview;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MainActivity extends Activity {
	
	HorizontalListView hListView;
	HorizontalListViewAdapter hListViewAdapter;
	Button btButton;
	public static final String TAG = "HorizontalListview";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @SuppressWarnings("null")
	public void initUI(){
		hListView = (HorizontalListView)findViewById(R.id.hlv);
		
		btButton = (Button)findViewById(R.id.bt_list_item);
		List<Button> mydatas= null ;
		for(int i=0;i<12;i++){
//			btButton.setText(i);
			mydatas.add(btButton);
		}
		
		hListViewAdapter = new HorizontalListViewAdapter(getApplicationContext(),mydatas);
		hListView.setAdapter(hListViewAdapter);
		//			@Override
		//			public void onNothingSelected(AdapterView<?> parent) {
		//				// TODO Auto-generated method stub
		//				
		//			}
		//		});
		hListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
//				if(olderSelectView == null){
//					olderSelectView = view;
//				}else{
//					olderSelectView.setSelected(false);
//					olderSelectView = null;
//				}
//				olderSelectView = view;
//				view.setSelected(true);
				Log.v(TAG, "onItemCLick");
				
				btButton.setText(position);
				hListViewAdapter.notifyDataSetChanged();
				
			}
		});
		hListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.v(TAG, "onItemLongCLick");
				return false;
			}
		});

	}
    
}
