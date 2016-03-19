package com.gazematic.loofinder;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NavigateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NavigateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigateFragment extends Fragment implements GoogleMap.OnMapClickListener , GoogleMap.OnInfoWindowClickListener {


    MapView mapView;
    GoogleMap map;
    Route drawMyRoute;
    LocationManager locMgr;
    Marker userloc = null;
    MyLocationListener locLstnr;
    ArrayList<Marker> markers;
    Polyline poly;
    Route r = new Route();
    LatLng sfLatLng1 = new LatLng(30.745893,76.785096);

    double [] l = {30.745893,31.559815,18.947206,12.821175,28.652633};
    double [] l1 = {76.785096,75.595093,72.836494,80.22274,77.123623};
    String [] s = {"PRS Chandigarh","PRS Jalandhar","PRS Mumbai", "PRS Chennai","PRS Delhi"};
    String [] s1  = {"Block No. 9, Jan Marg, Chandigarh, India","Choti Baradari, Jalandhar, Punjab, India","Apollo Bandar, Mumbai, Maharashtra","Rajiv Gandhi IT Expressway,Chennai ","A-38, Vishal Enclave, Rajouri Garden, New Delhi,India"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        // Gets the MapView from the XML layout and creates it

        MapsInitializer.initialize(getActivity());

//        try {
//
//        } catch (GooglePlayServicesNotAvailableException e) {
//            Log.e("Address Map", "Could not initialize google play", e);
//        }

        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity())) {
            case ConnectionResult.SUCCESS:
                Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                mapView = (MapView) v.findViewById(R.id.map);
                mapView.onCreate(savedInstanceState);
                // Gets to GoogleMap from the MapView and does initialization stuff
                if (mapView != null) {
                    map = mapView.getMap();
                    map.getUiSettings().setMyLocationButtonEnabled(false);
                    //map.setMyLocationEnabled(true);

                    LatLng president_estate = new LatLng(28.6177897, 77.1951896);
                    LatLng rashtrapati_bhavan = new LatLng(28.6165465, 77.1921426);
                    LatLng sardar_patel_marg = new LatLng(28.6077542, 77.1874975);
                    LatLng mother_terasa_crescent = new LatLng(28.614875, 77.190106);
                    LatLng talkatora_road = new LatLng(28.623589, 77.200853);


                    map.addMarker(new MarkerOptions().position(president_estate).title("Rashtrapati Bhavan"));
                    map.addMarker(new MarkerOptions().position(rashtrapati_bhavan).title("Rashtrapati Bhavan"));
                    map.addMarker(new MarkerOptions().position(sardar_patel_marg).title("Rashtrapati Bhavan"));
                    map.addMarker(new MarkerOptions().position(mother_terasa_crescent).title("Rashtrapati Bhavan"));
                    map.addMarker(new MarkerOptions().position(talkatora_road).title("Rashtrapati Bhavan"));


                    MarkerOptions marker = new MarkerOptions().position(new LatLng(28.616384, 77.195294)).title("Current Location");

                    // Changing marker icon
                    marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.blue));

                    map.addMarker(marker);


                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(28.616858, 77.195229), 16);
                    map.animateCamera(cameraUpdate);


                    //drawRoute(GoogleMap map, Context c, LatLng source, LatLng dest, String language)

                    drawMyRoute =  new Route();

                    drawMyRoute.drawRoute(map, getActivity(), new LatLng(28.616384, 77.195294),  new LatLng(28.6177897, 77.1951896), "en");




                }
                break;
            case ConnectionResult.SERVICE_MISSING:
                Toast.makeText(getActivity(), "SERVICE MISSING", Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                Toast.makeText(getActivity(), "UPDATE REQUIRED", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getActivity(), GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()), Toast.LENGTH_SHORT).show();
        }


        // Updates the location and zoom of the MapView

        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;


        //28.6165465,77.1921426 - Rashtrapati Bhavan
        //28.6077542,77.1874975 - Sardar patel Marg
        //28.614875, 77.190106  - Mother Terasa Crescent
        //28.623589, 77.200853  - Talkatora road

        // Add a marker in Sydney and move the camera
        LatLng president_estate = new LatLng(28.6177897, 77.1951896);
        LatLng rashtrapati_bhavan = new LatLng(28.6165465, 77.1921426);
        LatLng sardar_patel_marg = new LatLng(28.6077542, 77.1874975);
        LatLng mother_terasa_crescent = new LatLng(28.614875, 77.190106);
        LatLng talkatora_road = new LatLng(28.623589, 77.200853);


        map.addMarker(new MarkerOptions().position(president_estate).title("Rashtrapati Bhavan"));
        map.addMarker(new MarkerOptions().position(rashtrapati_bhavan).title("Rashtrapati Bhavan"));
        map.addMarker(new MarkerOptions().position(sardar_patel_marg).title("Rashtrapati Bhavan"));
        map.addMarker(new MarkerOptions().position(mother_terasa_crescent).title("Rashtrapati Bhavan"));
        map.addMarker(new MarkerOptions().position(talkatora_road).title("Rashtrapati Bhavan"));
        map.moveCamera(CameraUpdateFactory.newLatLng(president_estate));
    }




//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_COARSE_LOCATION:
//            {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    //noinspection ResourceType
//                    Location myLocation = m_locationManager.getLastKnownLocation(provider);
//                } else {
//                    //Permission denied
//                }
//                return;
//            }
//        }
//    }


    @Override
    public void onMapClick(LatLng point) {
        String hgg = "" + point.latitude;
        String cvb    = "" + point.longitude;
        String f = hgg + ", " + cvb ;
        //Toast.makeText( getApplicationContext(),
        //      f,
        //    Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        LatLng point = marker.getPosition();
        String hgg = "" + point.latitude;
        String cvb    = "" + point.longitude;
        String f = hgg + ", " + cvb ;
        r.drawRoute(map, getActivity(),sfLatLng1,point,true,Route.LANGUAGE_ENGLISH);

    }

    class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            //To change body of implemented methods use File | Settings | File Templates.
            //location.getLatitude();
            //location.getLongitude();
            String coordinates[] = {""+location.getLatitude(), ""+location.getLongitude()};
            double lat = Double.parseDouble(coordinates[0]);
            double lng = Double.parseDouble(coordinates[1]);
            if(map != null) {
                if(userloc != null)    {
                    userloc.remove();
                }
                LatLng sfLatLng = new LatLng(lat,lng);
                userloc = map.addMarker(new MarkerOptions()
                        .position(sfLatLng)
                        .title("My Location")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                sfLatLng1 = sfLatLng;
            }

        }


        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onProviderEnabled(String s) {
            Toast.makeText( getActivity(),
                    "GPS Enabled",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String s) {
            Toast.makeText(getActivity(),
                    "GPS Disabled",
                    Toast.LENGTH_SHORT ).show();
        }


    }

}
