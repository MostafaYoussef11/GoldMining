package com.example.goldmining.Utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.goldmining.R;

import java.util.ArrayList;

public class ClientAdapter extends ArrayAdapter<String> {
    Context context;
    String[] ClientName , DebitFBalance , CreditFBalance , ArStTypeBalance , ArStBalance;

    public ClientAdapter(Context context , String CLientName[] , String  DebitFBalance[] , String CreditFBalance[] ,String ArStTypeBalance[] , String ArStBalance[] ) {
        super(context,R.layout.client_list, R.id.NameClient , CLientName);
        this.context = context;
        this.ClientName = CLientName;
        this.DebitFBalance = DebitFBalance;
        this.CreditFBalance = CreditFBalance;
        this.ArStTypeBalance = ArStTypeBalance;
        this.ArStBalance = ArStBalance;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.client_list,parent,false);
        TextView NameClient = row.findViewById(R.id.NameClient);
        TextView creditFirst = row.findViewById(R.id.txtBalanecfirstD);
        TextView debitFirst = row.findViewById(R.id.txtBalanecfirstC);
        TextView nowBalance = row.findViewById(R.id.txtBalanecnow);
        TextView balanceType = row.findViewById(R.id.txtBalanecType);

        NameClient.setText(ClientName[position]);
        creditFirst.setText(CreditFBalance[position]);
        debitFirst.setText(DebitFBalance[position]);
        nowBalance.setText(ArStBalance[position]);
        String chType = ArStTypeBalance[position];
        switch (chType){
            case "C":
                balanceType.setText("دائن");
                break;
            case "D":
                balanceType.setText("مدين");
                break;
            default: balanceType.setText("");
        }

        return row;
    }
}