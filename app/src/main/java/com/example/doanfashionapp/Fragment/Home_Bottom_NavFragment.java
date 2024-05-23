package com.example.doanfashionapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doanfashionapp.ActivityThongTinSanPham;
import com.example.doanfashionapp.DAO.DAO_HangSanPham;
import com.example.doanfashionapp.DTO.HangSanPham;
import com.example.doanfashionapp.Adapter.CustomListViewAdapter_Home_Bottom_Nav;
import com.example.doanfashionapp.DTO.SanPham;
import com.example.doanfashionapp.Interface.SelectListener;
import com.example.doanfashionapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Bottom_NavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_Bottom_NavFragment extends Fragment implements MenuProvider ,SelectListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText edtSearch;
    private ImageButton btnFilter;
    private ListView listView;

    private ArrayList<HangSanPham> arrayList=new ArrayList<>();
    private CustomListViewAdapter_Home_Bottom_Nav adapter;


    public Home_Bottom_NavFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Bottom_NavFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_Bottom_NavFragment newInstance(String param1, String param2) {
        Home_Bottom_NavFragment fragment = new Home_Bottom_NavFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home__bottom__nav, container, false);
        MenuHost menuHost= requireActivity();
        menuHost.addMenuProvider(this,getViewLifecycleOwner(), Lifecycle.State.RESUMED);
        edtSearch=(EditText) view.findViewById(R.id.edtSearch);
        btnFilter=(ImageButton) view.findViewById(R.id.btnFilter);
        listView=(ListView) view.findViewById(R.id.listViewDSSP);
        DAO_HangSanPham dao_hangSanPham=new DAO_HangSanPham(getContext());
        arrayList= dao_hangSanPham.loadHangSanPham();
        adapter=new CustomListViewAdapter_Home_Bottom_Nav(getContext(),R.layout.custom_listview_home_bottom_nav,arrayList,this);

        listView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.option_menu,menu);

    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }


    @Override
    public void onItemClicked(SanPham sp) {
        Intent intent = new Intent(getContext(), ActivityThongTinSanPham.class);
        intent.putExtra("tenSP",sp.getTenSanPham());
        intent.putExtra("giaSP",sp.getGiaSanPham());
        startActivity(intent);
    }

    @Override
    public void onItemFavoriteClicked(SanPham sp) {

    }
}