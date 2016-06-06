##  
ViewGroup：dispatchTouchEvent(),onInterceptTouchEvent(),onTouchEvent()  
View：onTouchEvent(),dispatchTouchEvent()  
事件传递：先执行dispatchTouchEvent()方法，再执行onInterceptTouchEvent()方法。 A  
事件处理：onTouchEvent()。 
事件传递的返回值：True，拦截，不继续；False，不拦截，继续。  
事件处理的返回值：True,处理了，不审核；False，给上级处理。  
初始情况下，返回值都是false。  

