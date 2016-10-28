package co.yellowbricks.bggclient;

import co.yellowbricks.bggclient.fetch.domain.FetchItemOutput;
import co.yellowbricks.bggclient.fetch.domain.UserCollection;
import co.yellowbricks.bggclient.search.domain.SearchOutput;
import retrofit2.Call;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BggService {

    String BASE_URL = "http://www.boardgamegeek.com/xmlapi2/";

    @GET("search")
    Call<SearchOutput> search(@Query("query") String query, @Query("type") String types);

    @GET("thing")
    Call<FetchItemOutput> fetch(@Query("id") String ids, @Query("type") String types);

    @GET("collection")
    Call<UserCollection> fetchCollection(@Query("username") String ownerName, @Query("own") int own);

    BggService INSTANCE = new Builder().baseUrl(BASE_URL)
                                       .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
                                       .build()
                                       .create(BggService.class);
}
