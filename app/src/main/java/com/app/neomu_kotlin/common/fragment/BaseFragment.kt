package neomu.kotlin.common.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import neomu.kotlin.common.util.FirebaseUtil
import neomu.kotlin.common.util.FirebaseUtilImpl

open class BaseFragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onClick(view: View?) {

    }

}