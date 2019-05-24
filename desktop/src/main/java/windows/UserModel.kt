package windows

import org.uqbar.commons.model.annotations.Observable
import user.User
import applicationModel.MorfApp
import org.omg.CORBA.UserException
import restaurant.Restaurant
import user.Supervisor

@Observable
class UserModel {
    var user: String = "..."
    var password: String = "..."
    lateinit var restaurant: Restaurant

    fun autenticate() : RestaurantModel {
        var foundUser: User?
        try {
            foundUser = MorfApp.findUser(this.user)
            if (foundUser != null && foundUser.isCorrectPassword(this.password)) {

                foundUser as Supervisor
                this.restaurant = foundUser.restaurant
                return this.transformToRestaurantModel()
            }
            else {
                throw exception()
            }
        }
        catch (e : Exception){
               throw exception()
        }
    }

    private fun exception() : NoUserFoundException = NoUserFoundException ("Usuario y/0 Contraseña incorrecta")

    fun transformToRestaurantModel(): RestaurantModel{
        var restaurantModel = RestaurantModel()

                restaurantModel.restaurant = this.restaurant
                restaurantModel.name = this.restaurant.name
                restaurantModel.products = this.restaurant.products
                restaurantModel.menus = this.restaurant.menus

        return restaurantModel
    }

}

