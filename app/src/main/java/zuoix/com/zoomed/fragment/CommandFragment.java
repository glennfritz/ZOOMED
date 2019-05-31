package zuoix.com.zoomed.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsMessage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.MapBoxTileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

import zuoix.com.zoomed.AutoFitGridRecyclerView;
import zuoix.com.zoomed.CommandAdapter;
import zuoix.com.zoomed.R;
import zuoix.com.zoomed.activities.SharedPref;

import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.tileprovider.util.ManifestUtil;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.TilesOverlay;
import org.osmdroid.views.overlay.mylocation.DirectedLocationOverlay;

import java.io.File;
import java.util.ArrayList;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommandFragment extends Fragment implements OnMapReadyCallback {
    AutoFitGridRecyclerView commandView;
    RecyclerView.Adapter adapter;
    boolean OFF_LINE_MODE_TRUE = true;
    private RelativeLayout onlineMap;
    private MapView offlineMap;
    SharedPref sp;

    GoogleMap map;

    public void onPause(){
        super.onPause();
        offlineMap.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }


    public CommandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_command, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        commandView = view.findViewById(R.id.command_list);
        adapter = new CommandAdapter(getContext());
        commandView.setAdapter(adapter);
        sp = new SharedPref(getContext());
        adapter.notifyDataSetChanged();
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(this);
        getChildFragmentManager().beginTransaction().replace(R.id.map_online, mapFragment).commit();
        getContext().registerReceiver(responseRecieved,new IntentFilter("SMS_RECEIVED"));

        offlineMap = (MapView) view.findViewById(R.id.map_offline);
        offlineMap.setTileSource(TileSourceFactory.MAPNIK);
        offlineMap.setBuiltInZoomControls(true);
        offlineMap.setMultiTouchControls(true);
        onlineMap = view.findViewById(R.id.fragment_holder);

        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String pathDir = baseDir + "/storage/sdcard0/Android/data/zouix.com.zoomed/tiles/";

        Configuration.getInstance().setOsmdroidBasePath(new File(pathDir));
        //Configuration.getInstance().setOsmdroidTileCache(pathDir, "tiles"));
        System.out.println("      tile  /////   "+pathDir);

        offlineMap.setTileSource(new XYTileSource("Came", 0, 18, 256, ".png", new String[] { new File(Environment.getExternalStorageDirectory(), "tiles").getPath()}));
        if(new File(Environment.getExternalStorageDirectory(), "tiles").exists()){
            System.out.println(" ////////////////////           Tile exist");
        }else{
            System.out.println(" ////////////////////           Tile dont exist");
        }
        Configuration.getInstance().setOsmdroidBasePath(new File(pathDir));
        File tileCache = new File(Configuration.getInstance().getOsmdroidBasePath().getAbsolutePath(), "tile");
        Configuration.getInstance().setOsmdroidTileCache(tileCache);
        setDefaultViewPoint(sp.getLatitude(),sp.getLongitude());

   }



    void setDefaultViewPoint(double lat,double lon){
        IMapController mapController = offlineMap.getController();
        mapController.setZoom(9);
        GeoPoint startPoint = new GeoPoint(lat, lon);
        mapController.setCenter(startPoint);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            if(OFF_LINE_MODE_TRUE){
                offlineMap.setVisibility(View.VISIBLE);
                onlineMap.setVisibility(View.GONE);
            }else{
                offlineMap.setVisibility(View.GONE);
                onlineMap.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        this.map = map;

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(37.4219999,-122.0862462))
                .zoom(16)
                .bearing(0)
                .tilt(45)
                .build();

        map.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex));

    }

    BroadcastReceiver responseRecieved = new BroadcastReceiver () {
        public void onReceive(Context context, Intent intent )
        {
            final Bundle bundle = intent.getExtras();

            try {
                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    for (int i = 0; i < pdusObj.length; i++) {

                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String n = currentMessage.getDisplayOriginatingAddress();
                        String m = currentMessage.getDisplayMessageBody();
                        if(checkString(m,n).equals("success")){
                            JSONArray jsonObject = new JSONArray(m);
                           showLocations(jsonObject);
                        }



                    } // end for loop
                } // bundle is null
            } catch (Exception e) {}
        }


    };
    public String checkString(String m,String n){
        JSONObject response;
        try {
            response = new JSONObject(m);
            return "success";
        } catch (JSONException e) {
            return "invalid";
        }
    }


    void showLocations( JSONArray array){
        //your items
        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        for(int i =0; i<array.length(); i++){
            try {
                JSONObject object = array.getJSONObject(i);
                items.add(new OverlayItem("Car", "Description", new GeoPoint(Double.parseDouble(object.getString("lat")) ,Double.parseDouble(object.getString("long"))))); // Lat/Lon decimal degrees

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//the overlay
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        //do something
                        return true;
                    }
                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {

                        return false;
                    }
                }, getContext());
        mOverlay.setFocusItemsOnTap(true);

        offlineMap.getOverlays().add(mOverlay);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(responseRecieved);
    }

}
