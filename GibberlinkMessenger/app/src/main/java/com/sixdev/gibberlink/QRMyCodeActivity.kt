package com.sixdev.gibberlink

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set

class QRMyCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_my_code)

        val username = getSharedPreferences("gibber link", MODE_PRIVATE).getString("username", "unknown")
        val qrImage = findViewById<ImageView>(R.id.qrImage)

        val qrWriter = QRCodeWriter()
        val bitMatrix = qrWriter.encode(username, BarcodeFormat.QR_CODE, 500, 500)
        val bmp = createBitmap(500, 500, Bitmap.Config.RGB_565)
        for (x in 0 until 500) {
            for (y in 0 until 500) {
                bmp[x, y] =
                    if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE
            }
        }

        qrImage.setImageBitmap(bmp)
    }
}
