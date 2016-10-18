package kr.hs.emirim.eunji057.simplediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker datePic;
    EditText editDiary;
    Button butSave;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePic=(DatePicker)findViewById(R.id.date_picker);
        editDiary=(EditText)findViewById(R.id.edit_content);
        butSave=(Button)findViewById(R.id.but_save);

        Calendar calendar=Calendar.getInstance();
        int nowYear=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int date=calendar.get(Calendar.DATE);

        datePic.init(nowYear, month, date, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName=year+"_"+(monthOfYear+1)+"_"+dayOfMonth+".txt";
                String content= null;
                try {
                    content = readDiary(fileName);
                } catch (IOException e) {
                    editDiary.setHint("읽어올 일기가 없음 :(");
                    butSave.setText("새로 저장");
                }
                editDiary.setText(content);
                butSave.setEnabled(true);
            }
        });
    }

    String readDiary(String fileName) throws IOException {
        String diaryContents=null;
        FileInputStream in=openFileInput(fileName);
        byte[] txt=new byte[500];
        in.read(txt);
        in.close();
        diaryContents=new String(txt);
        butSave.setText("수정하기");

        return null;
    }
}
