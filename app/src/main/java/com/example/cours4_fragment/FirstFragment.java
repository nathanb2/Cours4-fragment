package com.example.cours4_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    public static final String TAG = FirstFragment.class.getSimpleName();
    private static final String NAME_KEY = "NAME_KEY";
    private static final String USER_KEY = "USER_KEY";
    private String name;
    private TextView nameTv;

    /**
     * Nous uilisons la fonction newInstance pour creer une instance de FirstFragment et sauvegarder dans un Bundle les parametre que l'on souhaite lui transmetre
     * Nous utilisons un Bundle pour sauvegarder les parametre afin de pouvoir les recuperer en cas de rotation de l'ecran qui entraine une destruction de l'instance du frgamnet
     * et une recreation automatique gerer par Android d'une nouvelle instance du fragment une fois la rotation effectue.
     * Android utilisant le constructeur par default sans parametre il recre une instance de fragment sans parametre
     * Le seul moyen de sauvegarder nos parametre et les recuperer dans le onviewcreated est de les passer en Arguments avec la methode setArgument (de la classe mere Fragment)
     * Dans un Bundle qu'il sait sauvegarder au dela de la destruction de l'instance du fragment precedement et le reatribuer a la nouvelle instance
     * @param name le nom que l'on souhaite afficher a l'ouverture du fragment
     * @param user parametre de type Objet User pour montrer comment on peut insere un objet dans un Bundle (si il est Parcelable)
     * @return une nouvelle instance du fragment FirsFragment
     */
    public static FirstFragment newInstance(String name, User user) {
        //creation de l'instance du fragment
        FirstFragment firstFragment = new FirstFragment();
        //creation du Bundle qui est comme une valise ou l'on peut inserer des donnees (primitif uniquement)
        Bundle bundle = new Bundle();
        //on insert la valeur du parametre name dans notre bundle et l'etiquette avec une clefs afin de pouvoir l'identifier lorsque l'on voudra le recuperer
        bundle.putString(NAME_KEY, name);
        //on insert dans le bundle un OBJECT de type User car User est Parcelable
        bundle.putParcelable(USER_KEY, user);
        //on set le bundle au fragment
        firstFragment.setArguments(bundle);
        return firstFragment;
    }

    /**
     * On instancie le layout de notre ffragment et le joint a notre fragment
     * @param inflater objet permettant d'instancier un xml donc notre layout
     * @param container la vue dans laquelle notre fragment va se loger donc notre frameLayout de l'activity
     * @param savedInstanceState un Autre bundle que l'on utilise pas ici
     * @return View soit l'instance du layout de notre fragment cree ici
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    /**
     * notre layout etant cree on peut recuperer ses vue et les manipuler
     * @param view la vue de notre layout cree dans onCreateView
     * @param savedInstanceState le Bundle recu dans onCreateView egaleemnt et pas utilise ici
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //on recupere le bundle inserer dans la variable mArguments de la classe mere Fragment dans la fonction newInstance
        //verifions que l'on y trouve bien une clefs equivalente a celle associee au parametre inserer dans la fonction newInstance
        if (getArguments() != null && getArguments().containsKey(NAME_KEY)) {
            //Nous recuperons la valeur sauvegarder dans le Bundle sous la clefs NAME_KEY
            name = getArguments().getString(NAME_KEY);
        }

        if (getArguments() != null && getArguments().containsKey(USER_KEY)) {
            //Nous recuperons la valeur sauvegarder dans le Bundle sous la clefs USER_KEY qui est du type Objet User mais implementant Parcelable
            //il peut etre parse automatiquement en un objet plus simple que le Bundle accepte
            User user = getArguments().getParcelable(USER_KEY);
            if (user != null){
                Log.d(TAG, "onViewCreated: user name : " + user.getName() + " age de : " + user.getAge());
            }
        }

        initVars(view);
        updateView(name);
    }

    private void initVars(View view) {
        nameTv = view.findViewById(R.id.FF_tv);
    }

    /**
     * Fonction public que l'on peut appeler depuis l'activity qui a acces a l'instance du fragment qu'elle affiche
     * @param name que l'on souhaite afficher
     */
    public void updateView(String name) {
        nameTv.setText(name);
    }
}
