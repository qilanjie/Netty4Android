package com.safframework.netty4android.ui

import android.app.Activity
import android.os.Bundle

import com.safframework.netty4android.R
import android.content.Intent
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.safframework.netty4android.databinding.ActivityConfigServerBinding
import com.safframework.netty4android.databinding.ActivityMainBinding


/**
 *
 * @FileName:
 *          com.safframework.netty4android.ui.ConfigServerActivity
 * @author: Tony Shen
 * @date: 2019-08-09 12:04
 * @version: V1.0 <描述当前版本功能>
 */
class ConfigServerActivity : AppCompatActivity(){
    private lateinit var binding: ActivityConfigServerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_config_server)
        binding= ActivityConfigServerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (intent.extras!=null) {

            val port = intent.extras!!.getInt("port")
            binding.portEdit.text = Editable.Factory.getInstance().newEditable(port.toString())
            binding.portEdit.setSelection(binding.portEdit.text.toString().length)

            val webSocketPath = intent.extras!!.getString("webSocketPath")
            binding.webSocketPathEdit.text = Editable.Factory.getInstance().newEditable(webSocketPath)
            if (webSocketPath != null) {
                binding.webSocketPathEdit.setSelection(webSocketPath.length)
            }
        }

        binding.update.setOnClickListener {

            if (binding.portEdit.text.isNotBlank()) {

                val intent = Intent(this@ConfigServerActivity, MainActivity::class.java)
                intent.putExtra("port", binding.portEdit.text.toString())
                intent.putExtra("webSocketPath", binding.webSocketPathEdit.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {

                Toast.makeText(this@ConfigServerActivity, "请输入端口号", Toast.LENGTH_LONG).show()
            }
        }
    }
}