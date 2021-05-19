package com.example.goldmining;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.goldmining.Utilities.ClientAdapter;
import com.example.goldmining.Utilities.GetingData;
import com.example.goldmining.Utilities.Tools;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientFragment newInstance(String param1, String param2) {
        ClientFragment fragment = new ClientFragment();
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

        View view = inflater.inflate(R.layout.fragment_client, container, false);

        ListView ClientList = view.findViewById(R.id.clientList);

        String[] name_millclients = getReslut("name_millclients");
        String[] DebitFBalance = getReslut("balancefirstdebit");
        String[] CreditFBalance = getReslut("balancefirstcredit");
        String[] ArStBalance = getReslut("balance");
        String[] ArStTypeBalance = getReslut("typeBalance");
        //String[] name_millclients = {"1" , "2" , "3" , "asdda" ,"sadrfgr" , "عريب"};
//            int c = name_millclients.length;
//            for(int i = 0 ; i < c ; i++){
//                Toast.makeText(getContext(), name_millclients[i], Toast.LENGTH_SHORT).show();
//            }
        ClientAdapter adapter = new ClientAdapter(getActivity(),name_millclients,DebitFBalance,CreditFBalance,ArStTypeBalance,ArStBalance);
        ClientList.setAdapter(adapter);
        return view;
    }
    private String[] getReslut (String column){
        String[] field = new String[2];
        field[0] = "tableName";
        field[1] = "columnName";
        String[] data = new String[2];
        data[0] = "millclients";
        data[1] = column;//"name_millclients";
        String url = "http://egicta.com/Mill_Client/selectOneColumn.php";//?columnName="+ColumnName+"&tableName="+tableNamput
        GetingData putData  = new GetingData(url,"POST",field,data);
        String[] result = null;
        if(putData.startPut()){
            if(putData.onComplete()){
                try {
                    JSONObject jObject = new JSONObject(putData.getResult());
                    JSONArray jArr = jObject.getJSONArray(column);
                    result = new String[jArr.length()];
                   // Toast.makeText(getActivity(), "Joson Size : "+jArr.length(), Toast.LENGTH_SHORT).show();
                    for(int i = 0 ; i < jArr.length() ; i++){
                        JSONObject jsonObject = jArr.getJSONObject(i);
                        String name = jsonObject.optString(column);
                        result[i] = name;
                       // Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
        return result;

    }







}