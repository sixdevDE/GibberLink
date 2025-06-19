package com.sixdev.gibberlink

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class QRScanActivity : AppCompatActivity() {

    // Register for activity result for camera permission
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your app.
                startQrScanner()
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied.
                Toast.makeText(this, "Camera permission is required to scan QR codes", Toast.LENGTH_LONG).show()
                finish() // Close the activity if permission is denied
            }
        }

    // Register for activity result for the QR scanner
    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this, "Scan cancelled or failed", Toast.LENGTH_LONG).show()
        } else {
            val contact = result.contents
            Toast.makeText(this, "Scanned: $contact", Toast.LENGTH_SHORT).show()
            // TODO: Process the scanned contact (save or pass it on)
            // For example, you could pass it back to a calling activity:
            // val returnIntent = Intent()
            // returnIntent.putExtra("scanned_data", contact)
            // setResult(Activity.RESULT_OK, returnIntent)
        }
        // Finish the activity after scanning or cancellation
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // No need to call setContentView if this activity is only for launching the scanner

        checkCameraPermissionAndStartScanner()
    }

    private fun checkCameraPermissionAndStartScanner() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                startQrScanner()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.
                // For this example, we'll just request it directly.
                Toast.makeText(this, "Camera permission is needed to scan QR codes.", Toast.LENGTH_LONG).show()
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
            else -> {
                // You can directly ask for the permission.
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun startQrScanner() {
        val options = ScanOptions()
        options.setPrompt("Scanne Gibberlink-QR") // "Scan Gibberlink QR Code"
        options.setBeepEnabled(true)
        options.setOrientationLocked(true)
        // options.captureActivity = YourCustomCaptureActivity::class.java // If you have one
        barcodeLauncher.launch(options)
    }
}