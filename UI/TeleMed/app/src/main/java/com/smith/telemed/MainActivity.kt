package com.smith.telemed

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.renderscript.ScriptGroup
import android.util.Log
import com.smith.telemed.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val REQUEST_CODE = 2
    private var foodImageUri: Uri? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectImageButton.setOnClickListener {
            openGalleryForImage()
        }

        var serialnumber: ResponseModel? = null
        binding.getInfoButton.setOnClickListener {

            if(foodImageUri != null) {
                GlobalScope.launch {
                    try {
                        val imageRequestBody = getImageRequestBody(foodImageUri!!)
                        serialnumber =
                            OcrRemoteDataSource(OcrApi, Dispatchers.IO).fetchSerialNumber(
                                imageRequestBody
                            )

                        Log.d("SERIAL NUMBER", serialnumber!!.toString())

                    } catch (e: Exception) {
                        Log.e("API CALLS ERROR", e.toString())
                    }

                }
            }


            if (serialnumber?.response != "error") {
            GlobalScope.launch {
                try {
                val responseModel: ResponseModel = MedicineRemoteDataSource(MedicineApi, Dispatchers.IO).fetchMedicineInfo(serialnumber!!.response)

                    if(responseModel != null) {
                        binding.name.text = "Name of Medicine: ${responseModel.name}"
                        binding.valid.text = "Valid: ${responseModel.valid}"
                        binding.expired.text = "Medicine ${responseModel.expired_text}"
                        Log.e("MEDICINE API CALLS RESPONSE", responseModel.toString())
                    }
                }catch (e: Exception) {
                    Log.e("MEDICINE API CALLS ERROR", e.toString())
                }
            }
            }
        }
    }

    private fun getImageRequestBody(uri: Uri): MultipartBody.Part {
        val file = File(getRealPathFromURI(uri))
        Log.d("File path", file.absolutePath)
        val requestFile = RequestBody.create(
            MediaType.parse(contentResolver.getType(uri)), file
        )
        return MultipartBody.Part.createFormData("image", file.name, requestFile)
    }

    private fun openGalleryForImage() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            foodImageUri = data?.data
            binding.serialImage.setImageURI(foodImageUri)
        }

        super.onActivityResult(requestCode, resultCode, data)


    }

    private fun getRealPathFromURI(uri: Uri?): String {
        var path = ""
        if (contentResolver != null) {
            val cursor: Cursor? = contentResolver.query(uri!!, null, null, null, null)
            if (cursor != null) {
                cursor.moveToFirst()
                val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                path = cursor.getString(idx)
                cursor.close()
            }
        }
        return path
    }
}