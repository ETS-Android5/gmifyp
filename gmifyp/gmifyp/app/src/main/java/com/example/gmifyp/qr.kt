package com.example.gmifyp

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.QRFragment
import com.budiyev.android.codescanner.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import java.text.DateFormat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import java.text.SimpleDateFormat
import java.time.*
import java.util.*
import java.util.*

private const val CAMERA_REQUEST_CODE = 101

class Qr : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    public var duration: Long = System.currentTimeMillis()
    public var date = SimpleDateFormat("dd/MM/yyyy")
    public var clockFormat = SimpleDateFormat("h:mm a")
    public var sree =5
    val PERMISSION_ID = 1010
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr)
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        setupPermission()
        codeScanner = CodeScanner(this, scannerView)
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            // Parameters (default values)
            formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
            // ex. listOf(BarcodeFormat.QR_CODE)
            autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
            scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
            isAutoFocusEnabled = true // Whether to enable auto focus or not
            isFlashEnabled = true // Whether to enable flash or not
        }
        //((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(R.string.subtitle);
        supportActionBar?.hide()
        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                val b = ""
                val a = it.text
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
                Toast.makeText(this, date.toString(),Toast.LENGTH_LONG).show()
                Toast.makeText(this, clockFormat.toString(),Toast.LENGTH_LONG).show()
                Toast.makeText(this, locationRequest.toString(),Toast.LENGTH_LONG).show()
                Toast.makeText(this, duration.toString(),Toast.LENGTH_LONG).show()
                val qrf = QRFragment()
                qrf.scanstate.visibility = View.VISIBLE
                qrf.scanstate.text = "Check in on " + qrf.currentTime
                val intent = Intent(this, QRFragment::class.java).apply {
                    putExtra(EXTRA_MESSAGE, "Scan successful")
                     val GREENLIGT =5
                    //if (!url.startsWith("http://") && !url.startsWith("https://"))
                      //  url = "http://" + url;

                   // val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
                   // startActivity(browserIntent)
                }
                val calendar = Calendar.getInstance()
                val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
                Toast.makeText(this, currentDate, Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()


            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()


            //if valid text change to sucess & qr show drawable checkcircle
            //else text says QR invalid!!
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermission(){
        val permission1 = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.CAMERA)
        val permission2 = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.ACCESS_FINE_LOCATION)
        val permission3 = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val permission4 = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE)
        val permission5 = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.INTERNET)
        if(permission1 != PackageManager.PERMISSION_GRANTED||permission2 != PackageManager.PERMISSION_GRANTED
            ||permission3 != PackageManager.PERMISSION_GRANTED ||permission4 != PackageManager.PERMISSION_GRANTED
            ||permission5 != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }

    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.INTERNET,
                android.Manifest.permission.READ_EXTERNAL_STORAGE),CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
            when (requestCode){
                CAMERA_REQUEST_CODE ->  {
                    if(grantResults.isEmpty()||grantResults[0] != PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this,"Camera Permission Needed",Toast.LENGTH_SHORT).show()
                }

            }

    }
    fun isLocationEnabled():Boolean{
        //this function will return to us the state of the location service
        //if the gps or the network provider is enabled then it will return true otherwise it will return false
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
}