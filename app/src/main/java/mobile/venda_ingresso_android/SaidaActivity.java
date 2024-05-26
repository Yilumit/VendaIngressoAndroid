package mobile.venda_ingresso_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import mobile.venda_ingresso_android.model.Ingresso;
import mobile.venda_ingresso_android.model.IngressoVip;

public class SaidaActivity extends AppCompatActivity {

    private Ingresso ingresso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_saida);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvSaida = findViewById(R.id.tvSaida);
        tvSaida.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String tipo = bundle.getString("tipo");
        String funcao = "";
        if (tipo != null && tipo.equals("VIP")){
            funcao = bundle.getString("funcao");
            ingresso = new IngressoVip(funcao);
        } else {
            ingresso = new Ingresso();
        }
        ingresso.setCodigo(bundle.getString("cod"));
        int quantidade = bundle.getInt("quantidade");
        ingresso.setValor(bundle.getFloat("valor"));


        String TIPO = getText(R.string.tipo).toString();
        String CODIGO = getText(R.string.codigo).toString();
        String QNTD = getText(R.string.quantidade).toString();
        String REAL = getText(R.string.real).toString();
        DecimalFormat df = new DecimalFormat(".##");
        String total = df.format(ingresso.getValor());

        String saida = TIPO+ " " + tipo + "\n" + CODIGO + " " + ingresso.getCodigo() + "\n" + QNTD + " " + quantidade + "\n"
                + REAL + total + "\n" + funcao;

        tvSaida.setText(saida);
        btnVoltar.setOnClickListener(op -> voltar());
    }

    private void voltar() {
        Intent in = new Intent(this, MainActivity.class);
        this.startActivity(in);
        this.finish();
    }
}