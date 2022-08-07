package ma.projet.arrosageintellegentv2.ui.espacevert;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ma.projet.arrosageintellegentv2.beans.EspaceVert;
import ma.projet.arrosageintellegentv2.beans.Zone;
import ma.projet.arrosageintellegentv2.networking.ApiClient;
import ma.projet.arrosageintellegentv2.networking.ApiInterface;
import ma.projet.arrosageintellegentv2.utils.Crendentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EspacevertViewModel extends AndroidViewModel {

    static MutableLiveData<List<EspaceVert>> mEspaceVert = new MutableLiveData<>();

    private static final String TAG = "EspaceVertViewModel";

    public EspacevertViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<EspaceVert>> getEspace() {
        return mEspaceVert;
    }


    void init() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<EspaceVert>> call = apiInterface.getespace(Crendentials.getaccess_token());
        call.enqueue(new Callback<List<EspaceVert>>() {
            @Override
            public void onResponse(Call call, Response response) {
                //Log.d(TAG, "onResponse: " + response);
                mEspaceVert.setValue((List<EspaceVert>) response.body());
                Log.d(TAG, "getValeu(à: " + mEspaceVert.getValue());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

    public static List<Zone> getZonesByEspaceId(long espace_id) {
        for (EspaceVert e : mEspaceVert.getValue()) {
            if (e.getId() == espace_id) {
                return e.getZones();
            }
        }
        return null;
    }

    public static  Zone getZoneDetails(long espace_id,long zone_id) {
        for (Zone z : getZonesByEspaceId(espace_id)) {
            if (z.getId() == zone_id) {
                return z;
            }
        }
        return null;
    }

}
