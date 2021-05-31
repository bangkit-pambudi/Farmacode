package com.bangkit.farmacode.patient

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.farmacode.databinding.ActivityPatientBinding
import com.bangkit.farmacode.utils.Formatter
import com.google.firebase.database.*

class PatientActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        private const val TAG = "PatientActivity"
    }

    private lateinit var binding: ActivityPatientBinding
    private lateinit var dbReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            hide()
        }

        val idPatient = intent.getStringExtra(EXTRA_DATA)

        dbReference = FirebaseDatabase.getInstance().reference

        populateData(idPatient)
    }

    private fun populateData(idPatient: String?) {
        idPatient?.let { id -> dbReference.child("pasien").child(id).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.patientName.text = snapshot.child("nama").value.toString()
                binding.patientAge.text = snapshot.child("umur").value.toString()
                binding.patientGender.text = snapshot.child("gender").value.toString()

                // Formatter.change() -> change data default format to new format
                binding.patientAllergy.text = Formatter.change(snapshot.child("alergi").value.toString())
                binding.patientHistory.text = Formatter.change(snapshot.child("riwayatPenyakit").value.toString())
                binding.patientDiagnose.text = Formatter.change(snapshot.child("diagnosa").value.toString())
                binding.patientDrug.text = Formatter.change(snapshot.child("daftarObat").value.toString())
                binding.patientSchedule.text = Formatter.change(snapshot.child("jadwalObat").value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, error.message)
            }
        }) }
    }
}