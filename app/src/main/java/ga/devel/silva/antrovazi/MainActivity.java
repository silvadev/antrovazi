/*
 * Instituto Federal de São Paulo - Campus Sertãozinho
 * Disciplina......: M4DADM
 * Programação de Computadores e Dispositivos Móveis
 * Aluno...........: Ivanildo José da Silva Filho
 */


package ga.devel.silva.antrovazi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Esta é Activity principal onde será exibida informações relativas ao aplicativo.

    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRegister = findViewById(R.id.btRegister);

        // Caso clicado o botão bt.Register mostra a tela de registros.
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callRegister();
            }
        });
    }

    // O método abaixo faz com que a aplicação vá a tela de registros.
    void callRegister() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
