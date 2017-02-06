package io.github.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.lfh.custom.widget.dialog.DialogParam;
import com.lfh.custom.widget.dialog.GeneralDialogFragment;



public class MainActivity extends AppCompatActivity {
    private static final String CANCEL_DIALOG = "cancel_dialog";
    private static final String CONFIRM_DIALOG = "confirm_dialog";
    private static final String CONFIRM_CANCEL_DIALOG = "confirm_cancel_dialog";
    private static final String THREE_BUTTON_DIALOG = "three_button_dialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_one_cancel_dialog).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View pView) {
                showCancelDialog();
            }
        });

        findViewById(R.id.btn_one_confirm_dialog).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View pView) {
                showConfirmDialog();
            }
        });

        findViewById(R.id.btn_two_button_dialog).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View pView) {
                showTwoButtonDialog();
            }
        });

        findViewById(R.id.btn_three_button_dialog).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View pView) {
                showThreeButtonDialog();
            }
        });
    }

    private void showThreeButtonDialog() {
        DialogParam param = DialogParam.builder()
                .setTitle("对话框标题")
                .setMessage("只有一个确定按钮的对话框")
                .setLeftButtonText("不限")
                .setNegativeText("取消")
                .setPositionText("确定")
                .build();
        GeneralDialogFragment fragment = GeneralDialogFragment.newInstance(param);
        fragment.setOnButtonClickListener(new GeneralDialogFragment.OnButtonClickListener() {
            @Override
            public void onButtonClick() {
                hideDialog(THREE_BUTTON_DIALOG);
                Toast.makeText(MainActivity.this, "点击了不限按钮", Toast.LENGTH_SHORT).show();
            }
        });
        fragment.setOnNegativeButtonClickListener(new GeneralDialogFragment.OnNegativeButtonClickListener() {
            @Override
            public void onNegativeButtonClick() {
                hideDialog(THREE_BUTTON_DIALOG);
                Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
            }
        });
        fragment.setOnPositiveButtonClickListener(new GeneralDialogFragment.OnPositiveButtonClickListener() {
            @Override
            public void onPositionButtonClick() {
                hideDialog(THREE_BUTTON_DIALOG);
                Toast.makeText(MainActivity.this, "点击了确定按钮", Toast.LENGTH_SHORT).show();
            }
        });
        showDialog(fragment, THREE_BUTTON_DIALOG);
    }

    private void showTwoButtonDialog() {
        DialogParam param = DialogParam.builder()
                .setTitle("对话框标题")
                .setMessage("只有一个确定按钮的对话框")
                .setNegativeText("取消")
                .setPositionText("确定")
                .build();
        GeneralDialogFragment fragment = GeneralDialogFragment.newInstance(param);
        fragment.setOnNegativeButtonClickListener(new GeneralDialogFragment.OnNegativeButtonClickListener() {
            @Override
            public void onNegativeButtonClick() {
                hideDialog(CONFIRM_CANCEL_DIALOG);
                Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
            }
        });
        fragment.setOnPositiveButtonClickListener(new GeneralDialogFragment.OnPositiveButtonClickListener() {
            @Override
            public void onPositionButtonClick() {
                hideDialog(CONFIRM_CANCEL_DIALOG);
                Toast.makeText(MainActivity.this, "点击了确定按钮", Toast.LENGTH_SHORT).show();
            }
        });
        showDialog(fragment, CONFIRM_CANCEL_DIALOG);
    }

    private void showConfirmDialog() {
        DialogParam param = DialogParam.builder()
                .setTitle("对话框标题")
                .setMessage("包含确定、取消按钮的对话框")

                .setPositionText("确定")
                .build();
        GeneralDialogFragment fragment = GeneralDialogFragment.newInstance(param);
        fragment.setOnPositiveButtonClickListener(new GeneralDialogFragment.OnPositiveButtonClickListener() {
            @Override
            public void onPositionButtonClick() {
                hideDialog(CONFIRM_DIALOG);
                Toast.makeText(MainActivity.this, "点击了确定按钮", Toast.LENGTH_SHORT).show();
            }
        });
        showDialog(fragment, CONFIRM_DIALOG);
    }

    private void showCancelDialog() {
        DialogParam param = DialogParam.builder()
                .setTitle("对话框标题")
                .setMessage("只有一个取消按钮的对话框")
                .setNegativeText("取消")
                .build();
        GeneralDialogFragment fragment = GeneralDialogFragment.newInstance(param);
        fragment.setOnNegativeButtonClickListener(new GeneralDialogFragment.OnNegativeButtonClickListener() {
            @Override
            public void onNegativeButtonClick() {
                hideDialog(CANCEL_DIALOG);
                Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
            }
        });

        showDialog(fragment, CANCEL_DIALOG);
    }

    private void showDialog(DialogFragment pDialogFragment, String pTag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(pDialogFragment, pTag);
        transaction.commitAllowingStateLoss();
    }

    private void hideDialog(String pTag) {
        FragmentManager manager = getSupportFragmentManager();
        GeneralDialogFragment fragment = (GeneralDialogFragment) manager.findFragmentByTag(pTag);
        FragmentTransaction transaction = manager.beginTransaction();

        if (null != fragment) {
            transaction.remove(fragment);
            transaction.commitAllowingStateLoss();
        }
    }
}
