FlowImageLayout
==============

当前主流社交软件（微信，QQ，美丽说）图片展示方式

![](https://github.com/ioneday/FlowImageLayout/blob/master/screenshot/flowimagelayout.png?raw=true)

![](https://github.com/ioneday/FlowImageLayout/blob/master/screenshot/flowimagelayout.gif?raw=true)

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
image_layout.setHorizontalSpacing(2);
image_layout.setVerticalSpacing(2);
image_layout.setOneImageWidthHeight(800,640);
image_layout.setLoadImage(9, new FlowImageLayout.OnImageLayoutFinishListener() {
            @Override
            public void layoutFinish(List<ImageView> images) {
//                for (int i = 0; i < images.size(); i++) {
//                    images.get(i).setImageResource(topic.getImgaes().get(i));
//                }
            }
        });
```