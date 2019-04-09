package com.example.bousquleProjet;

import com.example.bousquleProjet.model.Question;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bousquleProjet.QuizContract.*;

import java.util.ArrayList;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //créé la table contenant les questions et les réponses
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    // rajouter des questions à la table avec les réponses possibles

    private void fillQuestionsTable() {
        Question q1 = new Question("Donner la racine carrée de 25", "12", "2", "5", 3);
        addQuestion(q1);
        Question q2 = new Question("Quelle est la capitale du Portugal", "Lisbonne", "Barcelone", "Madrid", 1);
        addQuestion(q2);
        Question q3 = new Question("Traduire ce verbe : 'To hide '", "Grandir", "Se cacher", "Avoir une idée", 2);
        addQuestion(q3);
        Question q4 = new Question("Choisir la bonne orthographe :", "Appeler", "Apeller", "Appeller", 1);
        addQuestion(q4);
        Question q5 = new Question("Quel pays fait partie de l'UE ? ", "Hongrie", "Norvège", "Suisse", 1);
        addQuestion(q5);
        Question q6 = new Question("A quel animal l'adjectif 'hippique' se rapporte-t-il ? ", "Chien", "Cheval", "Canard", 2);
        addQuestion(q6);
        Question q7 = new Question(" Combien de voleurs accompagnaient Ali Baba ? ", "450", "2", "40", 3);
        addQuestion(q7);
        Question q8 = new Question(" La somme des angles d'un triangle est égale à ? (en degrés) ", "180", "124.5", "78", 1);
        addQuestion(q8);
        Question q9 = new Question(" Trouvez l’intrus qui s’est glissé dans les pluriels des mots en ou ? ", "Chou", "Caillou", "Bisou", 3);
        addQuestion(q9);
        Question q10 = new Question(" Comment conjugue-t-on au futur le verbe connaître à la troisième personne du singulier ? ", "Connaîtrait", "Connaîtra", "Aurez connu", 2);
        addQuestion(q10);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
