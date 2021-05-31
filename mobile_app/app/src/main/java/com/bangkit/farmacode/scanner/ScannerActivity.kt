package com.bangkit.farmacode.scanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.farmacode.databinding.ActivityScannerBinding
import com.bangkit.farmacode.drug.DrugActivity
import com.bangkit.farmacode.patient.PatientActivity

class ScannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.scannerButton.setOnClickListener {
            // Simulate the scanner to get the id
            val id = binding.scannerInput.text.toString()

            // Intent check where to navigate
            when (id[0].toString()) {
                "0" -> {
                    Intent(this, PatientActivity::class.java).apply {
                        putExtra(PatientActivity.EXTRA_DATA, id)
                        startActivity(this)
                    }
                }
                "1" -> {
                    Intent(this, DrugActivity::class.java).apply {
                        putExtra(DrugActivity.EXTRA_DATA, id)
                        startActivity(this)
                    }
                }
            }
        }

    }
}