package com.example.academia

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.*
import com.google.firebase.firestore.bundle.BundleCallback
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class ViewActivity : AppCompatActivity() {
    ////
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    //////
    private var webView: WebView? = null
    var progressBar: ProgressBar? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //////
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)



        //////
        val bundle: Bundle?
       bundle = intent.extras
        val web = bundle!!.getString("URL")

        webView = findViewById(R.id.myWebView)
        webView?.loadUrl(web!!)
        webView?.getSettings()?.loadsImagesAutomatically = true
        webView?.getSettings()?.javaScriptEnabled = true
        webView?.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)

        ////
        if (!isNetworkAvailable) { // loading offline
            webView!!.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }






        ///The method for Svse instnace ( for SCreen Roataion ) are created bewlow
        if (savedInstanceState != null) {
            (findViewById<View>(R.id.myWebView) as WebView).restoreState(
                savedInstanceState.getBundle("webViewState")!!
            )
        } else {
            webView = findViewById<View>(R.id.myWebView) as WebView
            load() // create seprate functin to loadweb
        }


        //////
      // progressBar = findViewById(R.id.progressBar)
        webView = findViewById(R.id.myWebView)


        //// calling the method
        initializeWebView()



        webView!!.setDownloadListener(
            DownloadListener { s, s1, s2, s3, l ->
                Dexter.withActivity(this@ViewActivity)
                    .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(object : PermissionListener {
                        override fun onPermissionGranted(response: PermissionGrantedResponse) {
                            val request = DownloadManager.Request(Uri.parse(s))
                            request.setMimeType(s3)
                            val cookies = CookieManager.getInstance().getCookie(s)
                            request.addRequestHeader("cookie", cookies)
                            request.addRequestHeader("User-Agent", s1)
                            request.setDescription("Downloading File.....")
                            request.setTitle(URLUtil.guessFileName(s, s2, s3))
                            request.allowScanningByMediaScanner()
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            request.setDestinationInExternalPublicDir(
                                Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(
                                    s, s2, s3
                                )
                            )
                            val downloadManager =
                                getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                            downloadManager.enqueue(request)
                            Toast.makeText(
                                this@ViewActivity,
                                "Downloading File..",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onPermissionDenied(response: PermissionDeniedResponse) {}
                        override fun onPermissionRationaleShouldBeShown(
                            permission: PermissionRequest,
                            token: PermissionToken
                        ) {
                            token.continuePermissionRequest()
                        }
                    }).check()
            })


        /// START SWIPE
        swipeRefreshLayout = findViewById<View>(R.id.swipeRefreshLayout) as SwipeRefreshLayout
        swipeRefreshLayout!!.setOnRefreshListener { webView!!.reload() }
        swipeRefreshLayout!!.setColorSchemeColors(
            resources.getColor(android.R.color.holo_blue_bright),
            resources.getColor(android.R.color.holo_orange_dark),
            resources.getColor(android.R.color.holo_green_dark),
            resources.getColor(android.R.color.holo_red_dark)
        )


        //solved problem on swipe

        //Solved WebView SwipeUp Problem


        ///ENd SWIPE
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initializeWebView() {


        webView!!.settings.javaScriptEnabled = true


        //modified web client
        webView!!.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView, url: String) {
                swipeRefreshLayout!!.isRefreshing = false
                super.onPageFinished(view, url)
            }
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)

                //if (isNetworkAvailable == null){
                //  webView?.loadUrl("www.google.com")
                //}

                return true
            }
            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                super.onReceivedError(view, request, error)



               ///// webView?.loadUrl("file:///android_asset/clock.html")

                //// Toast.makeText(applicationContext, "No internet connection", Toast.LENGTH_LONG).show()
            }

            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)

            }
        }

        ///// we set our   web view chrome client to control our ui
        webView!!.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
               // progressBar!!.visibility = View.VISIBLE
               // progressBar!!.progress = newProgress
                title = ""
                // progressDialog.show();
                if (newProgress == 100) {
                   // progressBar!!.visibility = View.GONE
                    /// title = "C N B C"

                    ///titleColor = titleColor.red
                    title = view.title
                    ///progressDialog.dismiss();
                }
                super.onProgressChanged(view, newProgress)
            }
        }


        //end of chrome client

        ///Defining the settings of the web view
        webView!!.settings.setAppCacheMaxSize((900 * 1024 * 1024).toLong()) // 5MB
        webView!!.settings.setAppCachePath(applicationContext.cacheDir.absolutePath + "cache")
        webView!!.settings.allowFileAccess = true
        webView!!.settings.setAppCacheEnabled(true)
        webView!!.settings.cacheMode = WebSettings.LOAD_DEFAULT
        webView!!.viewTreeObserver.addOnScrollChangedListener {
            swipeRefreshLayout!!.isEnabled = webView!!.scrollY == 0
        }

        ///for downloads
        webView!!.settings.domStorageEnabled = true
        webView!!.settings.loadsImagesAutomatically = true




    }

    override fun onBackPressed() {


        if (webView?.canGoBack() == true){
            webView!!.goBack()
        }
        else{
            super.onBackPressed()
        }

    }



    //Method for Network activity
    private val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    //// This method is respnisble to save instnce if SCREEN is Rotated
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        webView!!.saveState(bundle)
        outState.putBundle("webViewState", bundle)
    }

    override fun onRestoreInstanceState(state: Bundle) {
        super.onRestoreInstanceState(state)
        val bundle = Bundle()
        webView!!.saveState(bundle)
        state.putBundle("webViewState", bundle)
    }

    ////
    @SuppressLint("SetJavaScriptEnabled")
    fun load() {
        webView = findViewById<View>(R.id.myWebView) as WebView
        webView!!.webViewClient = WebViewClient()
        webView!!.settings.loadsImagesAutomatically = true
        webView!!.settings.javaScriptEnabled = true
        webView!!.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
    }


    override fun onSupportNavigateUp(): Boolean {

        finish()


        return true
    }



}