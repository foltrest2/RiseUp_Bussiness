package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.databinding.ActivityConfigHelpCenterBinding
import com.riseup.riseup_users.ContactusHelpCenterFragment
import com.riseup.riseup_users.FaqHelpCenterFragment

class ConfigHelpCenterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigHelpCenterBinding
    private lateinit var faqFragment: FaqHelpCenterFragment
    private lateinit var contatUsFragment: ContactusHelpCenterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigHelpCenterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        faqFragment = FaqHelpCenterFragment.newInstance()
        contatUsFragment = ContactusHelpCenterFragment.newInstance()

        showFragment(faqFragment)
        binding.contactUsDivider.setBackgroundResource(R.color.grayFigma)

        binding.navSuperiorConfigHelpCenter.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.faqItem) {
                showFragment(faqFragment)
                binding.contactUsDivider.setBackgroundResource(R.color.grayFigma)
                binding.faqDivider.setBackgroundResource(R.drawable.gradient_rosado_azul)
            } else if (menuItem.itemId == R.id.contactenosItem){
                binding.faqDivider.setBackgroundResource(R.color.grayFigma)
                binding.contactUsDivider.setBackgroundResource(R.drawable.gradient_rosado_azul)
                showFragment(contatUsFragment)
            }

            true
        }

        binding.backConfigHelpCenterBtn.setOnClickListener {
            startActivity(Intent(this@ConfigHelpCenterActivity, ConfigHelpActivity::class.java))
        }


    }
    fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.helpCenterFragContainer, fragment)
        transaction.commit()
    }
}