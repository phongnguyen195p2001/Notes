package br.com.aptech.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import br.com.senaicimatec.sqllite02.R;

public class NoteFragment extends Fragment {

    private Nota nota;
    private NotaDAO notaContext;

    public NoteFragment(Nota nota) {
        this.nota = nota;
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment

        notaContext = new NotaDAO(parent.getContext());

        return inflater.inflate(R.layout.frag_note_item, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        TextView txtView = view.findViewById(R.id.text_nota);
        txtView.setText(nota.getDescricao() + "\nCriada em: " + nota.getDataCriacao().toLocaleString());

        Button deleteBtn = view.findViewById((R.id.delete_button));

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("NOTA ID: " + nota.getId());
                notaContext.Delete(nota.getId());
            }
        });
    }
}