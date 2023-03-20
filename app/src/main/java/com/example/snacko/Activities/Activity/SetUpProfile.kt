package com.example.snacko.Activities.Activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.snacko.databinding.ActivitySetUpProfileBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class SetUpProfile : AppCompatActivity() {
    private lateinit var binding: ActivitySetUpProfileBinding
    private lateinit var database: DatabaseReference
    private lateinit var fAuth: FirebaseAuth
    private var selectedImage: Uri? = null
    private lateinit var reference: StorageReference
    private lateinit var user: Map<String, String>
    private var selectedDistrict: String? = null
    private  var selectedState:kotlin.String? = null
    private lateinit var tvStateSpinner: TextView
    private lateinit var tvDistrictSpinner : TextView
    private lateinit var stateSpinner: Spinner
    private  lateinit var districtSpinner: Spinner
    private lateinit var stateAdapter: ArrayAdapter<CharSequence>
    private lateinit var districtAdapter: ArrayAdapter<CharSequence?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySetUpProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference()
        var userUid=fAuth.currentUser!!.uid
        database.child("Users").child(userUid).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    //                var sharedPref = getSharedPreferences("User Info", Context.MODE_PRIVATE)
                    //                var editor = sharedPref.edit()
                    //                editor.putString("UsersUid", userUid.toString())
                    //                editor.apply()
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    stateSpinner = findViewById(com.example.snacko.R.id.spinner_indian_states)
                    stateAdapter = ArrayAdapter.createFromResource(this@SetUpProfile, com.example.snacko.R.array.array_indian_states, com.example.snacko.R.layout.spinner_layout)
                    stateAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    stateSpinner.setAdapter(stateAdapter)
                    stateSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            //Define City Spinner but we will populate the options through the selected state
                            districtSpinner = findViewById<Spinner>(com.example.snacko.R.id.spinner_indian_districts)
                            selectedState =
                                stateSpinner.getSelectedItem().toString() //Obtain the selected State
                            val parentID = parent.id
                            if (parentID == com.example.snacko.R.id.spinner_indian_states) {
                                when (selectedState) {
                                    "Select Your State" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_default_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Andhra Pradesh" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_andhra_pradesh_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Arunachal Pradesh" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_arunachal_pradesh_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Assam" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_assam_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Bihar" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_bihar_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Chhattisgarh" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_chhattisgarh_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Goa" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_goa_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Gujarat" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_gujarat_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Haryana" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_haryana_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Himachal Pradesh" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_himachal_pradesh_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Jharkhand" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_jharkhand_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Karnataka" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_karnataka_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Kerala" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_kerala_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Madhya Pradesh" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_madhya_pradesh_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Maharashtra" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_maharashtra_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Manipur" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_manipur_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Meghalaya" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_meghalaya_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Mizoram" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_mizoram_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Nagaland" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_nagaland_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Odisha" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_odisha_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Punjab" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_punjab_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Rajasthan" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_rajasthan_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Sikkim" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_sikkim_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Tamil Nadu" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_tamil_nadu_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Telangana" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_telangana_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Tripura" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_tripura_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Uttar Pradesh" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_uttar_pradesh_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Uttarakhand" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_uttarakhand_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "West Bengal" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_west_bengal_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Andaman and Nicobar Islands" -> districtAdapter =
                                        ArrayAdapter.createFromResource(
                                            parent.context,
                                            com.example.snacko.R.array.array_andaman_nicobar_districts, com.example.snacko.R.layout.spinner_layout
                                        )
                                    "Chandigarh" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_chandigarh_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Dadra and Nagar Haveli" -> districtAdapter =
                                        ArrayAdapter.createFromResource(
                                            parent.context,
                                            com.example.snacko.R.array.array_dadra_nagar_haveli_districts, com.example.snacko.R.layout.spinner_layout
                                        )
                                    "Daman and Diu" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_daman_diu_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Delhi" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_delhi_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Jammu and Kashmir" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_jammu_kashmir_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Lakshadweep" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_lakshadweep_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Ladakh" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_ladakh_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    "Puducherry" -> districtAdapter = ArrayAdapter.createFromResource(
                                        parent.context,
                                        com.example.snacko.R.array.array_puducherry_districts, com.example.snacko.R.layout.spinner_layout
                                    )
                                    else -> {
                                    }
                                }
                                districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Specify the layout to use when the list of choices appears
                                districtSpinner.setAdapter(districtAdapter) //Populate the list of Districts in respect of the State selected

                                //To obtain the selected District from the spinner
                                districtSpinner.setOnItemSelectedListener(object :
                                    AdapterView.OnItemSelectedListener {
                                    override fun onItemSelected(
                                        parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long
                                    ) {
                                        selectedDistrict = districtSpinner.getSelectedItem().toString()
                                    }

                                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                                })
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                    })
                    tvStateSpinner = findViewById(com.example.snacko.R.id.textView_indian_states)
                    tvDistrictSpinner = findViewById<TextView>(com.example.snacko.R.id.textView_indian_districts)
                    binding.userImage.setOnClickListener {
                        launchGallery()
                    }
                    binding.updateBtn.setOnClickListener {
                        if (selectedImage != null) {
                            if (selectedState == "Select Your State") {
                                Toast.makeText(this@SetUpProfile, "Please select your state from the list", Toast.LENGTH_LONG).show()
                                tvStateSpinner.setError("State is required!") //To set error on TextView
                                tvStateSpinner.requestFocus()
                            } else if (selectedDistrict == "Select Your District") { Toast.makeText(this@SetUpProfile, "Please select your district from the list", Toast.LENGTH_LONG).show()
                                tvDistrictSpinner.setError("District is required!")
                                tvDistrictSpinner.requestFocus()
                                tvStateSpinner.setError(null)
                            } else {
                                tvStateSpinner.setError(null)
                                tvDistrictSpinner.setError(null)
                                Toast.makeText(this@SetUpProfile, "Selected State: $selectedState\nSelected District: $selectedDistrict", Toast.LENGTH_LONG).show() }
                            reference = FirebaseStorage.getInstance().getReference().child("Profile").child(fAuth.currentUser!!.uid)
                            reference.putFile(selectedImage!!).addOnCompleteListener(
                                OnCompleteListener {
                                if (it.isSuccessful) {
                                    reference.downloadUrl.addOnSuccessListener {
                                        var imageUpload=it.toString()
                                        user = mapOf<String, String>(
                                            "usersUid" to userUid.toString().trim(),
                                            "status" to binding.status.text.toString().trim(),
                                            "description" to binding.description.text.toString().trim(),
                                            "name" to binding.userName.text.toString().trim(),
                                            "image" to it.toString(),
                                            "state" to selectedState.toString(),
                                            "district" to selectedDistrict.toString()
                                        )
                                        database.child("Profile").child(userUid).updateChildren(user).addOnSuccessListener {
                                            var sharedPref = getSharedPreferences("User Info", MODE_PRIVATE)
                                            var editor = sharedPref.edit()
                                            editor.putString("Name",binding.userName.text.toString().trim() )
                                            editor.putString("UserUid",userUid )
                                            editor.putString("State",selectedState.toString() )
                                            editor.putString("District",selectedDistrict.toString() )
                                            editor.putString("Image", imageUpload)
                                            editor.apply()
                                            var intent = Intent(this@SetUpProfile, MainActivity::class.java)
                                            startActivity(intent)
                                            finish()
                                        }.addOnFailureListener {
                                            Toast.makeText(this@SetUpProfile, "Error In Updating ", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            })
                        } else {
                            user = mapOf<String, String>(
                                "usersUid" to userUid,
                                "status" to binding.status.text.toString().trim(),
                                "description" to binding.description.text.toString().trim(),
                                "name" to binding.userName.text.toString().trim(),
                                "state" to selectedState.toString(),
                                "district" to selectedDistrict.toString()
                            )
                            database.child("Profile").child(userUid.toString().trim()).updateChildren(user).addOnSuccessListener {
                                Toast.makeText(this@SetUpProfile, "Status Uploaded", Toast.LENGTH_SHORT).show()
                                var sharedPref = getSharedPreferences("User Info", Context.MODE_PRIVATE)
                                var editor = sharedPref.edit()
                                editor.putString("Name",binding.userName.text.toString().trim() )
                                editor.putString("UserUid",userUid)
                                editor.putString("State",selectedState.toString())
                                editor.putString("District",selectedDistrict.toString())
                                editor.apply()
                                var intent = Intent(this@SetUpProfile, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (data.data != null) {
                binding.userImage.setImageURI(data.data)
                selectedImage = data.data
            }
        }
    }

    private fun launchGallery() {
        var intent = Intent()
        intent.setAction(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, 100)
    }
}