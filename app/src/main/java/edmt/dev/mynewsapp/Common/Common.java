package edmt.dev.mynewsapp.Common;

import edmt.dev.mynewsapp.Interface.FaviconService;
import edmt.dev.mynewsapp.Interface.NewsService;
import edmt.dev.mynewsapp.Remote.FaviconClient;
import edmt.dev.mynewsapp.Remote.RetrofitClient;

/**
 * Created by Adrija on 01/28/2022.
 */

public class Common {
    private static final String BASE_URL = "https://newsapi.org/";

    public static final String API_KEY="d9c4b75e0c934bd1967d65de6c0994d0";
    public static NewsService getNewsService()
    {
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }

    public static FaviconService getIconService()
    {
        return FaviconClient.getClient().create(FaviconService.class);
    }

    //https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=d9c4b75e0c934bd1967d65de6c0994d0
    public static String getAPIUrl(String source,String sortBy,String apiKEY)
    {
        StringBuilder apiUrl = new StringBuilder("https://newsapi.org/v2/top-headlines?sources=");
        return apiUrl.append(source)
                .append("&apiKey=")
                .append(apiKEY)
                .toString();
    }
}
