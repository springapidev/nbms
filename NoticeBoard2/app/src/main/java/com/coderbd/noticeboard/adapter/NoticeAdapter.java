package com.coderbd.noticeboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderbd.noticeboard.R;
import com.coderbd.noticeboard.model.Notice;
import java.util.List;


public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    Context mCtx;
    List<Notice> noticeList;

    public NoticeAdapter(Context mCtx, List<Notice> noticeList) {
        this.mCtx = mCtx;
        this.noticeList = noticeList;
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.notice_list,
                parent, false);
        NoticeViewHolder viewHolder = new NoticeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder holder, int position) {
        Notice notice = noticeList.get(position);

        holder.textViewTitle.setText(notice.getTitle());
        StringBuilder sb=new StringBuilder();
        sb.append(notice.getDetails()+"\n");
        sb.append("Dep: "+notice.getDepartment()+" Date: "+notice.getPublishDate()+"\n");
        holder.textViewDetails.setText(sb);
        //holder.textViewPubLishedBy.setText(notice.getCreatedBy());
       // holder.textviewPublistDate.setText(String.valueOf(String.valueOf(notice.getPublishDate())));
       // holder.textViewDep.setText(notice.getDepartment());
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage(), null));
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder {

        ImageView textViewPdf;
        TextView textViewTitle, textViewDetails, textViewPubLishedBy, textViewDep,textviewPublistDate;

        public NoticeViewHolder(View itemView) {
            super(itemView);
      //      textViewPdf = itemView.findViewById(R.id.imageView14);
            textViewTitle = itemView.findViewById(R.id.textView2);
            textViewDetails = itemView.findViewById(R.id.textView3);
         //   textViewPubLishedBy = itemView.findViewById(R.id.textView9);
         //   textViewDep = itemView.findViewById(R.id.textView10);
         //   textviewPublistDate = itemView.findViewById(R.id.textView10);

        }
    }
}