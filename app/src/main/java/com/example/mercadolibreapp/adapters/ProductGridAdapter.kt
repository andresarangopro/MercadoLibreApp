package com.example.mercadolibreapp.adapters

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibreapp.R
import com.example.mercadolibreapp.databinding.ItemGridProductBinding
import com.example.mercadolibreapp.domain.Product
import com.example.mercadolibreapp.imagemanager.bindImageUrl
import com.example.mercadolibreapp.utils.bindingInflate
import kotlinx.android.synthetic.main.item_grid_product.view.*

class ProductGridAdapter(
    private val listener: (Product) -> Unit
): RecyclerView.Adapter<ProductGridAdapter.PlaceGridViewHolder>() {

    private val placeList: MutableList<Product> = mutableListOf()

    fun addData(newData: List<Product>) {
        placeList.addAll(newData)
        notifyDataSetChanged()
    }

    fun newSearchData(newData: List<Product>) {
        placeList.clear()
        placeList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PlaceGridViewHolder(
            parent.bindingInflate(R.layout.item_grid_product, false),
            listener
        )

    override fun getItemCount() = placeList.size

    //override fun getItemId(position: Int): Long = characterList[position].name

    override fun onBindViewHolder(holder: PlaceGridViewHolder, position: Int) {
        holder.bind(placeList[position])
    }

    class PlaceGridViewHolder(
        private val dataBinding: ItemGridProductBinding,
        private val listener: (Product) -> Unit
    ) : RecyclerView.ViewHolder(dataBinding.root) {

        //region Public Methods
        fun bind(item: Product) {
            dataBinding.product = item
            itemView.product_image.bindImageUrl(
                url = item.thumbnail,
                placeholder = R.drawable.ic_camera_alt_black,
                errorPlaceholder = R.drawable.ic_broken_image_black
            )
            itemView.setOnClickListener { listener(item) }
        }

    }
}