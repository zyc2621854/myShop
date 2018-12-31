package android.kanzz.com.myshop.fragment;


import android.kanzz.com.myshop.adapter.DividerItemDecortion;
import android.kanzz.com.myshop.adapter.HomeCategoryAdapter;
import android.kanzz.com.myshop.bean.Banner;
import android.kanzz.com.myshop.bean.HomeCategory;
import android.kanzz.com.myshop.http.BaseCallback;
import android.kanzz.com.myshop.http.OkHttpHelper;
import android.kanzz.com.myshop.http.SpotsCallBack;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.kanzz.com.myshop.R;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private SliderLayout mSliderLayout;

    private PagerIndicator indicator;

    private RecyclerView mRecyclerView;

    private HomeCategoryAdapter mAdapter;

    private static  final String TAG="HomeFragment";

    private Gson mGson=new Gson();



    private List<Banner> mBanner;

    private OkHttpHelper httpHelper=OkHttpHelper.getInstance();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        mSliderLayout=(SliderLayout)view.findViewById(R.id.slider);

//        indicator=(PagerIndicator)view.findViewById(R.id.custom_indicator);

        initSlider();
        requestImages();
        initRecyclerView(view);
        return view;
    }

    private void requestImages(){
        String url="http://112.124.22.238:8001/course_api/banner/query?type=1";

         httpHelper.get(url, new BaseCallback<List<Banner>>() {
             @Override
             public void onRequestBefore(Request request) {

             }

             @Override
             public void onFailure(Request request, IOException e) {

             }

             @Override
             public void onSuccess(Response response, List<Banner> banners) {
                Log.d(TAG,"banner"+banners.size());
                 mBanner=banners;
                initSlider();
             }

             @Override
             public void onError(Response response, int code, Exception e) {

             }
         });
    }

    private void initRecyclerView(View view) {
        mRecyclerView=(RecyclerView) view.findViewById(R.id.recyclerview);

        List<HomeCategory> datas=new ArrayList<>(15);

        HomeCategory category = new HomeCategory("热门活动",R.drawable.img_big_1,R.drawable.img_1_small1,R.drawable.img_1_small2);
        datas.add(category);

        category = new HomeCategory("有利可图",R.drawable.img_big_4,R.drawable.img_4_small1,R.drawable.img_4_small2);
        datas.add(category);
        category = new HomeCategory("品牌街",R.drawable.img_big_2,R.drawable.img_2_small1,R.drawable.img_2_small2);
        datas.add(category);

        category = new HomeCategory("金融街 包赚翻",R.drawable.img_big_1,R.drawable.img_3_small1,R.drawable.imag_3_small2);
        datas.add(category);

        category = new HomeCategory("超值购",R.drawable.img_big_0,R.drawable.img_0_small1,R.drawable.img_0_small2);
        datas.add(category);

        mAdapter=new HomeCategoryAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecortion());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

    }

    private void initSlider(){

        if(mBanner!=null){
            for(Banner banner:mBanner){
                TextSliderView textSliderView=new TextSliderView(this.getActivity());
                textSliderView.image(banner.getImgUrl());
                textSliderView.description(banner.getDescription());
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                mSliderLayout.addSlider(textSliderView);
            }
        }


//        TextSliderView textSliderView = new TextSliderView(this.getActivity());
//        textSliderView
//                .description("新品推荐")
//                .image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t2416/102/20949846/13425/a3027ebc/55e6d1b9Ne6fd6d8f.jpg");
//        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
//            @Override
//            public void onSliderClick(BaseSliderView slider) {
//                Toast.makeText(HomeFragment.this.getActivity(),"新品推荐",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        TextSliderView textSliderView2 = new TextSliderView(this.getActivity());
//        textSliderView
//                .description("时尚男装")
//                .image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t1507/64/486775407/55927/d72d78cb/558d2fbaNb3c2f349.jpg");
//        textSliderView2.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
//            @Override
//            public void onSliderClick(BaseSliderView slider) {
//                Toast.makeText(HomeFragment.this.getActivity(),"时尚男装",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        TextSliderView textSliderView3 = new TextSliderView(this.getActivity());
//        textSliderView
//                .description("家电秒杀")
//                .image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t1363/77/1381395719/60705/ce91ad5c/55dd271aN49efd216.jpg");
//        textSliderView3.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
//            @Override
//            public void onSliderClick(BaseSliderView slider) {
//                Toast.makeText(HomeFragment.this.getActivity(),"家电秒杀",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        mSliderLayout.addSlider(textSliderView);
//        mSliderLayout.addSlider(textSliderView2);
//        mSliderLayout.addSlider(textSliderView3);

//        mSliderLayout.setCustomIndicator(indicator);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mSliderLayout.setDuration(3000);

        mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG,"onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG,"onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG,"onPageScrollStateChanged");
            }
        });
    }

}
