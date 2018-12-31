package android.kanzz.com.myshop.http;

import android.content.Context;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import dmax.dialog.SpotsDialog;

public abstract class SpotsCallBack<T> extends BaseCallback<T> {

//    SpotsDialog dialog;
//
//    public SpotsCallBack(Context context){
//        dialog=new SpotsDialog(context);
//    }
//
//    public void showDialog(){
//        dialog.show();
//    }
//
//    public void dismissDialog(){
//        if(dialog!=null){
//            dialog.dismiss();
//        }
//    }
//
//    public void setMessage(String message){
//        dialog.setMessage(message);
//    }
//
//    @Override
//    public void onRequestBefore(Request request) {
//        showDialog();
//    }
//
//    @Override
//    public void onFailure(Request request, IOException e) {
//        dismissDialog();
//    }
//
//    @Override
//    public void onResponse(Response response) {
//        dismissDialog();
//    }
}
