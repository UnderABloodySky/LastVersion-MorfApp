import applicationModel.MorfApp
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import controllers.MorfAppControllerContext
import geoclaseui.Geo
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400
import paymentMethod.*
import productAndMenu.Category
import productAndMenu.Product

fun main() {
    val app = Javalin.create()
            .enableRouteOverview("/routes")
            .exception(MismatchedInputException::class.java) { e, ctx ->
                ctx.status(BAD_REQUEST_400)
                ctx.json(mapOf(
                        "status" to BAD_REQUEST_400,
                        "message" to e.message
                ))
            }
            .start(8000)
    app.get("/") { ctx -> ctx.json(mapOf("message" to " Welcome to MorfApp ~ Online ")) }

    val morfApp = MorfApp
    //Ubicacion usuarios
    val unq = Geo(-34.706272, -58.278519, "UNQ")
    val bernal = Geo(-34.709390, -58.280507, "Bernal")
    val capital = Geo(-34.603595, -58.381717, "Obelisco")
    val mordor = Geo(-666.0, -666.0, "Remedios de Escalada")

    //Ubicacion restaurants
    val flores = Geo(-34.603595, -58.381717, "Flores")

    val mChaile = morfApp.createClient("NinjaMan", "Matias Chaile", "Roque Saenz Peña 500", unq, "dilequechupelimon")
    val mPais = morfApp.createClient("BBQMaster", "Mariano Pais", "Siempre Viva 442", bernal, "proyecto")
    val jLajcha = morfApp.createClient("RunForestRun", "Juliana Lajcha", "Calle Falsa 1234", capital, "1234")
    val fCaramelieri = morfApp.createClient("OracleFanBoy", "Fede Caramelieri", "Otra Calle Falsa 4321", bernal, "plusvalia")

    val cash = Cash()
    val debit = Debit()
    val creditCard = CreditCard()
    val mercadoPago = MercadoPago()

    val onlyCash = mutableListOf<PaymentMethod>(cash)
    val cashDebitAndCreditCard = mutableListOf(cash, debit, creditCard)
    val everything =  mutableListOf(cash, debit, creditCard, mercadoPago)

    val laConga = morfApp.createRestaurant("laConga", "Un antro", "Rivadavia al algo", flores, onlyCash)
    val guerrin = morfApp.createRestaurant("guerrin", "Alta pizza", "Por Corrientes", capital, everything)
    val elTano = morfApp.createRestaurant("elTano", "Llenadero magico de tripas", "Por Avellaneda", mordor, cashDebitAndCreditCard)

    val helado = laConga.createProduct("Helado", "Hecho con leche de vacas contentas", 100.0, Category.POSTRE)
    val cocaCola = laConga.createProduct("Coca Cola", "Azucar al 200%", 80.0, Category.BEBIDA)
    val hamburguesa = laConga.createProduct("Hamburguesa", "Aprobadas por la Universidad Bovina", 120.0, Category.PLATOPRINCIPAL)
    val productsOfLaConga = mutableListOf<Product>(helado, cocaCola, hamburguesa)

    val muzza = guerrin.createProduct("Pizza Muzzarella", "Hecho con leche de vacas contentas", 120.0, Category.PLATOPRINCIPAL)
    val cuatroQuesos = guerrin.createProduct("Pizza 4 quesos", "Azucar al 200%", 280.0, Category.PLATOPRINCIPAL)
    val faina= guerrin.createProduct("faina", "Garbanzo power", 120.0, Category.ADICIONAL)
    val pepsi= guerrin.createProduct("Pepsi", "Azucar al 190%", 80.0, Category.BEBIDA)
    val productsOfGuerrin = mutableListOf(muzza, cuatroQuesos, faina, pepsi)
    val productsGuerrin = mutableListOf(muzza, pepsi)
    val vacio = elTano.createProduct("vacio", "", 80.0, Category.PLATOPRINCIPAL)
    val parrilada2 = elTano.createProduct("Parrillada para 2", "", 100.0, Category.POSTRE)
    val parrilada1= elTano.createProduct("Parrillada para 1", "", 120.0, Category.ADICIONAL)
    val chori = elTano.createProduct("choripan", "", 80.0, Category.BEBIDA)
    val productsOfElTano = mutableListOf(vacio, parrilada1, parrilada2, chori)
    val productsTano = mutableListOf(vacio, chori)

    laConga.createMenu("Menu1", "", productsOfLaConga, laConga, discount.NoDiscount(), true)
    laConga.createMenu("Menu2", "", productsOfLaConga, laConga, discount.NoDiscount(), true)

    guerrin.createMenu("MenuA", "", productsOfGuerrin, guerrin, discount.FixedDiscount(100.0), true)
    guerrin.createMenu("MenuB", "", productsGuerrin, guerrin, discount.FixedDiscount(5.0), true)

    elTano.createMenu("MenuC", "", productsOfElTano, elTano, discount.FixedDiscount(50.0), true)
    elTano.createMenu("MenuD", "", productsTano, elTano, discount.PercentageDiscount(10.0), true)

    val controller = MorfAppControllerContext()
    controller.addDataUser(mChaile)
    controller.addDataUser(mPais)
    controller.addDataUser(jLajcha)
    controller.addDataUser(fCaramelieri)

    app.routes {
        path("users") {
            get(controller::getAllUsers)
//          post(controller::addPlace)
            path(":name") {
                get(controller::getUser)
//              put(controller::updatePlace)
                delete(controller::deletePlace)
            }
        }
    }
}