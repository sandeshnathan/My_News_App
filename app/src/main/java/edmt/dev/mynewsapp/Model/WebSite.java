package edmt.dev.mynewsapp.Model;

import java.util.List;

import edmt.dev.mynewsapp.Adapter.ListSourceAdapter;


/**
 * Created by Adrija on 01/28/2022.
 */

public class WebSite {
    private String status;
    private List<Source> sources;

    public WebSite(ListSourceAdapter listSourceAdapter) {
    }

    public WebSite(String status, List<Source> sources) {
        this.status = status;
        this.sources = sources;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
