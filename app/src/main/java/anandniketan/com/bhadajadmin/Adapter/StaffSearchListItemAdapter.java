package anandniketan.com.bhadajadmin.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import anandniketan.com.bhadajadmin.Fragment.Fragment.StaffInquiryProfileFragment;
import anandniketan.com.bhadajadmin.Fragment.Fragment.StudentFragment;
import anandniketan.com.bhadajadmin.Model.HR.SearchStaffModel;
import anandniketan.com.bhadajadmin.Model.Student.AnnouncementModel;
import anandniketan.com.bhadajadmin.Model.Student.FinalArrayStudentModel;
import anandniketan.com.bhadajadmin.R;
import anandniketan.com.bhadajadmin.Utility.AppConfiguration;

public class StaffSearchListItemAdapter extends RecyclerView.Adapter<StaffSearchListItemAdapter.MyViewHolder> {
    private Context context;
    private List<SearchStaffModel.FinalArray> announcmentModel;
    SpannableStringBuilder discriptionSpanned;
    String discriptionStr;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    public StaffSearchListItemAdapter(Context mContext, List<SearchStaffModel.FinalArray> announcmentModel) {
        this.context = mContext;
        this.announcmentModel = announcmentModel;
    }


    @Override
    public StaffSearchListItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_staff_search, parent, false);
        return new StaffSearchListItemAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StaffSearchListItemAdapter.MyViewHolder holder, final int position) {
        String sr = String.valueOf(position + 1);
        final SearchStaffModel.FinalArray result = announcmentModel.get(position);
        holder.employee_txt.setText(result.getName()+"("+result.getEmpCode()+")");
        holder.fh_name_txt.setText(result.getFatherHusbandName());
        holder.dob_txt.setText(result.getDateOfBirth());
        holder.department_txt.setText(String.valueOf(result.getDepartment()));

        holder.view_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    fragment = new StaffInquiryProfileFragment();
                    fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                    Bundle bundle = new Bundle();

                    ArrayList<SearchStaffModel.FinalArray> data = new ArrayList<>();
                    data.add(announcmentModel.get(position));
                    bundle.putParcelableArrayList("dataList",data);
                    fragment.setArguments(bundle);
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right).replace(R.id.frame_container,fragment).commit();
                    AppConfiguration.firsttimeback = true;
                    AppConfiguration.position = 61;

                }catch (Exception ex){
                    ex.printStackTrace();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return announcmentModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView employee_txt,fh_name_txt, dob_txt, department_txt, view_txt;


        public MyViewHolder(View itemView) {
            super(itemView);
            employee_txt = (TextView) itemView.findViewById(R.id.employee_txt);
            fh_name_txt = (TextView) itemView.findViewById(R.id.fh_name_txt);
            dob_txt = (TextView) itemView.findViewById(R.id.dob_txt);
            department_txt = (TextView) itemView.findViewById(R.id.department_txt);
            view_txt = (TextView) itemView.findViewById(R.id.view_txt);

        }
    }

    private SpannableStringBuilder trimSpannable(SpannableStringBuilder spannable) {
        int trimStart = 0;
        int trimEnd = 0;
        String text = spannable.toString();

        while (text.length() > 0 && text.startsWith("\n")) {
            text = text.substring(1);
            trimStart += 1;
        }
        while (text.length() > 0 && text.endsWith("\n")) {
            text = text.substring(0, text.length() - 1);
            trimEnd += 1;
        }
        return spannable.delete(0, trimStart).delete(spannable.length() - trimEnd, spannable.length());
    }
}