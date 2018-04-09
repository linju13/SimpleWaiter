package speedbars.simplewaiterserver;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import beans.Getraenk;
import enums.EinheitenEnum;



     public class GetraenkeAdapter extends BaseAdapter {

        private List<Getraenk> getraenke = new LinkedList<>();
        private Context context;
        private List<Integer> checked = new LinkedList<>();

         public List<Getraenk> getGetraenke() {
             return getraenke;
         }

         public GetraenkeAdapter(Context context, List<Getraenk> getraenke) {

            this.getraenke = getraenke;
            this.context = context;
        }

        public void addGetraenk(Getraenk g)
        {
          getraenke.add(g);
        }


        public List<Integer> getCheckedIndex()
        {
            return checked;
        }




         public void deleteGetraenke()
         {
             if(checked.size() > 0) {
                 for (int i : checked) {
                     getraenke.remove(i);
                 }
             }
             checked.clear();
         }


        @Override
        public int getCount() {
            return getraenke.size();
        }

        @Override
        public Object getItem(int i) {
            return getraenke.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Getraenk g = (Getraenk) this.getItem(position);
                View v = View.inflate(context, R.layout.listview_item, null);


            TextView name = (TextView) v.findViewById(R.id.GetraenkName);
            TextView price = (TextView) v.findViewById(R.id.GetraenkPreis);

            name.setText(g.getName());
            price.setText(String.format("%.2f €",g.getPreis()));

            CheckBox cb = v.findViewById(R.id.check);

            cb.setId(position);

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                       checked.add(buttonView.getId());
                    }
                    else
                    {
                        checked.remove(buttonView.getId());
                    }
                }
            });

            v.setTag(this.getItemId(position));

            return v;
        }


    }


