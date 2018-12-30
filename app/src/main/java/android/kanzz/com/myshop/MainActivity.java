package android.kanzz.com.myshop;

import android.kanzz.com.myshop.bean.Tab;
import android.kanzz.com.myshop.fragment.CartFragment;
import android.kanzz.com.myshop.fragment.CategoryFragment;
import android.kanzz.com.myshop.fragment.HomeFragment;
import android.kanzz.com.myshop.fragment.HotFragment;
import android.kanzz.com.myshop.fragment.MineFragment;
import android.kanzz.com.myshop.widget.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private LayoutInflater mInflater;

    private FragmentTabHost mTabHost;

    private List<Tab> mTabs=new ArrayList<>(5);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab();
    }

    private void initTab(){
        Tab tab_home=new Tab(HomeFragment.class,R.string.home,R.drawable.selector_icon_home);
        Tab tab_hot=new Tab(HotFragment.class,R.string.hot,R.drawable.selector_icon_hot);
        Tab tab_category=new Tab(CategoryFragment.class,R.string.category,R.drawable.selector_icon_category);
        Tab tab_cart=new Tab(CartFragment.class,R.string.cart,R.drawable.selector_icon_cart);
        Tab tab_mine=new Tab(MineFragment.class,R.string.mine,R.drawable.selector_icon_mine);

        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_category);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);


        mInflater=LayoutInflater.from(this);
        mTabHost=(FragmentTabHost)findViewById(R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

        for (Tab tab:mTabs){
            TabHost.TabSpec tabSpec=mTabHost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabHost.addTab(tabSpec,tab.getFragnment(),null);
        }
        //去掉分隔线
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabHost.setCurrentTab(0);
    }

    private View buildIndicator(Tab tab){
        View view =mInflater.inflate(R.layout.tab_indicator,null);
         ImageView img=(ImageView)view.findViewById(R.id.icon_tab);
        TextView text=(TextView)view.findViewById(R.id.txt_indicator);
        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }
}
