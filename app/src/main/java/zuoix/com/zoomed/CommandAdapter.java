package zuoix.com.zoomed;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import zuoix.com.zoomed.activities.SharedPref;

public class CommandAdapter extends RecyclerView.Adapter<CommandAdapter.ViewHolder> {
    public Context context;
    public String id;
    View v;


    public CommandAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.command_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommandModel listItem = BaseApplication.getInstance().getCommandList(new SharedPref(context).getGeneration()).get(position);
        holder.name.setText(listItem.getName());
        holder.image.setImageDrawable(listItem.getImage());

    }


    @Override
    public int getItemCount() {
        return BaseApplication.getInstance()
                .getCommandList(new SharedPref(context).getGeneration())==null?0
                :BaseApplication.getInstance().getCommandList(new SharedPref(context)
                .getGeneration()).size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        TextView name;
        ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            name = mView.findViewById(R.id.title);
            image = mView.findViewById(R.id.image);

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}


