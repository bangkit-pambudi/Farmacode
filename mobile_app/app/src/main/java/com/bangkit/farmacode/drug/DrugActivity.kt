package com.bangkit.farmacode.drug

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.farmacode.databinding.ActivityDrugBinding
import com.bangkit.farmacode.utils.Formatter
import com.google.firebase.database.*

class DrugActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        private const val TAG = "DrugActivity"
    }

    private lateinit var binding: ActivityDrugBinding
    private lateinit var dbReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrugBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            hide()
        }

        val idDrug = intent.getStringExtra(EXTRA_DATA)

        dbReference = FirebaseDatabase.getInstance().reference

        populateData(idDrug)
    }

    private fun populateData(idDrug: String?) {
        idDrug?.let { id -> dbReference.child("obat").child(id).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.drugName.text = snapshot.child("nama").value.toString()

                // Formatter.change() -> change data default format to new format
                binding.drugContain.text = Formatter.change(snapshot.child("kandungan").value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, error.message)
            }
        }) }
    }
}