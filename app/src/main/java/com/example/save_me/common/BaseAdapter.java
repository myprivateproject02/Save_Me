package com.example.save_me.common;

import android.view.LayoutInflater;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


import com.example.save_me.BR;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter<D> extends RecyclerView.Adapter<BaseAdapter<?>.BaseViewHolder> {

    @LayoutRes
    private final int itemLayout;
    private OnItemActionListener actionListener;

    private List<D> list;

    public BaseAdapter(int itemLayout) {
        this.itemLayout = itemLayout;
        list = new ArrayList<>();
    }

    public void setActionListener(OnItemActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void setList(List<D> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BaseAdapter<?>.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), itemLayout, parent, false);
        return new BaseViewHolder(binding, actionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.BaseViewHolder holder, int position) {
        holder.bind(position);
    }


    public void addItem(D item) {
        list.add(item);
        notifyItemChanged(list.size() - 1);
    }

    public void addItems(List<D> itemList) {
        int lastIndex = itemList.size() - 1;
        list.addAll(itemList);
        notifyItemRangeInserted(lastIndex, list.size() - 1);
    }

    public void updateItem(D item, int position) {
        list.set(position, item);
        notifyItemChanged(position);
    }


    public D getItemAtPosition(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;
        private OnItemActionListener listener;

        public BaseViewHolder(ViewDataBinding binding, OnItemActionListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;

        }

        public void bind(int position) {
            binding.setVariable(BR.model, list.get(position));
            binding.setVariable(BR.actionListener, listener);
            binding.setVariable(BR.position, getAdapterPosition());
            binding.executePendingBindings();
        }
    }


    public interface OnItemActionListener {
        void onItemClicked(int position);
    }

}
