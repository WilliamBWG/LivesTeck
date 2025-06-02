package br.com.bwg.livesteck.ui.granja;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.bwg.livesteck.R;
import br.com.bwg.livesteck.model.Granja;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadGranjaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadGranjaFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>, View.OnClickListener{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText etNome;
    private EditText etLat;
    private EditText etLong;
    private EditText etProprietario;
    private EditText etSilos;
    private View view;
    //volley
    private RequestQueue requestQueue;

    public CadGranjaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadGranjaFragment.
     */
    public static CadGranjaFragment newInstance(String param1, String param2) {
        CadGranjaFragment fragment = new CadGranjaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_cad_granja, container, false);
        //
        //Binding dos Objetos com os componentes XML
        this.etNome = view.findViewById(R.id.etNome);
        this.etLat = view.findViewById(R.id.etLat);
        this.etLong = view.findViewById(R.id.etLong);
        this.etProprietario = view.findViewById(R.id.etProprietario);
        this.etSilos = view.findViewById(R.id.etSilos);
        Button btSalvar = view.findViewById(R.id.btSalvar);
        //definindo o listener do botão
        btSalvar.setOnClickListener(this);
        //instanciando a fila de req
        this.requestQueue = Volley.newRequestQueue(view.getContext());
        //inicializando a fila
        this.requestQueue.start();
        //
        return this.view;
    }

    @Override
    public void onClick(View view) {
        //verificando se é o botão salvar
        if (view.getId() == R.id.btSalvar) {
            //instanciando objeto de negócio
            Granja g = new Granja();
            //populando objeto com dados da tela
            g.setNome(this.etNome.getText().toString());
            g.setLatitude(this.etLat.getText().toString());
            g.setLongitude(this.etLong.getText().toString());
            g.setProprietario(this.etProprietario.getText().toString());
            g.setSilos(Integer.parseInt(this.etSilos.getText().toString()));
            //
            JsonObjectRequest jsonObjectReq = new JsonObjectRequest(
                    Request.Method.POST,
                    "http://10.0.2.2/LivesTeck/cadGranha.php",
                    g.toJsonObject(), this, this);
            requestQueue.add(jsonObjectReq);


        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Snackbar mensagem = Snackbar.make(this.view,
                "Ops! Houve um problema ao realizar o cadastro: " +
                        error.toString(),Snackbar.LENGTH_LONG);
        mensagem.show();
    }

    @Override
    public void onResponse(JSONObject jason) {
        try {
//context e text são para a mensagem na tela o Toast
            Context context = view.getContext();
//pegando mensagem que veio do json
            CharSequence mensagem = jason.getString("message");
//duração da mensagem na tela
            int duration = Toast.LENGTH_SHORT;
//verificando se salvou sem erro para limpar campos da tela
            if (jason.getBoolean("success")){
//limpar campos da tela
                this.etNome.setText("");
                this.etLat.setText("");
                this.etLong.setText("");
                this.etProprietario.setText("");
                this.etSilos.setText("0");
            }
//mostrando a mensagem que veio do JSON
            Toast toast = Toast.makeText (context, mensagem, duration);
            toast.show();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}