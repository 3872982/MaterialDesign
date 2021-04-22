## MaterialDesign使用

### MainActivity
`DrawLayout` + `NavigationView` 实现常见的左侧菜单

### ToolbarActivity
`Toolbar`的使用，`SwipeRefreshLayout`+`RecyclerView`+`Adapter多布局实现加载`更多


### FloatTabActivity
`CoordinatorLayout` + `AppBarLayout` + `CollapsingToolBarLayout` + `Toolbar` 实现 MaterialDesign 经典收缩头部式布局结构

使用`CollapsingToolBarLayout`后如果需要对Toolbar设置标题需要设置到CollapsingToolBarLayout上面;
监听AppBarlayout，根据滑动具体调整标题样式

TabLayout + ViewPager 实现标签导航; 
值得注意的是：如果我们使用tabLayout.setWithViewPager(vp), setupWithViewPager内部会调用removeAllTabs();
然后根据adapter中的getPageTitle去重新生成新的tabs，我们查看源码后就会发现其实setUpWithPager内部是通过双向绑定去实现关联的，
所以我们不直接调用setupWithViewPager，而是直接进行手动双向绑定
```java
//自己手动实现ViewPager与TabLayout的双向绑定，这样就不会删除我们创建的TabItem，图标就不会变换了
mMainVpContainer.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTbToolbar));
mTbToolbar.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mMainVpContainer));
```

第三方控件Banner的使用;
这里的话也是省事，轮播图是非常常用的一个功能模块，如果自己实现的话，主要就是需要注意一下，伪无限轮播（切换起始页到Integer.MAX_VALUE的中间值附近，自己算下，通过模运算，确保起始位首图），
其次通过handler实现自动轮播，思路就是每隔固定秒数发送一个Message推动切换页面；最后的话需要在手指按下抬起的时候，以及进入离开页面的时候控制轮播图的自动轮播与否；

页面切换之间的元素共享动画;

### BottomNavigationViewActivity 
`BottomNavigationView`的使用

### VipActivity
MaterialDesign动画 Fade，Explode，Slide; 沉浸式布局;
