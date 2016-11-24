##  
ViewGroup：dispatchTouchEvent()分发,onInterceptTouchEvent()拦截,onTouchEvent()响应  
Activity：onTouchEvent(),dispatchTouchEvent()  
事件传递：先执行dispatchTouchEvent()方法，再执行onInterceptTouchEvent()方法。 A→B→ME  
事件处理：onTouchEvent()。 ME→B→A  
事件传递的返回值：True，拦截，不继续；False，不拦截，继续。  
事件处理的返回值：True,处理了，不审核；False，给上级处理。  
初始情况下，返回值都是false。  

对于Activity：不是继承自View，不存在onInterceptTouchEvent方法，并且不能设置onTouchListener。  
对于基础控件：不是继承自ViewGroup，也不存在onInterceptTouchEvent方法，但是能够设置onTouchListener。  
对于ViewGroup：满足所有要求，四个方法都能够响应。  

![事件分发](http://img.blog.csdn.net/20150722124421994)  

1. Android事件分发是先传递到ViewGroup，再由ViewGroup传递到View的。  
2. 在ViewGroup中可以通过onInterceptTouchEvent方法对事件传递进行拦截，onInterceptTouchEvent方法返回true代表不允许事件继续向子View传递  
   返回false代表不对事件进行拦截，默认返回false。  
   (返回True，拦截子View事件，Mylayout的dispatchTouchEvent方法，处理MyLayout的touch事件。结果为Layout touch，Layout touch，Layout touch)  
   (返回false，Button的dispatchTouchEvent方法，处理按钮的点击事件，终止事件传递。结果为button1，button2，Layout touch)
3. 子View中如果将传递的事件消费掉，ViewGroup中将无法接收到任何事件。  
4. Touch事件是从最顶层的View一直分发到手指touch的最里层的View,如果最里层View消费了ACTION_DOWN事件（设置onTouchListener，并且onTouch()返回true 或者onTouchEvent()方法返回true）才会触发ACTION_MOVE,ACTION_UP的发生,如果某个ViewGroup拦截了Touch事件，则Touch事件交给ViewGroup处理  
5. 当Acitivty接收到Touch事件时，将遍历子View进行Down事件的分发。ViewGroup的遍历可以看成是递归的。分发的目的是为了找到真正要处理本次完整触摸事件的View，这个View会在onTouchuEvent结果返回true。  
6. 当某个子View返回true时，会中止Down事件的分发，同时在ViewGroup中记录该子View。接下去的Move和Up事件将由该子View直接进行处理。由于子View是保存在ViewGroup中的，多层ViewGroup的节点结构时，上级ViewGroup保存的会是真实处理事件的View所在的ViewGroup对象:如ViewGroup0-ViewGroup1-TextView的结构中，TextView返回了true，它将被保存在ViewGroup1中，而ViewGroup1也会返回true，被保存在ViewGroup0中。当Move和UP事件来时，会先从ViewGroup0传递至ViewGroup1，再由ViewGroup1传递至TextView。  
7. 当ViewGroup中所有子View都不捕获Down事件时，将触发ViewGroup自身的onTouch事件。触发的方式是调用super.dispatchTouchEvent函数，即父类View的dispatchTouchEvent方法。在所有子View都不处理的情况下，触发Acitivity的onTouchEvent方法。  
8. onInterceptTouchEvent有两个作用：1.拦截Down事件的分发。2.中止Up和Move事件向目标View传递，使得目标View所在的ViewGroup捕获Up和Move事件。  
9. View的长按事件是在ACTION_DOWN中执行，要想执行长按事件该View必须是longClickable的，并且不能产生ACTION_MOVE。View的点击事件是在ACTION_UP中执行。   

###自定义了控件（View）的onTouchEvent直接返回true而不调运super方法时，事件派发机制类似，只是最后up事件没有触发onClick而已（因为没有调用super）。  

###整个View的事件转发流程是：

View.dispatchEvent->View.setOnTouchListener->View.onTouchEvent

在dispatchTouchEvent中会进行OnTouchListener的判断，如果OnTouchListener不为null且返回true，则表示事件被消费，onTouchEvent不会被执行；否则执行onTouchEvent。

1、如果ViewGroup找到了能够处理该事件的View，则直接交给子View处理，自己的onTouchEvent不会被触发；

2、可以通过复写onInterceptTouchEvent(ev)方法，拦截子View的事件（即return true），把事件交给自己处理，则会执行自己对应的onTouchEvent方法

3、子View可以通过调用getParent().requestDisallowInterceptTouchEvent(true);  阻止ViewGroup对其MOVE或者UP事件进行拦截；







