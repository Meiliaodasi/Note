###attrs.xml
<?xml version="1.0" encoding="utf-8"?>  
<resources>  
  
    <attr name="titleText" format="string" />  
    <attr name="titleTextColor" format="color" />  
    <attr name="titleTextSize" format="dimension" />  
  
    <declare-styleable name="CustomTitleView">  
        <attr name="titleText" />  
        <attr name="titleTextColor" />  
        <attr name="titleTextSize" />  
    </declare-styleable>  
  
</resources>  

###一定要引入 xmlns:custom="http://schemas.android.com/apk/res/com.example.customview01"我们的命名空间，后面的包路径指的是项目的package
    <com.example.customview01.view.CustomTitleView  
        android:layout_width="200dp"  
        android:layout_height="100dp"  
        custom:titleText="3712"  
        custom:titleTextColor="#ff0000"  
        custom:titleTextSize="40sp" />  
    TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyle, 0); 
    mTitleTextColor = a.getColor(attr, Color.BLACK);
    a.recycle(); //避免重新创建时错误
      
    @Override View的测量
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){ 
     int widthMode = MeasureSpec.getMode(widthMeasureSpec);  
     int widthSize = MeasureSpec.getSize(widthMeasureSpec);
     setMeasuredDimension(width, height);  
    }  
    
    
    
