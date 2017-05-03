package p2.b230.caffeinetrackerp2;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpandableListAdapter extends BaseExpandableListAdapter
{
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private final Set<Pair<Long, Long>> mCheckedItems = new HashSet<>();
    public static ArrayList<String> favList = new ArrayList<>();

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activity_list_item, null);
        }

        final CheckBox cb = (CheckBox) convertView.findViewById(R.id.list_check_box);
        // add tag to remember groupId/childId
        final Pair<Long, Long> tag = new Pair<Long, Long>(
                getGroupId(groupPosition),
                getChildId(groupPosition, childPosition));
        cb.setTag(tag);
        // set checked if groupId/childId in checked items
        cb.setChecked(mCheckedItems.contains(tag));
        // set OnClickListener to handle checked switches
        cb.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final CheckBox cb = (CheckBox) v;
                final Pair<Long, Long> tag = (Pair<Long, Long>) v.getTag();
                if (cb.isChecked()) {
                    mCheckedItems.add(tag);
                    favList.add(childText);
                } else {
                    mCheckedItems.remove(tag);
                    favList.remove(childText);
                }
            }
        });

        cb.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activity_group_list, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.group_header);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public static ArrayList<String> getFavList() {
        return favList;
    }
}
