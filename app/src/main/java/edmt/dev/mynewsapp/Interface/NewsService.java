package edmt.dev.mynewsapp.Interface;

/**
 * Created by Adrija on 01/28/2022.
 */

import edmt.dev.mynewsapp.Common.Common;
import edmt.dev.mynewsapp.Model.News;
import edmt.dev.mynewsapp.Model.WebSite;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewsService {
    @GET ("v2/sources?language=en&apiKey=" + Common.API_KEY)
    Call<WebSite> getSources();

    @GET
    Call<News> getNewestArticles(@Url String url);
}
