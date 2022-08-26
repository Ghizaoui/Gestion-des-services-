package com.example.isidigital;

import android.icu.text.StringPrepParseException;

import com.android.volley.toolbox.StringRequest;

public class Champs {
    private  String motif;
    private String groupe;
    private  String nom;
    private  String prenom;
    private String niveau;

     Champs(String motif, String groupe, String nom, String prenom, String niveau)
    {
        this.groupe=groupe;
        this.motif=motif;
        this.niveau=niveau;
        this.nom=nom;
        this.prenom=prenom;
    }
    public   String getMotif(){
            return motif ;
 }
    public String getGroupe(){ return groupe; }
    public  String getNom(){return  nom;}
    public  String getPrenom(){return  prenom;}
    public  String getNiveau(){return  niveau;}

    public void setMotif(String motif){this.motif=motif;}
    public void setNom(String nom){this.nom=nom;}
    public void setPrenom(String prenom){this.prenom=prenom;}
    public void setGroupe(String groupe){this.groupe=groupe;}
    public void setNiveau(String niveau){this.niveau=niveau;}

}
