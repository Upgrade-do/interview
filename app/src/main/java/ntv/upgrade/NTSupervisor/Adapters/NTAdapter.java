package ntv.upgrade.NTSupervisor.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ntv.upgrade.NTSupervisor.NTApplication;


/**
 * Created by jfrometa on 2/20/2016.
 */
public abstract class NTAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
  public  List<QuestionWrapper> items = new ArrayList<>();

    public final int SIMPLE_QUESTION= 0, COMPETENCY_QUESTION = 1;


    public NTAdapter() {
        setHasStableIds(true);
        if(items.size() == 0){
            addAll(NTApplication.QuestionList);
        }

    }

    public void add(QuestionWrapper elem) {
        items.add(elem);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends QuestionWrapper> collection) {
        if (collection != null) {
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void removeAll(){
        if (items != null) {
            for(int i =0 ; items.size() > i ; i++){
                items.remove(i);
            }
            notifyDataSetChanged();
        }
    }

    public void addAll(QuestionWrapper... items) {
        addAll(Arrays.asList(items));
    }


    public QuestionWrapper getItem(int position) {

        return items.get(position);
    }

    public List<QuestionWrapper> getItemList() {

        return items;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
       int xtest= items.get(position).getLayoutType();

        Log.i("layout type: ", String.valueOf(xtest) );
        Log.i("position: ", String.valueOf(position) );

        if (items.get(position).getLayoutType() == SIMPLE_QUESTION) {
            return SIMPLE_QUESTION;
        } else if (items.get(position).getLayoutType() == COMPETENCY_QUESTION) {
            return COMPETENCY_QUESTION;
        }
        return -1;
    }
}
