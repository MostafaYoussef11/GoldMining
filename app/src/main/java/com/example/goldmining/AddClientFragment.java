package com.example.goldmining;

import android.content.Context;
import android.os.Bundle;
import com.example.goldmining.Entity.MillClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.goldmining.Utilities.Tools;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddClientFragment extends Fragment {

//    private  ArrayAdapter<String> arrayAdapter;
//    private  ArrayList<String> arrayList = new ArrayList<>();
//    private  RequestQueue requestQueue  ; //= Volley.newRequestQueue(this);

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddClientFragment() {
        // Required empty public constructor
//        requestQueue = Volley.newRequestQueue(this);

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddClientFragment newInstance(String param1, String param2) {
        AddClientFragment fragment = new AddClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestQueue = Volley.newRequestQueue(getActivity());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize View
        View view = inflater.inflate(R.layout.fragment_add_client, container, false);
        MillClient millClient = new MillClient();
        //Initialize and assign Variable
        TextView txtId , txtname , txtIdClient;
        String IdClient = "";
        txtId = view.findViewById(R.id.tv17);
        txtIdClient = view.findViewById(R.id.idClient);
        txtname = view.findViewById(R.id.txtNameClinet);
        EditText edName = view.findViewById(R.id.edNameClient);
        EditText edDebit = view.findViewById(R.id.edDebit);
        EditText edCredit = view.findViewById(R.id.edCredit);
        Spinner spinner = view.findViewById(R.id.spinner);
        Button save = view.findViewById(R.id.btnsaveAddClient);
        //GetAutoId
        IdClient = Tools.getAutoId("millclients","id_millclients");
        txtIdClient.setText(IdClient);
        edCredit.setText("0.00");
        edDebit.setText("0.00");
        //values


        //fillSpinner
        String url = "Mill_Client/sptypeclientmill";
       // String url = "Mill_Client/spinner";
        Tools.fillSpinner(getContext(),url ,getActivity() ,spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
               // int id = position +1;
                if(position == 0){
                    millClient.setId_typeclientmill("1");
                }else {
                    millClient.setId_typeclientmill("2");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameClient = edName.getText().toString();
                String debit = edDebit.getText().toString();
                String credit = edCredit.getText().toString();
                millClient.setId_millclients(txtIdClient.getText().toString());
                millClient.setBalancefirstcredit(credit);
                millClient.setBalancefirstdebit(debit);
                millClient.setName_millclients(nameClient);
                if("".equals(nameClient) || nameClient.equals(null)){
                    Toast.makeText(getContext(), "اسم العميل غير مكتوب", Toast.LENGTH_SHORT).show();
                }else {
                    if(millClient.isAdd()){
                       // Toast.makeText(getContext(), "تم الحفظ بنجاح", Toast.LENGTH_SHORT).show();
                        edCredit.setText("0.00");
                        edDebit.setText("0.00");
                        edName.setText("");
                        txtIdClient.setText(Tools.getAutoId("millclients","id_millclients"));
                    }
                    Toast.makeText(getContext(), millClient.getResult(), Toast.LENGTH_SHORT).show();
                }


            }
        });
        return view;
    }








}

/*
*
*
*
*
*
*
*
*
*
* */