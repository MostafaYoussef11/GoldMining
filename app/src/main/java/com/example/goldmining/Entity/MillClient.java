package com.example.goldmining.Entity;

import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;



public class MillClient implements allEntitdb {
    private String name_millclients  , result;
    private String id_millclients , id_typeclientmill ;
    private String 	balancefirstdebit , balancefirstcredit ;

    // Const method
    public MillClient(String name_millclients, String id_millclients, String id_typeclientmill, String balancefirstdebit, String balancefirstcredit) {
        this.name_millclients = name_millclients;
        this.id_millclients = id_millclients;
        this.id_typeclientmill = id_typeclientmill;
        this.balancefirstdebit = balancefirstdebit;
        this.balancefirstcredit = balancefirstcredit;
    }
    public MillClient(){}
    // get and set
    public String getName_millclients() {
        return name_millclients;
    }
    public void setName_millclients(String name_millclients) {
        this.name_millclients = name_millclients;
    }
    public String getId_millclients() {
        return id_millclients;
    }
    public void setId_millclients(String  id_millclients) {
        this.id_millclients = id_millclients;
    }
    public String getId_typeclientmill() {
        return id_typeclientmill;
    }
    public void setId_typeclientmill(String id_typeclientmill) {
        this.id_typeclientmill = id_typeclientmill;
    }
    public String getBalancefirstdebit() {
        return balancefirstdebit;
    }
    public void setBalancefirstdebit(String balancefirstdebit) {
        this.balancefirstdebit = balancefirstdebit;
    }
    public String getBalancefirstcredit() {
        return balancefirstcredit;
    }
    public void setBalancefirstcredit(String balancefirstcredit) {
        this.balancefirstcredit = balancefirstcredit;
    }

    public String getResult() {
        return result;
    }

    @Override
    public boolean isAdd() {
        //String result = "";
        boolean isAdd = false;
        String url = "http://egicta.com/Mill_Client/addnewClient.php";
        String [] filed = new String[5];
        filed[0] = "id_millclients";
        filed[1]="name_millclients";
        filed[2]="id_typeclientmill";
        filed[3]= "balancefirstdebit";
        filed[4]="balancefirstcredit";
        String[] data = new String[5];
        data[0]=getId_millclients();
        data[1]=getName_millclients();
        data[2]=getId_typeclientmill();
        data[3]=getBalancefirstdebit();
        data[4]=getBalancefirstcredit();

        PutData putData = new PutData(url, "POST",filed,data);
        if (putData.startPut()){
            if(putData.onComplete()){
                result = putData.getResult();
               // String isOk = "New record created successfully";
                if("saved".equals(result)){
                    isAdd = true;
                  //  Toast.makeText(, "", Toast.LENGTH_SHORT).show();
                }
            }else {
                isAdd = false;
            }

        }else {
        isAdd = false;
    }
        return isAdd;
    }
}
