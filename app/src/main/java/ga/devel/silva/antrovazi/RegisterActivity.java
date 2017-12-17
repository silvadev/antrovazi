package ga.devel.silva.antrovazi;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private DataBase dataBase;

    EditText etRCpf, etRName, etRAge, etRPhone, etRMail;
    Button btAdd, btList, btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.dataBase = new DataBase(this);

        etRCpf = findViewById(R.id.etRCpf);
        etRName = findViewById(R.id.etRName);
        etRAge = findViewById(R.id.etRAge);
        etRPhone = findViewById(R.id.etRPhone);
        etRMail = findViewById(R.id.etRMail);

        btAdd = findViewById(R.id.btAdd);
        btList = findViewById(R.id.btList);
        btBack = findViewById(R.id.btBack);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etRCpf.getText().length() > 0 && etRName.getText().length() > 0
                        && etRAge.getText().length() > 0 && etRPhone.getText().length() > 0
                        && etRMail.getText().length() > 0) {
                    dataBase.insert(etRCpf.getText().toString(), etRName.getText().toString(),
                            etRAge.getText().toString(), etRPhone.getText().toString(), etRMail.getText().toString());
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
                    alertDialog.setTitle("Sucesso");
                    alertDialog.setMessage("Cadastro Realizado");
                    alertDialog.show();

                    etRMail.setText("");
                    etRPhone.setText("");
                    etRAge.setText("");
                    etRCpf.setText("");
                    etRName.setText("");
                }
                else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
                    alertDialog.setTitle("Erro");
                    alertDialog.setMessage("Todos os campos devem ser preenchidos.");
                    alertDialog.show();
                }
            }
        });

        btList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Person> personList = dataBase.queryAll();
                if (personList == null) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
                    alertDialog.setTitle("Mensagem");
                    alertDialog.setMessage("Não há registros cadastrados.");
                    alertDialog.show();

                    return;
                }
                for () {

                }
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callMain();
            }
        });
    }

    void callMain() {
        Intent intent = new Intent();
        intent.setClass(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
