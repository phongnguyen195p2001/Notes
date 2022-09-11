package br.com.aptech.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

public class NotaDAO {
    private Conexao conexao;
    private SQLiteDatabase db;
    private SQLiteDatabase RO;
    private String TableName = "nota";

    public NotaDAO(Context context){
        conexao = new Conexao(context);
        db = conexao.getWritableDatabase();
        RO = conexao.getReadableDatabase();
    }

    public long Inserir (Nota nota){
        ContentValues values = new ContentValues();
        values.put("Descricao", nota.getDescricao());
        values.put("DataCriacao", nota.getDataCriacao().toString());

        return db.insert(TableName, null, values);
    }

    public void Delete(int notaId) {
        String str = String.valueOf(notaId);
        db.delete(TableName, "id=?", new String[]{ str });
    }

    public ArrayList<Nota> FilterBy(String filter) {
        try {
            Cursor cursorNota = RO.rawQuery("select * from " + TableName + " where Descricao like '%" + filter + "%'", null);

            return getNotasFromContext(cursorNota);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<Nota>();
        }
    }

    public ArrayList<Nota> get() {
        try {
            Cursor cursorNota = RO.rawQuery("select * from " + TableName, null);

            return getNotasFromContext(cursorNota);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<Nota>();
        }

    }

    @NonNull
    private ArrayList<Nota> getNotasFromContext(Cursor cursorNota) {
        ArrayList<Nota> notaList = new ArrayList<>();

        if (cursorNota.moveToFirst()) {
            do {
                Nota novaNota = new Nota();

                int idIndex = cursorNota.getColumnIndex("Id");
                int descricaoIndex = cursorNota.getColumnIndex("Descricao");
                int dataCriacaoIndex = cursorNota.getColumnIndex("DataCriacao");

                String indexValue = cursorNota.getString(idIndex);

                int id = Integer.parseInt(indexValue);

                novaNota.setId(id);
                novaNota.setDescricao(cursorNota.getString(descricaoIndex));
                novaNota.setDataCriacao(new Date(cursorNota.getString(dataCriacaoIndex)));

                notaList.add(novaNota);
            } while (cursorNota.moveToNext());
        }

        cursorNota.close();

        return notaList;
    }
}
