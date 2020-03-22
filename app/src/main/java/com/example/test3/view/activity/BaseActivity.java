package com.example.test3.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import com.example.test3.urils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0 || requestCode == 1) {//requestCode = 0 表示申请单个重要权限  = 1 为同时申请多个权限 一般来说不太重要
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    if (shouldShowRequestPermissionRationale(permissions[i])) {
                        /**
                         * 用户拒绝后的操作，这里我们继续提醒用户申请权限（除非是特别特别重要的权限，否则不要这样做！）
                         */
                        if (requestCode == 0) {
                            ActivityCompat.requestPermissions(this, new String[]{permissions[i]}, 0);
                        } else {
                            Toast.makeText(BaseActivity.this, "权限被拒绝： " + permissions[i], Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        if (requestCode == 0) {
                            /**
                             * 用户多次拒绝权限并设置了不再提醒，那么只能提醒用户去设置页面打开我们的权限了（除非是特别特别重要的权限，否则不要这样做！）
                             */
                            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle("说明");
                            builder.setMessage("没有权限将无法使用APP,打开设置页面放开权限");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_SETTINGS);
                                    startActivity(intent);
                                }
                            });
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(BaseActivity.this, "无法使用APP", Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder.show();
                        } else {
                            Toast.makeText(BaseActivity.this, "权限被拒绝： " + permissions[i], Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (requestCode == 0) {
                    onPermissionCall(permissions[i]);
                }
            }

            if (requestCode == 1) {
                onPermissionCall("");
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    /**
     * 检查多个申请权限
     * @param permissionsArray
     */
    protected void setPermissionGroup(String[] permissionsArray){
        List<String> permissionsList = new ArrayList<String>();
        if (Build.VERSION.SDK_INT>23){

            for (String permission : permissionsArray) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionsList.add(permission);
                }
            }

            if (permissionsList.size() == 0){
                onPermissionCall("");
            }else{
                ActivityCompat.requestPermissions(this, permissionsList.toArray(new String[permissionsList.size()]), 1);
            }

        }else{
            onPermissionCall("");
        }
    }

    protected void setPermission(String permission) {
        if (Build.VERSION.SDK_INT > 23) {
//            检查是否获得该权限，如果没有，就去申请。
            if (PermissionChecker.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{permission}, 0);
            } else {
                onPermissionCall(permission);
            }
        } else {
            onPermissionCall(permission);
        }
    }

    protected void onPermissionCall(String message) {

    }

    /*toast*/
    public void showToast(int msgId) {
        showToast(getString(msgId));
    }

    public void showToast(String msg) {
        ToastUtil.showToast(this, msg);
    }

}
