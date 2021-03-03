package com.example.cours4_fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName = getString(R.string.your_name);
        initListener();
    }

    private void initFragment() {
        // FragmentManager est une variable de la classe mere activity, c'est un objet qui permet de gerer l'affichage des fragments d'une activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        //je creer une instance de mon fragment et lui passe mName en parametre
        FirstFragment firstFragment = FirstFragment.newInstance(mName);
        //je dis au fragment manager de creer une nouvelle transaction qui est d'ajouter un fragment et commit lui dit de realiser la transaction
        //la fonction add prend en parametre :
        // 1: L'id du frameLayout container qui se trouve dans le xml de l'activity
        // 2: l'instance du fragment a afficher
        // 3: un TAG pour identifier le fragment si besoin (comme dans la fonction getfragmentByTag plus bas)
        fragmentManager.beginTransaction().add(R.id.AM_bottom_container, firstFragment, FirstFragment.TAG).commit();
    }

    private void initListener() {

        findViewById(R.id.AM_add_btn).setOnClickListener(view -> {
            initFragment();
        });

        //Permet d'ecouter les changement de text d'un editText
        ((EditText) findViewById(R.id.AM_name_et)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //fonction appelee a chaque fois que le text est modifie, ajout ou supression d'un charactere
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mName = String.valueOf(charSequence);
                //Recupere le fragment par son tag
                FirstFragment firstFragment = (FirstFragment) getFragmentByTag(FirstFragment.TAG);
                //si e fragment est bien affiche getFragmentByTag va bien rendre l'instance du fragment afficher et non null
                if (firstFragment != null) {
                    //appel de la fonction public setView de FirsFragment pour afficher le nom comme insere dans ledittext par l'utilisateur
                    firstFragment.updateView(mName);
                }else {
                    Toast.makeText(MainActivity.this, getString(R.string.please_add_fragment_before), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * @param tag identifiant du fragment recherche
     * @return Frgament recherche, ayant pour Tag le Tag passe en parametre
     */
    private Fragment getFragmentByTag(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        //boucle sur la liste de fragment affiches par notre activity
        for (Fragment fragment : fragmentManager.getFragments()) {
            //si le fragment a un ta, que le TAG est egale au TAG passe en parametre
            // et que le fragment a le statut added donc a ete ajouter donc apriori visible
            if (fragment.getTag() != null && fragment.getTag().equals(tag) && fragment.isAdded()) {
                // alors on retourne le fragment qui etait celui que l'on cherche avec son TAG
                return fragment;
            }
        }
        //si on a pas trouve de frgamnet corrspondant on retourne null
        return null;
    }

}