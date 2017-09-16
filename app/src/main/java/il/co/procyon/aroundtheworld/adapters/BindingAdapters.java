package il.co.procyon.aroundtheworld.adapters;

import android.databinding.BindingAdapter;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hanan on 16-Sep-17.
 */

public class BindingAdapters {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Log.d("BindingAdapter", "loadImage: url= " + url);
//        Picasso.with(view.getContext()).load(url).into(view);
        Uri uri = Uri.parse(url);


        Glide
                .with(view.getContext())
                .using(Glide.buildStreamModelLoader(Uri.class, view.getContext()), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .listener(new RequestListener<Uri, PictureDrawable>() {
                    @Override
                    public boolean onException(Exception e, Uri model, Target<PictureDrawable> target, boolean isFirstResource) {
                        ImageView view = ((ImageViewTarget<?>) target).getView();
                        view.setLayerType(ImageView.LAYER_TYPE_NONE, null);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(PictureDrawable resource, Uri model, Target<PictureDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        ImageView view = ((ImageViewTarget<?>) target).getView();
                        view.setLayerType(ImageView.LAYER_TYPE_SOFTWARE, null);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(uri)
                .into(view);
    }

    private static class SvgDrawableTranscoder implements ResourceTranscoder<SVG, PictureDrawable> {
        @Override
        public Resource<PictureDrawable> transcode(Resource<SVG> toTranscode) {
            SVG svg = toTranscode.get();
            Picture picture = svg.renderToPicture();
            PictureDrawable drawable = new PictureDrawable(picture);
            return new SimpleResource<PictureDrawable>(drawable);
        }

        @Override
        public String getId() {
            return "null";
        }
    }



    private static class SvgDecoder implements ResourceDecoder<InputStream, SVG> {

        @Override
        public Resource<SVG> decode(InputStream source, int width, int height)
                throws IOException {
            try {
                SVG svg = SVG.getFromInputStream(source);
                return new SimpleResource<SVG>(svg);
            } catch (SVGParseException ex) {
                throw new IOException("Cannot load SVG from stream", ex);
            }
        }

        @Override
        public String getId() {
            return "null";
        }
    }
}
