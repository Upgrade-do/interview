package ntv.upgrade.NTSupervisor.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;

import ntv.upgrade.NTSupervisor.R;
import ntv.upgrade.NTSupervisor.constants.AppConstants;

/**
 * Created by jfrometa on 5/20/2016.
 */
public class GradeAdapter extends BaseAdapter implements SpinnerAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private ViewHolder holder ;
    public GradeAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return AppConstants.acceptanceIcons.length;
    }

    @Override
    public Object getItem(int position) {
        return AppConstants.acceptanceIcons[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return getMyView(position, convertView, parent);
    }

    public View getMyView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if (null == v) {
            holder = new ViewHolder();
            inflater = LayoutInflater.from(mContext);
            v = inflater.inflate(R.layout.spinner_competency_question, parent, false);
            holder.imgThumb = (ImageView) v.findViewById(R.id.spinner_aceptance_selection);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.imgThumb.setBackgroundResource(AppConstants.acceptanceIcons[position]);


        return v;
    }

    static class ViewHolder {
        ImageView imgThumb;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);

        //refer to this if you want to customize the drop down view.

  /*      TextView label = (TextView) super.getDropDownView(position, convertView, parent);

        PaymentType paymentType = getItem(position);
        label.setText(paymentType.getStringResourceId());
        label.setCompoundDrawablesWithIntrinsicBounds(0, 0, paymentType.
                getImageResourceId(), 0);

        return label;*/


    }
}
