package windows

import org.uqbar.commons.model.annotations.Observable
import user.User
import applicationModel.ApplicationModel

@Observable
class UserModel {

    var user:String = "..."
    var password:String = "..."


    fun autenticate(){

            var applicationModel: ApplicationModel = ApplicationModel

            var user:User? = applicationModel.findUser(user)
            if (user != null && user.isCorrectPassword(password)){

                //aca tendria que levantar la vista para armar ordenes

            }

            else{

            }
    }


}