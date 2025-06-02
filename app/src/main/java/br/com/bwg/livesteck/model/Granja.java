package br.com.bwg.livesteck.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Granja {
    private int id;
    private String nome;
    private String latitude;
    private String longitude;
    private String proprietario;
    private int silos;

    //CONSTRUTOR - Inicializa os atributos para gerar Objeto Json
    public Granja () {
        this.setId(0);
        this.setNome("");
        this.setLatitude("");
        this.setLongitude("");
        this.setProprietario("");
        this.setSilos(0);
    }

    //CONSTRUTOR - inicializa atributos de um arquivo JSon
    public Granja (JSONObject jp) {
        try {
            this.setId(jp.getInt("idGranja"));
            this.setNome(jp.getString("nomeGranja"));
            this.setNome(jp.getString("Latitude"));
            this.setNome(jp.getString("Longitude"));
            this.setNome(jp.getString("nomeProprietario"));
            this.setId(jp.getInt("Silos"));
        } catch (JSONException e) {
            Log.e("GRANJA", Objects.requireNonNull(e.getMessage()));
        }
    }
    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("idGranja", this.id);
            json.put("nomeGranja", this.nome);
            json.put("Latitude", this.latitude);
            json.put("Longitude", this.longitude);
            json.put("nomeProprietario", this.proprietario);
            json.put("Silos", this.silos);
        } catch (JSONException e) {
            Log.e("GRANJA", Objects.requireNonNull(e.getMessage()));
        }
        return json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public int getSilos() {
        return silos;
    }

    public void setSilos(int silos) {
        this.silos = silos;
    }
}
