package com.example.bousquleProjet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bousquleProjet.R;
import com.example.bousquleProjet.model.Multiplication;

import java.util.List;

public class MultiplicationAdapter extends ArrayAdapter<Multiplication> {

    private final List<Multiplication> values;

    public MultiplicationAdapter(Context context, List<Multiplication> values) {
        super(context, R.layout.template_calc_mul, values);
        this.values = values;
    }

    /**
     * Remplit une ligne de la listView avec les informations de la multiplication associée
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Récupération de la multiplication
        final Multiplication multiplication = values.get(position);

        // Charge le template XML
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.template_calc_mul, parent, false);

        // Récupération des objets graphiques dans le template
        TextView calcul = (TextView) rowView.findViewById(R.id.template_calcul);
        final EditText resultat = (EditText) rowView.findViewById(R.id.template_resultat);

        // Modification de l'objet graphique en fonction de la multiplication
        calcul.setText(multiplication.getOperande1() + " x " + multiplication.getOperande2() + " = " );

        //
        return rowView;
    }


}

