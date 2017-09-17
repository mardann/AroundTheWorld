package il.co.procyon.aroundtheworld.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import il.co.procyon.aroundtheworld.databinding.CountryCellBinding;
import il.co.procyon.aroundtheworld.objects.Country;

/**
 * Created by hanan on 16-Sep-17.
 */

public class CountriesAdapter extends RecyclerView.Adapter<CountryHolder> {

    ArrayList<Country> mCountries;
    private ItemClickCallback mClickCallback;



    public void setData(ArrayList<Country> countries) {
        mCountries = countries;
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(ItemClickCallback callback){
        mClickCallback = callback;
    }


    @Override
    public CountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CountryCellBinding countryCellBinding = CountryCellBinding.inflate(inflater, parent, false);

        return new CountryHolder(countryCellBinding);
    }



    @Override
    public void onBindViewHolder(CountryHolder holder, int position) {
        final Country country = mCountries.get(position);
        holder.setCountry(country);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickCallback != null) {
                    mClickCallback.onItemClick(country.getAlpha3Code());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mCountries == null) {
            return 0;
        } else {
            return mCountries.size();
        }
    }

    public interface ItemClickCallback{
        void onItemClick(String countryCode);
    }
}
