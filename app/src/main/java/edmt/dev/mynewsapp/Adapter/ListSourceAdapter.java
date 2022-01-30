package edmt.dev.mynewsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import edmt.dev.mynewsapp.Common.Common;
import edmt.dev.mynewsapp.Interface.FaviconService;
import edmt.dev.mynewsapp.Interface.ItemClickListener;
import edmt.dev.mynewsapp.ListNews;
import edmt.dev.mynewsapp.Model.FaviconIcon;
import edmt.dev.mynewsapp.Model.WebSite;
import edmt.dev.mynewsapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Adrija on 01/28/2022.
 */

class ListSourceViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener
{
    ItemClickListener itemClickListener;

    TextView source_title;
    CircleImageView source_image;

    public ListSourceViewHolder(View itemView) {
        super(itemView);

        source_image = (CircleImageView) itemView.findViewById(R.id.source_image);
        source_title = (TextView)itemView.findViewById(R.id.source_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}

public class ListSourceAdapter extends Adapter<ListSourceViewHolder> {
    private Context context;
    private WebSite webSite;

    private FaviconService mService;

    public ListSourceAdapter(Context context, WebSite webSite) {
        this.context = context;
        this.webSite = webSite;

        mService = Common.getIconService();
    }


    @Override
    public ListSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.source_layout,parent,false);
        return new ListSourceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListSourceViewHolder holder, int position) {

        StringBuilder FaviconAPI = new StringBuilder("https://besticon-demo.herokuapp.com/allicons.json?url=");
        FaviconAPI.append(webSite.getSources().get(position).getUrl());

        mService.getIconUrl(FaviconAPI.toString())
                .enqueue(new Callback<FaviconIcon>() {
                    @Override
                    public void onResponse(Call<FaviconIcon> call, Response<FaviconIcon> response) {
                        if(response.body().getIcons().size() > 0)
                        {
                            Picasso.get()
                                    .load(response.body().getIcons().get(0).getUrl())
                                    .into(holder.source_image);
                        }
                    }

                    @Override
                    public void onFailure(Call<FaviconIcon> call, Throwable t) {

                    }
                });

        holder.source_title.setText(webSite.getSources().get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, ListNews.class);
                intent.putExtra("source",webSite.getSources().get(position).getId());
                context.startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount()
    {
        return webSite != null ? (webSite.getSources() != null ? webSite.getSources().size() : 0) : 0;
        //return webSite.getSources().size();
    }
}