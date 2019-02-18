package com.example.day0218.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day0218.R;
import com.example.day0218.api.Api;
import com.example.day0218.bean.JsonBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * @Auther: 李
 * @Date: 2019/2/18 09:21:07
 * @Description:
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<JsonBean.DataBeanX.DataBean> list;
    private Context context;
    private final int TYPE_ONE = 0;
    private final int TYPE_TWO = 1;


    public RecyclerViewAdapter(List<JsonBean.DataBeanX.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_ONE){
            return new ViewHolder1(LayoutInflater.from(context).inflate(R.layout.item1,null));
        }else {
            return new ViewHolder2(LayoutInflater.from(context).inflate(R.layout.item2,null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder1){
            ((ViewHolder1) viewHolder).item1_text.setText(list.get(i).getTitle());
            ImageLoader.getInstance().displayImage(Api.BASE_URL+list.get(i).getPics().get(0),((ViewHolder1) viewHolder).item1_img);
        }else {
            ((ViewHolder2) viewHolder).item2_text.setText(list.get(i).getTitle());
            ImageLoader.getInstance().displayImage(Api.BASE_URL+list.get(i).getPics().get(0),((ViewHolder2) viewHolder).item2_img);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position%4==0){
            return TYPE_TWO;
        }else {
            return TYPE_ONE;
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private final TextView item1_text;
        private final ImageView item1_img;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            item1_text = itemView.findViewById(R.id.item1_text);
            item1_img = itemView.findViewById(R.id.item1_img);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {

        private final TextView item2_text;
        private final ImageView item2_img;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            item2_text = itemView.findViewById(R.id.item2_text);
            item2_img = itemView.findViewById(R.id.item2_img);
        }
    }
}
