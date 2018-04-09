package speedbars.simplewaiterserver;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import beans.Getraenk;
import beans.Getraenkelist;
import server.ApplicationVariables;
import speedbars.activities.TableViewActivity;

/**
 * Created by Laura on 09.04.18.
 *
 */

public class ListenAdapter extends BaseExpandableListAdapter
{
    private List<Getraenkelist> listen;

    private Context context;

    public ListenAdapter(Context c, List<Getraenkelist> g)
    {
    this.context = c;
    this.listen = g;

    }

    @Override
    public int getGroupCount() {
        return listen.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listen.get(groupPosition).getGetraenke().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listen.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listen.get(groupPosition).getGetraenke().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition + childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.expandable_root, null);

        Getraenkelist g  = listen.get(groupPosition);
        String s = g.getName();

        TextView tv = v.findViewById(R.id.text);
        tv.setText(s);

        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.expandable_child, null);
        Getraenk g = listen.get(groupPosition).getGetraenke().get(childPosition);


    TextView tv = v.findViewById(R.id.getrname);
    tv.setText(g.getName());

    TextView menge = v.findViewById(R.id.menge);
    String m = String.format("%.1f ",g.getMenge()) + g.getEinheit().getKurzzeichen();
    menge.setText(m);

    TextView p = v.findViewById(R.id.preis);
    String pr = String.format("%.2f€", g.getPreis());
    p.setText(pr);



        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
