package com.safframework.netty4android.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.safframework.netty4android.databinding.ActivityConfigClientBinding


/**
 *
 * @FileName:
 *          com.safframework.netty4android.ui.ConfigClientActivity
 * @author: Tony Shen
 * @date: 2019-08-10 12:01
 * @version: V1.0 <描述当前版本功能>
 */
class ConfigClientActivity : AppCompatActivity(){
//    val update = findViewById<Button>(R.id.update)
//    val ip_edit=findViewById<EditText>(R.id.ip_edit)
//    val port_edit=findViewById<EditText>(R.id.port_edit)
    private lateinit var binding: ActivityConfigClientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityConfigClientBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_config_client)

        if (intent.extras!=null) {

            val ip = intent.extras!!.getString("ip")
            binding.ipEdit.text = Editable.Factory.getInstance().newEditable(ip)
            if (ip != null) {
                binding.ipEdit.setSelection(ip.length)
            }

            val port = intent.extras!!.getInt("port")
            binding.portEdit.text = Editable.Factory.getInstance().newEditable(port.toString())
            binding.portEdit.setSelection(binding.portEdit.text.toString().length)
        }

        binding.update.setOnClickListener {

            if (binding.ipEdit.text.isNotBlank() && binding.portEdit.text.isNotBlank()) {

                val intent = Intent(this@ConfigClientActivity, MainActivity::class.java)
                intent.putExtra("port", binding.portEdit.text.toString())
                intent.putExtra("ip", binding.ipEdit.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {

                Toast.makeText(this@ConfigClientActivity, "请输入IP、端口号", Toast.LENGTH_LONG).show()
            }
        }
    }
}