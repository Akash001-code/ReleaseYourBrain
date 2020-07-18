package com.example.releaseyourbrain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.releaseyourbrain.QuizContract.*;

import java.util.ArrayList;


public class Quizdbhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RYB.db";
    private static final int DATABASE_VERSION = 6;
    private SQLiteDatabase db;

    public Quizdbhelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuizContract.QuestionTable.TABLE_NAME + " ( " +
                QuizContract.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_ANSWER + " INTEGER, " +
                QuestionTable.COLUMN_CATEGORY + " TEXT " +
                ")";

        db.execSQL(SQL_CREATE_QUESTION_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ QuizContract.QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void addQuestions(Questions questions)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionTable.COLUMN_QUESTION,questions.getQname());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION1,questions.getOption1());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION2,questions.getOption2());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION3,questions.getOption3());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION4,questions.getOption4());
        cv.put(QuizContract.QuestionTable.COLUMN_ANSWER,questions.getAnswer());
        cv.put(QuestionTable.COLUMN_CATEGORY,questions.getCategory());

        db.insert(QuizContract.QuestionTable.TABLE_NAME,null,cv);

    }

    private void fillQuestionsTable(){

        Questions q1 = new Questions("Which is the poorest Country in the world?",
                "Africa","Kenya","South Africa","Zimbabwe",1,Questions.CATEGORY_GENERAL);
        addQuestions(q1);

        Questions q2 = new Questions("Which crop is sown on the largest area in India?",
                "Rice","Wheat","Sugarcane","Maize",1,Questions.CATEGORY_GENERAL);
        addQuestions(q2);

        Questions q3 = new Questions("Entomology is the science that studies",
                "Behaviour of human beings","Insects","The origin and history of technical and scientific terms","The formation of rocks",2,Questions.CATEGORY_GENERAL);
        addQuestions(q3);

        Questions q4 = new Questions("Grand Central Terminal, Park Avenue, New York is the world's",
                "highest railway station","longest railway station","largest railway station","None of the above",3,Questions.CATEGORY_GENERAL);
        addQuestions(q4);

        Questions q5 = new Questions(" The state which has the largest number of sugar mills in India is",
                "Bihar","Haryana","Punjab"," Uttar Pradesh",3,Questions.CATEGORY_GENERAL);
        addQuestions(q5);

        Questions q6 = new Questions("Which is the smallest Country?",
                "Vatican city","Peru","Italy","Iraq",1,Questions.CATEGORY_GENERAL);
        addQuestions(q6);

        Questions q7 = new Questions("Which one of the following was the first fort constructed by the British in India?",
                "Fort William","Fort St. David","Fort St. Angelo","Fort St. George",4,Questions.CATEGORY_GENERAL);
        addQuestions(q7);

        Questions q8 = new Questions("What is the second largest country (in size) in the world",
                "USA","Canada","China","Russia",2,Questions.CATEGORY_GENERAL);
        addQuestions(q8);

        Questions q9 = new Questions("The currency notes are printed in",
                "New Delhi","Nasik","Nagpur","Bombay",1,Questions.CATEGORY_GENERAL);
        addQuestions(q9);

        Questions q10 = new Questions("For which of the following disciplines is Nobel Prize awarded?",
                "Literature, Peace and Economics","Physics and Chemistry","Physiology or Medicine","All of the above",4,Questions.CATEGORY_GENERAL);
        addQuestions(q10);


        Questions q11 = new Questions("Android is what ?","OS","Drivers","Software","Hardware",1,Questions.CATEGORY_COMPUTER);
        addQuestions(q11);


        Questions q12 = new Questions("Full form of PC is ?",
                "OS","Personal Computer","Pocket Computer","Hardware",2,Questions.CATEGORY_COMPUTER);
        addQuestions(q12);


        Questions q13 = new Questions("Windows is what ?",
                "Easy Software","Hardware Device","Operating System","Hardware",3,Questions.CATEGORY_COMPUTER);
        addQuestions(q13);


        Questions q14 = new Questions("Unity is used for what ?",
                "Game Development","Movie Making","Firmware","Hardware",1,Questions.CATEGORY_COMPUTER);
        addQuestions(q14);


        Questions q15 = new Questions("RAM stands for ",
                "Windows","Drivers","GUI","Random Access Memory",4,Questions.CATEGORY_COMPUTER);
        addQuestions(q15);


        Questions q16 = new Questions("Chrome is what ?",
                "OS","Browser","Tool","New Browser",2,Questions.CATEGORY_COMPUTER);
        addQuestions(q16);


        Questions q17 = new Questions("Which of the following is not a characteristic of a computer?",
                "Speed","Intelligence","Automation","Versatility",2,Questions.CATEGORY_COMPUTER);
        addQuestions(q17);

        Questions q18 = new Questions("Super computer developed by Indian scientists",
                "Param","Super301","Compaq Presario","Cray YMP",1,Questions.CATEGORY_COMPUTER);
        addQuestions(q18);


        Questions q19 = new Questions("Abacus was developed by",
                "China"," India","America","Germany",1,Questions.CATEGORY_COMPUTER);
        addQuestions(q19);


        Questions q20 = new Questions("First super computer developed in India is",
                "BUDDHA","ARYA BHATT","PARAM","SHIVA",3,Questions.CATEGORY_COMPUTER);
        addQuestions(q20);



        Questions q21 = new Questions("How many players are there in a team of Volleyball?",
                "Five","Six","Seven","Eight",2,Questions.CATEGORY_SPORTS);
        addQuestions(q21);

        Questions q22 = new Questions("Deodhar Trophy is a prestigious tournament of -",
                "Football","Cricket","Hockey","Badminton",2,Questions.CATEGORY_SPORTS);
        addQuestions(q22);

        Questions q23 = new Questions("Which team emerge champion of the FIFA under-17 Football World Cup, 2019?",
                " Argentina","Germany","Brazil","Spain",3,Questions.CATEGORY_SPORTS);
        addQuestions(q23);

        Questions q24 = new Questions("FIH Men's Hockey World Cup, 2023 will be hosted in India in -",
                "Kolkata","Jalandhar","Chandigarh","Bhubaneswar and Rourkela",4,Questions.CATEGORY_SPORTS);
        addQuestions(q24);

        Questions q25 = new Questions("Beighton Cup is the prestigious championship of the game of -",
                "Cricket","Basket Ball","Hockey","Football",3,Questions.CATEGORY_SPORTS);
        addQuestions(q25);

        Questions q26 = new Questions("Barabati Stadium is located in -",
                "Cuttack","Ranchi","Bhubaneshwar","Patna",1,Questions.CATEGORY_SPORTS);
        addQuestions(q26);

        Questions q27 = new Questions("Naomi Osaka and Simona Halep are eminent sports women associated with the game of -",
                "Badminton","Lawn Tennis","Table Tennis","Women Cricket",2,Questions.CATEGORY_SPORTS);
        addQuestions(q27);

        Questions q28 = new Questions("Which country hosted the 13th South Asian Games, 2019?",
                "Bangladesh","Nepal","India","Bhutan",2,Questions.CATEGORY_SPORTS);
        addQuestions(q28);

        Questions q29 = new Questions("Ballon d'Or honour is associated with the game of",
                "Basket Ball","Football","Polo","Tennis",2,Questions.CATEGORY_SPORTS);
        addQuestions(q29);

        Questions q30 = new Questions("The headquarters of The International Olympic Committee is situated in",
                "Vienna, Austria","Lausanne, Switzerland","Geneva, Switzerland","Madrid, Spain",2,Questions.CATEGORY_SPORTS);
        addQuestions(q30);


        Questions q31 = new Questions("Guwahati is situated on the banks of river?",
                "Brahmaputra","Ganga","Yammuna","Godavari",1,Questions.CATEGORY_GEOGRAPHY);
        addQuestions(q31);

        Questions q32 = new Questions(" In which state of India is the maximum area irrigated by tube wells ?",
                "Madhya Pradesh","Bihar","Rajasthan","Uttar Pradesh ",4,Questions.CATEGORY_GEOGRAPHY);
        addQuestions(q32);

        Questions q33 = new Questions("The Gulf of Mannar is situated along the coast which state in India?",
                "Karnataka","Andhra","Kerala","Tamil Nadu ",4,Questions.CATEGORY_GEOGRAPHY);
        addQuestions(q33);

        Questions q34 = new Questions("In which one of the following states of India is the Pamayangtse Monastery situated ?",
                "Nagaland","Himachal Pradesh","Sikkim"," Arunachal Pradesh",3,Questions.CATEGORY_GEOGRAPHY);
        addQuestions(q34);

        Questions q35 = new Questions(
                "The southernmost point of peninsular India, that is, Kanyakumari, is",
                "north of Tropic of Cancer","south of the Equator","south of the Capricorn","north of the Equator",4,Questions.CATEGORY_GEOGRAPHY);
        addQuestions(q35);

        Questions q36 = new Questions("The pass located at the southern end of the Nilgiri Hills in south India is called",
                "the Palghat gap","the Bhorghat pass","the Thalgat pass","the Bolan pass",1,Questions.CATEGORY_GEOGRAPHY);
        addQuestions(q36);

        Questions q37 = new Questions("The Yarlung Zangbo river, in India, is known as",
                "Ganga","Indus","Brahmaputra","Mahanadi",3,Questions.CATEGORY_GEOGRAPHY);
        addQuestions(q37);

        Questions q38 = new Questions("The Salal Project is on the river",
                "Chenab","Jhelum","Ravi","Sutlej",1,Questions.CATEGORY_GEOGRAPHY);
        addQuestions(q38);

        Questions q39 = new Questions("The only zone in the country that produces gold is also rich in iron is",
                "North-eastern zone","North-western zone","Southern zone","None of the above",3,Questions.CATEGORY_GEOGRAPHY);
        addQuestions(q39);

        Questions q40 = new Questions("The only state in India that produces saffron is",
                "Assam","Himachal Pradesh","Jammu and Kashmir","Meghalaya",3,Questions.CATEGORY_GEOGRAPHY);
        addQuestions(q40);



    }

    public ArrayList<Questions> getAllQuestionswithcategory(String category)
    {

        ArrayList<Questions> questionsList = new ArrayList<>();

        db = getReadableDatabase();
        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER,
                QuestionTable.COLUMN_CATEGORY
        };

        String selection = QuestionTable.COLUMN_CATEGORY + " = ? " ;
        String selectionArgs[] = {category};

        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                selection,
                selectionArgs,
                null,
                null,
                null

        );

        if( c.moveToFirst())
        {
            do {

                Questions questions = new Questions();
                questions.setQname(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                questions.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                questions.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                questions.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                questions.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                questions.setAnswer(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER)));
                questions.setCategory(c.getString(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY)));


                questionsList.add(questions);

            }while (c.moveToNext());
        }
        c.close();//closing the cursor
        return questionsList;
    }




}
