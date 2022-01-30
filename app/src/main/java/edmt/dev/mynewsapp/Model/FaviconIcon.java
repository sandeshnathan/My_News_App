package edmt.dev.mynewsapp.Model;



import java.util.List;

/**
 * Created by Adrija on 01/28/2022.
 */
public class FaviconIcon {
    private String url;
    private List<Icon> icons;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Icon> getIcons() {
        return icons;
    }

    public void setIcons(List<Icon> icons) {
        this.icons = icons;
    }
}
