package com.acs.abhijeetp_tesseract.App;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.acs.abhijeetp_tesseract.R;
import com.acs.sdk.ModalAppInfo;
import java.util.ArrayList;
import java.util.List;

public class AdapterAppList extends RecyclerView.Adapter<AdapterAppList.ViewHolder> {

    private RecyclerOnItemClickListener mClickListener;
    List<ModalAppInfo> listAllApp = new ArrayList<>();


    public AdapterAppList(List<ModalAppInfo> arrData, RecyclerOnItemClickListener mClickListener) {
        this.listAllApp = arrData;
        this.mClickListener = mClickListener;
    }

    @Override
    public AdapterAppList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_applist, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAppList.ViewHolder holder, final int position) {

        holder.txtAppName.setText(listAllApp.get(position).getAppName());
        holder.txtPkgName.setText(listAllApp.get(position).getPackages());
        if (listAllApp.get(position).getClassName().equals("")){
            holder.txtClassName.setVisibility(View.GONE);
        }else {
            holder.txtClassName.setVisibility(View.VISIBLE);
            holder.txtClassName.setText(listAllApp.get(position).getClassName());
        }
        holder.txtVerCode.setText(String.valueOf(listAllApp.get(position).getVersionCode()));
        holder.txtVerName.setText(listAllApp.get(position).getVersionName());

        holder.imgIcon.setImageDrawable(listAllApp.get(position).getIcon());
        holder.rowId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAllApp.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtAppName, txtPkgName, txtClassName, txtVerCode, txtVerName;
        de.hdodenhof.circleimageview.CircleImageView imgIcon;
        ConstraintLayout rowId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rowId = itemView.findViewById(R.id.rowId);
            imgIcon = itemView.findViewById(R.id.appIcon);

            txtAppName = itemView.findViewById(R.id.appName);
            txtPkgName = itemView.findViewById(R.id.appPkgName);
            txtClassName = itemView.findViewById(R.id.appClassName);
            txtVerCode = itemView.findViewById(R.id.appVerCode);
            txtVerName = itemView.findViewById(R.id.appVerName);
        }
    }

}
