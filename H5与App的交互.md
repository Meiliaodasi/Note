  1.Android调用HTML5

        <script type="text/javascript">
            function changeColor (color) {
                document.body.style.backgroundColor = color;
            }
        </script>


        wvAds.getSettings().setJavaScriptEnabled(true);
        wvAds.loadUrl("file:///android_asset/104.html");

        btnShowAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String color = "#00ee00";
                //调用JavaScript方法名称                            
                wvAds.loadUrl("javascript: changeColor ('" + color + "');");
            }
        });
   2.HTML5调用Android
   JavaScript定义要调用的Android中方法名称

         <a onclick="baobao.callAndroidMethod(100,100,'ccc',true)">
                  CallAndroidMethod</a>
  
   新创建一个JSInterface1 类，包括callAndroidMethod 方法的实现：

      class JSInteface1 {

        @JavascriptInterface
        public void callAndroidMethod(int a, f loat b,
          String c, boolean d) {
        if (d) {
        String strMessage = "-" + (a + 1) + "-" + (b + 1)
        + "-" + c + "-" + d;
        new AlertDialog.Builder(MainActivity.this)
        .setTitle("title")
        .setMessage(strMessage).show();
        }
        }
        }

     同时，需要注册baobao 和JSInterface1 的对应关系：

      wvAds.addJavascriptInterface(new JSInteface1(), "baobao");
