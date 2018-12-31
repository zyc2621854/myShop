package android.kanzz.com.myshop.adapter;

import android.content.Context;
import android.kanzz.com.myshop.R;
import android.kanzz.com.myshop.bean.HomeCampaign;
import android.kanzz.com.myshop.bean.HomeCategory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    private static int VIEW_TYPE_L=0;
    private static int VIEW_TYPE_R=1;

    private LayoutInflater mInflater;

    private List<HomeCampaign> mDatas;

    private Context mContext;

    public HomeCategoryAdapter(List<HomeCampaign> datas,Context context){
        mDatas=datas;
        this.mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        mInflater=LayoutInflater.from(viewGroup.getContext());
        if(type==VIEW_TYPE_R){
            return new ViewHolder(mInflater.inflate(R.layout.template_home_cardview2,viewGroup,false));
        }
        return new ViewHolder(mInflater.inflate(R.layout.template_home_cardview,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HomeCampaign homeCampaign = mDatas.get(i);
        viewHolder.textTitle.setText(homeCampaign.getTitle());
//        viewHolder.imageViewBig.setImageResource(homeCampaign.getCpOne());
//        viewHolder.imageViewSmallTop.setImageResource(homeCampaign.getImgSmallTop());
//        viewHolder.imageViewSmallBottom.setImageResource(homeCampaigny.getImgSmallBottom());

        Picasso.with(mContext).load(homeCampaign.getCpOne().getImgUrl()).into(viewHolder.imageViewBig);
        Picasso.with(mContext).load(homeCampaign.getCpTwo().getImgUrl()).into(viewHolder.imageViewSmallTop);
        Picasso.with(mContext).load(homeCampaign.getCpThree().getImgUrl()).into(viewHolder.imageViewSmallBottom);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
//        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return VIEW_TYPE_R;
        }
        else return VIEW_TYPE_L;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle;
        ImageView imageViewBig;
        ImageView imageViewSmallTop;
        ImageView imageViewSmallBottom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.text_title);
            imageViewBig = (ImageView) itemView.findViewById(R.id.imgview_big);
            imageViewSmallTop = (ImageView) itemView.findViewById(R.id.imgview_small_top);
            imageViewSmallBottom = (ImageView) itemView.findViewById(R.id.imgview_small_bottom);
        }
    }

}
