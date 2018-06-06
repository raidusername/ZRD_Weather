package com.weather.zrodo.zrd_weather.Fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;

import android.os.Build;
import android.os.Bundle;

import android.support.annotation.Nullable;

import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.zrodo.zrd_weather.MainActivity;
import com.weather.zrodo.zrd_weather.R;
import com.weather.zrodo.zrd_weather.db.Cities;
import com.weather.zrodo.zrd_weather.db.County;
import com.weather.zrodo.zrd_weather.db.Provinces;
import com.weather.zrodo.zrd_weather.utils.HttpUtil;
import com.weather.zrodo.zrd_weather.utils.Utillty;
import com.weather.zrodo.zrd_weather.zrd_Interface.Zrd_Interface;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChooseArea extends Fragment {
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    private ProgressDialog dialog;
    private TextView text_title;
    private Button btn_back;
    private ListView list_area;
    private ArrayAdapter adapter;
    private List<String> datalist = new ArrayList<>();
    private List<Provinces> ls_province;
    private List<Cities> ls_city;
    private List<County> ls_county;
    private Provinces provinces;
    private Cities city;
    private int selectLevel;
    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.activity_city_choose, container, false);
        text_title = (TextView) view.findViewById(R.id.text_title);
        btn_back = (Button) view.findViewById(R.id.btn_back);
        list_area = (ListView) view.findViewById(R.id.list_area);
        adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, datalist);
        list_area.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectLevel == LEVEL_PROVINCE) {
                    provinces = ls_province.get(position);
                    qureyCities();

                } else if (selectLevel == LEVEL_CITY) {
                    city = ls_city.get(position);
                    qureyCounties();
                }

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectLevel == LEVEL_COUNTY) {

                    qureyCities();

                } else if (selectLevel == LEVEL_CITY) {

                   qureyPrivicen();
                }
            }
        });
        qureyPrivicen();
    }




    /**
     * 查询所有的省优先查数据库
     */
    public void qureyPrivicen() {
        text_title.setText("中国");
        btn_back.setVisibility(View.GONE);
        ls_province = DataSupport.findAll(Provinces.class);
        if (ls_province.size() > 0) {
            datalist.clear();
            for (Provinces p : ls_province) {
                datalist.add(p.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            list_area.setSelection(0);
            selectLevel = LEVEL_PROVINCE;

        } else {

            String adress = Zrd_Interface.getProvincecodes();
            qureyFtommService(adress, "Provinces");

        }
    }

    public void qureyCities() {

        text_title.setText(provinces.getProvinceName());
        btn_back.setVisibility(View.VISIBLE);
        ls_city=DataSupport.where("provincecode= ?",String.valueOf(provinces.getProvincecode())).find(Cities.class);
        if (ls_city.size()>0){
            datalist.clear();
            for (Cities city:ls_city){
                datalist.add(city.getCityName());
            }

            adapter.notifyDataSetChanged();
            list_area.setSelection(0);
            selectLevel = LEVEL_CITY;
        }else {
            int provincecode=provinces.getProvincecode();
            String address=Zrd_Interface.getCities(provincecode);
            qureyFtommService(address,"Cities");
        }
    }

    public void qureyCounties() {
        text_title.setText(city.getCityName());
        btn_back.setVisibility(View.VISIBLE);
        ls_county=DataSupport.where("citycode= ?",String.valueOf(city.getCitycode())).find(County.class);
        if (ls_county.size()>0){
            datalist.clear();
            for (County county:ls_county){
                datalist.add(county.getCountyName());
            }

            adapter.notifyDataSetChanged();
            list_area.setSelection(0);
            selectLevel = LEVEL_COUNTY;
        }else {
            int provincecode=provinces.getProvincecode();
            int citycode=city.getCitycode();
            String address=Zrd_Interface.getCounty(provincecode,citycode);
            qureyFtommService(address,"County");
        }

    }

    private void qureyFtommService(String address, final String type) {
        showDialogprogress();
        Log.d("qureyFtommService", address);
        Log.d("qureyFtommService", type);
        HttpUtil.sendrequetwithokhttp(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        closeDialogprogress();
                        Toast.makeText(view.getContext(),"加载失败",Toast.LENGTH_SHORT).show(); }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //
                String responseText = response.body().string();
                Log.d("qureyFtommService", responseText);
                boolean result = false;
                if ("Provinces".equals(type)) {
                    result = Utillty.jsonprovince(responseText);
                } else if ("Cities".equals(type)) {
                    result = Utillty.jsoncity(responseText, provinces.getProvincecode());
                } else if ("County".equals(type)) {
                    result = Utillty.jsoncounty(responseText, city.getCitycode());
                }
                if (result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeDialogprogress();
                            if ("Provinces".equals(type)) {
                                qureyPrivicen();
                            } else if ("Cities".equals(type)) {
                                qureyCities();
                            } else if ("County".equals(type)) {
                                qureyCounties();
                            }
                        }
                    });
                }

            }
        });


    }

    /**
     * 显示进度光
     **/
    private void showDialogprogress() {
        if (dialog == null) {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("正在加载中");
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();
    }

    /**
     * 关闭进度框
     */
    private void closeDialogprogress() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
