package com.example.crud.template;

import com.example.crud.template.Template;

public interface TemplateOnItemActionListener {

    void onDelete(String id);

    void onEdit(Template template);
}
