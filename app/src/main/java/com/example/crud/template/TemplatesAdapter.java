package com.example.crud.template;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

import java.util.List;

public class TemplatesAdapter extends RecyclerView.Adapter<TemplateViewHolder> {

    public List<Template> templates;
    public TemplateOnItemActionListener templateOnItemActionListener;

    public void setData(List<Template> templates) {
        this.templates = templates;
        notifyDataSetChanged();
    }

    public void setTemplateOnItemActionListener(TemplateOnItemActionListener templateOnItemActionListener) {
        this.templateOnItemActionListener = templateOnItemActionListener;
    }

    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.templates_item, parent, false);
        TemplateViewHolder templateViewHolder = new TemplateViewHolder(view);
        return templateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        Template template = templates.get(position);
        holder.messageTxt.setText(template.message);
        holder.delete_ib.setOnClickListener(view -> {
            templateOnItemActionListener.onDelete(template.id);
        });
        holder.itemView.setOnClickListener(view -> {
            templateOnItemActionListener.onEdit(template);
        });
    }

    @Override
    public int getItemCount() {
        return templates.size();
    }
}
