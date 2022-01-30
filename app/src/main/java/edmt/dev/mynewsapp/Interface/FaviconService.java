package edmt.dev.mynewsapp.Interface;

import edmt.dev.mynewsapp.Model.FaviconIcon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Adrija on 01/28/2022.
 */
public interface FaviconService {
    @GET
    Call<FaviconIcon> getIconUrl(@Url String url);
}
