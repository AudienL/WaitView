# WaitView

## 效果图：

![效果图](https://github.com/AudienL/WaitView/blob/master/doc/show.gif?raw=true)

## 使用：

### 一、在 project 根目录的 build.gradle 中添加：

```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

### 二、在 module 根目录的 build.gradle 中添加：

其中最后版本在 release 中查看，如：1.0
```groovy
dependencies {
    compile 'com.github.AudienL:WaitView:最后版本'
}
```

### 三、使用

布局文件：
```xml
<!-- 默认 -->
<com.audienl.wait_view_core.WaitView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"/>
```
```xml
<!-- 自定义 -->
<com.audienl.wait_view_core.WaitView
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="200dp"
    android:layout_height="200dp"
    app:circle_color="#80000000"
    app:point_color="@android:color/holo_red_light"
    app:speed="15"
    app:stroke_width="5dp"/>
```
