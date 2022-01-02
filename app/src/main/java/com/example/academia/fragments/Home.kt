package com.example.academia.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.example.academia.R
import com.google.firebase.database.*


class Home : Fragment() {

    // creating a variable for our Firebase Database.
    var firebaseDatabase: FirebaseDatabase? = null

    // creating a variable for our Database
    // Reference for Firebase.

    private var reference: DatabaseReference? = null

    // creating a variable for our webview
    private var webView: WebView? = null

    var databaseReference: DatabaseReference? = null

    /////

    var progressBar: ProgressBar? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?

    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        // progressBar = view?.findViewById(R.id.progressBar)

        ////
        swipeRefreshLayout = view?.findViewById<View>(R.id.swipeRefreshLayout) as SwipeRefreshLayout
        swipeRefreshLayout!!.setOnRefreshListener { webView!!.reload() }
        swipeRefreshLayout!!.setColorSchemeColors(
            resources.getColor(android.R.color.holo_blue_bright),
            resources.getColor(android.R.color.holo_orange_dark),
            resources.getColor(android.R.color.holo_green_dark),
            resources.getColor(android.R.color.holo_red_dark)
        )


        //Solved WebView SwipeUp Problem


        /////


       val viewpager_n = view.findViewById<ViewPager>(R.id.viewpager)
        viewpager_n?.offscreenPageLimit = 3






        reference = FirebaseDatabase.getInstance().reference.child("home")
        reference!!.keepSynced(true)





        webView = view.findViewById(R.id.myWebView)
        // below line is used to get the instance
        // of our Firebase database.
        // below line is used to get the instance
        // of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance()
        // below line is used to get reference for our database.
        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase!!.getReference("home")

        /// Call the chrome Client function
        my_wechtome()

        /// Call the database function
        my_databse()


        /////


        /////
        return view
    }


    //chrome database function
    private fun my_databse() {

        databaseReference!!.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetJavaScriptEnabled")
            override fun onDataChange(snapshot: DataSnapshot) {

                val webUrl = snapshot.getValue(String::class.java)
                webView!!.loadUrl(webUrl!!)
                webView!!.settings.javaScriptEnabled = true


                //other settings


                //end of chrome client

                ///Defining the settings of the web view
                webView!!.settings.setAppCacheMaxSize((900 * 1024 * 1024).toLong()) // 5MB

                webView!!.settings.setAppCachePath(
                    (activity?.applicationContext?.cacheDir?.absolutePath)
                )
                webView!!.settings.allowFileAccess = true
                webView!!.settings.setAppCacheEnabled(true)
                webView!!.settings.cacheMode = WebSettings.LOAD_DEFAULT

                webView!!.viewTreeObserver.addOnScrollChangedListener {
                    swipeRefreshLayout!!.isEnabled = webView!!.scrollY == 0
                }



                if (!isNetworkAvailable()) { // loading offline
                    webView!!.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                }


            }

            override fun onCancelled(error: DatabaseError) {}
        })


    }


    //chrome client function
    private fun my_wechtome() {


        //modified web client

        // we set the WebView Client
        webView!!.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                swipeRefreshLayout!!.isRefreshing = false
                super.onPageFinished(view, url)

            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

        }

        /// the onnback pressed function
        webView!!.setOnKeyListener { view, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (webView != null) {
                        if (webView!!.canGoBack()) {
                            webView!!.goBack()
                        } else {
                            requireActivity().onBackPressed()
                        }
                    }
                }
            }
            true
        }

        ///// we set our   web view chrome client to control our ui
        webView!!.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                //  progressBar!!.visibility = View.VISIBLE
                //   progressBar!!.progress = newProgress
                // progressDialog.show();
                //  if (newProgress == 100) {
                // progressBar!!.visibility = View.GONE
                // title = "Feeds"

                ///titleColor = titleColor.red
                // title = view.title
                ///progressDialog.dismiss();
                // }
                super.onProgressChanged(view, newProgress)
            }
        }
    }
/// End of chrome cleint function


    //Method for Network activity
    open fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return !(activeNetworkInfo == null || !activeNetworkInfo.isConnected)
    }

    override fun onPause() {
        super.onPause()
        webView?.onPause()
    }
    override fun onResume() {
        super.onResume()
        webView?.onResume()
    }
    ///end on crete view

}







