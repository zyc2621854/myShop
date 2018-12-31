package android.kanzz.com.myshop.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.http.HttpMethod;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpHelper {


    private static OkHttpClient okHttpClient;

    private Handler handler;

    private Gson gson;

    private OkHttpHelper(){
        okHttpClient=new OkHttpClient();

        okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(10,TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        gson=new Gson();
        handler=new Handler(Looper.getMainLooper());
    }
    public static OkHttpHelper getInstance(){

        return new OkHttpHelper();
    }

    public void get(String url,BaseCallback callback){


        Request request=buildRequest(url,null,HttpMethodType.GET);
        doRequest(request,callback);
    }

    public void post(String url, Map<String,String> params,BaseCallback callback){
        Request request=buildRequest(url,params,HttpMethodType.POST);
        doRequest(request,callback);
    }

    public void doRequest(final Request request, final BaseCallback callback) {

        callback.onRequestBefore(request);

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callback.onFailure(request, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {

                    String resultStr = response.body().string();
                    if (callback.mType == String.class) {
                        callbackSuccess(callback,response,resultStr);
                    } else {
                        try {
                            Object object = gson.fromJson(resultStr, callback.mType);
                            callbackSuccess(callback,response,object);
                        } catch (JsonParseException e) {
                            callback.onError(response, response.code(), e);
                        }

                    }
                }
                else {
                    callbackError(callback,response,null);
                }
            }
        });
    }
//                callback.onResponse(response);
//
//                if(response.isSuccessful()){
//                    String resultStr=response.body().string();
//                    if(callback.mType==String.class){
//                        callback.onSuccess(response,resultStr);
//                    }else{
//                        try{
//                            Object object=gson.fromJson(resultStr,callback.mType);
//                            callbackSuccess(callback,response,object);
//                        }
//                        catch (JsonParseException e){
//                            callback.onError(response,response.code(),e);
//                        }
//                    }
//
//                    callback.onSuccess(response,null);
//                }else{
//                    callback.onError(response,response.code(),null);
//                }
//            }
//        });


    private Request buildRequest(String url, Map<String,String> params, HttpMethodType methodType){
        Request.Builder builder=new Request.Builder();
        builder.url(url);
        if(methodType==HttpMethodType.GET){
            builder.get();
        }else if(methodType==HttpMethodType.POST){
            RequestBody body=buildFormData(params);
            builder.post(body);
        }
        return builder.build();
    }

    private RequestBody buildFormData(Map<String,String> params){
        FormEncodingBuilder builder=new FormEncodingBuilder();
       if(params!=null){
           for(Map.Entry<String,String> entry:params.entrySet()){
               builder.add(entry.getKey(),entry.getValue());
           }
       }

       return builder.build();
    }

    private void callbackSuccess(final BaseCallback callback, final Response response, final Object object){
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response,object);
            }
        });
    }

    private void callbackError(final BaseCallback callback, final Response response, final Exception e){
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response,response.code(),e);
            }
        });
    }

    enum HttpMethodType{
        GET,
        POST
    }

}
