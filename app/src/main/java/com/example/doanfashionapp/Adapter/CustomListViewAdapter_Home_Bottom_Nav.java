package com.example.doanfashionapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanfashionapp.DTO.HangSanPham;
import com.example.doanfashionapp.Interface.SelectListener;
import com.example.doanfashionapp.R;

import java.util.ArrayList;

public class CustomListViewAdapter_Home_Bottom_Nav extends ArrayAdapter {

    private int layout;
    private ArrayList<HangSanPham> lsHangSP;
    private Context context;

    private SelectListener listener;

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public ArrayList<HangSanPham> getLsHangSP() {
        return lsHangSP;
    }

    public void setLsHangSP(ArrayList<HangSanPham> lsHangSP) {
        this.lsHangSP = lsHangSP;
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CustomListViewAdapter_Home_Bottom_Nav(@NonNull Context context, int resource, ArrayList<HangSanPham> arrayList,SelectListener listener1) {
        super(context,resource,arrayList);
        this.context=context;
        this.layout=resource;
        this.lsHangSP=arrayList;
        this.listener=listener1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HangSanPham sp =lsHangSP.get(position);
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(layout,null);
        }
        TextView txtTenHang=(TextView) convertView.findViewById(R.id.txtTenHang);
        txtTenHang.setText(sp.getTenHang());
        RecyclerView recyclerView=(RecyclerView) convertView.findViewById(R.id.recyclerViewDSSP);
        CustomRecyclerViewAdapter_Home_Bottom_Nav adapter = new CustomRecyclerViewAdapter_Home_Bottom_Nav(sp.getListSP(),context,listener);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return convertView;
    }
}
