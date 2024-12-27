package app.c14210290.myrestoapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.c14210290.myrestoapp.database.MenuItemEntity
import app.c14210290.myrestoapp.database.RestoDB
import com.google.android.material.floatingactionbutton.FloatingActionButton


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Owner_DataMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class Owner_DataMenu : Fragment() {
    private lateinit var DB: RestoDB
    private lateinit var adapterMenu: adapter_OwnerMakananCard
    private var arMenu: MutableList<MenuItemEntity> = mutableListOf()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        DB = RestoDB.getdatabase(requireContext())
        adapterMenu = adapter_OwnerMakananCard(arMenu)
        var rvMenu = view.findViewById<RecyclerView>(R.id.rvDataMenu)

        rvMenu.layoutManager = LinearLayoutManager(this.context)
        rvMenu.adapter = adapterMenu

        var dataMenu = DB.funmenuItemDao().getAllMenuItems()
        adapterMenu.isiData(dataMenu)
        val btn_addMakanan = view.findViewById<FloatingActionButton>(R.id.btn_addMakanan)

        btn_addMakanan.setOnClickListener {
            startActivity(Intent(this.context, activityAddOrEditMakanan::class.java))
        }

        adapterMenu.setOnItemClickCallback(object : adapter_OwnerMakananCard.OnItemClickCallback {
            override fun delDataMenu(dataMenu: MenuItemEntity) {
                DB.funmenuItemDao().deleteMenuItem(dataMenu)
            }
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner__data_menu, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Owner_DataMenu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Owner_DataMenu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}