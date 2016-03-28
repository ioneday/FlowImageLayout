FlowImageLayout
==============

当前主流社交软件（微信，QQ，美丽说）图片展示方式

![](https://github.com/ioneday/FlowImageLayout/blob/master/screenshot/screen1.png?raw=true)
![](https://github.com/ioneday/FlowImageLayout/blob/master/screenshot/screen2.png?raw=true)
![](https://github.com/ioneday/FlowImageLayout/blob/master/screenshot/screen3.png?raw=true)
![](https://github.com/ioneday/FlowImageLayout/blob/master/screenshot/screen4.png?raw=true)

Usage
===
1.
``` xml
<com.yongchun.flowimagelayout.FlowImageLayout
        android:id="@+id/image_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```
2.
``` java
FlowImageLayout image_layout = (FlowImageLayout) findViewById(R.id.image_layout);
image_layout.setSingleImageSize(800,640);
image_layout.loadImage(9, new FlowImageLayout.OnImageLayoutFinishListener() {
        @Override
        public void layoutFinish(List<ImageView> images) {
//            for (int i = 0; i < images.size(); i++) {
//                images.get(i).setImageResource();
//            }
        }
});
```

License
===
Copyright 2015 iOneDay

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.