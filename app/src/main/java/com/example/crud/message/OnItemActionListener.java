package com.example.crud.message;

import com.example.crud.message.Message;

public interface OnItemActionListener {

    void onDelete(String id);

    void onEdit(Message message);
}
