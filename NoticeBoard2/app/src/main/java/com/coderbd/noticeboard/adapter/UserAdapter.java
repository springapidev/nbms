package com.coderbd.noticeboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.coderbd.noticeboard.R;
import com.coderbd.noticeboard.model.User;

import java.util.List;



public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    Context mCtx;
    List<User> userList;

    public UserAdapter(Context mCtx, List<User> userList) {
        this.mCtx = mCtx;
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.user_list,
                parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userList.get(position);

        holder.textViewName.setText(user.getName());
        holder.textViewDep.setText(user.getDepartment());
        holder.textViewUserType.setText(String.valueOf(user.getUserType()));
        holder.textviewDate.setText(String.valueOf(String.valueOf(user.getCreateDate())));
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage(), null));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName, textViewDep, textViewUserType, textviewDate;

        public UserViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView14);
            textViewName = itemView.findViewById(R.id.textView7);
            textViewDep = itemView.findViewById(R.id.textView8);
            textViewUserType = itemView.findViewById(R.id.textView9);
            textviewDate = itemView.findViewById(R.id.textView10);

        }
    }
}