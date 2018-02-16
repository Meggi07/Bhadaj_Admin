package anandniketan.com.bhadajadmin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import anandniketan.com.bhadajadmin.Model.Student.FinalArrayStudentModel;
import anandniketan.com.bhadajadmin.Model.Student.StudentAttendanceModel;
import anandniketan.com.bhadajadmin.R;


/**
 * Created by admsandroid on 1/30/2018.
 */

public class AnnouncmentAdpater extends RecyclerView.Adapter<AnnouncmentAdpater.MyViewHolder> {
    private Context context;
    private StudentAttendanceModel announcmentModel;
    SpannableStringBuilder discriptionSpanned;
    String discriptionStr;

    public AnnouncmentAdpater(Context mContext, StudentAttendanceModel announcmentModel) {
        this.context = mContext;
        this.announcmentModel = announcmentModel;
    }


    @Override
    public AnnouncmentAdpater.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcment_list, parent, false);
        return new AnnouncmentAdpater.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnnouncmentAdpater.MyViewHolder holder, int position) {
        String sr = String.valueOf(position + 1);
        final FinalArrayStudentModel result = announcmentModel.getFinalArray().get(position);
        holder.index_txt.setText(sr);
        holder.date_txt.setText(result.getCirDate());
        holder.subject_txt.setText(result.getCirSubject());
        holder.order_txt.setText(String.valueOf(result.getCirOrder()));
        holder.status_txt.setText(result.getCirStatus());
        discriptionStr = result.getCirDescription();
        discriptionSpanned = (SpannableStringBuilder) Html.fromHtml(discriptionStr);
        discriptionSpanned = trimSpannable(discriptionSpanned);
        holder.discription_txt.setText(discriptionSpanned, TextView.BufferType.SPANNABLE);
    }

    @Override
    public int getItemCount() {
        return announcmentModel.getFinalArray().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView index_txt, date_txt, subject_txt, discription_txt, order_txt, status_txt;


        public MyViewHolder(View itemView) {
            super(itemView);
            index_txt = (TextView) itemView.findViewById(R.id.index_txt);
            date_txt = (TextView) itemView.findViewById(R.id.date_txt);
            subject_txt = (TextView) itemView.findViewById(R.id.subject_txt);
            discription_txt = (TextView) itemView.findViewById(R.id.discription_txt);
            order_txt = (TextView) itemView.findViewById(R.id.order_txt);
            status_txt = (TextView) itemView.findViewById(R.id.status_txt);

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
