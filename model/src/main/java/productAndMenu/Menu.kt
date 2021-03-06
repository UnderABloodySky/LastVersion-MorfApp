package productAndMenu

import com.fasterxml.jackson.annotation.JsonIgnore
import discount.Discount
import discount.NoDiscount
import restaurant.Restaurant
import searcher.Searchable

class Menu(code: Int,
           name: String,
           description: String,
           var productsOfMenu: MutableList<Product>,
           @JsonIgnore
           var restaurant : Restaurant,
           var discount: Discount = NoDiscount(),
           var enabled: Boolean = true) : Searchable(code, name, description)

{
    var menuImage : String = ""

    fun addImageToMenu(string: String) { this.menuImage = string}

    fun addProductToMenu(product: Product) { this.productsOfMenu.add(product) }

    fun costAutocalculation(): Double { return this.discount.processDiscount(totalPrice()) }

    fun enabled():Boolean { return this.enabled }

    fun removeProductFromMenu(code: Int) {
        var tempProductList = mutableListOf<Product>()
        this.productsOfMenu.forEach {
                    if (!(it.code == code)){
                        tempProductList.add(it)
                    }
        }
        this.productsOfMenu = tempProductList
    }

    fun totalPrice(): Double  = this.productsOfMenu.sumByDouble { it.price }

    fun containProductWith(code: Int?):Boolean{
       return productsOfMenu.any{ product ->  product.code == code }
    }
}
