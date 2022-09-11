package br.com.aptech.notes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.com.senaicimatec.sqllite02.R;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addButton;
    private NotaDAO notaContext;
    private SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.add_note);
        search = findViewById(R.id.searchView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add(view);
            }
        });

        notaContext = new NotaDAO(this);

        ArrayList<Nota> note = notaContext.get();

        // Lookup the recyclerview in activity layout
        RecyclerView rvNote = (RecyclerView) findViewById(R.id.note_list);

        CreateAdapter(note, rvNote);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextSubmit(String s) {
                ArrayList<Nota> noteFilter = (ArrayList<Nota>) notaContext.FilterBy(s);
                CreateAdapter(noteFilter, rvNote);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals("")) {
                    ArrayList<Nota> noteFilter = (ArrayList<Nota>) notaContext.get();
                    CreateAdapter(noteFilter, rvNote);
                }
                return true;
            }
        });

        System.out.println("Starting development apk...");
    }

    private void CreateAdapter(ArrayList<Nota> note, RecyclerView rvNote) {
        // Create adapter passing in the sample user data
        NoteAdapter adapter = new NoteAdapter(note);
        rvNote.setAdapter(adapter);
        rvNote.setLayoutManager(new LinearLayoutManager(this));
    }

    public void Add(View view) {
        Intent intent = new Intent(this, ActivityInsert.class);
        startActivity(intent);
    }
}