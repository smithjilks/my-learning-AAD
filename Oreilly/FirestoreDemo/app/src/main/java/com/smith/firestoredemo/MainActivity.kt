package com.smith.firestoredemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.smith.firestoredemo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val db = FirebaseFirestore.getInstance()
    private val journalRef = db.collection("Journal")
        .document("First Thoughts")

    private val collectionRef = db.collection("Journal")

    companion object {
        private const val MAIN_ACTIVITY_TAG = "MainActivity"
        private const val KEY_TITLE = "title"
        private const val KEY_THOUGHT = "thought"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //val journalRef = db.collection("Journal/First Thoughts")

        binding.button.setOnClickListener {
            val title = binding.titleEditText.text.toString().trim()
            val thought = binding.thoughtsEditText.text.toString().trim()

            val journal = Journal(
                title,
                thought
            )

//            val journal = mapOf(
//                KEY_TITLE to title,
//                KEY_THOUGHT to thought
//            )

//            journalRef.set(journal)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
//                }
//                .addOnFailureListener {
//                    Log.e(MAIN_ACTIVITY_TAG, "onFailure: ${it}")
//                }

            addThought()
        }

        binding.showDataButton.setOnClickListener {

            getThoughts()
//            journalRef.get()
//                .addOnSuccessListener { docSnapShot ->
//                    if (docSnapShot.exists()){
//
//                        val journal = docSnapShot.toObject(Journal::class.java)
//
////                        binding.recTitle.text = docSnapShot.get(KEY_TITLE).toString()
////                        binding.recThoughts.text = docSnapShot.get(KEY_THOUGHT).toString()
//
//                        binding.recTitle.text = journal?.title
//
//
//                    } else {
//                        Toast.makeText(this, "No data exists", Toast.LENGTH_LONG).show()
//
//                    }
//                }
//                .addOnFailureListener {
//                    Log.e(MAIN_ACTIVITY_TAG, "onFailure: ${it}")
//                }




        }

        binding.updateDataButton.setOnClickListener {
            //Update title
            updateTheData()
        }

        binding.deleteDataButton.setOnClickListener {
            //deleteThought()
            deleteAll()
        }
    }

    private fun updateTheData() {
        val title = binding.titleEditText.text.toString().trim()
        val thought = binding.thoughtsEditText.text.toString().trim()
        val data = mapOf<String, String>(
            KEY_TITLE to title,
            KEY_THOUGHT to thought
        )
        journalRef.update(data)
            .addOnSuccessListener {



                Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show()
            }
            .addOnSuccessListener {  }
    }

    private fun deleteThought() {
        journalRef.update(KEY_THOUGHT, FieldValue.delete())
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

    private fun deleteAll() {
        journalRef.delete()
    }

    private fun addThought() {
        val title = binding.titleEditText.text.toString().trim()
        val thought = binding.thoughtsEditText.text.toString().trim()

        val journal = Journal(
            title,
            thought
        )

        collectionRef.add(journal)
            .addOnSuccessListener {
                    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Log.e(MAIN_ACTIVITY_TAG, "onFailure: ${it}")
                }

    }

    private fun getThoughts() {
        collectionRef.get()
            .addOnSuccessListener { querySnapshots ->
                var data = ""
                for (snapShot in querySnapshots) {
                    val journal = snapShot.toObject(Journal::class.java)
                    data += "Title: ${journal.title} \nThought: ${journal.thought}\n\n"

                }
                binding.recTitle.text = data

            }
            .addOnFailureListener {  }
    }

    private fun getThoughtForOnStart() {
        journalRef.addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }
            if (value?.exists()!!) {
//                binding.recTitle.text = value.get(KEY_TITLE).toString()

                val journal = value.toObject(Journal::class.java)

                binding.recTitle.text = journal?.title



            } else {
                binding.recTitle.text = ""
            }
        }
    }
    override fun onStart() {
        super.onStart()
        collectionRef.addSnapshotListener { value, error ->
            if (error != null) {
                Log.d(MAIN_ACTIVITY_TAG, "onError $error")
            }

            if (value != null) {
                var data = ""
                for (snapShot in value) {
                    val journal = snapShot.toObject(Journal::class.java)
                    data += "Title: ${journal.title} \nThought: ${journal.thought}\n\n"

                }
                binding.recTitle.text = data


            } else {
                binding.recTitle.text = ""
            }
        }


    }

    override fun onStop() {
        super.onStop()
    }
}