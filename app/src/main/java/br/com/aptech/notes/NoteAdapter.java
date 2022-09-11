package br.com.aptech.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.senaicimatec.sqllite02.R;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button messageButton;
        public Button deleteButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.text_nota);
            // messageButton = (Button) itemView.findViewById(R.id.message_button);
            deleteButton = (Button) itemView.findViewById(R.id.delete_button);
        }
    }

    // Store a member variable for the contacts
    private List<Nota> mNotas;

    // Pass in the contact array into the constructor
    public NoteAdapter(List<Nota> notas) {
        mNotas = notas;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.frag_note_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Nota nota = mNotas.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(nota.getDescricao() + "\nCriada em: " + nota.getDataCriacao().toLocaleString());

        Button btn = holder.deleteButton;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("NOTA ID: " + nota.getId());
                NotaDAO ctx = new NotaDAO(view.getContext());
                ctx.Delete(nota.getId());
                int newPosition = holder.getAdapterPosition();
                mNotas.remove(newPosition);
                notifyItemRemoved(newPosition);
                notifyItemRangeChanged(newPosition, mNotas.size());
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mNotas.size();
    }
}