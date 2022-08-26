package com.example.isidigital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private ArrayList<ListeProcessModele> courseModalArrayList;
    private Context context;

    public CourseAdapter(ArrayList<ListeProcessModele> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;}



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListeProcessModele modal = courseModalArrayList.get(position);
        holder.courseNameTV.setText(modal.getCourseName());

        //Picasso.get().load(modal.getCourseimg()).into(holder.courseIV);
    }

    @Override
    public int getItemCount() {
        return courseModalArrayList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        // creating variables for our views.
        private TextView courseNameTV, courseModeTV, courseTracksTV;
       // private ImageView courseIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);

           // courseIV = itemView.findViewById(R.id.idIVCourse);
        }
    }
}
