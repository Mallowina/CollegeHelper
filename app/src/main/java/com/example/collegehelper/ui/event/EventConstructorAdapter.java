package com.example.collegehelper.ui.event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.collegehelper.R;
import com.example.collegehelper.StateAdapter;
import com.example.collegehelper.ui.event.EventConstructorAdapter;

import java.util.List;

public class EventConstructorAdapter extends RecyclerView.Adapter<EventConstructorAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<EventConstructor> events;

    public EventConstructorAdapter(Context context, List<EventConstructor> events) {
        this.events = events;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public EventConstructorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventConstructorAdapter.ViewHolder holder, int position) {
        EventConstructor eventConstructor = events.get(position);
        /**Здесь можно установить выбор события с помощью getТоЧтоНамНадо()*/
        holder.nameView.setText(eventConstructor.getName());
        holder.descView.setText(eventConstructor.getDesc());
        holder.dateView.setText(eventConstructor.getDate());
        holder.groupView.setText(eventConstructor.getGroup());
        holder.courseView.setText(eventConstructor.getCourse());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, descView, dateView, groupView, courseView;
        ViewHolder(View view){
            super(view);
            nameView = (TextView) view.findViewById(R.id.name);
            descView = (TextView) view.findViewById(R.id.desc);
            dateView = (TextView) view.findViewById(R.id.date);
            groupView = (TextView) view.findViewById(R.id.group);
            courseView = (TextView) view.findViewById(R.id.course);
        }
    }
}
