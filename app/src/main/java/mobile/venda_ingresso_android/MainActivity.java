package mobile.venda_ingresso_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import mobile.venda_ingresso_android.model.Ingresso;
import mobile.venda_ingresso_android.model.IngressoVip;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbIngresso;
    private RadioButton rbIngressoVip;
    private EditText etQuantidade;
    private EditText etFuncao;
    static int codigo = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rbIngresso = findViewById(R.id.rbIngresso);
        rbIngresso.setChecked(true);
        rbIngressoVip = findViewById(R.id.rbIngressoVip);
        etQuantidade = findViewById(R.id.etQuantidade);
        etFuncao = findViewById(R.id.etFuncao);
        etFuncao.setEnabled(false);
        Button btnComprar = findViewById(R.id.btnComprar);

    rbIngresso.setOnClickListener(v -> {    //Quando o botao for selecionado
            etFuncao.setEnabled(!rbIngresso.isChecked());   //desabilita o EditText
        });
        rbIngressoVip.setOnClickListener(v -> { //Quando o botao for selecionado
            etFuncao.setEnabled(rbIngressoVip.isChecked()); //habilita o EditText
        });

        btnComprar.setOnClickListener(op -> comprar());
    }

    private void comprar() {
        codigo++;
        String cod = String.valueOf(codigo);
        float valorBase = 47.99f;
        int quantidade = Integer.parseInt(etQuantidade.getText().toString());
        String tipo;

        float valorIngresso = valorBase * quantidade;
        float taxa = valorIngresso * 0.07f;

        Ingresso ingresso;
        Bundle bundle = new Bundle();

        if (rbIngresso.isChecked()){
            ingresso = new Ingresso(cod, valorIngresso);
            tipo = "Normal";
        } else {
            String funcao = etFuncao.getText().toString();
            ingresso = new IngressoVip(cod, valorIngresso, funcao);
            bundle.putString("funcao", funcao);
            tipo = "VIP";
        }

        valorIngresso = ingresso.valorFinal(taxa);

        bundle.putString("cod", cod);
        bundle.putInt("quantidade", quantidade);
        bundle.putFloat("valor", valorIngresso);
        bundle.putString("tipo", tipo);

        troca(bundle);
    }

    private void troca(Bundle bundle) {
        Intent in = new Intent(this, SaidaActivity.class);
        in.putExtras(bundle);
        this.startActivity(in);
        this.finish();
    }


}