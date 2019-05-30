package com.example.myapplication

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.webkit.WebView
import android.widget.*
import com.example.myapplication.adapter.ClickWithTxt
import com.example.myapplication.adapter.VRvadapter
import com.example.myapplication.model.Topic
import com.example.myapplication.networking.Api
import com.example.myapplication.networking.JobApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.myapplication.dao.DatabaseClient
import com.example.myapplication.model.room.Task
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference


class MainActivity : AppCompatActivity() {
    internal lateinit var webview: WebView
    //    String loadVideo="https://www.youtube.com/watch?v=XG9U7nJv0aE&feature=youtu.be";
    internal var loadVideo = "https://www.youtube.com/embed/9ClYy0MxsU0"

    internal lateinit var pb: ProgressBar
    internal lateinit var framen: FrameLayout

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = applicationContext
        TextTask = findViewById(R.id.editTextTask)
        TextFinishBy = findViewById(R.id.editTextFinishBy)
        TextDesc = findViewById(R.id.editTextDesc)
        rv_recycl = findViewById(R.id.rv_recycler)
        findViewById<Button>(R.id.button_save).setOnClickListener {
            SaveTaks(this,
                    editTextTask.text.toString(),
                    editTextFinishBy.text.toString(),
                    editTextDesc.text.toString()
            ).execute()
        }
        list = ArrayList()
        for (i in 0..5) {
            list!!.add("" + i)

        }
        Log.e(TAG, "53->" + list?.indexOf("" + 4))
        Log.e(TAG, "54->" + list?.filter({ it < "" + list!!.size }))
        getTask(this@MainActivity).execute()
        btn_update.setOnClickListener {
            updateTaskbyId(this@MainActivity,
                    id,
                    TextTask.text.toString().trim(),
                    TextDesc.text.toString().trim(),
                    TextFinishBy.text.toString().trim()).execute()
        }
        btn_delete.setOnClickListener {deleteParticularid(this@MainActivity, id).execute()  }
    }

    var list: ArrayList<String>? = null

    class SaveTaks(mcontext: MainActivity, toString: String, toString1: String, toString2: String) : AsyncTask<Void, Void, Void>() {
        private val activityReference: WeakReference<MainActivity> = WeakReference(mcontext)
        val activity = activityReference.get()
        var t0 = toString
        var to1 = toString1
        var to2 = toString2
        override fun doInBackground(vararg params: Void?): Void? {
            val task = Task()
            task.task = t0
            task.desc = to1
            task.finishBy = to2
            task.isFinished = false
            DatabaseClient.getInstance(activity).getAppDatabase()
                    .taskDao()
                    .insert(task)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            getTask(this.activity!!).execute()

        }

    }

    class updateTaskbyId(mcontext: MainActivity, id: String, t0: String, t1: String, t2: String) : AsyncTask<Void, Void, Void>() {
        private val activity: WeakReference<MainActivity> = WeakReference(mcontext)
        private var idi: String = id
        private var t0: String = t0
        private var t1: String = t1
        private var t2: String = t2
        override fun doInBackground(vararg params: Void?): Void? {
            val task = Task()
            task.id = idi.toInt()
            task.task = t0
            task.desc = t1
            task.finishBy = t2
            DatabaseClient.getInstance(activity.get()).appDatabase.taskDao().update(t0, id.toInt())
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            getTask(activity.get()!!).execute()
        }

    }
    class deleteParticularid(mcontext: MainActivity,id: String):  AsyncTask<Void, Void, Void>() {
        private val activity: WeakReference<MainActivity> = WeakReference(mcontext)
        private var idi: String = id
        override fun doInBackground(vararg params: Void?): Void? {
            DatabaseClient.getInstance(activity.get()).appDatabase.taskDao().delete(idi)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            getTask(activity.get()!!).execute()
        }
    }

    class getTask(mcontext: MainActivity) : AsyncTask<Void, Void, List<Task>>() {

        private val activityWeakReference: WeakReference<MainActivity> = WeakReference(mcontext)
        val activity = activityWeakReference.get()
        override fun doInBackground(vararg params: Void?): List<Task> {
            return DatabaseClient.getInstance(activity).appDatabase.taskDao().all
        }

        override fun onPostExecute(result: List<Task>?) {
            super.onPostExecute(result)
            adapter = VRvadapter(activity!!.context, result!!)

            rv_recycl!!.adapter = adapter
            rv_recycl!!.adapter!!.notifyDataSetChanged()
            adapter.clickWithTxt = object : ClickWithTxt {
                override fun onClickWidTxt(postion: Int, txt: String) {
                    Log.e(TAG, "109->" + result.get(postion).id)
                    TextTask.setText(result.get(postion).task)
                    TextDesc.setText(result.get(postion).desc)
                    TextFinishBy.setText(result.get(postion).finishBy)
                    id = "" + result.get(postion).id
                }
            }
//            adapter.performClick(postion: Int, txt: String)
        }
    }

    private fun hitgetUserAPI() {
        val dialog = ProgressDialog(this@MainActivity, R.style.AppTheme)
        dialog.setMessage("Please wait")
        dialog.setCancelable(false)
        dialog.show()
        JobApi.client.create(Api::class.java).getUserResult(
                "7S4k2mEa2338", "7S4k2mEa2338", "1555564824")
                .enqueue(object : Callback<Topic> {
                    override fun onResponse(call: Call<Topic>, response: Response<Topic>) {
                        Log.e(TAG, "67->" + response.body())
                        if (dialog != null && dialog.isShowing) {
                            dialog.dismiss()
                        }
                    }

                    override fun onFailure(call: Call<Topic>, t: Throwable) {
                        if (dialog != null && dialog.isShowing) {
                            dialog.dismiss()
                        }
                    }

                })

    }


    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }


    companion object {
        val TAG = MainActivity::class.java!!.getSimpleName()
        val baseURL: String? = "http://think360.in/kempto/api/users/"
        var rv_recycl: RecyclerView? = null
        lateinit var adapter: VRvadapter
        lateinit var TextTask: EditText
        lateinit var TextDesc: EditText
        lateinit var TextFinishBy: EditText
        var id: String = ""
    }

    fun MyMode(mode: Mode) {
        when (mode) {
            Mode.LIVE -> {
                print("LIVE")
            }
            Mode.DEV -> {
                print("DEV")
            }
            Mode.TEST -> {
                print("TEST")
            }
        }
    }

}

enum class Mode {
    TEST, DEV, LIVE
}


