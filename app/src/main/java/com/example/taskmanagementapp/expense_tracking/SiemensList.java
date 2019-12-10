package com.example.taskmanagementapp.expense_tracking;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.taskmanagementapp.R;

import java.util.List;

public class SiemensList extends ArrayAdapter<SiemensDetail> {

    private Activity context;
    private List<SiemensDetail> siemensList;

  public SiemensList(Activity context,List<SiemensDetail> siemensList){
            super(context, R.layout.list_layout_si,siemensList);
      this.context = context;
      this.siemensList = siemensList;
  }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater  inflater= context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout_si,null,true);
        TextView textview_S_type = listViewItem.findViewById(R.id.S_type_list);
        TextView textView_S_site = listViewItem.findViewById(R.id.S_site_list);
        TextView textView_S_date = listViewItem.findViewById(R.id.S_date_list);
        TextView textView_S_price = listViewItem.findViewById(R.id.S_price_list);
        TextView textView_S_Desc = listViewItem.findViewById(R.id.S_Description_list);

        SiemensDetail siemensDetail = siemensList.get(position);
        textview_S_type.setText(siemensDetail.getExpenseTypeSpinner_D());
        textView_S_site.setText(siemensDetail.getExpense_site_D());
        textView_S_date.setText(siemensDetail.getDateExpense_D());
        textView_S_price.setText(siemensDetail.getPriceEx_D());
        textView_S_Desc.setText(siemensDetail.getDescription_D());

        return listViewItem;
    }
}
