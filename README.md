#Behavior相关
想实现的效果是,floatButton和底部BottomNavigationView跟随recyclerview进行缩放和位移.

看过的相关的自定义有:[https://github.com/githubwing/ByeBurger](https://github.com/githubwing/ByeBurger),看了下思路,是根据每次滑动方向,
滑动距离是否大于TouchSlop,再去执行缩放/位移动画.
这样的问题就是缩放和位移没有中间状态,比如我现在缩小到了50%,不想继续缩小了,想停在这个状态.

我的思路是借助AppBarLayout,recyclerview滑动时,触发它滑动,可以自定义Behavior 依赖AppBarLayout,根据AppBarLayout的getY()位移占height的百分比,即每次变化的百分比,再转化为缩放比例等.
相关代码:
```java
    //BaseDependenToolBarBehavior.java #Line 30
    public boolean onDependentViewChanged(CoordinatorLayout parent, V child, View dependency) {
        float y = dependency.getY();
        boolean re = false;
        if (lastY == Float.MAX_VALUE) {
            re = false;
        } else {
            float dy = y - lastY;
            float fraction = dy / dependency.getHeight();
            childChange(child, fraction);
            re = true;
        }
        lastY = y;
        return re;
    }
```
子类去实现childChange方法,根据这个百分比具体去缩放or位移.
效果图:

