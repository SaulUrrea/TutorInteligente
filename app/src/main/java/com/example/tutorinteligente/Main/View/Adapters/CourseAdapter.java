package com.example.tutorinteligente.Main.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorinteligente.Main.Models.CursosModel;
import com.example.tutorinteligente.R;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

        private CursosModel[] localDataSet;


        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView tvTitle, tvDescription;

            public ViewHolder(View view) {
                super(view);

                tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                tvDescription = (TextView) view.findViewById(R.id.tvDescription);
            }

            public TextView getTextViewTitle() {
                return tvTitle;
            }
            public TextView getTextViewDescription() {
                return tvDescription;
            }
        }

        public void CustomAdapter(CursosModel[] dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.getTextViewTitle().setText(localDataSet[position].getNombre());
            viewHolder.getTextViewDescription().setText(localDataSet[position].getDescripcion());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.length;
        }
}
