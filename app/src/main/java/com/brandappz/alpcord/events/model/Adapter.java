package com.brandappz.alpcord.events.model;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.brandappz.alpcord.events.R;
import com.brandappz.alpcord.events.fragments.Agendaadapter;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.List;

class MyViewHolderWithoutChild extends RecyclerView.ViewHolder{
    public TextView textView;

    public MyViewHolderWithoutChild(View itemView) {

        super(itemView);
        textView=itemView.findViewById(R.id.textview);

    }
}
class MyViewHolderWithChild extends RecyclerView.ViewHolder{
    public TextView textView,textviewchild;
    public RelativeLayout button1;
    public ExpandableLinearLayout expandableLayout;
    public MyViewHolderWithChild(View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.textview);
        textviewchild=itemView.findViewById(R.id.textviewchild);
        button1=itemView.findViewById(R.id.button1);
        expandableLayout=itemView.findViewById(R.id.expandablelayout);

    }
}
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Item> items;
    Context context;
    SparseBooleanArray expandState= new SparseBooleanArray();

    public Adapter(List<Item> items) {
        this.items = items;
        for(int i=0;i<items.size();i++){
            expandState.append(i,false);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position).isExpandable)
            return 1;
        else
            return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        if(viewType==0){
            LayoutInflater inflater = LayoutInflater.from(context);
            View view=inflater.inflate(R.layout.withchildlayout,parent,false);
            return new MyViewHolderWithChild(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
          switch (holder.getItemViewType()){
              case 0:
              {
                  MyViewHolderWithoutChild viewholder= (MyViewHolderWithoutChild)holder;
                  Item item =items.get(position);
                  viewholder.setIsRecyclable(false);
                  viewholder.textView.setText(item.getText1());
              }
              break;
              case 1:
              {
                  final MyViewHolderWithChild viewholder= (MyViewHolderWithChild)holder;
                  Item item =items.get(position);
                  viewholder.setIsRecyclable(false);
                  viewholder.textView.setText(item.getText1());
                  viewholder.expandableLayout.setInRecyclerView(true);
                  viewholder.expandableLayout.setExpanded(expandState.get(position));
                  viewholder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
                      @Override
                      public void onPreOpen() {
                          changeRotate(viewholder.button1,0f,180f).start();
                          expandState.put(position,true);

                      }

                      @Override
                      public void onPreClose() {
                          changeRotate(viewholder.button1,180f,0f).start();
                          expandState.put(position,false);
                      }


                  });

                  viewholder.button1.setRotation(expandState.get(position)?180f:0f);
                  viewholder.button1.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          viewholder.expandableLayout.toggle();

                      }
                  });
                  viewholder.textviewchild.setText(items.get(position).getSubText());
              }
                  break;
              default:
                  }

    }

    private ObjectAnimator changeRotate(RelativeLayout button1, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(button1,"rotation",from,to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
