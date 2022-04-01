package com.example.academia.fragments

import android.app.AlertDialog
import android.app.Notification
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.Toast
import com.example.academia.adapter.CustomViewPager
import com.example.academia.adapter.SliderAdapter
import com.example.academia.adapter.SliderData
import com.google.firebase.firestore.FirebaseFirestore
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.ArrayList
import android.view.MenuInflater
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.academia.*


class DashBoard : Fragment() {

    // creating variables for our adapter, array list,
    // firebase firestore and our sliderview.
    private var toolbar: Toolbar? = null
    private var adapter: SliderAdapter? = null
    private var sliderDataArrayList: ArrayList<SliderData>? = null
    var db: FirebaseFirestore? = null
    private var sliderView: SliderView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val view = inflater.inflate(R.layout.fragment_dasboard, container, false)


        //setting up the toolbar
        toolbar = view.findViewById(R.id.toolbar_dashbord)
        toolbar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_about -> {
                    val uitter = Intent(context, SettingsActivity::class.java)
                    startActivity(uitter)
                    true
                }
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                else -> false
            }
        }




        var start = view?.findViewById<ImageView>(R.id.button)
        start?.setOnClickListener {
            // USED TO PREVENT DOUBLE CLICK
            start.isEnabled = false
            val starty = Intent(activity, Grid::class.java)
            startActivity(starty) }


        val image_h = view?.findViewById<ImageView>(R.id.cal)
        image_h?.setOnClickListener {
            image_h.isEnabled = false
            val intent = Intent(activity?.applicationContext, ViewActivity::class.java)
            val jk: String = "file:///android_asset/index.html"
            intent.putExtra("URL", jk)
            startActivity(intent)
        }


        val image_Video = view?.findViewById<ImageView>(R.id.image_Video)
        image_Video?.setOnClickListener {
            image_Video?.isEnabled = false
            val intentu = Intent(activity?.applicationContext, ViewActivity::class.java)
            val jk = "file:///android_asset/index.html"
            intentu.putExtra("URL", jk)
            startActivity(intentu)
        }

        /////// IMage slider


        sliderDataArrayList = ArrayList()
        sliderView = view?.findViewById<SliderView>(R.id.slider)
        db = FirebaseFirestore.getInstance()

        loadImages()
        return view
    }


    private fun loadImages() {
        db!!.collection("Slider").get().addOnSuccessListener { queryDocumentSnapshots ->
            // inside the on success method we are running a for loop
            // and we are getting the data from Firebase Firestore
            for (documentSnapshot in queryDocumentSnapshots) {

                // after we get the data we are passing inside our object class.
                val sliderData = documentSnapshot.toObject(SliderData::class.java)
                val model = SliderData()

                // below line is use for setting our
                // image url for our modal class.
                model.imgUrl = sliderData.imgUrl
                model.name = sliderData.name

                // after that we are adding that
                // data inside our array list.
                sliderDataArrayList!!.add(model)
                adapter = SliderAdapter(this@DashBoard.activity, sliderDataArrayList)
                sliderView!!.setSliderAdapter(adapter!!)
                sliderView!!.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                sliderView!!.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
                sliderView!!.scrollTimeInSec = 2
                // below line is for setting auto
                // cycle animation to our slider
                sliderView!!.isAutoCycle = true
                // below line is use to start
                // the animation of our slider view.
                sliderView!!.startAutoCycle()
            }
        }.addOnFailureListener { // if we get any error from Firebase we are
            // displaying a toast message for failure
            Toast.makeText(this@DashBoard.activity, "Fail to load slider data..", Toast.LENGTH_SHORT).show()
        }

       sliderView?.setOnClickListener {
           if (adapter?.getItemPosition(true) == 1){Toast.makeText(this@DashBoard.activity, "checked",  Toast.LENGTH_SHORT).show()}

           else {
               Toast.makeText(this@DashBoard.activity, "Fail to load slider data..", Toast.LENGTH_SHORT).show()
           }

       }
    }

    //// THE REASO FOR THIS ADDITIONAL CODE IS TO PREVENT DOUBLE CLICK WHEN LUNCHNG ACITIVITY
    override fun onResume() {
        super.onResume()
        val button1 = view?.findViewById<ImageView>(R.id.button)
        button1?.isEnabled = true
        val button2 = view?.findViewById<ImageView>(R.id.cal)
        button2?.isEnabled = true
        val button3 = view?.findViewById<ImageView>(R.id.image_Video)
        button3?.isEnabled = true
    }

}
