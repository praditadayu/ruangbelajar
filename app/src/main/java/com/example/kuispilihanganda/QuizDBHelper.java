package com.example.kuispilihanganda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "quizzes.db";
    private static final int DB_VERSION = 8;

    public static final String CATEGORIES_MTK = "matematika";
    public static final String CATEGORIES_IPA = "ipa";
    public static final String CATEGORIES_IPS = "ips";
    public static final String CATEGORIES_BAHASAINDONESIA = "bahasaindonesia";
    public static final String CATEGORIES_BAHASABALI = "bahasabali";
    public static final String CATEGORIES_BAHASAINGGRIS = "bahasainggris";

    private final String CREATE_TABLE_QUERY = "CREATE TABLE " + QuizContract.QuestionsTable.TABLE_NAME +
            "(" +
            QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
            QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
            QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
            QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
            QuizContract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
            QuizContract.QuestionsTable.COLUMN_ANSWER + " TEXT, " +
            QuizContract.QuestionsTable.COLUMN_CATEGORIES + " TEXT" +
            ")";

    private final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME;

    private SQLiteDatabase db;
    private List<Question> mQuestionList;

    private Bundle categoriesIntentBundle;

    public QuizDBHelper(Context context, Bundle bundle) {
        super(context, DB_NAME, null, DB_VERSION);
        this.categoriesIntentBundle = bundle;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_TABLE_QUERY);

        setUpQuestions();
        insertQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }

    private void setUpQuestions() {
        mQuestionList = new ArrayList<>();

        //questions for categories bahasa indonesia
        mQuestionList.add(new Question("Berikut ini adalah cara-cara bersyukur kepada Allah SWT, kecuali...", "Membaca hamdalah", "Mengerjakan salat lima waktu", "Berpuasa sepanjang waktu", "Belajar dan mengaji Al-Quran", "Berpuasa sepanjang waktu", CATEGORIES_BAHASAINDONESIA));
        mQuestionList.add(new Question("Arti fana yakni...", "Kekal", "Tidak kekal", "Abadi", "Selamanya", "Tidak kekal", CATEGORIES_BAHASAINDONESIA));
        mQuestionList.add(new Question("Batas antara alam dunia dengan alam akhirat disebut...", "Alam barzah", "Yaumul hisab", "Yaumul mahsyar", "Yaumul ba’as", "Alam barzah", CATEGORIES_BAHASABALI));
        mQuestionList.add(new Question("Kiamat kecil di kenal dengan istilah...", "Kiamat Kubro", "Kiamat Sugro", "Kiamat Zalzalah", "Hari Akhir", "Kiamat Sugro", CATEGORIES_BAHASAINDONESIA));
        mQuestionList.add(new Question("Wukuf di Padang Arafah adalah salah satu...", "Syarat wajib Haji", "Sunnah Haji", "Jenis ibadah Haji", "Rukun Haji", "Rukun Haji", CATEGORIES_BAHASABALI));

        //questions for category ips
        mQuestionList.add(new Question("Sosiologi mempunyai empat ciri ilmu, salah satunya adalah ilmu sosiologi selalu berusaha menyusun abstraksi dari hasil observasi yang konkret di lapangan. Ciri sosiologi tersebut dikenal dengan istilah...", "teoritis", "empiris", "kumulatif", "khayalan", "teoritis", CATEGORIES_IPS));
        mQuestionList.add(new Question("Objek sosiologi yang menjelaskan tentang gejala-gejala kehidupan sosial dan proses hubungan antar manusia yang mempengaruhi kesatuan hidup manusia itu sendiri merupakan objek sosiologi...", "material", "formal", "primer", "sekunder", "material", CATEGORIES_IPS));
        mQuestionList.add(new Question("Seorang pemimpin upacara bendera menyiapkan peserta upacara setelah diberi instruksi oleh pembina upacara. Dari contoh di atas interaksi terjadi karena memenuhi syarat...", "kontak dan pertemuan", "kontak dan komunikasi", "pengertian dan komunikasi", "kontak dan sugesti", "kontak dan komunikasi", CATEGORIES_IPS));
        mQuestionList.add(new Question("Negara Amerika Serikat banyak melakukan investasi seperti peminjaman dana pembangunan untuk Indonesia sebagai negara berkembang. Peminjaman dana pembangunan yang dilakukan Amerika di Indonesia adalah globalisasi dalam bentuk...", "globalisasi perdagangan", "globalisasi pembiayaan", "globalisasi produksi", "globalisasi pembanguna", "globalisasi pembiayaan", CATEGORIES_IPS));
        mQuestionList.add(new Question("Sekelompok pemuda yang biasa berkumpul di terminal untuk mabuk-mabukan ditangkap polisi karena sering membuat resah para calon penumpang karena perilaku mereka yang tidak terkendali. Kelompok sosial ini termasuk...", "inconvenient aggregations", "panic crowds", "immoral crowds", "spectator crowds", "immoral crowds", CATEGORIES_IPS));

        //questions for category ipa
        mQuestionList.add(new Question("Berikut ini merupakan komponen biotik di alam adalah...", "Tanah liat", "Batu bata", "Air laut", "Jasad renik", "Jasad renik", CATEGORIES_IPA));
        mQuestionList.add(new Question("Upaya meminimalisasi sampah hasil limbah rumah tangga agar tidak mencemari perairan dapat dilakukan dengan cara mendaur ulang sampah-sampah di sekitar kita. Seperti dibuat menjadi kompos, kerajinan tangan, dan benda berguna lainnya. Upaya tersebut disebut dengan istilah...", "Recycle", "Reuse", "Reduce", "Repair", "Recycle", CATEGORIES_IPA));
        mQuestionList.add(new Question("Batu ginjal merupakan penyakit berbahaya pada sistem ekskresi manusia. Salah satu cara yang dapat dilakukan untuk mencegah penyakit tersebut adalah...", "Banyak mengonsumsi garam mineral", "Banyak mengonsumsi minuman beralkohol", "Banyak mengonsumsi makanan yang mengandung pewarna", "Banyak mengonsumsi air putih", "Banyak mengonsumsi air putih", CATEGORIES_IPA));
        mQuestionList.add(new Question("Penyakit menular pada alat kelamin manusia yang disebabkan oleh bakteri Treponema pallidum disebut...", "HIV", "Gonorhoe", "Sifilis", "Keputihan", "Sifilis", CATEGORIES_IPA));
        mQuestionList.add(new Question("Berikut ini merupakan kondisi yang dapat terjadi jika organ hati rusak adalah...", "Terganggunya fungsi sistem organ", "Dapat menimbulkan penyakit", "Akumulasi suatu toksik dalam tubuh", "Semua jawaban benar", "Semua jawaban benar", CATEGORIES_IPA));

        //questions for category bahasa inggris
        mQuestionList.add(new Question("Dalam menyusun suatu program,langkah pertama yang harus di lakkukan adalah...", "Membuat program", "Membuat Algoritma", "Proses", "Mempelajari program", "Membuat Algoritma", CATEGORIES_BAHASAINGGRIS));
        mQuestionList.add(new Question("Pemberian nama variabel yang benar adalah...", "%nilai", "nilai_mahasiswa", "NamaMahasiswa", "&panjang", "nilai_mahasiswa", CATEGORIES_BAHASAINGGRIS));
        mQuestionList.add(new Question("Suatu program terpisah dalam blok sendiri yang berfungsi sebagai subprogram (program bagian) disebut...", "Variabel", "Deklarasi", "Prosedur", "Constructor", "Prosedur",CATEGORIES_BAHASAINGGRIS));
        mQuestionList.add(new Question("Istilah \"perulangan\" dalam pemograman pascal dikenal dengan...", "Repeating", "Funtion", "Looping", "Replay", "Looping", CATEGORIES_BAHASAINGGRIS));
        mQuestionList.add(new Question("Tipe data untuk TRUE FALSE adalah...", "String", "Boolean", "Byte", "Char", "Boolean", CATEGORIES_BAHASAINGGRIS));

        //questions for category bahasa bali
        mQuestionList.add(new Question("Serangan pertama kali untuk memulai permainan dalam permainan bola voli adalah pengertian dari...", "Smash", "Block", "Servis", "Passing", "Servis", CATEGORIES_BAHASABALI));
        mQuestionList.add(new Question("Setiap set dalam permainan bola voli berakhir ketika salah satu tim memperoleh nilai...", "11 poin", "15 poin", "21 poin", "25 poin", "25 poin", CATEGORIES_BAHASABALI));
        mQuestionList.add(new Question("Menyundul bola di sebut juga dengan...", "Shooting", "Heading", "Passing", "Block", "Heading", CATEGORIES_BAHASABALI));
        mQuestionList.add(new Question("Tembakan sambil melompat dalam bola basket di sebut juga dengan istilah...", "Jump Shoot", "Lay Up", "Rebound", "Pivot", "Jump Shoot", CATEGORIES_BAHASABALI));
        mQuestionList.add(new Question("Rencana untuk melakukan suatu penyerangan atau pertahanan sebelum pertandingan di sebut...", "Taktik", "Tehnik", "Formasi", "Strategi", "Strategi", CATEGORIES_BAHASABALI));

        //questions for category matematika
        mQuestionList.add(new Question("Hasil dari -4 + 8 : 2 adalah...", "-2", "2", "6", "0", "0", CATEGORIES_MTK));
        /*mQuestionList.add(new Question("Sebuah toko kue selama 8 hari dapat membuat 240 kotak kue. Banyak kue yang dapat dibuat oleh toko tersebut selama 12 hari adalah...", "160 kotak", "260 kotak", "360 kotak", "460 kotak", "360 kotak", CATEGORIES_MTK));
        mQuestionList.add(new Question("Pak Arif membeli motor dengan harga Rp15.000.000,00 dan dijual lagi dengan harga Rp16.500.000,00. Berapa perentase keuntungan yang diperoleh?", "1%", "1,5%", "10%", "15%", "10%", CATEGORIES_MTK));
        mQuestionList.add(new Question("Berat rata-rata dari 12 siswa adalah 55 kg dan berat rata-rata 15 orang lainya adalah 45 Berat rata-rata dari keseluruhan kedua kelompok tersebut adalah...", "47 kg", "48 kg", "49 kg", "50 kg", "49 kg", CATEGORIES_MTK));
        mQuestionList.add(new Question("Sebuah lapangan berbentuk lingkaran dengan diameter 56 m. Di sekeliling lapangan akan dipasang lampu dengan jarak 4 m. Berapa banyak lampu yang diperlukan?", "24 buah", "30 buah", "34 buah", "44 buah", "44 buah", CATEGORIES_MTK));*/
    }

    private void insertQuestions() {
        for (Question q : mQuestionList) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(QuizContract.QuestionsTable.COLUMN_QUESTION, q.getmQuestion());
            contentValues.put(QuizContract.QuestionsTable.COLUMN_OPTION1, q.getmOption1());
            contentValues.put(QuizContract.QuestionsTable.COLUMN_OPTION2, q.getmOption2());
            contentValues.put(QuizContract.QuestionsTable.COLUMN_OPTION3, q.getmOption3());
            contentValues.put(QuizContract.QuestionsTable.COLUMN_OPTION4, q.getmOption4());
            contentValues.put(QuizContract.QuestionsTable.COLUMN_ANSWER, q.getmAnswer());
            contentValues.put(QuizContract.QuestionsTable.COLUMN_CATEGORIES, q.getmCategories());
            db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, contentValues);
        }
    }

    public ArrayList<Question> getAllQuestions(String categoriesID) {
        Log.d("TAG", "Getting all questions for : " + categoriesID);
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String SELECT_TABLE_QUERY = "SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME + " WHERE " + QuizContract.QuestionsTable.COLUMN_CATEGORIES + " = \"" + categoriesID + "\"";
        Cursor cursor = db.rawQuery(SELECT_TABLE_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setmQuestion(cursor.getString(cursor.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setmOption1(cursor.getString(cursor.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setmOption2(cursor.getString(cursor.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setmOption3(cursor.getString(cursor.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setmOption4(cursor.getString(cursor.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setmAnswer(cursor.getString(cursor.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }
}