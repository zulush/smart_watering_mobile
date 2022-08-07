package ma.projet.arrosageintellegentv2.networking;

import java.util.List;
import java.util.Map;

import ma.projet.arrosageintellegentv2.beans.AppUser;
import ma.projet.arrosageintellegentv2.beans.EspaceVert;
import ma.projet.arrosageintellegentv2.beans.LoginResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @POST("/api/login")
    Call<LoginResponse> Authentifcate(@QueryMap Map<String,String> options);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/api/farmer")
    Call<AppUser> getUser(@Query("id") String id, @Header("Authorization") String auth);


    //@Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/api/farmer/espaces_verts")
    Call<List<EspaceVert>> getespace(@Header("Authorization") String auth);
  /*  @GET("/api/products")
    Call<List<Product>> getProducts();

    @GET("/products/{id}")
    Call<Product> getProduct(@Path("id") int id);

    @GET("/products/{id}/sellers")
    Call<Seller> getProductSellers(@Path("id") int id);
*/
}
