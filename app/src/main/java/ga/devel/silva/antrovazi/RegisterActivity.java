package ga.devel.silva.antrovazi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    // Esta Activity é usada para consultas e registros no banco de dados.

    private DataBase dataBase;

    EditText etRCpf, etRName, etRAge, etRPhone, etRMail;
    Button btAdd, btList, btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.dataBase = new DataBase(this);

        // Abaixo os dados dos botões e caixa de texto são passadas para variáveis locais.
        etRCpf = findViewById(R.id.etRCpf);
        etRName = findViewById(R.id.etRName);
        etRAge = findViewById(R.id.etRAge);
        etRPhone = findViewById(R.id.etRPhone);
        etRMail = findViewById(R.id.etRMail);

        btAdd = findViewById(R.id.btAdd);
        btList = findViewById(R.id.btList);
        btBack = findViewById(R.id.btBack);

        // Caso clicado o botão bt.Add executa a adição dos dados no banco de dados.
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Se todos os campos estiverem completos os dados são adicionados ao banco de dados e é retornando mensagem de sucesso.
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
                // Caso contrário é mostrada mensagem de erro.
                else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
                    alertDialog.setTitle("Erro");
                    alertDialog.setMessage("Todos os campos devem ser preenchidos.");
                    alertDialog.show();
                }
            }
        });

        // Caso clicado o botão bt.List mostra os dados no banco de dados.
        btList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Person> personList = dataBase.queryAll();
                // Se não houver registros é mostrada uma mensagem.
                if (personList == null) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
                    alertDialog.setTitle("Mensagem");
                    alertDialog.setMessage("Não há registros cadastrados.");
                    alertDialog.show();

                    return;
                }
                // Caso contrário é mostrado uma mensagem para cada pessoa cadastrada.
                for (int i =0; i<personList.size(); i++) {
                    Person person = personList.get(i);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
                    alertDialog.setTitle("CPF: " + person.getCpf());
                    alertDialog.setMessage("Nome: " + person.getName() + "\nIdade: " + person.getAge() +
                    "\nTelefone: " + person.getPhone() + "\nE-mail: " + person.getEmail());
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            }
        });

        // // Caso clicado o botão bt.Back volta a tela inicial.
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callMain();
            }
        });
    }

    // O método faz com que a aplicação volte a tela inicial.
    void callMain() {
        Intent intent = new Intent();
        intent.setClass(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
