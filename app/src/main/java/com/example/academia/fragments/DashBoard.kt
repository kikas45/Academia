package com.example.academia.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.academia.Grid
import com.example.academia.R
import com.example.academia.adapter.CustomViewPager
import com.example.academia.adapter.SliderAdapter
import com.example.academia.adapter.SliderData
import com.google.firebase.firestore.FirebaseFirestore
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import java.util.ArrayList


class DashBoard : Fragment() {

    // creating variables for our adapter, array list,
    // firebase firestore and our sliderview.
    private var adapter: SliderAdapter? = null
    private var sliderDataArrayList: ArrayList<SliderData>? = null
    var db: FirebaseFirestore? = null
    private var sliderView: SliderView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dasboard, container, false)

    val viewpager_n = view.findViewById<CustomViewPager>(R.id.viewpager)
     viewpager_n?.offscreenPageLimit = 3




    ///////

        //////

  var start = view?.findViewById<ImageView>(R.id.button)

 start?.setOnClickListener {
            // USED TO PREVENT DOUBLE CLICK
       start.isEnabled = false;

      var starty = Intent(activity, Grid::class.java)

      startActivity(starty)
 }

        /////

      ///  var image_h = view?.findViewById<ImageView>(R.id.video)
       /// image_h?.setOnClickListener {

            // USED TO PREVENT DOUBLE CLICK
        ///    image_h.isEnabled = false;
          //  var start_image = Intent(activity, Grid::class.java)
         ///   startActivity(start_image) }




        /////// IMage slider

        // creating a new array list fr our array list.

        // creating a new array list fr our array list.
        sliderDataArrayList = ArrayList()

        // initializing our slider view and
        // firebase firestore instance.

        // initializing our slider view and
        // firebase firestore instance.
        sliderView = view?.findViewById<SliderView>(R.id.slider)
        db = FirebaseFirestore.getInstance()

        // calling our method to load images.

        // calling our method to load images.
        loadImages()

        ////////




        //////

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

                // after that we are adding that
                // data inside our array list.
                sliderDataArrayList!!.add(model)

                // after adding data to our array list we are passing
                // that array list inside our adapter class.
                adapter = SliderAdapter(this@DashBoard.activity, sliderDataArrayList)

                // belows line is for setting adapter
                // to our slider view
                sliderView!!.setSliderAdapter(adapter!!)

                // below line is for setting animation to our slider.
                sliderView!!.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)

                // below line is for setting auto cycle duration.
                sliderView!!.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT

                // below line is for setting
                // scroll time animation
                sliderView!!.scrollTimeInSec = 3

                // below line is for setting auto
                // cycle animation to our slider
                sliderView!!.isAutoCycle = true

                // below line is use to start
                // the animation of our slider view.
                sliderView!!.startAutoCycle()
            }
        }.addOnFailureListener { // if we get any error from Firebase we are
            // displaying a toast message for failure
            Toast.makeText(this@DashBoard.activity, "Fail to load slider data..", Toast.LENGTH_SHORT)
                .show()
        }

        sliderView?.setOnClickListener {
            Toast.makeText(this@DashBoard.activity, "Fail to load slider data..", Toast.LENGTH_SHORT)
                .show()

        }
    }


    //// THE REASO FOR THIS ADDITIONAL CODE IS TO PREVENT DOUBLE CLICK WHEN LUNCHNG ACITIVITY

    override fun onResume() {
        super.onResume()

  val button1 = view?.findViewById<ImageView>(R.id.button)
 button1?.isEnabled = true

    //val button2  = view?.findViewById<ImageView>(R.id.video)
//button2?.isEnabled = true

    }


}
