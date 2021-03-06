package com.example.academia.fragments

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
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
import com.example.academia.ViewActivity
import com.google.firebase.database.*


class Home : Fragment() {

    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

    // creating a variable for our webview
    private var webView: WebView? = null


    /// prgress dialog

    var progressDialog: ProgressDialog? = null

    /////

    var progressBar: ProgressBar? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?

    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        progressBar = view?.findViewById(R.id.progressBar)
        progressDialog = ProgressDialog(context)
        progressDialog!!.setMessage("Loading Please Wait")


        ////
        swipeRefreshLayout = view?.findViewById<View>(R.id.swipeRefreshLayout) as SwipeRefreshLayout
        swipeRefreshLayout!!.setOnRefreshListener { webView!!.reload() }
        swipeRefreshLayout!!.setColorSchemeColors(
            resources.getColor(android.R.color.holo_blue_bright),
            resources.getColor(android.R.color.holo_orange_dark),
            resources.getColor(android.R.color.holo_green_dark),
            resources.getColor(android.R.color.holo_red_dark)
        )

        /////
       val viewpager_n = view.findViewById<ViewPager>(R.id.custom_viewpager)
        viewpager_n?.offscreenPageLimit = 3

        ///////

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase!!.getReference().child("Web").child("doc").child("url1")
        /////
        webView = view.findViewById(R.id.myWebView)

        /// Call the chrome Client function
        my_wechtome()

        /// Call the database function
        my_databse()

        ////
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
                webView!!.settings.useWideViewPort = true


                ///Defining the settings of the web view to save states in locl catch
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

                val intent = Intent(activity?.applicationContext, ViewActivity::class.java)
                intent.putExtra("URL", url)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
               //startActivity(intent)
                startActivityForResult(intent, 1)
               webView!!.stopLoading()
                webView!!.requestDisallowInterceptTouchEvent(false)

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




                progressDialog?.show();
                if (newProgress == 100) {
                    progressBar!!.visibility = View.GONE
                    progressDialog?.dismiss();
                }

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


    /// this method is used to stop Audio and video playimg at background when view pager swipes

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







