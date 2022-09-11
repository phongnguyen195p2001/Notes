package br.com.aptech.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class    Conexao  extends SQLiteOpenHelper {

    private static final String name= "bancoNotas2.db";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null , version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS nota (Id INTEGER primary key autoincrement, Descricao VARCHAR(250), DataCriacao VARCHAR(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
