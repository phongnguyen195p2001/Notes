package br.com.aptech.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import br.com.senaicimatec.sqllite02.R;

public class ActivityInsert extends AppCompatActivity {

    private EditText txtNote;
    private NotaDAO notaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        txtNote = findViewById(R.id.textNota);

        notaContext = new NotaDAO(this);
    }

    public void Salvar(View view){
        //criar objeto aluno
        Nota nota = new Nota();
        nota.setDescricao(txtNote.getText().toString());
        nota.setDataCriacao(new Date());

        long id = notaContext.Inserir(nota);
        Toast.makeText(this, "Nota inserida com sucesso, com o id: " +id, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}