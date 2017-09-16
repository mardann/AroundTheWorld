package il.co.procyon.aroundtheworld.adapters;

import android.support.v7.widget.RecyclerView;

import il.co.procyon.aroundtheworld.databinding.CountryCellBinding;
import il.co.procyon.aroundtheworld.objects.Country;

/**
 * Created by hanan on 16-Sep-17.
 */

public class CountryHolder extends RecyclerView.ViewHolder {

    CountryCellBinding mBinding;

    public CountryHolder(CountryCellBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void setCountry(Country country){
        mBinding.setCountry(country);
    }
}
