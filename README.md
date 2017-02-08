# EntDialog

标签（空格分隔）： 通用对话框

---

开发者：lifh

主要功能
----

 - 无标题对话框
 - 带标题、只有一个按钮对话框
 - 带标题、有两个按钮对话框
 - 带标题、有三个按钮对话框

可自定义选项
------

 - 按钮文字
 - 按钮是否显示
 - 标题是否显示
 - 消息内容

使用方法
----

> 1.构造参数

``` java
DialogParam param = DialogParam.builder()
                .setTitle("标题") //不设置不显示标题
                .setMessage("提示内容") //必填
                .setLeftButtonText("不限") //不设置不显示左边的按钮
                .setNegativeText("取消") //不设置不显示右边第一个按钮(通常是取消按钮)
                .setPositionText("确定") //不设置不显示右边第二个按钮(通常是确定按钮)
                .build();
```

> 2.实例一个对象

```java
GeneralDialogFragment fragment = GeneralDialogFragment.newInstance(param);
```

> 3.设置相应的监听

``` java
fragment.setOnButtonClickListener(new GeneralDialogFragment.OnButtonClickListener() {
    @Override
    public void onButtonClick() {
        //左边的按钮监听
    }
});

fragment.setOnNegativeButtonClickListener(new GeneralDialogFragment.OnNegativeButtonClickListener() {
    @Override
    public void onNegativeButtonClick() {
        //右边第一个按钮的监听
    }
});

fragment.setOnPositiveButtonClickListener(new GeneralDialogFragment.OnPositiveButtonClickListener() {
    @Override
    public void onPositionButtonClick() {
        //右边第二个按钮的监听
    }
});
```

> 4.显示、隐藏

 - 显示
```java
private void showDialog() {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.add(fragment, "YOUR TAG");
    transaction.commitAllowingStateLoss();
}
```
 - 隐藏
```java
 private void hideDialog() {
    FragmentManager manager = getSupportFragmentManager();
    GeneralDialogFragment fragment = (GeneralDialogFragment) manager.findFragmentByTag("YOUR TAG");
    FragmentTransaction transaction = manager.beginTransaction();

    if (null != fragment) {
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
    }
}
```
