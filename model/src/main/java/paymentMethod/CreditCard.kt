package paymentMethod

import java.util.*

class CreditCard (ownerName:String,number:Int,securityNumber:Int,dueDate:Date):PaymentMethod(){
    var typePM = "CreditCard"
    var ownerName= ownerName
    var number = number
    var securityNumber = securityNumber
    var dueDate = dueDate


    fun isCreditCarType(type:String):Boolean{
        return  typePM ==type

    }
}