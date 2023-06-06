package sg.edu.rp.c346.id22024713.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTask;
    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        lvTask = findViewById(R.id.listViewId);
        spn = findViewById(R.id.spinnerItems);

        ArrayList<String> taskList = new ArrayList<String>();

        ArrayAdapter aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        lvTask.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etOutput = etTask.getText().toString();
                if (etOutput.isEmpty()) {
                    etTask.setError("Enter field.");
                } else {
                    SimpleDateFormat date = new SimpleDateFormat("MMMM dd yyyy");
                    Date todayDate = new Date();
                    String thisDate = date.format(todayDate);
                    String strTask = etTask.getText().toString() + "\nEdited on: " + thisDate;
                    taskList.add(strTask);
                    etTask.setText("");
                    aaTask.notifyDataSetChanged();

                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etOutput = etTask.getText().toString();
                if (etOutput.isEmpty()) {
                    etTask.setError("Enter field.");
                } else {
                    int pos = Integer.parseInt(etOutput);
                    if (taskList.isEmpty()) {
                        etTask.setText("");
                        Toast.makeText(MainActivity.this, "You don't have any tasks to remove", Toast.LENGTH_SHORT).show();

                    } else if (pos > taskList.size() || pos == 0) {
                        etTask.setText("");
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    } else {
                        taskList.remove(pos - 1);
                        etTask.setText("");
                        aaTask.notifyDataSetChanged();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.clear();
                aaTask.notifyDataSetChanged();
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etTask.setHint("Add a new task");
                        etTask.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTask.setHint("Type in the index of the task to be removed");
                        etTask.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}